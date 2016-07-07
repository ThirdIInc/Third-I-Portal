 <script>	
  var gradeArr = new Array();                                    
 </script> 	  
		                            
		                            
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="TravelPolicy" validate="true" id="paraFrm" 	theme="simple"> 	 
	<table width="100%" border="0" align="right" cellpadding="0"  		cellspacing="0">
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
			<td height="5" colspan="3"><img
				src="../pages/images/recruitment/space.gif" width="5" height="7" /></td>
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
			     <td  ><img src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
			     <td>  
			        <s:hidden name="gradeLength"/> <s:hidden name="readOnlyFlag"/> 
			     <!-- hidden only First Page --> 
			    <s:hidden name="policyName" />    <s:hidden name="policyId" />   <s:hidden name="policyAbbr" />    <s:hidden name="effectDate" /> 
	            <s:hidden name="desc" />    <s:hidden name="status" />    <s:hidden  name="searchFlag" />
	               <s:hidden name="editFlag"/>           <s:hidden name="settleDays"/>     <s:hidden name="guidLines"/>   
	            <!-- hidden only -->   
	            
	            <!-- hidden only Third Page --> 
			  			 		<s:hidden name="totExpAmt" />   <s:hidden name="expTravelFlag" /> 
                                <s:hidden name="expCateTravel"/>  <s:hidden name="expCateTravelId"/> 
                                <s:hidden name="expCateHotel" /> <s:hidden name="expCateHotelId"/>
								 <s:iterator value="expList">	 
							 			 <s:hidden name ="exCategory" /> <s:hidden name ="exCategoryId" />   <s:hidden name ="expCatUnit" />    
										 <s:hidden name ="expCatUnitCode" />  <s:hidden  name="expValue"  /> 
										   <s:hidden name ="actualAtt" />  <s:hidden  name="hidActualAtt"  /> <s:hidden name="readOnlyFlag"/>  
								   </s:iterator>	
			  				     
								 <s:iterator value="travelModeList">						 
					 			 	 <s:hidden name ="travelMode" /> <s:hidden name ="travelModeId" /> 
					 			 	  <s:hidden name ="travelClass" /> <s:hidden name ="travelClassId" />   
							 		 <s:hidden name ="hidtrModeStatus"   /> <s:hidden name ="trModeStatus" /> 
	 						     </s:iterator>	
	 						     
	 						      <s:iterator value="hotelTypeList">	 
							 			 <s:hidden name ="hotelType" /> <s:hidden name ="hotelTypeId" />  
							 			 <s:hidden name ="roomType" /> <s:hidden name ="roomTypeId" />    
							 			  <s:hidden name ="hidHotelStatus" /> <s:hidden name ="hotelStatus" />    
							      </s:iterator>						
							 				
	            <!-- hidden only -->   
			        
			     </td>
	    </tr> 
		 
		 <tr>
			 <td colspan="2" width="100%">
			 	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
					    <tr>
								<td width="70%" colspan="1" class="formth"><label  class = "set"  id="emp.grade" name="emp.grade"  ondblclick="callShowDiv(this);"><%=label.get("emp.grade")%></label></td>
								<td width="30%" colspan="1" class="formth"><label  class = "set"  id="appGrade"  name="appGrade" ondblclick="callShowDiv(this);"><%=label.get("appGrade")%></label>  </td>
				    	</tr>
				    	     <%int b=1,b1=0; %> 
								<s:iterator value="gradeList">
									<tr>
										<s:hidden name="empGrade" />  
										<s:hidden name="empGradeId" />
										<td width="70%" colspan="1" class="sortableTD"><s:property
											value="empGrade" /></td>
										<td width="30%" colspan="1" class="sortableTD">
										<input type="hidden" name="hidGradeStatus" value="<s:property value="hidGradeStatus"/>" id="hidGradeStatus<%=b%>">
										<s:if test="editFlag">
										<input  type="checkbox" name="gradeStatus" <s:property value="gradeStatus"/>  id="gradeStatus<%=b%>"  disabled="disabled" onchange="callGradeStatus('<%=b%>');">
											 </s:if>
											 <s:else> <input  type="checkbox" name="gradeStatus" <s:property value="gradeStatus"/>  id="gradeStatus<%=b%>"   onchange="callGradeStatus('<%=b%>');"> </s:else>
										</td>
									</tr>
									 <script>	
		                                  gradeArr[<%=b1%>] = document.getElementById('hidGradeStatus'+<%=b%>).value;   
		                            </script> 	  
									<%b++; %> <%b1++; %>
								</s:iterator>
					</table>				
				</td>
			</tr>

        <tr>
			     <td  ><img src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
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
</s:form>
<script type="text/javascript">

  function nextFun()
     {  
     
     
       var count="0";
        for(var i =1 ;i<=gradeArr.length;i++)
         {  
           if(document.getElementById('hidGradeStatus'+i).value=='Y')
           {
             count++;
           }
         }
	      if(count==0)
	      {
	       alert("Please select at least one employee grade.");
	       return false;
	      }
	      
         document.getElementById('paraFrm').action = "TravelPolicy_secondNext.action";
		 document.getElementById('paraFrm').submit();
		 document.getElementById('paraFrm').target ="main"; 
    }
    
    function previousFun()
     {  
         document.getElementById('paraFrm').action = "TravelPolicy_previousSecond.action";
		 document.getElementById('paraFrm').submit();
		 document.getElementById('paraFrm').target ="main"; 
    }
    
 function cancelFun()
     {  
         document.getElementById('paraFrm').action = "TravelPolicy_cancel.action";
		 document.getElementById('paraFrm').submit();
		 document.getElementById('paraFrm').target ="main"; 
    }
    
        function editFun()
     {  
      	 document.getElementById('paraFrm_editFlag').value="false";
         document.getElementById('paraFrm').action = "TravelPolicy_firstNext.action";
		 document.getElementById('paraFrm').submit();
		 document.getElementById('paraFrm').target ="main"; 
    }

 function callGradeStatus(id)
    {  
     if(document.getElementById('gradeStatus'+id).checked)
      { 
        document.getElementById('hidGradeStatus'+id).value="Y";
         
      }else
       { 
         document.getElementById('hidGradeStatus'+id).value="N";
       }
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
</script>
	   