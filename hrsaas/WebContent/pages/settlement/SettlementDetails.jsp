<!-- @Author: Reeba Joseph @Date:02 July 2009 -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Vector"%>
<%@page import="org.paradyne.lib.Utility"%>
<s:form action="SettlmentDetails" validate="true" id="paraFrm" theme="simple">
	<s:hidden name="myPage" id="myPage" />
	<s:hidden name="resignCode" value="%{resignCode}" />
	<s:hidden name="settCode" />
	<s:hidden name="settDtlCode" />
	<s:hidden name="settFlag" />
	<s:hidden name="lockFlag" />
	<s:hidden name="daysFlag" value="daysFlag" />
	<s:hidden name="empCode" />
	<s:hidden name="empToken" />
	<s:hidden name="empName" />
	<s:hidden name="resignDate" />
	<s:hidden name="settDate" />
	<s:hidden name="sepDate"/>
	<s:hidden name="decodedStatus" />
	<s:hidden name="noticePeriod" />
	<s:hidden name="noticePeriodStatus" />
	<s:hidden name="status" />
	<table width="100%" border="0" align="right" cellpadding="2" cellspacing="1" class="formbg">
		<tr>
			<td valign="bottom" class="txt" colspan="3">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td colspan="1"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt" colspan="1"><strong
						class="text_head">Settlement Details </strong></td>
					<td width="3%" valign="top" class="txt" colspan="1">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<input type="button" class="search" value=" Search"	onclick="callSearch();" /> 
			</td>
		</tr>
		<tr>
			<td colspan="3" class="txt">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"	>
				<tr>
					<td height="27" class="formtxt" colspan="3"><a
						href="SettlmentDetails_callSettlementList.action?status=P">Pending
					Settlement</a> | <a
						href="SettlmentDetails_callSettlementList.action?status=I">In
					Process Settlement</a> | <a
						href="SettlmentDetails_callSettlementList.action?status=F">Finalized
					Settlement</a></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" class="txt">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr class="formbg">
					<td width="20%" colspan="1"><strong> <%
					 	String status = "";
					 	try {
					 		status = (String) request.getAttribute("stat");
					 		if (status != null) {
					 			out.println(status);
					 		} else {
					 			out.println("Pending Settlement");
					 			status = "Pending Settlement";
					 		}
					 		System.out.println("########status..." + status);
					 	} catch (Exception e) {
					 		status = "Pending Settlement";
					 	}
					 %> </strong></td>
 					<%
						int totalPage = 0;
						int pageNo = 0;
					%>
					
					<!-- Paging implementation -->
					<td id="showCtrl" width="70%" align="right" colspan="1">
					<s:if test="modeLength">
					<%
						totalPage = (Integer) request.getAttribute("totalPage");
						pageNo = (Integer) request.getAttribute("pageNo");
					%>
						<b>Page:</b>
						<a href="#"
							onclick="callPage('1', 'F', '<%=totalPage%>', 'SettlmentDetails_callSettlementList.action?status=<%=status %>');">
						<img title="First Page" src="../pages/common/img/first.gif"
							width="10" height="10" class="iconImage" /> </a>&nbsp;
						<a href="#"
							onclick="callPage('P', 'P', '<%=totalPage%>', 'SettlmentDetails_callSettlementList.action?status=<%=status %>');">
						<img title="Previous Page" src="../pages/common/img/previous.gif"
							width="10" height="10" class="iconImage" /> </a>
						<input type="text" name="pageNoField" id="pageNoField" size="3"
							value="<%=pageNo%>" maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'SettlmentDetails_callSettlementList.action?status=<%=status %>');return numbersOnly();" /> of <%=totalPage%>
						<a href="#"
							onclick="callPage('N', 'N', '<%=totalPage%>', 'SettlmentDetails_callSettlementList.action?status=<%=status %>')">
						<img title="Next Page" src="../pages/common/img/next.gif"
							class="iconImage" /> </a>&nbsp;
						<a href="#"
							onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'SettlmentDetails_callSettlementList.action?status=<%=status %>');">
						<img title="Last Page" src="../pages/common/img/last.gif"
							width="10" height="10" class="iconImage" /> </a>
					</s:if></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td class="txt" colspan="3">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="sortable">
				<tr class="td_bottom_border">
					<td width="5%" valign="top" class="formth"><label name="sr.no"
						id="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
					<td width="15%" valign="top" class="formth"><label
						name="employee.id" id="employee.id"
						ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></td>
					<td width="40%" valign="top" class="formth"><label
						name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
					<td width="30%" valign="top" class="formth" nowrap="nowrap">
					<%
						if (status.equals("Pending Settlement")	|| status.equals("In Process Settlement")) {
					%> 
					<label class="set" name="resignation.date" id="resignation.date" ondblclick="callShowDiv(this);"><%=label.get("resignation.date")%></label>
					<% } else {	%> 
					<label class="set" name="settlement.date" id="settlement.date" ondblclick="callShowDiv(this);"><%=label.get("settlement.date")%></label>
					<%	} %>
					</td>
					<td width="10%" valign="top" class="formth" nowrap="nowrap">
						<label class="set" name="click.to" id="click.to" ondblclick="callShowDiv(this);"><%=label.get("click.to")%></label>
					</td>
				</tr>
				<s:if test="noData">
					<tr>
						<td width="100%" colspan="7" align="center"><font color="red">No Data To Display</font></td>
					</tr>
				</s:if>
				<%!int d = 0;%>
				<%
					int t = 0;
					int cn = pageNo * 20 - 20;
				%>
				<s:iterator value="tableList">
					<tr class="sortableTD">
						<td class="sortableTD" width="5%">
							<%=++cn%> <% ++t; %>
						</td>
						<td class="sortableTD" width="15%">
							<input	type="hidden" value='<s:property value="empCodeItt" />' id="empCodeItt<%=t %>"/> 
							<s:property value="empTokenItt" />
						</td>
						<td class="sortableTD" width="40%">
							<s:property	value="empNameItt" />
						</td>
						<td class="sortableTD" width="30%" nowrap="nowrap" align="center">
						<%
									if (status.equals("Pending Settlement")	|| status.equals("In Process Settlement")) {
						%> 
						<s:property value="resignDateItt" /> 
						<% } else { %> 
							<s:property value="settDateItt" /> 
						<% } %>
						</td>
						<td align="center" width="10%" class="sortableTD">
							<input type="hidden" value='<s:property	value="statusItt" />' id="statusItt<%=t %>"/>
							<input type="hidden" value='<s:property	value="resignDateItt" />' id="resignDateItt<%=t %>"/>
							<input type="hidden" value='<s:property	value="settDateItt" />' id="settDateItt<%=t %>"/>
							<input type="hidden" value='<s:property	value="resignCodeItt" />' id="resignCodeItt<%=t %>"/>
							<input type="hidden" value='<s:property	value="separDateItt" />' id="separDateItt<%=t %>"/>
							<input type="hidden" value='<s:property	value="noticePeriodItt" />' id="noticePeriodItt<%=t %>"/>
							<input type="hidden" value='<s:property	value="noticeStatusItt" />'	id="noticeStatusItt<%=t %>" /> 
							<% if (status.equals("Pending Settlement")) { %>
							<input type="button" class="token" value=" Process"	onclick="callProcess(<%=t %>);" /> 
							<% } else if (status.equals("In Process Settlement")) { %>
							<input type="button" class="edit" value=" Edit" onclick="callProcess(<%=t %>);" /> 
							<% } else { %>
							<input type="button" class="token" value=" View" onclick="callProcess(<%=t %>);" />
							<% } %> 
						</td>
					</tr>
				</s:iterator>
				<tr>
					<td colspan="7" align="right">
						<strong>Total Records:</strong><s:property value="totalRecords"/>
					</td>
				</tr>
				<% d = t; %>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script>
window.onload=checkVal();	
function checkVal(){
	document.getElementById('enableAll')="Y";
}

function callProcess(code){
	var status = document.getElementById('statusItt'+code).value;
	var empCode = document.getElementById('empCodeItt'+code).value;
	//var resignDate = document.getElementById('resignDateItt'+code).value;
	//var separDate = document.getElementById('separDateItt'+code).value;
	//var notice = document.getElementById('noticePeriodItt'+code).value;
	//var resignCode = document.getElementById('resignCodeItt'+code).value;
	//var noticeStatus = document.getElementById('noticeStatusItt'+code).value;
	
	document.getElementById('paraFrm').target = "_self";
	document.getElementById("paraFrm").action='SettlmentDetails_processEditView.action?status='+status+'&empCode='+empCode; 
	document.getElementById("paraFrm").submit();
}



function reportFun(){
	var settCode=document.getElementById('paraFrm_settCode').value;
	if(settCode=="" || trim(settCode.toString())=="null"){
		alert('Please Select a record to generate report');
		return false;
	}
	document.getElementById('paraFrm').target="_blank";
  	document.getElementById('paraFrm').action="SettlmentDetails_report.action";
  	document.getElementById('paraFrm').submit();  
  	document.getElementById('paraFrm').target="main"; 
}

function replaceBlankWithZero(obj){
	if(obj.value==""){
		obj.value=0;
	}
}

function callSearch() {
		if(navigator.appName == 'Netscape') {
			var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
		} else {
			var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		}
		
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action ='SettlmentDetails_f9Action.action';
		document.getElementById("paraFrm").submit();
	}
</script>