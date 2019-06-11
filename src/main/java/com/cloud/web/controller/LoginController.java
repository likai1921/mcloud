package com.cloud.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.web.model.Cfg;
import com.cloud.web.service.CfgService;
import net.sf.json.JSONObject;
/**
 * 登陆
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/mcloud")
public class LoginController {
	
	private Logger logger =LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private CfgService cfgService;
	@RequestMapping("/login")
	@ResponseBody
	public String say(HttpServletRequest request, HttpServletResponse response) {
		logger.info(" -----------login start ---------------");
		String result= null;
		try {
			/*
			 * List<Cfg> list = cfgService.getAll(); System.out.println(list.toString());
			 * result = JSONArray.fromObject(list).toString();
			 */
			Cfg cfg = cfgService.selectCfgById(71);
			System.out.println(JSONObject.fromObject(cfg).toString());
			result=JSONObject.fromObject(cfg).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("-----------login end ---------------");
		return result;

	}
}
