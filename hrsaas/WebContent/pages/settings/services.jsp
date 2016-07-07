<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form  action="Services" validate="true" id="paraFrm"
	theme="simple">
	<table class="bodyTable" width="100%">
		<tr>
			<td width="100%" colspan="4" class="pageHeader" align="center">Services Management
			</td>
		</tr>
		<tr>
			
				<td colspan="4" width="100%" align="left" valign="middle" class="buttontr">
				<s:submit cssClass="pagebutton" action="Services_save" theme="simple" value="   Save   " onclick="return saveValidate();" />
				<s:submit cssClass="pagebutton" action="Services_reset" theme="simple"   value="  Reset  " />
  				<s:submit cssClass="pagebutton" action="Services_delete" theme="simple"   value="  Delete  " onclick="return callDelete('serviceCode');"/>&nbsp;</td>

			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>

			<tr>

				<td width="20%" colspan="1">Select :</td>
				<td colspan="3" width="75%"><s:hidden theme="simple"
					name="serviceCode" /> <img src="../pages/images/search.gif"
					height="18" align="absmiddle" width="18" theme="simple"
					onclick="javascript:callsF9(500,325,'Services_f9action.action'); ">
					<s:hidden name="chkfield"></s:hidden>
				</td>
			</tr>
			<!--<tr>
				<td colspan="1" width="15%">Header Name <font color="red">*</font>
				:</td>
				<td colspan="1" width="85%"><s:textfield size="40"
					name="headerName" /></td>
			</tr>
			--><tr>
				<td colspan="1" width="20%">Header  Name <font color="red">*</font>
				:</td>
				<td colspan="1" width="85%"><s:textfield size="40"
					name="subheadName" /></td>
			</tr>
			<tr>
				<td width="100%" colspan="4" class="sectionHead">Add Services</td>
			</tr>
			<tr>
				<td colspan="1" width="15%">Service Name <font color="red">*</font>
				:</td>
				<td colspan="1" width="85%"><s:textfield size="40"
					name="serviceName" /></td>
			</tr>
			<tr>
				<td colspan="1" width="15%">Service Link <font color="red">*</font>
				:</td>
				<td colspan="1" width="85%"><s:textfield size="40"
					name="serviceLink" /><s:hidden name="serviceLinkcode"/></td>
			</tr><tr><td></td>
			<td><s:submit cssClass="pagebutton"   action="Services_addLink" theme="simple"   value="  Add  " onclick="return validate();"/></td></tr>
			<table class="bodyTable" width="100%">

			<tr>
				<td class="headercell" width="10%" colspan="1">Sr.No.</td>
				<td class="headercell" width="40%" colspan="1">Link Name</td>
				<td class="headercell" width="35%" colspan="1">Link Path</td>
				<td class="headercell" width="15%" colspan="2"></td>
			</tr>

			<s:iterator value="list">
				<tr><td align="center" class="bodycell"><s:hidden name="serviceLinkCode" value="%{serviceLinkCode}" /><s:property value="srNo"/></td>
					<td class="bodycell" width="50%" colspan="1">
					 <s:property value="serviceName" /></td>
					<td class="bodycell" width="35%" colspan="1"><s:property
						value="serviceLink" /></td>
					<td class="bodycell" width="15%" colspan="2" align="center"><input
						type="button" value="Edit" class="pagebutton"
						onclick="callForEdit(<s:property value="serviceLinkCode"/>);" />&nbsp;&nbsp;
					<input
						type="button" value="Delete" class="pagebutton"
						onclick="callForDelete('<s:property value="serviceLinkCode"/>');" />&nbsp;&nbsp;
					</td>
				</tr>
			</s:iterator>
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
		
	</table>
</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js">
		
</script>
<script>

function callForEdit(id){
  	//alert("id "+id);
	  	document.getElementById("paraFrm").action="Services_edit.action";
	  	document.getElementById('paraFrm_serviceLinkcode').value=id;
	    document.getElementById("paraFrm").submit();
   }
   function callForDelete(id){
   
   var conf=confirm("Are you sure to delete this record?");
   //alert("hh"+id);
	if(conf)
	{
	 	document.getElementById("paraFrm").action="Services_deleteDtl.action";
	  	document.getElementById('paraFrm_serviceLinkcode').value=id;
	    document.getElementById("paraFrm").submit();
	}
	else
	return false;
	}
  
function validate(){
		var fields=["paraFrm_subheadName","paraFrm_serviceName","paraFrm_serviceLink"];
		var labels=["Header Name","Service Name","Service Link"];
		if(!checkMandatory(fields, labels)){
		return false;			  
	}
		
}
function saveValidate(){
if(document.getElementById('paraFrm_subheadName').value==""){
alert("Please Enter Header Name !");
return false;
}
/*if(document.getElementById('paraFrm_serviceName').value==""){
alert("Please Enter Service Name !");
return false;
}
if(document.getElementById('paraFrm_serviceLink').value==""){
alert("Please Enter serviceLink !");
return false;
}*/

}
	
</script>
