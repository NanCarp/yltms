package yongle.settlecorrectapplication.settleapplication;

import com.jfinal.core.Controller;
/**
 * @ClassName: SettleApplicationController.java
 * @Description: 结算批改申请-结算批改申请表
 * @author: xuhui
 * @date: 2017年9月5日上午8:22:01
 * @version: 1.0 版本初成
 */
public class SettleApplicationController extends Controller {

	public void index(){
		render("settleapplication.html");
	}
}
