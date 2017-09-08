package yongle.settle.shipfreight;

import com.jfinal.core.Controller;
/**
 * @ClassName: ShipFreightController.java
 * @Description: 结算审核表-船舶运费结算清单
 * @author: xuhui
 * @date: 2017年9月5日上午10:55:00
 * @version: 1.0 版本初成
 */
public class ShipFreightController extends Controller {

	/**
	 * @author xuhui
	 * @desc 展示清单页数据
	 */
	public void index(){
		render("shipfreight.html");
	}
	
	/**
	 * @desc 结算清单编辑
	 * @author xuhui
	 */
	public void getEdit(){
		render("shipfreight_detail.html");
	}
}
