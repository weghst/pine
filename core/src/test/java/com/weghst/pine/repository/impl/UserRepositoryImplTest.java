package com.weghst.pine.repository.impl;

import com.weghst.pine.SpringTestSupport;
import com.weghst.pine.domain.User;
import com.weghst.pine.domain.UserTempField;
import com.weghst.pine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class UserRepositoryImplTest extends SpringTestSupport {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSave() throws Exception {
        User user = newUser();
        int r = userRepository.save(user);
        assertEquals(r, 1);
    }

    @Test
    public void testDelete() throws Exception {
        User user = newUser();
        userRepository.save(user);

        int r = userRepository.delete(user.getId());
        assertEquals(r, 1);
    }

    @Test
    public void testUpdate() throws Exception {
        User user = newUser();
        userRepository.save(user);

        user.setEmail("new-hello@test.com");
        int r = userRepository.update(user);
        assertEquals(r, 1);
    }

    @Test
    public void testUpdateEmailValid() throws Exception {
        User user = newUser();
        userRepository.save(user);

        int r = userRepository.updateEmailValid(user.getEmail(), true);
        assertEquals(r, 1);
    }

    @Test
    public void testGet() throws Exception {
        User user = newUser();
        userRepository.save(user);

        User dbUser = userRepository.get(user.getId());
        assertNotNull(dbUser);
    }

    @Test
    public void testGet1() throws Exception {
        User user = newUser();
        userRepository.save(user);

        User dbUser = userRepository.get(user.getEmail());
        assertNotNull(dbUser);
    }

    private User newUser() {
        User user = new User();
        user.setEmail("hello@test.com");
        user.setPassword("[PASSWORD]");
        return user;
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        UserTempField userTempField = newUserTempField();
        int r = userRepository.saveOrUpdate(userTempField);
        assertEquals(r, 1);
    }

    @Test
    public void testDeleteUserTempField() throws Exception {
        UserTempField userTempField = newUserTempField();
        userRepository.saveOrUpdate(userTempField);

        int r = userRepository.deleteUserTempField(userTempField.getUid(), userTempField.getField());
        assertEquals(r, 1);
    }

    @Test
    public void testCleanUserTempField() throws Exception {
        UserTempField userTempField = newUserTempField();
        userTempField.setSurvivalMillis(-1000);
        userRepository.saveOrUpdate(userTempField);

        int r = userRepository.cleanUserTempField();
        assertTrue(r >= 1);
    }

    @Test
    public void testGetUserTempField() throws Exception {
        UserTempField userTempField = newUserTempField();
        userRepository.saveOrUpdate(userTempField);

        UserTempField dbUserTempField = userRepository.getUserTempField(userTempField.getUid(),
                userTempField.getField());
        assertNotNull(dbUserTempField);
    }

    private UserTempField newUserTempField() {
        UserTempField userTempField = new UserTempField();
        userTempField.setUid(Integer.MAX_VALUE);
        userTempField.setField("test");
        userTempField.setSurvivalMillis(1000 * 60);
        return userTempField;
    }
}