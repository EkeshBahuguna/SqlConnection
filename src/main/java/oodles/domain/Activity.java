package oodles.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long activityId;
    private String activityName;
    private boolean isActive;


    public Activity(){}

    public Activity(String activityName,boolean isActive){

        this.activityName = activityName;
        this.isActive = isActive;
   
    }

    public Activity(Object object, boolean active, Object object2) {
		// TODO Auto-generated constructor stub
	}

	@ManyToMany(mappedBy = "activities")
    private List<Role> roleList;


    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

}
