<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<link rel="stylesheet" type="text/css" href="../pages/common/tabcontent.css" />
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>
<s:form action="TestInterviewReport" id="paraFrm" theme="simple">

	<table width="100%" colspan="4" cellpadding="0" cellspacing="0"
		align="right" class="formbg">
	<s:hidden name="reqsId" /><s:hidden name='reqDate'/>
	<s:hidden name='selectedType'/><s:hidden name="divId"/><s:hidden name="backFlag"/>
	<s:hidden name="aId"/><s:hidden name="requisionFlag"/>
	<s:hidden name="positionFlag"/><s:hidden name="candidateFlag"/>
	<s:hidden name="interviewrFlag"/><s:hidden name="dateFlag"/>
	 <s:hidden name="selectedReq" /> <s:hidden name="selectedReqFlag"/>
	 <s:hidden name="editReqFlag" /><s:hidden name="headerChk"/>
	 <s:hidden name="schdReqCode" /><s:hidden name="listChk"/>
	 <s:hidden name="selecteReqCode" />
	 <s:hidden name="selecteReqName" />
	   <s:hidden name="myChkFlag" id="myChkFlag"  />
	    <s:hidden name="buttonFlag"  id="buttonFlag"  />
	     <s:hidden name="radIntFlag"  id="radIntFlag"/>
	     <s:hidden name="intCheckBoxFlag" id="intCheckBoxFlag" />
	 <s:hidden name="hidIntrvcandCheck" id="hidIntrvcandCheck" />
	
	  
	 
 <!-- flags for headings  of Itrator-->
	  <s:hidden name="candCheckHd"/>  <s:hidden name="dateHd"/>
	   <s:hidden name="interviewrHd"/>     <s:hidden name="recruiterHd"/> 
	  <s:hidden name="EmailCheckHd"/>  <s:hidden name="contactCheckHd"/>
	  <s:hidden name="testTimeHd"/>  <s:hidden name="testVenueHd"/>
  <!-- ---This are for Itrator values flag- -->
		  <s:hidden name="candidateFlag"/><s:hidden name="recruiterFlag"/>
		  <s:hidden name="dateFlag"/><s:hidden name="testTimeFlag"/>
		 <s:hidden name="testVenueFlag"/><s:hidden name="contactFlag"/>
		 <s:hidden name="EmailFlag"/><s:hidden name="testStatusFlag"/>
	<!-- -------------Interview Itrator Heading Flag Filed Name------------------------- -->					 
		<s:hidden name="intrvCandidateHd" /> <s:hidden name="intRndTypeHd" /> 
		<s:hidden name="intrvDateHd" /> <s:hidden name="intrvTimeHd" />
        <s:hidden name="intrvVenueHd" /> <s:hidden name="intrviewerHd" />
        <s:hidden name="recruiterIntHd" /> <s:hidden name="conductHd" />		
<!-- ----------------------------Interview Itrator Value flag------------------------------------------ -->
     <s:hidden name="intrvCandidateFlag" /> <s:hidden name="intRndTypeFlag" />
      <s:hidden name="intrvDateFlag" /> <s:hidden name="intrvTimeFlag" />
     <s:hidden name="intrvVenueFlag" /> <s:hidden name="intrviewerFlag" />
     <s:hidden name="recruiterIntFlag" /><s:hidden name="conductFlag" />
     
     
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0" 
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Scheduled Interview/Test </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
          <td width="100%" colspan="3">
          		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
            		<tr>
	              		<td width="78%">
	              		<input type="button" class="token" theme="simple" value='Generate Report' onclick="call()" />
			 			<input type="button" class="reset" theme="simple" value=" Reset" onclick="resetRep();" />
	             		<input type="button" class="add"  onclick="callVal();"
										theme="simple" value="Save report criteria"  target="main"/>
	             		</td>
	              		
            		</tr>
          		</table>
          </td>
        </tr>
        <tr><td  colspan="3"><table width="100%" border="0" cellpadding="0" cellspacing="0" >
        <td width="13%"> <label  name="save.setting" id="save.setting" ondblclick="callShowDiv(this);"><%=label.get("save.setting")%></label>:</td>
								<td  colspan="1" width="46%"><s:select headerKey="-1" headerValue="--Select--" name="settingSearch"   list="%{hashMap}"  onchange=" return callFilter();"/></td>
			
				</table></td></tr>
        
        
        
        <tr valign="top" >	
			<td width="100%" colspan="3">
			<table width="100%" border="0"  cellpadding="0" cellspacing="0" class="formbg">
				<div id="tabnav">
					<ul>
						<li>
							<a id="filtOpt" href="javascript:callDivLoad('filtOpt', 'filterOption');" >
								<div align="center"><span>Filter Option</span></div>
							</a>
						</li>
						
					</ul>
					
					<ul>
						<li>
							<a id="sortOpt" href="javascript:callDivLoad('sortOpt', 'sortingOption');" >
								<div align="center"><span>Sorting Option</span></div>
							</a>
						</li>
						
					</ul>
					
					<ul>
						<li>
							<a id="colDef" href="javascript:callDivLoad('colDef', 'columnDefinition');" >
								<div align="center"><span>Column Definition</span></div>
							</a>
						</li>
						
					</ul>
					
					
					<ul>
						<li>
							<a id="advFilter" href="javascript:callDivLoad('advFilter', 'advanceFilter');" >
								<div align="center"><span>Advance Filter</span></div>
							</a>
						</li>
						
					</ul>
					
				</div>
        
		<tr valign="top">
			<td width="100%" colspan="3">
			<div id="filterOption">
			
			<table width="100%" border="0" bordercolor="red" cellpadding="0" cellspacing="2" >
			                <tr>								
								<td width="100%" colspan="3" class="formtext"> <b> <label  class = "set"  id="selectFilter"  name="selectFilter" ondblclick="callShowDiv(this);"><%=label.get("selectFilter")%></label></b> </td>
							 </tr> 
<s:hidden name='reqCode'/><s:hidden name="rankId" />
<s:hidden name="requisitionRadioCheck" id="requisitionRadioCheck"/><s:hidden name="positionRadioCheck" id="positionRadioCheck" />
<s:hidden name="candidateRadioCheck"  id="candidateRadioCheck"/>
						<tr>
						<!--  <td width="3"><input type="radio" name="RequisitionRadio" id="RequisitionRadio" onclick=" return SelectReqRadio();"></td>-->
							<td  width="24%" colspan="1"><label  name="reqCode" id="reqCode1" ondblclick="callShowDiv(this);"><%=label.get("reqCode")%></label> :</td>
							<td  width="7%"><s:textfield
								name="reqName" theme="simple" readonly="true" size="25" /></td>
								<td  width="2%" align="left"><div id="reqsnId" > <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="15" align="absmiddle" width="16"
								onclick="return callReqsn();"></div>
							</td>
							<td width="15%"></td>

						</tr>
						<tr>
						<!--  <td width="3"></td>-->
						<td width="24%" colspan="1"></td>
						<td width="7%" colspan="1" align="center"> <strong class="forminnerhead">(OR)</strong>
						</td><td width="20%"></td>
						</tr>
						
						<tr>
						<!--<td width="2"><input type="radio" name="candidateRadio" id="candidateRadio"  onclick=" return SelectCandRadio();" ></td>-->
							<td  width="24%" colspan="1"><label  name="cand.name" id="cand" ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label> :</td>
							<td  width="7%"><s:hidden name="candCode" /><s:textfield
								name="candName" theme="simple" readonly="true" size="25" /></td>
								<td  width="3%"> <div id="CandId"><img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="15" align="absmiddle" width="16"
								onclick="return callCand();"></div>
							</td><td width="20%"></td>

					  </tr>
					  <tr>
					  <!--<td width="3"></td>-->
					  <td width="24%" colspan="1" >
					  </td><td width="7%" colspan="1" align="center"> <strong class="forminnerhead">(OR)</strong></td>
					  <td width="20%"></td>
					  </tr>
					  	<tr>			
					  	<!--<td width="2"><input type="radio" name="positionRadio" id="positionRadio"  onclick=" return SelectPosRadio();"></td>-->			
								<td width="10%" colspan="1" class="formtext"><label  class = "set"  id="position"  name="position" ondblclick="callShowDiv(this);"><%=label.get("position")%></label> :</td>
								<td width="10%" colspan="1" nowrap="nowrap"><s:textfield name="rankName" size="25" theme="simple" readonly="true" /> </td>
								<td  width="2%">
								<div id="PositionId"> <img src="../pages/images/recruitment/search2.gif" height="15" align="absmiddle" width="16" 
								onclick="return  callPosition();"></div></td>						
								<td width="20%"></td>
					</tr>
					  
					  	<tr>	<!--<td width="2"></td>	-->				
								<td width="24%"  colspan="1"><label  class = "set"  id="reqs.date"  name="reqs.date" ondblclick="callShowDiv(this);"><%=label.get("reqs.date")%></td>
								<td width="7%"  nowrap="nowrap"><s:select name="reqsDateCombo" cssStyle="width:70" 
						            			list="#{'':'--Select--', 'O':'On', 'B':'Before', 'A':'After', 'OB':'On Or Before',
						            			'OA':'On Or After','F':'From'}" onchange="enableToDate();"/><s:textfield name="fromDate" size="10" onkeypress="return numbersWithHiphen();" theme="simple" maxlength="10"  readonly="true"/></td>
									<td width="2%"  >
									<div id="fromdateId"><s:a href="javascript:NewCal('paraFrm_fromDate','DDMMYYYY');"><img src="../pages/images/recruitment/Date.gif"  class="iconImage" 
									height="15" align="absmiddle" width="16"> </s:a></div></td>						
								<td width="10%" class="formtext" colspan="1" align="left" id="todateId"><span id="toDateLebel"><label  class = "set"  id="tDate"  name="tDate" ondblclick="callShowDiv(this);"><%=label.get("tDate")%></label>:</span>
								<span id="toDateText"><s:textfield name="toDate" size="10" onkeypress="return numbersWithHiphen();" theme="simple" maxlength="10"  readonly="true"/>
									<s:a href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');"><img src="../pages/images/recruitment/Date.gif"  class="iconImage" 
									height="15" align="absmiddle" width="16"> </s:a></span>
								</td>
				      	</tr>	   
				      	   <tr> <!--<td width="3"></td>-->
								        <td width="22%"  colspan="1"  ><label  class = "set"  id="sel.Requisition"  name="sel.Requisition" ondblclick="callShowDiv(this);"><%=label.get("sel.Requisition")%></label> : </td>
								        <td width="7%"  id="textAreaId" colspan="1"> <s:textarea name="selectedReqName" cols="27" rows="4" readonly="true" theme="simple"/>   </td>
								   
								        <td width="10%"  valign="bottom"  > <div id="selectReqId"><input type="button" class="token" value=" Select Requistion " onclick="callSelectReq('simple');"></div> </td> 
								        <td width="52%"  valign="bottom" > <div id="editReqId"><input type="button"   class="token" value=" Edit Requistion " onclick="callSelectReq('edit');"></div>  </td>
								    
								      </tr>  
					</table>
					</div></td></tr> 
										
		<tr valign="top">
			<td width="100%">
			<div id="sortingOption" style="display: none;">
			
			<table width="100%" border="0" bordercolor="blue" cellpadding="2" cellspacing="2" >
			<tr>								
								<td width="100%" colspan="2" class="formtext"> <b> <label  class = "set"  id="selectSort"  name="selectSort" ondblclick="callShowDiv(this);"><%=label.get("selectSort")%></label></b> </td>
							 </tr> 
			<tr><td colspan="3">
			  <table id="testSortDiv" width="100%" border="0"  cellpadding="2" cellspacing="2" >
			
			<tr>
			<td width="20%"><label  class = "set"  id="sortBy"  name="sortBy" ondblclick="callShowDiv(this);"><%=label.get("sortBy")%></label> :</td>
								<td  colspan="1" nowrap="nowrap"><s:select name="sortBy" cssStyle="width:140" 
						            			list="#{'':'--Select--', 'R':'Requistion Name', 'C':'Candidate Name', 'P':'Position', 'RN':'Recruiter Name',
						            			'SD':'Test Date','E':'Email.Id','CN':'Contact No','TT':'Test Time','TV':'Test Venue','TS':'Test Status'}" /></td></tr>
			<tr><td width="20%" ></td>
								    <s:hidden name="sortRadio" id="sortRadio" />
								
			<td><s:radio name="RadioOne" theme="simple" list="#{'A':'Ascending', 'D':'Dscending'}"   onclick="return checkRadio();"/></td>
			</tr>
			<tr> 
			
			<td width="20%"><label  class = "set"  id="thenBy"  name="thenBy" ondblclick="callShowDiv(this);"><%=label.get("thenBy")%> :</td>
								<td  colspan="1" nowrap="nowrap"><s:select name="thenBY" cssStyle="width:140" 
						            			list="#{'':'--Select--', 'R':'Requistion Name', 'C':'Candidate Name', 'P':'Position', 'RN':'Recruiter Name',
						            			'SD':'Test Date','E':'Email.Id','CN':'Contact No','TT':'Test Time','TV':'Test Venue','TS':'Test Status'}" /></td></tr>
			<tr><td width="20%" ></td>
			
			 <s:hidden name="thenRadio"  id="thenRadio"/>
								
			<td>
			<s:radio name="RadioTwo" theme="simple" list="#{'A':'Ascending', 'D':'Descending'}"    onclick="return checkRadio();"/>
			</td>
	
			</tr>
			<tr>
			
			<td width="20%"><label  class = "set"  id="thenBy1"  name="thenBy" ondblclick="callShowDiv(this);"><%=label.get("thenBy")%> :</td>
								<td  colspan="1" nowrap="nowrap"><s:select name="secondBy"  id="secondBy" cssStyle="width:140" 
						            			list="#{'':'--Select--', 'R':'Requisition Name', 'C':'Candidate Name', 'P':'Position', 'RN':'Recruiter Name',
						            			'SD':'Test Date','E':'Email.Id','CN':'Contact No','TT':'Test Time','TV':'Test Venue','TS':'Test Status'}" /></td></tr>
			<tr><td width="20%" ></td>
								<s:hidden name="thanRadio" id="thanRadio"/>
								
		 <td><s:radio name="RadioThree" theme="simple" list="#{'A':'Ascending', 'D':'Descending'}"    onclick="return checkRadio();"/></td>
			
			</tr>
				</table></td></tr>
				
				<tr><td colspan="3">
			  <table id="intSortDiv" width="100%" border="0" bordercolor="green" cellpadding="2" cellspacing="2" >
			
			<tr>
			<td width="20%"><label  class = "set"  id="sortBy1"  name="sortBy" ondblclick="callShowDiv(this);"><%=label.get("sortBy")%> :</td>
								<td  colspan="1" nowrap="nowrap"><s:select name="sortByInt" cssStyle="width:140" 
						            			list="#{'':'--Select--','RN':'Requisition Name','PO':'Position','IC':'Candidate Name', 'IR':'Interview Round Type','ID':'Interview Date', 'IT':'Interview Time',
						            			'IV':'Interview Venue','IW':'Interviewer','RC':'Recruiter Name','CD':'Conducted'}" /></td></tr>
			<tr><td width="20%" ></td>
								    <s:hidden name="sortRadioInt" id="sortRadioInt"/>
							
			<td><s:radio name="RadioOneInt" theme="simple" list="#{'A':'Ascending', 'D':'Dscending'}"   onclick="return checkIntRadio();"/></td>
			</tr>
			<tr>  
			
			<td width="20%"><label  class = "set"  id="thenBy1"  name="thenBy" ondblclick="callShowDiv(this);"><%=label.get("thenBy")%> :</td>
								<td  colspan="1" nowrap="nowrap"><s:select name="thenBYInt" cssStyle="width:140" 
						            			list="#{'':'--Select--','RN':'Requisition Name','PO':'Position','IC':'Candidate Name', 'IR':'Interview Round Type','ID':'Interview Date', 'IT':'Interview Time',
						            			'IV':'Interview Venue','IW':'Interviewer','RC':'Recruiter Name','CD':'Conducted'}" /></td></tr>
			<tr><td width="20%" ></td>
		
			 <s:hidden name="thenRadioInt" id="thenRadioInt"/>
			<td>
			<s:radio name="RadioTwoInt" theme="simple" list="#{'A':'Ascending', 'D':'Descending'}"    onclick="return checkIntRadio();"/>
			</td>
			</tr>
			<tr>		
			<td width="20%"><label  class = "set"  id="thenBy1"  name="thenBy" ondblclick="callShowDiv(this);"><%=label.get("thenBy")%> :</td>
								<td  colspan="1" nowrap="nowrap"><s:select name="secondByInt" id="secondByInt" cssStyle="width:140" 
						            			list="#{'':'--Select--','RN':'Requisition Name','PO':'Position','IC':'Candidate Name', 'IR':'Interview Round Type','ID':'Interview Date', 'IT':'Interview Time',
						            			'IV':'Interview Venue','IW':'Interviewer','RC':'Recruiter Name','CD':'Conducted'}" /></td></tr>
			<tr><td width="20%" ></td>
								<s:hidden name="thanRadioInt" id="thanRadioInt"/>
								<!--<td><input type="radio" name="radAsc"  id="five" value="R" checked="checked"/> Ascending 
								
								<input type="radio" name="radAsc" id ="six" value="R1" />Descending</td>-->
		 <td><s:radio name="RadioThreeInt" theme="simple" list="#{'A':'Ascending', 'D':'Descending'}"    onclick="return checkIntRadio();"/></td>
			
			</tr>
				</table></td></tr>
					</table>
			</div>
			</td>
			</tr>
			
			<tr valign="top">
			<td width="100%" colspan="3">
			<div id="columnDefinition" style="display: none;">
			<table  width="100%" border="0" cellpadding="2" cellspacing="2" >
			<tr>								
								<td width="100%" colspan="6" class="formtext"> <b> <label  class = "set"  id="selectCoumn"  name="selectCoumn" ondblclick="callShowDiv(this);"><%=label.get("selectCoumn")%></label></b> </td>
							 </tr>  
			<tr><td  width="100%" colspan="3">
			  <table id="testDataDiv" width="100%" border="0"  bordercolor="yellow" cellpadding="2" cellspacing="2" >
				<tr>   
			                 <s:hidden name="hidintDateCheck" id="hidintDateCheck" />
									<s:hidden name="hidemailCheck" id="hidemailCheck" />
									<s:hidden name="hidcontactCheck" id="hidcontactCheck" />
			 						<s:hidden name="hidtimeCheck" id="hidtimeCheck" />
									<s:hidden name="hidtestvenueCheck" id="hidtestvenueCheck" />
									<s:hidden name="hidrecruitNameCheck" id="hidrecruitNameCheck" />
									<s:hidden name="hidtestStatusCheck" id="hidtestStatusCheck" />
									
									<s:hidden name="candCheck" id="candCheck" />
									
									<td width="10%"><input type="checkbox" name="hidcandCheck" id="hidcandCheck"  onclick="callCheck();" />&nbsp;&nbsp;&nbsp;<label
										class="set" name="cand.name" id="cand.name"
										ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label></td>
			
									<td width="30%"><input type="checkbox" name="EmailCheck" id="EmailCheck"  onclick="callCheck();" />&nbsp;&nbsp;&nbsp;<label
										class="set" name="email" id="email"
					                       ondblclick="callShowDiv(this);"><%=label.get("email")%></label></td>
									
									</tr>
									<tr>
									
									
									
									<td width="10%"><input type="checkbox" name="contactCheck" id="contactCheck" onclick="callCheck();"/>
									&nbsp;&nbsp;&nbsp;<label
										class="set" name="contactNo" id="contactNo"
										ondblclick="callShowDiv(this);"><%=label.get("contactNo")%></label></td>
								
								
									<td width="30%"><input type="checkbox"  name="intDateCheck"
										id="intDateCheck" onclick="callCheck();" />&nbsp;&nbsp;&nbsp;<label class="set"
					             name="intDate" id="intDate1"
								ondblclick="callShowDiv(this);"><%=label.get("intDate")%></label></td>
								</tr>
								<tr>
								
								
							          <td width="10%"><input type="checkbox"  name="timeCheck"   id="timeCheck"  onclick="callCheck();"/>
									&nbsp;&nbsp;&nbsp;<label class="set"
										name="time" id="time1"
										ondblclick="callShowDiv(this);"><%=label.get("time")%></label>
									</td>
									<td width="30%"><input type="checkbox"  name="testvenueCheck"
										id="testvenueCheck" onclick="callCheck();" />&nbsp;&nbsp;&nbsp;<label class="set"
					             name="testVenue" id="testVenue"
								ondblclick="callShowDiv(this);"><%=label.get("testVenue")%></label></td>
		                      </tr>
		                      <tr>
								
								
							          <td width="10%"><input type="checkbox"  name="recruitNameCheck"   id="recruitNameCheck"  onclick="callCheck();"/>
									&nbsp;&nbsp;&nbsp;<label class="set"
										name="recruitName" id="recruitName"
										ondblclick="callShowDiv(this);"><%=label.get("recruitName")%></label>
									</td>
									<td width="30%"><input type="checkbox"  name="testStatusCheck" checked="checked"
										id="testStatusCheck" onclick="callCheck();" />&nbsp;&nbsp;&nbsp;Test Status</td>
									
									</tr>
					</table></td></tr>
					<tr><td  width="100%" colspan="3">
					  <table id="interviewDadaDiv" width="100%" border="0" cellpadding="2" cellspacing="2" >
						
					<tr>   
		      	      	<s:hidden name="hidintRoundCheck" id="hidintRoundCheck" />
						<s:hidden name="hidintervewDateCheck" id="hidintervewDateCheck" /><s:hidden name="hidIntervewtimeCheck" id="hidIntervewtimeCheck" />
									<s:hidden name="hidIntervenueCheck" id="hidIntervenueCheck" />
									<s:hidden name="hidInterviewerCheck" id="hidInterviewerCheck" />
									<s:hidden name="hidIntRecruiterCheck" id="hidIntRecruiterCheck" />
									<s:hidden name="hidconductCheck" id="hidconductCheck" />
									<s:hidden name="hidinterviewewCheck" id="hidinterviewewCheck" />
						
									
									
									
									<td width="10%"><input type="checkbox" name="Intrvcandidate" id="Intrvcandidate"  onclick="callInterviewCheck();" />&nbsp;&nbsp;&nbsp;<label
										class="set" name="cand.name" id="cand.name1"
										ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label></td>
			
									<td width="30%"><input type="checkbox" name="intRoundType" id="intRoundType"  onclick="callInterviewCheck();" />&nbsp;&nbsp;&nbsp;<label
										class="set" name="rndType" id="rndType1"
					                       ondblclick="callShowDiv(this);"><%=label.get("rndType")%></label></td>
									
									</tr>
									<tr>
									
									
					
									<td width="10%"><input type="checkbox" name="intervewDate" id="intervewDate" onclick="callInterviewCheck();"/>
									&nbsp;&nbsp;&nbsp;<label
										class="set" name="intDate" id="intDate1"
										ondblclick="callShowDiv(this);"><%=label.get("intDate")%></label></td>
								
	
									<td width="30%"><input type="checkbox"  name="intervewTime"
										id="intervewTime" onclick="callInterviewCheck();" />&nbsp;&nbsp;&nbsp;<label class="set"
					             name="intTime" id="intTime1"
								ondblclick="callShowDiv(this);"><%=label.get("intTime")%></label></td>
								</tr>
								<tr>
								
								
							          <td width="10%"><input type="checkbox"  name="interviewVenue"   id="interviewVenue"  onclick="callInterviewCheck();"/>
									&nbsp;&nbsp;&nbsp;<label class="set"
										name="intven" id="intven"
										ondblclick="callShowDiv(this);"><%=label.get("intven")%></label>
									</td>
									<td width="30%"><input type="checkbox"  name="interviewer"
										id="interviewer" onclick="callInterviewCheck();" />&nbsp;&nbsp;&nbsp;<label class="set"
					             name="intrvr" id="intrvr1"
								ondblclick="callShowDiv(this);"><%=label.get("intrvr")%></label></td>
		                      </tr>
		                      <tr>
								
								
							          <td width="10%"><input type="checkbox"  name="interviewRecruietr"   id="interviewRecruietr"  onclick="callInterviewCheck();"/>
									&nbsp;&nbsp;&nbsp;<label class="set"
										name="rec.name" id="rec.name1"
										ondblclick="callShowDiv(this);"><%=label.get("rec.name")%></label>
									</td>
									<td width="30%"><input type="checkbox"  name="intConduct"
										id="intConduct" onclick="callInterviewCheck();" />&nbsp;&nbsp;&nbsp;<label class="set"
					             name="conduct" id="conduct1"
								ondblclick="callShowDiv(this);"><%=label.get("conduct")%></label></td>
									
							</tr>
									
			</table></td></tr>
			
			</table>
			
			</div></td></tr>
		
			<tr valign="top">
			<td width="100%" colspan="3">
			<div id="advanceFilter" style="display: none;">
			
			<table width="100%" border="0" cellpadding="2" cellspacing="2" ><s:hidden name="intStatusReport" />
			<tr>								
								<td width="100%" colspan="3" class="formtext"> <b> <label  class = "set"  id="selectAdvanceFilter"  name="selectAdvanceFilter" ondblclick="callShowDiv(this);"><%=label.get("selectAdvanceFilter")%></label> </b> </td>
							 </tr> 
			
			
			          <tr id="intStatusDiv">
							<td width="26%"><label  name="IntrvStatus" id="IntrvStatus" ondblclick="callShowDiv(this);"><%=label.get("IntrvStatus")%></label> :</td>
							<td width="10%"><s:select name="intStatus"
								theme="simple" headerKey="-1" cssStyle="width:140"
								list="#{'':'--Select--','Y':'Conducted','N':'Not Conducted','C':'Canceled'}"  />
							</td>
					</tr>
					<tr id="testStatusDiv">
							<td width="26%"><label  name="testStat" id="testStat" ondblclick="callShowDiv(this);"><%=label.get("testStat")%></label>  :</td>
							<td width="10%"><s:select name="testStatus"
								theme="simple" headerKey="-1" cssStyle="width:140"
								list="#{'':'--Select--','Y':'Conducted','N':'Not Conducted','C':'Canceled'}"  />
							</td>
					</tr>
					
					
					
			
						
						<tr id="RecDiv" ><s:hidden name="recId" />
							<td colspan="1" width="25%"><label  name="recrutName" id="recrutName" ondblclick="callShowDiv(this);"><%=label.get("recrutName")%></label> :</td>
							<td colspan="1" width="10%"><s:textfield
								name="recName" theme="simple" readonly="true" size="25" /></td>
								<td> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="15" align="absmiddle" width="16"
								onclick="javascript:callsF9(500,325,'TestInterviewReport_f9Recruiter.action');">
							
							</td>

				
		</tr>
						
						<tr id="IntvrDiv">
							<td colspan="1" width="25%"><label  name="intrvName" id="intrvName" ondblclick="callShowDiv(this);"><%=label.get("intrvName")%></label> :</td>
							<td colspan="1" width="10%"><s:hidden name="intrvCode" /><s:textfield
								name="interviewerName" theme="simple" readonly="true" size="25" /></td>
								<td> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="15" align="absmiddle" width="16"
								onclick="javascript:callsF9(500,325,'TestInterviewReport_f9Interviewer.action');">
							</td>
					  </tr>
					  
					 
			</table>
			</div>
			</td></tr>
			<tr>
			   <td width="100%" colspan="3">
			   		<table width="100%" border="0" bordercolor="blue" cellpadding="0" cellspacing="0" >
			 <tr>
							<td colspan="1" width="21%"><label  name="sch" id="sch" ondblclick="callShowDiv(this);"><%=label.get("sch")%></label> :</td>
							<td colspan="1" width="79%">
							<s:hidden name="schdType" />
							
							
							
							<b><s:radio name="type" theme="simple" value="%{'I'}"  list="#{'I':'Interview','T':'Test'}"  onclick="return checkRadioDiv();"/></b>
													<!--<s:select name="type"
						theme="simple" headerKey="-1" cssStyle="width:70"
								list="#{'':'--Select--','I':'Interview','T':'Test'}"  />-->

							</td>

					</tr>
			</table></td></tr>
			
			</table></td></tr>
			  
					
  	<tr>
			   <td colspan="3" widthy="100%">
			   		<table width="100%" border="0"  cellpadding="0" cellspacing="0" class="formbg">
			   		              <tr>								
								 <td width="100%" colspan="2" class="formtext"> <b>Display option</b> </td>
							   </tr>  
			   		               
			   		              
			   				 <tr><td align="left" width="21%">
								
								 <input type="radio" name="onScrn" id="onScrn" value="V"  onclick="return viewOnScrn();"/>&nbsp;&nbsp;<label  name="viewScreen" id="viewScreen" ondblclick="callShowDiv(this);"><%=label.get("viewScreen")%></label></td>
							<td width="20%">	
						    </td><td width="59" ></td>
						    </tr>
									
							<tr><td width="21%">
								
								<input type="radio" name="genRep" id="genRep"  onclick="return generateDropDown();"/>&nbsp;&nbsp;<label  name="report.type" id="report.type" ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label></td>
								
							<td id="showDropDown" width="20%" align="left"><s:select name="seltype" cssStyle="width:140" list="#{'':'--Select--','Pdf':'Pdf','Txt':'Txt','Xls':'Xls'}"  />
								
							
							</td><td width="59" ></td>
							
							</tr>
							
							<tr><td  width="21%">
			   		               <label  name="setting.name" id="setting.name" ondblclick="callShowDiv(this);"><%=label.get("setting.name")%></label> :</td>
			   		               <td width="20%" align="left"><s:textfield
								name="settingName" theme="simple" readonly="false" size="25" maxlength="50" />
			   		               
			   		               </td>
			   		               <td width="59" ></td>
			   		               </tr> </table></td></tr>
					
			<tr align="left">
				<td colspan="3">
					<input type="button" class="token" theme="simple" value='Generate Report' onclick="call()" />
					 <input type="button" class="reset" theme="simple" value=" Reset" onclick="resetRep();"/>
						<input type="button" class="add"  onclick="callVal();"
										theme="simple" value="Save report criteria"  target="main"/></td>
			</tr>
		
</td></tr></table>
</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script>
enableToDate();	
checkRadioDiv()
genReport();
callBack();
//following function is called when Back button is clicked in the viewIntTestSchedule.jsp
function callBack(){

if(document.getElementById('paraFrm_backFlag').value=="true"){
       var divID = document.getElementById('paraFrm_divId').value;
       var aID = document.getElementById('paraFrm_aId').value;
       if(aID=="" || aID=="null"){
       	callDivLoad('filtOpt', 'filterOption');
       }else{
       callDivLoad(aID,divID);
       }
		
     }else{
     
     callDivLoad('filtOpt', 'filterOption');
     } 
}

	function callOnLoad() {
		var divID = document.getElementById('paraFrm_divId').value;
		var aID = document.getElementById('paraFrm_aId').value;
		
		alert(aID);
		if(divID == '') {
			divID = 'filterOption';
			
		}
		
		if(aID == '') {
			aID = 'filtOpt';
		}
		
		document.getElementById('filterOption').style.display = 'none';
		document.getElementById('sortingOption').style.display = 'none';
		document.getElementById('columnDefinition').style.display = 'none';		
		document.getElementById('advanceFilter').style.display = 'none';		
		document.getElementById(divID).style.display = '';
		document.getElementById('filtOpt').className = '';
		document.getElementById('sortOpt').className = '';
		document.getElementById('colDef').className = '';
		document.getElementById('advFilter').className = '';
		document.getElementById(aID).className = 'on';
		
	}
	
	
	function callDivLoad(aID, divID) {
	
	    var settingVal=document.getElementById('paraFrm_settingSearch').value;
		document.getElementById('filterOption').style.display = 'none';
		document.getElementById('sortingOption').style.display = 'none';
		document.getElementById('columnDefinition').style.display = 'none';	
		document.getElementById('advanceFilter').style.display = 'none';
			
				
		document.getElementById('filtOpt').className = '';
		document.getElementById('sortOpt').className = '';
		document.getElementById('colDef').className = '';
		document.getElementById('advFilter').className = '';
		document.getElementById(aID).className = 'on';
		document.getElementById(divID).style.display = '';
		document.getElementById('paraFrm_divId').value =  divID;
		document.getElementById('paraFrm_aId').value =  aID;
		
		 if(settingVal=="-1")
	 {  
       document.getElementById('editReqId').style.display='none';   
	 }else{
	     document.getElementById('editReqId').style.display='';   
	 }
		
		
		//  ==========Test Data Check Box Flag=========
		if(document.getElementById('myChkFlag').value)
	  {
	    if( document.getElementById('hidemailCheck').value=='Y')
	         document.getElementById('EmailCheck').checked=true;
       if(document.getElementById('candCheck').value=='Y')
		     document.getElementById('hidcandCheck').checked=true;
		if(document.getElementById('hidcontactCheck').value=='Y')
		document.getElementById('contactCheck').checked=true;
		if( document.getElementById('hidintDateCheck').value=='Y')
		document.getElementById('intDateCheck').checked =true;
	   if(document.getElementById('hidtimeCheck').value=='Y')
		document.getElementById('timeCheck').checked=true;
		if(document.getElementById('hidtestvenueCheck').value=='Y')
		document.getElementById('testvenueCheck').checked=true;
		if(document.getElementById('hidrecruitNameCheck').value=='Y')
		document.getElementById('recruitNameCheck').checked=true;
		
		}
		else{
		document.getElementById('EmailCheck').checked=true;
        document.getElementById('hidEmailCheck').value='Y'
		document.getElementById('hidcandCheck').checked=true;
		document.getElementById('candCheck').value='Y'
		document.getElementById('contactCheck').checked=true;
		document.getElementById('hidcontactCheck').value='Y'
		document.getElementById('intDateCheck').checked =true;
	    document.getElementById('hidintDateCheck').value='Y'
		document.getElementById('timeCheck').checked=true;
		document.getElementById('hidtimeCheck').value='Y'
		document.getElementById('testvenueCheck').checked=true;
		document.getElementById('hidtestvenueCheck').value='Y'
		document.getElementById('recruitNameCheck').checked=true;
		document.getElementById('hidrecruitNameCheck').value='Y'
		}
		 
	  if(document.getElementById('buttonFlag').value){
	    if(document.getElementById('sortRadio').value=='D')
		     document.getElementById('paraFrm_RadioOneD').checked=true;
		     else
		      document.getElementById('paraFrm_RadioOneA').checked=true;
	   if(document.getElementById('thenRadio').value=='D')
		   document.getElementById('paraFrm_RadioTwoD').checked=true;
		   else
		    document.getElementById('paraFrm_RadioTwoA').checked=true;
	  if( document.getElementById('thanRadio').value=='D')
		   document.getElementById('paraFrm_RadioThreeD').checked=true;
		   else
		    document.getElementById('paraFrm_RadioThreeA').checked=true;
		}
	else{
		document.getElementById('paraFrm_RadioOne').checked=true;
	    document.getElementById('sortRadioA').value='A'
	    document.getElementById('paraFrm_RadioTwo').checked=true;
	    document.getElementById('thenRadioA').value='A'
	    document.getElementById('paraFrm_RadioThree').checked=true;
	   document.getElementById('thanRadioA').value='A'
	   }
  if(document.getElementById('radIntFlag').value){
	  
	   if(document.getElementById('sortRadioInt').value=='D')
	          document.getElementById('paraFrm_RadioOneIntD').checked=true;
	       else
	          document.getElementById('paraFrm_RadioOneIntA').checked=true;
	   if(document.getElementById('thenRadioInt').value=='D')
	            document.getElementById('paraFrm_RadioTwoIntD').checked=true;
	            else
	            document.getElementById('paraFrm_RadioTwoIntA').checked=true;
	   if( document.getElementById('thanRadioInt').value=='D')
	       document.getElementById('paraFrm_RadioThreeIntD').checked=true;
	      else  document.getElementById('paraFrm_RadioThreeIntA').checked=true;
	   
	           
	  }else{
	   document.getElementById('paraFrm_RadioOneInt').checked=true;
	    document.getElementById('paraFrm_sortRadioIntA').value='A'
	    document.getElementById('paraFrm_RadioTwoInt').checked=true;
	    document.getElementById('paraFrm_thenRadioIntA').value='A'
	    document.getElementById('paraFrm_RadioThreeInt').checked=true;
	   document.getElementById('paraFrm_thanRadioIntA').value='A'
	   }
	
	//=================Interview Data Check Box Flag===========	 
	
	     	if(document.getElementById('intCheckBoxFlag').value){
	     	
           if(  document.getElementById('hidIntrvcandCheck').value=='Y')
                   document.getElementById('Intrvcandidate').checked='true';
           
           if(  document.getElementById('hidintRoundCheck').value=='Y')
                 	 document.getElementById('intRoundType').checked=true;
           if( document.getElementById('hidintervewDateCheck').value=='Y')
                 	 document.getElementById('intervewDate').checked=true;
          if(document.getElementById('hidIntervewtimeCheck').value=='Y')
                 	 document.getElementById('intervewTime').checked=true;
          if( document.getElementById('hidIntervenueCheck').value=='Y')
                 	   document.getElementById('interviewVenue').checked=true;
           if(document.getElementById('hidIntervenueCheck').value=='Y')
                 	  document.getElementById('interviewer').checked=true;
           if(document.getElementById('hidIntRecruiterCheck').value=='Y')
                 	  document.getElementById('interviewRecruietr').checked=true;
          if( document.getElementById('hidconductCheck').value=='Y')
                 	    document.getElementById('intConduct').checked=true;
                 	 
	     	
	     	}
	     	else{
	     	
	    document.getElementById('Intrvcandidate').checked=true;
        document.getElementById('hidIntrvcandCheck').value='Y'
        document.getElementById('intRoundType').checked=true;
        document.getElementById('hidintRoundCheck').value='Y'
        document.getElementById('intervewDate').checked=true;
        document.getElementById('hidintervewDateCheck').value='Y'
        document.getElementById('intervewTime').checked=true;
        document.getElementById('hidIntervewtimeCheck').value='Y'
        document.getElementById('interviewVenue').checked=true;
        document.getElementById('hidIntervenueCheck').value='Y'
        document.getElementById('interviewer').checked=true;
        document.getElementById('hidinterviewewCheck').value='Y'
        document.getElementById('interviewRecruietr').checked=true;
        document.getElementById('hidIntRecruiterCheck').value='Y'
        document.getElementById('intConduct').checked=true;
        document.getElementById('hidconductCheck').value='Y'
	  		}
	}


function enableToDate(){
		     var positioningCriteria = document.getElementById('paraFrm_reqsDateCombo').value;
		//document.getElementById('paraFrm_typeFlag').value="true";
		if(positioningCriteria == 'F'){
			document.getElementById('toDateText').style.display = '';
			document.getElementById('toDateLebel').style.display = '';
		}
		else{
			document.getElementById('toDateText').style.display ='none';
			document.getElementById('toDateLebel').style.display ='none';
		}
	}
	


function genReport(){
	    document.getElementById('onScrn').checked=true;
		document.getElementById('showDropDown').style.display ='none';

}


function callReqsn(){
		
		document.getElementById('CandId').style.display ='none';
		document.getElementById('PositionId').style.display ='none';
		//document.getElementById('fromDateId').style.display ='none';
		document.getElementById('paraFrm_rankName').value="";
		document.getElementById('paraFrm_rankId').value="";
		document.getElementById('paraFrm_candName').value="";
		document.getElementById('paraFrm_candCode').value="";
		document.getElementById('selectReqId').style.display ='none';
		document.getElementById('editReqId').style.display ='none';
		document.getElementById('fromdateId').style.display ='none';
		document.getElementById('todateId').style.display ='none';
		
		callsF9(500,325,'TestInterviewReport_f9Reqsn.action');
}	


function callCand(){
		
		document.getElementById('reqsnId').style.display ='none';
		document.getElementById('PositionId').style.display ='none';
		document.getElementById('paraFrm_rankName').value="";
		document.getElementById('paraFrm_rankId').value="";
		document.getElementById('paraFrm_reqName').value="";
		document.getElementById('paraFrm_reqCode').value="";
		callsF9(500,325,'TestInterviewReport_f9Candidate.action');
}	
function callPosition(){
		document.getElementById('CandId').style.display ='none';
		document.getElementById('reqsnId').style.display ='none';
		document.getElementById('paraFrm_candName').value="";
		document.getElementById('paraFrm_candCode').value="";
		document.getElementById('paraFrm_reqName').value="";
		document.getElementById('paraFrm_reqCode').value="";
		callsF9(500,325,'TestInterviewReport_f9Position.action');
}	

function viewOnScrn(){
		document.getElementById('genRep').checked=false;
		//document.getElementById('paraFrm_type').value="";
	    document.getElementById('showDropDown').style.display ='none';

}


function generateDropDown(){
		document.getElementById('onScrn').checked=false;
		document.getElementById('showDropDown').style.display ='';

}



 function call(){ 

	 var frmDate=document.getElementById("paraFrm_fromDate").value;
  	var toDate=document.getElementById("paraFrm_toDate").value;
  	/*var type=document.getElementById("paraFrm_type").value;
  	if(document.getElementById("paraFrm_type").value==""){
  	 alert('Please select scheduled');
  	 return false;
  	
  	}*/
 

	var reqsDateCombo=document.getElementById("paraFrm_reqsDateCombo").value;
	if(reqsDateCombo=="F"){
	
	if(frmDate==""){
	
	alert('please enter from date');
	
	return false;
	}
	 else if(toDate==""){
	alert("please enter "+document.getElementById('tDate').innerHTML.toLowerCase());
	return false;
	
	}
	}
	
 
  	 if((frmDate!="") || (toDate!=""))
  	  {
    	if(!validateDate('paraFrm_fromDate','reqs.date'))
    	{
		             return false;
		}
		  
		if(!validateDate('paraFrm_toDate','tDate'))
		{
             return false;	 
          }     
	}
		if(!dateDifferenceEqual(frmDate,toDate,"paraFrm_toDate",'reqs.date','tDate'))
		{
			return false;
		}
	/*if((!(document.getElementById("onScrn").checked)) || (!(document.getElementById("genRep").checked)))
	{
  			alert("Please select view on screen or report type");
  			return false;
  	 	}*/
  	 var seltype=document.getElementById("paraFrm_seltype").value;
  	 
  	if(document.getElementById("genRep").checked)
  	{ 
  	 
  	 if(document.getElementById("paraFrm_seltype").value=="")
  	 	{
			alert('Please select report type');
			document.getElementById("paraFrm_seltype").focus;
			return false;
		}
		else
		{   document.getElementById('paraFrm').target="_blank"; 
			document.getElementById('paraFrm').action='TestInterviewReport_generateReport.action?seltype='+seltype;	
			document.getElementById('paraFrm').submit();
			document.getElementById('paraFrm').target="main"; 
		}
	}
	
	else {
			document.getElementById('paraFrm').action='TestInterviewReport_callJspView.action';	
			document.getElementById('paraFrm').submit();
			document.getElementById('paraFrm').target="main"; 	 
		}
}
	function resetRep(){
			document.getElementById('paraFrm_rankId').value="";
			document.getElementById('paraFrm_rankName').value="";
			document.getElementById('paraFrm_seltype').value="";
			document.getElementById('paraFrm_settingName').value="";
			document.getElementById('paraFrm_selectedReqName').value="";
			
			document.getElementById('buttonFlag').value=false;
			document.getElementById('radIntFlag').value=false;
			document.getElementById('paraFrm_reqCode').value="";
			document.getElementById('paraFrm_candName').value="";
			document.getElementById('paraFrm_candCode').value="";
			document.getElementById('paraFrm_fromDate').value="";
			document.getElementById('paraFrm_toDate').value="";
			document.getElementById('paraFrm_reqName').value="";
			document.getElementById('paraFrm_sortBy').value="";
		    document.getElementById('paraFrm_intStatus').value="";
		    document.getElementById('secondBy').value="";
		    document.getElementById('paraFrm_sortByInt').value="";
		    document.getElementById('paraFrm_testStatus').value="";
		    document.getElementById('secondByInt').value="";
		    document.getElementById('paraFrm_thenBYInt').value="";
		    document.getElementById('paraFrm_thenBY').value="";
		    document.getElementById('paraFrm_recName').value="";
		    document.getElementById('paraFrm_recId').value="";
		    document.getElementById('paraFrm_intrvCode').value="";
		    document.getElementById('paraFrm_interviewerName').value="";    
		    document.getElementById('paraFrm_reqsDateCombo').value="";
		    document.getElementById('intCheckBoxFlag').value=false;
		    document.getElementById('myChkFlag').value=false;
		    
		   
  //  document.getElementById('paraFrm').target ="main"; 
		     document.getElementById('paraFrm').action = "TestInterviewReport_input.action";
		     document.getElementById('paraFrm').submit(); 
   
    
    
    
       
	}
	
	
	

function callCheck(){
    
  
		    if(document.getElementById('hidcandCheck').checked ){
				   document.getElementById('candCheck').value='Y'
				}
			else{	
				  document.getElementById('candCheck').value='N';
				}
				
			if(document.getElementById('EmailCheck').checked ) {
		        document.getElementById('hidemailCheck').value='Y'
				}
			else{		
				  document.getElementById('hidemailCheck').value='N';
				}
			if(document.getElementById('timeCheck').checked ){
				   document.getElementById('hidtimeCheck').value='Y'
				}
			else{		
				  document.getElementById('hidtimeCheck').value='N';
				}
				
			if(document.getElementById('intDateCheck').checked ){
				   document.getElementById('hidintDateCheck').value='Y'
				}
			else{		
				  document.getElementById('hidintDateCheck').value='N';
				}
			
			if(document.getElementById('testvenueCheck').checked ){
				   document.getElementById('hidtestvenueCheck').value='Y'
				}
			else{		
				  document.getElementById('hidtestvenueCheck').value='N';
				}
			if(document.getElementById('recruitNameCheck').checked ){
				   document.getElementById('hidrecruitNameCheck').value='Y'
				}
			else{		
				  document.getElementById('hidrecruitNameCheck').value='N';
				}
		  if(document.getElementById('contactCheck').checked ){
				   document.getElementById('hidcontactCheck').value='Y'
				}
			else{		
				  document.getElementById('hidcontactCheck').value='N';
				}
				if(document.getElementById('testStatusCheck').checked ){
				   document.getElementById('hidtestStatusCheck').value='Y'
				}
			else{		
				  document.getElementById('hidtestStatusCheck').value='N';
				}
				
}
   
  
function callInterviewCheck(){
    
		    if(document.getElementById('Intrvcandidate').checked ){
				   document.getElementById('hidIntrvcandCheck').value='Y'
				}
			else{	
				  document.getElementById('hidIntrvcandCheck').value='N';
				}
				
			if(document.getElementById('intRoundType').checked ) {
		        document.getElementById('hidintRoundCheck').value='Y'
				}
			else{		
				  document.getElementById('hidintRoundCheck').value='N';
				}
			if(document.getElementById('intervewDate').checked ){
				   document.getElementById('hidintervewDateCheck').value='Y'
				}
			else{		
				  document.getElementById('hidintervewDateCheck').value='N';
				}
				
			if(document.getElementById('intervewTime').checked ){
				   document.getElementById('hidIntervewtimeCheck').value='Y'
				}
			else{		
				  document.getElementById('hidIntervewtimeCheck').value='N';
				}
			if(document.getElementById('interviewVenue').checked ){
				   document.getElementById('hidIntervenueCheck').value='Y'
				}
			else{		
				  document.getElementById('hidIntervenueCheck').value='N';
				}
		  if(document.getElementById('interviewer').checked ){
				   document.getElementById('hidinterviewewCheck').value='Y'
				}
			else{		
				  document.getElementById('hidinterviewewCheck').value='N';
				}
				if(document.getElementById('interviewRecruietr').checked ){
				   document.getElementById('hidIntRecruiterCheck').value='Y'
				}
			else{		
				  document.getElementById('hidIntRecruiterCheck').value='N';
				}
				if(document.getElementById('intConduct').checked ){
				   document.getElementById('hidconductCheck').value='Y'
				}
			else{		
				  document.getElementById('hidconductCheck').value='N';
				}
}
function checkRadio(){

			  if (document.getElementById('paraFrm_RadioOneD').checked ) {
			     document.getElementById('sortRadio').value='D'
			   
			    
			 }
			 else{
			    document.getElementById('sortRadio').value='A';
			    }
			 if (document.getElementById('paraFrm_RadioTwoD').checked ) {
			             document.getElementById('thenRadio').value='D'
			    }
			 else {
			    document.getElementById('thenRadio').value='A';
			     }
			   if (document.getElementById('paraFrm_RadioThreeD').checked ) {
			     document.getElementById('thanRadio').value='D'
			  }
			 else {
			    document.getElementById('thanRadio').value='A';
			   
			   }
			}
			 
			function checkIntRadio(){
			
			  if (document.getElementById('paraFrm_RadioOneIntD').checked ) {
			        document.getElementById('sortRadioInt').value='D'
			    }
			 else{
			    document.getElementById('sortRadioInt').value='A';
			     
			   }
			   
			   if (document.getElementById('paraFrm_RadioTwoIntD').checked ) {
			    document.getElementById('thenRadioInt').value='D'
			      
			 }
			 else {
			    document.getElementById('thenRadioInt').value='A';
			    }
			   if (document.getElementById('paraFrm_RadioThreeIntD').checked ) {
			     document.getElementById('thanRadioInt').value='D'
			   }
			 else {
			    document.getElementById('thanRadioInt').value='A';
			    
			   }
}
 function callDivRadio(){
 
			  if (document.getElementById('paraFrm_typeI').checked ) {
			     document.getElementById('paraFrm_schdType').value='I';
			     document.getElementById('testDataDiv').style.display ='none';	
				 document.getElementById('interviewDadaDiv').style.display = '';	
			    }
			  if (document.getElementById('paraFrm_typeT').checked ) {
			    document.getElementById('paraFrm_schdType').value='T';
			    document.getElementById('testDataDiv').style.display = '';	
			    document.getElementById('paraFrm_typeI').checked =false;
			      document.getElementById('typeFlag').value="false";
				document.getElementById('interviewDadaDiv').style.display ='none';	
			     
			   }    
 }
 
 
 
  function callSelectReq(status)
 {
	  if(status=='edit'){
	    document.getElementById('paraFrm_editReqFlag').value="true"; 
	    
	    
	  }else{
		   document.getElementById('paraFrm_editReqFlag').value="false"; 
		   document.getElementById('paraFrm_selectedReqName').value="";
		   document.getElementById('paraFrm_selectedReq').value=""; 
		   document.getElementById('editReqId').style.display ='';
		   document.getElementById('CandId').style.display ='none';
			document.getElementById('PositionId').style.display ='none';
			
			document.getElementById('paraFrm_rankName').value="";
			document.getElementById('paraFrm_rankId').value="";
			document.getElementById('paraFrm_candName').value="";
			document.getElementById('paraFrm_candCode').value="";
			
			document.getElementById('fromdateId').style.display ='none';
			document.getElementById('todateId').style.display ='none';
	    }  
	        document.getElementById('reqsnId').style.display ='none';
		   document.getElementById('paraFrm').target="wind";
		   var wind = window.open('','wind','width=600,height=400,scrollbars=yes,resizable=yes,menubar=no,top=200,left=150');	 
		   document.getElementById('paraFrm').action="TestInterviewReport_displayReq.action";
		   document.getElementById('paraFrm').submit();
		   document.getElementById('paraFrm').target="";
 }
 
 function checkRadioDiv(){
 
 if (document.getElementById('paraFrm_typeI').checked ) {
	     document.getElementById('paraFrm_schdType').value='I';
	     document.getElementById('testDataDiv').style.display ='none';	
	     document.getElementById('testSortDiv').style.display ='none';	
	     document.getElementById('RecDiv').style.display ='none';	
		 document.getElementById('IntvrDiv').style.display = '';
		 document.getElementById('testStatusDiv').style.display ='none';
		 document.getElementById('intStatusDiv').style.display = '';
		
		  document.getElementById('paraFrm_intStatus').value="";
		 document.getElementById('interviewDadaDiv').style.display = '';
		 document.getElementById('intSortDiv').style.display = '';	
    } 
  if (document.getElementById('paraFrm_typeT').checked ) {
	    document.getElementById('paraFrm_schdType').value='T';
	    document.getElementById('testDataDiv').style.display = '';	
	    document.getElementById('testSortDiv').style.display ='';	
	    document.getElementById('testStatusDiv').style.display ='';
	    document.getElementById('RecDiv').style.display ='';	
	    document.getElementById('paraFrm_typeI').checked =false;
	  	document.getElementById('interviewDadaDiv').style.display ='none';
	    document.getElementById('intSortDiv').style.display = 'none';
        document.getElementById('IntvrDiv').style.display = 'none';
        document.getElementById('intStatusDiv').style.display ='none';	
          document.getElementById('paraFrm_testStatus').value="";	
   
   } 
 }
 function callVal(){
 if(document.getElementById("paraFrm_settingName").value=="")
  	 	{
			alert('Please enter setting name');
			return false;
			
		}
	
		var reqCode=document.getElementById('paraFrm_settingSearch').value;
		document.getElementById('paraFrm_schdReqCode').value=reqCode;
		document.getElementById('paraFrm').action='TestInterviewReport_saveSetting.action';
		document.getElementById('paraFrm').submit();
	
	
	}
	
function callFilter(){
		var reqCode=document.getElementById('paraFrm_settingSearch').value;
		document.getElementById('paraFrm_schdReqCode').value=reqCode; 
		document.getElementById('paraFrm').action='TestInterviewReport_getFilterDetails.action';
		document.getElementById('paraFrm').submit();
}
//SelectReqRadio();
 function SelectReqRadio(){
 
		 if( document.getElementById('RequisitionRadio').checked){
		 document.getElementById('RequisitionRadioCheck').value='Y';
		 document.getElementById('reqsnId').style.display ='';	
		  
		 document.getElementById('candidateRadio').checked=false;
		 document.getElementById('positionRadio').checked=false;
		 document.getElementById('CandId').style.display ='none';	
		  document.getElementById('PositionId').style.display ='none';	
		  document.getElementById('fromdateId').style.display ='none';
		  document.getElementById('selectReqId').style.display ='none';
		  document.getElementById('editReqId').style.display ='none';
		  }
		  else{
		  document.getElementById('RequisitionRadioCheck').value='N';
		  }
  
 }
 
 
 function SelectCandRadio(){
		 if( document.getElementById('candidateRadio').checked){
		  document.getElementById('candidateRadioCheck').value='Y';
		  document.getElementById('CandId').style.display ='';	
		  document.getElementById('candidateRadio').checked=true;
		  document.getElementById('RequisitionRadio').checked=false;
		 document.getElementById('positionRadio').checked=false;
		 document.getElementById('reqsnId').style.display ='none';	
		 document.getElementById('PositionId').style.display ='none';
		 
		 document.getElementById('fromdateId').style.display ='';
		  document.getElementById('selectReqId').style.display ='';
		  document.getElementById('editReqId').style.display ='';
		 
		 }
	 else{
	  document.getElementById('candidateRadioCheck').value='N';
	  }
 }
 
 
 
 function SelectPosRadio(){
			 if( document.getElementById('PositionRadio').checked){
			 document.getElementById('positionRadioCheck').value='Y';
			 document.getElementById('PositionId').style.display ='';
			  document.getElementById('positionRadio').checked=true;
			 document.getElementById('candidateRadio').checked=false;
			 document.getElementById('RequisitionRadio').checked=false;
			 document.getElementById('CandId').style.display ='none';	
			  document.getElementById('reqsnId').style.display ='none';	
			 document.getElementById('fromdateId').style.display ='';
			  document.getElementById('selectReqId').style.display ='';
			  document.getElementById('editReqId').style.display ='';
			}
			else{
			  document.getElementById('positionRadioCheck').value='N';
			  }
			 
 }
 
 
</script>