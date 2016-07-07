<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@include file="/pages/ApplicationStudio/configAuth/authorizationChecking.jsp" %>


 <script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/arrearsAjax.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/superTables.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/syncscroll.js"></script>
<div id="msgDiv"
	style='position: absolute; z-index: 3; background-color: red; 100 px; height: 50px; visibility: hidden; top: 70px; left: 250px;'></div>

<%!int k = 1, c, d;%>
<%
int count = 0;
%>

<s:form action="PromotionArrears" name="PromotionArrears" id="paraFrm"
	theme="simple">
	<s:hidden name="arrCode" />
	<s:hidden name="proccessDate" />   
	
	<s:hidden name="status" />
	<s:hidden name="savedFlag" />
	<s:hidden name="payinSalFlag" />
	<s:hidden name="deductTaxFlag" />

	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Promotion Arrears
					  </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		
		
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /> </td>
					<td width="22%" align="right">
					<div align="right"><font color="red">*</font> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr id="ctrlShow">
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td width="100%" colspan="4"><strong>Select Filters</strong></td>

				</tr>
				
				<tr>
					<s:hidden name="divCode" />
					<td width="20%"><label class="set" id="division"
						name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label> :<font
						color="red"> *</font> </td>
					<td width="30%" colspan="1" ><s:textfield name="divName" readonly="true"   maxlength="50"
						size="25" /> 
						
							<s:if test="arrCode==''">
							<img src="../pages/images/recruitment/search2.gif"
							class="iconImage" height="16" align="absmiddle" width="16"
							onclick="javascript:callDropdown('paraFrm_divName',200,250,'PromotionArrears_f9Div.action',event,'false','no','right')"/>
						 
							
							<!--<img src="../pages/images/recruitment/search2.gif"
							class="iconImage" height="16" align="absmiddle" width="16"
							onclick="callsF9(500,325,'PromotionArrears_f9Div.action');">
						--></s:if>
					</td>
					<s:hidden name="brnCode" />
					<td width="20%"><label class="set" id="branch" name="branch"
						ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
					<td width="30%" colspan="1" ><s:textfield name="brnName" readonly="true" maxlength="50"
						size="25" /> <s:if test="savedFlag"></s:if><s:else>
						<s:if test="arrCode==''">
						<!--<img src="../pages/images/recruitment/search2.gif"
							class="iconImage" height="16" align="absmiddle" width="16"
							onclick="callsF9(500,325,'PromotionArrears_f9Branch.action');">
						--><img src="../pages/images/recruitment/search2.gif"
							class="iconImage" height="16" align="absmiddle" width="16"
							onclick="javascript:callDropdown('paraFrm_brnName',200,250,'PromotionArrears_f9Branch.action',event,'false','no','right')"/>
						 
						
						</s:if>	
							</s:else>
					</td>
				</tr>
				
				<tr>
					<td width="20%"><label class="set"
						id="calculateFrom" name="calculateFrom" ondblclick="callShowDiv(this);"><%=label.get("calculateFrom")%></label> :<font
						color="red"> *</font> </td>
					<td width="30%"><s:select name="monthName" headerKey=""
						headerValue="--Select--" onchange="callMonth();"
						list="#{'1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" />
						<s:textfield name="eArrYear" size="7"
						cssStyle="text-align: right;" onkeypress="return numbersOnly()"
						maxlength="4" />
					<td width="20%"><label class="set"
						id="calculateTo" name="calculateTo" ondblclick="callShowDiv(this);"><%=label.get("calculateTo")%></label> :<font
						color="red"> *</font> </td>
					<td width="30%"><s:select name="arrToMonth" headerKey=""
						headerValue="--Select--" onchange="callMonth();"
						list="#{'1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" />
						<s:textfield name="arrToYear" size="7"
						cssStyle="text-align: right;" onkeypress="return numbersOnly()"
						maxlength="4" /></td>
					
				</tr>
				
				<tr>
					<s:hidden name="paidArrMonth" />
					<td width="20%"><label class="set"
						id="paid.month" name="paid.month" ondblclick="callShowDiv(this);"><%=label.get("paid.month")%></label> :<font
						color="red"> *</font> </td>
					<td width="30%"><s:select name="arrPaidMonth" headerKey=""
						headerValue="--Select--" onchange="callPaidMonth();"
						list="#{'1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" /></td>
					<td width="20%"><label class="set" id="paid.year"
						name="paid.year" ondblclick="callShowDiv(this);"><%=label.get("paid.year")%></label> :<font
						color="red"> *</font>  </td>
					<td width="30%"><s:textfield name="arrPaidYear" size="7"
						cssStyle="text-align: right;" onkeypress="return numbersOnly()"
						maxlength="4" /></td>
					
				</tr>
				<tr>
					<td width="20%" ><label class="set" id="pay.salary" name="pay.salary"
						ondblclick="callShowDiv(this);"><%=label.get("pay.salary")%></label> :</td>
					<td width="30%"><s:checkbox  name="payinSalCheckBox"   /></td>
					<td width="20%"><label class="set" id="deduct.tax" name="deduct.tax"
						ondblclick="callShowDiv(this);"><%=label.get("deduct.tax")%></label> :</td>
					<td width="30%"><s:checkbox  name="deductTaxCheckBox" onclick="callCheckbox();" /></td>
				</tr>
				
				
				
			</table>
			</td>
		</tr>
		<s:if test="savedFlag">
		<tr>
			<td width="100%">
			<table width="100%" cellpadding="1"  cellspacing="2" class="formbg">
			
			<tr>
					<td width="20%" ><label id="record.count" name="record.count"
						ondblclick="callShowDiv(this);"><%=label.get("record.count")%></label>
					:</td>
					<td width="30%" id="ctrlShow"><s:textfield  name="totalEmpCount"  readonly="true" size="25" theme="simple" /></td>
					<td width="20%"><label id="total.netAmount" name="total.netAmount"
						ondblclick="callShowDiv(this);"><%=label.get("total.netAmount")%></label>
					:</td>
					
					<td width="30%" id="ctrlShow"><s:textfield id="totalNetAmount1" name="totalNetAmount1" 
						readonly="true" size="25" cssStyle="background-color: #F2F2F2;border :none " /></td>
					
				</tr>
			</table>
			</td>
			</tr>
		<tr>
			<td width="100%">
			<table width="100%" cellpadding="1" cellspacing="2" class="formbg">

				<tr>
					<td width="100%" colspan="4"><strong><label id="add.employee" name="add.employee"
						ondblclick="callShowDiv(this);"><%=label.get("add.employee")%></label></strong></td>

				</tr>
				<tr>
					<td width="20%">Select <label id="employee" name="employee"
						ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:</td>
					<td width="35%"><s:hidden name="empProcessId"/><s:textfield
						name="empProcessToken" readonly="true" size="10" theme="simple" /><s:textfield
						name="empProcessName" readonly="true" size="25" theme="simple" /></td>
					<td width="20%"><img src="../pages/images/recruitment/search2.gif"
							class="iconImage" height="16" align="absmiddle" width="16"
							onclick="callsF9(500,325,'PromotionArrears_f9Branch.action');">
							<input type="button" value="Process Employee" />
					</td>

				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
			<table width="100%" cellpadding="1" cellspacing="2" class="formbg">

				<tr>
					<td width="100%" colspan="4"><strong><label id="update.employee" name="update.employee"
						ondblclick="callShowDiv(this);"><%=label.get("update.employee")%></label></strong></td>

				</tr>
				<tr>
					<td width="20%">Select <label id="employee" name="employee"
						ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:</td>
					<td width="35%"><s:hidden name="empUpdateId"/><s:textfield
						name="empUpdateToken" readonly="true" size="10" theme="simple" /><s:textfield
						name="empUpdateName" readonly="true" size="25" theme="simple" /></td>
					<td width="20%"><img src="../pages/images/recruitment/search2.gif"
							class="iconImage" height="16" align="absmiddle" width="16"
							onclick="callsF9(500,325,'PromotionArrears_f9Branch.action');">
							<input type="button" value="View Records" />
					</td>

				</tr>
			</table>
			</td>
		</tr>
		</s:if>
		<s:hidden name="hdPage" id="hdPage" value="%{hdPage}" />
	
	<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td width="100%" colspan="4"><strong><label id="negative.suppresion.option" name="negative.suppresion.option"
						ondblclick="callShowDiv(this);"><%=label.get("negative.suppresion.option")%></label></strong></td>
				</tr>
				
				<tr>
					<td width="20%"><label id="suppress.credit" name="suppress.credit"
						ondblclick="callShowDiv(this);"><%=label.get("suppress.credit")%></label></td>
					<td colspan="3"><s:checkbox name="suppressCreditFlag"/></td>
					
				</tr>
				
				<tr>
					<td width="20%">Select Debit:</td>
					<td colspan="3" nowrap="nowrap">
					<s:hidden name="suppressDebitCode"/>
					 <s:textarea name="suppressDebitName" cols="120" rows="1" readonly="true" />
					 <img
								src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="17" theme="simple"
								onclick="javascript:callDropdown('paraFrm_suppressDebitName',350,250,'PromotionArrears_f9SuppressDebit.action',event,'false','no','right')">
					</td>					
				</tr>			
			</table>
			</td>
		</tr>
	<!-- ADD MISSING EMPLOYEE -->
	<s:if test="arrCode!=''">
	
	
	
	
	
	<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td width="20%"><strong><label id="total.employee" name="total.employee"
						ondblclick="callShowDiv(this);"><%=label.get("total.employee")%></label></strong></td>
					<td width="30%"><s:property value="totalEmployee" /><s:hidden name="totalEmployee"/></td>
					<td width="20%"><strong><label id="net.amount" name="net.amount"
						ondblclick="callShowDiv(this);"><%=label.get("net.amount")%></label></strong></td>
					<td width="30%"><s:property value="totalNetAmount" /><s:hidden name="totalNetAmount"/></td>
					
				</tr>				
			</table>
			</td>
		</tr>
	
	
	
	
	
	<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td width="100%" colspan="4"><strong><label id="add.missing.employee" name="add.missing.employee"
						ondblclick="callShowDiv(this);"><%=label.get("add.missing.employee")%></label></strong></td>

				</tr>				
				<tr>
					
					<td width="20%"><label class="set" id="select.employee"
						name="select.employee" ondblclick="callShowDiv(this);"><%=label.get("select.employee")%></label> :<font
						color="red"> *</font> </td>
					<td width="30%" colspan="2" nowrap="nowrap">
					<s:hidden name="addEmpCode"/> <s:hidden name="addEmpToken"/> 
					 <s:textfield name="addEmpName" readonly="true" size="50"/> 
						<img src="../pages/images/recruitment/search2.gif"
							class="iconImage" height="16" align="absmiddle" width="16"
							onclick="callsF9(500,325,'PromotionArrears_f9AddEmp.action');">
					</td>					
					
					<td width="30%" colspan="1" >
					<s:if test="status!='Lock'">
					<input type="button" class="token" name="" value="Process" onclick="callProcess()">
					</s:if>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	
	
	
	
	
	<!-- VIEW EMPLOYEE  DETAILS -->
	
	
	
	
	<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td width="100%" colspan="4"><strong>View Employee Details</strong></td>

				</tr>				
				<tr>
					
					<td width="20%"><label class="set" id="select.employee"
						name="select.employee" ondblclick="callShowDiv(this);"><%=label.get("select.employee")%></label> :<font
						color="red"> *</font> </td>
					<td width="30%" colspan="2" nowrap="nowrap">
					<s:hidden name="editEmpCode"/> <s:hidden name="editEmpToken"/> 
					 <s:textfield name="editEmpName" readonly="true" size="50"/> 
						<img src="../pages/images/recruitment/search2.gif"
							class="iconImage" height="16" align="absmiddle" width="16"
							onclick="callsF9(500,325,'PromotionArrears_f9emp.action');">
					</td>					
					
					<td width="30%" colspan="1" >
					<input type="button" class="token" name="" value="View Details" onclick="callViewDetails()">
					</td>
				</tr>
			</table>
			</td>
		</tr><!--
		
		<tr>
			<td colspan="4" align="right">
			<input type="button" class="token" name="" value="Save" onclick="callSave()">
			<input type="button" class="token" name="" value="Remove" onclick="callRemove()">
			</td>
		</tr>	
	--></s:if>
	
	<s:if test="arrList">
		<tr>
			<td>
		<div id="scrollDiv1" class="scrollf9" style="width: 750px;overflow-x: yes;height: 100%; margin: 0;padding: 0;overflow: auto;">	
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td><input class="token" readonly="readonly"
					style="text-align: center; border: none; margin: 1px" type="text"
					size="4" value="Month" /></td>
				<td><input class="token" readonly="readonly"
					style="text-align: center; border: none; margin: 1px" type="text"
					size="4" value="Year" /></td>
				<td><input class="token" readonly="readonly"
					style="text-align: center; border: none; margin: 1px" type="hidden"
					value="Sal Days" /></td>
				<td><input class="token" readonly="readonly"
					style="text-align: center; border: none; margin: 1px" type="text"
					size="8" value="Arr Days" /></td>
					
				
						<s:iterator value="crHDList">
						<td class="formth" align="center" nowrap="nowrap"><input
							class="token" name="creditName" readonly="readonly"
							style="text-align: center; border: none; margin: 1px" type="text"
							size="6" value='<s:property value="creditName" />' /><s:hidden
							name="creditCode" /></td>
					</s:iterator>
					<td><input class="token" readonly="readonly"
						style="text-align: center; border: none; margin: 1px" type="text"
						size="8" value="Total Credit" /></td>
					<s:iterator value="drHDList">
						<td class="formth" align="center" nowrap="nowrap"><s:hidden
							name="debitCode" /><input class="token" name="debitName"
							readonly="readonly"
							style="text-align: center; border: none; margin: 1px" type="text"
							size="6" value='<s:property value="debitName" />' /></td>
					</s:iterator>
					<td><input class="token" readonly="readonly"
						style="text-align: center; border: none; margin: 1px" type="text"
						size="8" value="Total Debit" /></td>
					<td><input class="token" readonly="readonly"
						style="text-align: center; border: none; margin: 1px" type="text"
						size="8" value="Net Pay" /></td>
					<td><input class="classCheck" type="checkbox" name="chkEmpAll"
						id="chkEmpAll" onclick="callChkAll();" size="2" /></td>
					
						
					
				

				</tr>				
				
				<%
					int CR = 1;
					int DR = 1;
					
				%>
				
				<s:iterator value="arrList">
				<%
					int i = 1;
					int j = 0;
					int col;
				%>
				
					<tr>
						
						<td><input name="month" id="month<%=k %>" readonly="readonly"
							style="background-color: #F2F2F2" type="text" size="4"
							value='<s:property value="month" />' /> <s:hidden name="hMonth"
							id='<%="hMonth"+k%>' />
							<s:hidden name="listEmpCode"/>
							</td>
						<td><input name="year" id="year<%=k %>" readonly="readonly"
							style="background-color: #F2F2F2" type="text" size="4"
							value='<s:property value="year" />' /></td>
						<td><input name="salDays" id="salDays<%=k %>"
							readonly="readonly" style="background-color: #F2F2F2"
							type="hidden" value='<s:property value="salDays" />' /></td>
						<td><input name="arrDays" id="arrDays<%=k %>"
							readonly="readonly" style="background-color: #F2F2F2" type="text"
							size="8" value='<s:property value="arrDays" />' /></td>
							
						
									<s:iterator value="crValList">
									<td><input type="text" name="CR<%=CR %>" size="6"
										style="text-align: right" id="<%= k+"c"+i %>" maxlength="10"
										value="<s:property value='creditVal' />" onkeyup="sum(<%=k %>)"
										onkeypress="return numbersOnly();" />
										</td>
											<%
												i++;
												j++;
											%>
										</s:iterator>
										
									<td>
									1<input type="text" name="totCredit" size="8"
										style="background-color: #F2F2F2; text-align: righ" id="<%= k+"c"+i %>"
										value="<s:property value='totalCr' />" readonly="readonly" />
									</td>
									<s:iterator value="drValList">
										<%
										i++;
										%>
										
										<td><input type="text" name="DR<%=DR %>" size="6"
											style="text-align: right" id="<%= k+"c"+i %>" maxlength="10"
											value="<s:property value='debitVal' />" onkeyup="sum(<%=k %>)"
											onkeypress="return numbersOnly();" /></td>
									</s:iterator>
									<%
										col = i;
										col++;
									%>
									<td><input type="text" name="totDebit" size="8"
										style="background-color: #F2F2F2; text-align: righ" id="<%= k+"c"+col %>"
										value="<s:property value='totalDr' />" readonly="readonly" /></td>
									<%
									col++;
									%>
									<td><input type="text" name="totAmtPay" size="8"
										style="background-color: #F2F2F2; text-align: righ" id="<%= k+"c"+col %>"
										value="<s:property value='amtPay' />" readonly="readonly" /></td>
									<td>
									<input type="hidden"   name="hcheck" id='<%="hcheck"+k %>' >
									<input class="classCheck" type="checkbox" size="2"
										name="chkEmp" id='<%="chkEmp"+k %>'
										value="<s:property value='eId' />&<s:property value='hMonth' />&<s:property value='year' />&<s:property value='promotionCode' />&<s:property value='<%=""+k %>' />"
										onclick="callChkEmp('<%=k %>');" />
									</td>
								
							
						
					</tr>
				
				<%
									k++;
									c = i;
									d = j;
									CR++;
									DR++;
								%>
			</s:iterator>
				
				
				
				
				
			</table>
			
			</div>
			</td>
		</tr>
	</s:if>
	
	<!-- END: -->
	
	<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1">
				<tr>
					<td width="78%">
					
					
					<jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /> </td>
					<td width="22%" align="right">
					
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>

<script type="text/javascript">
	//document.getElementById('paraFrm_arrToYear').disabled=true;
	// QueryLoader.init();
	//callOnLoad();
	function callOnLoad()
	{
		if(document.getElementById('paraFrm_payinSalFlag').value == "true")
			document.getElementById('paraFrm_payinSalCheckBox').checked=true
		else
			document.getElementById('paraFrm_payinSalCheckBox').checked = false;
		if(document.getElementById('paraFrm_deductTaxFlag').value == "true")
			document.getElementById('paraFrm_deductTaxCheckBox').checked=true
		else
			document.getElementById('paraFrm_deductTaxCheckBox').checked = false;	
			
		if(document.getElementById('paraFrm_eArrYear').value=='')
			setCurrentYear('paraFrm_eArrYear');
		if(document.getElementById('paraFrm_paidYear').value=='')
			setCurrentYear('paraFrm_paidYear');
		gridScroll();
	}
	
	/**
	METHOD TO CHECK VALIDATION
	**/
	function checkValidation(){
	var fromMonth=document.getElementById('paraFrm_monthName').value;
	var fromYear=document.getElementById('paraFrm_eArrYear').value;
	var toMonth=document.getElementById('paraFrm_arrToMonth').value;
	var toYear=document.getElementById('paraFrm_arrToYear').value;
	var paidMonth=document.getElementById('paraFrm_arrPaidMonth').value;
	var paidYear=document.getElementById('paraFrm_arrPaidYear').value;
	var divCode=document.getElementById('paraFrm_divCode').value;
	if(divCode==''){
	alert("Please select division");
	return false;
	}
	if(fromMonth==''){
	alert("Please select "+document.getElementById('calculateFrom').innerHTML.toLowerCase()+" month");
	return false;
	}
	if(fromYear==''){
	alert("Please enter "+document.getElementById('calculateFrom').innerHTML.toLowerCase()+" year");
	return false;
	}
	
	if(toMonth==''){
	alert("Please select "+document.getElementById('calculateTo').innerHTML.toLowerCase()+" month");
	return false;
	}
	if(toYear==''){
	alert("Please enter "+document.getElementById('calculateTo').innerHTML.toLowerCase()+" year");
	return false;
	}
	
	if(paidMonth==''){
	alert("Please select "+document.getElementById('paid.month').innerHTML.toLowerCase()+" ");
	return false;
	}
	if(paidYear==''){
	alert("Please enter "+document.getElementById('paid.year').innerHTML.toLowerCase()+" ");
	return false;
	}
	
	
	
	return true;
	}
	
	function processFun(){
		if(!checkValidation()){
			return false;
		}
		var con=confirm('Do you realy want to process the promotion arrears');
		if(con){	
				document.getElementById('paraFrm').target = "_self";
				document.getElementById('paraFrm').action="PromotionArrears_processPromotion.action";
				document.getElementById('paraFrm').submit();
		}
		else{
		return false;
		}
	}
	function reprocessFun(){
		if(!checkValidation()){
			return false;
		}
		var con=confirm('Do you realy want to reprocess the promotion arrears');
		if(con){	
				document.getElementById('paraFrm').target = "_self";
				document.getElementById('paraFrm').action="PromotionArrears_reprocessPromotion.action";
				document.getElementById('paraFrm').submit();
		}
		else{
		return false;
		}
	}
	
	function lockFun(){
		var con=confirm('Do you realy want to lock the promotion arrears');
		if(con){	
				document.getElementById('paraFrm').target = "_self";
				document.getElementById('paraFrm').action="PromotionArrears_lockPromotion.action";
				document.getElementById('paraFrm').submit();
		}
		else{
		return false;
		}
	}
	function deleteFun(){
		var con=confirm('Do you realy want to delete the promotion arrears');
		if(con){	
				document.getElementById('paraFrm').target = "_self";
				document.getElementById('paraFrm').action="PromotionArrears_deletePromotion.action";
				document.getElementById('paraFrm').submit();
		}
		else{
		return false;
		}
	}
	function unlockFun(){
		var con=confirm('Do you realy want to unlock the promotion arrears');
		if(con){	
		doAuthorisation('3', 'Promotion', 'U');
			//	document.getElementById('paraFrm').target = "_self";
			//	document.getElementById('paraFrm').action="PromotionArrears_unLockPromotion.action";
			//	document.getElementById('paraFrm').submit();
		}
		else{
		return false;
		}
	}
	
	
	
	function doUnlock() {
		//enableBlockDiv();
		document.getElementById('paraFrm').action="PromotionArrears_unLockPromotion.action";
		document.getElementById('paraFrm').submit();
	}
	
	
	function callViewDetails(){
				if(document.getElementById('paraFrm_editEmpCode').value==''){
				alert('Please select employee');
				document.getElementById('paraFrm_editEmpName').focus();
				return false;
				}
				document.getElementById('paraFrm').target = "_self";
				document.getElementById('paraFrm').action="PromotionArrears_viewEmpDetails.action";
				document.getElementById('paraFrm').submit();
	}
	function callProcess(){
				if(document.getElementById('paraFrm_addEmpCode').value==''){
				alert('Please select employee');
				document.getElementById('paraFrm_addEmpName').focus();
				return false;
				}
				var con=confirm('Do you realy want to process the promotion arrears');
		if(con){
				document.getElementById('paraFrm').target = "_self";
				document.getElementById('paraFrm').action="PromotionArrears_addPromEmployee.action";
				document.getElementById('paraFrm').submit();
				}
		else{
		return false;
		}
	}
	 function callSave(){
				
				var con=confirm('Do you realy want to save the promotion arrears');
		if(con){
				document.getElementById('paraFrm').target = "_self";
				document.getElementById('paraFrm').action="PromotionArrears_savePromEmployee.action";
				document.getElementById('paraFrm').submit();
				}
		else{
		return false;
		}
	}
	 
	 
	  function callRemove(){
				
				var con=confirm('Do you realy want to remove the promotion arrears');
		if(con){
				document.getElementById('paraFrm').target = "_self";
				document.getElementById('paraFrm').action="PromotionArrears_removePromEmployee.action";
				document.getElementById('paraFrm').submit();
				}
		else{
		return false;
		}
	}
	
	function bankstatementFun(){
			var arrearsCode=document.getElementById('paraFrm_arrCode').value;	
			var linkSource11='PromotionArrears_callForEdit.action?arrCode='+arrearsCode;
			var month=document.getElementById('paraFrm_arrPaidMonth').value;
			var year=document.getElementById('paraFrm_arrPaidYear').value;			
			var divName=document.getElementById('paraFrm_divName').value;
			var monthText=document.getElementById('paraFrm_arrPaidMonth').options[document.getElementById('paraFrm_arrPaidMonth').selectedIndex].text;
			var divCode=document.getElementById('paraFrm_divCode').value
			document.getElementById('paraFrm').action='SalaryStatementBank_viewSalaryStatementLink.action?earningType=P&earningTypeDisplay=S&hiddenMonth='+month+'&earningYear='+year+'&earningCode='+
			arrearsCode+'&divisionName='+divName+'&earningMonth='+monthText+'&linkSource='+linkSource11+'&divisionCode='+divCode;
			document.getElementById('paraFrm').submit();
	}
	
	function reportFun(){
			var arrearsCode=document.getElementById('paraFrm_arrCode').value;	
			var linkSource11='PromotionArrears_callForEdit.action?arrCode='+arrearsCode;
			var month=document.getElementById('paraFrm_arrPaidMonth').value;
			var year=document.getElementById('paraFrm_arrPaidYear').value;			
			var divName=document.getElementById('paraFrm_divName').value;
			var monthText=document.getElementById('paraFrm_arrPaidMonth').options[document.getElementById('paraFrm_arrPaidMonth').selectedIndex].text;
			var divCode=document.getElementById('paraFrm_divCode').value;
			if(month<10){
			month='0'+month;
			}
			var brnName=document.getElementById('paraFrm_brnName').value;
			var brnCode=document.getElementById('paraFrm_brnCode').value;
			document.getElementById('paraFrm').action='MonthlyArrearsReport_input.action?arrearType=P&earningTypeDisplay=S&hiddenMonth='+month+'&earningYear='+year+'&promotionReportCode='+
			linkSource11+'&divisionName='+divName+'&month='+month+'&linkSource='+linkSource11+'&divisionCode='+divCode+'&branchName='+brnName+'&branchCode='+brnCode+'&year='+year;
			document.getElementById('paraFrm').submit();
	}
	
	
	function taxchallanFun(){
	var arrearsCode=document.getElementById('paraFrm_arrCode').value;
	var type = 'P';
	var backAction = "<%=request.getContextPath()%>/payroll/PromotionArrears_callForEdit.action?arrCode="+arrearsCode;
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action='<%=request.getContextPath()%>/incometax/TaxChallan_input.action?applicationCode='+arrearsCode+'&applicationType='+type+'&backAction='+backAction;
	document.getElementById('paraFrm').submit();
}
	
	
	function gridScroll()
	{
		myST = superTable("thetable", { fixedCols : 6,rightCols:2,viewCols:4});
	}
	function callMonth()
	{
		document.getElementById('paraFrm_eArrMonth').value = document.getElementById('paraFrm_monthName').selectedIndex;
	}
	
	function callPaidMonth()
	{
		document.getElementById('paraFrm_paidArrMonth').value = document.getElementById('paraFrm_paidMonthName').selectedIndex;
	}

	function validateProcess()
	{
		if(document.getElementById('paraFrm_monthName').selectedIndex == 0)
		{
			alert("Please select "+document.getElementById('reflecting.month').innerHTML.toLowerCase());
			document.getElementById('paraFrm_monthName').focus();
			return false;
		}
		if(document.getElementById('paraFrm_eArrYear').value == "")
		{
			alert("Please enter "+document.getElementById('reflecting.year').innerHTML.toLowerCase());
			document.getElementById('paraFrm_eArrYear').focus();
			return false;
		}
		
			if(document.getElementById('paraFrm_paidMonthName').value==''){
				alert("Please select "+document.getElementById('paid.month').innerHTML.toLowerCase());
				return false;
			}
			if(document.getElementById('paraFrm_paidYear').value==''){
				alert("Please enter "+document.getElementById('paid.year').innerHTML.toLowerCase());
				return false;
			}
			
		if(document.getElementById('paraFrm_divCode').value == "")
		{
			alert("Please select the "+document.getElementById('division').innerHTML.toLowerCase());
			return false;
		}
		//if(document.getElementById('paraFrm_brnCode').value == "")
		//{
		//	alert("Please select "+document.getElementById('branch').innerHTML.toLowerCase());
		//	return false;
		//}
		document.getElementById('paraFrm_monthRef').value = document.getElementById('paraFrm_payinSalFlag').checked;
		return true;
	}
	
	function sum(rowNo)
	{
		var i = <%=c%>
		var j = <%=d%>
		var totalCr = 0;
		var totalDr = 0;
		for(count=1;count<=i;count++)
		{
			if(count<=j)
			{
				if(document.getElementById(rowNo+'c'+count).value!="")
				{
					if((document.getElementById(rowNo+'c'+count).value).indexOf('-') > -1)
					{
							var temp = new Array(2); 
							temp = document.getElementById(rowNo+'c'+count).value.split('-');
							document.getElementById(rowNo+'c'+count).value = "-"+temp[1];
					}
					totalCr+=Math.round(document.getElementById(rowNo+'c'+count).value);
				}
				else
					document.getElementById(rowNo+'c'+count).value=0;
			}
			else if(count>j+1)
			{
				if(document.getElementById(rowNo+'c'+count).value!="")
				{
					if((document.getElementById(rowNo+'c'+count).value).indexOf('-') > -1)
					{
							var temp = new Array(2); 
							temp = document.getElementById(rowNo+'c'+count).value.split('-');
							document.getElementById(rowNo+'c'+count).value = "-"+temp[1];
					}
					totalDr+=Math.round(document.getElementById(rowNo+'c'+count).value);
				}
				else
					document.getElementById(rowNo+'c'+count).value=0;
			}
		}
		document.getElementById(rowNo+'c'+(j+1)).value = totalCr;
		document.getElementById(rowNo+'c'+count).value = totalDr;
		count++;
		document.getElementById(rowNo+'c'+count).value = totalCr-totalDr;
	}
	
	function callPage(id,totalPage)
	{
		var con;
		if(id=='P'){
		var p=document.getElementById('hdPage').value;
		id=eval(p)-1;
		}
		
		if(id=='N'){
		var p=document.getElementById('hdPage').value;
		id=eval(p)+1;
		}
		
		document.getElementById('hdPage').value=id;
		document.getElementById('paraFrm').action="PromotionArrears_calArrears.action";
		document.getElementById('paraFrm').submit();
	}
	
	function lockArrears(type)
	{
		if(type == "lock")
		{
			if(document.getElementById('paraFrm_arrCode').value == "")
			{
				alert("Please select the record to lock");
				return false;
			}
				var conf=confirm("Do you really want to lock promotion arrears ?");
			if(conf)
			{
				retrieveLockURL("PromotionArrears_lockArrears.action?type="+type,"PromotionArrears");
				document.getElementById('lock').style.display = 'none';
				document.getElementById('delete').style.display = 'none';
				document.getElementById('unlock').style.display = '';
			}
			else
				return false;
		}
		else
		{
			if(document.getElementById('paraFrm_arrCode').value == "")
			{
				alert("Please select the record to unlock");
				return false;
			}
			if(document.getElementById('paraFrm_status').value == "Pending")
			{
				alert("Arrears already unlocked");
				return false;
			}
			doAuthorisation('3', 'Arrears', 'U');
		}
	}
	function doUnlock11() {
			var type='unlock';
	 		retrieveLockURL("PromotionArrears_lockArrears.action?type="+type,"PromotionArrears");
				document.getElementById('unlock').style.display = 'none';
				document.getElementById('lock').style.display = '';
				document.getElementById('delete').style.display = '';
	  
	}
	function saveValidate()
	{
		if(!validateProcess()){
			return false;
		}
		if(document.getElementById('paraFrm_status').value == "Locked")
		{
			alert("Arrears is locked so cannot update the record");
			return false;
		}
		var rowCount  = <%= count%>
		if(rowCount <= 1)
		{
			alert("No records found to save. Please either select a record or proccess the arrears");
			return false;
		}
		return true;
	}
	
	function callChkAll()
	{
		rowCount = <%= count %>
		var chkVal = true;
		if(document.getElementById('chkEmpAll').checked == false)
			chkVal = false;
		for(i = 1; i < rowCount; i++)
			document.getElementById('chkEmp'+i).checked = chkVal;
	}
	
	function callChkEmp(id)
	{
	//hcheck
	document.getElementById('hcheck'+id).value='N';
	if(document.getElementById('chkEmp'+id).checked == true){
		document.getElementById('hcheck'+id).value='Y';
	}		
	}
	
	function callOnHold()
	{
		var rowCount = <%= count%>
		if(rowCount == 0)
		{
			alert("Please select the arrears record ");
			return false;
		}
		if(document.getElementById('paraFrm_status').value == "Locked")
		{
			alert("Arrears is locked so cannot on hold the records");
			for(i = 1; i < rowCount; i++)
				document.getElementById('chkEmp'+i).checked = false;
			document.getElementById('chkEmpAll').checked = false;
			return false;
		}
			if(document.getElementById('paraFrm_arrCode').value == "")
			{
				alert("Please save the record first");
				for(i = 1; i < rowCount; i++)
					document.getElementById('chkEmp'+i).checked = false;
				document.getElementById('chkEmpAll').checked = false;
				return false;
			}
			var k = <%=c%>
			var count=0;
			rowCount = <%=count%>;
			for(i = 1; i < rowCount; i++)
			{
				if(document.getElementById('chkEmp'+i).checked == true)
				{
					document.getElementById('empOnHoldFlag'+i).value = "Y";
					document.getElementById('eToken'+i).style.backgroundColor = '#C0EDFE';
					document.getElementById('eName'+i).style.backgroundColor = '#C0EDFE';
					document.getElementById('month'+i).style.backgroundColor = '#C0EDFE';
					document.getElementById('year'+i).style.backgroundColor = '#C0EDFE';
					document.getElementById('arrDays'+i).style.backgroundColor = '#C0EDFE';
					for(j=1;j<=k+2;j++)
						document.getElementById(i+'c'+j).style.backgroundColor = '#C0EDFE';
					count++;
				}
			}
			if(count==0)
			{
				alert("Please select atleast one record");
				return false;
			}
			retrieveURLArrears('PromotionArrears_onHoldSave.action?','paraFrm');
			for(i = 1; i < rowCount; i++)
				document.getElementById('chkEmp'+i).checked = false;
			document.getElementById('chkEmpAll').checked = false;
	}
	
	function callRemoveOnHold()
	{
		var rowCount = <%= count%>
		if(rowCount == 0)
		{
			alert("Please select the arrears record ");
			return false;
		}
		if(document.getElementById('paraFrm_status').value == "Locked")
		{
			alert("Arrears are locked so cannot remove on hold");
			for(i = 1; i < rowCount; i++)
				document.getElementById('chkEmp'+i).checked = false;
			document.getElementById('chkEmpAll').checked = false;
			return false;
		}
			if(document.getElementById('paraFrm_arrCode').value == "")
			{
				alert("Please save the Record first");
				for(i = 1; i < rowCount; i++)
					document.getElementById('chkEmp'+i).checked = false;
				document.getElementById('chkEmpAll').checked = false;
				return false;
			}
			var count=0;
			rowCount = <%=count%>;
			var k = <%=c%>
			for(i = 1; i < rowCount; i++)
			{
				if(document.getElementById('chkEmp'+i).checked == true)
				{
					document.getElementById('empOnHoldFlag'+i).value = "N";
					document.getElementById('eToken'+i).style.backgroundColor = '#F2F2F2';
					document.getElementById('eName'+i).style.backgroundColor = '#F2F2F2';
					document.getElementById('month'+i).style.backgroundColor = '#F2F2F2';
					document.getElementById('year'+i).style.backgroundColor = '#F2F2F2';
					document.getElementById('arrDays'+i).style.backgroundColor = '#F2F2F2';
					for(j=1;j<=k+2;j++)
						document.getElementById(i+'c'+j).style.backgroundColor = '#FFFFFF';
					count++;
				}
			}
			if(count==0)
			{
				alert("Please select atleast one record");
				return false;
			}
			retrieveURLArrears('PromotionArrears_removeOnHoldSave.action?','paraFrm');
			for(i = 1; i < rowCount; i++)
				document.getElementById('chkEmp'+i).checked = false;
			document.getElementById('chkEmpAll').checked = false;
	}
	
	function callRecalculate()
	{
		var rowCount = <%= count%>
		if(rowCount == 0)
		{
			alert("Please select the arrear record to recalculate");
			return false;
		}
		if(document.getElementById('paraFrm_status').value == "Locked")
		{
			alert("Arrears are locked so cannot recalculate");
			for(i = 1; i < rowCount; i++)
				document.getElementById('chkEmp'+i).checked = false;
			document.getElementById('chkEmpAll').checked = false;
			return false;
		}
		var count=0;
		rowCount = <%=count%>;
		var k = <%=c%>
		for(i = 1; i < rowCount; i++)
		{
			if(document.getElementById('chkEmp'+i).checked == true)
			{
				document.getElementById('recalEmpId'+i).value = "Y";
				count++;
			}
		}
		if(count==0)
		{
			alert("Please select atleast one record");
			return false;
		}
		retrieveURLArrearsRecal('PromotionArrears_recalculate.action?','paraFrm');
		for(i = 1; i < rowCount; i++)
			document.getElementById('chkEmp'+i).checked = false;
		document.getElementById('chkEmpAll').checked = false;
	}
	function deleteArrears(){
		if(document.getElementById('paraFrm_arrCode').value==""){
			alert("Please select arrears to delete");
			return false;
		}else if(document.getElementById('paraFrm_status').value == "Locked")
		{
			alert("Arrears are locked so cannot delete");
			return false;
		}
		else{
		var conf=confirm("Do you really want to delete arrears?");
  			if(!conf) 
  			{
  				return false;
  			}
			document.getElementById('paraFrm').action="PromotionArrears_delete.action";
			document.getElementById('paraFrm').submit();
		}
	}
	
	function callDelete(){
	try{
		var arrearCode = document.getElementById('paraFrm_arrCode').value;
		if(arrearCode == ""){
			alert("Please save the record first");
			return false;
		}
		var rowCount = <%= count%>
		if(rowCount == 0)
		{
			alert("Please select the arrear record to delete");
			return false;
		}
		if(document.getElementById('paraFrm_status').value == "Locked")
		{
			alert("Arrears are locked so cannot delete");
			for(i = 1; i < rowCount; i++)
				document.getElementById('chkEmp'+i).checked = false;
			document.getElementById('chkEmpAll').checked = false;
			return false;
		}
		
		var count=0;
		rowCount = <%=count%>;
		var k = <%=c%>
		for(i = 1; i < rowCount; i++)
		{
			if(document.getElementById('chkEmp'+i).checked == true)
			{
				document.getElementById('deleteEmpId'+i).value = "Y";
				count++;
			}
		}
		if(count==0)
		{
			alert("Please select atleast one record");
			return false;
		}
		var conf = confirm("Do you really want to delete this record?");
  		if(conf) {
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action="PromotionArrears_deleteEmployee.action";
			document.getElementById('paraFrm').submit();
		}
		
		}catch(e){
			//alert(e);
		}
	
	}
	
	function backFun(){
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action="PromotionArrears_input.action";
			document.getElementById('paraFrm').submit();
	}

	

</script>
