package com.xiaojun.xungengguanliyuan.beans;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DENG_LU_BEAN".
*/
public class DengLuBeanDao extends AbstractDao<DengLuBean, Long> {

    public static final String TABLENAME = "DENG_LU_BEAN";

    /**
     * Properties of entity DengLuBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property UserId = new Property(1, Long.class, "userId", false, "USER_ID");
        public final static Property Zhuji = new Property(2, String.class, "zhuji", false, "ZHUJI");
        public final static Property Account = new Property(3, String.class, "account", false, "ACCOUNT");
        public final static Property Admin_id = new Property(4, int.class, "admin_id", false, "ADMIN_ID");
        public final static Property CardNum = new Property(5, String.class, "cardNum", false, "CARD_NUM");
        public final static Property Certificate = new Property(6, String.class, "certificate", false, "CERTIFICATE");
        public final static Property CompanyId = new Property(7, int.class, "companyId", false, "COMPANY_ID");
        public final static Property CreateTime = new Property(8, long.class, "createTime", false, "CREATE_TIME");
        public final static Property DtoResult = new Property(9, int.class, "dtoResult", false, "DTO_RESULT");
        public final static Property Email = new Property(10, String.class, "email", false, "EMAIL");
        public final static Property MemberNum = new Property(11, String.class, "memberNum", false, "MEMBER_NUM");
        public final static Property ModifyTime = new Property(12, long.class, "modifyTime", false, "MODIFY_TIME");
        public final static Property Name = new Property(13, String.class, "name", false, "NAME");
        public final static Property PageNum = new Property(14, int.class, "pageNum", false, "PAGE_NUM");
        public final static Property PageSize = new Property(15, int.class, "pageSize", false, "PAGE_SIZE");
        public final static Property Phone = new Property(16, String.class, "phone", false, "PHONE");
        public final static Property PlatType = new Property(17, int.class, "platType", false, "PLAT_TYPE");
        public final static Property Pwd = new Property(18, String.class, "pwd", false, "PWD");
        public final static Property Remark = new Property(19, String.class, "remark", false, "REMARK");
        public final static Property Role_id = new Property(20, int.class, "role_id", false, "ROLE_ID");
        public final static Property Sex = new Property(21, int.class, "sex", false, "SEX");
        public final static Property Status = new Property(22, int.class, "status", false, "STATUS");
        public final static Property QqTime = new Property(23, String.class, "qqTime", false, "QQ_TIME");
        public final static Property Mima = new Property(24, String.class, "mima", false, "MIMA");
        public final static Property Company = new Property(25, String.class, "company", false, "COMPANY");
        public final static Property Region_code = new Property(26, String.class, "region_code", false, "REGION_CODE");
    }


    public DengLuBeanDao(DaoConfig config) {
        super(config);
    }
    
    public DengLuBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DENG_LU_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY NOT NULL ," + // 0: id
                "\"USER_ID\" INTEGER," + // 1: userId
                "\"ZHUJI\" TEXT," + // 2: zhuji
                "\"ACCOUNT\" TEXT," + // 3: account
                "\"ADMIN_ID\" INTEGER NOT NULL ," + // 4: admin_id
                "\"CARD_NUM\" TEXT," + // 5: cardNum
                "\"CERTIFICATE\" TEXT," + // 6: certificate
                "\"COMPANY_ID\" INTEGER NOT NULL ," + // 7: companyId
                "\"CREATE_TIME\" INTEGER NOT NULL ," + // 8: createTime
                "\"DTO_RESULT\" INTEGER NOT NULL ," + // 9: dtoResult
                "\"EMAIL\" TEXT," + // 10: email
                "\"MEMBER_NUM\" TEXT," + // 11: memberNum
                "\"MODIFY_TIME\" INTEGER NOT NULL ," + // 12: modifyTime
                "\"NAME\" TEXT," + // 13: name
                "\"PAGE_NUM\" INTEGER NOT NULL ," + // 14: pageNum
                "\"PAGE_SIZE\" INTEGER NOT NULL ," + // 15: pageSize
                "\"PHONE\" TEXT," + // 16: phone
                "\"PLAT_TYPE\" INTEGER NOT NULL ," + // 17: platType
                "\"PWD\" TEXT," + // 18: pwd
                "\"REMARK\" TEXT," + // 19: remark
                "\"ROLE_ID\" INTEGER NOT NULL ," + // 20: role_id
                "\"SEX\" INTEGER NOT NULL ," + // 21: sex
                "\"STATUS\" INTEGER NOT NULL ," + // 22: status
                "\"QQ_TIME\" TEXT," + // 23: qqTime
                "\"MIMA\" TEXT," + // 24: mima
                "\"COMPANY\" TEXT," + // 25: company
                "\"REGION_CODE\" TEXT);"); // 26: region_code
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DENG_LU_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, DengLuBean entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        Long userId = entity.getUserId();
        if (userId != null) {
            stmt.bindLong(2, userId);
        }
 
        String zhuji = entity.getZhuji();
        if (zhuji != null) {
            stmt.bindString(3, zhuji);
        }
 
        String account = entity.getAccount();
        if (account != null) {
            stmt.bindString(4, account);
        }
        stmt.bindLong(5, entity.getAdmin_id());
 
        String cardNum = entity.getCardNum();
        if (cardNum != null) {
            stmt.bindString(6, cardNum);
        }
 
        String certificate = entity.getCertificate();
        if (certificate != null) {
            stmt.bindString(7, certificate);
        }
        stmt.bindLong(8, entity.getCompanyId());
        stmt.bindLong(9, entity.getCreateTime());
        stmt.bindLong(10, entity.getDtoResult());
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(11, email);
        }
 
        String memberNum = entity.getMemberNum();
        if (memberNum != null) {
            stmt.bindString(12, memberNum);
        }
        stmt.bindLong(13, entity.getModifyTime());
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(14, name);
        }
        stmt.bindLong(15, entity.getPageNum());
        stmt.bindLong(16, entity.getPageSize());
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(17, phone);
        }
        stmt.bindLong(18, entity.getPlatType());
 
        String pwd = entity.getPwd();
        if (pwd != null) {
            stmt.bindString(19, pwd);
        }
 
        String remark = entity.getRemark();
        if (remark != null) {
            stmt.bindString(20, remark);
        }
        stmt.bindLong(21, entity.getRole_id());
        stmt.bindLong(22, entity.getSex());
        stmt.bindLong(23, entity.getStatus());
 
        String qqTime = entity.getQqTime();
        if (qqTime != null) {
            stmt.bindString(24, qqTime);
        }
 
        String mima = entity.getMima();
        if (mima != null) {
            stmt.bindString(25, mima);
        }
 
        String company = entity.getCompany();
        if (company != null) {
            stmt.bindString(26, company);
        }
 
        String region_code = entity.getRegion_code();
        if (region_code != null) {
            stmt.bindString(27, region_code);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, DengLuBean entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        Long userId = entity.getUserId();
        if (userId != null) {
            stmt.bindLong(2, userId);
        }
 
        String zhuji = entity.getZhuji();
        if (zhuji != null) {
            stmt.bindString(3, zhuji);
        }
 
        String account = entity.getAccount();
        if (account != null) {
            stmt.bindString(4, account);
        }
        stmt.bindLong(5, entity.getAdmin_id());
 
        String cardNum = entity.getCardNum();
        if (cardNum != null) {
            stmt.bindString(6, cardNum);
        }
 
        String certificate = entity.getCertificate();
        if (certificate != null) {
            stmt.bindString(7, certificate);
        }
        stmt.bindLong(8, entity.getCompanyId());
        stmt.bindLong(9, entity.getCreateTime());
        stmt.bindLong(10, entity.getDtoResult());
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(11, email);
        }
 
        String memberNum = entity.getMemberNum();
        if (memberNum != null) {
            stmt.bindString(12, memberNum);
        }
        stmt.bindLong(13, entity.getModifyTime());
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(14, name);
        }
        stmt.bindLong(15, entity.getPageNum());
        stmt.bindLong(16, entity.getPageSize());
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(17, phone);
        }
        stmt.bindLong(18, entity.getPlatType());
 
        String pwd = entity.getPwd();
        if (pwd != null) {
            stmt.bindString(19, pwd);
        }
 
        String remark = entity.getRemark();
        if (remark != null) {
            stmt.bindString(20, remark);
        }
        stmt.bindLong(21, entity.getRole_id());
        stmt.bindLong(22, entity.getSex());
        stmt.bindLong(23, entity.getStatus());
 
        String qqTime = entity.getQqTime();
        if (qqTime != null) {
            stmt.bindString(24, qqTime);
        }
 
        String mima = entity.getMima();
        if (mima != null) {
            stmt.bindString(25, mima);
        }
 
        String company = entity.getCompany();
        if (company != null) {
            stmt.bindString(26, company);
        }
 
        String region_code = entity.getRegion_code();
        if (region_code != null) {
            stmt.bindString(27, region_code);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public DengLuBean readEntity(Cursor cursor, int offset) {
        DengLuBean entity = new DengLuBean( //
            cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // userId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // zhuji
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // account
            cursor.getInt(offset + 4), // admin_id
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // cardNum
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // certificate
            cursor.getInt(offset + 7), // companyId
            cursor.getLong(offset + 8), // createTime
            cursor.getInt(offset + 9), // dtoResult
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // email
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // memberNum
            cursor.getLong(offset + 12), // modifyTime
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // name
            cursor.getInt(offset + 14), // pageNum
            cursor.getInt(offset + 15), // pageSize
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // phone
            cursor.getInt(offset + 17), // platType
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // pwd
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // remark
            cursor.getInt(offset + 20), // role_id
            cursor.getInt(offset + 21), // sex
            cursor.getInt(offset + 22), // status
            cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23), // qqTime
            cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24), // mima
            cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25), // company
            cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26) // region_code
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, DengLuBean entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setUserId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setZhuji(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setAccount(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setAdmin_id(cursor.getInt(offset + 4));
        entity.setCardNum(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setCertificate(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setCompanyId(cursor.getInt(offset + 7));
        entity.setCreateTime(cursor.getLong(offset + 8));
        entity.setDtoResult(cursor.getInt(offset + 9));
        entity.setEmail(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setMemberNum(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setModifyTime(cursor.getLong(offset + 12));
        entity.setName(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setPageNum(cursor.getInt(offset + 14));
        entity.setPageSize(cursor.getInt(offset + 15));
        entity.setPhone(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setPlatType(cursor.getInt(offset + 17));
        entity.setPwd(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setRemark(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setRole_id(cursor.getInt(offset + 20));
        entity.setSex(cursor.getInt(offset + 21));
        entity.setStatus(cursor.getInt(offset + 22));
        entity.setQqTime(cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23));
        entity.setMima(cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24));
        entity.setCompany(cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25));
        entity.setRegion_code(cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(DengLuBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(DengLuBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(DengLuBean entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
