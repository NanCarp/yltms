package yongle.insidejob.plandispatch;

import com.jfinal.core.Controller;

/**
 * @ClassName: PlanDispatchController.java
 * @Description: 内勤管理-计划调度
 * @author: LiYu
 * @date: 2017年8月28日上午9:15:30
 * @version: 1.0 版本初成
 */
public class PlanDispatchController extends Controller {
    public void index() {
        render("plan_dispatch.html");
    }
}
