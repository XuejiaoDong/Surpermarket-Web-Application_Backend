package com.mercury.SpringBootRestDemo.service;

import com.mercury.SpringBootRestDemo.bean.Theme;
import com.mercury.SpringBootRestDemo.dao.ThemeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThemeService {
    @Autowired
    private ThemeDao themeDao;

    public List<Theme> getAllThemes() {
        return themeDao.findAll();
    }
}
