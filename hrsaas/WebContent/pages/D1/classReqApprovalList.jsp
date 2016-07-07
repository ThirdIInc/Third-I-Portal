<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="ClassRequestApproval" name="ClassRequestApprovaList"
	id="paraFrm" validate="true" target="main" theme="simple">

<!-- Hidden Fields Start -->
	<s:hidden name="listType" />
	<s:hidden name="classAppCode" />
	<s:hidden name="applnStatus" />
	<s:hidden name="myPage" id="myPage" />
	<s:hidden name="myPagePendingCancel" id="myPagePendingCancel" />
	<s:hidden name="myPageApproved" id="myPageApproved" />
	<s:hidden name="myPageRejected" id="myPageRejected" />

<!-- Hidden Fields End -->


	<table width="100%" class="formbg" align="right">
		<tr>
			<td width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="92%" class="txt"><strong class="text_head">Class
					Request Approval</strong></td>
					<td width="4%" valign="middle" align="right" class="txt"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" />
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table class="" width="100%">
				<tr>
					<script>
		    function setAction(listType){
		    
		     if(listType=="p"){
		     
		      	document.getElementById('paraFrm').action='ClassRequestApproval_input.action';
		      	document.getElementById('paraFrm').submit();
		      
		     }if(listType=="a"){
		     
		      	document.getElementById('paraFrm').action='ClassRequestApproval_getApprovedList.action';
		      	document.getElementById('paraFrm').submit();
		      
		     }
		    
		     if(listType=="r"){
		        
		      	document.getElementById('paraFrm').action='ClassRequestApproval_getRejectedList.action';
		      	document.getElementById('paraFrm').submit();
		        
		     } 
		     
		    }
		   </script>
					<td><a href="#" onclick="setAction('p')">Pending
					Application</a> | <a href="#" onclick="setAction('a')">Approved
					List</a> | <a href="#" onclick="setAction('r')">Rejected List</a></td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- ========= Pending and pending cancellation application List Begins ============= -->
		<s:if test="%{listType == 'pending'}">
			<!-- Pending Application List Begins -->
			<tr>
				<td>
				<table class="formbg" width="100%" cellspacing="1" cellspadding="0"
					border="0">
					<tr>
						<td><strong class="text_head"> Pending Application
						List </strong></td>
						<td width="70%" align="right">
						<%
							int totalPendingPage = 0;
							int pendingPageNo = 0;
						%> <s:if test="pendingListLength">
							<table>
								<tr>
									<td id="ctrlShow" width="100%" align="right"><b>Page:</b>
									<%
												totalPendingPage = (Integer) request
												.getAttribute("totalPendingPage");
										pendingPageNo = (Integer) request.getAttribute("pendingPageNo");
									%> <a href="#"
										onclick="callPage('1', 'F', '<%=totalPendingPage%>', 'ClassRequestApproval_input.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPage('P', 'P', '<%=totalPendingPage%>', 'ClassRequestApproval_input.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text" name="pageNoField"
										id="pageNoField" size="3" value="<%=pendingPageNo%>"
										maxlength="10"
										onkeypress="callPageText(event, '<%=totalPendingPage%>', 'ClassRequestApproval_input.action');return numbersOnly();" />
									of <%=totalPendingPage%> <a href="#"
										onclick="callPage('N', 'N', '<%=totalPendingPage%>', 'ClassRequestApproval_input.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPage('<%=totalPendingPage%>', 'L', '<%=totalPendingPage%>', 'ClassRequestApproval_input.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
								</tr>
							</table>
						</s:if></td>
					</tr>


					<tr>
						<td colspan="2">
						<table width="100%" border="0" class="formbg">
							<tr>
								<td class="formtext">
								<table width="100%" border="0">
									<tr>
										<td class="formth" width="5%"><b><label class="set"
											name="sr.no" id="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></b>
										</td>

										<td class="formth" width="20%"><b><label class="set"
											name="tracking_num" id="tracking_num"
											ondblclick="callShowDiv(this);"><%=label.get("tracking_num")%></label></b>
										</td>

										<td class="formth" width="15%"><b><label class="set"
											name="class_name" id="class_name"
											ondblclick="callShowDiv(this);"><%=label.get("class_name")%></label></b>
										</td>

										<td class="formth" width="20%"><b><label class="set"
											name="class_desc" id="class_desc"
											ondblclick="callShowDiv(this);"><%=label.get("class_desc")%></label></b>
										</td>

										<td class="formth" width="20%"><b><label class="set"
											name="app_date" id="app_date" ondblclick="callShowDiv(this);"><%=label.get("app_date")%></label></b>
										</td>
										<td class="formth" width="20%"><b>View Application</b></td>

									</tr>


									<s:if test="pendingListLength">
										<%
										int count1 = 0;
										%>
										<%!int d1 = 0;%>
										<%
										int i1 = pendingPageNo * 20 - 20;
										%>

										<s:iterator value="pendingIteratorList">
											<tr>

												<td align="center" class="sortableTD"><%=++i1%></td>

												<td class="sortableTD"><s:property value="trackingNum" /></td>

												<td class="sortableTD" align="left"><s:hidden
													name="classAppCode" /><s:property value="className" /></td>

												<td class="sortableTD" align="left"><s:property
													value="classDescription" /></td>


												<td class="sortableTD" align="center"><s:property
													value="classAppDate" /></td>


												<td align="center"><input type="button" id="ctrlShow"
													value=" View Applications " class="view" align="middle"
													onclick="classApplicationFunction('<s:property value="classAppCode"/>')"></td>
											</tr>
										</s:iterator>

										<%
										d1 = i1;
										%>
									</s:if>

									<s:else>
										<td align="center" colspan="6" nowrap="nowrap"><font
											color="red">There is no data to display</font></td>
									</s:else>
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
				<td>
				<table class="formbg" width="100%" cellspacing="1" cellspadding="0"
					border="0">
					<tr>
						<td><strong class="text_head"> Pending Cancellation
						Application List </strong></td>
						<td width="70%" align="right">
						<%
							int totalPendingCancellationPage = 0;
							int pendingCancellationPageNo = 0;
						%> <s:if test="pendingCancellationListLength">
							<table>
								<tr>
									<td id="ctrlShow" width="100%" align="right"><b>Page:</b>
									<%
												totalPendingCancellationPage = (Integer) request
												.getAttribute("totalPendingCancellationPage");
										pendingCancellationPageNo = (Integer) request
												.getAttribute("pendingCancellationPageNo");
									%> <a href="#"
										onclick="callPagePendingCancel('1', 'F', '<%=totalPendingCancellationPage%>', 'ClassRequestApproval_input.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPagePendingCancel('P', 'P', '<%=totalPendingCancellationPage%>', 'ClassRequestApproval_input.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text"
										name="pageNopendingCancellationField"
										id="pageNopendingCancellationField" size="3"
										value="<%=pendingCancellationPageNo%>" maxlength="10"
										onkeypress="callPagePendingCancelText(event, '<%=totalPendingCancellationPage%>', 'ClassRequestApproval_input.action');return numbersOnly();" />
									of <%=totalPendingCancellationPage%> <a href="#"
										onclick="callPagePendingCancel('N', 'N', '<%=totalPendingCancellationPage%>', 'ClassRequestApproval_input.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPagePendingCancel('<%=totalPendingCancellationPage%>', 'L', '<%=totalPendingCancellationPage%>', 'ClassRequestApproval_input.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
								</tr>
							</table>
						</s:if></td>
					</tr>


					<tr>
						<td colspan="2">
						<table width="100%" border="0" class="formbg">
							<tr>
								<td class="formtext">
								<table width="100%" border="0">
									<tr>
										<td class="formtext">
										<table width="100%" border="0">
											<tr>
												<td class="formth" width="5%"><b><label class="set"
													name="sr.no" id="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></b>
												</td>

												<td class="formth" width="20%"><b><label
													class="set" name="tracking_num" id="tracking_num"
													ondblclick="callShowDiv(this);"><%=label.get("tracking_num")%></label></b>
												</td>

												<td class="formth" width="15%"><b><label
													class="set" name="class_name" id="class_name"
													ondblclick="callShowDiv(this);"><%=label.get("class_name")%></label></b>
												</td>

												<td class="formth" width="20%"><b><label
													class="set" name="class_desc" id="class_desc"
													ondblclick="callShowDiv(this);"><%=label.get("class_desc")%></label></b>
												</td>

												<td class="formth" width="20%"><b><label
													class="set" name="app_date" id="app_date"
													ondblclick="callShowDiv(this);"><%=label.get("app_date")%></label></b>
												</td>
												<td class="formth" width="20%"><b>Details</b></td>

											</tr>

											<s:if test="pendingCancellationListLength">
												<%
												int count7 = 0;
												%>
												<%!int d7 = 0;%>
												<%
												int i7 = pendingCancellationPageNo * 20 - 20;
												%>

												<s:iterator value="pendingCancellationIteratorList">
													<tr>

														<td align="center" class="sortableTD"><%=++i7%></td>

														<td class="sortableTD"><s:property
															value="trackingNum" /></td>

														<td class="sortableTD" align="left"><s:hidden
															name="classAppCode" /><s:property value="className" /></td>

														<td class="sortableTD" align="left"><s:property
															value="classDescription" /></td>


														<td class="sortableTD" align="center"><s:property
															value="classAppDate" /></td>


														<td align="center"><input type="button" id="ctrlShow"
															value=" View Applications " class="view" align="middle"
															onclick="classApplicationFunction('<s:property value="classAppCode"/>')"></td>
													</tr>
												</s:iterator>

												<%
												d7 = i7;
												%>
											</s:if>

											<s:else>
												<td align="center" colspan="6" nowrap="nowrap"><font
													color="red">There is no data to display</font></td>
											</s:else>

										</table>
										</td>
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


		</s:if>

		<!-- Pending Application List  & Pending Cancellation Application List Ends-->

		<!-- ========= Approved application List Begins ============= -->
		<s:if test="%{listType == 'approved'}">
			<tr>
				<td>
				<table class="formbg" width="100%" cellspacing="1" cellspadding="0"
					border="0">
					<tr>
						<td><strong class="text_head"> Approved Application
						List </strong></td>
						<td width="70%" align="right">
						<%
										int totalApprovedPage = 0;
										int approvedPageNo = 0;
									%> <s:if test="approvedListLength">
							<table>
								<tr>
									<td id="ctrlShow" width="100%" align="right"><b>Page:</b>
									<%
															totalApprovedPage = (Integer) request
															.getAttribute("totalApprovedPage");
													approvedPageNo = (Integer) request.getAttribute("approvedPageNo");
												%> <a href="#"
										onclick="callPageApproved('1', 'F', '<%=totalApprovedPage%>', 'ClassRequestApproval_getApprovedList.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPageApproved('P', 'P', '<%=totalApprovedPage%>', 'ClassRequestApproval_getApprovedList.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text"
										name="pageNoApprovedField" id="pageNoApprovedField" size="3"
										value="<%=approvedPageNo%>" maxlength="10"
										onkeypress="callPageApprovedText(event, '<%=totalApprovedPage%>', 'ClassRequestApproval_getApprovedList.action');return numbersOnly();" />
									of <%=totalApprovedPage%> <a href="#"
										onclick="callPageApproved('N', 'N', '<%=totalApprovedPage%>', 'ClassRequestApproval_getApprovedList.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPageApproved('<%=totalApprovedPage%>', 'L', '<%=totalApprovedPage%>', 'ClassRequestApproval_getApprovedList.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
								</tr>
							</table>
						</s:if></td>
					</tr>


					<tr>
						<td colspan="2">
						<table width="100%" border="0" class="formbg">
							<tr>
								<td class="formtext">
								<table width="100%" border="0">
									<tr>
										<td class="formth" width="5%"><b><label class="set"
											name="sr.no" id="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></b>
										</td>
										<td class="formth" width="20%"><b><label class="set"
											name="tracking_num" id="tracking_num"
											ondblclick="callShowDiv(this);"><%=label.get("tracking_num")%></label></b>
										</td>

										<td class="formth" width="15%"><b><label class="set"
											name="class_name" id="class_name"
											ondblclick="callShowDiv(this);"><%=label.get("class_name")%></label></b>
										</td>

										<td class="formth" width="20%"><b><label class="set"
											name="class_desc" id="class_desc"
											ondblclick="callShowDiv(this);"><%=label.get("class_desc")%></label></b>
										</td>

										<td class="formth" width="20%"><b><label class="set"
											name="app_date" id="app_date" ondblclick="callShowDiv(this);"><%=label.get("app_date")%></label></b>
										</td>
										<td class="formth" width="20%"><b>View Application</b></td>

									</tr>

									<s:if test="approvedListLength">
										<%
													int count2 = 0;
													%>
										<%!int d2 = 0;%>
										<%
													int i2 = approvedPageNo * 20 - 20;
													%>

										<s:iterator value="approveredIteratorList">
											<tr>

												<td align="center" class="sortableTD"><%=++i2%></td>

												<td class="sortableTD"><s:property value="trackingNum" /></td>
												<td class="sortableTD" align="left"><s:hidden
													name="classAppCode" /><s:property value="className" /></td>

												<td class="sortableTD" align="left"><s:property
													value="classDescription" /></td>


												<td class="sortableTD" align="center"><s:property
													value="classAppDate" /></td>


												<td align="center"><input type="button" id="ctrlShow"
													value=" View Applications " class="view" align="middle"
													onclick="classApplicationFunction('<s:property value="classAppCode"/>')"></td>
											</tr>
										</s:iterator>

										<%
													d2 = i2;
													%>
									</s:if>

									<s:else>
										<td align="center" colspan="6" nowrap="nowrap"><font
											color="red">There is no data to display</font></td>
									</s:else>
								</table>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
		<!-- ========= Approved application List Ends ============= -->



		<!-- ========= Rejected application  application List Begins ============= -->
		<s:if test="%{listType == 'rejected'}">

			<tr>
				<td>
				<table class="formbg" width="100%" cellspacing="1" cellspadding="0"
					border="0">
					<tr>
						<td><strong class="text_head"> Rejected Application
						List </strong></td>
						<td width="70%" align="right">
						<%
										int totalRejectedPage = 0;
										int rejectedPageNo = 0;
									%> <s:if test="rejectedListLength">
							<table>
								<tr>
									<td id="ctrlShow" width="100%" align="right"><b>Page:</b>
									<%
															totalRejectedPage = (Integer) request
															.getAttribute("totalRejectedPage");
													rejectedPageNo = (Integer) request.getAttribute("rejectedPageNo");
												%> <a href="#"
										onclick="callPageRejected('1', 'F', '<%=totalRejectedPage%>', 'ClassRequestApproval_getRejectedList.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPageRejected('P', 'P', '<%=totalRejectedPage%>', 'ClassRequestApproval_getRejectedList.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text"
										name="pageNoRejectedField" id="pageNoRejectedField" size="3"
										value="<%=rejectedPageNo%>" maxlength="10"
										onkeypress="callPageRejectedText(event, '<%=totalRejectedPage%>', 'ClassRequestApproval_getRejectedList.action');return numbersOnly();" />
									of <%=totalRejectedPage%> <a href="#"
										onclick="callPageRejected('N', 'N', '<%=totalRejectedPage%>', 'ClassRequestApproval_getRejectedList.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPageRejected('<%=totalRejectedPage%>', 'L', '<%=totalRejectedPage%>', 'ClassRequestApproval_getRejectedList.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
								</tr>
							</table>
						</s:if></td>
					</tr>


					<tr>
						<td colspan="2">
						<table width="100%" border="0" class="formbg">
							<tr>
								<td class="formtext">
								<table width="100%" border="0">
									<tr>
										<td class="formth" width="5%"><b><label class="set"
											name="sr.no" id="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></b>
										</td>

										<td class="formth" width="20%"><b><label class="set"
											name="tracking_num" id="tracking_num"
											ondblclick="callShowDiv(this);"><%=label.get("tracking_num")%></label></b>
										</td>
										<td class="formth" width="15%"><b><label class="set"
											name="class_name" id="class_name"
											ondblclick="callShowDiv(this);"><%=label.get("class_name")%></label></b>
										</td>

										<td class="formth" width="20%"><b><label class="set"
											name="class_desc" id="class_desc"
											ondblclick="callShowDiv(this);"><%=label.get("class_desc")%></label></b>
										</td>

										<td class="formth" width="20%"><b><label class="set"
											name="app_date" id="app_date" ondblclick="callShowDiv(this);"><%=label.get("app_date")%></label></b>
										</td>
										<td class="formth" width="20%"><b>Details</b></td>

									</tr>

									<s:if test="rejectedListLength">
										<%
													int count5 = 0;
													%>
										<%!int d5 = 0;%>
										<%
													int i5 = rejectedPageNo * 20 - 20;
													%>

										<s:iterator value="rejectedIteratorList">
											<tr>

												<td align="center" class="sortableTD"><%=++i5%></td>

												<td class="sortableTD"><s:property value="trackingNum" /></td>

												<td class="sortableTD" align="left"><s:hidden
													name="classAppCode" /><s:property value="className" /></td>

												<td class="sortableTD" align="left"><s:property
													value="classDescription" /></td>


												<td class="sortableTD" align="center"><s:property
													value="classAppDate" /></td>


												<td align="center"><input type="button" id="ctrlShow"
													value=" View Applications " class="view" align="middle"
													onclick="classApplicationFunction('<s:property value="classAppCode"/>')"></td>
											</tr>
										</s:iterator>

										<%
													d5 = i5;
													%>
									</s:if>

									<s:else>
										<td align="center" colspan="6" nowrap="nowrap"><font
											color="red">There is no data to display</font></td>
									</s:else>
								</table>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
		<!-- ========= Rejected application  application List Ends ============= -->

	</table>
	</td>
	</tr>
	</table>
</s:form>
<script>
function callPending() {
 		document.getElementById('pending').style.display = '';
		//document.getElementById('pendingcancel').style.display = '';
		document.getElementById('approved').style.display = 'none';
		document.getElementById('rejected').style.display = 'none';
	}
 
	function callApproved() {
 		document.getElementById('pending').style.display = 'none';
		//document.getElementById('pendingcancel').style.display = 'none';
		document.getElementById('approved').style.display = '';
		document.getElementById('rejected').style.display = 'none';

	}

	function callRejected() {
 		document.getElementById('pending').style.display = 'none';
		//document.getElementById('pendingcancel').style.display = 'none';
		document.getElementById('approved').style.display = 'none';
		document.getElementById('rejected').style.display = '';
	}
	

	
	
	function classApplicationFunction(classAppCode)
{
/*alert(classAppCode);*/
	  document.getElementById('paraFrm').target = "_self";
   	  document.getElementById('paraFrm').action="ClassRequestApproval_classApplicationFunction.action?classAppCode="+classAppCode;
	  document.getElementById('paraFrm').submit();
}

	
	function viewList() {
		document.getElementById("paraFrm").target = 'main';
		document.getElementById('paraFrm').action = 'ClassRequestApproval_input.action';
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