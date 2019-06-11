package com.cloud.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cloud.web.model.Cfg;

/**
 * 配置文件控制层
 * @author Administrator
 *
 */
public interface CfgService {
	
	public void  insertCfg(Cfg cgf);
	
	public List<Cfg> selectCfgList();
	
	/**
	 * 查询对象集合
	 */
	public  List<Cfg> getAll();
	/**
	 * 根据id查对象
	 */
	
	public Cfg selectCfgById(long id);
	
	/**
	 * 删除对象
	 */
	public  void deleteCfg(long id);
}
