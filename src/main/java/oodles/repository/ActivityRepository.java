package oodles.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import oodles.domain.Activity;
import oodles.domain.Role;



public interface ActivityRepository extends JpaRepository<Activity, Serializable>{
	Activity findByActivityName(String activityName);
    Activity findByActivityId(Long id);
    List<Activity> findByRoleList(Role role);
    Activity findByRoleListAndActivityId(Role role, Long id);
    Activity findByRoleListAndActivityName(Role role, String activityName);

}
