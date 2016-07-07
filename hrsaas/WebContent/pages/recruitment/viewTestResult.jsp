  <%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="InterviewSchedule" validate="true" id="paraFrm" theme="simple"> 
   <s:hidden name="viewTestFlag"/>
   <s:hidden name="requisitionCode"/>
   <s:hidden name="testDetailCode"/>
   <s:hidden name="onlineTestTemplateCode"/>
   <s:hidden name="candCode"/>
   <s:hidden name="dataPath"/>  
	             <table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg"  > 
				              <tr>								
								<td width="100%" colspan="4" class="formtext"> <input type="button" value="Close" class="token" onclick="callCancel();"> </td>
							 </tr>  
							 
							  <tr>								
								<td width="100%" colspan="4" class="formtext"> <b> <label  class = "set"  id="testDetails"  name="testDetails" ondblclick="callShowDiv(this);"><%=label.get("testDetails")%></label> </b> </td>
							 </tr> 
							 
							 	 <tr>
							 <td width="100%" colspan="4" >
							  <table width="100%" class="formbg" cellpadding="2" cellspacing="2">  
 							<tr>								
								<td width="100%" colspan="4" class="formtext"> <b> <label  class = "set"  id="reqDetails"  name="reqDetails" ondblclick="callShowDiv(this);"><%=label.get("reqDetails")%></label> </b> </td>
							 </tr>  
							<tr>	
							 <s:hidden name="viewCandidateName"/> <s:hidden name="viewReq"/> <s:hidden name="viewPosition"/>
							  <td width="25%" colspan="1" >   <label  class = "set"  id="reqs.code"  name="reqs.code" ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label> :    </td>							
								<td width="30%" colspan="1" nowrap="nowrap" class="formtext"> <s:property value="viewReq"/>  </td>
								<td width="15%" colspan="1" >   <label  class = "set"  id="position"  name="position" ondblclick="callShowDiv(this);"><%=label.get("position")%></label> :    </td>
								<td width="30%" colspan="1" > <s:property value="viewPosition"/> </td>							
						 	</tr>    
						 	
						 	<tr>	
							    <td width="25%" colspan="1" >   <label  class = "set"  id="cand.name"  name="cand.name" ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label> :   </td>							
								<td width="30%" colspan="1" nowrap="nowrap" class="formtext"> <s:property value="viewCandidateName"/>  </td>
						 	</tr>   
						 	
						 	</table>
							 
							 <s:iterator value="viewTestList"> 
							 <tr>
							 <td width="100%" colspan="4" >
							  <table width="100%" class="formbg" cellpadding="2" cellspacing="2">
							  <tr>								
								<td width="100%" colspan="4" class="formtext"> <b> <label  class = "set"  id="testResult"  name="testResult" ondblclick="callShowDiv(this);"><%=label.get("testResult")%></label> </b><b> : <s:property value="viewTestRoundType"/> </b> </td>
							 </tr>  
							 
							  <s:hidden name="viewTotalMarks"/> 
						      <s:hidden name="viewOptMarks"/> <s:hidden name="viewResult"/> <s:hidden name="viewTestType"/> 
				              <s:hidden name="viewTestDate"/> <s:hidden name="viewTestTime"/> <s:hidden name="viewTestComments"/> 
 							  <s:hidden name="viewTestRoundType"/>  
						 	
						 	<tr>	 
								<td width="25%" colspan="1" >   <label  class = "set"  id="testDate"  name="testDate" ondblclick="callShowDiv(this);"><%=label.get("testDate")%></label> :  </td>							
								<td width="30%" colspan="1" nowrap="nowrap" class="formtext"> <s:property value="viewTestDate"/>  </td>
				            	<td width="15%" colspan="1" > <label  class = "set"  id="testType"  name="testType" ondblclick="callShowDiv(this);"><%=label.get("testType")%></label> :   </td>
								<td width="30%" colspan="1" > <s:property value="viewTestType"/> </td>							
						 	</tr>   
						 	
						 	<tr>	
						 	    <td width="25%" colspan="1" > <label  class = "set"  id="obtMarks"  name="obtMarks" ondblclick="callShowDiv(this);"><%=label.get("obtMarks")%></label> :  </td>
								<td width="30%" colspan="1" > <s:property value="viewOptMarks"/> </td>	 
								<td width="15%" colspan="1" >   <label  class = "set"  id="totalMarks"  name="totalMarks" ondblclick="callShowDiv(this);"><%=label.get("totalMarks")%></label> :  </td>							
								<td width="30%" colspan="1" nowrap="nowrap" class="formtext"> <s:property value="viewTotalMarks"/>  </td>
							 
						 	</tr>    
						 	
						 	
						 	<tr> 
							    <td width="25%" colspan="1" > <label  class = "set"  id="testTime"  name="testTime" ondblclick="callShowDiv(this);"><%=label.get("testTime")%></label> :    </td>
								<td width="30%" colspan="1" > <s:property value="viewTestTime"/> </td>	 
						 	    <td width="15%" colspan="1" >  <label  class = "set"  id="testResult"  name="testResult" ondblclick="callShowDiv(this);"><%=label.get("testResult")%></label> :  </td>							
								<td width="30%" colspan="1" nowrap="nowrap" class="formtext"> <s:property value="viewResult"/>  </td>
							   														
						 	</tr>  
						   <tr>	
							  <td width="25%" colspan="1" >   <label  class = "set"  id="rec.comments"  name="rec.comments" ondblclick="callShowDiv(this);"><%=label.get("rec.comments")%></label> :  </td>							
							  <td width="75%" colspan="3" class="formtext"> <s:property value="viewTestComments"/>  </td>
						 	</tr>    
						 	</td>
						 	</tr>
						 	</table> 
						</s:iterator> 
			<tr>
							 <td width="100%" colspan="4" >			
			<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg"  >
				<tr>
		  			<td width="100%" colspan="4"></td>
		  		</tr>
		  		<tr>
		  			<td width="100%" colspan="4"><b>Test Evaluation</b></td>
		  		</tr>
		  		
		  		<tr>
					<td colspan="4">
						<table cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<strong>Subject : </strong><s:property value="subject"/> 
								</td>
								<td width="20px">&nbsp;</td>
								<td>
									<strong>Category : </strong><s:property value="category"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
								
		  		<tr>
		  			<td width="20%"><strong>Question : </strong></td>
		  			<td colspan="3">
		  				<s:property value="questionName"/>
		  			</td><s:hidden name="questionName"/>
		  		</tr>
		  		<s:if test="documentAttachedFlag">
					<tr>
						<td colspan="4">
							<strong>Refer attached document :  </strong>
							<s:hidden name="questionUploadedDoc"/>
							<a href="#" onclick="showRecord('questionFile');" title="Click here to view attached document">  <font color="blue"><s:property value="questionUploadedDoc"/></font></a> 
						</td>
					</tr>
				</s:if>
				<s:else>
					<tr>
						<td colspan="4">&nbsp;</td>
					</tr>
				</s:else>
		  		
		  		<s:hidden name="subjectiveFlag"></s:hidden>
		  			<s:hidden name="questionSequence"/>
		  			<s:if test="subjectiveFlag">
			  			<tr>
				  			<td width="20%"><strong>Answer : </strong></td>
				  			<td colspan="3"><s:property value="subjAnswer"/></td><s:hidden name="subjAnswer"/>
			  			</tr>	
			  			<tr>
				  			 <td colspan="4">
				  			 	<table cellpadding="0" cellspacing="0">
									<s:if test="answerAttachedFlag">
										<tr>
											<td width="20%" nowrap="nowrap">
												<strong>Attached answer document :</strong>
											</td>
											<td width="80%">
												 <s:hidden name="answerUploadedDoc"/>
												 <a href="#" onclick="showRecord('answerFile');" title="Click here to view attached document">  
												 	<font color="blue"><s:property value="answerUploadedDoc"/></font>
												 </a>
											</td>
										</tr>
									</s:if>
								</table>
				  			 </td>
			  			</tr>	
		  			</s:if>
		  			<s:else>
		  				<%! int j = 0 ; %>
						<% int h = 1; int g=0;%>
						<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
						<tr>
		  					<td colspan="4" align="left"><strong>Answer : </strong></td>
		  				</tr>	
			  				<s:iterator status="status" value="onLineTestList">
			  					<tr>
			  						<td width="20%"></td>
				  					 <td width="5%" align="center">
										  <s:checkbox name="optionCheckBox" disabled="true"/>
							         </td>
				  					<td colspan="2" ><s:property value="optionName"/>
									</td>
								</tr>
			  				</s:iterator>
		  				</table>
		  			</s:else>
		  			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				  		<tr>
				  			<s:hidden name="previousButtonFlag"/>
				  			<s:hidden name="nextButtonFlag"/>
				  			<td colspan="4" align="center">
				  				<s:if test="previousButtonFlag">
				  					<s:submit value="Previous" cssClass="token" onclick="previous()"/>
				  				</s:if>
				  				<s:if test="nextButtonFlag">
				  					<s:submit value="Next" cssClass="token" onclick="next()"/>
				  				</s:if>
				  			</td>
				  		</tr>
		  		</table>
		  	</table>
  </td>
  </tr>
			 </table>  	
  
</s:form> 
<script>   
function callCancel()
{
  window.close();
}

function showRecord(fileType) {
	var fileName = "";
	document.getElementById('paraFrm').target = "_blank";
	if(fileType=='answerFile') {
		fileName =document.getElementById('paraFrm_answerUploadedDoc').value;
		if(fileName == "") {
			alert("File is not attached.");
			return false ; 
		}
	} else {
		fileName =document.getElementById('paraFrm_questionUploadedDoc').value;
		if(fileName == "") {
			alert("File is not attached.");
			return false ; 
		}
	}
	document.getElementById('paraFrm').action = 'InterviewSchedule_openUploadedFile.action?fileName='+fileName;
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target = "main";
	return true ; 
}

function previous(){
   	document.getElementById('paraFrm').target = "_self";
   	document.getElementById('paraFrm').action='InterviewSchedule_previousAction.action';
 	document.getElementById('paraFrm').submit();
}
 
function next(){
	document.getElementById('paraFrm').target = "_self";
   	document.getElementById('paraFrm').action='InterviewSchedule_nextAction.action';
 	document.getElementById('paraFrm').submit();
} 
</script>