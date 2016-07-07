<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="PartnerInvoice" id="paraFrm" validate="true"
	theme="simple">
	<s:hidden name="invoiceID" />
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
					Invoice Creation </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td><s:submit name="addNew" value=" AddNew"
				onclick=" addNewFun();" /></td>
		</tr>
		<tr>
			<td>
			<table width="100%">
				<tr>
					<td><a href="#" class="contlink" onclick="setList('all')">Pending
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
				<td>
				<table width="100%" class="formbg">
					<tr>
						<td>Draft</td>
					</tr>
					<%
						int totalDraftPage = 0;
						int draftPageNo = 0;
					%>
					<s:if test="draftLength">
						<tr>
							<td id="ctrlShow" width="100%" align="right"><b>Page:</b> <%
										totalDraftPage = (Integer) request.getAttribute("totalPage");
										draftPageNo = (Integer) request.getAttribute("PageNo");
									%> <a href="#"
								onclick="callPage('1', 'F', '<%=totalDraftPage%>', 'PartnerInvoice_input.action');">
							<img title="First Page" src="../pages/common/img/first.gif"
								width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPage('P', 'P', '<%=totalDraftPage%>', 'PartnerInvoice_input.action');">
							<img title="Previous Page" src="../pages/common/img/previous.gif"
								width="10" height="10" class="iconImage" /> </a> <input type="text"
								name="pageNoField" id="pageNoField" size="3"
								value="<%=draftPageNo%>" maxlength="10"
								onkeypress="callPageText(event, '<%=totalDraftPage%>', 'PartnerInvoice_input.action');return numbersOnly();" />
							of <%=totalDraftPage%> <a href="#"
								onclick="callPage('N', 'N', '<%=totalDraftPage%>', 'PartnerInvoice_input.action')">
							<img title="Next Page" src="../pages/common/img/next.gif"
								class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPage('<%=totalDraftPage%>', 'L', '<%=totalDraftPage%>', 'PartnerInvoice_input.action');">
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
								<td class="formth" width="20%"><b>Edit Application</b></td>
							</tr>
							<s:if test="draftLength">
								<%
										int cn = draftPageNo * 20 - 20;
										%>
								<s:iterator value="draftList">
									<tr>
										<td class="sortableTD" width="5%" align="center"><%=++cn%></td>
										<td class="sortableTD" width="15%"><s:property
											value="draftPartnerCode" /></td>
										<td class="sortableTD" width="20%"><s:property
											value="invoicePartnerName" /><s:hidden name="draftInvoiceID" /></td>
										<td class="sortableTD" width="20%" align="center"><s:property
											value="invoiceDate" /></td>
										<td class="sortableTD" width="20%" align="center"><s:property
											value="invoiceAppStatus" /><s:hidden name="hiddenStatus" /></td>
										<td class="sortableTD" width="20%" align="center"><input
											type="button" name="edit_Details" class="token"
											value=" Edit Application "
											onclick=" viewDetails('<s:property
												value="draftInvoiceID" />','<s:property
												value="invoiceAppStatus" />')" /></td>
									</tr>
								</s:iterator>
							</s:if>
							<s:if test="draftLength"></s:if>
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
			<tr>
				<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td><b>Application In Process</b></td>
					</tr>
					<%
						int totalPendingPage = 0;
						int pendingPageNo = 0;
					%>
					<s:if test="pendingPartnerLen">
						<tr>
							<td id="ctrlShow" width="100%" align="right"><b>Page:</b> <%
										totalPendingPage = (Integer) request.getAttribute("totalPenPage");
										pendingPageNo = (Integer) request.getAttribute("penPageNo");
									%> <a href="#"
								onclick="callPage('1', 'F', '<%=totalPendingPage%>', 'PartnerInvoice_input.action');">
							<img title="First Page" src="../pages/common/img/first.gif"
								width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPage('P', 'P', '<%=totalPendingPage%>', 'PartnerInvoice_input.action');">
							<img title="Previous Page" src="../pages/common/img/previous.gif"
								width="10" height="10" class="iconImage" /> </a> <input type="text"
								name="pageNoField" id="pageNoField" size="3"
								value="<%=pendingPageNo%>" maxlength="10"
								onkeypress="callPageText(event, '<%=totalPendingPage%>', 'PartnerInvoice_input.action');return numbersOnly();" />
							of <%=totalPendingPage%> <a href="#"
								onclick="callPage('N', 'N', '<%=totalPendingPage%>', 'PartnerInvoice_input.action')">
							<img title="Next Page" src="../pages/common/img/next.gif"
								class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPage('<%=totalPendingPage%>', 'L', '<%=totalPendingPage%>', 'PartnerInvoice_input.action');">
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
									<s:if test="pendingPartnerLen">
										<%
										int cn2 = pendingPageNo * 20 - 20;
										%>
										<s:iterator value="pendingList">
											<tr>
												<td class="sortableTD" width="5%" align="center"><%=++cn2%></td>
												<td class="sortableTD" width="15%"><s:property
													value="pendingPartnerCode" /></td>
												<td class="sortableTD" width="20%"><s:property
													value="invoicePartnerName" /></td>
												<td class="sortableTD" width="20%" align="center"><s:property
													value="invoiceDate" /></td>
												<td class="sortableTD" width="20%" align="center"><s:property
													value="invoiceAppStatus" /><s:hidden name="hiddenStatus" /></td>
												<td class="sortableTD" width="20%" align="center"><input
													type="button" name="view_Details" class="token"
													value=" View Application "
													onclick=" viewDetails('<s:property
												value="pendingInvoiceID" />','<s:property
												value="invoiceAppStatus" />')" /></td>
											</tr>
										</s:iterator>
									</s:if>
									<s:if test="pendingPartnerLen"></s:if>
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
						int totalApprovePage = 0;
						int approvedPageNo = 0;
					%>
					<s:if test="approvePartnerLen">
						<tr>
							<td id="ctrlShow" width="100%" align="right"><b>Page:</b> <%
 									totalApprovePage = (Integer) request.getAttribute("totalAppPage");
 									approvedPageNo = (Integer) request.getAttribute("appPageNo");
 								 %> <a href="#"
								onclick="callPage('1', 'F', '<%=totalApprovePage%>', 'PartnerInvoice_getApproverList.action');">
							<img title="First Page" src="../pages/common/img/first.gif"
								width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPage('P', 'P', '<%=totalApprovePage%>', 'PartnerInvoice_getApproverList.action');">
							<img title="Previous Page" src="../pages/common/img/previous.gif"
								width="10" height="10" class="iconImage" /> </a> <input type="text"
								name="pageNoField" id="pageNoField" size="3"
								value="<%=approvedPageNo%>" maxlength="10"
								onkeypress="callPageText(event, '<%=totalApprovePage%>', 'PartnerInvoice_getApproverList.action');return numbersOnly();" />
							of <%=totalApprovePage%> <a href="#"
								onclick="callPage('N', 'N', '<%=totalApprovePage%>', 'PartnerInvoice_getApproverList.action')">
							<img title="Next Page" src="../pages/common/img/next.gif"
								class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPage('<%=totalApprovePage%>', 'L', '<%=totalApprovePage%>', 'PartnerInvoice_getApproverList.action');">
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
							<s:if test="approvePartnerLen">
								<%
								int cn3 = approvedPageNo * 20 - 20;
								%>
								<s:iterator value="approvedList">
									<tr>
										<td class="sortableTD" width="5%" align="center"><%=++cn3%></td>
										<td class="sortableTD" width="15%"><s:property
											value="partnerCode" /></td>
										<td class="sortableTD" width="20%"><s:property
											value="invoicePartnerName" /></td>
										<td class="sortableTD" width="20%" align="center"><s:property
											value="invoiceDate" /></td>
										<td class="sortableTD" width="20%" align="center"><s:property
											value="invoiceAppStatus" /><s:hidden name="hiddenStatus" /></td>
										<td class="sortableTD" width="20%" align="center"><input
											type="button" name="view_Details" class="token"
											value=" View Application "
											onclick=" viewDetails('<s:property
												value="invoiceID" />','<s:property
												value="invoiceAppStatus" />')" /></td>
									</tr>
								</s:iterator>
							</s:if>
							<s:if test="approvePartnerLen"></s:if>
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
						int totalRejectedPage = 0;
						int rejectedPageNo = 0;
					%>
					<s:if test="rejectPartnerLen">
						<tr>
							<td id="ctrlShow" width="100%" align="right"><b>Page:</b> <%
							totalRejectedPage = (Integer) request.getAttribute("totalRejPage");
							rejectedPageNo = (Integer) request.getAttribute("rejPageNo");
 								 %> <a href="#"
								onclick="callPage('1', 'F', '<%=totalRejectedPage%>', 'PartnerInvoice_getRejectList.action');">
							<img title="First Page" src="../pages/common/img/first.gif"
								width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPage('P', 'P', '<%=totalRejectedPage%>', 'PartnerInvoice_getRejectList.action');">
							<img title="Previous Page" src="../pages/common/img/previous.gif"
								width="10" height="10" class="iconImage" /> </a> <input type="text"
								name="pageNoField" id="pageNoField" size="3"
								value="<%=rejectedPageNo%>" maxlength="10"
								onkeypress="callPageText(event, '<%=totalRejectedPage%>', 'PartnerInvoice_getRejectList.action');return numbersOnly();" />
							of <%=totalRejectedPage%> <a href="#"
								onclick="callPage('N', 'N', '<%=totalRejectedPage%>', 'PartnerInvoice_getRejectList.action')">
							<img title="Next Page" src="../pages/common/img/next.gif"
								class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPage('<%=totalRejectedPage%>', 'L', '<%=totalRejectedPage%>', 'PartnerInvoice_getRejectList.action');">
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
							<s:if test="rejectPartnerLen">
								<%
								int cn4 = rejectedPageNo * 20 - 20;
								%>
								<s:iterator value="rejectedList">
									<tr>
										<td class="sortableTD" width="5%" align="center"><%=++cn4%></td>
										<td class="sortableTD" width="15%"><s:property
											value="partnerCode" /></td>
										<td class="sortableTD" width="20%"><s:property
											value="invoicePartnerName" /></td>
										<td class="sortableTD" width="20%" align="center"><s:property
											value="invoiceDate" /></td>
										<td class="sortableTD" width="20%" align="center"><s:property
											value="invoiceAppStatus" /><s:hidden name="hiddenStatus" /></td>
										<td class="sortableTD" width="20%" align="center"><input
											type="button" name="view_Details" class="token"
											value=" View Application "
											onclick=" viewDetails('<s:property
												value="invoiceID" />','<s:property
												value="invoiceAppStatus" />')" /></td>
									</tr>
								</s:iterator>
							</s:if>
							<s:if test="rejectPartnerLen"></s:if>
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

function addNewFun(){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'PartnerInvoice_addNew.action';
		document.getElementById('paraFrm').submit();
}

function setList(listType){
 		if (listType=='all'){
				document.getElementById('paraFrm').action='PartnerInvoice_input.action?status=all';
				document.getElementById('paraFrm').submit();
		}
		else if(listType=='a'){
		  document.getElementById('paraFrm').action='PartnerInvoice_getApproverList.action';
		  document.getElementById('paraFrm').submit();
		}		
		else {
		   document.getElementById('paraFrm').action='PartnerInvoice_getRejectList.action';
		   document.getElementById('paraFrm').submit();		       
		  } 				
}	    
function viewDetails(invoiceID,status){
		      document.getElementById('paraFrm').action='PartnerInvoice_retrieveDetails.action?InvoiceID='+invoiceID+'&invoiceStatus='+status;
		      document.getElementById('paraFrm').submit();		     
		    }
</script>