package demo;

import entities.Pessoa;
import entities.Produto;
import models.PessoaModel;
import models.ProdutoModel;

import java.time.LocalDate;
import java.util.List;

public class AdministrativoApp {

    public static void main(String[] args) {
        ProdutoModel produtoModel = new ProdutoModel();
        PessoaModel pessoaModel = new PessoaModel();

        System.out.println("========== TESTANDO PRODUTO ==========");
        
        // 1) Criando um produto
        Produto p1 = new Produto();
        p1.setNome("TV");
        p1.setPreco(300.0);
        p1.setQuantidade(100);
        p1.setStatus(true);
        produtoModel.create(p1);

        // Criando mais produtos
        Produto p2 = new Produto("Notebook", 50, 2500.0, true);
        produtoModel.create(p2);

        Produto p3 = new Produto("Mouse", 200, 50.0, true);
        produtoModel.create(p3);

        // 2) Buscando todos os produtos na base de dados
        System.out.println("\n--- Buscando todos os produtos ---");
        List<Produto> produtos = produtoModel.findAll();
        System.out.println("Qtde de produtos encontrados : " + produtos.size());
        for (Produto p : produtos) {
            System.out.println(p);
        }

        // 3) Buscando produto por ID
        System.out.println("\n--- Buscando produto por ID ---");
        Produto produtoEncontrado = produtoModel.findById(1L);

        // 4) Atualizando um produto
        if (produtoEncontrado != null) {
            System.out.println("\n--- Atualizando produto ---");
            produtoEncontrado.setPreco(350.0);
            produtoEncontrado.setQuantidade(90);
            produtoModel.update(produtoEncontrado);
        }

        // 5) Buscando produto atualizado
        System.out.println("\n--- Verificando produto atualizado ---");
        produtoModel.findById(1L);

        // 6) Deletando um produto
        System.out.println("\n--- Deletando produto ---");
        Produto produtoParaDeletar = produtoModel.findById(3L);
        if (produtoParaDeletar != null) {
            produtoModel.delete(produtoParaDeletar);
        }

        // 7) Verificando produtos após exclusão
        System.out.println("\n--- Produtos após exclusão ---");
        produtoModel.findAll();

        System.out.println("\n\n========== TESTANDO PESSOA ==========");

        // 1) Criando pessoas
        Pessoa pessoa1 = new Pessoa();
        pessoa1.setNome("João Silva");
        pessoa1.setEmail("joao@email.com");
        pessoa1.setIdade(30);
        pessoa1.setCpf("123.456.789-00");
        pessoa1.setDataNascimento(LocalDate.of(1993, 5, 15));
        pessoaModel.create(pessoa1);

        Pessoa pessoa2 = new Pessoa("Maria Santos", "maria@email.com", 25, "987.654.321-00", LocalDate.of(1998, 8, 20));
        pessoaModel.create(pessoa2);

        Pessoa pessoa3 = new Pessoa("Pedro Costa", "pedro@email.com", 35, "111.222.333-44", LocalDate.of(1988, 12, 10));
        pessoaModel.create(pessoa3);

        // 2) Buscando todas as pessoas
        System.out.println("\n--- Buscando todas as pessoas ---");
        List<Pessoa> pessoas = pessoaModel.findAll();
        System.out.println("Qtde de pessoas encontradas : " + pessoas.size());
        for (Pessoa p : pessoas) {
            System.out.println(p);
        }

        // 3) Buscando pessoa por ID
        System.out.println("\n--- Buscando pessoa por ID ---");
        Pessoa pessoaEncontrada = pessoaModel.findById(1L);

        // 4) Atualizando uma pessoa
        if (pessoaEncontrada != null) {
            System.out.println("\n--- Atualizando pessoa ---");
            pessoaEncontrada.setEmail("joao.silva@email.com");
            pessoaEncontrada.setIdade(31);
            pessoaModel.update(pessoaEncontrada);
        }

        // 5) Verificando pessoa atualizada
        System.out.println("\n--- Verificando pessoa atualizada ---");
        pessoaModel.findById(1L);

        // 6) Deletando uma pessoa
        System.out.println("\n--- Deletando pessoa ---");
        Pessoa pessoaParaDeletar = pessoaModel.findById(3L);
        if (pessoaParaDeletar != null) {
            pessoaModel.delete(pessoaParaDeletar);
        }

        // 7) Verificando pessoas após exclusão
        System.out.println("\n--- Pessoas após exclusão ---");
        pessoaModel.findAll();

        System.out.println("\n========== TESTES FINALIZADOS ==========");
    }
}
