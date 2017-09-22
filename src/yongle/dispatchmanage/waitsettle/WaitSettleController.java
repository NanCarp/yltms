package yongle.dispatchmanage.waitsettle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import yongle.interceptor.ManageInterceptor;
/**
 * @ClassName: WaitSettleController.java
 * @Description: 结算审核管理-结算审核表
 * @author: xuhui
 * @date: 2017年8月29日上午11:01:26
 * @version: 1.0 版本初成
 */
@Before(ManageInterceptor.class)
public class WaitSettleController extends Controller {

	/**
	 * @desc 展示待审核清单页
	 * @author xuhui
	 */
	public void index(){
		render("waitsettle.html");
	}
	
	/**
	 * @desc 展示清单页数据,同时加载成品和半成品
	 * @author xuhui
	 */
	public void getJson(){
		String plan_no = getPara("plan_no");
		String consignor = getPara("consignor");
		Integer dispatch_settle_state = getParaToInt("dispatch_settle_state");
		
    	Integer	pageindex = 0;
    	Integer pagelimit = getParaToInt("limit")==null? 12 :getParaToInt("limit");
    	Integer offset = getParaToInt("offset")==null?0:getParaToInt("offset");
    	if(offset!=0){
    		pageindex = offset/pagelimit;
    	}
    	pageindex += 1;
    	Map<String, Object> map = new HashMap<String,Object>(); 	
    	List<Record> dictionaryList = WaitSettleService.getJson(pageindex, pagelimit
    			,plan_no,consignor,dispatch_settle_state).getList();
    	map.put("rows", dictionaryList);
    	map.put("total",WaitSettleService.getJson(pageindex, pagelimit,plan_no,consignor
    			,dispatch_settle_state).getTotalRow());
    	renderJson(map);	
	}
	
	/**
	 * @desc 打开审核明细
	 * @author xuhui
	 */
	public void examDetail(){
		Integer id = getParaToInt(0);
		String sql = "SELECT t.*,d.*,p.* from t_dispatch_ship t LEFT JOIN t_dispatch_detail d "
				+ " ON t.dispatch_detail_id = d.id LEFT JOIN t_dispatch p ON d.plan_no_id = p.id where p.id ="+id;
		List<Record> list = Db.find(sql);
		setAttr("list", list);
		setAttr("id", id);
		render("waitsettle_confirm.html");
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
		String sql = "select dispatch_settle_state from t_dispatch where id="+id;
		Record record = new Record();
		record.set("id", id);
		//flag_num状态：0，待审核；1，已审核；2，取消审核
		Integer flag_num = Db.queryInt(sql);
		if(flag_num==0||flag_num==2){
			record.set("dispatch_settle_state", 1);
			tips="审核成功";
		}else{
			record.set("dispatch_settle_state", 2);
			tips="完成取消审核";
		}
		flag = Db.update("t_dispatch", record);
		map.put("tips",tips);
		map.put("flag", flag);
		renderJson(map);;
	}
	
	/**
	 * @desc 判断内容是否可以取消审核
	 * ,在现场已完成审核的情况下，调度不可以取消审核
	 * @author xuhui
	 */
	public void isCancelCheck(){
		Map<String,Object> map = new HashMap<String,Object>();
		Integer id = getParaToInt(0);
		String sql = "select site_settle_state from t_dispatch where id="+id;
		String sql2 = "select dispatch_settle_state from t_dispatch where id="+id;
		//现场状态,flag_num状态：0，待审核；1，已审核；2，取消审核
		Integer flag_num = Db.queryInt(sql);
		//调度状态,flag_dispatch状态：0，待审核；1，已审核；2，取消审核
		Integer flag_dispatch = Db.queryInt(sql2);
		boolean flag = true;
		if(flag_num==1){
			flag = false;
		}
		map.put("flag_dispatch", flag_dispatch);
		map.put("flag", flag);
		map.put("flag_num", flag_num);
		renderJson(map);
	}
	
	/**
	 * @desc 查看修改历史记录
	 * @author xuhui
	 */
	public void revise(){
		String planNo = getPara();
		String sql = "select * from t_settle_apply where plan_no ='"+planNo+"'";
		List<Record> list = Db.find(sql);
		setAttr("list", list);		
		render("revise_detail.html");
	}
	
	
	/**
	 * @desc 历史记录明细
	 * @author xuhui
	 */
	public void reviseDetail(){
		String planNo = getPara("planNo");
		String ship_name = getPara("shipName");
		System.out.println(planNo + ship_name);
		String sql = "select * from t_settle_apply where plan_no ='"+planNo+"' and ship_name ='"+ship_name+"'";
		Record record = Db.findFirst(sql);
		System.out.println(sql);
		setAttr("record", record);	
		render("revise_detail_see.html");
	}
}
