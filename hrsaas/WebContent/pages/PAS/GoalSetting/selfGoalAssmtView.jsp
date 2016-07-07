<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="SelfGoalAssessment" validate="true" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<s:hidden name="signOffStatus" />
		<s:hidden name="confgoalId" />
		<s:hidden name="ratingrangedesc" />

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Self
					Goal Assessment </strong></td>
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

							<!--  <td width="16%" colspan="1" height="20"><label name="sch.assessment.date" class = "set"  id="sch.assessment.date" ondblclick="callShowDiv(this);"><%=label.get("sch.assessment.date")%></label> :</td>   
              <td width="22%" colspan="1" height="20"><s:property value="scheduledAssessmentDate" /></td>
              -->
						</tr>
						<s:hidden name="goalAssessmentId" />
						<s:hidden name="isGoalAchievedFlag"></s:hidden>
						<s:hidden name="isGoalCategory"></s:hidden>

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
							<td class="formth" width="20%" align="left"><label
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
									name="goalassesment.catWegt" class="set"
									id="goalassesment.catWegt" ondblclick="callShowDiv(this);"><%=label.get("goalassesment.catWegt")%></label></td>
							</s:if>							
							<td class="formth" width="15%" align="left"><label
								name="goalassessment.goalachdate" class="set"
								id="goalassessment.goalachdate" ondblclick="callShowDiv(this);"><%=label.get("goalassessment.goalachdate")%></label></td>																				
							<td class="formth" width="10%" align="left"><label
								name="goalassessment.goalcomment" class="set"
								id="goalassessment.goalcomment" ondblclick="callShowDiv(this);"><%=label.get("goalassessment.goalcomment")%></label></td>
							<td class="formth" width="10%" align="left"><label
								name="goalassessment.goalrating" class="set"
								id="goalassessment.goalrating" ondblclick="callShowDiv(this);"><%=label.get("goalassessment.goalrating")%></label></td>
							<s:if test="isGoalAchievedFlag">
								<td class="formth" width="10%" align="left"><label
									name="goal.achieved" class="set" id="goal.achieved"
									ondblclick="callShowDiv(this);"><%=label.get("goal.achieved")%></label></td>
							</s:if>	
							<td class="formth" width="20%" align="left"><label
								name="goalassessment.goalappcomment" class="set"
								id="goalassessment.goalappcomment"
								ondblclick="callShowDiv(this);"><%=label.get("goalassessment.goalappcomment")%></label></td>							
							<s:if test="listType=='processed'">

								<td class="formth" width="10%" align="left" colspan="3"><label
									name="manager.details" class="set" id="manager.details"
									ondblclick="callShowDiv(this);"><%=label.get("manager.details")%></label></td>
							</s:if>
						</tr>
						<%
						int j = 1, c = 0;
						%>
						<s:iterator value="goalList">
							<s:if test="goalDtlStatus=='A_'">
								<tr>
									<td width="5%" align="center" class="td_bottom_border"><%=j%></td>									
									<td width="10%" align="left" class="td_bottom_border"><s:hidden
										name="goalDtlStatus" /><s:hidden name="individualGoalId"></s:hidden><s:property
										value="goalDescriptionAbbr" /><input type="hidden"
										name="goalDescription" id="goalDescription<%=j%>" value="<s:property
										value="goalDescription" />" /><br><img
													src="../pages/images/zoomin.gif" height="10"
													align="right" width="10" theme="simple"
													onclick="javascript:callShowWindow('goalDescription<%=j%>','goalassessment.goaldesc','')">
									
									</td>
									<td width="10%" align="center" class="td_bottom_border"><s:hidden
										name="appWeightage" /><s:property value="appWeightage" />&nbsp;</td>
									
									<s:if test="isGoalCategory">
										<td width="10%" align="left" class="td_bottom_border"><s:hidden
											name="goalCategoryNameList" /><s:property
											value="goalCategoryNameList" />&nbsp;</td>
										<td width="10%" align="left" class="td_bottom_border"><s:hidden
											name="revieweWeight" /><s:property value="revieweWeight" />&nbsp;</td>
									</s:if>
									<td width="15%" align="center" class="td_bottom_border"><s:property
										value="goalAchDate" /><s:hidden name="goalAchDate"></s:hidden>&nbsp;</td>
									<td width="20%" align="left" class="td_bottom_border"><s:property value="appCommentsAbbr" />
										<input type="hidden"
										name="appComments" id="appComments<%=j%>" value="<s:property
										value="appComments" />" /><br><img
													src="../pages/images/zoomin.gif" height="10"
													align="right" width="10" theme="simple"
													onclick="javascript:callShowWindow('appComments<%=j%>','goalassessment.goalappcomment','')">
									
									</td>
									<td width="10%" align="left" class="td_bottom_border"><s:select
										name="goalRating" id="goalRating" headerKey="0"
										headerValue="Select" list="tmap" />&nbsp;</td>									
									<s:if test="isGoalAchievedFlag">
										<td width="10%" align="left" class="td_bottom_border"><s:select
											name="goalAchievedList" headerKey="" headerValue="Select"
											list="#{'Y':'Yes','N':'No','O':'On Going','P':'Partly achieved'}" />&nbsp;</td>
									</s:if>
									<s:else>
										<s:hidden name="goalAchievedList" />
									</s:else>
									
									<td width="10%" align="left" class="td_bottom_border"><s:textarea
										name="comment" id="commnet" cols="35" rows="3" />&nbsp;</td>

									<s:if test="selfGoalAssessment.listType=='processed'">
										<s:if test="managerRatingList!=null">
											<td class="td_bottom_border" width="35%" colspan="3">
											<table width="100%">
												<tr>
													<td class="formth" width="10%">Level</td>
													<td class="formth" width="10%" align="left">Rating</td>
													<td class="formth" width="80%" align="left">Comments</td>
												</tr>
												<%
												int srNo = 0;
												%>
												<s:iterator value="managerRatingList">
													<tr>
														<td width="10%" align="center" class="sortableTD"><%=++srNo%></td>

														<td width="10%" align="center" class="sortableTD"><s:property
															value="managerRating" /></td>
														<td width="80%" align="left" class="sortableTD"><s:property
															value="managerComments" /></td>

													</tr>

												</s:iterator>
											</table>

											</td>
										</s:if>
									</s:if>
									<%
										j++;
										c++;
									%>
								</tr>
							</s:if>
							<!--<s:else>
					<tr>
						<td width="5%" align="center" class="sortableTD"><=j%></td>
						<s:if test="isGoalCategory">
						<td width="10%" align="left" class="sortableTD"><s:hidden name="goalCategoryNameList"/><s:property value="goalCategoryNameList"/>&nbsp;</td>
						</s:if>
						
						<td width="10%" align="left" class="sortableTD"><s:hidden name="goalDtlStatus"/><s:hidden name="individualGoalId"></s:hidden><s:hidden name="goalDescription"></s:hidden><strike><s:property value="goalDescription"/></strike>&nbsp;</td>
						<td width="15%" align="center" class="sortableTD"><strike><s:property value="goalAchDate"/></strike><s:hidden name="goalAchDate"></s:hidden>&nbsp;</td>									
							<td width="20%" align="left" class="sortableTD"><s:hidden name="appComments" /><strike><s:property value="appComments"/></strike>&nbsp;</td>
								<td width="10%" align="center" class="sortableTD"><s:hidden name="appWeightage"/><strike><s:property value="appWeightage"/></strike>&nbsp;</td>
						<s:if test="isGoalAchievedFlag">
						<td width="10%" align="left" class="sortableTD" ><s:property value="goalAchievedList"/>&nbsp;</td>
						</s:if>	<s:else><s:hidden name="goalAchievedList" /> </s:else>
								<td width="10%" align="left" class="sortableTD" ><strike><s:property value="goalRating"/></strike><s:hidden name="goalRating" />&nbsp;</td>	
								<td width="10%" align="left" class="sortableTD"><strike><s:property value="comment"/></strike><s:hidden name="comment" />&nbsp;</td>	
						 <j++; c++;%>
				
						<s:if test="selfGoalAssessment.listType=='processed'">
					 <td class="sortableTD" width="35%" colspan="3">
								<table width="100%">
								 <tr>
									<td class="formth" width="10%">Level</td>
									<td class="formth" width="10%" align="left">Rating</td>
									<td class="formth" width="80%" align="left">Comments</td>
									</tr>
									<int srNo1=0; %>
									<s:iterator value="managerRatingList">
										<tr>
											<td width="10%" align="center" class="sortableTD"><=++srNo1 %></td>
											
												<td width="10%" align="center" class="sortableTD"><s:property value="managerRating"/></td>
												<td width="80%" align="left" class="sortableTD"><s:property value="managerComments"/></td>
											
										</tr>
									</s:iterator>
								</table>

						</td>
						</s:if>
						</tr>
				</s:else>
				-->
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
		<s:if test="esclationworkflowFlag">
			<tr>
				<td colspan="3" width="100%">
				<table width="100%" border="0" cellpadding="2" cellspacing="2"
					class="formbg">
					<tr>
						<td><b>Approver Details</b>
						<table width="98%" border="0" align="center" cellpadding="2"
							cellspacing="2">
							<tr>
								<td class="formth" width="5%"><label class="set"
									name="goalassessment.srno" id="goalassessment.srno"
									ondblclick="callShowDiv(this);"><%=label.get("goalassessment.srno")%></label></td>


								<td class="formth" width="20%" align="left"><label
									name="goalassesment.approverId" class="set"
									id="goalassesment.approverId" ondblclick="callShowDiv(this);"><%=label.get("goalassesment.approverId")%></label></td>
								<td class="formth" width="15%" align="left"><label
									name="goalassesment.approver" class="set"
									id="goalassesment.approver" ondblclick="callShowDiv(this);"><%=label.get("goalassesment.approver")%></label></td>
								<td class="formth" width="20%" align="left"><label
									name="goalassesment.agree" class="set" id="goalassesment.agree"
									ondblclick="callShowDiv(this);"><%=label.get("goalassesment.agree")%></label></td>
								<td class="formth" width="10%" align="left"><label
									name="goalassesment.disagree" class="set"
									id="goalassesment.disagree" ondblclick="callShowDiv(this);"><%=label.get("goalassesment.disagree")%></label></td>

								<td class="formth" width="10%" align="left"><label
									name="goalassesment.comments" class="set"
									id="goalassesment.comments" ondblclick="callShowDiv(this);"><%=label.get("goalassesment.comments")%></label></td>
							</tr>
							<s:hidden name="approverToken" />
							<s:hidden name="approverName" />

							<%
							int count = 1;
							%>
							<%!int i = 0;%>
							<s:iterator value="Approverlst">
								<tr>
									<td class="sortableTD" width="5%"><%=count++%></td>
									<s:hidden name="hiddenApproverId" />
									<s:hidden name="hiddenempid" />
									<s:hidden name="hiddenlevel" />
									<s:hidden name="hiddenassessmentId" />

									<td class="sortableTD" width="20%" align="left"><s:property
										value="approverToken" /></td>
									<td class="sortableTD" width="15%" align="left"><s:property
										value="approverName" /></td>
									<td class="sortableTD" id="ctrlShow" width="20%" align="center">


									<input type="hidden" name="hiddenacceptflag"
										id="hiddenacceptflag<%=i %>"
										value="<s:property value="hiddenacceptflag"/>" /> <input
										type="checkbox" name="acceptFlag" id="acceptFlag<%=i %>"
										value="<s:property value="acceptFlag" />"
										onclick="setAcceptFlag('acceptFlag<%=i %>','rejectFlag<%=i %>','hiddenacceptflag<%=i %>');" />


									<script>
				 		if(document.getElementById('hiddenacceptflag<%=i%>').value=='Y')
				 		 {
							document.getElementById('acceptFlag<%=i%>').checked =true;
						}
						if(document.getElementById('hiddenacceptflag<%=i%>').value=='N')
				 		 {
							document.getElementById('rejectFlag<%=i%>').checked =true;
						}
						</script>
									<td class="sortableTD" id="ctrlShow" width="10%" align="center"><input
										type="checkbox" name="rejectFlag" id="rejectFlag<%=i %>"
										value="<s:property value="rejectFlag" />"
										onclick="setRejectFlag('rejectFlag<%=i %>','acceptFlag<%=i %>','hiddenacceptflag<%=i %>');" /></td>
									<script>
				 		
						if(document.getElementById('hiddenacceptflag<%=i%>').value=='N')
				 		 {
							document.getElementById('rejectFlag<%=i%>').checked =true;
						}
						</script>
									<td class="sortableTD" width="10%" align="left" id="ctrlShow">

									<s:textarea cols="30" rows="2" name="employeecomment"
										id="employeecomment" /></td>
								</tr>
								<%
								i++;
								%>
							</s:iterator>
						</table>
						</td>
					</tr>
				</table>

				</td>
			</tr>
		</s:if>
		<tr>
			<td colspan="4" width="100%"><b> Note: </b><s:property
				value="ratingrangedesc" /></td>
		</tr>

		<!-- <tr>
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
		</tr> -->
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
	<s:hidden name="goalId"></s:hidden>
	<s:hidden name="scheduledAssessmentDate"></s:hidden>
	<s:hidden name="sysDate"></s:hidden>
	<s:hidden name="listType"></s:hidden>
	<s:hidden name="reportingType" />
	<s:hidden name="assessLevel" />
	<s:hidden name="source" />
</s:form>
<script type="text/javascript">

function setAcceptFlag(selectid,id,hiddenid)
{
	//alert("selectid : "+selectid);
	//alert("id : "+id);
	//alert("hiddenid : "+hiddenid);
	document.getElementById(id).checked="";
	//alert(""+document.getElementById(selectid).checked);
	if(document.getElementById(selectid).checked)
	{
	document.getElementById(hiddenid).value="Y";
	}
	else
	{
	document.getElementById(hiddenid).value="";
	}
}
function setRejectFlag(selectid,id,hiddenid)
{
	//alert("selectid : "+selectid);
	//alert("id : "+id);
	//alert("hiddenid : "+hiddenid);
	document.getElementById(id).checked="";
	if(document.getElementById(selectid).checked)
	{
		document.getElementById(hiddenid).value="N";
	}
	else
	{
	document.getElementById(hiddenid).value="";
	}
}
function submitrecordFun(){

try{
	/*	var totalRows = <%=i%>;
		//alert("totalRows : "+totalRows);
	var countcheck = 0;	
	var flag=true;
	for(var j=0;j<totalRows;j++){
		//alert("value of accept " +document.getElementById('hiddenacceptflag'+i).value);
		if(document.getElementById('hiddenacceptflag'+j).value==""){
		//alert("Inside If ");
			flag=false;
			countcheck++;
		} //end of if
	} //end of loop
	//alert("The flag : "+flag);
	if(!flag)
	{
		alert("Please select Agree/Disagree Approver Rating");
		return false;
	}*/
	//if(countcheck == 0){
		//alert('Please select atleast one record');
		//return false;
	//} //end of if 
		var conf=confirm("Do you really want to save ?");
  			if(!conf) 
  			{
  				return false;
  			}
 	
 		document.getElementById('paraFrm').target = "_self";
  		document.getElementById('paraFrm').action = 'SelfGoalAssessment_saveAcceptAssesment.action';
		document.getElementById('paraFrm').submit();
  	  	return true ;
  
	}
	catch(e)
	{
		alert("Error:::"+e);
	}	
	 
}
function closeFun(){

try{
		var conf=confirm("Do you really want to close application ?");
  			if(!conf) 
  			{
  				return false;
  			}
 	
 		document.getElementById('paraFrm').target = "_self";
  		document.getElementById('paraFrm').action = 'SelfGoalAssessment_saveAcceptAssesment.action';
		document.getElementById('paraFrm').submit();
  	  	return true ;
  
	}
	catch(e)
	{
		alert("Error:::"+e);
	}	
	 
}
function resubmitrecordFun(){

try{
		var conf=confirm("Do you really want to save ?");
  			if(!conf) 
  			{
  				return false;
  			}
 	
 		document.getElementById('paraFrm').target = "_self";
  		document.getElementById('paraFrm').action = 'SelfGoalAssessment_saveAcceptAssesment.action';
		document.getElementById('paraFrm').submit();
  	  	return true ;
  
	}
	catch(e)
	{
		alert("Error:::"+e);
	}	
	 
}

function saveFun(){
var comments=document.getElementById("commnet").value;
var length = comments.length;
if(length>2000){

alert("Self Assessment comments length should be less or equal to 2000");
return false;
}
var conf=confirm("Do you really want to save these ratings?");
  			if(!conf) 
  			{
  				return false;
  			}
		document.getElementById('paraFrm').action = 'SelfGoalAssessment_saveRating.action';
		document.getElementById('paraFrm').submit();
}
function dateCheckWithToday(fieldName, fromLabName){
	var enteredDate = document.getElementById(fieldName).value;
	var strDate   = enteredDate.split("-"); 
	var starttime = new Date(strDate[2],strDate[1]-1,strDate[0]); 
	
	
	var sysDate = document.getElementById('paraFrm_sysDate').value;
	var sysDateArray   = sysDate.split("-"); 
	var endtime = new Date(sysDateArray[2],sysDateArray[1]-1,sysDateArray[0]); 
	
	//alert('end==='+endtime);
	
	if(eval(starttime) > eval(endtime)) 
	{ 
		alert("Self Assessment should be finalized on or after "+document.getElementById(fromLabName).innerHTML);
		document.getElementById(fieldName).focus();
		return false;
	}
	return true;
}
function finalizeFun(){
if(!dateCheckWithToday("paraFrm_scheduledAssessmentDate", "sch.assessment.date")){
		return false;
}
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
		document.getElementById('paraFrm').action = 'SelfGoalAssessment_finalizeAssesment.action';
		document.getElementById('paraFrm').submit();
}

function cancelFun(){
		document.getElementById('paraFrm').action = 'SelfGoalAssessment_input.action';
		document.getElementById('paraFrm').submit();
}

function backFun(){
try{
	document.getElementById('paraFrm').target = "_self";
	if(document.getElementById('paraFrm_source').value=='mymessages')
		{
		document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
		}
		else if(document.getElementById('paraFrm_source').value=='myservices')
		{
		document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_serviceData.action';
		}else{
		document.getElementById('paraFrm').action = 'SelfGoalAssessment_input.action';
		}
		
	document.getElementById('paraFrm').submit();
	}catch(e){
	alert(e);
	}
}
function acceptsignoffFun(){
	document.getElementById('paraFrm_signOffStatus').value="Y"; 
	var conf=confirm("Do you want to accept the sign off?");
  	if(!conf) {
  		return false;
  	}else{
		document.getElementById('paraFrm').action = 'SelfGoalAssessment_acceptRejectSignOff.action';
		document.getElementById('paraFrm').submit();
	}
}
function rejectsignoffFun(){
	document.getElementById('paraFrm_signOffStatus').value="R"; 
	var conf=confirm("Do you want to reject the sign off?");
  	if(!conf) {
  		return false;
  	}else{
		document.getElementById('paraFrm').action = 'SelfGoalAssessment_acceptRejectSignOff.action';
		document.getElementById('paraFrm').submit();
	}
}
//Added by Prajakta B
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
//Ends Added by Prajakta B
</script>
