package com.jsm.android.sporttour.app.util;

import java.util.List;

/**
 * Created by admin on 7/26/2016.
 */
public enum ChildEnum {
    LATLON("latlon"), LOGO("logo"), NAME("name"), PLACE("place"), SPORT("sport"),

    TOURNAMENTS("tournaments",new ChildEnum[]{LATLON, LOGO, NAME, PLACE, SPORT});


    private String childName;
    private ChildEnum[] childs;

    private ChildEnum(String childName, ChildEnum... childs){
        this.childName = childName;
        this.childs = childs;
    }

    public String getChildName() {
        return childName;
    }

    public ChildEnum[] getChilds() {
        return childs;
    }
}
