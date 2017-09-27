package yongle.settle.customerfreight;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import yongle.interceptor.ManageInterceptor;
/**
 * @ClassName: CustomerSettledController.java
 * @Description:
 * @author: xuhui
 * @date: 2017年9月5日上午10:45:55
 * @version: 1.0 版本初成
 */
@Before(ManageInterceptor.class)
public class CustomerSettledController extends Controller {

	/**
	 * @desc 船舶结算管理-已结算
	 * @author xuhui
	 */
	public void index(){
		render("customer_settled.html");
	}
	
	 /** 
	    * @Title: getJson 
	    * @Description: 数据
	    * @author liyu
	    */
	    public void getJson(){
	        String plan_no = getPara("plan_no");    
	        Integer pageindex = 0; // 页码
	        Integer pagelimit = getParaToInt("limit")==null? 12 :getParaToInt("limit"); // 每页数据条数
	        Integer offset = getParaToInt("offset")==null?0:getParaToInt("offset");
	        if(offset!=0){
	            pageindex = offset/pagelimit;
	        }
	        pageindex += 1;
	        
	        Page<Record> page = CustomerSettledService.getDataPage(pageindex, pagelimit, plan_no);
	        
	        Map<String, Object> map = new HashMap<String,Object>();
	        
	        map.put("total", page.getTotalRow());
	        map.put("rows", page.getList());
	        
	        renderJson(map);
	    }
	
    /** 
    * @Title: print 
    * @Description: 打印
    * @author liyu
    */
    public void print() {
        
        String plan_no = getPara("plan_no"); // 计划号
        Record plan = new Record();
        if (plan_no != null && !"".equals(plan_no)) {
            List<Record> list = Db.find("SELECT * FROM t_dispatch WHERE plan_no = '"+ plan_no +"'");
            if (list.size() > 0) {
                plan = list.get(0);
            }
        }
        List<Record> recordList = CustomerSettledService.getList(plan_no);
        BigDecimal total = CustomerSettledService.calculateTotal(recordList);
        
        Map<String, Object> res = new HashMap<>();
        res.put("recordList", recordList);
        res.put("total", total);
        res.put("plan", plan);
        
        renderJson(res);
    }
}
