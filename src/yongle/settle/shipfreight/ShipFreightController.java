package yongle.settle.shipfreight;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import yongle.interceptor.ManageInterceptor;
import yongle.settle.customerfreight.CustomerFreightService;
/**
 * @ClassName: ShipFreightController.java
 * @Description: 结算审核表-船舶运费结算清单
 * @author: xuhui
 * @date: 2017年9月5日上午10:55:00
 * @version: 1.0 版本初成
 */
@Before(ManageInterceptor.class)
public class ShipFreightController extends Controller {

	/**
	 * @author xuhui
	 * @desc 展示清单页数据
	 */
	public void index(){
		render("shipfreight.html");
	}
	
  /** 
    * @desc 展示清单页
    * @author xuhui
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
        Page<Record> page = ShipFreightService.getDataPages(pageindex, pagelimit, plan_no);
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("total", page.getTotalRow());
        map.put("rows", page.getList());
        renderJson(map);
    }
	
	/**
	 * @desc 结算清单编辑
	 * @author xuhui
	 */
	public void getEdit(){
		Integer id = getParaToInt();
		System.out.println("id:"+id);
	    Record r = ShipFreightService.getRecordById(id);
	    setAttr("record", r);
		render("shipfreight_detail.html");
	}
	
	/**
	 * @desc 保存船舶运费结算清单
	 * @author xuhui
	 */
	public void save(){
		//判断是否保存成功
		boolean flag = false;
		Integer id = getParaToInt("id");
		System.out.println(id);
		Integer dispatch_ship_id = getParaToInt("dispatch_ship_id");
		Long port_construction_fee = getParaToLong("port_construction_fee");//港建费
		String demurrage_days = getPara("demurrage_days");//滞期天数
		Integer extended_days = getParaToInt("extended_days");//超期天数
		Long demurrage_charges = getParaToLong("demurrage_charges");//滞港费
		String other_charges = getPara("other_charges");//其他费用
		Long payable_amount = getParaToLong("payable_amount");//应收金额
		Record record = new Record();
		record.set("id",id);
		record.set("dispatch_ship_id", dispatch_ship_id);
		record.set("port_construction_fee", port_construction_fee);
		record.set("demurrage_days", demurrage_days);
		record.set("extended_days", extended_days);
		record.set("demurrage_charges", demurrage_charges);
		record.set("other_charges", other_charges);
		record.set("payable_amount", payable_amount);
		flag = Db.update("t_ship_settle", record);
		renderJson(flag);
	}
}
