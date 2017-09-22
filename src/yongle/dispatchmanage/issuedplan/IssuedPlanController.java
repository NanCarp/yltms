package yongle.dispatchmanage.issuedplan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import yongle.dispatchmanage.planmanage.PlanManageService;
/**
 * @ClassName: IssuedPlanController.java
 * @Description: 已下达计划
 * @author: xuhui
 * @date: 2017年9月19日下午4:15:05
 * @version: 1.0 版本初成
 */
public class IssuedPlanController extends Controller {

	/**
	 * @desc 展示清单页
	 * @author xuhui
	 */
	public void index(){
		render("issuedplan.html");
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
    	List<Record> dictionaryList = IssuedPlanService.getJson(pageindex, pagelimit,plan_no,goods_name,consignor,plan_begintime,plan_endtime).getList();
    	map.put("rows", dictionaryList);
    	map.put("total",IssuedPlanService.getJson(pageindex, pagelimit,plan_no,goods_name,consignor,plan_begintime,plan_endtime).getTotalRow());
    	renderJson(map);	
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
		render("issuedplan_detail_see.html");
	}

	/**
	 * @desc 判断是否已分配流向,已分配流向不可删除，待清空流向后方可删除
	 * @author xuhui
	 */
	public void Judgedelete(){
		Integer id = getParaToInt(0);
		String sql = "select * from t_dispatch_ship where dispatch_id ="+id;
		List<Record> listDetail = Db.find(sql);
		if(listDetail.size()!=0){
			renderJson(false);
		}else{
			renderJson(true);
		}
	}
}
