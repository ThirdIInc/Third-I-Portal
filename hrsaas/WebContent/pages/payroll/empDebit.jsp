<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<%@page import="java.util.HashMap"%>
<%@page import="org.paradyne.lib.Utility"%>
<s:form action="EmpDebit" id="paraFrm" validate="true">
<table class="formbg" width="100%">
	        <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Employee Debit Configuration</strong></td>
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
		<s:if test="generalFlag">
		</s:if>
		<s:else>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><s:if test="viewFlag"><input type="button" class="search"
						onclick="javascript:callsF9(600,400,'EmpDebit_f9actionEmpId.action');"
						value="Search" /></s:if> 
						<s:if test="debit.insertFlag">
							<s:submit cssClass="add" action="EmpDebit_save" theme="simple"
								value="Save" onclick = "return saveValidate();"/>
						</s:if>
						<s:if test="viewFlag">
						<s:submit cssClass="reset" action="EmpDebit_reset" theme="simple"
							value="Reset" /></s:if>
						<s:if test="debit.deleteFlag">
							<s:submit cssClass="delete" action="EmpDebit_delete"
								theme="simple" value="Delete" onclick="return callDelete('paraFrm_empId')" />
						</s:if>

					</td>
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			<label></label></td>
		</tr>
		</s:else>
		<tr>
			<td colspan="3"><img src="../pages/images/recruitment/space.gif"
				width="5" height="4" /></td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<tr>
							<td colspan="5" class="formhead"><strong
								class="forminnerhead"><label  class = "set"  id="empDbt" name="empDbt" ondblclick="callShowDiv(this);"><%=label.get("empDbt")%></label></strong></td>
						</tr>
						<tr>
							<td class="formtext" width="15%"><label  class = "set"  id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> <font color="red"> * </font>:</td>
							<td class="formtext" width="45%"><s:hidden name="empId" />
							<s:if test="generalFlag">
							<s:property value="empToken" /><s:property value="empName" />
							</s:if>
							<s:else>
							<s:textfield theme="simple" name="empToken" readonly="true"
								size="8" /><s:textfield theme="simple" name="empName"
								size="30" readonly="true" />
							</s:else>	
								<s:hidden theme="simple"
								name="empDebit" />
								<s:hidden name="empstatus"></s:hidden> </td>
						</tr>

						<tr>
							<td class="formtext" width="10%"><label  class = "set"  id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
							<td class="formtext" width="45%">
							<s:if test="generalFlag"><s:property value="empCenter" /></s:if>
							<s:else>
							<s:textfield theme="simple" readonly="true" name="empCenter" size="35" />
							</s:else>
							</td>
							<td class="formtext" width="12%"><label  class = "set"  id="designation" name="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label> :</td>
							<td class="formtext" width="28%">
							<s:if test="generalFlag"><s:property value="empCenter" /></s:if>
							<s:else>
							<s:textfield theme="simple" name="empRank" readonly="true" size="25" />
							</s:else>
							</td>

						</tr>
						
					</table>
					</td>
				</tr>


			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td class="formtext">
					<table width="100%" border="0" cellpadding="1" cellspacing="1">
						<tr>
							<td class="formth"><label  class = "set"  id="sno" name="sno" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></td>
							<td class="formth"><label  class = "set"  id="salHead" name="salHead" ondblclick="callShowDiv(this);"><%=label.get("salHead")%></label></td>
							<td class="formth"><label  class = "set"  id="period" name="period" ondblclick="callShowDiv(this);"><%=label.get("period")%></label></td>
							<td class="formth"><label  class = "set"  id="amnt" name="amnt" ondblclick="callShowDiv(this);"><%=label.get("amnt")%></label></td>

							<td></td>
						</tr>
						<%
											Object[][] rows = (Object[][]) request.getAttribute("rows");
											HashMap afdata = (HashMap) request.getAttribute("data");

											int i = 0;
											int s = 1;
										%>
						<%!int p = 0, t = 0;%>
						<s:iterator value="att">
							<%
											String audFlag = (String) afdata.get(String.valueOf(rows[i][1]));
											%>
							<tr>
								<td class="border2"><%=s%></td>

								<td class="border2"><s:property value="salHead" /></td>
								<td class="border2"><s:hidden name="period"
									id="<%="period"+i%>" /> <s:property value="period" /></td>
								<td class="border2">
									<s:if test="generalFlag"><%=Utility.twoDecimals(String.valueOf(rows[i][0]))%></s:if>
									<s:else>
								<input type="text" size="6"
									name="amount" id="amount<%=i %>" onkeyup="return sum(<%=i %>)"
									onkeypress="return numbersOnly();" value="<%=Utility.twoDecimals(String.valueOf(rows[i][0]))%>"
									style="text-align: right">
									</s:else>	
								</td>

							</tr>

							<%
												i++;
												s++;
												p++;
											%>
						</s:iterator>
						<%
											t = p;
											p = 0;
										%>
						<tr>
							<td colspan="4">
							<hr>
							</td>
						</tr>
						<tr>

							<td></td>
							<td align="right" width="50%"><label  class = "set"  id="totMonth" name="totMonth" ondblclick="callShowDiv(this);"><%=label.get("totMonth")%></label>.
							<s:if test="generalFlag"><s:property value="TotalAmt" /></s:if>
							<s:else>
							<s:textfield name="TotalAmt" readonly="true" size="6"
								theme="simple"  />
							</s:else>	
							&nbsp;&nbsp;&nbsp;</td>
							<!--<td class="headerCell">Applicable</td>
				-->
							<td>
								<label  class = "set"  id="totAnnual" name="totAnnual" ondblclick="callShowDiv(this);"><%=label.get("totAnnual")%></label>.
							<s:if test="generalFlag"><s:property value="annuallAmt" /></s:if>
							<s:else>
							<s:textfield name="annuallAmt" readonly="true" size="6"
								theme="simple"  />
							</s:else>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			
		
			</td>
		</tr>
	</table>
	</td></tr></table>

</td></tr></table>
</s:form>


<script>  

function saveValidate()

{ 	var emplyee=document.getElementById('employee').innerHTML.toLowerCase();
   var emp=document.getElementById('paraFrm_empId').value;
	if(emp=="")
	{
		alert("Please Select the "+emplyee );
		return false;
	}
}
function sum(s) 
{

var totalrow = <%=t%> ;
var count=0;
var count1=0;
var count2=0;
var count3=0;
var count4=0;
	for(var row = 0;row < totalrow ;row++)
	{
	
	if(document.getElementById('period'+row).value=="Monthly"){
	
	
	var values=document.getElementById('amount'+row).value;
	if(values ==""){
		values =0;
	}
		count=eval(count)+eval(values);
		count1=eval(count1)+eval(values)*12;
	}
	
	
	else if(document.getElementById('period'+row).value=="Half Yearly"){
			var values=document.getElementById('amount'+row).value;
			if(values =="" || values=='.'){
			values =0;
			}
				values =eval(values*100/100);
				count2=eval(count2)+eval(values)*2;
	
	
	}
	
	else if(document.getElementById('period'+row).value=="Annually"){
		var values=document.getElementById('amount'+row).value;
			if(values =="" || values=='.'){
			values =0;
			}
				values =eval(values*100/100);
				count3=eval(count3)+eval(values);
	
	
		}
	else if(document.getElementById('period'+row).value=="Quarterly"){
		var values=document.getElementById('amount'+row).value;
			if(values =="" || values=='.'){
			values =0;
			}
				values =eval(values*100/100);
				count4=eval(count4)+eval(values)*4;
	
	
		}
	
		
	}
//var debit=document.getElementById('preCommAmt'+s).value;
document.getElementById('paraFrm_TotalAmt').value=count;
document.getElementById('paraFrm_annuallAmt').value=count1+count2+count3+count4;
}

function numbersonly(myfield)
	{
		var key;
		var keychar;
		if (window.event)
			key = window.event.keyCode;
		else
			return true;
		
		keychar = String.fromCharCode(key);	
		if ((("0123456789").indexOf(keychar) > -1))
			return true;	
		else {
			myfield.focus();
			return false;
		}
	}

 function callChk(id){

 if(document.getElementById(id).value=='Y'){
 alert('The Amount will be set to 0')
  document.getElementById(id).value='N';
 }else  if(document.getElementById(id).value=='N'){
  document.getElementById(id).value='Y';
 } 
}

  </script>