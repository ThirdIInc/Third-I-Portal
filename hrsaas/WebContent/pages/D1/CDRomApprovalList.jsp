<!-- Bhushan Dasare --><!-- Feb 15, 2011 -->
<!-- Nilesh Dhandare -->

<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="CDRomApproval" name="CDRomApprovalList" id="paraFrm" validate="true" target="main" theme="simple">
	<s:hidden name="listType" />

	<table width="100%" class="formbg" align="right">
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt">
							<strong class="formhead">
								<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" />
							</strong>
						</td>
						<td width="92%" class="txt"><strong class="text_head">CDROM Approval</strong></td>
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
				<a href="#"  onclick="callRejected();">Rejected Applications</a>
			</td>
		</tr>
		<tr id="pending">
			<td>
				<table width="100%" class="formbg">
					<tr>
						<td width="100%">
							<table width="100%" class="formbg">
								<tr>
									<td width="30%" class="formtxt"><strong>Pending Applications</strong></td>
									<td align="right">
										<table width="100%">
											<tr>
												<s:hidden name="pageForPendingApp" id="pageForPendingApp" />
												<%
													int totalPageForPendingApp = 0;
													int pageNoForPendingApp = 0;
												%>
												<s:if test="pagingForPendingApp">
													<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
														<%
															totalPageForPendingApp = (Integer) request.getAttribute("totalPageForPendingApp");
															pageNoForPendingApp = (Integer) request.getAttribute("pageNoForPendingApp");
														%>
														<a href="#" onclick="callPDCPage('1', 'F', '<%=totalPageForPendingApp%>', 
														'CDRomApproval_callPage.action', 'fldPageNoForPending', 'pageForPendingApp');">
															<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" 
															class="iconImage" />
														</a>&nbsp;
														<a href="#" onclick="callPDCPage('P', 'P', '<%=totalPageForPendingApp%>', 
														'CDRomApproval_callPage.action', 'fldPageNoForPending', 'pageForPendingApp');">
															<img title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" 
															class="iconImage" />
														</a>
														<input type="text" name="fldPageNoForPending" size="3" value="<%=pageNoForPendingApp%>" 
														maxlength="10" onkeypress="callPageText(event, '<%=totalPageForPendingApp%>', 
														'CDRomApproval_callPage.action', 'fldPageNoForPending', 'pageForPendingApp');
														return numbersOnly();" /> of <%=totalPageForPendingApp%>
														<a href="#" onclick="callPDCPage('N', 'N', '<%=totalPageForPendingApp%>', 
														'CDRomApproval_callPage.action', 'fldPageNoForPending', 'pageForPendingApp')">
															<img title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
														</a>&nbsp;
														<a href="#" onclick="callPDCPage('<%=totalPageForPendingApp%>', 'L', '<%=totalPageForPendingApp%>', 
														'CDRomApproval_callPage.action', 'fldPageNoForPending', 'pageForPendingApp');">
															<img title="Last Page" src="../pages/common/img/last.gif" width="10" height="10" 
															class="iconImage" />
														</a>
													</td>
												</s:if>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td width="100%" colspan="2">
										<table width="100%" class="sortable">
											<tr class="td_bottom_border">
												<td width="10%" valign="top" class="formth">Sr No.</td>
												<td width="35%" valign="top" class="formth">Tracking No.</td>
												<td width="35%" valign="top" class="formth">Employee</td>
												<td width="10%" valign="top" class="formth" nowrap="nowrap">Application Date</td>
												<td width="10%" valign="top" class="formth" nowrap="nowrap">Details</td>
											</tr>
											<%	int paCount = 0; %>
											<s:iterator value="pendingAppList">
												<tr>
													<td class="sortableTD"><%=++paCount%></td>
													<td class="sortableTD">
														<s:hidden name="cdRomID" />
														<s:property value="authorizedToken"/>
													</td>
													<td class="sortableTD">
														<s:property value="empName" />
													</td>
													<td class="sortableTD" align="center">
														<s:property value="applicationDate" />
													</td>
													<td class="sortableTD" align="center">
														<input type="button" name="view_Details" class="token" value=" View / Approve " 
														onclick="viewDetails('<s:property value="cdRomID"/>')" />
													</td>
												</tr>
											</s:iterator>
											<% if(paCount == 0) { %>
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
			</td>
		</tr>
		
		<tr id="approved">
			<td>
				<table width="100%" class="formbg">
					<tr>
						<td width="30%" class="formtxt"><strong>Approved Applications</strong></td>
						<td align="right">
							<table width="100%">
								<tr>
									<s:hidden name="pageForApprovedApp" id="pageForApprovedApp" />
									<%
										int totalPageForApprovedApp = 0;
										int pageNoForApprovedApp = 0;
									%>
									<s:if test="pagingForApprovedApp">
										<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
											<%
												totalPageForApprovedApp = (Integer) request.getAttribute("totalPageForApprovedApp");
												pageNoForApprovedApp = (Integer) request.getAttribute("pageNoForApprovedApp");
											%>
											<a href="#" onclick="callPDCPage('1', 'F', '<%=totalPageForApprovedApp%>', 
											'CDRomApproval_callPage.action', 'fldPageNoForApproved', 'pageForApprovedApp');">
												<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage" />
											</a>&nbsp;
											<a href="#" onclick="callPDCPage('P', 'P', '<%=totalPageForApprovedApp%>', 
											'CDRomApproval_callPage.action', 'fldPageNoForApproved', 'pageForApprovedApp');">
												<img title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" 
												class="iconImage" />
											</a>
											<input type="text" name="fldPageNoForApproved" size="3" value="<%=pageNoForApprovedApp%>" 
											maxlength="10" onkeypress="callPageText(event, '<%=totalPageForApprovedApp%>', 
											'CDRomApproval_callPage.action', 'fldPageNoForApproved', 'pageForApprovedApp');
											return numbersOnly();" /> of <%=totalPageForApprovedApp%>
											<a href="#" onclick="callPDCPage('N', 'N', '<%=totalPageForApprovedApp%>', 
											'CDRomApproval_callPage.action', 'fldPageNoForApproved', 'pageForApprovedApp')">
												<img title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
											</a>&nbsp;
											<a href="#" onclick="callPDCPage('<%=totalPageForApprovedApp%>', 'L', '<%=totalPageForApprovedApp%>', 
											'CDRomApproval_callPage.action', 'fldPageNoForApproved', 'pageForApprovedApp');">
												<img title="Last Page" src="../pages/common/img/last.gif" width="10" height="10" 
												class="iconImage" />
											</a>
										</td>
									</s:if>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td width="100%" colspan="2">
							<table width="100%" class="sortable">
								<tr class="td_bottom_border">
												<td width="10%" valign="top" class="formth">Sr No.</td>
												<td width="35%" valign="top" class="formth">Tracking No.</td>
												<td width="35%" valign="top" class="formth">Employee</td>
												<td width="10%" valign="top" class="formth" nowrap="nowrap">Application Date</td>
												<td width="10%" valign="top" class="formth" nowrap="nowrap">Details</td>
											</tr>
								<%	int aaCount = 0; %>
								<s:iterator value="approvedAppList">
									<tr>
										<td class="sortableTD"><%=++aaCount%></td>
										<td class="sortableTD">
											<s:hidden name="cdRomID" />
											<s:property value="authorizedToken"/>
										</td>
										<td class="sortableTD">
											<s:property value="empName" />
										</td>
										<td class="sortableTD" align="center">
											<s:property value="applicationDate" />
										</td>
										<td class="sortableTD" align="center">
											<input type="button" name="View Application" class="token" value=" View " 
											onclick="viewDetails('<s:property value="cdRomID"/>')" />
										</td>
									</tr>
								</s:iterator>
								<% if(aaCount == 0) { %>
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
						<td width="30%" class="formtxt"><strong>Rejected Applications</strong></td>
						<td align="right">
							<table width="100%">
								<tr>
									<s:hidden name="pageForRejectedApp" id="pageForRejectedApp" />
									<%
										int totalPageForRejectedApp = 0;
										int pageNoForRejectedApp = 0;
									%>
									<s:if test="pagingForRejectedApp">
										<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
											<%
												totalPageForRejectedApp = (Integer) request.getAttribute("totalPageForRejectedApp");
												pageNoForRejectedApp = (Integer) request.getAttribute("pageNoForRejectedApp");
											%>
											<a href="#" onclick="callPDCPage('1', 'F', '<%=totalPageForRejectedApp%>', 
											'CDRomApproval_callPage.action', 'fldPageNoForRejected', 'pageForRejectedApp');">
												<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage" />
											</a>&nbsp;
											<a href="#" onclick="callPDCPage('P', 'P', '<%=totalPageForRejectedApp%>', 
											'CDRomApproval_callPage.action', 'fldPageNoForRejected', 'pageForRejectedApp');">
												<img title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" 
												class="iconImage" />
											</a>
											<input type="text" name="fldPageNoForRejected" size="3" value="<%=pageNoForRejectedApp%>" 
											maxlength="10" onkeypress="callPageText(event, '<%=totalPageForRejectedApp%>', 
											'CDRomApproval_callPage.action', 'fldPageNoForRejected', 'pageForRejectedApp');
											return numbersOnly();" /> of <%=totalPageForRejectedApp%>
											<a href="#" onclick="callPDCPage('N', 'N', '<%=totalPageForRejectedApp%>', 
											'CDRomApproval_callPage.action', 'fldPageNoForRejected', 'pageForRejectedApp')">
												<img title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
											</a>&nbsp;
											<a href="#" onclick="callPDCPage('<%=totalPageForRejectedApp%>', 'L', '<%=totalPageForRejectedApp%>', 
											'CDRomApproval_callPage.action', 'fldPageNoForRejected', 'pageForRejectedApp');">
												<img title="Last Page" src="../pages/common/img/last.gif" width="10" height="10" 
												class="iconImage" />
											</a>
										</td>
									</s:if>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td width="100%" colspan="2">
							<table width="100%" class="sortable">
								<tr class="td_bottom_border">
												<td width="10%" valign="top" class="formth">Sr No.</td>
												<td width="35%" valign="top" class="formth">Tracking No.</td>
												<td width="35%" valign="top" class="formth">Employee</td>
												<td width="10%" valign="top" class="formth" nowrap="nowrap">Application Date</td>
												<td width="10%" valign="top" class="formth" nowrap="nowrap">Details</td>
											</tr>
								<%	int raCount = 0; %>
								<s:iterator value="rejectedAppList">
								<tr>
									<td class="sortableTD"><%=++raCount%></td>
									<td class="sortableTD">
										<s:hidden name="cdRomID" />
										<s:property value="authorizedToken"/>
									</td>
									<td class="sortableTD">
										<s:property value="empName" />
									</td>
									<td class="sortableTD" align="center">
										<s:property value="applicationDate" />
									</td>
									<td class="sortableTD" align="center">
										<input type="button" name="View Application" class="token" value=" View " 
										onclick="viewDetails('<s:property value="cdRomID"/>')" />
									</td>
									</tr>
								</s:iterator>
								<% if(raCount == 0) { %>
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

<script>
	loadList();
	
	function loadList() {
		document.getElementById('pending').style.display = 'none';
		document.getElementById('approved').style.display = 'none';
		document.getElementById('rejected').style.display = 'none';
		
		var listType = document.getElementById('paraFrm_listType').value;
		document.getElementById(listType).style.display = '';
	}

	function callPending() {
	//alert("inside pending")
		document.getElementById('paraFrm').action = 'CDRomApproval_getPendingList.action';
		document.getElementById('paraFrm').submit();
	}
 
	function callApproved() {
		document.getElementById('paraFrm').action = 'CDRomApproval_getApprovedList.action';
		document.getElementById('paraFrm').submit();
	}

	function callRejected() {
		document.getElementById('paraFrm').action = 'CDRomApproval_getRejectedList.action';
		document.getElementById('paraFrm').submit();
	}	
	
	function viewDetails(cdromID) {
	//alert(cdromID)
		document.getElementById('paraFrm').action = 'CDRomRequest_viewDetails.action?cdRomId=' + cdromID;
		document.getElementById('paraFrm').submit();
	}
	
	function viewList() {
		document.getElementById("paraFrm").target = 'main';
		document.getElementById('paraFrm').action = 'CDRomApproval_input.action';
		document.getElementById('paraFrm').submit();
	}
	
	function callPDCPage(id, pageImg, totalPageHid, action, fldPageNo, hdnMyPage) {
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
</script>