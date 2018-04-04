package com.xiaojun.xungengguanliyuan.beans;

import java.util.List;

/**
 * Created by Administrator on 2018/2/1.
 */

public class XianLuBean {


    /**
     * createTime : 1517469886127
     * dtoResult : 0
     * modifyTime : 1517469886127
     * objects : [{"cmd_code":0,"createTime":1517469886177,"del_status":0,"dtoResult":0,"e_time":0,"id":0,"item_id":0,"line_name":"ddddd","modifyTime":1517469886177,"operator_id":0,"page":0,"pageNum":0,"pageSize":0,"records":0,"s_time":0,"sid":0,"time":0,"total":6,"total_num":0}]
     * pageNum : 0
     * pageSize : 0
     * sid : 0
     * totalNum : 11236997
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
         * cmd_code : 0
         * createTime : 1517469886177
         * del_status : 0
         * dtoResult : 0
         * e_time : 0
         * id : 0
         * item_id : 0
         * line_name : ddddd
         * modifyTime : 1517469886177
         * operator_id : 0
         * page : 0
         * pageNum : 0
         * pageSize : 0
         * records : 0
         * s_time : 0
         * sid : 0
         * time : 0
         * total : 6
         * total_num : 0
         */
        private String schedule_id;
        private String schedule_tag;
        private int cmd_code;
        private long createTime;
        private int del_status;
        private int dtoResult;
        private int e_time;
        private int id;
        private int item_id;
        private String line_name;
        private long modifyTime;
        private int operator_id;
        private int page;
        private int pageNum;
        private int pageSize;
        private int records;
        private int s_time;
        private int sid;
        private int time;
        private int total;
        private int total_num;
        private int line_id;

        public String getSchedule_id() {
            return schedule_id;
        }

        public void setSchedule_id(String schedule_id) {
            this.schedule_id = schedule_id;
        }

        public String getSchedule_tag() {
            return schedule_tag;
        }

        public void setSchedule_tag(String schedule_tag) {
            this.schedule_tag = schedule_tag;
        }

        public int getLine_id() {
            return line_id;
        }

        public void setLine_id(int line_id) {
            this.line_id = line_id;
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

        public int getE_time() {
            return e_time;
        }

        public void setE_time(int e_time) {
            this.e_time = e_time;
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

        public String getLine_name() {
            return line_name;
        }

        public void setLine_name(String line_name) {
            this.line_name = line_name;
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

        public int getRecords() {
            return records;
        }

        public void setRecords(int records) {
            this.records = records;
        }

        public int getS_time() {
            return s_time;
        }

        public void setS_time(int s_time) {
            this.s_time = s_time;
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

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getTotal_num() {
            return total_num;
        }

        public void setTotal_num(int total_num) {
            this.total_num = total_num;
        }
    }
}
