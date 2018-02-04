package com.xiaojun.xungengguanliyuan.beans;

import java.util.List;

/**
 * Created by Administrator on 2018/2/4.
 */

public class RenBean {

    /**
     * createTime : 1517721495839
     * dtoResult : 0
     * modifyTime : 1517721495839
     * objects : [{"account":"test4","admin_id":2,"companyId":0,"createTime":1517562779000,"creator":"系统管理员","dtoResult":0,"id":10000004,"modifyTime":1517562779000,"name":"巡更员测试账号4","pageNum":0,"pageSize":0,"phone":"13535434554","platType":0,"pwd":"49BA59ABBE56E057","region_code":"440100","role_id":10000004,"role_name":"巡更员","sex":0,"sid":0,"status":1},{"account":"test3","admin_id":2,"companyId":0,"createTime":1517562732000,"creator":"系统管理员","dtoResult":0,"id":10000003,"modifyTime":1517562732000,"name":"巡更员测试账号3","pageNum":0,"pageSize":0,"phone":"13344553565","platType":0,"pwd":"49BA59ABBE56E057","region_code":"440100","role_id":10000004,"role_name":"巡更员","sex":0,"sid":0,"status":1},{"account":"test2","admin_id":2,"companyId":0,"createTime":1517562667000,"creator":"系统管理员","dtoResult":0,"id":10000002,"modifyTime":1517562667000,"name":"巡更员测试账号2","pageNum":0,"pageSize":0,"phone":"13131133234","platType":0,"pwd":"49BA59ABBE56E057","region_code":"440100","role_id":10000004,"role_name":"巡更员","sex":0,"sid":0,"status":1},{"account":"test1","admin_id":2,"companyId":0,"createTime":1517562321000,"creator":"系统管理员","dtoResult":0,"id":10000001,"modifyTime":1517562321000,"name":"巡更员测试账号","pageNum":0,"pageSize":0,"phone":"13411313124","platType":0,"pwd":"49BA59ABBE56E057","region_code":"440100","role_id":10000004,"role_name":"巡更员","sex":0,"sid":0,"status":1}]
     * pageIndex : 0
     * pageNum : 1
     * pageSize : 100
     * sid : 0
     * totalNum : 4
     */

    private long createTime;
    private int dtoResult;
    private long modifyTime;
    private int pageIndex;
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

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
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
         * account : test4
         * admin_id : 2
         * companyId : 0
         * createTime : 1517562779000
         * creator : 系统管理员
         * dtoResult : 0
         * id : 10000004
         * modifyTime : 1517562779000
         * name : 巡更员测试账号4
         * pageNum : 0
         * pageSize : 0
         * phone : 13535434554
         * platType : 0
         * pwd : 49BA59ABBE56E057
         * region_code : 440100
         * role_id : 10000004
         * role_name : 巡更员
         * sex : 0
         * sid : 0
         * status : 1
         */

        private String account;
        private int admin_id;
        private int companyId;
        private long createTime;
        private String creator;
        private int dtoResult;
        private int id;
        private long modifyTime;
        private String name;
        private int pageNum;
        private int pageSize;
        private String phone;
        private int platType;
        private String pwd;
        private String region_code;
        private int role_id;
        private String role_name;
        private int sex;
        private int sid;
        private int status;
        private boolean isTrue;

        public boolean isTrue() {
            return isTrue;
        }

        public void setTrue(boolean aTrue) {
            isTrue = aTrue;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public int getAdmin_id() {
            return admin_id;
        }

        public void setAdmin_id(int admin_id) {
            this.admin_id = admin_id;
        }

        public int getCompanyId() {
            return companyId;
        }

        public void setCompanyId(int companyId) {
            this.companyId = companyId;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getCreator() {
            return creator;
        }

        public void setCreator(String creator) {
            this.creator = creator;
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

        public long getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(long modifyTime) {
            this.modifyTime = modifyTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getPlatType() {
            return platType;
        }

        public void setPlatType(int platType) {
            this.platType = platType;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getRegion_code() {
            return region_code;
        }

        public void setRegion_code(String region_code) {
            this.region_code = region_code;
        }

        public int getRole_id() {
            return role_id;
        }

        public void setRole_id(int role_id) {
            this.role_id = role_id;
        }

        public String getRole_name() {
            return role_name;
        }

        public void setRole_name(String role_name) {
            this.role_name = role_name;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getSid() {
            return sid;
        }

        public void setSid(int sid) {
            this.sid = sid;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
