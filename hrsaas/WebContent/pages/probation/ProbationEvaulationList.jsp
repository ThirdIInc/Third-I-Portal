<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@include
	file="/pages/ApplicationStudio/configAuth/authorizationChecking.jsp"%>
<script language="JavaScript" type="text/javascript"
	src="include/javascript/sorttable.js"></script>
<s:form action="probationEvaluation" validate="true" id="paraFrm"
	target="main" theme="simple">
	<s:hidden name="selectemployeetoken" />
	<s:hidden name="selectemployeeName" />
	<s:hidden name="hiddenempcode" />
	<s:hidden name="hiddenprobationcode" />
	<s:hidden name="evalcode" />
	<s:hidden name="duedateconf" />

	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<tr>
			<s:hidden name="show" />
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
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
			<td colspan="3"><input type="button" name="Add" class="add"
				value="Add New" onclick="return callSearch();" /></td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="2">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="99%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td height="27" class="formtxt"><a
										href="probationEvaluation_input.action?status=P">Pending
									Evaluation List</a> | <a
										href="probationEvaluation_input.action?status=H">Evaluated
									List</a></td>
								</tr>
							</table>
							</td>
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
					<td width="30%" class="formtxt"><strong> <%
 	//String status = (String) request.getAttribute("stat");
						String status = (String) request.getParameter("status")!=null?request.getParameter("status"):"";
 	if (status.equals("H")) {
 		out.println("Completed Evaluated List");
 	} else {
 		out.println("Pending Evaluation List");
 	}
 %> </strong></td>
					<td id="ctrlShow" width="100%" align="right"><b>Page:</b> <%
 	int totalPage = (Integer) request.getAttribute("totalPage");
 	int pageNo = (Integer) request.getAttribute("pageNo");
 	//int totalPage =0;
 		//int pageNo =0;
 %> <a href="#"
						onclick="callPage('1', 'F', '<%=totalPage%>', 'probationEvaluation_callPage.action');">
					<img title="First Page" src="../pages/common/img/first.gif"
						width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
						onclick="callPage('P', 'P', '<%=totalPage%>', 'probationEvaluation_callPage.action');">
					<img title="Previous Page" src="../pages/common/img/previous.gif"
						width="10" height="10" class="iconImage" /> </a> <input type="text"
						name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
						maxlength="10"
						onkeypress="callPageText(event, '<%=totalPage%>', 'probationEvaluation_callPage.action');return numbersOnly();" />
					of <%=totalPage%> <a href="#"
						onclick="callPage('N', 'N', '<%=totalPage%>', 'probationEvaluation_callPage.action')">
					<img title="Next Page" src="../pages/common/img/next.gif"
						class="iconImage" /> </a>&nbsp; <a href="#"
						onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'probationEvaluation_callPage.action');">
					<img title="Last Page" src="../pages/common/img/last.gif"
						width="10" height="10" class="iconImage" /> </a></td>
					<!-- PAGING STARTS-->

				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="sortable">
				<tr class="td_bottom_border">
					<s:hidden name="myPage" id="myPage" />
					<td width="10%" valign="top" class="formth" nowrap="nowrap"><label
						name="srno" id="srno" ondblclick="callShowDiv(this);">Sr
					No.</label></td>
					<td width="20%" valign="top" class="formth" nowrap="nowrap"><label
						name="empToken" id="empToken" ondblclick="callShowDiv(this);">Employee
					Token</label></td>
					<td width="35%" valign="top" class="formth"><label
						name="empName" id="empName" ondblclick="callShowDiv(this);">Name</label></td>
					<td width="20%" valign="top" class="formth"><label name="doj"
						id="doj" ondblclick="callShowDiv(this);"><%=label.get("doj")%></label></td>
					<td width="20%" valign="top" class="formth"><label name="doj"
						id="doj" ondblclick="callShowDiv(this);">Due Date</label></td>

					<s:if test="confirmFlag">
						<td width="20%" valign="top" class="formth" nowrap="nowrap"><label
							name="confirmdt" id="confirmdt" ondblclick="callShowDiv(this);"><%=label.get("confirmdt")%></label></td>
					</s:if>


					<td width="10%" valign="top" class="formth" nowrap="nowrap">
					Evaluation</td>
				</tr>
				<s:if test="noData">
					<tr>
						<td width="100%" colspan="7" align="center"><font color="red">No
						Data To Display</font></td>
					</tr>
				</s:if>
				<%
					int y = 1;
					%>
				<%!int z = 0;%>

				<!-- For paging -->
				<%
					int count = 0;
					%>
				<%!int d = 0;%>

				<%
						int i = 0;
						int cn = pageNo * 20 - 20;
					%>
				<s:iterator value="list">
					<tr class="sortableTD">
						<td width="10%" align="left" class="sortableTD"><%=++cn%> <s:hidden
							name="ittrevalCode" /><s:hidden name="ittprobationStatus" /></td>
						<td class="sortableTD" width="20%"><s:property
							value="empToken" /><s:hidden name="employeeId" /></td>
						<td class="sortableTD" width="35%"><s:property
							value="empName" /></td>
						<td class="sortableTD" width="20%" align="center"><s:property
							value="dateOfJoining" /></td>
						<td class="sortableTD" width="20%" align="center"><s:property
							value="dueDate" /></td>

						<s:if test="confirmFlag">
							<td class="sortableTD" width="20%" align="center"><s:property
								value="dateOfConfirm" /></td>
						</s:if>
						<s:if test="extenProbFlag">
							<td class="sortableTD" width="20%" align="center"><s:property
								value="extendedProbationDate" /></td>
						</s:if>
						<s:if test="terminatedFlag">
							<td class="sortableTD" width="20%" align="center"><s:property
								value="dateOfTermination" /></td>
						</s:if>
						<%
					if (status.equals("H"))
					{
					%>
						<td class="sortableTD" width="10%"><input type="button"
							class="token" value="View"
							onclick="viewRecord('<s:property value="employeeId"/>','<s:property value="ittrevalCode"/>','<%=status%>','<s:property value="dueDate" />');" /></td>
						<%}else
						{
						%>
						<td class="sortableTD" width="10%"><input type="button"
							class="token" value="Process"
							onclick="viewDetails('<s:property value="employeeId"/>','<s:property value="ittrevalCode"/>','<%=status%>','<s:property value="dueDate" />');" /></td>

						<%} %>

					</tr>
					<%
						y++;
						%>
				</s:iterator>
				<%
					z = y;
					%>
			</table>
			</td>
		</tr>
	</table>

</s:form>
<script>

	function callSearch() {
		var myWinDiv = window.open('', 'myWinDiv', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv';
		document.getElementById("paraFrm").action = 'probationEvaluation_f9Allemployee.action';
		document.getElementById("paraFrm").submit();
	}
	function viewRecord(empCode,probCode,status,duedate){
	
	try
	{
	// alert("aa");
	// alert("empCode----------"+empCode);
	// alert("probCode----------------"+probCode);
	// alert("status----------------"+status);
	//document.getElementById('paraFrm_hiddenempcode').value=empCode;
//	document.getElementById('paraFrm_evalcode').value=probCode;
	//document.getElementById('paraFrm_duedateconf').value=duedate;
	
	
	
	
			document.getElementById('paraFrm').action = 'probationEvaluation_viewRecord.action?empCode='+empCode+'&backBtnstatus=A&probCode='+probCode;
			document.getElementById('paraFrm').submit();
		
	}catch(e){ alert(e); }
	
	}
	function viewDetails(empCode,probCode,status,duedate){
	
	try
	{
	// alert("aa");
	// alert("empCode----------"+empCode);
	// alert("probCode----------------"+probCode);
	// alert("status----------------"+status);
	document.getElementById('paraFrm_hiddenempcode').value=empCode;
	document.getElementById('paraFrm_evalcode').value=probCode;
	document.getElementById('paraFrm_duedateconf').value=duedate;
	
			document.getElementById('paraFrm').action = 'probationEvaluation_processEvaluation.action';
			document.getElementById('paraFrm').submit();
		
	}catch(e){ alert(e); }
	
	}
</script>