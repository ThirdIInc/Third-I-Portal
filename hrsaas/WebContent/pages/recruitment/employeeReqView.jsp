<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="java.util.HashMap"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script language="JavaScript" type="text/javascript"
	src="..pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/jquery-1.2.2.pack.js"></script>

<s:form action="EmployeeRequi" validate="true" id="paraFrm"
	 theme="simple">
	<table width="100%" border="0" align="right" class="formbg">
		<tr>
			<td colspan="3">
			<table width="100%" cellpadding="0" cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/common/css/default/images/review_shared.gif"
						width="25" height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Manpower
					Requisition </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:hidden name="source" id="source" />
		<s:hidden name="leadTimeFlag" />
		<s:hidden name="addNewFlag" />
		<s:hidden name="viewDom" />
		<s:hidden name="reqFlag" />

		<s:hidden name="commentFlag" />
		<s:hidden name="viewCert" />
		<s:hidden name="viewQua" />
		<s:hidden name="viewSkill" />
		<s:hidden name="reqsLevel" />
		<s:hidden name="dashletFlag" />
		<s:hidden name="reqCode" />
		<s:hidden name="reqNameSer" />
		<s:hidden name="reqDateSer" />
		<s:hidden name="reqPosSer" />
		<s:hidden name="reqHireSer" />
		<s:hidden name="updateFirstFlag" />
		<s:hidden name="hiddenStatus" />
		<s:hidden name="createNewFlag" />
		<s:hidden name="offerAppointFlag" />

		<s:hidden name="position" />
		<s:hidden name="division" />
		<s:hidden name="branch" />
		<s:hidden name="department" />
		<s:hidden name="requisitionName" />
		<s:hidden name="requisitionCode" />

		<s:hidden name="positionCode" />
		<s:hidden name="divisionCode" />
		<s:hidden name="branchCode" />
		<s:hidden name="deptCode" />

		<s:hidden name="reqsLevel" />
		<s:hidden name="formAction" id="formAction" />
		<s:hidden name="statusKey" id="statusKey" />
		<s:hidden name="formFlag" id="formFlag" />
		<s:hidden name="backFlag" id="backFlag" />
		<s:hidden name="hiddenApproveStatus" />
		<s:hidden name="jdEffDate" />
		<s:hidden name="srNo" />
		<s:hidden name="replaceEmpId" id="replaceEmpId" />
		<!-- Added by Nilesh  -->
		<s:hidden name="employeeId" />
		<s:hidden name="employeeName" />
		<s:hidden name="employeeToken" />

		<s:hidden name="firstApproverCode" />
		<s:hidden name="firstApproverToken" />
		<s:hidden name="approverName" />

		<s:hidden name="serialNo" />
		<s:hidden name="keepInformedEmpNameItt" />
		<s:hidden name="empid" />
		<s:hidden name="checkRemove" />
		<!-- Added by Nilesh  End-->

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /> <s:if
						test="%{offerAppointFlag}">
						<s:submit cssClass="token"
							action="EmployeeRequi_backToOfferAppoint" theme="simple"
							value='Back To %{createNewFlag}' onclick="return callView()" />
					</s:if></td>
					<td width="22%"></td>
				</tr>
			</table>
			<label></label></td>
		</tr>
		<s:hidden name="myPage" id="myPage" />
		<s:hidden name="myPage1" />
		<s:hidden name="show" />
		<s:hidden name="saveFirstFlag" />
		<s:hidden name="saveSecondFlag" />
		<s:if test="listFlag">
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td colspan="2">
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="formbg">
							<tr>
								<td>
								<table width="99%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td height="27" class="formtxt"><a
											href="EmployeeRequi_showApplicationList.action?status=P">Pending
										List </a> | <a
											href="EmployeeRequi_showApplicationList.action?status=A">Approved
										List</a> | <a
											href="EmployeeRequi_showApplicationList.action?status=R">Rejected
										List</a> | <a
											href="EmployeeRequi_showApplicationList.action?status=S">Send
										Back List</a> | <a
											href="EmployeeRequi_showApplicationList.action?status=H">On
										Hold List</a></td>
									</tr>
								</table>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				<label></label></td>
			</tr>
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td>
						<table width="99%" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td height="27" class="formtxt"><strong> <%
 								String status = (String) request.getAttribute("listType");
 								if (status != null) {
 								out.println(status);
 								} else {
 									out.println("Pending List");
 								}
 								%> </strong></td>
								<td align="right"><b>Page:</b> <%
 									int total1 = 0;
 									int PageNo1 = 0;
 									try {
 											total1 = (Integer) request.getAttribute("abc");
 											System.out.println("val of rowCont" + total1);
 											PageNo1 = (Integer) request.getAttribute("xyz");
 									} catch (Exception e) {
 											e.printStackTrace();
 									}
									 %> <%
									 if (!(PageNo1 == 1)) {
 									%><a href="#" onclick="callPage('1','<%=status %>');"> <img
									src="../pages/common/img/first.gif" width="10" height="10"
									class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="previousPage('<%=status %>')"><img
									src="../pages/common/img/previous.gif" width="10" height="10"
									class="iconImage" /></a> <%
 										}
 								if (total1 < 5) {
 									for (int i = 1; i <= total1; i++) {
 										%> <a href="#" onclick="callPage('<%=i %>','<%=status %>');"> <%
 						if (PageNo1 == i) {
 %> <b><u><%=i%></u></b> <%
 } else
 %><%=i%> </a> <%
 	}
 	}

 	if (total1 >= 5) {
 		for (int i = 1; i <= 5; i++) {
 %> <a href="#" onclick="callPage('<%=i %>','<%=status %>');"> <%
 if (PageNo1 == i) {
 %> <b><u><%=i%></u></b> <%
 } else
 %><%=i%> </a> <%
 	}
 	}
 	if (!(PageNo1 == 1)) {
 %>...<a href="#" onclick="nextPage('<%=status %>')"> <img
									src="../pages/common/img/next.gif" class="iconImage" /> </a>
								&nbsp;<a href="#"
									onclick="callPage('<%=total1%>','<%=status %>');"> <img
									src="../pages/common/img/last.gif" width="10" height="10"
									class="iconImage" /></a> <%
 }
 %> <select name="selectname" onchange="onPage('<%=status %>')"
									id="selectname">
									<%
									for (int i = 1; i <= total1; i++) {
									%>

									<option value="<%=i%>"><%=i%></option>
									<%
									}
									%>
								</select>
						</table>
						</td>
					</tr>
					<tr>
						<td colspan="1">
						<table width="100%" border="0" cellpadding="1" cellspacing="1"
							class="sortable">
							<tr>
								<td width="6%" valign="top" class="formth"><label
									class="set" name="sr" id="sr" ondblclick="callShowDiv(this);"><%=label.get("sr")%></label></td>
								<td width="13%" valign="top" class="formth"><label
									class="set" name="reqs.code" id="reqs.code"
									ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label>
								</td>
								<td width="13%" valign="top" class="formth"><label
									class="set" name="reqdthead" id="reqdthead"
									ondblclick="callShowDiv(this);"><%=label.get("reqdthead")%></label></td>
								<td width="15%" valign="top" class="formth"><label
									class="set" name="appliedfor" id="appliedfor"
									ondblclick="callShowDiv(this);"><%=label.get("appliedfor")%></label></td>
								<td width="15%" valign="top" class="formth"><label
									class="set" name="applyhead" id="applyhead"
									ondblclick="callShowDiv(this);"><%=label.get("applyhead")%></label></td>
								<td width="15%" valign="top" class="formth"><label
									class="set" name="hiring.mgr" id="hiring.mgr"
									ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%></label>
								</td>

							</tr>

							<s:if test="noData">
								<tr>
									<td width="100%" colspan="8" align="center"><font
										color="red">There is no data to display</font></td>
								</tr>
							</s:if>

							<%!int i = 0;%>
							<%
								int k = 1;
								int c = 0;
							%>

							<s:iterator value="loadList">
								<tr>
									<td width="6%" class="border2"><s:property value="srNo" />
									<td width="13%" class="border2"><s:property
										value="reqName" /> <s:hidden name="empCode" /> <s:hidden
										name="reqNo" /></td>
									<td width="13%" class="border2"><s:property value="reqDt" /></td>
									<td width="15%" class="border2"><s:property
										value="appliedFor" /></td>
									<td width="15%" class="border2"><s:property
										value="appliedBy" />&nbsp;</td>
									<td width="15%" class="border2"><s:property
										value="hiringManager" /></td>
								</tr>
								<%
									k++;
									c++;
								%>
							</s:iterator>

							<%
							i = k;
							%>
						</table>
						<input type="hidden" name="count" id="count" value="<%=c%>" /></td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
		<s:else>
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td>
						<table width="98%" border="0" align="center" cellpadding="0"
							cellspacing="2">
							<tr>
								<strong class="text_head">Manpower Information : </strong>
							</tr>
							<tr>

								<td width="24%" height="22" class="formtext"><label
									class="set" name="reqs.code" id="reqs.code"
									ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label>
								:</td>
								<td width="27%" height="22"><label> <s:hidden
									name="reqName" id="reqName1" /> <s:property value="reqName" />
								</label></td>

								<td width="4%" height="22"></td>
								<td width="20%" height="22" class="formtext" nowrap="nowrap"><label
									class="set" name="reqs.date" id="reqs.date"
									ondblclick="callShowDiv(this);"><%=label.get("reqs.date")%></label>
								:</td>
								<td width="25%" height="22"><s:property value="reqDt" /></td>
							</tr>
							<tr>
								<td height="22" class="formtext"><label class="set"
									name="position" id="position" ondblclick="callShowDiv(this);"><%=label.get("position")%></label>
								:</td>
								<td height="22"><s:hidden name="reqPositionCode" /><s:property
									value="reqPositionName" /><s:hidden name="reqPositionName" />
								</td>
								<td height="22"></td>
								<td height="22" class="formtext"><label class="set"
									name="appstatus" id="appstatus" ondblclick="callShowDiv(this);"><%=label.get("appstatus")%></label>
								:</td>
								<td height="22"><s:hidden name="reqApprStatus"
									id="reqApprStatus1" /><s:property value="reqApprStatus" /></td>
								<td height="22"></td>
							</tr>
							<s:hidden name="appliedBy" />
							<tr>
								<td height="22" class="formtext"><label class="set"
									name="division" id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
								:</td>
								<td height="22"><s:hidden name="reqDivCode" /><s:property
									value="reqDiv" /><s:hidden name="reqDiv" /></td>
								<td height="22"></td>
								<td height="22" class="formtext"><label class="set"
									name="reqstatus" id="reqstatus" ondblclick="callShowDiv(this);"><%=label.get("reqstatus")%></label>
								:</td>
								<td height="22"><s:hidden name="reqStatus" /><s:property
									value="reqStatus" /></td>
								<td height="22"></td>
							</tr>

							<tr>
								<td height="22" class="formtext"><label class="set"
									name="branch" id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
								:</td>
								<td height="22"><s:hidden theme="simple" name="reqBrnCode" /><s:property
									value="reqBrn" /><s:hidden name="reqBrn" /></td>
								<td height="22">&nbsp;</td>
								<td height="22" class="formtext"></td>
								<td height="22"></td>
							</tr>

							<tr>
								<td height="22" class="formtext"><label class="set"
									name="department" id="department"
									ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
								:</td>
								<td height="22"><s:hidden theme="simple" name="reqDeptCode" /><s:property
									value="reqDept" /><s:hidden name="reqDept" /></td>
								<td height="22">&nbsp;</td>
								<td height="22" class="formtext"></td>
								<td height="22"></td>
							</tr>
							<%
								String internalVal = (String) request.getAttribute("int");
								String externalVal = (String) request.getAttribute("ext");
							%>
							<tr>

								<td height="22" class="formtext"><label class="set"
									name="hiring.mgr" id="hiring.mgr"
									ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%></label>
								:</td>
								<td height="22"><s:hidden name="hiringcode" /><s:hidden
									name="hiringToken" /> <s:property value="hiringManager" /></td>
								<td height="22"></td>
								<td height="22" class="formtext"><label class="set"
									name="recruitmenttype" id="recruitmenttype"
									ondblclick="callShowDiv(this);"><%=label.get("recruitmenttype")%></label>
								:</td>
								<td height="22"><input type="hidden" name="internal"
									id="internal" /><s:checkbox name="inter" disabled="true"
									id="inter" onclick="callForInternal();" /> <%
 if (String.valueOf(internalVal).equals("true")) {
 %>
								<script>
							document.getElementById('inter').checked =true;
						</script> <%
 }
 %> &nbsp;<label class="set" name="intern" id="intern"
									ondblclick="callShowDiv(this);"><%=label.get("intern")%></label>
								&nbsp; <s:checkbox name="exter" id="exter" disabled="true"
									onclick="callForExternal();" /><input type="hidden"
									name="external" id="external" /> <%
 if (String.valueOf(externalVal).equals("true")) {
 %>
								<script>
							document.getElementById('exter').checked =true;
						</script> <%
 }
 %> &nbsp;<label class="set" name="extern" id="extern"
									ondblclick="callShowDiv(this);"><%=label.get("extern")%></label>
								</td>
								<td height="22"></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>


			<tr>
				 <td colspan="6">
				 <table width="100%" border="0" cellpadding="1" cellspacing="0">
					<tr>
						<td colspan="1" width="35%" nowrap="nowrap"><strong>The
						Approver(s) for this application : </strong></td>

						<td colspan="1" width="15%" nowrap="nowrap"></td>

						<td colspan="1" width="20%" nowrap="nowrap"><strong>Keep
						Informed To : </strong></td>

						<td colspan="1" width="15%"> </td>

						<td colspan="1" width="5%"> </td>

						<td colspan="1" width="10%"> </td>
					</tr>

					<!-- APPROVER LIST  TABLE  STARTS -->
					<tr valign="top">
						<td colspan="3" rowspan="3">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<s:hidden name="firstApproverCode" />
								<s:hidden name="firstApproverToken" />
								<s:iterator value="approverList">
									<tr>
										<td><s:hidden name="approverName" /> <STRONG><s:property
											value="srNoIterator" /></STRONG> <s:property value="approverName" />
										</td>
									</tr>
								</s:iterator>
							</tr>
						</table>
						</td>
					</tr>
					<!-- APPROVER LIST  TABLE  ENDS -->

					<!-- KEEP INFORMED LIST TABLE  STARTS -->
					<tr valign="top">
						<td colspan="3">
						  <table width="100%" border="0" cellpadding="0" cellspacing="0">
								<%
									int counter11 = 0;
									int counter2 = 0;
								%>
							 <s:iterator value="keepInformedList" status="stat">
								<tr>
									<td colspan="1" width="10%">&nbsp;</td>
									<td colspan="1" width="60%" nowrap="nowrap"><%=++counter11%>
									<s:hidden name="serialNo" /> <s:hidden
										name="keepInformedEmpNameItt" /> <s:property
										value="keepInformedEmpNameItt" /> <s:hidden name="empid" />
									</td>
									<td width="30%"> </td>
								</tr>
								<%
									counter2 = counter11;
								%>
							</s:iterator>
							<%
								counter2 = 0;
							%>
						  </table>
						 </td>
						<td></td>
					 </tr>
				 <!-- KEEP INFORMED LIST TABLE  ENDS -->
				</table>
				</td>
			</tr>

			<tr>
				<td colspan="5">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<strong class="formhead"> </strong>
						<td>
						<table width="98%" border="0" align="center" cellpadding="0"
							cellspacing="0">


							<tr>
								<strong class="text_head">Job Description : </strong>

							</tr>



							<tr>
								<td width="24%" colspan="1" height="27"><label class="set"
									name="job.name" id="job.name" ondblclick="callShowDiv(this);"><%=label.get("job.name")%></label>
								:</td>
								<td width="76%" height="27" colspan="4"><s:hidden
									name="jdCode" /><s:property value="jdDescName" /></td>
							</tr>

							<tr>
								<td>&nbsp;&nbsp;</td>
							</tr>

							<tr>
								<td width="24%" height="22" class="formtext" colspan="1"
									valign="top"><label class="set" name="job.desc"
									id="job.desc" ondblclick="callShowDiv(this);"><%=label.get("job.desc")%></label>
								:</td>
								<td width="76%" valign="top"> 
									<s:hidden name="jdDesc"/>
									<s:textarea name="jdDesc" rows="3" id="ctrlShow" cols="26" disabled="true"/>
									<img src="../pages/images/zoomin.gif" height="12" align="bottom"
									width="12" theme="simple" onclick="javascript:callWindow('paraFrm_jdDesc','job.desc','readonly','1000','1000');">
								</td>
							</tr>

							<tr>
								<td>&nbsp;&nbsp;</td>
							</tr>
							<tr>
								<td width="24%" colspan="1" height="27" valign="top"><label
									class="set" name="roles.res" id="roles.res"
									ondblclick="callShowDiv(this);"><%=label.get("roles.res")%></label>
								:</td>
								<td width="76%" height="27" colspan="4" valign="top">
									<s:hidden name="jdRoleDesc"/>
									<s:textarea name="jdRoleDesc" rows="3" cols="25" id="ctrlShow" disabled="true"/>
									<img src="../pages/images/zoomin.gif" height="12" align="bottom"
										width="12" theme="simple" onclick="javascript:callWindow('paraFrm_jdRoleDesc','roles.res','readonly','1000','1000');">
								</td>
							</tr>
							<tr>
								<td>&nbsp;&nbsp;</td>
							</tr>

							<tr>

								<td width="24%" height="22" class="formtext" valign="top"><label
									class="set" name="specialreq" id="specialreq"
									ondblclick="callShowDiv(this);"><%=label.get("specialreq")%></label>
								:</td>
								<td width="76%" height="27" valign="top" colspan="4"><label>
								<s:property value="specialReq" /> </label></td>


							</tr>

							<tr>
								<td>&nbsp;&nbsp;</td>
							</tr>
							<tr>
								<td width="24%" colspan="1" height="27" valign="top"><label
									class="set" name="persreq" id="persreq"
									ondblclick="callShowDiv(this);"><%=label.get("persreq")%></label>
								:</td>
								<td width="76%" height="27" colspan="4"><s:property
									value="persoanlReq" /></td>
							</tr>

						</table>
						</td>
					</tr>
				</table>



				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td>
						<table width="98%" border="0" align="center" cellpadding="0"
							cellspacing="2">

							<tr>
								<td class="formtext">
								<table width="100%" border="0" cellpadding="0" cellspacing="0"
									class="formbg">
									<tr class="sortable">
										<td>
										<table width="100%" border="0" cellpadding="0" cellspacing="0">
											</td>
											</tr>

											<tr>
												<strong class="text_head">Vacancy Details : </strong>
												<td colspan="5">
												<table width="100%" id="tblVac" class="sortable">
													<tr>
														<td width="10%" valign="top" class="formth"
															nowrap="nowrap"><b><label class="set" name="sr"
															id="sr1" ondblclick="callShowDiv(this);"><%=label.get("sr")%></label></b></td>
														<td width="15%" valign="top" class="formth" align="center"><b><label
															class="set" name="vacn" id="vacn"
															ondblclick="callShowDiv(this);"><%=label.get("vacn")%></label></b></td>
														<td width="20%" valign="top" class="formth" align="center"><b><label
															class="set" name="required.date" id="required.date"
															ondblclick="callShowDiv(this);"><%=label.get("required.date")%></label></b></td>



													</tr>
													<%
													int v = 0;
													%>
													<s:iterator value="vacList">
														<tr>
															<td width="10%" align="center" class="sortableTD"><%=++v%></td>

															<td width="15%" align="center" class="sortableTD">&nbsp;
															<s:property value="noOfVac" id='<%="noOfVac"+v%>' /></td>
															<td width="15%" align="center" class="sortableTD">&nbsp;
															<s:property value="vacDate" id='<%="vacDate"+v%>' /></td>


														</tr>
													</s:iterator>
												</table>
												</td>
											</tr>
										</table>
										</td>
									</tr>






								</table>
								</td>
							</tr>



						</table>
						<table width="100%">


							<tr>

								<td width="24%" height="22" class="formtext" nowrap="nowrap"><label
									class="set" name="comp" id="comp"
									ondblclick="callShowDiv(this);"><%=label.get("comp")%></label>
								:</td>

								<td width="27%" height="22"><s:property
									value="reqCompensation" /></td>
								<td width="4%" height="22"></td>
								<td width="20%" height="22" class="formtext" nowrap="nowrap"
									colspan="1">&nbsp;<label class="set" name="experience"
									id="experience" ondblclick="callShowDiv(this);"><%=label.get("experience")%></label>
								:</td>
								<td width="25%" height="22" colspan="1"><label class="set"
									name="minexp" id="minexp" ondblclick="callShowDiv(this);"><%=label.get("minexp")%></label><label>
								<s:property value="minExp" /> <label class="set" name="maxexp"
									id="maxexp" ondblclick="callShowDiv(this);"><%=label.get("maxexp")%></label>
								<s:property value="maxExp" /></td>
							</tr>

							<tr>

								<td width="24%" height="22" class="formtext"><label
									class="set" name="vactype" id="vactype"
									ondblclick="callShowDiv(this);"><%=label.get("vactype")%></label>
								:</span></td>
								<td width="27%" height="22"><label> <s:property
									value="vacType" /></label></td>
								<td width="4%" height="22"></td>
								<td width="20%" height="22" class="formtext" nowrap="nowrap"><label
									class="set" name="contracttype" id="contracttype"
									ondblclick="callShowDiv(this);"><%=label.get("contracttype")%></label>
								:</td>
								<td width="25%" height="22"><label> <s:property
									value="reqConType" /></label><label> <s:property
									value="reqPartFull" /></label></td>
							</tr>
							<tr id="showNewPost">
								<td width="24%" height="22" class="formtext">Comment :</td>
								<td colspan="4"><s:property value="newPostComment" /></td>
							</tr>
							<tr id="showReplace">
								<td width="24%" height="22" class="formtext">Selected
								Employees for replacement :</td>
								<td colspan="4"><s:property value="replaceEmpName" /></td>
							</tr>





							<!--  <tr>
                    <td width="19%" height="22" class="formtext" nowrap="nowrap">
					<label  class = "set"  name="comp" id="comp" ondblclick="callShowDiv(this);"><%=label.get("comp")%></label> :</span></td>
                    <td width="30%" height="22"><s:property value="reqCompensation"  />
                    </td>
                    <td width="20%" height="22" class="formtext" nowrap="nowrap">
					<label  class = "set"  name="exper" id="exper" ondblclick="callShowDiv(this);"><%=label.get("exper")%></label>  :</td>
                    <td width="32%" height="22"><label  class = "set"  name="minexp" id="minexp" ondblclick="callShowDiv(this);"><%=label.get("minexp")%></label> :<label>
                      <s:property value="minExp"  />
					<label  class = "set"  name="maxexp" id="maxexp" ondblclick="callShowDiv(this);"><%=label.get("maxexp")%></label> :
                    </label><s:property value="maxExp" /></td>
                  </tr>
                  
                   <tr>
                    <td width="19%" height="22" class="formtext" nowrap="nowrap">
					<label  class = "set"  name="vactype" id="vactype" ondblclick="callShowDiv(this);"><%=label.get("vactype")%></label> :</span></td>
                    <td width="30%" height="22"><label>
                   <s:property value="vacType"
				/></label></td>
                    <td width="20%" height="22" class="formtext" nowrap="nowrap">
					 <label  class = "set"  name="contracttype" id="contracttype" ondblclick="callShowDiv(this);"><%=label.get("contracttype")%></label> :</td>
                    <td width="32%" height="22"><label>
                    <s:property value="reqConType" />
					 <s:property value="reqPartFull" /></label></td>
                  </tr>-->


						</table>



						</td>
					</tr>
				</table>
				<s:hidden name="apprvFlag" /> <s:if test="apprvFlag">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td nowrap="nowrap"><strong>Approver Details :</strong></td>
						</tr>
						<tr>
							<td class="formth" width="10%"><b> <label class="set"
								name="sr" id="sr1" ondblclick="callShowDiv(this);"><%=label.get("sr")%></label></b>
							</td>
							<td class="formth" width="25%"><b><label class="set"
								name="apprvName" id="apprvName" ondblclick="callShowDiv(this);"><%=label.get("apprvName")%></label></b></td>
							<td class="formth" width="20%"><b><label class="set"
								name="apprvDate" id="apprvDate" ondblclick="callShowDiv(this);"><%=label.get("apprvDate")%></label></b></td>
							<td class="formth" width="15%"><b><label class="set"
								name="appstatus" id="appstatus1" ondblclick="callShowDiv(this);"><%=label.get("appstatus")%></label></b></td>
							<td class="formth" width="30%"><b><label class="set"
								name="apprvRem" id="apprvRem" ondblclick="callShowDiv(this);"><%=label.get("apprvRem")%></label></b></td>

						</tr>
						<%
						int i = 0;
						%>
						<s:iterator value="apprvList">
							<tr>
								<td class="sortableTD" width="10%" align="center">&nbsp;<%=++i%></td>
								<td class="sortableTD" width="25%">&nbsp;<s:property
									value="apprvName" /></td>
								<td class="sortableTD" width="20%">&nbsp;<s:property
									value="apprvDate" /></td>
								<td class="sortableTD" width="15%">&nbsp;<s:property
									value="apprvStat" /></td>
								<td class="sortableTD" width="30%">&nbsp;<s:property
									value="apprvRem" /></td>

							</tr>
						</s:iterator>
					</table>
				</s:if> <s:if test="commentFlag">
					<table width="100%" class="formbg">
						<tr>
							<td width="24%" colspan="1" valign="top"><label class="set"
								name="apprvRem" id="apprvRem" ondblclick="callShowDiv(this);"><%=label.get("apprvRem")%></label>
							:</td>
							<td width="74%"><s:textarea cols="50" rows="4"
								name="apprvComment"></s:textarea> <img
								src="../pages/images/zoomin.gif" height="12" align="bottom"
								width="12" theme="simple"
								onclick="javascript:callWindow('paraFrm_apprvComment','apprvRem','','200','200');"></td>
						</tr>
					</table>
				</s:if></td>
			</tr>
			<tr>
				<td width="78%"><jsp:include
					page="/pages/ApplicationStudio/navigationPanel.jsp" /> <s:if
					test="%{offerAppointFlag}">
					<s:submit cssClass="token"
						action="EmployeeRequi_backToOfferAppoint" theme="simple"
						value='Back To %{createNewFlag}' onclick="return callView()" />
				</s:if></td>
				<td width="22%" align="right"><s:if
					test="viewforEmployeeRequisition">
					<strong>Page 1 of 2</strong>
				</s:if> <s:else>

				</s:else></td>
				<td></td>

			</tr>

		</s:else>
	</table>

</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script> 
callOnload();
function callOnload(){
	if(document.getElementById('replaceEmpId').value==""){
		document.getElementById('showNewPost').style.display = '';
		document.getElementById('showReplace').style.display = 'none';
	}
	else{
		document.getElementById('showNewPost').style.display = 'none';
		document.getElementById('showReplace').style.display = '';
	}
}  
  function verifyandsendFun(){
  
  var status=document.getElementById('reqName1').value;
  var reqStatus=document.getElementById('reqApprStatus1').value;
 
  			if(status=="" || status=="null"){
	  			alert("Please save the record first.");
	  			return false;	
  			}else if(reqStatus=="Pending"){
			alert("Application is already pending.So can't be send for approval.");
			return false;
		    }else if(reqStatus=="Approved"){
	  			alert("Application is approved.So can't be send for approval.");
	  			return false;
  			}else if(reqStatus=="On Hold")	{
			alert("Application is On Hold.So can't be send for approval.");
			return false;
		
		  }else if(reqStatus=="Quick Requisition")	{
			alert("Quick Requisition can't be send for approval.");
			return false;
		
		} else{	
		  
		  		/*var wind = window.open('','wind','width=1000,height=400,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
				document.getElementById('paraFrm').target = "wind";
				document.getElementById('paraFrm').action = "EmployeeRequi_sendForApprove.action";
				document.getElementById('paraFrm').submit();
				document.getElementById('paraFrm').target = "main";*/
		  
		  
		  
		 
  		  document.getElementById('paraFrm').action="EmployeeRequi_sendForApprove.action";
	      document.getElementById('paraFrm').submit();
	     	      	 
	        }	       
  
  return false;
  }
  
function nextFun(){
  	var newSt = document.getElementById('paraFrm_hiddenApproveStatus').value;
 	if (newSt=="New Requisition"){
     	document.getElementById("paraFrm").action="EmployeeRequi_viewNextPageFirst.action";
     	document.getElementById("paraFrm").submit();
	}
 
  	if(document.getElementById('paraFrm_reqFlag').value=="true"){
  		document.getElementById("paraFrm").action="EmployeeRequi_viewNextPage.action";
     	document.getElementById("paraFrm").submit();
  	} else {
    	document.getElementById("paraFrm").action="EmployeeRequi_viewNext.action";
     	document.getElementById("paraFrm").submit();
	}
	return false;
}
  
function callDate(id){
  NewCal('hvacDate'+id,'DDMMYYYY');
}
  
  
  function callDom(id)
    {
	    callsF9(500,325,'EmployeeRequi_f9Dom.action'); 
   }
  
function callForVac(id)
	   {
	   if(document.getElementById('confChkVac'+id).checked == true) {	  
	    document.getElementById('hdeleteVac'+id).value="Y";
	   } else {
	   	document.getElementById('hdeleteVac'+id).value="";
   		}
  }
  
  
  function chkDeleteVacancy()
	{
	 if(chkVac()){
	 var con=confirm('Do you really want to delete this record ?');
	 if(con){
	   document.getElementById('paraFrm').action="EmployeeRequi_deleteVacancy.action";
	    document.getElementById('paraFrm').submit();
	    } else{
	     return false;
	    }
	 }else {
	 	alert('Please Select atleast one Record To Delete');
	 	 return false;
	 }
	}
	
	
	
	function chkVac(){
	var tbl = document.getElementById('tblVac');
var rowLen = tbl.rows.length;
	  for(var a=1;a<rowLen;a++){	
	   if(document.getElementById('confChkVac'+a).checked == true)
	   {	
	 	    return true;
	   }	   
	  }
	  return false;
	}
  
  


 function callVacAll()
  {
		var tbl = document.getElementById('tblVac');
		var rowLen = tbl.rows.length;
	if (document.getElementById("chkVacAll").checked == true){
	for (var idx = 1; idx < rowLen; idx++) {
	
				document.getElementById("confChkVac"+idx).checked = true;
				 document.getElementById('hdeleteVac'+idx).value="Y";
				
	    }

 }else{
	for (var idx = 1; idx < rowLen; idx++) {
	document.getElementById("confChkVac"+idx).checked = false;
	document.getElementById('hdeleteVac'+idx).value="";
     }
  }	 
  }
  

	function addRowToTableVacancy()
	{
		  var tbl = document.getElementById('tblVac');
		  var lastRow = tbl.rows.length;
		 
		  
		  // if there's no header row in the table, then iteration = lastRow + 1
		  var iteration = lastRow;
		  var row = tbl.insertRow(lastRow);
		  
		 
		  // left cell
		  var cellLeft = row.insertCell(0);
		  var textNode = document.createTextNode(iteration);
		  cellLeft.appendChild(textNode);
	  
	  		var cellVacCode=row.insertCell(1);
	  		var code=document.createElement('input');
	  		code.type='hidden';
	  		code.name='vacDetCode';
	  		code.id='vacDetCode';
	  		cellVacCode.appendChild(code);
	  
   		  var cellNoOfVac = row.insertCell(2);
   		  var vac = document.createElement('input');
		  vac.type = 'text';
		  vac.name = 'noOfVac';
		  vac.id = 'noOfVac'+iteration;
		 vac.onkeypress= function(){
		  		document.onkeypress = numbersOnly;
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = e.which
			clear();
			// Was key that was pressed a numeric character (0-9) or backspace (8)?
			if ( key > 47 && key < 58 || key == 8 || key == 0)
			 	return true; // if so, do nothing
			else // otherwise, discard character
				return false;m
			if (window.event) //IE
			    window.event.returnValue = null;     else //Firefox
			    e.preventDefault();
  }
		  cellNoOfVac.appendChild(vac);
  
		  var cellVacDate = row.insertCell(3);
		  var vacDate = document.createElement('input');
  
		  vacDate.type = 'text';
		  vacDate.name = 'vacDate';
		  vacDate.id =  'vacDate'+iteration;
		  cellVacDate.appendChild(vacDate);
 
		  var cellVacImg= row.insertCell(4);
		  var img=  document.createElement('img');
		  img.type='image';
		  img.src="../pages/images/Date.gif";
		  img.height='16';
		 img.align='absmiddle';
		 img.width='16';
		 img.id='img'+ iteration;
		 img.theme='simple';
		 img.align="center";
		 img.onclick=function(){	 			
	 			NewCal('hvacDate'+iteration,'DDMMYYYY');
	     };
 		cellVacImg.appendChild(img);
	  
		  var cellVacChk = row.insertCell(5);
		  var vacChk = document.createElement('input');
		 
		  vacChk.type = 'checkbox';
		  vacChk.name = 'confChkVac';
		  vacChk.id = 'confChkVac'+iteration;
		  vacChk.onclick=function(){
		 	  if(document.getElementById('confChkVac'+iteration).checked == true)
			   {	
			    	document.getElementById('hdeleteVac'+iteration).value="Y";
			   }  else{
			  	    document.getElementById('hdeleteVac'+iteration).value="";
			   	
		   		}
			};
 
		
		  cellVacChk.appendChild(vacChk);
		  var hiddenDel=row.insertCell(6);
		  var hid = document.createElement('input');
		  hid.type = 'hidden';
		  hid.name = 'hdeleteVac'+iteration;
		  hid.id = 'hdeleteVac' + iteration;
		  hiddenDel.appendChild(hid);
	}  

function chkDupVac(){

var tbl = document.getElementById('tblVac');
  
var lastRow = tbl.rows.length;

for(var i=1;i<lastRow;i++){
 		 		var cert= document.getElementById('noOfVac'+i).value;
 		 		
 		 		
 		 	var cnt=0;
 		 		for(var d=1;d<lastRow;d++){
	 		 			
	 		 			var dupCert= document.getElementById('noOfVac'+d).value;
 		 			
	 		 		
	 		 		if(dupCert == cert){
	 		 			cnt++;
	 		 		}
	 		 		if(cnt > 1){
	 		 			alert('Duplicate Records Found in Vacancy Details.');
	 		 			return false;
	 		 		}
 		 		}
 		 	if(cert==""){
 		 		alert("Please enter the row value in vacany details.");
 		 		return false;
 		 	}	
 		}
 	
 		
 return true;		 
 		
 }				

	function deleteFun(){
	var status=document.getElementById("paraFrm_hiddenApproveStatus").value;
		if(status=="Pending"){
			alert("Application is already pending.So can't be deleted.");
			return false;
		}else if(status=="Approved"){
			alert("Application is already approved.So can't be deleted.");
			return false;
		}else if(status=="On Hold")	{
			alert("Application is On Hold.So can't be deleted.");
			return false;
		}else if(status=="Rejected"){
			alert("Application is Rejected.So can't be deleted.");
			return false;
		
		}else if(status=="Quick Requisition")	{
			alert("Quick Requisition can't be deleted.");
			return false;		
		} else{
	
			var conf=confirm("Do you really want to delete this record ?");
			if(conf){
				document.getElementById("paraFrm").action="EmployeeRequi_delete.action";
		   		document.getElementById("paraFrm").submit();
					
			}else{
			
			     return false;
			}
	   }
	
	}

	function addnewFun(){
		document.getElementById("paraFrm").action="EmployeeRequi_addNew.action";
	    document.getElementById("paraFrm").submit();
	}
	
	function saveFun(){
	
	
	var fieldName = ["paraFrm_reqDt","paraFrm_reqPositionName","paraFrm_reqDiv","paraFrm_hiringManager","paraFrm_jdDescName","paraFrm_jdDesc","paraFrm_jdRoleDesc","paraFrm_specialReq","paraFrm_personalReq"];
	var lableName = ["requisition date","position","division","hiring manager","job name","job description","job roles and responsibility","special requirement","personal qualities"];
	var flag = ["enter","enter","enter","enter","enter","enter","enter","enter","enter"];
	
		if(!chkDupVac()){
		return false;
		
		}
    // if(!checkMandatory(fieldName,lableName,flag)){
     
      //        return false;
     //   }
        
	  if(!validateDate("paraFrm_reqDt","Requisition Date")){
		return false;
		
		}
		if(document.getElementById("paraFrm_updateFirstFlag").value=="true"){
		
		document.getElementById("paraFrm").action="EmployeeRequi_updateFirst.action";
	    document.getElementById("paraFrm").submit();
		
		}else{

	
		document.getElementById("paraFrm").action="EmployeeRequi_saveFirst.action";
	    document.getElementById("paraFrm").submit();
	    }
	  
	}
	
	
	function cancelFun(){
		if(document.getElementById('source').value=='mymessages') {
			document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
		} else if(document.getElementById('source').value=='myservices') {
			document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_serviceData.action';
		} else {
			document.getElementById("paraFrm").action="EmployeeRequi_cancelFirst.action";
		}	
		document.getElementById("paraFrm").submit();
	}
	
	 function backFun(){
	 if(document.getElementById('paraFrm_dashletFlag').value=="true")
	 {
	    document.getElementById('paraFrm').action = "<%=request.getContextPath()%>/common/RecruitmentHome_input.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target ="main";
	 }else{
	  	document.getElementById('paraFrm').action = document.getElementById('formAction').value+'?status='+document.getElementById('statusKey').value;
	   	document.getElementById('paraFrm').submit();
	 }
	  
	 return false;  
   }
   
	function searchFun(){
		callsF9(500,325,'EmployeeRequi_f9Search.action');
	}
	
	function editFun(){
		var status=document.getElementById("paraFrm_hiddenApproveStatus").value;
		if(status=="Pending"){
			alert("Application is already pending.So can't be edited.");
			return false;
		}else if(status=="Approved"){
			alert("Application is already approved.So can't be edited.");
			return false;
		}else if(status=="On Hold")	{
			alert("Application is On Hold.So can't be edited.");
			return false;
		
		}else if(status=="Quick Requisition")	{
			alert("Quick Requisition can't be edited.");
			return false;
		
		} else{
		document.getElementById("paraFrm").action="EmployeeRequi_edit.action";
	    document.getElementById("paraFrm").submit();
	    }
	return false;
	}
	
  function callPage(id,status){
 
	  status = callStatus(status);
	   	document.getElementById('myPage').value=id;
	document.getElementById('paraFrm_show').value=id;
	    document.getElementById('paraFrm').action="EmployeeRequi_showApplicationList.action?status="+status;
	   document.getElementById('paraFrm').submit();
   }
   
   
     	 function previousPage(status)
   {
   	status = callStatus(status);
   var pageno=	document.getElementById('myPage').value;
   	
	 document.getElementById('myPage').value=eval(pageno)-1;
	 document.getElementById('paraFrm_show').value=eval(pageno)-1;
	   document.getElementById('paraFrm').action="EmployeeRequi_showApplicationList.action?status="+status;
	   document.getElementById('paraFrm').submit();
   }
   
   
   
   function nextPage(status)
   {
   status = callStatus(status);
   var pageno=	document.getElementById('myPage').value;
   	if(pageno=="1")
	   	{	document.getElementById('myPage').value=2;
		    document.getElementById('paraFrm_show').value=2;
	    } else{
				 document.getElementById('myPage').value=eval(pageno)+1;
				 document.getElementById('paraFrm_show').value=eval(pageno)+1;
	    }
		   document.getElementById('paraFrm').action="EmployeeRequi_showApplicationList.action?status="+status;
		   document.getElementById('paraFrm').submit();
   }	
   
  
  
  	function onPage(status)
   {
   status = callStatus(status);
  	var val= document.getElementById('selectname').value;
	document.getElementById('paraFrm_show').value=val;
	 document.getElementById('myPage').value=eval(val);
	 document.getElementById('selectname').value=val;
	   document.getElementById('paraFrm').action="EmployeeRequi_showApplicationList.action?status="+status;
	   
	   document.getElementById('paraFrm').submit();
   }
   
   function callStatus(status){
   	if(status==null  || status=="null" || status =="Pending List"){
	  	return "P";
	  }
	  else if(status =="Approved List"){
	  	return "A";
	  }
	  else if(status =="Rejected List"){
	  	return "R";
	  }
	  else if(status =="On Hold List"){
	  	return "H";
	  }
   }
   
   
   
    function callForInternal()
	   {
	 	  
	
	   if(document.getElementById('inter').checked)
	   {	  
	    document.getElementById('internal').value="true";
		
	   }
	   else{
	   document.getElementById('internal').value="";
	  
   		}
  }
   function callForExternal()
	   {
	 	  
	
	   if(document.getElementById('exter').checked)
	   {	  
	    document.getElementById('external').value="true";
	
	   }
	   else{
	   document.getElementById('external').value="";
	   	
   		}
  }
  
    function exportpdfreportFun(){
   		document.getElementById('paraFrm').target="_blank";
		document.getElementById('paraFrm').action="EmployeeRequi_reportFun.action";	
		document.getElementById('paraFrm').submit();	
		document.getElementById('paraFrm').target="main";
   
   }
   
   function exporttextreportFun(){
   		document.getElementById('paraFrm').target="_blank";
		document.getElementById('paraFrm').action="EmployeeRequi_reportFunText.action";	
		document.getElementById('paraFrm').submit();	
		document.getElementById('paraFrm').target="main";
   
   }
   
   function callView(){
   		var reqCode=document.getElementById('paraFrm_reqCode').value;
		document.getElementById('paraFrm').action="EmployeeRequi_backToOfferAppoint.action?reqCode="+reqCode;	
		document.getElementById('paraFrm').submit();	
		document.getElementById('paraFrm').target="main";		
   }
   
   function approveFun(){
   		
   		var comment=document.getElementById('paraFrm_apprvComment').value;
   		if(comment.length >200){
   		  alert("Maximum length of "+document.getElementById('apprvRem').innerHTML.toLowerCase()+" is 200 characters.");
		  return false;
   		 }
   		 var con=confirm("Do you really want to Approve the application?");
   		if(con){
	   		document.getElementById('formAction').value="RequisitionApproval_approve.action";		
	   		document.getElementById('paraFrm').action = document.getElementById('formAction').value+'?status='+document.getElementById('statusKey').value+'&comm='+comment;
		    document.getElementById('paraFrm').submit();
   		}
   
    return false;
   }
   
   
   function onholdFun(){
   			
   				var comment=document.getElementById('paraFrm_apprvComment').value;
   		if(comment.length >200){
   		  alert("Maximum length of "+document.getElementById('apprvRem').innerHTML.toLowerCase()+" is 200 characters.");
		  return false;
   		 }
   		var con=confirm("Do you really want to On Hold the application?");
   		if(con){
	   		document.getElementById('formAction').value="RequisitionApproval_onHold.action";		
	   		document.getElementById('paraFrm').action = document.getElementById('formAction').value+'?status='+document.getElementById('statusKey').value+'&comm='+comment;
		    document.getElementById('paraFrm').submit();
   		}
   return false;
   }
   
   function rejectFun(){
   			
   		    var comment=document.getElementById('paraFrm_apprvComment').value;
   		    if(comment.length >200){
   		      alert("Maximum length of "+document.getElementById('apprvRem').innerHTML.toLowerCase()+" is 200 characters.");
		      return false;
   		    }
   		 var con=confirm("Do you really want to Reject the application?");
   		if(con){
	   		document.getElementById('formAction').value="RequisitionApproval_reject.action";		
	   		document.getElementById('paraFrm').action = document.getElementById('formAction').value+'?status='+document.getElementById('statusKey').value+'&comm='+comment;
		    document.getElementById('paraFrm').submit();
   		}
    return false;
   }
   
    function sendbackFun(){
   			
   		    var comment=document.getElementById('paraFrm_apprvComment').value;
   		    if(comment.length >200){
   		      alert("Maximum length of "+document.getElementById('apprvRem').innerHTML.toLowerCase()+" is 200 characters.");
		      return false;
   		    }
   		 var con=confirm("Do you really want to Send Back the application?");
   		if(con){
   		
	   		document.getElementById('formAction').value="RequisitionApproval_sendBackApplication.action";		
	   		document.getElementById('paraFrm').action = document.getElementById('formAction').value+'?status='+document.getElementById('statusKey').value+'&comm='+comment;
		    document.getElementById('paraFrm').submit();
   		}
    return false;
   }
   
   
  </script>