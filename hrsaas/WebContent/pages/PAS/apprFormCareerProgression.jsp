
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<%

 Object data[][] = (Object[][])request.getAttribute("data"); //PREVIOUS PHASES DATA

%>

<s:form action="ApprFormTrainingDtls" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="source" id="source"/>
	<s:hidden name="phaseId" />
	<s:hidden name="phaseSequence" />
	<input type="hidden" name="delTrngCode" id="delTrngCode" />
	<input type="hidden" name="hSno" id="hSno" />
	<s:hidden name="removeTrngCode" />
	<s:hidden name="isSelf" />
	<s:hidden name="showCommentFlag" />
	<s:hidden name="isCommentsApp" id="isCommentsApp"/>
	<s:hidden name="isAddButtonPress" />
	<s:hidden name="sourceFormType" id="sourceFormType"/>
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head"><label name="appraisal.form.head" class = "set"  id="appraisal.form.head" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.head")%></label></strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="0">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td><s:hidden name="templateCode" /> <s:hidden
						name="apprValidTillDate" /> <s:hidden name="detailFLag" /> <s:hidden
						name="phaseForwardFlag" /> <s:hidden name="sectionCode" /> <s:hidden
						name="sectionList" /> <s:hidden name="nextExist" /> <s:hidden
						name="previousExist" /> <s:hidden name="mode" /> <b>
						<label name="appraisal.details" class = "set"  id="appraisal.details" ondblclick="callShowDiv(this);"><%=label.get("appraisal.details")%></label>
					</b>
					<table width="98%" border="0" align="center" cellpadding="2"
						cellspacing="2">

						<tr>
							<td width="18%" colspan="1" height="20" class="formtext"><label
								class="set" name="appraisal.form.period"
								id="appraisal.form.period" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.period")%></label>
							:</td>
							<td width="15%" colspan="1" height="20"><s:hidden
								name="apprId" /><s:hidden name="apprCode" /><s:property
								value="apprCode" /></td>
							<td width="10%" colspan="1" height="20"></td>
							<td width="50%" colspan="1" height="20"><label class="set"
								name="appr.from.date" id="appr.from.date"
								ondblclick="callShowDiv(this);"><%=label.get("appr.from.date")%></label>:
							<s:hidden name="apprStartDate" /><s:property
								value="apprStartDate" />&nbsp;&nbsp; <label class="set"
								name="appr.to.date" id="appr.to.date"
								ondblclick="callShowDiv(this);"><%=label.get("appr.to.date")%></label>:
							<s:hidden name="apprEndDate" /><s:property value="apprEndDate" /></td>
						</tr>
						<tr>
							<td width="18%" colspan="1" height="20" class="formtext"><label
								class="set" name="appraisal.form.phase"
								id="appraisal.form.phase" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.phase")%></label>
							:</td>
							<td width="15%" colspan="1" height="20"><s:hidden
								name="phaseCode" /><s:hidden name="phaseName" /><s:property
								value="phaseName" /></td>
							<td width="10%" colspan="1" height="20"></td>
							<td width="50%" colspan="1" height="20"><label class="set"
								name="phase.from.date" id="phase.from.date"
								ondblclick="callShowDiv(this);"><%=label.get("phase.from.date")%></label>:
							<s:hidden name="phaseStartDate" /><s:property
								value="phaseStartDate" />&nbsp;&nbsp; <label class="set"
								name="phase.to.date" id="phase.to.date"
								ondblclick="callShowDiv(this);"><%=label.get("phase.to.date")%></label>:
							<s:hidden name="phaseEndDate" /><s:property value="phaseEndDate" /><s:hidden
								name="phaseLockFlag" /><s:hidden name="quesWtDisplayFlag" /></td>
						</tr>
						<tr>
							<td width="18%" colspan="1" height="20" class="formtext"><label
								class="set" name="employee" id="employee"
								ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
							:</td>
							<td width="15%" colspan="1" height="20"><s:hidden
								name="empId" /><s:hidden name="empName" /><s:property
								value="empName" /></td>
							<td width="10%" colspan="1" height="20"></td>
							<td width="50%" colspan="1" height="20"><label class="set"
								name="designation" id="designation"
								ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:
							<s:hidden name="empDesgName" /><s:property value="empDesgName" /></td>
						</tr>

					</table>
					</td>
				</tr>

			</table>
			</td>
		</tr>
		<input type="hidden" value="<s:property value="phaseSequence" />">

		<!-- FOR SELF PHASE ONLY THE appraisee could add training attended details -->

		<tr>
			<td colspan="3">
			<table border="0" width="100%" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td><b><label class="set" name="career.progression" id="career.progression"
								ondblclick="callShowDiv(this);"><%=label.get("career.progression")%></label>
				 </b></td>

					<!--<s:if test="isSelf=='false'">
						<td align="right"><s:if test="flag">
							<a href="#" onclick="callAction();"> Previous Phase Details </a>
						</s:if></td>
					</s:if>
					--><script>
          
    		 /*function callAction(){
					var wind = window.open('','wind','width=800,height=200,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
					document.getElementById('paraFrm').target = "wind";
					document.getElementById('paraFrm').action = "ApprFormCareerProgression_previousPhaseCareerDtls.action";
					document.getElementById('paraFrm').submit();
					document.getElementById('paraFrm').target = "main";
         	 }*/
         </script>
				</tr>
				<tr>
					<td colspan="2">
					<table width="98%" class="formbg" align="center" cellpadding="2"
						cellspacing="2">
						<tr>
							<td class="formth" width="5%"><label name="career.sno"
								class="set" id="career.sno" ondblclick="callShowDiv(this);"><%=label.get("career.sno") %></label>:
							</td>
							<td class="formth" width="30%"><label name="career.desc"
								class="set" id="career.desc" ondblclick="callShowDiv(this);"><%=label.get("career.desc") %></label>:
							</td>
							<s:if test="commentFlag=='true'">
							<td class="formth" width="30%"><label name="career.comments"
								class="set" id="career.comments" ondblclick="callShowDiv(this);"><%=label.get("career.comments") %></label>:
							</td>
							</s:if>
							<td width="15%" class="formth">&nbsp; </td>
						</tr>
						<%int i=0; %>
						<%!int TotalCount=0; %>
						<s:if test="phaseForwardFlag=='false'">
							<s:iterator value="careerList">
								<tr>
									<td width="5%" align="center" class="td_bottom_border"><%=++i%>
									<input type="hidden" name="sNo" value="<%=i%>" /> <input
										type="hidden" name="questionCode" id="questionCode<%=i %>"
										value="<s:property value="questionCode" />" /> <input
										type="hidden" name="careerId"
										value="<s:property value="careerId" />" /> <input
										type="hidden" name="careerIdComment"
										value="<s:property value="careerIdComment" />" /></td>
									<td width="35%" align="left" class="td_bottom_border"><input
										type="hidden" name="questionDesc"
										value='<s:property value="questionDesc"/>' theme="simple" />


									<s:property value="questionDesc" /></td>
									<s:if test="commentFlag=='true'">
										<td width="35%" align="left" class="td_bottom_border"><textarea
											rows="2" cols="45" name="careerComment"
											onkeyup="return textCounter(this,<s:property value="charLimit" />)"><s:property
											value="careerComment" /></textarea> (<s:property value="charLimit" />)
										<input type="hidden" name="charLimit"
											value="<s:property value='charLimit' />" /></td>
									</s:if>
										<td width="15%" class="td_bottom_border"> 
										<s:if test="apprFormCareerProg.detailFLag">
										<a  href="#" onclick="showPreviouComments('<%=i%>');" >Show/Hide
										</s:if>
										
										
										</td>
								</tr>
								<s:if test="apprFormCareerProg.detailFLag">
								
								<tr id='<%="hiddenID"+i%>'>
									<td colspan="5">
									<table width="100%" class="formbg" align="center" cellpadding="0"
										cellspacing="0" border="0">
										<tr>
											<td class="formth" width="5%"><label name="career.sno"
												class="set" id="career.sno" ondblclick="callShowDiv(this);"><%=label.get("career.sno") %></label>:
											</td>
											<td class="formth" width="20%"><label name="appraisal.form.phase1"
												class="set" id="appraisal.form.phase" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.phase") %></label>:
											</td>
											<td class="formth" width="20%"><label name="appraisal.form.appraiser.name1"
												class="set" id="appraisal.form.appraiser.name" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.appraiser.name") %></label>:
											</td>
											<td width="30%" class="formth"><label name="trng.ques.comments1"
												class="set" id="trng.ques.comments" ondblclick="callShowDiv(this);"><%=label.get("trng.ques.comments") %></label> </td>
											<td width="10%">&nbsp;</td>
										</tr>
										<%int ii=1; %>
										<s:iterator value="careerListPrev">
										<tr >
											<td class="td_bottom_border" width="5%"><%=ii++ %>  </td>
											<td class="td_bottom_border" width="15%"> <s:property value="phaseNamePrev" /></td>
											<td class="td_bottom_border" width="15%"> <s:property value="appraiserNamePrev" /></td>
											<td width="30%" class="td_bottom_border"> <s:property value="commentsPrev" /></td>
											<td width="10%">&nbsp;</td>
										</tr>
										</s:iterator>
										
								</table>
								</td>
			                 </tr>
								
							</s:if>	
								
							</s:iterator>
							<%TotalCount=i; %>
							<input type="hidden" id="rows" value="<%=i%>">
						</s:if>
						<s:else>
							<s:iterator value="careerList">
								<tr>
									<td width="5%" align="center" class="td_bottom_border"><%=++i%>
									<input type="hidden" name="sNo" value="<%=i%>"> <input
										type="hidden" name="questionCode" id="questionCode<%=i %>"
										value="<s:property value="questionCode" />"> <input
										type="hidden" name="careerId"
										value="<s:property value="careerId" />"> <input
										type="hidden" name="careerIdComment"
										value="<s:property value="careerIdComment" />"></td>
									<td width="45%" align="left" class="td_bottom_border"><s:property
										value="questionDesc" /></td>
									<td width="50%" align="left" class="td_bottom_border"><textarea
										rows="2" cols="45" name="careerComment"
										onkeyup="return textCounter(this,<s:property value="charLimit" />)"><s:property
										value="careerComment" /></textarea> (<s:property value="charLimit" />)</td>
								</tr>
							</s:iterator>
						</s:else>
						<%if(i==0){ %>
						<s:if test="phaseForwardFlag=='false'">
							<tr>
								<td colspan="3" align="center"><font color="red">No
								Data To Display</font></td>
							</tr>
						</s:if>
						<s:else>
							<tr>
								<td colspan="3" align="center"><font color="red">No
								Data To Display</font></td>
							</tr>
						</s:else>
						<%} %>
						<script>
           
           				function showPreviouComments(id){							
								var temp = eval(id);
								var row = document.getElementById('hiddenID'+temp);
								//alert("check deepak : showPreviouComments : "+row.style.display);
							if (row.style.display == '') 
							{
								row.style.display = 'none';
								row.style.visibility = 'hidden';
							}								
							else 
								{
								row.style.display = '';
								row.style.visibility = 'visible';
							}
							}
							function showPreviouGenComments(id){							
								var row = document.getElementById(id);
							if (row.style.display == '') 
								{
								row.style.display = 'none';
								row.style.visibility = 'hidden';
							}	
							else 
							{
								row.style.display = '';
								row.style.visibility = 'visible';
							}
								
							}
							
           
           				
           
           
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
          
           </script>
					</table>
					</td>
				</tr>
				<!-- ADDED FOR PREVIOUS COMMENTS -->
				
				
						
				
				
			</table>
			</td>
		</tr>

		<!-- general comments section starts -->

		<s:if test="isCommentsApp">
			<tr>
				<td colspan="3">
				<table border="0" width="100%" cellpadding="2" cellspacing="2"
					class="formbg">
					<tr>
						<td><strong><label  class = "set" name="career.general.comments"  id="career.general.comments" ondblclick="callShowDiv(this);"><%=label.get("career.general.comments")%></label></strong><s:if
							test="phaseForwardFlag=='false'">(Please click on Add Record button to Add General Comments)</s:if></td>
						<s:if test="phaseForwardFlag=='false'">
							<td><s:submit action="ApprFormCareerProgression_add"
								cssClass="add" value="Add Record" onclick="callAdd()" />
								<s:if test="apprFormCareerProg.detailFLag">
								<a href="#" onclick="showPreviouGenComments('hiddenCommentID');" >Show Previous Comments</a>
								</s:if>
								</td>
						</s:if>
					</tr>
					<tr>
						<td colspan="2">
						<table width="98%" class="formbg" align="center" cellpadding="2"
							cellspacing="2">
							<tr>
								<%
							Object[][] labelNameObj = null;
							try {
								labelNameObj = (Object[][]) request.getAttribute("labelNameObj");
							} catch (Exception e) {
								e.printStackTrace();
							}
						%>

								<%
										if (labelNameObj != null && labelNameObj.length > 0) {
										for (int val = 0; val < labelNameObj.length ; val++) {
								%>
								<td class="formth" align="center"><%= String.valueOf(labelNameObj[val][2])%>
								</td>
								<%
											}
										}
								%>
							</tr>

							<% 
						int noOfColsFrmDb=0;
						
						%>
						<% 
						int n = 0;
						String[] comData = null;
							try{
								 n=Integer.parseInt(request.getAttribute("cols").toString());
								 System.out.println("jsp--cols"+n);
								
								 noOfColsFrmDb=Integer.parseInt(request.getAttribute("noOfColsFrmDb").toString());
								 System.out.println("jsp--noOfColsFrmDb"+noOfColsFrmDb);
								 comData = (String[]) request.getAttribute("commentData");
								//System.out.println("in try n =="+n);
							}
							catch(Exception e){
								//System.out.println("catch----------"+e);
							}
							//System.out.println("---- n ===="+n);
							 %>

							<s:hidden name="columnCount" value="<%=String.valueOf(n)%>" />
							<s:if test="showCommentFlag">
								<% int c=0; %>
								<% if(noOfColsFrmDb!=0)
								{
								for( int p=0;p<(n/noOfColsFrmDb);p++){ %>
								<tr>
									<% for( int q=0;q<noOfColsFrmDb;q++){ %>
									<%if(c<n){ %>
									<td class="td_bottom_border">
									<%if(comData!=null){ %> <textarea cols="45" rows="3"
										name="tCom_<%=p%>_<%=q%>" id="tCom_<%=p%>_<%=q%>"><%=comData[c] %></textarea>
									<%}else { %> <textarea cols="45" rows="3"
										name="tCom_<%=p%>_<%=q%>" id="tCom_<%=p%>_<%=q%>"></textarea>
									<%} %>
									</td>
									<%c++; } %>
									<%} %><!-- End of for for <td> -->

								</tr>
								<%}
								} %><!-- End of for loop for <tr> -->
								
							</s:if>


					<tr id="hiddenCommentID">
						<td colspan="10">
						<s:if test="phaseForwardFlag=='false'">
						<table width="98%" class="formbg" align="center" cellpadding="2"
							cellspacing="2">
									<tr>
											<td class="formth" width="5%"><label name="career.sno1"
												class="set" id="career.sno" ondblclick="callShowDiv(this);"><%=label.get("career.sno") %></label>:
											</td>
											<td class="formth" width="20%"><label name="appraisal.form.phase11"
												class="set" id="appraisal.form.phase" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.phase") %></label>:
											</td>
											<td class="formth" width="20%"><label name="appraisal.form.appraiser.name11"
												class="set" id="appraisal.form.appraiser.name" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.appraiser.name") %></label>:
											</td>
											<s:iterator value="commentsList">
											<td width="20%" class="formth"><s:property value="commentsHead"/></td>
											</s:iterator>

										</tr>	
										<%int totalCount=1; %>
									<s:iterator value="commentsDataList">
									<tr >
											<td class="td_bottom_border" width="5%"> <%=totalCount++ %>  </td>
											<td class="td_bottom_border" width="20%"> <s:property value="phaseNamePrev" /></td>
											<td class="td_bottom_border" width="20%"> <s:property value="appraiserNamePrev" /></td>
											<s:iterator value="commentsDataSubList">
											<td width="20%" class="td_bottom_border"> <s:property value="commentsHead" /></td>
											</s:iterator>
										</tr>
									</s:iterator>	
															
						</td>
					</tr>		
					

						</table>
						</s:if>
						</td>
					</tr>
					
				</table>
				</td>
			</tr>
		</s:if>
		<!-- general comments section ends -->
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
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

				</tr>
			</table>
			</td>
		</tr>



	</table>

	<label></label>



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
							var conf;
							if(document.getElementById('paraFrm_isSelf').value=="true"){//SELF
								 
								 conf = confirm('Do you want to Finish your '+document.getElementById('appraisal.form.head').innerHTML+' ?\nClick OK to Finish.');
								 				 
							}else{//OTHER PHASES
							     
							     conf = confirm('Do you want to Finish this '+document.getElementById('appraisal.form.head').innerHTML+' ?\nClick Ok to Finish.');
								 
							}
			
							if(conf){
							document.getElementById("paraFrm").action="ApprFormSection_updateAppraisal.action";
							document.getElementById("paraFrm").submit();
							document.getElementById('div_previewApprIdFinalRating').style.display = 'none';
							}
			
			
						return false;
					}
					//Proposed Score ----- End ----
							onload();
							function onload(){	
							try
							{
							if(document.getElementById('isCommentsApp').value=="true")
							{
								document.getElementById('hiddenCommentID').style.display = 'none';	
								document.getElementById('hiddenCommentID').style.visibility = 'hidden';
							}				
							
							var countValue='<%=TotalCount%>';	
							//alert("countValue====="+countValue);
								for(var j=1;j<=countValue;j++){	
								if(document.getElementById('paraFrm_detailFLag').value=="true")
									{											
										document.getElementById('hiddenID'+j).style.display = 'none';
										document.getElementById('hiddenID'+j).style.visibility = 'hidden';
									}
								}
							}
							catch(e)
							{
								alert("error message "+e);
							}
							
								
							}

 
function callAdd()
{
 document.getElementById('isAddButtonPress').value=true;
}

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
	
			 var rows =document.getElementById('rows').value;
			 if(rows==0)
			 {
			 	alert("No Records To Save.");
			 	return false;
			 }
			document.getElementById("paraFrm").action="ApprFormCareerProgression_save.action";
			document.getElementById("paraFrm").submit();
			
		
		
}
 
function saveandnextFun(){
	
			document.getElementById("paraFrm").action="ApprFormCareerProgression_saveAndNext.action";
			document.getElementById("paraFrm").submit();
		
}

function saveandpreviousFun(){

			document.getElementById("paraFrm").action="ApprFormCareerProgression_saveAndPrevious.action";
			document.getElementById("paraFrm").submit();
		
}


function nextFun(){
	
			document.getElementById("paraFrm").action="ApprFormSection_next.action";
			document.getElementById("paraFrm").submit();
		
}

function previousFun(){
		
				document.getElementById("paraFrm").action="ApprFormDiscpMeasures_previous.action";
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
		/*	var conf;
			if(document.getElementById('paraFrm_isSelf').value=="true"){//SELF
				 
				 conf = confirm('Do you want to Finish your '+document.getElementById('appraisal.form.head').innerHTML+' ?\nClick OK to Finish.');
				 				 
			}else{//OTHER PHASES
			     
			     conf = confirm('Do you want to Finish this '+document.getElementById('appraisal.form.head').innerHTML+' ?\nClick Ok to Finish.');
				 
			}
			
			if(conf){	*/	
					document.getElementById('sourceFormType').value="CareerProgression";	
					document.getElementById("paraFrm").action="ApprFormCareerProgression_forwardAppraisal.action";
					document.getElementById("paraFrm").submit();			
				 /*}
			
			
			return false;*/
}



</script>
