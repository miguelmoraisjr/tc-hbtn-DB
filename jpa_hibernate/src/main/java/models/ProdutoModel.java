package models;

import entities.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
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
            em.close();
            System.err.println("Erro ao criar o produto !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public void update(Produto p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        Produto produto;
        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            produto = em.find(Produto.class, p.getId());
            produto.setNome(p.getNome());
            produto.setQuantidade(p.getQuantidade());
            produto.setPreco(p.getPreco());
            produto.setStatus(p.getStatus());
            em.merge(produto);
            em.getTransaction().commit();
            System.out.println("Produto atualizado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao atualizar o produto !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public void delete(Produto p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        Produto produto;

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            produto = em.find(Produto.class, p.getId());
            em.remove(produto);
            em.getTransaction().commit();
            System.out.println("Produto deletado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao deletar o produto !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }

    }

    public Produto findById(Produto p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        Produto produto = null;

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            produto = em.find(Produto.class, p.getId());
            em.getTransaction().commit();
            System.out.println("Produto recuperado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao recuperar o produto !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
        return produto;
    }

    public List<Produto> findAll(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        List<Produto> produtos = new ArrayList<Produto>();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            Query consulta = em.createNativeQuery("SELECT * FROM produto");
            produtos = consulta.getResultList();
            em.getTransaction().commit();
            System.out.println("Produtos recuperados com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao recuperar os produtos !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
        return produtos;
    }
}
