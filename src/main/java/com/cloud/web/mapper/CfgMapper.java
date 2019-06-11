package com.cloud.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.cloud.web.model.Cfg;

public interface CfgMapper {
	int deleteByPrimaryKey(Long id);

	public void insert(Cfg record);

	int insertSelective(Cfg record);

	Cfg selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(Cfg record);

	int updateByPrimaryKey(Cfg record);

	/**
	 * 查询cfg对象集合
	 * 
	 * @return List<Cfg>
	 */
	public List<Cfg> selectCfgList();

	@Select("select id,name,value  from t_cfg")
	@Results(id = "cfgMap", value = { @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
			@Result(column = "value", property = "value", jdbcType = JdbcType.VARCHAR) })
	public List<Cfg> getAll();
	
	/**
	 * 根据id查询对象
	 * @param id
	 * @return
	 */
	@Select("Select * from t_cfg where id =#{id}")
	public Cfg selectCfgById(@Param("id") long id);
	
	/**
	 * 修改对象信息
	 * 
	 */
    @Insert("INSERT INTO t_cfg(name,value) VALUES(#{name}, #{value})")
    void insertCfg(Cfg user);

    @Update("UPDATE t_cfg SET name=#{name},value=#{value} WHERE id =#{id}")
    void updateCfg(Cfg user);

    @Delete("DELETE FROM t_cfg WHERE id =#{id}")
    void deleteCfg(Long id);
}	