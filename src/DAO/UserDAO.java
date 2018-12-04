/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import dto.EmployeeDTO;
import dto.UserDTO;
import entities.Admin;
import entities.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.transform.Transformers;

/**
 *
 * @author duy
 */
public class UserDAO {

    private final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public boolean doLogin(Admin admin) {
        Session session = sessionFactory.openSession();
        String sql = "select a.username as \"userName\", a.password as \"password\" "
                + "from admin a where username =:username and password=:pass";
        Query query = session.createSQLQuery(sql);
        query.setString("username", admin.getUsername());
        query.setString("pass", admin.getPassword());
        query.setResultTransformer(Transformers.aliasToBean(UserDTO.class));
        List<User> list = query.list();
        if (list.size() == 0) {
            return false;
        }
        return true;

    }

    /**
     *
     * @param startDate
     * @param enDate
     * @return
     */
    public List<EmployeeDTO> getAllUserByTime(Date startDate, Date endDate) {
        String sql = "select y.name as name, y.numberPhone as numberPhone,  "
                + " y.totalHour as \"totalHour\", y.userId from ( "
                + "  select user.user_id as userId, user.full_name as \"name\", user.number_phone as \"numberPhone\" , count(day)* :hour  as totalHour \n"
                + " from user\n"
                + "       left join registration on user.user_id = registration.user_id "
                + " and registration.day between :startDate and :endDate "
               + " group by user.user_id order by totalHour ) as y "
                 ;
        Query query = sessionFactory.openSession().createSQLQuery(sql);
        query.setDate("startDate", startDate);
        query.setDate("endDate", endDate);
        query.setInteger("hour", 8);
        List<Object[]> objects = query.list();
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        for(Object[] object: objects) {
             EmployeeDTO employeeDTO = new EmployeeDTO();
             employeeDTO.setName(object[0].toString());
             employeeDTO.setNumberPhone(object[1].toString());
             employeeDTO.setTotalHour(object[2].toString());
             employeeDTO.setUserId(object[3].toString());
             employeeDTOs.add(employeeDTO);
        }
        return employeeDTOs;
    }

}
