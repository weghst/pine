package com.weghst.pine.repository;

import com.weghst.pine.domain.UserTempField;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Repository
public interface UserTempFieldRepository {

    /**
     * @param userTempField
     */
    int saveOrUpdate(UserTempField userTempField);

    /**
     * 删除指定用户下所有的临时属性.
     *
     * @param uid 用户ID
     * @return 成功的记录数
     */
    int deleteUserTempFieldByUid(int uid);

    /**
     * 删除用户指定的临时属性.
     *
     * @param uid   用户ID
     * @param field 临时属性名称
     * @return 成功的记录数
     */
    int deleteUserTempFieldByUidAndField(@Param("uid") long uid, @Param("field") String field);

    /**
     * 清理所有用户的临时属性.
     *
     * @return 清理的记录数
     */
    int cleanUserTempField();

    /**
     * @param uid
     * @param field
     * @return
     */
    UserTempField getUserTempField(@Param("uid") long uid, @Param("field") String field);
}
