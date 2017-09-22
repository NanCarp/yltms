package yongle.settle.shipfreight;

import java.util.HashMap;
import java.util.Map;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

/**
 * @ClassName: ShipSettleController.java
 * @Description: 结算审核表-客户运费结算清单
 * @author: xuhui
 * @date: 2017年9月5日上午10:55:00
 * @version: 1.0 版本初成
 */
public class ShipSettleController extends Controller{

	
	public void index(){
		render("ship_settled.html");
	}
	
	 /** 
	    * @desc 展示清单页
	    * @author xuhui
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
	        Page<Record> page = ShipSettleService.getDataPages(pageindex, pagelimit, plan_no);
	        Map<String, Object> map = new HashMap<String,Object>();
	        map.put("total", page.getTotalRow());
	        map.put("rows", page.getList());
	        renderJson(map);
	    }
	
}
