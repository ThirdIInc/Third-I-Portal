
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/pages/common/labelManagement.jsp"%>

<html>
<head>
<link type="text/css" href="../pages/PAS/PAS-Css/apprFormGeneralInfo.css" rel="stylesheet"  />
<script type="text/javascript" src="../js/jquery-2.2.min.js" ></script>
</head>
<body>



<div align="center" id="overlay" style="z-index: 3; visibility: hidden; position: absolute; 
width: 776px; height: 700px; margin: 0px; left: 0; top: 0; -moz-opacity: .1; opacity: .1;">
</div>
<s:form action="ApprFormSection" validate="true" id="paraFrm" theme="simple">

<s:hidden name="source" id="source"/>

<table width="100%" border="0" align="right" cellpadding="2" cellspacing="1" class="formbg" >
       		
			           <div class = "form-header">
                      <img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" />
				      <label name="appraisal.form.head" class = "set"  id="appraisal.form.head" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.head")%>
				      </label>
				      </div>
				 
	    
    
    <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="1">
         <tr>
            <td width="78%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
            
          </tr>
        </table></td>
    </tr>
    <tr>
      <td colspan="3">
          
            <s:hidden name="templateCode"/>
            	<s:hidden name="nextExist"/>
             	<s:hidden name="visibilityFlag"/>
             	<s:hidden name="previousExist"/>
             	<s:hidden name="empDataExist"/>
             	<s:hidden name="maxWeightage"/>
            	<s:hidden name="phaseForwardFlag"/>
            	<s:hidden name="apprValidTillDate"/>
            	
            	<div class="Appraisal-details">
            		<div>
            		<b> 
            			<span><i class="fa fa-info-circle" aria-hidden="true"></i></span>
            			<label name="appraisal.details" class = "Appraisal-details"  id="appraisal.details"><%=label.get("appraisal.details")%></label>
            		</b>
            		</div>
            	</div>
            		
            		
	                        <div class = "formWrapper">
	                        <div class = "col-md-3">
	              				<label  class = "set" name="appraisal.form.period"  id="appraisal.form.period" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.period")%>
	              				</label> :
				  				<s:hidden name="apprId"/><s:hidden name="apprCode"/><s:property value="apprCode"  />
	              			</div>	  
	              			<div class = "col-md-3"> 
	              				<label name="appr.from.date" class = "set"  id="appr.from.date" ondblclick="callShowDiv(this);"><%=label.get("appr.from.date")%></label> :
	              					<s:hidden name="apprStartDate"/><s:property value="apprStartDate"  />&nbsp;
	              			</div>
	              			<div class = "col-md-3">		
	              					<label name="appr.to.date" class = "set"  id="appr.to.date" ondblclick="callShowDiv(this);"><%=label.get("appr.to.date")%></label> :
	              					<s:hidden name="apprEndDate"/><s:property value="apprEndDate" />
	              			</div>		 
	            			
	             			<div class = "col-md-3">
	              				<label  class = "set" name="appraisal.form.phase"  id="appraisal.form.phase" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.phase")%></label> :
				  				
				  				<s:hidden name="phaseCode"/>
				  				<s:hidden name="phaseName"/>
				  				<s:property value="phaseName"/>
							</div>
							<div class = "col-md-3">	              				   
	              				<label name="phase.from.date" class = "set"  id="phase.from.date" ondblclick="callShowDiv(this);"><%=label.get("phase.from.date")%></label> : 
	              					<s:hidden name="phaseStartDate"/><s:property value="phaseStartDate" />&nbsp;
	              			</div>
	              			<div class = "col-md-3">		
	               					<label name="phase.to.date" class = "set"  id="phase.to.date" ondblclick="callShowDiv(this);"><%=label.get("phase.to.date")%></label> : 
	              					<s:hidden name="phaseEndDate"/><s:property value="phaseEndDate" /><s:hidden name="phaseLockFlag"/>
	              			</div>		 
	            			
				            
				            <div class = "col-md-3">
				              	<label  class = "set" name="employee"  id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> :
							  	<s:hidden name="empId"/><s:hidden name="empName"/><s:property value="empName"  />
							</div>				              	   
				            <div class = "col-md-3">  	
				              	<label  class = "set" name="designation"  id="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:
				             	 <s:hidden name="empDesgName"/><s:property value="empDesgName" />
				            </div> 	   
				            </div>
                    	 
               		</td>
          		</tr>
        	
	<% int c=0; %>
	<!-- s:if test="apprFormSection.parametersAvailable" -->
    <tr>
      	<td colspan="3">
		<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
			 <s:if test="apprFormSection.ratingFlag">
		   		<s:if test='%{apprFormSection.ratingType == "scale" }'>
   					<tr>
		      			<td colspan="3">
		      				<table width="98%" border="0" cellpadding="2" cellspacing="2" >
		            			<tr>
		   				 			<td align="left" colspan="3" width="100%"><b>Rating Scale: </b><s:hidden name="ratingNote" id="ratingNote"/>
									<div id="ratingNoteDiv"></div>
									<script>
										document.getElementById("ratingNoteDiv").innerHTML=document.getElementById("ratingNote").value;
										$("#ratingNoteDiv").find("br").replaceWith('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;')
									</script>
									
									</td>
		   				 		</tr>
		      				</table>
		      			</td>
     				</tr>
     			</s:if>	
		   	</s:if>
		</table>
		<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
	      		<tr>
	            	<td><table width="100%" border="0" align="center" cellpadding="2" cellspacing="2">
	              			<tr>
								<td  width="15%" nowrap="nowrap" ><s:hidden name="sectionCode"/><b><s:property value="sectionName"/></b>
								<br><!--<s:if test="apprFormSection.ratingFlag">
									<label name="appraisal.form.ques.forLink" class = "set"  id="appraisal.form.ques.forLink" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.ques.forLink")%></label>
									</s:if>-->
								
								</td>
								<td  width="30%" align="left"><b><s:hidden name="sectionList"/></b></td>
								<td  width="5%" align="left"></td>
									<td  width="50%" align="right">
								</td>
							</tr>
					</table></td>
				</tr>	
	            <tr>
	            	<td><table width="98%" border="0" align="center" cellpadding="2" cellspacing="2">
	              			<tr><s:hidden name="detailFLag"/>
	              				<s:hidden name="ratingFlag"/>
	              				<s:hidden name="ratingType"/>
			              		<s:hidden name="commentFlag"/>
			              		<s:hidden name="quesWtDisplayFlag"/>
			              		<s:hidden name="fractionRating"/>
								<td class="formth" width="5%" ><label name="appraisal.form.section.srno"  class = "set"  id="appraisal.form.section.srno" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.section.srno")%></label></td>
								<td class="formth" width="100%" align="left">
								<label  name="appraisal.form.ques.desc" class = "set"  id="appraisal.form.ques.desc" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.ques.desc")%></label></td>
								<s:if test="ratingFlag">
									<s:if test="apprFormSection.quesWtDisplayFlag">
										<td class="formth" width="5%"  align="center" ><label name="appraisal.form.ques.wt" class = "set"  id="appraisal.form.ques.wt" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.ques.wt")%></label></td>
									</s:if>	
									<td class="formth" width="5%"  align="center" nowrap="nowrap"><label name="appraisal.form.rating" class = "set"  id="appraisal.form.rating" ondblclick="callShowDiv(this);">Rating</label></td>
									<s:if test="apprFormSection.quesWtDisplayFlag">
										<td class="formth" width="5%"  align="center" ><label name="appraisal.form.ques.avg" class = "set"  id="appraisal.form.ques.avg" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.ques.avg")%></label></td>
									</s:if>
								</s:if>
								<s:if test="commentFlag">
									<td class="formth" width="50%" align="left"><label name="appraisal.form.comment"  class = "set"  id="appraisal.form.comment" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.comment")%></label></td>
								</s:if>
							</tr>
						<%	int j = 1; %>
								<%!int TotalCount=0; %>
						<s:iterator value="questionList">
							<tr>
							
							
								<td width="5%" align="center" class="td_bottom_border"><%=j%><s:hidden name="questionCode" /></td>
								<td width="30%" align="justify" class="td_bottom_border">
																<s:hidden name="quesType" />
								<s:property value="questionDesc"/>
								<s:hidden name="quesMandatory" id='<%="quesMandatory"+c%>'/><s:hidden name="quesLimit" id='<%="quesLimit"+c%>'/>
								<s:if test='%{quesMandatory=="Y"}'><!-- <FONT color="red">*</font> --></s:if></td>
								<s:if test="apprFormSection.ratingFlag">
									<s:if test="apprFormSection.quesWtDisplayFlag">
										<td width="5%"  class="td_bottom_border" align="center" ><s:textfield name="quesWeight" readonly="true" size="3" id='<%="quesWeight"+c%>' cssStyle="background-color: #F2F2F2"/></td>
									</s:if>
									<s:else>
										<s:hidden name="quesWeight"  id='<%="quesWeight"+c%>'/>
									</s:else>	
									<s:if test="apprFormSection.phaseForwardFlag">
										<td width="5%"  class="td_bottom_border"><s:textfield name="quesRating" id='<%="quesRating"+c%>' size="3" disabled="true"/></td>									
									</s:if>
									<s:else>
										<s:if test='%{apprFormSection.ratingType == "perc" }'>
											<!--used for glodyne	-->
											<s:if test='%{apprFormSection.fractionRating == "0" }'>
												<td width="5%"  class="td_bottom_border" align="center"><s:textfield name="quesRating" id='<%="quesRating"+c%>' size="3" maxlength="3" onkeypress=" return numbersOnly()" onkeyup="return netAverage();"   /></td>
											</s:if>
											<s:else>
												<td width="5%"  class="td_bottom_border" align="center"><s:textfield name="quesRating" id='<%="quesRating"+c%>' size="3" maxlength="6" onkeypress=" return numbersWithDot()" onkeyup="return netAverage();" /></td>
											</s:else>	
										</s:if>
										<s:else>
											<s:if test='%{apprFormSection.fractionRating == "0" }'>
										 		<td width="5%" align="left" class="td_bottom_border"><s:select name="quesRating" headerKey="0"  id='<%="quesRating"+c%>' headerValue="-Select-" list="%{apprFormSection.hashmap}"  onchange="return netAverage();" /></td>
										 	</s:if>
											<s:else>
												<td width="5%"  class="td_bottom_border" align="center"><s:textfield name="quesRating" id='<%="quesRating"+c%>' size="3" maxlength="6" onkeypress=" return numbersWithDot()" onkeyup="return netAverage();"  /></td>
											</s:else>				 							
										</s:else>
									</s:else>
										<s:if test="apprFormSection.quesWtDisplayFlag">
										<!--used for glodyne -->
											<td width="5%"  class="td_bottom_border" align="center"><s:textfield name="quesAvg" size="3" readonly="true"  id='<%="quesAvg"+c%>' cssStyle="background-color: #F2F2F2" /></td>
										</s:if>
									</s:if>
									<s:if test="apprFormSection.commentFlag">	
										<s:if test="apprFormSection.phaseForwardFlag">				
											<td width="50%"  align="left" class="td_bottom_border"><s:textarea name="quesComment" cols="45" rows="2" id='<%="quesComment"+c%>'  disabled="true" />
												<!-- (<label id='<%="labelquesComment"+c%>'><s:property value="quesLimit" /></label>) -->
												<!--<s:hidden name="quesLimit" id='<%="limitquesComment"+c%>'/> -->
												</td>
										</s:if>	
										<s:else>
											<td width="50%" align="left" class="td_bottom_border"><s:textarea name="quesComment" cols="45" rows="2" id='<%="quesComment"+c%>' required="false"  />
											<!-- (<label id='<%="labelquesComment"+c%>'><s:property value="quesLimit" /></label>) -->
											<!--<s:hidden name="quesLimit" id='<%="limitquesComment"+c%>'/> -->
											</td>
										</s:else>
									</s:if>
									<s:if test="apprFormSection.detailFLag">
									<s:if test="flag">
										<td><a onclick="showPrevious(<%=c%>);" >Show/Hide</td>
										</s:if>
										
									</s:if>
							
								<tr id='<%="previous"+c%>'>
									 <td colspan="6">
									 <table width="98%" border="0" cellpadding="2" cellspacing="2" class="formbg">
									<tr>
									<td  width="3%" class="formth" align="center"><b><label name="appraisal.form.section.srno"  class = "set"  id="appraisal.form.section.srno1" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.section.srno")%></label></b></td>
									<td  width="12%" class="formth" nowrap="nowrap"><b><label  name="appraisal.form.phasename" class = "set"  id="appraisal.form.phasename2" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.phasename")%></label></b></td>
									<td  width="20%" align="left" class="formth" nowrap="nowrap"><b><label  name="appraisal.form.appraiser.name" class = "set"  id="appraisal.form.appraiser.name1" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.appraiser.name")%></label></td>
									
									<s:if test="apprFormSection.quesWtDisplayFlag">
									<td  width="5%"  align="center" class="formth" ><b><label name="appraisal.form.ques.wt" class = "set"  id="appraisal.form.ques.wt1" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.ques.wt")%></label></b></td>
									</s:if>
	
									<td width="5%"  align="center" nowrap="nowrap" class="formth"><b><label name="appraisal.form.rating" class = "set"  id="appraisal.form.rating1" ondblclick="callShowDiv(this);">Rating</label></b></td>
									
									<s:if test="apprFormSection.quesWtDisplayFlag">									
									<td  width="5%"  align="center" class="formth"><b><label name="appraisal.form.ques.avg" class = "set"  id="appraisal.form.ques.avg1" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.ques.avg")%></label></b></td>
									</s:if>

									<td  width="60%" align="center" class="formth"><b><label name="appraisal.form.comment1" class = "set"  id="appraisal.form.comment3" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.comment1")%></label></b></td>
							 
									
									</tr>
									
									<%int k=1; %>
									 <s:iterator value="subQuestionList">
									
									
									
									
									 <tr >
									  <td width="3%" class="td_bottom_border" align="center"><%=k++ %> </td>
									 <td width="5%" class="td_bottom_border">
									 <s:property value="prvPhaseName"/> </td>
									  <td width="20%" class="td_bottom_border">
									  <s:property value="prvPhaseApprName"/> </td>
									   									    <s:if test="apprFormSection.quesWtDisplayFlag">
									   <td align="center"  width="5%" class="td_bottom_border">
									   <s:property value="prvPhaseWeightage"/>
									       </td>
										   </s:if>
									    <td align="center"  width="5%" class="td_bottom_border">
									    <s:property value="prvPhaseRating"/>									    
									    </td >
									    <s:if test="apprFormSection.quesWtDisplayFlag">
									     <td align="center" width="5%" class="td_bottom_border">
									      <s:property value="prvPhaseActual"/>										    
									      </td>
									     </s:if>
									      
									      <td colspan="2" width="50%" class="td_bottom_border"> <s:property value="prvPhaseComments"/> </td>
									       
									 </tr>
									 </s:iterator>
									 </tr>
									  <%j++; c++;%>
									  
									
									</table>
								</td>
								 </tr>	  
							</s:iterator>
							<%TotalCount=c; %>
							<!--used for glodyne	-->
								<tr>
									<s:if test="apprFormSection.ratingFlag">
									<s:if test="apprFormSection.quesWtDisplayFlag">
	           							<td width="5%"  align="left"><b>Total :</b></td>
					           			<td width="30%" align="left"></td>
					           		 	<td width="5%" align="center"><s:textfield name="totalWeightage" readonly="true" size="3" cssStyle="background-color: #F2F2F2" ></s:textfield></td>
					          			<td width="5%" align="center"></td>
	          							<td width="5%" align="center"><s:textfield name="totalAvg" readonly="true" size="3" cssStyle="background-color: #F2F2F2" ></s:textfield></td>
	          							<s:if test="apprFormSection.commentFlag">
	          								<td width="50%" align="left"></td>
	          							</s:if>	
	          						</s:if>
	          						</s:if>	
								</tr>
		      			</table>
	      		</td>
	     		<td><input type="hidden" name="count" id="count" value="<%=c%>"/></td>
	    	</tr>
     	</table>
		
		
		
		</td>
    </tr>
    <!--/s:if--><!-- Section lenth check --> 	
    <input type="hidden" name="count" id="count" value="<%=c%>"/>

    <!-- ================================ -->
    <s:if test="flagNV">
     <tr>
      	<td colspan="4"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
      			<tr>
     				<td colspan="4"><b><label name="appraisal.form.section.previousinput"  class = "set"  id="appraisal.form.section.previousinput" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.section.previousinput")%></label></b></td>
    			</tr>
	            <tr>
	            	<td><table width="98%" border="0" align="center" cellpadding="2" cellspacing="2">
	              			<tr>
								<td class="formth" width="5%" ><label name="appraisal.form.section.srno"  class = "set"  id="appraisal.form.section.srno" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.section.srno")%></label></td>
								<td colspan="5" class="formth" width="80%" align="left"><label  name="appraisal.form.ques.desc" class = "set"  id="appraisal.form.ques.desc" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.ques.desc")%></label></td>
								<td class="formth" width="15%" ></td>

							</tr>
						<%	int jj = 1 , cc=0;
								%>
								<%!int TotalCountNV=0; %>
						<s:iterator value="questionListNV">
							<tr>
							
								<td width="5%"  class="td_bottom_border" align="center"><%=jj++ %>
								</td>
								<td width="80%" colspan="5" class="td_bottom_border" align="left"><s:property value="quesDescNV"/>
								</td>								
								<td width="15%" class="td_bottom_border"> <a onclick="showPreviouNV(<%=cc%>);" >Show/Hide</td>
								</td>
								</tr>	 
								
								
								<tr id='<%="previousNV"+cc%>'>
									 <td colspan="7">
									 <table width="98%" border="0" cellpadding="2" cellspacing="2" class="formbg">
									<tr>
									<td  width="3%" class="formth"><b><label name="appraisal.form.section.srno"  class = "set"  id="appraisal.form.section.srno2" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.section.srno")%></label></b></td>
									<td  width="5%" class="formth" nowrap="nowrap"><b><label  name="appraisal.form.phasename" class = "set"  id="appraisal.form.phasename1" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.phasename")%></label></b></td>
								<td  width="20%" align="left" class="formth"><b><label  name="appraisal.form.appraiser.name" class = "set"  id="appraisal.form.appraiser.name2" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.appraiser.name")%></label></td>
																		    <s:if test="apprFormSection.quesWtDisplayFlag">	
										<td  width="5%"  align="center" class="formth" ><b><label name="appraisal.form.ques.wt2" class = "set"  id="appraisal.form.ques.wt2" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.ques.wt")%></label></b></td>
									</s:if>
									<td width="5%"  align="center" nowrap="nowrap" class="formth"><b><label name="appraisal.form.rating2" class = "set"  id="appraisal.form.rating2" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.rating")%></label></b></td>
																		    <s:if test="apprFormSection.quesWtDisplayFlag">
										<td  width="5%"  align="center" class="formth"><b><label name="appraisal.form.ques.avg" class = "set"  id="appraisal.form.ques.avg2" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.ques.avg")%></label></b></td>
								 </s:if>
							 
									<td  width="60%" align="center" class="formth" colspan="3"><b><label name="appraisal.form.comment1" class = "set"  id="appraisal.form.comment2" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.comment1")%></label></b></td>
							 
									
									</tr>
									
									<%int kk=1; %>
									 <s:iterator value="subQuestionListNV">
									
									
									
									
									 <tr >
									  <td width="3%" class="td_bottom_border" align="center"><%=kk++ %> </td>
									 <td width="5%" class="td_bottom_border" nowrap="nowrap">
									 <s:property value="prvPhaseNameNV"/> </td>
									  <td width="20%" class="td_bottom_border" nowrap="nowrap">
									  <s:property value="prvPhaseApprNameNV"/> </td>
									   									    <s:if test="apprFormSection.quesWtDisplayFlag">
									   <td align="center"  width="5%" class="td_bottom_border">
									   <s:property value="prvPhaseWeightageNV"/>
									       </td></s:if>
										   <td align="center"  width="5%" class="td_bottom_border">
									    <s:property value="prvPhaseRatingNV"/>									    
									    </td >
									   									    <s:if test="apprFormSection.quesWtDisplayFlag">
									     <td align="center" width="5%" class="td_bottom_border">
									      <s:property value="prvPhaseActualNV"/>										    
									      </td>
									    </s:if>
									      
									      <td colspan="3" width="50%" class="td_bottom_border"> 
									      <s:property value="prvPhaseCommentsNV"/> </td>
									       
									 </tr>
									 </s:iterator>
									 </tr>
									  <% cc++;%>
									  
									
									</table>
								</td>
								 </tr>	 
								
								
								
								
								
								 
							</s:iterator>
						<%TotalCountNV=cc; %>
								
		      			</table>
	      		</td>	     		
	    	</tr>
   			
     	</table></td>
    </tr>
    </s:if>
    
  <!-- ================================== --> 
    <s:if test="showMonthRating">
    <tr>
      	<td colspan="4"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
      			<tr>
     				<td colspan="4"><b>Weightage for KRAs</b></td>
    			</tr>
				<tr>
					<td>
						<table>
							<tr>
								<td width="50%">Task based KRAs - 70 %</td>
							</tr>
							<tr>
								<td width="50%">Competency Traits - 30%</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
     <tr>
      	<td colspan="4"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
      			<tr>
     				<td colspan="4"><b>Task Based KRA Ratings</b></td>
    			</tr>
    			<tr>
     				<td colspan="4">Specified below are the Monthly Rating captured through KMS for Appraisal year 2010 - 2011</td>
     			</tr>	
	            <tr>
	            	<td><table width="100%" border="0" cellpadding="2" cellspacing="2">
	              			<tr>
	              				<td class="formth" width="10%" >Sr.No.</td>
								<td class="formth" width="50%" >Month-Year</td>
								<td class="formth" width="50%" align="left">Rating</td>
								
							</tr>
							<% int ratingCount=1; %>
							<s:iterator value="monthRatingList">
								<tr>
									<td width="10%"  class="td_bottom_border" align="center"><%=ratingCount++%></td>
									<td width="50%"  class="td_bottom_border" align="center"><s:property value="ratingMonthString" /></td>
									<td width="50%"  class="td_bottom_border" align="center"><s:property value="monthRating"/></td>
								</tr>
							</s:iterator>
							<tr>
								<td class="td_bottom_border" width="10%" >&nbsp;</td>
								<td class="td_bottom_border" width="50%" align="right"><STRONG>Avg. Rating</STRONG></td>
								<td class="td_bottom_border" width="50%" align="center"><STRONG><s:property value="avgMonthRating" /></STRONG></td>
							</tr>
							<tr>
								<td class="td_bottom_border" width="50%" colspan="2" align="left">Weighted average rating for Task based KRA's at 70%</td>
								<td class="td_bottom_border" width="50%" align="center"><s:property value="rating70" /></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	</s:if>
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
	<!-- ================================== --> 
 	<tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2">
         <tr>
            <td width="78%">
           	 <jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
         </tr>
        </table></td>
    </tr>
</table>
</s:form>
</body>
</html>

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
						if(document.getElementById('paraFrm_detailFLag').value == 'false')	
							var conf = confirm('Do you want to Finish your '+document.getElementById('appraisal.form.head').innerHTML+' ?\nClick OK to Finish.');
						else
							var conf = confirm('Do you want to Finish this '+document.getElementById('appraisal.form.head').innerHTML+' ?\nClick Ok to Finish.');
							
							if(conf){
								document.getElementById("paraFrm").action="ApprFormSection_updateAppraisal.action";
								document.getElementById("paraFrm").submit();
								document.getElementById('div_previewApprIdFinalRating').style.display = 'none';
							}
							
					}
					//Proposed Score ----- End ---- 
								onload();
							function onload(){
							
							var countValue='<%=TotalCount%>';							
							for(var j=0;j<countValue;j++){	
												
								document.getElementById('previous'+j).style.display = 'none';								
							}
							
							var countValueNV='<%=TotalCountNV%>';							
							for(var jj=0;jj<countValueNV;jj++){	
												
								document.getElementById('previousNV'+jj).style.display = 'none';								
							}
							
							
							}
								
								function showPreviouNV(id){
							
								var temp = eval(id);
								var row = document.getElementById('previousNV'+temp);
							if (row.style.display == '') 
								row.style.display = 'none';
							else 
								row.style.display = '';
							}
								
								
								
							function showPrevious(id){
							
								var temp = eval(id);
								var row = document.getElementById('previous'+temp);
							if (row.style.display == '') 
								row.style.display = 'none';
							else 
								row.style.display = '';
							}	
							</script>
<script type="text/javascript">
function check(){
	var tableLength = document.getElementById("count").value;
	//alert(tableLength);
			var ratingFlag = document.getElementById('paraFrm_ratingFlag').value;
			var ratingType = document.getElementById('paraFrm_ratingType').value;
			var commentFlag = document.getElementById('paraFrm_commentFlag').value;
			
			for(i=0;i<tableLength;i++){
				
					var check = document.getElementById('quesMandatory'+i).value;
				
					if(ratingFlag == 'true'){
						var rate = document.getElementById('quesRating'+i).value;
							
						if(ratingType == 'perc'){	
							if(check =='Y' && rate == ''){
									alert("Please give "+document.getElementById("appraisal.form.rating").innerHTML.toLowerCase() +" for "+document.getElementById("appraisal.form.ques.desc").innerHTML.toLowerCase()+" no. "+(i+1));
									//alert("Please give rating for question");
									document.getElementById('quesRating'+i).focus();
									return false;
								}
								try{
							if(eval(rate) > 100){
									alert("Please give "+document.getElementById("appraisal.form.rating").innerHTML.toLowerCase() +" less than or equal to 100 for "+document.getElementById("appraisal.form.ques.desc").innerHTML.toLowerCase()+" no. "+(i+1));
									//alert("Please give rating for question");
									document.getElementById('quesRating'+i).focus();
									return false;
							}
							}catch(e){
								alert("Only numbers allowed in rating field");
								document.getElementById('quesRating'+i).value="";
								document.getElementById('quesRating'+i).focus();
								return false;
							}
						  }else{
						  var maxWt = document.getElementById('paraFrm_maxWeightage').value;
						  	if(check =='Y' && rate == 0){
									alert("Please give "+document.getElementById("appraisal.form.rating").innerHTML.toLowerCase() +" for "+document.getElementById("appraisal.form.ques.desc").innerHTML.toLowerCase()+" no. "+(i+1));
									//alert("Please give rating for question");
									document.getElementById('quesRating'+i).focus();
									return false;
								}
								try{
									if(eval(rate) > eval(maxWt)){
										alert("Please give "+document.getElementById("appraisal.form.rating").innerHTML.toLowerCase() +" less than or equal to Max rating for "+document.getElementById("appraisal.form.ques.desc").innerHTML.toLowerCase()+" no. "+(i+1));
										//alert("Please give rating for question");
										document.getElementById('quesRating'+i).focus();
										return false;									
									}
								}catch(e){
								
								}
						  }
						}
					if(commentFlag == 'true'){
						var comment = trim(document.getElementById('quesComment'+i).value);
							if( check =='Y' && comment == ""){
									alert("Please enter "+document.getElementById("appraisal.form.comment").innerHTML.toLowerCase() +" for "+document.getElementById("appraisal.form.ques.desc").innerHTML.toLowerCase()+" no. "+(i+1));
									//alert("Please add comments for question ");
									document.getElementById('quesComment'+i).focus();
									return false;
								}
								
					/*var charLimit =  document.getElementById('quesLimit'+i).value;	
						if(eval(comment.length) > eval(charLimit)){
								alert("Add "+document.getElementById("appraisal.form.comment").innerHTML.toLowerCase() +" upto "+eval(charLimit)+" characters for "+document.getElementById("appraisal.form.ques.desc").innerHTML.toLowerCase()+" no. "+(i+1));
								document.getElementById('quesComment'+i).focus();
								//id.value=cmt.substring(0,50);
								return false;
							}*/
								
						}
				
				}
			return true;
}

function saveandnextFun(){
								
			var tableLength = document.getElementById("count").value;
				if(tableLength == 0 )
					{
				
						if(document.getElementById('paraFrm_nextExist').value == 'true'){	
							document.getElementById("paraFrm").action="ApprFormSection_next.action";
							document.getElementById("paraFrm").submit();
						}else{
						
							document.getElementById("paraFrm").action="ApprFormTrainingDtls_input.action";
							document.getElementById("paraFrm").submit();
						}
					}
			
			//if(! check())
				//return false;
				
			//document.getElementById("navigationButtons").disabled=true;
			document.getElementById("overlay").style.visibility = "visible";
			document.getElementById("overlay").style.display = "block";
			document.getElementById("paraFrm").action="ApprFormSection_saveAndNext.action";
			document.getElementById("paraFrm").submit();
		
}

function saveFun(){
	var tableLength = document.getElementById("count").value;
	alert(tableLength);	
			var tableLength = document.getElementById("count").value;
				if(tableLength == 0 )
					{
						alert("No data to save.");
						return false;
					}
			//if(! check())
				//return false;			
			
			//document.getElementById("navigationButtons").disabled=true;
			document.getElementById("overlay").style.visibility = "visible";
			document.getElementById("overlay").style.display = "block";
			document.getElementById("paraFrm").action="ApprFormSection_save.action";
			document.getElementById("paraFrm").submit();
				
}

function saveandpreviousFun(){
		
			var tableLength = document.getElementById("count").value;
				if(tableLength == 0 )
					{
					if(document.getElementById('paraFrm_previousExist').value == 'true'){	
						document.getElementById("paraFrm").action="ApprFormSection_previous.action";
						document.getElementById("paraFrm").submit();
					}else{
						document.getElementById("paraFrm").action="ApprFormInstruction_input.action";
						document.getElementById("paraFrm").submit();
					}
				}
				
			//if(! check())
				//return false;	
				
			//document.getElementById("navigationButtons").disabled=true;
			document.getElementById("overlay").style.visibility = "visible";
			document.getElementById("overlay").style.display = "block";	
			document.getElementById("paraFrm").action="ApprFormSection_saveAndPrevious.action";
			document.getElementById("paraFrm").submit();
				
}
function viewPreviousDetails(sectionCode,phaseCode,apprId,templateCode,empCode){

		var wind = window.open('','wind','width=900,height=400,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
		document.getElementById('paraFrm').target = "wind";
		document.getElementById('paraFrm').action = "ApprFormSection_retrivePreviousDetails.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";

}

function nextFun(){
	
		if(document.getElementById('paraFrm_nextExist').value == 'true'){	
			
			document.getElementById("paraFrm").action="ApprFormSection_next.action";
			document.getElementById("paraFrm").submit();
		}else{
			alert("not Next")
			document.getElementById("paraFrm").action="ApprFormTrainingDtls_input.action";
			document.getElementById("paraFrm").submit();
		}
		
}
function previousFun(){
		
		if(document.getElementById('paraFrm_previousExist').value == 'true'){	
				document.getElementById("paraFrm").action="ApprFormSection_previous.action";
				document.getElementById("paraFrm").submit();
		}else{
			document.getElementById("paraFrm").action="ApprFormInstruction_previousFirst.action";
			document.getElementById("paraFrm").submit();
		}
		
		
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
	
	var tableLength = document.getElementById("count").value;
				if(tableLength == 0 )
					{
						alert("Please add the questions to this section.");
						return false;
					}
						
		if(! check()){
			return false;	
		}
			
		/*if(document.getElementById('paraFrm_detailFLag').value == 'false')	
			var conf = confirm('Do you want to Finish your '+document.getElementById('appraisal.form.head').innerHTML+' ?\nClick OK to Finish.');
		else
			var conf = confirm('Do you want to Finish this '+document.getElementById('appraisal.form.head').innerHTML+' ?\nClick Ok to Finish.');
			
			if(conf){*/
				document.getElementById("paraFrm").action="ApprFormSection_forwardAppraisal.action";
				document.getElementById("paraFrm").submit(); 
			//}
	
}
//Round number for Two decimal
function roundNumber(num,dec)
{
	var result=Math.round(num*Math.pow(10,dec))/Math.pow(10,dec);
	return result;
}
function netAverage(){
		
			var tableLength = document.getElementById("count").value;
			var outOf=0;
			if(document.getElementById('paraFrm_ratingType').value =='perc')
				outOf=100;
			else
				outOf=document.getElementById('paraFrm_maxWeightage').value;	
			
			var average=0;
			for(i=0;i<tableLength;i++){
				
				var weightage=document.getElementById("quesWeight"+i).value
				var rating=document.getElementById("quesRating"+i).value
			
				if(rating != "")
					average=(weightage * rating)/eval(outOf);
				else
					average=0;
			
				//document.getElementById("quesAvg"+i).value=average;
				document.getElementById("quesAvg"+i).value=roundNumber(average,2);
			}
			
			getTotal();
}
function getTotal(){
			
			
			var tableLength = document.getElementById("count").value;
			var totalWt=0;
			var totalAvg=0;
			
			for(i=0;i<tableLength;i++){
			
				var weightage=document.getElementById("quesWeight"+i).value;
				var avg=document.getElementById("quesAvg"+i).value;
				
				totalWt= eval(totalWt)+ eval(weightage);
				totalAvg= eval(totalAvg)+ eval(avg);
				
			}
			document.getElementById("paraFrm_totalWeightage").value=totalWt;
			document.getElementById("paraFrm_totalAvg").value=Math.round(totalAvg);
}
onload();
function onload(){
		var check = document.getElementById("paraFrm_ratingFlag").value
		if(check == 'true'){
			netAverage();
			getTotal();
		}
		setTextLengthOnLoad();
}
function callCheck(id){
		try{
		
		var aa= eval(document.getElementById('quesRating'+id).value);
	
		}catch(e){alert(e);}
}


/*function setMaxLength(obj)
{
	try {
	if(document.getElementById('label'+obj.id).innerHTML == "")
	{
		document.getElementById('label'+obj.id).innerHTML=document.getElementById('limit'+obj.id).value;
		return false;
	}
	var maxLen = eval(document.getElementById('limit'+obj.id).value);
	document.getElementById('label'+obj.id).innerHTML=maxLen-obj.value.length;
		
		if((maxLen - obj.value.length) >= 0){
				
				document.getElementById(obj.id).style.background = '#FFFFFF';
			}
		else{
				
				document.getElementById(obj.id).style.background = '#FFFF99';
			}
			
	}catch(e)
	{
	}
}*/

function setTextLengthOnLoad()
{
	try {
	var rowCount = document.getElementById("count").value;
	for(i=0;i<rowCount;i++)
	{
		var textLen = document.getElementById('quesComment'+i).value.length;
		document.getElementById('labelquesComment'+i).innerHTML=document.getElementById('limitquesComment'+i).value-textLen;
		
		if(document.getElementById('limitquesComment'+i).value-textLen < 0)
			document.getElementById('quesComment'+i).style.background = '#FFFF99';
		else
			document.getElementById('quesComment'+i).style.background = '#FFFFFF';
	}
	}catch(e)
	{
	}
}
</script>
