package com.accp.dao;

import com.accp.pojo.AddressObj;

public interface AddressObjMapper {
    int insert(AddressObj record);

    int insertSelective(AddressObj record);
}