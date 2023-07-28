package com.mercury.SpringBootRestDemo.service;

import com.mercury.SpringBootRestDemo.bean.ThemeProduct;
import com.mercury.SpringBootRestDemo.dao.ThemeProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThemeProductService {
    @Autowired
    private ThemeProductDao themeProductDao;

    public List<ThemeProduct> getAllThemeProducts() {
        return themeProductDao.findAll();
    }
    public List<ThemeProduct> getThemeProductsByThemeId(int themeId) {
        return themeProductDao.findAllByThemeId(themeId);
    }
}
