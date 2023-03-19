package dataaccess;

import java.util.List;
import models.Role;
import models.User;

/**
 *
 * @author xbali
 */

public class UserDB {
    
    public List<User> getUsers() throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try{
            List<User> users = em.createNamedQuery("User.findAll", User.class).getResultList();
            return users;
        } finally {
            em.close();
        }
    }
    
    public User getUser(String email) throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try{
            User user = em.find(User.class, email);
            return user;
        } finally {
            em.close();
        }
        
    }
    
    public void insertUser(User user) throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            User newUser = user;
            Role curRole = newUser.getRole();
            trans.begin();
            em.persist(newUser);
            em.merge(curRole);
            trans.commit();
        } catch(Exception ex){
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public void updateUser(User user) throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try{
            User curUser = user;
            Role curRole = curUser.getRole();
            trans.begin();
            em.merge(curUser);
            trans.commit();
        } catch(Exception ex){
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public void deleteUser(User user) throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try{
            User delUser = user;
            trans.begin();
            em.remove(em.merge(delUser));
            trans.commit();
        } catch(Exception ex){
            trans.rollback();
        } finally {
            em.close();
        }
    }
}