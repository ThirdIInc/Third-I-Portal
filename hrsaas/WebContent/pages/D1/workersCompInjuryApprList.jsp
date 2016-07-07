<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="WorkersCompInjuryAppr" method="post" name="LeaveForm"
	id="paraFrm" theme="simple">
	
		<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">

		<!-- FORM NAME START -->
		<tr>
			<td valign="bottom" class="txt"><s:hidden name="myPage" id="myPage" />
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Workers Comp Injury/Illness </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<%
 	int totalPage = 0;
 	int pageNo = 0;
 %>
	
		
		<!-- 
		<a href="#" onclick="setAction('CC')"> Cancelled
					Applications</a> |
		 -->
		
		<tr>
			<td width="100%"> <table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0"  ><s:hidden name="workersCode"/>	
				<tr>
		<td><a href="#" onclick="setAction('')">Pending
					Application</a> | <a href="#" onclick="setAction('AA')">Approved
					List</a> |  <a href="#" onclick="setAction('RR')">Rejected
					List</a>
					<s:hidden name="flag"/>
					</td>
			</tr>
			</table>
			</td>
		</tr>
		 <tr>
			
		</tr>
		
		<s:if test="%{flag == ''}">
		<tr>
				<td>
				<table width="100%" class="formbg">
					<tr>
					<td colspan="2"><strong> Pending Application List</strong> </td>
					<td colspan="3">
					<table width="100%">
					<tr>
					
					<td width="100%">
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td align="left" colspan="2"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /> 
					
					<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
					<%
						totalPage = (Integer) request.getAttribute("totalPage");
						pageNo = (Integer) request.getAttribute("pageNo");
					%> <a href="#"
						onclick="callPage('1', 'F', '<%=totalPage%>', 'WorkersCompInjuryAppr_input.action');">
					<img title="First Page" src="../pages/common/img/first.gif"
						width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
						onclick="callPage('P', 'P', '<%=totalPage%>', 'WorkersCompInjuryAppr_input.action');">
					<img title="Previous Page" src="../pages/common/img/previous.gif"
						width="10" height="10" class="iconImage" /> </a> <input type="text"
						name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
						maxlength="10"
						onkeypress="callPageText(event, '<%=totalPage%>', 'WorkersCompInjuryAppr_input.action');return numbersOnly();" />
					of <%=totalPage%> <a href="#"
						onclick="callPage('N', 'N', '<%=totalPage%>', 'WorkersCompInjuryAppr_input.action')">
					<img title="Next Page" src="../pages/common/img/next.gif"
						class="iconImage" /> </a>&nbsp; <a href="#"
						onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'WorkersCompInjuryAppr_input.action');">
					<img title="Last Page" src="../pages/common/img/last.gif"
						width="10" height="10" class="iconImage" /> </a></td>

				</tr>
			</table>
			</td>
					
					
					
					</tr>
					</table>
					</td>
					
					</tr>	
					<tr><td colspan="5">
					<table width="100%" class="formbg">				
					
					<tr>
					<td class="formth" width="05%">Sr No </td>
					<td class="formth" width="25%"><label class="set" name="tracking.no" id="tracking.no" ondblclick="callShowDiv(this);">
				<%=label.get("tracking.no")%></label> </td>
					<td class="formth" width="25%"><label class="set" name="employee" id="employee" ondblclick="callShowDiv(this);">
				<%=label.get("employee")%></label> </td>
					<td class="formth" width="25%" align="center"><label class="set" name="aplicationDate" id="aplicationDate" ondblclick="callShowDiv(this);">
				<%=label.get("aplicationDate")%></label> </td>
					<td class="formth" width="25%" align="center">Edit Application </td>
					</tr>
					
					
					<%int i=1; %>
				<s:if test="listDraft">	
					<s:iterator value="listDraft">
					<tr><td  class="sortableTD" > <%=i++ %></td>
					<td  class="sortableTD" ><s:hidden name="ittEmployeeId"/><s:hidden name="ittWorkersCode"/>
					<s:property value="ittEmployeeToken"/></td>
					<td  class="sortableTD" ><s:property value="ittEmployee"/> </td>
					<td   class="sortableTD" align="center"><s:property value="ittApplicationDate"/> </td>
					<td   class="sortableTD" align="center"><input type="submit" value="View/Approve Application" class="token" onclick="editapplicationFun('<s:property value="ittWorkersCode"/>');"> </td>
					</tr>
					</s:iterator>
				</s:if>	
				<s:else>
					<td align="center" colspan="5" nowrap="nowrap">
										<font color="red" >There is no data to display</font>
									</td>
					</s:else>	
						</tr>
					</table
				</td>
				</tr>
				</table>
				</td>
		</tr>
	</s:if>	
						
			
	
		
		<s:if test="%{flag =='AA'}">
			<tr>
				<td>
				<table width="100%" class="formbg">
					<tr>
					<td colspan="2"><strong> Approved Application</strong> </td>
					<td colspan="3">
					<table width="100%">
					<tr>
					
					<td width="100%">
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
										
					<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
					<%
						totalPage = (Integer) request.getAttribute("totalPage");
						pageNo = (Integer) request.getAttribute("pageNo");
					%> <a href="#"
						onclick="callPage('1', 'F', '<%=totalPage%>', 'WorkersCompInjuryAppr_input.action');">
					<img title="First Page" src="../pages/common/img/first.gif"
						width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
						onclick="callPage('P', 'P', '<%=totalPage%>', 'WorkersCompInjuryAppr_input.action');">
					<img title="Previous Page" src="../pages/common/img/previous.gif"
						width="10" height="10" class="iconImage" /> </a> <input type="text"
						name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
						maxlength="10"
						onkeypress="callPageText(event, '<%=totalPage%>', 'WorkersCompInjuryAppr_input.action');return numbersOnly();" />
					of <%=totalPage%> <a href="#"
						onclick="callPage('N', 'N', '<%=totalPage%>', 'WorkersCompInjuryAppr_input.action')">
					<img title="Next Page" src="../pages/common/img/next.gif"
						class="iconImage" /> </a>&nbsp; <a href="#"
						onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'WorkersCompInjuryAppr_input.action');">
					<img title="Last Page" src="../pages/common/img/last.gif"
						width="10" height="10" class="iconImage" /> </a></td>

				</tr>
			</table>
			</td>
					
					
					
					</tr>
					</table>
					</td>
					</tr>
					<tr><td colspan="5">
					<table width="100%" class="formbg">
					
					<tr>
					<td class="formth" width="05%">Sr No </td>
					<td class="formth" width="25%"><label class="set" name="tracking.no" id="tracking.no1" ondblclick="callShowDiv(this);">
				<%=label.get("tracking.no")%></label> </td>
					<td class="formth" width="25%"><label class="set" name="employee" id="employee1" ondblclick="callShowDiv(this);">
				<%=label.get("employee")%></label> </td>
					<td class="formth" width="25%" align="center"><label class="set" name="aplicationDate" id="aplicationDate1" ondblclick="callShowDiv(this);">
				<%=label.get("aplicationDate")%></label> </td>
					<td class="formth" width="25%" align="center">View Application </td>
					</tr>
					
					
				<s:if test="listApprove">	
				<%int a=1; %>	
					<s:iterator value="listApprove">
					<tr><td  class="sortableTD" ><%=a++ %> </td>
					<td  class="sortableTD" ><s:hidden name="ittEmployeeId"/><s:hidden name="ittWorkersCode"/>
					<s:property value="ittEmployeeToken"/></td>
					<td   class="sortableTD"><s:property value="ittEmployee"/> </td>
					<td  class="sortableTD" align="center" ><s:property value="ittApplicationDate"/> </td>
					<td  class="sortableTD"  align="center"><input type="submit" value="View Application" class="token" onclick="editapplicationFun('<s:property value="ittWorkersCode"/>');"> </td>
					</tr>
					</s:iterator>
				</s:if>	
				<s:else>
					<td align="center" colspan="5" nowrap="nowrap">
										<font color="red" >There is no data to display</font>
									</td>
					</s:else>	
						</tr>
					</table
				</td>
				</tr>
				</table>
				</td>
			</tr>	
		</s:if>	
		<s:if test="%{flag =='RR'}">
			<tr>
				<td>
				<table width="100%" class="formbg" border="0">
					<tr>
					<td colspan="2"><strong> Rejected Application</strong> </td>
					<td colspan="3">
					<table width="100%" border="0">
					<tr>
					
					<td width="100%" colspan="4" >
			<table width="100%" cellpadding="0" cellspacing="0" border="0">
				<tr>					
					
					<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
					<%
						totalPage = (Integer) request.getAttribute("totalPage");
						pageNo = (Integer) request.getAttribute("pageNo");
					%> <a href="#"
						onclick="callPage('1', 'F', '<%=totalPage%>', 'WorkersCompInjuryAppr_input.action');">
					<img title="First Page" src="../pages/common/img/first.gif"
						width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
						onclick="callPage('P', 'P', '<%=totalPage%>', 'WorkersCompInjuryAppr_input.action');">
					<img title="Previous Page" src="../pages/common/img/previous.gif"
						width="10" height="10" class="iconImage" /> </a> <input type="text"
						name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
						maxlength="10"
						onkeypress="callPageText(event, '<%=totalPage%>', 'WorkersCompInjuryAppr_input.action');return numbersOnly();" />
					of <%=totalPage%> <a href="#"
						onclick="callPage('N', 'N', '<%=totalPage%>', 'WorkersCompInjuryAppr_input.action')">
					<img title="Next Page" src="../pages/common/img/next.gif"
						class="iconImage" /> </a>&nbsp; <a href="#"
						onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'WorkersCompInjuryAppr_input.action');">
					<img title="Last Page" src="../pages/common/img/last.gif"
						width="10" height="10" class="iconImage" /> </a></td>

				</tr>
			</table>
			</td>
					
					
					
					</tr>
					</table>
					</td>
					</tr>
					<tr><td colspan="5">
					<table width="100%" class="formbg">
					<tr>
					<td class="formth" width="05%">Sr No </td>
					<td class="formth" width="25%"><label class="set" name="tracking.no" id="tracking.no2" ondblclick="callShowDiv(this);">
				<%=label.get("tracking.no")%></label> </td>
					<td class="formth" width="25%"><label class="set" name="employee" id="employee2" ondblclick="callShowDiv(this);">
				<%=label.get("employee")%></label> </td>
					<td class="formth" width="25%" align="center"><label class="set" name="aplicationDate" id="aplicationDate2" ondblclick="callShowDiv(this);">
				<%=label.get("aplicationDate")%></label> </td>
					<td class="formth" width="25%" align="center">View Application </td>
					</tr>
				<s:if test="listReject">	
				<%int r=1; %>	
					<s:iterator value="listReject">
					<tr><td  class="sortableTD" ><%=r++ %> </td>
					<td  class="sortableTD" ><s:hidden name="ittEmployeeId"/><s:hidden name="ittWorkersCode"/>
					<s:property value="ittEmployeeToken"/></td>
					<td   class="sortableTD"><s:property value="ittEmployee"/> </td>
					<td  class="sortableTD" align="center" ><s:property value="ittApplicationDate"/> </td>
					<td  class="sortableTD" align="center" ><input type="submit" value="View Application" class="token" onclick="editapplicationFun('<s:property value="ittWorkersCode"/>');"> </td>
					</tr>
					</s:iterator>
				</s:if>	
				<s:else>
					<td align="center" colspan="5" nowrap="nowrap">
										<font color="red" >There is no data to display</font>
									</td>
					</s:else>
						</tr>
					</table
				</td>
				</tr>
				</table>
				</td>
			</tr>	
		</s:if>	
		
		
		</table>
	
	
</s:form> 

<script>
 function setAction(listType){
 document.getElementById('paraFrm_flag').value=listType;
 		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'WorkersCompInjuryAppr_input.action';
		document.getElementById('paraFrm').submit(); 
 }



	function editapplicationFun(id) {
	document.getElementById('paraFrm_workersCode').value=id;
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'WorkersCompInjuryAppr_editApplication.action';
		document.getElementById('paraFrm').submit();
	}
</script>