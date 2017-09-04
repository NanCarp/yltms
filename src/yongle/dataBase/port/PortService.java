package yongle.dataBase.port;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

/**
 * @ClassName: PortService.java
 * @Description:
 * @author: LiYu
 * @date: 2017年8月29日下午6:58:15
 * @version: 1.0 版本初成
 */
public class PortService {

    /** 
    * @Title: getDataPages 
    * @Description: 分页查询
    * @param pageindex
    * @param pagelimit
    * @param warehouse_in_no
    * @param company_name
    * @return Page<Record>
    * @author liyu
    */
    public static Page<Record> getDataPages(Integer pageindex, Integer pagelimit, String port) {
        String select = " SELECT * ";
        String sqlExceptSelect = " FROM `t_base_port` WHERE 1=1 ";
        
        // 港口名称
        if (port != null && !"".equals(port)) {
            sqlExceptSelect += " AND port like '%" + port + "%'";
        }
        
        return Db.paginate(pageindex, pagelimit, select, sqlExceptSelect);
    }

    /** 
    * @Title: isDuplicate 
    * @Description: 查重
    * @param port_code
    * @return boolean
    * @author liyu
    */
    public static boolean isDuplicate(String port_code) {
        List<Record> list = Db.find("SELECT id FROM t_base_port WHERE port_code = ?", port_code);
        return list.size() > 0;
    }

}
