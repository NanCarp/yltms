package yongle.insidejob.contract;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.upload.UploadFile;


/**
 * @ClassName: ContractController.java
 * @Description: 内勤管理-合同管理
 * @author: LiYu
 * @date: 2017年8月28日上午11:24:43
 * @version: 1.0 版本初成
 */

public class ContractController extends Controller {
    /** 
    * @Title: index 
    * @Description: 合同管理页面
    * @author liyu
    */
    public void index() {
        render("contract.html");
    }
    
    /**
	 * @desc 展示清单页数据,同时加载成品和半成品
	 * @author xuhui
	 */
	public void getJson(){	
		String company_name = getPara("company_name");
		String art_no = getPara("art_no");
		String dispatch_port = getPara("dispatch_port");
		String consignee = getPara("consignee");
    	Integer	pageindex = 0;
    	Integer pagelimit = getParaToInt("limit")==null? 12 :getParaToInt("limit");
    	Integer offset = getParaToInt("offset")==null?0:getParaToInt("offset");
    	if(offset!=0){
    		pageindex = offset/pagelimit;
    	}
    	pageindex += 1;
    	Map<String, Object> map = new HashMap<String,Object>(); 	
    	List<Record> dictionaryList = ContractService.getJson(pageindex, pagelimit,company_name,art_no,dispatch_port,consignee).getList();
    	map.put("rows", dictionaryList);
    	map.put("total",ContractService.getJson(pageindex, pagelimit,company_name,art_no,dispatch_port,consignee).getTotalRow());
    	renderJson(map);	
	}
    
    /** 
    * @Title: getRecord 
    * @Description: 单条记录信息
    * @author liyu
    */
    public void getRecord() {
    	Integer id = getParaToInt(0);
    	Record record = Db.findById("t_insidejob_contract", id);
    	setAttr("record", record);
        render("contract_detail.html");
    }
    
    /**
     * @desc 保存合同管理
     * @author xuhui
     */
    public void saveContract(){
    	boolean flag = false;
    	String fname = "";
    	UploadFile uf = getFile("file");
    	Record record = new Record();
    	Integer pid = getParaToInt("id");
    	record.set("customer",getPara("customer"));
    	record.set("art_no",getPara("art_no"));
    	record.set("dispatch_port",getPara("dispatch_port"));
    	record.set("consignee",getPara("consignee"));
    	record.set("freight",getPara("freight"));
    	record.set("quantity",getPara("quantity"));
    	if(uf!=null){
    		fname = uf.getOriginalFileName();
    		record.set("attachment", fname);
    	}
    	if(pid!=null){
    		Long id = pid.longValue();
    		record.set("id", id);
    		flag = Db.update("t_insidejob_contract",record);
    	}else{
    		flag = Db.save("t_insidejob_contract", record);
    	}
    	if(uf!=null){
    		//创建对应的文件夹目录
        	File newFile = new File(PropKit.get("filepath_contant")+record.getLong("id"));
        	newFile.mkdirs();
        	uf.getFile().renameTo(new File(PropKit.get("filepath_contant")+record.getLong("id")+"/"+fname));
    	}
    	renderJson(flag);
    }
    
    /**
     * @desc 查看文件
     * @author xuhui
     * @throws IOException 
     */
    public void watchFile() throws IOException{
    	Integer id = getParaToInt();
		ContractService.downloadFile(getResponse(), id);
		renderNull();
    }

    /**
     * @desc 删除操作
     * @author xuhui
     */
    public void delete(){
    	boolean flag = false;//判断是否删除成功
    	Integer id = getParaToInt(0);
    	//删除该id下对应的文件
    	File file = new File(PropKit.get("filepath_contant")+id);
    	deleteDir(file);
    	flag = Db.deleteById("t_insidejob_contract", id);
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
}
