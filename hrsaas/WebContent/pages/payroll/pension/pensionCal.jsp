<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="EmpConfForPension" method="post" id="paraFrm" validate="true" target="main" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg" >
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Pension
					Calculation </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3"><%@include
				file="/pages/payroll/pension/empConfigurationList.jsp"%>

			</td>
		</tr>
		<!--<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="1">
				<tr>
					<td width="50%"><s:if test="noDataPC"></s:if><s:else>
						<s:if test="lockFlag">
							<input type="button" value=" Stop Pension"
								onclick="return updatePensionStatus('S');" class="save" />
							<input type="button" value=" OnHold"
								onclick="return updatePensionStatus('O');" class="token" />


							<td>Report Type :</td>
							<td><s:select theme="simple" name="report"
								cssStyle="width:60" list="#{'Pdf':'Pdf','Xls':'Xls'}" /> <input
								type="button" value=" Export " class="token"
								onclick="callReportPensionType('report','L');"></td>


						</s:if>
						<s:else>
							<s:if test="onholdFlag">
								555<input type="button" value=" Remove OnHold"
									onclick="return updatePensionStatus('RO');" class="token" />
								<td>Report Type :</td>
								<td><s:select theme="simple" name="report"
									cssStyle="width:60" list="#{'Pdf':'Pdf','Xls':'Xls'}" /> <input
									type="button" value=" Export " class="token"
									onclick="callReportPensionType('report','O');"></td>

							</s:if>
							<s:else>
								<s:if test="stopPensionFlag">

									<td>Report Type :</td>
									<td><s:select theme="simple" name="report"
										cssStyle="width:60" list="#{'Pdf':'Pdf','Xls':'Xls'}" /> <input
										type="button" value=" Export " class="token"
										onclick="callReportPensionType('report','S');"></td>
								</s:if>
								<s:else>
									<input type="button" value=" Lock"
										onclick="return updatePensionStatus('L');" class="lock" />
									<td>Report Type :</td>
									<td><s:select theme="simple" name="report"
										cssStyle="width:60" list="#{'Pdf':'Pdf','Xls':'Xls'}" /> <input
										type="button" value=" Export " class="token"
										onclick="callReportPensionType('report','U');"></td>
								</s:else>
								
							</s:else>

						</s:else>

					</s:else></td>
					<td width="18%"><label class="set" name="export.all"
						id="export.all" ondblclick="callShowDiv(this);"><%=label.get("export.all")%></label>
					: <input type="checkbox" name="exportAll" id="exportAll"
						checked="checked" onclick="callChkBox('exportAll');"></td>
				</tr>
			</table>
			</td>
		</tr>
		-->
		<!--<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="1">
				<tr>
					<td width="45%"> <s:if test="noDataPC"></s:if><s:else>
						<s:if test="lockFlag">
							<input type="button" value=" Stop Pension"
								onclick="return updatePensionStatus('S');" class="save" /> <input type="button" value=" OnHold"
								onclick="return updatePensionStatus('O');" class="token" />
								<td >Report Type :</td>
							<td ><s:select theme="simple" name="report" cssStyle="width:80" 
							list="#{'Pdf':'Pdf','Xls':'Xls'}" /></td>
								<td>
								<input type="button" value=" Export " class="token"
							onclick="callReportPensionType('report','L')">
							</td>
						</s:if>
						<s:else>
							<s:if test="onholdFlag">
								<input type="button" value=" Remove OnHold" onclick="return updatePensionStatus('RO');"
								class="token" /> 
								
								<td >Report Type :</td>
							<td ><s:select theme="simple" name="report" cssStyle="width:80" 
							list="#{'Pdf':'Pdf','Xls':'Xls'}" /></td>
								<td>
								<input type="button" value=" Export " class="token"
							onclick="callReportPensionType('report','O')">
							</td>
							</s:if>
							
							<s:elseif test="stopPensionFlag">
							<input type="button" value=" Lock" onclick="return updatePensionStatus('L');"
								class="lock" /> 
								<td >Report Type :</td>
							<td ><s:select theme="simple" name="report" cssStyle="width:80" 
							list="#{'Pdf':'Pdf','Xls':'Xls'}" /></td>
								<td>
								<input type="button" value=" Export " class="token"
							onclick="callReportPensionType('report','S')">
							</td>
								
							</s:elseif>
							
							
							<s:else>
								<input type="button" value=" Lock" onclick="return updatePensionStatus('L');"
								class="lock" /> 
								<td >Report Type :</td>
							<td ><s:select theme="simple" name="report" cssStyle="width:80" 
							list="#{'Pdf':'Pdf','Xls':'Xls'}" /></td>
								<td>
								<input type="button" value=" Export " class="token"
							onclick="callReportPensionType('report','U')">
							</td>
							</s:else>
							
						</s:else>
						
					</s:else></td>
					<td width="18%">
						<label class="set" name="export.all" id="export.all"
							ondblclick="callShowDiv(this);"><%=label.get("export.all")%></label> : <input
							type="checkbox" name="exportAll" id="exportAll" checked="checked"
							onclick="callChkBox('exportAll');">
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		--><tr>
			<s:hidden name="lockFlag"></s:hidden>
			<s:hidden name="onholdFlag" /><s:hidden name="stopPensionFlag" />
			<td colspan="3"><s:hidden name='divisionChk' /><s:hidden
				name='branchChk' /><s:hidden name='desgChk' /><s:hidden
				name='dobChk' /> <s:hidden name='dojChk' /><s:hidden name='ageChk' /><s:hidden
				name='statusChk' /><s:hidden name="reportType" /><s:hidden
				name="checkedCount" /> <s:hidden name="applDivisionCode" /> <s:hidden
				name="applDivisionName" /> <s:hidden name="applBranchCode" /><s:hidden
				name="applBranchName" /> <s:hidden name="applDeptCode" /><s:hidden
				name="applDeptName" /><s:hidden name="applDesgCode" /> <s:hidden
				name="applDesgName" /> <s:hidden name="applETypeCode" /> <s:hidden
				name="applETypeName" /><s:hidden name="applGradeCode" /><s:hidden
				name="applGradeName" /><s:hidden name="applEmpCode" /><s:hidden
				name="applEmpName" /> <s:hidden name="sortBy1" /><s:hidden
				name="sortBy2" /><s:hidden name="sortBy3" /><s:hidden
				name="sortByOrder1" /> <s:hidden name="sortByOrder2" /><s:hidden
				name="sortByOrder3" /> <s:hidden name="dobCompare" /><s:hidden
				name="dobFrom" /><s:hidden name="dobTo" /><s:hidden
				name="dojCompare" /> <s:hidden name="dojFrom" /><s:hidden
				name="dojTo" /><s:hidden name="dorCompare" /><s:hidden
				name="dorFrom" /><s:hidden name="dorTo" /><s:hidden
				name="ageFilter" /><s:hidden name="ageOperator" />
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td height="27" class="formtxt" colspan="3"><strong> <%
											 	String status = (String) request.getParameter("hiddenStatus");
											
											 			if (status == null) {
											 				out.println("Pension Due List :");
											 			} else if (status.equals("PP")) {
											 				out.println("Pension Processed List :");
											 			} else if (status.equals("PA")) {
											 				out.println("Pension Active List :");
											 			} else if (status.equals("PO")) {
											 				out.println("Pension On-Hold List :");
											 			} else if (status.equals("PS")) {
											 				out.println("Pension Stop List :");
											 			} 
											 %> </strong></td>
				</tr>
				
				<tr>
			<td colspan="8">
			<table width="100%" border="0" cellpadding="2" cellspacing="1">
				<tr>
					<td> <s:if test="noDataPC"></s:if><s:else>
						<s:if test="lockFlag">
							<input type="button" value=" Stop Pension"
								onclick="return updatePensionStatus('S');" class="save" /> <input type="button" value=" OnHold"
								onclick="return updatePensionStatus('O');" class="token" />
								<td >Report Type :<s:select theme="simple" name="report" cssStyle="width:80" 
							list="#{'Pdf':'Pdf','Xls':'Xls'}" />
								<input type="button" value=" Export " class="token"
							onclick="callReportPensionType('report','L')">
							</td>
						</s:if>
						<s:else>
							<s:if test="onholdFlag">
								<input type="button" value=" Remove OnHold" onclick="return updatePensionStatus('RO');"
								class="token" /> 
								
								<td >Report Type :<s:select theme="simple" name="report" cssStyle="width:80" 
							list="#{'Pdf':'Pdf','Xls':'Xls'}" />
								<input type="button" value=" Export " class="token"
							onclick="callReportPensionType('report','O')">
							</td>
							</s:if>
							
							<s:elseif test="stopPensionFlag">
							<input type="button" value=" Lock" onclick="return updatePensionStatus('L');"
								class="lock" /> 
								<td >Report Type :<s:select theme="simple" name="report" cssStyle="width:80" 
							list="#{'Pdf':'Pdf','Xls':'Xls'}" />
								<input type="button" value=" Export " class="token"
							onclick="callReportPensionType('report','S')">
							</td>
								
							</s:elseif>
							
							
							<s:else>
								<input type="button" value=" Lock" onclick="return updatePensionStatus('L');"
								class="lock" /> 
								<td >Report Type :<s:select theme="simple" name="report" cssStyle="width:80" 
							list="#{'Pdf':'Pdf','Xls':'Xls'}" />
								<input type="button" value=" Export " class="token"
							onclick="callReportPensionType('report','U')">
							</td>
							</s:else>
							
						</s:else>
						
					</s:else></td>
					<td width="18%">
						<label class="set" name="export.all" id="export.all"
							ondblclick="callShowDiv(this);"><%=label.get("export.all")%></label> : <input
							type="checkbox" name="exportAll" id="exportAll" checked="checked"
							onclick="callChkBox('exportAll');">
					</td>
				
					
					<%
						int totalPage = 0;
							int pageNo = 0;
							try {
								totalPage = (Integer) request.getAttribute("totalPage");
								pageNo = (Integer) request.getAttribute("PageNo");

							} catch (Exception e) {

							}
					%>
					<%
						int cntPage = pageNo * 20 - 20;
					%>
					<s:if test="noDataPC"></s:if>
					<s:else>
						<td align="right" colspan="4"><b>Page:</b> <input
							type="hidden" name="totalPage" id="totalPage"
							value="<%=totalPage%>"> <a href="#"
							onclick="callPage('1','F');"> <img title="First Page"
							src="../pages/common/img/first.gif" width="10" height="10"
							class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('P','P');"> <img title="Previous Page"
							src="../pages/common/img/previous.gif" width="10" height="10"
							class="iconImage" /> </a> <input type="text" name="pageNoField"
							id="pageNoField" theme="simple" size="3" value="<%= pageNo%>"
							onkeypress="callPageText(event);return numbersOnly()"
							maxlength="10" /> of <%=totalPage%> <a href="#"
							onclick="callPage('N','N')"> <img title="Next Page"
							src="../pages/common/img/next.gif" class="iconImage" /> </a>&nbsp; <a
							href="#" onclick="callPage('<%=totalPage%>','L');"> <img
							title="Last Page" src="../pages/common/img/last.gif" width="10"
							height="10" class="iconImage" /> </a></td>
					</s:else>
				</tr>
			</table>
			</td>
		</tr>
				
				

				<tr>
					<s:hidden name="myPagePC" />
					<s:hidden name='noDataPC' />
					
					<td width="8%" class="formth"><input type="checkbox"
						id='checkAllForLock' name='checkAllForLock'
						onclick="checkAllEmp();" /></td>
					<td width="5%" class="formth"><label class="set" name="srno"
						id="srno" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></td>
					
					
					<td width="20%" class="formth"><label class="set"
						name="employee.id" id="employee.id"
						ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></td>
					<td width="34%" class="formth"><label class="set"
						name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
					<td width="11%" class="formth"><label class="set"
						name="pensionType" id="pensionType"
						ondblclick="callShowDiv(this);"><%=label.get("pensionType")%></label></td>
					<td width="11%" class="formth"><label class="set"
						name="pensionAmt" id="pensionAmt" ondblclick="callShowDiv(this);"><%=label.get("pensionAmt")%></label></td>
					<td width="11%" class="formth"><label class="set"
						name="grossPensionAmt" id="grossPensionAmt"
						ondblclick="callShowDiv(this);"><%=label.get("grossPensionAmt")%></label></td>
					<td width="11%" class="formth"><label class="set"
						name="pensionDetails" id="pensionDetails"
						ondblclick="callShowDiv(this);"><%=label.get("pensionDetails")%></label></td>
					
				</tr>
				<s:if test="noDataPC">
					<tr>
						<td width="100%" colspan="7" align="center"><font color="red">No
						data to display</font></td>
					</tr>
				</s:if>
				<s:else>
					<%
						int i = 0;
					%>
					<s:iterator value="empListPC">
						<tr onmouseover="javascript:newRowColor(this);"
														title="Double click for edit"
														onmouseout="javascript:oldRowColor(this);"><%
								++i;
							%>
							<td class="td_bottom_border" width="8%" align="center"
								nowrap="nowrap"><input type="checkbox" name="lockChk"
								id="<%="lockChk"+i %>" onclick="checkEmpForLock(<%=i%>);" /><s:hidden
								name='lockChkHd' value='N' id="<%="lockChkHd"+i %>" /></td>
							
							<td class="td_bottom_border" width="5%"><%=++cntPage%>
							
							</td>
							
							
							<td class="td_bottom_border" width="20%" nowrap="nowrap"><s:property
								value="empIdPC" /><s:hidden name="empIdPC" /><s:hidden
								name="empCodePC" /></td>
							<td class="td_bottom_border" width="34%" align="left"
								nowrap="nowrap"><s:property value="empNamePC" /><s:hidden
								name="empNamePC" /></td>

							<td class="td_bottom_border" width="11%" align="left"
								nowrap="nowrap"><s:property value="pensionTypePC" /><s:hidden
								name="pensionTypePC" /></td>
							<td class="td_bottom_border" width="11%" align="right"
								nowrap="nowrap"><s:property value="pensionAmtPC" /><s:hidden
								name="pensionAmtPC" /></td>
							<td class="td_bottom_border" width="11%" align="right"
								nowrap="nowrap"><s:property value="grossPensionAmt" /><s:hidden
								name="grossPensionAmt" /></td>
							<td class="td_bottom_border" width="11%" align="center"
								nowrap="nowrap"><input type="button" value='Details'
								class="token"
								onclick="callPensionDetails(<s:property value='empCodePC'/>);" /></td>
							

						</tr>
					</s:iterator>
					<input type="hidden" name='count' id='count' value='<%=i%>' />
				</s:else>
				<s:hidden name='empCodePCDet' />
				</td>
				</tr>

			</table>
			</td>
		</tr>
	</table>
</s:form>
<script type="text/javascript">


function callPensionDetails(empId){
	//alert('opens pension calculation window');
	var lockFlag=document.getElementById('paraFrm_lockFlag').value;
	document.getElementById("paraFrm_empCodePCDet").value=empId;
	document.getElementById('paraFrm').target="wind";
   var wind = window.open('','wind','width=800,height=550,scrollbars=yes,resizable=no,status=yes,menubar=no,top=200,left=150');	 
   document.getElementById('paraFrm').action="EmpConfForPension_viewPensionDetails.action?lockFlag="+lockFlag;
   document.getElementById('paraFrm').submit();
   document.getElementById('paraFrm').target="";
}
function checkAllEmp(){
	var flag=document.getElementById('checkAllForLock').checked;
	var count=document.getElementById('count').value;
	for(var i=1;i<=eval(count);i++){
	if(flag){
	document.getElementById("lockChkHd"+i).value="Y";
	}else{
		document.getElementById("lockChkHd"+i).value="N";
	}
	document.getElementById("lockChk"+i).checked=flag;
}
}

/*
function callLock(){
	var selectedFlag='false';
	var count=document.getElementById('count').value;
	for(var i=1;i<=eval(count);i++){
	if(document.getElementById("lockChk"+i).checked){
	selectedFlag='true';
		}
	}
	if(selectedFlag=="false"){
			alert("Please select atleast one record to lock");
			return false;
		}
		var conf=confirm("Do you really want to lock these records ?");
  			if(conf) {
		document.getElementById('paraFrm').action="EmpConfForPension_lockPension.action"
		document.getElementById('paraFrm').submit();	
		}
	
}
function callStopPension(){
	var selectedFlag='false';
	var count=document.getElementById('count').value;
	for(var i=1;i<=eval(count);i++){
	if(document.getElementById("lockChk"+i).checked){
	selectedFlag='true';
		}
	}
	if(selectedFlag=="false"){
			alert("Please select atleast one record to stop pension");
			return false;
		}
		var conf=confirm("Do you really want to stop pension ?");
  			if(conf) {
		document.getElementById('paraFrm').action="EmpConfForPension_stopPension.action"
		document.getElementById('paraFrm').submit();	
		}
	
}*/

//ADDED BY REEBA - BEGINS
function updatePensionStatus(type){
	var selectedFlag='false';
	var count=document.getElementById('count').value;
	for(var i=1;i<=eval(count);i++){
	if(document.getElementById("lockChk"+i).checked){
	selectedFlag='true';
		}
	}
	if(selectedFlag=="false"){
		alert("Please select atleast one record.");
		return false;
	}
	var conf="";
	if(type=="S"){
		conf=confirm("Do you really want to stop pension ?");
		if(conf) {
			document.getElementById('paraFrm').action="EmpConfForPension_updatePensionStatus.action?status=S";
			document.getElementById('paraFrm').submit();	
		}
	}else if(type=="L"){
		conf=confirm("Do you really want to lock these records ?");
		if(conf) {
			document.getElementById('paraFrm').action="EmpConfForPension_updatePensionStatus.action?status=L";
			document.getElementById('paraFrm').submit();	
		}
	}else if(type=="O"){
		conf=confirm("Do you really want to put these records onhold?");
		if(conf) {
			document.getElementById('paraFrm').action="EmpConfForPension_updatePensionStatus.action?status=O";
			document.getElementById('paraFrm').submit();	
		}
	}else if(type=="RO"){
		//conf=confirm("Do you really want to lock these records ?");
		//if(conf) {
			document.getElementById('paraFrm').action="EmpConfForPension_updatePensionStatus.action?status=L";
			document.getElementById('paraFrm').submit();	
		//}
	}
}
//ADDED BY REEBA - ENDS


function callBack(){
		document.getElementById('paraFrm').action="EmpConfForPension_callBackToApplList.action"
		document.getElementById('paraFrm').submit();	
}
function checkEmpForLock(id){
	if(document.getElementById("lockChk"+id).checked){
		document.getElementById("lockChkHd"+id).value="Y";
	}else{
		document.getElementById("lockChkHd"+id).value="N";
	}
	
	var count=document.getElementById('count').value;
	var flag=true;
	for(var i=1;i<=eval(count);i++){
	if(!document.getElementById("lockChk"+i).checked){
		flag=false;
	}
	}
	document.getElementById('checkAllForLock').checked=flag;
}

function callPageText(id,status1){
 			 
	   if(status1=="null" || status1=="" ){		
				status1="P";
			}
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoField').value;		
		 	totalPage =document.getElementById('totalPage').value;		
			document.getElementById('paraFrm_myPagePC').value=pageNo;
	      
	        if(pageNo==""){
	        alert("Please Enter Page Number.");
	        document.getElementById('pageNoField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero.");
		     document.getElementById('pageNoField').focus();
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField').focus();
			 return false;
		    }
		var lockFlag=document.getElementById('paraFrm_lockFlag').value;
		var onholdFlag=document.getElementById('paraFrm_onholdFlag').value;
		var actionName="EmpConfForPension_viewCalculatedPension.action";
		if(lockFlag=="true"){
			actionName="EmpConfForPension_viewLockedPension.action";
		}else if(onholdFlag=="true"){
			actionName="EmpConfForPension_viewOnholdPension.action";
		}
	
		
		document.getElementById('paraFrm').action=actionName;
		document.getElementById('paraFrm').submit();
		}
		
		
	}
	 function callPage(id,pageImg){
 	 pageNo =document.getElementById('pageNoField').value;	
 	 totalPage =document.getElementById('totalPage').value;	 
 	 
 	 
 	   if(pageNo==""){
	        alert("Please Enter Page Number.");
	        document.getElementById('pageNoField').focus();
			 return false;
	        }
	 if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero.");
		     document.getElementById('pageNoField').focus();
			 return false;
		    }
	  if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField').focus();
			 return false;
		    }
		    	
       if(pageImg=="F")
         {
	        if(pageNo=="1"){
	         alert("This is first page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       }  
       
       	if(pageImg=="L")
         {
	        if(eval(pageNo)==eval(totalPage)){
	         alert("This is last page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       } 
       
        if(pageImg=="P")
       {
	        if(pageNo=="1"){
	         alert("There is no previous page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        }  
       }  
       if(pageImg=="N")
       {
	        if(Number(pageNo)==Number(totalPage)){
	         alert("There is no next page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        }  
       }  
       
         var p=document.getElementById('pageNoField').value;
		if(id=='P'){
		id=eval(p)-1;
		}
		if(id=='N'){
		id=eval(p)+1;
		} 
		var lockFlag=document.getElementById('paraFrm_lockFlag').value;
		var onholdFlag=document.getElementById('paraFrm_onholdFlag').value;
		var actionName="EmpConfForPension_viewCalculatedPension.action";
		if(lockFlag=="true"){
			actionName="EmpConfForPension_viewLockedPension.action";
		}else if(onholdFlag=="true"){
			actionName="EmpConfForPension_viewOnholdPension.action";
		}
		document.getElementById('paraFrm_myPagePC').value=id;
		
		document.getElementById('paraFrm').action=actionName;
		document.getElementById('paraFrm').submit();
		 
	}
	
function callReportForDisp(reportType,status)
{
 document.getElementById('paraFrm_reportType').value=reportType;
   callReport('EmpConfForPension_generateOnHoldReport.action?status='+status);    
}
function callReportPensionType(reportType,status)
{
 document.getElementById('paraFrm_reportType').value=document.getElementById('paraFrm_'+reportType).value;
   callReport('EmpConfForPension_generateOnHoldReport.action?status='+status);     
}

</script>