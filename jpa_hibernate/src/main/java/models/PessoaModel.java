package models;

import entities.Pessoa;
import entities.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
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
            em.close();
            System.err.println("Erro ao criar a pessoa !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public void update(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        Pessoa pessoa;
        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            pessoa = em.find(Pessoa.class, p.getId());
            pessoa.setNome(p.getNome());
            pessoa.setEmail(p.getEmail());
            pessoa.setIdade(p.getIdade());
            pessoa.setCpf(p.getCpf());
            pessoa.setDataDeNascimento(p.getDataDeNascimento());
            em.merge(pessoa);
            em.getTransaction().commit();
            System.out.println("Pessoa atualizada com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao atualizar a pessoa !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public void delete(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        Pessoa pessoa;

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            pessoa = em.find(Pessoa.class, p.getId());
            em.remove(pessoa);
            em.getTransaction().commit();
            System.out.println("Pessoa deletada com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao deletar a pessoa !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }

    }

    public Pessoa findById(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        Pessoa pessoa = null;

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            pessoa = em.find(Pessoa.class, p.getId());
            em.getTransaction().commit();
            System.out.println("Pessoa recuperada com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao recuperar a pessoa !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
        return pessoa;
    }

    public List<Pessoa> findAll(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        List<Pessoa> pessoas = new ArrayList<Pessoa>();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            Query consulta = em.createNativeQuery("SELECT * FROM pessoa");
            pessoas = consulta.getResultList();
            em.getTransaction().commit();
            System.out.println("Pessoas recuperadas com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao recuperar as pessoas !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
        return pessoas;
    }
}
