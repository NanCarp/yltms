package yongle.login;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

/**
 * @ClassName: LoginService.java
 * @Description:
 * @author: LiYu
 * @date: 2017年6月12日上午11:13:58
 * @version: 1.0 版本初成
 */
public class LoginService {
    /*********************登陆管理*************************/
    // 根据角色 id ，获取菜单列表
    public static List<Record> getMenusByRoleId(Integer roleId) {
        // roleId 对应的菜单权限记录
        Record record = Db.find("SELECT * FROM t_role_menu WHERE role_id = ? ", roleId).get(0);
        // 菜单 ids
        String menuIds = record.getStr("menu_ids");
        // 菜单列表
        List<Record> menus = Db.find("SELECT * FROM `t_menu` WHERE FIND_IN_SET(id,?)", menuIds);

        return menus;
    }
    public static Record getLoginInfo(String username){
		return Db.findFirst("select * from t_user where account = ?",username);
	}
	
	public static boolean saveuserLogin(int user_id,String ip,String agent){

		return false;
	}


    
	/**
	 * @desc 保存用户登录信息
	 * @param loginRecordMap
	 * @return boolean 
	 */
	public static boolean saveLoginMessage(Map<String, Object> loginRecordMap){
		Record record = new Record();
		Record record1=Db.findById("t_user",loginRecordMap.get("userid"));
		record.set("user_account", record1.getStr("account"));
		record.set("ip", loginRecordMap.get("userip"));
		record.set("agent", loginRecordMap.get("MoblieOrPc"));
		record.set("login_time",loginRecordMap.get("loginTime"));
		record.set("logout_time",new Date());
		System.out.println(loginRecordMap.get("userid"));
		return Db.save("t_user_log", record);
	}
	
	/**
	 * @desc 通知公告
	 * @author xuhui
	 */
	public static List<Record> getNotices(){
		String sql ="SELECT * from t_notice WHERE type='通知公告' ORDER BY id DESC  LIMIT 0,5";
		return Db.find(sql);
	}
	
	/**
	 * @desc 提醒
	 * @author xuhui
	 */
	public static List<Record> getTips(){
		String sql ="SELECT * from t_notice WHERE type='提醒' AND review = 0 ORDER BY id DESC  ";
		return Db.find(sql);
	}
	
}
