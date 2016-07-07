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
	String contentType = request.getAttribute("contentType").toString();
String content="";
	if (contentType != null) {
		 content = request.getAttribute("content").toString();
		if (contentType.equalsIgnoreCase("text")) {
			%>
			<div style="overflow: auto;	height: 500px;	width: 100%;">
			<% 
			out.println(content);
			%>
			</div>
			<%
		} else {

%>
	 	<iframe id="urlIframe" src="<%=content %>" height="630px" width="1000px" frameborder="0"  scrolling="no">
	 	</iframe>
<%
		}
	}
%>
<script type="text/javascript">
var contentType = '<%=contentType %>';
if(contentType == 'text'){
parent.document.getElementById("zoomId").style.display="none";
}else{
parent.document.getElementById("zoomId").style.display="";
}
</script>

</body>
</html>