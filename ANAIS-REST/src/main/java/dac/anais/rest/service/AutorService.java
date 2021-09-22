/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.anais.rest.service;


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
import dac.anais.rest.persistencia.Autores;
import dac.anais.rest.persistencia.JPAAutoresDAO;

/**
 *
 * @author viter
 */
@Path("autor")
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})

public class AutorService {
    
    private final JsonBuilderFactory factory;
    
    @Context
    UriInfo uriInfo;

    public AutorService() {
        factory = Json.createBuilderFactory(null);
    }

    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    public void create(@FormParam("ordArt") int ordArt,@FormParam("primeironome") String primeironome, @FormParam("meio") String meio, @FormParam("sobrenome") String snome, @FormParam("email") String email, @FormParam("afiliacao") String afiliacao, @FormParam("afiliacaoEN") String afiliacaoEN, @FormParam("registro") String registro, @FormParam("pais") String pais ) {
        
        
        JPAAutoresDAO dao = new JPAAutoresDAO();
        Autores e = new Autores();
        e.setOrdArt(ordArt);
        e.setPrimeiroNome(primeironome);
        e.setMeio(meio);
        e.setSobrenome(snome);
        e.setEmail(email);
        e.setAfiliacao(afiliacao);
        e.setAfiliacaoEN(afiliacaoEN);
        e.setRegistro(registro);
        e.setPais(pais);
        dao.salva(e);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createJson(String ent) {
        
        JPAAutoresDAO dao = new JPAAutoresDAO();
        JsonReaderFactory factory = Json.createReaderFactory(null);
        JsonReader jsonReader = factory.createReader(new StringReader(ent));
        JsonObject json = jsonReader.readObject();
        Autores e = new Autores();
        e.setPrimeiroNome(json.getString("primeironome"));
        e.setMeio(json.getString("meio"));
        e.setSobrenome(json.getString("sobrenome"));
        e.setEmail(json.getString("email"));
        e.setAfiliacao(json.getString("afiliacao"));
        e.setAfiliacaoEN(json.getString("afiliacaoEN"));
        e.setPais(json.getString("pais"));
        e.setRegistro(json.getString("registro"));
        
        dao.salva(e);
        URI uri = uriInfo.getAbsolutePathBuilder().path("autor/" + String.valueOf(e.getId_autores())).build();
        Response resp = Response.created(uri).build();
        return resp;
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Autores entity) {
        JPAAutoresDAO dao = new JPAAutoresDAO();
        dao.salva(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        JPAAutoresDAO dao = new JPAAutoresDAO();
        dao.deleta(id);
    }

    @GET
    @Path("autor/{id}")
    @Produces({MediaType.APPLICATION_XML})
    public Autores find(@PathParam("id") Long id) {
        JPAAutoresDAO dao = new JPAAutoresDAO();
        return dao.recupera(id);
    }

    @GET
    @Path("autor/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public JsonObject findJson(@PathParam("id") Long id) {
        JPAAutoresDAO dao = new JPAAutoresDAO();
        Autores e = dao.recupera(id);
        if (e == null)
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        JsonObjectBuilder builder = factory.createObjectBuilder();
        JsonObject obj = builder.add("id", e.getId_autores())
                .add("ordArt", e.getOrdArt())
                .add("primeironome", e.getPrimeiroNome())
                .add("meio", e.getMeio())
                .add("sobrenome", e.getSobrenome())
                .add("afiliacao", e.getAfiliacao())
                .add("afiliacaoEN", e.getAfiliacaoEN())
                .add("email", e.getEmail())
                .add("pais", e.getPais())
                .add("registro", e.getRegistro())
                .build();
        return obj;
    }

    @GET
    @Path("autor/{id}")
    @Produces({MediaType.TEXT_PLAIN})
    public String findplain(@PathParam("id") Long id) {
        JPAAutoresDAO dao = new JPAAutoresDAO();
        return dao.recupera(id).toString();
    }

    @GET
    @Path("todos")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Autores> findAll() {
        JPAAutoresDAO dao = new JPAAutoresDAO();
        return dao.buscaTudo();
    }

    @GET
    @Path("sobrenomes/{sn}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Autores> findAll(@PathParam("sn") String sn) {
        JPAAutoresDAO dao = new JPAAutoresDAO();
        return dao.buscaSobrenome(sn);
    }

}
