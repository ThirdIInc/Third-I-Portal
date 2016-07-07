<!--@author Krish @date 23-04-2010 -->
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<link rel="stylesheet" type="text/css"
	href="../pages/common/tabcontent.css" />
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>



<s:form action="TrvlApplicantReport" method="post" id="paraFrm"
	theme="simple">

	<s:hidden name="hiddenColumns" />
	<s:hidden name="trvBean" />
	<s:hidden name="accomBean" />
	<s:hidden name="convBean" />
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
					Report for Applicant</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
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
						type="button" value="Search" class="search" onclick="callSearch()" /></td>
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
		document.getElementById("paraFrm").action = 'TrvlApplicantReport_searchSavedReport.action';
		document.getElementById("paraFrm").submit();
		}
		
		</script>
		<tr>
			<td colspan="4">
			<table border="0" cellpadding="0" cellspacing="0" class="formbg"
				width="100%">
				<tr>
					<s:hidden name="savedReport" />
					<s:hidden name="reportId" />					
					<td width="20%" colspan="1"><label class="set"
						name="report.title" id="report.title"
						ondblclick="callShowDiv(this);"><%=label.get("report.title")%></label>:
					</td>
					<td colspan="3"><s:textfield size="25"
						maxlength="30" name="reportTitle"
						onkeypress="return allCharacters();" /></td>

				</tr>
			</table>
			</td>
		</tr>

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

		<!-- filter -->
		<tr>
			<td colspan="4">
			<div id="first">
			<table width="100%" border="0" cellpadding="2" cellspacing="0"
				class="formbg">

				<tr>
					<td width="23%" colspan="1"><label class="set"
						name="appl.date" id="appl.date" ondblclick="callShowDiv(this);"><%=label.get("appl.date")%></label>
					:</td>
					<td width="13%" colspan="1" height="22" align="left"><s:select
						theme="simple" name="applDtSelect" value="%{applDtSelect}"
						cssStyle="width: 80px"
						list="#{'':'--Select--','IE':'=','LT':'<','GT':'>','LE':'<=',
						'GE':'>='}"
						onchange="callBetween(this,'1');" /></td>
					<td width="20%" colspan="1"><s:textfield size="15"
						name="applicationDate" maxlength="10" onkeypress="return numbersWithHiphen();"/>

					<s:a
						href="javascript:NewCal('paraFrm_applicationDate','DDMMYYYY');">
						<img class="iconImage" src="../pages/images/recruitment/Date.gif"
							width="16" height="16" border="0" align="absmiddle" />
					</s:a></td>

				</tr>

				

				<tr>
					<td width="23%" colspan="1"><label class="set" name="trvl.sts"
						id="trvl.sts" ondblclick="callShowDiv(this);"><%=label.get("trvl.sts")%></label>
					:</td>
					<td width="13%" colspan="1" height="22" align="left"><s:select
						theme="simple" name="stsSelect" value="%{stsSelect}"
						cssStyle="width: 80px"
						list="#{'':'--Select--','P':'Pending','A':'Approved','R':'Rejected','B':'Return',
						'S':'Scheduled','PC':'Pending for cancellation','CC':'Cancelled'}"
						onchange="callBetween(this,'1');" /></td>


				</tr>

				<tr>
					<td width="23%" colspan="1"><label class="set" name="trvl.id"
						id="trvl.id" ondblclick="callShowDiv(this);"><%=label.get("trvl.id")%></label>
					:</td>
					<td width="13%" colspan="1" height="22" align="left"><s:textfield
						size="20" name="travelId" /></td>


				</tr>

				<tr>
					<td width="23%" colspan="1"><label class="set"
						name="trvl.purpose" id="trvl.purpose"
						ondblclick="callShowDiv(this);"><%=label.get("trvl.purpose")%></label>
					:</td>
					<td width="13%" colspan="1" height="22" align="left"><s:textfield
						size="20" name="trvlPurpose" readonly="true" /><s:hidden
						name="trvlPurposeId" /><img
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage"
						onclick="javascript:callsF9(500,325,'TrvlApplicantReport_f9TrvlPurpose.action');"></td>


				</tr>
				
				
				<tr>
					<td width="23%" colspan="1"><label class="set" name="appl.for"
						id="appl.for" ondblclick="callShowDiv(this);"><%=label.get("appl.for")%></label>
					:</td>
					<td width="13%" colspan="1" height="22" align="left"><s:select
						theme="simple" name="applForSelect" value="%{applForSelect}"
						cssStyle="width: 80px"
						list="#{'':'--Select--','S':'Self','G':'Guest','O':'Others'}"
						onchange="callBetween(this,'1');" /></td>


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





			</table>
			</div>

			<div id="second">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td colspan="4">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<tr>
							<td colspan="8" class="text_head"><strong
								class="forminnerhead"> <label class="set"
								name="report.generation" id="report.generation"
								ondblclick="callShowDiv(this);"><%=label.get("report.generation")%></label>
							</strong></td>
						</tr>
						<!-- added by krishna -->
						<!-- EMPLOYEE ID,EMPLOYEE NAME,APPLICATION DATE -->
						<tr>
							<td width="30%" align="right" colspan="1"><label class="set"
								name="emp.id" id="emp.id" ondblclick="callShowDiv(this);"><%=label.get("emp.id")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple" name="empId"
								onclick="AddItem('emp.id',this);" /></td>
							<td width="30%" align="right" colspan="1"><label class="set"
								name="emp.name" id="emp.name" ondblclick="callShowDiv(this);"><%=label.get("emp.name")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple" name="empName"
								onclick="AddItem('emp.name',this);" /></td>
							<td width="30%" align="right" colspan="1"><label class="set"
								name="appl.date" id="appl.date" ondblclick="callShowDiv(this);"><%=label.get("appl.date")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple" name="applDate"
								onclick="AddItem('appl.date',this);" /></td>
						</tr>


						<!-- BRANCH,TRAVEL START DATE,GRADE -->
						<tr>
							<td width="30%" align="right" colspan="1"><label class="set"
								name="branch" id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple" name="branch"
								onclick="AddItem('branch',this);" /></td>
							<td width="30%" align="right" colspan="1"><label class="set"
								name="trvl.startdate" id="trvl.startdate"
								ondblclick="callShowDiv(this);"><%=label.get("trvl.startdate")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="travelStartDate" onclick="AddItem('trvl.startdate',this);" /></td>
							<td width="30%" align="right" colspan="1"><label class="set"
								name="grade" id="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple" name="grade"
								onclick="AddItem('grade',this);" /></td>
						</tr>


						<!-- TRAVEL END DATE,TRAVEL TYPE,TRAVEL PURPOSE -->
						<tr>
							<td width="30%" align="right" colspan="1"><label class="set"
								name="trvl.enddate" id="trvl.enddate"
								ondblclick="callShowDiv(this);"><%=label.get("trvl.enddate")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="travelEndDate" onclick="AddItem('trvl.enddate',this);" /></td>
							<td width="30%" align="right" colspan="1"><label class="set"
								name="trvl.type" id="trvl.type" ondblclick="callShowDiv(this);"><%=label.get("trvl.type")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple" name="travelType"
								onclick="AddItem('trvl.type',this);" /></td>
							<td width="30%" align="right" colspan="1"><label class="set"
								name="trvl.purpose" id="trvl.purpose"
								ondblclick="callShowDiv(this);"><%=label.get("trvl.purpose")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple"
								name="travelPurpose" onclick="AddItem('trvl.purpose',this);" /></td>
						</tr>




						<!-- ADVANCE AMOUNT,,APPROVER,INITIATOR NAME -->
						<tr>
							<td width="30%" align="right" colspan="1"><label class="set"
								name="trvl.advAmt" id="trvl.advAmt"
								ondblclick="callShowDiv(this);"><%=label.get("trvl.advAmt")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple" name="trvlAdvAmt"
								onclick="AddItem('trvl.advAmt',this);" /></td>
							<td width="30%" align="right" colspan="1"><label class="set"
								name="approver" id="approver" ondblclick="callShowDiv(this);"><%=label.get("approver")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple" name="approver"
								onclick="AddItem('approver',this);" /></td>
							<td width="30%" align="right" colspan="1"><label class="set"
								name="init.name" id="init.name" ondblclick="callShowDiv(this);"><%=label.get("init.name")%></label>:</td>
							<td colspan="1"><s:checkbox theme="simple" name="initName"
								onclick="AddItem('init.name',this);" /></td>
						</tr>



						<!-- end of columns added by krishna -->

					</table>
					</td>
				</tr>
			</table>
			</div>


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

						<tr>
							<td colspan="1" width="25%"><label class="set"
								id="sortByLabel" name="sortByLabel"
								ondblclick="callShowDiv(this);"><%=label.get("sortByLabel")%></label>
							:</td>
							<td colspan="4"><select name="sortBy" id="sortBy"
								style="width: 200" onchange="callSortBy();">
								<!--  <option value="1">--Select--</option>-->
								<option value="Travel Id">Travel Id</option>
								<s:hidden name="hiddenSortBy" />
							</select></td>
						</tr>
						<tr height="35" valign="top">
							<td colspan="1" width="25%"><s:hidden name="sortByAsc" /> <s:hidden
								name="sortByDsc" /></td>
							<td colspan="1" width="5%"><input type="radio" value="A"
								name="sortByOrder" <s:property value="sortByAsc"/>></td>

							<td colspan="1" width="14%">Ascending</td>

							<td colspan="1" width="4%"><input type="radio" value="D"
								name="sortByOrder" <s:property value="sortByDsc"/>></td>

							<td colspan="1" width="57%">Descending</td>
						</tr>

						<tr>
							<td colspan="1" width="25%"><label class="set"
								id="thenByLabel" name="thenByLabel"
								ondblclick="callShowDiv(this);"><%=label.get("thenByLabel")%></label>
							:</td>
							<td colspan="4"><select name="thenBy1" id="thenBy1"
								style="width: 200" onchange="callThenBy1();">
								<!--<option value="1">--Select--</option>-->
								<option value="Travel Id">Travel Id</option>
							</select>
							<s:hidden name="hiddenThenBy1" />
							</td>
						</tr>
						<tr height="35" valign="top">
							<td colspan="1" width="25%"><s:hidden name="thenByOrder1Asc" />
							<s:hidden name="thenByOrder1Dsc" /></td>
							<td colspan="1" width="5%"><input type="radio" value="A"
								name="thenByOrder1" <s:property value="thenByOrder1Asc"/>></td>

							<td colspan="1" width="14%">Ascending</td>

							<td colspan="1" width="4%"><input type="radio" value="D"
								name="thenByOrder1" <s:property value="thenByOrder1Dsc"/>></td>

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
							</select>
							<s:hidden name="hiddenThenBy2" />
							</td>
						</tr>
						<tr height="35" valign="top">
							<td colspan="1" width="25%"><s:hidden name="thenByOrder2Asc" />
							<s:hidden name="thenByOrder2Dsc" /></td>
							<td colspan="1" width="5%"><input type="radio" value="A"
								name="thenByOrder2" <s:property value="thenByOrder2Asc"/>></td>

							<td colspan="1" width="14%">Ascending</td>

							<td colspan="1" width="4%"><input type="radio" value="D"
								name="thenByOrder2" <s:property value="thenByOrder2Dsc"/>></td>

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

							</select></td>
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


			<div id="fourth">
			<table cellspacing="0" cellpadding="0" class="formbg" width="100%"
				style="border-collapse: collapse;" border="0">
				<tr>
					<td colspan="4"><strong> <label
						id="selectAdvanceFilter" ondblclick="callShowDiv(this);"
						name="selectAdvanceFilter">Select appropriate advance
					filter option for report generation</label> </strong></td>
				</tr>
				<!-- 
				<tr>
					<td width="23%" colspan="1" height="22"><label class="set"
						name="costwise" id="costwise" ondblclick="callShowDiv(this);"><%=label.get("costwise")%></label>
					:</td>
					<td width="13%" colspan="1" height="22" align="left"><s:select
						theme="simple" name="costwiseSelect" value="%{costwiseSelect}"
						cssStyle="width: 80px"
						list="#{'':'--Select--','IE':'=','LT':'<','GT':'>','LE':'<=',
						'GE':'>='}"
						onchange="callBetween(this,'1');" /></td>
					<td width="54%" colspan="2" height="22"><s:textfield
						name="costwise" size="10" onkeypress="return numbersOnly();"
						maxlength="5" /></td>

				</tr>
				-->
				

				<tr>
					<td width="23%" colspan="1" height="22"><label class="set"
						name="start.date" id="start.date" ondblclick="callShowDiv(this);"><%=label.get("start.date")%></label>:</td>
					<td colspan="1" width="13%" align="left"><s:select
						theme="simple" name="startDtSelect" value="%{startDtSelect}"
						cssStyle="width: 80px"
						list="#{'':'--Select--','ON':'On','BF':'Before','AF':'After','OB':'On before',
						'OA':'On after'}" /></td>
					<td width="13%" colspan="1" height="22"><s:textfield
						name="startDate" maxlength="10"
						onkeypress="return numbersWithHiphen();"
						onfocus="clearText('paraFrm_startDate','dd-mm-yyyy')"
						onblur="setText('paraFrm_startDate','dd-mm-yyyy')" maxlength="10"
						size="10" /></td>
					<td width="33%" colspan="1" height="22"><s:a
						href="javascript:NewCal('paraFrm_startDate','DDMMYYYY');">
						<img class="iconImage" src="../pages/images/recruitment/Date.gif"
							width="16" height="16" border="0" align="absmiddle" />
					</s:a></td>

				</tr>
				<tr>
					<td width="23%" colspan="1" height="22"><label class="set"
						name="end.date" id="end.date" ondblclick="callShowDiv(this);"><%=label.get("end.date")%></label>:</td>

					<td colspan="1" width="13%" align="left"><s:select
						theme="simple" name="endDtSelect" value="%{endDtSelect}"
						cssStyle="width: 80px"
						list="#{'':'--Select--','ON':'On','BF':'Before','AF':'After','OB':'On before',
						'OA':'On after'}" /></td>

					<td width="13%" colspan="1" height="22"><s:textfield size="10"
						name="endDate" onkeypress="return numbersWithHiphen();" 
						maxLength="10" 
						onblur="setText('paraFrm_endDate','dd-mm-yyyy')"
						onfocus="clearText('paraFrm_endDate','dd-mm-yyyy')" maxlength="10" />
					<!--  
					<input
						onkeypress="return numbersWithHiphen();" id="frmDate2"
						onblur="setText('frmDate2','dd-mm-yyyy')"
						onfocus="clearText('frmDate2','dd-mm-yyyy')" maxlength="10"
						size="9" value="dd-mm-yyyy" name="frmDate2" />--></td>
					<td width="33%" colspan="2" height="22"><s:a
						href="javascript:NewCal('paraFrm_endDate','DDMMYYYY');">
						<img class="iconImage" src="../pages/images/recruitment/Date.gif"
							width="16" height="16" border="0" align="absmiddle" />
					</s:a></td>

				</tr>
				<tr>
					<td width="23%" colspan="1" height="22"><label class="set"
						name="cyclewise" id="cyclewise" ondblclick="callShowDiv(this);"><%=label.get("cyclewise")%></label>:</td>
					<td width="13%" colspan="1" align="left"><s:select
						theme="simple" name="cyclewiseSelect" value="%{cyclewiseSelect}"
						cssStyle="width: 80px"
						list="#{'':'--Select--','IE':'=','LT':'<','GT':'>','LE':'<=',
						'GE':'>='}"
						onchange="callToDateDispOnClick(this,'3','conf');" /></td>
					<td width="13%" colspan="1" height="22"><s:textfield size="10"
						name="cyclewise" onkeypress="return numbersOnly();" maxlength="10" /></td>
					<td width="50%" colspan="1">(no. of days)</td>

				</tr>

				<tr>
					<td width="23%" colspan="1" height="22"><label class="set"
						name="durationwise" id="durationwise"
						ondblclick="callShowDiv(this);"><%=label.get("durationwise")%></label>:</td>
					<td width="13%" colspan="1" align="left"><s:select
						theme="simple" name="durationwiselSelect"
						value="%{durationwiselSelect}" cssStyle="width: 80px"
						list="#{'':'--Select--','IE':'=','LT':'<','GT':'>','LE':'<=',
						'GE':'>='}"
						onchange="callToDateDispOnClick(this,'4','leave' );" /></td>
					<td width="13%" colspan="1" height="22"><s:textfield size="10"
						name="durationwise" maxlength="10"
						onkeypress="return numbersOnly();" /></td>
					<td width="50%" colspan="1">(no. of days)</td>

				</tr>
			</table>
			</div>




			</td>
		</tr>



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
						class="set" id="report.setting" name="report.setting"
						ondblclick="callShowDiv(this);"><%=label.get("report.setting")%></label>
					:</td>
					<td width="83%" colspan="1"><s:textfield name="settingName"
						size="25" theme="simple" maxlength="40" /></td>
				</tr>
			</table>
			</td>
		</tr>


		<tr>
			<s:hidden name="reqStatus" />
			<td width="100%"><input type="button" class="token"
				theme="simple" value="  Generate Report"
				onclick=" return generateReport();" /> <input type="button"
				class="reset" theme="simple" value=" Reset" onclick="resetForm()" />
			<input name="button" type="button" value="Save report criteria"
				class="save" onclick="saveReport()" /> <input name="button"
						type="button" value="Search" class="search" onclick="callSearch()" /></td>

		</tr>





	</table>

</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script>


		function displayDiv(chkId,divId,secId){
		//alert("chkId"+chkId);
				 //alert("divId"+divId);
				 //alert("secId"+secId);
		    	 //alert("1");
		    	 
		    	// alert(document.getElementById('trvChk').checked);
		    	 
				 if(document.getElementById('trvChk').checked
				 	&& document.getElementById('trvChk').disabled==false){//Travel checked
				 	//alert("in if1");
				 	document.getElementById('paraFrm').action="TrvlApplicantReport_getArrangement.action?id=trv";
				 	document.getElementById('paraFrm').submit();
				 	return true;
				 	
				 }if(document.getElementById('accomChk').checked
				 	&& document.getElementById('accomChk').disabled==false){//Lodging checked
				 	//alert("in if2");
				 	document.getElementById('paraFrm').action="TrvlApplicantReport_getArrangement.action?id1=accom";
				 	document.getElementById('paraFrm').submit();
				 	return true;
				 	
				 }if(document.getElementById('trConv').checked
				 	&& document.getElementById('trConv').disabled==false
				 	){//Local Conveyance checked
				 	//alert("in if3");
				 	document.getElementById('paraFrm').action="TrvlApplicantReport_getArrangement.action?id2=conv";
				 	document.getElementById('paraFrm').submit();
				 	return true;
				 	
				 }
				 
				 
				 //UNRECHABLE CODE BELOW
			     if(document.getElementById(chkId).checked){
			     	
			     	document.getElementById(divId).style.display="block";
			     	document.getElementById(secId).style.display="block";
			     	
			     }else{
			     	
			     	document.getElementById(divId).style.display="none";
			     	document.getElementById(secId).style.display="none";
			     	
			    }
			    
		    }
		    
		    

	function callValidation(){
	
	try{
		var applDtSelect=document.getElementById('paraFrm_applDtSelect').value;
		var applicationDate=document.getElementById('paraFrm_applicationDate').value;
		//var costwiseSelect=document.getElementById('paraFrm_costwiseSelect').value;
		//var costwise=document.getElementById('paraFrm_costwise').value;
		var startDtSelect=document.getElementById('paraFrm_startDtSelect').value;
		var startDate=document.getElementById('paraFrm_startDate').value;
		var endDtSelect=document.getElementById('paraFrm_endDtSelect').value;
		var endDate=document.getElementById('paraFrm_endDate').value;
		var cyclewiseSelect=document.getElementById('paraFrm_cyclewiseSelect').value;
		var cyclewise=document.getElementById('paraFrm_cyclewise').value;
		var durationwiselSelect=document.getElementById('paraFrm_durationwiselSelect').value;
		var durationwise=document.getElementById('paraFrm_durationwise').value;
		
		if((applDtSelect=="") && (applicationDate!="" )){
		alert("Please select application Date");
		return false;
		}
		if(applDtSelect!="" && applicationDate==""){
		alert("Please enter application Date.");
		return false;
		}
		/*
		if((costwiseSelect=="") && (costwise!="" )){
		alert("Please select costwise");
		return false;
		}
		if(costwiseSelect!="" && costwise==""){
		alert("Please enter costwise amount.");
		return false;
		}
		*/
		/*
		if(startDtSelect=="" && startDate!=""){
		alert("Please select startDate.");
		return false;
		}*/
		if(startDtSelect!="" && startDate=="" && startDate!="dd-mm-yyyy"){
		alert("Please enter startDate.");
		return false;
		}
		/*
		if(endDtSelect=="" && endDate!=""){
		alert("Please select endDate.");
		return false;
		} */
		if(endDtSelect!="" && endDate=="" && endDate!="dd-mm-yyyy"){
		alert("Please enter endDate.");
		return false;
		}
		
		if(cyclewiseSelect=="" && cyclewise!=""){
		alert("Please select cyclewise.");
		return false;
		}
		if(cyclewiseSelect!="" && cyclewise==""){
		alert("Please enter cyclewise.");
		return false;
		}
		
		if(durationwiselSelect=="" && durationwise!=""){
		alert("Please select durationwise.");
		return false;
		}
		//if(durationwiselSelect!="" && durationwise==""){
		//alert("Please enter no. of days.");
		//return false;
		//}
		
		return true;
		}catch(e){
		//alert(e);
		}
	         
	}



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
		//callAllDisable();
		//document.getElementById('betweenOn').style.display='none'; 
		setFieldsOnload();
		
	}
	
	function setFieldsOnload(){
    	try{
    	var sortBy = document.getElementById('sortBy').value;
    	//alert(sortBy);
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
	
	function resetForm()
	{
		document.getElementById('paraFrm').target='main';
		document.getElementById('paraFrm').action="TrvlApplicantReport_reset.action";
		document.getElementById('paraFrm').submit();
	
	}
	
	function saveReport(){
	//alert("1");
	
	
	if(!callValidation()){	
	return false;
	}
		var settingName = trim(document.getElementById('paraFrm_settingName').value);
		//alert("2");
		if(settingName == ""){
		//alert("3");
			alert("Please enter "+document.getElementById('report.setting').innerHTML);
			document.getElementById('paraFrm_settingName').focus();
			return false;
		}
		//alert("4");
		document.getElementById('paraFrm').target='main';
		document.getElementById('paraFrm').action="TrvlApplicantReport_saveReport.action";
		document.getElementById('paraFrm').submit();
	
	}


	function generateReport()
	{
		try{
		//alert("generateReport");
		if(!callValidation()){
			//alert("dipti")
		return false;
	}
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
			
		
  			
  			// for sort the filter   
	   	  	var sortBy = document.getElementById('sortBy').value;
	   	  	 //alert("sortBy"+sortBy);
	   	  	var thenBy1 = document.getElementById('thenBy1').value;
	   	  	//alert("thenBy1"+thenBy1);
	   	  	var thenBy2 = document.getElementById('thenBy2').value; 
	   	  	//start
	   	  	
	   	  	
	   	//Application Date
			
		var applDate=document.getElementById('paraFrm_applicationDate').value;

		if(document.getElementById("paraFrm_applDtSelect").value!=""){
				if(applDate=="" || applDate=="dd-mm-yyyy"){
					alert("Please enter "+document.getElementById('appl.date').innerHTML);
					return false;
				}
				
				
				if(applDate!=""|| applDate!="dd-mm-yyyy" ){
				  	var fld=['paraFrm_applicationDate'];
				  	var lbl=['appl.date'];
			   		var chkfrmDate=validateDate(fld,lbl);
			   		if(!chkfrmDate) {
				    	return false;
				   	}
	  			} 	
	  			
			}
			
			
			//cost wise
			/*
              if(document.getElementById("paraFrm_costwiseSelect").value!=""){
				if(document.getElementById("paraFrm_costwise").value==""){
					alert("Please enter cost amount");
					document.getElementById('paraFrm_costwise').focus();
					return false;
				}
			}
			*/
				//Travel Start Date
			
		var trvlStartDate=document.getElementById('paraFrm_startDate').value;

		if(document.getElementById("paraFrm_startDtSelect").value!=""){
				if(trvlStartDate=="" || trvlStartDate=="dd-mm-yyyy"){
					alert("Please enter "+document.getElementById('start.date').innerHTML);
					return false;
				}
				
				
				if(trvlStartDate!=""|| trvlStartDate!="dd-mm-yyyy" ){
				  	var fld=['paraFrm_startDate'];
				  	var lbl=['start.date'];
			   		var chkfrmDate=validateDate(fld,lbl);
			   		if(!chkfrmDate) {
				    	return false;
				   	}
	  			} 	
	  			
			}
			//Travel End Date
			
		var trvlEndDate=document.getElementById('paraFrm_endDate').value;
		if(document.getElementById("paraFrm_endDtSelect").value!=""){
				if(trvlEndDate=="" || trvlEndDate=="dd-mm-yyyy"){
					alert("Please enter "+document.getElementById('end.date').innerHTML);
					return false;
				}
				
				
				if(trvlEndDate!=""|| trvlEndDate!="dd-mm-yyyy" ){
				  	var fld=['paraFrm_endDate'];
				  	var lbl=['end.date'];
			   		var chkfrmDate=validateDate(fld,lbl);
			   		if(!chkfrmDate) {
				    	return false;
				   	}
	  			} 	
	  			
			}
			
			//Cycle wise
			
              if(document.getElementById("paraFrm_cyclewiseSelect").value!=""){
				if(document.getElementById("paraFrm_cyclewise").value==""){
					alert("Please enter cost amount");
					document.getElementById('paraFrm_cyclewise').focus();
					return false;
				}
			}
			
			//Travel duration wise
			
              if(document.getElementById("paraFrm_durationwiselSelect").value!=""){
				if(document.getElementById("paraFrm_durationwise").value==""){
					alert("Please enter Duration");
					document.getElementById('paraFrm_durationwise').focus();
					return false;
				}
			}
			
			
  		}catch(e){
  			alert(e);
  		}
  		
  		if(document.getElementById("reportViewV").checked){
  			document.getElementById('paraFrm').target='_self';
	 		document.getElementById('paraFrm').action="TrvlApplicantReport_viewOnScreen.action";
     		document.getElementById('paraFrm').submit();
		}else{
		  	document.getElementById('paraFrm').target='_blank';
			document.getElementById('paraFrm').action="TrvlApplicantReport_report.action";
			document.getElementById('paraFrm').submit();
			document.getElementById('paraFrm').target="main"; 
		}
	}
	
	
	function AddItem(labelName,id)
	
    {
  //  alert("id"+id);
	//alert(labelName);
        try{
        	var labelNameCheck = document.getElementById(labelName).innerHTML;
        	//alert('labelNameCheck--'+labelNameCheck);
	        var checkedValue = id.checked;
	        	//alert('checkedValue--'+checkedValue);
	        // Create an Option object                
	        var opt = document.createElement("option");
	        //alert('opt--'+opt);
	        var sortBy = document.getElementById("sortBy");
	        var option = document.createElement("option");
	        var thenBy1 = document.getElementById('thenBy1');
	        var option1 = document.createElement("option");
	   	  	var thenBy2 = document.getElementById('thenBy2');
	   	  	var option2 = document.createElement("option"); 
	        
	        if(checkedValue){
	        	//IF VALUE PRESENT INITIAL COUNTER SHOULD BE MAX KEY
	        	var checkValue=document.getElementById('paraFrm_hiddenColumns').value
	        	//alert('checkValue--'+checkValue);
	        	var backFlag=document.getElementById('paraFrm_backFlag').value
	        	//alert("backFlag .. "+backFlag);
	        	if(checkValue!="" && backFlag=="true"){
	        	//alert('if--');
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
		       // alert(' opt.text --'+opt.text);
		        // alert(' opt.value--'+opt.value);
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
    
    function listbox_move(listID, direction) {
		try{
			var listbox = document.getElementById(listID);
			var selIndex = listbox.selectedIndex;

			if(-1 == selIndex) {
				alert("Please select an option to move.");
				return;
			}

			var increment = -1;
			if(direction == 'up')
				increment = -1;
			else
				increment = 1;

			if((selIndex + increment) < 0 ||
				(selIndex + increment) > (listbox.options.length-1)) {
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
			}catch(e){
				alert(e);
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
	
</script>




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
