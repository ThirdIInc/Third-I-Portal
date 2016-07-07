<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<link rel="stylesheet" type="text/css"
	href="../pages/common/tabcontent.css" />
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>
<s:form action="ManpowerRequisitionAnalysis" method="post" id="paraFrm"
	theme="simple">
	<s:hidden name='aId' />
	<s:hidden name='divId' />
	<s:hidden name='backFlag' />
	<s:hidden name="hidSelectedReqName" />
	<s:hidden name="selectedReq" />
	<s:hidden name="hidFirstAsc" />
	<s:hidden name="hdrOpenVac" />
	<s:hidden name="itrVacOpen" />
	<s:hidden name="hidFirstDesc" />
	<s:hidden name="hidSecAsc" />
	<s:hidden name="hidSecDesc" />
	<s:hidden name="hdrNoOfVacFlg" />
	<s:hidden name="itrNoOfVacFlg" />
	<s:hidden name="hdrReqByDt" />
	<s:hidden name="itrReqByFlgDt" />
	<s:hidden name="hdrOvrFlg" />
	<s:hidden name="itrOvrcFlg" />
	<s:hidden name="hdrVacStatFlg" />
	<s:hidden name="itrVacStatFlg" />
	<s:hidden name="myPage" />
	<s:hidden name="listChk" />
	<s:hidden name="itrCloseVacFlag" />
	<s:hidden name="hidThirdAsc" />
	<s:hidden name="hidThirdDesc" />
	<s:hidden name="hdrReqFlg" />
	<s:hidden name="noData" />
	<s:hidden name="reqsnCode" />
	<s:hidden name="mraRepCode" />
	<s:hidden name="itrReqFlag" />
	<s:hidden name="exportAllData" />
	<s:hidden name="positionId" />
	<s:hidden name="reportFlag" />
	<s:hidden name="headerChk" />
	<s:hidden name="scrnReqCode" />
	<s:hidden name="totRecord" />
	<s:hidden name="editReqFlag" />
	<s:hidden name="editVal" />
	<s:hidden name="radioFlag" />
	<s:hidden name="selectedReqFlag" />
	<s:hidden name="dateChk" />
	<s:hidden name="reqChk" />
	<s:hidden name="closeChk" />
	<s:hidden name="vacChk" />
	<s:hidden name="overChk" />
	<s:hidden name="openChk" />
	<s:hidden name="exportAll1" />
	<s:hidden name="positionChk" />
	<s:hidden name="statusChk" />
	<s:hidden name="apprvChk" />
	<s:hidden name="itrReqsStatus" />
	<s:hidden name="irtApprvStatus" />
	<s:hidden name="hdrPosFlag" />
	<s:hidden name="itrPosFlag" />
	<s:hidden name="hdrReqsStatFlag" />
	<s:hidden name="itrReqsStatFlag" />
	<s:hidden name="hdrApprvStatFlag" />
	<s:hidden name="itrApprvStatFlag" />
	<s:hidden name="viewReqsDate" />
	<s:hidden name="totalCtcChk" />
	<s:hidden name="hiringMngrChk" />
	<s:hidden name="apprvrNameChk"></s:hidden>
	<s:hidden name="recruitNameChk"></s:hidden>
	<s:hidden name="closedDateChk" />
	<s:hidden name="hdrTotalCtcFlag" />
	<s:hidden name="itrTotalCtcFlag" />
	<s:hidden name="hdrHrngMngrFlag" />
	<s:hidden name="itrHrngMngrFlag" />
	<s:hidden name="hdrRecruitNameFlag" />
	<s:hidden name="itrRecruitNameFlag" />
	<s:hidden name="hdrApprvrNameFlag" />
	<s:hidden name="itrApprvrNameFlag" />
	<s:hidden name="hdrClosedDateFlag" />
	<s:hidden name="itrClosedDateFlag" />
	<s:hidden name="showReqsnFlag" />
	<s:hidden name="reqsnFlg" />
	<s:hidden name="positionFlg" />
	<table width="100%" border="0" align="right" cellpadding="1"
		cellspacing="2" class="formbg">
		<tr>
			<s:hidden name="checkFlag" />
			<s:hidden name="firstRadio" />
			<s:hidden name="secondRadio" />
			<s:hidden name="thirdRadio" />
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/common/css/default/images/review_shared.gif"
						width="25" height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Manpower
					Requisition Analysis</strong></td>
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
						<input type="button" class="token" onclick="return callReport();"
							value=" Generate Report" />
					</s:if> <input type="button" class="reset" theme="simple" value=" Reset"
						onclick="callReset();" /> <input type="button" class="token"
						value="Save Report Criteria" onclick="callSave();" /></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="22%"><label class="set" id="save.setting"
						name="save.setting" ondblclick="callShowDiv(this);"><%=label.get("save.setting")%></label>
					:</td>
					<td width="78%"><s:select headerKey="-1"
						headerValue="--Select--" name="searchSetting" list="%{hashMap}"
						onchange="callVal();" /></td>
				</tr>
			</table>
			</td>
		</tr>


		<tr valign="top">
			<td width="100%" class="formbg">
			<table width="100%" class="formbg" border="0" cellpadding="0"
				cellspacing="0">
				<div id="tabnav">


				<ul>
					<li><a id="filtOpt" href="javascript:callTab('filter');">
					<div align="center"><span>Filter </span></div>
					</a></li>

				</ul>

				<ul>
					<li><a id="sortOpt" href="javascript:callTab('sort');">
					<div align="center"><span>Sorting Option</span></div>
					</a></li>

				</ul>

				<ul>
					<li><a id="colDef" href="javascript:callTab('column');">
					<div align="center"><span>Column Definition</span></div>
					</a></li>

				</ul>


				<ul>
					<li><a id="advFilter" href="javascript:callTab('advance');">
					<div align="center"><span>Advanced Filter</span></div>
					</a></li>

				</ul>
				</div>


				<tr valign="top">
					<!-- Filter Tab Starts -->
					<td colspan="3" width="100%">
					<div id="filterOption">
					<table width="100%" border="0">
						<tr>
							<td width="100%"><b><label class="set" id="selectFilter"
								name="selectFilter" ondblclick="callShowDiv(this);"><%=label.get("selectFilter")%></label></b></td>
						</tr>

						<tr>
							<td width="100%">
							<table width="100%" border="0">
								<tr>
									<td width="23%" class="formtext" nowrap="nowrap"><input
										type="radio" name="reqsnRadio" id="reqsnRadio"
										onclick="callRadio('reqsn');"><label class="set"
										id="reqs.name" name="reqs.code"
										ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label>
									:</td>
									<td width="9%" colspan="1" nowrap="nowrap"><s:textfield
										name="reqsnName" size="25" theme="simple" readonly="true" /></td>
									<td id="mpreqsnId" width="3%" colspan="1"><img
										src="../pages/images/recruitment/search2.gif" height="15"
										align="absmiddle" width="16" onclick="return callReqsn();">
									</td>
									<td width="10%"></td>
									<td width="52%"></td>
								</tr>



								<tr>
									<td width="23%" class="formtext"><input type="radio"
										name="positionRadio" id="positionRadio"
										onclick="callRadio('position');"> <label class="set"
										id="position" name="position" ondblclick="callShowDiv(this);"><%=label.get("position")%></label>
									:</td>
									<td width="9%" colspan="1" nowrap="nowrap"><s:textfield
										name="positionName" size="25" theme="simple" readonly="true" /></td>
									<td id="mpPositionId" width="3%" colspan="1"><img
										src="../pages/images/recruitment/search2.gif" height="16"
										align="absmiddle" width="16" onclick="return callPosition();"></td>
									<td width="10%"></td>
									<td width="52%"></td>
								</tr>

								<tr>
									<td width="23%" class="formtext"><label class="set"
										id="reqsStatus" name="reqsStatus"
										ondblclick="callShowDiv(this);"><%=label.get("reqsStatus")%></label>
									:</td>
									<td width="9%" colspan="1" nowrap="nowrap"><s:select
										name="reqsnStatus" cssStyle="width:85"
										list="#{'1':'--Select--','O':'Open', 'C':'Close','N':'Canceled'}" /></td>
									<td width="3%" colspan="1"></td>
									<td width="10%"></td>
									<td width="52%"></td>
								</tr>


								<tr>
									<td width="23%" colspan="1" class="formtext"><label
										class="set" id="reqs.date" name="reqs.date"
										ondblclick="callShowDiv(this);"><%=label.get("reqs.date")%>
									</label>:</td>
									<td nowrap="nowrap"><s:select name="reqsnDate"
										cssStyle="width:85"
										list="#{'':'--Select--', 'O ':'On', 'B ':'Before', 'A ':'After', 'OB':'On Or Before',
						            			'OA':'On Or After','F ':'From'}"
										onchange="enableToDate();" /> <s:textfield name="frmDate"
										size="8" onkeypress="return numbersWithHiphen();"
										theme="simple" maxlength="10" /></td>
									<td width="10%" colspan="1" align="left"><img
										src="../pages/images/recruitment/Date.gif" id="fromDateIcon"
										class="iconImage" height="18" align="absmiddle" width="18"
										onclick="javascript:NewCal('paraFrm_frmDate','DDMMYYYY');">
									</td>
									<td width="10%" colspan="1">
									<div id="toDateLebel"><label class="set" id="tDate"
										name="tDate" ondblclick="callShowDiv(this);"><%=label.get("tDate")%></label>
									:</div>
									</td>
									<td width="55%" colspan="2" nowrap="nowrap">
									<div id="toDateText"><s:textfield name="tDate" size="7"
										onkeypress="return numbersWithHiphen();" theme="simple"
										maxlength="10" /> <s:a
										href="javascript:NewCal('paraFrm_tDate','DDMMYYYY');" /> <img
										src="../pages/images/recruitment/Date.gif" class="iconImage"
										height="18" align="absmiddle" width="18"
										onclick="javascript:NewCal('paraFrm_tDate','DDMMYYYY');">
									</div>
									</td>
								</tr>






								<tr>
									<td width="23%"><label class="set" id="selected"
										name="selected" ondblclick="callShowDiv(this);"><%=label.get("selected")%>
									: </td>
									<td width="77%" colspan="3">
									<table width="100%">
										<tr>
											<td width="50%"><s:textarea name="selectedReqName"
												cols="40" rows="2" readonly="true" theme="simple" /></td>
											<td width="25%">
											<div id="selectReqDiv"><input type="button"
												class="token" value="Select Requisition"
												onclick="callSelectReq('simple');" /></div>
											</td>
											<td width="25%" colspan="3">
											<div id="textAreaId1"><input type="button"
												class="token" value=" Edit Requisition"
												onclick="callSelectReq('edit');"></div>
											</td>
										</tr>
									</table>
									</td>
								</tr>
							</table>
							</td>
						</tr>




					</table>
					</div>
					</td>
				</tr>
				<!-- Filter Tab Ends -->

				<tr>
					<!-- Sorting Tab Starts -->
					<td>
					<div id="sortingOption">
					<table width="100%">
						<tr>
							<td><b> <label class="set" id="selectSort"
								name="selectSort" ondblclick="callShowDiv(this);"><%=label.get("selectSort")%></label></b></td>
						</tr>

					</table>
					<table width="100%" border="0" cellpadding="2" cellspacing="2">

						<tr>
							<td width="20%">Sort By :</td>
							<td colspan="1" nowrap="nowrap"><s:select name="firstSort"
								cssStyle="width:125" headerKey="1" headerValue="--Select--"
								list="#{'RN':'Requisition Code','RD':'Requisition Date','PO':'Position','RS':'Requisition Status','AS':'Approval Status','NV':'No Of Vacancies', 
							        			'RB':'Required By Date'}" />
							</td>
						</tr>
						<tr>
							<td width="20%"></td>
							<td><s:if test="radio1">
								<input type="radio" value="A" name="firstAsc" id="firstAsc"
									checked="checked" onclick="callFirstAsc();">
							</s:if><s:else>
								<input type="radio" value="A" name="firstAsc" id="firstAsc"
									onclick="callFirstAsc();">
							</s:else> Ascending <s:if test="radioFlag1">
								<input type="radio" value="D" name="firstDesc" id="firstDesc"
									checked="checked" onclick="callFirstDesc();">
							</s:if><s:else>
								<input type="radio" value="D" name="firstDesc" id="firstDesc"
									onclick="callFirstDesc();">
							</s:else> Descending</td>
						</tr>
						<tr>
							<td width="20%">Then By :</td>
							<td colspan="1" nowrap="nowrap"><s:select name="secSortBy"
								headerKey="1" headerValue="--Select--" cssStyle="width:125"
								list="#{'RN':'Requisition Code','RD':'Requisition Date','PO':'Position','RS':'Requisition Status','AS':'Approval Status','NV':'No Of Vacancies', 
							        			'RB':'Required By Date'}" />
							</td>
						</tr>
						<tr>
							<td width="20%"></td>
							<td><s:if test="radio2">

								<input type="radio" value="A" name="secAsc" id="secAsc"
									checked="checked" onclick="callSecAsc();">
							</s:if><s:else>
								<input type="radio" value="A" name="secAsc" id="secAsc"
									onclick="callSecAsc();">

							</s:else> Ascending <s:if test="radioFlag2">
								<input type="radio" value="D" name="secDesc" id="secDesc"
									checked="checked" onclick="callSecDesc();">
							</s:if><s:else>
								<input type="radio" value="D" name="secDesc" id="secDesc"
									onclick="callSecDesc();">
							</s:else> Descending</td>
						</tr>
						<tr>
							<td width="20%">Then By :</td>
							<td colspan="1" nowrap="nowrap"><s:select name="thirdSort"
								headerKey="1" headerValue="--Select--" cssStyle="width:125"
								list="#{'RN':'Requisition Code','RD':'Requisition Date','PO':'Position','RS':'Requisition Status','AS':'Approval Status','NV':'No Of Vacancies', 
							        			'RB':'Required By Date'}" />
							</td>
						</tr>
						<tr>
							<td width="20%"></td>
							<td><s:if test="radio3">
								<input type="radio" value="A" name="thirdAsc" checked="checked"
									id="thirdAsc" onclick="callThirdAsc();">
							</s:if><s:else>
								<input type="radio" value="A" name="thirdAsc" id="thirdAsc"
									onclick="callThirdAsc();">
							</s:else> Ascending <s:if test="radioFlag3">
								<input type="radio" value="D" name="thirdDesc" id="thirdDesc"
									checked="checked" onclick="callThirdDesc();">
							</s:if><s:else>
								<input type="radio" value="D" name="thirdDesc" id="thirdDesc"
									onclick="callThirdDesc();">
							</s:else> Descending</td>
						</tr>
					</table>
					</div>
					</td>
				</tr>
				<!-- Sorting Tab Ends -->

				<tr>
					<!-- ColumnDef Tab Starts -->
					<td>
					<div id="columnDefinition">

					<table width="100%">
						<tr>
							<td><b><label class="set" id="selectCoumn"
								name="selectCoumn" ondblclick="callShowDiv(this);"><%=label.get("selectCoumn")%></label></b></td>
						</tr>

					</table>

					<%
					try {
					%> <s:if test="checkFlag">
						<%
						Object[][] obj = (Object[][]) request.getAttribute("checkBox");
						%>
						<table width="100%" border="0">
							<tr>
								<td width="2%"><input type="checkbox" name="chkReqsn"
									checked="checked" disabled="true" /></td>
								<s:hidden name="hidReqsn" />
								<td width="10%"><label class="set" id="reqs.code"
									name="reqs.code" ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></td>

								<td width="2%"><input type="checkbox" name="chkNoOfVac"
									id="chkNoOfVac" onclick="callCheck();"
									<%=obj[0][1].equals("true")?"checked":"" %> /></td>
								<s:hidden name="hidNoOfVac" />
								<td width="20%"><label class="set" id="noofvac"
									name="noofvac" ondblclick="callShowDiv(this);"><%=label.get("noofvac")%></td>
							</tr>

							<tr>
								<td width="2%"><input type="checkbox" name="chkReqsnDate"
									id="chkReqsnDate" onclick="callCheck();"
									<%=obj[0][0].equals("true")?"checked":"" %> /></td>
								<s:hidden name="hidReqsnDate" />
								<td width="10%"><label class="set" id="reqs.date1"
									name="reqs.date" ondblclick="callShowDiv(this);"><%=label.get("reqs.date")%></label>
								</td>

								<td width="2%"><input type="checkbox" name="chkReqByDate"
									id="chkReqByDate" onclick="callCheck();"
									<%=obj[0][2].equals("true")?"checked":"" %> /></td>
								<s:hidden name="hidReqByDate" />
								<td width="20%"><label class="set" id="reqdbydate"
									name="reqdbydate" ondblclick="callShowDiv(this);"><%=label.get("reqdbydate")%></td>

							</tr>

							<tr>
								<td width="2%"><input type="checkbox" name="chkPosition"
									id="chkPosition" onclick="callCheck();"
									<%=obj[0][6].equals("true")?"checked":"" %> /></td>
								<s:hidden name="hidPosition" />
								<td width="10%"><label class="set" id="position1"
									name="position" ondblclick="callShowDiv(this);"><%=label.get("position")%></td>

								<td width="2%"><input type="checkbox" name="chkOverDue"
									id="chkOverDue" onclick="callCheck();"
									<%=obj[0][3].equals("true")?"checked":"" %> /></td>
								<s:hidden name="hidOverDue" />
								<td width="20%"><label class="set" id="overdue"
									name="overdue" ondblclick="callShowDiv(this);"><%=label.get("overdue")%></td>
							</tr>

							<tr>
								<td width="2%"><input type="checkbox" name="chkReqsStatus"
									id="chkReqsStatus" onclick="callCheck();"
									<%=obj[0][7].equals("true")?"checked":"" %> /></td>
								<s:hidden name="hidReqsStatus" />
								<td width="10%"><label class="set" id="reqsStatus1"
									name="reqsStatus" ondblclick="callShowDiv(this);"><%=label.get("reqsStatus")%></td>

								<td width="2%"><input type="checkbox" name="chkClosedDate"
									id="chkClosedDate" onclick="callCheck();"
									<%=obj[0][4].equals("true")?"checked":"" %> /></td>
								<s:hidden name="hidClosedDate" />
								<td width="20%"><label class="set" id="clsdDt"
									name="clsdDt" ondblclick="callShowDiv(this);"><%=label.get("clsdDt")%></td>
							</tr>

							<tr>
								<td width="2%"><input type="checkbox" name="chkApprvStatus"
									id="chkApprvStatus" onclick="callCheck();"
									<%=obj[0][8].equals("true")?"checked":"" %> /></td>
								<s:hidden name="hidApprvStatus" />
								<td width="10%"><label class="set" id="apprvStatus"
									name="apprvStatus" ondblclick="callShowDiv(this);"><%=label.get("apprvStatus")%></td>


								<td width="2%"><input type="checkbox" name="chkTotalCtc"
									id="chkTotalCtc" onclick="callCheck();"
									<%=obj[0][5].equals("true")?"checked":"" %> /></td>
								<s:hidden name="hidTotalCtc" />
								<td width="20%"><label class="set" id="totCtc"
									name="totCtc" ondblclick="callShowDiv(this);"><%=label.get("totCtc")%></td>

							</tr>

							<tr>
								<td width="2%"><input type="checkbox" name="chkHiringMngr"
									id="chkHiringMngr" onclick="callCheck();"
									<%=obj[0][9].equals("true")?"checked":"" %> /></td>
								<s:hidden name="hidHiringMngr" />
								<td width="10%"><label class="set" id="hiringMngr"
									name="hiringMngr" ondblclick="callShowDiv(this);"><%=label.get("hiringMngr")%></td>


								<td width="2%"><input type="checkbox" name="chkApprvrName"
									id="chkApprvrName" onclick="callCheck();"
									<%=obj[0][10].equals("true")?"checked":"" %> /></td>
								<s:hidden name="hidApprvrName" />
								<td width="20%"><label class="set" id="apprvrName"
									name="apprvrName" ondblclick="callShowDiv(this);"><%=label.get("apprvrName")%></td>

							</tr>
							<tr>
								<td width="2%"><input type="checkbox" name="chkRecruitName"
									id="chkRecruitName" onclick="callCheck();"
									<%=obj[0][11].equals("true")?"checked":"" %> /></td>
								<s:hidden name="hidRecruitName" />
								<td width="10%"><label class="set" id="recruitName"
									name="recruitName" ondblclick="callShowDiv(this);"><%=label.get("recruitName")%></td>

							</tr>




						</table>
					</s:if><s:else>
						<table width="100%" border="0">
							<tr>
								<td width="2%"><input type="checkbox" name="chkReqsn"
									checked="checked" disabled="true" /></td>
								<s:hidden name="hidReqsn" />
								<td width="10%"><label class="set" id="reqs.code"
									name="reqs.code" ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></td>

								<td width="2%"><input type="checkbox" name="chkNoOfVac"
									id="chkNoOfVac" onclick="callCheck();" /></td>
								<s:hidden name="hidNoOfVac" />
								<td width="20%"><label class="set" id="noofvac"
									name="noofvac" ondblclick="callShowDiv(this);"><%=label.get("noofvac")%></td>
							</tr>

							<tr>
								<td width="2%"><input type="checkbox" name="chkReqsnDate"
									id="chkReqsnDate" onclick="callCheck();" /></td>
								<s:hidden name="hidReqsnDate" />
								<td width="10%"><label class="set" id="reqs.date1"
									name="reqs.date" ondblclick="callShowDiv(this);"><%=label.get("reqs.date")%></label>
								</td>

								<td width="2%"><input type="checkbox" name="chkReqByDate"
									id="chkReqByDate" onclick="callCheck();" /></td>
								<s:hidden name="hidReqByDate" />
								<td width="20%"><label class="set" id="reqdbydate1"
									name="reqdbydate" ondblclick="callShowDiv(this);"><%=label.get("reqdbydate")%></td>

							</tr>

							<tr>
								<td width="2%"><input type="checkbox" name="chkPosition"
									id="chkPosition" onclick="callCheck();" /></td>
								<s:hidden name="hidPosition" />
								<td width="10%"><label class="set" id="position1"
									name="position" ondblclick="callShowDiv(this);"><%=label.get("position")%></td>

								<td width="2%"><input type="checkbox" name="chkOverDue"
									id="chkOverDue" onclick="callCheck();" /></td>
								<s:hidden name="hidOverDue" />
								<td width="20%"><label class="set" id="overdue"
									name="overdue" ondblclick="callShowDiv(this);"><%=label.get("overdue")%></td>
							</tr>

							<tr>
								<td width="2%"><input type="checkbox" name="chkReqsStatus"
									id="chkReqsStatus" onclick="callCheck();" /></td>
								<s:hidden name="hidReqsStatus" />
								<td width="10%"><label class="set" id="reqsStatus1"
									name="reqsStatus" ondblclick="callShowDiv(this);"><%=label.get("reqsStatus")%></td>

								<td width="2%"><input type="checkbox" name="chkClosedDate"
									id="chkClosedDate" onclick="callCheck();" /></td>
								<s:hidden name="hidClosedDate" />
								<td width="20%"><label class="set" id="clsdDt"
									name="clsdDt" ondblclick="callShowDiv(this);"><%=label.get("clsdDt")%></td>
							</tr>

							<tr>
								<td width="2%"><input type="checkbox" name="chkApprvStatus"
									id="chkApprvStatus" onclick="callCheck();" /></td>
								<s:hidden name="hidApprvStatus" />
								<td width="10%"><label class="set" id="apprvStatus"
									name="apprvStatus" ondblclick="callShowDiv(this);"><%=label.get("apprvStatus")%></td>


								<td width="2%"><input type="checkbox" name="chkTotalCtc"
									id="chkTotalCtc" onclick="callCheck();" /></td>
								<s:hidden name="hidTotalCtc" />
								<td width="20%"><label class="set" id="totCtc"
									name="totCtc" ondblclick="callShowDiv(this);"><%=label.get("totCtc")%></td>

							</tr>
							<tr>
								<td width="2%"><input type="checkbox" name="chkHiringMngr"
									id="chkHiringMngr" onclick="callCheck();" /></td>
								<s:hidden name="hidHiringMngr" />
								<td width="10%"><label class="set" id="hiringMngr"
									name="hiringMngr" ondblclick="callShowDiv(this);"><%=label.get("hiringMngr")%></td>


								<td width="2%"><input type="checkbox" name="chkApprvrName"
									id="chkApprvrName" onclick="callCheck();" /></td>
								<s:hidden name="hidApprvrName" />
								<td width="20%"><label class="set" id="apprvrName"
									name="apprvrName" ondblclick="callShowDiv(this);"><%=label.get("apprvrName")%></td>

							</tr>

							<tr>
								<td width="2%"><input type="checkbox" name="chkRecruitName"
									id="chkRecruitName" onclick="callCheck();" /></td>
								<s:hidden name="hidRecruitName" />
								<td width="10%"><label class="set" id="recruitName"
									name="recruitName" ondblclick="callShowDiv(this);"><%=label.get("recruitName")%></td>

							</tr>



						</table>

					</s:else> <%
 		} catch (Exception e) {
 		e.printStackTrace();
 	}
 %>
					</div>
					</td>
				</tr>

				<!-- Adavancefilter Tab Starts -->

				<tr>
					<td>
					<div id="advancedFilters">
					<table width="100%">
						<tr>
							<td><b><label class="set" id="selectAdvanceFilter"
								name="selectAdvanceFilter" ondblclick="callShowDiv(this);"><%=label.get("selectAdvanceFilter")%></label></b></td>
						</tr>

					</table>
					<table width="100%" border="0" cellpadding="" cellspacing="0">
						<tr>
							<td width="21%"><label class="set" id="noofvac2"
								name="noofvac" ondblclick="callShowDiv(this);"><%=label.get("noofvac")%>
							:</td>
							<td colspan="1" nowrap="nowrap"><s:select name="advVacOpt"
								headerKey="1" headerValue="--Select--" cssStyle="width:90"
								list="#{'IE':'=','LT':'<','GT':'>','LE':'<=','GE':'>='}"
								onchange="callAdvance();" /> <s:textfield name="advVacVal"
								size="4" maxlength="3" onkeypress="return numbersOnly();" /></td>
						</tr>
						<!--  <tr>
							<td width="21%"><label class="set" id="overdue1"
								name="overdue" ondblclick="callShowDiv(this);"><%=label.get("overdue")%>
							:</td>
							<td colspan="1" nowrap="nowrap"><s:select name="advOverDueOpt" headerKey="1" headerValue="--Select--"
								cssStyle="width:90"
								list="#{'IE':'=','LT':'<','GT':'>','LE':'<=','GE':'>='}" />
							<s:textfield name="advOverDueVal" size="4" maxlength="3"
								onkeypress="return numbersOnly();" /></td>
						</tr>-->

						<tr>
							<td width="21%"><label class="set" id="apprvStatus1"
								name="apprvStatus" ondblclick="callShowDiv(this);"><%=label.get("apprvStatus")%>
							:</td>
							<td colspan="1" nowrap="nowrap"><s:select
								name="advApprvStat" headerKey="1" headerValue="--Select--"
								cssStyle="width:90"
								list="#{'A':'Approved','Q':'Quick Requisition'}" /></td>
						</tr>



					</table>
					</div>
					</td>
				</tr>

			</table>
			</td>
		</tr>
		<!-- Adavancefilter Tab Ends -->

		<tr>
			<td colspan="3">
			<table width="100%" border="0" class="formbg">

				<tr>
					<td width="100%" colspan="2" class="formtext"><input
						type="radio" value="V" name="viewOnScrn" id="viewOnScrn"
						onclick="callReportChk('N');"> <label class="set"
						id="view.screen" name="view.screen"
						ondblclick="callShowDiv(this);"><%=label.get("view.screen")%></td>
				</tr>
				<tr>
					<td width="18%" colspan="1" class="formtext"><input
						type="radio" value="R" name="genRep" id="genRep"
						onclick="callReportChk('Y');"><label class="set"
						id="report.type" name="report.type"
						ondblclick="callShowDiv(this);"><%=label.get("report.type")%></td>
					<td width="82%" colspan="1" class="formtext">
					<div id="reportTypeDiv"><s:select name="repType"
						headerKey="1" headerValue="--Select--"
						list="#{'Pdf':'Pdf','Txt':'Doc','Xls':'Xls'}" /></div>
					</td>
				</tr>
				<tr>
					<td width="21%" colspan="1" class="formtext" id="setting"
						nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label
						class="set" id="setting.name" name="setting.name"
						ondblclick="callShowDiv(this);"><%=label.get("setting.name")%>
					:</td>
					<td width="11%"><s:textfield name="settingName" size="25"
						maxlength="25" /></td>

				</tr>

			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0">
				<tr>
					<td width="100%" colspan="3"><s:if test="%{viewFlag}">
						<input type="button" class="token" onclick="return callReport();"
							value=" Generate Report" />
					</s:if> <input type="button" class="reset" theme="simple" value=" Reset"
						onclick="callReset();" /> <input type="button" class="token"
						value="Save Report Criteria" onclick="return callSave();" /></td>
				</tr>
			</table>
			</td>
		</tr>

	</table>
</s:form>
<script>
function callAdvance(){

	if(document.getElementById('paraFrm_advVacOpt').value=="1"){
		document.getElementById('paraFrm_advVacVal').value="";
	
	}
}

callDisplay();
function callDisplay(){
 	if(document.getElementById('paraFrm_reqsnFlg').value=="true"){
   		document.getElementById('paraFrm_positionName').value="";
		document.getElementById('paraFrm_positionId').value="";
		document.getElementById('selectReqDiv').style.display ='none';
		document.getElementById('textAreaId1').style.display ='none';
		document.getElementById('paraFrm_selectedReq').value="";
		document.getElementById('paraFrm_selectedReqName').value="";
		document.getElementById('mpreqsnId').style.display ='';
		document.getElementById('mpPositionId').style.display ='none';
		document.getElementById('positionRadio').checked =false;
		document.getElementById('paraFrm_reqsnFlg').value="true";
		document.getElementById('reqsnRadio').checked=true;
   	}
   	if(document.getElementById('paraFrm_positionFlg').value=="true"){
   		document.getElementById('mpreqsnId').style.display ='none';
	    document.getElementById('paraFrm_reqsnName').value="";
	    document.getElementById('paraFrm_reqsnCode').value="";
	    document.getElementById('mpPositionId').style.display ='';
	    document.getElementById('reqsnRadio').checked =false;
	    document.getElementById('selectReqDiv').style.display ='';
	    document.getElementById('paraFrm_positionFlg').value="true";
	    document.getElementById('positionRadio').checked=true;
   	
   	}

}
function callRadio(param){
if(param=="reqsn"){
 	document.getElementById('paraFrm_positionName').value="";
	document.getElementById('paraFrm_positionId').value="";
	document.getElementById('selectReqDiv').style.display ='none';
	document.getElementById('textAreaId1').style.display ='none';
	document.getElementById('paraFrm_selectedReq').value="";
	document.getElementById('paraFrm_selectedReqName').value="";
	document.getElementById('mpreqsnId').style.display ='';
	document.getElementById('mpPositionId').style.display ='none';
	document.getElementById('positionRadio').checked =false;
	document.getElementById('paraFrm_reqsnFlg').value="true";
	
	}
	if(param=="position"){
	document.getElementById('mpreqsnId').style.display ='none';
	document.getElementById('paraFrm_reqsnName').value="";
	document.getElementById('paraFrm_reqsnCode').value="";
	document.getElementById('mpPositionId').style.display ='';
	document.getElementById('reqsnRadio').checked =false;
	document.getElementById('selectReqDiv').style.display ='';
	document.getElementById('paraFrm_positionFlg').value="true";
	
}

}

function callReset(){

	document.getElementById('paraFrm').action="ManpowerRequisitionAnalysis_reset.action";
	document.getElementById('paraFrm').submit();

}

callOnBackTab();
 function callOnBackTab()
 {
 
 
 
 
 document.getElementById('mpreqsnId').style.display ='none';
 document.getElementById('mpPositionId').style.display ='none';
 var back =   document.getElementById('paraFrm_backFlag').value;
 
 var aId =   document.getElementById('paraFrm_aId').value;
 var tabId =   document.getElementById('paraFrm_divId').value; 
  if(back=='true'){
   callTab(tabId,aId);
   document.getElementById('paraFrm_backFlag').value="false";
   } else{
  
   callOnLoad(); 
   }
 } 

callOnLoad();
enableToDate();
 function callOnLoad()
 {
	if(document.getElementById('paraFrm_showReqsnFlag').value=="true"){
        document.getElementById('textAreaId1').style.display='';
      }else{
        document.getElementById('textAreaId1').style.display='none';
     }
	
//	 document.getElementById('textAreaId1').style.display='none'; 
 	var rep= document.getElementById('paraFrm_reportFlag').value;
 	var div=document.getElementById('paraFrm_divId').value;
 if(div=="" || div=="filter"){
   document.getElementById('filterOption').style.display='';
   document.getElementById('sortingOption').style.display='none'; 
   document.getElementById('columnDefinition').style.display='none';  
   document.getElementById('advancedFilters').style.display='none'; 
   	document.getElementById('filtOpt').className = 'on';
   	document.getElementById('sortOpt').className = '';
   	document.getElementById('advFilter').className = '';
   	document.getElementById('colDef').className = '';
   	if(document.getElementById('paraFrm_reqsnFlg').value=="true"){
   		document.getElementById('paraFrm_positionName').value="";
		document.getElementById('paraFrm_positionId').value="";
		//document.getElementById('selectReqDiv').style.display ='none';
		//document.getElementById('textAreaId1').style.display ='none';
		//document.getElementById('paraFrm_selectedReq').value="";
		//document.getElementById('paraFrm_selectedReqName').value="";
		document.getElementById('mpreqsnId').style.display ='';
		document.getElementById('mpPositionId').style.display ='none';
		document.getElementById('positionRadio').checked =false;
		document.getElementById('paraFrm_reqsnFlg').value="true";
		document.getElementById('reqsnRadio').checked=true;
   	}
   	if(document.getElementById('paraFrm_positionFlg').value=="true"){
   		document.getElementById('mpreqsnId').style.display ='none';
	    document.getElementById('paraFrm_reqsnName').value="";
	    document.getElementById('paraFrm_reqsnCode').value="";
	    document.getElementById('mpPositionId').style.display ='';
	    document.getElementById('reqsnRadio').checked =false;
	    document.getElementById('selectReqDiv').style.display ='';
	    document.getElementById('paraFrm_positionFlg').value="true";
	    document.getElementById('positionRadio').checked=true;
   	
   	}
   	
   	
 	
 }	else if(div=="sort"){
    var first=document.getElementById('paraFrm_firstRadio').value;
    var second=document.getElementById('paraFrm_secondRadio').value;
    var third=document.getElementById('paraFrm_thirdRadio').value;
    	document.getElementById('sortOpt').className = 'on';
    	document.getElementById('filtOpt').className = '';
   	 	document.getElementById('advFilter').className = '';
   	 	document.getElementById('colDef').className = '';
   
   document.getElementById('filterOption').style.display='none';
   document.getElementById('sortingOption').style.display=''; 
   document.getElementById('columnDefinition').style.display='none';  
   document.getElementById('advancedFilters').style.display='none'; 
     if(first=="true"){
     		  document.getElementById('paraFrm_hidFirstDesc').value='D';
	   		  document.getElementById('firstDesc').checked=true;
	   }else{
	          document.getElementById('firstAsc').checked=true;
	           document.getElementById('paraFrm_hidFirstAsc').value='A';
	   }
	 if(second=="true"){
	          document.getElementById('paraFrm_hidSecDesc').value='D';
	   		  document.getElementById('secDesc').checked=true;
	 
	 }else{
	          document.getElementById('paraFrm_hidSecAsc').value='A';
	   		  document.getElementById('secAsc').checked=true;
	 }  
	 
	 if(third=="true"){
	     document.getElementById('paraFrm_hidThirdDesc').value='D';
	  	 document.getElementById('thirdDesc').checked=true;
	 
	 }else{
	 	 document.getElementById('thirdAsc').checked=true;
	     document.getElementById('paraFrm_hidThirdAsc').value='A';
	 }  
	   
	 
 
 }else if(div=="advance"){
   document.getElementById('filterOption').style.display='none';
   document.getElementById('sortingOption').style.display='none'; 
   document.getElementById('columnDefinition').style.display='none';  
   document.getElementById('advancedFilters').style.display=''; 
   	document.getElementById('advFilter').className = 'on';
   	document.getElementById('filtOpt').className = '';
   	document.getElementById('sortOpt').className = '';
   	document.getElementById('colDef').className = '';
 
 }else if(div=="column"){
 	document.getElementById('colDef').className = 'on';
 	document.getElementById('filtOpt').className = '';
   	document.getElementById('sortOpt').className = '';
   	document.getElementById('advFilter').className = '';
   	 
 
 	if( document.getElementById('paraFrm_dateChk').value=="true"){
 	     document.getElementById('chkReqsnDate').checked=true;
 	      document.getElementById('paraFrm_hidReqsnDate').value='Y';
 	}else{
 	     document.getElementById('chkReqsnDate').checked=false;//
 	     document.getElementById('paraFrm_hidReqsnDate').value='N';
 	}
 	
 	if(document.getElementById('paraFrm_reqChk').value=="true"){
 	     document.getElementById('chkReqByDate').checked=true;
 	     document.getElementById('paraFrm_hidReqByDate').value='Y';
 	}else{
 	     document.getElementById('chkReqByDate').checked=false;
 	     document.getElementById('paraFrm_hidReqByDate').value='N';
 	}
 	
 	if( document.getElementById('paraFrm_totalCtcChk').value=="true"){
 	    document.getElementById('chkTotalCtc').checked=true;//hidVacStatus
 	       document.getElementById('paraFrm_hidTotalCtc').value='Y';
 	}else{
 	    document.getElementById('chkTotalCtc').checked=false;
 	     document.getElementById('paraFrm_hidTotalCtc').value='N';
 	}
 	
 if(document.getElementById('paraFrm_vacChk').value=="true"){
 	   document.getElementById('chkNoOfVac').checked=true;
 	   document.getElementById('paraFrm_hidNoOfVac').value='Y';
 	}else{
 	    document.getElementById('chkNoOfVac').checked=false;
 	    document.getElementById('paraFrm_hidNoOfVac').value='N';
 	}
 	
	if( document.getElementById('paraFrm_overChk').value=="true"){
 	   document.getElementById('chkOverDue').checked=true;
 	   document.getElementById('paraFrm_hidOverDue').value='Y';
 	}else{
 	    document.getElementById('chkOverDue').checked=false;//
 	      document.getElementById('paraFrm_hidOverDue').value='N';
 	    
 	}
 	
 	if(document.getElementById('paraFrm_closedDateChk').value=="true"){
 	     document.getElementById('chkClosedDate').checked=true;
 	      document.getElementById('paraFrm_hidClosedDate').value='Y';
 	}else{
 	    document.getElementById('chkClosedDate').checked=false;
 	     document.getElementById('paraFrm_hidClosedDate').value='N';
 	    
 	}
 	
 	
 	 if(document.getElementById('paraFrm_positionChk').value=="true")
			   {
			 
			    document.getElementById('chkPosition').checked=true;
			     document.getElementById('paraFrm_hidPosition').value='Y';
			   }
		  else
			   {
			   	 			
				  document.getElementById('chkPosition').checked=false;
			     document.getElementById('paraFrm_hidPosition').value='N';
			   }
			   
		  if(document.getElementById('paraFrm_statusChk').value=="true")
			   {
			    document.getElementById('chkReqsStatus').checked=true;
			    document.getElementById('paraFrm_hidReqsStatus').value='Y';
			    
			   }
		  else
			   {	
			     document.getElementById('chkReqsStatus').checked=false;	
				 document.getElementById('paraFrm_hidReqsStatus').value='N';
			   
			   } 
			   
		 if(document.getElementById('paraFrm_apprvChk').value=="true")
			   {
			    document.getElementById('chkApprvStatus').checked=true;
			     document.getElementById('paraFrm_hidApprvStatus').value='Y';
			    
			   }
		  else
			   {
			   document.getElementById('chkApprvStatus').checked=false;		
				 document.getElementById('paraFrm_hidApprvStatus').value='N';
			     
			   }
			   
			   
			   if(document.getElementById('paraFrm_hiringMngrChk').value=="true")
			   {
			   document.getElementById('chkHiringMngr').checked=true;
			    document.getElementById('paraFrm_hidHiringMngr').value='Y';
			     
			   }
		  else
			   {
			     document.getElementById('chkHiringMngr').checked=false;		
				 document.getElementById('paraFrm_hidHiringMngr').value='N';
			   
			   }

            if(document.getElementById('paraFrm_apprvrNameChk').value=="true")

			   {
			      document.getElementById('chkApprvrName').checked=true;
			    document.getElementById('paraFrm_hidApprvrName').value='Y';
			  
			   }
		  else
			   {	
			    document.getElementById('chkApprvrName').checked=false;	
				 document.getElementById('paraFrm_hidApprvrName').value='N';
			    
			   } 
			   
			   if(document.getElementById('paraFrm_recruitNameChk').value=="true")
			   {
			       document.getElementById('chkRecruitName').value=true;
			    document.getElementById('paraFrm_hidRecruitName').value='Y';
			  
			   }
		  else
			   {
			    document.getElementById('chkRecruitName').checked=false;		
				 document.getElementById('paraFrm_hidRecruitName').value='N';
			    
			   } 	
			   
			   
			   
			   
			   
			  
 	
 		  
	
   document.getElementById('filterOption').style.display='none';
   document.getElementById('sortingOption').style.display='none'; 
   document.getElementById('columnDefinition').style.display='';  
   document.getElementById('advancedFilters').style.display='none'; 
 }
   
   
   document.getElementById('toDateText').style.display='none';   
   document.getElementById('toDateLebel').style.display='none';
   if(rep=="true"){
   	document.getElementById('genRep').checked=true;
   	 document.getElementById('reportTypeDiv').style.display='';  
   	  document.getElementById('paraFrm_exportAllData').value='Y';  
   }else{
     document.getElementById('viewOnScrn').checked=true;
     document.getElementById('paraFrm_exportAllData').value='N';  
     document.getElementById('reportTypeDiv').style.display='none';  
}
 
 }
 
 function callTab(tabId,aId)
 {	
 
 
	
	
	if(document.getElementById('paraFrm_showReqsnFlag').value=="true"){
	     document.getElementById('textAreaId1').style.display='';
	  }else{
	     document.getElementById('textAreaId1').style.display='none';
	  } 
        document.getElementById('paraFrm_divId').value=tabId;
          document.getElementById('paraFrm_aId').value=aId;
        
	  if(tabId=="filter")
	  {
	   document.getElementById('filterOption').style.display='';
	   document.getElementById('sortingOption').style.display='none'; 
	   document.getElementById('columnDefinition').style.display='none';  
	   document.getElementById('advancedFilters').style.display='none';  
	  
	   	 	document.getElementById('filtOpt').className = 'on';
     	document.getElementById('sortOpt').className = '';
   	 	document.getElementById('advFilter').className = '';
   	 	document.getElementById('colDef').className = '';
	  }
	  
	  if(tabId=="sort")
	  {
	   var radio=document.getElementById('paraFrm_radioFlag').value;
	
	   document.getElementById('sortingOption').style.display='';
	   document.getElementById('filterOption').style.display='none';
	   document.getElementById('columnDefinition').style.display='none'; 
	   document.getElementById('advancedFilters').style.display='none';  
		  document.getElementById('sortOpt').className = 'on';
		  document.getElementById('filtOpt').className = '';
	      document.getElementById('advFilter').className = '';
	   	  document.getElementById('colDef').className = '';
	  
	   if(radio=="false"){
	   
	 
	   
			   if(document.getElementById('paraFrm_hidFirstAsc').value=='A'){
			   	   document.getElementById('firstAsc').checked=true;
			   	 }else{
			   	 	 document.getElementById('firstAsc').checked=false;
			   	 }  
			   	 
			   if(document.getElementById('paraFrm_hidFirstDesc').value=='D'){
			   	     document.getElementById('firstDesc').checked=true;
			   	 }else{
			   	 	 document.getElementById('firstDesc').checked=false;
			   	 }  
			   	 
			   	 
			   	 
			   	 if(document.getElementById('paraFrm_hidSecAsc').value=='A'){
			   	   document.getElementById('secAsc').checked=true;
			   	 }else{
			   	 	 document.getElementById('secAsc').checked=false;
			   	 }  
			   	 
			   if(document.getElementById('paraFrm_hidSecDesc').value=='D'){
			   	     document.getElementById('secDesc').checked=true;
			   	 }else{
			   	 	 document.getElementById('secDesc').checked=false;
			   	 }  
			   	 
			   	 
			   	 if(document.getElementById('paraFrm_hidThirdAsc').value=='A'){
			   	   document.getElementById('thirdAsc').checked=true;
			   	 }else{
			   	 	 document.getElementById('thirdAsc').checked=false;
			   	 }  
			   	 
			   if(document.getElementById('paraFrm_hidThirdDesc').value=='D'){
			   	     document.getElementById('thirdDesc').checked=true;
			   	 }else{
			   	 	 document.getElementById('thirdDesc').checked=false;
			   	 }  
	   	   }
	     
	  }
	  
	   if(tabId=="column")
	   {
	 
	   var chk=  document.getElementById('paraFrm_checkFlag').value;
	   	 
	   document.getElementById('columnDefinition').style.display='';  
	  
	   document.getElementById('sortingOption').style.display='none';
	   document.getElementById('filterOption').style.display='none'; 
	   document.getElementById('advancedFilters').style.display='none';  
	    
	   	document.getElementById('filtOpt').className = '';
	   	 
     	document.getElementById('sortOpt').className = '';
     
   	 	document.getElementById('advFilter').className = '';
   	 	
   	 	document.getElementById('colDef').className = 'on';
   	 
	   if(chk=="false"){
	   	
	   		  
	   		   if(document.getElementById('paraFrm_hidReqsnDate').value=='Y'){
	   	 	 
		       document.getElementById('chkReqsnDate').checked=true;
		   }else{
		      document.getElementById('chkReqsnDate').checked=false;
		   }
	   		  
	   		
	    if(document.getElementById('paraFrm_hidNoOfVac').value=="Y"){
	    
	         document.getElementById('chkNoOfVac').checked=true;
	    
	    }else{
	    	document.getElementById('chkNoOfVac').checked=false;
	    
	    }		  
	   		   
	 //chkNoOfVac
	  /**
	   if(document.getElementById('chkNoOfVac').checked)
		   {
		    document.getElementById('paraFrm_hidNoOfVac').value='Y';
		      document.getElementById('paraFrm_vacChk').value='true';
		   }
		   else
		   {		
			document.getElementById('paraFrm_hidNoOfVac').value='N';
			  document.getElementById('paraFrm_vacChk').value='false';
		   }
	 
	 **/
	  
	   	 
		   
		    if(document.getElementById('paraFrm_hidReqByDate').value=='Y'){
		      document.getElementById('chkReqByDate').checked=true;
		   }else{
		     document.getElementById('chkReqByDate').checked=false;
		   }
		   
		   if(document.getElementById('paraFrm_hidOverDue').value=='Y'){ 
		      document.getElementById('chkOverDue').checked=true;
		   }else{
		      document.getElementById('chkOverDue').checked=false;
		   }
		   
		   if(document.getElementById('paraFrm_hidClosedDate').value=='Y'){ 
		      document.getElementById('chkClosedDate').checked=true;
		   }else{
		     document.getElementById('chkClosedDate').checked=false;
		   }
		   
		  if(document.getElementById('paraFrm_hidTotalCtc').value=='Y'){ 
		      document.getElementById('chkTotalCtc').checked=true;
		   }else{
		      document.getElementById('chkTotalCtc').checked=false;
		   }
		   
		  
		   if(document.getElementById('paraFrm_hidPosition').value=='Y'){ 
		      document.getElementById('chkPosition').checked=true;
		   }else{
		      document.getElementById('chkPosition').checked=false;
		   } 
		   
		   if(document.getElementById('paraFrm_hidReqsStatus').value=='Y'){ 
		      document.getElementById('chkReqsStatus').checked=true;
		   }else{
		      document.getElementById('chkReqsStatus').checked=false;
		   } 
		    
		    if(document.getElementById('paraFrm_hidApprvStatus').value=='Y'){ 
		      document.getElementById('chkApprvStatus').checked=true;
		   }else{
		      document.getElementById('chkApprvStatus').checked=false;
		   } 
		   
		   

		    if( document.getElementById('paraFrm_hidHiringMngr').value=='Y')
			   {
			   document.getElementById('chkHiringMngr').checked=true;
			   }
		  else{
			     document.getElementById('chkHiringMngr').checked=false;		
			   }

            if( document.getElementById('paraFrm_hidApprvrName').value=='Y')

			   {
			      document.getElementById('chkApprvrName').checked=true;
			   }
		  else
			   {	
			    document.getElementById('chkApprvrName').checked=false;	
			   } 
			   
			   if(  document.getElementById('paraFrm_hidRecruitName').value=='Y')
			   {
			       document.getElementById('chkRecruitName').checked=true;
			   }
		  else
			   {
			    document.getElementById('chkRecruitName').checked=false;		
				
			   } 	
	   }
	  } 
	  
	   if(tabId=="advance")
	   {
	   
	  
	   document.getElementById('advancedFilters').style.display='';  
	   document.getElementById('columnDefinition').style.display='none';   
	   document.getElementById('sortingOption').style.display='none';
	   document.getElementById('filterOption').style.display='none';  
	   document.getElementById('filtOpt').className = '';
       document.getElementById('sortOpt').className = '';
       document.getElementById('colDef').className = '';
	   document.getElementById('advFilter').className = 'on';
	  }
 }
 
 function callReqsn(){

	callsF9(500,325,'ManpowerRequisitionAnalysis_f9Requisition.action');
 }
 
  function callPosition(){
	document.getElementById('mpreqsnId').style.display ='none';
	document.getElementById('paraFrm_reqsnName').value="";
	document.getElementById('paraFrm_reqsnCode').value="";
	callsF9(500,325,'ManpowerRequisitionAnalysis_f9Position.action');
 }
 
 
  function callSelectReq(status)
 {
 
		var frmDate=trim(document.getElementById("paraFrm_frmDate").value);
 	 
      	if(!(document.getElementById("paraFrm_reqsnDate").value=="")){
      		if(trim(document.getElementById("paraFrm_frmDate").value)==""){
      		callTab('filter','filtOpt');
      			alert("Please select or enter "+document.getElementById('reqs.date').innerHTML.toLowerCase());
      			return false;
      		}
      	
      	}
    
      
         if((frmDate!="") || (toDate!="")) {
		    		if(!validateDate('paraFrm_frmDate','reqs.date')){
		    		callTab('filter','filtOpt');
				             return false;
				     }
					     
		  }
			 if(document.getElementById("paraFrm_reqsnDate").value=="F "){ 	
							 var toDate=trim(document.getElementById("paraFrm_tDate").value); 	
							  if(toDate!=""){	
								  if(!validateDate('paraFrm_tDate','tDate')){
								  callTab('filter','filtOpt');
							             return false;	 
							      }	
						        }     	
						             	
						  	 if(!dateDifferenceEqual(frmDate,toDate,"paraFrm_tDate",'reqs.date','tDate')){
						  	 callTab('filter','filtOpt');
						  	   return false;
						  	  } 
  	   }  
 
 
 
 
	  if(status=='edit'){
    document.getElementById('paraFrm_selectedReqName').value =document.getElementById('paraFrm_hidSelectedReqName').value;
   document.getElementById('paraFrm_editReqFlag').value="true";    
	  }else{
	   document.getElementById('paraFrm_editReqFlag').value="false"; 
	   document.getElementById('paraFrm_selectedReqName').value="";
	   document.getElementById('paraFrm_selectedReq').value=""; 
	  } 
  
	document.getElementById('mpreqsnId').style.display ='none';
	document.getElementById('paraFrm_reqsnName').value="";
	document.getElementById('paraFrm_reqsnCode').value="";
	document.getElementById('paraFrm_showReqsnFlag').value="true"; 
	document.getElementById('paraFrm').target="wind";
	 var wind = window.open('','wind','width=700,height=450,scrollbars=yes,resizable=no,status=yes,menubar=no,top=200,left=150');
	//var wind = window.open('','wind','width=500,height=410,status=yes,scrollbars=no,resizable=yes,menubar=no,top=200,left=150');	 
	document.getElementById('paraFrm').action="ManpowerRequisitionAnalysis_displayReq.action";
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target="";
 }
 
 function callReport(){
	var frmDate=trim(document.getElementById("paraFrm_frmDate").value);
	var sort1=document.getElementById("paraFrm_firstSort").value;
 	var sort2=document.getElementById("paraFrm_secSortBy").value;
 	var sort3=document.getElementById("paraFrm_thirdSort").value;
 	 
    if(trim(document.getElementById("paraFrm_frmDate").value)!=""){
      		     callTab('filter','filtOpt');
      			if(document.getElementById("paraFrm_reqsnDate").value==""){
	      			alert("Please select requisition date option");
	      			return false;
      			}
      		}
      		
      		
      	 if(trim(document.getElementById("paraFrm_reqsnDate").value)!=""){
      		     callTab('filter','filtOpt');
      			if(trim(document.getElementById("paraFrm_frmDate").value)==""){
	      			alert("Please select or enter "+document.getElementById('reqs.date').innerHTML.toLowerCase());
	      			return false;
      			}
      		}	
      
         if((frmDate!="")) {
		    		if(!validateDate('paraFrm_frmDate','reqs.date')){
		    		callTab('filter','filtOpt');
				             return false;
				     }
					     
				   }
			 if(document.getElementById("paraFrm_reqsnDate").value=="F "){ 	
			 var toDate=trim(document.getElementById("paraFrm_tDate").value); 	
			  if(toDate!=""){	
				  if(!validateDate('paraFrm_tDate','tDate')){
				  callTab('filter','filtOpt');
			             return false;	 
			             	}	
		        }     	
		             	
		  	 if(!dateDifferenceEqual(frmDate,toDate,"paraFrm_tDate",'reqs.date','tDate')){
		  	 callTab('filter','filtOpt');
		  	   return false;
		  	  } 
  	   } 
  	   
  	   
  	    if(sort1!="1" && sort2!="1"){
  	 	if(sort1==sort2){
  	 			callTab('sort','sortOpt');
  	 		 	alert("Duplicate Sorting Option.");
  	 		 	return false;
  	 	}	
  	 
  	 }
  	 
  	 if(sort1!="1" && sort3!="1"){
  	 	if(sort1==sort3){
  	 			callTab('sort','sortOpt');
  	 		 	alert("Duplicate Sorting Option.");
  	 		 	return false;
  	 	}	
  	 
  	 }
  	 
  	 
  	 if(sort2!="1" && sort3!="1"){
  	 	if(sort2==sort3){
  	 			callTab('sort','sortOpt');
  	 		 	alert("Duplicate Sorting Option.");
  	 		 	return false;
  	 	}	
  	 
  	 }
  	 
  	  if(document.getElementById("paraFrm_advVacOpt").value!="1"){
  	   callTab('advance','advFilter');
  	    if(trim(document.getElementById("paraFrm_advVacVal").value)==""){
  	 	alert("Please enter the "+document.getElementById('noofvac').innerHTML.toLowerCase());
  	 	 document.getElementById('paraFrm_advVacVal').focus();
  	 	return false;
  	 	}	
   	 }
   	 
   	/* if(document.getElementById("paraFrm_advOverDueOpt").value!="1"){
  	   callTab('advance','advFilter');
  	    if(trim(document.getElementById("paraFrm_advOverDueVal").value)==""){
  	 	alert("Please enter the "+document.getElementById('overdue').innerHTML.toLowerCase());
  	 	 document.getElementById('paraFrm_advOverDueVal').focus();
  	 	 return false;
  	 	}	
   	  }*/
  	
 		if(document.getElementById('viewOnScrn').checked==true){
 			
    		  		
  	 
 			document.getElementById('paraFrm').action='ManpowerRequisitionAnalysis_showPagewise.action';	
		    document.getElementById('paraFrm').submit();
 		
 		}else {
 			
 		  if(document.getElementById('paraFrm_repType').value=="1"){
 		 	alert("Please select the report type");
 		 	return false;
 		  }	
		  document.getElementById('paraFrm').action='ManpowerRequisitionAnalysis_generateReport.action';	
		  document.getElementById('paraFrm').submit();
 		}
 }
 
  function callCheck(){
		  
   		   if(document.getElementById('chkTotalCtc').checked)
		   {
		    document.getElementById('paraFrm_hidTotalCtc').value='Y';
		    document.getElementById('paraFrm_totalCtcChk').value='true';
		   }
		   else
		   {		
			document.getElementById('paraFrm_hidTotalCtc').value='N';
			document.getElementById('paraFrm_totalCtcChk').value='false';
		   }
   
   
		   if(document.getElementById('chkReqsnDate').checked )
		   {
		     document.getElementById('paraFrm_hidReqsnDate').value='Y';
		     document.getElementById('paraFrm_dateChk').value='true';
		   }
		   else
		   {		
			document.getElementById('paraFrm_hidReqsnDate').value='N';
			document.getElementById('paraFrm_dateChk').value='false';
		   }
		   
		   
		   if(document.getElementById('chkReqByDate').checked)
		   {
		    document.getElementById('paraFrm_hidReqByDate').value='Y';
		    document.getElementById('paraFrm_reqChk').value='true';
		   }
		   else
		   {		
			document.getElementById('paraFrm_hidReqByDate').value='N';
			  document.getElementById('paraFrm_reqChk').value='false';
		   }
   
		   if(document.getElementById('chkNoOfVac').checked)
		   {
		    document.getElementById('paraFrm_hidNoOfVac').value='Y';
		      document.getElementById('paraFrm_vacChk').value='true';
		   }
		   else
		   {		
			document.getElementById('paraFrm_hidNoOfVac').value='N';
			  document.getElementById('paraFrm_vacChk').value='false';
		   }
   
		   if(document.getElementById('chkOverDue').checked )
		   {
		    document.getElementById('paraFrm_hidOverDue').value='Y';
		     document.getElementById('paraFrm_overChk').value='true';
		    //overChk
		   }
		   else
		   {		
			document.getElementById('paraFrm_hidOverDue').value='N';
			 document.getElementById('paraFrm_overChk').value='false';
		   }
		   
		    if(document.getElementById('chkClosedDate').checked)
			   {
			    document.getElementById('paraFrm_hidClosedDate').value='Y';
			     document.getElementById('paraFrm_closedDateChk').value='true';
			   }
		   else
			   {		
				document.getElementById('paraFrm_hidClosedDate').value='N';
				  document.getElementById('paraFrm_closedDateChk').value='false';
			   }
			   
			   
		  if(document.getElementById('chkPosition').checked)
			   {
			    document.getElementById('paraFrm_hidPosition').value='Y';
			     document.getElementById('paraFrm_positionChk').value='true';
			   }
		  else
			   {		
				 document.getElementById('paraFrm_hidPosition').value='N';
			     document.getElementById('paraFrm_positionChk').value='false';
			     
			     
			   }
			   
		  if(document.getElementById('chkReqsStatus').checked)
			   {
			    document.getElementById('paraFrm_hidReqsStatus').value='Y';
			     document.getElementById('paraFrm_statusChk').value='true';
			   }
		  else
			   {		
				 document.getElementById('paraFrm_hidReqsStatus').value='N';
			     document.getElementById('paraFrm_statusChk').value='false';
			   } 
			   
		 if(document.getElementById('chkApprvStatus').checked)
			   {
			    document.getElementById('paraFrm_hidApprvStatus').value='Y';
			     document.getElementById('paraFrm_apprvChk').value='true';
			   }
		  else
			   {		
				 document.getElementById('paraFrm_hidApprvStatus').value='N';
			     document.getElementById('paraFrm_apprvChk').value='false';
			   } 
if(document.getElementById('chkHiringMngr').checked)
			   {
			    document.getElementById('paraFrm_hidHiringMngr').value='Y';
			     document.getElementById('paraFrm_hiringMngrChk').value='true';
			   }
		  else
			   {		
				 document.getElementById('paraFrm_hidHiringMngr').value='N';
			     document.getElementById('paraFrm_hiringMngrChk').value='false';
			   }

if(document.getElementById('chkApprvrName').checked)
			   {
			    document.getElementById('paraFrm_hidApprvrName').value='Y';
			     document.getElementById('paraFrm_apprvrNameChk').value='true';
			   }
		  else
			   {		
				 document.getElementById('paraFrm_hidApprvrName').value='N';
			     document.getElementById('paraFrm_apprvrNameChk').value='false';
			   } 
			   
			   if(document.getElementById('chkRecruitName').checked)
			   {
			    document.getElementById('paraFrm_hidRecruitName').value='Y';
			     document.getElementById('paraFrm_recruitNameChk').value='true';
			   }
		  else
			   {		
				 document.getElementById('paraFrm_hidRecruitName').value='N';
			     document.getElementById('paraFrm_recruitNameChk').value='false';
			   } 	   
		   	   
		   
		   
		  
		   
	
 }
 
  function callReportChk(status)
 { 
	 if(status=="Y"){
	    document.getElementById('reportTypeDiv').style.display='';
	    document.getElementById('viewOnScrn').checked=false;
	    document.getElementById('paraFrm_exportAllData').value='Y';
	  }else{
	     document.getElementById('reportTypeDiv').style.display='none';  
	 //    document.getElementById('viewOnScrn').checked=true;
	       document.getElementById('genRep').checked=false;
	       document.getElementById('repType').value="";
	        document.getElementById('paraFrm_exportAllData').value='N';
	     
	  }
 }
 
function callFirstAsc(){
	  document.getElementById('paraFrm_firstRadio').value='false';
	  document.getElementById('paraFrm_hidFirstAsc').value='A';
	  document.getElementById('paraFrm_hidFirstDesc').value='';
	  document.getElementById('firstDesc').checked=false;
					
					
} 
 function callFirstDesc(){
 	  document.getElementById('paraFrm_firstRadio').value='true';
	  document.getElementById('paraFrm_hidFirstAsc').value='';
	  document.getElementById('paraFrm_hidFirstDesc').value='D';
	  document.getElementById('firstAsc').checked=false;
					
					
						
} 


function callSecAsc(){
document.getElementById('paraFrm_secondRadio').value='false';
document.getElementById('paraFrm_hidSecAsc').value='A';
document.getElementById('paraFrm_hidSecDesc').value='';
document.getElementById('secDesc').checked=false;

}

function callSecDesc(){
	  document.getElementById('paraFrm_secondRadio').value='true';
	  document.getElementById('paraFrm_hidSecAsc').value='';
	  document.getElementById('paraFrm_hidSecDesc').value='D';
	  document.getElementById('secAsc').checked=false;

}

function callThirdAsc(){
		document.getElementById('paraFrm_thirdRadio').value='false';
		document.getElementById('paraFrm_hidThirdAsc').value='A';
	  document.getElementById('paraFrm_hidThirdDesc').value='';
	  document.getElementById('thirdDesc').checked=false;

}

function callThirdDesc(){
		document.getElementById('paraFrm_thirdRadio').value='true';
	  document.getElementById('paraFrm_hidThirdAsc').value='';
	  document.getElementById('paraFrm_hidThirdDesc').value='D';
	  document.getElementById('thirdAsc').checked=false;

}
 function enableToDate()
 {
   var dateFilter= document.getElementById('paraFrm_reqsnDate').value;
   if(dateFilter == "F "){
     document.getElementById('toDateText').style.display=''; 
     document.getElementById('toDateLebel').style.display='';    
   }else {
     document.getElementById('toDateText').style.display='none'; 
     document.getElementById('toDateLebel').style.display='none';   
     document.getElementById('paraFrm_tDate').value="";     
   } 
 }
 
 function callSave(){
 //save.setting
 	var frmDate=trim(document.getElementById("paraFrm_frmDate").value);
 	var sort1=document.getElementById("paraFrm_firstSort").value;
 	var sort2=document.getElementById("paraFrm_secSortBy").value;
 	var sort3=document.getElementById("paraFrm_thirdSort").value;
 	
 	
 	//if(!(document.getElementById("paraFrm_reqsnDate").value=="")){
 		//callTab('filter','filtOpt');
      		if(trim(document.getElementById("paraFrm_frmDate").value)!=""){
      		     callTab('filter','filtOpt');
      			if(document.getElementById("paraFrm_reqsnDate").value==""){
	      			alert("Please select requisition date option");
	      			return false;
      			}
      		}
      	
      //	}
      
      if(trim(document.getElementById("paraFrm_reqsnDate").value)!=""){
      		     callTab('filter','filtOpt');
      			if(trim(document.getElementById("paraFrm_frmDate").value)==""){
	      			alert("Please select or enter "+document.getElementById('reqs.date').innerHTML.toLowerCase());
	      			return false;
      			}
      		}	
      
 	 
         if((frmDate!="")) {
         	callTab('filter','filtOpt');
    		if(!validateDate('paraFrm_frmDate','reqs.date')){
		             return false;
		     }
		       document.getElementById('paraFrm_frmDate').focus();
			     
		   }
	 if(document.getElementById("paraFrm_reqsnDate").value=="F "){
		      callTab('filter','filtOpt'); 	
		 var toDate=trim(document.getElementById("paraFrm_tDate").value); 	
		  if(toDate!=""){	
			  if(!validateDate('paraFrm_tDate','tDate')){
		             return false;	 
		               document.getElementById('paraFrm_tDate').focus();
		             	}	
	        }     	
	             	
	  	 if(!dateDifferenceEqual(frmDate,toDate,"paraFrm_tDate",'reqs.date','tDate'))return false;
  	 } 
  	 
  	  if(document.getElementById("paraFrm_advVacOpt").value!="1"){
  	   callTab('advance','advFilter');
  	    if(trim(document.getElementById("paraFrm_advVacVal").value)==""){
  	 	alert("Please enter the"+document.getElementById('noofvac').innerHTML.toLowerCase());
  	 	 document.getElementById('paraFrm_advVacVal').focus();
  	 	return false;
  	 	}	
   	  }
   	 
   	 
   	/* if(document.getElementById("paraFrm_advOverDueOpt").value!="1"){
  	   callTab('advance','advFilter');
  	    if(trim(document.getElementById("paraFrm_advOverDueVal").value)==""){
  	 	alert("Please enter the "+document.getElementById('overdue').innerHTML.toLowerCase());
  	 	 document.getElementById('paraFrm_advOverDueVal').focus();
  	 	 return false;
  	 	}	
   	  }
   	 */
   	 
  	  if(trim(document.getElementById("paraFrm_settingName").value)==""){
  	 	callTab('filter','filtOpt');
  	 	alert("Please enter the  "+document.getElementById('setting.name').innerHTML.toLowerCase());
  	 	document.getElementById('paraFrm_settingName').focus();
  	 	return false;
  	 
  	 }
  	 
  	 if(sort1!="1" && sort2!="1"){
  	 	if(sort1==sort2){
  	 			callTab('sort','sortOpt');
  	 		 	alert("Duplicate Sorting Option.");
  	 		 	return false;
  	 	}	
  	 
  	 }
  	 
  	 if(sort1!="1" && sort3!="1"){
  	 	if(sort1==sort3){
  	 			callTab('sort','sortOpt');
  	 		 	alert("Duplicate Sorting Option.");
  	 		 	return false;
  	 	}	
  	 
  	 }
  	 
  	 
  	 if(sort2!="1" && sort3!="1"){
  	 	if(sort2==sort3){
  	 			callTab('sort','sortOpt');
  	 		 	alert("Duplicate Sorting Option.");
  	 		 	return false;
  	 	}	
  	 
  	 }
  	 
  	 
  	 
  	callTab('filter','filtOpt');
    document.getElementById('paraFrm').action="ManpowerRequisitionAnalysis_saveSettings.action";
	document.getElementById('paraFrm').submit();
 
 }
 
  function callVal(){
 
  
	var repCode=document.getElementById('paraFrm_searchSetting').value;
	if(repCode!="-1"){
		document.getElementById('setting').style.display ='none';
		document.getElementById('paraFrm_mraRepCode').value=repCode;
		document.getElementById('paraFrm').action='ManpowerRequisitionAnalysis_getFilterDetails.action';
		document.getElementById('paraFrm').submit();
	}
	
	
	}
 
</script>