<%@taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="JourneyMaster" validate="true" id="paraFrm"
	validate="true" theme="simple">



	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">
		<tr>
			<td colspan="3" valign="bottom" class="txt">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="3" valign="bottom"
				background="../pages/common/css/default/images/lines.gif"
				class="txt"><img
				src="../pages/common/css/default/images/lines.gif" width="16"
				height="1" /></td>
		</tr>
		<tr>
			<td colspan="3" valign="bottom" class="txt"><img
				src="../pages/common/css/default/images/space.gif" width="5"
				height="5" /></td>
		</tr>
		<tr>
			<td width="4%" valign="bottom" class="txt"><strong
				class="formhead"><img
				src="../pages/common/css/default/images/review_shared.gif"
				width="25" height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="formhead">
			Journey </strong></td>
			<td width="3%" valign="top" class="txt">
			<div align="right"><img
				src="../pages/common/css/default/images/help.gif" width="16"
				height="16" /></div>
			</td>
		</tr>
		<tr>
			<td height="5" colspan="3"><img
				src="../pages/common/css/default/images/space.gif" width="5"
				height="7" /></td>
		</tr>
		<tr>
			<td height="5" colspan="3"><img
				src="../pages/common/css/default/images/space.gif" width="5"
				height="7" /></td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><s:submit cssClass="add" value="    Add New"
						action="JourneyMaster_save" onclick="return saveValidation();" />
					<s:submit cssClass="edit" action="JourneyMaster_save"
						onclick="return callUpdate();" value="   Update" />
						<input type="button"
						class="search" value="    Search"
						onclick="javascript:callsF9(500,325,'JourneyMaster_f9action.action'); " />
						
					<s:submit cssClass="reset" value="    Reset"
						action="JourneyMaster_reset" /> <s:submit cssClass="delete"
						value="    Delete" action="JourneyMaster_delete"
						onclick="return deletefun();" /> 
					<input type="button" class="token" value=" Report" onclick="callReport()">
					<s:hidden name="hiddenMode" />
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3"><img
				src="../pages/common/css/default/images/space.gif" width="5"
				height="4" /></td>
		</tr>
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="2">

						<tr>
							<td height="7" colspan="5" class="formtext"><img
								src="../pages/common/css/default/images/space.gif" width="5"
								height="7" /></td>
						</tr>

						<tr>

							<td width="25%" class="formtext" ><label  class = "set" name="journey.mode" id="journey.mode" ondblclick="callShowDiv(this);"><%=label.get("journey.mode")%></label>
							<font color="red">*</font> :</td>
							<td ><s:textfield size="25" name="journeyMode" maxlength="30" onkeypress="return allCharacters();"/>
							</td>
						</tr>
					</table></td></tr></table></td></tr>
		
		<tr>
			<td colspan="3"><img
				src="../pages/common/css/default/images/space.gif" width="5"
				height="4" /></td>
		</tr>	
				
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="2">

						<tr>
							<td height="7" colspan="5" class="formtext"><img
								src="../pages/common/css/default/images/space.gif" width="5"
								height="7" /></td>
						</tr>
						
						<tr>
					<td width="100%" colspan="6" class="formth"><b>OPTIONS</b></td>
					</tr>

						

						<tr>
							<td  class="formtext" ><label  class = "set" name="journey.class" id="journey.class" ondblclick="callShowDiv(this);"><%=label.get("journey.class")%></label>
							<font color="red">*</font> :</td>
							<td ><s:textfield size="25" name="journeyClass" maxlength="25"/>
							<s:hidden name="hiddenEdit" /></td>
						</tr>
						<tr>
							<td colspan="3"><img
								src="../pages/common/css/default/images/space.gif" width="5"
								height="4" /></td>
						</tr>

						<tr>
							<td colspan="3"><img
								src="../pages/common/css/default/images/space.gif" width="5"
								height="4" /></td>
						</tr>
						<tr>
							<td width="25%" >&nbsp;</td>
							<td colspan="1"><s:submit cssClass="add"
								action="JourneyMaster_addItem" theme="simple" value="    Add   "
								onclick="return callAdd()" /></td>

						</tr>
					</table>

					</td>
				</tr>

			</table>

			<table class="formbg" width="100%">

				<tr>
					<td align="center" class="formth" width="10%">
						<label  class = "set" name="journey.srno" id="journey.srno" ondblclick="callShowDiv(this);"><%=label.get("journey.srno")%></label>
					</td>
					<td align="center" class="formth" width="70%">
						<label  class = "set" name="journey.class" id="journey.class1" ondblclick="callShowDiv(this);"><%=label.get("journey.class")%></label>
					</td>


					<td align="right" class="formth" nowrap="nowrap" width="10%"><s:submit
						cssClass="delete" theme="simple" value="     Delete  "
						onclick=" return chkDelete()" /></td>


					<%
					int count1 = 0;
					%>
					<%!int d1 = 0;%>
					<%
					int ii = 0;
					%>


					<s:iterator value="list">

						<tr <%if(count1%2==0){
									%> class="tableCell1" <%}else{%>
							class="tableCell2" <%	}count1++; %>
							onmouseover="javascript:newRowColor(this);"
							title="Double click for edit"
							onmouseout="javascript:oldRowColor(this,<%=count1%2 %>);"
							ondblclick="javascript:callForEdit('<s:property value="jClass"/>','<%=ii+1%>');">



							<td width="10%"><%=++ii%><input type="hidden" name="srNo"
								value="<%=ii%>" /></td>

							<td><s:property value="jClass" /><s:hidden name="jClass" /></td>

							<input type="hidden" name="hdeleteOp" id="hdeleteOp<%=ii%>" />
							<td width="10%" align="center" nowrap="nowrap"><input
								type="checkbox" class="checkbox" value="N" id="confChkop<%=ii%>"
								name="confChkop" onclick="callForDelete('<%=ii%>')" /></td>
						</tr>

					</s:iterator>
					<%
					d1 = ii;
					%>
				</tr>
				<s:hidden name="subcode" />
				<s:hidden name="tableLength" />
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script>
	
	/* function saveValidation()
  {
  var val=document.getElementById('paraFrm_journeyMode').value;
  if(val=="")
  {
  alert('Please Enter Journey Mode');
  return false;
  }
  if(document.getElementById("paraFrm_tableLength").value=="0"){
     	 alert("Please Enter the Options");
     	 document.getElementById("paraFrm_journeyClass").focus();
     	 return false;
     	 } 	
 
   return true;
  }*/
  
  var fieldName = ["paraFrm_journeyMode","paraFrm_journeyClass"];
var lableName = ["journey.mode","journey.class"];
  var flag	   = ["enter","enter"];
  function saveValidation()
 {
 		
		if(!document.getElementById('paraFrm_hiddenMode').value==""){
 		alert("Please click on Update");
 			return false;
 }
     	if(!validateBlank(fieldName,lableName,flag))
          return false;
        	
        	if(document.getElementById("paraFrm_tableLength").value=="0"){
     	 alert("Please Add the Options");
     	 document.getElementById("paraFrm_journeyClass").focus();
     	 return false;
     	 } 	
         
    	if(!f9specialchars(f9Fields))
		return false;
		
		return true;
}


function callUpdate(){
	var len='<%=d1%>';
	if(document.getElementById("paraFrm_hiddenMode").value==""){
  			alert("Please select a record to Update.");
  			return false;
  		}
 		if(len=="0"){
     	 alert("Atleast one Option should be available. ");
     	 document.getElementById("paraFrm_journeyClass").focus();
     	 return false;
     	 } 	 
 		/*if(document.getElementById('paraFrm_hiddenMode').value==""){
 			alert("Please select the record to update!");
 			return false;
 		}*/
 		else {
 			if(!checkMandatory(fieldName, lableName,flag)){
        		return false;
        		
        	}
         	
		if(document.getElementById("paraFrm_tableLength").value=="0"){
     	 alert("Please Enter the Options");
     	 document.getElementById("paraFrm_journeyClass").focus();
     	 return false;
     	 } 	 
     	   	
     	   	  
 		}
 		return true;
 }
  
  
  function callAdd(){
 
	var fields=["paraFrm_journeyClass"];
    var labels=["Journey Class"];
    var flag = ["enter"];
 	 if(!checkMandatory(fields,labels,flag))
     return false;

   }
  
  
  
 function newRowColor(cell)
   		 {
		cell.className='onOverCell';

		}
		
	
	function oldRowColor(cell,val) {
	
	if(val=='1'){
	 cell.className='tableCell2';
	}
	else 
	cell.className='tableCell1';
		
	}
	
	function callForDelete(id)
	   {
	 	   //alert(id);
	   var i=eval(id)-1;
	   if(document.getElementById('confChkop'+id).checked == true)
	   {	  
	    document.getElementById('hdeleteOp'+id).value=eval(id)-1;
	   }
	   else
	   document.getElementById('hdeleteOp'+id).value="";
	    //alert(i+"flag=="+document.getElementById('confChkop'+id).checked);
	   //alert("id for delete"+document.getElementById('hdeleteOp'+id).value);
   		}
   		
   	function callForEdit(journeyCls,sno){
   	//alert("+++++++++"+journeyCls+"hhhhhhhhhhhh"+sno);
   		document.getElementById('paraFrm_hiddenEdit').value=sno;
	 	document.getElementById('paraFrm_journeyClass').value=journeyCls;
	   	
	   	
	   	return false;
  		//document.getElementById("paraFrm").submit();
  		}
   		
   		
   	function chkDelete()
		{
		 if(chk()){
		 var con=confirm('Do you really want to  delete the record ?');
	 	if(con){
	   document.getElementById('paraFrm').action="JourneyMaster_deleteDtl.action";
	    document.getElementById('paraFrm').submit();
	    }
	    else{
	    var flag='<%=d1 %>';
	  	for(var a=1;a<=flag;a++){	
	   document.getElementById('confChkop'+a).checked=false;
	   document.getElementById('confChkop'+a).checked="";
	    document.getElementById('hdeleteOp'+a).value="";
	    }
	     return false;
		 }
	 	}
	 	else {
	 	alert('Please select atleast one record to delete.');
	 	 return false;
	 	}
	}
	
	function chk(){
	 	var flag='<%=d1 %>';
	  	for(var a=1;a<=flag;a++){	
	   if(document.getElementById('confChkop'+a).checked == true)
	   {	
	 	    return true;
	   }	   
	  }
	  	return false;
	}
	
	function deletefun()
    {
   	 	var val= document.getElementById('paraFrm_hiddenMode').value;
    	if(val=="")
    	{
   	 	alert('Please Select a record to delete.');
    	return false;
    	}
   		 else {
    		var conf=confirm("Do you really want to  delete the record ?");
  			if(conf) {
  			return true;
  			}
  			else
  			{
  			return false;
  			}
        }
    
   	return true;
    }
	
	function callReport(){
	    
				document.getElementById('paraFrm').target="_blank";
				document.getElementById('paraFrm').action="JourneyMaster_report.action";	
				document.getElementById('paraFrm').submit();	
				document.getElementById('paraFrm').target="main";
	
	}
</script>