/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.anais.rest.persistencia;

import java.util.List;

/**
 *
 * @author viter
 */
public interface ListaAutoresDAO {

    void salva(Autores e);

    Autores recupera(Long id);

    List<Autores> buscaSobrenome(String snome);

    List<Autores> buscaTudo();
}
