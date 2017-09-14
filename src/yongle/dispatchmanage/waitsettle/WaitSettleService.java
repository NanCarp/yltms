package yongle.dispatchmanage.waitsettle;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

/**
 * @ClassName: WaitSettleService.java
 * @Description: 待结算审核表
 * @author: xuhui
 * @date: 2017年8月29日上午11:01:26
 * @version: 1.0 版本初成
 */
public class WaitSettleService {

	/**
	 * @desc 生成待结算审核表
	 * 条件：水路货运单的报港日期、收货数量、卸空日期不为空，即表示可以生成待结算审核表
	 * 该字段来自t_dispatch_ship
	 * @author xuhui
	 */
	public static Page<Record> getJson(Integer pageNumber,Integer pageSize,String plan_no
										,String consignor,Integer dispatch_settle_state){
		
		//待审核计划号获取
		String sqlparam = "SELECT"
						+" d.id,d.plan_no,d.examine_state,d.consignor,d.goods_name"
						+ ",d.seagoing_vessel_name,d.delivery_dock,d.dispatch_settle_state"
						+ ",d.manager_settle_state,d.dispatcher";
		String sql = " from"
					+" t_dispatch d"
					+" where d.examine_state = 1"  
					+" AND id IN"
					+" (SELECT a.dispatch_id" 
					+" FROM" 
					+" (SELECT DISTINCT m.dispatch_id" 
					+" FROM t_dispatch_ship m) a"
					+" WHERE a.dispatch_id NOT IN"
					+" (SELECT k.dispatch_id"
					+" FROM"
					+" (SELECT" 
					+" s.id,s.dispatch_id,s.ship_name,s.declare_date,s.received_quantity,s.unloaded_date"
					+" from" 
					+" t_dispatch_ship s"
					+" where" 
					+" s.received_quantity IS NULL OR s.declare_date is NULL or s.unloaded_date is NULL"
					+" )k))";
		if(plan_no!=null&&plan_no!=""){
			sql +=" and plan_no like '%"+plan_no+"%'";
		}
		if(consignor!=null&&consignor!=""){
			sql +=" and consignor like '%"+consignor+"%'";
		}
		if(dispatch_settle_state!=null){
			sql +=" and dispatch_settle_state ="+dispatch_settle_state;
		}
		sql +=" order by id desc";
		return Db.paginate(pageNumber, pageSize,sqlparam,sql);
	}
}
