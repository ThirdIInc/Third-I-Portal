<!--@author KrishD @date 23-04-2010 -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="TravelMainSchdlrReport" id="paraFrm" theme="simple">

	<s:hidden name="reportId" />

	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">
					Report For Main Scheduler </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- BUTTON PANEL BEGINS -->
		<tr>
			<s:hidden name='backFlag' />

			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><input type="button" class="token"
						theme="simple" value="  Generate Report"
						onclick=" return generateReport();" /> <input type="button"
						class="reset" theme="simple" value=" Reset" onclick="resetForm()" />
					<input name="button" type="button" value="Save report criteria"
						class="save" onclick="saveReport()" /> <input name="button"
						type="button" value="Search" class="search" onclick="callSearch()" />


					</td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<script>
		
		function callSearch(){
	     myWin = window.open('','myWin','top=260,left=250,width=700,height=400,scrollbars=no,toolbars=no,status=yes,resizable=yes');
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action = 'TravelMainSchdlrReport_searchSavedReport.action';
		document.getElementById("paraFrm").submit();
	
	}
		
		</script>
		<!-- BUTTON PANEL ENDS -->

		<!-- SEARCH AND REPORT TITLE FIELDS -->
		<tr>
			<td width="100%">
			<table border="0" cellpadding="0" cellspacing="0" class="formbg"
				width="100%">
				<tr>
					<td><label class="set" name="report.title" id="report.title"
						ondblclick="callShowDiv(this);"><%=label.get("report.title")%></label>:</td>
					<td colspan="2"><s:textfield size="30" maxlength="30"
						name="reportTitle" onkeypress="return allCharacters();" /></td>
				</tr>
			</table>
			</td>
		</tr>



		<!-- SEARCH AND REPORT TITLE FIELDS ENDS -->


		<!-- NAVIGATION TABS BEGINS -->
		<tr>
			<td>
			<div id="tabnav" style="vertical-align: bottom">
			<ul>
				<li id="list1"><a href="#" id="menuid1" class="on"
					onclick="showCurrent('menuid1','first')"> <span>Filter</span></a></li>
				<li id="list2"><a href="#" id="menuid2"
					onclick="showCurrent( 'menuid2','second')"> <span>Column
				Definition </span></a></li>
				<li id="list3"><a href="#" id="menuid3"
					onclick="showCurrent( 'menuid3','third')"> <span>Sorting
				Option </span></a></li>
				<li id="list3"><a href="#" id="menuid4"
					onclick="showCurrent( 'menuid4','fourth')"> <span>Advance
				Filter </span></a></li>
			</ul>
			</div>
			</td>
		</tr>
		<!-- NAVIGATION TABS ENDS -->

		<tr>
			<td width="100%" colspan="7"><!-- DIV FIRST BEGINS - FILTERS -->
			<div id="first">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td colspan="7" class="text_head"><strong
						class="forminnerhead"> <label class="set"
						name="search.criteria" id="search.criteria"
						ondblclick="callShowDiv(this);"><%=label.get("search.criteria")%></label>:
					</strong></td>
				</tr>				
				
				
				<!-- application date  -->
				<tr>
					<td colspan="1" height="22" width="20%"><label class="set"
						name="tms.applDate" id="tms.applDate"
						ondblclick="callShowDiv(this);"><%=label.get("tms.applDate")%></label>:</td>				
					
					<td colspan="1" align="left" width="10%"><s:select
						theme="simple" name="applDateSelect" value="%{applDateSelect}"
						list="#{'':'--Select--','ON':'On','BF':'Before','AF':'After','OB':'On before',
						'OA':'On after','BN':'Between'}"
						onchange="call2DateDisp4FromDate(this,'1','applicationDate' );" /></td>

					<td colspan="1" width="10%">&nbsp;<s:textfield size="9"
						onkeypress="return numbersWithHiphen();" name="applDateFrm"
						onblur="setText('paraFrm_applDateFrm','dd-mm-yyyy')"
						onfocus="clearText('paraFrm_applDateFrm','dd-mm-yyyy')"
						maxlength="10" /></td>
					<td>&nbsp;<s:a
						href="javascript:NewCal('paraFrm_applDateFrm','DDMMYYYY');">
						<img class="iconImage" src="../pages/images/recruitment/Date.gif"
							width="16" height="16" border="0" align="absmiddle" />
					</s:a></td>
					<td colspan="3">

					<table cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td colspan="1" id="applDTToDateDiv" width="15%" align="center"><strong>AND
							</strong></td>
							<td width="10%" colspan="1" id="applDTToDateDivLabel" height="22"><s:textfield
								size="9" onkeypress="return numbersWithHiphen();"
								name="applDateTo"
								onblur="setText('paraFrm_applDateTo','dd-mm-yyyy')"
								onfocus="clearText('paraFrm_applDateTo','dd-mm-yyyy')"
								maxlength="10" /></td>
							<td id="applDTToDatePicker">&nbsp;<s:a
								href="javascript:NewCal('paraFrm_applDateTo','DDMMYYYY');">
								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" />
							</s:a></td>
						</tr>

					</table>
					</td>
				</tr>
				
				<!-- STATUS -->
				<tr>

					<td width="20%" colspan="1"><label class="set"
						name="tms.status" id="tms.status" ondblclick="callShowDiv(this);"><%=label.get("tms.status")%></label>:</td>
					<td colspan="6"><s:select name="applStatus" id="sta"
						headerKey="" headerValue="--Select--" cssStyle="width:100"
						list="#{'P':'Pending','A':'Assigned','S':'Scheduled','CC':'Cancelled'}"
						value="%{applStatus}" /></td>
				</tr>


				<!-- APPROVER NAME -->
				<tr>
					<td colspan="1" width="20%"><label class="set"
						name="tms.approver" id="tms.approver"
						ondblclick="callShowDiv(this);"><%=label.get("tms.approver")%></label>
					:</td>
					<td colspan="6"><s:hidden name="apprvrToken" /> <s:hidden
						name="apprvrCode" /> <s:textfield size="25" name="apprvrName"
						readonly="true" /> <img
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage"
						onclick="javascript:callsF9(500,325,'TravelMainSchdlrReport_f9ApprvrAction.action');">
					</td>
				</tr>

				<!-- SUB SCHEDULER -->


				<tr>
					<td colspan="1" width="20%"><label class="set"
						name="tms.subSchdlr" id="tms.subSchdlr"
						ondblclick="callShowDiv(this);"><%=label.get("tms.subSchdlr")%></label>
					:</td>
					<td colspan="6"><s:hidden name="subSchdlrToken" /> <s:hidden
						name="subSchdlrCode" /> <s:textfield size="25"
						name="subSchdlrName" readonly="true" /> <img
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage"
						onclick="javascript:callsF9(500,325,'TravelMainSchdlrReport_f9SubSchdlrAction.action');">
					</td>
				</tr>


				<!-- APPLICATION FOR -->
				<tr>
					<td width="20%" colspan="1"><label class="set"
						name="tms.appldFor" id="tms.appldFor"
						ondblclick="callShowDiv(this);"><%=label.get("tms.appldFor")%></label>:</td>
					<td colspan="6"><s:select name="applFor" id="appFr"
						headerKey="" headerValue="--Select--" cssStyle="width:100"
						list="#{'S':'Self','G':'Guest','O':'Others'}" /></td>
				</tr>

				<tr>
					<td colspan="1" width="20%"><s:hidden name="travelCheckVal"
						id="travelCheckVal" /> <s:checkbox theme="simple"
						name="travelCheck" id="travelCheck"
						onclick="enableMyDiv('travel');" /> <label class="set"
						name="tms.trvel" id="tms.trvel" ondblclick="callShowDiv(this);"><%=label.get("tms.trvel")%></label></td>
					<td width="80%" id="travelDiv" style="display: none" colspan="6">
					<table cellpadding="0" cellspacing="0" border="0"
						style="border-collapse: collapse;" width="100%">
						<tr>
							<td class="formtext" colspan="1" width="10%"><s:hidden
								name="hidTravelSelf" /> <input type="radio" value="TSR"
								name="trvlSelf" id="trvlSelfTSR"
								value='<s:property value="hidTravelSelf" />'
								onclick="callTravelSelf();"> <label class="set"
								name="tms.self" id="tms.self" ondblclick="callShowDiv(this);"><%=label.get("tms.self")%></label></td>
							<td class="formtext" colspan="5" width="70%"><s:hidden
								name="hidTravelComp" /> <input type="radio" value="TSR"
								name="trvlComp" id="trvlCompTSR"
								value='<s:property value="hidTravelComp" />'
								onclick="callTravelCompany();"> <label class="set"
								name="tms.company" id="tms.company"
								ondblclick="callShowDiv(this);"><%=label.get("tms.company")%></label></td>
						</tr>

					</table>

					</td>
				</tr>
				<script>
				
				function callTravelSelf()
				{
				document.getElementById('paraFrm_hidTravelSelf').value="Y";
				document.getElementById('paraFrm_hidTravelComp').value="";
				document.getElementById('trvlCompTSR').checked=false;
				}
				function callTravelCompany()
				{
				document.getElementById('paraFrm_hidTravelComp').value="Y";
				document.getElementById('paraFrm_hidTravelSelf').value="";
				document.getElementById('trvlSelfTSR').checked=false;
				}
				
				</script>
				<tr>
					<td colspan="1" width="20%"><s:hidden name="accomCheckVal"
						id="accomCheckVal" /> <s:checkbox theme="simple"
						name="accomCheck" id="accomCheck" onclick="enableMyDiv('accom');" /><label
						class="set" name="tms.accomodation" id="tms.accomodation"
						ondblclick="callShowDiv(this);"><%=label.get("tms.accomodation")%></label></td>

					<td width="80%" id="accomDiv" style="display: none" colspan="6">
					<table cellpadding="0" cellspacing="0" border="0"
						style="border-collapse: collapse;" width="100%">
						<tr>
							<td class="formtext" colspan="1" width="10%"><s:hidden
								name="hidAccomSelf" /> <input type="radio" value="ASR"
								name="accomSelf" id="accomSelfASR"
								value='<s:property value="hidAccomSelf" />'
								onclick="callAccomSelf();"> <label class="set"
								name="tms.self" id="tms.self2" ondblclick="callShowDiv(this);"><%=label.get("tms.self")%></label></td>
							<td class="formtext" colspan="5" width="70%"><s:hidden
								name="hidAccomComp" /> <input type="radio" value="ACR"
								name="accomComp" id="accomCompACR"
								value='<s:property value="hidAccomComp" />'
								onclick="callAccomCompany();"> <label class="set"
								name="tms.company" id="tms.company2"
								ondblclick="callShowDiv(this);"><%=label.get("tms.company")%></label></td>
						</tr>

					</table>

					</td>
				</tr>


				<script>
				
				function callAccomSelf()
				{
				document.getElementById('paraFrm_hidAccomSelf').value="Y";
				document.getElementById('paraFrm_hidAccomComp').value="";
				document.getElementById('accomCompACR').checked=false;
				}
				function callAccomCompany()
				{
				document.getElementById('paraFrm_hidAccomComp').value="Y";
				document.getElementById('paraFrm_hidAccomSelf').value="";
				document.getElementById('accomSelfASR').checked=false;
				}
				
				</script>



				<tr>
					<td colspan="1" width="20%"><s:hidden name="localCheckVal"
						id="localCheckVal" /> <s:checkbox theme="simple"
						name="localCheck" id="localCheck" onclick="enableMyDiv('local');" />
					<label class="set" name="tms.localConv" id="tms.localConv"
						ondblclick="callShowDiv(this);"><%=label.get("tms.localConv")%></label></td>
					<td width="80%" id="localDiv" style="display: none" colspan="6">
					<table cellpadding="0" cellspacing="0" border="0"
						style="border-collapse: collapse;" width="100%">
						<tr>
							<td class="formtext" colspan="1" width="10%"><s:hidden
								name="hidLocalSelf" /> <input type="radio" value="LSR"
								name="localSelf" id="localSelfLSR"
								value='<s:property value="hidLocalSelf" />'
								onclick="callLocalSelf();"> <label class="set"
								name="tms.self" id="tms.self3" ondblclick="callShowDiv(this);"><%=label.get("tms.self")%></label></td>
							<td class="formtext" colspan="5" width="70%"><s:hidden
								name="hidLocalComp" /> <input type="radio" value="LCR"
								name="localComp" id="localCompLCR"
								value='<s:property value="hidLocalComp" />'
								onclick="callLocalCompany();"> <label class="set"
								name="tms.company" id="tms.company3"
								ondblclick="callShowDiv(this);"><%=label.get("tms.company")%></label></td>
						</tr>

					</table>

					</td>
				</tr>

				<script>
				
				function callLocalSelf()
				{
				document.getElementById('paraFrm_hidLocalSelf').value="Y";
				document.getElementById('paraFrm_hidLocalComp').value="";
				document.getElementById('localCompLCR').checked=false;
				}
				function callLocalCompany()
				{
				document.getElementById('paraFrm_hidLocalComp').value="Y";
				document.getElementById('paraFrm_hidLocalSelf').value="";
				document.getElementById('localSelfLSR').checked=false;
				}				
				</script>
				
				
			</table>


<script> 
callMeOnLoadMethod();
		 function  callMeOnLoadMethod(type)
		 {	
		
		 var types= ["travel","accom","local"];
		 for(var i=0;i<3;i++)
		    {
		         if(document.getElementById(types[i]+'CheckVal').value=="true"   )
		         {
		            //enable the div
		             document.getElementById(types[i]+'Div').style.display=''; 
		             document.getElementById(types[i]+"CheckVal").value="Y"	
		             document.getElementById(types[i]+"Check").checked=true;			             
                                            //check the internal check values ie self or company hidLocalSelf
								             if(types[i]=='accom')		
								             {			          
								                if(document.getElementById('paraFrm_hidAccomSelf').value=="true")  {
								                             document.getElementById('accomSelfASR').checked=true;	
								                             document.getElementById('paraFrm_hidAccomSelf').value="Y";
								                 } else {
									                         document.getElementById('accomCompACR').checked=true;	
									                         document.getElementById('paraFrm_hidAccomComp').value="Y";
								                 }
								             }
								              if(types[i]=='travel')	 {
								                if(document.getElementById('paraFrm_hidTravelSelf').value=="true") {
								                             document.getElementById('trvlSelfTSR').checked=true;	
								                             document.getElementById('paraFrm_hidTravelSelf').value="Y";
								                 } else {
								                             document.getElementById('trvlCompTSR').checked=true;	
									                         document.getElementById('paraFrm_hidTravelComp').value="Y";
								                 }
								              }
								              if (types[i]=='local') {								                 
								                if(document.getElementById('paraFrm_hidLocalSelf').value=="true") {
								                             document.getElementById('localSelfLSR').checked=true;	
								                             document.getElementById('paraFrm_hidlocalSelf').value="Y";
								                 } else  {
									                         document.getElementById('localCompLCR').checked=true;	
									                         document.getElementById('paraFrm_hidlocalComp').value="Y";
								                 }
							                   }
		         }//end of if-for
		         else if(document.getElementById(types[i]+'CheckVal').value=="Y") {
		              document.getElementById(types[i]+'Div').style.display=''; 
		             //set the values for check box related fields		             
		                  if (types[i]=='travel') {
		                     if(document.getElementById('paraFrm_hidTravelSelf').value=="Y") {
		                     callTravelSelf();
		                     document.getElementById('trvlSelfTSR').checked=true;
		                    }else if(document.getElementById('paraFrm_hidTravelComp').value=="Y") {
		                     callTravelCompany();
		                     document.getElementById('trvlCompTSR').checked=true;
		                    }
		                 }		                 
		                  if (types[i]=='accom'){
		                     if(document.getElementById('paraFrm_hidAccomSelf').value=="Y")  {
		                     callAccomSelf();
		                     document.getElementById('accomSelfASR').checked=true;
		                    }
		                     else if(document.getElementById('paraFrm_hidAccomComp').value=="Y")  {
		                     callAccomCompany();
		                     document.getElementById('accomCompACR').checked=true; 
		                    }
		                 }
		                
		                 if (types[i]=='local'){
		                     if(document.getElementById('paraFrm_hidLocalSelf').value=="Y") {
		                     callLocalSelf();
		                     document.getElementById('localSelfLSR').checked=true;
		                    }else
		                     if(document.getElementById('paraFrm_hidLocalComp').value=="Y") {
		                     callLocalCompany();
		                     document.getElementById('localCompLCR').checked=true;
		                 }
		                 }
		         }
		       
		    }//end of for
	     }
			 
		</script>






			</div>
			<!-- DIV FIRST ENDS - FILTERS --> <!-- DIV SECOND BEGINS - COLUMN DEFINITIONS -->
			<div id="second">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<tr>
							<td colspan="6" class="text_head"><strong
								class="forminnerhead"> <label class="set"
								name="report.generation" id="report.generation"
								ondblclick="callShowDiv(this);"><%=label.get("report.generation")%></label>
							</strong></td>
						</tr>


						<!-- EMPLOYEE ID,TRAVEL TYPE,BRANCH-->
						<tr>
							<td width="30%" align="right" colspan="1"><label class="set"
								name="tms.empid" id="tms.empid"
								ondblclick="callShowDiv(this);"><%=label.get("tms.empid")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="empIdFlag" onclick="AddItem('tms.empid',this);" /></td>
							<td width="30%" align="right" colspan="1"><label class="set"
								name="tms.travelType" id="tms.travelType"
								ondblclick="callShowDiv(this);"><%=label.get("tms.travelType")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="trvlTypeFlag" onclick="AddItem('tms.travelType',this);" /></td>
							<td width="30%" align="right" colspan="1"><label class="set"
								name="branch" id="branch12" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple" name="branchFlag"
								onclick="AddItem('branch',this);" /></td>
						</tr>
						<!-- TRAVEL START DATE,TRAVEL SUB SCHEDULER NAME,GRADE-->
						<tr>
							<td width="30%" align="right" colspan="1"><label class="set"
								name="tms.trvlStrtDate" id="tms.trvlStrtDate"
								ondblclick="callShowDiv(this);"><%=label.get("tms.trvlStrtDate")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="travelStrtDateFlag"
								onclick="AddItem('tms.trvlStrtDate',this);" /></td>
							<td width="30%" align="right" colspan="1"><label class="set"
								name="tms.subSchdlr" id="tms.subSchdlr12"
								ondblclick="callShowDiv(this);"><%=label.get("tms.subSchdlr")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="subSchdlrFlag" onclick="AddItem('tms.subSchdlr',this);" /></td>
							<td width="30%" align="right" colspan="1"><label class="set"
								name="grade" id="grade12" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple" name="gradeFlag"
								onclick="AddItem('grade',this);" /></td>
						</tr>


						<!-- TRAVEL END DATE,APPLICANT NAME,PURPOSE-->
						<tr>
							<td width="30%" align="right" colspan="1"><label class="set"
								name="tms.trvlEndDate" id="tms.trvlEndDate"
								ondblclick="callShowDiv(this);"><%=label.get("tms.trvlEndDate")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="travelEndDateFlag"
								onclick="AddItem('tms.trvlEndDate',this);" /></td>
							<td width="30%" align="right" colspan="1"><label class="set"
								name="tms.applcntName" id="tms.applcntName"
								ondblclick="callShowDiv(this);"><%=label.get("tms.applcntName")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="applcntNameFlag"
								onclick="AddItem('tms.applcntName',this);" /></td>
							<td width="30%" align="right" colspan="1"><label class="set"
								name="tms.trvlPurpose" id="tms.trvlPurpose"
								ondblclick="callShowDiv(this);"><%=label.get("tms.trvlPurpose")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="trvlPurposeFlag"
								onclick="AddItem('tms.trvlPurpose',this);" /></td>
						</tr>


						<!-- TRAVEL ASSIGNED DATE,APPROVER NAME,STATUS-->
						<tr>
							<td width="30%" align="right" colspan="1"><label class="set"
								name="tms.trvlAssigntDate" id="tms.trvlAssigntDate"
								ondblclick="callShowDiv(this);"><%=label.get("tms.trvlAssigntDate")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="travelAssignDateFlag"
								onclick="AddItem('tms.trvlAssigntDate',this);" /></td>

							<td width="30%" align="right" colspan="1"><label class="set"
								name="tms.approver" id="tms.approver12"
								ondblclick="callShowDiv(this);"><%=label.get("tms.approver")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple" name="apprvrFlag"
								onclick="AddItem('tms.approver',this);" /></td>
							<td width="30%" align="right" colspan="1"><label class="set"
								name="tms.trvlStatus" id="tms.trvlStatus"
								ondblclick="callShowDiv(this);"><%=label.get("tms.trvlStatus")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="trvlStatusFlag" onclick="AddItem('tms.trvlStatus',this);" /></td>
						</tr>



						<!-- TRAVEL BOOKING DATE,INITIATOR NAME  -->
						<tr>
							<td width="30%" align="right" colspan="1"><label class="set"
								name="tms.trvlBookingDate" id="tms.trvlBookingDate"
								ondblclick="callShowDiv(this);"><%=label.get("tms.trvlBookingDate")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="trvlBkngDateFlag"
								onclick="AddItem('tms.trvlBookingDate',this);" /></td>
							<td width="30%" align="right" colspan="1"><label class="set"
								name="tms.trvlInitrName" id="tms.trvlInitrName"
								ondblclick="callShowDiv(this);"><%=label.get("tms.trvlInitrName")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="trvlInitrNameFlag"
								onclick="AddItem('tms.trvlInitrName',this);" /></td>

						</tr>
					</table>
					</td>
				</tr>
			</table>
			</div>
			<!-- DIV SECOND ENDS - COLUMN DEFINITIONS --> <!-- DIV THIRD BEGINS - SORTING OPTIONS -->
			<div id="third">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="505">
					<table width="100%" border="0" cellpadding="0" cellspacing="3"
						class="formbg">
						<tr>
							<td class="text_head" colspan="5"><strong
								class="forminnerhead">Select appropriate sorting option
							for report generation</strong></td>
						</tr>
						<!-- SORT BY STARTS -->
						<tr>
							<td colspan="1" width="25%"><label class="set"
								id="sortByLabel" name="sortByLabel"
								ondblclick="callShowDiv(this);"><%=label.get("sortByLabel")%></label>
							:</td>
							<td colspan="4"><select name="sortBy" id="sortBy"
								style="width: 200" onchange="callSortBy();">
								<!--  <option value="1">--Select--</option>-->
								<option value="Travel Id">Travel Id</option>
							</select> <s:hidden name="hiddenSortBy" /></td>
						</tr>
						<tr height="35" valign="top">
							<td colspan="1" width="25%"><s:hidden name="sortByAsc" /> <s:hidden
								name="sortByDsc" /></td>
							<td colspan="1" width="5%"><input type="radio" value="A"
								onclick="callOrder('A');" name="sortByOrder"
								<s:property value="sortByAsc"/>></td>

							<td colspan="1" width="14%">Ascending</td>

							<td colspan="1" width="4%"><input type="radio" value="D"
								onclick="callOrder('D');" name="sortByOrder"
								<s:property value="sortByDsc"/>></td>

							<td colspan="1" width="57%">Descending</td>
						</tr>
						<!-- SORT BY ENDS -->
						<tr>
							<td colspan="1" width="25%"><label class="set"
								id="thenByLabel" name="thenByLabel"
								ondblclick="callShowDiv(this);"><%=label.get("thenByLabel")%></label>
							:</td>
							<td colspan="4"><select name="thenBy1" id="thenBy1"
								style="width: 200" onchange="callThenBy1();">
								<!--<option value="1">--Select--</option>-->
								<option value="Travel Id">Travel Id</option>
							</select> <s:hidden name="hiddenThenBy1" /></td>
						</tr>
						<tr height="35" valign="top">
							<td colspan="1" width="25%"><s:hidden name="thenByOrder1Asc" />
							<s:hidden name="thenByOrder1Dsc" /></td>
							<td colspan="1" width="5%"><input type="radio" value="A"
								onclick="callOrder1('A');" name="thenByOrder1"
								<s:property value="thenByOrder1Asc"/>></td>

							<td colspan="1" width="14%">Ascending</td>

							<td colspan="1" width="4%"><input type="radio" value="D"
								onclick="callOrder1('D');" name="thenByOrder1"
								<s:property value="thenByOrder1Dsc"/>></td>

							<td colspan="1" width="57%">Descending</td>
						</tr>

						<tr>
							<td colspan="1" width="25%"><label class="set"
								id="thenByLabel" name="thenByLabel"
								ondblclick="callShowDiv(this);"><%=label.get("thenByLabel")%></label>
							:</td>
							<td colspan="4"><select name="thenBy2" id="thenBy2"
								style="width: 200" onchange="callThenBy2();">
								<!--<option value="1">--Select--</option>-->
								<option value="Travel Id">Travel Id</option>
							</select> <s:hidden name="hiddenThenBy2" /></td>
						</tr>
						<tr height="35" valign="top">
							<td colspan="1" width="25%"><s:hidden name="thenByOrder2Asc" />
							<s:hidden name="thenByOrder2Dsc" /></td>
							<td colspan="1" width="5%"><input type="radio" value="A"
								onclick="callOrder2('A');" name="thenByOrder2"
								<s:property value="thenByOrder2Asc"/>></td>

							<td colspan="1" width="14%">Ascending</td>

							<td colspan="1" width="4%"><input type="radio" value="D"
								onclick="callOrder2('D');" name="thenByOrder2"
								<s:property value="thenByOrder2Dsc"/>></td>

							<td colspan="1" width="57%">Descending</td>
						</tr>
					</table>
					</td>

					<td width="397" valign="top">
					<table width="366" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td class="text_head" colspan="5"><strong
								class="forminnerhead">Select appropriate order of
							columns for report generation</strong></td>
						</tr>

						<tr>
							<td width="123"><select id="columnOrdering" size="10"
								name="columnOrdering" multiple="true">

							</select> <s:hidden name="hiddenColumns" /></td>
							<td width="242"><input type="button" class="shuffleUp"
								onclick="listbox_move('columnOrdering', 'up');" /> <br />
							<br />
							<input type="button" class="shuffleDown"
								onclick="listbox_move('columnOrdering', 'down');" /></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</div>
			<!-- DIV THIRD ENDS - SORTING OPTIONS --> <!-- DIV FOURTH BEGINS - ADVANCE FILTERS -->

			<div id="fourth">
			<table cellspacing="0" cellpadding="0" class="formbg" width="100%"
				style="border-collapse: collapse;" border="0">
				<tr>
					<td colspan="7"><strong> <label
						id="selectAdvanceFilter" ondblclick="callShowDiv(this);"
						name="selectAdvanceFilter">Select appropriate advance
					filter option for report generation</label> </strong></td>
				</tr>


				<tr>

					<td colspan="1" height="22" width="20%"><label class="set"
						name="tms.trvlAssigntDate" id="tms.trvlAssigntDate12"
						ondblclick="callShowDiv(this);"><%=label.get("tms.trvlAssigntDate")%></label>:</td>
					<td colspan="1" align="left" width="10%" align="left"><s:select
						theme="simple" name="assignDateSelect" value="%{assignDateSelect}"
						list="#{'':'--Select--','ON':'On','BF':'Before','AF':'After','OB':'On before',
						'OA':'On after','BN':'Between'}"
						onchange="callToDateDispOnClick(this,'1','TAD');" /></td>

					<td colspan="1" width="10%" height="22"><s:textfield
						name="TADfromDate" maxlength="10"
						onkeypress="return numbersWithHiphen();"
						onfocus="clearText('paraFrm_TADfromDate','dd-mm-yyyy')"
						onblur="setText('paraFrm_TADfromDate','dd-mm-yyyy')"
						maxlength="10" size="9" /></td>
					<td><s:a
						href="javascript:NewCal('paraFrm_TADfromDate','DDMMYYYY');">
						<img class="iconImage" src="../pages/images/recruitment/Date.gif"
							width="16" height="16" border="0" align="absmiddle" />
					</s:a></td>
					<td colspan="3" height="22">
					<table cellspacing="0" cellpadding="0" border="0"
						style="border-collapse: collapse;">
						<tr>
							<td id="toDateDiv1" width="15%" align="center"><strong>&nbsp;AND
							</strong></td>
							<td width="10%" colspan="1" id="toDateDivLabel1" height="22">&nbsp;<s:textfield
								name="TADToDate" maxlength="10"
								onkeypress="return numbersWithHiphen();"
								onfocus="clearText('paraFrm_TADToDate','dd-mm-yyyy')"
								onblur="setText('paraFrm_TADToDate','dd-mm-yyyy')"
								maxlength="10" size="9" /></td>
							<td id="toDatePicker1"><s:a
								href="javascript:NewCal('paraFrm_TADToDate','DDMMYYYY');">
								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" />
							</s:a></td>
						</tr>
					</table>
					</td>
				</tr>


				<td colspan="1" height="22"><label class="set"
					name="tms.trvlStrtDate" id="tms.trvlStrtDate12"
					ondblclick="callShowDiv(this);"><%=label.get("tms.trvlStrtDate")%></label>:</td>
				<td colspan="1" width="80" align="left"><s:select
					theme="simple" name="trvlStrtDateSelect"
					value="%{trvlStrtDateSelect}"
					list="#{'':'--Select--','ON':'On','BF':'Before','AF':'After','OB':'On before',
						'OA':'On after','BN':'Between'}"
					onchange="callToDateDispOnClick(this,'2','TSD');" /></td>

				<td width="47" colspan="1" height="22"><s:textfield
					name="TSDfromDate" maxlength="10"
					onkeypress="return numbersWithHiphen();"
					onfocus="clearText('paraFrm_TSDfromDate','dd-mm-yyyy')"
					onblur="setText('paraFrm_TSDfromDate','dd-mm-yyyy')" maxlength="10"
					size="9" /></td>
				<td width="90" colspan="1" height="22"><s:a
					href="javascript:NewCal('paraFrm_TSDfromDate','DDMMYYYY');">
					<img class="iconImage" src="../pages/images/recruitment/Date.gif"
						width="16" height="16" border="0" align="absmiddle" />
				</s:a></td>
				<td colspan="3" height="22">
				<table cellspacing="0" cellpadding="0" border="0"
					style="border-collapse: collapse;">
					<tr>
						<td width="69" id="toDateDiv2" align="center"><strong>AND
						</strong></td>
						<td width="52" id="toDateDivLabel2" height="22"><s:textfield
							name="TSDToDate" maxlength="10"
							onkeypress="return numbersWithHiphen();"
							onfocus="clearText('paraFrm_TSDToDate','dd-mm-yyyy')"
							onblur="setText('paraFrm_TSDToDate','dd-mm-yyyy')" maxlength="10"
							size="9" /></td>
						<td width="449" id="toDatePicker2"><s:a
							href="javascript:NewCal('paraFrm_TSDToDate','DDMMYYYY');">
							<img class="iconImage" src="../pages/images/recruitment/Date.gif"
								width="16" height="16" border="0" align="absmiddle" />
						</s:a></td>
					</tr>
				</table>
				</td>
				</tr>


				<tr>


					<td colspan="1" height="22"><label class="set"
						name="tms.trvlBookingDate" id="tms.trvlBookingDate12"
						ondblclick="callShowDiv(this);"><%=label.get("tms.trvlBookingDate")%></label>:</td>
					<td colspan="1" width="80" align="left"><s:select
						theme="simple" name="trvlBookingDateSelect"
						value="%{trvlBookingDateSelect}"
						list="#{'':'--Select--','ON':'On','BF':'Before','AF':'After','OB':'On before',
						'OA':'On after','BN':'Between'}"
						onchange="callToDateDispOnClick(this,'3','TBD');" /></td>

					<td width="47" colspan="1" height="22"><s:textfield
						name="TBDfromDate" maxlength="10"
						onkeypress="return numbersWithHiphen();"
						onfocus="clearText('paraFrm_TBDfromDate','dd-mm-yyyy')"
						onblur="setText('paraFrm_TBDfromDate','dd-mm-yyyy')"
						maxlength="10" size="9" /></td>
					<td width="90" colspan="1" height="22"><s:a
						href="javascript:NewCal('paraFrm_TBDfromDate','DDMMYYYY');">
						<img class="iconImage" src="../pages/images/recruitment/Date.gif"
							width="16" height="16" border="0" align="absmiddle" />
					</s:a></td>
					<td colspan="3" height="22">
					<table cellspacing="0" cellpadding="0" border="0"
						style="border-collapse: collapse;">
						<tr>
							<td width="69" id="toDateDiv3" align="center"><strong>AND
							</strong></td>
							<td width="52" id="toDateDivLabel3" height="22"><s:textfield
								name="TBDToDate" maxlength="10"
								onkeypress="return numbersWithHiphen();"
								onfocus="clearText('paraFrm_TBDToDate','dd-mm-yyyy')"
								onblur="setText('paraFrm_TBDToDate','dd-mm-yyyy')"
								maxlength="10" size="9" /></td>
							<td width="449" id="toDatePicker3"><s:a
								href="javascript:NewCal('paraFrm_TBDToDate','DDMMYYYY');">
								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" />
							</s:a></td>
						</tr>
					</table>
					</td>
				</tr>





				<tr>

					<td colspan="1" width="20%"><label class="set"
						name="tms.schCycleWise" id="tms.schCycleWise123"
						ondblclick="callShowDiv(this);"><%=label.get("tms.schCycleWise")%></label>:</td>
					<td colspan="1" align="left" width="10%"><s:select
						theme="simple" name="schCycleSelect" value="%{schCycleSelect}"
						cssStyle="width: 80px"
						list="#{'':'--Select--','IE':'=','LT':'<','GT':'>','LE':'<=',
						'GE':'>=','BN':'Between'}"
						onchange="callBetween(this,'5');" /></td>

					<td>&nbsp;<s:textfield name="schCycleFrom" size="9"
						onkeypress="return numbersOnly();" maxlength="2" /></td>
					<td colspan="4" id="betweenOn5" width="70%" height="22">
					<table cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td colspan="1" id="toDateDiv421" width="15%" align="center"><strong>
							AND </strong></td>
							<td colspan="1" height="22" width="10%" id="toDivAndText"><s:textfield
								name="schCycleTo" size="9" onkeypress="return numbersOnly();"
								maxlength="2" /></td>
							<td colspan="2">&nbsp;</td>
						</tr>
					</table>
					</td>
				</tr>



				<tr>

					<td width="20%" colspan="1"><label class="set"
						name="tms.travelType" id="tms.travelType1"
						ondblclick="callShowDiv(this);"><%=label.get("tms.travelType")%></label>:</td>
					<td colspan="6"><s:select name='trvlType' list="trvlTypeMap"
						theme="simple" headerValue="--Select--" headerKey="" id='trvlType' />
					</td>
				</tr>

				<tr>
					<td colspan="1" width="20%"><label class="set" name="grade"
						id="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>
					:</td>
					<td colspan="6"><s:hidden name="gradeId" /> <s:textfield
						size="25" name="gradeName" readonly="true" /> <img
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage"
						onclick="javascript:callsF9(500,325,'TravelMainSchdlrReport_f9cadretaction.action');">
					</td>
				</tr>


				<tr>
					<td colspan="1" width="20%"><label class="set" name="branch"
						id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
					:</td>
					<td colspan="6"><s:hidden name="brnchCode" /> <s:textfield
						size="25" name="branchName" readonly="true" /> <img
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage"
						onclick="javascript:callsF9(500,325,'TravelMainSchdlrReport_f9centeraction.action');">
					</td>
				</tr>


				<tr>
					<td colspan="1" width="20%"><label class="set"
						name="department" id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
					:</td>
					<td colspan="6"><s:hidden name="deptCode" /> <s:textfield
						size="25" name="deptName" readonly="true" /> <img
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage"
						onclick="javascript:callsF9(500,325,'TravelMainSchdlrReport_f9deptaction.action');">
					</td>
				</tr>

			</table>
			</div>
			<!-- DIV FOURTH ENDS - ADVANCE FILTERS --> <!-- DIV FIFTH BEGINS - COMMON FIELDS FOR ALL DIVs -->
			<div id="fifth">
			<table cellpadding="0" cellspacing="0" border="0" class="formbg"
				width="100%">
				<tr>
					<td colspan="4" width="100%">Report Layout</td>
				</tr>
			</table>
			</div>
			<!-- DIV FIFTH ENDS - COMMON FIELDS FOR ALL DIVs --></td>
		</tr>

		<!-- SHOW DISPLAY OPTIONS -->
		<tr>
			<td>
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
					View On Screen</td>
				</tr>
				<tr>
					<td width="17%" colspan="1" class="formtext"><input
						type="radio" value="R" name="reportView" id="reportViewR"
						<s:property value="hidReportRadio"/> onclick="callReportChk('Y');">
					Report Type</td>
					<td width="83%" colspan="1" class="formtext">
					<div id="reportTypeDiv"><s:select headerKey="1"
						headerValue="--Select--" name="reportType"
						list="#{'P':'Pdf','X':'Xls' ,'T':'Doc'}" /></div>
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" colspan="1" class="formtext"><label
						class="set" id="report.criteria" name="report.criteria"
						ondblclick="callShowDiv(this);"><%=label.get("report.criteria")%></label>
					:</td>
					<td width="83%" colspan="1"><s:textfield name="settingName"
						size="25" theme="simple" maxlength="40" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- DISPLAY OPTIONS ENDS -->

		<!-- BUTTON PANEL BEGINS -->
		<tr>
			<s:hidden name="reqStatus" />
			<td width="100%"><input type="button" class="token"
				theme="simple" value="  Generate Report"
				onclick=" return generateReport();" /> <input type="button"
				class="reset" theme="simple" value=" Reset" onclick="resetForm()" />
			<input name="button" type="button" value="Save report criteria"
				class="save" onclick="saveReport()" />
				<input name="button"
						type="button" value="Search" class="search" onclick="callSearch()" />
				</td>

		</tr>
		<!-- BUTTON PANEL ENDS -->

	</table>
</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script>
	var misCounter=0;
	onloadCall();
	function onloadCall(){
		document.getElementById('first').style.display='block';
		document.getElementById('second').style.display='none';
		document.getElementById('third').style.display='none';
		document.getElementById('fourth').style.display='none';
		document.getElementById('menuid1').className='on';
		document.getElementById('menuid2').className='li';
		document.getElementById('menuid3').className='li';
		document.getElementById('menuid4').className='li';
		document.getElementById('reportTypeDiv').style.display='none'; 
		callAllDisable();	
		setFieldsOnload();
		
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
 
	function showCurrent(menuId, id){
		document.getElementById('first').style.display='none';
		document.getElementById('second').style.display='none';
		document.getElementById('third').style.display='none';
		document.getElementById('fourth').style.display='none';
		document.getElementById('menuid1').className='li';
		document.getElementById('menuid2').className='li';
		document.getElementById('menuid3').className='li';
		document.getElementById('menuid4').className='li';
		document.getElementById(menuId).className='on';
		document.getElementById(id).style.display='block';
	}

	function callView(){	
		window.open("FormulaCalculator.html","show","height=400,width=400,status=yes,toolbar=no,menubar=no,location=no");
	}

	function callView1(){	
		window.open("LeaveName.html","show","height=400,width=400,status=yes,toolbar=no,menubar=no,location=no");
	}

	function callToDateDispOnClick(obj,idNo,idName)
 	{
 		try{
   			var dateFilter= obj.value;
   			document.getElementById('paraFrm_'+idName+'ToDate').value="";  
   			if(dateFilter=="BN"){
     			document.getElementById('toDateDiv'+idNo).style.display=''; 
     			document.getElementById('toDateDivLabel'+idNo).style.display='';  
	  			document.getElementById('toDatePicker'+idNo).style.display=''; 
      			setText('paraFrm_'+idName+'ToDate',"dd-mm-yyyy");
   			}else {
      			setText('paraFrm_'+idName+'ToDate',"dd-mm-yyyy");
     			document.getElementById('toDateDiv'+idNo).style.display='none'; 
     			document.getElementById('toDateDivLabel'+idNo).style.display='none';     
  				document.getElementById('toDatePicker'+idNo).style.display='none';  
   			} 
   		}catch(e){
   			alert(e);
   		}
 	}
 	
 	
 	
 	
 	
 	
 	function call2DateDisp4FromDate(obj,idNo,idName)
 	{
   		try{
   			var dateFilter= obj.value;
   			if(dateFilter=="BN"){
     			document.getElementById('applDTToDateDiv').style.display=''; 
     			document.getElementById('applDTToDateDivLabel').style.display='';  
	  			document.getElementById('applDTToDatePicker').style.display='';
 
      			setText('paraFrm_applDateTo',"dd-mm-yyyy");
   			}else {
      			setText('paraFrm_applDateTo',"dd-mm-yyyy");
     			document.getElementById('applDTToDateDiv').style.display='none'; 
     			document.getElementById('applDTToDateDivLabel').style.display='none';  
	  			document.getElementById('applDTToDatePicker').style.display='none';
   			} 
   		}catch(e){
   			alert(e);
   		}
 	}
 	 
	function callBetween(obj,idNo)
	{
		var dateFilter= obj.value;		
   		if(dateFilter=="BN"){
			document.getElementById('betweenOn'+idNo).style.display=''; 
		     //document.getElementById('toDivAnd').style.display=''; 
		     //document.getElementById('toDivAndText').style.display='';  
			 // document.getElementById('toDivAndYrs').style.display='';
	 
  		}else {
  			document.getElementById('betweenOn'+idNo).style.display='none'; 
    		// document.getElementById('toDateDiv').style.display='none'; 
     		//document.getElementById('toDivAndText').style.display='none';     
			// document.getElementById('toDivAndYrs').style.display='none';  
   		} 
 	}
 	
 	
 	
 
 	function callAllDisable(){
 	
 	 var fields= ["","assignDateSelect","trvlStrtDateSelect","trvlBookingDateSelect"]; 	 
  		
  		for(var idNo=1;idNo <=3;idNo++){ 
  		
  				if(document.getElementById("paraFrm_"+fields[idNo]).value=="BN")
		  		{
		  		document.getElementById('toDateDiv'+idNo).style.display=''; 
     		    document.getElementById('toDateDivLabel'+idNo).style.display='';     
	  		    document.getElementById('toDatePicker'+idNo).style.display='';  
		  		}
		  		else
		  		{
		  		document.getElementById('toDateDiv'+idNo).style.display='none'; 
     		    document.getElementById('toDateDivLabel'+idNo).style.display='none';     
	  		    document.getElementById('toDatePicker'+idNo).style.display='none';
		  		}
		  		
	  	}
	  		//application date
		  		
		  		if(document.getElementById("paraFrm_applDateSelect").value=="BN"){		  		
		  		document.getElementById('applDTToDateDiv').style.display=''; 	  	  		
     			document.getElementById('applDTToDateDivLabel').style.display='';      			 
	  			document.getElementById('applDTToDatePicker').style.display='';				  	
			  	}
			  	else
			  	{
			  	document.getElementById('applDTToDateDiv').style.display='none'; 	  	  		
     			document.getElementById('applDTToDateDivLabel').style.display='none';      			 
	  			document.getElementById('applDTToDatePicker').style.display='none';	
			  	}
  				
	  	        if(document.getElementById("paraFrm_schCycleSelect").value=="BN"){
				  document.getElementById('betweenOn5').style.display='';
				}
				else
				{
				  document.getElementById('betweenOn5').style.display='none';
				}	  	  	
 	}
 
 
	function listbox_move(listID, direction) {
		try{
			var listbox = document.getElementById(listID);
			var selIndex = listbox.selectedIndex;

			if(-1 == selIndex) {
				alert("Please select an option to move.");
				return;
			}
			
			//alert(selIndex);

			var increment = -1;
			if(direction == 'up')
				increment = -1;
			else
				increment = 1;

			/*if((selIndex + increment) < 0 ||
				(selIndex + increment) > (listbox.options.length-1)) {
				alert('in if');
				return;
			}*/
			if((selIndex + increment) < 0){
				alert("You cannot move up the record");
				return;
			}
			if((selIndex + increment) > (listbox.options.length-1)){
				alert("You cannot move down the record");
				return;
			}

			var selValue = listbox.options[selIndex].value;
			var selText = listbox.options[selIndex].text;
			listbox.options[selIndex].value = listbox.options[selIndex + increment].value
			listbox.options[selIndex].text = listbox.options[selIndex + increment].text

			listbox.options[selIndex + increment].value = selValue;
			listbox.options[selIndex + increment].text = selText;

			listbox.selectedIndex = selIndex + increment;
			
        	var finalValue="";
        	var count=1;
        	for(var i=0;i<listbox.options.length;i++){
        		var opt1 = listbox.options[i];
        		//alert('Values in listbox : '+opt1.text);
        		finalValue+=count+"-"+opt1.text+",";
        		count++;
        	}
			document.getElementById('paraFrm_hiddenColumns').value=finalValue;
			//alert("finalValue------"+finalValue);
			}catch(e){
				alert(e);
			}
		}


	function generateReport()
	{
		try{
			if(!document.getElementById("reportViewV").checked && !document.getElementById("reportViewR").checked){
				alert("Please select any display option");
				return false;
			}
			
			if(document.getElementById("reportViewR").checked){
				if(document.getElementById('paraFrm_reportType').value=="1"){
					alert("Please select Report Type");
					return false;
				}
			}
			
			
			//validations start
			
			//application Date
			var applDateF=document.getElementById('paraFrm_applDateFrm').value;
			var applDateT=document.getElementById('paraFrm_applDateTo').value;
			if(document.getElementById("paraFrm_applDateSelect").value!=""){
				if(applDateF=="" || applDateF=="dd-mm-yyyy"){
					alert("Please enter "+document.getElementById('tms.applDate').innerHTML);
					return false;
				}
				if(document.getElementById("paraFrm_applDateSelect").value=="BN"){
					if(applDateF==""||applDateT=="dd-mm-yyyy"){
						alert("Please enter date");
						return false;
					}
				}
				if(applDateF!="" ||applDateF!="dd-mm-yyyy"){
			  		var fld=['paraFrm_applDateFrm'];
			  		var lbl=['tms.applDate'];
		   			var chkjfrmDate=validateDate(fld,lbl);
		   			if(!chkjfrmDate) {
			    		return false;
			   		}
	  			}
	  			if(applDateT!="dd-mm-yyyy" ){
			  		var fld=['paraFrm_applDateTo'];
		 		var lbl=['tms.applDate'];
		   			var chkjtoDate=validateDate(fld,lbl);
		   			if(!chkjtoDate) {
			    		return false;
			   		}
	  			}
			}
			
			
			
			//To Travel Assign Date
			var tadfrmDate=document.getElementById('paraFrm_TADfromDate').value;
			var tadtoDate=document.getElementById('paraFrm_TADToDate').value;		
			if(document.getElementById("paraFrm_assignDateSelect").value!=""){
				if(tadfrmDate=="" || tadfrmDate=="dd-mm-yyyy"){
					alert("Please enter "+document.getElementById('tms.trvlAssigntDate12').innerHTML);
					return false;
				}
				if(document.getElementById("paraFrm_assignDateSelect").value=="BN"){
					if(tadtoDate==""||tadtoDate=="dd-mm-yyyy"){
						alert("Please enter "+document.getElementById('tms.trvlAssigntDate12').innerHTML);
						return false;
					}
				}
				if(tadfrmDate!="" ||tadfrmDate!="dd-mm-yyyy"){
			  		var fld=['paraFrm_TADfromDate'];
			  		var lbl=['tms.trvlAssigntDate12'];
		   			var chkjfrmDate=validateDate(fld,lbl);
		   			if(!chkjfrmDate) {
			    		return false;
			   		}
	  			}
	  			if(tadtoDate!="dd-mm-yyyy" ){
			  		var fld=['paraFrm_TADToDate'];
			  			var lbl=['tms.trvlAssigntDate12'];
		   			var chkjtoDate=validateDate(fld,lbl);
		   			if(!chkjtoDate) {
			    		return false;
			   		}
	  			}
			}
			
			
			
		//Travel Start Date
		
			var tsdfrmDate=document.getElementById('paraFrm_TSDfromDate').value;
			var tsdtoDate=document.getElementById('paraFrm_TSDToDate').value;
			if(document.getElementById("paraFrm_trvlStrtDateSelect").value!=""){
				if(tsdfrmDate=="" || tsdfrmDate=="dd-mm-yyyy"){
					alert("Please enter "+document.getElementById('tms.trvlStrtDate12').innerHTML);
					return false;
				}
				if(document.getElementById("paraFrm_trvlStrtDateSelect").value=="BN"){
					if(tsdtoDate==""||tsdtoDate=="dd-mm-yyyy"){
						alert("Please enter "+document.getElementById('tms.trvlStrtDate12').innerHTML);
						return false;
					}
				}
				if(tsdfrmDate!="" ||tsdfrmDate!="dd-mm-yyyy"){
			  		var fld=['paraFrm_TSDfromDate'];
			  		var lbl=['tms.trvlStrtDate12'];
		   			var chkjfrmDate=validateDate(fld,lbl);
		   			if(!chkjfrmDate) {
			    		return false;
			   		}
	  			}
	  			if(tsdtoDate!="dd-mm-yyyy" ){
			  		var fld=['paraFrm_TSDToDate'];
			  		var lbl=['tms.trvlStrtDate12'];
		   			var chkjtoDate=validateDate(fld,lbl);
		   			if(!chkjtoDate) {
			    		return false;
			   		}
	  			}
			}
			
				//Travel booking date
		
		
			var tbdfrmDate=document.getElementById('paraFrm_TBDfromDate').value;
			var tbdtoDate=document.getElementById('paraFrm_TBDToDate').value;
			if(document.getElementById("paraFrm_trvlBookingDateSelect").value!=""){
				if(tbdfrmDate=="" || tbdfrmDate=="dd-mm-yyyy"){
					alert("Please enter "+document.getElementById('tms.trvlBookingDate12').innerHTML);
					return false;
				}
				if(document.getElementById("paraFrm_trvlBookingDateSelect").value=="BN"){
					if(tbdtoDate==""||tbdtoDate=="dd-mm-yyyy"){
						alert("Please enter "+document.getElementById('paraFrm_trvlBookingDateSelect').innerHTML);
						return false;
					}
				}
				if(tbdfrmDate!="" ||tbdfrmDate!="dd-mm-yyyy"){
			  		var fld=['paraFrm_TBDfromDate'];
			  		var lbl=['tms.trvlBookingDate12'];
		   			var chkjfrmDate=validateDate(fld,lbl);
		   			if(!chkjfrmDate) {
			    		return false;
			   		}
	  			}
	  			if(tbdtoDate!="dd-mm-yyyy" ){
			  		var fld=['paraFrm_TBDToDate'];
			  		var lbl=['tms.trvlBookingDate12'];
		   			var chkjtoDate=validateDate(fld,lbl);
		   			if(!chkjtoDate) {
			    		return false;
			   		}
	  			}
			}
			
		
		
			//Schedule Cycle Wise:
              if(document.getElementById("paraFrm_schCycleSelect").value!=""){
				if(document.getElementById("paraFrm_schCycleFrom").value==""){
					alert("Please enter Schedule Cycle Wise");
					return false;
				}
				if(document.getElementById("paraFrm_schCycleSelect").value=="BN"){
					if(document.getElementById("paraFrm_schCycleTo").value==""){
						alert("Please enter Schedule Cycle Wise");
						document.getElementById('paraFrm_schCycleTo').focus();
						return false;
					}
				}
			}
			
			
			
			//validations end
			
			
			
			
			

  			// for sort the filter   
	   	  	var sortBy = document.getElementById('sortBy').value;
	   	  	var thenBy1 = document.getElementById('thenBy1').value;
	   	  	var thenBy2 = document.getElementById('thenBy2').value; 
	   	  

   	  // == end of sort======
  			
  			
  		}catch(e){
  			alert(e);
  		}
  		
  		if(document.getElementById("reportViewV").checked){
  			document.getElementById('paraFrm').target='_self';
	 		document.getElementById('paraFrm').action="TravelMainSchdlrReport_viewOnScreen.action";
     		document.getElementById('paraFrm').submit();
		}else{
		  	document.getElementById('paraFrm').target='_blank';
			document.getElementById('paraFrm').action="TravelMainSchdlrReport_report.action";
			document.getElementById('paraFrm').submit();
			document.getElementById('paraFrm').target="main"; 
		}
	}
	
	function resetForm()
	{
		document.getElementById('paraFrm').target='main';
		document.getElementById('paraFrm').action="TravelMainSchdlrReport_reset.action";
		document.getElementById('paraFrm').submit();
	
	}
	
	function saveReport(){
		var settingName = trim(document.getElementById('paraFrm_settingName').value);
		if(settingName == ""){
			alert("Please enter "+document.getElementById('report.criteria').innerHTML);
			document.getElementById('paraFrm_settingName').focus();
			return false;
		}
		document.getElementById('paraFrm').target='main';
		document.getElementById('paraFrm').action="TravelMainSchdlrReport_saveReport.action";
		document.getElementById('paraFrm').submit();
	
	}


	function AddItem(labelName,id)
    {
        try{
        	var labelNameCheck = document.getElementById(labelName).innerHTML;
	        var checkedValue = id.checked;
	        // Create an Option object                
	        var opt = document.createElement("option");
	        
	        var sortBy = document.getElementById("sortBy");
	        var option = document.createElement("option");
	        var thenBy1 = document.getElementById('thenBy1');
	        var option1 = document.createElement("option");
	   	  	var thenBy2 = document.getElementById('thenBy2');
	   	  	var option2 = document.createElement("option"); 
	        
	        if(checkedValue){
	        	//IF VALUE PRESENT INITIAL COUNTER SHOULD BE MAX KEY
	        	var checkValue=document.getElementById('paraFrm_hiddenColumns').value
	        	var backFlag=document.getElementById('paraFrm_backFlag').value
	        	//alert("backFlag .. "+backFlag);
	        	if(checkValue!="" && backFlag=="true"){
	        		checkValue=checkValue.substr(0,checkValue.length-1);
	        		var splitComma=checkValue.split(",");
	        		var splitHiphen = "";
	        		for(var i=0;i<splitComma.length;i++){
	        			splitHiphen = splitComma[i].split("-");
	        		}
	        		//alert(splitHiphen[0]);
	        		misCounter = splitHiphen[0];
	        	}
	        
	        
	        	misCounter++;
		        // Assign text and value to Option object
		        opt.text = labelNameCheck;
		        opt.value = labelNameCheck;
		        
		        option.text = labelNameCheck;
		        option.value = labelNameCheck;
		        
		        option1.text = labelNameCheck;
		        option1.value = labelNameCheck;
		        
		        option2.text = labelNameCheck;
		        option2.value = labelNameCheck;
		        
		        // Add an Option object to Drop Down/List Box
		        document.getElementById("columnOrdering").options.add(opt); 
		        
		        try {
					sortBy.add(option, null); //Standard 
				}catch(error) {
					//alert('1');
					sortBy.add(option); // IE only
				}
				try {
					thenBy1.add(option1, null);
				}catch(error) {
					thenBy1.add(option1);
				}
				try {
					thenBy2.add(option2, null);
				}catch(error) {
					thenBy2.add(option2);
				}
		        
		        var hiddenValue=document.getElementById('paraFrm_hiddenColumns').value+
		        			misCounter+"-"+labelNameCheck+",";
		        					
		        document.getElementById('paraFrm_hiddenColumns').value=hiddenValue;
		        
		        
	        }else{
	        	var hiddenValue=document.getElementById('paraFrm_hiddenColumns').value;
	        	hiddenValue=hiddenValue.substr(0,hiddenValue.length-1);
	        	var splittedValue=hiddenValue.split(",");
	        	var finalValue="";
	        	var count=1;
	        	for(var i=0;i<splittedValue.length;i++){
	        		var splitDash = splittedValue[i].split("-");
	        	
	        		if(labelNameCheck!=splitDash[1]){
	        			finalValue+=count+"-"+splitDash[1]+",";
	        			count++;
	        		}else{
	        			for(var j = 0; j < document.getElementById("columnOrdering").childNodes.length; j++) {
	        				var opt1 = document.getElementById("columnOrdering").childNodes[j];
	        				if(opt1.text == splitDash[1]) {
	        					document.getElementById("columnOrdering").removeChild(opt1);
	        					break;
	        				}
	        			}
	        			for(var j = 0; j < document.getElementById("sortBy").childNodes.length; j++) {
	        				var opt1 = document.getElementById("sortBy").childNodes[j];
	        				if(opt1.text == splitDash[1]) {
	        					document.getElementById("sortBy").removeChild(opt1);
	        					break;
	        				}
	        			}
	        			for(var j = 0; j < document.getElementById("thenBy1").childNodes.length; j++) {
	        				var opt1 = document.getElementById("thenBy1").childNodes[j];
	        				if(opt1.text == splitDash[1]) {
	        					document.getElementById("thenBy1").removeChild(opt1);
	        					break;
	        				}
	        			}
	        			for(var j = 0; j < document.getElementById("thenBy2").childNodes.length; j++) {
	        				var opt1 = document.getElementById("thenBy2").childNodes[j];
	        				if(opt1.text == splitDash[1]) {
	        					document.getElementById("thenBy2").removeChild(opt1);
	        					break;
	        				}
	        			}
	        		}
	        	}
	        	document.getElementById('paraFrm_hiddenColumns').value=finalValue;
	        }
	   }catch(e){
	   		alert(e);
	   }
    }
    
    
   
    
    function setFieldsOnload(){
    	try{
    	
    	var columnOrderValues = document.getElementById('paraFrm_hiddenColumns').value;
    	//alert(columnOrderValues);
    	if(columnOrderValues!=""){
	    	columnOrderValues=columnOrderValues.substr(0,columnOrderValues.length-1);
	       	var splittedValue=columnOrderValues.split(",");
	       	for(var i=0;i<splittedValue.length;i++){
	       		//alert(splittedValue[i]);
	       		var sortBy = document.getElementById("sortBy");
		        var option = document.createElement("option");
		        var thenBy1 = document.getElementById('thenBy1');
		        var option1 = document.createElement("option");
		   	  	var thenBy2 = document.getElementById('thenBy2');
		   	  	var option2 = document.createElement("option"); 
		   	  	var opt = document.createElement("option");
	   	  	
	       		var splitDash = splittedValue[i].split("-");
	       		
	       		// Assign text and value to Option object
		        opt.text = splitDash[1];
		        opt.value = splitDash[1];
		        
	       		option.text = splitDash[1];
		        option.value = splitDash[1];
		        
		        option1.text = splitDash[1];
		        option1.value = splitDash[1];
		        
		        option2.text = splitDash[1];
		        option2.value = splitDash[1];
		        
		        // Add an Option object to Drop Down/List Box
		       	document.getElementById("columnOrdering").options.add(opt); 
		        
		        try {
					sortBy.add(option, null); //Standard 
				}catch(error) {
					sortBy.add(option); // IE only
				}
				try {
					thenBy1.add(option1, null);
				}catch(error) {
					thenBy1.add(option1);
				}
				try {
					thenBy2.add(option2, null);
				}catch(error) {
					thenBy2.add(option2);
				}
	   		}
       	}
       	//SET SELECTED OPTION ON BACK AND SEARCH (SORTING OPTIONS)
       	var hidSortBy = document.getElementById('paraFrm_hiddenSortBy').value;
       	if(hidSortBy!=""){
	    	for (var i=0;i<document.getElementById('sortBy').length;i++){
	    		//alert(document.getElementById('sortBy').options(i).text);
				if (hidSortBy == document.getElementById('sortBy').options(i).text){
					document.getElementById('sortBy').options(i).selected = true;
					break;
				}
			}
		}
		var hidThenBy1 = document.getElementById('paraFrm_hiddenThenBy1').value;
		if(hidThenBy1!=""){
	    	for (var x=0;x<document.getElementById('thenBy1').length;x++){
				if (hidThenBy1 == document.getElementById('thenBy1').options(x).text){
					document.getElementById('thenBy1').options(x).selected = true;
					break;
				}
			}
		}
		var hidThenBy2 = document.getElementById('paraFrm_hiddenThenBy2').value;
		if(hidThenBy2!=""){
	    	for (var y=0;y<document.getElementById('thenBy2').length;y++){
				if (hidThenBy2 == document.getElementById('thenBy2').options(y).text){
					document.getElementById('thenBy2').options(y).selected = true;
					break;
				}
			}
		}
		
    	}catch(e){
    		//alert(e);
    	}
    }
    
    function callSortBy(){
    	var sortBy = document.getElementById('sortBy').value;
    	document.getElementById('paraFrm_hiddenSortBy').value=sortBy;
    	
    }
    function callThenBy1(){
    	var thenBy1 = document.getElementById('thenBy1').value;
   		document.getElementById('paraFrm_hiddenThenBy1').value=thenBy1;
    	
    }
    function callThenBy2(){
    	var thenBy2 = document.getElementById('thenBy2').value;
   		document.getElementById('paraFrm_hiddenThenBy2').value=thenBy2;
    	
    }
    
    function callOrder(id){
    	if(id=="A"){
    		document.getElementById('paraFrm_sortByDsc').value="";
    		document.getElementById('paraFrm_sortByAsc').value="checked";
    	}
    	if(id=="D"){
    		document.getElementById('paraFrm_sortByAsc').value="";
    		document.getElementById('paraFrm_sortByDsc').value="checked";
    	}
    }
    function callOrder1(id){
    	if(id=="A"){
    		document.getElementById('paraFrm_thenByOrder1Dsc').value="";
    		document.getElementById('paraFrm_thenByOrder1Asc').value="checked";
    	}
    	if(id=="D"){
    		document.getElementById('paraFrm_thenByOrder1Asc').value="";
    		document.getElementById('paraFrm_thenByOrder1Dsc').value="checked";
    	}
    }
    function callOrder2(id){
    	if(id=="A"){
    		document.getElementById('paraFrm_thenByOrder2Dsc').value="";
    		document.getElementById('paraFrm_thenByOrder2Asc').value="checked";
    	}
    	if(id=="D"){
    		document.getElementById('paraFrm_thenByOrder2Asc').value="";
    		document.getElementById('paraFrm_thenByOrder2Dsc').value="checked";
    	}
    }

</script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<script> 
		 function  enableMyDiv(type)
		 {			
		          if(document.getElementById(type+"Check").checked)
			      {				  
				       document.getElementById(type+'Div').style.display=''; 
				       document.getElementById(type+"CheckVal").value="Y"
					   document.getElementById('paraFrm_hid'+type+'Comp').value="Y";
					   document.getElementById('paraFrm_hid'+type+'Self').value="";	
				             if(type=='travel')					            
				                    document.getElementById('trvlCompTSR').checked=true;				           
				              if(type=='accom')					             
				                    document.getElementById('accomCompACR').checked=true;				           
				              if (type=='local')				              
				                    document.getElementById('localCompLCR').checked=true;
				  }else
			      {			    
			      document.getElementById(type+'Div').style.display='none'; 
			      document.getElementById(type+"CheckVal").value=""
			      
			      document.getElementById('paraFrm_hid'+type+'Comp').value="";
				  document.getElementById('paraFrm_hid'+type+'Self').value="Y";	
				                if(type=='travel')					             
				                    document.getElementById('trvlSelfTSR').checked=false;				            
				              if(type=='accom')	
				                    document.getElementById('accomSelfASR').checked=false;
				              if (type=='local')
				                    document.getElementById('localSelfLSR').checked=false;
			      
			      
			      }
	     }
			 
		</script>