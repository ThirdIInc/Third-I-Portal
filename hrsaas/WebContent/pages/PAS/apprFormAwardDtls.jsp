
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<script type = "text/javascript" src = "../pages/common/datetimepicker.js"></script>



<s:form action="ApprFormAwardDtls" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="source" id="source"/>	
	<s:hidden name="phaseId" />
	<s:hidden name="phaseSequence" />
	<input type="hidden" name="delAwdCode" id="delAwdCode"/>
	<input type="hidden" name="hSno" id="hSno"/>
	<s:hidden name="removeAwdCode" />
	<s:hidden name="removeNomCode" />
	<s:hidden name="sourceFormType" id="sourceFormType"/>
	
	<s:hidden name="isSelf" />
	
	  <table width="100%" border="0" align="right" cellpadding="2" cellspacing="1"  class="formbg" >
   			 <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head"><label name="appraisal.form.head" class = "set"  id="appraisal.form.head" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.head")%></label></strong>55555555</td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
   		 <tr>
      			<td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="0">
       			  <tr>
           			 <td width="78%">
           				 <jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
          			
					</td>
            		<td width="22%"><div align="right"><font color="red">*</font> Indicates Required </div></td>
         		 </tr>
        	</table></td>
   		 </tr>
    <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
          <tr>
            <td>
            	<s:hidden name="templateCode"/>
             	<s:hidden name="apprValidTillDate"/> 
             	<s:hidden name="detailFLag"/>
              	<s:hidden name="phaseForwardFlag"/>
                <s:hidden name="sectionCode"/>
                <s:hidden name="sectionList"/>
				<s:hidden name="nextExist"/>
                <s:hidden name="previousExist"/>
                <s:hidden name="mode" />
           	
           	<b><label name="appraisal.details" class = "set"  id="appraisal.details" ondblclick="callShowDiv(this);"><%=label.get("appraisal.details")%></label></b><table width="98%" border="0" align="center" cellpadding="2" cellspacing="2">
              
           <tr>
              <td width="18%" colspan="1" height="20" class="formtext"><label  class = "set" name="appraisal.form.period"  id="appraisal.form.period" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.period")%></label> :</td>
			  <td width="15%" colspan="1" height="20"><s:hidden name="apprId"/><s:hidden name="apprCode"/><s:property value="apprCode"  /></td>
              <td width="10%" colspan="1" height="20"></td>   
              <td width="50%" colspan="1" height="20">
              <label  class = "set" name="appr.from.date"  id="appr.from.date" ondblclick="callShowDiv(this);"><%=label.get("appr.from.date")%></label>: 
              <s:hidden name="apprStartDate"/><s:property value="apprStartDate"  />&nbsp;&nbsp; 
			   <label  class = "set" name="appr.to.date"  id="appr.to.date" ondblclick="callShowDiv(this);"><%=label.get("appr.to.date")%></label>: 
              <s:hidden name="apprEndDate"/><s:property value="apprEndDate" /></td> 
            </tr>
             <tr>
              <td width="18%" colspan="1" height="20" class="formtext"><label  class = "set" name="appraisal.form.phase"  id="appraisal.form.phase" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.phase")%></label> :</td>
			  <td width="15%" colspan="1" height="20"><s:hidden name="phaseCode"/><s:hidden name="phaseName"/><s:property value="phaseName"  /></td>
              <td width="10%" colspan="1" height="20"></td>   
              <td width="50%" colspan="1" height="20">
              <label  class = "set" name="phase.from.date"  id="phase.from.date" ondblclick="callShowDiv(this);"><%=label.get("phase.from.date")%></label>: 
              <s:hidden name="phaseStartDate"/><s:property value="phaseStartDate" />&nbsp;&nbsp; 
			  <label  class = "set" name="phase.to.date"  id="phase.to.date" ondblclick="callShowDiv(this);"><%=label.get("phase.to.date")%></label>: 
              <s:hidden name="phaseEndDate"/><s:property value="phaseEndDate" /><s:hidden name="phaseLockFlag"/><s:hidden name="quesWtDisplayFlag"/></td> 
            </tr>
            <tr>
              <td width="18%" colspan="1" height="20" class="formtext"><label  class = "set" name="employee"  id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> :</td>
			  <td width="15%" colspan="1" height="20"><s:hidden name="empId"/><s:hidden name="empName"/><s:property value="empName"  /></td>
              <td width="10%" colspan="1" height="20"></td>   
              <td width="50%" colspan="1" height="20">
              <label  class = "set" name="designation"  id="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:
              <s:hidden name="empDesgName"/><s:property value="empDesgName" /></td>  
            </tr>
            
            </td>
          </tr>
      </table></td>
    </tr>
    </table></td></tr>
     <input type="hidden" value="<s:property value="phaseSequence" />">
    
<!-- FOR SELF PHASE ONLY THE appraisee could add training attended details -->
    
    <s:if test="isSelf">
     <tr>
      <td colspan="3"> 
       <table border="0" width="100%" cellpadding="2" cellspacing="2"  class="formbg"  >
        <tr>
         <td><b>Award Details</b><s:if test="phaseForwardFlag=='false'">  (Please click on Add Record button to add your Award Details)</s:if></td>
         <td align="right"></td>
        </tr>
        <tr>
         <td colspan="2">
          <table width="98%" class="formbg" align="center" cellpadding="2" cellspacing="2">
           <tr>
            <td class="formth" width="3%">            			  
             <label  name="trng.srno"  class = "set"  id="trng.srno" ondblclick="callShowDiv(this);"><%=label.get("trng.srno")%></label>          
		    </td>
            <td class="formth" width="20%">
            <label  name="awd.desc"  class = "set"  id="awd.desc" ondblclick="callShowDiv(this);"><%=label.get("awd.desc")%></label>            
            </td>
            <td class="formth" width="15%">            
            <label  name="awd.date"  class = "set"  id="awd.date" ondblclick="callShowDiv(this);"><%=label.get("awd.date")%></label>
            </td>
            <td class="formth" width="20%">            
            <label  name="awd.issue"  class = "set"  id="awd.issue" ondblclick="callShowDiv(this);"><%=label.get("awd.issue")%></label>
            </td>
            <s:if test="phaseForwardFlag=='false'"> 
            	<td class="formth" width="5%" align="center"><s:submit action="ApprFormAwardDtls_addAwardDetails" cssClass="add" value="   Add Record" /> </td>
           	</s:if>
           </tr>
           <%int i=0; %>
           <!-- FOR APPRAISAL FORM FILLING -->
           <s:if test="phaseForwardFlag=='false'">  
           <s:iterator value="awardList">
            <tr>
            	<td class="td_bottom_border" align="center"><%=++i%><input type="hidden" name="sNo" value="<%=i%>"><input type="hidden" name="awdCode" id="awdCode<%=i %>" value="<s:property value="awdCode" />"></td>
            	<td class="td_bottom_border" align="center"><input type="text" name="awdDesc" id="awdDesc<%=i %>" size="35"  value="<s:property value="awdDesc" />" onkeypress="return alphaNumeric();"  onkeydown="textCounter(this,200)"></td>
            	<td class="td_bottom_border" align="center">
            			<input type="text" name="awdDate" id="awdDate<%=i %>" size="10" maxlength="10" value="<s:property value="awdDate" />" onkeypress="return numbersWithHiphen();">
            		    <img src="../pages/images/recruitment/Date.gif" class="iconImage" height="18" align="absmiddle" width="18" onclick="javascript:NewCal('<%="awdDate"+i%>','DDMMYYYY');">            	
            	</td>
            	<td class="td_bottom_border" width="10%" align="center">
            	<input type="text" name="awdIssAuth" id="awdIssAuth<%=i %>" size="40"  value="<s:property value="awdIssAuth" />" onkeyup="return textCounter(this,95)"  onkeydown="return textCounter(this,95)">
            	</td> 
	            	<td class="td_bottom_border" align="center">
		            	<input type="button" value="  Remove" class="delete" onclick="return remove(document.getElementById('awdCode<%=i %>').value,'<%=i%>')"  >
		             
		            </td> 
            </tr>
           </s:iterator>
           </s:if>
           <s:else>
           <s:iterator value="awardList">
            <tr>
            	<td class="td_bottom_border" align="center"><%=++i%><input type="hidden" name="sNo" value="<%=i%>"><input type="hidden" name="awdCode" id="awdCode<%=i %>" value="<s:property value="awdCode" />"></td>
            	<td class="td_bottom_border" align="center"><s:property value="awdDesc" /></td>
            	<td class="td_bottom_border" align="center">
            			<s:property value="awdDate" />
            	</td>
            	<td class="td_bottom_border" width="10%" align="center">
            		<s:property value="awdIssAuth" />
            	</td> 	           
            </tr>
           </s:iterator> 
                   
           </s:else>
           <input type="hidden" id="rows1" value="<%=i %>" />
           <%if(i==0){ %>
	           <s:if test="commentFlag">           
	            <tr>
	             <td colspan="7" align="center"><font color="red">No Data To Display</font></td>
	            </tr>           
			   </s:if>
			   <s:else>
            <tr>
             <td colspan="6" align="center"><font color="red">No Data To Display</font></td>
            </tr>
		   </s:else>
		   <%} %>
           <script>
           
           function remove(awdCode,hSno){       
           	    
	           	conf = confirm('Do you really want to delete the record?');
	           	
	           	if(conf){
	           	
	           		document.getElementById('delAwdCode').value=awdCode;
	           		document.getElementById('hSno').value = hSno; 
	           		document.getElementById('paraFrm').action='ApprFormAwardDtls_removeAwardDetails.action';
	           		document.getElementById('paraFrm').submit();	           		
	           		return true; 
	           		
	           	}else{
	           	
	           	 return false;
	           	 
	           	}
            
            
           }
           
           /* function chkTraining(chkId,trngId){
             
             if(document.getElementById(chkId).checked){
               document.getElementById(trngId).value='Y';
             }else{
             	document.getElementById(trngId).value='N';
             }
             
            }*/
           </script>      
          </table>
         </td>
        </tr>
       </table>
      </td>
     </tr>
    </s:if>
    
<!--   FOR OTHER PHASES the appraiser could add comments for training attended and RECOMMENDATIONS-->
    
    <s:else>
   
    <tr>
      <td colspan="3"> 
       <table border="0" width="100%" cellpadding="2" cellspacing="2"  class="formbg"  >
        <tr>
         <td><b>Award Details</b><s:if test="phaseForwardFlag=='false'">  (Please click on Add Record button to add your Award Details)</s:if></td>
         <td align="right">
         	
         	<s:if test="flag">
         	<a href="#" onclick="callAction();" >
         		Previous Phase Details
         	</a>
         	</s:if>
         </td>
        </tr>
        <tr>
         <td colspan="2">
          <table width="98%" class="formbg" align="center" cellpadding="2" cellspacing="2">
           <tr>
             <td class="formth" width="3%">            			  
             <label  name="trng.srno"  class = "set"  id="trng.srno" ondblclick="callShowDiv(this);"><%=label.get("trng.srno")%></label>          
		    </td>
            <td class="formth" width="20%">
            <label  name="awd.desc"  class = "set"  id="awd.desc" ondblclick="callShowDiv(this);"><%=label.get("awd.desc")%></label>            
            </td>
            <td class="formth" width="15%">            
            <label  name="awd.date"  class = "set"  id="awd.date" ondblclick="callShowDiv(this);"><%=label.get("awd.date")%></label>
            </td>
            <td class="formth" width="20%">            
            <label  name="awd.issue"  class = "set"  id="awd.issue" ondblclick="callShowDiv(this);"><%=label.get("awd.issue")%></label>
            </td>
            <td class="formth" width="10%" align="center">
            <label  name="awd.appr.comments"  class = "set"  id="awd.appr.comments" ondblclick="callShowDiv(this);"><%=label.get("awd.appr.comments")%></label>
            </td>
            <td class="formth" width="5%" align="center">
            <s:submit action="ApprFormAwardDtls_addAwardDetails" cssClass="add" value="   Add Record" /> 
            </td>
            
           </tr>
           <%int j=0; %>
           <s:iterator value="awardList">
            <tr>
	            <td class="td_bottom_border" align="center"><%=++j%><input type="hidden" name="sNo" value="<%=j%>"><input type="hidden" name="awdCode" id="awdCode<%=j %>" value="<s:property value="awdCode" />">
	             <input type="hidden" name="awdCodeComment" id="awdCodeComment<%=j %>" value="<s:property value="awdCodeComment" />">
	            </td>
	           	<td class="td_bottom_border" align="center"><input type="text" name="awdDesc" id="awdDesc<%=j %>" size="20"  value="<s:property value="awdDesc" />" onkeypress="return alphaNumeric();"  onkeydown="textCounter(this,200)"></td>
	           	<td class="td_bottom_border" align="center" nowrap="nowrap">
	           			<input type="text" name="awdDate" id="awdDate<%=j %>" size="10" maxlength="10" value="<s:property value="awdDate" />" onkeypress="return numbersWithHiphen();">
	           		    <img src="../pages/images/recruitment/Date.gif" class="iconImage" height="18" align="absmiddle" width="18" onclick="javascript:NewCal('<%="awdDate"+j%>','DDMMYYYY');">            	
	           	</td>
	           	<td class="td_bottom_border" width="10%" align="center">
	           	<input type="text" name="awdIssAuth" id="awdIssAuth<%=j %>" size="30"  value="<s:property value="awdIssAuth" />" onkeyup="return textCounter(this,95)"  onkeydown="return textCounter(this,95)">
	           	</td>
	           	<td class="td_bottom_border" align="center">
	            		<textarea name="awdComments" id="awdComments<%=j %>" rows="2" cols="30" onkeyup="return textCounter(this,200)" ><s:property value="awdComments"/></textarea>
		        </td>
	           	<td class="td_bottom_border" align="center">
	            	<input type="button" value="  Remove" class="delete" onclick="return remove(document.getElementById('awdCode<%=j %>').value,'<%=j%>')"  >
	            </td>
	                        
            </tr>
           </s:iterator>
           <input type="hidden" id="rows1" value="<%=j %>" />
           
           <%if(j==0){ %>
					<tr>
           					<td colspan="6" align="center"><font color="red">No Data To Display</font></td>
          				</tr>
			<%} %> 
		
           <script>
           
           function callAction(){
					var wind = window.open('','wind','width=800,height=200,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
					document.getElementById('paraFrm').target = "wind";
					document.getElementById('paraFrm').action = "ApprFormAwardDtls_previousPhaseAwardDtls.action";
					document.getElementById('paraFrm').submit();
					document.getElementById('paraFrm').target = "main";
         	 }
           
           function remove(awdCode,hSno){       
           	    
	           	conf = confirm('Do you really want to delete the record?');
	           	
	           	if(conf){
	           	
	           		document.getElementById('delAwdCode').value=awdCode;
	           		document.getElementById('hSno').value = hSno; 
	           		document.getElementById('paraFrm').action='ApprFormAwardDtls_removeAwardDetails.action';
	           		document.getElementById('paraFrm').submit();	           		
	           		return true; 
	           		
	           	}else{
	           	
	           	 return false;
	           	 
	           	}
            
            
           }
           
           /* function chkTraining(chkId,trngId){
             
             if(document.getElementById(chkId).checked){
               document.getElementById(trngId).value='Y';
             }else{
             	document.getElementById(trngId).value='N';
             }
             
            }*/
           </script>      
          </table>
         </td>
        </tr>
       </table>
      </td>
     </tr>
 <tr>     
<s:hidden name="nominateFlag" />
<s:if test="nominateFlag">  
  
    <td colspan="3"> 
       <table width="100%" border="0"  class="formbg" >
        	<tr>
         <td>
          <table border="0" width="100%" cellpadding="2" cellspacing="2"  class="formbg">
          
         <tr>
           <td colspan="4"><b>Award Nomination</b></td>
         </tr>
         <tr>
         <s:hidden name="reasonFlag" />
         <s:if test="nominateFlag">
         	 <td>
         	 <label name="nom.award" class = "set" id="nom.award" ondblclick="callShowDiv(this);"><%=label.get("nom.award") %></label>:
         	 </td>
             <td><s:textfield name="award" id="award" maxlength="50" onkeyup="textCounter(this,50)" /></td>
             <s:if test="reasonFlag">
	             <td>
	             <label name="reason.nom" class = "set" id="reason.nom" ondblclick="callShowDiv(this);"><%=label.get("reason.nom") %></label>:
	             </td>
	          	 <td>
	           		<textarea name="reason"  id="reason" rows="2" cols="40" onkeyup="textCounter(this,200)"  ></textarea> 
	             </td>
             </s:if> 
             <s:else>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
             </s:else>
             
         </s:if> 
          
         </tr>
         
         <script>
          function chkAdd(){
           
           if(trim(document.getElementById('award').value)==""){
            	alert('Please enter nominate the award');
            	document.getElementById('award').value = trim(document.getElementById('award').value);
            	document.getElementById('award').focus();
            	return false;
           }/*if(document.getElementById('reason').value==""){
            	alert('Please enter reason for nomination.');
            	document.getElementById('reason').focus();
            	return false;
           } */
           
          }
         </script>
         
         
         <tr>
          <td colspan="4" align="center"><s:submit action="ApprFormAwardDtls_addNomination" cssClass="add" value="    Add " onclick="return chkAdd();"   /></td>
         </tr>
         
         <tr>
         <td colspan="4">
          <table width="98%" class="formbg" align="center" cellpadding="2" cellspacing="2">
          <tr>
            <td class="formth" width="3%">
            <label  name="trng.srno"  class = "set"  id="trng.srno" ondblclick="callShowDiv(this);"><%=label.get("trng.srno")%></label>         
            </td>
            <td class="formth" width="45%">
             <label  name="nom.award"  class = "set"  id="nom.award" ondblclick="callShowDiv(this);"><%=label.get("nom.award")%></label>
            </td>
            <s:if test="reasonFlag">
	            <td class="formth" width="49%">
	             <label  name="reason.nom"  class = "set"  id="reason.nom" ondblclick="callShowDiv(this);"><%=label.get("reason.nom")%></label>
	            </td>
            </s:if>
            <td class="formth" width="3%" align="center">
            	<s:submit action="ApprFormAwardDtls_removeNomination" cssClass="delete" value="  Remove" onclick="return validate()" />
            </td>
           </tr>
           <%int k=0; %>
           <s:iterator value="nomList">
            <tr>
            	<td class="td_bottom_border" align="center">
            	<%=++k %>
            	<input type="hidden" name="awdId" value="<s:property value="awdId" />">
            		<input type="hidden" name="awdSno" value="<%=k %>" >
            		<input type="hidden" name="removeFlag" id="removeFlag<%=k %>" value="N">
            	</td>
            		<td class="td_bottom_border"><input type="text" name="hAward" id="hAward<%=k %>" size="45" value="<s:property value="award" />"  onkeyup="textCounter(this,50)" ></td>
            	<s:if test="reasonFlag">
	            	<td class="td_bottom_border" >
	            			<input type="text" name="hReason" id="hReason<%=k %>" size="49" value="<s:property value="reason" />"  onkeyup="textCounter(this,200)" >
	            	</td>
            	</s:if>
            	<td class="td_bottom_border" align="center">
            		<input type="checkbox" name="chkRemove" id="chkRemove<%=k %>"  onclick="callChkRemove('chkRemove<%=k %>','removeFlag<%=k %>');">
            	</td>
            </tr>
           </s:iterator>
           <input type="hidden" id="rows" value="<%=k %>">
           <%if(k==0){ %>
	           <s:if test="reasonFlag">           
	            <tr>
	             <td colspan="4" align="center"><font color="red">No Data To Display</font></td>
	            </tr>           
			   </s:if>
			   <s:else>
            <tr>
             <td colspan="3" align="center"><font color="red">No Data To Display</font></td>
            </tr>
		   </s:else>
		   <%} %>
           </table>
          </td>
          </table>
       </td>
      </tr>
      </table>
      </td>
</s:if>
     <script>
         
	         function textCounter(field,  maxlimit) {
				
				if (field.value.length > maxlimit){				
					field.value = field.value.substring(0, maxlimit);
					field.focus;
					return false;				
				}
				
				return true;
				
			}
			
           function callChkRemove(chkId,id){
                     
            if(document.getElementById(chkId).checked){
             	document.getElementById(id).value='Y';
            }else{
             	document.getElementById(id).value='N';
            }
            
           }
           
          function validate(){
          
           count = document.getElementById('rows').value;
           temp=0;
           
           for(i=1;i<=count;i++){
             if(document.getElementById('chkRemove'+i).checked){
              temp++;
             }
           }
           
           if(temp==0){
           
            alert('Please select a record to delete.');
            return false;
            
           }else{
           
             	conf = confirm('Do you really want to delete?');
             	if(conf){
             	
             	 return true;
             	 
             	}else{
             	
             	  for(i=1;i<=count;i++){
             		 document.getElementById('chkRemove'+i).checked=false;               			
              		 }
            	   }
             	 return false;
             	 
            	}
            
           }
            
           
           
           
         </script>
   </tr>
   
   
    </s:else>
    
    <!-- Proposed Score Start -->
	<s:hidden name="previewFlag" id="previewFlag" />
	<tr>
		<td colspan="3">
			<div id='div_previewApprIdFinalRating'
				style='position: absolute; z-index: 3; width: 350px; height: 120px; display: none; border: 2px solid; top: 200px; left: 200px; padding: 10px;'
				class="formbg">
				<table width="100%">
				<tr>
					<td width="93%"  colspan="5"  style="cursor: move"
						onmouseout="Drag.end();"
						onmouseover="Drag.init(document.getElementById('div_previewApprIdFinalRating'), null, 0, 350, 0, 600);">
					<b>
					<label id="moduleName" style="cursor: move" />Projected Appraisal Score 
					</b></td>
					 
				</tr>
				
					<tr>
						<td width="10%" class="formth">Sr No</td>
						<td width="20%" class="formth">Type</td>
						<td width="25%" class="formth">Weightage</td>
						<td width="25%" class="formth">Score</td>
						<td width="20%" class="formth">Status</td>
					</tr>
					<tr>
						<td width="10%">1</td>
						<td width="20%">Appraisal</td>
						<td width="25%"><s:hidden name="apprWeightage"/><s:property value="apprWeightage" /></td>
						<td width="25%"><s:hidden name="apprScore"/><s:property value="apprScore" /></td>
						<td width="20%" >Pending</td>
					</tr>
					<s:if test="goalMapFlag">
					<tr>
						<td width="10%">2</td>
						<td width="20%">Goal</td>
						<td width="25%"><s:hidden name="goalWeightage"/><s:property value="goalWeightage" /></td>
						<td width="25%"><s:hidden name="goalScore"/><s:property value="goalScore" /></td>
						<s:if test="goalFinalizeFlag">
					
     					<td width="20%" >Not Finalize</td>
    				
						</s:if>
						<s:else><td width="20%" >Finalized</td></s:else>
					</tr>
					
					</s:if>
					<s:if test="compMapFlag">
					<tr>
						<td width="10%">3</td>
						<td width="20%">Competancy</td>
						<td width="25%"><s:hidden name="compWeightage"/><s:property value="compWeightage" /></td>
						<td width="25%"><s:hidden name="compScore"/><s:property value="compScore" /></td>
						<s:if test="compFinalizeFlag">
					
     					<td width="20%" >Not Finalize</td>
    				
					</s:if>
					<s:else><td width="20%" >Finalized</td></s:else>
					</tr>
					
					</s:if>
					<tr>
						<td width="10%">&nbsp;</td>
						<td width="20%">&nbsp;</td>
						<td width="25%">Total</td>
						<td width="25%"><s:hidden name="totalScore"/><s:property value="totalScore" /></td>
						<td width="20%" >&nbsp;</td>
					</tr><tr>
					<td colspan="5" align="center"><input type="button" class="token" name="ok"
						value=" OK " onclick="finishAppraisal();" /> <input
						type="button" class="token" name="cancel" value=" Cancel "
						 onclick="hideApprRating();"/> </td>
				</tr>
				</table>
		</div>
		</td>
	</tr>
	<!-- Proposed Score End -->
 <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2">
         <tr>
            <td width="78%">
            <jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
          			
		</td>
            
          </tr>
        </table>
          </td>
    </tr>
 
 
 
 </table>

  <label></label></s:form>
<script type="text/javascript">
		//Proposed Score ----- Start ----
					previewOnload();
					function previewOnload()
					{
						if(document.getElementById('previewFlag').value=="true")
						{
							document.getElementById('div_previewApprIdFinalRating').style.display = '';
						}
					}
					
					function hideApprRating()
					{
					
					try{
						document.getElementById("paraFrm").action="ApprFormSection_updateCancelAppraisal.action";
							document.getElementById("paraFrm").submit();
							document.getElementById('div_previewApprIdFinalRating').style.display = 'none';
							document.getElementById('previewFlag').value="false";}
							catch(e){
							alert(e);
							}
					}
					function finishAppraisal()
					{
							document.getElementById("paraFrm").action="ApprFormSection_updateAppraisal.action";
							document.getElementById("paraFrm").submit();
							document.getElementById('div_previewApprIdFinalRating').style.display = 'none';
					}
					//Proposed Score ----- End ---- 

function textCounter(field,  maxlimit) {
		
	          // if too long...trim it!
		if (field.value.length > maxlimit){		
			//alert('Field value should not exceed '+maxlimit+' chars.');	 
			field.value = field.value.substring(0, maxlimit);
			field.focus;
			return false;
			
		}
		
		return true;
		
	}

	
	function saveFun(){
						
				len=eval(document.getElementById('rows1').value);
				if(document.getElementById('paraFrm_isSelf').value=='true'){ //SELF PHASE
					
					if(len==0){
			  			alert('No records available to save.');
			  			return false;
			 		}
					
					for(i=1;i<=len;i++){
						 
						 if(trim(document.getElementById('awdDesc'+i).value)==''){
							  alert('Please enter award description.');
							  document.getElementById('awdDesc'+i).value='';
							  document.getElementById('awdDesc'+i).focus();
							  return false; 
							  
						}if(document.getElementById('awdDate'+i).value==""){
							  alert('Please enter award date.');
							  document.getElementById('awdDate'+i).value='';
							  document.getElementById('awdDate'+i).focus();
							  return false;
							  
						}if(!validateDate('awdDate'+i,'awd.date')){
							//document.getElementById('awdDate'+i).value='';
							document.getElementById('awdDate'+i).select();
							document.getElementById('awdDate'+i).focus();
					  	    return false;
					  
					  	}if(!dateCheckLessThanToday('awdDate'+i,'awd.date')){
						  	  document.getElementById('awdDate'+i).focus();
						   	  return false; 
					   	      
					  	}
					}
				}else{//OTHER PHASES
										
				 	if(len==0){
			  			alert('No records available to save.');
			  			return false;
			 		}
					
					 for(i=1;i<=len;i++){
						 
						 if(trim(document.getElementById('awdDesc'+i).value)==''){
							  alert('Please enter award description.');
							  document.getElementById('awdDesc'+i).value='';
							  document.getElementById('awdDesc'+i).focus();
							  return false; 
							  
						}if(document.getElementById('awdDate'+i).value==""){
							  alert('Please enter award date.');
							  document.getElementById('awdDate'+i).value='';
							  document.getElementById('awdDate'+i).focus();
							  return false; 
							  
						}if(!validateDate('awdDate'+i,'awd.date')){
							//document.getElementById('awdDate'+i).value='';
							document.getElementById('awdDate'+i).select();
							document.getElementById('awdDate'+i).focus();
					  	    return false;
					  
					  	}if(!dateCheckLessThanToday('awdDate'+i,'awd.date')){
						  	  document.getElementById('awdDate'+i).focus();
						   	  return false; 
					   	      
					  	}
					}
					
					if(document.getElementById('paraFrm_nominateFlag').value=='true'){
					   len1=document.getElementById("rows").value
						for(i=1;i<=len1;i++){
						 	
						 	if(trim(document.getElementById('hAward'+i).value)==""){
						 	 alert('Please enter nomination for award.');
						 	 document.getElementById('hAward'+i).value="";
						 	 document.getElementById('hAward'+i).focus();
						 	 return false;
						 	}
						 	
						}
				}
	}
				document.getElementById("paraFrm").action="ApprFormAwardDtls_save.action";
				document.getElementById("paraFrm").submit();
		
}

function saveandnextFun(){
		
		len=eval(document.getElementById('rows1').value);
				if(document.getElementById('paraFrm_isSelf').value=='true'){ 
				
					for(i=1;i<=len;i++){
						 
						 if(trim(document.getElementById('awdDesc'+i).value)==''){
							  alert('Please enter award description.');
							  document.getElementById('awdDesc'+i).value='';
							  document.getElementById('awdDesc'+i).focus();
							  return false; 
							  
						}if(document.getElementById('awdDate'+i).value==""){
							  alert('Please enter award date.');
							  document.getElementById('awdDate'+i).value='';
							  document.getElementById('awdDate'+i).focus();
							  return false; 
							  
						}if(!validateDate('awdDate'+i,'awd.date')){
							//document.getElementById('awdDate'+i).value='';
							document.getElementById('awdDate'+i).select();
							document.getElementById('awdDate'+i).focus();
					  	    return false;
					  
					  	}if(!dateCheckLessThanToday('awdDate'+i,'awd.date')){
						  	  document.getElementById('awdDate'+i).focus();
						   	  return false; 
					   	      
					  	}
					}
				}else{
					  
					  for(i=1;i<=len;i++){
						 
						 if(trim(document.getElementById('awdDesc'+i).value)==''){
							  alert('Please enter award description.');
							  document.getElementById('awdDesc'+i).value='';
							  document.getElementById('awdDesc'+i).focus();
							  return false; 
							  
						}if(document.getElementById('awdDate'+i).value==""){
							  alert('Please enter award date.');
							  document.getElementById('awdDate'+i).value='';
							  document.getElementById('awdDate'+i).focus();
							  return false; 
							  
						}if(!validateDate('awdDate'+i,'awd.date')){
							//document.getElementById('awdDate'+i).value='';
							document.getElementById('awdDate'+i).select();
							document.getElementById('awdDate'+i).focus();
					  	    return false;
					  
					  	}if(!dateCheckLessThanToday('awdDate'+i,'awd.date')){
						  	  document.getElementById('awdDate'+i).focus();
						   	  return false; 
					   	      
					  	}
					}
					
					if(document.getElementById('paraFrm_nominateFlag').value=='true'){
					   len1=document.getElementById("rows").value
						for(i=1;i<=len1;i++){
						 	
						 	if(trim(document.getElementById('hAward'+i).value)==""){
						 	 alert('Please enter nomination for award.');
						 	 document.getElementById('hAward'+i).value="";
						 	 document.getElementById('hAward'+i).focus();
						 	 return false;
						 	}
						 	
						}
						}
				     
				} 
		
		document.getElementById("paraFrm").action="ApprFormAwardDtls_saveNext.action";
		document.getElementById("paraFrm").submit();
}

function saveandpreviousFun(){
		
		
		len=eval(document.getElementById('rows1').value);
				if(document.getElementById('paraFrm_isSelf').value=='true'){ 
				
					for(i=1;i<=len;i++){
						 
						 if(trim(document.getElementById('awdDesc'+i).value)==''){
							  alert('Please enter award description.');
							  document.getElementById('awdDesc'+i).value='';
							  document.getElementById('awdDesc'+i).focus();
							  return false; 
							  
						}if(document.getElementById('awdDate'+i).value==""){
							  alert('Please enter award date.');
							  document.getElementById('awdDate'+i).value='';
							  document.getElementById('awdDate'+i).focus();
							  return false; 
							  
						}if(!validateDate('awdDate'+i,'awd.date')){
							//document.getElementById('awdDate'+i).value='';
							document.getElementById('awdDate'+i).select();
							document.getElementById('awdDate'+i).focus();
					  	    return false;
					  
					  	}if(!dateCheckLessThanToday('awdDate'+i,'awd.date')){
						  	  document.getElementById('awdDate'+i).focus();
						   	  return false; 
					   	      
					  	}
					}
				}else{
					  
					  for(i=1;i<=len;i++){
						 
						 if(trim(document.getElementById('awdDesc'+i).value)==''){
							  alert('Please enter award description.');
							  document.getElementById('awdDesc'+i).value='';
							  document.getElementById('awdDesc'+i).focus();
							  return false; 
							  
						}if(document.getElementById('awdDate'+i).value==""){
							  alert('Please enter award date.');
							  document.getElementById('awdDate'+i).value='';
							  document.getElementById('awdDate'+i).focus();
							  return false; 
							  
						}if(!validateDate('awdDate'+i,'awd.date')){
							//document.getElementById('awdDate'+i).value='';
							document.getElementById('awdDate'+i).select();
							document.getElementById('awdDate'+i).focus();
					  	    return false;
					  
					  	}if(!dateCheckLessThanToday('awdDate'+i,'awd.date')){
						  	  document.getElementById('awdDate'+i).focus();
						   	  return false; 
					   	      
					  	}
					}
					  if(document.getElementById('paraFrm_nominateFlag').value=='true'){
				      len1=document.getElementById("rows").value
						for(i=1;i<=len1;i++){
						 	
						 	if(trim(document.getElementById('hAward'+i).value)==""){
						 	 alert('Please enter nomination for award.');
						 	 document.getElementById('hAward'+i).value="";
						 	 document.getElementById('hAward'+i).focus();
						 	 return false;
						 	}
						 	
						}
						}
				}
		
		document.getElementById("paraFrm").action="ApprFormAwardDtls_savePrevious.action";
		document.getElementById("paraFrm").submit();
			
}

function nextFun(){
			document.getElementById("paraFrm").action="ApprFormDiscpMeasures_input.action";
			document.getElementById("paraFrm").submit();
		
}

function previousFun(){
		
		document.getElementById("paraFrm").action="ApprFormTrainingDtls_previous.action";
		document.getElementById("paraFrm").submit();
		
}

function previewFun(){
				document.getElementById('paraFrm').target = "main";
				document.getElementById("paraFrm").action="ApprFormSection_previewAppraisal.action";
				document.getElementById("paraFrm").submit(); 
				
				/*var wind = window.open('','wind','width=900,height=400,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
				document.getElementById('paraFrm').target = "wind";
				document.getElementById('paraFrm').action = "ApprFormSection_previewAppraisal.action";
				document.getElementById('paraFrm').submit();
				document.getElementById('paraFrm').target = "main";*/
				
				//window.open('ApprFormSection_previewAppraisal.action',500,200);
	}

function finishFun(){
			
			len=eval(document.getElementById('rows1').value);
				if(document.getElementById('paraFrm_isSelf').value=='true'){ 
				
					for(i=1;i<=len;i++){
						 
						 if(trim(document.getElementById('awdDesc'+i).value)==''){
							  alert('Please enter award description.');
							  document.getElementById('awdDesc'+i).value='';
							  document.getElementById('awdDesc'+i).focus();
							  return false; 
							  
						}if(document.getElementById('awdDate'+i).value==""){
							  alert('Please enter award date.');
							  document.getElementById('awdDate'+i).value='';
							  document.getElementById('awdDate'+i).focus();
							  return false; 
							  
						}if(!dateCheckLessThanToday('awdDate'+i,'awd.date')){
						  	  document.getElementById('awdDate'+i).focus();
						   	  return false; 
					   	      
					  	}
					}
				}
			
			var conf;
			if(document.getElementById('paraFrm_isSelf').value=="true"){//SELF
				 
				 conf = confirm('Do you want to Finish your '+document.getElementById('appraisal.form.head').innerHTML+' ?\nClick OK to Finish.');
				 				 
			}else{//OTHER PHASES
			     
			     conf = confirm('Do you want to Finish this '+document.getElementById('appraisal.form.head').innerHTML+' ?\nClick Ok to Finish.');
				 
			}
			
			if(conf){			
					document.getElementById("paraFrm").action="ApprFormCareerProgression_forwardAppraisal.action";
					document.getElementById("paraFrm").submit();			
				 }
			
			
			return false;
}

</script>
