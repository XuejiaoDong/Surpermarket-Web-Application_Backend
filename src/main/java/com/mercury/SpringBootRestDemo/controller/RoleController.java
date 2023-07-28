package com.mercury.SpringBootRestDemo.controller;

import com.mercury.SpringBootRestDemo.bean.Role;
import com.mercury.SpringBootRestDemo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    //获取所有角色
    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    //通过角色ID获取角色
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable int id) {
        Role role = roleService.getRoleById(id);
        if(role != null) {
            return ResponseEntity.ok(role);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //创建新的角色
    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Role newRole = roleService.createRole(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(newRole);
    }

    //更新已有的角色
    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable int id, @RequestBody Role updatedRole) {
        Role existingRole = roleService.getRoleById(id);
        if(existingRole != null) {
            existingRole.setRoleName(updatedRole.getRoleName());
            existingRole.setDiscount(updatedRole.getDiscount());
            Role savedRole = roleService.saveRole(existingRole);
            return ResponseEntity.ok(savedRole);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //删除角色
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable int id) {
        boolean isDeleted = roleService.deleteRole(id);
        if(isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/role/{roleId}")
    public Role getRoleByRoleId(@PathVariable int roleId) {
        return roleService.getRoleByRoleId(roleId);
    }
}
