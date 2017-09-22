package yongle.dispatchmanage.planmanage;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import yongle.interceptor.ManageInterceptor;
import yongle.model.Dispatch;

/**
 * @ClassName: PlanManageController.java
 * @Description: 调度管理
 * @author: xuhui
 * @date: 2017年8月25日上午13:11:26
 * @version: 1.0 版本初成
 */
@Before(ManageInterceptor.class)
public class PlanManageController extends Controller {

	/**
	 * @desc 计划管理清单页
	 * @author xuhui
	 */
	public void index(){
		render("planmanage.html");
	}
	
	/**
	 * @desc 展示清单页数据
	 * @author xuhui
	 */
	public void getJson(){
		String plan_no = getPara("plan_no");
		String goods_name = getPara("goods_name");
		String consignor = getPara("consignor");
		String plan_begintime = getPara("plan_begintime");
		String plan_endtime = getPara("plan_endtime");
    	Integer	pageindex = 0;
    	Integer pagelimit = getParaToInt("limit")==null? 12 :getParaToInt("limit");
    	Integer offset = getParaToInt("offset")==null?0:getParaToInt("offset");
    	if(offset!=0){
    		pageindex = offset/pagelimit;
    	}
    	pageindex += 1;
    	Map<String, Object> map = new HashMap<String,Object>(); 	
    	List<Record> dictionaryList = PlanManageService.getJson(pageindex, pagelimit,plan_no,goods_name,consignor,plan_begintime,plan_endtime).getList();
    	map.put("rows", dictionaryList);
    	map.put("total",PlanManageService.getJson(pageindex, pagelimit,plan_no,goods_name,consignor,plan_begintime,plan_endtime).getTotalRow());
    	renderJson(map);	
	}
	
	/**
	 * @desc 新增数据
	 * @author xuhui
	 */
	public void getPlanManage(){
		Integer id = getParaToInt(0);
		if(id!=null){
			Record record = Db.findById("t_dispatch", id);
			setAttr("im", record);
		}
		//根据货物基础数据
		String goodsql = "select * from t_base_goods";
		List<Record> goodslist =  Db.find(goodsql);
		setAttr("goodslist", goodslist);
		render("planmanage_detail.html");
	}
	
	/**
	 * @desc 计划明细
	 * @author xuhui
	 */
	public void getPlanDetailSee(){
		Integer id = getParaToInt(0);
		String sql = "select * from t_dispatch_detail where plan_no_id ="+id;
		List<Record> listDetail = Db.find(sql);
		setAttr("planId", id);
		setAttr("listDetail", listDetail);
		render("planmanage_detail_see.html");
	}
	
	/**
	 * @desc 保存计划明细
	 * @author xuhui
	 */
	public void savePlanDetailSee(){
		//判断明细数据是否保存成功
		boolean flag = true;
		String PlanDetail = getPara("productList");
		Integer id = getParaToInt("id");
		flag = PlanManageService.savePlanDetailSee(id, PlanDetail);
		renderJson(flag);
	}
	
	
	/**
	 * @desc 审核计划单
	 * @author xuhui
	 */
	public void examine(){
		boolean result = false;
		Integer id = getParaToInt(0);
		Record record = new Record();
		 Map<String,Object> map = new HashMap<String,Object>();
		//是否产生后续业务,true已经产生，false未产生
		boolean flagnext = false;
		//判断是否有后续业务逻辑产生，判断‘调度交接表’是否审核，0:待审核 1:已审核 2:取消审核
		Integer dispatch_review = Db.findById("t_dispatch", id).getInt("dispatch_review");
		//判断调度交接明细表是否有数据产生
		String sql = "select * from t_dispatch_ship where dispatch_id = "+id;
		if(Db.findFirst(sql)!=null&&dispatch_review==1){
			flagnext = true;
		}
		if(!flagnext){
			record.set("id", id);
			//判断审核状态 0、待审核，1、已审核，2、取消审核
			Integer value = Db.findById("t_dispatch", id).getInt("examine_state");
			if(value==0||value==2){
				record.set("examine_state", 1);
			}else{
				record.set("examine_state", 2);
			}
			result = Db.update("t_dispatch", record);
		}
		map.put("flag", result);
		map.put("flagnext", flagnext);
		renderJson(map);
	}
	
	/**
	 * @desc 获取计划单审核状态
	 * @author xuhui
	 */
	public void getExamineState(){
		boolean flag = false;
		Integer id = getParaToInt(0);
		Integer value = Db.findById("t_dispatch", id).getInt("examine_state");
		//判断审核状态 0、待审核，1、已审核，2、取消审核
		if(value == 1){
			flag = true;
		}
		renderJson(flag);
	}
	
	/**
	 * @desc 保存新增以及修改计划管理
	 * @author xuhui
	 */
	public void savePlanManage(){
		//保存或者更新状态
		Boolean result = false;
		Dispatch record = getModel(Dispatch.class,"");
		Integer id = record.getId();
		String fixPlanNo = "";
		if(id!=null){
			result = record.update();
		}else{
			SimpleDateFormat sdfplan = new SimpleDateFormat("yyyyMMdd");
			Date date = new Date();
			String dateStr = sdfplan.format(date);
			String sql = "select Max(plan_no) from t_dispatch where plan_no like '"+dateStr+"%'";
			if(Db.queryStr(sql)!=null){
				Integer plan_no_pre =Integer.parseInt(Db.queryStr(sql).substring(9));
				String plan_no_fix =String.valueOf(plan_no_pre + 1001).substring(1);
				fixPlanNo = dateStr +plan_no_fix;
			}else{
				fixPlanNo = dateStr +"001";
			}
			record.set("plan_no", fixPlanNo);
			record.set("left_quantity", record.getNumber("total_quantity"));
			//保存时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date now = new Date();
			record.set("entry_time", sdf.format(now));
			Record admin = (Record) getSession().getAttribute("admin");
			String adminName = admin.getStr("account");		
			record.set("entry_man",adminName);
			result = record.save();
		}
		renderJson(result);
	}
	
	/**
	 * @desc 删除计划单号
	 * @author xuhui
	 */
	public void delete(){
		Integer id = getParaToInt(0);
		Map<String,Object> stateMap = new HashMap<String,Object>();
		//计划单号的审核状态
		boolean Plan_no_state = false;
		//判断审核状态 0、待审核，1、已审核，2、取消审核
		boolean delstate = Db.deleteById("t_dispatch", id);
		stateMap.put("delstate", delstate);
		stateMap.put("Plan_no_state", Plan_no_state);
		renderJson(stateMap);
	}
	
	/**
	 * @desc 根据产品名称得出运输定耗
	 * @author xuhui
	 */
	public void getFixedLoss(){
		String goods_name = getPara("goods_name");
		String sql = "select * from t_base_goods where goods_name = '"+goods_name+"'";
		BigDecimal fixed_loss = Db.findFirst(sql).getBigDecimal("fixed_loss");
		renderJson(fixed_loss);
	}
	
	
	/**
	 * @desc 判断用户是否可以下发
	 * @author xuhui
	 */
	public void isIssued(){
		//判断下发状态
		boolean flag = false;
		Integer id = getParaToInt();		
		String sql ="select plan_state from t_dispatch where id="+id;
		Integer state = Db.queryInt(sql);
		System.out.println(state);
		if(state==1){
			flag = true;
		}
		renderJson(flag);
	}
	
	/**
	 * @desc 更改下发状态
	 * @author xuhui
	 */
	public void thenIssued(){
		boolean result = false;
		Integer id = getParaToInt();
		String sql = "update t_dispatch set examine_state =1 where id="+id;
		Integer count = Db.update(sql);
		if(count!=0){
			result = true;
		}
		renderJson(result);
	}
	
	/**
	 * @desc 判断计划合计数量是否等于
	 * @author xuhui
	 */
	public void isAll(){
		BigDecimal bigde = new BigDecimal(0);
		Map<String,Object> map = new HashMap<String,Object>();
		Integer id = getParaToInt("id");
		String data = getPara("productList");
		List<JSONObject> jlist = (List<JSONObject>)JSONObject.parse(data);
		for(JSONObject jobj:jlist){
			BigDecimal num = jobj.getBigDecimal("b");
			bigde = bigde.add(num);
		}
		String sql = "select total_quantity from t_dispatch where id="+id;
		BigDecimal total_quantity = Db.queryBigDecimal(sql);
		Integer flag = total_quantity.compareTo(bigde);
		//flag结果是:-1 小于,0 等于,1 大于
		if(flag==0){
			//flag=0,合计数量已完全分配，可以更改总数量分配状态
			String sqlparam = "update t_dispatch set plan_state = 1 where id = "+id;
			Db.update(sqlparam);
		}else if(flag==1){
			String sqlparam = "update t_dispatch set plan_state = 0 where id = "+id;
			Db.update(sqlparam);
		}
		map.put("flag", flag);
		renderJson(map);
	}
}

