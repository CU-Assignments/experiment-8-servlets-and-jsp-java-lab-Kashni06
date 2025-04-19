import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EmployeeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/yourDB", "root", "password");

            String query = "SELECT * FROM employees";
            if (id != null && !id.isEmpty()) {
                query += " WHERE id = ?";
            }

            PreparedStatement ps = con.prepareStatement(query);
            if (id != null && !id.isEmpty()) {
                ps.setInt(1, Integer.parseInt(id));
            }

            ResultSet rs = ps.executeQuery();
            out.println("<table border='1'><tr><th>ID</th><th>Name</th></tr>");
            while (rs.next()) {
                out.println("<tr><td>" + rs.getInt("id") + "</td><td>" + rs.getString("name") + "</td></tr>");
            }
            out.println("</table>");

            con.close();
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
}
