package yongle.notice.ship;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import yongle.dispatchmanage.handover.HandoverService;
import yongle.interceptor.ManageInterceptor;
import yongle.utils.ResponseObj;

/**
 * @ClassName: NoticeShipController.java
 * @Description:
 * @author: 
 * @date: 2017年9月21日下午5:05:01
 * @version: 1.0 版本初成
 */
@Before(ManageInterceptor.class)
public class NoticeShipController extends Controller{
    
    /** 
    * @Title: getRecord 
    * @Description: 获取提醒信息
    * @author 
    */
    public void getRecord() {
        Integer notice_id = getParaToInt(); // 提醒 id
        setAttr("notice_id", notice_id);
        Record ship = Db.findFirst("SELECT * FROM `t_notice_ship` WHERE notice_id = ? ", notice_id);
        Integer dispatch_detail_id = ship.getInt("dispatch_detail_id");
        
        Record record = HandoverService.getRecordById(dispatch_detail_id);
        setAttr("record", record);
        List<Record> recordList = Db.find("SELECT * FROM `t_notice_ship` WHERE notice_id = ? ", notice_id);
        setAttr("recordList", recordList);
        
        render("notice_ship.html");
    }
    
    /** 
    * @Title: confirm 
    * @Description: 同意超出指导价的运价
    * @author 
    */
    public void confirm() {
        ResponseObj res = new ResponseObj(); // 返回信息
        Integer dispatch_detail_id = getParaToInt("dispatch_detail_id");
        Integer notice_id = getParaToInt("notice_id");
        boolean result = NoticeShipService.confirm(notice_id, dispatch_detail_id);
        res.setCode(result ? ResponseObj.OK : ResponseObj.FAILED);
        res.setMsg(result ? "完成" : "失败");
        
        renderJson(res);
    }
    
    /**
     * @desc 打开结算批改申请
     * @author xuhui
     */
    public void settle(){
    	Integer notice_id = getParaToInt(); // 提醒 id
        Record r = Db.findById("t_settle_apply", notice_id);
        setAttr("record", r);
    	render("notice_application.html");
    }
    
    /** 
    * @Title: getWaybill 
    * @Description: 运单预付费提醒
    * @author 
    */
    public void getWaybill() {
        Integer notice_id = getParaToInt(); // 提醒 id
        setAttr("notice_id", notice_id);
        List<Record> recordList = Db.find("SELECT * FROM `t_notice_waybill` WHERE notice_id = ? ", notice_id);
        setAttr("recordList", recordList);
        
        render("notice_waybill.html");
    }
    
    /** 
    * @Title: confirmWaybill 
    * @Description: 确认已付款
    * @author 
    */
    public void confirmWaybill() {
        ResponseObj res = new ResponseObj(); // 返回信息
        Integer notice_id = getParaToInt("notice_id"); // 提醒 id
        // 提醒已审核
        Record r = Db.findById("t_notice", notice_id);
        r.set("review", 1); // 提醒确认
        boolean result = Db.update("t_notice", r);
        // 标记为已付款
        Record ship = Db.find("SELECT * FROM `t_notice_waybill` WHERE notice_id = ? ", notice_id).get(0);
        Integer dispatch_ship_id = ship.getInt("dispatch_ship_id");
        Record record = new Record();
        record.set("id", dispatch_ship_id);
        record.set("pay_status", 1);
        Db.update("t_dispatch_ship", record);
        
        res.setCode(result ? ResponseObj.OK : ResponseObj.FAILED);
        res.setMsg(result ? "完成" : "失败");
        
        renderJson(res);
    }
}
