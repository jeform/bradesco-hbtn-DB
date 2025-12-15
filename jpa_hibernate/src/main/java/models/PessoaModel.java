package models;

import entities.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class PessoaModel {

    public void create(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            System.out.println("Pessoa criada com sucesso !!!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao criar a pessoa !!!" + e.getMessage());
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }

    public void update(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação de atualização");
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
            System.out.println("Pessoa atualizada com sucesso !!!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao atualizar a pessoa !!!" + e.getMessage());
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }

    public void delete(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação de exclusão");
            em.getTransaction().begin();
            Pessoa pessoaToDelete = em.find(Pessoa.class, p.getId());
            if (pessoaToDelete != null) {
                em.remove(pessoaToDelete);
                em.getTransaction().commit();
                System.out.println("Pessoa excluída com sucesso !!!");
            } else {
                System.out.println("Pessoa não encontrada para exclusão.");
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao excluir a pessoa !!!" + e.getMessage());
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }

    public Pessoa findById(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        Pessoa pessoa = null;

        try {
            pessoa = em.find(Pessoa.class, id);
            if (pessoa != null) {
                System.out.println("Pessoa encontrada: " + pessoa);
            } else {
                System.out.println("Pessoa não encontrada com ID: " + id);
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar a pessoa !!!" + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }

        return pessoa;
    }

    public List<Pessoa> findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        List<Pessoa> pessoas = new ArrayList<Pessoa>();

        try {
            TypedQuery<Pessoa> query = em.createQuery("SELECT p FROM Pessoa p", Pessoa.class);
            pessoas = query.getResultList();
            System.out.println("Total de pessoas encontradas: " + pessoas.size());
        } catch (Exception e) {
            System.err.println("Erro ao buscar pessoas !!!" + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }

        return pessoas;
    }
}
