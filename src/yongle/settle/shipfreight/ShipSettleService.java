package yongle.settle.shipfreight;

import java.math.BigDecimal;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

/**
 * @ClassName: ShipFreightService.java
 * @Description: 船舶运费结算清单-已结算
 * @author: xuhui
 * @date: 2017年9月6日上午10:55:00
 * @version: 1.0 版本初成
 */
public class ShipSettleService {

	/**
	 * @desc 结算审核表清单页展示
	 * @author xuhui
	 */
	public static Page<Record> getDataPages(Integer pageNumber,Integer pageSize,String plan_no){
	    String select = " select s.*,k.*,e.*,d.* ";
        String sqlExceptSelect = " from t_ship_settle s LEFT JOIN t_dispatch_ship d ON s.dispatch_ship_id = d.id"
        						+" LEFT JOIN t_dispatch_detail e ON e.id = d.dispatch_detail_id" 
        						+" LEFT JOIN t_dispatch k ON k.id = e.plan_no_id where s.payable_amount is not null";
        if(plan_no!=null&&plan_no!=""){
        	sqlExceptSelect +=" and k.plan_no like '%"+plan_no+"%'";
        } 
        sqlExceptSelect +=" order by s.id desc";
       return Db.paginate(pageNumber, pageSize, select,sqlExceptSelect);
	}
	
    /** 
    * @Title: getList 
    * @Description: 打印列表
    * @param plan_no
    * @return List<Record>
    * @author liyu
    */
    public static List<Record> getList(String plan_no) {
        return getDataPages(1, 1000, plan_no).getList();
    }

    /** 
    * @Title: calculateTotal 
    * @Description: 应收金额合计
    * @param recordList
    * @return BigDecimal
    * @author liyu
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
