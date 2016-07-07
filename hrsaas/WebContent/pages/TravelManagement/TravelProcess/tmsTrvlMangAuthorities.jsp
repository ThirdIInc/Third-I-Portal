
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<s:form action="TrvlMangAuthorities_input" validate="true" id="paraFrm"
	validate="true" theme="simple">


	<s:hidden name="employeeCode" />
	<s:hidden name="employeeToken" />


	<s:hidden name="accountantemployeeCode" />
	<s:hidden name="accountantemployeeToken" />

	<s:hidden name="panelFlag" />
	<s:hidden name="retrnFlag" />
	<s:hidden name="cancelFlg" />
	<s:hidden name="myHidden" />
	<s:hidden name="subTabLength" />
	<s:hidden name="testBranchCode" />
	<s:hidden name="myHiddenEmpCode" />
	<s:hidden name="dataFlag" />


	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="2" class="formbg">

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Travel
					Management Authorities </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="22%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<s:hidden name="itBranchCode" />
		<s:hidden name="hiddenEmpId" />
		<s:hidden name="hidTabEmpId" />
		<s:hidden name="mangAuthCode" />
		<s:hidden name="hidAppFlag" />
		<s:hidden name="hiddenAppFlag" />
		<s:hidden name="mainSchdlrCode" />
		<s:hidden name="mainSchdlrToken" />
		<s:hidden name="altMainSchdlrCode" />
		<s:hidden name="altMainSchdlrToken" />
		<s:hidden name="schdlrApprCode" />
		<s:hidden name="schdlrApprToken" />
		<s:hidden name="accntCode" />
		<s:hidden name="accntToken" />
		<s:hidden name="itAltSchdlrCode" />
		<s:hidden name="itSchdlrCode" />
		<s:hidden name="itSchdlrApprCode" />
		<s:hidden name="itAccntCode" />
		<!-- ADDED BY REEBA -->
		<s:hidden name="alterAccountantCode" />
		<s:hidden name="alterAccountantToken" />
		<s:hidden name="itAltAccntCode" />
		<s:hidden name="escalationEmployeeCode" />
		<s:hidden name="escalationEmployeeToken" />
		<s:hidden name="itEscalationEmployeeCode" />
		<s:hidden name="descriptionNew"></s:hidden>
		<s:hidden name="hiddenclaimwrkflowflag" />
		<s:hidden name="hiddenackwrkflowflag" />
		


		<tr>
			<td colspan="3">
			<div id="branchDiv"
				style='position: absolute; z-index: 3; width: 300px; display: none; border: 2px solid; top: 70px; left: 200px; padding: 10px'
				class="formbg">
			<table width="100%" border="0" cellpadding="10" cellspacing="1">
				<tr>
					<td width="35%" colspan="3" align="center">The Travel
					Management Authorities of some branches has been defined.<br>
					Do you really want to overWrite ?</br>
					</td>
				</tr>

				<tr>
					<td colspan="3" align="center"><input type="button"
						class="token" value="  Yes  " style="cursor: pointer;"
						onclick="applyAllBranch('true')"> <input type="button"
						class="token" value="   No  " style="cursor: pointer;"
						onclick="applyOneBranch('false')"> <input type="button"
						class="cancel" value=" Cancel" style="cursor: pointer;"
						onclick="hideDiv();"></td>
				</tr>

			</table>
			</div>
			</td>
		</tr>


		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>

					<td colspan="4">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2">

						<tr>
							<td width="20%" 0 class="formtext" colspan="1"><label
								class="set" id="all.branch" name="all.branch"
								ondblclick="callShowDiv(this);"><%=label.get("all.branch")%></label>
							:</td>
							<td width="80%" colspan="3"><input type="checkbox"
								name="appFlag" <s:property value="appFlag"/> id="appFlag"
								onclick="return callapp();" /></td>

						</tr>

						<tr>
							<td valign="top" height="22" class="formtext" colspan="1"
								width="20%"><label class="set" id="branch.name"
								name="branch.name" ondblclick="callShowDiv(this);"><%=label.get("branch.name")%></label><font
								color="red">*</font>:</td>
							<s:hidden name="branchCode" />
							<td width="80%" height="22" colspan="3"><s:textfield
								name="branch" size="32" theme="simple" readonly="true" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'TrvlMangAuthorities_f9Branch.action');"></td>

						</tr>

						<tr>
							<td valign="top" height="22" class="formtext" colspan="1"
								width="20%"><label class="set" id="mainSch.name"
								name="mainSch.name" ondblclick="callShowDiv(this);"><%=label.get("mainSch.name")%></label><font
								color="red">*</font>:</td>

							<td width="30%" height="22" colspan="1"><s:textfield
								name="mainSchdlr" size="32" theme="simple" readonly="true" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'TrvlMangAuthorities_f9MainSchdlr.action'); "></td>

							<!-- ADDED BY REEBA -->
							<td valign="top" height="22" class="formtext" colspan="2"
								width="50%"><input type="button"
								name="Handover to Alternate Scheduler"
								value="Handover to Alternate Scheduler" class="token"
								onclick="handover('S');" /></td>

						</tr>


						<tr>
							<td valign="top" height="22" class="formtext" colspan="1"
								width="20%"><label class="set" id="alt.mainSch"
								name="alt.mainSch" ondblclick="callShowDiv(this);"><%=label.get("alt.mainSch")%></label>
							:</td>

							<td width="80%" height="22" colspan="3"><s:textfield
								name="altMainSchdlr" size="32" theme="simple" readonly="true" />
							<img src="../pages/images/recruitment/search2.gif"
								class="iconImage" height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'TrvlMangAuthorities_f9AltMainSchdlr.action'); "></td>

						</tr>


						<tr>
							<td valign="top" height="22" class="formtext" 
								width="20%"><label class="set" id="sch.appr"
								name="sch.appr" ondblclick="callShowDiv(this);"><%=label.get("sch.appr")%></label>
							:</td>

							<td width="30%" height="22" nowrap="nowrap"><s:textfield
								name="schdlrAppr" size="32" theme="simple" readonly="true" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'TrvlMangAuthorities_f9SchdlrAppr.action');"></td>
							<td valign="top" height="22" class="formtext" 
								width="30%"><label class="set" id="workflow"
								name="workflow" ondblclick="callShowDiv(this);"><%=label.get("workflow")%></label>
							:</td>

							<td width="20%" height="22" ><!--<s:select
								theme="simple" name="claimwrkflowflag" disabled="false" 
								list="#{'Y':'Yes','N':'No'}" />-->
								<input type="checkbox"
								name="claimwrkflowflag" <s:property value="claimwrkflowflag"/> id="claimwrkflowflag"
								onclick="return callackworkflow();" />
								
								</td>
							
						</tr>


						<!--  	<tr>
							<td width="20%" height="22" class="formtext" colspan="1"><label
								class="set" id="desc" name="desc"
								ondblclick="callShowDiv(this);"><%=label.get("desc")%></label> :</td>
							<td height="22" colspan="2" width="30%"><s:textarea
								name="descriptionNew" cols="33" rows="4" readonly="false"
								onkeypress="return allCharacters();"
								onkeyup="callLength('descriptionNew','descCnt','2000');" /></td>


							<td height="22" valign="bottom" colspan="1" width="50%"><img
								src="../pages/images/zoomin.gif" height="12" align="absmiddle"
								width="12" theme="simple"
								onclick="javascript:callWindow('paraFrm_descriptionNew','desc','','paraFrm_descCnt','2000');">
							&nbsp;Remaining chars<s:textfield name="descCnt" readonly="true"
								size="5"></s:textfield></td>

						</tr>
				-->


						<tr>
							<td height="22" class="formtext"  width="20%"><label
								class="set" id="travel.sts" name="travel.sts"
								ondblclick="callShowDiv(this);"><%=label.get("travel.sts")%></label>
							:</td>
							<td height="22"  width="30%"><s:select
								theme="simple" name="status" disabled="false" value="%{status}"
								list="#{'A':'Active','D':'Deactive'}" /></td>
							<s:hidden name="hidStatus" />
							<td valign="top" height="22" class="formtext" 
								width="30%"><label class="set" id="ackworkflow"
								name="ackworkflow" ondblclick="callShowDiv(this);"><%=label.get("ackworkflow")%></label>
							:</td>

							<td width="20%" height="22" >
							<!--<s:select
								theme="simple" name="ackwrkflowflag" disabled="false" 
								list="#{'Y':'Yes','N':'No'}" />-->
								<input type="checkbox"
								name="ackwrkflowflag" <s:property value="ackwrkflowflag"/> id="ackwrkflowflag"
								onclick="return calladminworkflow();" />
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

					<td colspan="4">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2">

						<tr class="td_bottom_border">
							<td height="15" class="formhead" nowrap="nowrap"><strong
								class="forminnerhead">Add Sub-Scheduler </strong></td>
						</tr>

						<tr>
							<td valign="top" height="22" class="formtext" colspan="1"
								width="20%"><label class="set" id="emp1" name="emp"
								ondblclick="callShowDiv(this);"><%=label.get("emp")%></label><font
								color="red">*</font>:</td>
							<td width="30%" height="22" colspan="2"><s:textfield
								name="employee" size="35" theme="simple" readonly="true" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'TrvlMangAuthorities_f9employee.action');"></td>
							<td width="50%" height="22" colspan="1"><input type="button"
								class="add" value="  Add " onclick="return add();" /></td>

						</tr>


					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>





		<tr>
			<td colspan="3">
			<%
			try {
			%>
			<table width="100%" border="0" cellpadding="0" cellspacing="1"
				class="formbg" class="sortable">

				<tr>

					<td class="formtext">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="sortable">
						<tr class="td_bottom_border">
							<td height="15" class="formhead" nowrap="nowrap"><strong
								class="forminnerhead">Sub-Scheduler List</strong></td>
						</tr>
						<tr>

							<td class="formth" align="center" width="5%"><strong><label
								class="set" id="sr.no3" name="sr.no"
								ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></strong></td>
							<td class="formth" align="center" width="15%"><strong><label
								class="set" id="emp.id3" name="emp.id"
								ondblclick="callShowDiv(this);"><%=label.get("emp.id")%></label></strong></td>

							<td class="formth" align="center" width="15%"><strong><label
								class="set" id="emp3" name="emp" ondblclick="callShowDiv(this);"><%=label.get("emp")%></label></strong></td>

							<td align="right" class="formth" nowrap="nowrap" width="15%">
							<input type="button" class="delete" theme="simple"
								value=" Remove " onclick=" return chkDeleteSub();" /></td>


							<%
							int count = 0;
							%>
							<%!int d = 0;%>
							<%							
							int i = 0,
							  j=0;
							%>




							<s:iterator value="subSchdlrList">
								<tr>


									<td align="left" class="sortableTD" nowrap="nowrap"><s:hidden
										name="srNo" /><s:property value="srNo" /> <%
									++i;
									%>
									</td>




									<td align="left" class="sortableTD"><input type="hidden"
										name="itEmployeeToken"
										value="<s:property
										value="itEmployeeToken" />"
										id="itEmployeeToken<%=i %>" /> <s:property
										value="itEmployeeToken" /><input type="hidden"
										name="itEmployeeCode"
										value="<s:property value="itEmployeeCode" />"
										id="itEmployeeCode<%=i%>" /></td>

									<td align="left" class="sortableTD"><input type="hidden"
										name="itEmployee" id="itEmployee<%=i %>"
										value="<s:property value="itEmployee" />" /> <s:property
										value="itEmployee" /></td>

									<input type="hidden" name="hdeleteSub" id="hdeleteSub<%=i%>" />
									<input type="hidden" name="hidDeleteEmpId"
										id="hidDeleteEmpId<%=i%>" />

									<td align="center" nowrap="nowrap" class="sortableTD"><input
										type="checkbox" class="checkbox" id="confChk<%=i%>"
										name="confChk"
										onclick="callForDeleteSub(<%=i%>,'<s:property value="itEmployeeCode" />')" /></td>
								</tr>

							</s:iterator>
							<%
							d = i;
							j++;
							%>
						</tr>



					</table>
					<table></table>

					<%
						} catch (Exception e) {
						}
					%>
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

					<td colspan="4">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2">

						<tr>
							<td valign="top" height="22" class="formtext" colspan="1"
								width="20%"><label class="set" id="accnt.name"
								name="accnt.name" ondblclick="callShowDiv(this);"><%=label.get("accnt.name")%></label>
							:</td>

							<td width="30%" height="22" colspan="1"><s:textfield
								name="accnt" size="32" theme="simple" readonly="true" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'TrvlMangAuthorities_f9Accnt.action');"></td>

							<!-- ADDED BY REEBA -->
							<td valign="top" height="22" class="formtext" colspan="2"
								width="50%"><input type="button"
								name="Handover to Alternate Accountant"
								value="Handover to Alternate Accountant" class="token"
								onclick="handover('A');" /></td>

						</tr>


						<!-- ADDED BY REEBA BEGINS -->
						<tr>
							<td valign="top" height="22" class="formtext" colspan="1"
								width="20%"><label class="set" id="alt.accnt"
								name="alt.accnt" ondblclick="callShowDiv(this);"><%=label.get("alt.accnt")%></label>
							:</td>

							<td width="80%" height="22" colspan="3"><s:textfield
								name="alterAccountant" size="32" theme="simple" readonly="true" />
							<img src="../pages/images/recruitment/search2.gif"
								class="iconImage" height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'TrvlMangAuthorities_f9AlterAccountant.action'); "></td>

						</tr>

						<tr>
							<td valign="top" height="22" class="formtext" colspan="1"
								width="20%"><label class="set" id="accountant.emailID"
								name="accountant.emailID" ondblclick="callShowDiv(this);"><%=label.get("accountant.emailID")%></label>
							:</td>

							<td width="80%" height="22" colspan="3"><s:textfield
								name="accountantEmailID" size="32" theme="simple" /></td>
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

					<td colspan="4">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2">

						<tr class="td_bottom_border">
							<td height="15" class="formhead" nowrap="nowrap"><strong
								class="forminnerhead">Add Sub-Accountant </strong></td>
						</tr>

						<tr>
							<td valign="top" height="22" class="formtext" colspan="1"
								width="20%"><label class="set" id="emp4" name="emp"
								ondblclick="callShowDiv(this);"><%=label.get("emp")%></label><font
								color="red">*</font>:</td>
							<td width="30%" height="22" colspan="2"><s:textfield
								name="accountantemployee" size="35" theme="simple"
								readonly="true" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'TrvlMangAuthorities_f9Accountantemployee.action');"></td>
							<td width="50%" height="22" colspan="1"><input type="button"
								class="add" value="  Add " onclick="return addAccountant();" /></td>

						</tr>


					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<%
			try {
			%>
			<table width="100%" border="0" cellpadding="0" cellspacing="1"
				class="formbg" class="sortable">

				<tr>

					<td class="formtext">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="sortable">
						<tr class="td_bottom_border">
							<td height="15" class="formhead" nowrap="nowrap"><strong
								class="forminnerhead">Sub-Accountant List</strong></td>
						</tr>
						<tr>
							<s:hidden name="myPage" id="myPage" />
							<td class="formth" align="center" width="5%"><strong><label
								class="set" id="sr.no4" name="sr.no"
								ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></strong></td>
							<td class="formth" align="center" width="15%"><strong><label
								class="set" id="emp.id4" name="emp.id"
								ondblclick="callShowDiv(this);"><%=label.get("emp.id")%></label></strong></td>

							<td class="formth" align="center" width="15%"><strong><label
								class="set" id="emp4" name="emp" ondblclick="callShowDiv(this);"><%=label.get("emp")%></label></strong></td>


							<td class="formth" align="center" width="15%"><strong><label
								class="set" id="travel3" name="travel"
								ondblclick="callShowDiv(this);"><%=label.get("travel")%></label></strong></td>

							<td class="formth" align="center" width="15%"><strong><label
								class="set" id="local.conv3" name="local.conv"
								ondblclick="callShowDiv(this);"><%=label.get("local.conv")%></label></strong></td>

							<td class="formth" align="center" width="15%"><strong><label
								class="set" id="lodge" name="lodge"
								ondblclick="callShowDiv(this);"><%=label.get("lodge")%></label></strong></td>


							<td align="right" class="formth" nowrap="nowrap" width="15%">
							<input type="button" class="delete" theme="simple"
								value=" Remove " onclick=" return chkDeleteAcc();" /></td>


							<%
							int count1 = 0;
							%>
							<%!int d1 = 0;%>
							<%							
							int i1 = 0,
							  j1=0;
							%>




							<s:iterator value="subAccountantList">
								<tr>


									<td align="left" class="sortableTD" nowrap="nowrap"><s:hidden
										name="srNoAcc" /><s:property value="srNoAcc" /> <%
									++i1;
									%>
									</td>




									<td align="left" class="sortableTD"><input type="hidden"
										name="itEmployeeTokenAcc"
										value="<s:property
										value="itEmployeeTokenAcc" />"
										id="itEmployeeTokenAcc<%=i1 %>" /> <s:property
										value="itEmployeeTokenAcc" /> <input type="hidden"
										name="itEmployeeCodeAcc"
										value="<s:property value="itEmployeeCodeAcc" />"
										id="itEmployeeCodeAcc<%=i1%>" /></td>

									<td align="left" class="sortableTD"><input type="hidden"
										name="itEmployeeAcc" id="itEmployeeAcc<%=i1 %>"
										value="<s:property value="itEmployeeAcc" />" /> <s:property
										value="itEmployeeAcc" /> <!-- <input
										type="hidden" class="checkbox" id="itTravel<%=i1%>" <s:property value="itTravel"/>
										name="itTravel"  onclick="chkTrvl();"/>
										<input type="text" name="hidItTravel" id="hidItTravel<%=i1%>" value="<s:property value="hidItTravel" />"/>
									
									<input
										type="hidden" class="checkbox" id="itLocalConv<%=i1%>" <s:property value="itLocalConv"/>
										name="itLocalConv" onclick="chkConv();"/>
										<input type="hidden" name="hidItLocalConv" id="hidItLocalConv<%=i1%>" value="<s:property value="hidItLocalConv" />"/>
									
									<input
										type="hidden" class="checkbox" id="itLodging<%=i1%>" <s:property value="itLodging"/>
										name="itLodging" onclick="chkLodge();"/>									
										<input type="hidden" name="hidItLodging" id="hidItLodging<%=i1%>" value="<s:property value="hidItLodging" />"/>
																		
									 --></td>


									<td align="center" nowrap="nowrap" class="sortableTD"><input
										type="checkbox" class="checkbox" id="itAcknoledge<%=i1%>"
										<s:property value="itAcknoledge"/> name="itAcknoledge"
										onclick="chkAcknoledge();" /> <input type="hidden"
										name="hiddenitAcknoledge" id="hiddenitAcknoledge<%=i1%>"
										value="<s:property value="hiddenitAcknoledge" />" /></td>

									<td align="center" nowrap="nowrap" class="sortableTD"><input
										type="checkbox" class="checkbox" id="itAdvance<%=i1%>"
										<s:property value="itAdvance"/> name="itAdvance"
										onclick="chkAdvance();" /> <input type="hidden"
										name="hiddenitAdvance" id="hiddenitAdvance<%=i1%>"
										value="<s:property value="hiddenitAdvance" />" /></td>

									<td align="center" nowrap="nowrap" class="sortableTD"><input
										type="checkbox" class="checkbox" id="ittClaim<%=i1%>"
										<s:property value="ittClaim"/> name="ittClaim"
										onclick="chkClaim();" /> <input type="hidden"
										name="hiddenittClaim" id="hiddenittClaim<%=i1%>"
										value="<s:property value="hiddenittClaim" />" /></td>



									<input type="hidden" name="hdeleteAcc" id="hdeleteAcc<%=i1%>" />

									<input type="hidden" name="hidDeleteEmpIdAcc"
										id="hidDeleteEmpIdAcc<%=i1%>" />

									<td align="center" nowrap="nowrap" class="sortableTD"><input
										type="checkbox" class="checkbox" id="confChkAcc<%=i1%>"
										name="confChkAcc"
										onclick="callForDeleteAccountant(<%=i1%>,'<s:property value="itEmployeeCodeAcc" />')" /></td>
								</tr>

							</s:iterator>
							<%
							d1 = i1;
							j1++;
							%>
						</tr>



					</table>
					<table></table>

					<%
						} catch (Exception e) {
						}
					%>
					</td>
				</tr>
			</table>
			</td>
		</tr>




		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="22%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%"></td>
				</tr>
			</table>
			</td>
		</tr>


	</table>

</s:form>





<script>

onloadfun();

 function applyAllBranch(flag){
document.getElementById('paraFrm').target="main";
document.getElementById('paraFrm').action="TrvlMangAuthorities_save.action?overWrite="+flag;
document.getElementById('paraFrm').submit();
}

function applyOneBranch(flag){
document.getElementById('paraFrm').target="main";
document.getElementById('paraFrm').action="TrvlMangAuthorities_save.action?overWrite="+flag;
document.getElementById('paraFrm').submit();
}



function hideDiv() {
		document.getElementById('branchDiv').style.display = "none";
	}



	function handover(type){
		//alert(type);
		if(type=='S'){
			var main=document.getElementById('paraFrm_mainSchdlrCode').value;
	    	if (main==""){
		   		alert("Please select "+document.getElementById('mainSch.name').innerHTML.toLowerCase());
		   		return false;
	  		}
			
			var alterMain=document.getElementById('paraFrm_altMainSchdlrCode').value;
	    	if (alterMain==""){
		   		alert("Please select "+document.getElementById('alt.mainSch').innerHTML.toLowerCase());
		   		return false;
	  		}
	  		document.getElementById('paraFrm').target="main";
	     	document.getElementById("paraFrm").action="TrvlMangAuthorities_handoverToAlternateScheduler.action";
		    document.getElementById("paraFrm").submit();   
		}
		
		if(type=='A'){
			var acct=document.getElementById('paraFrm_accntCode').value;
	    	if (acct==""){
		   		alert("Please select "+document.getElementById('accnt.name').innerHTML.toLowerCase());
		   		return false;
	  		}
	  		
			var alterAcct=document.getElementById('paraFrm_alterAccountantCode').value;
	    	if (alterAcct==""){
		   		alert("Please select "+document.getElementById('alt.accnt').innerHTML.toLowerCase());
		   		return false;
	  		}
	  		document.getElementById('paraFrm').target="main";
	     	document.getElementById("paraFrm").action="TrvlMangAuthorities_handoverToAlternateAccountant.action";
		    document.getElementById("paraFrm").submit();   
		}
		
	}


function chkAcknoledge(){
	try{
	 
	var count=<%=d1%>;
	  
	for(var a=1;a<=count;a++)
	{
   		if(document.getElementById('itAcknoledge'+a).checked == true)
   			{
				document.getElementById('hiddenitAcknoledge'+a).value='Y';			
   			}
   			else
   			{
   				document.getElementById('hiddenitAcknoledge'+a).value='N'; 
   			}   		
   		}  
   		
	}catch(e){ 
	}
   		
   				
   	 }
   	 
   	 function chkAdvance(){
   		var count=<%=d1%>;
   		for(var a=1;a<=count;a++){
   	 	if(document.getElementById('itAdvance'+a).checked == true){
			document.getElementById('hiddenitAdvance'+a).value='Y';			
   			}else{
   			document.getElementById('hiddenitAdvance'+a).value='N'; 
   			}   		
   		}   			
   	 }
   	 
   	  function chkClaim(){
   		var count=<%=d1%>;
   		for(var a=1;a<=count;a++){
   	 
   		if(document.getElementById('ittClaim'+a).checked == true){
			document.getElementById('hiddenittClaim'+a).value='Y';			
   			}else{
   			document.getElementById('hiddenittClaim'+a).value='N'; 
   			}   		
   		}   			
   	 }
    

function add(){
	var employee=document.getElementById('paraFrm_employee').value;  
	if(employee==""){
	alert("Please select an "+document.getElementById('emp3').innerHTML.toLowerCase());
	return false;
	}
	document.getElementById("paraFrm").target ="";
	document.getElementById("paraFrm").action="TrvlMangAuthorities_addSubSchdlr.action" ;
	document.getElementById("paraFrm").submit();
}



function addAccountant(){
	var employee=document.getElementById('paraFrm_accountantemployee').value;  
	if(employee==""){
	alert("Please select an "+document.getElementById('emp4').innerHTML.toLowerCase());
	return false;
	}
	document.getElementById("paraFrm").target ="";
	document.getElementById("paraFrm").action="TrvlMangAuthorities_addSubAccountant.action" ;
	document.getElementById("paraFrm").submit();
}

function chkDeleteSub()
		{
		 if(chk2()){
		 var con=confirm('Do you really want to  delete the record ?');
	 	if(con){
	   document.getElementById('paraFrm').action="TrvlMangAuthorities_deleteSub.action";
	    document.getElementById('paraFrm').submit();
	    }
	    else{
	    var flag='<%=d %>';
	  	for(var a=1;a<=flag;a++){	
	   document.getElementById('confChk'+a).checked=false;
	   document.getElementById('confChk'+a).checked="";
	    document.getElementById('hdeleteSub'+a).value="";
	    }
	     return false;
		 }
	 	}
	 	else {
	 	alert('Please select atleast one record to delete.');
	 	 return false;
	 	}
	}
	
	
	function chkDeleteAcc()
		{
		 if(chk3()){
		 var con=confirm('Do you really want to  delete the record ?');
	 	if(con){
	   document.getElementById('paraFrm').action="TrvlMangAuthorities_deleteAccountant.action";
	    document.getElementById('paraFrm').submit();
	    }
	    else{
	    var flag='<%=d %>';
	  	for(var a=1;a<=flag;a++){	
	   document.getElementById('confChkAcc'+a).checked=false;
	   document.getElementById('confChkAcc'+a).checked="";
	    document.getElementById('hdeleteAcc'+a).value="";
	    }
	     return false;
		 }
	 	}
	 	else {
	 	alert('Please select atleast one record to delete.');
	 	 return false;
	 	}
	}
	
	
	function chk2(){
	 	var flag='<%=d %>';
	  	for(var a=1;a<=flag;a++){	
	   if(document.getElementById('confChk'+a).checked == true)
	   {	
	 	    return true;
	   }	   
	  }
	  	return false;
	}
	
	function chk3(){
	 	var flag='<%=d1%>';
	  	for(var a=1;a<=flag;a++){	
	   if(document.getElementById('confChkAcc'+a).checked == true)
	   {	
	 	    return true;
	   }	   
	  }
	  	return false;
	}
	
	function callForDeleteSub(id,EmployeeCode)
	   {
	   //alert("code"+eval(EmployeeCode)); 
	   var i=eval(id)-1;
	   if(document.getElementById('confChk'+id).checked == true)
	   {	  
	   document.getElementById('hdeleteSub'+id).value=eval(id)-1;
	   document.getElementById('hidDeleteEmpId'+id).value=eval(EmployeeCode);
	   }
	   else
	   document.getElementById('hdeleteSub'+id).value="";	   
   	}
   	
   	
   	function callForDeleteAccountant(id,EmployeeCode)
	   {
	 
	   var i=eval(id)-1;
	   if(document.getElementById('confChkAcc'+id).checked == true)
	   {	  
	   document.getElementById('hdeleteAcc'+id).value=eval(id)-1;
	   document.getElementById('hidDeleteEmpIdAcc'+id).value=eval(EmployeeCode);
	   }
	   else
	   document.getElementById('hdeleteAcc'+id).value="";	   
   	}
   	
   	
   	// For Save Button

function saveFun()
{
try{	
	
	var f9Fields= ["paraFrm_branch","paraFrm_mainSchdlr"];
	var fieldName = ["paraFrm_branch","paraFrm_mainSchdlr"];
	var lableName = ["branch.name","mainSch.name"];
	var type = ['select','select'];	
	if(!validateBlank(fieldName, lableName, type))
	{
    	//alert("Inside validate blank");
    	return s;
    }	
    if(!f9specialchars(f9Fields))
    {
		return false;
	}	
	var desc =	document.getElementById('paraFrm_descriptionNew').value;
	var dataFlag =	document.getElementById('paraFrm_dataFlag').value;
	
	if(desc != "" && desc.length >2000) {
		alert("Maximum length of "+document.getElementById('desc').innerHTML.toLowerCase()+" is 2000 characters.");
		return false;
    } 
     
   if(!validateEmail('paraFrm_accountantEmailID'))
   {   
	return false;
	}   
    var hidAppFlag =document.getElementById('paraFrm_hidAppFlag').value;   
   if(hidAppFlag=="Y" && dataFlag=="true"){   
   document.getElementById('branchDiv').style.display = "";   
   return false;
   }   
  
    document.getElementById('paraFrm').target="main";
	document.getElementById('paraFrm').action="TrvlMangAuthorities_save.action?overWrite=";
	document.getElementById('paraFrm').submit();
	}
	catch(e)
	{
	//alert(e);
	}
 
}

//For Cancel Button

	function cancelFun(){
	//alert("cancelFirst");
		document.getElementById('paraFrm').target="main";
     	document.getElementById("paraFrm").action="TrvlMangAuthorities_cancelFirst.action";
	    document.getElementById("paraFrm").submit();   
    
    }
    
    
    function callapp(){
	//alert("callapp");
	if(document.getElementById('appFlag').checked == true)
		{		
		//alert("Y");		
		document.getElementById('paraFrm_hidAppFlag').value='Y'; 
		}else{
		//alert("N");		
		document.getElementById('paraFrm_hidAppFlag').value='N';
		}
	}
	function onloadfun()
	{
		if(document.getElementById('paraFrm_hidAppFlag').value=='Y')
		{
			document.getElementById('appFlag').checked = true;
		}
		if(document.getElementById('paraFrm_hiddenclaimwrkflowflag').value=='Y')
		{
			document.getElementById('claimwrkflowflag').checked = true;
		}
		if(document.getElementById('paraFrm_hiddenackwrkflowflag').value=='Y')
		{
			document.getElementById('ackwrkflowflag').checked = true;
		}
		
	}
	
	function callackworkflow()
	{
		if(document.getElementById('claimwrkflowflag').checked == true)
		{		
		//alert("Y");		
		document.getElementById('paraFrm_hiddenclaimwrkflowflag').value='Y'; 
		}else{
		//alert("N");		
		document.getElementById('paraFrm_hiddenclaimwrkflowflag').value='N';
		}
	}
	function calladminworkflow()
	{
		if(document.getElementById('ackwrkflowflag').checked == true)
		{		
		//alert("Y");		
		document.getElementById('paraFrm_hiddenackwrkflowflag').value='Y'; 
		}else{
		//alert("N");		
		document.getElementById('paraFrm_hiddenackwrkflowflag').value='N';
		}
	}
	
	

</script>
