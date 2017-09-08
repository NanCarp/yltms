package yongle.dataBase.customer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.template.stat.ast.Set;

import yongle.utils.EncodeUtil;

/**
 * @ClassName: CustomerController.java
 * @Description: 客户信息管理
 * @author: xuhui
 * @date: 2017年8月29日下午16:40:05
 * @version: 1.0 版本初成
 */
public class CustomerService {

	/**
	 * @desc 展示清单页
	 * @author xuhui
	 */
	public static Page<Record>  getJson(Integer pageNumber,Integer pageSize,String customer_name,String customer_type,String company_information){
		String sql = " from t_base_customer where 1=1 ";
		if(customer_name!=null&&customer_name!=""){
			sql +=" and customer_name like '%"+customer_name+"%'";
		}
		if(customer_type!=null&&customer_type!=""){
			sql +=" and customer_type = '"+customer_type+"'";
		}
		if(company_information!=null&&company_information!=""){
			sql +=" and company_information like '%"+company_information+"%'";
		}
		sql +=" order by id desc";
		return Db.paginate(pageNumber, pageSize, "select *",sql);
	}
	
	/**
	 * tab1
	 * @desc 保存新增数据
	 * @author xuhui
	 */
	public static Integer saveTabOne(Integer id,String customer_name,String customer_type,String variety_goods
			,String common_receiving_company,String common_delivery_company,String wharf_berth){
		Integer flag = 0;//设置返回值，如果返回值为0，则表示保存失败
		boolean flagDb = false;//判断是否保存成功
		Record record = new Record();
		record.set("customer_name", customer_name);
		record.set("customer_type", customer_type);
		record.set("variety_goods", variety_goods);
		record.set("common_delivery_company", common_delivery_company);
		record.set("common_receiving_company", common_receiving_company);
		record.set("wharf_berth", wharf_berth);
		if(id!=null){
			record.set("id", id);
			flagDb = Db.update("t_base_customer", record);
			if(flagDb){
				flag = record.getInt("id");
			}
		}else{
			flagDb = Db.save("t_base_customer", record);
			if(flagDb){
				flag = record.getLong("id").intValue();
			}
		}
		return flag;
	}
	
	/**
	 * tab2
	 * @desc 保存新增数据
	 * @author xuhui
	 */
	public static boolean saveTabTwo(Integer id,String name_content,String post_contant,String phone_contant
			,String fixed_phone_contant,String fax_contant,String qq_content,String e_mail_content,String address_contant){
		Record record = new Record();
		record.set("id", id);
		record.set("name_content", name_content);
		record.set("post_contant", post_contant);
		record.set("phone_contant", phone_contant);
		record.set("fixed_phone_contant", fixed_phone_contant);
		record.set("fax_contant", fax_contant);
		record.set("qq_content", qq_content);
		record.set("e_mail_content", e_mail_content);
		record.set("address_contant", address_contant);
		return Db.update("t_base_customer", record);
	}
	
	/**
	 * tab3
	 * @desc 保存新增数据
	 * @author xuhui
	 */
	public static boolean saveTabThree(Integer id,String company_information,String taxpayer_distinguish_information
			,String blankname_information,String blankaccount_infomation,String address_information,String phone_information){
		Record record = new Record();
		record.set("id", id);
		record.set("company_information", company_information);
		record.set("taxpayer_distinguish_information", taxpayer_distinguish_information);
		record.set("blankname_information", blankname_information);
		record.set("blankaccount_infomation", blankaccount_infomation);
		record.set("address_information", address_information);
		record.set("phone_information", phone_information);
		return Db.update("t_base_customer", record);
	}
	
	/**
	 * @desc 下载文件
	 * @author xuhui
	 * @throws IOException 
	 */
	public static void downloadFile(HttpServletResponse response, Integer id,String filename) throws IOException{
		Record file = Db.findById("t_base_customer", id);
		String fileName = file.getStr(filename);
		System.out.println("fileName:"+fileName);
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
}
