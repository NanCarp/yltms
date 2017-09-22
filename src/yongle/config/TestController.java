/**  
 * All rights Reserved, Designed By www.yetangtang.com
 * @Title:  TestController.java   
 * @Package yongle.config   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: sugar Ye    
 * @date:   2017年9月21日 下午4:36:43   
 * @version V1.0 
 * @Copyright: 2017 www.yetangtang.com Inc. All rights reserved. 
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目
 */
package yongle.config;

import com.jfinal.core.Controller;

/**   
 * @ClassName:  TestController   
 * @Description:一个简单的测试控制器，功能完结之后即可删除  
 * @author: sugar Ye
 * @date:   2017年9月21日 下午4:36:43   
 *     
 * @Copyright: 2017 www.yetangtang.com Inc. All rights reserved. 
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目 
 */
public class TestController extends Controller {
   public void index(){
	   render("wsTest.html");
   }
}
