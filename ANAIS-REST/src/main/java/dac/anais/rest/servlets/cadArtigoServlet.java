/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.anais.rest.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author erika
 */
public class cadArtigoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        URI uri;
        int status=0;
        Response.StatusType statusInfo= null;
        String statusMsg="";
        URI res = null;
        JsonBuilderFactory factory;

        response.setContentType("text/html;charset=UTF-8");

        Client client = ClientBuilder.newClient();

        String url = "http://localhost:8080/AnaisRest/";

        try {

            uri = new URI(url);
            WebTarget webTarget = client.target(uri);
            WebTarget wt = webTarget.path("resources/artigo");

            Invocation.Builder invocationBuilder = wt.request(MediaType.APPLICATION_JSON);

            factory = Json.createBuilderFactory(null);
            JsonObjectBuilder builder = factory.createObjectBuilder();
            JsonObject obj = builder.add("id", request.getParameter("id"))
                    .add("titulo",request.getParameter("titulo"))
                    .add("tituloEN",request.getParameter("tituloEN"))
                    .add("chave",request.getParameter("chave"))
                    .add("chaveEN",request.getParameter("chaveEN"))
                    .add("resumo", request.getParameter("resumo"))
                    .add("resumoEN",request.getParameter("resumoEN")) 
                    .add("idioma",request.getParameter("idioma")) 
                    .add("volume",request.getParameter("volume")) 
                    .add("paginas",request.getParameter("paginas"))
                    .add("lista",request.getParameter("lista"))
                    .build();
            Response resposta = invocationBuilder.post(Entity.entity(obj, MediaType.APPLICATION_JSON));

            //Entrada entrada = resposta.readEntity(Entrada.class);
            status = resposta.getStatus();
            statusInfo = resposta.getStatusInfo();
            statusMsg = statusInfo.getReasonPhrase();
            res = resposta.getLocation();

        } catch (URISyntaxException ex) {
            Logger.getLogger(cadArtigoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>API Cadastra Artigo</title>");
            out.println("</head>");
            out.println("<body>");
            
            out.println("<p>Status da resposta: " + status + " \"" + statusMsg + "\"</p>");
            if (res !=null)
                out.println("<p>URI: " + res.toString() + "</p>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
