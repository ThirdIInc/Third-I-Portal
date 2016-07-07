<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="QuestionnaireMaster" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="show" value="%{show}" />
	<s:hidden name="hiddenCode" />
	<s:hidden name="quesCode" />

	<table width="100%" border="0" align="right" class="formbg">

		<tr>
			<td width="100%" colspan="3">
			<table width="100%" border="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="97%" class="txt"><strong class="text_head">Questionnaire
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
								name="questionname" id="questionname"
								ondblclick="callShowDiv(this);"><%=label.get("questionname")%></label>
							:<font color="red">*</font></td>
							<td width="75%" colsspan="3"><s:textfield size="75"
								name="quesName" maxlength="150"
								onkeypress="return allCharacters();" /></td>
						</tr>
						<tr>
							<td width="25%" colspan="1"><label class="set"
								name="questiontype" id="questiontype"
								ondblclick="callShowDiv(this);"><%=label.get("questiontype")%></label>
							:<font color="red">*</font></td>
							<td width="75%" colspan="3"><s:select name="quesType"
								list="#{'':'--Select--','T':'Training','F':'Training Feedback','E':'Exit Interview','C':'Candidate Evaluation','I':'Induction Feedback'}" /></td>
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
					<td width="100%" colspan="2" class="formth"><b>OPTIONS</b></td>
				</tr>
				<tr>
					<td width="25%" >&nbsp;</td>		
					<td width="75%" >&nbsp;</td>					
				</tr>
				
				<tr >
					<td width="25%" colspan="1"><label class="set"
						name="attribute" id="attribute" ondblclick="callShowDiv(this);"><%=label.get("attribute")%></label>
					:<font color="red">*</font></td>
					<td width="75%" colspan="1"><s:textfield size="30"
						maxlength="35" name="quesAttr" /> <s:hidden name="hiddenEdit"/>
					</td>

				</tr>
				<tr >
					<td width="25%" colspan="1"><label class="set" name="value"
						id="value" ondblclick="callShowDiv(this);"><%=label.get("value")%></label>
					:<font color="red">*</font></td>
					<td width="75%" colspan="1"><s:textfield size="15"
						maxlength="5" name="quesValue" onkeypress="return numbersOnly();" /></td>

				</tr>

				<tr>
					<td width="25%" colspan="1">&nbsp;</td>
					<td colspan="1"><s:submit cssClass="add"
						action="QuestionnaireMaster_addItem" theme="simple" value="Add"
						onclick="return callAdd()" /></td>

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
			<table class="formbg" width="100%">

				<tr>
					<td align="center" class="formth" width="10%">Sr.No.</td>
					<td align="center" class="formth" width="40%"><label
						class="set" name="attribute" id="attribute1"
						ondblclick="callShowDiv(this);"><%=label.get("attribute")%></label></td>
					<td align="center" class="formth" width="30%"><label
						class="set" name="value" id="value1"
						ondblclick="callShowDiv(this);"><%=label.get("value")%></label></td>

					<td align="right" class="formth" nowrap="nowrap" width="10%">


					<input type="button" class="token" theme="simple" value="Delete"
						onclick=" return chkDelete2()" /></td>
				</tr>


				<%
				int count1 = 0;
				%>
				<%!int d1 = 0;%>
				<%
				int ii = 0;
				%>
				<%!int val = 0;%>

				<s:iterator value="list">

					<tr <%if(count1%2==0){
									%> class="tableCell1" <%}else{%>
						class="tableCell2" <%	}count1++; %>
						onmouseover="javascript:newRowColor(this);"
						title="Double click for edit"
						onmouseout="javascript:oldRowColor(this,<%=count1%2 %>);"
						ondblclick="javascript:callForEdit('<s:property value="attribute"/>','<s:property value="value"/>',<s:property value="srNo"/>);">

						<td width="10%" align="center" class="sortableTD"><%=++ii%>
							<input type="hidden" name="srNo" value="<%=ii%>" />
						</td>
						<td class="sortableTD">
							<s:property value="attribute" />&nbsp;
							<s:hidden name="attribute" />
						</td>
						<td class="sortableTD">
							<s:property value="value" />&nbsp;
							<s:hidden	name="value" />&nbsp;
						</td>
						<input type="hidden" name="hdeleteOp" id="hdeleteOp<%=ii%>" />
						<td width="10%" align="center" nowrap="nowrap" class="sortableTD"><input
							type="checkbox" class="checkbox" value="N" id="confChkop<%=ii%>"
							name="confChkop" onclick="callForDelete2('<%=ii%>')" />
						</td>
					</tr>

				</s:iterator>
				<%
				d1 = ii;
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
    //alert("callAdd");
    var fields=["paraFrm_quesAttr"];
  	var val=document.getElementById('paraFrm_quesAttr').value;
	var que=document.getElementById('paraFrm_quesValue').value;
		
	if(val==""){
	  alert("Please enter "+document.getElementById('attribute').innerHTML.toLowerCase());
	  document.getElementById('paraFrm_quesAttr').value='';
	  
	  return false;
	}
	if(que==""){
	  alert("Please enter "+document.getElementById('value').innerHTML.toLowerCase());
	  document.getElementById('paraFrm_quesValue').value='';
	  return false;
	}
	
	if(!f9specialchars(fields)){
              return false;
    }
    return true;	  	 
   }

function callForEdit(attvalue,value,sno){
 
	   	document.getElementById('paraFrm_hiddenEdit').value=sno;
	 	document.getElementById('paraFrm_quesAttr').value=attvalue;
	   	document.getElementById('paraFrm_quesValue').value=value;
	   	
	   	return false;
	   /*	
	   	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'QuestionnaireMaster_edit.action';
  	 	
		document.getElementById('paraFrm').submit();
		
		return true;
		*/
  		
  		}



function saveFun()
{
    	  var name=document.getElementById('paraFrm_quesName').value;
		  if(name=="")
		  {
			  alert('Please Enter '+document.getElementById('questionname').innerHTML.toLowerCase());
			  return false;
		  }
   		  var type=document.getElementById('paraFrm_quesType').value;
		  if(type=="")
		  {
			  alert('Please select '+document.getElementById('questiontype').innerHTML.toLowerCase());
			  return false;
		  }
      	  
     	 if(document.getElementById("paraFrm_tableLength").value=="0"){
	     	 alert("Please Enter the Options ");
	     	 document.getElementById("paraFrm_quesAttr").focus();
	     	 return false;
     	 } 	  
        document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'QuestionnaireMaster_save.action';
		document.getElementById('paraFrm').submit();
		
return true;
   }

function resetFun() {
		document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'QuestionnaireMaster_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'QuestionnaireMaster_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
		var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'QuestionnaireMaster_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	function callSearch(action) {
		var myWinDiv = window.open('', 'myWinDiv', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv';
		document.getElementById("paraFrm").action = 'QuestionnaireMaster_'+action+'.action';
		document.getElementById("paraFrm").submit();
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
	   document.getElementById('paraFrm').action="QuestionnaireMaster_deleteDtl.action";
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
  
   
</script>


