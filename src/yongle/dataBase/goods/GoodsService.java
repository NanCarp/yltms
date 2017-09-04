package yongle.dataBase.goods;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

/**
 * @ClassName: GoodsService.java
 * @Description: 
 * @author: LiYu
 * @date: 2017年8月29日下午6:58:08
 * @version: 1.0 版本初成
 */
public class GoodsService {

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
    public static Page<Record> getDataPages(Integer pageindex, Integer pagelimit, String goods_name) {
        String select = " SELECT * ";
        String sqlExceptSelect = " FROM `t_base_goods` WHERE 1=1 ";
        
        // 货物名称
        if (goods_name != null && !"".equals(goods_name)) {
            sqlExceptSelect += " AND goods_name like '%" + goods_name + "%'";
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
    public static boolean isDuplicate(String goods_name) {
        List<Record> list = Db.find("SELECT id FROM t_base_goods WHERE goods_name = ?", goods_name);
        return list.size() > 0;
    }

}
