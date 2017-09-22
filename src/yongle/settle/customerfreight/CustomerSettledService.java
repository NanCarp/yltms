package yongle.settle.customerfreight;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

/**
 * @ClassName: CustomerSettledController.java
 * @Description:
 * @author: xuhui
 * @date: 2017年9月5日上午10:45:55
 * @version: 1.0 版本初成
 */
public class CustomerSettledService {

	public static Page<Record> getDataPage(Integer pageindex, Integer pagelimit, String plan_no){
    	String select = " select s.*,k.*,e.*,d.*,s.id";
        String sqlExceptSelect = " from t_customer_settle s LEFT JOIN t_dispatch_ship d ON s.dispatch_ship_id = d.id"
        						+" LEFT JOIN t_dispatch_detail e ON e.id = d.dispatch_detail_id" 
        						+" LEFT JOIN t_dispatch k ON k.id = e.plan_no_id where s.payable_amount is not null";     
        if (plan_no != null && !"".equals(plan_no)) {
            sqlExceptSelect += " AND k.plan_no like '%"+ plan_no +"'";
        }       
        sqlExceptSelect += " ORDER BY s.id DESC";
        return Db.paginate(pageindex, pagelimit, select, sqlExceptSelect);		   
	}
}
