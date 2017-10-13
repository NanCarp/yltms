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
import yongle.model.CustomerSettle;
import yongle.model.ShipSettle;
import yongle.utils.ResponseObj;
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
	    Record r = ShipFreightService.getRecordById(id);
	    setAttr("record", r);
		render("shipfreight_detail.html");
	}
	
	/**
	 * @desc 保存船舶运费结算清单
	 * @author xuhui
	 */
	public void save(){
		ShipSettle record = getModel(ShipSettle.class, "");
        boolean flag = record.update();
        renderJson(flag);
	}
}
