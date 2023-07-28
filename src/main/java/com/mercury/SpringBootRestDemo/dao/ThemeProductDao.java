package com.mercury.SpringBootRestDemo.dao;

import com.mercury.SpringBootRestDemo.bean.ThemeProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThemeProductDao extends JpaRepository<ThemeProduct, Integer> {
    List<ThemeProduct> findAllByThemeId(int themeId);
}
