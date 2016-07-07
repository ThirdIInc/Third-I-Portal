<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<link rel="stylesheet" type="text/cs href=" ../pages/common/tabcontent.css" />
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>

<s:form action="AccessProfile" theme="simple" method="post" id="paraFrm">

	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">
		
		<s:hidden name="profileId" /><s:hidden name="hiddenMapCheckBox" />
			
	
		<tr>
			<td colspan="3" valign="bottom" class="txt"><img
				src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
		</tr>

		<tr>
			<td colspan="4" width="100%">
			<table class="formbg" width="100%">

				<tr width="100%">
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Access
					Profile</strong></td>
					<td width="7%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>

		</tr>
		<tr>
			<td  colspan="3"><img
				src="../pages/images/recruitment/space.gif" width="5" height="7" /></td>
		</tr>

		<tr>
			<td>
			<table width="100%">
				<td><input type="button" class="save"
					onclick="callSave();" theme="simple" value="Save" /> 
					<s:if test="deleteButFlag">
					<input
					type="button" class="delete" onclick="deleteFun();" theme="simple"
					value="Delete" />
					</s:if> 
					<input type="button" class="reset"
					onclick="resetFun();" theme="simple" value="Reset" /> 
					<input
					type="button" class="back" onclick="callBack();" theme="simple"
					value="Back" /></td>
			</table>
			</td>
		</tr>
		<tr>
		
			<td colspan="4">
			<table width="100%" border="0" align="center" cellpadding="1"
				cellspacing="1" class="formbg">
				<td width="25%"><label name="profile.name"
					id="profile.name" ondblclick="callShowDiv(this);"><%=label.get("profile.name")%></label>:<font color="red">*</font> </td>
				<td width="25%"><s:textfield label="Access Profile" size="40"
					name="profile" maxlength="25" /> <!--<input type="button"
					name="Go" class="token" value=" Go "
					onclick="javascript:callGo('AccessProfile_callGoAction.action');" />
				--></td>
				<td width="25%">&nbsp;</td>
				<td width="25%">&nbsp;</td>
			</table>
		</tr>
		<tr>
			<td colspan="4">
			<table width="100%" border="0" align="center" cellpadding="1"
				cellspacing="1" class="formbg">
				<tr>
					<td colspan="4" class="text_head"><strong
						class="forminnerhead">MAP Access </strong></td>
				</tr>

			</table>
			</td>
		</tr>
		<!-- Division -->
		<tr>
			<td colspan="4">
			<table width="100%" border="0" align="center" cellpadding="1"
				cellspacing="1" class="formbg">
				<tr>
					<td colspan="4" class="text_head"><strong
						class="forminnerhead">Division Access </strong></td>
				</tr>
				<tr>
					<td width="25%"><label class="set" name="map.division"
						id="map.division" ondblclick="callShowDiv(this);"><%=label.get("map.division")%></label>:</td>
					<td width="25%" nowrap="nowrap"><s:checkbox
						name="divisionFlag" id="divisionFlag" onclick="hideDivTable();" />
					<td width="25%"></td>
					<td width="25%"></td>

				</tr>
				<tr id="divTable">

					<td width="20%"><label class="set" id="division"
						name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:<font color="red">*</font> </td>
					<td width="30%"><s:hidden name="sDivCode" /> <s:textarea
						name="sDivName" cols="25" rows="2" readonly="true"></s:textarea> <img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="16" theme="simple"
						onclick="javascript:callsF9(500,325,'AccessProfile_f9Div.action'); ">
					</td>
					<td width="25%"></td>
					<td width="25%"></td>

				</tr>
			</table>
			</td>
		</tr>
		<!-- Branch  -->
		<tr>
			<td colspan="4">
			<table width="100%" border="0" align="center" cellpadding="1"
				cellspacing="1" class="formbg">
				<tr>
					<td colspan="4" class="text_head"><strong
						class="forminnerhead">Branch Access </strong></td>
				</tr>
				<tr>
					<td width="25%"><label class="set" name="map.branch"
						id="map.branch" ondblclick="callShowDiv(this);"><%=label.get("map.branch")%></label>:</td>
					<td width="25%" nowrap="nowrap"><s:checkbox name="branchFlag" id="branchFlag"
						onclick="hideBranchTable();" />
					<td width="25%"></td>
					<td width="25%"></td>
				</tr>
				<tr id="branchTable">

					<td width="20%"><label class="set" id="branch" name="branch"
						ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:<font color="red">*</font> </td>
					<td width="30%"><s:hidden name="sBranch"></s:hidden> <s:textarea
						name="sBranchName" cols="25" rows="2" readonly="true"></s:textarea>
					<img src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="16" theme="simple"
						onclick="javascript:callsF9(500,325,'AccessProfile_f9Brch.action'); ">
					</td>
					<td width="25%"></td>
					<td width="25%"></td>

				</tr>
				
			</table>
			</td>
		</tr>
		<!-- PayBill -->
		<tr>
			<td colspan="4">
			<table width="100%" border="0" align="center" cellpadding="1"
				cellspacing="1" class="formbg">
				<!-- Branch Access -->
				<tr>
					<td colspan="4" class="text_head"><strong
						class="forminnerhead">Paybill Access </strong></td>
				</tr>
				<tr>
					<td width="25%"><label class="set" name="map.paybill"
						id="map.paybill" ondblclick="callShowDiv(this);"><%=label.get("map.paybill")%></label>:</td>
					<td width="25%" nowrap="nowrap"><s:checkbox name="paybillFlag" id="paybillFlag"
						onclick="hidePaybillTable();" />
					<td width="25%"></td>
					<td width="25%"></td>
				</tr>
				<tr id="paybillTable">

					<td width="20%"><label class="set" id="paybill" name="paybill"
						ondblclick="callShowDiv(this);"><%=label.get("paybill")%></label>:<font color="red">*</font> </td>
					<td width="30%"><s:hidden name="sPaybill"></s:hidden> <s:textarea
						name="sPaybillName" cols="25" rows="2" readonly="true"></s:textarea>
					<img src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="16" theme="simple"
						onclick="javascript:callsF9(500,325,'AccessProfile_f9Paybill.action'); ">
					</td>
					<td width="25%"></td>
					<td width="25%"></td>

				</tr>

			</table></td></tr>
	
		<tr>
			<td>
			<table width="100%"><tr>
				<td><input type="button" class="save"
					onclick="callSave();" theme="simple" value="Save" />
					 <s:if test="deleteButFlag">
					<input
					type="button" class="delete" onclick="deleteFun();" theme="simple"
					value="Delete" />
					</s:if>  
					<input type="button" class="reset"
					onclick="resetFun();" theme="simple" value="Reset" /> 
					<input
					type="button" class="back" onclick="callBack();" theme="simple"
					value="Back" /></td>
			</tr></table>
			</td>
		</tr>


		
	</table>
</s:form>
<script>

	function createProfile(name){
		document.getElementById('paraFrm').target="main";
		document.getElementById('paraFrm').action=name;	
		document.getElementById('paraFrm').submit();							
		
	}
	
	function updateProfile(name){
		document.getElementById('paraFrm').target="main";
		document.getElementById('paraFrm').action=name;	
		document.getElementById('paraFrm').submit();							
		
	}
	
	function callGo(name){
	
		var fields=["paraFrm_profileAccess_profile"];
       var labels=["profile.name"];
       var flag = ["enter"];
 	  if(!validateBlank(fields,labels,flag))
     return false;
			
		var profil= document.getElementById('paraFrm_profileAccess_profile').value ;
		if(profil==""){
			alert('Please Enter a Name for the Profile to be Created');
			return false;
		}
		
		 
			if(!f9specialchars(fields)){
		     	return false;
   		}
   		
		document.getElementById('paraFrm').target="main";
		document.getElementById('paraFrm').action=name;	
		document.getElementById('paraFrm').submit();	
	}
	
	function callUpdateGo(name){
	
		var profil= document.getElementById('paraFrm_profileAccess_profile').value ;
		if(profil==""){
			alert('Please Select a Name for the Profile to be Updated');
			return false;
		}
		document.getElementById('paraFrm').target="main";
		document.getElementById('paraFrm').action=name;	
		document.getElementById('paraFrm').submit();	
	}
	
	function callFun(id){
		document.getElementById('paraFrm').action='AccessProfile_createNew.action?tab='+id;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target="main";
				
		return true;
	}
	
	function trimData(str) {     
	if(!str || typeof str != 'string')         
		return null;     
	return str.replace(/^[\s]+/,'').replace(/[\s]+$/,'').replace(/[\s]{2,}/,' '); 
	}
	
	function callSave(){
		try{
		var profil= trimData(document.getElementById('paraFrm_profile').value);
		
		if(document.getElementById('paraFrm_profile').value==''){
			 alert ("Please enter " +document.getElementById('profile.name').innerHTML.toLowerCase());
			return false;
		}
		
		if(profil==''){
		alert('Please enter Profile Name');
		document.getElementById('paraFrm_profile').focus();
			return false;
		}
		
		
		
		var divCheckboxVar = document.getElementById('divisionFlag').checked;
		var branchCheckboxVar = document.getElementById('branchFlag').checked;
		var paybillCheckboxVar = document.getElementById('paybillFlag').checked;
		
		
		if((divCheckboxVar == false)  )
		{
		if(document.getElementById('paraFrm_sDivCode').value==""){
		alert("Please select the " + document.getElementById('division').innerHTML.toLowerCase());
			document.getElementById('paraFrm_sDivName').focus();
			return false;
		
		}
			
		}
		
		if((branchCheckboxVar == false)  )
		{
		if(document.getElementById('paraFrm_sBranch').value==""){
			alert("Please select the " + document.getElementById('branch').innerHTML.toLowerCase());
			document.getElementById('paraFrm_sBranchName').focus();
			return false;
		}
		}
		if((paybillCheckboxVar == false)  )
		{
		if(document.getElementById('paraFrm_sPaybill').value==""){
			alert("Please select the " + document.getElementById('paybill').innerHTML.toLowerCase());
			document.getElementById('paraFrm_sPaybillName').focus();
			return false;
		}
		}
		
		} catch(e)
		{
			alert(e);
		}
		
		document.getElementById('paraFrm').target = "_self";
  		document.getElementById('paraFrm').action = 'AccessProfile_saveFunction.action';
		document.getElementById('paraFrm').submit();
		
		return true;
	}
	
	function callReport(name) {
		document.getElementById('paraFrm').target="_blank";
		document.getElementById('paraFrm').action=name;	
		document.getElementById('paraFrm').submit();							
		document.getElementById('paraFrm').target="main";
	}
	
	function SetChecked(){
		if(document.getElementById('divBox1').checked==true){
				document.getElementById('paraFrm_hiddenDivChk').value='divOn';
		}else{
			document.getElementById('paraFrm_hiddenDivChk').value='';
		}
		if(document.getElementById('divBox2').checked==true){
				document.getElementById('paraFrm_hiddenDptChk').value='dptOn';
		}else{
			document.getElementById('paraFrm_hiddenDptChk').value='';
		}
		if(document.getElementById('divBox3').checked==true){
				document.getElementById('paraFrm_hiddenCntChk').value='cntOn';
		}else{
			document.getElementById('paraFrm_hiddenCntChk').value='';
		}
		if(document.getElementById('divBox4').checked==true){
				document.getElementById('paraFrm_hiddenChk').value='empOn';
		}else{
			document.getElementById('paraFrm_hiddenChk').value='';
		}
	}
	onLoad();


function hideDivTable(){

	

		if(document.getElementById('divisionFlag').checked){
			document.getElementById('divTable').style.display = 'none';
			document.getElementById('paraFrm_sDivCode').value = ''; 
			document.getElementById('paraFrm_sDivName').value = ''; 
			
		}
		else{
			document.getElementById('divTable').style.display = '';
			document.getElementById('divisionFlag').value = ''; 
			
			
		}
	}

function hideBranchTable(){
		if(document.getElementById('branchFlag').checked){
			document.getElementById('branchTable').style.display = 'none';
			document.getElementById('paraFrm_sBranch').value = ''; 
			document.getElementById('paraFrm_sBranchName').value = ''; 
			
		}
		else{
			document.getElementById('branchTable').style.display = '';
			document.getElementById('divFlag').value = ''; 
			
		}
	}

function hidePaybillTable(){
		if(document.getElementById('paybillFlag').checked){
			document.getElementById('paybillTable').style.display = 'none';
			document.getElementById('paraFrm_sPaybill').value = ''; 
			document.getElementById('paraFrm_sPaybillName').value = ''; 
			
		}
		else{
			document.getElementById('paybillTable').style.display = '';
			document.getElementById('paybillFlag').value = ''; 
		}
	}
	function callBack() 
{
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'AccessProfile_back.action';
		document.getElementById('paraFrm').submit();
}
	function onLoad(){
	var hiddenMapCheckBox = document.getElementById('paraFrm_hiddenMapCheckBox').value;
	
	if(hiddenMapCheckBox =="Y"){
	document.getElementById('divisionFlag').checked=true;
	document.getElementById('branchFlag').checked=true;
	document.getElementById('paybillFlag').checked=true;
	document.getElementById('divTable').style.display='none';
	document.getElementById('branchTable').style.display='none';
	document.getElementById('paybillTable').style.display='none';
	}
	else{
	
	if(document.getElementById('divisionFlag').checked){
		document.getElementById('divTable').style.display='none';
		
			
		}
		else{
			document.getElementById('divTable').style.display='';
			
		}
	
	if(document.getElementById('branchFlag').checked){
		document.getElementById('branchTable').style.display='none';
			
		}
		else{
			document.getElementById('branchTable').style.display='';
			
		}
		
		if(document.getElementById('paybillFlag').checked){
		document.getElementById('paybillTable').style.display='none';
			
		}
		else{
			document.getElementById('paybillTable').style.display='';
			
		}
		
	}
	//document.getElementById('divisionFlag').checked=true;
	//document.getElementById('branchFlag').checked=true;
	//document.getElementById('paybillFlag').checked=true;
	
	}
	
	function deleteFun() {
	var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'AccessProfile_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	
	function resetFun() {
		//document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'AccessProfile_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
</script>