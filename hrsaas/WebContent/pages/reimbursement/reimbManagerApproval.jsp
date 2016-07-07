<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script language="JavaScript" type="text/javascript"
	src="include/javascript/sorttable.js"></script>
<s:form action="ReimbManagerApproval" validate="true" id="paraFrm"
	validate="true" theme="simple">
<s:hidden name="listType"/>
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
					<td width="93%" class="txt"><strong class="text_head">Reimbursement	Manager Approval </strong></td>
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
					<td><a href="#" onclick="setAction('pending')">Pending
					Application</a> | <a href="#" onclick="setAction('processed')">Processed Applications</a> </td>
				</tr>
			</table>
			</td>
		</tr>

		<!--------- PENDING APPLICATION SECTION BEGINS - displaying the pending applications ------->

		<%	int viewBtn = 1; 
			int totalPage = 0;
			int pageNo = 0;	%>
		<s:if test="%{listType == 'pending'}">
			<tr>
				<td>
				<table width="100%" class="formbg" border="0">
					<tr>
						<td width="30%"><b>Pending Reimbursements </b></td>
						<td id="ctrlShow" width="80%" align="right"><b>Page:</b>
									<%try{
										totalPage = (Integer) request.getAttribute("totalPage");
										pageNo = (Integer) request.getAttribute("pageNo");
									}catch(Exception e){
										e.printStackTrace();
									}
									%> <a href="#"
										onclick="callPage('1', 'F', '<%=totalPage%>', 'ReimbManagerApproval_input.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPage('P', 'P', '<%=totalPage%>', 'ReimbManagerApproval_input.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text" name="pageNoField"
										id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10"
										onkeypress="callPageText(event, '<%=totalPage%>', 'ReimbManagerApproval_input.action');return numbersOnly();" />
									of <%=totalPage%> <a href="#"
										onclick="callPage('N', 'N', '<%=totalPage%>', 'ReimbManagerApproval_input.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'ReimbManagerApproval_input.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
					</tr>
					<tr>
						<td colspan="2">
						<table width="100%" class="sorttable">
							<tr><s:hidden name="myPage" id="myPage" />
								<td class="formth" width="5%"><b><label class="set"
									name="srno" id="srno" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></b>
								</td>
								
								<td class="formth" width="13%"><b><label class="set"
									name="applId" id="applId" ondblclick="callShowDiv(this);"><%=label.get("applId")%></label></b>
								</td>
								
								<td class="formth"><b><label class="set"
									name="applDate" id="applDate"
									ondblclick="callShowDiv(this);"><%=label.get("applDate")%></label></b>
								</td>
								<td class="formth"><b><label class="set"
									name="empName" id="empName"
									ondblclick="callShowDiv(this);"><%=label.get("empName")%></label></b>
								</td>
								<td class="formth"><b><label class="set" name="head"
									id="head" ondblclick="callShowDiv(this);"><%=label.get("head")%></label></b>
								</td>
								<td class="formth"><b><label class="set"
									name="claimAmt" id="claimAmt"
									ondblclick="callShowDiv(this);"><%=label.get("claimAmt")%></label></b>
								</td>
								<td class="formth">View Reimbursements</td>
							</tr>
							<%	int pending = pageNo * 20 - 20;	%>
							<s:iterator value="reimbursementIteratorlist">
								<tr>
									<td class="sortableTD"><%++pending;%><%=pending%><s:hidden
										name="srNo" />
									<s:hidden name="claimIdItt" />
										</td>
									<td class="sortableTD" align="left"><s:property
										value="applicationIdItt" />&nbsp;</td>
									<td class="sortableTD" align="left"><s:property
										value="applicationDateItt" />&nbsp;</td>
									<td class="sortableTD" align="center"><s:property
										value="employeeNameItt" />&nbsp;</td>
									<td class="sortableTD" align="center"><s:property
										value="headItt" />&nbsp;</td>
									<td class="sortableTD" align="center"><s:property
									value="claimAmountItt" />&nbsp;</td>
									<td align="center" class="sortableTD"><input type="button"
										name="reimbursementStatusView" value="View Details" class="token"
										id="<%="reimbursementStatusView"+viewBtn%>" onclick="viewDetails('<s:property value="claimIdItt" />')" />&nbsp;</td>
								</tr>
							</s:iterator>
							<%
									if (pending == 0) {
									%>
									<tr align="center">
										<td colspan="7" class="sortableTD" width="100%"><font
											color="red">No Data to display</font></td>
									</tr>
									<%
									} else {
										
									}
									%>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			</s:if>
			<!-------------------------------------- PENDING LIST ENDS ------------------------------->
		<!-------------------------------------- PROCESSED LIST BEGINS ------------------------------->
	
		<s:if test="%{listType == 'processed'}">
			<tr>
				<td>
				<table width="100%" class="formbg" border="0">
					<tr>
						<td width="30%"><b>Processed Reimbursements </b></td>
						<td id="ctrlShow" width="80%" align="right"><b>Page:</b>
									<%try{
										totalPage = (Integer) request.getAttribute("totalPage");
										pageNo = (Integer) request.getAttribute("pageNo");}catch(Exception e){
											e.printStackTrace();
										}
									%> <a href="#"
										onclick="callPage('1', 'F', '<%=totalPage%>', 'ReimbManagerApproval_getAllReimbursements.action?status=processed');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPage('P', 'P', '<%=totalPage%>', 'ReimbManagerApproval_getAllReimbursements.action?status=processed');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text" name="pageNoField"
										id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10"
										onkeypress="callPageText(event, '<%=totalPage%>', 'ReimbManagerApproval_getAllReimbursements.action?status=processed');return numbersOnly();" />
									of <%=totalPage%> <a href="#"
										onclick="callPage('N', 'N', '<%=totalPage%>', 'ReimbManagerApproval_getAllReimbursements.action?status=processed')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'ReimbManagerApproval_getAllReimbursements.action?status=processed');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
					</tr>
					<tr>
						<td colspan="2">
						<table width="100%" class="sorttable">
							<tr><s:hidden name="myPage" id="myPage" />
								<td class="formth" width="5%"><b><label class="set"
									name="srno" id="srno1" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></b>
								</td>
								
								<td class="formth" width="13%"><b><label class="set"
									name="applId" id="applId1" ondblclick="callShowDiv(this);"><%=label.get("applId")%></label></b>
								</td>
								
								<td class="formth"><b><label class="set"
									name="applDate" id="applDate1"
									ondblclick="callShowDiv(this);"><%=label.get("applDate")%></label></b>
								</td>
								<td class="formth"><b><label class="set"
									name="empName" id="empName1"
									ondblclick="callShowDiv(this);"><%=label.get("empName")%></label></b>
								</td>
								<td class="formth"><b><label class="set" name="head"
									id="head1" ondblclick="callShowDiv(this);"><%=label.get("head")%></label></b>
								</td>
								<td class="formth"><b><label class="set"
									name="claimAmt" id="claimAmt1"
									ondblclick="callShowDiv(this);"><%=label.get("claimAmt")%></label></b>
								</td>
									<td class="formth"><b><label class="set"
									name="status" id="status1"
									ondblclick="callShowDiv(this);"><%=label.get("status")%></label></b>
								</td>
								<td class="formth">View Reimbursements</td>
							</tr>
							<%	int processed = pageNo * 20 - 20;	%>
							
							<s:iterator value="reimbursementIteratorlist">
								<tr>
									<td class="sortableTD"><%++processed;%><%=processed%><s:hidden
										name="srNo" />
										<s:hidden name="claimIdItt" /></td>
									<td class="sortableTD" align="left"><s:property
										value="applicationIdItt" />&nbsp;</td>
									<td class="sortableTD" align="left"><s:property
										value="applicationDateItt" />&nbsp;</td>
									<td class="sortableTD" align="center"><s:property
										value="employeeNameItt" />&nbsp;</td>
									<td class="sortableTD" align="center"><s:property
										value="headItt" />&nbsp;</td>
									<td class="sortableTD" align="center"><s:property
										value="claimAmountItt" />&nbsp;</td>
									<td class="sortableTD" align="center"><s:property
										value="decodedStatusItt" />&nbsp;</td>
									<td align="center" class="sortableTD"><input type="button"
										name="reimbursementStatusView" value="View Details" class="token"
										id="<%="reimbursementStatusView"+viewBtn%>" onclick="viewDetails('<s:property value="claimIdItt" />')" />&nbsp;</td>
								</tr>
							</s:iterator>
							<%
									if (processed == 0) {
									%>
									<tr align="center">
										<td colspan="8" class="sortableTD" width="100%"><font
											color="red">No Data to display</font></td>
									</tr>
									<%
									} else {
										
									}
									%>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			</s:if>
			<!-------------------------------------- PROCESSED LIST ENDS ------------------------------->
		</table>
	</table>
</s:form>

<script>

	function setAction(reimbList){
		document.getElementById('paraFrm').action='ReimbManagerApproval_getAllReimbursements.action?status='+reimbList; 
		document.getElementById('paraFrm').submit();
	}
	
	function viewDetails(claimId){
    	document.getElementById('paraFrm').action='ReimbManagerApproval_viewReimbursement.action?claimId='+claimId;
		document.getElementById('paraFrm').submit();	
    }
</script>







		