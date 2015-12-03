package com.weghst.pine.repository;

import com.weghst.pine.domain.User;
import com.weghst.pine.domain.UserTempField;

public interface UserRepository {

    int save(User user);

    int delete(int id);

    int update(User user);

    int updateEmailValid(String email, boolean emailValid);

    User get(int id);

    User get(String email);

    // ============================ UserTempField ================================//

    /**
     *
     * @param userTempField
     */
    int saveOrUpdate(UserTempField userTempField);

    /**
     *  @param uid
     * @param field
     */
    int deleteUserTempField(int uid, String field);

    /**
     *
     */
    int cleanUserTempField();

    /**
     *
     * @param uid
     * @param field
     * @return
     */
    UserTempField getUserTempField(int uid, String field);

}
