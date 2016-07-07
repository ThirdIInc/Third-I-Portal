<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="AjaxShortTerm.js"></script>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="QuickProjectAppr" id="paraFrm" validate="true"
	name="ShortTermForm" target="main" theme="simple">

	<table width="100%" border="0" align="right" cellpadding="2" cellspacing="2" class="formbg">

		<tr>
			<td valign="bottom" class="txt"><s:hidden
				name="quickProjectCode" /> <s:hidden name="apptype" /> <s:hidden
				name="status" /> <s:hidden name="dataPath" /> <s:hidden
				name="forwardToHidden" />
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Quick
					Project Approval Form</strong> <s:hidden name="hiddenValue" /></td>
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
					<td width="80%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

					<td width="20%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="4"><s:hidden name="flag" />
			<table width="100%" cellspacing="2" cellpadding="2" class="formbg" border="0">
				<tr>
					<td width="25%" colspan="1"><B>Comments By Approver</B></td>
					<td width="75%" colspan="3" id="ctrlShow"><s:if
						test="%{status == 'PP'||status == 'SS'}">
						<s:textarea name="comments" cols="100" rows="3" id="comments"
							onkeypress="return imposeMaxLength(event, this, 900);" />
					</s:if></td>
				</tr>
			</table>
			</td>
		</tr>
		<s:if test="%{status == 'PP'}">
			<tr>
				<td colspan="4">
				<table width="100%" cellspacing="1" cellpadding="1" class="formbg"
					border="0">
					<tr>
						<td colspan="4"><strong>Form Approval </strong></td>
					</tr>
					<tr>
						<td colspan="4"><b>Note : </b>Please Follow the Following
						Reporting Structure : <br />
						Manager,Resource Planning >> RBM >> Branch Director >> Vice
						President >> Director, Resource Planning>> Sr. VP Operations >>
						Human Resources<br>
						</td>
					</tr>
					<tr>
						<td width="25%" colspan="1"><label class="set"
							name="next.approver" id="next.approver"
							ondblclick="callShowDiv(this);"> <%=label.get("next.approver")%></label>
						:</td>
						<td width="75%" colspan="3" id="ctrlShow"><s:hidden
							name="nextAppCode"></s:hidden> <s:if
							test="%{forwardToHidden == 'SS'}">

						</s:if> <s:else>
							<s:textfield name="nextAppToken" size="20" theme="simple"
								readonly="true" />
							<s:textfield name="nextAppName" size="72" theme="simple"
								readonly="true" />
							<s:a href="#">
								<img id="ctrlShow" src="../pages/images/recruitment/search2.gif"
									width="16" height="15" class="iconImage"
									onclick="javascript:callsF9(500,325,'QuickProjectAppr_f9nextAppr.action')" />
							</s:a>
						</s:else></td>
					<tr>
						<td colspan="1"><label class="set" name="forward.to"
							id="forward.to" ondblclick="callShowDiv(this);"> <%=label.get("forward.to")%></label>:
						</td>
						<td colspan="3" id="ctrlShow"><s:if
							test="%{forwardToHidden == 'MM'}">
							<s:select name="forwardTo" theme="simple"
								list="#{'RR':'RBM','BB':'Branch Director','VV':'Vice President','DD':'Director, Resource Planning','SS':'Sr. VP Operations'}"></s:select>
						</s:if> <s:elseif test="%{forwardToHidden == 'RR'}">
							<s:select name="forwardTo" theme="simple"
								list="#{'BB':'Branch Director','VV':'Vice President','DD':'Director, Resource Planning','SS':'Sr. VP Operations'}"></s:select>
						</s:elseif> <s:elseif test="%{forwardToHidden == 'BB'}">
							<s:select name="forwardTo" theme="simple"
								list="#{'VV':'Vice President','DD':'Director, Resource Planning','SS':'Sr. VP Operations'}"></s:select>
						</s:elseif> <s:elseif test="%{forwardToHidden == 'VV'}">
							<s:select name="forwardTo" theme="simple"
								list="#{'DD':'Director, Resource Planning','SS':'Sr. VP Operations'}"></s:select>
						</s:elseif> <s:elseif test="%{forwardToHidden == 'DD'}">
							<s:select name="forwardTo" theme="simple"
								list="#{'SS':'Sr. VP Operations'}"></s:select>
						</s:elseif></td>
					</tr>

					</tr>
				</table>
				</td>
			</tr>
		</s:if>

		<s:if test="listComment">
			<tr>
				<td colspan="4">
				<table width="100%" cellspacing="2" cellpadding="2" class="formbg"
					border="0">
					<tr>
						<td class="formth" width="05%">Sr No</td>
						<td class="formth" width="15%">Approver Name</td>
						<td class="formth" width="40%" align="center">Comments</td>
						<td class="formth" width="15%">Application Date</td>
						<td class="formth" width="15%">Status</td>
					</tr>
					<%int tt=1; %>

					<s:iterator value="listComment">
						<tr>
							<td class="sortableTD"><%=tt++ %></td>
							<td class="sortableTD"><s:property value="ittApproverName" /></td>
							<td class="sortableTD"><s:property value="ittComments" /></td>
							<td class="sortableTD"><s:property
								value="ittApplicationDate" /></td>
							<td class="sortableTD"><s:property value="ittStatus" /></td>
						</tr>
					</s:iterator>
				</table>
				</td>
			</tr>
		</s:if>
		<s:hidden name="fileheaderName" />
		
		<!-- Request Information start -->
		<tr>
			<td width="100%" height="22">
			<table width="100%" border="0" align="center" cellpadding="2" cellspacing="1" class="formbg">
				<tr>
					<td colspan="4"><b><label class="set"
						name="requisition.information" id="requisition.information"
						ondblclick="callShowDiv(this);"><%=label.get("requisition.information")%></label>
					</b></td>
				</tr>

				<tr>

					<td width="25%" align="left"><label id="manager.name"
						name="manager.name" ondblclick="callShowDiv(this);"><%=label.get("manager.name")%></label>
					:<font color="red">*</font></td>
					<td width="75%" colspan="3"><s:hidden name="managerCode"></s:hidden>
					<s:textfield cssStyle="background-color: #F2F2F2;"
						name="managerToken" size="20" theme="simple" readonly="true" /> <s:textfield
						name="managerName" size="72" theme="simple" readonly="true"
						cssStyle="background-color: #F2F2F2;" /></td>
				</tr>

				<tr>
					<td width="25%"><label id="phone" name="phone"
						ondblclick="callShowDiv(this);"><%=label.get("phone")%></label> :<font
						color="red">*</font></td>
					<td width="25%"><s:textfield name="phone" maxlength="200"
						size="20" onkeypress="return numbersOnly();" /></td>
					<td width="25%" align="left"><label id="manager.email"
						name="manager.email" ondblclick="callShowDiv(this);"><%=label.get("manager.email")%></label>
					:</td>
					<td width="25%"><s:textfield name="managerEmail"
						maxlength="200" size="20" /></td>
				</tr>

				<tr>
					<td width="25%"><label id="no.of.temps" name="no.of.temps"
						ondblclick="callShowDiv(this);"><%=label.get("no.of.temps")%></label>
					:</td>
					<td width="25%"><s:textfield name="noOfTemps" maxlength="200"
						size="20" /></td>
					<td width="25%" align="left"><label id="position.title"
						name="position.title" ondblclick="callShowDiv(this);"><%=label.get("position.title")%></label>
					:</td>
					<td width="25%"><s:textfield name="positionTitle" size="20"
						maxlength="200" /></td>
				</tr>

				<tr>
					<td width="25%"><label id="brand" name="brand"
						ondblclick="callShowDiv(this);"><%=label.get("brand")%></label> :<font
						color="red">*</font></td>
					<td width="25%"><s:hidden name="brandCode" /> <s:textfield
						name="brand" maxlength="20" size="20"
						onkeypress="return numbersWithColon();" /> <img id="ctrlHide"
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage"
						onclick="javascript:callsF9(500,325,'QuickProjectAppr_f9brand.action')" />

					</td>
					<td width="25%" align="left"><label id="branch" name="branch"
						ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
					<td width="25%"><s:hidden name="branchCode" /> <s:textfield
						name="branch" maxlength="20" size="20"
						onkeypress="return numbersWithColon();" /> <img id="ctrlHide"
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage"
						onclick="javascript:callsF9(500,325,'QuickProjectAppr_f9branch.action')" />
					</td>
				</tr>

				<tr>
					<td width="25%"><label id="executive" name="executive"
						ondblclick="callShowDiv(this);"><%=label.get("executive")%></label>
					:</td>
					<td width="25%"><s:textfield name="executive" maxlength="20"
						size="20" onkeypress="return numbersWithColon();" /></td>

					<td width="25%"><label id="department" name="department"
						ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
					:<font color="red">*</font></td>
					<td width="25%"><s:hidden name="departmentCode" /> <s:textfield
						name="department" maxlength="200" size="20" /> <s:a href="#">
						<img id="ctrlHide" src="../pages/images/recruitment/search2.gif"
							width="16" height="15" class="iconImage"
							onclick="javascript:callsF9(500,325,'QuickProjectAppr_f9department.action')" />
					</s:a></td>
				</tr>

				<tr>
					<td width="25%"><label id="location" name="location"
						ondblclick="callShowDiv(this);"><%=label.get("location")%></label>
					:</td>
					<td width="25%"><s:textfield name="location" maxlength="200"
						size="20" /></td>
					<td width="25%" align="left"><label id="customer"
						name="customer" ondblclick="callShowDiv(this);"><%=label.get("customer")%></label>
					:</td>
					<td width="25%"><s:textfield name="customer" maxlength="200"
						size="20" /></td>
				</tr>

				<tr>
					<td width="25%"><label id="ops.director.namet"
						name="ops.director.name" ondblclick="callShowDiv(this);"><%=label.get("ops.director.name")%></label>
					:</td>
					<td width="25%"><s:textfield name="opsDirectorName"
						maxlength="200" size="20" /></td>
					<td width="25%" align="left"><label id="max.temp.agency"
						name="max.temp.agency" ondblclick="callShowDiv(this);"><%=label.get("max.temp.agency")%></label>
					:</td>
					<td width="25%"><s:textfield name="maxTempAgency"
						maxlength="20" size="20" /></td>
				</tr>

				<tr>
					<td width="25%"><label id="standard.hour" name="standard.hour"
						ondblclick="callShowDiv(this);"><%=label.get("standard.hour")%></label>
					:</td>
					<td width="25%"><s:textfield name="standardHour" maxlength="5"
						size="20" onkeypress="return numbersWithColon();" />(HH24:MM)</td>
					<td width="25%"></td>
					<td width="25%"><s:hidden name="perWeek" /></td>
				</tr>
				<tr>
					<td width="25%"><label id="ot.required" name="ot.required"
						ondblclick="callShowDiv(this);"><%=label.get("ot.required")%></label>
					:</td>
					<td width="25%"><s:select name="otRequired"
						list="#{'Y':'Yes','N':'No'}" onchange="callOTRequired()"></s:select>

					</td>
					<td width="25%" align="left" id="OT1"><label id="no.of.ot.hrs"
						name="no.of.ot.hrs" ondblclick="callShowDiv(this);"><%=label.get("no.of.ot.hrs")%></label>
					:</td>
					<td width="25%" id="OT2"><s:textfield name="noOfOTHrs"
						maxlength="5" size="20" onkeypress="return numbersWithColon();" />(HH24:MM)</td>
				</tr>

				<tr>
					<td width="25%"><label id="project" name="project"
						ondblclick="callShowDiv(this);"><%=label.get("project")%></label>
					:<font color="red">*</font></td>
					<td width="25%"><s:textfield name="project" maxlength="200"
						size="20" /></td>

				</tr>
				<tr>
					<td width="25%"><label id="start.date" name="start.date"
						ondblclick="callShowDiv(this);"><%=label.get("start.date")%></label>
					:<font color="red">*</font></td>
					<td width="25%"><s:textfield name="startDate" maxlength="10"
						size="20" onkeypress="return numbersWithHiphen();" /> <s:a
						href="javascript:NewCal('paraFrm_startDate','DDMMYYYY');">
						<img class="iconImage" id="ctrlHide"
							src="../pages/images/recruitment/Date.gif" width="16" height="16"
							border="0" align="absmiddle" />
					</s:a></td>
					<td width="25%" align="left"><label id="end.date"
						name="end.date" ondblclick="callShowDiv(this);"><%=label.get("end.date")%></label>
					:<font color="red">*</font></td>
					<td width="25%"><s:textfield name="endDate" maxlength="10"
						size="20" onkeypress="return numbersWithHiphen();" /><s:a
						href="javascript:NewCal('paraFrm_endDate','DDMMYYYY');">
						<img class="iconImage" id="ctrlHide"
							src="../pages/images/recruitment/Date.gif" width="16" height="16"
							border="0" align="absmiddle" />
					</s:a></td>
				</tr>

				<tr>
					<td width="25%"><label id="extension" name="extension"
						ondblclick="callShowDiv(this);"><%=label.get("extension")%></label>
					:</td>
					<td width="25%"><s:textfield name="extension" maxlength="200"
						size="20" /></td>
				</tr>

			</table>
			</td>
		</tr>

		<!-- SKILL REQUIREMENT -->
		<tr>
			<td width="100%" height="22">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">

				<tr>
					<td colspan="4"><b><label class="set"
						name="skill.requirement" id="skill.requirement"
						ondblclick="callShowDiv(this);"><%=label.get("skill.requirement")%></label>
					</b></td>
				</tr>

				<tr>
					<td width="25%"><label id="sbu" name="sbu"
						ondblclick="callShowDiv(this);"><%=label.get("sbu")%></label> :</td>
					<td width="25%"><s:select name="sbu"
						list="#{'':'--Select--','DataCenter':'DataCenter','Desk-Top':'Desk-Top','End-User':'End-User','Mid-Range':'Mid-Range','Network':'Network'}"></s:select>

					</td>
					<td width="25%" align="left"><label id="support.type"
						name="support.type" ondblclick="callShowDiv(this);"><%=label.get("support.type")%></label>
					:</td>
					<td width="25%"><s:select name="supportType"
						list="#{'':'--Select--','H':'Hardware Support','S':'Software Support','B':'Hardware & Software Support'}" />

					</td>
				</tr>

				<tr>
					<td width="100%" colspan="4"><strong><label
						id="specific.details" name="specific.details"
						ondblclick="callShowDiv(this);"><%=label.get("specific.details")%></label></strong>
					:</td>
				</tr>


				<tr>
					<td width="25%"><label id="hardware.skill"
						name="hardware.skill" ondblclick="callShowDiv(this);"><%=label.get("hardware.skill")%></label>
					:</td>
					<td width="25%"><s:textfield name="hardwareSkill"
						maxlength="200" size="20" /></td>
					<td width="25%" align="left"><label id="software.skill"
						name="software.skill" ondblclick="callShowDiv(this);"><%=label.get("software.skill")%></label>
					:</td>
					<td width="25%"><s:textfield name="softwareSkill"
						maxlength="200" size="20" /></td>
				</tr>

				<tr>
					<td width="25%"><label id="network.skill" name="network.skill"
						ondblclick="callShowDiv(this);"><%=label.get("network.skill")%></label>
					:</td>
					<td width="25%"><s:textfield name="networkSkill"
						maxlength="200" size="20" /></td>
					<td width="25%" align="left"><label id="other.skill"
						name="other.skill" ondblclick="callShowDiv(this);"><%=label.get("other.skill")%></label>
					:</td>
					<td width="25%"><s:textfield name="otherSkill" maxlength="200"
						size="20" /></td>
				</tr>

			</table>
			</td>
		</tr>
		<!-- END:SKILL REQUIREMENT -->

		<!-- START:REVENUE/COST INFORMATION -->
		<tr>
			<td width="100%" height="22">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">

				<tr>
					<td colspan="4"><b><label class="set"
						name="revenue.cost.information" id="revenue.cost.information"
						ondblclick="callShowDiv(this);"><%=label.get("revenue.cost.information")%></label>
					</b></td>
				</tr>

				<tr>
					<td width="25%" class="formth"><label id="per.of.month"
						name="per.of.month" ondblclick="callShowDiv(this);"><%=label.get("per.of.month")%></label>
					</td>
					<td width="25%" class="formth"><label id="current.month"
						name="current.month" ondblclick="callShowDiv(this);"><%=label.get("current.month")%></label></td>
					<td width="25%" align="left" class="formth"><label
						id="next.month" name="next.month" ondblclick="callShowDiv(this);"><%=label.get("next.month")%></label>
					</td>
					<td width="25%" class="formth"><label id="successive.month"
						name="successive.month" ondblclick="callShowDiv(this);"><%=label.get("successive.month")%></label></td>
				</tr>

				<tr>
					<td width="25%" class="sortableTD"><label id="per.of.month1"
						name="per.of.month" ondblclick="callShowDiv(this);"><%=label.get("per.of.month")%></label>
					:</td>
					<td width="25%" class="sortableTD"><s:textfield
						onkeypress="return numbersOnly();" name="perCurrentMonth"
						maxlength="200" size="20" /></td>
					<td width="25%" align="left" class="sortableTD"><s:textfield
						onkeypress="return numbersOnly();" name="perNextMonth"
						maxlength="200" size="20" /></td>
					<td width="25%" class="sortableTD"><s:textfield
						onkeypress="return numbersOnly();" name="perSuccessiveMonth"
						maxlength="200" size="20" /></td>
				</tr>

				<tr>
					<td width="25%" class="sortableTD"><label
						id="revenue.for.this.project" name="revenue.for.this.project"
						ondblclick="callShowDiv(this);"><%=label.get("revenue.for.this.project")%></label>
					:</td>
					<td width="25%" class="sortableTD"><s:textfield
						onkeypress="return numbersOnly();" name="revCurrentMonth"
						maxlength="200" size="20" /></td>
					<td width="25%" align="left" class="sortableTD"><s:textfield
						onkeypress="return numbersOnly();" name="revNextMonth"
						maxlength="200" size="20" /></td>
					<td width="25%" class="sortableTD"><s:textfield
						onkeypress="return numbersOnly();" name="revSuccessiveMonth"
						maxlength="200" size="20" /></td>
				</tr>

				<tr>
					<td width="25%" class="sortableTD"><label id="labor.cost"
						name="labor.cost" ondblclick="callShowDiv(this);"><%=label.get("labor.cost")%></label>
					:</td>
					<td width="25%" class="sortableTD"><s:textfield
						onkeypress="return numbersOnly();" name="laborCurrentMonth"
						maxlength="200" size="20" /></td>
					<td width="25%" align="left" class="sortableTD"><s:textfield
						onkeypress="return numbersOnly();" name="laborNextMonth"
						maxlength="200" size="20" /></td>
					<td width="25%" class="sortableTD"><s:textfield
						onkeypress="return numbersOnly();" name="laborSuccessiveMonth"
						maxlength="200" size="20" /></td>
				</tr>


				<tr>
					<td width="25%" class="sortableTD"><label id="other.cost"
						name="other.cost" ondblclick="callShowDiv(this);"><%=label.get("other.cost")%></label>
					:</td>
					<td width="25%" class="sortableTD"><s:textfield
						onkeypress="return numbersOnly();" name="othorCurrentMonth"
						maxlength="200" size="20" /></td>
					<td width="25%" align="left" class="sortableTD"><s:textfield
						onkeypress="return numbersOnly();" name="othorNextMonth"
						maxlength="200" size="20" /></td>
					<td width="25%" class="sortableTD"><s:textfield
						onkeypress="return numbersOnly();" name="othorSuccessiveMonth"
						maxlength="200" size="20" /></td>
				</tr>


				<tr>
					<td width="25%" class="sortableTD"><label id="total.cost"
						name="total.cost" ondblclick="callShowDiv(this);"><%=label.get("total.cost")%></label>
					:</td>
					<td width="25%" class="sortableTD"><s:textfield
						onkeypress="return numbersOnly();" name="totalCurrentMonth"
						maxlength="200" size="20" /></td>
					<td width="25%" align="left" class="sortableTD"><s:textfield
						onkeypress="return numbersOnly();" name="totalNextMonth"
						maxlength="200" size="20" /></td>
					<td width="25%" class="sortableTD"><s:textfield
						onkeypress="return numbersOnly();" name="totalSuccessiveMonth"
						maxlength="200" size="20" /></td>
				</tr>

				<tr>
					<td width="25%" class="sortableTD"><label id="profit.cost"
						name="profit.cost" ondblclick="callShowDiv(this);"><%=label.get("profit.cost")%></label>
					:</td>
					<td width="25%" class="sortableTD"><s:textfield
						onkeypress="return numbersOnly();" name="profitCurrentMonth"
						maxlength="200" size="20" /></td>
					<td width="25%" align="left" class="sortableTD"><s:textfield
						onkeypress="return numbersOnly();" name="profitNextMonth"
						maxlength="200" size="20" /></td>
					<td width="25%" class="sortableTD"><s:textfield
						onkeypress="return numbersOnly();" name="profitSuccessiveMonth"
						maxlength="200" size="20" /></td>
				</tr>


				<tr>
					<td width="25%" class="sortableTD"><label id="gross.margin"
						name="gross.margin" ondblclick="callShowDiv(this);"><%=label.get("gross.margin")%></label>
					:</td>
					<td width="25%" class="sortableTD"><s:textfield
						onkeypress="return numbersOnly();" name="grossCurrentMonth"
						maxlength="200" size="20" /></td>
					<td width="25%" align="left" class="sortableTD"><s:textfield
						onkeypress="return numbersOnly();" name="grossNextMonth"
						maxlength="200" size="20" /></td>
					<td width="25%" class="sortableTD"><s:textfield
						onkeypress="return numbersOnly();" name="grossSuccessiveMonth"
						maxlength="200" size="20" /></td>
				</tr>
				<tr>
					<td width="25%" class="sortableTD"><label
						id="meets.requirement" name="meets.requirement"
						ondblclick="callShowDiv(this);"><%=label.get("meets.requirement")%></label>
					:</td>
					<td width="25%" class="sortableTD"><s:textfield
						name="meetsCurrentMonth" readonly="true"
						cssStyle="background-color: #F2F2F2;" /></td>
					<td width="25%" align="left" class="sortableTD"><s:textfield
						name="meetsNextMonth" readonly="true"
						cssStyle="background-color: #F2F2F2;" /></td>
					<td width="25%" class="sortableTD"><s:textfield
						name="meetsSuccessiveMonth" readonly="true"
						cssStyle="background-color: #F2F2F2;" /></td>
				</tr>

				<tr>
					<td width="25%" class="sortableTD"><label id="requires.exp"
						name="requires.exp" ondblclick="callShowDiv(this);"><%=label.get("requires.exp")%></label>
					:</td>
					<td width="25%" class="sortableTD"><s:textfield name="currExp"
						readonly="true" cssStyle="background-color: #F2F2F2;" /></td>
					<td width="25%" align="left" class="sortableTD"><s:textfield
						name="nextExp" readonly="true"
						cssStyle="background-color: #F2F2F2;" /></td>
					<td width="25%" class="sortableTD"><s:textfield name="succExp"
						readonly="true" cssStyle="background-color: #F2F2F2;" /></td>
				</tr>


				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>

				<tr>
					<td width="25%" class="formth">&nbsp;</td>
					<td width="25%" class="formth"><label id="current.month1"
						name="current.month" ondblclick="callShowDiv(this);"><%=label.get("current.month")%></label></td>
					<td width="25%" align="left" class="formth"><label
						id="next.month1" name="next.month" ondblclick="callShowDiv(this);"><%=label.get("next.month")%></label>
					</td>
					<td width="25%" class="formth"><label id="successive.month1"
						name="successive.month" ondblclick="callShowDiv(this);"><%=label.get("successive.month")%></label></td>
				</tr>


				<tr>
					<td width="25%" class="sortableTD"><label id="revenue.cu"
						name="revenue.cu" ondblclick="callShowDiv(this);"><%=label.get("revenue.cu")%></label>
					:</td>
					<td width="25%" class="sortableTD"><s:textfield
						onkeypress="return numbersOnly();" name="revCurrent"
						maxlength="200" size="20" /></td>
					<td width="25%" align="left" class="sortableTD"><s:textfield
						onkeypress="return numbersOnly();" name="revNext" maxlength="200"
						size="20" /></td>
					<td width="25%" class="sortableTD"><s:textfield
						onkeypress="return numbersOnly();" name="revSuccessive"
						maxlength="200" size="20" /></td>
				</tr>

				<tr>
					<td width="25%" class="sortableTD"><label id="labor.cost.cu"
						name="labor.cost.cu" ondblclick="callShowDiv(this);"><%=label.get("labor.cost.cu")%></label>
					:</td>
					<td width="25%" class="sortableTD"><s:textfield
						onkeypress="return numbersOnly();" name="laborCurrent"
						maxlength="200" size="20" /></td>
					<td width="25%" align="left" class="sortableTD"><s:textfield
						onkeypress="return numbersOnly();" name="laborNext"
						maxlength="200" size="20" /></td>
					<td width="25%" class="sortableTD"><s:textfield
						onkeypress="return numbersOnly();" name="laborSuccessive"
						maxlength="200" size="20" /></td>
				</tr>
				<tr>
					<td width="25%" class="sortableTD"><label id="other.cost.cu"
						name="other.cost.cu" ondblclick="callShowDiv(this);"><%=label.get("other.cost.cu")%></label>
					:</td>
					<td width="25%" class="sortableTD"><s:textfield
						onkeypress="return numbersOnly();" name="othorCurrent"
						maxlength="200" size="20" /></td>
					<td width="25%" align="left" class="sortableTD"><s:textfield
						onkeypress="return numbersOnly();" name="othorNext"
						maxlength="200" size="20" /></td>
					<td width="25%" class="sortableTD"><s:textfield
						onkeypress="return numbersOnly();" name="othorSuccessive"
						maxlength="200" size="20" /></td>
				</tr>
				<tr>
					<td width="25%" class="sortableTD"><label
						id="ops.contribution" name="ops.contribution"
						ondblclick="callShowDiv(this);"><%=label.get("ops.contribution")%></label>
					:</td>
					<td width="25%" class="sortableTD"><s:textfield
						onkeypress="return numbersOnly();" name="opsCurrent"
						maxlength="200" size="20" /></td>
					<td width="25%" align="left" class="sortableTD"><s:textfield
						onkeypress="return numbersOnly();" name="opsNext" maxlength="200"
						size="20" /></td>
					<td width="25%" class="sortableTD"><s:textfield
						onkeypress="return numbersOnly();" name="opsSuccessive"
						maxlength="200" size="20" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- END:REVENUE/COST INFORMATION -->
		
		<!-- Applicant comments -- BEGINS -->
		<tr>
			<td colspan="4">
				<table width="100%" cellspacing="1" cellpadding="1" class="formbg" border="0">
					<tr>
						<td width="25%">
							<strong><label class="set" name="applicantComments" id="applicantComments"
							ondblclick="callShowDiv(this);"> <%=label.get("applicantComments")%></label></strong> :
						</td>
						<td width="75%" nowrap="nowrap">
							<s:textarea rows="5" readonly="true" cols="80" name="applicantComments"/>
							<img src="../pages/images/zoomin.gif" height="12" align="absmiddle"
								id='ctrlHide' width="12" theme="simple"
								onclick="javascript:callWindow('paraFrm_applicantComments','applicantComments','','1000','1000');">
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<!-- Applicant comments -- ENDS -->
		
		<%!int count=0; %>
		<tr>
			<td colspan="4">
			<table width="100%" cellspacing="1" cellpadding="1" class="formbg" border="0">
				<tr>
					<td width="25%" colspan="1"><strong><label
						class="set" name="completed.by" id="completed.by"
						ondblclick="callShowDiv(this);"> <%=label.get("completed.by")%></label></strong>
					:</td>
					<td width="25%" colspan="1"><s:property value="completedBy" />
					<s:hidden name="completedBy" /> <s:hidden name="completedOn" /></td>
					<td width="25%" colspan="1"><strong><label
						class="completed.on" name="completed.on" id="sin"
						ondblclick="callShowDiv(this);"> <%=label.get("completed.on")%></label></strong>
					:</td>
					<td width="25%" colspan="1"><s:property value="completedOn" /></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
</s:form>

<script type="text/javascript">	
		function imposeMaxLength(Event, Object, MaxLen)
{
        return (Object.value.length <= MaxLen)||(Event.keyCode == 8 ||Event.keyCode==46||(Event.keyCode>=35&&Event.keyCode<=40))
}
		
		
		callOTRequired();
		
		function callOTRequired(){	
				document.getElementById('OT1').style.display='none';
				document.getElementById('OT2').style.display='none';	
			if(document.getElementById('paraFrm_otRequired').value=='Y')
			{
				document.getElementById('OT1').style.display='';
				document.getElementById('OT2').style.display='';
			}					
		}
		
		
		function validate(){
			if(document.getElementById('paraFrm_phone').value==''){
				alert('Please enter '+document.getElementById('phone').innerHTML.toLowerCase());
				return false;
			}
			
			if(document.getElementById('paraFrm_brand').value==''){
				alert('Please select '+document.getElementById('brand').innerHTML.toLowerCase());
				return false;
			}
			
			if(document.getElementById('paraFrm_department').value==''){
				alert('Please select '+document.getElementById('department').innerHTML.toLowerCase());
				return false;
			}
			
			if(document.getElementById('paraFrm_standardHour').value==''){		
			
			}
			else{
			if(IsValidTime(document.getElementById('paraFrm_standardHour').value)){
			}
			else{
			document.getElementById('paraFrm_standardHour').focus();
			return false;
			}
			}
			
			if(document.getElementById('paraFrm_noOfOTHrs').value==''){		
			
			}
			else{
			if(IsValidTime(document.getElementById('paraFrm_noOfOTHrs').value)){
			}
			else{
			document.getElementById('paraFrm_noOfOTHrs').focus();
			return false;
			}
			}
			
			
			if(document.getElementById('paraFrm_project').value==''){
				alert('Please enter '+document.getElementById('project').innerHTML.toLowerCase());
				return false;
			}
			
			if(document.getElementById('paraFrm_startDate').value==''){
				alert('Please enter '+document.getElementById('start.date').innerHTML.toLowerCase());
				return false;
			}
			var checkstartDate= validateDate('paraFrm_startDate', 'start.date');
			if(!checkstartDate){
			return false;
		}
			
			
			if(document.getElementById('paraFrm_endDate').value==''){
				alert('Please enter '+document.getElementById('end.date').innerHTML.toLowerCase());
				return false;
			}
			var checkend= validateDate('paraFrm_endDate', 'end.date');
			if(!checkend){
			return false;
		}
		
		
		return true;
		}
	
function IsValidTime(id) {		
				
				if(id==''){
				return true;
				}
			var timeStr=id;//document.getElementById('fromTime'+id).value;
				
			var timePat = /^(\d{1,2}):(\d{2})(:(\d{2}))?(\s?(AM|am|PM|pm))?$/;
			
			var matchArray = timeStr.match(timePat);
		
			if (matchArray == null) {
			alert("Please enter time in a valid format(HH24:MM)");
			return false;
			}
			hour = matchArray[1];
			minute = matchArray[2];
			second = matchArray[4];
			ampm = matchArray[6];
			
			if (second=="") { second = null; }
			if (ampm=="") { ampm = null }
			
			if (hour < 0  || hour > 23) {
			alert("Hour must be between 0 and 23");
			return false;
			}
		
			
			if (minute<0 || minute > 59) {
			alert ("Minute must be between 0 and 59.");
			return false;
			}
			if (second != null && (second < 0 || second > 59)) {
			alert ("Second must be between 0 and 59.");
			return false;
			}
			return true;
			}

			function draftFun() {
			if(!validate()){
				return false;
			}
				document.getElementById('paraFrm').target = "_self";
		      	document.getElementById('paraFrm').action = 'QuickProjectAppr_draft.action';
				document.getElementById('paraFrm').submit();
			}
		function sendforapprovalFun() {
				if(!validate()){
				return false;
			}
				var vv=confirm("Do you really want to send for approval this application?");
			if(vv){
				document.getElementById('paraFrm').target = "_self";
		      	document.getElementById('paraFrm').action = 'QuickProjectAppr_sendForApproval.action';
				document.getElementById('paraFrm').submit();
				}else{
				return false;
				}
			}	
			
	
	




		function authorizedsignoffFun() {
	var vv=confirm("Do you really want to authorized sign off this application?");
	if(vv){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'QuickProjectAppr_authorizedSign.action';
		document.getElementById('paraFrm').submit();
		}
	}
	function approveFun() {
	var vv=confirm("Do you really want to approved this application?");
	if(vv){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'QuickProjectAppr_approve.action';
		document.getElementById('paraFrm').submit();
		}
	}
	function forwardFun() {
	if(document.getElementById('paraFrm_nextAppCode').value==''){
	alert('Please select '+document.getElementById('next.approver').innerHTML.toLowerCase());
	return false;
	}
	
	
	var vv=confirm("Do you really want to forward this application?");
	if(vv){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'QuickProjectAppr_forward.action';
		document.getElementById('paraFrm').submit();
		}
	}
	
	
	
	
	function rejectFun() {
	var vv=confirm("Do you really want to reject this application?");
	if(vv){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'QuickProjectAppr_reject.action';
		document.getElementById('paraFrm').submit();
		}
	}
	function sendbackFun() {
	var vv=confirm("Do you really want to sendBack this application?");
	if(vv){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'QuickProjectAppr_sendBack.action';
		document.getElementById('paraFrm').submit();
		}
	}
	
		function backtolistFun() {	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'QuickProjectAppr_back.action';
		document.getElementById('paraFrm').submit();
	}
		function printFun() {	
	window.print();
	}
		function closeFun() {
					window.close();
			}
		</script>



<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>