package yongle.settlecorrectapplication.settleapplication;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import yongle.model.SettleApply;
import yongle.utils.ResponseObj;

/**
 * @ClassName: SettleApplicationController.java
 * @Description: 结算批改申请-结算批改申请表
 * @author: xuhui
 * @date: 2017年9月5日上午8:22:01
 * @version: 1.0 版本初成
 */
public class SettleApplicationController extends Controller {

	public void index(){
		render("settleapplication.html");
	}

    /** 
    * @Title: getJson 
    * @Description: 数据
    * @author liyu
    */
    public void getJson(){
        String plan_no = getPara("plan_no");
        String apply_begin = getPara("apply_begin");
        String apply_end = getPara("apply_end");
        String dispatcher = getPara("dispatcher");
        
        Integer pageindex = 0; // 页码
        Integer pagelimit = getParaToInt("limit")==null? 12 :getParaToInt("limit"); // 每页数据条数
        Integer offset = getParaToInt("offset")==null?0:getParaToInt("offset");
        if(offset!=0){
            pageindex = offset/pagelimit;
        }
        pageindex += 1;
        
        Page<Record> page = SettleApplicationService.getDataPages(pageindex, pagelimit, 
                plan_no, apply_begin, apply_end, dispatcher);
        
        Map<String, Object> map = new HashMap<String,Object>();
        
        map.put("total", page.getTotalRow());
        map.put("rows", page.getList());
        
        renderJson(map);
    }
    
    /** 
    * @Title: edit 
    * @Description: 新增、编辑页面
    * @author liyu
    */
    public void edit() {
        Integer id = getParaToInt();
        if (id != null) { // 编辑
            Record r = Db.findById("t_settle_apply", id);
            setAttr("record", r);
        }
        
        render("settleapplication_edit.html");
    }
    
    /** 
    * @Title: save 
    * @Description: 保存
    * @author liyu
    */
    public void save() {
        ResponseObj res = new ResponseObj(); // 返回信息
        SettleApply r = getModel(SettleApply.class, "");
        Record user = getSessionAttr("admin");
        user = Db.findById("t_user", 1); // TODO
        String user_name = user.getStr("user_name");
        r.setDispatcher(user_name); // 调度员
        Date now = new Date();
        r.setApplyDate(now);// 申请时间
        
        Integer id = r.getId();
        boolean result = false;
        if (id == null) { // 新增
            result = r.save();
        } else { // 编辑
            result = r.update();
        }
        res.setCode(result ? ResponseObj.OK : ResponseObj.FAILED);
        res.setMsg(result ? ResponseObj.SAVE_SUCCESS : ResponseObj.SAVE_FAILED);
        
        renderJson(res);
    }
    
    /** 
    * @Title: delete 
    * @Description: 删除
    * @author liyu
    */
    public void delete() {
        ResponseObj res = new ResponseObj(); // 返回信息
        Integer id = getParaToInt();
        boolean result = SettleApplicationService.deleteById(id);
        res.setCode(result ? ResponseObj.OK : ResponseObj.FAILED);
        res.setMsg(result ? "删除成功" : "删除失败");
        
        renderJson(res);
    }
    
    /** 
    * @Title: review 
    * @Description: TODO(这里用一句话描述这个方法的作用)  void
    * @author liyu
    */
    public void review() {
        ResponseObj res = new ResponseObj(); // 返回信息
        Integer id = getParaToInt(0);
        Record record = new Record();
        record.set("id", id);
        Record dbRecord = Db.findById("t_settle_apply", id);
        //判断审核状态 0、待审核，1、已审核，2、取消审核
        Integer value = dbRecord.getInt("manager_review");
        if(value==0||value==2){
            record.set("manager_review", 1);
            boolean result = Db.update("t_settle_apply", record);
            res.setCode(result ? ResponseObj.OK : ResponseObj.FAILED);
            res.setMsg(result ? "完成审核" : "审核失败");
        }else{
            record.set("manager_review", 2);
            boolean result = Db.update("t_settle_apply", record);
            res.setCode(result ? ResponseObj.OK : ResponseObj.FAILED);
            res.setMsg(result ? "取消审核成功" : "取消审核失败");
        }
        
        renderJson(res);
    }
    
    /**
     * @desc 获取计划单审核状态
     * @author liyu
     */
    public void getExamineState(){
        boolean flag = false;
        Integer id = getParaToInt(0);
        Record r = Db.findById("t_settle_apply", id);
        Integer value = r.getInt("manager_review");
        //判断审核状态 0、待审核，1、已审核，2、取消审核
        if(value == 1){
            flag = true;
        }
        renderJson(flag);
    }
    
    /** 
    * @Title: getInfoByPlanNo 
    * @Description: 根据计划号获取相关信息
    * @author liyu
    */
    public void getInfoByPlanNo() {
        ResponseObj res = new ResponseObj(); // 返回信息
        String plan_no = getPara("plan_no");
        Record r = Db.findFirst("SELECT * FROM t_dispatch WHERE plan_no = ?", plan_no);
        res.setCode(r != null ? ResponseObj.OK : ResponseObj.FAILED);
        res.setData(r);
        
        renderJson(res);
    }
    
    /** 
    * @Title: getInfoByPlanNo 
    * @Description: 根据船名获取相关信息
    * @author liyu
    */
    public void getInfoByShipName() {
        ResponseObj res = new ResponseObj(); // 返回信息
        String plan_no = getPara("plan_no");
        String ship_name = getPara("ship_name");
        Record r = Db.findFirst("SELECT *  "
                + " FROM `t_dispatch_ship` AS a "
                + " LEFT JOIN t_dispatch AS b "
                + " ON a.dispatch_id = b.id "
                + " WHERE plan_no = ? "
                + " AND ship_name = ? ", plan_no, ship_name);
        res.setCode(r != null ? ResponseObj.OK : ResponseObj.FAILED);
        res.setData(r);
        
        renderJson(res);
    }
}
