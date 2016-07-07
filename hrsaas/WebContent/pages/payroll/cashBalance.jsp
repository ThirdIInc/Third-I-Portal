<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="CashBalance" method="post" id="paraFrm" validate="true"
	theme="simple">
	<table class="formbg" width="100%">
	        <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Cash Balance</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
	
	<tr>
	<td colspan="3" width="100%">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">
	    <tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%">
				<s:if test="cashBal.insertFlag">
				  <s:if test="cashBal.updateFlag">
							<s:submit cssClass="add" action="CashBalance_save"
								theme="simple" value="     Save " onclick="return callAdd();" />
					</s:if></s:if>
					
		
                  <s:submit cssClass="reset" action="CashBalance_reset"
								theme="simple" value="     Reset " />
				
					  <s:if test="cashBal.generalFlag"></s:if><s:else>
                   <input type="button" class="search" value="     Search " 
					onclick="javascript:callsF9(500,325,'CashBalance_f9action.action')"/>
				
					</s:else>
					
					
					</td>
					<td width="22%">
					<div align="right"><span class="style2"><font color="red">*</font></span> Indicates
					Required</div>
					</td>
				
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3"><img
				src="../pages/common/css/default/images/space.gif" width="5"
				height="4" /></td>
		</tr>
		<tr>
			<td colspan="3">

			<table width="100%" border="0" cellpadding="0" cellspacing="0" bordercolor="red"
				class="formbg">
				<tr>
					<td>

					<table width="98%" border="0" align="center" cellpadding="0" bordercolor="red"
						cellspacing="2">
						
						  <tr>
                    <td width="12%" height="22" class="formtext"><label  class = "set"  id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> :<font color="red">*</font></td>
                    <td height="22" colspan="4"><label>
                    <s:hidden name="empId" theme="simple" />
                     <s:textfield name="empToken" theme="simple"  size="10" readonly="true" />
                    <s:textfield readonly="true" label="%{getText('empName')}" name="empName" size="85" />                         
                    </label><label></label></td>
                  </tr>
                  
                  
                  <tr>
                    <td height="22" class="formtext"><label  class = "set"  id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
                    <td width="22%" height="22"><s:textfield label="%{getText('center')}" name="empBrn" size="28"	readonly="true" />
                     </td>
                    <td width="4%" height="22">&nbsp;</td>
                    <td width="12%" class="formtext"><label  class = "set"  id="department" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> : </td>
                    <td width="28%" >&nbsp;&nbsp;<s:textfield label="%{getText('empDept')}" name="empDept" size="27" readonly="true" /></td>
                  </tr>
						
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
          <td colspan="3"><img src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
        </tr>
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="100%">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
						 	<td height="22" width="100%" class="text_head"><strong
								class="forminnerhead">&nbsp;&nbsp;<label  class = "set"  id="cashBal" name="cashBal" ondblclick="callShowDiv(this);"><%=label.get("cashBal")%></label></strong></td>
						</tr>

						<tr>
							<td>
							<table width="100%" border="0" cellpadding="1" cellspacing="1"
								class="sortable">
								<tr>
									<td width="33%" valign="top" class="formth"><label  class = "set"  id="creType" name="creType" ondblclick="callShowDiv(this);"><%=label.get("creType")%></label></td>
									<td width="33%" valign="top" class="formth"><label  class = "set"  id="cashAmount" name="cashAmount" ondblclick="callShowDiv(this);"><%=label.get("cashAmount")%></label>
									</td>
									<td width="33%" valign="top" class="formth"><label  class = "set"  id="holdAmount" name="holdAmount" ondblclick="callShowDiv(this);"><%=label.get("holdAmount")%></label>
									</td>
								
								</tr>
								<% int i=0;
								int s = 1;
								%>
									<%!int p = 0, t = 0;%>
								<s:iterator value="balanceList" status="stat">
									<tr>
										<s:hidden name="creditCode" value="%{creditCode}" />
										<td class="bodyCell" width="33%" nowrap="nowrap"><s:property
											value="creditType"/></td>
										<td class="bodyCell" width="33%"><input type="text"
											name="balanceAmt" value='<s:property value="balanceAmt"/>'   theme="simple" maxlength="5" onkeypress="return numbersWithDot();" id="balanceAmt<%=i %>" onkeyup="return sum(<%=i %>)" /></td>
										<td class="bodyCell" width="33%" nowrap="nowrap">
										<input type="text"
											name="onHoldAmt" value='<s:property value="onHoldAmt"/>' readonly="true"/></td>
										
									
											
											
										
									
									</tr>
									
									<% i++;
									   p++;
									   s++;
									%>
								</s:iterator>
								<%
											t = p;
											p = 0;
										%>
								
								
							</table>
							<hr>
							</td>
						</tr>
						
						<tr>

							
						<td><label  class = "set"  id="totAmount" name="totAmount" ondblclick="callShowDiv(this);"><%=label.get("totAmount")%></label>:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:textfield name="totAmt" readonly="true" size="20"
								theme="simple"  /></td></tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	</td></tr></table>
</s:form>
<script>
  
	
	
	function callAdd(){
		
		var pre1 = document.getElementById('paraFrm_empName').value;
		
		var employee=document.getElementById('employee').innerHTML.toLowerCase();
		if(pre1==""){
		alert("Please select the "+employee);
			return false;
		}
		
		
	}
	

	function sum(s) 
{

var totalrow = <%=t%> ;
var count=0;

	for(var row = 0;row < totalrow ;row++)
	{
	var values=document.getElementById('balanceAmt'+row).value;
	if(values ==""){
		values =0;
	}
		count=eval(count)+eval(values);
		
	}
document.getElementById('paraFrm_totAmt').value=count;

}
	
	
  	</script>
