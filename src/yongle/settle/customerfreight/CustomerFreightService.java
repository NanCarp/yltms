package yongle.settle.customerfreight;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

/**
 * @ClassName: CustomerFreightService.java
 * @Description:
 * @author: LiYu
 * @date: 2017年9月5日上午10:46:15
 * @version: 1.0 版本初成
 */
public class CustomerFreightService {

    /** 
    * @Title: getDataPages 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param pageindex
    * @param pagelimit
    * @param plan_no
    * @return Page<Record>
    * @author liyu
    */
    public static Page<Record> getDataPages(Integer pageindex, Integer pagelimit, String plan_no) {
        String select = " SELECT *,a.id ";
        String sqlExceptSelect = " FROM `t_customer_settle` AS a "
                + " LEFT JOIN t_dispatch_ship AS b "
                + " ON a.dispatch_ship_id = b.id "
                + " LEFT JOIN t_dispatch AS c "
                + " ON b.dispatch_id = c.id WHERE 1=1 ";
        
        if (plan_no != null && !"".equals(plan_no)) {
            sqlExceptSelect += " AND plan_no like '%"+ plan_no +"'";
        }
        
        return Db.paginate(pageindex, pagelimit, select, sqlExceptSelect);
    }

    /** 
    * @Title: getRecordById 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @return Record
    * @author liyu
    */
    public static Record getRecordById(Integer id) {
        String sql = " SELECT *,a.id "
                + " FROM `t_customer_settle` AS a "
                + " LEFT JOIN t_dispatch_ship AS b "
                + " ON a.dispatch_ship_id = b.id "
                + " LEFT JOIN t_dispatch AS c "
                + " ON b.dispatch_id = c.id "
                + " WHERE a.id = ? ";
        return Db.findFirst(sql, id);
    }

}
