package yongle.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.handler.Handler;

public class YoleHandler extends Handler {

	@Override
	public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {
		 if(target.indexOf(".html") == -1){
		        next.handle(target, request, response, isHandled);
		    }else{
		    	next.handle("/pages", request, response, isHandled);
		    }
	}

}
