<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%

if(true){//isset($_GET['eventToDeleteId'])
	
	%>
	<script type="text/javascript">
	alert('tttttttttttttt');
	</script>
	<%
	// db query where you delete your event
	// mysql_query("delete from events where ID='".$_GET['eventToDeleteId']."'");
	
	out.println("OK");	// This value is sent back to the script. The script looks for the value "OK". This confirms that the event has been deleted.
	
}

%>
</body>
</html>