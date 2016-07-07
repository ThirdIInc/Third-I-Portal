<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="CandidateSearch" id="paraFrm" theme="simple">
	<s:hidden name="searchCode" />
	<s:hidden name="searchFlag" />
	<s:hidden name="hiringManager" />
	<s:hidden name="hiringManagerCode" />
	<s:hidden name="dashletFlag" />
	<s:hidden name="requiredByDate" />

	<table class="formbg" width="100%">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/common/css/default/images/review_shared.gif"
						width="25" height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Candidate
					Search</strong></td>
					<td width="3%" valign="top" class="txt">
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%" nowrap="nowrap"><s:submit cssClass="save"
						value="Save Search Criteria" action="CandidateSearch_save"
						onclick="return validateForm();" /> <input type="button"
						class="search" value="Search Criteria"
						onclick="javascript:callsF9(500,325,'CandidateSearch_f9action.action')" />
					<!--
						<s:submit cssClass="reset" value=" Clear" action="CandidateSearch_clear" /> 
					-->
					<input type="button" value=" Clear" class="reset" onclick="callResetData();">
					<s:submit cssClass="token" value="Post Resume >>" onclick="return postResume();"
						action="CandidateSearch_toPostReumeForm" /> <input type="button"
						value=" Export In Xls " class="token"
						onclick="callReportForDisp('X');"></td>
					<s:if test='%{dashletFlag}'>
						<!-- 
						<s:submit cssClass="cancel" value="Cancel"
							onclick="return cancelFun();" />
						 -->	
					</s:if>
					</td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			<label></label></td>
		</tr>

		<!-- <tr>
          <td colspan="3"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
            <tr>
              <td width="100%" colspan="4"><strong class="formhead">Keyword Search</strong></td>
            </tr>
            
            <tr>
	          <td colspan="3"><img src="../pages/common/css/default/images/space.gif" width="5" height="2" /></td>
	        </tr>
            
            <tr>
            	<td colspan="4"><s:textfield name="keywordSearch" size="85"/>
            		&nbsp;<s:submit cssClass="search" value="    Search"/></td>
            </tr>
            
            <tr>
	          <td colspan="3"><img src="../pages/common/css/default/images/space.gif" width="5" height="2" /></td>
	        </tr>
            
            <tr>	
            	<td width="17%"><label  class = "set" name="search.criteria" id="search.criteria" ondblclick="callShowDiv(this);"><%=label.get("search.criteria")%></label> :</td>
            	<td width="20%"><s:select name="searchCriteria" cssStyle="width:150" 
            			list="#{'A':'All Words', 'AW':'Any of the words', 'E':'Exact Phrase', 'B':'Boolean'}"/></td>
            	<td width="20%">&nbsp;</td>
            	<td width="20%">&nbsp;</td>
            </tr>
            
            <tr>	
            	<td width="17%">
            		<label  class = "set" name="search.in" id="search.in" 
            		ondblclick="callShowDiv(this);"><%=label.get("search.in")%></label> :
            	</td>
            	<td width="50%" colspan="2">
            		<s:checkbox name="shortListed" onclick="selectCheckBox();"/>
            			<label  class = "set" name="short.listed" id="short.listed" ondblclick="callShowDiv(this);">
            			<%=label.get("short.listed")%></label>&nbsp; 
            		<s:checkbox name="rejected" onclick="selectCheckBox();"/>
            			<label  class = "set" name="rejected" id="rejected" ondblclick="callShowDiv(this);">
            			<%=label.get("rejected")%></label>&nbsp; 
            		<s:checkbox name="newResume" onclick="selectCheckBox();"/>
            			<label  class = "set" name="new.resume" id="new.resume" ondblclick="callShowDiv(this);">
            			<%=label.get("new.resume")%></label>&nbsp; 
            		<s:checkbox name="showAll" onclick="selectAll();"/>
            			<label  class = "set" name="show.all" id="show.all" ondblclick="callShowDiv(this);">
            			<%=label.get("show.all")%></label>&nbsp; 
            	</td>
            	<td width="20%" align="right"><s:a href="#" onclick="enableAdvanceSearch();">Advance Search</s:a></td>
            </tr>
            
          </table></td>
        </tr>
        
       <tr id="advanceSearch">
        	<td width="100%" colspan="3">
	        	<table width="100%"> -->
		<tr>
			<td width="100%" colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="100%" colspan="4" class="txt"><strong
						class="text_head">Candidate Search</strong></td>
				</tr>

				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<tr>
							<td width="17%"><label class="set" name="search.in"
								id="search.in" ondblclick="callShowDiv(this);"><%=label.get("search.in")%></label>
							:</td>
							<td width="70%" colspan="3"><s:checkbox name="shortListed"
								onclick="selectCheckBox();" /> <label class="set"
								name="short.listed" id="short.listed"
								ondblclick="callShowDiv(this);"> <%=label.get("short.listed")%></label>&nbsp;
							<s:checkbox name="rejected" onclick="selectCheckBox();" /> <label
								class="set" name="rejected" id="rejected"
								ondblclick="callShowDiv(this);"> <%=label.get("rejected")%></label>&nbsp;
							<s:checkbox name="newResume" onclick="selectCheckBox();" /> <label
								class="set" name="new.resume" id="new.resume"
								ondblclick="callShowDiv(this);"> <%=label.get("new.resume")%></label>&nbsp;
							<s:checkbox name="showAll" onclick="selectAll();" /> <label
								class="set" name="show.all" id="show.all"
								ondblclick="callShowDiv(this);"> <%=label.get("show.all")%></label>&nbsp;
							</td>
						</tr>

						<tr>
							<td width="15%"><label class="set" name="reqs.code"
								id="requisition.code" ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label>
							:<font color="red" size="2">*</font></td>
							<td width="25%" nowrap="nowrap"><s:textfield
								name="requisitionName" size="25" readonly="true" /> <img
								class="iconImage" src="../pages/images/recruitment/search2.gif"
								width="15" height="16"
								onclick="javascript:callsF9(500,325,'CandidateSearch_f9RequisitionCodeAction.action');" />
							<s:hidden name="requisitionCode" /> <s:hidden
								name="requisitionDate" /> <s:hidden name="jobDescription" /></td>
							<td width="10%"><label class="set" name="position"
								id="position" ondblclick="callShowDiv(this);"><%=label.get("position")%></label>
							:</td>
							<td width="20%"><s:textfield name="position" size="25"
								readonly="true" /> <s:hidden name="positionCode" /></td>
						</tr>

						<tr>
							<td width="15%" height="22"><label class="set"
								name="source.resume" id="source.resume"
								ondblclick="callShowDiv(this);"><%=label.get("source.resume")%></label>
							:</td>
							<td width="25%" height="22"><s:select name="sourceOfResume"
								cssStyle="width:163;z-index:1"
								list="#{'A':'All', 'E':'Employee Referral', 'C':'Job Consultant', 'O':'On Line Application', 
						            			'D':'Direct'}" /></td>
							<td width="10%" height="22">&nbsp;</td>
							<td width="20%" height="22">&nbsp;</td>
						</tr>

						<tr>
							<td width="15%" height="22" nowrap="nowrap"><label
								class="set" name="resume.date" id="resume.date"
								ondblclick="callShowDiv(this);"><%=label.get("resume.date")%></label>
							:</td>
							<td width="25%" height="22" nowrap="nowrap"><s:select
								name="positioningDate" cssStyle="width:90"
								list="#{'':'Select', 'O':'On', 'B':'Before', 'A':'After', 'OB':'On Or Before',
						            			'OA':'On Or After','F':'From'}"
								onchange="enableToDate();" /> <s:textfield name="fromDate"
								size="9" maxlength="10" onkeypress="return numbersWithHiphen();" />
							<s:a href="javascript:NewCal('paraFrm_fromDate','DDMMYYYY');">
								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" />
							</s:a></td>
							<td width="10%" height="22"><span id="toDateLebel"> <label
								class="set" name="to.date" id="to.date"
								ondblclick="callShowDiv(this);"><%=label.get("to.date")%></label>
							:</span></td>
							<td width="20%" height="22" nowrap="nowrap"><span
								id="toDateText"> <s:textfield name="toDate" size="25"
								maxlength="10" onkeypress="return numbersWithHiphen();" /> <s:a
								href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" />
							</s:a></span></td>
						</tr>

						<tr>
							<td width="15%" height="22"><label class="set"
								name="experience" id="experience"
								ondblclick="callShowDiv(this);"><%=label.get("experience")%></label>
							:</td>
							<td width="25%" height="22"><label class="set"
								name="exp.min" id="exp.min" ondblclick="callShowDiv(this);"><%=label.get("exp.min")%></label>:
							<s:textfield name="minExperience" maxlength="6" size="4"
								onkeypress="return numbersWithDot();" /> &nbsp;&nbsp;&nbsp;<label
								class="set" name="exp.max" id="exp.max"
								ondblclick="callShowDiv(this);"><%=label.get("exp.max")%></label>:
							<s:textfield name="maxExperience" size="4" maxlength="6"
								onkeypress="return numbersWithDot();" /></td>
							<td width="10%" height="22">&nbsp;</td>
							<td width="20%" height="22">&nbsp;</td>
						</tr>

						<tr>
							<td width="15%" height="22"><label class="set"
								name="first.name" id="first.name"
								ondblclick="callShowDiv(this);"><%=label.get("first.name")%></label>
							:</td>
							<td width="25%" height="22" nowrap="nowrap"><s:textfield
								name="firstName" size="25" maxlength="50"
								onkeypress="return charactersOnlyWithoutSpace();" /></td>
							<td width="10%" height="22"><label class="set"
								name="last.name" id="last.name" ondblclick="callShowDiv(this);"><%=label.get("last.name")%></label>
							:</td>
							<td width="20%" height="22"><s:textfield name="lastName"
								size="25" maxlength="50"
								onkeypress="return charactersOnlyWithoutSpace();" /></td>
						</tr>

						<tr>
							<td width="15%" height="22"><label class="set" name="gender"
								id="gender" ondblclick="callShowDiv(this);"><%=label.get("gender")%></label>
							:</td>
							<td width="25%" height="22" nowrap="nowrap"><s:select
								name="gender" cssStyle="width:163"
								list="#{'':'Select','M':'Male','F':'Female','O':'Other'}" /></td>
							<td width="10%" height="22"><label class="set"
								name="marital.status" id="marital.status"
								ondblclick="callShowDiv(this);"><%=label.get("marital.status")%></label>
							:</td>
							<td width="20%" height="22"><s:select name="maritalStatus"
								cssStyle="width:163"
								list="#{'':'Select', 'S':'Single', 'M':'Married', 'D':'Divorced'}" /></td>
						</tr>
						<tr>
							<td width="15%" height="22"><label class="set"
								name="sort.on" id="sort.on" ondblclick="callShowDiv(this);"><%=label.get("sort.on")%></label>
							:</td>
							<td width="25%" height="22" nowrap="nowrap"><s:select
								name="sortOn" cssStyle="width:163"
								list="#{'':'Select', 'RFA':'Resume Freshness (Ascending)', 'RFD':'Resume Freshness (Descending)', 
			            			'EA':'Experience (Ascending)', 'ED':'Experience (Descending)', 'CA':'CTC (Ascending)', 'CD':'CTC (Descending)'}" /></td>

						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="100%" colspan="4"><strong class="text_head">Location</strong></td>
				</tr>

				<tr>
					<td width="10%" valign="top"><label class="set" name="city"
						id="city" ondblclick="callShowDiv(this);"><%=label.get("city")%></label>
					:</td>
					<td width="15%" nowrap="nowrap"><s:select name="city"
						cssStyle="width:150" size="5" multiple="true" headerKey=""
						headerValue="Select" list="cityMap" /></td>
					<td width="10%" valign="top"><label class="set" name="state"
						id="state" ondblclick="callShowDiv(this);"><%=label.get("state")%></label>
					:</td>
					<td width="15%" valign="top"><s:select name="state"
						cssStyle="width:150" size="5" multiple="true" headerKey=""
						headerValue="Select" list="stateMap" /></td>
					<td width="10%" valign="top"><label class="set" name="country"
						id="country" ondblclick="callShowDiv(this);"><%=label.get("country")%></label>
					:</td>
					<td width="15%"><s:select name="country" cssStyle="width:150"
						size="5" multiple="true" headerKey="" headerValue="Select"
						list="countryMap" /></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="100%" colspan="4"><strong class="text_head">Qualification
					Details</strong></td>
				</tr>

				<tr>
					<td width="10%" valign="top"><label class="set"
						name="qualification" id="qualification"
						ondblclick="callShowDiv(this);"><%=label.get("qualification")%></label>
					:</td>
					<td width="15%" nowrap="nowrap"><s:select name="qualification"
						cssStyle="width:150" size="5" multiple="true" headerKey=""
						headerValue="Select" list="qualificationMap" /></td>
					<td width="10%" valign="top"><label class="set"
						name="specialization" id="specialization"
						ondblclick="callShowDiv(this);"><%=label.get("specialization")%></label>
					:</td>
					<td width="15%" valign="top"><s:select name="specialization"
						cssStyle="width:150" size="5" multiple="true" headerKey=""
						headerValue="Select" list="specializationMap" /></td>
					<td width="10%" valign="top"><label class="set"
						name="skill.set" id="skill.set" ondblclick="callShowDiv(this);"><%=label.get("skill.set")%></label>
					:</td>
					<td width="15%"><s:select name="skillSet" cssStyle="width:150"
						size="5" multiple="true" headerKey="" headerValue="Select"
						list="skillSetMap" /></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="100%" colspan="4"><strong class="text_head">Industry
					Details</strong></td>
				</tr>
				<tr>
					<td width="10%" valign="top"><label class="set"
						name="functional.area" id="functional.area"
						ondblclick="callShowDiv(this);"><%=label.get("functional.area")%></label>
					:</td>
					<td width="15%" nowrap="nowrap"><s:select
						name="functionalArea" cssStyle="width:150" size="5"
						multiple="true" headerKey="" headerValue="Select"
						list="functionalAreaMap" /></td>
					<td width="10%" valign="top"></td>
					<td width="15%" valign="top"></td>
					<td width="10%" valign="top"><label class="set"
						name="industry.type" id="industry.type"
						ondblclick="callShowDiv(this);"><%=label.get("industry.type")%></label>
					:</td>
					<td width="15%"><s:select name="industryType"
						cssStyle="width:150" size="5" multiple="true" headerKey=""
						headerValue="Select" list="industryTypeMap" /></td>
				</tr>

			</table>
			</td>
		</tr>

		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">


				<!--
			            
			            <tr>	
			            	<td width="15%">
			            		<label  class = "set" name="enablecv.search" id="enablecv.search" 
			            		ondblclick="callShowDiv(this);"><%=label.get("enablecv.search")%></label> : </td>
			            	<td width="20%" nowrap="nowrap">
			            		<s:checkbox name="enableSearch"/>&nbsp;</td>
			            	<td width="15%">&nbsp;</td>
			            	<td width="20%">&nbsp;</td>
			            </tr>
			            
			            -->
				<tr>
					<td width="100%" colspan="4" align="center"><s:submit
						cssClass="token" value="Search Candidate"
						action="CandidateSearch_searchCandidate"
						onclick="return validateSearch();" /></td>

				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="100%" colspan="4"><strong class="text_head">Search
					Results</strong></td>
				</tr>

				<tr>
					<td colspan="4">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="100%" border="0" cellpadding="1" cellspacing="1"
								class="sortable">
								<tr>
									<td width="5%" valign="top" class="formth"><label
										class="set" name="serial.no" id="serial.no"
										ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
									<td width="15%" valign="top" class="formth"><label
										class="set" name="cand.name" id="candidate.name"
										ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label></td>
									<td width="12%" valign="top" class="formth"><label
										class="set" name="experience" id="experience"
										ondblclick="callShowDiv(this);"><%=label.get("experience")%></label></td>
									<td width="9%" valign="top" class="formth" nowrap="nowrap">
									<label class="set" name="posted.date" id="posted.date"
										ondblclick="callShowDiv(this);"><%=label.get("posted.date")%></label></td>
									<td width="8%" valign="top" class="formth"><label
										class="set" name="ctc" id="ctc"
										ondblclick="callShowDiv(this);"><%=label.get("ctc")%></label></td>
									<td width="6%" valign="top" class="formth"><label
										class="set" name="gender" id="gender1"
										ondblclick="callShowDiv(this);"><%=label.get("gender")%></label></td>
									<td width="12%" valign="top" class="formth"><label
										class="set" name="listing.status" id="listing.status"
										ondblclick="callShowDiv(this);"><%=label.get("listing.status")%></label></td>
									<td width="6%" valign="top" class="formth"><label
										class="set" name="view.cv" id="view.cv"
										ondblclick="callShowDiv(this);"><%=label.get("view.cv")%></label></td>
									<td width="4%" valign="top" class="formth"><s:checkbox
										name="chkAll" onclick="selectAllCandidates();" /></td>
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
								<s:iterator status="stat" value="candidateList">
									<tr>
										<td width="5%" class="sortabletd" valign="middle"
											align="center"><%=k%></td>
										<td width="15%" class="sortabletd" valign="middle">
										<a href="#" onclick="viewCandidateDetails('<s:property value="candidateCode"/>');" title="Click here to view candidate details">
											<font color="blue"><u><s:property value="candidateName" /> </u></font>
										</a>
											<s:hidden name="candidateName" />
											<s:hidden name="candidateCode" />&nbsp;
										</td>
										<td width="12%" class="sortabletd" valign="middle"><s:property
											value="candExperience" /> <s:hidden name="candExperience" />&nbsp;</td>
										<td width="9%" class="sortabletd" valign="middle"
											align="center"><s:property value="postedDate" /> <s:hidden
											name="postedDate" />&nbsp;</td>
										<td width="8%" class="sortabletd" valign="middle"><s:property
											value="ctc" /> <s:hidden name="ctc" />&nbsp;</td>
										<td width="6%" class="sortabletd" valign="middle"><s:property
											value="candGender" /> <s:hidden name="candGender" />&nbsp;</td>
										<td width="12%" class="sortabletd" valign="middle"><s:select
											name="status" id='<%="status"+c %>'
											list="#{'':'Select', 'S':'Short Listed', 'R':'Rejected'}" /></td>
										<td width="6%" class="sortabletd" valign="middle"
											align="center"><input type="button" class="token"
											value="View"
											onclick="showRecord('<s:property value="resumeName"/>');" /></td>
										<td width="4%" class="sortabletd" align="center"
											valign="middle"><input type="checkbox" name="chk"
											id="<%="chk"+c %>"
											onclick="deselectAllCandidates();callChk(<%=c %>)" /> <input
											type="hidden" name="checkBox" id="<%=c %>" value="N" /></td>
									</tr>
									<%
										k++;
												c++;
									%>
								</s:iterator>
							</table>
							</td>
						</tr>
					</table>
					</td>
					<input type="hidden" name="count" id="count" value="<%=c%>" />
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%" nowrap="nowrap">
						<s:submit cssClass="save"
							value="Save Search Criteria" action="CandidateSearch_save"
							onclick="return validateForm();" /> 
						<input type="button"
							class="search" value="Search Criteria"
							onclick="javascript:callsF9(500,325,'CandidateSearch_f9action.action')" />
					<!--
						<s:submit cssClass="reset" value=" Clear" action="CandidateSearch_clear" />
					-->
						<input type="button" value=" Clear" class="reset" onclick="callResetData();">
						<s:submit cssClass="token"
							value="Post Resume >>" onclick="return postResume();"
							action="CandidateSearch_toPostReumeForm" /> 
						<input type="button" value=" Export In Xls " class="token" onclick="callReportForDisp('X');">
					</td>
					<s:if test='%{dashletFlag}'>
					<!-- 
						<s:submit cssClass="cancel" value="Cancel"
							onclick="return cancelFun();" />
							 -->
					</s:if>

				</tr>
			</table>
			<label></label></td>
		</tr>

	</table>

</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>

<script>
	//onLoad();
	enableToDate();
	
	function onLoad(){
		if(document.getElementById('paraFrm_searchFlag').value == "false"){
			document.getElementById('advanceSearch').style.display = 'none';
		}
	}
	
	function enableAdvanceSearch(){
		document.getElementById('advanceSearch').style.display = '';
	}
	
	function enableToDate(){
		var positioningCriteria = document.getElementById('paraFrm_positioningDate').value;
		
		if(positioningCriteria == 'F'){
			document.getElementById('toDateText').style.display = '';
			document.getElementById('toDateLebel').style.display = '';
		}
		else{
			document.getElementById('toDateText').style.display = 'none';
			document.getElementById('toDateLebel').style.display = 'none';
		}
	}
	
	function selectCheckBox(){
		var shortListed = document.getElementById('paraFrm_shortListed').checked;
		var rejected    = document.getElementById('paraFrm_rejected').checked;
		var newResume   = document.getElementById('paraFrm_newResume').checked;
				
		if(shortListed && rejected && newResume){
			document.getElementById('paraFrm_showAll').checked = true;
		}else{
			document.getElementById('paraFrm_showAll').checked = '';
		}
	}
	
	function selectAll(){
		var showAll     = document.getElementById('paraFrm_showAll').checked;
		
		if(showAll){
			document.getElementById('paraFrm_shortListed').checked = true;
			 document.getElementById('paraFrm_rejected').checked = true;
			 document.getElementById('paraFrm_newResume').checked = true;
		}else{
			document.getElementById('paraFrm_shortListed').checked = '';
			 document.getElementById('paraFrm_rejected').checked = '';
			 document.getElementById('paraFrm_newResume').checked = '';
		}
	}
	
	function validateForm(){
		var fieldName = ["paraFrm_requisitionName"];
		var lableName = ["requisition.code"];
		var flag      = ['select'];
		
		if(!validateBlank(fieldName, lableName, flag)) 
		 return false;
		 
		
		if(!validateSearch())return false;
	}
	
	function validateSearch(){
		document.getElementById('paraFrm').target='_self';
		var minExp = document.getElementById("paraFrm_minExperience").value;
		var maxExp = document.getElementById("paraFrm_maxExperience").value;
		var fromDate = document.getElementById("paraFrm_fromDate").value;
		var toDate = document.getElementById("paraFrm_toDate").value;
		
		if(fromDate != ""){
			if(!validateDate("paraFrm_fromDate", "<%=label.get("resume.date")%>"))return false;
		}
		
		if(toDate != ""){
			if(!validateDate("paraFrm_toDate", "<%=label.get("to.date")%>"))return false;
		}
		
		var resumedt=document.getElementById('resume.date').innerHTML.toLowerCase();
		
		if((document.getElementById("paraFrm_positioningDate").value) != ""){
			if(fromDate == "")
			{
				alert("Please enter "+resumedt);
				return false;
			}
			
		}
		
		
		if(minExp != "" && maxExp != ""){
			if(maxExp < minExp){
				alert("Maximum experience should be greater or equal to minimum experience");
				document.getElementById("paraFrm_maxExperience").focus();
				return false;
			}
		}
		return true;
	}
	
	function postResume(){
	document.getElementById('paraFrm').target='_self';
		var count      = document.getElementById("count").value;
		var chkFlag    = false;
		var selectFlag = false;
		
		var fieldName1 = ["paraFrm_requisitionName"];
		var lableName1 = ["requisition.code"];
		var flag1      = ["select"];
		
		if(!validateBlank(fieldName1, lableName1, flag1)) return false;
		
		if(count == 0){
			alert('Please click on Search Candidate');
			return false;
		}
		
		for(var i=0; i<count; i++){
			if(document.getElementById('chk'+i).checked){
				if(document.getElementById('status'+i).value == ""){
					alert("Please change the status of the selected candidate");
					document.getElementById('status'+i).focus();
					return false;
				}
				chkFlag = true;
			}
		}
		if(!chkFlag){
			alert('Please select atleast one candidate in the list');
			return false;
		}
	}
	
	function selectAllCandidates(){
		var count = document.getElementById('count').value;
		
		if(document.getElementById('paraFrm_chkAll').checked){
			for(var i=0; i<count; i++){
				document.getElementById('chk'+i).checked = true;
				document.getElementById(i).value = "Y";
			}
		}else{
			for(var i=0; i<count; i++){
				document.getElementById('chk'+i).checked = '';
				document.getElementById(i).value = "N";
			}
		}
	}
	function deselectAllCandidates(){
		var count = document.getElementById('count').value;
		
		for(var i=0; i<count; i++){
			if(!document.getElementById('chk'+i).checked){
				document.getElementById('paraFrm_chkAll').checked = '';
			}
		}
	}
	
	function showRecord(fileName)
	{
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = "CandidateSearch_viewCV.action?fileName="+fileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
	}
	
	function cancelFun()
	{
	
	 if(document.getElementById('paraFrm_dashletFlag').value=="true")
	 {	
	    document.getElementById('paraFrm').action = "<%=request.getContextPath()%>/common/RecruitmentHome_input.action";
	    document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target ="main";
	}
	}

function callReportForDisp(reportType) {
		document.getElementById('paraFrm').target='_blank';
		document.getElementById('paraFrm').action="CandidateSearch_report.action";
		document.getElementById('paraFrm').submit(); 
}

function callResetData() {
		document.getElementById('paraFrm').target='_self';
		document.getElementById('paraFrm').action="CandidateSearch_clear.action";
		document.getElementById('paraFrm').submit(); 
}
		  	
function viewCandidateDetails(candidateCodeFromCandidateSearch) {
	win=window.open('','win','top=250,left=100,width=900,height=400,scrollbars=yes,status=no,resizable=yes');
	document.getElementById("paraFrm").target="win";
	document.getElementById("paraFrm").action="CandDataBank_callForEdit.action?candidateCodeFromCandidateSearch="+candidateCodeFromCandidateSearch;
	document.getElementById("paraFrm").submit();	
	document.getElementById("paraFrm").target="main"; 
}		  	
</script>
