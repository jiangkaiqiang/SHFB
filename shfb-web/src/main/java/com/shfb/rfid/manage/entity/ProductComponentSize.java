package com.shfb.rfid.manage.entity;

public class ProductComponentSize {
    private Integer product_component_size_id;

    private Integer length_small_pass=2;

    private String length_small_mark="";

    private Integer length_middle_pass=2;

    private String length_middle_mark="";

    private Integer length_big_pass=2;

    private String length_big_mark="";

    private Integer height_section_size_pass=2;

    private String height_section_size_mark="";

    private Integer height_thickness_wallboard_pass=2;

    private String height_thickness_wallboard_mark="";

    private Integer planeness_inner_surface_pass=2;

    private String planeness_inner_surface_mark="";

    private Integer planeness_out_surface_pass=2;

    private String planeness_out_surface_mark="";

    private Integer lateral_bending_column_pass=2;

    private String lateral_bending_column_mark="";

    private Integer lateral_bending_wallboard_pass=2;

    private String lateral_bending_wallboard_mark="";

    private Integer warping_plate_pass=2;

    private String warping_plate_mark="";

    private Integer warping_wallboard_pass=2;

    private String warping_wallboard_mark="";

    private Integer diagonal_difference_plate_pass=2;

    private String diagonal_difference_plate_mark="";

    private Integer diagonal_difference_wallboard_pass=2;

    private String diagonal_difference_wallboard_mark="";

    private Integer deflection_arch_pass=2;

    private String deflection_arch_mark="";

    private Integer deflection_drooping_pass=2;

    private String deflection_drooping_mark="";

    private Integer preformed_hole_centerline_pass=2;

    private String preformed_hole_centerline_mark="";

    private Integer preformed_hole_holesize_pass=2;

    private String preformed_hole_holesize_mark="";

    private Integer reserved_hole_centerline_pass=2;

    private String reserved_hole_centerline_mark="";

    private Integer reserved_hole_entrancesize_pass=2;

    private String reserved_hole_entrancesize_mark="";

    private Integer door_window_centerline_pass=2;

    private String door_window_centerline_mark="";

    private Integer door_window_height_pass=2;

    private String door_window_height_mark="";

    private Integer embedded_parts_anchorcenterline_pass=2;

    private String embedded_parts_anchorcenterline_mark="";

    private Integer embedded_parts_steelbarplane_pass=2;

    private String embedded_parts_steelbarplane_mark="";

    private Integer embedded_parts_boltcenterline_pass=2;

    private String embedded_parts_boltcenterline_mark="";

    private Integer embedded_parts_exposedlength_pass=2;

    private String embedded_parts_exposedlength_mark="";

    private Integer embedded_parts_nutcenterline_pass=2;

    private String embedded_parts_nutcenterline_mark="";

    private Integer embedded_parts_concreteheight_pass=2;

    private String embedded_parts_concreteheight_mark="";

    private Integer embedded_parts_centrallineposition_pass=2;

    private String embedded_parts_centrallineposition_mark="";

    private Integer embedded_parts_componentheight_pass=2;

    private String embedded_parts_componentheight_mark="";

    private Integer embedded_reinforcement_centerline_pass=2;

    private String embedded_reinforcement_centerline_mark="";

    private Integer embedded_reinforcement_length_pass=2;

    private String embedded_reinforcement_length_mark="";

    private Integer keyway_centerline_pass=2;

    private String keyway_centerline_mark="";

    private Integer keyway_length_pass=2;

    private String keyway_length_mark="";

    private Integer component_id;

    private String picture;
    
    private Integer product_result_pass=2;
    
    private String product_result_mark="";

    public Integer getProduct_result_pass() {
		return product_result_pass;
	}

	public void setProduct_result_pass(Integer product_result_pass) {
		this.product_result_pass = product_result_pass;
	}

	public String getProduct_result_mark() {
		return product_result_mark;
	}

	public void setProduct_result_mark(String product_result_mark) {
		this.product_result_mark = product_result_mark;
	}

	public Integer getProduct_component_size_id() {
        return product_component_size_id;
    }

    public void setProduct_component_size_id(Integer product_component_size_id) {
        this.product_component_size_id = product_component_size_id;
    }

    public Integer getLength_small_pass() {
        return length_small_pass;
    }

    public void setLength_small_pass(Integer length_small_pass) {
        this.length_small_pass = length_small_pass;
    }

    public String getLength_small_mark() {
        return length_small_mark;
    }

    public void setLength_small_mark(String length_small_mark) {
        this.length_small_mark = length_small_mark == null ? null : length_small_mark.trim();
    }

    public Integer getLength_middle_pass() {
        return length_middle_pass;
    }

    public void setLength_middle_pass(Integer length_middle_pass) {
        this.length_middle_pass = length_middle_pass;
    }

    public String getLength_middle_mark() {
        return length_middle_mark;
    }

    public void setLength_middle_mark(String length_middle_mark) {
        this.length_middle_mark = length_middle_mark == null ? null : length_middle_mark.trim();
    }

    public Integer getLength_big_pass() {
        return length_big_pass;
    }

    public void setLength_big_pass(Integer length_big_pass) {
        this.length_big_pass = length_big_pass;
    }

    public String getLength_big_mark() {
        return length_big_mark;
    }

    public void setLength_big_mark(String length_big_mark) {
        this.length_big_mark = length_big_mark == null ? null : length_big_mark.trim();
    }

    public Integer getHeight_section_size_pass() {
        return height_section_size_pass;
    }

    public void setHeight_section_size_pass(Integer height_section_size_pass) {
        this.height_section_size_pass = height_section_size_pass;
    }

    public String getHeight_section_size_mark() {
        return height_section_size_mark;
    }

    public void setHeight_section_size_mark(String height_section_size_mark) {
        this.height_section_size_mark = height_section_size_mark == null ? null : height_section_size_mark.trim();
    }

    public Integer getHeight_thickness_wallboard_pass() {
        return height_thickness_wallboard_pass;
    }

    public void setHeight_thickness_wallboard_pass(Integer height_thickness_wallboard_pass) {
        this.height_thickness_wallboard_pass = height_thickness_wallboard_pass;
    }

    public String getHeight_thickness_wallboard_mark() {
        return height_thickness_wallboard_mark;
    }

    public void setHeight_thickness_wallboard_mark(String height_thickness_wallboard_mark) {
        this.height_thickness_wallboard_mark = height_thickness_wallboard_mark == null ? null : height_thickness_wallboard_mark.trim();
    }

    public Integer getPlaneness_inner_surface_pass() {
        return planeness_inner_surface_pass;
    }

    public void setPlaneness_inner_surface_pass(Integer planeness_inner_surface_pass) {
        this.planeness_inner_surface_pass = planeness_inner_surface_pass;
    }

    public String getPlaneness_inner_surface_mark() {
        return planeness_inner_surface_mark;
    }

    public void setPlaneness_inner_surface_mark(String planeness_inner_surface_mark) {
        this.planeness_inner_surface_mark = planeness_inner_surface_mark == null ? null : planeness_inner_surface_mark.trim();
    }

    public Integer getPlaneness_out_surface_pass() {
        return planeness_out_surface_pass;
    }

    public void setPlaneness_out_surface_pass(Integer planeness_out_surface_pass) {
        this.planeness_out_surface_pass = planeness_out_surface_pass;
    }

    public String getPlaneness_out_surface_mark() {
        return planeness_out_surface_mark;
    }

    public void setPlaneness_out_surface_mark(String planeness_out_surface_mark) {
        this.planeness_out_surface_mark = planeness_out_surface_mark == null ? null : planeness_out_surface_mark.trim();
    }

    public Integer getLateral_bending_column_pass() {
        return lateral_bending_column_pass;
    }

    public void setLateral_bending_column_pass(Integer lateral_bending_column_pass) {
        this.lateral_bending_column_pass = lateral_bending_column_pass;
    }

    public String getLateral_bending_column_mark() {
        return lateral_bending_column_mark;
    }

    public void setLateral_bending_column_mark(String lateral_bending_column_mark) {
        this.lateral_bending_column_mark = lateral_bending_column_mark == null ? null : lateral_bending_column_mark.trim();
    }

    public Integer getLateral_bending_wallboard_pass() {
        return lateral_bending_wallboard_pass;
    }

    public void setLateral_bending_wallboard_pass(Integer lateral_bending_wallboard_pass) {
        this.lateral_bending_wallboard_pass = lateral_bending_wallboard_pass;
    }

    public String getLateral_bending_wallboard_mark() {
        return lateral_bending_wallboard_mark;
    }

    public void setLateral_bending_wallboard_mark(String lateral_bending_wallboard_mark) {
        this.lateral_bending_wallboard_mark = lateral_bending_wallboard_mark == null ? null : lateral_bending_wallboard_mark.trim();
    }

    public Integer getWarping_plate_pass() {
        return warping_plate_pass;
    }

    public void setWarping_plate_pass(Integer warping_plate_pass) {
        this.warping_plate_pass = warping_plate_pass;
    }

    public String getWarping_plate_mark() {
        return warping_plate_mark;
    }

    public void setWarping_plate_mark(String warping_plate_mark) {
        this.warping_plate_mark = warping_plate_mark == null ? null : warping_plate_mark.trim();
    }

    public Integer getWarping_wallboard_pass() {
        return warping_wallboard_pass;
    }

    public void setWarping_wallboard_pass(Integer warping_wallboard_pass) {
        this.warping_wallboard_pass = warping_wallboard_pass;
    }

    public String getWarping_wallboard_mark() {
        return warping_wallboard_mark;
    }

    public void setWarping_wallboard_mark(String warping_wallboard_mark) {
        this.warping_wallboard_mark = warping_wallboard_mark == null ? null : warping_wallboard_mark.trim();
    }

    public Integer getDiagonal_difference_plate_pass() {
        return diagonal_difference_plate_pass;
    }

    public void setDiagonal_difference_plate_pass(Integer diagonal_difference_plate_pass) {
        this.diagonal_difference_plate_pass = diagonal_difference_plate_pass;
    }

    public String getDiagonal_difference_plate_mark() {
        return diagonal_difference_plate_mark;
    }

    public void setDiagonal_difference_plate_mark(String diagonal_difference_plate_mark) {
        this.diagonal_difference_plate_mark = diagonal_difference_plate_mark == null ? null : diagonal_difference_plate_mark.trim();
    }

    public Integer getDiagonal_difference_wallboard_pass() {
        return diagonal_difference_wallboard_pass;
    }

    public void setDiagonal_difference_wallboard_pass(Integer diagonal_difference_wallboard_pass) {
        this.diagonal_difference_wallboard_pass = diagonal_difference_wallboard_pass;
    }

    public String getDiagonal_difference_wallboard_mark() {
        return diagonal_difference_wallboard_mark;
    }

    public void setDiagonal_difference_wallboard_mark(String diagonal_difference_wallboard_mark) {
        this.diagonal_difference_wallboard_mark = diagonal_difference_wallboard_mark == null ? null : diagonal_difference_wallboard_mark.trim();
    }

    public Integer getDeflection_arch_pass() {
        return deflection_arch_pass;
    }

    public void setDeflection_arch_pass(Integer deflection_arch_pass) {
        this.deflection_arch_pass = deflection_arch_pass;
    }

    public String getDeflection_arch_mark() {
        return deflection_arch_mark;
    }

    public void setDeflection_arch_mark(String deflection_arch_mark) {
        this.deflection_arch_mark = deflection_arch_mark == null ? null : deflection_arch_mark.trim();
    }

    public Integer getDeflection_drooping_pass() {
        return deflection_drooping_pass;
    }

    public void setDeflection_drooping_pass(Integer deflection_drooping_pass) {
        this.deflection_drooping_pass = deflection_drooping_pass;
    }

    public String getDeflection_drooping_mark() {
        return deflection_drooping_mark;
    }

    public void setDeflection_drooping_mark(String deflection_drooping_mark) {
        this.deflection_drooping_mark = deflection_drooping_mark == null ? null : deflection_drooping_mark.trim();
    }

    public Integer getPreformed_hole_centerline_pass() {
        return preformed_hole_centerline_pass;
    }

    public void setPreformed_hole_centerline_pass(Integer preformed_hole_centerline_pass) {
        this.preformed_hole_centerline_pass = preformed_hole_centerline_pass;
    }

    public String getPreformed_hole_centerline_mark() {
        return preformed_hole_centerline_mark;
    }

    public void setPreformed_hole_centerline_mark(String preformed_hole_centerline_mark) {
        this.preformed_hole_centerline_mark = preformed_hole_centerline_mark == null ? null : preformed_hole_centerline_mark.trim();
    }

    public Integer getPreformed_hole_holesize_pass() {
        return preformed_hole_holesize_pass;
    }

    public void setPreformed_hole_holesize_pass(Integer preformed_hole_holesize_pass) {
        this.preformed_hole_holesize_pass = preformed_hole_holesize_pass;
    }

    public String getPreformed_hole_holesize_mark() {
        return preformed_hole_holesize_mark;
    }

    public void setPreformed_hole_holesize_mark(String preformed_hole_holesize_mark) {
        this.preformed_hole_holesize_mark = preformed_hole_holesize_mark == null ? null : preformed_hole_holesize_mark.trim();
    }

    public Integer getReserved_hole_centerline_pass() {
        return reserved_hole_centerline_pass;
    }

    public void setReserved_hole_centerline_pass(Integer reserved_hole_centerline_pass) {
        this.reserved_hole_centerline_pass = reserved_hole_centerline_pass;
    }

    public String getReserved_hole_centerline_mark() {
        return reserved_hole_centerline_mark;
    }

    public void setReserved_hole_centerline_mark(String reserved_hole_centerline_mark) {
        this.reserved_hole_centerline_mark = reserved_hole_centerline_mark == null ? null : reserved_hole_centerline_mark.trim();
    }

    public Integer getReserved_hole_entrancesize_pass() {
        return reserved_hole_entrancesize_pass;
    }

    public void setReserved_hole_entrancesize_pass(Integer reserved_hole_entrancesize_pass) {
        this.reserved_hole_entrancesize_pass = reserved_hole_entrancesize_pass;
    }

    public String getReserved_hole_entrancesize_mark() {
        return reserved_hole_entrancesize_mark;
    }

    public void setReserved_hole_entrancesize_mark(String reserved_hole_entrancesize_mark) {
        this.reserved_hole_entrancesize_mark = reserved_hole_entrancesize_mark == null ? null : reserved_hole_entrancesize_mark.trim();
    }

    public Integer getDoor_window_centerline_pass() {
        return door_window_centerline_pass;
    }

    public void setDoor_window_centerline_pass(Integer door_window_centerline_pass) {
        this.door_window_centerline_pass = door_window_centerline_pass;
    }

    public String getDoor_window_centerline_mark() {
        return door_window_centerline_mark;
    }

    public void setDoor_window_centerline_mark(String door_window_centerline_mark) {
        this.door_window_centerline_mark = door_window_centerline_mark == null ? null : door_window_centerline_mark.trim();
    }

    public Integer getDoor_window_height_pass() {
        return door_window_height_pass;
    }

    public void setDoor_window_height_pass(Integer door_window_height_pass) {
        this.door_window_height_pass = door_window_height_pass;
    }

    public String getDoor_window_height_mark() {
        return door_window_height_mark;
    }

    public void setDoor_window_height_mark(String door_window_height_mark) {
        this.door_window_height_mark = door_window_height_mark == null ? null : door_window_height_mark.trim();
    }

    public Integer getEmbedded_parts_anchorcenterline_pass() {
        return embedded_parts_anchorcenterline_pass;
    }

    public void setEmbedded_parts_anchorcenterline_pass(Integer embedded_parts_anchorcenterline_pass) {
        this.embedded_parts_anchorcenterline_pass = embedded_parts_anchorcenterline_pass;
    }

    public String getEmbedded_parts_anchorcenterline_mark() {
        return embedded_parts_anchorcenterline_mark;
    }

    public void setEmbedded_parts_anchorcenterline_mark(String embedded_parts_anchorcenterline_mark) {
        this.embedded_parts_anchorcenterline_mark = embedded_parts_anchorcenterline_mark == null ? null : embedded_parts_anchorcenterline_mark.trim();
    }

    public Integer getEmbedded_parts_steelbarplane_pass() {
        return embedded_parts_steelbarplane_pass;
    }

    public void setEmbedded_parts_steelbarplane_pass(Integer embedded_parts_steelbarplane_pass) {
        this.embedded_parts_steelbarplane_pass = embedded_parts_steelbarplane_pass;
    }

    public String getEmbedded_parts_steelbarplane_mark() {
        return embedded_parts_steelbarplane_mark;
    }

    public void setEmbedded_parts_steelbarplane_mark(String embedded_parts_steelbarplane_mark) {
        this.embedded_parts_steelbarplane_mark = embedded_parts_steelbarplane_mark == null ? null : embedded_parts_steelbarplane_mark.trim();
    }

    public Integer getEmbedded_parts_boltcenterline_pass() {
        return embedded_parts_boltcenterline_pass;
    }

    public void setEmbedded_parts_boltcenterline_pass(Integer embedded_parts_boltcenterline_pass) {
        this.embedded_parts_boltcenterline_pass = embedded_parts_boltcenterline_pass;
    }

    public String getEmbedded_parts_boltcenterline_mark() {
        return embedded_parts_boltcenterline_mark;
    }

    public void setEmbedded_parts_boltcenterline_mark(String embedded_parts_boltcenterline_mark) {
        this.embedded_parts_boltcenterline_mark = embedded_parts_boltcenterline_mark == null ? null : embedded_parts_boltcenterline_mark.trim();
    }

    public Integer getEmbedded_parts_exposedlength_pass() {
        return embedded_parts_exposedlength_pass;
    }

    public void setEmbedded_parts_exposedlength_pass(Integer embedded_parts_exposedlength_pass) {
        this.embedded_parts_exposedlength_pass = embedded_parts_exposedlength_pass;
    }

    public String getEmbedded_parts_exposedlength_mark() {
        return embedded_parts_exposedlength_mark;
    }

    public void setEmbedded_parts_exposedlength_mark(String embedded_parts_exposedlength_mark) {
        this.embedded_parts_exposedlength_mark = embedded_parts_exposedlength_mark == null ? null : embedded_parts_exposedlength_mark.trim();
    }

    public Integer getEmbedded_parts_nutcenterline_pass() {
        return embedded_parts_nutcenterline_pass;
    }

    public void setEmbedded_parts_nutcenterline_pass(Integer embedded_parts_nutcenterline_pass) {
        this.embedded_parts_nutcenterline_pass = embedded_parts_nutcenterline_pass;
    }

    public String getEmbedded_parts_nutcenterline_mark() {
        return embedded_parts_nutcenterline_mark;
    }

    public void setEmbedded_parts_nutcenterline_mark(String embedded_parts_nutcenterline_mark) {
        this.embedded_parts_nutcenterline_mark = embedded_parts_nutcenterline_mark == null ? null : embedded_parts_nutcenterline_mark.trim();
    }

    public Integer getEmbedded_parts_concreteheight_pass() {
        return embedded_parts_concreteheight_pass;
    }

    public void setEmbedded_parts_concreteheight_pass(Integer embedded_parts_concreteheight_pass) {
        this.embedded_parts_concreteheight_pass = embedded_parts_concreteheight_pass;
    }

    public String getEmbedded_parts_concreteheight_mark() {
        return embedded_parts_concreteheight_mark;
    }

    public void setEmbedded_parts_concreteheight_mark(String embedded_parts_concreteheight_mark) {
        this.embedded_parts_concreteheight_mark = embedded_parts_concreteheight_mark == null ? null : embedded_parts_concreteheight_mark.trim();
    }

    public Integer getEmbedded_parts_centrallineposition_pass() {
        return embedded_parts_centrallineposition_pass;
    }

    public void setEmbedded_parts_centrallineposition_pass(Integer embedded_parts_centrallineposition_pass) {
        this.embedded_parts_centrallineposition_pass = embedded_parts_centrallineposition_pass;
    }

    public String getEmbedded_parts_centrallineposition_mark() {
        return embedded_parts_centrallineposition_mark;
    }

    public void setEmbedded_parts_centrallineposition_mark(String embedded_parts_centrallineposition_mark) {
        this.embedded_parts_centrallineposition_mark = embedded_parts_centrallineposition_mark == null ? null : embedded_parts_centrallineposition_mark.trim();
    }

    public Integer getEmbedded_parts_componentheight_pass() {
        return embedded_parts_componentheight_pass;
    }

    public void setEmbedded_parts_componentheight_pass(Integer embedded_parts_componentheight_pass) {
        this.embedded_parts_componentheight_pass = embedded_parts_componentheight_pass;
    }

    public String getEmbedded_parts_componentheight_mark() {
        return embedded_parts_componentheight_mark;
    }

    public void setEmbedded_parts_componentheight_mark(String embedded_parts_componentheight_mark) {
        this.embedded_parts_componentheight_mark = embedded_parts_componentheight_mark == null ? null : embedded_parts_componentheight_mark.trim();
    }

    public Integer getEmbedded_reinforcement_centerline_pass() {
        return embedded_reinforcement_centerline_pass;
    }

    public void setEmbedded_reinforcement_centerline_pass(Integer embedded_reinforcement_centerline_pass) {
        this.embedded_reinforcement_centerline_pass = embedded_reinforcement_centerline_pass;
    }

    public String getEmbedded_reinforcement_centerline_mark() {
        return embedded_reinforcement_centerline_mark;
    }

    public void setEmbedded_reinforcement_centerline_mark(String embedded_reinforcement_centerline_mark) {
        this.embedded_reinforcement_centerline_mark = embedded_reinforcement_centerline_mark == null ? null : embedded_reinforcement_centerline_mark.trim();
    }

    public Integer getEmbedded_reinforcement_length_pass() {
        return embedded_reinforcement_length_pass;
    }

    public void setEmbedded_reinforcement_length_pass(Integer embedded_reinforcement_length_pass) {
        this.embedded_reinforcement_length_pass = embedded_reinforcement_length_pass;
    }

    public String getEmbedded_reinforcement_length_mark() {
        return embedded_reinforcement_length_mark;
    }

    public void setEmbedded_reinforcement_length_mark(String embedded_reinforcement_length_mark) {
        this.embedded_reinforcement_length_mark = embedded_reinforcement_length_mark == null ? null : embedded_reinforcement_length_mark.trim();
    }

    public Integer getKeyway_centerline_pass() {
        return keyway_centerline_pass;
    }

    public void setKeyway_centerline_pass(Integer keyway_centerline_pass) {
        this.keyway_centerline_pass = keyway_centerline_pass;
    }

    public String getKeyway_centerline_mark() {
        return keyway_centerline_mark;
    }

    public void setKeyway_centerline_mark(String keyway_centerline_mark) {
        this.keyway_centerline_mark = keyway_centerline_mark == null ? null : keyway_centerline_mark.trim();
    }

    public Integer getKeyway_length_pass() {
        return keyway_length_pass;
    }

    public void setKeyway_length_pass(Integer keyway_length_pass) {
        this.keyway_length_pass = keyway_length_pass;
    }

    public String getKeyway_length_mark() {
        return keyway_length_mark;
    }

    public void setKeyway_length_mark(String keyway_length_mark) {
        this.keyway_length_mark = keyway_length_mark == null ? null : keyway_length_mark.trim();
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