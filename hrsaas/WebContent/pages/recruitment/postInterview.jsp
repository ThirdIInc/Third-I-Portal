<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="PostInterview" id="paraFrm" theme="simple">
	<table class="bodyTable" width="100%">
		<tr>
			<td width="100%"  colspan="6" class="pageHeader">Post Interview</td>
		</tr>
		<tr align="center">
			<td colspan="4" width="100%" align="left" valign="middle" class="buttontr">
			<s:submit cssClass="pagebutton"   action="LoanClosure_save" value="  Save  " onclick="return call();" /> 
			<s:submit cssClass="pagebutton"   action="LoanClosure_reset" value="  Reset  "  /> 
			<s:submit cssClass="pagebutton"   action="LoanClosure_delete"  value="  Delete  " onclick="return del();" />
			</td>
		</tr>
		<tr>
			<td width="100%" colspan="4">&nbsp;</td>
		</tr>
			<tr>
			<td width="100%" colspan="4">&nbsp;</td>
		</tr>	
		<tr>
			<td  width="15%" colspan="1">Post Interview Code :</td>
			<td width="20%" colspan="1"><s:textfield  maxLength="5" cssStyle="width:100" size="8" 
			theme="simple" name="transdate"  />
			
			<img src="../pages/images/search.gif" height="18" align="absmiddle" width="18"
			onclick="javascript:callsF9(500,325,'LoanClosure_f9loanNo.action');">
			<s:hidden name="interviewcode"/></td>
		</tr>
		<tr>
			<td width="15%" colspan="1">Select Candidate :</td>
			<td width="60%" colspan="1"><s:textfield name="empname" size="20"
				theme="simple"  />
				<img src="../pages/images/search.gif" height="18" align="absmiddle" width="18"
			onclick="javascript:callsF9(500,325,'LoanClosure_f9loanNo.action');">
			<s:hidden name="candcode"/></td>
			
			
			
		</tr>
		
		<tr>
			<td colspan="1"  width="25%" >Current Salary :</td>
			<td colspan="1"  width="25%"><s:textfield   size="25" 
			theme="simple" name="currentsal"  /></td>
			</tr>
			<tr>
			<td colspan="1"  width="25%" >Expected Salary :</td>
			<td colspan="1"  width="25%"><s:textfield   size="25" 
			theme="simple" name="expectedsal"  />
			</td>
		</tr>
		<tr>
		<td width="25%" colspan="1">Negotiable Salary :</td>
			<td width="25%" colspan="1"><s:textfield   size="25" 
			theme="simple" name="negsal"  />
			</td>
		</tr>
		<tr>
			<td colspan="1"  width="20%" >Applied Date :</td>
			<td colspan="1"  width="10%"><s:textfield  maxLength="5" cssStyle="width:130" size="8" 
						theme="simple" name="loandate"  value="%{loandate}"/><s:a
				href="javascript:NewCal('loandate','DDMMYYYY');">
				<img src="../pages/images/cal.gif" class="iconImage"  height="16" align="absmiddle" width="16">
			</s:a>
			
		</tr>
		
		<tr>
			<td colspan="1"  width="15%" >Expected Join Date :</td>
			<td colspan="1"  width="85%"><s:textfield  maxLength="5" cssStyle="width:130" size="8" 
			theme="simple" name="loandate"  value="%{loandate}"/><s:a
			href="javascript:NewCal('loandate','DDMMYYYY');">
			<img src="../pages/images/cal.gif" class="iconImage"  height="16" align="absmiddle" width="16">
			</s:a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			Offer Valid Till :&nbsp;<s:textfield  maxLength="5" cssStyle="width:130" size="8" 
			theme="simple" name="loandate"  value="%{loandate}"/><s:a
			href="javascript:NewCal('loandate','DDMMYYYY');">
			<img src="../pages/images/cal.gif" class="iconImage"  height="16" align="absmiddle" width="16">
		   	  </s:a>
			</td>
		</tr>
		
		<tr>
					<td colspan="1"  width="15%" >Status :</td>
					<td  colspan="1"  width="85%"><s:select 
						name="mode" headerKey="1" headerValue="------------Select-------------"
						list="#{'CA':'Cash','CH':'Cheque'}"  cssStyle="width:150" onchange="javascript:showText();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Employee Type :&nbsp;<s:select 
						name="mode" headerKey="1" headerValue="------------Select-------------"
						list="#{'CA':'Cash','CH':'Cheque'}"  cssStyle="width:150" onchange="javascript:showText();"/>
					</td>
					
		</tr>
		
		<tr>
			<td colspan="1"  width="15%" >Offer Given Date :</td>
			<td colspan="1"  width="85%"><s:textfield  maxLength="5" cssStyle="width:130" size="8" 
			theme="simple" name="loandate"  value="%{loandate}"/><s:a
			href="javascript:NewCal('loandate','DDMMYYYY');">
			<img src="../pages/images/cal.gif" class="iconImage"  height="16" align="absmiddle" width="16">
			</s:a>
			</td>
		</tr>
		
		<tr>
			<td colspan="1"  width="15%" >Division :</td>
			<td colspan="1"  width="85%"><s:textfield   size="25" 
			theme="simple" name="empName"  value="%{empName}"/>
			<img src="../pages/images/search.gif" height="18" align="absmiddle" width="18"
			onclick="javascript:callsF9(500,325,'LoanClosure_f9loanNo.action');">&nbsp;
			</td>
		</tr>
		
		<tr>
			<td colspan="1"  width="15%" >Branch :</td>
			<td colspan="1"  width="85%"><s:textfield   size="25" 
			theme="simple" name="empName"  value="%{empName}"/>
			<img src="../pages/images/search.gif" height="18" align="absmiddle" width="18"
			onclick="javascript:callsF9(500,325,'LoanClosure_f9loanNo.action');">&nbsp;
			</td>
		</tr>
		
		<tr>
			<td colspan="1"  width="15%" >Department :</td>
			<td colspan="1"  width="85%"><s:textfield   size="25" 
			theme="simple" name="empName"  value="%{empName}"/>
			<img src="../pages/images/search.gif" height="18" align="absmiddle" width="18"
			onclick="javascript:callsF9(500,325,'LoanClosure_f9loanNo.action');">&nbsp;
			</td>
		</tr>
		
		<tr>
			<td colspan="1"  width="15%" >Designation :</td>
			<td colspan="1"  width="85%"><s:textfield   size="25" 
			theme="simple" name="empName"  value="%{empName}"/>
			<img src="../pages/images/search.gif" height="18" align="absmiddle" width="18"
			onclick="javascript:callsF9(500,325,'LoanClosure_f9loanNo.action');">&nbsp;
			</td>
		</tr>
		
		<tr>
			<td colspan="1"  width="15%" >Reporting To :</td>
			<td colspan="1"  width="85%"><s:textfield   size="40" 
			theme="simple" name="empName"  value="%{empName}"/>
			<img src="../pages/images/search.gif" height="18" align="absmiddle" width="18"
			onclick="javascript:callsF9(500,325,'LoanClosure_f9loanNo.action');">&nbsp;
			</td>
		</tr>
		
		<tr>
			<td colspan="1"  width="15%" >Constraints :</td>
			<td colspan="1"  width="85%"><s:textfield   size="100" 
			theme="simple" name="empName"  value="%{empName}"/>
			</td>
		</tr>
		
		<tr>
			<td colspan="1"  width="15%" >Address :</td>
			<td colspan="1"  width="85%"><s:textfield   size="100" 
			theme="simple" name="empName"  value="%{empName}"/>
			</td>
		</tr>
		
		<tr>
			<td colspan="4">&nbsp</td>

		</tr>
				
	</table>
	
	<table class="bodyTable" width="100%">
		
		<tr>
			<td width="20%" class="pageHeader" align="left">Salary Details</td>
		</tr>
		
		<tr>
			<td width="100%" colspan="4"> &nbsp;</td>
		</tr>
		
		<tr>
			<td class="headercell" width="5%">Code</td>
			<td class="headercell" width="25%">Salary Head</td>
			<td class="headercell" width="20%">Salary Head Type</td>
			<td class="headercell" width="25%">Amount(During Probation)</td>
			<td class="headercell" width="25%">Amount(After Probation)</td>
		</tr>
		<tr>
        <td width="5%" class="bodycell"><span style="font-weight: 500">&nbsp;1</span></td>
        <td width="25%" class="bodycell"><span style="font-weight: 500">Basic</span></td>
        <td width="20%" class="bodycell"><span style="font-weight: 400">Monthly</span></td>
        <td width="25%" class="bodycell"><span style="font-weight: 400">2047</span></td>
        <td width="20%" class="bodycell"><span style="font-weight: 400">3480</span></td>            
      </tr>
      <tr>
        <td width="5%" class="bodycell"><span style="font-weight: 500">&nbsp;2</span></td>
        <td width="25%" class="bodycell"><span style="font-weight: 500">Annual Bonus</span></td>
        <td width="20%" class="bodycell"><span style="font-weight: 400">Annual</span></td>
        <td width="25%" class="bodycell"><span style="font-weight: 400">8000</span></td>
        <td width="20%" class="bodycell"><span style="font-weight: 400">10500</span></td>      
      </tr>
		
		<tr>
			<td width="100%" colspan="4"> &nbsp;</td>
		</tr>
				
		</table>
	
	
</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script>
  
</script>