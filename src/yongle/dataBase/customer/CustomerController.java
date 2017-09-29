package yongle.dataBase.customer;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.upload.UploadFile;
import yongle.interceptor.ManageInterceptor;
/**
 * @ClassName: CustomerController.java
 * @Description: 客户信息管理
 * @author: xuhui
 * @date: 2017年8月29日下午16:40:05
 * @version: 1.0 版本初成
 */
@Before(ManageInterceptor.class)
public class CustomerController extends Controller{

	/**
	 * @desc 展示清单页
	 * @author xuhui
	 */
	public void index(){
		render("customer.html");
	}
	
	/**
	 * @desc 展示清单页数据,同时加载成品和半成品
	 * @author xuhui
	 */
	public void getJson(){
		String customer_name = getPara("customer_name");
		String customer_type = getPara("customer_type");
		String company_information = getPara("company_information");
		
    	Integer	pageindex = 0;
    	Integer pagelimit = getParaToInt("limit")==null? 12 :getParaToInt("limit");
    	Integer offset = getParaToInt("offset")==null?0:getParaToInt("offset");
    	if(offset!=0){
    		pageindex = offset/pagelimit;
    	}
    	pageindex += 1;
    	Map<String, Object> map = new HashMap<String,Object>(); 	
    	List<Record> dictionaryList = CustomerService.getJson(pageindex, pagelimit,customer_name,customer_type,company_information).getList();
    	map.put("rows", dictionaryList);
    	map.put("total",CustomerService.getJson(pageindex, pagelimit,customer_name,customer_type,company_information).getTotalRow());
    	renderJson(map);	
	}
	
	/**
	 * @desc tab页新增
	 * @author xuhui
	 */
	public void getTab(){
		Integer id = getParaToInt(0);
		if(id!=null){
			//id不为空，更具id查询数据
			Record record = Db.findById("t_base_customer", id);
			setAttr("record", record);
		}
		render("customer_detail.html");
	}
	
	/**
	 * @desc 查看客户信息管理明细
	 * @author xuhui
	 */
	public void getInfo(){
		render("customer_info.html");
	}
	
	/**
	 * tab1页面
	 * @desc 保存新增以及修改数据
	 * @author xuhui
	 */
	public void saveTabOne(){
		Map<String, Object> map = new HashMap<String,Object>();//返回数据map
		boolean is_success = false;//判断数据是否保存成功
		Integer id = getParaToInt("id");
		String customer_name = getPara("customer_name");
		String customer_type = getPara("customer_type");
		String variety_goods = getPara("variety_goods");
		String common_delivery_company = getPara("common_delivery_company");
		String common_receiving_company = getPara("common_receiving_company");
		String wharf_berth = getPara("wharf_berth");
		Integer flagId = CustomerService.saveTabOne(id,customer_name, customer_type
				, variety_goods, common_delivery_company, common_receiving_company, wharf_berth);
		if(flagId==0){
			map.put("is_success", is_success);
		}else{
			is_success = true;
			map.put("flagId",flagId);
			map.put("is_success", is_success);
		}
		renderJson(map);
	}
	
	/**
	 * tab2
	 * @desc 保存新增数据
	 * @author xuhui
	 */
	public void saveTabTwo(){
		Integer id = getParaToInt("id");
		String name_content = getPara("name_content");
		String post_contant = getPara("post_contant");
		String phone_contant = getPara("phone_contant");
		String fixed_phone_contant = getPara("fixed_phone_contant");
		String fax_contant = getPara("fax_contant");
		String qq_content = getPara("qq_content");
		String e_mail_content = getPara("e_mail_content");
		String address_contant = getPara("address_contant");
		boolean flag = CustomerService.saveTabTwo(id,name_content, post_contant, phone_contant
				, fixed_phone_contant,fax_contant,qq_content,e_mail_content,address_contant);
		renderJson(flag);
	}

	/**
	 * tab3
	 * @desc 保存新增数据
	 * @author xuhui
	 */
	public void saveTabThree(){
		Integer id = getParaToInt("id");
		String company_information = getPara("company_information");
		String taxpayer_distinguish_information = getPara("taxpayer_distinguish_information");
		String blankname_information = getPara("blankname_information");
		String blankaccount_infomation = getPara("blankaccount_infomation");
		String address_information = getPara("address_information");
		String phone_information = getPara("phone_information");
		boolean flag = CustomerService.saveTabThree(id, company_information, taxpayer_distinguish_information, blankname_information
				,blankaccount_infomation,address_information,phone_information);
		renderJson(flag);
	}
	
	/**
	 * tab4
	 * @desc 保存数据
	 * @author xuhui
	 */
	public void saveTabFour(){
		//判断问价是否保存成功
		boolean flag = false;
		//获取文件
		UploadFile license_copy_commercefile = getFile("license_copy_commerce");
		UploadFile registration_commercefile = getFile("registration_commerce");
		UploadFile organization_commercefile = getFile("organization_commerce");
		//获取id，需要放在文件信息下方
		Integer id = getParaToInt("id");
		//保存数据
		Record record = new Record();
		//根据id创建文件夹
		File file = new File(PropKit.get("filepath_commerce")+"/"+id);
		if(!file.exists()){
			file.mkdirs();
		}
		if(license_copy_commercefile!=null){
			//获取文件名
			String license_copy_commerce = license_copy_commercefile.getOriginalFileName();
			//保存文件
			license_copy_commercefile.getFile().renameTo(new File (PropKit.get("filepath_commerce")+"/"+id+"/"+license_copy_commerce));
			record.set("license_copy_commerce", license_copy_commerce);
		}
		if(registration_commercefile!=null){
			String registration_commerce = registration_commercefile.getOriginalFileName();
			registration_commercefile.getFile().renameTo(new File (PropKit.get("filepath_commerce")+"/"+id+"/"+registration_commerce));
			record.set("registration_commerce", registration_commerce);
		}
		if(organization_commercefile!=null){
			String organization_commerce = organization_commercefile.getOriginalFileName();
			organization_commercefile.getFile().renameTo(new File (PropKit.get("filepath_commerce")+"/"+id+"/"+organization_commerce));
			record.set("organization_commerce", organization_commerce);
		}	
		record.set("id", id);
		flag = Db.update("t_base_customer",record);
		renderJson(flag);
	}
	
	
	
	/**
	 * @desc 删除操作
	 * @author xuhui
	 */
	public void delete(){
		//判断是否删除成功
		boolean flag = false;
		Integer id = getParaToInt(0);
		flag = Db.deleteById("t_base_customer", id);
		deleteDir(new File(PropKit.get("filepath_commerce")+"/"+id));
		renderJson(flag);
	}
	
	 /**
     * @desc 递归方法删除目录文件下所以文件
     * @author xuhui
     */
    public boolean deleteDir(File dir){
    	if(dir.isDirectory()){
    		String[] children = dir.list();
    		//递归删除目录中的子目录
    		for(int i=0;i<children.length;i++){
    			boolean success = deleteDir(new File(dir,children[i]));
    			if(!success){
    				return false;
    			}
    		}
    	}
    	return dir.delete();
    }
    
    
    /**
     * @desc 查看明细
     * @author mxuhui
     */
    public void seeDetail(){
    	Integer id = getParaToInt(0);
		if(id!=null){
			//id不为空，更具id查询数据
			Record record = Db.findById("t_base_customer", id);
			setAttr("record", record);
		}
    	render("customer_info.html");
    }
    
    
    /**
     * @desc 查看文件
     * @author xuhui
     * @throws IOException 
     */
    public void watchFile() throws IOException{
    	Integer id = getParaToInt("id");
    	String filename = getPara("filename");
    	CustomerService.downloadFile(getResponse(), id,filename);
		renderNull();
    }
}
