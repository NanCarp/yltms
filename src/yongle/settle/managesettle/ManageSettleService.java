package yongle.settle.managesettle;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

/**
 * @ClassName: SettleApplicationController.java
 * @Description: 结算审核表-结算审核表(经理)
 * @author: xuhui
 * @date: 2017年9月5日上午8:29:51
 * @version: 1.0 版本初成
 */
public class ManageSettleService {

	/**
	 * @desc 生成待结算审核表
	 * 条件：水路货运单的报港日期、收货数量、卸空日期不为空，即表示可以生成待结算审核表
	 * 该字段来自t_dispatch_ship
	 * @author xuhui
	 */
	public static Page<Record> getJson(Integer pageNumber,Integer pageSize,String plan_no
										,String consignor,Integer bursar_settle_state,Integer manager_settle_state){
		
		//待审核计划号获取
		String sqlparam = "SELECT * ";
		/*String sql = " from t_dispatch where document_status = 1"
					+" AND bursar_settle_state = 0"
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
				+" AND bursar_settle_state = 0"
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
		if(bursar_settle_state!=null){
			sql +=" and bursar_settle_state ="+bursar_settle_state;
		}
		if(manager_settle_state!=null){
			sql +=" and manager_settle_state ="+manager_settle_state;
		}
		sql +=" order by id desc";
		return Db.paginate(pageNumber, pageSize, sqlparam,sql);
	} 
	
	/**
	 * @desc 获取计划单号下所有船信息
	 * @author xuhui
	 */
	public static List<Record> getDetail(Integer id){
		String sql = " SELECT d.*,p.*,k.*"
					+" from t_dispatch_ship d" 
					+" LEFT JOIN t_dispatch_detail p ON d.dispatch_detail_id = p.id" 
					+" LEFT JOIN t_dispatch k ON p.plan_no_id = k.id"
					+" where p.plan_no_id = "+id;
		return Db.find(sql);
	}
	
	/**
	 * @desc 经理完成审核生成船舶运费结算清单 以及客户运费结算清单
	 * @author xuhui
	 */
	public static boolean buildShipAndCustomer(boolean flag,Integer id){
		boolean result = Db.tx(new IAtom() {	
			@Override
			public boolean run() throws SQLException {
				boolean k1 = false,k2 = false;
				if(flag){
					//根据计划号id 获取相应的船只(t_dispatch_ship)中的id
					String sqls = "SELECT s.* from t_dispatch t LEFT JOIN t_dispatch_detail d ON t.id = d.plan_no_id LEFT JOIN "
							+ " t_dispatch_ship s ON d.id = s.dispatch_detail_id where t.id ="+id;
					//根据计划号id获取该计划号全部信息
					String sqld = "select goods_name from t_dispatch where id="+id;
					List<Record> list = Db.find(sqls);
					String goods_name = Db.queryStr(sqld);//货物名称
					//获取该计划号下的运输定耗值
					String sqlFroFix = "select transport_consumption,Excess_deduction_price from t_dispatch where id ="+id;
					Record recFor = Db.findFirst(sqlFroFix);
					BigDecimal transportConsumption = recFor.getBigDecimal("transport_consumption");//运输定耗值，千分比
					BigDecimal deduct_price = recFor.getBigDecimal("Excess_deduction_price");//超耗扣价
					//根据数据字典的对应货物名称获取损耗信息
					String sqlbasegoods = "select * from t_base_goods where goods_name = '"+goods_name+"'";
					Record r1 = Db.findFirst(sqlbasegoods);
					//BigDecimal fix_loss_rate = r1.getBigDecimal("fixed_loss");//获取运输定耗值
					//BigDecimal deduct_price = r1.getBigDecimal("deduct_price");//获取超耗扣价
					for(Record record:list){
						Integer dispatch_ship_id = record.getInt("id");//根据id获取
						String sql1 = "select * from t_dispatch_ship where id="+dispatch_ship_id;
						Record r3 = Db.findFirst(sql1);
						BigDecimal received_quantity = r3.getBigDecimal("received_quantity");//收货数量
						BigDecimal delivery_quantity = r3.getBigDecimal("delivery_quantity");//发货数量
						BigDecimal fix_loss_rate = delivery_quantity.multiply(transportConsumption).divide(new BigDecimal(1000));
						//需要保存的数据
						Record ship = new Record();
						Record customer = new Record();
						ship.set("dispatch_ship_id", dispatch_ship_id);
						customer.set("dispatch_ship_id", dispatch_ship_id);
						customer.set("deduct_price", deduct_price);
		 				BigDecimal loss = delivery_quantity.subtract(received_quantity);//损耗
		 				ship.set("loss", loss);
		 				customer.set("loss", loss);
						BigDecimal fixed_loss = delivery_quantity.multiply(transportConsumption).divide(new BigDecimal(1000));//定耗
						ship.set("fixed_loss", fixed_loss);
						customer.set("fixed_loss", fixed_loss);
						BigDecimal exceed_loss = loss.subtract(fixed_loss); //超耗
						if(exceed_loss.compareTo(new BigDecimal(0))!=-1){// -1小于 0等于 1大于
							ship.set("exceed_loss", exceed_loss);
							customer.set("exceed_loss", exceed_loss);
							BigDecimal deduct_money = deduct_price.multiply(exceed_loss);//扣款
							ship.set("deduct_money", deduct_money);
							customer.set("deduct_money", deduct_money);
						}
						k1 = Db.save("t_ship_settle", ship);
						k2 = Db.save("t_customer_settle",customer);
					}
				}
				return k1&&k2;
			}
		});
		return result;
	}
	
}
