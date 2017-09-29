/**  
 * All rights Reserved, Designed By www.yetangtang.com
 * @Title:  WeatherUtil.java   
 * @Package yongle.utils   
 * @Description:天气接口工具类 
 * @author: sugar Ye    
 * @date:   2017年9月15日 上午11:07:24   
 * @version V1.0 
 * @Copyright: 2017 www.yetangtang.com Inc. All rights reserved. 
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目
 */
package yongle.utils;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.weixin.sdk.utils.IOUtils;

/**   
 * @ClassName:  WeatherUtil   
 * @Description:天气接口工具类
 * @author: sugar Ye
 * @date:   2017年9月15日 上午11:07:24   
 *     
 * @Copyright: 2017 www.yetangtang.com Inc. All rights reserved. 
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目 
 */
public class WeatherUtil {
	
	/**
	 * @throws Exception 
	 * @Title: queryWeatherByCity   
	 * @Description: 根据城市查询天气   
	 * @param:  city      
	 * @return: JSONObject     
	 * @throws
	 */
    public static JSONObject queryWeatherByCity(String city) throws Exception{
    	//声明变量
    	JSONObject jsonObj = null;
    	//简单的剔除空格
    	String new_city=city.trim();
    	//判定参数是否为空
    	if(new_city!=""&&new_city!=null){
    		//优先从缓存中获取数据
    		jsonObj=CacheKit.get("weather","weatherJson");
    		//缓存中的数据不存在，则去请求服务器
    		if(jsonObj==null){
    			//参数url化
    			new_city = java.net.URLEncoder.encode(new_city,"utf-8");
    			//拼接地址
    			String apiUrl = String.format("http://www.sojson.com/open/api/weather/json.shtml?city=%s", new_city);
    			//开始请求
    			URL url = new URL(apiUrl);
    			URLConnection open = url.openConnection();
    			InputStream input = open.getInputStream();
    			String result = IOUtils.toString(input, Charset.forName("utf-8"));
    			jsonObj = JSONObject.parseObject(result);
    			//将数据存入缓存
    			CacheKit.put("weather","weatherJson",jsonObj);
    		}
    	}
    	//返回天气信息
		return jsonObj;
    }
}
