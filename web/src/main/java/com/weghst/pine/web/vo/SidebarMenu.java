package com.weghst.pine.web.vo;

/**
 * @author Kevin Zou
 */
public class SidebarMenu {

    private String text;
    private String url;
    private String icon;
    private boolean restricted;
    private SidebarMenu[] subMenus;

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

    public SidebarMenu[] getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(SidebarMenu[] subMenus) {
        this.subMenus = subMenus;
    }
}
