package com.mercury.SpringBootRestDemo.dao;

import com.mercury.SpringBootRestDemo.bean.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeDao extends JpaRepository<Theme, Integer> {
}
