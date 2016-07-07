<!-- Nilesh Dhandare -->
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="/pages/common/labelManagement.jsp"%>
 
<s:form action="BusinessRequirementDocument" validate="true" id="paraFrm" theme="simple">
	<s:hidden name="brdNumber" />
	<s:hidden name="brdCode" />
	<s:hidden name="attachFile" />
	<s:hidden name="completedBy" />
	<s:hidden name="completedByID" />
	<s:hidden name="forMyActionPage" id="forMyActionPage" />
	<s:hidden name="myongoingProjectPage" id="myongoingProjectPage" />
	<s:hidden name="myCloseProjectPage" id="myCloseProjectPage" />
	<s:hidden name="myCancelProjectPage" id="myCancelProjectPage" />
	<s:hidden name="listType" />
	<!--<s:hidden name="mySendBackProjectPage" id="mySendBackProjectPage" />-->

	<table width="100%" border="0" align="right" class="formbg">
		<tr>
			<td colspan="4">
			<table width="100%" border="0" align="right" class="formbg">
				<tr>
					<td colspan="1" width="5%"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td colspan="1" width="80%" class="txt"><strong
						class="text_head">Business Requirement Document </strong></td>
					<td colspan="2" width="15%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td width="30%" colspan="4"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>

		<tr>
			<td colspan="4">
			<table class="" width="100%">
				<tr>
					<!-- 
					<td><a href="#" onclick="callMyAction();">For My Action </a> |
						<a href="#" onclick="callOngoing();">On Going Project</a> | 
						<a href="#" onclick="callClose();"> Close Project</a>
					</td> -->
					<!-- 
					<td><STRONG>My Applications</STRONG></td>
				 	-->	
					
					<td>
						<a href="#" onclick="callBRDInProcess();" title="BRD in process">BRD in process </a> | 
						<a href="#" onclick="callClosedBRD();" title="Closed BRD"> Closed BRD</a> | 
						<a href="#" onclick="callCancelBRDList();" title="Cancel BRD"> Cancel BRD</a> 
					</td>
				</tr>
			</table>
			</td>
		</tr>


<!-- For My Action List Start -->
<s:if test="%{listType == 'inProcess'}">
		<tr>
			<td colspan="4">
			<table width="100%" class="formbg">
				<tr>
					<td width="100%">
					<table width="100%">
						<tr>
							<td width="20%"><b>For My Action</b></td>
							<td width="50%" align="right">
								<input type="button" value="Search" onclick="javascript:callsF9(500,325,'BusinessRequirementDocument_f9SearchRecord.action');">
							</td>
							<td width="30%">
							<%
								int totalPageMyAction = 0;
								int pageNoMyAction = 0;
							%> 
							</td>
						</tr>
					</table>
					</td>
				</tr>


				<tr>
					<td>
					<table width="100%" class="formbg" class="sortable">
						<tr>
							<td class="formth" width="10%" align="center"><b><label
								class="set" name="sr.no" id="sr.no"
								ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></b></td>

							<td class="formth" width="20%" align="center"><b><label
								class="set" name="brd.ticket.no" id="brd.ticket.no"
								ondblclick="callShowDiv(this);"><%=label.get("brd.ticket.no")%></label></b></td>

							<td class="formth" width="25%" align="center"><b><label class="set"
								name="proj.name" id="proj.name" ondblclick="callShowDiv(this);"><%=label.get("proj.name")%></label></b>	</td>

							<td class="formth" width="20%" align="center"><b><label
								class="set" name="expected.date" id="expected.date"
								ondblclick="callShowDiv(this);"><%=label.get("expected.date")%></label></b></td>
							
							<td class="formth" width="15%" align="center" nowrap="nowrap"><b><label
								class="set" name="current.stage" id="current.stage"
								ondblclick="callShowDiv(this);"><%=label.get("current.stage")%></label></b>
							</td>	
								
							<td class="formth" width="10%" align="center" colspan="3"><b>Details</b></td>

						</tr>
						<s:if test="formyActionListLength">

							<%
							int count = 0;
							%>
							<%!int d = 0;%>
							<%
							//int i = pageNoMyAction * 20 - 20;
							int i = 0;
							%>
							<s:iterator value="forMyActionList">
								<tr>
									<td class="sortabletd" width="10%" align="center"><%=++i%></td>

									<td class="sortabletd" width="20%" align="left">
										<s:hidden name="brdCode" />
										<s:hidden name="status" />
										<s:property value="brdNumber" />
									</td>

									<td class="sortabletd" width="25%">&nbsp;<s:property
										value="projectName" /></td>
									<td class="sortabletd" width="20%" align="center" align="center">&nbsp;<s:property
										value="expectedEndDate" /></td>
										
									<td class="sortabletd" width="15%">&nbsp;<s:property
										value="currentStage" /></td>	

									<td class="sortabletd" width="10%" align="center" colspan="3"><input
											type="button" name="view_Details" class="token" id="ctrlShow" 
											value=" View / Edit"
											onclick="javascript:editApplication('<s:property value="brdCode"/>','<s:property value="status"/>')" />
										</td>
								</tr>

							</s:iterator>
							<%
							d = i;
							%>
						</s:if>
						<s:else>
							<td align="center" colspan="8" nowrap="nowrap"><font
								color="red">There is no data to display</font></td>
						</s:else>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<!-- For My Action List End -->

		<!-- On Going Projects List Start -->
		<tr>
			<td colspan="4">
			<table width="100%" class="formbg">

				<tr>
					<td width="100%">
					<table width="100%">
						<tr>
							<td><b>On Going Project</b></td>
							<td colspan="2">
							<%
								int totalPageOngoing = 0;
								int pageNoOngoing = 0;
							%> <s:if test="formyOngoingListLength">
								<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
								<%
										try {
											totalPageOngoing = (Integer) request.getAttribute("totalPageOngoing");
											pageNoOngoing = (Integer) request.getAttribute("pageNoOngoing");
									} catch (Exception e) {
										e.printStackTrace();
									}
								%> <a href="#"
									onclick="callPageOngoing('1', 'F', '<%=totalPageOngoing%>', 'BusinessRequirementDocument_input.action');">
								<img title="First Page" src="../pages/common/img/first.gif"
									width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPageOngoing('P', 'P', '<%=totalPageOngoing%>', 'BusinessRequirementDocument_input.action');">
								<img title="Previous Page"
									src="../pages/common/img/previous.gif" width="10" height="10"
									class="iconImage" /> </a> <input type="text"
									name="pageNoFieldOngoing" id="pageNoFieldOngoing" size="3"
									value="<%=pageNoOngoing%>" maxlength="10"
									onkeypress="callPageTextOngoing(event, '<%=totalPageOngoing%>', 'BusinessRequirementDocument_input.action');return numbersOnly();" />
								of <%=totalPageOngoing%> <a href="#"
									onclick="callPageOngoing('N', 'N', '<%=totalPageOngoing%>', 'BusinessRequirementDocument_input.action')">
								<img title="Next Page" src="../pages/common/img/next.gif"
									class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPageOngoing('<%=totalPageOngoing%>', 'L', '<%=totalPageOngoing%>', 'BusinessRequirementDocument_input.action');">
								<img title="Last Page" src="../pages/common/img/last.gif"
									width="10" height="10" class="iconImage" /> </a></td>
							</s:if></td>
						</tr>
					</table>
					</td>
				</tr>


				<tr>
					<td>
					<table width="100%" class="formbg" class="sortable">
						<tr>
							<td class="formth" width="5%" align="center"><b><label
								class="set" name="sr.no" id="sr.no"
								ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></b>
							</td>

							<td class="formth" width="15%" align="center"><b><label
								class="set" name="brd.ticket.no" id="brd.ticket.no"
								ondblclick="callShowDiv(this);"><%=label.get("brd.ticket.no")%></label></b>
							</td>

							<td class="formth" width="20%" align="center"><b><label class="set"
								name="proj.name" id="proj.name" ondblclick="callShowDiv(this);"><%=label.get("proj.name")%></label></b>
							</td>

							<td class="formth" width="10%" align="center"><b><label
								class="set" name="expected.date" id="expected.date"
								ondblclick="callShowDiv(this);"><%=label.get("expected.date")%></label></b>
							</td>


							<td class="formth" width="10%" align="center"><b><label
								class="set" name="current.stage" id="current.stage"
								ondblclick="callShowDiv(this);"><%=label.get("current.stage")%></label></b>
							</td>

							<td class="formth" width="10%" align="center"><b><label class="set"
								name="pending.with.role" id="pending.with.role"
								ondblclick="callShowDiv(this);"><%=label.get("pending.with.role")%></label></b>
							</td>

							<td class="formth" width="15%" align="center"><b><label class="set"
								name="pending.name" id="pending.name"
								ondblclick="callShowDiv(this);"><%=label.get("pending.name")%></label></b>
							</td>

							<td class="formth" width="5%" align="center"><b>Details</b></td>

						</tr>
						<s:if test="formyOngoingListLength">

							<%
							int count1 = 0;
							%>
							<%!int d1 = 0;%>
							<%
							int j = pageNoOngoing * 15 - 15;
							%>
							<s:iterator value="forMyOngoingList">
								<tr>
									<td class="sortabletd" align="center"><%=++j%></td>

									<td class="sortabletd">
										<s:hidden name="status" />
										<s:hidden name="brdCode" />
										<s:property value="brdNumber" />
									</td>

									<td class="sortabletd">&nbsp;<s:property
										value="projectName" /></td>

									<td class="sortabletd" align="center">&nbsp;<s:property value="expectedEndDate" /></td>

									<td class="sortabletd">&nbsp;<s:property
										value="currentStage" /></td>

									<td class="sortabletd">&nbsp;<s:property
										value="selectRole" /></td>

									<td class="sortabletd">&nbsp;<s:property
										value="forwardEmpName" /></td>

									<td class="sortabletd" align="center"><input
										type="button" name="view_Details" class="token" id="ctrlShow"
										value=" View "
										onclick="javascript:viewDetails('<s:property value="brdCode"/>')" />
									</td>
								</tr>

							</s:iterator>
							<%
							d1 = j;
							%>
						</s:if>

						<s:else>
							<td align="center" colspan="8" nowrap="nowrap"><font
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

<!-- Close Project List Start -->
<s:if test="%{listType == 'closed'}">	
		<tr>
			<td colspan="4">
			<table width="100%" class="formbg">

				<tr>
					<td width="100%">
					<table width="100%">
						<tr>
							<td width="20%"><b>My Closed Project</b></td>
							<td width="50%" align="right">
								<input type="button" value="Search" onclick="javascript:callsF9(500,325,'BusinessRequirementDocument_f9SearchRecord.action');">
							</td>
							<td>
							<%
								int totalPageClosed = 0;
								int pageNoClosed = 0;
							%> 
							<s:if test="formyClosedListLength">
								<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
								<%
										try {
											totalPageClosed = (Integer) request.getAttribute("totalPageClosed");
											pageNoClosed = (Integer) request.getAttribute("pageNoClosed");
									} catch (Exception e) {
										e.printStackTrace();
									}
								%> <a href="#"
									onclick="callPageClosed('1', 'F', '<%=totalPageClosed%>', 'BusinessRequirementDocument_callClosedList.action');">
								<img title="First Page" src="../pages/common/img/first.gif"
									width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPageClosed('P', 'P', '<%=totalPageClosed%>', 'BusinessRequirementDocument_callClosedList.action');">
								<img title="Previous Page"
									src="../pages/common/img/previous.gif" width="10" height="10"
									class="iconImage" /> </a> <input type="text"
									name="pageNoFieldClosed" id="pageNoFieldClosed" size="3"
									value="<%=pageNoClosed%>" maxlength="10"
									onkeypress="callPageTextClosed(event, '<%=totalPageClosed%>', 'BusinessRequirementDocument_callClosedList.action');return numbersOnly();" />
								of <%=totalPageClosed%> <a href="#"
									onclick="callPageClosed('N', 'N', '<%=totalPageClosed%>', 'BusinessRequirementDocument_callClosedList.action')">
								<img title="Next Page" src="../pages/common/img/next.gif"
									class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPageClosed('<%=totalPageClosed%>', 'L', '<%=totalPageClosed%>', 'BusinessRequirementDocument_callClosedList.action');">
								<img title="Last Page" src="../pages/common/img/last.gif"
									width="10" height="10" class="iconImage" /> </a>
								</td>
							</s:if>
							</td>
						</tr>
					</table>
					</td>
				</tr>


				<tr>
					<td>
					<table width="100%" class="formbg" class="sortable">
						<tr>
							<td class="formth" width="5%" align="center"><b><label class="set"
								name="sr.no" id="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></b>
							</td>

							<td class="formth" width="30%" align="center"><b><label
								class="set" name="brd.ticket.no" id="brd.ticket.no"
								ondblclick="callShowDiv(this);"><%=label.get("brd.ticket.no")%></label></b>
							</td>

							<td class="formth" width="30%" align="center"><b><label class="set"
								name="proj.name" id="proj.name" ondblclick="callShowDiv(this);"><%=label.get("proj.name")%></label></b>
							</td>

							<td class="formth" width="20%" align="center"><b><label
								class="set" name="expected.date" id="expected.date"
								ondblclick="callShowDiv(this);"><%=label.get("expected.date")%></label></b>
							</td>

							<td class="formth" width="15%" align="center" colspan="4"><b>Details</b></td>

						</tr>
						<s:if test="formyClosedListLength">

							<%
							int count2 = 0;
							%>
							<%!int d2 = 0;%>
							<%
							int k = pageNoClosed * 20 - 20;
							%>
							<s:iterator value="forMyClosedList">
								<tr>
									<td class="sortabletd" align="center"><%=++k%></td>

									<td class="sortabletd" nowrap="nowrap">
										<s:hidden name="status" />
										<s:hidden name="brdCode" />
										<s:property value="brdNumber" />
									</td>

									<td class="sortabletd">&nbsp;<s:property
										value="projectName" /></td>

									<td class="sortabletd" align="center">&nbsp;<s:property
										value="expectedEndDate" /></td>

									<td class="sortabletd" align="center" colspan="4"><input
										type="button" name="view_Details" class="token" id="ctrlShow"
										value=" View "
										onclick="javascript:viewDetails('<s:property value="brdCode"/>')" />
									</td>
								</tr>

							</s:iterator>
							<%
							d2 = k;
							%>
						</s:if>
						<s:else>
							<td align="center" colspan="9" nowrap="nowrap"><font
								color="red">There is no data to display</font></td>
						</s:else>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
</s:if>		
<!-- Close Project List Ends -->

<!-- Cancel Project List Start -->
<s:if test="%{listType == 'cancelled'}">	
		<tr>
			<td colspan="4">
			<table width="100%" class="formbg">

				<tr>
					<td width="100%">
					<table width="100%">
						<tr>
							<td width="20%"><b>My Cancel Project</b></td>
							<td width="50%" align="right">
								<input type="button" value="Search" onclick="javascript:callsF9(500,325,'BusinessRequirementDocument_f9SearchRecord.action');">
							</td>
							<td>
							<%
								int totalPageCancelled = 0;
								int pageNoCancelled = 0;
							%> 
							<s:if test="myCancelListLength">
								<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
								<%
									try {
										totalPageCancelled = (Integer) request.getAttribute("totalPageCancelled");
										pageNoCancelled = (Integer) request.getAttribute("pageNoCancelled");
									} catch (Exception e) {
										e.printStackTrace();
									}
								%> <a href="#"
									onclick="callPageCancelled('1', 'F', '<%=totalPageCancelled%>', 'BusinessRequirementDocument_callCancelBRDList.action');">
								<img title="First Page" src="../pages/common/img/first.gif"
									width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPageCancelled('P', 'P', '<%=totalPageCancelled%>', 'BusinessRequirementDocument_callCancelBRDList.action');">
								<img title="Previous Page"
									src="../pages/common/img/previous.gif" width="10" height="10"
									class="iconImage" /> </a> <input type="text"
									name="pageNoFieldCancelled" id="pageNoFieldCancelled" size="3"
									value="<%=pageNoCancelled%>" maxlength="10"
									onkeypress="callPageTextCancelled(event, '<%=totalPageCancelled%>', 'BusinessRequirementDocument_callCancelBRDList.action');return numbersOnly();" />
								of <%=totalPageCancelled%> <a href="#"
									onclick="callPageCancelled('N', 'N', '<%=totalPageCancelled%>', 'BusinessRequirementDocument_callCancelBRDList.action')">
								<img title="Next Page" src="../pages/common/img/next.gif"
									class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPageCancelled('<%=totalPageCancelled%>', 'L', '<%=totalPageCancelled%>', 'BusinessRequirementDocument_callCancelBRDList.action');">
								<img title="Last Page" src="../pages/common/img/last.gif"
									width="10" height="10" class="iconImage" /> </a>
								</td>
							</s:if>
							</td>
						</tr>
					</table>
					</td>
				</tr>


				<tr>
					<td>
					<table width="100%" class="formbg" class="sortable">
						<tr>
							<td class="formth" width="5%" align="center"><b><label class="set"
								name="sr.no" id="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></b>
							</td>

							<td class="formth" width="30%" align="center"><b><label
								class="set" name="brd.ticket.no" id="brd.ticket.no"
								ondblclick="callShowDiv(this);"><%=label.get("brd.ticket.no")%></label></b>
							</td>

							<td class="formth" width="30%" align="center"><b><label class="set"
								name="proj.name" id="proj.name" ondblclick="callShowDiv(this);"><%=label.get("proj.name")%></label></b>
							</td>

							<td class="formth" width="20%" align="center"><b><label
								class="set" name="expected.date" id="expected.date"
								ondblclick="callShowDiv(this);"><%=label.get("expected.date")%></label></b>
							</td>

							<td class="formth" width="15%" align="center" colspan="4"><b>Details</b></td>

						</tr>
						<s:if test="myCancelListLength">
							<%!int canceld2 = 0;%>
							<%
							int cancelCount = pageNoCancelled * 20 - 20;
							%>
							<s:iterator value="myCancelList">
								<tr>
									<td class="sortabletd" align="center"><%=++cancelCount%></td>

									<td class="sortabletd">
										<s:hidden name="status" />
										<s:hidden name="brdCode" />
										<s:property value="brdNumber" />
									</td>

									<td class="sortabletd" >&nbsp;<s:property
										value="projectName" /></td>

									<td class="sortabletd" align="center">&nbsp;<s:property
										value="expectedEndDate" /></td>

									<td class="sortabletd" align="center" colspan="4"><input
										type="button" name="view_Details" class="token" id="ctrlShow"
										value=" View "
										onclick="javascript:viewDetails('<s:property value="brdCode"/>')" />
									</td>
								</tr>

							</s:iterator>
							<%
							canceld2 = cancelCount;
							%>
						</s:if>
						<s:else>
							<td align="center" colspan="9" nowrap="nowrap"><font
								color="red">There is no data to display</font></td>
						</s:else>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
</s:if>		
<!-- Cancel Project List Ends -->
	</table>
</s:form>
<script>
function addprojectFun() {
	    document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action =  'BusinessRequirementDocument_addNew.action';
		document.getElementById('paraFrm').submit();
}
 
function editBackApplication(code) {
  document.getElementById('paraFrm_brdCode').value =code;
  document.getElementById('paraFrm').action ='BusinessRequirementDocument_editSendBackApplication.action';	
  document.getElementById('paraFrm').submit();
}

function editApplication(id, status) {
	if(status=="F") {
   	  document.getElementById('paraFrm').action='BusinessRequirementDocument_viewApplication.action?brdCode='+id;
	  document.getElementById('paraFrm').submit();
	} else {
 	 document.getElementById('paraFrm').action ='BusinessRequirementDocument_editApplication.action?brdCode='+id;		
  	 document.getElementById('paraFrm').submit();
  }
}


function viewDetails(appno){
	  document.getElementById('paraFrm_brdCode').value =appno; 
	  document.getElementById('paraFrm').action='BusinessRequirementDocument_retriveDetails.action?applCode='+appno;
	  document.getElementById('paraFrm').submit();
}

function callPage(id, pageImg, totalPageHid, action) {		
		try {
		pageNo = document.getElementById('pageNoField').value;	
		actPage = document.getElementById('forMyActionPage').value; 
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
		document.getElementById('forMyActionPage').value = id;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action = action;
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
		 	var actPage = document.getElementById('forMyActionPage').value;   
	     
		 	 
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
	        
	         document.getElementById('forMyActionPage').value=pageNo;
		   document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
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
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action = action;
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
		   document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
}		

///For Closed Project

function callPageClosed(id, pageImg, totalPageHid, action) {		
		try
		{
		pageNo = document.getElementById('pageNoFieldClosed').value;	
		actPage = document.getElementById('myCloseProjectPage').value; 
		
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
		document.getElementById('myCloseProjectPage').value = id;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
}
	
function callPageTextClosed(id, totalPage, action){   
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
		 	var actPage = document.getElementById('myCloseProjectPage').value;   
	     
		 	 
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
	        
	        document.getElementById('myCloseProjectPage').value=pageNo;
		    document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
}	


///For Cancel Project
function callPageCancelled(id, pageImg, totalPageHid, action) {		
		try {
		pageNo = document.getElementById('pageNoFieldCancelled').value;	
		actPage = document.getElementById('myCancelProjectPage').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoFieldCancelled').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoFieldCancelled').value = actPage;
			    document.getElementById('pageNoFieldCancelled').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoFieldCancelled').value=actPage;
			    document.getElementById('pageNoFieldCancelled').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoFieldCancelled').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoFieldCancelled').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoFieldCancelled').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoFieldCancelled').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoFieldCancelled').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoFieldCancelled').value;
			id = eval(p) + 1;
		}
		document.getElementById('myCancelProjectPage').value = id;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').submit();
		 } catch(e){alert(e);}
}
	
function callPageTextCancelled(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoFieldCancelled').value;
		 	var actPage = document.getElementById('myCancelProjectPage').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoFieldCancelled').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoFieldCancelled').focus();
		     document.getElementById('pageNoFieldCancelled').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoFieldCancelled').focus();
		     document.getElementById('pageNoFieldCancelled').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoFieldCancelled').focus();
		      return false;
	        }
	        
	        document.getElementById('myCancelProjectPage').value=pageNo;
		    document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
}	

function callBRDInProcess() {
	document.getElementById('forMyActionPage').value = "1";
	document.getElementById('myongoingProjectPage').value = "1";
	document.getElementById('paraFrm').target = '_self';
	document.getElementById('paraFrm').action = 'BusinessRequirementDocument_input.action';
	document.getElementById('paraFrm').submit();
}
		
function callClosedBRD() {
	document.getElementById('myCloseProjectPage').value = "1";
	document.getElementById('paraFrm').target = '_self';
	document.getElementById('paraFrm').action = 'BusinessRequirementDocument_callClosedList.action';
	document.getElementById('paraFrm').submit();
}

function callCancelBRDList() {
	document.getElementById('myCancelProjectPage').value = "1";
	document.getElementById('paraFrm').target = '_self';
	document.getElementById('paraFrm').action = 'BusinessRequirementDocument_callCancelBRDList.action';
	document.getElementById('paraFrm').submit();
}
</script>
