package org.btm.PostApp;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
public class FirstServlet extends HttpServlet
{
 @Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
{
  String sid=req.getParameter("i");
  int id=Integer.parseInt(sid);
  String name=req.getParameter("nm");
  String dept=req.getParameter("dp");
  String sperc=req.getParameter("pr");
  double perc=Double.parseDouble(sperc);
  PrintWriter out=resp.getWriter();
  out.println("<html><body bgcolor='red'>"
  		     + "<h1>Student Details are "+ name+" "+dept+" </h1>"
  		     + "</body></html>");
  out.close();
  Connection con=null;
  PreparedStatement pstmt=null;
  String qry="insert into btm.student values(?,?,?,?)";
  try {
	Class.forName("com.mysql.jdbc.Driver");
	con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=tanmaya");
	pstmt=con.prepareStatement(qry);
	pstmt.setInt(1, id);
	pstmt.setString(2, name);
	pstmt.setString(3, dept);
	pstmt.setDouble(4, perc);
	pstmt.executeUpdate();
} catch (ClassNotFoundException | SQLException e) {
	e.printStackTrace();
}
  finally {
	if(pstmt!=null) {
		try {
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	if(con!=null) {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
  
}
}
