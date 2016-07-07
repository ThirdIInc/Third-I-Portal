<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>
<script type="text/javascript" src="../pages/common/employeeTree.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="AppraiserConfig" theme="simple" method="post" name="paraFrm" id="paraFrm">
<s:hidden name="apprId"/>
<s:hidden name="groupId"/>
<s:hidden name="frmDate"/>
<s:hidden name="toDate"/>
<s:hidden name="calUpdateflag"/>
<table width="100%" border="0" align="right" cellpadding="2" cellspacing="2" class="formbg">
 			<tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Define Appraiser for the Group </strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
			
			<tr>
	          <td colspan="3"> 
	          
	          <table width="100%" border="0" cellpadding="2" cellspacing="2">
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
            <td><table width="98%" border="0" align="center" cellpadding="2" cellspacing="2">
            
             <tr>
              
              
              <td width="25%" colspan="1" height="20" class="formtext"><label name="appraisal.code" class = "set"  id="appraisal.code" ondblclick="callShowDiv(this);"><%=label.get("appraisal.code")%></label> <font color="red">*</font> :</td>
			  <td width="25%" colspan="1" height="20" align="left"><s:hidden name="apprCode"  /><s:property value="apprCode" /></td>
              <td width="50%" colspan="1" height="20" class="formtext"><label name="group.name" class = "set"  id="group.name" ondblclick="callShowDiv(this);"><%=label.get("group.name")%></label>  :</td>
	        	<td width="50%" colspan="2" height="20"><s:hidden name="groupName"  /><s:property value="groupName" /></td>
            </tr>
              
            <tr>
              
              
              <td width="25%" colspan="1" height="20" class="formtext" nowrap="nowrap"><label name="phase.name" class = "set"  id="phase.name" ondblclick="callShowDiv(this);"><%=label.get("phase.name")%></label> <font color="red">*</font> :</td>
			  <td width="25%" colspan="1" height="20" nowrap="nowrap"><s:textfield name="phaseName" size="20" maxlength="25" readonly="true" /><s:hidden name="phaseId" /><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="callPhase();" ></td>
              <td width="25%" height="22" class="formtext"nowrap="nowrap" colspan="1"><s:hidden name="isSelfPhase"/></td>
			  <td width="25%" colspan="1" height="20"nowrap="nowrap"></td> 
            </tr>
            
            <tr height="22">
              <td colspan="4">
              <div id="apprDiv"><table border="0" width="100%">
              <tr>
              <td width="25%" colspan="1" height="20" class="formtext"><label name="appraiser.name" class = "set"  id="appraiser.name" ondblclick="callShowDiv(this);"><%=label.get("appraiser.name")%></label> <font color="red">*</font> :</td>
			  <td width="40%" colspan="1" height="20" ><s:textfield name="appraiserId" size="10"  readonly="true"></s:textfield><s:textfield name="appraiserName" size="25"  readonly="true" /><s:hidden name="appraiserCode" /><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="javascript:callsF9(500,325,'AppraiserConfig_f9SelectAppraiser.action'); " ></td>
              <td width="15%" height="22" class="formtext" colspan="1"><label name="appraiser.level" class = "set"  id="appraiser.level" ondblclick="callShowDiv(this);"><%=label.get("appraiser.level")%> </label> <font color="red">*</font>: </td>
			  <td width="20%" colspan="1" height="20"><s:textfield name="appraiserLevel" onkeypress="return numbersOnly();" size="10" maxlength="1"  /><s:hidden name="branch"/><s:hidden name="dept"/></td> 
            </tr>
            </table></div></td></tr>
            
            <tr height="22">
            
              <td  width="100%" colspan="5" align="center" class="formtext"><s:submit value="   Add" action="AppraiserConfig_addAppraiser" cssClass="add" onclick="return callAdd();"/></td>
              
             </tr>
             
      
            </table></td>
          </tr>
          
      </table></td>
    </tr>
  
		<tr>
			<td colspan="6">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td colspan="6"><strong class="forminnerhead"><label
						class="set" id="list" name="list" ondblclick="callShowDiv(this);">Appraisee Group List</label></strong></td>
				</tr>
				<tr>
					<td class="formtext">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="sortable">
						
						<tr class="td_bottom_border">
							<td width="5%" class="formth" nowrap="nowrap"><label name="appraiser.sr.no" class = "set"  id="appraiser.sr.no" ondblclick="callShowDiv(this);"><%=label.get("appraiser.sr.no")%></label></td>
							<td width="20%" class="formth" nowrap="nowrap"><label name="appraisal.code" class = "set"  id="appraisal.code" ondblclick="callShowDiv(this);"><%=label.get("appraisal.code")%></label></td>
							<td width="20%" class="formth" nowrap="nowrap"><label name="phase.name" class = "set"  id="phase.name1" ondblclick="callShowDiv(this);"><%=label.get("phase.name")%></label></td>
							<td width="20%" class="formth" nowrap="nowrap"><label name="appraiser.name" class = "set"  id="appraiser.name1" ondblclick="callShowDiv(this);"><%=label.get("appraiser.name")%></label></td>
							<td width="10%" class="formth" nowrap="nowrap"><label name="appraiser.level" class = "set"  id="appraiser.level1" ondblclick="callShowDiv(this);"><%=label.get("appraiser.level")%></label></td>
							<td width="10%" class="formth" nowrap="nowrap"><input type="button" value="   Remove" class="del" onclick="callRemove();"></td>
						</tr>
						<% int i = 0;%>

						<s:iterator value="appraiserList">


							<tr class="sortableTD">
								<td class="sortableTD" width="5%" align="center"><%=i+1%></td>
								<td class="sortableTD" width="5%"><s:property value="appraisalCodeList" /><s:hidden
									name="appraisalCodeList"></s:hidden><s:hidden
									name="appraisalIdList" ></s:hidden></td>
								<td class="sortableTD" width="5%"><s:property value="phaseNameList" /><s:hidden
									name="phaseNameList"/><s:hidden
									name="phaseIdList" id='<%="phaseIdList"+i%>'/></td>
								<td class="sortableTD" width="5%"><s:property value="appraiserNameList" /><s:hidden
									name="appraiserNameList"/><s:hidden
									name="appraiserCodeList" id='<%="appraiserCodeList"+i%>'/><s:hidden name="isSelfPhaseList"/></td>
								<td class="sortableTD" width="5%" align="center"><s:property value="appraisalLevelList" /><s:hidden
									name="appraisalLevelList" id='<%="appraisalLevelList"+i%>'/></td>
								<td class="sortableTD" width="5%" align="center"><input type="checkbox" id='<%="removeAppraiser"+i%>' name="removeAppraiser"></td>
								
							</tr>

							<%
			i++;
			%>
						</s:iterator>
			
			<input type="hidden" name="count" id="count" value="<%=i%>"/>
			<s:hidden name="paraId"></s:hidden>
				<%if(i==0){ %>			       	
				       	 <tr>
				 	          <td colspan="7" class="sortableTD" align="center"><font color="red">No Data To Display</font></td>
				 	     </tr>			       	
			       	<%} %>   
					</table>

					</td>
				
			</table>
			</td>
		</tr>
	        
	        
	        <tr>
	          <td colspan="3"> 
	          
	          <table width="100%" border="0" cellpadding="2" cellspacing="2">
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
<script>

function saveFun(){
	
				
				document.getElementById("paraFrm").action="AppraiserConfig_saveAppraiser.action";
				document.getElementById("paraFrm").submit();
	}
	function backFun(){
	
				document.getElementById('paraFrm_groupName').value="";
				document.getElementById("paraFrm").action="AppraiserConfig_setApprGroupList.action";
				document.getElementById("paraFrm").submit();
	}
function onload(){
	
	if(document.getElementById("paraFrm_isSelfPhase").value=="Y"){
		document.getElementById('apprDiv').style.display='none';
		document.getElementById('paraFrm_appraiserName').value="";
		document.getElementById('paraFrm_appraiserId').value="";
		document.getElementById('paraFrm_appraiserCode').value="";
		document.getElementById('paraFrm_appraiserLevel').value="1";
	}else
	{
	document.getElementById('apprDiv').style.display='';
	document.getElementById('paraFrm_appraiserLevel').value="";
	}
	
	}
	onload();
	function callPhase(){

	if(document.getElementById("paraFrm_apprId").value==""){
		alert("Please select "+document.getElementById("appraisal.code").innerHTML+" .");
		return false;
	}	
	javascript:callsF9(500,325,'AppraiserConfig_f9SelectPhase.action'); 
}

function callAdd(){
		var countCheck = document.getElementById("count").value;
		var fields=["paraFrm_phaseName","paraFrm_appraiserName","paraFrm_appraiserLevel"];
		var labels=[document.getElementById("phase.name").innerHTML,document.getElementById("appraiser.name").innerHTML,
		document.getElementById("appraiser.level").innerHTML];
		var types=["select","select","enter"];
		
			
		var phaseId= document.getElementById("paraFrm_phaseId").value;
		var appraiserId = document.getElementById("paraFrm_appraiserCode").value;
		var appraiserLevel = document.getElementById("paraFrm_appraiserLevel").value;
		
		if(document.getElementById("paraFrm_isSelfPhase").value=="Y"){
			if(document.getElementById("paraFrm_phaseName").value=="")
			{
				alert("Please select "+document.getElementById("phase.name").innerHTML);
				document.getElementById("paraFrm_phaseName").focus();
				return false;
			}
		}else
		{
			if(!(checkMandatory(fields,labels,types)))
			{
				return false;
			}
			
			/* Appraisal Level */
			if (appraiserLevel == 0)
			{
				alert("Appraisal Level '0' is not allowed.");			
				document.getElementById("paraFrm_appraiserLevel").value = "";
				document.getElementById("paraFrm_appraiserLevel").focus();
				return false;
			} 
		}
		
		
		
		if(!(countCheck =="0")){
		
			for(j=0;j<countCheck;j++){
				var phaseIdList= document.getElementById("phaseIdList"+j).value;
				var appraiserCodeList= document.getElementById("appraiserCodeList"+j).value;
				var appraisalLevelList = document.getElementById("appraisalLevelList"+j).value;
				if(phaseId == phaseIdList && appraiserId == appraiserCodeList ){
					if(appraiserId==""){
					alert("Phase is already added");
						return false;
					}else{
					alert("' "+document.getElementById("paraFrm_appraiserName").value+" ' is already added for this phase. ");
					return false;
					}
				}
				if(phaseId == phaseIdList && appraiserLevel == appraisalLevelList ){
				
					alert("Level  ' "+appraiserLevel+" ' Appraiser is already added for this phase. ");
					return false;
				}
				
			}
		}
		
		//document.getElementById("paraFrm_addQuestionFlag").value="true";
}

function callRemove(){
		var countCheck = document.getElementById("count").value;
		var counter="";
		if(!(countCheck =="0")){
		
			for(j=0;j<countCheck;j++){
					if(document.getElementById("removeAppraiser"+j).checked){
						counter += j+",";
					}			
			}
		}
		if(counter==""){
			alert("Please select atleast one Appriser to remove.");
			return false;
			}
		var conf=confirm("Do you really want to remove these Appraisers ?");
  			if(conf) {
			  		document.getElementById("paraFrm_paraId").value = counter;
					document.getElementById("paraFrm").action="AppraiserConfig_removeMultiple.action";
					document.getElementById("paraFrm").submit();
  			}else{
  				return false;
  			}
		
	}
</script>