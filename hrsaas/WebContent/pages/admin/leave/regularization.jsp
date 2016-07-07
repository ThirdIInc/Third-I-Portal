 <%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.HashMap"%>

<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="Regularization" method="post" name="LeavePolicy"
	id="paraFrm" theme="simple" target="main">


	<table width="100%" border="0" align="center" cellpadding="1"
		cellspacing="1" class="formbg">
		<tr>
			<td colspan="6">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="100%" class="txt"><strong class="text_head">Regularization  
					Application</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%">
			<table width="100%">
				<tr>
					<td><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="6">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">
				<tr>
					<td colspan="6"></td>
				</tr>
				<tr>
					<td width="14%"><label class="set" name="month" id="month"
						ondblclick="callShowDiv(this);"><%=label.get("month")%> :
					</td>
					<td width="13%"><s:select name="month_old"
						list="#{'1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}">
					</s:select></td>
					<td width="8%"><label class="set" name="year" id="year"
						ondblclick="callShowDiv(this);"><%=label.get("year")%> :<font
						color="red">*</font></td>
					<td width="31%"><s:textfield name="year" size="10"
						maxlength="4" onkeypress="return numbersOnly();"></s:textfield></td>
					<td width="17%">&nbsp;</td>
					<td width="17%">&nbsp;</td>
				</tr>
				<tr>
					<td height="23" colspan="1" class="formtext"><label
						class="set" name="applied.for" id="applied.for"
						ondblclick="callShowDiv(this);"><%=label.get("applied.for")%>
					: </td>
					<td height="23" colspan="5"><s:hidden name="applyForName" />
					<s:select name="applyFor"
						list="#{'1':'Forgot Flash card','2':'Forgotten to Flash Flash','3':'Special Sanction','4':'Flash Card Not Issued','5':'Swipe System Not Working On My System','6':'Swipe System Not Loading','7':'Swipe System Capturing Incorrect Data','8':'Swipe System - Forget To LOGIN / LOGOUT','9':'Swipe System - Forget To Bring Access Card','10':'Swipe System - Others','LR':'Late Regularization','RR':'Redressal Application','PT':'Personal Time'}">
					</s:select></td>
				</tr>

				<!--  start  -->
			</table>

			</td>
		</tr>

		<s:if test="listFlag">
			<tr>
				<td colspan="6">
				<table width="100%" border="0" cellpadding="1" cellspacing="0"
					class="formbg">
					<tr>
						<td width="9%" class="formth"><label class="set" name="sno"
							id="sno" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label>
						</td>
						<td width="15%" class="formth"><label class="set" name="date"
							id="date" ondblclick="callShowDiv(this);"><%=label.get("date")%></label>
						</td>
						<td width="15%" class="formth"><label class="set"
							name="shift.time" id="shift.time" ondblclick="callShowDiv(this);"><%=label.get("shift.time")%></label>
						</td>
						<td width="15%" class="formth"><label class="set"
							name="in.time" id="in.time" ondblclick="callShowDiv(this);"><%=label.get("in.time")%></label>
						</td>

						<td width="10%" class="formth"><label class="set"
							name="late.hrs" id="late.hrs" ondblclick="callShowDiv(this);"><%=label.get("late.hrs")%></label>
						</td>
						<td width="10%" class="formth"><label class="set"
							name="early.hrs" id="early.hrs" ondblclick="callShowDiv(this);"><%=label.get("early.hrs")%></label>
						</td>

						<td width="15%" class="formth"><label class="set"
							name="total.hrs" id="total.hrs" ondblclick="callShowDiv(this);"><%=label.get("total.hrs")%></label>
						</td>
						<td width="10%" class="formth"><label class="set"
							name="status1" id="status1" ondblclick="callShowDiv(this);"><%=label.get("status1")%></label>
						</td>
						<td width="10%" class="formth"><label class="set"
							name="status2" id="status2" ondblclick="callShowDiv(this);"><%=label.get("status2")%></label>
						</td>
						<td width="16%" class="formth"><input name="button4"
							type="button" class="addnew" onclick="callApply()"
							value="  Apply" /> <s:checkbox name="applyAll" id="applyAll"
							onclick="callCheckAll()"></s:checkbox></td>
					</tr>


					<%int i=0; %>
					<%! int t=0; %>
					<s:iterator value="list">
						<tr>
							<td width="9%" align="center"><%=i+1%></td>
							<td width="15%" align="center"><s:property value="date" /></td>
							<td width="15%" align="center"><s:property value="shiftTime" /></td>
							<td width="15%" align="center"><s:property value="inTime" /></td>
							<td width="15%" align="center"><s:property value="lateHrs" /></td>
							<td width="15%" align="center"><s:property value="earlyHrs" /></td>
							<td width="15%" align="center"><s:property value="totalHrs" /></td>
							<td width="10%" align="center" nowrap="nowrap"><s:property
								value="status1" /></td>
							<td width="10%" align="center" nowrap="nowrap"><s:property
								value="status2" /></td>
							<td width="16%" align="center"><label> <input
								type="checkbox" name="lateCheckBox" id="<%="check"+i%>"
								value='<s:property value="date"/>' /> </label></td>
						</tr>
						<%i++; %>
					</s:iterator>
					<%t=i; %>

					<%if(t==0){ %>
					<tr>
						<td colspan="6" align="center"><font color="red">
						There in no data to display</font></td>
					</tr>
					<%} %>
				</table>
				</td>
			</tr>


		</s:if>
		<s:if test="redressalFlag">
			<tr>
				<td colspan="6">
				<table width="100%" border="0" cellpadding="1" cellspacing="0"
					class="formbg">
					<tr>
						<td width="9%" class="formth"><label class="set" name="sno"
							id="sno" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label>
						</td>
						<td width="15%" class="formth"><label class="set"
							name="leave.type" id="leave.type" ondblclick="callShowDiv(this);"><%=label.get("leave.type")%></label></td>
						<td width="15%" class="formth"><label class="set"
							name="from.date" id="from.date" ondblclick="callShowDiv(this);"><%=label.get("from.date")%></label></td>
						<td width="15%" class="formth"><label class="set"
							name="to.date" id="to.date" ondblclick="callShowDiv(this);"><%=label.get("to.date")%></label></td>
						<td width="15%" class="formth"><label class="set"
							name="penalty.days" id="penalty.days"
							ondblclick="callShowDiv(this);"><%=label.get("penalty.days")%></label>
						</td>
						<td width="16%" class="formth"><input name="button4"
							type="button" class="addnew" onclick="callRedressalApply()"
							value="  Apply" /> <s:checkbox name="applyAll" id="applyAll"
							onclick="applyRedressal()"></s:checkbox></td>
					</tr>
					<%int red=0; %>
					<%!int redCount=0; %>
					<s:iterator value="list">
						<tr>
							<td width="9%"><%=red+1 %></td>
							<td width="15%" align="center"><s:property value="leaveType" /><s:hidden
								name="rLeaveCode" /></td>
							<td width="15%" align="center"><s:property value="rFromDate" /></td>
							<td width="15%" align="center"><s:property value="rToDate" /></td>
							<td width="15%" align="center"><s:property
								value="rPenaltyDays" /></td>
							<td width="16%" align="center"><input type="checkbox"
								name="lateCheckBox" id="<%="check"+red%>"
								value='<s:property value="rFromDate"/>' /></td>
						</tr>
						<%red++; %>
					</s:iterator>
					<%redCount=red; %>
					<%if(redCount==0){ %>
					<tr>
						<td colspan="6" align="center"><font color="red">
						There in no data to display</font></td>
					</tr>
					<%} %>
				</table>
				</td>
			</tr>
		</s:if>

		<tr>
			<td width="100%">
			<table width="100%">
				<tr>
					<td><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td align="right"></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	
	 <s:hidden name="source" id="source" />
</s:form>

<script>
function backFun(){
	document.getElementById('paraFrm').target = "_self";
	
	 
	 	//this is for mypage back button
		if(document.getElementById('source').value=='mymessages')
		{
		document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
		}
		else if(document.getElementById('source').value=='myservices')
		{
		document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_serviceData.action';
		}
		else{
		document.getElementById('paraFrm').action = 'Regularization_input.action';
		}
		document.getElementById('paraFrm').submit();
	 
	
}

function viewrecordsFun(){
	var year=document.getElementById('paraFrm_year').value;
	if(trim(year)==''){
		alert('Please enter year');
		return false;
	}
	document.getElementById('paraFrm').target="main";
	document.getElementById('paraFrm').action="Regularization_showRegularizationList.action";
	document.getElementById('paraFrm').submit();	
}

function numbersonly(myfield)
{
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
	
function callApply(){		
		var tot='<%=t%>';
		var count='0';
		for(var i=0; i<tot;i++){
			if(document.getElementById('check'+i).checked){
				count=count+1;
			}
		}
		
		if(count=='0'){
			alert('Please select at least one check box');
			return false;
		}	
		document.getElementById('paraFrm').target="main";
		document.getElementById('paraFrm').action="Regularization_apply.action";
		document.getElementById('paraFrm').submit();		
}
		
function callCheckAll(){	
		var tot='<%=t%>';	
		if(document.getElementById('applyAll').checked){		
			for(var i=0; i<tot;i++){		
				document.getElementById('check'+i).checked=true;
			}
		}else{
			for(var i=0; i<tot;i++){		
				document.getElementById('check'+i).checked=false;
			}
		}	
}

function callRedressalApply(){	
	var tot='<%=redCount%>';		
	var count='0';
	for(var i=0; i<tot;i++){
		if(document.getElementById('check'+i).checked){
			count=count+1;
		}
	}
	
	if(count=='0'){
		alert('Please select at least one check box');
		return false;
	}	
	document.getElementById('paraFrm').target="main";
	document.getElementById('paraFrm').action="Regularization_apply.action";
	document.getElementById('paraFrm').submit();		
}


function applyRedressal(){		
	var tot='<%=redCount%>';	
	if(document.getElementById('applyAll').checked){		
		for(var i=0; i<tot;i++){		
			document.getElementById('check'+i).checked=true;
		}
	}else{
		for(var i=0; i<tot;i++){		
			document.getElementById('check'+i).checked=false;
		}
	}		
}
		
</script>