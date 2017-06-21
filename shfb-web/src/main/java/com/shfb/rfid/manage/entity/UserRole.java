package com.shfb.rfid.manage.entity;

public class UserRole {
    private Integer user_role_id;

    private String user_role_name;

    private String menu_ids;
    
    private String creater;
    
    private String createTime;
    
    private String user_role_note;

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

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUser_role_note() {
		return user_role_note;
	}

	public void setUser_role_note(String user_role_note) {
		this.user_role_note = user_role_note;
	}
    
}