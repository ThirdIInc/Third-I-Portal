<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>

<script type="text/javascript">
	var eadd = new Array();
</script>

<s:form action="ExtraWorkApplication" validate="true" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Extra
					Work Benefits Application</strong></td>

					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:hidden name="myPageRejected" />
		<s:hidden name="myPage" />
		<s:hidden name="checkRemove" />
		<s:hidden name="leaveApplication.isApprovalClick" />
		<s:hidden name="checkApproveRejectStatus" />
	    <s:hidden name="flagForward"/>
		<s:hidden name="source" id="source" /> 

		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>

			</table>
			</td>
		</tr>


		<!-- TABLE FOR APPROVER COMMENTS START-->
		<s:if test="prevAppCommentFlag">
			<tr>
				<td>
				<table width="100%" border="0" cellpadding="1" cellspacing="0"
					class="formbg">
					<tr>
					<s:if test="apprCommentsCheck">
						<td width="138" colspan="1">Approver Comments:</td>
						<td colspan="3">
							<s:textarea theme="simple" cols="100" rows="3"
								name="approverComments" />
					   </td>
						<td width="175" colspan="1"></td>
						<td colspan="1"></td>
						</s:if>
					</tr>


				</table>
				</td>
			</tr>
		</s:if>
		<!-- TABLE FOR APPROVER COMMENTS ENDS-->
		<!-- TABLE FOR PREVIOUS APPROVER COMMENTS START-->

		<s:if test="prevAppCommentListFlag">
			<tr>
				<td colspan="7">
				<table width="100%" border="0" cellpadding="1" cellspacing="0"
					class="formbg">
					<tr>
						<td width="100%" colspan="7">
						<table width="100%" border="0" cellpadding="1" cellspacing="1"
							class="sortable">
							<tr>
								<td width="100%" nowrap="nowrap" colspan="7"><strong>
								Approver Details :</strong></td>

							</tr>
							<tr>
								<td class="formth" width="10%" height="22" valign="top">Sr.No.</td>
								<td class="formth" width="15%" height="22" valign="top">Approver
								ID</td>
								<td class="formth" width="25%" height="22" valign="top">
								Approver Name</td>
								<td class="formth" width="10%" height="22" valign="top">
								Date</td>
								<td class="formth" width="10%" height="22" valign="top">Status
								</td>
								<td class="formth" width="30%" height="22" valign="top">Comments
								</td>

							</tr>

							<%
							int i = 0;
							%>
							<%
							int k = 1;
							%>
							<s:iterator value="approverCommentList" status="stat">
								<tr>
									<td width="10%" class="sortableTD"><%=k%><s:hidden
										name="appSrNo" value="%{<%=k%>}" /></td>
									<td width="15%" class="sortableTD"><s:property
										value="prevApproverID" /><s:hidden name="prevApproverID" /></td>
									<td width="25%" class="sortableTD"><s:property
										value="prevApproverName" /><s:hidden name="prevApproverName" /></td>
									<td width="10%" class="sortableTD" align="center"><s:property
										value="prevApproverDate" /><s:hidden name="prevApproverDate" /></td>
									<td width="10%" class="sortableTD">&nbsp;<s:property
										value="prevApproverStatus" /><s:hidden
										name="prevApproverStatus" /></td>
									<td width="30%" class="sortableTD">&nbsp;<s:property
										value="prevApproverComment" /><s:hidden
										name="prevApproverComment" /></td>
								</tr>
								<%
								k++;
								%>
							</s:iterator>
							<%
								i = k;
								k = 0;
							%>

						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>

		</s:if>


		<!-- TABLE FOR PREVIOUS APPROVER COMMENTS END-->

		<!-- EMPLOYEE DETAILS TABLE STARTS-->
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<s:if test="%{generalFlag}">
						<td width="141"><label id="employee" name="employee"
							onDblClick="callShowDiv(this);"><%=label.get("employee")%></label>
						<font color="red">*</font>:</td>
						<td width="22"><s:textfield name="empToken"
							size="25" readonly="true" cssStyle="background-color: #F2F2F2;" /><s:hidden
							name="compId" /></td>
						<td colspan="3" nowrap="nowrap"><s:textfield size="70"
							name="empName" readonly="true"
							cssStyle="background-color: #F2F2F2;" /><s:hidden
							name="checkEdit" /></td>
						<td width="30" nowrap="nowrap"><s:hidden
							name="empId" /></td>
					</s:if>
					<s:else>
						<td width="158"><label id="employee" name="employee"
							onDblClick="callShowDiv(this);"><%=label.get("employee")%></label>
						<font color="red">*</font>:</td>
						<td width="21"><s:textfield name="empToken"
							size="25" readonly="true" cssStyle="background-color: #F2F2F2;" /><s:hidden
							name="compId" /></td>
						<td colspan="3" nowrap="nowrap"><s:textfield size="70"
							name="empName" readonly="true"
							cssStyle="background-color: #F2F2F2;" /><s:hidden
							name="checkEdit" /></td>
						<td width="44" nowrap="nowrap"><s:hidden
							name="empId" /><s:if test="isExtApp">
							<img src="../pages/images/recruitment/search2.gif"
								class="iconImage" height="14" width="14"
								onClick="javascript:callsF9(500,325,'ExtraWorkApplication_f9Employee.action');">
						</s:if></td>
					</s:else>
				</tr>
				<tr>
					<td width="141" colspan="1"><label class="set" id="branch"
						name="branch" onDblClick="callShowDiv(this);"><%=label.get("branch")%></label>
					:</td>
					<td colspan="2"><s:textfield size="25"
						name="branchName" readonly="true"
						cssStyle="background-color: #F2F2F2;" /></td>
					<td width="102" colspan="1"><label class="set" id="department"
						name="department" onDblClick="callShowDiv(this);"><%=label.get("department")%></label>
					:</td>
					<td colspan="2"><s:textfield size="25" maxlength="40"
						name="dept" readonly="true"
						cssStyle="background-color: #F2F2F2;" /></td>
				</tr>
				<tr>
					<td width="141" colspan="1"><label class="set"
						id="designation" name="designation"
						onDblClick="callShowDiv(this);"><%=label.get("designation")%></label>
					:</td>
					<td colspan="2"><s:textfield size="25" name="desg"
						readonly="true" cssStyle="background-color: #F2F2F2;" /></td>
					<td width="102" colspan="1"><label class="set"
						id="app.dateate" name="app.dateate"
						onDblClick="callShowDiv(this);"><%=label.get("app.dateate")%></label>
					<font color="red">*</font>:</td>
					<td colspan="2"><s:textfield size="25"
						name="appDate" readonly="true"
						cssStyle="background-color: #F2F2F2;"
						onblur="return validateDate('paraFrm_appDate','Date');"
						onkeypress="return numbersWithHiphen();" maxlength="10" /> <s:a
						href="javascript:NewCal('paraFrm_appDate','DDMMYYYY');">
						<s:if test="isExtApp">
							<img src="../pages/images/recruitment/Date.gif" class="iconImage"
								height="18" align="absmiddle" width="18">
						</s:if>
					</s:a></td>
				</tr>
				<tr>
					<td width="141" colspan="1"><label class="set" id="status"
						name="status" onDblClick="callShowDiv(this);"><%=label.get("status")%></label>
					:</td>
					<td colspan="2"><s:hidden name="hiddenStatus" /><s:select
						theme="simple" name="status" value="%{status}"
						list="#{'D':'Draft','P':'Pending','B':'Sent Back','A':'Approved','R':'Rejected','F':'Forwarded','W':'Withdrawn'}"
						disabled="true"></s:select></td>
					<td width="102" colspan="1"></td>
					<td colspan="2"></td>
				</tr>
				<tr>
					<td width="141" colspan="1"><label class="set" id="commenet"
						name="comment" onDblClick="callShowDiv(this);"><%=label.get("comment")%></label>
					:</td>
					<td colspan="3"><s:if test="isExtApp">
						<s:textarea theme="simple" name="comments" value="%{comments}"
							cols="60" rows="3" wrap="wrap"
							onkeyup="return callLength('isappcount');" />
					</s:if><s:else>
						<s:property value="comments" />
					</s:else></td>
					<td width="175" colspan="1" nowrap="nowrap"><s:if
						test="isExtApp">Remaining Chars
					<s:textfield name="isappcount" readonly="true" size="5" />(Max:500)</s:if>
					</td>
					<td colspan="1"></td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- EMPLOYEE DETAILS TABLE ENDS-->

		<!-- APPROVER LIST TABLE  STARTS -->
		<tr>
			<td colspan="7">
			<table width="100%" border="0" cellpadding="1" cellspacing="0"
				class="formbg">
				<tr>
					<td width="50%" nowrap="nowrap"><strong>The
					Approver(s) for this application :</strong></td>
					<td colspan="2" nowrap="nowrap"></td>

					<td width="11%" nowrap="nowrap"><strong>Keep Informed
					To : </strong></td>
					<td width="13%"><s:if test="isExtTypeApp">
						<s:hidden name="employeeId" />
						<s:hidden name="employeeToken" />
						<s:textfield name="employeeName" readonly="true" />
					</s:if></td>
					<td width="5%" colspan="1"><s:if test="isExtTypeApp">
						<img src="../pages/common/css/default/images/search2.gif"
							class="iconImage" width="16" height="15"
							onclick="getKeepInformedEmp();" />
					</s:if></td>
					<td width="15%"><s:if test="isExtTypeApp">
						<input type="button" value=" Add" class=" add"
							onclick="return callKeepInformed();" />
					</s:if></td>
				</tr>
				<!-- APPROVER LIST  TABLE  STARTS -->
				<tr valign="top">
					<td colspan="3" rowspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<%
							int y = 1;
							%>
							<%!int z = 0;%>
							<s:iterator value="approverList">
								<tr>
									<td><s:hidden name="approverName" /><STRONG><s:property
										value="srNoIterator" /></STRONG> <s:property value="approverName" /></td>

								</tr>
								<%
								y++;
								%>
							</s:iterator>
							<%
							z = y;
							%>
						</tr>
					</table>
					</td>

				</tr>

				<!-- APPROVER LIST  TABLE  ENDS -->
				<!-- KEEP INFORMED LIST TABLE  STARTS -->
				<tr valign="top">
					<td colspan="3" rowspan="5">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">

						<%
							int counter11 = 0;
							int counter2 = 0;
						%>

						<s:iterator value="keepInformedList" status="stat">

							<tr>

								<td width="80%"><%=++counter11%><s:hidden
									name="keepInformedSerialNo" /><s:hidden
									name="keepInformedEmpName" /><s:property
									value="keepInformedEmpName" /><s:hidden
									name="keepInformedEmpId" /></td>
								<td width="20%"><s:if test="%{application.approvalFlag}">
									<a href="#" onclick="callForRemove(<%=counter11%>);"><b>Remove</b></a>
								</s:if></td>
							</tr>
							<%
							counter2 = counter11;
							%>
						</s:iterator>
						<%
						counter2 = 0;
						%>
					</table>
					</td>
					<td></td>
				</tr>

			</table>
			</td>
		</tr>

		<!-- KEEP INFORMED LIST TABLE  ENDS -->

		<!-- EXTRA WORK DETAILS LIST TABLE STARTS -->
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td colspan="5" class="formhead"><strong class="forminnerhead">
					<label class="set" id="compoffdtl" name="compoffdtl"
						ondblclick="callShowDiv(this);"><%=label.get("compoffdtl")%></label></strong>
					</td>
				</tr>
				<s:if test="isExtApp">
					<tr>
						<td width="18%" colspan="1"><label class="set" id="compdate"
							name="compdate" ondblclick="callShowDiv(this);"><%=label.get("compdate")%></label><font
							color="red">*</font>:</td>
						<td width="19%" colspan="1"><s:textfield size="12"
							name="prDate" id="paraFrm_prDate"
							onblur="return validateDate('paraFrm_prDate','Extra Work Date');"
							onkeypress="return numbersWithHiphen();" maxlength="10" /> <s:a
							href="javascript:NewCal('paraFrm_prDate','DDMMYYYY');">
							<img src="../pages/images/recruitment/Date.gif" class="iconImage"
								height="18" align="absmiddle" width="18">
						</s:a></td>
						<td width="19%" colspan="1"><label class="set"
							id="extrawork.type" name="extrawork.type"
							ondblclick="callShowDiv(this);"><%=label.get("extrawork.type")%></label><font
							color="red">*</font>:</td>
						<td colspan="3"><s:select id="paraFrm_type"
							theme="simple" name="type"
							value="%{application.type}"
							list="#{'':'--Select--','F':'Full Day','H':'Half Day','O':'Partial Day'}" /></td>
					</tr>
					<tr>
						<td width="18%" colspan="1" nowrap="nowrap"><label
							class="set" id="sTime" name="sTime"
							ondblclick="callShowDiv(this);"><%=label.get("sTime")%></label>
						[HH24:MI]<font color="red">*</font>:</td>
						<td width="19%" colspan="1"><s:textfield size="12"
							name="startTime" id="paraFrm_startTime"
						 
							maxlength="5" onkeypress="return numbersWithColon();" /></td>
						<td width="19%" colspan="1" nowrap="nowrap"><label
							class="set" id="eTime" name="eTime"
							ondblclick="callShowDiv(this);"><%=label.get("eTime")%></label>
						[HH24:MI] <font color="red">*</font>:</td>
						<td colspan="3"><s:textfield size="12"
							name="endTime" id="paraFrm_endTime"
							 
							maxlength="5" onkeypress="return numbersWithColon();" /></td>
					</tr>

					<tr>
						<td width="18%" colspan="1"><label class="set" id="purpose"
							name="purpose" ondblclick="callShowDiv(this);"><%=label.get("purpose")%></label>
						<font color="red">*</font>:</td>
						<td colspan="2"><s:textarea theme="simple"
							id="paraFrm_prName" name="prName"
							value="%{application.prName}" cols="65" rows="3" wrap="wrap"
							onkeyup="return callLength('isappcounts');" /></td>
						<td width="22%" colspan="1" nowrap="nowrap">Remaining Chars <s:textfield
							name="isappcounts" readonly="true" size="5" /><br>
						(Max:200)</td>
						<td width="22%"></td>
					</tr>

					<s:if test="flagForward">
					<tr>
						<td colspan="5" align="center"><input type="button"
							class="add" value="   Add" onclick="return callAdd();" /> <input
							type="button" class="reset" value="  Clear"
							onclick="return callClear();" /></td>
					</tr>
					</s:if>
				</s:if>

				<tr>
					<td colspan="5"><s:hidden name="iteratorFlag" /> <s:if
						test="iteratorFlag">
						<table width="100%">
							<tr>
								<td class="formth" width="05%"><label id="sno" name="sno"
									ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></td>
								<td class="formth" width="15%" align="center"><label
									id="compdate1" name="compdate" ondblclick="callShowDiv(this);"><%=label.get("compdate")%></label></td>
								<td class="formth" width="15%"><label id="extrawork.type1"
									name="extrawork.type" ondblclick="callShowDiv(this);"><%=label.get("extrawork.type")%></label></td>
								<td class="formth" width="10%" align="center"><label
									id="sTime1" name="sTime" ondblclick="callShowDiv(this);"><%=label.get("sTime")%></label></td>
								<td class="formth" width="10%" align="center"><label
									id="eTime1" name="eTime" ondblclick="callShowDiv(this);"><%=label.get("eTime")%></label></td>
								<td class="formth" width="35%"><label id="purpose1"
									name="purpose" ondblclick="callShowDiv(this);"><%=label.get("purpose")%></label></td>
								<s:if test="%{application.approvalFlag}">
									<td class="formth" width="10%" align="center">Edit/Delete</td>
								</s:if>
							</tr>
							<%
							int srNo = 0;
							%>
							<s:iterator value="compList">
								<tr>
									<td class="sortableTD" width="5%"><%=++srNo%></td>
									<td class="sortableTD" width="15%" nowrap="nowrap"
										align="center"><s:property value="hDate" /></td>
									<td class="sortableTD" width="15%" nowrap="nowrap"
										align="center"><s:property value="hsType" /> <s:hidden
										name="hsType" /></td>
									<td class="sortableTD" width="10%" nowrap="nowrap"
										align="center"><s:property value="hsTime" /></td>
									<td class="sortableTD" width="10%" nowrap="nowrap"
										align="center"><s:property value="heTime" /> <s:hidden
										name="hprojName" /><s:hidden name="hDate"
										id='<%="hDate"+srNo%>' /> <s:hidden name="hDay"
										id='<%="hDay"+srNo%>' /><s:hidden name="hsTime" /><s:hidden
										name="heTime" /><s:hidden name="serialNo" /></td>
									<td class="sortableTD" width="35%" align="left"><s:property
										value="hprojName" /></td>
										<s:if
										test="%{application.approvalFlag}">
									<td class="sortableTD" width="10%" nowrap="nowrap">
										<input type="button" class="edit" value="Edit"
											onclick="return setEdit('<%=srNo-1%>','<s:property value="hprojName" />','<s:property 	value="hDate" />','<s:property value="hsType" />',
												'<s:property value="hsTime" />','<s:property value="heTime" />','<s:property value="hidMon" />','<s:property value="hyear" />');" />
										<input type="button" class="delete" value="  Delete"
											onclick="return setSerialNo(<%=(srNo-1)%>);" />
									 <script>
													eadd[<%=srNo%>] = document.getElementById('hDate'+<%=srNo%>).value; 
												</script></td>
									</s:if>			
								</tr>
							</s:iterator>
						</table>
					</s:if></td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- EXTRA WORK DETAILS LIST TABLE ENDS -->

		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%"></td>
				</tr>

			</table>
			</td>
		</tr>

	</table>
</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<script> 
	function setSerialNo(code) {
		var st = document.getElementById('paraFrm_status').value;
		if(!(st=="D" || st=="B")){
			var msg="You can not delete the";
			if(st=='P'){
				msg+=" submited";
			}
				if(st=='R'){
				msg+=" rejected";
			}
				if(st=='A'){
				msg+=" approved ";
			}
				if(st=='F'){
				msg+=" forwarded ";
			}
			alert(  msg+" application !");
			return false;
		}
	 
    	
    	var conf = confirm("Do you really want to delete this record ?");
  	 	if(conf) {  		 
			document.getElementById('paraFrm_serialNo').value = code;  			 
	        document.getElementById('paraFrm').action = 'ExtraWorkApplication_delItem.action';	        
	        document.getElementById('paraFrm').submit();		         
			return true;
  		} else {
			return false;
		}      
		return true;
	}

	function draftFun() { 
  		var empId = document.getElementById('paraFrm_empId').value;
  		var appDate = document.getElementById('paraFrm_appDate').value;
    	var compId = document.getElementById('paraFrm_compId').value;
	 
	 	/*if(compId != "") {
	   		alert("Please click on 'Update' button");
	   		return false;
	 	}*/
   
	 	if(empId == "") {
	   		alert("Please select " + document.getElementById('employee').innerHTML.toLowerCase());	   
	   		return false;
	 	}
	 	
	 	if(appDate == "") {
	   		alert("Please enter/select " + document.getElementById('app.dateate').innerHTML.toLowerCase());
	   		return false;
	 	}
	 
	   	if(eadd.length <= 0) {
	   		alert("Please add atleast one Record !");
	   		return false;
	 	}
	 	
 		var cmt =document.getElementById('paraFrm_comments').value;
 		if(cmt!="")
 		{
			var remain = 500 - eval(cmt.length);
				
			if(eval(remain)< 0){
			
			alert("Maximum characters for Comments field is 500 Remove Extra "+remain+"Characters.");
			return false;
			}
			
 		
 		}
 		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ExtraWorkApplication_save.action?checkStatus=D'; 
		document.getElementById('paraFrm').submit();
	 	
 		return true;
	}
	
	function sendforapprovalFun(){ 
		var empCode = document.getElementById('paraFrm_empId').value;
		if(empCode==""){
			alert (" Please fill the application");
			return false;
		}
		
		if(eadd.length <= 0) {
	   		alert("Please add atleast one Record !");
	   		return false;
	 	}
	 	
	 	
	 	document.getElementById('sendforapproval').disabled=true;
	 		
		/*for (var i = 0; i < document.all.length; i++) {
			if(document.all[i].id == 'sendforapproval') {
				//alert(document.all[i]);
				//document.all[i].value="Saving...";
				document.all[i].disabled=true;
			}
		}*/
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action ='ExtraWorkApplication_save.action?checkStatus=P'; 
		document.getElementById('paraFrm').submit();
}

	function callUpdate() {
		var empId = document.getElementById('paraFrm_empId').value;
     	var appDate = document.getElementById('paraFrm_appDate').value;
	 	var compId = document.getElementById('paraFrm_compId').value;
	 	
	 	if(compId == "") {
	   		alert("Please select the record to update !");
	   		return false;
	 	}
	 
	 	if(!(document.getElementById('paraFrm_status').value == "P" || document.getElementById('paraFrm_status').value == "")) {
	  		alert("You can not update approved or rejected application !");
			return false;
		}
	   
	 	if(empId == "") {
	   		alert("Please select " + document.getElementById('employee').innerHTML.toLowerCase());	   
	   		return false;
	 	}
	 	
	 	if(appDate == "") {
	   		alert("Please enter/select " + document.getElementById('app.dateate').innerHTML.toLowerCase());
	   		return false;
	 	}
	 
	   	if(eadd.length <= 0) {
	   		alert("Please add atleast one Record !");
	   		return false;
	 	}
	 	
	 	var cmt =document.getElementById('paraFrm_comments').value;
	 		if(cmt!="")
	 		{
				var remain = 500 - eval(cmt.length);
					
				if(eval(remain)< 0){
				
				alert("Maximum characters for Comments field is 500 Remove Extra "+remain+"Characters.");
				return false;
				}
				
	 		
	 		}
	 	return true;
	}

	function callAdd() {
	try{
    	var empId = document.getElementById('paraFrm_empId').value;
  		var prName = document.getElementById('paraFrm_prName').value;
  		var prDate = document.getElementById('paraFrm_prDate').value;
  		var startTime = document.getElementById('paraFrm_startTime').value;
  		var endTime = document.getElementById('paraFrm_endTime').value;
  		var prDate = document.getElementById('paraFrm_prDate').value;
  		var checkEdit = document.getElementById("paraFrm_checkEdit").value;
  		var type = document.getElementById('paraFrm_type').value;
  		var st=document.getElementById('paraFrm_status').value;
  		if(!(st=="D" || st=="B")){
			var msg="You can not modify the";
			if(st=='P'){
				msg+=" Submited";
			}
				if(st=='R'){
				msg+=" Rejected";
			}
				if(st=='A'){
				msg+=" Approved ";
			}
				if(st=='F'){
				msg+=" Forwarded ";
			}
			if(st=='W'){
				msg+=" Withdrawn";
			}
			alert(  msg+" application !");
			return false;
		}
		
   		if(empId == "") {
	   		alert("Please select "+document.getElementById('employee').innerHTML.toLowerCase());
	   		return false;
	 	}
  		
		if(prDate == "") {
			alert("Please enter/select " + document.getElementById('compdate').innerHTML.toLowerCase());
			document.getElementById('paraFrm_prDate').focus();
			return false;
		}
		
		if(!validateDate('paraFrm_prDate','compdate')) {
			return false;
		}
		
		if(type == "") {
			alert("Please select "+document.getElementById('extrawork.type').innerHTML.toLowerCase());
			document.getElementById('paraFrm_type').value = "";
			document.getElementById('paraFrm_type').focus();
			return false;
		}
		
		if(startTime == "") {
			alert("Please enter " + document.getElementById('sTime').innerHTML.toLowerCase());
			document.getElementById('paraFrm_startTime').focus();
			return false;
		}
		if(!validateTime('paraFrm_startTime','sTime')) {
			return false;
		}
	
		if(endTime == "") {
			alert("Please enter " + document.getElementById('eTime').innerHTML.toLowerCase());
			document.getElementById('paraFrm_endTime').focus();
			return false;
		}
		if(!validateTime('paraFrm_endTime','eTime')) {
			return false;
		}
		
	    /*if(!timeDifference(startTime,endTime, 'paraFrm_endTime', 'sTime', 'eTime')) {
			return false;
		}*/
		
		var cmt =trim(document.getElementById('paraFrm_prName').value);
		if(cmt==""){
			alert("Please enter " + document.getElementById('purpose').innerHTML.toLowerCase());
			document.getElementById('paraFrm_prName').focus();
			return false;
		}
		if(cmt!=""){
			var remain = 200 - eval(cmt.length);
			if(eval(remain)< 0){
				alert("Maximum characters for "+document.getElementById('purpose').innerHTML.toLowerCase()+" field is 500 Remove Extra "+remain+"Characters.");
				return false;
			}
		}
		
		 var applicationDate =document.getElementById('paraFrm_appDate').value ;
		   var extraWorkDate = document.getElementById('paraFrm_prDate').value ;
		 
		 
			var datdiff = dateDifferenceEqual(extraWorkDate,applicationDate,'paraFrm_appDate','compdate','app.dateate');
  	   
  	  		if(!datdiff){
  				return false;
  			}
			
		 
	
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ExtraWorkApplication_add.action';
		document.getElementById('paraFrm').submit();
	}catch(e){
			alert(e.description);
	}
	//return true;
	}

	function setEdit(id, prName, hDate, hsType, hsTime, heTime, hidMonth, hyear) {
		//alert(hsType);
		try{
		var type="";
		if(hsType=="Full Day")
			type="F";
		else if(hsType=="Half Day")
			type="H";
		else if(hsType=="Partial Day")
			type="O";
		var st=document.getElementById('paraFrm_status').value;	
		if(!(st=="D" || st=="B")){
			var msg="You can not modify the";
			if(st=='P'){
				msg+=" Submited";
			}
				if(st=='R'){
				msg+=" Rejected";
			}
				if(st=='A'){
				msg+=" Approved ";
			}
				if(st=='F'){
				msg+=" Forwarded ";
			}
			alert(  msg+" application !");
			return false;
		}else{
			document.getElementById("paraFrm_prName").value = prName;
			document.getElementById("paraFrm_prDate").value = hDate;
			document.getElementById("paraFrm_type").value = type;
			//alert(document.getElementById("paraFrm_application_type").value);
		  	document.getElementById("paraFrm_startTime").value = hsTime;
		  	document.getElementById("paraFrm_endTime").value = heTime;
		  	document.getElementById("paraFrm_checkEdit").value=id;	
	  	} 
	  	}catch(e){
	  		alert(e);
	  	}
  	}
  	
  	function callClear(){
		var st=document.getElementById('paraFrm_status').value;	
		if(!(st=="D" || st=="B")){
			var msg="You can not clear the";
			if(st=='P'){
				msg+=" Submited";
			}
				if(st=='R'){
				msg+=" Rejected";
			}
				if(st=='A'){
				msg+=" Approved ";
			}
				if(st=='F'){
				msg+=" Forwarded ";
			}
			alert(  msg+" application !");
			return false;
		}else{
			document.getElementById("paraFrm_prName").value = "";
			document.getElementById("paraFrm_prDate").value = "";
			document.getElementById("paraFrm_type").value = "";
			//alert(document.getElementById("paraFrm_application_type").value);
		  	document.getElementById("paraFrm_startTime").value = "";
		  	document.getElementById("paraFrm_endTime").value = "";	
		  	document.getElementById("paraFrm_isappcounts").value = "";			
 		}
   }
	 
	function callTime() {
		var startTime = document.getElementById('paraFrm_startTime').value;
       	var endTime = document.getElementById('paraFrm_endTime').value;
	    var start = startTime.split(":");
	    var end = endTime.split(":");	    
	    var first = eval(start[0])* eval(start[1]);
	    var second = eval(end[0])* eval(end[1]);
	}
	 
	/*function callReport() {
		var id = document.getElementById('paraFrm_compId').value;
	 	if(id == "") {
	   		alert(" Please Select the Record !");
	   		return false;
	 	} else {
		 document.getElementById('paraFrm').target ='_blank';	 
		 document.getElementById('paraFrm').action = "ExtraWorkApplication_report.action";
		 document.getElementById('paraFrm').submit();
		 document.getElementById('paraFrm').target ="";
	 	}
	}*/
	 
	function autoDate () {
		var tDay = new Date();
		var tMonth = tDay.getMonth()+1;
		var tDate = tDay.getDate();
		if(tMonth < 10) { tMonth = "0" + tMonth; }
		if(tDate < 10) { tDate = "0" + tDate; }
		if(document.getElementById('paraFrm_appDate').value == "") {
			document.getElementById("paraFrm_appDate").value = tDate + "-" + tMonth + "-" + tDay.getFullYear();
		}
	}
	
	autoDate();
 
	/*function deletefun() {
  		if(document.getElementById('paraFrm_compId').value == "") {
  			alert("Please select a record to delete !");
  			return false;	
  		}
  		if(!(document.getElementById('paraFrm_status').value == "P" || document.getElementById('paraFrm_status').value == "")) {
			alert("You can not delete approved or rejected compesatory off application !");
			return false;
		}
	}*/
	
	function callLength(type){ 
 		if(type=='isappcount'){
			var cmt =document.getElementById('paraFrm_comments').value;
			var remain = 500- eval(cmt.length);
			document.getElementById('paraFrm_isappcount').value = remain;
			if(eval(remain)< 0){
				document.getElementById('paraFrm_comments').style.background = '#FFFF99';
			}else 
				document.getElementById('paraFrm_comments').style.background = '#FFFFFF';
		}
		if(type=='isappcounts'){
			var cmt =document.getElementById('paraFrm_prName').value;
			var remain = 200- eval(cmt.length);
			document.getElementById('paraFrm_isappcounts').value = remain;
			if(eval(remain)< 0){
				document.getElementById('paraFrm_prName').style.background = '#FFFF99';
			}else 
				document.getElementById('paraFrm_prName').style.background = '#FFFFFF';
		}
 	}
 	
 	function backFun(){
		document.getElementById('paraFrm').target = "_self";
		
	 	//this is for mypage back button
		if(document.getElementById('source').value=='mymessages')
		{
		document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
		}
		else if(document.getElementById('source').value=='myservices')
		{
		document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_serviceData.action';
		}
		else if(document.getElementById('source').value=='mytimecard')
		{
		document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_mytimeCard.action';
		}
		else{
		document.getElementById('paraFrm').action = 'ExtraWorkApplication_back.action';
		}
	 
		document.getElementById('paraFrm').submit();
	}
	
	function getKeepInformedEmp(){
	try
	{
	 	var empcode=document.getElementById('paraFrm_empId').value;
	 	if(empcode==""){
			alert("Please select "+document.getElementById('employee').innerHTML.toLowerCase());
			return false;
		}else{
			callsF9(500,325,'ExtraWorkApplication_f9KeepInformedEmployee.action');
	 	}
	}catch(e){
		alert(e);
	} 
	}
	
	function callKeepInformed(){
	try{
		var st=document.getElementById('paraFrm_status').value;
		if(!(st=="D" || st=="B")){
			var msg="You can't add keep informed to for the";
			if(st=='P'){
				msg+=" submited";
			}
			if(st=='D'){
				msg+=" drafted";
			}
				if(st=='R'){
				msg+=" rejected";
			}
				if(st=='A'){
				msg+=" approved ";
			}
			if(st=='F'){
				msg+=" forwarded ";
			}
			alert(  msg+" application !");
			return false;
		}
		 var empcode=document.getElementById('paraFrm_empId').value;
		 var emp =document.getElementById('paraFrm_employeeId').value;
		 if(empcode==""){
			alert("Please select "+document.getElementById('employee').innerHTML.toLowerCase());
		 	return false;
		 }
		if(emp==""){
			alert("Please select Keep Informed To ");
			return false;
		}
		}catch(e){
			alert(e);
		}
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ExtraWorkApplication_addKeepInformedEmpList.action';
		document.getElementById('paraFrm').submit();
		return true;
	}
	
	 function callForRemove(id){
    	if(!(document.getElementById('paraFrm_status').value=="D" || document.getElementById('paraFrm_status').value=="B")){
			var msg="You can not remove the";
			var st=document.getElementById('paraFrm_status').value;
			if(st=='P'){
				msg+=" Submited";
			}
			if(st=='R'){
				msg+=" Rejected";
			}
			if(st=='A'){
				msg+=" Approved ";
			}
			if(st=='F'){
				msg+=" Forwarded ";
			}
			alert(  msg+" application !");
			return false;
		}else{
	    		var conf=confirm("Are you sure !\n You want to Remove this record ?");
  				if(conf){
			  		document.getElementById('paraFrm_checkRemove').value=id;
			  		document.getElementById('paraFrm').target="_self";
			  		document.getElementById("paraFrm").action="ExtraWorkApplication_removeKeepInformed.action";
  					document.getElementById("paraFrm").submit();
  				}else{
  					return false;
  				}
		  	}
	  	return true;			
    }
    
    function resetFun(){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ExtraWorkApplication_reset.action';
		document.getElementById('paraFrm').submit();
	}
	
	
	function reportFun(){
		//document.getElementById('paraFrm').target ='_blank';	
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ExtraWorkApplication_report.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun(){
 		var conf=confirm("Do you really want to delete this record ?");
	 	if(conf){
			 
			document.getElementById('delete').disabled=true;
			
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action ='ExtraWorkApplication_delete.action'; 
			document.getElementById('paraFrm').submit();
		}else{
			return false; 
		}
	}
	
	function withdrawFun(){ 
		var st=document.getElementById('paraFrm_status').value;
		//alert("st "+st);
		if(st=='F'){
			alert("You can not withdraw forwarded application");
			return false;
		}
	 	var conf=confirm("Do you really want to withdraw this record ?");
 		if(conf){
 			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action ='ExtraWorkApplication_withdrawApplication.action'; 
			document.getElementById('paraFrm').submit();
		}else{
		 	return false;
  		}
	}
	
	/*function checkAppStatus(obj,id){
	  	try{
		  	var approverComments = document.getElementById('paraFrm_approverComments').value;
		   	var conf;
		  	document.getElementById("paraFrm_checkApproveRejectStatus").value=id; 
		  	if(document.getElementById("paraFrm_checkApproveRejectStatus").value=="A")
		    	conf=confirm("Do you really want to approve this application ?");
		    if(document.getElementById("paraFrm_checkApproveRejectStatus").value=="R")
		    	conf=confirm("Do you really want to reject this application ?");
		    if(document.getElementById("paraFrm_checkApproveRejectStatus").value=="B"){
		    	if(trim(document.getElementById("paraFrm_approverComments").value)==""){
					alert("Please enter approver comments");
					return false;
		        }
		    }
		    if(document.getElementById("paraFrm_checkApproveRejectStatus").value=="B")
		     	conf=confirm("Do you really want to send back this application ?");
		    if(conf){
				obj.disabled=true;
				document.getElementById("paraFrm").target="main";
				document.getElementById("paraFrm").action="ExtraWorkApplication_approveRejSendBackApp.action";
				document.getElementById("paraFrm").submit();
				//window.close();
			}else{
				 return false; 
			}
		}catch(e){
			alert(e);
		}	
		return true; 		
	}*/
	
	function approveFun(){
		var approverComments = document.getElementById('paraFrm_approverComments').value;
		document.getElementById("paraFrm_checkApproveRejectStatus").value="A";
		if(confirm("Do you really want to approve this application ?")){
			document.getElementById("paraFrm").target="main";
			document.getElementById("paraFrm").action="ExtraWorkApplication_approveRejSendBackApp.action";
			document.getElementById("paraFrm").submit();
			//window.close();
		}else{
			 return false; 
		}
	}
	
	function rejectFun(){
		var approverComments = document.getElementById('paraFrm_approverComments').value;
		document.getElementById("paraFrm_checkApproveRejectStatus").value="R";
		if(confirm("Do you really want to reject this application ?")){
			document.getElementById("paraFrm").target="main";
			document.getElementById("paraFrm").action="ExtraWorkApplication_approveRejSendBackApp.action";
			document.getElementById("paraFrm").submit();
			//window.close();
		}else{
			 return false; 
		}
	}
	
	function sendbackFun(){
		var approverComments = document.getElementById('paraFrm_approverComments').value;
		if(trim(document.getElementById("paraFrm_approverComments").value)==""){
			alert("Please enter approver comments");
			return false;
        }
		document.getElementById("paraFrm_checkApproveRejectStatus").value="B";
		if(confirm("Do you really want to send back this application ?")){
			document.getElementById("paraFrm").target="main";
			document.getElementById("paraFrm").action="ExtraWorkApplication_approveRejSendBackApp.action";
			document.getElementById("paraFrm").submit();
			//window.close();
		}else{
			 return false; 
		}
	}
	
	function backtolistFun(){
	
		try{
	
		document.getElementById('paraFrm').target = "_self";
		
	 	//this is for mypage back button
		if(document.getElementById('source').value=='mymessages')
		{
		document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
		}
		else{
		document.getElementById('paraFrm').action = 'ExtraWorkApplication_backToApprovalList.action';
		}
		document.getElementById("paraFrm").submit();
		}catch(e)
		{
			alert("Err  "+e);
		}
	  
	}
  
 
 </script>