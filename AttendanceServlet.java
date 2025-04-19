import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AttendanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String name = request.getParameter("name");
        String date = request.getParameter("date");
        String status = request.getParameter("status");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/yourDB", "root", "password");

            String query = "INSERT INTO attendance(name, date, status) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, date);
            ps.setString(3, status);

            int rows = ps.executeUpdate();
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            if (rows > 0) {
                out.println("<h3>Attendance recorded successfully!</h3>");
            } else {
                out.println("<h3>Failed to record attendance.</h3>");
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
