package yongle.settle.customerfreight;

import java.math.BigDecimal;
import java.util.List;

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

    /** 
    * @Title: getList 
    * @Description: 打印列表
    * @param plan_no
    * @return List<Record>
    * @author 
    */
    public static List<Record> getList(String plan_no) {
        return getDataPage(1, 1000, plan_no).getList();
    }

    /** 
    * @Title: calculateTotal 
    * @Description: 应收金额合计
    * @param recordList
    * @return BigDecimal
    * @author 
    */
    public static BigDecimal calculateTotal(List<Record> recordList) {
        BigDecimal total = new BigDecimal(0);
        for (Record r : recordList) {
            BigDecimal t = r.getBigDecimal("payable_amount");
            
            total = total.add(t);
        }
        return total;
    }
}
