package com.mercury.SpringBootRestDemo.controller;


import com.mercury.SpringBootRestDemo.bean.Theme;
import com.mercury.SpringBootRestDemo.bean.ThemeProduct;
import com.mercury.SpringBootRestDemo.service.ThemeProductService;
import com.mercury.SpringBootRestDemo.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@PreAuthorize("hasAuthority('user')")
@RequestMapping("/themes")
public class ThemeController {

    @Autowired
    private ThemeService themeService;

    @Autowired
    private ThemeProductService themeProductService;

    @PreAuthorize("hasAuthority('user')")
    @GetMapping
    public List<Theme> getAllThemes() {
        return themeService.getAllThemes();
    }

    @PreAuthorize("hasAuthority('user')")
    @GetMapping("/{themeId}/theme-products")
    public List<ThemeProduct> getThemeProductsByThemeId(@PathVariable int themeId) {
        return themeProductService.getThemeProductsByThemeId(themeId);
    }


}
