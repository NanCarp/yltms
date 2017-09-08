package yongle.settle.managesettle;

import com.jfinal.core.Controller;

/**
 * @ClassName: SettleApplicationController.java
 * @Description: 结算审核表-结算审核表(经理)
 * @author: xuhui
 * @date: 2017年9月5日上午8:29:51
 * @version: 1.0 版本初成
 */
public class ManageSettleController extends Controller{

	/**
	 * @desc 展示清单页面
	 * @author xuhui
	 */
	public void index(){
		render("settle.html");
	}
	
	/**
	 * @desc 明细数据
	 * @author xuhui
	 */
	public void getDetail(){
		render("settle_detail.html");	
	}
}
