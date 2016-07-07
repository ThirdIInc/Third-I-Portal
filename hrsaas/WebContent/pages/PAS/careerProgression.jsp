
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<link href="css/hrms.css" rel="stylesheet" type="text/css" />

<script language="JavaScript" type="text/javascript"
	src="..pages/common/include/javascript/sorttable.js"></script>

<%
	Object visibility[]  = (Object[])request.getAttribute("visibility");
	Object comments[]  =(Object[])request.getAttribute("comment");
%>


<s:form theme="simple" name="paraFrm" id="paraFrm" method="post"
	action="CareerProgression_input">
 <%
String calupdateflag=(String)request.getAttribute("calupdateFlag");
%>
<s:hidden name="calupdateFlag" value="<%=calupdateflag %>"/>
	<s:hidden name="mode" theme="simple" />
	<s:hidden name="removeQuestions" />
	<s:hidden name="startDate" />
	<s:hidden name="endDate" />

	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head"><label class="set" name="career.progression" id="career.progression"
								ondblclick="callShowDiv(this);"><%=label.get("career.progression")%></label></strong></td>
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

			<table width="100%" border="0" cellpadding="2" cellspacing="2">
				<tr>
					<td width="84%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

					<td width="16%"><font color="red">*</font> Indicates Required
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td>
					<table width="101%" border="0" align="center" cellpadding="2"
						cellspacing="2" height="39">


						<tr>
							<td width="267"><s:checkbox name="chkCareerAppl"
								id="chkCareerAppl" theme="simple" /> <label
								name="career.prog.applicable" class="set"
								id="career.prog.applicable" ondblclick="callShowDiv(this);"><%=label.get("career.prog.applicable")%></label>
							</td>

						</tr>

					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="2" height="58">
						<tr>
							<td height="13" width="15%"><label name="appraisal.code"
								class="set" id="appraisal.code" ondblclick="callShowDiv(this);"><%=label.get("appraisal.code")%></label>
							</td>
							<td height="13"><s:property value="apprCode" /> <input
								type="hidden" name="apprCode"
								value="<s:property  value="apprCode"  />"> <s:hidden
								name="apprId" /></td>
							<td nowrap="nowrap"><label name="career.from" class="set"
								id="career.from" ondblclick="callShowDiv(this);"><%=label.get("career.from")%></label>
							: <s:property value="startDate" /> &nbsp;&nbsp; <label
								name="career.to" class="set" id="career.to"
								ondblclick="callShowDiv(this);"><%=label.get("career.to")%></label>
							: <s:property value="endDate" /></td>
						</tr>
						<tr>
							<td width="170"><label name="template.name" class="set"
								id="template.name" ondblclick="callShowDiv(this);"><%=label.get("template.name")%></label>
							:</td>
							<td><s:property value="templateName" /> <input type="hidden"
								name="templateName"
								value="<s:property  value="templateName"  />"> <s:hidden
								name="templateCode" /></td>
							<td width="453">&nbsp;</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>



		<td colspan="3">
		<table width="100%" border="0" cellpadding="2" cellspacing="2"
			class="formbg">
			<tr>
				<td><b><label name="career.prog.applicability" class="set"
					id="career.prog.applicability" ondblclick="callShowDiv(this);"><%=label.get("career.prog.applicability")%></label></b>
				</td>
			</tr>
			<tr>
				<td>
				<table width="100%" border="0" align="center" cellpadding="2"
					cellspacing="2">
					<tr>
						<td width="10%" height="32"><label name="applicable.phase"
							class="set" id="applicable.phase" ondblclick="callShowDiv(this);"><%=label.get("applicable.phase")%></label>
						:</td>
						<script>
							  function setCode(chkId,id){
					 	          if(document.getElementById(chkId).checked){
					 	           document.getElementById(id).value='Y';
					 	          }else{
					 	           document.getElementById(id).value='N';
					 	          }
				 	         }
							</script>
						<%int i=0; %>


						<s:iterator value="trngList">
							<td width="2%"><input type="checkbox" name="phase"
								id="chk<%=i %>" value="wd"
								onclick="setCode('chk<%=i %>','applicability<%=i %>')"
								<%if(visibility!=null){%> <%= visibility[i]%> <%} %>> <s:property
								value="phase" /> <input type="hidden" name="hPhaseId"
								id="hPhaseId" value="<s:property value="phaseId"  />"> <input
								type="hidden" name="applicability" id="applicability<%=i %>"
								value="<%if(visibility!=null && visibility[i].equals("checked")){%> <%="Y"%> <%}else{ %> <%="N"%>  <%} %>">
							<input type="hidden" name="hSectionId"
								value="<s:property value="hSectionId" />"> <input
								type="hidden" name="hPhase" value="<s:property value="phase"/>" />
							</td>
							<%i++; %>
						</s:iterator>
					</tr>

					<tr>
						<td width="10%" height="32"><label name="allow.comments"
							class="set" id="allow.comments" ondblclick="callShowDiv(this);"><%=label.get("allow.comments")%></label>
						:</td>
						<% int j=0; %>
						<s:iterator value="trngList">
							<td width="7%"><input type="checkbox" name="phase"
								id="chkc<%=j %>" value="wd"
								onclick="setCode('chkc<%=j %>','comments<%=j %>')"
								<%if(comments!=null){%> <%=comments[j]%> <%} %> /> <s:property
								value="phase" /> <input type="hidden" name="comments"
								id="comments<%=j %>"
								value="<%if(comments!=null && comments[j].equals("checked")){%> <%="Y"%> <%}else{ %> <%="N"%>  <%} %>">
							</td>
							<%j++; %>
						</s:iterator>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</td>
		</tr>


		<tr>
			<td colspan="3" height="82">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg" height="157">
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="2" height="138">
						<tr>
							<td width="15%"><label name="add.quest" class="set"
								id="add.quest" ondblclick="return callShowDiv(this);"><%=label.get("add.quest")%></label>
							: #</td>
							<td width="60%"><s:textarea name="question" theme="simple"
								rows="2" cols="100" readonly="true" /> <s:hidden name="rownum"
								theme="simple" /> <s:hidden name="questionId" theme="simple" />

							<script>
						function callAddQuestion(){
								 
								//validations for question selection
								rows = document.getElementById('rows').value;
								questCode = document.getElementById('paraFrm_questionId').value;
								//alert(rows+' '+questCode); 
								for(i=1;i<=rows;i++){
									//alert(document.getElementById('hQuestionId'+i).value);
								 	if(document.getElementById('hQuestionId'+i).value==questCode){
								 		
								 	 	alert('Question already exists in the list,\nplease add a different question.');
								 	 	return false;
								 	 	
								 	}
								 	
								}
										   				 	
			   				 	if(!document.getElementById('paraFrm_question').value==""){
				   					document.getElementById('paraFrm').action="CareerProgression_addQuestion.action";
				   					document.getElementById('paraFrm').submit();
				   					return true;
			   					}else{
			   					 alert('Please select a question to add to the list.');
			   					 return false;
			   					}
			   				}
			   				/*function callAddQuestion(){
								
								//validations for question selection			   				 	
			   				 	if(!document.getElementById('paraFrm_question').value==""){
				   					document.getElementById('paraFrm').action="CareerProgression_addQuestion.action";
				   					document.getElementById('paraFrm').submit();
				   					return true;
			   					}else{
				   					 alert('Please select a question to add to the list.');
				   					 return false;
			   					}
			   				}*/
			   			</script></td>
							<td width="15%"><img border="0"
								src="../pages/images/recruitment/search2.gif" width="16"
								height="15"
								onclick="javascript:callsF9(500,325,'CareerProgression_f9quest.action');">
							<input type="button" onclick="callAddQuestion();" class="add"
								value="   Add "></td>
						</tr>



						<tr>
							<td colspan="2" width="90%"><strong> <label
								name="quest.list" class="set" id="quest.list"
								ondblclick="callShowDiv(this);"><%=label.get("quest.list")%></label>

							</strong></td>
						</tr>

						<tr>
							<td colspan="3">
							<table width="100%" border="0" cellpadding="1" cellspacing="1"
								class="sortable">
								<tr>
									<td width="5%" valign="top" class="formth"><label
										name="career.sno" class="set" id="career.sno"
										ondblclick="callShowDiv(this);"><%=label.get("career.sno")%></label>
									</td>
									<td width="85%" valign="top" class="formth" nowrap="nowrap">
									<label name="career.ques" class="set" id="career.ques"
										ondblclick="callShowDiv(this);"><%=label.get("career.ques")%></label>
									</td>
									<td width="10%" valign="top" class="formth" nowrap="nowrap">
									<s:if test="removeFlag">
										<input type="button" class="delete" value="  Remove"
											onclick="return callRemove();">
									</s:if></td>
								</tr>
								<% int count=0; %>
								<s:iterator value="questionList">
									<tr <%if(count%2==0){
									%> class="tableCell1"
										<%}else{ %> class="tableCell2" <%	} %>>
										<td class="sortableTD"><%=++count %></td>
										<td class="sortableTD"><input type="hidden"
											value="<%=count%>" name="sNo"> <input type="hidden"
											name="hQuestionId" id="hQuestionId<%=count %>"
											value="<s:property value="hQuestionId" />"> <input
											type="hidden" name="hQuestion"
											value="<s:property value="hQuestion" />"> <input
											type="hidden" name="hQuestSectionId"
											value="<s:property value="hSectionId" />"> <input
											type="hidden" name="removeQuestion"
											id="removeQuestion<%=count%>" value="N"> <s:property
											value="hQuestion" /></td>
										<td class="sortableTD" align="center"><input
											type="checkbox" name="chkRemove" id="chkRemove<%=count%>"
											onclick="callSetRemoveFlag(this,'removeQuestion<%=count%>')">
										<input type="hidden" name=""></td>
									</tr>
								</s:iterator>
								<input type="hidden" name="rows" id="rows" value="<%=count%>">
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td colspan="3"># : <label name="note" class="set" id="note"
						ondblclick="callShowDiv(this);"><%=label.get("note")%></label></td>
				</tr>

			</table>
			</td>
		</tr>

		<!-- General Comments Starts -->
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">
				<tr>
					<td colspan="4"><label  class = "set" name="allow.general.comments"  id="allow.general.comments" ondblclick="callShowDiv(this);"><%=label.get("allow.general.comments")%></label> <input type="checkbox"
						name="genComFlgChk" id="genComFlgChk"
						onclick="callGenComFlgChk();" /> <s:hidden name="genComFlg" /></td>
				</tr>
				<tr id="allComments">
					<td colspan="4">No. of Columns : <s:textfield name="noOfCols"
						onkeypress="numbersOnly()" /> <input type="button" value="Go"
						onclick="callGo()" /></td>
				</tr>

				<s:hidden  id="checkGo" name="checkGo" />
				<tr id="textboxes">
					<td colspan="4" width="100%">
					<%int n = 0;
						String[] comData = null;
							try{
								n=Integer.parseInt(request.getAttribute("cols").toString());
								System.out.println("jsp--cols"+n);
								comData = (String[]) request.getAttribute("comData");
								System.out.println("comData is not null");
								
								//System.out.println("in try n =="+n);
							}
							catch(Exception e){
								//System.out.println("catch----------"+e);
							}
							//System.out.println("---- n ===="+n);
							 %>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<% int c=0; %>
						<%for( int p=0;p<(n/4+1);p++){ %>
						<tr>
							<% for( int q=0;q<4;q++){ %>
							<%if(c<n){ %>
							<td width="25%">
							<%if(comData!=null){ %> <input type="text" value="<%=comData[c] %>"
								name="tCom_<%=p%>_<%=q%>" id="tCom_<%=p%>_<%=q%>" /> <%}else { %>
							<input type="text" value=" " name="tCom_<%=p%>_<%=q%>"
								id="tCom_<%=p%>_<%=q%>" /> <%} %>
							</td>
							<%c++; } %>
							<%} %><!-- End of for for <td> -->
						</tr>
						<%} %><!-- End of for loop for <tr> -->
					</table>

					</td>
				</tr>



			</table>
			</td>
		</tr>
		<script type="text/javascript">
        	setFlgChk();
        	function setFlgChk()
        	{
        		if(document.getElementById('paraFrm_genComFlg').value=="Y")
        		{
        			document.getElementById('genComFlgChk').checked=true;
        			document.getElementById('allComments').style.display='';
        			document.getElementById('textboxes').style.display='';
        			document.getElementById('checkGo').value = "1";
        		}
        		else
        		{
        			document.getElementById('checkGo').value = "0";
        			document.getElementById('genComFlgChk').checked=false;
        			document.getElementById('allComments').style.display='none';
        			document.getElementById('textboxes').style.display='none';
        		}        		
        	}
        	function callGenComFlgChk()
        	{
        		if(document.getElementById('genComFlgChk').checked)
        		{
        			document.getElementById('allComments').style.display='';
        			document.getElementById('paraFrm_genComFlg').value="Y";
        		}
        		else
        		{
        			document.getElementById('paraFrm_genComFlg').value="N";
        			document.getElementById('paraFrm_noOfCols').value="0"
        			document.getElementById('allComments').style.display='none';
        			document.getElementById('textboxes').style.display='none';
        		}
        	}
        	function callGo()
        	{
        		var col = document.getElementById('paraFrm_noOfCols').value;
        		if(eval(col)>0) {
        		document.getElementById('paraFrm_genComFlg').value="Y";
        		document.getElementById('allComments').style.display='';
        		document.getElementById('checkGo').value = "1";
        		document.getElementById('textboxes').style.display='';
        		document.getElementById('paraFrm').action = "CareerProgression_addCols.action";
        		document.getElementById('paraFrm').submit();
        		}
        		else{
        			alert("No. of Columns should be greater than zero");
        		}
        	}
        </script>
		<!-- General Comments Ends -->

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2">
				<tr>
					<td width="75%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				</tr>
			</table>
			</td>
		</tr>


	</table>

</s:form>



<script>

function validation()
{
	if(document.getElementById('genComFlgChk').checked)
	{
		if(document.getElementById('paraFrm_noOfCols').value == "0") {
			alert("No. of columns should be greater than zero");
			return false;
		}
		if(document.getElementById('checkGo').value == "0") {
			alert("Please click on Go button");
			return false;
		}
		return true;
	}
	else
	{
		return true;
	}

}
function saveFun()
{
  if(validation()) {
  document.getElementById('paraFrm').action='CareerProgression_next.action';
  document.getElementById('paraFrm').submit();
  }
}

function saveandnextFun()
{
  if(validation()){
  document.getElementById('paraFrm').action='CareerProgression_saveAndNext.action';
  document.getElementById('paraFrm').submit();
  }
}

function saveandpreviousFun()
{
  if(validation()) {
  document.getElementById('paraFrm').action='CareerProgression_saveAndPrevious.action';
  document.getElementById('paraFrm').submit();
  }
}		
	
function previousFun(){
 	document.getElementById('paraFrm').action='DisciplinaryMeasures_input.action';	
	document.getElementById('paraFrm').submit();
}


function nextFun()
 {
    //alert(document.getElementById('chkTrngAppl').checked);	
    document.getElementById('paraFrm').action='WeightageDistribution_input.action';	
	document.getElementById('paraFrm').submit();
			  	 
}
   function cancelFun()
 {
    //alert(document.getElementById('chkTrngAppl').checked);	 
    document.getElementById('paraFrm').action='TemplateDefination_input.action';	
	document.getElementById('paraFrm').submit();
			  	
} 


function callRemove(){
 
 
  count = document.getElementById('rows').value;
  temp = 0;	 
		for(k=1;k<=count;k++){
	 
			if( !document.getElementById('chkRemove'+k).checked ){			
				//document.getElementById('paraFrm').action='TrainingDetails_remove.action';
				//document.getElementById('paraFrm').submit();
			  	//return true;
			  	temp++;
			 }
		}
		if(temp==count){
			 alert('Please select a question to delete.');
			 return false;
		}
   
   
   var conf = confirm('Do you really want delete?');
   
   if(conf){
   	   
	   document.getElementById('paraFrm').action='CareerProgression_remove.action';
	   document.getElementById('paraFrm').submit();
	   return true;
	   
	}else{//UNCHECK THE CHECK BOXES SELECTED
	
	  for(k=1;k<=count;k++){
	  		
			document.getElementById('chkRemove'+k).checked=false;
			
	  }
	
	}
	return false;
	
                  
  }
 
                  
function callSetRemoveFlag(field,id){
	 
	if(field.checked){
	 document.getElementById(id).value='Y';	
	}else{
	 document.getElementById(id).value='N';
	}
	
}


  function skipFun()
 {
    //alert(document.getElementById('chkTrngAppl').checked);
    document.getElementById('paraFrm').action='CareerProgression_skip.action';	
	document.getElementById('paraFrm').submit();			  	
}  

</script>
