package ru.sergeirodionov.testjr.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.sergeirodionov.testjr.model.User;

import java.util.List;

/**
 * Created by SergeiRodionov on 03.08.2015.
 */

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<User> findAllUsers(Integer firstResult, Integer maxResults, String searchUserName, String sortFieldName) {

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        if (searchUserName == null || searchUserName.length() < 3) {
            return (List<User>) criteria.list();
        } else {
            Criterion likeName = Restrictions.like("name", "%" + searchUserName + "%");
            criteria.add(likeName);
            return (List<User>) criteria.list();
        }
    }

    public User getUserById(Integer userId) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        Criterion idx = Restrictions.idEq(userId);
        criteria.add(idx);
        criteria.setMaxResults(1);
        return (User) criteria.uniqueResult();
    }

    public void saveOrUpdateUser(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    public void deleteUser(Integer userId) {
        sessionFactory.getCurrentSession().delete(getUserById(userId));
    }

    public Integer getCountPages(Integer maxRowPerPage, String searchUserName) {
        String countRowQ;
        if (searchUserName == null || searchUserName.length() < 3) {
            countRowQ = "Select count (u.id) from User u";
        } else {
            countRowQ = "Select count (u.id) from User u where u.name LIKE '%" + searchUserName + "%'";
        }
        Query countRow = sessionFactory.getCurrentSession().createQuery(countRowQ);
        Long countResult = (Long) countRow.uniqueResult();
        return (int) Math.ceil(countResult * 1.0 / maxRowPerPage);
    }
}
