<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript">
	var records = new Array();
</script>
<s:form action="EmployeeSurveyQuestionnaire" validate="true"
	id="paraFrm" theme="simple">
	<s:hidden name="show" />
	<s:hidden name="noData" />
	<s:hidden name="myPage" id="myPage" />
	<s:hidden name="hiddencode" />
	<s:hidden name="paraId" />
	<s:hidden name="optionId" />
	<s:hidden name="optionFlag" />
	<s:hidden name="optionTextarea" />
	<s:hidden name="questionView" />
	<s:hidden name="flagShow" />
	<s:hidden name="selectname" id="selectname" />

	<table width="100%" border="0" align="right" class="formbg">

		<tr>
			<td colspan="3">
			<table width="100%" cellpadding="0" cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/common/css/default/images/review_shared.gif"
						width="25" height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head"><label
						class="set" name="survey.questionnaire" id="survey.questionnaire"
						ondblclick="callShowDiv(this);"><%=label.get("survey.questionnaire")%></label></strong></td>
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
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><input type="button" name="Search"
						value="Search" class="search" onclick="callSearch('f9Search');" /> <input
						type="button" name="Save" value="Save" class="save"
						onclick="callSave();" /> <input type="button" name="Delete"
						value="Delete" class="delete" onclick="callDelete();" /> <input
						type="button" name="Reset" value="Reset" class="reset"
						onclick="callReset();" /></td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
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
							<td width="20%" height="22" class="formtext"><label
								class="set" name="survey.name" id="survey.name"
								ondblclick="callShowDiv(this);"><%=label.get("survey.name")%></label><font
								color="red">*</font> :</td>
							<td width="34%" height="22" nowrap="nowrap"><s:hidden
								name="surveyCode" /><s:textfield name="surveyName" size="30"
								maxlength="30" readonly="true" /> <a href="#"><img
								src="../pages/common/css/default/images/search2.gif" width="16"
								height="15" border="0" onclick="callSearch('searchSurvey');" /></a></td>

						</tr>


						<tr>
							<td width="15%" colspan="1"><label class="set"
								name="survey.section" id="survey.section"
								ondblclick="callShowDiv(this);"><%=label.get("survey.section")%></label>
							<font color="red">*</font> :</td>
							<td width="35%" colspan="1"><s:hidden
								name="sectionCode" /><s:textfield name="sectionName"
								size="30" readonly="true" /><a href="#"><img
								src="../pages/common/css/default/images/search2.gif" width="16"
								height="15" border="0" onclick="callSearch('searchSection');" /></a></td>
						</tr>

						<tr>
							<td width="22%" height="22" valign="top" class="formtext"
								nowrap="nowrap"><label class="set" name="qsn" id="qsn"
								ondblclick="callShowDiv(this);"><%=label.get("qsn")%></label><font
								color="red">*</font> :</td>
							<td colspan="3" nowrap="nowrap"><s:hidden name="quesCode" />
							<s:textarea theme="simple" name="question" rows="4" cols="70"
								onkeypress="return allCharacters()"
								onkeyup="callLength('question','descCnt','1000');"
								readonly="false" /> <img src="../pages/images/zoomin.gif"
								height="12" align="bottom" width="12" theme="simple"
								onclick="javascript:callWindow('paraFrm_question','qsn','','paraFrm_descCnt','1000');">
							&nbsp;Remaining chars <s:textfield name="descCnt" readonly="true"
								size="4"></s:textfield></td>
						</tr>

						<tr>
							<td height="22" class="formtext" nowrap="nowrap"><label
								class="set" name="qsnType" id="qsnType"
								ondblclick="callShowDiv(this);"><%=label.get("qsnType")%></label>:</td>
							<td height="22" colspan="1">
							<table>
								<tr>
									<td colspan="1"><s:select theme="simple" name="ansOptions"
										onchange="showText();" disabled="false"
										list="#{'O':'Objective','S':'Subjective'}" /></td>
									<td id="buttonValue" colspan="2" align="left"><input
										type="button" class="add" value="Add Answer Option"
										onclick="return callAnswerOpt();" /></td>
										
										<!-- ---------checkbox -->
										<td id="isMulti">Is Multi Select</td>
										
										
										<td id="multipleSelect"><s:checkbox name="multipleSelect"     /></td>
								</tr>
							</table>
							</td>
						</tr>


					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<s:if test="optionFlag">
			<tr>
				<td>

				<table width="100%" border="0" cellpadding="1" cellspacing="1"
					id="tblOption" class="formbg">
					<tr>
						<td align="left" class="txt" colspan="5"><strong
							class="text_head">Answer Option List :</strong></td>
					</tr>
					<tr>
						<td class="formth" align="center" width="10%" nowrap="nowrap"><b><label
							class="set" name="serial.no" id="serial.no"
							ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b></td>
						<td class="formth" align="center" width="50%"><b><label
							class="set" name="opt" id="opt" ondblclick="callShowDiv(this);"><%=label.get("opt")%></label></b></td>
						<td class="formth" align="center" width="20%">Edit|Remove</td>



					</tr>

					<%
					int j = 0;
					%>
					<%
					int k = 0;
					%>
					<%!int i = 0;%>
					<s:iterator value="optionList">


						<tr>
							<td width="10%" align="center" class="sortableTD"><%=++j%><s:hidden
								name="srNo" /> <input type="hidden" name="optionName"
								id="opt<%=j %>" value="<s:property value="optionName" />" /></td>
							<td width="50%" class="sortableTD"><s:property
								value="optionName" /><s:hidden name="optionCode" id='<%="optionCode" + j%>'/></td>
							<script type="text/javascript">
								records[<%=j%>] = document.getElementById('optionCode' + '<%=j%>').value;
							</script>
							<input type="hidden" name="hdeleteOp<%=j%>" id="hdeleteOp<%=j%>" />
							<td width="20%" align="center" class="sortableTD" nowrap="nowrap">
							<input type="button" class="edit" value="Edit"
								onclick="callforedit(document.getElementById('opt<%=j%>').value,'<%=j%>','<s:property value="optionCode"/>');" />
							<input type="button" class="delete" value="Remove"
								onclick="callfordel('<%=j%>','<s:property value="optionCode"/>');" />
							</td>
						</tr>

						<%
						k++;
						%>
					</s:iterator>
					<%
					i = k;
					%>

				</table>

				</td>
			</tr>
		</s:if>


	</table>
</s:form>

<script>

function saveFun(){
			var ansRow =<%=i%>;
			var qsn=trim(document.getElementById('paraFrm_question').value);
			var sub=trim(document.getElementById('paraFrm_subject').value);
			var cate=trim(document.getElementById('paraFrm_category').value);
			var flag=document.getElementById('paraFrm_optionFlag').value;
			var lim=trim(document.getElementById('paraFrm_limit').value);
			var option=document.getElementById('paraFrm_ansOptions').value;
			if(sub==""){
				alert("Please select the "+ document.getElementById('sub').innerHTML.toLowerCase());	
				return false;
			}
			else if(cate==""){
				alert("Please select the "+ document.getElementById('cat').innerHTML.toLowerCase());	
				return false;
			}else if(qsn==""){
				alert("Please enter the "+ document.getElementById('qsn').innerHTML.toLowerCase());
				document.getElementById('paraFrm_question').focus();	
				return false;	
			}else if(qsn != "" && qsn.length > 2000){
			    alert("Maximum length of "+ document.getElementById('qsn').innerHTML.toLowerCase()+" is 2000 characters.");
			    return false;
			}else if(option=="O"){
					  if(flag=="false" || ansRow==0){
						alert("Please add the answer options");
						return false;
						}
				
			
			}else if(option=="S"){
				if(lim==""){
				 alert("Please enter the "+document.getElementById('lim').innerHTML.toLowerCase());
				 return false;
				}
			}
		 document.getElementById('paraFrm').action="QuestionBankAction_save.action";
		 document.getElementById('paraFrm').submit();	
		return false;
}			


function cancelFun(){
    	document.getElementById('paraFrm').action="QuestionBankAction_cancelFirst.action";
        document.getElementById("paraFrm").submit();
        return false;
    
}
//For Report Button
function reportFun()
{
	document.getElementById('paraFrm').action="QuestionBankAction_report.action";
    document.getElementById("paraFrm").submit();
}


function addValidation(){
var val=trim(document.getElementById('paraFrm_option').value);
var qsn=trim(document.getElementById('paraFrm_question').value);

	if(qsn==""){
		alert('Please enter the '+ document.getElementById('qsn').innerHTML.toLowerCase());
		return false;
	}else if(val==""){
		alert('Please enter the '+document.getElementById('ansopt').innerHTML.toLowerCase());
		return false;

	}else {
	
		document.getElementById('paraFrm').action='QuestionBankAction_addOption.action';
		document.getElementById('paraFrm').submit();
	}
}

 function callForOption(id)
	   {
	 	
	
	   if(document.getElementById('confChkop'+id).checked==true)
	   {	  
	    document.getElementById('hdeleteOp'+id).value="Y";
	    alert("sssss"+document.getElementById('hdeleteOp'+id).value);
	   }
	   else{
	   document.getElementById('hdeleteOp'+id).value="";
	    
	   	
   		}
  }
   
   function callForOptionAll()
	   {
	
		var tbl = document.getElementById('tblOption');
		var rowLen = tbl.rows.length;
		if(document.getElementById('chkOpt').checked){
		 for(i = 1; i < rowLen; i++){
			document.getElementById('confChkOp'+i).checked = true;
			  document.getElementById('hdeleteOp'+i).value="Y";
		  }
			
	    }else{
	    
	     for(i = 1; i < rowLen; i++){
			document.getElementById('confChkop'+i).checked =false;
			  document.getElementById('hdeleteOp'+i).value="";
		  }
	   }	
	
  }
   
   function deleteOption()
	{
	 if(chkOption()){
	 var con=confirm('Do you really want to delete this record ?');
	 if(con){
	    document.getElementById('paraFrm').action="QuestionBankAction_deleteForOption.action";
  		document.getElementById('paraFrm').submit();
	    } else{
	     return true;
	    }
	 }else {
	 	alert('Please select a record to delete');
	 	 return false;
	 }
	 
	}
	
	
	
	function chkOption(){
	
		
			var tbl = document.getElementById('tblOption');
			var rowLen = tbl.rows.length;
	
	  for(var a=1;a<rowLen;a++){	
	   if(document.getElementById('confChkop'+a).checked == true)
		   {	
	 	    return true;
	   }	   
	  }
	  return false;
	}



function callPage(id){
	   if(id=='P'){
		var p=document.getElementById('myPage').value;
		id=eval(p)-1;
		}
		if(id=='N'){
		var p=document.getElementById('myPage').value;
		id=eval(p)+1;
		}
		document.getElementById('myPage').value=id;
		document.getElementById('paraFrm_show').value=id;
		document.getElementById('paraFrm').action='QuestionBankAction_callPage.action';
		document.getElementById('paraFrm').submit();
		
	}	
   
   

   function on()
   {
	  	var val= document.getElementById('selectname').value;
		document.getElementById('paraFrm_show').value=val;
		document.getElementById('myPage').value=eval(val);
		document.getElementById('selectname').value=val;
		document.getElementById('paraFrm').action="QuestionBankAction_callPage.action";
	    document.getElementById('paraFrm').submit();
   }
   
    
   pgshow();
  	function pgshow()
  	{
  	var pgno=document.getElementById('paraFrm_show').value;
  
  	if(!(pgno==""))
  	 document.getElementById('selectname').value=pgno;
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
	
	function chkDelete2()
	{
		 if(chk2()){
	 var con=confirm('Do you want to delete these records');
	 if(con){
	   document.getElementById('paraFrm').action="QuestionBankAction_deleteOption.action";
	    document.getElementById('paraFrm').submit();
	    }
	    else{
	   	var tbl = document.getElementById('tblOption');
			var rowLen = tbl.rows.length;
	
	  	for(var a=1;a<rowLen;a++){	
	   document.getElementById('confChkop'+a).checked=false;
	    }
	     return false;
	 }
	 }
	 else {
	 alert('Please select one record to delete');
	 	 return false;
	 }
	 
	}
	
function callForEdit(id){
	  	document.getElementById('paraFrm_hiddencode').value=id;
	  	document.getElementById("paraFrm").action="QuestionBankAction_calforedit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
   }	 
   
   
   
	function callAnswerOpt(){
		var qsn=trim(document.getElementById('paraFrm_question').value);
		document.getElementById('paraFrm_optionTextarea').value="";
		document.getElementById('paraFrm_paraId').value="";
       	if(qsn==""){
			alert("Please enter the "+ document.getElementById('qsn').innerHTML.toLowerCase());
			document.getElementById('paraFrm_question').focus();	
			return false;	
		}else if(qsn != "" && qsn.length >2000){
			alert("Maximum length of "+ document.getElementById('qsn').innerHTML.toLowerCase()+" is 2000 characters.");
			return false;
		}
	    var wind = window.open('','wind','toolbar=no,location=no,status=no,menubar=no,width=600,height=200,scrollbars=no,resizable=no,menubar=no,top=200,left=100');	 
		document.getElementById('paraFrm').target = "wind";
		document.getElementById('paraFrm').action = "EmployeeSurveyQuestionnaire_showAnsOpt.action";
	    document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
	}
	
	function callSearch(action) {
		var myWinDiv1 = window.open('', 'myWinDiv1', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv1';
		document.getElementById("paraFrm").action = 'EmployeeSurveyQuestionnaire_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}
	
	function callforedit(id,id1,id3){
		document.getElementById("paraFrm_paraId").value=id1;
		var wind = window.open('','wind','toolbar=no,location=no,status=no,menubar=no,width=600,height=200,scrollbars=no,resizable=no,menubar=no,top=200,left=100');	 
		document.getElementById('paraFrm').target = "wind";
		document.getElementById('paraFrm').action = "EmployeeSurveyQuestionnaire_callUpdate.action?opt="+id+'&para='+id1;
	    document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
	}
	
	 function callfordel(id,id1){
		var conf=confirm("Do you really want to remove this record ?");
		if(conf) {
			document.getElementById("paraFrm_paraId").value=id;
			document.getElementById("paraFrm_optionId").value=id1;
			document.getElementById("paraFrm").action="EmployeeSurveyQuestionnaire_deleteForOption.action";
		    document.getElementById("paraFrm").submit();
	    }
   }
   
   function callSave(){
		var surveyCode = document.getElementById('paraFrm_surveyCode').value;
		if(surveyCode==""){
			alert("Please select "+document.getElementById('survey.name').innerHTML.toLowerCase());
			return false;
		}
		
		var sectionCode = trim(document.getElementById('paraFrm_sectionCode').value);
		if(sectionCode==""){
			alert("Please select "+document.getElementById('survey.section').innerHTML.toLowerCase());
			return false;
		}
		
		var ansRow =<%=i%>;
		/*alert(records.length);*/
		var qsn=trim(document.getElementById('paraFrm_question').value);
		var option=document.getElementById('paraFrm_ansOptions').value;
		if(qsn==""){
			alert("Please enter the "+ document.getElementById('qsn').innerHTML.toLowerCase());
			document.getElementById('paraFrm_question').focus();	
			return false;	
		}else if( qsn.length > 1000){
		    alert("Maximum length of Answer Option is 1000 characters");
		    
		   /* alert("Maximum length of "+ document.getElementById('qsn').innerHTML.toLowerCase()+" is 2000 characters.");*/
		    return false;
		}else if(option=="O"){
				  if(records.length==0){
					alert("Please add the answer options");
					return false;
					}
			
		
		}
		document.getElementById('paraFrm').target = "_self";
		 document.getElementById('paraFrm').action="EmployeeSurveyQuestionnaire_save.action";
		 document.getElementById('paraFrm').submit();	
		return false;
	}
	
	function callDelete(){
		var surveyCode = trim(document.getElementById('paraFrm_surveyCode').value);
		if(surveyCode==""){
			alert("Please select a record to delete ");
			return false;
		}else{
			var con=confirm('Do you want to delete the record(s) ?');
			if(con){
				document.getElementById('paraFrm').target = "_self";
		      	document.getElementById('paraFrm').action="EmployeeSurveyQuestionnaire_delete.action";
			  	document.getElementById('paraFrm').submit();  
	      	}
	      }
	}
	
	function callReset(){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action="EmployeeSurveyQuestionnaire_reset.action";
	  	document.getElementById('paraFrm').submit();  
	}
	
	showText();
	
	function showText(){
		var chq = document.getElementById('paraFrm_ansOptions').value;
		if(chq=="O" ){
		document.getElementById('buttonValue').style.display='';
		document.getElementById('multipleSelect').style.display='';
		
		document.getElementById('isMulti').style.display='';
		}else if(chq=="S" || chq==""){
			document.getElementById('buttonValue').style.display='none';
			document.getElementById('multipleSelect').style.display='none';
				document.getElementById('isMulti').style.display='none';
	   	}
	}
	
	

	
	
	
		
</script>



