package yongle.login;

import com.jfinal.core.Controller;

/**
 * @ClassName: HomeController.java
 * @Description: 未匹配的 url 跳转到登陆页面
 * @author: liyu
 * @date: 2017年9月5日上午10:46:15
 * @version: 1.0 版本初成
 */
public class HomeController extends Controller {
    public void index(){
        redirect("/pages/login");
    }
}
