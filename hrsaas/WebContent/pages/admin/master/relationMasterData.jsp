<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript">
	var records = new Array();
</script>
<s:form action="RelationMaster" id="paraFrm" validate="true"
	target="main" theme="simple">
	<s:hidden name="show" value="%{show}" />
	<s:hidden name="hiddencode" />
	
	<!-- <s:hidden  name="relationName" /> -->
	
	<table width="100%" border="0" align="right"  class="formbg">
		
		<tr>
			<td valign="bottom" class="txt">
			<table  width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg" >
			<tr>
			<td>
			<strong class="text_head"><img
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="text_head">Relation
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
					<td>

					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<s:hidden theme="simple" name="relationCode" />

						</tr>

						<tr>
							<td height="22" width="20%" colspan="1" class="formtext"><label  class = "set" name="relationname" id="relationname" ondblclick="callShowDiv(this);"><%=label.get("relationname")%></label> :<font color="red">*</font></td>
							<td width="80%" height="22" colspan="2"><s:textfield size="25"
								label="%{getText('relName')}" maxlength="30" theme="simple"
								name="relationName" id="paraFrm_relationName"
								onkeypress="return charactersOnlyWithoutSpace();" /></td>		
								
								
								
								
								
						</tr>
						
						<tr>
							<td width="20%" height="22" class="formtext"><label name="is.active" class="set" id="is.active" 		
								ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label> :
							</td>
							<td>
								<s:select name="isActive"  list="#{'Y':'Yes','N':'No'}" cssStyle="width:151;z-index:5;" />
							</td>
						</tr>
						
						
						</table></td></tr></table></td></tr>
						<tr>
			<td width="100%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
						
						</table></s:form>
						
						
<script>




						
		
function saveFun() 
{
	
	if(!validate())
	{
		return false;
	} 
	else 
	{
      	document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'RelationMaster_save.action';
	 	document.getElementById('paraFrm').submit();
     	return true;
     }
     
}


function validate() 
{
	
		var relationName = document.getElementById("paraFrm_relationName").value;
		
	
		if(relationName =="")
		{
			alert("Please enter relation name");
			return false;
		}
		
		else 
		{
			return true;
		}
}
 					
						
						
	function resetFun() {
		document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'RelationMaster_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'RelationMaster_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
	var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'RelationMaster_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	function callSearch(action) {
		var myWinDiv = window.open('', 'myWinDiv', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv';
		document.getElementById("paraFrm").action = 'RelationMaster_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}
	

function editFun() {
		return true;
	}
		
		
function charactersOnlyWithoutSpace(e){
	document.onkeypress = charactersOnlyWithoutSpace;
	var key //= (window.event) ? event.keyCode : e.which;
	if (window.event)
	    key = event.keyCode
	else
	   	key = e.which
	clear();
	// Was key that was pressed a numeric character (0-9) or backspace (8)?
	if ( (key > 96 && key < 123) || (key > 64 && key < 91)|| key == 8  || key == 0)
	 	return true; // if so, do nothing
	else // otherwise, discard character
		return false;
	if (window.event) //IE
	    window.event.returnValue = null;     else //Firefox
	    e.preventDefault();
}

		
		</script>
			
