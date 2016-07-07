<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="CBTApproval" validate="true" id="paraFrm" validate="true" theme="simple">	
	<s:hidden name="cbtID" />
	<s:hidden name="listType" />
	
	<s:hidden name="myPage" id="myPage" />
	<s:hidden name="myPageApproved" id="myPageApproved" />
	<s:hidden name="myPageRejected" id="myPageRejected" />
	
	<table width="100%" border="0" align="right" cellpadding="2" cellspacing="1" class="formbg">
		<tr>			
			<td valign="bottom" class="txt">						
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td>
						<strong class="text_head"> 
							<img src="../pages/images/recruitment/review_shared.gif" width="25"	height="25" /> 
						</strong>
					</td>
					<td width="93%" class="txt">
						<strong class="text_head">CBT/Self Study Enrollment Approval Form</strong>
					</td>
					<td width="3%" valign="top" class="txt">
						<div align="right">
							<img src="../pages/images/recruitment/help.gif" width="16" height="16" />
						</div>
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
		     
		      	document.getElementById('paraFrm').action='CBTApproval_input.action';
		      	document.getElementById('paraFrm').submit();
		      
		     }if(listType=="a"){
		     
		      	document.getElementById('paraFrm').action='CBTApproval_getApprovedList.action';
		      	document.getElementById('paraFrm').submit();
		      
		     }
		    
		     if(listType=="r"){
		        
		      	document.getElementById('paraFrm').action='CBTApproval_getRejectedList.action';
		      	document.getElementById('paraFrm').submit();
		        
		     } 
		     
		    }
		   </script>
					<td><a href="#" onclick="setAction('p')">Pending
					Application</a> | <a href="#" onclick="setAction('a')">Approved
					Application</a> | <a href="#" onclick="setAction('r')">Rejected
					Application</a></td>
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
										onclick="callPage('1', 'F', '<%=totalPendingPage%>', 'CBTApproval_input.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPage('P', 'P', '<%=totalPendingPage%>', 'CBTApproval_input.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text" name="pageNoField"
										id="pageNoField" size="3" value="<%=pendingPageNo%>" maxlength="10"
										onkeypress="callPageText(event, '<%=totalPendingPage%>', 'CBTApproval_input.action');return numbersOnly();" />
									of <%=totalPendingPage%> <a href="#"
										onclick="callPage('N', 'N', '<%=totalPendingPage%>', 'CBTApproval_input.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPage('<%=totalPendingPage%>', 'L', '<%=totalPendingPage%>', 'CBTApproval_input.action');">
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
										<td width="5%" class="formth" align="center">
											<label class="set" name="SerialNo" id="SerialNo" ondblclick="callShowDiv(this);"> <%=label.get("SerialNo")%></label>
										</td>

										<td class="formth" align="center" width="15%">
											<label class="set" name="trackingNumber" id="trackingNumber" ondblclick="callShowDiv(this);"> <%=label.get("trackingNumber")%></label>
										</td>

										<td class="formth" align="center" width="25%">
											<label	class="set" name="emp.name" id="emp.name"	ondblclick="callShowDiv(this);"> <%=label.get("emp.name")%></label>
										</td>						

										<td class="formth" align="center" width="20%">
											<label class="set" name="application.Date" id="application.Date" ondblclick="callShowDiv(this);"> <%=label.get("application.Date")%></label>
										</td>	
									
										<td class="formth" align="center" width="15%">
											<label class="set" name="details" id="details" ondblclick="callShowDiv(this);"> <%=label.get("details")%></label>
										</td>
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
											<s:hidden name="cbtHiddenID" />
											<s:hidden name="hiddenStatus" />		
											<td align="center" class="sortableTD"><%=++i1%></td>
										
											<td class="sortableTD">&nbsp;			
												<s:property	value="trackingNumberIterator" />																					
											</td>

											<td class="sortableTD" align="left">&nbsp;
												<s:property	value="empNameIterator" />
											</td>
										
											<td class="sortableTD" align="center">&nbsp;
												<s:property	value="applicationDateIterator" />
											</td>
										
											<td class="sortableTD" align="center">&nbsp;
												<input type="button" name="edit" value="View application" class="token" onclick="viewApplicationFunction('<s:property value="cbtHiddenID"/>','<s:property value="hiddenStatus"/>');" />
											</td>										
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
										onclick="callPageApproved('1', 'F', '<%=totalApprovedPage%>', 'ReqToBackOutVoucher_getApprovedList.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPageApproved('P', 'P', '<%=totalApprovedPage%>', 'ReqToBackOutVoucher_getApprovedList.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text" name="pageNoApprovedField"
										id="pageNoApprovedField" size="3" value="<%=approvedPageNo%>" maxlength="10"
										onkeypress="callPageApprovedText(event, '<%=totalApprovedPage%>', 'ReqToBackOutVoucher_getApprovedList.action');return numbersOnly();" />
									of <%=totalApprovedPage%> <a href="#"
										onclick="callPageApproved('N', 'N', '<%=totalApprovedPage%>', 'ReqToBackOutVoucher_getApprovedList.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPageApproved('<%=totalApprovedPage%>', 'L', '<%=totalApprovedPage%>', 'ReqToBackOutVoucher_getApprovedList.action');">
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
										<td width="5%" class="formth" align="center">
											<label class="set" name="SerialNo" id="SerialNo" ondblclick="callShowDiv(this);"> <%=label.get("SerialNo")%></label>
										</td>

										<td class="formth" align="center" width="15%">
											<label class="set" name="trackingNumber" id="trackingNumber" ondblclick="callShowDiv(this);"> <%=label.get("trackingNumber")%></label>
										</td>

										<td class="formth" align="center" width="25%">
											<label	class="set" name="emp.name" id="emp.name"	ondblclick="callShowDiv(this);"> <%=label.get("emp.name")%></label>
										</td>						

										<td class="formth" align="center" width="20%">
											<label class="set" name="application.Date" id="application.Date" ondblclick="callShowDiv(this);"> <%=label.get("application.Date")%></label>
										</td>	
									
										<td class="formth" align="center" width="15%">
											<label class="set" name="details" id="details" ondblclick="callShowDiv(this);"> <%=label.get("details")%></label>
										</td>
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
											<s:hidden name="cbtHiddenID" />
											<s:hidden name="hiddenStatus" />	
											<td align="center" class="sortableTD"><%=++i2%></td>
										
											<td class="sortableTD">&nbsp;			
												<s:property	value="trackingNumberIterator" />																					
											</td>

											<td class="sortableTD" align="left">&nbsp;
												<s:property	value="empNameIterator" />
											</td>
										
											<td class="sortableTD" align="center">&nbsp;
												<s:property	value="applicationDateIterator" />
											</td>
										
											<td class="sortableTD" align="center">&nbsp;
												<input type="button" name="edit" value="View Application" class="token" onclick="viewApplicationFunction('<s:property value="cbtHiddenID"/>','<s:property value="hiddenStatus"/>');" />
											</td>										
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
										onclick="callPageRejected('1', 'F', '<%=totalRejectedPage%>', 'ReqToBackOutVoucher_getRejectedList.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPageRejected('P', 'P', '<%=totalRejectedPage%>', 'ReqToBackOutVoucher_getRejectedList.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text" name="pageNoRejectedField"
										id="pageNoRejectedField" size="3" value="<%=rejectedPageNo%>" maxlength="10"
										onkeypress="callPageRejectedText(event, '<%=totalRejectedPage%>', 'ReqToBackOutVoucher_getRejectedList.action');return numbersOnly();" />
									of <%=totalRejectedPage%> <a href="#"
										onclick="callPageRejected('N', 'N', '<%=totalRejectedPage%>', 'ReqToBackOutVoucher_getRejectedList.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPageRejected('<%=totalRejectedPage%>', 'L', '<%=totalRejectedPage%>', 'ReqToBackOutVoucher_getRejectedList.action');">
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
										<td width="5%" class="formth" align="center">
											<label class="set" name="SerialNo" id="SerialNo" ondblclick="callShowDiv(this);"> <%=label.get("SerialNo")%></label>
										</td>

										<td class="formth" align="center" width="15%">
											<label class="set" name="trackingNumber" id="trackingNumber" ondblclick="callShowDiv(this);"> <%=label.get("trackingNumber")%></label>
										</td>

										<td class="formth" align="center" width="25%">
											<label	class="set" name="emp.name" id="emp.name"	ondblclick="callShowDiv(this);"> <%=label.get("emp.name")%></label>
										</td>						

										<td class="formth" align="center" width="20%">
											<label class="set" name="application.Date" id="application.Date" ondblclick="callShowDiv(this);"> <%=label.get("application.Date")%></label>
										</td>	
									
										<td class="formth" align="center" width="15%">
											<label class="set" name="details" id="details" ondblclick="callShowDiv(this);"> <%=label.get("details")%></label>
										</td>
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
											<s:hidden name="cbtHiddenID" />	
											<s:hidden name="hiddenStatus" />
											<td align="center" class="sortableTD"><%=++i5%></td>
										
											<td class="sortableTD">&nbsp;			
												<s:property	value="trackingNumberIterator" />																					
											</td>

											<td class="sortableTD" align="left">&nbsp;
												<s:property	value="empNameIterator" />
											</td>
										
											<td class="sortableTD" align="center">&nbsp;
												<s:property	value="applicationDateIterator" />
											</td>
										
											<td class="sortableTD" align="center">&nbsp;
												<input type="button" name="edit" value="View Application" class="token" onclick="viewApplicationFunction('<s:property value="cbtHiddenID"/>','<s:property value="hiddenStatus"/>');" />
											</td>										
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

function viewApplicationFunction(cbtHiddenID, hiddenStatus)
{
	  document.getElementById('paraFrm').target = "_self";
   	  document.getElementById('paraFrm').action="CBTApproval_viewApplicationFunction.action?cbtHiddenID="+cbtHiddenID+"&hiddenStatus="+hiddenStatus;
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
