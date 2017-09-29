package yongle.settle.customerfreight;

import java.util.HashMap;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import yongle.interceptor.ManageInterceptor;
import yongle.model.CustomerSettle;
import yongle.utils.ResponseObj;

/**
 * @ClassName: CustomerFreightController.java
 * @Description: 客户结算管理-待结算
 * @author: LiYu
 * @date: 2017年9月5日上午10:45:55
 * @version: 1.0 版本初成
 */
@Before(ManageInterceptor.class)
public class CustomerFreightController extends Controller {
    /** 
    * @Title: index 
    * @Description: 列表页面
    * @author liyu
    */
    public void index() {
        render("customer_freight.html");
    }
    
    /** 
    * @Title: getJson 
    * @Description: 数据
    * @author liyu
    */
    public void getJson(){
        String plan_no = getPara("plan_no");
        
        Integer pageindex = 0; // 页码
        Integer pagelimit = getParaToInt("limit")==null? 12 :getParaToInt("limit"); // 每页数据条数
        Integer offset = getParaToInt("offset")==null?0:getParaToInt("offset");
        if(offset!=0){
            pageindex = offset/pagelimit;
        }
        pageindex += 1;
        
        Page<Record> page = CustomerFreightService.getDataPages(pageindex, pagelimit, plan_no);
        
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
        Record r = CustomerFreightService.getRecordById(id);
        setAttr("record", r);
        
        render("customer_freight_edit.html");
    }
    
    /** 
    * @Title: save 
    * @Description: 保存
    * @author liyu
    */
    public void save() {
        ResponseObj res = new ResponseObj();
        CustomerSettle record = getModel(CustomerSettle.class, "");
        boolean b = record.update();
        res.setCode(b ? ResponseObj.OK : ResponseObj.FAILED);
        res.setMsg(b ? ResponseObj.SAVE_SUCCESS : ResponseObj.SAVE_FAILED);  
        renderJson(res);
    }
    
}
