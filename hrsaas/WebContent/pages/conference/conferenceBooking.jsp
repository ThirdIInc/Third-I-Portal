<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="ConferenceBooking" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="hiddencode" />
	<s:hidden name="hiddenStatus" />		
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
		class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" cellpadding="0" cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Conference
					Booking Application </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
		<!-- Tab Options Stars -->
		<tr>
			<td width="100%">
			<table width="100%">
				<tr>
					<td><a href="#" onclick="setAction('P')">Pending
					Application</a> | <a href="#" onclick="setAction('A')">Approved
					List</a> | <a href="#" onclick="setAction('R')">Rejected
					List/Cancelled List</a></td>
					<script type="text/javascript">
							function setAction(listType)
							{
								if(listType == "P") {
									document.getElementById('paraFrm').action = 'ConferenceBooking_input.action';
									document.getElementById('paraFrm').submit();
								}
								if(listType == "A") {
									document.getElementById('paraFrm').action = 'ConferenceBooking_getApprovedList.action';
									document.getElementById('paraFrm').submit();
								}
								if(listType == "R") {
									document.getElementById('paraFrm').action = 'ConferenceBooking_getRejectedList.action';
									document.getElementById('paraFrm').submit();
								}
							}
						</script>
				</tr>
			</table>
			</td>
		</tr>
		<!-- Tab Options Ends -->
		<!-- Pending Application Tab Starts -->
		<s:if test="%{listType == 'pending'}">
			<!-- Draft Table Starts - all Saved records -->
			<tr>
				<td>
				<table width="100%" class="formbg" border="0">
					<tr>
						<td><b>Draft</b></td>
					</tr>
					<tr>
						<td>
						<table width="100%" class="sorttable">
							<tr>
								<td class="formth" width="8%"><b><label id="serial.no"
									name="serial.no" class="set" ondblclick="callShowDiv(this);">
								<%=label.get("serial.no")%></label></b></td>
								<td class="formth" width="12%"><b><label id="emp.id"
									name="emp.id" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("emp.id")%></label></b></td>
								<td class="formth" width="30%"><b><label id="emp.name"
									name="emp.name" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("emp.name")%></label></b></td>
								<td class="formth" width="25%"><b><label id="confDate"
									name="confDate" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("confDate")%></label></b></td>
								<td class="formth" width="25%"><b><label id="fromToTime"
									name="fromToTime" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("fromToTime")%></label></b></td>
							</tr>
							<%
							int i = 0;
							%>
							<s:iterator value="draftList">
								<tr <% if(i%2==0){ %> class="tableCell1" <%}else{ %>
									class="tableCell2" <%} %> onmouseover="newRowColor(this);"
									onmouseout="oldRowColor(this,<%=i%>);"
									ondblclick="callForEdit('<s:property value="confCode"/>','<s:property value="status"/>');"
									title="Double click for Edit">
									<td class="sortableTD" width="8%"><%=++i%></td>
									<td class="sortableTD" width="12%"><s:property
										value="bookByToken" /></td>
									<td class="sortableTD" width="30%"><s:property
										value="bookBy" /></td>
									<td class="sortableTD" width="25%"><s:property
										value="requireDate" />&nbsp;</td>
									<td class="sortableTD" width="25%"><s:property
										value="fromTime" />&nbsp;-&nbsp;<s:property value="toTime" /></td>
								</tr>
							</s:iterator>
							<%
							if (i == 0) {
							%>
							<tr>
								<td colspan="4" class="sortableTD" align="center" width="100%">
								<font color="red">No Data to display</font></td>
							</tr>
							<%
							}
							%>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<!-- Draft Table Ends - all Saved records -->

			<!-- Submitted List Starts - all send to approval records -->
			<tr>
				<td>
				<table width="100%" class="formbg" border="0">
					<tr>
						<td><b>Application In Process</b></td>
					</tr>
					<tr>
						<td>
						<table width="100%" class="sorttable">
							<tr>
								<td class="formth" width="8%"><b><label id="serial.no"
									name="serial.no" class="set" ondblclick="callShowDiv(this);">
								<%=label.get("serial.no")%></label></b></td>
								<td class="formth" width="12%"><b><label id="emp.id"
									name="emp.id" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("emp.id")%></label></b></td>
								<td class="formth" width="30%"><b><label id="emp.name"
									name="emp.name" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("emp.name")%></label></b></td>
								<td class="formth" width="25%"><b><label id=confDate
									name="confDate" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("confDate")%></label></b></td>
								<td class="formth" width="25%"><b><label id="fromToTime"
									name="fromToTime" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("fromToTime")%></label></b></td>
							</tr>
							<%
							int j = 0;
							%>
							<s:iterator value="submittedList">
								<tr <% if(j%2==0){ %> class="tableCell1" <%}else{ %>
									class="tableCell2" <%} %> onmouseover="newRowColor(this);"
									onmouseout="oldRowColor(this,<%=j%>);"
									ondblclick="callForEdit('<s:property value="confCode"/>','<s:property value="status"/>');"
									title="Double click to View">

									<td class="sortableTD" width="8%"><%=++j%></td>
									<td class="sortableTD" width="12%"><s:property
										value="bookByToken" /></td>
									<td class="sortableTD" width="30%"><s:property
										value="bookBy" /></td>
									<td class="sortableTD" width="25%"><s:property
										value="requireDate" />&nbsp;</td>
									<td class="sortableTD" width="25%"><s:property
										value="fromTime" />&nbsp;-&nbsp;<s:property value="toTime" /></td>
								</tr>
							</s:iterator>
							<%
							if (j == 0) {
							%>
							<tr>
								<td colspan="4" class="sortableTD" align="center" width="100%">
								<font color="red">No Data to display</font></td>
							</tr>
							<%
							}
							%>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<!-- Submitted List Ends - all Send to Approval records -->

			<!-- Returned List Starts - all send to back records -->
			<tr>
				<td>
				<table width="100%" class="formbg" border="0">
					<tr>
						<td><b>Returned Applications(Please view the comments,
						update Application)</b></td>
					</tr>
					<tr>
						<td>
						<table width="100%" class="sorttable">
							<tr>
								<td class="formth" width="8%"><b><label id="serial.no"
									name="serial.no" class="set" ondblclick="callShowDiv(this);">
								<%=label.get("serial.no")%></label></b></td>
								<td class="formth" width="12%"><b><label id="emp.id"
									name="emp.id" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("emp.id")%></label></b></td>
								<td class="formth" width="30%"><b><label id="emp.name"
									name="emp.name" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("emp.name")%></label></b></td>
								<td class="formth" width="25%"><b><label id="confDate"
									name="confDate" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("confDate")%></label></b></td>
								<td class="formth" width="25%"><b><label id="fromToTime"
									name="fromToTime" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("fromToTime")%></label></b></td>
							</tr>
							<%
							int k = 0;
							%>
							<s:iterator value="returnedList">
								<tr <% if(k%2==0){ %> class="tableCell1" <%}else{ %>
									class="tableCell2" <%} %> onmouseover="newRowColor(this);"
									onmouseout="oldRowColor(this,<%=k%>);"
									ondblclick="callForEdit('<s:property value="confCode"/>','<s:property value="status"/>');"
									title="Double click for Edit">

									<td class="sortableTD" width="8%"><%=++k%></td>
									<td class="sortableTD" width="12%"><s:property
										value="bookByToken" /></td>
									<td class="sortableTD" width="30%"><s:property
										value="bookBy" /></td>
									<td class="sortableTD" width="25%"><s:property
										value="requireDate" />&nbsp;</td>
									<td class="sortableTD" width="25%"><s:property
										value="fromTime" />&nbsp;-&nbsp;<s:property value="toTime" /></td>
								</tr>
							</s:iterator>
							<%
							if (k == 0) {
							%>
							<tr>
								<td colspan="4" class="sortableTD" align="center" width="100%">
								<font color="red">No Data to display</font></td>
							</tr>
							<%
							}
							%>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<!-- Returned List Ends - all Send to back records -->
		</s:if>
		<!-- Pending Application Tab Ends -->
		<s:hidden name="myPage" id="myPage" />
		<!-- Approved List Tab Starts -->
		<s:if test="%{listType=='approved'}">
			<tr>
				<td>
				<table width="100%" class="formbg" border="0">
					<tr>
						<td><b>Approved Application List</b></td>
					</tr>
					<tr>
						<%
							int totalPage1 = 0;
							int pageNo1 = 0;
						%>
						<s:if test="listLengthApproved">
							<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
							<%
								totalPage1 = (Integer) request.getAttribute("totalPage1");
								pageNo1 = (Integer) request.getAttribute("pageNo1");
							%> <a href="#"
								onclick="callPage('1', 'F', '<%=totalPage1%>', 'ConferenceBooking_callPage.action');">
							<img title="First Page" src="../pages/common/img/first.gif"
								width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPage('P', 'P', '<%=totalPage1%>', 'ConferenceBooking_callPage.action');">
							<img title="Previous Page" src="../pages/common/img/previous.gif"
								width="10" height="10" class="iconImage" /> </a> <input type="text"
								name="pageNoField" id="pageNoField" size="3"
								value="<%=pageNo1%>" maxlength="10"
								onkeypress="callPageText(event, '<%=totalPage1%>', 'ConferenceBooking_callPage.action');return numbersOnly();" />
							of <%=totalPage1%> <a href="#"
								onclick="callPage('N', 'N', '<%=totalPage1%>', 'ConferenceBooking_callPage.action')">
							<img title="Next Page" src="../pages/common/img/next.gif"
								class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPage('<%=totalPage1%>', 'L', '<%=totalPage1%>', 'ConferenceBooking_callPage.action');">
							<img title="Last Page" src="../pages/common/img/last.gif"
								width="10" height="10" class="iconImage" /> </a></td>
						</s:if>
					</tr>
					<tr>
						<td>
						<table width="100%" class="sorttable">
							<tr>
								<td class="formth" width="8%"><b><label id="serial.no"
									name="serial.no" class="set" ondblclick="callShowDiv(this);">
								<%=label.get("serial.no")%></label></b></td>
								<td class="formth" width="12%"><b><label id="emp.id"
									name="emp.id" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("emp.id")%></label></b></td>
								<td class="formth" width="30%"><b><label id="emp.name"
									name="emp.name" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("emp.name")%></label></b></td>
								<td class="formth" width="15%"><b><label id="confDate"
									name="confDate" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("confDate")%></label></b></td>
								<td class="formth" width="20%"><b><label id="fromToTime"
									name="fromToTime" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("fromToTime")%></label></b></td>
								<td class="formth" width="15%"><b><label id="cancel"
									name="cancel" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("cancel")%></label></b></td>
							</tr>
							<%
							int m = 0;
							%>
							<s:iterator value="approvedList">
								<tr <% if(m%2==0){ %> class="tableCell1" <%}else{ %>
									class="tableCell2" <%} %> onmouseover="newRowColor(this);"
									onmouseout="oldRowColor(this,<%=m%>);"
									ondblclick="callForEdit('<s:property value="confCode"/>','<s:property value="status"/>');"
									title="Double click to view">
									<td class="sortableTD" width="5%"><%=++m%></td>
									<td class="sortableTD" width="10%"><s:property
										value="bookByToken" /></td>
									<td class="sortableTD" width="35%"><s:property
										value="bookBy" /></td>
									<td class="sortableTD" width="15%"><s:property
										value="requireDate" />&nbsp;</td>
									<td class="sortableTD" width="20%"><s:property
										value="fromTime" />&nbsp;-&nbsp;<s:property value="toTime" /></td>
									<td class="sortableTD" width="20%"><s:if test="cancelFlag"><input type="button"
										name="cancel_btn" value="Cancel"
										onclick="cancelApplication('<s:property value="confCode"/>');"></s:if></td>
								</tr>
							</s:iterator>
							<s:if test="listLengthApproved">
							</s:if>
							<s:else>
								<tr>
									<td colspan="4" class="sortableTD" align="center" width="100%">
									<font color="red">No Data to display</font></td>
								</tr>
							</s:else>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<s:if test="listLengthApproved">
					<td align="right"><b>Total No. of Records: <s:property
						value="totalRecords" /></b></td>
				</s:if>
			</tr>
		</s:if>
		<!-- Approved List Tabe Ends -->

		<!-- Rejected List Tab Starts -->
		<s:if test="%{listType=='rejected'}">
			<tr>
				<td>
				<table width="100%" class="formbg" border="0">
					<tr>
						<td><b>Rejected Applications List</b></td>
					</tr>
					<tr>
						<%
							int totalPage2 = 0;
							int pageNo2 = 0;
						%>
						<s:if test="listLengthRejected">
							<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
							<%
								totalPage2 = (Integer) request.getAttribute("totalPage2");
								pageNo2 = (Integer) request.getAttribute("pageNo2");
							%> <a href="#"
								onclick="callPage('1', 'F', '<%=totalPage2%>', 'ConferenceBooking_callPage.action');">
							<img title="First Page" src="../pages/common/img/first.gif"
								width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPage('P', 'P', '<%=totalPage2%>', 'ConferenceBooking_callPage.action');">
							<img title="Previous Page" src="../pages/common/img/previous.gif"
								width="10" height="10" class="iconImage" /> </a> <input type="text"
								name="pageNoField" id="pageNoField" size="3"
								value="<%=pageNo2%>" maxlength="10"
								onkeypress="callPageText(event, '<%=totalPage2%>', 'ConferenceBooking_callPage.action');return numbersOnly();" />
							of <%=totalPage2%> <a href="#"
								onclick="callPage('N', 'N', '<%=totalPage2%>', 'ConferenceBooking_callPage.action')">
							<img title="Next Page" src="../pages/common/img/next.gif"
								class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPage('<%=totalPage2%>', 'L', '<%=totalPage2%>', 'ConferenceBooking_callPage.action');">
							<img title="Last Page" src="../pages/common/img/last.gif"
								width="10" height="10" class="iconImage" /> </a></td>
						</s:if>
					</tr>
					<tr>
						<td>
						<table width="100%" class="sorttable">
							<tr>
								<td class="formth" width="8%"><b><label id="serial.no"
									name="serial.no" class="set" ondblclick="callShowDiv(this);">
								<%=label.get("serial.no")%></label></b></td>
								<td class="formth" width="12%"><b><label id="emp.id"
									name="emp.id" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("emp.id")%></label></b></td>
								<td class="formth" width="30%"><b><label id="emp.name"
									name="emp.name" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("emp.name")%></label></b></td>
								<td class="formth" width="25%"><b><label id="confDate"
									name="confDate" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("confDate")%></label></b></td>
								<td class="formth" width="25%"><b><label id="fromToTime"
									name="fromToTime" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("fromToTime")%></label></b></td>
							</tr>
							<%
							int n = 0;
							%>
							<s:iterator value="rejectedList">
								<tr <% if(n%2==0){ %> class="tableCell1" <%}else{ %>
									class="tableCell2" <%} %> onmouseover="newRowColor(this);"
									onmouseout="oldRowColor(this,<%=n%>);"
									ondblclick="callForEdit('<s:property value="confCode"/>','<s:property value="status"/>');"
									title="Double click to view">

									<td class="sortableTD" width="8%"><%=++n%></td>
									<td class="sortableTD" width="12%"><s:property
										value="bookByToken" /></td>
									<td class="sortableTD" width="30%"><s:property
										value="bookBy" /></td>
									<td class="sortableTD" width="25%"><s:property
										value="requireDate" />&nbsp;</td>
									<td class="sortableTD" width="25%"><s:property
										value="fromTime" />&nbsp;-&nbsp;<s:property value="toTime" /></td>
								</tr>
							</s:iterator>
							<s:if test="listLengthRejected">
							</s:if>
							<s:else>
								<tr>
									<td colspan="4" class="sortableTD" align="center" width="100%">
									<font color="red">No Data to display</font></td>
								</tr>
							</s:else>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<s:if test="listLengthRejected">
					<td align="right"><b>Total No. of Records: <s:property
						value="totalRecords" /></b></td>
				</s:if>
			</tr>
		</s:if>

		<tr>
			<td><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
		<!-- Rejected List Tabe Ends -->
	</table>
</s:form>
<script type="text/javascript">
function addnewFun() {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = "ConferenceBooking_addNew.action";
		document.getElementById('paraFrm').submit();
	}
	
function searchFun() {
		if(navigator.appName == 'Netscape') {
			var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
		} else {
			var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		}
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action ='ConferenceBooking_f9action.action';
		document.getElementById("paraFrm").submit();
	}
	
function callForEdit(id,s) {

		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ConferenceBooking_callforedit.action?applicationCode='+id+'&applStatus='+s;
		document.getElementById('paraFrm').submit();
	}
	
function callForEdit_old(id,s) {
		document.getElementById('paraFrm_hiddencode').value = id;
		document.getElementById('paraFrm_hiddenStatus').value = s;
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = "ConferenceBooking_callforedit.action";
		document.getElementById('paraFrm').submit();
	}
		
	function oldRowColor(cell,val) {
		if(val=='1'){
		 cell.className='tableCell2';
		}
		else cell.className='tableCell1';
	}
	
	function newRowColor(cell) {
		cell.className='Cell_bg_first';
	}
	
function cancelApplication(code){
	var conferenceCode = code;
	conf = confirm('Do you Really want to Cancel the Application');
	if(conf){
		document.getElementById('paraFrm').target="_self";	
		document.getElementById('paraFrm').action='ConferenceBooking_cancelApplication.action?confID='+conferenceCode;
		document.getElementById('paraFrm').submit();
	}
  }
</script>