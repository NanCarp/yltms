package yongle.dataBase.port;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import yongle.model.BasePort;
import yongle.utils.ResponseObj;

/**
 * @ClassName: PortController.java
 * @Description:
 * @author: LiYu
 * @date: 2017年8月29日下午4:51:54
 * @version: 1.0 版本初成
 */
public class PortController extends Controller {
    private ResponseObj msg = new ResponseObj();
    
    /** 
    * @Title: index 
    * @Description: 港口页面
    * @author liyu
    */
    public void index() {
        render("port.html");
    }
    
    /** 
    * @Title: getJson 
    * @Description: 数据
    * @author liyu
    */
    public void getJson() {
        String port = getPara("port"); // 港口名称
        
        Integer pageindex = 0; // 页码
        Integer pagelimit = getParaToInt("limit")==null? 12 :getParaToInt("limit"); // 每页数据条数
        Integer offset = getParaToInt("offset")==null?0:getParaToInt("offset");
        if(offset!=0){
            pageindex = offset/pagelimit;
        }
        pageindex += 1;
        
        Page<Record> page = PortService.getDataPages(pageindex, pagelimit, port);
        
        Map<String, Object> map = new HashMap<String,Object>();
        
        map.put("total", page.getTotalRow());
        map.put("rows", page.getList());
        
        renderJson(map);
    }
    
    /** 
    * @Title: getRecord 
    * @Description: 新增、编辑页面
    * @author liyu
    */
    public void getRecord() {
        Integer id = getParaToInt();
        
        if (id != null) { // 编辑
            Record r = Db.findById("t_base_port", id);
            setAttr("record", r);
        }
        
        render("port_detail.html");
    }
    
    /** 
    * @Title: save 
    * @Description: 保存
    * @author liyu
    */
    public void save() {
        
        BasePort record = getModel(BasePort.class, ""); // 港口
        Date now = new Date();
        record.setEntryTime(now); // 录入时间
        Record user = getSessionAttr("admin");
        record.setEntryMan(user.getStr("user_name")); // 录入人
        
        Integer id = record.getId();
        if (id != null) { // 更新
            boolean r = record.update();
            msg.setCode(r ? ResponseObj.OK : ResponseObj.FAILED);
            msg.setMsg(r ? "保存成功" : "保存失败");
        } else { // 新增
            String port_code = record.getPortCode(); // 港口代码
            // 查重
            boolean isDuplicate = PortService.isDuplicate(port_code);
            if (isDuplicate) { // 重复
                msg.setCode(ResponseObj.FAILED);
                msg.setMsg("港口代码重复");
            } else {
                boolean r = record.save();
                msg.setCode(r ? ResponseObj.OK : ResponseObj.FAILED);
                msg.setMsg(r ? "保存成功" : "保存失败");
            }
        }
        renderJson(msg);
    }
    
    /** 
    * @Title: delete 
    * @Description: 删除
    * @author liyu
    */
    public void delete() {
        Integer id = getParaToInt();
        BasePort record = new BasePort(); // 港口
        record.setId(id);
        boolean r = record.delete();
        msg.setCode(r ? ResponseObj.OK : ResponseObj.FAILED);
        msg.setMsg(r ? "删除成功" : "删除失败");
        
        renderJson(msg);
    }
}
