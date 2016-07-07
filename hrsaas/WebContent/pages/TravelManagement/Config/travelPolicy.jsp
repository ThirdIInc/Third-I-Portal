 <%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="TravelPolicy" validate="true" id="paraFrm" 	theme="simple"> 	 
	<table width="100%" border="0" align="right" cellpadding="0"  	class="formbg"	cellspacing="2">
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
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			<label></label></td>
		</tr>    

		<tr>
			<td colspan="3">
		
			<table width="100%" border="0" cellpadding="0" cellspacing="2" class="formbg">
				<s:if test="editFlag"> 
				 		
				 		<tr>
							<td width="20%" colspan="1"> 
								<label  class = "set" name="policy.name" id="policy.name" ondblclick="callShowDiv(this);"><%=label.get("policy.name")%></label> <font color="red">*</font> :
							</td>
							<td>  
								<s:hidden name="policyName"/>    
								<s:property value="policyName"/> 
								<s:hidden  name="policyId" />  
							</td> 
						</tr> 	
						
						<tr>
							<td width="20%" colspan="1">
								<label  class = "set" name="abbreviation" id="abbreviation" ondblclick="callShowDiv(this);"><%=label.get("abbreviation")%></label><font color="red">*</font> :
							</td>
							<td>   
								<s:property value="policyAbbr"/> 
								<s:hidden name="policyAbbr"/>    
							</td>
						</tr> 
						 
						
						<tr>  
							<td width="20%" colspan="1">
								<label  class = "set" name="effDate" id="effDate" ondblclick="callShowDiv(this);"><%=label.get("effDate")%></label><font color="red">*</font> :
							</td>
							<td>  
								<s:property value="effectDate"/> <s:hidden name="effectDate"/>  
							 </td>
						</tr>   
						
						<tr>
							<td width="20%" colspan="1">
								<label  class = "set"  id="policy.desc" name ="policy.desc" ondblclick="callShowDiv(this);"><%=label.get("policy.desc")%></label> :
							</td>
							<td>   
								<s:property value="desc"/> <s:hidden name="desc"/>   
							</td>
						</tr> 
						
						<tr>
							<td width="20%" colspan="1">
								<label  class = "set"  id="status"  name ="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label> :
							</td>							
							<td> 	 
								<s:property value="editStatus"/> 
								<s:hidden name="status"/> 
								<s:hidden name="editStatus"/>    
							</td>					
		                 </tr> 
		  </s:if>
		  <s:else>
		  <tr>
							<td width="20%" colspan="1"> <label  class = "set" name="policy.name" id="policy.name" ondblclick="callShowDiv(this);"><%=label.get("policy.name")%></label><font color="red">*</font> :</td>
							<td>  <s:textfield size="25" name="policyName" />  
							<s:hidden  name="policyId" /> 
							</td> 
						</tr> 	
						<tr  >
							<td width="20%" colspan="1"><label  class = "set" name="abbreviation" id="abbreviation" ondblclick="callShowDiv(this);"><%=label.get("abbreviation")%></label><font color="red">*</font> :</td>
							<td>   <s:textfield size="25" name="policyAbbr" onkeypress="return allCharacters();" maxlength="50"  />  
							
							</td>
						</tr> 
						 
						<tr >  
							<td width="20%" colspan="1"><label  class = "set" name="effDate" id="effDate" ondblclick="callShowDiv(this);"><%=label.get("effDate")%></label><font color="red">*</font> :</td>
							<td>  	 <s:textfield name="effectDate" size="10" onkeypress="return numbersWithHiphen();"  
								 
									maxlength="10"  ></s:textfield>
							<s:a href="javascript:NewCal('paraFrm_effectDate','DDMMYYYY');">
											<img src="../pages/images/recruitment/Date.gif" class="iconImage" height="18" align="absmiddle" width="18">
										</s:a>  
							 </td>
						</tr>   
						<tr >
							<td width="20%" colspan="1"><label  class = "set"  id="policy.desc" name ="policy.desc" ondblclick="callShowDiv(this);"><%=label.get("policy.desc")%></label> :</td>
							<td>   	<s:textarea name="desc" cols="90" rows="2" />  </td>
						</tr> 
						<tr  >
							<td width="20%" colspan="1"><label  class = "set"  id="status"  name ="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label> :</td>
							<td> 
							 <s:select name="status" list="#{'A':'Active','D':'Dective'}" />   </td>
					
		                 </tr> 
		  </s:else>
		  <tr>
			<td colspan="3" valign="bottom" class="txt">&nbsp;</td>
		</tr>  
		 
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

					<td width="22%"> &nbsp;
					</td>
				</tr>
			</table>
			<label></label></td>
		</tr>
	</table>
</td>
</tr>
		   <tr>
			     <td  ><img src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
			     <td>  <s:hidden name="loadFlag"/> 
				     <s:hidden name="addNewFlag"/> 
				     <s:hidden name="editFlag"/>  
				     <s:hidden name="saveFlag"/>  
				    <s:hidden name="hidFlag"/>  
				    <s:hidden  name="searchFlag" /> 
				    
				    		    <s:iterator value="gradeList"> 
										<s:hidden name="empGrade" /> 		<s:hidden name="empGradeId" />  
										<s:hidden name="hidGradeStatus" />   <s:hidden name="gradeStatus" />   
							 	</s:iterator> 
								
							<!-- hidden only Third Page --> 
			  			 		<s:hidden name="totExpAmt" />   <s:hidden name="expTravelFlag" /> 
                                <s:hidden name="expCateTravel"/>  <s:hidden name="expCateTravelId"/> 
                                <s:hidden name="expCateHotel" /> <s:hidden name="expCateHotelId"/>
								 <s:iterator value="expList">	 
							 			 <s:hidden name ="exCategory" /> <s:hidden name ="exCategoryId" />   <s:hidden name ="expCatUnit" />    
										 <s:hidden name ="expCatUnitCode" />  <s:hidden  name="expValue"  /> 
										  <s:hidden name ="actualAtt" />  <s:hidden  name="hidActualAtt"  />  <s:hidden name="readOnlyFlag"/>  
			  				     </s:iterator>	
			  				     
								 <s:iterator value="travelModeList">						 
					 			 	 <s:hidden name ="travelMode" /> <s:hidden name ="travelModeId" />  <s:hidden name ="trSrNo" />
					 			 	  <s:hidden name ="travelClass" /> <s:hidden name ="travelClassId" />   
							 		 <s:hidden name ="hidtrModeStatus"   /> <s:hidden name ="trModeStatus" /> 
	 						     </s:iterator>	
	 						     
	 						      <s:iterator value="hotelTypeList">	 
							 			 <s:hidden name ="hotelType" /> <s:hidden name ="hotelTypeId" />   
							 			  <s:hidden name ="hidHotelStatus" /> <s:hidden name ="hotelStatus" />    
							      </s:iterator>		
							      
							        <s:iterator value="roomList">	 
							 			   <s:hidden name ="roomType" /> <s:hidden name ="roomTypeId" />   
							 			  <s:hidden name ="hidRoomStatus" /> <s:hidden name ="roomStatus" />    
							      </s:iterator>						
							 				
	            <!-- hidden only -->   
			        
			        
				     </td> 
	    </tr> 
</table>
</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script type="text/javascript">
 
   
 
   function addnewFun()
     {    
         
         document.getElementById('paraFrm').action = "TravelPolicy_addNew.action";
		 document.getElementById('paraFrm').submit(); 
		 document.getElementById('paraFrm').target ="main"; 
    }
    
     function cancelFun()
     {  
         document.getElementById('paraFrm').action = "TravelPolicy_cancel.action";
		 document.getElementById('paraFrm').submit();
		 document.getElementById('paraFrm').target ="main"; 
    }
    function nextFun()
     {   
	     if(!saveValidate())
	     {
	      return true;
	     }   
	     document.getElementById('paraFrm_hidFlag').value="save";
         document.getElementById('paraFrm').action = "TravelPolicy_firstNext.action";
		 document.getElementById('paraFrm').submit();
		 document.getElementById('paraFrm').target ="main"; 
    }
    
      function editFun()
     {    
      		document.getElementById('paraFrm_editFlag').value="false";
         document.getElementById('paraFrm').action = "TravelPolicy_edit.action";
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
  	 	  document.getElementById('paraFrm_editFlag').value="false";
  		 document.getElementById('paraFrm').action = "TravelPolicy_delete.action";
		 document.getElementById('paraFrm').submit();
		 document.getElementById('paraFrm').target ="main";
  		 }else
  		 {
  		   return false;
  		 } 
    }
 
    
    
   function saveValidate()
    {
  
      policyName = document.getElementById('paraFrm_policyName').value; 
	    if(policyName=="")
	    {
	     alert("Please enter Policy Name.");
	     document.getElementById('paraFrm_policyName').focus();
	     return false;
	    } 
	     policyAbbr =document.getElementById('paraFrm_policyAbbr').value;
	    if(policyAbbr=="")
	    {
	     alert("Please enter Policy Abbreviation .");
        document.getElementById('paraFrm_policyAbbr').focus();
	     return false;
	    } 
	   var policyAbbrValue = LTrim(RTrim(policyAbbr));
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
	  } 
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
	    return true;
	    
    }
    
 
     
</script>
 