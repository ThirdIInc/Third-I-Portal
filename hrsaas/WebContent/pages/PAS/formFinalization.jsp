 <%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<link href="css/hrms.css" rel="stylesheet" type="text/css" />

	<script language="JavaScript" type="text/javascript"
	src="..pages/common/include/javascript/sorttable.js"></script>



<s:form theme="simple" name="paraFrm" id="paraFrm" method="post" action="FormFinalization_input">
  <%
String calupdateflag=(String)request.getAttribute("calupdateFlag");
%>
<s:hidden name="calupdateFlag" value="<%=calupdateflag %>"/>
 <s:hidden name="mode" theme="simple" />
 <s:hidden name="startDate" />
 <s:hidden name="endDate" />
 <table width="100%" class="formbg" >
				<tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Form Finalization</strong></td>
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
                  		 <td colspan="3" height="13">
                  		 	<b><label name="frm.success" class = "set"  id="frm.success" ondblclick="callShowDiv(this);"><%=label.get("frm.success")%></label></b>
                  		 </td>
                  		</tr>
                  		
                  		<tr>
                  		 <td colspan="3" height="13">&nbsp;</td>
                  		</tr>
                  		
                  		<tr >
                  			<td  height="13" width="15%">
               					<label name="appraisal.code" class = "set"  id="appraisal.code" ondblclick="callShowDiv(this);"><%=label.get("appraisal.code")%></label>
               				</td>
               				<td   height="13" width="25%">
               					<s:property  value="apprCode"  />
               					<input type="hidden" name="apprCode" value="<s:property  value="apprCode"  />">
               					<s:hidden name="apprId"   />
               				</td>
               				<td height="13" nowrap="nowrap">
               					<label name="frm.from" class = "set"  id="frm.from" ondblclick="callShowDiv(this);"><%=label.get("frm.from")%></label> : 
               					<s:property value="startDate"/>
               					&nbsp;&nbsp;  
               					<label name="frm.to" class = "set"  id="frm.to" ondblclick="callShowDiv(this);"><%=label.get("frm.to")%></label> : 
               					<s:property value="endDate"/> 
               				</td>
                  		</tr>
                  		<tr>
                  			<td height="13" >
               					<label name="template.name" class = "set"  id="template.name" ondblclick="callShowDiv(this);"><%=label.get("template.name")%></label> :
               				</td>
               				<td height="13">
               					<s:property value="templateName"/>
               					<input type="hidden" name="templateName" value="<s:property  value="templateName"  />">
               					<s:hidden  name="templateCode" />
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
                  		  <td >
                  			<table width="100%" border="0" cellpadding="1" cellspacing="1"   class="sortable">
                    			<tr>
		                      		<td width="5%" valign="top" class="formth">
		                      		<label  name="quest.sno"  class = "set"  id="quest.sno" ondblclick="callShowDiv(this);"><%=label.get("quest.sno")%></label>
		                      		</td>
		                      		<td width="85%" valign="top" class="formth" nowrap="nowrap">
		                      			<label  name="frm.step.name"  class = "set"  id="frm.step.name" ondblclick="callShowDiv(this);"><%=label.get("frm.step.name")%></label>
		                      		</td>
		                      		<td width="10%" valign="top" class="formth" nowrap="nowrap">		                      		
		                      		<label  name="frm.applicability"  class = "set"  id="frm.applicability" ondblclick="callShowDiv(this);"><%=label.get("frm.applicability")%></label>
		                      		</td>
		                      		<td width="10%" valign="top" class="formth" nowrap="nowrap">		                      		
		                      		<label  name="frm.config.status"  class = "set"  id="frm.config.status" ondblclick="callShowDiv(this);"><%=label.get("frm.config.status")%></label>
		                      		</td>
                      			</tr> 
                      			<% int i=1; %>
                      			<s:iterator value="stepList">
                      			<tr>
                      			 <td class="sortableTD"><%=i++ %></td>
                      			 <td class="sortableTD"><s:property value="step" /></td>
                      			 <td class="sortableTD" align="right"><s:property value="applicability" /></td>
                      			 <td class="sortableTD" align="right"><s:property value="status" /></td>
                      			</tr>                     			
                      			</s:iterator>
            				</table>
            			   </td>
        				</tr>
         </table>
       </td>
      </tr>
       
  </table>
  
  <table width="100%">
   	<tr>
         <td colspan="3"> 
         
         <table width="100%" border="0" cellpadding="2" cellspacing="2">
			<tr> 
				<td width="100%">
					<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp"/>
				</td>
			</tr>
		</table>                    
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
  document.getElementById('paraFrm').action='WeightageDistribution_next.action';
  document.getElementById('paraFrm').submit();
}
	
function previousFun(){
 	document.getElementById('paraFrm').action='WeightageDistribution_input.action';	
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

 function finishFun()
 {
    //alert(document.getElementById('chkTrngAppl').checked);
    var conf=confirm('Do you really want to finish the wizard ?');
    
    if(conf){
    	document.getElementById('paraFrm').action='TemplateDefination_input.action';	
		document.getElementById('paraFrm').submit(); 
    }
			  	
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
