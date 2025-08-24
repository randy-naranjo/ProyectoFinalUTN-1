package dao;

import common.Exceptions.ValidadorEntity;
import jakarta.persistence.*;
import model.Partida;

import java.util.List;

public class PartidaDAO {

    private EntityManager em;

    public PartidaDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("memoriaPersistence");
        this.em = emf.createEntityManager();
    }

    public void crear(Partida partida) {
        EntityTransaction tx = em.getTransaction();
        ValidadorEntity.validar(partida);
        tx.begin();
        em.persist(partida);
        tx.commit();
    }

    public List<Partida> listar(){
        em.clear();
        return em.createQuery("SELECT p FROM Partida p", Partida.class)
                .getResultList();
    }
}
