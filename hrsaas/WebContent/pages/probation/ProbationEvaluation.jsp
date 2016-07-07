<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../common/commonValidations.jsp"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<link rel="stylesheet" type="text/css"
	href="../pages/common/tabcontent.css" />
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="probationEvaluation" method="post" validate="true"
	id="paraFrm" theme="simple">
	<s:hidden name="hiddenempcode" />
	<s:hidden name="hiddenprobationcode" />
	<s:hidden name="evalcode" />
	<s:hidden name="evalstatus" />
	<s:hidden name="displayFlag" />


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
					<td width="93%" class="txt"><strong class="text_head">Probation
					Evaluation</strong></td>
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
					<td width="78%"><s:if test="displayFlag">
						<s:submit cssClass="add" action="probationEvaluation_save"
							value="    Save" onclick="return validateForm();" />


						<s:submit cssClass="reset" action="probationEvaluation_reset"
							value="    Reset" />

						<s:if test="evalFlag">

							<s:submit cssClass="token" action="probationEvaluation_sendtoHR"
								value="    Send to HR" onclick="return callsendtoHR();" />

							<s:submit cssClass="delete" action="probationEvaluation_delete"
								value="    Delete" onclick="return confirmDeleteFun();" />

							<input type="button" class="token" value="  Report  "
								onclick="callReport('probationEvaluation_report.action');" />

						</s:if>

					</s:if> <s:if test="backBtnFlag">
						<s:submit cssClass="back" action="probationEvaluation_cancel"
							value="    Back " />

					</s:if> <s:else>
						<s:submit cssClass="back" action="ProbationConfirmation_back"
							value="    Back " />
						<input type="button" class="token" value="  Report  "
							onclick="callReport('probationEvaluation_report.action');" />

					</s:else> <s:if test="viewReportFlag">
						<input type="button" class="token" value="  Report  "
							onclick="callReport('probationEvaluation_report.action');" />
					</s:if></td>
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			<label></label></td>
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
							<td colspan="3" class="formhead"><strong
								class="forminnerhead">Probation Evaluation</strong></td>
						</tr>

						<tr>
							<td width="100%" colspan="3">&nbsp;</td>
						</tr>

						<tr>
							<td width="15%" colspan="1"><label name="employee"
								id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
							:</td>
							<td width="20%" colspan="6"><s:textfield name="empToken"
								size="12" value="%{empToken}" readonly="true" /> <s:textfield
								name="empName" size="83" value="%{empName}" readonly="true" />
							<s:hidden name="intques.exIntcode" /> <s:hidden name="empId"
								value="%{empId}"></s:hidden></td>
						</tr>
						<tr>
							<td width="15%" colspan="1"><label name="branch" id="branch"
								ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
							:</td>
							<td width="25%" colspan="1"><s:textfield name="branch"
								value="%{branch}" theme="simple" size="25" readonly="true" /></td>
							<td width="20%" colspan="1"><label name="designation"
								id="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
							:</td>
							<td width="30%" colspan="3"><s:textfield name="designation"
								value="%{designation}" theme="simple" size="25" readonly="true" /></td>
						</tr>
						<tr>
							<td width="15%" colspan="1"><label name="department"
								id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
							:</td>
							<td width="25%" colspan="1"><s:textfield name="department"
								theme="simple" size="25" readonly="true" /></td>
							<td width="20%" colspan="1"><label name="duedate"
								id="duedate" ondblclick="callShowDiv(this);"><%=label.get("duedate")%></label>
							:</td>
							<td width="30%" colspan="3"><s:textfield name="duedateconf"
								theme="simple" size="25" readonly="true" /></td>
						</tr>


						<!--  <tr>
			<td width="25%">Designation:</td>

			<td width="25%"><s:textfield name="intques.designation"
				value="%{designation}" theme="simple" size="25" /></td>
				
					<!--  <td width="25%">Grade:</td>

			<td width="25%"><s:textfield name="intques.grade" value="%{grade}"
				theme="simple" size="25" /></td>
		</tr> -->

						<tr>
							<td width="15%" colspan="1"><label name="joindate"
								id="joindate" ondblclick="callShowDiv(this);"><%=label.get("joindate")%></label>
							:</td>

							<td width="25%" colspan="1"><s:textfield
								name="dateOfJoining" theme="simple" size="25" readonly="true" /></td>
							<td width="20%" colspan="1"><label name="evaluationDate"
								id="evaluationDate" ondblclick="callShowDiv(this);"><%=label.get("evaluationDate")%></label><font
								color="red">*</font> :</td>

							<td width="30%" colspan="3"><s:if test="displayFlag">
								<s:textfield name="evaluationDate"
									onkeypress="return numbersWithHiphen();" maxlength="10" />
								<s:a
									href="javascript:NewCal('paraFrm_evaluationDate','DDMMYYYY');">


									<img class="iconImage" id="ctrlHide"
										src="../pages/images/recruitment/Date.gif" width="16"
										height="16" border="0" align="absmiddle" />
								</s:a>

							</s:if> <s:else>

								<s:textfield name="evaluationDate"
									onkeypress="return numbersWithHiphen();" maxlength="10"
									readonly="true" />

							</s:else></td>
						</tr>



					</table>
					</td>
				</tr>
				<tr>
					<td class="sortableTD">
					<table width="100%" border="0" cellpadding="1" cellspacing="1">

						<tr>
							<td class="formth" width="50%"><label name="question"
								id="question" ondblclick="callShowDiv(this);"><%=label.get("question")%></label>
							</td>
							<td class="formth" width="10%"><label name="rating"
								id="rating" ondblclick="callShowDiv(this);"><%=label.get("rating")%></label>
							</td>
							<td class="formth" width="40%"><label name="comment"
								id="comment" ondblclick="callShowDiv(this);"><%=label.get("comment")%></label>
							</td>

						</tr>
						<s:hidden name="flag" />
						<s:hidden name="QuesDtlflag" />
						<s:hidden name="quedetails" />
						<%!int p = 1, s = 0;%>
						<s:iterator value="tableList">
							<td width="50%" class="sortableTD"><s:hidden
								name="probationevalCode" /> <s:property
								value="probationevalName" /></td>

							<td class="sortableTD" width="10%" nowrap="nowrap"><s:if
								test="probationEvaluation.displayFlag">
								<s:if test="flag">
									<s:select name="rating" cssStyle="width:100" id='<%="h"+p%>'
										list="tmap" />
								</s:if>
								<s:else>

									<s:select name="rating" cssStyle="width:100" headerKey=""
										headerValue="Select Rating" id='<%="h"+p%>' list="tmap"
										size="1" />
								</s:else>
							</s:if> <s:else>
								<s:if test="flag">
									<s:select name="rating" disabled="true" cssStyle="width:100"
										id='<%="h"+p%>' list="tmap" />
								</s:if>
								<s:else>

									<s:select name="rating" disabled="true" cssStyle="width:100"
										headerKey="" headerValue="Select Rating" id='<%="h"+p%>'
										list="tmap" size="1" />
								</s:else>
							</s:else></td>
							<td class="sortableTD" width="40%" nowrap="nowrap"><s:if
								test="probationEvaluation.displayFlag">
								<s:textarea name="comment" id='<%="comment"+p%>' rows="3"
									cols="40" onkeyup="callLength(id,'500');">
								</s:textarea>
								<img src="../pages/images/zoomin.gif" height="12"
									align="absmiddle" width="12" theme="simple"
									onclick="javascript:callWindow('<%="comment"+p%>','comment','','paraFrm_descCnt<%=p%>','500');">
									&nbsp;Remaining chars <input type="text" name="descCnt"
									id='paraFrm_descCnt<%=p%>' readonly="true" size="5" />
							</s:if> <s:else>
								<s:textarea disabled="true" name="comment" id='<%="comment"+p%>'
									rows="3" cols="40" onkeyup="callLength(id,'500');">
								</s:textarea>
								<img src="../pages/images/zoomin.gif" height="12"
									align="absmiddle" width="12" theme="simple"
									onclick="javascript:callWindow('<%="comment"+p%>','comment','','paraFrm_descCnt<%=p%>','500');">
									&nbsp;Remaining chars <input type="text" name="descCnt"
									id='paraFrm_descCnt<%=p%>' readonly="true" size="5" />
							</s:else> <input type="hidden" value='<s:property value="comment"/>' /></td>
							<s:hidden name="code" />

							</tr>
							<%
							p++;
							%>

						</s:iterator>
						<input type="hidden" name="count" id="count" value="<%=p%>" />
						<%
							s = p;
							p = 1;
						%>
					</table>
					</td>
				</tr>
				<tr>
					<td>
					<table>
						<tr>
							<td><label name="recommend" id="recommend"
								ondblclick="callShowDiv(this);"><%=label.get("recommend")%>
							</label><font color="red">*</font> :</td>
							<td><s:if test="displayFlag">
								<s:select theme="simple" name="recommendation"
									cssStyle="width:130" id="recommendation"
									list="#{'-1':'Select','C':'Confirmed','E':'Extended Probation','T':'Terminated'}"
									onchange="checkVal();" />
							</s:if> <s:else>

								<s:select theme="simple" name="recommendation"
									cssStyle="width:130" disabled="true" id="recommendation"
									list="#{'-1':'Select','C':'Confirmed','E':'Extended Probation','T':'Terminated'}"
									onchange="checkVal();" />
							</s:else></td>
						</tr>
						<tr id="extndProbationDaysId">
							<td><label name="extendprobationdays"
								id="extendprobationdays" ondblclick="callShowDiv(this);"><%=label.get("extendprobationdays")%></label><font
								color="red">*</font> :</td>
							<td><s:textfield name="extendedProbationDays" maxlength="3"
								onkeypress="return numbersOnly();" /></td>
						</tr>
						<tr id="confirmId">
							<td id=""><label name="confirmdt" id="confirmdt"
								ondblclick="callShowDiv(this);"><%=label.get("confirmdt")%></label><font
								color="red">*</font>:</td>
							<td><s:if test="displayFlag">
								<s:textfield name="confirmDate"
									onkeypress="return numbersWithHiphen();" maxlength="10" />
								<s:a href="javascript:NewCal('paraFrm_confirmDate','DDMMYYYY');">
									<img class="iconImage" id="ctrlHide"
										src="../pages/images/recruitment/Date.gif" width="16"
										height="16" border="0" align="absmiddle" />
								</s:a>


							</s:if> <s:else>

								<s:textfield name="confirmDate"
									onkeypress="return numbersWithHiphen();" maxlength="10"
									readonly="true" />

							</s:else></td>
						</tr>
						<tr id="terminatedId">
							<td colspan="1"><label name="terminateddt"
								id="terminateddt" ondblclick="callShowDiv(this);"><%=label.get("terminateddt")%></label>
							<font color="red">*</font>:</td>
							<td colspan="3"><s:if test="displayFlag">
								<s:textfield name="terminationDate"
									onkeypress="return numbersWithHiphen();" />
								<s:a
									href="javascript:NewCal('paraFrm_terminationDate','DDMMYYYY');">
									<img class="iconImage" id="ctrlHide"
										src="../pages/images/recruitment/Date.gif" width="16"
										height="16" border="0" align="absmiddle" />
								</s:a>

							</s:if> <s:else>
								<s:textfield name="terminationDate"
									onkeypress="return numbersWithHiphen();" maxlength="10"
									readonly="true" />
							</s:else></td>
						</tr>
					</table>
					</td>
				</tr>

			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><s:if test="displayFlag">
						<s:submit cssClass="add" action="probationEvaluation_save"
							value="    Save" onclick="return validateForm();" />


						<s:submit cssClass="reset" action="probationEvaluation_reset"
							value="    Reset" />

						<s:if test="evalFlag">

							<s:submit cssClass="token" action="probationEvaluation_sendtoHR"
								value="    Send to HR" onclick="return callsendtoHR();" />

							<s:submit cssClass="delete" action="probationEvaluation_delete"
								value="    Delete" onclick="return confirmDeleteFun();" />

							<input type="button" class="token" value="  Report  "
								onclick="callReport('probationEvaluation_report.action');" />

						</s:if>

					</s:if> <s:if test="backBtnFlag">
						<s:submit cssClass="back" action="probationEvaluation_cancel"
							value="    Back " />

					</s:if> <s:else>
						<s:submit cssClass="back" action="ProbationConfirmation_back"
							value="    Back " />
						<input type="button" class="token" value="  Report  "
							onclick="callReport('probationEvaluation_report.action');" />

					</s:else> <s:if test="viewReportFlag">
						<input type="button" class="token" value="  Report  "
							onclick="callReport('probationEvaluation_report.action');" />
					</s:if></td>
					<td width="22%"></td>
				</tr>
			</table>
			<label></label></td>
		</tr>
	</table>
</s:form>
<script>
checkVal();

function confirmDeleteFun()
{
	 var conf=confirm("Are you sure !\n You want to delete this record ?");
  				if(conf){
  					return true;
  				}else{
  				return false;
  				}
}
function checkVal()
{
	/*if(document.getElementById('recommendation').value=='E')
	{	
	document.getElementById('extndProbationDaysId').style.display='inline';
	}
	else
	document.getElementById('extndProbationDaysId').style.display = 'none';
	
	*/
	
	
	
	document.getElementById('confirmId').style.display = 'none';
	document.getElementById('terminatedId').style.display = 'none';
	document.getElementById('extndProbationDaysId').style.display = 'none';
	
	
	if(document.getElementById('recommendation').value == 'T'){
		document.getElementById('terminatedId').style.display = 'inline';
		document.getElementById('extndProbationDaysId').style.display = 'none';
		document.getElementById('confirmId').style.display = 'none';
		
	}
	if(document.getElementById('recommendation').value == 'C'){
		document.getElementById('terminatedId').style.display = 'none';
		document.getElementById('extndProbationDaysId').style.display = 'none';
		document.getElementById('confirmId').style.display = 'inline';
		
	}
	if(document.getElementById('recommendation').value == 'E'){
		document.getElementById('terminatedId').style.display = 'none';
		document.getElementById('extndProbationDaysId').style.display = 'inline';
		document.getElementById('confirmId').style.display = 'none';
		
	}
}
function callsendtoHR()
{
	 var conf=confirm("Are you sure !\n You want to send to HR this record ?");
  				if(conf){
  					document.getElementById('paraFrm_evalstatus').value='H';
  					return true;
  				}else{
  				return false;
  				}
	
}
function validateForm()
{
	var evaluationDate=document.getElementById('paraFrm_evaluationDate').value;
	var length = document.getElementById("count").value;
	var recommnd=document.getElementById('recommendation').value;
	if(evaluationDate=="")
	{
		alert("Please select/enter evaluation date");
		return false;
	}
	if(!evaluationDate=="")
		{
			 var check= validateDate('paraFrm_evaluationDate', 'evaluationDate');
			if(!check){
			return false;
		}
		}
	if(recommnd=='-1')
	{
		alert("Please select recommendation" );
		return false;
	}
	if(recommnd=='E')
	{
		if(document.getElementById('paraFrm_extendedProbationDays').value=="")
		{
			alert("Please enter extend probation days ");
			return false;
		}
	}
	if(recommnd=='C')
	{
		var confirmDate=document.getElementById('paraFrm_confirmDate').value;
			 if(confirmDate=="")
			{
				alert("Please select/enter confirmation date");
				return false;
			}
		if(!confirmDate=="")
			{
				 var check= validateDate('paraFrm_confirmDate', 'confirmdt');
				if(!check){
				return false;
			}
		}
	}
	if(recommnd=='T')
	{
			var terminationDate=document.getElementById('paraFrm_terminationDate').value;
			if(terminationDate=="")
			{
				alert("Please select/enter termination date");
				return false;
			}
		if(!terminationDate=="")
			{
				 var check= validateDate('paraFrm_terminationDate', 'terminateddt');
				if(!check){
				return false;
			}
		}
	}
	
	document.getElementById('paraFrm_evalstatus').value='D';
	try{
	//alert("length = "+length);
	for(var i=1;i<=length;i++){
		var charLimit =  document.getElementById('paraFrm_descCnt'+i).value;
		var comment = trim(document.getElementById('comment'+i).value);
		//alert('comment	:'+eval(comment.length));
		//alert('charLimit1	:'+eval(charLimit));
		if(eval(comment.length) > 500){
			alert("Maximum length of "+ document.getElementById('comment').innerHTML.toLowerCase()+" is 500 characters.");
			document.getElementById('comment'+i).focus();
			return false;
		}else{
		//alert('in else');
			//return true;
		}
	}
	return true;
	}catch(e){
	//alert(e);
	}
	return true;
}
	function callValidate()
{
	var count;
	var myCount = <%= --s %>;
	var idName;
	var idValue;
	var code = document.getElementById('paraFrm_intques_empId').value; 
	
	var excode=document.getElementById('paraFrm_intques_exIntcode').value;
	var queFlag=document.getElementById('paraFrm_QuesDtlflag').value;
	var qd=document.getElementById('paraFrm_quedetails').value;
	var length = document.getElementById("count").value;
	
	if(code=="")
	{
		alert('Please select the employee record');
		return false;
	}
/*	for(count = 1; count <= eval(myCount); count++)
	{
		idValue  = document.getElementById("h"+count).value;
		if(idValue == "")
			{
				alert("Please Select the Rating");
				document.getElementById("h"+count).focus();
				return false;
			}
	}*/
		
	if(qd=="0")
	{
		alert("There are no candidate question details , So you cannot fill the exit interview questionnaire!");
		return false;
	}
	try{
	for(var i=1;i<=length;i++){
		var charLimit =  document.getElementById('paraFrm_descCnt'+i).value;
		var comment = trim(document.getElementById('comment'+i).value);
		alert('comment	:'+eval(comment.length));
		alert('charLimit1	:'+eval(charLimit));
		if(eval(comment.length) > 500){
			alert("Maximum length of "+ document.getElementById('comment').innerHTML.toLowerCase()+" is 500 characters.");
			document.getElementById('comment'+i).focus();
			return false;
		}else{
		alert('in else');
			return true;
		}
	}
	}catch(e){
	//alert(e);
	}
	
	return true;
}
 function callLength(type,maxLenght){
  			var count = type;
  			var lengthType=count.replace("comment","");
			var cmt = document.getElementById(type).value;
			var remain = eval(maxLenght) - eval(cmt.length);
			document.getElementById('paraFrm_descCnt'+lengthType).value = remain;
			if(eval(remain)< 0){
				document.getElementById(type).style.background = '#FFFF99';
			}
			else 
				document.getElementById(type).style.background = '#FFFFFF';
	}
</script>