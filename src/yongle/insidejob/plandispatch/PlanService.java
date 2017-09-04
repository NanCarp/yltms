package yongle.insidejob.plandispatch;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class PlanService {
	  public static List<Record> getRoleList(String plan_no, String consignor) {
	      String sql = " SELECT *  FROM `t_dispatch` AS a "
	                + " RIGHT JOIN t_dispatch_ship AS b "
	                + " ON a.id = b.dispatch_id "
	                + " WHERE a.dispatch_issue = 1 "
	                + " AND plan_no = " + plan_no;
	        
	        /*if (plan_no != null && !"".equals(plan_no)) {
	            sql += " AND plan_no = '"+ plan_no +"'";
	        }*/
	        
	        if (consignor != null && !"".equals(consignor)) {
	            sql += " AND consignor like '%"+ consignor +"'";
	        }
	        return Db.find(sql);
	    }

}
