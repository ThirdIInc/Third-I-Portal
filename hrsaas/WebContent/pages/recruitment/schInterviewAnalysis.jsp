<!-- modified by Prajakta B @Date 15 April 2013 -->
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<link rel="stylesheet" type="text/css"
	href="../pages/common/tabcontent.css" />
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="InterviewAnalysis" method="post" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/common/css/default/images/review_shared.gif"
						width="25" height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Schedule
					Interview Analysis</strong></td>
					<td width="3%" valign="top" class="otxt">
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
					<td width="100%" colspan="3"><s:if test="%{viewFlag}">
						<input type="button" class="token"
							onclick="return callReportVacancies();" value=" Generate Report" />
					</s:if> <s:submit cssClass="reset" action="InterviewAnalysis_reset"
						theme="simple" value="    Reset" /> <input type="button"
						class="token" value=" Save report criteria" onclick="callSave();" />
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="1" cellspacing="1">
				<tr>
					<td nowrap="nowrap"><label class="set" id="save.setting"
						name="save.setting" ondblclick="callShowDiv(this);"><%=label.get("save.setting")%></label>
					:</td>
					<td width="85%"><s:select headerKey="B"
						headerValue="--Select--" name="searchSetting" list="map"
						onchange="callShowRecord();" /> <s:hidden name="reqCode" /> <s:hidden
						name="candidateCode" /> <s:hidden name="positionId" /> <s:hidden
						name="common" /> <s:hidden name="divCount" /> <s:hidden
						name="selectedReq" /> <s:hidden name="editReqFlag" /> <s:hidden
						name="hidSelectedReqName" /> <s:hidden name="checkedCount"
						value="12" /> <s:hidden name="reqStatus" /> <s:hidden
						name="exportAll" id="exportAll" value="on" /> <s:hidden
						name="radioStatus" /> <s:hidden name="radioReq" /> <s:hidden
						name="radioHiringMgr" /> <s:hidden name="radioPosition" /> <s:hidden
						name="hiringMgrCode" /> <s:hidden name="screenFlag" /> <s:hidden
						name="candidateFlag" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3"><s:hidden name='aId' /> <s:hidden name='divId' />
			<s:hidden name='backFlag' /> <s:hidden name='settingNameDup' />

			<table border="0" cellpadding="0" cellspacing="0" class="formbg"
				width="100%">
				<tr>
					<td class="btn-middlebg">
					<div id="tabnav" style="vertical-align: bottom">
					<ul>
						<li><a id="filtOpt"
							href="javascript:callTab('filtOpt','filter');">
						<div align="center"><span>Filter </span></div>
						</a></li>
						<li><a id="sortOpt"
							href="javascript:callTab('sortOpt','sort');">
						<div align="center"><span>Sorting option</span></div>
						</a></li>

						<li><a id="colDef"
							href="javascript:callTab('colDef','column');">
						<div align="center"><span>Column Definition</span></div>
						</a></li>

						<li><a id="advFilter"
							href="javascript:callTab('advFilter','advance');">
						<div align="center"><span>Advanced Filter</span></div>
						</a></li>
					</ul>
					</div>
					</td>
				</tr>
			</table>

			<table id="filterDisp" width="100%" border="0" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td width="100%" colspan="4" class="formtext"><b> <label
						class="set" id="selectFilter" name="selectFilter"
						ondblclick="callShowDiv(this);"><%=label.get("selectFilter")%></label></b>
					</td>
				</tr>

				<tr>
					<td width="20%"><input type="radio" value="C"
						<s:property value="radioReq"/> name="radioOption"
						onclick="callRadioOptionFun('C');"></td>
					<td width="20%" colspan="1" nowrap="nowrap" class="formtext">
					<label class="set" id="reqs.code" name="reqs.code"
						ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label>
					:</td>
					<td width="20%" colspan="1"><s:textfield name="reqname"
						size="25" theme="simple" readonly="true" /></td>
					<td width="50%" colspan="1" id="f9ReqCode"><img
						src="../pages/images/recruitment/search2.gif" height="15"
						align="absmiddle" width="16" onclick="callF9ReqCode('selF9');"></td>
				</tr>

				<tr>
					<td width="20%"><input type="radio" value="H"
						<s:property value="radioHiringMgr"/> name="radioOption"
						onclick="callRadioOptionFun('H');"></td>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" id="hiring.mgr" name="hiring.mgr"
						ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%></label>
					:</td>
					<td width="20%" colspan="1"><s:textfield
						name="hiringMgrName" size="25" theme="simple" readonly="true" />
					</td>
					<td width="50%" colspan="1" id="f9HiringMgrRowId"><img
						src="../pages/images/recruitment/search2.gif" height="15"
						align="absmiddle" width="16" onclick="callF9HiringMgr('selF9');"></td>
				</tr>

				<tr>
					<td width="20%"><input type="radio" value="P"
						name="radioOption" <s:property value="radioPosition"/>
						onclick="callRadioOptionFun('P');"></td>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" id="position11" name="position"
						ondblclick="callShowDiv(this);"><%=label.get("position")%></label>
					:</td>
					<td width="20%" colspan="1"><s:textfield name="position"
						size="25" theme="simple" readonly="true" /></td>
					<td width="50%" colspan="1" id="f9Position"><img
						src="../pages/images/recruitment/search2.gif" height="15"
						align="absmiddle" width="16" onclick="callF9Position('selF9');">
					</td>
				</tr>
				<tr>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" id="reqs.date" name="reqs.date"
						ondblclick="callShowDiv(this);"><%=label.get("reqs.date")%></label>
					:</td>
					<td width="90%" colspan="3">
					<table width="100%">
						<tr>
							<td width="20%"><s:select headerKey="1"
								headerValue="--Select--" name="dateFilter"
								list="#{'O':'On','B':'Before','A':'After' ,'OB':'On before' ,'OA':'On after','F':'Between'}"
								onchange="callToDateDispOnClick();" /></td>
							<td width="20%" colspan="1"><s:textfield name="frmDate"
								size="9" maxlength="10" onkeypress="return numbersWithHiphen();"
								onfocus="clearText('paraFrm_frmDate','dd-mm-yyyy')"
								onblur="setText('paraFrm_frmDate','dd-mm-yyyy')" /></td>
							<td width="20%" colspan="1" align="left"><img
								src="../pages/images/recruitment/Date.gif" id="fromDateIcon"
								class="iconImage" height="18" align="absmiddle" width="18"
								onclick="javascript:NewCal('paraFrm_frmDate','DDMMYYYY');">
							</td>
							<td width="20%" colspan="1">
							<div id="toDateDivLabel"><label class="set"
								id="toDateLabel" name="toDateLabel"
								ondblclick="callShowDiv(this);"><%=label.get("toDateLabel")%></label>
							:</div>
							</td>
							<td width="30%" colspan="2">
							<div id="toDateDiv"><s:textfield name="toDate" size="10"
								maxlength="10" onkeypress="return numbersWithHiphen();"
								onfocus="clearText('paraFrm_toDate','dd-mm-yyyy')"
								onblur="setText('paraFrm_toDate','dd-mm-yyyy')" /> &nbsp; <img
								src="../pages/images/recruitment/Date.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
							</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<!-- Added By Prajakta For Interview Date -->
				<tr>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" id="int.date" name="int.date"
						ondblclick="callShowDiv(this);"><%=label.get("int.date")%></label>
					:</td>
					<td width="80%" colspan="3">
					<table width="100%">
						<tr>
							<td width="20%"><s:select headerKey="1"
								headerValue="--Select--" name="intDateFilter"
								list="#{'O':'On','B':'Before','A':'After' ,'OB':'On before' ,'OA':'On after','F':'Between'}"
								onchange="callToDateDispOnClick1();" /></td>
							<td width="20%" colspan="1"><s:textfield name="intFrmDate"
								size="9" maxlength="10" onkeypress="return numbersWithHiphen();"
								onfocus="clearText('paraFrm_intFrmDate','dd-mm-yyyy')"
								onblur="setText('paraFrm_intFrmDate','dd-mm-yyyy')" /></td>
							<td width="20%" colspan="1" align="left"><img
								src="../pages/images/recruitment/Date.gif" id="IntFromDateIcon"
								class="iconImage" height="18" align="absmiddle" width="18"
								onclick="javascript:NewCal('paraFrm_intFrmDate','DDMMYYYY');">
							</td>
							<td width="20%" colspan="1">
							<div id="intToDateDivLabel"><label class="set"
								id="intToDateLabel" name="intToDateLabel"
								ondblclick="callShowDiv(this);"><%=label.get("intToDateLabel")%></label>
							:</div>
							</td>
							<td width="30%" colspan="2">
							<div id="intToDateDiv"><s:textfield name="intToDate"
								size="10" maxlength="10"
								onkeypress="return numbersWithHiphen();"
								onfocus="clearText('paraFrm_intToDate','dd-mm-yyyy')"
								onblur="setText('paraFrm_intToDate','dd-mm-yyyy')" /> &nbsp; <img
								src="../pages/images/recruitment/Date.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:NewCal('paraFrm_intToDate','DDMMYYYY');">
							</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<!--Ends  Added By Prajakta For Interview Date -->
				<tr>
					<td width="80%" colspan="4">
					<table width="100%" border="0">
						<tr>
							<td width="20%" colspan="1"><label class="set"
								id="selectReqLabel" name="selectReqLabel"
								ondblclick="callShowDiv(this);"><%=label.get("selectReqLabel")%></label>
							:</td>
							<td width="20%" colspan="1" align="left"><s:textarea
								name="selectedReqName" cols="40" rows="2" readonly="true"
								theme="simple" /></td>
							<td width="70%">
							<table width="100%">
								<tr>
									<td width="40%">
									<div id="selectReqDiv"><input type="button" class="token"
										value=" Select Requisition "
										onclick="callSelectReq('simple');"></div>
									</td>
									<td width="60%" colspan="1">
									<div id="textAreaId1"><input type="button" class="token"
										value=" Edit Selection " onclick="callSelectReq('edit');">
									</td>

								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>

			<table id="sort" width="100%" border="0" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td width="100%" colspan="2" class="formtext"><b> <label
						class="set" id="selectSort" name="selectSort"
						ondblclick="callShowDiv(this);"><%=label.get("selectSort")%></label></b>
					</td>
				</tr>
				<tr>
					<td width="17%" colspan="1" class="formtext"><label
						class="set" id="sortByLabel" name="sortByLabel"
						ondblclick="callShowDiv(this);"><%=label.get("sortByLabel")%></label>
					:</td>
					<td width="83%" colspan="1"><s:select headerKey="1"
						headerValue="--Select--" name="sortBy"
						list="#{'R':'Requisition Code','P':'Position','C':'Candidate Name', 'T':'Interview Round Type','D':'Interview Date', 
						            			'I':'Interviewer','N':'Recruiter Name'}" />
					</td>
				</tr>
				<tr>
					<td width="17%" colspan="1" class="formtext">&nbsp;</td>
					<s:hidden name="sortByAsc" />
					<s:hidden name="sortByDsc" />
					<td width="83%" colspan="1"><input type="radio" value="A"
						name="sortByOrder" <s:property value="sortByAsc"/>>
					Ascending <input type="radio" value="D" name="sortByOrder"
						<s:property value="sortByDsc"/>> Descending</td>
				</tr>

				<tr>
					<td width="17%" colspan="1" class="formtext"><label
						class="set" id="thenByLabel" name="thenByLabel"
						ondblclick="callShowDiv(this);"><%=label.get("thenByLabel")%></label>
					:</td>
					<td width="83%" colspan="1"><s:select headerKey="1"
						headerValue="--Select--" name="thenBy1"
						list="#{'R':'Requisition Code','P':'Position','C':'Candidate Name', 'T':'Interview Round Type','D':'Interview Date', 
						            			'I':'Interviewer','N':'Recruiter Name'}" />
					</td>
				</tr>
				<tr>
					<td width="17%" colspan="1" class="formtext">&nbsp;</td>
					<s:hidden name="thenByOrder1Asc" />
					<s:hidden name="thenByOrder1Dsc" />
					<td width="83%" colspan="1"><input type="radio" value="A"
						name="thenByOrder1" <s:property value="thenByOrder1Asc"/>>
					Ascending <input type="radio" value="D" name="thenByOrder1"
						<s:property value="thenByOrder1Dsc"/>> Descending</td>
				</tr>
				<tr>
					<td width="17%" colspan="1" class="formtext"><label
						class="set" id="thenByLabel" name="thenByLabel"
						ondblclick="callShowDiv(this);"><%=label.get("thenByLabel")%></label>
					:</td>
					<td width="83%" colspan="1"><s:select headerKey="1"
						headerValue="--Select--" name="thenBy2"
						list="#{'R':'Requisition Code','P':'Position','C':'Candidate Name', 'T':'Interview Round Type','D':'Interview Date', 
						            			'I':'Interviewer','N':'Recruiter Name'}" />
					</td>
				</tr>
				<tr>
					<td width="17%" colspan="1" class="formtext">&nbsp; <s:hidden
						name="thenByOrder2Asc" /> <s:hidden name="thenByOrder2Dsc" /></td>
					<td width="83%" colspan="1"><input type="radio" value="A"
						name="thenByOrder2" <s:property value="thenByOrder2Asc"/>>
					Ascending <input type="radio" value="D" name="thenByOrder2"
						<s:property value="thenByOrder2Dsc"/>> Descending</td>
				</tr>
			</table>

			<table id="column" width="100%" border="0" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td width="100%" colspan="6" class="formtext"><b> <label
						class="set" id="selectCoumn" name="selectCoumn"
						ondblclick="callShowDiv(this);"><%=label.get("selectCoumn")%></label></b>
					</td>
				</tr>
				<tr>
					<td width="5%" colspan="1" class="formtext"><s:hidden
						name="candiCodeChk" id="candiCodeChk" value="on" /> <input
						type="checkbox" name="candiCodeChk1" id="candiCodeChk1"
						checked="checked" onclick="checkedCounter('candiCodeChk1');"
						disabled="disabled"></td>
					<td width="28%" colspan="1" class="formtext"><label
						class="set" id="cand.name12345" name="cand.name"
						ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label>
					</td>
					<td width="5%" colspan="1" class="formtext"><input
						type="checkbox" name="schDateChk" id="schDateChk"
						<s:property value="schDateChk"/>
						onclick="checkedCounter('schDateChk');"></td>
					<td width="28%" colspan="1" class="formtext"><label
						class="set" id="schDateChkLabel" name="schDateChkLabel"
						ondblclick="callShowDiv(this);"><%=label.get("schDateChkLabel")%></label>
					</td>
				</tr>


				<tr>
					<td width="5%" colspan="1" class="formtext"><input
						type="checkbox" name="emailIdChk" id="emailIdChk"
						<s:property value="emailIdChk" />
						onclick="checkedCounter('emailIdChk');"></td>
					<td width="28%" colspan="1" class="formtext"><label
						class="set" id="emailIDLabel" name="emailIDLabel"
						ondblclick="callShowDiv(this);"><%=label.get("emailIDLabel")%></label>
					</td>
					<td width="5%" colspan="1" class="formtext"><input
						type="checkbox" name="interviewVenueChk" id="interviewVenueChk"
						<s:property value="interviewVenueChk"/>
						onclick="checkedCounter('interviewVenueChk');"></td>
					<td width="28%" colspan="1" class="formtext"><label
						class="set" id="interviewVenueLabel" name="interviewVenueLabel"
						ondblclick="callShowDiv(this);"><%=label.get("interviewVenueLabel")%></label>
					</td>
				</tr>

				<tr>
					<td width="5%" colspan="1" class="formtext"><input
						type="checkbox" name="contactNumChk" id="contactNumChk"
						<s:property value="contactNumChk"/>
						onclick="checkedCounter('contactNumChk');"></td>
					<td width="28%" colspan="1" class="formtext"><label
						class="set" id="ContactLabel" name="ContactLabel"
						ondblclick="callShowDiv(this);"><%=label.get("ContactLabel")%></label>
					</td>
					<td width="5%" colspan="1" class="formtext"><input
						type="checkbox" name="interviewerChk" id="interviewerChk"
						<s:property value="interviewerChk"/>
						onclick="checkedCounter('interviewerChk');"></td>
					<td width="28%" colspan="1" class="formtext"><label
						class="set" id="interviewerChkLabel" name="interviewerChkLabel"
						ondblclick="callShowDiv(this);"><%=label.get("interviewerChkLabel")%></label>
					</td>
				</tr>


				<tr>
					<td width="5%" colspan="1" class="formtext"><input
						type="checkbox" name="roundTypeChk" id="postionChk"
						<s:property value="roundTypeChk" />
						onclick="checkedCounter('roundTypeChk');"></td>
					<td width="28%" colspan="1" class="formtext"><label
						class="set" id="Interview.Round" name="Interview.Round"
						ondblclick="callShowDiv(this);"><%=label.get("Interview.Round")%></label>
					</td>
					<td width="5%" colspan="1" class="formtext"><input
						type="checkbox" name="recruiterChk" id="recruiterChk"
						<s:property value="recruiterChk"/>
						onclick="checkedCounter('recruiterChk');"></td>
					<td width="28%" colspan="1" class="formtext"><label
						class="set" id="rec.name1" name="rec.name"
						ondblclick="callShowDiv(this);"><%=label.get("rec.name")%></label>
					</td>
				</tr>

				<tr>
					<td width="5%" colspan="1" class="formtext"><input
						type="checkbox" name="interviewDateChk" id="interviewDateChk"
						<s:property value="interviewDateChk"/>
						onclick="checkedCounter('interviewDateChk');"></td>
					<td width="28%" colspan="1" class="formtext"><label
						class="set" id="interviewDate" name="interviewDate"
						ondblclick="callShowDiv(this);"><%=label.get("interviewDate")%></label>
					</td>
					<td width="5%" colspan="1" class="formtext"><input
						type="checkbox" name="schStatusChk" id="schStatusChk"
						<s:property value="schStatusChk"/>
						onclick="checkedCounter('schStatusChk');"></td>
					<td width="28%" colspan="1" class="formtext"><label
						class="set" id="schStatusChkLabel" name="schStatusChkLabel"
						ondblclick="callShowDiv(this);"><%=label.get("schStatusChkLabel")%></label>
					</td>
				</tr>
				<!-- Added By Prajakta For Interview Status -->
				<tr>
					<td width="5%" colspan="1" class="formtext"><input
						type="checkbox" name="interviewStatusChk" id="interviewStatusChk"
						<s:property value="interviewStatusChk"/>
						onclick="checkedCounter('interviewStatusChk');"></td>
					<td width="28%" colspan="1" class="formtext"><label
						class="set" id="interviewStatus" name="interviewStatus"
						ondblclick="callShowDiv(this);"><%=label.get("interviewStatus")%></label>
					</td>
				</tr>
				<!--Ends Added By Prajakta For Interview Status -->


			</table>

			<table id="advance" width="100%" border="0" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td width="100%" colspan="3" class="formtext"><b> <label
						class="set" id="selectAdvanceFilter" name="selectAdvanceFilter"
						ondblclick="callShowDiv(this);"><%=label.get("selectAdvanceFilter")%></label>
					</b></td>
				</tr>
				<tr>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" id="schStatusChkLabel1" name="schStatusChkLabel"
						ondblclick="callShowDiv(this);"><%=label.get("schStatusChkLabel")%></label>
					:</td>
					<td width="80%" colspan="2" class="formtext"><s:select
						headerKey="1" headerValue="--Select--" name="schStatusCom"
						list="#{'N':'Scheduled','R':'Rescheduled','Y':'Conducted','C':'Canceled'}" />
					</td>

				</tr>
				<tr>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" id="interviewerChkLabel1" name="interviewerChkLabel"
						ondblclick="callShowDiv(this);"><%=label.get("interviewerChkLabel")%></label>
					:</td>
					<td width="22%" colspan="1" class="formtext"><s:textfield
						name="interviewerName" size="25" readonly="true" /></td>
					<td width="58%" colspan="1"><img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="15" align="absmiddle" width="16"
						onclick="javascript:callsF9(500,325,'InterviewAnalysis_f9Interviewer.action');">
					<s:hidden name="interviewerCode" /></td>
				</tr>

				<tr>
					<td width="20%" colspan="1" class="formtext"><label
						class="set" id="cand.name00011" name="cand.name"
						ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label>
					:</td>
					<td width="22%" colspan="1" class="formtext"><s:textfield
						name="candidateName" size="25" readonly="true" /></td>
					<td width="58%" colspan="1"><img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="15" align="absmiddle" width="16"
						onclick="javascript:callsF9(500,325,'InterviewAnalysis_f9Candidate.action');">
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">
				<tr>
					<td width="100%" colspan="2" class="formtext"><b>Display
					option</b></td>
				</tr>
				<tr>
					<td width="100%" colspan="2" class="formtext"><s:hidden
						name="hidReportView" /> <s:hidden name="hidReportRadio" /> <input
						type="radio" value="V" name="reportView" id="reportViewV"
						<s:property value="hidReportView"/> onclick="callReportChk('N');">
					<label class="set" id="view.screen" name="view.screen"
						ondblclick="callShowDiv(this);"><%=label.get("view.screen")%></label>
					</td>
				</tr>
				<tr>
					<td width="17%" colspan="1" class="formtext"><input
						type="radio" value="R" name="reportView" id="reportViewR"
						<s:property value="hidReportRadio"/> onclick="callReportChk('Y');">
					<label class="set" id="report.type" name="report.type"
						ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label>
					</td>
					<td width="83%" colspan="1" class="formtext">
					<div id="reportTypeDiv"><s:select headerKey="1"
						headerValue="--Select--" name="reportType"
						list="#{'P':'Pdf','X':'Xls' ,'T':'Doc'}" /></div>
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" colspan="1" class="formtext"><label
						class="set" id="setting.name" name="setting.name"
						ondblclick="callShowDiv(this);"><%=label.get("setting.name")%></label>
					:</td>
					<td width="83%" colspan="1"><s:textfield name="settingName"
						size="25" theme="simple" maxlength="40" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="100%" colspan="3"><s:if test="%{viewFlag}">
						<input type="button" class="token"
							onclick="return callReportVacancies();" value=" Generate Report" />
					</s:if> <s:submit cssClass="reset" action="InterviewAnalysis_reset"
						theme="simple" value="    Reset" /> <input type="button"
						class="token" value=" Save report criteria" onclick="callSave();" />
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>


<script type="text/javascript">    

function callRadioOptionFun(id)
{
if(id=="C")
	{
	  callF9ReqCode('radio');
	}
	if(id=="H")
	{
	  callF9HiringMgr('radio');
	}
	
 
	if(id=="P")
	{
	  callF9Position('radio');
	}
	document.getElementById('paraFrm_radioStatus').value=id;
	
}
 callMeOnload(); 
function callMeOnload()
{ 
   
  radioStatus =document.getElementById('paraFrm_radioStatus').value;
  callRadioOptionFun(radioStatus); 
  if(radioStatus=="")
   {
   document.getElementById('f9ReqCode').style.display='none'; 
   document.getElementById('f9HiringMgrRowId').style.display='none'; 
   document.getElementById('f9Position').style.display='none'; 
  } 
  if(radioStatus=="C")
  { 
   document.getElementById('paraFrm_radioReq').value="checked";
  }  
    if(radioStatus=="H")
  { 
   document.getElementById('paraFrm_radioHiringMgr').value="checked";
  } 
    if(radioStatus=="P")
  { 
   document.getElementById('paraFrm_radioPosition').value="checked";
  }
  
  
   var fieldName =["paraFrm_frmDate","paraFrm_toDate"];
   setTextArray(fieldName,"dd-mm-yyyy");
   var intFieldName=["paraFrm_intFrmDate","paraFrm_intToDate"];
   setTextArray(intFieldName,"dd-mm-yyyy");
  var settingVal = document.getElementById('paraFrm_searchSetting').value;
  var dateFilter = document.getElementById('paraFrm_dateFilter').value;
  var intDateFilter= document.getElementById('paraFrm_intDateFilter').value;
  var hidReportRadio = document.getElementById('paraFrm_hidReportRadio').value;   
  var divCount = document.getElementById('paraFrm_divCount').value;    
  var selectedReqName =  document.getElementById('paraFrm_selectedReqName').value ; 
  var backFlag =   document.getElementById('paraFrm_backFlag').value;
     if((settingVal!="B" ||backFlag=='true') && selectedReqName!="" )
	 {   
	  document.getElementById('textAreaId1').style.display='';    
    }else{
       document.getElementById('textAreaId1').style.display='none';  
    } 
	if(dateFilter=="F")
	 { 
	   document.getElementById('toDateDiv').style.display=''; 
	   document.getElementById('toDateDivLabel').style.display='';     
	 }else{
	   document.getElementById('toDateDiv').style.display='none'; 
	   document.getElementById('toDateDivLabel').style.display='none';    
	 }
	 	if(intDateFilter=="F")
	 { 
	   document.getElementById('intToDateDiv').style.display=''; 
	   document.getElementById('intToDateDivLabel').style.display='';     
	 }else{
	   document.getElementById('intToDateDiv').style.display='none'; 
	   document.getElementById('intToDateDivLabel').style.display='none';    
	 }
	 
	 
	 if(hidReportRadio=="checked")
	 { 
	  document.getElementById('reportTypeDiv').style.display=''; 
	 }else{
	  document.getElementById('reportTypeDiv').style.display='none'; 
	 }
}


  callOnBackTab();
 function callOnBackTab()
 {  
   
 var backFlag =   document.getElementById('paraFrm_backFlag').value;
 var aId =   document.getElementById('paraFrm_aId').value;
 var tabId =   document.getElementById('paraFrm_divId').value;  
  if(backFlag=='true'){ 
   callTab(aId,tabId);
   document.getElementById('paraFrm_backFlag').value="false";
   } else{
    callOnLoad(); 
   }
   var reqCode = document.getElementById('paraFrm_reqCode').value;
   var hiringMgrCode = document.getElementById('paraFrm_hiringMgrCode').value; 
   var positionId = document.getElementById('paraFrm_positionId').value;
  
   if(reqCode!=""){
   
     callF9ReqCode('radio');
   } 
   if(hiringMgrCode!=""){
     callF9HiringMgr('radio');
   } 
   
   if(positionId!=""){
    callF9Position('radio');
   }
   callToDateDisp();
   
 } 

 
 function callOnLoad()
 {
   document.getElementById('filterDisp').style.display='';
   document.getElementById('sort').style.display='none'; 
   document.getElementById('column').style.display='none';  
   document.getElementById('advance').style.display='none';    
   
    document.getElementById('filtOpt').className = '';
	document.getElementById('sortOpt').className = '';
	document.getElementById('colDef').className = '';
	document.getElementById('advFilter').className = '';
	document.getElementById('filtOpt').className = 'on';
	document.getElementById('paraFrm_aId').value =  'filtOpt';
	document.getElementById('paraFrm_divId').value = 'filter'; 
   
 }

function callShowRecord()
{ 
var settingVal = document.getElementById('paraFrm_searchSetting').value;
if(settingVal=="B")
{
 document.getElementById('paraFrm').action="InterviewAnalysis_reset.action";
}else{
 document.getElementById('paraFrm').action="InterviewAnalysis_viewSavedRecord.action";
 }
 document.getElementById('paraFrm').submit();
}

function callSave()
{    
   
    var settingVal = document.getElementById('paraFrm_searchSetting').value;
    if(settingVal=="B")
    { var settingName = document.getElementById('paraFrm_settingName').value;
      if(LTrim(settingName)=="")
      {
        alert("Please enter the "+document.getElementById('setting.name').innerHTML.toLowerCase());
	    document.getElementById('paraFrm_settingName').focus();
	    return false; 	 
      } 
    }
    
    // filter on tab
    
	       var dateFilter = document.getElementById('paraFrm_dateFilter').value;
	       var frmDate = document.getElementById('paraFrm_frmDate').value;
	        var reqCode = document.getElementById('paraFrm_reqCode').value;
	      var intDateFilter=document.getElementById('paraFrm_intDateFilter').value;
	       var intFrmDate = document.getElementById('paraFrm_intFrmDate').value;
	    if(reqCode=="")
	       {
		      if(dateFilter!="1")
		      { 
		        if(frmDate=="dd-mm-yyyy")
		         { 
		          callTab('filtOpt','filter');
		         alert("Please enter/select the "+document.getElementById('reqs.date').innerHTML.toLowerCase()); 
		          clearText("paraFrm_frmDate","dd-mm-yyyy");
			     document.getElementById('paraFrm_frmDate').focus();
			     return false; 	 
		        }   
		       if(!validateDate('paraFrm_frmDate', 'reqs.date'))
		        {  return false;
		        }
		        if(dateFilter=="F")
		         {
		           
		           var toDate = document.getElementById('paraFrm_toDate').value;  
		              clearText("paraFrm_toDate","dd-mm-yyyy");
			         if(!validateDate('paraFrm_toDate', 'toDateLabel'))
		             {  return false;
		               }
			         callTab('filtOpt','filter');
		             if(!dateDifferenceEqual(frmDate,toDate,"paraFrm_toDate",'reqs.date','toDateLabel'))
		           {
		            return false;
		           }
		          setText("paraFrm_toDate","dd-mm-yyyy");
		         }
		     }
		     
		     if(intDateFilter!="1")
		      { 
		        if(frmDate=="dd-mm-yyyy")
		         { 
		          callTab('filtOpt','filter');
		         alert("Please enter/select the "+document.getElementById('int.date').innerHTML.toLowerCase()); 
		          clearText("paraFrm_intFrmDate","dd-mm-yyyy");
			     document.getElementById('paraFrm_intFrmDate').focus();
			     return false; 	 
		        }   
		       if(!validateDate('paraFrm_intFrmDate', 'int.date'))
		        {  return false;
		        }
		        if(intDateFilter=="F")
		         {
		           
		           var toDate = document.getElementById('paraFrm_intToDate').value;  
		              clearText("paraFrm_intToDate","dd-mm-yyyy");
			         if(!validateDate('paraFrm_intToDate', 'intToDateLabel'))
		             {  return false;
		               }
			         callTab('filtOpt','filter');
		             if(!dateDifferenceEqual(frmDate,toDate,"paraFrm_intToDate",'int.date','intToDateLabel'))
		           {
		            return false;
		           }
		          setText("paraFrm_intToDate","dd-mm-yyyy");
		         }
		     }
     	}	    
     	     		    
    // for sort the filter   
   	  var sortBy = document.getElementById('paraFrm_sortBy').value;
   	  var thenBy1 = document.getElementById('paraFrm_thenBy1').value;
   	  var thenBy2 = document.getElementById('paraFrm_thenBy2').value; 
   	  
   	  if(sortBy!="1" &&thenBy1!="1"){
   	    if(sortBy==thenBy1){ 
   	      callTab('sortOpt','sort');
   	     alert("Duplicate sorting option."); 
   	     document.getElementById('paraFrm_thenBy1').focus();
   	     return false;
   	    } 
   	  }
   	  
   	  if(sortBy!="1" &&thenBy2!="1"){
   	    if(sortBy==thenBy2){ 
   	      callTab('sortOpt','sort');
   	     alert("Duplicate sorting option."); 
   	     document.getElementById('paraFrm_thenBy2').focus();
   	     return false;
   	    } 
   	  }
   	  
   	    if(thenBy1!="1" &&thenBy2!="1"){
   	    if(thenBy1==thenBy2){ 
   	      callTab('sortOpt','sort');
   	     alert("Duplicate sorting option."); 
   	     document.getElementById('paraFrm_thenBy2').focus();
   	     return false;
   	    } 
   	  } 
   	  // == end of sort======  
	  
	var fieldName =["paraFrm_frmDate","paraFrm_toDate"];
      clearTextArray(fieldName,"dd-mm-yyyy");
    var intfieldName =["paraFrm_intFrmDate","paraFrm_intToDate"];
      clearTextArray(intfieldName,"dd-mm-yyyy"); 
   document.getElementById('paraFrm').action="InterviewAnalysis_save.action";
   document.getElementById('paraFrm').submit();
   document.getElementById('paraFrm').target="main";
}
function checkedCounter(fieldName)
{ 
	var checkedCount =  document.getElementById('paraFrm_checkedCount').value; 
	
	 if(checkedCount=="" || checkedCount=='null')
	 {
	  checkedCount="0";
	 }else{  
		 if(document.getElementById(fieldName).checked==true){ 
		 checkedCount= eval(eval(checkedCount)+1); 
		 document.getElementById('paraFrm_checkedCount').value =checkedCount;
		 }else{ 
		checkedCount= eval(eval(checkedCount)-1);
		 document.getElementById('paraFrm_checkedCount').value =checkedCount;
		 }
	 }
	 
}
function callF9ReqCode(status) {

   document.getElementById('f9ReqCode').style.display=''; 
   document.getElementById('f9HiringMgrRowId').style.display='none'; 
   document.getElementById('f9Position').style.display='none'; 
   document.getElementById('selectReqDiv').style.display='none';   
   document.getElementById('textAreaId1').style.display='none';    
   document.getElementById('fromDateIcon').style.display='none'; 
   document.getElementById('paraFrm_frmDate').readOnly='true'; 
   document.getElementById('toDateDiv').style.display='none'; 
   document.getElementById('toDateDivLabel').style.display='none';  
   document.getElementById('paraFrm_dateFilter').value='1';
   document.getElementById('paraFrm_frmDate').value="";
   document.getElementById('paraFrm_toDate').value=""; 
    
   document.getElementById('paraFrm_selectedReqName').value="";
   document.getElementById('paraFrm_selectedReq').value=""; 
    document.getElementById('paraFrm_hiringMgrName').value="";
   document.getElementById('paraFrm_hiringMgrCode').value="";   
   document.getElementById('paraFrm_positionId').value=""; 
   document.getElementById('paraFrm_position').value=""; 
    var intDateFilter= document.getElementById('paraFrm_intDateFilter').value;
   if(intDateFilter=="F"){
     document.getElementById('intToDateDiv').style.display=''; 
     document.getElementById('intToDateDivLabel').style.display='';    
   }else {
     document.getElementById('intToDateDiv').style.display='none'; 
     document.getElementById('intToDateDivLabel').style.display='none';       
   }
    
      if(status=='selF9'){ 
   callsF9(500,325,'InterviewAnalysis_f9actionReqName.action'); 
   }
}

 

 
 
 
 function callF9HiringMgr(status)
{ 
   document.getElementById('paraFrm_reqname').value="";
   document.getElementById('paraFrm_reqCode').value="";   
   document.getElementById('f9Position').style.display='none'; 
   document.getElementById('f9HiringMgrRowId').style.display='';  
   document.getElementById('f9ReqCode').style.display='none';  
   document.getElementById('selectReqDiv').style.display='';   
    var settingVal = document.getElementById('paraFrm_searchSetting').value; 
    var selectedReqName =  document.getElementById('paraFrm_selectedReqName').value ;
    var backFlag =   document.getElementById('paraFrm_backFlag').value;
     if((settingVal!="B" ||backFlag=='false') && selectedReqName!="" )
	 {  
	  document.getElementById('textAreaId1').style.display='';    
    }else{
       document.getElementById('textAreaId1').style.display='none';  
    }
   document.getElementById('fromDateIcon').style.display=''; 
   document.getElementById('IntFromDateIcon').style.display=''; 
   document.getElementById('paraFrm_frmDate').readOnly=''; 
   document.getElementById('paraFrm_intFrmDate').readOnly='';   
   document.getElementById('paraFrm_positionId').value=""; 
   document.getElementById('paraFrm_position').value="";   
   
 var dateFilter= document.getElementById('paraFrm_dateFilter').value;
   if(dateFilter=="F"){
     document.getElementById('toDateDiv').style.display=''; 
     document.getElementById('toDateDivLabel').style.display='';    
   }else {
     document.getElementById('toDateDiv').style.display='none'; 
     document.getElementById('toDateDivLabel').style.display='none';       
   }
   var intDateFilter= document.getElementById('paraFrm_intDateFilter').value;
   if(intDateFilter=="F"){
     document.getElementById('intToDateDiv').style.display=''; 
     document.getElementById('intToDateDivLabel').style.display='';    
   }else {
     document.getElementById('intToDateDiv').style.display='none'; 
     document.getElementById('intToDateDivLabel').style.display='none';       
   }
    
   if(status=='selF9'){
   callsF9(500,325,'InterviewAnalysis_f9HiringManger.action');
   }
} 


function callF9Position(status)
{ 
    document.getElementById('paraFrm_reqname').value="";
    document.getElementById('paraFrm_reqCode').value="";  
   document.getElementById('f9HiringMgrRowId').style.display='none'; 
   document.getElementById('paraFrm_hiringMgrName').value="";
   document.getElementById('paraFrm_hiringMgrCode').value="";  
   document.getElementById('f9Position').style.display=''; 
   document.getElementById('f9ReqCode').style.display='none';  
   document.getElementById('selectReqDiv').style.display=''; 
    
    var settingVal = document.getElementById('paraFrm_searchSetting').value; 
    var selectedReqName =  document.getElementById('paraFrm_selectedReqName').value ;
    var backFlag =   document.getElementById('paraFrm_backFlag').value;
     if((settingVal!="B" ||backFlag=='false') && selectedReqName!="" )
	 {  
	  document.getElementById('textAreaId1').style.display='';    
    }else{
       document.getElementById('textAreaId1').style.display='none';  
    } 
   document.getElementById('fromDateIcon').style.display=''; 
   document.getElementById('IntFromDateIcon').style.display=''; 
   document.getElementById('paraFrm_frmDate').readOnly='';    
   document.getElementById('paraFrm_intFrmDate').readOnly=''; 
 var dateFilter= document.getElementById('paraFrm_dateFilter').value;
   if(dateFilter=="F"){
     document.getElementById('toDateDiv').style.display=''; 
     document.getElementById('toDateDivLabel').style.display='';    
   }else {
     document.getElementById('toDateDiv').style.display='none'; 
     document.getElementById('toDateDivLabel').style.display='none';       
   } 
   var intDateFilter= document.getElementById('paraFrm_intDateFilter').value;
   if(intDateFilter=="F"){
     document.getElementById('intToDateDiv').style.display=''; 
     document.getElementById('intToDateDivLabel').style.display='';    
   }else {
     document.getElementById('intToDateDiv').style.display='none'; 
     document.getElementById('intToDateDivLabel').style.display='none';       
   }
   if(status=='selF9'){
   callsF9(500,325,'InterviewAnalysis_f9Position.action');
   }
} 

  function callToDateDisp()
 {
   var dateFilter= document.getElementById('paraFrm_dateFilter').value;
   var  reqCode = document.getElementById('paraFrm_reqCode').value;
   if(dateFilter=="F"){
    if(reqCode==""){
     document.getElementById('toDateDiv').style.display=''; 
     document.getElementById('toDateDivLabel').style.display='';   
     }
   }else {
     document.getElementById('toDateDiv').style.display='none'; 
     document.getElementById('toDateDivLabel').style.display='none';       
   } 
   
    var intDateFilter= document.getElementById('paraFrm_intDateFilter').value;
   if(intDateFilter=="F"){
   if(reqCode==""){
     document.getElementById('intToDateDiv').style.display=''; 
     document.getElementById('intToDateDivLabel').style.display='';    
   }
   }else {
     document.getElementById('intToDateDiv').style.display='none'; 
     document.getElementById('intToDateDivLabel').style.display='none';       
   }
 }
 
   function callToDateDispOnClick()
 {
   var dateFilter= document.getElementById('paraFrm_dateFilter').value;
   var  reqCode = document.getElementById('paraFrm_reqCode').value;
   document.getElementById('paraFrm_toDate').value="";  
   if(dateFilter=="F"){
    if(reqCode==""){
     document.getElementById('toDateDiv').style.display=''; 
     document.getElementById('toDateDivLabel').style.display='';  
   //  document.getElementById('paraFrm_toDate').value="";  
     }
      setText("paraFrm_toDate","dd-mm-yyyy");
   }else {
   
      setText("paraFrm_toDate","dd-mm-yyyy");
     document.getElementById('toDateDiv').style.display='none'; 
     document.getElementById('toDateDivLabel').style.display='none';       
   } 
 }
 
 function callToDateDispOnClick1()
 {
   var intDateFilter= document.getElementById('paraFrm_intDateFilter').value;
   var  reqCode = document.getElementById('paraFrm_reqCode').value;
   document.getElementById('paraFrm_intToDate').value="";  
   if(intDateFilter=="F"){
    if(reqCode==""){
     document.getElementById('intToDateDiv').style.display=''; 
     document.getElementById('intToDateDivLabel').style.display='';  
     }
      setText("paraFrm_intToDate","dd-mm-yyyy");
   }else {
      setText("paraFrm_intToDate","dd-mm-yyyy");
     document.getElementById('intToDateDiv').style.display='none'; 
     document.getElementById('intToDateDivLabel').style.display='none';       
   } 
 }
 
  function callAdvCombo(filedName,resetFiled)
  {
  filedClick = document.getElementById('paraFrm_'+filedName).value;
  resetFiledName= document.getElementById('paraFrm_'+resetFiled).value;
   if(filedClick=="1") 
   {
    document.getElementById('paraFrm_'+resetFiled).value="";
   }
  }
 
 
 
 function callReportChk(status)
 { 
	 if(status=="Y"){
	   document.getElementById('reportTypeDiv').style.display=''; 
	   document.getElementById('paraFrm_reqStatus').value ="R"; 
	  }else{
	   document.getElementById('reportTypeDiv').style.display='none';  
	   document.getElementById('paraFrm_reqStatus').value ="V"; 
	  }
 }

 function callTab(aId,tabId)
 { 
   
        document.getElementById('filtOpt').className = ''; 
		document.getElementById('sortOpt').className = ''; 
		document.getElementById('colDef').className = ''; 
		document.getElementById('advFilter').className = ''; 
		document.getElementById(aId).className = 'on'; 
		document.getElementById('paraFrm_aId').value =  aId; 
		document.getElementById('paraFrm_divId').value = tabId; 
     document.getElementById('paraFrm_divCount').value ="someThing"; 
	  if(tabId=="filter")
	  {   
	   document.getElementById('filterDisp').style.display='';
	   document.getElementById('sort').style.display='none'; 
	   document.getElementById('column').style.display='none';  
	   document.getElementById('advance').style.display='none';  
	  }
	   
	  if(tabId=="sort")
	  {   
	   document.getElementById('sort').style.display=''; 
	   document.getElementById('filterDisp').style.display='none'; 
	   document.getElementById('column').style.display='none';  
	   document.getElementById('advance').style.display='none';    
	  }
	  
	   if(tabId=="column")
	   { 
	   document.getElementById('column').style.display='';   
	   document.getElementById('sort').style.display='none';
	   document.getElementById('filterDisp').style.display='none'; 
	   document.getElementById('advance').style.display='none';  
	  } 
	  
	   if(tabId=="advance")
	   { 
	   document.getElementById('advance').style.display='';  
	   document.getElementById('column').style.display='none';   
	   document.getElementById('sort').style.display='none';
	   document.getElementById('filterDisp').style.display='none';  
	   
	  
	 
	  }
	     
 }

function callReportVacancies()
 { 	     	document.getElementById('paraFrm_screenFlag').value="true";
			document.getElementById('paraFrm_candidateFlag').value="false";
    var reqCode= document.getElementById('paraFrm_reqCode').value; 
    if(reqCode!=""){
       document.getElementById('paraFrm_dateFilter').value="1";
     }  
	 
	  // filter on tab
    
	       var dateFilter = document.getElementById('paraFrm_dateFilter').value;
	       var intDateFilter=document.getElementById('paraFrm_intDateFilter').value;
	       var frmDate = document.getElementById('paraFrm_frmDate').value;
	       var intFrmDate=document.getElementById('paraFrm_intFrmDate').value;
	      
	      if(dateFilter!="1")
	      {   
	        if(frmDate=="dd-mm-yyyy")
	         {
	          callTab('filtOpt','filter');
	         alert("Please enter/select the "+document.getElementById('reqs.date').innerHTML.toLowerCase());
	          clearText("paraFrm_frmDate","dd-mm-yyyy");
		     document.getElementById('paraFrm_frmDate').focus();
		     return false; 	 
	        }  
	         if(!validateDate('paraFrm_frmDate', 'reqs.date'))
	            {  return false;
	              } 
	        if(dateFilter=="F")
	         { 
	           var toDate = document.getElementById('paraFrm_toDate').value; 
	           clearText("paraFrm_toDate","dd-mm-yyyy");
		       if(!validateDate('paraFrm_toDate', 'toDateLabel'))
	            {  return false;
	              }  
		      //  alert(dateDifferenceEqual(frmDate,toDate,"paraFrm_toDate",'fDate','tDate'));
		      callTab('filtOpt','filter');  
	           if(!dateDifferenceEqual(frmDate,toDate,"paraFrm_toDate",'reqs.date','toDateLabel'))
	           {
	            return false;
	           }
	             setText("paraFrm_toDate","dd-mm-yyyy");
	         }
	     }
	     
	     if(intDateFilter!="1")
	      {   
	        if(intFrmDate=="dd-mm-yyyy")
	         {
	          callTab('filtOpt','filter');
	         alert("Please enter/select the "+document.getElementById('int.date').innerHTML.toLowerCase());
	          clearText("paraFrm_intFrmDate","dd-mm-yyyy");
		     document.getElementById('paraFrm_intFrmDate').focus();
		     return false; 	 
	        }  
	         if(!validateDate('paraFrm_intFrmDate', 'int.date'))
	            {  return false;
	              } 
	        if(intDateFilter=="F")
	         { 
	           var intToDate = document.getElementById('paraFrm_intToDate').value; 
	           clearText("paraFrm_intToDate","dd-mm-yyyy");
		       if(!validateDate('paraFrm_intToDate', 'intToDateLabel'))
	            {  return false;
	              }  
		      //  alert(dateDifferenceEqual(frmDate,toDate,"paraFrm_toDate",'fDate','tDate'));
		      callTab('filtOpt','filter');  
	           if(!dateDifferenceEqual(intFrmDate,intToDate,"paraFrm_intToDate",'int.date','intToDateLabel'))
	           {
	            return false;
	           }
	             setText("paraFrm_intToDate","dd-mm-yyyy");
	         }
	     }
	     
	     
	     
	     
	  // for sort the filter   
   	  var sortBy = document.getElementById('paraFrm_sortBy').value;
   	  var thenBy1 = document.getElementById('paraFrm_thenBy1').value;
   	  var thenBy2 = document.getElementById('paraFrm_thenBy2').value; 
   	  
   	  if(sortBy!="1" &&thenBy1!="1"){
   	    if(sortBy==thenBy1){ 
   	      callTab('sortOpt','sort');
   	     alert("Duplicate sorting option."); 
   	     document.getElementById('paraFrm_thenBy1').focus();
   	     return false;
   	    } 
   	  }
   	  
   	  if(sortBy!="1" &&thenBy2!="1"){
   	    if(sortBy==thenBy2){ 
   	      callTab('sortOpt','sort');
   	     alert("Duplicate sorting option."); 
   	     document.getElementById('paraFrm_thenBy2').focus();
   	     return false;
   	    } 
   	  }
   	  
   	    if(thenBy1!="1" &&thenBy2!="1"){
   	    if(thenBy1==thenBy2){ 
   	      callTab('sortOpt','sort');
   	     alert("Duplicate sorting option."); 
   	     document.getElementById('paraFrm_thenBy2').focus();
   	     return false;
   	    } 
   	  } 
   	  // == end of sort======
   	  
     		    
    
	   
var fieldName =["paraFrm_frmDate","paraFrm_toDate"];
      clearTextArray(fieldName,"dd-mm-yyyy");
      
var intFieldName=["paraFrm_intFrmDate","paraFrm_intToDate"];
clearTextArray(intFieldName,"dd-mm-yyyy");
	if(document.getElementById("reportViewV").checked)
	{
	 document.getElementById('paraFrm').action="InterviewAnalysis_viewOnScreen.action";
     document.getElementById('paraFrm').submit();
	}else{	
	   var reportType = document.getElementById("paraFrm_reportType").value; 
	   if(reportType=="1"){
	    alert("Please select "+document.getElementById('report.type').innerHTML.toLowerCase());
	    document.getElementById("paraFrm_reportType").focus();
	   return false;
	   }
  	  callReport('InterviewAnalysis_report.action');   		
  	}
 }
 
 function callSelectReq(status)
 {
      // filter on tab 
       var dateFilter = document.getElementById('paraFrm_dateFilter').value;
       var frmDate = document.getElementById('paraFrm_frmDate').value; 
       var intDateFilter=document.getElementById('paraFrm_intDateFilter').value;
	  	var intFrmDate=document.getElementById('paraFrm_intFrmDate').value;
	      
	      if(dateFilter!="1")
	      { 
	        if(frmDate=="dd-mm-yyyy")
	         { 
	          callTab('filtOpt','filter');
	         alert("Please enter/select  the "+document.getElementById('reqs.date').innerHTML.toLowerCase()); 
	          clearText("paraFrm_frmDate","dd-mm-yyyy");
		     document.getElementById('paraFrm_frmDate').focus();
		     return false; 	 
	        }   
	       if(!validateDate('paraFrm_frmDate', 'reqs.date'))
	        {  return false;
	        }
	        if(dateFilter=="F")
	         {
	           
	           var toDate = document.getElementById('paraFrm_toDate').value; 
		        clearText("paraFrm_toDate","dd-mm-yyyy");
		         if(!validateDate('paraFrm_toDate', 'toDateLabel'))
	             {  return false;
	               }
		         callTab('filtOpt','filter');
	             if(!dateDifferenceEqual(frmDate,toDate,"paraFrm_toDate",'reqs.date','toDateLabel'))
	           {
	            return false;
	           }
	             setText("paraFrm_toDate","dd-mm-yyyy");
	         }
	     }
	     
	     if(intDateFilter!="1")
	      {   
	        if(intFrmDate=="dd-mm-yyyy")
	         {
	          callTab('filtOpt','filter');
	         alert("Please enter/select the "+document.getElementById('int.date').innerHTML.toLowerCase());
	          clearText("paraFrm_intFrmDate","dd-mm-yyyy");
		     document.getElementById('paraFrm_intFrmDate').focus();
		     return false; 	 
	        }  
	         if(!validateDate('paraFrm_intFrmDate', 'int.date'))
	            {  return false;
	              } 
	        if(intDateFilter=="F")
	         { 
	           var intToDate = document.getElementById('paraFrm_intToDate').value; 
	           clearText("paraFrm_intToDate","dd-mm-yyyy");
		       if(!validateDate('paraFrm_intToDate', 'intToDateLabel'))
	            {  return false;
	              }  
		      //  alert(dateDifferenceEqual(frmDate,toDate,"paraFrm_toDate",'fDate','tDate'));
		      callTab('filtOpt','filter');  
	           if(!dateDifferenceEqual(intFrmDate,intToDate,"paraFrm_intToDate",'int.date','intToDateLabel'))
	           {
	            return false;
	           }
	             setText("paraFrm_intToDate","dd-mm-yyyy");
	         }
	     }
	     
   document.getElementById('f9ReqCode').style.display='none'; 
  if(status=='edit'){
 document.getElementById('paraFrm_selectedReqName').value =document.getElementById('paraFrm_hidSelectedReqName').value;
       document.getElementById('paraFrm_editReqFlag').value="true";    
  }else{
   document.getElementById('paraFrm_editReqFlag').value="false"; 
   document.getElementById('paraFrm_selectedReqName').value="";
   document.getElementById('paraFrm_selectedReq').value=""; 
  }  
   document.getElementById('paraFrm').target="wind";
   var wind = window.open('','wind','width=700,height=450,scrollbars=yes,resizable=no,status=yes,menubar=no,top=200,left=150');	 
   document.getElementById('paraFrm').action="InterviewAnalysis_displayReq.action";
   document.getElementById('paraFrm').submit();
   document.getElementById('paraFrm').target="";
 }
 
</script>
