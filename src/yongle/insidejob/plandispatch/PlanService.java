package yongle.insidejob.plandispatch;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

/**
 * @ClassName: PlanService.java
 * @Description: 计划调度信息
 * @author: liyu
 * @date: 2017年9月27日下午3:41:32
 * @version: 1.0 版本初成
 */
public class PlanService {
	  /** 
	* @Title: getRecordList 
	* @Description: 根据计划号查询计划调度信息
	* @param plan_no
	* @param consignor
	* @return List<Record>
	* @author liyu
	*/
	public static List<Record> getRecordList(String plan_no, String consignor) {
	      String sql = " SELECT b.*,c.*,a.*  "
	              + " FROM `t_dispatch_detail` AS a "
	              + " LEFT JOIN t_dispatch_ship AS b "
	              + " ON a.id = b.dispatch_detail_id "
	              + " LEFT JOIN t_dispatch AS c "
	              + " ON a.plan_no_id = c.id "
	              + " WHERE c.document_status = 1";
	        
	        if (plan_no != null && !"".equals(plan_no)) {
	            sql += " AND plan_no = '"+ plan_no +"'";
	        }
	        if (consignor != null && !"".equals(consignor)) {
	            sql += " AND consignor like '%"+ consignor +"'";
	        }
	        return Db.find(sql);
	    }

}
