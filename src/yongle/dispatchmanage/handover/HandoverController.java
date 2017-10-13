package yongle.dispatchmanage.handover;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import yongle.interceptor.ManageInterceptor;
import yongle.utils.ResponseObj;

/**
 * @ClassName: HandoverController.java
 * @Description: 调度交接
 * @author: LiYu
 * @date: 2017年8月25日下午2:40:05
 * @version: 1.0 版本初成
 */
@Before(ManageInterceptor.class)
public class HandoverController extends Controller {
    
    private ResponseObj msg = new ResponseObj();
    
    /** 
    * @Title: index 
    * @Description: 调度交接表页面
    * @author liyu
    */
    public void index() {
        render("handover.html");
    }
    
    /** 
    * @Title: getJson 
    * @Description: 数据
    * @author liyu
    */
    public void getJson(){
        String plan_no = getPara("plan_no"); // 计划号
        String goods_name = getPara("goods_name"); // 货物名称
        String entry_start = getPara("entry_start"); // 录入起始时间
        String entry_end = getPara("entry_end"); // 录入截止时间
        String dispatcher = getPara("entry_man"); // 录入人
        
        Integer pageindex = 0; // 页码
        Integer pagelimit = getParaToInt("limit")==null? 12 :getParaToInt("limit"); // 每页数据条数
        Integer offset = getParaToInt("offset")==null?0:getParaToInt("offset");
        if(offset!=0){
            pageindex = offset/pagelimit;
        }
        pageindex += 1;
        
        Page<Record> page = HandoverService.getDataPages(pageindex, pagelimit, 
                plan_no, goods_name, entry_start, entry_end, dispatcher);
        
        Map<String, Object> map = new HashMap<String,Object>();
        
        map.put("total", page.getTotalRow());
        map.put("rows", page.getList());
        
        renderJson(map);
    }
    
    /** 
    * @Title: getRecord 
    * @Description: 编辑页面
    * @author liyu
    */
    public void getRecord() {
        Integer id = getParaToInt(); // 流向 id
        Record record = HandoverService.getRecordById(id);
        setAttr("record", record);
        List<Record> recordList = Db.find("SELECT *,a.id FROM `t_dispatch_ship` AS a LEFT JOIN t_dispatch_detail AS b ON a.dispatch_detail_id = b.id WHERE dispatch_detail_id = ? ", id);
        setAttr("recordList", recordList);
        
        render("handover_detail.html");
    }
    
    /** 
    * @Title: save 
    * @Description: 保存
    * @author liyu
    */
    public void save() {
        Integer dispatch_detail_id = getParaToInt("id"); // 计划 id
        String left_qty = getPara("left_qty"); // 剩余吨数
        String recordList = getPara("recordList"); // 驳船列表
        Record user = getSessionAttr("admin");
        //user = Db.findById("t_user", 1);
        String dispatcher = user.getStr("user_name");
        
        msg = HandoverService.save(dispatch_detail_id, left_qty, recordList, dispatcher);
        renderJson(msg);
    }
    
    /** 
    * @Title: review 
    * @Description: 审核
    * @author liyu
    */
    public void review() {
        ResponseObj res = new ResponseObj(); // 返回信息
        Integer id = getParaToInt(0); // 流向 id
        
        // 运价超出指导价，未同意，禁止审核
        Long a = Db.queryLong("SELECT COUNT(1) FROM t_dispatch_ship WHERE dispatch_detail_id = ? AND over_guide_price = 1 ", id);
        if (a > 0) {
            res.setCode(ResponseObj.FAILED);
            res.setMsg("运价超出指导价，经理未同意，禁止审核");
            
            renderJson(res);
            return;
        }
        
        boolean result = HandoverService.review(id);
        res.setCode(result ? ResponseObj.OK : ResponseObj.FAILED);
        res.setMsg(result ? "完成审核" : "审核失败");
        
        renderJson(res);
    }
    /**
     * @desc 获取计划单审核状态
     * @author liyu
     */
    public void getExamineState(){
        boolean flag = false;
        Integer id = getParaToInt(0);
        Record r = Db.findById("t_dispatch", id);
        Integer value = r.getInt("dispatch_review");
        //判断审核状态 0、待审核，1、已审核，2、取消审核
        if(value == 1){
            flag = true;
        }
        renderJson(flag);
    }
    
    /** 
    * @Title: getShipInfoByShipName 
    * @Description: 根据船名获取相关信息
    * @author liyu
    */
    public void getShipInfoByShipName() {
        ResponseObj res = new ResponseObj(); // 返回信息
        String ship_name = getPara("ship_name"); // 船名
        Record ship = Db.findFirst("SELECT * FROM t_base_ship WHERE ship_name = ?", ship_name);
        
        // 提示此船在黑名单中
        if (ship!=null && ship.getInt("blacklist") == 1) {
            res.setCode(ResponseObj.FAILED);
            res.setMsg("此船在黑名单中，请选择其他船只。");
            renderJson(res);
            return;
        }
        
        res.setCode(ship != null ? ResponseObj.OK : ResponseObj.FAILED);
        res.setMsg(ship != null ? "" : "船舶信息中不存在该船");
        res.setData(ship);
        
        renderJson(res);
    }
    
    /** 
    * @Title: export 
    * @Description: 导出 excel
    * @author liyu
    */
    public void export() {
        Integer id = getParaToInt();
        Record r = Db.findById("t_dispatch_detail", id);
        
        boolean result = HandoverService.export(getResponse(), r.getInt("plan_no_id"));
        
        if (result) {
            renderNull();
        } else {
            renderText("导出失败");
        }
    }
    
}
