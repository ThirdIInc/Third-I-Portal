<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="QuestionCategory" validate="true" id="paraFrm"
	theme="simple">
	
   <s:hidden name="sMode"></s:hidden>
   <s:hidden name="sQuestionCode"></s:hidden>
  
  <table width="100%" border="0" align="right" cellpadding="2" cellspacing="2" class="formbg" >
    <tr>
		<td colspan="3">
		<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
			<tr>
				<td>
				<table width="98%" border="0" align="center" cellpadding="2" cellspacing="1">
	
					<tr>
						<td valign="bottom" class="txt"><strong class="formhead"><img
							src="../pages/images/review_shared.gif" width="25" height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="text_head">Questionnaire Definition</strong></td>
						<td width="3%" valign="top" class="txt">
						<div align="right"><img src="../pages/images/help.gif"
							width="16" height="16" /></div>
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
      	<table width="100%" border="0" cellpadding="2" cellspacing="1">
         <s:if test="isApprove"></s:if><s:else>
           <tr>
           <td width="78%">
           		<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
		   </td>
            <td width="22%"><div align="right"><font color="red">*</font> Indicates Required </div></td>
          </tr>
         </s:else>
        </table>
      </td>
    </tr>
    
 
   	<!-- Body -->

	<tr>
    	<td colspan="3">
    		<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
              <tr>
                <td>
					<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2">
                  			
                  			<tr>
                    			<td width="15%" height="30" class="formtext" nowrap="nowrap">
                    				<label name="questionnairedefinition.question" class = "set"  id="questionnairedefinition.question" ondblclick="callShowDiv(this);"><%=label.get("questionnairedefinition.question")%></label>
                    				<font color="#FF0000">*</font> : 
                    			</td>
                    			<td width="80%" height="30" colspan="3" nowrap="nowrap">
									<s:textarea rows="3" cols="85" name="sQuestion" onkeyup="callLength('sQuestion','descCnt','500');"></s:textarea>
									&nbsp;
									<img src="../pages/images/zoomin.gif" height="12" width="12" 
									onclick = "javascript:callWindow('paraFrm_sQuestion','questionnairedefinition.question','','paraFrm_descCnt','500');" >
									Remaining characters &nbsp;
                    				<s:textfield name="descCnt" readonly ="true" size="5" cssStyle="background-color: #F2F2F2"></s:textfield>
                      			</td>
                    			<td width="5%" height="30" align="left" valign="bottom">
                    				 &nbsp;
								</td>
        					</tr>
                 
        					<tr>
                    			<td width="15%" height="30" class="formtext" nowrap="nowrap">
                    				<label name="questionnairedefinition.answertype" class = "set"  id="questionnairedefinition.answertype" ondblclick="callShowDiv(this);"><%=label.get("questionnairedefinition.answertype")%></label> :
                    			</td>

                    			<td width="30%" height="30" nowrap="nowrap">
									<s:if test="flgQuestionType">
										<input type="radio" name="sQuestionType" value="O" checked="checked" onclick="return callAnswerLimitVal(this);">
										Objective
										 
										<input type="radio" name="sQuestionType" value="D" onclick="return callAnswerLimitVal(this);" >
										Descriptive
										
									</s:if>
									<s:else>	
									 	<input type="radio" name="sQuestionType" value="O" onclick="return callAnswerLimitVal(this);">
									 	Objective
									 	
										<input type="radio" name="sQuestionType" value="D" checked="checked" onclick="return callAnswerLimitVal(this);" >
										Descriptive
									</s:else> 
								</td>
								
                    			<td width="10%" height="30" class="formtext" nowrap="nowrap">
                    				<label name="questionnairedefinition.status" class = "set"  id="questionnairedefinition.status" ondblclick="callShowDiv(this);"><%=label.get("questionnairedefinition.status")%></label> :
                    			</td>
                    			<td width="30%" height="30" nowrap="nowrap">
									<!-- <s:radio list="#{'A':'Active','D':'De-Active'}" name="sQuestionStatus" value="%{sQuestionStatus}" /> -->
									<s:if test="flgStatus">
										<input type="radio" name="sQuestionStatus" value="A" checked="checked">
										Active
										 
										<input type="radio" name="sQuestionStatus" value="D">
										De-Active
									</s:if> 
									<s:else>
										<input type="radio" name="sQuestionStatus" value="A">
										Active 
										
										<input type="radio" name="sQuestionStatus" value="D" checked="checked">
										De-Active
									</s:else>
								</td>
								                    
								<td width="5%" height="25"> 
									&nbsp;
								</td>
							</tr>
							
                    		<tr>
                    			<td width="15%"> 
									&nbsp;
								</td>
								
								<td width="85%" colspan="4">
									<!--  
									<div id="charDiv">
									-->
										<table width="100%" border=0>
											<tr>
									  			<td width="16%">
													<label name="charLimit" class = "set"  id="charLimit" ondblclick="callShowDiv(this);"><%=label.get("charLimit")%></label> :
												</td>
												
												<td width="7%">
													<s:if test="flgQuestionType">
														<s:textfield name="sAnwserLimit" size="5" onkeypress="return numbersOnly()" maxlength="4" readonly="true" value="" cssStyle="background-color: #F2F2F2"  />
													</s:if><s:else>
														<s:textfield name="sAnwserLimit" size="5" onkeypress="return numbersOnly()" maxlength="4" cssStyle="background-color: #FFFFFF"  />
													</s:else>
												</td>
												
												<td width="78%">
													Note : Default character length will be 500 chars. Maximum character length will be 2000 chars.
												</td>
											</tr>
										</table>
									<!--
									</div>
									-->
								</td>
				   			</tr>
                  			
                  
                  			<tr>
								<td width="15%" height="30" nowrap="nowrap">
									<label name="questionnairedefinition.mandedory" class = "set"  id="questionnairedefinition.mandedory" ondblclick="callShowDiv(this);"><%=label.get("questionnairedefinition.mandedory")%></label> :
								</td>
								
								<td width="30%" height="30" class="formtext" nowrap="nowrap">
									<s:select name="sQuestionMandatory" list="#{'Y':'Yes','N':'No'}" />
									</select>
								</td>
					
				   				<td width="10%" height="30" nowrap="nowrap" >
				   					<label name="questionnairedefinition.questioncategory" class = "set"  id="questionnairedefinition.questioncategory" ondblclick="callShowDiv(this);"><%=label.get("questionnairedefinition.questioncategory")%></label>
				   					<font color="#FF0000">*</font> :
				   				</td>
								
								<td width="30%" height="30" nowrap="nowrap">
									<s:select name="sQuestionCategoryCode" list="lstQuestionCategory" headerKey="" headerValue="--Select--" />
									</select>
								</td>
								
								<td width="10%" height="30"> 
									&nbsp;
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
      	<table width="100%" border="0" cellpadding="2" cellspacing="1">
         <s:if test="isApprove"></s:if><s:else>
            <tr>
            <td width="78%">
           		<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
		    </td>
            
            </tr>
         </s:else>
        </table>
      </td>
    </tr>
    
  </table>
	
</s:form>

<script type="text/javascript">

	function saveFun() 
	{
		var fields=["paraFrm_sQuestion"];
		var labels=["questionnairedefinition.question"];
		var types=["enter"];
		var f9Fields = ["paraFrm_sQuestion"];
		
		if(!(validateBlank(fields,labels,types)))
		{
			return false;
		}
		
		if(!f9specialchars(f9Fields))
		return false;
		
		/* Character Limit */
		var charLimit = document.getElementById("paraFrm_sAnwserLimit").value
		//alert (charLimit);
		
		if (eval(charLimit) < 500)
		{
			alert("Character limit should not less than 500");
			return false;
		}
		
		if (eval(charLimit) > 2000)
		{
			alert("Character limit should not greater than 2000");
			return false;
		}
		
		/* Validate the Question Category */
		if(document.getElementById("paraFrm_sQuestionCategoryCode").value=="")
		{
			alert("Please select the "+document.getElementById("questionnairedefinition.questioncategory").innerHTML.toLowerCase());
			//alert("Please select question category");
			document.getElementById("paraFrm_sQuestionCategoryCode").focus();			
			return false;
		}
		
		var descriptionValue = document.getElementById("paraFrm_sQuestion").value
		if (eval(descriptionValue.length) > 500)
		{
			alert (document.getElementById("questionnairedefinition.question").innerHTML.toLowerCase() + " is not allow more than 500 characters.");
			return false;
		}
		
		
		document.getElementById("paraFrm").action="QuestionnaireDefinition_save.action";
		document.getElementById("paraFrm").submit();
	}
	
	function cancelFun() 
	{
		document.getElementById("paraFrm").action="QuestionnaireDefinition_input.action";
		document.getElementById("paraFrm").submit();	
	}
	
	/*onload();
	
	//function onload()
	/{
	
		alert(document.getElementById("paraFrm_sMode").value);
		
	}
	*/
	
	function callCharDiv(obj)
	{
		if(obj.value=="D")
		{
			document.getElementById('charDiv').style.display='';
			if(document.getElementById("paraFrm_sAnwserLimit").value == '')
			{
				document.getElementById("paraFrm_sAnwserLimit").value = "500";
			}
		}
		else 
		{
			document.getElementById('charDiv').style.display='none';
		}
	}
	
	function callAnswerLimitVal(obj)
	{
		if(obj.value == "D")
		{
			document.getElementById("paraFrm_sAnwserLimit").readOnly = false;
			if (document.getElementById("paraFrm_sMode").value == 'new')
			{
				document.getElementById("paraFrm_sAnwserLimit").value = '500';
			}
			else
			{
				document.getElementById("paraFrm_sAnwserLimit").value = '';
			}
			
			document.getElementById("paraFrm_sAnwserLimit").style.background = '#FFFFFF';
		}
		else
		{
			document.getElementById("paraFrm_sAnwserLimit").readOnly = true;
			document.getElementById("paraFrm_sAnwserLimit").value = '';
			document.getElementById("paraFrm_sAnwserLimit").style.background = '#F2F2F2';
		}
	}
	
	function textCounter(field,  maxlimit) 
	{
		if (field.value.length > maxlimit)
		{			
			field.value = field.value.substring(0, maxlimit);
			field.focus;
			return false;			
		}		
		return true;		
	}

</script>
