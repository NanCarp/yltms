package yongle.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseInsidejobContractCopy<M extends BaseInsidejobContractCopy<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public M setCustomer(java.lang.String customer) {
		set("customer", customer);
		return (M)this;
	}

	public java.lang.String getCustomer() {
		return get("customer");
	}

	public M setArtNo(java.lang.String artNo) {
		set("art_no", artNo);
		return (M)this;
	}

	public java.lang.String getArtNo() {
		return get("art_no");
	}

	public M setDispatchPort(java.lang.String dispatchPort) {
		set("dispatch_port", dispatchPort);
		return (M)this;
	}

	public java.lang.String getDispatchPort() {
		return get("dispatch_port");
	}

	public M setConsignee(java.lang.String consignee) {
		set("consignee", consignee);
		return (M)this;
	}

	public java.lang.String getConsignee() {
		return get("consignee");
	}

	public M setFreight(java.lang.String freight) {
		set("freight", freight);
		return (M)this;
	}

	public java.lang.String getFreight() {
		return get("freight");
	}

	public M setQuantity(java.math.BigDecimal quantity) {
		set("quantity", quantity);
		return (M)this;
	}

	public java.math.BigDecimal getQuantity() {
		return get("quantity");
	}

	public M setAttachment(java.lang.String attachment) {
		set("attachment", attachment);
		return (M)this;
	}

	public java.lang.String getAttachment() {
		return get("attachment");
	}

}
