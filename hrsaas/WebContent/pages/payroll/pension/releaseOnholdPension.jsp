<!-- @author: REEBA_JOSEPH _ 25 OCTOBER 2010 -->
<%@include file="/pages/common/labelManagement.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	var records = new Array();
</script>
<s:form action="ReleaseOnHold" validate="true" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Release
					OnHold Pensions </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">

				<tr>
					<td><s:submit cssClass="reset" action="ReleaseOnHold_reset" value=" Reset"/> 
					<input type="button" class="save" onclick="saveFun()" value=" Save"/></td>
					<td width="22%">
					<div align="right"><font color="red">*</font>Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="5">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td width="20%"><label class="set" id="month" name="month"
						ondblclick="callShowDiv(this);"><%=label.get("month")%></label> <font
						color="red">*</font> :</td>
					<td width="30%"><s:select theme="simple" name="month"
						cssStyle="width:155"
						list="#{'0':'-- Select --','1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" /></td>
					<td></td>
					<td width="20%"><label class="set" id="year" name="year"
						ondblclick="callShowDiv(this);"><%=label.get("year")%></label> <font
						color="red">*</font> :</td>
					<td width="30%"><s:textfield name="year" size="25"
						maxlength="4" onkeypress="return numbersOnly();" />
				</tr>
				
				<tr>
					<td width="20%"><label class="set" id="division" name="division"
						ondblclick="callShowDiv(this);"><%=label.get("division")%></label> <font
						color="red">*</font> :</td>
					<td width="30%"><s:hidden name="divCode" /> <s:textfield
						name="divName" size="25" theme="simple" readonly="true" />
						<img src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="17"
						onclick="javascript:callsF9(500,325,'ReleaseOnHold_f9Div.action');"></td>
				</tr>

				<tr>
					<td align="center" colspan="5"><input type="button"
						value="View Employees" class="token" title="View Employees"
						onclick="callViewEmployees()" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<s:hidden name="viewEmp" /><s:hidden name="recordsAvailable" />
		<s:if test="viewEmp">
			<tr>
				<td colspan="3" width="100%">
				<table class="formbg" width="100%" cellspacing="0" cellspacing="1"
					border="0">
					<tr>
						<td colspan="2" class="txt"><strong class="text_head">Employee
						List</strong></td>
					</tr>
					<tr>
						<td width="100%" class="formbg" colspan="3">
						<table width="100%" class="sortableTD">
							<tr>
								<td align="center" class="formth"><b><label
									name="serial.no" id="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b>
								</td>
								<td align="center" class="formth"><b><label
									name="employee.id" id="employee.id"
									ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></b>
								</td>
								<td align="center" class="formth"><b><label
									name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></b>
								</td>
								<td class="formth" align="center"><label name="paid.month"
									id="paid.month" ondblclick="callShowDiv(this);"><%=label.get("paid.month")%></label></td>
								<td class="formth" align="center"><label name="paid.year"
									id="paid.year" ondblclick="callShowDiv(this);"><%=label.get("paid.year")%></label></td>
								<td class="formth" align="center"><label name="paid"
									id="paid" ondblclick="callShowDiv(this);"><%=label.get("paid")%></label></td>
							</tr>
							<s:if test="recordsAvailable">
								<%!int d = 0;%>
								<%
									int i = 0;
									int cn = 0;
								%>
								<s:iterator value="empList">
									<tr onmouseover="javascript: newRowColor(this);"
										onmouseout="javascript: oldRowColor(this);"
										ondblclick="javascript: callForEdit('<s:property value="typeID"/>');"
										style="cursor: hand;">
										<td title="Double click for edit" width="10%"
											class="sortableTD" align="center"><%=++cn%> <%
										 ++i;
										 %></td>
										<s:hidden  name="empId" />
										
										<td title="Double click for edit" width="20%"
											class="sortableTD"><s:property value="empToken" /> <input
											type="hidden" name="hdeleteCode" id="hdeleteCode<%=i%>" /></td>
										<td title="Double click for edit" width="40%"
											class="sortableTD"><s:property value="empName" /></td>
										<td class="sortableTD" width="20%"><s:select theme="simple" name="paidMonth"
											cssStyle="width:155"
											list="#{'0':'-- Select --','1':'January','2':'February','3':'March','4':'April','5':'May',
											'6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" />
											</td>
										<td class="sortableTD" width="20%"><s:textfield cssStyle="text-align: right"
											name="paidYear" maxlength="4" onkeypress="return numbersOnly();"/></td>
										<td class="sortableTD" width="10%"><input type="checkbox" class="checkbox" id="payCheck<%=i%>" 
											name="payCheck" onclick="callOnClick('<%=i%>')" /><input type="hidden" name="hiddenCode" 
											id="hiddenCode<%=i%>" />
											<s:hidden name="ledgerCode"/></td>

									</tr>
								</s:iterator>
								<%
								d = i;
								%>
							</s:if>
						</table>
						<s:if test="recordsAvailable"></s:if> <s:else>
							<table width="100%">
								<tr>
									<td align="center"><font color="red">No Data To
									Display</font></td>
								</tr>
							</table>
						</s:else></td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>

	</table>
</s:form>

<script>
//setCurrentYear('paraFrm_year');

function callViewEmployees(){
	try{
		var month =document.getElementById("paraFrm_month").value;
		var year =document.getElementById("paraFrm_year").value;
		var divId =document.getElementById("paraFrm_divCode").value;
		if(month=="0"){
			alert("Please select "+document.getElementById('month').innerHTML.toLowerCase());
			return false;
		}
		if(year==""){
			alert("Please enter "+document.getElementById('year').innerHTML.toLowerCase());
			return false;
		}
		if(divId==""){
			alert("Please enter "+document.getElementById('division').innerHTML.toLowerCase());
			return false;
		}
		document.getElementById('paraFrm').action="ReleaseOnHold_viewOnholdEmployees.action";	
		document.getElementById('paraFrm').submit();
	}catch(e){
		alert(e);
	}
}

function callOnClick(i) {
	var flag = '<%=d%>';
	if(document.getElementById('payCheck' + i).checked == true) {
		document.getElementById('hiddenCode' + i).value = "Y";
	} else {
		document.getElementById('hiddenCode' + i).value = "N";
	}
}

function saveFun() {
	if(chk()) {
	 	var con = confirm('Do you want to save the record(s)?');
	 	if(con) {
	 		document.getElementById('paraFrm').target = "_self";
	   		document.getElementById('paraFrm').action = "ReleaseOnHold_save.action";
	    	document.getElementById('paraFrm').submit();
		} else {	    
	    	var flag = '<%=d%>';
	  		for(var a = 1; a <= flag; a++) {	
				document.getElementById('payCheck' + a).checked = false;
				document.getElementById('hiddenCode' + a).value = "";
			}
	     	return false;
	 	}
	} else {
 		alert('Please select atleast one record');
 		return false;
 	}
}

function chk() {
	var flag = '<%=d%>';
  	for(var a = 1; a <= flag; a++) {
   		if(document.getElementById('payCheck' + a).checked == true) {
  			return true;
   		}
  	}
  	return false;
}

</script>