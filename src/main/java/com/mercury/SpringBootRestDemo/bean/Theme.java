package com.mercury.SpringBootRestDemo.bean;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "THEME")
public class Theme {
    
    @Id
    private int themeId;
    @Column
    private String themeName;

    @OneToMany(mappedBy = "theme", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ThemeProduct> themeProducts = new HashSet<>();


    public Theme() {
    }

    public Theme(int themeId, String themeName, Set<ThemeProduct> themeProducts) {
        this.themeId = themeId;
        this.themeName = themeName;
        this.themeProducts = themeProducts;
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public Set<ThemeProduct> getThemeProducts() {
        return themeProducts;
    }

    public void setThemeProducts(Set<ThemeProduct> themeProducts) {
        this.themeProducts = themeProducts;
    }

    @Override
    public String toString() {
        return "Theme{" +
                "themeId=" + themeId +
                ", themeName='" + themeName + '\'' +
                ", themeProducts=" + themeProducts +
                '}';
    }
}
