/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

/**
 *
 * @author simeenkhan
 */
public class paymentServlet extends HttpServlet {

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
            String n1 = request.getParameter("ID");
            String n2 = request.getParameter("package");
            String n3 = request.getParameter("date");
            String n4 = request.getParameter("month");
            String n5 = request.getParameter("amt");
            Cookie ck[] = request.getCookies();
            String g1 = ck[0].getValue();
            String g2 = ck[0].getName();
            String g3 = ck[0].getValue();
            String g4 = ck[0].getValue();
            String g5 = ck[0].getValue();
            
            out.println("<html><body>");
            out.println("<h1>Payment Details</h1>");
            out.println("<table border=\"3\">");
            out.println("<tr>");
            out.println("<th>Customer ID</th>");
            out.println("<th>Package</th>");
            out.println("<th>Recharge due on</th>");
            out.println("<th>Recharge Term</th>");
            out.println("<th>Amount</th>");
            out.println("</tr>");
            out.println("<tr><td>n1</td><td>n2</td><td>n3</td><td>n4</td><td>n5</td></tr>");
            out.println("<tr><td>g1</td><td>g2</td><td>g3</td><td>g4</td><td>g5</td></tr>");
            out.println("<tr align=\"right\"><td>Total</td><td>g5</td></tr>");
            out.println("</table></body></html>");
            
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost/shop","root","");
            stmt=conn.createStatement();
            
            rs=stmt.executeQuery("Select * from cart"); //the entire table is loaded 
            rs.last();
            int n=rs.getInt("orderid");
            
            String sql="insert into cart values (?,?,?,?,?,?)";
            
            PreparedStatement s=conn.prepareStatement(sql);
            
            s.setInt(1,n+1);
            s.setString(2,n3);
            s.setInt(3,Integer.valueOf(n4));
            s.setString(4,n1);
            s.setInt(5,Integer.valueOf(n2));
            s.setDouble(6,Double.valueOf());
            
            int r=s.executeUpdate();
            if(r>0){
                out.println("<h4>Data Added Successfully"+r+"</h4>");
            }  
            System.out.println(r);
        }
        catch(Exception e){
            System.out.println(e);
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
