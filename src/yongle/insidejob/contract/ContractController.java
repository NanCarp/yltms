package yongle.insidejob.contract;

import com.jfinal.core.Controller;

/**
 * @ClassName: ContractController.java
 * @Description: 内勤管理-合同管理
 * @author: LiYu
 * @date: 2017年8月28日上午11:24:43
 * @version: 1.0 版本初成
 */
public class ContractController extends Controller {
    /** 
    * @Title: index 
    * @Description: 合同管理页面
    * @author liyu
    */
    public void index() {
        render("contract.html");
    }
    
    /** 
    * @Title: getJson 
    * @Description: 数据
    * @author liyu
    */
    public void getJson() {
        renderJson("");
    }
    
    /** 
    * @Title: getRecord 
    * @Description: 单条记录信息
    * @author liyu
    */
    public void getRecord() {
        render("contract_detail.html");
    }
    
    public void uploadFile() {
        renderNull();
    }
}
