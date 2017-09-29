package yongle.dispatchmanage.issuedplan;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Record;

import yongle.interceptor.ManageInterceptor;
/**
 * @ClassName: IssuedPlanController.java
 * @Description: 已下达计划
 * @author: xuhui
 * @date: 2017年9月19日下午4:15:05
 * @version: 1.0 版本初成
 */
@Before(ManageInterceptor.class)
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
		//返回值
		boolean flag = false;
		Integer id = getParaToInt(0);
		String sql = "SELECT e.flow_state from t_dispatch d LEFT JOIN t_dispatch_detail e ON d.id = e.plan_no_id where d.id ="+id;
		List<Record> listDetail = Db.find(sql);
		if(listDetail!=null){
			for(Record r :listDetail){
				if(r.getInt("flow_state")==1){
					flag = true;
					break;
				};
			}
		}
		renderJson(flag);
	}
	
	/**
	 * @desc 判断该id下是否有该流向，有则修改信息，没有则新增
	 * @author xuhui
	 */
	public void savePlanDetailSee(){
		boolean flag = Db.tx(new IAtom() {
			@Override
			public boolean run() throws SQLException {
				//判断是否执行成功
				boolean result = false;
				String PlanId = getPara("id");
				//一旦产生流向更改，即改变单据状态
				String sqlForPlanState = "UPDATE t_dispatch SET document_status = 0 WHERE id = "+PlanId;
				Db.update(sqlForPlanState);
				String PlanDetail = getPara("productList");
				List<JSONObject> jsonList = (List<JSONObject>) JSONObject.parse(PlanDetail);
				String sql = "select id from t_dispatch_detail where plan_no_id = "+PlanId;
				List<Record> list = Db.find(sql);
				for(Record record:list){
					//判断是否需要删除
					boolean flag = true;
					for(JSONObject jbg :jsonList){
						Integer id = jbg.getInteger("h");
						if(id == record.get("id")){
							flag = false;
							break;
						};
					}
					if(flag){
						//删除该流向下的船信息
						String sqldel = "delete from t_dispatch_ship where dispatch_detail_id ="+record.getInt("id");
						Db.update(sqldel);
						Db.delete("t_dispatch_detail", record);
					}
				}
				for(JSONObject jbg :jsonList){
					Integer id = jbg.getInteger("h");
					if(id!=null){
						Record r = Db.findById("t_dispatch_detail", id);
						if(r!=null){
							//判断流向
							String flowFromR = r.getStr("flow");
							String flowFromJ = jbg.getString("a");
							Record rec = new Record();
							rec.set("plan_no_id", PlanId);
							if(flowFromR.equals(flowFromJ)){
								rec.set("id", id);
								rec.set("planned_qty",jbg.getInteger("b"));
								rec.set("destination_port",jbg.getString("c"));
								rec.set("consignee",jbg.getString("d"));
								rec.set("guide_price",jbg.getBigDecimal("e"));
								rec.set("settle_price",jbg.getBigDecimal("f"));
								rec.set("remark",jbg.getString("g"));
								rec.set("flow_state", 0);
								result= Db.update("t_dispatch_detail", rec);
							}else{
								rec.set("flow",jbg.getString("a"));
								rec.set("planned_qty",jbg.getString("b"));
								rec.set("destination_port",jbg.getString("c"));
								rec.set("consignee",jbg.getString("d"));
								rec.set("guide_price",jbg.getBigDecimal("e"));
								rec.set("settle_price",jbg.getBigDecimal("f"));
								rec.set("remark",jbg.getString("g"));
								//流向名称改变，变成新的流向
								Db.deleteById("t_dispatch_detail", id);
								String sqldel = "delete from t_dispatch_ship where dispatch_detail_id ="+id;
								Db.update(sqldel);
								result = Db.save("t_dispatch_detail", rec);
							}	
						}else{
							Record rec = new Record();
							rec.set("plan_no_id", PlanId);
							rec.set("flow",jbg.getString("a"));
							rec.set("planned_qty",jbg.getString("b"));
							rec.set("destination_port",jbg.getString("c"));
							rec.set("consignee",jbg.getString("d"));
							rec.set("guide_price",jbg.getBigDecimal("e"));
							rec.set("settle_price",jbg.getBigDecimal("f"));
							rec.set("remark",jbg.getString("g"));
							result = Db.save("t_dispatch_detail", rec);
						}
					}else{
						Record rec = new Record();
						rec.set("plan_no_id", PlanId);
						rec.set("flow",jbg.getString("a"));
						rec.set("planned_qty",jbg.getString("b"));
						rec.set("destination_port",jbg.getString("c"));
						rec.set("consignee",jbg.getString("d"));
						rec.set("guide_price",jbg.getBigDecimal("e"));
						rec.set("settle_price",jbg.getBigDecimal("f"));
						rec.set("remark",jbg.getString("g"));
						result = Db.save("t_dispatch_detail", rec);
					}	
				}
				return result;
			}
		});
		renderJson(flag);
	}
}
