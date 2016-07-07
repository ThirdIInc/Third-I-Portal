    <%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript">
	var eadd = new Array();
	var schArr = new Array();
	var hotelArr = new Array();
</script>

<s:form action="TravelAppMngt" validate="true" id="paraFrm" theme="simple">
	<table width="99%">
		<tr>
			<td width="4%" valign="bottom" class="txt">
				<strong class="formhead">
					<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" />
				</strong>
			</td>
			<td width="90%" class="txt"><strong class="formhead"> Travel Cancellation</strong></td>
			<td width="4%" valign="top" class="txt">
				<div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
			</td>
		</tr>
	</table>
	
	<table width="99%" class="formbg">
		<tr>
			<td colspan="3">
				<table width="99%">
					<tr>
						<td width="99%" colspan="3">
							<table width="99%">
								<tr>
								<s:hidden name="linkStatus" value="E"/>
								 <s:hidden name="trApp.travelAppId"/>
								 <s:hidden name="checkEdit"/>
								 <s:hidden name="trApp.empId" /> 
								 <s:hidden name="trApp.fromPlaceId"/>
								 <s:hidden name="trApp.toPlaceId" /> 
								 <s:hidden name="trApp.journeyClassId"/> 
								 <s:hidden name="trApp.journeyId"/> 
								 <s:hidden name ="serialNo" />
								  <s:hidden name ="travelAppFlag" />
								  <s:hidden name ="statusSch" />
								    <s:hidden name ="schSaveFlag" />
								     <s:hidden name ="hidStatus" />
								      <s:hidden name ="scheduleFinalFlag" />
								       <s:hidden name ="travelReportId" />
								       <s:hidden name="confStatus"/>
								       <s:hidden name="cancelStatus"/>
								  
								 
									<s:if test="%{generalFlag}">
										<td width="20%" colspan="1">Employee  Name :</td>
										<td width="75%" colspan="3">
											<s:textfield name="trApp.empToken" size="20" readonly="true"/>
											<s:textfield size="55" name="trApp.empName"   readonly="true" />  
										</td>
									</s:if>
									<s:else>
										<td width="20%" colspan="1">  Employee Name :</td>
										<td width="75%" colspan="3">
											<s:textfield name="trApp.empToken" size="20" readonly="true"/>
											<s:textfield size="55" name="trApp.empName"  readonly="true" /> 
											<s:if test="travelAppFlag">
				  								<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18"
												onclick="javascript:callsF9(500,325,'TravelAppMngt_f9Employee.action');"> 
											 </s:if>
										</td>
									</s:else>
								</tr> 
		 						<tr>
									<td width="20%" colspan="1">Branch :</td>
									<td width="35%" colspan="1">
										<s:textfield size="30" name="trApp.branchName" readonly="true" />
									</td>
									<td width="20%" colspan="1">Department:</td>
									<td width="20%" colspan="1">
										<s:textfield size="30" maxlength="40" name="trApp.dept" readonly="true" />
									</td>
								</tr>
								<tr>
									<td width="20%" colspan="1">Designation:</td>
									<td width="35%" colspan="1">
										<s:textfield size="30" name="trApp.desg" readonly="true" />
									</td>
									<td width="20%" colspan="1">Application Date<font color="red" >*</font>:</td>
									<td width="20%" colspan="1">
										<s:textfield size="12" name="trApp.appDate" maxlength="10"
										onblur="return validateDate('paraFrm_trApp_appDate','Date');" onkeypress="return numbersWithHiphen();" />
										
									</td>
								</tr> 
								<tr>
									<td width="20%" colspan="1">Status :</td>
									<td width="35%" colspan="1">
									<s:select theme="simple" name="status" value="%{status}" list="#{'P':'Pending','A':'Approved','R':'Rejected','D':'Schedule','C':'Confirm','N':'Cancel In Process','K':'Cancel'}" disabled="true"></s:select>
									 </td> 
									 <td width="20%" colspan="1">Grade:</td>
									<td width="20%" colspan="1"><s:textfield size="25" name="gradeName" readonly="true"/> </td>
								</tr>
								
								<tr>
									<td width="20%" colspan="1">Age :</td>
									<td width="35%" colspan="1">
									 <s:textfield size="15" name="empAge" readonly="true"/>
									</td> 
									<td width="20%" colspan="1">Contact Number:</td>
									<td width="20%" colspan="1">										 
										<s:textfield size="25" name="contactNo" readonly="true"/>
									</td> 
								</tr>
								
								<tr>
									<td width="25%" colspan="1">ID Proof<font color="red" >*</font> :</td>
									<td width="35%" colspan="1">
									 <s:textfield name="trApp.idProof" size="35" readonly="true" />
									</td> 
									<td width="20%" colspan="1">ID Number </td>
									<td width="20%" colspan="1">										 
										<s:textfield size="25" name="trApp.idNumber" readonly="true"  />
									</td> 
								</tr>
							
								
								<tr>
									<td width="20%" colspan="1">Comments :</td>
									<td  colspan="3"> 
									 <s:textarea name ="applicantComment" cols="100" rows="2" readonly="true"/> 
									</td> 
								</tr> 
					        </table>
					     </td>
 		              </tr>
	                </table>
	                <table width="99%"  class="formbg" border="0"> 
							 
								  <tr>
									<td width="17%" colspan="1">Grade :</td>
									<td width="85%" colspan="3">
										<s:hidden  name="trApp.gradeCode"/>
										&nbsp;&nbsp;<s:textfield size="25" name="trApp.gradeName" readonly="true"/>
									</td>  
								</tr> 
								
								<tr>
									<td width="17%" colspan="1">Travel Entitlement </td>
									<td  colspan="3">   
									              <s:property value="appModeName"/> 
			 				 	 </td> 
								</tr>    
									<tr>
									<td width="17%" colspan="1"> &nbsp; </td>
									<td  colspan="1" width="40%" >  <s:property value="trOtherExp"/>   </td>  
			 				 	    <td  colspan="2"  >   <s:property value="lodgOtherAllow"/>  	 	 </td> 
								</tr>    
								<tr>
									<td width="17%" colspan="1"> &nbsp; </td>
									<td  colspan="1" width="40%" >   <s:property value="lodgAllowPerDay"/>      	 </td>  
			 				 	    <td  colspan="2"  >  <s:property value="outPocketAllow"/>  	 </td>  
								</tr>    
								<tr>
									<td width="17%" colspan="1"> &nbsp; </td>
									<td  colspan="3"  >  <s:property value="foodAllow"/>  	 </td> 
								</tr> 
		         </table>
				
	              </td>
				</tr>
				</table>
				 
					<table width="99%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
								
								<s:if test="travelAppFlag">
								<tr>
								     <td colspan="4"><strong class="formhead">Journey Details</strong></td>
								</tr>
								<tr>
									<td width="20%" colspan="1">From Place<font color="red" >*</font>:</td>
									<td width="35%" colspan="1"><s:textfield size="25" name="trApp.fromPlace" readonly="true"  />
									 
									</td>
									<td width="20%" colspan="1"> To Place<font color="red" >*</font>:</td>
									<td width="25%" colspan="1">
										<s:textfield size="25" name="trApp.toPlace"  readonly="true" />
										
								    </td>
								</tr>
								<tr>
									<td width="20%" colspan="1">Journey Mode<font color="red" >*</font>:</td>
									<td width="35%" colspan="1">
										<s:textfield size="25" name="trApp.journeyName" readonly="true"  />  
									</td>
									<td width="20%" colspan="1">Class<font color="red" >*</font>:</td>
									<td width="25%" colspan="1">
										<s:textfield size="25" name="trApp.journeyClassName" readonly="true"  /> 
									</td>
								</tr>
								<tr>
									<td width="20%" colspan="1">Journey Start Date &nbsp;&nbsp;(DD-MM-YYYY) <font color="red" >*</font>:</td>
									<td width="35%" colspan="1">
										 <s:textfield size="12" name="trApp.journeyDate" maxlength="10"
										readonly="true" />
										</td>
									<td width="20%" colspan="1">Journey Start Time(HH24:MI) :</td>
									<td width="25%" colspan="1">
										<s:textfield size="12" maxlength="4" name="trApp.journeyTime" readonly="true"   maxlength="5"  onkeypress="return numbersWithColon();" />
				 					</td>
								</tr>
		 						<tr>
									<td width="100%" colspan="6" align="center">
										<s:submit cssClass="add" action="TravelAppMngt_addIterator" value="   Add   " onclick="return callAdd();" />&nbsp;
			 						</td>
								</tr>
							</s:if>
		 				</table>
		 				 
		 				<s:if test="travelAppFlag">
		 					  <s:if test="noDataFlag">
			 					<table class="sortable" width="99%">
	  								<tr>
	  									<td class="formth" width="05%">Sr No</td>
	  									<td class="formth" width="15%">From Place</td>
										<td class="formth" width="15%">To Place</td>
										<td class="formth" width="10%">Journey Mode</td>
										<td class="formth" width="10%">Class</td>
										<td class="formth" width="10%">Journey Date</td>
										<td class="formth" width="8%"> Time</td>
										<td class="formth" width="12%">Edit/Delete</td>
	  								</tr>
	  								<%int srNo = 0;%>
	  								<s:iterator value="journeyList">
				 						<tr>
				   							<td class="border2"><%=++srNo%></td>
				  							<td class="border2"><s:property value="itFromPlaceName"/></td>
				  							<td class="border2"><s:property value="itToPlaceName"/></td>   
				  							<td class="border2"><s:property value="itJourneyName"/></td>
				  							<td class="border2"><s:property value="itJourneyClassName"/></td>
				  							<td class="border2"><s:property value="itJourneyDate"/></td>
				  							<td class="border2"><s:property value="itJourneyTime"/>
				  								<s:hidden name="itFromPlaceName" /><s:hidden name ="itToPlaceName" /><s:hidden  name ="itJourneyName"/>
				  								<s:hidden  name ="itJourneyClassName"/>
				  								<s:hidden name="itFromPlaceId" /><s:hidden name ="itToPlaceId" /><s:hidden  name ="itJourneyId"/>
				  								<s:hidden  name ="itJourneyClassId"/>
				  								<s:hidden name ="itJourneyDate" /><s:hidden name ="itJourneyTime" />  
				  							</td>
				 							<td class="border2">
				 								<input type="button" class ="token" value="Edit"
												onclick="return setEdit('<%=srNo-1%>','<s:property value="itFromPlaceName" />','<s:property value="itToPlaceName" />',
												'<s:property value="itJourneyName" />','<s:property value="itJourneyClassName" />','<s:property value="itJourneyDate" />',
												'<s:property value="itJourneyTime" />',
												'<s:property value="itFromPlaceId" />','<s:property value="itToPlaceId" />','<s:property value="itJourneyId" />',
												'<s:property value="itJourneyClassId" />');" /> 
												<input type="button" class ="token" value="Delete" onclick="return setSerialNo(<%=(srNo-1)%>);" />	 
												<script>
													eadd[<%=srNo%>] = document.getElementById('paraFrm_itFromPlaceName').value;
												</script>	
											</td>
			     						</tr> 
			    					</s:iterator>
			    				</table>
			    			</s:if>
			    		 </s:if>
			    		 <s:else>
			    		        <s:hidden name="schJournyId" id ="schJournyId"/>
			    			  <s:if test="noDataFlag">
			    			  <div style="overflow-x:auto;width:770;" >
			 					<table   width="99%">  
			 					<tr height="30">
	  									<td class="headercell" colspan="4"><strong class="formth">Applicant Journey Details </strong></td>
	  									<td class="headercell" colspan="10"><strong class="formth">Approver Journey Details </strong></td> 
						    	</tr>
	  								<tr>
	  									<td class="formth" nowrap="nowrap">Sr No</td>
	  									<td class="formth" nowrap="nowrap">From Place-To Place</td> 
										<td class="formth" nowrap="nowrap">Journey Mode-Class</td> 
										<td class="formth" nowrap="nowrap">Journey Date-Time</td>  
										<td class="formth"  nowrap="nowrap">Journey Mode</td>
										<td class="formth" nowrap="nowrap" >Journey Class</td>
										<td class="formth" width="6%">Bus/Rail/Air Number</td>
										<td class="formth" width="6%">Ticket Number</td>
										<td class="formth" nowrap="nowrap">Journey Date(DD-MM-YYYY)</td>
										<td class="formth" nowrap="nowrap">Time(HH24:MI)</td>
										<td class="formth" width="5%">Cost(Rs.)</td>  
										<td class="formth"nowrap="nowrap" >Other Amt.</td>
										<td class="formth"nowrap="nowrap" >Cancellation Amt.</td> 
										<td class="formth" width="5%">Comments</td> 
	  								</tr>
	  								<%int cnt = 0;%>
	  						<s:iterator value="schJourneyList">
					 			  <tr>
					   						<td class="border2"  ><%=++cnt%>      <s:hidden name="itFromPlaceName" /><s:hidden name ="itToPlaceName" />
					  								                                  <s:hidden  name ="itJourneyClassName"/><s:hidden  name ="itJourneyName"/>
					  								                                  <s:hidden name="itFromPlaceId" /><s:hidden name ="itToPlaceId" /><s:hidden  name ="itJourneyId"/>
					  								                                  <s:hidden  name ="itJourneyClassId"/><s:hidden name ="itJourneyDate" />
					  								                                  <s:hidden name ="itJourneyTime" /> <s:hidden name ="journeyDtlId" /><s:hidden name="schViewFlag"/>    </td>
					  						<td class="border2" nowrap="nowrap"><s:property value="itFromPlaceName"/>-<s:property value="itToPlaceName"/></td>
					  						<td class="border2" nowrap="nowrap"><s:property value="itJourneyName"/>-<s:property value="itJourneyClassName"/></td>
					  						<td class="border2" nowrap="nowrap"><s:property value="itJourneyDate"/>-<s:property value="itJourneyTime"/></td>
					  						<td class="border2" nowrap="nowrap">
					  						
	      	   		                            
	      	   		                            
	      	   		                               <%
	      	   		                               String jourMode = (String)request.getAttribute("schJourneyMode"+cnt); 
	      	   		                               String jourModeId = (String)request.getAttribute("schJourneyModeId"+cnt);   
	      	   		                               %>
	      	   		                             
	      	   		                                  <s:textfield name ="<%="schJourneyMode"+cnt %>"  value="<%=jourMode%>" readonly="true" />
	      	   		                                  <s:hidden name ="<%="schJourneyModeId"+cnt%>" value="<%=jourModeId%>" />
					  						   	</td> 
											<td class="border2" nowrap="nowrap">  
	      	   		                               
	      	   		                               <%
			      	   		                               String jourClass = (String)request.getAttribute("schJourneyClass"+cnt);  
			      	   		                               String jourClassId = (String)request.getAttribute("schJourneyClassId"+cnt);   
	      	   		                               %>
		      	   		                               <s:textfield name ="<%="schJourneyClass"+cnt %>"   value="<%=jourClass%>" readonly="true"/> 
		      	   		                               <s:hidden name ="<%="schJourneyClassId"+cnt%>"  value="<%=jourClassId%>" />
						  				</td>
      	   		                        <td class="border2" width="8"><input type="text" readonly="readonly"  name="schModeNumber" id="schModeNumber<%=cnt%>"  value="<s:property value="schModeNumber"/>"   size="8"     ></td>
											<td class="border2" width="8"><input type="text" readonly="readonly"   name="schTicketNumber" id="schTicketNumber<%=cnt%>" size="8"  value="<s:property value="schTicketNumber"/>"     ></td>
											<td class="border2" width="5%"> <input type="text" readonly="readonly"  name="schJourneyDate" id="schJourneyDate<%=cnt%>" value="<s:property value="schJourneyDate"/>" size="7"  onblur="return validateDate('schJourneyDate<%=cnt%>','Journey  Date');"  onkeypress="return numbersWithHiphen();"  maxlength="10" > 
											 
											 </td>
											<td class="border2" width="4%"><input type="text" readonly="readonly"   name="schJourneyTime" id="schJourneyTime<%=cnt%>" size="3"  value="<s:property value="schJourneyTime"/>"  onblur="return validateTime('schJourneyTime<%=cnt%>','Time(HH24:MI)');" maxlength="5"  onkeypress="return numbersWithColon();"  ></td>
											<td class="border2" width="5%"><input type="text" readonly="readonly" style="text-align:right " name="schJourneyCost" id="schJourneyCost<%=cnt%>"  value="<s:property value="schJourneyCost"/>"  size="4" onkeyup="additionForSchedule();"  onkeypress="return numbersWithDot();"  ></td>
									        <td class="border2"  ><input type="text" readonly="readonly" style="text-align:right " name="schExtraAmt" id="schExtraAmt<%=cnt%>"  value="<s:property value="schExtraAmt" />"  size="4"   onkeypress="return numbersWithDot();" ></td>
									        <td class="border2"  ><input type="text"  style="text-align:right " name="cancelJourneyAmt" id="cancelJourneyAmt<%=cnt%>"  value="<s:property value="cancelJourneyAmt" />" onkeyup="return additionForSchedule();" size="4"  onkeypress="return numbersWithDot();" ></td>
									        <td class="border2" width="5%"><s:textarea readonly="true"  name="schJourneyComment" rows="2"  cols="40" /></td>
									         <script>
												 	schArr[<%=cnt%>] = document.getElementById('paraFrm_schJourneyComment').value;
											</script>	
									 </tr> 
				     			</s:iterator> 
				     			<tr>
	  									<td   colspan="7">&nbsp;</td>
	  									<td  colspan="2" align="right">Total Journey Cost : </td>  
	  									<td><input type="text"  style="text-align:right " name="totalJCost" id="totalJCost"  value="<s:property value="totalJCost"/>"  size="4" readonly="true"  ></td>
	  									<td  colspan="2" align="right">Total journey Cancellation : </td>  
	  									<td><input type="text"  style="text-align:right " name="totalJCancel" id="totalJCancel"   value="<s:property value="totalJCancel"/>"  size="4" readonly="true"  ></td>
						    	</tr>
						    	<tr>
	  									<td   colspan="13">&nbsp;</td>
	  									 
						    	</tr>
				     			
			    				</table>
			    					</div>
			    					
			    					  <div style="overflow-x:auto;width:770;" >	
			    		   <table   width="99%">   
			 					<tr height="30">
			 					
			 					           
	  								<td class="headercell" colspan="11"><strong class="formhead">Staying Details </strong></td>
	  									 
						    	</tr>
	  								<tr>
	  									<td class="formth" nowrap="nowrap">Sr No</td> 
										<td class="formth" nowrap="nowrap">Hotel Name</td>  
										<td class="formth"  nowrap="nowrap">Address</td>
										<td class="formth" nowrap="nowrap" >Booking From Date</td>
										<td class="formth" nowrap="nowrap">From Time</td>
										<td class="formth" nowrap="nowrap">To Date</td>
										<td class="formth" nowrap="nowrap">To Time</td>
										<td class="formth" nowrap="nowrap">Bill Amt.</td> 
										<td class="formth" nowrap="nowrap">Other Amt.</td> 
										<td class="formth"nowrap="nowrap" >Cancellation Amt.</td> 
										<td class="formth" nowrap="nowrap">Comments</td> 
	  								</tr>
	  								<%int k = 0;%> 
	  							<s:iterator value="hotelList">
					 			  <tr>
					   						<td class="border2" width="2%"><%=++k%>  </td>
					  					    <td class="border2" nowrap="nowrap" > <s:textfield name="hotelName" readonly="true" size="10"/>        </td>
		      	   		                    <td class="border2" width="8"><s:textarea  name="hotelAddress" readonly="true"  rows="2"  cols="35"/></td>
											<td class="border2" nowrap="nowrap">  
											<input type="text"   name="chkInDate" readonly="true" id="chkInDate<%=k%>"  value="<s:property value="chkInDate"/>"   size="10" onkeypress="return numbersWithHiphen();"  onblur="return validateDate('chkInDate<%=k%>','Staying From Date');"  >
											  
									       </td>
											<td class="border2" nowrap="nowrap"><input type="text" readonly="true" name="chkInTime" id="chkInTime<%=k%>" value="<s:property value="chkInTime"/>"  size="10" onkeypress="return numbersWithColon();"  maxlength="6" onblur="return validateTime('chkInTime<%=k%>','Staying From Time');" >   </td>
											<td class="border2" nowrap="nowrap"><s:hidden name="hotelDtlId"></s:hidden>
											<input type="text"   name="chkOutDate" readonly="true" id="chkOutDate<%=k%>"  value="<s:property value="chkOutDate"/>"   size="10" onkeypress="return numbersWithHiphen();"  onblur="return validateDate('chkOutDate<%=k%>','Staying To Date');"  >
											  
									      	<td class="border2" nowrap="nowrap"> <input type="text" readonly="true" name="chkOutTime" id="chkOutTime<%=k%>" value="<s:property value="chkOutTime"/>"  size="10" onkeypress="return numbersWithColon();" maxlength="6" onblur="return validateTime('chkOutTime<%=k%>','Staying To Time');" > </td>
											<td class="border2"nowrap="nowrap"><input type="text" readonly="true" name="hotelBill" id="hotelBill<%=k%>"  value="<s:property value="hotelBill"/>" size="10"      onkeypress="return numbersWithDot();"  > </td>
							      	        <td class="border2"nowrap="nowrap"><input type="text" readonly="true" name="extraHotelBill" id="extraHotelBill<%=k%>" value="<s:property value="extraHotelBill"/>"  size="10"     onkeypress="return numbersWithDot();"   > </td>
							      	        <td class="border2"nowrap="nowrap"><input type="text" name="hotelCancelAmt" id="hotelCancelAmt<%=k%>" value="<s:property value="hotelCancelAmt"/>"  size="10" onkeyup="return additionForHotel();"  onkeypress="return numbersWithDot();"   > </td>
							      	        <td class="border2" width="8"><s:textarea  name="hotelComment" readonly="true" rows="2"  cols="35"/></td>
							      	          <script>
													hotelArr[<%=k%>] = document.getElementById('paraFrm_hotelComment').value;
												</script>	
							      	</tr> 
							      	</s:iterator>
							      	
							      	<tr>
	  									<td colspan="6">&nbsp;</td> 
										<td   nowrap="nowrap">Total Bill</td>
										<td   nowrap="nowrap"><s:textfield name="hotelTotalBill" readonly="true" id="hotelTotalBill" size="10" readonly="true"/>   </td>
										<td   nowrap="nowrap">Total Bill Canceled</td>
										<td   nowrap="nowrap"><s:textfield name="hotelTotalCanceled" readonly="true" id="hotelTotalCanceled"  size="10" readonly="true"/>   </td>  
	  								</tr>
	  								<tr>
	  									<td colspan="9">&nbsp;</td> 
									</tr>
	  						   
				     			 
				     
			    		</table> 
			    		</div>  
			    			<table width="99%">
			    			<tr>
			    				   <td  align="center" colspan="1" width="15%"> Total Amount : </td> 
			    				   <td  align="center" colspan="1" width="10%"><s:textfield name="totalTravelCost" id="totalTravelCost" size="10" readonly="true"/>    </td> 
			    				   <td  align="center" colspan="1" width="25%"> Total Canceled Amount : </td> 
			    				   <td  align="center" colspan="1" width="10%"><s:textfield name="totalCanceled" id="totalCanceled" size="10" readonly="true"/>    </td> 
			    				    <td  align="center" colspan="7">&nbsp;</td> 
	  				         </tr>
			    				<tr>
			    				  <td  align="center" colspan="11">  
			    				   <s:submit action="TravelAppMngt_travelCancelSave" cssClass="token"  theme="simple"  value="   Save "  onclick="return callScheduleSave();"/>
			    				 
					  				<input type="button" Class="token" theme="simple"  value="   Back " onclick="callBack();" /> 
					  				  </td> 
	  				                </tr>
			    			</table>
			    			
			    			</s:if>
			    			
			    		</s:else>
		    			
			
</s:form>

<script type = "text/javascript" src = "../pages/common/datetimepicker.js"></script>

<script>


function callClass()
{
 var journeyId = document.getElementById('paraFrm_trApp_journeyId').value;
	if(journeyId=="")
	{
	  alert("Please select Journey Mode !");
	  return false;
	}else
	{
	  callsF9(500,325,'TravelAppMngt_f9JourneyClass.action');
	  return true;
	}
	 return true;
}

function callJourneyModeF9Popup()
 { 	 
  document.getElementById('paraFrm_trApp_journeyId').value =""; 
  document.getElementById('paraFrm_trApp_journeyClassName').value =""; 
  callsF9(500,325,'TravelAppMngt_f9Journey.action'); 
	  return true; 
}

   function additionForSchedule()
    {
         var JCancel =0;
        
         var JTotalCancel =0;
       for(var i=1; i<schArr.length;i++)
       {
        var tempCost =document.getElementById('cancelJourneyAmt'+i).value; 
        
        if(tempCost=="")
        tempCost=0;
        JTotalCancel = eval(JTotalCancel) + Math.round(tempCost); 
        
       }  
       if(JTotalCancel =="")
       JTotalCancel="0";
       document.getElementById('totalJCancel').value =JTotalCancel; 
       var hTotalCancel = document.getElementById('hotelTotalCanceled').value;
       if(hTotalCancel=="")
       {
       hTotalCancel =0;
       }
        document.getElementById('totalCanceled').value =eval(JTotalCancel)+eval(hTotalCancel);
    }
    
    
     function additionForHotel()
{        

         var hTotalCancel =0; 
		       for(var i=1; i<hotelArr.length;i++)
		       {
		      
		        var tempCost =document.getElementById('hotelCancelAmt'+i).value;  
		        if(tempCost=="")
		        tempCost=0;
		        
		        hTotalCancel= eval(hTotalCancel) + Math.round(tempCost); 
		       }  
          document.getElementById('hotelTotalCanceled').value = hTotalCancel; 
          var jTotalCancel = document.getElementById('totalJCancel').value;
          document.getElementById('totalCanceled').value = eval(hTotalCancel)+eval(jTotalCancel);
       
    }
    
 function setSerialNo(code){
 
 if(!(document.getElementById('paraFrm_status').value=="P")){
	alert("You can't update the application !");
	return false;
	}
    var conf=confirm("Do you really want to delete this record ?");
  	 		if(conf) 
  	 	{
  		 
  			  document.getElementById('paraFrm_serialNo').value=code; 
  			 
	        document.getElementById('paraFrm').action='TravelAppMngt_delItem.action';
	        
	        document.getElementById('paraFrm').submit();	
	         
  			 	return true;
  			 }
	  		 else
	  		 {
	  		 	 return false;
	  		 }
 
      
	return true;
}

function callAdd()
{
      empName =  document.getElementById("paraFrm_trApp_empName").value;
  	  fromPlace =  document.getElementById("paraFrm_trApp_fromPlace").value;
	  toPlace = document.getElementById("paraFrm_trApp_toPlace").value;
	  journeyName = document.getElementById("paraFrm_trApp_journeyName").value;
	  journeyClassName = document.getElementById("paraFrm_trApp_journeyClassName").value;
	  journeyDate = document.getElementById("paraFrm_trApp_journeyDate").value;
 
 
		  if(!(document.getElementById('paraFrm_status').value=="P")){
			  alert("You can't update the application !");
			  return false;
			}
		   if(empName =="")
			 {
			   alert("Please select Employee Name !");
			   return false;
			 } 
			 if(fromPlace =="")
			 {
			   alert("Please select From Place !"); 
			   return false;
			 }
			  if(toPlace =="")
			 {
			   alert("Please select To Place !"); 
			   return false;
			 }
			  if(!validateDate('paraFrm_trApp_journeyDate','Journey Date'))
  		      {
			       return false;
		           }
		 
			   if(journeyName =="")
			 {
			   alert("Please select Journey Mode !"); 
			   return false;
			 }
			 
			if(journeyClassName =="")
			 {
			   alert("Please select Journey Class!"); 
			   return false;
			 }  
			 if(journeyDate =="")
			 {
			   alert("Please enter Journey Date!"); 
			   return false;
			 }  
			  
       return true;
}
 

function setEdit(id,itFromPlaceName,itToPlaceName,itJourneyName,itJourneyClassName,itJourneyDate,
itJourneyTime,itFromPlaceId,itToPlaceId,itJourneyId,itJourneyClassId){
if(!(document.getElementById('paraFrm_status').value=="P")){
	alert("You can't update the application !");
	return false;
	}    
	     document.getElementById("paraFrm_checkEdit").value=id;	 
	  	document.getElementById("paraFrm_trApp_fromPlace").value=itFromPlaceName;
	  	document.getElementById("paraFrm_trApp_toPlace").value=itToPlaceName;
	  	document.getElementById("paraFrm_trApp_journeyName").value=itJourneyName;
	  	document.getElementById("paraFrm_trApp_journeyClassName").value=itJourneyClassName;
	  	document.getElementById("paraFrm_trApp_journeyDate").value=itJourneyDate;
	  	document.getElementById("paraFrm_trApp_journeyTime").value=itJourneyTime;
	  	document.getElementById("paraFrm_trApp_fromPlaceId").value=itFromPlaceId;
	  	document.getElementById("paraFrm_trApp_toPlaceId").value=itToPlaceId;
	  	document.getElementById("paraFrm_trApp_journeyId").value=itJourneyId;
	  	document.getElementById("paraFrm_trApp_journeyClassId").value=itJourneyClassId;
	  	document.getElementById("paraFrm_trApp_fromPlace").value=prName;
	  	 
	  	//	document.getElementById("paraFrm").submit();
	  		
	 }
	 
	 function callTime()
	 {
	    var startTime = document.getElementById('paraFrm_comp_startTime').value;
       var endTime = document.getElementById('paraFrm_comp_endTime').value;
	    var start = startTime.split(":");
	    var end = endTime.split(":");
	    
	    var first = eval(start[0])* eval(start[1]) ;
	    var second = eval(end[0])* eval(end[1]) ; 
	 }
	 
	 
	 function callSave()
   { 
       travelAppId =  document.getElementById("paraFrm_trApp_travelAppId").value; 
       empName =  document.getElementById("paraFrm_trApp_empName").value;
  	 
		     if(travelAppId!="")
			 {
			   alert("Please update record !");
			   return false;
			 } 
		  if(!(document.getElementById('paraFrm_status').value=="P")){
			  alert("You can't update the application !");
			  return false;
			}
		   if(empName =="")
			 {
			   alert("Please select Employee Name !");
			   return false;
			 } 
			 
			 if(eadd.length <=0)
			 {
			  alert("Please add atleast one record !");
	           return false; 
			 } 
         return true;
}
	   function callUpdate()
	    { 
	         travelAppId =  document.getElementById("paraFrm_trApp_travelAppId").value; 
             empName =  document.getElementById("paraFrm_trApp_empName").value;
  	 
		      if(travelAppId=="")
			 {
			   alert("Please select record to update !");
			   return false;
			 } 
			 
		   if(!(document.getElementById('paraFrm_status').value=="P")){
			  alert("You can't update the application !");
			  return false;
			}
		   if(empName =="")
			 {
			   alert("Please select Employee Name !");
			   return false;
			 } 
			 
			 if(eadd.length <=0)
			 {
			  alert("Please add atleast one record !");
	           return false; 
			 }  
	 return true;
	 }
	    
	 
	 function autoDate () {
	var tDay = new Date();
	var tMonth = tDay.getMonth()+1;
	var tDate = tDay.getDate();
			if ( tMonth < 10) tMonth = "0"+tMonth;
			if ( tDate < 10) tDate = "0"+tDate;
			if(document.getElementById('paraFrm_trApp_appDate').value=="")
				document.getElementById("paraFrm_trApp_appDate").value = tDate+"-"+tMonth+"-"+tDay.getFullYear();
				//alert("date"+document.getElementById("paraFrm_vchDate").value);
  }
autoDate();


 function callReport()
	 {
	 
	 var id = document.getElementById('paraFrm_trApp_travelAppId').value;
	 if(id=="")
	 {
	   alert(" Please select the record !");
	   return false;
	 }
	 else
	 {
	 document.getElementById('paraFrm').target ='_blank';	 
	 document.getElementById('paraFrm').action = "TravelAppMngt_report.action";
	 document.getElementById('paraFrm').submit();
	 document.getElementById('paraFrm').target ="";
	 }
	 }
    
    //scheduled javascript
    function callJourneyMode(id)
    {
    
      document.getElementById('schJournyId').value=id; 
     //  document.getElementById('paraFrm_schJourneyMode'+id).value =  document.getElementById('schViewJourneyMode'+id).value;
      callsF9(500,325,'TravelAppMngt_f9SchJourney.action'); 
      // var schJourneyMode = document.getElementById('paraFrm_schJourneyMode'+id).value
      /// alert(schJourneyMode);
      // document.getElementById('schViewJourneyMode'+id).value = schJourneyMode;
     
    }
    function callJourneyClass(id)
    {
      document.getElementById('schJournyId').value=id;
      sModeId = document.getElementById('paraFrm_schJourneyModeId'+id).value; 
     if(sModeId=="" ||sModeId=="0")
       {
       alert("Please select Journey Mode !");
     } else
      {
      callsF9(500,325,'TravelAppMngt_f9SchJourneyClass.action');
      }
    }
    
    function callHotel(id)
    {
      document.getElementById('schJournyId').value=id;
      callsF9(500,325,'TravelAppMngt_f9SchHotel.action');
    }
    
    function callBack()
    {  
        document.getElementById('paraFrm').action = "TravelCancel_callAdminCancel.action";
		document.getElementById('paraFrm').submit(); 
    }
    
function callScheduleSave()
{ 
 for(var i=1; i<hotelArr.length;i++)
	{ 
	    
		    if(!validateTime('chkInTime'+i,'Staying From Time'))
		    {
		     return false;
		    }    
		     if(!validateTime('chkOutTime'+i,'Staying To Time'))
		    {
		     return false;
		    }   
	 } 
	 
	  for(var i=1; i<schArr.length;i++)
	  { 
	    if(!validateTime('schJourneyTime'+i,'Time(HH24:MI)'))
		    {
		     return false;
		    } 
		    
		    if(!validateDate('schJourneyDate'+i,'Journey  Date'))
		    {
		     return false;
		    }  
	 } 
  return true;
}
     //callScheduleSaveFinal
     
     
 function callScheduleSaveFinal()
{ 

document.getElementById('paraFrm_scheduleFinalFlag').value ="final";

 for(var i=1; i<hotelArr.length;i++)
	{ 
	    
		    if(!validateTime('chkInTime'+i,'Staying From Time'))
		    {
		     return false;
		    }    
		     if(!validateTime('chkOutTime'+i,'Staying To Time'))
		    {
		     return false;
		    }   
	 } 
	 
	  for(var i=1; i<schArr.length;i++)
	  { 
	    if(!validateTime('schJourneyTime'+i,'Time(HH24:MI)'))
		    {
		     return false;
		    } 
		    
		    if(!validateDate('schJourneyDate'+i,'Journey  Date'))
		    {
		     return false;
		    }  
	 } 
  return true;
}


function callReportMis(){  
	  document.getElementById('paraFrm').target="_blank";
      document.getElementById('paraFrm').action="TravelScheduleReport_report.action";
      document.getElementById("paraFrm_travelReportId").value = document.getElementById("paraFrm_trApp_travelAppId").value ;
	  document.getElementById('paraFrm').submit();  
      document.getElementById('paraFrm').target="main";
  
   	
	
}
 
</script>
 
 
  