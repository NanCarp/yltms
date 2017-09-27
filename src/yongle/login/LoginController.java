package yongle.login;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.core.JFinal;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import yongle.interceptor.ManageInterceptor;
import yongle.utils.MD5Util;
import yongle.utils.WeatherUtil;


/**
 * @ClassName: LoginController.java
 * @Description: 登陆控制器
 * @author: LiYu
 * @date: 2017年5月12日下午4:02:44
 * @version: 1.0 版本初成
 */
@Before(ManageInterceptor.class)
public class LoginController extends Controller{
	
	/** 
	* @Title: index 
	* @Description: 首页
	* @param 
	* @return void
	* @throws 
	*/	
	@Before(ManageInterceptor.class)
	public void index(){
		// session 获取用户
		Record user = getSessionAttr("admin");
		user = Db.findById("t_user", 1);
		// 角色 id
        Integer roleId = user.getInt("role_id");
        // 角色对应菜单列表
        List<Record> menuList = LoginService.getMenusByRoleId(roleId);
        setAttr("menuList", menuList);
        // 账号姓名
        setAttr("user_name", user.getStr("user_name"));

        render("index.html");
	}
	
	/** 
	* @Title: login 
	* @Description: 登录页面
	* @param 
	* @return void
	* @throws 
	*/
	@Clear
	public void login() {
		
		render("login.html");
		
	}
	
	/**
	 * @Title:amdinLogin
	 * @desc 判定用户名以及密码
	 * @return void 
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 */
	@Clear
	public void adminLogin() throws NoSuchAlgorithmException, UnsupportedEncodingException{
	    Map<String, Object> responseMap = new HashMap<>(); // 返回信息
	    String dateStr = "2018-9-1 00:00:00";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date cutoffTime = new Date();
		try {
			 cutoffTime = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String username = getPara("username");
		String password = getPara("password");
		
		boolean result = false;
		String msg = new String();
		
		Record admin = LoginService.getLoginInfo(username);
		if(admin == null){
			msg = "用户名或密码错误";
		}else{
		    // 查看是否冻结
		    if(admin.getBoolean("state") == false) {
		        responseMap = new HashMap<>();
		        responseMap.put("result", result);  
		        responseMap.put("msg", "此账号已被冻结");
		        renderJson(responseMap);
		        return;
		    }
		    
		    // 查看是否到期，0：正常，1：提示快到期，2：已到期，无法登录
		    System.out.println(compareDate(cutoffTime, new Date()));
            if(admin.getInt("type") == 2||compareDate(cutoffTime, new Date())==-1) {
                responseMap = new HashMap<>();
                responseMap.put("result", result);  
                responseMap.put("type", admin.getInt("type"));
                responseMap.put("msg", "已到期，无法登录");
                renderJson(responseMap);
                return;
            }
		    
			boolean v = MD5Util.validPassword(password, admin.getStr("password"));
			if(v){
				result = true;
				msg = "登录成功";
				getSession().setAttribute("admin", admin);
			}else{
				msg = "用户名或密码错误";
			}
		}
		responseMap.put("result", result);	
		responseMap.put("msg", msg);
		responseMap.put("type", admin.getInt("type"));
		renderJson(responseMap);
	}
	

	/**
	 * @desc:退出登录
	 */
	public void loginOut(){		
		ServletContext application = JFinal.me().getServletContext();
		Record admin = getSessionAttr("admin");
		if(admin!=null){
			String username = admin.getStr("account");
			Map<String, Object> loginRecordMap = (Map<String, Object>) getSession().getAttribute("loginRecordMap");
			getSession().removeAttribute("admin");			
		}
			redirect("/pages/login");	
	}
	
	
	/**
	 * @desc 自用仓库库存信息查询页面
	 * @author xuhui
	 */
	public void iframe(){
		//通知公告
		List<Record> noticesList = LoginService.getNotices();
		
		//提醒
		List<Record> tipsList = LoginService.getTips();
		
		setAttr("noticesList", noticesList);
		setAttr("tipsList", tipsList);
		
		render("indexframe.html");	
	}

	
	/**
	 * @desc 比较时间大小，判断是否可以登录
	 * @param d1
	 * @param d2
	 * @return
	 */
	public int compareDate(Date d1,Date d2){
        if (d1.getTime() > d2.getTime()) {
            return 1;
        } else if (d1.getTime() < d2.getTime()) {
            return -1;
        } else {//相等
            return 0;
        }
	}
	
	/**
	 * @desc 调用天气接口
	 * @author xuhui
	 * @throws UnsupportedEncodingException 
	 */
	@Clear
	public void getWeather() throws Exception{
		renderJson(WeatherUtil.queryWeatherByCity("泰州"));	
	}
	
	/**
	 * @desc 查看提示明细
	 * @author xuhui
	 */
	@Clear
	public void getNotice(){
		Integer id = getParaToInt();
		Record record = Db.findById("t_notice", id);
		setAttr("record", record);
		render("noticed.html");
	}
	
	/**
	 * @desc 更新提示数据
	 * @author xuhui
	 */
	@Clear
	public void getTips(){
		String sql = "select * from t_notice where type='提醒' and review = 0";
		List<Record> list = Db.find(sql);
		renderJson(list);	
	}
	
	/** 
	* @Title: getMoreTips 
	* @Description: 查看更多提醒
	* @author liyu
	*/
	public void getMoreTips() {
		//TODO 只有经理可以查看 此处需要注意
		// session 获取用户
	    String sql = "SELECT * FROM t_notice WHERE type='提醒' and review = 0 and publisher = 'admin'";
        List<Record> list = Db.find(sql);
        setAttr("tipsList", list);
        render("tips_list.html");
	}

}
