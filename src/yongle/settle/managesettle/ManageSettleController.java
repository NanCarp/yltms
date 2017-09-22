package yongle.settle.managesettle;
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
 * @ClassName: SettleApplicationController.java
 * @Description: 结算审核管理-待结算审核表(经理)
 * @author: xuhui
 * @date: 2017年9月5日上午8:29:51
 * @version: 1.0 版本初成
 */
@Before(ManageInterceptor.class)
public class ManageSettleController extends Controller{

	/**
	 * @desc 展示清单页面
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
			Integer bursar_settle_state = getParaToInt("bursar_settle_state");
	    	Integer manager_settle_state = getParaToInt("manager_settle_state");
			
	        Integer pageindex = 0; // 页码
	        Integer pagelimit = getParaToInt("limit")==null? 12 :getParaToInt("limit"); // 每页数据条数
	        Integer offset = getParaToInt("offset")==null?0:getParaToInt("offset");
	        if(offset!=0){
	            pageindex = offset/pagelimit;
	        }
	        pageindex += 1;
	        Page<Record> page = ManageSettleService.getJson(pageindex, pagelimit,plan_no
	        		,consignor,bursar_settle_state,manager_settle_state);
	        Map<String, Object> map = new HashMap<String,Object>();
	        map.put("total", page.getTotalRow());
	        map.put("rows", page.getList());
	        renderJson(map);
	    }
	
	/**
	 * @desc 明细数据
	 * @author xuhui
	 */
	public void getDetail(){
		Integer id = getParaToInt(0);
		List<Record> list = ManageSettleService.getDetail(id);
		setAttr("list", list);
		render("settle_detail.html");	
	}
	
	/**
	 * @desc 判断是否可以更改复核状态
	 * @author xuhui
	 */
	public void iscancel(){
		//返回结果map
		Map<String,Object> map =  new HashMap<String,Object>();
		//判断是否可以更改数据
		boolean flag = true;
		Integer id = getParaToInt(0);
		
		String sql ="select bursar_settle_state,manager_settle_state from t_dispatch where id="+id;
		//审核结算表（复核）审核状态，0，待审核，1.已审核，2，取消审核
		Integer state = Db.findFirst(sql).getInt("bursar_settle_state");
		//获取现场当前审核状态
		Integer manageState = Db.findFirst(sql).getInt("manager_settle_state");
		if(manageState==1||manageState==3){
			flag = false;
		}
		map.put("flag", flag);
		map.put("state", state);
		renderJson(map);
	}
	
	/**
	 * @desc 复核结算表
	 * @author xuhui
	 */
	public void bursarCheckOut(){
		Map<String,Object> map = new HashMap<String,Object>();
		Integer id = getParaToInt(0);
		//判断是否更改成功
		boolean flag = false;
		String tips ="";
		String sql = "select bursar_settle_state from t_dispatch where id="+id;
		Record record = new Record();
		record.set("id", id);
		//flag_num状态：0，待审核；1，已审核；2，取消审核
		Integer flag_num = Db.queryInt(sql);
		if(flag_num==0||flag_num==2){
			record.set("bursar_settle_state", 1);
			tips="审核成功";
		}else{
			record.set("bursar_settle_state", 2);
			tips="完成取消审核";
		}
		flag = Db.update("t_dispatch", record);
		map.put("tips",tips);
		map.put("flag", flag);
		renderJson(map);
	}
	
	/**
	 * @desc 审核操作
	 * @author xuhui
	 */
	public void manageCheck(){
		boolean flag = false;
		Map<String,Object> map = new HashMap<String, Object>();
		Integer id = getParaToInt(0);
		String sql = "select manager_settle_state from t_dispatch where id="+id;
		Integer manager_settle_state = Db.findFirst(sql).getInt("manager_settle_state");
		//返回字段
		String tips = "";
		Record record = new Record();
		record.set("id", id);
		//manager_settle_state,审核结算表（经理复核）审核状态，0，待审核，1.已审核，2，取消审核,3.驳回
		if(manager_settle_state!=3){
			if(manager_settle_state==0||manager_settle_state==2){
				record.set("manager_settle_state", 1);
				flag = Db.update("t_dispatch", record);
				tips = "审核成功";
				ManageSettleService.buildShipAndCustomer(flag, id);
			}
			if(manager_settle_state==1){
				//record.set("manager_settle_state", 2);
				//flag = Db.update("t_dispatch", record);
				tips = "该计划号已通过审核";
			}
		}else{
			tips = "已被驳回不可操作";
		}
		map.put("flag", flag);
		map.put("tips", tips);
		renderJson(map);
	}
	
	/**
	 * @desc 驳回操作
	 * @author xuhui
	 */
	public void reject(){
		Map<String,Object> map = new HashMap<>();
		//判断是否驳回成功
		boolean flag = false;
		String tips = "驳回失败";
		Integer id = getParaToInt(0);
		String sql = "select manager_settle_state from t_dispatch where id="+id;
		Integer manager_settle_state = Db.findFirst(sql).getInt("manager_settle_state");
		if(manager_settle_state==1){
			tips = "已通过审核，驳回失败";
		}else{
			String sqlpara = "update t_dispatch set manager_settle_state = 3 where id="+id;
			if(Db.update(sqlpara)>0){
				flag = true;
				tips = "驳回成功";
			}
		}
		map.put("flag", flag);
		map.put("tips", tips);
		renderJson(map);
	}
	
	/**
	 * @desc 判断经理是否可以审核数据
	 * @author xuhui
	 */
	public void ischange(){
		Map<String,Object> map = new HashMap<String,Object>();
		String tips ="操作失败";
		//判断是否可以删除
		boolean flag = false;
		Integer id = getParaToInt(0);
		String sql = "select bursar_settle_state,manager_settle_state from t_dispatch where id="+id;
		Integer state_num = Db.findFirst(sql).getInt("manager_settle_state");
		Integer bursar_settle_state = Db.findFirst(sql).getInt("bursar_settle_state");
		if(bursar_settle_state==0||bursar_settle_state==2){
			tips = "尚未复核，无法操作";
		}else{
			if(state_num!=3){
				flag = true;
			}
		}
		map.put("tips", tips);
		map.put("num", state_num);
		map.put("flag", flag);
		renderJson(map);
	}
	
}
