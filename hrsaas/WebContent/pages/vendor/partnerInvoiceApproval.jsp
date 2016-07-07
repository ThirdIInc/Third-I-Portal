<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="PartnerInvoiceApproval" id="paraFrm" validate="true"
	theme="simple">
	<s:hidden name="myPage" />
	<table width="100%" cellpadding="0" cellspacing="0" border="0"
		align="right" class="formbg">
		<tr>
			<td class="txt" valign="bottom">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Partner
					Invoice Approval </strong></td>
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
			<table width="100%">
				<tr>
					<td><a href="#" class="contlink" onclick="setList('p')">Pending
					Application</a> | <a href="#" class="contlink" onclick="setList('a')">Approved
					List</a> | <a href="#" class="contlink" onclick="setList('r')">Rejected
					List</a></td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- Pending Application List Start -->
		<s:if test="%{listType == 'pending'}">
			<tr>
				<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td><b>Pending List</b></td>
					</tr>
					<%
								int totalPendingPage = 0;
								int pendingPageNo = 0;
							%>
					<s:if test="pendingAppLength">
						<tr>
							<td id="ctrlShow" width="100%" align="right"><b>Page:</b> <%
										pendingPageNo = (Integer) request.getAttribute("PageNo");
										totalPendingPage = (Integer)request.getAttribute("totalPage");
									%> <a href="#"
								onclick="callPage('1', 'F', '<%=totalPendingPage%>', 'PartnerInvoiceApproval_input.action');">
							<img title="First Page" src="../pages/common/img/first.gif"
								width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPage('P', 'P', '<%=totalPendingPage%>', 'PartnerInvoiceApproval_input.action');">
							<img title="Previous Page" src="../pages/common/img/previous.gif"
								width="10" height="10" class="iconImage" /> </a> <input type="text"
								name="pageNoField" id="pageNoField" size="3"
								value="<%=pendingPageNo%>" maxlength="10"
								onkeypress="callPageText(event, '<%=totalPendingPage%>', 'PartnerInvoiceApproval_input.action');return numbersOnly();" />
							of <%=totalPendingPage%> <a href="#"
								onclick="callPage('N', 'N', '<%=totalPendingPage%>', 'PartnerInvoiceApproval_input.action')">
							<img title="Next Page" src="../pages/common/img/next.gif"
								class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPage('<%=totalPendingPage%>', 'L', '<%=totalPendingPage%>', 'PartnerInvoiceApproval_input.action');">
							<img title="Last Page" src="../pages/common/img/last.gif"
								width="10" height="10" class="iconImage" /> </a></td>
						</tr>
					</s:if>
					<tr>
						<td>
						<table width="100%" class="sortable">
							<tr>
								<td>
								<table width="100%" class="sortable">
									<tr>
										<td class="formth" width="5%"><b><label class="set"
											name="srno" id="srno2" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></b>
										</td>
										<td class="formth" width="15%"><b><label class="set"
											name="partner.id" id="partner.id2"
											ondblclick="callShowDiv(this);"><%=label.get("partner.id")%></label></b>
										</td>
										<td class="formth" width="35%"><b><label class="set"
											name="partner.name" id="partner.name2"
											ondblclick="callShowDiv(this);"><%=label.get("partner.name")%></label></b>
										</td>
										<td class="formth" width="15%"><b><label class="set"
											name="invoicedate" id="invoicedate1"
											ondblclick="callShowDiv(this);"><%=label.get("invoicedate")%></label></b>
										</td>
										<td class="formth" width="10%"><b><label class="set"
											name="status" id="status1" ondblclick="callShowDiv(this);"><%=label.get("status")%></label></b>
										</td>
										<td class="formth" width="20%"><b>View/Approve
										Application</b></td>
									</tr>
									<s:if test="pendingAppLength">
										<%
												int cn = pendingPageNo * 20 - 20;
												%>
										<s:iterator value="appPendingList">
											<tr>
												<td class="sortableTD" width="5%" align="center"><%=++cn%></td>
												<td class="sortableTD" width="15%"><s:property
													value="partnerCodeApp" /></td>
												<td class="sortableTD" width="35%"><s:property
													value="invoicePartnerNameApp" /><s:hidden
													name="invoiceIDApp" /></td>
												<td class="sortableTD" width="15%" align="center"><s:property
													value="invoiceDateApp" /></td>
												<td class="sortableTD" width="10%" align="center"><s:property
													value="invoiceAppStatusApp" /><s:hidden
													name="hiddenStatus" /></td>
												<td class="sortableTD" width="20%" align="center"><input
													type="button" name="view_Details" class="token"
													value=" View/Approve Application "
													onclick=" viewDetails('<s:property value="invoiceIDApp" />','<s:property
																		value="invoiceAppStatusApp" />')" /></td>
											</tr>
										</s:iterator>
									</s:if>
									<s:if test="pendingAppLength"></s:if>
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
		<!-- Pending Application List End -->
		<!-- Approved Application List -->
		<s:if test="%{listType == 'Approved'}">
			<tr>
				<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td><b>Approved Applications List</b></td>
					</tr>
					<%
						int totalPageApproved = 0;
						int approvedPageNo = 0;
					 %>
					<s:if test="approvedLength">
						<tr>
							<td id="ctrlShow" width="100%" align="right"><b>Page:</b> <%
 								totalPageApproved = (Integer) request.getAttribute("totalPageApproved");
								approvedPageNo = (Integer) request.getAttribute("PageNoApproved");
 									%> <a href="#"
								onclick="callPageApproved('1', 'F', '<%=totalPageApproved%>', 'PartnerInvoiceApproval_getApproverList.action');">
							<img title="First Page" src="../pages/common/img/first.gif"
								width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPageApproved('P', 'P', '<%=totalPageApproved%>', 'PartnerInvoiceApproval_getApproverList.action');">
							<img title="Previous Page" src="../pages/common/img/previous.gif"
								width="10" height="10" class="iconImage" /> </a> <input type="text"
								name="pageNoField2" id="pageNoField2" size="3"
								value="<%=approvedPageNo%>" maxlength="10"
								onkeypress="callPageTextApprove(event, '<%=totalPageApproved%>', 'PartnerInvoiceApproval_getApproverList.action');return numbersOnly();" />
							of <%=totalPageApproved%> <a href="#"
								onclick="callPageApproved('N', 'N', '<%=totalPageApproved%>', 'PartnerInvoiceApproval_getApproverList.action')">
							<img title="Next Page" src="../pages/common/img/next.gif"
								class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPageApproved('<%=totalPageApproved%>', 'L', '<%=totalPageApproved%>', 'PartnerInvoiceApproval_getApproverList.action');">
							<img title="Last Page" src="../pages/common/img/last.gif"
								width="10" height="10" class="iconImage" /> </a></td>
						</tr>
					</s:if>
					<tr>
						<td>
						<table width="100%" class="sorttable">
							<tr>
								<td class="formth" width="5%"><b><label class="set"
									name="srno" id="srno2" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></b>
								</td>
								<td class="formth" width="15%"><b><label class="set"
									name="partner.id" id="partner.id2"
									ondblclick="callShowDiv(this);"><%=label.get("partner.id")%></label></b>
								</td>
								<td class="formth" width="40%"><b><label class="set"
									name="partner.name" id="partner.name2"
									ondblclick="callShowDiv(this);"><%=label.get("partner.name")%></label></b>
								</td>
								<td class="formth" width="20%"><b><label class="set"
									name="invoicedate" id="invoicedate1"
									ondblclick="callShowDiv(this);"><%=label.get("invoicedate")%></label></b>
								</td>
								<td class="formth" width="20%"><b><label class="set"
									name="status" id="status1" ondblclick="callShowDiv(this);"><%=label.get("status")%></label></b>
								</td>
								<td class="formth" width="20%"><b>View Application</b></td>
							</tr>
							<%
							 int cn2 = approvedPageNo * 20 - 20;
							%>
							<s:iterator value="approvedListApp">
								<tr>
									<td class="sortableTD" width="5%" align="center"><%=++cn2%></td>
									<td class="sortableTD" width="15%"><s:property
										value="partnerCodeApp" /></td>
									<td class="sortableTD" width="20%"><s:property
										value="invoicePartnerNameApp" /><s:hidden name="invoiceIDApp" /></td>
									<td class="sortableTD" width="20%" align="center"><s:property
										value="invoiceDateApp" /></td>
									<td class="sortableTD" width="20%" align="center"><s:property
										value="invoiceAppStatusApp" /><s:hidden name="hiddenStatus" /></td>
									<td class="sortableTD" width="20%" align="center"><input
										type="button" name="view_Details" class="token"
										value=" View Application "
										onclick=" viewDetails('<s:property
												value="invoiceIDApp" />','<s:property
												value="invoiceAppStatusApp" />')" /></td>
								</tr>
							</s:iterator>
							<s:if test="approvedLength"></s:if>
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
		</s:if>
		<!-- Rejected Application List -->
		<s:if test="%{listType == 'Rejected'}">
			<tr>
				<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td><b>Rejected Applications List</b></td>
					</tr>
					<%
						int totalPageReject = 0;
						int PageNoReject = 0;
					 %>
					<s:if test="rejectedLength">
						<tr>
							<td id="ctrlShow" width="100%" align="right"><b>Page:</b> <%
							totalPageReject = (Integer) request.getAttribute("totalPageReject");
							PageNoReject = (Integer) request.getAttribute("PageNoReject");
 									%> <a href="#"
								onclick="callPageRejected('1', 'F', '<%=totalPageReject%>', 'PartnerInvoiceApproval_getRejectList.action');">
							<img title="First Page" src="../pages/common/img/first.gif"
								width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPageRejected('P', 'P', '<%=totalPageReject%>', 'PartnerInvoiceApproval_getRejectList.action');">
							<img title="Previous Page" src="../pages/common/img/previous.gif"
								width="10" height="10" class="iconImage" /> </a> <input type="text"
								name="pageNoField2" id="pageNoField2" size="3"
								value="<%=PageNoReject%>" maxlength="10"
								onkeypress="callPageTextReject(event, '<%=totalPageReject%>', 'PartnerInvoiceApproval_getRejectList.action');return numbersOnly();" />
							of <%=totalPageReject%> <a href="#"
								onclick="callPageRejected('N', 'N', '<%=totalPageReject%>', 'PartnerInvoiceApproval_getRejectList.action')">
							<img title="Next Page" src="../pages/common/img/next.gif"
								class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPageRejected('<%=totalPageReject%>', 'L', '<%=totalPageReject%>', 'PartnerInvoiceApproval_getRejectList.action');">
							<img title="Last Page" src="../pages/common/img/last.gif"
								width="10" height="10" class="iconImage" /> </a></td>
						</tr>
					</s:if>
					<tr>
						<td>
						<table width="100%" class="sortable">
							<tr>
								<td class="formth" width="5%"><b><label class="set"
									name="srno" id="srno2" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></b>
								</td>
								<td class="formth" width="15%"><b><label class="set"
									name="partner.id" id="partner.id2"
									ondblclick="callShowDiv(this);"><%=label.get("partner.id")%></label></b>
								</td>
								<td class="formth" width="40%"><b><label class="set"
									name="partner.name" id="partner.name2"
									ondblclick="callShowDiv(this);"><%=label.get("partner.name")%></label></b>
								</td>
								<td class="formth" width="20%"><b><label class="set"
									name="invoicedate" id="invoicedate1"
									ondblclick="callShowDiv(this);"><%=label.get("invoicedate")%></label></b>
								</td>
								<td class="formth" width="20%"><b><label class="set"
									name="status" id="status1" ondblclick="callShowDiv(this);"><%=label.get("status")%></label></b>
								</td>
								<td class="formth" width="20%"><b>View Application</b></td>
							</tr>
							<%							 
							  int cn3 = PageNoReject * 20 - 20;
							%>
							<s:iterator value="rejectedListApp">
								<tr>
									<td class="sortableTD" width="5%" align="center"><%=++cn3%></td>
									<td class="sortableTD" width="15%"><s:property
										value="partnerCodeApp" /></td>
									<td class="sortableTD" width="20%"><s:property
										value="invoicePartnerNameApp" /><s:hidden name="invoiceIDApp" /></td>
									<td class="sortableTD" width="20%" align="center"><s:property
										value="invoiceDateApp" /></td>
									<td class="sortableTD" width="20%" align="center"><s:property
										value="invoiceAppStatusApp" /><s:hidden name="hiddenStatus" /></td>
									<td class="sortableTD" width="20%" align="center"><input
										type="button" name="view_Details" class="token"
										value=" View Application "
										onclick=" viewDetails('<s:property
												value="invoiceIDApp" />','<s:property
												value="invoiceAppStatusApp" />')" /></td>
								</tr>
							</s:iterator>
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
		</s:if>
	</table>
</s:form>

<script>
function setList(listType){
        
 		if (listType=='p'){
				document.getElementById('paraFrm').action='PartnerInvoiceApproval_input.action';
				document.getElementById('paraFrm').submit();
		}
		else if(listType=='a'){
		  document.getElementById('paraFrm').action='PartnerInvoiceApproval_getApproverList.action';
		  document.getElementById('paraFrm').submit();
		}		
		else {
		   document.getElementById('paraFrm').action='PartnerInvoiceApproval_getRejectList.action';
		   document.getElementById('paraFrm').submit();		       
		  } 				
}
			
		    
function viewDetails(invoiceID,status){
		      document.getElementById('paraFrm').action='PartnerInvoice_retriveForApproval.action?InvoiceID='+invoiceID+'&invoiceStatus='+status;
		      document.getElementById('paraFrm').submit();		     
		    }
</script>