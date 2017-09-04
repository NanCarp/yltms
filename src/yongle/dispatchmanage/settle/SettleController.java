package yongle.dispatchmanage.settle;

import com.jfinal.core.Controller;

/**
 * @ClassName: SettleController.java
 * @Description: 结算审核表
 * @author: xuhui
 * @date: 2017年8月29日上午14:11:26
 * @version: 1.0 版本初成
 */
public class SettleController extends Controller{

	public void index(){
		render("settle.html");
	}
}
