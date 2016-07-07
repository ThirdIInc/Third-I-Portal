<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script language="JavaScript" type="text/javascript"
	src="include/javascript/sorttable.js"></script>
<s:form action="EmpGoalApproval" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="myPageApproved" id="myPageApproved" />
	<s:hidden name="myPage" id="myPage" />
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
					<td width="100%" class="txt"><strong class="text_head">Employee 	Goal Approval</strong></td>
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
		      		document.getElementById('paraFrm').action='EmpGoalApproval_getPendingList.action';
		      		document.getElementById('paraFrm').submit();		      
		     	}if(listType=="a"){		     
		      		document.getElementById('paraFrm').action='EmpGoalApproval_getApprovedList.action';
		      		document.getElementById('paraFrm').submit();		     
		     	}		     
		    }
		   </script>
					<td><a href="#" onclick="setAction('p')">Pending
					Application</a> | <a href="#" onclick="setAction('a')">Approved
					List</a></td>
				</tr>
			</table>
			</td>
		</tr>
		<!--------- PENDING APPLICATION SECTION BEGINS - displaying the pending leave applications ------->
		<s:if test="%{listType == 'pending'}">
			<tr>
				<td>
				<table width="100%" class="formbg" border="0">
					<tr>
						<td><b>Pending Applications</b></td>
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
									<td id="ctrlShow" width="100%" align="right"><b>Page:</b>
									<%
										totalPagePending = (Integer) request.getAttribute("totalPage");
										PageNoPending = (Integer) request.getAttribute("PageNo");
									%> <a href="#"
										onclick="callPage('1', 'F', '<%=totalPagePending%>', 'EmpGoalApproval_getPendingList.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPage('P', 'P', '<%=totalPagePending%>', 'EmpGoalApproval_getPendingList.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text" name="pageNoField"
										id="pageNoField" size="3" value="<%=PageNoPending%>"
										maxlength="10"
										onkeypress="callPageText(event, '<%=totalPagePending%>', 'EmpGoalApproval_getPendingList.action');return numbersOnly();" />
									of <%=totalPagePending%> <a href="#"
										onclick="callPage('N', 'N', '<%=totalPagePending%>', 'EmpGoalApproval_getPendingList.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPage('<%=totalPagePending%>', 'L', '<%=totalPagePending%>', 'EmpGoalApproval_getPendingList.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
								</tr>
							</s:if>
							<tr>
								<td>
								<table width="100%" class="sorttable" border="0">
									<tr>
										<td width="5%" valign="top" class="formth" nowrap="nowrap"><label
											name="pendingSrno" id="pendingSrno"
											ondblclick="callShowDiv(this);"><%=label.get("pendingSrno")%></label></td>
										<td width="15%" valign="top" class="formth"><label
											name="employee.id" id="employee.id"
											ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></td>
										<td width="40%" valign="top" class="formth"><label
											name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
										<td width="20%" valign="top" class="formth" nowrap="nowrap"><label
											name="goalPeriod" id="goalPeriod"
											ondblclick="callShowDiv(this);"><%=label.get("goalPeriod")%></label></td>
										<td width="20%" valign="top" class="formth" nowrap="nowrap"><label
											name="goalDetails" id="goalDetails"
											ondblclick="callShowDiv(this);"><%=label.get("goalDetails")%></label></td>
									</tr>
									<s:if test="pendingLength">
										<%
										int cn = PageNoPending * 20 - 20;
										%>
										<s:iterator value="pendingList">

											<tr class="sortableTD">
												<td width="5%" align="left" class="sortableTD"><%=++cn%></td>
												<td class="sortableTD" width="15%"><s:property
													value="empToken" /><s:hidden name="empGoalId" /><s:hidden
													name="reportingType" /><s:hidden name="level" /><s:hidden
													name="empCode" /><s:hidden name="status" /></td>
												<td class="sortableTD" width="30%"><s:property
													value="empName" /></td>
												<td class="sortableTD" width="30%" nowrap="nowrap"
													align="left"><s:property value="goalPeriod" /><s:hidden
													name="goalPeriod" /><s:hidden name="approverCodeList" /></td>
												<td class="sortableTD" width="20%" nowrap="nowrap"><input
													type="button" name="view_Details" class="token"
													value=" View / Approve "
													onclick="viewDetails('<s:property value="empGoalId"/>','<s:property value="status"/>','<s:property value="reportingType"/>',<s:property value="approverCodeList" />)" />&nbsp;</td>
											</tr>
										</s:iterator>
									</s:if>
									<s:if test="pendingLength"></s:if>
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
		<!--------- PENDING APPLICATION SECTION ENDS - displaying the pending applications ------->
		<!--------- APPROVED LEAVE APPLICATION SECTION STARTS  ------->
		<s:if test="%{listType == 'approved'}">
			<tr>
				<td>
				<table width="100%" class="formbg">
					<tr>
						<td><b>Approved Applications List</b></td>
					</tr>
					<%
						int totalPageApproved = 0;
						int PageNoApproved = 0;
					%>
					<s:if test="approvedLength">
						<tr>
							<td id="ctrlShow" width="100%" align="right"><b>Page:</b> <%
 							totalPageApproved = (Integer) request.getAttribute("totalPageApproved");
 							PageNoApproved = (Integer) request.getAttribute("PageNoApproved");
 							%> <a href="#"
								onclick="callPageApproved('1', 'F', '<%=totalPageApproved%>', 'EmpGoalApproval_getApprovedList.action');">
							<img title="First Page" src="../pages/common/img/first.gif"
								width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPageApproved('P', 'P', '<%=totalPageApproved%>', 'EmpGoalApproval_getApprovedList.action');">
							<img title="Previous Page" src="../pages/common/img/previous.gif"
								width="10" height="10" class="iconImage" /> </a> <input type="text"
								name="pageNoField2" id="pageNoField2" size="3"
								value="<%=PageNoApproved%>" maxlength="10"
								onkeypress="callPageTextApprove(event, '<%=totalPageApproved%>', 'EmpGoalApproval_getApprovedList.action');return numbersOnly();" />
							of <%=totalPageApproved%> <a href="#"
								onclick="callPageApproved('N', 'N', '<%=totalPageApproved%>', 'EmpGoalApproval_getApprovedList.action')">
							<img title="Next Page" src="../pages/common/img/next.gif"
								class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPageApproved('<%=totalPageApproved%>', 'L', '<%=totalPageApproved%>', 'EmpGoalApproval_getApprovedList.action');">
							<img title="Last Page" src="../pages/common/img/last.gif"
								width="10" height="10" class="iconImage" /> </a></td>
						</tr>
					</s:if>
					<tr>
						<td>
						<table width="100%" class="sorttable" border="0">
							<tr>
								<td width="5%" valign="top" class="formth" nowrap="nowrap"><label
									name="apprSrNo" id="apprSrNo" ondblclick="callShowDiv(this);"><%=label.get("apprSrNo")%></label></td>
								<td width="15%" valign="top" class="formth"><label
									name="employee.id" id="employee.id"
									ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></td>
								<td width="40%" valign="top" class="formth"><label
									name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
								<td width="20%" valign="top" class="formth" nowrap="nowrap"><label
									name="goalPeriod" id="goalPeriod"
									ondblclick="callShowDiv(this);"><%=label.get("goalPeriod")%></label></td>
								<td width="20%" valign="top" class="formth" nowrap="nowrap"><label
									name="goalDetails" id="goalDetails"
									ondblclick="callShowDiv(this);"><%=label.get("goalDetails")%></label></td>
							</tr>
							<%
							int appCount = 0;
							%>
							<s:if test="approvedLength">
								<%
								int cnApp = PageNoApproved * 20 - 20;
								%>
								<s:iterator value="approvedList">
									<tr class="sortableTD">
										<td width="5%" align="left" class="sortableTD"><%=++cnApp%>
										<%
										++appCount;
										%>
										</td>
										<td class="sortableTD" width="15%"><s:property
											value="empToken" /><s:hidden name="empGoalId" /><s:hidden
											name="reportingType" /><s:hidden name="level" /><s:hidden
											name="empCode" /><s:hidden name="status" /><input
											type="hidden" name="empGoalIdHiddenList"
											id="empGoalIdHiddenList<%=cnApp%>"
											value='<s:property value="empGoalId"/>' /></td>
										<td class="sortableTD" width="30%"><s:property
											value="empName" /></td>
										<td class="sortableTD" width="30%" nowrap="nowrap"
											align="left"><s:property value="goalPeriod" /><s:hidden
											name="goalPeriod" /><s:hidden name="approverCodeList" /></td>
										<td class="sortableTD" width="20%" nowrap="nowrap"
											align="center"><input type="button" name="view_Details"
											class="token" value=" View "
											onclick="viewDetails('<s:property value="empGoalId"/>','<s:property value="status"/>','<s:property value="reportingType"/>',<s:property value="approverCodeList" />)" />&nbsp;</td>
									</tr>
								</s:iterator>
							</s:if>

							<s:if test="approvedLength"></s:if>
							<s:else>
								<tr align="center">
									<td colspan="6" class="sortableTD" width="100%"><font
										color="red">No Data to display</font></td>
								</tr>
							</s:else>
							<input type="hidden" name="count" id="count"
								value='<%=appCount%>' />

						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
	</table>
	<!--------- APPROVED LEAVE APPLICATION SECTION ENDS  ------->
	<s:hidden name="eId" />
	<s:hidden name="leaveApplication.isApprovalClick" />
</s:form>
<script>    

function viewDetails(empGoalId,status,reportingType,approverCode){
		document.getElementById('paraFrm').action = 'EmpGoalSetting_retriveForApproval.action?empGoalId='+empGoalId+'&approvalStatus='+status+'&reportingType='+reportingType+'&approverCode='+approverCode;
		document.getElementById('paraFrm').submit();
}
	
// FOR APPROVED APPLICATIONS LIST
	
function callPageApproved(id, pageImg, totalPageHid, action) {				
	try{
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
	
	
function callPageTextApprove(id, totalPage, action){   
	try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) { 
			pageNo =document.getElementById('pageNoField2').value;
		 	var actPage = document.getElementById('myPageApproved').value;   		 	 
	        if(pageNo==""){
	         	alert("Please Enter Page Number.");
	         	document.getElementById('pageNoField2').focus();
			 	return false;
	        }
		    if(Number(pageNo)<=0){
		     	alert("Page number should be greater than zero."); 
		      	document.getElementById('pageNoField2').focus();
		     	document.getElementById('pageNoField2').value=actPage;  
			 	return false;
		    }
		    if(Number(totalPage)<Number(pageNo)){
		     	alert("Page number should not be greater than "+totalPage+".");
		     	document.getElementById('pageNoField2').focus();
		     	document.getElementById('pageNoField2').value=actPage;  
			 	return false;
		     }if(pageNo==actPage){  
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
</script>
