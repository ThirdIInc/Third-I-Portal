<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="QuestionBankAction" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="dataPath" />
	<s:hidden name="show" />
	<s:hidden name="noData" />
	<s:hidden name="myPage" id="myPage" />
	<s:hidden name="categoryCode" />
	<s:hidden name="subjectCode" />
	<s:hidden name="cancelFlag" />
	<s:hidden name="editFlag" />
	<s:hidden name="hiddencode" />
	<s:hidden name="paraId" />
	<s:hidden name="optionId" />
	<s:hidden name="optionFlag" />
	<s:hidden name="optionTextarea" />
	<s:hidden name="flag" />
	<s:hidden name="concatEx" />
	<s:hidden name="concatFlg" />
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
					<td width="93%" class="txt"><strong class="text_head">Question
					Bank </strong></td>
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
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
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
								class="set" name="sub" id="sub" ondblclick="callShowDiv(this);"><%=label.get("sub")%></label><font
								color="red">*</font> :</td>
							<td width="34%" height="22" nowrap="nowrap"><s:textfield
								name="subject" size="30" maxlength="30" readonly="true" /> <a
								href="#"><img
								src="../pages/common/css/default/images/search2.gif" width="16"
								height="15" border="0" onclick="callSubject();" /></a></td>

							<td width="44%" height="22" class="formtext">
							<table width="100%">
								<tr>
									<td width="30%" height="22" class="formtext"><label
										class="set" name="cat" id="cat"
										ondblclick="callShowDiv(this);"><%=label.get("cat")%></label><font
										color="red">*</font> :</td>
									<td height="22" class="formtext" nowrap="nowrap"><s:textfield
										name="category" size="30" maxlength="30" readonly="true" /> <a
										href="#"><img
										src="../pages/common/css/default/images/search2.gif"
										width="16" height="15" border="0" onclick="callCategory();" /></a>
									</td>
								</tr>
							</table>
							</td>
							<td></td>
						</tr>
						
						<tr>
							<td width="22%" height="22" class="formtext"><label
								class="set" name="complvl" id="complvl"
								ondblclick="callShowDiv(this);"><%=label.get("complvl")%></label>
							:</td>
							<td width="22%" height="22"><s:select theme="simple"
								name="compLevel" disabled="false" cssStyle="width:85"
								list="#{'E':'Easy','M':'Medium','H':'Hard'}" /></td>



							<td width="27%" height="22" class="formtext">
							<table width="100%">
								<tr>
									<td width="30%" height="22" class="formtext"><label
										class="set" name="sts" id="sts"
										ondblclick="callShowDiv(this);"><%=label.get("sts")%></label>
									:</td>
									<td height="22" class="formtext" colspan="3"><s:select
										name="qsnStatus" disabled="false"
										list="#{'A':'Active','D':'Deactive'}" /></td>
								</tr>
							</table>
							</td>
							<td width="18%"></td>
						</tr>



						<tr>
							<td width="22%" height="22" valign="top" class="formtext"
								nowrap="nowrap"><label class="set" name="qsn" id="qsn"
								ondblclick="callShowDiv(this);"><%=label.get("qsn")%></label><font
								color="red">*</font> :</td>
							<td colspan="3" nowrap="nowrap"><s:hidden name="quesCode" />
							<s:textarea theme="simple" name="question" rows="4" cols="70"
								onkeypress="return allCharacters()"
								onkeyup="callLength('question','descCnt','1999');"
								readonly="false" /> <img src="../pages/images/zoomin.gif"
								height="12" align="bottom" width="12" theme="simple"
								onclick="javascript:callWindow('paraFrm_question','qsn','','paraFrm_descCnt','1999');">
							&nbsp;Remaining chars <s:textfield name="descCnt" readonly="true"
								size="4"></s:textfield></td>
						</tr>


						<tr>
							<td height="22" class="formtext" nowrap="nowrap">
								<label
								class="set" name="qsnType" id="qsnType"
								ondblclick="callShowDiv(this);"><%=label.get("qsnType")%></label>:
							</td>
							
							<td height="22" colspan="1">
								<table>
									<tr>
										<td colspan="1"><s:select theme="simple" name="ansOptions"
											onchange="showText();" disabled="false"
											list="#{'O':'Objective','S':'Subjective'}" /></td>
										<td id="buttonValue" colspan="2" align="left"><input
											type="button" class="add" value="Add Answer Option"
											onclick="return callAnswerOpt();" /></td>
									</tr>
								</table>
							</td>
							
							<td height="22" colspan="1">
								<div id="limitValue">
								<table>
									<tr>
										<td colspan="1" width="36%"><label class="set" name="lim"
											id="lim" ondblclick="callShowDiv(this);"><%=label.get("lim")%></label>
										:</td>
										<td width="64%"><s:textfield theme="simple" name="limit"
											size="30" maxlength="3" onkeypress="return numbersOnly();" />
										</td>
									</tr>
								</table>
								</div>
							</td>
						</tr>
					
				<!-- UPLOAD DOC SECTION -- BEGINS -->		
						 <tr>
						 	<td colspan="3">
						 		<div id="uploadDocFlag">
						 		<table width="100%">
						 			<tr>
										<td width="22%" class="formtext" nowrap="nowrap">
											<label	class="set" name="uploadDocumentLabel" id="uploadDocumentLabel"
											ondblclick="callShowDiv(this);"><%=label.get("uploadDocumentLabel")%>
											</label>:
										</td>
										
										<td width="35%">
											 <s:textfield name="uploadFileName" size="45" readonly="true" cssStyle="background-color: #F2F2F2;" />
										</td>
										
										<td width="10%">
											<input type="button" value="Select File" class="token"
												onclick="uploadFile('uploadFileName');" />
										</td>
										<td width="33%">
											<input type="button" value="View File" class="token" onclick="showRecord();" />	
										</td>
									</tr>
						 		</table>
						 		</div>
						 	</td>
						 </tr>
				<!-- UPLOAD DOC SECTION -- ENDS -->		
						 
						 <tr id="allowToUploadDocFlag">
							<td width="22%" nowrap="nowrap">
								<label class="set" name="allowToUploadAnswerDocsLabel" id="allowToUploadAnswerDocsLabel"
								ondblclick="callShowDiv(this);"><%=label.get("allowToUploadAnswerDocsLabel")%></label> :
							</td>
							
							<td colspan="3" nowrap="nowrap">
								 <s:checkbox name="allowToUploadAnswer" />
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
							class="text_head">Option List :</strong></td>
					</tr>
					<tr>
						<td class="formth" align="center" width="10%" nowrap="nowrap"><b><label
							class="set" name="serial.no" id="serial.no"
							ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b></td>
						<td class="formth" align="center" width="50%"><b><label
							class="set" name="opt" id="opt" ondblclick="callShowDiv(this);"><%=label.get("opt")%></label></b></td>
						<td class="formth" align="center" width="20%"><b><label
							class="set" name="ansflg" id="ansflg"
							ondblclick="callShowDiv(this);"><%=label.get("ansflg")%></label></b></td>
						<td class="formth" align="center" width="20%">Edit|Remove</td>



					</tr>

					<% int j = 0;%>

					<s:iterator value="optionList">


						<tr>
							<td width="10%" align="center" class="sortableTD"><%=++j%><s:hidden
								name="srNo" /> <input type="hidden" name="optionName"
								id="opt<%=j %>" value="<s:property value="optionName" />" /></td>
							<td width="50%" class="sortableTD"><s:property
								value="optionName" /></td>
							<td width="20%" align="center" class="sortableTD"><s:property
								value="flagItt" /><s:hidden name="flagItt" /> <s:hidden
								name="optionCode" /></td>
							<input type="hidden" name="hdeleteOp<%=j%>" id="hdeleteOp<%=j%>" />
							<td width="20%" align="center" class="sortableTD" nowrap="nowrap">
							<input type="button" class="edit" value="Edit"
								onclick="callforedit(document.getElementById('opt<%=j%>').value,'<%=j%>','<s:property value="flagItt" />','<s:property value="optionCode"/>');" />
							<input type="button" class="delete" value="Remove"
								onclick="callfordel('<%=j%>','<s:property value="optionCode"/>');" />
							</td>
						</tr>


					</s:iterator>


				</table>

				</td>
			</tr>
		</s:if>

		<tr>
			<td><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
</s:form>

<script>

function saveFun(){
			
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
			}else if(qsn != "" && qsn.length >= 1999){
			    alert("Maximum length of "+ document.getElementById('qsn').innerHTML.toLowerCase()+" is 2000 characters.");
			    return false;
			}else if(option=="O"){
			  if(flag=="false"){
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

showText();


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



function showText(){
	var chq = document.getElementById('paraFrm_ansOptions').value;
	
	if(chq=="O" ){
		document.getElementById('buttonValue').style.display='';
		document.getElementById('limitValue').style.display='none';
		document.getElementById('uploadDocFlag').style.display = 'none';
		document.getElementById('allowToUploadDocFlag').style.display = 'none';
	}else if(chq=="S" || chq==""){
		document.getElementById('uploadDocFlag').style.display = '';
		document.getElementById('limitValue').style.display='';
		document.getElementById('allowToUploadDocFlag').style.display='';
		document.getElementById('buttonValue').style.display='none';
   }
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
   
   
   
	
	
	function callforedit(id,id1,id2,id3){
				
				document.getElementById("paraFrm_paraId").value=id1;
				var wind = window.open('','wind','toolbar=no,location=no,status=no,menubar=no,width=600,height=200,scrollbars=no,resizable=no,menubar=no,top=200,left=100');	 
				document.getElementById('paraFrm').target = "wind";
				document.getElementById('paraFrm').action = "QuestionBankAction_callUpdate.action?opt="+id+'&para='+id1+'&flg='+id2;
			    document.getElementById('paraFrm').submit();
				document.getElementById('paraFrm').target = "main";
		
			
	}
	
function callfordel(id,id1){
 var conf=confirm("Do you really want to remove this record ?");
	if(conf) {
		document.getElementById("paraFrm_paraId").value=id;
		document.getElementById("paraFrm_optionId").value=id1;
		document.getElementById("paraFrm").action="QuestionBankAction_deleteForOption.action";
	    document.getElementById("paraFrm").submit();
    }
}
   
function callSubject() {
   document.getElementById('paraFrm_categoryCode').value="";
   document.getElementById('paraFrm_category').value="";
   callsF9(500,325,'QuestionBankAction_f9action.action'); 
}
   
function callCategory(){
   if(document.getElementById('paraFrm_subject').value==""){
 	 alert("Please select the subject name");   
		 	 return false;
   } else {		 
     callsF9(500,325,'QuestionBankAction_f9actionCat.action'); 
   }
}
  
function callAnswerOpt(){
	var qsn=trim(document.getElementById('paraFrm_question').value);
	var sub=trim(document.getElementById('paraFrm_subject').value);
	var cate=trim(document.getElementById('paraFrm_category').value);
	document.getElementById('paraFrm_optionTextarea').value="";
	document.getElementById('paraFrm_flag').value="N";
	document.getElementById('paraFrm_paraId').value="";
			
       	if(sub=="") {
		alert("Please select the "+ document.getElementById('sub').innerHTML.toLowerCase());	
		return false;
	} else if(cate==""){
		alert("Please select the "+ document.getElementById('cat').innerHTML.toLowerCase());	
		return false;
	} else if(qsn==""){
		alert("Please enter the "+ document.getElementById('qsn').innerHTML.toLowerCase());
		document.getElementById('paraFrm_question').focus();	
		return false;	
	} else if(qsn != "" && qsn.length >1000){
		alert("Maximum length of "+ document.getElementById('qsn').innerHTML.toLowerCase()+" is 1000 characters.");
		return false;
	}
    var wind = window.open('','wind','toolbar=no,location=no,status=no,menubar=no,width=600,height=200,scrollbars=no,resizable=no,menubar=no,top=200,left=100');	 
	document.getElementById('paraFrm').target = "wind";
	document.getElementById('paraFrm').action = "QuestionBankAction_showAnsOpt.action";
    document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target = "main";
}

function uploadFile(fieldName) {
	var path = document.getElementById("paraFrm_dataPath").value;
	window.open('<%=request.getContextPath()%>/pages/recruitment/uploadResume.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
}

//View uploaded documents - BEGINS
function showRecord() {
	var fileName =document.getElementById('paraFrm_uploadFileName').value;
	if(fileName == "") {
		alert("Please upload file.");
		return false ; 
	}
	document.getElementById('paraFrm').target = "_blank";
	document.getElementById('paraFrm').action = 'QuestionBankAction_openUploadedFile.action?fileName='+fileName;
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target = "main";
	return true ; 
}


</script>



