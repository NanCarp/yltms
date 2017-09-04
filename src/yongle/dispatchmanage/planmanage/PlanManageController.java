package yongle.dispatchmanage.planmanage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import yongle.model.Dispatch;

/**
 * @ClassName: PlanManageController.java
 * @Description: 调度管理
 * @author: xuhui
 * @date: 2017年8月25日上午13:11:26
 * @version: 1.0 版本初成
 */
public class PlanManageController extends Controller {

	/**
	 * @desc 计划管理清单页
	 * @author xuhui
	 */
	public void index(){
		render("planmanage.html");
	}
	
	/**
	 * @desc 展示清单页数据,同时加载成品和半成品
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
		System.out.println(listDetail);
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
		Integer id = getParaToInt(0);
		Record record = new Record();
		record.set("id", id);
		//判断审核状态 0、待审核，1、已审核，2、取消审核
		Integer value = Db.findById("t_dispatch", id).getInt("examine_state");
		if(value==0||value==2){
			record.set("examine_state", 1);
		}else{
			record.set("examine_state", 2);
		}
		boolean result = Db.update("t_dispatch", record);
		renderJson(result);
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
				System.out.println("plan_no_str:"+plan_no_pre);
				String plan_no_fix =String.valueOf(plan_no_pre + 1001).substring(1);
				fixPlanNo = dateStr +plan_no_fix;
			}else{
				fixPlanNo = dateStr +"001";
			}
			System.out.println("fixPlanNo:"+fixPlanNo);
			record.set("plan_no", fixPlanNo);
			record.set("left_quantity", record.getNumber("total_quantity"));
			//保存时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date now = new Date();
			record.set("entry_time", sdf.format(now));
			//保存录入人
			/*
			Record admin = (Record) getSession().getAttribute("admin");
			String adminName = admin.getStr("account");
			*/
			record.set("entry_man", "admin");
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
		Integer value = Db.findById("t_dispatch", id).getInt("examine_state");
		//判断审核状态 0、待审核，1、已审核，2、取消审核
		if(value == 1){
			Plan_no_state = true;
		}
		if(!Plan_no_state){
			boolean delstate = Db.deleteById("t_dispatch", id);
			stateMap.put("delstate", delstate);
		}
		stateMap.put("Plan_no_state", Plan_no_state);
		renderJson(stateMap);
	}
}

