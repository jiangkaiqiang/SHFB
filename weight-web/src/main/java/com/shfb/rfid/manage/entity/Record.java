package com.shfb.rfid.manage.entity;

public class Record {
    private Integer record_id;

    private String car_num;

    private String entry_time;

    private String entry_weight;

    private String entry_pic;

    private String leave_time;

    private String leave_weight;

    private String leave_pic;

    public Integer getRecord_id() {
        return record_id;
    }

    public void setRecord_id(Integer record_id) {
        this.record_id = record_id;
    }

    public String getCar_num() {
        return car_num;
    }

    public void setCar_num(String car_num) {
        this.car_num = car_num == null ? null : car_num.trim();
    }

    public String getEntry_time() {
        return entry_time;
    }

    public void setEntry_time(String entry_time) {
        this.entry_time = entry_time == null ? null : entry_time.trim();
    }

    public String getEntry_weight() {
        return entry_weight;
    }

    public void setEntry_weight(String entry_weight) {
        this.entry_weight = entry_weight == null ? null : entry_weight.trim();
    }

    public String getEntry_pic() {
        return entry_pic;
    }

    public void setEntry_pic(String entry_pic) {
        this.entry_pic = entry_pic == null ? null : entry_pic.trim();
    }

    public String getLeave_time() {
        return leave_time;
    }

    public void setLeave_time(String leave_time) {
        this.leave_time = leave_time == null ? null : leave_time.trim();
    }

    public String getLeave_weight() {
        return leave_weight;
    }

    public void setLeave_weight(String leave_weight) {
        this.leave_weight = leave_weight == null ? null : leave_weight.trim();
    }

    public String getLeave_pic() {
        return leave_pic;
    }

    public void setLeave_pic(String leave_pic) {
        this.leave_pic = leave_pic == null ? null : leave_pic.trim();
    }
}