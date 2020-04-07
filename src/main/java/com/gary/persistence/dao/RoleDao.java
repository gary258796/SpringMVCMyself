package com.gary.persistence.dao;
import com.gary.persistence.entity.Role;

public interface RoleDao {

	 Role findRoleByName(String theRoleName);
	
}
