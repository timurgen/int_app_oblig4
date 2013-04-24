/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hin.stg.intapp.oblig4web.servlet;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.hin.stg.intapp.oblig4web.beans.KontoFacadeLocal;
import no.hin.stg.intapp.oblig4web.entities.Konto;

/**
 *
 * @author Timur Samkharadze
 */
public class Account extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String ACTION = "action";
    private static final String GET_ALL = "get-all";
    private static final String GET_BY_ID = "get-by-id";
    private static final String CREATE = "create";
    @EJB
    private KontoFacadeLocal kontoIface;
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
        if (request.getSession(false) == null || request.getSession().getAttribute("username") == null) {
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
        switch (action) {
            case CREATE: //skaper ny konto
                createKonto(request, response);
                break;
            case GET_ALL://returnerer alle kontoer
                output = gson.toJson(kontoIface.findAll());
                break;
            case GET_BY_ID: //returnerer konto med gitt id
                //TODO implemnent returnere konto med gitt id funksjon
                break;
            default:
                throw new AssertionError();
        }
        try {
            out.println(output);
        }
        finally {
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
     * <p>Lagrer ny konto i database
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    private String createKonto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Konto k = new Konto();
        //setter person id
        String personId = request.getParameter("personId");
        if (personId == null || personId.isEmpty()) {
            response.sendError(400, "Mangler personId");
            return null;
        } else {
            k.setPersonid(Integer.valueOf(personId));
        }
        //setter bank id
        String bankId = request.getParameter("bankId");
        if (bankId == null || bankId.isEmpty()) {
            response.sendError(400, "Mangler bank id");
            return null;
        } else {
            k.setBankid(bankId);
        }
        //setter kontotype
        String kontotype = request.getParameter("kontotype");
        if (kontotype == null || kontotype.isEmpty()) {
            response.sendError(400, "Mangler kontotype");
            return null;
        } else {
            k.setKontotype(kontotype);
        }
        //setter kontonummer
        String kontonummer = request.getParameter("kontonummer");
        if (kontonummer == null || kontonummer.isEmpty()) {
            response.sendError(400, "Mangler kontonummer");
            return null;
        } else {
            k.setKontonr(kontonummer);
        }
        //setter endringsdato
        k.setSisteEndringsTidspunkt(new Date());
        //setter oppstartssaldo
        String saldo = request.getParameter("saldo");
        if (saldo == null || saldo.isEmpty()) {
            k.setSaldo(0.0);
        } else {
            k.setSaldo(Double.valueOf(saldo));
        }
        try {
            kontoIface.create(k);
            return gson.toJson("konto created successfully");
        }
        catch (Exception e) {
            return e.getCause().getMessage();
        }

    }
}
