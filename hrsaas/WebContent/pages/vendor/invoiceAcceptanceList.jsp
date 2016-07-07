<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="InvoiceAcceptance" id="paraFrm" validate="true"
	theme="simple">
	<s:hidden name="invoiceIDIA" />
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
					Invoice Acceptance </strong></td>
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
					Application</a><!-- | <a href="#" class="contlink" onclick="setList('q')">Acceptance
					List</a>--></td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- Pending Application List Start -->
		<s:if test="%{listType == 'pendingList'}">

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
					<s:if test="pendingAccLength">
						<tr>
							<td id="ctrlShow" width="100%" align="right"><b>Page:</b> <%
										totalPendingPage = (Integer) request.getAttribute("totalPage");
										pendingPageNo = (Integer) request.getAttribute("PageNo");
									%> <a href="#"
								onclick="callPage('1', 'F', '<%=totalPendingPage%>', 'InvoiceAcceptance_input.action');">
							<img title="First Page" src="../pages/common/img/first.gif"
								width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPage('P', 'P', '<%=totalPendingPage%>', 'InvoiceAcceptance_input.action');">
							<img title="Previous Page" src="../pages/common/img/previous.gif"
								width="10" height="10" class="iconImage" /> </a> <input type="text"
								name="pageNoField" id="pageNoField" size="3"
								value="<%=pendingPageNo%>" maxlength="10"
								onkeypress="callPageText(event, '<%=totalPendingPage%>', 'InvoiceAcceptance_input.action');return numbersOnly();" />
							of <%=totalPendingPage%> <a href="#"
								onclick="callPage('N', 'N', '<%=totalPendingPage%>', 'InvoiceAcceptance_input.action')">
							<img title="Next Page" src="../pages/common/img/next.gif"
								class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPage('<%=totalPendingPage%>', 'L', '<%=totalPendingPage%>', 'InvoiceAcceptance_input.action');">
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
										<td class="formth" width="20%"><b>View Application</b></td>
									</tr>
									<s:if test="pendingAccLength">
										<%
										int cn = pendingPageNo * 20 - 20;
										%>
										<s:iterator value="pendingList">
											<tr>
												<td class="sortableTD" width="5%" align="center"><%=++cn%></td>
												<td class="sortableTD" width="15%"><s:property
													value="partnerCodeIA" /></td>
												<td class="sortableTD" width="35%"><s:property
													value="partnerNameIA" /></td>
												<td class="sortableTD" width="15%" align="center"><s:property
													value="invoiceDateIA" /></td>
												<td class="sortableTD" width="10%" align="center"><s:property
													value="invoiceStatusIA" /><s:hidden name="hiddenStatus" /></td>
												<td class="sortableTD" width="20%" align="center"><input
													type="button" name="view_Details" class="token"
													value=" View/Accept Application "
													onclick=" viewDetails('<s:property
												value="invoiceIDIA" />','<s:property
												value="invoiceStatusIA" />')" /></td>
											</tr>

										</s:iterator>
									</s:if>
									<s:if test="pendingAccLength"></s:if>
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
		<!-- Accepted Application List-->
	</table>
</s:form>

<script>
function setList(listType){
 		if (listType=='p'){
				document.getElementById('paraFrm').action='InvoiceAcceptance_input.action';
				document.getElementById('paraFrm').submit();
		}
		/*else if(listType=='q'){
		 document.getElementById('paraFrm').action='InvoiceAcceptance_getAcceptList.action';
		  document.getElementById('paraFrm').submit();
		}*/		
	 		
}
			
		    
function viewDetails(invoiceID,status){
		      document.getElementById('paraFrm').action='InvoiceAcceptance_retriveForAcceptance.action?InvoiceID='+invoiceID;
		      document.getElementById('paraFrm').submit();		     
		    }
</script>