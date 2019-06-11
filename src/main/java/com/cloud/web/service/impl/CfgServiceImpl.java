package com.cloud.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.web.mapper.CfgMapper;
import com.cloud.web.model.Cfg;
import com.cloud.web.service.CfgService;
import com.mysql.cj.xdevapi.SessionFactory;

@Service
public class CfgServiceImpl implements CfgService{
	
	private Logger logger =LoggerFactory.getLogger(CfgServiceImpl.class);
	@Autowired
	private CfgMapper cfgMapper;
	@Override
	public void  insertCfg(Cfg cgf) {
		cfgMapper.insert(cgf);
	}
	/**
	 * 查询配置文件list集合	
	 */
	@Override
	public List<Cfg> selectCfgList() {
		List<Cfg> list=new ArrayList<Cfg>();
		
		try {
			logger.info("selectCfgList start ...............");
			list= cfgMapper.selectCfgList();
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("selectCfgList error message{},error{}",e.getMessage(),e.getStackTrace());
		}
		return list;
	}
	@Override
	public List<Cfg> getAll() {
		List<Cfg> list=new ArrayList<Cfg>();
		try {
			logger.info("getAll start ...............");
			list= cfgMapper.getAll();
			logger.info("getAll end ...............");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getAll error message{},error{}",e.getMessage(),e.getStackTrace());
		}
		return list;
	}
	
	
	@Override
	public Cfg selectCfgById(long id) {
		Cfg cfg=null;
		try {
			logger.info("selectCfgById start ...............");
			cfg= cfgMapper.selectCfgById(id);
			logger.info("selectCfgById end ...............");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("selectCfgById error message{},error{}",e.getMessage(),e.getStackTrace());
		}
		return cfg;
	}
	/**
	 * 删除对象	
	 */
	@Override
	public void deleteCfg(long id) {
		if(id==0){
			return;
		}
		try {
			logger.info("deleteCfg start ...............");
			cfgMapper.deleteCfg(id);
			logger.info("deleteCfg end ...............");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("deleteCfg error message{},error{}",e.getMessage(),e.getStackTrace());
		}
		
	}

}
