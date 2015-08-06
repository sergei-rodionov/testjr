package ru.sergeirodionov.testjr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sergeirodionov.testjr.dao.UserDao;
import ru.sergeirodionov.testjr.model.User;

import java.util.List;

/**
 * Created by SergeiRodionov on 03.08.2015.
 */

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    public List<User> findAllUsers(Integer firstResult, Integer maxResults, String searchUserName, String sortFieldName) {
        return dao.findAllUsers(firstResult, maxResults, searchUserName, sortFieldName);
    }

    public User getUserById(Integer userId) {
        return dao.getUserById(userId);
    }

    public void saveOrUpdateUser(User user) {
        dao.saveOrUpdateUser(user);
    }

    public void deleteUser(Integer userId) {
        dao.deleteUser(userId);
    }

    public Integer getCountPages(Integer maxRowPerPage, String searchUserName) {
        return dao.getCountPages(maxRowPerPage, searchUserName);
    }
}
