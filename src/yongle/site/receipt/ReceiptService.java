package yongle.site.receipt;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

/**
 * @ClassName: WaterwayFreightService.java
 * @Description: 回单录入
 * @author: LiYu
 * @date: 2017年9月1日上午9:10:16
 * @version: 1.0 版本初成
 */
public class ReceiptService {

    /** 
    * @Title: getDataPages 
    * @Description: 数据
    * @param pageindex
    * @param pagelimit
    * @param plan_no 计划号
    * @param ship_name 船名
    * @param start_date 起始日期
    * @param end_date 截止日期
    * @return Page<Record>
    * @author liyu
    */
    public static Page<Record> getDataPages(Integer pageindex, Integer pagelimit, String plan_no, String ship_name,
            String start_date, String end_date) {
        String select = " SELECT d.*,c.*,a.*,b.* ";
        String sqlExceptSelect = " FROM `t_dispatch_detail` AS a "
                + " LEFT JOIN t_dispatch_ship AS b "
                + " ON a.id = b.dispatch_detail_id "
                + " LEFT JOIN t_dispatch AS c "
                + " ON a.plan_no_id = c.id "
                + " LEFT JOIN t_base_customer AS d "
                + " ON a.consignee = d.customer_name "
                + " WHERE c.document_status = 1 ";
        
        if (plan_no != null && !"".equals(plan_no)) {
            sqlExceptSelect += " AND plan_no like '%"+ plan_no +"'";
        }
        
        if (ship_name != null && !"".equals(ship_name)) {
            sqlExceptSelect += " AND ship_name like '%"+ ship_name +"%'";
        }
        
        if (start_date != null && !"".equals(start_date)) {
            sqlExceptSelect += " AND delivery_date >= '" + start_date + "'";
        }
        
        if (end_date != null && !"".equals(end_date)) {
            sqlExceptSelect += " AND delivery_date <= '" + end_date + "'";
        }
        
        sqlExceptSelect += " ORDER BY plan_no DESC ";
        
        return Db.paginate(pageindex, pagelimit, select, sqlExceptSelect);
    }

    /** 
    * @Title: getRecordById 
    * @Description: 回单相关信息
    * @return Record
    * @author liyu
    */
    public static Record getRecordById(Integer id) {
        String sql = " SELECT d.*,c.*,a.*,b.* "
                + " FROM `t_dispatch_detail` AS a "
                + " LEFT JOIN t_dispatch_ship AS b "
                + " ON a.id = b.dispatch_detail_id "
                + " LEFT JOIN t_dispatch AS c "
                + " ON a.plan_no_id = c.id "
                + " LEFT JOIN t_base_customer AS d "
                + " ON a.consignee = d.customer_name "
                + " WHERE b.id = ? ";
        return Db.findFirst(sql, id);
    }

}
