<!-- ADDED BY REEBA - ADVANCE DEFAULTERS -->

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>

<s:form action="AdvClmDisbursement" validate="true" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="right" cellpadding="2"
		class="formbg" cellspacing="1">
		<tr>
			<td colspan="3" valign="bottom" class="txt">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" align="center" class="formbg">
				<tr>
					<td valign="bottom" class="txt" width="3%" colspan="1"><strong
						class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="97%" class="txt" colspan="2"><strong
						class="text_head"> Advance Defaulters </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<s:hidden name="rowId" />
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="80%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>


					<td width="20%"></td>
				</tr>
			</table>
			<label></label></td>
		</tr>

		<s:hidden name="checkEdit" />
		<s:hidden name="categoryListlength" />

		<tr>
			<td colspan="4">

			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">

				<tr>
					<td width="20%" colspan="5"><strong>Employee Details</strong></td>
				</tr>
				<tr>
					<td width="20%" colspan="1" class="formtext" height="22"><label
						class="set" name="employee" id="employee"
						ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
					:</td>
					<td width="30%" colspan="1"><s:property
						value="defaulterEmpName" /><s:hidden name="deafulterEmpId" /></td>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" name="travelstartdate" id="travelstartdate"
						ondblclick="callShowDiv(this);"><%=label.get("travelstartdate")%></label></td>
					<td width="30%" colspan="1"><s:property
						value="defTravelstartdate" /> <s:hidden name="defTravelstartdate" />
				</tr>

				<tr>
					<td width="20%" colspan="1" class="formtext" height="22"><label
						class="set" name="branch" id="branch"
						ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
					<td width="30%" colspan="1"><s:property value="defBranchName" /></td>

					<td width="20%" colspan="1" class="formtext"><label
						class="set" name="travelenddate" id="travelenddate"
						ondblclick="callShowDiv(this);"><%=label.get("travelenddate")%></label>
					:</td>

					<td width="30%" colspan="1"><s:property
						value="defTravelEnddate" /> <s:hidden name="defTravelEnddate" /></td>
				</tr>

				<tr>
					<td colspan="1" width="20%" class="formtext" height="22"><label
						class="set" name="department" id="department"
						ondblclick="callShowDiv(this);"><%=label.get("department")%></label :</td>
					<td width="30%" colspan="1"><s:property
						value="defDepartmentName" /></td>

					<td width="20%" colspan="1" class="formtext"><label
						class="set" name="grade" id="grade"
						ondblclick="callShowDiv(this);"><%=label.get("grade")%></label> :</td>

					<td width="30%" colspan="1"><s:property value="defGradeName" /><s:hidden
						name="defGradeID" /></td>
				</tr>

				<tr>
					<td width="20%" colspan="1" class="formtext" height="22"><label
						class="set" id="designation" name="designation"
						ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
					:</td>
					<td width="30%" colspan="1"><s:property value="defDesignation" /></td>
					<td width="20%" colspan="1" class="formtext"></td>
					<td width="30%" colspan="1"></td>

				</tr>


				<tr>
					<td width="20%" colspan="1" class="formtext" height="22"><label
						class="set" id="appl.date" name="appl.date"
						ondblclick="callShowDiv(this);"><%=label.get("appl.date")%></label>
					:</td>
					<td width="30%" colspan="1"><s:property
						value="defaulterApplDate" /></td>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" id="trvlid" name="trvlid"
						ondblclick="callShowDiv(this);"><%=label.get("trvlid")%></label> :</td>
					<td width="30%" colspan="1"><s:property
						value="defaulterTrvlId" /></td>
					<s:hidden name="pendingapplId" />
					<s:hidden name="defaulterTrvlId" />
					<s:hidden name="pendingapplCode" />
					<s:hidden name="pendingadvAmount" />
				</tr>



				</td>
				</tr>
			</table>
			</td>
		</tr>


		<!-- payment details  block-->


		<tr>
			<td colspan="5">

			<table width="100%" border="0" cellpadding="0" cellspacing="2"
				class="formbg">

				<tr>
					<td width="20%" colspan="1"><label class="set" name="month"
						id="month1" ondblclick="callShowDiv(this);"><%=label.get("month")%></label>: 
					<font color="red">*</font></td>
					<td width="30%" colspan="1"><s:select name="defMonth"
						headerKey="0" headerValue="--Select--" title="Select a month"
						list="#{'1':'January', '2':'Febuary', '3':'March', '4':'April', '5':'May', '6':'June', '7':'July', 
										'8':'August', '9':'September', '10':'October', '11':'November', '12':'December'}" /></td>
					<td></td>
					<td width="20%" colspan="1"><label class="set" name="year"
						id="year1" ondblclick="callShowDiv(this);"><%=label.get("year")%></label>: 
					</td>

					<td width="30%" colspan="1"><s:textfield name="defYear"
						size="5" maxlength="4" cssStyle="text-align: right"
						title="Enter the year" onkeypress="return numbersOnly(event);"
						onblur="return checkYear('paraFrm_defYear', 'year');" /></td>

				</tr>

				<tr>
					<td width="20%" colspan="1"><label id="debit" name="debit"
						ondblclick="callShowDiv(this);"><%=label.get("debit")%></label> :
					<font color="red">*</font></td>
					<td width="30%" colspan="1"><s:hidden name="debitCode"
						value="%{debitCode}" theme="simple" /> <s:textfield
						name="debitName" theme="simple" size="30" readonly="true" /><img id="ctrlHide"
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'AdvClmDisbursement_f9debitHead.action');">
					</td>
				</tr>

			</table>
			</td>
		</tr>


		<!-- end of payment details -->


		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="60%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

				</tr>
			</table>
			</td>
		</tr>

	</table>

</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>




<script>


function returntolistFun(){
	document.getElementById("paraFrm").target="_self";  
	document.getElementById('paraFrm').action = "AdvClmDisbursement_returnHome.action";
	document.getElementById('paraFrm').submit();
}

 
 //for save
function saveFun() {
	var month = document.getElementById('paraFrm_defMonth').value;
	if(month=="0"){
		alert("Please select "+document.getElementById('month1').innerHTML.toLowerCase());
		return false;
	}	
	var year = document.getElementById('paraFrm_defYear').value;
	if(year==""){
		alert("Please enter "+document.getElementById('year1').innerHTML.toLowerCase());
		return false;
	}
	var debit = document.getElementById('paraFrm_debitCode').value;
	if(debit==""){
		alert("Please select "+document.getElementById('debit').innerHTML.toLowerCase());
		return false;
	}		
	document.getElementById("paraFrm").target="_self";   
	document.getElementById('paraFrm').action = "AdvClmDisbursement_saveDefaulterData.action";
	document.getElementById('paraFrm').submit();
		
}

function resetFun(){
	document.getElementById('paraFrm_defMonth').value="0";
	document.getElementById('paraFrm_defYear').value="";
	document.getElementById('paraFrm_debitCode').value="";
	document.getElementById('paraFrm_debitName').value="";
}
  

   
 
function newRowColor(cell) {   		
	cell.className='Cell_bg_first';
}
	    
function oldRowColor(cell,val) {
	cell.className='Cell_bg_second';
} 
		
	function viewPolicy(gradId){		   
		//alert(gradId);  
		win=window.open('','win','top=260,left=250,width=650,height=600,scrollbars=yes,status=no,resizable=no');
		document.getElementById("paraFrm").target="win";
		document.getElementById("paraFrm").action="TravelApplication_getTravelPolicy.action?gradeId="+gradId;
		document.getElementById("paraFrm").submit();	
		document.getElementById("paraFrm").target="main"; 
	}
 
</script>