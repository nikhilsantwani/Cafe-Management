package data;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sign")
public class signin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public signin() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        // Retrieve form parameters
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");

        // JDBC Connection parameters
        String jdbcUrl = "jdbc:mysql://localhost:3306/cafe";
        String User = "root";
        String Pass = "1234";

        try {
            //MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

           
            Connection conn = DriverManager.getConnection(jdbcUrl, User, Pass);
           
            PreparedStatement ps = conn.prepareStatement("INSERT INTO signin (name,email,password,phone) VALUES (?, ?, ?, ?)");

           
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, phone);

            int status = ps.executeUpdate();
            ps.close();
            conn.close();

            if (status > 0) {
                response.sendRedirect("index.html");
            } else {
            
                out.println("<p>Failed to sign in. Please try again.</p>");
            }

        } catch (Exception ex) {
            out.print(ex);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
