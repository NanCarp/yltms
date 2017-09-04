package yongle.site.handoversite;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import yongle.model.DispatchShip;
import yongle.utils.ResponseObj;

/**
 * @ClassName: HandoverSiteService.java
 * @Description:
 * @author: LiYu
 * @date: 2017年8月31日上午8:21:13
 * @version: 1.0 版本初成
 */
public class HandoverSiteService {

    /** 
    * @Title: getDataPages 
    * @Description: 调度交接
    * @param pageindex
    * @param pagelimit
    * @param warehouse_in_no
    * @param company_name
    * @return Page<Record>
    * @author liyu
    */
    public static Page<Record> getDataPages(Integer pageindex, Integer pagelimit, 
            String plan_no, String goods_name, String entry_start, String entry_end, String entry_man) {
        String select = " SELECT * ";
        String sqlExceptSelect = " FROM `t_dispatch` WHERE examine_state = 1 AND dispatch_review = 1 AND dispatch_issue = 1 ";
        
        if (plan_no != null && !"".equals(plan_no)) {
            sqlExceptSelect += " AND plan_no like '%"+ plan_no +"'";
        }
        
        if (goods_name != null && !"".equals(goods_name)) {
            sqlExceptSelect += " AND goods_name like '%"+ goods_name +"'";
        }
        
        if (entry_start != null && !"".equals(entry_start)) {
            sqlExceptSelect += " AND entry_time > '" + entry_start + "'";
        }
        
        if (entry_end != null && !"".equals(entry_end)) {
            sqlExceptSelect += " AND entry_time < '" + entry_end + "'";
        }
        
        if (entry_man != null && !"".equals(entry_man)) {
            sqlExceptSelect += " AND plan_no like '%" + entry_man + "'";
        }
        
        return Db.paginate(pageindex, pagelimit, select, sqlExceptSelect);
    }

    /** 
    * @Title: save 
    * @Description: 保存调度交接表驳船信息
    * @param dispatch_id
    * @param recordList
    * @return ResponseObject
    * @author liyu
    */
    public static ResponseObj save(Integer dispatch_id, String recordList) {
        ResponseObj msg = new ResponseObj();
        
        boolean result = Db.tx(new IAtom() {
            List<Record> list = jsonToRecordList(recordList);
            @Override
            public boolean run() throws SQLException {
                // 新增驳船信息
                for (Record r : list) {
                    r.set("dispatch_id", dispatch_id);
                    Db.update("t_dispatch_ship", r);
                }
                
                return true;
            }
            
        });
        
        msg.setCode(result ? ResponseObj.OK : ResponseObj.FAILED);
        msg.setMsg(result ? "保存成功" : "保存失败");
        
        return msg;
    }

    /** 
    * @Title: jsonToRecordList 
    * @Description: json 转换成 recordList
    * @param json
    * @return List<Record>
    * @author liyu
    */
    private static List<Record> jsonToRecordList(String json) {
        List<JSONObject> list = new ArrayList<>();
        list = JSONObject.parseObject(json, list.getClass());
        List<Record> recordList = new ArrayList<Record>();
        for (JSONObject o : list) {
            Record record = new Record().setColumns(o);
            recordList.add(record);
        }
        return recordList;
    }

}