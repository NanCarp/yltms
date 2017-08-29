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

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.core.JFinal;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import yongle.interceptor.ManageInterceptor;
import yongle.utils.MD5Util;


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
			// TODO Auto-generated catch block
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
		render("indexframe.html");	
	}
	
	/**
	 * @desc 自用仓库库存信息查询
	 * @author xuhui
	 */
	public void getJson(){
		String product_num = getPara("product_num");
		String trade_name = getPara("trade_name");
    	Integer	pageindex = 0;
    	Integer pagelimit = getParaToInt("limit")==null? 12 :getParaToInt("limit");
    	Integer offset = getParaToInt("offset")==null?0:getParaToInt("offset");
    	if(offset!=0){
    		pageindex = offset/pagelimit;
    	}
    	pageindex += 1;
    	Map<String, Object> map = new HashMap<String,Object>(); 	
    	List<Record> dictionaryList = LoginService.getIframe(pageindex, pagelimit,product_num,trade_name).getList();
    	map.put("rows", dictionaryList);
    	map.put("total",LoginService.getIframe(pageindex, pagelimit,product_num,trade_name).getTotalRow());
    	renderJson(map);
	}
	
	/**
	 * @desc 比较时间大小，判断是否可以登录
	 * @param d1
	 * @param d2
	 * @return
	 */
	public int compareDate(Date d1,Date d2){
        if (d1.getTime() > d2.getTime()) {
            System.out.println("dt1 在dt2前");
            return 1;
        } else if (d1.getTime() < d2.getTime()) {
            System.out.println("dt1在dt2后");
            return -1;
        } else {//相等
            return 0;
        }
	}
}
