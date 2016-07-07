<%@include file="/pages/common/labelManagement.jsp" %>
<%@ taglib prefix="s" uri="/struts-tags"%>


<script type = "text/javascript" src = "../pages/common/datetimepicker.js"></script>

<%

 Object data[][] = (Object[][])request.getAttribute("data"); //PREVIOUS PHASES DATA

%>

<s:form action="ApprFormTrainingDtls" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="source" id="source"/>	
	<s:hidden name="phaseId" theme="simple" />
	<s:hidden name="phaseSequence" theme="simple"  />
	<input type="hidden" name="delTrngCode" id="delTrngCode"/>
	<input type="hidden" name="hSno" id="hSno"/>
	<s:hidden name="removeTrngCode" theme="simple"  />
	<s:hidden name="isSelf" theme="simple"  />
	<s:hidden name="sourceFormType" id="sourceFormType"/>
	
	  <table width="100%" border="0" align="right" cellpadding="2" cellspacing="1" class="formbg"  >
    
    <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head"><label name="appraisal.form.head" class = "set"  id="appraisal.form.head" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.head")%></label></strong></td>
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
        </table>
          </td>
    </tr>
    <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
          <tr>
            <td><s:hidden name="templateCode"/>
              <s:hidden name="apprValidTillDate"/> 
             <s:hidden name="detailFLag"/>
              <s:hidden name="phaseForwardFlag"/>
               <s:hidden name="sectionCode"/>
                <s:hidden name="sectionList"/>
                <s:hidden name="nextExist"/>
                <s:hidden name="previousExist"/>
               
            <s:hidden theme="simple" theme="simple"  name="mode" />
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
                           
            </table></td>
          </tr>
          
      </table></td>
    </tr>
     <input type="hidden" value="<s:property value="phaseSequence" />">
    
<!-- FOR SELF PHASE ONLY THE appraisee could add training attended details -->
   
    <s:if test="isSelf"> 
     <tr>
      <td colspan="3">
       <table border="0" width="100%" cellpadding="2" cellspacing="2"  class="formbg"  >
        <tr>
         <td><b>Training Details</b><s:if test="phaseForwardFlag=='false'">  (Please click on Add Record button to add your Training Details)</s:if></td>
        </tr>
        <tr>
         <td colspan="2">
          <table width="98%" class="formbg" cellpadding="2" cellspacing="2">
           <tr>
            <td class="formth" width="3%">
            <label  name="trng.srno"  class = "set"  id="trng.srno" ondblclick="callShowDiv(this);"><%=label.get("trng.srno")%></label>            
            </td>
            <td class="formth" width="20%">
            <label  name="trng.desc"  class = "set"  id="trng.desc" ondblclick="callShowDiv(this);"><%=label.get("trng.desc")%></label>
            </td>
            <td class="formth" width="5%">
            <label  name="trng.duration"  class = "set"  id="trng.duration" ondblclick="callShowDiv(this);"><%=label.get("trng.duration")%></label>
            </td>
            <td class="formth" width="15%">
            <label  name="trng.from"  class = "set"  id="trng.from" ondblclick="callShowDiv(this);"><%=label.get("trng.from")%></label>            
            </td>
            <td class="formth" width="15%">
            <label  name="trng.to"  class = "set"  id="trng.to" ondblclick="callShowDiv(this);"><%=label.get("trng.to")%></label>           
            </td>
            <td class="formth" width="15%">
            <label  name="trng.sponsor"  class = "set"  id="trng.sponsor" ondblclick="callShowDiv(this);"><%=label.get("trng.sponsor")%></label>
            </td>
            <s:if test="phaseForwardFlag=='false'">        
             <td class="formth" width="8%" align="center"><s:submit theme="simple" action="ApprFormTrainingDtls_addTrainingDetails" cssClass="add" value="   Add Record" /> </td>
            </s:if>
           </tr>
           <%int i=0; %>
           <!--      FOR FILLING APPRAISAL FORM      -->
           <s:if test="phaseForwardFlag=='false'" >
           <s:iterator value="trainingList" > 
            <tr>
            	<td class="td_bottom_border" align="center"><%=++i%><input type="hidden" name="sNo" value="<%=i%>"><input type="hidden" name="trngCode" id="trngCode<%=i %>" value="<s:property value="trngCode" />"></td>
            	<td class="td_bottom_border"><input type="text" name="trngDesc" id="trngDesc<%=i %>"  value="<s:property value="trngDesc" />" size="25" maxlength="500" ></td>
            	<td class="td_bottom_border" align="center"><input type="text" name="trngDuration" id="trngDuration<%=i %>" size="1" maxlength="3" value="<s:property value="trngDuration" />" onkeypress="return numbersOnly()"></td>
            	<td class="td_bottom_border" width="10%" align="center">
            	<input type="text" name="trngFrom" id="trngFrom<%=i %>" size="10" maxlength="10" value="<s:property value="trngFrom" />"  onkeypress="return numbersWithHiphen();">
	            <img src="../pages/images/recruitment/Date.gif"  class="iconImage" height="18" align="absmiddle" width="18" onclick="javascript:NewCal('<%="trngFrom"+i%>','DDMMYYYY');">
            	</td> 
            	<td class="td_bottom_border" align="center">
            		<input type="text" name="trngTo" id="trngTo<%=i %>" size="10" maxlength="10" value="<s:property value="trngTo" />" onkeypress="return numbersWithHiphen();">
            		<img src="../pages/images/recruitment/Date.gif" class="iconImage" height="18" align="absmiddle" width="18" onclick="javascript:NewCal('<%="trngTo"+i%>','DDMMYYYY');">
            	</td>
            	<td class="td_bottom_border"><input type="text" name="trngSponsor" id="trngSponsor<%=i %>" value="<s:property value="trngSponsor" />" size=""  maxlength="500"></td>
	            	<td class="td_bottom_border" align="center">
		            	<input type="button" value="  Remove" class="delete" onclick="return remove(document.getElementById('trngCode<%=i %>').value,'<%=i%>')"  >
		            </td>
            </tr>
           </s:iterator>   
           </s:if>
           <!-- FOR VIEWING APPRAISAL FORM -->
           <s:else>
            <s:iterator value="trainingList"> 
            <tr> 
            	<td class="td_bottom_border" align="center"><%=++i%><input type="hidden" name="sNo" value="<%=i%>"><input type="hidden" name="trngCode" id="trngCode<%=i %>" value="<s:property value="trngCode" />"></td>
            	<td class="td_bottom_border"><s:property value="trngDesc" /></td>
            	<td class="td_bottom_border" align="center"><s:property value="trngDuration" /></td>
            	<td class="td_bottom_border" width="10%" align="center">
            		<s:property value="trngFrom" />
            	</td>
            	<td class="td_bottom_border" align="center">
            		<s:property value="trngTo" />
            	</td>
            	<td class="td_bottom_border">
            		<s:property value="trngSponsor" />
            	</td>            	
            </tr>
           </s:iterator>            
           </s:else>
            <input type="hidden" id="rows" value="<%=i %>">
            <%if(i==0){ %>
	            <tr>
	             <td colspan="6" align="center"><font color="red">No Data To Display</font></td>
	            </tr>
		   <%} %>
           <script>
           
           function remove(trngCode,hSno){       
           	           
	           	conf = confirm('Do you really want to delete the record?');
	           	if(conf){
	           	
	           		document.getElementById('delTrngCode').value=trngCode;
	           		document.getElementById('hSno').value = hSno; 
	           		document.getElementById('paraFrm').action='ApprFormTrainingDtls_removeTrainingDetails.action';
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
        
       <table border="0" width="100%" cellpadding="2" cellspacing="2"  class="formbg" >
        <tr>
         <td><b>Training Details</b></td>
         <td align="right">
         	<!--<script type="text/javascript">
         	 
	         	 function callAction(){
	         	 var wind = window.open('','wind','width=800,height=200,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
						document.getElementById('paraFrm').target = "wind";
						document.getElementById('paraFrm').action = "ApprFormTrainingDtls_previousPhaseTrainingAttendedDtls.action";
						document.getElementById('paraFrm').submit();
						document.getElementById('paraFrm').target = "main";         	 
	         	 }
         	 
         	</script>
         	
         	<s:if test="flag">
	         	<a href="#" onclick="callAction();" >
	         		Previous Phase Details
	         	</a>
         	</s:if><s:else>
         		
         	</s:else>-->
         </td>
        </tr>
        <tr>
         <td colspan="2"  align="center">
          <table border="0" width="98%" class="formbg" cellpadding="2" cellspacing="2">
          <tr>
            <td class="formth" width="3%">
            <label  name="trng.srno"  class = "set"  id="trng.srno" ondblclick="callShowDiv(this);"><%=label.get("trng.srno")%></label>            
            </td>
            <td class="formth" width="20%">
            <label  name="trng.desc"  class = "set"  id="trng.desc" ondblclick="callShowDiv(this);"><%=label.get("trng.desc")%></label>
            </td>
            <td class="formth" width="5%">
            <label  name="trng.duration"  class = "set"  id="trng.duration" ondblclick="callShowDiv(this);"><%=label.get("trng.duration")%></label>
            </td>
            <td class="formth" width="15%">
            <label  name="trng.from"  class = "set"  id="trng.from" ondblclick="callShowDiv(this);"><%=label.get("trng.from")%></label>            
            </td>
            <td class="formth" width="15%">
            <label  name="trng.to"  class = "set"  id="trng.to" ondblclick="callShowDiv(this);"><%=label.get("trng.to")%></label>           
            </td>
            <td class="formth" width="15%">
            <label  name="trng.sponsor"  class = "set"  id="trng.sponsor" ondblclick="callShowDiv(this);"><%=label.get("trng.sponsor")%></label>
            </td>
            <s:if test="commentFlag">
             <td class="formth" width="10%">
             <label  name="trng.appr.comments"  class = "set"  id="trng.appr.comments" ondblclick="callShowDiv(this);"><%=label.get("trng.appr.comments")%></label>
             </td>
            </s:if>  
           </tr> 
           <%int k=0; %>
           <s:iterator value="trainingList"> 
            <tr>
            	<td class="td_bottom_border" align="center"><%=++k%><input type="hidden" name="sNo" value="<%=k%>"><input type="hidden" name="trngCode" value="<s:property value="trngCode" />"></td>
            	<td class="td_bottom_border"><s:property value="trngDesc" />&nbsp;</td>
            	<td class="td_bottom_border" align="center"><s:property value="trngDuration" />&nbsp;</td>
            	<td class="td_bottom_border" width="10%" align="center"><s:property value="trngFrom" />&nbsp;</td> 
            	<td class="td_bottom_border" align="center"><s:property value="trngTo" />&nbsp;</td>
            	<td class="td_bottom_border"><s:property value="trngSponsor" />&nbsp;</td>            	
            	 <s:if test="commentFlag">
            		<td class="td_bottom_border"><textarea name="trngComments" rows="2" cols="30" onkeyup="return textCounter(this,500)" onkeydown="return textCounter(this,500)"><s:property value="trngComments" /></textarea>
            		</td>
            	 </s:if>
            </tr>
           </s:iterator> 
           <input type="hidden" id="rows" value="<%=k %>">
           <%if(k==0){ %>
	           <s:if test="commentFlag">           
	            <tr>
	             <td class="td_bottom_border" colspan="7" align="center"><font color="red">No Data To Display</font></td>
	            </tr>           
			   </s:if>
			   <s:else>
            <tr>
             <td class="td_bottom_border" colspan="6" align="center"><font color="red">No Data To Display</font></td>
            </tr>
		   </s:else>
		   <%} %>
		   
		                   
          </table>
         </td>
        </tr>
       </table>
      </td>
     </tr>
    <script>
     function callAction1(){
          var wind = window.open('','wind','width=800,height=200,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
					document.getElementById('paraFrm').target = "wind";
					document.getElementById('paraFrm').action = "ApprFormTrainingDtls_previousPhaseTrainingRecommDtls.action";
					document.getElementById('paraFrm').submit();
					document.getElementById('paraFrm').target = "main";         	 
         	
     }
    </script>
       <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg" >
          <tr >
            		 <td ><b>Training Recommendations</b></td>
            		 <td align="right" >
            		 	
           		 		<s:if test="flag">
            		 		<a href="#" onclick="callAction1();" >
         						Previous Phase Details
         					</a> 
        				</s:if>
            		 </td>
            		</tr>
          <tr> 
            <td colspan="2">
            <table width="98%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">            		
            			<tr>
            			 <td width="5%" class="formth">
            			  <label  name="trng.srno"  class = "set"  id="trng.srno" ondblclick="callShowDiv(this);"><%=label.get("trng.srno")%></label>           
            			 </td>
            			 <td width="30%" class="formth"> 
            			 <label  name="trng.ques.descr"  class = "set"  id="trng.ques.descr" ondblclick="callShowDiv(this);"><%=label.get("trng.ques.descr")%></label>
            			 </td>
            			 <s:if test="commentFlag">
            			 	<td width="63%" class="formth">
            			 	<label  name="trng.ques.comments"  class = "set"  id="trng.ques.comments" ondblclick="callShowDiv(this);"><%=label.get("trng.ques.comments")%></label>
            			 	</td>
            			 </s:if>            			             			 
            			</tr>
            			<%
						int j = 1 , c=0;
						%>
							<s:iterator value="questionList">
								<tr>
									<td width="5%" align="center" class="td_bottom_border"><%=j%><INPUT type="hidden" name="questionCode" value="<s:property value="questionCode" />" /><input type="hidden" name="sectionId" value="<s:property value="sectionId" />"> </td>
									<td width="45%" align="left" class="td_bottom_border"><s:property value="questionDesc"  /><input type="hidden" name="questionDesc" value="<s:property value="questionDesc"  />"></td>
									
									<s:if test="commentFlag">
									<td width="50%" align="left"  class="td_bottom_border">
												<textarea name="quesComment" rows="2" cols="45" onkeyup="textCounter(this,<s:property value="charLimit" />)" onkeydown="textCounter(this,<s:property value="charLimit" />)" ><s:property value="quesComment" /></textarea>										
												(<s:property value="charLimit" /><input type="hidden" name="charLimit" value="<s:property value="charLimit" />"  >)										
											<input type="hidden" name="commentFlag" value="<s:property value="commentFlag" />">
									</td>
									</s:if>
								 <%j++; c++;%>
								 </tr>
							</s:iterator>
							<input type="hidden" id="rows1" value="<%=c %>">
							<%if(c==0){ %>
							<s:if test="commentFlag">
								<tr>
	             					<td class="td_bottom_border" colspan="3" align="center"><font color="red">No Data To Display</font></td>
	            				</tr>
							</s:if>
							<s:else>
								<tr>
	             					<td class="td_bottom_border" colspan="2" align="center"><font color="red">No Data To Display</font></td>
	            				</tr>
							</s:else>
							<%} %>    
      </table></td>
    </tr>
    </table></td></tr>
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
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="1">
         <tr>
            <td width="78%">
            <jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
            </td>
            </tr>
        </table>
          </td>
    </tr>
 
 
 </table>

	
	
	</s:form>


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
						document.getElementById('div_previewApprIdFinalRating').style.display = 'none';
						document.getElementById('previewFlag').value="false";
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

			if(document.getElementById('paraFrm_isSelf').value=='true'){
						 
			 len = document.getElementById('rows').value;
			 if(len==0){
			  alert('No records available to save.');
			  return false;
			 }
			 
			 for(i=1;i<=len;i++){
					  
					  if(trim(document.getElementById('trngDesc'+i).value)==''){
					  	   document.getElementById('trngDesc'+i).value='';
						   alert('Please add training description');
						   document.getElementById('trngDesc'+i).focus();
						   
						   return false;
					  }if(document.getElementById('trngDuration'+i).value==''){					  
						   alert('Please add training duration');
						   document.getElementById('trngDuration'+i).focus();
						   return false;
						   
					  }if(eval(document.getElementById('trngDuration'+i).value)==0){
						   alert('Invalid training duration entered.');
						   document.getElementById('trngDuration'+i).value="";
						   document.getElementById('trngDuration'+i).focus();
						   return false;
						   
					  }if(document.getElementById('trngFrom'+i).value==''){					  		
						   alert('Please enter training from date.');
						   document.getElementById('trngFrom'+i).focus();
						   return false;
						   
					  }if(!validateDate('trngFrom'+i,'trng.from')){
					  	   return false;
					  
					  }if(!dateCheckLessThanToday('trngFrom'+i,'trng.from')){
					  	  document.getElementById('trngFrom'+i).focus();
					   	  return false; 
					   	  
					  }if(document.getElementById('trngTo'+i).value==''){					  	   
						   alert('Please enter training to date.');
						   document.getElementById('trngTo'+i).focus();
						   return false;
						   
					  }if(!validateDate('trngTo'+i,'trng.to')){
					  	   return false;
					  
					  }if(!dateCheckLessThanToday('trngTo'+i,'trng.to')){
					  	  document.getElementById('trngTo'+i).focus();
					   	  return false; 
					   	  
					  }if(!dateDifferenceEqual(document.getElementById('trngFrom'+i).value, document.getElementById('trngTo'+i).value, 'trngFrom'+i, 'trng.from', 'trng.to')){
					      document.getElementById('trngFrom'+i).focus();
					      return false;
					      
					  } 
			  
			 }//for ends
			 
			 
			}else{//Other phases			
				//len1 = document.getElementById('rows').value;//Training details
				
				/*
				len2 = document.getElementById('rows1').value;//Training recommendations
				
				if(len2==0){
				  alert('No records available to save.');
				  return false;
			 	}
			 	*/
			 	
				
			}
			
			document.getElementById("paraFrm").action="ApprFormTrainingDtls_save.action";
			document.getElementById("paraFrm").submit();
			
}

function saveandnextFun(){
			
			if(document.getElementById('paraFrm_isSelf').value=='true'){
			 
			 len = document.getElementById('rows').value;
		
			 for(i=1;i<=len;i++){	
					 
					  if(trim(document.getElementById('trngDesc'+i).value)==''){
					  	   document.getElementById('trngDesc'+i).value='';
						   alert('Please add training description');
						   document.getElementById('trngDesc'+i).focus();
						   
						   return false;
					  }if(document.getElementById('trngDuration'+i).value==''){					  
						   alert('Please add training duration');
						   document.getElementById('trngDuration'+i).focus();
						   return false;
						   
					  }if(eval(document.getElementById('trngDuration'+i).value)==0){
						   alert('Invalid training duration entered.');
						   document.getElementById('trngDuration'+i).value="";
						   document.getElementById('trngDuration'+i).focus();
						   return false;
						   
					  }if(document.getElementById('trngFrom'+i).value==''){					  		
						   alert('Please enter training from date.');
						   document.getElementById('trngFrom'+i).focus();
						   return false;
						   
					  }if(!validateDate('trngFrom'+i,'trng.from')){
					  	   return false;
					  
					  }if(!dateCheckLessThanToday('trngFrom'+i,'trng.from')){
					  	  document.getElementById('trngFrom'+i).focus();
					   	  return false; 
					   	  
					  }if(document.getElementById('trngTo'+i).value==''){					  	   
						   alert('Please enter training to date.');
						   document.getElementById('trngTo'+i).focus();
						   return false;
						   
					  }if(!validateDate('trngTo'+i,'trng.to')){
					  	   return false;
					  
					  }if(!dateCheckLessThanToday('trngTo'+i,'trng.to')){
					  	  document.getElementById('trngTo'+i).focus();
					   	  return false; 
					   	  
					  }if(!dateDifferenceEqual(document.getElementById('trngFrom'+i).value, document.getElementById('trngTo'+i).value, 'trngFrom'+i, 'trng.from', 'trng.to')){
					      document.getElementById('trngFrom'+i).focus();
					      return false;
					      
					  } 
			  
			 }//for ends
			 
			 
			}else{//Other phases			
				
				
			}
			
			
			document.getElementById("paraFrm").action="ApprFormTrainingDtls_saveAndNext.action";
			document.getElementById("paraFrm").submit();
		
}

function saveandpreviousFun(){
			
			if(document.getElementById('paraFrm_isSelf').value=='true'){
			 
			 len = document.getElementById('rows').value;
		
			 for(i=1;i<=len;i++){	
					 
					  if(trim(document.getElementById('trngDesc'+i).value)==''){
					  	   document.getElementById('trngDesc'+i).value='';
						   alert('Please add training description');
						   document.getElementById('trngDesc'+i).focus();
						   
						   return false;
					  }if(document.getElementById('trngDuration'+i).value==''){					  
						   alert('Please add training duration');
						   document.getElementById('trngDuration'+i).focus();
						   return false;
						   
					  }if(eval(document.getElementById('trngDuration'+i).value)==0){
						   alert('Invalid training duration entered.');
						   document.getElementById('trngDuration'+i).value="";
						   document.getElementById('trngDuration'+i).focus();
						   return false;
						   
					  }if(document.getElementById('trngFrom'+i).value==''){					  		
						   alert('Please enter training from date.');
						   document.getElementById('trngFrom'+i).focus();
						   return false;
						   
					  }if(!validateDate('trngFrom'+i,'trng.from')){
					  	   return false;
					  
					  }if(!dateCheckLessThanToday('trngFrom'+i,'trng.from')){
					  	  document.getElementById('trngFrom'+i).focus();
					   	  return false; 
					   	  
					  }if(document.getElementById('trngTo'+i).value==''){					  	   
						   alert('Please enter training to date.');
						   document.getElementById('trngTo'+i).focus();
						   return false;
						   
					  }if(!validateDate('trngTo'+i,'trng.to')){
					  	   return false;
					  
					  }if(!dateCheckLessThanToday('trngTo'+i,'trng.to')){
					  	  document.getElementById('trngTo'+i).focus();
					   	  return false; 
					   	  
					  }if(!dateDifferenceEqual(document.getElementById('trngFrom'+i).value, document.getElementById('trngTo'+i).value, 'trngFrom'+i, 'trng.from', 'trng.to')){
					      document.getElementById('trngFrom'+i).focus();
					      return false;
					      
					  } 
			  
			 }//for ends
			 
			 
			}else{//Other phases			
				//len1 = document.getElementById('rows').value;//Training details
				//len2 = document.getElementById('rows1').value;//Training recommendations
				
				//if( len2==0){
				 // alert('No records available to save.');
				  //return false;
			 //	} 
				
			}
			
			document.getElementById("paraFrm").action="ApprFormTrainingDtls_saveAndPrevious.action";
			document.getElementById("paraFrm").submit();
		
}

function nextFun(){
	
			document.getElementById("paraFrm").action="ApprFormAwardDtls_input.action";
			document.getElementById("paraFrm").submit();
		
}

function previousFun(){
			
				document.getElementById("paraFrm").action="ApprFormTrainingDtls_getPrevious.action";
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
			
			if(document.getElementById('paraFrm_isSelf').value=='true'){
			 
			 len = document.getElementById('rows').value;
		
			 for(i=1;i<=len;i++){	
					 
					  if(trim(document.getElementById('trngDesc'+i).value)==''){
					  	   document.getElementById('trngDesc'+i).value='';
						   alert('Please add training description');
						   document.getElementById('trngDesc'+i).focus();
						   
						   return false;
					  }if(document.getElementById('trngDuration'+i).value==''){					  
						   alert('Please add training duration');
						   document.getElementById('trngDuration'+i).focus();
						   return false;
						   
					  }if(eval(document.getElementById('trngDuration'+i).value)==0){
						   alert('Invalid training duration entered.');
						   document.getElementById('trngDuration'+i).value="";
						   document.getElementById('trngDuration'+i).focus();
						   return false;
						   
					  }if(document.getElementById('trngFrom'+i).value==''){					  		
						   alert('Please enter training from date.');
						   document.getElementById('trngFrom'+i).focus();
						   return false;
						   
					  }if(!validateDate('trngFrom'+i,'trng.from')){
					  	   return false;
					  
					  }if(!dateCheckLessThanToday('trngFrom'+i,'trng.from')){
					  	  document.getElementById('trngFrom'+i).focus();
					   	  return false; 
					   	  
					  }if(document.getElementById('trngTo'+i).value==''){					  	   
						   alert('Please enter training to date.');
						   document.getElementById('trngTo'+i).focus();
						   return false;
						   
					  }if(!validateDate('trngTo'+i,'trng.to')){
					  	   return false;
					  
					  }if(!dateCheckLessThanToday('trngTo'+i,'trng.to')){
					  	  document.getElementById('trngTo'+i).focus();
					   	  return false; 
					   	  
					  }if(!dateDifferenceEqual(document.getElementById('trngFrom'+i).value, document.getElementById('trngTo'+i).value, 'trngFrom'+i, 'trng.from', 'trng.to')){
					      document.getElementById('trngFrom'+i).focus();
					      return false;
					      
					  } 
			  
			 }//for ends
			 
			 
			}
			
			var conf;
			if(document.getElementById('paraFrm_isSelf').value=="true"){//SELF
				 
				 conf = confirm('Do you want to Finish your '+document.getElementById('appraisal.form.head').innerHTML+' ?\nClick OK to Finish. ');
				 				 
			}else{//OTHER PHASES
			     
			     conf = confirm('Do you want to Finish this '+document.getElementById('appraisal.form.head').innerHTML+' ?\nClick Ok to Finish.');
				 
			}
		
			if(conf){	
				document.getElementById('sourceFormType').value="TrainingDtl";		
				document.getElementById("paraFrm").action="ApprFormCareerProgression_forwardAppraisal.action";
				document.getElementById("paraFrm").submit();			
			}
			return false;
}

</script>
