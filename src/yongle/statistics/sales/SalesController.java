package yongle.statistics.sales;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import yongle.interceptor.ManageInterceptor;

/**
 * @ClassName: SalesController.java
 * @Description:
 * @author: LiYu
 * @date: 2017年9月13日下午3:16:56
 * @version: 1.0 版本初成
 */
@Before(ManageInterceptor.class)
public class SalesController extends Controller {

	public void index(){
		render("sales.html");
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
        
        Page<Record> page = SalesService.getDataPages(pageindex, pagelimit, 
                plan_no, consignor);
        
        Map<String, Object> map = new HashMap<String,Object>();
        
        map.put("total", page.getTotalRow());
        map.put("rows", page.getList());
        
        renderJson(map);
    }
    
    /** 
    * @Title: detail 
    * @Description: 查看
    * @author liyu
    */
    public void detail() {
        Integer id = getParaToInt();
        
        Record plan = Db.findById("t_dispatch", id);
        setAttr("plan", plan);
        List<Record> shipList = SalesService.getShipSettleList(id);
        setAttr("shipList", shipList);
        List<Record> customerList = SalesService.getCustomerSettleList(id);
        setAttr("customerList", customerList);
        
        render("sales_detail.html");
    }
    
}
