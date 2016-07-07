<%@ taglib prefix="s" uri="/struts-tags" %>
<s:form action = "LoanDetailsReport" target="main" theme="simple" validate ="true" id ="paraFrm">
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" >
        <tr>
          <td colspan="3" valign="bottom" class="txt">&nbsp;</td>
        </tr>
        
        <tr>
          <td colspan="3" valign="bottom" background="../pages/images/recruitment/lines.gif" class="txt"><img src="../pages/images/recruitment/lines.gif" width="16" height="1" /></td>
        </tr>
        
        <tr>
          <td colspan="3" valign="bottom" class="txt"><img src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
        </tr>
        
        <tr>
          <td valign="bottom" class="txt"><strong class="formhead"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
          <td width="93%" class="txt"><strong class="formhead">Loan MIS Report   </strong></td>
          <td width="3%" valign="top" class="txt"><div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
        </tr>
        
        <tr>
          <td height="5" colspan="3"><img src="../pages/images/recruitment/space.gif" width="5" height="7" /></td>
        </tr>
        
        <tr>
          <td colspan="3">
          	<table width="100%" border="0" cellpadding="0" cellspacing="0">
            	<tr>
                	 <td  width="78%">
                	<!-- class="search"  --> 
                		<s:if test="%{viewFlag}">
                	 		<input type="button" class="token" onclick="return validateForm();" value="    Show Report " />
                	 	</s:if>
						<s:submit cssClass="reset" action="ConferenceMisReport_reset" theme="simple" value="    Reset  " />
					</td>
              		<td width="22%"><div align="right"></div></td>
            	</tr>
          	</table><label></label>
          </td>
        </tr>
        
       
		<tr>
          <td colspan="3"><img src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
        </tr>
        
        <tr>
          <td colspan="3">
          	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
              <tr>
                <td>
                	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="2">
		                  <tr>
		                    <td colspan="5" class="formhead"><strong class="forminnerhead">Select any/all search criteria to generate MIS report  </strong></td>
		                  </tr>
	                  
		                  <tr>
								<td width="18%" colspan="1" class="formtext">Select Employee :
									<s:hidden name="employeeCode"/></td>
								<td width="82%" colspan="3" nowrap="nowrap"><s:textfield name="employeeToken" size="10" theme="simple" readonly="true" />
									<s:textfield name="employeeName" size="50" theme="simple" readonly="true" /> 
										<img src="../pages/images/recruitment/search2.gif" height="18" align="absmiddle" width="18" 
											onclick="javascript:callsF9(500,325,'ConferenceMisReport_f9actionEmployeeName.action');">
								</td>
						 	</tr>
						 
							<tr>
								<td width="18%" class="formtext">From Date :</td>
								<td width="42%" nowrap="nowrap"><s:textfield name="fromDate" size="10" onkeypress="return numbersWithHiphen();" 
									theme="simple" maxlength="10" />
									<s:a href="javascript:NewCal('paraFrm_fromDate','DDMMYYYY');">
										<img src="../pages/images/Date.gif"  class="iconImage" height="16" align="absmiddle" width="16"> </s:a>
								</td>
								<td width="10%" class="formtext">To Date :</td>
								<td width="30%" nowrap="nowrap"><s:textfield name="toDate" size="10" onkeypress="return numbersWithHiphen();" 
									theme="simple" maxlength="10" />
									<s:a href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
										<img src="../pages/images/Date.gif"  class="iconImage" height="16" align="absmiddle" width="16"> </s:a>
								</td>		
							</tr>
							
							<tr>
								<td width="18%" colspan="1" class="formtext">Status :
								<td width="42%" colspan="3">
									<s:select name="status" headerKey="" headerValue="Select" theme="simple" list="#{'P':'Pending', 'A':'Approved', 'R':'Rejected'}"/>
								</td>
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
	<label></label>
</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script type="text/javascript">

	function validateForm(){
		var employeeName = document.getElementById("paraFrm_employeeName").value;
		var fromDate	 = document.getElementById("paraFrm_fromDate").value;
		var toDate		 = document.getElementById("paraFrm_toDate").value;
		var status		 = document.getElementById("paraFrm_status").value;
		
		if(employeeName == "" && fromDate == "" && toDate == "" && status == ""){
			alert("Please select any/all search criteria to generate MIS report");
			document.getElementById().focus("paraFrm_employeeName");
			return false;
		}if(!fromDate == ""){
			if(!validateDate("paraFrm_fromDate", "From Date"))return false;
		}if(!toDate == ""){
			if(!validateDate("paraFrm_toDate", "To Date"))return false;
		}if(!fromDate == "" && !toDate == ""){
			if(!dateDifferenceEqual(fromDate, toDate, "paraFrm_toDate", "From Date", "To Date"))return false;
		}
		callReport('ConferenceMisReport_report.action')
	}

</script>
