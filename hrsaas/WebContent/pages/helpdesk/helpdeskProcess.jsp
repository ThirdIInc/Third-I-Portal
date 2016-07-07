<!-- @author: Mangesh Jadhav 19 Jan 2011  -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<style>
#refresh input {
  cursor:pointer;
  display:inline-block;
  background-position: 0 0;
  text-decoration:none;
  background-repeat:no-repeat;
  color:#444;
  border:0px solid #AAA;
  height:20px;
  font-size:12px;
  text-decoration: bold;
  background-color:#F3F6EF;
  padding:0px 0px 8px 8px; 
  background-image: url(<%=request.getContextPath()%>/pages/common/css/default/images/update1.gif);
  
 
}
</style>
<s:form action="HelpDeskProcess" validate="true" id="paraFrm"  theme="simple">

	<s:hidden name="pendingLength" />
	<s:hidden name="escalatedFlag" />
	<s:hidden name="escalatedFromName" />
	<s:hidden name="escalatedTime" />
	<s:hidden name="myPageClosed" id="myPageClosed" />
	<s:hidden name="myPage" id="myPage" />
	<s:hidden name="listType" />
	<s:hidden name="src" id="src"/>
	<s:hidden name="checkSearch" value="%{checkSearch}" id="checkSearch" />
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
					<td width="93%" class="txt"><strong class="text_head">Helpdesk Request Processing
					</strong></td>
					<!--
					<td width="93%" class="txt" id="refresh">
					<s:submit name="refresh" value="  Refresh" action="HelpDeskProcess_input" ></s:submit>				<div align="right"><img
					</td>
					  -->
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
				 
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="9">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td colspan="4" class="text_head"><strong
						class="forminnerhead">Select Filters </strong>
						</td>
						<td colspan="5" align="right" nowrap="nowrap"><a href="#"
						onclick="searchRecord();">Search</a> | <a href="#" id="showLink"
						style="display: inline;"
						onclick="checkIsExpandSearch('1');showtr();">Expand Search</a>  <a
						href="#" id="hideLink" style="display: none;" onclick="showDiv();">Hide Search</a>
						| <a href="#" onclick="clearFilter();">Clear</a> 
						</td>
				</tr>
				<tr>
					<td width="10%" nowrap="nowrap"><label
						name="requestId" id="requestId1"
						ondblclick="callShowDiv(this);"><%=label.get("requestId")%></label>
					:</td>
					<td width="10%"><s:hidden name="filterById" />
					<s:textfield name="filterByReqToken" size="15"/></td>
					<td width="5%"><img id="ctrlHide"
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'HelpDeskProcess_f9requestId.action');">
					</td>
					<td width="10%" nowrap="nowrap"><label
						name="employee" id="employee1"
						ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
					:</td>
					<td width="10%"><s:hidden name="filterByEmpId"
						value="%{filterByEmpId}" theme="simple" /> 
						<s:hidden name="filterByEmpToken"/><s:textfield
						name="filterByEmp" theme="simple" size="15" readonly="true" />
					</td>
					<td width="5%"><img id="ctrlHide"
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'HelpDeskProcess_f9employee.action');">
					</td>
					<td width="10%" nowrap="nowrap">Status <font color="red">*</font> :</td>
					<td width="10%"><s:select  
						name="filterByStatus" cssStyle="width:100"   
						list="statusMap" />
					</td>
					<td width="10%">&nbsp;</td>
				</tr>
				<tr id="view" style="display: none;">
					<td width="10%" nowrap="nowrap"><label
						name="category" id="category1"
						ondblclick="callShowDiv(this);"><%=label.get("category")%></label>
					:</td>
					<td width="10%"><s:hidden name="filterByCatId"
						value="%{filterByCatId}" theme="simple" /> <s:textfield
						name="filterByCat" theme="simple" size="15" readonly="true" />
					</td>
					<td width="5%"><img id="ctrlHide"
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'HelpDeskProcess_f9category.action');">
					</td>
					<td width="10%" nowrap="nowrap"><label
						name="dept" id="dept1"
						ondblclick="callShowDiv(this);"><%=label.get("dept")%></label>
					:</td>
					<td width="10%"><s:hidden name="filterByDeptId"
						value="%{filterByDeptId}" theme="simple" /> <s:textfield
						name="filterByDept" theme="simple" size="15" readonly="true" />
					</td>
					<td width="5%"><img id="ctrlHide"
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'HelpDeskProcess_f9department.action');">
					</td>
					<td width="10%" nowrap="nowrap"><label
						name="request.date" id="request.date2"
						ondblclick="callShowDiv(this);"><%=label.get("request.date")%></label>
					:</td>
					<td width="10%"><s:textfield
						name="filterByDate" theme="simple" size="15"  />
					</td>
					<td width="10%"><s:a
						href="javascript:NewCal('paraFrm_filterByDate','DDMMYYYY');">
						<img class="iconImage" src="../pages/images/recruitment/Date.gif"
							width="16" height="16" border="0" align="absmiddle" />
					</s:a></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table class="" width="100%">
				<tr>
					<script>
		  
		   </script>
					<td><a href="#" onclick="setAction('PD')">Pending
					Requests</a> | <a href="#" onclick="setAction('RD')">Resolved Requests</a></td>
				</tr>
			</table>
			</td>
		</tr>
		

		

		<!-------------------------------------- PENDING LIST OF APPLICATIONS [BEGINS]----------------------------->

		<s:if test="%{listType == 'pending'}">

			<tr>
				<td colspan="2">
				<table width="100%" class="formbg">
					<tr>
						<td width="40%"><b>Pending Requests</b></td>
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
										onclick="callPage('1', 'F', '<%=totalPage%>', 'HelpDeskProcess_input.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPage('P', 'P', '<%=totalPage%>', 'HelpDeskProcess_input.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text" name="pageNoField"
										id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10"
										onkeypress="callPageText(event, '<%=totalPage%>', 'HelpDeskProcess_input.action');return numbersOnly();" />
									of <%=totalPage%> <a href="#"
										onclick="callPage('N', 'N', '<%=totalPage%>', 'HelpDeskProcess_input.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'HelpDeskProcess_input.action');">
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
											name="sno" id="sno" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
										</td>
										<!--
										<td class="formth" width="10%"><b><label class="set"
											name="employee.id" id="employee.id1"
											ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></b>
										</td>
										-->
										<td class="formth" width="10%"><b><label class="set"
											name="request.token" id="request.token"
											ondblclick="callShowDiv(this);"><%=label.get("request.token")%></label></b>
										</td>
										<td class="formth" width="20%"><b><label class="set"
											name="employee" id="employee1"
											ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></b>
										</td>
										<td class="formth" width="10%"><b><label class="set"
											name="category" id="category1"
											ondblclick="callShowDiv(this);"><%=label.get("category")%></label></b>
										</td>
										<td class="formth" width="30%"><b><label class="set"
											name="req.subject" id="req.subject"
											ondblclick="callShowDiv(this);"><%=label.get("req.subject")%></label></b>
										</td>
										<td class="formth" width="15%"><b><label class="set"
											name="request.date" id="request.date1"
											ondblclick="callShowDiv(this);"><%=label.get("request.date")%></label></b>
										</td>
										<td class="formth" width="10%"><b><label class="set"
											name="pendingdays" id="pendingdays1" ondblclick="callShowDiv(this);"><%=label.get("pendingdays")%></label></b>
										</td>
										<td class="formth" width="10%"><b><label class="set"
											name="status" id="status1" ondblclick="callShowDiv(this);"><%=label.get("status")%></label></b>
										</td>
										<td class="formth" width="5%">&nbsp;</td>

									</tr>
									<s:if test="pendingLength">
									<%	int count = 0;	%>
										<%
										int cn = pageNo * 20 - 20;
										%>
										<s:iterator value="pendingList">
										<s:hidden name="escalatedFlagList" />
										<s:hidden name="escalatedFromNameList" />
										<s:hidden name="escalatedTimeList" />
										<!-- 
										<s:if test="escalatedFlagList">
											<tr bgcolor="#C0EDFE">
												<td class="sortableTD" width="5%" align="center"><%=++cn%></td>
												<td class="sortableTD" width="10%" align="center"><s:property
													value="empIdList" /></td>
												<td class="sortableTD" width="20%"><s:hidden
													name="empCodeList" /><s:hidden name="requestIdList" />
												<s:property	value="empNameList" /></td>
												<td class="sortableTD" width="10%"><s:hidden name="requestTokenList" />
												<s:property	value="requestTokenList" /></td>
												<td class="sortableTD" width="20%"><s:hidden name="subjectList" />
												<s:property	value="subjectList" /></td>
												<td class="sortableTD" width="25%"><s:property
													value="applDeptNameList" /><s:hidden name="applDeptCodeList" /></td>
												<td class="sortableTD" width="10%"><s:property
													value="requestDateList" /></td>
												<td class="sortableTD" width="10%" align="center"><s:property value="statusList" /><s:hidden name="requestDateList" /></td>
												<td class="sortableTD" width="5%" align="center"><input
													type="button" name="view_Details" class="token"
													value=" View Application "
													onclick="viewDetails('<s:property value="requestIdList"/>','<s:property value="statusList"/>','true',
													'<s:property value="escalatedFromNameList"/>','<s:property value="escalatedTimeList"/>')" /></td>
											</tr>
										</s:if>
										commenting for now
										 -->
											<tr id="tr1" <%if(count%2==0){
									%>
																class="tableCell1" <%}else{%> class="tableCell2"
																<%	}count++; %>
																onmouseover="javascript:newRowColor(this);"
																onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
																ondblclick="javascript:viewDetails('<s:property value="requestIdList"/>','<s:property value="statusList"/>','false','','')">
												<td class="sortableTD" width="5%" align="center"><%=++cn%></td>
												<!-- 
												<td class="sortableTD" width="10%" align="center"><s:property
													value="empIdList" /></td>
													 -->
												<td class="sortableTD" width="10%"><s:hidden name="requestTokenList" />
												<s:property	value="requestTokenList" /></td>
												<td class="sortableTD" width="20%"><s:hidden
													name="empCodeList" /><s:hidden name="requestIdList" />
												<s:property	value="empNameList" /></td>
												<td class="sortableTD" width="10%"><s:property
													value="categoryList" /><s:hidden name="categoryIdList" /></td>
												<td class="sortableTD" width="30%"><s:hidden name="subjectList" />
												<s:property	value="subjectList" /></td>
												<td class="sortableTD" width="15%"><s:property
													value="requestDateList" /></td>
												<td class="sortableTD" width="10%" align="center"><s:property value="pendingSinceList" /></td>
												<td class="sortableTD" width="10%" align="center"><s:property value="statusList" />
												<s:hidden name="requestDateList" /></td>
												<td class="sortableTD" width="5%" align="center"><input
													type="button" name="view_Details" class="token"
													value=" View "
													onclick="viewDetails('<s:property value="requestIdList"/>','<s:property value="statusList"/>','false','','')" /></td>
											</tr>
										</s:iterator>

									</s:if>
									<s:if test="pendingLength"></s:if>
									<s:else>
										<tr align="center">
											<td colspan="9" class="sortableTD" width="100%"><font
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
<!-- 
			<tr>
				<td>
				<table width="100%" class="formbg" cellspacing="2">
					<tr>
						<td style="color: red;">Note:</td>
					</tr>
					<tr>
						<td style="color: red;">Records in Blue colour represents
						escalated requests.</td>
					</tr>
				</table>
				</td>
			</tr>
 -->
		</s:if>
		<!-------------------------------------- PENDING LIST OF APPLICATIONS [ENDS] ------------------------------->

		<!-------------------------------------- CLOSED APPLICATIONS LIST [BEGINS]----------------------------->

		<s:if test="%{listType == 'closed'}">
			<tr>
				<td colspan="2">
				<table width="100%" class="formbg">
					<tr>
						<td width="40%"><b>Resolved Requests</b></td>
						<%
						int totalPageClosed = 0;
						int PageNoClosed = 0;
					%>
					<s:if test="closedLength">
							<td id="ctrlShow" width="100%" align="right"><b>Page:</b> <%
 			totalPageClosed = (Integer) request
 			.getAttribute("totalPageClosed");
 	PageNoClosed = (Integer) request.getAttribute("pageNoClosed");
 %> <a href="#"
								onclick="callPageClosed('1', 'F', '<%=totalPageClosed%>', 'HelpDeskProcess_getResolvedList.action');">
							<img title="First Page" src="../pages/common/img/first.gif"
								width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPageClosed('P', 'P', '<%=totalPageClosed%>', 'HelpDeskProcess_getResolvedList.action');">
							<img title="Previous Page" src="../pages/common/img/previous.gif"
								width="10" height="10" class="iconImage" /> </a> <input type="text"
								name="pageNoField3" id="pageNoField3" size="3"
								value="<%=PageNoClosed%>" maxlength="10"
								onkeypress="callPageTextClose(event, '<%=totalPageClosed%>', 'HelpDeskProcess_getResolvedList.action');return numbersOnly();" />
							of <%=totalPageClosed%> <a href="#"
								onclick="callPageClosed('N', 'N', '<%=totalPageClosed%>', 'HelpDeskProcess_getResolvedList.action')">
							<img title="Next Page" src="../pages/common/img/next.gif"
								class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPageClosed('<%=totalPageClosed%>', 'L', '<%=totalPageClosed%>', 'HelpDeskProcess_getResolvedList.action');">
							<img title="Last Page" src="../pages/common/img/last.gif"
								width="10" height="10" class="iconImage" /> </a></td>
					</s:if>
					</tr>
						<tr>
								<td colspan="2">
								<table width="100%" class="sorttable">
									<tr>
										<td class="formth" width="5%"><b><label class="set"
											name="sno" id="sno" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
										</td>
										<!-- 
										<td class="formth" width="10%"><b><label class="set"
											name="employee.id" id="employee.id1"
											ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></b>
										</td>
										 -->
										 <td class="formth" width="10%"><b><label class="set"
											name="request.token" id="request.token"
											ondblclick="callShowDiv(this);"><%=label.get("request.token")%></label></b>
										</td>
										<td class="formth" width="20%"><b><label class="set"
											name="employee" id="employee1"
											ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></b>
										</td>
										<td class="formth" width="10%"><b><label class="set"
											name="category" id="category2"
											ondblclick="callShowDiv(this);"><%=label.get("category")%></label></b>
										</td>
										<td class="formth" width="30%"><b><label class="set"
											name="req.subject" id="req.subject"
											ondblclick="callShowDiv(this);"><%=label.get("req.subject")%></label></b>
										</td>
										<td class="formth" width="15%"><b><label class="set"
											name="request.date" id="request.date1"
											ondblclick="callShowDiv(this);"><%=label.get("request.date")%></label></b>
										</td>
										<td class="formth" width="10%"><b><label class="set"
											name="status" id="status1" ondblclick="callShowDiv(this);"><%=label.get("status")%></label></b>
										</td>
										<td class="formth" width="5%"><b>View Application</b></td>

									</tr>
									<s:if test="closedLength">
									<%	int count1 = 0;	%>
										<%
										int cnClosed = PageNoClosed * 20 - 20;
										%>
										<s:iterator value="closedList">
											<tr id="tr2" <%if(count1%2==0){	%>
																class="tableCell1" <%}else{%> class="tableCell2"
																<%	}count1++; %>
																onmouseover="javascript:newRowColor(this);"
																onmouseout="javascript:oldRowColor(this,<%=count1%2 %>);"
																ondblclick="javascript:viewDetails('<s:property value="requestIdList"/>','<s:property value="statusList"/>','false','','')">
												<td class="sortableTD" width="5%" align="center"><%=++cnClosed%></td>
												<!-- 
												<td class="sortableTD" width="10%" align="center"><s:property
													value="empIdList" /></td>
												 -->
												 <td class="sortableTD" width="10%"><s:hidden name="requestTokenList" />
												<s:property	value="requestTokenList" /></td>
												<td class="sortableTD" width="20%"><s:hidden
													name="empCodeList" /><s:hidden name="requestIdList" />
												<s:property	value="empNameList" /></td>
												<td class="sortableTD" width="10%"><s:property
													value="categoryList" /><s:hidden name="categoryIdList" /></td>
												<td class="sortableTD" width="30%"><s:hidden name="subjectList" />
												<s:property	value="subjectList" /></td>
												<td class="sortableTD" width="15%"><s:property
													value="requestDateList" /></td>
												<td class="sortableTD" width="10%" align="center"><s:property value="statusList" /><s:hidden name="requestDateList" /></td>
												<td class="sortableTD" width="5%" align="center"><input
													type="button" name="view_Details" class="token"
													value=" View "
													onclick="viewDetails('<s:property value="requestIdList"/>','<s:property value="statusList"/>','false','','')" /></td>
											</tr>

										</s:iterator>

									</s:if>
									<s:if test="closedLength"></s:if>
									<s:else>
										<tr align="center">
											<td colspan="9" class="sortableTD" width="100%"><font
												color="red">No Data to display</font></td>
										</tr>
									</s:else>
								</table>
								</td>
							</tr>
				</table>
				</td>
			</tr>

		</s:if>
		<!-------------------------------------- CLOSED APPLICATIONS LIST [ENDS]----------------------------->
	</table>

</s:form>
<script>
onload();

function refreshPage(){
       parent.frames[2].location=parent.frames[2].location;
}


function onload(){
		if(document.getElementById('checkSearch').value==1){
				showtr();
		}
		var interval = setInterval("refreshPage();", 300000);
		
}
function addnewFun() {
	document.getElementById('paraFrm').action = 'HelpDeskProcess_addNew.action';
	document.getElementById('paraFrm').submit();
}

function viewDetails(reqNo,status,escalatedFlag,escalatedFrom,escalatedTime){

	document.getElementById('paraFrm_escalatedFlag').value=escalatedFlag;
	document.getElementById('paraFrm_escalatedFromName').value=escalatedFrom;
	document.getElementById('paraFrm_escalatedTime').value=escalatedTime;
	document.getElementById('paraFrm').target = "_self";
   	document.getElementById('paraFrm').action='HelpDeskProcess_retrieveDetails.action?reqAppCode='+reqNo+'&reqStatus='+status+'&listStatus=details';
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
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	
	function setAction(listType){
	
       	document.getElementById('paraFrm').action='HelpDeskProcess_input.action?listStatus='+listType;
      	document.getElementById('paraFrm').submit();
	}
	function showDiv(){
		document.getElementById('view').style.display = 'none';
		document.getElementById('hideLink').style.display = 'none';
 		document.getElementById('showLink').style.display = '';
 		document.getElementById('checkSearch').value="";
	}
	function checkIsExpandSearch(id){
		document.getElementById('checkSearch').value=id;
	}
	function showtr() {
 		document.getElementById('view').style.display = '';
 		document.getElementById('hideLink').style.display = '';
 		document.getElementById('showLink').style.display = 'none';
 	}
	function hidetr() {
 		document.getElementById('view').style.display = 'none';
 		document.getElementById('hideLink').style.display = 'none';
 		document.getElementById('showLink').style.display = '';
 	}
	function searchRecord() {
		if(!validateFilter()){    
			return false;  
		}else{
			//document.getElementById("paraFrm").target = 'main';
	  		document.getElementById('paraFrm').action = 'HelpDeskProcess_input.action?listStatus=1';
			document.getElementById('paraFrm').submit();
			}
	}
	function validateFilter(){
		if(document.getElementById('paraFrm_filterByStatus').value==""){
			alert("Please select status");
			return false;
		}
		if(!validateDate('paraFrm_filterByDate','request.date2')){
			return false;	
		}
		return true;
	}
	
	function clearFilter(){
		document.getElementById('paraFrm_filterByStatus').value="";  
		document.getElementById('paraFrm_filterById').value="";  
		document.getElementById('paraFrm_filterByReqToken').value="";  
		document.getElementById('paraFrm_filterByEmpId').value="";  
		document.getElementById('paraFrm_filterByEmpToken').value="";  
		document.getElementById('paraFrm_filterByEmp').value="";  
		document.getElementById('paraFrm_filterByCatId').value="";  
		document.getElementById('paraFrm_filterByCat').value="";  
		document.getElementById('paraFrm_filterByDeptId').value="";  
		document.getElementById('paraFrm_filterByDept').value="";  
		document.getElementById('paraFrm_filterByDate').value="";  
		
		document.getElementById('paraFrm').action = 'HelpDeskProcess_input.action';
		document.getElementById('paraFrm').submit();
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