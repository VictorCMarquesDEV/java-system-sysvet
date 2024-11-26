package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "consultas")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data", nullable = false)
    private String data;

    @Column(name = "hora", nullable = false)
    private String hora;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "cliente", nullable = false)
    private String cliente;

    @Column(name = "pet", nullable = false)
    private String pet;

    @Column(name = "responsavel", nullable = false)
    private String responsavel;

    public Consulta() {
    }

    public Consulta(String data, String hora, String descricao, String cliente, String pet, String responsavel) {
        this.data = data;
        this.hora = hora;
        this.descricao = descricao;
        this.cliente = cliente;
        this.pet = pet;
        this.responsavel = responsavel;
    }

    public Long getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getPet() {
        return pet;
    }

    public void setPet(String pet) {
        this.pet = pet;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }
}