
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="AssetEmployee" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="assetCodeAssigned" />
	<s:hidden name="checkRemove" />
	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">

		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Asset
					Application </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="30%" id="ctrlHide" align="right"><font color="red">*</font>
					Indicates Required</td>
				</tr>
			</table>
			</td>
		</tr>

		<s:if test="approverCommentsFlag">

			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="2" cellspacing="2"
					class="formbg">
					<tr>
						<td class="formtext" width="25%"><label class="set"
							id="assetComment" name="assetComment"
							ondblclick="callShowDiv(this);"><%=label.get("assetComment")%></label>
						:</td>

						<td><s:textarea name="appcomment" cols="75" rows="3"
							onkeyup="appcallLength('appdescCnt');"></s:textarea> <img
							src="../pages/images/zoomin.gif" height="12" align="absmiddle"
							id='ctrlHide' width="12" theme="simple"
							onclick="javascript:callWindow('paraFrm_appcomment','appcomment','','500','500');"></td>
						<td colspan="1" id='ctrlHide'>Remaining chars<s:textfield
							name="appdescCnt" readonly="true" size="5"></s:textfield></td>

					</tr>
				</table>
				</td>
			</tr>

		</s:if>


		<s:if test="(isApprove=='true') || (isSentBack='true')">
			<s:if test="commentFlag">
				<tr>
					<td colspan="4">
					<div align="center">
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">
						<tr>
							<td colspan="4" class="text_head"><strong><label
								class="set" id="assetComtsByApp" name="assetComtsByApp"
								ondblclick="callShowDiv(this);"><%=label.get("assetComtsByApp")%></label></strong></td>
						</tr>
						<tr>
							<td class="formth" width="8%"><label class="set"
								id="asstSrNo" name="asstSrNo" ondblclick="callShowDiv(this);"><%=label.get("asstSrNo")%></label></td>
							<td class="formth" width="30%" nowrap="nowrap"><label class="set"
								id="assetAppr" name="assetAppr" ondblclick="callShowDiv(this);"><%=label.get("assetAppr")%></label></td>
							<td class="formth" width="15%" align="center"><label
								class="set" id="assetDate" name="assetDate"
								ondblclick="callShowDiv(this);"><%=label.get("assetDate")%></label></td>
							<td class="formth" width="10%" align="center"><label
								class="set" id="assetStatus" name="assetStatus"
								ondblclick="callShowDiv(this);"><%=label.get("assetStatus")%></label></td>
							<td class="formth" width="30%"><label class="set"
								id="assetComment" name="assetComment"
								ondblclick="callShowDiv(this);"><%=label.get("assetComment")%></label></td>
						</tr>

						<%
						int j = 1;
						%>
						<s:iterator value="apprList" status="stat">

							<tr>
								<td width="8%" align="center" class="sortableTD" nowrap="nowrap"><%=j++%></td>
								<td width="30%" class="sortableTD"><s:property
									value="approverName" /></td>
								<td width="15%" align="center" class="sortableTD"><s:property
									value="approvedDate" /></td>
								<td width="10%" align="center" class="sortableTD"><s:property
									value="approveStatus" /></td>
								<td width="30%" class="sortableTD"><s:property
									value="approverComment" />&nbsp;</td>

							</tr>
						</s:iterator>
					</table>
					</div>
					</td>
				</tr>
			</s:if>
		</s:if>

		<s:hidden name="isApprove" />
		<s:hidden name="isAssign" />
		<s:hidden name='commentFlag' />
		<s:hidden name="partialAssign"></s:hidden>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td colspan="5" class="formhead"><strong class="forminnerhead"><label
						class="set" id="assetApp" name="assetApp"
						ondblclick="callShowDiv(this);"><%=label.get("assetApp")%></label><s:hidden
						name="tableLength" value="%{tableLength}" /></strong></td>
				</tr>
				<tr>
					<s:if test="isApprove">
						<td width="25%" colspan="1" class="formtext"><label
							class="set" id="employee" name="employee"
							ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
						:<font color="red">*</font><s:hidden name="empCode"
							value="%{empCode}" /> </td>
						<td width="75%" colspan="3"><s:textfield name="empToken"
							size="10" value="%{empToken}" theme="simple" readonly="true" /><s:textfield
							name="empName" size="50" value="%{empName}" theme="simple"
							readonly="true" /></td>
					</s:if>
					<s:else>
						<s:if test="%{generalFlag}">
							<td width="25%" colspan="1" class="formtext"><label
								class="set" id="employee" name="employee"
								ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
							:<font color="red">*</font><s:hidden name="empCode"
								value="%{empCode}" /></td>
							<td width="75%" colspan="3"><s:textfield name="empToken"
								size="10" value="%{empToken}" theme="simple" readonly="true" /><s:textfield
								name="empName" size="50" value="%{empName}" theme="simple"
								readonly="true" /></td>
						</s:if>
						<s:else>

							<td width="25%" colspan="1" class="formtext"><label
								class="set" id="employee" name="employee"
								ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
							:<font color="red">*</font><s:hidden name="empCode"
								value="%{empCode}" /> </td>
							<td width="75%" colspan="3"><s:textfield name="empToken"
								size="10" value="%{empToken}" theme="simple" readonly="true" /><s:textfield
								name="empName" size="50" value="%{empName}" theme="simple"
								readonly="true" /><img src="../pages/images/search2.gif"
								height="16" align="absmiddle" width="16" theme="simple"
								onclick="callEmployee();" id="ctrlHide"></td>
						</s:else>
					</s:else>
				</tr>
				<tr>
					<td><label class="set" id="branch" name="branch"
						onDblClick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
					<td><s:textfield size="25" name="branch" readonly="true"
						cssStyle="background-color: #F2F2F2;" /></td>
					<td><label class="set" id="department" name="department"
						onDblClick="callShowDiv(this);"><%=label.get("department")%></label>
					:</td>
					<td><s:textfield size="25" name="dept" readonly="true"
						cssStyle="background-color: #F2F2F2;" /></td>
				</tr>
				<tr>
					<td><label class="set" id="designation" name="designation"
						onDblClick="callShowDiv(this);"><%=label.get("designation")%></label>
					:</td>
					<td><s:textfield size="25" name="desig" readonly="true"
						cssStyle="background-color: #F2F2F2;" /></td>
					<td height="22" width="25%" class="formtext"><label
						class="set" id="assetAppDate" name="assetAppDate"
						ondblclick="callShowDiv(this);"><%=label.get("assetAppDate")%></label>
					:<font color="red">*</font></td>
					<td width="25%" colspan="1" nowrap="nowrap"><s:textfield
						size="25" name="assignDate1" readonly="true" /></td>
				</tr>
				<tr>
					<td width="25%" class="formtext"><label class="set"
						id="assetStatus" name="assetStatus"
						ondblclick="callShowDiv(this);"><%=label.get("assetStatus")%></label>:</td>
					<td width="25%"><s:hidden name="hiddenStatus"
						value="%{hiddenStatus}" /> <s:select theme="simple" name="status"
						disabled="true" cssStyle="width:130"
						list="#{'D':'Draft','P':'Pending','A':'Approved','R':'Rejected','F':'Forwarded','S':'Assigned','C':'Canceled','B':'SentBack'}" /></td>
				</tr>
				<script type="text/javascript">
</script>
			</table>
			</td>
		</tr>

		<!-- APPROVER LIST  AND KEEP INFORMED TABLE  STARTS -->
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
					<td width="13%"><s:if test="%{keepInfoFlag}">
						<s:hidden name="employeeId" />
						<s:hidden name="employeeToken" />
						<s:textfield name="employeeName" readonly="true" />
					</s:if></td>
					<td width="5%" colspan="1"><s:if test="%{keepInfoFlag}">
						<img src="../pages/common/css/default/images/search2.gif"
							class="iconImage" width="16" height="15"
							onclick="javascript:getKeepInformedEmp();" />
					</s:if></td>
					<td width="15%"><s:if test="%{keepInfoFlag}">
						<s:submit name="" value=" Add" cssClass=" add"
							action="AssetEmployee_addKeepInformedEmpList"
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

								<td width="80%"><%=++counter11%><s:hidden name="serialNo" /><s:hidden
									name="keepInformedEmpName" /><s:property
									value="keepInformedEmpName" /><s:hidden
									name="keepInformedEmpId" /></td>
								<td width="20%"><s:if test="%{assetEmployee.keepInfoFlag}">
									<a href="#" onclick="callForRemove(<%=counter11%>);">Remove</a>
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


		<tr id="ctrlHide">
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td colspan="6" class="text_head"><strong
						class="forminnerhead"><label class="set" id="assetAssign"
						name="assetAssign" ondblclick="callShowDiv(this);"><%=label.get("assetAssign")%></label>
					</strong></td>
				</tr>
				<tr>
					<td width="25%" height="22" class="formtext"><label
						class="set" id="asset" name="asset"
						ondblclick="callShowDiv(this);"><%=label.get("asset")%></label> :<font
						color="red">*</font></td>
					<td height="22" width="25%" nowrap="nowrap"><s:hidden
						name="asstCode1"></s:hidden><s:textfield size="25"
						name="asstHdType1" readonly="true" /><s:if test="%{keepInfoFlag}"> <img
						src="../pages/images/search2.gif" height="16" align="absmiddle"
						width="16" theme="simple" onclick="return callAsset();"> </s:if></td>
					<td height="22" width="25%" class="formtext"><label
						class="set" id="assetSubType" name="assetSubType"
						ondblclick="callShowDiv(this);"><%=label.get("assetSubType")%></label>
					:<font color="red">*</font></td>
					<td width="25%" height="22" nowrap="nowrap"><s:textfield
						size="25" readonly="true" name="assetSubType" /><s:if test="%{keepInfoFlag}"><img
						src="../pages/images/search2.gif" height="16" align="absmiddle"
						width="16" theme="simple" onclick="return callSubType();"></s:if>
						<s:hidden
						name="subTypeCode" value="%{subTypeCode}">
						<s:hidden name="assetInvType" />
					</s:hidden><s:hidden name="paraId" /></td>
				</tr>

				<tr>

					<td width="25%"><label class="set" id="assetQtyReq"
						name="assetQtyReq" ondblclick="callShowDiv(this);"><%=label.get("assetQtyReq")%></label>
					<font color="red">*</font> :</td>
					<td width="75%" colspan="3"><s:if test="assetInvType">
						<s:textfield size="25" name="assetRequired"
							onkeypress="return numbersOnly();"></s:textfield>
					</s:if><s:else>
						<s:textfield size="25" name="assetRequired"
							onkeypress="return numbersWithDot();"></s:textfield>
					</s:else><s:textfield name="assetUnit" readonly="true"
						cssStyle="border: none" /></td>
				</tr>
				<tr>
					<td width="25%"></td>
					<td width="25%"></td>
					<td width="25%"><s:if test="%{keepInfoFlag}">
						<s:submit cssClass="add" onclick="return callAdd();"
							action="AssetEmployee_addItem" theme="simple" value="   Add   " />
					</s:if><s:else></s:else></td>
				</tr>
				<td width="25%"></td>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td colspan="8"><strong class="text_head"><label
						class="set" id="assetAppAssets" name="assetAppAssets"
						ondblclick="callShowDiv(this);"><%=label.get("assetAppAssets")%></label>
					</strong></td>
				</tr>
				<tr>
					<td width="5%" class="formth"><label class="set" id="asstSrNo"
						name="asstSrNo" ondblclick="callShowDiv(this);"><%=label.get("asstSrNo")%></label></td>
					<td class="formth" width="25%"><label class="set" id="asset1"
						name="asset" ondblclick="callShowDiv(this);"><%=label.get("asset")%></label></td>
					<td class="formth" width="25%"><label class="set"
						id="assetSubType1" name="assetSubType"
						ondblclick="callShowDiv(this);"><%=label.get("assetSubType")%></label></td>
					<td class="formth" width="25%"><label class="set"
						id="assetQtyReq1" name="assetQtyReq"
						ondblclick="callShowDiv(this);"><%=label.get("assetQtyReq")%></label></td>
					<td class="formth" width="20%"><label class="set"
						id="assetUnit1" name="assetUnit" ondblclick="callShowDiv(this);"><%=label.get("assetUnit")%></label></td>

					<td class="formth" width="20%" id="ctrlHide"><label
						class="set" id="assetEditRemove" name="assetEditRemove"
						ondblclick="callShowDiv(this);"><%=label.get("assetEditRemove")%></label></td>

					<%
						int k = 1;
						int c = 0;
					%>
					<s:iterator value="list">
						<tr>

							<td class=sortableTD width="5%"><%=k%><s:hidden name="srNo" /></td>
							<s:hidden name="assetCode" value="%{assetCode}"></s:hidden>
							<td class="sortableTD" width="25%"><s:property
								value="asstHdType" /><s:hidden name="asstHdType" /></td>
							<td class="sortableTD" width="25%"><s:property
								value="assetSubTypeIterator" /><s:hidden
								name="assetSubTypeIterator" /><s:hidden
								name="subTypeCodeIterator" id="<%="subTypeCodeIterator"+k%>" /></td>
							<td class="sortableTD" width="25%"><s:property
								value="assetRequiredIterator" /><s:hidden
								name="assetRequiredIterator" /></td>
							<td class="sortableTD" width="20%"><s:property
								value="assetUnitIterator" /><s:hidden name="assetUnitIterator" />
							<s:hidden name="partialAssignIt" /></td>
							<s:hidden name="assetInvTypeIterator" />

							<td align="center" width="25%" class="sortableTD" id="ctrlHide">
							<s:if test="%{AssetEmployee.keepInfoFlag}">
							<input type="button" class="rowEdit" title="Edit Record"
								onclick="callForEdit(<s:property value="assetCode"/>,'<s:property value="asstHdType"/>',<s:property value="subTypeCodeIterator"/>,'<s:property value="assetSubTypeIterator" />',
											<s:property value="assetRequiredIterator"/>,'<s:property value="assetUnitIterator"/>','<%=k%>','<s:property value="partialAssignIt"/>','<s:property value="assetInvTypeIterator"/>')" />
							<input type="button" class="rowDelete" title="Delete Record"
								onclick="callForDelete(<%=k%>,'<s:property value="partialAssignIt"/>')" /></s:if>
								&nbsp;
								</td>

						</tr>
						<%
							k++;
							c++;
						%>
					</s:iterator>
					<input type="hidden" name="count" id="count" value="<%=c%>" />
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td class="formtext" width="25%"><label class="set"
						id="assetComment" name="assetComment"
						ondblclick="callShowDiv(this);"><%=label.get("assetComment")%></label>
					:</td>
					<s:if test="isApprove">
						<td colspan="2"><s:textarea name="comments" cols="75"
							rows="3" readonly="true"></s:textarea></td>
					</s:if>
					<s:else>
						<td><s:textarea name="comments" cols="75" rows="3"
							onkeyup="callLength('descCnt');"></s:textarea> <img
							src="../pages/images/zoomin.gif" height="12" align="absmiddle"
							id='ctrlHide' width="12" theme="simple"
							onclick="javascript:callWindow('paraFrm_comments','assetComment','','500','500');"></td>
						<td colspan="1" id='ctrlHide'>Remaining chars<s:textfield
							name="descCnt" readonly="true" size="5"></s:textfield></td>
					</s:else>
				</tr>
			</table>
			</td>
		</tr>

		<s:if test="assignCommentsFlag">
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="2" cellspacing="2"
					class="formbg">
					<tr>
						<td class="formtext" width="25%"><label class="set"
							id="assignComments" name="assignComments"
							ondblclick="callShowDiv(this);"><%=label.get("assignComments")%></label>
						:</td>
						<td><s:textarea name="assignComments" cols="75" rows="3"
							readonly="true"></s:textarea></td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
		<s:elseif test="isAssign">
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="2" cellspacing="2"
					class="formbg">
					<tr>
						<td class="formtext" width="25%"><label class="set"
							id="assignComments" name="assignComments"
							ondblclick="callShowDiv(this);"><%=label.get("assignComments")%></label>
						:</td>
						<td><s:textarea name="assignComments" cols="75" rows="3"></s:textarea></td>
					</tr>
				</table>
				</td>
			</tr>
		</s:elseif>


		<tr>
			<td><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>

	<s:hidden name="applicationLevel" /> 
	<s:hidden name="referenceId" /> 
	<s:hidden name="code" />
	
	
	
	 <s:hidden name="source" id="source" /> 

</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js">

</script>


<script type="text/javascript">
autoDate();
var fieldName  = [ "paraFrm_asstHdType1","paraFrm_assetSubType","paraFrm_assetRequired"];
var lableName  = ["asset","assetSubType","assetQtyReq"];
var types  = ["select","select","enter"];
var fieldName1  = [ "paraFrm_empName","paraFrm_assignDate1"];
var lableName1  = ["employee","assetAppDate"];
var types1  = ["select","enter"];
/* ======================================================================
	Function	: callChk
	Explanation : change the status of check box as mouse clicked on check box
	========================================================================== */
	
	
	function callChk(id){
	
		 if(document.getElementById("check"+id).value=='Y'){
		   	document.getElementById("check"+id).value='N';
		  	  }else  if(document.getElementById("check"+id).value=='N'){
		  	document.getElementById("check"+id).value='Y';
		  	 } 
		  	 
	}
	function callAdd(){
	
var assignFlag=document.getElementById("paraFrm_isAssign").value;
	var approverFlag=document.getElementById("paraFrm_isApprove").value;
		var status = document.getElementById("paraFrm_status").value;

		if(approverFlag=="true"){
				if(status =="A" || status =="R" || status =="S"){
					alert("You can't update the application.");
					return false;
				}
		}else if (assignFlag=='true'){
		if(status =="R"){
		alert("You can't update the application.");
					return false;
		}
		}
	//	else{
	//if(status=='P'){
	//			alert("You can't update the application.");
	//			return false; 
	//}
	//	}
		var required=document.getElementById('paraFrm_assetRequired').value;
			
			if(!validateBlank(fieldName, lableName,types)){
				return false;			  
			}
			
			var paraId=document.getElementById("paraFrm_paraId").value;
			var count=document.getElementById("count").value;
			
			if(paraId=="" && count!="" && count!="0")
			{
					
			for(var j=1;j<=count;j++)
			{
				
				var iteratorItem=document.getElementById("subTypeCodeIterator"+j).value;
				
				var item=document.getElementById("paraFrm_subTypeCode").value;
				
				
				if(item==iteratorItem)
				{
				alert("Asset is already added.");
				return false;
				}
				
			}
			
			}else if(paraId!="")
			{
				
				for(var j=1;j<=count;j++)
				{
				
				if(paraId!=j){
				
				var iteratorItem=document.getElementById("subTypeCodeIterator"+j).value;
				var item=document.getElementById("paraFrm_subTypeCode").value;
				
				if(item==iteratorItem)
				{
				alert("Asset is already added.");
				return false;
				}
				}
				}
			}
			if(eval(required)== '0'){
				alert("0 is not allowed in Required field");
				return false;
			}
			
			if(isNaN(required)) {
	 	// if(!amount.match(amountpat)) { 
	 	 	 alert("Enter valid number in Asset Required .");
			 document.getElementById('paraFrm_assetRequired').focus();
			return false;
		}
			if(document.getElementById('paraFrm_assetInvType').value=="true"){
			var checkInt= eval(parseInt(required));
				
				if(required!=checkInt){
					alert('Only whole number allowed in required field');
					document.getElementById('paraFrm_assetRequired').focus();
					return false;
				}
				document.getElementById('paraFrm_assetRequired').value=checkInt;
				}
				
	}
	
	function validate(){
//	function sendapprovalFunction{
	var assignFlag=document.getElementById("paraFrm_isAssign").value;
		var tableLength=document.getElementById('count').value;
		var empCode=document.getElementById('paraFrm_empName').value;
	
	var approverFlag=document.getElementById("paraFrm_isApprove").value;
		var status = document.getElementById("paraFrm_status").value;
		if(approverFlag=="true"){
				if(status =="A" || status =="R" ||status =="S"){
					alert("You can't update the application.");
					return false;
				}
		}else if (assignFlag=='true'){
		if(status =="R"){
		alert("You can't update the application.");
					return false;
		}
		}
		//else{
	//if(status=='P'){
	//			alert("You can't update the application.");
	//			return false; 
	//}
	//	}
			/*if(type=="add"){
				if(!document.getElementById('paraFrm_code').value==""){
  					alert("Please click on update button to update the record.");
  					return false;
  					}
  			}else{
  				if(document.getElementById('paraFrm_code').value==""){
  					alert("Please select a record to update.");
  					return false;
  					}
  			}
			*/
			if(!validateBlank(fieldName1, lableName1, types1)){
				return false;			  
			}
			
			if(!validateDate('paraFrm_assignDate1','assetAppDate')){
				return false;
			}
			if(tableLength=="" || tableLength=="0"){
				alert("Please Add Assets in the Table. ");
				return false;
			}
			/*for(count = 0; count < eval(tableLength); count++){
				var assignedDate=document.getElementById("assignDate"+count).value;
				var returnDate=document.getElementById("returnDate"+count).value;
				
					if(document.getElementById("check"+count).value=='Y' && document.getElementById("returnDate"+count).value=="")
					{
						alert("Please enter return date !");
						document.getElementById("returnDate"+count).focus();
						return false;
					}
					if(document.getElementById("check"+count).value=='N' && !document.getElementById("returnDate"+count).value=="")
					{
						alert("Please select the check box !");
						document.getElementById("returnDate"+count).focus();
						return false;
					}
				if(!validateDate("returnDate"+count,'Return date')){
					return false;
					}
				if(!dateDifferenceEqual(assignedDate,returnDate,"returnDate"+count,"Assigned date","Return date")){
					return false;
					}
			}*/

		//	var actionName = "AssetEmployee_save.action";
			

			
			//obj.disabled =true;
	 	var divComment =document.getElementById('paraFrm_comments').value;
			if (eval(divComment.length) > 500)
		    {
		      alert(document.getElementById('assetComment').innerHTML.toLowerCase()+' accepts only 500' + 
		            ' characters. Please remove '+ (eval(divComment.length) - 500) + ' characters.');
		      return false; 
		  	 }

		return true;
			
	}
		
	function saveFun()//This is for update
	{
			var approverFlag=document.getElementById("paraFrm_isApprove").value;
			var assignFlag=document.getElementById("paraFrm_isAssign").value;
			if(approverFlag =="true" ){
				actionName = "AssetEmployee_saveByApprover.action";
			}
			if( assignFlag=="true" ){
				actionName = "AssetEmployee_saveByAssigner.action";
			}
			if(validate()){
				document.getElementById("paraFrm").action= actionName;
		    	document.getElementById("paraFrm").submit();
		    }
		    else
		    	return false;
			
	}	
	
	function updateFun() {
		if(validate()){
			document.getElementById('paraFrm').target="_self";
			document.getElementById('paraFrm').action="AssetEmployee_update.action";
			document.getElementById('paraFrm').submit();
		}
		else{
			return false;
		}
	}
		
	function draftFun(){
	document.getElementById('paraFrm_hiddenStatus').value = document.getElementById('paraFrm_status').value;
	if(validate())
	{
	document.getElementById("paraFrm").action="AssetEmployee_save.action";
	document.getElementById("paraFrm").submit();
	}
	else
	{
	return false;
	}
}

function sendforapprovalFun(){
	if(validate())
	{
		 var conf=confirm("Do you really want to send for approval ?");
			 		if(conf)
			 		{
			 			document.getElementById('paraFrm_hiddenStatus').value = 'P';
		document.getElementById("paraFrm").action="AssetEmployee_save.action";
		document.getElementById("paraFrm").submit();
			 		}
			 		else{
			 		return false;
			 		}
	
	}
	else
	{
	return false;
	}
}	
		
	function callForEdit(categoryCode,categoryName,subTypeCode,subTypeName,quantityReq,assetUnit,rowId,partialAssign,assetInvType){
	var assignFlag=document.getElementById("paraFrm_isAssign").value;
	var approverFlag=document.getElementById("paraFrm_isApprove").value;
		var status = document.getElementById("paraFrm_status").value;
	
		if(approverFlag=="true"){
				if(status =="A" || status =="R" ||status =="S"){
					alert("You can't update the application.");
					return false;
				}
		}else if (assignFlag=='true'){
		if(status =="R"){
		alert("You can't update the application.");
					return false;
		}
		}
	//	else{
	//if(status=='P'){
	//			alert("You can't update the application.");
	//			return false; 
	//}
	//	}
		
		document.getElementById("paraFrm_asstCode1").value=categoryCode;
		document.getElementById("paraFrm_asstHdType1").value=categoryName;
		document.getElementById("paraFrm_subTypeCode").value=subTypeCode;
		document.getElementById("paraFrm_assetSubType").value=subTypeName;
		document.getElementById("paraFrm_assetRequired").value=quantityReq;
		document.getElementById("paraFrm_assetUnit").value=assetUnit;
		document.getElementById("paraFrm_paraId").value=rowId;
		document.getElementById("paraFrm_assetRequired").readOnly='';
		document.getElementById("paraFrm_assetInvType").value=assetInvType;
	   	
	  	 	
	  	/*document.getElementById("paraFrm").action="AssetEmployee_edit.action";
	    document.getElementById("paraFrm").submit();*/
   }
   
   function callForDelete(rowId,partialAssign){
   var assignFlag=document.getElementById("paraFrm_isAssign").value;
	var approverFlag=document.getElementById("paraFrm_isApprove").value;
		var status = document.getElementById("paraFrm_status").value;

		if(approverFlag=="true"){
				if(status =="A" || status =="R" ||status =="S"){
					alert("You can't update the application.");
					return false;
				}
		}else if (assignFlag=='true'){
		if(status =="R"){
		alert("You can't update the application.");
					return false;
		}
		}
//		else{
//	if(status=='P'){
	//			alert("You can't update the application.");
//				return false; 
//	}
	//	}
		var conf=confirm("Do you really want to remove this record ?");
  			if(conf) {
		document.getElementById("paraFrm_paraId").value=rowId;
		document.getElementById("paraFrm").action="AssetEmployee_remove.action";
	    document.getElementById("paraFrm").submit();
	    }

   }
   function callEmployee(){
   
    var assignFlag=document.getElementById("paraFrm_isAssign").value;
	   var approverFlag=document.getElementById("paraFrm_isApprove").value;
		var status = document.getElementById("paraFrm_status").value;
		if(approverFlag=="true" ){
				if(status =="A" || status =="R" ||status =="S"){
					alert("You can't update the application.");
					return false;
				}
		}else if (assignFlag=='true'){
		if(status =="R"){
		alert("You can't update the application.");
					return false;
		}
		}else{
	if(status!='D'){
				alert("You can't update the application.");
				return false; 
	}
		}
		callsF9(500,325,'AssetEmployee_f9actionEmp.action');
	}
   function callAsset(){
   
    var assignFlag=document.getElementById("paraFrm_isAssign").value;
	   var approverFlag=document.getElementById("paraFrm_isApprove").value;
		var status = document.getElementById("paraFrm_status").value;
		
		if(approverFlag=="true"){
				if(status =="A" || status =="R" ||status =="S"){
					alert("You can't update the application.");
					return false;
				}
		}else if (assignFlag=='true'){
		if(status =="R"){
		alert("You can't update the application.");
					return false;
		}
		}
	//	else{
	//if(status=='P'){
	//			alert("You can't update the application.");
	//			return false; 
	//}
	//	}
		
		if(document.getElementById("paraFrm_empCode").value==""){
			alert("Please select "+document.getElementById('employee').innerHTML.toLowerCase());
			return false;
		}
		
		document.getElementById("paraFrm_assetRequired").value="";
		callsF9(500,325,'AssetEmployee_f9actionCategory.action');
	}
	
   function callSubType(){
   
   var assignFlag=document.getElementById("paraFrm_isAssign").value;
	   var approverFlag=document.getElementById("paraFrm_isApprove").value;
		var status = document.getElementById("paraFrm_status").value;

		if(approverFlag=="true"){
				if(status =="A" || status =="R" ||status =="S"){
					alert("You can't update the application.");
					return false;
				}
		}
		else if (assignFlag=='true'){
		if(status =="R"){
		alert("You can't update the application.");
					return false;
		}
		}
//		else{
	//if(status=='P'){
	//			alert("You can't update the application.");
	//			return false; 
	//}
	//	}
		if(document.getElementById("paraFrm_empCode").value==""){
			alert("Please select "+document.getElementById('employee').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById("paraFrm_asstCode1").value==""){
			alert("Please select "+document.getElementById('asset').innerHTML.toLowerCase());
			return false;
			}
			document.getElementById("paraFrm_assetRequired").readonly=false;
		
	  		callsF9(500,325,'AssetEmployee_f9actionSubType.action'); 
	  		
	   }
	
	function callDelete1()
	{
	if((document.getElementById('paraFrm_status').value=="C"))
			{
				alert("Application is canceled already.");
				return false;
			}
	
	if(!(document.getElementById('paraFrm_status').value=="P"))
			{
				alert("You can't cancel the application.");
				return false;
			}
		return callCancel("paraFrm_code");
			
	}
  function autoDate () {
  
	var tDay = new Date();
	var tMonth = tDay.getMonth()+1;
	var tDate = tDay.getDate();
		if ( tMonth < 10) tMonth = "0"+tMonth;
		if ( tDate < 10) tDate = "0"+tDate;
		if(document.getElementById('paraFrm_code').value=="")
	document.getElementById("paraFrm_assignDate1").value = tDate+"-"+tMonth+"-"+tDay.getFullYear();
	if(document.getElementById("paraFrm_assetInvType").value=="true"){
			document.getElementById("paraFrm_assetRequired").readOnly='';
		}else if(document.getElementById("paraFrm_assetInvType").value=="false"){
			document.getElementById("paraFrm_assetRequired").readOnly='';
		}else {
		document.getElementById("paraFrm_assetRequired").readOnly='';
		}
				//alert("date"+document.getElementById("paraFrm_vchDate").value);
}

function deleteFun() {
	var conf = confirm("Do you really want to delete this application?");
 	if(conf) {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'AssetEmployee_delete.action';
		document.getElementById('paraFrm').submit();
	}
}
//function callBack(){
function backFun() {
	var status = document.getElementById("paraFrm_status").value;
/*	if(document.getElementById('paraFrm_isApprove').value=='true'){
	document.getElementById("paraFrm").action="AssetApproval_checkData.action?status="+status;
	} else if(document.getElementById('paraFrm_isAssign').value=='true'){
		document.getElementById("paraFrm").action="AssetAssignment_input.action";
	} else {
		document.getElementById("paraFrm").action="AssetEmployee_input.action";
	}*/
	document.getElementById("paraFrm").target="_self";
	
	
	if(document.getElementById('source').value=='mymessages')
		{
		document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
		}
		else if(document.getElementById('source').value=='myservices')
		{
		document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_serviceData.action';
		}
		else{
	document.getElementById("paraFrm").action="AssetEmployee_input.action";
		}
	
	document.getElementById("paraFrm").submit();
}
	
function withdrawFun() {
	var conf = confirm("Do you really want to withdraw this application?");
 	if(conf) {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'AssetEmployee_withdrawApplication.action';
		document.getElementById('paraFrm').submit();
	}
}	
	
function resetFun() {
	document.getElementById('paraFrm').target = "_self";
    document.getElementById('paraFrm').action = 'AssetEmployee_reset.action';
	document.getElementById('paraFrm').submit();
}

function reportFun() {
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = 'AssetEmployee_report.action';
	document.getElementById('paraFrm').submit();
}
	
function callReject(){
	var status = document.getElementById("paraFrm_status").value;
	if(status=='R'){
		alert('Application rejected already');
		return false;
	}else if(status=='P'){
		alert('Application sent back already');
		return false
	}
		if(document.getElementById('paraFrm_partialAssign').value=='true'){
			alert('Asset already assigned');
			return false;
		}
		var commentsLength =(document.getElementById("paraFrm_assignComments").value).length;
		if(commentsLength > 500){
		alert("Maximum 500 characters allowed in Reason field.");
		return false;
		}
			
	var conf=confirm("Do you really want to reject this application ?");
  			if(conf) {
  				return true;
  			}else{
  				return false;
  			}
}
function callSendBack(){
	var status = document.getElementById("paraFrm_status").value;
	if(status=='R'){
		alert('Application rejected already');
		return false;
	}else if(status=='P'){
		alert('Application sent back already');
		return false;
	}
	if(document.getElementById('paraFrm_partialAssign').value=='true'){
			alert('Asset already assigned');
			return false;
		}
		var commentsLength =(document.getElementById("paraFrm_assignComments").value).length;
		if(commentsLength > 500){
		alert("Maximum 500 characters allowed in Reason field.");
		return false;
		}
	var conf=confirm("Do you really want to send back this application ?");
  			if(conf) {
  				return true;
  			}else{
  				return false;
  			}
}
function callLength(type){ 
		
		 if(type=='descCnt'){
					var cmt =document.getElementById('paraFrm_comments').value;
					var remain = 500 - eval(cmt.length);
					document.getElementById('paraFrm_descCnt').value = remain;
					
						if(eval(remain)< 0){
					document.getElementById('paraFrm_comments').style.background = '#FFFF99';
					
					}else document.getElementById('paraFrm_comments').style.background = '#FFFFFF';
				
				}
				} 
function appcallLength(type){ 
		
		 if(type=='appdescCnt'){
					var cmt =document.getElementById('paraFrm_appcomment').value;
					var remain = 500 - eval(cmt.length);
					document.getElementById('paraFrm_appdescCnt').value = remain;
					
						if(eval(remain)< 0){
					document.getElementById('paraFrm_appcomment').style.background = '#FFFF99';
					
					}else document.getElementById('paraFrm_appcomment').style.background = '#FFFFFF';
				
				}
				} 				 	
	function backtolistFun(){
		document.getElementById('paraFrm').target = "_self";
		if(document.getElementById('source').value=='mymessages')
		{
		document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
		}
		else{
	document.getElementById("paraFrm").action="AssetEmployee_input.action";
		}
	 
		document.getElementById('paraFrm').submit();
	}
	
	function approveFun(){
	if(validate())
	{
	
	 var conf=confirm("Are you sure !\n You want to approve this record ?");
  				if(conf){
  				
  				document.getElementById('paraFrm_hiddenStatus').value = 'A';
		document.getElementById("paraFrm").action="AssetApproval_callApprove.action";
		
		document.getElementById("paraFrm").submit();
		 
  				}
  				else{
  				return false;
  				}
		
	}
	else
	{
	return false;
	}
	}
	
	
	
	function rejectFun(){
	if(validate())
	{
	
	 var conf=confirm("Are you sure !\n You want to reject this record ?");
  				if(conf){
  				
  				document.getElementById('paraFrm_hiddenStatus').value = 'R';
	document.getElementById("paraFrm").action="AssetApproval_callApprove.action";
		document.getElementById("paraFrm").submit();
  				}
  				else{
  				return false;
  				}
		
	}
	else
	{
	return false;
	}
	}
	
	
	
		function sendbackFun(){
	if(validate())
	{
	
	 var conf=confirm("Are you sure !\n You want to send back this record ?");
  				if(conf){
  				
  				document.getElementById('paraFrm_hiddenStatus').value = 'B';
	document.getElementById("paraFrm").action="AssetApproval_callApprove.action";
		document.getElementById("paraFrm").submit();
  				}
  				else{
  				return false;
  				}
		
	}
	else
	{
	return false;
	}
	}
	
		function getKeepInformedEmp()
	{
	try
	{
	 
	var empcode=document.getElementById('paraFrm_empCode').value;
	 //	var emp =document.getElementById('paraFrm_employeeId').value;
			 if(empcode==""){
				alert("Please select "+document.getElementById('employee').innerHTML.toLowerCase());
				return false;
			}
	else{
			callsF9(500,325,'AssetEmployee_f9KeepInformedEmployee.action');
		 	}
	}
	catch(e)
	{
		alert(e);
		} 
	
	}

function callKeepInformed()
	{
		
		if(!(document.getElementById('paraFrm_status').value=="D" || document.getElementById('paraFrm_status').value=="B")){
			var msg="You can't add keep informed to for the";
			var st=document.getElementById('paraFrm_status').value;
			if(st=='N'){
				msg+=" Cancelled";
			}
			if(st=='P'){
				msg+=" Submited";
			}
			if(st=='D'){
				msg+=" Draft";
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
			}
			
	
		
		 var empcode=document.getElementById('paraFrm_empCode').value;
		 var emp =document.getElementById('paraFrm_employeeId').value;
		 if(empcode==""){
			 alert("Please select "+document.getElementById('employee').innerHTML.toLowerCase());
		 return false;
			 }
			if(emp=="")
			{
			alert("Please select Keep Informed To ");
				return false;
			}
	
		return true;
	}	
	
	 function callForRemove(id)
	    {
	    	if(!(document.getElementById('paraFrm_status').value=="D" || document.getElementById('paraFrm_status').value=="B")){
		
		var msg="You can't remove the";
			var st=document.getElementById('paraFrm_status').value;
			if(st=='N'){
				msg+=" Cancelled";
			}
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
			}   
	    	
	    	else
	    	{
	    		var conf=confirm("Are you sure !\n You want to Remove this record ?");
  				if(conf){
					  		document.getElementById('paraFrm_checkRemove').value=id;
					  		document.getElementById('paraFrm').target="_self";
					  		 document.getElementById("paraFrm").action="AssetEmployee_removeKeepInformed.action";
		  					document.getElementById("paraFrm").submit();
		  				}	
		  				else
		  				{
		  					return false;
		  				}
		  	}
		  	return true;			
	    }
	     
	
	
</script>