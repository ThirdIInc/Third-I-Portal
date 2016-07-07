<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<script type="text/javascript">
var records = new Array();
</script>
<s:form action="ClassRequest" validate="true" id="paraFrm" validate="true" theme="simple">

<!-- Operation Hidden Fields -->
	<s:hidden name="classCode" />
	<s:hidden name="status" />
<!-- Hidden Fields Ends -->	

	<!-- Paging purpose hideen fields -->
	<s:hidden name="myPageApproved" id="myPageApproved" />
	<s:hidden name="myPageReturn" id="myPageReturn" />
	<s:hidden name="myPageApprovedList" id="myPageApprovedList" />
	<s:hidden name="myPageApprovedCancelList" id="myPageApprovedCancelList" />
	<s:hidden name="myPageReject" id="myPageReject" />
	<s:hidden name="myPageRejectCancel" id="myPageRejectCancel" />
	<s:hidden name="myPageCancel" id="myPageCancel" />
	<s:hidden name="myPageRejected" id="myPageRejected" />
	<s:hidden name="myPageCancelled" id="myPageCancelled" />
	<s:hidden name="myPageCancelRejected" id="myPageCancelRejected" />
	<s:hidden name="myPageCancelApproved" id="myPageCancelApproved" />
	<s:hidden name="myPage" id="myPage" />
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
						class="text_head">Class Request </strong></td>
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

					<td><a href="#" onclick="callPending();">Pending
					Application</a> | <a href="#" onclick="callApprove();">Approved
					List</a> | <a href="#" onclick="callCancel();"> Cancelled
					Applications</a> | <a href="#" onclick="callReject();">Rejected
					List</a></td>
				</tr>

			</table>
			</td>
		</tr>

		<!----Draft List ----->

		<tr id="Draft">
			<td colspan="4">
			<table width="100%" class="formbg">

				<tr>
					<td width="100%">
					<table width="100%">
						<tr>
							<td><b>Draft</b></td>
							<td align="left" colspan="2">
							<%
								int totalPage = 0;
								int pageNo = 0;
							%> <s:if test="draftVoucherListLength">
								<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
								<%
										try {
										totalPage = (Integer) request.getAttribute("totalPage");
										pageNo = (Integer) request.getAttribute("pageNo");
									} catch (Exception e) {
										e.printStackTrace();
									}
								%> <a href="#"
									onclick="callPage('1', 'F', '<%=totalPage%>', 'ClassRequest_callPage.action');">
								<img title="First Page" src="../pages/common/img/first.gif"
									width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPage('P', 'P', '<%=totalPage%>', 'ClassRequest_callPage.action');">
								<img title="Previous Page"
									src="../pages/common/img/previous.gif" width="10" height="10"
									class="iconImage" /> </a> <input type="text" name="pageNoField"
									id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10"
									onkeypress="callPageText(event, '<%=totalPage%>', 'ClassRequest_callPage.action');return numbersOnly();" />
								of <%=totalPage%> <a href="#"
									onclick="callPage('N', 'N', '<%=totalPage%>', 'ClassRequest_callPage.action')">
								<img title="Next Page" src="../pages/common/img/next.gif"
									class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'ClassRequest_callPage.action');">
								<img title="Last Page" src="../pages/common/img/last.gif"
									width="10" height="10" class="iconImage" /> </a></td>
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
								name="sr.no" id="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></b>
							</td>

							<td class="formth" width="20%"><b><label class="set"
								name="tracking_num" id="tracking_num"
								ondblclick="callShowDiv(this);"><%=label.get("tracking_num")%></label></b>
							</td>

							<td class="formth" width="15%"><b><label class="set"
								name="class_name" id="class_name"
								ondblclick="callShowDiv(this);"><%=label.get("class_name")%></label></b>
							</td>

							<td class="formth" width="20%"><b><label class="set"
								name="class_desc" id="class_desc"
								ondblclick="callShowDiv(this);"><%=label.get("class_desc")%></label></b>
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

									<td class="sortableTD" width="20%"><s:property
										value="trackingNum" /></td>

									<td class="sortableTD" width="15%"><s:property
										value="className" /></td>
									<td class="sortableTD" width="20%"><s:property
										value="classDescription" /></td>

									<td class="sortableTD" width="20%" align="center"><s:property
										value="classAppDate" /></td>

									<td class="sortableTD" width="20%" align="center"><input
										type="button" name="view_Details" class="token" id="ctrlShow"
										value=" Edit Application "
										onclick="javascript:draftEdit('<s:property value="classCode"/>')" />
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

		<!----Draft List End----->

		<!-- Pending List Start -->
		<tr id="pending">
			<td colspan="4">
			<table width="100%" class="formbg">


				<tr>

					<td width="100%">
					<table width="100%">
						<tr>
							<td><b>Send for Approval List</b></td>
							<td align="left" colspan="2">
							<%
								int totalPageApproved = 0;
								int pageNoApproved = 0;
							%> <s:if test="pedingListListLength">
								<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
								<%
										try {
										totalPageApproved = (Integer) request.getAttribute("totalPageApproved");
										pageNoApproved = (Integer) request.getAttribute("pageNoApproved");
									} catch (Exception e) {
										e.printStackTrace();
									}
								%> <a href="#"
									onclick="callPageApproved('1', 'F', '<%=totalPageApproved%>', 'ClassRequest_callPage.action');">
								<img title="First Page" src="../pages/common/img/first.gif"
									width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPageApproved('P', 'P', '<%=totalPageApproved%>', 'ClassRequest_callPage.action');">
								<img title="Previous Page"
									src="../pages/common/img/previous.gif" width="10" height="10"
									class="iconImage" /> </a> <input type="text"
									name="pageNoFieldApproved" id="pageNoFieldApproved" size="3"
									value="<%=pageNoApproved%>" maxlength="10"
									onkeypress="callPageTextApproved(event, '<%=totalPageApproved%>', 'ClassRequest_callPage.action');return numbersOnly();" />
								of <%=totalPageApproved%> <a href="#"
									onclick="callPageApproved('N', 'N', '<%=totalPageApproved%>', 'ClassRequest_callPage.action')">
								<img title="Next Page" src="../pages/common/img/next.gif"
									class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPageApproved('<%=totalPageApproved%>', 'L', '<%=totalPageApproved%>', 'ClassRequest_callPage.action');">
								<img title="Last Page" src="../pages/common/img/last.gif"
									width="10" height="10" class="iconImage" /> </a></td>
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
								name="sr.no" id="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></b>
							</td>

							<td class="formth" width="20%"><b><label class="set"
								name="tracking_num" id="tracking_num"
								ondblclick="callShowDiv(this);"><%=label.get("tracking_num")%></label></b></td>



							<td class="formth" width="15%"><b><label class="set"
								name="class_name" id="class_name"
								ondblclick="callShowDiv(this);"><%=label.get("class_name")%></label></b>
							</td>

							<td class="formth" width="20%"><b><label class="set"
								name="class_desc" id="class_desc"
								ondblclick="callShowDiv(this);"><%=label.get("class_desc")%></label></b>
							</td>

							<td class="formth" width="20%"><b><label class="set"
								name="app_date" id="app_date" ondblclick="callShowDiv(this);"><%=label.get("app_date")%></label></b>
							</td>
							<td class="formth" width="20%"><b>Details</b></td>

						</tr>
						<s:if test="pedingListListLength">
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

									<td class="sortableTD" width="20%"><s:property
										value="trackingNum" /></td>
									<td class="sortableTD" width="15%"><s:property
										value="className" /></td>
									<td class="sortableTD" width="20%"><s:property
										value="classDescription" /></td>

									<td class="sortableTD" width="20%" align="center"><s:property
										value="classAppDate" /></td>

									<td class="sortableTD" width="20%" align="center"><input
										type="button" name="view_Details" class="token" id="ctrlShow"
										value=" View Application "
										onclick="javascript:viewPendingDetails('<s:property value="classCode"/>')" /></td>
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
		<!-- Pending List End -->

		<!-- Return Back/Send Back List Start -->
		<tr id="sendBack">
			<td colspan="4">
			<table width="100%" class="formbg">
				<tr>

					<td width="100%">
					<table width="100%">
						<tr>
							<td><b>Return Application List</b></td>
							<td align="left" colspan="2">
							<%
								int totalPageReturn = 0;
								int pageNoReturn = 0;
							%> <s:if test="returnListLength">
								<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
								<%
										try {
										totalPageReturn = (Integer) request.getAttribute("totalPageReturn");
										pageNoReturn = (Integer) request.getAttribute("pageNoReturn");
									} catch (Exception e) {
										e.printStackTrace();
									}
								%> <a href="#"
									onclick="callPageReturn('1', 'F', '<%=totalPageReturn%>', 'ClassRequest_callPage.action');">
								<img title="First Page" src="../pages/common/img/first.gif"
									width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPageReturn('P', 'P', '<%=totalPageReturn%>', 'ClassRequest_callPage.action');">
								<img title="Previous Page"
									src="../pages/common/img/previous.gif" width="10" height="10"
									class="iconImage" /> </a> <input type="text"
									name="pageNoFieldReturn" id="pageNoFieldReturn" size="3"
									value="<%=pageNoReturn%>" maxlength="10"
									onkeypress="callPageTextReturn(event, '<%=totalPageReturn%>', 'ClassRequest_callPage.action');return numbersOnly();" />
								of <%=totalPageReturn%> <a href="#"
									onclick="callPageReturn('N', 'N', '<%=totalPageReturn%>', 'ClassRequest_callPage.action')">
								<img title="Next Page" src="../pages/common/img/next.gif"
									class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPageReturn('<%=totalPageReturn%>', 'L', '<%=totalPageReturn%>', 'ClassRequest_callPage.action');">
								<img title="Last Page" src="../pages/common/img/last.gif"
									width="10" height="10" class="iconImage" /> </a></td>
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
								name="sr.no" id="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></b>
							</td>

							<td class="formth" width="20%"><b><label class="set"
								name="tracking_num" id="tracking_num"
								ondblclick="callShowDiv(this);"><%=label.get("tracking_num")%></label></b></td>

							<td class="formth" width="15%"><b><label class="set"
								name="class_name" id="class_name"
								ondblclick="callShowDiv(this);"><%=label.get("class_name")%></label></b>
							</td>

							<td class="formth" width="20%"><b><label class="set"
								name="class_desc" id="class_desc"
								ondblclick="callShowDiv(this);"><%=label.get("class_desc")%></label></b>
							</td>

							<td class="formth" width="20%"><b><label class="set"
								name="app_date" id="app_date" ondblclick="callShowDiv(this);"><%=label.get("app_date")%></label></b>
							</td>
							<td class="formth" width="20%"><b>Details</b></td>

						</tr>
						<s:if test="returnListLength">
							<%
							int count3 = 0;
							%>
							<%!int d3 = 0;%>
							<%
							int i3 = pageNoReturn * 20 - 20;
							%>
							<s:iterator value="returnList">
								<tr>
									<td class="sortableTD" width="5%" align="center"><%=++i3%></td>

									<td class="sortableTD" width="20%"><s:property
										value="trackingNum" /></td>
									<td class="sortableTD" width="15%"><s:property
										value="className" /></td>
									<td class="sortableTD" width="20%"><s:property
										value="classDescription" /></td>

									<td class="sortableTD" width="20%" align="center"><s:property
										value="classAppDate" /></td>

									<td class="sortableTD" width="20%" align="center"><input
										type="button" name="view_Details" class="token" id="ctrlShow"
										value=" Edit Application "
										onclick="javascript:draftEdit('<s:property value="classCode"/>')" /></td>
								</tr>

							</s:iterator>
							<%
							d3 = i3;
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

		<!-- Return/Send Back List End -->

		<!-- Approved List Start -->
		<tr id="Approved" style="display: none">
			<td colspan="4">
			<table width="100%" class="formbg">

				<tr>

					<td width="100%">
					<table width="100%">
						<tr>
							<td><b>Approved List</b></td>
							<td align="left" colspan="2">
							<%
								int totalPageApprovedList = 0;
								int pageNoApprovedList = 0;
							%> <s:if test="approvedListLength">
								<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
								<%
										try {
										totalPageApprovedList = (Integer) request
										.getAttribute("totalPageApprovedList");
										pageNoApprovedList = (Integer) request.getAttribute("pageNoApprovedList");
									} catch (Exception e) {
										e.printStackTrace();
									}
								%> <a href="#"
									onclick="callPageReturn('1', 'F', '<%=totalPageApprovedList%>', 'ClassRequest_callPage.action');">
								<img title="First Page" src="../pages/common/img/first.gif"
									width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPageApprovedList('P', 'P', '<%=totalPageApprovedList%>', 'ClassRequest_callPage.action');">
								<img title="Previous Page"
									src="../pages/common/img/previous.gif" width="10" height="10"
									class="iconImage" /> </a> <input type="text"
									name="pageNoFieldApprovedList" id="pageNoFieldApprovedList"
									size="3" value="<%=pageNoApprovedList%>" maxlength="10"
									onkeypress="callPageTextApprovedList(event, '<%=totalPageApprovedList%>', 'ClassRequest_callPage.action');return numbersOnly();" />
								of <%=totalPageApprovedList%> <a href="#"
									onclick="callPageApprovedList('N', 'N', '<%=totalPageApprovedList%>', 'ClassRequest_callPage.action')">
								<img title="Next Page" src="../pages/common/img/next.gif"
									class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPageApprovedList('<%=totalPageApprovedList%>', 'L', '<%=totalPageApprovedList%>', 'ClassRequest_callPage.action');">
								<img title="Last Page" src="../pages/common/img/last.gif"
									width="10" height="10" class="iconImage" /> </a></td>
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
								name="sr.no" id="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></b>
							</td>

							<td class="formth" width="20%"><b><label class="set"
								name="tracking_num" id="tracking_num"
								ondblclick="callShowDiv(this);"><%=label.get("tracking_num")%></label></b></td>



							<td class="formth" width="15%"><b><label class="set"
								name="class_name" id="class_name"
								ondblclick="callShowDiv(this);"><%=label.get("class_name")%></label></b>
							</td>

							<td class="formth" width="20%"><b><label class="set"
								name="class_desc" id="class_desc"
								ondblclick="callShowDiv(this);"><%=label.get("class_desc")%></label></b>
							</td>

							<td class="formth" width="20%"><b><label class="set"
								name="app_date" id="app_date" ondblclick="callShowDiv(this);"><%=label.get("app_date")%></label></b>
							</td>
							<td class="formth" width="20%"><b>Details</b></td>

						</tr>
						<s:if test="approvedListLength">
							<%
							int count4 = 0;
							%>
							<%!int d4 = 0;%>
							<%
							int i4 = pageNoApprovedList * 20 - 20;
							%>
							<s:iterator value="approvedList">
								<tr>
									<td class="sortableTD" width="5%" align="center"><%=++i4%></td>
									<td class="sortableTD" width="20%"><s:property
										value="trackingNum" /></td>

									<td class="sortableTD" width="15%"><s:property
										value="className" /></td>
									<td class="sortableTD" width="20%"><s:property
										value="classDescription" /></td>

									<td class="sortableTD" width="20%" align="center"><s:property
										value="classAppDate" /></td>

									<td class="sortableTD" width="20%" align="center"><input
										type="button" name="view_Details" class="token" id="ctrlShow"
										value=" View Application "
										onclick="javascript:viewPendingDetails('<s:property value="classCode"/>')" /></td>
								</tr>

							</s:iterator>
							<%
							d4 = i4;
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
		<!-- Approved List End -->

		<!-- Approved Cancelled List Start -->
		
		<tr id="ApprovedCancel" style="display: none">
			<td colspan="4">
			<table width="100%" class="formbg">
				<tr>
					<td width="100%">
					<table width="100%">
						<tr>
							<td><b>Approved Cancellation Applications List</b></td>
							<td align="left" colspan="2">
							<%
								int totalPageApprovedCancelList = 0;
								int pageNoApprovedCancelList = 0;
							%> <s:if test="approvedCancelListLength">
								<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
								<%
										try {
										totalPageApprovedCancelList = (Integer) request
										.getAttribute("totalPageApprovedCancelList");
										pageNoApprovedCancelList = (Integer) request
										.getAttribute("pageNoApprovedCancelList");
									} catch (Exception e) {
										e.printStackTrace();
									}
								%> <a href="#"
									onclick="callPage('1', 'F', '<%=totalPageApprovedCancelList%>', 'ClassRequest_callPage.action');">
								<img title="First Page" src="../pages/common/img/first.gif"
									width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPageApprovedCancelList('P', 'P', '<%=totalPageApprovedCancelList%>', 'ClassRequest_callPage.action');">
								<img title="Previous Page"
									src="../pages/common/img/previous.gif" width="10" height="10"
									class="iconImage" /> </a> <input type="text"
									name="pageNoFieldApprovedCancelList"
									id="pageNoFieldApprovedCancelList" size="3"
									value="<%=pageNoApprovedCancelList%>" maxlength="10"
									onkeypress="callPageTextApprovedCancelList(event, '<%=totalPageApprovedCancelList%>', 'ClassRequest_callPage.action');return numbersOnly();" />
								of <%=totalPage%> <a href="#"
									onclick="callPageApprovedCancelList('N', 'N', '<%=totalPageApprovedCancelList%>', 'ClassRequest_callPage.action')">
								<img title="Next Page" src="../pages/common/img/next.gif"
									class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPageApprovedCancelList('<%=totalPageApprovedCancelList%>', 'L', '<%=totalPageApprovedCancelList%>', 'ClassRequest_callPage.action');">
								<img title="Last Page" src="../pages/common/img/last.gif"
									width="10" height="10" class="iconImage" /> </a></td>
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
								name="sr.no" id="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></b>
							</td>

							<td class="formth" width="20%"><b><label class="set"
								name="tracking_num" id="tracking_num"
								ondblclick="callShowDiv(this);"><%=label.get("tracking_num")%></label></b></td>


							<td class="formth" width="15%"><b><label class="set"
								name="class_name" id="class_name"
								ondblclick="callShowDiv(this);"><%=label.get("class_name")%></label></b>
							</td>

							<td class="formth" width="20%"><b><label class="set"
								name="class_desc" id="class_desc"
								ondblclick="callShowDiv(this);"><%=label.get("class_desc")%></label></b>
							</td>

							<td class="formth" width="20%"><b><label class="set"
								name="app_date" id="app_date" ondblclick="callShowDiv(this);"><%=label.get("app_date")%></label></b>
							</td>
							<td class="formth" width="20%"><b>Details</b></td>

						</tr>
						<s:if test="approvedCancelListLength">
							<%
							int count5 = 0;
							%>
							<%!int d5 = 0;%>
							<%
							int i5 = pageNoApprovedCancelList * 20 - 20;
							%>
							<s:iterator value="approvedCancelList">
								<tr>
									<td class="sortableTD" width="5%" align="center"><%=++i5%></td>
									<td class="sortableTD" width="20%"><s:property
										value="trackingNum" /></td>

									<td class="sortableTD" width="15%"><s:property
										value="className" /></td>
									<td class="sortableTD" width="20%"><s:property
										value="classDescription" /></td>

									<td class="sortableTD" width="20%" align="center"><s:property
										value="classAppDate" /></td>

									<td class="sortableTD" width="20%" align="center"><input
										type="button" name="view_Details" class="token" id="ctrlShow"
										value=" View Application "
										onclick="javascript:viewDetails('<s:property value="classCode"/>')" /></td>
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
		<!-- Approved Cancelled List End -->

		<!-- Cancel List Start-->

		<tr id="cancel" style="display: none">
			<td colspan="4">
			<table width="100%" class="formbg">
				<tr>
					<td width="100%">
					<table width="100%">
						<tr>
							<td><b>Cancel List</b></td>
							<td align="left" colspan="2">
							<%
								int totalPageCancel = 0;
								int pageNoCancel = 0;
							%> <s:if test="cancelListLength">
								<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
								<%
										try {
										totalPageCancel = (Integer) request.getAttribute("totalPageCancel");
										pageNoCancel = (Integer) request.getAttribute("pageNoCancel");
									} catch (Exception e) {
										e.printStackTrace();
									}
								%> <a href="#"
									onclick="callPageCancel('1', 'F', '<%=totalPageCancel%>', 'ClassRequest_callPage.action');">
								<img title="First Page" src="../pages/common/img/first.gif"
									width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPageCancel('P', 'P', '<%=totalPageCancel%>', 'ClassRequest_callPage.action');">
								<img title="Previous Page"
									src="../pages/common/img/previous.gif" width="10" height="10"
									class="iconImage" /> </a> <input type="text"
									name="pageNoFieldCancel" id="pageNoFieldCancel" size="3"
									value="<%=pageNoCancel%>" maxlength="10"
									onkeypress="callPageTextCancel(event, '<%=totalPageCancel%>', 'ClassRequest_callPage.action');return numbersOnly();" />
								of <%=totalPageCancel%> <a href="#"
									onclick="callPageCancel('N', 'N', '<%=totalPageCancel%>', 'ClassRequest_callPage.action')">
								<img title="Next Page" src="../pages/common/img/next.gif"
									class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPageCancel('<%=totalPageCancel%>', 'L', '<%=totalPageCancel%>', 'ClassRequest_callPage.action');">
								<img title="Last Page" src="../pages/common/img/last.gif"
									width="10" height="10" class="iconImage" /> </a></td>
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
								name="sr.no" id="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></b>
							</td>

							<td class="formth" width="20%"><b><label class="set"
								name="tracking_num" id="tracking_num"
								ondblclick="callShowDiv(this);"><%=label.get("tracking_num")%></label></b></td>

							<td class="formth" width="15%"><b><label class="set"
								name="class_name" id="class_name"
								ondblclick="callShowDiv(this);"><%=label.get("class_name")%></label></b>
							</td>

							<td class="formth" width="20%"><b><label class="set"
								name="class_desc" id="class_desc"
								ondblclick="callShowDiv(this);"><%=label.get("class_desc")%></label></b>
							</td>

							<td class="formth" width="20%"><b><label class="set"
								name="app_date" id="app_date" ondblclick="callShowDiv(this);"><%=label.get("app_date")%></label></b>
							</td>
							<td class="formth" width="20%"><b>Details</b></td>
						</tr>
						<s:if test="cancelListLength">
							<%
							int count6 = 0;
							%>
							<%!int d6 = 0;%>
							<%
							int i6 = pageNoCancel * 20 - 20;
							%>
							<s:iterator value="cancelList">
								<tr>
									<td class="sortableTD" width="5%" align="center"><%=++i6%></td>

									<td class="sortableTD" width="20%"><s:property
										value="trackingNum" /></td>

									<td class="sortableTD" width="15%"><s:property
										value="className" /></td>
									<td class="sortableTD" width="20%"><s:property
										value="classDescription" /></td>

									<td class="sortableTD" width="20%" align="center"><s:property
										value="classAppDate" /></td>

									<td class="sortableTD" width="20%" align="center"><input
										type="button" name="view_Details" class="token" id=ctrlShow
										value=" View Application "
										onclick="javascript:viewDetails('<s:property value="classCode"/>')" /></td>
								</tr>
							</s:iterator>
							<%
							d6 = i6;
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
		<!-- Cancel List End -->

		<!-- Rejected List Start -->
		
		<tr id="Reject" style="display: none">
			<td colspan="4">
			<table width="100%" class="formbg">
				<tr>
					<td width="100%">
					<table width="100%">
						<tr>
							<td><b>Rejected List</b></td>
							<td align="left" colspan="2">
							<%
								int totalPageReject = 0;
								int pageNoReject = 0;
							%> <s:if test="rejectedListLength">
								<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
								<%
										try {
										totalPageReject = (Integer) request.getAttribute("totalPageReject");
										pageNoReject = (Integer) request.getAttribute("pageNoReject");
									} catch (Exception e) {
										e.printStackTrace();
									}
								%> <a href="#"
									onclick="callPage('1', 'F', '<%=totalPageReject%>', 'ClassRequest_callPage.action');">
								<img title="First Page" src="../pages/common/img/first.gif"
									width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPageReject('P', 'P', '<%=totalPageReject%>', 'ClassRequest_callPage.action');">
								<img title="Previous Page"
									src="../pages/common/img/previous.gif" width="10" height="10"
									class="iconImage" /> </a> <input type="text"
									name="pageNoFieldReject" id="pageNoFieldReject" size="3"
									value="<%=pageNoReject%>" maxlength="10"
									onkeypress="callPageTextReject(event, '<%=totalPageReject%>', 'ClassRequest_callPage.action');return numbersOnly();" />
								of <%=totalPageReject%> <a href="#"
									onclick="callPageReject('N', 'N', '<%=totalPageReject%>', 'ClassRequest_callPage.action')">
								<img title="Next Page" src="../pages/common/img/next.gif"
									class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPageReject('<%=totalPageReject%>', 'L', '<%=totalPageReject%>', 'ClassRequest_callPage.action');">
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
							<td class="formth" width="20%"><b><label class="set"
								name="tracking_num" id="tracking_num"
								ondblclick="callShowDiv(this);"><%=label.get("tracking_num")%></label></b></td>

							<td class="formth" width="15%"><b><label class="set"
								name="class_name" id="class_name"
								ondblclick="callShowDiv(this);"><%=label.get("class_name")%></label></b>
							</td>

							<td class="formth" width="20%"><b><label class="set"
								name="class_desc" id="class_desc"
								ondblclick="callShowDiv(this);"><%=label.get("class_desc")%></label></b>
							</td>

							<td class="formth" width="20%"><b><label class="set"
								name="app_date" id="app_date" ondblclick="callShowDiv(this);"><%=label.get("app_date")%></label></b>
							</td>
							<td class="formth" width="20%"><b>Details</b></td>
						</tr>
						<s:if test="rejectedListLength">
							<%
							int count7 = 0;
							%>
							<%!int d7 = 0;%>
							<%
							int i7 = pageNoReject * 20 - 20;
							%>
							<s:iterator value="rejectedList">
								<tr>
									<td class="sortableTD" width="5%" align="center"><%=++i7%></td>
									<td class="sortableTD" width="20%"><s:property
										value="trackingNum" /></td>
									<td class="sortableTD" width="15%"><s:property
										value="className" /></td>
									<td class="sortableTD" width="20%"><s:property
										value="classDescription" /></td>

									<td class="sortableTD" width="20%" align="center"><s:property
										value="classAppDate" /></td>

									<td class="sortableTD" width="20%" align="center"><input
										type="button" name="view_Details" class="token" id="ctrlShow"
										value=" View Application "
										onclick="javascript:viewDetails('<s:property value="classCode"/>')" /></td>
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
		<!-- Rejected List End -->

		<!-- Approved Rejected List Start -->
		
		<tr id="ApprovedReject" style="display: none">
			<td colspan="4">
			<table width="100%" class="formbg">
				<tr>

					<td width="100%">
					<table width="100%">
						<tr>
							<td><b>Rejected Cancellation Applications List</b></td>
							<td align="left" colspan="2">
							<%
								int totalPageRejectCancel = 0;
								int pageNoRejectCancel = 0;
							%> <s:if test="approvedRejectListLength">
								<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
								<%
										try {
										totalPageRejectCancel = (Integer) request
										.getAttribute("totalPageRejectCancel");
										pageNoRejectCancel = (Integer) request.getAttribute("pageNoRejectCancel");
									} catch (Exception e) {
										e.printStackTrace();
									}
								%> <a href="#"
									onclick="callPageRejectCancel('1', 'F', '<%=totalPageRejectCancel%>', 'ClassRequest_callPage.action');">
								<img title="First Page" src="../pages/common/img/first.gif"
									width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPageRejectCancel('P', 'P', '<%=totalPageRejectCancel%>', 'ClassRequest_callPage.action');">
								<img title="Previous Page"
									src="../pages/common/img/previous.gif" width="10" height="10"
									class="iconImage" /> </a> <input type="text"
									name="pageNoFieldRejectCancel" id="pageNoFieldRejectCancel"
									size="3" value="<%=pageNoRejectCancel%>" maxlength="10"
									onkeypress="callPageTextRejectCancel(event, '<%=totalPageRejectCancel%>', 'ClassRequest_callPage.action');return numbersOnly();" />
								of <%=totalPageRejectCancel%> <a href="#"
									onclick="callPageRejectCancel('N', 'N', '<%=totalPageRejectCancel%>', 'ClassRequest_callPage.action')">
								<img title="Next Page" src="../pages/common/img/next.gif"
									class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPageRejectCancel('<%=totalPageRejectCancel%>', 'L', '<%=totalPageRejectCancel%>', 'ClassRequest_callPage.action');">
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
							<td class="formth" width="20%"><b><label class="set"
								name="tracking_num" id="tracking_num"
								ondblclick="callShowDiv(this);"><%=label.get("tracking_num")%></label></b></td>
							<td class="formth" width="15%"><b><label class="set"
								name="class_name" id="class_name"
								ondblclick="callShowDiv(this);"><%=label.get("class_name")%></label></b>
							</td>

							<td class="formth" width="20%"><b><label class="set"
								name="class_desc" id="class_desc"
								ondblclick="callShowDiv(this);"><%=label.get("class_desc")%></label></b>
							</td>
							<td class="formth" width="20%"><b><label class="set"
								name="app_date" id="app_date" ondblclick="callShowDiv(this);"><%=label.get("app_date")%></label></b>
							</td>
							<td class="formth" width="20%"><b>Details</b></td>
						</tr>
						<s:if test="approvedRejectListLength">
							<%
							int count8 = 0;
							%>
							<%!int d8 = 0;%>
							<%
							int i8 = pageNoRejectCancel * 20 - 20;
							%>
							<s:iterator value="approvedRejectList">
								<tr>
									<td class="sortableTD" width="5%" align="center"><%=++i8%></td>

									<td class="sortableTD" width="20%"><s:property
										value="trackingNum" /></td>
									<td class="sortableTD" width="15%"><s:property
										value="className" /></td>
									<td class="sortableTD" width="20%"><s:property
										value="classDescription" /></td>

									<td class="sortableTD" width="20%" align="center"><s:property
										value="classAppDate" /></td>

									<td class="sortableTD" width="20%" align="center"><input
										type="button" name="view_Details" class="token" id="ctrlShow"
										value=" View Application "
										onclick="javascript:viewDetails('<s:property value="classCode"/>')" /></td>
								</tr>
							</s:iterator>
							<%
							d8 = i8;
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
		<!-- Approved Rejected  List End -->

	</table><!-- Table Ends Here -->
</s:form><!-- Form Ends Here -->

<!-- Scripting Starts Here -->
<script>

function addapplicationFun() {
	
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action =  'ClassRequest_addNew.action';
		document.getElementById('paraFrm').submit();
	}

function sendforapprovalFun(status)
{ 
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action ='ClassRequest_draft.action?status ='+status; 		
		document.getElementById('paraFrm').submit();

}

function draftEdit(id)
{ 

		//document.getElementById('paraFrm').target = "_self";
	    document.getElementById('paraFrm_classCode').value =id;
		document.getElementById('paraFrm').action ='ClassRequest_editBusinessData.action';		
		document.getElementById('paraFrm').submit();



}

function callForDelete1(id,i)
	   {
	 /*alert("i am in dete fun in regionMasterInput jsp");*/
	   
	/*alert('id----1-----'+id);*/
	/*alert('i----1-----'+i);*/
	
	   if(document.getElementById('confChk'+i).checked == true)
	   {	  
	    document.getElementById('hdeleteCode'+i).value=id;
	   }
	   else
	   {
	   document.getElementById('hdeleteCode'+i).value="";
   }
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
			document.getElementById('ApprovedCancel').style.display = '';
				
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
			document.getElementById('ApprovedReject').style.display = '';
		
		}
		
	 function viewPendingDetails(appno){
	      	document.getElementById('paraFrm').action='ClassRequest_retrivePendingDetails.action?levApplicationCode='+appno;
	      	document.getElementById('paraFrm').submit();
	    }
		   
     function viewDetails(appno){
     
		      	document.getElementById('paraFrm').action='ClassRequest_cancelDetails.action?levApplicationCode='+appno;
		      	document.getElementById('paraFrm').submit();
		     
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




/*Approved List*/

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
	
/*Approved Cancel text*/
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
	
	//Reject List
	function callPageReject(id, pageImg, totalPageHid, action) {		
		
		try
		{
		pageNo = document.getElementById('pageNoFieldReject').value;	
		actPage = document.getElementById('myPageReject').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoFieldReject').focus();
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
	 
</script>
