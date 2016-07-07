<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<link href="css/hrms.css" rel="stylesheet" type="text/css" />

	<script language="JavaScript" type="text/javascript"
	src="..pages/common/include/javascript/sorttable.js"></script>



<s:form theme="simple" name="paraFrm" id="paraFrm" method="post" action="WeightageDistribution_input">
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
				          <td width="93%" class="txt"><strong class="text_head">Weightage Distribution</strong></td>
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
          <td colspan="3">
          	<table  width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
              <tr>
                <td>
					<table  width="100%" border="0" align="center" cellpadding="2" cellspacing="2" height="58">                  
                  		
                  		<tr>
                  			<td  height="13" width="15%">
               					<label name="appraisal.code" class = "set"  id="appraisal.code" ondblclick="callShowDiv(this);"><%=label.get("appraisal.code")%></label>
               				</td>
               				<td   height="13">
               					<s:property  value="apprCode"  />
               					<input type="hidden" name="apprCode" value="<s:property  value="apprCode"  />">
               					<s:hidden name="apprId"   />
               				</td>
               				<td height="13" nowrap="nowrap"> 
               					<label name="weigh.from" class = "set"  id="weigh.from" ondblclick="callShowDiv(this);"><%=label.get("weigh.from")%></label> :
               					<s:property value="startDate"/>
               					&nbsp;&nbsp;  
               					<label name="weigh.to" class = "set"  id="weigh.to" ondblclick="callShowDiv(this);"><%=label.get("weigh.to")%></label> : 
               					<s:property value="endDate"/> 
               				</td>
                  		</tr>
                  		<tr>
                  			<td width="170" height="13">
               					<label name="template.name" class = "set"  id="template.name" ondblclick="callShowDiv(this);"><%=label.get("template.name")%></label> :
               				</td>
               				<td height="13">
               					<s:property value="templateName"/>
               					<input type="hidden" name="templateName" value="<s:property  value="templateName"  />">
               					<s:hidden  name="templateCode" />
               				</td>
							<td width="453" height="13">
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
          	<table  width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
              <tr>
                <td>
					<table  width="100%" border="0" align="center" cellpadding="2" cellspacing="2" height="58">                  
                  		<tr>
                  			<td>
                  				<b>
                  				 <label name="weigh.phase.list" class = "set"  id="weigh.phase.list" ondblclick="callShowDiv(this);"><%=label.get("weigh.phase.list")%></label>
                  			    </b> 
                  			</td>
                  		</tr>
                  		<tr>
                  		 <td>&nbsp;</td>
                  		</tr> 
                  		
                  		 <tr>
                  		  <td >
                  			<table width="100%" border="0" cellpadding="1" cellspacing="1"   class="sortable">
                    			<tr>
		                      		<td width="5%" valign="top" class="formth">
		                      		<label  name="quest.sno"  class = "set"  id="quest.sno" ondblclick="callShowDiv(this);"><%=label.get("quest.sno")%></label>
		                      		</td>
		                      		<td width="85%" valign="top" class="formth" nowrap="nowrap">		                      		
		                      		<label  name="weigh.phase.name"  class = "set"  id="weigh.phase.name" ondblclick="callShowDiv(this);"><%=label.get("weigh.phase.name")%></label>
		                      		</td>
		                      		<td width="10%" valign="top" class="formth" nowrap="nowrap">		                      		
		                      		<label  name="weigh.weightage"  class = "set"  id="weigh.weightage" ondblclick="callShowDiv(this);"><%=label.get("weigh.weightage")%></label>
		                      		</td>
                      			</tr> 
                      			<% int i=0; %>
                      			<% long totalWeightage = 0;
                      			   totalWeightage = (Long)request.getAttribute("totalWeightage"); 
                      			%>
                      			<s:iterator value="phaseList">
                      			<tr>
                      			 <td class="sortableTD"><%=++i %></td>
                      			 <td class="sortableTD"><s:property value="phase" /></td>
                      			 <td class="sortableTD" align="right"><s:property value="weightage" /></td>
                      			</tr>                     			
                      			</s:iterator>
                      			<tr>
                      			 <td class="sortableTD">&nbsp;</td>
                      			 <td class="sortableTD" align="right"><b><%=totalWeightage>0?"Total Weightage":"" %></b></td>
                      			 <td class="sortableTD" align="right"><b><%=totalWeightage>0?totalWeightage:"" %></b></td>
                      			</tr>
            				</table>
            			   </td>
        				</tr>
         </table>
       </td>
      </tr>
        
            
  </table>
  
  <tr>
          <td colspan="3"> 
          
          <table width="100%" border="0" cellpadding="2" cellspacing="2">
				<tr> 
					<td width="84%">
						<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp"/>
					</td>					
				</tr>
			</table>                    
          </td>
	   </tr>
	  
	 </td>
	</tr>
 </table>

</s:form>



<script>

function saveFun()
{
  document.getElementById('paraFrm').action='WeightageDistribution_next.action';
  document.getElementById('paraFrm').submit();
}
	
function previousFun(){
 	document.getElementById('paraFrm').action='CareerProgression_input.action';	
	document.getElementById('paraFrm').submit();
}


function nextFun()
 {
    //alert(document.getElementById('chkTrngAppl').checked);	
    document.getElementById('paraFrm').action='FormFinalization_input.action';	
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

	for(k=1;k<=count;k++){
 
		if( document.getElementById('chkRemove'+k).checked ){
			document.getElementById('paraFrm').action='CareerProgression_remove.action';
			document.getElementById('paraFrm').submit();
		  	return true;
		 }
 
	}
	alert('Please select a question to remove.');
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
