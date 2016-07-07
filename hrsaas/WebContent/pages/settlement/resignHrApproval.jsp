<%@ taglib prefix="s" uri="/struts-tags"%>

<%@include file="/pages/common/labelManagement.jsp"%>
<script language="JavaScript" type="text/javascript"
	src="include/javascript/sorttable.js"></script>

<s:form action="ResignationHRApproval" validate="true" id="paraFrm"
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
					<td width="93%" class="txt"><strong class="text_head">Resignation HR 
					Approval </strong></td>
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
		     
		      	document.getElementById('paraFrm').action='ResignationHRApproval_callstatus.action';
		      	document.getElementById('paraFrm').submit();
		      
		     }if(listType=="a"){
		     
		      	document.getElementById('paraFrm').action='ResignationHRApproval_getApprovedList.action';
		      	document.getElementById('paraFrm').submit();
		      
		     }
		   if(listType=="r"){
		     
		      	document.getElementById('paraFrm').action='ResignationHRApproval_getRejectedList.action';
		      	document.getElementById('paraFrm').submit();
		      
		     }
		     
		    }
		   </script>
					<td><a href="#" onclick="setAction('p')">Pending
					Application</a> | <a href="#" onclick="setAction('a')">Approved
					List</a> | <a href="#" onclick="setAction('r')">Rejected List</a></td>
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
						<td><b>Pending Application List</b></td>
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
									int b = 1;
									%>
								 
									<s:iterator value="pendingList">

										<tr class="sortableTD">
											<td width="5%" align="left" class="sortableTD"><%= b %></td>
											<td class="sortableTD" width="15%"><s:property
												value="tokenNo" /><s:hidden name="resignAppNo" /><s:hidden
												name="level" /><s:hidden name="empCode" /><s:hidden
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
			
		
				<!--------- APPROVED LEAVE APPLICATION SECTION STARTS  ------->

		<s:if test="%{listType == 'approved'}">

			<tr>
				<td>
				<table width="100%" class="formbg">
					<tr>
						<td><b>Approved  Applications List</b></td>
					</tr>
			 				 
					<tr>
						<td>
						<table width="100%">
							
							<tr>
								<td>
								<table width="100%" class="sorttable">
									<tr>
										<td class="formth" width="5%" nowrap="nowrap"><b><label class="set"
											name="srno" id="srno4" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></b>
										</td>
										<td class="formth" width="15%"><b><label class="set"
											name="employee.id" id="employee.id4"
											ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></b>
										</td>
										<td class="formth" width="40%"><b><label class="set"
											name="employee" id="employee4"
											ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></b>
										</td>
										<td class="formth" width="20%"><b><label class="set"
											name="appdate" id="appdate4" ondblclick="callShowDiv(this);"><%=label.get("appdate")%></label></b>
										</td>
										<td class="formth" width="20%"><b>View Application</b></td>

									</tr>
									
										
									<%
									int approvedCount = 1;
									%>
								 
									 
									<s:iterator value="appList">
										<tr>
											<td class="sortableTD" width="5%" align="center"><%= approvedCount %></td>
											<td class="sortableTD" width="15%"><s:hidden
												name="appEmpId" /><s:hidden name="appResignAppNo" /><s:hidden
												name="appLevel" /> <s:hidden name="appStatus" /> <s:property
												value="appEmpToken" /></td>
											<td class="sortableTD" width="40%"><s:property
												value="appEmpName" /></td>
											<td class="sortableTD" width="20%" align="center"><s:property
												value="appResignAppDate" /></td>
											<td class="sortableTD" width="20%" nowrap="nowrap" align="center">
											
											<input
												type="button" name="view_Details" class="token"
												value=" Details "
												onclick="viewDetails('<s:property value="appResignAppNo"/>','<s:property value="appStatus"/>')" /> 
											
											</td>
										</tr>
										
										<%
										
										approvedCount++;
										%>
									 
									</s:iterator>
								<%
									if (approvedCount == 1) {
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

		<!--------- APPROVED LEAVE APPLICATION SECTION ENDS  ------->
		
		
			<!--------- REJECTED LEAVE APPLICATION SECTION STARTS  ------->
		<s:if test="%{listType == 'rejected'}">
			<tr>
				<td>
				<table width="100%" class="formbg">
					<tr>
						<td><b>Rejected Applications List</b></td>
					</tr>
					<tr>
						<td>
						<table width="100%">
					 
							<tr>
								<td>
								<table width="100%" class="sorttable">
									<tr>
										<td class="formth" width="5%" nowrap="nowrap"><b><label class="set"
											name="srno" id="srno6" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></b>
										</td>
										<td class="formth" width="15%"><b><label class="set"
											name="employee.id" id="employee.id6"
											ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></b>
										</td>
										<td class="formth" width="40%"><b><label class="set"
											name="employee" id="employee6"
											ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></b>
										</td>
										<td class="formth" width="20%"><b><label class="set"
											name="appdate" id="appdate6" ondblclick="callShowDiv(this);"><%=label.get("appdate")%></label></b>
										</td>
										<td class="formth" width="20%"><b>View Application</b></td>

									</tr>
									
									<%
									int rejectedCount = 1;
									%>
								 
						 
									<s:iterator value="rejList">
										<tr>
											<td class="sortableTD" width="5%" align="center"><%= rejectedCount %></td>
											<td class="sortableTD" width="15%"><s:hidden
												name="rejEmpId" /><s:hidden name="rejResignAppNo" /><s:hidden
												name="rejLevel" /> <s:hidden name="rejStatus" /> <s:property
												value="rejEmpToken" /></td>
											<td class="sortableTD" width="40%"><s:property
												value="rejEmpName" /></td>
											<td class="sortableTD" width="20%" align="center"><s:property
												value="rejResignAppDate" /></td>
											<td class="sortableTD" width="20%" nowrap="nowrap" align="center">
											
											
											<input
												type="button" name="view_Details" class="token"
												value=" Details "
												onclick="viewDetails('<s:property value="rejResignAppNo"/>','<s:property value="rejStatus"/>')" /> 
											
											
											</td>
										</tr>
										
										<%
										
										rejectedCount ++ ;
										%>
										 
									</s:iterator>
							 
									<%
									if (rejectedCount == 1) {
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
		
		
		
	</table>
	
	

</s:form>


<script>


 function viewDetails(resignAppNo,status)
		 {
		 
		// alert("resignAppNo----------------"+resignAppNo);
	     // alert("status------------------------"+status);
		 
	   // var wind = window.open('','wind','width=800,height=500,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
		//document.getElementById('paraFrm').target = "wind";
		document.getElementById('paraFrm').action = 'ResignationHRApproval_retriveForApproval.action?resignApplicationNo='+resignAppNo+'&applicationStatus='+status;
		document.getElementById('paraFrm').submit();
		 document.getElementById('paraFrm').target = "_self";
		}


</script>