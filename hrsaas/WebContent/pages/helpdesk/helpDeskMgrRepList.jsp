<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="HelpDeskMgrReporting" validate="true" id="paraFrm" theme="simple">
<s:hidden name="myPage" id="myPage" />
<s:hidden  name="managerCode" />
<s:hidden  name="managerName" />
<s:hidden  name="reqType" />
<s:hidden  name="deptName" />
<s:hidden  name="reqTypeCode" />
<s:hidden  name="deptCode" />
	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		class="formbg">
		<tr>
			<td width="100%"  colspan="3">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Helpdesk Manager</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="70%">
			<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
			<%	int totalPage = 0;	int pageNo = 0;	%>
			<td width="30%" align="right" class="" id="ctrlShow"><b>Page:</b>
						<%
							totalPage = (Integer) request.getAttribute("totalPage");
							pageNo = (Integer) request.getAttribute("pageNo");
							
						%> <a
							onclick="callPage('1', 'F', '<%=totalPage%>', 'HelpDeskMgrReporting_input.action');"
							href="#"> <img height="10" width="10" class="iconImage"
							src="../pages/common/img/first.gif" title="First Page"> </a>&nbsp;
						<a
							onclick="callPage('P', 'P', '<%=totalPage%>', 'HelpDeskMgrReporting_input.action');"
							href="#"> <img height="10" width="10" class="iconImage"
							src="../pages/common/img/previous.gif" title="Previous Page">
						</a> <input type="text"
							onkeypress="callPageText(event, '1', 'HelpDeskMgrReporting_input.action');return numbersOnly();"
							maxlength="10" value='<%=pageNo%>' size="3" id="pageNoField"
							name="pageNoField"> of <%=totalPage%> <a
							onclick="callPage('N', 'N', '<%=totalPage%>', 'HelpDeskMgrReporting_input.action')"
							href="#"> <img class="iconImage"
							src="../pages/common/img/next.gif" title="Next Page"> </a>&nbsp;
						<a
							onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'HelpDeskMgrReporting_input.action');"
							href="#"> <img height="10" width="10" class="iconImage"
							src="../pages/common/img/last.gif" title="Last Page"> </a></td>
		</tr>
		<tr>
			<td class="formtext" colspan="3">
			<table width="100%" border="0" class="formbg" cellpadding="0" cellspacing="0">
				<tr>
					<td class="formth" width="10%" valign="top"><label class="set"
						id="serial.no1" name="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
					<td class="formth" width="25%" valign="top"><label class="set"
						id="manager1" name="manager" ondblclick="callShowDiv(this);"><%=label.get("manager")%></label></td>
					<td class="formth" width="20%" valign="top"><label class="set"
						id="dept" name="dept" ondblclick="callShowDiv(this);"><%=label.get("dept")%></label></td>
					<td class="formth" width="20%" valign="top"><label class="set"
						id="req.type1" name="req.type" ondblclick="callShowDiv(this);"><%=label.get("req.type")%></label></td>
					<td class="formth" align="center" >
					<input type="button" class="token"  value="Delete"	onclick="deleteMultiple();" /></td>
				</tr>
					<%	int count = 0;	
						int delCount = 1;
					%>
					<%!	int del = 0; %>
					<%	int i = 0; int cn = pageNo * 20 - 20; %>
				<s:iterator value="allRecordsList">
					<tr<%if(count%2==0){
									%> class="tableCell1" <%}else{%>
									class="tableCell2" <%	}count++; %>
									onmouseover="javascript:newRowColor(this);"
									title="Double click for edit"
									onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
									ondblclick="javascript:callForEdit('<s:property value="managerCodeItr" />', '<s:property value="deptCodeItr" />', '<s:property value="reqTypeCodeItr" />');">
						<td align="center" class="sortableTD"><%=++cn%></td>
						<td class="sortableTD">
						<s:hidden name="managerCodeItr" /> 
						<s:property value="managerNameItr" />&nbsp;</td>
						<td align="left" class="sortableTD">
						<s:hidden name="deptCodeItr" /> 
						<s:property value="deptNameItr" /></td>
						<td class="sortableTD">
						<s:hidden name="reqTypeCodeItr" /> 
						<s:property value="reqTypeItr" />&nbsp;</td>
						<td align="center" class="sortableTD" id="ctrlShow">
						<input type="hidden" name="hdeleteCode" id="hdeleteCode<%=delCount%>" />
						<input type="hidden" name="hdeleteDeptId" id="hdeleteDeptId<%=delCount%>" />
						<input type="hidden" name="hdeleteReqId" id="hdeleteReqId<%=delCount%>" />
						<input type="checkbox" name="checkedBox" id="checkedBox<%=delCount%>" 
						onclick="callForDelete('<%=delCount%>', '<s:property value="managerCodeItr"/>', '<s:property value="deptCodeItr"/>', '<s:property value="reqTypeCodeItr"/>');" /></td>
					</tr>
					<% delCount++;	%>
				</s:iterator>
				<%	del = delCount; %>
				<% if (cn == 0) {%>
					<tr align="center">
						<td colspan="7" class="sortableTD" width="100%"><font
											color="red">No Data to display</font></td>
					</tr>
				<% } else {	}%>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="5">
						<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
</s:form>

<script>
	function addnewFun() {
	
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'HelpDeskMgrReporting_addNew.action';
		document.getElementById('paraFrm').submit();
	}
	function searchFun() {
		if(navigator.appName == 'Netscape') {
			var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
		} else {
			var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		}
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action ='HelpDeskMgrReporting_f9action.action';
		document.getElementById("paraFrm").submit();
	}
	
	function newRowColor(cell){
		cell.className='onOverCell';
	}
	
	function oldRowColor(cell,val) {
		if(val=='1'){
	 		cell.className='tableCell2';
		}else {
			cell.className='tableCell1';
		}
	}
	
	function callForEdit(autoCode, deptCode, reqType){
		document.getElementById("paraFrm").target="main";
	 	document.getElementById("paraFrm").action="HelpDeskMgrReporting_callForEdit.action?autoCode="+autoCode+"&deptId="+deptCode+"&reqType="+reqType;
	    document.getElementById("paraFrm").submit();
		 
	}
	
	function callForDelete(i,id,deptId,reqType) {
		if(document.getElementById('checkedBox'+i).checked == true){
			document.getElementById('hdeleteCode'+i).value=id;
			document.getElementById('hdeleteDeptId'+i).value=deptId;
			document.getElementById('hdeleteReqId'+i).value=reqType;
		} else {
			document.getElementById('hdeleteCode'+i).value="";
			document.getElementById('hdeleteDeptId'+i).value="";
			document.getElementById('hdeleteReqId'+i).value="";
		}
	}
	
	function deleteMultiple() {
		var selected=false;
			var flag = <%=del%>;
			for(var check=1;check < flag;check++) {
				if( document.getElementById('checkedBox'+check).checked ){
					selected = true;
				}
			}
			if(selected){
				var con=confirm('Do you really want to delete the records ?');
	 				if(con){
	 					document.getElementById('paraFrm').target = "_self";
						document.getElementById("paraFrm").action="HelpDeskMgrReporting_deleteMultipleRecordsFromList.action";
						document.getElementById("paraFrm").submit();
					}
					}else{
						alert("Select atleast one record to delete");
						return false;
					}
	}
	
</script>
