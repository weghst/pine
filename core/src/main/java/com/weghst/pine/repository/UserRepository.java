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

    /**
     * 删除指定用户.
     * <p>该实现须同时删除用户所有的临时属性.</p>
     *
     * @param id 用户ID
     * @return 成功的记录数
     */
    int delete(int id);

    /**
     *
     * @param user
     * @return
     */
    int update(User user);

    /**
     *
     * @param email
     * @param emailValid
     * @return
     */
    int updateEmailValid(String email, boolean emailValid);

    /**
     * 返回用户信息.
     *
     * @param id 用户ID
     * @return 用户信息
     */
    User get(int id);

    /**
     * 返回用户信息.
     *
     * @param email 用户邮箱
     * @return 用户信息
     */
    User get(String email);

    // ============================ UserTempField ================================//

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
    int deleteUserTempField(int uid);

    /**
     * 删除用户指定的临时属性.
     *
     * @param uid   用户ID
     * @param field 临时属性名称
     * @return 成功的记录数
     */
    int deleteUserTempField(int uid, String field);

    /**
     * 清理所有用户的临时属性.
     *
     * @return 成功的记录数
     */
    int cleanUserTempField();

    /**
     * @param uid
     * @param field
     * @return
     */
    UserTempField getUserTempField(int uid, String field);
}
