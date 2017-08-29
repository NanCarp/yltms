package yongle.config;
import com.jfinal.config.Routes;

import yongle.dispatchmanage.handover.HandoverController;
import yongle.dispatchmanage.planmanage.PlanManageController;
import yongle.insidejob.contract.ContractController;
import yongle.insidejob.plandispatch.PlanDispatchController;
import yongle.login.HomeController;
import yongle.login.LoginController;
import yongle.system.authority.AuthorityController;
import yongle.system.button.ButtonController;
import yongle.system.menu.MenuController;
import yongle.system.role.RoleController;
import yongle.system.user.UserController;


/**
 * @ClassName: AdminRoutes.java
 * @Description: 配置后端路由（供管理系统）
 * @author: LiYu
 * @date: 2017年8月19日上午8:07:33
 * @version: 1.0 版本初成
 */
public class AdminRoutes extends Routes{
	/**
	 *@desc 配置后端路由
	 *@date 2017/05/12 
	 */
	@Override
	public void config() {
		// 设置页面base路径
		setBaseViewPath("/pages");
		// 其他 url 
		add("/",HomeController.class,"");
		
		// 登陆控制器
		add("/pages",LoginController.class,"");
		
		//调度管理 - 计划管理
		add("/planManage/dispatch",PlanManageController.class,"/dispatchmanage");
		//调度管理 - 调度交接
		add("/planManage/handover",HandoverController.class,"/dispatchmanage");
		
		// 内勤管理-计划调度
		add("/insidejob/plandispatch", PlanDispatchController.class, "/insidejob");
		// 内勤管理-计划调度
        add("/insidejob/contract", ContractController.class, "/insidejob");

		//系统管理-角色管理控制器
		add("/system/role",RoleController.class,"/system");
		//系统管理-用户管理控制器
		add("/system/user",UserController.class,"/system");
		//系统管理-菜单管理控制器
		add("/system/menu",MenuController.class,"/system");
		//系统管理-按钮管理控制器
		add("/system/button",ButtonController.class,"/system");
		//系统管理-权限管理控制器
		add("/system/authority",AuthorityController.class,"/system");
		
	}
}
