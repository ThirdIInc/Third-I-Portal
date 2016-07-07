<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<link href="css/hrms.css" rel="stylesheet" type="text/css" />

	<script language="JavaScript" type="text/javascript"
	src="..pages/common/include/javascript/sorttable.js"></script>

<%
	Object visibility[]  = (Object[])request.getAttribute("visibility");
	Object comments[]  =(Object[])request.getAttribute("comment");
%>


<s:form theme="simple" name="paraFrm" id="paraFrm" method="post" action="DisciplinaryMeasures_input">
 <%
String calupdateflag=(String)request.getAttribute("calupdateFlag");
%>
<s:hidden name="calupdateFlag" value="<%=calupdateflag %>"/>
<s:hidden name="mode" theme="simple" />
<s:hidden name="startDate" />
<s:hidden name="endDate" /> 
 <table width="100%" border="0" align="right" cellpadding="2"
					cellspacing="1" class="formbg">
				
				<tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Disciplinary Action History</strong></td>
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
		               <s:checkbox name="chkDiscipAppl" id="chkDiscipAppl" theme="simple"   />
		                
		               <label  class = "set" name="discip.action.applicable"  id="discip.action.applicable" ondblclick="callShowDiv(this);"><%=label.get("discip.action.applicable")%></label>
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
               					<label  name="appraisal.code" class = "set"  id="appraisal.code" ondblclick="callShowDiv(this);"><%=label.get("appraisal.code")%></label>
               				</td>
               				<td   height="13">               					
               					<s:property  value="apprCode"  />
               					<input type="hidden" name="apprCode" value="<s:property  value="apprCode"  />">
               					<s:hidden name="apprId"  />
               				</td>
               				<td nowrap="nowrap">
               				 	<label name="discp.from" class = "set"  id="discp.from" ondblclick="callShowDiv(this);"><%=label.get("discp.from")%></label> :
               				 	<s:property value="startDate" /> 
               				 	&nbsp;&nbsp;
							 	<label name="discp.to" class = "set"  id="discp.to" ondblclick="callShowDiv(this);"><%=label.get("discp.to")%></label> :
               				 	<s:property value="endDate" />
               				</td>
                  		</tr>
                  		<tr>
                  			<td width="170">
               					<label name="template.name" class = "set"  id="template.name" ondblclick="callShowDiv(this);"><%=label.get("template.name")%></label> :
               				</td>
               				<td>
               					<s:property value="templateName"/>
               					<input type="hidden" name="templateName" value="<s:property  value="templateName"  />">
               					<s:hidden  name="templateCode" />
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
               	 <b><label name="discip.action.applicability" class = "set"  id="discip.action.applicability" ondblclick="callShowDiv(this);"><%=label.get("discip.action.applicability")%></label></b>
               </td>
              </tr>
              <tr>
                <td>
					<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2">
                  		<tr>
							<td  width="10%" height="32">
								<label name="applicable.phase" class = "set"  id="applicable.phase" ondblclick="callShowDiv(this);"><%=label.get("applicable.phase")%></label> :
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
							
							
							<s:iterator value="phaseList">
								<td width="2%">
			 						<input type="checkbox" name="phase" id="chk<%=i %>" value="wd" onclick="setCode('chk<%=i %>','applicability<%=i %>')" <%if(visibility!=null){%> <%= visibility[i]%> <%} %> > <s:property value="phase"/> 
			 						<input type="hidden" name="hPhaseId"  id="hPhaseId" value="<s:property value="phaseId"  />">
			 						<input type="hidden" name="applicability" id="applicability<%=i %>" value="<%if(visibility!=null && visibility[i].equals("checked")){%> <%="Y"%> <%}else{ %> <%="N"%>  <%} %>"  >
			 						<input type="hidden" name="hSectionId" value="<s:property value="hSectionId" />">
			 						<input type="hidden" name="hPhase" value="<s:property value="phase"/>" />
								</td>
								<%i++; %>
							</s:iterator>
				  		</tr>
				  
						  <tr>
							<td  width="10%" height="32">
							<label name="allow.comments" class = "set"  id="allow.comments" ondblclick="callShowDiv(this);"><%=label.get("allow.comments")%></label> :
							</td>
							<% int j=0; %>
							<s:iterator value="phaseList">
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
  document.getElementById('paraFrm').action='DisciplinaryMeasures_next.action';
  document.getElementById('paraFrm').submit();
}

function saveandnextFun()
{
  document.getElementById('paraFrm').action='DisciplinaryMeasures_saveAndNext.action';
  document.getElementById('paraFrm').submit();
}
function saveandpreviousFun()
{
  document.getElementById('paraFrm').action='DisciplinaryMeasures_saveAndPrevious.action';
  document.getElementById('paraFrm').submit();
}	
	
function previousFun(){
 	document.getElementById('paraFrm').action='AwardsRecognition_input.action';	
	document.getElementById('paraFrm').submit();
}

function nextFun()
{
    //alert(document.getElementById('chkTrngAppl').checked);	
    document.getElementById('paraFrm').action='CareerProgression_input.action';	
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
    document.getElementById('paraFrm').action='DisciplinaryMeasures_skip.action';	
	document.getElementById('paraFrm').submit();			  	
}  

</script>
