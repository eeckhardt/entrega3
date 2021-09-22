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
public interface ArtigosDAO {

    void salva(Artigos e);

    Artigos recupera(Long id);

    //List<Artigos> buscaSobrenome(String snome);

    List<Artigos> buscaTudo();
}
