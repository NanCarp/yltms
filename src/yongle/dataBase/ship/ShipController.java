package yongle.dataBase.ship;

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
 * @ClassName: ShipController.java
 * @Description: 船舶信息管理
 * @author: xuhui
 * @date: 2017年8月29日下午17:40:05
 * @version: 1.0 版本初成
 */
public class ShipController extends Controller{

	/**
	 * @desc 展示清单页面
	 * @author xuhui
	 */
	public void index(){
		render("ship.html");
	}
	
	/**
	 * @desc 展示清单页数据,同时加载成品和半成品
	 * @author xuhui
	 */
	public void getJson(){
		String shipowname = getPara("shipowname");
		String ship_name = getPara("ship_name");
    	Integer	pageindex = 0;
    	Integer pagelimit = getParaToInt("limit")==null? 12 :getParaToInt("limit");
    	Integer offset = getParaToInt("offset")==null?0:getParaToInt("offset");
    	if(offset!=0){
    		pageindex = offset/pagelimit;
    	}
    	pageindex += 1;
    	Map<String, Object> map = new HashMap<String,Object>(); 	
    	List<Record> dictionaryList = ShipService.getJson(pageindex, pagelimit,shipowname,ship_name).getList();
    	map.put("rows", dictionaryList);
    	map.put("total",ShipService.getJson(pageindex, pagelimit,shipowname,ship_name).getTotalRow());
    	renderJson(map);	
	}
	
	/**
	 * @desc tab页新增
	 * @author xuhui
	 */
	public void getTab(){
		Integer id = getParaToInt("id");
		System.out.println(id);
		if(id!=null){
			Record record = Db.findById("t_base_ship", id);
			setAttr("im", record);
			String sql = "select * from t_base_ship_crew  where t_base_ship_id = "+id+" order by id desc";
			List<Record> list = Db.find(sql);
			setAttr("crews", list);
		}
		render("ship_detail.html");
	}
	
	/**
	 * tab1
	 * @desc 保存新增数据
	 * @author xuhui
	 */
	public void saveTabOne(){
		Map<String, Object> map = new HashMap<String,Object>();//返回数据map
		boolean is_success = false;//判断数据是否保存成功
		String shipowname = getPara("shipowname");
		String ship_name = getPara("ship_name");
		String ID_number = getPara("ID_number");
		String phone_num = getPara("phone_num");
		String ship_ton = getPara("ship_ton");
		String grade_sail = getPara("grade_sail");
		String business_route = getPara("business_route");
		Integer flagId = ShipService.saveTabOne(shipowname, ship_name
				, ID_number, phone_num, ship_ton, grade_sail, business_route);
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
		String open_bank = getPara("open_bank");
		String name_bank = getPara("name_bank");
		String card_bank = getPara("card_bank");
		String updatetime_bank = getPara("updatetime_bank");
		boolean flag = ShipService.saveTabTwo(id, open_bank, name_bank, card_bank, updatetime_bank);
		renderJson(flag);
	}

	/**
	 * tab3
	 * @desc 保存船证件信息
	 * @author xuhui
	 */
	public void saveTabThree(){
		//判断数据是否保存成功
		boolean flag = true;
		UploadFile visa_sheetfile = getFile("visa_sheet");
		UploadFile ship_inspectionfile = getFile("ship_inspection");
		UploadFile loadingfile = getFile("loading");
		UploadFile operationfile = getFile("operation");		
		Integer id = getParaToInt("id");
		//创建该id下对应的文件夹
		File file = new File(PropKit.get("filepath_ship")+"/"+id);
		//判断该文件夹是否存在，不存在则创建该文件夹
		if(!file.exists()){
			file.mkdirs();
		}
		String visa_sheet = visa_sheetfile.getOriginalFileName();
		String ship_inspection = ship_inspectionfile.getOriginalFileName();
		String loading = loadingfile.getOriginalFileName();
		String operation = operationfile.getOriginalFileName();
		//保存文件
		visa_sheetfile.getFile().renameTo(new File(PropKit.get("filepath_ship")+"/"+id+"/"+visa_sheet));
		ship_inspectionfile.getFile().renameTo(new File(PropKit.get("filepath_ship")+"/"+id+"/"+ship_inspection));
		loadingfile.getFile().renameTo(new File(PropKit.get("filepath_ship")+"/"+id+"/"+loading));
		operationfile.getFile().renameTo(new File(PropKit.get("filepath_ship")+"/"+id+"/"+operation));
		//保存信息
		Record record = new Record();
		record.set("visa_sheet", visa_sheet);
		record.set("id", id);
		record.set("ship_inspection", ship_inspection);
		record.set("loading", loading);
		record.set("operation", operation);
		flag = Db.update("t_base_ship", record);
		renderJson(flag);
	}
	
	/**
	 * @desc 查看船舶信息管理明细
	 * @author xuhui
	 */
	public void getInfo(){
		Integer id = getParaToInt("id");
		System.out.println(id);
		if(id!=null){
			Record record = Db.findById("t_base_ship", id);
			setAttr("im", record);
			String sql = "select * from t_base_ship_crew  where t_base_ship_id = "+id+" order by id desc";
			List<Record> list = Db.find(sql);
			setAttr("crews", list);
		}
		render("ship_info.html");
	}
	
	/**
	 * @desc 删除操作
	 * @author xuhui
	 */
	public void delete(){
		//判断是否删除成功
		boolean flag = false;
		Integer id = getParaToInt(0);
		//删除船证件信息
		deleteDir(new File(PropKit.get("filepath_ship")+id));
		//删除对应的船员信息
		String sqlPara ="select id from t_base_ship_crew where t_base_ship_id ="+id;

		List<Record> list = Db.find(sqlPara);
		for(Record r:list){
			System.out.println(r.getInt("id"));
			deleteDir(new File(PropKit.get("filepath_shipCrew")+r.getInt("id")));
		}
		flag = Db.deleteById("t_base_ship", id);
		String sql ="delete from t_base_ship_crew where t_base_ship_id = "+id;
		Db.update(sql);
		renderJson(flag);
	}
	
	/**
	 * @desc 打开新增船员信息
	 * @author xuhui
	 */
	public void editCrew(){
		//船基础信息id
		Integer newid = getParaToInt("newid");
		//船员id
		Integer crewid = getParaToInt("id");
		//船id
		setAttr("shipid", newid);
		if(crewid!=null){
			setAttr("crewid", crewid);
			String sql = "select * from t_base_ship_crew where id = "+crewid;
			Record record = Db.findFirst(sql);
			setAttr("im", record);
		}
		render("ship_detail_crew.html");
	}
	
	/**
	 * @desc 保存船员信息
	 * @author xuhui
	 */
	public void saveCrew(){
		//判断文件是否保存成功
		boolean flag = false;
		UploadFile file = getFile("file");
		//文件名字
		String crew_certificate = file.getOriginalFileName();
		String crew_name = getPara("crew_name");
		String ID_card_certificate = getPara("ID_card_certificate");
		//船员基础信息id
		Integer shipid = getParaToInt("shipid");
		//船员id
		Integer crewid = getParaToInt("crewid");

		//保存信息
		Record record = new Record();
		record.set("t_base_ship_id", shipid);
		record.set("crew_name", crew_name);
		record.set("ID_card_certificate", ID_card_certificate);
		record.set("crew_certificate", crew_certificate);
		if(crewid!=null){
			
		}else{
			flag = Db.save("t_base_ship_crew", record);
		}
		//保存成功后保存文件
		if(flag){
			File filepath = new File(PropKit.get("filepath_shipCrew")+"/"+record.getLong("id"));
			if(!filepath.exists()){
				filepath.mkdirs();
			}
			file.getFile().renameTo(new File(PropKit.get("filepath_shipCrew")+record.getLong("id")+"/"+crew_certificate));
		}
		renderJson(flag);
	}
	
	/**
	 * @desc 展示船员信息
	 * @author xuhui
	 */
	public void showCrew(){
		//船基础信息id
		Integer id = getParaToInt("newid");
		String sql = "select * from t_base_ship_crew  where t_base_ship_id = "+id+" order by id desc";
		List<Record> list = Db.find(sql);
		renderJson(list);
	}
	
	/**
	 * @desc 删除船员信息
	 * @author xuhui
	 */
	public void deleteCrew(){
		//判断是否删除成功
		boolean flag = false;
		//需要删除的船员id
		Integer id = getParaToInt(0);
		//删除船员对应的船员证信息
		deleteDir(new File(PropKit.get("filepath_shipCrew")+"/"+id));
		flag = Db.deleteById("t_base_ship_crew", id);
		renderJson(flag);
	}
	
	/**
	 * @desc 船证书查看
	 * @author xuhui
	 * @throws IOException 
	 */
	public void watchFile() throws IOException{
		Integer id = getParaToInt("id");
		String filedata = getPara("filename");
		//文件名
		String sql = "select "+filedata+" from t_base_ship where id ="+id;
		String filename = Db.queryStr(sql); 
		ShipService.downloadFile(getResponse(), id, filename);
		renderNull();
	}
	
	/**
	 * @desc 船员证件查看
	 * @author xuhui
	 * @throws IOException 
	 */
	public void watchCrewFile() throws IOException{
		System.out.println("watchCrewFile");
		Integer id = getParaToInt("id");
		String filename = getPara("filename");
		ShipService.downloadCrewFile(getResponse(), id, filename);
		renderNull();
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
