/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.anais.rest.persistencia;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author erika
 */
@Entity
public class Autores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_autores;
    @OneToOne
    private int ordArt;
    
    private String email;
    private String primeiroNome;
    private String meio;
    private String sobrenome;       
    private String afiliacao;       
    private String afiliacaoEN;       
    private String pais;       
    private String registro;       
    
    
    
    public Long getId_autores() {
        return id_autores;
    }

    public void setId_autores(Long id_autores) {
        this.id_autores = id_autores;
    }

    public int getOrdArt() {
        return ordArt;
    }

    public void setOrdArt(int ordArt) {
        this.ordArt = ordArt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }


     public String getMeio() {
        return meio;
    }

    public void setMeio(String meio) {
        this.meio = meio;
    }
    
    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }


    public String getAfiliacao() {
        return afiliacao;
    }

    public void setAfiliacao(String afiliacao) {
        this.afiliacao = afiliacao;
    }


    public String getAfiliacaoEN() {
        return afiliacaoEN;
    }

    public void setAfiliacaoEN(String afiliacaoEN) {
        this.afiliacaoEN = afiliacaoEN;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }


    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }    
    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_autores != null ? id_autores.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Autores)) {
            return false;
        }
        Autores other = (Autores) object;
        if ((this.id_autores == null && other.id_autores != null) || (this.id_autores != null && !this.id_autores.equals(other.id_autores))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Autores[ id=" + id_autores + " ]";
    }
    
}
