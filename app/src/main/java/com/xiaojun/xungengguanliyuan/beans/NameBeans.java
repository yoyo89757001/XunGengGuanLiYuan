package com.xiaojun.xungengguanliyuan.beans;

/**
 * Created by Administrator on 2018/4/12.
 */

public class NameBeans {
    private String ss;
    private boolean isTrue;
    private int p2;


    public int getP2() {
        return p2;
    }

    public void setP2(int p2) {
        this.p2 = p2;
    }

    public String getSs() {
        return ss;
    }

    public void setSs(String ss) {
        this.ss = ss;
    }

    public boolean isTrue() {
        return isTrue;
    }

    public void setTrue(boolean aTrue) {
        isTrue = aTrue;
    }

    public NameBeans(String ss, boolean isTrue, int p2) {
        this.ss = ss;
        this.isTrue = isTrue;
        this.p2 = p2;
    }
}
