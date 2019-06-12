package com.cloud.web.controller;

import java.util.List;

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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 登陆
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/mcloud")
public class LoginController {

	private Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private CfgService cfgService;

	@RequestMapping("/login")
	@ResponseBody
	public String say(HttpServletRequest request, HttpServletResponse response) {
		logger.info(" -----------login start ---------------");
		String result = null;
		System.out.println("111111");
		try {
			
			  List<Cfg> list = cfgService.getAll(); System.out.println(list.toString());
			  result = JSONArray.fromObject(list).toString();
			  
			  Cfg cfg1 = cfgService.selectCfgById(71);
			  System.out.println(JSONObject.fromObject(cfg1).toString());
			  result=JSONObject.fromObject(cfg1).toString();
			 
			
			Cfg cfg =new Cfg();
			cfg.setName("test");
			cfg.setValue("234");
			cfgService.addCfg(cfg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("-----------login end ---------------");
		return result;

	}
}
