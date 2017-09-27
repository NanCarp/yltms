package yongle.insidejob.plandispatch;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class PlanService {
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
	        System.out.println(sql);
	        if (consignor != null && !"".equals(consignor)) {
	            sql += " AND consignor like '%"+ consignor +"'";
	        }
	        return Db.find(sql);
	    }

}
