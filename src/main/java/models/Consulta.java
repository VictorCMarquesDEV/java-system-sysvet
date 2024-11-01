package models;

import java.sql.Date;
import java.sql.Time;

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
    private Date data;

    @Column(name = "hora", nullable = false)
    private Time hora;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "clientepet", nullable = false)
    private String clientepet;

    @Column(name = "responsavel", nullable = false)
    private String responsavel;

    public Consulta() {
    }

    public Consulta(Date data, Time hora, String descricao, String clientepet, String responsavel) {
        this.data = data;
        this.hora = hora;
        this.descricao = descricao;
        this.clientepet = clientepet;
        this.responsavel = responsavel;
    }

    public Long getId() {
        return id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getClientepet() {
        return clientepet;
    }

    public void setClientepet(String clientepet) {
        this.clientepet = clientepet;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }
}