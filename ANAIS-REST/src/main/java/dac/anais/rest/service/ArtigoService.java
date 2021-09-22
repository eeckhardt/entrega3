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
import dac.anais.rest.persistencia.JPAArtigosDAO;
import dac.anais.rest.persistencia.ListaAutores;
import dac.anais.rest.persistencia.Volume;

/**
 *
 * @author viter
 */
@Path("artigo")
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class ArtigoService {
    
    private final JsonBuilderFactory factory;
    
    @Context
    UriInfo uriInfo;

    public ArtigoService() {
        factory = Json.createBuilderFactory(null);
    }

    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    public void create(@FormParam("ordVol") int ordVol, @FormParam("idioma") String idioma, 
            @FormParam("titulo") String titulo, @FormParam("tituloEN") String tituloEN, @FormParam("resumo") String resumo, @FormParam("resumoEN") String resumoEN,
            @FormParam("pchave") String pchave, @FormParam("pchaveEN") String pchaveEN, @FormParam("paginas") int paginas,
            @FormParam("listAutor") ListaAutores listAutor,@FormParam("vol") Volume vol ) {
        
        
        JPAArtigosDAO dao = new JPAArtigosDAO();
        Artigos e = new Artigos();
        e.setOrdVol(ordVol);
        e.setIdioma(idioma);
        e.setTitulo(titulo);
        e.setTituloEN(tituloEN);
        e.setResumo(resumo);
        e.setResumoEN(resumoEN);
        e.setPchave(pchave);
        e.setPchaveEN(pchaveEN);
        e.setPaginas(paginas);
        e.setListAutor(listAutor);
        e.setVol(vol);
        
        dao.salva(e);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createJson(String ent) {
        
        JPAArtigosDAO dao = new JPAArtigosDAO();
        JsonReaderFactory factory = Json.createReaderFactory(null);
        JsonReader jsonReader = factory.createReader(new StringReader(ent));
        JsonObject json = jsonReader.readObject();
        Artigos e = new Artigos();
        
        e.setOrdVol(json.getInt("ordVol"));
        e.setIdioma(json.getString("idioma"));
        e.setTitulo(json.getString("titulo"));
        e.setTituloEN(json.getString("tituloEN"));
        e.setResumo(json.getString("resumo"));
        e.setResumoEN(json.getString("resumoEN"));
        e.setPchave(json.getString("pchave"));
        e.setPchaveEN(json.getString("pchaveEN"));
        e.setPaginas(json.getInt("paginas"));
       /* e.setListAutor(json.getString("listAutor"));
        e.setVol(json.getJsonArray(vol));
        */
        dao.salva(e);
        URI uri = uriInfo.getAbsolutePathBuilder().path("artigo/" + String.valueOf(e.getId())).build();
        Response resp = Response.created(uri).build();
        return resp;
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Artigos entity) {
        JPAArtigosDAO dao = new JPAArtigosDAO();
        dao.salva(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        JPAArtigosDAO dao = new JPAArtigosDAO();
        dao.deleta(id);
    }

    @GET
    @Path("artigo/{id}")
    @Produces({MediaType.APPLICATION_XML})
    public Artigos find(@PathParam("id") Long id) {
        JPAArtigosDAO dao = new JPAArtigosDAO();
        return dao.recupera(id);
    }

    @GET
    @Path("artigo/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public JsonObject findJson(@PathParam("id") Long id) {
        JPAArtigosDAO dao = new JPAArtigosDAO();
        Artigos e = dao.recupera(id);
        if (e == null)
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        JsonObjectBuilder builder = factory.createObjectBuilder();
        JsonObject obj = builder.add("id", e.getId())
                
                .add("ordVol",e.getId())
                .add("idioma", e.getIdioma())
                .add("titulo", e.getTitulo())
                .add("tituloEN", e.getTituloEN())
                .add("resumo", e.getResumo())
                .add("resumoEN", e.getResumoEN())
                .add("pchave", e.getPchave())
                .add("pchaveEN", e.getPchaveEN())
                .add("paginas", e.getPaginas())
               // .add("listAutor", e.getListAutor())
            //    .add("vol", e.getVol())
                
                .build();
        return obj;
    }

    @GET
    @Path("artigo/{id}")
    @Produces({MediaType.TEXT_PLAIN})
    public String findplain(@PathParam("id") Long id) {
        JPAArtigosDAO dao = new JPAArtigosDAO();
        return dao.recupera(id).toString();
    }

    @GET
    @Path("artigo/todos")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Artigos> findAll() {
        JPAArtigosDAO dao = new JPAArtigosDAO();
        return dao.buscaTudo();
    }

    

}
