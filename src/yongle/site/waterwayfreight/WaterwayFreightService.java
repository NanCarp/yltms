package yongle.site.waterwayfreight;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

/**
 * @ClassName: WaterwayFreightService.java
 * @Description:
 * @author: LiYu
 * @date: 2017年9月1日上午9:10:16
 * @version: 1.0 版本初成
 */
public class WaterwayFreightService {

    /** 
    * @Title: getDataPages 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param pageindex
    * @param pagelimit
    * @param plan_no
    * @param ship_name
    * @param start_date
    * @param end_date
    * @return Page<Record>
    * @author liyu
    */
    public static Page<Record> getDataPages(Integer pageindex, Integer pagelimit, String plan_no, String ship_name,
            String start_date, String end_date) {
        String select = " SELECT c.*,a.*,b.* ";
        String sqlExceptSelect = " FROM `t_dispatch` AS a "
                + " RIGHT JOIN t_dispatch_ship AS b "
                + " ON a.id = b.dispatch_id "
                + " LEFT JOIN t_dispatch_detail AS c "
                + " ON b.dispatch_detail_id = c.id "
                + " WHERE examine_state = 1 "
                + " AND dispatch_review =1 "
                + " AND dispatch_issue =1 ";
        
        if (plan_no != null && !"".equals(plan_no)) {
            sqlExceptSelect += " AND plan_no like '%"+ plan_no +"'";
        }
        
        if (ship_name != null && !"".equals(ship_name)) {
            sqlExceptSelect += " AND ship_name like '%"+ ship_name +"%'";
        }
        
        if (start_date != null && !"".equals(start_date)) {
            sqlExceptSelect += " AND estimated_arrvial_date > '" + start_date + "'";
        }
        
        if (end_date != null && !"".equals(end_date)) {
            sqlExceptSelect += " AND estimated_arrvial_date < '" + end_date + "'";
        }
        
        sqlExceptSelect += " ORDER BY plan_no DESC ";
        
        return Db.paginate(pageindex, pagelimit, select, sqlExceptSelect);
    }

    /** 
    * @Title: getRecordById 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @return Record
    * @author liyu
    */
    public static Record getRecordById(Integer id) {
        String sql = "SELECT b.*,c.*,a.* "
                + " FROM `t_dispatch_ship` AS a "
                + " LEFT JOIN t_dispatch AS b "
                + " ON a.dispatch_id = b.id "
                + " LEFT JOIN t_dispatch_detail AS c "
                + " ON a.dispatch_detail_id = c.id "
                + " WHERE a.id = ? ";
        return Db.findFirst(sql, id);
    }

}
