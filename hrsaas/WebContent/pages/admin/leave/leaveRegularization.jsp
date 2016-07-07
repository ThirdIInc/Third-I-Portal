<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="java.util.HashMap"%>
<s:form action="LeaveRegularization"  validate="true" id="paraFrm" validate="true" theme="simple">
<table class="bodyTable" width="100%">
      <tr>
  			<td width="100%" colspan="4" class="pageHeader" align="center" >Leave Regularization</td>
  	  </tr>
  	  <tr>
			<td colspan="6" width="100%" align="left" valign="middle" class="buttontr"><s:submit cssClass="pagebutton"
				action="LeaveRegularization_details" theme="simple" value=" View "
				 onclick="return callView()" /> <s:submit cssClass="pagebutton"
				action="LeaveRegularization_reset" theme="simple" value=" Reset "
				 /></td>
		</tr>
  	   <tr>
	    <td width="100%" colspan="4">&nbsp;</td>
	
	  
<tr>
			<td width="25%" colspan="1">Select Employee<font color="red">*</font>:</td>
			<td width="75%" colspan="5"><s:if test="">
			</s:if> <s:else>

				<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"
					width="18"
					onclick="javascript:callsF9(500,325,'LeaveRegularization_f9empaction.action');">
			</s:else><s:textfield name="empToken" value="%{empToken}" size="20"
				theme="simple" readonly="true" /> <s:textfield name="empName"
				size="70" value="%{empName}" theme="simple" readonly="true" /><s:hidden
				name="empID" value="%{empID}" id="empID" theme="simple" /></td>


		</tr>


		<tr>
			<td  width="25%" >Center:</td>
			<td width="25%" ><s:textfield name="empCenter"
				value="%{empCenter}" size="25" theme="simple" readonly="true" /><td width="25%"  />Rank:</td>
				<td width="25%" ><s:textfield name="empRank"
				value="%{empRank}" size="25" theme="simple" readonly="true" /></td>
		</tr>
		
			<tr>
			<td  width="25%" >From Date <font color="red">*</font>:</td>
			<td width="25%" ><s:textfield name="leaveFrom" size="25"
				value="%{leaveFrom}" theme="simple" readonly="true" /><s:a
				href="javascript:NewCal('leaveFrom','DDMMYYYY');">
				<img src="../pages/images/cal.gif"  class="iconImage" height="16" align="absmiddle" width="16">
			</s:a></td>
			<td  width="25%" >To Date <font color="red">*</font>:</td>

			<td width="25%" ><s:textfield name="leaveTo" value="%{leaveTo}"
				theme="simple" size="20" readonly="true"/><s:a
				href="javascript:NewCal('leaveTo','DDMMYYYY');">
				<img src="../pages/images/cal.gif"  class="iconImage" height="16" align="absmiddle"
					width="16">
			</s:a></td>
		</tr>
		
		<tr>
			<td >&nbsp;</td>
		</tr>

		
		
</table>

	<s:if test="%{isFromEdit}">
	<table class="bodyTable" width="100%" border="0">
				<tr>
					<td colspan="8">&nbsp;</td>
				</tr>
				
				<tr>
					<td height="15" width="5%" class="headercell">Sr No.</td>
					<td height="15" width="20%" class="headercell">Leave Name</td>
					<td height="15" width="10%" class="headercell">Date</td>
					<td height="15" width="10%" class="headercell">Amount</td>
					
					<td height="15" width="7%" class="headercell">Check Box</td>
				</tr>
				
				<%
						try {
						System.out
								.println("----------------------------------value of=");
						String[][] centerChk = (String[][]) request
								.getAttribute("result");
						System.out
								.println("----------------------------------value of="
								+ centerChk.length);
						
						HashMap afdata=(HashMap)request.getAttribute("data");
						
					 
						for (int i = 0, k = 0; k < centerChk.length; k++, i++) {
							System.out
							.println("----------------------------------value of flag===================================== ="
									+ centerChk[i][3]);
							
							String audFlag=(String)afdata.get(""+i);  
							
							System.out.println("values o faudFlag==========="+audFlag);  
				%>
				
					<tr>
					<td class="bodyCell" width="5%"><%=i + 1%></td>

					<s:hidden name="leaveCode"
						value="<%= String.valueOf(centerChk[k][0]) %>" />
					<td class="bodyCell" width="20%"><%=String.valueOf(centerChk[k][1])%></td>
					
					<td class="bodyCell" width="10%"><input name="date" value="<%=String.valueOf(centerChk[k][2])%>"/></td>
					
					<td class="bodyCell" width="10%"><input type="text" 
						name="amount"  readonly="readonly" 
						value="<%= String.valueOf(centerChk[k][3]) %>" /></td>
					
					    <td class="bodyCell" width="15%" align="center"><input type="checkbox" class="checkbox"  name="leaveChk" value="<%=audFlag.equals("Y")?"checked":""%>" onclick="callChk(<%=i%>)"   />
	   		 <input type="hidden" name="pmChkFlag" id="<%=i%>" value="<%=audFlag.equals("AA")?"N":audFlag%>" />
					</tr>
				
				<%
						}
						
						
						
						request.removeAttribute("chkFlag");
							} catch (Exception e) {
						e.printStackTrace();
							}
				%>

				<tr>
					<td width="100%" colspan="6">&nbsp;</td>
				</tr>
				
				<s:if test="flag">
				<tr>
			
			<td></td>
			<td colspan="2" align="right" >Total Amount:</td>
			<td ><s:textfield name="totalAmt" readonly="true" theme="simple" /></td>
			
			<td></td>
		</tr>
		
		<tr>
			<td width="100%" colspan="6">&nbsp;</td>
		</tr>
		
		<tr>
			<td colspan="6" align="center"><s:submit cssClass="pagebutton"
				 theme="simple" value=" Regularize "
				 onclick="return callMov();" /></td>
		</tr>
		</s:if>
	</table>
	</s:if>




<s:hidden name="checkboxSel" />

</s:form>		

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>	

<script>

function callView()
{  
	var employee=document.getElementById('empID').value;
	var from=document.getElementById('leaveFrom').value;
	var to=document.getElementById('leaveTo').value;
		
	if(employee=="")
	{
		alert("Please Select Employee");
		return false; 
	}
	
	if(from=="")
	{
		alert("Please Select Leave From");
		return false; 
	}
	
	if(to=="")
	{
		alert("Please Select Leave To");
		return false; 
	}
	
	
}

function callChk(id){

 if(document.getElementById(id).value=='Y'){
  document.getElementById(id).value='N';
  document.getElementById('checkboxSel').value='';
 }else  if(document.getElementById(id).value=='N'){
  document.getElementById(id).value='Y';
  document.getElementById('checkboxSel').value='C';
 } 
}

	

function callMov()
{
		if (document.getElementById('checkboxSel').value !="C")
		{
		alert('Please Select a CheckBox');
		return false;
		}
		else{
		document.getElementById('paraFrm').action="LeaveRegularization_regular.action";	
			}
		
}

</script>	