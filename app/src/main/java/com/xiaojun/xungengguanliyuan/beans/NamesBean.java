package com.xiaojun.xungengguanliyuan.beans;

/**
 * Created by Administrator on 2018/1/24.
 */

public class NamesBean {

    public NamesBean() {
    }

    public NamesBean(String name, boolean isTrue) {
        this.name = name;
        this.isTrue = isTrue;
    }

    private String name;
    private boolean isTrue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isTrue() {
        return isTrue;
    }

    public void setTrue(boolean aTrue) {
        isTrue = aTrue;
    }
}
