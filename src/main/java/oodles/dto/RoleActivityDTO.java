package oodles.dto;

public class RoleActivityDTO {
    Long roleId;
    Long [] activities;

    public Long[] getActivities() {
        return activities;
    }

    public Long getRoleId() {
        return roleId;
    }
}
