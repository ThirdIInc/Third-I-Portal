<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp" %>

<s:form action="UserCreation" method="post" validate="true"
	theme="simple" name="form" id="paraFrm">
	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">

		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">User
					Creation</strong></td>
				</tr>
			</table>
			</td>
		</tr>





		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">

				<tr>
					<td class="formhead"><strong class="forminnerhead">User
					Creation</strong></td>
				</tr>

				<tr>
					<td>&nbsp;&nbsp;</td>
				</tr>

				<tr>
					<td><label name="user.name" id="user.name" ondblclick="callShowDiv(this);"><%=label.get("user.name") %></label> : <s:select theme="simple"
						name="userCreation.name1" headerKey="-1" cssStyle="width:135"
						list="#{'1':'First Name','2':'Middle Name','3':'Last Name','4':'Fisrt Name Initial',
								'5':'Middle Name Initial','6':'Last Name Initial'}" />

					<s:select theme="simple" name="userCreation.seperator"
						headerKey="-1" cssStyle="width:70" headerValue="Select"
						list="#{'1':'.','2':'_','3':'-'}" /> <s:select theme="simple"
						name="userCreation.name2" headerKey="-3" cssStyle="width:135"
						list="#{'3':'Last Name','1':'First Name','2':'Middle Name','4':'Fisrt Name Initial',
								'5':'Middle Name Initial','6':'Last Name Initial'}" /><!--

							Digits: <s:textfield size="10" theme="simple"
								name="userCreation.digits" maxlength="5" onkeypress="return numbersOnly();" />

							--></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;</td>
				</tr>

				<tr>
					<td width="100%" height="26" valign="top" align="center"><input
						type="button" class="token" value="Generate Users"
						onclick="submitSend();" /> 
				 
						</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td width="20%" colspan="4" class="formhead"><strong
						class="forminnerhead">User Creation using Email id</strong></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td width="80%" height="26" valign="top" class="cellbg"
						align="center"><input type="button" class="token"
						value="Generate Users Using Email Ids" onclick="submitEmail();" /></td>

				</tr>


			</table>
			</td>
		</tr>





		<tr>
			<td width="100%" colspan="3">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td width="20%" colspan="4" class="formhead"><strong
						class="forminnerhead">Send Mail To</strong></td>
				</tr>

				<tr>
					<td><b>Send To :</b></td>

				</tr>


				<tr>
					<td width="100%" colspan="4"><label name="division" id="division" ondblclick="callShowDiv(this);"><%=label.get("division") %></label>
					:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <s:textfield size="35"
						name="divisionName" readonly="true" /> <s:hidden
						name="divisionCode" /> <img class="iconImage"
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" border="0" onclick="javascript:callDivision();" /></td>

				</tr>
				<tr>
					<td><b>( OR )</b></td>
				</tr>
				<tr>
					<td width="100%" colspan="4"><label name="branch" id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch") %></label>
					:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <s:textfield size="35"
						name="branchName" readonly="true" /> <s:hidden name="branchCode" />
					<img class="iconImage"
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" border="0" onclick="javascript:callBranch();" /></td>

				</tr>
				<tr>
					<td><b>( OR )</b></td>
				</tr>
				<tr>
					<td width="100%" colspan="4"><label name="department" id="department" ondblclick="callShowDiv(this);"><%=label.get("department") %></label> :<s:textfield
						size="35" name="deptName" readonly="true" /> <s:hidden
						name="deptCode" /> <img class="iconImage"
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" border="0" onclick="javascript:callDept();" /></td>

				</tr>
				<tr>
					<td><b>( OR )</b></td>
				</tr>
				<tr>
					<td width="100%" colspan="4"><label name="designation" id="designation" ondblclick="callShowDiv(this);"><%=label.get("designation") %></label> :<s:textfield
						size="35" name="desgName" readonly="true" /> <s:hidden
						name="desgCode" /> <img class="iconImage"
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" border="0" onclick="javascript:callDesignationt();" />
					</td>

				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td><b>( OR )</b></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td class="formtext"><b>Send To :</b></td>

				</tr>

				<tr>
					<td width="100%" colspan="4"><label name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee") %></label> : <s:textfield size="35"
						name="employeeName" readonly="true" /> <s:hidden
						name="employeeCode" /><s:hidden name="empTokenNo"/> <img class="iconImage"
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" border="0" onclick="javascript:callEmployee();" /></td>

				</tr>
				<tr>
					<td colspan="5"><img
						src="../pages/common/css/default/images/space.gif" width="5"
						height="7" /></td>
				</tr>

				<tr>
					<td width="100%" height="26" align="center"><s:select
						theme="simple" name="userCreation.sendDate" headerKey="-1"
						list="#{'1':'ALL Users','2':'Users Generated Today','3':'Users generated During Current Month','4':'Users generated in Previous Month'}" /><input
						type="button" class="token" value="Send Email" onclick="send();" />
					</td>
				</tr>
			</table>
		</td>
		</tr>

	</table>

</s:form>

<script>

function submitSend()
{
var conf=confirm("User Accounts of New Employees will be Generated\nAre you sure you want to proceed?")
		if(conf){
			document.getElementById("paraFrm").action="UserCreation_submit.action";
  			document.getElementById("paraFrm").target="main";
			document.getElementById("paraFrm").submit();
			
		}else{
			return false;
			}
			return true;
}
 
function submitEmail()
{

var confm=confirm("User Accounts of New Employees will be Generated\nAre you sure you want to proceed?")
		if(confm){
			//alert('1');
			document.getElementById("paraFrm").action="UserCreation_submitEmail.action";
  			document.getElementById("paraFrm").target="main";
			document.getElementById("paraFrm").submit();
			return true;
		}else{
			return false;
			}

}

function numbersonly(myfield){
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

 function callReport()
  	{
  	  	document.getElementById('paraFrm').target = "_blank";
  		document.getElementById('paraFrm').action = 'UserCreation_report.action';
  		document.getElementById('paraFrm').submit();
  		//document.getElementById('paraFrm').target = "main";
  	}

function send(){
	
	document.getElementById("paraFrm").action="UserCreation_sendMail.action";
  			document.getElementById("paraFrm").target="main";
			document.getElementById("paraFrm").submit();
}


	function callEmployee()
	{
	callsF9(500,325,'UserCreation_f9employee.action');
	}
	
	function callDivision()
	{
	var lucky=callsF9(500,325,'UserCreation_f9division.action');
	
	}
	
	function callBranch()
	{
	callsF9(500,325,'UserCreation_f9branch.action');
	}
	
	function callDept()
	{
	callsF9(500,325,'UserCreation_f9dept.action');
	}
	
	function callDesignationt()
	{
	callsF9(500,325,'UserCreation_f9designation.action');
	}

</script>