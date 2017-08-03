package com.shfb.rfid.manage.entity;

public class Component {
	private Integer component_id;

	private String component_name;

	private String component_num;

	private String component_type;

	private String component_size;

	private Integer bind_status;

	private Integer expedit_status;

	private String expedit_date;

	private Integer component_status_id;

	private Integer check_res;

	private String single_name;

	private Integer pro_id;

	private String floor;

	private String plan_begin_date;

	private String plan_end_date;

	private String real_begin_date;

	private String real_end_date;

	private String order_num;

	private Integer comp_factory_id;

	private Integer order_user_id;

	private String order_time;

	private String product_plan_begin_date;

	private String product_plan_end_date;

	private String drawing;
	
	private String picture_comp_make;

	private String picture_product_accept;
	
	private String picture_receipt;
	
	private String picture_install;
	
	private String order_date;
	
	private String product_explain;
	
	private String concrete_strength;
	
	private String weight;
	
	private String card_num;
	
	private String importDate;
	
	
	
	public String getImportDate() {
		return importDate;
	}

	public void setImportDate(String importDate) {
		this.importDate = importDate;
	}

	public String getConcrete_strength() {
		return concrete_strength;
	}

	public void setConcrete_strength(String concrete_strength) {
		this.concrete_strength = concrete_strength;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public Integer getComponent_id() {
		return component_id;
	}

	public void setComponent_id(Integer component_id) {
		this.component_id = component_id;
	}

	public String getComponent_name() {
		return component_name;
	}

	public void setComponent_name(String component_name) {
		this.component_name = component_name == null ? null : component_name
				.trim();
	}

	public String getComponent_num() {
		return component_num;
	}

	public void setComponent_num(String component_num) {
		this.component_num = component_num == null ? null : component_num
				.trim();
	}

	public String getComponent_type() {
		return component_type;
	}

	public void setComponent_type(String component_type) {
		this.component_type = component_type == null ? null : component_type
				.trim();
	}

	public String getComponent_size() {
		return component_size;
	}

	public void setComponent_size(String component_size) {
		this.component_size = component_size == null ? null : component_size
				.trim();
	}

	public Integer getBind_status() {
		return bind_status;
	}

	public void setBind_status(Integer bind_status) {
		this.bind_status = bind_status;
	}

	public Integer getExpedit_status() {
		return expedit_status;
	}

	public void setExpedit_status(Integer expedit_status) {
		this.expedit_status = expedit_status;
	}

	public String getExpedit_date() {
		return expedit_date;
	}

	public void setExpedit_date(String expedit_date) {
		this.expedit_date = expedit_date;
	}

	public Integer getComponent_status_id() {
		return component_status_id;
	}

	public void setComponent_status_id(Integer component_status_id) {
		this.component_status_id = component_status_id;
	}

	public Integer getCheck_res() {
		return check_res;
	}

	public void setCheck_res(Integer check_res) {
		this.check_res = check_res;
	}

	public String getSingle_name() {
		return single_name;
	}

	public void setSingle_name(String single_name) {
		this.single_name = single_name == null ? null : single_name.trim();
	}

	public Integer getPro_id() {
		return pro_id;
	}

	public void setPro_id(Integer pro_id) {
		this.pro_id = pro_id;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor == null ? null : floor.trim();
	}

	public String getPlan_begin_date() {
		return plan_begin_date;
	}

	public void setPlan_begin_date(String plan_begin_date) {
		this.plan_begin_date = plan_begin_date;
	}

	public String getPlan_end_date() {
		return plan_end_date;
	}

	public void setPlan_end_date(String plan_end_date) {
		this.plan_end_date = plan_end_date;
	}

	public String getReal_begin_date() {
		return real_begin_date;
	}

	public void setReal_begin_date(String real_begin_date) {
		this.real_begin_date = real_begin_date;
	}

	public String getReal_end_date() {
		return real_end_date;
	}

	public void setReal_end_date(String real_end_date) {
		this.real_end_date = real_end_date;
	}

	public String getOrder_num() {
		return order_num;
	}

	public void setOrder_num(String order_num) {
		this.order_num = order_num == null ? null : order_num.trim();
	}

	public Integer getComp_factory_id() {
		return comp_factory_id;
	}

	public void setComp_factory_id(Integer comp_factory_id) {
		this.comp_factory_id = comp_factory_id;
	}

	public Integer getOrder_user_id() {
		return order_user_id;
	}

	public void setOrder_user_id(Integer order_user_id) {
		this.order_user_id = order_user_id;
	}

	public String getOrder_time() {
		return order_time;
	}

	public void setOrder_time(String order_time) {
		this.order_time = order_time;
	}

	public String getProduct_plan_begin_date() {
		return product_plan_begin_date;
	}

	public void setProduct_plan_begin_date(String product_plan_begin_date) {
		this.product_plan_begin_date = product_plan_begin_date;
	}

	public String getProduct_plan_end_date() {
		return product_plan_end_date;
	}

	public void setProduct_plan_end_date(String product_plan_end_date) {
		this.product_plan_end_date = product_plan_end_date;
	}

	public String getDrawing() {
		return drawing;
	}

	public void setDrawing(String drawing) {
		this.drawing = drawing;
	}

	public String getPicture_comp_make() {
		return picture_comp_make;
	}

	public void setPicture_comp_make(String picture_comp_make) {
		this.picture_comp_make = picture_comp_make;
	}

	public String getPicture_product_accept() {
		return picture_product_accept;
	}

	public void setPicture_product_accept(String picture_product_accept) {
		this.picture_product_accept = picture_product_accept;
	}

	public String getPicture_receipt() {
		return picture_receipt;
	}

	public void setPicture_receipt(String picture_receipt) {
		this.picture_receipt = picture_receipt;
	}

	public String getPicture_install() {
		return picture_install;
	}

	public void setPicture_install(String picture_install) {
		this.picture_install = picture_install;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public String getProduct_explain() {
		return product_explain;
	}

	public void setProduct_explain(String product_explain) {
		this.product_explain = product_explain;
	}

	public String getCard_num() {
		return card_num;
	}

	public void setCard_num(String card_num) {
		this.card_num = card_num;
	}
}