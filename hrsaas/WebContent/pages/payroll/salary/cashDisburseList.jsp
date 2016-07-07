<!-- @author: REEBA JOSEPH 15 NOVEMBER 2010 -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="CashDisburse" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Reimbursement Claim
					Disbursement</strong></td>
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
					<script>
				    function setAction(listType){
					    if(listType=="p"){
					      	document.getElementById('paraFrm').action='CashDisburse_input.action';
					      	document.getElementById('paraFrm').submit();
					    }
					    if(listType=="c"){
					      	document.getElementById('paraFrm').action='CashDisburse_getClosedList.action';
					      	document.getElementById('paraFrm').submit();
					    }
					     
				    }
				   </script>
					<td><a href="#" onclick="setAction('p')">Pending
					Disbursement</a> | <a href="#" onclick="setAction('c')">Closed
					Disbursement</a></td>
				</tr>
			</table>
			</td>
		</tr>

		<s:if test="%{listType == 'pending'}">
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td><b>Pending Disbursements</b></td>
					</tr>
					<tr>
						<td class="formtext">
						<table width="100%" border="0" cellpadding="1" cellspacing="1">
							<tr>
								<td width="10%" class="formth"><label class="set"
									id="employee.id" name="employee.id"
									ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></td>
								<td width="30%" class="formth"><label class="set"
									id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
								<td width="10%" class="formth"><label class="set"
									id="claimed.date" name="claimed.date"
									ondblclick="callShowDiv(this);"><%=label.get("claimed.date")%></label></td>
								<td width="30%" class="formth"><label class="set"
									id="claimed.particulars" name="claimed.particulars"
									ondblclick="callShowDiv(this);"><%=label.get("claimed.particulars")%></label></td>
								<td width="15%" class="formth"><label class="set"
									id="claimed.amount" name="claimed.amount"
									ondblclick="callShowDiv(this);"><%=label.get("claimed.amount")%></label></td>
								<td width="15%" class="formth">Disburse</td>

							</tr>

							<s:iterator value="disburseList" status="stat">

								<%!int p = 0;%>
								<tr>
									<td width="10%" class="sortableTD" width="30%"><s:hidden
										name="empId" /><s:property value="empToken" /><s:hidden
										name="itClmAppId" /></td>
									<td width="30%" class="sortableTD" width="25%"><s:property
										value="empName" /></td>
									<td width="10%" class="sortableTD" width="25%"><s:property
										value="claimDate" /></td>
									<td width="30%" class="sortableTD" width="25%"><s:property
										value="claimParticulars" /></td>
									<td width="15%" class="sortableTD" width="25%" align="right"><s:property
										value="claimAmt" /></td>
									<td width="15%" class="sortableTD" width="25%"><input
										type="button" class="token" value="Disburse"
										onclick="viewDetails('<s:property value="itClmAppId"/>',
									'<s:property value="status"/>','<s:property value="empId"/>')"></td>

								</tr>
								<%
									p++;
								%>
							</s:iterator>
						</table>
						</td>
					</tr>
				</table>


				</td>
			</tr>

		</s:if>

		<s:if test="%{listType == 'closed'}">
			<tr>
				<td>
				<table width="100%" border="0">
					<%
						int totalPage = 0;
								int pageNo = 0;
					%>
					<s:if test="closedLength">
						<tr>
							<td width="30%"><b>Closed Disbursements</b></td>
							<td id="ctrlShow" width="70%" align="right"><b>Page:</b> <%
 	totalPage = (Integer) request.getAttribute("totalPage");
 				pageNo = (Integer) request.getAttribute("pageNo");
 %> <a href="#"
								onclick="callPage('1', 'F', '<%=totalPage%>', 'CashDisburse_getClosedList.action');">
							<img title="First Page" src="../pages/common/img/first.gif"
								width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPage('P', 'P', '<%=totalPage%>', 'CashDisburse_getClosedList.action');">
							<img title="Previous Page" src="../pages/common/img/previous.gif"
								width="10" height="10" class="iconImage" /> </a> <input type="text"
								name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
								maxlength="10"
								onkeypress="callPageText(event, '<%=totalPage%>', 'CashDisburse_getClosedList.action');return numbersOnly();" />
							of <%=totalPage%> <a href="#"
								onclick="callPage('N', 'N', '<%=totalPage%>', 'CashDisburse_getClosedList.action')">
							<img title="Next Page" src="../pages/common/img/next.gif"
								class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'CashDisburse_getClosedList.action');">
							<img title="Last Page" src="../pages/common/img/last.gif"
								width="10" height="10" class="iconImage" /> </a></td>
						</tr>
					</s:if>
				</table>
				</td>
			</tr>
			<tr>
				<td width="100%">
				<table width="100%" class="sorttable" border="0">
					<tr>
						<s:hidden name="myPage" />
						<td width="10%" class="formth"><label class="set"
							id="employee.id1" name="employee.id"
							ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></td>
						<td width="30%" class="formth"><label class="set"
							id="employee1" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
						<td width="10%" class="formth"><label class="set"
							id="claimed.date1" name="claimed.date"
							ondblclick="callShowDiv(this);"><%=label.get("claimed.date")%></label></td>
						<td width="30%" class="formth"><label class="set"
							id="claimed.particulars1" name="claimed.particulars"
							ondblclick="callShowDiv(this);"><%=label.get("claimed.particulars")%></label></td>
						<td width="15%" class="formth"><label class="set"
							id="claimed.amount1" name="claimed.amount"
							ondblclick="callShowDiv(this);"><%=label.get("claimed.amount")%></label></td>
						<td width="15%" class="formth">View</td>

					</tr>
					<s:if test="closedLength">
						<%
							int cn = pageNo * 20 - 20;
						%>
						<s:iterator value="closedList">
							<tr>
								<td width="10%" class="sortableTD" width="30%"><s:hidden
									name="closedEmpId" /><s:property value="empToken" /><s:hidden
									name="closedClmAppId" /></td>
								<td width="30%" class="sortableTD" width="25%"><s:property
									value="empName" /></td>
								<td width="10%" class="sortableTD" width="25%"><s:property
									value="claimDate" /></td>
								<td width="30%" class="sortableTD" width="25%"><s:property
									value="claimParticulars" /></td>
								<td width="15%" class="sortableTD" width="25%" align="right"><s:property
									value="claimAmt" /></td>
								<td width="15%" class="sortableTD" width="25%"><input
									type="button" class="token" value="View Details"
									onclick="viewDetails('<s:property value="closedClmAppId"/>',
													'<s:property value="status"/>','<s:property value="closedEmpId"/>')"></td>

							</tr>

						</s:iterator>

					</s:if>
					<s:if test="closedLength"></s:if>
					<s:else>
						<tr align="center">
							<td colspan="6" class="sortableTD" width="100%"><font
								color="red">No Data to display</font></td>
						</tr>
					</s:else>
				</table>
				</td>
			</tr>

		</s:if>

	</table>
</s:form>

<script>
function viewDetails(claimId,claimStatus,empCode){
	document.getElementById('paraFrm').action='CashDisburse_retrieveDetails.action?claimId='+claimId+'&empCode='+empCode+'&status='+claimStatus;
   	document.getElementById('paraFrm').submit();
}
</script>