package com.weghst.pine.repository;

import com.weghst.pine.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 用户数据接口定义.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Repository
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
    int delete(long id);

    /**
     * @param user
     * @return
     */
    int update(User user);

    /**
     * @param email
     * @param emailValid
     * @return
     */
    int updateEmailValid(@Param("email") String email, @Param("emailValid") boolean emailValid);

    /**
     * 返回用户信息.
     *
     * @param id 用户ID
     * @return 用户信息
     */
    User getById(long id);

    /**
     * 返回用户信息.
     *
     * @param email 用户邮箱
     * @return 用户信息
     */
    User getByEmail(String email);

    /**
     * 返回用户信息。
     *
     * @param mobile 手机号码
     * @return 用户信息
     */
    User getByMobile(String mobile);

}
