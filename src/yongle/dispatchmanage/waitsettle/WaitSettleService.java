package yongle.dispatchmanage.waitsettle;

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
		String sqlparam = "SELECT * ";
		/*String sql = "  from t_dispatch where document_status = 1"
					+" AND bursar_settle_state = 1"
					+" AND id NOT IN"
					+" (SELECT kk.id from"
					+" (SELECT DISTINCT(k.id)"
					+" from t_dispatch_ship d"
					+" LEFT JOIN t_dispatch_detail p ON d.dispatch_detail_id = p.id"
					+" LEFT JOIN t_dispatch k ON p.plan_no_id = k.id"
					+" where d.received_quantity IS NULL OR d.declare_date is NULL or d.unloaded_date is NULL ) kk"
					+" where kk.id is not NULL)";*/
		String sql = " from t_dispatch"
					+" where document_status = 1" 
					+" AND bursar_settle_state = 1"
					+" AND id NOT IN"
					+" (SELECT kk.id from"
					+" (SELECT DISTINCT(k.id)"
					+" from t_dispatch_ship d"
					+" LEFT JOIN t_dispatch_detail p ON d.dispatch_detail_id = p.id"
					+" LEFT JOIN t_dispatch k ON p.plan_no_id = k.id"
					+" where d.receipt_review=0) kk"
					+" where kk.id is not NULL)";		
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
