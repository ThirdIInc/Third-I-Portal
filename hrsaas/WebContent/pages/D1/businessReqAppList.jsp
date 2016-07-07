<!-- Nilesh Dhandare -->

<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript">
var records = new Array();
</script>
<s:form action="BusinessRequirementDocumentApproval" validate="true"
	id="paraFrm" validate="true" theme="simple" target="main">

	<!-- Operation Hidden Fields -->

	<s:hidden name="attachFile" />
	<s:hidden name="listType" />
	<!-- Hidden Fields Ends -->
	<!-- Not used hidden fields -->
	    <s:hidden name ="ongoingField" id="ongoingField"/>
		<s:hidden name ="sendbcakField" id="sendbcakField" />
	


	<!-- Paging purpose hideen fields -->

	<s:hidden name="myongoingProjectPage" id="myongoingProjectPage" />
	<s:hidden name="myClosedProjectPage" id="myClosedProjectPage" />
	<!-- Paging purpose hideen fields  Ends Here-->

	<table width="100%" border="0" align="right" class="formbg">
		<tr>
			<td colspan="4">
			<table width="100%" border="0" align="right" class="formbg">
				<tr>
					<td colspan="1" width="5%"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td colspan="1" width="80%" class="txt"><strong
						class="text_head">Business Requirement Document Approval </strong></td>
					<td colspan="2" width="15%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td width="30%" colspan="1"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
		<!-- new -->
		<tr>
			<td>
			<table class="" width="100%">
				<tr>
					<script>
		  
		   </script>
					<td><a href="#" onclick="setAction('f')">On Going Project</a>
					| <a href="#" onclick="setAction('c')">Closed Project</a></td>
				</tr>
			</table>
			</td>
		</tr>


		<!-- On Going Projects List Start -->

		<s:if test="%{listType == 'ongoing'}">
			<tr>
				<td>
				<table width="100%" class="formbg">

					<tr>
						<td width="100%">
						<table width="100%">
							<tr>
								<td><b>On Going Project</b></td>
								<td align="left" colspan="2">
								<%
									int totalPageOngoing = 0;
									int pageNoOngoing = 0;
								%> <s:if test="formyOngoingListLength">
									<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
									<%
											try {
											totalPageOngoing = (Integer) request
											.getAttribute("totalPageOngoing");
											pageNoOngoing = (Integer) request.getAttribute("pageNoOngoing");
										} catch (Exception e) {
											e.printStackTrace();
										}
									%> <a href="#"
										onclick="callPageOngoing('1', 'F', '<%=totalPageOngoing%>', 'BusinessRequirementDocumentApproval_callPage.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPageOngoing('P', 'P', '<%=totalPageOngoing%>', 'BusinessRequirementDocumentApproval_callPage.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text"
										name="pageNoFieldOngoing" id="pageNoFieldOngoing" size="3"
										value="<%=pageNoOngoing%>" maxlength="10"
										onkeypress="callPageTextOngoing(event, '<%=totalPageOngoing%>', 'BusinessRequirementDocumentApproval_callPage.action');return numbersOnly();" />
									of <%=totalPageOngoing%> <a href="#"
										onclick="callPageOngoing('N', 'N', '<%=totalPageOngoing%>', 'BusinessRequirementDocumentApproval_callPage.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPageOngoing('<%=totalPageOngoing%>', 'L', '<%=totalPageOngoing%>', 'BusinessRequirementDocumentApproval_callPage.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
								</s:if></td>
							</tr>
						</table>
						</td>
					</tr>


					<tr>
						<td>
						<table width="100%" class="sorttable">
							<tr>
								<td class="formth" width="5%"><b><label class="set"
									name="sr.no" id="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></b>
								</td>

								<td class="formth" width="15%" nowrap="nowrap"><b><label class="set"
									name="brd.ticket.no" id="brd.ticket.no"
									ondblclick="callShowDiv(this);"><%=label.get("brd.ticket.no")%></label></b>
								</td>

								<td class="formth" width="15%" nowrap="nowrap"><b><label class="set"
									name="proj.name" id="proj.name" ondblclick="callShowDiv(this);"><%=label.get("proj.name")%></label></b>
								</td>

								<td class="formth" width="15%" nowrap="nowrap"><b><label class="set"
									name="expected.date" id="expected.date"
									ondblclick="callShowDiv(this);"><%=label.get("expected.date")%></label></b>
								</td>


								<td class="formth" width="10%" nowrap="nowrap"><b><label class="set"
									name="current.state" id="current.state"
									ondblclick="callShowDiv(this);"><%=label.get("current.state")%></label></b>
								</td>

								<td class="formth" width="15%" nowrap="nowrap"><b><label class="set"
									name="pending.with.role" id="pending.with.role"
									ondblclick="callShowDiv(this);"><%=label.get("pending.with.role")%></label></b>
								</td>

								<td class="formth" width="20%" nowrap="nowrap"><b><label class="set"
									name="pending.name" id="pending.name"
									ondblclick="callShowDiv(this);"><%=label.get("pending.name")%></label></b>
								</td>

								<td class="formth" width="5%"><b>Details</b></td>

							</tr>
							<s:if test="formyOngoingListLength">

								<%
								int count1 = 0;
								%>
								<%!int d1 = 0;%>
								<%
								int j = pageNoOngoing * 20 - 20;
								%>
								<s:iterator value="myAppOngoingList">
									<tr>
										<td class="sortableTD" width="5%" align="center"><%=++j%></td>

										<td class="sortableTD" width="15%" nowrap="nowrap"><s:property
											value="brdNumber" /><s:hidden name="brdAppCodeItt"/></td>

										<td class="sortableTD" width="15%" nowrap="nowrap"><s:property
											value="projectName" /></td>

										<td class="sortableTD" width="15%">&nbsp;<s:property
											value="expectedEndDate" /></td>

										<td class="sortableTD" width="10%">&nbsp;<s:property
											value="currentStage" /></td>

										<td class="sortableTD" width="15%" nowrap="nowrap">&nbsp;<s:property
											value="selectRole" /></td>

										<td class="sortableTD" width="20%" nowrap="nowrap">&nbsp;<s:property
											value="forwardEmpName" /></td>

										<td class="sortableTD" width="5%" align="center"><input
											type="button" name="view_Details" class="token" id="ctrlShow"
											value="View Application"
											onclick="javascript:viewApplication('<s:property value="brdAppCodeItt"/>')" />
										</td>
									</tr>

								</s:iterator>
								<%
								d1 = j;
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
		</s:if>

		<!-- On Going Projects List Ends  -->

		<!-- Send Back Project List Start -->
		<s:if test="%{listType == 'close'}">
			<tr>
				<td>
				<table width="100%" class="formbg">

					<tr>
						<td width="100%">
						<table width="100%">
							<tr>
								<td><b>Close Project</b></td>
								<td align="left" colspan="2">
								<%
									int totalPageClosed = 0;
									int pageNoClosed = 0;
								%> <s:if test="myCloseedListLength">
									<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
									<%
											try {
											totalPageClosed = (Integer) request
											.getAttribute("totalPageClosed");
											pageNoClosed = (Integer) request
											.getAttribute("pageNoClosed");
										} catch (Exception e) {
											e.printStackTrace();
										}
									%> <a href="#"
										onclick="callPageClose('1', 'F', '<%=totalPageClosed%>', 'BusinessRequirementDocumentApproval_callPage.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPageClose('P', 'P', '<%=totalPageClosed%>', 'BusinessRequirementDocumentApproval_callPage.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text"
										name="pageNoFieldClosed" id="pageNoFieldClosed" size="3"
										value="<%=pageNoClosed%>" maxlength="10"
										onkeypress="callPageTextClose(event, '<%=totalPageClosed%>', 'BusinessRequirementDocumentApproval_callPage.action');return numbersOnly();" />
									of <%=totalPageClosed%> <a href="#"
										onclick="callPageClose('N', 'N', '<%=totalPageClosed%>', 'BusinessRequirementDocumentApproval_callPage.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPageClose('<%=totalPageClosed%>', 'L', '<%=totalPageClosed%>', 'BusinessRequirementDocumentApproval_callPage.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
								</s:if></td>
							</tr>
						</table>
						</td>
					</tr>


					<tr>
						<td>
						<table width="100%" class="sorttable">


							<tr>
								<td class="formth" width="10%"><b><label class="set"
									name="sr.no" id="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></b>
								</td>

								<td class="formth" width="30%"><b><label class="set"
									name="brd.ticket.no" id="brd.ticket.no"
									ondblclick="callShowDiv(this);"><%=label.get("brd.ticket.no")%></label></b>
								</td>

								<td class="formth" width="25%"><b><label class="set"
									name="proj.name" id="proj.name" ondblclick="callShowDiv(this);"><%=label.get("proj.name")%></label></b>
								</td>

								<td class="formth" width="20%" nowrap="nowrap"><b><label class="set"
									name="expected.date" id="expected.date"
									ondblclick="callShowDiv(this);"><%=label.get("expected.date")%></label></b>
								</td>

								<td class="formth" width="15%"><b>Details</b></td>

							</tr>
						
<s:if test="myCloseedListLength">
								<%
								int count2 = 0;
								%>
								<%!int d2 = 0;%>
								<%
								int k = pageNoClosed * 20 - 20;
								%>
								<s:iterator value="myAppClosedList">
									<tr>
										<td class="sortableTD" width="10%" align="center"><%=++k%></td>

										<td class="sortableTD" width="30%" nowrap="nowrap"><s:property
											value="brdNumber" /><s:hidden name="brdAppCodeItt"/></td>

										<td class="sortableTD" width="25%" nowrap="nowrap">&nbsp;<s:property
											value="projectName" /></td>

										<td class="sortableTD" width="20%" nowrap="nowrap">&nbsp;<s:property
											value="expectedEndDate" /></td>

										<td class="sortableTD" width="15%" align="center"><input
											type="button" name="view_Details" class="token" id="ctrlShow"
											value="View Application"
											onclick="javascript:viewClosedApplication('<s:property value="brdAppCodeItt"/>')" />
										</td>
									</tr>

								</s:iterator>
								<%
								d2 = k;
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
		</s:if>
		<!-- Send Back Project List Ends -->


	</table>
</s:form>

<script>
   function setAction(listType){
  /// alert("listType             "+ listType);
		   
		     if(listType=='f'){
		
		 var statusOngoing = "f";
	        	document.getElementById('ongoingField').value = listType; 
		      	document.getElementById('paraFrm').action='BusinessRequirementDocumentApproval_input.action';
		      	document.getElementById('paraFrm').submit();
		      
		     } if(listType=='c'){
	            document.getElementById('sendbcakField').value = listType; 
		        document.getElementById('paraFrm').action='BusinessRequirementDocumentApproval_input.action';
		        document.getElementById('paraFrm').submit();
		      
		     }
		     
		    }

 
 	function viewApplication(brdAppCode)
{

///alert(brdAppCode);
		
	  document.getElementById('paraFrm').target = "_self";
   	  document.getElementById('paraFrm').action="BusinessRequirementDocumentApproval_viewApplication.action?brdAppCode="+brdAppCode;
	  document.getElementById('paraFrm').submit();
}
 
 function viewClosedApplication(brdAppId)
 
 {

///alert(brdAppId);
     try{
   	  document.getElementById('paraFrm').action='BusinessRequirementDocumentApproval_viewClosedApplication.action?brdAppId='+brdAppId;
   	  document.getElementById('paraFrm').submit();
 }
 catch(e)
 {
 alert(e);
 }
 }
 
 ///For Ongoing Project 
	
	function callPageOngoing(id, pageImg, totalPageHid, action) {		
		
		try
		{
		pageNo = document.getElementById('pageNoFieldOngoing').value;	
		actPage = document.getElementById('forMyActionPage').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoFieldOngoing').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoFieldOngoing').value = actPage;
			    document.getElementById('pageNoFieldOngoing').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoFieldOngoing').value=actPage;
			    document.getElementById('pageNoFieldOngoing').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoFieldOngoing').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoFieldOngoing').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoFieldOngoing').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoFieldOngoing').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoFieldOngoing').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoFieldOngoing').value;
			id = eval(p) + 1;
		}
		document.getElementById('myongoingProjectPage').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}
	
	function callPageTextOngoing(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoFieldOngoing').value;
		 	var actPage = document.getElementById('myongoingProjectPage').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoFieldOngoing').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoFieldOngoing').focus();
		     document.getElementById('pageNoFieldOngoing').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoFieldOngoing').focus();
		     document.getElementById('pageNoFieldOngoing').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoFieldOngoing').focus();
		      return false;
	        }
	        
	         document.getElementById('myongoingProjectPage').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}		
 ///for send back list
 
 
 ///For Ongoing Project 
	
	function callPageClose(id, pageImg, totalPageHid, action) {		
		
		try
		{
		pageNo = document.getElementById('pageNoFieldClosed').value;	
		actPage = document.getElementById('myClosedProjectPage').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoFieldClosed').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoFieldClosed').value = actPage;
			    document.getElementById('pageNoFieldClosed').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoFieldClosed').value=actPage;
			    document.getElementById('pageNoFieldClosed').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoFieldClosed').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoFieldClosed').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoFieldClosed').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoFieldClosed').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoFieldClosed').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoFieldClosed').value;
			id = eval(p) + 1;
		}
		document.getElementById('myClosedProjectPage').value = id;
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
			pageNo =document.getElementById('pageNoFieldClosed').value;
		 	var actPage = document.getElementById('myClosedProjectPage').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoFieldClosed').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoFieldClosed').focus();
		     document.getElementById('pageNoFieldClosed').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoFieldClosed').focus();
		     document.getElementById('pageNoFieldClosed').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoFieldClosed').focus();
		      return false;
	        }
	        
	         document.getElementById('myClosedProjectPage').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}		
 
 
 
</script>