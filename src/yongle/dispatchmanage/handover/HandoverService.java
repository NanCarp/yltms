package yongle.dispatchmanage.handover;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import yongle.utils.EncodeUtil;
import yongle.utils.ResponseObj;

/**
 * @ClassName: HandoverService.java
 * @Description: 调度交接
 * @author: liyu
 * @date: 2017年9月22日下午6:10:43
 * @version: 1.0 版本初成
 */
public class HandoverService {
    /** 
    * @Title: getDataPages 
    * @Description: 调度交接待配流向数据
    * @param pageindex
    * @param pagelimit
    * @param plan_no
    * @param goods_name
    * @param entry_start
    * @param entry_end
    * @param dispatcher
    * @return Page<Record>
    * @author liyu
    */
    public static Page<Record> getDataPages(Integer pageindex, Integer pagelimit, 
            String plan_no, String goods_name, String entry_start, String entry_end, String dispatcher) {
        String select = " SELECT *,a.id AS dispatch_detail_id ";
        String sqlExceptSelect = " FROM `t_dispatch_detail` AS a "
                + " LEFT JOIN t_dispatch AS b "
                + " ON a.plan_no_id = b.id "
                + " WHERE a.flow_state != 1 "
                + " AND b.examine_state = 1 ";
        
        if (plan_no != null && !"".equals(plan_no)) {
            sqlExceptSelect += " AND plan_no like '%"+ plan_no +"'";
        }
        
        if (goods_name != null && !"".equals(goods_name)) {
            sqlExceptSelect += " AND goods_name like '%"+ goods_name +"'";
        }
        
        if (entry_start != null && !"".equals(entry_start)) {
            sqlExceptSelect += " AND dispatcher_entry_time >= '" + entry_start + "'";
        }
        
        if (entry_end != null && !"".equals(entry_end)) {
            sqlExceptSelect += " AND dispatcher_entry_time <= '" + entry_end + "'";
        }
        
        if (dispatcher != null && !"".equals(dispatcher)) {
            sqlExceptSelect += " AND dispatcher like '%" + dispatcher + "'";
        }
        
        sqlExceptSelect += " ORDER BY plan_no DESC ";
        
        return Db.paginate(pageindex, pagelimit, select, sqlExceptSelect);
    }

    /** 
    * @Title: save 
    * @Description: 保存调度交接表驳船信息
    * @param dispatch_detail_id
    * @param left_qty
    * @param recordList
    * @param dispatcher
    * @return ResponseObj
    * @author liyu
    */
    public static ResponseObj save(Integer dispatch_detail_id, String left_qty, 
            String recordList, String dispatcher) {
        ResponseObj msg = new ResponseObj();
        
        boolean result = Db.tx(new IAtom() {
            List<Record> list = jsonToRecordList(recordList);
            @Override
            public boolean run() throws SQLException {
                List<Record> overList = new ArrayList<Record>(); // 运价超出指导价列表
                // 删除原驳船信息
                Db.update("DELETE FROM `t_dispatch_ship` WHERE dispatch_detail_id = ? ", dispatch_detail_id);
                // 新增驳船信息
                for (Record r : list) {
                    r.set("dispatch_detail_id", dispatch_detail_id);
                    Db.save("t_dispatch_ship", r);
                    if (r.getInt("over_guide_price") == 1) { // 运价超过指导价
                        overList.add(r);
                    }
                }
                // 运价超过指导价，提醒
                if (overList.size() > 0) {
                    Record notice = new Record(); // 提醒
                    notice.set("title", "调度运价超出指导价，请确认。");
                    notice.set("type", "提醒");
                    notice.set("publisher", dispatcher); // 发布者
                    notice.set("receiver", 1); // 接收者，经理
                    notice.set("publish_time", new Date());
                    Db.save("t_notice", notice);
                    Long notice_id = notice.getLong("id");
                    notice.set("url", "/notice/ship/getRecord/" + notice_id);
                    Db.update("t_notice", notice);
                    
                    for (Record r : overList) {
                        r.set("dispatch_ship_id", r.getLong("id"));
                        r.remove("id");
                        r.set("notice_id", notice_id);
                        Db.save("t_notice_ship", r);
                    }
                }
                
                
                // 更新 dispatch_detail 表
                Record dispatch = Db.findById("t_dispatch_detail", dispatch_detail_id);
                dispatch.set("left_qty", left_qty == "" ? null : left_qty);
                dispatch.set("dispatcher", dispatcher); 
                dispatch.set("dispatcher_entry_time", new Date());
                dispatch.set("flow_state", 2); // 配船状态，正在配船
                Db.update("t_dispatch_detail", dispatch);
                
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
    @SuppressWarnings("unchecked")
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

    /** 
    * @Title: export 
    * @Description: 导出宜兴永乐运输有限公司调度员交接表
    * @return boolean
    * @author liyu
     * @param id 
     * @param httpServletResponse 
    */
    public static boolean export(HttpServletResponse response, Integer id) {
        
        List<Record> recordList = Db.find("SELECT * "
                + " FROM t_dispatch_ship AS a "
                + " LEFT JOIN t_dispatch_detail AS b "
                + " ON b.id = a.dispatch_detail_id "
                + " LEFT JOIN t_dispatch AS c "
                + " ON b.plan_no_id = c.id"
                + " WHERE c.id = ? ", id);
        
        HSSFWorkbook wbook = new HSSFWorkbook();
        HSSFSheet sheet = wbook.createSheet();
        wbook.setSheetName(0, "调度交接表", (short)1);
        sheet.addMergedRegion(new Region(0, (short)0, 0, (short)13));
        // 设置列宽
        for (int i = 0; i < 14; i++) {
            sheet.setColumnWidth((short)i, (short)3000);
        }
        
        HSSFCellStyle cellStyle = wbook.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFFont font = wbook.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short)20);
        cellStyle.setFont(font);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        
        HSSFCellStyle cellBorder = wbook.createCellStyle();
        cellBorder.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellBorder.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
        cellBorder.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
        cellBorder.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
        cellBorder.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
        
        HSSFRow row;
        String[] a = {"计划号","货名","海船名","发货码头","目的港","船名","姓名","手机号","身份证号码","配载吨位","可载吨位","到港日期","运价","加油"};
        
        //int i = 1;
        row = sheet.createRow(0);
        HSSFCell cell = row.createCell((short)0);
        cell.setEncoding(HSSFCell.ENCODING_UTF_16);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("宜兴永乐运输有限公司调度员交接表");
        
        row = sheet.createRow(1);
        for (int j = 0; j < a.length; j++) {
            HSSFCell cell0 = row.createCell((short)j);
            cell0.setEncoding(HSSFCell.ENCODING_UTF_16);
            cell0.setCellStyle(cellBorder);
            cell0.setCellValue(a[j]);
        }
        
        for (int j = 0; j < recordList.size(); j++) {
            Record r = recordList.get(j);
            row = sheet.createRow(j + 2);
            
            HSSFCell cell0 = row.createCell((short) 0);
            cell0.setEncoding(HSSFCell.ENCODING_UTF_16);
            cell0.setCellStyle(cellBorder);
            cell0.setCellValue(r.getStr("plan_no"));
            
            HSSFCell cell1 = row.createCell((short) 1);
            cell1.setEncoding(HSSFCell.ENCODING_UTF_16);
            cell1.setCellStyle(cellBorder);
            cell1.setCellValue(r.getStr("goods_name"));

            HSSFCell cell2 = row.createCell((short) 2);
            cell2.setEncoding(HSSFCell.ENCODING_UTF_16);
            cell2.setCellStyle(cellBorder);
            cell2.setCellValue(r.getStr("seagoing_vessel_name"));

            HSSFCell cell3 = row.createCell((short) 3);
            cell3.setEncoding(HSSFCell.ENCODING_UTF_16);
            cell3.setCellStyle(cellBorder);
            cell3.setCellValue(r.getStr("delivery_dock"));

            HSSFCell cell4 = row.createCell((short) 4);
            cell4.setEncoding(HSSFCell.ENCODING_UTF_16);
            cell4.setCellStyle(cellBorder);
            cell4.setCellValue(r.getStr("destination_port"));

            HSSFCell cell5 = row.createCell((short) 5);
            cell5.setEncoding(HSSFCell.ENCODING_UTF_16);
            cell5.setCellStyle(cellBorder);
            cell5.setCellValue(r.getStr("ship_name"));

            HSSFCell cell6 = row.createCell((short) 6);
            cell6.setEncoding(HSSFCell.ENCODING_UTF_16);
            cell6.setCellStyle(cellBorder);
            cell6.setCellValue(r.getStr("ship_owner_name"));

            HSSFCell cell7 = row.createCell((short) 7);
            cell7.setEncoding(HSSFCell.ENCODING_UTF_16);
            cell7.setCellStyle(cellBorder);
            cell7.setCellValue(r.getStr("ship_owner_phone"));

            HSSFCell cell8 = row.createCell((short) 8);
            cell8.setEncoding(HSSFCell.ENCODING_UTF_16);
            cell8.setCellStyle(cellBorder);
            cell8.setCellValue(r.getStr("id_card_no"));

            HSSFCell cell9 = row.createCell((short) 9);
            cell9.setEncoding(HSSFCell.ENCODING_UTF_16);
            cell9.setCellStyle(cellBorder);
            cell9.setCellValue(r.getBigDecimal("loading_tonnage").doubleValue());

            HSSFCell cell10 = row.createCell((short) 10);
            cell10.setEncoding(HSSFCell.ENCODING_UTF_16);
            cell10.setCellStyle(cellBorder);
            cell10.setCellValue(r.getBigDecimal("available_tonnage").doubleValue());

            HSSFCell cell11 = row.createCell((short) 11);
            cell11.setEncoding(HSSFCell.ENCODING_UTF_16);
            cell11.setCellStyle(cellBorder);
            Date arrival_date = r.getDate("arrival_date");
            if (arrival_date == null) {
                cell11.setCellValue("");
            } else {
                cell11.setCellValue(arrival_date);
            }

            HSSFCell cell12 = row.createCell((short) 12);
            cell12.setEncoding(HSSFCell.ENCODING_UTF_16);
            cell12.setCellStyle(cellBorder);
            BigDecimal freight_price = r.getBigDecimal("freight_price");
            if (freight_price == null) {
                cell12.setCellValue("");
            } else {
                cell12.setCellValue(freight_price.doubleValue());
            }

            HSSFCell cell13 = row.createCell((short) 13);
            cell13.setEncoding(HSSFCell.ENCODING_UTF_16);
            cell13.setCellStyle(cellBorder);
            BigDecimal pre_refuel = r.getBigDecimal("pre_refuel");
            if (pre_refuel == null) {
                cell13.setCellValue("");
            } else {
                cell13.setCellValue(pre_refuel.doubleValue());
            }
        }
        
        response.addHeader("Content-Disposition", "attachment;filename=" + EncodeUtil.toUtf8String("调度员交接表") + ".xls");
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        try {
            ServletOutputStream out = response.getOutputStream();
            wbook.write(out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static Record getRecordById(Integer id) {
        String sql = " SELECT *,a.id "
                + " FROM `t_dispatch_detail` AS a "
                + " LEFT JOIN t_dispatch AS b "
                + " ON a.plan_no_id = b.id "
                + " WHERE a.id = ? ";
        return Db.findFirst(sql, id);
    }

    /** 
    * @Title: review 
    * @Description: 审核流向
    * @param id
    * @return boolean
    * @author 
    */
    public static boolean review(Integer id) {
        boolean result = Db.tx(new IAtom() {
            
            @Override
            public boolean run() throws SQLException {
                // 修改流向状态
                Record r = Db.findById("t_dispatch_detail", id);
                r.set("flow_state", 1);
                Db.update("t_dispatch_detail", r);
                
                // 如果所有流向都已审核，修改计划状态、实装数量
                Integer dispatch_id = r.getInt("plan_no_id");
                // 计划号下流向数量
                Long a = Db.queryLong("SELECT COUNT(1) FROM `t_dispatch_detail` WHERE plan_no_id = ?", dispatch_id);
                // 已经配完的流向数量
                Long b = Db.queryLong("SELECT COUNT(1) FROM `t_dispatch_detail` WHERE plan_no_id = ? AND flow_state = ?", dispatch_id, 1);
                if (a.equals(b)) {
                    Record dispatch = new Record();
                    BigDecimal real_quantity = Db.queryBigDecimal("SELECT SUM(planned_qty) - SUM(left_qty) FROM `t_dispatch_detail` WHERE plan_no_id = ?", dispatch_id);
                    dispatch.set("id", r.get("plan_no_id"));
                    dispatch.set("real_quantity", real_quantity); // 实装数量
                    dispatch.set("document_status", 1); // 修改计划状态
                    Db.update("t_dispatch", dispatch); // 更新
                }
                
                return true;
            }
            
        });
        return result;
    }

}