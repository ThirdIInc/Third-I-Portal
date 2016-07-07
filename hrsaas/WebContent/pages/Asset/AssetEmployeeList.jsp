<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="AssetEmployee" validate="true" id="paraFrm"
	theme="simple">

	<s:hidden name="myPageRejected" id="myPageRejected" />
	
	<s:hidden name="myPageAssigned" id="myPageAssigned" />

	<s:hidden name="hiddencode" />
	<s:hidden name="hdeleteCode" />
	<s:hidden name="hiddenStatus" />
	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Asset
					Application </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
		<!-- Tab Options Stars -->
		<tr>
			<td width="100%">
			<table width="100%">
				<tr>
					<td><a href="#" onclick="setAction('P')">Pending
					Application</a> | <a href="#" onclick="setAction('A')">Approved
					List</a> | <a href="#" onclick="setAction('R')">Rejected List</a> | <a
						href="#" onclick="setAction('S')">Assigned List</a></td>
					<script type="text/javascript">
							function setAction(listType)
							{
								if(listType == "P") {
									document.getElementById('paraFrm').action = 'AssetEmployee_input.action';
									document.getElementById('paraFrm').submit();
								}
								if(listType == "A") {
									document.getElementById('paraFrm').action = 'AssetEmployee_getApprovedList.action';
									document.getElementById('paraFrm').submit();
								}
								if(listType == "R") {
									document.getElementById('paraFrm').action = 'AssetEmployee_getRejectedList.action';
									document.getElementById('paraFrm').submit();
								}
								if(listType == "S") {
									document.getElementById('paraFrm').action = 'AssetEmployee_getAssignedList.action';
									document.getElementById('paraFrm').submit();
								}
							}
						</script>
				</tr>
			</table>
			</td>
		</tr>
		<!-- Tab Options Ends -->
		<!-- Pending Tab Starts -->
		<s:if test="%{listType=='pending'}">
			<!-- Draft Table Starts -->
			<tr>
				<td>
				<table width="100%" class="formbg" border="0">
					<tr>
						<td><b>Draft</b></td>
					</tr>
					<tr>
						<td>
						<table width="100%" class="sorttable">
							<tr>
								<td class="formth" width="10%" nowrap="nowrap"><b><label id="serial.no"
									name="serial.no" class="set" ondblclick="callShowDiv(this);">
								<%=label.get("serial.no")%></label></b></td>
								
									<td class="formth" width="10%" nowrap="nowrap"><b><label id="assetId"
									name="assetId" class="set" ondblclick="callShowDiv(this);">
								<%=label.get("assetId")%></label></b></td>
								
								<td class="formth" width="22%" nowrap="nowrap"><b><label id="emp.id"
									name="emp.id" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("emp.id")%></label></b></td>
								<td class="formth" width="23%" nowrap="nowrap"><b><label id="emp.name"
									name="emp.name" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("emp.name")%></label></b></td>
								<td class="formth" width="25%" nowrap="nowrap"><b><label id="appDate"
									name="appDate" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("appDate")%></label></b></td>
								<td class="formth" width="20%" nowrap="nowrap"><b>Edit Application</b></td>
							</tr>
							<%
							int i = 0;
							%>
							<s:iterator value="draftList">
								<tr class="tableCell1">
									<td class="sortableTD" width="10%" nowrap="nowrap"><%=++i%></td>
									<td class="sortableTD" width="10%" nowrap="nowrap"><s:property
										value="assetId" /></td>
									<td class="sortableTD" width="22%" nowrap="nowrap"><s:property
										value="empToken" /></td>
									<td class="sortableTD" width="23%" nowrap="nowrap"><s:property
										value="empName" /></td>
									<td class="sortableTD" width="25%" nowrap="nowrap" align="center"><s:property
										value="assignDate1" /></td>
									<td class="sortableTD" width="20%" align="center">&nbsp;<input
										type="button" name="view_Details" class="token"
										value=" Edit Application "
										onclick="viewDetails('<s:property value="code"/>','<s:property value="status"/>')" /></td>

								</tr>
							</s:iterator>
							<%
							if (i == 0) {
							%>
							<tr>
								<td colspan="6" class="sortableTD" align="center" width="100%">
								<font color="red">No Data to display</font></td>
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
			<!-- Draft Table Ends -->
			<!-- Submitted List Starts -->
			<tr>
				<td>
				<table width="100%" class="formbg" border="0">
					<tr>
						<td><b>Application In Process</b></td>
					</tr>
					<tr>
						<td>
						<table width="100%" class="sorttable">
							<tr>
								<td class="formth" width="10%" nowrap="nowrap"><b><label id="serial.no"
									name="serial.no" class="set" ondblclick="callShowDiv(this);">
								<%=label.get("serial.no")%></label></b></td>
								
								<td class="formth" width="10%" nowrap="nowrap"><b><label id="assetId1"
									name="assetId" class="set" ondblclick="callShowDiv(this);">
								<%=label.get("assetId")%></label></b></td>
								
								
								<td class="formth" width="22%" nowrap="nowrap"><b><label id="emp.id"
									name="emp.id" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("emp.id")%></label></b></td>
								<td class="formth" width="23%" nowrap="nowrap"><b><label id="emp.name"
									name="emp.name" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("emp.name")%></label></b></td>
								<td class="formth" width="25%" nowrap="nowrap"><b><label id="appDate"
									name="appDate" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("appDate")%></label></b></td>
								<td class="formth" width="20%" nowrap="nowrap"><b>View Application</b></td>
							</tr>
							<%
							int j = 0;
							%>
							<s:iterator value="submittedList">
								<tr class="tableCell1">

									<td class="sortableTD" width="10%" nowrap="nowrap"><%=++j%></td>
									
										<td class="sortableTD" width="10%" nowrap="nowrap"><s:property
										value="assetId" /></td>
										
										
									<td class="sortableTD" width="22%" nowrap="nowrap"><s:property
										value="empToken" /></td>
									<td class="sortableTD" width="23%" nowrap="nowrap"><s:property
										value="empName" /></td>
									<td class="sortableTD" width="25%" nowrap="nowrap" align="center"><s:property
										value="assignDate1" />&nbsp;</td>
									<td class="sortableTD" width="20%" align="center" nowrap="nowrap"><input
										type="button" name="view_Details" class="token"
										value=" View Application "
										onclick="viewDetails('<s:property value="code"/>','<s:property value="status"/>')" /></td>

								</tr>
							</s:iterator>
							<%
							if (j == 0) {
							%>
							<tr>
								<td colspan="6" class="sortableTD" align="center" width="100%">
								<font color="red">No Data to display</font></td>
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
			<!-- Submitted List Ends -->
			<!-- Returned List Starts -->
			<tr>
				<td>
				<table width="100%" class="formbg" border="0">
					<tr>
						<td><b>Returned Applications(Please view the comments,
						update Application)</b></td>
					</tr>
					<tr>
						<td>
						<table width="100%" class="sorttable">
							<tr>
								<td class="formth" width="10%" nowrap="nowrap"><b><label id="serial.no"
									name="serial.no" class="set" ondblclick="callShowDiv(this);">
								<%=label.get("serial.no")%></label></b></td>
								
								<td class="formth" width="10%" nowrap="nowrap"><b><label id="assetId2"
									name="assetId" class="set" ondblclick="callShowDiv(this);">
								<%=label.get("assetId")%></label></b></td>
								
								
								
								<td class="formth" width="22%" nowrap="nowrap"><b><label id="emp.id"
									name="emp.id" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("emp.id")%></label></b></td>
								<td class="formth" width="23%" nowrap="nowrap"><b><label id="emp.name"
									name="emp.name" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("emp.name")%></label></b></td>
								<td class="formth" width="25%" nowrap="nowrap"><b><label id="appDate"
									name="appDate" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("appDate")%></label></b></td>
								<td class="formth" width="20%" nowrap="nowrap"><b>Edit Application</b></td>
							</tr>
							<%
							int k = 0;
							%>
							<s:iterator value="returnedList">
								<tr class="tableCell1">

									<td class="sortableTD" width="10%" nowrap="nowrap"><%=++k%></td>
									
										<td class="sortableTD" width="10%" nowrap="nowrap"><s:property
										value="assetId" /></td>
										
										
									<td class="sortableTD" width="22%" nowrap="nowrap"><s:property
										value="empToken" /></td>
									<td class="sortableTD" width="23%" nowrap="nowrap" ><s:property
										value="empName" /></td>
									<td class="sortableTD" width="25%" nowrap="nowrap" align="center"> <s:property
										value="assignDate1" />&nbsp;</td>
									<td class="sortableTD" width="20%" align="center" nowrap="nowrap"><input
										type="button" name="view_Details" class="token"
										value=" Edit Application "
										onclick="viewDetails('<s:property value="code"/>','<s:property value="status"/>')" /></td>

								</tr>
							</s:iterator>
							<%
							if (k == 0) {
							%>
							<tr>
								<td colspan="6" class="sortableTD" align="center" width="100%">
								<font color="red">No Data to display</font></td>
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
			<!-- Returned List Ends -->
		</s:if>
		<!-- Pending Tab Ends -->
		<s:hidden name="myPage" id="myPage" />
		<!-- Approved Tab Starts -->
		<s:if test="%{listType=='approved'}">
			<tr>
				<td>
				<table width="100%" class="formbg" border="0">
					<tr>
						<td><b>Approved Application List</b></td>
					</tr>
					<tr>
						<%
							int totalPage1 = 0;
							int pageNo1 = 0;
						%>
						<s:if test="listLengthApproved">
							<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
							<%
								totalPage1 = (Integer) request.getAttribute("totalPage");
								pageNo1 = (Integer) request.getAttribute("pageNo");
							%> <a href="#"
								onclick="callPage('1', 'F', '<%=totalPage1%>', 'AssetEmployee_getApprovedList.action');">
							<img title="First Page" src="../pages/common/img/first.gif"
								width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPage('P', 'P', '<%=totalPage1%>', 'AssetEmployee_getApprovedList.action');">
							<img title="Previous Page" src="../pages/common/img/previous.gif"
								width="10" height="10" class="iconImage" /> </a> <input type="text"
								name="pageNoField" id="pageNoField" size="3"
								value="<%=pageNo1%>" maxlength="10"
								onkeypress="callPageText(event, '<%=totalPage1%>', 'AssetEmployee_getApprovedList.action');return numbersOnly();" />
							of <%=totalPage1%> <a href="#"
								onclick="callPage('N', 'N', '<%=totalPage1%>', 'AssetEmployee_getApprovedList.action')">
							<img title="Next Page" src="../pages/common/img/next.gif"
								class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPage('<%=totalPage1%>', 'L', '<%=totalPage1%>', 'AssetEmployee_getApprovedList.action');">
							<img title="Last Page" src="../pages/common/img/last.gif"
								width="10" height="10" class="iconImage" /> </a></td>
						</s:if>
					</tr>
					<tr>
						<td>
						<table width="100%" class="sorttable">
							<tr>
								<td class="formth" width="10%" nowrap="nowrap"><b><label id="serial.no"
									name="serial.no" class="set" ondblclick="callShowDiv(this);">
								<%=label.get("serial.no")%></label></b></td>
								
									<td class="formth" width="10%" nowrap="nowrap"><b><label id="assetId3"
									name="assetId" class="set" ondblclick="callShowDiv(this);">
								<%=label.get("assetId")%></label></b></td>
								
								
								
								<td class="formth" width="22%" nowrap="nowrap"><b><label id="emp.id"
									name="emp.id" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("emp.id")%></label></b></td>
								<td class="formth" width="23%" nowrap="nowrap"><b><label id="emp.name"
									name="emp.name" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("emp.name")%></label></b></td>
								<td class="formth" width="25%" nowrap="nowrap"><b><label id="appDate"
									name="appDate" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("appDate")%></label></b></td>
								<td class="formth" width="20%" nowrap="nowrap"><b>View Application</b></td>
							</tr>
							<%
							int cn = pageNo1 * 20 - 20;
							%>
							<s:if test="listLengthApproved">
								<s:iterator value="approvedList">
									<tr class="tableCell1">

										<td class="sortableTD" width="10%" nowrap="nowrap"><%=++cn%></td>
										
											<td class="sortableTD" width="10%" nowrap="nowrap"><s:property
										value="assetId" /></td>
										
										
										
										<td class="sortableTD" width="22%" nowrap="nowrap"><s:property
											value="empToken" /></td>
										<td class="sortableTD" width="23%" nowrap="nowrap"><s:property
											value="empName" /></td>
										<td class="sortableTD" width="25%" nowrap="nowrap" align="center"><s:property
											value="assignDate1" />&nbsp;</td>
										<td class="sortableTD" width="20%" align="center" nowrap="nowrap"><input
											type="button" name="view_Details" class="token"
											value=" View Application "
											onclick="viewDetails('<s:property value="code"/>','<s:property value="status"/>')" /></td>

									</tr>
								</s:iterator>
							</s:if>

							<s:else>
								<tr>
									<td colspan="6" class="sortableTD" align="center" width="100%">
									<font color="red">No Data to display</font></td>
								</tr>
							</s:else>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<s:if test="listLengthApproved">
					<td align="right"><b>Total No. of Records: <s:property
						value="totalRecords" /></b></td>
				</s:if>
			</tr>
		</s:if>
		<!-- Approved Tab Ends -->
		<!-- Rejected Tab Starts -->
		<s:if test="%{listType=='rejected'}">
			<tr>
				<td>
				<table width="100%" class="formbg" border="0">
					<tr>
						<td><b>Rejected Applications List</b></td>
					</tr>
					<tr>

						<%
							int totalPageRejected = 0;
							int PageNoRejected = 0;
						%>

						<s:if test="listLengthRejected">
							<tr>
								<td id="ctrlShow" width="100%" align="right"><b>Page:</b> <%
 			totalPageRejected = (Integer) request
 			.getAttribute("totalPageRejected");
 	PageNoRejected = (Integer) request.getAttribute("PageNoRejected");
 %> <a href="#"
									onclick="callPageRejected('1', 'F', '<%=totalPageRejected%>', 'AssetEmployee_getRejectedList.action');">
								<img title="First Page" src="../pages/common/img/first.gif"
									width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPageRejected('P', 'P', '<%=totalPageRejected%>', 'AssetEmployee_getRejectedList.action');">
								<img title="Previous Page"
									src="../pages/common/img/previous.gif" width="10" height="10"
									class="iconImage" /> </a> <input type="text" name="pageNoField3"
									id="pageNoField3" size="3" value="<%=PageNoRejected%>"
									maxlength="10"
									onkeypress="callPageTextReject(event, '<%=totalPageRejected%>', 'AssetEmployee_getRejectedList.action');return numbersOnly();" />
								of <%=totalPageRejected%> <a href="#"
									onclick="callPageRejected('N', 'N', '<%=totalPageRejected%>', 'AssetEmployee_getRejectedList.action')">
								<img title="Next Page" src="../pages/common/img/next.gif"
									class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPageRejected('<%=totalPageRejected%>', 'L', '<%=totalPageRejected%>', 'AssetEmployee_getRejectedList.action');">
								<img title="Last Page" src="../pages/common/img/last.gif"
									width="10" height="10" class="iconImage" /> </a></td>
							</tr>
						</s:if>
					</tr>
					<tr>
						<td colspan="2">
						<table width="100%" class="sorttable">
							<tr>
								<td class="formth" width="10%" nowrap="nowrap"><b><label id="serial.no"
									name="serial.no" class="set" ondblclick="callShowDiv(this);">
								<%=label.get("serial.no")%></label></b></td>
								
										<td class="formth" width="10%" nowrap="nowrap"><b><label id="assetId4"
									name="assetId" class="set" ondblclick="callShowDiv(this);">
								<%=label.get("assetId")%></label></b></td>
								
								
								
								<td class="formth" width="22%" nowrap="nowrap"><b><label id="emp.id"
									name="emp.id" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("emp.id")%></label></b></td>
								<td class="formth" width="23%" nowrap="nowrap"><b><label id="emp.name"
									name="emp.name" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("emp.name")%></label></b></td>
								<td class="formth" width="25%" nowrap="nowrap"><b><label id="appDate"
									name="appDate" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("appDate")%></label></b></td>
								<td class="formth" width="20%" nowrap="nowrap"><b>View Application</b></td>
							</tr>

							<%
							int cn3 = PageNoRejected * 20 - 20;
							%>

							<s:if test="listLengthRejected">
								<s:iterator value="rejectedList">
									<tr class="tableCell1">

										<td class="sortableTD" width="10%" nowrap="nowrap"><%=++cn3%></td>
										
											<td class="sortableTD" width="10%" nowrap="nowrap"><s:property
										value="assetId" /></td>
										
										
										
										<td class="sortableTD" width="22%" nowrap="nowrap"><s:property
											value="empToken" /></td>
										<td class="sortableTD" width="23%" nowrap="nowrap"><s:property
											value="empName" /></td>
										<td class="sortableTD" width="25%" nowrap="nowrap" align="center"><s:property
											value="assignDate1" />&nbsp;</td>
										<td class="sortableTD" width="20%" align="center" nowrap="nowrap"><input
											type="button" name="view_Details" class="token"
											value=" View Application "
											onclick="viewDetails('<s:property value="code"/>','<s:property value="status"/>')" /></td>

									</tr>
								</s:iterator>
							</s:if>

							<s:else>
								<tr>
									<td colspan="6" class="sortableTD" align="center" width="100%">
									<font color="red">No Data to display</font></td>
								</tr>
							</s:else>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<s:if test="listLengthRejected">
					<td align="right"><b>Total No. of Records: <s:property
						value="totalRecords" /></b></td>
				</s:if>
			</tr>
		</s:if>
		<!-- Rejected Tab Ends -->
		<!-- Assigned Tab Starts -->
		<s:if test="%{listType=='assigned'}">
			<tr>
				<td>
				<table width="100%" class="formbg" border="0">
					<tr>
						<td align="left"><b>Assigned Applications List</b></td>
						
						<%
						int totalPageAssigned = 0;
						int PageNoAssigned = 0;
					%>

						<s:if test="listLengthAssigned">
						
						<tr>
							<td id="ctrlShow" width="100%" align="right"><b>Page:</b> <%
							totalPageAssigned = (Integer) request
 			.getAttribute("totalPageAssigned");
							PageNoAssigned = (Integer) request.getAttribute("PageNoAssigned");
 %> <a href="#"
								onclick="callPageAssigned('1', 'F', '<%=totalPageAssigned%>', 'AssetEmployee_getAssignedList.action');">
							<img title="First Page" src="../pages/common/img/first.gif"
								width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPageAssigned('P', 'P', '<%=totalPageAssigned%>', 'AssetEmployee_getAssignedList.action');">
							<img title="Previous Page" src="../pages/common/img/previous.gif"
								width="10" height="10" class="iconImage" /> </a> <input type="text"
								name="pageNoField8" id="pageNoField8" size="3"
								value="<%=PageNoAssigned%>" maxlength="10"
								onkeypress="callPageTextAssigned(event, '<%=totalPageAssigned%>', 'AssetEmployee_getAssignedList.action');return numbersOnly();" />
							of <%=totalPageAssigned%> <a href="#"
								onclick="callPageAssigned('N', 'N', '<%=totalPageAssigned%>', 'AssetEmployee_getAssignedList.action')">
							<img title="Next Page" src="../pages/common/img/next.gif"
								class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPageAssigned('<%=totalPageAssigned%>', 'L', '<%=totalPageAssigned%>', 'AssetEmployee_getAssignedList.action');">
							<img title="Last Page" src="../pages/common/img/last.gif"
								width="10" height="10" class="iconImage" /> </a></td>
						</tr>

						</s:if>
					</tr>
					<tr>
						<td colspan="2">
						<table width="100%" class="sorttable">
							<tr>
								<td class="formth" width="10%" nowrap="nowrap"><b><label id="serial.no"
									name="serial.no" class="set" ondblclick="callShowDiv(this);">
								<%=label.get("serial.no")%></label></b></td>
								
								
										<td class="formth" width="10%" nowrap="nowrap"><b><label id="assetId5"
									name="assetId" class="set" ondblclick="callShowDiv(this);">
								<%=label.get("assetId")%></label></b></td>
								

								<td class="formth" width="22%" nowrap="nowrap"><b><label id="emp.id"
									name="emp.id" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("emp.id")%></label></b></td>
								<td class="formth" width="23%" nowrap="nowrap"><b><label id="emp.name"
									name="emp.name" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("emp.name")%></label></b></td>
								<td class="formth" width="25%" nowrap="nowrap"><b><label id="appDate"
									name="appDate" class="set" ondblclick="callShowDiv(this)">
								<%=label.get("appDate")%></label></b></td>
								<td class="formth" width="20%" nowrap="nowrap"><b>View Application</b></td>
							</tr>
							
								<%
							int cn5 = PageNoAssigned * 20 - 20;
							%>
							 
							<s:if test="listLengthAssigned">
								<s:iterator value="assignedList">
									<tr class="tableCell1">

										<td class="sortableTD" width="10%" nowrap="nowrap"><%=++cn5%>
									 
										</td>
										
											<td class="sortableTD" width="10%" nowrap="nowrap"><s:property
										value="assetId" /></td>
										
										<td class="sortableTD" width="22%" nowrap="nowrap"><s:property
											value="empToken" /></td>
										<td class="sortableTD" width="43%" nowrap="nowrap"><s:property
											value="empName" /></td>
										<td class="sortableTD" width="25%" nowrap="nowrap" align="center"><s:property
											value="assignDate1" />&nbsp;</td>
										<td class="sortableTD" width="20%" align="center"><input
											type="button" name="view_Details" class="token"
											value=" View Application "
											onclick="viewDetails('<s:property value="code"/>','<s:property value="status"/>')" /></td>

									</tr>
								</s:iterator>

							</s:if>
						 
							<s:else>
								<tr>
									<td colspan="6" class="sortableTD" align="center" width="100%">
									<font color="red">No Data to display</font></td>
								</tr>
							</s:else>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<s:if test="listLengthAssigned">
					<td align="right"><b>Total No. of Records: <s:property
						value="totalRecords" /></b></td>
				</s:if>
			</tr>
		</s:if>
		<!-- Assigned Tab Ends -->
		<tr>
			<td><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
</s:form>
<script type="text/javascript">



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
	



function callPageAssigned(id, pageImg, totalPageHid, action) {		
		
		pageNo = document.getElementById('pageNoField8').value;	
		actPage = document.getElementById('myPageAssigned').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoField8').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoField8').value = actPage;
			    document.getElementById('pageNoField8').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoField8').value=actPage;
			    document.getElementById('pageNoField8').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoField8').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoField8').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoField8').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoField8').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoField8').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoField8').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageAssigned').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 
	}
	

	 
function callPageTextAssigned(id, totalPage, action){   
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoField8').value;
		 	var actPage = document.getElementById('myPageAssigned').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoField8').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoField8').focus();
		     document.getElementById('pageNoField8').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField8').focus();
		     document.getElementById('pageNoField8').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoField8').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageAssigned').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		
	}

 




	function addapplicationFun(){
		document.getElementById('paraFrm').target="_self";
		document.getElementById('paraFrm').action="AssetEmployee_addNew.action";
		document.getElementById('paraFrm').submit();
	}
	function callForEdit(id,s) {
		//document.getElementById('paraFrm_hiddencode').value = id;
		//document.getElementById('paraFrm_hiddenStatus').value = s;
		
		
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = "AssetEmployee_callforedit.action?hiddencode="+id+"&hiddenStatus="+s;
		document.getElementById('paraFrm').submit();
	}
	function viewDetails(id,s) {
		//document.getElementById('paraFrm_hiddencode').value = id;
		//document.getElementById('paraFrm_hiddenStatus').value = s;
		 
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = "AssetEmployee_callforedit.action?hiddencode="+id+"&hiddenStatus="+s;
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