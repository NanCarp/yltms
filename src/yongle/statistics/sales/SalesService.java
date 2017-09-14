package yongle.statistics.sales;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import yongle.model.SettleApply;

/**
 * @ClassName: SettleApplicationService.java
 * @Description:
 * @author: LiYu
 * @date: 2017年9月12日下午2:21:10
 * @version: 1.0 版本初成
 */
public class SalesService {
    
    /** 
    * @Title: getDataPages 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param pageindex
    * @param pagelimit
    * @param plan_no
    * @param apply_begin
    * @param apply_end
    * @param dispatcher
    * @return Page<Record>
    * @author liyu
    */
    public static Page<Record> getDataPages(Integer pageindex, Integer pagelimit, String plan_no, String consignor) {
        String select = " SELECT * ";
        String sqlExceptSelect = " FROM v_sales WHERE 1=1 ";
        
        if (plan_no != null && !"".equals(plan_no)) {
            sqlExceptSelect += " AND plan_no like '%"+ plan_no +"'";
        }
        if (consignor != null && !"".equals(consignor)) {
            sqlExceptSelect += " AND consignor like '%" + consignor + "'";
        }
        
        return Db.paginate(pageindex, pagelimit, select, sqlExceptSelect);
    }

}
