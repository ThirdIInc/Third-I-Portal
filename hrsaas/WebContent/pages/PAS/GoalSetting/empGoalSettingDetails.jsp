<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="EmpGoalSetting" validate="true" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">


		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="100%" class="txt"><strong class="text_head">Employee Goal<s:if test="isApprovalGoalClick">Approval</s:if><s:else>Setting</s:else> 
					</strong></td> 
				</tr>    
			</table>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						<td width="30%" id="ctrlHide" align="right"><font color="red">*</font> Indicates Required</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
							<td width="25%" colspan="1" class="formtext"><label  class = "set"  id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>  :<font
								color="red">*</font><s:hidden name="empCode"
								value="%{empCode}" /><s:hidden name="code" /></td>
							<td width="75%" colspan="3"><s:textfield name="empToken"
								size="10" value="%{empToken}" theme="simple" readonly="true" /><s:textfield
								name="empName" size="50" value="%{empName}" theme="simple"
								readonly="true" /></td>
						
					
				</tr>
				<tr>
					<td><label class="set" id="goalPeriod"
						name="goalPeriod" onDblClick="callShowDiv(this);"><%=label.get("goalPeriod")%></label>
					: <font color="red">*</font></td>
					<td colspan="3"><s:textfield size="40" name="goalPeriod" readonly="true" 
					cssStyle="background-color: #F2F2F2;"/><img id="ctrlHide"
							src="../pages/images/search2.gif" height="16" align="absmiddle"
							width="16" theme="simple" onclick="callsF9(500,325,'EmpGoalSetting_f9GoalAction.action'); ;"></td>
					<s:hidden name="goalPeriodId"/>
					
				</tr>
				<tr>
					<td><label class="set"
						id="goalFromDate" name="goalFromDate"
						onDblClick="callShowDiv(this);"><%=label.get("goalFromDate")%></label>
					:</td>
					<td><s:textfield size="30" name="goalFromDate" readonly="true" 
					cssStyle="background-color: #F2F2F2;"/></td>
					<td><label class="set"
						id="goalToDate" name="goalToDate"
						onDblClick="callShowDiv(this);"><%=label.get("goalToDate")%></label>
					:</td>
					<td><s:textfield size="30" name="goalToDate" readonly="true" 
					cssStyle="background-color: #F2F2F2;"/></td>
				</tr>
				
				<tr>
					<td class="formtext"><label  class = "set"  id="status" name="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label>:</td>
					<td><s:hidden name="hiddenStatus" 
						value="%{hiddenStatus}" /> <s:select theme="simple" name="approvalStatus" 
						disabled="true" cssStyle="width:130"
						list="#{'1':'Draft','2':'Pending','3':'Approved','4':'Sent Back','6':'Sent For Revision'}" /></td>
				</tr>
				
			</table>
			</td>
		</tr>
		<s:if test="flag=='true'">
	</s:if>
	<s:else>
			<tr id='ctrlHide'>
				<td>
				<table width="100%" border="0" align="center" cellpadding="2"
					cellspacing="2" class="formbg">
					<tr>
						<td colspan="1" class="text_head"><strong
							class="forminnerhead"><label class="set"
							id="goalDetails" name="goalDetails"
							ondblclick="callShowDiv(this);"><%=label.get("goalDetails")%></label>
						</strong></td>
						<td width="75%" colspan="4"><!--<a href="#" onclick="showCompetency()"> <font color="blue">Click here to View Competencies</font></a>--></td>
					</tr>
					<tr>
						<td width="30%" height="22" class="formtext"><label
							class="set" id="goalDesc" name="goalDesc"
							ondblclick="callShowDiv(this);"><%=label.get("goalDesc")%></label>
						:<font color="red">*</font></td>
						<td height="22" width="70%" colspan="4" nowrap="nowrap"><s:textarea
							cols="100" rows="2" name="goalDesc" /></td>

					</tr>
					<tr>
						<td width="10%" height="22" class="formtext"><label
							class="set" id="goalComments" name="goalComments"
							ondblclick="callShowDiv(this);"><%=label.get("goalComments")%></label>
						:</td>
						<td height="22" width="84%" colspan="4"><s:textarea
							theme="simple" name="goalComments" rows="4" cols="70"
							onkeypress="return allCharacters()"
							onkeyup="callLength('goalComments','descCnt','1999');"
							readonly="false" /> <img src="../pages/images/zoomin.gif"
							height="12" align="bottom" width="12" theme="simple"
							onclick="javascript:callWindow('paraFrm_goalComments','goalComments','','paraFrm_descCnt','1999');">
						&nbsp;Remaining chars <s:textfield name="descCnt" readonly="true"
							size="4"></s:textfield></td>

					</tr>


					<tr>
						<td height="22" width="30%" class="formtext"><label
							class="set" id="goalAchieveDate" name="goalAchieveDate"
							ondblclick="callShowDiv(this);"><%=label.get("goalAchieveDate")%></label>
						:</td>
						<td width="20%" height="22"><s:textfield size="25"
							name="goalAchieveDate" theme="simple"
							onkeypress="return numbersWithHiphen();" /></td>
						<td><a
							href="javascript:NewCal('paraFrm_goalAchieveDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage"
							height="16" align="absmiddle" width="16"> </a></td>

						<td height="22" width="30%" class="formtext"><label
							class="set" id="goalWeightage" name="goalWeightage"
							ondblclick="callShowDiv(this);"><%=label.get("goalWeightage")%></label>
						:<font color="red">*</font></td>
						<td height="22" width="20%"><s:textfield size="25"
							name="goalWeightage" theme="simple"
							onkeypress="return numbersWithHiphen();" /></td>
					</tr>
					<s:if test="isGoalCategory">
						<tr>
							<td height="22" width="25%" class="formtext"><label
								class="set" id="goal.category" name="goal.category"
								ondblclick="callShowDiv(this);"><%=label.get("goal.category")%></label>
							:</td>
							<td width="25%" height="22"><s:select
								name="goalCategoryCode" headerKey="" headerValue="--Select--"
								list="%{categoryMap}" cssStyle="width:165" /></td>

							<!--  <td height="22" width="25%" class="formtext"><label  class = "set"  id="categoryWightage" name="categoryWightage" ondblclick="callShowDiv(this);"><%=label.get("categoryWightage")%></label>  : </td>
						<td  height="22"><s:property
								value="categoryWeightage" />&nbsp;<s:hidden
								name="categoryWeightage" />
						</td>-->
						</tr>
					</s:if>
					<s:else>
						<s:hidden name="goalCategoryCode" />
					</s:else>
					<tr>

						<td width="100%" colspan="4" align="center"><s:if
							test="%{insertFlag}">
							<input type="button" class="add" onclick="return callAddGoals();"
								theme="simple" value="   Add   " />
						</s:if><input type="button" class="reset" onclick="return callClear();"
							theme="simple" value=" Clear " /></td>
					</tr>
					<td width="25%"></td>
				</table>
				</td>
			</tr>
		</s:else>
		<tr >
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td colspan="8"><strong class="text_head"><label  class = "set"  id="goalList" name="goalList" ondblclick="callShowDiv(this);"><%=label.get("goalList")%></label> </strong></td>
					
				</tr>
				<tr>
					<td width="5%" class="formth"><label  class = "set"  id="goalSrno" name="goalSrno" ondblclick="callShowDiv(this);"><%=label.get("goalSrno")%></label></td>					
					<td class="formth" width="30%"><label  class = "set"  id="goalDesc1" name="goalDesc" ondblclick="callShowDiv(this);"><%=label.get("goalDesc")%></label></td>
					<td class="formth" width="15%"><label  class = "set"  id="goalWeightage1" name="goalWeightage" ondblclick="callShowDiv(this);"><%=label.get("goalWeightage")%></label></td>
					<td class="formth" width="30%"><label  class = "set"  id="goalComments1" name="goalComments" ondblclick="callShowDiv(this);"><%=label.get("goalComments")%></label> </td>										
					<s:if test="isGoalCategory">
					<td class="formth" width="25%"><label  class = "set"  id="goal.category1" name="goal.category" ondblclick="callShowDiv(this);"><%=label.get("goal.category")%></label></td>
					<td class="formth" width="25%"><label  class = "set"  id="categoryWightage1" name="categoryWightage" ondblclick="callShowDiv(this);"><%=label.get("categoryWightage")%></label></td>
					</s:if>
					<td class="formth" width="25%"><label  class = "set"  id="goalAchieveDate1" name="goalAchieveDate" ondblclick="callShowDiv(this);"><%=label.get("goalAchieveDate")%></label></td>
					<td class="formth" width="20%" id="ctrlHide"><label  class = "set"  id="EditRemove" name="EditRemove" ondblclick="callShowDiv(this);"><%=label.get("EditRemove")%></label></td>
					
					<%
											int k = 1;
											int c = 0;
										%>
										
										 
					<s:iterator value="empGoalList">
					<s:if test="goalDTLStatusList=='A_'"> 
						<tr>

							<td class=sortableTD width="5%"><%=k%></td>						
							<td class="sortableTD" width="30%">&nbsp;<s:property
								value="goalDescList" /><s:hidden name="goalDescList" /></td>
							<td class="sortableTD" width="15%" align="center"><s:property
								value="goalWeightageList" />&nbsp;<input type="hidden"
								name="goalWeightageHidden" id='goalWeightageHidden<%=k%>'
								value="<s:property value="goalWeightageList" />" /><s:hidden
								name="goalWeightageList" /></td>
							<td class="sortableTD" width="30%">&nbsp;<s:property
								value="goalCommentsList" /><s:hidden name="goalDTLidList" /><s:hidden name="goalDTLStatusList" /> <s:hidden name="goalCommentsList" />
							</td>
							<s:if test="isGoalCategory">
							<td class="sortableTD" width="25%" align="left"><s:property
								value="goalCategoryNameList" />&nbsp;<input type="hidden"
								name="goalCategoryNameList" id="goalCategoryNameList<%=k%>" value="<s:property
								value='goalCategoryNameList'/>" />
								</td>        
								
								<td class="sortableTD" width="25%" align="left"><s:property
								value="goalCategoryWeightageList" />&nbsp;<s:hidden
								name="goalCategoryWeightageList" /></td>
						</s:if>
						<input type="hidden"
								name="goalCategoryCodeList" id='goalCategoryCodeList<%=k%>'
								value="<s:property value="goalCategoryCodeList" />" />
							<td class="sortableTD" width="25%" align="center"><s:property
								value="goalAchieveDateList" />&nbsp;<s:hidden
								name="goalAchieveDateList" /></td>															
							<td align="center" width="20%" class="sortableTD" id="ctrlHide">
							<input type="button" class="rowEdit" title="Edit Record"
								onclick="callForEdit('<s:property value="goalDTLidList"/>')" />
							<input type="button" class="rowDelete" title="Delete Record"
								onclick="callForDelete('<s:property value="goalDTLidList"/>')" /></td>

						</tr>
						</s:if>
						<!--<s:else>
					<tr>

							<td class=sortableTD width="5%"><k%></td>
							<s:if test="isGoalCategory">
							<td class="sortableTD" width="25%" align="left"><s:property
								value="goalCategoryNameList" />&nbsp;<s:hidden
								name="goalCategoryNameList" /></td>
						</s:if>
							<td class="sortableTD" width="30%"><strike><s:property
								value="goalDescList" /></strike><s:hidden name="goalDescList" /></td>

							<td class="sortableTD" width="30%"><strike>&nbsp;<s:property
								value="goalCommentsList" /></strike><s:hidden name="goalDTLidList" /><s:hidden name="goalDTLStatusList" /> <s:hidden name="goalCommentsList" />
							</td>

							<td class="sortableTD" width="25%" align="center"><strike><s:property
								value="goalAchieveDateList" /></strike>&nbsp;<s:hidden
								name="goalAchieveDateList" /></td>

							<td class="sortableTD" width="25%" align="center"><strike><s:property
								value="goalWeightageList" /></strike>&nbsp;<input type="hidden"
								name="goalWeightageHidden" id='goalWeightageHidden<k%>'
								value="0" /><s:hidden
								name="goalWeightageList" /></td><s:hidden name="goalCategoryCodeList" />
						
							<td align="center" width="20%" class="sortableTD" id="ctrlHide">&nbsp;
							</td>

						</tr>
						</s:else>
						
						--><%
							k++;
							c++;
						%>
					</s:iterator>
					<tr>

						<s:if test="isGoalCategory">
							<td class="sortableTD" width="55%" colspan="5" align="right">Total Weightage </td>
						</s:if><s:else>
						<td class="sortableTD" width="55%" colspan="4" align="right">Total Weightage </td>
						</s:else>
							<td class="sortableTD" width="25%" align="center"><s:property value="totalWeightage"/></td>

							<td align="center" width="20%" class="sortableTD" id="ctrlHide">&nbsp;
							</td>

						</tr>
				<input type="hidden" name="count" id="count"
					value="<%=c%>" />
			</table>
			</td>
		</tr>
		
		<s:if test="commentFlag">
				<tr>
					<td colspan="4">
					<div align="center">
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
							class="formbg">
						<tr>
							<td colspan="4" class="text_head"><strong><label  class = "set"  id="approverCommentsList" name="approverCommentsList" ondblclick="callShowDiv(this);"><%=label.get("approverCommentsList")%></label></strong></td>
						</tr>
						<tr>
							<td class="formth" width="8%"><label  class = "set"  id="apprSrno" name="apprSrno" ondblclick="callShowDiv(this);"><%=label.get("apprSrno")%></label></td>
							<td class="formth" width="30%"><label  class = "set"  id="apprName" name="apprName" ondblclick="callShowDiv(this);"><%=label.get("apprName")%></label></td>
							<td class="formth" width="15%" align="center"><label  class = "set"  id="apprDate" name="apprDate" ondblclick="callShowDiv(this);"><%=label.get("apprDate")%></label></td>
							<td class="formth" width="10%" align="center"><label  class = "set"  id="apprStatus" name="apprStatus" ondblclick="callShowDiv(this);"><%=label.get("apprStatus")%></label></td>
							<td class="formth" width="30%"><label  class = "set"  id="apprComment" name="apprComment" ondblclick="callShowDiv(this);"><%=label.get("apprComment")%></label></td>
						</tr>

						<%
						int j = 1;
						%>
						<s:iterator value="apprList" status="stat">

							<tr>
								<td width="8%" align="center" class="sortableTD"><%=j++%></td>
								<td width="30%" class="sortableTD"><s:property
									value="approverName" /> <s:hidden name='approverName'/></td>
								<td width="15%" align="center" class="sortableTD"><s:property
									value="approvedDate" /><s:hidden name='approvedDate'/>&nbsp;</td>
								<td width="10%" align="center" class="sortableTD"><s:property
									value="approveStatus" /><s:hidden name='approveStatus'/></td>
								<td width="30%" class="sortableTD"><s:property
									value="approverCommentList" /><s:hidden name='approverCommentList'/>&nbsp;</td>

							</tr>
						</s:iterator>
					</table>
					</div>
					</td>
				</tr>
			</s:if>
			
			<s:if test="isApprovalForm">
				<tr>
					<td colspan="4">
					<div align="center">
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
							class="formbg">
						<tr>
							<td colspan="1"><label  class = "set"  id="apprComment" name="apprComment" ondblclick="callShowDiv(this);"><%=label.get("apprComment")%></label> :</td>
							<td colspan="3"><s:textarea cols="100" rows="3" id="ctrlShow" name="approverComment" theme="simple"></s:textarea></td>
						</tr>
					</table>
					</div>
					</td>
				</tr>
			</s:if>
		
		<s:hidden name="paraId" />
		<s:hidden name="totalWeightage" />
		<s:hidden name="empGoalId" />
		<s:hidden name="isApprovalForm" />
		<s:hidden name="level" />
		<s:hidden name="reportingType" />
		<s:hidden name="isGoalCategory"  />  
		<s:hidden name="isApprovalGoalClick" /> 
		<tr>
			<td><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
	
	 <s:hidden name="source" id="source" />
</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js">

</script>


<script type="text/javascript"><!--
function callAddGoals(){
	var fields = ["paraFrm_goalDesc","paraFrm_goalWeightage"];
	var labels = ["goalDesc","goalWeightage"];
	var selectFlag = ["enter","enter"];
	var goalWeightage=document.getElementById('paraFrm_goalWeightage').value;
	var totalWeightage=document.getElementById('paraFrm_totalWeightage').value;
	
	 var goalPeriodId=document.getElementById('paraFrm_goalPeriodId').value;
	 if(goalPeriodId=="")
	 {
	 	alert("Please select Goal Name");
		return false;
	 }
	  if(document.getElementById('paraFrm_isGoalCategory').value=="true"){
	  
	if(!validateBlank(fields,labels,selectFlag))
	return false;
	 
	if(document.getElementById('paraFrm_goalCategoryCode').value=="")  
	{   
	alert("Please select Goal Category from the list");
	return false; 
	} 
	var fields1 = ["paraFrm_goalAchieveDate"];
	var labels1 = ["goalAchieveDate"];
	if(!validateDate(fields1,labels1)){
	
	return false;
	}
	
	document.getElementById('paraFrm').action = "EmpGoalSetting_addGoals.action";
	document.getElementById('paraFrm').submit();
	  
	
}else{
 
var fields = ["paraFrm_goalDesc","paraFrm_goalWeightage"];
	var labels = ["goalDesc","goalWeightage"];
	var selectFlag = ["enter","enter"];
	var goalWeightage=document.getElementById('paraFrm_goalWeightage').value;
	var totalWeightage=document.getElementById('paraFrm_totalWeightage').value;
 
if(!validateBlank(fields,labels,selectFlag))
	return false;
	    
	
	document.getElementById('paraFrm').action = "EmpGoalSetting_addGoals.action";
	document.getElementById('paraFrm').submit();


}

 }
 
     

 function checkDateBetweenForApplication(star,enddate,enteredDt,startlabel,endlabel,enteredlabel){
 		var fromDate     = document.getElementById(startDate).value;
		var toDate       = document.getElementById(enddate).value;
		var enteredDate  = document.getElementById(enteredDt).value;
		
		var strDate   = fromDate.split("-"); 
		var starttime = new Date(strDate[2],strDate[1]-1,strDate[0]); 
	
		var endDate   = toDate.split("-"); 
		var endtime   = new Date(endDate[2],endDate[1]-1,endDate[0]); 
	
		var validDate   = enteredDate.split("-");
		var validTime   = new Date(validDate[2],validDate[1]-1,validDate[0]); 
	
	if((validTime < starttime) || (validTime > endtime)) { 
		alert(""+enteredlabel+" should be between "+startlabel+" and "+endlabel);
		document.getElementById(enteredDt).focus();
		return false;
	}
	return true;
 	
 	}

function checkWeightage(){
	var rowCount=document.getElementById('count').value;
	var totalWeightage=0;
	for(var i=1;i<=eval(rowCount);i++){
		totalWeightage = eval(eval(totalWeightage)+eval(document.getElementById("goalWeightageHidden"+i).value));
	}
	if(totalWeightage!="100"){
			alert("Total "+ document.getElementById('goalWeightage').innerHTML+" should be 100");;
			return false;
		}
	return true;
}
function reportFun(){
	document.getElementById('paraFrm').target = "_blank";
	document.getElementById('paraFrm').action="EmpGoalSetting_genReport.action";
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target = "main";
}


//@modified by Prajakta B
//to compare category code wise(previously it was compared Name wise.)
function draftFun(){
try{
 
    var rowCount=document.getElementById('count').value;  
    var key="true";    
	    
   if(document.getElementById('paraFrm_isGoalCategory').value=="true"){
   var option_length=document.getElementById('paraFrm_goalCategoryCode').options.length; 
   var value_obj=document.getElementById('paraFrm_goalCategoryCode');
	var list_values;
   
	    for(i=1;i<option_length;i++)  
		 {
		 for(j=1;j<=eval(rowCount);j++)
		 { 
		  
		 try{
	  			var list_values1=document.getElementById('goalCategoryCodeList'+j).value;  
  		      //alert('------'+trim(value_obj.options[i].text)+'::::'+trim(list_values1)+'-----------');
		      if(trim(value_obj.options[i].value)==trim(list_values1))  
		      {  
				key="true";
				 break;   
		      }else{
		        key="false";
 		      } 
 		     
		     }
		     catch(e){
		     alert(e)
		     }
         }
         if( key=="false"){
         break;
         }
       } 
      }
   if(key=="false"){
   alert("Please select all category atleast once.");
   } else{
   
     
   
     var fields = ["paraFrm_empName","paraFrm_goalPeriod"];
	 var labels = ["employee","goalPeriod"];
	 var selectFlag = ["select","select"];
	var pre4=document.getElementById('paraFrm_totalWeightage').value;  
	   
	 var goalName=document.getElementById('paraFrm_goalPeriod').value;  
	 var goalWeight=document.getElementById('paraFrm_goalWeightage').value;  
	   
	   if(goalName==""){
	   alert("Please select Goal Name");
	return false;
	   }
	   	var goaldesc=document.getElementById('paraFrm_goalDesc').value;  
	  
	 
	if(!validateBlank(fields,labels,selectFlag))
	return false; 
	
	if(document.getElementById('count').value=="0" || document.getElementById('count').value==""){
			alert('Please add minimum one goal in the list');
			return false;
		}
	
	if(pre4!=100.0) 
	{
	alert("The Total Weightage should not be less than or greater than 100");
	return false; 
	}
		
	document.getElementById('paraFrm').action = "EmpGoalSetting_saveAsDraft.action";
	document.getElementById('paraFrm').submit();
	} 
}catch(e){
alert(e)
}

	}   
  

function deleteFun(){
	var conf=confirm("Do you really want to delete this goal?");
  			if(!conf) 
  			{
  				return false;
  			}
  	document.getElementById('paraFrm').action = "EmpGoalSetting_deleteEmpGoals.action";
	document.getElementById('paraFrm').submit();
}
//@modified by Prajakta B
//to compare category code wise(previously it was compared Name wise.)
function sendforapprovalFun(){

var rowCount=document.getElementById('count').value;  
    var key="true";    
	    
   if(document.getElementById('paraFrm_isGoalCategory').value=="true"){
   var option_length=document.getElementById('paraFrm_goalCategoryCode').options.length; 
   var value_obj=document.getElementById('paraFrm_goalCategoryCode');
	var list_values;
   
	    for(i=1;i<option_length;i++)  
		 {
		 for(j=1;j<=eval(rowCount);j++)
		 { 
		  
		 try{
	  			var list_values1=document.getElementById('goalCategoryCodeList'+j).value;       
  		      
		      if(trim(value_obj.options[i].value)==trim(list_values1))  
		      {  
				key="true";
				 break;   
		      }else{
		        key="false";
 		      } 
 		     
		     }
		     catch(e){
		     alert(e)
		     }
         }
         if( key=="false"){
         break;
         }
       } 
      }
   if(key=="false"){
   alert("Please select all category atleast once.");
   } else{  
		
	var fields = ["paraFrm_empName","paraFrm_goalPeriod"];
	var labels = ["employee","goalPeriod"];
	var selectFlag = ["select","select"];
	var pre4=document.getElementById('paraFrm_totalWeightage').value;  
	 
	 
	 var goalName=document.getElementById('paraFrm_goalPeriod').value;  
	   
	   if(goalName==""){
	   alert("Please select Goal Name");
	return false;
	   }
	   	
	
	if(!validateBlank(fields,labels,selectFlag))
	return false;
	
	if(document.getElementById('count').value=="0" || document.getElementById('count').value==""){
			alert('Please add minimum one goal in the list');
			return false;
		}
		
		if(pre4!=100.0) 
	{
	alert("The Total Weightage should not be less than or greater than 100");
	return false; 
	}  
		
	  var conf=confirm("Do you really want to send for approval?");
  			if(!conf) 
  			{
  				return false;
  			}
	document.getElementById('paraFrm').action = "EmpGoalSetting_sendForApproval.action";
	document.getElementById('paraFrm').submit();
}
}
function callForEdit(rowId){

		document.getElementById('paraFrm_paraId').value = rowId;
		document.getElementById('paraFrm').action = "EmpGoalSetting_callforEdit.action";
	document.getElementById('paraFrm').submit();
}
function callForDelete(rowId){
 var conf=confirm("Do you really want to remove goal?");
  			if(!conf) 
  			{
  				return false;
  			}
	document.getElementById('paraFrm_paraId').value = rowId;
	document.getElementById('paraFrm').action = "EmpGoalSetting_removeGoals.action";
	document.getElementById('paraFrm').submit();
}
function backFun(){

	var status =document.getElementById('paraFrm_approvalStatus').value;
	
	var isApprover =document.getElementById('paraFrm_isApprovalForm').value;
	var actionName = "";
	if(isApprover == 'true'){
	if(status!=3){
				if(document.getElementById('source').value=='mymessages')
					{
					actionName = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
					}
					else if(document.getElementById('source').value=='myservices')
					{
						actionName = 'MypageProcessManagerAlerts_serviceData.action';
					}else{
						actionName = 'EmpGoalApproval_getPendingList.action';
				}
		}else{
				if(document.getElementById('source').value=='mymessages')
					{
					actionName = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
					}
					else if(document.getElementById('source').value=='myservices')
					{
						actionName = 'MypageProcessManagerAlerts_serviceData.action';
					}else{
				actionName = 'EmpGoalApproval_getApprovedList.action';
				}	
		}
	}else {
	if(status!=3){
		if(document.getElementById('source').value=='mymessages')
					{
					actionName = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
					}
					else if(document.getElementById('source').value=='myservices')
					{
						actionName = 'MypageProcessManagerAlerts_serviceData.action';
					}else{
						actionName = "EmpGoalSetting_input.action"
					}	
	}else{
			if(document.getElementById('source').value=='mymessages')
					{
					actionName = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
					}
					else if(document.getElementById('source').value=='myservices')
					{
						actionName = 'MypageProcessManagerAlerts_serviceData.action';
					}else{
					actionName = "EmpGoalSetting_getApprovedList.action"
					}
	}
	}
	document.getElementById('paraFrm').action = actionName;
	document.getElementById('paraFrm').submit();
	
	
}
function cancelFun(){
	var status =document.getElementById('paraFrm_approvalStatus').value;
	var empGoalId =document.getElementById('paraFrm_empGoalId').value;
	var isApprover =document.getElementById('paraFrm_isApprovalForm').value;
	var actionName = "";
	if(isApprover == 'true'){
		if(document.getElementById('source').value=='mymessages')
		{
		actionName = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
		}
		else if(document.getElementById('source').value=='myservices')
		{
		actionName = 'MypageProcessManagerAlerts_serviceData.action';
		}
		else{
		
		actionName = 'EmpGoalSetting_retriveForApproval.action?empGoalId='+empGoalId+'&approvalStatus='+status;
		}
		
	}else {
	if(status!=3){
			if(document.getElementById('source').value=='mymessages')
		{
		actionName = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
		}
		else if(document.getElementById('source').value=='myservices')
		{
		actionName = 'MypageProcessManagerAlerts_serviceData.action';
		}
		else{
		actionName = "EmpGoalSetting_input.action"
		}
	}else{
		if(document.getElementById('source').value=='mymessages')
		{
		actionName = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
		}
		else if(document.getElementById('source').value=='myservices')
		{
		actionName = 'MypageProcessManagerAlerts_serviceData.action';
		}
		else{
			actionName = "EmpGoalSetting_getApprovedList.action"
			}
	}
	}
	document.getElementById('paraFrm').action = actionName;
	document.getElementById('paraFrm').submit();
}
function resetFun(){
try{
	document.getElementById('paraFrm_goalDesc').value = "";
	document.getElementById('paraFrm_goalComments').value = "";
	document.getElementById('paraFrm_goalAchieveDate').value = "";
	document.getElementById('paraFrm_goalWeightage').value = "";
		document.getElementById('paraFrm').action = "EmpGoalSetting_reset.action";
	document.getElementById('paraFrm').submit();
}catch(e){
alert(e)
}
}
//@modified by Prajakta B
//to compare category code wise(previously it was compared Name wise.)
function saveFun(){

	 var rowCount=document.getElementById('count').value;  
    var key="true";    
	    
   if(document.getElementById('paraFrm_isGoalCategory').value=="true"){
   var option_length=document.getElementById('paraFrm_goalCategoryCode').options.length; 
   var value_obj=document.getElementById('paraFrm_goalCategoryCode');
	var list_values;
   
	    for(i=1;i<option_length;i++)  
		 {
		 for(j=1;j<=eval(rowCount);j++)
		 { 
		  
		 try{
	  			var list_values1=document.getElementById('goalCategoryCodeList'+j).value;       
  		      
		      if(trim(value_obj.options[i].value)==trim(list_values1))  
		      {  
				key="true";
				 break;   
		      }else{
		        key="false";
 		      } 
 		     
		     }
		     catch(e){
		     alert(e)
		     }
         }
         if( key=="false"){
         break;
         }
       } 
      }
   if(key=="false"){
   alert("Please select all category atleast once.");
   } else{
	var fields = ["paraFrm_empName","paraFrm_goalPeriod"];
	var labels = ["employee","goalPeriod"];
	var selectFlag = ["select","select"];
	
	if(!validateBlank(fields,labels,selectFlag))
	return false;
	
	if(document.getElementById('count').value=="0" || document.getElementById('count').value==""){
			alert('Please add minimum one goal in the list');
			return false;
		}
		if(!checkWeightage())
		return false;
		
	document.getElementById('paraFrm').action = "EmpGoalSetting_saveByApprover.action";
	document.getElementById('paraFrm').submit();
	}
	
}
function callClear(){
	document.getElementById('paraFrm_goalDesc').value = "";
	document.getElementById('paraFrm_goalAchieveDate').value = "";
	document.getElementById('paraFrm_goalWeightage').value = "";
	document.getElementById('paraFrm_goalComments').value="";
	document.getElementById('paraFrm_paraId').value="";
	document.getElementById('paraFrm_goalCategoryCode').value="";
	
}
function approveFun(){
try
{
		if(!checkWeightage())
		return false;
 	var conf=confirm("Do you really want to approve this goal?");
  			if(!conf) 
  			{
  				return false;
  			}
	document.getElementById('paraFrm').action = "EmpGoalSetting_approveApplication.action";
	document.getElementById('paraFrm').submit();
}catch(e)
	{
		alert("Exception occured in draft function : "+e);
	}
	
}

function sendbackFun(){
 	var conf=confirm("Do you really want to send back this goal?");
  			if(!conf) 
  			{
  				return false;
  			}
	document.getElementById('paraFrm').action = "EmpGoalSetting_sentBackApplication.action";
	document.getElementById('paraFrm').submit();
}
function editFun(){
	document.getElementById('paraFrm').action = "EmpGoalSetting_edit.action";
	document.getElementById('paraFrm').submit();
}

function reviseapplicationFun(){
	var conf=confirm("Do you really want to send the application for revision?");
  			if(!conf) 
  			{
  				return false;
  			}
	document.getElementById('paraFrm').action = "EmpGoalSetting_reviseApplication.action";
	document.getElementById('paraFrm').submit();
}

function showCompetency()
{
		var wind = window.open('','wind','width=600,height=300,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
		document.getElementById('paraFrm').target = "wind";
	    document.getElementById('paraFrm').action = 'EmpGoalSetting_getCompetency.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "_self";

}
--></script>  