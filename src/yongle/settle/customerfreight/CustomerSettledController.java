package yongle.settle.customerfreight;

import java.util.HashMap;
import java.util.Map;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
/**
 * @ClassName: CustomerSettledController.java
 * @Description:
 * @author: xuhui
 * @date: 2017年9月5日上午10:45:55
 * @version: 1.0 版本初成
 */
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
	
}
