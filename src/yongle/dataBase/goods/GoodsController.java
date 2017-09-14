package yongle.dataBase.goods;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import yongle.interceptor.ManageInterceptor;
import yongle.model.BaseGoods;
import yongle.utils.ResponseObj;

/**
 * @ClassName: GoodsController.java
 * @Description: 货物信息管理
 * @author: LiYu
 * @date: 2017年8月29日下午5:21:45
 * @version: 1.0 版本初成
 */
@Before(ManageInterceptor.class)
public class GoodsController extends Controller {
    
    private ResponseObj msg = new ResponseObj();
    
    /** 
    * @Title: index 
    * @Description: 货物页面
    * @author liyu
    */
    public void index() {
        render("goods.html");
    }
    
    /** 
    * @Title: getJson 
    * @Description: 数据
    * @author liyu
    */
    public void getJson() {
        String goods_name = getPara("goods_name"); // 港口名称
        
        Integer pageindex = 0; // 页码
        Integer pagelimit = getParaToInt("limit")==null? 12 :getParaToInt("limit"); // 每页数据条数
        Integer offset = getParaToInt("offset")==null?0:getParaToInt("offset");
        if(offset!=0){
            pageindex = offset/pagelimit;
        }
        pageindex += 1;
        
        Page<Record> page = GoodsService.getDataPages(pageindex, pagelimit, goods_name);
        
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
            Record r = Db.findById("t_base_goods", id);
            setAttr("record", r);
        }
        
        render("goods_detail.html");
    }
    
    /** 
    * @Title: save 
    * @Description: 保存
    * @author liyu
    */
    public void save() {
        
        BaseGoods record = getModel(BaseGoods.class, "");
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
            String goods_name = record.getGoodsName(); // 货物名称
            // 查重
            boolean isDuplicate = GoodsService.isDuplicate(goods_name);
            if (isDuplicate) { // 重复
                msg.setCode(ResponseObj.FAILED);
                msg.setMsg("货物名称重复");
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
        BaseGoods record = new BaseGoods();
        record.setId(id);
        boolean r = record.delete();
        msg.setCode(r ? ResponseObj.OK : ResponseObj.FAILED);
        msg.setMsg(r ? "删除成功" : "删除失败");
        
        renderJson(msg);
    }
    
}
