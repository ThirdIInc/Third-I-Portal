<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript">
	var records = new Array();
</script>
<s:form action="HolidayMaster" id="paraFrm" validate="true"
	theme="simple" target="main">
	
	<s:hidden name="show"  />
     <s:hidden name="hiddencode" />
     
	<table width="100%" border="0" align="right" class="formbg">
		
		<tr>
			<td valign="bottom" class="txt">
			<table  width="100%" border="0"  class="formbg" >
			<tr>
			<td>
			<strong class="text_head"><img
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="text_head">Holiday Master  
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
				<td width="100%">
					<table width="100%">
						<td width="80%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						<td width="20%">
						<div align="right"><span class="style2"><font	color="red">*</font></span> Indicates Required</div>
						</td>
					</table>
				</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				
						<tr>

							<td width="19%" colspan="1">
<label  class = "set" name="date" id="date" ondblclick="callShowDiv(this);"><%=label.get("date")%></label> (DD-MM-YYYY) :<font
								color="red"> *</font> </td>
							<td colspan="2" width="81%"><s:textfield  
								label="%{getText('holiDate')}" theme="simple" name="holiDate"
								size="25" onkeypress="return numbersWithHiphen();" /> <s:hidden name="hidedate"  /> <s:a id='ctrlHide'
								href="javascript:NewCal('paraFrm_holiDate','DDMMYYYY');">
								<img src="../pages/images/Date.gif" class="iconImage" height="16"
									align="absmiddle" width="16">
							</s:a></td>
						</tr>


						<tr>

							<td width="19%" colspan="1">
<label  class = "set" name="description" id="description" ondblclick="callShowDiv(this);"><%=label.get("description")%></label> :</td>
							<td colspan="2" width="81%"><s:textfield
								label="%{getText('desc')}" theme="simple" name="desc"
								maxlength="50" size="25" onkeypress="return allCharacters()" /></td>
						</tr>
						
						<tr>

							<td width="19%" colspan="1">
<label  class = "hldType" name="description" id="hldType" ondblclick="callShowDiv(this);"><%=label.get("hldType")%></label> :</td>
							<td colspan="2" width="81%"> 
							<s:select theme="simple"
						name="holidayType"  cssStyle="width:150"
						list="#{'H':'Holiday','N':'National Holiday'}" />
							
							
							</td>
						</tr>
						
						<tr>
							<td width="20%" height="22" class="formtext"><label name="is.active" class="set" id="is.active" 			ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label> :
						</td>
						<td>
							<s:select name="isActive" list="#{'Y':'Yes','N':'No'}" cssStyle="width:151;z-index:5;" />
						</td>
						</tr>
						
						
						</table>
						</td>
						</tr><tr><td width="100%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td></tr>
						</table>
						
						</s:form>
						
  	  
<script type="text/javascript" src="../pages/common/datetimepicker.js">	</script>
						
<script>
	
function saveFun(type)
{

	var fieldName = ["paraFrm_holiDate"];
	var lableName = ["date"];
	var flag = ["enter"];
	var f9Fields = ["paraFrm_desc"];
/*	if(type == 'addnew')
	{
		if(!document.getElementById('paraFrm_hidedate').value == "")
		{
			alert("Please Click on Update");
			return false;
		}
	}
	else
	{
		if(document.getElementById('paraFrm_hidedate').value == "")
		{
			alert("Please Select the Record to Update");
			return false;
		}
	}*/
	/*if(fieldName=="dd-mm-yyyy"){
	 clearText("paraFrm_holiDate","dd-mm-yyyy");
	  document.getElementById('paraFrm_holiDate').focus();
	  }
	  */
	if(!(validateBlank(fieldName, lableName,flag))){
	document.getElementById('paraFrm_holiDate').focus();
			    
              return false;
        }

        
	if(!validateDate("paraFrm_holiDate","Date"))
			return false;
			
	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'HolidayMaster_save.action';
		document.getElementById('paraFrm').submit();
	
	return true;
}					

	function resetFun() {
		document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'HolidayMaster_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'HolidayMaster_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'HolidayMaster_delete.action';
		document.getElementById('paraFrm').submit();
	}
	function callSearch(action) {
		var myWinDiv = window.open('', 'myWinDiv', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv';
		document.getElementById("paraFrm").action = 'HolidayMaster_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}
	function editFun(){
		return true;
	}
						</script>
						
						
					