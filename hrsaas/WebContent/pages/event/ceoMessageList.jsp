<%@ taglib prefix="s" uri="/struts-tags"%>

<%@include file="/pages/common/labelManagement.jsp"%>
<script language="JavaScript" type="text/javascript"
	src="include/javascript/sorttable.js"></script>



<s:hidden name="myPageInProcess" id="myPageInProcess" />
<s:hidden name="totalPendingRecords"/>
<s:hidden name="totalApprovedRecords"/>
<s:hidden name="totalRejectedRecords"/>
<s:hidden name="approvedLength"/>
<s:hidden name="rejectedLength"/>
<s:hidden name="pendingLength"/>
<s:form action="CeoMessageList" validate="true" id="paraFrm"
	 theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">CEO
					Message List </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
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
		     
		      	document.getElementById('paraFrm').action='CeoMessageList_callstatus.action';
		      	document.getElementById('paraFrm').submit();
		      
		     }if(listType=="a"){
		     
		      	document.getElementById('paraFrm').action='CeoMessageList_getApprovedList.action';
		      	document.getElementById('paraFrm').submit();
		      
		     }
		   if(listType=="r"){
		     
		      	document.getElementById('paraFrm').action='CeoMessageList_getRejectedList.action';
		      	document.getElementById('paraFrm').submit();
		      
		     }
		     
		    }
		   </script>
					<td><a href="#" onclick="setAction('p')">Pending Message</a> |
					<a href="#" onclick="setAction('a')">Accepted Message</a> | <a
						href="#" onclick="setAction('r')">Rejected Message</a></td>
				</tr>
			</table>
			</td>
		</tr>

		<!--------- PENDING Message SECTION BEGINS - displaying the pending Message  ------->

		<s:if test="%{listType == 'pending'}">

			<tr>
				<td>
				<table width="100%"  border="0">
					<tr>
						<td><b>Pending CEO Message</b></td>
					</tr>

					<tr>
						<td>
						<table width="100%">
							<%
								int totalPagePending = 0;
								int PageNoPending = 0;
							%>
							<s:if test="pendingLength">
								<tr>
								<s:hidden name="myPage" id="myPage" />
									<td id="ctrlShow" width="100%" align="right"><b>Page:</b>
									<%
										totalPagePending = (Integer)request.getAttribute("totalPage");
										PageNoPending = (Integer)request.getAttribute("pageNo");
									%> <a href="#"
										onclick="callPage('1', 'F', '<%=totalPagePending%>', 'CeoMessageList_callstatus.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPage('P', 'P', '<%=totalPagePending%>', 'CeoMessageList_callstatus.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text" name="pageNoField"
										id="pageNoField" size="3" value="<%=PageNoPending%>"
										maxlength="10"
										onkeypress="callPageText(event, '<%=totalPagePending%>', 'CeoMessageList_callstatus.action');return numbersOnly();" />
									of <%=totalPagePending%> <a href="#"
										onclick="callPage('N', 'N', '<%=totalPagePending%>', 'CeoMessageList_callstatus.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPage('<%=totalPagePending%>', 'L', '<%=totalPagePending%>', 'CeoMessageList_callstatus.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
								</tr>
							</s:if>
							<tr>
								<td>
								<table width="100%" class="formbg" border="0">
									<tr>
										<td width="5%" valign="top" class="formth" nowrap="nowrap"><label
											name="srno" id="srno" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></td>
										<td width="15%" valign="top" class="formth"><label
											name="employee.id" id="employee.id"
											ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></td>
										<td width="40%" valign="top" class="formth"><label
											name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
										<td width="20%" valign="top" class="formth" nowrap="nowrap"><label
											name="appdate" id="appdate" ondblclick="callShowDiv(this);"><%=label.get("appdate")%></label></td>
										<td width="20%" valign="top" class="formth" nowrap="nowrap"><label
											name="details" id="details" ondblclick="callShowDiv(this);"><%=label.get("details")%></label></td>

									</tr>
									<s:if test="pendingLength">
										<%
										int cn = PageNoPending * 20 - 20;
										%>

										<s:iterator value="pendingList">

											<tr class="sortableTD">
												<td width="5%" align="left" class="sortableTD">&nbsp;<%=++cn%></td>
												<td class="sortableTD" width="15%"><s:property
													value="tokenNo" /><s:hidden name="messageNo" /><s:hidden
													name="level" /><s:hidden name="empCode" /><s:hidden
													name="pendingStatus" /></td>
												<td class="sortableTD" width="40%">&nbsp;<s:property
													value="empName" /></td>
												<td class="sortableTD" width="20%" nowrap="nowrap"
													align="center">&nbsp;<s:property value="date" /><s:hidden
													name="date" /></td>
												<td class="sortableTD" width="20%" nowrap="nowrap"
													align="center"><input type="button"
													name="view_Details" class="token" value=" Accept / Reject "
													onclick="viewDetails('<s:property value="messageNo"/>','<s:property value="pendingStatus"/>','<s:property value="empCode"/>')" />

												</td>


											</tr>

										</s:iterator>
									</s:if>

									<s:if test="pendingLength">
										<tr>
											<td align="right" colspan="5"><b>Total No. Of
											Records:</b>&nbsp;<s:property value="totalPendingRecords" /></td>
										</tr>
									</s:if>
									<s:else>
										<tr align="center">
											<td colspan="6" class="sortableTD" width="100%"><font
												color="red">No Data to display</font></td>
										</tr>
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

		<!--------- PENDING Message SECTION ENDS - displaying the pending Message  ------->

		<!--------- APPROVED MEssage SECTION STARTS  ------->

		<s:if test="%{listType == 'approved'}">

			<tr>
				<td>
				<table width="100%" class="formbg">
					<tr>
						<td><b>Accepted CEO Message</b></td>
					</tr>
					<%
								int totalPageApproved = 0;
								int PageNoApproved = 0;
							%>
					<s:if test="approvedLength">
						<tr>
						<s:hidden name="myPageApproved" id="myPageApproved" />
							<td id="ctrlShow" width="100%" align="right"><b>Page:</b> <%
								totalPageApproved = (Integer) request
 			.getAttribute("totalPageApproved");
								PageNoApproved = (Integer) request.getAttribute("PageNoApproved");
 %> <a href="#"
								onclick="callPageApproved('1', 'F', '<%=totalPageApproved%>', 'CeoMessageList_getApprovedList.action');">
							<img title="First Page" src="../pages/common/img/first.gif"
								width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPageApproved('P', 'P', '<%=totalPageApproved%>', 'CeoMessageList_getApprovedList.action');">
							<img title="Previous Page" src="../pages/common/img/previous.gif"
								width="10" height="10" class="iconImage" /> </a> <input type="text"
								name="pageNoField2" id="pageNoField2" size="3"
								value="<%=PageNoApproved%>" maxlength="10"
								onkeypress="callPageTextApprove(event, '<%=totalPageApproved%>', 'CeoMessageList_getApprovedList.action');return numbersOnly();" />
							of <%=totalPageApproved%> <a href="#"
								onclick="callPageApproved('N', 'N', '<%=totalPageApproved%>', 'CeoMessageList_getApprovedList.action')">
							<img title="Next Page" src="../pages/common/img/next.gif"
								class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPageApproved('<%=totalPageApproved%>', 'L', '<%=totalPageApproved%>', 'CeoMessageList_getApprovedList.action');">
							<img title="Last Page" src="../pages/common/img/last.gif"
								width="10" height="10" class="iconImage" /> </a></td>
						</tr>
					</s:if>
					<tr>
						<td>
						<table width="100%">

							<tr>
								<td>
								<table width="100%" class="sorttable">
									<tr>
										<td class="formth" width="5%"><b><label class="set"
											name="srno" id="srno4" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></b>
										</td>
										<td class="formth" width="15%"><b><label class="set"
											name="employee.id" id="employee.id4"
											ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></b>
										</td>
										<td class="formth" width="40%"><b><label class="set"
											name="employee" id="employee4"
											ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></b>
										</td>
										<td class="formth" width="20%"><b><label class="set"
											name="appdate" id="appdate4" ondblclick="callShowDiv(this);"><%=label.get("appdate")%></label></b>
										</td>
										<td class="formth" width="20%"><b>View Details</b></td>

									</tr>
									<s:if test="approvedLength">
										<%
									 
										int cn2 = PageNoApproved * 20 - 20;
									%>

										<s:iterator value="appList">
											<tr>
												<td class="sortableTD" width="5%" align="center"><%=++cn2%></td>
												<td class="sortableTD" width="15%"><s:hidden
													name="appEmpId" /><s:hidden name="appMessageNo" /> <s:hidden
													name="appStatus" /> <s:property value="appEmpToken" /></td>
												<td class="sortableTD" width="40%"><s:property
													value="appEmpName" /></td>
												<td class="sortableTD" width="20%" align="center"><s:property
													value="appAppDate" /></td>
												<td class="sortableTD" width="20%" nowrap="nowrap"
													align="center"><input type="button"
													name="view_Details" class="token" value=" Details "
													onclick="viewDetails('<s:property value="appMessageNo"/>','<s:property value="appStatus"/>','<s:property value="appEmpId"/>')" />



												</td>
											</tr>

										</s:iterator>
									</s:if>
									
									<s:if test="approvedLength">
										<tr>
											<td align="right" colspan="5"><b>Total No. Of
											Records:</b>&nbsp;<s:property value="totalApprovedRecords" /></td>
										</tr>
									</s:if>
									<s:else>
										<tr align="center">
											<td colspan="6" class="sortableTD" width="100%"><font
												color="red">No Data to display</font></td>
										</tr>
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

		<!--------- APPROVED MEssage SECTION ENDS  ------->


		<!--------- REJECTED  SECTION STARTS  ------->

		<s:if test="%{listType == 'rejected'}">

			<tr>
				<td>
				<table width="100%" class="formbg">
					<tr>
						<td><b>Rejected CEO Message </b></td>
					</tr>
					<tr>
						<td>
						<table width="100%">
							<% int totalPageRejected = 0; int PageNoRejected = 0; %>
							<s:if test="rejectedLength">
								<tr>
									<td id="ctrlShow" width="100%" align="right"><b>Page:</b>
									
									<%	 totalPageRejected = (Integer) request.getAttribute("totalPageRejected");
							PageNoRejected = (Integer) request.getAttribute("PageNoRejected");
							
						%>
						<a href="#"
										onclick="callPageRejected('1', 'F', '<%=totalPageRejected%>', 'CeoMessageList_getRejectedList.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPageRejected('P', 'P', '<%=totalPageRejected%>', 'CeoMessageList_getRejectedList.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text" name="pageNoField3"
										id="pageNoField3" size="3" value="<%=PageNoRejected%>"
										maxlength="10"
										onkeypress="callPageTextReject(event, '<%=totalPageRejected%>', 'CeoMessageList_getRejectedList.action');return numbersOnly();" />
									of <%=totalPageRejected%> <a href="#"
										onclick="callPageRejected('N', 'N', '<%=totalPageRejected%>', 'CeoMessageList_getRejectedList.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPageRejected('<%=totalPageRejected%>', 'L', '<%=totalPageRejected%>', 'CeoMessageList_getRejectedList.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
								</tr>
							</s:if>

							<tr>
								<td>
								<table width="100%" class="sorttable">
									<tr>
										<td class="formth" width="5%"><b><label class="set"
											name="srno" id="srno6" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></b>
										</td>
										<td class="formth" width="15%"><b><label class="set"
											name="employee.id" id="employee.id6"
											ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></b>
										</td>
										<td class="formth" width="40%"><b><label class="set"
											name="employee" id="employee.name"
											ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></b>
										</td>
										<td class="formth" width="20%"><b><label class="set"
											name="appdate" id="appdate6" ondblclick="callShowDiv(this);"><%=label.get("appdate")%></label></b>
										</td>
										<td class="formth" width="20%"><b>View Details</b></td>

									</tr>
									<s:if test="rejectedLength">
										<%
										 
										int cn3 = PageNoRejected * 20 - 20;
									%>
																<s:hidden name="myPageRejected" id="myPageRejected" /> 
										<s:iterator value="rejList">
											<tr>
												<td class="sortableTD" width="5%" align="center"><%=++cn3%></td>
												<td class="sortableTD" width="15%">&nbsp;<s:hidden
													name="rejEmpId" /><s:hidden name="rejMessageNo" /> <s:hidden
													name="rejStatus" /><s:property value="rejEmpToken" /></td>
												<td class="sortableTD" width="40%">&nbsp;<s:property
													value="rejEmpName" /></td>
												<td class="sortableTD" width="20%" align="center">&nbsp;<s:property
													value="rejAppDate" /></td>
												<td class="sortableTD" width="20%" nowrap="nowrap"
													align="center"><input type="button"
													name="view_Details" class="token" value=" Details "
													onclick="viewDetails('<s:property value="rejMessageNo"/>','<s:property value="rejStatus"/>','<s:property value="rejEmpId"/>')" />


												</td>
											</tr>

										</s:iterator>

									</s:if>

									<s:if test="rejectedLength">
										<tr>
											<td align="right" colspan="5"><b>Total No. Of
											Records:</b>&nbsp;<s:property value="totalRejectedRecords" /></td>
										</tr>
									</s:if>
									<s:else>
										<tr align="center">
											<td colspan="6" class="sortableTD" width="100%"><font
												color="red">No Data to display</font></td>
										</tr>
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


		<!--------- REJECTED  SECTION Ends  ------->


	</table>

</s:form>


<script>




     function viewDetails(messageNo,status,empCode)
		 {
	 
		document.getElementById('paraFrm').action = 'CeoMessageList_retriveForApproval.action?messageNo='+messageNo+'&messageStatus='+status+'&empCode='+empCode;
		document.getElementById('paraFrm').submit();
		 document.getElementById('paraFrm').target = "_self";
		}
  



function callPageApproved(id, pageImg, totalPageHid, action) {		
		
		try
		{
		pageNo = document.getElementById('pageNoField2').value;	
		actPage = document.getElementById('myPageApproved').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoField2').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoField2').value = actPage;
			    document.getElementById('pageNoField2').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoField2').value=actPage;
			    document.getElementById('pageNoField2').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoField2').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoField2').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoField2').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoField2').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoField2').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoField2').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageApproved').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}
	
	function callPage(id, pageImg, totalPageHid, action) {		
		
		try
		{
		pageNo = document.getElementById('pageNoField').value;	
		actPage = document.getElementById('myPage').value; 
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoField').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoField').value = actPage;
			    document.getElementById('pageNoField').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoField').value=actPage;
			    document.getElementById('pageNoField').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoField').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoField').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoField').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoField').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoField').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPage').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}

	
	
	function callPageTextApprove(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoField2').value;
		 	var actPage = document.getElementById('myPageApproved').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoField2').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoField2').focus();
		     document.getElementById('pageNoField2').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField2').focus();
		     document.getElementById('pageNoField2').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoField2').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageApproved').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	
	
		function callPageText(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoField').value;
		 	var actPage = document.getElementById('myPage').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoField').focus();
		     document.getElementById('pageNoField').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField').focus();
		     document.getElementById('pageNoField').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoField').focus();
		      return false;
	        }
	        
	         document.getElementById('myPage').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	
	
	
	
		function callPageRejected(id, pageImg, totalPageHid, action) {		
		
		try
		{
		pageNo = document.getElementById('pageNoField3').value;	
		actPage = document.getElementById('myPageRejected').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoField3').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoField3').value = actPage;
			    document.getElementById('pageNoField3').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoField3').value=actPage;
			    document.getElementById('pageNoField3').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoField3').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoField3').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoField3').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoField3').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoField3').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoField3').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageRejected').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}
	
	
	
	
	function callPageTextReject(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoField3').value;
		 	var actPage = document.getElementById('myPageRejected').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoField3').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoField3').focus();
		     document.getElementById('pageNoField3').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField3').focus();
		     document.getElementById('pageNoField3').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoField3').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageRejected').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	
	
	
	

</script>