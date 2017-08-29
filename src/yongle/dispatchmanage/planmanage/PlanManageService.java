package yongle.dispatchmanage.planmanage;

import com.jfinal.plugin.activerecord.Db;
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
	 * String plan_no = getPara("plan_no");
		String goods_name = getPara("goods_name");
		String consignor = getPara("consignor");
		String plan_begintime = getPara("plan_begintime");
		String plan_endtime = getPara("plan_endtime");
	 * 
	 */
	public static Page<Record> getJson(Integer pageNumber,Integer pageSize,String plan_no
			,String goods_name,String consignor,String plan_begintime,String plan_endtime){
		String sql = " from t_dispatch where 1=1";
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
		System.out.println("select *" + sql);
		return Db.paginate(pageNumber, pageSize, "select *",sql);
	}
	
	
	
}

