package yongle.dataBase.shipblacklist;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Record;

import yongle.dataBase.ship.ShipController;
import yongle.dataBase.ship.ShipService;
import yongle.interceptor.ManageInterceptor;
/**
 * @ClassName: ShipBlackListController.java
 * @Description: 船舶黑名单管理
 * @author: xuhui
 * @date: 2017年8月29日下午17:40:05
 * @version: 1.0 版本初成
 */
@Before(ManageInterceptor.class)
public class ShipBlackListController extends ShipController{

	public void index(){
		render("ship_blacklist.html");
	}
	
	/**
	 * @desc 展示清单页数据,同时加载成品和半成品
	 * @author xuhui
	 */
	public void getJson(){
		String shipowname = getPara("shipowname");
		String ship_name = getPara("ship_name");
    	Integer	pageindex = 0;
    	Integer pagelimit = getParaToInt("limit")==null? 12 :getParaToInt("limit");
    	Integer offset = getParaToInt("offset")==null?0:getParaToInt("offset");
    	if(offset!=0){
    		pageindex = offset/pagelimit;
    	}
    	pageindex += 1;
    	Map<String, Object> map = new HashMap<String,Object>(); 	
    	List<Record> dictionaryList = ShipBlackService.getJson(pageindex, pagelimit,shipowname,ship_name).getList();
    	map.put("rows", dictionaryList);
    	map.put("total",ShipBlackService.getJson(pageindex, pagelimit,shipowname,ship_name).getTotalRow());
    	renderJson(map);	
	}
	
	/**
	 * @desc 移除黑名单
	 * @author xuhui
	 */
		public void setOutBlack(){
			String blackList = getPara();
			//判断是否成功
			boolean flag = ShipBlackService.setOutBlack(blackList);
			renderJson(flag);
		}
	
	
	
}
