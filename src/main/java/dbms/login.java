package dbms;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/log")
public class login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public login() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");

        String url = "jdbc:mysql://localhost:3306/cafe";
        String Dbuser = "root";
        String DBPASS = "1234";
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, Dbuser, DBPASS);
            String query = "SELECT * FROM signin WHERE email = ? AND password = ?";
            ps = con.prepareStatement(query);
            
            ps.setString(1, email);
            ps.setString(2, pass);
 
            rs = ps.executeQuery();

            if (rs.next()) {
                response.sendRedirect("index.html");
            } else {
                out.println("<h1>Invalid email or password. Please try again.</h1>");
            }
        } catch (Exception ex) {
            out.println("<h1>Error: " + ex.getMessage() + "</h1>");
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.close();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
