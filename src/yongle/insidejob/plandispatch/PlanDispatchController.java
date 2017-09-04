package yongle.insidejob.plandispatch;

import java.util.HashMap;
import java.util.Map;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import yongle.site.handoversite.HandoverSiteService;

/**
 * @ClassName: PlanDispatchController.java
 * @Description: 内勤管理-计划调度
 * @author: LiYu
 * @date: 2017年8月31日上午8:36:41
 * @version: 1.0 版本初成
 */
public class PlanDispatchController extends Controller {
    /** 
    * @Title: index 
    * @Description: 页面
    * @author liyu
    */
    public void index() {
        render("plan_dispatch.html");
    }
    
    /** 
    * @Title: getJson 
    * @Description: 数据
    * @author liyu
    */
    public void getJson(){
        String plan_no = getPara("plan_no");
        String consignor = getPara("consignor");
        
        Integer pageindex = 0; // 页码
        Integer pagelimit = getParaToInt("limit")==null? 12 :getParaToInt("limit"); // 每页数据条数
        Integer offset = getParaToInt("offset")==null?0:getParaToInt("offset");
        if(offset!=0){
            pageindex = offset/pagelimit;
        }
        pageindex += 1;
        
        Page<Record> page = PlanDispatchService.getDataPages(pageindex, pagelimit, 
                plan_no, consignor);
        
        Map<String, Object> map = new HashMap<String,Object>();
        
        map.put("total", page.getTotalRow());
        map.put("rows", page.getList());
        
        renderJson(map);
    }
}
