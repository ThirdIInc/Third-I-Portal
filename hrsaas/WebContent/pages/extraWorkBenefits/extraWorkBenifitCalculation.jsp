<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@include file="/pages/ApplicationStudio/configAuth/authorizationChecking.jsp" %>

<s:form action="ExtraWorkBenifitCalculation" method="post"
	name="ExtraWorkBenifitCal" id="paraFrm" theme="simple" target="main">
	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td colspan="6">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="100%" class="txt"><strong class="text_head">Extra
					Work Benefit Calculation</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
		<tr>
			<td colspan="6">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">
				<tr>
					<td colspan="6"></td>
				</tr>
				<tr>
					<td width="14%">Month :<font color="red">*</font></td>
					<td width="12%"><s:select name="month" headerKey="0"
						headerValue="--Select--" cssStyle="width:90"
						list="#{'1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" />
					</td>
					<td width="9%">Year :<font color="red">*</font></td>
					<td width="13%"><s:textfield name="year" size="10"
						cssStyle="text-align: right" maxlength="4"
						onkeypress="return numbersOnly();"
						onblur="return checkYear('paraFrm_year', 'Year');"></s:textfield></td>
					<td width="8%"></td>
					<td width="44%"></td>
				</tr>
				<tr>
					<td width="14%">Division :<font color="red">*</font></td>
					<td width="12%"><s:hidden name="divisionCode" /><s:textfield
						name="divisionName" readonly="true" /></td>
					<td width="9%"><img
						src="../pages/images/recruitment/search2.gif" width="16"
						id="ctrlHide" height="15" class="iconImage"
						onclick="javascript:callsF9(500,325,'ExtraWorkBenifitCalculation_f9division.action');"></td>
					<td width="13%"></td>
					<td width="8%"></td>
					<td width="44%"></td>
				</tr>
				<tr>
					<td width="14%" nowrap="nowrap">Include in salary :</td>
					<td width="12%"><s:checkbox name="salaryCheck" /></td>
					<td width="9%" nowrap="nowrap">Salary Month:</td>
					<td width="13%"><s:select name="salarymonth" headerKey="0"
						headerValue="--Select--" cssStyle="width:90"
						list="#{'1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" />
					</td>
					<td width="8%" nowrap="nowrap">Salary Year:</td>
					<td width="44%"><s:textfield name="salaryyear" size="10"
						cssStyle="text-align: right"
						onblur="return checkYear('paraFrm_salaryyear', 'Year');"
						maxlength="4" onkeypress="return numbersOnly();"></s:textfield></td>
				</tr>


			</table>
			</td>
		</tr>

		<tr>
			<td colspan="6">
			<table width="100%" border="0" cellpadding="1" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="1" cellspacing="0"
						class="formbg">

						<tr>
							<td width="5%" class="formth" nowrap="nowrap" >Sr No.</td>
							<td width="10%" class="formth" nowrap="nowrap">Employee Id</td>
							<td width="10%" class="formth" nowrap="nowrap">Employee Name</td>
							<td width="10%" class="formth" nowrap="nowrap">ExtraWork Date</td>
							<td width="10%" class="formth" nowrap="nowrap">Benefit For</td>
							<td width="10%" class="formth"nowrap="nowrap" >Benefit Type</td>
							<s:iterator value="creditList">
								<td width="10%" class="formth" nowrap="nowrap"><s:hidden
									name="creditHeadCode" /> <s:property value="creditHead" /></td>
							</s:iterator>
			<td width="10%" class="formth">Total</td>

						</tr>


						<%
						int i = 0;
						%>
						<%!int t = 0;%>
						<%
							Object[][] credit_amt = null;
							try {
								credit_amt = (Object[][]) request.getAttribute("credit_amt");

							} catch (Exception e) {
								e.printStackTrace();
							}
						%>
						<%
						int counter = 0;
						%>

						<s:iterator value="list">

							<tr class="sortableTD">
								<td width="5%" align="left" class="sortableTD"><%=i + 1%></td>
								<td width="10%" align="left" class="sortableTD"><s:property
									value="empToken" /><s:hidden name="empToken" /> <s:hidden
									name="employeeId" /></td>
								<td width="10%" align="left" class="sortableTD"><s:property
									value="empName" /></td>
								<td width="10%" align="center" class="sortableTD"><s:property
									value="extraWorkDate" /><s:hidden name="extraWorkDate" /></td>
								<td width="10%" align="center" class="sortableTD"><s:property
									value="benifitFor" /><s:hidden name="benifitFor" /></td>

								<td width="10%" align="center" nowrap="nowrap"
									class="sortableTD"><s:property value="benifitType" /><s:hidden
									name="benifitType" />
									<s:hidden
									name="creditedTo" />
										<s:hidden
									name="extraworkFromTime" />
										<s:hidden
									name="extraworkToTime" />
									</td>

								<%
										if (credit_amt != null && credit_amt.length > 0) {
										for (int val = 0; val < credit_amt[0].length-1; val++) {
								%>
								<td width="10%" align="right" class="sortableTD"><input
									type="hidden" name="<%=i%>" size="5" readonly="readonly" style="background-color: #F2F2F2; text-align: right;"
									value="<%= String.valueOf(credit_amt[counter][val]) %>" />
									<s:property value="<%= String.valueOf(credit_amt[counter][val]) %>" />
									</td>
								<%
									}
									}
								%>
								<%
								if (credit_amt != null && credit_amt.length > 0) {
								%>
								
								<td width="10%" align="right" class="sortableTD" ><input
									type="hidden" name="totAmount" size="5" readonly="readonly" style="background-color: #F2F2F2; text-align: right;"
									value="<%= String.valueOf(credit_amt[counter][credit_amt[0].length-1]) %>" />
									
									<s:property value="<%= String.valueOf(credit_amt[counter][credit_amt[0].length-1]) %>" />
									</td>
								<%
								}
								%>
								
							</tr>

							<%
								i++;
								counter++;
							%>

						</s:iterator>
						<%
						t = i;
						%>

						<%
						if (t == 0) {
						%>
						<tr>
							<td colspan="6" align="center"><font color="red"> No
							data to display</font></td>
						</tr>
						<%
						}
						%>

					</table>
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
	<s:hidden name="processCode" />
	<s:hidden name="checkProcess" />
	<s:hidden name="lockFlag" />
</s:form>

<script>


function searchFun() {
		if(navigator.appName == 'Netscape') {
			var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
		} else {
			var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		}
		
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action = 'ExtraWorkBenifitCalculation_f9searchaction.action';
		document.getElementById("paraFrm").submit();
	}

 
function processFun() {
	
	 var month = document.getElementById('paraFrm_month').value;
	var year =document.getElementById('paraFrm_year').value;
	var div =document.getElementById('paraFrm_divisionCode').value;
	 	
		if(month==0)
		{
		alert("Please select month");
				return false;
		}
		if(year=="")
		{
				alert("Please enter year");
				return false;
		}
		if(div=="")
		{
				alert("Please select division");
				 
				return false;
		}
		 
		 
		 		
		 
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ExtraWorkBenifitCalculation_process.action';
		document.getElementById('paraFrm').submit();
	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ExtraWorkBenifitCalculation_back.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
	
	var conf=confirm("Do you really want to delete this record ?");
		 	if(conf)
			{
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action = 'ExtraWorkBenifitCalculation_delete.action';
			document.getElementById('paraFrm').submit();
			}
			 else
			 {
			 return false; 
			 }
	 return true;
	}
	
	function lockFun() {
  
	 var month = document.getElementById('paraFrm_month').value;
	var year =document.getElementById('paraFrm_year').value;
	var div =document.getElementById('paraFrm_divisionCode').value;
		
		if(month==0)
		{
		alert("Please select month");
				return false;
		}
		if(year=="")
		{
				alert("Please enter year");
				return false;
		}
		if(div=="")
		{
				alert("Please select division");
				 
				return false;
		}
		var conf=confirm("Do you really want to lock extra work benefit calculation ?");
		 	if(conf)
			{
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action = 'ExtraWorkBenifitCalculation_lock.action';
			document.getElementById('paraFrm').submit();
			}
			 else
			 {
			 return false; 
			 }
	 return true;
		 
		
	}
	
	
	function unlockFun() {
  		doAuthorisation('6', 'Extra Working Benefit', 'U');
	}
	
	function doUnlock() {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ExtraWorkBenifitCalculation_unlock.action';
		document.getElementById('paraFrm').submit();
	}
	
	function saveFun() {
	
	 var month = document.getElementById('paraFrm_month').value;
	var year =document.getElementById('paraFrm_year').value;
	var div =document.getElementById('paraFrm_divisionCode').value;
	 
		if(month==0)
		{
		alert("Please select month");
				return false;
		}
		if(year=="")
		{
				alert("Please enter year");
				return false;
		}
		if(div=="")
		{
				alert("Please select division");
				 
				return false;
		}
	 
		 
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ExtraWorkBenifitCalculation_save.action';
		document.getElementById('paraFrm').submit();
	}

 if(document.getElementById('paraFrm_year').value=='')
{
	setCurrentYear('paraFrm_year');
}

function process()
{

 var month = document.getElementById('paraFrm_month').value;
	var year =document.getElementById('paraFrm_year').value;
	var div =document.getElementById('paraFrm_divisionCode').value;
		
		if(month==0)
		{
		alert("Please select month");
				return false;
		}
if(year=="")
{
		alert("Please enter year");
		return false;
}
if(div=="")
{
		alert("Please select division");
		 
		return false;
}
	return true;
}

function callSave()
{

var val=<%=t%>;
   
var month = document.getElementById('paraFrm_month').value;
	var year =document.getElementById('paraFrm_year').value;
	var div =document.getElementById('paraFrm_divisionCode').value;
		
		if(month==0)
		{
		alert("Please select month");
				return false;
		}
if(year=="")
{
		alert("Please enter year");
		return false;
}
if(div=="")
{
		alert("Please select division");
		return false;
}

if(val==0)
{
alert("No record to save");
		return false;
}

	return true;
}

function callLock()
{
var val=<%=t%>;
 var month = document.getElementById('paraFrm_month').value;
	var year =document.getElementById('paraFrm_year').value;
	var div =document.getElementById('paraFrm_divisionCode').value;
		
		if(month==0)
		{
		alert("Please select month");
				return false;
		}
if(year=="")
{
		alert("Please enter year");
		return false;
}
if(div=="")
{
		alert("Please select division");
		return false;
}

if(val==0)
{
alert("No record to Lock");
		return false;
}

	return true;
}

function checkYear(name){
	 
   		var year = document.getElementById(name).value;
   		if(year == "") {
   			return true;
   		}
   		if(year.length < 4) {
   			alert("Year should have atleast 4 digits");
   			document.getElementById(name).focus();
   			return false;
   		}
   		return true;
   }
</script>