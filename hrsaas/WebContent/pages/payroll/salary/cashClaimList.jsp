<!-- @author: REEBA JOSEPH 16 NOVEMBER 2010 -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="CashClaimNew" validate="true" id="paraFrm"
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
					<td width="93%" class="txt"><strong class="text_head">Cash
					Claim</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
			<td width="100%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
		
		<tr>
			<td width="20%">Cash Balance: </td>
			<td width="80%"><s:property value="cashBalance"/> </td>
		</tr>
		<!-- ###################### CASH BALANCE BLOCK BEGINS ############################## -->
		
		<tr>
			<td>
			<table width="100%" cellspacing="0" cellpadding="0" border="0" class="formbg">
				<tbody><tr>
					<td width="100%">
					<table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
						<tbody><tr>
						 	<td height="22" width="100%" class="text_head"><strong class="forminnerhead">&nbsp;&nbsp;<label ondblclick="callShowDiv(this);" name="cashBal" id="cashBal" class="set">Cash Balance Details</label></strong></td>
						</tr>
						<tr>
							<td>
							<table width="100%" cellspacing="1" cellpadding="1" border="0" class="sortable">
								<tbody><tr>
									<td width="33%" valign="top" class="formth"><label ondblclick="callShowDiv(this);" name="creType" id="creType" class="set">Credit Type</label></td>
									<td width="33%" valign="top" class="formth"><label ondblclick="callShowDiv(this);" name="cashAmount" id="cashAmount" class="set">Cash Amount</label>
									</td>
									<td width="33%" valign="top" class="formth"><label ondblclick="callShowDiv(this);" name="holdAmount" id="holdAmount" class="set">Onhold Balance Amount</label>
									</td>
								</tr>
								<s:iterator value="balanceList" status="stat">
									<tr>
										<s:hidden name="creditCode" value="%{creditCode}" />
										<td class="bodyCell" width="33%" nowrap="nowrap"><s:property
											value="creditType"/></td>
										<td class="bodyCell" width="33%"><s:property value="balanceAmount"/></td>
										<td class="bodyCell" width="33%" nowrap="nowrap">
										<s:property value="onHoldAmount"/></td>
									</tr>
								</s:iterator>
							</tbody></table>
							<hr>
							</td>
						</tr>
						<tr>
						<td><label ondblclick="callShowDiv(this);" name="totAmount" id="totAmount" class="set">Total Amount</label> :&nbsp;<input type="text" name="totAmt" id="paraFrm_totAmt" value='<s:property value="totAmt"/>' readonly="readonly"  >
</td></tr>
					</tbody></table>
					</td>
				</tr>
			</tbody></table>
			</td>
		</tr>
		<!-- ###################### CASH BALANCE BLOCK ENDS ############################## -->
		
		<tr>
			<td>
			<table class="" width="100%">
				<tr>
					<script>
				    function setAction(listType){
					    if(listType=="p"){
					      	document.getElementById('paraFrm').action='CashClaimNew_input.action';
					      	document.getElementById('paraFrm').submit();
					    }
					    if(listType=="a"){
					      	document.getElementById('paraFrm').action='CashClaimNew_getApprovedList.action';
					      	document.getElementById('paraFrm').submit();
					    }
					    if(listType=="r"){
					      	document.getElementById('paraFrm').action='CashClaimNew_getRejectedList.action';
					      	document.getElementById('paraFrm').submit();
					    }
					     
				    }
				   </script>
					<td><a href="#" onclick="setAction('p')">Pending
					List</a> | <a href="#" onclick="setAction('a')">Approved
					List</a> | <a href="#" onclick="setAction('r')">Rejected
					List</a></td>
				</tr>
			</table>
			</td>
		</tr>
		
		<s:if test="%{listType == 'pending'}">
		<tr>
			<td>
			<table width="100%" class="formbg">
				<tr>
						<td><b>Draft</b></td>
					</tr>
				<tr>
					<td class="formtext">
					<table width="100%" border="0" cellpadding="1" cellspacing="1">
							<tr>
								<td width="10%" class="formth"><label class="set"
									id="serial.no" name="serial.no"
									ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
								<td width="20%" class="formth"><label class="set"
									id="employee.id" name="employee.id"
									ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></td>
								<td width="40%" class="formth"><label class="set"
									id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
								<td width="20%" class="formth"><label class="set"
									id="claimed.date" name="claim.date"
									ondblclick="callShowDiv(this);"><%=label.get("claim.date")%></label></td>
								<td width="10%" class="formth">Edit Claim</td>

							</tr>
								<%
							int i = 1;
							%>
							<s:iterator value="cashClaimList" status="stat">
							
							<%!int p = 0;%>
							<tr>
								<td width="10%" class="sortableTD"><%=i %><s:hidden
									name="claimAppIdIt" /><s:hidden name="draftStatus" /></td>
								<td width="20%" class="sortableTD" width="30%"><s:hidden
									name="empIdIt" /><s:property value="empTokenIt" /></td>
								<td width="40%" class="sortableTD" width="25%"><s:property
									value="empNameIt" /></td>
								<td width="20%" class="sortableTD" width="25%"><s:property
									value="claimDateIt" /></td>
								<td width="10%" class="sortableTD" width="25%"><input
									type="button" name="edit_Details" class="token"
									value=" Edit Application " id="ctrlShow"
									onclick="viewDetails('<s:property value="claimAppIdIt"/>',
									'<s:property value="empIdIt"/>','<s:property value="draftStatus"/>')" /></td>
								
							</tr>
							<%
								p++;i++;
											
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
										<td width="10%" class="formth"><label class="set"
											id="serial.no1" name="serial.no"
											ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
										<td width="20%" class="formth"><label class="set"
											id="employee.id1" name="employee.id"
											ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></td>
										<td width="40%" class="formth"><label class="set"
											id="employee1" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
										<td width="20%" class="formth"><label class="set"
											id="claimed.date1" name="claim.date"
											ondblclick="callShowDiv(this);"><%=label.get("claim.date")%></label></td>
										<td width="10%" class="formth">View Application</td>
		
									</tr>
									

									<%
									int me = 1;
									%>
									<s:iterator value="claimSubmitList">
										<tr>
											<td width="10%" class="sortableTD"><%=me %><s:hidden
												name="subClaimAppIdIt" /><s:hidden
												name="pendingStatus" /></td>
											<td width="20%" class="sortableTD" width="30%"><s:hidden
												name="subEmpIdIt" /><s:property value="empTokenIt" /></td>
											<td width="40%" class="sortableTD" width="25%"><s:property
												value="empNameIt" /></td>
											<td width="20%" class="sortableTD" width="25%"><s:property
												value="claimDateIt" /></td>
											<td width="10%" class="sortableTD" width="25%"><input
												type="button" name="edit_Details" class="token"
												value=" Edit Application " id="ctrlShow"
												onclick="viewDetails('<s:property value="subClaimAppIdIt"/>',
												'<s:property value="subEmpIdIt"/>','<s:property value="pendingStatus"/>')" /></td>
											
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
		
		</s:if>
		
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
										onclick="callPage('1', 'F', '<%=totalPage%>', 'CashClaimNew_getApprovedList.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPage('P', 'P', '<%=totalPage%>', 'CashClaimNew_getApprovedList.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text" name="pageNoField"
										id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10"
										onkeypress="callPageText(event, '<%=totalPage%>', 'CashClaimNew_getApprovedList.action');return numbersOnly();" />
									of <%=totalPage%> <a href="#"
										onclick="callPage('N', 'N', '<%=totalPage%>', 'CashClaimNew_getApprovedList.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'CashClaimNew_getApprovedList.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
								</tr>
							</s:if>
							<tr>
								<td>
								<table width="100%" class="sorttable">
									<tr>
										<td width="10%" class="formth"><label class="set"
											id="serial.no2" name="serial.no"
											ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
										<td width="20%" class="formth"><label class="set"
											id="employee.id2" name="employee.id"
											ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></td>
										<td width="40%" class="formth"><label class="set"
											id="employee2" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
										<td width="20%" class="formth"><label class="set"
											id="claimed.date2" name="claim.date"
											ondblclick="callShowDiv(this);"><%=label.get("claim.date")%></label></td>
										<td width="10%" class="formth">View Application</td>
		
									</tr>
									<s:if test="approveLength">
										<%
										int cn = pageNo * 20 - 20;
										%>
										<s:iterator value="claimApprovedList">
											<tr>
												<td width="10%" class="sortableTD"><%=++cn%><s:hidden
													name="appClaimAppIdIt" /><s:hidden
													name="approvedStatus" /></td>
												<td width="20%" class="sortableTD" width="30%"><s:hidden
													name="appEmpIdIt" /><s:property value="empTokenIt" /></td>
												<td width="40%" class="sortableTD" width="25%"><s:property
													value="empNameIt" /></td>
												<td width="20%" class="sortableTD" width="25%"><s:property
													value="claimDateIt" /></td>
												<td width="10%" class="sortableTD" width="25%"><input
													type="button" name="edit_Details" class="token"
													value=" View Application " id="ctrlShow"
													onclick="viewDetails('<s:property value="appClaimAppIdIt"/>',
													'<s:property value="appEmpIdIt"/>','<s:property value="approvedStatus"/>')" /></td>
												
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

		</s:if>
		<!-------------------------------------- APPROVED LIST OF APPLICATIONS [ENDS] ------------------------------->

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
								onclick="callPageRejected('1', 'F', '<%=totalPageRejected%>', 'CashClaimNew_getRejectedList.action');">
							<img title="First Page" src="../pages/common/img/first.gif"
								width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPageRejected('P', 'P', '<%=totalPageRejected%>', 'CashClaimNew_getRejectedList.action');">
							<img title="Previous Page" src="../pages/common/img/previous.gif"
								width="10" height="10" class="iconImage" /> </a> <input type="text"
								name="pageNoField3" id="pageNoField3" size="3"
								value="<%=PageNoRejected%>" maxlength="10"
								onkeypress="callPageTextReject(event, '<%=totalPageRejected%>', 'CashClaimNew_getRejectedList.action');return numbersOnly();" />
							of <%=totalPageRejected%> <a href="#"
								onclick="callPageRejected('N', 'N', '<%=totalPageRejected%>', 'CashClaimNew_getRejectedList.action')">
							<img title="Next Page" src="../pages/common/img/next.gif"
								class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPageRejected('<%=totalPageRejected%>', 'L', '<%=totalPageRejected%>', 'CashClaimNew_getRejectedList.action');">
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
										<td width="10%" class="formth"><label class="set"
											id="serial.no3" name="serial.no"
											ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
										<td width="20%" class="formth"><label class="set"
											id="employee.id3" name="employee.id"
											ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></td>
										<td width="40%" class="formth"><label class="set"
											id="employee3" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
										<td width="20%" class="formth"><label class="set"
											id="claimed.date3" name="claim.date"
											ondblclick="callShowDiv(this);"><%=label.get("claim.date")%></label></td>
										<td width="10%" class="formth">View Application</td>
		
									</tr>
									<s:if test="rejectedLength">
										<%
										int cn3 = PageNoRejected * 20 - 20;
										%>

										<s:iterator value="claimRejectedList">
											<tr>
												<td width="10%" class="sortableTD"><%=++cn3%><s:hidden
													name="rejClaimAppIdIt" /><s:hidden
													name="rejectedStatus" /></td>
												<td width="20%" class="sortableTD" width="30%"><s:hidden
													name="rejEmpIdIt" /><s:property value="empTokenIt" /></td>
												<td width="40%" class="sortableTD" width="25%"><s:property
													value="empNameIt" /></td>
												<td width="20%" class="sortableTD" width="25%"><s:property
													value="claimDateIt" /></td>
												<td width="10%" class="sortableTD" width="25%"><input
													type="button" name="edit_Details" class="token"
													value=" View Application " id="ctrlShow"
													onclick="viewDetails('<s:property value="rejClaimAppIdIt"/>',
													'<s:property value="rejEmpIdIt"/>','<s:property value="rejectedStatus"/>')" /></td>
												
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

		</s:if>
		<!-------------------------------------- REJECTED APPLICATIONS LIST [ENDS]----------------------------->
		
		<tr>
			<td width="100%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>

	</table>
</s:form>

<script>
function addnewFun() {
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = 'CashClaimNew_addNew.action';
	document.getElementById('paraFrm').submit();
}

function viewDetails(claimId,empCode, status){
   	document.getElementById('paraFrm').action='CashClaimNew_retrieveDetails.action?claimId='+claimId+'&empCode='+empCode+'&status='+status;
   	document.getElementById('paraFrm').submit();
}
</script>