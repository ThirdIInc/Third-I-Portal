<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@page import="java.util.HashMap"%>
<%@page import="org.paradyne.lib.Utility"%>
<script type="text/javascript" src="../pages/common/include/javascript/sorttable.js"></script>
<s:form name="" action="" validate="" id="paraFrm" theme="simple">
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Employee Increment History</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="80%">
						<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
					</td>
					<td width="20%">
						<div align="right"><font color="red">*</font> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="4">
			<table width="100%" class="formbg" border="0">
				<tr>
					<td colspan="4" width="100%"><strong>Employee Information</strong></td>
				</tr>
				<tr>
					<td width="20%" class="formtext"><label class="set"
						id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label><font
						color="red"> *</font> :</td>
					<td colspan="3"><s:textfield theme="simple" name="empToken"
						size="10" readonly="true" /><s:textfield theme="simple"
						name="empName" size="93" readonly="true" /><s:hidden
						theme="simple" name="empCredit" /> <s:hidden
						name="empstatus"></s:hidden><s:hidden name="emp_Id" /> <s:hidden
						name="empId" /> </td>
				</tr>

				<tr>
					<td width="20%"><label class="set" id="branch"
						name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:
					</td>
					<td width="25%"><s:textfield theme="simple"
						readonly="true" name="empCenter"  /></td>
					<td width="20%"><label class="set" id="empDept"
						name="empDept1" ondblclick="callShowDiv(this);"><%=label.get("empDept")%></label> :</td>
					<td width="25%"><s:textfield name="empDeptName" readonly="true" /><s:hidden name="empDeptId"/></td>
				</tr>
				<tr>
					<td width="20%"><label class="set" id="designation"
						name="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:</td>
					<td width="25%"><s:textfield theme="simple" 
						name="empRank" readonly="true" /></td>
					<td width="20%"><label class="set" id="empgrade"
						name="empgrade" ondblclick="callShowDiv(this);"><%=label.get("empgrade")%></label>
					:</td>
					<td width="25%"><s:hidden name="empGradeId" /><s:textfield
						theme="simple" readonly="true"  name="empGradeName" />
					</td>
				</tr>
				<tr>
				<td width="20%"><label class="set" id="joinDate1"
						name="joinDate" ondblclick="callShowDiv(this);"><%=label.get("joinDate")%></label>:</td>
					<td width="25%"><s:textfield name="joiningDate" readonly="true" /></td>
				</tr>
				<tr>
					<td width="20%"><label class="set" id="accountNo1"
						name="accountNo1"><%=label.get("accountNo")%></label>:</td>
					<td width="25%">
					<s:property value="empAccountNo"/>
					</td>
				</tr>
				<tr>
					<td width="20%"><label class="set" id="incHistory"
						name="incHistory" ondblclick="callShowDiv(this);"><%=label.get("incHistory")%></label>
					:</td>
					<td width="25%" id="ctrlShow">
					<s:select name="incrementPeriod" headerKey="" 
						headerValue="   --Select--  " list="%{incrementHistoryMap}" cssStyle="width:129" />
					</td>
					<td width="55%" colspan="2">
						<input type="button" value="  View  " Class="token" onclick="callView();" id="ctrlShow"/>
					</td>
				</tr>
			</table>
			</td>
			</tr>
			<tr>
				<td colspan="4">
				<table width="100%" class="formbg" border="0">
					<tr>
						<td colspan="3" width="100%"><strong>Salary Break up</strong></td>
					</tr>
					<tr>
						<td class="formth" width="30%"><label class="set"
							id="salary.header" name="salary.header"
							ondblclick="callShowDiv(this);"><%=label.get("salary.header")%></label></td>
						<td class="formth" width="10%" now><label class="set"
							id="period" name="period" ondblclick="callShowDiv(this);"><%=label.get("period")%></label></td>
						<td class="formth" width="10%" now><label class="set"
							id="amount" name="amount" ondblclick="callShowDiv(this);"><%=label.get("amount")%></label></td>
					</tr>
				<!-- Credit-Monthly -->
				<s:iterator value="salHeaderList">
					<tr>
						<td class="sortableTD" width="30%"><s:property value="creditNameItt" /></td>
						<td class="sortableTD" width="10%"><s:property value="creditPeriodItt" /></td>
						<td class="sortableTD" width="10%" align="right"><s:property value="creditAmountItt" /></td>
					</tr>
				</s:iterator>
			</table>
		</td>
	</tr>
	<tr>
			<td colspan="3">
			<table width="100%" class="formbg" cellpadding="1" cellspacing="1"	border="0">
				<!-- TOTAL MONTHLY SALARY -->
				<tr>
					<td>
						&nbsp;
					</td>
					<td width="20%"><label class="set" id="totMonth"
						name="totMonth" ondblclick="callShowDiv(this);"><%=label.get("totMonth")%></label>
					</td>
					<td width="20%" align="right">
						<s:if test="generalFlag">
							<s:property value="totalamt" />
						</s:if> <s:else>
							<s:textfield name="totalamt" theme="simple" size="10" readonly="true" cssStyle="background-color: #F2F2F2;text-align:right;" />
						</s:else>
					</td>
				</tr>
				<!-- TOTAL ANNUAL SALARY -->
				<tr>
					<td>
						&nbsp;
					</td>
					<td><label class="set" id="totAnnual"
						name="totAnnual" ondblclick="callShowDiv(this);"><%=label.get("totAnnual")%></label>
					</td>
					<td align="right">
						<s:if test="generalFlag">
							<s:property value="annualAmt" />
						</s:if> <s:else>
						<s:textfield name="annualAmt" theme="simple" size="10" readonly="true" cssStyle="background-color: #F2F2F2;text-align:right;"/>
						</s:else>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
</s:form>
<script>
	function backFun(){
 		document.getElementById('paraFrm').target = "_self";
 		document.getElementById('paraFrm').action="EmpCredit_empDetail.action";
		document.getElementById('paraFrm').submit();
	}
	
	function callView(){
		var incPeriod = document.getElementById('paraFrm_incrementPeriod').value; 
	
		if(incPeriod==""){
			alert("Please select increment history");
			return false;
		}else{
			//window.open('','new','top=50,left=200,width=650,height=500,scrollbars=yes,status=no,resizable=no');
			//document.getElementById("paraFrm").target="new";
		 	document.getElementById("paraFrm").action="EmpCredit_viewIncrementHistory.action"; 
		  	document.getElementById("paraFrm").submit();
		  	}
	}
</script>