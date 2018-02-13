package oodles.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oodles.domain.Activity;
import oodles.domain.Role;
import oodles.message.Message;
import oodles.repository.ActivityRepository;
import oodles.repository.RoleRepository;



@Service
public class ActivityService {
	
	private static Logger LOGGER = LoggerFactory.getLogger(RoleService.class); 
	
	@Autowired
	ActivityRepository activityRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	MessageService messageService;
	
	//Add Activity
	public Map<String, Object> addActivity(String activityName) {

		Map<String, Object> result = new HashMap<String, Object>();
		Boolean isSuccess = false;
		try{
		if (activityName !=null || activityName != ""){
			Activity activity=activityRepository.findByActivityName(activityName);
		
			if(activity == null){
				Activity a = new Activity();
				a.setActivityName(activityName);
				activityRepository.save(a);
				isSuccess = true;
				LOGGER.info("Role saved successfully {}", activityName);
				result.put("message", "Success");
			}else {
				result.put("message", "Data already exist");
			}
		}
		} catch(Exception e){
			LOGGER.warn(e.getMessage());
			result.put("message", "Internal Server Error");
		}
		result.put("isSuccess", isSuccess);
		
		
		return result;
	}
	
	// Display
	public Map<String, Object> getService() {

		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> allRoles = new ArrayList<Map<String, Object>>();

		Boolean isSuccess = false;
		try{
			List<Activity> activity =  activityRepository.findAll();
			for (Activity a : activity){
				List<Map<String, Object>> allActivities = new ArrayList<Map<String, Object>>();
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("serviceId", a.getActivityId());
				data.put("service", a.getActivityName());
				List<oodles.domain.Role> activities =  a.getRoleList();
				for(oodles.domain.Role act : activities){
					Map<String, Object> data1 = new HashMap<String, Object>();
					data1.put("activityId", act.getId());
					data1.put("activityName", act.getRole());
					allActivities.add(data1);
				}
				LOGGER.info("Roles list fetched successfully");
				data.put("activities", allActivities);
				allRoles.add(data);
			}
			isSuccess = true;
			result.put("roleList", allRoles);
			result.put("message", messageService.getMessage(Message.SUCCESS));
		} catch(Exception e){
			LOGGER.warn(e.getMessage());
			result.put("message", messageService.getMessage(Message.INTERNAL_SERVER_ERROR));
		}
		result.put("isSuccess", isSuccess);
		return result;
	}
	
	
//Update 
	public Map<String, Object> changeStatus(Map<String, String> data) {
		Map<String, Object> result = new HashMap<String, Object>();
		Boolean isSuccess = false;
		try{
		Long id = Long.parseLong(data.get("id"));
		Boolean isActive = Boolean.parseBoolean(data.get("isActive"));
		if (id !=null){
			Role role =  roleRepository.findById(id);
			if(role != null && role.getActive() != isActive){
				role.setActive(isActive);
				roleRepository.save(role);
				LOGGER.info("Role updated successfully");
				isSuccess = true;
				result.put("message", messageService.getMessage(Message.SUCCESS));
			}else {
				result.put("message", messageService.getMessage(Message.ALREADY_EXIST));
			}
		}
		} catch(Exception e){
			LOGGER.warn(e.getMessage());
			result.put("message", messageService.getMessage(Message.INTERNAL_SERVER_ERROR));
		}
		result.put("isSuccess", isSuccess);
		return result;
	}
	
	//Assign Activity
	public Map<String, Object> assignActivities(oodles.dto.RoleActivityDTO dto) {

        Map<String, Object> result = new HashMap<String, Object>();
        List<Activity> activities = new ArrayList<>();
        Boolean isSuccess = false;
        Long roleId = dto.getRoleId();
        
        Long [] dtoActivities = dto.getActivities();
        if (roleId != null && dtoActivities != null){
            Role role = roleRepository.findById(roleId);

            try{
                for(Long activityId : dtoActivities){
                    Activity activity = activityRepository.findByActivityId(activityId);

                    if(activity == null){
                        LOGGER.info("No such activity found");
                        result.put("error",activityId);
                        result.put("message", messageService.getMessage(Message.ERROR));
                        result.put("isSuccess", isSuccess);
                        return result;
                    }
                    activities.add(activity);
                }
                role.setActivities(activities);
                roleRepository.save(role);
                LOGGER.info("activities assigned successfully");
                isSuccess = true;
                result.put("message", messageService.getMessage(Message.SUCCESS));
            } catch(Exception e){
                LOGGER.warn(e.getMessage());
                result.put("message", messageService.getMessage(Message.INTERNAL_SERVER_ERROR));
            }
        }else{
        	
        	
        	
        	
            LOGGER.info("Invalid data");
            result.put("message", messageService.getMessage(Message.INVALID_INPUTS));
        }
        result.put("isSuccess", isSuccess);
        return result;
        
        
        
       
    }
	
	
	

	/*public Map<String, Object> addRole(String roleName) {

		Map<String, Object> result = new HashMap<String, Object>();
		Boolean isSuccess = false;
		try{
		if (roleName !=null || roleName != ""){
			Role role =  roleRepository.findByRole(roleName);
			if(role == null){
				Role r = new Role();
				r.setRole(roleName);
				roleRepository.save(r);
				isSuccess = true;
				LOGGER.info("Role saved successfully {}", roleName);
				result.put("message", "Success");
			}else {
				result.put("message", "Data already exist");
			}
		}
		} catch(Exception e){
			LOGGER.warn(e.getMessage());
			result.put("message", "Internal Server Error");
		}
		result.put("isSuccess", isSuccess);
		return result;
	}

	

	

	public Map<String, Object> getActiveRoles() {

		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> allRoles = new ArrayList<Map<String, Object>>();

		Boolean isSuccess = false;
		try{
			List<Role> role =  roleRepository.findByIsActiveTrue();
			for (Role r : role){
				List<Map<String, Object>> allActivities = new ArrayList<Map<String, Object>>();
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("roleId", r.getId());
				data.put("role", r.getRole());
				List<oodles.domain.Activity> activities =  r.getActivities();
				for(oodles.domain.Activity activity : activities){
					Map<String, Object> data1 = new HashMap<String, Object>();
					data1.put("activityId", activity.getActivityId());
					data1.put("activityName", activity.getActivityName());
					allActivities.add(data1);
				}
				LOGGER.info("Active roles list fetched successfully");
				data.put("activities", allActivities);
				allRoles.add(data);
			}
			isSuccess = true;
			result.put("roleList", allRoles);
			result.put("message", messageService.getMessage(Message.SUCCESS));
		} catch(Exception e){
			LOGGER.warn(e.getMessage());
			result.put("message", messageService.getMessage(Message.INTERNAL_SERVER_ERROR));
		}
		result.put("isSuccess", isSuccess);
		return result;
	}

	public Map<String, Object> getRolesByRoleName(String RoleName) {

		Map<String, Object> result = new HashMap<String, Object>();
		Boolean isSuccess = false;
		try{
			Role role =  roleRepository.findByRole(RoleName);
			LOGGER.info("Role fetched successfully");
			isSuccess = true;
			result.put("data", role);
			result.put("message", messageService.getMessage(Message.SUCCESS));
		} catch(Exception e){
			LOGGER.warn(e.getMessage());
			result.put("message", messageService.getMessage(Message.INTERNAL_SERVER_ERROR));
		}
		result.put("isSuccess", isSuccess);
		return result;
	}

	public Map<String, Object> getRolesById(Long id) {

		Map<String, Object> result = new HashMap<String, Object>();
		Boolean isSuccess = false;
		try{
			Role role =  roleRepository.findById(id);
			LOGGER.info("Role fetched successfully");
			isSuccess = true;
			result.put("data", role);
			result.put("message", messageService.getMessage(Message.SUCCESS));
		} catch(Exception e){
			LOGGER.warn(e.getMessage());
			result.put("message", messageService.getMessage(Message.INTERNAL_SERVER_ERROR));
		}
		result.put("isSuccess", isSuccess);
		return result;
	}*/
}
