package yongle.dispatchmanage.handover;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

/**
 * @ClassName: HandoverController.java
 * @Description: 调度交接
 * @author: LiYu
 * @date: 2017年8月25日下午2:40:05
 * @version: 1.0 版本初成
 */
public class HandoverController extends Controller {
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
        //  
        String warehouse_in_no = getPara("warehouse_in_no");
        setAttr("warehouse_in_no", warehouse_in_no);
        //  
        String company_name = getPara("company_name");
        setAttr("company_name", company_name);
        
        Integer pageindex = 0; // 页码
        Integer pagelimit = getParaToInt("limit")==null? 12 :getParaToInt("limit"); // 每页数据条数
        Integer offset = getParaToInt("offset")==null?0:getParaToInt("offset");
        if(offset!=0){
            pageindex = offset/pagelimit;
        }
        pageindex += 1;
        
        Page<Record> page = HandoverService.getDataPages(pageindex, pagelimit, warehouse_in_no, company_name);
        
        Map<String, Object> map = new HashMap<String,Object>();
        
        map.put("total", page.getTotalRow());
        map.put("rows", page.getList());
        
        renderJson(map);
    }
    
    public void getRecord() {
        render("handover_detail.html");
    }
}
