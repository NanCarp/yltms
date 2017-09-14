package yongle.settle.shipfreight;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

/**
 * @ClassName: ShipFreightService.java
 * @Description: 结算审核表-船舶运费结算清单
 * @author: xuhui
 * @date: 2017年9月6日上午10:55:00
 * @version: 1.0 版本初成
 */
public class ShipFreightService {

	/**
	 * @desc 结算审核表清单页展示
	 * @author xuhui
	 */
	public static Page<Record> getDataPages(Integer pageNumber,Integer pageSize,String plan_no){
	    String select = " SELECT *,a.id ";
        String sqlExceptSelect = " FROM `t_ship_settle` AS a "
                + " LEFT JOIN t_dispatch_ship AS b "
                + " ON a.dispatch_ship_id = b.id "
                + " LEFT JOIN t_dispatch AS c "
                + " ON b.dispatch_id = c.id WHERE 1=1 ";
        if(plan_no!=null&&plan_no!=""){
        	sqlExceptSelect +=" and plan_no like '%"+plan_no+"%'";
        } 
        sqlExceptSelect +=" order by a.id desc";
       return Db.paginate(pageNumber, pageSize, select,sqlExceptSelect);
	} 
		
	  /** 
	    * @Title: getRecordById 
	    * @Description: 获取单条信息
	    * @return Record
	    * @author xuhui
	    */
	    public static Record getRecordById(Integer id) {
	        String sql = " SELECT *,a.id "
	                + " FROM `t_ship_settle` AS a "
	                + " LEFT JOIN t_dispatch_ship AS b "
	                + " ON a.dispatch_ship_id = b.id "
	                + " LEFT JOIN t_dispatch AS c "
	                + " ON b.dispatch_id = c.id "
	                + " WHERE a.id = ? ";
	        return Db.findFirst(sql, id);
	    }
    
}
