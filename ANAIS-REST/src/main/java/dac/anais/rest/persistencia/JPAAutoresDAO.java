/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.anais.rest.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author viter
 */
public class JPAAutoresDAO {
    
    private EntityManager em;
    
    public JPAAutoresDAO() {
    }
        
    public void salva(Autores e) {
        
        em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(e);
        et.commit();
        em.close();
    }
    
    public Autores recupera(Long id) {
        
        em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
        Autores e = em.find(Autores.class, id);
        et.commit();
        em.close();
        return e;
    }
    
    public void deleta(Long id) {
        
        em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
        Autores e = em.find(Autores.class, id);
        em.remove(e);
        et.commit();
        em.close();
    }
    
    public List<Autores> buscaSobrenome(String snome) {
        String jpqlQuery = "SELECT e FROM Autores e where e.sobrenome = :sn";
        em = JPAUtil.getEM();
        Query query = em.createQuery(jpqlQuery);
        query.setParameter("sn", snome);
        List<Autores> e = query.getResultList();
        em.close();
        return e;
    }
    
    public List<Autores> buscaTudo() {
        String jpqlQuery = "SELECT e FROM Autores e";
        em = JPAUtil.getEM();
        Query query = em.createQuery(jpqlQuery);
        List<Autores> e = query.getResultList();
        em.close();
        return e;
    }
    
}
