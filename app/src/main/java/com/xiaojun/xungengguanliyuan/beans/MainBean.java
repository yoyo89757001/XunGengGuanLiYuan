package com.xiaojun.xungengguanliyuan.beans;

/**
 * Created by Administrator on 2018/1/23.
 */

public class MainBean {

    public MainBean(boolean istrue, boolean isShuaXin) {
        this.istrue = istrue;
        this.isShuaXin = isShuaXin;
    }

    private boolean istrue;

    public boolean isShuaXin() {
        return isShuaXin;
    }

    public void setShuaXin(boolean shuaXin) {
        isShuaXin = shuaXin;
    }

    private boolean isShuaXin;

    public boolean isIstrue() {
        return istrue;
    }

    public void setIstrue(boolean istrue) {
        this.istrue = istrue;
    }
}
