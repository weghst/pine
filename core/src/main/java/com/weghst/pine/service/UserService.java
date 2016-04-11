package com.weghst.pine.service;

import com.weghst.pine.domain.User;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public interface UserService {

    /**
     * @param user
     */
    void save(User user);

    /**
     * @param id
     */
    void delete(int id);

    /**
     * @param user
     */
    void update(User user);

    /**
     * @param id
     * @return
     */
    User get(int id);

    /**
     * @param email
     * @return
     */
    User get(String email);

    /**
     * @param user
     */
    void registerForMobile(User user);

    /**
     * @param user
     */
    void sendMobileValidate(User user);

    /**
     * @param mobile
     * @param code
     * @return
     */
    boolean mobileValidate(String mobile, String code);

    /**
     * @param user
     */
    void registerForEmail(User user);

    /**
     * @param user
     */
    void sendEmailValidate(User user);

    /**
     * @param email
     * @param code
     * @return
     */
    boolean emailValidate(String email, String code);

    /**
     * @param email
     * @param password
     * @return
     * @throws UserNotFoundException
     * @throws PasswordNotMatchedException
     */
    User loginForEmail(String email, String password) throws UserNotFoundException, PasswordNotMatchedException;

    /**
     * @param mobile
     * @param password
     * @return
     * @throws UserNotFoundException
     * @throws PasswordNotMatchedException
     */
    User loginForMobile(String mobile, String password) throws UserNotFoundException, PasswordNotMatchedException;
}
