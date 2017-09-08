package yongle.dispatchmanage.handover;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import yongle.utils.ResponseObj;

/**
 * @ClassName: HandoverController.java
 * @Description: 调度交接
 * @author: LiYu
 * @date: 2017年8月25日下午2:40:05
 * @version: 1.0 版本初成
 */
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
        String plan_no = getPara("plan_no");
        String goods_name = getPara("goods_name");
        String entry_start = getPara("entry_start");
        String entry_end = getPara("entry_end");
        String entry_man = getPara("entry_man");
        
        Integer pageindex = 0; // 页码
        Integer pagelimit = getParaToInt("limit")==null? 12 :getParaToInt("limit"); // 每页数据条数
        Integer offset = getParaToInt("offset")==null?0:getParaToInt("offset");
        if(offset!=0){
            pageindex = offset/pagelimit;
        }
        pageindex += 1;
        
        Page<Record> page = HandoverService.getDataPages(pageindex, pagelimit, 
                plan_no, goods_name, entry_start, entry_end, entry_man);
        
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
        Integer id = getParaToInt();
        Record record = Db.findById("t_dispatch", id);
        setAttr("record", record);
        List<Record> recordList = Db.find("SELECT *,a.id FROM `t_dispatch_ship` AS a LEFT JOIN t_dispatch_detail AS b ON a.dispatch_detail_id = b.id WHERE dispatch_id = ? ", id);
        setAttr("recordList", recordList);
        
        // 收货单位
        List<Record> consigneeList = Db.find("SELECT * FROM t_dispatch_detail WHERE plan_no_id = ?", id);
        // setAttr("consigneeList", consigneeList);
        setAttr("consigneeList", JsonKit.toJson(consigneeList));
        
        render("handover_detail2.html");
    }
    
    /** 
    * @Title: save 
    * @Description: 保存
    * @author liyu
    */
    public void save() {
        Integer dispatch_id = getParaToInt("id"); // 计划 id
        String left_quantity = getPara("left_quantity"); // 剩余吨数
        String site_dispatch = getPara("site_dispatch"); // 现场调度
        String recordList = getPara("recordList"); // 驳船列表
        Record user = getSessionAttr("admin");
        user = Db.findById("t_user", 1);
        String dispatcher = user.getStr("user_name");
        
        msg = HandoverService.save(dispatch_id, left_quantity, site_dispatch, recordList, dispatcher);
        renderJson(msg);
    }
    
    /** 
    * @Title: review 
    * @Description: 审核
    * @author liyu
    */
    public void review() {
        ResponseObj res = new ResponseObj(); // 返回信息
        Integer id = getParaToInt(0);
        Record record = new Record();
        record.set("id", id);
        
        Record dbRecord = Db.findById("t_dispatch", id);
        // 已下发不允许取消审核
        if (dbRecord.getInt("dispatch_issue") == 1) {
            res.setCode(ResponseObj.FAILED);
            res.setMsg("已下发不能取消审核");
            renderJson(res);
            return;
        }
        //判断审核状态 0、待审核，1、已审核，2、取消审核
        Integer value = dbRecord.getInt("dispatch_review");
        if(value==0||value==2){
            record.set("dispatch_review", 1);
            boolean result = Db.update("t_dispatch", record);
            res.setCode(result ? ResponseObj.OK : ResponseObj.FAILED);
            res.setMsg(result ? "完成审核" : "审核失败");
        }else{
            record.set("dispatch_review", 2);
            boolean result = Db.update("t_dispatch", record);
            res.setCode(result ? ResponseObj.OK : ResponseObj.FAILED);
            res.setMsg(result ? "取消审核成功" : "取消审核失败");
        }
        
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
    * @Title: issue 
    * @Description: 下发
    * @author liyu
    */
    public void issue() {
        // 返回信息
        ResponseObj res = new ResponseObj();
        // 
        Integer id = getParaToInt();
        Record r = Db.findById("t_dispatch", id);
        // 未审核禁止下发
        Integer dispatch_review = r.getInt("dispatch_review");
        if (dispatch_review != 1) {
            res.setCode(ResponseObj.FAILED);
            res.setMsg("请审核后下发");
            renderJson(res);
            return;
        }
        // 已下发
        if (r.getInt("dispatch_issue") == 1) {
            res.setCode(ResponseObj.FAILED);
            res.setMsg("已下发");
            renderJson(res);
            return;
        }
        
        // 修改下发状态
        r.set("dispatch_issue", 1);
        boolean result = Db.update("t_dispatch", r);
        res.setCode(result ? ResponseObj.OK : ResponseObj.FAILED);
        res.setMsg(result ? "完成下发" : "下发失败");
        
        renderJson(res);
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
        
        res.setCode(ship != null ? ResponseObj.OK : ResponseObj.FAILED);
        res.setData(ship);
        
        renderJson(res);
    }
    
    public void export() {
        Integer id = getParaToInt();
        Record record = Db.findById("t_dispatch", id);
        setAttr("record", record);
        List<Record> recordList = Db.find("SELECT *,a.id FROM `t_dispatch_ship` AS a LEFT JOIN t_dispatch_detail AS b ON a.dispatch_detail_id = b.id WHERE dispatch_id = ? ", id);
        setAttr("recordList", recordList);
    }
    
}
