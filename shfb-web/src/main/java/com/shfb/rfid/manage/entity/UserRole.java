package com.shfb.rfid.manage.entity;

public class UserRole {
    private Integer user_role_id;

    private String user_role_name;

    private String menu_ids;

    public Integer getUser_role_id() {
        return user_role_id;
    }

    public void setUser_role_id(Integer user_role_id) {
        this.user_role_id = user_role_id;
    }

    public String getUser_role_name() {
        return user_role_name;
    }

    public void setUser_role_name(String user_role_name) {
        this.user_role_name = user_role_name == null ? null : user_role_name.trim();
    }

    public String getMenu_ids() {
        return menu_ids;
    }

    public void setMenu_ids(String menu_ids) {
        this.menu_ids = menu_ids == null ? null : menu_ids.trim();
    }
}