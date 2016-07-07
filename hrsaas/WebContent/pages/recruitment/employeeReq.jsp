<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/pages/recruitment/Ajax.js">
</script>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@include file="/pages/common/commonValidations.jsp"%>
<%@page import="java.util.HashMap"%>
<script language="JavaScript" type="text/javascript"
	src="..pages/common/include/javascript/sorttable.js"></script>

<script type="text/javascript"
	src="../pages/common/include/javascript/jquery-1.2.2.pack.js"></script>

<script type="text/javascript"
	src="../pages/common/include/javascript/ddaccordion.js"></script>

<script type="text/javascript">
ddaccordion.init({
	headerclass: "technology", //Shared CSS class name of headers group
	contentclass: "thelanguage", //Shared CSS class name of contents group
	collapseprev: false, //Collapse previous content (so only one open at any time)? true/false 
	defaultexpanded: [], //index of content(s) open by default [index1, index2, etc]. [] denotes no content.
	animatedefault: false, //Should contents open by default be animated into view?
	persiststate: true, //persist state of opened contents within browser session?
	toggleclass: ["closedlanguage", "openlanguage"], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
	togglehtml: ["prefix", "<img src='../pages/common/css/default/images/plus.gif' align='left' vspace='3'  hspace='3' /> ", "<img src='../pages/common/css/default/images/minus.gif'align='left' vspace='3' hspace='3'  /> "], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
	animatespeed: "slow" //speed of animation: "fast", "normal", or "slow"
})

</script>
<s:form action="EmployeeRequi" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="leadTimeFlag" />
	<s:hidden name="calCulayedRequiredByDate" />
	<s:hidden name="checkRemove" />
	<table width="100%" align="right" class="formbg">
		<s:hidden name="addNewFlag" />
		<tr>
			<td colspan="3">
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="3" width="100%">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0" class="formbg">
						<tr>
							<td width="4%" valign="bottom" class="txt"><strong
								class="formhead"><img
								src="../pages/common/css/default/images/review_shared.gif"
								width="25" height="25" /></strong></td>
							<td width="93%" class="txt"><strong class="text_head">Manpower
							Requisition </strong></td>
							<td width="3%" valign="top" class="txt">
							<div align="right"><img
								src="../pages/images/recruitment/help.gif" width="16"
								height="16" /></div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<s:hidden name="viewDom" />
		<s:hidden name="viewCert" />
		<s:hidden name="viewQua" />
		<s:hidden name="viewSkill" />
		<s:hidden name="reqCode" />
		<s:hidden name="reqNameSer" />
		<s:hidden name="reqDateSer" />
		<s:hidden name="reqPosSer" />
		<s:hidden name="reqHireSer" />
		<s:hidden name="updateFirstFlag" />
		<s:hidden name="cancelThrd" />
		<s:hidden name="createNewFlag" />
		<s:hidden name="offerAppointFlag" />
		<s:hidden name="nextFlag" />
		<s:hidden name="reqAppBy" />
		<s:hidden name="reqApprStatusSer" />
		<s:hidden name="hiddenCode" />
		<s:hidden name="jdEffDate" />

		<s:hidden name="positionCode" />
		<s:hidden name="divisionCode" />
		<s:hidden name="branchCode" />
		<s:hidden name="deptCode" />
		<s:hidden name="source" id="source" />
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>

					<s:if test="%{offerAppointFlag}">
						<td width="29%" nowrap="nowrap"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					</s:if>
					<s:else>
						<td width="78%" nowrap="nowrap"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					</s:else>
					<s:if test="%{offerAppointFlag}">
						<td nowrap="nowrap"><s:submit cssClass="token"
							action="EmployeeRequi_backToOfferAppoint" theme="simple"
							value='Back To %{createNewFlag}' onclick="return callView()" />
						</td>
					</s:if>
					<td width="22%"><s:if test="listFlag"></s:if><s:else>
						<div align="right"><span class="style2"><font
							color="red">*</font></span> Indicates Required</div>
					</s:else></td>
				</tr>
			</table>
			</td>
		</tr>
		<s:hidden name="myPage" id="myPage" />
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
											href="EmployeeRequi_showApplicationList.action?status=B">New
										Requisition</a> | <a
											href="EmployeeRequi_showApplicationList.action?status=P">Pending
										List</a> | <a
											href="EmployeeRequi_showApplicationList.action?status=A">Approved
										List</a> | <a
											href="EmployeeRequi_showApplicationList.action?status=R">Rejected
										List</a> |<a
											href="EmployeeRequi_showApplicationList.action?status=S">Send
										Back List</a> | <a
											href="EmployeeRequi_showApplicationList.action?status=H">On
										Hold List</a> | <a
											href="EmployeeRequi_showApplicationList.action?status=Q">Quick
										Requisition</a></td>
										</td>
									</tr>
								</table>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
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
								 			out.println("New Application");
								 		}
 									%> </strong></td>
								<%
									int totalPage = (Integer) request.getAttribute("totalPage");
									int pageNo = (Integer) request.getAttribute("PageNo");
								%>
								<s:if test="noData"></s:if>
								<s:else>
									<td align="right"><b>Page:</b> <input type="hidden"
										name="totalPage" id="totalPage" value="<%=totalPage%>">
									<a href="#" onclick="callPage('1','F','<%=status %>');"> <img
										title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#" onclick="callPage('P','P','<%=status %>');"> <img
										title="Previous Page" src="../pages/common/img/previous.gif"
										width="10" height="10" class="iconImage" /> </a> <input
										type="text" name="pageNoField" id="pageNoField" theme="simple"
										size="3" value="<%= pageNo%>"
										onkeypress="callPageText(event,'<%=status%>');return numbersOnly()"
										maxlength="4" /> of <%=totalPage%> <a href="#"
										onclick="callPage('N','N','<%=status %>')"> <img
										title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPage('<%=totalPage%>','L','<%=status %>');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
								</s:else>
							</tr>
						</table>
						</td>
					</tr>
					<tr>
						<td colspan="1">
						<table width="100%" border="0" cellpadding="1" cellspacing="1"
							class="sortable">
							<tr>
								<td width="8%" valign="top" class="formth"><b> <label
									class="set" name="sr" id="sr5" ondblclick="callShowDiv(this);"><%=label.get("sr")%></label></b>
								</td>
								<td width="15%" valign="top" class="formth"><b><label
									class="set" name="reqs.code" id="reqs.code"
									ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label></b></td>
								<td width="15%" valign="top" class="formth" nowrap="nowrap"><b><label
									class="set" name="reqs.date" id="reqs.date1"
									ondblclick="callShowDiv(this);"><%=label.get("reqs.date")%></label></b></td>
								<td width="18%" valign="top" class="formth"><b><label
									class="set" name="position" id="position"
									ondblclick="callShowDiv(this);"><%=label.get("position")%></label></b></td>
								<td width="22%" valign="top" class="formth"><b><label
									class="set" name="applied.by" id="applied.by"
									ondblclick="callShowDiv(this);"><%=label.get("applied.by")%></label></b></td>
								<td width="22%" valign="top" class="formth"><b><label
									class="set" name="hiring.mgr" id="hiring.mgr"
									ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%></label></b></td>
							</tr>
							<s:if test="noData">
								<tr>
									<td width="100%" colspan="8" align="center"><font
										color="red">There is no data to display</font></td>
								</tr>
							</s:if>
							<%
							int count = 0;
							%>
							<%!int i = 0;%>
							<%
								int k = 1;
								int c = 0;
							%>

							<%
								int cnt = pageNo * 20 - 20;
								int kk = 0;
							%>

							<s:iterator value="loadList">
								<tr <%if(count%2==0){
									%> class="tableCell1" <%}else{%>
									class="tableCell2" <%	}count++; %>
									onmouseover="javascript:newRowColor(this);"
									onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
									title="Double click for view Requisition"
									ondblclick="javascript:callForEdit('<s:property value="hiddenReqCode" />');">
									<td width="8%" class="sortableTD"><%=++cnt%> <%++kk;%>
									
									<td width="15%" class="sortableTD"><s:property
										value="reqName" /> <s:hidden name="empCode" /> <s:hidden
										name="reqNo" /> <s:hidden name="hiddenReqCode" /></td>
									<td width="15%" class="sortableTD"><s:property
										value="reqDt" /></td>
									<td width="18%" class="sortableTD"><s:property
										value="appliedFor" /></td>
									<td width="22%" class="sortableTD"><s:property
										value="appliedBy" />&nbsp;</td>
									<td width="22%" class="sortableTD"><s:property
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




				</table><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
				</td>
			</tr>


		</s:if>
		<s:else>
			<tr>
				<td colspan="3">
				<table width="100%" border="0" class="formbg">
					<tr>
						<td>
						<table width="98%" border="0" align="center" cellpadding="0"
							cellspacing="2">
							<tr>
								<strong class="text_head">Manpower Information : </strong>
								<td colspan="5" class="formhead"><img
									src="../pages/common/css/default/images/space.gif" width="5"
									height="7" /></td>
							</tr>
							<tr>

								<td width="24%" height="22" class="formtext"><label
									class="set" name="reqs.code" id="reqs.code"
									ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label>
								:<font color="red">*</font></td>
								<td width="27%" height="22"><label> <s:textfield
									name="reqName" size="25" maxlength="25" /> </label></td>
								<td width="4%" height="22"></td>
								<td width="20%" height="22" class="formtext" nowrap="nowrap"><label
									class="set" name="reqs.date" id="reqs.date"
									ondblclick="callShowDiv(this);"><%=label.get("reqs.date")%></label>
								:<font color="red">*</font></td>
								<td width="25%" height="22" nowrap="nowrap"><label>
								<s:textfield name="reqDt" size="25" maxlength="10"
									onkeypress="return numbersWithHiphen();" /> <s:a
									href="javascript:NewCal('paraFrm_reqDt','DDMMYYYY');">
									<img src="../pages/common/css/default/images/Date.gif"
										width="16" height="16" border="0" />
								</s:a> </label></td>
							</tr>
							<tr>
								<td height="22" class="formtext"><label class="set"
									name="position" id="position" ondblclick="callShowDiv(this);"><%=label.get("position")%></label>
								:<font color="red">*</font></td>
								<td height="22" nowrap="nowrap"><s:hidden
									name="reqPositionCode" /><s:textfield name="reqPositionName"
									size="25" maxlength="30" readonly="true" /> <a href="#"><img
									src="../pages/common/css/default/images/search2.gif" width="16"
									height="15" border="0"
									onclick="javascript:callsF9(500,325,'EmployeeRequi_f9actionDesig.action');" /></a></td>
								<td height="22"></td>
								<td height="22" class="formtext"><label class="set"
									name="appstatus" id="appstatus" ondblclick="callShowDiv(this);"><%=label.get("appstatus")%></label>
								:</td>
								<td height="22"><s:textfield name="reqApprStatus" size="25"
									maxlength="30" readonly="true" /></td>
								<td height="22"></td>
							</tr>
							<s:hidden name="appliedBy" />
							<tr>
								<td height="22" class="formtext"><label class="set"
									name="division" id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
								:<font color="red">*</font></td>
								<td height="22" nowrap="nowrap"><s:hidden name="reqDivCode" /><s:textfield
									name="reqDiv" size="25" maxlength="30" readonly="true" /> <a
									href="#"><img
									src="../pages/common/css/default/images/search2.gif" width="16"
									height="15" border="0"
									onclick="javascript:callsF9(500,325,'EmployeeRequi_f9actionDiv.action');" /></a></td>
								<td height="22"></td>
								<td height="22" class="formtext"><label class="set"
									name="reqstatus" id="reqstatus" ondblclick="callShowDiv(this);"><%=label.get("reqstatus")%></label>
								:</td>
								<td height="22"><s:textfield name="reqStatus" size="25"
									maxlength="30" readonly="true" /></td>
								<td height="22"></td>


							</tr>



							<tr>
								<td height="22" class="formtext"><label class="set"
									name="branch" id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
								:<font color="red">*</font></td>
								<td height="22" nowrap="nowrap"><s:hidden theme="simple"
									name="reqBrnCode" /><s:textfield name="reqBrn" size="25"
									maxlength="30" readonly="true" /> <a href="#"> <img
									src="../pages/common/css/default/images/search2.gif" width="16"
									height="15" border="0"
									onclick="javascript:callsF9(500,325,'EmployeeRequi_f9actionBrn.action');" /></a></td>
								<td height="22">&nbsp;</td>
								<td height="22" class="formtext"></td>
								<td height="22"></td>
							</tr>



							<tr>
								<td height="22" class="formtext"><label class="set"
									name="department" id="department"
									ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
								:<font color="red">*</font></td>
								<td height="22" nowrap="nowrap"><s:hidden theme="simple"
									name="reqDeptCode" /><s:textfield name="reqDept" size="25"
									maxlength="30" readonly="true" /> <a href="#"><img
									src="../pages/common/css/default/images/search2.gif" width="16"
									height="15" border="0"
									onclick="javascript:callsF9(500,325,'EmployeeRequi_f9actionDept.action'); " /></a></td>
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
								:<font color="red">*</font></td>
								<td height="22" nowrap="nowrap"><s:hidden name="hiringcode" /><s:hidden
									name="hiringToken" /> <s:textfield name="hiringManager"
									size="25" maxlength="30" readonly="true" /> <a href="#"><img
									src="../pages/common/css/default/images/search2.gif" width="16"
									height="15" border="0"
									onclick="javascript:callsF9(500,325,'EmployeeRequi_f9actionHireMan.action'); " /></a></td>

								<td height="22"></td>
								<td height="22" class="formtext"><label class="set"
									name="recruitmenttype" id="recruitmenttype"
									ondblclick="callShowDiv(this);"><%=label.get("recruitmenttype")%></label>
								:</td>
								<td height="22"><s:hidden name="internal" /> <s:checkbox
									name="inter" onclick="callForInternal();" /> <%
 if (String.valueOf(internalVal).equals("true")) {
 %> <script>
							document.getElementById('paraFrm_inter').checked =true;
						</script> <%
 }
 %> &nbsp;<label class="set" name="intern" id="intern"
									ondblclick="callShowDiv(this);"><%=label.get("intern")%></label>
								&nbsp; <s:checkbox name="exter" onclick="callForExternal();" />
								<s:hidden name="external" /> <%
 if (String.valueOf(externalVal).equals("true")) {
 %> <script>
							document.getElementById('paraFrm_exter').checked =true;
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


			<!-- APPROVER LIST  AND KEEP INFORMED TABLE  STARTS -->
			<tr>
				<td colspan="6">
				  <table width="100%" border="0" cellpadding="1" cellspacing="0"
					class="formbg">
					<tr>
						<td colspan="1" width="35%" nowrap="nowrap"><strong>The
						Approver(s) for this application : </strong></td>

						<td colspan="1" width="15%" nowrap="nowrap"></td>

						<td colspan="1" width="20%" nowrap="nowrap"><strong>Keep
						Informed To : </strong></td>

						<td colspan="1" width="15%"><s:hidden name="employeeId" /><s:hidden
							name="employeeToken" /><s:textfield name="employeeName"
							readonly="true" /></td>

						<td colspan="1" width="5%"><img
							src="../pages/common/css/default/images/search2.gif"
							class="iconImage" width="16" height="15"
							onclick="javascript:getKeepInformedEmp();" /></td>

						<td colspan="1" width="10%"><s:submit name="" value=" Add"
							cssClass=" add" action="EmployeeRequi_addKeepInformedEmpList"
							onclick="return callAddApprover();" /></td>
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
									<td width="30%"><a href="#"
										onclick="callForRemove(<%=counter11%>);"> Remove</a></td>
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
		<!--  APPROVER LIST  AND KEEP INFORMED TABLE ENDS --> 

			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td>
						<table width="98%" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<strong class="text_head">Job Description : </strong>
								<td colspan="5" class="formhead"><img
									src="../pages/common/css/default/images/space.gif" width="5"
									height="7" /></td>
							</tr>
							<tr>
								<td width="24%" height="27" colspan="2"><label class="set"
									name="job.name" id="job.name" ondblclick="callShowDiv(this);"><%=label.get("job.name")%></label>
								:<font color="red">*</font></td>
								<td height="27" width="27%" nowrap="nowrap"><s:hidden
									name="jdCode" /> <s:textfield size="25" maxlength="50"
									name="jdDescName" readonly="false" /> <a href="#"> <img
									src="../pages/common/css/default/images/search2.gif" width="16"
									height="15" border="0" onclick="callJob();;" /> </a></td>
								<td width="4%" height="23" colspan="1"></td>
								<td width="20%" height="27" colspan="2" valign="top"></td>
								<td width="25%" height="27"><s:hidden name="minReuiredDays" /></td>
							</tr>

							<tr>
								<td width="24%" colspan="2" height="27" valign="top"><label
									class="set" name="job.desc" id="job.desc"
									ondblclick="callShowDiv(this);"><%=label.get("job.desc")%></label>
								: <font color="red">*</font></td>
								<td height="27" nowrap="nowrap"><s:textarea name="jdDesc"
									rows="3" cols="26" onkeyup="return callLength('isJbDesc');" />
								<img src="../pages/images/zoomin.gif" height="12" align="bottom"
									width="12" theme="simple"
									onclick="javascript:callWindow('paraFrm_jdDesc','job.desc','','1000','1000');"></td>
								<td width="4%" height="23" colspan="1"></td>
								<td width="20%" height="27" colspan="2" valign="top"><label
									class="set" name="roles.res" id="roles.res"
									ondblclick="callShowDiv(this);"><%=label.get("roles.res")%></label>
								: <font color="red">*</font></td>
								<td height="27" width="25%" nowrap="nowrap"><s:textarea
									name="jdRoleDesc" rows="3" cols="25"
									onkeyup="return callLength('isRoleDesc')" /><img
									src="../pages/images/zoomin.gif" height="12" align="bottom"
									width="12" theme="simple"
									onclick="javascript:callWindow('paraFrm_jdRoleDesc','roles.res','','1000','1000');">
								</td>
							</tr>

							<tr>
								<td width="24%" colspan="2" height="27" valign="top"><label
									class="set" name="specialreq" id="specialreq"
									ondblclick="callShowDiv(this);"><%=label.get("specialreq")%></label>:</td>
								<td height="27" nowrap="nowrap"><s:textarea
									name="specialReq" rows="3" cols="26"
									onkeyup="return callLength('isSpecialReq')" /><img
									src="../pages/images/zoomin.gif" height="12" align="bottom"
									width="12" theme="simple"
									onclick="javascript:callWindow('paraFrm_specialReq','specialreq','','1000','1000');">
								</td>
								<td height="23" width="4%" colspan="1"></td>
								<td width="20%" height="27" colspan="2" valign="top"><label
									class="set" name="persreq" id="persreq"
									ondblclick="callShowDiv(this);"><%=label.get("persreq")%></label>
								:</td>
								<td height="27" width="25%" nowrap="nowrap"><s:textarea
									name="persoanlReq" rows="3" cols="25"
									onkeyup="return callLength('isPersoanlReq')" /><img
									src="../pages/images/zoomin.gif" height="12" align="bottom"
									width="12" theme="simple"
									onclick="javascript:callWindow('paraFrm_persoanlReq','persreq','','1000','1000');">
								</td>

							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td><input type="button" name="Add" value="Add To Master"
									class="add" onclick="return addJobDesc()" /></td>
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
								<table width="32%" border="0" class="formbg">
									<tr>
										<td>
										<table width="32%" border="0" cellpadding="0" cellspacing="0">
											</td>
											</tr>

											<tr>
												<td><strong class="text_head">Vacancy Details
												</strong>:</td>


												<td align="right"><input type="button" class="add"
													value="Add Row" onclick="addRowToTableVacancy();" /> <input
													type="button" class="delete" value="Remove"
													onclick="chkDeleteVacancy();" /></td>
											</tr>
											<tr id="showLeadTime" style="display: none;">
												<td><strong class="text_head">Lead Time : <s:property
													value="leadTimeFlag" /> Days </strong></td>
											</tr>
											<tr>
												<td colspan="3">
												<table width="36%" id="tblVac" class="sortable">
													<tr>
														<td width="5%" valign="top" class="formth" nowrap="nowrap"><b>
														<label class="set" name="sr" id="sr2"
															ondblclick="callShowDiv(this);"><%=label.get("sr")%></label></b>
														</td>
														<td width="12%" valign="top" class="formth" align="left"
															nowrap="nowrap"><b><label class="set"
															name="vacn" id="vacn" ondblclick="callShowDiv(this);"><%=label.get("vacn")%></label></b><font
															color="red">*</font></td>
														<td width="12%" valign="top" class="formth" align="left"><b><label
															class="set" name="required.date" id="required.date"
															ondblclick="callShowDiv(this);"><%=label.get("required.date")%></label></b><font
															color="red">*</font></td>
														<td width="3%" valign="top" class="formth" align="left">&nbsp;</td>
														<td width="4%" valign="top" class="formth" align="center"><input
															type="checkbox" name="chkVacAll" id="paraFrm_chkVacAll"
															onclick="return callVacAll();" /></td>


													</tr>
													<%
													int v = 0;
													%>
													<s:iterator value="vacList">
														<tr class="border2">
															<td width="5%" align="center" class="sortableTD"><%=++v%></td>


															<td width="12%" align="center" class="sortableTD"><s:textfield
																name="noOfVac" id="<%="noOfVac"+v%>" size="17"
																onkeypress="return numbersOnly();" maxlength="5" /></td>
															<td width="12%" align="center" class="sortableTD"><input
																type="text" size="15" maxlength="10" name="vacDate"
																id="paraFrm_vacDate<%=v%>"
																value="<s:property value="vacDate" />"
																onkeypress="return numbersWithHiphen();" /></td>
															<td width="3%" class="sortableTD"><a
																href="javascript:NewCal('paraFrm_vacDate<%=v%>','DDMMYYYY');">
															<img src="../pages/images/Date.gif" class="iconImage"
																height="16" align="absmiddle" width="16"> </a></td>
															<td width="4%" align="center" class="sortableTD"><input
																type="checkbox" class="checkbox" value="N"
																name="confChkVac" id="paraFrm_confChkVac<%=v%>"
																onclick="callForVac('<%=v%>')" /> <s:hidden
																name="vacDetCode" /> <input type="hidden"
																name="hdeleteVac<%=v%>" id="paraFrm_hdeleteVac<%=v%>" />
															</td>

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

							<tr>
								<td colspan="2" class="formtext"></td>
							</tr>
						</table>
						<table width="100%">

							<tr>
								<td width="24%" height="22" class="formtext"><label
									class="set" name="comp" id="comp"
									ondblclick="callShowDiv(this);"><%=label.get("comp")%></label>
								:</span></td>
								<td width="27%" height="22"><label> <s:textfield
									name="reqCompensation" size="13" maxlength="5" readonly="false"
									onblur="return valCTC('paraFrm_reqCompensation','comp');"
									onkeypress="return numbersWithDot();" /> </label></td>
								<td width="4%" height="22"></td>
								<td width="20%" height="22" class="formtext" nowrap="nowrap">&nbsp;<label
									class="set" name="experience" id="experience1"
									ondblclick="callShowDiv(this);"><%=label.get("experience")%></label>
								:</td>
								<td width="25%" height="22"><label class="set"
									name="minexp" id="minexp" ondblclick="callShowDiv(this);"><%=label.get("minexp")%></label><label>
								<s:textfield name="minExp" size="3" maxlength="5"
									onkeypress="return numbersWithDot();" /> <label class="set"
									name="maxexp" id="maxexp" ondblclick="callShowDiv(this);"><%=label.get("maxexp")%></label>
								<s:textfield name="maxExp" size="3" maxlength="5"
									onkeypress="return numbersWithDot();" /></td>
							</tr>

							<tr>
								<td width="24%" height="22" class="formtext"><label
									class="set" name="vactype" id="vactype"
									ondblclick="callShowDiv(this);"><%=label.get("vactype")%></label>
								:</span></td>
								<td width="27%" height="22"><label> <s:select
									name="vacType" disabled="false"
									list="#{'':'Select','N':'New Post','R':'Replacement'}"
									onchange="callShowReplacement();" /></label></td>
								<td width="4%" height="22"></td>
								<td width="20%" height="22" class="formtext" nowrap="nowrap"><label
									class="set" name="contracttype" id="contracttype"
									ondblclick="callShowDiv(this);"><%=label.get("contracttype")%></label>
								:</td>
								<td width="25%" height="22"><label> <s:select
									name="reqConType" disabled="false"
									list="#{'':'Select','R':'Regular','O':'On Contract'}" /></label><label>
								<s:select name="reqPartFull"
									list="#{'':'Select','P':'Part Time','F':'Full Time'}" /></label></td>
							</tr>
							<tr id="showReplacement" style="display: none">
								<td width="24%" height="22" class="formtext">Select
								Employees for replacement :</td>
								<td colspan="4">
									<s:hidden name="replaceEmpId" /> 
									<s:hidden name="hiddenEmpId" /> 
									<s:hidden name="hiddenEmpName" /> 
									<s:hidden name="dummyTokenForReplace" /> 
									<s:textarea
									name="replaceEmpName" rows="3" cols="70" readonly="true" /> <input
									type="button" class="add"
									onclick="javascript:callsF9(500,325,'EmployeeRequi_f9replacementEmp.action');"
									value="Add" /> <input type="button" class="delete"
									value="Remove" onclick="removeReplaceEmp();" /></td>
							</tr>
							<tr id="hideReplacement" style="display: none">
								<td width="24%" height="22" class="formtext">Comment :</td>
								<td colspan="4"><s:textarea name="newPostComment" rows="3"
									cols="70" /></td>
							</tr>

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
						i = 0;
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
				</s:if>
			<tr>

				<s:if test="%{offerAppointFlag}">
					<td width="29%" nowrap="nowrap"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				</s:if>
				<s:else>
					<td width="78%" nowrap="nowrap"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				</s:else>
				<s:if test="%{offerAppointFlag}">
					<td nowrap="nowrap"><s:submit cssClass="token"
						action="EmployeeRequi_backToOfferAppoint" theme="simple"
						value='Back To %{createNewFlag}' onclick="return callView()" /></td>
				</s:if>
				</td>

				<td width="30%" align="right"><strong>Page 1 of 2</strong></td>
				<td></td>

			</tr>
		</s:else>
	</table>
	<s:hidden name="hiddenDeleteId" id="hiddenDeleteId" />
</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script>
  
  
  
callOnload();
function callOnload(){
	try {
		if(eval(document.getElementById('paraFrm_leadTimeFlag').value) > 0){
			document.getElementById('showLeadTime').style.display = '';
		} else {
			document.getElementById('showLeadTime').style.display = 'none';
		}
	
		if(document.getElementById('paraFrm_vacType').value == 'R'){
			document.getElementById('showReplacement').style.display = '';
			document.getElementById('hideReplacement').style.display = 'none';
		} else if(document.getElementById('paraFrm_vacType').value == 'N'){
			document.getElementById('hideReplacement').style.display = '';
			document.getElementById('showReplacement').style.display = 'none';
		} else {
			document.getElementById('showReplacement').style.display = 'none';
			document.getElementById('hideReplacement').style.display = 'none';
		}  
		
		var requiredByDate = trim(document.getElementById('vacDate').value);
		if(requiredByDate == "") {
			document.getElementById('vacDate').value = document.getElementById('paraFrm_calCulayedRequiredByDate').value;
		} 
	}catch(e) {
		//alert("Unable to open Requisition Application >>"+e);
	}	
}

function removeReplaceEmp(){

		if(document.getElementById('paraFrm_replaceEmpId').value==""){
			alert("No employees added");
			return false;
		} //end of if
		window.open('','new','top=100,left=300,width=700,height=400,scrollbars=yes,status=yes,resizable=no');
	 	document.getElementById("paraFrm").target="new";
		document.getElementById("paraFrm").action = 'EmployeeRequi_removeEmployee.action'; 
	  	document.getElementById("paraFrm").submit();
	  	document.getElementById("paraFrm").target = "main";
} //end of removeReplaceEmp method

function callShowReplacement(){
	if(document.getElementById('paraFrm_vacType').value == 'R'){
		document.getElementById('showReplacement').style.display = '';
		document.getElementById('hideReplacement').style.display = 'none';
		document.getElementById('paraFrm_replaceEmpId').value="";
		document.getElementById('paraFrm_replaceEmpName').value="";
		document.getElementById('paraFrm_newPostComment').value="";
	} //end of if
	else if(document.getElementById('paraFrm_vacType').value == 'N'){
		document.getElementById('hideReplacement').style.display = '';
		document.getElementById('showReplacement').style.display = 'none';
		document.getElementById('paraFrm_replaceEmpId').value="";
		document.getElementById('paraFrm_replaceEmpName').value="";
		document.getElementById('paraFrm_newPostComment').value="";
	} //end of if
	else{
		document.getElementById('showReplacement').style.display = 'none';
		document.getElementById('hideReplacement').style.display = 'none';
		document.getElementById('paraFrm_replaceEmpId').value="";
		document.getElementById('paraFrm_replaceEmpName').value="";newPostComment
		document.getElementById('paraFrm_newPostComment').value="";
	} //end of else
} //end of callShowReplacement method
  
  function verifyandsendFun(){

  var status=document.getElementById('paraFrm_reqName').value;
  var reqStatus=document.getElementById('paraFrm_reqApprStatus').value;
  var jobN=document.getElementById("paraFrm_jdDescName").value;
		var jobD=document.getElementById("paraFrm_jdDesc").value;
		var jobRoleRes=document.getElementById("paraFrm_jdRoleDesc").value;
		var pers=document.getElementById("paraFrm_persoanlReq").value;
		var splReq=document.getElementById("paraFrm_specialReq").value;
	
		var comp=document.getElementById("paraFrm_reqCompensation").value;
   		var compensation=comp.split(".");
   		var compen=comp.indexOf('.');
   		var compInLacs=comp.substring(eval(compen)+1,comp.length);
   		
   		var min=document.getElementById("paraFrm_minExp").value;
   		var minimum=min.split(".");
   		var minimumE=min.indexOf('.');
   		var minimumExp=min.substring(eval(minimumE)+1,min.length);
   		
   		var max=document.getElementById("paraFrm_maxExp").value;
   		var maximum=max.split(".");
   		var maximumE=max.indexOf('.');
   		var maximumExp=max.substring(eval(maximumE)+1,max.length);
	      
		var fieldName = ["paraFrm_reqPositionName","paraFrm_reqDiv",
							"paraFrm_reqBrn","paraFrm_reqDept","paraFrm_hiringManager","paraFrm_jdDescName",
							"paraFrm_jdDesc","paraFrm_jdRoleDesc"];
	
		var lableName = ["position","division","branch","department",
							"hiring.mgr","job.name","job.desc","roles.res"];
		var flag = ["select","select","select","select","select","enter/select","enter","enter"];
		
		if(trim(document.getElementById('paraFrm_reqName').value)==""){
			alert("Please enter the "+document.getElementById('reqs.code').innerHTML.toLowerCase());
			  document.getElementById('paraFrm_reqName').focus();
			return false;
		}
		
		if(trim(document.getElementById('paraFrm_reqDt').value)==""){
		    alert("Please enter/select the "+document.getElementById('reqs.date').innerHTML.toLowerCase());
		    document.getElementById('paraFrm_reqDt').focus();
			return false;
		}else if(!validateDate("paraFrm_reqDt",'reqs.date')){
		 document.getElementById('paraFrm_reqDt').focus();
			return false;
		}
		
    	if(!validateBlank(fieldName,lableName,flag)){
             return false;
     	 }else if(!chkDupVac()){
			return false;
		}else if(!chkDate()){
			return false;
		}else if(jobN.length >1000){
			alert("Maximum length of "+document.getElementById('job.name').innerHTML.toLowerCase()+" is 1000 characters.");
			return false;
		}else if(jobD.length >1000){
			alert("Maximum length of "+document.getElementById('job.desc').innerHTML.toLowerCase()+" is 1000 characters.");
			return false;
		}else if(jobRoleRes.length >1000){
			alert("Maximum length of "+document.getElementById('roles.res').innerHTML.toLowerCase()+" is 1000 characters.");
			return false;
		}else if(splReq.length >1000){
			alert("Maximum length of "+document.getElementById('specialreq').innerHTML.toLowerCase()+" is 1000 characters.");
			return false;
		}else if(pers.length >1000){
			alert("Maximum length of "+document.getElementById('persreq').innerHTML.toLowerCase()+" is 1000 characters.");
			return false;
		}else if(!checkDate()){
		    return false;
		}
		 if(!valCTC('paraFrm_reqCompensation','comp')) {
		 	return false;
		 }
				
		
		/*else 
		if(compensation.length-1 >1){
				alert("More than one dot not allowed in "+document.getElementById('comp').innerHTML.toLowerCase());   	     
	   	     	return false;
	   	}else if(eval(compInLacs.length)>2){
					alert("Only two digits allowed after the decimal point in "+document.getElementById('comp').innerHTML.toLowerCase());
					return false;
	   	}else */
	   	if(!valExp('paraFrm_maxExp','maxexp')){
		  return false;
		}else if(!valExp('paraFrm_minExp','minexp')){
		  return false;
		}else if(eval(min)>eval(max)){
			alert(document.getElementById('maxexp').innerHTML+" experience should be greater than "+document.getElementById('minexp').innerHTML.toLowerCase()+" experience");
			return false;
		}else if(reqStatus=="Approved"){
	  			alert("Application already approved.So can't be send for approval");
	  			return false;
  		}else if(reqStatus=="Pending"){
	  			alert("Application already pending.So can't be send for approval");
	  			return false;
  		}else if(reqStatus=="On Hold"){
	  			alert("Application already on hold.So can't be send for approval");
	  			return false;
  		}else{	
				var chkLeadTime = 0;
				if(document.getElementById("paraFrm_leadTimeFlag").value > 0){
					chkLeadTime = leadTimeFun();
				}
  				if(chkLeadTime >0){
					var conf=confirm("Your Required by date is greater than lead time\n Do you want to proceed ?");
					if(conf){
						document.getElementById('paraFrm').action = "EmployeeRequi_sendForApproveInFirst.action";
						document.getElementById('paraFrm').submit();
					}else{
					     return false;
					}
				}else {
					document.getElementById('paraFrm').action = "EmployeeRequi_sendForApproveInFirst.action";
					document.getElementById('paraFrm').submit();
				}	
  		
  			//document.getElementById("paraFrm_updateFirstFlag").value="true";
  			//	var wind = window.open('','wind','width=1000,height=400,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
			//	document.getElementById('paraFrm').target = "wind";
			//	document.getElementById('paraFrm').action = "EmployeeRequi_sendForApproveInFirst.action";
			//	document.getElementById('paraFrm').submit();
			//	document.getElementById('paraFrm').target = "main";
	    }	       
  }
  
  
  
  function callDate(id){
  

  NewCal('paraFrm_hvacDate'+id,'DDMMYYYY');
  
  }
  
  
  function callDom(id)
    {

		
	   
	    callsF9(500,325,'EmployeeRequi_f9Dom.action'); 
     
      
   }
  
  
 
  
  
function callForVac(id)
	   {
	 	  
	 
	   if(document.getElementById('paraFrm_confChkVac'+id).checked == true)
	   {	  
	    document.getElementById('paraFrm_hdeleteVac'+id).value="Y";
	   }
	   else{
	   document.getElementById('paraFrm_hdeleteVac'+id).value="";
	   	
   		}
  }
  
  
  function chkDeleteVacancy()
	{
			var tbl = document.getElementById('tblVac');
			var rowLen = tbl.rows.length;
	 if(chkVac()){
	 var con=confirm('Do you really want to remove this record ?');
	 if(con){
	   document.getElementById('paraFrm').action="EmployeeRequi_deleteVacancy.action";
	    document.getElementById('paraFrm').submit();
	    } else{
	     	for (var idx = 1; idx < rowLen; idx++) {
	
			document.getElementById("paraFrm_confChkVac"+idx).checked = false;
			document.getElementById('paraFrm_hdeleteVac'+idx).value="";
			document.getElementById("paraFrm_chkVacAll").checked=false; 
     			}
	    }
	 }else {
	 	alert('Please select one record to remove');
	 	 return false;
	 }
	 
	}
	
	
	
	function chkVac(){
	var tbl = document.getElementById('tblVac');
var rowLen = tbl.rows.length;

	
	  for(var a=1;a<rowLen;a++){	
	   if(document.getElementById('paraFrm_confChkVac'+a).checked == true)
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
	if (document.getElementById("paraFrm_chkVacAll").checked == true){
	for (var idx = 1; idx < rowLen; idx++) {
	
				document.getElementById("paraFrm_confChkVac"+idx).checked = true;
				 document.getElementById('paraFrm_hdeleteVac'+idx).value="Y";
				
	    }

 }else{
	for (var idx = 1; idx < rowLen; idx++) {
	
	document.getElementById("paraFrm_confChkVac"+idx).checked = false;
	document.getElementById('paraFrm_hdeleteVac'+idx).value="";
     }
  }	 
		
	
  }
  

	function addRowToTableVacancy()
	{
		  var tbl = document.getElementById('tblVac');
		  var leadTimeDate = document.getElementById('paraFrm_calCulayedRequiredByDate').value;
		  var lastRow = tbl.rows.length;
		 
		  
		  // if there's no header row in the table, then iteration = lastRow + 1
		  var iteration = lastRow;
		  var row = tbl.insertRow(lastRow);
		  
		 
		  // left cell
		  var cellLeft = row.insertCell(0);
		  var textNode = document.createTextNode(iteration);
		  cellLeft.align='center';
		  cellLeft.className='sortableTD';
		  cellLeft.appendChild(textNode);
	  
	  		
	  
   		  var cellNoOfVac = row.insertCell(1);
   		  var vac = document.createElement('input');
		  vac.type = 'text';
		  vac.name = 'noOfVac';
		  vac.id = 'noOfVac'+iteration;
		  cellNoOfVac.className='sortableTD';
		  vac.maxLength='5';
		  vac.size='17';
		  cellNoOfVac.align='center';
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
				return false;
			if (window.event) //IE
			    window.event.returnValue = null;     else //Firefox
			    e.preventDefault();
  }			
		  cellNoOfVac.appendChild(vac);
  
		  var cellVacDate = row.insertCell(2);
		  var vacDate = document.createElement('input');
  
		  vacDate.type = 'text';
		  vacDate.name = 'vacDate';
		  vacDate.id =  'paraFrm_vacDate'+iteration;
		  vacDate.size='15';
		  vacDate.value = leadTimeDate;
		  vacDate.maxLength='10';
		  cellVacDate.className='sortableTD';
		  vacDate.onkeypress=function(){
		  				document.onkeypress = numbersWithHiphen;
						var key //= (window.event) ? event.keyCode : e.which;
						if (window.event)
						    key = event.keyCode
						else
						   	key = e.which
						clear();
						// Was key that was pressed a numeric character (0-9) or backspace (8)?
						if ( (key > 47 && key < 58) || key == 8 || key == 45 || key == 0){
						 	return true;} // if so, do nothing
						else {// otherwise, discard character
							return false;
						}
						if (window.event) //IE
						    window.event.returnValue = null;     else //Firefox
						    e.preventDefault();
							  
		  
		  };
		 cellVacDate.align='center';
		 cellVacDate.appendChild(vacDate);
 
		  var cellVacImg= row.insertCell(3);
		  var img=  document.createElement('img');
		  img.type='image';
		  img.src="../pages/images/Date.gif";
		  img.height='16';
		 img.align='absmiddle';
		 img.width='16';
		 img.id='img'+ iteration;
		 img.theme='simple';
		
		 cellVacImg.className='sortableTD';
		
		 
		 img.onclick=function(){	 	
		 
				NewCal('paraFrm_vacDate'+iteration,'DDMMYYYY');
	 		
	     };
	     cellVacImg.align='left';
	     cellVacImg.width='6';
 		cellVacImg.appendChild(img);
	  
		  var cellVacChk = row.insertCell(4);
		  var vacChk = document.createElement('input');
		 
		  vacChk.type = 'checkbox';
		  vacChk.name = 'confChkVac';
		  cellVacChk.className='sortableTD';
		  vacChk.id = 'paraFrm_confChkVac'+iteration;
		  vacChk.onclick=function(){
		 	  if(document.getElementById('paraFrm_confChkVac'+iteration).checked == true)
			   {	
			    	document.getElementById('paraFrm_hdeleteVac'+iteration).value="Y";
			    	
			   }  else{
			  	    document.getElementById('paraFrm_hdeleteVac'+iteration).value="";
			   	
		   		}
			};
 
		  cellVacChk.align='center';
		  //cellVacChk.width='8';
		  cellVacChk.appendChild(vacChk);
		 // var hiddenDel=row.insertCell(5);
		  var hid = document.createElement('input');
		  hid.type = 'hidden';
		  hid.name = 'hdeleteVac'+iteration;
		  hid.id = 'paraFrm_hdeleteVac' +iteration;
		 // hiddenDel.appendChild(hid);
		  cellVacChk.appendChild(hid);
		  
		 // var cellVacCode=row.insertCell(6);
	  	  var code=document.createElement('input');
	  	  code.type='hidden';
	  	  code.name='vacDetCode';
	  	  code.id='vacDetCode';
	   cellVacChk.appendChild(code);
	  	//  cellVacCode.appendChild(code);
		  
		  
	}  
	
	
function chkDate(){
		var tbl = document.getElementById('tblVac');
  		var lastRow = tbl.rows.length;
  		for(var i=1;i<lastRow;i++){
  				if(!validateDate('paraFrm_vacDate'+i,'required.date'))
  		    	return false;
  		
  		 }

return true;

}	

function chkDupVac(){

var tbl = document.getElementById('tblVac');
var lastRow = tbl.rows.length;
if(lastRow<2){
alert("Please add at least one row value in vacancy details.");
return false;
}

var flag=false;
for(var i=1;i<lastRow;i++){
					
 		 				var cert= document.getElementById('noOfVac'+i).value;
 		 				var dt= document.getElementById('paraFrm_vacDate'+i).value;
 		 				if(cert!=""){
 		 					flag=true;
 		 				}
 		 	var cnt=0;
 		 		for(var d=1;d<lastRow;d++){
	 		 			var dupCert= document.getElementById('noOfVac'+d).value;
	 		 			var dupDt= document.getElementById('paraFrm_vacDate'+d).value;
 		 			
	 		 		if(dupDt!=""){
	 		 		if(dupDt == dt){
	 		 			cnt++;
	 		 		}
	 		 		}
	 		 		if(cnt > 1){
	 		 			alert("Please enter/select  different "+ document.getElementById('required.date').innerHTML.toLowerCase() +" in vacancy details.");
	 		 			return false;
	 		 		}
 		 		}
 		 	
 		 	if(cert!=""){
 		 	if(Math.abs(cert)==0){
 		 	alert("Zero not allowed in "+ document.getElementById('vacn').innerHTML.toLowerCase());
 		 	document.getElementById('noOfVac'+i).focus();
 		 	return false;
 		 	}
 		 	}
 		 	
 		 	if(dt!=""){
 		 	  if(cert==""){
 		 		alert("Please enter "+document.getElementById('vacn').innerHTML.toLowerCase()+" in vacancy details");
 		 		document.getElementById('noOfVac'+i).focus();
 		 		return false;
 		 	      }
 		  	}
 		  	
 		  	if(cert!=""){
 		 	  if(dt==""){
 		 		alert("Please enter/select  "+document.getElementById('required.date').innerHTML.toLowerCase()+" in vacancy details");
 		 		document.getElementById('paraFrm_vacDate'+i).focus();
 		 		return false;
 		 	      }
 		 	      else{
 		  		try
				{		
					if(!check())
					{
						return false;
						}
						
									
					
				/*
					var requisitionDate = document.getElementById("paraFrm_reqDt").value;
					var minimumReuiredDays = document.getElementById("paraFrm_minReuiredDays").value;			
					alert("Date==>"+requisitionDate);
					alert("minimumReuiredDays==>"+minimumReuiredDays);		
					var x=requisitionDate.split("-"); //split the date by '/' 
					alert("x--->"+x);
		
					x=new Date(x[2],   x[1]-1 ,x[0]); //create a new date object 
					x.setDate(x.getDate()+eval(minimumReuiredDays));
		 			var output =x.getDate()+'-'+x.getMonth()+'-'+x.getFullYear();
		 			alert("output  ==>          "+output); 
		 			dateDifferenceEqual(output,dt,'paraFrm_vacDate'+i,'reqs.date','required.date');
		 			//return false;
		 		*/			
				}
				catch(e)
				{
					alert("Exception==>"+e);
					return false;
				}	
 		  	}
 		  	}
 		 	
 		}
 		
 	if(!flag){
			alert("Please enter "+document.getElementById('vacn').innerHTML.toLowerCase()+" in vacancy details");
			return false;
		}	
 		
 	
 		
 return true;		 
 		
 }				

	function deleteFun(){
	
		var conf=confirm("Do you really want to delete this record ?");
		if(conf){
			document.getElementById("paraFrm").action="EmployeeRequi_delete.action";
	   		document.getElementById("paraFrm").submit();
				
		}else{
		
		     return false;
		}
	
	
	}

	function addnewFun(){
		document.getElementById("paraFrm").action="EmployeeRequi_addNew.action";
	    document.getElementById("paraFrm").submit();
	    return false;
	}
	
	function leadTimeFun(){
	try{
		var leadTime = document.getElementById('paraFrm_leadTimeFlag').value;
		var reqDate = document.getElementById('paraFrm_reqDt').value;
		/*
		var reqDtMonth = reqDate.split('-');
		var newDtMonth = "";
		var newDtYear = "";
		var newFinalDt = "";
		newDtMonth = eval(reqDtMonth[1]) + eval(leadTime);
		if(Math.floor(eval(eval(newDtMonth)/12)) > 0){
			newDtYear =  Math.floor(eval(reqDtMonth[2]) + eval(eval(newDtMonth)/12));
			if(eval(newDtMonth)%12==0){
				newDtMonth=12;
			}
			else{
				newDtMonth = eval(newDtMonth)%12;
			}
			if(eval(newDtMonth)< 10){
				newDtMonth = "0"+newDtMonth;
			}
			newFinalDt = reqDtMonth[0]+"-"+newDtMonth+"-"+newDtYear;
		} //end of if
		else{
			if(eval(newDtMonth)< 10){
				newDtMonth = "0"+newDtMonth;
			}
			newFinalDt = reqDtMonth[0]+"-"+newDtMonth+"-"+reqDtMonth[2];
		}
		*/
		var calCulatedReqrdByDate = document.getElementById('paraFrm_calCulayedRequiredByDate').value;
		var tbl = document.getElementById('tblVac');
		var lastRow = tbl.rows.length;
		var count=0;
		for(var i=1;i<lastRow;i++){
			var vacDt= document.getElementById('paraFrm_vacDate'+i).value;
			if(dateDifferenceLeadTime(vacDt,calCulatedReqrdByDate)){
				count = 1;
			}
 		} //end of loop
		return count;
	}catch(e){
		alert(e);
	}
	} //end of method
	
	
	function dateDifferenceLeadTime(fromDate, toDate){
		strDate1 = fromDate.split("-"); 
		starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
	
		strDate2 = toDate.split("-"); 
		endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 
		if(endtime < starttime){
			return true;
		}
		return false; 
	}
	
	function saveFun(){
		var chkLeadTime = 0;
		if(document.getElementById("paraFrm_leadTimeFlag").value > 0){
			chkLeadTime = leadTimeFun();
		}
	
	  if(document.getElementById('paraFrm_inter').checked) {	  
	      document.getElementById('paraFrm_internal').value="true";
	  	 } else {
	       document.getElementById('paraFrm_internal').value="";
   		}
   		
   		 if(document.getElementById('paraFrm_exter').checked) {	  
	        document.getElementById('paraFrm_external').value="true";
	      } else {
	        document.getElementById('paraFrm_external').value="";
   		}
   		
   		var comp=document.getElementById("paraFrm_reqCompensation").value;
   		var compensation=comp.split(".");
   		var compen=comp.indexOf('.');
   		var compInLacs=comp.substring(eval(compen)+1,comp.length);
   		
		var min=document.getElementById("paraFrm_minExp").value;
   		var minimum=min.split(".");
   		var minimumE=min.indexOf('.');
   		var minimumExp=min.substring(eval(minimumE)+1,min.length);
   		
   		var max=document.getElementById("paraFrm_maxExp").value;
   		var maximum=max.split(".");
   		var maximumE=max.indexOf('.');
   		var maximumExp=max.substring(eval(maximumE)+1,max.length);
   		
   		
		var jobN=document.getElementById("paraFrm_jdDescName").value;
		var jobD=document.getElementById("paraFrm_jdDesc").value;
		var jobRoleRes=document.getElementById("paraFrm_jdRoleDesc").value;
		var pers=document.getElementById("paraFrm_persoanlReq").value;
		var splReq=document.getElementById("paraFrm_specialReq").value;
	
		var fieldName = ["paraFrm_reqPositionName","paraFrm_reqDiv",
							"paraFrm_reqBrn","paraFrm_reqDept","paraFrm_hiringManager","paraFrm_jdDescName",
							"paraFrm_jdDesc","paraFrm_jdRoleDesc"];
	
							var lableName = ["position","division","branch","department",
							"hiring.mgr","job.name","job.desc","roles.res"];
		var flag = ["select","select","select","select","select","enter/select","enter","enter"];
		
		if(trim(document.getElementById('paraFrm_reqName').value)==""){
			alert("Please enter the "+document.getElementById('reqs.code').innerHTML.toLowerCase());
			document.getElementById('paraFrm_reqName').focus();
			return false;
		}
		
		if(trim(document.getElementById('paraFrm_reqDt').value)==""){
		    alert("Please enter/select the "+document.getElementById('reqs.date').innerHTML.toLowerCase());
		    document.getElementById('paraFrm_reqDt').focus();
			return false;
		}else if(!validateDate("paraFrm_reqDt",'reqs.date')){
		 document.getElementById('paraFrm_reqDt').focus();
			return false;
		}
    	if(!validateBlank(fieldName,lableName,flag)){
             return false;
     	 }else if(!chkDupVac()){
			return false;
		
		}else if(!chkDate()){
			return false;
		
		}else if(jobN.length >1000){
			alert("Maximum length of "+document.getElementById('job.name').innerHTML.toLowerCase()+" is 1000 characters.");
			return false;
		}else if(jobD.length >1000){
			alert("Maximum length of "+document.getElementById('job.desc').innerHTML.toLowerCase()+" is 1000 characters.");
			return false;
		
		}else if(jobRoleRes.length >1000){
			alert("Maximum length of "+document.getElementById('roles.res').innerHTML.toLowerCase()+" is 1000 characters.");
			return false;
		
		}else if(splReq.length >1000){
			alert("Maximum length of "+document.getElementById('specialreq').innerHTML.toLowerCase()+" is 1000 characters.");
			return false;
		
		}else if(pers.length >1000){
			alert("Maximum length of "+document.getElementById('persreq').innerHTML.toLowerCase()+" is 1000 characters.");
			return false;
		
		}else if(!checkDate()){
		
		    return false;
		}
		
		if(!valCTC('paraFrm_reqCompensation','comp'))
		return false;
	
	   	
	  
		
		
		if(!valExp('paraFrm_maxExp','maxexp')){
		  return false;
		}else if(!valExp('paraFrm_minExp','minexp')){
		  return false;
		}else if(eval(min)>eval(max)){
			alert(document.getElementById('maxexp').innerHTML+" experience should be greater than "+document.getElementById('minexp').innerHTML.toLowerCase()+" experience");
			return false;
		
		  }else if(document.getElementById("paraFrm_updateFirstFlag").value=="true"){
		
			if(chkLeadTime >0){
				var conf=confirm("Your Required by date is greater than lead time\n Do you want to proceed ?");
				if(conf){
					document.getElementById("paraFrm").action="EmployeeRequi_updateFirst.action";
	    			document.getElementById("paraFrm").submit();
						
				}else{
				
				     return false;
				}
			}	
			else{
				document.getElementById("paraFrm").action="EmployeeRequi_updateFirst.action";
		    	document.getElementById("paraFrm").submit();
			}
		}
		else{
			if(chkLeadTime >0){
				var conf=confirm("Your Required by date is greater than lead time\n Do you want to proceed ?");
				if(conf){
					document.getElementById("paraFrm").action="EmployeeRequi_saveFirst.action";
		    		document.getElementById("paraFrm").submit();
				}else{
				
				     return false;
				}
			}	
			else{
				document.getElementById("paraFrm").action="EmployeeRequi_saveFirst.action";
		   		document.getElementById("paraFrm").submit();
			}
	    }
	  return false;
	}
	
	
	function saveandnextFun(){
	
		var chkLeadTime = 0;
		if(document.getElementById("paraFrm_leadTimeFlag").value > 0){
			chkLeadTime = leadTimeFun();
		} //end of if
		var jobN=document.getElementById("paraFrm_jdDescName").value;
		var jobD=document.getElementById("paraFrm_jdDesc").value;
		var jobRoleRes=document.getElementById("paraFrm_jdRoleDesc").value;
		var pers=document.getElementById("paraFrm_persoanlReq").value;
		var splReq=document.getElementById("paraFrm_specialReq").value;
		
		var comp=document.getElementById("paraFrm_reqCompensation").value;
   		var compensation=comp.split(".");
   		var compen=comp.indexOf('.');
   		var compInLacs=comp.substring(eval(compen)+1,comp.length);
   		
		var min=document.getElementById("paraFrm_minExp").value;
   		var minimum=min.split(".");
   		var minimumE=min.indexOf('.');
   		var minimumExp=min.substring(eval(minimumE)+1,min.length);
   		
   		var max=document.getElementById("paraFrm_maxExp").value;
   		var maximum=max.split(".");
   		var maximumE=max.indexOf('.');
   		var maximumExp=max.substring(eval(maximumE)+1,max.length);
	
	   if(document.getElementById('paraFrm_inter').checked==true)
	      {	  
	  
	          document.getElementById('paraFrm_internal').value="true";
		
	   }else{
	       document.getElementById('paraFrm_internal').value="";
	  
   		}
   		
   		
   		
   		 if(document.getElementById('paraFrm_exter').checked==true)
	      {	  
	           document.getElementById('paraFrm_external').value="true";
		
	      }  else{
	           document.getElementById('paraFrm_external').value="";
	  
   		}
   		
   		
	
		var fieldName = ["paraFrm_reqPositionName","paraFrm_reqDiv",
							"paraFrm_reqBrn","paraFrm_reqDept","paraFrm_hiringManager","paraFrm_jdDescName",
							"paraFrm_jdDesc","paraFrm_jdRoleDesc"];
	
							var lableName = ["position","division","branch","department",
							"hiring.mgr","job.name","job.desc","roles.res"];
		var flag = ["select","select","select","select","select","enter/select","enter","enter"];
		
		
		if(document.getElementById("paraFrm_updateFirstFlag").value=="true"){
			if(trim(document.getElementById('paraFrm_reqName').value)==""){
				alert("Please enter the "+document.getElementById('reqs.code').innerHTML.toLowerCase());
				document.getElementById('paraFrm_reqName').focus();
				return false;
			}
			
			if(trim(document.getElementById('paraFrm_reqDt').value)==""){
			    alert("Please enter/select the "+document.getElementById('reqs.date').innerHTML.toLowerCase());
			    document.getElementById('paraFrm_reqDt').focus();
				return false;
			}else if(!validateDate("paraFrm_reqDt",'reqs.date')){
			 document.getElementById('paraFrm_reqDt').focus();
				return false;
			}
			
				
		  if(!validateBlank(fieldName,lableName,flag)){
		             return false;
		   }else if(!chkDupVac()){
					return false;
				
		  }else if(!chkDate()){
					return false;
				
		   }else if(jobN.length >1000){
			alert("Maximum length of "+document.getElementById('job.name').innerHTML.toLowerCase+" is 1000 characters.");
			return false;
		  }else if(jobD.length >1000){
			alert("Maximum length of "+document.getElementById('job.desc').innerHTML.toLowerCase()+" is 1000 characters.");
			return false;
		
		  }else if(jobRoleRes.length >1000){
			alert("Maximum length of "+document.getElementById('roles.res').innerHTML.toLowerCase()+" is 1000 characters.");
			return false;
		
		  }else if(splReq.length >1000){
			alert("Maximum length of "+document.getElementById('specialreq').innerHTML.toLowerCase()+" is 1000 characters.");
			return false;
		
		  }else if(pers.length >1000){
			alert("Maximum length of "+document.getElementById('persreq').innerHTML.toLowerCase()+" is 1000 characters.");
			return false;
		
		  }
		
		  if(!valCTC('paraFrm_reqCompensation','comp'))
		   return false;
		
   	     
   	     if(!checkDate()){
		
		    return false;
		}
		
		if(!valExp('paraFrm_maxExp','maxexp')){
		  return false;
		}else if(!valExp('paraFrm_minExp','minexp')){
		  return false;
		}else if(eval(min)>eval(max)){
			alert(document.getElementById('maxexp').innerHTML+" experience should be greater than "+document.getElementById('minexp').innerHTML.toLowerCase()+" experience");
			return false;
		
		  }else{
				if(chkLeadTime >0){
				var conf=confirm("Your Required by date is greater than lead time\n Do you want to proceed ?");
					if(conf){
						document.getElementById("paraFrm").action="EmployeeRequi_nextUpdatePage.action";
		    			document.getElementById("paraFrm").submit();
					}else{
					
					     return false;
					}
				}	
				else{
					document.getElementById("paraFrm").action="EmployeeRequi_nextUpdatePage.action";
		    		document.getElementById("paraFrm").submit();
				}
	    	
	    }
	
	    }else if(document.getElementById("paraFrm_nextFlag").value=="true"){
	    		if(trim(document.getElementById('paraFrm_reqName').value)==""){
					alert("Please enter the "+document.getElementById('reqs.code').innerHTML.toLowerCase());
					document.getElementById('paraFrm_reqName').focus();
					return false;
				}
				
				if(trim(document.getElementById('paraFrm_reqDt').value)==""){
				    alert("Please enter/select the "+document.getElementById('reqs.date').innerHTML.toLowerCase());
				    document.getElementById('paraFrm_reqDt').focus();
					return false;
				}else if(!validateDate("paraFrm_reqDt",'reqs.date')){
				 document.getElementById('paraFrm_reqDt').focus();
					return false;
				}
			    if(!validateBlank(fieldName,lableName,flag)){
			             return false;
			    }else if(!chkDupVac()){
						return false;
					
				}else if(!chkDate()){
						return false;
					
				}else if(jobN.length >1000){
				     alert("Maximum length of "+document.getElementById('job.name').innerHTML.toLowerCase()+" is 1000 characters.");
				      return false;
			    }else if(jobD.length >1000){
					alert("Maximum length of "+document.getElementById('job.desc').innerHTML.toLowerCase()+" is 1000 characters.");
					return false;
			
			   }else if(jobRoleRes.length >1000){
					alert("Maximum length of "+document.getElementById('roles.res').innerHTML.toLowerCase()+" is 1000 characters.");
				    return false;
			
			  }else if(splReq.length >1000){
				alert("Maximum length of "+document.getElementById('specialreq').innerHTML.toLowerCase()+" is 1000 characters.");
				return false;
			
			  }else if(pers.length >1000){
				alert("Maximum length of "+document.getElementById('persreq').innerHTML.toLowerCase()+" is 1000 characters.");
				return false;
			
			  }else if(!checkDate()){
			
			    return false;
			}
			 if(!valCTC('paraFrm_reqCompensation','comp'))
				return false;
			
	   	    if(!valExp('paraFrm_maxExp','maxexp')){
		       return false;
		      }else if(!valExp('paraFrm_minExp','minexp')){
		        return false;
		      }else if(eval(min)>eval(max)){
				alert(document.getElementById('maxexp').innerHTML+" experience should be greater than "+document.getElementById('minexp').innerHTML.toLowerCase()+" experience");
				return false;
			  }else{  
		     			document.getElementById("paraFrm").action="EmployeeRequi_getNextPage.action";
				    	document.getElementById("paraFrm").submit();
				   }  	
	    }else{
	    
			    		if(trim(document.getElementById('paraFrm_reqName').value)==""){
					alert("Please enter the "+document.getElementById('reqs.code').innerHTML.toLowerCase());
					document.getElementById('paraFrm_reqName').focus();
					return false;
				}
				
				if(trim(document.getElementById('paraFrm_reqDt').value)==""){
				    alert("Please enter/select the "+document.getElementById('reqs.date').innerHTML.toLowerCase());
				    document.getElementById('paraFrm_reqDt').focus();
				 
					return false;
				}else if(!validateDate("paraFrm_reqDt",'reqs.date')){
				 document.getElementById('paraFrm_reqDt').focus();
				 
					return false;
				
				}	
		    	if(!validateBlank(fieldName,lableName,flag)){
			             return false;
			     }else if(!chkDupVac()){
						return false;
					
				}else if(!chkDate()){
						return false;
					
				}else if(jobN.length >1000){
				       alert("Maximum length of "+document.getElementById('job.name').innerHTML.toLowerCase()+" is 1000 characters.");
				       return false;
			    }else if(jobD.length >1000){
				       alert("Maximum length of "+document.getElementById('job.desc').innerHTML.toLowerCase()+" is 1000 characters.");
				       return false;
			
			    }else if(jobRoleRes.length >1000){
				      alert("Maximum length of "+document.getElementById('roles.res').innerHTML.toLowerCase()+" is 1000 characters.");
				      return false;
			
			   }else if(splReq.length >1000){
				      alert("Maximum length of "+document.getElementById('specialreq').innerHTML.toLowerCase()+" is 1000 characters.");
				      return false;
			
			   }else if(pers.length >1000){
				      alert("Maximum length of "+document.getElementById('persreq').innerHTML.toLowerCase()+" is 1000 characters.");
				      return false;
			
			  }else if(!checkDate()){
			   	return false;
			  }
			  if(!valCTC('paraFrm_reqCompensation','comp'))
				return false;
			 
	   	    if(!valExp('paraFrm_maxExp','maxexp')){
			  return false;
			}else if(!valExp('paraFrm_minExp','minexp')){
			  return false;
			}else if(eval(min)>eval(max)){
				alert(document.getElementById('maxexp').innerHTML+" experience should be greater than "+document.getElementById('minexp').innerHTML.toLowerCase()+" experience");
				return false;
			
		  	}	 else{
						document.getElementById("paraFrm").action="EmployeeRequi_nextPage.action";
				    	document.getElementById("paraFrm").submit();
		    	}
	  }
	  return false;
   }
	
function cancelFun(){
		document.getElementById('paraFrm').target = "_self";
		if(document.getElementById("paraFrm_cancelThrd").value=="true") {
	 		//this is for mypage back button
			if(document.getElementById('source').value=='mymessages') {
				document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
			} else if(document.getElementById('source').value=='myservices') {
				document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_serviceData.action';
			} else {
	  			document.getElementById("paraFrm").action="EmployeeRequi_cancelThird.action";
	  		}
	    	document.getElementById("paraFrm").submit();
		} else {
			if(document.getElementById('source').value=='mymessages') {
				document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
			} else if(document.getElementById('source').value=='myservices') {
				document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_serviceData.action';
			} else {
				document.getElementById("paraFrm").action="EmployeeRequi_cancelFirst.action";
			}
	    	document.getElementById("paraFrm").submit();
	    }
}
	
	function searchFun(){
		callsF9(500,325,'EmployeeRequi_f9Search.action');
		//return false;
	}
	
	function editFun(){
	
		document.getElementById("paraFrm").action="EmployeeRequi_edit.action";
	    document.getElementById("paraFrm").submit();
	
	}
/*	
  function callPage(id,status1){
		if(status1=="null" || status1=="" ){		
			status1="B";
		}
		else if(status1=="New Requisition"){
			status1="B";
		}
		else if(status1=="Pending List"){
			status1="P";
		}
		else if(status1=="Approved List"){
			status1="A";
		}
		else if(status1=="Rejected List"){
			status1="R";
		}
		else if(status1=="On Hold List"){
			status1="H";
		}
		if(id=='P'){
		var p=document.getElementById('myPage').value;
		id=eval(p)-1;
		}
		if(id=='N'){
		var p=document.getElementById('myPage').value;
		id=eval(p)+1;
		}
		document.getElementById('myPage').value=id;
		document.getElementById('paraFrm').action='EmployeeRequi_showApplicationList.action?status='+status1;
		document.getElementById('paraFrm').submit();
		
	}
	
	*/
   function callPageText(id,status1){  
  if(status1=="null" || status1=="" ){		
			status1="B";
		}
		else if(status1=="New Requisition"){
			status1="B";
		}
		else if(status1=="Pending List"){
			status1="P";
		}
		else if(status1=="Approved List"){
			status1="A";
		}
		else if(status1=="Rejected List"){
			status1="R";
		}
		else if(status1=="On Hold List"){
			status1="H";
		} 
		else if(status1=="Quick Requisition"){
			status1="Q";
		}
		
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoField').value;	 
		 	totalPage =document.getElementById('totalPage').value;	
		 	var actPage = document.getElementById('myPage').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoField').focus();
		     document.getElementById('pageNoField').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField').focus();
		     document.getElementById('pageNoField').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoField').focus();
		      return false;
	        }
	        
	         document.getElementById('myPage').value=pageNo;
		   
			document.getElementById('paraFrm').action='EmployeeRequi_showApplicationList.action?status='+status1;
			document.getElementById('paraFrm').submit();
		}
		
	}	
	
   function callPage(id,pageImg,status1){  
 	 pageNo =document.getElementById('pageNoField').value;	
 	 totalPage =document.getElementById('totalPage').value;	 
 	 
 	   if(pageNo==""){
	        alert("Please Enter Page Number.");
	        document.getElementById('pageNoField').focus();
			 return false;
	        }
	 if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero.");
		     document.getElementById('pageNoField').focus();
			 return false;
		    }
	  if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField').focus();
			 return false;
		    }
		    	
       if(pageImg=="F")
         {
	        if(pageNo=="1"){
	         alert("This is first page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       }  
       
       	if(pageImg=="L")
         {
	        if(eval(pageNo)==eval(totalPage)){
	         alert("This is last page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       } 
       
        if(pageImg=="P")
       {
	        if(pageNo=="1"){
	         alert("There is no previous page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        }  
       }  
       if(pageImg=="N")
       {
	        if(Number(pageNo)==Number(totalPage)){
	         alert("There is no next page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        }  
       }  
       
       
       
       if(status1=="null" || status1=="" ){		
			status1="B";
		}
		else if(status1=="New Requisition"){
			status1="B";
		}
		else if(status1=="Pending List"){
			status1="P";
		}
		else if(status1=="Approved List"){
			status1="A";
		}
		else if(status1=="Rejected List"){
			status1="R";
		}
		else if(status1=="On Hold List"){
			status1="H";
		}
       else if(status1=="Quick Requisition"){
			status1="Q";
		}
		if(id=='P'){
		var p=document.getElementById('pageNoField').value;
		id=eval(p)-1;
		}
		if(id=='N'){
		var p=document.getElementById('pageNoField').value;
		id=eval(p)+1;
		} 
		document.getElementById('myPage').value=id;
	//	document.getElementById('paraFrm_show').value=id;
		document.getElementById('paraFrm').action='EmployeeRequi_showApplicationList.action?status='+status1;
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
   	if(status =="Pending List"){
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
	  }else {
	  	return "B";
	  
	  }
   }
   
   function newRowColor(cell)
   		 {
		  cell.className='Cell_bg_first';

	    }
	
	function oldRowColor(cell,val) {
	
	cell.className='Cell_bg_second';
		
	}
   
   
    function callForInternal()
	   {
	 	  
	
	   if(document.getElementById('paraFrm_inter').checked)
	   {	  
	  
	      document.getElementById('paraFrm_internal').value="true";
		
	   }
	   else{
	       document.getElementById('paraFrm_internal').value="";
	  
   		}
  }
   function callForExternal()
	   {
	 	  

	   if(document.getElementById('paraFrm_exter').checked)
	   {	  
	    document.getElementById('paraFrm_external').value="true";
	
	   }
	   else{
	   document.getElementById('paraFrm_external').value="";
	   	
   		}
  }
  
  
  function checkDate(){
 
		var tbl = document.getElementById('tblVac');
  		var lastRow = tbl.rows.length;
  		var req=document.getElementById('paraFrm_reqDt').value;
  		
  		for(var i=1;i<lastRow;i++){
  		var vac= document.getElementById('paraFrm_vacDate'+i).value;
  		 		    
  		    	//if(!dateDifferenceEqual(req, vac, 'paraFrm_vacDate'+i, 'reqdate','required.date'))
				//return false;
  		
  		 }

return true;

}	


 function callForEdit(id){
	  	document.getElementById('paraFrm_hiddenCode').value=id;
		document.getElementById("paraFrm").action="EmployeeRequi_callForEdit.action?requisitionCode="+id;
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
   }
   
   	function callJob(){
   		try{
   			if((document.getElementById('paraFrm_reqDt').value)==""){
   				alert("Please enter/select the "+document.getElementById('reqs.date').innerHTML.toLowerCase()+"");
   				document.getElementById('paraFrm_reqDt').focus();
   				return false;
   			}else if(!validateDate("paraFrm_reqDt",'reqs.date')){
				return false;
			}else{
				callsF9(500,325,'EmployeeRequi_f9jobDesc.action');
			}
   		}catch(e){
   			//alert("Exception ocuured >>>>>"+e);
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
   
    function valCTC(ctcfieldname,ctclabelname)
	{
	
	var amount=document.getElementById(ctcfieldname).value;
	var amountlabel=document.getElementById(ctclabelname).innerHTML.toLowerCase();
	
		
	if(trim(amount)!=""){
		if(isNaN(amount)) { 
		  alert("Only one dot is allowed in "+amountlabel+" field.");
		  document.getElementById(ctcfieldname).focus();
		  return false;
		 
		 }	
		 }
		  return true;
		 }
		 
  function valExp(ctcfieldname,ctclabelname)
	{
	
	var amount=document.getElementById(ctcfieldname).value;
	var amountlabel=document.getElementById(ctclabelname).innerHTML.toLowerCase();
	
		
	if(trim(amount)!=""){
		if(isNaN(amount)) { 
		  alert("Only one dot is allowed in "+amountlabel+" experience field.");
		  document.getElementById(ctcfieldname).focus();
		  return false;
		 
		 }	
		 }
		  return true;
		 }		 


// For converting the days into date and compare it accordingly........
		 
function check()
{
	var tbl = document.getElementById('tblVac');
	var lastRow = tbl.rows.length;
	if(lastRow<2){
alert("Please add at least one row value in vacancy details.");
return false;
}
	
	for(var i=1;i<lastRow;i++)
	{
		try
		{			
			var requisitionDate = document.getElementById("paraFrm_reqDt").value;			
			var vacDate= document.getElementById('paraFrm_vacDate'+i).value;			
			var minimumReuiredDays = document.getElementById("paraFrm_minReuiredDays").value;					
			var myReqDate=requisitionDate.split("-"); //split the date by '/'			
			var myDate=new Date(myReqDate[2], myReqDate[1]-1, myReqDate[0]); //create a new date object			
			myDate.setDate(myDate.getDate()+eval(minimumReuiredDays));

			var vFormat="dd-MM-yyyy";
			var vDay = addZero(myDate.getDate()); 
			var vMonth = addZero(myDate.getMonth()+1); 
			var vYearLong = addZero(myDate.getFullYear()); 
			var vYearShort = addZero(myDate.getFullYear().toString().substring(3,4)); 
			var vYear = (vFormat.indexOf("yyyy")>-1?vYearLong:vYearShort) 
			var vHour = addZero(myDate.getHours()); 
			var vMinute = addZero(myDate.getMinutes()); 
			var vSecond = addZero(myDate.getSeconds()); 
			var myDateString = vFormat.replace(/dd/g, vDay).replace(/MM/g, vMonth).replace(/y{1,4}/g, vYear) 
			myDateString = myDateString.replace(/hh/g, vHour).replace(/mm/g, vMinute).replace(/ss/g, vSecond)			
			
			//if(dateDifferenceEqual(myDateString,vacDate,'paraFrm_vacDate'+i,'reqs.date','required.date'))
			
			if(dateDifferenceEqual(requisitionDate,vacDate,'paraFrm_vacDate'+i,'reqs.date','required.date'))
			{			
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(e)
		{
			alert("Error Occurred ==== "+e);
			return false;
		}
	}
	return true;
  }
  
  
  function addZero(vNumber){ 	
    return ((vNumber < 10) ? "0" : "") + vNumber 
  }
  
  function addJobDesc(){ 
		  	var strJbDescName=document.getElementById("paraFrm_jdDescName").value;
		  	
		 	var jdCode=document.getElementById("paraFrm_jdCode").value;
		  	
		  	
		  	var strJbDesc=document.getElementById("paraFrm_jdDesc").value;
		  	var strRoleDesc=document.getElementById("paraFrm_jdRoleDesc").value
		  	var strSpecialReq=document.getElementById("paraFrm_specialReq").value
		  	var strPersoanlReq=document.getElementById("paraFrm_persoanlReq").value
		  	 var fieldName = ["paraFrm_jdDescName","paraFrm_jdDesc","paraFrm_jdRoleDesc"];
			 var lableName = ["job.name","job.desc","roles.res"];
			 var flag = ["enter/select","enter/select","enter/select"];
			 if(!validateBlank(fieldName,lableName,flag)){
			      return false;
			 }
			if(!strJbDesc == ''){
				 	if(strJbDesc.length>1000){
				 		alert("Maximum length of  job Description is 1000 characters");
				 		return false;
				 	}
			 }
			if(!strRoleDesc==''){
				 	if(strRoleDesc.length>1000){
					 		alert("Maximum length of  Role Description is 1000 characters");
					 		return false;
				 	}	
			}
			if(!strSpecialReq==''){
				 	if(strSpecialReq.length>1000){
					 		alert("Maximum length of  Special Requirement is 1000 characters");
					 		return false;
				 	}
			}
			if(!strPersoanlReq==''){
				 	if(strPersoanlReq.length>1000){
					 		alert("Maximum length of Personal Qualities is 1000 characters");
					 		return false;
				 	}
			}	
			 conf=confirm("Do you really want to add data into master?");
			 if(conf){
				try{
					   addToMaster('<%=request.getContextPath()%>/recruit/EmployeeRequi_addDataToMaster.action?',strJbDescName,strJbDesc,strRoleDesc,strSpecialReq,strPersoanlReq,jdCode);
				}
				catch(e){
					alert("Error Occurred    "+e);
					return false;
				}
				 alert("Data added to Job Description Master successfully");
			 }
			
			 return true;
 } 
  		 
   function callLength(type){ 
 		if(type=='isJbDesc'){
			var cmt =document.getElementById('paraFrm_jdDesc').value;
			if(eval(cmt.length)>1000){
				document.getElementById('paraFrm_jdDesc').style.background = '#FFFF99';
			}else{ 
				document.getElementById('paraFrm_jdDesc').style.background = '#FFFFFF';
			}
		}
		if(type=='isRoleDesc'){
			var cmt =document.getElementById('paraFrm_jdRoleDesc').value;
			if(eval(cmt.length)>1000){
				document.getElementById('paraFrm_jdRoleDesc').style.background = '#FFFF99';
			}else{ 
				document.getElementById('paraFrm_jdRoleDesc').style.background = '#FFFFFF';
			}
		}
		if(type=='isSpecialReq'){
			var cmt =document.getElementById('paraFrm_specialReq').value;
			if(eval(cmt.length)>1000){
				document.getElementById('paraFrm_specialReq').style.background = '#FFFF99';
			}else{ 
				document.getElementById('paraFrm_specialReq').style.background = '#FFFFFF';
			}
		}
		if(type=='isPersoanlReq'){
			var cmt =document.getElementById('paraFrm_persoanlReq').value;
			if(eval(cmt.length)>1000){
				document.getElementById('paraFrm_persoanlReq').style.background = '#FFFF99';
			}else{ 
				document.getElementById('paraFrm_persoanlReq').style.background = '#FFFFFF';
			}
		}
		
		
 	}
    
    
    
    
     function callAddApprover(){
     ///  alert("in callAddApprover");
 
  var empId = document.getElementById('paraFrm_employeeId').value;
    	///alert("empId ======"+ empId)
     if(empId=="")
  {
     alert("Please Select Keep Informed To");
          return false;
  }
  
     return true;
  
   }
    
    
    
    function getKeepInformedEmp()
	{
	try
	{
	 
	/* var empcode=document.getElementById('paraFrm_hiringcode').value;
	 ///	var emp =document.getElementById('paraFrm_employeeId').value;
			
			alert("empcode         " + empcode);
			
			 if(empcode==""){
				alert("Please select "+document.getElementById('hiring.mgr').innerHTML.toLowerCase());
				return false;
			}*/
	
			callsF9(500,325,'EmployeeRequi_f9KeepInformedEmployee.action');
		 	
	}
	catch(e)
	{
		alert(e);
		} 
	
	}
   
   
    function callKeepInformed()
	{
		 var empcode=document.getElementById('paraFrm_hiringcode').value;
		 var emp =document.getElementById('paraFrm_employeeId').value;
		 if(empcode==""){
			 alert("Please select "+document.getElementById('hiring.mgr').innerHTML.toLowerCase());
		 return false;
			 }
			if(emp=="")
			{
			alert("Please select Keep Informed To ");
				return false;
			}
	
		return true;
	}
	
	
	 function callDeleteApprover(deleteId)
  {
  ///alert(deleteId);
        var conf=confirm("Are you sure to delete this record?");
   if(conf) {
   		
   		document.getElementById("hiddenDeleteId").value=deleteId;
   	   	document.getElementById("paraFrm").action="EmployeeRequi_deleteKeepInfoTo.action";
    	document.getElementById("paraFrm").submit();
              } 
   }
    function callForRemove(id) {
    var conf=confirm("Are you sure !\n You want to Remove this record ?");
  				if(conf){
					  		document.getElementById('paraFrm_checkRemove').value=id;
					  		document.getElementById('paraFrm').target="_self";
					  		 document.getElementById("paraFrm").action="EmployeeRequi_removeKeepInformed.action";
		  					document.getElementById("paraFrm").submit();
		  				}	
    return true;
    }
    
  </script>
