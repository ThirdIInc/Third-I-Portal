<%@page import="org.paradyne.lib.Utility"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:form action="TaxChallan" validate="true" id="paraFrm" theme="simple">
<s:hidden name="challanID" id="challanID" />
<s:hidden name="divId" />
<s:hidden name="month" />
<s:hidden name="year" />
<s:hidden name="divName" />
<s:hidden name="tax" />
<s:hidden name="surcharge" />
<s:hidden name="intAmt" />
<s:hidden name="othrAmt" />
<s:hidden name="eduCess" />
<s:hidden name="totalTax" />
<s:hidden name="onHold" />
<s:hidden name="bank" />
<s:hidden name="bsrCode" />
<s:hidden name="ackNo" />
<s:hidden name="bookEntry" />
<s:hidden name="paymentDate" />
<s:hidden name="chequeNo" />
<s:hidden name="chequeDate" />
<s:hidden name="challanDate" />
<s:hidden name="surchargePercen" id="surchargePercen"/>
<s:hidden name="eduCessPercen" id="eduCessPercen"/>
<s:hidden name="rebateLimit" id="rebateLimit"/>
<s:hidden name="myPage" id="myPage" />
<s:hidden name="f9AddEmpFlag"/>
<s:hidden name="tempTotalTax" id="tempTotalTax" />
<s:hidden name="tempTax" id="tempTax" />
<s:hidden name="tempSur" id="tempSur" />
<s:hidden name="tempEdu" id="tempEdu" />

<table width="100%" border="0" align="center" cellpadding="2" cellspacing="1" class="formbg"><!--main table  -->
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Tax
					Challan </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">

				<tr>
					<td><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%">
					<div align="right"><font color="red">*</font>Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
			<td>
				<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
					<tr>
						<td colspan="2" class="formhead"><strong
							class="forminnerhead">Search Employee</strong>  
						</td>
					<tr>
						<td width="10%" nowrap="nowrap">Select Employee
						</td>
						<td colspan="2" nowrap="nowrap">
							<s:hidden name="searchForEmpId"/>
							<s:textfield name="searchForEmpToken" size="10" readonly="true"/>
							<s:textfield name="searchForEmpName" size="50" readonly="true"/><img id="ctrlHide" src="../pages/images/search2.gif"
								class="iconImage" height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'TaxChallan_f9SearchEmployee.action');">
						</td>
					</tr>
					</tr>
				</table>
			</td>
		</tr>		
		
		<tr>
			<td>
				<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
					<tr>
						<td colspan="8" class="formhead"><strong
							class="forminnerhead">Add Employee</strong>  
						</td>
					</tr>
					<tr>
						<td colspan="8">
							<table width="100%" border="0" cellpadding="2" cellspacing="2">
								<tr>
									<td width="5%" nowrap="nowrap">Select Employee<font color="red">*</font>
									</td>
									<td width="45%" nowrap="nowrap">
										<s:hidden name="addEmpId"/>
										<s:textfield name="addEmpToken" size="10" readonly="true"/>
										<s:textfield name="addEmpName" size="50" readonly="true"/>
									</td>	
									<td colspan="6" nowrap="nowrap" id="dispAddEmpF9">	
										<img id="ctrlHide" src="../pages/images/search2.gif"
											class="iconImage" height="18" align="absmiddle" width="18"
											onclick="javascript:callsF9(500,325,'TaxChallan_f9AddEmployee.action');">
									</td>
								</tr>
							</table>
						</td>
					</tr>		
					<tr>
						<td width="5%" nowrap="nowrap">Please Enter Total Tax<font color="red">*</font></td>
						<td width="10%" nowrap="nowrap">
							<s:textfield name="enterTotalTax" size="10" onkeypress="return numbersOnly();"
								id="enterTotalTax" onkeyup="challanCalculation()"/>
						</td>
						<td width="2%" nowrap="nowrap">
							Tds
						</td>
						<td width="6%" nowrap="nowrap">
							<s:textfield name="addedTds" id="addedTds" size="10" readonly="true"/>
						</td>
						<td width="5%" nowrap="nowrap">
							Surcharge
						</td>
						<td width="6%" nowrap="nowrap">
							<s:textfield name="addedSurcharge" id="addedSurcharge" size="10" readonly="true"/>
						</td>
						<td width="5%" nowrap="nowrap">
							Education Cess
						</td>
						<td width="6%" nowrap="nowrap">
							<s:textfield name="addedEduCess" id="addedEduCess" size="10" readonly="true"/>
						</td>
					</tr>
					<tr>
						<td colspan="8" align="center">
							<input type="button" class="save" theme="simple" value="Save"
								onclick="saveValidate()" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		
			<tr>
				<td>
					<table width="100%" border="0" cellpadding="2" cellspacing="2">
						<td colspan="1"><b><label class="set" id="taxEmpWiseTaxDtls"
						name="taxEmpWiseTaxDtls" ondblclick="callShowDiv(this);"><%=label.get("taxEmpWiseTaxDtls")%></label></b>
						</td>
						<s:if test="noData"></s:if>
						<s:else>	
						<td colspan="3" align="right">
						<% int totalPage = 0; int pageNo = 0; %>
							<b>Page:</b>
							<%	 totalPage = (Integer) request.getAttribute("totalPage");
								 pageNo = (Integer) request.getAttribute("pageNo");
							%>
							<a href="#" onclick="callPage('1', 'F', '<%=totalPage%>', 'TaxChallan_callPage.action');">
								<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
							</a>&nbsp;
							<a href="#" onclick="callPage('P', 'P', '<%=totalPage%>', 'TaxChallan_callPage.action');" >
								<img title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
							</a>
							<input type="text" name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'TaxChallan_callPage.action');return numbersOnly();" /> of <%=totalPage%>
							<a href="#" onclick="callPage('N', 'N', '<%=totalPage%>', 'TaxChallan_callPage.action')" >
								<img title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
							</a>&nbsp;
							<a href="#" onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'TaxChallan_callPage.action');" >
								<img title="Last Page" src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
							</a>						
						</td>
						</s:else>
					</table>
				</td>
				
			</tr>
			<tr>
				<td>						

				<table width="100%" border="0" cellpadding="2" cellspacing="2"
					class="formbg">
					<tr>
						<td>

						<table width="100%" border="0" align="center" cellpadding="2"
							cellspacing="2">
							<tr>
								<td width="10%" nowrap="nowrap" valign="top" class="formth"><label
									class="set" id="employee.id" name="employee.id"
									ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></td>
								<td width="15%" nowrap="nowrap" valign="top" class="formth"><label
									class="set" id="taxEmpName" name="taxEmpName"
									ondblclick="callShowDiv(this);"><%=label.get("taxEmpName")%></label></td>
								<td width="15%" nowrap="nowrap" valign="top" class="formth"><label
									class="set" id="taxTds1" name="taxTds"
									ondblclick="callShowDiv(this);"><%=label.get("taxTds")%></label></td>
								<td width="15%" nowrap="nowrap" valign="top" class="formth"><label
									class="set" id="taxSurChrg1" name="taxSurChrg"
									ondblclick="callShowDiv(this);"><%=label.get("taxSurChrg")%></label></td>
								<td width="15%" nowrap="nowrap" valign="top" class="formth"><label
									class="set" id="taxEduCess1" name="taxEduCess"
									ondblclick="callShowDiv(this);"><%=label.get("taxEduCess")%></label></td>
								<td width="15%" nowrap="nowrap" valign="top" class="formth"><label
									class="set" id="taxTotTax1" name="taxTotTax"
									ondblclick="callShowDiv(this);"><%=label.get("taxTotTax")%></label></td>
								<td class="formth" nowrap="nowrap">Edit | Delete</td>	


							</tr>
							<s:if test="noData">
								<tr>
									<td width="100%" colspan="6" align="center"><font
										color="red">There is no data to display</font></td>
								</tr>
							</s:if>
							<%
								int i = 0;
								int s = 1;
							%>
							<%!int p = 0, t = 0;%>
							<s:iterator value="challanList">
							<%  int highlightRec = (Integer) request.getAttribute("highlightRec"); 
								System.out.println("highlightRec==="+highlightRec);
								System.out.println("iiii==="+i);
								if(highlightRec == i){%>
									<tr class="sortableTD" bgcolor="#FF8383">
								
								<% }else{%>
									<tr class="sortableTD">
								<% }%>
									<td class="sortableTD" width="10%"><s:hidden
										name="challanCode" /><input type="hidden"
										 name="empId" id="empId<%=i%>"
										value="<s:property value="empId" />"/><s:property
										value="empToken" /><input type="hidden"
										 name="empToken" id="empToken<%=i%>"
										value="<s:property value="empToken" />"/></td>
									<td class="sortableTD" width="25%"><s:property
										value="empName" /><input type="hidden"
										 name="empName" id="empName<%=i%>"
										value="<s:property value="empName" />"/></td>
									<td class="sortableTD" width="15%"><s:property
										value="challanTax" /><input type="hidden"
										size="10" name="challanTax" id="challanTax<%=i%>"
										 readonly="true"
										value="<s:property value="challanTax" />" /></td>
									<td class="sortableTD" width="15%"><s:property
										value="challanSurcharge" /><input type="hidden"
										size="10" name="challanSurcharge" id="challanSurcharge<%=i%>"
										value="<s:property value="challanSurcharge" />"
										 readonly="true" /></td>
									<td class="sortableTD" width="15%"><s:property
										value="challanEduCess" /><input type="hidden"
										name="challanEduCess" id="challanEduCess<%=i%>"
										value="<s:property value="challanEduCess" />"
										 readonly="true"
										 /></td>
									<td class="sortableTD" width="15%"><s:property
										value="challanTotTax" /><input type="hidden"
										size="10" name="challanTotTax" id="challanTotTax<%=i%>"
										value="<s:property value="challanTotTax" />" />
									</td>
									<td width="15%" class="sortableTD" align="center" nowrap>
											<input type="button" class="rowEdit"
											onclick="callForEdit(<%=i%>)" />
										|
											<input type="button" class="rowDelete"
											onclick="callDelete(<%=i%>)" />
										
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


						</table>


						</td>
					</tr>

				</table>

				</td>
			</tr>
		
		<tr>
			<td colspan="4" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				</tr>
			</table>
			</td>
		</tr>
</table><!--end of main table  -->		
<%
	int l = 0;
%>
<%!int incomeList = 0;%>
 <s:iterator status="stat" value="valuesList">
	 <s:hidden name="hidListEmpId" id="<%="hidListEmpId"+l%>" />
	 <s:hidden name="hidListTdsIncome" id="<%="hidListTdsIncome"+l%>" />
	 <%
		l++;
	%>
 </s:iterator>
 <%
 incomeList = l;
	l = 0;
 %>
</s:form>

<script>
function backFun(){

	document.getElementById('tempTotalTax').value ="0"; 
	document.getElementById('tempSur').value = "0";
	document.getElementById('tempEdu').value ="0";
	document.getElementById('tempTax').value = "0";

	document.getElementById("paraFrm").action="TaxChallan_backEmpDtls.action";
  	document.getElementById("paraFrm").target="main";
   	document.getElementById("paraFrm").submit();
}

function challanCalculation(){
try{
	var totalrow = <%=incomeList%> ;
	var tds = 0;
	var surCharge = 0;
	var edu_cess = 0;
	var chkAmount=0;
		
	var surchargePercen = document.getElementById("surchargePercen").value;
	var eduCessPercen = document.getElementById("eduCessPercen").value;
	var rebateLimit = document.getElementById("rebateLimit").value;
	
	var enteredTotalTax=document.getElementById('enterTotalTax').value;
	
	var selectedEmp = document.getElementById('paraFrm_addEmpId').value;
	var empTotalIncome = 0;	
	//alert("selectedEmp===="+selectedEmp);
	for(var row=0;row<totalrow;row++){
		var hidListEmpId=document.getElementById('hidListEmpId'+row).value;
		if(eval(hidListEmpId) == eval(selectedEmp)){
			empTotalIncome = document.getElementById('hidListTdsIncome'+row).value;
		} //end of if
	}//end of totalrow loop
	//alert("empTotalIncome===="+empTotalIncome);
	//alert("rebateLimit===="+rebateLimit);
		if(eval(empTotalIncome) > eval(rebateLimit)){
			//alert("1");
			tds = parseFloat(enteredTotalTax)/((1+parseFloat(eduCessPercen/100)) + (parseFloat(surchargePercen/100)));
			surCharge = tds*(parseFloat(surchargePercen/100));
			edu_cess = enteredTotalTax - tds - surCharge;
		}//end of if
		else{
			//alert("2");
			//alert("eduCessPercen==="+eduCessPercen);
			tds = enteredTotalTax/(1+parseFloat(eduCessPercen/100));
			surCharge = 0;
			edu_cess = enteredTotalTax - tds;
			//alert("edu_cess=="+edu_cess);
		} //end of else
		
		chkAmount = Math.round(tds) + Math.round(surCharge) + Math.round(edu_cess);
		chkAmount = enteredTotalTax - chkAmount;
		
		document.getElementById('addedTds').value = parseFloat(Math.round(tds) + chkAmount);
		document.getElementById('addedSurcharge').value = parseFloat(Math.round(surCharge));
		document.getElementById('addedEduCess').value = parseFloat(Math.round(edu_cess));
		}catch(e){
	alert(e);
}
} //end of function

function callForEdit(id){
	document.getElementById('dispAddEmpF9').style.display='none';
	document.getElementById('enterTotalTax').value = document.getElementById('challanTotTax'+id).value;
	document.getElementById('addedSurcharge').value = document.getElementById('challanSurcharge'+id).value;
	document.getElementById('addedEduCess').value = document.getElementById('challanEduCess'+id).value;
	document.getElementById('addedTds').value = document.getElementById('challanTax'+id).value;
	document.getElementById('paraFrm_addEmpId').value = document.getElementById('empId'+id).value;
	//alert("ListempId"+document.getElementById('empId'+id).value);
	document.getElementById('paraFrm_addEmpToken').value = document.getElementById('empToken'+id).value;
	document.getElementById('paraFrm_addEmpName').value = document.getElementById('empName'+id).value;
	
	document.getElementById('paraFrm_f9AddEmpFlag').value = "false";
	
	////////Keeping in temp fields===========//
	document.getElementById('tempTotalTax').value = document.getElementById('challanTotTax'+id).value;
	document.getElementById('tempSur').value = document.getElementById('challanSurcharge'+id).value;
	document.getElementById('tempEdu').value = document.getElementById('challanEduCess'+id).value;
	document.getElementById('tempTax').value = document.getElementById('challanTax'+id).value;
}

function callDelete(id){
try{
	var con=confirm('Do you want to delete the employee?');
	 if(con){
	    document.getElementById('paraFrm_totalTax').value = eval(document.getElementById('paraFrm_totalTax').value) 
						- eval(document.getElementById('challanTotTax'+id).value);
		document.getElementById('paraFrm_tax').value = eval(document.getElementById('paraFrm_tax').value) 
							- eval(document.getElementById('challanTax'+id).value);		
		document.getElementById('paraFrm_surcharge').value = eval(document.getElementById('paraFrm_surcharge').value) 
							- eval(document.getElementById('challanSurcharge'+id).value);
		document.getElementById('paraFrm_eduCess').value = eval(document.getElementById('paraFrm_eduCess').value) 
							- eval(document.getElementById('challanEduCess'+id).value);	
		
		var challanId = document.getElementById('challanID').value;
		var delEmpId = document.getElementById('empId'+id).value;
		
		document.getElementById("paraFrm").action='TaxChallan_deleteEmp.action?delEmpId='+delEmpId+'&challanId='+challanId;
	  	document.getElementById("paraFrm").target="main";
	   	document.getElementById("paraFrm").submit();	
     } 
     else{
       return false;
     }
     }catch(e){alert(e)}
     return true;
}

function saveValidate(){
try{
	if(document.getElementById('paraFrm_addEmpId').value == ""){
		alert("Please select employee to add or edit");
		return false;
	} //end of if
	if(document.getElementById('enterTotalTax').value == ""){
		alert("Please enter total tax");
		return false;
	} //end of if
	
	document.getElementById('paraFrm_totalTax').value = eval(document.getElementById('paraFrm_totalTax').value) 
						- eval(document.getElementById('tempTotalTax').value);
	document.getElementById('paraFrm_totalTax').value = eval(document.getElementById('paraFrm_totalTax').value) 
							+ eval(document.getElementById('enterTotalTax').value);
	//alert("tt=="+document.getElementById('paraFrm_totalTax').value);
	document.getElementById('paraFrm_tax').value = eval(document.getElementById('paraFrm_tax').value) 
						- eval(document.getElementById('tempTax').value);
	document.getElementById('paraFrm_tax').value = eval(document.getElementById('paraFrm_tax').value) 
							+ eval(document.getElementById('addedTds').value);		
	//alert("t=="+document.getElementById('paraFrm_tax').value);
	document.getElementById('paraFrm_surcharge').value = eval(document.getElementById('paraFrm_surcharge').value) 
						- eval(document.getElementById('tempSur').value);
	document.getElementById('paraFrm_surcharge').value = eval(document.getElementById('paraFrm_surcharge').value) 
							+ eval(document.getElementById('addedSurcharge').value);
	//alert("su=="+document.getElementById('paraFrm_surcharge').value);
	document.getElementById('paraFrm_eduCess').value = eval(document.getElementById('paraFrm_eduCess').value) 
						- eval(document.getElementById('tempEdu').value);
	document.getElementById('paraFrm_eduCess').value = eval(document.getElementById('paraFrm_eduCess').value) 
							+ eval(document.getElementById('addedEduCess').value);																		
	//alert("ed=="+document.getElementById('paraFrm_eduCess').value);
	document.getElementById('paraFrm_searchForEmpId').value="";
	document.getElementById('paraFrm_searchForEmpToken').value="";
	document.getElementById('paraFrm_searchForEmpName').value="";
	document.getElementById("paraFrm").action="TaxChallan_addEmpSave.action";
  	document.getElementById("paraFrm").target="main";
   	document.getElementById("paraFrm").submit();
	}catch(e){
	alert(e);
}
} //end of saveValidate function

</script>
