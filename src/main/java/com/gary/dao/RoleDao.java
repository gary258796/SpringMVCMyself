package com.gary.dao;

import com.gary.entity.Role;

public interface RoleDao {

	 Role findRoleByName(String theRoleName);
	
}
