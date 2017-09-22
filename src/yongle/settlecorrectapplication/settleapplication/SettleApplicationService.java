package yongle.settlecorrectapplication.settleapplication;

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
public class SettleApplicationService {
    private static final SettleApply dao = new SettleApply();
    
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
    public static Page<Record> getDataPages(Integer pageindex, Integer pagelimit, String plan_no, String apply_begin,
            String apply_end, String dispatcher) {
        String select = " SELECT * ";
        String sqlExceptSelect = " FROM `t_settle_apply` ";
        
        if (plan_no != null && !"".equals(plan_no)) {
            sqlExceptSelect += " AND plan_no like '%"+ plan_no +"'";
        }
        
        if (apply_begin != null && !"".equals(apply_begin)) {
            sqlExceptSelect += " AND entry_time > '" + apply_begin + "'";
        }
        
        if (apply_end != null && !"".equals(apply_end)) {
            sqlExceptSelect += " AND entry_time < '" + apply_end + "'";
        }
        
        if (dispatcher != null && !"".equals(dispatcher)) {
            sqlExceptSelect += " AND dispatcher like '%" + dispatcher + "'";
        }
        
        sqlExceptSelect += " ORDER BY id desc";
        
        return Db.paginate(pageindex, pagelimit, select, sqlExceptSelect);
    }

    /** 
    * @Title: deleteById 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @return boolean
    * @author liyu
    */
    public static boolean deleteById(Integer id) {
        return dao.deleteById(id);
    }

}
