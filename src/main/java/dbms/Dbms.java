package dbms;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/l")
public class Dbms extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public Dbms() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();

        Connection con=null;
        java.sql.Statement stmt=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vishal","root","1234");
            stmt = con.createStatement();   
            rs = stmt.executeQuery("select * from data");
            while (rs.next()) {
                pw.print("<br>"+rs.getString("Id")+", "+rs.getString("name")+rs.getString("password"));
            }
        }
        catch(Exception e) {pw.print(e);}
			}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
