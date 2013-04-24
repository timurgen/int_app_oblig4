/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hin.stg.intapp.oblig4web.servlet;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.hin.stg.intapp.oblig4web.beans.PersonFacadeLocal;
import no.hin.stg.intapp.oblig4web.entities.Person;

/**
 *
 * @author Timur Samkharadze timur.samkharadze@gmail.com
 */
public class Kunde extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String ACTION = "action";
    private static final String GET_ALL = "get-all";
    private static final String GET_BY_ID = "get-by-id";
    private static final String CREATE = "create";
    @EJB
    private PersonFacadeLocal personFacade;
    /**
     * POJO to JSON converter
     */
    private Gson gson;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //sjekker om bruker har tilgang til servlet
        if(request.getSession(false) == null || request.getSession().getAttribute("username") == null){
            response.sendError(403, "access denied ");
            return;
        }
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        gson = new Gson();
        String output = "";
        String action = request.getParameter(ACTION);
        if (action == null || action.isEmpty()) {//returnerer feil dersom action ikke er definert
            response.sendError(400, "empty action");
            return;
        }

        /**
         * <p>Håndterer forskjellige forespørsler
         */
        switch (action) {
            case GET_ALL: // returnerer alle kunder (leverer hele liste uten å bruke range)
                //passord leveres med, siden sikkerhet er ikke en del av oppgave
                //ellers må passordene vaskes
                output = gson.toJson(personFacade.findAll());
                break;
            case GET_BY_ID: //returnerer kunde med gitt id, passord leveres med som klar tekst
                output = getPersonById(request, response);
                break;
            case CREATE: //lagrer ny kunde
                output = gson.toJson(createPerson(request, response));
                break;
            default://must not be reached
                throw new AssertionError();
        }

        try {
            out.println(output);
        } finally {
            out.flush();
            out.close();
        }


    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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

    /**
     * <p>Returnerer JSON representasjon av Person objekt
     * <p> password felt er med som klar tekst
     * @param request HttpServlet request
     * @param response HttpServlet response
     * @return JSON representasjon av <code>Person</code> objekt
     * @throws NumberFormatException
     * @throws IOException
     */
    private String getPersonById(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, IOException {
        Object id = request.getParameter("id");
        String output = null;
        if (id == null) {
            response.sendError(400, "empty id");
            return output;
        } else {
            if (id instanceof String) {
                Integer _id = Integer.valueOf((String) id);
                output = gson.toJson(personFacade.find(_id));
            }
        }
        return output;
    }

    /**
     * 
     * @param request
     * @param response
     * @return
     * @throws IOException 
     */
    private String createPerson(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Person p = new Person();
        //setter fødselsdato
        String birthdate = request.getParameter("birthdate");   //SQL date
        try {
            DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
            df.setLenient(true);
            p.setFodselsdato(df.parse(birthdate));
        } catch (ParseException ex) {
            Logger.getLogger(Kunde.class.getName()).log(Level.SEVERE, null, ex);
        }
        //setter fødselsnummer
        String idNumber = request.getParameter("idNumber");    //SQL varchar 5
        if (idNumber != null) {
            p.setFodselsnummer(idNumber);
        } else {
            response.sendError(400, "Mangler foedselsnummer");
            return null;
        }
        //setter name
        String name = request.getParameter("name");        //SQL varchar 30
        if (name != null) {
            p.setNavn(name);
        } else {
            response.sendError(400, "Mangler navn");
            return null;
        }
        //setter addresse (kan være null)
        String address1 = request.getParameter("addresse1");    //SQL varchar 30
        String address2 = request.getParameter("addresse2");    //SQL varchar 30
        p.setAdresselinje1(address1);
        p.setAdresselinje2(address2);
        //setter postnummer
        String postNumber = request.getParameter("postNumber");  //SQL varchar 4
        if (postNumber != null) {
            p.setPostnummer(postNumber);
        } else {
            response.sendError(400, "Mangler postnummer");
            return null;
        }
        //setter passord, ligger som klar text siden sikkerhet ikke er oppgavnes temaet
        String password = request.getParameter("password");    //SQL varchar 50
        if (password != null) {
            p.setPassord(password);
        } else {
            response.sendError(400, "Mangler passord");
            return null;
        }
        //setter pin-code
        String pinCode = request.getParameter("pinCode");     //SQL int 11
        if (pinCode != null) {
            p.setPINkode(Integer.valueOf(pinCode));
        } else {
            response.sendError(400, "Mangler PIN kode");
            return null;
        }
        //setter poststed
        String postSted = request.getParameter("postSted");    //SQL varchar 50
        if (postSted != null) {
            p.setPoststed(postSted);
        } else {
            response.sendError(400, "Mangler poststed");
            return null;
        }
        //setter comment
        String comment = request.getParameter("comment");     //SQL varchar 250
        if (comment != null) {
            p.setKommentar(comment);
        } else {
            response.sendError(400, "Mangler kommentar");
            return null;
        }
        try {
            personFacade.create(p);
            return "user created successfully";
        } catch(Exception e) {
            return e.getCause().getMessage();
        }
        
        
    }
}
