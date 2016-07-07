	<!--
		@author Vijay_Gaikwad
		created on-14-03-2013
	-->
	<%@ taglib prefix="s" uri="/struts-tags"%>
	<%@ include file="/pages/common/labelManagement.jsp"%>

	<STYLE type=text/css>
	a:hover {
		COLOR: #FF0000;
		text-decoration: underline;
	}
	</STYLE>
	<script type="text/javascript"
		src="../pages/common/include/javascript/sorttable.js"></script>
	<script type="text/javascript">
		var records = new Array();
	</script>
	<script type="text/javascript">
	function callforInternalUser(dashBoardID,dashBoardName){
    document.getElementById("paraFrm").action="ManageClientDashBoard_callForInternalUsers.action?dashBoardID="+dashBoardID+"&dashBoardName="+dashBoardName
	document.getElementById('paraFrm').target = "_self";
	document.getElementById("paraFrm").submit();
    }

    function callforExternalUser(dashBoardID,dashBoardName){
    document.getElementById("paraFrm").action="ManageClientDashBoard_callForExternalUsers.action?dashBoardID="+dashBoardID+"&dashBoardName="+dashBoardName
	document.getElementById('paraFrm').target = "_self";
	document.getElementById("paraFrm").submit();
    }

	function callforCRMAccounts(dashBoardID,dashBoardName){
	document.getElementById("paraFrm").action="ManageClientDashBoard_getDashBoardAccountList.action?dashBoardID="+dashBoardID+"&dashBoardName="+dashBoardName
	document.getElementById('paraFrm').target = "_self";
	document.getElementById("paraFrm").submit();
	
	}    
    
    function callForEdit(dashBoardname, isActive, dashBoardID){
    document.getElementById("paraFrm").action="ManageClientDashBoard_updateDashBoardDetails.action?dashBoardname="+dashBoardname+"&isActive="+isActive+"&dashBoardID="+dashBoardID
	document.getElementById('paraFrm').target = "_self";
	document.getElementById("paraFrm").submit();
    
    
    }
	</script>
	
	
	<s:form action="ManageClientDashBoard" id="paraFrm" validate="true"
		theme="simple" target="main">
		<s:hidden name="hiddencode" />
		<s:hidden name="accountCode" />
		<s:hidden name="businessName" />
		<s:hidden name="isActive" />
		<s:hidden name="recordCreatedOn" />
		<s:hidden name="recordModifiedOn" />
		<s:hidden name="accountId" />
		<s:hidden name="myPage" id="myPage" />
		<s:hidden name="hiddenSearchMessage" />
		<table width="100%" align="right" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" align="right" class="formbg">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="93%" class="txt"><strong class="text_head">DashBoard	Master List</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right">
					<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
			<td>
			<table class="formbg" width="100%" cellspacing="1" cellspadding="0"
				border="0">
				<tr>
					<td width="30%" align="left"></td>
					<td width="70%" align="right">
					<%
						int totalPage = 0;
							int pageNo = 0;
					%> <s:if test="listLength">
						<table>
							<tr>
								<td id="ctrlShow" width="100%" align="right"><b>Page:</b> 
								<%
 									totalPage = (Integer) request.getAttribute("totalPage");
 									pageNo = (Integer) request.getAttribute("pageNo");
 								%> 
 								<a href="#"
									onclick="callPage('1', 'F', '<%=totalPage%>', 'AccountMaster_input.action');">
								<img title="First Page" src="../pages/common/img/first.gif"
									width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPage('P', 'P', '<%=totalPage%>', 'AccountMaster_input.action');">
								<img title="Previous Page"
									src="../pages/common/img/previous.gif" width="10" height="10"
									class="iconImage" /> </a> <input type="text" name="pageNoField"
									id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10"
									onkeypress="callPageText(event, '<%=totalPage%>', 'AccountMaster_input.action');return numbersOnly();" />
								of <%=totalPage%> <a href="#"
									onclick="callPage('N', 'N', '<%=totalPage%>', 'AccountMaster_input.action')">
								<img title="Next Page" src="../pages/common/img/next.gif"
									class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'AccountMaster_input.action');">
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
									<td class="formth" align="center" width="05%"><label
										name="srno" id="srno" ondblclick="callShowDiv(this);">Sr.No.</label>
									</td>
									
									<td class="formth" align="center" width="20%"><label
										name="business.name" id="business.name"
										ondblclick="callShowDiv(this);">Dashboard Name</label>
									</td>
									<td class="formth" align="center" width="10%"><label
										name="crm.report.mapping" id="crm.report.mapping"
										ondblclick="callShowDiv(this);">Employee Mapping</label>
									</td>
									<td class="formth" align="center" width="10%"><label
										name="business.users" id="business.users"
										ondblclick="callShowDiv(this);">Client Mapping</label>
									</td>
									<td class="formth" align="center" width="10%"><label
										name="business.users" id="business.users"
										ondblclick="callShowDiv(this);">Account</label>
									</td>
									
									<td class="formth" align="center" width="15%" nowrap="nowrap"><label
										name="created.on" id="created.on"
										ondblclick="callShowDiv(this);">Is Active</label>
									</td>
									<td class="formth" align="center" width="15%" nowrap="nowrap"><label
										name="last.modified" id="last.modified"
										ondblclick="callShowDiv(this);">Edit</label>
									</td>
								</tr>
								
									<%
										int count = 0;
									%>
									<%!int d = 0;%>
									<%
										int i = 0;
												int cn = pageNo * 20 - 20;
									%>
									<s:iterator value="iteratorList">
										<s:hidden name="isParent" />
										<tr>
												<td width="05%" align="center" class="sortableTD">
												<s:property value="srno"/>
												 <s:hidden name="srNo" />
												 </td>
												<s:hidden value="%{accountCode}" id='<%="accountCode" + i%>' />
												<script type="text/javascript">
													records[<%=i%>] = document.getElementById('accountCode' + '<%=i%>').value;
												</script>
												
												<td class="sortableTD" align="center" width="20%">
												<s:property	value="dashBoardName" /></td>
												
												<s:if test='%{isAccountApplicatble=="0"}'>
												<td class="sortableTD" width="10%" align="center">
													<a href="#" class="link"
														onclick="javascript:callforInternalUser('<s:property value="dashBoardID"/>','<s:property value="dashBoardName"/>');">Manage</a>
												</td>
												</s:if> 
												<s:else>
												<td class="sortableTD" width="10%" align="center" colspan="1">
													
												</td>
												</s:else>
												<s:if test='%{isAccountApplicatble=="0"}'>
												<td class="sortableTD" width="10%" align="center">
													<a href="#" class="link"
														onclick="javascript:callforExternalUser('<s:property value="dashBoardID"/>','<s:property value="dashBoardName"/>');">Manage</a>				
												</td>
												</s:if> 
												<s:else>
												<td></td>
												</s:else>
												<s:if test='%{isAccountApplicatble=="0"}'>
												<td class="sortableTD" width="10%" align="center">
												
												</td>
												</s:if>	
												<s:else>
												<td class="sortableTD" width="10%" align="center">
												<a href="#" class="link"
														onclick="javascript:callforCRMAccounts('<s:property value="dashBoardID"/>','<s:property value="dashBoardName"/>');">Manage Account</a>	
												</td>
												</s:else>
												<td class="sortableTD" align="center" width="5%">
												<s:property	value="isActive" /></td>
												<td class="sortableTD" width="5%" id="ctrlShow"	align="center">
													<input type="button" class="rowEdit" title="Click for edit"	onclick="callForEdit('<s:property value="dashBoardName"/>','<s:property value="isActive"/>','<s:property value="dashBoardID"/>')" />
												</td>
											</tr>
									</s:iterator>
									<%
										d = i;
									%>
								
								<!--<s:else>
									<td align="center" colspan="6" nowrap="nowrap"><font
										color="red">There is no data to display</font></td>
								</s:else>
							-->
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:hidden name="rptType" />
		</table>
	</s:form>
	<script>

	
	

</script>



