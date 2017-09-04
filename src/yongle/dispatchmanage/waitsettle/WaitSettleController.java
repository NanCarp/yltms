package yongle.dispatchmanage.waitsettle;

import com.jfinal.core.Controller;
/**
 * @ClassName: WaitSettleController.java
 * @Description: 待结算审核表
 * @author: xuhui
 * @date: 2017年8月29日上午11:01:26
 * @version: 1.0 版本初成
 */
public class WaitSettleController extends Controller {

	public void index(){
		render("waitsettle.html");
	}
}
