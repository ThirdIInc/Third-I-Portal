<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="LeaveApplication" method="post" name="LeaveForm"
	id="paraFrm" theme="simple">

	<s:hidden name="myPageRejected" id="myPageRejected" />
	<s:hidden name="myPageCancelled" id="myPageCancelled" />
	<s:hidden name="myPageCancelRejected" id="myPageCancelRejected" />
	<s:hidden name="myPageCancelApproved" id="myPageCancelApproved" />
	<s:hidden name="myPage" id="myPage" />

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
					<td width="93%" class="txt"><strong class="text_head">Leave
					Application Form </strong></td>
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
					<script>
		    function setAction(listType){
		    
		     if(listType=="p"){
		     
		      	document.getElementById('paraFrm').action='LeaveApplication_input.action';
		      	document.getElementById('paraFrm').submit();
		      
		     }if(listType=="a"){
		     
		      	document.getElementById('paraFrm').action='LeaveApplication_getApprovedList.action';
		      	document.getElementById('paraFrm').submit();
		      
		     }
		     if(listType=="c"){
		     
		      	document.getElementById('paraFrm').action='LeaveApplication_getCancelledList.action';
		      	document.getElementById('paraFrm').submit();
		      
		     }
		     
		     if(listType=="r"){
		        
		      	document.getElementById('paraFrm').action='LeaveApplication_getRejectedList.action';
		      	document.getElementById('paraFrm').submit();
		        
		     } 
		     
		    }
		   </script>
					<td><a href="#" onclick="setAction('p')">Pending
					Application</a> | <a href="#" onclick="setAction('a')">Approved
					List</a> | <a href="#" onclick="setAction('c')"> Cancelled
					Applications</a> | <a href="#" onclick="setAction('r')">Rejected
					List</a></td>
				</tr>
			</table>
			</td>
		</tr>
		<!--------------------------------------  PENDING LIST OF APPLICATIONS [BEGINS]----------------------------->

		<s:if test="%{listType == 'pending'}">

			<!--------- DRAFT SECTION BEGINS - displaying the saved leave applications ------->

			<tr>
				<td>
				<table width="100%" class="formbg">
					<tr>
						<td><b>Draft</b></td>
					</tr>
					<tr>
						<td>
						<table width="100%" class="sorttable">
							<tr>
								<td class="formth" width="5%"><b><label class="set"
									name="sno" id="sno1" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
								</td>
								<td class="formth" width="15%"><b><label class="set"
									name="employee.id" id="employee.id1"
									ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></b>
								</td>
								<td class="formth" width="40%"><b><label class="set"
									name="employee.name" id="employee.name1"
									ondblclick="callShowDiv(this);"><%=label.get("employee.name")%></label></b>
								</td>
								<td class="formth" width="20%"><b><label class="set"
									name="appdate" id="appdate1" ondblclick="callShowDiv(this);"><%=label.get("appdate")%></label></b>
								</td>
								<td class="formth" width="20%"><b>Edit Application</b></td>

							</tr>

							<%
							int i = 1;
							%>
							<s:iterator value="draftList">
								<tr>
									<td class="sortableTD" width="5%" align="center"><%=i%></td>
									<td class="sortableTD" width="15%"><s:hidden
										name="draftEmpId" /><s:hidden name="draftLeaveAppNo" /> <s:hidden
										name="levAppStatus" /> <s:property value="levEmpToken" /></td>
									<td class="sortableTD" width="40%"><s:property
										value="levEmpName" /></td>
									<td class="sortableTD" width="20%" align="center"><s:property
										value="levAppDate" /></td>
									<td class="sortableTD" width="20%" align="center"><input
										type="button" name="view_Details" class="token"
										value=" Edit Application "
										onclick="viewDetails('<s:property value="draftLeaveAppNo"/>','<s:property value="levAppStatus"/>')" /></td>
								</tr>
								<%
								i++;
								%>
							</s:iterator>

							<%
							if (i == 1) {
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

			<!--------- DRAFT SECTION ENDS - displaying the saved leave applications -------->
			<!--------- SUBMIT SECTION BEGINS - displaying the sent leave applications -------->

			<tr>
				<td>
				<table width="100%" class="formbg">
					<tr>
						<td><b>Applications In Process</b></td>
					</tr>
					<tr>
						<td width="100%">
						<table width="100%" class="sorttable">
							<tr>
								<td>
								<table width="100%" class="sorttable">
									<tr>
										<td class="formth" width="5%"><b><label class="set"
											name="sno" id="sno2" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
										</td>
										<td class="formth" width="15%"><b><label class="set"
											name="employee.id" id="employee.id2"
											ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></b>
										</td>
										<td class="formth" width="40%"><b><label class="set"
											name="employee.name" id="employee.name2"
											ondblclick="callShowDiv(this);"><%=label.get("employee.name")%></label></b>
										</td>
										<td class="formth" width="20%"><b><label class="set"
											name="appdate" id="appdate2" ondblclick="callShowDiv(this);"><%=label.get("appdate")%></label></b>
										</td>
										<td class="formth" width="20%"><b>View Application</b></td>

									</tr>

									<%
									int me = 1;
									%>
									<s:iterator value="submitList">
										<tr>
											<td class="sortableTD" width="5%" align="center"><%=me%></td>
											<td class="sortableTD" width="15%"><s:hidden
												name="submitEmpId" /><s:hidden name="submitLeaveAppNo" />
											<s:hidden name="levAppStatus" /> <s:property
												value="levEmpToken" /></td>
											<td class="sortableTD" width="40%"><s:property
												value="levEmpName" /></td>
											<td class="sortableTD" width="20%" align="center"><s:property
												value="levAppDate" /></td>
											<td class="sortableTD" width="20%" align="center"><input
												type="button" name="view_Details" class="token"
												value=" View Application "
												onclick="viewDetails('<s:property value="submitLeaveAppNo"/>','<s:property value="levAppStatus"/>')" /></td>
										</tr>
										<%
										me++;
										%>
									</s:iterator>

									<%
									if (me == 1) {
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
				</table>
				</td>
			</tr>

			<!--------- SUBMIT SECTION ENDS - displaying the sent leave applications -------->

			<!--------- RETURN SECTION BEGINS - displaying the returned leave applications --->

			<tr>
				<td>
				<table width="100%" class="formbg">
					<tr>
						<td><b>Returned Applications (Please view the comments,
						update application and submit again) </b></td>
					</tr>
					<tr>
						<td>
						<table width="100%" class="sorttable" border="0">

							<tr>
								<td>
								<table width="100%" class="sorttable">
									<tr>
										<td class="formth" width="5%"><b><label class="set"
											name="sno" id="sno3" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
										</td>
										<td class="formth" width="15%"><b><label class="set"
											name="employee.id" id="employee.id3"
											ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></b>
										</td>
										<td class="formth" width="40%"><b><label class="set"
											name="employee.name" id="employee.name3"
											ondblclick="callShowDiv(this);"><%=label.get("employee.name")%></label></b>
										</td>
										<td class="formth" width="20%"><b><label class="set"
											name="appdate" id="appdate3" ondblclick="callShowDiv(this);"><%=label.get("appdate")%></label></b>
										</td>
										<td class="formth" width="20%"><b>Edit Application</b></td>

									</tr>

									<%
									int b = 1;
									%>
									<s:iterator value="returnList">
										<tr>
											<td class="sortableTD" width="5%" align="center"><%=b%></td>
											<td class="sortableTD" width="15%"><s:hidden
												name="returnEmpId" /><s:hidden name="returnLeaveAppNo" />
											<s:hidden name="levAppStatus" /> <s:property
												value="levEmpToken" /></td>
											<td class="sortableTD" width="40%"><s:property
												value="levEmpName" /></td>
											<td class="sortableTD" width="20%" align="center"><s:property
												value="levAppDate" /></td>
											<td class="sortableTD" width="20%" align="center"><input
												type="button" name="view_Details" class="token"
												value=" Edit Application "
												onclick="viewDetails('<s:property value="returnLeaveAppNo"/>','<s:property value="levAppStatus"/>')" /></td>
										</tr>
										<%
										b++;
										%>
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

				</table>
				</td>
			</tr>
		</s:if>

		<!--------- RETURN SECTION ENDS - displaying the returned leave applications ----->

		<!-------------------------------------- APPROVED LIST OF APPLICATIONS [BEGINS]----------------------------->

		<s:if test="%{listType == 'approved'}">

			<tr>
				<td>
				<table width="100%" class="formbg">
					<tr>
						<td><b>Approved Applications List</b></td>
					</tr>
					<tr>
						<td>
						<table width="100%">
							<%
								int totalPage = 0;
								int pageNo = 0;
							%>
							<s:if test="approveLength">
								<tr>
									<td id="ctrlShow" width="100%" align="right"><b>Page:</b>
									<%
										totalPage = (Integer) request.getAttribute("totalPage");
										pageNo = (Integer) request.getAttribute("pageNo");
									%> <a href="#"
										onclick="callPage('1', 'F', '<%=totalPage%>', 'LeaveApplication_getApprovedList.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPage('P', 'P', '<%=totalPage%>', 'LeaveApplication_getApprovedList.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text" name="pageNoField"
										id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10"
										onkeypress="callPageText(event, '<%=totalPage%>', 'LeaveApplication_getApprovedList.action');return numbersOnly();" />
									of <%=totalPage%> <a href="#"
										onclick="callPage('N', 'N', '<%=totalPage%>', 'LeaveApplication_getApprovedList.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'LeaveApplication_getApprovedList.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
								</tr>
							</s:if>
							<tr>
								<td>
								<table width="100%" class="sorttable">
									<tr>
										<td class="formth" width="5%"><b><label class="set"
											name="sno" id="sno4" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
										</td>
										<td class="formth" width="15%"><b><label class="set"
											name="employee.id" id="employee.id4"
											ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></b>
										</td>
										<td class="formth" width="40%"><b><label class="set"
											name="employee.name" id="employee.name4"
											ondblclick="callShowDiv(this);"><%=label.get("employee.name")%></label></b>
										</td>
										<td class="formth" width="20%"><b><label class="set"
											name="appdate" id="appdate4" ondblclick="callShowDiv(this);"><%=label.get("appdate")%></label></b>
										</td>
										<td class="formth" width="20%"><b>View Application</b></td>

									</tr>
									<s:if test="approveLength">
										<%
										int cn = pageNo * 20 - 20;
										%>
										<s:iterator value="approvedList">
											<tr>
												<td class="sortableTD" width="5%" align="center"><%=++cn%></td>
												<td class="sortableTD" width="15%"><s:hidden
													name="approvedEmpId" /><s:hidden name="approvedLeaveAppNo" />
												<s:hidden name="levAppStatus" /> <s:property
													value="levEmpToken" /></td>
												<td class="sortableTD" width="40%"><s:property
													value="levEmpName" /></td>
												<td class="sortableTD" width="20%" align="center"><s:property
													value="levAppDate" /></td>
												<td class="sortableTD" width="20%" align="center"><input
													type="button" name="view_Details" class="token"
													value=" View Application "
													onclick="viewDetails('<s:property value="approvedLeaveAppNo"/>','<s:property value="levAppStatus"/>')" /></td>
											</tr>

										</s:iterator>

									</s:if>
									<s:if test="approveLength"></s:if>
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

			<tr>
				<td>
				<table width="100%" class="formbg">
					<tr>
						<td><b>Approved Cancellation Applications List</b></td>
					</tr>
					<tr>
						<td>
						<table width="100%">
							<%
								int totalPageCancelApproved = 0;
								int PageNoCancelApproved = 0;
							%>
							<s:if test="approveCancelLength">
								<tr>
									<td id="ctrlShow" width="100%" align="right"><b>Page:</b>
									<%
												totalPageCancelApproved = (Integer) request
												.getAttribute("totalPageCancelApproved");
										PageNoCancelApproved = (Integer) request
												.getAttribute("PageNoCancelApproved");
									%> <a href="#"
										onclick="callPageApproveCancel('1', 'F', '<%=totalPageCancelApproved%>', 'LeaveApplication_getApprovedList.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPageApproveCancel('P', 'P', '<%=totalPageCancelApproved%>', 'LeaveApplication_getApprovedList.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text" name="pageNoField1"
										id="pageNoField1" size="3" value="<%=PageNoCancelApproved%>"
										maxlength="10"
										onkeypress="callPageTextApproveCancel(event, '<%=totalPageCancelApproved%>', 'LeaveApplication_getApprovedList.action');return numbersOnly();" />
									of <%=totalPageCancelApproved%> <a href="#"
										onclick="callPageApproveCancel('N', 'N', '<%=totalPageCancelApproved%>', 'LeaveApplication_getApprovedList.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPageApproveCancel('<%=totalPageCancelApproved%>', 'L', '<%=totalPageCancelApproved%>', 'LeaveApplication_getApprovedList.action');">
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
													name="sno" id="sno4" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
												</td>
												<td class="formth" width="15%"><b><label
													class="set" name="employee.id" id="employee.id4"
													ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></b>
												</td>
												<td class="formth" width="40%"><b><label
													class="set" name="employee.name" id="employee.name4"
													ondblclick="callShowDiv(this);"><%=label.get("employee.name")%></label></b>
												</td>
												<td class="formth" width="20%"><b><label
													class="set" name="appdate" id="appdate4"
													ondblclick="callShowDiv(this);"><%=label.get("appdate")%></label></b>
												</td>
												<td class="formth" width="20%"><b>View Application</b></td>

											</tr>
											<s:if test="approveCancelLength">
												<%
												int cn1 = PageNoCancelApproved * 20 - 20;
												%>

												<s:iterator value="cancelledApprovedList">
													<tr>
														<td class="sortableTD" width="5%" align="center"><%=++cn1%></td>
														<td class="sortableTD" width="15%"><s:hidden
															name="cancelledApprovedEmpId" /><s:hidden
															name="cancelledApprovedLeaveAppNo" /> <s:hidden
															name="levAppStatus" /> <s:property value="levEmpToken" /></td>
														<td class="sortableTD" width="40%"><s:property
															value="levEmpName" /></td>
														<td class="sortableTD" width="20%" align="center"><s:property
															value="levAppDate" /></td>
														<td class="sortableTD" width="20%" align="center"><input
															type="button" name="view_Details" class="token"
															value=" View Application "
															onclick="viewDetails('<s:property value="cancelledApprovedLeaveAppNo"/>','<s:property value="levAppStatus"/>')" /></td>
													</tr>

												</s:iterator>

											</s:if>
											<s:if test="approveCancelLength"></s:if>
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
				</table>
				</td>
			</tr>
		</s:if>
		<!-------------------------------------- APPROVED LIST OF APPLICATIONS [ENDS] ------------------------------->

		<!-------------------------------------- CANCELLED LIST OF APPLICATIONS [BEGINS]----------------------------->
		<s:if test="%{listType == 'cancelled'}">
			<tr>
				<td>
				<table width="100%" class="formbg">
					<tr>
						<td><b>Cancelled Applications List</b></td>
					</tr>
					<%
						int totalPageCancelled = 0;
						int PageNoCancelled = 0;
					%>
					<s:if test="cancelledLength">
						<tr>
							<td id="ctrlShow" width="100%" align="right"><b>Page:</b> <%
 			totalPageCancelled = (Integer) request
 			.getAttribute("totalPageCancelled");
 	PageNoCancelled = (Integer) request.getAttribute("PageNoCancelled");
 %> <a href="#"
								onclick="callPageCancelled('1', 'F', '<%=totalPageCancelled%>', 'LeaveApplication_getCancelledList.action');">
							<img title="First Page" src="../pages/common/img/first.gif"
								width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPageCancelled('P', 'P', '<%=totalPageCancelled%>', 'LeaveApplication_getCancelledList.action');">
							<img title="Previous Page" src="../pages/common/img/previous.gif"
								width="10" height="10" class="iconImage" /> </a> <input type="text"
								name="pageNoField2" id="pageNoField2" size="3"
								value="<%=PageNoCancelled%>" maxlength="10"
								onkeypress="callPageTextCancel(event, '<%=totalPageCancelled%>', 'LeaveApplication_getApprovedList.action');return numbersOnly();" />
							of <%=totalPageCancelled%> <a href="#"
								onclick="callPageCancelled('N', 'N', '<%=totalPageCancelled%>', 'LeaveApplication_getCancelledList.action')">
							<img title="Next Page" src="../pages/common/img/next.gif"
								class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPageCancelled('<%=totalPageCancelled%>', 'L', '<%=totalPageCancelled%>', 'LeaveApplication_getCancelledList.action');">
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
											name="sno" id="sno5" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
										</td>
										<td class="formth" width="15%"><b><label class="set"
											name="employee.id" id="employee.id5"
											ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></b>
										</td>
										<td class="formth" width="40%"><b><label class="set"
											name="employee.name" id="employee.name5"
											ondblclick="callShowDiv(this);"><%=label.get("employee.name")%></label></b>
										</td>
										<td class="formth" width="20%"><b><label class="set"
											name="appdate" id="appdate5" ondblclick="callShowDiv(this);"><%=label.get("appdate")%></label></b>
										</td>
										<td class="formth" width="20%"><b>View Application</b></td>

									</tr>
									<s:if test="cancelledLength">
										<%
										int cn2 = PageNoCancelled * 20 - 20;
										%>

										<s:iterator value="cancelledList">
											<tr>
												<td class="sortableTD" width="5%" align="center"><%=++cn2%></td>
												<td class="sortableTD" width="15%"><s:hidden
													name="cancelledEmpId" /><s:hidden
													name="cancelledLeaveAppNo" /><s:hidden name="levAppStatus" />
												<s:property value="levEmpToken" /></td>
												<td class="sortableTD" width="40%"><s:property
													value="levEmpName" /></td>
												<td class="sortableTD" width="20%" align="center"><s:property
													value="levAppDate" /></td>
												<td class="sortableTD" width="20%" align="center"><input
													type="button" name="view_Details" class="token"
													value=" View Application "
													onclick="viewDetails('<s:property value="cancelledLeaveAppNo"/>','<s:property value="levAppStatus"/>')" /></td>
											</tr>

										</s:iterator>
									</s:if>

									<s:if test="cancelledLength"></s:if>
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
		<!-------------------------------------- CANCELLED LIST OF APPLICATIONS [ENDS] ------------------------------->


		<!-------------------------------------- REJECTED APPLICATIONS LIST [BEGINS]----------------------------->

		<s:if test="%{listType == 'rejected'}">
			<tr>
				<td>
				<table width="100%" class="formbg">
					<tr>
						<td><b>Rejected Applications List</b></td>
					</tr>
					<%
						int totalPageRejected = 0;
						int PageNoRejected = 0;
					%>
					<s:if test="rejectedLength">
						<tr>
							<td id="ctrlShow" width="100%" align="right"><b>Page:</b> <%
 			totalPageRejected = (Integer) request
 			.getAttribute("totalPageRejected");
 	PageNoRejected = (Integer) request.getAttribute("PageNoRejected");
 %> <a href="#"
								onclick="callPageRejected('1', 'F', '<%=totalPageRejected%>', 'LeaveApplication_getRejectedList.action');">
							<img title="First Page" src="../pages/common/img/first.gif"
								width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPageRejected('P', 'P', '<%=totalPageRejected%>', 'LeaveApplication_getRejectedList.action');">
							<img title="Previous Page" src="../pages/common/img/previous.gif"
								width="10" height="10" class="iconImage" /> </a> <input type="text"
								name="pageNoField3" id="pageNoField3" size="3"
								value="<%=PageNoRejected%>" maxlength="10"
								onkeypress="callPageTextReject(event, '<%=totalPageRejected%>', 'LeaveApplication_getRejectedList.action');return numbersOnly();" />
							of <%=totalPageRejected%> <a href="#"
								onclick="callPageRejected('N', 'N', '<%=totalPageRejected%>', 'LeaveApplication_getRejectedList.action')">
							<img title="Next Page" src="../pages/common/img/next.gif"
								class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPageRejected('<%=totalPageRejected%>', 'L', '<%=totalPageRejected%>', 'LeaveApplication_getRejectedList.action');">
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
											name="sno" id="sno6" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
										</td>
										<td class="formth" width="15%"><b><label class="set"
											name="employee.id" id="employee.id6"
											ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></b>
										</td>
										<td class="formth" width="40%"><b><label class="set"
											name="employee.name" id="employee.name6"
											ondblclick="callShowDiv(this);"><%=label.get("employee.name")%></label></b>
										</td>
										<td class="formth" width="20%"><b><label class="set"
											name="appdate" id="appdate6" ondblclick="callShowDiv(this);"><%=label.get("appdate")%></label></b>
										</td>
										<td class="formth" width="20%"><b>View Application</b></td>

									</tr>
									<s:if test="rejectedLength">
										<%
										int cn3 = PageNoRejected * 20 - 20;
										%>

										<s:iterator value="rejectedList">
											<tr>
												<td class="sortableTD" width="5%" align="center"><%=++cn3%></td>
												<td class="sortableTD" width="15%"><s:hidden
													name="rejectedEmpId" /><s:hidden name="rejectedLeaveAppNo" />
												<s:hidden name="levAppStatus" /> <s:property
													value="levEmpToken" /></td>
												<td class="sortableTD" width="40%"><s:property
													value="levEmpName" /></td>
												<td class="sortableTD" width="20%" align="center"><s:property
													value="levAppDate" /></td>
												<td class="sortableTD" width="20%" align="center"><input
													type="button" name="view_Details" class="token"
													value=" View Application "
													onclick="viewDetails('<s:property value="rejectedLeaveAppNo"/>','<s:property value="levAppStatus"/>')" /></td>
											</tr>

										</s:iterator>
									</s:if>

									<s:if test="rejectedLength"></s:if>
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
			<tr>
				<td>
				<table width="100%" class="formbg">
					<tr>
						<td><b>Rejected Cancellation Applications List</b></td>
					</tr>
					<tr>
						<td>
						<table width="100%">

							<!-- PAGING IMPLEMENTATION STARTS -->
							<%
								int totalPageCancelRejected = 0;
								int PageNoCancelRejected = 0;
							%>
							<s:if test="rejectedCancelLength">
								<tr>
									<td id="ctrlShow" width="100%" align="right"><b>Page:</b>
									<%
												totalPageCancelRejected = (Integer) request
												.getAttribute("totalPageCancelRejected");
										PageNoCancelRejected = (Integer) request
												.getAttribute("PageNoCancelRejected");
									%> <a href="#"
										onclick="callPageRejectedCancel('1', 'F', '<%=totalPageCancelRejected%>', 'LeaveApplication_getRejectedList.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPageRejectedCancel('P', 'P', '<%=totalPageCancelRejected%>', 'LeaveApplication_getRejectedList.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text" name="pageNoField4"
										id="pageNoField4" size="3" value="<%=PageNoCancelRejected%>"
										maxlength="10"
										onkeypress="callPageTextRejectCancel(event, '<%=totalPageCancelRejected%>', 'LeaveApplication_getRejectedList.action');return numbersOnly();" />
									of <%=totalPageCancelRejected%> <a href="#"
										onclick="callPageRejectedCancel('N', 'N', '<%=totalPageCancelRejected%>', 'LeaveApplication_getRejectedList.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPageRejectedCancel('<%=totalPageCancelRejected%>', 'L', '<%=totalPageCancelRejected%>', 'LeaveApplication_getRejectedList.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
								</tr>
							</s:if>

							<!-- PAGING IMPLEMENTATION ENDS -->


							<tr>
								<td>
								<table width="100%" class="sorttable">
									<tr>
										<td class="formth" width="5%"><b><label class="set"
											name="sno" id="sno6" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
										</td>
										<td class="formth" width="15%"><b><label class="set"
											name="employee.id" id="employee.id6"
											ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></b>
										</td>
										<td class="formth" width="40%"><b><label class="set"
											name="employee.name" id="employee.name6"
											ondblclick="callShowDiv(this);"><%=label.get("employee.name")%></label></b>
										</td>
										<td class="formth" width="20%"><b><label class="set"
											name="appdate" id="appdate6" ondblclick="callShowDiv(this);"><%=label.get("appdate")%></label></b>
										</td>
										<td class="formth" width="20%"><b>View Application</b></td>

									</tr>
									<s:if test="rejectedCancelLength">
										<%
										int cn4 = PageNoCancelRejected * 20 - 20;
										%>

										<s:iterator value="approveCancelrejectList">
											<tr>
												<td class="sortableTD" width="5%" align="center"><%=++cn4%></td>
												<td class="sortableTD" width="15%"><s:hidden
													name="appCanrejectedEmpId" /><s:hidden
													name="appCanrejectedLeaveAppNo" /> <s:hidden
													name="levAppStatus" /> <s:property value="levEmpToken" /></td>
												<td class="sortableTD" width="40%"><s:property
													value="levEmpName" /></td>
												<td class="sortableTD" width="20%" align="center"><s:property
													value="levAppDate" /></td>
												<td class="sortableTD" width="20%" align="center"><input
													type="button" name="view_Details" class="token"
													value=" View Application "
													onclick="viewDetails('<s:property value="appCanrejectedLeaveAppNo"/>','<s:property value="levAppStatus"/>')" /></td>
											</tr>

										</s:iterator>

									</s:if>
									<s:if test="rejectedCancelLength"></s:if>
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
		<tr>
			<td width="100%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>

		<!-- <tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>

					<td width="100%"><s:submit value="  Add Application"
						action="LeaveApplication_addNew" cssClass="add" /></td>

				</tr>
			</table>
			</td>

		</tr>-->
	</table>

</s:form>


<script>


	function addapplicationFun() {
	
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'LeaveApplication_addNew.action?levapp_random='+Math.random();
		document.getElementById('paraFrm').submit();
	}
function addhrsapplicationFun() {
	
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'LeaveApplication_addNew.action?flag=flag';
		document.getElementById('paraFrm').submit();
	}
	

 function viewDetails(leaveAppNo,status){
		    
		     
		   // alert("leaveAppNo"+leaveAppNo);
		   // alert("status"+status);
		     
		     
		      	document.getElementById('paraFrm').action='LeaveApplication_retriveDetails.action?levApplicationCode='+leaveAppNo+'&levStatus='+status;
		      	document.getElementById('paraFrm').submit();
		     
		     
		    }
 
	// APPROVED CANCELLATION APPLICATIONS LIST
function callPageApproveCancel(id, pageImg, totalPageHid, action) {		
		
		pageNo = document.getElementById('pageNoField1').value;	
		actPage = document.getElementById('myPageCancelApproved').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoField1').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoField1').value = actPage;
			    document.getElementById('pageNoField1').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoField1').value=actPage;
			    document.getElementById('pageNoField1').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoField1').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoField1').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoField1').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoField1').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoField1').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoField1').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageCancelApproved').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 
	}
	

	 
function callPageTextApproveCancel(id, totalPage, action){   
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoField1').value;
		 	var actPage = document.getElementById('myPageCancelApproved').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoField1').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoField1').focus();
		     document.getElementById('pageNoField1').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField1').focus();
		     document.getElementById('pageNoField1').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoField1').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageCancelApproved').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		
	}

	// FOR CANCELLED APPLICATIONS LIST
	
	function callPageCancelled(id, pageImg, totalPageHid, action) {		
		
		try
		{
		pageNo = document.getElementById('pageNoField2').value;	
		actPage = document.getElementById('myPageCancelled').value; 
		
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
		document.getElementById('myPageCancelled').value = id;
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
			pageNo =document.getElementById('pageNoField2').value;
		 	var actPage = document.getElementById('myPageCancelled').value;   
	     
		 	 
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
	        
	         document.getElementById('myPageCancelled').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	

		
		// REJECTED APPLICATIONS LIST
	
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
	




// Rejected Cancellation Applications List
	
	function callPageRejectedCancel(id, pageImg, totalPageHid, action) {		
		
		try
		{
		pageNo = document.getElementById('pageNoField4').value;	
		actPage = document.getElementById('myPageCancelRejected').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoField4').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoField4').value = actPage;
			    document.getElementById('pageNoField4').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoField4').value=actPage;
			    document.getElementById('pageNoField4').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoField4').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoField4').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoField4').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoField4').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoField4').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoField4').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageCancelRejected').value = id;
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
			pageNo =document.getElementById('pageNoField4').value;
		 	var actPage = document.getElementById('myPageCancelRejected').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoField4').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoField4').focus();
		     document.getElementById('pageNoField4').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField4').focus();
		     document.getElementById('pageNoField4').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoField4').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageCancelRejected').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	





</script>
