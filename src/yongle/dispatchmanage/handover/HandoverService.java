package yongle.dispatchmanage.handover;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

public class HandoverService {

    /** 
    * @Title: getDataPages 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param pageindex
    * @param pagelimit
    * @param warehouse_in_no
    * @param company_name
    * @return Page<Record>
    * @author liyu
    */
    public static Page<Record> getDataPages(Integer pageindex, Integer pagelimit, String warehouse_in_no,
            String company_name) {
        // TODO Auto-generated method stub
        return Db.paginate(pageindex, pagelimit, "SELECT * ", "FROM `t_dispatch`");
    }

}
