package com.shfb.rfid.manage.entity;

public class ComponentStatus {
    private Integer component_status_id;

    private String component_status_name;

    private Integer component_status_order;

    public Integer getComponent_status_id() {
        return component_status_id;
    }

    public void setComponent_status_id(Integer component_status_id) {
        this.component_status_id = component_status_id;
    }

    public String getComponent_status_name() {
        return component_status_name;
    }

    public void setComponent_status_name(String component_status_name) {
        this.component_status_name = component_status_name == null ? null : component_status_name.trim();
    }

    public Integer getComponent_status_order() {
        return component_status_order;
    }

    public void setComponent_status_order(Integer component_status_order) {
        this.component_status_order = component_status_order;
    }
}