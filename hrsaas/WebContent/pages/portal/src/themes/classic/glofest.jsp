<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Galleria Classic Theme</title>
<script src="../../../src/jquery.min.js"></script>
<script src="../../../src/galleria.js"></script>
<style>
html,body {
	
}

.content {
	color: #eee;
	font: 14px/ 1.4 "helvetica neue", arial, sans-serif;
	width: 640px;
}

h1 {
	line-height: 1.1;
	letter-spacing: -1px;
}

a {
	color: #fff;
}

#galleria {
	height: 500px;
}

body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>


<%
Object[][] glofestImageObj = null;		
try {
	 	glofestImageObj = (Object[][]) request
		.getAttribute("glofestImageObj");
	} catch (Exception e) {
	
	}
	out.println("glofestImageObj.length         "+glofestImageObj.length);
%>


 <div id="galleria">
<%
		if (glofestImageObj != null && glofestImageObj.length > 0) {
		for (int i = 0; i < glofestImageObj.length; i++) {
%> <img alt="Glofest"
	src="../pages/images/<%=session.getAttribute("session_pool")%>/employeePortal/<%=glofestImageObj[i][0] %>" >

<%
	}
	}
%>
</div>

  
<script>
    // Load the classic theme
    Galleria.loadTheme('galleria.classic.js');
    // Initialize Galleria
    $('#galleria').galleria();
    </script>
</body>
</html>