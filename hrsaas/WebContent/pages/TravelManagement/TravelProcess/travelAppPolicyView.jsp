<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
	
	
	<%
	
		String policyDefined = "";
	
		try{
			policyDefined = (String) request.getAttribute("policyNotDefined");
		}finally{
			if(policyDefined==null){
				policyDefined="";
			}
		}
	%>
<s:form name="" action="" validate="" id="paraFrm" theme="simple">


<table width="100%" border="0"  cellpadding="1" cellspacing="1" class="formbg">
<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Travel
					Policy </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		
		<tr>
		 <td><input type="button"  value="   Close   " class="token" onclick="window.close();" /></td>
		</tr> 
<% if(policyDefined.equals("") || !policyDefined.equals("true") ){ %>
		
		<tr> 
		 <td>
		  <table class="formbg" width="100%" border="0">
		    <tr>
		     <td colspan="4"><b>Policy Details</b></td>
		    </tr>
		    
		    <tr>
		     <td width="40%">
		     	<label class="set" name="policyName" id="policyName" ondblclick="callShowDiv(this);"><%=label.get("policyName")%></label>:
		     </td>
		     <td width="25%"><s:property value="policy" /></td>
		     
		     
		     <td><label class="set" name="status" id="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label>:</td>
		     <td>
		     	<s:if test='%{policyStatus=="A"}'>
		     	  Active
		     	</s:if>
		     	<s:if test='%{policyStatus=="D"}'>
		     	  De-Active
		     	</s:if>
		     </td>	
		     
		    </tr>
		    
		    <tr> 
		     <td width="40%"><label class="set" name="effFrmDt" id="effFrmDt" ondblclick="callShowDiv(this);"><%=label.get("effFrmDt")%></label>:</td>
		     <td><s:property value="effctDate" /></td>
		     	 
		     <td>
				<label class="set"	name="advance.allow" id="advance.allow" ondblclick="callShowDiv(this);"><%=label.get("advance.allow")%></label> :
			 </td>
			 <td>
				<s:property value="policyAdvanceAllowed" />
			 </td>	     
		    </tr>
		    
		    
		    <tr>
		    	<td width="40%">
					<label class="set"	name="maxDaysToSettleTravelClaim" id="maxDaysToSettleTravelClaim" ondblclick="callShowDiv(this);"><%=label.get("maxDaysToSettleTravelClaim")%></label> :
				</td>
				<td>
					<s:property value="maxDaysTravelClaim" />
				</td>
			</tr>	
			
			
				<tr>
		    	<td width="40%">
					<label class="set"	name="minNumberOfAdvanceDaysToApplyForTravel" id="minNumberOfAdvanceDaysToApplyForTravel" ondblclick="callShowDiv(this);"><%=label.get("minNumberOfAdvanceDaysToApplyForTravel")%></label> :
				</td>
				<td>
					<s:property value="minNumberOfAdvanceDaysToApplyForTravel" />
				</td>
			</tr>			
			
				
			
			
			<tr>
				<td width="40%">
					<label class="set"	name="payModeForAdvanceClaim" id="payModeForAdvanceClaim" ondblclick="callShowDiv(this);"><%=label.get("payModeForAdvanceClaim")%></label> :
				</td>				
				<td colspan="3">
					<table>
						
						<tr width="20%">
							<td>								
								<s:property value="payModeForAdvanceClaimCheque" />													
							</td>
									
							<td>
								<s:property value="payModeForAdvanceClaimCash" />								
							</td>
									
							<td>
								<s:property value="payModeForAdvanceClaimTransfer" />											
							</td>
									
							<td>
								<s:property value="payModeForAdvanceClaimInSalary" />								
							</td>
						</tr>
										
					</table>						
				</td>
			</tr>		
			
			
			
			<tr>
				<td width="40%">
					<label class="set"	name="travelType" id="travelType" ondblclick="callShowDiv(this);"><%=label.get("travelType")%></label> :
				</td>				
				<td colspan="3">
					<table>						
						<tr width="20%">
							<td>							
								<s:property value="travelTypeSelf" />													
							</td>
									
							<td>
								<s:property value="travelTypeTeam" />								
							</td>
									
							<td>
								<s:property value="travelTypeGuest" />											
							</td>
							<td></td>							
						</tr>										
					</table>						
				</td>
			</tr>		       
		    
			<tr>
				<td width="40%">
					<label class="set"	name="travelCurrency" id="travelCurrency" ondblclick="callShowDiv(this);"><%=label.get("travelCurrency")%></label> :
				</td>				
				<td colspan="3">
						<s:property value="travelPolicyCurrency"/>			
				</td>
			</tr>		       
		    
		     </table>
		 	</td>
			</tr>
		   		     
		    <tr>
		     <td colspan="2">
		     	<table width="100%" class="formbg">
		     	 <tr>
		     	  <td><b>Expense Entitle List</b></td>
		     	 </tr>
		     	 <tr>
		     	  <td>
		     	   <table width="100%">
		     	    <tr>
		     	     <td class="formth"><b><label class="set" name="sno" id="sno" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b> </td>
		     	     <td class="formth"><b><label class="set" name="expCatName" id="expCatName" ondblclick="callShowDiv(this);"><%=label.get("expCatName")%></label></b></td>
		     	     <td class="formth"><b><label class="set" name="unit" id="unit" ondblclick="callShowDiv(this);"><%=label.get("unit")%></label></b></td>
		     	     <td class="formth"><b><label class="set" name="amountWithBill" id="amountWithBill" ondblclick="callShowDiv(this);"><%=label.get("amountWithBill")%></label></b></td>
		     	     <td class="formth"><b><label class="set" name="amountWithOutBill" id="amountWithOutBill" ondblclick="callShowDiv(this);"><%=label.get("amountWithOutBill")%></label></b></td>
		     	     <td class="formth"><b><label class="set" name="noLimit" id="noLimit" ondblclick="callShowDiv(this);"><%=label.get("noLimit")%></label></b></td>
		     	     <td class="formth"><b>Proof Required</b></td>
		     	    <td class="formth"><b><label class="set" name="cityGrade" id="cityGrade" ondblclick="callShowDiv(this);"><%=label.get("cityGrade")%></label></b></td>
		     	    </tr>  
		     	    <% int i=1; %>
		     	    <s:iterator value="expList">
		     	     <tr>
		     	      <td class="sortableTD"><%=i%></td>
		     	      <td class="sortableTD"><s:property  value="expCategory"/>&nbsp;</td>
		     	      <td class="sortableTD"><s:property  value="unit"/>&nbsp;</td> 
		     	      <td class="sortableTD" align="right"><s:property  value="amountWithBill"/>&nbsp;</td>
		     	      <td class="sortableTD" align="right"><s:property  value="amountWithOutBill"/>&nbsp;</td>
		     	      <td class="sortableTD">  
		     	      	<s:if test='%{noLimit=="Y"}'>
		     	      	 Yes
		     	      	</s:if>
		     	      	<s:if test='%{noLimit=="N"}'>
		     	      	 No
		     	      	</s:if>
		     	      </td>
		     	     
		     	      <td class="sortableTD">
		     	      <s:if test='%{proofReq=="Y"}'>
		     	      	 Yes
		     	      	</s:if>
		     	      	<s:if test='%{proofReq=="N"}'>
		     	      	 No
		     	      	</s:if> </td>
		     	      	<td class="sortableTD"><s:property  value="cityGrade"/></td>
		     	      	 
		     	     </tr>		 
		     	     <%i++;%>    	    
		     	    </s:iterator>
		     	    
		     	   </table>
		     	  </td>
		     	 </tr>
		     	</table>
		     </td>
		    </tr>
		    
		    <tr>
		     <td>
		      <table width="100%" class="formbg">
		       <!--<tr>
		        <td><b>Travel mode entitle (Maximum entitled Amount) </b></td>
		       </tr>
		       --><tr>
		        <td><b>Map Travel with Expense category</b> :<s:property value="expCategory" /></td>		        
		       </tr>
		       <tr>
		        <td>
		         <table width="100%">
		          <tr>
		           <td class="formth"><b><label class="set" name="sno" id="sno" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b></td>
		           <td class="formth"><b><label class="set" name="trvlMode" id="trvlMode" ondblclick="callShowDiv(this);"><%=label.get("trvlMode")%></label></b></td>
		           <td class="formth"><b><label class="set" name="trvlClass" id="trvlClass" ondblclick="callShowDiv(this);"><%=label.get("trvlClass")%></label></b></td><!--
		           <td class="formth"><b><label class="set" name="status" id="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label></b></td>
		          --></tr>
		          <%int j=1; %>
		          <s:iterator value="trvlModeList">
		           <tr>
		            <td class="sortableTD" width="5%"><%=j%></td>
		            <td class="sortableTD" width="60%"><s:property value="journeyName" /></td>
		            <td class="sortableTD" width="15%"><s:property value="className" /></td><!--		            
		            <td class="sortableTD" width="20%"><s:property value="status" />&nbsp;</td>
		           --></tr>
		           <%j++; %>		           		           
		          </s:iterator>
		          <%if(j==1){ %>
		          	<tr>
		          	 <td colspan="4"><font color="red">No Data To Display</font></td>
		          	</tr>
		          <%} %>
		         </table>
		        </td>
		       </tr>		       
		          
		         </table>
		        </td>
		       </tr>
		       <tr>
		        <td>
		            <table width="100%" class="formbg"><!--
				             <tr> 
				        <td><b>Lodging entitle (Maximum entitled amount Rs.4000 per day)</b></td>
				       </tr>
				       --><tr>
				        <td><b>Map Lodging with Expense category</b> :<s:property value="lodgExpCategory" /></td>
				       </tr>
				       <tr>
				        <td>
				         <table width="100%" class="">
				          <tr>
				           <td class="formth"><b><label class="set" name="sno" id="sno" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b></td>
				           <td class="formth"><b><label class="set" name="hotelTyp" id="hotelTyp" ondblclick="callShowDiv(this);"><%=label.get("hotelTyp")%></label></b></td>
				           <td class="formth"><b><label class="set" name="roomTyp" id="roomTyp" ondblclick="callShowDiv(this);"><%=label.get("roomTyp")%></label></b></td><!--
				           <td class="formth"><b><label class="set" name="status" id="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label></b></td>
				          --></tr>
				          <%int k=1; %>
				          <s:iterator value="hotelTypeList">
				           <tr>
				            <td class="sortableTD" width="5%"><%=k%></td>
				            <td class="sortableTD" width="60%"><s:property value="hotel" /></td>
				            <td class="sortableTD" width="15%"><s:property value="room" /></td><!--
				            <td class="sortableTD" width="20%"><s:property value="status" /></td>
				           --></tr>
				           <%k++; %>
				          </s:iterator>	
				          <%if(j==1){ %>
				          	<tr>
				          	 <td colspan="4"><font color="red">No Data To Display</font></td>
				          	</tr>
		          		  <%} %>
		            </table>
		        </td>
		       </tr>
		      </table>
		     </td>
		    </tr>
		    
		    <tr>
		        <td>
		         <table width="100%" class="formbg" border="0">
		          <s:hidden  name="sttlmntDays"/>
		          <!--<tr>
		          	<td colspan="2" width="100%" >
		          	 <table width="100%" border="0">
		          	  <tr>
				           <td colspan="1" width="40%">
				           	<b>
				           		<label class="set" name="settDays" id="settDays" ondblclick="callShowDiv(this);"><%=label.get("settDays")%></label>
				           	</b> :				           	 
				           	</td>
				           <td width="45%"><s:property  value="sttlmntDays"/></td>
			           </tr>
			          </table>
			        </td>
		          </tr>  -->
		          
		          <tr>
		           		<td colspan="2" width="100%" >
		           		 <table width="100%" border="0">
		           			<tr>
		           			  <td valign="top" width="10%">
		           				<b><label class="set" name="policyGuid" id="policyGuid" ondblclick="callShowDiv(this);"><%=label.get("policyGuid")%></label></b> :	
		           			  </td>
		           			  <td valign="top" width="40%">		
		           			  
		           			  <s:hidden name="policyGud" id="policyGud"/>
		           			  <a href="#" onclick="showRecord();"><s:property  value="policyGud" /></a>           			  	
		           				
		           			  </td>  			           		 
		           			  <!-- <td><a href="#" onclick="showRecord();">View Policy Guidelines</a></td> -->   	  
		           			</tr> 
		           		</table>
		           		</td>	 
		          </tr>
		          
		         </table>
		        </td>
		       </tr>
		       <tr>
    			<td colspan="4" align="left">    
     				<input type="button"  value="   Close   " class="token" onclick="window.close();" />
    			</td>
   			   </tr>
   <%}else{ %>   
   <tr>
    	<td align="center"><b>Policy not defined for the grade.</b></td>
   </tr>
   <%} %> 
   
</table>



</s:form>



<script>

function showRecord()
	{
		var fileName =document.getElementById('policyGud').value;
		 
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = "TravelApplication_viewAttachedProof.action?fileName="+fileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
	}



</script>


