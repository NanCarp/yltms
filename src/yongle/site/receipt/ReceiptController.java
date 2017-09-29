package yongle.site.receipt;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import yongle.interceptor.ManageInterceptor;
import yongle.model.DispatchShip;
import yongle.utils.ResponseObj;

/**
 * @ClassName: WaterwayFreightController.java
 * @Description: 回单录入
 * @author: LiYu
 * @date: 2017年8月29日下午2:17:52
 * @version: 1.0 版本初成
 */
@Before(ManageInterceptor.class)
public class ReceiptController extends Controller {
    /** 
    * @Title: index 
    * @Description: 列表页面
    * @author liyu
    */
    public void index() {
        render("receipt.html");
    }
    
    /** 
    * @Title: getJson 
    * @Description: 数据
    * @author liyu
    */
    public void getJson(){
        String plan_no = getPara("plan_no");
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
        
        Page<Record> page = ReceiptService.getDataPages(pageindex, pagelimit, 
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
        Record r = ReceiptService.getRecordById(id);
        setAttr("record", r);
        
        render("receipt_edit.html");
    }
    
    /** 
    * @Title: save 
    * @Description: 保存
    * @author liyu
    */
    public void save() {
        ResponseObj res = new ResponseObj();
        DispatchShip record = getModel(DispatchShip.class, "");
        //获取实际运费价格，收货数量 乘以 运价
        BigDecimal freight_price = record.getBigDecimal("freight_price");//运价
        BigDecimal received_quantity = record.getBigDecimal("received_quantity");//收货数量
        BigDecimal ship_freight = freight_price.multiply(received_quantity);//运费
        //System.out.println(freight_price);
        //System.out.println(received_quantity);
        //System.out.println(ship_freight);
        record.set("ship_freight", ship_freight);        
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
        Record r = ReceiptService.getRecordById(id);
        setAttr("record", r);
        render("receipt_detail.html");
    }
    
}
