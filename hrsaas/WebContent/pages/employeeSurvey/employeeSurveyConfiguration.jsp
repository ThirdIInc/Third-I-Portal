<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="EmployeeSurveyConfiguration" method="post" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">

		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Employee
					Survey Configuration </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>



		<s:hidden name="show" value="%{show}" />
		<s:hidden name="hiddencode" />
		<s:if test="listFlag">
			<s:hidden name="surveyCode" />
			<s:hidden name="surveyName" />
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr class="formbg">
						<td width="20%" class="formtxt"></td>
						<%
							int totalPage = (Integer) request.getAttribute("totalPage");
							int pageNo = (Integer) request.getAttribute("pageNo");
						%>
						<!-- Paging implementation -->
						<td id="showCtrl" width="80%" align="right"><s:if
							test="modeLength">
							<b>Page:</b>
							<a href="#"
								onclick="callPage('1', 'F', '<%=totalPage%>', 'EmployeeSurveyConfiguration_input.action');">
							<img title="First Page" src="../pages/common/img/first.gif"
								width="10" height="10" class="iconImage" /> </a>&nbsp;
						<a href="#"
								onclick="callPage('P', 'P', '<%=totalPage%>', 'EmployeeSurveyConfiguration_input.action');">
							<img title="Previous Page" src="../pages/common/img/previous.gif"
								width="10" height="10" class="iconImage" /> </a>
							<input type="text" name="pageNoField" id="pageNoField" size="3"
								value="<%=pageNo%>" maxlength="10"
								onkeypress="callPageText(event, '<%=totalPage%>', 'EmployeeSurveyConfiguration_input.action');return numbersOnly();" /> of <%=totalPage%>
							<a href="#"
								onclick="callPage('N', 'N', '<%=totalPage%>', 'EmployeeSurveyConfiguration_input.action')">
							<img title="Next Page" src="../pages/common/img/next.gif"
								class="iconImage" /> </a>&nbsp;
						<a href="#"
								onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'EmployeeSurveyConfiguration_input.action');">
							<img title="Last Page" src="../pages/common/img/last.gif"
								width="10" height="10" class="iconImage" /> </a>
						</s:if></td>

					</tr>
				</table>
				</td>
			</tr>





			<tr>
				<td>
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="sortable">
					<tr class="td_bottom_border">
						<s:hidden name="myPage" id="myPage" />
						<td width="20%" valign="top" class="formth"><label
							name="serial.no" id="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
						<td width="80%" valign="top" class="formth"><label
							name="emp.survey" id="emp.survey1"
							ondblclick="callShowDiv(this);"><%=label.get("emp.survey")%></label></td>


					</tr>

					<s:if test="noData">
						<tr>
							<td width="100%" colspan="7" align="center"><font
								color="red">No Data To Display</font></td>
						</tr>
					</s:if>

					<%
					int count = 0;
					%>
					<%!int d = 0;%>
					<%
						int t = 0;
						int cn = 1 * 20 - 20;
					%>
					<s:iterator value="tableList">

						<tr class="sortableTD" <%if(count%2==0){
							%>
							class="tableCell1" <%}else{%> class="tableCell2" <%	}count++; %>
							onmouseover="javascript:newRowColor(this);"
							title="Double click for edit"
							onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
							ondblclick="javascript:callForEdit('<s:property  value="hiddenAutoCodeItt"/>');">
							<td class="sortableTD" width="20%"><%=++cn%> <%
 ++t;
 %>
							</td>
							<td class="sortableTD" width="80%"><s:property
								value="surveyNameItt" /></td>
						</tr>
					</s:iterator>

					<tr>
						<td colspan="7" align="right"><strong>Total Records:
						</strong><s:property value="totalRecords" /></td>
					</tr>
					<%
					d = t;
					%>
				</table>
				</td>
			</tr>

		</s:if>

		<s:if test="listFlag"></s:if>

		<s:else>



			<tr>
				<td colspan="4">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">


					<tr>
						<td colspan="1" width="20%" class="formtext" height="22"><label
							name="emp.survey" id="emp.survey" ondblclick="callShowDiv(this);"><%=label.get("emp.survey")%></label>
						<font color="red">*</font> :</td>
						<td colspan="3" width="80%"><s:hidden name="surveyCode" /> <s:textfield
							name="surveyName" theme="simple" size="25" readonly="true" /> <img
							src="../pages/images/recruitment/search2.gif" height="18"
							class="iconImage" align="absmiddle" width="18"
							onclick="javascript:callsF9(500,325,'EmployeeSurveyConfiguration_f9survey.action');">
						</td>
					</tr>
					<tr>
						<td colspan="1" width="20%" class="formtext" height="22"><label
							name="division" id="" division"" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
						:</td>
						<td colspan="1" width="30%"><s:hidden name="divCode"
							value="%{divCode}" theme="simple" /> <s:textfield name="divName"
							theme="simple" size="25" readonly="true" /> <img
							src="../pages/images/recruitment/search2.gif" height="18"
							class="iconImage" align="absmiddle" width="18"
							onclick="javascript:callsF9(500,325,'EmployeeSurveyConfiguration_f9division.action');">
						</td>
						<td colspan="1" width="20%" class="formtext"><label
							name="department" id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
						:</td>
						<td colspan="1" width="30%"><s:hidden name="deptCode"
							value="%{deptCode}" theme="simple" /> <s:textfield
							name="deptName" theme="simple" size="25" readonly="true" /> <img
							src="../pages/images/recruitment/search2.gif" height="18"
							align="absmiddle" width="18" class="iconImage"
							onclick="javascript:callsF9(500,325,'EmployeeSurveyConfiguration_f9department.action');">

						</td>

					</tr>

					<tr>
						<td colspan="1" width="20%" class="formtext" height="22"><label
							name="branch" id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
						:</td>
						<td colspan="1" width="30%"><s:hidden name="branchCode"
							value="%{branchCode}" theme="simple" /> <s:textfield
							name="branchName" theme="simple" size="25" readonly="true" /> <img
							src="../pages/images/recruitment/search2.gif" height="18"
							class="iconImage" align="absmiddle" width="18"
							onclick="javascript:callsF9(500,325,'EmployeeSurveyConfiguration_f9branch.action');">
						</td>

						<td colspan="1" width="20%" class="formtext"><label
							name="designation" id="designation"
							ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
						:</td>
						<td colspan="1" width="30%"><s:hidden name="desgCode"
							value="%{desgCode}" theme="simple" /> <s:textfield
							name="desgName" theme="simple" size="25" readonly="true" /> <img
							src="../pages/images/recruitment/search2.gif" height="18"
							class="iconImage" align="absmiddle" width="18"
							onclick="javascript:callsF9(500,325,'EmployeeSurveyConfiguration_f9designation.action');">
						</td>
					</tr>

					<tr>
						<td colspan="1" width="20%" class="formtext" height="22"></td>
						<td colspan="1" align="center" width="30%"><input
							type="button" name="add" class="add" value=" Add to List "
							onclick="return addtoList();" /></td>

						<td colspan="1" width="20%" class="formtext"></td>
						<td colspan="1" width="30%"></td>
					</tr>




				</table>
				</td>
			</tr>
			<tr>
				<td>
				<table width="100%" border="0" align="center" cellpadding="2"
					cellspacing="2" class="formbg">
					<tr>
						<td colspan="3" class="text_head"><strong
							class="forminnerhead">Add Employee</strong></td>
					</tr>

					<tr>
						<td colspan="1" width="20%"><label class="set"
							name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
						<td colspan="3" width="80%"><s:hidden name="employeeCode"
							value="%{employeeCode}" /><s:textfield readonly="true" size="15"
							name="employeeToken" /> <s:textfield size="50"
							name="employeeName" label="%{getText('employeeName')}"
							readonly="true" /> <img class="iconImage" id="ctrlHide"
							src="../pages/images/recruitment/search2.gif" width="16"
							height="15" border="0" onclick="javascript:callEmployee();" /> <s:submit
							name="add" cssClass="add"
							action="EmployeeSurveyConfiguration_addEmployee" value=" Add"
							onclick="return callAdd();" /></td>

					</tr>



				</table>
				</td>
			</tr>

			<s:if test="showFlag">

				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr class="formbg">
							<td width="20%" class="formtxt"></td>
							<%
								int totalEmp = (Integer) request.getAttribute("totalPageEmp");
								 
								int pageNoEmp = (Integer) request.getAttribute("pageNoEmp");
							 
							%>
							<!-- Paging implementation -->
							<td id="showCtrl" width="80%" align="right"><s:if
								test="modeLengthEmpFlag">
								<s:hidden name="myPageEmp" id="myPageEmp" />
								<b>Page:</b>
								<a href="#"
									onclick="callPageForEmpList('1', 'F', '<%=totalEmp%>', 'EmployeeSurveyConfiguration_getEmployeeList.action');">
								<img title="First Page" src="../pages/common/img/first.gif"
									width="10" height="10" class="iconImage" /> </a>&nbsp;
						<a href="#"
									onclick="callPageForEmpList('P', 'P', '<%=totalEmp%>', 'EmployeeSurveyConfiguration_getEmployeeList.action');">
								<img title="Previous Page"
									src="../pages/common/img/previous.gif" width="10" height="10"
									class="iconImage" /> </a>
								<input type="text" name="pageNoField1" id="pageNoField1"
									size="3" value="<%=pageNoEmp%>" maxlength="10"
									onkeypress="callPageTextForEmpList(event, '<%=totalEmp%>', 'EmployeeSurveyConfiguration_getEmployeeList.action');return numbersOnly();" /> of <%=totalEmp%>
								<a href="#"
									onclick="callPageForEmpList('N', 'N', '<%=totalEmp%>', 'EmployeeSurveyConfiguration_getEmployeeList.action')">
								<img title="Next Page" src="../pages/common/img/next.gif"
									class="iconImage" /> </a>&nbsp;
						<a href="#"
									onclick="callPageForEmpList('<%=totalEmp%>', 'L', '<%=totalEmp%>', 'EmployeeSurveyConfiguration_getEmployeeList.action');">
								<img title="Last Page" src="../pages/common/img/last.gif"
									width="10" height="10" class="iconImage" /> </a>
							</s:if></td>

						</tr>
					</table>
					</td>
				</tr>


				<tr>
					<td colspan="6">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								class="formbg">

								<tr>
									<td width="5%" class="formth" nowrap="nowrap">Sr No.</td>
									<td width="10%" class="formth" nowrap="nowrap">Employee Id</td>
									<td width="75%" class="formth" nowrap="nowrap">Employee
									Name</td>

									<td align="right" width="10%" class="formth" nowrap="nowrap">
									Remove</td>

								</tr>
								<%
								int i = 0;
								%>

								<%!int t = 0;%>

								<%
									int serialNumber = 0;

									serialNumber = pageNoEmp * 20 - 20;
								%>

								<s:iterator value="list">



									<tr class="sortableTD">
										<td width="5%" class="sortableTD"><%=++serialNumber%></td>
										<td width="10%" class="sortableTD"><s:property
											value="empToken" /><s:hidden name="empToken" /> <s:hidden
											name="employeeId" /></td>
										<td width="25%" class="sortableTD"><s:property
											value="empName" /><s:hidden name="empName" /></td>
										<td width="10%" align="center" class="sortableTD"><input
											type="button" class="rowDelete"
											onclick="callForDelete('<s:property value="employeeId"/>')" /></td>

									</tr>
									<%
									i++;
									%>

								</s:iterator>

								<%
								t = i;
								%>

								<%
								if (i == 0) {
								%>
								<tr>
									<td colspan="6" align="center"><font color="red">
									No data to display</font></td>
								</tr>
								<%
								}
								%>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>

			</s:if>

		</s:else>
		<tr>
			<td width="100%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>

	</table>
	<s:hidden name="hiddenEdit" />
	<s:hidden name="mailText" />

</s:form>

<script>

window.onload = document.getElementById('pageNoField').focus();



function publishFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'EmployeeSurveyConfiguration_publish.action';
		document.getElementById('paraFrm').submit();
	}
	
	
	function publishwithmailFun() {
	
		var mailMessageWindow = window.open('', 'mailMessageWindow', 'width=800, height=400, top=150, left=0, scrollbars=yes, resizable=yes, menubar=no, toolbar=no, status=yes');	 
		document.getElementById('paraFrm').target = "mailMessageWindow";
		document.getElementById("paraFrm").action = 'EmployeeSurveyConfiguration_publishWithMailAlert.action';
    	document.getElementById("paraFrm").submit();
    	document.getElementById('paraFrm').target = 'main';
	 
	}
	
	
	function callWindow1(fieldName,windowName,readFlag,charCount,maxLength) {
	   mytitle=document.getElementById(windowName).innerHTML;
	   
	    if (window.event){
		window.open('../pages/employeeSurvey/test.jsp?fieldName='+fieldName+'&windowName='+mytitle+'&charCntName='+charCount+'&maxLength='+maxLength+'&readFlag='+readFlag+'','','width=580,height=430,scrollbars=no,resizable=no,top=250,left=350');
		 }else{
		 window.open('../pages/employeeSurvey/test.jsp?fieldName='+fieldName+'&windowName='+mytitle+'&charCntName='+charCount+'&maxLength='+maxLength+'&readFlag='+readFlag+'','','width=650,height=450,scrollbars=no,resizable=no,top=250,left=350');
		 }
		document.getElementById('paraFrm').target ="";	 
	}
	
	
function callAdd()
{
	 
	var employee = document.getElementById('paraFrm_employeeCode').value;
	 	if(employee=="")
		{
		alert("Please select employee");
		return false;
		}
		 return true;

}


function callForEdit(id){
	//  alert(id);
		callButton('NA', 'Y', 2);
	  	try{
	  	//var resignCode = document.getElementById('resignCodeItt'+id).value;
	  	document.getElementById('paraFrm_hiddencode').value=id;
		document.getElementById("paraFrm").action="EmployeeSurveyConfiguration_callforedit.action?autoCode="+id; 
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
	    }catch(e){
	    	alert(e);
	    }
	}
	

	function resetFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'EmployeeSurveyConfiguration_reset.action';
		document.getElementById('paraFrm').submit();
	}

function deleteFun() {

var surveyCode = document.getElementById('paraFrm_surveyCode').value;
	
	if(surveyCode=="")
	{
			alert("Please select the record .");
			return false;
	}
	
	var conf=confirm("Do you really want to delete this record ?");
		 	if(conf)
			{
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action = 'EmployeeSurveyConfiguration_delete.action';
			document.getElementById('paraFrm').submit();
			}
			 else
			 {
			 return false; 
			 }
	 return true;
	}
 


function addnewFun() {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'EmployeeSurveyConfiguration_addNew.action';
		document.getElementById('paraFrm').submit();
	}

function searchFun() {
		if(navigator.appName == 'Netscape') {
			var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
		} else {
			var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		}
		
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action = 'EmployeeSurveyConfiguration_f9searchAction.action';
		document.getElementById("paraFrm").submit();
	}
	
	
	function saveFun() {
 
 		var value='<%=t%>'
  	if(value=='0')
 		{
		 		alert("There is no data to save.");
				return false;
 		}
 		
	 
  var survey =document.getElementById('paraFrm_surveyCode').value;
 
 if(survey=="")
		{
				alert("Please select survey.");
				return false;
		}
 
  	for (var i = 0; i < document.all.length; i++) {
			if(document.all[i].id == 'save') {
			//alert(document.all[i]);
			document.all[i].disabled=true;
		}
		}
		
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'EmployeeSurveyConfiguration_save.action';
		document.getElementById('paraFrm').submit();
	}
	
	
	 function backFun() {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'EmployeeSurveyConfiguration_input.action';
		document.getElementById('paraFrm').submit();
	}
	
	
	function addtoList() {
	 
	 var survey =document.getElementById('paraFrm_surveyCode').value;
 
 	if(survey=="")
		{
				alert("Please select survey.");
				return false;
		}
		
	   
			for (var i = 0; i < document.all.length; i++) {
			if(document.all[i].id == 'process') {
			//alert(document.all[i]);
			document.all[i].disabled=true;
		}
		}
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'EmployeeSurveyConfiguration_process.action';
		document.getElementById('paraFrm').submit();
	}
  
 

 function callForDelete(id)
	   {
	  	  var conf=confirm("Do you really want to delete this record ?");
		 	if(conf)
			{
			document.getElementById('paraFrm_hiddenEdit').value=id;
   		document.getElementById("paraFrm").action="EmployeeSurveyConfiguration_deleteData.action";
		document.getElementById("paraFrm").submit();
			}
			 else
			 {
			 return false; 
			 }
	 return true;
	 	 
	   
   		}

   		function callEmployee(){
	  
		callsF9(500,325,'EmployeeSurveyConfiguration_f9employee.action');
	}
	
	
	
	// APPROVED CANCELLATION APPLICATIONS LIST
function callPageForEmpList(id, pageImg, totalPageHid, action) {		
		
		try{
		pageNo = document.getElementById('pageNoField1').value;	
		actPage = document.getElementById('myPageEmp').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoField1').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoField1').value = actPage;
			    document.getElementById('pageNoField1').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoField1').value=actPage;
			    document.getElementById('pageNoField1').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoField1').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoField1').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoField1').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoField1').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoField1').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoField1').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageEmp').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		
		}catch(e){alert("value of e------------"+e);} 
	}
	
	
	
	function callPageTextForEmpList(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo = document.getElementById('pageNoField1').value;	
		actPage = document.getElementById('myPageEmp').value; 
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoField1').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoField1').focus();
		     document.getElementById('pageNoField1').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField1').focus();
		     document.getElementById('pageNoField1').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoField1').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageEmp').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}

</script>


