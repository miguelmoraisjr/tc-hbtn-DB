package demo;

import entities.Pessoa;
import entities.Produto;
import models.PessoaModel;
import models.ProdutoModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class AdministrativoApp {

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        ProdutoModel produtoModel = new ProdutoModel();

        Produto p1 = new Produto();
        p1.setNome("TV");
        p1.setPreco(300.0);
        p1.setQuantidade(100);
        p1.setStatus(true);

        // 1) Criando um produto
        produtoModel.create(p1);

        // 2) Buscando todos os produtos na base de dados
        List<Produto> produtos = produtoModel.findAll();
        System.out.println("Qtde de produtos encontrados : " + produtos.size());

        // 3) Buscar produto na base de dados
        Produto produto = produtoModel.findById(p1);

        // 4) Atualizar produto na base de daddos
        p1.setNome("Mesa");
        p1.setPreco(500.0);
        p1.setQuantidade(90);
        p1.setStatus(true);
        produtoModel.update(p1);

        // 5) Deletar produto na base de dados
        produtoModel.delete(p1); 

        PessoaModel pessoaModel = new PessoaModel();

        Pessoa p2 = new Pessoa();
        p2.setNome("Marcos Paulo");
        p2.setEmail("marco@gmail.com");
        p2.setIdade(30);
        p2.setCpf("01045633461");
        p2.setDataDeNascimento(sdf.parse("20/07/1993"));

        // 1) Criando uma pessoa
        pessoaModel.create(p2);

        //2) Buscando todos as pessoas na base de dados
        List<Pessoa> pessoas = pessoaModel.findAll();
        System.out.println("Qtde de produtos encontrados : " + pessoas.size());

        // 3) Buscar pessoa na base de dados
        Pessoa pessoa = pessoaModel.findById(p2);

        // 4) Atualizar pessoa na base de dados
        p2.setNome("Marcos Paulo");
        p2.setEmail("marco@gmail.com");
        p2.setIdade(45);
        p2.setCpf("01045633561");
        p2.setDataDeNascimento(sdf.parse("20/08/1993"));
        pessoaModel.update(p2);

        // 5) Deletar pessoa na base de dados
        pessoaModel.delete(p2);


    }
}
