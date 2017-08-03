package com.shfb.rfid.manage.entity;

public class Project {
    private Integer pro_id;

    private String pro_name;

    private String contacts_name;

    private String contacts_tel;

    private String contacts_phone;

    private Integer pr_id;

    private Integer ci_id;

    private String address;

    private String details;

    public Integer getPro_id() {
        return pro_id;
    }

    public void setPro_id(Integer pro_id) {
        this.pro_id = pro_id;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name == null ? null : pro_name.trim();
    }

    public String getContacts_name() {
        return contacts_name;
    }

    public void setContacts_name(String contacts_name) {
        this.contacts_name = contacts_name == null ? null : contacts_name.trim();
    }

    public String getContacts_tel() {
        return contacts_tel;
    }

    public void setContacts_tel(String contacts_tel) {
        this.contacts_tel = contacts_tel == null ? null : contacts_tel.trim();
    }

    public String getContacts_phone() {
        return contacts_phone;
    }

    public void setContacts_phone(String contacts_phone) {
        this.contacts_phone = contacts_phone == null ? null : contacts_phone.trim();
    }

    public Integer getPr_id() {
        return pr_id;
    }

    public void setPr_id(Integer pr_id) {
        this.pr_id = pr_id;
    }

    public Integer getCi_id() {
        return ci_id;
    }

    public void setCi_id(Integer ci_id) {
        this.ci_id = ci_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details == null ? null : details.trim();
    }
}