<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="HelpDeskSubReqType" validate="true" id="paraFrm"
	validate="true" theme="simple">
	
	<%
			String isAssetChkd = "";
			try {
				isAssetChkd = (String) request.getAttribute("assetStatus");
			} finally {
				if (isAssetChkd == null) {
					isAssetChkd = "";
				}
			}
		%>
	<s:hidden name="statusCheck" value="<%=isAssetChkd%>" id="paraFrm_statusCheck"/>
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />
	<s:hidden name="editFlag" />
	<s:hidden name="cancelFlag" /><s:hidden name="hidReqTypeCode" />
	<s:hidden name="hiddenReqCode" /><s:hidden name="hiddenSubReqCode" />

	<table class="formbg" align="right" width="100%">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Request/Problem Type</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">
				<!-- The Following code Denotes  Include JSP Page For Button Panel -->
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="75%"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
							<td width="22%">
							<div align="right"><span class="style2"><font
								color="red">*</font></span> Indicates Required</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td width="22%" height="22" class="formtext"><label
								name="subreq.type" class="set" id="subreq.type"
								ondblclick="callShowDiv(this);"><%=label.get("subreq.type")%></label>
							:<font color="red">*</font></td>
							<td width="17%" height="22"><s:textfield name="subReqType"
								theme="simple" size="25" maxlength="50" /></td>
							<td width="27%" height="22" class="formtext"></td>
							<td width="18%" height="22"><s:hidden name="subReqTypeCode" /></td>
						</tr>
						<tr>
							<td width="22%" height="22" class="formtext"><label
								name="req.type" class="set" id="req.type"
								ondblclick="callShowDiv(this);"><%=label.get("req.type")%></label>
							:<font color="red">*</font></td>
							<td width="17%" height="22"><s:textfield name="reqType"
								theme="simple" size="25" readonly="true" /></td>
							<td width="27%" height="22" class="formtext"><img
								id='ctrlHide' align="absmiddle"
								src="../pages/common/css/default/images/search2.gif"
								onclick="callSearch('f9reqtypeaction');"></td>
							<td width="18%" height="22"><s:hidden name="reqTypeCode" /></td>
						</tr>
						<tr>
							<td width="22%" height="22" class="formtext"><label
								name="sla.name" class="set" id="sla.name"
								ondblclick="callShowDiv(this);"><%=label.get("sla.name")%></label>
							:<font color="red">*</font></td>
							<td width="17%" height="22"><s:hidden name="slaCode" /> <s:textfield
								name="slaName" theme="simple" size="25" readonly="true" /></td>
							<td width="27%" height="22" class="formtext"><img
								id='ctrlHide' align="absmiddle"
								src="../pages/common/css/default/images/search2.gif"
								onclick="callSearch('f9slaaction');"></td>
							<td width="18%" height="22">&nbsp;</td>
						</tr>
						<tr>
							<td width="22%" height="22" class="formtext"><label
								name="managerAppr" class="set" id="managerAppr"
								ondblclick="callShowDiv(this);"><%=label.get("managerAppr")%></label>
							:</td>
							<td><s:checkbox name="isManagerApproved" /></td>
						</tr>
						<tr>
							<td width="22%" height="22" class="formtext"><label
								name="active" class="set" id="active"
								ondblclick="callShowDiv(this);"><%=label.get("active")%></label>
							:</td>
							<td><s:checkbox name="isActive" /></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="75%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
</s:form>
<script>

// For Save Button

	function saveFun(){	
  	  
  	  var sub=trim(document.getElementById('paraFrm_subReqType').value);
	  if(sub==""){
		  alert("Please enter "+document.getElementById('subreq.type').innerHTML.toLowerCase());
		  document.getElementById('paraFrm_subReqType').focus();
		  
		  return false;
	  }
  	  
  	  var val=trim(document.getElementById('paraFrm_reqType').value);
	  if(val==""){
		  alert("Please select "+document.getElementById('req.type').innerHTML.toLowerCase());
		  document.getElementById('paraFrm_reqType').focus();
		  
		  return false;
	  }
	  
	 var abbr=document.getElementById('paraFrm_slaName').value;
		  
	if(abbr==""){
		alert("Please select "+document.getElementById('sla.name').innerHTML.toLowerCase());
		 return false;
	}
	/*
	if(document.getElementById('isLinkAsset').checked==true){
		if(trim(document.getElementById('paraFrm_assetTypeId').value)=="" || 
			trim(document.getElementById('paraFrm_assetTypeId').value)=="null") {
			alert("Please select "+document.getElementById('assettyp').innerHTML.toLowerCase());
	 		return false;			
		}
	 }*/
	
		document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'HelpDeskSubReqType_save.action';
		document.getElementById('paraFrm').submit();
	  return true;
	}  

	function resetFun() {
		document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'HelpDeskSubReqType_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'HelpDeskSubReqType_input.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'HelpDeskSubReqType_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	function callSearch(action) {
		var myWinDiv = window.open('', 'myWinDiv', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv';
		document.getElementById("paraFrm").action = 'HelpDeskSubReqType_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}
	
	function editFun() {
		return true;
	}
	
	function validateBlank(){
		
	}
	 
	
	function showHideBlock(){
		 
		 if(document.getElementById('isLinkAsset').checked==true)
		 {
		 	document.getElementById('assetBlock').style.display="";
		 }
		 else{
		 	document.getElementById('assetBlock').style.display="none";
			document.getElementById('paraFrm_assetTypeId').value="";
			document.getElementById('paraFrm_assetType').value="";
		 }
		
	}
	
	 
</script>
