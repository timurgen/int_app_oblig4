/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.hin.stg.intapp.oblig4web.servlet;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.hin.stg.intapp.oblig4web.beans.KontoFacadeLocal;
import no.hin.stg.intapp.oblig4web.beans.KontoTransaksjonFacadeLocal;
import no.hin.stg.intapp.oblig4web.entities.Konto;
import no.hin.stg.intapp.oblig4web.entities.KontoTransaksjon;

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
    private static final String GET_SALDO = "get-saldo";
    private static final String SETTE_INN = "sette-inn";
    private static final String TA_UT = "ta-ut";
    @EJB
    private KontoFacadeLocal kontoIface;
    @EJB
    KontoTransaksjonFacadeLocal transactionIface;
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
            case GET_SALDO:
                output = getSaldo(request, response); //returnerer saldo til en gitt konto
                break;
            case SETTE_INN://setter penger inn på konto
                output = gson.toJson(settePengerInn(request, response));
                break;
            case TA_UT:
                output = gson.toJson(taUtPenger(request, response));
                break;
            default://must not be reached
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

    /**
     * <p> Obtains saldo for given account
     *
     * @param request
     * @param response
     * @return
     */
    private String getSaldo(HttpServletRequest request, HttpServletResponse response) {
        //TODO
        return null;
    }

    /**
     * <p> setter inn penger på konto
     *
     * @param request
     * @param response
     * @return string representation of operation result
     * @throws IOException
     */
    private String settePengerInn(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String _belop = request.getParameter("belop");
        String _konto = request.getParameter("konto");
        if (_belop == null || _belop.isEmpty()) {
            response.sendError(400, "bad value");
            return null;
        }
        if (_konto == null || _konto.isEmpty()) {
            response.sendError(400, "bad konto");
            return null;
        }
        double belop = Double.valueOf(_belop);
        if (belop <= 0.0) {
            response.sendError(400, "negative/zero value");
            return null;
        }
        synchronized (this) {
            Konto k = this.kontoIface.findByKontoNmr(_konto);
            if (k != null) {
                k.setSaldo(k.getSaldo() + belop);
                k.setSisteEndringsTidspunkt(new Date());
                this.kontoIface.edit(k);
                KontoTransaksjon kt = new KontoTransaksjon();
                kt.setBelop(belop);
                kt.setFraKonto(-1);
                kt.setInitsiator(1);
                kt.setRegistreringsTidspunkt(new Date());
                kt.setTilKonto(Integer.valueOf(_konto));
                kt.setTransType(1);
                kt.setTransaksjonsTid(new Date());
                kt.setTranskasjonsbeskrivelse("penger satt på bankkontor");
                this.transactionIface.create(kt);
                return "operation successed";
            }
        }
        return "opps, feil...";
    } //end of settePengerInn

    /**
     *  <p> Trekker pnger fra bank konto
     * @param request
     * @param response
     * @return string representation of operation result
     * @throws IOException 
     */
    private String taUtPenger(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String _belop = request.getParameter("belop");
        String _konto = request.getParameter("konto");
        if (_belop == null || _belop.isEmpty()) {
            response.sendError(400, "bad value");
            return null;
        }
        if (_konto == null || _konto.isEmpty()) {
            response.sendError(400, "bad konto");
            return null;
        }
        double belop = Double.valueOf(_belop);
        if (belop <= 0.0) {
            response.sendError(400, "negative/zero value");
            return null;
        }
        synchronized (this) {
            Konto k = this.kontoIface.findByKontoNmr(_konto);
            if (k != null) {
                if ((k.getSaldo() - belop) < 0) {
                    response.sendError(400, "ikke nok penger på konto");
                    return null;
                }
                k.setSaldo(k.getSaldo() - belop);
                k.setSisteEndringsTidspunkt(new Date());
                this.kontoIface.edit(k);
                KontoTransaksjon kt = new KontoTransaksjon();
                kt.setBelop(belop);
                kt.setFraKonto(Integer.valueOf(_konto));
                kt.setInitsiator(1);
                kt.setRegistreringsTidspunkt(new Date());
                kt.setTilKonto(-1);
                kt.setTransType(1);
                kt.setTransaksjonsTid(new Date());
                kt.setTranskasjonsbeskrivelse("penger tatt ut på bankkontor");
                this.transactionIface.create(kt);
                return "operation successed";
            }
        }
        return "opps, feil...";
    }
}
