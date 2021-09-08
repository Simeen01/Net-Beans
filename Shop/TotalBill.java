/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Amal Sunil
 */
public class TotalBill extends HttpServlet {

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
        
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            String n1=request.getParameter("n1");
            String n2=request.getParameter("n2");
            Cookie ck[]=request.getCookies();       //fetching the data from the cookie
            String n3=ck[0].getName();
            String n4=ck[0].getValue();
            
            int bill=(Integer.valueOf(n4)*400)+(Integer.valueOf(n2)*500);
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<body>");
            out.println("<h1>Product Details and Total Bill</h1>");            
            out.println("<table border=3><th>Sr.No.</th><th>Product Name</th>");
            out.println("<th>Product Quantity</th><th>Product Price</th>");
            out.println("<tr><td>1</td><td>"+n3+"</td><td>"+n4+"</td><td>400</td>");
            out.println("<tr><td>1</td><td>"+n1+"</td><td>"+n2+"</td><td>500</td>");
            out.println("<tr align='right'><td colspan=3>Total Bill</td><td>" +bill+ "</td></tr>");
            out.println("</body>");
            out.println("</html>");
            
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
            s.setDouble(6,Double.valueOf(bill));
            
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
