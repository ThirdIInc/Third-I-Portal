<!-- Bhushan Dasare --><!-- Mar 22, 2011 -->

<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp"%>

<s:form action="ReportRequestChangeApproval" name="ReportRequestChangeApproval" id="paraFrm" validate="true" target="main" theme="simple">
	<s:hidden name="listType" /><s:hidden name="userAManager" /><s:hidden name="userAITApprover" />

	<table width="100%" class="formbg" align="right">
		<tr>
			<td>
				<table width="100%" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt">
							<strong class="formhead">
								<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" />
							</strong>
						</td>
						<td width="92%" class="txt"><strong class="text_head">Report Request Change Approval</strong></td>
						<td width="4%" valign="middle" align="right" class="txt">
							<img src="../pages/images/recruitment/help.gif" width="16" height="16" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<a href="#" onclick="callPending();">Pending Applications</a> | 
				<a href="#" onclick="callApproved();">Approved Applications</a> | 
				<a href="#" onclick="callRejected();">Rejected Applications</a>
			</td>
		</tr>
		<tr id="pending">
			<td>
				<table width="100%" class="formbg">
					<tr>
						<td width="50%" class="formtxt"><strong>Pending Applications</strong></td>
						<td align="right">
							<table width="100%">
								<tr>
									<s:hidden name="pageForPending" id="pageForPending" />
									<%
										int totalPageForPending = 0;
										int pageNoForPending = 0;
										try {
											totalPageForPending = (Integer) request.getAttribute("totalPageForPending");
											pageNoForPending = (Integer) request.getAttribute("pageNoForPending");
										} catch(Exception e) {}
									%>
									<% if(totalPageForPending > 0) { %>
										<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
											<a href="#" onclick="callASRPage('1', 'F', '<%=totalPageForPending%>', 
											'ReportRequestChangeApproval_callPage.action', 'fldPageNoForPending', 'pageForPending');">
												<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" 
												class="iconImage" />
											</a>&nbsp;
											<a href="#" onclick="callASRPage('P', 'P', '<%=totalPageForPending%>', 
											'ReportRequestChangeApproval_callPage.action', 'fldPageNoForPending', 'pageForPending');">
												<img title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" 
												class="iconImage" />
											</a>
											<input type="text" name="fldPageNoForPending" size="3" value="<%=pageNoForPending%>" 
											maxlength="10" onkeypress="callPageText(event, '<%=totalPageForPending%>', 
											'ReportRequestChangeApproval_callPage.action', 'fldPageNoForPending', 'pageForPending');
											return numbersOnly();" /> of <%=totalPageForPending%>
											<a href="#" onclick="callASRPage('N', 'N', '<%=totalPageForPending%>', 
											'ReportRequestChangeApproval_callPage.action', 'fldPageNoForPending', 'pageForPending')">
												<img title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
											</a>&nbsp;
											<a href="#" onclick="callASRPage('<%=totalPageForPending%>', 'L', '<%=totalPageForPending%>', 
											'ReportRequestChangeApproval_callPage.action', 'fldPageNoForPending', 'pageForPending');">
												<img title="Last Page" src="../pages/common/img/last.gif" width="10" height="10" 
												class="iconImage" />
											</a>
										</td>
									<% } %>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td width="100%" colspan="2">
							<table width="100%" class="sortable">
								<tr class="td_bottom_border">
									<td width="10%" valign="top" class="formth">
										<label id="sr.no" name="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label>
									</td>
									<td width="20%" valign="top" class="formth">
										<label id="tracking.no" name="tracking.no" ondblclick="callShowDiv(this);"><%=label.get("tracking.no")%></label>
									</td>
									<td width="50%" valign="top" class="formth">
										<label id="requestor.name" name="requestor.name" ondblclick="callShowDiv(this);"><%=label.get("requestor.name")%></label>
									</td>
									<td width="10%" valign="top" class="formth" nowrap="nowrap">
										<label id="requested.date" name="requested.date" ondblclick="callShowDiv(this);"><%=label.get("requested.date")%></label>
									</td>
									<td width="10%" valign="top" class="formth" nowrap="nowrap">
										<label id="details" name="details" ondblclick="callShowDiv(this);"><%=label.get("details")%></label>
									</td>
								</tr>
								<%	int pendingCount = 0; %>
								<s:iterator value="pendingList">
									<tr>
										<td class="sortableTD"><%=++pendingCount%></td>
										<td class="sortableTD">
											<s:hidden name="reqId" />
											<s:property value="requestorToken" />
										</td>
										<td class="sortableTD">
											<s:property value="requestorName" />
										</td>
										<td class="sortableTD" align="center">
											<s:property value="requestDate" />
										</td>
										<td id="ctrlShow" class="sortableTD" align="center">
											<input type="button" class="token" value="View Application" 
											onclick="viewDetails('<s:property value="reqId"/>')" />
										</td>
									</tr>
								</s:iterator>
								<% if(pendingCount == 0) { %>
									<tr>
										<td width="100%" colspan="5" align="center"><font color="red">No Data To Display</font></td>
									</tr>
								<% } %>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr id="approved">
			<td>
				<table width="100%" class="formbg">
					<tr>
						<td width="50%" class="formtxt"><strong>Approved Applications</strong></td>
						<td align="right">
							<table width="100%">
								<tr>
									<s:hidden name="pageForApproved" id="pageForApproved" />
									<%
										int totalPageForApproved = 0;
										int pageNoForApproved = 0;
										try {
											totalPageForApproved = (Integer) request.getAttribute("totalPageForApproved");
											pageNoForApproved = (Integer) request.getAttribute("pageNoForApproved");
										} catch(Exception e) {}
									%>
									<% if(totalPageForApproved > 0) { %>
										<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
											<a href="#" onclick="callASRPage('1', 'F', '<%=totalPageForApproved%>', 
											'ReportRequestChangeApproval_callPage.action', 'fldPageNoForApproved', 'pageForApproved');">
												<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" 
												class="iconImage" />
											</a>&nbsp;
											<a href="#" onclick="callASRPage('P', 'P', '<%=totalPageForApproved%>', 
											'ReportRequestChangeApproval_callPage.action', 'fldPageNoForApproved', 'pageForApproved');">
												<img title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" 
												class="iconImage" />
											</a>
											<input type="text" name="fldPageNoForApproved" size="3" value="<%=pageNoForApproved%>" 
											maxlength="10" onkeypress="callPageText(event, '<%=totalPageForApproved%>', 
											'ReportRequestChangeApproval_callPage.action', 'fldPageNoForApproved', 'pageForApproved');
											return numbersOnly();" /> of <%=totalPageForApproved%>
											<a href="#" onclick="callASRPage('N', 'N', '<%=totalPageForApproved%>', 
											'ReportRequestChangeApproval_callPage.action', 'fldPageNoForApproved', 'pageForApproved')">
												<img title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
											</a>&nbsp;
											<a href="#" onclick="callASRPage('<%=totalPageForApproved%>', 'L', '<%=totalPageForApproved%>', 
											'ReportRequestChangeApproval_callPage.action', 'fldPageNoForApproved', 'pageForApproved');">
												<img title="Last Page" src="../pages/common/img/last.gif" width="10" height="10" 
												class="iconImage" />
											</a>
										</td>
									<% } %>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td width="100%" colspan="2">
							<table width="100%" class="sortable">
								<tr class="td_bottom_border">
									<td width="10%" valign="top" class="formth">
										<label id="sr.no" name="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label>
									</td>
									<td width="20%" valign="top" class="formth">
										<label id="tracking.no" name="tracking.no" ondblclick="callShowDiv(this);"><%=label.get("tracking.no")%></label>
									</td>
									<td width="50%" valign="top" class="formth">
										<label id="requestor.name" name="requestor.name" ondblclick="callShowDiv(this);"><%=label.get("requestor.name")%></label>
									</td>
									<td width="10%" valign="top" class="formth" nowrap="nowrap">
										<label id="requested.date" name="requested.date" ondblclick="callShowDiv(this);"><%=label.get("requested.date")%></label>
									</td>
									<td width="10%" valign="top" class="formth" nowrap="nowrap">
										<label id="details" name="details" ondblclick="callShowDiv(this);"><%=label.get("details")%></label>
									</td>
								</tr>
								<%	int approvedCount = 0; %>
								<s:iterator value="approvedList">
									<tr>
										<td class="sortableTD"><%=++approvedCount%></td>
										<td class="sortableTD">
											<s:hidden name="reqId" />
											<s:property value="requestorToken" />
										</td>
										<td class="sortableTD">
											<s:property value="requestorName" />
										</td>
										<td class="sortableTD" align="center">
											<s:property value="requestDate" />
										</td>
										<td id="ctrlShow" class="sortableTD" align="center">
											<input type="button" class="token" value="View Application" 
											onclick="viewDetails('<s:property value="reqId"/>')" />
										</td>
									</tr>
								</s:iterator>
								<% if(approvedCount == 0) { %>
									<tr>
										<td width="100%" colspan="5" align="center"><font color="red">No Data To Display</font></td>
									</tr>
								<% } %>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr id="rejected">
			<td>
				<table width="100%" class="formbg">
					<tr>
						<td width="50%" class="formtxt"><strong>Rejected Applications</strong></td>
						<td align="right">
							<table width="100%">
								<tr>
									<s:hidden name="pageForRejected" id="pageForRejected" />
									<%
										int totalPageForRejected = 0;
										int pageNoForRejected = 0;
										try {
											totalPageForRejected = (Integer) request.getAttribute("totalPageForRejected");
											pageNoForRejected = (Integer) request.getAttribute("pageNoForRejected");
										} catch(Exception e) {}
									%>
									<% if(totalPageForRejected > 0) { %>
										<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
											<a href="#" onclick="callASRPage('1', 'F', '<%=totalPageForRejected%>', 
											'ReportRequestChangeApproval_callPage.action', 'fldPageNoForRejected', 'pageForRejected');">
												<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" 
												class="iconImage" />
											</a>&nbsp;
											<a href="#" onclick="callASRPage('P', 'P', '<%=totalPageForRejected%>', 
											'ReportRequestChangeApproval_callPage.action', 'fldPageNoForRejected', 'pageForRejected');">
												<img title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" 
												class="iconImage" />
											</a>
											<input type="text" name="fldPageNoForRejected" size="3" value="<%=pageNoForRejected%>" 
											maxlength="10" onkeypress="callPageText(event, '<%=totalPageForRejected%>', 
											'ReportRequestChangeApproval_callPage.action', 'fldPageNoForRejected', 'pageForRejected');
											return numbersOnly();" /> of <%=totalPageForRejected%>
											<a href="#" onclick="callASRPage('N', 'N', '<%=totalPageForRejected%>', 
											'ReportRequestChangeApproval_callPage.action', 'fldPageNoForRejected', 'pageForRejected')">
												<img title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
											</a>&nbsp;
											<a href="#" onclick="callASRPage('<%=totalPageForRejected%>', 'L', '<%=totalPageForRejected%>', 
											'ReportRequestChangeApproval_callPage.action', 'fldPageNoForRejected', 'pageForRejected');">
												<img title="Last Page" src="../pages/common/img/last.gif" width="10" height="10" 
												class="iconImage" />
											</a>
										</td>
									<% } %>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td width="100%" colspan="2">
							<table width="100%" class="sortable">
								<tr class="td_bottom_border">
									<td width="10%" valign="top" class="formth">
										<label id="sr.no" name="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label>
									</td>
									<td width="20%" valign="top" class="formth">
										<label id="tracking.no" name="tracking.no" ondblclick="callShowDiv(this);"><%=label.get("tracking.no")%></label>
									</td>
									<td width="50%" valign="top" class="formth">
										<label id="requestor.name" name="requestor.name" ondblclick="callShowDiv(this);"><%=label.get("requestor.name")%></label>
									</td>
									<td width="10%" valign="top" class="formth" nowrap="nowrap">
										<label id="requested.date" name="requested.date" ondblclick="callShowDiv(this);"><%=label.get("requested.date")%></label>
									</td>
									<td width="10%" valign="top" class="formth" nowrap="nowrap">
										<label id="details" name="details" ondblclick="callShowDiv(this);"><%=label.get("details")%></label>
									</td>
								</tr>
								<%	int rejectedCount = 0; %>
								<s:iterator value="rejectedList">
									<tr>
										<td class="sortableTD"><%=++rejectedCount%></td>
										<td class="sortableTD">
											<s:hidden name="reqId" />
											<s:property value="requestorToken" />
										</td>
										<td class="sortableTD">
											<s:property value="requestorName" />
										</td>
										<td class="sortableTD" align="center">
											<s:property value="requestDate" />
										</td>
										<td id="ctrlShow" class="sortableTD" align="center">
											<input type="button" class="token" value="View Application" 
											onclick="viewDetails('<s:property value="reqId"/>')" />
										</td>
									</tr>
								</s:iterator>
								<% if(rejectedCount == 0) { %>
									<tr>
										<td width="100%" colspan="5" align="center"><font color="red">No Data To Display</font></td>
									</tr>
								<% } %>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</s:form>

<script type="text/javascript">
	loadList();
	
	function loadList() {
		document.getElementById('pending').style.display = 'none';
		document.getElementById('approved').style.display = 'none';
		document.getElementById('rejected').style.display = 'none';
		
		var listType = document.getElementById('paraFrm_listType').value;
		document.getElementById(listType).style.display = '';
	}

	function callPending() {
		document.getElementById('paraFrm').action = 'ReportRequestChangeApproval_getPendingList.action';
		document.getElementById('paraFrm').submit();
	}
	
	function callApproved() {
		document.getElementById('paraFrm').action = 'ReportRequestChangeApproval_getApprovedList.action';
		document.getElementById('paraFrm').submit();
	}

	function callRejected() {
		document.getElementById('paraFrm').action = 'ReportRequestChangeApproval_getRejectedList.action';
		document.getElementById('paraFrm').submit();
	}
	
	function callASRPage(id, pageImg, totalPageHid, action, fldPageNo, hdnMyPage) {
		var pageNo = document.getElementById(fldPageNo).value;	
		var actPage = document.getElementById(hdnMyPage).value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById(fldPageNo).focus();
				return false;
			}

		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById(fldPageNo).value = actPage;
			    document.getElementById(fldPageNo).focus();
				return false;
			}
			
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById(fldPageNo).value=actPage;
			    document.getElementById(fldPageNo).focus();
				return false;
			}
		}
		
       	if(pageImg == "F") {
			if(pageNo == "1") {
				alert("This is first page.");
				document.getElementById(fldPageNo).focus();
				return false;
			}
		}
		
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById(fldPageNo).focus();
	         	return false;
	        }
       	}
       	
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById(fldPageNo).focus();
	         	return false;
	        }
		}
       	
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById(fldPageNo).focus();
	         	return false;
			}
		}
       	
       	if(id == 'P') {
			var p = document.getElementById(fldPageNo).value;
			id = eval(p) - 1;
		}
		
		if(id == 'N') {
			var p = document.getElementById(fldPageNo).value;
			id = eval(p) + 1;
		}
		
		document.getElementById(hdnMyPage).value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
	}
	
	function callPageText(id, totalPage, action, fldPageNo, hdnMyPage) {
		var key //= (window.event) ? event.keyCode : e.which;

		if (window.event)
			key = event.keyCode
		else
			key = id.which
		clear();
					
		if(key == 13) {
			var pageNo = document.getElementById(fldPageNo).value;
			var actPage = document.getElementById(hdnMyPage).value;   

			if(pageNo == "") {
				alert("Please Enter Page Number.");
				document.getElementById(fldPageNo).focus();
				return false;
			}
			
			if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
				document.getElementById(fldPageNo).focus();
				document.getElementById(fldPageNo).value=actPage;
				return false;
			}
		    
		    if(Number(totalPage) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPage + ".");
				document.getElementById(fldPageNo).focus();
				document.getElementById(fldPageNo).value = actPage;  
				return false;
			}
		    
		    if(pageNo == actPage) {
				document.getElementById(fldPageNo).focus();
				return false;
			}
	        
	        document.getElementById(hdnMyPage).value = pageNo;
			document.getElementById('paraFrm').action = action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
	}
	
	function viewDetails(reqId) {
		document.getElementById('paraFrm').action = 'ReportRequestChangeApproval_viewDetails.action?reqId=' + reqId;
		document.getElementById('paraFrm').submit();
	}
</script>