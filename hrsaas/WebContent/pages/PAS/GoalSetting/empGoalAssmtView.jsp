<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="EmpGoalAssessment" validate="true" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<s:hidden name="isSignOffRequired" />
		<s:hidden name="hiddenassessmentType" />
		<s:hidden name="reassessmentlevel" />
		<s:hidden name="listType" />
		<s:hidden name="ratingrangedesc" />
		<s:hidden name="isGoalCategory" />
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Employee
					Goal Assessment</strong></td>
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
			<td colspan="5" width="100%">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td><b>Employee Details</b>
					<table width="98%" border="0" align="center" cellpadding="2"
						cellspacing="1">
						<tr>
							<td width="14%" colspan="1" height="20" class="formtext"><label
								name="employee.id" class="set" id="employee.id"
								ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label>
							:</td>
							<td width="15%" colspan="1" height="20"><s:hidden
								name="empId"></s:hidden><s:hidden name="empToken"></s:hidden><s:property
								value="empToken" /></td>
							<td width="4%" colspan="1" height="20"></td>
							<td width="16%" colspan="1" height="20" class="formtext"><label
								name="employee" class="set" id="employee"
								ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
							:</td>
							<td width="22%" colspan="1" height="20"><s:property
								value="empName" /><s:hidden name="empName"></s:hidden></td>
						</tr>
						<tr>
							<td width="14%" colspan="1" height="20" class="formtext"><label
								name="branch" class="set" id="branch"
								ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
							:</td>
							<td width="15%" colspan="1" height="20"><s:property
								value="empBrn" /><s:hidden name="empBrn"></s:hidden></td>
							<td width="4%" colspan="1" height="20"></td>
							<td width="16%" colspan="1" height="20"><label
								name="department" class="set" id="department"
								ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
							:</td>
							<td width="22%" colspan="1" height="20"><s:property
								value="empDept" /><s:hidden name="empDept"></s:hidden></td>
						</tr>
						<tr>
							<td width="14%" colspan="1" height="20" class="formtext"><label
								name="designation" class="set" id="designation"
								ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
							:</td>
							<td width="15%" colspan="1" height="20"><s:property
								value="empDesg" /><s:hidden name="empDesg"></s:hidden></td>
							<td width="4%" colspan="1" height="20"></td>
							<td width="16%" colspan="1" height="20"><label
								name="reporting.to" class="set" id="reporting.to"
								ondblclick="callShowDiv(this);"><%=label.get("reporting.to")%></label>
							:</td>
							<td width="22%" colspan="1" height="20"><s:property
								value="empReportingTo" /><s:hidden name="empReportingTo"></s:hidden></td>
						</tr>
						<tr>
							<td width="14%" colspan="1" height="20" class="formtext"><label
								name="doj" class="set" id="doj" ondblclick="callShowDiv(this);"><%=label.get("doj")%></label>
							:</td>
							<td width="15%" colspan="1" height="20"><s:property
								value="empDoj" /><s:hidden name="empDoj"></s:hidden></td>
							<td width="4%" colspan="1" height="20"></td>
							<td width="16%" colspan="1" height="20"><label
								name="goalassessment.goalname" class="set"
								id="goalassessment.goalname" ondblclick="callShowDiv(this);"><%=label.get("goalassessment.goalname")%></label>
							:</td>
							<td width="22%" colspan="1" height="20"><s:property
								value="goalName" /><s:hidden name="goalName"></s:hidden></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td><b>Employee Goal Details</b>
					<table width="98%" border="0" align="center" cellpadding="2"
						cellspacing="2">
						<tr>
							<td class="formth" width="5%"><label class="set"
								name="goalassessment.srno" id="goalassessment.srno"
								ondblclick="callShowDiv(this);"><%=label.get("goalassessment.srno")%></label></td>							
							<td class="formth" width="10%" align="left"><label
								name="goalassessment.goaldesc" class="set"
								id="goalassessment.goaldesc" ondblclick="callShowDiv(this);"><%=label.get("goalassessment.goaldesc")%></label></td>
							<td class="formth" width="10%" align="left"><label
								name="goalassessment.goalweightage" class="set"
								id="goalassessment.goalweightage"
								ondblclick="callShowDiv(this);"><%=label.get("goalassessment.goalweightage")%></label></td>
							<s:if test="isGoalCategory">
							<td class="formth" width="20%" align="left"><label
									name="goal.category" class="set" id="goal.category"
									ondblclick="callShowDiv(this);"><%=label.get("goal.category")%></label></td>
							<td class="formth" width="20%" align="left"><label
									name="goalCategoryWeightage" class="set"
									id="goalCategoryWeightage" ondblclick="callShowDiv(this);"><%=label.get("goalCategoryWeightage")%></label></td>
							</s:if>
							<td class="formth" width="10%" align="left"><label
								name="goalassessment.goalachdate" class="set"
								id="goalassessment.goalachdate" ondblclick="callShowDiv(this);"><%=label.get("goalassessment.goalachdate")%></label></td>
							<td class="formth" width="10%" align="left"><label
								name="goalassessment.previousrating" class="set"
								id="goalassessment.previousrating"
								ondblclick="callShowDiv(this);"><%=label.get("goalassessment.previousrating")%></label></td>
							<td class="formth" width="10%" align="left"><label
								name="goalassessment.previouscomment" class="set"
								id="goalassessment.previouscomment"
								ondblclick="callShowDiv(this);"><%=label.get("goalassessment.previouscomment")%></label></td>

							<td class="formth" width="10%" align="left"><label
								name="goalassessment.goalappcomment" class="set"
								id="goalassessment.goalappcomment"
								ondblclick="callShowDiv(this);"><%=label.get("goalassessment.goalappcomment")%></label></td>
							<s:if test="isGoalAchievedFlag">
								<td class="formth" width="10%" align="left"><label
									name="goal.achieved" class="set" id="goal.achieved"
									ondblclick="callShowDiv(this);"><%=label.get("goal.achieved")%></label></td>
							</s:if>
							<td class="formth" width="10%" colspan="3" align="left">Previous
							Details</td>
						</tr>
						<%
						int j = 1, c = 0;
						%>
						<s:iterator value="goalList">
							<s:if test="goalDtlStatus=='A_'">
								<tr>
									<td width="5%" align="center" class="td_bottom_border"><%=j%></td>									
									<td width="10%" align="left" class="td_bottom_border"><s:hidden
										name="goalDtlStatus" /><s:hidden name="individualGoalId" /><s:property
										value="goalDescriptionAbbr" /><input type="hidden"
										name="goalDescription" id="goalDescription<%=j%>"
										value="<s:property
										value="goalDescription" />" /><br><img
													src="../pages/images/zoomin.gif" height="10"
													align="right" width="10" theme="simple"
													onclick="javascript:callShowWindow('goalDescription<%=j%>','goalassessment.goaldesc','')">
									
									</td>
									<td width="10%" align="left" class="td_bottom_border"><s:property
										value="weightage" /><s:hidden name="weightage"></s:hidden>&nbsp;</td>
									 <s:if test="isGoalCategory">
									 <td width="10%" align="left" class="td_bottom_border"><s:property
											value="goalCategoryNameList" /><input type="hidden"
											name="goalCategoryNameList" id="goalCategoryNameList<%=j%>"
											value="<s:property
											value="goalCategoryNameList" />" />&nbsp;<!--<br><img
													src="../pages/images/zoomin.gif" height="10"
													align="right" width="10" theme="simple"
													onclick="javascript:callShowWindow('goalCategoryNameList<%=j%>','goal.category','')">
										-->
										</td>
										<td width="10%" align="left" class="td_bottom_border"><s:hidden
											name="goalCategoryWeightage" /><s:property
											value="goalCategoryWeightage" />&nbsp;</td>
									</s:if>
									<td width="10%" align="center" class="td_bottom_border"><s:property
										value="goalAchDate" /><s:hidden name="goalAchDate"></s:hidden>&nbsp;</td>

									<td width="10%" align="center" class="td_bottom_border"><s:property
										value="selfRating" /><s:hidden name="selfRating"></s:hidden>&nbsp;</td>

									<td width="10%" align="left" class="td_bottom_border"><s:property
										value="selfCommentsAbbr" /><input type="hidden"
										name="selfComments" id="selfComments<%=j%>"
										value="<s:property
										value="selfComments" />" /><br><img
													src="../pages/images/zoomin.gif" height="10"
													align="right" width="10" theme="simple"
													onclick="javascript:callShowWindow('selfComments<%=j%>','goalassessment.previouscomment','')">									
									</td>
									<td width="10%" align="left" class="td_bottom_border"><s:property
										value="applicantCommentsAbbr" /><input type="hidden"
										name="applicantComments" id="applicantComments<%=j%>" value="<s:property
										value="applicantComments" />" /><br><img
													src="../pages/images/zoomin.gif" height="10"
													align="right" width="10" theme="simple"
													onclick="javascript:callShowWindow('applicantComments<%=j%>','goalassessment.goalappcomment','')">																			
									</td>									
									<s:if test="isGoalAchievedFlag">
										<td width="10%" align="left" class="td_bottom_border"><s:property
											value="goalAchievedList" />&nbsp;</td>
									</s:if>
									<s:hidden name="goalAchievedList" />
									<td class="td_bottom_border" width="35%" colspan="3">
									<table width="100%">
										<tr>
											<td class="formth" width="10%">Level</td>
											<td class="formth" width="10%" align="left">Rating</td>
											<td class="formth" width="80%" align="left">Comments</td>
										</tr>
										<%int srNo=0; %>
										<s:iterator value="managerRatingList">
										<tr>
											<td width="10%" align="center" class="sortableTD"><%=++srNo %> </td>
											<s:if test="accessorFlag">
											
												<td width="10%" align="center" class="sortableTD"><s:hidden name="hiddengoaldtlid"/><s:if test="empGoalAssessment.goalRatingAccess"><s:select name="goalRating" headerKey="0"
													headerValue="Select"  list="tmap"/></s:if><s:elseif test="empGoalAssessment.listType=='processed'"><s:property value="managerRating"/></s:elseif></td>
												<td width="25%" align="left" class="sortableTD"><s:if test="empGoalAssessment.goalCommentsAccess"><s:textarea  name="comment" cols="35" rows="3" /></s:if><s:elseif test="empGoalAssessment.listType=='processed'"><s:property value="managerComments"/></s:elseif></td>											
											</s:if>
											<s:else>
												<td width="10%" align="center" class="sortableTD"><s:property value="managerRating"/></td>
												<td width="80%" align="left" class="sortableTD"><s:property value="managerComments"/></td>
											</s:else>
										</tr>
									</s:iterator>
									</table>
									</td>
									<%
										j++;
										c++;
									%>
								</tr>
							</s:if>
							<!--<s:else>
					 		<tr>
								<td width="5%" align="center" class="sortableTD"><j%></td>
								<td width="10%" align="left" class="sortableTD"><s:hidden name="goalDtlStatus"/><s:hidden name="individualGoalId"></s:hidden><s:hidden name="goalDescription"></s:hidden><strike><s:property value="goalDescription"/></strike></td>
								<td width="10%" align="center" class="sortableTD"><strike><s:property value="goalAchDate"/></strike><s:hidden name="goalAchDate"></s:hidden>&nbsp;</td>						
								<td width="10%" align="center" class="sortableTD"><strike><s:property value="selfRating"/></strike><s:hidden name="selfRating"></s:hidden>&nbsp;</td>						
								<td width="10%" align="left" class="sortableTD"><strike><s:property value="selfComments"/></strike><s:hidden name="selfComments"></s:hidden>&nbsp;</td>						
								<td width="10%" align="left" class="sortableTD"><strike><s:property value="applicantComments"/></strike><s:hidden name="applicantComments"></s:hidden>&nbsp;</td>												
								<td width="10%" align="left" class="sortableTD"><strike><s:property value="weightage"/></strike><s:hidden name="weightage"></s:hidden>&nbsp;</td>						
								<td class="sortableTD" width="35%" colspan="2">
								<table width="100%">
								 	<tr>
										<td class="formth" width="10%"></td>
										<td class="formth" width="45%" align="left">Rating</td>
										<td class="formth" width="45%" align="left">Comments</td>
										<s:iterator value="managerRatingList">
										<tr>
											<td width="10%" align="left">Manager-1</td>
											<td width="45%" align="left"><s:property value="managerRating"/></td>
											<td width="45%" align="left"><s:property value="managerComments"/></td>
										</tr>
										</s:iterator>
								 </table>
								</td>											
								<s:if test="empGoalAssessment.goalRatingAccess"><td width="10%" align="center" class="sortableTD" ><s:property value="goalRating"/><s:hidden name="goalRating"/> &nbsp;</td></s:if>
								<s:if test="empGoalAssessment.goalCommentsAccess"><td width="25%" align="left" class="sortableTD"><s:property value="comment"/><s:hidden name="comment" />&nbsp;</td></s:if>
								<s:if test="isGoalAchievedFlag">
								<td width="10%" align="left" class="sortableTD" ><s:property value="goalAchievedList"/>&nbsp;</td>
								</s:if>	<s:hidden name="goalAchievedList" /> 					
						 		<j++; c++;%>
					 	  </tr>
					 	  </s:else>-->
						</s:iterator>
						<tr>
							<td><input type="hidden" name="count" id="count"
								value="<%=c%>" /></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="4" width="100%"><b> Note: </b><s:property
				value="ratingrangedesc" /></td>
		</tr>
		<!-- 
		<tr>
			<td colspan="4" width="100%">1.<label  class = "set" name="goalrating1"  id="goalrating1" ondblclick="callShowDiv(this);"><%=label.get("goalrating1")%></label></td>
		</tr>
		<tr>
			<td colspan="4" width="100%">2.<label  class = "set" name="goalrating2"  id="goalrating2" ondblclick="callShowDiv(this);"><%=label.get("goalrating2")%></label></label></td>
		</tr>
		<tr>
			<td colspan="4" width="100%">3.<label  class = "set" name="goalrating3"  id="goalrating3" ondblclick="callShowDiv(this);"><%=label.get("goalrating3")%></label></td>
		</tr>
		<tr>
			<td colspan="4" width="100%">4.<label  class = "set" name="goalrating4"  id="goalrating4" ondblclick="callShowDiv(this);"><%=label.get("goalrating4")%></label></td>
		</tr>
		<tr>
			<td colspan="4" width="100%">5.<label  class = "set" name="goalrating5"  id="goalrating5" ondblclick="callShowDiv(this);"><%=label.get("goalrating5")%></label></td>
		</tr>
	 -->
		<!-- Proposed Score Start -->
		<s:hidden name="divScoreDet" id="divScoreDet" />
		<tr>
			<td colspan="3">
			<div id='div_previewApprIdFinalRating'
				style='position: absolute; z-index: 3; width: 350px; height: 120px; visibility: hidden; border: 2px solid; top: 200px; left: 200px; padding: 10px;'
				class="formbg">
			<table width="100%">
				<tr>
					<td width="93%" colspan="5" style="cursor: move"
						onmouseout="Drag.end();"
						onmouseover="Drag.init(document.getElementById('div_previewApprIdFinalRating'), null, 0, 350, 0, 600);">
					<b> <label id="moduleName" style="cursor: move" />Projected
					Goal Score </b></td>

				</tr>

				<tr>
					<td width="10%">&nbsp;</td>
					<td width="20%">&nbsp;</td>
					<td width="25%">Final Score</td>
					<td width="25%"><s:hidden name="totalScore" /><s:property
						value="finalRating" /></td>
					<td width="20%">&nbsp;</td>
				</tr>
				<tr height="100">
					<td width="100%" colspan="5"><input type="button" name="ok"
						id="hiddenButton1" value=" OK " onclick="finishAppraisal();" /> <input
						type="button" name="cancel" value=" Cancel " id="hiddenButton2"
						onclick="hideApprRating();" /></td>
				</tr>
			</table>
			</div>
			</td>
		</tr>
		<!-- Proposed Score End -->
		<!-- ================================== -->
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
		<s:hidden name="confgoalId" />
		<s:hidden name="goalRatingAccess" />
		<s:hidden name="goalCommentsAccess" />
		<s:hidden name="accessorFlag" />
		<s:hidden name="goalAssessmentId" />
		<s:hidden name="assessLevel" />
		<s:hidden name="reportingType" />
		<s:hidden name="source" id="source" />
		<s:hidden name="goalId"></s:hidden>
	</table>
	<div id="textDiv"
		style='position: absolute; z-index: 3; width: 370px; height: 120px; display: none; border: 2px solid; top: 70px; left: 200px; padding: 10px'
		class="formbg">
	<table width="100%" border="0" cellpadding="2" cellspacing="1">
		<tr>
			<td width="35%" colspan="3" align="center" class="formth"
				style="cursor: move" onmouseout="Drag.end();"
				onmouseover="Drag.init(document.getElementById('textDiv'), null, 0, 350, 0, 700);">
			<b>Details</b></td>
			<td width="3%" align="right" border="1" class="formth"
				style="font-family: Arial; cursor: pointer" onclick="hideDiv();">
			<b>X</b></td>
		</tr>
		<tr>
			<td>
			<div style="overflow-y: auto; height: 50px; top: 30px" id="callDiv">
			</div>
			</td>
		</tr>
	</table>
	</div>
</s:form>
<script type="text/javascript"><!--
function saveFun(){
var conf=confirm("Do you really want to save these ratings?");
  			if(!conf) 
  			{
  				return false;
  			}
		document.getElementById('paraFrm').action = 'EmpGoalAssessment_saveRating.action';
		document.getElementById('paraFrm').submit();
}

function finalizeFun(){

var ratingValidationFlag=false;
var ratingVal=document.getElementsByName('goalRating');
//alert("Total length : "+ratingVal.length)
//var totalRows=document.getElementById("count").value;
//alert("totalRows :: "+totalRows);
for(var j=0;j<ratingVal.length;j++)
{
	//alert("ratingVal["+j+"]"+ratingVal[j].value);
	if(ratingVal[j].value=="0")
	{
		ratingValidationFlag=true;
		break;
	}
}
if(ratingValidationFlag)
{
	alert("Please select rating");
	return false;
}

var conf=confirm("Do you really want to finalize these ratings?");
  			if(!conf) 
  			{
  				return false;
  			}
		document.getElementById('paraFrm').action = 'EmpGoalAssessment_finalizeAssessment.action';
		document.getElementById('paraFrm').submit();
}

function cancelFun(){
		document.getElementById('paraFrm').target = "_self";
	if(document.getElementById('source').value=='mymessages')
		{
		document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
		}
		else if(document.getElementById('source').value=='myservices')
		{
		document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_serviceData.action';
		}else{
			var listType=document.getElementById('paraFrm_listType').value;
			if(listType=="reassessment"){
			document.getElementById('paraFrm').action = 'EmpGoalAssessment_getReassessmentList.action';
			}else if(listType=="processed"){
			document.getElementById('paraFrm').action = 'EmpGoalAssessment_getProcessedList.action';
			}
			else{
			document.getElementById('paraFrm').action = 'EmpGoalAssessment_input.action';
			}
		
		}
	document.getElementById('paraFrm').submit();
}

function backFun(){
try{
	document.getElementById('paraFrm').target = "_self";
	if(document.getElementById('source').value=='mymessages')
		{
		document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
		}
		else if(document.getElementById('source').value=='myservices')
		{
		document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_serviceData.action';
		}else{
			var listType=document.getElementById('paraFrm_listType').value;
			if(listType=="reassessment"){
			document.getElementById('paraFrm').action = 'EmpGoalAssessment_getReassessmentList.action';
			}else if(listType=="processed"){
			document.getElementById('paraFrm').action = 'EmpGoalAssessment_getProcessedList.action';
			}else{
			document.getElementById('paraFrm').action = 'EmpGoalAssessment_input.action';
			}
		
		}
	document.getElementById('paraFrm').submit();
	}catch(e){
	alert(e);
	}
	
}
function signoffFun(){
	var conf=confirm("Do you want to sign off the evaluation?");
  	if(!conf) {
  		return false;
  	}else{
		document.getElementById('paraFrm').action = 'EmpGoalAssessment_signOff.action';
		document.getElementById('paraFrm').submit();
	}

}


function hideApprRating()
					{
						document.getElementById('div_previewApprIdFinalRating').style.visibility = 'hidden';
						document.getElementById('divScoreDet').value="false";
						document.getElementById('hiddenButton1').style.display = 'none';
							document.getElementById('hiddenButton2').style.display= 'none';
							
}
					
previewOnload();
					function previewOnload()
					{
						if(document.getElementById('divScoreDet').value=="true")
						{
							document.getElementById('div_previewApprIdFinalRating').style.visibility = 'visible';
							document.getElementById('hiddenButton1').style.visibility = 'visible';
							document.getElementById('hiddenButton2').style.visibility = 'visible';
							document.getElementById('hiddenButton1').style.display = '';
							document.getElementById('hiddenButton2').style.display= '';
							document.getElementById('textDiv').style.display = 'none';
						}
						else{
							document.getElementById('hiddenButton1').style.display = 'none';
							document.getElementById('hiddenButton2').style.display= 'none';
						}
					}
function finishAppraisal()
					{
			document.getElementById("paraFrm").action="EmpGoalAssessment_updateGoal.action";
	    	document.getElementById("paraFrm").submit();
        	document.getElementById('div_previewApprIdFinalRating').style.visibility = 'hidden';
							}
						
//function to show new window on click of zoom image
	function callShowWindow(fieldName,windowName,readFlag) {
	try{
		mytitle=document.getElementById(windowName).innerHTML;
		 if (window.event){
		window.open('../pages/common/textAreaPopUp.jsp?fieldName='+fieldName+'&windowName='+mytitle+'&readFlag='+readFlag+'','','width=580,height=430,scrollbars=no,resizable=no,top=250,left=350');
		 }else{
		 window.open('../pages/common/textAreaPopUp.jsp?fieldName='+fieldName+'&windowName='+mytitle+'&readFlag='+readFlag+'','','width=650,height=450,scrollbars=no,resizable=no,top=250,left=350');
		 }
		document.getElementById('paraFrm').target ="";	  
		}catch(e){
		
		}
	}

</script>
