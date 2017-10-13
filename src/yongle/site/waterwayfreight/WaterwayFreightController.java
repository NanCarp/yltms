package yongle.site.waterwayfreight;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import yongle.interceptor.ManageInterceptor;
import yongle.model.DispatchShip;
import yongle.utils.ResponseObj;

/**
 * @ClassName: WaterwayFreightController.java
 * @Description: 水路货物运输
 * @author: LiYu
 * @date: 2017年8月29日下午2:17:52
 * @version: 1.0 版本初成
 */
@Before(ManageInterceptor.class)
public class WaterwayFreightController extends Controller {
    /** 
    * @Title: index 
    * @Description: 列表页面
    * @author liyu
    */
    public void index() {
        Record admin = getSessionAttr("admin"); // 用户
        Integer rid = admin.getInt("role_id");
        String buttons = Db.queryStr("select button_ids from t_role_button where role_id = ?", rid);
        if(buttons.indexOf("103")!=-1){ // 审核权限
            setAttr("_review", 1);
        }
        if(buttons.indexOf("104")!=-1){ // 修改权限
            setAttr("_edit", 1);
        }

        render("waterway_freight.html");
    }
    
    /** 
    * @Title: getJson 
    * @Description: 数据
    * @author liyu
    */
    public void getJson(){
        String plan_no = getPara("plan_no"); // 计划号
        String ship_name = getPara("ship_name");
        String start_date = getPara("start_date");
        String end_date = getPara("end_date");
        
        Integer pageindex = 0; // 页码
        Integer pagelimit = getParaToInt("limit")==null? 12 :getParaToInt("limit"); // 每页数据条数
        Integer offset = getParaToInt("offset")==null?0:getParaToInt("offset");
        if(offset!=0){
            pageindex = offset/pagelimit;
        }
        pageindex += 1;
        
        Page<Record> page = WaterwayFreightService.getDataPages(pageindex, pagelimit, 
                plan_no, ship_name, start_date, end_date);
        
        Map<String, Object> map = new HashMap<String,Object>();
        
        map.put("total", page.getTotalRow());
        map.put("rows", page.getList());
        
        renderJson(map);
    }
    
    /** 
    * @Title: getRecord 
    * @Description: 编辑页面
    * @author liyu
    */
    public void getRecord() {
        Integer id = getParaToInt();
        Record r = WaterwayFreightService.getRecordById(id);
        setAttr("record", r);
        
        render("waterway_freight_edit.html");
    }
    
    /** 
    * @Title: save 
    * @Description: 保存
    * @author liyu
    */
    public void save() {
        ResponseObj res = new ResponseObj();
        DispatchShip record = getModel(DispatchShip.class, "");
        boolean b = record.update();
        res.setCode(b ? ResponseObj.OK : ResponseObj.FAILED);
        res.setMsg(b ? "保存成功" : "保存失败");      
        renderJson(res);
    }
    
    /** 
    * @Title: detail 
    * @Description: 查看详情
    * @author liyu
    */
    public void detail() {
        Integer id = getParaToInt();
        Record r = WaterwayFreightService.getRecordById(id);
        setAttr("record", r);
        render("waterway_freight_detail.html");
    }
    
    /** 
    * @Title: review 
    * @Description: 审核
    * @author 
    */
    public void review() {
        ResponseObj res = new ResponseObj();
        Integer id = getParaToInt(); // 货运单 id
        Record r = Db.findById("t_dispatch_ship", id);
        
        // 已审核，不能重复审核
        if (r.getInt("receipt_review") == 1) {
            res.setCode(ResponseObj.FAILED);
            res.setMsg("不能重复审核");
            renderJson(res);
            return;
        }
        
        // 信息未录入，不能审核
        BigDecimal quantity =  Db.queryBigDecimal("SELECT delivery_quantity FROM t_dispatch_ship WHERE id = ?", id);
        if (quantity == null) {
            res.setCode(ResponseObj.FAILED);
            res.setMsg("信息未录入，不能审核");
            renderJson(res);
            return;
        }
        
        
        // 更新审核状态
        r.set("receipt_review", 1);
        boolean result = Db.update("t_dispatch_ship", r);
        res.setCode(result ? ResponseObj.OK : ResponseObj.FAILED);
        res.setMsg(result ? "已审核" : "审核失败");
        
        // 生成提醒
        Record user = getSessionAttr("admin");
        WaterwayFreightService.createNotice(id, user);
        
        renderJson(res);
    }
}
