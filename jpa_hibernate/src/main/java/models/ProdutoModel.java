package models;

import entities.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class ProdutoModel {

    public void create(Produto p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            System.out.println("Produto criado com sucesso !!!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao criar o produto !!!" + e.getMessage());
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }

    public void update(Produto p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação de atualização");
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
            System.out.println("Produto atualizado com sucesso !!!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao atualizar o produto !!!" + e.getMessage());
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }

    public void delete(Produto p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação de exclusão");
            em.getTransaction().begin();
            Produto produtoToDelete = em.find(Produto.class, p.getId());
            if (produtoToDelete != null) {
                em.remove(produtoToDelete);
                em.getTransaction().commit();
                System.out.println("Produto excluído com sucesso !!!");
            } else {
                System.out.println("Produto não encontrado para exclusão.");
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Erro ao excluir o produto !!!" + e.getMessage());
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }

    public Produto findById(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        Produto produto = null;

        try {
            produto = em.find(Produto.class, id);
            if (produto != null) {
                System.out.println("Produto encontrado: " + produto);
            } else {
                System.out.println("Produto não encontrado com ID: " + id);
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar o produto !!!" + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }

        return produto;
    }

    public List<Produto> findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        List<Produto> produtos = new ArrayList<Produto>();

        try {
            TypedQuery<Produto> query = em.createQuery("SELECT p FROM Produto p", Produto.class);
            produtos = query.getResultList();
            System.out.println("Total de produtos encontrados: " + produtos.size());
        } catch (Exception e) {
            System.err.println("Erro ao buscar produtos !!!" + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }

        return produtos;
    }
}
