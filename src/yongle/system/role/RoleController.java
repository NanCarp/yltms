package yongle.system.role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import yongle.interceptor.ManageInterceptor;
/**
 * @ClassName: RoleController
 * @Description: 系统管理_角色管理
 * @author: xuhui
 * @date: 2017年8月3日下午2:00:00
 * @version: 1.0 版本初成
 */
@Before(ManageInterceptor.class)
public class RoleController extends Controller {

    public void index() {
    	
    	String rolename = getPara("rolename");
        List<Record> roleList =  RoleService.getRoleList(rolename, null);
        setAttr("roleList", roleList);
        setAttr("rolename", rolename);
        System.out.println("ss"+ roleList);
        render("role.html");
    }

	/**
	 * @desc:角色列表
	 */
	public void getRole(){
        // 角色 id
        Integer id = getParaToInt();

        if (id != null) {//编辑
            Record role  = Db.findById("t_role", id);
            setAttr("role", role);
        }

		render("role_detail.html");
	}

	//
    public void saveRole() {
        // 角色 id
        Integer id = getParaToInt("id");
        // 角色名称
        String role = getPara("role").trim();
        // 备注
        String remark = getPara("remark", "");
        // 保存结果
        boolean result = false;
        // 返回信息
        Map<String, Object> response = new HashMap<>();
        // 重复检测
        if (id == null && RoleService.isDuplicate(role)) {
            response.put("tips", "角色重复！");
            response.put("isSuccess", false);
            renderJson(response);
            return;
        }
        
        Record record = new Record();
        record.set("role_type", role);
        record.set("remark", remark);
        if (id != null) {// 编辑
            record.set("id", id);
            result = Db.update("t_role", record);
            response.put("isSuccess", result);
            response.put("tips", result ? "保存成功": "保存失败");
        } else {// 新增
            result = Db.save("t_role", record);
            response.put("isSuccess", result);
            response.put("tips", result ? "保存成功": "保存失败");
        }

        renderJson(response);
    }

    /**
	 * @desc:批量删除
	 * @author xuhui
	 */
	public void delete(){
		String ids = getPara(0);
		boolean result = RoleService.delete(ids);
		System.out.println(result);
		renderJson(result);
	}

    // 根据公司 id 获取角色列表
    public void getRoleByCompanyId() {
        // 公司 id
        Integer companyId = getParaToInt();
        // 角色列表
        List<Record> roleList = RoleService.getRoleByCompanyId(companyId);

        renderJson(roleList);
    }

    // 根据公司 id 获取角色列表，剔除已分配权限的角色，
    public void getRoleByCompanyIdNotAuthorized() {
        // 公司 id
        Integer companyId = getParaToInt();
        // 角色列表
        List<Record> roleList = RoleService.getRoleByCompanyIdNotAuthorized(companyId);

        renderJson(roleList);
    }
}
