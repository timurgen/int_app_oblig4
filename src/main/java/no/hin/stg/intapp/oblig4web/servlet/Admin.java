/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hin.stg.intapp.oblig4web.servlet;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.hin.stg.intapp.oblig4web.beans.PersonFacadeLocal;

/**
 * <p>Her ligger diverse funksjoner, som egentlig ikke er relevante til oppgave
 *
 * @author Timur Samkharadze
 */
public class Admin extends HttpServlet {

    private static final long serialVersionUID = 1L;
    @EJB
    private PersonFacadeLocal personIface;

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
        response.setContentType("application/json");
        String action = request.getParameter("action");
        if (action == null || action.isEmpty()) {
            response.sendError(400, "empty action");

        } else {
            switch (action) {
                case "get-db-status"://TODO check DB connection
                    checkDB(request, response);
                    break;
                default:
                    response.sendError(400, "bad action");
            }
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

    private void checkDB(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            this.personIface.count();
            response.getWriter().println("ok");
        }
        catch (Exception e) {
            response.getWriter().println(e.getMessage());
        }
        finally {
            response.getWriter().close();
        }

    }
}
