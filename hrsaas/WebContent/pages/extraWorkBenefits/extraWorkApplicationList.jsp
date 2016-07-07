<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="ExtraWorkApplication" validate="true" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Extra
					Work Benefits Application</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<s:hidden name="myPageRejected" id="myPageRejected" />
		<s:hidden name="myPage" id="myPage" />

		<tr>
			<td width="100%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>

		<tr>
			<td>
			<table class="" width="100%">
				<tr>
					<script>
		    function setAction(listType){
			    if(listType=="p"){
			      	document.getElementById('paraFrm').action='ExtraWorkApplication_input.action';
			      	document.getElementById('paraFrm').submit();
			    }
			    if(listType=="a"){
			      	document.getElementById('paraFrm').action='ExtraWorkApplication_getApprovedList.action';
			      	document.getElementById('paraFrm').submit();
			     }
			     if(listType=="r"){
			      	document.getElementById('paraFrm').action='ExtraWorkApplication_getRejectedList.action';
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
									name="employee" id="employee1"
									ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></b>
								</td>
								<td class="formth" width="20%"><b><label class="set"
									name="app.dateate" id="app.dateate1" ondblclick="callShowDiv(this);"><%=label.get("app.dateate")%></label></b>
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
										name="draftEmpId" /><s:hidden name="draftExtWorkAppNo" /> <s:hidden
										name="extAppStatus" /> <s:property value="extEmpToken" /></td>
									<td class="sortableTD" width="40%"><s:property
										value="extEmpName" /></td>
									<td class="sortableTD" width="20%" align="center"><s:property
										value="extAppDate" /></td>
									<td class="sortableTD" width="20%" align="center"><input
										type="button" name="view_Details" class="token"
										value=" Edit Application "
										onclick="viewDetails('<s:property value="draftExtWorkAppNo"/>','<s:property value="extAppStatus"/>')" /></td>
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
											name="employee" id="employee2"
											ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></b>
										</td>
										<td class="formth" width="20%"><b><label class="set"
											name="app.dateate" id="app.dateate2" ondblclick="callShowDiv(this);"><%=label.get("app.dateate")%></label></b>
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
												name="submitEmpId" /><s:hidden name="submitExtWorkAppNo" />
											<s:hidden name="extAppStatus" /> <s:property
												value="extEmpToken" /></td>
											<td class="sortableTD" width="40%"><s:property
												value="extEmpName" /></td>
											<td class="sortableTD" width="20%" align="center"><s:property
												value="extAppDate" /></td>
											<td class="sortableTD" width="20%" align="center"><input
												type="button" name="view_Details" class="token"
												value=" View Application "
												onclick="viewDetails('<s:property value="submitExtWorkAppNo"/>','<s:property value="extAppStatus"/>')" /></td>
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
						<td><b>Returned Applications (Please view the comments,
						update application and submit again) </b></td>
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
											name="employee" id="employee3"
											ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></b>
										</td>
										<td class="formth" width="20%"><b><label class="set"
											name="app.dateate" id="app.dateate3" ondblclick="callShowDiv(this);"><%=label.get("app.dateate")%></label></b>
										</td>
										<td class="formth" width="20%"><b>Edit Application</b></td>

									</tr>

									<%
									int b = 1;
									%>
									<s:iterator value="returnList">
										<tr>
											<td class="sortableTD" width="5%" align="center"><%=b%></td>
											<td class="sortableTD" width="15%"><s:hidden
												name="returnEmpId" /><s:hidden name="returnExtWorkAppNo" />
											<s:hidden name="extAppStatus" /> <s:property
												value="extEmpToken" /></td>
											<td class="sortableTD" width="40%"><s:property
												value="extEmpName" /></td>
											<td class="sortableTD" width="20%" align="center"><s:property
												value="extAppDate" /></td>
											<td class="sortableTD" width="20%" align="center"><input
												type="button" name="view_Details" class="token"
												value=" Edit Application "
												onclick="viewDetails('<s:property value="returnExtWorkAppNo"/>','<s:property value="extAppStatus"/>')" /></td>
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
										onclick="callPage('1', 'F', '<%=totalPage%>', 'ExtraWorkApplication_getApprovedList.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPage('P', 'P', '<%=totalPage%>', 'ExtraWorkApplication_getApprovedList.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text" name="pageNoField"
										id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10"
										onkeypress="callPageText(event, '<%=totalPage%>', 'ExtraWorkApplication_getApprovedList.action');return numbersOnly();" />
									of <%=totalPage%> <a href="#"
										onclick="callPage('N', 'N', '<%=totalPage%>', 'ExtraWorkApplication_getApprovedList.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'ExtraWorkApplication_getApprovedList.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
								</tr>
							</s:if>
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
											name="employee" id="employee4"
											ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></b>
										</td>
										<td class="formth" width="20%"><b><label class="set"
											name="app.dateate" id="app.dateate4" ondblclick="callShowDiv(this);"><%=label.get("app.dateate")%></label></b>
										</td>
										<td class="formth" width="20%"><b>View Application</b></td>

									</tr>
									<s:if test="approveLength">
										<%
										int cn = pageNo * 20 - 20;
										%>
										<s:iterator value="approvedList">
											<tr>
												<td class="sortableTD" width="5%" align="center"><%=++cn%></td>
												<td class="sortableTD" width="15%"><s:hidden
													name="approvedEmpId" /><s:hidden name="approvedExtWorkAppNo" />
												<s:hidden name="extAppStatus" /> <s:property
													value="extEmpToken" /></td>
												<td class="sortableTD" width="40%"><s:property
													value="extEmpName" /></td>
												<td class="sortableTD" width="20%" align="center"><s:property
													value="extAppDate" /></td>
												<td class="sortableTD" width="20%" align="center"><input
													type="button" name="view_Details" class="token"
													value=" View Application "
													onclick="viewDetails('<s:property value="approvedExtWorkAppNo"/>','<s:property value="extAppStatus"/>')" /></td>
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
								onclick="callPageRejected('1', 'F', '<%=totalPageRejected%>', 'ExtraWorkApplication_getRejectedList.action');">
							<img title="First Page" src="../pages/common/img/first.gif"
								width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPageRejected('P', 'P', '<%=totalPageRejected%>', 'ExtraWorkApplication_getRejectedList.action');">
							<img title="Previous Page" src="../pages/common/img/previous.gif"
								width="10" height="10" class="iconImage" /> </a> <input type="text"
								name="pageNoField3" id="pageNoField3" size="3"
								value="<%=PageNoRejected%>" maxlength="10"
								onkeypress="callPageTextReject(event, '<%=totalPageRejected%>', 'ExtraWorkApplication_getRejectedList.action');return numbersOnly();" />
							of <%=totalPageRejected%> <a href="#"
								onclick="callPageRejected('N', 'N', '<%=totalPageRejected%>', 'ExtraWorkApplication_getRejectedList.action')">
							<img title="Next Page" src="../pages/common/img/next.gif"
								class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPageRejected('<%=totalPageRejected%>', 'L', '<%=totalPageRejected%>', 'ExtraWorkApplication_getRejectedList.action');">
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
										<td class="formth" width="5%"><b><label class="set"
											name="sno" id="sno6" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
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
											name="app.dateate" id="app.dateate6" ondblclick="callShowDiv(this);"><%=label.get("app.dateate")%></label></b>
										</td>
										<td class="formth" width="20%"><b>View Application</b></td>

									</tr>
									<s:if test="rejectedLength">
										<%
										int cn3 = PageNoRejected * 20 - 20;
										%>

										<s:iterator value="rejectedList">
											<tr>
												<td class="sortableTD" width="5%" align="center"><%=++cn3%></td>
												<td class="sortableTD" width="15%"><s:hidden
													name="rejectedEmpId" /><s:hidden name="rejectedExtWorkAppNo" />
												<s:hidden name="extAppStatus" /> <s:property
													value="extEmpToken" /></td>
												<td class="sortableTD" width="40%"><s:property
													value="extEmpName" /></td>
												<td class="sortableTD" width="20%" align="center"><s:property
													value="extAppDate" /></td>
												<td class="sortableTD" width="20%" align="center"><input
													type="button" name="view_Details" class="token"
													value=" View Application "
													onclick="viewDetails('<s:property value="rejectedExtWorkAppNo"/>','<s:property value="extAppStatus"/>')" /></td>
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
function addapplicationFun() {
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = 'ExtraWorkApplication_addNew.action';
	document.getElementById('paraFrm').submit();
}

function viewDetails(extAppNo,status){
    // alert("leaveAppNo"+leaveAppNo);
    // alert("status"+status);
   	document.getElementById('paraFrm').action='ExtraWorkApplication_retrieveDetails.action?extWrkApplCode='+extAppNo+'&extWrkStatus='+status;
   	document.getElementById('paraFrm').submit();
}
</script>