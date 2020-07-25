package com.accp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.accp.pojo.PersonObj;

public interface PersonObjMapper {
    int deleteByPrimaryKey(Integer pid);

    int insert(PersonObj record);

    int insertSelective(PersonObj record);

    PersonObj selectByPrimaryKey(Integer pid);

    int updateByPrimaryKeySelective(PersonObj record);

    int updateByPrimaryKeyWithBLOBs(PersonObj record);

    int updateByPrimaryKey(PersonObj record);
    
    
    public List<PersonObj> queryAllPersons();

	public PersonObj loadPerson(@Param("pid") Integer pid);

	public int updatePerson(@Param("person") PersonObj person);

	public int savePerson(@Param("person") PersonObj person);

	public int deletePerson(@Param("pid") Integer pid);

	public int deleteAddressByPersonId(@Param("pid") Integer pid);

}