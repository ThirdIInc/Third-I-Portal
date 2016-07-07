<!--@author Ayyappa @date 13-04-2010 -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="TravelAccntReport" validate="true" id="paraFrm" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="2" cellspacing="1" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head"> Report for Accountant
						</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<!-- BUTTON PANEL BEGINS -->
		<tr><s:hidden name='backFlag' />
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><input type="button" class="token"
						theme="simple" value="  Generate Report"
						onclick=" return generateReport();" /> <input type="button"
						class="reset" theme="simple" value=" Reset" onclick="resetForm()" />
						<input name="button" type="button" value="Save report criteria"
						class="save" onclick="saveReport()" /> 
						<input name="button" type="button" value="  Search"
						class="search" onclick="searchReport()" /></td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- BUTTON PANEL ENDS -->
		
		<!-- SEARCH AND REPORT TITLE FIELDS -->
		<tr>
			<td>
			<table border="0" cellpadding="0" cellspacing="0" class="formbg"
				width="100%">
				<tr>
					<td width="20%"><s:hidden name="accntRepBean.savedReport" />
					<s:hidden name="accntRepBean.reportId" /><!-- Report ID -->
					<label class="set" name="report.title" id="report.title"
						ondblclick="callShowDiv(this);"><%=label.get("report.title")%></label>:</td>
					<td colspan="2"><s:textfield size="30" maxlength="30"
						name="accntRepBean.reportTitle" onkeypress="return allCharacters();" /></td>
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
			<td>
			<!-- DIV FIRST BEGINS - FILTERS -->
			<div id="first">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
				<tr>
					<td colspan="6" class="text_head"><strong
						class="forminnerhead"> <label class="set"
						name="search.criteria" id="search.criteria"
						ondblclick="callShowDiv(this);"><%=label.get("search.criteria")%></label>:
					</strong></td>
				</tr>
				<!-- TYPE OF EXPENSE -->
				<tr>
					<td width="20%"><label class="set" name="tms.typeofexpense" id="tms.typeofexpense"
						ondblclick="callShowDiv(this);"><%=label.get("tms.typeofexpense")%></label>:
						<font color="red">*</font>
					</td>
					<td width="20%" colspan="1"><s:select name="accntRepBean.typeExp" 
						headerKey="" headerValue="--Select--" cssStyle="width:150"
						list="#{'A':'Advance','C':'Claim'}" onchange="showTab();" 
						value="%{typeExp}" />
					</td>
					<td width="60%" colspan="4"></td>
				</tr>		
		<script type="text/javascript">
			function showTab() {
				document.getElementById('advStaTab').style.display='';
				var val = document.getElementById('paraFrm_accntRepBean_typeExp').value;
				var s = document.getElementById("paraFrm_accntRepBean_status");
				try{
					if(val=='A') {
			   		     s.remove(4);
					}else if(val=='C') {
					 	var option = document.createElement("option");
		      		 	option.text = "Over Payment";		
		      		 	option.value = "X";
				      	try {
							s.add(option, null); //Standard 
						}catch(error) {
							s.add(option); // IE only
						}
		      		 }
		      		 else{
		      		 	document.getElementById('advStaTab').style.display='none';
		      		 }
				}catch(error){}
			}
		</script>						
				<!-- ADVANCE - STATUS -->
				<tr id="advStaTab" style="display: none">
					<td width="20%"><label class="set" name="tms.status" id="tms.status"
						ondblclick="callShowDiv(this);"><%=label.get("tms.status")%></label>:
					</td>
					<td width="20%" colspan="1"><s:select name="accntRepBean.status" 
						headerKey="" headerValue="--Select--" cssStyle="width:150"
						list="#{'B':'Pending','P':'Partially Paid','C':'Closed'}"
						value="%{status}" />
					</td>
					<td width="60%" colspan="4"></td>
				</tr>
				<!-- GRADE -->
				<tr>
					<td width="20%"><label class="set" name="tms.grade" id="tms.grade"
						ondblclick="callShowDiv(this);"><%=label.get("tms.grade")%></label>
					:</td>
					<td width="20%" colspan="1"><s:textfield size="25"
						name="accntRepBean.gradeName" readonly="true" />
						<s:hidden name="accntRepBean.gradeId" />
						<img src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="14" width="14" theme="simple"
						onclick="javascript:callsF9(500,325,'TravelAccntReport_f9grade.action');" />
					</td>
					<td width="60%" colspan="4"></td>
				</tr>
				<!-- BRANCH -->
				<tr>
					<td width="20%"><label class="set" name="tms.branch" id="tms.branch"
						ondblclick="callShowDiv(this);"><%=label.get("tms.branch")%></label>
					: </td>
					<td width="25%" colspan="1"><s:textfield size="25"
						name="accntRepBean.branchName" readonly="true" />
						<s:hidden name="accntRepBean.branchId" />
						<img src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="14" width="14" theme="simple"
						onclick="javascript:callsF9(500,325,'TravelAccntReport_f9branch.action');" />
					</td>
					<td width="55%" colspan="4"></td>
				</tr>				
				<!-- DEPARTMENT -->
				<tr>
					<td width="20%"><label class="set" name="tms.dept" id="tms.dept"
						ondblclick="callShowDiv(this);"><%=label.get("tms.dept")%></label>
					:</td>
					<td width="25%" colspan="1"><s:textfield size="25"
						name="accntRepBean.deptName" readonly="true" />
						<s:hidden name="accntRepBean.deptId" />
						<img src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="14" width="14" theme="simple"
						onclick="javascript:callsF9(500,325,'TravelAccntReport_f9department.action');" />
					</td>
					<td width="55%" colspan="4"></td>
				</tr>								
				<!-- ADVANCE AMOUNT -->
				<tr>
					<td colspan="1" width="20%"><label class="set" name="tms.advanceamount" id="tms.advanceamount"
						ondblclick="callShowDiv(this);"><%=label.get("tms.advanceamount")%></label>:</td>
					<td colspan="1" align="left" width="10%"><s:select theme="simple" name="advAmtSelect" cssStyle="width: 80px"
						list="#{'':'--Select--','IE':'=','LT':'<','GT':'>','LE':'<=',
						'GE':'>=','BN':'Between'}" value="%{advAmtSelect}" 
						onchange="callBetween(this,'1');" />
						<s:textfield name="accntRepBean.advAmtFrom" size="9" onkeyup="validAmt(this)" 
						onkeypress="return numbersWithDot();" /></td>					
					<td colspan="4" id="betweenOn1" width="55%" height="22">
					<table cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td colspan="1" id="toDateDiv412" width="15%" align="center"><strong>
							AND </strong></td>
							<td colspan="1" height="22" width="10%" id="toDivAndText"><s:textfield
									name="accntRepBean.advAmtTo" size="9" onkeypress="return numbersWithDot();" 
									onkeyup="validAmt(this)" /></td>
							<td colspan="2">&nbsp;</td>
						</tr>

					</table>
					</td>
				</tr>
				<!-- ADVANCE CLAIM SETTLEMENT -->
				<tr>
					<td colspan="1" width="20%"><label class="set" name="tms.advclaimsetment" id="tms.advclaimsetment"
						ondblclick="callShowDiv(this);"><%=label.get("tms.advclaimsetment")%></label>:</td>
					<td colspan="1" align="left" width="10%"><s:select theme="simple" name="claimsetSelect" cssStyle="width: 80px"
						list="#{'':'--Select--','IE':'=','LT':'<','GT':'>','LE':'<=',
						'GE':'>=','BN':'Between'}" value="%{claimsetSelect}" 
						onchange="callBetween(this,'2');" />

						<s:textfield name="accntRepBean.claimsetFrom" size="9" onkeyup="validAmt(this)" 
						onkeypress="return numbersWithDot();" />					
					<td colspan="4" id="betweenOn2" width="55%" height="22">
					<table cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td colspan="1" id="toDateDiv412" width="15%" align="center"><strong>
							AND </strong></td>
							<td colspan="1" height="22" width="10%" id="toDivAndText"><s:textfield
									name="accntRepBean.claimsetTo" size="9" onkeypress="return numbersWithDot();" 
									onkeyup="validAmt(this)" /></td>
							<td colspan="2">&nbsp;</td>
						</tr>

					</table>
					</td>
				</tr>		
				<script type="text/javascript">
					function validAmt(obj) {
						var amt = obj.value;
						if(amt!='.' && isNaN(amt)){
							alert("Only Sigle dot is  allowed");
						}
						else
						{
							callNumOfFraction(obj.name,2)
						}
					}
				</script>	
				<!-- FROM DATE 
				<tr>
					<td colspan="1" height="22" width="20%"><label class="set"
						name="tms.fromdate" id="tms.fromdate"
						ondblclick="callShowDiv(this);"><%=label.get("tms.fromdate")%></label>:</td>
					<td colspan="1" align="left" width="25%"><s:select
						theme="simple" name="fromDateSelect" value="%{fromDateSelect}"
						list="#{'':'--Select--','ON':'On','BF':'Before','AF':'After','OB':'On before',
						'OA':'On after','BN':'Between'}"  cssStyle="width: 80px"  
						onchange="callToDateDispOnClick(this,'1','fromDate' );" />
						<s:textfield size="9"
						onkeypress="return numbersWithHiphen();" name="accntRepBean.fromDateFromDate"
						onblur="setText('paraFrm_accntRepBean_fromDateFromDate','dd-mm-yyyy')"
						onfocus="clearText('paraFrm_accntRepBean_fromDateFromDate','dd-mm-yyyy')"
						maxlength="10" />
						<s:a
						href="javascript:NewCal('paraFrm_accntRepBean_fromDateFromDate','DDMMYYYY');">
						<img class="iconImage" src="../pages/images/recruitment/Date.gif"
							width="16" height="16" border="0" align="absmiddle" />
					</s:a></td>
					<td colspan="4" width="55%">
					<table cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td colspan="1" id="toDateDiv1" width="15%" align="center"><strong>AND
							</strong></td>
							<td width="10%" colspan="1" id="toDateDivLabel1"
								height="22"><s:textfield size="9"
								onkeypress="return numbersWithHiphen();" name="accntRepBean.fromDateToDate"
								onblur="setText('paraFrm_accntRepBean_fromDateToDate','dd-mm-yyyy')"
								onfocus="clearText('paraFrm_accntRepBean_fromDateToDate','dd-mm-yyyy')"
								maxlength="10" /></td>
							<td id="toDatePicker1">&nbsp;<s:a
								href="javascript:NewCal('paraFrm_accntRepBean_fromDateToDate','DDMMYYYY');">
								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" />
							</s:a></td>
						</tr>
					</table>
					</td>
				</tr>	
				-->			
				<!-- TO DATE 
				<tr>
					<td colspan="1" height="22" width="20%"><label class="set"
						name="tms.todate" id="tms.todate"
						ondblclick="callShowDiv(this);"><%=label.get("tms.todate")%></label>:</td>
					<td colspan="1" align="left" width="25%"><s:select
						theme="simple" name="toDateSelect" value="%{toDateSelect}"
						list="#{'':'--Select--','ON':'On','BF':'Before','AF':'After','OB':'On before',
						'OA':'On after','BN':'Between'}"  cssStyle="width: 80px" 
						onchange="callToDateDispOnClick(this,'2','toDate' );" />
						<s:textfield size="9"
						onkeypress="return numbersWithHiphen();" name="accntRepBean.toDateFromDate"
						onblur="setText('paraFrm_accntRepBean_toDateFromDate','dd-mm-yyyy')"
						onfocus="clearText('paraFrm_accntRepBean_toDateFromDate','dd-mm-yyyy')"
						maxlength="10" />
						<s:a
						href="javascript:NewCal('paraFrm_accntRepBean_toDateFromDate','DDMMYYYY');">
						<img class="iconImage" src="../pages/images/recruitment/Date.gif"
							width="16" height="16" border="0" align="absmiddle" />
					</s:a></td>
					<td colspan="4" width="55%">
					<table cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td colspan="1" id="toDateDiv2" width="15%" align="center"><strong>AND
							</strong></td>
							<td width="10%" colspan="1" id="toDateDivLabel2"
								height="22"><s:textfield size="9"
								onkeypress="return numbersWithHiphen();" name="accntRepBean.toDateToDate"
								onblur="setText('paraFrm_accntRepBean_toDateToDate','dd-mm-yyyy')"
								onfocus="clearText('paraFrm_accntRepBean_toDateToDate','dd-mm-yyyy')"
								maxlength="10" /></td>
							<td id="toDatePicker2">&nbsp;<s:a
								href="javascript:NewCal('paraFrm_accntRepBean_toDateToDate','DDMMYYYY');">
								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" />
							</s:a></td>
						</tr>
					</table>
					</td>
				</tr>		
				-->						
				<!-- APPLICATION FOR -->
				<tr>
					<td width="20%" colspan="1"><label class="set"
						name="tms.applifor" id="tms.applifor"
						ondblclick="callShowDiv(this);"><%=label.get("tms.applifor")%></label>:</td>
					<td colspan="5" width="80%"><s:select name="accntRepBean.appliFor" value="%{appliFor}"  
						headerKey="" headerValue="--Select--" cssStyle="width:150"
						list="#{'S':'Self','G':'Guest','O':'Others'}" /></td>
				</tr>
				<!-- TRAVEL -->
				<tr>
					<td colspan="1" width="20%"><s:hidden name="travelCheckVal"
						id="travelCheckVal" /> <s:checkbox theme="simple"
						name="accntRepBean.travelCheck" 
						onclick="enableMyDiv('travel');" /> <label class="set"
						name="tms.travel" id="tms.travel" ondblclick="callShowDiv(this);"><%=label.get("tms.travel")%></label></td>
					<td width="80%" id="travelDiv" style="display: none" colspan="5">
					<table cellpadding="0" cellspacing="0" border="0"
						style="border-collapse: collapse;" width="100%">
						<tr>
							<td class="formtext" colspan="1" width="10%"><s:hidden
								name="hidTravelSelf" /> <input type="radio" value="TSR"
								name="trvlRadio" id="trvlRadio"
								<s:property value="hidTravelSelf" />
								onclick="callRadioChk('Travel',1);"> <label class="set"
								name="tms.self" id="tms.self" ondblclick="callShowDiv(this);"><%=label.get("tms.self")%></label></td>
							<td class="formtext" colspan="5" width="70%"><s:hidden
								name="hidTravelComp" /> <input type="radio" value="TCR"
								name="trvlRadio" id="trvlRadio"
								<s:property value="hidTravelComp" />
								onclick="callRadioChk('Travel',2);"> <label class="set"
								name="tms.company" id="tms.company"
								ondblclick="callShowDiv(this);"><%=label.get("tms.company")%></label></td>
						</tr>
					</table>
					</td>
				</tr>				
				<!-- ACCOMMODATION -->
				<tr>
					<td colspan="1" width="20%"><s:hidden name="accomCheckVal"
						id="accomCheckVal" /> <s:checkbox theme="simple"
						name="accntRepBean.accomCheck" onclick="enableMyDiv('accom');" /> <label
						class="set" name="tms.accomodation" id="tms.accomodation"
						ondblclick="callShowDiv(this);"><%=label.get("tms.accomodation")%></label></td>

					<td width="80%" id="accomDiv" style="display: none" colspan="5">
					<table cellpadding="0" cellspacing="0" border="0"
						style="border-collapse: collapse;" width="100%">
						<tr>
							<td class="formtext" colspan="1" width="10%"><s:hidden
								name="hidAccomSelf" /> <input type="radio" value="ASR"
								name="accomRadio" id="accomRadio"
								<s:property value="hidAccomSelf" /> 
								onclick="callRadioChk('Accom',1);"> <label class="set"
								name="tms.self" id="tms.self2" ondblclick="callShowDiv(this);"><%=label.get("tms.self")%></label></td>
							<td class="formtext" colspan="5" width="70%"><s:hidden
								name="hidAccomComp" /> <input type="radio" value="ACR"
								name="accomRadio" id="accomRadio"
								<s:property value="hidAccomComp" /> 
								onclick="callRadioChk('Accom',2);"> <label class="set"
								name="tms.company" id="tms.company2"
								ondblclick="callShowDiv(this);"><%=label.get("tms.company")%></label></td>
						</tr>
					</table>
					</td>
				</tr>
				<!-- LOCAL CONVEYANCE -->
				<tr>
					<td colspan="1" width="20%"><s:hidden name="localCheckVal"
						id="localCheckVal" /> <s:checkbox theme="simple"
						name="accntRepBean.localCheck" onclick="enableMyDiv('local');" />
					<label class="set" name="tms.localConv" id="tms.localConv"
						ondblclick="callShowDiv(this);"><%=label.get("tms.localConv")%></label></td>
					<td width="80%" id="localDiv" style="display: none" colspan="5">
					<table cellpadding="0" cellspacing="0" border="0"
						style="border-collapse: collapse;" width="100%">
						<tr>
							<td class="formtext" colspan="1" width="10%"><s:hidden
								name="hidLocalSelf" /> <input type="radio" value="LSR"
								name="localRadio" id="localRadio"
								<s:property value="hidLocalSelf" /> 
								onclick="callRadioChk('Local',1);"> <label class="set"
								name="tms.self" id="tms.self3" ondblclick="callShowDiv(this);"><%=label.get("tms.self")%></label></td>
							<td class="formtext" colspan="5" width="70%"><s:hidden
								name="hidLocalComp" /> <input type="radio" value="LCR"
								name="localRadio" id="localRadio"
								<s:property value="hidLocalComp" /> 
								onclick="callRadioChk('Local',2);"> <label class="set"
								name="tms.company" id="tms.company3"
								ondblclick="callShowDiv(this);"><%=label.get("tms.company")%></label></td>
						</tr>
					</table>
					</td>
				</tr>				
			</table>
			</div>
			
			<script type="text/javascript">
				function enableMyDiv(name) {
					var test = document.getElementById('paraFrm_accntRepBean_'+name+'Check').checked;
					if(test){
						document.getElementById(name+'Div').style.display='';
					}else{
						document.getElementById(name+'Div').style.display='none';
					}
				}
				function callRadioChk(name, value) {
					if(value == 1) {
						document.getElementById('paraFrm_hid'+name+'Self').value="checked";
						document.getElementById('paraFrm_hid'+name+'Comp').value="";
					}else if(value == 2){
						document.getElementById('paraFrm_hid'+name+'Self').value="";
						document.getElementById('paraFrm_hid'+name+'Comp').value="checked";
					}
				}
			</script>
			<!-- DIV FIRST ENDS - FILTERS -->
			
			<!-- DIV SECOND BEGINS - COLUMN DEFINITIONS -->
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
						<!-- APPLICANT NAME, GRADE, BRANCH -->
						<tr>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="tms.applicantname" id="tms.applicantname" ondblclick="callShowDiv(this);"><%=label.get("tms.applicantname")%></label>:</td>
							<td width="10%"><s:checkbox theme="simple"
								name="accntRepBean.appliNameFlag" onclick="AddItem('tms.applicantname',this);" /></td>																												
							<td width="20%" align="right" colspan="1"><label class="set"
								name="tms.grade" id="tms.grade" ondblclick="callShowDiv(this);"><%=label.get("tms.grade")%></label>:</td>
							<td width="10%"><s:checkbox theme="simple"
								name="accntRepBean.gradeFlag" onclick="AddItem('tms.grade',this);" /></td>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="tms.branch" id="tms.branch" ondblclick="callShowDiv(this);"><%=label.get("tms.branch")%></label>:</td>
							<td width="20%"><s:checkbox theme="simple"
								name="accntRepBean.branchFlag" onclick="AddItem('tms.branch',this);" /></td>	
						</tr>
						<!-- EMPLOYEE ID, TRAVEL START DATE, TRAVEL END DATE -->
						<tr>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="tms.empid" id="tms.empid" ondblclick="callShowDiv(this);"><%=label.get("tms.empid")%></label>:</td>
							<td width="10%"><s:checkbox theme="simple"
								name="accntRepBean.empIdFlag" onclick="AddItem('tms.empid',this);" /></td>																												
							<td width="20%" align="right" colspan="1"><label class="set"
								name="tms.startdate" id="tms.startdate" ondblclick="callShowDiv(this);"><%=label.get("tms.startdate")%></label>:</td>
							<td width="10%"><s:checkbox theme="simple"
								name="accntRepBean.startDateFlag" onclick="AddItem('tms.startdate',this);" /></td>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="tms.enddate" id="tms.enddate" ondblclick="callShowDiv(this);"><%=label.get("tms.enddate")%></label>:</td>
							<td width="20%"><s:checkbox theme="simple"
								name="accntRepBean.endDateFlag" onclick="AddItem('tms.enddate',this);" /></td>	
						</tr>
						<!-- STATUS, TRAVEL PURPOSE, TRAVEL TYPE -->
						<tr>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="tms.status" id="tms.status" ondblclick="callShowDiv(this);"><%=label.get("tms.status")%></label>:</td>
							<td width="10%"><s:checkbox theme="simple"
								name="accntRepBean.statusFlag" onclick="AddItem('tms.status',this);" /></td>																												
							<td width="20%" align="right" colspan="1"><label class="set"
								name="tms.purpose" id="tms.purpose" ondblclick="callShowDiv(this);"><%=label.get("tms.purpose")%></label>:</td>
							<td width="10%"><s:checkbox theme="simple"
								name="accntRepBean.purposeFlag" onclick="AddItem('tms.purpose',this);" /></td>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="tms.traveltype" id="tms.traveltype" ondblclick="callShowDiv(this);"><%=label.get("tms.traveltype")%></label>:</td>
							<td width="20%"><s:checkbox theme="simple"
								name="accntRepBean.typeFlag" onclick="AddItem('tms.traveltype',this);" /></td>	
						</tr>						
						<!-- ADVANCE AMOUNT -->
						<tr>
							<td width="20%" align="right" colspan="1"><label class="set"
								name="tms.advanceamount" id="tms.advanceamount" ondblclick="callShowDiv(this);"><%=label.get("tms.advanceamount")%></label>:</td>
							<td width="10%"><s:checkbox theme="simple"
								name="accntRepBean.advAmtFlag" onclick="AddItem('tms.advanceamount',this);" /></td>
							<td colspan="4" width="70%"></td>		
						</tr>	
									
					</table>
					</td>
				</tr>
			</table>
			</div>
			<!-- DIV SECOND ENDS - COLUMN DEFINITIONS -->
			
			<!-- DIV THIRD BEGINS - SORTING OPTIONS -->
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
							</select>
							<s:hidden name="hiddenSortBy" />
							</td>
						</tr>
						<tr height="35" valign="top">
							<td colspan="1" width="25%"><s:hidden name="sortByAsc" /> <s:hidden
								name="sortByDsc" /></td>
							<td colspan="1" width="5%"><input type="radio" value="A" onclick="callOrder('sortBy','A');"
								name="sortByOrder" <s:property value="sortByAsc"/> ></td>

							<td colspan="1" width="14%">Ascending</td>

							<td colspan="1" width="4%"><input type="radio" value="D" onclick="callOrder('sortBy','D');"
								name="sortByOrder" <s:property value="sortByDsc"/> ></td>

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
							</select>
							<s:hidden name="hiddenThenBy1" />
							</td>
						</tr>
						<tr height="35" valign="top">
							<td colspan="1" width="25%"><s:hidden name="thenByOrder1Asc" />
							<s:hidden name="thenByOrder1Dsc" /></td>
							<td colspan="1" width="5%"><input type="radio" value="A"  onclick="callOrder('thenByOrder1','A');"
								name="thenByOrder1" <s:property value="thenByOrder1Asc"/>></td>

							<td colspan="1" width="14%">Ascending</td>

							<td colspan="1" width="4%"><input type="radio" value="D"  onclick="callOrder('thenByOrder1','D');"
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
							<td colspan="1" width="5%"><input type="radio" value="A" onclick="callOrder('thenByOrder2','A');"
								name="thenByOrder2" <s:property value="thenByOrder2Asc"/>></td>

							<td colspan="1" width="14%">Ascending</td>

							<td colspan="1" width="4%"><input type="radio" value="D" onclick="callOrder('thenByOrder2','D');"
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
			<!-- DIV THIRD ENDS - SORTING OPTIONS -->
			
			<!-- DIV FOURTH BEGINS - ADVANCE FILTERS -->
			<div id="fourth">
			<table cellspacing="0" cellpadding="0" class="formbg" width="100%"
				style="border-collapse: collapse;" border="0">
				<tr>
					<td colspan="7"><strong> <label
						id="selectAdvanceFilter" ondblclick="callShowDiv(this);"
						name="selectAdvanceFilter">Select appropriate advance
					filter option for report generation</label> </strong></td>
				</tr>
				<!-- TRAVEL TYPE -->
				<tr>
					<td width="20%" colspan="1"><label class="set"
						name="tms.traveltype" id="tms.traveltype"
						ondblclick="callShowDiv(this);"><%=label.get("tms.traveltype")%></label>:</td>
					<td colspan="6"><s:select name='trvlType' list="trvlTypeMap"
						theme="simple" headerValue="--Select--" headerKey="" id='trvlType' />
					</td>
				</tr>				
				<!-- TRAVEL PURPOSE -->
				<tr>
					<td width="20%" colspan="1"><label class="set"
						name="tms.purpose" id="tms.purpose"
						ondblclick="callShowDiv(this);"><%=label.get("tms.purpose")%></label>:</td>
					<td colspan="6"><s:select name='trvlPurpose' list="trvlPurposeMap"
						theme="simple" headerValue="--Select--" headerKey="" id='trvlPurpose' />
					</td>
				</tr>								
				<!-- TRAVEL START DATE -->
				<tr>
					<td colspan="1" height="22" width="20%"><label class="set"
						name="tms.startdate" id="tms.startdate"
						ondblclick="callShowDiv(this);"><%=label.get("tms.startdate")%></label>:</td>
					<td colspan="1" align="left" width="25%"><s:select
						theme="simple" name="startDateSelect" value="%{startDateSelect}"
						list="#{'':'--Select--','ON':'On','BF':'Before','AF':'After','OB':'On before',
						'OA':'On after','BN':'Between'}"  cssStyle="width: 80px" 
						onchange="callToDateDispOnClick(this,'3','start' );" />
						<s:textfield size="9"
						onkeypress="return numbersWithHiphen();" name="accntRepBean.startFromDate"
						onblur="setText('paraFrm_accntRepBean_startFromDate','dd-mm-yyyy')"
						onfocus="clearText('paraFrm_accntRepBean_startFromDate','dd-mm-yyyy')"
						maxlength="10" />
						<s:a
						href="javascript:NewCal('paraFrm_accntRepBean_startFromDate','DDMMYYYY');">
						<img class="iconImage" src="../pages/images/recruitment/Date.gif"
							width="16" height="16" border="0" align="absmiddle" />
					</s:a></td>
					<td colspan="4" width="55%">
					<table cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td colspan="1" id="toDateDiv3" width="15%" align="center"><strong>AND
							</strong></td>
							<td width="10%" colspan="1" id="toDateDivLabel3"
								height="22"><s:textfield size="9"
								onkeypress="return numbersWithHiphen();" name="accntRepBean.startToDate"
								onblur="setText('paraFrm_accntRepBean_startToDate','dd-mm-yyyy')"
								onfocus="clearText('paraFrm_accntRepBean_startToDate','dd-mm-yyyy')"
								maxlength="10" /></td>
							<td id="toDatePicker3">&nbsp;<s:a
								href="javascript:NewCal('paraFrm_accntRepBean_startToDate','DDMMYYYY');">
								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" />
							</s:a></td>
						</tr>
					</table>
					</td>
				</tr>												
				<!-- TRAVEL END DATE -->
				<tr>
					<td colspan="1" height="22" width="20%"><label class="set"
						name="tms.enddate" id="tms.enddate"
						ondblclick="callShowDiv(this);"><%=label.get("tms.enddate")%></label>:</td>
					<td colspan="1" align="left" width="25%"><s:select
						theme="simple" name="endDateSelect" value="%{endDateSelect}"
						list="#{'':'--Select--','ON':'On','BF':'Before','AF':'After','OB':'On before',
						'OA':'On after','BN':'Between'}"  cssStyle="width: 80px" 
						onchange="callToDateDispOnClick(this,'4','end' );" />
						<s:textfield size="9"
						onkeypress="return numbersWithHiphen();" name="accntRepBean.endFromDate"
						onblur="setText('paraFrm_accntRepBean_endFromDate','dd-mm-yyyy')"
						onfocus="clearText('paraFrm_accntRepBean_endFromDate','dd-mm-yyyy')"
						maxlength="10" />
						<s:a
						href="javascript:NewCal('paraFrm_accntRepBean_endFromDate','DDMMYYYY');">
						<img class="iconImage" src="../pages/images/recruitment/Date.gif"
							width="16" height="16" border="0" align="absmiddle" />
					</s:a></td>
					<td colspan="4" width="55%">
					<table cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td colspan="1" id="toDateDiv4" width="15%" align="center"><strong>AND
							</strong></td>
							<td width="10%" colspan="1" id="toDateDivLabel4"
								height="22"><s:textfield size="9"
								onkeypress="return numbersWithHiphen();" name="accntRepBean.endToDate"
								onblur="setText('paraFrm_accntRepBean_endToDate','dd-mm-yyyy')"
								onfocus="clearText('paraFrm_accntRepBean_endToDate','dd-mm-yyyy')"
								maxlength="10" /></td>
							<td id="toDatePicker4">&nbsp;<s:a
								href="javascript:NewCal('paraFrm_accntRepBean_endToDate','DDMMYYYY');">
								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" />
							</s:a></td>
						</tr>
					</table>
					</td>
				</tr>																
			</table>
			</div>
			<!-- DIV FOURTH ENDS - ADVANCE FILTERS -->
			</td>
		</tr>
		<!-- DISPLAY OPTIONS BEGINS -->
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
			<td width="100%">
				<input type="button" class="token" theme="simple" value="  Generate Report"
						onclick=" return generateReport();" /> 
				<input type="button"
						class="reset" theme="simple" value=" Reset" onclick="resetForm()" /> 
				<input name="button" type="button" value="Save report criteria"
						class="save" onclick="saveReport()" /> 
				<input name="button" type="button" value="  Search"
						class="search" onclick="searchReport()" />
			</td>
		</tr>
		<!-- BUTTON PANEL ENDS -->					
	</table>
</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<script type="text/javascript">

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
		showRadios();
		showTab();
	}
	function showRadios() {
		var name = ['travel','accom','local'];
		for(i=0;i<3;i++) {
		//alert("value -- "+document.getElementById('paraFrm_accntRepBean_'+name[i]+'Check').checked);
			if(document.getElementById('paraFrm_accntRepBean_'+name[i]+'Check').checked) {
				document.getElementById(name[i]+'Div').style.display='';
			}
		}
	}
 	function callAllDisable(){
  		for(idNo=3;idNo <=4;idNo++){ 
   			document.getElementById('toDateDiv'+idNo).style.display='none'; 
     		document.getElementById('toDateDivLabel'+idNo).style.display='none';     
	  		document.getElementById('toDatePicker'+idNo).style.display='none';  
	  	 }
	   	for(idNo=1;idNo <=2;idNo++){ 
   			document.getElementById('betweenOn'+idNo).style.display='none'; 
	  	}
	  	if(document.getElementById("paraFrm_startDateSelect").value=="BN"){
	  		document.getElementById('toDateDiv3').style.display=''; 
   			document.getElementById('toDateDivLabel3').style.display='';  
  			document.getElementById('toDatePicker3').style.display='';
	  	}
	  	if(document.getElementById("paraFrm_endDateSelect").value=="BN"){
	  		document.getElementById('toDateDiv4').style.display=''; 
   			document.getElementById('toDateDivLabel4').style.display='';  
  			document.getElementById('toDatePicker4').style.display='';
	  	}
	  	if(document.getElementById("paraFrm_advAmtSelect").value=="BN"){
	  		document.getElementById('betweenOn1').style.display='';
	  	}
	  	if(document.getElementById("paraFrm_claimsetSelect").value=="BN"){
	  		document.getElementById('betweenOn2').style.display='';
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
	function callBetween(obj,idNo)
	{
		var dateFilter= obj.value;		
   		if(dateFilter=="BN"){
			document.getElementById('betweenOn'+idNo).style.display=''; 
  		}else {
  			document.getElementById('betweenOn'+idNo).style.display='none'; 
   		} 
 	}
	function callToDateDispOnClick(obj,idNo,idName)
 	{
   		try{
   			var dateFilter= obj.value;
 			//alert(dateFilter);
   			document.getElementById('paraFrm_accntRepBean_'+idName+'ToDate').value="";  
   			if(dateFilter=="BN"){
     			document.getElementById('toDateDiv'+idNo).style.display=''; 
     			document.getElementById('toDateDivLabel'+idNo).style.display='';  
	  			document.getElementById('toDatePicker'+idNo).style.display='';
 
      			setText('paraFrm_accntRepBean_'+idName+'ToDate',"dd-mm-yyyy");
   			}else {
      			setText('paraFrm_accntRepBean_'+idName+'ToDate',"dd-mm-yyyy");
     			document.getElementById('toDateDiv'+idNo).style.display='none'; 
     			document.getElementById('toDateDivLabel'+idNo).style.display='none';     
  				document.getElementById('toDatePicker'+idNo).style.display='none';  
   			} 
   		}catch(e){
   			alert(e);
   		}
   	}
   	
	function listbox_move(listID, direction) 
	{
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
		}catch(e){
			alert(e);
		}
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
    
    function callOrder(sortName,id){
    	if(id=="A"){
    		document.getElementById('paraFrm_'+sortName+'Dsc').value="";
    		document.getElementById('paraFrm_'+sortName+'Asc').value="checked";
    	}
    	if(id=="D"){
    		document.getElementById('paraFrm_'+sortName+'Asc').value="";
    		document.getElementById('paraFrm_'+sortName+'Dsc').value="checked";
    	}
    } 	
	function resetForm()
	{
		document.getElementById('paraFrm_accntRepBean_reportId').value='';
		document.getElementById('paraFrm_accntRepBean_reportTitle').value='';
		document.getElementById('paraFrm_settingName').value='';
			
		document.getElementById('paraFrm').target='main';
		document.getElementById('paraFrm').action="TravelAccntReport_reset.action";
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
		document.getElementById('paraFrm').action="TravelAccntReport_saveReport.action";
		document.getElementById('paraFrm').submit();
	}	
	
	function searchReport() {
		callsF9(500,325,'TravelAccntReport_searchSavedReport.action');
	}	
	function generateReport()
	{
		var expType = document.getElementById('paraFrm_accntRepBean_typeExp').value;
		if(expType==''){
			alert("Please select "+document.getElementById('tms.typeofexpense').innerHTML.toLowerCase());
			return false;
		}
	
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
			
			//Filter
			//Advance Amount - adAmt
			var adAmtFrom = document.getElementById('paraFrm_accntRepBean_advAmtFrom').value;
			var adAmtTo = document.getElementById('paraFrm_accntRepBean_advAmtTo').value;
			if(document.getElementById('paraFrm_advAmtSelect').value!=""){
				if(adAmtFrom=="" || adAmtFrom=="."){
					alert("Please enter "+document.getElementById('tms.advanceamount').innerHTML);
					return false;
				}
				if(document.getElementById('paraFrm_advAmtSelect').value=="BN") {
					if(adAmtTo=="" || adAmtTo=="."){
						alert("Please enter "+document.getElementById('tms.advanceamount').innerHTML);
						return false;
					}
					var v1 = parseFloat(document.getElementById('paraFrm_accntRepBean_advAmtFrom').value);
					var v2 = parseFloat(document.getElementById('paraFrm_accntRepBean_advAmtTo').value);
					if(v1>v2)
					{
						alert(document.getElementById('tms.advanceamount').innerHTML+" from value should less than to value");
						document.getElementById('paraFrm_accntRepBean_advAmtTo').focus();
						return false;
					}
				}
			} //End of Advace Amount if
			
			//Advance Claim Settlement - claim
			var claimFrom = document.getElementById('paraFrm_accntRepBean_claimsetFrom').value;
			var claimTo = document.getElementById('paraFrm_accntRepBean_claimsetTo').value;
			if(document.getElementById('paraFrm_claimsetSelect').value!="") {
				if(claimFrom=="" || claimFrom=="."){
					alert("Please enter "+document.getElementById('tms.advclaimsetment').innerHTML);
					return false;
				}
				if(document.getElementById('paraFrm_claimsetSelect').value=="BN") {
					if(claimTo=="" || claimTo=="."){
						alert("Please enter "+document.getElementById('tms.advclaimsetment').innerHTML);
						return false;
					}
					var v1 = parseFloat(document.getElementById('paraFrm_accntRepBean_claimsetFrom').value);
					var v2 = parseFloat(document.getElementById('paraFrm_accntRepBean_claimsetTo').value);
					if(v1>v2)
					{
						alert(document.getElementById('tms.advclaimsetment').innerHTML+" from value should less than to value");
						document.getElementById('paraFrm_accntRepBean_claimsetTo').focus();
						return false;
					}
				}
			}//End of Advace Amount if
			
			//Advance Filter
			var startFromDate = document.getElementById('paraFrm_accntRepBean_startFromDate').value;
			var startToDate = document.getElementById('paraFrm_accntRepBean_startToDate').value;
			var endFromDate = document.getElementById('paraFrm_accntRepBean_endFromDate').value;
			var endToDate = document.getElementById('paraFrm_accntRepBean_endToDate').value;
			
			if(document.getElementById("paraFrm_startDateSelect").value!=""){
				//alert(document.getElementById("paraFrm_appliFromDate").value);
				if(startFromDate=="" || startFromDate=="dd-mm-yyyy"){
					alert("Please enter "+document.getElementById('tms.startdate').innerHTML);
					return false;
				}
				if(document.getElementById("paraFrm_startDateSelect").value=="BN"){
					if(startToDate==""||startToDate=="dd-mm-yyyy"){
						alert("Please enter "+document.getElementById('tms.startdate').innerHTML);
						return false;
					}
				}
				
				if(startFromDate!=""||startFromDate!="dd-mm-yyyy" ){
				  	var fld=['paraFrm_accntRepBean_startFromDate'];
				  	var lbl=['tms.startdate'];
			   		var chkfrmDate=validateDate(fld,lbl);
			   		if(!chkfrmDate) {
				    	return false;
				   	}
	  			}
	  	
	  			if(startToDate!="dd-mm-yyyy" ){
			  		var fld=['paraFrm_accntRepBean_startToDate'];
			  		var lbl=['tms.startdate'];
		   			var chktoDate=validateDate(fld,lbl);
		   			if(!chktoDate) {
			    		return false;
			   		}
			   	}
			}	//End of Start Date - IF		
			
			if(document.getElementById("paraFrm_endDateSelect").value!=""){
				//alert(document.getElementById("paraFrm_attFromDate").value);
				if(endFromDate=="" || endFromDate=="dd-mm-yyyy"){
					alert("Please enter "+document.getElementById('tms.enddate').innerHTML);
					return false;
				}
				if(document.getElementById("paraFrm_endDateSelect").value=="BN"){
					if(endToDate==""||endToDate=="dd-mm-yyyy"){
						alert("Please enter "+document.getElementById('tms.enddate').innerHTML);
						return false;
					}
				}
				
				if(endFromDate!=""||endFromDate!="dd-mm-yyyy" ){
				  	var fld=['paraFrm_accntRepBean_endFromDate'];
				  	var lbl=['tms.enddate'];
			   		var chkfrmDate=validateDate(fld,lbl);
			   		if(!chkfrmDate) {
				    	return false;
				   	}
	  			}
	  	
	  			if(endToDate!="dd-mm-yyyy" ){
			  		var fld=['paraFrm_accntRepBean_endToDate'];
			  		var lbl=['tms.enddate'];
		   			var chktoDate=validateDate(fld,lbl);
		   			if(!chktoDate) {
			    		return false;
			   		}
			   	}
			}	//End of Att - IF					
			
		}catch(e){
			alert(e);
		}
  		if(document.getElementById("reportViewV").checked){
  			document.getElementById('paraFrm').target='_self';
	 		document.getElementById('paraFrm').action="TravelAccntReport_viewOnScreen.action";
     		document.getElementById('paraFrm').submit();
		}else{
		  	document.getElementById('paraFrm').target='_blank';
			document.getElementById('paraFrm').action="TravelAccntReport_report.action";
			document.getElementById('paraFrm').submit();
			document.getElementById('paraFrm').target="main"; 
		}		
	}
</script>