/**  
 * All rights Reserved, Designed By www.yetangtang.com
 * @Title:  WebSocketHandler.java   
 * @Package yongle.utils   
 * @Description:消息处理类  
 * @author: sugar Ye    
 * @date:   2017年9月21日 下午3:54:07   
 * @version V1.0 
 * @Copyright: 2017 www.yetangtang.com Inc. All rights reserved. 
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目
 */
package yongle.utils;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.handler.Handler;
import com.jfinal.kit.StrKit;

/**   
 * @ClassName:  WebSocketHandler   
 * @Description:处理系统中提醒信息   
 * @author: sugar Ye
 * @date:   2017年9月21日 下午3:54:07     
 * @Copyright: 2017 www.yetangtang.com Inc. All rights reserved. 
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目 
 */
public class WebSocketHandler extends Handler {
	
	private Pattern filterUrlRegxPattern;
	
	public WebSocketHandler(String filterUrlRegx){
		if(StrKit.isBlank(filterUrlRegx)){
			throw new IllegalArgumentException("The para filterUrlRegx can not be blank.");
		}
		else{
			filterUrlRegxPattern = Pattern.compile(filterUrlRegx);
		}
	}
	
	@Override
	public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {
		if (filterUrlRegxPattern.matcher(target).find())
			return ;
		else
			next.handle(target, request, response, isHandled);
		
	}

}
