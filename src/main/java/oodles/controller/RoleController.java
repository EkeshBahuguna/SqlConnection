package oodles.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import oodles.domain.Role;
import oodles.mapping.UrlMapping;
import oodles.repository.RoleRepository;
import oodles.service.RoleService;
import oodles.util.Activity;
import oodles.util.ResponseHandler;





@RestController

public class RoleController {

private static Logger LOGGER = LoggerFactory.getLogger(RoleController.class);
	@Autowired
	RoleService roleService;
	
	
	
	@Autowired
	RoleRepository roleRepository;
	
	
	@Activity
	@RequestMapping(value = UrlMapping.ADD_ROLE, method = RequestMethod.POST)
	public ResponseEntity<Object> addRole(@RequestBody String role) {
		Map<String, Object> result = null;
		try {
			result = roleService.addRole(role);
			if(result.get("isSuccess").equals(true)){
			return ResponseHandler.generateResponse(HttpStatus.OK, true, result.get("message").toString(), result);
			}
			else
				return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, result.get("message").toString(), result);		
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage(), result);
		}
	}

	@Activity
	@RequestMapping(value = UrlMapping.ACTIVATE_OR_DEACTIVATE_ROLE, method = RequestMethod.POST)
	public ResponseEntity<Object> changeStatus(@RequestBody Map<String, String> data) {
		Map<String, Object> result = null;
		try {
			result = roleService.changeStatus(data);
			if(result.get("isSuccess").equals(true)){
			return ResponseHandler.generateResponse(HttpStatus.OK, true, result.get("message").toString(), result);
			}
			else
				return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, result.get("message").toString(), result);		
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage(), result);
		}
	}
	

	@Activity
	@RequestMapping(value = UrlMapping.GET_ROLES, method = RequestMethod.GET)
	public ResponseEntity<Object> getRoles() {
		List<Role> result = null;
		try {
			result = roleRepository.findAll();
			
			if(result!=null){

				return ResponseHandler.generateResponse(HttpStatus.OK, true, "Success".toString(), result);
			}
			else
				return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, "failer", result);		
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage(), result);
		}
	}

	
	/*@Activity
//	@PreAuthorize("hasAuthority('getActiveRoles')")
	@RequestMapping(value = UrlMapping.GET_ACTIVE_ROLES, method = RequestMethod.GET)
	public ResponseEntity<Object> getActiveRoles() {
			Map<String, Object> result = null;
			try {
				result = roleService.getActiveRoles();
				if (result.get("isSuccess").equals(true)) {
					return ResponseHandler.generateResponse(HttpStatus.OK, true, result.get("message").toString(), result);
				} else
					return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, result.get("message").toString(), result);
			} catch (Exception e) {
				return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage(), result);
			}
	}*/

	
	@Activity
	@RequestMapping(value = UrlMapping.GET_ROLE_BY_NAME, method = RequestMethod.GET)
	public ResponseEntity<Object> getRoleByRoleName(@RequestParam("role") String role) {
		Map<String, Object> result = null;
		try {
			String roleName = role;
			if (roleName != null && roleName != "") {
				result = roleService.getRolesByRoleName(roleName);

				if (result.get("isSuccess").equals(true)) {
					return ResponseHandler.generateResponse(HttpStatus.OK, true, result.get("message").toString(), result);
				}
				else
					return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, result.get("message").toString(), result);
			}else {
				return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, "InputMismatchException", result);
			}

		}
		catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage(), result);
		}
	}

	

	//	@PreAuthorize("hasAuthority('GET_ROLE_BY_ID')")
	@Activity
	@RequestMapping(value = UrlMapping.GET_ROLE_BY_ID, method = RequestMethod.GET)
	public ResponseEntity<Object> getRolebyRoleId(@RequestParam("roleId") String roleId) {

		Map<String, Object> result = null;
		try {
			Long id = Long.parseLong(roleId);
			if (id != null) {
				result = roleService.getRolesById(id);

				if (result.get("isSuccess").equals(true)) {
					return ResponseHandler.generateResponse(HttpStatus.OK, true, result.get("message").toString(), result);
				}
				else
					return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, result.get("message").toString(), result);
			}else {
				return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, "InputMismatchException", result);
			}

		}
		catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage(), result);
		}
	}

}
