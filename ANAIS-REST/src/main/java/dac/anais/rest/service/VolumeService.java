/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.anais.rest.service;


import dac.anais.rest.persistencia.Artigos;
import java.io.StringReader;
import java.net.URI;
import java.util.List;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import dac.anais.rest.persistencia.JPAVolumeDAO;
import dac.anais.rest.persistencia.ListaArtigos;
import dac.anais.rest.persistencia.Volume;

/**
 *
 * @author viter
 */
@Path("volume")
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})


public class VolumeService {
    
    private final JsonBuilderFactory factory;
    
    @Context
    UriInfo uriInfo;

    public VolumeService() {
        factory = Json.createBuilderFactory(null);
    }

  /*  @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    public void create(@FormParam("sigla") String sigla, @FormParam("edicao") int edicao, @FormParam("cidade") String cidade, @FormParam("dtinicio") String dtinicio, @FormParam("descricao") String descricao, @FormParam("descricaoEN") String descricaoEN, @FormParam("id") int id, @FormParam("lArtigos") ListaArtigos liArtigos, List<Artigos> ListaArtigos) {
        
        
        JPAVolumeDAO dao = new JPAVolumeDAO();
        Volume e = new Volume();
        e.setSigla(sigla);
        e.setEdicao(edicao);
        e.setCidade(cidade);
        e.setDtInicio(dtinicio);
        e.setDescricaoPT(descricao);
        e.setDescricaoEN(descricaoEN);
        e.setLiArtigos(ListaArtigos);
     
        
        dao.salva(e);
    }
*/
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createJson(String ent, String ListaArtigos) {
        
        JPAVolumeDAO dao = new JPAVolumeDAO();
        JsonReaderFactory factory = Json.createReaderFactory(null);
        JsonReader jsonReader = factory.createReader(new StringReader(ent));
        JsonObject json = jsonReader.readObject();
        Volume e = new Volume();
        
        e.setSigla(json.getString("sigla"));
        e.setEdicao(json.getInt("edicao"));
        e.setCidade(json.getString("cidade"));
        e.setDtInicio(json.getString("dtinicio"));
        e.setDescricaoPT(json.getString("descricao"));
        e.setDescricaoEN(json.getString("descricaoEN"));
        e.setLiArtigos(json.getString(ListaArtigos));
       
        dao.salva(e);
        URI uri = uriInfo.getAbsolutePathBuilder().path("volume/" + String.valueOf(e.getId())).build();
        Response resp = Response.created(uri).build();
        return resp;
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Volume entity) {
        JPAVolumeDAO dao = new JPAVolumeDAO();
        dao.salva(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        JPAVolumeDAO dao = new JPAVolumeDAO();
        dao.deleta(id);
    }

    @GET
    @Path("volume/{id}")
    @Produces({MediaType.APPLICATION_XML})
    public Volume find(@PathParam("id") Long id) {
        JPAVolumeDAO dao = new JPAVolumeDAO();
        return dao.recupera(id);
    }

    @GET
    @Path("volume/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public JsonObject findJson(@PathParam("id") Long id) {
        JPAVolumeDAO dao = new JPAVolumeDAO();
        
        
        Volume e = dao.recupera(id);
        if (e == null)
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        JsonObjectBuilder builder = factory.createObjectBuilder();
        JsonObject obj = builder.add("id", e.getId())
                .add("sigla",e.getSigla())
                .add("edicao",e.getEdicao())
                .add("cidade",e.getCidade())
                .add("dtinicio", e.getDtInicio())
                .add("descricao", e.getDescricaoPT())
                .add("descricaoEN",e.getDescricaoEN())
                .add("listaArtigos",e.getListaArtigos())
              
                .build();
        return obj;
    }

    @GET
    @Path("volume/{id}")
    @Produces({MediaType.TEXT_PLAIN})
    public String findplain(@PathParam("id") Long id) {
        JPAVolumeDAO dao = new JPAVolumeDAO();
        return dao.recupera(id).toString();
    }

    @GET
    @Path("volume/todos")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Volume> findAll() {
        JPAVolumeDAO dao = new JPAVolumeDAO();
        return dao.buscaTudo();
    }

    /*
    @GET
    @Path("sobrenomes/{sn}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Entrada> findAll(@PathParam("sn") String sn) {
        JPAEntradaDAO dao = new JPAEntradaDAO();
        return dao.buscaSobrenome(sn);
    }
*/
}
