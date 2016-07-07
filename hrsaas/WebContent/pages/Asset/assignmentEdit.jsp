<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="AssetEmployee" validate="true" id="paraFrm"
	theme="simple">
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
		<tr><s:hidden name="isApprove"/>
		<s:hidden name="isAssign"/>
		<s:hidden name="partialAssign"></s:hidden>
		<s:hidden name="source"/>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
				<td >
				<input type="button"  class="edit"
								onclick="return validate(this);" value="   Update" />
				<input type="button" class="token"
								onclick="return callBack();" value=" Back " />
				<s:submit action="AssetEmployee_rejectApplication"
						cssClass="token" value="Reject" onclick="return callReject();" />
					<s:submit action="AssetEmployee_sendBackToEmp"
						onclick="return callSendBack();" cssClass="token"
						value="Send back to Employee" />
					 
				<td width="22%">
						<div align="right"><font color="red">*</font> Indicates
						Required</div>
						</td>
						</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td colspan="5" class="formhead"><strong class="forminnerhead"><label  class = "set"  id="assetApp" name="assetApp" ondblclick="callShowDiv(this);"><%=label.get("assetApp")%></label><s:hidden name="tableLength" value="%{tableLength}" /></strong></td>
				</tr>
				<tr>
					
						<td width="25%" colspan="1" class="formtext"><label  class = "set"  id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label><font
							color="red">*</font> :<s:hidden name="empCode" value="%{empCode}" /><s:hidden
							name="code" /></td>
						<td width="75%" colspan="3"><s:textfield name="empToken"
							size="10" value="%{empToken}" theme="simple" readonly="true" /><s:textfield
							name="empName" size="50" value="%{empName}" theme="simple"
							readonly="true" /></td>
						
				<tr>

					<td height="22" width="25%" class="formtext"><label  class = "set"  id="assetAppDate" name="assetAppDate" ondblclick="callShowDiv(this);"><%=label.get("assetAppDate")%></label>
					<font color="red">*</font> :</td>
					<td width="25%" colspan="1" nowrap="nowrap"><s:textfield
						size="25" name="assignDate1" readonly="true" /></td>
					<td width="25%" class="formtext"><label  class = "set"  id="assetStatus" name="assetStatus" ondblclick="callShowDiv(this);"><%=label.get("assetStatus")%></label>:</td>
					<td width="25%"><s:hidden name="hiddenStatus"
						value="%{hiddenStatus}" /> <s:select theme="simple" name="status"
						disabled="true" cssStyle="width:130"
						list="#{'P':'Pending','A':'Approved','R':'Rejected','F':'Forwarded','S':'Assigned'}" /></td>
				</tr>
			</table>
			</td>
		</tr>
		
			<tr>
				<td>
				<table width="100%" border="0" align="center" cellpadding="2"
					cellspacing="2" class="formbg">
					<tr>
						<td colspan="6" class="text_head"><strong
							class="forminnerhead"><label  class = "set"  id="assetAssign" name="assetAssign" ondblclick="callShowDiv(this);"><%=label.get("assetAssign")%></label> </strong></td>
					</tr>
					<tr>
						<td width="25%" height="22" class="formtext"><label  class = "set"  id="asset" name="asset" ondblclick="callShowDiv(this);"><%=label.get("asset")%></label> <font
							color="red">*</font> :</td>
						<td height="22" width="25%" nowrap="nowrap"><s:hidden
							name="asstCode1"></s:hidden><s:textfield size="25"
							name="asstHdType1" readonly="true" /> <img
							src="../pages/images/search2.gif" height="16" align="absmiddle"
							width="16" theme="simple" onclick="return callAsset();"></td>
						<td height="22" width="25%" class="formtext"><label  class = "set"  id="assetSubType" name="assetSubType" ondblclick="callShowDiv(this);"><%=label.get("assetSubType")%></label> <font
							color="red">*</font> :</td>
						<td width="25%" height="22" nowrap="nowrap"><s:textfield
							size="25" readonly="true" name="assetSubType" /><img
							src="../pages/images/search2.gif" height="16" align="absmiddle"
							width="16" theme="simple" onclick="return callSubType();"><s:hidden
							name="subTypeCode">
							<s:hidden name="assetInvType" />
						</s:hidden><s:hidden name="paraId" /></td>
					</tr>

					<tr>

						<td width="25%"><label  class = "set"  id="assetQtyReq" name="assetQtyReq" ondblclick="callShowDiv(this);"><%=label.get("assetQtyReq")%></label> <font color="red">*</font>
						:</td>
						<td width="75%" colspan="3"><s:if test="assetInvType">
							<s:textfield size="25" name="assetRequired"
								onkeypress="return numbersOnly();"></s:textfield>
						</s:if><s:else>
							<s:textfield size="25" name="assetRequired"
								onkeypress="return numbersWithDot();"></s:textfield>
						</s:else><s:property value="assetUnit" /><s:hidden name="assetUnit" /></td>
					</tr>
					<tr>
						<td width="25%"></td>
						<td width="25%"></td>
						<td width="25%"><s:if test="%{insertFlag}">
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
					<td colspan="8"><strong class="text_head"><label  class = "set"  id="unassignedAssets" name="unassignedAssets" ondblclick="callShowDiv(this);"><%=label.get("unassignedAssets")%></label> </strong></td>
				</tr>
				<tr>
					<td width="5%" class="formth"><label  class = "set"  id="asstSrNo" name="asstSrNo" ondblclick="callShowDiv(this);"><%=label.get("asstSrNo")%></label></td>
					<td class="formth" width="25%"><label  class = "set"  id="asset1" name="asset" ondblclick="callShowDiv(this);"><%=label.get("asset")%></label></td>
					<td class="formth" width="25%"><label  class = "set"  id="assetSubType" name="assetSubType" ondblclick="callShowDiv(this);"><%=label.get("assetSubType")%></label></td>
					<td class="formth" width="25%"><label  class = "set"  id="assetQtyReq" name="assetQtyReq" ondblclick="callShowDiv(this);"><%=label.get("assetQtyReq")%></label></td>
					<td class="formth" width="20%"><label  class = "set"  id="assetUnit1" name="assetUnit" ondblclick="callShowDiv(this);"><%=label.get("assetUnit")%></label></td>
					
						<td class="formth" width="20%"><label  class = "set"  id="assetEditRemove" name="assetEditRemove" ondblclick="callShowDiv(this);"><%=label.get("assetEditRemove")%></label></td>
					
					<%int k1 = 1; int c1=0;%>
					<s:iterator value="list">
						<tr>

							<td class="td_bottom_border" width="5%"><%=k1%><s:hidden
								name="srNo" /></td>
							<s:hidden name="assetCode" value="%{assetCode}"></s:hidden>
							<td class="td_bottom_border" width="25%"><s:property
								value="asstHdType" /><s:hidden name="asstHdType" /></td>
							<td class="td_bottom_border" width="25%"><s:property
								value="assetSubTypeIterator" /><s:hidden
								name="assetSubTypeIterator"  /><s:hidden
								name="subTypeCodeIterator" id="<%="subTypeCodeIterator"+k1%>"/></td>
							<td class="td_bottom_border" width="25%"><s:property
								value="assetRequiredIterator" /><s:hidden
								name="assetRequiredIterator" /></td>
							<td class="td_bottom_border" width="20%"><s:property
								value="assetUnitIterator" /><s:hidden
								name="assetUnitIterator" />
								<s:hidden name="partialAssignIt" /></td>
							<s:hidden name="assetInvTypeIterator" />
							
								<td align="center" width="25%" class="td_bottom_border">
								<input type="button" class="rowEdit" title="Edit Record"
									onclick="callForEdit(<s:property value="assetCode"/>,'<s:property value="asstHdType"/>',<s:property value="subTypeCodeIterator"/>,'<s:property value="assetSubTypeIterator" />',
											<s:property value="assetRequiredIterator"/>,'<s:property value="assetUnitIterator"/>','<%=k1%>','<s:property value="partialAssignIt"/>','<s:property value="assetInvTypeIterator"/>')"
									 /> <input type="button" class="rowDelete" title="Delete Record"
									onclick="callForDelete(<%=k1%>,'<s:property value="partialAssignIt"/>')"  /></td>
							
						</tr>
						<%k1++; c1++;%>
					</s:iterator>
				<input type="hidden" name="count" id="count"
					value="<%=c1%>" />
			</table>
			</td>
		</tr>
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td colspan="8"><strong class="text_head"><label  class = "set"  id="assignedAssets" name="assignedAssets" ondblclick="callShowDiv(this);"><%=label.get("assignedAssets")%></label> </strong></td>
				</tr>
				<tr>
					<td width="5%" class="formth"><label  class = "set"  id="asstSrNo" name="asstSrNo" ondblclick="callShowDiv(this);"><%=label.get("asstSrNo")%></label></td>
					<td class="formth" width="25%"><label  class = "set"  id="asset1" name="asset" ondblclick="callShowDiv(this);"><%=label.get("asset")%></label></td>
					<td class="formth" width="25%"><label  class = "set"  id="assetSubType" name="assetSubType" ondblclick="callShowDiv(this);"><%=label.get("assetSubType")%></label></td>
					<td class="formth" width="25%"><label  class = "set"  id="assignedQty" name="assignedQty" ondblclick="callShowDiv(this);"><%=label.get("assignedQty")%></label></td>
					<td class="formth" width="20%"><label  class = "set"  id="assetUnit1" name="assetUnit" ondblclick="callShowDiv(this);"><%=label.get("assetUnit")%></label></td>
											
					<%int k = 1; int c=0;%>
					<s:iterator value="assignedAssetList">
						<tr>

							<td class="td_bottom_border" width="5%"><%=k%><s:hidden
								name="srNo" /></td>
							<s:hidden name="assetCodeAssigned" value="%{assetCodeAssigned}"></s:hidden>
							<td class="td_bottom_border" width="25%"><s:property
								value="asstHdTypeAssigned" /><s:hidden name="asstHdTypeAssigned" /></td>
							<td class="td_bottom_border" width="25%"><s:property
								value="assetSubTypeIteratorAssigned" /><s:hidden
								name="assetSubTypeIteratorAssigned" /><s:hidden
								name="subTypeCodeIteratorAssigned" /></td>
							<td class="td_bottom_border" width="25%"><s:property
								value="assetAssignedIterator" /><s:hidden
								name="assetAssignedIterator" /></td>
							<td class="td_bottom_border" width="20%"><s:property
								value="assetUnitIteratorAssigned" /><s:hidden
								name="assetUnitIteratorAssigned" />
															
						</tr>
						<%k++; c++;%>
					</s:iterator>
				<input type="hidden" name="countAssigned" id="countAssigned"
					value="<%=c%>" />
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td class="formtext"><label  class = "set"  id="assetComment" name="assetComment" ondblclick="callShowDiv(this);"><%=label.get("assetComment")%></label> :</td>
					
						<td><s:textarea name="comments" cols="100" rows="3"
							readonly="true"></s:textarea></td>
					
				</tr>
			</table>
			</td>
		</tr>
		
		
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td class="formtext"><label  class = "set"  id="assignComments" name="assignComments" ondblclick="callShowDiv(this);"><%=label.get("assignComments")%></label> :</td>
					<td><s:textarea name="assignComments" cols="100" rows="3" ></s:textarea></td>
				</tr>
			</table>
			</td>
		</tr>
	
		<s:if test="%{isApprove}">
			<s:if test="assetEmployee.commentFlag">
			</s:if>
			<s:else>
				<tr>
					<td colspan="4">
					<div align="center">
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
							class="formbg">
						<tr>
							<td colspan="4" class="text_head"><strong><label  class = "set"  id="assetComtsByApp" name="assetComtsByApp" ondblclick="callShowDiv(this);"><%=label.get("assetComtsByApp")%></label></strong></td>
						</tr>
						<tr>
							<td class="formth" width="8%"><label  class = "set"  id="asstSrNo" name="asstSrNo" ondblclick="callShowDiv(this);"><%=label.get("asstSrNo")%></label></td>
							<td class="formth" width="30%"><label  class = "set"  id="assetAppr" name="assetAppr" ondblclick="callShowDiv(this);"><%=label.get("assetAppr")%></label></td>
							<td class="formth" width="15%" align="center"><label  class = "set"  id="assetDate" name="assetDate" ondblclick="callShowDiv(this);"><%=label.get("assetDate")%></label></td>
							<td class="formth" width="10%" align="center"><label  class = "set"  id="assetStatus" name="assetStatus" ondblclick="callShowDiv(this);"><%=label.get("assetStatus")%></label></td>
							<td class="formth" width="30%"><label  class = "set"  id="assetComment" name="assetComment" ondblclick="callShowDiv(this);"><%=label.get("assetComment")%></label></td>
						</tr>

						<%
							int j = 1;
							%>
						<s:iterator value="apprList" status="stat">

							<tr>
								<td width="8%" align="center" class="td_bottom_border"><%=j++%></td>
								<td width="30%" class="td_bottom_border"><s:property
									value="approverName" /></td>
								<td width="15%" align="center" class="td_bottom_border"><s:property
									value="approvedDate" /></td>
								<td width="10%" align="center" class="td_bottom_border"><s:property
									value="approveStatus" /></td>
								<td width="30%" class="td_bottom_border"><s:property
									value="approverComment" />&nbsp;</td>

							</tr>
						</s:iterator>
					</table>
					</div>
			</s:else>
		</s:if>
		</td>
		</tr>
	</table>
</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js">

</script>


<script type="text/javascript">
autoDate();

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
	var fieldName  = [ "paraFrm_asstHdType1","paraFrm_assetSubType","paraFrm_assetRequired"];
var lableName  = ["asset","assetSubType","assetQtyReq"];
var types  = ["select","select","enter"];
var assignFlag=document.getElementById("paraFrm_isAssign").value;
	var approverFlag=document.getElementById("paraFrm_isApprove").value;
		var status = document.getElementById("paraFrm_status").value;

		if(approverFlag=="true"){
	
				if(status =="A" || status =="R" || status =="S"){
					alert("You can't update the application.");
					return false;
				}
				
		}else if (assignFlag=='true'){
		
		if(status =="R" || status =="S"){
		alert("You can't update the application.");
					return false;
		}
		}else{
	if(status!='P'){
				alert("You can't update the application.");
				return false; 
	}
		}
		var required=document.getElementById('paraFrm_assetRequired').value;
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
	
	function validate(obj){
	
	var assignFlag=document.getElementById("paraFrm_isAssign").value;
		var tableLength=document.getElementById('count').value;
		var empCode=document.getElementById('paraFrm_empName').value;
	

	var approverFlag=document.getElementById("paraFrm_isApprove").value;
		var status = document.getElementById("paraFrm_status").value;

		
		if(status =="R" || status =="S"){
		alert("You can't update the application.");
					return false;
		}
		
			obj.disabled =true;
			document.getElementById("paraFrm").action= "AssetEmployee_saveByAssigner.action";
	    	document.getElementById("paraFrm").submit();
			
			
	}
		
	function callForEdit(categoryCode,categoryName,subTypeCode,subTypeName,quantityReq,assetUnit,rowId,partialAssign,invType){
	
	var status = document.getElementById("paraFrm_status").value;
	
		if(status =="R"){
		alert("You can't update the application.");
					return false;
		}
		
		
		document.getElementById("paraFrm_asstCode1").value=categoryCode;
		document.getElementById("paraFrm_asstHdType1").value=categoryName;
		document.getElementById("paraFrm_subTypeCode").value=subTypeCode;
		document.getElementById("paraFrm_assetSubType").value=subTypeName;
		document.getElementById("paraFrm_assetRequired").value=quantityReq;
		document.getElementById("paraFrm_assetUnit").value=assetUnit;
		document.getElementById("paraFrm_paraId").value=rowId;
		document.getElementById("paraFrm_assetRequired").readOnly='';
		document.getElementById("paraFrm_assetInvType").value=invType;
		
	   
	  	 	
	  	/*document.getElementById("paraFrm").action="AssetEmployee_edit.action";
	    document.getElementById("paraFrm").submit();*/
   }
   
   function callForDelete(rowId,partialAssign){
   
   var assignFlag=document.getElementById("paraFrm_isAssign").value;
	var approverFlag=document.getElementById("paraFrm_isApprove").value;
	var status = document.getElementById("paraFrm_status").value;

	
		if(status =="R"){
		alert("You can't update the application.");
					return false;
		}
		
		var conf=confirm("Do you really want to remove this record ?");
  			if(conf) {
		document.getElementById("paraFrm_paraId").value=rowId;
		document.getElementById("paraFrm").action="AssetEmployee_remove.action";
	    document.getElementById("paraFrm").submit();
	    }

   }

   function callAsset(){
   
    var assignFlag=document.getElementById("paraFrm_isAssign").value;
	   var approverFlag=document.getElementById("paraFrm_isApprove").value;
		var status = document.getElementById("paraFrm_status").value;
		
	if(status =="R" || status =="S"){
		alert("You can't update the application.");
					return false;
		}
		
		
		if(document.getElementById("paraFrm_empCode").value==""){
			alert("Please select "+document.getElementById('employee').innerHTML.toLowerCase());
			return false;
		}
		
		document.getElementById("paraFrm_assetRequired").value="";
		callsF9(500,325,'AssetEmployee_f9actionCategory.action');
	}
	
   function callSubType(){
   
   var assignFlag=document.getElementById("paraFrm_isAssign").value;
		var status = document.getElementById("paraFrm_status").value;

		
		if(status =="R" || status =="S"){
		alert("You can't update the application.");
					return false;
		}
		
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
	if(!(document.getElementById('paraFrm_status').value=="P"))
			{
				alert("You can't delete the application.");
				return false;
			}
		return callDelete("paraFrm_code");
			
	}
  

function callBack(){
					document.getElementById("paraFrm").action="AssetAssignment_input.action";
				 	document.getElementById("paraFrm").submit();
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
</script>