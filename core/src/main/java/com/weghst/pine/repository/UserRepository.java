package com.weghst.pine.repository;

import com.weghst.pine.domain.User;
import com.weghst.pine.domain.UserTempField;

/**
 * 用户数据接口定义.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public interface UserRepository {

    /**
     * 保存用户信息返回成功的记录数.
     *
     * @param user 用户对象
     * @return 成功的记录数
     */
    int save(User user);

    int delete(int id);

    int update(User user);

    int updateEmailValid(String email, boolean emailValid);

    User get(int id);

    User get(String email);

    // ============================ UserTempField ================================//

    /**
     * @param userTempField
     */
    int saveOrUpdate(UserTempField userTempField);

    /**
     * @param uid
     * @param field
     */
    int deleteUserTempField(int uid, String field);

    /**
     *
     */
    int cleanUserTempField();

    /**
     * @param uid
     * @param field
     * @return
     */
    UserTempField getUserTempField(int uid, String field);

}
