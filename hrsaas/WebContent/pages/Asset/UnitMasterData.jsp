<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp" %>

<s:form action="UnitMaster" validate="true" id="paraFrm" theme="simple">
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
					<td width="93%" class="txt"><strong class="text_head">Unit Master</strong></td>
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
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td colspan="5" class="formhead"><strong class="forminnerhead">Unit
					Master </strong></td>
				</tr>
				<tr>
					<s:hidden name="unitCode" />
					<td width="10%" colspan="1"><label  class = "set" name="unit.name" id="unit.name" ondblclick="callShowDiv(this);"><%=label.get("unit.name")%></label> 
					<font color="red">*</font>
					:</td>
					<td colspan="2" width="55%"><s:textfield theme="simple"
						name="unitName" size="35" maxlength="50" /></td>
				</tr>
			<tr>
			<td width="20%" height="22" class="formtext"><label
						name="is.active" class="set" id="is.active"
						ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label>
					:</td>
					<td><s:select name="isActive" list="#{'Y':'Yes','N':'No'}"
						cssStyle="width:151;z-index:5;" /></td>
					<td height="22" class="formtext">&nbsp;</td>
					<td height="22">&nbsp;</td>
			
			
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

var f9Fields=["paraFrm_unitName"];
var fieldName = ["paraFrm_unitName"];
var lableName = ["unit.name"];
var type = ["enter"];


function saveFun()
 {
 
 

          if(!validateBlank(fieldName,lableName,type))
          return false;
         
    	if(!f9specialchars(f9Fields))
		return false;
          
  	    document.getElementById('paraFrm').target = "_self";
  	   	document.getElementById('paraFrm').action ='UnitMaster_save.action';
     	document.getElementById('paraFrm').submit(); 
       
}
		
	
	function resetFun() {
		document.getElementById('paraFrm_show').value = '1';  	 
  	 	document.getElementById('paraFrm').action = 'UnitMaster_reset.action';
  	 	document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'UnitMaster_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'UnitMaster_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	function callSearch(action) {
		var myWinDiv = window.open('', 'myWinDiv', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv';
		document.getElementById("paraFrm").action = 'UnitMaster_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}
	
	function editFun() {
		return true;
	}	
		

 	
</script>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>