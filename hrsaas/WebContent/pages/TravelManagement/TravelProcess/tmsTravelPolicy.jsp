
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/pages/TravelManagement/TravelProcess/tmsAjax.js"></script>
<s:form action="TravelPolicy" validate="true" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		class="formbg" cellspacing="2">
		<tr>
			<td colspan="3" valign="bottom" class="txt">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" align="center" class="formbg">
				<tr>
					<td valign="bottom" class="txt" width="3%" colspan="1"><strong
						class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="97%" class="txt" colspan="2"><strong
						class="text_head">Travel Policy </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="80%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>


					<td width="20%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			<label></label></td>
		</tr>

		<tr>
			<td colspan="3">
			<div id="policyDiv"
				style='position: absolute; z-index: 3; width: 300px; display: none; border: 2px solid; top: 70px; left: 200px; padding: 10px'
				class="formbg">
			<table width="100%" border="0" cellpadding="10" cellspacing="1">
				<tr>
					<td width="35%" colspan="3" align="center"><label
						id="policyGrade"></label><br>
					Do you really want to overWrite?</br>
					</td>
				</tr>

				<tr>
					<td colspan="3" align="center"><input type="button"
						class="token" value="  Yes  " style="cursor: pointer;"
						onclick="overWriteGradesForPage('Y');"> <input
						type="button" class="token" value="   No  "
						style="cursor: pointer;" onclick="overWriteGradesForPage('N');">
					<input type="button" class="cancel" value=" Cancel"
						style="cursor: pointer;"
						onclick="document.getElementById('policyDiv').style.display = 'none'; "></td>
				</tr>
			</table>
			</div>
			</td>
		</tr>

		<s:hidden name="onloadstatus" />
		<s:hidden name="tp.policyId" />
		<s:hidden name="activeflag" />
		<s:hidden name="activeflag1" />
		<s:hidden name="empGradeId" />
		<input type="hidden" name="actionName" id="actionName">
		<input type="hidden" name="rowCount" id="rowCount">
		<input type="hidden" name="existGrades" id="existGrades">
		<input type="hidden" id="overWrite" />
		<s:hidden name="checkActive" value="%{status}" />
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="2" class="formbg">
<!-- Start of s:if -->
				<s:if test="editFlag">
					<tr>
						<td width="35%" colspan="1">
							<label class="set" name="policy.name" id="policy.name" ondblclick="callShowDiv(this);"><%=label.get("policy.name")%></label><font color="red">*</font> :
						</td>
						<td>
							<s:hidden name="policyName" /> <s:property	value="policyName" />
						</td>
					</tr>
			<!-- 
			
						<tr>
						<td width="35%" colspan="1">
							<label class="set"	name="abbreviation" id="abbreviation" ondblclick="callShowDiv(this);"><%=label.get("abbreviation")%></label><font color="red">*</font> :
						</td>
						<td>
							<s:property value="policyAbbr" /> <s:hidden name="policyAbbr" />
						</td>
					</tr>
			 -->
					
			<!-- (Start) Added by manish sakpal -->		
					<tr>
						<td width="35%" colspan="1">
							<label class="set"	name="advance.allow" id="advance.allow" ondblclick="callShowDiv(this);"><%=label.get("advance.allow")%></label> :
						</td>
						<td>
							<s:checkbox  name="policyAdvanceAllowed" id="policyAdvanceAllowed" disabled="true"/> <s:hidden name="policyAdvanceAllowed" />
						</td>
					</tr>			
				
					<tr>
						<td width="35%" colspan="1">
							<label class="set"	name="maxDaysToSettleTravelClaim" id="maxDaysToSettleTravelClaim" ondblclick="callShowDiv(this);"><%=label.get("maxDaysToSettleTravelClaim")%></label> :
						</td>
						<td>
							<s:property value="maxDaysTravelClaim" /> <s:hidden name="maxDaysTravelClaim" />
						</td>
					</tr>	
					
					<tr>
						<td width="35%" colspan="1">
							<label class="set"	name="minNumberOfAdvanceDaysToApplyForTravel" id="minNumberOfAdvanceDaysToApplyForTravel" ondblclick="callShowDiv(this);"><%=label.get("minNumberOfAdvanceDaysToApplyForTravel")%></label> :
						</td>
						<td>
							<s:property value="minNoOfAdvanceDaysToApplyForTravel" /> <s:hidden name="minNoOfAdvanceDaysToApplyForTravel" />
						</td>
					</tr>			
			<!-- (End) Added by manish sakpal -->
					<tr>
						<td width="35%" colspan="1"><label class="set" name="effDate"
							id="effDate" ondblclick="callShowDiv(this);"><%=label.get("effDate")%></label><font
							color="red">*</font> :</td>
						<td><s:property value="effectDate" /> <s:hidden
							name="effectDate" /></td>
					</tr>
					
					<!-- 
						<tr>
						<td width="35%" colspan="1"><label class="set"
							id="policy.desc" name="policy.desc"
							ondblclick="callShowDiv(this);"><%=label.get("policy.desc")%></label>
						:</td>
						<td><s:property value="desc" /> <s:hidden name="desc" /></td>
					</tr>				
					 -->		
					<tr>
						<td width="35%" colspan="1">
							<label class="set" id="status"	name="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label><font	color="red">*</font>:
						</td>
						<td>
							<s:property value="status" /> <s:hidden name="status" />
						</td>
					</tr>
			
			<!-- (Start) Added by manish sakpal -->		
					<tr>
						<td width="35%" colspan="1">
							<label class="set"	name="payModeForAdvanceClaim" id="payModeForAdvanceClaim" ondblclick="callShowDiv(this);"><%=label.get("payModeForAdvanceClaim")%></label> :
						</td>
						<td>
							<table>
								<tr width="20%">
									<td width="5%">
										Cheque <s:checkbox  name="payModeForAdvanceClaimCheque" id="payModeForAdvanceClaimCheque" disabled="true"/>													
									</td>
									
									<td width="5%">
										Cash <s:checkbox  name="payModeForAdvanceClaimCash" id="payModeForAdvanceClaimCash" disabled="true"/>
									</td>
									
									<td width="5%">
										Transfer <s:checkbox  name="payModeForAdvanceClaimTransfer" id="payModeForAdvanceClaimTransfer" disabled="true"/>		
									</td>
									
									<td width="5%">
										In Salary <s:checkbox  name="payModeForAdvanceClaimInSalary" id="payModeForAdvanceClaimInSalary" disabled="true"/>
									</td>
								</tr>						
							</table>	
							
						</td>
					</tr>	
					
					
					<tr>
						<td width="35%" colspan="1">
							<label class="set"	name="travelType" id="travelType" ondblclick="callShowDiv(this);"><%=label.get("travelType")%></label> :
						</td>
						<td>							
							<table>
								<tr width="20%">
									<td width="5%">
										Self <s:checkbox  name="travelTypeSelf" id="travelTypeSelf" disabled="true"/>													
									</td>
									
									<td width="5%">
										Team <s:checkbox  name="travelTypeTeam" id="travelTypeTeam" disabled="true"/>
									</td>
									<!--
									<td width="5%">
										Guest <s:checkbox  name="travelTypeGuest" id="travelTypeGuest" disabled="true"/>		
									</td>
									  -->
									 <td width="5%"></td>
									<td width="5%"> </td>									
								</tr>						
							</table>		
						</td>
					</tr>	
					
					<tr>
						<td width="35%" colspan="1">
							<label class="set"	name="travelCurrencyLabel" id="travelCurrencyLabel" ondblclick="callShowDiv(this);"><%=label.get("travelCurrencyLabel")%></label> :
						</td>
						<td>							
							  <s:select name="travelCurrency" list="tarvelCurrencyList" />
						</td>
					</tr>	
											
			<!-- (End) Added by manish sakpal -->		
				</s:if>
<!-- End of s:if -->				
				
<!-- End of s:else -->				
				<s:else>
					<tr>
						<td width="35%" colspan="1"><label class="set"
							name="policy.name" id="policy.name"
							ondblclick="callShowDiv(this);"><%=label.get("policy.name")%></label><font
							color="red">*</font> :</td>
						<td><s:textfield size="25" maxlength="30" name="policyName"
							onkeypress="return alphaNumeric();" /></td>
					</tr>
			<!-- 
				<tr>
						<td width="35%" colspan="1">
							<label class="set"	name="abbreviation" id="abbreviation" ondblclick="callShowDiv(this);"><%=label.get("abbreviation")%></label>
							<font color="red">*</font> :
						</td>
						<td>
							<s:textfield size="25" maxlength="10" name="policyAbbr"	onkeypress="return allCharacters();" />
						</td>
				</tr>
			 -->
				<!-- (Start) Added by manish sakpal -->			
					<tr>
						<td width="35%" colspan="1">
							<label class="set"	name="advance.allow" id="advance.allow"	ondblclick="callShowDiv(this);"><%=label.get("advance.allow")%></label> :
						</td>
						<td><s:checkbox  name="policyAdvanceAllowed" id="policyAdvanceAllowed"/></td>
					</tr>
					
					<tr>
						<td width="35%" colspan="1">
							<label class="set"	name="maxDaysToSettleTravelClaim" id="maxDaysToSettleTravelClaim" ondblclick="callShowDiv(this);"><%=label.get("maxDaysToSettleTravelClaim")%></label> :
						</td>
						<td>
							<s:textfield size="20" maxlength="3" name="maxDaysTravelClaim" onkeypress="return numbersOnly();"/>
						</td>	
					</tr>
					
					<tr>
						<td width="35%" colspan="1">
							<label class="set"	name="minNumberOfAdvanceDaysToApplyForTravel" id="minNumberOfAdvanceDaysToApplyForTravel" ondblclick="callShowDiv(this);"><%=label.get("minNumberOfAdvanceDaysToApplyForTravel")%></label> :
						</td>
						<td>
							<s:textfield size="20" maxlength="3" name="minNoOfAdvanceDaysToApplyForTravel" onkeypress="return numbersOnly();"/>
						</td>
					</tr>		
				<!-- (End) Added by manish sakpal -->	
					
					<tr>
						<td width="35%" colspan="1"><label class="set" name="effDate"
							id="effDate" ondblclick="callShowDiv(this);"><%=label.get("effDate")%></label><font
							color="red">*</font> :</td>
						<td><s:textfield name="effectDate" size="20"
							onkeypress="return numbersWithHiphen();" maxlength="10"></s:textfield>
						<s:a href="javascript:NewCal('paraFrm_effectDate','DDMMYYYY');">
							<img src="../pages/images/recruitment/Date.gif" class="iconImage"
								height="18" align="absmiddle" width="18" id="ctrlHide">
						</s:a></td>
					</tr>
				<!-- 
				
					<tr>
						<td width="35%" colspan="1"><label class="set"
							id="policy.desc" name="policy.desc"
							ondblclick="callShowDiv(this);"><%=label.get("policy.desc")%></label>
						:</td>
						<td width="80%" colspan="2"><s:textarea name="desc" cols="50"
							rows="4" onkeyup="callLength('desc','descCnt','2000');" /> <img
							src="../pages/images/zoomin.gif" class="iconImage" height="12"
							width="12"
							onclick="callWindow('paraFrm_desc','policy.desc','','paraFrm_descCnt','2000');">
						Remaining chars <input type="text" name="descCnt"
							id="paraFrm_descCnt" size="5" readonly="readonly"></td>
					</tr>			
				 -->	
					
					<tr>
						<td width="35%" colspan="1"><label class="set" id="status"
							name="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label><font
							color="red">*</font> 
						:</td>
						<td>
							<s:select name="status"	list="#{'S':'--Select--','D':'Deactive','A':'Active'}" />
						</td>
					</tr>
					
			<!-- (Start)Added by manish sakpal -->
					<tr>
						<td width="35%" colspan="1">
							<label class="set"	name="payModeForAdvanceClaim" id="payModeForAdvanceClaim"	ondblclick="callShowDiv(this);"><%=label.get("payModeForAdvanceClaim")%></label> :
						</td>
						<td>
							<table>
								<tr width="20%">
									<td width="5%">
										Cheque <s:checkbox  name="payModeForAdvanceClaimCheque" id="payModeForAdvanceClaimCheque"/>													
									</td>
									
									<td width="5%">
										Cash <s:checkbox  name="payModeForAdvanceClaimCash" id="payModeForAdvanceClaimCash" />
									</td>
									
									<td width="5%">
										Transfer <s:checkbox  name="payModeForAdvanceClaimTransfer" id="payModeForAdvanceClaimTransfer"/>		
									</td>
									
									<td width="5%">
										In Salary <s:checkbox  name="payModeForAdvanceClaimInSalary" id="payModeForAdvanceClaimInSalary"/>
									</td>
								</tr>						
							</table>						
						</td>
					</tr>		
					
					
					<tr>
						<td width="35%" colspan="1">
							<label class="set"	name="travelType" id="travelType"	ondblclick="callShowDiv(this);"><%=label.get("travelType")%></label> :
						</td>
						<td>
							<table>
								<tr width="20%">
									<td width="5%">
										Self <s:checkbox  name="travelTypeSelf" id="travelTypeSelf"/>													
									</td>
									
									<td width="5%">
										Team <s:checkbox  name="travelTypeTeam" id="travelTypeTeam"/>
									</td>
									<!-- 
									<td width="5%">
										Guest <s:checkbox  name="travelTypeGuest" id="travelTypeGuest"/>		
									</td>
									 -->
									 <td width="5%"></td>
									<td width="5%"> </td>									
								</tr>						
							</table>						
						</td>
					</tr>	
					
					<tr>
						<td width="35%" colspan="1">
							<label class="set"	name="travelCurrencyLabel" id="travelCurrencyLabel" ondblclick="callShowDiv(this);"><%=label.get("travelCurrencyLabel")%></label> :
						</td>
						<td>							
							 <s:select name="travelCurrency" list="tarvelCurrencyList" />
						</td>
					</tr>					
			<!-- (End)Added by manish sakpal -->					
				</s:else>
<!-- End of s:else -->				
				<tr>
					<td colspan="3" valign="bottom" class="txt">&nbsp;</td>
				</tr>


				</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

					<td width="35%" align="right"><strong><font
						color="black">Page 1 of 3</font></strong></td>
				</tr>
			</table>
	</table>
</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script type="text/javascript">
 
   function textCounter(field,  maxlimit) { 
	  if (field.value.length > maxlimit){			
		alert('Field value should not exceed '+maxlimit+' chars.');
		field.value = field.value.substring(0, maxlimit);	
		field.focus;			
		return false;	 
		//field.value = field.value.substring(0, maxlimit);			
	}		
	return true;
	}
 
   function addnewFun() {  
      document.getElementById('paraFrm').action = "TravelPolicy_addNew.action";
	  document.getElementById('paraFrm').submit(); 
	  document.getElementById('paraFrm').target ="main"; 
     }
    
     function returntolistFun() {  
    //alert('cancel')
      document.getElementById('paraFrm').action = "TravelPolicy_cancel.action?saveType=policy";
	  document.getElementById('paraFrm').submit();
	  document.getElementById('paraFrm').target ="main"; 
    }
    function nextFun() {   
    /* if(!saveValidate())
     {
      return true;
     } */  
      document.getElementById('paraFrm').action = "TravelPolicy_firstNext.action";
	  document.getElementById('paraFrm').submit();
	  document.getElementById('paraFrm').target ="main"; 
    }
    
    function saveFun()   
    {   
	  try
	  {  
	  	if(!saveValidate()) 
	  	{
	  		return false;
	  	} 
	  	//alert('y11--'+document.getElementById('paraFrm_tp_policyId').value);
	  	var policyId = document.getElementById('paraFrm_tp_policyId').value; 
	  	var status =document.getElementById('paraFrm_status').value;
	  	activeflag=document.getElementById('paraFrm_activeflag').value;
	  	// alert('activeflag'+activeflag);
	  	checkActive=document.getElementById('paraFrm_checkActive').value;
	  	//alert('status'+status);
	  	// alert('activeflag'+activeflag);
	  	document.getElementById('rowCount').value = 1;
	  	// alert('checkActive'+checkActive);
	  	//Currently selected is active, Filled up policy, existing is deactive 
	  	if(status=='A' && activeflag=='true'&& checkActive=='D') 
	  	{
		  	// alert('in if--');
		  document.getElementById('actionName').value = "TravelPolicy_save.action?saveType=success&";
	  		//alert('before');
	  		try 
	  		{
	  			//alert('try11');
	  			TravelCheckDupPolicyNew('TravelPolicy_beforeSaveInFirstPage.action?policyId='+policyId+'&abcd='+Math.random()+'&status=D');
	  		}
	  		catch(e) 
	  		{
	  			alert(e);
	  		}
	    	
    	}
    	else 
    	{
     		//alert('in else');
         	document.getElementById('paraFrm').action = "TravelPolicy_save.action?saveType=success";
		 	document.getElementById('paraFrm').submit();
		 	document.getElementById('paraFrm').target ="main";  
     	} 
    	return false;
    }
    catch(e)
    {
    	alert("Error ===== "+e);
    }
   }
   
   
   
   
   
   function  saveandnextFun()  
   {
   	try
   	{	
   		if(!saveValidate())
    	{
    		return false;
    	}
	     
    var policyId = document.getElementById('paraFrm_tp_policyId').value; 
    var status =document.getElementById('paraFrm_status').value;
    activeflag=document.getElementById('paraFrm_activeflag').value;
    checkActive=document.getElementById('paraFrm_checkActive').value;
    //   alert('status'+status);
	// alert('activeflag'+activeflag);
    document.getElementById('rowCount').value = 1;
    if(status=='A' && activeflag=='true'&& checkActive=='D')   {
   // alert('in if--');
    document.getElementById('actionName').value = "TravelPolicy_firstSaveandNext.action?";
   // alert('before');
     try{
	//alert('try');
   	TravelCheckDupPolicyNew('TravelPolicy_beforeSaveInFirstPage.action?policyId='+policyId+'&abcd='+Math.random()+'&status=D');
   	}catch(e){
   		alert(e);
   	}
    }
    else {
	 document.getElementById('paraFrm').action = "TravelPolicy_firstSaveandNext.action";
	 document.getElementById('paraFrm').submit();
	 document.getElementById('paraFrm').target ="main";  
     } 
     return false;
   }
   catch(e)
   	{
   		alert("Error ==== "+e);
   	}
   }
    
    function editFun()
    {    
   //  alert('x');
    document.getElementById('paraFrm').action = "TravelPolicy_edit.action?saveType=success";
    document.getElementById('paraFrm').submit();
    document.getElementById('paraFrm').target ="main"; 
    }
    
    function searchFun()
    {  
    document.getElementById('paraFrm_hidFlag').value="search";
    callsF9(500,325,'TravelPolicy_f9action.action');
    }
    
    function deleteFun()
    {  
    var conf=confirm("Do you really want to delete this record ?");
  	if(conf) 
  	{
 	 // document.getElementById('paraFrm_editFlag').value="false";
	 document.getElementById('paraFrm').action = "TravelPolicy_delete.action";
	 document.getElementById('paraFrm').submit();
	 document.getElementById('paraFrm').target ="main";
     }else
  	 {
  	 return false;
  	 } 
   }
 
     /* function saveFun()
     {   
     alert('x');
	     if(!saveValidate())
	     {
	      return true;
	     } 
	     alert('y11--'+document.getElementById('paraFrm_tp_policyId').value);
	      var policyId = document.getElementById('paraFrm_tp_policyId').value; 
	      var status =document.getElementById('paraFrm_status').value;
	      activeflag=document.getElementById('paraFrm_activeflag').value;
	       checkActive=document.getElementById('paraFrm_checkActive').value;
	      
	      alert('status'+status);
	      alert('activeflag'+activeflag);
	     document.getElementById('rowCount').value = 1;
	     if(status=='A' && activeflag=='true'&& checkActive=='   D')
	     {
	     document.getElementById('actionName').value = "TravelPolicy_save.action?saveType=success&";
	     alert('before');
	     try
		{
		alert('try');
    	TravelCheckDupPolicyNew('TravelPolicy_beforeSave.action?policyId='+policyId+'&abcd='+Math.random());
    	}catch(e)
    	{
    		alert(e);
    	}
        }
        
     else
     {
         document.getElementById('paraFrm').action = "TravelPolicy_save.action?saveType=success";
		 document.getElementById('paraFrm').submit();
		 document.getElementById('paraFrm').target ="main";  
     }   
    }*/
    
   function saveValidate()
    {
                
   var status =document.getElementById('paraFrm_status').value;
 
      policyName = trim(document.getElementById('paraFrm_policyName').value); 
	    if(policyName=="")
	    {
	     alert("Please enter Policy Name.");
	     document.getElementById('paraFrm_policyName').focus();
	     return false;
	    } 
	   
	   /*
	     policyAbbr =trim(document.getElementById('paraFrm_policyAbbr').value);
	    if(policyAbbr=="")
	    {
	     alert("Please enter Policy Abbreviation .");
        document.getElementById('paraFrm_policyAbbr').focus();
	     return false;
	    }
	   */  
	   /*var policyAbbrValue = LTrim(RTrim(policyAbbr));
		  if(policyAbbr!=policyAbbrValue){
			alert('Space not Allowed in Abbreviation.');
			  document.getElementById('paraFrm_policyAbbr').value="";
	          document.getElementById('paraFrm_policyAbbr').focus();
			 return false;
		  }  
	  var policyNameValue = LTrim(RTrim(policyName));
	  if(policyName!=policyNameValue){
		  alert('Space not Allowed in Policy Name.');
		 document.getElementById('paraFrm_policyName').value="";
	     document.getElementById('paraFrm_policyName').focus();
		 return false;
	  } */
	     if( document.getElementById('paraFrm_effectDate').value=="")
	    {
	     alert("Please enter/select Effective From date.");
	     document.getElementById('paraFrm_effectDate').focus();
	     return false;
	    } 
	   if(!validateDate('paraFrm_effectDate','effDate'))
	   {
	   	return false;
	   }
			if(status=="S")
			{	
			 alert("Please select Status.");
			 return false;
			}
	
	/*
	   if(eval(document.getElementById('paraFrm_descCnt').value)<0){
			alert('Maximum length of policy description is 2000');
		    document.getElementById('paraFrm_desc').focus();
		  	return false;
	   }
	 */  
	   /*if(!dateCheckWithToday('paraFrm_effectDate', 'effDate'))
   			return false;
   		*/	
	    return true;
	    
    }
function overWriteGradesForPage(flag)
{
	document.getElementById('paraFrm').target="main";
	document.getElementById('paraFrm').action = document.getElementById('actionName').value+'overWrite='+flag;
	document.getElementById('paraFrm').submit();      		 
}
    
    function afterCallingAjax()
    {
    var val  = document.getElementById('existGrades').value;
	if(val.length == 1)
		document.getElementById('policyGrade').innerHTML = 'Grades '+val+' is already defined in other active policies.';
	else
		document.getElementById('policyGrade').innerHTML = 'Grades '+val+' are already defined in other active policies.';
     document.getElementById('policyDiv').style.display = "";
     return false;   
    }
   function checkDupPolicyNew()
    {
   // alert('out');
    var policyId = document.getElementById('paraFrm_policyId').value;
    TravelCheckDupPolicyNew('TravelPolicy_beforeSaveInFirstPage.action?policyId='+policyId+'&abcd='+Math.random());
    //alert("After ajax");
    }  
 
    
    
    
  
  
function numbersOnly(e)
{
	
	document.onkeypress = numbersOnly;
	var key //= (window.event) ? event.keyCode : e.which;
	if (window.event)
	    key = event.keyCode
	else
	   	key = e.which
	clear();
	// Was key that was pressed a numeric character (0-9) or backspace (8)?
	if ( key > 47 && key < 58 || key == 8 || key == 0)
	 	return true; // if so, do nothing
	else // otherwise, discard character
		return false;
	if (window.event) //IE
	    window.event.returnValue = null;     else //Firefox
	    e.preventDefault();
}     
</script>
