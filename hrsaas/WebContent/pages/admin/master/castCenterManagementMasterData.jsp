<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="CastCenterManagementMaster" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="show" value="%{show}" />
	<s:hidden name="hiddenCode" />
	<s:hidden name="castCode"/>
	

<table width="100%" border="0" align="right" class="formbg">
		
		<tr>
			<td valign="bottom" class="txt" colspan="3">
			<table  width="100%" border="0"  class="formbg" >
			<tr>
			<td>
			<strong class="text_head"><img
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25" /></strong></td>
			<td width="97%" class="txt"><strong class="text_head">Cost Center Management
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

							<td width="25%" colsspan="1"><label  class = "set" name="cast.abbr" id="cast.abbr1" ondblclick="callShowDiv(this);"><%=label.get("cast.abbr")%></label> :<font
								color="red">*</font></td>
							<td width="75%" colsspan="3"><s:textfield size="25"
								name="castAbbr" maxlength="10"
								 /></td>

						</tr>
						<tr>
							<td width="25%" colspan="1"><label  class = "set" name="cast.name" id="cast.name1" ondblclick="callShowDiv(this);"><%=label.get("cast.name")%></label> :<font color="red">*</font></td>
							<td width="75%" colsspan="3"><s:textfield size="25"
								name="castName" maxlength="50"
								/></td>
							</tr>
						</table>
					</td>
				</tr>
	</table>
		</td></tr>
							
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">

				
				<tr id='ctrlHide'>
					<td width="25%" colspan="1"><label  class = "set" name="subcast.abbr" id="subcast.abbr1" ondblclick="callShowDiv(this);"><%=label.get("subcast.abbr")%></label> :<font color="red">*</font></td>
					<td width="75%" colspan="1"><s:textfield size="25"
						maxlength="10" name="subcastAbbr" /> <s:hidden name="hiddenEdit" /></td>

				</tr>
				<tr id='ctrlHide'>
					<td width="25%" colspan="1"><label  class = "set" name="subcast.center" id="subcast.center1" ondblclick="callShowDiv(this);"><%=label.get("subcast.center")%></label> :<font color="red">*</font></td>
					<td width="75%" colspan="1"><s:textfield size="25"
						maxlength="50" name="subcastName"  /></td>

				</tr>
				
				
				</td>
				</tr>
			</table>
		
		
		
		
		
		<tr id='ctrlHide'>
					<td width="25%" colspan="1">&nbsp;</td>
					<td colspan="1" align="left"><s:submit type="button" cssClass="add"
					action="CastCenterManagementMaster_addItem"
						 value="Add Sub Cast Center" onclick="return callAdd()" /></td>

				</tr>
				
				<tr><td colspan="3">
				<table class="formbg" width="100%">

				<tr>
						<td class="formth" align="center" width="10%"><label  class = "set" name="serial.no" id="serial.no1" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
					<td align="center" class="formth" width="40%"><label  class = "set" name="subcast.abbr" id="subcast.abbr" ondblclick="callShowDiv(this);"><%=label.get("subcast.abbr")%></label></td>
					<td align="center" class="formth" width="30%"><label  class = "set" name="subcast.center" id="subcast.center" ondblclick="callShowDiv(this);"><%=label.get("subcast.center")%></label></td>

					<td align="right" class="formth" nowrap="nowrap" width="10%">
					
					
					<input type="button"
						class="token" theme="simple" value="Remove"
						onclick=" return chkDelete2()" /></td>


					<%
					int count1 = 0;
					%>
					<%!int d1 = 0;%>
					<%
					int ii = 0;
					%>
<%!	int val = 0;
					%>
					
					<s:iterator value="list">

						<tr <%if(count1%2==0){
									%> class="tableCell1" <%}else{%>
							class="tableCell2" <%	}count1++; %>
							onmouseover="javascript:newRowColor(this);"
							title="Double click for edit"
							onmouseout="javascript:oldRowColor(this,<%=count1%2 %>);"
							ondblclick="javascript:callForEdit('<s:property value="subCAbbr"/>','<s:property value="subCName"/>',<s:property value="srNo"/>);">



							<td width="10%"  align="center" class="sortableTD"><%=++ii%><input type="hidden" name="srNo"
								value="<%=ii%>" /></td>

							<td class="sortableTD"><s:property value="subCAbbr" />&nbsp;<s:hidden
								name="subCAbbr" /></td>
							<td class="sortableTD"><s:property value="subCName" />&nbsp;<s:hidden name="subCName" />&nbsp;</td>
							<input type="hidden" name="hdeleteOp" id="hdeleteOp<%=ii%>" />
							<td width="10%" align="center" nowrap="nowrap"class="sortableTD"><input
								type="checkbox" class="checkbox" value="N" id="confChkop<%=ii%>"
								name="confChkop" onclick="callForDelete2('<%=ii%>')" /></td>
						</tr>

					</s:iterator>
					<%
					d1 = ii;
					%>
				</tr>
				</table></td></tr>
				<s:hidden name="subcode" />
	<s:hidden name="tableLength" />
			
		<tr><td><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td></tr>
</table></s:form>

<script>



function oldRowColor(cell,val) {
	
	if(val=='1'){
	 cell.className='tableCell2';
	}
	else cell.className='tableCell1';
		//cell.style.backgroundColor="#ffffff";
	}
	
		function newRowColor(cell) {
	//	cell.style.backgroundColor="#E2F2FE";
		//cell.style.cursor='hand';
		cell.className='onOverCell';
	//	document.getElementById(cell).backgroundColor="#FFA31D";
	}
function callForDelete(id){

	
	  var conf=confirm("Are you sure !\n You want to Remove this record ?");
  			if(conf) {
  			document.getElementById("paraFrm").action="CastCenterManagementMaster_edit.action";
	  		document.getElementById('paraFrm_subcode').value=id;
	   		document.getElementById("paraFrm").submit();
  				}
  			else
  			{
  				return false;
  			}
  		
	    
   }


function callForEdit(subCAbbr,subCName,sno){

 		 
	   	 document.getElementById('paraFrm_hiddenEdit').value=sno;
	 	document.getElementById('paraFrm_subcastAbbr').value=subCAbbr;
	   	document.getElementById('paraFrm_subcastName').value=subCName;
	   	
	   	return false;
	   	
	   	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'CastCenterManagementMaster_edit.action';
  	 	
		document.getElementById('paraFrm').submit();
		
return true;
  		
  		}



function saveFun()
{
	

   var name=document.getElementById('paraFrm_castName').value;
   var abbr=document.getElementById('paraFrm_castAbbr').value;
  if(abbr=="")
  {
  alert('Please Enter '+document.getElementById('cast.abbr1').innerHTML.toLowerCase());
  return false;
  }
  
 
  if(name=="")
  {
  alert('Please Enter '+document.getElementById('cast.name1').innerHTML.toLowerCase());
  return false;
  }
      	  
     	 if(document.getElementById("paraFrm_tableLength").value=="0"){
     	 alert("Please Enter the subcast center details ");
     	 document.getElementById("paraFrm_subcastAbbr").focus();
     	 return false;
     	 } 	 
     	
  document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'CastCenterManagementMaster_save.action';
  	 	
		document.getElementById('paraFrm').submit();
		

   }

function resetFun() {
		document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'CastCenterManagementMaster_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'CastCenterManagementMaster_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'CastCenterManagementMaster_delete.action';
		document.getElementById('paraFrm').submit();
	}
	function callSearch(action) {
		var myWinDiv = window.open('', 'myWinDiv', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv';
		document.getElementById("paraFrm").action = 'CastCenterManagementMaster_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}
	
	
	 function callForDelete2(id)
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
   		
   		function chkDelete2()
	{
		 if(chk2()){
	 var con=confirm('Do you want to delete these records');
	 if(con){
	   document.getElementById('paraFrm').action="CastCenterManagementMaster_deleteDtl.action";
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
	 alert('Please Select Atleast one Record To Delete');
	 	 return false;
	 }
	 
	}	
	function chk2(){
	 var flag='<%=d1 %>';
	  for(var a=1;a<=flag;a++){	
	   if(document.getElementById('confChkop'+a).checked == true)
	   {	
	 	    return true;
	   }	   
	  }
	  return false;
	}
	
 function callAdd(){

 	var abbr=document.getElementById('paraFrm_subcastAbbr').value;
	var name=document.getElementById('paraFrm_subcastName').value;
	
	
	if(abbr==""){
	  alert("Please enter "+document.getElementById('subcast.abbr1').innerHTML.toLowerCase());
	 document.getElementById('paraFrm_subcastAbbr').value='';
	  return false;
	}
	if(name==""){
	  alert("Please enter "+document.getElementById('subcast.center1').innerHTML.toLowerCase());
	 document.getElementById('paraFrm_subcastName').value='';
	  return false;
	}
      	  	 return true;
      	  	 

   }
  
</script>


