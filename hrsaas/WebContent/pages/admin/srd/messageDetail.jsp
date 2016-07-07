<%@ taglib prefix="s" uri="/struts-tags"%>
	<s:form  target="_top">
	<table class="bodyTable" width="100%">
		<tr>
			<td width="100%" class="pageHeader" colspan="4" align="center">Message Detail</td>
		</tr>
		<tr>
			<td >&nbsp;</td>
    	
    	</tr>
    	
    	<%String str =(String) request.getAttribute("msg1");
    	String[] strSpilt=str.split("#");
    	System.out.println("---------------**************************************"+strSpilt.length);
    	String messag ="";
    	for (int i = 0; i < strSpilt.length; i++) {%>
    	
    	<tr><td>&nbsp;</td></tr>
		<tr>
			<td width="5%"></td>
    	<td width="50%" colspan="3"><%=strSpilt[i]%></td>
    	
    	</tr>
    	<%}	%>
    	<tr>
			<td >&nbsp;</td>
    	
    	</tr>
    	<tr>
			<td width="12%"></td>
    	<td width="50%" colspan="2"><s:property value="msg2"/></td>
    	<td width="25%"></td>
    	</tr>
    	<tr>
			<td width="12%"></td>
    	<td width="50%" colspan="2"><s:property value="msg3"/></td>
    	<td width="25%"></td>
    	</tr>
    	<tr>
			<td width="12%"></td>
    	<td width="50%" colspan="2"><s:property value="msg4"/></td>
    	<td width="25%"></td>
    	</tr>
    	<tr>
			<td width="12%"></td>
    	<td width="50%" colspan="2"><s:property value="msg5"/></td>
    	<td width="25%"></td>
    	</tr>
    	<tr>
			<td >&nbsp;</td>
    	
    	</tr>
		<tr>
		<td colspan="4" >
			<center><input type="button" class="pagebutton" value="Close" onclick="callBack()" /></center>
		 </td>
		</tr>
		
	</table>
</s:form>
	<script>
	function callBack()
	{
		window.close();
	}
	</script>