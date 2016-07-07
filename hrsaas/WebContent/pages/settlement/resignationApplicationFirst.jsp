<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="ResignationApplication" method="post" name="LeaveForm"
	id="paraFrm" theme="simple">

<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">

		<!-- FORM NAME START -->
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Resignation
					Application Form </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- FORM NAME END -->
		<!-- BUTTON PANEL START -->
		<tr>
			<td width="100%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>

		<!-- BUTTON PANEL END -->
				<tr>
			<td>
			<table class="" width="100%">
				<tr>
					<script>
		    function setAction(listType){
		    
		     if(listType=="p"){
		     
		      	document.getElementById('paraFrm').action='ResignationApplication_input.action';
		      	document.getElementById('paraFrm').submit();
		      
		     }if(listType=="a"){
		     
		      	document.getElementById('paraFrm').action='ResignationApplication_getApprovedList.action';
		      	document.getElementById('paraFrm').submit();
		      
		     }
		    
		     if(listType=="r"){
		        
		      	document.getElementById('paraFrm').action='ResignationApplication_getRejectedList.action';
		      	document.getElementById('paraFrm').submit();
		        
		     } 
		     
		    }
		   </script>
					<td><a href="#" onclick="setAction('p')">Pending
					Application</a> | <a href="#" onclick="setAction('a')">Approved
					List</a> |  <a href="#" onclick="setAction('r')">Rejected
					List</a></td>
				</tr>
			</table>
			</td>
		</tr>
		
			<!--------------------------------------  Draft LIST OF APPLICATIONS [BEGINS]----------------------------->
			
			
			
			<s:if test="%{listType == 'pending'}">

			<!--------- DRAFT SECTION BEGINS - displaying the saved leave applications ------->

			<tr>
				<td>
				<table width="100%" class="formbg">
					<tr>
						<td><b>Draft</b></td>
					</tr>
					<tr>
						<td>
						<table width="100%" class="sorttable">
							<tr>
								<td class="formth" width="5%"><b><label class="set"
									name="sno" id="sno1" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
								</td>
								<td class="formth" width="15%"><b><label class="set"
									name="employee.id" id="employee.id1"
									ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></b>
								</td>
								<td class="formth" width="40%"><b><label class="set"
									name="employee.name" id="employee.name1"
									ondblclick="callShowDiv(this);"><%=label.get("employee.name")%></label></b>
								</td>
								<td class="formth" width="20%"><b><label class="set"
									name="appdate" id="appdate1" ondblclick="callShowDiv(this);"><%=label.get("appdate")%></label></b>
								</td>
								<td class="formth" width="20%"><b>Edit Application</b></td>

							</tr>

							<%
							int i = 1;
							%>
							<s:iterator value="draftList">
								<tr>
									<td class="sortableTD" width="5%" align="center"><%=i%></td>
									<td class="sortableTD" width="15%"><s:hidden
										name="draftResignEmpId" /><s:hidden name="draftResignAppNo" /> <s:hidden
										name="resignAppStatus" /> <s:property value="resignEmpToken" /></td>
									<td class="sortableTD" width="40%"><s:property
										value="resignEmpName" /></td>
									<td class="sortableTD" width="20%" align="center"><s:property
										value="resignAppDate" /></td>
									<td class="sortableTD" width="20%" align="center"><input
										type="button" name="view_Details" class="token"
										value=" Edit Application "
										onclick="viewDetails('<s:property value="draftResignAppNo"/>','<s:property value="resignAppStatus"/>')" /></td>
								</tr>
								<%
								i++;
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
						</td>
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
										<td class="formth" width="5%"><b><label class="set"
											name="sno" id="sno2" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
										</td>
										<td class="formth" width="15%"><b><label class="set"
											name="employee.id" id="employee.id2"
											ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></b>
										</td>
										<td class="formth" width="40%"><b><label class="set"
											name="employee.name" id="employee.name2"
											ondblclick="callShowDiv(this);"><%=label.get("employee.name")%></label></b>
										</td>
										<td class="formth" width="20%"><b><label class="set"
											name="appdate" id="appdate2" ondblclick="callShowDiv(this);"><%=label.get("appdate")%></label></b>
										</td>
										<td class="formth" width="20%"><b>View Application</b></td>

									</tr>

									<%
									int me = 1;
									%>
									<s:iterator value="submitList">
										<tr>
											<td class="sortableTD" width="5%" align="center"><%=me%></td>
											<td class="sortableTD" width="15%"><s:hidden
												name="submitEmpId" /><s:hidden name="submitResignAppNo" />
											<s:hidden name="resignAppStatus" /> <s:property
												value="resignEmpToken" /></td>
											<td class="sortableTD" width="40%"><s:property
												value="resignEmpName" /></td>
											<td class="sortableTD" width="20%" align="center"><s:property
												value="resignAppDate" /></td>
											<td class="sortableTD" width="20%" align="center"><input
												type="button" name="view_Details" class="token"
												value=" View Application "
												onclick="viewDetails('<s:property value="submitResignAppNo"/>','<s:property value="resignAppStatus"/>')" /></td>
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

			<!--------- SUBMIT SECTION ENDS - displaying the sent leave applications -------->

			<!--------- RETURN SECTION BEGINS - displaying the returned leave applications --->

			<tr>
				<td>
				<table width="100%" class="formbg">
					<tr>
						<td><b>Withdrawn Applications </b></td>
					</tr>
					<tr>
						<td>
						<table width="100%" class="sorttable" border="0">

							<tr>
								<td>
								<table width="100%" class="sorttable">
									<tr>
										<td class="formth" width="5%"><b><label class="set"
											name="sno" id="sno3" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
										</td>
										<td class="formth" width="15%"><b><label class="set"
											name="employee.id" id="employee.id3"
											ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></b>
										</td>
										<td class="formth" width="40%"><b><label class="set"
											name="employee.name" id="employee.name3"
											ondblclick="callShowDiv(this);"><%=label.get("employee.name")%></label></b>
										</td>
										<td class="formth" width="20%"><b><label class="set"
											name="appdate" id="appdate3" ondblclick="callShowDiv(this);"><%=label.get("appdate")%></label></b>
										</td>
										<td class="formth" width="20%"><b>View Application</b></td>

									</tr>

									<%
									int b = 1;
									%>
							 		<s:iterator value="withdrawnList">
										<tr>
											<td class="sortableTD" width="5%" align="center"><%=b%></td>
											<td class="sortableTD" width="15%"><s:hidden
												name="withdrawnEmpId" /><s:hidden name="withdrawnAppNo" />
											<s:hidden name="resignAppStatus" /> <s:property
												value="resignEmpToken" /></td>
											<td class="sortableTD" width="40%"><s:property
												value="resignEmpName" /></td>
											<td class="sortableTD" width="20%" align="center"><s:property
												value="resignAppDate" /></td>
											<td class="sortableTD" width="20%" align="center"><input
												type="button" name="view_Details" class="token"
												value=" View Application "
												onclick="viewDetails('<s:property value="withdrawnAppNo"/>','<s:property value="resignAppStatus"/>')" /></td>
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

		<!--------- RETURN SECTION ENDS - displaying the returned leave applications ----->
		
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
						<tr>
								<td>
								<table width="100%" class="sorttable">
									<tr>
										<td class="formth" width="5%"><b><label class="set"
											name="sno" id="sno4" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
										</td>
										<td class="formth" width="15%"><b><label class="set"
											name="employee.id" id="employee.id4"
											ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></b>
										</td>
										<td class="formth" width="40%"><b><label class="set"
											name="employee.name" id="employee.name4"
											ondblclick="callShowDiv(this);"><%=label.get("employee.name")%></label></b>
										</td>
										<td class="formth" width="20%"><b><label class="set"
											name="appdate" id="appdate4" ondblclick="callShowDiv(this);"><%=label.get("appdate")%></label></b>
										</td>
										<td class="formth" width="20%"><b>View Application</b></td>

									</tr>
									
									
									<%
									int approvedCount = 1;
									%>
									
										
										<s:iterator value="approvedList">
											<tr>
												<td class="sortableTD" width="5%" align="center"><%=approvedCount%></td>
												<td class="sortableTD" width="15%"><s:hidden
													name="approvedResignEmpId" /><s:hidden name="approvedResignAppNo" />
												<s:hidden name="resignAppStatus" /> <s:property
													value="resignEmpToken" /></td>
												<td class="sortableTD" width="40%"><s:property
													value="resignEmpName" /></td>
												<td class="sortableTD" width="20%" align="center"><s:property
													value="resignAppDate" /></td>
												<td class="sortableTD" width="20%" align="center"><input
													type="button" name="view_Details" class="token"
													value=" View Application "
													onclick="viewDetails('<s:property value="approvedResignAppNo"/>','<s:property value="resignAppStatus"/>')" /></td>
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
			
			
		<!-- Approved Application list table ends  -->
		
		
			<!-------------------------------------- REJECTED APPLICATIONS LIST [BEGINS]----------------------------->

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
										<td class="formth" width="5%"><b><label class="set"
											name="sno" id="sno6" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
										</td>
										<td class="formth" width="15%"><b><label class="set"
											name="employee.id" id="employee.id6"
											ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></b>
										</td>
										<td class="formth" width="40%"><b><label class="set"
											name="employee.name" id="employee.name6"
											ondblclick="callShowDiv(this);"><%=label.get("employee.name")%></label></b>
										</td>
										<td class="formth" width="20%"><b><label class="set"
											name="appdate" id="appdate6" ondblclick="callShowDiv(this);"><%=label.get("appdate")%></label></b>
										</td>
										<td class="formth" width="20%"><b>View Application</b></td>

									</tr>
								  	
									
									<%
									int rejectedCount = 1;
									%>
									

										<s:iterator value="rejectedList">
											<tr>
												<td class="sortableTD" width="5%" align="center"><%= rejectedCount %></td>
												<td class="sortableTD" width="15%"><s:hidden
													name="rejectedResignEmpId" /><s:hidden name="rejectedResignAppNo" />
												<s:hidden name="resignAppStatus" /> <s:property
													value="resignEmpToken" /></td>
												<td class="sortableTD" width="40%"><s:property
													value="resignEmpName" /></td>
												<td class="sortableTD" width="20%" align="center"><s:property
													value="resignAppDate" /></td>
												<td class="sortableTD" width="20%" align="center"><input
													type="button" name="view_Details" class="token"
													value=" View Application "
													onclick="viewDetails('<s:property value="rejectedResignAppNo"/>','<s:property value="resignAppStatus"/>')" /></td>
											</tr>
											
												<%
												rejectedCount++;
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
			
			<!-- rejected list table ends -->
		
		<!-- BUTTON PANEL START -->
		<tr>
			<td width="100%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>

		<!-- BUTTON PANEL END -->
		
		</table>
</s:form>


<script>


function addapplicationFun() {
	
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ResignationApplication_addNew.action';
		document.getElementById('paraFrm').submit();
	}
	 
	
	function viewDetails(resignAppNo,status){
	   //alert("resignAppNo---------"+resignAppNo);
	 // alert("status----------"+status);
	 	      	document.getElementById('paraFrm').action='ResignationApplication_retriveDetails.action?resignApplicationCode='+resignAppNo+'&resignAppStatus='+status;
		      	document.getElementById('paraFrm').submit();
		     
		     
		    }


</script>