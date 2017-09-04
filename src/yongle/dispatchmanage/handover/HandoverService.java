package yongle.dispatchmanage.handover;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import yongle.utils.ResponseObj;

public class HandoverService {

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
        String sqlExceptSelect = " FROM `t_dispatch` WHERE examine_state = 1 ";
        
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
    public static ResponseObj save(Integer dispatch_id, String left_quantity, String site_dispatch, String recordList) {
        ResponseObj msg = new ResponseObj();
        
        boolean result = Db.tx(new IAtom() {
            List<Record> list = jsonToRecordList(recordList);
            @Override
            public boolean run() throws SQLException {
                // 删除原驳船信息
                Db.update("DELETE FROM `t_dispatch_ship` WHERE dispatch_id = ? ", dispatch_id);
                // 新增驳船信息
                for (Record r : list) {
                    r.set("dispatch_id", dispatch_id);
                    Db.save("t_dispatch_ship", r);
                }
                // 更新 dispatch 表
                Record dispatch = Db.findById("t_dispatch", dispatch_id);
                dispatch.set("left_quantity", left_quantity);
                dispatch.set("site_dispatch", site_dispatch);
                // 剩余吨数
                //BigDecimal total_quantity = dispatch.getBigDecimal("total_quantity");
                /*BigDecimal remain = dispatch.getBigDecimal("total_quantity");
                for (Record r : list) {
                    remain = remain.subtract(r.getBigDecimal("loading_tonnage"));
                    if (remain.compareTo(BigDecimal.ZERO) == -1) {
                        msg.setCode(ResponseObject.FAILED);
                        msg.setMsg("剩余吨数不足");
                    }
                }*/
                
                Db.update("t_dispatch", dispatch);
                
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