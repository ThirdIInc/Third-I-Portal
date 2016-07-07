<%@ taglib prefix="s" uri="/struts-tags"%>

<link rel="StyleSheet"
	href="http://<%=request.getServerName()%>:<%=request.getServerPort()%>/hrsaas-usermanual/pages/common/dtree/dtree.css"
	type="text/css" />

<script type="text/javascript"
	src="http://<%=request.getServerName()%>:<%=request.getServerPort()%>/hrsaas-usermanual/pages/common/dtree/dtree.js"></script>
<s:form action="EmpHierarchy" id="paraFrm" validate="true"
	theme="simple">
<table border="0" width="100%">
	<tr>
		<td class="pageHeader" colspan="4">
		<center>Employee Wise Hierarchy</center>
		</td>
	</tr>
	<tr><td colspan="4">&nbsp;</td></tr>
	<tr><td colspan="4">&nbsp;</td></tr>
	<tr>
	<td width="5%">&nbsp;</td>
	<td width="20%" align="left">Select Employee:</td>
	<td width="55%" align="left" colspan="2">
	<img src="../pages/images/search.gif" class="iconImage"
				height="18" align="absmiddle" width="18"
				onclick="javascript:callsF9(500,325,'EmpHierarchy_f9action.action');" /><s:hidden name="emphr.empID" value="%{emphr.empID}"/>
	<s:textfield name="emphr.empToken" value="%{emphr.empToken}" size="10" readonly="true"/>
			<s:textfield name="emphr.empName" value="%{emphr.empName}" size="45" readonly="true"/>
			
	
	</td>
	</tr>
	<s:if test="emphr.empFlag">
	
	<tr><td colspan="4">&nbsp;</td></tr>
	<tr><td colspan="4">&nbsp;</td></tr>
	<tr><td width="20%">&nbsp;</td>
		<td width="80%" align="center" colspan="4" >
		<div class="dtree" align="left" valign="top">
		<p><a href="javascript:d.openAll();">open all</a> | <a
			href="javascript:d.closeAll();">close all</a></p>

		<script type="text/javascript">
		alert('start');
		d = new dTree('d');</script><script type="text/javascript">
		d.add(0,-1,'PIMS Employee');
		alert('end');
	</script>  
<%
 			String[][] twoDimObjArr = (String[][]) request
 			.getAttribute("twoDimObjArr");
 	int i = 0;
 	if(twoDimObjArr!=null){
 	for (i = 0; i < twoDimObjArr.length; i++) {
 		Object test = twoDimObjArr[i][2];
 		System.out.println("Length of twodim array"+twoDimObjArr.length);
 		System.out.println("First"+twoDimObjArr[i][0]);
 		System.out.println("Second"+twoDimObjArr[i][1]);
 		System.out.println("Third"+twoDimObjArr[i][2]);
 		
 		
 %> <script type="text/javascript">
		d.add('<%=twoDimObjArr[i][0] %>','<%=twoDimObjArr[i][1] %>','<%=twoDimObjArr[i][2] %>');
		
	</script> 
	<%
 	}
 }
 %><script type="text/javascript">
		document.write(d);
	</script></div>
		</td>
	</tr>
	</s:if>
</table>
</s:form>
