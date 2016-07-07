

<%@ taglib prefix="s" uri="/struts-tags"%>
<%try{ %>
<%
String str="";
if(request.getAttribute("messageStr")!=null){
	 str=(String)request.getAttribute("messageStr");
}else{
	str="";
}
System.out.println("here action message"+str);
%>
<s:form action="DepartmentChangeApproval" name="DepartmentChangeApprovalList" id="paraFrm" validate="true" target="main" theme="simple">
	<s:hidden name="myMessage" value="<%=str%>" id="myMessage"/>
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
						<td width="92%" class="txt"><strong class="text_head">Department/Location Change Approval</strong></td>
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
														
														<%try{
															totalPageForPendingApp = (Integer) request.getAttribute("totalPageForPendingApp");
															pageNoForPendingApp = (Integer) request.getAttribute("pageNoForPendingApp");
														}catch(Exception e){}%>
														<a href="#" onclick="callPDCPage('1', 'F', '<%=totalPageForPendingApp%>', 
														'DepartmentChangeApproval_callPage.action', 'fldPageNoForPending', 'pageForPendingApp');">
															<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" 
															class="iconImage" />
														</a>&nbsp;
														<a href="#" onclick="callPDCPage('P', 'P', '<%=totalPageForPendingApp%>', 
														'DepartmentChangeApproval_callPage.action', 'fldPageNoForPending', 'pageForPendingApp');">
															<img title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" 
															class="iconImage" />
														</a>
														<input type="text" name="fldPageNoForPending" size="3" value="<%=pageNoForPendingApp%>" 
														maxlength="10" onkeypress="callPageText(event, '<%=totalPageForPendingApp%>', 
														'DepartmentChangeApproval_callPage.action', 'fldPageNoForPending', 'pageForPendingApp');
														return numbersOnly();" /> of <%=totalPageForPendingApp%>
														<a href="#" onclick="callPDCPage('N', 'N', '<%=totalPageForPendingApp%>', 
														'DepartmentChangeApproval_callPage.action', 'fldPageNoForPending', 'pageForPendingApp')">
															<img title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
														</a>&nbsp;
														<a href="#" onclick="callPDCPage('<%=totalPageForPendingApp%>', 'L', '<%=totalPageForPendingApp%>', 
														'DepartmentChangeApproval_callPage.action', 'fldPageNoForPending', 'pageForPendingApp');">
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
												<td width="5%" valign="top" class="formth">Sr No.</td>
												<td width="7%" valign="top" class="formth" nowrap="nowrap">Tracking No</td>
												<td width="20%" valign="top" class="formth">Employee</td>
												<td width="10%" valign="top" class="formth" nowrap="nowrap">Application Date</td>
												<td width="10%" valign="top" class="formth" nowrap="nowrap">Details</td>
											</tr>
											<%	int paCount = 0; %>
											
											<s:iterator value="pendingAppList">
												<tr>
													<td class="sortableTD"><%=++paCount%></td>
													<td class="sortableTD" nowrap="nowrap">
														<s:hidden name="deptDataChangeId" />
														<!--<s:property value="empToken" />
													-->
													<s:property value="authorizedToken" />
													</td>
													<td class="sortableTD">
														<s:property value="empName" />
													</td>
													<td class="sortableTD" align="center">
														<s:property value="applicationDate" />
													</td>
													<td class="sortableTD" align="center">
														<input type="button" name="view_Details" class="token" value=" View / Approve " 
														onclick="viewDetails('<s:property value="deptDataChangeId"/>')" />
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
		<tr id="approved" style="display: none">
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
												try{totalPageForApprovedApp = (Integer) request.getAttribute("totalPageForApprovedApp");
												pageNoForApprovedApp = (Integer) request.getAttribute("pageNoForApprovedApp");
											}catch(Exception e){}%>
											<a href="#" onclick="callPDCPage('1', 'F', '<%=totalPageForApprovedApp%>', 
											'DepartmentChangeApproval_callPage.action', 'fldPageNoForApproved', 'pageForApprovedApp');">
												<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage" />
											</a>&nbsp;
											<a href="#" onclick="callPDCPage('P', 'P', '<%=totalPageForApprovedApp%>', 
											'DepartmentChangeApproval_callPage.action', 'fldPageNoForApproved', 'pageForApprovedApp');">
												<img title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" 
												class="iconImage" />
											</a>
											<input type="text" name="fldPageNoForApproved" size="3" value="<%=pageNoForApprovedApp%>" 
											maxlength="10" onkeypress="callPageText(event, '<%=totalPageForApprovedApp%>', 
											'DepartmentChangeApproval_callPage.action', 'fldPageNoForApproved', 'pageForApprovedApp');
											return numbersOnly();" /> of <%=totalPageForApprovedApp%>
											<a href="#" onclick="callPDCPage('N', 'N', '<%=totalPageForApprovedApp%>', 
											'DepartmentChangeApproval_callPage.action', 'fldPageNoForApproved', 'pageForApprovedApp')">
												<img title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
											</a>&nbsp;
											<a href="#" onclick="callPDCPage('<%=totalPageForApprovedApp%>', 'L', '<%=totalPageForApprovedApp%>', 
											'DepartmentChangeApproval_callPage.action', 'fldPageNoForApproved', 'pageForApprovedApp');">
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
									<td width="5%" valign="top" class="formth">Sr No.</td>
									<td width="7%" valign="top" class="formth" nowrap="nowrap">Tracking No</td>
									<td width="20%" valign="top" class="formth">Employee</td>
									<td width="10%" valign="top" class="formth" nowrap="nowrap">Application Date</td>
									<td width="10%" valign="top" class="formth" nowrap="nowrap">Details</td>
								</tr>
								<%	int aaCount = 0; %>
								<s:iterator value="approvedAppList">
									<tr>
										<td class="sortableTD"><%=++aaCount%></td>
										<td class="sortableTD" nowrap="nowrap">
											<s:hidden name="deptDataChangeId" />
											<!--<s:property value="empToken" />
										-->
										<s:property value="authorizedToken" />
										</td>
										<td class="sortableTD">
											<s:property value="empName" />
										</td>
										<td class="sortableTD" align="center">
											<s:property value="applicationDate" />
										</td>
										<td class="sortableTD" align="center">
											<input type="button" name="view_Details" class="token" value=" View " 
											onclick="viewDetails('<s:property value="deptDataChangeId"/>')" />
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
		
		<tr id="rejected" style="display: none">
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
												try{totalPageForRejectedApp = (Integer) request.getAttribute("totalPageForRejectedApp");
												pageNoForRejectedApp = (Integer) request.getAttribute("pageNoForRejectedApp");
												}catch(Exception e){}%>
											<a href="#" onclick="callPDCPage('1', 'F', '<%=totalPageForRejectedApp%>', 
											'DepartmentChangeApproval_callPage.action', 'fldPageNoForRejected', 'pageForRejectedApp');">
												<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage" />
											</a>&nbsp;
											<a href="#" onclick="callPDCPage('P', 'P', '<%=totalPageForRejectedApp%>', 
											'DepartmentChangeApproval_callPage.action', 'fldPageNoForRejected', 'pageForRejectedApp');">
												<img title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" 
												class="iconImage" />
											</a>
											<input type="text" name="fldPageNoForRejected" size="3" value="<%=pageNoForRejectedApp%>" 
											maxlength="10" onkeypress="callPageText(event, '<%=totalPageForRejectedApp%>', 
											'DepartmentChangeApproval_callPage.action', 'fldPageNoForRejected', 'pageForRejectedApp');
											return numbersOnly();" /> of <%=totalPageForRejectedApp%>
											<a href="#" onclick="callPDCPage('N', 'N', '<%=totalPageForRejectedApp%>', 
											'DepartmentChangeApproval_callPage.action', 'fldPageNoForRejected', 'pageForRejectedApp')">
												<img title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
											</a>&nbsp;
											<a href="#" onclick="callPDCPage('<%=totalPageForRejectedApp%>', 'L', '<%=totalPageForRejectedApp%>', 
											'DepartmentChangeApproval_callPage.action', 'fldPageNoForRejected', 'pageForRejectedApp');">
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
									<td width="5%" valign="top" class="formth">Sr No.</td>
									<td width="7%" valign="top" class="formth" nowrap="nowrap">Tracking No</td>
									<td width="20%" valign="top" class="formth">Employee</td>
									<td width="10%" valign="top" class="formth" nowrap="nowrap">Application Date</td>
									<td width="10%" valign="top" class="formth" nowrap="nowrap">Details</td>
								</tr>
								<%	int raCount = 0; %>
								<s:iterator value="rejectedAppList">
									<tr>
									<td class="sortableTD"><%=++raCount%></td>
									<td class="sortableTD" nowrap="nowrap">
										<s:hidden name="deptDataChangeId" />
										<!--<s:property value="empToken" />
									-->
									<s:property value="authorizedToken" />
									</td>
									<td class="sortableTD">
										<s:property value="empName" />
									</td>
									<td class="sortableTD" align="center">
										<s:property value="applicationDate" />
									</td>
									<td class="sortableTD" align="center">
										<input type="button" name="view_Details" class="token" value=" View " 
										onclick="viewDetails('<s:property value="deptDataChangeId"/>')" />
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
window.onload=callMessage();
	call();



	function callMessage() {
	try{
		var message = document.getElementById("myMessage").value;
		if(!trim(document.getElementById("myMessage").value)=="" ) {
			alert(message);
		}
	}catch(e){
      }
}
	function callPending() {
 		document.getElementById('pending').style.display = '';
		document.getElementById('approved').style.display = 'none';
		document.getElementById('rejected').style.display = 'none';
	}
 
	function callApproved() {
 		document.getElementById('pending').style.display = 'none';
		document.getElementById('approved').style.display = '';
		document.getElementById('rejected').style.display = 'none';

	}

	function callRejected() {
	
 		document.getElementById('pending').style.display = 'none';
		document.getElementById('approved').style.display = 'none';
		document.getElementById('rejected').style.display = '';
	}

	function call() {
		document.getElementById('pending').style.display = '';
		document.getElementById('approved').style.display = 'none';
		document.getElementById('rejected').style.display = 'none';
	}
	
	function viewDetails(deptDataChangeId) {
		document.getElementById('paraFrm').action = 'DepartmentLocationChange_viewDetails.action?deptDataId=' + deptDataChangeId;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "_self";
	}
	
	function viewList() {
		document.getElementById("paraFrm").target = 'main';
		document.getElementById('paraFrm').action = 'DepartmentChangeApproval_input.action';
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
<%}catch(Exception e){
	e.printStackTrace();
}%>