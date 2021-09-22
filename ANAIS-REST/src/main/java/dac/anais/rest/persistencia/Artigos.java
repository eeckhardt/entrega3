/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.anais.rest.persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author erika
 */
@Entity
@XmlRootElement

public class Artigos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_artigo;
    
    private int ordVol;
    private String idioma;
    @Column(length=256)
    private String titulo; 
    @Column(length=256)
    private String tituloEN; 
    @Column(length=2048)
    private String resumo; 
    @Column(length=2048)
    private String resumoEN;   
    @Column(length=256)    
    private String pchave;
    @Column(length=256)
    private String pchaveEN;
    
    private int paginas;
    @OneToMany
    @OrderBy("sobrenome asc")
    private List<Autores> listAutor;
    @ManyToOne
    private Volume vol;
              
                      
            

    public Long getId() {
        return id_artigo;
    }

    public void setId(Long id_artigo) {
        this.id_artigo = id_artigo;
    }

    
    public int getOrdVol() {
        return ordVol;
    }

    public void setOrdVol(int id) {
        this.ordVol = ordVol;
    }
    
    
    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    } 
    
    
     public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


   public String getTituloEN() {
        return tituloEN;
    }

    public void setTituloEN(String tituloEN) {
        this.tituloEN = tituloEN;
    }    
   public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }    
    
     public String getResumoEN() {
        return resumoEN;
    }

    public void setResumoEN(String resumoEN) {
        this.resumoEN = resumoEN;
        
        
        }    
    
    
   public String getPchave() {
        return pchave;
    }

    public void setPchave(String pchave) {
        this.pchave = pchave;
    }    
   public String getPchaveEN() {
        return pchaveEN;
    }

    public void setPchaveEN(String pchaveEN) {
        this.pchaveEN = pchaveEN;
    }    
   public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }    

   public List<Autores> getListAutor() {
        return listAutor;
    }

    public void setListAutor(List<Autores> listAutor) {
        this.listAutor = listAutor;
    }    
    
    public Volume getVol() {
        return vol;
    }

    public void setVol(Volume vol) {
        this.vol = vol;
    }    
    




    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_artigo != null ? id_artigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Artigos)) {
            return false;
        }
        Artigos other = (Artigos) object;
        if ((this.id_artigo == null && other.id_artigo != null) || (this.id_artigo != null && !this.id_artigo.equals(other.id_artigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Artigos[ id=" + id_artigo + " ]";
    }
    
}
