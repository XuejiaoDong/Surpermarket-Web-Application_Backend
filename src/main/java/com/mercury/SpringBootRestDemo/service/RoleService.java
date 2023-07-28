package com.mercury.SpringBootRestDemo.service;


import com.mercury.SpringBootRestDemo.bean.Role;
import com.mercury.SpringBootRestDemo.dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public List<Role> getAllRoles() {
        return roleDao.findAll();
    }


    public Role getRoleById(int id) {
        return roleDao.findById(id).orElse(null);
    }


    public Role createRole(Role role) {
        return roleDao.save(role);
    }


    public Role saveRole(Role role) {
        return roleDao.save(role);
    }

    public boolean deleteRole(int id) {
        if (roleDao.existsById(id)) {
            roleDao.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Role getRoleByRoleId(int roleId) {
        return roleDao.findById(roleId).orElse(null);
    }
}
