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
public class JPAArtigosDAO {
    
    private EntityManager em;
    
    public JPAArtigosDAO() {
    }
        
    
    
    
    public Artigos recupera(Long id) {
        
        em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
        Artigos e = em.find(Artigos.class, id);
        et.commit();
        em.close();
        return e;
    }
    
    public void deleta(Long id) {
        
        em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
        Artigos e = em.find(Artigos.class, id);
        em.remove(e);
        et.commit();
        em.close();
    }
    
    public List<Artigos> buscaTitulo(String titulo) {
        String jpqlQuery = "SELECT e FROM Artigos e where e.titulo = :ti";
        em = JPAUtil.getEM();
        Query query = em.createQuery(jpqlQuery);
        query.setParameter("ti", titulo);
        List<Artigos> e = query.getResultList();
        em.close();
        return e;
    }
    
    public List<Artigos> buscaTudo() {
        String jpqlQuery = "SELECT e FROM Artigos e";
        em = JPAUtil.getEM();
        Query query = em.createQuery(jpqlQuery);
        List<Artigos> e = query.getResultList();
        em.close();
        return e;
    }

    public void salva(Artigos entity) {
      
        
        em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(entity);
        et.commit();
        em.close();
    
    }

   
    
}
