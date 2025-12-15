package models;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entities.Aluno;

public class AlunoModel {

    public void create(Aluno aluno) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(aluno);
            em.getTransaction().commit();
            System.out.println("Aluno criado com sucesso !!!");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao criar um aluno !!! " + e.getMessage());
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }

    public Aluno findById(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        Aluno aluno = null;

        try {
            aluno = em.find(Aluno.class, id);
            if (aluno != null) {
                System.out.println("Aluno encontrado: " + aluno.getNome());
            } else {
                System.out.println("Aluno não encontrado com ID: " + id);
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar aluno !!! " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
        
        return aluno;
    }

    public List<Aluno> findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        List<Aluno> alunos = null;

        try {
            TypedQuery<Aluno> query = em.createQuery("SELECT a FROM Aluno a", Aluno.class);
            alunos = query.getResultList();
            System.out.println("Total de alunos encontrados: " + alunos.size());
        } catch (Exception e) {
            System.err.println("Erro ao buscar todos os alunos !!! " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
        
        return alunos;
    }

    public void update(Aluno aluno) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação de atualização");
            em.getTransaction().begin();
            em.merge(aluno);
            em.getTransaction().commit();
            System.out.println("Aluno atualizado com sucesso !!!");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao atualizar aluno !!! " + e.getMessage());
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação de atualização");
        }
    }

    public void delete(Aluno aluno) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação de exclusão");
            em.getTransaction().begin();
            
            // Reattach the entity if it's detached
            if (!em.contains(aluno)) {
                aluno = em.merge(aluno);
            }
            
            em.remove(aluno);
            em.getTransaction().commit();
            System.out.println("Aluno excluído com sucesso !!!");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao excluir aluno !!! " + e.getMessage());
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação de exclusão");
        }
    }
}
