<%@ page contentType = "text/html; charset=utf-8" %>
<%@ page import = "java.sql.DriverManager" %>
<%@ page import = "java.sql.Connection" %>
<%@ page import = "java.sql.Statement" %>
<%@ page import = "java.sql.ResultSet" %>
<%@ page import = "java.sql.SQLException" %>

<html>
<head></head>
<body>

<table width="100%" border="solid-black">
<tr>
	<td>장소</td><td>주소</td><td>카테고리</td><td>ID</td>
</tr>
<%

	Class.forName("com.mysql.jdbc.Driver");
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	try {
		String jdbcDriver = "jdbc:mysql://localhost:3306/travel?" +
							"useUnicode=true&characterEncoding=utf8";
		String dbUser = "root";
		String dbPass = "rootpw";
		
		String query = "select * from place order by ID";
		
		
		conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
		

		stmt = conn.createStatement();
		

		rs = stmt.executeQuery(query);

		while(rs.next()) {
%>
<tr onclick ='displayPlace("<%= rs.getString("name") %>","<%= rs.getString("address") %>","<%= rs.getString("category") %>","<%= rs.getInt("ID") %><")'>
	<td ><%= rs.getString("name") %></td>
	<td ><%= rs.getString("address") %></td>
	<td ><%= rs.getString("category") %></td>
	<td ><%= rs.getInt("ID") %></td>
</tr>
<%
		}
	} 
	
	catch(SQLException ex) {
		out.println(ex.getMessage());
		ex.printStackTrace();
	} finally {

		if (rs != null) try { rs.close(); } catch(SQLException ex) {}
		if (stmt != null) try { stmt.close(); } catch(SQLException ex) {}
		

		if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	}
%>
</table>
<script>
function displayPlace(i,j,k,l) {
	  document.getElementById("show").innerHTML =
		"장소: "+i+
		"<br>주소: "+j+
		"<br>카테고리: "+k+
		"<br>ID: "+l;	  
	}
</script>
</body>
</html>
