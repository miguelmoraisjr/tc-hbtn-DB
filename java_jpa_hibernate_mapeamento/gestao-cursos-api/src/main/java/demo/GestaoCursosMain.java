package demo;

import entities.*;
import models.AlunoModel;
import models.CursoModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GestaoCursosMain {

    public static void main(String[] args) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        AlunoModel alunoModel = new AlunoModel();
        // Endereço
        Endereco end1 = new Endereco();
        end1.setLogradouro("12345");
        end1.setEndereco("Rua Caio Pereira");
        end1.setNumero("456");
        end1.setBairro("Rosarinho");
        end1.setCidade("Recife");
        end1.setEstado("Pernambuco");
        end1.setCep(50610120);


        // Set de Endereço
        Set<Endereco> enderecos = new HashSet<>();
        enderecos.add(end1);


        // Telefone
        Telefone tel1 = new Telefone();
        tel1.setDDD("081");
        tel1.setNumero("99999999");

        // Set de Telefone
        Set<Telefone> telefones = new HashSet<>();
        telefones.add(tel1);

        // Aluno
        Aluno al1 = new Aluno();
        al1.setNomeCompleto("Miguel Morais");
        al1.setMatricula("01045633461");
        al1.setNascimento(sdf.parse("20/07/1993"));
        al1.setEmail("miguel@gmail.com");
        al1.getEnderecos().add(end1);
        al1.getTelefones().add(tel1);
        tel1.setAluno(al1);
        end1.setAluno(al1);

        // Criar Aluno
        alunoModel.create(al1);

     /*   // Atualizar Aluno
        al1.setEmail("miguelmorais@gmail.com");
        al1.setMatricula("22344566");
        al1.setNomeCompleto("Miguel Augusto Morais");
        alunoModel.update(al1);

       // Buscar Aluno
        Aluno al2 = alunoModel.findById(al1);

        // Buscar todos os alunos
        List<Aluno> alunos = alunoModel.findAll();

        // Deletar aluno
        alunoModel.delete(al1); */

        // -------------------

        CursoModel cursoModel = new CursoModel();

        // Professor
        Professor professor = new Professor();
        professor.setNomeCompleto("Oswaldo Marques");
        professor.setMatricula("1223455");
        professor.setEmail("oswaldo@gmail.com");

        // MaterialCurso
        MaterialCurso material = new MaterialCurso();
        material.setUrl("UrldoMateiral");

        // Curso
        Curso cr1 = new Curso();
        cr1.setNome("Frances");
        cr1.setSigla("FR");
        cr1.setMaterial(material);
        cr1.getAlunos().add(al1);
        cr1.setProfessor(professor);
        material.setCurso(cr1);
        professor.getCursos().add(cr1);
        al1.getCursos().add(cr1);

        // Criar Curso
        cursoModel.create(cr1);

        // Atualizar curso
        cr1.setSigla("PT");
        cr1.setNome("Portugues");
        cursoModel.update(cr1);

        // Encotrar curso
        Curso cr2 = cursoModel.findById(cr1);

        // Encontrar cursos
        List<Curso> cursos = cursoModel.findAll();

        // Deletar curso
        cursoModel.delete(cr1);




    }
}
