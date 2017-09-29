/**  
 * All rights Reserved, Designed By www.yetangtang.com
 * @Title:  WebSocketController.java   
 * @Package yongle.utils   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: sugar Ye    
 * @date:   2017年9月21日 下午4:11:58   
 * @version V1.0 
 * @Copyright: 2017 www.yetangtang.com Inc. All rights reserved. 
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目
 */
package yongle.utils;



import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.alibaba.fastjson.JSONObject;


/**   
 * @ClassName:  WebSocketController   
 * @Description:ws控制器  
 * @author: sugar Ye
 * @date:   2017年9月21日 下午4:11:58   
 *     
 * @Copyright: 2017 www.yetangtang.com Inc. All rights reserved. 
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目 
 */
@ServerEndpoint("/websocket")
public class WebSocketController {
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<WebSocketController> webSocketSet = new CopyOnWriteArraySet<WebSocketController>();

    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    
    
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		webSocketSet.add(this);
	}

	@OnClose
	public void onClose(Session session) {
		webSocketSet.remove(this);
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		try {
			//session.getBasicRemote().sendText(requestJson);
		    for (WebSocketController item : webSocketSet) {
		        item.sendMessage(message);
		    }
		} catch (IOException e) {		
			e.printStackTrace();
		}
	}
	
	/**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {

    }
    
    // 发送消息
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
}
