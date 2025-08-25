package dao;

import common.Exceptions.ValidadorEntity;
import jakarta.persistence.*;
import model.Jugador;

import java.util.List;

public class JugadorDAO {

    private final EntityManager em;

    public JugadorDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("memoriaPersistence");
        this.em = emf.createEntityManager();
    }

    public void crear(Jugador jugador) {
        EntityTransaction tx = em.getTransaction();
        ValidadorEntity.validar(jugador);
        tx.begin();
        em.persist(jugador);
        tx.commit();
    }

    public List<Jugador> listarTodos() {
        TypedQuery<Jugador> query = em.createQuery("SELECT j FROM Jugador j WHERE activo = true", Jugador.class);
        return query.getResultList();
    }

    public void modificar(Jugador jugador) {
        EntityTransaction tx = em.getTransaction();
        ValidadorEntity.validar(jugador);
        tx.begin();
        em.merge(jugador);
        tx.commit();
    }

    public void borrar(Jugador jugador) {
        EntityTransaction tx = em.getTransaction();
        ValidadorEntity.validar(jugador);
        tx.begin();
        jugador.setActivo(false);
        em.merge(jugador);
        tx.commit();
    }

    public Jugador buscarPorNombre(String nombre){
        try {
            Jugador jugador = em.createQuery("SELECT j FROM Jugador j WHERE nombreUsuario = :nombre and activo = true", Jugador.class)
                    .setParameter("nombre", nombre)
                    .getSingleResult();
            return jugador;

        } catch (NoResultException ex){
            return null;
        }
    }
}
