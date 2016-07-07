<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>


<script language="JavaScript" type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="TravelClaim" validate="true" id="paraFrm" theme="simple">
	<s:hidden name="appDate" />
	<s:hidden name="claimApplnCode" />
	<s:hidden name="listType" />
	<s:hidden name="applnId" />
	<s:hidden name="maxClaimDays" />
	<s:hidden name="applnEmpId" />
	<s:hidden name="applnCode" />
	<s:hidden name="applnStatus" />
	<s:hidden name="applnFor" />
	<s:hidden name="applnInitId" />	
	<!-- <s:hidden name="appCode" /> -->
	<s:hidden name="myPageDraft" />
	<s:hidden name="myPageSubmit" />
	<s:hidden name="myPageReturn" />
	<s:hidden name="myPageApproved" />
	<s:hidden name="myPageClose" />
	<s:hidden name="myPageRejected" />
	<s:hidden name="myPageSchlTrvlList" />
	<s:hidden name="myPageCancel" />
	<s:hidden name="myPagePendingCancel" />
	<!-- for navigation purpose  added by krishna-->
	<s:hidden name="tmsClmAppId" />
	<s:hidden name="tmsTrvlId" />
	<s:hidden name="tmsTrvlCode" />
	<s:hidden name="tmsExpType" />

	<%
	request.getLocalAddr();
	%>


	<table width="100%" border="0" cellpadding="2" cellspacing="1"
		class="formbg">

		<tr>
			<td colspan="3">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt" colspan="1"><strong
						class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt" colspan="2"><strong
						class="text_head">Travel Claim Application</strong></td>
				</tr>
			</table>
			</td>
		</tr>

		<script>
		    function setAction(listType)
		    {		    
		     if(listType=="S")
		     {		     
		      	document.getElementById('paraFrm').action='TravelClaim_input.action';
		      	document.getElementById('paraFrm').submit();		      
		     }
		     if(listType=="A")
		     {		     
		      	document.getElementById('paraFrm').action='TravelClaim_getApprovedList.action';
		      	document.getElementById('paraFrm').submit();		      
		     }
		     if(listType=="C")
		     {		        
		      	document.getElementById('paraFrm').action='TravelClaim_getClosedList.action';
		      	document.getElementById('paraFrm').submit();		        
		     }
		   }
		</script>


		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class=formbg>
				<tr>
					<td height="27" class="formtxt"><input type="hidden"
						name="userType"> <a href="#" onclick="setAction('S')">Pending
					Claims </a>  &nbsp;&nbsp;|&nbsp;&nbsp;  <a href="#" onclick="setAction('A')">Processed Claims </a></td>
				</tr>
			</table>
			</td>
		</tr>
		
		<%--
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="22%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%">&nbsp;</td>
				</tr>
			</table>
			</td>
		</tr>
	--%>

		<!-- Start of Pending Scheduled  List -->
		<s:if test="%{listType == 'sch'}">
			<tr>
				<td>
				<table width="100%" class="formbg">
					<tr>
						<td><b> Pending Claims </b></td>
					</tr>
					<tr>
						<td>
						<table width="100%" class="sorttable">

							<tr>
								<td class="formth" width="5%"><b><label class="set"
									name="sno" id="sno1" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
								</td>
								<td class="formth" width="10%"><b>Travel Id</b></td>
								<td class="formth" width="20%"><b><label class="set"
									name="traReqname" id="traReqname1"
									ondblclick="callShowDiv(this);"><%=label.get("traReqname")%></label></b>
								</td>
								<td class="formth" width="15%"><b><label class="set"
									name="empguest" id="empguest1" ondblclick="callShowDiv(this);"><%=label.get("empguest")%></label></b>
								</td>

								<td class="formth" width="18%"><b><label class="set"
									name="Traenddate" id="Traenddate"
									ondblclick="callShowDiv(this);"><%=label.get("Traenddate")%></label></b>
								</td>


								<td class="formth" width="15%"><b><label class="set"
									name="trainitor" id="trainitor1"
									ondblclick="callShowDiv(this);"><%=label.get("trainitor")%></label></b>
								</td>

								<td class="formth" width="5%"><b>Claim due days</b></td>

								<td class="formth" width="10%"><b>Status</b></td>

								<td class="formth" width="10%"><b>Action</b></td>
							</tr>
							
							<!-- blocked List Iterator Begins -->
							<%
							int i = 1;
							%>
							<%
							int row = 0, count8 = 0;
							%>
							<s:iterator value="blockedAppList">
								<s:hidden name="schExpAppId" />
								<s:hidden name="appStatus" />
								<s:hidden name="appFor" />
								<s:hidden name="initId" />
								<s:hidden name="empId" />
								<!-- <s:hidden name="applnStatus" /> -->
							 
								<s:hidden name="appId" />
								<s:hidden name="appCode" />
								
								<s:hidden name="maximumClaimDueDays" />
								
								<tr <%if(count8%2==0){
						%> class="tableCell1" <%}else{ %>
									class="tableCell2" <%	}count8++; %> 
									onmouseover="javascript:newRowColor(this);"
								onmouseout="javascript:oldRowColor(this,<%=count8%2 %>);"	
									>
									
									<td class="sortableTD"><%=++row%></td>
									<td class="sortableTD"><s:property value="trvlId" />&nbsp;</td>
									<td class="sortableTD"><s:property value="trvlReqName" />&nbsp;</td>
									<td class="sortableTD"><s:property value="empName" />&nbsp;</td>
									<td class="sortableTD" align="center" width="13%"><s:property
										value="appEndDate" />&nbsp;</td>
									<td class="sortableTD"><s:property value="initName" />&nbsp;</td>
									<td class="sortableTD" align="center" width="13%"><s:property
										value="claimDueDays" />&nbsp;</td>
									
									<s:if test="%{applnStatus=='_F'}">
										<td class="sortableTD" align="center" width="13%">Revoked</td>
									<td width="20%" class="sortableTD" align="center">
									<input
										type="button" value=" Send UnBlock Request " class="token"
										align="middle"
										onclick="sendUnblockRequest('<s:property value="appId" />','<s:property value="empId" />','<s:property value="maximumClaimDueDays" />','<s:property value="appCode" />','<s:property value="applnStatus" />');">
									</s:if>
									
									<s:elseif test="%{applnStatus=='_A' || applnStatus=='_C'}">
									<td class="sortableTD" align="center" width="13%">Blocked due to delay</td>
									<td width="20%" class="sortableTD" align="center">
									<input
										type="button" value=" Send UnBlock Request " class="token"
										align="middle"
										onclick="sendUnblockRequest('<s:property value="appId" />','<s:property value="empId" />','<s:property value="maximumClaimDueDays" />','<s:property value="appCode" />','<s:property value="applnStatus" />');">
									</s:elseif> 
									
									<s:if test="%{applnStatus=='_Z'}">
									
									<!-- waiting for unblock request -->
									<td class="sortableTD" align="center" width="13%">Pending for UnBlock Approval</td>
									<td width="20%" class="sortableTD" align="center">
									&nbsp;
									</s:if> 
									<s:if test="%{applnStatus=='_W'}">
									
									<!-- Unblocked successfully -->
									<td class="sortableTD" align="center" width="13%">Unblocked Successfully</td>
									<td width="20%" class="sortableTD" align="center">
									<input
										type="button" value=" Apply for claim " class="token"
										align="middle"
										onclick="callForSchduledDtl(<s:property value="appId" />,<s:property value="appCode" />,'<s:property value="appStatus" />','<s:property value="appFor" />','<s:property value="initId" />','<s:property value="empId" />','<s:property value="schExpAppId" />','<s:property value="applnStatus" />');">									
									</s:if> 
									</td>

									<!--				
									<s:else>
									<td class="sortableTD" align="center" width="13%">Blocked for claim</td>								
									<td width="20%" class="sortableTD" align="center">
										<input	type="button" value=" Send unblocked request " class="token" align="middle" onclick="callForSchduledDtl(<s:property value="appId" />,<s:property value="appCode" />,'<s:property value="appStatus" />','<s:property value="appFor" />','<s:property value="initId" />','<s:property value="empId" />','<s:property value="schExpAppId" />');">
									</td>
								</s:else>							
							 -->



								</tr>
								<%
								i++;
								%>
							</s:iterator>

							<tr>
								<td align="right" colspan="5"></td>
							</tr>
							<!-- blocked List Iterator End -->

							<!-- Scheduled List Iterator Begins -->
							<%
							i = 1;
							%>
							<%
							int count1 = 0;
							%>
							<s:iterator value="scheduledAppList">
								<s:hidden name="schExpAppId" />
								<s:hidden name="appStatus" />
								<s:hidden name="appFor" />
								<s:hidden name="initId" />
								<s:hidden name="empId" />
								
								<tr <%if(count1%2==0){
						%> class="tableCell1" <%}else{ %>
									class="tableCell2" <%	}count1++; %> 
								onmouseover="javascript:newRowColor(this);"
								onmouseout="javascript:oldRowColor(this,<%=count1%2 %>);"		
									>
									<td class="sortableTD"><%=++row%></td>
									<td class="sortableTD"><s:property value="trvlId" />&nbsp;</td>
									<td class="sortableTD"><s:property value="trvlReqName" />&nbsp;</td>
									<td class="sortableTD"><s:property value="empName" />&nbsp;</td>
									<td class="sortableTD" align="center" width="13%"><s:property
										value="appEndDate" />&nbsp;</td>
									<td class="sortableTD"><s:property value="initName" />&nbsp;</td>


									<td class="sortableTD" align="center" width="13%"><s:property
										value="claimDueDays" />&nbsp;</td>

									<!-- <s:if test="claimDueDaysFlag">  </s:if>	-->
									
									<s:if test="%{applnStatus=='_F'}">
										<td class="sortableTD" align="center" width="13%">Revoked</td>									
									</s:if>
									<s:else>
									<td class="sortableTD" align="center" width="13%">Pending
									for claim</td>									
									</s:else>
									
									
									<td width="20%" class="sortableTD" align="center"><input
										type="button" value=" Apply For Claim " class="token"
										align="middle"
										onclick="callForSchduledDtl(<s:property value="appId" />,<s:property value="appCode" />,'<s:property value="appStatus" />','<s:property value="appFor" />','<s:property value="initId" />','<s:property value="empId" />','<s:property value="schExpAppId" />','<s:property value="applnStatus" />');">
									</td>
									
									<!--				
									<s:else>
									<td class="sortableTD" align="center" width="13%">Blocked for claim</td>								
									<td width="20%" class="sortableTD" align="center">
										<input	type="button" value=" Send unblocked request " class="token" align="middle" onclick="callForSchduledDtl(<s:property value="appId" />,<s:property value="appCode" />,'<s:property value="appStatus" />','<s:property value="appFor" />','<s:property value="initId" />','<s:property value="empId" />','<s:property value="schExpAppId" />');">
									</td>
								</s:else>							
							 -->



								</tr>
								<%
								i++;
								%>
							</s:iterator>

							<tr>
								<td align="right" colspan="5"></td>
							</tr>
							<!-- Scheduled List Iterator End -->
							<!-- Submit List Iterator Begins -->
							<%
							int k = 1;
							%>
							<%
							int count3 = 0;
							%>
							<s:iterator value="submitAppList">
								<s:hidden name="submitExpAppId" />
								<s:hidden name="appIdSubmit" />
								<s:hidden name="appCodeSubmit" />
								<s:hidden name="appStatusSubmit" />
								<s:hidden name="appForSubmit" />
								<s:hidden name="initIdSubmit" />
								<s:hidden name="empIdSubmit" />

								<tr <%if(count3%2==0){
						%> class="tableCell1" <%}else{ %>
									class="tableCell2" <%	}count3++; %>
									onmouseover="javascript:newRowColor(this);"
								onmouseout="javascript:oldRowColor(this,<%=count3%2 %>);"	
									>
									
									<td class="sortableTD"><%=++row%></td>
									<td class="sortableTD"><s:property value="trvlIdSubmit" />&nbsp;</td>
									<td class="sortableTD"><s:property
										value="trvlReqNameSubmit" />&nbsp;</td>
									<td class="sortableTD"><s:property value="empNameSubmit" />&nbsp;</td>


									<td class="sortableTD" align="center" width="13%"><s:property
										value="appEndDateSubmit" />&nbsp;</td>


									<td class="sortableTD"><s:property value="initName" />&nbsp;</td>


									<td class="sortableTD" align="center" width="13%"><s:property
										value="claimDueDays" />&nbsp;</td>
									<td class="sortableTD" align="center" width="13%">
									<s:property
										value="pendingStatus"></s:property></td>


									<td width="20%" class="sortableTD" align="center">
									<input type="button" value=" View Claim " class="token" align="middle" onclick="callForDrftDtl('<s:property value="appIdSubmit" />','<s:property value="appCodeSubmit" />','<s:property value="appStatusSubmit" />','<s:property value="appForSubmit" />','<s:property value="empIdSubmit" />','<s:property value="submitExpAppId" />');">
									
									</td>

								</tr>
								<%
								k++;
								%>
							</s:iterator>

							<tr>
								<td align="right" colspan="5"></td>
							</tr>

							<!-- Submit List Iterator End -->


						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
		<!-- End of Pending Scheduled List -->






		<s:if test="%{listType == 'approved'}">
			<tr>
				<td>
				<table width="100%" class="formbg">
					<tr>
						<td><b>Processed List</b></td>
					</tr>
					<tr>
						<%
									int totalPageApprove = (Integer) request
									.getAttribute("totalPageApprove");
							int PageNoApprove = (Integer) request.getAttribute("PageNoApprove");
							int row5 = (Integer) request.getAttribute("rowApprove");
							
							
						%>
						<td align="right" colspan="6" width="100%"><b>Page:</b> <%
 		if (PageNoApprove != 1) {
 		if (PageNoApprove > totalPageApprove) {
 		} else {
 %> <a href="#" onclick="callPage('1','A');"> <img
							src="../pages/common/img/first.gif" width="10" height="10"
							class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('<%=PageNoApprove-1 %>','A');"> <img
							src="../pages/common/img/previous.gif" width="10" height="10"
							class="iconImage" /> </a> <%
 	}
 	}
 	if (totalPageApprove <= 5) {
 		if (totalPageApprove == 1) {
 %> <b><u><%=totalPageApprove%></u></b> <%
 			} else {
 			for (int z = 1; z <= totalPageApprove; z++) {
 		if (PageNoApprove == z) {
 %> <b><u><%=z%></u></b> <%
 } else {
 %> <a href="#" onclick="callPage('<%=z%>','A');"><%=z%></a> <%
 			}
 			}
 		}
 	} else {
 		if (PageNoApprove == totalPageApprove - 1
 		|| PageNoApprove == totalPageApprove) {
 			for (int z = PageNoApprove - 2; z <= totalPageApprove; z++) {
 		if (PageNoApprove == z) {
 %> <b><u><%=z%></u></b> <%
 } else {
 %> &nbsp;<a href="#" onclick="callPage('<%=z%>','A');"><%=z%></a> <%
 			}
 			}
 		} else if (PageNoApprove <= 3) {
 			for (int z = 1; z <= 5; z++) {
 		if (PageNoApprove == z) {
 %> <b><u><%=z%></u></b> <%
 } else {
 %> &nbsp;<a href="#" onclick="callPage('<%=z%>','A');"><%=z%></a> <%
 			}
 			}
 		} else if (PageNoApprove > 3) {
 			for (int z = PageNoApprove - 2; z <= PageNoApprove + 2; z++) {
 		if (PageNoApprove == z) {
 %> <b><u><%=z%></u></b> <%
 } else {
 %> &nbsp;<a href="#" onclick="callPage('<%=z%>','A');"><%=z%></a> <%
 			}
 			}
 		}
 	}
 	if (!(PageNoApprove == totalPageApprove)) {
 		if (totalPageApprove == 0 || PageNoApprove > totalPageApprove) {
 		} else {
 %> ....<%=totalPageApprove%> <a href="#" onclick="callPage('N','A')">
						<img src="../pages/common/img/next.gif" class="iconImage" /> </a>&nbsp;
						<a href="#" onclick="callPage('<%=totalPageApprove%>','A');">
						<img src="../pages/common/img/last.gif" width="10" height="10"
							class="iconImage" /> </a> <%
 	}
 	}
 %><select name="selectname" onchange="selectPage('A')" id="selectnameA">
							<%
							for (int x = 1; x <= totalPageApprove; x++) {
							%>

							<option value="<%=x%>" <%=PageNoApprove==x?"selected":"" %>><%=x%></option>
							<%
							}
							%>
						</select></td>
					</tr>
					<tr>
						<td colspan="5">
						<table width="100%" class="sorttable">

							<tr>
								<td class="formth" width="5%" colspan="1"><b><label
									class="set" name="sno" id="sno1"
									ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b></td>
								<td class="formth" width="15%" colspan="1"><b>Travel Id</b></td>
								<td class="formth" width="24%" colspan="1"><b><label
									class="set" name="empguest" id="empguest1"
									ondblclick="callShowDiv(this);"><%=label.get("empguest")%></label></b>
								</td>
								<td class="formth" width="24%" colspan="1"><b><label
									class="set" name="traReqname" id="traReqname1"
									ondblclick="callShowDiv(this);"><%=label.get("traReqname")%></label></b>
								</td>

								<td class="formth" width="18%" colspan="1"><b><label
									class="set" name="Traenddate" id="Traenddate"
									ondblclick="callShowDiv(this);"><%=label.get("Traenddate")%></label></b>
								</td>


								<td class="formth" width="10%" colspan="1"><b><label
									class="set" name="status" id="status"
									ondblclick="callShowDiv(this);"><%=label.get("status")%></label></b>
								</td>
								
								<td class="formth" width="15%" colspan="1"><b>Action</b></td>
							</tr>
							<%
							int x = 1;
							%>
							<%
								int count5 = 0;
								int cnt5 = PageNoApprove * 20 - 20;
							%>
							<s:iterator value="ApproveAppList">
								<s:hidden name="approveExpAppId" />
								<s:hidden name="appIdApprove" />
								<s:hidden name="appCodeApprove" />
								<s:hidden name="appStatusApprove" />
								<s:hidden name="appForApprove" />
								<s:hidden name="initIdApprove" />
								<s:hidden name="empIdApprove" />
								<s:hidden name="claimApplnCodeApprove" />

								<tr <%if(count5%2==0){
						%> class="tableCell1" <%}else{ %>
									class="tableCell2" <%	}count5++; %>
								onmouseover="javascript:newRowColor(this);"
								onmouseout="javascript:oldRowColor(this,<%=count5%2 %>);"	
									
									 >
									
							<!-- 
								title="double click to edit "
									onmouseover="javascript:newRowColor(this);"
									onmouseout="javascript:oldRowColor(this,<%=count5%2 %>);"
									ondblclick="javascript:callForApprvdDtl(<s:property value="appIdApprove" />,<s:property value="appCodeApprove" />,'<s:property value="approveExpAppId" />');"
							
							 -->		

									<td class="sortableTD"><%=++row5%></td>
									<td class="sortableTD"><s:property value="trvlIdApprove" />&nbsp;</td>
									<td class="sortableTD"><s:property value="empNameApprove" />&nbsp;</td>
									<td class="sortableTD"><s:property
										value="trvlReqNameApprove" />&nbsp;</td>

									<td class="sortableTD" align="center" width="13%"><s:property
										value="appEndDateApprove" />&nbsp;</td>

									<td class="sortableTD" align="center" width="13%"><s:property
										value="status" />&nbsp;</td>
										
										
									<td class="sortableTD" align="center" width="13%">
									<input type="button" value="view" class="token"
										align="middle" onclick="callForApprvdDtl('A','<s:property value="approveExpAppId" />');" />
									&nbsp;
									</td>	

								</tr>
								<%
								x++;
								%>
							</s:iterator>
							<%
							if (x == 1) {
							%>
							<tr align="center">
								<td colspan="6" class="sortableTD" width="100%"><font
									color="red">No Data to display</font></td>
							</tr>
							<%
							}
							%>
							<tr>
								<td align="right" colspan="5"></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
		<!-- Approved List Close -->

		<!-- Return ENDS -->
		<s:if test="%{listType == 'closed'}">
			<tr>
				<td>
				<table width="100%" class="formbg">
					<tr>
						<td><b>Closed List</b></td>
					</tr>
					<tr>
						<%
									int totalPageClose = (Integer) request
									.getAttribute("totalPageClose");
							int PageNoClose = (Integer) request.getAttribute("PageNoClose");
							int row6 = (Integer) request.getAttribute("rowClose");
						%>
						<td align="right" colspan="6" width="100%"><b>Page:</b> <%
 		if (PageNoClose != 1) {
 		if (PageNoClose > totalPageClose) {
 		} else {
 %> <a href="#" onclick="callPage('1','C');"> <img
							src="../pages/common/img/first.gif" width="10" height="10"
							class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('P','C');"> <img
							src="../pages/common/img/previous.gif" width="10" height="10"
							class="iconImage" /> </a> <%
 	}
 	}
 	if (totalPageClose <= 5) {
 		if (totalPageClose == 1) {
 %> <b><u><%=totalPageClose%></u></b> <%
 			} else {
 			for (int z = 1; z <= totalPageClose; z++) {
 		if (PageNoClose == z) {
 %> <b><u><%=z%></u></b> <%
 } else {
 %> <a href="#" onclick="callPage('<%=z%>','C');"><%=z%></a> <%
 			}
 			}
 		}
 	} else {
 		if (PageNoClose == totalPageClose - 1
 		|| PageNoClose == totalPageClose) {
 			for (int z = PageNoClose - 2; z <= totalPageClose; z++) {
 		if (PageNoClose == z) {
 %> <b><u><%=z%></u></b> <%
 } else {
 %> &nbsp;<a href="#" onclick="callPage('<%=z%>','C');"><%=z%></a> <%
 			}
 			}
 		} else if (PageNoClose <= 3) {
 			for (int z = 1; z <= 5; z++) {
 		if (PageNoClose == z) {
 %> <b><u><%=z%></u></b> <%
 } else {
 %> &nbsp;<a href="#" onclick="callPage('<%=z%>','C');"><%=z%></a> <%
 			}
 			}
 		} else if (PageNoClose > 3) {
 			for (int z = PageNoClose - 2; z <= PageNoClose + 2; z++) {
 		if (PageNoClose == z) {
 %> <b><u><%=z%></u></b> <%
 } else {
 %> &nbsp;<a href="#" onclick="callPage('<%=z%>','C');"><%=z%></a> <%
 			}
 			}
 		}
 	}
 	if (!(PageNoClose == totalPageClose)) {
 		if (totalPageClose == 0 || PageNoClose > totalPageClose) {
 		} else {
 %> ....<%=totalPageClose%> <a href="#" onclick="callPage('N','C')">
						<img src="../pages/common/img/next.gif" class="iconImage" /> </a>&nbsp;
						<a href="#" onclick="callPage('<%=totalPageClose%>','C');"> <img
							src="../pages/common/img/last.gif" width="10" height="10"
							class="iconImage" /> </a> <%
 	}
 	}
 %><select name="selectname" onchange="selectPage('C')" id="selectnameC">
							<%
							for (int g = 1; g <= totalPageClose; g++) {
							%>

							<option value="<%=g%>" <%=PageNoClose==g?"selected":"" %>><%=g%></option>
							<%
							}
							%>
						</select></td>
					</tr>
					<tr>
						<td colspan="5">
						<table width="100%" class="sorttable">

							<tr>
								<td class="formth" width="5%" colspan="1"><b><label
									class="set" name="sno" id="sno1"
									ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b></td>
								<td class="formth" width="24%" colspan="1"><b>Travel Id</b></td>
								<td class="formth" width="24%" colspan="1"><b><label
									class="set" name="empguest" id="empguest1"
									ondblclick="callShowDiv(this);"><%=label.get("empguest")%></label></b>
								</td>
								<td class="formth" width="24%" colspan="1"><b><label
									class="set" name="traReqname" id="traReqname1"
									ondblclick="callShowDiv(this);"><%=label.get("traReqname")%></label></b>
								</td>

								<td class="formth" width="24%" colspan="1"><b><label
									class="set" name="Traenddate" id="Traenddate"
									ondblclick="callShowDiv(this);"><%=label.get("Traenddate")%></label></b>
								</td>

							</tr>
							<%
							int g = 1;
							%>
							<%
								int count6 = 0;
								int cnt6 = PageNoClose * 20 - 20;
							%>
							<s:iterator value="closeAppList">
								<s:hidden name="closeExpAppId" />
								<s:hidden name="appIdClose" />
								<s:hidden name="appCodeClose" />
								<s:hidden name="appStatusClose" />
								<s:hidden name="appForClose" />
								<s:hidden name="initIdClose" />
								<s:hidden name="empIdClose" />
								<s:hidden name="claimApplnCodeClose" />

								<tr <%if(count6%2==0){
						%> class="tableCell1" <%}else{ %>
									class="tableCell2" <%	}count6++; %>
									title="double click to edit"
									onmouseover="javascript:newRowColor(this);"
									onmouseout="javascript:oldRowColor(this,<%=count6%2 %>);"
									ondblclick="javascript:callForClsdtDtl(<s:property value="appIdClose" />,<s:property value="appCodeClose" />,'<s:property value="closeExpAppId" />');">
									<td class="sortableTD"><%=++row6%></td>
									<td class="sortableTD"><s:property value="trvlIdClose" />&nbsp;</td>
									<td class="sortableTD"><s:property value="empNameClose" />&nbsp;</td>
									<td class="sortableTD"><s:property
										value="trvlReqNameClose" />&nbsp;</td>

									<td class="sortableTD" align="center" width="13%"><s:property
										value="appEndDateClose" />&nbsp;</td>

								</tr>
								<%
								g++;
								%>
							</s:iterator>
							<%
							if (g == 1) {
							%>
							<tr align="center">
								<td colspan="6" class="sortableTD" width="100%"><font
									color="red">No Data to display</font></td>
							</tr>
							<%
							}
							%>
							<tr>
								<td align="right" colspan="5"></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
		<!-- Closed List Close -->
		<%--
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="22%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%">&nbsp;</td>
				</tr>
			</table>
			</td>
		</tr>
		--%>
		

	</table>
</s:form>

<script>

function callPage(id,appType){//1,'A'   N,'A'
		
		if(appType=='S'){
			if(!(id=="N" || id=="P") ){
			 	document.getElementById('paraFrm_myPageSchlTrvlList').value=id;		
			 	document.getElementById('paraFrm').action='TravelClaim_input.action'	 	
			}if(id=="N"){
			 	currentPage = document.getElementById('paraFrm_myPageSchlTrvlList').value;
			 	document.getElementById('paraFrm_myPageSchlTrvlList').value=eval(currentPage)+1;
			 	document.getElementById('paraFrm').action='TravelClaim_input.action'
			}if(id=="P"){
			 	currentPage = document.getElementById('paraFrm_myPageSchlTrvlList').value;
			 	document.getElementById('paraFrm_myPageSchlTrvlList').value=eval(currentPage)-1;
			 	document.getElementById('paraFrm').action='TravelClaim_input.action'
			}
		}
		
		if(appType=='N'){
			if(!(id=="N" || id=="P") ){
			 	document.getElementById('paraFrm_myPageDraft').value=id;		
			 	document.getElementById('paraFrm').action='TravelClaim_input.action'	 	
			}if(id=="N"){
			 	currentPage = document.getElementById('paraFrm_myPageDraft').value;
			 	document.getElementById('paraFrm_myPageDraft').value=eval(currentPage)+1;
			 	document.getElementById('paraFrm').action='TravelClaim_input.action'
			}if(id=="P"){
			 	currentPage = document.getElementById('paraFrm_myPageDraft').value;
			 	document.getElementById('paraFrm_myPageDraft').value=eval(currentPage)-1;
			 	document.getElementById('paraFrm').action='TravelClaim_input.action'
			}
		}
		
		if(appType=='P'){
			if(!(id=="N" || id=="P") ){
			 	document.getElementById('paraFrm_myPageSubmit').value=id;
			 	document.getElementById('paraFrm').action='TravelClaim_input.action';
			}if(id=="N"){
			 	currentPage = document.getElementById('paraFrm_myPageSubmit').value;
			 	document.getElementById('paraFrm_myPageSubmit').value=eval(currentPage)+1;
			 	document.getElementById('paraFrm').action='TravelClaim_input.action'
			}if(id=="P"){
			 	currentPage = document.getElementById('paraFrm_myPageSubmit').value;
			 	document.getElementById('paraFrm_myPageSubmit').value=eval(currentPage)-1;
			 	document.getElementById('paraFrm').action='TravelClaim_input.action'
			}
		}
		
		if(appType=='B'){
			if(!(id=="N" || id=="P") ){
			 	document.getElementById('paraFrm_myPageReturn').value=id;		
			 	document.getElementById('paraFrm').action='TravelClaim_input.action'	 	
			}if(id=="N"){
			 	currentPage = document.getElementById('paraFrm_myPageReturn').value;
			 	document.getElementById('paraFrm_myPageReturn').value=eval(currentPage)+1;
			 	document.getElementById('paraFrm').action='TravelClaim_input.action'
			}if(id=="B"){
			 	currentPage = document.getElementById('paraFrm_myPageReturn').value;
			 	document.getElementById('paraFrm_myPageReturn').value=eval(currentPage)-1;
			 	document.getElementById('paraFrm').action='TravelClaim_input.action'
			}
		}
		
		if(appType=='A'){
			if(!(id=="N" || id=="A") ){
			 	document.getElementById('paraFrm_myPageApproved').value=id;		
			 	document.getElementById('paraFrm').action='TravelClaim_getApprovedList.action'	 	
			}if(id=="N"){
			 	currentPage = document.getElementById('paraFrm_myPageApproved').value;
			 	document.getElementById('paraFrm_myPageApproved').value=eval(currentPage)+1;
			 	document.getElementById('paraFrm').action='TravelClaim_getApprovedList.action'
			}if(id=="A"){
			 	currentPage = document.getElementById('paraFrm_myPageApproved').value;
			 	document.getElementById('paraFrm_myPageApproved').value=eval(currentPage)-1;
			 	document.getElementById('paraFrm').action='TravelClaim_getApprovedList.action'
			}
		}
		
		if(appType=='C'){
			if(!(id=="N" || id=="C") ){
			 	document.getElementById('paraFrm_myPageClose').value=id;		
			 	document.getElementById('paraFrm').action='TravelClaim_getClosedList.action'	 	
			}if(id=="N"){
			 	currentPage = document.getElementById('paraFrm_myPageClose').value;
			 	document.getElementById('paraFrm_myPageClose').value=eval(currentPage)+1;
			 	document.getElementById('paraFrm').action='TravelClaim_getClosedList.action'
			}if(id=="C"){
			 	currentPage = document.getElementById('paraFrm_myPageClose').value;
			 	document.getElementById('paraFrm_myPageClose').value=eval(currentPage)-1;
			 	document.getElementById('paraFrm').action='TravelClaim_getClosedList.action'
			}
		}
		
		document.getElementById('paraFrm').submit();
		
}

function selectPage(appStatus)
   {     	
   		var pageNo;
   		
   		if(appStatus=='S'){ //Scheduled Travel List
			pageNo = document.getElementById('selectnameS').value;
   		 	document.getElementById('paraFrm_myPageSchlTrvlList').value=pageNo;
   		 	document.getElementById('paraFrm').action='TravelClaim_input.action';  		 	
		}  
		if(appStatus=="N"){//Draft List
   		 	pageNo = document.getElementById('selectnameN').value;
   		 	document.getElementById('paraFrm_myPageDraft').value=pageNo;
   		 	document.getElementById('paraFrm').action='TravelClaim_input.action';
   		 	
   		}if(appStatus=="P"){//Submit List
   		 	pageNo = document.getElementById('selectnameP').value;
   		 	document.getElementById('paraFrm_myPageSubmit').value=pageNo;
   		 	document.getElementById('paraFrm').action='TravelClaim_input.action';
   		 	
   		}if(appStatus=="B"){//Returned List
   		 	pageNo = document.getElementById('selectnameB').value;
   		 	document.getElementById('paraFrm_myPageReturn').value=pageNo;
   		 	document.getElementById('paraFrm').action='TravelClaim_input.action';
   		 	
   		}
   		if(appStatus=="A"){//Approved List
   		 	pageNo = document.getElementById('selectnameA').value;
   		 	document.getElementById('paraFrm_myPageApproved').value=pageNo;
   		 	document.getElementById('paraFrm').action='TravelClaim_getApprovedList.action';
   		 	
   		}if(appStatus=="C"){//Rejected List
   		 	pageNo = document.getElementById('selectnameR').value;
   		 	document.getElementById('paraFrm_myPageClose').value=pageNo;
   		 	document.getElementById('paraFrm').action='TravelClaim_getClosedList.action';
   		}
   		   		
  		//var val= document.getElementById('selectname').value;
  		//document.getElementById('paraFrm_show').value=val;
	 	
	 	//document.getElementById('selectname').value=val;
	  	//document.getElementById('paraFrm').action='TravelApplication_input.action';
	    document.getElementById('paraFrm').submit();
   }
   
   
   function callForSchduledDtl(appId, appCode, appStatus, appFor, initId, empId,schExpAppId,status )
   {
		  /*
		   alert("Status :::: "+status);		
		  if(Status=="_F")
		  {
		  	alert("This application is revoked.");
		  	return false;
		  }		 
		 alert(appId+'--'+appStatus);
		 alert("Application ID==========>"+(document.getElementById('paraFrm_applnId').value)); 
      	  */ 
      	  
      	  document.getElementById('paraFrm_applnId').value=appId;
      	 
      	  
      	  if(appStatus=="N" )
      	  {//|| appStatus=="W"
		  	document.getElementById('paraFrm_applnCode').value="";
		  }
		  document.getElementById('paraFrm_applnCode').value=appCode;
		  document.getElementById('paraFrm_applnStatus').value=appStatus;
		  document.getElementById('paraFrm_applnFor').value=appFor;
		  document.getElementById('paraFrm_applnInitId').value=initId;
		  document.getElementById('paraFrm_applnEmpId').value=empId; 
		  document.getElementById('paraFrm_claimApplnCode').value=schExpAppId;
		  
		  
		  document.getElementById('paraFrm').action='TravelClaim_trvlClaimDtl.action?status='+status;
		  document.getElementById('paraFrm').submit();  
     	  
    } 
    
   function sendUnblockRequest(appId,empCode,claimDueDays,appCode,Status) {
     
      	 //alert("Status :: "+Status);
      	 if(Status=='_F')
      	 {
      	 	alert("This application is revoked.");
      	 	return false;
      	 }	
      	 // document.getElementById('paraFrm_applnId').value=appId;
      	  document.getElementById('paraFrm_applnEmpId').value=empCode;
      	   document.getElementById('paraFrm_maxClaimDays').value=claimDueDays;
      	   
      	 //  alert("appId : "+appId);
      	 //  alert("appCode : "+appCode);
      	  	var conf=confirm("Do you really want to unblock this application?");
  	  		if(conf) {
	  	 	  document.getElementById('paraFrm').action="TravelClaim_save.action?buttonType=P";
	      	  document.getElementById('paraFrm').action='TravelClaim_sendUnblockRequest.action?appId='+appId+'&appCode='+appCode;
			  document.getElementById('paraFrm').submit();  
		  	}
    } 
   
    
    //CALLED WHEN Add New BUTTON IS CLICKED
	 function addnewFun(){			
		document.getElementById('paraFrm').action="TravelClaim_addNew.action";
		document.getElementById('paraFrm').submit();
	}
	
	function saveFun(){
	document.getElementById('paraFrm').action="TravelClaim_save.action";
  	document.getElementById('paraFrm').submit();
	}
	
	
	
	function callForDrftDtl(appIdDraft, appCodeDraft, appStatusDraft, appForDraft, empIdDraft,draftExpAppId){  
  	  	  //alert("draftExpAppId"+draftExpAppId);
	  document.getElementById('paraFrm_applnId').value=appIdDraft;
	  document.getElementById('paraFrm_applnCode').value=appCodeDraft;
	  document.getElementById('paraFrm_applnStatus').value=appStatusDraft;
	  document.getElementById('paraFrm_applnFor').value=appForDraft;
	  document.getElementById('paraFrm_applnEmpId').value=empIdDraft;
	  document.getElementById('paraFrm_claimApplnCode').value=draftExpAppId;	  	  
	  document.getElementById('paraFrm').action='TravelClaim_trvlClaimDraftDtl.action?status=underprocess';
	  document.getElementById('paraFrm').submit();
	      
 	}
 	
 	
 	
 	function callForApprvdDtl(processedStatus, applicationCode){  
  	  //	alert(processedStatus);
  	  	
  	  //	alert(applicationCode);
	  document.getElementById('paraFrm_applnStatus').value=processedStatus;
	  document.getElementById('paraFrm_claimApplnCode').value=applicationCode;	  	  
	  document.getElementById('paraFrm').action='TravelClaim_trvlClaimDraftDtl.action?status=process';
	  document.getElementById('paraFrm').submit();
	      
 	}
 	
 	 	function callForApprvdDtl_old(appIdApprvd, appCodeApprvd,expAppId){  
 		
 		//alert("expAppId : "+expAppId);
 		//document.getElementById('paraFrm_tmsClmAppId').value = apprvdExpAppId; 
		//document.getElementById('paraFrm_tmsTrvlId').value = appIdApprvd; 
		//document.getElementById('paraFrm_tmsTrvlCode').value = appCodeApprvd; 
		document.getElementById('paraFrm').action = 'TravelClmAppvr_input.action?clmAppFlag=Y&expAppId='+expAppId+'&appIdApprvd='+appIdApprvd+'&appCodeApprvd='+appCodeApprvd;	
	    document.getElementById('paraFrm').submit();
	    document.getElementById('paraFrm').target = "main";
	      
 	}
 	
 	
 	function callForClsdtDtl(appIdApprvd, appCodeApprvd,apprvdExpAppId){   	
 	document.getElementById('paraFrm_tmsClmAppId').value = apprvdExpAppId; 
		document.getElementById('paraFrm_tmsTrvlId').value = appIdApprvd; 
		document.getElementById('paraFrm_tmsTrvlCode').value = appCodeApprvd; 
		document.getElementById('paraFrm').action = 'TravelClmAppvr_input.action?clmAppFlag=Y&clmStatus=CL';	
	    document.getElementById('paraFrm').submit();
	    document.getElementById('paraFrm').target = "main";
	      
 	}
 	
 	
 	
 	
</script>
