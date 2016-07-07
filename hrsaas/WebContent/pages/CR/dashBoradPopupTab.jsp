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
Object[][] tabObj=(Object[][])request.getAttribute("tableData");

System.out.println("Object Containt------"+tabObj[0][0]);
String tableHeading=(String)request.getAttribute("tableHeading");
%>
<table width="100%" height="100%" border="1" cellpadding="0"
								id="dashTab" name="innerCellTab" cellspacing="0"
								style="border-collapse: collapse; border-color: #ccc">
<tr>
<!--table Heading	-->

<td class="portalAppButtons" align="left" width="100%">
							<img src="../pages/CR/icons/analytics.png" ;	align="absmiddle" width="16" height="16"><strong> &nbsp <%=tableHeading%></strong>
							<a href="#"><img src="../pages/cssv2/icons/message24.png"; class="iconImage"	 align="right" width="16" height="16"></a>
							<a href="#"><img src="../pages/images/icons/portal/insightone.png";	class="iconImage" align="right" width="14" height="14"  style="padding: 2px";/></a></td>
						
</tr>
<tr>
<table width="100%" height="100%" border="1" cellpadding="0"
								id="dashTab" name="innerCellTab" cellspacing="0"
								style="border-collapse: collapse; border-color: #ccc">
								<%
												
												//rendering the table a=row b=cols
												for (int a = 0; a < tabObj.length; a++) {
											if (a % 2 == 0) {
								%>
								<tr valign="top" style="background-color: #E6EAE2">
									<%
									} else {
									%>
								
								<tr style="background-color: #FFFFFF">
									<%
												}
												for (int b = 0; b < tabObj[0].length; b++) {
													if (a == 0) {
									%>
									<th ALIGN="left" valign="top"
										style="padding-left: 4px; padding-right: 2px; background-color: "><%=tabObj[a][b]%></th>
									<%
									} else {
									%>

									<td ALIGN="Left"
										style="border: 1px solid #ccc; padding-left: 4px; padding-right: 2px;"><%=tabObj[a][b]%></td>
									<%
												}
												}//end icol
									%>
								</tr>
								<%
												}//end irow
												
								%>
								

								


							</table>
							</tr>
							</table>


</body>
</html>