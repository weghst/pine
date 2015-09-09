package com.weghst.pine.web.vo;

/**
 * @author Kevin Zou
 */
public class SidebarMenuVo {

    private String text;
    private String url;
    private String icon;
    private boolean restricted;
    private SidebarMenuVo[] submenus;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isRestricted() {
        return restricted;
    }

    public void setRestricted(boolean restricted) {
        this.restricted = restricted;
    }

    public SidebarMenuVo[] getSubmenus() {
        return submenus;
    }

    public void setSubmenus(SidebarMenuVo[] submenus) {
        this.submenus = submenus;
    }
}
