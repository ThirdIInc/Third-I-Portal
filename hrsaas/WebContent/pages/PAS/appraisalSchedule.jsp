<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="AppraisalSchedule" validate="true" id="paraFrm"
	theme="simple">
	
	
	  <table width="100%" border="0" align="right" cellpadding="2" cellspacing="1" class="formbg" >
    
    <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Appraisal Schedule</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
    <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2">
         <tr>
            <td width="78%">
            <jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
          			
	</td>
            <td width="22%"><div align="right"><font color="red">*</font>Indicates Required </div></td>
          </tr>
        </table>
          </td>
    </tr>
    <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
          <tr>
            <td>
	            <table width="98%" border="0" align="center" cellpadding="2" cellspacing="2">
	            <tr>
	              <s:hidden name="apprId"></s:hidden>
	              <s:hidden name="startDate"></s:hidden>
	              <s:hidden name="endDate"></s:hidden>
	              <s:hidden name="addFlag"></s:hidden>
	               <s:hidden name="appStarted"></s:hidden>
	               <s:hidden name="lockAppr"></s:hidden>
	                
	               
	              <td width="10%" colspan="1" height="20" class="formtext" nowrap="nowrap">
	              	<label name="appraisal.code" class = "set"  id="appraisal.code" ondblclick="callShowDiv(this);"><%=label.get("appraisal.code")%></label> <font color="red">*</font> :
	              </td>
				  <td width="40%" colspan="1" height="20" nowrap="nowrap">
				  <s:if test="calUpdateFlag">
				  <s:property value="apprCode"/>
				  <s:hidden name="apprCode"></s:hidden>
				  </s:if>
				  <s:else>
				 	  <s:textfield name="apprCode" size="25" readonly="true" />
				 	  <img
				 			src="../pages/images/recruitment/search2.gif" height="16"
				 			align="absmiddle" width="17" theme="simple"
				 			onclick="javascript:callsF9(500,325,'AppraisalSchedule_f9SelectAppraisal.action'); ">
				  </s:else>			
				  </td>
	              <td width="50%" colspan="1" height="20" class="formtext">
	              	<label name="appr.from.date" class = "set"  id="appr.from.date" ondblclick="callShowDiv(this);"><%=label.get("appr.from.date")%></label> :
	              	<s:property value="startDate"></s:property>&nbsp;&nbsp;   
	              	<label name="appr.to.date" class = "set"  id="appr.to.date" ondblclick="callShowDiv(this);"><%=label.get("appr.to.date")%></label> :
	              	<s:property value="endDate"></s:property>
	              </td>
	            </tr>
	            </table>
            </td>
          </tr>
          
      </table></td>
    </tr>
    <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg" >
          <tr>
										<td class="formth" width="5%"><label name="phase.srno" class = "set"  id="phase.srno" ondblclick="callShowDiv(this);"><%=label.get("phase.srno")%></label></td>
										<td class="formth" width="30%" align="left"><label  name="phase.name" class = "set"  id="phase.name" ondblclick="callShowDiv(this);"><%=label.get("phase.name")%></label></td>
										<td class="formth" width="20%" align="center"><label name="phase.start.date" class = "set"  id="phase.start.date" ondblclick="callShowDiv(this);"><%=label.get("phase.start.date")%></label></td>
										<td class="formth" width="20%"><label name="phase.end.date" class = "set"  id="phase.end.date" ondblclick="callShowDiv(this);"><%=label.get("phase.end.date")%></label></td>
										<td class="formth" width="15%"><label name="phase.lock" class = "set"  id="phase.lock" ondblclick="callShowDiv(this);"><%=label.get("phase.lock")%></label></td>
									</tr>

							<%
						int j = 1 , c=0;
							
							%>
							<s:iterator value="phaseList">
											
								<tr>
									<td width="5%" align="center" class="sortableTD"><%=j%></td>
									<td width="30%" align="left" class="sortableTD"><s:hidden name="phaseCode" /><s:textfield
										name="phaseName" id='<%="phaseName"+j%>' readonly="true" /></td>
										<td width="20%" align="center" class="sortableTD"><s:textfield name="phaseStartDate" id='<%="phaseStartDate"+j%>' onkeypress="return numbersWithHiphen();" size="10"/>
										<a href="javascript:NewCal('<%="phaseStartDate"+j%>','DDMMYYYY');"> <img src="../pages/images/Date.gif" class="iconImage" height="16"
											align="absmiddle" width="16"> </a>
										</td>
									<td width="20%" align="center" class="sortableTD"><s:textfield name="phaseEndDate"   id='<%="phaseEndDate"+j%>' onkeypress="return numbersWithHiphen();" size="10"/>
									<a href="javascript:NewCal('<%="phaseEndDate"+j%>','DDMMYYYY');"> <img src="../pages/images/Date.gif" class="iconImage" height="16"
											align="absmiddle" width="16"> </a>
									</td>
									<td width="15%" align="center" class="sortableTD"><s:select name="phaseLockFlag" theme="simple" 
															list="#{'Y':'Yes','N':'No'}" />
															
															</td>
									
								
								 <%j++; c++;%>
								 </tr>
							</s:iterator>
							<tr><td><input type="hidden" name="count" id="count" value="<%=c%>"/></td></tr>
      </table></td>
    </tr>
 <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2">
         <tr>
            <td width="78%">
            <jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
          			
	</td>
           
          </tr>
        </table>
          <label></label></td>
    </tr>

  </table>
  
	
	
	</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js">

</script>


<script type="text/javascript">


function saveFun(){
		
		var apprCode = document.getElementById("paraFrm_apprId").value;
		var tableLength = document.getElementById("count").value;
		
		//if(document.getElementById('paraFrm_appStarted').value=='Y'){
		// alert('Cannot save Appraisal schedule details,\n as the calendar is locked.');
		// return false;
		//}
		if(apprCode == ""){
			alert("Please select the "+document.getElementById("appraisal.code").innerHTML.toLowerCase());
			return false;
		}
		if(tableLength == 0 )
		{
			alert("Please define the phases for this appraisal.");
			return false;
		}
		for(var count=1;count<=eval(tableLength);count++){
						
			if(document.getElementById("phaseStartDate"+count).value == ""){
				alert("Please add the "+document.getElementById("phase.start.date").innerHTML.toLowerCase()+" for ' "+
				document.getElementById("phaseName"+count).value +" ' phase" );
				document.getElementById("phaseStartDate"+count).focus();
				return false;
			}
			
			if(!validateDate("phaseStartDate"+count, 'phase.start.date'))return false;
						
			if(!dateDifferenceEqualthis(document.getElementById("paraFrm_startDate").value,
									 document.getElementById("phaseStartDate"+count).value,"phaseStartDate"+count,
									 document.getElementById("appr.from.date").innerHTML.toLowerCase(),document.getElementById("phase.start.date").innerHTML.toLowerCase()+" of ' "+document.getElementById("phaseName"+count).value+" '")){
			
			return false;
			}
						
			//var fieldNameStart = ["paraFrm_startDate","paraFrm_endDate","phaseStartDate"+count];
			//var lableNameStart = ["appr.from.date","appr.to.date","phase.start.date"];
			//if(!dateBetweenTwoDates(fieldNameStart, lableNameStart)){
			//	return false;
			//}
												
			if(document.getElementById("phaseEndDate"+count).value == ""){
				alert("Please add the "+document.getElementById("phase.end.date").innerHTML.toLowerCase()+" for ' "+
				document.getElementById("phaseName"+count).value+" ' phase");
				document.getElementById("phaseEndDate"+count).focus();
				return false;
			}
			
			if(!validateDate("phaseEndDate"+count, "phase.end.date"))return false;
			
			//var fieldNameEnd = ["paraFrm_startDate","paraFrm_endDate","phaseEndDate"+count];
			//var lableNameEnd = ["appr.from.date","appr.to.date","phase.end.date"];	
			//if(!dateBetweenTwoDates(fieldNameEnd, lableNameEnd)){
			//	return false;
			//	}
			
			if(!dateDifferenceEqualthis(document.getElementById("phaseStartDate"+count).value,
									 document.getElementById("phaseEndDate"+count).value,"phaseEndDate"+count,
									 document.getElementById("phase.start.date").innerHTML.toLowerCase()+" of ' "+document.getElementById("phaseName"+count).value+" '",document.getElementById("phase.end.date").innerHTML.toLowerCase()+" of ' "+document.getElementById("phaseName"+count).value+" '")){
			
			return false;
			}
						
			
			
			if(count > 1){
								
				if(!dateDifferenceEqualthis(document.getElementById("phaseStartDate"+eval(count-1)).value,
									 document.getElementById("phaseStartDate"+count).value,"phaseStartDate"+count,
									 document.getElementById("phase.start.date").innerHTML.toLowerCase()+" of ' "+document.getElementById("phaseName"+eval(count-1)).value+" '",document.getElementById("phase.start.date").innerHTML.toLowerCase()+" of ' "+document.getElementById("phaseName"+count).value+" '")){
			
					return false;
				}
				
				if(!dateDifferenceEqualthis(document.getElementById("phaseEndDate"+eval(count-1)).value,
									 document.getElementById("phaseEndDate"+count).value,"phaseEndDate"+count,
									 document.getElementById("phase.end.date").innerHTML.toLowerCase()+" of ' "+document.getElementById("phaseName"+eval(count-1)).value+" '",document.getElementById("phase.end.date").innerHTML.toLowerCase()+" of ' "+document.getElementById("phaseName"+count).value+" '")){
			
					return false;
				}
							
			}
			
		}		
			document.getElementById("paraFrm").action="AppraisalSchedule_save.action";
			document.getElementById("paraFrm").submit();
		
		
}
function resetFun(){
		
		document.getElementById("paraFrm").action="AppraisalSchedule_reset.action";
		document.getElementById("paraFrm").submit();
		
}
function cancelFun(){

		document.getElementById("paraFrm").action="AppraisalCalendar_input.action";
	    document.getElementById("paraFrm").submit();	
				
}

function deleteFun(){

		alert(document.getElementById("paraFrm_lockAppr").value);
		if(document.getElementById("paraFrm_lockAppr").value == 'N')
		{
			document.getElementById("paraFrm").action="AppraisalSchedule_delete.action";
			document.getElementById("paraFrm").submit();
		}else{
			alert("Appraisal schedule cannot be deleted as it is referenced to some other resources.");
		}	
		
}

function dateDifferenceEqualthis(fromDate, toDate, fieldName, fromLabName, toLabName){
	var strDate1 = fromDate.split("-"); 
	var starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
	
	var strDate2 = toDate.split("-"); 
	var endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 

	if(endtime < starttime) 
	{ 
		alert(toLabName+" should be greater or equal to "+fromLabName);
		document.getElementById(fieldName).focus();
		return false;
	}
	return true;
}
</script>
