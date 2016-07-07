<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script language="JavaScript" type="text/javascript"
	src="..pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="EmployeeScoreCalculation" validate="true" id="paraFrm"  theme="simple">
	<div align="center" id="overlay"
		style="z-index: 3; 
		position: absolute; 
		width: 790px; 
		height: 920px; 
		margin: 0px; 
		left: 0; 
		top: 100; 
		background-color: #A7BEE2; 
		filter: progid :   DXImageTransform .   Microsoft .   alpha(opacity =   15); -moz-opacity: .1; opacity: .1; display: none;">
	</div>
	
	<div id="confirmationDiv"
		style='position: absolute; 
		z-index: 3; 100 px; 
		height: 150px; 
		visibility: hidden; 
		top: 200px; 
		left: 100px;'>
	</div>
	
	<table width="100%" border="0" align="center" cellpadding="0"
		cellspacing="0" class="formbg">
		<s:hidden name="myPage" id="myPage" />
		<s:hidden name="searchFlag" />
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Employee
					Score Calculation</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td  colspan="3" width="100%"><input type="button" class="save"
						onclick="return saveScore();" value=" Save " target = "_self"/>&nbsp;
						<input type="button" class="token"
						onclick="return recalculate();" value=" Recalculate " target = "_self" />
						</td>
		</tr>
		<tr>
			<td  colspan="3" width="100%">
			<table width="100%" class="formbg" border="0">
				<tr>
					<td colspan="6" class="text_head"><strong
						class="forminnerhead">Select Filters </strong>
						</td>
				</tr>
				<tr>
					<td width="10%" nowrap="nowrap"><label
						name="apprId" id="apprId1"
						ondblclick="callShowDiv(this);"><%=label.get("apprId")%></label>
					<font color="red">*</font>:</td>
					<td width="20%">
					<s:hidden name="appraisalId"/>
					<s:textfield name="appraisalCode" />
					<img id="ctrlHide"
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'EmployeeScoreCalculation_f9appraisal.action');">
					<td width="10%" nowrap="nowrap"><label
						name="dept" id="dept1"
						ondblclick="callShowDiv(this);"><%=label.get("dept")%></label>
					:</td>
					<td width="20%">
					<s:hidden name="departmentId"/>
					<s:textfield name="departmentName" size="15"/>
					<img id="ctrlHide"
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'EmployeeScoreCalculation_f9department.action');">
					</td>
					<td width="10%" nowrap="nowrap"><label
						name="branch" id="branch1"
						ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
					:</td>
					<td width="20%">
					<s:hidden name="branchId"/>
					<s:textfield name="branchName" size="15"/>
					<img id="ctrlHide"
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'EmployeeScoreCalculation_f9branch.action');">
					</td>
				</tr>
				<tr>
					<td align="center" colspan="6"><input type="button" class="token"
						onclick="return searchRecord();" value=" View " />&nbsp; <input
						type="button" class="token" onclick="return clearFilter();"
						value=" Clear " /></td>
				</tr>
			</table>
			</td>
		</tr>
		<!--
		<tr>
			<td>
			<table width="100%">
				<tr>
					<td><a href="#" onclick="setAction('p')">Pending List </a> | <a
						href="#" onclick="setAction('f')">Finalized List</a></td>
				</tr>
			</table>
			</td>
		</tr>
		-->
		<s:if test="%{listType == 'pending'}">
			<tr>
				<td colspan="3">
				<table width="100%" border="0" class="formbg">
					<tr>
						<td width="40%"><b>Employee List</b></td>
						<%
							int totalPage = 0;
							int pageNo = 0;
						%>

						<td id="ctrlShow" width="100%" align="right"><b>Page:</b> <%
 	totalPage = (Integer) request.getAttribute("totalPage");
 	pageNo = (Integer) request.getAttribute("pageNo");
 %> <a href="#"
							onclick="callPage('1', 'F', '<%=totalPage%>', 'EmployeeScoreCalculation_getEmployeeListByAppraisalID.action');">
						<img title="First Page" src="../pages/common/img/first.gif"
							width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('P', 'P', '<%=totalPage%>', 'EmployeeScoreCalculation_getEmployeeListByAppraisalID.action');">
						<img title="Previous Page" src="../pages/common/img/previous.gif"
							width="10" height="10" class="iconImage" /> </a> <input type="text"
							name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
							maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'EmployeeScoreCalculation_getEmployeeListByAppraisalID.action');return numbersOnly();" />
						of <%=totalPage%> <a href="#"
							onclick="callPage('N', 'N', '<%=totalPage%>', 'EmployeeScoreCalculation_getEmployeeListByAppraisalID.action')">
						<img title="Next Page" src="../pages/common/img/next.gif"
							class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'EmployeeScoreCalculation_getEmployeeListByAppraisalID.action');">
						<img title="Last Page" src="../pages/common/img/last.gif"
							width="10" height="10" class="iconImage" /> </a></td>
					</tr>
				<tr>
				<td colspan="3">
				<table width="100%" class="sorttable">
					<tr>
						<td class="formth" width="5%"><b><label class="set"
							name="sno" id="sno1" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
						</td>
						<td class="formth" width="15%"><b><label class="set"
							name="empId" id="empId1" ondblclick="callShowDiv(this);"><%=label.get("empId")%></label></b>
						</td>
						<td class="formth" width="40%"><b><label class="set"
							name="empName" id="empName1" ondblclick="callShowDiv(this);"><%=label.get("empName")%></label></b>
						</td>
						<td class="formth" width="10%"><b><label class="set"
							name="score" id="score1" ondblclick="callShowDiv(this);"><%=label.get("score")%></label></b>
						</td>
						<td class="formth" width="20%"><b><label class="set"
							name="modScore" id="modScore1" ondblclick="callShowDiv(this);"><%=label.get("modScore")%></label></b>
						</td>
						<td class="formth" width="20%"><b><label class="set"
							name="reviewScore" id="reviewScore1" ondblclick="callShowDiv(this);"><%=label.get("reviewScore")%></label></b>
						</td>
					</tr>
					<s:if test="pendingLength">
						<%
						int count = 0;
						%>
						<%
						int cn = pageNo * 20 - 20;
						%>
						<s:iterator value="pendingScoreList">
							<tr>
								<td class="sortableTD align="center"><%=++cn%> <s:hidden name="appraisalIdItt" /></td>
								<td class="sortableTD">
								<input type="hidden" name="employeeIdItt" id="paraFrm_employeeIdItt<%=count%>" 
								value='<s:property value="employeeIdItt"/>' />&nbsp;
								<s:property value="employeeTokenItt" /></td>
								<td class="sortableTD"><s:property value="employeeNameItt" />&nbsp;</td>
								<td class="sortableTD" align="center"><s:property
									value="employeeScoreItt" /></td>
								<td class="sortableTD" align="center">
									<input type="text" name="moderatedScoreItt" id="paraFrm_moderatedScoreItt<%=count%>" size="5" 
									value='<s:property value="moderatedScoreItt"/>' />
								</td>
								<td class="sortableTD" align="center"><s:property value="reviewPanelScoreItt" />&nbsp;</td>
							</tr>
						</s:iterator>
					</s:if>
					<s:if test="pendingLength"></s:if>
					<s:else>
						<tr align="center">
							<td colspan="7" class="sortableTD" width="100%"><font
								color="red">No Data to display</font></td>
						</tr>
					</s:else>
				</table>
				</td>
			</tr>
				</table>
				</td>
			</tr>
		</s:if>
		<tr>
			<td  colspan="3" width="100%"><input type="button" class="save"
						onclick="return saveScore();" value=" Save " /></td>
		</tr>
	</table>
</s:form>
<script>
	/*
	function setAction(listType){
		if(listType=="p"){
			document.getElementById('paraFrm').action='EmployeeScoreCalculation_input.action';
			document.getElementById('paraFrm').submit();
		} else if(listType=="f"){
			document.getElementById('paraFrm').action='EmployeeScoreCalculation_getProcessedList.action';
			document.getElementById('paraFrm').submit();
		 }
	}
	*/
	function clearFilter(){
		document.getElementById('paraFrm_searchFlag').value=false;  
		document.getElementById('paraFrm_appraisalId').value="";  
		document.getElementById('paraFrm_appraisalCode').value="";  
		document.getElementById('paraFrm_departmentId').value="";  
		document.getElementById('paraFrm_departmentName').value=""; 
		document.getElementById('paraFrm_branchId').value="";  
		document.getElementById('paraFrm_branchName').value="";  
		
		document.getElementById('paraFrm').action = 'EmployeeScoreCalculation_input.action';
		document.getElementById('paraFrm').submit();
	}
	
	function searchRecord(){
		if(document.getElementById('paraFrm_appraisalCode').value==""){
			alert("Please select appraisal code");
			return false;
		}else{alert("here");
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm_searchFlag').value=true;  
			document.getElementById('paraFrm').action = 'EmployeeScoreCalculation_getEmployeeListByAppraisalID.action';
			document.getElementById('paraFrm').submit();
			
		}
	}
	
	function saveScore(){
		if(document.getElementById('paraFrm_appraisalCode').value==""){
			alert("Please select appraisal code");
			return false;
		}else if(document.getElementById('paraFrm_searchFlag').value=="false"){
			alert("Please click view to display employee list");
			return false;
		}else{
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm_searchFlag').value=true;  
			document.getElementById('paraFrm').action = 'EmployeeScoreCalculation_saveModerateScore.action';
			document.getElementById('paraFrm').submit();
			
		}	
	}
	
	function showConfirmationDiv(){
		displayConfirmDiv();
		document.getElementById('confirmationDiv').style.display='block';
	}
	
	function callPage(id, pageImg, totalPageHid, action) {
		
		pageNo = document.getElementById('pageNoField').value;	
		actPage = document.getElementById('myPage').value; 
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoField').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoField').value = actPage;
			    document.getElementById('pageNoField').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoField').value=actPage;
			    document.getElementById('pageNoField').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        }else{
		   		displayConfirmDiv();
				document.getElementById('confirmationDiv').style.display='block';
			} 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoField').focus();
	         	return false;
	        }else{
		   		displayConfirmDiv();
				document.getElementById('confirmationDiv').style.display='block';
			}
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoField').focus();
	         	return false;
	        }else{
		   		displayConfirmDiv();
				document.getElementById('confirmationDiv').style.display='block';
			}
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoField').focus();
	         	return false;
			}else{
		   		displayConfirmDiv();
				document.getElementById('confirmationDiv').style.display='block';
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoField').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoField').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPage').value = id;
		 
	}
	
	
	function displayConfirmDiv(){
		document.getElementById("confirmationDiv").style.visibility = 'visible';
		document.getElementById("confirmationDiv").innerHTML = '<table width=500 height=100 border=0 class=formbg>'
		+'<tr><td class="txt"><b><center>Please select anyone of the option given below</td></tr>'
		+'<tr><td><b><center><input type="button" value="Proceed With Save" onclick="proceedWithSave();" class="token"/>'
		+'&nbsp;<input type="button" class="token" value="Proceed Without Save" onclick="proceedWithoutSave();"/>'
		+'&nbsp;<input type="button" class="token" value="Cancel" onclick="cancel();"/></td></tr></table>';
 		document.getElementById("overlay").style.display = "block";
	}
	
	function proceedWithSave(){
		try{
		document.getElementById("confirmationDiv").style.visibility = 'hidden';
		document.getElementById('confirmationDiv').style.display='none';
		document.getElementById("overlay").style.display = "none";
		enableBlockDiv();
		document.getElementById('paraFrm').action="EmployeeScoreCalculation_saveModerateScore.action";
		document.getElementById('paraFrm').submit();
		}
		catch(e){}
	}
	function proceedWithoutSave(){
		try{
		enableBlockDiv();
		document.getElementById("confirmationDiv").style.visibility = 'hidden';
		document.getElementById('confirmationDiv').style.display='none';
		document.getElementById("overlay").style.display = "none";
		document.getElementById('paraFrm').action="EmployeeScoreCalculation_getEmployeeListByAppraisalID.action";
		document.getElementById('paraFrm').submit();
		}catch(e){}
	}
	
	function cancel(){
		document.getElementById("confirmationDiv").style.visibility = 'hidden';
		document.getElementById("overlay").style.display = "none";
	}

	function enableBlockDiv(){
		try{
			document.getElementById("overlay").style.visibility = "visible";
			document.getElementById("overlay").style.display = "block";
		}catch(e){}
	  }
	function disableBlockDiv(){
  		try{
			document.getElementById("overlay").style.visibility = "hidden";
			document.getElementById("overlay").style.display = "none";
			}catch(e){
				document.getElementById("overlay").style.visibility = "hidden";
				document.getElementById("overlay").style.display = "none";
			}
	  }
	  
	function recalculate(){
		document.getElementById('paraFrm').action="EmployeeScoreCalculation_recalculateScore.action";
		document.getElementById('paraFrm').submit();
	}	
</script>
