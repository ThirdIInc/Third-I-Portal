<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp" %>

<s:form action="ConferenceMaster" validate="true" id="paraFrm" theme="simple">
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
					Accessories </strong></td>
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
					<td colspan="5" class="formhead"><strong class="forminnerhead">Conference
					Accessories</strong></td>
					<s:hidden name="confMaster.accessCode"></s:hidden>
				</tr>

				<tr>
					<td width="12%" colspan="1"><label class="set"
						name="conf.accessname" id="conf.accessname"
						ondblclick="callShowDiv(this);"><%=label.get("conf.accessname")%></label>
					<font color="red">*</font> :</td>
					<td width="50%" colspan="2"><s:textfield size="25"
						name="confMaster.accessoryName" maxlength="90"
						onkeypress="return allCharacters();" /></td>
				</tr>

				<!--  <tr>
					<td width="12%" colspan="1"><label class="set"
						name="conf.resperson" id="conf.resperson"
						ondblclick="callShowDiv(this);"></label>
					<font color="red">*</font> :</td>
					<td width="50%" colspan="2" ><s:hidden
						name="confMaster.resPersonCode" theme="simple"></s:hidden> <s:textfield
						name="confMaster.resPersonToken" theme="simple" size="10"
						readonly="true" /> <s:textfield size="50"
						name="confMaster.resPersonName" readonly="true" /> <img id='ctrlHide'
						src="../pages/images/recruitment/search2.gif" height="16"
						width="16" theme="simple" align="absmiddle" class="iconImage"
						onclick="javascript:callsF9(500,325,'ConferenceMaster_f9ResPerson.action');" id="ctrlShow">

					</td>
				</tr>-->
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

var fieldName = ["paraFrm_confMaster_accessoryName"];
var lableName = ["conf.accessname"];
var type      = ["enter"];


function saveFun()
 {

          if(!validateBlank(fieldName,lableName,type))
          return false;
      
          
  	    document.getElementById('paraFrm').target = "_self";
  	   	document.getElementById('paraFrm').action ='ConferenceMaster_save.action';
     	document.getElementById('paraFrm').submit(); 
       
}
		
	
	function resetFun() {
		document.getElementById('paraFrm_show').value = '1';  	 
  	 	document.getElementById('paraFrm').action = 'ConferenceMaster_reset.action';
  	 	document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ConferenceMaster_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ConferenceMaster_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	function callSearch(action) {
		var myWinDiv = window.open('', 'myWinDiv', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv';
		document.getElementById("paraFrm").action = 'ConferenceMaster_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}
	
	function editFun() {
		return true;
	}	
		

 	
</script>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>