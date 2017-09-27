package yongle.site.handoversite;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import yongle.dispatchmanage.handover.HandoverService;
import yongle.interceptor.ManageInterceptor;
import yongle.utils.ResponseObj;

/**
 * @ClassName: HandoverSiteController.java
 * @Description: 调度交接
 * @author: LiYu
 * @date: 2017年8月31日上午8:20:59
 * @version: 1.0 版本初成
 */
@Before(ManageInterceptor.class)
public class HandoverSiteController extends Controller {
    
    private ResponseObj msg = new ResponseObj();
    
    /** 
    * @Title: index 
    * @Description: 调度交接表页面
    * @author liyu
    */
    public void index() {
        render("handover_site.html");
    }
    
    /** 
    * @Title: getJson 
    * @Description: 数据
    * @author liyu
    */
    public void getJson(){
        String plan_no = getPara("plan_no");
        String goods_name = getPara("goods_name");
        String entry_start = getPara("entry_start");
        String entry_end = getPara("entry_end");
        String entry_man = getPara("entry_man");
        
        Integer pageindex = 0; // 页码
        Integer pagelimit = getParaToInt("limit")==null? 12 :getParaToInt("limit"); // 每页数据条数
        Integer offset = getParaToInt("offset")==null?0:getParaToInt("offset");
        if(offset!=0){
            pageindex = offset/pagelimit;
        }
        pageindex += 1;
        
        Page<Record> page = HandoverSiteService.getDataPages(pageindex, pagelimit, 
                plan_no, goods_name, entry_start, entry_end, entry_man);
        
        Map<String, Object> map = new HashMap<String,Object>();
        
        map.put("total", page.getTotalRow());
        map.put("rows", page.getList());
        
        renderJson(map);
    }
    
    /** 
    * @Title: getRecord 
    * @Description: 
    * @author liyu
    */
    public void getRecord() {
        Integer id = getParaToInt(); // 流向 id
        Record record = HandoverSiteService.getRecordById(id);
        setAttr("record", record);
        List<Record> recordList = Db.find("SELECT *,a.id FROM `t_dispatch_ship` AS a LEFT JOIN t_dispatch_detail AS b ON a.dispatch_detail_id = b.id WHERE dispatch_detail_id = ? ", id);
        setAttr("recordList", recordList);
        
        render("handover_site_detail.html");
    }
    
    /** 
     * @Title: edit
     * @Description: 编辑页面 
     * @author liyu
     */
     public void edit() {
         Integer id = getParaToInt(); // 流向 id
         Record record = HandoverSiteService.getRecordById(id);
         setAttr("record", record);
         List<Record> recordList = Db.find("SELECT *,a.id FROM `t_dispatch_ship` AS a LEFT JOIN t_dispatch_detail AS b ON a.dispatch_detail_id = b.id WHERE dispatch_detail_id = ? ", id);
         setAttr("recordList", recordList);
         
         render("handover_site_edit.html");
     }
    
    /** 
    * @Title: save 
    * @Description: 保存
    * @author liyu
    */
    public void save() {
        Integer dispatch_detail_id = getParaToInt("id"); // 流向 id
        String recordList = getPara("recordList"); // 驳船列表
        msg = HandoverSiteService.save(dispatch_detail_id, recordList);
        renderJson(msg);
    }
    
    /** 
    * @Title: export 
    * @Description: 导出 excel
    * @author liyu
    */
    public void export() {
        Integer id = getParaToInt();
        Record r = Db.findById("t_dispatch_detail", id);
        boolean result = HandoverService.export(getResponse(), r.getInt("plan_no_id"));
        
        if (result) {
            renderNull();
        } else {
            renderText("导出失败");
        }
    }
    
    public void print() {
        Integer id = getParaToInt();
        Integer plan_no_id = Db.findById("t_dispatch_detail", id).getInt("plan_no_id");
        List<Record> recordList = Db.find("SELECT * "
                + " FROM t_dispatch_ship AS a "
                + " LEFT JOIN t_dispatch_detail AS b "
                + " ON b.id = a.dispatch_detail_id "
                + " LEFT JOIN t_dispatch AS c "
                + " ON b.plan_no_id = c.id"
                + " WHERE c.id = ? ", plan_no_id);
        
        renderJson(recordList);
    }
}
