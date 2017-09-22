package yongle.system.notice;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

/**
 * @ClassName: NoticeService
 * @Description: 系统管理_提醒通知
 * @author: xuhui
 * @date: 2017年9月18日上午10:15:00
 * @version: 1.0 版本初成
 */
public class NoticeService {

	/**
	 * @desc 清单页数据展示
	 * @author xuhui
	 */
	public static List<Record> getAllNotice(String title,String type){
		String sql = "select * from t_notice where 1=1";
		if(title!=null&&title!=""){
			sql +=" and title like '%"+title+"%'";
		}
		if(type!=null&&type!=""){
			sql +=" and type ='"+type+"'";
		}
		sql += " order by id desc";
		return Db.find(sql);
	} 
}
