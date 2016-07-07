<%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %> 

<s:form action="PartnerPostResume" validate="true" id="paraFrm" theme="simple"> 
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg">
	 <tr>
	       <td colspan="3" width="100%">
				 <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt"><strong class="formhead"><img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="text_head">Post Resume </strong></td>
						<td width="3%" valign="top" class="otxt"><div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td  width="70%">	<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp"/> </td>
			<td  width="30%"><div align="right"><font color="red">*</font>Indicates Required</div>
			<s:hidden name="cityId" /> <s:hidden name="countryId" />  <s:hidden name="dataPath" />
						             <s:hidden name="workLevelId" /> <s:hidden name="currentDesgId" />  <s:hidden name="funAreaId" />
			  <s:hidden name="qualificationId" />
			 </td>
		</tr>
		<tr>
			<td colspan="3" width="100%"> <s:hidden name="editFlag"/>  <s:hidden name="searchFlag"/>
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg"><!--Table 2-->
			 
					<tr>
						<td>
							<table width="100%" border="0" align="center" cellpadding="1" cellspacing="2"><!--Table 1-->	
								<tr> 
									<td width="22%"><label  class = "set" name="laDateOfBirth" id="laDateOfBirth" ondblclick="callShowDiv(this);"><%=label.get("laDateOfBirth")%></label> <font style="size: 6" >(dd-mm-yyyy)</font> <font color="red">*</font> :</td>
									<td width="20%"> <s:textfield name="dateOfBirth" size="25" onkeypress="return numbersWithHiphen();" /> </td>
									<td width="12%"> <img src="../pages/images/recruitment/Date.gif" class="iconImage" height="18" align="absmiddle" width="18" onclick="javascript:NewCal('paraFrm_dateOfBirth','DDMMYYYY');"> </td>
									<td width="16%"><label  class = "set" name="laGender" id="laGender" ondblclick="callShowDiv(this);"><%=label.get("laGender")%></label><font color="red">*</font> :</td>
									<td width="32%" colspan="2"> <s:select name="gender" headerKey="B" headerValue="Select" list="#{'M':'Male','F':'Female'}"></s:select> </td>
								 </tr> 
								 
								 <tr> 
								     <td width="22%"><label  class = "set" name="laMaritalStatus" id="laMaritalStatus" ondblclick="callShowDiv(this);"><%=label.get("laMaritalStatus")%></label> <font color="red">*</font> :</td>
									 <td width="78%" colspan="5"><s:select headerKey="B" name="maritalStatus" headerValue="Select" list="#{'M':'Married','U':'Unmarried'}"></s:select>  </td>
								 </tr> 
								 
								 <tr> 
									<td width="22%"><label  class = "set" name="laAddress" id="laAddress" ondblclick="callShowDiv(this);"><%=label.get("laAddress")%></label> <font color="red">*</font> :</td>
									<td width="20%"> <s:textarea name="address" cols="22" rows="1" /> </td>
									<td width="12%"> <img src="../pages/images/recruitment/Date.gif" class="iconImage" height="18" align="absmiddle" width="18" onclick="javascript:NewCal('paraFrm_dateOfBirth','DDMMYYYY');"> </td>
									<td width="16%"><label  class = "set" name="lacityName" id="lacityName" ondblclick="callShowDiv(this);"><%=label.get("lacityName")%></label><font color="red">*</font> :</td>
									<td width="20%"> <s:textfield name="cityName" size="25" readonly="true"/>   </td>
									<td width="12%"> <img src="../pages/images/search2.gif" 	class="iconImage" height="18" align="absmiddle" width="18" 	onclick="javascript:callsF9(500,325,'PartnerPostResume_f9City.action');">   </td>
								 </tr> 
								 
								  <tr> 
									<td width="22%"><label  class = "set" name="laCountry" id="laCountry" ondblclick="callShowDiv(this);"><%=label.get("laCountry")%></label> :</td>
									<td width="20%">  <s:textfield name="countryName" size="25" readonly="true" />   </td>
									<td width="12%"> <img src="../pages/images/search2.gif" 	class="iconImage" height="18" align="absmiddle" width="18" 	onclick="javascript:callsF9(500,325,'PartnerPostResume_f9Country.action');">   </td>
									<td width="16%"><label  class = "set" name="laPinCode" id="laPinCode" ondblclick="callShowDiv(this);"><%=label.get("laPinCode")%></label><font color="red">*</font> :</td>
									<td width="32%" colspan="2"> <s:textfield name="pinCode" size="25"  onkeypress="return numbersOnly();" maxlength="10"/>  </td>
							 	 </tr> 
							 	 
							 	  <tr> 
									<td width="22%"><label  class = "set" name="laPhoneNo" id="laPhoneNo" ondblclick="callShowDiv(this);"><%=label.get("laPhoneNo")%></label> :</td>
									<td width="20%">  <s:textfield name="phoneNo" size="25" onkeypress="return numbersWithHiphen();" maxLength="15" />   </td>
									<td width="12%"> &nbsp;  </td>
									<td width="16%"><label  class = "set" name="laMobileNo" id="laMobileNo" ondblclick="callShowDiv(this);"><%=label.get("laMobileNo")%></label><font color="red">*</font> :</td>
									<td width="32%" colspan="2"> <s:textfield name="mobileNo" size="25" onkeypress="return numbersWithHiphen();" maxlength="15"/>  </td>
							 	 </tr> 
							 	 
							 	  <tr> 
									<td width="22%"><label  class = "set" name="laExperience" id="laExperience" ondblclick="callShowDiv(this);"><%=label.get("laExperience")%></label> <font color="red">*</font> :</td>
									<td width="32%" colspan="2"> Year :<s:textfield name="expYear" size="4" onkeypress="return numbersOnly();" maxLength="4" /> &nbsp; Month :<s:textfield name="expMonth" size="4" onkeypress="return numbersOnly();" maxlength="4"/>  </td>
									 <td width="16%"><label  class = "set" name="laWorkLevel" id="laWorkLevel" ondblclick="callShowDiv(this);"><%=label.get("laWorkLevel")%></label><font color="red">*</font> :</td>
									<td width="20%"> <s:textfield name="workLevel" size="25" readonly="true"/>   </td>
									<td width="12%"> <img src="../pages/images/search2.gif" 	class="iconImage" height="18" align="absmiddle" width="18" 	onclick="javascript:callsF9(500,325,'PartnerPostResume_f9WorkLevel.action');">   </td>
								 </tr> 
								 
								  <tr> 
									<td width="22%"><label  class = "set" name="laCurrentEmployeer" id="laCurrentEmployeer" ondblclick="callShowDiv(this);"><%=label.get("laCurrentEmployeer")%></label> :</td>
									<td width="20%"> <s:textfield name="currentEmployeer" size="25" maxlength="40" />  </td>
									<td width="12%"> &nbsp; </td>
									<td width="16%"><label  class = "set" name="laCurrentDesignation" id="laCurrentDesignation" ondblclick="callShowDiv(this);"><%=label.get("laCurrentDesignation")%></label> :</td>
									<td width="20%"> <s:textfield name="currentDesignation" size="25"  readonly="true"/>   </td>
									<td width="12%"> <img src="../pages/images/search2.gif" 	class="iconImage" height="18" align="absmiddle" width="18" 	onclick="javascript:callsF9(500,325,'PartnerPostResume_f9Designation.action');">   </td>
								 </tr> 
								  
								  <tr> 
									<td width="22%"><label  class = "set" name="laCurrentFunctionalArea" id="laCurrentFunctionalArea" ondblclick="callShowDiv(this);"><%=label.get("laCurrentFunctionalArea")%></label>  :</td>
									<td width="20%"> <s:textfield name="currentFunctionalArea" size="25" readonly="true"/>  </td>
									<td width="12%"> <img src="../pages/images/search2.gif" 	class="iconImage" height="18" align="absmiddle" width="18" 	onclick="javascript:callsF9(500,325,'PartnerPostResume_f9FunctionalArea.action');">   </td> </td>
									<td width="16%"><label  class = "set" name="laQualifcation" id="laQualifcation" ondblclick="callShowDiv(this);"><%=label.get("laQualifcation")%></label><font color="red">*</font> :</td>
									<td width="20%"> <s:textfield name="qualifcation" size="25" readonly="true" />   </td>
									<td width="12%"> <img src="../pages/images/search2.gif" 	class="iconImage" height="18" align="absmiddle" width="18" 	onclick="javascript:callsF9(500,325,'PartnerPostResume_f9Qualification.action');">   </td>
								 </tr> 
								 
								  <tr> 
									<td width="22%"><label  class = "set" name="laYearOfPassing " id="laYearOfPassing" ondblclick="callShowDiv(this);"><%=label.get("laYearOfPassing")%></label> <font color="red">*</font> :</td>
									<td width="32%" colspan="2"> Year :<s:textfield name="passYear" size="4"  onkeypress="return numbersOnly();" maxlength="4"/> &nbsp; Month :<s:textfield name="passMonth" size="4" onkeypress="return numbersOnly();" maxlength="4" />  </td>
									<td width="46%" colspan="4">&nbsp;</td>
								</tr> 
								
									<tr> 
									<td width="22%"><label  class = "set" name="laUpload" id="laUpload" ondblclick="callShowDiv(this);"><%=label.get("laUpload")%></label> <font color="red">*</font> :</td>
									<td width="20%"> <s:textfield name="uploadFileName" size="25" readonly="true"/> </td>
									<td width="58%" colspan="4"> <input type="button" class="token" theme="simple" value="Upload Resume" 
						         		onclick="uploadFile('uploadFileName');"/> </td>
								</tr> 
								 
								 
							</table><!--Table 1-->	
						</td>
					</tr>		
				</table><!--Table 2-->	
			</td><!--end of vacancy search-->	
		</tr> 
		 
       <tr>
			  <td  width="100%">	<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp"/> </td> 
		</tr>
	</table>
</s:form>
 <script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script> 

	function uploadFile(fieldName) {
		var path = document.getElementById("paraFrm_dataPath").value; 
		window.open('<%=request.getContextPath()%>/pages/recruitment/uploadResume.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
	}

   function saveFun()
   { 
       var tbl = document.getElementById('advetiseTable');
	   var rowNum = tbl.rows.length;  
	   
	   var reqCode = document.getElementById('paraFrm_reqCode').value;
	   if(reqCode=="")
	   {
		  alert("Please select the "+document.getElementById('reqCodeLabel').innerHTML.toLowerCase());
		   document.getElementById('paraFrm_reqCode').focus();
		      return false;	    
	   } 
	   for(i=1 ; i<rowNum ; i++){
	
           var advertiseMode = document.getElementById('advertiseMode'+i).value;
           var advertiseModeName = document.getElementById('advertiseModeName'+i).value;
           var advertiseStartDate = document.getElementById('advertiseStartDate'+i).value;
           var advertiseEndDate = document.getElementById('advertiseEndDate'+i).value; 
           var advertiseCost = document.getElementById('advertiseCost'+i).value; 
        
		   if(advertiseMode=="0" || advertiseMode=="")
		   {
			    alert("Please select the "+document.getElementById('mode.Advertisement').innerHTML.toLowerCase());
			     document.getElementById('advertiseMode'+i).focus();
			    return false;	    
		   }
		        
	      if( advertiseModeName=="")
		   {
			    alert("Please enter the "+document.getElementById('name').innerHTML.toLowerCase());
			    document.getElementById('advertiseModeName'+i).focus();
			    return false 	    
		   } 
		   
	     if(!validateDate("advertiseStartDate"+i,'start.date')){
	         document.getElementById('advertiseStartDate'+i).focus();
				return false; 
			}   
			 
			if(!validateDate("advertiseEndDate"+i,'end.Date')){
	            document.getElementById('advertiseEndDate'+i).focus();
				return false; 
			}    
			if(!dateDifferenceEqual(advertiseStartDate, advertiseEndDate, "advertiseStartDate"+i, 'start.date','end.Date')){
	            document.getElementById('advertiseEndDate'+i).focus();
				return false; 
			}    
			
			  if(advertiseCost=="")
		     {
			    alert("Please enter the "+document.getElementById('cost').innerHTML.toLowerCase());
			    document.getElementById('advertiseCost'+i).focus();
			    return false 	    
		    } 
		} 
		if(!checkDuplicateAdvertise())
		 {
		   return false;
		 }
     document.getElementById('paraFrm').action="Advertisement_saveAdvertise.action"; 
     document.getElementById('paraFrm').submit(); 
   }
   
    
   
   function editFun()
   { 
     document.getElementById('paraFrm').action="Advertisement_edit.action";
     document.getElementById('paraFrm').submit(); 
   }
   
   function cancelFun()
   { 
     document.getElementById('paraFrm').action="Advertisement_cancel.action";
     document.getElementById('paraFrm').submit(); 
   }  
 
  function deleteFun()
   {
   	   var conf=confirm("Do you really want to delete the records ?");
		if(conf){ 
				     document.getElementById('paraFrm').action="Advertisement_delete.action";
				     document.getElementById('paraFrm').submit();
		 }  else
		 {
		    return false;
		 }
   }
  
</script>