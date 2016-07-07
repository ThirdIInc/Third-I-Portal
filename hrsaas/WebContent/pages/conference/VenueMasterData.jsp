<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp" %>

<s:form action="VenueMaster" validate="true" id="paraFrm" theme="simple">
<s:hidden name="show" />
<s:hidden name="myPage" id="myPage" />
<s:hidden name="rowId" />
<s:hidden name="modeLength"  />
<s:hidden name="hiddencode"  />

	
	<table width="100%" class="formbg">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Conference
					Room </strong></td>
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
			<table width="100%">
				<tr>

					<td width="80%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td align="right"><font color="red">*</font> Indicates
					Required</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="2" cellspacing="1"
				class="formbg">
				<tr>
					<td width="15%" class="formhead"><strong class="forminnerhead">Conference
					Room</strong></td>
					<s:hidden name="venueMaster.venueCode" />
				</tr>
				<tr>
					<td width="26%" class="formtext" ><label class="set"
						name="conference.name" id="conference.name"
						ondblclick="callShowDiv(this);"><%=label.get("conference.name")%></label>
					<font color="red">*</font> :</td>
					<td width="23%" height="22"><s:textfield
						name="venueMaster.venueName" size="30" maxlength="90"
						onkeypress="return allCharacters();"></s:textfield></td>
					<td width="1%" height="22">&nbsp;</td>
					<td width="26%" class="formtext" ><label class="set"
						name="branch" id="branch"
						ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
					<font color="red">*</font> :</td>
					<td width="23%" height="22"><s:textfield
						name="branchName" readonly="true" size="30" ></s:textfield><s:hidden name='branchCode'/></td>
					<td width="2%" height="22"><img class="imageIcon" id='ctrlHide' src="../pages/images/search2.gif" height="16"
						align="absmiddle" width="17" theme="simple"
						onclick="javascript:callsF9(500,325,'VenueMaster_f9BranchAction.action'); "></td>								
				</tr>
				
				<tr>
					<td width="26%" class="formtext" ><label class="set"
						name="conference.respPerson" id="conference.respPerson"
						ondblclick="callShowDiv(this);"><%=label.get("conference.respPerson")%></label>
					<font color="red">*</font> :</td>
					<td width="73%" colspan="5"><s:textfield name="resPersonToken"  size="10" readonly="true"></s:textfield><s:textfield
						name="resPersonName"  size="40" readonly="true"></s:textfield> <s:hidden name='resPersonCode'/>
					<img class="imageIcon" id='ctrlHide' src="../pages/images/search2.gif" height="16"
						align="absmiddle" width="17" theme="simple"
						onclick="javascript:callsF9(500,325,'VenueMaster_f9ResPersonAction.action'); "></td>					
					
				</tr>
				
				
			</table>
			</td>
		</tr>



	<tr>
			<td colspan="3" width="100%">
			<table width="100%">
				<tr><td width="80%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				</tr>
			</table>
			</td>
		</tr>



	</table>
	
	
	
	
	
</s:form>	
		
<script type="text/javascript">

var fieldName = ["paraFrm_venueMaster_venueName","paraFrm_branchName","paraFrm_resPersonName"];
	var lableName = ["conference.name","branch","conference.respPerson"];
	var flag      = ["enter","select","select"];


function saveFun()
 {

          if(!validateBlank(fieldName,lableName,flag))
          return false;
      
          
  	    document.getElementById('paraFrm').target = "_self";
  	   	document.getElementById('paraFrm').action ='VenueMaster_save.action';
     	document.getElementById('paraFrm').submit(); 
       
}
		
	
	function resetFun() {
		document.getElementById('paraFrm_show').value = '1';  	 
  	 	document.getElementById('paraFrm').action = 'VenueMaster_reset.action';
  	 	document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'VenueMaster_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'VenueMaster_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	function callSearch(action) {
		var myWinDiv = window.open('', 'myWinDiv', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv';
		document.getElementById("paraFrm").action = 'VenueMaster_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}
	
	function editFun() {
		return true;
	}	
		

 	
</script>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>