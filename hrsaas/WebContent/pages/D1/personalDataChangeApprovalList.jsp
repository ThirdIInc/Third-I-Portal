<!-- Bhushan Dasare --><!-- Feb 15, 2011 -->

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="PersonalDataChangeApproval" id="paraFrm" validate="true" target="main" theme="simple">
	<s:hidden name="listType" />

	
	<s:hidden name="myPage" id="myPage" />
	<s:hidden name="myPagePendingCancel" id="myPagePendingCancel" />
	<s:hidden name="myPageApproved" id="myPageApproved" />
	<s:hidden name="myPageRejected" id="myPageRejected" />
	
	
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
						<td width="92%" class="txt"><strong class="text_head">Personal Data Change Approval</strong></td>
						<td width="4%" valign="middle" align="right" class="txt">
							<img src="../pages/images/recruitment/help.gif" width="16" height="16" />
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
		     
		      	document.getElementById('paraFrm').action='PersonalDataChangeApproval_input.action';
		      	document.getElementById('paraFrm').submit();
		      
		     }if(listType=="a"){
		     
		      	document.getElementById('paraFrm').action='PersonalDataChangeApproval_getApprovedList.action';
		      	document.getElementById('paraFrm').submit();
		      
		     }
		    
		     if(listType=="r"){
		        
		      	document.getElementById('paraFrm').action='PersonalDataChangeApproval_getRejectedList.action';
		      	document.getElementById('paraFrm').submit();
		        
		     } 
		     
		    }
		   </script>
					<td><a href="#" onclick="setAction('p')">Pending
					Application</a> | <a href="#" onclick="setAction('a')">Approved
					List</a> | <a href="#" onclick="setAction('r')">Rejected
					List</a></td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- ========= Pending and pending cancellation application List Begins ============= -->
		<s:if test="%{listType == 'pending'}">
		<!-- Pending Application List Begins -->
		<tr>
			<td>
				<table class="formbg" width="100%" cellspacing="1" cellspadding="0"	border="0">
				<tr>
					<td>
						<strong class="text_head"> Pending Application List </strong>
					</td>
					<td width="70%" align="right">
						<%
							int totalPendingPage = 0;
							int pendingPageNo = 0;
						%>
							<s:if test="pendingListLength">
								<table>
								<tr>
									<td id="ctrlShow" width="100%" align="right"><b>Page:</b>
									<%
									totalPendingPage = (Integer) request.getAttribute("totalPendingPage");
									pendingPageNo = (Integer) request.getAttribute("pendingPageNo");
									%> <a href="#"
										onclick="callPage('1', 'F', '<%=totalPendingPage%>', 'PersonalDataChangeApproval_input.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPage('P', 'P', '<%=totalPendingPage%>', 'PersonalDataChangeApproval_input.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text" name="pageNoField"
										id="pageNoField" size="3" value="<%=pendingPageNo%>" maxlength="10"
										onkeypress="callPageText(event, '<%=totalPendingPage%>', 'PersonalDataChangeApproval_input.action');return numbersOnly();" />
									of <%=totalPendingPage%> <a href="#"
										onclick="callPage('N', 'N', '<%=totalPendingPage%>', 'PersonalDataChangeApproval_input.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPage('<%=totalPendingPage%>', 'L', '<%=totalPendingPage%>', 'PersonalDataChangeApproval_input.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
								</tr>
								</table>
							</s:if>
					</td>
				</tr>
				
							
				<tr>
					<td colspan="2">
						<table width="100%" border="0"  class="formbg">
							<tr>
								<td class="formtext">
								<table width="100%" border="0">
									<tr>								
									<td width="10%" colspan="1" class="formth"><label
										class="set" id="sr.no" name="sr.no"
										ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
									<td width="25%" colspan="1" class="formth"><label
										class="set" id="tracking.code" name="tracking.code"
										ondblclick="callShowDiv(this);"><%=label.get("tracking.code")%></label></td>
									<td width="25%" colspan="1" class="formth"><label
										class="set" id="emp.name" name="emp.name"
										ondblclick="callShowDiv(this);"><%=label.get("emp.name")%></label></td>
									<td width="20%" colspan="1" class="formth"><label
										class="set" id="application.Date" name="application.Date"
										ondblclick="callShowDiv(this);"><%=label.get("application.Date")%></label></td>

									<td width="20%" colspan="1" class="formth"><label
										class="set" id="view.application" name="view.application"
										ondblclick="callShowDiv(this);"><%=label.get("view.application")%></label></td>
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
											
											<td class="sortableTD" align="left"><s:hidden name="persDataChangeApproverId" /><s:property value="trackingNo"/></td>

											<td class="sortableTD" align="left"><s:property value="empName"/></td>


											<td class="sortableTD" align="center"><s:property value="applicationDate"/></td>
											

											<td  align="center"><input type="button"  id="ctrlShow"
															value=" View/Approve Applications " class="token" align="middle"
															onclick="viewApplicationFunction('<s:property value="persDataChangeApproverId"/>')"></td>								
										</tr>
									</s:iterator>

									<%
										d1 = i1;
									%>
								</s:if>
							
								<s:else>
									<td align="center" colspan="6" nowrap="nowrap">
										<font color="red" >There is no data to display</font>
									</td>
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
		<!-- Pending Application List Ends -->
		
		
		</s:if>
	<!-- ========= Pending  and Pending Cancellation application List Ends ============= -->		
		
		
		
	<!-- ========= Approved application List Begins ============= -->		
	<s:if test="%{listType == 'approved'}">	
		<tr>
			<td>
				<table class="formbg" width="100%" cellspacing="1" cellspadding="0"	border="0">
				<tr>
					<td>
						<strong class="text_head"> Approved Application List </strong>
					</td>
					<td width="70%" align="right">
						<%
							int totalApprovedPage = 0;
							int approvedPageNo = 0;
						%>
							<s:if test="approvedListLength">
								<table>
								<tr>
									<td id="ctrlShow" width="100%" align="right"><b>Page:</b>
									<%
									totalApprovedPage = (Integer) request.getAttribute("totalApprovedPage");
									approvedPageNo = (Integer) request.getAttribute("approvedPageNo");
									%> <a href="#"
										onclick="callPageApproved('1', 'F', '<%=totalApprovedPage%>', 'PersonalDataChangeApproval_getApprovedList.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPageApproved('P', 'P', '<%=totalApprovedPage%>', 'PersonalDataChangeApproval_getApprovedList.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text" name="pageNoApprovedField"
										id="pageNoApprovedField" size="3" value="<%=approvedPageNo%>" maxlength="10"
										onkeypress="callPageApprovedText(event, '<%=totalApprovedPage%>', 'PersonalDataChangeApproval_getApprovedList.action');return numbersOnly();" />
									of <%=totalApprovedPage%> <a href="#"
										onclick="callPageApproved('N', 'N', '<%=totalApprovedPage%>', 'PersonalDataChangeApproval_getApprovedList.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPageApproved('<%=totalApprovedPage%>', 'L', '<%=totalApprovedPage%>', 'PersonalDataChangeApproval_getApprovedList.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
								</tr>
								</table>
							</s:if>
					</td>
				</tr>
				
							
				<tr>
					<td colspan="2">
						<table width="100%" border="0"  class="formbg">
							<tr>
								<td class="formtext">
								<table width="100%" border="0">
									<tr>								
									<td width="10%" colspan="1" class="formth"><label
										class="set" id="sr.no" name="sr.no"
										ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
									<td width="25%" colspan="1" class="formth"><label
										class="set" id="tracking.code" name="tracking.code"
										ondblclick="callShowDiv(this);"><%=label.get("tracking.code")%></label></td>
									<td width="25%" colspan="1" class="formth"><label
										class="set" id="emp.name" name="emp.name"
										ondblclick="callShowDiv(this);"><%=label.get("emp.name")%></label></td>
									<td width="20%" colspan="1" class="formth"><label
										class="set" id="application.Date" name="application.Date"
										ondblclick="callShowDiv(this);"><%=label.get("application.Date")%></label></td>

									<td width="20%" colspan="1" class="formth"><label
										class="set" id="view.application" name="view.application"
										ondblclick="callShowDiv(this);"><%=label.get("view.application")%></label></td>
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
											
											<td class="sortableTD" align="left"><s:hidden name="persDataChangeApproverId" /><s:property value="trackingNo"/></td>

											<td class="sortableTD" align="left"><s:property value="empName"/></td>


											<td class="sortableTD" align="center"><s:property value="applicationDate"/></td>
											

											<td  align="center"><input type="button"  id="ctrlShow"
															value=" View Applications " class="token" align="middle"
															onclick="viewApplicationFunction('<s:property value="persDataChangeApproverId"/>')"></td>								
										</tr>
									</s:iterator>

									<%
										d2 = i2;
									%>
								</s:if>
							
								<s:else>
									<td align="center" colspan="6" nowrap="nowrap">
										<font color="red" >There is no data to display</font>
									</td>
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
				<table class="formbg" width="100%" cellspacing="1" cellspadding="0"	border="0">
				<tr>
					<td>
						<strong class="text_head"> Rejected Application List </strong>
					</td>
					<td width="70%" align="right">
						<%
							int totalRejectedPage = 0;
							int rejectedPageNo = 0;
						%>
							<s:if test="rejectedListLength">
								<table>
								<tr>
									<td id="ctrlShow" width="100%" align="right"><b>Page:</b>
									<%
									totalRejectedPage = (Integer) request.getAttribute("totalRejectedPage");
									rejectedPageNo = (Integer) request.getAttribute("rejectedPageNo");
									%> <a href="#"
										onclick="callPageRejected('1', 'F', '<%=totalRejectedPage%>', 'PersonalDataChangeApproval_getRejectedList.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPageRejected('P', 'P', '<%=totalRejectedPage%>', 'PersonalDataChangeApproval_getRejectedList.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text" name="pageNoRejectedField"
										id="pageNoRejectedField" size="3" value="<%=rejectedPageNo%>" maxlength="10"
										onkeypress="callPageRejectedText(event, '<%=totalRejectedPage%>', 'PersonalDataChangeApproval_getRejectedList.action');return numbersOnly();" />
									of <%=totalRejectedPage%> <a href="#"
										onclick="callPageRejected('N', 'N', '<%=totalRejectedPage%>', 'PersonalDataChangeApproval_getRejectedList.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPageRejected('<%=totalRejectedPage%>', 'L', '<%=totalRejectedPage%>', 'PersonalDataChangeApproval_getRejectedList.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
								</tr>
								</table>
							</s:if>
					</td>
				</tr>
				
							
				<tr>
					<td colspan="2">
						<table width="100%" border="0"  class="formbg">
							<tr>
								<td class="formtext">
								<table width="100%" border="0">
									<tr>								
									<td width="10%" colspan="1" class="formth"><label
										class="set" id="sr.no" name="sr.no"
										ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
									<td width="25%" colspan="1" class="formth"><label
										class="set" id="tracking.code" name="tracking.code"
										ondblclick="callShowDiv(this);"><%=label.get("tracking.code")%></label></td>
									<td width="25%" colspan="1" class="formth"><label
										class="set" id="emp.name" name="emp.name"
										ondblclick="callShowDiv(this);"><%=label.get("emp.name")%></label></td>
									<td width="20%" colspan="1" class="formth"><label
										class="set" id="application.Date" name="application.Date"
										ondblclick="callShowDiv(this);"><%=label.get("application.Date")%></label></td>

									<td width="20%" colspan="1" class="formth"><label
										class="set" id="view.application" name="view.application"
										ondblclick="callShowDiv(this);"><%=label.get("view.application")%></label></td>
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
											
											<td class="sortableTD" align="left"><s:hidden name="persDataChangeApproverId" /><s:property value="trackingNo"/></td>

											<td class="sortableTD" align="left"><s:property value="empName"/></td>


											<td class="sortableTD" align="center"><s:property value="applicationDate"/></td>
											

											<td  align="center"><input type="button"  id="ctrlShow"
															value=" View Applications " class="token" align="middle"
															onclick="viewApplicationFunction('<s:property value="persDataChangeApproverId"/>')"></td>								
										</tr>
									</s:iterator>

									<%
										d5 = i5;
									%>
								</s:if>
							
								<s:else>
									<td align="center" colspan="6" nowrap="nowrap">
										<font color="red" >There is no data to display</font>
									</td>
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
</s:form>

<script>

//new added

	function viewApplicationFunction(persDataChangeApproverId)
{//alert(persDataChangeApproverId);
	  document.getElementById('paraFrm').target = "_self";
   	  document.getElementById('paraFrm').action="PersonalDataChangeApproval_viewApplicationFunction.action?persDataChangeApproverId="+persDataChangeApproverId;
	  document.getElementById('paraFrm').submit();
}

//end
	
	
	

	function callPending() {
		document.getElementById('paraFrm').action = 'PersonalDataChangeApproval_getPendingList.action';
		document.getElementById('paraFrm').submit();
	}
 
	function callApproved() {
		document.getElementById('paraFrm').action = 'PersonalDataChangeApproval_getApprovedList.action';
		document.getElementById('paraFrm').submit();
	}

	function callRejected() {
		document.getElementById('paraFrm').action = 'PersonalDataChangeApproval_getRejectedList.action';
		document.getElementById('paraFrm').submit();
	}	
	
	function viewDetails(persDataChangeId) {
		document.getElementById('paraFrm').action = 'PersonalDataChange_viewDetails.action?persDataId=' + persDataChangeId;
		document.getElementById('paraFrm').submit();
	}
	
	function viewList() {
		document.getElementById("paraFrm").target = 'main';
		document.getElementById('paraFrm').action = 'PersonalDataChangeApproval_input.action';
		document.getElementById('paraFrm').submit();
	}
	
	// Function for Pending Cancellation List Begins
	function callPagePendingCancel(id, pageImg, totalPageHid, action) {		
		try
		{
		pageNo = document.getElementById('pageNopendingCancellationField').value;	
		actPage = document.getElementById('myPagePendingCancel').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNopendingCancellationField').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNopendingCancellationField').value = actPage;
			    document.getElementById('pageNopendingCancellationField').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNopendingCancellationField').value=actPage;
			    document.getElementById('pageNopendingCancellationField').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNopendingCancellationField').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNopendingCancellationField').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNopendingCancellationField').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNopendingCancellationField').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNopendingCancellationField').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNopendingCancellationField').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPagePendingCancel').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}


	function callPagePendingCancelText(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNopendingCancellationField').value;
		 	var actPage = document.getElementById('myPagePendingCancel').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNopendingCancellationField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNopendingCancellationField').focus();
		     document.getElementById('pageNopendingCancellationField').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNopendingCancellationField').focus();
		     document.getElementById('pageNopendingCancellationField').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNopendingCancellationField').focus();
		      return false;
	        }
	        
	         document.getElementById('myPagePendingCancel').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	// Function Pending Cancellation List Ends
	
	
	// Function for Approved List Begins
	function callPageApproved(id, pageImg, totalPageHid, action) {		
		try
		{
		pageNo = document.getElementById('pageNoApprovedField').value;	
		actPage = document.getElementById('myPageApproved').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoApprovedField').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoApprovedField').value = actPage;
			    document.getElementById('pageNoApprovedField').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoApprovedField').value=actPage;
			    document.getElementById('pageNoApprovedField').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoApprovedField').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoApprovedField').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoApprovedField').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoApprovedField').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoApprovedField').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoApprovedField').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageApproved').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}


	function callPageApprovedText(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoApprovedField').value;
		 	var actPage = document.getElementById('myPageApproved').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoApprovedField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoApprovedField').focus();
		     document.getElementById('pageNoApprovedField').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoApprovedField').focus();
		     document.getElementById('pageNoApprovedField').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoApprovedField').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageApproved').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	// Function Approved List Ends
	
		// Function for Rejected List Begins
	function callPageRejected(id, pageImg, totalPageHid, action) {		
		try
		{
		pageNo = document.getElementById('pageNoRejectedField').value;	
		actPage = document.getElementById('myPageRejected').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoRejectedField').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoRejectedField').value = actPage;
			    document.getElementById('pageNoRejectedField').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoRejectedField').value=actPage;
			    document.getElementById('pageNoRejectedField').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoRejectedField').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoRejectedField').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoRejectedField').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoRejectedField').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoRejectedField').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoRejectedField').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageRejected').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}


	function callPageRejectedText(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoRejectedField').value;
		 	var actPage = document.getElementById('myPageRejected').value;   
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoRejectedField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoRejectedField').focus();
		     document.getElementById('pageNoRejectedField').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoRejectedField').focus();
		     document.getElementById('pageNoRejectedField').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoRejectedField').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageRejected').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	// Function Rejected List Ends
</script>