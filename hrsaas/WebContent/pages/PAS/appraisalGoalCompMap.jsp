<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="java.util.HashMap"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="AppraisalCalendar" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="appraisalId" />
	 <table width="100%" border="0" align="right" cellpadding="2" cellspacing="2" class="formbg" >
    
   
    <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Appraisal Calendar</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
        	<tr>
        		<td colspan="3">
        		<input type="button" name="save" value="Save" onclick="saveFun();">
        		<input type="button" class="back" value=" Back "
							onclick="return callBack();" />	
        		</td>
        	</tr>
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg" id="goalTable">
				<tr>
              
              
              <td width="30%" colspan="1" height="20" class="formtext"><label  class = "set"  name="map.goal" id="map.goal" ondblclick="callShowDiv(this);"><%=label.get("map.goal")%></label>  :</td>
			  <td width="70%" colspan="3" height="20">
			  <!--<input type="checkbox"  name="goalMapCheck" class="checkbox" id="goalMapCheck" 
			    onclick="return callGoalMapCheckCheck();"></input>
			    --><s:checkbox name="goalMapCheck" onclick="return callGoalMapCheckCheck();"/>
			    
			    </td>
             
            </tr>
				
				<tr>
					<td><label class="set" id="map.goalName"
						name="map.goalName" onDblClick="callShowDiv(this);"><%=label.get("map.goalName")%></label>
					: <font color="red">*</font></td>
					<td ><s:textfield size="40" name="goalName" readonly="true" 
					cssStyle="background-color: #F2F2F2;"/><img id="ctrlHide"
							src="../pages/images/search2.gif" height="16" align="absmiddle"
							width="16" theme="simple" onclick="callsF9(500,325,'AppraisalCalendar_f9actionGoal.action'); ;"></td>
					<s:hidden name="goalId"/>
					<s:hidden name="hidengoalStartDate"/>
					<s:hidden name="hidengoalEndDate"/>
					
				</tr>
				
				<tr>
					<td><label class="set" id="map.goalWeightage"
						name="map.goalWeightage" onDblClick="callShowDiv(this);"><%=label.get("map.goalWeightage")%></label>
					: <font color="red">*</font></td>
					<td ><s:textfield size="40" name="goalWeightage" onkeypress="return numbersOnly();" />
					</td>
					
				</tr>
				<tr>
					<!--  <td width="25%" colspan="1">&nbsp;</td>-->
					<td colspan="2" width="10%" class="sortableTD" id="ctrlHide" align="center">

					<s:submit cssClass="add" theme="simple" value="Add"
						action="AppraisalCalendar_addItem" 
						onclick="return callAdd()" /></td>
				</tr>
				<s:hidden name="paraId" />
				<tr>
					<td width="100%" colspan="2">
					<table class="formbg" width="100%">
						<tr>
							<!--   srNo -->
							<td align="center" class="formth" width="10%"><label
								class="set" name="srNo" id="srNo"
								ondblclick="callShowDiv(this);"><%=label.get("srNo")%></label></td>
							<!--   category  -->
							<td align="center" class="formth" width="20%"><label
								class="set" name="goalName" id="goalName"
								ondblclick="callShowDiv(this);"><%=label.get("goalName")%></label>
							</td>
							<td align="center" class="formth" width="20%"><label
								class="set" name="goalStartDate" id="goalStartDate"
								ondblclick="callShowDiv(this);"><%=label.get("goalStartDate")%></label>
							</td>
							<td align="center" class="formth" width="20%"><label
								class="set" name="goalToDate" id="goalToDate"
								ondblclick="callShowDiv(this);"><%=label.get("goalToDate")%></label>
							</td>
							<td align="center" class="formth" width="20%"><label
								class="set" name="goalWeightage" id="goalWeightage"
								ondblclick="callShowDiv(this);"><%=label.get("goalWeightage")%></label>
							</td>
							<td class="formth" class="formth" width="20%" id="ctrlHide">
							<label class="set" class="token" theme="simple" name="editRemove"
								id="editRemove" ondblclick="callShowDiv(this);"><%=label.get("editRemove")%></label>&nbsp;
							</td>
						</tr>
						
						<%
							int count1 = 0;
						%>
						<%!int d1 = 0;%>
						<%
							int ii = 0;
						%>
						<%!int val = 0;%>

						<s:iterator value="list">

							<tr <%if(count1%2==0){
															%> class="tableCell1"
								<%}else{%> class="tableCell2" <%	}count1++; %>
								onmouseover="javascript:newRowColor(this);"
								onmouseout="javascript:oldRowColor(this,<%=count1%2 %>);"
								ondblclick="javascript:callForEdit('<s:property value="category"/>',<s:property value="srNo"/>);">

								<td width="10%" align="center" class="sortableTD"><%=++ii%>
								<input type="hidden" name="srNo" value="<%=ii%>" /></td>
								<td class="sortableTD"><s:property value="ittrGoalName" />&nbsp;
								<s:hidden name="ittrGoalName" /></td>
								<td class="sortableTD"><s:property value="ittrGoalFromDate" />&nbsp;
								<s:hidden name="ittrGoalFromDate" /></td>
								<td class="sortableTD"><s:property value="ittrGoalToDate" />&nbsp;
								<s:hidden name="ittrGoalToDate" /></td>
								<td class="sortableTD"><s:property value="ittrGoalWeightage" />&nbsp;
								<s:hidden name="ittrGoalWeightage" /><s:hidden name="ittrGoalId" /></td>
								<input type="hidden" name="hdeleteOp" id="hdeleteOp<%=ii%>" />
								<td width="10%" align="center" nowrap="nowrap"
									class="sortableTD" id="ctrlHide">
								<input type="button" class="rowDelete" title="Delete Record"
									id="confChkop<%=ii%>" name="confChkop"
									onclick="callForDelete2('<%=ii%>')" /></td>

							</tr>

						</s:iterator>
						<%
							d1 = ii;
						%>
						</table>
					</td>
				</tr>
				<tr>
					<td><label class="set" id="map.mulipleGoalFirst"
						name="map.mulipleGoalFirst" onDblClick="callShowDiv(this);"><%=label.get("map.mulipleGoalFirst")%></label>
					: <font color="red">*</font></td>
					<td >
					<s:if test="hiddenmultipleGoal=='FF'">
					<input type="radio" name="multipleGoal" id="multipleGoal1" value="F" checked="checked"/>
					</s:if>
					<s:else>
					<input type="radio" name="multipleGoal" id="multipleGoal1" value="F" />
					</s:else>
					</td>
					
				</tr>
				<tr>
			
					<td><label class="set" id="map.mulipleGoalLast"
						name="map.mulipleGoalLast" onDblClick="callShowDiv(this);"><%=label.get("map.mulipleGoalLast")%></label>
					: <font color="red">*</font></td>
					<td >
					<s:if test="hiddenmultipleGoal=='LL'">
					<input type="radio" name="multipleGoal" id="multipleGoal2" value="L" checked="checked" />
					</s:if>
					<s:else>
					<input type="radio" name="multipleGoal" id="multipleGoal2" value="L" />
					</s:else>
					</td>
					
				</tr>
				<tr>
					<td><label class="set" id="map.mulipleGoalAverage"
						name="map.mulipleGoalAverage" onDblClick="callShowDiv(this);"><%=label.get("map.mulipleGoalAverage")%></label>
					: <font color="red">*</font></td>
					<td >
					<s:if test="hiddenmultipleGoal=='AA'">
					<input type="radio" name="multipleGoal" id="multipleGoal3" value="A"  checked="checked"/>
					</s:if>
					<s:else>
					<input type="radio" name="multipleGoal" id="multipleGoal3" value="A" />
					</s:else>
					</td>
					
				</tr>
				<s:hidden name="hiddenmultipleGoal"/>
			</table>
			</td>
		</tr>
		<tr>
		</tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg" id="compTable">
				<tr>
              
              
              <td width="30%" colspan="1" height="20" class="formtext"><label  class = "set"  name="map.competency" id="map.competency" ondblclick="callShowDiv(this);"><%=label.get("map.competency")%></label>  :</td>
			  <td width="70%" colspan="3" height="20"><!--
			  <input type="checkbox"  name="competencyMapCheck" class="checkbox"   onclick="return callCompetencyMapCheck();">
			  
			  </input>
			  --><s:checkbox name="competencyMapCheck" onclick="return callCompetencyMapCheck();"/>
			  
			  </td>
             
            </tr>
				<tr>
					<td><label class="set" id="map.compName"
						name="map.compName" onDblClick="callShowDiv(this);"><%=label.get("map.compName")%></label>
					: <font color="red">*</font></td>
					<td ><s:textfield size="40" name="compName" readonly="true" 
					cssStyle="background-color: #F2F2F2;"/><img id="ctrlHide"
							src="../pages/images/search2.gif" height="16" align="absmiddle"
							width="16" theme="simple" onclick="callsF9(500,325,'AppraisalCalendar_f9actionComp.action'); ;"></td>
					<s:hidden name="compId"/>
					
					
				</tr>
				
				<tr>
					<td><label class="set" id="map.compWeightage"
						name="map.compWeightage" onDblClick="callShowDiv(this);"><%=label.get("map.compWeightage")%></label>
					: <font color="red">*</font></td>
					<td ><s:textfield size="40" name="compWeightage" maxlength="3" onkeypress="return numbersOnly();" />
					</td>
					
				</tr>
				
				</table>
			</td>
		<tr>
        		<td colspan="3">
        		<input type="button" name="save" value="Save" onclick="saveFun();">
        		<input type="button" class="back" value=" Back "
							onclick="return callBack();" />	
        		</td>
        	</tr>
	</table>
        	
        	<s:hidden name="hiddengoalMap"/>
        	<s:hidden name="hiddencompetencyMap"/>
        	
</s:form>
<script type="text/javascript">
function cancelFun()
{
		document.getElementById('paraFrm_paraId').value = document.getElementById('paraFrm_appraisalId').value;
		document.getElementById('paraFrm').action = "AppraisalCalendar_goalCompetencyMap.action";
		 document.getElementById('paraFrm').submit();
}
function callAdd()
{
	if(document.getElementById('paraFrm_goalId').value=='')
	{
		alert("Please select goal");
		return false;
	}
	if(document.getElementById('paraFrm_goalWeightage').value=='')
	{
		alert("Please enter goal weightage");
		return false;
	}
	
}
////onload();
function onload()
{
	//alert("Goal Flag "+document.getElementById('paraFrm_hiddengoalMap').value);
	//alert("comp Flag "+document.getElementById('paraFrm_hiddencompetencyMap').value);
	if(document.getElementById('paraFrm_hiddengoalMap').value=='Y')
	{
		document.getElementById('goalTable').style.display ='inline';
		document.getElementById('goalMapCheck').checked=true;
		var multipleGoalWeight=document.getElementById('paraFrm_hiddenmultipleGoal').value;
		//alert('multipleGoalWeight : '+multipleGoalWeight);
		if(multipleGoalWeight=='F')
		{
			document.getElementById('multipleGoal1').checked=true;
		}
		if(multipleGoalWeight=='L')
		{
			document.getElementById('multipleGoal2').checked=true;
		}
		if(multipleGoalWeight=='A')
		{
			document.getElementById('multipleGoal3').checked=true;
		}
		
	}
	else{
		document.getElementById('goalTable').style.display ='none';
	}
	if(document.getElementById('paraFrm_hiddencompetencyMap').value=='Y')
	{
		document.getElementById('compTable').style.display ='inline';
		document.getElementById('competencyMapCheck').checked=true;
	}
	else{
		document.getElementById('compTable').style.display ='none';
	}
}
function callForDelete2(id){
   
    var conf=confirm("Are you sure to delete this record?");   
		   		if(conf) {
		    try{		
    	 document.getElementById('paraFrm_paraId').value = id;
		 document.getElementById('paraFrm').action = "AppraisalCalendar_removeGoalsWeightage.action";
		 document.getElementById('paraFrm').submit();
		 }catch(e)
		 {
		 	alert("Exception occured in delete function : "+e);
		 } 
		 }
		 
		 
		 
    }
 function criteriaValidation()
 {
 	if(document.getElementById('paraFrm_hiddencompetencyMap').value=='Y')
	{
		if(document.getElementById('paraFrm_compId').value=='')
		{
			alert("Please select competency");
			return false;
		}
		if(document.getElementById('paraFrm_compWeightage').value=='')
		{
			alert("Please enter competency weightage");
			return false;
		}
		else
		{
			if(eval(document.getElementById('paraFrm_compWeightage').value)>100)
			{
				alert("Please enter competency weightage less than or equal to 100");
				return false;
			}
		}
	}
 		return true
 }
 
 
 function saveFun()
{
	if(!criteriaValidation()){
			return false;
			}
	
	var con = confirm("Do you really want to Save Details.");
		if (con) {
	
			document.getElementById('paraFrm').action = "AppraisalCalendar_saveMapGoalComp.action";
		 	document.getElementById('paraFrm').submit();
	}
}
 
 
 
 function callBack(){
		var apprId = document.getElementById('paraFrm_appraisalId').value;
		
		document.getElementById('paraFrm').target = "_self";
		document.getElementById("paraFrm").action="AppraisalCalendar_callForEdit.action?apprId="+apprId;
		document.getElementById('paraFrm').submit();
	}
 
 function callCompetencyMapCheck(){
 	var parentCheckboxVar = document.getElementById('paraFrm_competencyMapCheck').checked;
 		if((parentCheckboxVar == true)){
	 		document.getElementById('paraFrm_hiddencompetencyMap').value="Y";
			document.getElementById('paraFrm_competencyMapCheck').checked=true;
 		}else{
 			document.getElementById('paraFrm_competencyMapCheck').checked=false;
			document.getElementById('paraFrm_hiddencompetencyMap').value="N";
 		}
 
 }
 function callGoalMapCheckCheck(){
 	var parentCheckboxVar = document.getElementById('paraFrm_goalMapCheck').checked;
 		if((parentCheckboxVar == true)){
	 		document.getElementById('paraFrm_hiddengoalMap').value="Y";
			document.getElementById('paraFrm_goalMapCheck').checked=true;
 		}else{
 			document.getElementById('paraFrm_goalMapCheck').checked=false;
			document.getElementById('paraFrm_hiddengoalMap').value="N";
 		}
 
 }
</script>
