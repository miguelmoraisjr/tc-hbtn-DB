package models;

import entities.Aluno;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

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
            em.close();
            System.err.println("Erro ao criar um aluno !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public Aluno findById(Aluno aluno) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        Aluno al2 = null;

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            al2 = em.find(Aluno.class, aluno.getId());
            em.getTransaction().commit();
            System.out.println("Aluno recuperado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao recuperar o aluno!!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
        return al2;
    }

    public List<Aluno> findAll(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        List<Aluno> alunos = new ArrayList<Aluno>();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            Query consulta = em.createNativeQuery("SELECT * FROM aluno");
            alunos = consulta.getResultList();
            em.getTransaction().commit();
            System.out.println("Alunos recuperados com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao recuperar os alunos !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
        return alunos;
    }

    public void update(Aluno aluno) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        Aluno al2;
        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            al2 = em.find(Aluno.class, aluno.getId());
            al2.setNomeCompleto(aluno.getNomeCompleto());
            al2.setMatricula(aluno.getMatricula());
            al2.setEmail(aluno.getEmail());
            em.merge(al2);
            em.getTransaction().commit();
            System.out.println("Aluno atualizado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao atualizar um aluno !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public void delete(Aluno aluno) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        Aluno al2;

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            al2 = em.find(Aluno.class, aluno.getId());
            em.remove(al2);
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
}
