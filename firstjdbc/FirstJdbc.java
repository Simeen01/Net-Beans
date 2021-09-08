/* 

 * To change this license header, choose License Headers in Project Properties. 

 * To change this template file, choose Tools | Templates 

 * and open the template in the editor. 

 */ 

package firstjdbc; 

import java.sql.*; 

public class FirstJdbc{ 

    public static void main(String[] args) {                      

        Connection conn = null; 
        Statement stmt = null; 

        try{ 

        conn = DriverManager.getConnection("jdbc:mysql://localhost/test","root","");      

        stmt = conn.createStatement(); 

        String sql; 

        sql = "SELECT * from emp"; 

        ResultSet rs = stmt.executeQuery(sql); 


        while(rs.next()){    

            System.out.print("Emp ID: " + rs.getInt(1));          

            System.out.print(", Emp Name: " + rs.getString(2)); 

            System.out.print(", Salary: " + rs.getInt(3)); 

            System.out.println();    

      }       

        rs.close(); 
        stmt.close(); 
        conn.close(); 

    }catch(SQLException se){ 
      se.printStackTrace(); 

   } 
    } 
} 