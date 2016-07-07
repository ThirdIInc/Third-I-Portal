<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="HelpDeskReqType" validate="true" id="paraFrm" validate="true"
	theme="simple">
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />
	<s:hidden name="editFlag"/>
	<s:hidden name="cancelFlag"/>
	
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
					<td width="93%" class="txt"><strong class="text_head">Request Category</strong></td>
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
							<td>
							<table width="100%%" border="0" align="center" cellpadding="0"
								cellspacing="0">

								<tr>
									<td width="22%" height="22" class="formtext"><label
										name="req.type" class="set" id="req.type"
										ondblclick="callShowDiv(this);"><%=label.get("req.type")%></label>
									:<font color="red">*</font></td>
									<td width="17%" height="22"><s:textfield onkeypress="return charactersOnly();"
										name="reqType" theme="simple" size="25" maxlength="50" />
									</td>
									<td width="27%" height="22" class="formtext"><s:hidden name="reqTypeCode"/></td>
									<td width="18%" height="22">&nbsp;</td>
								</tr>
								<tr>
									<td width="22%" height="22" class="formtext"><label
										name="deptname" class="set" id="deptname2"
										ondblclick="callShowDiv(this);"><%=label.get("deptname")%></label>
									:<font color="red">*</font></td>
									<td width="17%" height="22"><s:hidden name="reqTypeDeptId" />
									<s:textfield name="reqTypeDept" theme="simple" size="25"
										readonly="true" /></td>
									<td width="27%" height="22" class="formtext"><img
										id='ctrlHide' align="absmiddle"
										src="../pages/common/css/default/images/search2.gif"
										onclick="callSearch('f9deptaction');"></td>
									<td width="18%" height="22">&nbsp;</td>
								</tr>
								<tr>
									<td width="22%" height="22" class="formtext"><label
										name="active" class="set" id="active"
										ondblclick="callShowDiv(this);"><%=label.get("active")%></label>
									:</td>
									<td><s:checkbox name="isActive" /></td>
								</tr>
								<!--
								<tr>
									<td width="22%" height="22" class="formtext"><label
										name="managerAppr" class="set" id="managerAppr"
										ondblclick="callShowDiv(this);"><%=label.get("managerAppr")%></label>
									:</td>
									<td><s:checkbox name="isManagerApproved" /></td>
								</tr>
								-->
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr></table></td></tr>
				
				
				<tr>
							<td width="75%"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						</tr>
				</table></s:form>
				
<script>




// For Save Button

	function saveFun(){	
		var val=trim(document.getElementById('paraFrm_reqType').value);
		var abbr=document.getElementById('paraFrm_reqTypeDept').value;
		
		if(val==""){
		  alert("Please enter "+document.getElementById('req.type').innerHTML.toLowerCase());
		  document.getElementById('paraFrm_reqType').focus();
		  return false;
	  	}
		if(abbr==""){
			alert("Please select "+document.getElementById('deptname2').innerHTML.toLowerCase());
		 	return false;
		}
		document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'HelpDeskReqType_save.action';
		document.getElementById('paraFrm').submit();
		return true;
	}  

	function resetFun() {
		document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'HelpDeskReqType_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'HelpDeskReqType_input.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'HelpDeskReqType_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	function callSearch(action) {
		var myWinDiv = window.open('', 'myWinDiv', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv';
		document.getElementById("paraFrm").action = 'HelpDeskReqType_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}
	
	function editFun() {
		return true;
	}
</script>
