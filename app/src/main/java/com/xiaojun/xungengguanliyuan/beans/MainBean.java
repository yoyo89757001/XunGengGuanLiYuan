package com.xiaojun.xungengguanliyuan.beans;

/**
 * Created by Administrator on 2018/1/23.
 */

public class MainBean {
    public MainBean(boolean istrue) {
        this.istrue = istrue;
    }

    private boolean istrue;

    public boolean isIstrue() {
        return istrue;
    }

    public void setIstrue(boolean istrue) {
        this.istrue = istrue;
    }
}
