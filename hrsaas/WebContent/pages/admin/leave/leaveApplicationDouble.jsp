	<%@ taglib uri="/struts-tags" prefix="s"%>	
	 <script type="text/javascript" src="Ajax.js"></script>

<s:form action="LeaveApplication" method="post" name="LeaveForm"  id="paraFrm" theme="simple" target="main">
	<table class="tableBody" width="90%">
		<tr>
			<td class="pageHeader" colspan="4" ><center>Leave Application Form</center></td>
		</tr>
		<tr><td>&nbsp;</td></tr>
		<s:if test="%{leaveApplication.isForApprove}">
			<s:hidden 	theme="simple"name="leaveApplication.leaveCode" />	
		</s:if>
		<s:else>
		<tr><td colspan="4" >Select Leave Application 
				<s:hidden 	theme="simple"name="leaveApplication.leaveCode" />			
				<img src="../pages/images/search.gif" class="iconImage" height="16" align="absmiddle"	width="16" onclick="javascript:callsF9(500,325,'LeaveApplication_f9actionLeaveCode.action');">
			</td>
		</tr>
		</s:else>
		
		<tr><td width="20%" >Applicant Name<font color="red" >*</font> </td>
			<td colspan="3"><s:hidden 	theme="simple"	name="leaveApplication.empCode" value="%{leaveApplication.empCode}" />
				<s:hidden name="leaveApplication.isOfficer" />
				<s:if test="%{leaveApplication.generalFlag}">			
				</s:if>
				<s:else>
						<img src="../pages/images/search.gif" class="iconImage" height="16" align="absmiddle"	width="16" onclick="javascript:getEmployee();">
				</s:else>
				  <s:textfield theme="simple"  readonly="true" size="10" name="leaveApplication.tokenNo" />
				  <s:textfield label="%{getText('empName')}" 	theme="simple" size="75"  readonly="true"  name="leaveApplication.empName" />
				
			</td>
			
		</tr>
		<tr>
			<td  >Center Name</td>
			<td>
				<s:textfield label="%{getText('centerNo')}" theme="simple" size="41"	readonly="true"	name="leaveApplication.center" />
			</td>
			<td  >Rank</td>
			<td>
				<s:textfield label="%{getText('department')}" theme="simple"	readonly="true"	size="25"	name="leaveApplication.department" />
			</td>
		</tr>
		<tr>
			<td  >Application Date</td>
			<td colspan="3" >
				<s:textfield label="%{getText('applicationdate')}" 	theme="simple" readonly="true" 	name="leaveApplication.applicationDate" />
			</td>
		</tr>
		<tr><td colspan="4"><hr></td></tr>
		<tr><td colspan="4" >
					
			<table width="100%" colspan="4">
			<s:if test="%{leaveApplication.isForApprove}">
			
			</s:if>
			<s:else>
				<tr>
				 <td  >Leave Type <font color="red" >*</font></td>
				 <td><s:hidden name="leaveApplication.levCode" value="%{leaveApplication.levCode}" theme="simple"  />
				 	<s:hidden name="leaveApplication.leaveId" value="%{leaveApplication.leaveId}" theme="simple"  />
				 	<s:hidden name="leaveApplication.hdlevType" value="%{leaveApplication.hdlevType}" theme="simple"  />
				 <s:textfield name="leaveApplication.levType" readonly="true" theme="simple"  />
				 <img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle" width="18" 
				 onclick="javascript:callsF9(500,325,'LeaveApplication_f9ltypeaction.action');">
			  		</td>  
				<td  >Available Balance</td>
				<td  ><span id="open"><s:textfield name="levOpeningBalance" theme="simple" readonly="true"  /></span></td>
			</tr>
			<tr>
				 <td  >From Date<font color="red" >*</font></td>
				 <td><s:textfield name="leaveFromDtl" theme="simple"  />
				 	<s:a href="javascript:NewCal('leaveFromDtl','DDMMYYYY');"><img src="../pages/images/cal.gif"  class="iconImage" height="16" align="absmiddle" width="16" ></s:a>				
				 </td>
				  <td  >To Date<font color="red" >*</font></td>
				<td  ><s:textfield name="leaveToDtl" theme="simple"  onblur="retrieveURL('LeaveApplication_calculate.action?','LeaveForm');" />
						<s:a href="javascript:NewCal('leaveToDtl','DDMMYYYY');"><img src="../pages/images/cal.gif"  class="iconImage" height="16" onclick="callDate()" align="absmiddle" width="16" ></s:a>				
				</td>
			</tr>				
			<tr>
				 <td  >Closing balance</td>
				 <td><span id="close"><s:textfield name="levClosingBalance" theme="simple" readonly="true"  /></span></td>
				  <td  >Leave days</td>
				<td><span id="balance"><s:textfield theme="simple" name="leaveApplication.leaveTotalDays" readonly="true" /></span>
				</td>
			</tr>
			<s:if test="%{leaveApplication.insertFlag}">
					<tr>			
				  		<td width="100%" align="center" colspan="4"><s:submit cssClass="pagebutton" action="LeaveApplication_addLdtl" theme="simple"  onclick="return callAdd();" value=" Add "/>
				  		</td>
				  	</tr>
				 </s:if>
			</s:else>				
				</table>
			</td></tr>
		<s:if test="%{leaveApplication.att}">
		<tr><td colspan="4">
				<table width="100%" colspan="6" >				
					<tr>
						<td class="headerCell">Leave Type</td>
						<td class="headerCell">From Date</td>
						<td class="headerCell">To Date</td>
						<td class="headerCell">Leave Days</td>
						<s:if test="%{leaveApplication.isForApprove}">	
						</s:if>
						<s:else>
						<td class="headerCell" >&nbsp;</td>
						</s:else>
					</tr>
						<s:iterator  value="att" status="stat">
						<%int i=0; %>
							<tr>
						
							    <s:hidden  name="slevCode" value="%{slevCode}" />
							    <td><s:property  value="slevType"  /></td>
								<td><s:property  value="sleaveFromDtl"   /></td>
								<td><s:property  value="sleaveToDtl"  /></td>
								<td><center><s:property  value="slevClosingBalance"  /></center></td>
								<s:if test="%{leaveApplication.isForApprove}">	
								</s:if>
								<s:else>
									<td><input type="button" class="pagebutton" onclick="callForEdit('<s:property value="leaveId" />')"  value="Edit" />
										<input type="button" class="pagebutton" onclick="callForRemove('<s:property value="leaveId" />','<s:property  value="slevClosingBalance"  />','<s:property  value="slevCode" />')"  value="Remove" />
									</td>
								</s:else>
							</tr>
						<%i++; %>    
					</s:iterator>				
					
				</table>
			</td>
		</tr>
		</s:if>
		<tr><td colspan="4"><hr></td></tr>
		<tr><td  >Prefix</td>
			<td>
				<s:textfield label="%{getText('prefix')}" theme="simple" size="30"	name="leaveApplication.prefix" />
			</td>
			<td  >Suffix</td>
			<td>
				<s:textfield label="%{getText('suffix')}" theme="simple" size="30" name="leaveApplication.suffix" />
			</td>
		</tr>
		<tr><td  >Reasons\<br>Nearest Naval Unit </td>
			<td>
				<s:textarea label="%{getText('medicalCert')}" theme="simple" cols="33"	rows="4" name="leaveApplication.medicalCert" />
			</td>
			<td  >Leave Address,<br>Phone No.</td>
			<td>
				<s:textarea label="%{getText('contact_details')}" 	name="leaveApplication.contactDetails" theme="simple"	cols="30"	rows="4" />
				<s:if test="%{leaveApplication.isForApprove}">
				</s:if>
				<s:else>
				<img src="../pages/images/search.gif" class="iconImage" height="16" align="absmiddle"	width="16" onclick="javascript:callsF9(500,325,'LeaveApplication_f9Address.action');">
				</s:else>
			</td>
		</tr>			
		<tr><td  >Applicants Comments</td>
			<td colspan="3" >
				<s:textarea label="%{getText('comments')}" 		theme="simple"	 cols="110"	rows="4"	name="leaveApplication.comments" />
			</td>
		</tr>
		<tr><td colspan="4"><hr></td></tr>
		<tr><td colspan="4" >
					<div id="ration" >
						<table width="100%" >
							<tr><td colspan="4"  >Ration to be:(*delete whichever is not applicable)</td></tr>
							<tr><td  >&nbsp;</td>
								<td><s:radio name="leaveApplication.rationRW"  list="#{'Continue':'Continue','Ceased':'Ceased'}"   theme="simple"/></td>
									<td >To Draw </td>
									<td><s:select name="leaveApplication.toDraw" onchange="javascript:showText();" theme="simple" list="#{'':'Select','RIK':'RIK(S/V)','WNCO':'Vitc in WNC(O)','CLR':'Draw CLR'}" /> </td>
							</tr>
							<tr><td>&nbsp;</td>
								<td colspan="4">w.e.f &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<s:textfield theme="simple" name="leaveApplication.wefDate" />
										<s:a href="javascript:NewCal('leaveApplication.wefDate','DDMMYYYY');"><img src="../pages/images/cal.gif"  class="iconImage" height="16" align="absmiddle"	width="16" ></s:a>
										<s:radio name="leaveApplication.rationCHQ"  list="#{'AM':'AM','PM':'PM'}"   theme="simple"/>
								</td>
							</tr>
							<tr><td colspan="4" ><div id="div2" ><table width="100%" >
											<tr><td>&nbsp;</td>
												<td  >Vide HQWNC Letter
																		<s:textfield theme="simple" name="leaveApplication.letterNo" />
												</td>
												<td  colspan="2" >Letter Date&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<s:textfield theme="simple" name="leaveApplication.letterDate" />
														<s:a href="javascript:NewCal('leaveApplication.letterDate','DDMMYYYY');"><img src="../pages/images/cal.gif"  class="iconImage" height="16" align="absmiddle"	width="16" ></s:a>				
												</td>
												<td  >&nbsp;</td>
											</tr>
										</table>
									</div>
								</td>
							</tr>
							<tr><td colspan="4"><hr></td></tr>
					 </table>
				</div>
			</td>
		</tr>
		<tr><td  >Reliever Name</td>
			<td >
				<s:textfield label="%{getText('relieverName')}" theme="simple" readonly="true" name="leaveApplication.relieverName" size="33" />
				<s:hidden name="leaveApplication.reliever"  value="%{leaveApplication.reliever}" />
				<s:if test="%{leaveApplication.isForApprove}">
				</s:if>
				<s:else>
				<img src="../pages/images/search.gif" class="iconImage" height="16" align="absmiddle"	width="16" onclick="javascript:callsF9(500,325,'LeaveApplication_f9actionRelieverCode.action');">
				</s:else>
			</td>
			<td colspan="2" >Application Status :
				<s:textfield  label="%{getText('status')}"  theme="simple" size="33" name="leaveApplication.status" readonly="true" />
			</td>
		</tr>
		<tr><td colspan="4" >Leave Application Path :</td></tr>
		<tr><td>Recommended By<font color="red" >*</font>:</td>
			<td >
				<s:textfield  theme="simple" name="leaveApplication.forwardName" readonly="true" size="32" />
				<s:hidden name="leaveApplication.forwardId"  value="%{leaveApplication.forwardId}" />
				<s:if test="%{leaveApplication.isForApprove}">
				</s:if>
				<s:else>
				<img src="../pages/images/search.gif" class="iconImage" height="16" align="absmiddle"	width="16" onclick="javascript:callsF9(500,325,'LeaveApplication_f9actionApproverCode.action');">
				</s:else>
			</td>
			<td colspan="2" >Approved By <font color="red" >*</font> :
				<s:textfield  theme="simple"  name="leaveApplication.approverName" readonly="true" size="35" />
				<s:hidden name="leaveApplication.approverId"  value="%{leaveApplication.approverId}" />
				<s:if test="%{leaveApplication.isForApprove}">
				</s:if>
				<s:else>
				<img src="../pages/images/search.gif" class="iconImage" height="16" align="absmiddle"	width="16" onclick="javascript:callsF9(500,325,'LeaveApplication_f9actionForwardCode.action');">
				</s:else>
			</td>
		</tr>
		 <tr><td colspan="4">&nbsp;</td></tr>
		 <s:if test="%{leaveApplication.isForApprove}">
		 	<s:if test="%{leaveApplication.isAdmin}">
			 	 <tr>
					 	<td colspan="4" >
					 	<center>
					 		<table width="100%">
					 			<tr>
					 				<td >Comments</td>
					 				<td><s:textarea theme="simple"	 cols="40"	rows="4" name="leaveApplication.assesedComment" /></td>
					 			</tr>
					 			<tr>
					 				<td  >Status</td>
					 				<td><s:select  label="%{getText('status')}"  theme="simple"  name="leaveApplication.assesedApproved" list="statMap"/></td>
					 			</tr>
					 		</table>
					 	</center>
					 	</td>
				 </tr>
				 <tr><td>&nbsp;</td></tr>
			 	<tr><td colspan="4" >
			 			<center>
							<s:submit cssClass="pagebutton" action="LeaveApplication_saveForApprove" theme="simple"	value="Save" />
						</center>
			 		</td>
			 	</tr>
		 	</s:if>
		</s:if>
		<s:else>
		 <tr><td colspan="4" >
				<center>
				<s:submit cssClass="pagebutton"  theme="simple" onclick="return callSave();" action="LeaveApplication_save" value="Save" />&nbsp;
				<s:submit cssClass="pagebutton" action="LeaveApplication_forward" value="Forward" onclick="return callForward();" theme="simple"  />&nbsp;
				<input type="button" class="pagebutton"  onclick="callReport('LeaveApplication_report.action')" value="Report"/>&nbsp;
				<s:submit cssClass="pagebutton" action="LeaveApplication_clear" value="Reset" theme="simple"  />
				</center>
			</td>
		</tr>
		</s:else>
		 <s:if test="%{leaveApplication.isForApprove}">
			 <tr><td colspan="4" >
					<div bgcolor="#FFFFFF" align="center">
						<table width="100%">
							<tr>
								<td class="headerCell" width="30%">Officer Name</td>
								<td class="headerCell">Comments</td>
								<td class="headerCell">Date</td>
							</tr>
							<s:iterator value="leaveStatus" >
								<tr>
									<td width="30%" ><s:property  value="assesedName" /></td>
									<td><s:property  value="assesedComment" /></td>
									<td><s:property  value="assesedDate" /></td>
								</tr>
								<tr><td>&nbsp;</td><td>&nbsp;</td></tr>
							</s:iterator>
						</table>
			 	</td>
			</tr>
	 	</s:if>
		<tr><td>&nbsp;</td></tr>
	</table>
	
	<s:hidden name="leaveApplication.paracode" />
	<s:hidden name="leaveApplication.hiddenDate" />
	
	<s:hidden name="leaveApplication.remlevCode" />
	<s:hidden name="leaveApplication.remLevDay" />
	<s:hidden name="leaveApplication.remlevDtl" />

		
	</s:form>

	<script>	
		callDisplay();
		showText();
	// #{'C':'Recomended','A':'Approved','R':'Rejected'}
	function callForEdit(id){
			document.getElementById("paraFrm").action="LeaveApplication_editLdtl.action";
	//	   	document.getElementById('leaveApplication.paracode').value=id;
		   	document.getElementById('leaveApplication.hiddenDate').value=id;
		  	document.getElementById("paraFrm").submit();

   }
   function callForRemove(id,leavDays,levCode){
			document.getElementById("paraFrm").action="LeaveApplication_removeLeave.action";
		   	document.getElementById('leaveApplication.remLevDay').value=leavDays;
		   	document.getElementById('leaveApplication.remlevCode').value=levCode;
		   	document.getElementById('leaveApplication.remlevDtl').value=id;
		  	document.getElementById("paraFrm").submit();

   }
   function callForward(){
   		var forw = document.getElementById('leaveApplication.forwardName').value;
		var appr = document.getElementById('leaveApplication.approverName').value;
		var applCode = document.getElementById('leaveApplication.leaveCode').value;
		var status = document.getElementById('leaveApplication.status').value ;
		if(applCode==""){
			alert ("If you have already applied application '\n then select application for forward '\n if not then  Save Application first then forward !");
			return false;
		}
		if(!(status=="Being Applied")){
			alert ("Your application already forwarded!");
			return false;
		}
		if(forw==""){
			alert ("Select officer name which you want forward the application for recomended !");
			return false;
		}
		if(appr==""){
			alert ("Select officer name which you want forward the application for approve !");
			return false;
		}
		return true;
   }
	
	function callAdd(){
		var empcode = document.getElementById('leaveApplication.empCode').value;
		var leav = document.getElementById('leaveApplication.levCode').value;
		
		var pre = document.getElementById('leaveApplication.prefix').value;
		var suf = document.getElementById('leaveApplication.suffix').value;
		var lettNo = document.getElementById('leaveApplication.letterNo').value;
		var status = document.getElementById('leaveApplication.status').value ;
		var baln = document.getElementById('levOpeningBalance').value ;
		var close = document.getElementById('levClosingBalance').value ;
		var total = document.getElementById('leaveApplication.leaveTotalDays').value ;
		if(empcode==""){
			alert ("Select name of applicant !");
			return false;
		}
		if(leav==""){
			alert ("Select leave detail !");
			return false;
		}
		
		if(!(pre=="")){
			var iChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?";
		  		for (var i = 0; i < pre.length; i++) {			
			  	if (iChars.indexOf(pre.charAt(i)) != -1) {
				  	alert ("Enter valid Prefixsty !");
				  	return false;
  					}
  				}
		}
		if(!(suf=="")){
			var iChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?";
		  		for (var i = 0; i < suf.length; i++) {		  	
			  	if (iChars.indexOf(suf.charAt(i)) != -1) {
				  	alert ("Enter valid Sufixsss !");
				  	return false;
  					}
  				}
		}
		if(!(lettNo=="")){
			var iChars = "!@#$%^&*()+=[]\\\';,/{}|\"<>?";
		  		for (var i = 0; i < lettNo.length; i++) {		  	
			  	if (iChars.indexOf(lettNo.charAt(i)) != -1) {
				  	alert ("Enter valid letter No !");
				  	return false;
  					}
  				}
		}
		if(baln==0){
			alert ("You don't have sufficient leave balance  !");
			return false;
		}
		if(close < 0){
			alert ("You don't have sufficient leave  !");
			return false;
		}
		if(total ==0){
			alert ("You can't apply without leave  !");
			return false;
		}
		if(total < 0){
			alert ("Todate  should greater than from date  !");
			return false;
		}
		return true;
	}         
	
  	function callReport(name) {
  			if(document.getElementById('leaveApplication.leaveCode').value == ""){
  				alert("Select application");
  			}else{
				document.getElementById('paraFrm').target="_blank";
				document.getElementById('paraFrm').action=name;	
				document.getElementById('paraFrm').submit();	
				document.getElementById('paraFrm').target="main";
			}						
	}	
	function getEmployee(){
		var empid =document.getElementById('leaveApplication.leaveCode').value;
		if(empid < 1){
			callsF9(500,325,'LeaveApplication_f9actionEmployeeCode.action');
		}else{
			alert("You can't change or select employee for this Application ! ")
		}
	}
	
	function callAjax(link, formName) {	
		document.getElementById("paraFrm").target="main";
		parent.frames[2].name="main";
		retrieveURL(link,formName);
		parent.frames[2].name="main";	

	}
	function callDisplay(){
		var empType = document.getElementById('leaveApplication.isOfficer').value;
		if(!(empType == "4" || empType == "7")){
			document.getElementById('ration').style.display='none';
		}
	}
	function showText(){
		var chq = document.getElementById('leaveApplication.toDraw').value;
		if(chq=="CLR"){
			document.getElementById('div2').style.display='';
		}else{
			document.getElementById('div2').style.display='none';
		}
	}
	function callDate(){
		document.getElementById('leaveToDtl').focus();
	}
	function callSave(){
		var forw = document.getElementById('leaveApplication.forwardName').value;
		var appr = document.getElementById('leaveApplication.approverName').value;
		var applCode = document.getElementById('leaveApplication.leaveCode').value;
		if(applCode==""){
			alert ("If you have already applied application '\n then select application for save '\n if not then  add leave first then save !");
			return false;
		}
		if(forw==""){
			alert ("Select officer name which you want forward the application for recomended !");
			return false;
		}
		if(appr==""){
			alert ("Select officer name which you want forward the application for approve !");
			return false;
		}
		return true;
	}

  	</script>
  	
  	<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
