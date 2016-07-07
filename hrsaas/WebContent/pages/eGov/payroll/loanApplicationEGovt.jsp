<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="LoanApplicationEGovAction" method="post" id="paraFrm" name="loan"
	theme="simple">
	<s:hidden name="empId" />
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Loan
					Application </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td ><input type="button" value="Reset" class="reset" onclick="return resetScreen();"/></td>
					<td width="22%">
						<div align="right"><font color="red">*</font> Indicates	Required</div>
					</td>
				</tr>
			</table>
			<label></label></td>
		</tr>
		<tr>
			<td colspan="4">
			<table width="100%" border="0" align="center" cellpadding="1"
				cellspacing="0" class="formbg">
				<tr>
					<td></td>
				</tr>
				<tr>

					<td colspan="1" width="20%"><label name="employee"
						id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
					<font color="red">*</font>:</td>
					<td colspan="3" width="80%"><s:textfield theme="simple"
						readonly="true" name="token" size="15" /><s:textfield theme="simple"
						name="empName" size="50" readonly="true" /><img
						src="../pages/images/recruitment/search2.gif" height="16"
						class="iconImage" align="absmiddle" width="16"
						onclick="javascript:callEmployee();"></td>

				</tr>
				<tr>
					<td colspan="1" width="20%"><label name="loanAmount" id="loanAmount"
						ondblclick="callShowDiv(this);"><%=label.get("loanAmount")%></label>:
					</td>
					<td colspan="1" width="30%"><s:textfield theme="simple"
						readonly="true" name="loanAmount" size="25" /></td>
					<td colspan="1" width="20%"><label name="pendingLoanAmt"
						id="pendingLoanAmt" ondblclick="callShowDiv(this);"><%=label.get("pendingLoanAmt")%></label>:
					</td>
					<td colspan="1" width="30%"><s:textfield theme="simple"
						readonly="true" name="pendingLoanAmount" size="25" /></td>
				</tr>
				<tr>
					<td colspan="1" width="20%"><label name="emiAmt" id="emiAmt"
						ondblclick="callShowDiv(this);"><%=label.get("emiAmt")%> </label>:
					</td>
					<td colspan="3" width="80%"><s:textfield theme="simple"
						name="emiAmount" size="25" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>&nbsp</td>
		</tr>
		<s:hidden name="paraId" />
		<s:hidden name="paraLoanAmt" />
		<s:hidden name="paraEmiAmt" />
		<s:hidden name="loanID" />
		<tr>
			<td >
			<table width="100%" border="0" align="center" cellpadding="1"
				cellspacing="1" class="formbg">
				<tr>
					<td>Add Application</td>
				</tr>
				<tr>
					<td colspan="1" width="20%" class="formtext" height="22"><label
						name="loanDate" id="loanDate" ondblclick="callShowDiv(this);"><%=label.get("loanDate")%></label><font color="red">*</font>:
					</td>
					<td colspan="1">
						<s:textfield theme="simple" onkeypress="return numbersWithHiphen()" maxlength="10"  name="loanDate" size="25" />
						<s:a href="javascript:NewCal('paraFrm_loanDate','DDMMYYYY');">
						<img class="iconImage" src="../pages/images/recruitment/Date.gif" width="16"
							 height="16" border="0" align="absmiddle" />
						</s:a>	 
					</td>
					<td colspan="1" width="20%" class="formtext" height="22"><label
						name="isRefundable" id="isRefundable"
						ondblclick="callShowDiv(this);"><%=label.get("isRefundable")%>
					</label><font color="red">*</font>:</td>
					<td colspan="1"><s:select cssStyle="width:100"
						list=" #{'Y':'Yes','N':'No'}" name="isRefundable" /></td>
					</td>
				</tr>
				<tr>
					<td colspan="1" width="20%" class="formtext" height="22"><label
						name="loanAmt" id="loanAmt" ondblclick="callShowDiv(this);"><%=label.get("loanAmt")%></label><font color="red">*</font>:
					</td>
					<td colspan="1"><s:textfield theme="simple" onkeypress="return numbersOnly();" name="loanAmt1"
						size="25"  /></td>
					<td colspan="1" width="20%" class="formtext" height="22">
						<label
							name="emiAmt1" id="emiAmt1" ondblclick="callShowDiv(this);"><%=label.get("emiAmt1")%>
						</label>
						<font color="red">*</font>:
					</td>
					<td colspan="1"><s:textfield theme="simple" onkeypress="return numbersOnly();" name="emiAmount1"
						size="25"  onclick="validateEmi()"/></td>
				</tr>
				<tr>
					
					<td width="100%" colspan="4" align="center">
						<input type="button" class="add" value="Add"  onclick="validateFields()"/>
						<input type="button" class="clear" value="Clear"  onclick="return clearFields();"/>
					</td>
				</tr>
			</table>
			<!-- Add Applicatrion Closed -->
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<!--   srNo -->
					<td align="center" class="formth" width="15%"><label
						class="set" name="srNo" id="srNo" ondblclick="callShowDiv(this);"><%=label.get("srNo")%></label>
					</td>
					<!--   Loan Date  -->
					<td align="center" class="formth" width="20%"><label
						class="set" name="loanDate" id="loanDate"
						ondblclick="callShowDiv(this);"><%=label.get("loanDate")%></label>
					</td>
					<td align="center" class="formth" width="20%"><label
						class="set" name="loanAmount" id="loanAmount"
						ondblclick="callShowDiv(this);"><%=label.get("loanAmount")%></label>
					</td>
					<td align="center" class="formth" width="15%"><label
						class="set" name="isRefundable" id="isRefundable"
						ondblclick="callShowDiv(this);"><%=label.get("isRefundable")%></label>
					</td>

					<td align="center" class="formth" width="15%"><label
						class="set" name="emiAmt1" id="emiAmt1"
						ondblclick="callShowDiv(this);"><%=label.get("emiAmt1")%></label>
					</td>
					<td class="formth" class="formth" width="15%" id="ctrlHide"><label
						class="set" class="token" theme="simple" name="Remove"
						id="Remove" ondblclick="callShowDiv(this);"><%=label.get("Remove")%></label>&nbsp;
					</td>
				</tr>

				<%
				int count1 = 0;
				%>
				<%!int d1 = 0;%>
				<%
				int ii = 0;
				%>
				<%!int val = 0;%>

				<s:iterator value="list">

					<tr <%if(count1%2==0){
																			%> class="tableCell1"
						<%}else{%> class="tableCell2" <%	}count1++; %>
						>

						<td width="10%" align="center" ><%=++ii%> 
							<input type="hidden" name="srNo" value="<%=ii%>" />
						</td>

						<td>
							<s:hidden name="loanDateIt"></s:hidden>
							<s:property value="loanDateIt" /> 
						</td>
						<td>
							<s:hidden name="loanAmt1It"></s:hidden>
							<s:property value="loanAmt1It" /> 
						</td>
						<td>
							<s:hidden name="isRefundableIt"></s:hidden>
							<s:property value="isRefundableIt" /> 
						</td>

						<td>
							<s:hidden name="emiAmount1It"></s:hidden>
							<s:hidden name="loanIDList"></s:hidden>
							<s:property value="emiAmount1It" /> 
						</td>
						<input type="hidden" name="hdeleteOp" id="hdeleteOp<%=ii%>" />
						<td width="10%" align="center" nowrap="nowrap" 
							id="ctrlHide">
						<input type="button" class="rowDelete" title="Delete Record"
							id="confChkop<%=ii%>" name="confChkop"
							onclick="callForDelete2('<s:property value='loanIDList'/>','<s:property value='isRefundableIt'/>',<s:property value="loanAmt1It" />,<s:property value="emiAmount1It" /> )" /></td>
					</tr>
				</s:iterator>
				<%
				d1 = ii;
				%>
			</table>
			</td>
		</tr>
		</td>
		</tr>
	</table>

</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script type="text/javascript">
   function callEmployee(){
		callsF9(500,325,'LoanApplicationEGovAction_f9action.action');
	}
	function resetScreen(){
		document.getElementById("paraFrm").action="LoanApplicationEGovAction_reset.action";
		document.getElementById("paraFrm").submit();
	}
	function callForDelete2(id,isRefund,loanAmount,emiAmount ){
	//alert(id);
	//alert(isRefund);
	//alert(loanAmount);
	//alert(emiAmount);
	 var con=confirm('Do you  really want to delete this record ?');
		    if(con){
			document.getElementById('paraFrm_loanID').value =id;
			document.getElementById('paraFrm_paraId').value =isRefund;
			document.getElementById('paraFrm_paraLoanAmt').value =loanAmount;
			document.getElementById('paraFrm_paraEmiAmt').value =emiAmount;
			document.getElementById("paraFrm").action="LoanApplicationEGovAction_delete.action";
			document.getElementById("paraFrm").submit();
			}else 
			return false;
	}
	function validateFields(){
		var isRefund=document.getElementById("paraFrm_isRefundable").value;
		if(isRefund=='N'){
			    var fieldName = ["paraFrm_empName","paraFrm_loanDate","paraFrm_isRefundable","paraFrm_loanAmt1"];
				var lableName = ["employee","loanDate","isRefundable","loanAmt"];
				var types = ["select","select","select","enter"];
			      if(!validateBlank(fieldName,lableName,types)){
			          return false;
			      }
			      else{
			      		document.getElementById("paraFrm").action="LoanApplicationEGovAction_addItem.action";
						document.getElementById("paraFrm").submit();
			      }
		}else{
			  var fieldName = ["paraFrm_empName","paraFrm_loanDate","paraFrm_isRefundable","paraFrm_loanAmt1","paraFrm_emiAmount1"];
			  var lableName = ["employee","loanDate","isRefundable","loanAmt","emiAmt1"];
			  var types = ["select","select","select","enter","enter"];
		      if(!validateBlank(fieldName,lableName,types)){
		          return false;
		      }
		      else{
		      		document.getElementById("paraFrm").action="LoanApplicationEGovAction_addItem.action";
					document.getElementById("paraFrm").submit();
		      }
		}      
	}
	function validateEmi(){
		var isRefund=document.getElementById("paraFrm_isRefundable").value;
		if(isRefund=='N'){
			document.loan.emiAmount1.readonly="true";
		}else{
			document.loan.emiAmount1.readonly="false";
		}
	}
	
	function clearFields(){
		document.getElementById('paraFrm_loanDate').value='';
		document.getElementById('paraFrm_isRefundable').value='Y';
		document.getElementById('paraFrm_loanAmt1').value='';
		document.getElementById('paraFrm_emiAmount1').value='';
		document.getElementById('paraFrm_').value='';
	}
</script>

