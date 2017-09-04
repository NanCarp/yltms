package yongle.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseBaseCustomer<M extends BaseBaseCustomer<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public M setCustomerName(java.lang.String customerName) {
		set("customer_name", customerName);
		return (M)this;
	}

	public java.lang.String getCustomerName() {
		return get("customer_name");
	}

	public M setCustomerType(java.lang.String customerType) {
		set("customer_type", customerType);
		return (M)this;
	}

	public java.lang.String getCustomerType() {
		return get("customer_type");
	}

	public M setNameContent(java.lang.String nameContent) {
		set("name_content", nameContent);
		return (M)this;
	}

	public java.lang.String getNameContent() {
		return get("name_content");
	}

	public M setPostContant(java.lang.String postContant) {
		set("post_contant", postContant);
		return (M)this;
	}

	public java.lang.String getPostContant() {
		return get("post_contant");
	}

	public M setPhoneContant(java.lang.String phoneContant) {
		set("phone_contant", phoneContant);
		return (M)this;
	}

	public java.lang.String getPhoneContant() {
		return get("phone_contant");
	}

	public M setFixedPhoneContant(java.lang.String fixedPhoneContant) {
		set("fixed_phone_contant", fixedPhoneContant);
		return (M)this;
	}

	public java.lang.String getFixedPhoneContant() {
		return get("fixed_phone_contant");
	}

	public M setFaxContant(java.lang.String faxContant) {
		set("fax_contant", faxContant);
		return (M)this;
	}

	public java.lang.String getFaxContant() {
		return get("fax_contant");
	}

	public M setQqContent(java.lang.String qqContent) {
		set("qq_content", qqContent);
		return (M)this;
	}

	public java.lang.String getQqContent() {
		return get("qq_content");
	}

	public M setEMailContent(java.lang.String eMailContent) {
		set("e_mail_content", eMailContent);
		return (M)this;
	}

	public java.lang.String getEMailContent() {
		return get("e_mail_content");
	}

	public M setAddressContant(java.lang.String addressContant) {
		set("address_contant", addressContant);
		return (M)this;
	}

	public java.lang.String getAddressContant() {
		return get("address_contant");
	}

	public M setCompanyInformation(java.lang.String companyInformation) {
		set("company_information", companyInformation);
		return (M)this;
	}

	public java.lang.String getCompanyInformation() {
		return get("company_information");
	}

	public M setTaxpayerDistinguishInformation(java.lang.String taxpayerDistinguishInformation) {
		set("taxpayer_distinguish_information", taxpayerDistinguishInformation);
		return (M)this;
	}

	public java.lang.String getTaxpayerDistinguishInformation() {
		return get("taxpayer_distinguish_information");
	}

	public M setBlanknameInformation(java.lang.String blanknameInformation) {
		set("blankname_information", blanknameInformation);
		return (M)this;
	}

	public java.lang.String getBlanknameInformation() {
		return get("blankname_information");
	}

	public M setBlankaccountInfomation(java.lang.String blankaccountInfomation) {
		set("blankaccount_infomation", blankaccountInfomation);
		return (M)this;
	}

	public java.lang.String getBlankaccountInfomation() {
		return get("blankaccount_infomation");
	}

	public M setAddressInformation(java.lang.String addressInformation) {
		set("address_information", addressInformation);
		return (M)this;
	}

	public java.lang.String getAddressInformation() {
		return get("address_information");
	}

	public M setPhoneInformation(java.lang.String phoneInformation) {
		set("phone_information", phoneInformation);
		return (M)this;
	}

	public java.lang.String getPhoneInformation() {
		return get("phone_information");
	}

	public M setLicenseCopyCommerce(java.lang.String licenseCopyCommerce) {
		set("license_copy_commerce", licenseCopyCommerce);
		return (M)this;
	}

	public java.lang.String getLicenseCopyCommerce() {
		return get("license_copy_commerce");
	}

	public M setRegistrationCommerce(java.lang.String registrationCommerce) {
		set("registration_commerce", registrationCommerce);
		return (M)this;
	}

	public java.lang.String getRegistrationCommerce() {
		return get("registration_commerce");
	}

	public M setOrganizationCommerce(java.lang.String organizationCommerce) {
		set("organization_commerce", organizationCommerce);
		return (M)this;
	}

	public java.lang.String getOrganizationCommerce() {
		return get("organization_commerce");
	}

	public M setVarietyGoods(java.lang.String varietyGoods) {
		set("variety_goods", varietyGoods);
		return (M)this;
	}

	public java.lang.String getVarietyGoods() {
		return get("variety_goods");
	}

	public M setCommonDeliveryCompany(java.lang.String commonDeliveryCompany) {
		set("common_delivery_company", commonDeliveryCompany);
		return (M)this;
	}

	public java.lang.String getCommonDeliveryCompany() {
		return get("common_delivery_company");
	}

	public M setCommonReceivingCompany(java.lang.String commonReceivingCompany) {
		set("common_receiving_company", commonReceivingCompany);
		return (M)this;
	}

	public java.lang.String getCommonReceivingCompany() {
		return get("common_receiving_company");
	}

	public M setWharfBerth(java.lang.String wharfBerth) {
		set("wharf_berth", wharfBerth);
		return (M)this;
	}

	public java.lang.String getWharfBerth() {
		return get("wharf_berth");
	}

}
