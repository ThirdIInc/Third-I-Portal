<!--@ Author: Prajakta B-->
<!--@ Date: 26 March 2013-->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="profileAction" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="hiddenProfile"/>
	<s:hidden name="hiddenProfileId"/>
	
	<table width="100%" border="0" align="right" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="right" class="formbg">
				<tr>
					<td width="5%"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="80%" class="txt"><strong class="text_head">Menu
					Profile</strong></td>
					<td width="5%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="40%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
		<tr>
			<td colspan="2">
			<table width="100%" border="0" align="right" class="formbg">
				<tr>
					

					<s:if test="createFlag">
							<!-- Back link for create -->
							<td colspan="4" class="formhead"><strong
								class="forminnerhead">Menu Profile - Create</strong></td>
</s:if>

					<s:if test="copyFlag">
						<!-- Back link for copy -->
						<td colspan="4" class="formhead"><strong
							class="forminnerhead">Menu Profile - Copy</strong></td>

					</s:if>
				</tr>
					<s:if test="createFlag">
					<tr>
						<td class="formtext" width="15%"><label name="profileName"
							id="profileName" ondblclick="callShowDiv(this);"><%=label.get("profileName")%></label>
						:<font color="red">*</font></td>
						<td><s:hidden name="profileId"></s:hidden><s:textfield label="Menu Profile" size="20"
							name="profile" />
							<input type="button"
										onclick="javascript:callGo('profileAction_callGo.action');"
										name="Go" value=" Go " class="token" />
						</td>
					</tr>
					</s:if>
					<s:if test="copyFlag">
						<td class="formtext" width="15%"><label name="profileName"
							id="profileName" ondblclick="callShowDiv(this);"><%=label.get("profileName")%></label>
						:</td>
						<td><s:hidden name="profileId"></s:hidden> <s:textfield
							label="Menu Profile" size="20" name="profile"
							/></td>
						<tr>
							<td width="100%" colspan="2"><s:radio
								name="profileType1"
								list="#{'Existing':'Copy into Existing Profile','New':'Copy into New Profile'}"
								onclick="chkRadio(this);" theme="simple" /> <s:hidden
								name="profileType" /></td>
						</tr>
						<tr>
							<td colspan="4">
							<div id="newDiv">
							<table width="50%">
								<tr>
									<td width="15%" height="50%"><label name="profileName"
										id="profileName" ondblclick="callShowDiv(this);"><%=label.get("profileName") %></label>
									:</td>
									<td width="10%"><s:hidden
										name="copyProfileId"
										value="%{copyProfileId}"></s:hidden><s:textfield label="Menu Profile"
										size="20" name="copyProfile" /><div id="existingDiv" align="left">
												<img
												src="../pages/images/search2.gif" height="16"
												align="absmiddle" width="16" theme="simple"
												onclick="javascript:callsF9(500,325,'profileAction_f9Copy.action');">
									<s:hidden name="copyProId"
												value="%{copyProfileId}"></s:hidden> <s:hidden
												name="copyProName"
												value="%{copyProName}"></s:hidden>
								</div>
									</td>
									</tr>
							</table>
							</div>
							</td>
						</tr>
					<tr>
						<td colspan="4" align="center" ><input type="button"
							class="token"
							onclick="javascript:validateCopy('profileAction_copyProfileItems.action');"
							value="   Copy   " theme="simple" cssStyle="cursor:hand" /></td>
					</tr>

				</s:if>
			
			</table>
			</td>
		</tr>

		<tr>
			<td width="40%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
</s:form>
<script>
callSelect1(document.getElementById('paraFrm_profileType').value);
	var profileType = "";
	 
	function chkRadio(id){
		profileType = id.value;
		//alert(profileType);
		document.getElementById('paraFrm_profileType').value = profileType;
		callSelect1(profileType);
	}
	
	function callSelect1(proType){ 
		if(proType==""){
  			document.getElementById("existingDiv").style.display = 'none';
  			document.getElementById("newDiv").style.display = 'none';
  		}
		
		if(proType=="Existing"){
			document.getElementById("existingDiv").style.display = '';
			document.getElementById("newDiv").style.display = '';
			document.getElementById('paraFrm_copyProfile').disabled=true;
			document.getElementById('paraFrm_copyProfile').value='';
			
		}if(proType=="New"){
			document.getElementById("newDiv").style.display = '';
			document.getElementById("existingDiv").style.display = 'none';
			document.getElementById('paraFrm_copyProfile').disabled=false;
			document.getElementById('paraFrm_copyProfile').value='';
			
		}
	}
	
	function backFun(){
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = 'profileAction_reset.action';
	document.getElementById('paraFrm').submit();
	}	
	
	function validateCopy(name){
	var profil= LTrim(document.getElementById('paraFrm_profileId').value) ;
	var copyProfil= LTrim(document.getElementById('paraFrm_copyProfile').value) ;
	var copyProfilId= document.getElementById('paraFrm_copyProfileId').value ;
	var profileType = document.getElementById("paraFrm_profileType").value;
	var profilName = LTrim(document.getElementById('paraFrm_profile').value);
		
	if(profil==""){
		alert('Please select the '+document.getElementById('profileName').innerHTML.toLowerCase()+' to be Copied');
		return false;
	}
	
	if(profileType==""){
  			alert('Please select any radio button');
  			document.getElementById('paraFrm_profileType').focus();
  			return false;
  		}
	
	if(copyProfil==""){
		if(document.getElementById('paraFrm_copyProfile').disabled==true){
			alert('Please select '+document.getElementById('profileName').innerHTML.toLowerCase()+' to copy');
		}
		else alert('Please enter '+document.getElementById('profileName').innerHTML.toLowerCase()+' to copy');
		return false;
	}

	//alert('not same');
	document.getElementById('paraFrm').target="main";
		document.getElementById('paraFrm').action=name;	
		document.getElementById('paraFrm').submit();	
	return true;
	
}

function callGo(name){
	
		var profil= LTrim(document.getElementById('paraFrm_profile').value) ;
		if(profil==""){
			alert('Please enter a '+document.getElementById('profileName').innerHTML.toLowerCase());
			return false;
		}
		document.getElementById('paraFrm').target="_self";
		document.getElementById('paraFrm').action=name;	
		document.getElementById('paraFrm').submit();	
	}
	
</script>