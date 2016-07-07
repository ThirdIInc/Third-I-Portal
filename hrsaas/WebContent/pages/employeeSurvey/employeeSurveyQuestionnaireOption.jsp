<!-- JSP file for front end for Answer Option Details -->

<%@taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>

<s:form action="EmployeeSurveyQuestionnaire" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">

<s:hidden name="quesCode"/>
		<s:hidden name="show" />
		<s:hidden name="myPage" id="myPage" />
		<s:hidden name="surveyCode" />
		<s:hidden name="paraId" />
		<s:hidden name="optionId" />
		<s:hidden name="optionFlag" />
		<s:hidden name="surveyName" />
		<s:hidden name="question" />
		<s:hidden name="descCnt" />
		<s:hidden name="ansOptions" />

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%">
					<input type="button" class="add"
						value=" Add " onclick="return addValidation();" />
					<input type="button" class="cancel"
						value=" Close " onclick="return callClose();" />
						
						
						</td>
					<td width="22%">
					</td>
				</tr>
			</table>
			</td>
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
							<td colspan="5" class="text_head"><strong
								class="forminnerhead">Answer Options</strong></td>
						</tr>
						<tr>
							<td width="21%" height="22" class="formtext" valign="top" nowrap="nowrap"><label
								class="set" name="ansopt" id="ansopt"
								ondblclick="callShowDiv(this);"><%=label.get("ansopt")%></label><font
								color="red">*</font>:</td>
							<td width="35%" height="22"><s:textarea theme="simple"
								name="optionTextarea" rows="4" cols="50"
								
								onkeyup="callLength('optionTextarea','descCnt1','1000');"
								readonly="false" /></td>
							<td height="22" colspan="3" valign="bottom">&nbsp;&nbsp;<img
								src="../pages/images/zoomin.gif" height="12" align="bottom"
								width="12" theme="simple"
								onclick="javascript:callWindow('paraFrm_optionTextarea','ansopt','','paraFrm_descCnt1','1000');">
							&nbsp;Remaining chars <s:textfield name="descCnt1"
								readonly="true" size="5"></s:textfield></td>
						</tr>

					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr><td>
		<input type="button" class="add"
						value=" Add " onclick="return addValidation();" />
					<input type="button" class="cancel"
						value=" Close " onclick="return callClose();" /></td>
		
		</tr>


		<%int j=0; %>
		<s:iterator value="optionList"> 
						 <%++j;%><s:hidden name="srNo" /> 
							 <s:hidden name="optionName" /> 
 					 <s:hidden name="flagItt" /> <s:hidden name="optionCode" /></td>
							<input type="hidden" name="hdeleteOp<%=j%>" id="hdeleteOp<%=j%>" /> 
		 </s:iterator> 
	</table>
</s:form>


<script>
	
  function callforedit(id,id1,id2,id3){
	
		document.getElementById("paraFrm_option").value=id;
		document.getElementById("paraFrm_paraId").value=id1;
	
		document.getElementById("paraFrm_optionId").value=id3;
		if(id2=="Right"){
			document.getElementById("paraFrm_flag").value="Y";
			//document.getElementById("paraFrm_hflag").value="Yes";
		}else{
			document.getElementById("paraFrm_flag").value="N";//=false;
			//document.getElementById("paraFrm_hflag").value='';
		
		}
		
			
	} 
   

function callfordel(id,id1){
	
	
		var conf=confirm("Do you really want to delete this record ?");
  			if(conf) {
		document.getElementById("paraFrm_paraId").value=id;
		document.getElementById("paraFrm_optionId").value=id1;
		document.getElementById("paraFrm").action="QuestionBankAction_deleteForOption.action";
	    document.getElementById("paraFrm").submit();
	    }

   }
   
	function callClose(){
		window.close();
	}   
     
	function addValidation(){
		var val=trim(document.getElementById('paraFrm_optionTextarea').value);
		var finalVal;
		
		if (val.search(/\r/g) != -1) {
		   //alert('enter found');
		   finalVal = val.replace(/\r/g, " ");
		  // alert(finalVal);
		  }else{
		  	finalVal=val;
	  	}
	 	opener.document.getElementById('paraFrm_optionTextarea').value =document.getElementById('paraFrm_optionTextarea').value;
		if(val==""){
			alert('Please enter the '+document.getElementById('ansopt').innerHTML.toLowerCase());
			return false;
		}
		
		
		else if(val.length > 1000){
			
			/*alert('Maximum length of '+document.getElementById('ansopt').innerHTML.toLowerCase()+" is 2000 characters");*/
			
			alert("Maximum length of Question is 1000 characters");
			return false;
		}else {
			
			opener.document.getElementById('paraFrm').target='main';
			opener.document.getElementById('paraFrm').action='EmployeeSurveyQuestionnaire_addOption.action'; 
			opener.document.getElementById('paraFrm').submit();
			
			window.close();
		}
	}
	
	</script>

