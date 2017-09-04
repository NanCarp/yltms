package yongle.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseStatement<M extends BaseStatement<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public M setShipper(java.lang.String shipper) {
		set("shipper", shipper);
		return (M)this;
	}

	public java.lang.String getShipper() {
		return get("shipper");
	}

	public M setGoodsName(java.lang.String goodsName) {
		set("goods_name", goodsName);
		return (M)this;
	}

	public java.lang.String getGoodsName() {
		return get("goods_name");
	}

	public M setSeagoingVesselName(java.lang.String seagoingVesselName) {
		set("seagoing_vessel_name", seagoingVesselName);
		return (M)this;
	}

	public java.lang.String getSeagoingVesselName() {
		return get("seagoing_vessel_name");
	}

	public M setDeliveryTerminal(java.lang.String deliveryTerminal) {
		set("delivery_terminal", deliveryTerminal);
		return (M)this;
	}

	public java.lang.String getDeliveryTerminal() {
		return get("delivery_terminal");
	}

	public M setPlanNo(java.lang.String planNo) {
		set("plan_no", planNo);
		return (M)this;
	}

	public java.lang.String getPlanNo() {
		return get("plan_no");
	}

	public M setPlanDate(java.util.Date planDate) {
		set("plan_date", planDate);
		return (M)this;
	}

	public java.util.Date getPlanDate() {
		return get("plan_date");
	}

	public M setFlow(java.lang.String flow) {
		set("flow", flow);
		return (M)this;
	}

	public java.lang.String getFlow() {
		return get("flow");
	}

	public M setShipName(java.lang.String shipName) {
		set("ship_name", shipName);
		return (M)this;
	}

	public java.lang.String getShipName() {
		return get("ship_name");
	}

	public M setPlanQuantity(java.math.BigDecimal planQuantity) {
		set("plan_quantity", planQuantity);
		return (M)this;
	}

	public java.math.BigDecimal getPlanQuantity() {
		return get("plan_quantity");
	}

	public M setLoss(java.math.BigDecimal loss) {
		set("loss", loss);
		return (M)this;
	}

	public java.math.BigDecimal getLoss() {
		return get("loss");
	}

	public M setDischargingPeriod(java.util.Date dischargingPeriod) {
		set("discharging_period", dischargingPeriod);
		return (M)this;
	}

	public java.util.Date getDischargingPeriod() {
		return get("discharging_period");
	}

	public M setFreight(java.math.BigDecimal freight) {
		set("freight", freight);
		return (M)this;
	}

	public java.math.BigDecimal getFreight() {
		return get("freight");
	}

	public M setRefuelingMode(java.lang.String refuelingMode) {
		set("refueling_mode", refuelingMode);
		return (M)this;
	}

	public java.lang.String getRefuelingMode() {
		return get("refueling_mode");
	}

	public M setAmountMoney(java.math.BigDecimal amountMoney) {
		set("amount_money", amountMoney);
		return (M)this;
	}

	public java.math.BigDecimal getAmountMoney() {
		return get("amount_money");
	}

	public M setDateTranshipment(java.util.Date dateTranshipment) {
		set("date_transhipment", dateTranshipment);
		return (M)this;
	}

	public java.util.Date getDateTranshipment() {
		return get("date_transhipment");
	}

	public M setActualTonnage(java.math.BigDecimal actualTonnage) {
		set("actual_tonnage", actualTonnage);
		return (M)this;
	}

	public java.math.BigDecimal getActualTonnage() {
		return get("actual_tonnage");
	}

	public M setAdvanceCharge(java.math.BigDecimal advanceCharge) {
		set("advance_charge", advanceCharge);
		return (M)this;
	}

	public java.math.BigDecimal getAdvanceCharge() {
		return get("advance_charge");
	}

	public M setRemark(java.lang.String remark) {
		set("remark", remark);
		return (M)this;
	}

	public java.lang.String getRemark() {
		return get("remark");
	}

}