<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="org.paradyne.lib.Utility"%>
<%
Object[][] d1Menu = (Object[][]) request.getAttribute("d1MenuData");
String userType =(String)request.getAttribute("userType");
int noOfDashboard = (Integer)request.getAttribute("noOfDashboard");

%>
<s:form action="MypageProcessManagerAlerts" method="post"
	validate="true" id="paraFrm" theme="simple">
	<s:hidden name="accountNo"></s:hidden>
	<s:hidden name="dashBoardID"></s:hidden>
	<s:hidden name="accountID"></s:hidden>
	<s:hidden name="accountName"></s:hidden>
	<s:hidden id="screenWidth" name="screenWidth"></s:hidden >
	<div style="float: left; width: 98%">
	<div style="float: left; width: 100%">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td valign="bottom" class="txt" width="100%">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="pageHeader">
				<tr>
					<td style="padding: 3px;"><strong class="text_head"><img
						src="../pages/CR/icons/analytics.png" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">My
					Analytics </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<table width="80%" border="0" cellspacing="0" cellpadding="0"
		height="100%">			
		<%
				try {	
					if(!userType.equals("E") || noOfDashboard!=1){
				if (d1Menu != null && d1Menu.length > 0) {
					int firstchk = 0;
					int secondchk = 0;
					int cnt=0;
					int check=1;
					for (int i = 0; i < d1Menu.length; i++) {
						if (cnt == firstchk) {
							secondchk = firstchk + 1;
						
		%>
		
		<tr>
		<%
			}
			%>

			<td width="8%" valign="top" style="padding-top: 5px" colspan="1">			
				<div align="center"><img src="../pages/CR/icons/<%=String.valueOf(d1Menu[i][4])%>" 
				height="30" align="absmiddle" /></div></td>
			<td width="42%" valign="middle" style="padding-top: 5px" colspan="5"><a class="servicelink"
				href="javascript:void(0);" title="<%=Utility.checkNull(String.valueOf(d1Menu[i][1]))%>" onclick="callAction('<%=Utility.checkNull(String.valueOf(d1Menu[i][3]))%>','<%=Utility.checkNull(String.valueOf(d1Menu[i][0]))%>','<%=Utility.checkNull(String.valueOf(d1Menu[i][1]))%>');"><%=Utility.checkNull(String.valueOf(d1Menu[i][1]))%></a><br>
				<s:hidden name="<%=Utility.checkNull(String.valueOf(d1Menu[i][2]))%>" value="<%=Utility.checkNull(String.valueOf(d1Menu[i][2]))%>" id='<%="id"+check%>'></s:hidden>
			<span class="smalltext"><%=Utility.checkNull(String.valueOf(d1Menu[i][1]))%></span>	
			</td>
	<%
							if (cnt == secondchk) {

							firstchk = secondchk + 1;
			%>			
		</tr>
		<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
		<%
							}
					cnt++;
					check++;
					}
					%> 
					<input type="hidden" name="otherLengthVar" id="otherLengthVar"
							value="<%=check%>" />
					<%
				}
				}
					
					
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		%>		
	</table>
	</div>
	</div>
	
</s:form>
<script>
function callAction(action,id, dashName){   
	try{
	
	var listCount = document.getElementById('otherLengthVar').value;
	for(var i = 1; i < listCount; i++){
	if(action==""){
		alert("Under Construction");
		return false;
	}else {
		document.getElementById('paraFrm').target = "_self";
	 	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>'+action+'?dashBoardID='+id+'&dashBoardName='+dashName;
		document.getElementById('paraFrm').submit();
		return true;
		}
	
	}
	}
	catch(e){		 
	}	 	
		return true;
}

</script>