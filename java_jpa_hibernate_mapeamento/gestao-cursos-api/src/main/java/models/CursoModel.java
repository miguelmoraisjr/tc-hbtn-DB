package models;

import entities.Aluno;
import entities.Curso;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class CursoModel {

    public void create(Curso curso) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(curso.getMaterial());
            em.persist(curso.getProfessor());
            em.persist(curso);
            em.getTransaction().commit();
            System.out.println("Curso criado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao criar o curso !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public Curso findById(Curso curso) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        Curso cr2 = null;

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            cr2 = em.find(Curso.class, curso.getId());
            em.getTransaction().commit();
            System.out.println("Aluno recuperado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao recuperar o aluno!!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
        return cr2;
    }

    public List<Curso> findAll(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        List<Curso> cursos = new ArrayList<Curso>();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            Query consulta = em.createNativeQuery("SELECT * FROM curso");
            cursos = consulta.getResultList();
            em.getTransaction().commit();
            System.out.println("Alunos recuperados com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao recuperar os alunos !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
        return cursos;
    }

    public void update(Curso curso) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        Curso cr2;
        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            cr2 = em.find(Curso.class, curso.getId());
            cr2.setNome(curso.getNome());
            cr2.setSigla(curso.getSigla());
            em.merge(cr2);
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

    public void delete(Curso curso) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        Curso cr2;

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            cr2 = em.find(Curso.class, curso.getId());
            em.remove(cr2);
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
