/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author simeenkhan
 */
public class user extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String str1 = request.getParameter("phone");
            String str2 = request.getParameter("pin");
            Cookie c1 = new Cookie (str1,str2);
            response.addCookie(c1);
            out.println("<html><body>");
            out.println("<header><center><img src=\"/Users/simeenkhan/Desktop/D2h_logo.jpg\" alt=\"Videocon d2hLogo\" width=\"400\" height=\"100\"></center></header>\n" +
"");
            out.println("<form method='post' action='paymentServlet'>");
            out.println("<legend>Recharge your Videocon d2h</legend>");
            out.println("<br>\n" +
"            <label for=\"ID\">Customer ID: </label>\n" +
"            <input type=\"text\" id=\"ID\" name=\"ID\"><br><br>");
            out.println("<label for=\"package\">Current Package(s)</label>\n" +
"            <input type=\"text\" id=\"package\" name=\"package\"><br><br>");
            out.println("<label for=\"date\">Recharge due on</label>\n" +
"            <input type=\"date\" id=\"date\" name=\"date\"><br><br>");
            out.println("<label for=\"month\">Select Recharge Term</label>\n" +
"            <input type=\"month\" id=\"month\" name=\"month\"><br><br>");
            out.println("<label for=\"amt\">Amount</label>\n" +
"            <input type=\"number\" id=\"amt\" name=\"amt\"><br><br>");
            out.println("<input type=\"submit\" value=\"Submit\"></fieldset></form></body></html>");
        } catch(Exception e){}
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
