<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="HelpDeskApproval" validate="true" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Helpdesk Request Approval</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:hidden name="hAppFor" />
		<s:hidden name="myPageClosed" id="myPageClosed" />
		<s:hidden name="myPage" id="myPage" />
		<s:hidden name="listType" />
		<s:hidden name="source" id="source"/>
		<tr>
			<td>
			<table class="" width="100%">
				<tr>
					<script>
		    function setAction(listType){
			    if(listType=="p"){
			      	document.getElementById('paraFrm').action='HelpDeskApproval_input.action?listType='+"pending";
			      	document.getElementById('paraFrm').submit();
			    }
			     if(listType=="c"){
			      	document.getElementById('paraFrm').action='HelpDeskApproval_getClosedList.action?listType='+"closed";
			      	document.getElementById('paraFrm').submit();
			     }  
		    }
		   </script>
					<td><a href="#" onclick="setAction('p')"> Requests Under Process 
					</a> |&nbsp;<a href="#" onclick="setAction('c')">Requests Processed</a></td>
				</tr>
			</table>
			</td>
		</tr>
		<!-------------------------------------- PENDING LIST OF APPLICATIONS [BEGINS]----------------------------->
		<s:if test="%{listType == 'pending'}">
			<tr>
				<td colspan="2">
				<table width="100%" border="0" class="formbg">
					<tr>
						<td width="40%"><b>Requests Under Process</b></td>
						<%
								int totalPage = 0;
								int pageNo = 0;
							%>
							<s:if test="pendingLength">
									<td id="ctrlShow" width="100%" align="right"><b>Page:</b>
									<%
										totalPage = (Integer) request.getAttribute("totalPage");
										pageNo = (Integer) request.getAttribute("pageNo");
									%> <a href="#"
										onclick="callPage('1', 'F', '<%=totalPage%>', 'HelpDeskApproval_input.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPage('P', 'P', '<%=totalPage%>', 'HelpDeskApproval_input.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text" name="pageNoField"
										id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10"
										onkeypress="callPageText(event, '<%=totalPage%>', 'HelpDeskApproval_input.action');return numbersOnly();" />
									of <%=totalPage%> <a href="#"
										onclick="callPage('N', 'N', '<%=totalPage%>', 'HelpDeskApproval_input.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'HelpDeskApproval_input.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
							</s:if>
					</tr>
					<tr>
						<td colspan="2">
						<table width="100%">
							<tr>
								<td>
								<table width="100%" class="sorttable">
									<tr>
										<td class="formth" width="5%"><b><label class="set"
											name="sno" id="sno4" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
										</td>
											<td class="formth" width="10%"><b><label class="set"
											name="reqId" id="reqId2" ondblclick="callShowDiv(this);"><%=label.get("reqId")%></label></b>
										</td>
										<td class="formth" width="20%"><b><label class="set"
											name="requestby" id="requestby1"
											ondblclick="callShowDiv(this);"><%=label.get("requestby")%></label></b>
										</td>
										<td class="formth" width="15%"><b><label class="set"
											name="req.dept" id="req.dept4"
											ondblclick="callShowDiv(this);"><%=label.get("req.dept")%></label></b>
										</td>
										<td class="formth" width="30%"><b><label class="set"
											name="subject" id="subject1"
											ondblclick="callShowDiv(this);"><%=label.get("subject")%></label></b>
										</td>
										<td class="formth" width="10%"><b><label class="set"
											name="status" id="status4"
											ondblclick="callShowDiv(this);"><%=label.get("status")%></label></b>
										</td>
										<td class="formth" width="10%"><b><label class="set"
											name="req.date" id="req.date4" ondblclick="callShowDiv(this);"><%=label.get("req.date")%></label></b>
										</td>
										<td class="formth" width="10%">&nbsp;</td>

									</tr>
									<s:if test="pendingLength">
									<%	int count = 0;	%>
										<%
										int cn = pageNo * 20 - 20;
										%>
										<s:iterator value="pendingList">
											<tr id="tr1" <%if(count%2==0){
									%>
																class="tableCell1" <%}else{%> class="tableCell2"
																<%	}count++; %>
																onmouseover="javascript:newRowColor(this);"
																onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
																ondblclick="javascript:viewDetails('<s:property value="pendingReqCode"/>','<s:property value="reqMgrStatus"/>');">
												<td class="sortableTD" width="5%" align="center"><%=++cn %></td>
												<td class="sortableTD" width="10%" align="center"><s:property
													value="pendingReqNo" /></td>
												<td class="sortableTD" width="20%"><s:hidden
													name="pendingEmpId" /><s:hidden name="pendingReqNo" />
												<s:hidden name="reqStatus" /> <s:hidden
													value="reqEmpToken" /><s:hidden name="reqMgrStatus" />
													<s:property	value="reqEmpName" /></td>
												<td class="sortableTD" width="15%"><s:property
													value="pendingReqDept" /></td>
												<td class="sortableTD" width="30%"><s:property
													value="reqSubject" /></td>
												<td class="sortableTD" width="10%"><s:hidden name="reqStatusIsLast" />
												<s:hidden name="reqStatusOrder" /><s:property value="reqStatusName" /></td>
												<td class="sortableTD" width="15%" align="center" nowrap="nowrap"><s:property
													value="reqDate" /></td>
												<td class="sortableTD" width="10%" align="center">
												<input type="button" name="view_Details" class="token"
													value=" View "
													onclick="viewDetails('<s:property value="pendingReqCode"/>','<s:property value="reqMgrStatus"/>')" />&nbsp;</td>
											</tr>

										</s:iterator>

									</s:if>
									<s:if test="pendingLength"></s:if>
									<s:else>
										<tr align="center">
											<td colspan="7" class="sortableTD" width="100%"><font
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
		<!-------------------------------------- PENDING LIST OF APPLICATIONS [ENDS] ------------------------------->

		<!-------------------------------------- CLOSED APPLICATIONS LIST [BEGINS]----------------------------->

		<s:if test="%{listType == 'closed'}">
			<tr>
				<td colspan="2">
				<table width="100%" border="0" class="formbg">
					<tr>
						<td width="40%"><b>Requests Processed</b></td>
						<%
						int totalPageClosed = 0;
						int PageNoClosed = 0;
					%>
					<s:if test="closedLength">
							<td id="ctrlShow" width="100%" align="right"><b>Page:</b> <%
 			totalPageClosed = (Integer) request
 			.getAttribute("totalPageClosed");
 	PageNoClosed = (Integer) request.getAttribute("PageNoClosed");
 %> <a href="#"
								onclick="callPageClosed('1', 'F', '<%=totalPageClosed%>', 'HelpDeskApproval_getClosedList.action');">
							<img title="First Page" src="../pages/common/img/first.gif"
								width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPageClosed('P', 'P', '<%=totalPageClosed%>', 'HelpDeskApproval_getClosedList.action');">
							<img title="Previous Page" src="../pages/common/img/previous.gif"
								width="10" height="10" class="iconImage" /> </a> <input type="text"
								name="pageNoField3" id="pageNoField3" size="3"
								value="<%=PageNoClosed%>" maxlength="10"
								onkeypress="callPageTextClose(event, '<%=totalPageClosed%>', 'HelpDeskApproval_getClosedList.action');return numbersOnly();" />
							of <%=totalPageClosed%> <a href="#"
								onclick="callPageClosed('N', 'N', '<%=totalPageClosed%>', 'HelpDeskApproval_getClosedList.action')">
							<img title="Next Page" src="../pages/common/img/next.gif"
								class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPageClosed('<%=totalPageClosed%>', 'L', '<%=totalPageClosed%>', 'HelpDeskApproval_getClosedList.action');">
							<img title="Last Page" src="../pages/common/img/last.gif"
								width="10" height="10" class="iconImage" /> </a></td>
					</s:if>
					</tr>
					<tr>
						<td colspan="2">
						<table width="100%">
							<tr>
								<td>
								<table width="100%" class="sorttable">
									<tr>
										<td class="formth" width="5%"><b><label class="set"
											name="sno" id="sno6" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
										</td>
											<td class="formth" width="10%"><b><label class="set"
											name="reqId" id="reqId2" ondblclick="callShowDiv(this);"><%=label.get("reqId")%></label></b>
										</td>
										<td class="formth" width="20%"><b><label class="set"
											name="requestby" id="requestby2"
											ondblclick="callShowDiv(this);"><%=label.get("requestby")%></label></b>
										</td>
										<td class="formth" width="15%"><b><label class="set"
											name="req.dept" id="req.dept6"
											ondblclick="callShowDiv(this);"><%=label.get("req.dept")%></label></b>
										</td>
										<td class="formth" width="30%"><b><label class="set"
											name="subject" id="subject1"
											ondblclick="callShowDiv(this);"><%=label.get("subject")%></label></b>
										</td>
										<td class="formth" width="15%"><b><label class="set"
											name="status" id="status6"
											ondblclick="callShowDiv(this);"><%=label.get("status")%></label></b>
										</td>
										<td class="formth" width="10%"><b><label class="set"
											name="req.date" id="req.date6" ondblclick="callShowDiv(this);"><%=label.get("req.date")%></label></b>
										</td>
										<td class="formth" width="10%">&nbsp;</td>

									</tr>
									<s:if test="closedLength">
									<%	int count1 = 0;	%>
										<%
										int cn3 = PageNoClosed * 20 - 20;
										%>

										<s:iterator value="closedList">
											<tr id="tr2" <%if(count1%2==0){	%>
																class="tableCell1" <%}else{%> class="tableCell2"
																<%	}count1++; %>
																onmouseover="javascript:newRowColor(this);"
																onmouseout="javascript:oldRowColor(this,<%=count1%2 %>);"
																ondblclick="javascript:viewDetails('<s:property value="closedReqCode"/>','<s:property value="reqMgrStatus"/>')">
												<td class="sortableTD" width="5%" align="center"><%=++cn3 %></td>
												<td class="sortableTD" width="10%" align="center"><s:property value="closedReqNo" /></td>
												<td class="sortableTD" width="20%"><s:hidden
													name="closedEmpId" /><s:hidden name="closedReqNo" />
												<s:hidden name="reqStatus" /> <s:hidden
													value="reqEmpToken" /><s:hidden name="reqMgrStatus" />
													<s:property	value="reqEmpName" /></td>
												<td class="sortableTD" width="15%"><s:property
													value="closedReqDept" /></td>
												<td class="sortableTD" width="30%"><s:property
													value="reqSubject" /></td>
												<td class="sortableTD" width="10%"><s:hidden name="reqStatusIsLast" />
												<s:hidden name="reqStatusOrder" /><s:hidden name="reqStatusName" /><s:property value="reqStatusName" /></td>
												<td class="sortableTD" width="20%" align="center" nowrap="nowrap"><s:property
													value="reqDate" /></td>
												<td class="sortableTD" width="10%" align="center"><input
													type="button" name="view_Details" class="token"
													value=" View "
													onclick="viewDetails('<s:property value="closedReqCode"/>','<s:property value="reqMgrStatus"/>')" /></td>
											</tr>

										</s:iterator>
									</s:if>

									<s:if test="closedLength"></s:if>
									<s:else>
										<tr align="center">
											<td colspan="7" class="sortableTD" width="100%"><font
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
		<!-------------------------------------- CLOSED APPLICATIONS LIST [ENDS]----------------------------->

		<!--<tr>
			<td width="100%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>

	--></table>
	 
</s:form>

<script>


function viewDetails(reqNo, mgrStatus){
     
   	document.getElementById('paraFrm').action='HelpDeskApproval_retrieveDetails.action?reqAppCode='+reqNo+
   		'&mgrStatus='+mgrStatus;
   	document.getElementById('paraFrm').submit();
}


		// PENDING APPLICATIONS LIST
	
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





// CLOSED APPLICATIONS LIST
	
	function callPageClosed(id, pageImg, totalPageHid, action) {		
		
		try
		{
		pageNo = document.getElementById('pageNoField3').value;	
		actPage = document.getElementById('myPageClosed').value; 
		
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
		document.getElementById('myPageClosed').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}
	
	
	
	function callPageTextClose(id, totalPage, action){   
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
		 	var actPage = document.getElementById('myPageClosed').value;   
	     
		 	 
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
	        
	         document.getElementById('myPageClosed').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
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
	

</script>