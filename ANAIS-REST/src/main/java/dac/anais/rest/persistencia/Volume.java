/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.anais.rest.persistencia;


import java.io.Serializable;
import java.util.List;
import javax.json.JsonValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

/**
 *
 * @author erika
 */
@Entity


public class Volume implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_volume;
   
    private String sigla;
    private int edicao;
    private String cidade;
    private String dtInicio;
    private String descricaoPT;
    private String descricaoEN;
    @OneToMany
    @OrderBy("titulo asc")
    private List<Artigos> liArtigos;
    
    
            
            
            
    public Long getId() {
        return id_volume;
    }

    public void setId(Long id) {
        this.id_volume = id_volume;
    }
   
    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    
     public int getEdicao() {
        return edicao;
    }

    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }
    
     public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    
     public String getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(String dtInicio) {
        this.dtInicio = dtInicio;
    }
    
     public String getDescricaoPT() {
        return descricaoPT;
    }

    public void setDescricaoPT(String descricaoPT) {
        this.descricaoPT = descricaoPT;
    }
    
     public String getDescricaoEN() {
        return descricaoEN;
    }

    public void setDescricaoEN(String descricaoEN) {
        this.descricaoEN = descricaoEN;
    }
    
     public List<Artigos> getLiArtigos() {
        return liArtigos;
    }

    public void setLiArtigos(List<Artigos> liArtigos) {
        this.liArtigos = liArtigos;
    }
    
    
    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_volume != null ? id_volume.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Volume)) {
            return false;
        }
        Volume other = (Volume) object;
        if ((this.id_volume == null && other.id_volume != null) || (this.id_volume != null && !this.id_volume.equals(other.id_volume))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.Volume[ id=" + id_volume + " ]";
    }

    public void setLiArtigos(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public JsonValue getListaArtigos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
