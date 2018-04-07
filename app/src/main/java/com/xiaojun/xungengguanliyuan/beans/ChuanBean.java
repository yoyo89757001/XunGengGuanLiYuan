package com.xiaojun.xungengguanliyuan.beans;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/6.
 */

public class ChuanBean implements Serializable{
    private String name;
    private String bianhao;
    private String xiangmuMing;
    private int type;
    private String shijian1;
    private String shijian2;

    @Override
    public String toString() {
        return "ChuanBean{" +
                "name='" + name + '\'' +
                ", bianhao='" + bianhao + '\'' +
                ", xiangmuMing='" + xiangmuMing + '\'' +
                ", type=" + type +
                ", shijian1='" + shijian1 + '\'' +
                ", shijian2='" + shijian2 + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBianhao() {
        return bianhao;
    }

    public void setBianhao(String bianhao) {
        this.bianhao = bianhao;
    }

    public String getXiangmuMing() {
        return xiangmuMing;
    }

    public void setXiangmuMing(String xiangmuMing) {
        this.xiangmuMing = xiangmuMing;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getShijian1() {
        return shijian1;
    }

    public void setShijian1(String shijian1) {
        this.shijian1 = shijian1;
    }

    public String getShijian2() {
        return shijian2;
    }

    public void setShijian2(String shijian2) {
        this.shijian2 = shijian2;
    }
}
