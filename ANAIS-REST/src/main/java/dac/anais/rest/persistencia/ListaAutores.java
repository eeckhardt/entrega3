/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.anais.rest.persistencia;

import dac.anais.rest.persistencia.Autores;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 *
 * @author viter
 */
@XmlRootElement
@XmlSeeAlso(Autores.class)
public class ListaAutores extends ArrayList<Autores> {

    public ListaAutores() {
        super();
    }

    public ListaAutores(Collection<? extends Autores> c) {
        super(c);
    }

    @XmlElement(name = "autores")
    public List<Autores> getAutores() {
        return this;
    }

    public void setListaAutores(List<Autores> lista) {
        this.addAll(lista);
    }
}
