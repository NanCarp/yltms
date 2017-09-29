package yongle.config;
import com.jfinal.config.Routes;

import yongle.dataBase.customer.CustomerController;
import yongle.dataBase.goods.GoodsController;
import yongle.dataBase.port.PortController;
import yongle.dataBase.ship.ShipController;
import yongle.dispatchmanage.handover.HandoverController;
import yongle.dispatchmanage.handoverfinish.HandoverFinishController;
import yongle.dispatchmanage.issuedplan.IssuedPlanController;
import yongle.dispatchmanage.planmanage.PlanManageController;
import yongle.dispatchmanage.waitsettle.WaitSettleController;
import yongle.insidejob.contract.ContractController;
import yongle.insidejob.plandispatch.PlanController;
import yongle.login.HomeController;
import yongle.login.LoginController;
import yongle.notice.ship.NoticeShipController;
import yongle.settle.customerfreight.CustomerFreightController;
import yongle.settle.customerfreight.CustomerSettledController;
import yongle.settle.managesettle.ManageSettleController;
import yongle.settle.shipfreight.ShipFreightController;
import yongle.settle.shipfreight.ShipSettleController;
import yongle.settlecorrectapplication.settleapplication.SettleApplicationController;
import yongle.site.handoversite.HandoverSiteController;
import yongle.site.receipt.ReceiptController;
import yongle.site.settle.SettleController;
import yongle.site.waterwayfreight.WaterwayFreightController;
import yongle.statistics.sales.SalesController;
import yongle.system.authority.AuthorityController;
import yongle.system.button.ButtonController;
import yongle.system.dictionary.DictionaryController;
import yongle.system.menu.MenuController;
import yongle.system.notice.NoticeController;
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
		//调度管理 - 待配流向
		add("/planManage/handover",HandoverController.class,"/dispatchmanage");
		//调度管理 - 已配流向
        add("/planManage/handoverfinish",HandoverFinishController.class,"/dispatchmanage");
		//调度管理 - 待结算审核表
		add("/planManage/waitsettle",WaitSettleController.class,"/dispatchmanage");
		//已下发计划
		add("/planManage/issued",IssuedPlanController.class,"/dispatchmanage");
		
		
		// 内勤管理-计划调度
		add("/insidejob/plan", PlanController.class, "/insidejob");
		// 内勤管理-合同管理
        add("/insidejob/contract", ContractController.class, "/insidejob");
        
        //现场管理- 结算审核表
      	add("/site/settle",SettleController.class,"/site");
        // 现场管理-调度交接
        add("/site/handoversite", HandoverSiteController.class, "/site");
        // 现场管理-运单录入
        add("/site/waterwayfreight", WaterwayFreightController.class, "/site");
        // 现场管理-回单录入
        add("/site/receipt", ReceiptController.class, "/site");
        
        // 结算审核-客户运费结算清单
        add("/settle/customerfreight", CustomerFreightController.class, "/settle");
        //客户结算管理
        add("/settle/customersettled",CustomerSettledController.class,"/settle");
        
        
        // 基础数据-港口信息
        add("/database/port", PortController.class, "/database");
        // 基础数据-货物信息
        add("/database/goods", GoodsController.class, "/database");
        //基础数据 - 客户信息管理
        add("/dataBase/customer",CustomerController.class,"/database");
        //基础数据 - 船舶信息管理
        add("/dataBase/ship",ShipController.class,"/database");
        
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
		// 系统管理-基础数据
        add("/system/dictionary",DictionaryController.class,"/system");
        //系统管理-提醒通知
        add("/system/notice",NoticeController.class,"/system");
        
        //结算批改申请-结算批改申请表
        add("/settlecorrect/app",SettleApplicationController.class,"/settlecorrectapplication");
        
        // 统计管理
        add("/statistics/sales", SalesController.class, "/statistics");
        
        //结算审核-结算审核表(经理)
        add("/settle/managesettle",ManageSettleController.class,"/settle");
        //结算审核-船舶运费结算清单
        add("/settle/ship",ShipFreightController.class,"/settle");
        //船舶运费结算清单-已结算
        add("/settle/shipsettled",ShipSettleController.class,"/settle");
        
        // 提醒-调度运价超出指导价
        add("/notice/ship",NoticeShipController.class,"/notice");

	}
}
