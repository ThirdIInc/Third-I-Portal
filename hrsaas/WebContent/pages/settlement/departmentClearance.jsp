<%@ taglib prefix="s" uri="/struts-tags"%>

<%@include file="/pages/common/labelManagement.jsp"%>


<s:form action="DepartmentClearance" validate="true" id="paraFrm"
	 theme="simple">
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
					<td width="93%" class="txt"><strong class="text_head">Department Clearance</strong></td>
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
		     
		      	document.getElementById('paraFrm').action='DepartmentClearance_callstatus.action';
		      	document.getElementById('paraFrm').submit();
		      
		     }if(listType=="c"){
		     
		      	document.getElementById('paraFrm').action='DepartmentClearance_getClearedList.action';
		      	document.getElementById('paraFrm').submit();
		      
		     }
		 
		     
		    }
		   </script>
					<td><a href="#" onclick="setAction('p')">Pending
					List</a> | <a href="#" onclick="setAction('c')">Cleared 
					List</a> </td>
				</tr>
			</table>
			</td>
		</tr>
		
		
			<!--------- PENDING APPLICATION SECTION BEGINS - displaying the pending leave applications ------->

		<s:if test="%{listType == 'pending'}">
			<tr>
				<td>
				<table width="100%" class="formbg" border="0">
					<tr>
						<td><b>Pending Department Clearance List</b></td>
					</tr>

					<tr>
						<td>
						<table width="100%">
					 		 
							<tr>
								<td>
								<table width="100%" class="sorttable" border="0">
									<tr>
										<td width="5%" valign="top" class="formth" nowrap="nowrap"><label
											name="srno" id="srno" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></td>
										<td width="15%" valign="top" class="formth"><label
											name="employee.id" id="employee.id"
											ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></td>
										<td width="40%" valign="top" class="formth"><label
											name="employeeName" id="employeeName" ondblclick="callShowDiv(this);"><%=label.get("employeeName")%></label></td>
										<td width="20%" valign="top" class="formth" nowrap="nowrap"><label
											name="appdate" id="appdate" ondblclick="callShowDiv(this);"><%=label.get("appdate")%></label></td>
										<td width="20%" valign="top" class="formth" nowrap="nowrap"><label
											name="details" id="details" ondblclick="callShowDiv(this);"><%=label.get("details")%></label></td>

									</tr>
									
									
									<%
									int b = 1;
									%>
								 
									<s:iterator value="pendingList">

										<tr class="sortableTD">
											<td width="5%" align="left" class="sortableTD"><%= b %></td>
											<td class="sortableTD" width="15%"><s:property
												value="tokenNo" /><s:hidden name="resignAppNo" /> 
												 <s:hidden name="empCode" /><s:hidden
												name="pendingStatus" /></td>
											<td class="sortableTD" width="40%"><s:property
												value="empName" /></td>
											<td class="sortableTD" width="20%" nowrap="nowrap"
												align="center"><s:property value="date" /><s:hidden
												name="date" /></td>
											<td class="sortableTD" width="20%" nowrap="nowrap" align="center"><input
												type="button" name="view_Details" class="token"
												value=" View / Approve "
												onclick="viewDetails('<s:property value="resignAppNo"/>','<s:property value="pendingStatus"/>')" /> </td>


										</tr>
										
											<%
										b++;
										%>
										 
									</s:iterator>
								 
									
									<%
									if (b == 1) {
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

			<!--------- PENDING APPLICATION SECTION ENDS - displaying the pending leave applications ------->
			
			
			<!-- end of cleared list application -->
			
			<s:if test="%{listType == 'cleared'}">
			<tr>
				<td>
				<table width="100%" class="formbg" border="0">
					<tr>
						<td><b>Department Clear List</b></td>
					</tr>

					<tr>
						<td>
						<table width="100%">
					 		 
							<tr>
								<td>
								<table width="100%" class="sorttable" border="0">
									<tr>
										<td width="5%" valign="top" class="formth" nowrap="nowrap"><label
											name="srno" id="srno" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></td>
										<td width="15%" valign="top" class="formth"><label
											name="employee.id" id="employee.id"
											ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></td>
										<td width="40%" valign="top" class="formth"><label
											name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
										<td width="20%" valign="top" class="formth" nowrap="nowrap"><label
											name="appdate" id="appdate" ondblclick="callShowDiv(this);"><%=label.get("appdate")%></label></td>
										<td width="20%" valign="top" class="formth" nowrap="nowrap"><label
											name="details" id="details" ondblclick="callShowDiv(this);"><%=label.get("details")%></label></td>

									</tr>
									
									
									<%
									int c = 1;
									%>
								 
									<s:iterator value="clearList">

										<tr class="sortableTD">
											<td width="5%" align="left" class="sortableTD"><%= c %></td>
											<td class="sortableTD" width="15%"><s:property
												value="tokenNo" /><s:hidden name="clearResignAppNo" /> 
												 <s:hidden name="clearEmpCode" /><s:hidden
												name="clearStatus" /></td>
											<td class="sortableTD" width="40%"><s:property
												value="empName" /></td>
											<td class="sortableTD" width="20%" nowrap="nowrap"
												align="center"><s:property value="date" /><s:hidden
												name="date" /></td>
											<td class="sortableTD" width="20%" nowrap="nowrap" align="center"><input
												type="button" name="view_Details" class="token"
												value=" View "
												onclick="viewDetails('<s:property value="clearResignAppNo"/>','<s:property value="clearStatus"/>')" /> </td>


										</tr>
										
											<%
										c++;
										%>
										 
									</s:iterator>
								 
									
									<%
									if (c == 1) {
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
			
			<!-- end of cleared list application -->
	</table>
</s:form>


<script>
	
	
	     function viewDetails(resignAppNo,status)
		 {
		 
		// alert("resignAppNo----------------"+resignAppNo);
	   //   alert("status------------------------"+status);
	 
			document.getElementById('paraFrm').action = 'DepartmentClearance_retriveForApproval.action?resignApplicationNo='+resignAppNo+'&applicationStatus='+status;
			document.getElementById('paraFrm').submit();
		 	document.getElementById('paraFrm').target = "_self";
		}

    


</script>