<!-- @author: REEBA JOSEPH 22 NOVEMBER 2010 -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript">
	var records = new Array();
</script>
<s:form action="LTCCalc" validate="true" id="paraFrm" validate="true"
	theme="simple">
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">LTC
					Calculation</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>

		<tr>
			<td>
			<table width="100%" class="formbg">
				<%
					int totalPage = 0;
					int pageNo = 0;
				%>
				<s:if test="recordsAvailable">
					<tr>
						<td id="ctrlShow" width="100%" align="right"><b>Page:</b> <%
						 	totalPage = (Integer) request.getAttribute("totalPage");
				 			pageNo = (Integer) request.getAttribute("pageNo");
						 %> <a href="#"
							onclick="callPage('1', 'F', '<%=totalPage%>', 'LTCCalc_input.action');">
						<img title="First Page" src="../pages/common/img/first.gif"
							width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('P', 'P', '<%=totalPage%>', 'LTCCalc_input.action');">
						<img title="Previous Page" src="../pages/common/img/previous.gif"
							width="10" height="10" class="iconImage" /> </a> <input type="text"
							name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
							maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'LTCCalc_input.action');return numbersOnly();" />
						of <%=totalPage%> <a href="#"
							onclick="callPage('N', 'N', '<%=totalPage%>', 'LTCCalc_input.action')">
						<img title="Next Page" src="../pages/common/img/next.gif"
							class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'LTCCalc_input.action');">
						<img title="Last Page" src="../pages/common/img/last.gif"
							width="10" height="10" class="iconImage" /> </a></td>
					</tr>
				</s:if>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" class="sorttable">
				<tr><s:hidden name="myPage" id = "myPage" />
					<td width="10%" class="formth"><label class="set"
						id="serial.no2" name="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
					<td width="20%" class="formth"><label class="set" id="month"
						name="month" ondblclick="callShowDiv(this);"><%=label.get("month")%></label></td>
					<td width="20%" class="formth"><label class="set" id="year"
						name="year" ondblclick="callShowDiv(this);"><%=label.get("year")%></label></td>
					<td width="40%" class="formth"><label class="set"
						id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label></td>
					<td width="10%" class="formth"><label class="set"
						id="status" name="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label></td>

				</tr>
				<s:if test="recordsAvailable">
					<%!int d = 0;%>
					<%
						int i = 0;
						int cn = pageNo * 20 - 20;
					%>
					<s:iterator value="processedLtcList">
						<tr onmouseover="javascript: newRowColor(this);"
							onmouseout="javascript: oldRowColor(this);"
							ondblclick="javascript: callForEdit('<s:property value="ltcCodeIt"/>','<s:property
								value="monthIt" />','<s:property value="yearIt" />','<s:property value="divisionIt" />',
								'<s:property value="divisionCodeIt" />','<s:property value="monthCodeIt" />','<s:property value="statusIt" />');"
							style="cursor: hand;">
							<td title="Double click for edit" width="10%" class="sortableTD"
								align="center"><%=++cn%> <%
											 	++i;
											 %>
							</td>
							<s:hidden id='<%="ltcCodeIt" + i%>' value="%{ltcCodeIt}" />
							<script type="text/javascript">
								records[<%=i%>] = document.getElementById('ltcCodeIt' + '<%=i%>').value;
							</script>
							<td title="Double click for edit" width="20%" class="sortableTD"
								width="20%"><s:hidden name="monthIt" /><s:property
								value="monthIt" /><s:hidden name="monthCodeIt" /></td>
							<td title="Double click for edit" width="20%" class="sortableTD"
								width="20%"><s:property value="yearIt" /></td>
							<td title="Double click for edit" class="sortableTD"
								width="40%"><s:property value="divisionIt" /><s:hidden
								name="divisionCodeIt" /></td>
							<td title="Double click for edit" width="10%" class="sortableTD"><s:property value="statusIt" /><s:hidden
								name="statusIt" /></td>

						</tr>

					</s:iterator>
					<%
						d = i;
					%>
				</s:if>
				<s:if test="recordsAvailable"></s:if>
				<s:else>
					<tr align="center">
						<td colspan="6" class="sortableTD" width="100%"><font
							color="red">No Data to display</font></td>
					</tr>
				</s:else>

			</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
			<table width="100%">
				<tr>
					<td><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<s:if test="recordsAvailable">
						<td align="right"><b>Total No. of Records: <s:property
							value="totalRecords" /></b></b></td>
					</s:if>
				</tr>
			</table>
			</td>
		</tr>
		<s:hidden name="monthCode" /><s:hidden name="status" />
		<s:hidden name="month" /><s:hidden name="year" />
		<s:hidden name="divisionId" /><s:hidden name="divisionName" />
		<s:hidden name="ltcCode"/>
		
	</table>
</s:form>

<script>
window.onload = document.getElementById('pageNoField').focus();
	
function addnewFun() {
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = 'LTCCalc_addNew.action';
	document.getElementById('paraFrm').submit();
}

function searchFun() {
	if(navigator.appName == 'Netscape') {
		var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
	} else {
		var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
	}
	
	document.getElementById("paraFrm").target = 'myWin';
	document.getElementById("paraFrm").action = 'LTCCalc_search.action';
	document.getElementById("paraFrm").submit();
}

function newRowColor(cell) {
	cell.className = 'onOverCell';
}

function oldRowColor(cell) {
	cell.className = 'tableCell1';
}

function callForEdit(ltcCode, month,year,divname,divid,monthCode, status){
	document.getElementById("paraFrm_ltcCode").value = ltcCode;
	document.getElementById("paraFrm_month").value = month;
	document.getElementById("paraFrm_year").value = year;
	document.getElementById("paraFrm_divisionName").value = divname;
	document.getElementById("paraFrm_divisionId").value = divid;
	document.getElementById("paraFrm_monthCode").value = monthCode;
	document.getElementById("paraFrm_status").value = status;
	document.getElementById("paraFrm").action="LTCCalc_callForEdit.action";
   	document.getElementById("paraFrm").target="main";
    document.getElementById("paraFrm").submit();
}
</script>