package demo;

import entities.Aluno;
import entities.Endereco;
import entities.Telefone;
import models.AlunoModel;

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

        // Atualizar Aluno
        al1.setEmail("miguelmorais@gmail.com");
        al1.setMatricula("22344566");
        al1.setNomeCompleto("Miguel Augusto Morais");
        alunoModel.update(al1);

        // Buscar Aluno
        Aluno al2 = alunoModel.findById(al1);

        // Buscar todos os alunos
        List<Aluno> alunos = alunoModel.findAll();

        // Deletar aluno
        alunoModel.delete(al1);

        // Set de Aluno
        Set<Aluno> aluno2 = new HashSet<>();
        alunos.add(al1);



    }
}
