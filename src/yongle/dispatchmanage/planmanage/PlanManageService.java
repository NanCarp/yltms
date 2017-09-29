package yongle.dispatchmanage.planmanage;

import java.sql.SQLException;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

/**
 * @ClassName: PlanManageService.java
 * @Description: 调度管理
 * @author: xuhui
 * @date: 2017年8月25日上午16:12:26
 * @version: 1.0 版本初成
 */
public class PlanManageService{
	/**
	 * @author xuhui
	 * @desc 展示清单页数据
	 */
	public static Page<Record> getJson(Integer pageNumber,Integer pageSize,String plan_no
			,String goods_name,String consignor,String plan_begintime,String plan_endtime){
		String sql = " from t_dispatch where examine_state=0";
		if(plan_no!=null&&plan_no!=""){
			sql +=" and plan_no like '%"+plan_no+"%'";
		}
		if(goods_name!=null&&goods_name!=""){
			sql +=" and goods_name like '%"+goods_name+"%'";
		}
		if(consignor!=null&&consignor!=""){
			sql +=" and consignor like '%"+consignor+"%'";
		}
		if(plan_begintime!=null&&plan_begintime!=""){
			if(plan_endtime!=null&&plan_endtime!=""){
				sql +=" and '"+plan_begintime+"'<= entry_time and entry_time <= '"+plan_endtime+"'";
			}else{
				sql +=" and entry_time = '"+plan_begintime+"'";
			}
		}
		if(plan_endtime!=null&&plan_endtime!=""){
			if(plan_begintime!=null&&plan_begintime!=""){
				//计划开始时间不为空不处理；
			}else{
				sql +=" and entry_time = '"+plan_endtime+"'";
			}
		}
		sql +=" order by id desc";
		return Db.paginate(pageNumber, pageSize, "select *",sql);
	}
	
	/**
	 * @author xuhui
	 * @desc 保存明细
	 * @param id
	 * @param PlanDetail
	 * @return
	 */
	public static boolean savePlanDetailSee(Integer id,String PlanDetail){
		boolean flag = Db.tx(new IAtom() {			
			@Override
			public boolean run() throws SQLException {
				//判断数据是否保存成功
				boolean flagData = true;
				//删除该计划号下所有明细，重新保存数据
				String sql = "delete from t_dispatch_detail where plan_no_id = "+id;
				Integer num = Db.update(sql);
				List<JSONObject> planDetailList = (List<JSONObject>) JSONObject.parse(PlanDetail);
				for(JSONObject plandetail:planDetailList){
					Record planDetailRecord = new Record();
					planDetailRecord.set("plan_no_id", id);//计划号
					planDetailRecord.set("flow", plandetail.get("a"));//流向
					planDetailRecord.set("planned_qty", plandetail.get("b"));//计划数量
					planDetailRecord.set("destination_port", plandetail.get("c"));//目的港
					planDetailRecord.set("consignee", plandetail.get("d"));//收货单位
					planDetailRecord.set("guide_price", plandetail.get("e"));//指导价
					planDetailRecord.set("settle_price", plandetail.get("f"));//结算价
					planDetailRecord.set("remark", plandetail.get("g"));//备注
					if(!Db.save("t_dispatch_detail", planDetailRecord)){
						flagData = false;
						break;
					}
				}
				return flagData;
			}
		});
		return flag;
	}
}

