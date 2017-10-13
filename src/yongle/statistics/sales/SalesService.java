package yongle.statistics.sales;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

/**
 * @ClassName: SettleApplicationService.java
 * @Description:  销售统计
 * @author: LiYu
 * @date: 2017年9月12日下午2:21:10
 * @version: 1.0 版本初成
 */
public class SalesService {
    
    /** 
    * @Title: getDataPages 
    * @Description: 销售统计数据
    * @param pageindex
    * @param pagelimit
    * @param plan_no 计划号
    * @param consignor 托运人
    * @return Page<Record>
    * @author liyu
    */
    public static Page<Record> getDataPages(Integer pageindex, Integer pagelimit, String plan_no, String consignor) {
        String select = " SELECT a.plan_no,a.plan_no_id,a.consignor, "
                + " SUM(a.payable_amount) AS customer_total_payable_amount, SUM(b.payable_amount) AS ship_total_payable_amount, "
                + " SUM(a.payable_amount) - SUM(b.payable_amount) AS total_profit ";
        String sqlExceptSelect = " FROM v_customer_settle AS a "
                + " LEFT JOIN v_ship_settle AS b "
                + " ON a.id = b.id  "
                + " WHERE 1=1 ";
        
        if (plan_no != null && !"".equals(plan_no)) {
            sqlExceptSelect += " AND a.plan_no like '%"+ plan_no +"'";
        }
        if (consignor != null && !"".equals(consignor)) {
            sqlExceptSelect += " AND a.consignor like '%" + consignor + "'";
        }
        
        sqlExceptSelect += " GROUP BY a.plan_no_id "
                + " ORDER BY a.plan_no_id DESC "; // 计划号 id 降序排列
        
        return Db.paginate(pageindex, pagelimit, select, sqlExceptSelect);
    }

    /** 
    * @Title: getShipSettleList 
    * @Description: 船结算信息
    * @param id
    * @return List<Record>
    * @author liyu
    */
    public static List<Record> getShipSettleList(Integer id) {
        return Db.find("SELECT * FROM v_ship_settle WHERE plan_no_id = ? ", id);
    }

    /** 
    * @Title: getCustomerSettleList 
    * @Description: 客户结算信息
    * @param id
    * @return List<Record>
    * @author 
    */
    public static List<Record> getCustomerSettleList(Integer id) {
        return Db.find("SELECT * FROM v_customer_settle WHERE plan_no_id = ? ", id);
    }

}
