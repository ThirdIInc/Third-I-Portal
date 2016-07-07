<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript"
	src="../pages/common/calendar/calendar/myAjax.js"></script>
	
	<link rel="stylesheet" type="text/css" href="../pages/common/calendar/assets/calendar.css" />
	<%
								String[][] twoDimObjArr = (String[][]) request
								.getAttribute("twoDimObjArr");
	
					
					%>
						<%
		if (twoDimObjArr != null && twoDimObjArr.length > 1) {
		%>
					<table border="0" width="150" height="100%">
								<tr>
									<td valign="top">

									<table>
										<%
										if (twoDimObjArr != null && twoDimObjArr.length > 0) {
										%>
										<tr>
											<td><a href="javascript:d.openAll();">open all</a> | <a
												href="javascript:d.closeAll();">close all</a></td>
										</tr>
										<%
										}
										%>
									</table>

									<script type="text/javascript">
		d = new dTree('d');
		</script> <%
 if (twoDimObjArr != null && twoDimObjArr.length > 0) {
 %> <script>
		d.add('<%=twoDimObjArr[0][0]%>','-1','<%=twoDimObjArr[0][2]%>');
	</script> <%
 			int i = 0;
 			for (i = 1; i < twoDimObjArr.length; i++) {
 		Object test = twoDimObjArr[i][3];
 		if (twoDimObjArr[i][3].equals("")
 				|| twoDimObjArr[i][3].equals("null")) {
 %> <script type="text/javascript">
			d.add('<%=twoDimObjArr[i][0] %>','<%=twoDimObjArr[i][1] %>','<%=twoDimObjArr[i][2] %>','');
		</script> <%
 } else {
 %> <script type="text/javascript">
		d.add('<%=twoDimObjArr[i][0] %>','<%=twoDimObjArr[i][1] %>','<%=twoDimObjArr[i][2] %>','<%=twoDimObjArr[i][3] %>','<%=twoDimObjArr[i][4] %>','<%=String.valueOf(twoDimObjArr[i][5]) %>');
	</script> <%
 			}
 			}
 		}
 %> <script type="text/javascript">
		document.write(d);
	</script>
		<%
		} else {
		%>
		<table width="100%"  align="center" >
								<tr>
									<td colspan="2" align="center"><s:hidden 
										name="plannerBean.newtaskDate" />	
									<div id="cal1Container"></div>
									</td>
								</tr>	
								<br>	
														
								<tr>	
									<td width="100%"  colspan="2"  nowrap align="left">
									<s:submit theme="simple" onclick="newTask()" value="Add Task" cssClass="token" />&nbsp;
									<s:submit theme="simple" onclick="return viewTaskList();" value="View Task" cssClass="token"/>
									</td>	
								</tr>							
								</table>
		<%}%>