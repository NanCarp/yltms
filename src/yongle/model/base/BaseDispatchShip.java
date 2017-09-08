package yongle.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseDispatchShip<M extends BaseDispatchShip<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public M setDispatchId(java.lang.Integer dispatchId) {
		set("dispatch_id", dispatchId);
		return (M)this;
	}

	public java.lang.Integer getDispatchId() {
		return get("dispatch_id");
	}

	public M setShipName(java.lang.String shipName) {
		set("ship_name", shipName);
		return (M)this;
	}

	public java.lang.String getShipName() {
		return get("ship_name");
	}

	public M setShipOwnerName(java.lang.String shipOwnerName) {
		set("ship_owner_name", shipOwnerName);
		return (M)this;
	}

	public java.lang.String getShipOwnerName() {
		return get("ship_owner_name");
	}

	public M setShipOwnerPhone(java.lang.String shipOwnerPhone) {
		set("ship_owner_phone", shipOwnerPhone);
		return (M)this;
	}

	public java.lang.String getShipOwnerPhone() {
		return get("ship_owner_phone");
	}

	public M setIdCardNo(java.lang.String idCardNo) {
		set("id_card_no", idCardNo);
		return (M)this;
	}

	public java.lang.String getIdCardNo() {
		return get("id_card_no");
	}

	public M setLoadingTonnage(java.math.BigDecimal loadingTonnage) {
		set("loading_tonnage", loadingTonnage);
		return (M)this;
	}

	public java.math.BigDecimal getLoadingTonnage() {
		return get("loading_tonnage");
	}

	public M setAvailableTonnage(java.math.BigDecimal availableTonnage) {
		set("available_tonnage", availableTonnage);
		return (M)this;
	}

	public java.math.BigDecimal getAvailableTonnage() {
		return get("available_tonnage");
	}

	public M setArrivalLimit(java.util.Date arrivalLimit) {
		set("arrival_limit", arrivalLimit);
		return (M)this;
	}

	public java.util.Date getArrivalLimit() {
		return get("arrival_limit");
	}

	public M setArrivalDate(java.util.Date arrivalDate) {
		set("arrival_date", arrivalDate);
		return (M)this;
	}

	public java.util.Date getArrivalDate() {
		return get("arrival_date");
	}

	public M setFreightPrice(java.math.BigDecimal freightPrice) {
		set("freight_price", freightPrice);
		return (M)this;
	}

	public java.math.BigDecimal getFreightPrice() {
		return get("freight_price");
	}

	public M setTotalFreight(java.math.BigDecimal totalFreight) {
		set("total_freight", totalFreight);
		return (M)this;
	}

	public java.math.BigDecimal getTotalFreight() {
		return get("total_freight");
	}

	public M setPrepay(java.math.BigDecimal prepay) {
		set("prepay", prepay);
		return (M)this;
	}

	public java.math.BigDecimal getPrepay() {
		return get("prepay");
	}

	public M setPreRefuel(java.math.BigDecimal preRefuel) {
		set("pre_refuel", preRefuel);
		return (M)this;
	}

	public java.math.BigDecimal getPreRefuel() {
		return get("pre_refuel");
	}

	public M setLeftAmount(java.math.BigDecimal leftAmount) {
		set("left_amount", leftAmount);
		return (M)this;
	}

	public java.math.BigDecimal getLeftAmount() {
		return get("left_amount");
	}

	public M setSpecifications(java.lang.String specifications) {
		set("specifications", specifications);
		return (M)this;
	}

	public java.lang.String getSpecifications() {
		return get("specifications");
	}

	public M setUnit(java.lang.String unit) {
		set("unit", unit);
		return (M)this;
	}

	public java.lang.String getUnit() {
		return get("unit");
	}

	public M setDeclareDate(java.util.Date declareDate) {
		set("declare_date", declareDate);
		return (M)this;
	}

	public java.util.Date getDeclareDate() {
		return get("declare_date");
	}

	public M setReceivedQuantity(java.math.BigDecimal receivedQuantity) {
		set("received_quantity", receivedQuantity);
		return (M)this;
	}

	public java.math.BigDecimal getReceivedQuantity() {
		return get("received_quantity");
	}

	public M setUnloadedDate(java.util.Date unloadedDate) {
		set("unloaded_date", unloadedDate);
		return (M)this;
	}

	public java.util.Date getUnloadedDate() {
		return get("unloaded_date");
	}

	public M setRainyDays(java.lang.Integer rainyDays) {
		set("rainy_days", rainyDays);
		return (M)this;
	}

	public java.lang.Integer getRainyDays() {
		return get("rainy_days");
	}

	public M setWaterwayRemark(java.lang.String waterwayRemark) {
		set("waterway_remark", waterwayRemark);
		return (M)this;
	}

	public java.lang.String getWaterwayRemark() {
		return get("waterway_remark");
	}

	public M setDispatchDetailId(java.lang.Integer dispatchDetailId) {
		set("dispatch_detail_id", dispatchDetailId);
		return (M)this;
	}

	public java.lang.Integer getDispatchDetailId() {
		return get("dispatch_detail_id");
	}

}
