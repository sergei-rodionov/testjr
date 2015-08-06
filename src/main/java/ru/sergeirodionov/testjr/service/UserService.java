package ru.sergeirodionov.testjr.service;

import ru.sergeirodionov.testjr.model.User;

import java.util.List;

/**
 * Created by SergeiRodionov on 03.08.2015.
 */
public interface UserService {

    public List<User> findAllUsers(Integer firstResult, Integer maxResults, String searchUserName, String sortFieldName);

    public User getUserById(Integer userId);

    public void saveOrUpdateUser(User user);

    public void deleteUser(Integer userId);

    public Integer getCountPages(Integer maxRowPerPage, String searchUserName);
}
