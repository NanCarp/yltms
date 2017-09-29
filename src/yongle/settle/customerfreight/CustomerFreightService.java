package yongle.settle.customerfreight;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

/**
 * @ClassName: CustomerFreightService.java
 * @Description: 客户结算管理-待结算
 * @author: LiYu
 * @date: 2017年9月5日上午10:46:15
 * @version: 1.0 版本初成
 */
public class CustomerFreightService {

    /** 
    * @Title: getDataPages 
    * @Description: 数据
    * @param pageindex
    * @param pagelimit
    * @param plan_no
    * @return Page<Record>
    * @author liyu
    */
    public static Page<Record> getDataPages(Integer pageindex, Integer pagelimit, String plan_no) {
    	String select = " select s.*,k.*,e.*,d.*,s.id";
        String sqlExceptSelect = " from t_customer_settle s LEFT JOIN t_dispatch_ship d ON s.dispatch_ship_id = d.id"
        						+" LEFT JOIN t_dispatch_detail e ON e.id = d.dispatch_detail_id" 
        						+" LEFT JOIN t_dispatch k ON k.id = e.plan_no_id where s.payable_amount is null";     
        if (plan_no != null && !"".equals(plan_no)) {
            sqlExceptSelect += " AND k.plan_no like '%"+ plan_no +"'";
        }       
        sqlExceptSelect += " ORDER BY s.id DESC";
        return Db.paginate(pageindex, pagelimit, select, sqlExceptSelect);
    }

    /** 
    * @Title: getRecordById 
    * @Description: 根据船 id 获取相关数据
    * @return Record
    * @author liyu
    */
    public static Record getRecordById(Integer id) {
        String sql = "select d.*,e.*,k.*,s.*,s.id"
        		+" from t_customer_settle s LEFT JOIN t_dispatch_ship d ON s.dispatch_ship_id = d.id"
				+" LEFT JOIN t_dispatch_detail e ON e.id = d.dispatch_detail_id" 
				+" LEFT JOIN t_dispatch k ON k.id = e.plan_no_id  where s.id="+id;
        return Db.findFirst(sql);
    }

}
