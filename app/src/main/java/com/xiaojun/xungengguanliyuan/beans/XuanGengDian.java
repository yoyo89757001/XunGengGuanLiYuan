package com.xiaojun.xungengguanliyuan.beans;

import java.util.List;

/**
 * Created by Administrator on 2018/2/1.
 */

public class XuanGengDian {


    /**
     * createTime : 1517471902129
     * dtoResult : 0
     * modifyTime : 1517471902129
     * objects : [{"address":"daaa","cmd_code":0,"createTime":1517429355000,"create_time":1517429355000,"del_status":1,"dtoResult":0,"id":2,"item_id":10000003,"line_id":10000002,"modifyTime":1517471903999,"operator_id":10000015,"page":0,"pageNum":0,"pageSize":0,"schedule_id":10000002,"sid":0,"time":0,"total_num":0,"xungeng_id":10000008}]
     * pageNum : 0
     * pageSize : 0
     * sid : 0
     * totalNum : 13027232
     */

    private long createTime;
    private int dtoResult;
    private long modifyTime;
    private int pageNum;
    private int pageSize;
    private int sid;
    private int totalNum;
    private List<ObjectsBean> objects;

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getDtoResult() {
        return dtoResult;
    }

    public void setDtoResult(int dtoResult) {
        this.dtoResult = dtoResult;
    }

    public long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public List<ObjectsBean> getObjects() {
        return objects;
    }

    public void setObjects(List<ObjectsBean> objects) {
        this.objects = objects;
    }

    public static class ObjectsBean {
        /**
         * address : daaa
         * cmd_code : 0
         * createTime : 1517429355000
         * create_time : 1517429355000
         * del_status : 1
         * dtoResult : 0
         * id : 2
         * item_id : 10000003
         * line_id : 10000002
         * modifyTime : 1517471903999
         * operator_id : 10000015
         * page : 0
         * pageNum : 0
         * pageSize : 0
         * schedule_id : 10000002
         * sid : 0
         * time : 0
         * total_num : 0
         * xungeng_id : 10000008
         */


        private int is_sequence;
        private String address;

        public int getIs_sequence() {
            return is_sequence;
        }

        public void setIs_sequence(int is_sequence) {
            this.is_sequence = is_sequence;
        }

        private int cmd_code;
        private long createTime;
        private long create_time;
        private int del_status;
        private int dtoResult;
        private int id;
        private int item_id;
        private int line_id;
        private long modifyTime;
        private int operator_id;
        private int page;
        private int pageNum;
        private int pageSize;
        private int schedule_id;
        private int sid;
        private int time;
        private int status;
        private int total_num;
        private int xungeng_id;
        private String mac;
        private String vedios;
        private String imgs;
        private String other;
        private long s_time;
        private long e_time;

        public long getS_time() {
            return s_time;
        }

        public void setS_time(long s_time) {
            this.s_time = s_time;
        }

        public long getE_time() {
            return e_time;
        }

        public void setE_time(long e_time) {
            this.e_time = e_time;
        }

        public String getOther() {
            return other;
        }

        public void setOther(String other) {
            this.other = other;
        }

        public String getVedios() {
            return vedios;
        }

        public void setVedios(String vedios) {
            this.vedios = vedios;
        }

        public String getImgs() {
            return imgs;
        }

        public void setImgs(String imgs) {
            this.imgs = imgs;
        }

        public String getMac() {
            return mac;
        }

        public void setMac(String mac) {
            this.mac = mac;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getCmd_code() {
            return cmd_code;
        }

        public void setCmd_code(int cmd_code) {
            this.cmd_code = cmd_code;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getCreate_time() {
            return create_time;
        }

        public void setCreate_time(long create_time) {
            this.create_time = create_time;
        }

        public int getDel_status() {
            return del_status;
        }

        public void setDel_status(int del_status) {
            this.del_status = del_status;
        }

        public int getDtoResult() {
            return dtoResult;
        }

        public void setDtoResult(int dtoResult) {
            this.dtoResult = dtoResult;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getItem_id() {
            return item_id;
        }

        public void setItem_id(int item_id) {
            this.item_id = item_id;
        }

        public int getLine_id() {
            return line_id;
        }

        public void setLine_id(int line_id) {
            this.line_id = line_id;
        }

        public long getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(long modifyTime) {
            this.modifyTime = modifyTime;
        }

        public int getOperator_id() {
            return operator_id;
        }

        public void setOperator_id(int operator_id) {
            this.operator_id = operator_id;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getSchedule_id() {
            return schedule_id;
        }

        public void setSchedule_id(int schedule_id) {
            this.schedule_id = schedule_id;
        }

        public int getSid() {
            return sid;
        }

        public void setSid(int sid) {
            this.sid = sid;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public int getTotal_num() {
            return total_num;
        }

        public void setTotal_num(int total_num) {
            this.total_num = total_num;
        }

        public int getXungeng_id() {
            return xungeng_id;
        }

        public void setXungeng_id(int xungeng_id) {
            this.xungeng_id = xungeng_id;
        }
    }
}
