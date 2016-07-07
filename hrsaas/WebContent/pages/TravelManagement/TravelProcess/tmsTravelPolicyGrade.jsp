 <script>	
  var gradeArr = new Array();                                    
 </script> 

		                            
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="../pages/TravelManagement/TravelProcess/tmsAjax.js"></script>
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="TravelPolicy" validate="true" id="paraFrm" 	theme="simple"> 

<input type="hidden" id="operation">
	 
	<table width="100%" border="0" align="right" cellpadding="0"  class="formbg" cellspacing="0">
		<tr>
			<td colspan="3" valign="bottom" class="txt">&nbsp;</td>
		</tr>
		<s:hidden  name="tp.policyId" />
		<s:hidden  name="activeflag" /> 
  <s:hidden  name="cancelFlag" /> 
	 <input type="hidden" name="existGrades" id="existGrades">
	 <input type="hidden" name="rowCount" id="rowCount">
	 <input type="hidden" name="actionName" id="actionName">
	   <input type="hidden" id="gradeList"/>
 		<tr>
		 <td colspan="3">
			<table width="100%" border="0" align="center" class="formbg">
				<tr>
					<td valign="bottom" class="txt" width="3%" colspan="1"><strong
						class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="97%" class="txt" colspan="2"><strong
						class="text_head">Travel Policy</strong></td>
				</tr>
			</table>
			</td>
		</tr>
		
		
		
		<tr>
			<td colspan="3">
			<div id="branchDiv"
				style='position: absolute; z-index: 3; width: 300px;display: none; border: 2px solid; top: 70px; left: 200px; padding: 10px'
				class="formbg">
			<table width="100%" border="0" cellpadding="10" cellspacing="1">
				<tr>
					<td width="35%" colspan="3" align="center"><label id="policyGrade"></label><br>
					Do you really want to overWrite?</br>
					</td>
				</tr>

				<tr>
					<td colspan="3" align="center"><input type="button"
						class="token" value="  Yes  " style="cursor: pointer;"
						onclick="overWriteGrades('Y')"> <input type="button"
						class="token" value="   No  " style="cursor: pointer;"
						onclick="overWriteGrades('N')"> <input type="button"
						class="cancel" value=" Cancel" style="cursor: pointer;"
						onclick="document.getElementById('branchDiv').style.display = 'none'; "></td>
				</tr>
			</table>
			</div>
			</td>
		</tr>
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="80%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					
				</tr>
			</table>
			<label></label></td>
		</tr>
		   <tr>
			   
			     <td>  
			        <s:hidden name="gradeLength"/> <s:hidden name="readOnlyFlag"/> 
			        <input type="hidden" id="overWrite"/>
			     <!-- hidden only First Page --> 
			    <s:hidden name="policyName" />    <s:hidden name="policyId" />   <s:hidden name="policyAbbr" />    <s:hidden name="effectDate" /> 
	            <s:hidden name="desc" />
	                <s:hidden name="status" />    <s:hidden  name="searchFlag" />
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
								<td width="70%" colspan="1" class="formth"><strong><label  class = "set"  id="emp.grade" name="emp.grade"  ondblclick="callShowDiv(this);"><%=label.get("emp.grade")%></label></strong></td>
								<td width="30%" colspan="1" class="formth"><strong><label  class = "set"  id="appGrade"  name="appGrade" ondblclick="callShowDiv(this);"><%=label.get("appGrade")%></label> </strong> </td>
				    	</tr>
				    	     <%!
				    	     int b=1,b1=0; %> 
								<s:iterator value="gradeList">
									<tr>
										<s:hidden name="empGrade" id='<%="empGrade"+b%>'/>  
										<s:hidden name="empGradeId" id='<%="empGradeId"+b%>'/>
										<td width="70%" colspan="1" class="sortableTD"><s:property
											value="empGrade" /></td>
										<td width="30%" colspan="1" class="sortableTD" align="center">
										<s:if test="tp.editFlag">
										
										<s:if test="gradeStatus">
										
											<input  type="checkbox" name="gradeStatus"  checked="true"  id="gradeStatus<%=b%>"  onclick="callGradeStatus('<%=b%>');" />
											<input type="hidden" name="hidGradeStatus"     id="hidGradeStatus<%=b%>" value="Y">
										</s:if>
										<s:else>
											<input  type="checkbox" name="gradeStatus"  id="gradeStatus<%=b%>"  onclick="callGradeStatus('<%=b%>');" />
											<input type="hidden" name="hidGradeStatus"     id="hidGradeStatus<%=b%>" value="N">
										</s:else>
										</s:if>
										<s:else>
										<s:if test="gradeStatus">
											<input  type="checkbox" name="gradeStatus"  checked="true"  id="gradeStatus<%=b%>" disabled="true" onclick="callGradeStatus('<%=b%>');" />
											<input type="hidden" name="hidGradeStatus"     id="hidGradeStatus<%=b%>" value="Y">
										</s:if>
										<s:else>
											<input  type="checkbox" name="gradeStatus"  id="gradeStatus<%=b%>" disabled="true" onclick="callGradeStatus('<%=b%>');" />
											<input type="hidden" name="hidGradeStatus"     id="hidGradeStatus<%=b%>" value="N">
										</s:else>
											 </s:else>
										</td>
									</tr>
<%b++; %>
								</s:iterator>
								<%
									b1=b;
									b=1;
								%>
					</table>				
				</td>
			</tr>

       
	    <tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

					<td width="20%" align="right">
						<strong><font color="black">Page 2 of 3</font></strong>
					</td>
				</tr>
			</table>
			<label></label></td>
		</tr>
	    </table>
</s:form>
<script type="text/javascript">


 function saveFun()
     {   
     
	     var policyId = document.getElementById('paraFrm_policyId').value;
	     var rowCount = <%=b1%>;
	     var gradeList="";
	      for(i=1;i<rowCount;i++)
	      {//alert(i);
    		 try{
				if(document.getElementById('hidGradeStatus'+i).value=='Y')
				{   						   													
					gradeList+=document.getElementById('empGradeId'+i).value+",";
				}
				}catch(e){
					// alert(e);
				}
    	 }
	     document.getElementById('rowCount').value = eval(eval(rowCount)-1);
	     document.getElementById('actionName').value = "TravelPolicy_save.action?saveType=gradeview&gradeList="+gradeList+"&";
	     try
		 {
    		TravelCheckDupPolicy('TravelPolicy_beforeSave.action?policyId='+policyId+'&abcd='+Math.random()+'&status=A&gradeList='+gradeList);
    	}catch(e)
    	{
    		// alert(e);
    	}
    	return false;
    }
	
	
  //for save and goto next(final) page
   function  saveandnextFun()
   {
   
   		 var policyId = document.getElementById('paraFrm_policyId').value;
	     var rowCount = <%=b1%>;
	     document.getElementById('rowCount').value = eval(eval(rowCount)-1);
	     var gradeList="";
	     for(i=1;i<rowCount;i++)
	     {//alert(i);
    		 try{
				if(document.getElementById('hidGradeStatus'+i).value=='Y')
				{   						   													
					gradeList+=document.getElementById('empGradeId'+i).value+",";
				}
				}catch(e){
					// alert(e);
				}
    	 }
	     document.getElementById('actionName').value = "TravelPolicy_secondSaveAndNext.action?gradeList="+gradeList+"&"; 
	    try
		{
    		TravelCheckDupPolicy('TravelPolicy_beforeSave.action?policyId='+policyId+'&abcd='+Math.random()+'&status=A&gradeList='+gradeList);
    	}catch(e)
    	{
    		alert(e);
    	}
    	return false;
   }



 //for save and previous button in grade page
  function  saveandpreviousFun()
  {
  	 var policyId = document.getElementById('paraFrm_policyId').value;
     var rowCount = <%=b1%>;
     document.getElementById('rowCount').value = eval(eval(rowCount)-1);
     var gradeList="";
     for(i=1;i<rowCount;i++)
     {
		try{
			if(document.getElementById('hidGradeStatus'+i).value=='Y')
			{   						   													
				gradeList+=document.getElementById('empGradeId'+i).value+",";
			}
		}catch(e){
			// alert(e);
		}
   	 }
	 document.getElementById('actionName').value = "TravelPolicy_saveandprevious.action?gradeList="+gradeList+"&";
	 try
	 {
    	TravelCheckDupPolicy('TravelPolicy_beforeSave.action?policyId='+policyId+'&abcd='+Math.random()+'&status=A&gradeList='+gradeList);
     }catch(e)
     {
    	alert(e);
      }
      return false;
  }
  function nextFun()
     {  
         document.getElementById('paraFrm').action = "TravelPolicy_secondNext.action";
		 document.getElementById('paraFrm').submit();
		 document.getElementById('paraFrm').target ="main"; 
    }
    
    function previousFun()
     {  
         document.getElementById('paraFrm').action = "TravelPolicy_previousGrade.action";
		 document.getElementById('paraFrm').submit();
		 document.getElementById('paraFrm').target ="main"; 
    }
    
    
 //for save and previous button in grade page
 
 function returntolistFun()
     {  
         document.getElementById('paraFrm').action = "TravelPolicy_cancel.action?saveType=grade";
		 document.getElementById('paraFrm').submit();
		 document.getElementById('paraFrm').target ="main"; 
    }
    
        function editFun()
     {  
      	 document.getElementById('paraFrm_editFlag').value="false";
         document.getElementById('paraFrm').action = "TravelPolicy_edit.action?saveType=gradeview";
		 document.getElementById('paraFrm').submit();
		 document.getElementById('paraFrm').target ="main"; 
    }


//for checkbox

 function callGradeStatus(id)

    {  // alert('id'+id);
    var check=document.getElementById('gradeStatus'+id).checked;
   // alert('----------checked-------'+check);
     if(document.getElementById('gradeStatus'+id).checked)
      { 
     
        document.getElementById('hidGradeStatus'+id).value='Y';
         
      }else
       { 
         document.getElementById('hidGradeStatus'+id).value='N';
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
    
    
function overWriteGrades(flag)
{
	if(flag == 'N')
	{
		try{
		var rowCount= document.getElementById('rowCount').value;
		for(i=0;i<rowCount;i++)
		{
			var exGrdArr = document.getElementById('existGrades').value.split(',');
	    	for(j=0;j<exGrdArr.length;j++)
			{
				if(exGrdArr[j]==document.getElementById('empGrade'+(eval(i)+1)).value 
					&& document.getElementById('gradeStatus'+(eval(i)+1)).checked)
				{
					document.getElementById('gradeStatus'+(eval(i)+1)).checked=false;
					document.getElementById('hidGradeStatus'+(eval(i)+1)).value='N';
					break;
				}
			} 
 		}
 		}catch(e)
 		{
 			// alert(e);
 		}
	}
	document.getElementById('paraFrm').target="main";
	document.getElementById('paraFrm').action = document.getElementById('actionName').value+'overWrite='+flag;
	document.getElementById('paraFrm').submit();      		 
}
   
function afterGetResponse()
{
	var val  = document.getElementById('existGrades').value;
	if(val.length == 1)
		document.getElementById('policyGrade').innerHTML = 'Grades '+val+' is already defined in other active policies.';
	else
		document.getElementById('policyGrade').innerHTML = 'Grades '+val+' are already defined in other active policies.';
    document.getElementById('branchDiv').style.display = "";
    return false;   
}
  
function checkDupPolicy()
{
	var policyId = document.getElementById('paraFrm_policyId').value;
	TravelCheckDupPolicy('TravelPolicy_beforeSave.action?policyId='+policyId+'&abcd='+Math.random());
}
</script>
	   