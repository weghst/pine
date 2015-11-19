package com.weghst.pine.repository;

import com.weghst.pine.domain.User;
import com.weghst.pine.domain.UserTempField;

public interface UserRepository {

    void save(User user);

    void delete(int id);

    void update(User user);

    void updateEmailValid(String email, boolean emailValid);

    User get(int id);

    User get(String email);

    // ============================ UserTempField ================================//

    /**
     *
     * @param userTempField
     */
    void saveOrUpdate(UserTempField userTempField);

    /**
     *
     * @param uid
     * @param field
     */
    void deleteUserTempField(int uid, String field);

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
