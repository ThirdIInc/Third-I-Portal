<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="EmpConfForPension" method="post" id="paraFrm" validate="true" target="main" theme="simple">

	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="0" class="formbg">
		<tr>
			<td width="100%"><jsp:include page="/pages/payroll/pension/empConfigurationList.jsp" /></td>
		</tr>

		<tr>
			<td colspan="3" width="100%"><s:hidden name='divisionChk' /><s:hidden
				name='branchChk' /><s:hidden name='desgChk' /><s:hidden
				name='dobChk' /> <s:hidden name='dojChk' /><s:hidden name='ageChk' /><s:hidden
				name='statusChk' /><s:hidden name="reportType" /><s:hidden
				name="checkedCount" value="11" /> <s:hidden name="applDivisionCode" />
			<s:hidden name="applDivisionName" /> <s:hidden name="applBranchCode" /><s:hidden
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

					<td width="25%" colspan="7"><strong><label
						class="set" name="reportTitle" id="reportTitle"
						ondblclick="callShowDiv(this);"><%=label.get("reportTitle")%></label>.</strong></td>
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
				</tr>
				<!--  <tr>
					
					<td width="100%" colspan="13" align="right"><input type="button" value="View Calculated List" onclick="return callViewList();" class="token"/> <input type="button" value="Process" onclick="return callProcessing();" class="token"/></td>
				</tr>-->
			</table>
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td class="formtxt" colspan="3"><strong>
					<%
						String status = (String) request.getParameter("hiddenStatus");

							if (status == null || status.equals("PD")) {
								out.println("Pension Due List : ");
							} else if (status.equals("PP")) {
								out.println("Pension Processed List :");
							}
					%>
					
					 </strong></td>
				</tr>
				<tr>
					
					
					<s:if test="noData"></s:if>
					<s:else>
					<td>
					 <input type="button" value="Process"
						onclick="return callProcessing();" class="token" />
						</td>
						<td align="right" width="40%" colspan="6">Report Type : <s:select
						theme="simple" name="report" cssStyle="width:80"
						list="#{'Pdf':'Pdf','Xls':'Xls','Txt':'Txt'}" /> <input
						type="button" value=" Export " class="token"
						onclick="callReportMain('report');"> <label class="set"
						name="export.all" id="export.all" ondblclick="callShowDiv(this);"><%=label.get("export.all")%></label>
					: <input type="checkbox" name="exportAll" id="exportAll"
						checked="checked" onclick="callChkBox('exportAll');"></td>
						<td align="right" width="30%" colspan="6"><b>Page:</b> <input
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
				<tr>
					<s:hidden name="myPageEmpConf" />
					<s:hidden name='noData' />
					<td width="10%" class="formth"><input type="checkbox"
						name='checkAll' id='checkAll' onclick="return callCheckAll();" /></td>
					<td width="5%" class="formth"><label class="set" name="srno"
						id="srno" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></td>
					

					<td width="10%" class="formth"><label class="set"
						name="employee.id" id="employee.id"
						ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></td>
					<td width="20%" class="formth"><label class="set"
						name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>

					<td width="10%" class="formth"><label class="set"
						name="pensionType" id="pensionType"
						ondblclick="callShowDiv(this);"><%=label.get("pensionType")%></label></td>
					<td width="10%" class="formth"><label class="set"
						name="QualfYearsService" id="QualfYearsService"
						ondblclick="callShowDiv(this);"><%=label.get("QualfYearsService")%></label></td>

					<td width="10%" class="formth"><label class="set" name="dob"
						id="dob" ondblclick="callShowDiv(this);"><%=label.get("dob")%></label>.</td>

					<td width="10%" class="formth"><label class="set" name="doj"
						id="doj" ondblclick="callShowDiv(this);"><%=label.get("doj")%></label></td>

					<td width="10%" class="formth"><label class="set"
						name="dateOfRetirement" id="dateOfRetirement"
						ondblclick="callShowDiv(this);"><%=label.get("dateOfRetirement")%></label></td>


					<td width="15%" class="formth"><label class="set" name="age"
						id="age" ondblclick="callShowDiv(this);"><%=label.get("age")%></label></td>

					<s:if test="statusChk">
						<td width="10%" class="formth"><label class="set"
							name="status" id="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label></td>
					</s:if>
					<td width="10%" class="formth"><label class="set"
						name="division" id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label></td>

					<td width="10%" class="formth"><label class="set"
						name="branch" id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
					</td>

					<td width="10%" class="formth"><label class="set"
						name="designation" id="designation"
						ondblclick="callShowDiv(this);"><%=label.get("designation")%></label></td>

				</tr>
				<s:if test="noData">
					<tr>
						<td width="100%" colspan="14" align="center"><font
							color="red">No data to display</font></td>
					</tr>
				</s:if>
				<s:else>
					
					<s:iterator value="applEmpList">
					<%
						int i = 0;
					%>
						<tr onmouseover="javascript:newRowColor(this);"
														title="Double click for edit"
														onmouseout="javascript:oldRowColor(this);"><%
								++i;
							%>
							<td class="sortableTD" width="10%" align="center" nowrap="nowrap"><input
								type="checkbox" name="processingChk" id="<%="processingChk"+i%>"
								onclick="return chkEmpForProcess(<%=i%>)" /><s:hidden
								name='processingChkHd' id="<%="processingChkHd"+i%>" /></td>
							
							<td class="sortableTD" width="5%"><%=++cntPage%></td>
							

							<td class="sortableTD" width="10%" nowrap="nowrap"><s:property
								value="empToken" /><s:hidden name="empToken" /><s:hidden
								name="empCode" /></td>
							<td class="sortableTD" width="20%" align="left" nowrap="nowrap"><s:property
								value="empName" /><s:hidden name="empName" /></td>

							<td class="sortableTD" width="10%" align="left" nowrap="nowrap"><s:select
								name="pensionType" headerKey="" id="<%="pensionType"+i%>"
								theme="simple" headerValue="Select" cssStyle="width:110"
								list="#{'1':'Super Annuation','2':'Voluntary','3':'Compulsory','4':'Death'}" />
							</td>
							<td class="sortableTD" width="10%" align="left" nowrap="nowrap"><input
								type="text" name='qualfYears' size="10"
								style="text-align: right;" theme="simple"
								onkeypress="numbersOnly()" id="<%="qualfYears"+i%>"></input></td>

							<s:hidden name="empDob" />

							<td class="sortableTD" width="10%" align="left" nowrap="nowrap"><s:property
								value="empDob" />&nbsp;</td>

							<s:hidden name="empDoj" />

							<td class="sortableTD" width="10%" align="left" nowrap="nowrap"><s:property
								value="empDoj" /></td>

							<td class="sortableTD" width="10%" align="left" nowrap="nowrap"><s:property
								value="empDateOfRet" />&nbsp;<s:hidden name="empDateOfRet" /></td>

							<s:hidden name="empAge" />

							<td class="sortableTD" width="15%" align="left" nowrap="nowrap"><s:property
								value="empAge" />&nbsp;</td>

							<s:hidden name="empStatus" />
							<s:if test="statusChk">
								<td class="sortableTD" width="10%" align="left" nowrap="nowrap"><s:property
									value="empStatus" /></td>
							</s:if>
							<s:hidden name="empDiv" />
							<s:hidden name="divCode" />

							<td class="sortableTD" width="10%" align="left"><s:property
								value="empDiv" /></td>

							<s:hidden name="empBranch" />

							<td class="sortableTD" width="10%" align="left"><s:property
								value="empBranch" /></td>

							<s:hidden name="empDesg" />

							<td class="sortableTD" width="10%" align="left"><s:property
								value="empDesg" /></td>

						</tr>
						<input type="hidden" name='count' id='count' value='<%=i%>' />
					</s:iterator>
					
				</s:else>


			</table>
			</td>
		</tr>

	</table>
</s:form>

<script type="text/javascript">


function callProcessing(){
try{	
		var count=document.getElementById('count').value;
		var selectedFlag='false';
		for(var i=1;i<=eval(count);i++){
			if(document.getElementById("processingChk"+i).checked){
				selectedFlag='true';
				if(document.getElementById("pensionType"+i).value==""){
					alert("Please select "+document.getElementById('pensionType').innerHTML);
					document.getElementById("pensionType"+i).focus();
					return false;
				}
				if(document.getElementById("qualfYears"+i).value==""){
					alert("Please enter "+document.getElementById('QualfYearsService').innerHTML);
					document.getElementById("qualfYears"+i).focus();
					return false;
				}
				
			}
		}
		}catch(e){}
		if(selectedFlag=="false"){
			alert("Please select atleast one employee to process");
			return false;
		}
		var conf=confirm("Do you really want to start the processing for selected employees ?");
  			if(conf) {
		document.getElementById('paraFrm').action="EmpConfForPension_calculatePension.action"
		document.getElementById('paraFrm').submit();
		}
} 
function chkEmpForProcess(id){
	if(document.getElementById("processingChk"+id).checked){
		document.getElementById("processingChkHd"+id).value='Y';
	}else{
		document.getElementById("processingChkHd"+id).value='N';
	}
	var count=document.getElementById('count').value;
	var flag=true;
	for(var i=1;i<=eval(count);i++){
	if(!document.getElementById("processingChk"+i).checked){
		flag=false;
	}
	}
	document.getElementById('checkAll').checked=flag;
}
function callViewList(){
		document.getElementById('paraFrm').action="EmpConfForPension_viewCalculatedPension.action"
		document.getElementById('paraFrm').submit();
}
function callViewLockedList(){
		document.getElementById('paraFrm').action="EmpConfForPension_viewLockedPension.action";
		document.getElementById('paraFrm').submit();
}
//METHOD ADDED BY REEBA
function callViewOnholdList(){
		document.getElementById('paraFrm').action="EmpConfForPension_viewOnholdPension.action";
		document.getElementById('paraFrm').submit();
}

callOnload();
function callOnload(){
	var count=document.getElementById('count').value;
	for(var i=1;i<=eval(count);i++){
	document.getElementById("processingChkHd"+i).value="N";
}
}

function callCheckAll(){
	var flag=document.getElementById('checkAll').checked;
	var count=document.getElementById('count').value;
	for(var i=1;i<=eval(count);i++){
	if(flag){
	document.getElementById("processingChkHd"+i).value="Y";
	}else{
		document.getElementById("processingChkHd"+i).value="N";
	}
	document.getElementById("processingChk"+i).checked=flag;
}
}
function callBack()
 {
  document.getElementById('paraFrm').action='EmpConfForPension_callBack.action';
  document.getElementById('paraFrm').submit(); 
 }
function callReportForDisp(reportType)
{
 document.getElementById('paraFrm_reportType').value=reportType;
   callReport('EmpConfForPension_generateReport.action');    
}
//METHOD ADDED BY GANESH
function callReportMain(reportType)
{
 document.getElementById('paraFrm_reportType').value=document.getElementById('paraFrm_'+reportType).value;
   callReport('EmpConfForPension_generateReport.action');    
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
		     document.getElementById('pageNoField').value=document.getElementById('paraFrm_myPageEmpConf').value;
			 return false;
		    }
		    document.getElementById('paraFrm_myPageEmpConf').value=pageNo;
		    
		    //displayConfirmDiv();
			//document.getElementById('confirmationDiv').style.display='block';
			
		   	document.getElementById('paraFrm').action='EmpConfForPension_viewEmpOnScreen.action';
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
		
		document.getElementById('paraFrm_myPageEmpConf').value=id;
		document.getElementById('paraFrm').action='EmpConfForPension_viewEmpOnScreen.action';
		document.getElementById('paraFrm').submit();
		 
	}
	
	
</script>