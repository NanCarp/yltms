package yongle.insidejob.plandispatch;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

/**
 * @ClassName: PlanDispatchService.java
 * @Description:
 * @author: LiYu
 * @date: 2017年8月31日上午8:36:35
 * @version: 1.0 版本初成
 */
public class PlanDispatchService {

    public static Page<Record> getDataPages(Integer pageindex, Integer pagelimit, String plan_no, String consignor) {
        String select = " SELECT * ";
        String sqlExceptSelect = " FROM `t_dispatch` AS a "
                + " RIGHT JOIN t_dispatch_ship AS b "
                + " ON a.id = b.dispatch_id "
                + " WHERE a.dispatch_issue = 1 ";
        
        if (plan_no != null && !"".equals(plan_no)) {
            sqlExceptSelect += " AND plan_no like '%"+ plan_no +"'";
        }
        
        if (consignor != null && !"".equals(consignor)) {
            sqlExceptSelect += " AND consignor like '%"+ consignor +"'";
        }
        
        return Db.paginate(pageindex, pagelimit, select, sqlExceptSelect);
    }

}
