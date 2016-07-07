<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="CBTForm" id="paraFrm" validate="true" target="main"
	theme="simple">

	<s:hidden name="cbtID" />
	<s:hidden name="myPage" id="myPage" />
	<s:hidden name="myPageInProcess" id="myPageInProcess" />
	<s:hidden name="myPageSentBack" id="myPageSentBack" />

	<s:hidden name="myPageApproved" id="myPageApproved" />
	<s:hidden name="myPageApprovedCancel" id="myPageApprovedCancel" />

	<s:hidden name="myPageCancel" id="myPageCancel" />

	<s:hidden name="myPageRejected" id="myPageRejected" />
	<s:hidden name="myPageCancelRejected" id="myPageCancelRejected" />
	<s:hidden name="listType" />

	<table width="100%" class="formbg" align="right">
		<tr>
			<td>
			<table width="100%" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="92%" class="txt"><strong class="text_head">CBT/Self Study Enrollment Form</strong></td>
					<td width="4%" valign="middle" align="right" class="txt"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" />
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="70%">
						<input type="button" class="add"  onclick="addNewFunction();" theme="simple"  value="Add Application" />
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table class="" width="100%">
				<tr>
					<td><a href="#" onclick="setAction('p')">Pending
					Application</a> | <a href="#" onclick="setAction('a')">Approved
					Application</a> | <a href="#" onclick="setAction('r')">Rejected
					Application</a></td>
				</tr>
			</table>
			</td>
		</tr>

<!-- ========= Pending (Drafted and Application in Process) application List Begins ============= -->
		<s:if test="%{listType == 'pending'}">
			<!-- Drafted application List Begins -->
			<tr>
				<td>
				<table class="formbg" width="100%" cellspacing="1" cellspadding="0"
					border="0">
					<tr>
						<td width="30%" align="left"><strong class="text_head">
						Draft </strong></td>
						<td width="70%" align="right">
						<%
							int totalDraftPage = 0;
							int draftPageNo = 0;
						%> <s:if test="draftListLength">
							<table>
								<tr>
									<td id="ctrlShow" width="100%" align="right"><b>Page:</b>
									<%
									totalDraftPage = (Integer) request.getAttribute("totalDraftPage");
									draftPageNo = (Integer) request.getAttribute("draftPageNo");
									%> <a href="#"
										onclick="callPage('1', 'F', '<%=totalDraftPage%>', 'CBTForm_input.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPage('P', 'P', '<%=totalDraftPage%>', 'CBTForm_input.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text" name="pageNoField"
										id="pageNoField" size="3" value="<%=draftPageNo%>"
										maxlength="10"
										onkeypress="callPageText(event, '<%=totalDraftPage%>', 'CBTForm_input.action');return numbersOnly();" />
									of <%=totalDraftPage%> <a href="#"
										onclick="callPage('N', 'N', '<%=totalDraftPage%>', 'CBTForm_input.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPage('<%=totalDraftPage%>', 'L', '<%=totalDraftPage%>', 'CBTForm_input.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
								</tr>
							</table>
						</s:if></td>
					</tr>


					<tr>
						<td colspan="2">
						<table width="100%" border="0" class="formbg">
							<tr>
								<td class="formtext">
								<table width="100%" border="0">
									<tr>
										<td width="10%" class="formth"><label
											class="set" id="sr.no" name="sr.no"
											ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
										<td class="formth" align="center" width="30%">
											<label class="set" name="trackingNumber" id="trackingNumber" ondblclick="callShowDiv(this);"> <%=label.get("trackingNumber")%></label>
										</td>
										<td width="20%" class="formth"><label
											class="set" id="emp.name" name="emp.name"
											ondblclick="callShowDiv(this);"><%=label.get("emp.name")%></label></td>
										<td width="20%" class="formth"><label
											class="set" id="application.Date" name="application.Date"
											ondblclick="callShowDiv(this);"><%=label.get("application.Date")%></label></td>

										<td width="20%" class="formth">
											<label class="set" id="details" name="details"
											ondblclick="callShowDiv(this);"><%=label.get("details")%></label>
										</td>

									</tr>

									<s:if test="draftListLength">
										<%
										int count = 0;
									%>
										<%!int d = 0;%>
										<%
										int i = draftPageNo * 20 - 20;									
									%>

										<s:iterator value="draftList">
											<tr>
												<s:hidden name="cbtHiddenID" />
												<s:hidden name="hiddenStatus" />		
												<td align="center" class="sortableTD"><%=++i%></td>

												<td class="sortableTD" align="left">&nbsp;
													<s:property	value="trackingNumberItr" />
												</td>

												<td class="sortableTD" align="left">&nbsp;
													<s:property value="empNameItr" />
												</td>

												<td class="sortableTD" align="center">&nbsp;
													<s:property	value="applicationDateItr" />
												</td>

												<td class="sortableTD" align="center">&nbsp;
													<input type="button" value=" Edit Applications " class="edit" align="middle"
													onclick="javascript:viewDetails('<s:property value="cbtHiddenID"/>','<s:property value="hiddenStatus"/>')">
												</td>
											</tr>
										</s:iterator>
										<%
											d = i;
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
				</table>
				</td>
			</tr>
			<!-- Drafted application List Ends -->


			<!-- Application in-process List Begins -->
			<tr>
				<td>
				<table class="formbg" width="100%" cellspacing="1" cellspadding="0"
					border="0">
					<tr>
						<td><strong class="text_head"> Application In
						Process </strong></td>
						<td width="70%" align="right">
						<%
							int totalInProcessPage = 0;
							int inProcessPageNo = 0;
						%> <s:if test="inProcessListLength">
							<table>
								<tr>
									<td id="ctrlShow" width="100%" align="right"><b>Page:</b>
									<%
									try{
									totalInProcessPage = (Integer) request.getAttribute("totalInProcessPage");
									inProcessPageNo = (Integer) request.getAttribute("inProcessPageNo");
									}catch(Exception e){
										e.printStackTrace();
										
									}
									%> <a href="#"
										onclick="callPageInProcess('1', 'F', '<%=totalInProcessPage%>', 'CBTForm_input.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPageInProcess('P', 'P', '<%=totalInProcessPage%>', 'CBTForm_input.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text"
										name="pageNoInProcessField" id="pageNoInProcessField" size="3"
										value="<%=inProcessPageNo%>" maxlength="10"
										onkeypress="callPageInProcessText(event, '<%=totalInProcessPage%>', 'CBTForm_input.action');return numbersOnly();" />
									of <%=totalInProcessPage%> <a href="#"
										onclick="callPageInProcess('N', 'N', '<%=totalInProcessPage%>', 'CBTForm_input.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPageInProcess('<%=totalInProcessPage%>', 'L', '<%=totalInProcessPage%>', 'CBTForm_input.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
								</tr>
							</table>
						</s:if></td>
					</tr>


					<tr>
						<td colspan="2">
						<table width="100%" border="0" class="formbg">
							<tr>
								<td class="formtext">
								<table width="100%" border="0">
									<tr>
										<td width="10%" colspan="1" class="formth"><label
											class="set" id="sr.no" name="sr.no"
											ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
										<td class="formth" align="center" width="30%">
											<label class="set" name="trackingNumber" id="trackingNumber" ondblclick="callShowDiv(this);"> <%=label.get("trackingNumber")%></label>
										</td>
										<td width="20%" colspan="1" class="formth"><label
											class="set" id="emp.name" name="emp.name"
											ondblclick="callShowDiv(this);"><%=label.get("emp.name")%></label></td>
										<td width="20%" colspan="1" class="formth"><label
											class="set" id="application.Date" name="application.Date"
											ondblclick="callShowDiv(this);"><%=label.get("application.Date")%></label></td>

										<td width="20%" class="formth">
											<label class="set" id="details" name="details"
											ondblclick="callShowDiv(this);"><%=label.get("details")%></label>
										</td>

									</tr>
									<s:if test="inProcessListLength">
										<%
											int count1 = 0;
										%>
										<%!int d1 = 0;%>
										<%
											int i1 = 0;
										%>

										<s:iterator value="inProcessApplicationList">
											<tr>
												<s:hidden name="cbtHiddenID" />
												<s:hidden name="hiddenStatus" />
												<td align="center" class="sortableTD"><%=++i1%></td>

												<td class="sortableTD" align="left">&nbsp;
													<s:property value="trackingNumberItr" />
												</td>

												<td class="sortableTD" align="left">&nbsp;
													<s:property value="empNameItr" />
												</td>

												<td class="sortableTD" align="center">&nbsp;
													<s:property	value="applicationDateItr" />
												</td>

												<td class="sortableTD" align="center">&nbsp;
													<input type="button" id="ctrlShow" value=" View application " class="token" align="middle"
													onclick="javascript:viewDetails('<s:property value="cbtHiddenID"/>','<s:property value="hiddenStatus"/>')">
												</td>
											</tr>
										</s:iterator>

										<%
											d1 = i1;
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
				</table>
				</td>
			</tr>
			<!-- Application in-process List Begins -->

			<!-- Sent Back Applications List Begins -->
			<tr>
				<td>
				<table class="formbg" width="100%" cellspacing="1" cellspadding="0"
					border="0">
					<tr>
						<td><strong class="text_head"> Sent Back Application
						</strong></td>
						<td width="70%" align="right">
						<%
							int totalSentBackPage = 0;
							int sentBackPageNo = 0;
						%> <s:if test="sendBackLength">
							<table>
								<tr>
									<td id="ctrlShow" width="100%" align="right"><b>Page:</b>
									<%
									totalSentBackPage = (Integer) request.getAttribute("totalSentBackPage");
									sentBackPageNo = (Integer) request.getAttribute("sentBackPageNo");
									%> <a href="#"
										onclick="callPageSentBack('1', 'F', '<%=totalSentBackPage%>', 'CBTForm_input.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPageSentBack('P', 'P', '<%=totalSentBackPage%>', 'CBTForm_input.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text"
										name="pageNoSentBackField" id="pageNoSentBackField" size="3"
										value="<%=sentBackPageNo%>" maxlength="10"
										onkeypress="callPageSentBackText(event, '<%=totalSentBackPage%>', 'CBTForm_input.action');return numbersOnly();" />
									of <%=totalSentBackPage%> <a href="#"
										onclick="callPageSentBack('N', 'N', '<%=totalSentBackPage%>', 'CBTForm_input.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPageSentBack('<%=totalInProcessPage%>', 'L', '<%=totalSentBackPage%>', 'CBTForm_input.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
								</tr>
							</table>
						</s:if></td>
					</tr>


					<tr>
						<td colspan="2">
						<table width="100%" border="0" class="formbg">
							<tr>
								<td class="formtext">
								<table width="100%" border="0">
									<tr>
										<td width="10%" colspan="1" class="formth"><label
											class="set" id="sr.no" name="sr.no"
											ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
										<td class="formth" align="center" width="30%">
											<label class="set" name="trackingNumber" id="trackingNumber" ondblclick="callShowDiv(this);"> <%=label.get("trackingNumber")%></label>
										</td>
										<td width="20%" colspan="1" class="formth"><label
											class="set" id="emp.name" name="emp.name"
											ondblclick="callShowDiv(this);"><%=label.get("emp.name")%></label></td>
										<td width="20%" colspan="1" class="formth"><label
											class="set" id="application.Date" name="application.Date"
											ondblclick="callShowDiv(this);"><%=label.get("application.Date")%></label></td>

										<td width="20%" class="formth">
											<label class="set" id="details" name="details"
											ondblclick="callShowDiv(this);"><%=label.get("details")%></label>
										</td>

									</tr>

									<s:if test="sendBackLength">
										<%
										int count7 = 0;
									%>
										<%!int d7 = 0;%>
										<%
										int i7 = sentBackPageNo * 20 - 20;										
									%>

										<s:iterator value="sendBackList">
											<tr>
												<s:hidden name="cbtHiddenID" />
												<s:hidden name="hiddenStatus" />
												<td align="center" class="sortableTD"><%=++i7%></td>

												<td class="sortableTD" align="left">&nbsp;
													<s:property	value="trackingNumberItr" />
												</td>

												<td class="sortableTD" align="left">&nbsp;
													<s:property	value="empNameItr" />
												</td>

												<td class="sortableTD" align="center">&nbsp;
													<s:property	value="applicationDateItr" />
												</td>

												<td class="sortableTD" align="center">&nbsp;
													<input type="button" id="ctrlShow" value=" Edit Application " class="edit" align="middle"
													onclick="javascript:viewDetails('<s:property value="cbtHiddenID"/>','<s:property value="hiddenStatus"/>')">
												</td>
											</tr>
										</s:iterator>

										<%
											d7 = i7;
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
				</table>
				</td>
			</tr>
			<!-- Sent Back Applications List Ends -->
		</s:if>
<!-- ========= Pending (Drafted, Application in-Process and Sent Back) application List Ends ============= -->
		
<!-- ========= Approved and approved cancellation  application List Begins ============= -->
		<s:if test="%{listType == 'approved'}">
			<!--  Approved application List Begins  -->
			<tr>
				<td>
				<table class="formbg" width="100%" cellspacing="1" cellspadding="0"
					border="0">
					<tr>
						<td><strong class="text_head"> Approved Applications
						</strong></td>
						<td width="70%" align="right">
						<%
							int totalApprovedPage = 0;
							int approvedPageNo = 0;
						%> <s:if test="approvedListLength">
							<table>
								<tr>
									<td id="ctrlShow" width="100%" align="right"><b>Page:</b>
									<%
									totalApprovedPage = (Integer) request.getAttribute("totalApprovedPage");
									approvedPageNo = (Integer) request.getAttribute("approvedPageNo");
									%> <a href="#"
										onclick="callPageApproved('1', 'F', '<%=totalApprovedPage%>', 'CBTForm_getApprovedList.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPageApproved('P', 'P', '<%=totalApprovedPage%>', 'CBTForm_getApprovedList.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text"
										name="pageNoApprovedField" id="pageNoApprovedField" size="3"
										value="<%=approvedPageNo%>" maxlength="10"
										onkeypress="callPageApprovedText(event, '<%=totalApprovedPage%>', 'CBTForm_getApprovedList.action');return numbersOnly();" />
									of <%=totalApprovedPage%> <a href="#"
										onclick="callPageApproved('N', 'N', '<%=totalApprovedPage%>', 'CBTForm_getApprovedList.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPageApproved('<%=totalApprovedPage%>', 'L', '<%=totalApprovedPage%>', 'CBTForm_getApprovedList.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
								</tr>
							</table>
						</s:if></td>
					</tr>


					<tr>
						<td colspan="2">
						<table width="100%" border="0" class="formbg">
							<tr>
								<td class="formtext">
								<table width="100%" border="0">
									<tr>
										<td width="10%" colspan="1" class="formth"><label
											class="set" id="sr.no" name="sr.no"
											ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
										<td class="formth" align="center" width="30%">
											<label class="set" name="trackingNumber" id="trackingNumber" ondblclick="callShowDiv(this);"> <%=label.get("trackingNumber")%></label>
										</td>
										<td width="20%" colspan="1" class="formth"><label
											class="set" id="emp.name" name="emp.name"
											ondblclick="callShowDiv(this);"><%=label.get("emp.name")%></label></td>
										<td width="20%" colspan="1" class="formth"><label
											class="set" id="application.Date" name="application.Date"
											ondblclick="callShowDiv(this);"><%=label.get("application.Date")%></label></td>

										<td width="20%" class="formth">
											<label class="set" id="details" name="details"
											ondblclick="callShowDiv(this);"><%=label.get("details")%></label>
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

										<s:iterator value="approvedList">
											<tr>
												<s:hidden name="cbtHiddenID" />
												<s:hidden name="hiddenStatus" />
												<td align="center" class="sortableTD"><%=++i2%></td>

												<td class="sortableTD" align="left">&nbsp;
													<s:property value="trackingNumberItr" />
												</td>

												<td class="sortableTD" align="left">&nbsp;
													<s:property	value="empNameItr" />
												</td>

												<td class="sortableTD" align="center">&nbsp;
													<s:property	value="applicationDateItr" />
												</td>

												<td class="sortableTD" align="center">&nbsp;
													<input type="button" id="ctrlShow" value=" View Application " class="token"
														align="middle" onclick="javascript:viewDetails('<s:property value="cbtHiddenID"/>','<s:property value="hiddenStatus"/>')">
												</td>
											</tr>
										</s:iterator>

										<%
										d2 = i2;
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
				</table>
				</td>
			</tr>
			<!--  Approved application List Ends  -->
		</s:if>
<!-- ========= Approved and approved cancellation  application List Ends ============= -->


<!-- ========= Approved and approved cancellation  application List Begins ============= -->
		<s:if test="%{listType == 'reject'}">
			<!--  Rejected application List Begins  -->
			<tr>
				<td>
				<table class="formbg" width="100%" cellspacing="1" cellspadding="0"
					border="0">
					<tr>
						<td><strong class="text_head"> Rejected Applications
						</strong></td>
						<td width="70%" align="right">
						<%
							int totalRejectedPage = 0;
							int rejectedPageNo = 0;
						%> <s:if test="rejectListLength">
							<table>
								<tr>
									<td id="ctrlShow" width="100%" align="right"><b>Page:</b>
									<%
									totalRejectedPage = (Integer) request.getAttribute("totalRejectedPage");
									rejectedPageNo = (Integer) request.getAttribute("rejectedPageNo");
									%> <a href="#"
										onclick="callPageRejected('1', 'F', '<%=totalRejectedPage%>', 'CBTForm_getRejectedList.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPageRejected('P', 'P', '<%=totalRejectedPage%>', 'CBTForm_getRejectedList.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text"
										name="pageNoRejectedField" id="pageNoRejectedField" size="3"
										value="<%=rejectedPageNo%>" maxlength="10"
										onkeypress="callPageRejectedText(event, '<%=totalRejectedPage%>', 'CBTForm_getRejectedList.action');return numbersOnly();" />
									of <%=totalRejectedPage%> <a href="#"
										onclick="callPageRejected('N', 'N', '<%=totalRejectedPage%>', 'CBTForm_getRejectedList.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPageRejected('<%=totalRejectedPage%>', 'L', '<%=totalRejectedPage%>', 'CBTForm_getRejectedList.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
								</tr>
							</table>
						</s:if></td>
					</tr>


					<tr>
						<td colspan="2">
						<table width="100%" border="0" class="formbg">
							<tr>
								<td class="formtext">
								<table width="100%" border="0">
									<tr>
										<td width="10%" colspan="1" class="formth"><label
											class="set" id="sr.no" name="sr.no"
											ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
										<td class="formth" align="center" width="30%">
											<label class="set" name="trackingNumber" id="trackingNumber" ondblclick="callShowDiv(this);"> <%=label.get("trackingNumber")%></label>
										</td>
										<td width="20%" colspan="1" class="formth"><label
											class="set" id="emp.name" name="emp.name"
											ondblclick="callShowDiv(this);"><%=label.get("emp.name")%></label></td>
										<td width="20%" colspan="1" class="formth"><label
											class="set" id="application.Date" name="application.Date"
											ondblclick="callShowDiv(this);"><%=label.get("application.Date")%></label></td>

										<td width="20%" class="formth">
											<label class="set" id="details" name="details"
											ondblclick="callShowDiv(this);"><%=label.get("details")%></label>
										</td>

									</tr>

									<s:if test="rejectListLength">
										<%
										int count5 = 0;
									%>
										<%!int d5 = 0;%>
										<%
										int i5 = rejectedPageNo * 20 - 20;									
									%>

										<s:iterator value="rejectList">
											<tr>
												<s:hidden name="cbtHiddenID" />
												<s:hidden name="hiddenStatus" />
												<td align="center" class="sortableTD"><%=++i5%></td>

												<td class="sortableTD" align="left">&nbsp;
													<s:property value="trackingNumberItr" />
												</td>

												<td class="sortableTD" align="left">&nbsp;
													<s:property	value="empNameItr" />
												</td>

												<td class="sortableTD" align="center">&nbsp;
													<s:property	value="applicationDateItr" />
												</td>

												<td class="sortableTD" align="center">&nbsp;
													<input type="button" id="ctrlShow" value=" View Application " class="token"
													align="middle" onclick="javascript:viewDetails('<s:property value="cbtHiddenID"/>','<s:property value="hiddenStatus"/>')">
												</td>
											</tr>
										</s:iterator>

										<%
										d5 = i5;
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
				</table>
				</td>
			</tr>
			<!--  Rejected Application List Ends  -->
		</s:if>
<!-- ========= Approved and approved cancellation  application List Ends ============= -->

		<tr>
			<td width="100%">
			<table border="0" width="100%">
				<td width="30%">
					<input type="button" class="add" value="Add Application" theme="simple" onclick="addNewFunction();"  />
				</td>
			</table>
			</td>
		</tr>
	</table>
</s:form>

<script>
	function addNewFunction()
     {  
        document.getElementById('paraFrm').target = "_self";
        document.getElementById('paraFrm').action = "CBTForm_addNew.action";
		document.getElementById('paraFrm').submit();
     }
	
	function viewDetails(CBTHiddenID,hiddenStatus)
	{
	  document.getElementById('paraFrm').target = "_self";
   	  document.getElementById('paraFrm').action="CBTForm_viewApplicationFunction.action?CBTHiddenID="+CBTHiddenID+"&hiddenStatus="+hiddenStatus;
	  document.getElementById('paraFrm').submit();
	}	
	
	// Application In-Process List Begins
	function callPageInProcess(id, pageImg, totalPageHid, action) {		
		try
		{
		pageNo = document.getElementById('pageNoInProcessField').value;	
		actPage = document.getElementById('myPageInProcess').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoInProcessField').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoInProcessField').value = actPage;
			    document.getElementById('pageNoInProcessField').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoInProcessField').value=actPage;
			    document.getElementById('pageNoInProcessField').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoInProcessField').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoInProcessField').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoInProcessField').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoInProcessField').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoInProcessField').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoInProcessField').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageInProcess').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}

function callPageInProcessText(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoInProcessField').value;
		 	var actPage = document.getElementById('myPageInProcess').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoInProcessField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoInProcessField').focus();
		     document.getElementById('pageNoInProcessField').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoInProcessField').focus();
		     document.getElementById('pageNoInProcessField').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoInProcessField').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageInProcess').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	// Application In-Process List Ends

	// Function for Sent-Back List Begins
	function callPageSentBack(id, pageImg, totalPageHid, action) {		
		try
		{
		pageNo = document.getElementById('pageNoSentBackField').value;	
		actPage = document.getElementById('myPageSentBack').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoSentBackField').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoSentBackField').value = actPage;
			    document.getElementById('pageNoSentBackField').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoSentBackField').value=actPage;
			    document.getElementById('pageNoSentBackField').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoSentBackField').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoSentBackField').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoSentBackField').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoSentBackField').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoSentBackField').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoSentBackField').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageSentBack').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}


	function callPageSentBackText(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoSentBackField').value;
		 	var actPage = document.getElementById('myPageSentBack').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoSentBackField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoSentBackField').focus();
		     document.getElementById('pageNoSentBackField').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoSentBackField').focus();
		     document.getElementById('pageNoSentBackField').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoSentBackField').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageSentBack').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	// Function for Sent-Back List Ends
	
	
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
	
	
	// Function for Approved Cancellation List Begins
	function callPageApprovedCancellation(id, pageImg, totalPageHid, action) {		
		try
		{
		pageNo = document.getElementById('pageNoApprovedCancellationField').value;	
		actPage = document.getElementById('myPageApprovedCancel').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoApprovedCancellationField').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoApprovedCancellationField').value = actPage;
			    document.getElementById('pageNoApprovedCancellationField').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoApprovedCancellationField').value=actPage;
			    document.getElementById('pageNoApprovedCancellationField').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoApprovedCancellationField').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoApprovedCancellationField').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoApprovedCancellationField').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoApprovedCancellationField').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoApprovedCancellationField').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoApprovedCancellationField').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageApprovedCancel').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}


	function callPageApprovedCancellationText(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoApprovedCancellationField').value;
		 	var actPage = document.getElementById('myPageApprovedCancel').value;   
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoApprovedCancellationField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoApprovedCancellationField').focus();
		     document.getElementById('pageNoApprovedCancellationField').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoApprovedCancellationField').focus();
		     document.getElementById('pageNoApprovedCancellationField').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoApprovedCancellationField').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageApprovedCancel').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	// Function Approved Cancellation List Ends
	
	
	
	// Function for Cancel List Begins
	function callPageCancel(id, pageImg, totalPageHid, action) {		
		try
		{
		pageNo = document.getElementById('pageNoCancelField').value;	
		actPage = document.getElementById('myPageCancel').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoCancelField').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoCancelField').value = actPage;
			    document.getElementById('pageNoCancelField').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoCancelField').value=actPage;
			    document.getElementById('pageNoCancelField').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoCancelField').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoCancelField').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoCancelField').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoCancelField').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoCancelField').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoCancelField').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageCancel').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}


	function callPageCancelText(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoCancelField').value;
		 	var actPage = document.getElementById('myPageCancel').value;   
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoCancelField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoCancelField').focus();
		     document.getElementById('pageNoCancelField').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoCancelField').focus();
		     document.getElementById('pageNoCancelField').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoCancelField').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageCancel').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	// Function Cancel List Ends
	
	
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
	
	
	// Function for Rejected Cancellation List Begins
	function callPageRejectedCancellation(id, pageImg, totalPageHid, action) {		
		try
		{
		pageNo = document.getElementById('pageNoRejectedCancellationField').value;	
		actPage = document.getElementById('myPageCancelRejected').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoRejectedCancellationField').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoRejectedCancellationField').value = actPage;
			    document.getElementById('pageNoRejectedCancellationField').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoRejectedCancellationField').value=actPage;
			    document.getElementById('pageNoRejectedCancellationField').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoRejectedCancellationField').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoRejectedCancellationField').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoRejectedCancellationField').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoRejectedCancellationField').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoRejectedCancellationField').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoRejectedCancellationField').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageCancelRejected').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}


	function callPageRejectedCancellationText(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoRejectedCancellationField').value;
		 	var actPage = document.getElementById('myPageCancelRejected').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoRejectedCancellationField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoRejectedCancellationField').focus();
		     document.getElementById('pageNoRejectedCancellationField').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoRejectedCancellationField').focus();
		     document.getElementById('pageNoRejectedCancellationField').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoRejectedCancellationField').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageCancelRejected').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	// Function Rejected Cancellation List Ends
 function setAction(listType){
		    //  alert(listType);
		    //listType
		     if(listType=="p"){
		    // document.getElementById('paraFrm_listType').value='pending';
		      	document.getElementById('paraFrm').action='CBTForm_input.action';
		      	document.getElementById('paraFrm').submit();
		      
		     }if(listType=="a"){
		  		 document.getElementById('paraFrm_listType').value='approved';
		      	document.getElementById('paraFrm').action='CBTForm_getApprovedList.action';
		      	document.getElementById('paraFrm').submit();
		      		
		      
		     }
		     if(listType=="c"){
		    
		     	document.getElementById('paraFrm_listType').value='cancelled';
		      		document.getElementById('paraFrm').action='CBTForm_input.action';
		      	document.getElementById('paraFrm').submit();
		      
		      
		     }
		     
		     if(listType=="r"){
		        document.getElementById('paraFrm_listType').value='reject';
		     	document.getElementById('paraFrm').action='CBTForm_getRejectedList.action';
		      	document.getElementById('paraFrm').submit();
		      		
		        
		     } 
		     
		    }
</script>