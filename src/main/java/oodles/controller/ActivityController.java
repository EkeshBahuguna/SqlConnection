package oodles.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import oodles.dto.RoleActivityDTO;
import oodles.mapping.UrlMapping;

import oodles.service.ActivityService;

import oodles.util.Activity;
import oodles.util.ResponseHandler;

@Controller
@RestController

public class ActivityController {

	private static Logger LOGGER = LoggerFactory.getLogger(ActivityController.class);

	@Autowired
	ActivityService activityService;

	@Activity
	@RequestMapping(value = UrlMapping.ADD_ACTIVITY, method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Object> addRole(@RequestBody String activityName) {
		Map<String, Object> result = null;
		try {
			result = activityService.addActivity(activityName);
			if (result.get("isSuccess").equals(true)) {
				return ResponseHandler.generateResponse(HttpStatus.OK, true, result.get("message").toString(), result);
			} else
				return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, result.get("message").toString(),
						result);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage(), result);
		}
	}

	@Activity
	@RequestMapping(value = UrlMapping.ACTIVATE_OR_DEACTIVATE_ACTIVITY, method = RequestMethod.POST)
	public ResponseEntity<Object> changeStatus(@RequestBody Map<String, String> data) {
		Map<String, Object> result = null;
		try {
			result = activityService.changeStatus(data);
			if (result.get("isSuccess").equals(true)) {
				return ResponseHandler.generateResponse(HttpStatus.OK, true, result.get("message").toString(), result);
			} else
				return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, result.get("message").toString(),
						result);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage(), result);
		}
	}

	@Activity
	@RequestMapping(value = UrlMapping.ASSIGN_ACTIVITIES, method = RequestMethod.PUT)
	public ResponseEntity<Object> assignActivities(@RequestBody RoleActivityDTO dto) {

		Map<String, Object> result = null;
		try {
			result = activityService.assignActivities(dto);
			if (result.get("isSuccess").equals(true)) {
				return ResponseHandler.generateResponse(HttpStatus.OK, true, result.get("message").toString(), result);
			} else
				return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, result.get("message").toString(),
						result);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage(), result);
		}
	}

	/*
	 * @RequestMapping(value = UrlMapping.ADD_ACTIVITY, method = RequestMethod.POST)
	 * public ResponseEntity<Object> StudentBean(@RequestBody StudentBean
	 * studentBean) {
	 * 
	 * Map<String, Object> result = null; try {
	 * 
	 * StudentBean sb=new StudentBean(); sb.setId(studentBean.getId());
	 * 
	 * sb.setName(studentBean.getName()); sb.setRollno(studentBean.getRollno());
	 * LOGGER.info("set data"+studentBean.getName());
	 * LOGGER.info("set data "+studentBean.getRollno());
	 * 
	 * result.put("isSuccess", true);
	 * 
	 * LOGGER.info("set data"); if (result.get("isSuccess").equals(true)) {
	 * 
	 * return ResponseHandler.generateResponse(HttpStatus.OK, true, "sucessfull",
	 * result); } else return
	 * ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, "else",
	 * result); } catch (Exception e) { return
	 * ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false,
	 * e.getMessage(), result); } }
	 */

}
