package yongle.notice.ship;

import java.sql.SQLException;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;

/**
 * @ClassName: NoticeShipService.java
 * @Description: 消息提醒
 * @author: liyu
 * @date: 2017年9月21日下午5:05:01
 * @version: 1.0 版本初成
 */
public class NoticeShipService {


    /** 
    * @Title: review 
    * @Description: 同意价格超出指导价
    * @param id
    * @return boolean
    * @author liyu
    */
    public static boolean confirm(Integer notice_id, Integer dispatch_detail_id) {
        boolean result = Db.tx(new IAtom() {
            
            @Override
            public boolean run() throws SQLException {
                
                Db.update("UPDATE t_dispatch_ship SET over_guide_price = 0 WHERE dispatch_detail_id = ? ", dispatch_detail_id);
                Db.update("UPDATE t_notice SET review = 1 WHERE id = ?", notice_id);
                
                return true;
            }
            
        });
        return result;
    }

}