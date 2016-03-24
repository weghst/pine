package com.weghst.pine.service;

import com.weghst.pine.domain.User;
import com.weghst.pine.domain.UserTempField;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public interface UserService {

    /**
     *
     * @param user
     */
    void save(User user);

    /**
     *
     * @param id
     */
    void delete(int id);

    /**
     *
     * @param user
     */
    void update(User user);

    /**
     *
     * @param id
     * @return
     */
    User get(int id);

    /**
     *
     * @param email
     * @return
     */
    User get(String email);

    /**
     *
     * @param user
     */
    void register(User user);

    /**
     *
     * @param email
     * @param password
     * @return
     * @throws UserNotFoundException
     * @throws PasswordNotMatchedException
     */
    User login(String email, String password) throws UserNotFoundException, PasswordNotMatchedException;

    /**
     *
     * @param user
     */
    void sendEmailValidate(User user);

    /**
     *
     * @param email
     * @param code
     * @return
     */
    boolean emailValidate(String email, String code);
}
