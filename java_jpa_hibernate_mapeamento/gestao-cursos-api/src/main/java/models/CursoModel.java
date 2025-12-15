package models;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entities.Curso;

public class CursoModel {

    public void create(Curso curso) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(curso);
            em.getTransaction().commit();
            System.out.println("Curso criado com sucesso !!!");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao criar um curso !!! " + e.getMessage());
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }

    public Curso findById(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        Curso curso = null;

        try {
            curso = em.find(Curso.class, id);
            if (curso != null) {
                System.out.println("Curso encontrado: " + curso.getNome());
            } else {
                System.out.println("Curso não encontrado com ID: " + id);
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar curso !!! " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
        
        return curso;
    }

    public List<Curso> findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        List<Curso> cursos = null;

        try {
            TypedQuery<Curso> query = em.createQuery("SELECT c FROM Curso c", Curso.class);
            cursos = query.getResultList();
            System.out.println("Total de cursos encontrados: " + cursos.size());
        } catch (Exception e) {
            System.err.println("Erro ao buscar todos os cursos !!! " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
        
        return cursos;
    }

    public void update(Curso curso) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação de atualização");
            em.getTransaction().begin();
            em.merge(curso);
            em.getTransaction().commit();
            System.out.println("Curso atualizado com sucesso !!!");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao atualizar curso !!! " + e.getMessage());
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação de atualização");
        }
    }

    public void delete(Curso curso) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação de exclusão");
            em.getTransaction().begin();
            
            // Reattach the entity if it's detached
            if (!em.contains(curso)) {
                curso = em.merge(curso);
            }
            
            em.remove(curso);
            em.getTransaction().commit();
            System.out.println("Curso excluído com sucesso !!!");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao excluir curso !!! " + e.getMessage());
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação de exclusão");
        }
    }
}
