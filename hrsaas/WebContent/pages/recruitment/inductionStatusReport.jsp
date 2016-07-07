<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="InductionStatusReport" method="post" id="paraFrm" theme="simple">
 <s:hidden name="experience"/>
 <s:hidden name="postingDate"/>
 <s:hidden name="postingDate"/>
  <s:hidden name="convertemp"/>
 <s:hidden name="searchstatus"/>
 <s:hidden name="position"/>
 <s:hidden name="testresult"/>
 <s:hidden name="candscreeningstatus"/>
 <s:hidden name="intvstatus"/>
 <s:hidden name="intvround"/>
 <s:hidden name="makeoffer"/>
 <s:hidden name="offerstatus"/>
 <s:hidden name="appointstatus"/>
 
	<table width="100%" border="0" align="right" cellpadding="2" cellspacing="2" class="formbg">
	       <tr>	      
				<td>			
				<table width="105%" border="0" align="center" cellpadding="2"
					cellspacing="2" class="formbg">
					<tr>
						<td valign="bottom" class="txt"><strong class="text_head"><img
							src="../pages/images/recruitment/review_shared.gif" width="25"
							height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="text_head">Induction Status Report</strong></td>
					</tr>
				</table>
				</td>			
		  </tr>
	        <tr>
	          <td colspan="3">
	          <table width="105%" border="0" cellpadding="2" cellspacing="2">
									<tr>
									<td width="78%" colspan="3"><s:if test="%{viewFlag}">
									
									<s:submit value="Show Report" cssClass="token" onclick="return callReportCandidateStatus();" action="CandidateStatusReport_report"/>
									
									</s:if>
									
									<s:submit cssClass="reset"  action="CandidateStatusReport_reset"
										theme="simple" value="    Reset"  />
									</td>
										<td width="22%" align="right">
										</td>
									</tr>
							</table>
						</td>
					</tr>
				<tr>
			<td colspan="2">
			<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
						
							<tr>
								<td width="25%" class="formtext"> <label  class = "set"  id="frmdate"  name="frmdate" ondblclick="callShowDiv(this);"><%=label.get("frmdate")%></label>:</td>
								<td width="25%"><s:textfield name="frmDate" size="25" onkeypress="return numbersWithHiphen();" theme="simple" maxlength="10" />
									<s:a href="javascript:NewCal('paraFrm_frmDate','DDMMYYYY');"><img src="../pages/images/recruitment/Date.gif"  class="iconImage" 
									height="16" align="absmiddle" width="16"> </s:a>
								</td>
								
								
								<td width="25%" class="formtext"> <label  class = "set"  id="todate"  name="todate" ondblclick="callShowDiv(this);"><%=label.get("todate")%></label>:</td>
								<td width="25%"><s:textfield name="toDate" size="25" onkeypress="return numbersWithHiphen();" theme="simple" maxlength="10" />
									<s:a href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');"><img src="../pages/images/recruitment/Date.gif"  class="iconImage" 
									height="16" align="absmiddle" width="16"> </s:a>
								</td>
							</tr>
							
							<tr>								
								<td width="25%" colspan="1" class="formtext"><label  class = "set"  id="reqname1"  name="reqname1" ondblclick="callShowDiv(this);"><%=label.get("reqname")%></label> :<s:hidden name="reqCode" /></td>
								<td width="25%" colspan="1"><s:textfield name="reqname" size="25" theme="simple" readonly="true" /> 
								<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" 
									onclick="javascript:callsF9(500,325,'CandidateStatusReport_f9actionReqName.action');"></td>							
								<td></td><td></td>
							</tr>
						
						
							<tr>						
								<td width="25%" colspan="1" class="formtext"><label  class = "set"  id="candidate"  name="candidate" ondblclick="callShowDiv(this);"><%=label.get("candidate")%></label> :<s:hidden name="candidateCode" /></td>
								<td width="25%" colspan="1"><s:textfield name="candidateName" size="25" theme="simple" readonly="true" /> 
								<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" 
								onclick="javascript:callsF9(500,325,'CandidateStatusReport_f9actionCandidate.action');"></td>						
								<td></td><td></td>
						  	</tr>
							
							<tr>						
								<td width="25%" colspan="1" class="formtext"><label  class = "set"  id="position"  name="position" ondblclick="callShowDiv(this);"><%=label.get("position")%></label> :<s:hidden name="rankId" /></td>
								<td width="25%" colspan="1"><s:textfield name="rankName" size="25" theme="simple" readonly="true" /> 
								<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" 
								onclick="javascript:callsF9(500,325,'CandidateStatusReport_f9actionPosition.action');"></td>						
								<td></td><td></td>
						  	</tr>
					</table>
					
					</td>
				</tr>					
					<tr>
			          <td colspan="3">
					          <table width="105%" border="0" cellpadding="2" cellspacing="2">
									<tr>
									<td width="78%" colspan="3"><s:if test="%{viewFlag}">
									
									<s:submit value="Show Report" cssClass="token" onclick="return callReportCandidateStatus();" action="CandidateStatusReport_report"/>
									
									</s:if>
									
									<s:submit cssClass="reset"  action="CandidateStatusReport_reset"
										theme="simple" value="    Reset"  />
									</td>
										<td width="22%" align="right">
										</td>
									</tr>
							</table>
						</td>					
					</tr>					
			</table>
</s:form>

<script type="text/javascript">

function callReportCandidateStatus(){ 	
	
	var frmDate=document.getElementById("paraFrm_frmDate").value;
  	var toDate=document.getElementById("paraFrm_toDate").value;
  	
    if((frmDate!="") || (toDate!="")) {
    	if(!validateDate('paraFrm_frmDate','From Date')){
		             return false;
		     }
		if(!validateDate('paraFrm_toDate','To Date')){
             return false;	 
             }     
	}
	  	
  	 		
}
</script>
