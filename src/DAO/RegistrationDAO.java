/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.>
 */
package DAO;

import dto.RegistrationDTO;

import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.transform.Transformers;

/**
 *
 * @author duy
 */
public class RegistrationDAO {

    private final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public List<RegistrationDTO> getAllRegisterNextWeek(Date startDate, Date endDate) {
        String sql = "select r.day as date ,r.shift_id as shiftId , u.full_name as name from registration r inner join user u on r.user_id = u.user_id and r.day between :start and :end ";
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery(sql);
        query.setDate("start", startDate);
        query.setDate("end", endDate);
        query.setResultTransformer(Transformers.aliasToBean(RegistrationDTO.class));
        List<RegistrationDTO> list = query.list();
        return list;
    }

    public void deleteRegistration(Date date, int shift) {
        Transaction tx = null;
        Session session = sessionFactory.openSession();
        String sql = "delete from registration where day = :date and shift_id = :shift ";
        try {
            tx = session.beginTransaction();
            Query query = session.createSQLQuery(sql);
            //java.sql.Date data =  new java.sql.Date(date.getTime());
            Object param = new java.sql.Timestamp(date.getTime());
            query.setParameter("date", param);
            query.setInteger("shift", shift);
            query.executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public void insertRegister(List<Integer> userIds, Date date, int shift) {
        String sql = "insert into registration(user_id, shift_id, day) values(:userId, :shift, :day)";
        Transaction tx = null;
        Session session = sessionFactory.openSession();
        try {
            tx = session.beginTransaction();
            for(int i=0 ; i<userIds.size() ; i++ ) {
                
           
            Query query = session.createSQLQuery(sql);
            
            //java.sql.Date data =  new java.sql.Date(date.getTime());
            Object param = new java.sql.Timestamp(date.getTime());
            query.setParameter("day", param);
            query.setInteger("shift", shift);
            query.setInteger("userId", userIds.get(i));
            query.executeUpdate();
            
            }
            session.flush();
            tx.commit();
            
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
