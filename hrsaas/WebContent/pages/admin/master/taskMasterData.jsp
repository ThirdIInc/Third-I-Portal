<%@ taglib uri="/struts-tags" prefix="s"%>

<%@include file="/pages/common/labelManagement.jsp" %>

<script type="text/javascript">
	var records = new Array();
</script>
			

<s:form action="TaskMaster" method="post" name="TaskMaster"
	validate="true" id="paraFrm" theme="simple">
	<s:hidden name="show" />
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
			<td width="93%" class="txt"><strong class="text_head">Task Type
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
			<table width="100%" border="0" >
				<tr>

					
		
			<td width="80%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
	
					<td width="20%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
	
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2" class="formbg">
						
						<tr>
							<s:hidden name="typeCode" />

							<td width="10%" colspan="1"><label  name="typename" id="typename" ondblclick="callShowDiv(this);"><%=label.get("typename")%></label> :<font color="red">*</font>

							
							<td colspan="2" width="55%"><s:textfield theme="simple"
								name="typeName" size="25" maxlength="50" /></td>
						</tr>


		

</table></td></tr>

<tr>
			<td width="100%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr></table></s:form>
		
		<script>
		
		
		
		
var f9Fields=["paraFrm_typeName"];
var fieldName = ["paraFrm_typeName"];
var lableName = ["typename"];
var type = ["enter"];
function saveFun()
 {
 
	/*	if(!document.getElementById('paraFrm_typeCode').value==""){
 		alert("Please click on 'Update' button");
 			return false;
 }*/
     	if(!(validateBlank(fieldName, lableName,type)))
        	return false;
         
    	if(!f9specialchars(f9Fields))
		return false
		document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'TaskMaster_save.action';
		document.getElementById('paraFrm').submit();
		return true;
}
 
		function resetFun() {
		document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'TaskMaster_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'TaskMaster_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
	var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'TaskMaster_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	function callSearch(action) {
		var myWinDiv = window.open('', 'myWinDiv', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv';
		document.getElementById("paraFrm").action = 'TaskMaster_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}

function editFun() {
		return true;
	}
</script>
