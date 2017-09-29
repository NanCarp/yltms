package yongle.notice.ship;

import java.sql.SQLException;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;

public class NoticeShipService {


    /** 
    * @Title: review 
    * @Description: 审核流向
    * @param id
    * @return boolean
    * @author 
    */
    public static boolean confirm(Integer notice_id, Integer dispatch_detail_id) {
        boolean result = Db.tx(new IAtom() {
            
            @Override
            public boolean run() throws SQLException {
                //Record r = Db.findFirst("SELECT * FROM `t_notice_ship` WHERE notice_id = ? ", id);
                //Integer dispatch_detail_id = r.getInt("dispatch_detail_id");
                
                int i = Db.update("UPDATE t_dispatch_ship SET over_guide_price = 0 WHERE dispatch_detail_id = ? ", dispatch_detail_id);
                int j = Db.update("UPDATE t_notice SET review = 1 WHERE id = ?", notice_id);
                
                return true;
            }
            
        });
        return result;
    }

}