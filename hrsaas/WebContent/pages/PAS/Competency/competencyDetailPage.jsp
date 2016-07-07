<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="CompetencyMaster" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="show" value="%{show}" />
	<s:hidden name="hiddenCode" />
	<s:hidden name="competencyCode" />

	<table width="100%" border="0" align="right" class="formbg">

		<tr>
			<td width="100%" colspan="3">
			<table width="100%" border="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="97%" class="txt"><strong class="text_head">Competency Master
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
			<td width="100%" colspan="3">
			<table width="100%">
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
			<td width="100%" colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>

					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="25%" colsspan="1"><label class="set"
								name="competencyTitle" id="competencyTitle"
								ondblclick="callShowDiv(this);">Competency Title</label>
							:<font color="red">*</font></td>
							<td width="75%" colsspan="3">
							<s:textfield size="50"
								name="competencyTitle" maxlength="50"
								onkeypress="return allCharacters();" /></td>
						</tr>
						<tr>
							<td width="25%" colsspan="1"><label class="set"
								name="competencyDesc" id="competencyDesc"
								ondblclick="callShowDiv(this);">Competency Description</label>
							:<font color="red">*</font></td>
							<td width="75%" colsspan="3">
								<s:textarea rows="3" cols="40" name="competencyDesc" onkeyup="callLength('competencyDesc','descCnt','1500');"></s:textarea>
									<label id='ctrlHide' >&nbsp; Remaining characters &nbsp;</label>
                    				<s:textfield name="descCnt" readonly ="true" size="5" cssStyle="background-color: #F2F2F2"></s:textfield>
							</td>
						</tr>
						<tr>
							<td width="25%" colsspan="1"><label class="set"
								name="competencyIndicator" id="competencyIndicator"
								ondblclick="callShowDiv(this);">Competency Indicator</label>
							:<font color="red">*</font></td>
							<td width="75%" colsspan="3"><s:textarea rows="3" cols="40" name="competencyIndicator" onkeyup="callLength('competencyIndicator','descCnt1','1500');"></s:textarea>
									<label id='ctrlHide' >&nbsp;Remaining characters &nbsp;</label>
                    				<s:textfield name="descCnt1" readonly ="true" size="5" cssStyle="background-color: #F2F2F2"></s:textfield>
                    		</td>
						</tr>
						
						<tr>
							<td width="100%" colspan="4">&nbsp;</td>
						</tr>
					</table>
					</td>
				</tr>

			</table>
		<tr>
		<tr>
			<td width="100%" colspan="3"  id='ctrlHide'>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="100%" colspan="2" class="formth"><b>Competency Level</b></td>
				</tr>
				<tr>
					<td width="25%" >&nbsp;</td>		
					<td width="75%" >&nbsp;</td>					
				</tr>
				
				<tr >
					<td width="25%" colspan="1"><label class="set"
						name="competencyLevel" id="competencyLevel" ondblclick="callShowDiv(this);">Level</label>
					:<font color="red">*</font></td>
					<td width="75%" colspan="1"><s:textfield size="40"
						maxlength="2" name="compLevel" onkeypress="return numbersOnly();" /> 
					</td>
				</tr>
				<tr >
					<td width="25%" colspan="1"><label class="set"
						name="competencyLevelTitle" id="competencyLevelTitle" ondblclick="callShowDiv(this);">Level Title</label>
					:<font color="red">*</font></td>
					<td width="75%" colspan="1"><s:textfield size="40"
						maxlength="35" name="compLevelTitle"  /> 
					</td>
				</tr>
				<tr>
					<td width="25%" colsspan="1"><label class="set"
						name="competencyLevelDesc" id="competencyLevelDesc"
						ondblclick="callShowDiv(this);">Level Description</label> :<font
						color="red">*</font></td>
					<td width="75%" colsspan="1"><s:textarea rows="3" cols="40"
						name="compLevelDesc"
						onkeyup="callLength('compLevelDesc','descCnt2','1500');"></s:textarea>
					&nbsp; 
					Remaining characters &nbsp; <s:textfield name="descCnt2"
						readonly="true" size="5" cssStyle="background-color: #F2F2F2"></s:textfield>
					</td>
				</tr>

				<tr>
					<td width="25%" colspan="1">&nbsp;</td>
					<td colspan="1">
					<input type="button" value=" Add " onclick="return callAdd()">
					</td>

				</tr>
				<tr>
					<td width="25%" >&nbsp;</td>		
					<td width="75%" >&nbsp;</td>					
				</tr>
				
			</table>
			</td>
		</tr>



		<tr>
			<td width="100%" colspan="3">
			<table class="formbg" id="myTable" width="100%">

				<tr>
					<td align="center" class="formth" width="10%">Level</td>
					<td align="center" class="formth" width="40%"><label
						class="set" name="attribute" id="attribute1"
						ondblclick="callShowDiv(this);">Level Title</label></td>
					<td align="center" class="formth" width="30%"><label
						class="set" name="value" id="value1"
						ondblclick="callShowDiv(this);">Level Desc</label></td>
							<s:hidden name="hiddenEdit"/>
					<td align="right" class="formth" nowrap="nowrap" width="10%">
					<label name="deleteEdit"   id="deleteEdit" ondblclick="callShowDiv(this);"><%=label.get("deleteEdit")%></label>
				</tr>


				<%
				int count1 = 0;
				%>
				<%!int d1 = 0;%>
				<%
				int ii = 0;
				%>
				<%!int val = 0;%>

				<s:iterator value="competencyLevelList">

					<tr <%if(count1%2==0){
									%> class="tableCell1" <%}else{%>
						class="tableCell2" <%	}count1++; %>
						onmouseover="javascript:newRowColor(this);"
						title="Double click for edit"
						onmouseout="javascript:oldRowColor(this,<%=count1%2 %>);"
						ondblclick="javascript:callForEdit('<s:property value="competencyLevel"/>','<s:property value="competencyLevelTitle"/>','<s:property value="competencyLevelDesc"/>');">

						<td width="10%" align="center" class="sortableTD">
							<s:property value="competencyLevel" />&nbsp;
							<s:hidden name="competencyLevel" />
						
						</td>
						<td class="sortableTD">
							<s:property value="competencyLevelTitle" />&nbsp;
							<s:hidden name="competencyLevelTitle" />
						</td>
						<td class="sortableTD">
							<s:property value="competencyLevelDesc" />&nbsp;
							<s:hidden	name="competencyLevelDesc" />&nbsp;
						</td>
						<input type="hidden" name="hdeleteOp" id="hdeleteOp<%=ii%>" />
						<td width="10%" align="center" nowrap="nowrap" class="sortableTD">
						
						<input type="button" class="rowDelete"	theme="simple"  title="Delete Record" 
										onclick="deleteRecord('<s:property value="competencyLevel"/>')"/>
						</td>
					</tr>

				</s:iterator>
				<%
				d1 = count1;
				%>

				<s:hidden name="subcode" />
				<s:hidden name="tableLength" />
			</table>

			</td>
		</tr>


		<tr>
			<td width="100%" colspan="3"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
</s:form>

<script>



function oldRowColor(cell,val) {
	if(val=='1'){
	 cell.className='tableCell2';
	}
	else cell.className='tableCell1';
		
}
function newRowColor(cell) {
	cell.className='onOverCell';
}
function callForDelete(id){
	  var conf=confirm("Are you sure !\n You want to Remove this record ?");
  			if(conf) {
  			document.getElementById("paraFrm").action="QuestionnaireMaster_edit.action";
	  		document.getElementById('paraFrm_subcode').value=id;
	   		document.getElementById("paraFrm").submit();
  				}
  			else
  			{
  				return false;
  			}
  		
	    
}
function callAdd(){
   var fieldName = ["paraFrm_compLevel","paraFrm_compLevelTitle","paraFrm_compLevelDesc"];
   var lableName = ["competencyLevel","competencyLevelTitle","competencyLevelDesc"];
   var flag = ["enter","enter","enter"];
   if(!validateBlank(fieldName, lableName,flag)){
         return false;
   }  
   var compLevelDesc=document.getElementById('paraFrm_compLevelDesc').value;
   if(compLevelDesc.length>1500 ){
	  alert("Maximum length of Competency Level Description is 1500");
	  return false;
   }
   document.getElementById('paraFrm').target = "_self";
   document.getElementById('paraFrm').action = 'CompetencyMaster_addItem.action';
   document.getElementById('paraFrm').submit();
}

function callForEdit(complevel,compleveltitle,compleveldesc){
	document.getElementById('paraFrm_hiddenEdit').value=complevel;
   	document.getElementById('paraFrm_compLevel').value=complevel;
 	document.getElementById('paraFrm_compLevelDesc').value=compleveldesc;
   	document.getElementById('paraFrm_compLevelTitle').value=compleveltitle;   	
   	return false;
}
function saveFun()
{
		  
	  var fieldName = ["paraFrm_competencyTitle","paraFrm_competencyDesc","paraFrm_competencyIndicator"];
	  var lableName = ["competencyTitle","competencyDesc","competencyIndicator"];
	  var flag = ["enter","enter","enter"];
	  if(!validateBlank(fieldName, lableName,flag)){
             return false;
      }  
		  
   	  var title=document.getElementById('paraFrm_competencyTitle').value;
   	  var desc=document.getElementById('paraFrm_competencyDesc').value;
   	  var indicator=document.getElementById('paraFrm_competencyIndicator').value;
   	
	  if(desc!="" && desc.length >1500 )
	  {
		  alert("Maximum length of Competency Description is 1500");
		  document.getElementById('paraFrm_competencyDesc').focus();
		  return false;
	  }
	  
	  if(indicator!="" && indicator.length >1500 )
	  {
		  alert("Maximum length of Competency Indicator is 1500");
		  document.getElementById('paraFrm_competencyIndicator').focus();
		  return false;
	  }
      if(document.getElementById("myTable").rows.length==1){
     	  	  alert("Please add level ");
     	  	  return false;
      }
      document.getElementById('paraFrm').target = "_self";
 	  document.getElementById('paraFrm').action = 'CompetencyMaster_save.action';
	  document.getElementById('paraFrm').submit();  
     	  
   }

function resetFun() {
	document.getElementById('paraFrm_show').value = '1';
 	document.getElementById('paraFrm').target = "_self";
 	document.getElementById('paraFrm').action = 'CompetencyMaster_reset.action';
	document.getElementById('paraFrm').submit();
}

function backFun() {
	document.getElementById('paraFrm').target = "_self";
    document.getElementById('paraFrm').action = 'CompetencyMaster_cancel.action';
	document.getElementById('paraFrm').submit();
}
	
function deleteFun() {
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'CompetencyMaster_delete.action';
		document.getElementById('paraFrm').submit();
	}
}

	


function editFun() {
		return true;
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
			 	  	
				   document.getElementById('paraFrm').action="CompetencyMaster_deleteLevelDtl.action?";
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
	 	else{
	 		alert('Please Select Atleast one Record To Delete');
	 	 	return false;
	    }
	 
	}
	
	
function deleteRecord(levelCode1){
	var con=confirm('Do you want to delete this records');
	 if(con){
	 	document.getElementById("paraFrm").action="CompetencyMaster_deleteLevelDtl.action?levelCode="+levelCode1+"";
		document.getElementById('paraFrm').target = "_self";
		document.getElementById("paraFrm").submit();
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
  
   
</script>


