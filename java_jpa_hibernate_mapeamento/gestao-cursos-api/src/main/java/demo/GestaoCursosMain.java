package demo;

import java.util.List;

import entities.Aluno;
import entities.Curso;
import entities.Endereco;
import entities.MaterialCurso;
import entities.Professor;
import entities.Telefone;
import models.AlunoModel;
import models.CursoModel;

public class GestaoCursosMain {

    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GESTÃO DE CURSOS ===\n");

        // Instanciar os models
        AlunoModel alunoModel = new AlunoModel();
        CursoModel cursoModel = new CursoModel();

        // ===== PARTE I: CRIAR DADOS =====
        System.out.println("--- CRIANDO DADOS ---\n");

        // 1. Criar Aluno com Endereço e Telefone
        Aluno aluno = new Aluno("João Silva", "joao.silva@email.com", "MAT2024001");
        
        Endereco endereco = new Endereco(
            "Rua das Flores", 
            "123", 
            "Centro", 
            "São Paulo", 
            "SP", 
            "01234-567"
        );
        endereco.setComplemento("Apto 45");
        
        Telefone telefone = new Telefone("(11) 98765-4321", "celular");
        
        aluno.addEndereco(endereco);
        aluno.addTelefone(telefone);
        
        alunoModel.create(aluno);
        System.out.println();

        // 2. Criar Professor
        Professor professor = new Professor(
            "Dr. Maria Santos", 
            "maria.santos@email.com", 
            "Desenvolvimento de Software"
        );

        // 3. Criar Material do Curso
        MaterialCurso material = new MaterialCurso(
            "Apostila JPA e Hibernate",
            "Material completo sobre persistência de dados com JPA e Hibernate",
            "https://material.curso.com/jpa-hibernate.pdf",
            "PDF"
        );

        // 4. Criar Curso
        Curso curso = new Curso(
            "Java Persistence API (JPA) com Hibernate",
            "Curso completo de JPA e Hibernate para desenvolvimento Java",
            40
        );
        curso.setProfessor(professor);
        curso.setMaterialCurso(material);
        curso.addAluno(aluno);
        
        cursoModel.create(curso);
        System.out.println();

        // ===== PARTE II: BUSCAR DADOS =====
        System.out.println("\n--- BUSCANDO DADOS ---\n");

        // Buscar aluno por ID
        System.out.println("=== Buscar Aluno por ID ===");
        Aluno alunoEncontrado = alunoModel.findById(1L);
        if (alunoEncontrado != null) {
            System.out.println("ID: " + alunoEncontrado.getId());
            System.out.println("Nome: " + alunoEncontrado.getNome());
            System.out.println("Email: " + alunoEncontrado.getEmail());
            System.out.println("Matrícula: " + alunoEncontrado.getMatricula());
        }
        System.out.println();

        // Buscar todos os alunos
        System.out.println("=== Buscar Todos os Alunos ===");
        List<Aluno> alunos = alunoModel.findAll();
        for (Aluno a : alunos) {
            System.out.println("- " + a.getNome() + " (" + a.getMatricula() + ")");
        }
        System.out.println();

        // Buscar curso por ID
        System.out.println("=== Buscar Curso por ID ===");
        Curso cursoEncontrado = cursoModel.findById(1L);
        if (cursoEncontrado != null) {
            System.out.println("ID: " + cursoEncontrado.getId());
            System.out.println("Nome: " + cursoEncontrado.getNome());
            System.out.println("Descrição: " + cursoEncontrado.getDescricao());
            System.out.println("Carga Horária: " + cursoEncontrado.getCargaHoraria() + "h");
            System.out.println("Professor: " + cursoEncontrado.getProfessor().getNome());
            System.out.println("Material: " + cursoEncontrado.getMaterialCurso().getTitulo());
        }
        System.out.println();

        // Buscar todos os cursos
        System.out.println("=== Buscar Todos os Cursos ===");
        List<Curso> cursos = cursoModel.findAll();
        for (Curso c : cursos) {
            System.out.println("- " + c.getNome() + " (Professor: " + c.getProfessor().getNome() + ")");
        }
        System.out.println();

        // ===== PARTE III: ATUALIZAR DADOS =====
        System.out.println("\n--- ATUALIZANDO DADOS ---\n");

        if (alunoEncontrado != null) {
            System.out.println("=== Atualizar Aluno ===");
            alunoEncontrado.setEmail("joao.silva.novo@email.com");
            alunoModel.update(alunoEncontrado);
            System.out.println();
        }

        if (cursoEncontrado != null) {
            System.out.println("=== Atualizar Curso ===");
            cursoEncontrado.setCargaHoraria(60);
            cursoModel.update(cursoEncontrado);
            System.out.println();
        }

        // ===== PARTE IV: DEMONSTRAR RELACIONAMENTOS =====
        System.out.println("\n--- DEMONSTRANDO RELACIONAMENTOS ---\n");

        // Adicionar mais um aluno ao curso
        Aluno aluno2 = new Aluno("Maria Oliveira", "maria.oliveira@email.com", "MAT2024002");
        Telefone telefone2 = new Telefone("(11) 91234-5678", "celular");
        aluno2.addTelefone(telefone2);
        
        Endereco endereco2 = new Endereco(
            "Avenida Paulista", 
            "1000", 
            "Bela Vista", 
            "São Paulo", 
            "SP", 
            "01310-100"
        );
        aluno2.addEndereco(endereco2);
        
        alunoModel.create(aluno2);
        System.out.println();

        // Buscar curso novamente e adicionar o novo aluno
        Curso cursoParaAtualizar = cursoModel.findById(1L);
        if (cursoParaAtualizar != null) {
            Aluno alunoParaAdicionar = alunoModel.findById(2L);
            if (alunoParaAdicionar != null) {
                cursoParaAtualizar.addAluno(alunoParaAdicionar);
                cursoModel.update(cursoParaAtualizar);
                
                // Verificar atualização
                System.out.println("Novo aluno adicionado ao curso com sucesso!");
            }
        }
        System.out.println();

        // ===== RESUMO FINAL =====
        System.out.println("\n=== RESUMO FINAL ===");
        System.out.println("Total de Alunos cadastrados: " + alunoModel.findAll().size());
        System.out.println("Total de Cursos cadastrados: " + cursoModel.findAll().size());
        
        System.out.println("\n=== SISTEMA FINALIZADO COM SUCESSO ===");
    }
}
