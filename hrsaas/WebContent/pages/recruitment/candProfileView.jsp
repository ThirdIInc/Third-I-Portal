<%@ taglib prefix="s" uri="/struts-tags"%>

<s:hidden name="refFlag"/>
<s:hidden name="expFlag"/>
<s:hidden name="skillFlag"/>
<s:hidden name="domFlag"/>
<s:hidden name="qualiFlag"/>
<s:hidden name="radioEmp"/>
<s:form action="CandidateProfile" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg"><!--
        <tr>
          <td colspan="3" valign="bottom" class="txt">&nbsp;</td>
        </tr>
        <tr>
          <td colspan="3" valign="bottom" background="../pages/common/css/default/images/lines.gif" class="txt"><img src="../pages/common/css/default/images/lines.gif" width="16" height="1" /></td>
        </tr>
        --><!--<tr>
          <td colspan="3" valign="bottom" class="txt"><img src="../pages/common/css/default/images/space.gif" width="5" height="5" /></td>
        </tr>
        --><tr>
          <td colspan="3"><table width="100%"  cellpadding="0" cellspacing="0" class="formbg">   
    	<tr>
          <td width="4%" valign="bottom" class="txt" ><strong class="formhead"><img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" /></strong></td>
          <td width="93%" class="txt" ><strong class="text_head" >My Profile</strong></td>
          <td width="3%" valign="top" class="txt"><div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
   		 </tr>
    	 </table>
   		 </td>
    	 </tr><!--
    	  <tr>
          <td colspan="3" valign="bottom" class="txt"><img src="../pages/common/css/default/images/space.gif" width="5" height="5" /></td>
        </tr>
        --><!--
        <tr>
          <td width="4%" valign="bottom" class="txt"><strong class="formhead"><img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" /></strong></td>
          <td width="93%" class="txt"><strong class="formhead">Candidate DataBank </strong></td>
          <td width="3%" valign="top" class="txt"><div align="right"><img src="../pages/common/css/default/images/help.gif" width="16" height="16" /></div></td>
        </tr>
        --><!--<tr>
          <td height="5" colspan="3">
          <img src="../pages/common/css/default/images/space.gif" width="5" height="7" />
          </td>
        </tr>   --><s:hidden name="candCode"/>
         <s:hidden name="candName"/>
         <s:hidden name="experience"/>
         <s:hidden name="postingDate"/>
         <s:hidden name="shortStatus"/>
        <s:hidden name="cityCode1"/>
		<s:hidden name="stateCode1"/>
		<s:hidden name="countryCode1"/>
        <tr>
          <td colspan="3">
          		<table width="100%" border="0" cellpadding="0" cellspacing="0">
            		<tr>
	              		<td width="78%">
	              		 	<input type="button" value="Edit" class="edit" onclick="return editFun(); ">
	              		 	<input type="button" value="   Next"  class="next" onclick="return nextFun();">
	             		</td>
	              		<td width="22%"><div align="right"></div></td>
            		</tr>
          		</table>
         </td>
        </tr><!--
         <tr>
          <td colspan="3" valign="bottom" class="txt"><img src="../pages/common/css/default/images/space.gif" width="5" height="5" /></td>
        </tr>
         --><tr>
          <td colspan="3">
          	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
              	<tr>
              	  <td>
                	<table width="98%" border="0"  align="center" cellpadding="0" cellspacing="2">
		                 	 <tr>
		                 	   <td colspan="5" class="formhead"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
		                  	</tr>
		                 	 <tr>
                 				 <strong class="formhead">Candidate Profile:</strong>
                    			<td colspan="5" class="formhead"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
                  		 	 </tr>
                  		 	  	<tr>
		                        <td height="22" class="formtext" width="16%" valign="top">First Name<font color="red">*</font>:</td>
		                        <td height="22" width="24%">
		                        <s:property value="firstName" />
		                       </td>
		                        <td height="22" class="formtext" valign="top" width="16%">Photograph :</td>
					             <td height="22" rowspan="6" width="24%"><table width="5" height="100" border="0" cellpadding="0"
								cellspacing="0" class="border">
								 <tr>
									<td bgcolor="#FFFFFF" align="center" style="padding: 3px;">
						
									<%
									String str = (String) request.getAttribute("photo");
									
									%>
				
									
								<%if(!(("null").equals(str)|| str.equals("")))   { %>
										
										<img src="../pages/images/<%=session.getAttribute("session_pool") %>/databankphoto/<%=str %>"
											height="150" width="150"/>

										<% } else  { %>
															
												<img src="../pages/images/employee/NoImage.jpg" height="150"  width="150"
											align="middle" />
											
										<%} %>

												
									 </td>
									</tr>
								</table>
					             
					        </td>
		                        
		                   </tr>
		                    
		                    <tr>
		                   
		                        <td height="22" class="formtext" width="16%">Middle Name  : </td>
		                        <td height="22" width="24%">
		                        <s:property value="middleName" />
		                        </td>
                     	 	</tr>
                     	 	<tr>
		                   
		                        <td height="22" class="formtext" width="16%">Last Name :</td>
		                        <td height="22" width="24%">
		                        <s:property value="lastName" />
		                        </td>
                     	 	</tr>
		                    <tr>
                     	 		<td width="16%" height="22" class="formtext" nowrap="nowrap">Date Of Birth :</td>
                    			<td  height="22" width="24%">
                     			 <s:property value="dob"  /></td>
                     			 
                     		</tr>	 
                      		<tr>
                      			
								<td height="22" class="formtext" width="16%">Gender :</td>
								<td height="22" width="24%">
                   				 <s:property value="genderView"/></td>
                      		</tr>
                      		
                      		 <tr>
				                <td height="22" class="formtext" width="16%">Marital Status :</td>
				                <td height="22" width="24%"><s:property value="marriageView"  /></td>
				                 
               				 </tr>
                      		
                      		 <tr>
				                <td height="22" class="formtext" width="16%">Experience :</td>
				                <td height="22" width="24%"><s:property value="expView" /></td>
				                 
               				 </tr>
               				 
               				 <tr>
				                 <td height="22" class="formtext" valign="top" width="16%">Current Address :</td>
							                <td height="22" valign="top" width="24%"><s:property value="address"  /> </td>
							                
							                <td height="22" class="formtext" valign="top" width="16%">Permanent Address :</td>
							                <td height="22" valign="top" width="24%"> <s:property value="address1"  /></td>
							                 
	               			 </tr>
	               			 
	               			  <tr>
	               					 <td height="22" class="formtext" width="16%">City :</td>
					                <td height="22" width="24%"> <s:property value="city" />
						                <s:hidden name="cityCode"/>
						                <s:hidden name="stateCode"/>
						                <s:hidden name="countryCode"/>
						              
									</td>
									
									 <td height="22" class="formtext" width="16%">City :</td>
					                <td height="22" width="24%"> <s:property value="city1"/>
					          
						               
									</td>
									
								</tr>
								<tr>
									<td height="22" class="formtext" width="16%">State :</td>
					                <td height="22" width="24%"> <s:property value="state"/> 
						            </td>
						            
						            <td height="22" class="formtext" width="16%">State :</td>
					                <td height="22" width="24%"><s:property value="state1"/> 
						            </td>
	               				 </tr>    
	               				 
	               				  <tr>
	               					 <td height="22" class="formtext" width="16%">Country :</td>
					               <td height="22" width="24%"><s:property value="country"/>
					                </td>
					               <td height="22" class="formtext" width="16%">Country :</td>
					                <td height="22" width="24%"><s:property value="country1"/>
					                
					              </td>
					             </tr> 
		                    		
		                         <tr>    
									
								<td height="22" class="formtext" width="16%">Pincode :</td>
					                <td height="22" width="24%"><s:property value="pincode"/> </td>
						              
						              
									<td height="22" class="formtext" width="16%">Pincode :</td>
					                <td height="22" width="24%"><s:property value="pincode1"/>
						              
									</td>
	               			</tr>
               				 <tr>
               					 <td height="22" class="formtext" width="16%">Residence Phone :</td>
				                <td height="22" width="24%"> <s:property value="resPhone" />
					              
								</td>
								<td height="22" class="formtext" width="16%">Mobile :</td>
				                <td height="22" width="24%"> <s:property value="mobile" />
					              
								</td>
               				 </tr>
               				  <tr>
               					 <td height="22" class="formtext" width="16%">Office Phone :</td>
				                <td height="22" width="24%"> <s:property value="offPhone" />
					              
								</td>
								<td height="22" class="formtext" width="16%">Email Id :</td>
				                <td height="22" width="24%"> <s:property value="email" />
					              
								</td>
               				 </tr>
               				 
               				   <tr>
               					 <td height="22" class="formtext" width="16%">Current CTC in Lacs :</td>
				                <td height="22" width="24%"><s:property value="currentCtc"  />
					              
								</td>
								<td height="22" class="formtext" width="16%">Expected CTC in Lacs :</td>
				                <td height="22" width="24%"><s:property value="expectedCtc"  />
					              
								</td>
               				 </tr>
               				 
               				  <tr>
               					 <td height="22" class="formtext" width="16%">Passport No. :</td>
				                <td height="22" width="24%"> <s:property value="passport" />
					              
								</td>
								<td height="22" class="formtext" width="16%">Pan No.:</td>
				                <td height="22" width="24%"> <s:property value="panNo"   />
					              
								</td>
               				 </tr>
               				 
               				   <tr>
	               				 	<td height="22" class="formtext" valign="top" width="16%">Other Information :</td>
					                <td height="22" valign="top" width="24%"> <s:property value="otherInfo"  /></td>
					                
					                <td height="22" class="formtext" valign="top" width="16%"></td>
					                <td height="22" width="24%"></td>
					                 
	               				 </tr>
	               				<tr>
	               				 	<td height="22" class="formtext" valign="top" width="16%">View Resume :</td>
					                <td height="22" valign="top" width="24%"><s:hidden name="uploadFileName"/>
					                <input type="button" class="token" theme="simple" value="View" 
				         				onclick="showRecord('<s:property value="uploadFileName" />');" /></td>
					                
					                <td height="22" class="formtext" valign="top" width="16%"></td>
					                <td height="22" width="24%"></td>
					                 
	               			</tr> 		
		                    				 
	               		 <tr>
               			 <td colspan="4" width="100%">
               			 		<table width="100%" border="0"  align="center" cellpadding="0" cellspacing="2">
	               			
	               			<tr>
		                   
		                        <td height="22" width="20%" class="formtext">Do you know anybody currently
		                        working with the group :
		                    
		                      </td><td width="80%">
		                        <s:hidden name="radioFlag" />
							 	<s:if test="radioFlag"> 
								 <s:radio  name="doYou" disabled="true" value="%{'Y'}"   list="#{'Y':'Yes','N':'No'}"  onclick="showEmpId();" ></s:radio> 
								</s:if>
								<s:else>
								<s:radio  name="doYou" disabled="true" value="%{'N'}"   list="#{'Y':'Yes','N':'No'}"  onclick="showEmpId();"></s:radio>
								</s:else> 
		                      
		                      </td>
		                     
                     	 	</tr>
                     	 	
                     	 	<s:if test="radioFlag">
                     	 	 <tr>    
									
							<td height="22" class="formtext" width="20%">Employee Details :
					           </td><td width="80%">
				             <s:property value="refEmpName" />	</td>      
						 	
	               			</tr>
	               			</s:if>
                     	 	<tr>
                     
		                   
		                        <td height="22" class="formtext" width="20%" align="left">Whether you employed earlier in the group  :
		                     
		             			
		                    
		                       </td><td width="80%">
		                       
		                        <s:hidden name="radioFlag1" />
							 	<s:if test="radioFlag1">
								 <s:radio  name="wheYouEmp" disabled="true"  value="%{'Y'}"   list="#{'Y':'Yes','N':'No'}" ></s:radio> 
								</s:if>
								<s:else>
								<s:radio  name="wheYouEmp" disabled="true" value="%{'N'}"   list="#{'Y':'Yes','N':'No'}" ></s:radio>
								</s:else>  
		                       
		                       </td>
		                    
		                
                     	</tr>
                     	
                     	 <tr>
		                   
		                        <td height="22" class="formtext" width="20%">Relocation :</td>
		                    	
		                    	<td width="80%">
		                        <s:hidden name="radioFlag2" />
							 	<s:if test="radioFlag2">
								 <s:radio  name="relocate" disabled="true"  value="%{'Y'}"   list="#{'Y':'Yes','N':'No'}" ></s:radio> 
								</s:if>
								<s:else>
								<s:radio  name="relocate"  disabled="true" value="%{'N'}"   list="#{'Y':'Yes','N':'No'}" ></s:radio>
								</s:else>  
		                        </td>
                     	 	</tr>
                     	
                     	 	
               			 </table>
               			 
               			 </td></tr>
	               			
                	 	 
               </table>
           </td>
        </tr>
       </table>
        
        <tr>
          <td colspan="3">
          		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
    				
           					<tr>
            					<td class="formtext" >
            						<table width="100%" border="0" cellpadding="0" cellspacing="0" >
                						<tr >
                  							<td >
                  								<table width="100%" border="0" cellpadding="0" cellspacing="0">
                      				 			  <tr> <td>
                      				 			  	<strong class="text_head">Reference Details : 
                      				 			  	</strong>
                      				 			  	</td>
                      				 			  	
							 					</tr>
								 				<tr>	
		                       					 <td colspan="5">
		                       					 	<table width="100%" id="tblRef">
		                          					 <tr>
														<td  valign="top" class="formth" nowrap="nowrap">Sr.No</td>
														<td  valign="top" class="formth" align="center">Reference Name</td>
														<td  valign="top" class="formth" align="center">Profession</td>
														<td  valign="top" class="formth" align="center">Contact Details</td>
														<td  valign="top" class="formth" align="center">Comments</td>
													</tr>
		                     						
		                     						 <s:iterator value="refList" >
						                               <tr >
						                                   <td  align="center" class="sortableTD"><s:property value="srNo" /></td>
						                                   <td align="left" class="sortableTD">&nbsp;
						                                   		<s:hidden name="refDtlCode" />
						                                  		 <s:property  value="refName"  />
						                                   </td>
							    			 				<td  align="left" class="sortableTD">&nbsp;
							    			 					<s:property value="profession"   />
							    			 				</td>
							    			 				<td  align="left" class="sortableTD">&nbsp;
							    			 					<s:property  value="contactDet"   />
							    			 				</td>
							    			 				<td  align="left" class="sortableTD">&nbsp;
							    			 					<s:property  value="refComment"  />
							    			 				</td>
							    			 				     
						                                </tr>
		                        					</s:iterator>
		                        				 <tr><td align="center" width="100%" colspan="4">
                     			 								<s:if test="refFlag"></s:if>
                     											 <s:else>
                     			 
                     			 								<font color="red">There is no data to display.</font>
                     			 								</s:else>
                     										</td>
                     			 
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
             </table>
       </td>
      </tr>
       <tr>
          <td colspan="3" valign="bottom" class="txt"><!--
          
          
          <img src="../pages/common/css/default/images/space.gif" width="5" height="5" />
          
          
          --></td>
        </tr>
     <tr>
	              		<td width="78%">
	              		 <input type="button" value="Edit" class="edit" onclick="return editFun(); ">
	              		 	<input type="button" value="   Next" class="token" class="next" onclick="return nextFun();">
	             		</td>
	              		<td width="22%"><div align="right"><span class="style3"></span><strong>Page 1 of 2</strong></div></td>
     </tr>
	</table>
</s:form>    

<script>

function editFun(){

	document.getElementById("paraFrm").action="CandidateProfile_editFun.action";
	document.getElementById("paraFrm").submit();

}

function nextFun(){

	document.getElementById("paraFrm").action="CandidateProfile_nextPage.action";
	document.getElementById("paraFrm").submit();

}





	
	

function showRecord(fileName)
	{	document.getElementById('paraFrm').target='_blank';
		document.getElementById('paraFrm').action = "CandDataBank_viewCV.action?fileName="+fileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target='main';
		
	}

function exportpdfreportFun(){
		document.getElementById('paraFrm').target='_blank';
		document.getElementById('paraFrm').action = "CandDataBank_getReportInPdf.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target='main';

}

function exporttextreportFun(){
		document.getElementById('paraFrm').target='_blank';
		document.getElementById('paraFrm').action = "CandDataBank_getReportInText.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target='main';

}

</script>
