package yongle.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseShipSettle<M extends BaseShipSettle<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public M setDispatchShipId(java.lang.Integer dispatchShipId) {
		set("dispatch_ship_id", dispatchShipId);
		return (M)this;
	}

	public java.lang.Integer getDispatchShipId() {
		return get("dispatch_ship_id");
	}

	public M setLoss(java.math.BigDecimal loss) {
		set("loss", loss);
		return (M)this;
	}

	public java.math.BigDecimal getLoss() {
		return get("loss");
	}

	public M setFixedLoss(java.math.BigDecimal fixedLoss) {
		set("fixed_loss", fixedLoss);
		return (M)this;
	}

	public java.math.BigDecimal getFixedLoss() {
		return get("fixed_loss");
	}

	public M setExceedLoss(java.math.BigDecimal exceedLoss) {
		set("exceed_loss", exceedLoss);
		return (M)this;
	}

	public java.math.BigDecimal getExceedLoss() {
		return get("exceed_loss");
	}

	public M setPortConstructionFee(java.math.BigDecimal portConstructionFee) {
		set("port_construction_fee", portConstructionFee);
		return (M)this;
	}

	public java.math.BigDecimal getPortConstructionFee() {
		return get("port_construction_fee");
	}

	public M setDeductMoney(java.math.BigDecimal deductMoney) {
		set("deduct_money", deductMoney);
		return (M)this;
	}

	public java.math.BigDecimal getDeductMoney() {
		return get("deduct_money");
	}

	public M setDemurrageDays(java.lang.String demurrageDays) {
		set("demurrage_days", demurrageDays);
		return (M)this;
	}

	public java.lang.String getDemurrageDays() {
		return get("demurrage_days");
	}

	public M setExtendedDays(java.lang.Integer extendedDays) {
		set("extended_days", extendedDays);
		return (M)this;
	}

	public java.lang.Integer getExtendedDays() {
		return get("extended_days");
	}

	public M setDemurrageCharges(java.math.BigDecimal demurrageCharges) {
		set("demurrage_charges", demurrageCharges);
		return (M)this;
	}

	public java.math.BigDecimal getDemurrageCharges() {
		return get("demurrage_charges");
	}

	public M setOtherCharges(java.lang.String otherCharges) {
		set("other_charges", otherCharges);
		return (M)this;
	}

	public java.lang.String getOtherCharges() {
		return get("other_charges");
	}

	public M setPayableAmount(java.math.BigDecimal payableAmount) {
		set("payable_amount", payableAmount);
		return (M)this;
	}

	public java.math.BigDecimal getPayableAmount() {
		return get("payable_amount");
	}

}