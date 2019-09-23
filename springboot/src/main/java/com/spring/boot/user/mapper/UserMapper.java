package com.spring.boot.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spring.boot.user.entity.User;

@Mapper
public interface UserMapper {
	int insert(User user);
	
	User selectById(Integer id);
    
    int updateById(User user);  
      
    int deleteById(Integer id);  
      
    List<User> queryAll(); 
}
