package yongle.dataBase.shipblacklist;

import java.sql.SQLException;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

/**
 * @ClassName: ShipBlackService.java
 * @Description: 船舶黑名单管理
 * @author: xuhui
 * @date: 2017年8月29日下午17:40:05
 * @version: 1.0 版本初成
 */
public class ShipBlackService {

	/**
	 * @desc 展示清单页数据
	 * @author xuhui
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public static Page<Record> getJson(Integer pageNumber,Integer pageSize
			,String shipowname,String ship_name){
		String sql = " from t_base_ship where blacklist=1";
		if(shipowname!=null){
			sql +=" and shipowname like '%"+shipowname+"%'";
		}
		if(ship_name!=null){
			sql +=" and ship_name like '%"+ship_name+"%'";
		}
		sql += "order by id desc";
		return Db.paginate(pageNumber, pageSize, "select *",sql);
	}
	
	/**
	 * @desc 修改黑名单
	 * @author xuhui
	 */
	public static boolean setOutBlack(String blackList){
		
		boolean flag = Db.tx(new IAtom() {
			@Override
			public boolean run() throws SQLException {
				// TODO Auto-generated method stub
				String[] ids =  blackList.split(",");
				for(String id:ids){
					String sql = "UPDATE t_base_ship SET blacklist = 0 WHERE id = "+id;
					Db.update(sql);
				}
				return true;
			}
		});
		return flag;
	}
}
