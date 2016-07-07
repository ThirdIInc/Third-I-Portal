<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<link rel="StyleSheet"
	href="../pages/dtree/dtree.css"
	type="text/css" />

<script type="text/javascript"
	src="../pages/common/dtree/dtree.js"></script>
<script type="text/javascript"
	src="../pages/common/dtree/vertdtree.js"></script>
<s:form action="EmpHierarchy" id="paraFrm" validate="true"
	theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" align="left">
				<tr>
					<td width="3%" colspan="1" class="txt"><strong
						class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="20"
						height="20" /></strong></td>
					<td width="97%" colspan="2" class="txt" align="left"><strong
						class="text_head">Employee Wise Hierarchy </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- <tr>
			<td valign="bottom" class="txt"><strong class="formhead"><img
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="formhead">Employee
			Wise Hierarchy </strong></td>
			<td width="3%" valign="top" class="txt">
			<div align="right"><img
				src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
			</td>
		</tr>-->
		<tr>
			<td width="100%">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				align="left">
				<tr>
					<td width="85%" colspan="1" align="left"><s:if
						test="%{emphr.viewFlag}">
						<s:submit cssClass="token" value="   Show hierarchy"
							action="EmpHierarchy_create" onclick="return showValidation();" />
					</s:if></td>
					<td width="15%" colspan="1" height="28" align="left">Export:<img
						src="../pages/images/buttonIcons/file-xls.png" align="absmiddle" class="iconImage"
						onclick="return exportReport();" title="Excel"></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" class="formbg">
				<tr>
					<td colspan="1" align="left"><s:checkbox name="emphr.flag" /> <label
						class="set" name="show" id="show" ondblclick="callShowDiv(this);"><%=label.get("show")%></label></td>
				</tr>
				<tr>
					<td width="10%" align="left"><label class="set" name="employee"
						id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:</td>
					<td width="40%" align="left"><s:textfield name="emphr.empToken"
						value="%{emphr.empToken}" size="10" readonly="true" /> <s:textfield
						name="emphr.empName" value="%{emphr.empName}" size="45"
						readonly="true" /><s:hidden name="emphr.empID"
						value="%{emphr.empID}" /> <s:if test="%{emphr.generalFlag}"></s:if>
					<s:elseif test="%{emphr.viewFlag}">

						<img src="../pages/images/recruitment/search2.gif"
							class="iconImage" height="18" align="absmiddle" width="18"
							onclick="javascript:callsF9(500,325,'EmpHierarchy_f9action.action');" />
					</s:elseif></td>
				</tr>
				<%
				try {
				%>
				<%
						String[][] twoDimObjArr = (String[][]) request
						.getAttribute("empList");

						if (twoDimObjArr != null && twoDimObjArr.length > 0) {
				%>
			</table>
			<table width="100%" align="left" class="formbg">
				<%
				if (twoDimObjArr != null) {
				%>
				<tr>
					<td><s:if test="%{flag}">
						<table width="100%" align="left" class="formbg">
							<tr>
								<td colspan="1"><font color="red">*</font>Note:- Red color
								indicates employee status Resigned.</td>
								<s:hidden name="emphr.hiddensub" />
							</tr>
						</table>
					</s:if></td>
				</tr>
				<%
				}
				%>
				<tr>
					<td><a href="javascript:mytree.openAll();">open all</a> | <a
						href="javascript:mytree.closeAll();">close all</a></td>
				</tr>
				<tr>
					<td><script type="text/javascript">
				mytree = new dTree('mytree');
			</script> <script type="text/javascript">
		
				mytree.add('<%=twoDimObjArr[0][0]%>','-1','<%=twoDimObjArr[0][2]%>');
			</script> <%
 			;
 			if (twoDimObjArr != null) {
 		for (int i = 1; i < twoDimObjArr.length; i++) {
 			//System.out.println("ddddddddddddd"+twoDimObjArr[i][0]);
 %> <script type="text/javascript">
			mytree.add('<%=twoDimObjArr[i][0] %>','<%=twoDimObjArr[i][1] %>','<%=twoDimObjArr[i][2] %>');
		
	</script> <%
 			}
 			}
 %><script type="text/javascript">
		document.write(mytree);
		mytree.o(0);
	</script></td>
				</tr>
				<%
					}
					} catch (Exception e) {
						e.printStackTrace();
					}
				%>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script>
 
callShow('idDiv');

function callShow(id){
 		document.getElementById(id).style.display='none';
    	document.getElementById('paraFrm_emphr_hiddensub').value='';
	}
	
function showValidation(){
	var empName=document.getElementById('employee').innerHTML.toLowerCase();
	var val=document.getElementById('paraFrm_emphr_empToken').value;
		if(val==""){
	  	alert("Please select "+empName);
	 	  return false;
	}
}

function exportReport(){
	var empName=document.getElementById('employee').innerHTML.toLowerCase();
	var val=document.getElementById('paraFrm_emphr_empToken').value;
	if(val==""){
	  	alert("Please select "+empName);
	 	  return false;
	}else{	
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action ='EmpHierarchy_report.action';
		document.getElementById('paraFrm').submit()
	}
}
</script>