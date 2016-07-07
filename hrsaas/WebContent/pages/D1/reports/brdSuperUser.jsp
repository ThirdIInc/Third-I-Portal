<!-- Created on 11th April 2012 -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="BRDSuperUser" validate="true" id="paraFrm" theme="simple">
	<table width="100%" class="formbg">
	<s:hidden name="myPage" id="myPage" />
		<tr>
			<td valign="bottom" class="txt">
				<table width="100%" align="right" class="formbg">
					<tr>
						<td>
							<strong class="text_head">
								<img src="../pages/images/recruitment/review_shared.gif" width="25"
									height="25" />
							</strong>
						</td>
						<td width="93%" class="txt">
							<strong class="text_head">BRD Super User Assignment </strong>
						</td>
						<td width="3%" valign="top" class="txt"> 
							<div align="right">
								<img src="../pages/images/recruitment/help.gif" width="16" height="16" />
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		
		<tr>
			<td>
				<table width="100%" align="right" class="formbg" border="0">
					<tr>
						<td colspan="4">
							<b><label name="selectBrdApplication" id="selectBrdApplication" ondblclick="callShowDiv(this);"><%=label.get("selectBrdApplication")%></label></b>
						</td>
					</tr>
					
					<tr>
						<td width="25%">
							<label name="applicationStatusLabel" id="applicationStatusLabel" ondblclick="callShowDiv(this);"><%=label.get("applicationStatusLabel")%></label> : 
						</td>
						<td width="15%">
							<s:select headerKey="-1" headerValue="------------Select--------------" name="applicationStatus" list="#{'D':'Draft','F':'Forwarded', 'C':'Closed', 'Z':'Cancel'}"></s:select>
						</td>
						<td width="25%">
							<label name="currentStageLabel" id="currentStageLabel" ondblclick="callShowDiv(this);"><%=label.get("currentStageLabel")%></label> :
						</td>
						<td width="35%">
							<s:select headerKey="-1" headerValue="------------Select--------------"
							name="currentStage" cssStyle="width:160" list="currentStageList" />
						</td>
					</tr>
					
					<tr>
						<td width="25%">
							<label name="fromDateLabel" id="fromDateLabel"
						ondblclick="callShowDiv(this);"><%=label.get("fromDateLabel")%></label> :  
						</td>
						<td width="15%" nowrap="nowrap">
							<s:textfield size="25" name="fromDate" onkeypress="return numbersWithHiphen();" /> 
							<s:a href="javascript:NewCal('paraFrm_fromDate','DDMMYYYY');">
								<img src="../pages/images/Date.gif" id="ctrlHide"
								class="iconImage" height="16" align="absmiddle" width="16">
							</s:a>
						</td>
						<td width="25%">
							<label name="toDateLabel" id="toDateLabel"
							ondblclick="callShowDiv(this);"><%=label.get("toDateLabel")%></label> :
						</td>
						<td width="35%" nowrap="nowrap">
							<s:textfield size="25" name="toDate" onkeypress="return numbersWithHiphen();" /> 
							<s:a href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
								<img src="../pages/images/Date.gif" id="ctrlHide"
								class="iconImage" height="16" align="absmiddle" width="16">
							</s:a>
						</td>
					</tr>
					
					<tr>
						<td width="25%">
							<label name="ticketNumberLabel" id="ticketNumberLabel" ondblclick="callShowDiv(this);"><%=label.get("ticketNumberLabel")%></label> : 
						</td>
						<td width="15%">
							<s:textfield readonly="true" size="40" name="ticketNumber"/>
						</td>
						<td colspan="2">
							<img src="../pages/images/search2.gif" class="iconImage" height="18" align="absmiddle" width="18"
							onclick="javascript:callsF9(500,325,'BRDSuperUser_f9TicketNumber.action');">
						</td>
					</tr>
					
					<tr>
						<td width="25%">
							<label name="initiatorLabel" id="initiatorLabel" ondblclick="callShowDiv(this);"><%=label.get("initiatorLabel")%></label> : 
						</td>
						<td colspan="2">
							<s:hidden name="initiatorId"/>
							<s:textfield readonly="true" size="15" name="initiatorToken"/>
							<s:textfield readonly="true" size="47" name="initiatorName"/>
						</td>
						<td width="35%">
							<img src="../pages/images/search2.gif" class="iconImage" height="18" align="absmiddle" width="18"
							onclick="javascript:callsF9(500,325,'BRDSuperUser_f9Initiator.action');">
						</td>
					</tr>
					
					<tr>
						<td width="25%">
							<label name="pendingWithEmployeeLabel" id="pendingWithEmployeeLabel" ondblclick="callShowDiv(this);"><%=label.get("pendingWithEmployeeLabel")%></label> : 
						</td>
						<td colspan="2">
							<s:hidden name="pendingWithEmpId"/>
							<s:textfield readonly="true" size="15" name="pendingWithEmpToken"/>
							<s:textfield readonly="true" size="47" name="pendingWithEmpName"/>
						</td>
						<td width="35%">
							<img src="../pages/images/search2.gif" class="iconImage" height="18" align="absmiddle" width="18"
							onclick="javascript:callsF9(500,325,'BRDSuperUser_f9PendingWithEmploye.action');">
						</td>
					</tr>
					
					<tr>
						<td colspan="4" align="center">
							<input type="button" value="View Records" onclick="viewBrdApplicationList();"/>
							
							<input type="button" value="Reset" onclick="resetData();"/>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	
		<%
			int totalPage = 0;
			int pageNo = 0;
		%>
		<tr>
			<td>
				<table width="100%" align="right" class="formbg" border="0">
				<s:if test="brdSuperUserPagingFlag">
					<tr>
						<td colspan="11" align="right">
						<table>
							<tr>
								<td id="ctrlShow" width="100%" align="right"><b>Page:</b> 
									<%
									 	totalPage = (Integer) request.getAttribute("totalPage");
									 	pageNo = (Integer) request.getAttribute("pageNo");
 									%> 
 									<a href="#" onclick="callPage('1', 'F', '<%=totalPage%>', 'BRDSuperUser_viewBrdApplicationList.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
									width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPage('P', 'P', '<%=totalPage%>', 'BRDSuperUser_viewBrdApplicationList.action');">
									<img title="Previous Page"
									src="../pages/common/img/previous.gif" width="10" height="10"
									class="iconImage" /> </a> <input type="text" name="pageNoField"
									id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10"
									onkeypress="callPageText(event, '<%=totalPage%>', 'BRDSuperUser_viewBrdApplicationList.action');return numbersOnly();" />
									of <%=totalPage%> <a href="#"
									onclick="callPage('N', 'N', '<%=totalPage%>', 'BRDSuperUser_viewBrdApplicationList.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
									class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'BRDSuperUser_viewBrdApplicationList.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
									width="10" height="10" class="iconImage" /> </a>
								</td>
							</tr>
						</table>
						</td>

						<td width="5%">&nbsp;</td>
					</tr>
				</s:if>
				<tr>
						<td width="5%" class="formth">
							<label name="srNo" id="srNo" ondblclick="callShowDiv(this);"><%=label.get("srNo")%></label>
						</td>
						<td width="10%" class="formth">
							<label name="brdTicketNo" id="brdTicketNo" ondblclick="callShowDiv(this);"><%=label.get("brdTicketNo")%></label>
						</td>
						<td width="10%" class="formth">
							<label name="projectName" id="projectName" ondblclick="callShowDiv(this);"><%=label.get("projectName")%></label>
						</td>
						<td width="10%" class="formth">
							<label name="expectedDate" id="expectedDate" ondblclick="callShowDiv(this);"><%=label.get("expectedDate")%></label> 
						</td>
						<td width="10%" class="formth">
							<label name="currentStage" id="currentStage" ondblclick="callShowDiv(this);"><%=label.get("currentStage")%></label>
						</td>
						<td width="10%" class="formth">
							<label name="pendingWithRole" id="pendingWithRole" ondblclick="callShowDiv(this);"><%=label.get("pendingWithRole")%></label>
						</td>
						<td width="10%" class="formth">
							<label name="status" id="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label>
						</td>
						<td width="10%" class="formth">
							<label name="pendingWithName" id="pendingWithName" ondblclick="callShowDiv(this);"><%=label.get("pendingWithName")%></label>
						</td>
						<td width="10%" class="formth">
							<label name="currentActivityLabel" id="currentActivityLabel" ondblclick="callShowDiv(this);"><%=label.get("currentActivityLabel")%></label>
						</td>
						<td width="10%" class="formth">
							<label name="forecastedDateLabel" id="forecastedDateLabel" ondblclick="callShowDiv(this);"><%=label.get("forecastedDateLabel")%></label>
						</td>
						<td width="5%" class="formth">
							<label name="view" id="view" ondblclick="callShowDiv(this);"><%=label.get("view")%></label>
						</td>
					</tr>
					
					<%
						int count = pageNo * 20 - 20;
					%>
					<s:iterator value="applicationList">
					<tr>
						<td align="center">
							<%=++count%>
						</td>
						<td>
							<s:hidden name="brdApplicationIdItr" />
							<s:property value="brdTicketNoItr"/>
						</td>
						<td>
							<s:property value="projectNameItr"/>
						</td>
						<td nowrap="nowrap" align="center">
							<s:property value="expectedDateItr"/>
						</td>
						<td>
							<s:property value="currentStageItr"/>
						</td>
						<td>
							<s:property value="pendingWithRoleItr"/>
						</td>
						<td>
							<s:property value="statusItr"/>
						</td>
						<td>
							<s:property value="pendingWithNameItr"/>
						</td>
						
						<td>
							<s:property value="currentActivityItr"/>
						</td>
						<td>
							<s:property value="forecastedCompletionDateItr"/>
						</td>
		
						<td nowrap="nowrap">
							<input type="button" value="View" onclick="viewApplication('<s:property value="brdApplicationIdItr" />','<s:property value="statusItr"/>');">
						</td>
					</tr>	
					</s:iterator>
				
				<s:if test="nodataFlag">		
					<tr>
						<td colspan="11" align="center">
							<font color="red">No data to display</font>
						</td>
					</tr>
				</s:if>	
				</table>
			</td>
		</tr>
	</table>
</s:form>

<script>
function viewBrdApplicationList() {
		var fromDate = trim(document.getElementById('paraFrm_fromDate').value);
		var toDate = trim(document.getElementById('paraFrm_toDate').value);
		if(fromDate != "") {
			if(!validateDate('paraFrm_fromDate','fromDateLabel')){
				return false;
			}
			
			if(toDate == "") {
				alert("Please select/enter "+ document.getElementById('toDateLabel').innerHTML.toLowerCase());
				document.getElementById('paraFrm_toDate').focus();
				return false;
			}
		}
		
		if(toDate != "") {
			if(!validateDate('paraFrm_toDate','toDateLabel')){
				return false;
			}
			
			if(fromDate == "") {
				alert("Please select/enter "+ document.getElementById('fromDateLabel').innerHTML.toLowerCase());
				document.getElementById('paraFrm_fromDate').focus();
				return false;
			}
		}
		
		if(fromDate!="" && toDate!="") {
			if(!dateDifferenceEqual(fromDate,toDate,'paraFrm_toDate','fromDateLabel','toDateLabel')) {
				return false;
			}
		}
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = "BRDSuperUser_viewBrdApplicationList.action";
	document.getElementById('paraFrm').submit();
}
 
 
function viewApplication(applicationId, status) {
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = "BusinessRequirementDocument_viewApplicationFromBRDSuperUser.action?applCode="+applicationId+"&statusFromSuperUser="+status;		
	document.getElementById("paraFrm").submit();
}

function resetData() {
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = "BRDSuperUser_reset.action";		
	document.getElementById("paraFrm").submit();
}
</script>