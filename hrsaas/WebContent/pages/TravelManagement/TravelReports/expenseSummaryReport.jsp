<%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="ExpenseSummaryReport" validate="true" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">
					Expense Summary Report </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<!-- BUTTON PANEL BEGINS -->
		<tr>
			<s:hidden name='backFlag' />
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<!-- BUTTON PANEL ENDS -->



		<tr>
			<td colspan="5">
			<table border="0" cellpadding="1" cellspacing="1" class="formbg"
				width="100%">

				<!-- EMPLOYEE NAME -->
				<!-- DIVISION -->
				<tr>
					<td width="20%" colspan="1"><label class="set" name="division"
						id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
					:<font color="red">*</font></td>
					<td width="20%" colspan="1"><s:textfield size="25"
						name="division" readonly="true" /> <s:hidden name="divisionId" />
					</td>
					<td width="5%" colspan="1"><img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="14" width="14" theme="simple"
						onclick="javascript:callsF9(500,325,'ExpenseSummaryReport_f9division.action');" />
					</td>

					<td width="20%" colspan="1"><label class="set"
						name="employee.name" id="employee.name"
						ondblclick="callShowDiv(this);"><%=label.get("employee.name")%></label>
					:</td>
					<td width="20%" colspan="1"><s:textfield size="25"
						name="employeeName" readonly="true" /> <s:hidden name="empId" /><s:hidden
						name="empToken" /></td>
					<td width="5%" colspan="1"><img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="14" width="14" theme="simple"
						onclick="javascript:callsF9(500,325,'ExpenseSummaryReport_f9empName.action');" />
					</td>

				</tr>

				<!-- DATE -->

				<tr>
					<td height="22" width="20%"><label class="set"
						name="from.date" id="from.date" ondblclick="callShowDiv(this);"><%=label.get("from.date")%></label>:</td>


					<td colspan="1" width="20%"><s:textfield size="25"
						onkeypress="return numbersWithHiphen();" name="frmDate"
						onblur="setText('paraFrm_frmDate','')"
						onfocus="clearText('paraFrm_frmDate','dd-mm-yyyy')" maxlength="10" />
					</td>
					<td width="5%" colspan="1"><s:a
						href="javascript:NewCal('paraFrm_frmDate','DDMMYYYY');">
						<img class="iconImage" src="../pages/images/recruitment/Date.gif"
							width="16" height="16" border="0" align="absmiddle" />
					</s:a></td>


					<td colspan="1" height="22" width="20%"><label class="set"
						name="to.date" id="to.date" ondblclick="callShowDiv(this);"><%=label.get("to.date")%></label>:</td>


					<td colspan="1" width="20%"><s:textfield size="25"
						onkeypress="return numbersWithHiphen();" name="toDate"
						onblur="setText('paraFrm_toDate','')"
						onfocus="clearText('paraFrm_toDate','dd-mm-yyyy')" maxlength="10" />
					</td>
					<td width="5%" colspan="1"><s:a
						href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
						<img class="iconImage" src="../pages/images/recruitment/Date.gif"
							width="16" height="16" border="0" align="absmiddle" />
					</s:a></td>
				</tr>

				<!-- PROJECT -->
				<!-- CUSTOMER -->

				<tr>
					<td width="20%" colspan="1"><label class="set" name="project"
						id="project" ondblclick="callShowDiv(this);"><%=label.get("project")%></label>
					:</td>
					<td width="20%" colspan="1"><s:textfield size="25"
						name="project" readonly="true" /> <s:hidden name="projectId" />
					</td>
					<td width="5%" colspan="1"><img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="14" width="14" theme="simple"
						onclick="javascript:callsF9(500,325,'ExpenseSummaryReport_f9project.action');" />
					</td>
					<td width="20%" colspan="1"><label class="set" name="customer"
						id="customer" ondblclick="callShowDiv(this);"><%=label.get("customer")%></label>
					:</td>
					<td width="20%" colspan="1"><s:textfield size="25"
						name="customer" readonly="true" theme="simple" /> <s:hidden
						name="customerId" /></td>
					<td width="5%" colspan="1"><img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="14" width="14" theme="simple"
						onclick="javascript:callsF9(500,325,'ExpenseSummaryReport_f9customer.action');" />
					</td>


				</tr>

				<!-- EXPENSE CATEGORY -->
				<!--HOTEL TYPE -->

				<tr>
					<td width="20%"><label class="set" name="expense.category"
						id="expense.category" ondblclick="callShowDiv(this);"><%=label.get("expense.category")%></label>
					:</td>
					<td width="20%" colspan="1"><s:textfield size="25"
						name="expenseCategory" readonly="true" /> <s:hidden
						name="categoryId" /></td>
					<td width="5%" colspan="1"><img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="14" width="14" theme="simple"
						onclick="javascript:callsF9(500,325,'ExpenseSummaryReport_f9expenseCategory.action');" />
					</td>


					<td width="20%"><label class="set" name="hotel.type"
						id="hotel.type" ondblclick="callShowDiv(this);"><%=label.get("hotel.type")%></label>
					:</td>
					<td width="20%" colspan="1"><s:textfield size="25"
						name="hotelType" readonly="true" /> <s:hidden name="hotelId" />
					</td>
					<td width="5%" colspan="1"><img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="14" width="14" theme="simple"
						onclick="javascript:callsF9(500,325,'ExpenseSummaryReport_f9hotelType.action');" />
					</td>






				</tr>

				<!--AGENCY -->
				<!--CARRIER -->


				<tr>
					<td width="20%"><label class="set" name="agency" id="agency"
						ondblclick="callShowDiv(this);"><%=label.get("agency")%></label> :</td>
					<td width="20%" colspan="1"><s:textfield size="25"
						name="agencyName" readonly="true" /> <s:hidden name="agencyId" />
					</td>
					<td width="5%" colspan="1"><img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="14" width="14" theme="simple"
						onclick="javascript:callsF9(500,325,'ExpenseSummaryReport_f9agency.action');" />
					</td>


					<td width="20%"><label class="set" name="carrier" id="carrier"
						ondblclick="callShowDiv(this);"><%=label.get("carrier")%></label>
					:</td>
					<td width="20%" colspan="1"><s:textfield size="25"
						name="carrierName" readonly="true" /> <s:hidden name="carrierId" />
					</td>
					<td width="5%" colspan="1"><img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="14" width="14" theme="simple"
						onclick="javascript:callsF9(500,325,'ExpenseSummaryReport_f9carrier.action');" />
					</td>

				</tr>

				<!-- branch & Department -->
				<tr>
					<td width="20%" colspan="1"><label class="set" name="branch"
						id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
					<td width="20%%" colspan="1"><s:hidden name="branchCode" /><s:textfield
						size="25" name="branchName" readonly="true" /></td>
					<td width="5%" colspan="1"><img
						src="../pages/images/recruitment/search2.gif" width="14"
						height="14" class="iconImage"
						onclick="javascript:callsF9(500,325,'ExpenseSummaryReport_f9Branch.action');"></td>
					
					<td width="20%" colspan="1"><label class="set"
						name="department" id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:</td>
					<td width="20%" colspan="1"><s:hidden name="departmentCode" /><s:textfield
						size="25" name="departmentName" readonly="true" /></td>
					<td width="5%" colspan="1"><img
						src="../pages/images/recruitment/search2.gif" width="14"
						height="14" class="iconImage"
						onclick="javascript:callsF9(500,325,'ExpenseSummaryReport_f9Department.action');"></td>
					



				</tr>

				<tr>
					<td width="20%" colspan="1"><label class="set" name="grade"
						id="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>
					:</td>
					<td width="20%" colspan="1"><s:textfield size="25"
						name="gradeName" readonly="true" /> <s:hidden name="gradeId" /></td>
					<td width="5%" colspan="1"><img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="14" width="14" theme="simple"
						onclick="javascript:callsF9(500,325,'ExpenseSummaryReport_f9gradeName.action');" />
					</td>
					<td colspan="1" ><label class="set"
						id="currency.type" name="currency.type" ondblclick="callShowDiv(this);"><%=label.get("currency.type")%></label>:<font
						color="red"></font></td>
					<td colspan="1" >
						<s:select name="currencyType"
							 list="%{currencyHashmap}" 
							cssStyle="width:250" />
					</td>
					
				</tr>
				<tr>
					<td width="20%" class="formtext">Report Type</td>
					<td width="30%" colspan="2" class="formtext">
					<div id="reportTypeDiv"><s:select name="reportType"
						list="#{'P':'Pdf','X':'Xls','T':'Txt'}" /></div>
					</td>
				</tr>
				





			</table>
			</td>
		</tr>
	</table>


</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<script>
function reportFun(){
var division = trim(document.getElementById('paraFrm_division').value);
		if(division==""){
			alert("Please select "+document.getElementById('division').innerHTML.toLowerCase());
			return false;
		}
var fields1=["paraFrm_frmDate"];
	var labels1=["from.date"];
	if(!validateDate(fields1,labels1)){
	return false;
	
	}
	var fields2=["paraFrm_toDate"];
	var labels2=["to.date"];
	if(!validateDate(fields2,labels2)){
	return false;
	
	}
	var datediff = checkDateForApplication('paraFrm_frmDate','paraFrm_toDate','From date', 'To date');
	  	  	if(!datediff){
	  			return false;
	}
	
document.getElementById('paraFrm').target = "_blank";
  		document.getElementById('paraFrm').action = 'ExpenseSummaryReport_generateReport.action';
		document.getElementById('paraFrm').submit();
      	

}

function resetFun() {
		
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'ExpenseSummaryReport_reset.action';
		document.getElementById('paraFrm').submit();
  	}

function checkDateForApplication(fromDate,todate,fromlabel,tolabel){
 		var fromDt =document.getElementById(fromDate).value ;
		var toDate = document.getElementById(todate).value ;
 		try{
 			var datediff = dateDifferenceChk(fromDt,toDate,fromDate,fromlabel,tolabel);
  	  		if(!datediff){
  				return false;
  			}
  		}catch(e){alert(e);}
 		return true;
 	}
 	
 	function dateDifferenceChk(fromDate, toDate, fieldName, fromLabName, toLabName){
		var strDate1 = fromDate.split("-");
		var starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
		var strDate2 = toDate.split("-"); 
		var endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 
		
		if(endtime < starttime) { 
			alert(""+toLabName+" should be greater than or equal to "+fromLabName);
			document.getElementById(fieldName).focus();
			return false;
		}
		return true;
	}
</script>