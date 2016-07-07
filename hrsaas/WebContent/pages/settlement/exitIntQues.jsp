<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../common/commonValidations.jsp"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<link rel="stylesheet" type="text/css"
	href="../pages/common/tabcontent.css" />
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>

<s:form action="ExitInterview" method="post" name="ExitInterview"
	validate="true" id="paraFrm" theme="simple">

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
					<td width="93%" class="txt"><strong class="text_head">Exit
					Interview Questionnaire</strong></td>
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
					<td width="78%"><s:if test="viewFlag">
						<input type="button" class="search"
							onclick="javascript:callsF9(500,325,'ExitInterview_f9action.action');"
							value="    Search " />
					</s:if> <s:if test="%{intques.insertFlag}">
						<s:submit cssClass="add" action="ExitInterview_save"
							value="    Save" onclick="return callValidate();" />

					</s:if> <s:elseif test="%{intques.updateFlag}">
						<s:submit cssClass="add" action="ExitInterview_save"
							value="    Save" onclick="return callValidate();" />
					</s:elseif> <s:else></s:else> <s:submit cssClass="reset"
						action="ExitInterview_reset" value="    Reset" /> <s:if
						test="%{intques.deleteFlag}">
						<s:submit cssClass="delete" action="ExitInterview_delete"
							value="    Delete"
							onclick="return callDelExit('paraFrm_intques_exIntcode');" />
					</s:if><s:else></s:else> <s:if test="%{intques.viewFlag}">
						<input type="button" class="token" value="  Report  "
							onclick="callReport('ExitInterview_report.action');" />
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
								class="forminnerhead">Exit Interview Questionnaire</strong></td>
						</tr>

						<tr>
							<td width="100%" colspan="3">&nbsp;</td>
						</tr>

						<tr>
							<td width="15%" colspan="1"><label name="employee"
								id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
							:</td>
							<td width="20%" colspan="6"><s:textfield
								name="intques.empToken" size="12" value="%{empToken}"
								readonly="true" /> <s:textfield name="intques.empName"
								size="84" value="%{empName}" readonly="true" /> <s:hidden
								name="intques.exIntcode" /> <s:hidden name="intques.empId"
								value="%{empId}"></s:hidden></td>
						</tr>
						<tr>
							<td width="15%" colspan="1"><label name="branch" id="branch"
								ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
							:</td>
							<td width="25%" colspan="1"><s:textfield
								name="intques.branch" value="%{branch}" theme="simple" size="25"
								readonly="true" /></td>
							<td width="20%" colspan="1"><label name="designation"
								id="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
							:</td>
							<td width="30%" colspan="3"><s:textfield
								name="intques.designation" value="%{designation}" theme="simple"
								size="25" readonly="true" /></td>
						</tr>
						<tr>
							<td width="15%" colspan="1"><label name="resigndate"
								id="resigndate" ondblclick="callShowDiv(this);"><%=label.get("resigndate")%></label>
							:</td>
							<td width="25%" colspan="1"><s:textfield
								name="intques.resignDate" value="%{resignDate}" theme="simple"
								size="25" readonly="true" /></td>
							<td width="20%" colspan="1"><label name="seperationdate"
								id="seperationdate" ondblclick="callShowDiv(this);"><%=label.get("seperationdate")%></label>
							:</td>
							<td width="30%" colspan="3"><s:textfield
								name="intques.sepDate" value="%{sepDate}" theme="simple"
								size="25" readonly="true" /></td>
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
								name="intques.joinDate" value="%{joinDate}" theme="simple"
								size="25" readonly="true" /></td>
							<td width="20%" colspan="1"><label name="exitinterdate"
								id="exitinterdate" ondblclick="callShowDiv(this);"><%=label.get("exitinterdate")%></label>
							:</td>

							<td width="30%" colspan="3"><s:hidden
								name="intques.resignId" /><s:hidden name="intques.resignCode" />
							<s:textfield name="intques.leaveDate" value="%{leaveDate}"
								theme="simple" size="25" readonly="true" /><s:a
										href="javascript:NewCal('paraFrm_intques_leaveDate','DDMMYYYY');">
										<img src="../pages/images/Date.gif" class="iconImage"
											height="16" align="absmiddle" width="16">
									</s:a> <s:hidden
								name="intques.isInterviewed" /></td>
						</tr>

					</table>
					</td>
				</tr>

				<tr>
					<td width="100%" colspan="3">&nbsp;</td>
				</tr>
				<tr>

					<td>
					<table class="formbg" width="100%">
						<tr>
							<td colspan="3" class="formhead"><strong
								class="forminnerhead">Candidate Question</strong></td>
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
								<s:iterator value="intques.tableList">
									<td width="50%" class="sortableTD"><s:hidden
										name="questionCode" /> <s:property value="question" /></td>

									<td class="sortableTD" width="10%" nowrap="nowrap"><s:if
										test="flag">
										<s:select name="rating" cssStyle="width:100" id='<%="h"+p%>'
											list="tmap" />
									</s:if> <s:else>

										<s:select name="rating" cssStyle="width:100" headerKey=""
											headerValue="Select Rating" id='<%="h"+p%>'
											list="tmap" size="1" />
									</s:else></td>
									<td class="sortableTD" width="40%" nowrap="nowrap"><s:textarea
										name="comment" id='<%="comment"+p%>' rows="3" cols="40"
										onkeyup="callLength(id,'500');" > </s:textarea> <img
										src="../pages/images/zoomin.gif" height="12" align="absmiddle"
										width="12" theme="simple"
										onclick="javascript:callWindow('<%="comment"+p%>','comment','','paraFrm_descCnt<%=p%>','500');">
									&nbsp;Remaining chars <input type="text" name="descCnt"
										id='paraFrm_descCnt<%=p%>' readonly="true" size="5" />
										
										<input type="hidden" value='<s:property value="comment"/>' />
										
										</td>
									<s:hidden name="code" />

									</tr>
									<%
									p++;
									%>

								</s:iterator>
								<input type="hidden" name="count" id="count" value="<%=p%>"/>
								<%
																	s = p;
																	p = 1;
																%>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<script>
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
		if(eval(comment.length) > 500){
			alert("Maximum length of "+ document.getElementById('comment').innerHTML.toLowerCase()+" is 500 characters.");
			document.getElementById('comment'+i).focus();
			return false;
		}
	}
	}catch(e){
	//alert(e);
	}
	
	return true;
}

function callDelExit(id){
	var excode=document.getElementById('paraFrm_intques_exIntcode').value;
	if(excode=="")
	{
		alert('Please select the employee record');
		return false;
	}
	else if(trim(excode.toString())=="null")
	{
	alert('Please save the record first');
		return false;
	}
	
	var conf=confirm("Do you really want to delete this record ?");
	if(conf) 
	{
		return true;
	}
	else
	{
		 return false;
	}
	
	
 }

function callReport(id){
	var excode=document.getElementById('paraFrm_intques_exIntcode').value;
	if(excode=="")
	{
		alert('Please select the employee record');
		return false;
	}
	else if(trim(excode.toString())=="null")
	{
	alert('Please save the record first');
		return false;
	}
	
	document.getElementById('paraFrm').target="_blank";
  	document.getElementById('paraFrm').action=id;
	document.getElementById('paraFrm').submit();  
    document.getElementById('paraFrm').target="main";
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
	
	//callWindow(id,'comment','','paraFrm_descCnt<%=p%>','500')
	function callWindow(fieldName,windowName,readFlag,charCount,maxLength) {
		   	mytitle=document.getElementById(windowName).innerHTML.toLowerCase();
			window.open('../pages/common/popupTextArea.jsp?fieldName='+fieldName+'&windowName='+mytitle+'&charCntName='+charCount+'&maxLength='+maxLength+'&readFlag='+readFlag+'','','width=500,height=430,scrollbars=no,resizable=no,top=250,left=350');
			document.getElementById('paraFrm').target ="main";	 
	}

</script>

