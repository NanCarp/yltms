package yongle.dispatchmanage.handover;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.core.Controller;
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
        List<Record> recordList = Db.find("SELECT * FROM `t_dispatch_ship` WHERE dispatch_id = ? ", id);
        setAttr("recordList", recordList);
        
        render("handover_detail.html");
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
        msg = HandoverService.save(dispatch_id, left_quantity, site_dispatch, recordList);
        renderJson(msg);
    }
    
    /** 
    * @Title: review 
    * @Description: 审核
    * @author liyu
    */
    public void review() {
        Integer id = getParaToInt(0);
        Record record = new Record();
        record.set("id", id);
        //判断审核状态 0、待审核，1、已审核，2、取消审核
        Integer value = Db.findById("t_dispatch", id).getInt("dispatch_review");
        if(value==0||value==2){
            record.set("dispatch_review", 1);
        }else{
            record.set("dispatch_review", 2);
        }
        boolean result = Db.update("t_dispatch", record);
        renderJson(result);
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
        // 修改下发状态
        r.set("dispatch_issue", 1);
        boolean result = Db.update("t_dispatch", r);
        res.setCode(result ? ResponseObj.OK : ResponseObj.FAILED);
        res.setMsg(result ? "完成下发" : "下发失败");
        
        renderJson(res);
    }
}
