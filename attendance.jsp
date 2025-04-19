<%@ page language="java" %>
<html>
<body>
    <form action="AttendanceServlet" method="post">
        Name: <input type="text" name="name"><br>
        Date: <input type="date" name="date"><br>
        Status: 
        <select name="status">
            <option value="Present">Present</option>
            <option value="Absent">Absent</option>
        </select><br>
        <input type="submit" value="Submit Attendance">
    </form>
</body>
</html>
