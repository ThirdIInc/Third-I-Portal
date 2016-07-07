<%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="VacancyPublish" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="reqDt" />
	<s:hidden name="noVac" />
	<s:hidden name="recruitmentInternal" />
	<s:hidden name="recruitmentExternal" />
	<s:hidden name="formAction" id="formAction" />
	<s:hidden name="statusKey" id="statusKey" />
	<s:hidden name="flagVal" id="flagVal" />
	<s:hidden name="flag" id="flag" />
	<s:hidden name="myPage" id="myPage" />
	<s:hidden name="jobPortal" />
	<table width="100%" class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Vacancy
					Publish</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="100%">
					<table width="100%">
						<tr>
							<td width="78%" colspan="2"><!-- 
								<s:submit cssClass="token"
								action="VacancyPublish_publish" theme="simple"
								value="Publish" id="Publish" onclick="return validation();" />
							 --> <input type="button" name="search" class="token"
								value="Publish" id="PublishAbove" onclick="return validation();" />

							<s:if test="cancelFlag">
								<input type="button" name="search" class="cancel" value="Cancel"
									onclick="callManagement()" />
							</s:if> <s:else>
								<input type="button" name="search" class="cancel" value="Cancel"
									onclick="callPublish()" />
							</s:else></td>
							<td width="22%">
							<div align="right"><font color="red">*</font>Indicates
							Required</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td width="100%">
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">
						<!--Table 2-->
						<tr>
							<td width="100%"><strong>Recruitment Details</strong></td>
						</tr>
						<tr>
							<td width="100%"></td>
						</tr>
						<tr>
							<td width="100%">
							<table width="100%">

								<s:hidden name="headerView" />
								<s:if test="headerView">
									<tr>
										<td width="20%" nowrap="nowrap"><label class="set"
											name="reqs.code" id="reqs.code"
											ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label>
										:<font color="red">*</font></td>
										<td width="25%" nowrap="nowrap"><s:textfield
											name="reqName" size="25" readonly="true" /> <s:if
											test="f9Flag">
											<img src="../pages/images/search2.gif" height="16"
												align="absmiddle" width="16" theme="simple"
												onclick="javascript:callsF9(500,325,'VacancyPublish_f9Publish.action');">
										</s:if></td>
										<td width="25%"><label class="set" name="reqs.date"
											id="reqs.date" ondblclick="callShowDiv(this);"><%=label.get("reqs.date")%></label>
										:</td>
										<td width="25%"><s:textfield name="reqDate" size="25"
											readonly="true" /></td>
									</tr>
									<tr>
										<td width="20%"><label class="set" name="position"
											id="Position" ondblclick="callShowDiv(this);"><%=label.get("position")%></label>
										:</td>
										<td width="25%"><s:textfield name="position" size="25"
											onmouseover="return tooltipDisplay(this)" readonly="true" /></td>
										<td width="25%"><label class="set" name="noofvacan"
											id="noofvacan" ondblclick="callShowDiv(this);"><%=label.get("noofvacan")%></label>
										:</td>
										<td width="25%"><s:textfield name="totalVacancy"
											size="25" readonly="true" /></td>
									</tr>
									<tr>
										<td width="20%"><label class="set" name="applied.by"
											id="Applied.By" ondblclick="callShowDiv(this);"><%=label.get("applied.by")%></label>
										:</td>
										<td width="25%"><s:textfield name="appliedBy" size="25"
											readonly="true" /></td>
										<td width="25%"><label class="set" name="required.date"
											id="required.date" ondblclick="callShowDiv(this);"><%=label.get("required.date")%></label>
										:</td>
										<td width="25%"><s:textfield name="requiredDate"
											size="25" readonly="true" /></td>
									</tr>
									<tr>
										<td width="20%"><label class="set" name="hiring.mgr"
											id="hiring.mgr" ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%></label>
										:</td>
										<td width="25%"><s:textfield name="hiringMgr" size="25"
											readonly="true" /></td>
										<td width="25%"></td>
										<td width="25%"></td>
									</tr>
								</s:if>
								<s:else>
									<tr>
										<td width="20%"><label class="set" name="reqs.code"
											id="reqcode1" ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label>
										:</td>
										<td width="25%"><s:property value="reqName" /><s:hidden
											name="reqName" /></td>
										<td width="25%"><label class="set" name="reqs.date"
											id="Requisition.Date" ondblclick="callShowDiv(this);"><%=label.get("reqs.date")%></label>
										:</td>
										<td width="25%"><s:property value="reqDate" /><s:hidden
											name="reqDate" /></td>
									</tr>
									<tr>
										<td width="20%"><label class="set" name="position"
											id="Position1" ondblclick="callShowDiv(this);"><%=label.get("position")%></label>
										:</td>
										<td width="25%"><s:property value="position" /><s:hidden
											name="position" /></td>
										<td width="25%"><label class="set" name="noofvacan"
											id="noofvacan" ondblclick="callShowDiv(this);"><%=label.get("noofvacan")%></label>
										:</td>
										<td width="25%"><s:property value="totalVacancy" /> <s:hidden
											name="totalVacancy" /></td>
									</tr>

									<tr>
										<td width="20%"><label class="set" name="Applied.By"
											id="Applied.By" ondblclick="callShowDiv(this);"><%=label.get("Applied.By")%></label>
										:</td>
										<td width="25%"><s:property value="appliedBy" /><s:hidden
											name="appliedBy" /></td>
										<td width="25%"><label class="set" name="required.date"
											id="Required.Date1" ondblclick="callShowDiv(this);"><%=label.get("required.date")%></label>
										:</td>
										<td width="25%"><s:property value="requiredDate" /><s:hidden
											name="requiredDate" /></td>
									</tr>

									<tr>
										<td width="20%"><label class="set" name="hiring.mgr"
											id="hiring.mgr" ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%></label>
										:</td>
										<td width="25%"><s:property value="hiringMgr" /><s:hidden
											name="hiringMgr" /></td>
										<td width="25%"></td>
										<td width="25%"></td>
									</tr>

								</s:else>
								<tr>
									<td width="20%"><strong>Recruitment Type :</strong></td>
									<td width="25%"><s:checkbox name="intEmployee" />&nbsp;<label
										class="set" name="Internal.Employees" id="Internal.Employees"
										ondblclick="callShowDiv(this);"><%=label.get("Internal.Employees")%></label></td>
									<!--<td width="25%"><s:checkbox name="extEmployee" />&nbsp;<label
										class="set" name="External.Employees" id="External.Employees"
										ondblclick="callShowDiv(this);"><%=label.get("External.Employees")%></label></td>-->

									<td width="25%"></td>
								</tr>

								<!--<tr>
									<td width="20%"><strong>Publish To :</strong></td>
									<td width="25%"><s:checkbox name="copWeb" id="checkMeId" />&nbsp;<label
										class="set" name="coweb" id="coweb"
										ondblclick="callShowDiv(this);"><%=label.get("coweb")%></label></td>
									<td width="25%">
									<s:checkbox name="jobCons" id="checkId"  onclick="chechboxFlag();"/>
									&nbsp;<label
										class="set" name="Job.Consultant" id="Job.Consultant"
										ondblclick="callShowDiv(this);"><%=label.get("Job.Consultant")%></label></td>
								</tr>								
							 
								<tr>
									<td width="20%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</strong></td>
									<td width="25%"><s:checkbox name="refProgram"
										id="checkMeId" />&nbsp;<label class="set"
										name="Referral.Program" id="Referral.Program"
										ondblclick="callShowDiv(this);"><%=label.get("Referral.Program")%></label></td>
								
									<td width="25%"><s:checkbox name="onlinejobPort"   id="onjobportId"/>
									&nbsp;<label class="set"
										name="onjob.port" id="onjob.port"
										ondblclick="callShowDiv(this);"><%=label.get("onjob.port")%></label>
									</td>									
								</tr>							
							 -->
								<!--Start Modified by manish sakpal -->
								<tr>
									<td width="20%"><strong>Publish To :</strong></td>
									<td width="25%"><s:checkbox name="jobCons" id="checkId"
										onclick="chechboxFlag();" /> &nbsp;<label class="set"
										name="Job.Consultant" id="Job.Consultant"
										ondblclick="callShowDiv(this);"><%=label.get("Job.Consultant")%></label>
									</td>

									<td width="25%"><s:checkbox name="refProgram"
										id="checkMeId" onclick="callRefDiv();"/>&nbsp;<label class="set"
										name="Referral.Program" id="Referral.Program"
										ondblclick="callShowDiv(this);"><%=label.get("Referral.Program")%></label>
									</td>

									<td width="25%"><s:checkbox name="onlinejobPort"
										id="onjobportId" /> &nbsp;<label class="set" name="onjob.port"
										id="onjob.port" ondblclick="callShowDiv(this);"><%=label.get("onjob.port")%></label>
									</td>
								</tr>
								<tr id="divShow">
									<td width="20%"><label class="set" name="refDivision"
										id="refDivision" ondblclick="callShowDiv(this);"><%=label.get("refDivision")%></label>:</td>
									<td width="35%"><s:hidden name="divCode" theme="simple" /><s:textarea
										name="divsion" theme="simple" readonly="true" rows="1"
										cols="50"></s:textarea></td>
									<td width="30%"><img src="../pages/images/search2.gif"
										height="16" align="absmiddle" width="16" theme="simple"
										onclick="javascript:callDropdown('paraFrm_divsion',350,250,'VacancyPublish_f9MultiDivision.action',event,'false','no','right')"></td>

								</tr>
								<!--End Modified by manish sakpal -->
							</table>
							</td>
						</tr>

					</table>
					</td>
				</tr>

				<tr>
					<td width="100%"><img
						src="../pages/images/recruitment/space.gif" width="5" height="7" /></td>
				</tr>
				<tr>
					<!-- Start of recruiter list -->
					<td width="100%">
					<table width="50%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">
						<tr>
							<td nowrap="nowrap"><strong class="formhead">Recruiter
							Assignment : </strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" class="add" value="  Add Row"
								onclick="addRowToTableSkill();" /><input type="button"
								class="delete" value="  Remove" onclick="return deleteSkill();" /></td>
						</tr>
						<tr class="sortable">
							<td width="100%">
							<%
									try {
									%>

							<table width="100%" border="0" id="tblRecruiter">
								<tr>
									<td width="10%" valign="top" class="formth" nowrap="nowrap"><b><label
										class="set" name="serial.no" id="serial.no"
										ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b></td>
									<td width="40%" valign="top" class="formth" align="center"><b><label
										class="set" name="Recruiter.Name" id="Recruiter.Name"
										ondblclick="callShowDiv(this);"><%=label.get("Recruiter.Name")%></label></b><font
										color="red">*</font></td>
									<td width="5%" valign="top" class="formth" align="center">&nbsp;</td>
									<td width="30%" valign="top" class="formth" align="center"><b><label
										class="set" name="asgvacan" id="asgvacan"
										ondblclick="callShowDiv(this);"><%=label.get("asgvacan")%></label></b><font
										color="red">*</font></td>
									<td width="5%" valign="top" class="formth" align="center">
									<input type="checkbox" name="chkSkillAll" id="chkSkillAll"
										onclick="callForRecruiterAll();" size="2" /></td>
									<!--
								<td width="0"></td><td width="0"></td>
						-->
								</tr>
								<%!int s = 0;%>

								<%
										int j = 0;
										%>

								<s:if test="recruiterListFlag">
									<s:iterator value="recruiterList">

										<tr class="border2">
											<td width="10%" class="sortableTD"><%=++j%><s:hidden
												name="hssrlNo" /></td>
											<!--  <td width="15%" class="border2" align="right"><s:select name="skillType" id='<%="skillType"+j %>'
			   							cssStyle="width:75" theme="simple" list="#{'':'Select','E':'Essential','D':'Desirable'}" /></td> -->


											<%
															String si = (String) request
															.getAttribute("paraFrm_skillId" + j);
															String sn = (String) request.getAttribute("paraFrm_skillName"
															+ j);
													%>
											<td nowrap="nowrap" width="40%" class="sortableTD"><s:textfield
												name='<%="paraFrm_skillName"+j%>'
												id='<%="paraFrm_skillName"+j%>' value="<%=sn%>"
												readonly="true" size="20" /></td>
											<td width="5%" class="sortableTD"><img
												src="../pages/images/search2.gif" height="16"
												align="absmiddle" width="16" theme="simple"
												onclick="callSkill('<s:property value='<%=""+j%>'/>')">
											</td>

											<td width="30%" class="sortableTD"><input type="text"
												name="skillExp" maxlength="5"
												onkeypress="return numbersOnly();"
												id="paraFrm_skillExp<%=j%>"
												value='<s:property value="skillExp" />' size="10" /></td>
											<!-- <td width="12%" class="border2" align="center"><s:select name="skillSel" cssStyle="width:65" theme="simple" list="#{'A':'And','R':'Or'}" /></td> -->

											<input type="hidden" name="<%="hdeleteSkill"+j%>"
												id="hdeleteSkill<%=j%>" />
											<td width="15%" class="sortableTD" align="center"><input
												type="checkbox" value="N" name=<%="confChkSkill"+j%>
												id="confChkSkill<%=j%>" onclick="callForSkill('<%=j%>')" />
											<input type="hidden" name="<%="hdeleteSkill"+j%>"
												id="hdeleteSkill<%=j%>" /> <s:hidden
												name='<%="paraFrm_skillId"+j%>'
												id='<%="paraFrm_skillId"+j%>' value="<%=si%>" /></td>
											<!--<td width="0%" ></td>
													<td width="0%" ></td>
															-->
										</tr>


									</s:iterator>


								</s:if>
								<s:else>


									<tr class="border2">
										<td width="10%" class="sortableTD" class="sortableTD"><%=++j%></td>
										<!--  <td width="15%" class="border2" align="right"><s:select name="skillType" id='<%="skillType"+j %>'
			   							cssStyle="width:75" theme="simple" list="#{'':'Select','E':'Essential','D':'Desirable'}" /></td> -->


										<td nowrap="nowrap" width="40%" align="left"
											class="sortableTD"><s:textfield
											name="paraFrm_skillName1" id="paraFrm_skillName1"
											readonly="true" size="20" /></td>
										<td width="5%" class="sortableTD"><img
											src="../pages/images/search2.gif" height="16"
											align="absmiddle" width="16" theme="simple"
											onclick="callSkill('<s:property value='<%=""+j%>'/>')">
										</td>

										<td width="30%" align="left" class="sortableTD"><input
											type="text" maxlength="5" name="skillExp"
											onkeypress="return numbersOnly();"
											id="paraFrm_skillExp<%=j%>"
											value='<s:property value="skillExp" />' size="10" /></td>
										<!--  <td width="12%" class="border2" align="center"><s:select name="skillSel" cssStyle="width:65" theme="simple" list="#{'':'','A':'And','R':'Or'}" /></td>-->

										<input type="hidden" name="<%="hdeleteSkill"+j%>"
											id="hdeleteSkill<%=j%>" />
										<td width="10%" class="sortableTD" align="center"><input
											type="checkbox" class="checkbox" value="N"
											style="text-align: center; margin: 1px" name="confChkSkill"
											id="confChkSkill<%=j%>" onclick="callForSkill('<%=j%>')" />
										<input type="hidden" name="hdeleteSkill1" id="hdeleteSkill1" />
										<input type="hidden" name="paraFrm_skillId1"
											id="paraFrm_skillId1" /></td>
										<!--<td width="0%"></td>
												<td width="0%"></td>

											-->
									</tr>
								</s:else>


							</table>
							<%
											} catch (Exception e) {

											e.printStackTrace();

										}
									%>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td width="100%"><img
						src="../pages/images/recruitment/space.gif" width="5" height="7" /></td>
				</tr>

				<tr id="jobConsultFlag">
					<!-- Start of consultant list -->
					<td width="100%">

					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">
						<tr>
							<td width="100%">
							<table width="100%" border="0" cellpadding="2" cellspacing="2">
								<tr>

									<td width="75%" nowrap="nowrap"><strong class="formhead">Consultant
									List: </strong></td>
									<td width="25%" align="right" nowrap="nowrap"><input
										type="button" class="add" value="  Add Row"
										onclick="addRowToTableConsultant();" /> <input type="button"
										class="delete" value="  Remove"
										onclick="return deleteConsultant();" /></td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td width="100%">
							<table border="0" width="100%" id="tblConsultant">
								<tr class="sortable">
									<td width="5%" valign="top" class="formth" nowrap="nowrap"><b><label
										class="set" name="serial.no" id="serial.no2"
										ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b></td>
									<td width="25%" valign="top" class="formth" align="center"><b><label
										class="set" name="Consultant.Name" id="Consultant.Name"
										ondblclick="callShowDiv(this);"><%=label.get("Consultant.Name")%></label></b></td>
									<td width="5%" valign="top" class="formth" align="center">&nbsp;</td>
									<td width="15%" valign="top" class="formth" align="center"><b><label
										class="set" name="City" id="City"
										ondblclick="callShowDiv(this);"><%=label.get("City")%></label></b></td>
									<td width="15%" valign="top" class="formth" align="center"><b><label
										class="set" name="Phone" id="Phone"
										ondblclick="callShowDiv(this);"><%=label.get("Phone")%></label></b></td>
									<td width="15%" valign="top" class="formth" align="center"><b><label
										class="set" name="Email.Address" id="Email.Address"
										ondblclick="callShowDiv(this);"><%=label.get("Email.Address")%></label></b></td>
									<td width="15%" valign="top" class="formth" align="center"><b><label
										class="set" name="asgvacan" id="asgvacan1"
										ondblclick="callShowDiv(this);"><%=label.get("asgvacan")%></label></b></td>
									<td width="5%" valign="top" class="formth" abbr="right"><input
										class="classcheck"
										style="text-align: center; border: none; margin: 1px"
										type="checkbox" size="2" name="chkConsultantAll"
										id="chkConsultantAll" onclick="return callForConsultantAll();"></td>

								</tr>
								<%! int c=0; %>

								<%int j=0; %>



								<s:if test="consultantListFlag">
									<s:iterator value="consultantList">

										<tr class="border2">
											<td width="5%" class="sortableTD"><%=++j%><s:hidden
												name="hssrlNoConsultant" /></td>
											<%
		      	   		                                String si = (String)request.getAttribute("paraFrm_consultantId"+j);   
			   											String sn=(String)request.getAttribute("paraFrm_consultantName"+j);
			   											String city=(String)request.getAttribute("paraFrm_city"+j);
			   											String phoneNum=(String)request.getAttribute("paraFrm_phoneNo"+j);
			   											String email=(String)request.getAttribute("paraFrm_emailAdd"+j);
			   											String cityName=(String)request.getAttribute("paraFrm_cityName"+j);
		      	   		                               %>
											<td nowrap="nowrap" width="25%" class="sortableTD"><s:textfield
												name='<%="paraFrm_consultantName"+j%>'
												id='<%="paraFrm_consultantName"+j%>' value="<%=sn%>"
												readonly="true" size="25" /></td>
											<td width="5%" nowrap="nowrap" class="sortableTD"><img
												src="../pages/images/search2.gif" height="16"
												align="absmiddle" width="16" theme="simple"
												onclick="callConsultant('<s:property value='<%=""+j%>'/>')">
											</td>

											<td width="15%" nowrap="nowrap" class="sortableTD"
												align="left"><s:textfield
												name='<%="paraFrm_cityName"+j%>'
												id='<%="paraFrm_cityName"+j%>' value="<%=cityName%>"
												readonly="true" size="15" /></td>
											<td width="15%" nowrap="nowrap" class="sortableTD"
												align="left"><s:textfield
												name='<%="paraFrm_phoneNo"+j%>'
												id='<%="paraFrm_phoneNo"+j%>' value="<%=phoneNum%>"
												readonly="true" size="15" /></td>
											<td width="15%" nowrap="nowrap" class="sortableTD"
												align="left"><s:textfield
												name='<%="paraFrm_emailAdd"+j%>'
												id='<%="paraFrm_emailAdd"+j%>' value="<%=email%>"
												readonly="true" size="15" /></td>
											<td width="15%" nowrap="nowrap" class="sortableTD"
												align="left"><input type="text"
												name="consultantVacancies" maxlength="5"
												id="paraFrm_consultantVacancies<%=j %>"
												onkeypress="return numbersOnly();"
												value='<s:property value="consultantVacancies" />' size="15" /></td>
											<td width="5%" nowrap="nowrap" class="sortableTD"><input
												type="checkbox" class="checkbox" value="N"
												name=<%="confChkConsultant"+j%> id="confChkConsultant<%=j%>"
												onclick="callForConsultant('<%=j%>')" /> <input
												type="hidden" name='<%="hdeleteConsultant"+j%>'
												id="hdeleteConsultant<%=j%>" /> <s:hidden
												name='<%="paraFrm_consultantId"+j%>'
												id='<%="paraFrm_consultantId"+j%>' value="<%=si%>" /> <s:hidden
												name='<%="paraFrm_city"+j%>' id='<%="paraFrm_city"+j%>'
												value="<%=city%>" /></td>

										</tr>


									</s:iterator>


								</s:if>
								<s:else>


									<tr class="border2">
										<td nowrap="nowrap" width="5%" class="sortableTD"><%=++j%></td>
										<td nowrap="nowrap" width="25%" class="sortableTD"><s:textfield
											name="paraFrm_consultantName1" id="paraFrm_consultantName1"
											readonly="true" size="25" /></td>
										<td nowrap="nowrap" width="5%" class="sortableTD"><img
											src="../pages/images/search2.gif" height="16"
											align="absmiddle" width="16" theme="simple"
											onclick="callConsultant('<s:property value='<%=""+j%>'/>')">
										</td>
										<td nowrap="nowrap" width="15%" class="sortableTD"
											align="left"><s:textfield name="paraFrm_cityName1"
											id="paraFrm_cityName1" readonly="true" size="15" /></td>
										<td nowrap="nowrap" width="15%" class="sortableTD"
											align="left"><s:textfield name="paraFrm_phoneNo1"
											id="paraFrm_phoneNo1" readonly="true" size="15" /></td>
										<td nowrap="nowrap" width="15%" class="sortableTD"
											align="left"><s:textfield name="paraFrm_emailAdd1"
											id="paraFrm_emailAdd1" readonly="true" size="15" /></td>
										<td nowrap="nowrap" width="15%" class="sortableTD"
											align="left"><input type="text"
											name="consultantVacancies" maxlength="5"
											id="paraFrm_consultantVacancies<%=j%>"
											onkeypress="return numbersOnly();"
											value='<s:property value="consultantVacancies" />' size="15" />
										<input type="hidden" name="<%="hdeleteConsultant"+j%>"
											id="hdeleteConsultant<%=j%>" /></td>
										<td width="5%" nowrap="nowrap" class="sortableTD"><input
											type="checkbox" class="checkbox" value="N"
											name="confChkConsultant" id="confChkConsultant<%=j%>"
											onclick="callForConsultant('<%=j%>')" /> <input
											type="hidden" name="paraFrm_consultantId1"
											id="paraFrm_consultantId1" /> <input type="hidden"
											name="paraFrm_city1" id="paraFrm_city1" /> <input
											type="hidden" name="hdeleteConsultant1"
											id="hdeleteConsultant1" /></td>

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

		<tr>
			<td width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<!-- table 7 -->
				<tr>
					<td width="15%"><label class="set" name="rec.comments"
						id="rec.comments" ondblclick="callShowDiv(this);"><%=label.get("rec.comments")%></label>
					:</td>
					<td width="50%" nowrap="nowrap"><s:textarea theme="simple"
						cols="60" rows="3" name="comments"
						onkeyup="return checkMaxLenthTextarea('paraFrm_comments','paraFrm_maxchar','2000');" />
					<img src="../pages/images/zoomin.gif" height="12" align="bottom"
						width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_comments','rec.comments','','2000','2000');">&nbsp;&nbsp;

					Remaining Chars : <s:textfield name="maxchar" readonly="true"
						size="5" /></td>
				</tr>
			</table>
			</td>
		</tr>






		<tr>
			<td width="100%">
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%" colspan="2"><!-- 
							<s:submit cssClass="token"
								action="VacancyPublish_publish" theme="simple"
								value="Publish" id="Publish" onclick="return validation();" />
						 --> <input type="button" name="search" class="token"
						value="Publish" id="PublishBelow" onclick="return validation();" />

					<s:if test="cancelFlag">
						<input type="button" name="search" class="cancel" value="Cancel"
							onclick="callManagement()" />
					</s:if> <s:else>
						<input type="button" name="search" class="cancel" value="Cancel"
							onclick="callPublish()" />
					</s:else></td>
					<td width="22%"></td>
				</tr>
			</table>
			</td>
		</tr>








	</table>
	<s:hidden name="rowId" />
	<s:hidden name="vacanDtlCode" />
	<s:hidden name="reqCode" />
	<s:hidden name="appEmpId" />
	<s:hidden name="hiringEmpId" />
</s:form>
<script>

//******************recruitment scripts*******		
  function clear(){
	document.onkeypress = "";
}
  
  
  function addRowToTableSkill()
{
  var tbl = document.getElementById('tblRecruiter');
  
  var lastRow = tbl.rows.length;
 
  
  // if there's no header row in the table, then iteration = lastRow + 1
  var iteration = lastRow;
  var row = tbl.insertRow(lastRow);
 
  //alert("iteration is"+iteration);
  // left cell
  var cellLeft = row.insertCell(0);
   cellLeft.className='sortableTD';
  var textNode = document.createTextNode(iteration);
  cellLeft.appendChild(textNode);
  
  
  
   var cell = row.insertCell(1);
      
  var ed = document.createElement('input');
  ed.type = 'text';
  ed.name = 'paraFrm_skillName' + iteration;
  ed.id = 'paraFrm_skillName' + iteration;
  ed.size =20;
   ed.readOnly ='true';
  

    cell.appendChild(ed);
  	cell.className='sortableTD';

//var id1=  document.getElementById('id1');
//id1.className='sortableTD'; 

var cell3 = row.insertCell(2);
 cell3.className='sortableTD';
var img=  document.createElement('img');
img.type='image';
img.src='../pages/images/search2.gif';
 img.height='16';
 img.align='absmiddle';
 img.width='16';
 img.id='img'+ iteration;
 img.theme='simple';
 img.onclick=function(){
 			
 			document.getElementById('paraFrm_rowId').value=iteration;
 			callsF9(500,325,'VacancyPublish_f9Recruiter.action');
 		
};
 cell3.appendChild(img);
 

  // right cell
  var cellRight = row.insertCell(3);
     cellRight.className='sortableTD';
  
  var el = document.createElement('input');
  
  el.type = 'text';
  el.name = 'skillExp';
 el.id = 'paraFrm_skillExp'+ iteration;
  el.size = 10;
  el.align = "absmiddle";
 	el.maxLength='5';
  el.onkeypress = function(){
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
  
  cellRight.appendChild(el);
   // select cell
  var cellLast = row.insertCell(4);
  cellLast.className='sortableTD';
  var skillChk = document.createElement('input');
 
   skillChk.type = 'checkbox';
  skillChk.name = 'confChkSkill'+iteration;
  skillChk.id = 'confChkSkill'+iteration;
 //  skillChk.align = 'center';
  skillChk.onclick=function(){
 			  if(document.getElementById('confChkSkill'+iteration).checked == true)
	   {	
	    		document.getElementById('hdeleteSkill'+iteration).value="Y";
	    		
	
	   }  else{
	  			 document.getElementById('hdeleteSkill'+iteration).value="";
	   	
   		}
 		

	     	
	     
	     	
};

  
 cellLast.align='center';
  cellLast.appendChild(skillChk);

  
 // var hiddenDel=row.insertCell(5);
  var hid = document.createElement('input');
  hid.type = 'hidden';
  hid.name = 'hdeleteSkill'+iteration;
  hid.id = 'hdeleteSkill' + iteration;
   cellLast.appendChild(hid);
// hiddenDel.appendChild(hid);
 //  hiddenDel.className='sortableTD';
  
  
 // var hiddenSkill=row.insertCell(6);
  var hidSkillId = document.createElement('input');
  hidSkillId.type = 'hidden';
  hidSkillId.name = 'paraFrm_skillId'+iteration;
  hidSkillId.id = 'paraFrm_skillId' + iteration;
   cellLast.appendChild(hidSkillId);
 // hiddenSkill.appendChild(hidSkillId);
//hiddenSkill.className='sortableTD';
	
  
   }  
   
   
   function deleteSkill()
	{
	//alert('dgdfgfd');

		 if(chkSkill()){
		 var con=confirm('Do you want to remove the records?');
		 if(con){
		    document.getElementById('paraFrm').action="VacancyPublish_deleteSkill.action";
	  		document.getElementById('paraFrm').submit();
		    } else{
		     return true;
		    }
		 }else {
		 	alert('Please Select Atleast one Record To Remove');
		 	 return false;
		 }
		
	 
	}
	
	function callForRecruiterAll()
  {
	 	  
		var tbl = document.getElementById('tblRecruiter');
		var rowLen = tbl.rows.length;
	if (document.getElementById("chkSkillAll").checked == true){
	for (var idx = 1; idx < rowLen; idx++) {
	
				 document.getElementById("confChkSkill"+idx).checked = true;
				 document.getElementById('hdeleteSkill'+idx).value="Y";
				
	    }

 }else{
	for (var idx = 1; idx < rowLen; idx++) {
	
	document.getElementById("confChkSkill"+idx).checked = false;
	document.getElementById('hdeleteSkill'+idx).value="";
     }
  }	 
		
	
  }
  
  function callSkill(id)
    {
	    //alert("val of id"+id);
	      document.getElementById('paraFrm_rowId').value=id; 
	      callsF9(500,325,'VacancyPublish_f9Recruiter.action'); 
     
     
   }
   
   function callForSkill(id)
	   {
	 	  
	
	   if(document.getElementById('confChkSkill'+id).checked == true)
	   {	  
	    document.getElementById('hdeleteSkill'+id).value="Y";
	   
	   }
	   else{
	   document.getElementById('hdeleteSkill'+id).value="";
	   	
   		}
  }
  
  function chkSkill(){
	var tbl = document.getElementById('tblRecruiter');
		var rowLen = tbl.rows.length;
		  for(var a=1;a<rowLen;a++){	
		   if(document.getElementById('confChkSkill'+a).checked == true)
			   {	
		 	    return true;
		        }	   
		  }
		  return false;
	}
//      recruitment scripts

// consultant scripts

 function addRowToTableConsultant()
{
  var tbl = document.getElementById('tblConsultant');
  
  var lastRow = tbl.rows.length;
 
  
  // if there's no header row in the table, then iteration = lastRow + 1
  var iteration = lastRow;
  var row = tbl.insertRow(lastRow);
  
  //alert("iteration is"+iteration);
  // left cell
  var cellLeft = row.insertCell(0);
  var textNode = document.createTextNode(iteration);
  cellLeft.appendChild(textNode);
  cellLeft.className='sortableTD';
  
   var cell = row.insertCell(1);
      
  var ed = document.createElement('input');
  ed.type = 'text';
  ed.name = 'paraFrm_consultantName' + iteration;
  ed.id = 'paraFrm_consultantName' + iteration;
  ed.size =25;
  ed.readOnly ='true';

    cell.appendChild(ed);
   cell.className='sortableTD';

 
var cell3 = row.insertCell(2);
var img=  document.createElement('img');
img.type='image';
img.src='../pages/images/search2.gif';
 img.height='16';
 img.align='absmiddle';
 img.width='16';
 img.id='img'+ iteration;
 img.theme='simple';
 img.onclick=function(){
 			
 			document.getElementById('paraFrm_rowId').value=iteration;
 			callsF9(500,325,'VacancyPublish_f9Consultant.action');
 		
};
  cell3.appendChild(img);
  cell3.className='sortableTD';

  // right cell
  //var cellRight = row.insertCell(3);
  
   
 // cellRight.appendChild(el);
  //cellRight.className='sortableTD';
  
   var cellRight = row.insertCell(3);
   cellRight.className='sortableTD';
  var el = document.createElement('input');
  
  el.type = 'text';
  el.name = 'paraFrm_cityName'+ iteration;
 el.id = 'paraFrm_cityName'+ iteration;
  el.size = 15;
   el.readOnly ='true';
  
  cellRight.appendChild(el);
  
   // right cell
  var cellRight = row.insertCell(4);
  var el = document.createElement('input');
  
  el.type = 'text';
  el.name = 'paraFrm_phoneNo'+ iteration;
 el.id = 'paraFrm_phoneNo'+ iteration;
  el.size = 15;
   el.readOnly ='true';
  
  cellRight.appendChild(el);
  cellRight.className='sortableTD';
  // right cell
  var cellRight = row.insertCell(5);
  var el = document.createElement('input');
  
  el.type = 'text';
  el.name = 'paraFrm_emailAdd'+ iteration;
 el.id = 'paraFrm_emailAdd'+ iteration;
  el.size = 15;
   el.readOnly ='true';
  
  cellRight.appendChild(el);
  cellRight.className='sortableTD';
   // right cell
  var cellRight = row.insertCell(6);
  var el = document.createElement('input');
  
  el.type = 'text';
  el.name = 'consultantVacancies';
  el.maxLength='5';
 el.id = 'paraFrm_consultantVacancies'+ iteration;
  el.size = 15;
  el.onkeypress = function(){
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
  cellRight.appendChild(el);
  cellRight.className='sortableTD';
   // select cell
  var cellLast = row.insertCell(7);
  var skillChk = document.createElement('input');
 
   skillChk.type = 'checkbox';
  skillChk.name = 'confChkConsultant'+iteration;
  skillChk.id = 'confChkConsultant'+iteration;
  skillChk.onclick=function(){
 			  if(document.getElementById('confChkConsultant'+iteration).checked == true)
	   {	
	    		document.getElementById('hdeleteConsultant'+iteration).value="Y";
	    		
	
	   }  else{
	  			 document.getElementById('hdeleteConsultant'+iteration).value="";
	   	
   		}
};
  cellLast.appendChild(skillChk);
  cellLast.className='sortableTD';
  var el = document.createElement('input');
  
  el.type = 'hidden';
  el.name = 'paraFrm_city'+ iteration;
 el.id = 'paraFrm_city'+ iteration;
  cellLast.appendChild(el);
  
  
  //var hiddenDel=row.insertCell(8);
  var hid = document.createElement('input');
  hid.type = 'hidden';
  hid.name = 'hdeleteConsultant'+iteration;
  hid.id = 'hdeleteConsultant' + iteration;
  
  //hiddenDel.appendChild(hid);
// hiddenDel.className='sortableTD';
   cellLast.appendChild(hid);
  
  var hiddenSkill=row.insertCell(8);
  var hidSkillId = document.createElement('input');
  hidSkillId.type = 'hidden';
  hidSkillId.name = 'paraFrm_consultantId'+iteration;
  hidSkillId.id = 'paraFrm_consultantId' + iteration;
  hiddenSkill.appendChild(hidSkillId);
  //hiddenSkill.className='sortableTD';
  cellLast.appendChild(hidSkillId);
  
   }  
   
   function callConsultant(id)
    {
	    //alert("val of id"+id);
	      document.getElementById('paraFrm_rowId').value=id; 
	      callsF9(500,325,'VacancyPublish_f9Consultant.action'); 
     
     
   }
   
   function callForConsultantAll()
  {
	 	  
		var tbl = document.getElementById('tblConsultant');
		var rowLen = tbl.rows.length;
	if (document.getElementById("chkConsultantAll").checked == true){
	for (var idx = 1; idx < rowLen; idx++) {
	
				document.getElementById("confChkConsultant"+idx).checked = true;
				 document.getElementById('hdeleteConsultant'+idx).value="Y";
				
	    }

 }else{
	for (var idx = 1; idx < rowLen; idx++) {
	
	document.getElementById("confChkConsultant"+idx).checked = false;
	document.getElementById('hdeleteConsultant'+idx).value="";
     }
  }	 
		
	
  }
  
  function deleteConsultant()
	{
	//alert('dgdfgfd');
		 if(chkConsultant()){
		 var con=confirm('Do you want to remove the records?');
		 if(con){
		    document.getElementById('paraFrm').action="VacancyPublish_deleteConsultant.action";
	  		document.getElementById('paraFrm').submit();
		    } else{
		     return true;
		    }
		 }else {
		 	alert('Please Select Atleast one Record To Remove');
		 	 return false;
		 }
	 
	}
	
	function chkConsultant(){
	var tbl = document.getElementById('tblConsultant');
		var rowLen = tbl.rows.length;
		  for(var a=1;a<rowLen;a++){	
		   if(document.getElementById('confChkConsultant'+a).checked == true)
			   {	
		 	    return true;
		        }	   
		  }
		  return false;
	}
	
	function callForConsultant(id)
	   {
	 	  
	
	   if(document.getElementById('confChkConsultant'+id).checked == true)
	   {	  
	    document.getElementById('hdeleteConsultant'+id).value="Y";
	   
	   }
	   else{
	   document.getElementById('hdeleteConsultant'+id).value="";
	   	
   		}
  }

////////////////////////////////////end of consultant scripts/////////////////////////////////////////	
	
	function callManagement(){
	
		 document.getElementById('paraFrm').target="main";
		 document.getElementById('paraFrm').action = document.getElementById('formAction').value+'?status='+document.getElementById('statusKey').value+'&flag='+document.getElementById('flagVal').value;
	  	 //document.getElementById("paraFrm").action="VacancyManagement_backPublish.action"; //?code='+reqCode+'&position='+position+'&appliedBy='+appBy+'&hiringMgr='+hirManager+'&requiDate='+requiDt+'&noOfVacan='+noVacancy+'&reqDate='+reqDate+'&vacanDtlCode='+vacanDtlCode;
	 	 document.getElementById("paraFrm").submit();
	}
	
	function callPublish(){
	
		document.getElementById('paraFrm').target="main";
	  	 document.getElementById("paraFrm").action="VacancyPublish_reset.action"; //?code='+reqCode+'&position='+position+'&appliedBy='+appBy+'&hiringMgr='+hirManager+'&requiDate='+requiDt+'&noOfVacan='+noVacancy+'&reqDate='+reqDate+'&vacanDtlCode='+vacanDtlCode;
	 	 document.getElementById("paraFrm").submit();
	}
	
	
	
	function chkRecruiter(){
	
			var tbl = document.getElementById('tblRecruiter');
 		 	var lastRow = tbl.rows.length;
 		 	var iteration = lastRow-1;
 		 	var count=0;
 	
 		 	for(var v=1;v<=iteration;v++){
 		 	 	var vacanNo = trim(document.getElementById('paraFrm_skillExp'+v).value);
 		 		var recName = document.getElementById('paraFrm_skillName'+v).value;
 		 		
 		 		if(!(vacanNo=="" && recName=="")){
 		 			count++;
 		 		
 		 		}
	 		 	
 		 	}
 		 	
 		 	
				if(count==0){
				
				return true;
				}else{
				return false;
				}	

		
	}
	
	
function validation(){		
		var refrralProg ="N"; 
		var divName = document.getElementById('paraFrm_divsion').value;
		if(document.getElementById('checkMeId').checked == true){
			 refrralProg = "Y";
		}		
		if(document.getElementById('paraFrm_reqCode').value==""){
			alert("Please select "+document.getElementById('reqs.code').innerHTML.toLowerCase());
			return false;
		}
		if(refrralProg== "Y"){
		  if(document.getElementById('paraFrm_divsion').value== ""){
		   	alert("Please Select "+document.getElementById('refDivision').innerHTML.toLowerCase());
		   return false;
		  }
		}	
		
		var tbl = document.getElementById('tblRecruiter');
 		var lastRow = tbl.rows.length;
 		if(lastRow <=1){
 		 	alert("Please add at least one row in recruiter assignment");
 		 	return false;
 		}
  		// if there's no header row in the table, then iteration = lastRow + 1
 		var iteration = lastRow-1; 		
 		var totVacancy = document.getElementById('paraFrm_totalVacancy').value; 	
		if(iteration == 1){		 		
		 		 var vacanNo = trim(document.getElementById('paraFrm_skillExp1').value);
		 		 		var recName = document.getElementById('paraFrm_skillName1').value;
		 		 
		 		 if(recName ==''){
		 		 		alert("Please select "+document.getElementById('Recruiter.Name').innerHTML.toLowerCase());
		 		 		return false;
		 		 	    }	
		 		 	    
		 		 if(vacanNo==""){
		 		 		alert("Please enter "+document.getElementById('asgvacan').innerHTML.toLowerCase());
		 		 		return false;
		 		 	    }		 		 	
		 		 if(vacanNo!=""){
		 		 	if(recName ==''){
		 		 		alert("Please select "+document.getElementById('Recruiter.Name').innerHTML.toLowerCase());
		 		 		return false;
		 		 	    }
		 		 	}
		 		 	
		 		 if(recName!=""){
		 			 if(vacanNo==""){
		 		 		alert("Please enter "+document.getElementById('asgvacan').innerHTML.toLowerCase());
		 		 		return false;
		 		 	    }		 		 
		 		 }			 
		 		 	if(eval(vacanNo) > eval(totVacancy)){		 		 	
		 		 			alert(document.getElementById('asgvacan').innerHTML+' should not be greater than '+document.getElementById('noofvacan').innerHTML.toLowerCase());
		 		 			document.getElementById('paraFrm_skillExp1').focus();
		 		 	 		return false;
		 		 	}		 		 	
		 		 	if(eval(vacanNo) < eval(totVacancy)){
		 		 			alert(document.getElementById('asgvacan').innerHTML+' should not be less than '+document.getElementById('noofvacan').innerHTML.toLowerCase());
		 		 			document.getElementById('paraFrm_skillExp1').focus();
		 		 			return false;
		 		 	}
 		 }
 		 else{ 		
		 		if(chkRecruiter()){
		 		alert("Please select the Recruiter Name and enter the no. of vacancies assigned.");
		 			//alert("Please select the "+document.getElementById('Recruiter.Name').innerHTML.toLowerCase()+" and "+document.getElementBYId('asgvacan').innerHTML.toLowerCase());
		 			return false;
		 			
		 		}
		 		 var total = 0;
		 		
		 		 	for(var i=1;i<=iteration;i++){
		 		 		var vacanNo = trim(document.getElementById('paraFrm_skillExp'+i).value);
		 		 		var recName = document.getElementById('paraFrm_skillName'+i).value;
		 		 	
		 		 		var cnt=0;
		 		 for(var d=1;d<=iteration;d++){			 		 			
			 		 var duplicateName = document.getElementById('paraFrm_skillName'+d).value;
			 		 if(recName!="" && duplicateName!=""){		
			 		 		if(recName == duplicateName){
			 		 			cnt++;
			 		 		}
			 		 }	
			 		 if(cnt > 1){
			 		 		alert('Duplicate records found.');
			 		 		return false;
			 		 }
		 		 }		 		 		
		 		if(vacanNo!=""){
		 		 	if(Math.abs(vacanNo)==0){
 		 				alert("Zero not allowed in "+ document.getElementById('asgvacan').innerHTML.toLowerCase());
 		 				document.getElementById('paraFrm_skillExp'+i).focus();
 		 				return false;
 		 			}
		 		 	if(recName ==''){
		 		 		alert("Please select "+document.getElementById('Recruiter.Name').innerHTML.toLowerCase());
		 		 		return false;
		 		 	 }
		 		}		 		 	
		 		if(recName!=""){
		 			 	if(vacanNo==""){
		 		 		alert("Please enter "+document.getElementById('asgvacan').innerHTML.toLowerCase());
		 		 		return false;
		 		 	    }		 		 
		 		 }			 		 		
		 		if(vacanNo!="")
		 		 		total =eval(total) + eval(vacanNo);		 		 		
		 		 }
		 		for(var i=1;i<=iteration;i++){
		 		 if(total !=0){
			 		 	if(total > totVacancy){
			 		 		alert(document.getElementById('asgvacan').innerHTML+' should not be greater than '+document.getElementById('noofvacan').innerHTML);			 		 		
			 		 		document.getElementById('paraFrm_skillExp'+i).focus();
			 		 		return false;
			 		 	}
			 		 	if(total < totVacancy){
			 		 		alert(document.getElementById('asgvacan').innerHTML+' should not be less than '+document.getElementById('noofvacan').innerHTML.toLowerCase());
			 		 		document.getElementById('paraFrm_skillExp'+i).focus();
			 		 		return false;
			 		 	}
		 		 	}
 		 	 } 		 	
 		 } 		
 		 /////////////validation for consultant list///////////////////////////
 		 var tblCons = document.getElementById('tblConsultant');
 		 var lastRow1 = tblCons.rows.length;
  		 // if there's no header row in the table, then iteration = lastRow + 1
 		 var iteration1 = lastRow1-1; 		 
 		 if(iteration1 == 1){
 		 	var consVacanNo = trim(document.getElementById('paraFrm_consultantVacancies1').value);
 		 	var consName = document.getElementById('paraFrm_consultantName1').value; 		 	
 		 	if(consName !=""){
 		 		if(consVacanNo ==''){
 		 		alert('Please enter No of Vacancies Assigned in Consultant List');
 		 		return false;
 		 		}
 		 	}
 		 	if(eval(consVacanNo) == eval(0)){
 		 		alert('No of Vacancies Assigned should not be zero');
 		 		document.getElementById('paraFrm_consultantVacancies1').focus();
 		 		return false;
 		 	}
 		 	
 		 	if(Math.abs(consVacanNo) > Math.abs(totVacancy)){ 		 	
 		 		alert('Vacancy Assigned should not be Greater than No of Vacancies');
 		 		document.getElementById('paraFrm_consultantVacancies1').value='';
 		 		document.getElementById('paraFrm_consultantVacancies1').focus();
 		 		return false;
 		 	}
 		 	
 		 	if(consVacanNo !=""){
	 		 	if(eval(consVacanNo) == eval(0)){
	 		 		alert('Vacancy assigned should not be zero');
	 		 		return false;
	 		 	}
	 		 	if(consName ==''){
 		 		alert('Please select Consultant Name');
 		 		return false;
 		 		}
 		 	}
 		 }
 		 else{ 		 
 		 	var total = 0;
 		 	for(var i=1;i<=iteration1;i++){
 		 		var consVacanNo = trim(document.getElementById('paraFrm_consultantVacancies'+i).value);
 		 		var consName = document.getElementById('paraFrm_consultantName'+i).value;
 		 		//alert('consName'+consName); 		 			
 		 		var cnt1=0;
 		 		for(var c=1;c<=iteration1;c++){	 		 			
	 		 		var duplicateConsName = document.getElementById('paraFrm_consultantName'+c).value;	 		 		
	 		 	 	if(consName!="" && duplicateConsName!=""){ 
	 		 		  if(consName == duplicateConsName){
	 		 			cnt1++;
	 		 	     	}
	 		 	     }	
	 		 		if(cnt1 > 1){
	 		 			alert('Duplicate Records Found');
	 		 			return false;
	 		 		}
 		 		} 		 		
 		 		if(consVacanNo ==''){
 		 		alert('Please enter No of Vacancies Assigned in Consultant List');
 		 		return false;
 		 		}
 		 		if(eval(consVacanNo) == eval(0)){
 		 		alert('No of Vacancies Assigned should not be zero');
 		 		return false;
 		 		} 		 		
 		 		if(Math.abs(consVacanNo) > Math.abs(totVacancy)){ 		 	
 		 		alert('Vacancy Assigned should not be Greater than No of Vacancies');
 		 		document.getElementById('paraFrm_consultantVacancies'+i).value = '';
 		 		document.getElementById('paraFrm_consultantVacancies1'+i).focus();
 		 		return false;
 		 	} 		 		
 		 		if(consName ==''){
 		 		alert('Please select Consultant Name');
 		 		return false;
 		 		}
 		 		total = eval(total) + eval(consVacanNo);
 		 	}
 		 }
 		 var comment = document.getElementById('paraFrm_comments').value;
 		 var textVal="";
	   	 var charStr;
			for(var i =0; i < eval(comment.length);i++)
			 {
			   charStr =comment.substring(i,eval(i+1));
			     if(charStr=='\n') { 
				    textVal=textVal+'  '; 
				 }else {
				     textVal=textVal+charStr;
				    }
			}   
 		 if(textVal.length >2000){
 			alert("Maximum length of 'Comments' is 2000 characters.");
			return false;
   
  		 }    	 
 		 var con = confirm("Do you really want to publish this vacancy ?");
 		 if(con) {
 		 	document.getElementById('PublishAbove').disabled=true;
 		 	document.getElementById('PublishBelow').disabled=true;
 		 	/*for (var i = 0; i < document.all.length; i++) {
					if(document.all[i].id == 'Publish') {
						document.all[i].disabled=true;
					}
				}*/	
 		 	document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').action ='VacancyPublish_publish.action';
			document.getElementById('paraFrm').submit();
 		 } else {
 		  return false;
 		 } 
} //End of validation() function
	
	
function textCounter(field,  maxlimit) {
	          // if too long...trim it!
		if (field.value.length > maxlimit){			
			//alert('Field value should not exceed '+maxlimit+' chars.');	 
			field.value = field.value.substring(0, maxlimit);
			field.focus;
			return false;
		}
		return true;
}
	
 
chechboxFlag();
function chechboxFlag(){
	if(document.getElementById('checkId').checked == true){
	document.getElementById('jobConsultFlag').style.display='';
	}else{
	document.getElementById('jobConsultFlag').style.display='none';
	}
}

function tooltipDisplay(){	 
	 document.getElementById("paraFrm_position").title =document.getElementById("paraFrm_position").value; 	 
}
	
	
function callRefDiv(){
	if(document.getElementById('checkMeId').checked== true){
	   document.getElementById('divShow').style.display ='';
	}
	else{
		document.getElementById('divShow').style.display ='none';
	}
}
callRefDiv();
</script>