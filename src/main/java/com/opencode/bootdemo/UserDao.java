package com.opencode.bootdemo;

import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long>{
	public User findByUserName(String userName);
}
