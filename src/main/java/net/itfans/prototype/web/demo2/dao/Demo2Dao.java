package net.itfans.prototype.web.demo2.dao;

import net.itfans.prototype.web.demo2.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Demo2Dao {

    int insertUser(UserEntity userEntity);
}
