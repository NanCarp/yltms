package yongle.dispatchmanage.handoverfinish;

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
public class HandoverFinishController extends Controller {
    
    /** 
    * @Title: index 
    * @Description: 调度交接表页面
    * @author liyu
    */
    public void index() {
        render("handover_finish.html");
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
        String entry_man = getPara("entry_man"); // 录入人
        
        Integer pageindex = 0; // 页码
        Integer pagelimit = getParaToInt("limit")==null? 12 :getParaToInt("limit"); // 每页数据条数
        Integer offset = getParaToInt("offset")==null?0:getParaToInt("offset");
        if(offset!=0){
            pageindex = offset/pagelimit;
        }
        pageindex += 1;
        
        Page<Record> page = HandoverFinishService.getDataPages(pageindex, pagelimit, 
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
        Integer id = getParaToInt(); // 流向 id
        Record record = HandoverFinishService.getRecordById(id);
        setAttr("record", record);
        List<Record> recordList = Db.find("SELECT *,a.id FROM `t_dispatch_ship` AS a LEFT JOIN t_dispatch_detail AS b ON a.dispatch_detail_id = b.id WHERE dispatch_detail_id = ? ", id);
        setAttr("recordList", recordList);
        
        render("handover_finish_detail.html");
    }
       
    /** 
    * @Title: cancel
    * @Description: 取消审核
    * @author liyu
    */
    public void cancel() {
        ResponseObj res = new ResponseObj(); // 返回信息
        Integer id = getParaToInt(0); // 流向 id
        
        // 存在后续业务，不能取消
        if (HandoverFinishService.hasBusiness(id)) {
            res.setCode(ResponseObj.FAILED);
            res.setMsg("已录入现场信息，不能取消审核");
            renderJson(res);
            return;
        }
        
        boolean result = HandoverFinishService.cancel(id);
        res.setCode(result ? ResponseObj.OK : ResponseObj.FAILED);
        res.setMsg(result ? "完成" : "失败");
        
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
    * @Title: export 
    * @Description: 导出 excel
    * @author liyu
    */
    public void export() {
        Integer id = getParaToInt();
        Record r = Db.findById("t_dispatch_detail", id);
        
        boolean result = HandoverFinishService.export(getResponse(), r.getInt("plan_no_id"));
        
        if (result) {
            renderNull();
        } else {
            renderText("导出失败");
        }
    }
    
}
