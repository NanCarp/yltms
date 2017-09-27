package yongle.system.notice;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Record;

import yongle.interceptor.ManageInterceptor;

/**
 * @ClassName: NoticeController
 * @Description: 系统管理_提醒通知
 * @author: xuhui
 * @date: 2017年9月18日下午8:56:06
 * @version: 1.0 版本初成
 */
@Before(ManageInterceptor.class)
public class NoticeController extends Controller{

	/**
	 * @author xuhui
	 * @desc 页面展示
	 */
	public void index(){
		String title = getPara("title");
		String type = getPara("type");
		setAttr("title", title);
		setAttr("type", type);
		
		List<Record> noticeList = NoticeService.getAllNotice(title,type);
		setAttr("noticeList", noticeList);
		render("notice.html");
	}
	
	/**
	 * @desc 新增以及修改
	 * @author xuhui
	 */
	public void edit(){
		Integer id = getParaToInt();
		if(id!=null){
			Record record = Db.findById("t_notice", id);
			setAttr("record", record);
		}
		render("notice_detail.html");
	}
	
	/**
	 * @desc 保存新增以及修改数据
	 * @author xuhui
	 */
	public void save(){
		boolean flag = false;
		Record admin = (Record) getSession().getAttribute("admin");
		String publisher = admin.getStr("account");	
		Record record = new Record();
		Integer id = getParaToInt("id");
		record.set("title", getPara("title"));
		record.set("type", "通知公告");
		record.set("content", getPara("content"));
		record.set("publisher", publisher);
		record.set("publish_time", new Date());
		if(id!=null){
			record.set("id", id);
			flag = Db.update("t_notice",record);
		}else{
			flag = Db.save("t_notice",record);
		}
		renderJson(flag);
	}
	
	
	/**
	 * @desc 查看详情
	 * @author xuhui
	 */
	public void seeDatil(){
		Integer id = getParaToInt();
		if(id!=null){
			Record record = Db.findById("t_notice", id);
			setAttr("record", record);
		}
		render("notice_content.html");
	}
	
	/**
	 * @desc 删除选中id
	 * @author xuhui
	 */
	public void delete(){
		boolean flag = false;//判断是否删除成功	
		flag = Db.tx(new IAtom() {
			//判断单条数据是否删除成功
			boolean result = false;
			@Override
			public boolean run() throws SQLException {
				
				String ids = getPara(0);
				String[] deleteid = ids.split(",");
				for(String id:deleteid){
					result = Db.deleteById("t_notice", id);
					if(!result){
						return result;
					}
				}
				return result;
			}
		});
		renderJson(flag);
	}
}
