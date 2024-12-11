package model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "disciplina")
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @OneToMany(mappedBy = "disciplina", cascade = CascadeType.ALL)
    private Set<Matricula> matriculas;

    @OneToMany(mappedBy = "disciplina", cascade = CascadeType.ALL)
    private Set<Periodo> periodos;

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

    public Set<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(Set<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    public Set<Periodo> getPeriodos() {
        return periodos;
    }

    public void setPeriodos(Set<Periodo> periodos) {
        this.periodos = periodos;
    }
}