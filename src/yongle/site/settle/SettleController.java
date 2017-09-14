package yongle.site.settle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;


/**
 * @ClassName: SettleController.java
 * @Description: 结算审核表(现场)
 * @author: xuhui
 * @date: 2017年8月29日上午14:11:26
 * @version: 1.0 版本初成
 */
public class SettleController extends Controller{

	/**
	 * @desc 清单页展示
	 * @author xuhui
	 */
	public void index(){
		render("settle.html");
	}
	
	 /** 
	    * @Title: getJson 
	    * @Description: 数据
	    * @author xuhui
	    */
	    public void getJson(){
	    	String plan_no = getPara("plan_no");
			String consignor = getPara("consignor");
			Integer dispatch_settle_state = getParaToInt("dispatch_settle_state");
	    	
	        Integer pageindex = 0; // 页码
	        Integer pagelimit = getParaToInt("limit")==null? 12 :getParaToInt("limit"); // 每页数据条数
	        Integer offset = getParaToInt("offset")==null?0:getParaToInt("offset");
	        if(offset!=0){
	            pageindex = offset/pagelimit;
	        }
	        pageindex += 1;
	        Page<Record> page = SettleService.getJson(pageindex, pagelimit,plan_no,consignor,dispatch_settle_state);
	        Map<String, Object> map = new HashMap<String,Object>();
	        map.put("total", page.getTotalRow());
	        map.put("rows", page.getList());
	        renderJson(map);
	    }
	
	/**
	 * @desc 审核明细
	 * @author xuhui
	 */
	public void seeDetail(){
		Integer id = getParaToInt(0);
		String sql = "SELECT t.*,d.entry_time,d.estimated_arrvial_date from t_dispatch_ship t LEFT "
				+ "JOIN t_dispatch d ON t.dispatch_id = d.id where t.dispatch_id ="+id;
		List<Record> list = Db.find(sql);
		setAttr("list", list);
		setAttr("id", id);
		render("settle_confirm.html");
	}
	
	/**
	 * @desc 判断内容是否可以取消审核
	 * ,在符合完成的情况下，不可以取消审核
	 * @author xuhui
	 */
	public void isCancelCheck(){
		//返回结果map
		Map<String,Object> map =  new HashMap<String,Object>();
		//判断是否可以更改数据
		boolean flag = true;
		Integer id = getParaToInt(0);
		String sql ="select bursar_settle_state,site_settle_state from t_dispatch where id="+id;
		//审核结算表（复核）审核状态，0，待审核，1.已审核，2，取消审核
		Integer state = Db.findFirst(sql).getInt("bursar_settle_state");
		//获取现场当前审核状态
		Integer siteState = Db.findFirst(sql).getInt("site_settle_state");
		if(state==1){
			flag = false;
		}
		map.put("state", flag);
		map.put("siteState", siteState);
		renderJson(map);
	}
	
	/**
	 * @desc 审核结算表
	 * @author xuhui
	 */
	public void checkOut(){
		Map<String,Object> map = new HashMap<String,Object>();
		Integer id = getParaToInt(0);
		//判断是否更改成功
		boolean flag = false;
		String tips ="";
		String sql = "select site_settle_state from t_dispatch where id="+id;
		Record record = new Record();
		record.set("id", id);
		//flag_num状态：0，待审核；1，已审核；2，取消审核
		Integer flag_num = Db.queryInt(sql);
		if(flag_num==0||flag_num==2){
			record.set("site_settle_state", 1);
			tips="审核成功";
		}else{
			record.set("site_settle_state", 2);
			tips="完成取消审核";
		}
		flag = Db.update("t_dispatch", record);
		map.put("tips",tips);
		map.put("flag", flag);
		renderJson(map);
	}
	
}
