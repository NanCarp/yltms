package yongle.dataBase.ship;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import yongle.utils.EncodeUtil;

/**
 * @ClassName: ShipService.java
 * @Description: 船舶信息管理
 * @author: xuhui
 * @date: 2017年8月30日下午15:16:05
 * @version: 1.0 版本初成
 */
public class ShipService {
	
	/**
	 * @desc 展示清单页数据
	 * @author xuhui
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public static Page<Record> getJson(Integer pageNumber,Integer pageSize
			,String shipowname,String ship_name){
		String sql = " from t_base_ship where 1=1";
		if(shipowname!=null){
			sql +=" and shipowname like '%"+shipowname+"%'";
		}
		if(ship_name!=null){
			sql +=" and ship_name like '%"+ship_name+"%'";
		}
		sql += "order by id desc";
		return Db.paginate(pageNumber, pageSize, "select *",sql);
	}
	
	
	/**
	 * tab1
	 * @desc 保存新增数据
	 * @author xuhui
	 */
	public static Integer saveTabOne(String shipowname,String ship_name,String ID_number
			,String phone_num,String ship_ton,String grade_sail,String business_route){
		Integer flag = 0;//设置返回值，如果返回值为0，则表示保存失败
		boolean flagDb = false;//判断是否保存成功
		Record record = new Record();
		record.set("shipowname", shipowname);
		record.set("ship_name", ship_name);
		record.set("ID_number", ID_number);
		record.set("phone_num", phone_num);
		record.set("ship_ton", ship_ton);
		record.set("grade_sail", grade_sail);
		record.set("business_route", business_route);
		flagDb = Db.save("t_base_ship", record);
		if(flagDb){
			flag = record.getLong("id").intValue();
			}
		return flag;
	}
	
	/**
	 * tab2
	 * @desc 保存新增数据
	 * @author xuhui
	 */
	public static boolean saveTabTwo(Integer id,String open_bank,String name_bank
			,String card_bank,String updatetime_bank){
		Record record = new Record();
		record.set("id", id);
		record.set("open_bank", open_bank);
		record.set("name_bank", name_bank);
		record.set("card_bank", card_bank);
		record.set("updatetime_bank", updatetime_bank);
		return Db.update("t_base_ship", record);
	}
	
	/**
	 * @desc 下载文件
	 * @author xuhui
	 * @throws IOException 
	 */
	public static void downloadFile(HttpServletResponse response, Integer id,String fileName) throws IOException{
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + EncodeUtil.toUtf8String(fileName));
		String fullFileName = PropKit.get("filepath_commerce")+id+"/"+fileName;
		//读文件
		InputStream in = new FileInputStream(fullFileName);
		OutputStream out = response.getOutputStream();

		//写文件
		int b;
		while((b=in.read())!=-1){
			out.write(b);
		}
		in.close();
		out.close();
	}
	
	/**
	 * 
	 * @param response
	 * @param id
	 * @param fileName
	 * @throws IOException 
	 */
	public static void downloadCrewFile(HttpServletResponse response,Integer id,String fileName) throws IOException{
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + EncodeUtil.toUtf8String(fileName));
		String fullFileName = PropKit.get("filepath_shipCrew")+id+"/"+fileName;
		//读文件
		InputStream in = new FileInputStream(fullFileName);
		OutputStream out = response.getOutputStream();

		//写文件
		int b;
		while((b=in.read())!=-1){
			out.write(b);
		}
		in.close();
		out.close();
	}
}
