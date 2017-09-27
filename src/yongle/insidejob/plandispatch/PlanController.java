package yongle.insidejob.plandispatch;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import yongle.interceptor.ManageInterceptor;
import yongle.model.Dispatch;
import yongle.utils.ResponseObj;

/**
 * @ClassName: PlanDispatchController.java
 * @Description: 内勤管理-计划调度
 * @author: LiYu
 * @date: 2017年8月31日上午8:36:41
 * @version: 1.0 版本初成
 */
@Before(ManageInterceptor.class)
public class PlanController extends Controller {
    public void index() {
        render("plan_dispatch.html");
    }

    /** 
    * @Title: index 
    * @Description: 页面
    * @author liyu
    */
    public void search() {
    	
    	String plan_no = getPara("plan_no");
    	setAttr("plan_no", plan_no);
    	String consignor = getPara("consignor");
    	setAttr("consignor", consignor);
    	
    	if (plan_no != null && !"".equals(plan_no)) {
    	    List<Record> list = Db.find("SELECT * FROM t_dispatch WHERE plan_no = ? ", plan_no);
    	    if (list.size() > 0) {
    	        setAttr("dispatch", list.get(0));
    	    }
    	    
    	}
    	
        List<Record> recordList =  PlanService.getRecordList(plan_no, consignor);
        setAttr("recordList", recordList);
        render("plan_dispatch.html");
    }
    
    /** 
    * @Title: save 
    * @Description: 保存
    * @author liyu
    */
    public void save() {
        // 返回信息
        ResponseObj res = new ResponseObj();
        // 计划号 id
        Integer dispatch_id = getParaToInt("dispatch_id");
        // 公司发货现场负责人
        String site_dispatch = getPara("site_dispatch");
        
        Dispatch record = new Dispatch();
        record.set("id", dispatch_id);
        record.set("site_dispatch", site_dispatch);
        boolean result = record.update();
        
        res.setCode(result ? ResponseObj.OK : ResponseObj.FAILED);
        res.setMsg(result ? ResponseObj.SAVE_SUCCESS : ResponseObj.SAVE_FAILED);
        
        renderJson(res);
    }

}
