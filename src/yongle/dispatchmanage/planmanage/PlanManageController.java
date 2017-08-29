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
		String sql = "select * from t_dispatch_detail where id ="+id;
		List<Record> listDetail = Db.find(sql);
		setAttr("listDetail", listDetail);
		render("planmanage_detail_see.html");
	}
	
	/**
	 * @desc 审核计划单
	 * @author xuhui
	 */
	public void examine(){
		Integer id = getParaToInt(0);
		Record record = new Record();
		record.set("id", id);
		record.set("examine_state", true);
		boolean result = Db.update("t_dispatch", record);
		renderJson(result);
	}
	
	/**
	 * @desc 获取计划单审核状态
	 * @author xuhui
	 */
	public void getExamineState(){
		Integer id = getParaToInt(0);
		boolean flag = Db.findById("t_dispatch", id).getBoolean("examine_state");
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
		if(id!=null){
			result = record.update();
		}else{
			record.set("plan_no", "Plan123456");
			//保存时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date now = new Date();
			record.set("entry_time", sdf.format(now));
			//保存录入人
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
		boolean Plan_no_state = true;
		Plan_no_state = Db.findById("t_dispatch", id).getBoolean("examine_state");
		if(!Plan_no_state){
			boolean delstate = Db.deleteById("t_dispatch", id);
			stateMap.put("delstate", delstate);
		}
		stateMap.put("Plan_no_state", Plan_no_state);
		renderJson(stateMap);
	}
}

