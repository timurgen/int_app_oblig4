/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hin.stg.intapp.oblig4web.servlet;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.hin.stg.intapp.oblig4web.beans.KontoTransaksjonFacadeLocal;

/**
 *
 * @author Timur Samkharadze
 */
public class Transaction extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String ACTION = "action";
    private static final String GET_ALL = "get-all";
    private static final String GET_BY_ID = "get-by-id";
    private static final String GET_BY_ACCOUNT = "get-by-acc";
    private static final String CREATE = "create";
    @EJB
    private KontoTransaksjonFacadeLocal trIface;
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
            case GET_ALL:
                output = getAllTransactions();
                break;
            case GET_BY_ACCOUNT:
                output = getTrByAccNmbr(request, response);
                break;
            case CREATE:
                //TODO implement create transaction function
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
     * <p>returnerer alle transaksjoner i form av JSON String
     *
     * @return
     */
    private String getAllTransactions() {
        return this.gson.toJson(trIface.findAll());
    }

    /**
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    private String getTrByAccNmbr(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String param = request.getParameter("accnmbr");
        if (param == null || param.isEmpty()) {
            response.sendError(400, "bad id");
            return null;
        }
        int accNmbr = Integer.valueOf(param);
        return this.gson.toJson(this.trIface.findByAccNmbr(accNmbr));
    }
}
