package yongle.insidejob.contract;

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
import com.jfinal.template.stat.ast.Output;

import yongle.utils.EncodeUtil;

/**
 * @ClassName: ContractController.java
 * @Description: 内勤管理-合同管理
 * @author: LiYu
 * @date: 2017年8月28日上午11:24:43
 * @version: 1.0 版本初成
 */
public class ContractService {

	/**
	 * @desc 展示清单页数据
	 * @author xuhui
	 */
	public static Page<Record> getJson(Integer pageNumber,Integer pageSize,String company_name
			,String art_no,String dispatch_port,String consignee){
		String sql = " from t_insidejob_contract where 1=1";
		if(company_name!=null&&company_name!=""){
			sql +=" and customer like '%"+company_name+"%'";
		}
		if(art_no!=null&&art_no!=""){
			sql +=" and art_no like '%"+art_no+"%'";
		}
		if(dispatch_port!=null&&dispatch_port!=""){
			sql +=" and dispatch_port like '%"+dispatch_port+"%'";
		}
		if(consignee!=null&&consignee!=""){
			sql +=" and consignee like '%"+consignee+"%'";
		}
		sql += " order by id desc";
		return Db.paginate(pageNumber, pageSize, "select *",sql);
	}
	
	/**
	 * @desc 下载文件
	 * @author xuhui
	 * @throws IOException 
	 */
	public static void downloadFile(HttpServletResponse response, Integer id) throws IOException{
		Record file = Db.findById("t_insidejob_contract", id);
		String fileName = file.getStr("attachment");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + EncodeUtil.toUtf8String(fileName));
		String fullFileName = PropKit.get("filepath_contant")+id+"/"+fileName;
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
