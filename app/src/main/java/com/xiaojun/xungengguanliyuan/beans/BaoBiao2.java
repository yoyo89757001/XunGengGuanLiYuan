package com.xiaojun.xungengguanliyuan.beans;

import java.util.List;

/**
 * Created by Administrator on 2018/4/7.
 */

public class BaoBiao2 {


    /**
     * createTime : 1523103574597
     * dtoResult : 0
     * modifyTime : 1523103574597
     * objects : [{"address":"办公室2","createTime":1523103574367,"del_status":0,"dtoResult":0,"e_hour":12000,"e_time":1523016000000,"finish_mission":0,"id":2172,"item_id":0,"item_name":"BenC","line_id":0,"mission_times":0,"modifyTime":1523103574367,"operator_id":0,"page":0,"pageNum":0,"pageSize":0,"s_hour":11500,"s_time":1522998000000,"schedule_id":10000127,"sid":0,"start_time":"2018-04-06","status":1,"status_":"未打卡","str_btime":"15:00--20:00","summary_e_hour":0,"summary_s_hour":0,"time":0,"total_mission":0,"total_num":0,"unfinished_mission":0,"user_names":"陈远平"},{"address":"办公室3","createTime":1523103574367,"del_status":0,"dtoResult":0,"e_hour":12000,"e_time":1523016000000,"finish_mission":0,"id":2173,"item_id":0,"item_name":"BenC","line_id":0,"mission_times":0,"modifyTime":1523103574367,"operator_id":0,"page":0,"pageNum":0,"pageSize":0,"s_hour":11500,"s_time":1522998000000,"schedule_id":10000127,"sid":0,"start_time":"2018-04-06","status":1,"status_":"未打卡","str_btime":"15:00--20:00","summary_e_hour":0,"summary_s_hour":0,"time":0,"total_mission":0,"total_num":0,"unfinished_mission":0,"user_names":"陈远平"},{"address":"办公室2","createTime":1523103574367,"del_status":0,"dtoResult":0,"e_hour":12000,"e_time":1523016000000,"finish_mission":0,"id":2175,"item_id":0,"item_name":"BenC","line_id":0,"mission_times":0,"modifyTime":1523103574367,"operator_id":0,"page":0,"pageNum":0,"pageSize":0,"s_hour":11500,"s_time":1522998000000,"schedule_id":10000127,"sid":0,"start_time":"2018-04-06","status":1,"status_":"未打卡","str_btime":"15:00--20:00","summary_e_hour":0,"summary_s_hour":0,"time":0,"total_mission":0,"total_num":0,"unfinished_mission":0,"user_names":"郑雪江"},{"address":"办公室3","createTime":1523103574367,"del_status":0,"dtoResult":0,"e_hour":12000,"e_time":1523016000000,"finish_mission":0,"id":2176,"item_id":0,"item_name":"BenC","line_id":0,"mission_times":0,"modifyTime":1523103574367,"operator_id":0,"page":0,"pageNum":0,"pageSize":0,"s_hour":11500,"s_time":1522998000000,"schedule_id":10000127,"sid":0,"start_time":"2018-04-06","status":1,"status_":"未打卡","str_btime":"15:00--20:00","summary_e_hour":0,"summary_s_hour":0,"time":0,"total_mission":0,"total_num":0,"unfinished_mission":0,"user_names":"郑雪江"}]
     * pageNum : 0
     * pageSize : 0
     * sid : 0
     * totalNum : -1894098341
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
         * address : 办公室2
         * createTime : 1523103574367
         * del_status : 0
         * dtoResult : 0
         * e_hour : 12000
         * e_time : 1523016000000
         * finish_mission : 0
         * id : 2172
         * item_id : 0
         * item_name : BenC
         * line_id : 0
         * mission_times : 0
         * modifyTime : 1523103574367
         * operator_id : 0
         * page : 0
         * pageNum : 0
         * pageSize : 0
         * s_hour : 11500
         * s_time : 1522998000000
         * schedule_id : 10000127
         * sid : 0
         * start_time : 2018-04-06
         * status : 1
         * status_ : 未打卡
         * str_btime : 15:00--20:00
         * summary_e_hour : 0
         * summary_s_hour : 0
         * time : 0
         * total_mission : 0
         * total_num : 0
         * unfinished_mission : 0
         * user_names : 陈远平
         */

        private String address;
        private long createTime;
        private int del_status;
        private int dtoResult;
        private int e_hour;
        private long e_time;
        private int finish_mission;
        private int id;
        private int item_id;
        private String item_name;
        private int line_id;
        private int mission_times;
        private long modifyTime;
        private int operator_id;
        private int page;
        private int pageNum;
        private int pageSize;
        private int s_hour;
        private long s_time;
        private int schedule_id;
        private int sid;
        private String start_time;
        private int status;
        private String status_;
        private String str_btime;
        private int summary_e_hour;
        private int summary_s_hour;
        private int time;
        private int total_mission;
        private int total_num;
        private int unfinished_mission;
        private String user_names;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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

        public int getE_hour() {
            return e_hour;
        }

        public void setE_hour(int e_hour) {
            this.e_hour = e_hour;
        }

        public long getE_time() {
            return e_time;
        }

        public void setE_time(long e_time) {
            this.e_time = e_time;
        }

        public int getFinish_mission() {
            return finish_mission;
        }

        public void setFinish_mission(int finish_mission) {
            this.finish_mission = finish_mission;
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

        public String getItem_name() {
            return item_name;
        }

        public void setItem_name(String item_name) {
            this.item_name = item_name;
        }

        public int getLine_id() {
            return line_id;
        }

        public void setLine_id(int line_id) {
            this.line_id = line_id;
        }

        public int getMission_times() {
            return mission_times;
        }

        public void setMission_times(int mission_times) {
            this.mission_times = mission_times;
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

        public int getS_hour() {
            return s_hour;
        }

        public void setS_hour(int s_hour) {
            this.s_hour = s_hour;
        }

        public long getS_time() {
            return s_time;
        }

        public void setS_time(long s_time) {
            this.s_time = s_time;
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

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getStatus_() {
            return status_;
        }

        public void setStatus_(String status_) {
            this.status_ = status_;
        }

        public String getStr_btime() {
            return str_btime;
        }

        public void setStr_btime(String str_btime) {
            this.str_btime = str_btime;
        }

        public int getSummary_e_hour() {
            return summary_e_hour;
        }

        public void setSummary_e_hour(int summary_e_hour) {
            this.summary_e_hour = summary_e_hour;
        }

        public int getSummary_s_hour() {
            return summary_s_hour;
        }

        public void setSummary_s_hour(int summary_s_hour) {
            this.summary_s_hour = summary_s_hour;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public int getTotal_mission() {
            return total_mission;
        }

        public void setTotal_mission(int total_mission) {
            this.total_mission = total_mission;
        }

        public int getTotal_num() {
            return total_num;
        }

        public void setTotal_num(int total_num) {
            this.total_num = total_num;
        }

        public int getUnfinished_mission() {
            return unfinished_mission;
        }

        public void setUnfinished_mission(int unfinished_mission) {
            this.unfinished_mission = unfinished_mission;
        }

        public String getUser_names() {
            return user_names;
        }

        public void setUser_names(String user_names) {
            this.user_names = user_names;
        }
    }
}
