package yongle.site.waterwayfreight;

import java.sql.SQLException;
import java.util.Date;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
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
    * @Description: 页面数据
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
                + " WHERE c.document_status = 1 "
                + " AND a.flow_state = 1 ";
        
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
    * @Description: 查询船相关信息
    * @param id 船id
    * @return Record
    * @author 
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

    /** 
    * @Title: createNotice 
    * @Description: 创建运单预付款提醒
    * @param id 船id
    * @return Boolean
    * @author 
    */
    public static Boolean createNotice(Integer id, Record user) {
        boolean result = Db.tx(new IAtom() {
            @Override
            public boolean run() throws SQLException {
                Record r = Db.find(" SELECT a.flow,b.ship_name,b.ship_owner_name,b.ship_owner_phone, "
                        + " b.pre_refuel,b.site_refuel,b.site_pay,b.prepay,b.refuel_type,b.id AS dispatch_ship_id, "
                        + " c.plan_no "
                        + " FROM t_dispatch_detail AS a "
                        + " LEFT JOIN t_dispatch_ship AS b ON a.id = b.dispatch_detail_id "
                        + " LEFT JOIN t_dispatch AS c ON a.plan_no_id = c.id "
                        + " WHERE b.id = ? ", id).get(0);
                
                Record notice = new Record(); // 提醒
                notice.set("title", "运单需要预付款，请确认。");
                notice.set("type", "提醒");
                notice.set("publisher", user.getStr("user_name")); // 发布者
                notice.set("receiver", 2); // 接收者，财务
                notice.set("publish_time", new Date());
                Db.save("t_notice", notice);
                Long notice_id = notice.getLong("id");
                notice.set("url", "/notice/ship/getWaybill/" + notice_id);
                Db.update("t_notice", notice);
                
                r.set("notice_id", notice_id);
                Db.save("t_notice_waybill", r);
                
                return true;
            }
            
        });
        return result;
    }

}
