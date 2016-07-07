	<%@ taglib uri="/struts-tags" prefix="s"%>	
	    <script type="text/javascript" src="Ajax.js"></script>
	    
	<s:form action="LeaveExtension" method="post" name="LeaveForm"  id="paraFrm" theme="simple" target="main">
	<table class="tableBody" width="90%">
	<tr>
		<td class="pageHeader" colspan="4" ><center>Leave Application Form</center></td>
	</tr>
	<tr><td>&nbsp;<s:hidden  theme="simple"name="leave.leaveCode" /></td></tr>
	<tr><td  >Applicant Name</td>
		<td colspan="3"><s:hidden 	theme="simple"	name="leave.empCode" value="%{leave.empCode}" />
						<s:hidden name="leave.isOfficer" />
			<s:textfield theme="simple"  readonly="true"  name="leave.tokenNo" />
			<s:textfield label="%{getText('empName')}" 	theme="simple" size="63" readonly="true" name="leave.empName" />
		</td>
	</tr>
	<tr>
		<td  >Center Name</td>
		<td colspan="3" >
			<s:textfield label="%{getText('centerNo')}" theme="simple" size="63"	readonly="true"	name="leave.center" />
		</td>
	</tr>
	<tr><td  >Rank Name</td>
		<td colspan="3" >
			<s:textfield label="%{getText('department')}" theme="simple"	readonly="true"	size="63"	name="leave.department" />
		</td>
	</tr>
	<tr><td  >Application Date</td>
		<td colspan="3" >
			<s:textfield label="%{getText('applicationdate')}" 	theme="simple" readonly="true" 	name="leave.applicationDate" />
		</td>
	</tr>
	<tr><td  >Status</td>
		<td colspan="3" >
			<s:textfield  label="%{getText('status')}"  theme="simple"  name="leave.status" readonly="true" />
		</td>
	</tr>
	<tr><td  >Reliever Name</td>
		<td colspan="3">
			<s:textfield label="%{getText('relieverName')}" theme="simple" readonly="true" name="leave.relieverName" size="60" />
			<s:hidden name="leave.reliever"  value="%{leave.reliever}" />
		</td>
	</tr>
	<tr><td  >Prefix</td>
		<td>
			<s:textfield label="%{getText('prefix')}" theme="simple"	name="leave.prefix" />
		</td>
		<td  >Suffix</td>
		<td>
			<s:textfield label="%{getText('suffix')}" theme="simple" name="leave.suffix" />
		</td>
	</tr>
	<tr><td  >Nearest Naval Unit </td>
		<td colspan="3">
			<s:textarea label="%{getText('medicalCert')}" theme="simple" cols="93"	rows="4" name="leave.medicalCert" />
		</td>
	</tr>
	<tr><td  >Leave Address</td>
		<td colspan="3" >
			<s:textarea label="%{getText('contact_details')}" 	name="leave.contactDetails" theme="simple"	cols="93"	rows="4" />
		</td>
	</tr>
	<tr><td  >Comments</td>
		<td colspan="3" >
			<s:textarea label="%{getText('comments')}" 		theme="simple"	 cols="93"	rows="4"	name="leave.comments" />
		</td>
	</tr>
	<tr><td colspan="4" class="seperator" ></td></tr>
		<tr><td colspan="4" >
					<div id="ration" >
						<table width="100%" >
							<tr><td colspan="4"  >Ration to be:(*delete whichever is not applicable)</td></tr>
							<tr><td  >&nbsp;</td>
								<td><s:radio name="leave.rationRW"  list="#{'Continue':'Continue','Ceased':'Ceased'}"   theme="simple"/></td>
									<td >To Draw </td>
									<td><s:select name="leave.toDraw" onchange="javascript:showText();" theme="simple" list="#{'':'Select','RIK':'RIK(S/V)','WNCO':'Vitc in WNC(O)','CLR':'Draw CLR'}" /> </td>
							</tr>
							<tr><td>&nbsp;</td>
								<td colspan="4">w.e.f &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<s:textfield theme="simple" name="leave.wefDate" />
										<s:a href="javascript:NewCal('leave.wefDate','DDMMYYYY');"><img src="../pages/images/cal.gif"  class="iconImage" height="16" align="absmiddle"	width="16" ></s:a>
										<s:radio name="leave.rationCHQ"  list="#{'AM':'AM','PM':'PM'}"   theme="simple"/>
								</td>
							</tr>
							<tr><td colspan="4" ><div id="div2" ><table width="100%" >
											<tr><td>&nbsp;</td>
												<td  >Vide HQWNC Letter
																		<s:textfield theme="simple" name="leave.letterNo" />
												</td>
												<td  colspan="2" >Letter Date&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<s:textfield theme="simple" name="leave.letterDate" />
														<s:a href="javascript:NewCal('leave.letterDate','DDMMYYYY');"><img src="../pages/images/cal.gif"  class="iconImage" height="16" align="absmiddle"	width="16" ></s:a>				
												</td>
												<td  >&nbsp;</td>
											</tr>
										</table>
									</div>
								</td>
							</tr>
							<tr><td colspan="4" class="seperator" ></td></tr>
					 </table>
				</div>
			</td>
	</tr>
	<tr><td  >Forward To</td>
		<td colspan="3">
			<s:textfield  theme="simple" name="leave.forwardName" readonly="true" size="60" />
			<s:hidden name="leave.forwardId"  value="%{leave.forwardId}" />
		</td>
	</tr>
	<!--<tr><td  >Select Approver</td>
		<td colspan="3">
			<s:textfield  theme="simple" name="leave.approverName" readonly="true" size="60" />
			<s:hidden name="leave.approverId"  value="%{leave.approverId}" />
		</td>
	</tr>
	--><tr><td colspan="4" class="seperator" ></td></tr>
	<tr><td colspan="4" >
				
			<table width="100%" colspan="4">
				<tr>
					 <td  >Leave Type</td>
					 <td><s:hidden name="leave.levCode" value="%{leave.levCode}" theme="simple"  />
					 	<s:hidden name="leave.leaveId" value="%{leave.leaveId}" theme="simple"  />
					 	<s:hidden name="leaveApplication.hdlevType" value="%{leaveApplication.hdlevType}" theme="simple"  />
					 <s:textfield name="leave.levType" readonly="true" theme="simple"  />
					 <img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle" width="18" 
					 onclick="javascript:callsF9(500,325,'LeaveExtension_f9ltypeaction.action');">
				  		</td>  
					<td  >Available Balance</td>
					<td  ><span id="open"><s:textfield name="levOpeningBalance" theme="simple" readonly="true"  /></span></td>
				</tr>
				<tr>
					 <td  >From Date</td>
					 <td><s:textfield name="leaveFromDtl" theme="simple"  />
					 	<s:a href="javascript:NewCal('leaveFromDtl','DDMMYYYY');"><img src="../pages/images/cal.gif"  class="iconImage" height="16" align="absmiddle"	width="16" ></s:a>				
					 </td>
					  <td  >To Date</td>
					<td  ><s:textfield name="leaveToDtl" theme="simple"  onblur="leaveExtensionURL('LeaveExtension_calculate.action?','LeaveForm');" />
						<s:a href="javascript:NewCal('leaveToDtl','DDMMYYYY');"><img src="../pages/images/cal.gif"  class="iconImage" height="16" onclick="callDate()" align="absmiddle" width="16" ></s:a>				
					</td>
				</tr>				
				<tr>
					 <td  >Closing balance</td>
					 <td><span id="close"><s:textfield name="levClosingBalance" theme="simple" readonly="true"  /></span></td>
					  <td  >Total leave days</td>
					<td><span id="balance"><s:textfield theme="simple" name="leave.leaveTotalDays" readonly="true" /></span>
					</td>
				</tr>
				<!--<tr><td  >Remark</td>
					<td  ><s:textfield theme="simple" name="leave.remark" /></td>
				</tr>
				--><tr>			
			  		<td width="100%" align="center" colspan="4"><s:submit cssClass="pagebutton" action="LeaveExtension_addLdtl" theme="simple"  onclick="return callAdd();" value="Add"/>
			  		</td>
			  	</tr>			
			</table>
		
		</td>
	</tr>
		
	<tr><td colspan="4">
			<table width="100%" colspan="6" >				
				<tr>
					<td class="headerCell">Leave Type</td>
					<!--<td class="headerCell">Available Balance</td>
					--><td class="headerCell">From Date</td>
					<td class="headerCell">To Date</td>
					<td class="headerCell">Leave Days</td>
					<!--<td class="headerCell">Remark</td>
					--><s:if test="%{leave.isForApprove}">	
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
							<!--<td><s:textfield  name="slevOpeningBalance"  /></td>
							--><td><s:property  value="sleaveFromDtl"   /></td>
							<td><s:property  value="sleaveToDtl"  /></td>
							<td><center><s:property  value="slevClosingBalance"  /></center></td>
							<!--<td><s:property  value="remark"  /></td>
								--><td><input type="button" class="pagebutton" onclick="callForEdit('<s:property value="leaveId" />')"  value="Edit" />
										<input type="button" class="pagebutton" onclick="callForRemove('<s:property value="leaveId" />','<s:property  value="slevClosingBalance"  />','<s:property  value="slevCode" />')"  value="Remove" />
								</td>
						</tr>
			
					<%i++; %>    
				</s:iterator>				
				
			</table>
		</td>
	</tr>			
	 <tr><td>&nbsp;</td></tr>
	</table>
	
	<s:hidden name="leave.paracode" />
	<s:hidden name="leave.hiddenDate" />
	
	<s:hidden name="leave.remlevCode" />
	<s:hidden name="leave.remLevDay" />
	<s:hidden name="leave.remlevDtl" />	
	
	<s:hidden name="leave.zeroBalance" />
	<s:hidden name="leave.advanceAppl" />
	
	</s:form>

	<script>	
	// #{'C':'Recomended','A':'Approved','R':'Rejected'}
		callDisplay();
		showText();
	function callForEdit(id){
			document.getElementById("paraFrm").action="LeaveExtension_editLdtl.action";
	//	   	document.getElementById('leave.paracode').value=id;
		   	document.getElementById('leave.hiddenDate').value=id;
		  	document.getElementById("paraFrm").submit();

   }
	 function callForRemove(id,leavDays,levCode){
			document.getElementById("paraFrm").action="LeaveExtension_removeLeave.action";
		   	document.getElementById('leave.remLevDay').value=leavDays;
		   	document.getElementById('leave.remlevCode').value=levCode;
		   	document.getElementById('leave.remlevDtl').value=id;
		  	document.getElementById("paraFrm").submit();

   }
	function callAdd(){
		var current = new Date();
		 var year =current.getYear();
		 var month =current.getMonth();
		 var days =current.getDate();
		 
		var forw = document.getElementById('leave.forwardName').value;
		var appr = document.getElementById('leave.approverName').value;
		
		var pre = document.getElementById('leave.prefix').value;
		var suf = document.getElementById('leave.suffix').value;
		var lettNo = document.getElementById('leave.letterNo').value;
		var status = document.getElementById('leave.status').value ;
		var baln = document.getElementById('levOpeningBalance').value ;
		var close = document.getElementById('levClosingBalance').value ;
		var total = document.getElementById('leave.leaveTotalDays').value ;
		
			var toDate = document.getElementById('leaveToDtl').value ;
			var zeroFlag = document.getElementById('leave.zeroBalance').value ;
			var advFlag = document.getElementById('leave.advanceAppl').value ;
		
		curentDate = new Date(year,month,days); 
		
		toDate = toDate.split("-");
		middletime= new Date(toDate[2],toDate[1]-1,toDate[0]);
		
		
		if(curentDate <= middletime && advFlag =='N')
		{
		alert("This leave you can't apply as advance")
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
				  	alert ("Enter valid Letter No !");
				  	return false;
  					}
  				}
		}
		if(baln==0 && zeroFlag =='N'){
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
  			if(document.getElementById('leave.leaveCode').value == ""){
  				alert("Select application");
  			}else{
				document.getElementById('paraFrm').target="_blank";
				document.getElementById('paraFrm').action=name;	
				document.getElementById('paraFrm').submit();	
				document.getElementById('paraFrm').target="main";
			}						
	}	
	
	function callAjax(link, formName) {	
		document.getElementById("paraFrm").target="main";
		parent.frames[2].name="main";
		leaveExtensionURL(link,formName);
		parent.frames[2].name="main";	

	}
	function callDate(){
		document.getElementById('leaveToDtl').focus();
	}
	function callDisplay(){
		var empType = document.getElementById('leave.isOfficer').value;
		if(!(empType == "4" || empType == "7")){
			document.getElementById('ration').style.display='none';
		}
	}
	function showText(){
		var chq = document.getElementById('leave.toDraw').value;
		if(chq=="CLR"){
			document.getElementById('div2').style.display='';
		}else{
			document.getElementById('div2').style.display='none';
		}
	}
  	</script>
  	
  	<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
