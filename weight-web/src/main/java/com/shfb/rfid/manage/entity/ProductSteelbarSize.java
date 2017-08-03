package com.shfb.rfid.manage.entity;

public class ProductSteelbarSize {
    private Integer product_steelbar_size_id;

    private Integer mesh_length_width_pass=2;

    private String mesh_length_width_mark="";

    private Integer mesh_size_pass=2;

    private String mesh_size_mark="";

    private Integer skeleton_length_pass=2;

    private String skeleton_length_mark="";

    private Integer skeleton_width_high_pass=2;

    private String skeleton_width_high_mark="";

    private Integer force_bet_spacing_pass=2;

    private String force_bet_spacing_mark="";

    private Integer force_row_spacing_pass=2;

    private String force_row_spacing_mark="";

    private Integer force_column_beam_pass=2;

    private String force_column_beam_mark="";

    private Integer force_plate_wall_pass=2;

    private String force_plate_wall_mark="";

    private Integer steelbar_spacing_pass=2;

    private String steelbar_spacing_mark="";

    private Integer steelbar_start_point_pass=2;

    private String steelbar_start_point_mark="";

    private Integer component_id;

    private String picture;

    public Integer getProduct_steelbar_size_id() {
        return product_steelbar_size_id;
    }

    public void setProduct_steelbar_size_id(Integer product_steelbar_size_id) {
        this.product_steelbar_size_id = product_steelbar_size_id;
    }

    public Integer getMesh_length_width_pass() {
        return mesh_length_width_pass;
    }

    public void setMesh_length_width_pass(Integer mesh_length_width_pass) {
        this.mesh_length_width_pass = mesh_length_width_pass;
    }

    public String getMesh_length_width_mark() {
        return mesh_length_width_mark;
    }

    public void setMesh_length_width_mark(String mesh_length_width_mark) {
        this.mesh_length_width_mark = mesh_length_width_mark == null ? null : mesh_length_width_mark.trim();
    }

    public Integer getMesh_size_pass() {
        return mesh_size_pass;
    }

    public void setMesh_size_pass(Integer mesh_size_pass) {
        this.mesh_size_pass = mesh_size_pass;
    }

    public String getMesh_size_mark() {
        return mesh_size_mark;
    }

    public void setMesh_size_mark(String mesh_size_mark) {
        this.mesh_size_mark = mesh_size_mark;
    }

    public Integer getSkeleton_length_pass() {
        return skeleton_length_pass;
    }

    public void setSkeleton_length_pass(Integer skeleton_length_pass) {
        this.skeleton_length_pass = skeleton_length_pass;
    }

    public String getSkeleton_length_mark() {
        return skeleton_length_mark;
    }

    public void setSkeleton_length_mark(String skeleton_length_mark) {
        this.skeleton_length_mark = skeleton_length_mark;
    }

    public Integer getSkeleton_width_high_pass() {
        return skeleton_width_high_pass;
    }

    public void setSkeleton_width_high_pass(Integer skeleton_width_high_pass) {
        this.skeleton_width_high_pass = skeleton_width_high_pass;
    }

    public String getSkeleton_width_high_mark() {
        return skeleton_width_high_mark;
    }

    public void setSkeleton_width_high_mark(String skeleton_width_high_mark) {
        this.skeleton_width_high_mark = skeleton_width_high_mark;
    }

    public Integer getForce_bet_spacing_pass() {
        return force_bet_spacing_pass;
    }

    public void setForce_bet_spacing_pass(Integer force_bet_spacing_pass) {
        this.force_bet_spacing_pass = force_bet_spacing_pass;
    }

    public String getForce_bet_spacing_mark() {
        return force_bet_spacing_mark;
    }

    public void setForce_bet_spacing_mark(String force_bet_spacing_mark) {
        this.force_bet_spacing_mark = force_bet_spacing_mark == null ? null : force_bet_spacing_mark.trim();
    }

    public Integer getForce_row_spacing_pass() {
        return force_row_spacing_pass;
    }

    public void setForce_row_spacing_pass(Integer force_row_spacing_pass) {
        this.force_row_spacing_pass = force_row_spacing_pass;
    }

    public String getForce_row_spacing_mark() {
        return force_row_spacing_mark;
    }

    public void setForce_row_spacing_mark(String force_row_spacing_mark) {
        this.force_row_spacing_mark = force_row_spacing_mark == null ? null : force_row_spacing_mark.trim();
    }

    public Integer getForce_column_beam_pass() {
        return force_column_beam_pass;
    }

    public void setForce_column_beam_pass(Integer force_column_beam_pass) {
        this.force_column_beam_pass = force_column_beam_pass;
    }

    public String getForce_column_beam_mark() {
        return force_column_beam_mark;
    }

    public void setForce_column_beam_mark(String force_column_beam_mark) {
        this.force_column_beam_mark = force_column_beam_mark == null ? null : force_column_beam_mark.trim();
    }

    public Integer getForce_plate_wall_pass() {
        return force_plate_wall_pass;
    }

    public void setForce_plate_wall_pass(Integer force_plate_wall_pass) {
        this.force_plate_wall_pass = force_plate_wall_pass;
    }

    public String getForce_plate_wall_mark() {
        return force_plate_wall_mark;
    }

    public void setForce_plate_wall_mark(String force_plate_wall_mark) {
        this.force_plate_wall_mark = force_plate_wall_mark == null ? null : force_plate_wall_mark.trim();
    }

    public Integer getSteelbar_spacing_pass() {
        return steelbar_spacing_pass;
    }

    public void setSteelbar_spacing_pass(Integer steelbar_spacing_pass) {
        this.steelbar_spacing_pass = steelbar_spacing_pass;
    }

    public String getSteelbar_spacing_mark() {
        return steelbar_spacing_mark;
    }

    public void setSteelbar_spacing_mark(String steelbar_spacing_mark) {
        this.steelbar_spacing_mark = steelbar_spacing_mark == null ? null : steelbar_spacing_mark.trim();
    }

    public Integer getSteelbar_start_point_pass() {
        return steelbar_start_point_pass;
    }

    public void setSteelbar_start_point_pass(Integer steelbar_start_point_pass) {
        this.steelbar_start_point_pass = steelbar_start_point_pass;
    }

    public String getSteelbar_start_point_mark() {
        return steelbar_start_point_mark;
    }

    public void setSteelbar_start_point_mark(String steelbar_start_point_mark) {
        this.steelbar_start_point_mark = steelbar_start_point_mark == null ? null : steelbar_start_point_mark.trim();
    }

    public Integer getComponent_id() {
        return component_id;
    }

    public void setComponent_id(Integer component_id) {
        this.component_id = component_id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }
}