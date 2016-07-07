<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript">
var qsnArray = new Array();
</script>
<s:form action="QuestionBankAction" validate="true" id="paraFrm"
	 theme="simple">
	<s:hidden name="show" />
	<s:hidden name="myPage" />
	<s:hidden name="dataPath" />
	<s:hidden name="categoryCode" />
	<s:hidden name="subjectCode" />
	<s:hidden name="cancelFlag" />
	<s:hidden name="editFlag" />
	<s:hidden name="hiddencode" />
	<s:hidden name="paraId" />
	<s:hidden name="optionId" />
	<s:hidden name="optionFlag" />
	<s:hidden name="noData" />
	<s:hidden name="quesCode" />
	<s:hidden name="fromFlag" />
	<s:hidden name="from" />
	<!-- <s:hidden name="ansOptions" />
	<s:hidden name="question" />
	<s:hidden name="compLevel" />
	<s:hidden name="category"></s:hidden>
	<s:hidden name="subject"></s:hidden> -->
	<table width="100%" border="0" align="right" class="formbg">


		<tr>
			<td colspan="3">
			<table width="100%" cellpadding="0" cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/common/css/default/images/review_shared.gif"
						width="25" height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Module</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td align="left" colspan="3"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
		<tr>
			<td width="100%" colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<s:if test="questionView">
					<tr>
						<td colspan="3" width="100%">
						<table border="0">
							<tr>
								<td height="22" class="formtext" width="15%"><label
									class="set" name="modulenamelabel" id="modulenamelabel7"
									ondblclick="callShowDiv(this);"><%=label.get("modulenamelabel")%></label>
								:</td>
								<td height="22" width="24%">
								 <s:property value="subject"/>
								<s:hidden name="subject"   /></td>

								<s:if test="fromFlag=='Category'">
									<td height="22" class="formtext" width="11%"><label
										class="set" name="cat" id="cat"
										ondblclick="callShowDiv(this);"><%=label.get("cat")%></label>
									:</label></td>
									<td width="25%">
									<s:property value="category"/>
									<s:hidden name="category"   /></td>
								</s:if>
								<s:else>
									<s:hidden name="category" />
								</s:else>
							</tr>

							<tr>
								<td height="22" class="formtext" width="8%"><label
									class="set" name="complvl" id="complvl"
									ondblclick="callShowDiv(this);"><%=label.get("complvl")%>
								</label>:</td>
								<td height="22" width="24%"><s:select theme="simple"
									name="compLevel" disabled="false" cssStyle="width:85"
									list="#{'E':'Easy','M':'Medium','H':'Hard'}" /></td>

								<td height="22" class="formtext" width="11%"><label
									class="set" name="sts" id="sts2"
									ondblclick="callShowDiv(this);"><%=label.get("sts")%> </label>:
								</td>
								<td width="25%" height="22" class="formtext"><s:select
									name="qsnStatus" disabled="false"
									list="#{'A':'Active','D':'Inactive'}" /></td>
							</tr>
							<!-- </table>
						</td>
					</tr>

					<tr>
						<td colspan="3" width="100%">
						<table border="0" width="100%"> -->
							<tr>
								<td height="22" class="formtext" width="16%" valign="top"><label
									class="set" name="qsn" id="qsn" ondblclick="callShowDiv(this);"><%=label.get("qsn")%></label>
								:<font color="red">*</font></td>
								<td height="22" width="84%" colspan="3"><s:textarea
									theme="simple" name="question" rows="4" cols="70"
									onkeypress="return allCharacters()"
									onkeyup="callLength('question','descCnt','1999');"
									readonly="false" /> <img src="../pages/images/zoomin.gif"
									height="12" align="bottom" width="12" theme="simple"
									onclick="javascript:callWindow('paraFrm_question','qsn','','paraFrm_descCnt','1999');">
								&nbsp;Remaining chars <s:textfield name="descCnt"
									readonly="true" size="4"></s:textfield></td>
							</tr>
							<!-- </table>
						</td>
					</tr>

					<tr>
						<td colspan="3" width="100%">
						<table border="0" width="100%">  -->
							<tr>
								<td height="22" class="formtext" width="11%"><label
									class="set" name="qsnType" id="qsnType"
									ondblclick="callShowDiv(this);"><%=label.get("qsnType")%></label>:</td>
								<td height="22" width="16%" colspan="2"><s:select
									theme="simple" name="ansOptions" onchange="showText();"
									disabled="false" list="#{'O':'Objective','S':'Subjective'}" />
								</td>
								<!--<td id="buttonValue" colspan="2" align="left"><input
											type="button" class="add" value="Add Answer Option"
											onclick="return callAnswerOpt();" /></td>-->
							</tr>

							<!-- </table>
						</td>
					</tr>

					<%-- <s:if test="viewUploadDocFlag"> --%>
						<tr >
							<td colspan="3">
							<table width="100%" border="0">  -->
							<tr id="uploadDocFlag" style="display: none;">
								<td class="formtext" nowrap="nowrap"><label class="set"
									name="uploadDocumentLabel" id="uploadDocumentLabel"
									ondblclick="callShowDiv(this);"><%=label.get("uploadDocumentLabel")%>
								</label> :<font color="red">*</font></td>

								<td width=""><s:textfield name="uploadFileName" size="45"
									readonly="true" cssStyle="background-color: #F2F2F2;" /></td>

								<td width=""><input type="button" value="Select File"
									class="token" onclick="uploadFile('uploadFileName');" /></td>
								<td width=""><input type="button" value="View File"
									class="token" onclick="showRecord();" /></td>
							</tr>
							<tr id="limitValue">
								<td height="22" colspan="1"><label class="set" name="lim"
									id="lim" ondblclick="callShowDiv(this);"><%=label.get("lim")%></label>
								:<font color="red">*</font></td>
								<td width="64%"><s:textfield theme="simple" name="limit"
									size="30" maxlength="3" onkeypress="return numbersOnly();" />
								</td>
							</tr>
							<tr id="allowToUploadDocFlag">
								<td width="" nowrap="nowrap"><label class="set"
									name="allowToUploadAnswerDocsLabel"
									id="allowToUploadAnswerDocsLabel"
									ondblclick="callShowDiv(this);"><%=label.get("allowToUploadAnswerDocsLabel")%></label>
								:</td>
								<td colspan="3" nowrap="nowrap"><s:checkbox
									name="allowToUploadAnswer" /></td>
							</tr>

							<tr id="optionTr" style="display: none;">
								<td width="" height="22" class="formtext" valign="top"
									nowrap="nowrap"><label class="set" name="ansopt"
									id="ansopt" ondblclick="callShowDiv(this);"><%=label.get("ansopt")%></label>:<font
									color="red">*</font></td>
								<td width="" height="22"><s:textfield theme="simple"
									name="optionTextarea" readonly="false" maxlength="2000" /></td>

								<td width="" height="22" class="formtext" colspan="2"><label
									class="set" name="ansflg" id="flag"
									ondblclick="callShowDiv(this);"><%=label.get("ansflg")%></label>
								: <s:hidden name="hiddenCode_option" id="hiddenCode_option" />
								<s:select theme="simple" name="flag" disabled="false"
									list="#{'N':'No','Y':'Yes'}" /> &nbsp;&nbsp;&nbsp;&nbsp; <input
									type="button" value="Add" class="token" onclick="addOption();" /></td>
							</tr>

						</table>
						</td>
					</tr>

					<%--</s:if>--%>
					<!-- UPLOAD DOC SECTION -- ENDS -->
				</s:if>


				<s:if test="optionFlag">
					<tr>
						<td height="22" class="formhead" colspan="3" width="100%"><strong
							class="forminnerhead">Option List :</strong></td>

					</tr>

					<tr>
						<td colspan="3" class="formtext"><s:hidden name="hiddenEdit" /><s:hidden
							name="hiddensub" />

						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="tblOption">
							<tr>
								<td class="formth" align="center" width="10%"><b><label
									class="set" name="serial.no" id="serial.no"
									ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b>
								</td>
								<td class="formth" align="center" width="30%"><b><label
									class="set" name="opt" id="opt" ondblclick="callShowDiv(this);"><%=label.get("opt")%></label></b>
								</td>
								<td class="formth" align="center" width="20%"><b><label
									class="set" name="ansflg" id="ansflg"
									ondblclick="callShowDiv(this);"><%=label.get("ansflg")%></label></b>
								</td>
								<!--<td class="formth" align="center" width="20%"><b><label
									class="set" name="ques.order" id="ques.order"
									ondblclick="callShowDiv(this);"><%=label.get("ques.order")%></label></b>
								</td>
								-->
								<td class="formth" align="center" width="20%"><b><label
									class="set" name="ques.editdelete" id="ques.editdelete"
									ondblclick="callShowDiv(this);"><%=label.get("ques.editdelete")%></label></b>
								</td>

								<%
								int j = 0;
								%>
								<%! int totalOption =0; %>
								<s:iterator value="optionList">
								
									<tr>
										<td width="10%" align="center" class="sortableTD"><%=++j%><s:hidden
											name="srNo" /> <input type="hidden" name="optionName"
											id="opt<%=j %>" value="<s:property value="optionName" />" /></td>
										<td width="30%" class="sortableTD"
											title="<s:property value="optionName" />"><s:property
											value="quesChoiceLen" /><input type="hidden"
											name="quesChoiceLen"
											value="<s:property value="quesChoiceLen" />" /></td>
										<td width="20%" align="center" class="sortableTD"><s:property
											value="flagItt" />
											
											<input type="hidden" name="flagItt" id="flagItt<%=j%>" value="<s:property value="flagItt"/>"/>
											
											 <s:hidden
											name="optionCode" /></td>
										<input type="hidden" name="hdeleteOp<%=j%>"
											id="hdeleteOp<%=j%>" />
										<td width="20%" align="center" class="sortableTD"
											nowrap="nowrap"><img
											onclick="editOption(document.getElementById('opt<%=j%>').value,'<%=j%>','<s:property value="flagItt" />','<s:property value="optionCode"/>');"
											title="Edit" src="../pages/mypage/images/icons/edit.png"
											style="cursor: pointer;"> | <img
											onclick="callfordel('<%=j%>','<s:property value="optionCode"/>');"
											style="cursor: pointer;"
											src="../pages/mypage/images/icons/delete.png" title="Delete">

										</td>
									</tr>
									<% if(j==1){ %>
									<script>
										document.getElementById("paraFrm_ansOptions").disabled="true";
									</script>
									<s:hidden name="ansOptions" value="O" />
									<% } %>
								</s:iterator>
								
								<%
								totalOption=j;
								
								%>
							</tr>
						</table>
						</td>
					</tr>
				</s:if>
			</table>
			</td>
		</tr>
		<tr>
			<td align="left" colspan="3"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>

		
		<s:if test="flagShow">
			
			<s:if test="noData">
				<tr>
					<td colspan="3">

					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<%
								int totalPage = (Integer) request.getAttribute("totalPage");
								int pageNo = (Integer) request.getAttribute("PageNo");
							%>
							<s:if test="flagShow">
								<td align="right" width="22%"><b>Page:</b> <input
									type="hidden" name="totalPage" id="totalPage"
									value="<%=totalPage%>"> <a href="#"
									onclick="callPage('1','F');"> <img title="First Page"
									src="../pages/common/img/first.gif" width="10" height="10"
									class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPage('P','P');"> <img title="Previous Page"
									src="../pages/common/img/previous.gif" width="10" height="10"
									class="iconImage" /> </a> <input type="text" name="pageNoField"
									id="pageNoField" theme="simple" size="3" value="<%= pageNo%>"
									onkeypress="callPageText(event);return numbersOnly()"
									maxlength="4" /> of <%=totalPage%> <a href="#"
									onclick="callPage('N','N')"> <img title="Next Page"
									src="../pages/common/img/next.gif" class="iconImage" /> </a>&nbsp; <a
									href="#" onclick="callPage('<%=totalPage%>','L');"> <img
									title="Last Page" src="../pages/common/img/last.gif" width="10"
									height="10" class="iconImage" /> </a></td>
							</s:if>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="3">
					<%
					try {
					%>
					<table width="100%" border="0" cellpadding="0" cellspacing="1"
						class="formbg">

						<tr>

							<td class="formtext" colspan="3">
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								id="tblQsn">
								<tr>
									<td colspan="7" width="100%">
									<table width="100%">
										<tr>
											<td width="85%"><strong class="innerhead">Question
											List</strong></td>
											<td width="15%" align="right"><input type="button"
												class="delete" theme="simple" value="Delete"
												onclick=" return chkDelete()" /></td>
										</tr>
									</table>
									</td>
								</tr>
								<tr>
									<td class="formth" align="center" width="5%"><b><label
										class="set" name="serial.no" id="serial.no1"
										ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b></td>
									<!--<td class="formth" align="center" width="15%"><b><label
										class="set" name="sub" id="sub1"
										ondblclick="callShowDiv(this);"><%=label.get("sub")%></label></b></td>
									<td class="formth" align="center" width="15%"><b><label
										class="set" name="cat" id="cat"
										ondblclick="callShowDiv(this);"><%=label.get("cat")%></label></b></label></td>-->
									<td class="formth" align="center" width="30%"><b><label
										class="set" name="qsn" id="qsn1"
										ondblclick="callShowDiv(this);"><%=label.get("qsn")%></label></b></td>
									<td class="formth" align="center" width=""><b><label
										class="set" name="qsnType" id="qsnType"
										ondblclick="callShowDiv(this);"><%=label.get("qsnType")%></label></b></td>
									<td class="formth" align="center" width=""><b><label
										class="set" name="sts" id="sts1"
										ondblclick="callShowDiv(this);"><%=label.get("sts")%></label></b></td>


									<td align="center" class="formth" width="10%"><input
										type="checkbox" name="chkAll" id="paraFrm_chkAll"
										onclick="return callQsnAll();" /></td>
								</tr>

								<%
										int count = 0;
										int counter = 0;
								%>
								<%!int d = 0;%>
								<%
										int cnt = pageNo * 20 - 20;
										int i = 0;
								%>
								<s:iterator value="questionList">

									<tr <%if(count%2==0){
									%> class="tableCell1"
										<%}else{%> class="tableCell2" <%	}count++; %>
										onmouseover="javascript:newRowColor(this);"
										onmouseout="javascript:oldRowColor(this,<%=count%2 %>);">
										<td width="5%" align="center" class="sortableTD"><%=++cnt%>
										<%
										++i;
										%><s:hidden value="srNo" /><input type="hidden"
											name="hdeleteCode" id="hdeleteCode<%=i%>" /></td>


										<!--<td width="15%" class="sortableTD"
											title="Double click for edit"
											ondblclick="javascript:callForEdit('<s:property value="quesCodeItt" />');">&nbsp;<s:property
											value="subject" /></td>


										<td width="15%" class="sortableTD"
											title="Double click for edit"
											ondblclick="javascript:callForEdit('<s:property value="quesCodeItt" />');">&nbsp;
										<s:property value="category" /><s:hidden name="categoryCode" /></td>

										-->

										<td width="30%" class="sortableTD"><s:hidden
											name="quesCodeItt" /> <a href="#"
											onclick="javascript:callForEdit('<s:property value="quesCodeItt" />');"
											title="<s:property value="question" />"> <u><s:property
											value="questionAbbr" /> </u></a></td>

										<td width="" class="sortableTD" align="center">&nbsp; <s:property
											value="ansOptions" /></td>

										<td width="" align="center" class="sortableTD">&nbsp;<s:property
											value="qsnStatus" /></td>
										<td width="" align="center" class="sortableTD"><input
											type="checkbox" class="checkbox" id="confChk<%=i%>"
											name="confChk"
											onclick="callForDelete1('<s:property value="quesCodeItt" />','<%=i%>')" /></td>

										<script>
								
								qsnArray[<%=counter%>]='<s:property value="quesCodeItt" />';
								
								</script>
									</tr>
									<%
									counter++;
									%>
								</s:iterator>
								<%
								d = i;
								%>
							</table>

							<%
								} catch (Exception e) {
								}
							%>
							</td>
						</tr>
					</table>

					</td>
				</tr>
			</s:if>
			<s:else>
				<!--
				<tr>
					<td align="center" width="100%">
					<table width="100%" align="center" class="formbg">
						<tr>
							<td class="formth" align="center" width="5%"><b><label
								class="set" name="serial.no" id="serial.no1"
								ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b></td>
							<td class="formth" align="center" width="15%"><b><label
								class="set" name="sub" id="sub1" ondblclick="callShowDiv(this);"><%=label.get("sub")%></label></b></td>
							<td class="formth" align="center" width="15%"><b><label
								class="set" name="cat" id="cat" ondblclick="callShowDiv(this);"><%=label.get("cat")%></label></b></label></td>
							
							<td class="formth" align="center" width="30%"><b><label
								class="set" name="qsn" id="qsn1" ondblclick="callShowDiv(this);"><%=label.get("qsn")%></label></b></td>
							<td class="formth" align="center" width="10%"><b><label
								class="set" name="qsnType" id="qsnType"
								ondblclick="callShowDiv(this);"><%=label.get("qsnType")%></label></b></td>
							<td class="formth" align="center" width="10%"><b><label
								class="set" name="sts" id="sts1" ondblclick="callShowDiv(this);"><%=label.get("sts")%></label></b></td>
							<td class="formth" align="center" width="10%"><b><label
								class="set" name="ques.delete" id="ques.delete"
								ondblclick="callShowDiv(this);"><%=label.get("ques.delete")%></label></b></td>

						</tr>
						<tr>
							<td width="100%" align="center" colspan="5"><font
								color="red">There is no data to display.</font></td>
						</tr>
					</table>
					</td>
				</tr>
			-->
			</s:else>
		</s:if>
		<tr>
			<td align="right" nowrap="nowrap"><s:if test="flagShow">
				<s:if test="noData">
					<strong class="forminnerhead">Total no. of Records :</strong>
					<s:property value="totRec" />
				</s:if>
			</s:if></td>
		</tr>
	</table>
</s:form>

<script>

function addValidation(){
	if(document.getElementById('option').value==""){
		alert('Please enter the option value.');
		return false;
	}
}

 function callForOption(id)   {	
	   if(document.getElementById('confChkop'+id).checked==true)
	   {	  
	    document.getElementById('hdeleteOp'+id).value="Y";
	    alert("sssss"+document.getElementById('hdeleteOp'+id).value);
	   }
	   else{
	   document.getElementById('hdeleteOp'+id).value="";
	     alert("sssss"+document.getElementById('hdeleteOp'+id).value);
	   	
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

function callAnswer(){
	if(document.getElementById('paraFrm_flag').checked) {
		document.getElementById('paraFrm_hflag').value='Yes';
	} else {
		document.getElementById('paraFrm_hflag').value='';
	}
}	
/*			
function saveFun(){
		var option=document.getElementById('ansOptions').value;
		var fieldName = ["paraFrm_question","paraFrm_subject","paraFrm_category","paraFrm_qsnWtg"];
		var lableName = ["question","subject","category","question weightage"];
		var flag = ["enter","select","select","enter"];
		
    	if(!checkMandatory(fieldName,lableName,flag)){
             return false;
     	 }else{

		document.getElementById('paraFrm').action="QuestionBankAction_save.action";
  		document.getElementById('paraFrm').submit();
		}
}			
*/
function callPage(id,pageImg){
	 pageNo =document.getElementById('pageNoField').value;	
 	 totalPage =document.getElementById('totalPage').value;	 
 	 
 	   if(pageNo==""){
	        alert("Please Enter Page Number.");
	        document.getElementById('pageNoField').focus();
			 return false;
	        }
	 if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero.");
		     document.getElementById('pageNoField').focus();
			 return false;
		    }
	  if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField').focus();
			 return false;
		    }
		    	
       if(pageImg=="F")
         {
	        if(pageNo=="1"){
	         alert("This is first page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       }  
       
       	if(pageImg=="L")
         {
	        if(eval(pageNo)==eval(totalPage)){
	         alert("This is last page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       } 
       
        if(pageImg=="P")
       {
	        if(pageNo=="1"){
	         alert("There is no previous page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        }  
       }  
       if(pageImg=="N")
       {
	        if(Number(pageNo)==Number(totalPage)){
	         alert("There is no next page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        }  
       }

		if(id=='P'){
		var p=document.getElementById('paraFrm_myPage').value;
		id=eval(p)-1;
		}
		if(id=='N'){
		var p=document.getElementById('paraFrm_myPage').value;
		id=eval(p)+1;
		}
		document.getElementById('paraFrm_myPage').value=id;
		document.getElementById('paraFrm').action='QuestionBankAction_callPageView.action';
		document.getElementById('paraFrm').submit();
		
	}	
	function newRowColor(cell) {
		cell.className='Cell_bg_first';
	}
	
	function oldRowColor(cell,val) {
		cell.className='Cell_bg_second';
	}
	
	function chkDelete(){
		var flag='<%=d %>';
		if(chk()){
			var con=confirm('Do you really want to delete the records?');
			if(con){
				document.getElementById('paraFrm').action="QuestionBankAction_delete1.action";
				document.getElementById('paraFrm').submit();
			} else {
				var flag='<%=d %>';
				for(var a=1;a<=flag;a++){	
					document.getElementById('confChk'+a).checked=false;
					document.getElementById('hdeleteCode'+a).value="";
					document.getElementById("paraFrm_chkAll").checked=false;
				}
				return false;
			}
		} else {
			alert('Please select atleast one record to delete');
			return false;
		}
	}

function chk(){
	 var flag='<%=d %>';
	  for(var a=1;a<=flag;a++){	
	   if(document.getElementById('confChk'+a).checked == true)
	   {	
	 	    return true;
	   }	   
	  }
	  return false;
}	
	
function callForEdit(id){
 
	  	document.getElementById('paraFrm_hiddencode').value=id;
	  	document.getElementById("paraFrm").action="QuestionBankAction_calforedit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
   }	  
   function callForDelete1(id,i)
	   {
	   //alert(id);
	   //alert(i);
	   var flag='<%=d %>';
	   if(document.getElementById('confChk'+i).checked == true)
	   {	  
	    document.getElementById('hdeleteCode'+i).value=id;
	   }
	   else
	   document.getElementById('hdeleteCode'+i).value="";
	}	
function callQsnAll() { 	  
	var rowLen ='<%=d %>';
	if (document.getElementById("paraFrm_chkAll").checked){
		for(var idx=1;idx<=rowLen;idx++){
			document.getElementById("confChk"+idx).checked ="true";
			document.getElementById('hdeleteCode'+idx).value=qsnArray[idx-1];
		}
	} else {
		for (var idx = 1; idx <= rowLen; idx++) {
			document.getElementById("confChk"+idx).checked = false;
			document.getElementById("paraFrm_chkAll").checked=false;
			document.getElementById('hdeleteCode'+idx).value="";
		}
	}
}    	
	
function editOption(id,id1,id2,id3) {
	//document.getElementById("paraFrm_option").value=id;
	document.getElementById("paraFrm_optionTextarea").value=id;
	document.getElementById("paraFrm_paraId").value=id1;
	document.getElementById("paraFrm_optionId").value=id3;
	if(id2=="Yes"){
		//document.getElementById("paraFrm_flag").checked=true;		
		/*document.getElementById('paraFrm_flag').options[0].text="Yes";
		document.getElementById('paraFrm_flag').options[0].value="Y";
		document.getElementById('paraFrm_flag').options[0].selected="selected";
		document.getElementById('paraFrm_flag').options[1].text="No";
		document.getElementById('paraFrm_flag').options[1].value="N";*/		
		//document.getElementById("paraFrm_hflag").value="Right";
		document.getElementById("paraFrm_flag").value = "Y";
	}
	else if(id2=="No"){
		/*document.getElementById('paraFrm_flag').options[0].text="No";
		document.getElementById('paraFrm_flag').options[0].value="N";
		document.getElementById('paraFrm_flag').options[1].selected="selected";
		document.getElementById('paraFrm_flag').options[1].text="Yes";
		document.getElementById('paraFrm_flag').options[1].value="Y";*/	
		document.getElementById("paraFrm_flag").value = "N";
	}
	
}
	
function callfordel(id,id1) {
	var conf=confirm("Do you really want to delete this record ?");
	if(conf) {
		document.getElementById("paraFrm_paraId").value=id;
		document.getElementById("paraFrm_optionId").value=id1;
		document.getElementById("paraFrm").action="QuestionBankAction_deleteForOption.action";
	    document.getElementById("paraFrm").submit();
	}
}

function editFun(){
		document.getElementById('paraFrm_myPage').value="";
		//document.getElementById('selectname').value="";
		//document.getElementById('paraFrm_show').value="";
		document.getElementById('paraFrm').action="QuestionBankAction_edit.action";
  		document.getElementById('paraFrm').submit();
}

function deleteFun(){
		document.getElementById('paraFrm_myPage').value="";
		//document.getElementById('selectname').value="";
		//document.getElementById('paraFrm_show').value="";
		var conf=confirm("Do you really want to delete this record ?");
		if(conf){

		document.getElementById('paraFrm').action="QuestionBankAction_delete.action";
  		document.getElementById('paraFrm').submit();
		}
}

function searchFun(){
	if(document.getElementById('paraFrm_noData').value=="true"){
		document.getElementById('paraFrm_myPage').value="";
		//document.getElementById('selectname').value="";
		//document.getElementById('paraFrm_show').value="";
	}
	callsF9(500,325,'QuestionBankAction_f9Search.action');
}

function addnewFun(){
	if(document.getElementById('paraFrm_noData').value=="true"){
		document.getElementById('paraFrm_myPage').value="";
		//document.getElementById('selectname').value="";
		//document.getElementById('paraFrm_show').value="";
	}	
	document.getElementById('paraFrm').action="QuestionBankAction_addNew.action";
  	document.getElementById('paraFrm').submit();
}
//For Report Button
function reportFun(){
	document.getElementById('paraFrm').action="QuestionBankAction_report.action";
    document.getElementById("paraFrm").submit();
}

function callPageText(id){  
	var key //= (window.event) ? event.keyCode : e.which;
	if (window.event)
	    key = event.keyCode
	else
	   	key = id.which
	clear();

	if(key==13) { 
		pageNo =document.getElementById('pageNoField').value;	 
		totalPage =document.getElementById('totalPage').value;	
		var actPage = document.getElementById('paraFrm_myPage').value;   
		if(pageNo==""){
        alert("Please Enter Page Number.");
        document.getElementById('pageNoField').focus();
	 return false;
       }
   if(Number(pageNo)<=0) {
     alert("Page number should be greater than zero."); 
      document.getElementById('pageNoField').focus();
     document.getElementById('pageNoField').value=actPage;  
	 return false;
    }
    if(Number(totalPage)<Number(pageNo))
    {
     alert("Page number should not be greater than "+totalPage+".");
     document.getElementById('pageNoField').focus();
     document.getElementById('pageNoField').value=actPage;  
	 return false;
    }
    
    if(pageNo==actPage){  
      document.getElementById('pageNoField').focus();
      return false;
       }
	        
	        document.getElementById('paraFrm_myPage').value=pageNo;
		    document.getElementById('paraFrm').action='QuestionBankAction_callPageView.action';
			document.getElementById('paraFrm').submit();
		}
		
	}	

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



function chkIsCorrectYes()
		{
		 
			 try{
			 var flag='<%=totalOption %>';
			 var count =0 ;
			 
			  for(var a=1; a<=flag  ;a++)
			  {	
			  	 if(document.getElementById('flagItt'+a).value == 'Yes')
			 	   {	
			  		count++;
			  	   }
			  }
			 if(count==0)
			 {
			 	 alert('Atleast one choice should be marked Is correct as Yes.  ');
			   return false;
			 }
			   return true;
			  }catch(e){ } 
		}	

function saveFun(){
			
	var qsn=document.getElementById('paraFrm_question').value;
	var sub=document.getElementById('paraFrm_subject').value;
	var cate=document.getElementById('paraFrm_category').value;
	var flag=document.getElementById('paraFrm_optionFlag').value;
	var lim=document.getElementById('paraFrm_limit').value;
	var option=document.getElementById('paraFrm_ansOptions').value;
	var fileName =document.getElementById('paraFrm_uploadFileName').value;	
	var optionNm = document.getElementsByName('optionName');
	/*if(sub==""){
		alert("Please select the "+ document.getElementById('sub').innerHTML.toLowerCase());	
		return false;
	
	}else if(cate==""){
		alert("Please select the "+ document.getElementById('cat').innerHTML.toLowerCase());	
		return false;
	
	}else*/ 
	if(qsn==""){
		alert("Please enter the "+ document.getElementById('qsn').innerHTML.toLowerCase());
		document.getElementById('paraFrm_question').focus();	
		return false;	
	}
	else if(optionNm.length == 0 &&  option=="O"){
		alert("Plaese Enter Option First");
		return false;	
	}
	else if(qsn != "" && qsn.length >= 1999){
	    alert("Maximum length of "+ document.getElementById('qsn').innerHTML.toLowerCase()+" is 2000 characters.");
	    return false;
	}else if(option=="O"){
		if(flag=="false"){
			alert("Please add the answer options");
			return false;
		}			
	}else if(option=="S"){
		if(fileName == "") {
			alert("Please upload file.");
			return false ; 
		}
		if(lim==""){
			alert("Please enter the "+document.getElementById('lim').innerHTML.toLowerCase());
			return false;
		}		
		
	}
	
	
	var option=document.getElementById('paraFrm_ansOptions').value;
	
	if(option=='O')
	{
			 var flag='<%=totalOption %>';
	 if(flag==1)
	 {
	 	alert("Please add one more option.");
	 		return false;
	 }
		var check =chkIsCorrectYes();
		if(check){
		document.getElementById('paraFrm').action="QuestionBankAction_save.action";
	document.getElementById('paraFrm').submit();	
	return true;
		}
		else{
		return false;
		}
	
	}
	else{
	
		document.getElementById('paraFrm').action="QuestionBankAction_save.action";
	document.getElementById('paraFrm').submit();	
	return true;
	
	}

	
}			


function backFun(){
	try{
		var subcode = document.getElementById('paraFrm_subjectCode').value;    	
		var from = document.getElementById('paraFrm_fromFlag').value;
		if(from == "SubjectList"){
			document.getElementById('paraFrm').action="ExamMaster_input.action";
		} else {
			document.getElementById('paraFrm').action="ExamMaster_calforedit.action?paraFrm_hiddencode="+subcode;
		}
		
        document.getElementById("paraFrm").submit();
        return false;
    }catch(e){
    }
}

function uploadFile(fieldName) {
	var path = document.getElementById("paraFrm_dataPath").value;
	window.open('<%=request.getContextPath()%>/pages/recruitment/uploadResume.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
}


function showText(){
	var chq = document.getElementById('paraFrm_ansOptions').value;
	
	if(chq=="O" ){
		//document.getElementById('buttonValue').style.display='';
		document.getElementById('limitValue').style.display='none';
		document.getElementById('uploadDocFlag').style.display = 'none';
		document.getElementById('allowToUploadDocFlag').style.display = 'none';
		
		document.getElementById('paraFrm_limit').value = '';
		
		
		document.getElementById('optionTr').style.display='';
	}else if(chq=="S" || chq==""){
		document.getElementById('uploadDocFlag').style.display = '';
		document.getElementById('limitValue').style.display='';
		document.getElementById('allowToUploadDocFlag').style.display='';
		//document.getElementById('buttonValue').style.display='none';
		
		document.getElementById('optionTr').style.display='none';
   }
}

showText();

function addOption(){
	var val=trim(document.getElementById('paraFrm_optionTextarea').value);
	var finalVal;
	if (val.search(/\r/g) != -1) {
		//alert('enter found');
		finalVal = val.replace(/\r/g, " ");
		// alert(finalVal);
	}else{
		finalVal=val;
	}
	//document.getElementById('paraFrm_concatEx').value+=document.getElementById('paraFrm_option').value +',';
	//document.getElementById('paraFrm_concatFlg').value+=document.getElementById('paraFrm_flag').value +',';
	//alert(document.getElementById('paraFrm_concatEx').value);
	//alert("222"+document.getElementById('paraFrm_concatFlg').value);

	if(val==""){
		alert('Please enter the '+document.getElementById('ansopt').innerHTML.toLowerCase());
		return false;
	}else if(val.length > 999){
		alert('Maximum length of '+document.getElementById('ansopt').innerHTML.toLowerCase()+" is 1000 characters");
		return false;
	}else {
		document.getElementById('paraFrm').target='main';
		document.getElementById('paraFrm').action='QuestionBankAction_addOption.action'; 
		document.getElementById('paraFrm').submit();		
	}
}
	
		
</script>
