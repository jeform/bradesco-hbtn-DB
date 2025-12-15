package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "aluno")
public class Aluno implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nome", nullable = false)
    private String nome;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "matricula", unique = true)
    private String matricula;
    
    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos = new ArrayList<>();
    
    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Telefone> telefones = new ArrayList<>();
    
    @ManyToMany(mappedBy = "alunos")
    private List<Curso> cursos = new ArrayList<>();
    
    // Construtores
    public Aluno() {
    }
    
    public Aluno(String nome, String email, String matricula) {
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;
    }
    
    // Métodos auxiliares para manter sincronização bidirecional
    public void addEndereco(Endereco endereco) {
        enderecos.add(endereco);
        endereco.setAluno(this);
    }
    
    public void removeEndereco(Endereco endereco) {
        enderecos.remove(endereco);
        endereco.setAluno(null);
    }
    
    public void addTelefone(Telefone telefone) {
        telefones.add(telefone);
        telefone.setAluno(this);
    }
    
    public void removeTelefone(Telefone telefone) {
        telefones.remove(telefone);
        telefone.setAluno(null);
    }
    
    // Getters e Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getMatricula() {
        return matricula;
    }
    
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    
    public List<Endereco> getEnderecos() {
        return enderecos;
    }
    
    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
    
    public List<Telefone> getTelefones() {
        return telefones;
    }
    
    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }
    
    public List<Curso> getCursos() {
        return cursos;
    }
    
    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }
}
