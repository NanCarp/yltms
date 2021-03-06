package yongle.model;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * Generated by JFinal, do not modify this file.
 * <pre>
 * Example:
 * public void configPlugin(Plugins me) {
 *     ActiveRecordPlugin arp = new ActiveRecordPlugin(...);
 *     _MappingKit.mapping(arp);
 *     me.add(arp);
 * }
 * </pre>
 */
public class _MappingKit {

	public static void mapping(ActiveRecordPlugin arp) {
		arp.addMapping("t_base_customer", "id", BaseCustomer.class);
		arp.addMapping("t_base_goods", "id", BaseGoods.class);
		arp.addMapping("t_base_port", "id", BasePort.class);
		arp.addMapping("t_base_ship", "id", BaseShip.class);
		arp.addMapping("t_base_ship_crew", "id", BaseShipCrew.class);
		arp.addMapping("t_button", "id", Button.class);
		arp.addMapping("t_contract", "id", Contract.class);
		arp.addMapping("t_customer_settle", "id", CustomerSettle.class);
		arp.addMapping("t_dictionary", "id", Dictionary.class);
		arp.addMapping("t_dispatch", "id", Dispatch.class);
		arp.addMapping("t_dispatch_detail", "id", DispatchDetail.class);
		arp.addMapping("t_dispatch_ship", "id", DispatchShip.class);
		arp.addMapping("t_insidejob_contract", "id", InsidejobContract.class);
		arp.addMapping("t_menu", "id", Menu.class);
		arp.addMapping("t_notice", "id", Notice.class);
		arp.addMapping("t_notice_ship", "id", NoticeShip.class);
		arp.addMapping("t_notice_waybill", "id", NoticeWaybill.class);
		arp.addMapping("t_role", "id", Role.class);
		arp.addMapping("t_role_button", "id", RoleButton.class);
		arp.addMapping("t_role_menu", "id", RoleMenu.class);
		arp.addMapping("t_settle_apply", "id", SettleApply.class);
		arp.addMapping("t_ship_settle", "id", ShipSettle.class);
		arp.addMapping("t_statement", "id", Statement.class);
		arp.addMapping("t_user", "id", User.class);
	}
}

