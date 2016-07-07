<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="TravelMisReport" method="post" id="paraFrm">


<s:hidden name="travelId" value="%{travelId}"/>
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" >
        <tr>
          <td colspan="3" valign="bottom" class="txt">&nbsp;</td>
        </tr>
        <tr>
          <td colspan="3" valign="bottom" background="../images/lines.gif" class="txt"><img src="../pages/images/recruitment/lines.gif" width="16" height="1" /></td>
        </tr>
        <tr>
          <td colspan="3" valign="bottom" class="txt"><img src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
        </tr>
        <tr>
          <td valign="bottom" class="txt"><strong class="formhead"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
          <td width="93%" class="txt"><strong class="formhead">Travel Schedule Report </strong></td>
          <td width="3%" valign="top" class="txt"><div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
        </tr>
        <tr>
          <td height="5" colspan="3"><img src="../pages/images/recruitment/space.gif" width="5" height="7" /></td>
        </tr>
        <tr>
          <td colspan="3"><table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="70%" colspan="3">
						<s:if test="%{viewFlag}">
							<input type="button" class="token" onclick="return callReportMis();"	value="    Show Report" />
						</s:if>
					<s:submit cssClass="reset" action="TravelScheduleReport_reset"	theme="simple" value="    Reset"  />
					</td>
					<td width="30%">
					<div align="right"><span class="style2"></span> </div>
					</td>
				</tr>
			</table>
			<label></label></td>
		</tr>
		<tr>
			<td colspan="3"><img src="../pages/images/recruitment/space.gif"
				width="5" height="4" /></td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td colspan="3">
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="2">
						
						<tr> 
						 <td colspan="6"><b>Travel Applications during</b></td>
						</tr>
						<tr>
							<td width="15%" class="formtext">From Date:</td>
							<td width="25%">
							<s:textfield name="appFromDate" size="20" onblur="return validateDate('paraFrm_appFromDate','From Date');" onkeypress="return numbersWithHiphen();" theme="simple" value="%{frmDate}" maxlength="10" />
								<s:a href="javascript:NewCal('paraFrm_appFromDate','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif"  class="iconImage" height="16" align="absmiddle"
								width="16"> </s:a>
							</td>
							<td width="15%" class="formtext">To Date:<s:hidden name="today"/></td>
							<td width="25%"><s:textfield name="appToDate" size="20"
								onblur="return validateDate('paraFrm_appToDate','To Date');" onkeypress="return numbersWithHiphen();" theme="simple" value="%{toDate}" maxlength="10" />
								<s:a href="javascript:NewCal('paraFrm_appToDate','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif"  class="iconImage" height="16" align="absmiddle"
								width="16"> </s:a>
							</td>	
							<td>&nbsp;</td>
						 </tr>
						 <tr>
						 <td colspan="6"><b>Journey start during</b></td>
						</tr>
						 <tr>
							<td width="15%" class="formtext">From Date:</td>
							<td width="25%"><s:textfield name="journeyFromDate" size="20"
								onblur="return validateDate('paraFrm_journeyFromDate','From Date');" onkeypress="return numbersWithHiphen();" theme="simple" value="%{frmDate}" maxlength="10" />
								<s:a href="javascript:NewCal('paraFrm_journeyFromDate','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif"  class="iconImage" height="16" align="absmiddle"
								width="16"> </s:a>
							</td>
							<td width="15%" class="formtext">To Date:<s:hidden name="today"/></td>
							<td width="25%"><s:textfield name="journeyToDate" size="20"
								onblur="return validateDate('paraFrm_journeyToDate','To Date');" onkeypress="return numbersWithHiphen();" theme="simple" value="%{toDate}" maxlength="10" />
								<s:a href="javascript:NewCal('paraFrm_journeyToDate','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif"  class="iconImage" height="16" align="absmiddle"
								width="16"> </s:a>
							</td>	
							<td>&nbsp;</td>
						 </tr>
							
						 <tr>
							<td width="15%" colspan="1">Branch :</td>
							<td width="25%" colspan="1">
								<s:textfield size="20" theme="simple" name="branch" value="%{branch}" readonly="true"/>
							<img
							src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle"
							width="16"
							onclick="javascript:callsF9(500,325,'TravelScheduleReport_f9Branch.action');">
							<s:hidden name="branchId" value="%{branchId}"/>
							</td>						
							<td width="10%" colspan="1">Department :</td>
							<td  width="30%" colspan="3">
							<s:textfield
							 size="20" theme="simple" name="dept" value="%{dept}" readonly="true" />
							<img
							src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle"
							width="16"
							onclick="javascript:callsF9(500,325,'TravelScheduleReport_f9Dept.action');">
							<s:hidden name="deptId" value="%{deptId}"/>
							</td>
						</tr>  
                 
           				<tr>
							<td width="15%" colspan="1">Designation :</td>
							<td width="25%" colspan="1" >
							 <s:textfield 
							 size="20" theme="simple" name="desg" readonly="true"
							value="%{desg}" />
							<img
							src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle"
							width="16"
							onclick="javascript:callsF9(500,325,'TravelScheduleReport_f9Desg.action');">
							<s:hidden name="desgId" value="%{desgId}"/></td>
							<td width="15%" colspan="1" >Division :</td>
							<td width="35%" colspan="3" >
						 		<s:textfield size="20" theme="simple" name="division" readonly="true"	value="%{division}" />
								<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="16"
								onclick="javascript:callsF9(500,325,'TravelScheduleReport_f9Division.action');">
								<s:hidden name="divisionId" value="%{divisionId}"/> 
							</td>				
		 				</tr>
		 				
		 				
		      			<tr>
							<td width="15%" colspan="1" class="formtext">
							Select Employee  :<s:hidden name="empId" value="%{empId}" /></td>
							<td width="85%" colspan="4">
							   <s:textfield name="empToken"	size="10" value="%{empToken}" theme="simple" readonly="true" />
							   <s:textfield	name="empName" size="75" value="%{empName}" theme="simple"	readonly="true" /> 
								<img src="../pages/images/recruitment/search2.gif" height="16"
										align="absmiddle" width="16" 
										onclick="javascript:callsF9(500,325,'TravelScheduleReport_f9Employee.action');">
							</td>
						</tr>					
						
						<tr align="left">
							<td width="25%" colspan="1">Report Type:</td>
							<td><s:select name="rptType" headerKey="Pdf" headerValue="Pdf" list="#{'Xls':'Xls', 'Txt':'Text'}" theme="simple"/></td>
						</tr>
						
					</table>
					</td>
				</tr>
	

			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3"><img src="../pages/images/recruitment/space.gif"
				width="5" height="4" /></td>
		</tr>
	
		<tr>
			<td colspan="3"><img src="../pages/images/recruitment/space.gif"
				width="5" height="4" /></td>
		</tr>
			</table>
	<br />


	<label></label></td></tr></table>
</s:form>
<script type="text/javascript">

function callReportMis(){


  	if(validateDate('paraFrm_appFromDate','From Date')==false){
  	 return false; 
  	}if(validateDate('paraFrm_appToDate','To Date')==false){
  	 return false;
  	}if(validateDate('paraFrm_journeyFromDate','From Date')==false){
  	 return false;
  	}if(validateDate('paraFrm_journeyToDate','To Date')==false){
  	 return false;
  	}
	  document.getElementById('paraFrm').target="_blank";
      document.getElementById('paraFrm').action="TravelScheduleReport_report.action";
	  document.getElementById('paraFrm').submit();  
      document.getElementById('paraFrm').target="main";
  
   	
	
}
  	function autoDate () {
var tDay = new Date();
var tMonth = tDay.getMonth()+1;
var tDate = tDay.getDate();
if ( tMonth < 10) tMonth = "0"+tMonth;
if ( tDate < 10) tDate = "0"+tDate;
if(document.getElementById('paraFrm_vCode').value=="")
document.getElementById("paraFrm_today").value = tDate+"-"+tMonth+"-"+tDay.getFullYear();

}
autoDate();
</script>
