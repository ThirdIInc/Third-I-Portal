<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="DeptMaster" id="paraFrm" validate="true" target="main"
	theme="simple">
	<s:hidden name="show" value="%{show}" />
	<s:hidden name="hiddencode" />
	<s:hidden name="dptMaster.deptID" />
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Department
					</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="80%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

					<td width="20%">
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
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="10%"><label class="set" name="department"
								id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
							:<font color="red">*</font></td>
							<td colspan="2" width="90%"><s:textfield size="25"
								label="%{getText('dptName')}" theme="simple"
								name="dptMaster.deptName" onkeypress="return allCharacters();" /></td>
						</tr>
						<tr>

							<td width="10%"><label class="set" name="deptdesc"
								id="deptdesc" ondblclick="callShowDiv(this);"><%=label.get("deptdesc")%></label>
							:</td>
							<td colspan="2" width="90%"><s:textfield size="25"
								label="%{getText('dptDesc')}" theme="simple" maxlength="100"
								name="dptMaster.deptDesc" /></td>
						</tr>
						<tr>
							<td width="10%"><label class="set" name="deptabbr"
								id="deptabbr" ondblclick="callShowDiv(this);"><%=label.get("deptabbr")%></label>
							:</td>
							<td colspan="2" width="90%" height="22"><s:textfield
								maxlength="6" label="%{getText('dptAbbr')}" theme="simple"
								size="25" name="dptMaster.deptAbbr" /></td>
						</tr>
						<tr>

							<td width="10%"><label class="set" name="division"
								id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
							:</td>
							<td colspan="2" width="90%" height="22"><s:hidden
								theme="simple" name="dptMaster.deptDivCode" /> <s:textfield
								label="%{getText('divName')}" readonly="true" theme="simple"
								name="dptMaster.divName" size="25" /> <s:if
								test="dptMaster.viewFlag">
								<img id='ctrlHide'
									src="../pages/common/css/default/images/search2.gif" width="16"
									height="15" onclick="callSearch('f9Divaction');"
									align="absmiddle" />
							</s:if></td>
						</tr>
						<tr>

							<td width="10%"><label class="set" name="deptparent"
								id="deptparent" ondblclick="callShowDiv(this);"><%=label.get("deptparent")%></label>
							:</td>
							<td colspan="2" width="90%"><s:hidden theme="simple"
								name="dptMaster.deptParID" /> <s:textfield theme="simple"
								maxlength="12" readonly="true" size="25"
								name="dptMaster.deptParName" /> <img id='ctrlHide'
								align="absmiddle"
								src="../pages/common/css/default/images/search2.gif" width="16"
								height="15" onclick="callSearch('f9Deptaction');" /></td>
						</tr>
						<tr>

							<td width="20%"><label class="set" name="deptlevel"
								id="deptlevel" ondblclick="callShowDiv(this);"><%=label.get("deptlevel")%></label>
							:</td>
							<td colspan="2" width="80%"><s:textfield
								label="%{getText('dptLev')}" maxlength="4" size="25"
								theme="simple" name="dptMaster.deptLev"
								onkeypress="return numbersOnly();" /></td>
						</tr>


						<tr>
							<td colspan="1"><label class="set" name="is.active"
								id="is.active" ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label>
							:</td>
							<td colspan="2"><s:select cssStyle="width:152"
								list=" #{'Y':'Yes','N':'No'}" name="dptMast.isActive" /></td>



						</tr>








					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
</s:form>


<script type="text/javascript">	
		
	
	function saveFun(type){
  		var deptName =document.getElementById('paraFrm_dptMaster_deptName').value;
  		var deptParID =document.getElementById('paraFrm_dptMaster_deptParID').value;
  		var deptDivCode =document.getElementById('paraFrm_dptMaster_deptDivCode').value;
  		var deptAbbr =document.getElementById('paraFrm_dptMaster_deptAbbr').value;
  		var deptDesc =document.getElementById('paraFrm_dptMaster_deptDesc').value;
  		var deptLev =document.getElementById('paraFrm_dptMaster_deptLev').value;
  		
	 	var fieldName = ["paraFrm_dptMaster_deptName"];
		var lableName = ["department"];
		var flag = ["enter"];
		var fieldName1 = ["paraFrm_dptMaster_deptName"];

  /*if(type == 'update'){
		if(document.getElementById('paraFrm_dptMaster_deptID').value==""){
			alert("Please select a record to update ");
  			return false;
			}
		}
	else 
	{
		if(!document.getElementById('paraFrm_dptMaster_deptID').value==""){
			alert("Please Click on Update ");
  			return false;
			}
		
	}*/

     if(!validateBlank(fieldName, lableName,flag)){
              return false;
        }
        
      if(!f9specialchars(fieldName1))
      {
              return false;
       }
  		
  			var amountpat= /^[0-9]/;

		if(!(deptLev=="")){
    	//if(!deptLev.match(amountpat)) { 
    	 if(isNaN(deptLev)) {
	   			alert ("Please Enter Number Only in "+document.getElementById('deptlevel').innerHTML.toLowerCase());
			  	 		document.getElementById('paraFrm_dptMaster_deptLev').focus();
			return false;
		}
   		 }
   		 
   		 if(!(deptDivCode=="")){
    //	if(!deptDivCode.match(amountpat)) { 
    	 if(isNaN(deptDivCode)) {
	   				alert ("Department Division Code should be Number !");
				  	document.getElementById('paraFrm_dptMaster_deptDivCode').focus();
			return false;
		}
   		 }
   		 
   		 if(!(deptParID=="")){
    	//if(!deptParID.match(amountpat)) { 
    	 if(isNaN(deptParID)) {
    	
	   			alert ("Department Parent Code should be Number !");
				  	document.getElementById('paraFrm_dptMaster_deptParID').focus();
			return false;
		}
   		 }
   document.getElementById('paraFrm').target = "_self";	
   document.getElementById('paraFrm').action = 'DeptMaster_save.action';
   	document.getElementById('paraFrm').submit(); 	 
   return true ;
  		 		
  		
  	}		
		
		function resetFun() {
		document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'DeptMaster_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'DeptMaster_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
	var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'DeptMaster_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	function callSearch(action) {
		var myWinDiv = window.open('', 'myWinDiv', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv';
		document.getElementById("paraFrm").action = 'DeptMaster_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}
		
		function editFun() {
		return true;
	}	
		</script>


