<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="java.util.HashMap"%>
<s:form action="RegularizationList" method="post" name="LeavePolicy"
	id="paraFrm" theme="simple" target="main">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<s:hidden name="month" />
		<s:hidden name="year" />
		<s:hidden name="applyFor" />
		<tr>
			<td colspan="6">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong>Late
					Regularization </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="5"><input name="button" type="button" class="back"
				onclick="callBack()" value="   Back" /></td>
			<td width="17%" align="center"><input name="button4"
				type="button" class="addnew" onclick="callApply()" value="  Apply" />
			</td>
		</tr>
		<tr>
			<td colspan="6">
			<table width="100%" border="0" cellpadding="1" cellspacing="0"
				class="formbg">
				<tr>
					<td width="9%" class="formth">Sr No.</td>
					<td width="25%" class="formth">Date</td>
					<td width="15%" class="formth">Shift Time</td>
					<td width="15%" class="formth">In Time</td>
					<td width="15%" class="formth">Late Hrs</td>
					<td width="21%" class="formth"><s:checkbox name="applyAll"
						id="applyAll" onclick="callCheckAll()"></s:checkbox></td>
				</tr>
				<%int i=0; %>
				<%! int t=0; %>
				<s:iterator value="list">
					<tr>
						<td width="9%" align="center"><%=i+1%></td>
						<td width="25%" align="center"><s:property value="date" /></td>
						<td width="15%" align="center"><s:property value="shiftTime" /></td>
						<td width="15%" align="center"><s:property value="inTime" /></td>
						<td width="15%" align="center"><s:property value="lateHrs" /></td>
						<td width="21%" align="center"><label> <input
							type="checkbox" name="lateCheckBox" id="<%="check"+i%>"
							value='<s:property value="date"/>' /> </label></td>
					</tr>
					<%i++; %>
				</s:iterator>
				<%t=i; %>

				<%if(t==0){ %>
				<tr>
					<td colspan="6" align="center"><font color="red"> There
					in no data to display</font></td>
				</tr>
				<%} %>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="5"><input name="button" type="button" class="back"
				onclick="callBack()" value="   Back" /></td>
			<td width="17%" align="center"><input name="button4"
				type="button" class="addnew" onclick="callApply()" value="  Apply" />
			</td>
		</tr>
	</table>
</s:form>

<script>
			function callBack(){		
		document.getElementById('paraFrm').target="main";
		document.getElementById('paraFrm').action="Regularization_input.action";
		document.getElementById('paraFrm').submit();		
		}
		
		function callApply(){	
		var tot='<%=t%>';
		var count='0';
		for(var i=0; i<tot-1;i++){
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
		for(var i=0; i<tot-1;i++){		
		document.getElementById('check'+i).checked=true;
		}
		}	
		else{
		for(var i=0; i<tot-1;i++){		
		document.getElementById('check'+i).checked=false;
		}
		}	
		}
</script>