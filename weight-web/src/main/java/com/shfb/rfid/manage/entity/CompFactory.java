package com.shfb.rfid.manage.entity;

public class CompFactory {
    private Integer comp_factory_id;

    private String comp_factory_name;

    private String contacts_name;

    private String contacts_tel;

    private String contacts_phone;

    private Integer pr_id;

    private Integer ci_id;

    private String address;

    public Integer getComp_factory_id() {
        return comp_factory_id;
    }

    public void setComp_factory_id(Integer comp_factory_id) {
        this.comp_factory_id = comp_factory_id;
    }

    public String getComp_factory_name() {
        return comp_factory_name;
    }

    public void setComp_factory_name(String comp_factory_name) {
        this.comp_factory_name = comp_factory_name == null ? null : comp_factory_name.trim();
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
}