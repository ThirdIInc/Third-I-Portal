 <%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<link href="css/hrms.css" rel="stylesheet" type="text/css" />

	<script language="JavaScript" type="text/javascript"
	src="..pages/common/include/javascript/sorttable.js"></script>

<%
	Object visibility[]  = (Object[])request.getAttribute("visibility");
	Object comments[]  =(Object[])request.getAttribute("comment");
%>
 

<s:form theme="simple" name="paraFrm" id="paraFrm" method="post" action="TrainingDetails_input">
 <%
String calupdateflag=(String)request.getAttribute("calupdateFlag");
%>
<s:hidden name="calupdateFlag" value="<%=calupdateflag %>"/>
 <s:hidden name="mode" theme="simple" />
 <s:hidden theme="simple"  name="removeQuestions" />
 <s:hidden theme="simple"  name="startDate" />
 <s:hidden theme="simple"  name="endDate" />
 
  <table width="100%" border="0" align="right" cellpadding="2"
					cellspacing="1" class="formbg">
				
				<tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Training Details</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
       
      <tr> 
			
          
      <tr>
          <td colspan="3"> 
          
          <table width="100%" border="0" cellpadding="2" cellspacing="2">
				<tr> 
					<td width="84%">
						<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp"/>
					</td>
					<td width="16%">
								<font color="red">*</font> Indicates Required
					</td> 
				</tr>
			</table>                    
          </td>
        </tr>
   		
   		
	         <tr>
	          <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
	              <tr>
	                <td>
					<table width="101%" border="0" align="center" cellpadding="2" cellspacing="2" height="39">
	                  
	                  
	                  <tr><td width="267">		               
		               <s:checkbox name="chkTrngAppl" id="chkTrngAppl" theme="simple"   />
		                
		               <label  name="trng.dtls.applicable"  class = "set"  id="trng.dtls.applicable" ondblclick="callShowDiv(this);"><%=label.get("trng.dtls.applicable")%></label>
	               </td> 
	             
	                  </tr>
	                 
	            </table></td>
	        </tr>
	         </table>
	         </td>
        </tr>
        
        
        <tr>
        
          <td colspan="3">
          	<table  width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
              <tr>
                <td>
					<table  width="100%" border="0" align="center" cellpadding="2" cellspacing="2" height="58">                  
                  		
                  		<tr>
                  			<td  height="13" width="15%">
               					<label name="appraisal.code" class = "set"  id="appraisal.code" ondblclick="callShowDiv(this);"><%=label.get("appraisal.code")%></label>:
               				</td>
               				<td   height="13">               					
               					<s:property  value="apprCode"   />
               					<input type="hidden" name="apprCode" value="<s:property  value="apprCode"  />">
               					<s:hidden theme="simple"  name="apprId"   />               					
               				</td>
               				<td>
							<label  name="trng.from"  class = "set"  id="trng.from" ondblclick="callShowDiv(this);"><%=label.get("trng.from")%></label> :               				
               				<s:property  value="startDate" />&nbsp;&nbsp;
							<label  name="trng.to"  class = "set"  id="trng.to" ondblclick="callShowDiv(this);"><%=label.get("trng.to")%></label> :               				
                				<s:property value="endDate" /></td>
                  		</tr>
                  		<tr>
                  			<td width="170">
               					<label name="template.name" class = "set"  id="template.name" ondblclick="callShowDiv(this);"><%=label.get("template.name")%></label> :
               				</td>
               				<td>
               					<s:property value="templateName"/>
               					<input type="hidden" name="templateName" value="<s:property  value="templateName"  />">
               					<s:hidden theme="simple"   name="templateCode" />
               				</td>
							<td width="453">
               					&nbsp;
               				</td>
                  		</tr>                 
            		</table>
            	</td>
        	</tr>
         </table>
       </td>
      </tr>
        
        
        
          <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
              
              <tr>
               <td>
               		<b><label  name="trng.recon.applicable" class = "set"  id="trng.recon.applicable" ondblclick="callShowDiv(this);"><%=label.get("trng.recon.applicable")%></label></b>
               </td>
              </tr>
              <tr>
                <td>
					<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2">
                  		<tr>
							<td  width="10%" height="32">
								<label  class = "set"  id="applicable.phase" ondblclick="callShowDiv(this);"><%=label.get("applicable.phase")%></label> :
							</td>
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
								<td width="2%" >									
			 						<input type="checkbox" name="phase" id="chk<%=i %>" value="wd" onclick="setCode('chk<%=i %>','applicability<%=i %>')" <%if(visibility!=null){%> <%= visibility[i]%> <%} %> > <s:property value="phase"/> 
			 						<input type="hidden" name="hPhaseId"  id="hPhaseId" value="<s:property value="phaseId"  />">
			 						<input type="hidden" name="applicability" id="applicability<%=i %>" value="<%if(visibility!=null && visibility[i].equals("checked")){%> <%="Y"%> <%}else{ %> <%="N"%>  <%} %>"  >
			 						<input type="hidden" name="hSectionId" value="<s:property value="hSectionId" />">
			 						<input type="hidden" name="hPhase" value="<s:property value="phase"/>" /></td>
								<%i++; %>
							</s:iterator>
				  		</tr>
				  
						  <tr>
							<td  width="10%" height="32">
							<label  class = "set"  id="allow.comments" ondblclick="callShowDiv(this);"><%=label.get("allow.comments")%></label> :
							</td>
							<% int j=0; %>
							<s:iterator value="trngList">
							<td width="7%">
		 						<input type="checkbox" name="phase" id="chkc<%=j %>" value="wd" onclick="setCode('chkc<%=j %>','comments<%=j %>')" <%if(comments!=null){%> <%=comments[j]%> <%} %> /> <s:property value="phase"/> 						
		 						<input type="hidden" name="comments" id="comments<%=j %>" value="<%if(comments!=null && comments[j].equals("checked")){%> <%="Y"%> <%}else{ %> <%="N"%>  <%} %>">		 						
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
          <td colspan="3" >
			<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg" height="157">
              <tr>
                <td>
				<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" height="138">
                  <tr>
	                  <td width="100%" height="18" colspan="3" nowrap="nowrap">
	                  	<strong>
							<label name="trng.recon" class = "set"  id="trng.recon" ondblclick="callShowDiv(this);"><%=label.get("trng.recon")%></label>                  		
	                  	</strong>
	                  </td>
                  </tr> 
				  <tr>
					<td  width="15%" >
						 <label  class="set" name="add.quest"  id="add.quest" ondblclick="return callShowDiv(this);"><%=label.get("add.quest")%></label> : #
					</td>
					<td   width="60%">
					
						<s:textarea name="question" theme="simple" rows="2" cols="100" readonly="true" />
						<s:hidden name="rownum" theme="simple" />
						<s:hidden name="questionId" theme="simple" />
						
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
				   					document.getElementById('paraFrm').action="TrainingDetails_addQuestion.action";
				   					document.getElementById('paraFrm').submit();
				   					return true;
			   					}else{
			   					 alert('Please select a question to add to the list.');
			   					 return false;
			   					}
			   				}
			   			</script>
			   		</td>
			   		<td  width="15%">
			   		 	<img border="0" src="../pages/images/recruitment/search2.gif" width="16" height="15" onclick="javascript:callsF9(500,325,'TrainingDetails_f9quest.action');">
			   			<input type="button" onclick="callAddQuestion();" class="add" value="   Add ">
			   			
			   		</td>
				  </tr>
				
				  
     			  <tr>
     			  	<td colspan="2" width="90%">
                  		<strong>
                  		 <label name="quest.list" class = "set"  id="quest.list" ondblclick="callShowDiv(this);"><%=label.get("quest.list")%></label>
                  		
                  		</strong>	
                  	</td>
                  	<td align="right">
                  	&nbsp;
                   </td>
                  </tr>
                 
                  <tr>
                  <td colspan="3">
                  	<table width="100%" border="0" cellpadding="1" cellspacing="1"   class="sortable">
                    	<tr >
                      		<td width="5%" valign="top" class="formth">
                      		<label  name="trng.sno"  class = "set"  id="trng.sno" ondblclick="callShowDiv(this);"><%=label.get("trng.sno")%></label>
                      		</td>
                      		<td width="85%" valign="top" class="formth" nowrap="nowrap">                      		
                      		<label  name="trng.quest"  class = "set"  id="trng.quest" ondblclick="callShowDiv(this);"><%=label.get("trng.quest")%></label>
                      		</td>
                      		<td width="10%" valign="top" class="formth" nowrap="nowrap">
								<s:if test="removeFlag">
                      				<input type="button" class="delete" value="  Remove" onclick="return callRemove();">
                      			</s:if>
							</td>
                      </tr>
                    	<% int count=0; %>
                    	<s:iterator value="questionList">
                    	<tr <%if(count%2==0){
									%> class="tableCell1" <%}else{ %>
									class="tableCell2" <%	} %>
						>
                    	 <td class="sortableTD"><%=++count %></td>
                    	 <td class="sortableTD">
                    	 	<input type="hidden" value="<%=count%>" name="sNo" >
                    	 	<input type="hidden" name="hQuestionId" id="hQuestionId<%=count%>" value="<s:property value="hQuestionId" />" >
                    	 	<input type="hidden" name="hQuestion" value="<s:property value="hQuestion" />">
                    	 	<input type="hidden" name="hQuestSectionId" value="<s:property value="hSectionId" />">
                    	 	<input type="hidden" name="removeQuestion" id="removeQuestion<%=count%>" value="N">
							<s:property value="hQuestion" />
                    	 </td>
                    	 <td align="center" class="sortableTD">
                    	  <input type="checkbox"  name="chkRemove"  id="chkRemove<%=count%>"   onclick="callSetRemoveFlag(this,'removeQuestion<%=count%>')"   >
                    	  <input type="hidden" name="">
                    	 </td>
                    	</tr>                    	
                    	</s:iterator>
                    	<input type="hidden" name="rows" id="rows" value="<%=count%>">		
                  </table></td>
                </tr>
</table></td>
        </tr>

        <tr>
                    <td colspan="3">
                    # : 
                      <label name="note" class = "set"  id="note" ondblclick="callShowDiv(this);"><%=label.get("note")%></label>
                    </td>
                    </tr>

              </table></td></tr>
                         
           
              
              
              
                  
         <tr>
          <td colspan="3">           
          	<table width="100%" border="0" cellpadding="2" cellspacing="2">
				<tr> 
					<td width="75%">
						<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp"/>
					</td>
				</tr>
			</table>                    
          </td>
        </tr>
        
  
        
          
  </table>
 

</s:form>



<script>

function saveFun()
{
  document.getElementById('paraFrm').action='TrainingDetails_next.action';
  document.getElementById('paraFrm').submit();
}

function saveandnextFun()
{
  document.getElementById('paraFrm').action='TrainingDetails_saveAndNext.action';
  document.getElementById('paraFrm').submit();
}
function saveandpreviousFun()
{
  document.getElementById('paraFrm').action='TrainingDetails_saveAndPrevious.action';
  document.getElementById('paraFrm').submit();
}

function previousFun(){
 	document.getElementById('paraFrm').action='DefineSection_input.action';
	document.getElementById('paraFrm').submit();
}


function nextFun()
 {
    //alert(document.getElementById('chkTrngAppl').checked);	
    document.getElementById('paraFrm').action='AwardsRecognition_input.action';	
	document.getElementById('paraFrm').submit();
			  	
}
  function cancelFun()
 {
    //alert(document.getElementById('chkTrngAppl').checked);	 
    document.getElementById('paraFrm').action='TemplateDefination_input.action';	
	document.getElementById('paraFrm').submit();
			  	
}  	

  function skipFun()
 {
    //alert(document.getElementById('chkTrngAppl').checked);
    document.getElementById('paraFrm').action='TrainingDetails_skip.action';	
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
   	   
	   document.getElementById('paraFrm').action='TrainingDetails_remove.action';
	   document.getElementById('paraFrm').submit();
	   return true;
	   
	}else{ //UNCHECK THE CHECK BOXES SELECTED
	
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


</script>
