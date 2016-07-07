  <script>
 var travelArr = new Array();
 var hotelArr = new Array();
 var expArr = new Array();
 var roomArr = new Array();
 
 </script>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="TravelPolicy" validate="true" id="paraFrm" 	theme="simple"> 	 
	<table width="100%" border="0" align="right" cellpadding="0"  		cellspacing="0">
		<tr>
			<td colspan="3" valign="bottom" class="txt">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="3" valign="bottom"
				background="../pages/images/recruitment/lines.gif" class="txt"><img
				src="../pages/images/recruitment/lines.gif" width="16" height="1" /></td>
		</tr>
		<tr>
			<td colspan="3" valign="bottom" class="txt"><img
				src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
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
			     <td>  <s:hidden name="loadFlag"/> <s:hidden name="expLength"/>
				     <s:hidden name="addNewFlag"/>  
				     <s:hidden name="editFlag"/>  
				     <s:hidden name="saveFlag"/> 
				    <s:hidden name="hidFlag"/>   <s:hidden name="expTravelFlag" />   
				    
				  <!-- hidden only First Page --> 
			    <s:hidden name="policyName" />    <s:hidden name="policyId" />   <s:hidden name="policyAbbr" />    <s:hidden name="effectDate" /> 
	            <s:hidden name="desc" />    <s:hidden name="status" />   <s:hidden  name="searchFlag" /> 
	            <!-- hidden only -->  
	            
	            <!-- hidden only Second Page --> 
			  			 <%int b=1; %>
								<s:iterator value="gradeList"> 
										<s:hidden name="empGrade" />
										<s:hidden name="empGradeId" />  
										<s:hidden name="hidGradeStatus" />  
										<s:hidden name="gradeStatus" />    
								 	<%b++; %>
								</s:iterator> 
	            <!-- hidden only -->  
			        
			        
				    
				    </td>
	    </tr> 
	    
	    <tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						
						<tr >
							<td width="25%" colspan="1"><strong><label  class = "set"  id="exp.entitle" ondblclick="callShowDiv(this);"><%=label.get("exp.entitle")%></label></strong> </td>
						 
						</tr>  
						 <tr >
							<td width="100%" colspan="2">   
							  <table   class="formbg" width="100%"> 
							    <tr>   
							          <td width="10%" colspan="1" class="formth" ><label  class = "set"  id="srNo" name="srNo"  ondblclick="callShowDiv(this);"><%=label.get("srNo")%></label></td>
						 	          <td width="60%" colspan="1" class="formth" ><label  class = "set"  id="exp.cate" name="exp.cate"  ondblclick="callShowDiv(this);"><%=label.get("exp.cate")%></label>  </td>
						 	          <td width="20%" colspan="1" class="formth" ><label  class = "set"  id="unit" name="unit"  ondblclick="callShowDiv(this);"><%=label.get("unit")%></label>  </td>
						 	          <td width="10%" colspan="1" class="formth" ><label  class = "set"  id="amt" name="amt"  ondblclick="callShowDiv(this);"><%=label.get("amt")%></label> </td> 
						 	             <td width="10%" colspan="1" class="formth" ><label  class = "set"  id="atActual" name="atActual"  ondblclick="callShowDiv(this);"><%=label.get("atActual")%></label> </td> 
								</tr> 
								<% int p=1 ,p1=0; %> 
								 <s:iterator value="expList">						 
					 			   <tr>
							 			 <s:hidden name ="exCategory" /> <s:hidden name ="exCategoryId" />   <s:hidden name ="expCatUnit" /> 
							 			    <s:hidden name ="expCatUnitCode" />  <s:hidden name="readOnlyFlag"/> 
										  <td width="5%" colspan="1" class="sortableTD" ><%=p%></td>
							 	          <td width="60%" colspan="1" class="sortableTD" ><s:property value="exCategory"/>  </td>
							 	          <td width="10%" colspan="1" class="sortableTD" ><s:property value="expCatUnit"/>  </td>
							 	          <td width="10%" colspan="1" class="sortableTD" >   <s:if test="editFlag"><s:property value="expValue" />  <s:hidden name="expValue" /> </s:if> <s:else> <input type="text"  style="text-align:right " name="expValue" id="expValue<%=p%>"  value="<s:property value="expValue" />"  size="6"  onkeyup="expAddition();" onkeypress="return numbersOnly();" maxlength="8"  <s:property value="readOnlyFlag"/> > </s:else> </td> 
								           <td width="15%" colspan="1" class="sortableTD" > 
								           <s:if test="editFlag">
								          	 <input 	type="checkbox" name="actualAtt" id="actualAtt<%=p%>"   <s:property value="actualAtt"/> disabled="disabled"  onclick="callExpChk('<%=p%>')"   > 
										   </s:if>
											 <s:else>
											   <input 	type="checkbox" name="actualAtt" id="actualAtt<%=p%>"   <s:property value="actualAtt"/>  onclick="callExpChk('<%=p%>')"   > 
											 </s:else>
												<input 	type="hidden" name="hidActualAtt" value='<s:property value="hidActualAtt"/>' id="hidActualAtt<%=p%>"> 
								          </td>
								    </tr>  
								    <script>	
		                                  expArr[<%=p1%>] = document.getElementById('hidActualAtt'+<%=p%>).value;   
		                            </script> 	  
								   <%p++; %>  <%p1++; %>
								 
							      </s:iterator>	
							       
							       
							     <tr>   
							          <td width="5%" colspan="1" class="sortableTD" >&nbsp;</td>
						 	          <td width="60%" colspan="1" class="sortableTD" >&nbsp; </td>
						 	          <td width="10%" colspan="1" class="sortableTD" ><strong><label  class = "set"  id="totAmt" name="totAmt" ondblclick="callShowDiv(this);"><%=label.get("totAmt")%></label></strong>  </td>
						 	          <td width="10%" colspan="1" class="sortableTD" > <s:if test="editFlag"> <s:property value="totExpAmt"/> </s:if><s:else> <s:textfield name="totExpAmt"  readonly="true" cssStyle="text-align:right" maxlength="8"  size="7"/> </s:else>  </td> 
								       <td width="15%" colspan="1" class="sortableTD" >&nbsp;</td>
								 </tr> 										 
								  </table>   
							</td>
						</tr> 
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			     <td  ><img src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
	    </tr>  
	    <tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="2">
						
						<tr >
							<td width="35%" colspan="1"><strong> <label  class = "set"  id="trModeEnt" name="trModeEnt"  ondblclick="callShowDiv(this);"><%=label.get("trModeEnt")%></label> </strong> </td> 
							<td   colspan="1">&nbsp; </td> 
						 
						</tr>  
						
						<tr >
							<td  colspan="2"><label  class = "set"  id="mapTrMode" name="mapTrMode" ondblclick="callShowDiv(this);"><%=label.get("mapTrMode")%></label><font color="red">*</font> :
							<s:hidden name="expCateTravelId"/>  <s:hidden name="expCateTravelUnit"/> 
							<s:if test="editFlag"> <s:property value="expCateTravel"/> </s:if> <s:else> <s:textfield name="expCateTravel" readonly="true"/> 
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18"
												onclick="javascript:callsF9(500,325,'TravelPolicy_f9ExpenseCatTravel.action');">
												</s:else>   </td>
							 
						 
						</tr>  
						 <tr >
							<td width="100%" colspan="2">   
							  <table   class="formbg" width="100%"> 
							    <tr>   
							          <td width="10%" colspan="1" class="formth" ><label  class = "set" name="srNo" id="srNo" ondblclick="callShowDiv(this);"><%=label.get("srNo")%></label></td>
						 	          <td width="40%" colspan="1" class="formth" ><label  class = "set" name="trMode" id="trMode" ondblclick="callShowDiv(this);"><%=label.get("trMode")%></label>   </td>
						 	          <td width="40%" colspan="1" class="formth" ><label  class = "set" name="trClass" id="trClass" ondblclick="callShowDiv(this);"><%=label.get("trClass")%></label>  </td>
						 	          <td width="10%" colspan="1" class="formth" ><label  class = "set"  name="status" id="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label>  </td> 
								</tr> 
								<% int q=1; %>   	<% int q1=0; %>  
								 <s:iterator value="travelModeList">						 
					 			   <tr>
							 			 <s:hidden name ="travelMode" /> <s:hidden name ="travelModeId" /> 
							 			  <s:hidden name ="travelClass" /> <s:hidden name ="travelClassId" /> <s:hidden name ="trSrNo" />  
										  <td width="10%" colspan="1" class="sortableTD" > <s:property value="trSrNo"/> &nbsp; </td>
							 	          <td width="40%" colspan="1" class="sortableTD" ><s:property value="travelMode"/>&nbsp;  </td>
							 	          <td width="40%" colspan="1" class="sortableTD" ><s:property value="travelClass"/>&nbsp; </td>
							 	          <td width="10%" colspan="1" class="sortableTD" >   
							 	              <s:if test="editFlag">
							 	        		<input 	type="checkbox" name="trModeStatus" id="trModeStatus<%=q%>"   <s:property value="trModeStatus"/> disabled="disabled" onclick="callTravelMode('<%=q%>')"   > 
							 	        		</s:if>
							 	        		<s:else>
							 	        		<input 	type="checkbox" name="trModeStatus" id="trModeStatus<%=q%>"   <s:property value="trModeStatus"/>  onclick="callTravelMode('<%=q%>')"   >
							 	        		</s:else>
							 	        
												<input 	type="hidden" name="hidtrModeStatus" value='<s:property value="hidtrModeStatus"/>' id="hidtrModeStatus<%=q%>"> 
							 	            </td> 
								    </tr>  
								    <script>	
		                                  travelArr[<%=q1%>] = document.getElementById('hidtrModeStatus'+<%=q%>).value;   
		                            </script> 	  
								 <%q++; %>  <%q1++; %>
								  
								 	
							      </s:iterator>								 
								  </table>   
							</td>
						</tr> 
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			     <td  ><img src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
	    </tr>
	    		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0" 	cellspacing="2"> 
						<tr >
							<td width="35%" colspan="1"><strong><label  class = "set"  id="lodgEnt"  name="lodgEnt" ondblclick="callShowDiv(this);"><%=label.get("lodgEnt")%></label> </strong> </td>
							 <td  colspan="1"> &nbsp;	 </td> 
						</tr>  
						
						<tr >
							<td colspan="2"><label  class = "set"  id="mapLodg" name="mapLodg" ondblclick="callShowDiv(this);"><%=label.get("mapLodg")%></label><font color="red">*</font> :
							   <s:hidden name="expCateHotelId"/>   <s:hidden name="expCateHotelUnit"/>
							<s:if test="editFlag"> <s:property value="expCateHotel"/> </s:if> <s:else> 
							 <s:textfield name="expCateHotel" readonly="true"/> 
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18"
							onclick="javascript:callsF9(500,325,'TravelPolicy_f9ExpHotel.action');">  </s:else> </td>
					 	</tr>  
						 <tr >
							<td width="100%" colspan="2">   
							  <table   class="formbg" width="100%"> 
							  <td colspan="1" valign="top">
							   <table class="formbg" width="100%"> 
								    <tr>   
								          <td width="10%" colspan="1" class="formth" ><label  class = "set"  id="srNo" name="srNo" ondblclick="callShowDiv(this);"><%=label.get("srNo")%></label></td>
							 	          <td width="40%" colspan="1" class="formth" ><label  class = "set"  id="hotType" name="hotType" ondblclick="callShowDiv(this);"><%=label.get("hotType")%></label>   </td>
							 	           <td width="10%" colspan="1" class="formth" ><label  class = "set"  id="lodgStatus" name="lodgStatus"  ondblclick="callShowDiv(this);"><%=label.get("lodgStatus")%></label> </td> 
									</tr> 
									<% int r=1; %> <% int r1=0; %>
									 <s:iterator value="hotelTypeList">						 
						 			   <tr>
								 			 <s:hidden name ="hotelType" /> <s:hidden name ="hotelTypeId" />   
											  <td width="10%" colspan="1" class="sortableTD" ><%=r%></td>
								 	          <td width="40%" colspan="1" class="sortableTD" ><s:property value="hotelType"/>&nbsp;  </td>
								 	          <td width="10%" colspan="1" class="sortableTD" >
								 	           <input type="hidden" name="hidHotelStatus"   id="hidHotelStatus<%=r%>" value="<s:property value="hidHotelStatus"/>"  >
											 <s:if test="editFlag">
											   <input type="checkbox" name="hotelStatus" <s:property value="hotelStatus"/> disabled="disabled"  id="hotelStatus<%=r%>" onclick="callHotelTypeStatus('<%=r%>');" >
											   </s:if>
											   <s:else>
											    <input type="checkbox" name="hotelStatus" <s:property value="hotelStatus"/>   id="hotelStatus<%=r%>" onclick="callHotelTypeStatus('<%=r%>');" >
											   </s:else>
									v		   </td> 								    </tr>  	
									     <script>	
			                                  hotelArr[<%=r1%>] = document.getElementById('hidHotelStatus'+<%=r%>).value;   
			                            </script> 	 	
									 <%r++; %>  <%r1++; %>
								      </s:iterator>	 
							   </table>
							  </td> 
							  <td colspan="1" valign="top">
							   <table class="formbg" width="100%"> 
								    <tr>   
								          <td width="10%" colspan="1" class="formth" ><label  class = "set"  id="srNo" name="srNo" ondblclick="callShowDiv(this);"><%=label.get("srNo")%></label></td>
							 	          <td width="40%" colspan="1" class="formth" ><label  class = "set"  id="roomType" name="roomType" ondblclick="callShowDiv(this);"><%=label.get("roomType")%></label>  </td>
							 	          <td width="10%" colspan="1" class="formth" ><label  class = "set"  id="lodgStatus" name="lodgStatus"  ondblclick="callShowDiv(this);"><%=label.get("lodgStatus")%></label> </td> 
									</tr> 
									<% int s=1; %> <% int s1=0; %>
									 <s:iterator value="roomList">						 
						 			   <tr> 
								 			 <s:hidden name ="roomType" /> <s:hidden name ="roomTypeId" />    
											  <td width="10%" colspan="1" class="sortableTD" ><%=s%></td> 
								 	          <td width="40%" colspan="1" class="sortableTD" ><s:property value="roomType"/>&nbsp; </td>
								 	          <td width="10%" colspan="1" class="sortableTD" >
								 	           <input type="hidden" name="hidRoomStatus"   id="hidRoomStatus<%=s%>" value="<s:property value="hidRoomStatus"/>"  >
											 <s:if test="editFlag">
											   <input type="checkbox" name="roomStatus" <s:property value="roomStatus"/> disabled="disabled"  id="roomStatus<%=s%>" onclick="callRoomStatus('<%=s%>');" >
											   </s:if>
											   <s:else>
											    <input type="checkbox" name="roomStatus" <s:property value="roomStatus"/>   id="roomStatus<%=s%>" onclick="callRoomStatus('<%=s%>');" >
											   </s:else>
											   </td> 								    </tr>  	
									     <script>	
			                                  roomArr[<%=s1%>] = document.getElementById('hidRoomStatus'+<%=s%>).value;   
			                            </script> 	 	
									 <%s++; %>  <%s1++; %>
								      </s:iterator>	
								      
			  
							   </table>
							  </td>  
						</tr> 
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>  
		<tr>
			     <td  ><img src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
	    </tr>
	    <tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<tr >
							<td width="100%" colspan="2"><label  class = "set"  id="settleDay" name="settleDay"  ondblclick="callShowDiv(this);"><%=label.get("settleDay")%></label><font color="red">*</font> :&nbsp;
								<s:if test="editFlag"> <s:property value="settleDays"/> </s:if> <s:else> 
							<s:textfield size="10" name="settleDays" onkeypress="return numbersOnly();"  maxlength="6" /> </s:else>  </td>
							 
						</tr>	
						
						<tr >
							<td width="100%" colspan="2"><Strong><label  class = "set"  id="polGuid" name="polGuid"  ondblclick="callShowDiv(this);"><%=label.get("polGuid")%></label></Strong></td>
							 
						</tr>	
						 <tr >
							<td width="97%" colspan="1"> <s:hidden name="textAreaLength" id="textAreaLength" value="0"/> 
							<s:if test="editFlag"> <s:property value="guidLines"/> </s:if> <s:else> 
							  <s:textarea name="guidLines"  id="guidLines"  name="guidLines"   cols="118" rows="5"  onkeypress="return textCounter(this,1950)"  />
						     </s:else>
                             </td> 
                            <td valign="top">
                             <s:if test="editFlag"> </s:if> <s:else>  
						 	<img src="../pages/images/zoomin.gif" class="iconImage" height="18" align="absmiddle" width="18"
							 onclick="callWindow('guidLines','polGuid');"> 
							</s:else>
                            </td> 
						</tr>						
					</table>
					</td>
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

					<td width="22%"> &nbsp;
					</td>
				</tr>
			</table>
			<label></label></td>
		</tr>
	</table> 
	  </td>
    </tr>
	</table>
</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script type="text/javascript"> 
    function previousFun()
     {   
         document.getElementById('paraFrm').action = "TravelPolicy_previousThird.action";
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
         document.getElementById('paraFrm').action = "TravelPolicy_secondNext.action";
		 document.getElementById('paraFrm').submit();
		 document.getElementById('paraFrm').target ="main"; 
    }
    
    
    function saveFun()
     {   
     
      totExpAmt=document.getElementById('paraFrm_totExpAmt').value;
	      
	      if(totExpAmt=="0")
	      {
	       alert("Please enter cost for at least one expense category unit.");
	       return false;
	      }
	     if( document.getElementById('paraFrm_expCateTravel').value=="")
	     {
		     alert("Please select Map travel mode with expense category."); 
		     return false;
	     }
	     
	      if(travelArr.length>0)
	      {
		       var j =1 ;
		       var chkStatus ="notSelected"
		       for(i=0;i<travelArr.length ;i++)
		       {
			       	hidtrModeStatus = document.getElementById('hidtrModeStatus'+j).value;
			       	if(hidtrModeStatus=='Y')
			       	{ 
			         var chkStatus ="selected"
			       	} 
			       	j++;
		       }
		       if(chkStatus=="notSelected")
		       {
		       alert("Please select at least one travel mode.");
		       return false;
		       }
	      } 
		   if( document.getElementById('paraFrm_expCateHotel').value=="")
		    {
		     alert("Please select Map lodging with expense category."); 
		     return false;
		    }
		    
	  if(hotelArr.length>0)
	      {
		       var j =1 ;
		       var chkStatus ="notSelected"
		       for(i=0;i<hotelArr.length ;i++)
		       {
			       	hidtrModeStatus = document.getElementById('hidHotelStatus'+j).value;
			       	if(hidtrModeStatus=='Y')
			       	{ 
			         var chkStatus ="selected"
			       	} 
			       	j++;
		       }
		       if(chkStatus=="notSelected")
		       {
		       alert("Please select at least one Hotel Type.");
		       return false;
		       }
	      } 
		    
		  
		    if(document.getElementById('paraFrm_settleDays').value=="")
		    {
		    document.getElementById('paraFrm_settleDays').focus();
		     alert("Please enter travel expenses need to be settle within days."); 
		     return false;
		    }
		 document.getElementById('paraFrm_editFlag').value="true";
         document.getElementById('paraFrm').action = "TravelPolicy_save.action";
		 document.getElementById('paraFrm').submit();
		 document.getElementById('paraFrm').target ="main"; 
    }
  
function textCounter(field,  maxlimit) { 
		if (field.value.length > maxlimit){
			
			//alert('Field value should not exceed '+maxlimit+' chars.');	 
			field.value = field.value.substring(0, maxlimit);
			field.focus;
			return false; 
		} 
		return true;
		
	}
	
	 function callTextArea()
	 {
	  var length = document.getElementById('textAreaLength').value;
	  document.getElementById('guidLines').style.height=eval(length)+eval(200);
	  document.getElementById('textAreaLength').value=eval(length)+eval(200); 
	 }  
	 
	 function callTextAreaMinus()
	 {
	 
	 var size =document.getElementById('guidLines').style.height;
	 var abc = size.split("px"); 
	  var length = document.getElementById('textAreaLength').value; 
	  if(abc[0]>201)
	  {
		  document.getElementById('guidLines').style.height=eval(length)-eval(200);
		  document.getElementById('textAreaLength').value=eval(length)-eval(200); 
	  }
	  
	    if(abc[0]>101 && abc[0]<=200)
	  {
		  document.getElementById('guidLines').style.height=eval(length)-eval(100);
		  document.getElementById('textAreaLength').value=eval(length)-eval(100); 
	  }
	  
	 }   
   
   
      function callExpChk(id)
       {   
	      if(document.getElementById('actualAtt'+id).checked)
	      {  
	     
	      document.getElementById('expValue'+id).readOnly=true;  
	      document.getElementById('expValue'+id).value='0';
	      document.getElementById('hidActualAtt'+id).value="Y";   
	      expAddition();
	      }else
	      {   
	        document.getElementById('expValue'+id).readOnly=false;  
	        document.getElementById('hidActualAtt'+id).value="N"; 
	        expAddition();
	      }
     }
    
    
     
     function callTravelMode(id)
    {  
     var from =eval(id)+eval(1);
     var length =eval(travelArr.length)+eval(1); 
      if(document.getElementById('trModeStatus'+id).checked)
      {    
        document.getElementById('hidtrModeStatus'+id).value="Y";  
        for(i=from;i<length;i++)
	        {
		         document.getElementById('hidtrModeStatus'+i).value="Y";
		         document.getElementById('trModeStatus'+i).checked=true; 
	        }  
      }else
      {   
        document.getElementById('hidtrModeStatus'+id).value="N";
          for(i=1;i<from;i++)
	        {
		         document.getElementById('hidtrModeStatus'+i).value="N";
		         document.getElementById('trModeStatus'+i).checked=false; 
	        } 
      }
    }
    
    
    function callHotelTypeStatus(id)
    { 
	     var from =eval(id)+eval(1);
	     var length =eval(hotelArr.length)+eval(1); 
	     
	     if(document.getElementById('hotelStatus'+id).checked)
	      {      document.getElementById('hidHotelStatus'+id).value="Y";
		          for(i=from;i<length;i++)
			        {
				         document.getElementById('hidHotelStatus'+i).value="Y";
				         document.getElementById('hotelStatus'+i).checked=true; 
			        }   
	      }else
	      {   document.getElementById('hidHotelStatus'+id).value="N";
	          for(i=1;i<from;i++)
		        {
			         document.getElementById('hidHotelStatus'+i).value="N";
			         document.getElementById('hotelStatus'+i).checked=false; 
		        }  
	       }
    }
    
    
       function callRoomStatus(id)
    { 
	     var from =eval(id)+eval(1);
	     var length =eval(roomArr.length)+eval(1); 
	     
	     if(document.getElementById('roomStatus'+id).checked)
	      {      document.getElementById('hidRoomStatus'+id).value="Y";
		          for(i=from;i<length;i++)
			        {
				         document.getElementById('hidRoomStatus'+i).value="Y";
				         document.getElementById('roomStatus'+i).checked=true; 
			        }   
	      }else
	      {   document.getElementById('hidRoomStatus'+id).value="N";
	          for(i=1;i<from;i++)
		        {
			         document.getElementById('hidRoomStatus'+i).value="N";
			         document.getElementById('roomStatus'+i).checked=false; 
		        }  
	       }
    }
   
    
   function expAddition(){ 
	      var tot=0;
	     for(var i=1; i<=expArr.length;i++)
	       {
	        var expValue =document.getElementById('expValue'+i).value;  
	          if(expValue=="") 
	          { 
	             expValue=0;
	          }
	          tot=Math.abs(tot)+Math.abs(expValue);
	       }  
	      document.getElementById('paraFrm_totExpAmt').value =tot;
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
 