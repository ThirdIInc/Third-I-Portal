<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="DepartmentLocationChange" method="post"
	name="DepartmentandLocationChange" id="paraFrm" theme="simple">
<s:hidden name="myPageApproved" id="myPageApproved" />
<s:hidden name="myPageReturn" id="myPageReturn" />
<s:hidden name="myPageApprovedList" id="myPageApprovedList" />
<s:hidden name="myPageApprovedCancelList" id="myPageApprovedCancelList" />
<s:hidden name="myPageReject" id="myPageReject" />
<s:hidden name="myPageRejectCancel" id="myPageRejectCancel" />
<s:hidden name="myPageCancel" id="myPageRejectCancel" />
	<s:hidden name="myPageRejected" id="myPageRejected" />
	<s:hidden name="myPageCancelled" id="myPageCancelled" />
	<s:hidden name="myPageCancelRejected" id="myPageCancelRejected" />
	<s:hidden name="myPageCancelApproved" id="myPageCancelApproved" />
	
	<s:hidden name="myPage" id="myPage" />
	<s:hidden name="deptCode"></s:hidden>
<s:hidden name="status"/>


	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">

		<!-- FORM NAME START -->
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Department
					and Location Change Application </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- FORM NAME END -->
		<!-- BUTTON PANEL START -->
		<tr>
			<td width="100%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

		</tr>

		<!-- BUTTON PANEL END -->
		<!--------------------------------------  Draft LIST OF APPLICATIONS [BEGINS]----------------------------->
		<tr>
			<td>
			<table class="" width="100%">
				<tr>
					
					<td><a href="#" onclick="callPending();">Pending
					Application</a> | <a href="#" onclick="callApprove();" >Approved
					List</a>  | <a href="#" onclick="callReject();">Rejected
					List</a></td>
				</tr>
			
			
			</table>
			</td>
		</tr>

		<!----Draft List ----->

		<tr id="Draft">
			<td>
			<table width="100%" class="formbg">

		<tr>
			<td width="100%">
			<table width="100%">
				<tr>
	<td width="30%" align="left"><b>Draft</b></td>
					<td width="70%" align="right" ><%
 	int totalPage = 0;
 	int pageNo = 0;
 %>
					<s:if test="draftVoucherListLength">
								<table>
								<tr>
					<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
					<%
						try{
					    totalPage = (Integer) request.getAttribute("totalPage");
						pageNo = (Integer) request.getAttribute("pageNo");
						}catch(Exception e){
							e.printStackTrace();
						}
					%> <a href="#"
						onclick="callPage('1', 'F', '<%=totalPage%>', 'DepartmentLocationChange_callPage.action');">
					<img title="First Page" src="../pages/common/img/first.gif"
						width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
						onclick="callPage('P', 'P', '<%=totalPage%>', 'DepartmentLocationChange_callPage.action');">
					<img title="Previous Page" src="../pages/common/img/previous.gif"
						width="10" height="10" class="iconImage" /> </a> <input type="text"
						name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
						maxlength="10"
						onkeypress="callPageText(event, '<%=totalPage%>', 'DepartmentLocationChange_callPage.action');return numbersOnly();" />
					of <%=totalPage%> <a href="#"
						onclick="callPage('N', 'N', '<%=totalPage%>', 'DepartmentLocationChange_callPage.action')">
					<img title="Next Page" src="../pages/common/img/next.gif"
						class="iconImage" /> </a>&nbsp; <a href="#"
						onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'DepartmentLocationChange_callPage.action');">
					<img title="Last Page" src="../pages/common/img/last.gif"
						width="10" height="10" class="iconImage" /> </a></td>

				</tr>
								</table>
							</s:if>



				</tr>
			</table>
			</td>
		</tr>

				
				<tr>
					<td>
					<table width="100%" class="sorttable">
						
						
						<tr>
							<td class="formth" width="5%"><b><label class="set"
								name="sno" id="sno1" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
							</td>
							<td class="formth" width="15%"><b><label class="set"
								name="emp_no" id="emp_no" ondblclick="callShowDiv(this);"><%=label.get("emp_no")%></label></b>
							</td>

							<td class="formth" width="40%"><b><label class="set"
								name="employee" id="employee"
								ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></b>
							</td>

							<td class="formth" width="20%"><b><label class="set"
								name="app_date" id="app_date" ondblclick="callShowDiv(this);"><%=label.get("app_date")%></label></b>
							</td>
							<td class="formth" width="20%"><b>Details</b></td>

						</tr>
					<s:if test="draftVoucherListLength">
						<%
										int count = 0;
									%>
									<%!int d = 0;%>
									<%
										int i = pageNo * 20 - 20;									
									%>
								
						<s:iterator value="draftList">
							<tr>
								<td class="sortableTD" width="5%" align="center"><%=++i%></td>
								
								<td class="sortableTD" width="15%" nowrap="nowrap"><!--<s:property value="empNum" />
								--><s:property value="authorizedToken" /></td>
								<td class="sortableTD" width="40%"><s:property
									value="fname" /></td>

								<td class="sortableTD" width="20%" align="center"><s:property
									value="depAppDate" /></td>

								<td class="sortableTD" width="20%" align="center"><input
									type="button" name="view_Details" class="token" id="ctrlShow"
									value=" Edit Application "  onclick="javascript:draftEdit('<s:property value="deptCode"/>')"/> </td>
							</tr>
							
						</s:iterator>
						<%
										d = i;
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

		<!----Draft List End----->

		<!-- Pending List Starts -->
		<tr id="pending">
			<td>
			<table width="100%" class="formbg">
			
				
					<tr>
			
			<td width="100%">
			<table width="100%">
				<tr>
	<td  width="30%" align="left"><b>Send for Approval List</b></td>
					<td  width="70%" align="right"><%
 	int totalPageApproved = 0;
 	int pageNoApproved = 0;
 %>
					<s:if test="inProcessVoucherListLength">
								<table>
								<tr>
					<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
					<%
					
					try{
						totalPageApproved = (Integer) request.getAttribute("totalPage");
						pageNoApproved = (Integer) request.getAttribute("pageNo");
						}catch(Exception e){
							e.printStackTrace();
						}
					%> <a href="#"
						onclick="callPageApproved('1', 'F', '<%=totalPageApproved%>', 'DepartmentLocationChange_callPage.action');">
					<img title="First Page" src="../pages/common/img/first.gif"
						width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
						onclick="callPageApproved('P', 'P', '<%=totalPageApproved%>', 'DepartmentLocationChange_callPage.action');">
					<img title="Previous Page" src="../pages/common/img/previous.gif"
						width="10" height="10" class="iconImage" /> </a> <input type="text"
						name="pageNoFieldApproved" id="pageNoFieldApproved" size="3" value="<%=pageNoApproved%>"
						maxlength="10"
						onkeypress="callPageTextApproved(event, '<%=totalPageApproved%>', 'DepartmentLocationChange_callPage.action');return numbersOnly();" />
					of <%=totalPageApproved%> <a href="#"
						onclick="callPageApproved('N', 'N', '<%=totalPageApproved%>', 'DepartmentLocationChange_callPage.action')">
					<img title="Next Page" src="../pages/common/img/next.gif"
						class="iconImage" /> </a>&nbsp; <a href="#"
						onclick="callPageApproved('<%=totalPageApproved%>', 'L', '<%=totalPageApproved%>', 'DepartmentLocationChange_callPage.action');">
					<img title="Last Page" src="../pages/common/img/last.gif"
						width="10" height="10" class="iconImage" /> </a></td>

				</tr>
								</table>
							</s:if>



				</tr>
			</table>
			</td>
		</tr>
				
				<tr>
					<td>
					<table width="100%" class="sorttable">
						<tr>
							<td class="formth" width="5%"><b><label class="set"
								name="sno" id="sno1" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
							</td>
							<td class="formth" width="15%"><b><label class="set"
								name="emp_no" id="emp_no" ondblclick="callShowDiv(this);"><%=label.get("emp_no")%></label></b>
							</td>

							<td class="formth" width="40%"><b><label class="set"
								name="employee" id="employee"
								ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></b>
							</td>

							<td class="formth" width="20%"><b><label class="set"
								name="app_date" id="app_date" ondblclick="callShowDiv(this);"><%=label.get("app_date")%></label></b>
							</td>
							<td class="formth" width="20%"><b>Details</b></td>

						</tr>

						<s:if test="inProcessVoucherListLength">
									<%
										int count1 = 0;
									%>
									<%!int d1 = 0;%>
									<%
										int i1 = pageNoApproved * 20 - 20;									
									%>

						<s:iterator value="pedingList">
							<tr>
								<td class="sortableTD" width="5%" align="center"><%=++i1%></td>
								<td class="sortableTD" width="15%" nowrap="nowrap"><s:hidden
									name="pendingCode" /> <!--<s:property value="empNum" />
									-->
									<s:property value="authorizedToken" />
									</td>
								<td class="sortableTD" width="40%"><s:property
									value="fname" /></td>

								<td class="sortableTD" width="20%" align="center"><s:property
									value="depAppDate" /></td>

								<td class="sortableTD" width="20%" align="center"><input
									type="button" name="view_Details" class="token" id="ctrlShow"
									value=" View Application "  onclick="javascript:viewPendingDetails('<s:property value="deptCode"/>')" /></td>
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



	<tr id="sendBack">
			<td>
			<table width="100%" class="formbg">
				<tr>
			
			<td width="100%">
			<table width="100%">
				<tr>
	<td  width="30%" align="left" ><b>Return Application List</b></td>
					<td width="70%" align="right"><%
 	int totalPageReturn = 0;
 	int pageNoReturn = 0;
 %>
					<s:if test="sentBackVoucherListLength">
								<table>
								<tr>
					<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
					<%
					try{
						totalPageReturn = (Integer) request.getAttribute("totalSendBackPage");
						pageNoReturn = (Integer) request.getAttribute("pageSendBackNo");
						}catch(Exception e){
							e.printStackTrace();
						}
					%> <a href="#"
						onclick="callPageReturn('1', 'F', '<%=totalPageReturn%>', 'DepartmentLocationChange_callPage.action');">
					<img title="First Page" src="../pages/common/img/first.gif"
						width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
						onclick="callPageReturn('P', 'P', '<%=totalPageReturn%>', 'DepartmentLocationChange_callPage.action');">
					<img title="Previous Page" src="../pages/common/img/previous.gif"
						width="10" height="10" class="iconImage" /> </a> <input type="text"
						name="pageNoFieldReturn" id="pageNoFieldReturn" size="3" value="<%=pageNoReturn%>"
						maxlength="10"
						onkeypress="callPageTextReturn(event, '<%=totalPageReturn%>', 'DepartmentLocationChange_callPage.action');return numbersOnly();" />
					of <%=totalPageReturn%> <a href="#"
						onclick="callPageReturn('N', 'N', '<%=totalPageReturn%>', 'DepartmentLocationChange_callPage.action')">
					<img title="Next Page" src="../pages/common/img/next.gif"
						class="iconImage" /> </a>&nbsp; <a href="#"
						onclick="callPageReturn('<%=totalPageReturn%>', 'L', '<%=totalPageReturn%>', 'DepartmentLocationChange_callPage.action');">
					<img title="Last Page" src="../pages/common/img/last.gif"
						width="10" height="10" class="iconImage" /> </a></td>

					</tr>
								</table>
							</s:if>



				</tr>
			</table>
			</td>
		</tr>
				<tr>
					<td>
					<table width="100%" class="sorttable">
						<tr>
							<td class="formth" width="5%"><b><label class="set"
								name="sno" id="sno1" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
							</td>
							<td class="formth" width="15%"><b><label class="set"
								name="emp_no" id="emp_no" ondblclick="callShowDiv(this);"><%=label.get("emp_no")%></label></b>
							</td>

							<td class="formth" width="40%"><b><label class="set"
								name="employee" id="employee"
								ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></b>
							</td>

							<td class="formth" width="20%"><b><label class="set"
								name="app_date" id="app_date" ondblclick="callShowDiv(this);"><%=label.get("app_date")%></label></b>
							</td>
							<td class="formth" width="20%"><b>Details</b></td>

						</tr>

						<s:if test="sentBackVoucherListLength">
									<%
										int count7 = 0;
									%>
									<%!int d7 = 0;%>
									<%
										int i7 = pageNoReturn * 20 - 20;										
									%>
						<s:iterator value="returnList">
							<tr>
								<td class="sortableTD" width="5%" align="center"><%=++i7%></td>
								<td class="sortableTD" width="15%" nowrap="nowrap"><s:hidden
									name="pendingCode" /><!-- <s:property value="empNum" />
									--><s:property value="authorizedToken" />
									</td>
								<td class="sortableTD" width="40%"><s:property
									value="fname" /></td>

								<td class="sortableTD" width="20%" align="center"><s:property
									value="depAppDate" /></td>

								<td class="sortableTD" width="20%" align="center"><input
									type="button" name="view_Details" class="token" id="ctrlShow"
									value=" View Application "  onclick="javascript:draftEdit('<s:property value="deptCode"/>')" /></td>
							</tr>
							
						</s:iterator>
						<%
										d7 = i7;
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


		<!----Pending List End ----->

<!-- Approved List Start -->
		<tr id="Approved" style="display:none">
			<td>
			<table width="100%" class="formbg">
				
					<tr>
			
			<td width="100%">
			<table width="100%">
				<tr>
	<td><b>Approved List</b></td>
					<td  width="70%" align="right"><%
 	int totalPageApprovedList = 0;
 	int pageNoApprovedList = 0;
 %>
					<s:if test="approvedVoucherListLength">
								<table>
								<tr>
					<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
					<%
					try{
						totalPageApprovedList = (Integer) request.getAttribute("totalApprovedPage");
						pageNoApprovedList = (Integer) request.getAttribute("pageApprovedNo");
						}catch(Exception e){
							e.printStackTrace();
						}
					%> <a href="#"
						onclick="callPageReturn('1', 'F', '<%=totalPageApprovedList%>', 'DepartmentLocationChange_callPage.action');">
					<img title="First Page" src="../pages/common/img/first.gif"
						width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
						onclick="callPageApprovedList('P', 'P', '<%=totalPageApprovedList%>', 'DepartmentLocationChange_callPage.action');">
					<img title="Previous Page" src="../pages/common/img/previous.gif"
						width="10" height="10" class="iconImage" /> </a> <input type="text"
						name="pageNoFieldApprovedList" id="pageNoFieldApprovedList" size="3" value="<%=pageNoApprovedList%>"
						maxlength="10"
						onkeypress="callPageTextApprovedList(event, '<%=totalPageApprovedList%>', 'DepartmentLocationChange_callPage.action');return numbersOnly();" />
					of <%=totalPageApprovedList%> <a href="#"
						onclick="callPageApprovedList('N', 'N', '<%=totalPageApprovedList%>', 'DepartmentLocationChange_callPage.action')">
					<img title="Next Page" src="../pages/common/img/next.gif"
						class="iconImage" /> </a>&nbsp; <a href="#"
						onclick="callPageApprovedList('<%=totalPageApprovedList%>', 'L', '<%=totalPageApprovedList%>', 'DepartmentLocationChange_callPage.action');">
					<img title="Last Page" src="../pages/common/img/last.gif"
						width="10" height="10" class="iconImage" /> </a></td>
					</tr>
								</table>
							</s:if>

				</tr>
			</table>
			</td>
		</tr>
				<tr>
					<td>
					<table width="100%" class="sorttable">
						<tr>
							<td class="formth" width="5%"><b><label class="set"
								name="sno" id="sno1" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
							</td>
							<td class="formth" width="15%"><b><label class="set"
								name="emp_no" id="emp_no" ondblclick="callShowDiv(this);"><%=label.get("emp_no")%></label></b>
							</td>

							<td class="formth" width="40%"><b><label class="set"
								name="employee" id="employee"
								ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></b>
							</td>

							<td class="formth" width="20%"><b><label class="set"
								name="app_date" id="app_date" ondblclick="callShowDiv(this);"><%=label.get("app_date")%></label></b>
							</td>
							<td class="formth" width="20%"><b>Details</b></td>

						</tr>

						<s:if test="approvedVoucherListLength">
									<%
										int count2 = 0;
									%>
									<%!int d2 = 0;%>
									<%
										int i2 = pageNoApprovedList * 20 - 20;		
																	
									%>
						<s:iterator value="approvedList">
							<tr>
								<td class="sortableTD" width="5%" align="center"><%=++i2%></td>
								<td class="sortableTD" width="15%" nowrap="nowrap"><s:hidden
									name="approveCode" /> <!--<s:property value="empNum" />
									-->
									<s:property value="authorizedToken" />
									</td>
								<td class="sortableTD" width="40%"><s:property
									value="fname" /></td>

								<td class="sortableTD" width="20%" align="center"><s:property
									value="depAppDate" /></td>

								<td class="sortableTD" width="20%" align="center"><input
									type="button" name="view_Details" class="token" id="ctrlShow"
									value=" View Application " onclick="javascript:viewPendingDetails1('<s:property value="deptCode"/>')" /></td>
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
		<!----Approved List End----->

<tr id="ApprovedCancel" style="display:none">
			<td>
			<table width="100%" class="formbg">
				<tr>
			<td width="100%">
			<table width="100%">
				<tr>
	<td><b>Approved Cancellation Applications List</b></td>
					<td align="left" colspan="2"><%
 	int totalPageApprovedCancelList = 0;
 	int pageNoApprovedCancelList = 0;
 %>
					
					<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
					<%
					
					try{
						totalPageApprovedCancelList = (Integer) request.getAttribute("totalPage");
						pageNoApprovedCancelList = (Integer) request.getAttribute("pageNo");
						}catch(Exception e){
							e.printStackTrace();
						}
					%> <a href="#"
						onclick="callPage('1', 'F', '<%=totalPageApprovedCancelList%>', 'DepartmentLocationChange_callPage.action');">
					<img title="First Page" src="../pages/common/img/first.gif"
						width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
						onclick="callPageApprovedCancelList('P', 'P', '<%=totalPageApprovedCancelList%>', 'DepartmentLocationChange_callPage.action');">
					<img title="Previous Page" src="../pages/common/img/previous.gif"
						width="10" height="10" class="iconImage" /> </a> <input type="text"
						name="pageNoFieldApprovedCancelList" id="pageNoFieldApprovedCancelList" size="3" value="<%=pageNoApprovedCancelList%>"
						maxlength="10"
						onkeypress="callPageTextApprovedCancelList(event, '<%=totalPageApprovedCancelList%>', 'DepartmentLocationChange_callPage.action');return numbersOnly();" />
					of <%=totalPage%> <a href="#"
						onclick="callPageApprovedCancelList('N', 'N', '<%=totalPageApprovedCancelList%>', 'DepartmentLocationChange_callPage.action')">
					<img title="Next Page" src="../pages/common/img/next.gif"
						class="iconImage" /> </a>&nbsp; <a href="#"
						onclick="callPageApprovedCancelList('<%=totalPageApprovedCancelList%>', 'L', '<%=totalPageApprovedCancelList%>', 'DepartmentLocationChange_callPage.action');">
					<img title="Last Page" src="../pages/common/img/last.gif"
						width="10" height="10" class="iconImage" /> </a></td>





				</tr>
			</table>
			</td>
		</tr>
				<tr>
					<td>
					<table width="100%" class="sorttable">
						<tr>
							<td class="formth" width="5%"><b><label class="set"
								name="sno" id="sno1" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
							</td>
							<td class="formth" width="15%"><b><label class="set"
								name="emp_no" id="emp_no" ondblclick="callShowDiv(this);"><%=label.get("emp_no")%></label></b>
							</td>

							<td class="formth" width="40%"><b><label class="set"
								name="employee" id="employee"
								ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></b>
							</td>

							<td class="formth" width="20%"><b><label class="set"
								name="app_date" id="app_date" ondblclick="callShowDiv(this);"><%=label.get("app_date")%></label></b>
							</td>
							<td class="formth" width="20%"><b>Details</b></td>

						</tr>

						<%
							int a= 1;
							%>
							<%!int totalAC = 0;%>
						<s:iterator value="approvedCancelList">
							<tr>
								<td class="sortableTD" width="5%" align="center"><%=a%></td>
								<td class="sortableTD" width="15%" nowrap="nowrap"><s:hidden
									name="approveCode" /> <!--<s:property value="empNum" />
									-->
									<s:property value="authorizedToken" />
									</td>
								<td class="sortableTD" width="40%"><s:property
									value="fname" /></td>

								<td class="sortableTD" width="20%" align="center"><s:property
									value="depAppDate" /></td>

								<td class="sortableTD" width="20%" align="center"><input
									type="button" name="view_Details" class="token" id="ctrlShow"
									value=" View Application " onclick="javascript:viewPendingDetails('<s:property value="deptCode"/>')" /></td>
							</tr>
							<%
								a++;
								%>
						</s:iterator>
						<%totalAC=a; %>
						<%
							if (a == 1) {
							%>
						<tr align="center">
							<td colspan="6" class="sortableTD" width="100%"><font
								color="red">No Data to display</font></td>
						</tr>
						<%
							}
							%>

					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
	
           <!-- Rejected List Start-->
	<tr id="Reject" style="display:none">
			<td>
			<table width="100%" class="formbg">
							
					<tr>
			<td width="100%">
			<table width="100%">
				<tr>
	<td><b>Rejected List</b></td>
					<td width="70%" align="right"><%
 	int totalPageReject = 0;
 	int pageNoReject = 0;
 %>
					<s:if test="rejectedVoucherListLength">
								<table>
								<tr>
					<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
					<%
					
						try{
							totalPageReject = (Integer) request.getAttribute("totalRejectedPage");
							pageNoReject = (Integer) request.getAttribute("pageRejectedNo");
							}catch(Exception e){
								e.printStackTrace();
						}
					%> <a href="#"
						onclick="callPage('1', 'F', '<%=totalPageReject%>', 'DepartmentLocationChange_callPage.action');">
					<img title="First Page" src="../pages/common/img/first.gif"
						width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
						onclick="callPageReject('P', 'P', '<%=totalPageReject%>', 'DepartmentLocationChange_callPage.action');">
					<img title="Previous Page" src="../pages/common/img/previous.gif"
						width="10" height="10" class="iconImage" /> </a> <input type="text"
						name="pageNoFieldReject" id="pageNoFieldReject" size="3" value="<%=pageNoReject%>"
						maxlength="10"
						onkeypress="callPageTextReject(event, '<%=totalPageReject%>', 'DepartmentLocationChange_callPage.action');return numbersOnly();" />
					of <%=totalPageReject%> <a href="#"
						onclick="callPageReject('N', 'N', '<%=totalPageReject%>', 'DepartmentLocationChange_callPage.action')">
					<img title="Next Page" src="../pages/common/img/next.gif"
						class="iconImage" /> </a>&nbsp; <a href="#"
						onclick="callPageReject('<%=totalPageReject%>', 'L', '<%=totalPageReject%>', 'DepartmentLocationChange_callPage.action');">
					<img title="Last Page" src="../pages/common/img/last.gif"
						width="10" height="10" class="iconImage" /> </a></td>
						</tr>
								</table>
							</s:if>
				</tr>
			</table>
			</td>
		</tr>
		
				<tr>
					<td>
					<table width="100%" class="sorttable">
						<tr>
							<td class="formth" width="5%"><b><label class="set"
								name="sno" id="sno1" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
							</td>
							<td class="formth" width="15%"><b><label class="set"
								name="emp_no" id="emp_no" ondblclick="callShowDiv(this);"><%=label.get("emp_no")%></label></b>
							</td>

							<td class="formth" width="40%"><b><label class="set"
								name="employee" id="employee"
								ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></b>
							</td>

							<td class="formth" width="20%"><b><label class="set"
								name="app_date" id="app_date" ondblclick="callShowDiv(this);"><%=label.get("app_date")%></label></b>
							</td>
							<td class="formth" width="20%"><b>Details</b></td>

						</tr>

						<s:if test="rejectedVoucherListLength">
									<%
										int count5 = 0;
									%>
									<%!int d5 = 0;%>
									<%
										int i5 = pageNoReject * 20 - 20;									
									%>

						<s:iterator value="rejectedList">
							<tr>
								<td class="sortableTD" width="5%" align="center"><%=++i5%></td>
								<td class="sortableTD" width="15%" nowrap="nowrap"><s:hidden
									name="rejectCode" /> <!--<s:property value="empNum" />
									--><s:property value="authorizedToken" /></td>
								<td class="sortableTD" width="40%"><s:property
									value="fname" /></td>

								<td class="sortableTD" width="20%" align="center"><s:property
									value="depAppDate" /></td>

								<td class="sortableTD" width="20%" align="center"><input
									type="button" name="view_Details" class="token" id="ctrlShow"
									value=" View Application "  onclick="javascript:viewDetails('<s:property value="deptCode"/>')" /></td>
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

	
		<tr id="ApprovedReject" style="display:none">
			<td>
			<table width="100%" class="formbg">
						<tr>
			
			<td width="100%">
			<table width="100%">
				<tr>
	<td><b>Rejected Cancellation Applications List</b></td>
					<td align="left" colspan="2"><%
 	int totalPageRejectCancel = 0;
 	int pageNoRejectCancel = 0;
 %>
					
					<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
					<%
					
					try{
						totalPageRejectCancel = (Integer) request.getAttribute("totalPage");
						pageNoApproved = (Integer) request.getAttribute("pageNo");
						}catch(Exception e){
							e.printStackTrace();
					}
					%> <a href="#"
						onclick="callPageRejectCancel('1', 'F', '<%=totalPageRejectCancel%>', 'DepartmentLocationChange_callPage.action');">
					<img title="First Page" src="../pages/common/img/first.gif"
						width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
						onclick="callPageRejectCancel('P', 'P', '<%=totalPageRejectCancel%>', 'DepartmentLocationChange_callPage.action');">
					<img title="Previous Page" src="../pages/common/img/previous.gif"
						width="10" height="10" class="iconImage" /> </a> <input type="text"
						name="pageNoFieldRejectCancel" id="pageNoFieldRejectCancel" size="3" value="<%=pageNoRejectCancel%>"
						maxlength="10"
						onkeypress="callPageTextRejectCancel(event, '<%=totalPageRejectCancel%>', 'DepartmentLocationChange_callPage.action');return numbersOnly();" />
					of <%=totalPageApproved%> <a href="#"
						onclick="callPageRejectCancel('N', 'N', '<%=totalPageRejectCancel%>', 'DepartmentLocationChange_callPage.action')">
					<img title="Next Page" src="../pages/common/img/next.gif"
						class="iconImage" /> </a>&nbsp; <a href="#"
						onclick="callPageRejectCancel('<%=totalPageRejectCancel%>', 'L', '<%=totalPageRejectCancel%>', 'DepartmentLocationChange_callPage.action');">
					<img title="Last Page" src="../pages/common/img/last.gif"
						width="10" height="10" class="iconImage" /> </a></td>





				</tr>
			</table>
			</td>
		</tr>
				<tr>
					<td>
					<table width="100%" class="sorttable">
						<tr>
							<td class="formth" width="5%"><b><label class="set"
								name="sno" id="sno1" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
							</td>
							<td class="formth" width="15%"><b><label class="set"
								name="emp_no" id="emp_no" ondblclick="callShowDiv(this);"><%=label.get("emp_no")%></label></b>
							</td>

							<td class="formth" width="40%"><b><label class="set"
								name="employee" id="employee"
								ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></b>
							</td>

							<td class="formth" width="20%"><b><label class="set"
								name="app_date" id="app_date" ondblclick="callShowDiv(this);"><%=label.get("app_date")%></label></b>
							</td>
							<td class="formth" width="20%"><b>Details</b></td>

						</tr>

						<%
							int b= 1;
							%>
							<%!int totalRejectCancel = 0;%>
						<s:iterator value="approvedRejectList">
							<tr>
								<td class="sortableTD" width="5%" align="center"><%=b%></td>
								<td class="sortableTD" width="15%" nowrap="nowrap"><s:hidden
									name="approveCode" /> <!--<s:property value="empNum" />
									-->
									<s:property value="authorizedToken" /></td>
								<td class="sortableTD" width="40%"><s:property
									value="fname" /></td>

								<td class="sortableTD" width="20%" align="center"><s:property
									value="depAppDate" /></td>

								<td class="sortableTD" width="20%" align="center"><input
									type="button" name="view_Details" class="token" id="ctrlShow"
									value=" View Application " onclick="javascript:viewPendingDetails('<s:property value="deptCode"/>')" /></td>
							</tr>
							<%
								b++;
								%>
								<%totalRejectCancel=b; %>
						</s:iterator>

						<%
							if (b == 1) {
							%>
						<tr align="center">
							<td colspan="6" class="sortableTD" width="100%"><font
								color="red">No Data to display</font></td>
						</tr>
						<%
							}
							%>

					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>

             <!-- Rejected List End -->
             
             
             <tr id="cancel" style="display:none">
			<td>
			<table width="100%" class="formbg">
				
				
						<tr>
			
			<td width="100%">
			<table width="100%">
				<tr>
	<td><b>Cancel List</b></td>
					<td align="left" colspan="2"><%
 	int totalPageCancel = 0;
 	int pageNoCancel = 0;
 %>
					
					<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
					<%
					
					try{
						totalPageCancel = (Integer) request.getAttribute("totalPage");
						pageNoCancel = (Integer) request.getAttribute("pageNo");
						}catch(Exception e){
							e.printStackTrace();
						}
					%> <a href="#"
						onclick="callPageCancel('1', 'F', '<%=totalPageCancel%>', 'DepartmentLocationChange_callPage.action');">
					<img title="First Page" src="../pages/common/img/first.gif"
						width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
						onclick="callPageCancel('P', 'P', '<%=totalPageCancel%>', 'DepartmentLocationChange_callPage.action');">
					<img title="Previous Page" src="../pages/common/img/previous.gif"
						width="10" height="10" class="iconImage" /> </a> <input type="text"
						name="pageNoFieldCancel" id="pageNoFieldCancel" size="3" value="<%=pageNoCancel%>"
						maxlength="10"
						onkeypress="callPageTextCancel(event, '<%=totalPageCancel%>', 'DepartmentLocationChange_callPage.action');return numbersOnly();" />
					of <%=totalPageApproved%> <a href="#"
						onclick="callPageCancel('N', 'N', '<%=totalPageCancel%>', 'DepartmentLocationChange_callPage.action')">
					<img title="Next Page" src="../pages/common/img/next.gif"
						class="iconImage" /> </a>&nbsp; <a href="#"
						onclick="callPageCancel('<%=totalPageCancel%>', 'L', '<%=totalPageCancel%>', 'DepartmentLocationChange_callPage.action');">
					<img title="Last Page" src="../pages/common/img/last.gif"
						width="10" height="10" class="iconImage" /> </a></td>





				</tr>
			</table>
			</td>
		</tr>
				<tr>
					<td>
					<table width="100%" class="sorttable">
						<tr>
							<td class="formth" width="5%"><b><label class="set"
								name="sno" id="sno1" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
							</td>
							<td class="formth" width="15%"><b><label class="set"
								name="emp_no" id="emp_no" ondblclick="callShowDiv(this);"><%=label.get("emp_no")%></label></b>
							</td>

							<td class="formth" width="40%"><b><label class="set"
								name="employee" id="employee"
								ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></b>
							</td>

							<td class="formth" width="20%"><b><label class="set"
								name="app_date" id="app_date" ondblclick="callShowDiv(this);"><%=label.get("app_date")%></label></b>
							</td>
							<td class="formth" width="20%"><b>Details</b></td>

						</tr>

						<%
							int c= 1;
							%>
							<%!int totalCancel = 0;%>
						<s:iterator value="cancelList">
							<tr>
								<td class="sortableTD" width="5%" align="center"><%=c%></td>
								<td class="sortableTD" width="15%" nowrap="nowrap"><s:hidden
									name="rejectCode" /> <!--<s:property value="empNum" />
									--><s:property value="authorizedToken" /></td>
								<td class="sortableTD" width="40%"><s:property
									value="fname" /></td>

								<td class="sortableTD" width="20%" align="center"><s:property
									value="depAppDate" /></td>

								<td class="sortableTD" width="20%" align="center"><input
									type="button" name="view_Details" class="token"
									value=" View Application "  onclick="javascript:viewDetails('<s:property value="deptCode"/>')" /></td>
							</tr>
							<%
								c++;
								%>
								<%totalCancel=c; %>
						</s:iterator>

						<%
							if (c == 1) {
							%>
						<tr align="center">
							<td colspan="6" class="sortableTD" width="100%"><font
								color="red">No Data to display</font></td>
						</tr>
						<%
							}
							%>

					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>


	</table>

</s:form>


<script>

function addapplicationFun() {
	
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'DepartmentLocationChange_addNew.action';
		document.getElementById('paraFrm').submit();
	}

function sendforapprovalFun(status)
{ 
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action ='DepartmentLocationChange_draft.action?status ='+status; 		
		document.getElementById('paraFrm').submit();

}

function draftEdit(id)
{ 
		//document.getElementById('paraFrm').target = "_self";
	    document.getElementById('paraFrm_deptCode').value =id;
		document.getElementById('paraFrm').action ='DepartmentLocationChange_retriveDetails.action';		
		document.getElementById('paraFrm').submit();



}



function viewDetails(deptDataId){
//alert(deptDataId);
		      	document.getElementById('paraFrm').action='DepartmentLocationChange_viewDepartmentDetails.action?deptDataId='+deptDataId;
		      	document.getElementById('paraFrm').submit();
		     
		    }
		    
	    function viewPendingDetails(appno){
	   // alert(appno);
	      	document.getElementById('paraFrm').action='DepartmentLocationChange_retrivePendingDetails.action?appno='+appno;
	      	document.getElementById('paraFrm').submit();
	    }
		 //For Approved List  
		 function viewPendingDetails1(appno){
		 //alert(appno);
	      	document.getElementById('paraFrm').action='DepartmentLocationChange_retriveApprovalDetails.action?appno='+appno;
	      	document.getElementById('paraFrm').submit();
	    } 
		function callPending() 
		{
	 		document.getElementById('pending').style.display = '';
			document.getElementById('Draft').style.display = '';
			document.getElementById('sendBack').style.display = '';
			document.getElementById('Approved').style.display = 'none';
			document.getElementById('Reject').style.display = 'none';
			document.getElementById('ApprovedCancel').style.display = 'none';
			document.getElementById('ApprovedReject').style.display = 'none';
			document.getElementById('cancel').style.display = 'none';
			
			//document.getElementById('paraFrm').action ='DepartmentLocationChange_callPendingList.action';		
			//document.getElementById('paraFrm').submit();
		}
		
		function callApprove() 
		{
	 		document.getElementById('pending').style.display = 'none';
			document.getElementById('Draft').style.display = 'none';
			document.getElementById('Reject').style.display = 'none';
			document.getElementById('sendBack').style.display = 'none';
			document.getElementById('ApprovedReject').style.display = 'none';
			document.getElementById('cancel').style.display = 'none';
			document.getElementById('Approved').style.display = '';
			document.getElementById('ApprovedCancel').style.display = 'none';
			
			//document.getElementById('paraFrm').action ='DepartmentLocationChange_callApproveList.action';		
			//document.getElementById('paraFrm').submit();
				
		}
		
		function callCancel() 
		{
	 		document.getElementById('pending').style.display = 'none';
			document.getElementById('Draft').style.display = 'none';
			document.getElementById('Approved').style.display = 'none';
			document.getElementById('Reject').style.display = 'none';
			document.getElementById('ApprovedReject').style.display = 'none';
			document.getElementById('ApprovedCancel').style.display = 'none';
			document.getElementById('sendBack').style.display = 'none';
			document.getElementById('cancel').style.display = '';
			
			//document.getElementById('paraFrm').action ='DepartmentLocationChange_callCancelList.action';		
			//document.getElementById('paraFrm').submit();
		}
		
		function callReject() 
		{
	 		document.getElementById('pending').style.display = 'none';
			document.getElementById('Draft').style.display = 'none';
			document.getElementById('Approved').style.display = 'none';
			document.getElementById('sendBack').style.display = 'none';
			document.getElementById('ApprovedCancel').style.display = 'none';
			document.getElementById('cancel').style.display = 'none';
			document.getElementById('Reject').style.display = '';
			document.getElementById('ApprovedReject').style.display = 'none';
			
			
			//document.getElementById('paraFrm').action ='DepartmentLocationChange_callRejectList.action';		
			//document.getElementById('paraFrm').submit();
		}
		
		
		
function callPageApproved(id, pageImg, totalPageHid, action) {		
		
		try
		{
		pageNo = document.getElementById('pageNoFieldApproved').value;	
		actPage = document.getElementById('myPageApproved').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoFieldApproved').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoFieldApproved').value = actPage;
			    document.getElementById('pageNoFieldApproved').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoFieldApproved').value=actPage;
			    document.getElementById('pageNoFieldApproved').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoFieldApproved').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoFieldApproved').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoFieldApproved').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoFieldApproved').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoFieldApproved').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoFieldApproved').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageApproved').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}
	


function callPageTextApproved(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoFieldApproved').value;
		 	var actPage = document.getElementById('myPageApproved').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoFieldApproved').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoFieldApproved').focus();
		     document.getElementById('pageNoFieldApproved').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoFieldApproved').focus();
		     document.getElementById('pageNoFieldApproved').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoFieldApproved').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageApproved').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	//Return List
	function callPageReturn(id, pageImg, totalPageHid, action) {		
		
		try
		{
		pageNo = document.getElementById('pageNoFieldReturn').value;	
		actPage = document.getElementById('myPageReturn').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoFieldReturn').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoFieldReturn').value = actPage;
			    document.getElementById('pageNoFieldReturn').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoFieldReturn').value=actPage;
			    document.getElementById('pageNoFieldReturn').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoFieldReturn').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoFieldReturn').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoFieldReturn').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoFieldReturn').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoFieldReturn').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoFieldReturn').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageReturn').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}
	


function callPageTextReturn(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoFieldReturn').value;
		 	var actPage = document.getElementById('myPageReturn').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoFieldReturn').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoFieldReturn').focus();
		     document.getElementById('pageNoFieldReturn').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoFieldReturn').focus();
		     document.getElementById('pageNoFieldReturn').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoFieldReturn').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageReturn').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}	



//Approved List
	function callPageApprovedList(id, pageImg, totalPageHid, action) {		
		
		try
		{
		pageNo = document.getElementById('pageNoFieldApprovedList').value;	
		actPage = document.getElementById('myPageApprovedList').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoFieldApprovedList').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoFieldApprovedList').value = actPage;
			    document.getElementById('pageNoFieldApprovedList').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoFieldApprovedList').value=actPage;
			    document.getElementById('pageNoFieldApprovedList').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoFieldApprovedList').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoFieldApprovedList').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoFieldApprovedList').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoFieldApprovedList').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoFieldApprovedList').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoFieldApprovedList').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageApprovedList').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}
	


function callPageTextApprovedList(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoFieldApprovedList').value;
		 	var actPage = document.getElementById('myPageApprovedList').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoFieldApprovedList').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoFieldApprovedList').focus();
		     document.getElementById('pageNoFieldApprovedList').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoFieldApprovedList').focus();
		     document.getElementById('pageNoFieldApprovedList').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoFieldApprovedList').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageApprovedList').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}	



//Approved Cancel List
	function callPageApprovedCancelList(id, pageImg, totalPageHid, action) {		
		
		try
		{
		pageNo = document.getElementById('pageNoFieldApprovedCancelList').value;	
		actPage = document.getElementById('myPageApprovedCancelList').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoFieldApprovedCancelList').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoFieldApprovedCancelList').value = actPage;
			    document.getElementById('pageNoFieldApprovedCancelList').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoFieldApprovedCancelList').value=actPage;
			    document.getElementById('pageNoFieldApprovedCancelList').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoFieldApprovedCancelList').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoFieldApprovedCancelList').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoFieldApprovedCancelList').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoFieldApprovedCancelList').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoFieldApprovedCancelList').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoFieldApprovedCancelList').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageApprovedCancelList').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}
	


function callPageTextApprovedCancelList(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoFieldApprovedCancelList').value;
		 	var actPage = document.getElementById('myPageApprovedCancelList').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoFieldApprovedCancelList').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoFieldApprovedCancelList').focus();
		     document.getElementById('pageNoFieldApprovedCancelList').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoFieldApprovedCancelList').focus();
		     document.getElementById('pageNoFieldApprovedCancelList').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoFieldApprovedCancelList').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageApprovedCancelList').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}	
	
	
	
	//Reject List
	function callPageReject(id, pageImg, totalPageHid, action) {		
		
		try
		{
		pageNo = document.getElementById('pageNoFieldReject').value;	
		actPage = document.getElementById('myPageReject').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoFieldApprovedCancelList').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoFieldReject').value = actPage;
			    document.getElementById('pageNoFieldReject').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoFieldReject').value=actPage;
			    document.getElementById('pageNoFieldReject').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoFieldReject').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoFieldReject').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoFieldReject').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoFieldReject').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoFieldReject').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoFieldReject').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageReject').value = id;
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
			pageNo =document.getElementById('pageNoFieldReject').value;
		 	var actPage = document.getElementById('myPageReject').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoFieldReject').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoFieldReject').focus();
		     document.getElementById('pageNoFieldReject').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoFieldReject').focus();
		     document.getElementById('pageNoFieldReject').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoFieldReject').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageReject').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	
	
	//Reject Cancel List
	function callPageRejectCancel(id, pageImg, totalPageHid, action) {		
		
		try
		{
		pageNo = document.getElementById('pageNoFieldRejectCancel').value;	
		actPage = document.getElementById('myPageRejectCancel').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoFieldRejectCancel').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoFieldRejectCancel').value = actPage;
			    document.getElementById('pageNoFieldRejectCancel').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoFieldRejectCancel').value=actPage;
			    document.getElementById('pageNoFieldRejectCancel').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoFieldRejectCancel').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoFieldRejectCancel').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoFieldRejectCancel').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoFieldRejectCancel').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoFieldRejectCancel').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoFieldRejectCancel').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageRejectCancel').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}
	


function callPageTextRejectCancel(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoFieldRejectCancel').value;
		 	var actPage = document.getElementById('myPageRejectCancel').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoFieldRejectCancel').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoFieldRejectCancel').focus();
		     document.getElementById('pageNoFieldRejectCancel').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoFieldRejectCancel').focus();
		     document.getElementById('pageNoFieldRejectCancel').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoFieldRejectCancel').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageRejectCancel').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	
	
	//Cancel List
	function callPageCancel(id, pageImg, totalPageHid, action) {		
		
		try
		{
		pageNo = document.getElementById('pageNoFieldCancel').value;	
		actPage = document.getElementById('myPageCancel').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoFieldCancel').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoFieldCancel').value = actPage;
			    document.getElementById('pageNoFieldCancel').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoFieldCancel').value=actPage;
			    document.getElementById('pageNoFieldCancel').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoFieldCancel').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoFieldCancel').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoFieldCancel').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoFieldCancel').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoFieldCancel').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoFieldCancel').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageCancel').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}
	


function callPageTextCancel(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoFieldCancel').value;
		 	var actPage = document.getElementById('myPageCancel').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoFieldCancel').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoFieldCancel').focus();
		     document.getElementById('pageNoFieldCancel').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoFieldCancel').focus();
		     document.getElementById('pageNoFieldCancel').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoFieldCancel').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageCancel').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	
	
	
</script>
