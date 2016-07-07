<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="CandidateProfile" validate="true" id="paraFrm"
	validate="true" theme="simple"><s:hidden name="listFlag"/>
		<s:hidden name="refFlag"/>
	<s:hidden name="expFlag"/>
	<s:hidden name="skillFlag"/>
	<s:hidden name="qualiFlag"/>
	<s:hidden name="domFlag"/>
	<s:hidden name="radioEmp"/>
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg"><!--
        <tr>
          <td colspan="3" valign="bottom" class="txt">&nbsp;</td>
        </tr>
        <tr>
          <td colspan="3" valign="bottom" background="../pages/common/css/default/images/lines.gif" class="txt"><img src="../pages/common/css/default/images/lines.gif" width="16" height="1" /></td>
        </tr>
        <tr>
          <td colspan="3" valign="bottom" class="txt"><img src="../pages/common/css/default/images/space.gif" width="5" height="5" /></td>
        </tr>
          --><tr>
          <td colspan="3"><table width="100%"  cellpadding="0" cellspacing="0"  class="formbg">   
        <tr>
          <td width="4%" valign="bottom" class="txt" ><strong class="formhead"><img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" /></strong></td>
          <td width="93%" class="txt" ><strong class="text_head" >My Profile</strong></td>
          <td width="3%" valign="top" class="txt"><div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
       </tr>
     </table>
     </td>
     </tr>
        
        
        
        <!--
        <tr>
          <td width="4%" valign="bottom" class="txt"><strong class="formhead"><img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" /></strong></td>
          <td width="93%" class="txt"><strong class="formhead">Candidate DataBank </strong></td>
          <td width="3%" valign="top" class="txt"><div align="right"><img src="../pages/common/css/default/images/help.gif" width="16" height="16" /></div></td>
        </tr>
        --><!--<tr>
          <td height="5" colspan="3"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
        </tr>
         --><s:hidden name="candCode"/>
         <s:hidden name="candName"/>
         <s:hidden name="experience"/>
         <s:hidden name="postingDate"/>
         <s:hidden name="shortStatus"/>
        <s:hidden name="cityCode1"/>
		<s:hidden name="stateCode1"/>
		<s:hidden name="countryCode1"/>
		<s:hidden name="updateFirst"/>
		<s:hidden name="updateSecond"/>
		<s:hidden name="cancelFirst" />
		<s:hidden name="dataPath"/>
		<s:hidden name="pathPhoto"/>
		<s:hidden name="show"/>
		<s:hidden name="myPage"/>
		<s:hidden name="hiddenCode" />
         <!--
         <s:textfield name="dob"/>
         --><tr>
          <td colspan="3">
          		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
            		  <tr>
	              		<td width="60%">
	              		 <input type="button" value="Save" class="save" onclick="return saveFun();">
	              		 <input type="button" value="Save and Next" class="saveandnext" onclick="return saveAndNext();">
	             		</td><td width="22%"><div align="right"><span class="style3"><font color="red">*</font></span>Indicates Required </div></td>
	              		
     </tr>
          		</table><label></label>
          </td>
        </tr>
       
        <tr>
          <td colspan="3">
          	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
              	<tr>
              	  <td>
                	<table width="98%" border="0"  align="center" cellpadding="0" cellspacing="2" ><!--
		                 	 <tr>
		                 	   <td colspan="5" class="formhead"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
		                  	</tr>
		                 	 --><tr>
                 				 <strong class="text_head">Candidate Details :</strong>
                    			<td colspan="5" class="formhead"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
                  		 	 </tr>
		                  	<tr>
		                        <td height="22" class="formtext">First Name<font color="red">*</font>:</td>
		                        <td height="22">
		                        <s:textfield size="30" name="firstName" onkeypress="return charactersOnly();" maxlength="30"/>
		                       </td>
		                        <td height="22" class="formtext" valign="top">Photograph :</td>
					             <td height="22" rowspan="6"><table width="5" height="100" border="0" cellpadding="0"
								cellspacing="0" class="border">
								 <tr>
									<td bgcolor="#FFFFFF" align="center" style="padding: 3px;">
									
			

									<%
									String str = (String) request.getAttribute("photo");
										
									%>
				
									
								<%if(str.equals("") || str.equals("null") || str.equals(" "))   { %>
										<img src="../pages/images/employee/NoImage.jpg" height="150"  width="150"
											align="middle" />


										<% } else  { %>

										<img src="../pages/images/<%=session.getAttribute("session_pool") %>/databankphoto/<%=str %>"
											height="150" width="150"/>
										<%} %>
										
									
									
								
									


												
									 </td>
									</tr>
								</table>
					             
					        </td>
		                        
		                   </tr>
		                  	<tr>
		                   
		                        <td height="22" class="formtext">Middle Name : </td>
		                        <td height="22">
		                        <s:textfield size="30" name="middleName" onkeypress="return charactersOnly();" maxlength="30"/>
		                        </td>
                     	 	</tr>
                     	 	<tr>
		                   
		                        <td height="22" class="formtext">Last Name<font color="red">*</font> : </td>
		                        <td height="22">
		                        <s:textfield size="30" name="lastName" onkeypress="return charactersOnly();" maxlength="30"/>
		                        </td>
                     	 	</tr>
                     	 	<tr>
                     	 		<td width="20%" height="22" class="formtext">Date Of Birth<font color="red">*</font> :</td>
                    			<td  height="22" id="dob">
                    			<label>
                      			<s:textfield name="dob" theme="simple" size="26" maxlength="10" 
                      			 onkeypress="return numbersWithHiphen();" />
                   				<s:a  href="javascript:NewCal('paraFrm_dob','DDMMYYYY');">
                      			<img src="../pages/common/css/default/images/Date.gif" width="16" height="16" border="0"/></s:a></label>
                    			</td>
                      		</tr>
                      		<tr>
                      			
								<td height="22" class="formtext">Gender<font color="red">*</font> :</td>
								<td height="22">
                   				 <s:select name="gender" 
									list="#{'':'Select','M':'Male','F':'Female','O':'Other'}" /></td>
                      		</tr>
                      		 <tr>
				                <td height="22" class="formtext">Marital Status<font color="red">*</font> :</td>
				                <td height="22"><s:select name="maritalStatus"  cssStyle="width:62"   list="#{'':'Select','S':'Single','M':'Married','D':'Divorced'}"  /></td>
				                 
               				 </tr>
               				 <tr>
				                <td height="22" class="formtext">Experience<font color="red">*</font> :</td>
				                <td height="22"><s:textfield name="minExp"  size="2" maxlength="2" onkeypress="return numbersOnly();"/>&nbsp; Year <s:textfield name="maxExp"  size="2" maxlength="2" onkeypress="return numbersOnly();"/> &nbsp; Months</td>
				                 <td height="22" class="formtext">Upload Photo :</td>
				                <td height="22"><s:textfield name="uploadPhoto" size="18"
								maxlength="50" readonly="true" /><span id="upload"> <input name="Browse"
								type="button" class="token" value="Upload"
								onclick="uploadPhotograph('paraFrm_uploadPhoto');" /></span></td> 
               				 </tr>
               				  <tr><!--
                 				 <strong class="formhead">Address Details :</strong>
                    			--><td colspan="5" class="formhead"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
                  		 	 </tr>
               				 <tr>
				                <td height="22" class="formtext"  colspan="2">Permanent Address Same as Current Address  : &nbsp;
				                <input type="checkbox" name="addCheck" id="addCheck" onclick="setAddress();"/></td>
				                <td height="22"></td>
				                 
               				 </tr>
               				
               				 <tr>
	               				 	<td height="22" class="formtext" valign="top" id="current address">Current Address<font color="red">*</font> :</td>
					                <td height="22" nowrap="nowrap"> <s:textarea  rows="4" cols="32" name="address"  />
					               <img src="../pages/images/zoomin.gif" height="12" align="absmiddle" width="12" theme="simple"
																
										onclick="javascript:callWindow('paraFrm_address','current address','','500','500');">
										
					                </td>
					                
					                <td height="22" class="formtext" valign="top" id="Perm">Permanent Address :</td>
					                <td height="22" nowrap="nowrap"> <s:textarea  rows="4" cols="32" name="address1"  />
					                 <img src="../pages/images/zoomin.gif" height="12" align="absmiddle" width="12" theme="simple"
										onclick="javascript:callWindow('paraFrm_address1','Perm','','500','500');" >
					                </td>
					                 
	               				 </tr>
	               				
	               				 <tr>
	               					 <td height="22" class="formtext">City<font color="red">*</font> :</td>
					                <td height="22"> <s:textfield size="26" name="city" readonly="true" maxlength="30"/>
						            <img src="../pages/images/recruitment/search2.gif" width="16" height="15" class="iconImage"
										onclick="javascript:callsF9(500,325,'CandidateProfile_f9cityaction1.action');">
										 <s:hidden name="cityCode"/>
						                <s:hidden name="stateCode"/>
						                <s:hidden name="countryCode"/>
									</td>
									
									 <td height="22" class="formtext">City :</td>
					                <td height="22"> <s:textfield size="26" name="city1" readonly="true" maxlength="30"/>
					          
						                <img src="../pages/images/recruitment/search2.gif" width="16"
											height="15" class="iconImage"
										onclick="javascript:callsF9(500,325,'CandidateProfile_f9cityaction2.action');">
									</td>
									
								</tr>
								<tr>
									<td height="22" class="formtext">State :</td>
					                <td height="22"> <s:textfield size="30" name="state" readonly ="true" maxlength="30"/>
						            </td>
						            
						            <td height="22" class="formtext">State :</td>
					                <td height="22"> <s:textfield size="30" name="state1" readonly ="true" maxlength="30"/>
						            </td>
	               				 </tr>
	               				  <tr>
	               					 <td height="22" class="formtext">Country :</td>
					                <td height="22"> <s:textfield size="30" name="country" readonly="true" maxlength="30"/>
					                </td>
					                 <td height="22" class="formtext">Country :</td>
					                <td height="22"> <s:textfield size="30" name="country1" readonly="true" maxlength="30"/>
					                
					              </td>
						          <tr>    
									
									<td height="22" class="formtext">Pincode :</td>
					                <td height="22"> <s:textfield size="30" name="pincode" readonly ="false" maxlength="15" onkeypress="return numbersOnly();"/></td>
						              
						              
									<td height="22" class="formtext">Pincode :</td>
					                <td height="22"> <s:textfield size="30" name="pincode1" readonly = "false" maxlength="15" onkeypress="return numbersOnly();"/>
						              
									</td>
	               			</tr>
	               				
               				 <tr> 
               					 <td height="22" class="formtext">Residence Phone  :</td>
				                <td height="22"> <s:textfield size="30" name="resPhone"  maxlength="25" onkeypress="return validatePhoneNo();" />
					              
								</td>
								<td height="22" class="formtext">Mobile <font color="red">*</font>:</td>
				                <td height="22"> <s:textfield size="30" name="mobile"  maxlength="25" onkeypress="return validateMobile();"/>
					              
								</td>
               				 </tr>
               				  <tr>
               					 <td height="22" class="formtext">Office Phone :</td>
				                <td height="22"> <s:textfield size="30" name="offPhone"  maxlength="25" onkeypress="return validatePhoneNo();" />
					              
								</td>
								<td height="22" class="formtext">Email Id<font color="red">*</font> :</td>
				                <td height="22"> <s:textfield size="30" name="email"  maxlength="50"  onblur="return validateEmail('paraFrm_email');"   />
					              
								</td>
               				 </tr>
               				 
               				  <tr>
               					 <td height="22" class="formtext">Current CTC in Lacs<font color="red">*</font> :</td>
				                <td height="22"><s:textfield size="30" name="currentCtc"  maxlength="6"  onkeypress="return numbersWithDot();" />
					              
								</td>
								<td height="22" class="formtext" nowrap="nowrap">Expected CTC in Lacs :</td>
				                <td height="22"><s:textfield size="30" name="expectedCtc"  maxlength="6"  onkeypress="return numbersWithDot();"/>
					              
								</td>
               				 </tr>
               				 
               				  <tr>
               					 <td height="22" class="formtext">Passport No. :</td>
				                <td height="22"> <s:textfield size="30" name="passport"  maxlength="25" onkeypress="return alphaNumeric();" />
					              
								</td>
								<td height="22" class="formtext">Pan No.:</td>
				                <td height="22"> <s:textfield size="30" name="panNo"  maxlength="50"  onkeypress="return alphaNumeric();"   />
					              
								</td>
               				 </tr>
               				
               				  <tr>
	               				 	<td height="22" class="formtext" valign="top" id="info">Other Information :</td>
					                <td height="22" nowrap="nowrap"> <s:textarea  rows="4" cols="32" name="otherInfo"  />
					                <img src="../pages/images/zoomin.gif" height="12" align="absmiddle" width="12" theme="simple"
										onclick="javascript:callWindow('paraFrm_otherInfo','info','','300','300');">
					                </td>
					                
					                <td height="22" class="formtext" valign="top"></td>
					                <td height="22"></td>
					                 
	               				 </tr>
	               				
               				  <tr>	
				         <td width="18%" height="22">Upload Resume
				            	<font color="red" size="2">*</font> : </td>
				         <td width="60%" nowrap="nowrap" height="22" colspan="3">
				         	<s:textfield name="uploadFileName" size="25" readonly="true"/>
				         	<span id="upload"><input type="button" class="token" theme="simple" value="Upload Resume" 
				         		onclick="uploadFile('paraFrm_uploadFileName');"/></span></td>
				      </tr>
               			  
               				<tr>
		                   
		                        <td height="22" class="formtext" width="26%">Do you know anybody currently working with the group 
 :
		                    
		                        </td><td>
		                        <s:hidden name="radioFlag" />
							 	<s:if test="radioFlag">
								 <s:radio  name="doYou"  value="%{'Y'}"   list="#{'Y':'Yes','N':'No'}"  onclick="showEmpId();" ></s:radio> 
								</s:if>
								<s:else>
								<s:radio  name="doYou"  value="%{'N'}"   list="#{'Y':'Yes','N':'No'}"  onclick="showEmpId();"></s:radio>
								</s:else>  
		                       </td>
                     	 	</tr>
                     	 	
                     	 	 <tr id="refEmp">    
									
									<td height="22" class="formtext" width="26%" >Employee Details<font color="red">*</font> :</td>
					          <td>    
				               <s:textarea name="refEmpName" cols="32" rows="4" />
				            <img src="../pages/images/zoomin.gif" height="12" align="absmiddle" width="12" theme="simple"
										onclick="callWindowToOpen('paraFrm_refEmpName','employee details','','100','100');" ></td>
					
	               			</tr>
                     	 	
                     	 	
                     	 	
                     	 	
                     	 	
                     	 	
                     	 	<tr>
		                   
		                        <td height="22" class="formtext" width="26%">Whether you employed earlier in the &nbsp;&nbsp;group : </td>
		                     
		                        <td>
		                         <s:hidden name="radioFlag1" />
							 	<s:if test="radioFlag1">
								 <s:radio  name="wheYouEmp"  value="%{'Y'}"   list="#{'Y':'Yes','N':'No'}" ></s:radio> 
								</s:if>
								<s:else>
								<s:radio  name="wheYouEmp"  value="%{'N'}"   list="#{'Y':'Yes','N':'No'}" ></s:radio>
								</s:else>  
		                       </td>
                     	 	</tr>
                     	 	 <tr>
		                   
		                        <td height="22" class="formtext" width="26%" >Relocation : 
		                    
		                        </td><td>
		                        <s:hidden name="radioFlag2" />
							 	<s:if test="radioFlag2">
								 <s:radio  name="relocate"  value="%{'Y'}"   list="#{'Y':'Yes','N':'No'}" ></s:radio> 
								</s:if>
								<s:else>
								<s:radio  name="relocate"  value="%{'N'}"   list="#{'Y':'Yes','N':'No'}" ></s:radio>
								</s:else>  
		                        
		                      
		                        </td>
                     	 	</tr>
                	 </table>
                	  
         		 </td>
       	  	</tr>
       </table><!--
         <tr>
          <td height="5" colspan="3"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
        </tr>
       --><tr>
          <td colspan="3">
          		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
    				
           					<tr>
            					<td class="formtext" >
            						<table width="100%" border="0" cellpadding="0" cellspacing="0">
                						<tr >
                  							<td >
                  								<table width="100%" border="0" cellpadding="0" cellspacing="0">
                      				 			  <tr> <td>
                      				 			  	<strong class="text_head">Reference Details : 
                      				 			  	</strong>
                      				 			  	</td>
                      				 			  	<td align="right">
						                     			 <input type="button" class="add" value="  Add Row" onclick="addRowToRef();" />
						                      			<input type="button" class="delete" value="  Delete"  onclick="chkDeleteVacancy();"/>
								 				
							 						</td>
							 					</tr>
								 				<tr>	
		                       					 <td colspan="5">
		                       					 	<table width="100%" id="tblRef" class="sortable">
		                          					 <tr>
														<td width="10%" valign="top" class="formth" nowrap="nowrap">Sr.No</td>
														<td width="15%" valign="top" class="formth" align="center">Reference Name</td>
														<td width="20%" valign="top" class="formth" align="center">Profession</label></td>
														<td width="15%" valign="top" class="formth" align="center" id="cont">Contact Details</td>
														<td width="5%" valign="top" class="formth" align="center">&nbsp;</td>
														<td width="25%" valign="top" class="formth" align="center" id="comm">Comments</td>
														<td width="5%" valign="top" class="formth" align="center">&nbsp;</td>
														<td width="10%" valign="top" class="formth" abbr="right" align="left"><input type="checkbox" name="chkVacAll" id="chkVacAll"  onclick="return callVacAll();"/></td>
														
														<td></td>
													</tr>
													  <% int i = 0 ; %>
		                     						 <s:iterator value="refList" >
						                               <tr>
						                                   <td width="10%" class="sortableTD" align="center"><%=++i%><!--
						                                   
						                                   <s:property value="srNo" />
						                                   
						                                   --></td>
						                                   <td width="15%" class="sortableTD" align="left">
						                                  
						                                  		 <s:textfield  name="refName" id="<%="refName"+i%>" size="25" maxlength="50"/>
						                                   </td>
							    			 				<td width="15%" class="sortableTD" align="left">
							    		                   <s:textfield name="profession" id="<%="profession"+i%>" size="20" maxlength="50"/>
							    			 				</td>
										 				<td width="15%" class="sortableTD" align="center" nowrap="nowrap">
							    			 					<s:textarea rows="1" cols="20" name="contactDet" id="<%="contactDet"+i%>"  />
							    			 					
							    			 				</td>
							    			 				<td width="5%" class="sortableTD"><img src="../pages/images/zoomin.gif" height="12" align="absmiddle" width="12" theme="simple"
									          				onclick="javascript:callWindow('<%="contactDet"+i%>','cont','','500','500');" > </td>
							    			 				
							    			 				<td width="15%" class="sortableTD" align=center nowrap="nowrap">
							    			 					<s:textarea rows="1" cols="20" name="refComment" id="<%="refComment" +i%>" />
							    			 				
							    			 				
							    			 				</td>
							    			 				<td width="5%" class="sortableTD"><img src="../pages/images/zoomin.gif" height="12" align="absmiddle" width="12" theme="simple"
									          				onclick="javascript:callWindow('<%="refComment"+i%>','comm','','500','500');" > </td>
							    			 				<td width="10%" align="left" class="sortableTD"><input type="checkbox" class="checkbox" value="N" name="confChkVac" id="confChkVac<%=i%>" onclick="callForVac('<%=i%>')" /></td>
						                    				<td width="0%"  ><input type="hidden" name="hdeleteVac<%=i%>"	id="hdeleteVac<%=i%>" /></td>
						                 					<td width="0%"><input type="hidden" name="refDtlCode"	id="refDtlCode" /></td>
						                                </tr>
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
             </table>
       </td>
      </tr>
  
       <tr>
          <td colspan="3" valign="bottom" class="txt"><!--
          
          <img src="../pages/common/css/default/images/space.gif" width="5" height="5" />
          
          
          --></td>
        </tr>
     <tr>
	              		<td width="60%">
	              		 <input type="button" value="Save" class="save" onclick="return saveFun();">
	              		 <input type="button" value="Save and Next" class="saveandnext" onclick="return saveAndNext();">
	             		</td><td width="40%" align="right">
		
						<strong>Page 1 of 2</strong></td>
	              		
     </tr>
    
     </table>
	
	</s:form>
	
   <script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
  <script>
  
   
  
  
//if(!(document.getElementById("paraFrm_listFlag").value=="true")){
 showEmpId();
 //}
 
 
  function uploadFile(fieldName) {
		var path = document.getElementById("paraFrm_dataPath").value;
		window.open('<%=request.getContextPath()%>/pages/recruitment/uploadResume.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
	}

 function uploadPhotograph(fieldName) {

	//	var path = document.getElementById("paraFrm_pathPhoto").value;
		var path="images/<%=session.getAttribute("session_pool")%>/databankphoto";
		
	
		window.open('../pages/common/multipleUploadFile.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
	}

	function addRowToRef()
	{
		  var tbl = document.getElementById('tblRef');
		  var lastRow = tbl.rows.length;
		 
		  
		  // if there's no header row in the table, then iteration = lastRow + 1
		  var iteration = lastRow;
		  var row = tbl.insertRow(lastRow);
		
		 
		  // left cell
		  var cellLeft = row.insertCell(0);
		  var textNode = document.createTextNode(iteration);
		  cellLeft.className='sortableTD';
		  cellLeft.align='center';
		  cellLeft.appendChild(textNode);
	  
   		  var cellRefName = row.insertCell(1);
      	  var refName = document.createElement('input');
      	  cellRefName.className='sortableTD';
		  refName.type = 'text';
		  refName.name = 'refName';
		  refName.id = 'refName'+iteration;
		  refName.size='25';
		  refName.maxLength='50';
		  cellRefName.align='center';
		  cellRefName.appendChild(refName);
  
		  var cellProfession = row.insertCell(2);
		  var profession = document.createElement('input');
		  cellProfession.className='sortableTD';
  		  profession.type = 'text';
		  profession.name = 'profession';
		  profession.id = 'profession'+iteration;
		  profession.size='20';
		  profession.maxLength='50';
		  cellProfession.align='center';
		  cellProfession.appendChild(profession);
		  
		  var cellContact = row.insertCell(3);
		  var contact = document.createElement('textarea');
		  cellContact.className='sortableTD';
  		   contact.name = 'contactDet';
		  contact.id = 'contactDet'+iteration;
		  contact.rows ='1';
		  contact.cols ='20';
		  cellContact.align='center';
		  cellContact.appendChild(contact);
		  
		    var cellContactImg = row.insertCell(4);
		    cellContactImg.className='sortableTD';
			var img=  document.createElement('img');
			img.type='image';
			img.src="../pages/images/zoomin.gif";
	 		img.height='12';
	 		img.align='absmiddle';
 			img.width='12';
 			img.id='img'+ iteration;
			img.theme='simple';
 			img.onclick=function(){
 			readFlag='';
 			fieldName='contactDet'+iteration;
 			windowName='cont';
 			mytitle=document.getElementById(windowName).innerHTML.toLowerCase();
 			charCount='500';
 			maxLength='500';
 			mytitle=document.getElementById(windowName).innerHTML.toLowerCase();
 			window.open('../pages/common/popupTextArea.jsp?fieldName='+fieldName+'&windowName='+mytitle+'&charCntName='+charCount+'&maxLength='+maxLength+'&readFlag='+readFlag+'','','width=580,height=430,scrollbars=no,resizable=no,top=250,left=350');
 			 
			//window.open('../pages/common/popupTextArea.jsp?fieldName='+fieldName+'&windowName='+mytitle+'&readFlag='+readFlag+'','','width=500,height=430,scrollbars=no,resizable=no,top=250,left=350');
			document.getElementById('paraFrm').target ="main";
 		
	
	     	
	     
	     	
};
 			cellContactImg.appendChild(img);
		  
		  
		  
		  
		  var cellComm = row.insertCell(5);
		  cellComm.className='sortableTD';
		  var comm = document.createElement('textarea');
  		  comm.name = 'refComment';
		  comm.id = 'refComment'+iteration;
		  comm.rows = '1';
		  comm.cols ='20';
		  cellComm.align='center';
		  cellComm.appendChild(comm);
		  
		  
		     var cellCommentImg = row.insertCell(6);
		    cellCommentImg.className='sortableTD';
			var commImg=  document.createElement('img');
			commImg.type='image';
			commImg.src='../pages/images/zoomin.gif';
	 		commImg.height='12';
	 		commImg.align='absmiddle';
 			commImg.width='12';
 			commImg.id='img'+ iteration;
			commImg.theme='simple';
 			commImg.onclick=function(){
 			readFlag='';
 			fieldName='refComment'+iteration;
 			windowName='comm';
 			// mytitle=document.getElementById(windowName).innerHTML.toLowerCase();
			//window.open('../pages/common/popupTextArea.jsp?fieldName='+fieldName+'&windowName='+mytitle+'&readFlag='+readFlag+'','','width=500,height=430,scrollbars=no,resizable=no,top=250,left=350');
			charCount='500';
 			maxLength='500';
 			 mytitle=document.getElementById(windowName).innerHTML.toLowerCase();
 			 window.open('../pages/common/popupTextArea.jsp?fieldName='+fieldName+'&windowName='+mytitle+'&charCntName='+charCount+'&maxLength='+maxLength+'&readFlag='+readFlag+'','','width=580,height=430,scrollbars=no,resizable=no,top=250,left=350');
		
			document.getElementById('paraFrm').target ="main";
 		
	
	     	
	     
	     	
};
 			cellCommentImg.appendChild(commImg);
		  
		  
		  
 
		  
		  var cellrefChk = row.insertCell(7);
		  var refChk = document.createElement('input');
		  cellrefChk.className='sortableTD';
		  refChk.type = 'checkbox';
		  refChk.name = 'confChkVac';
		  refChk.id = 'confChkVac'+iteration;
		  refChk.onclick=function(){
		 	  if(document.getElementById('confChkVac'+iteration).checked == true)
			   {	
			    	document.getElementById('hdeleteVac'+iteration).value="Y";
			   }  else{
			  	    document.getElementById('hdeleteVac'+iteration).value="";
			   	
		   		}
			};
 
		  cellrefChk.align='left';
		  cellrefChk.appendChild(refChk);
		  
		  
		  var hiddenDel=row.insertCell(8);
		  var hid = document.createElement('input');
		  hid.type = 'hidden';
		  hid.name = 'hdeleteVac'+iteration;
		  hid.id = 'hdeleteVac' +iteration;
		  hiddenDel.appendChild(hid);//refDtlCode
		  
		  var refDetCode=row.insertCell(9);
		  var detCode = document.createElement('input');
		  detCode.type = 'hidden';
		  detCode.name = 'refDtlCode';
		  detCode.id = 'refDtlCode' ;
		  refDetCode.appendChild(detCode);
		 
	}  

	function deleteFun(){
	var conf=confirm("Do you really want to delete this record ?");
	  if(conf){
		document.getElementById("paraFrm").action="CandDataBank_deleteRec.action";
	    document.getElementById("paraFrm").submit();
	
	  }
	
	}

	function addnewFun(){
		document.getElementById("paraFrm").action="CandDataBank_addNew.action";
	    document.getElementById("paraFrm").submit();
	}
	
	function chkSize(){
	
				  var tbl = document.getElementById('tblRef');
		  		  var lastRow = tbl.rows.length;
		  		  
		  		  
		  		  for(var idx=1;idx<lastRow;idx++){
		  		  var comment=trim(document.getElementById('refComment'+idx).value);
		  		  var contact=trim(document.getElementById('contactDet'+idx).value);
		  		  var prof=trim(document.getElementById('profession'+idx).value);
		  		  var ref=trim(document.getElementById('refName'+idx).value);
		  		
		  		  if(prof!=""){
		  		  
		  		  		if(ref==""){
		  		  			alert("Please enter the reference name in Reference Details.")
		  		  		     document.getElementById('refComment'+idx).focus();
		  		  			return false;	
		  		  		}
		  		  
		  		  }
		  		  
		  		 if(contact!=""){
		  		  
		  		  		if(ref==""){
		  		  			alert("Please enter the reference name in Reference Details.");
		  		  			document.getElementById('refComment'+idx).focus();
		  		  			return false;	
		  		  		}
		  		  
		  		  }
		  		  
		  		  
		  		   if(comment!=""){
		  		  
		  		  		if(ref==""){
		  		  			alert("Please enter the reference name in Reference Details.");
		  		  			 document.getElementById('refComment'+idx).focus();
		  		  			
		  		  			return false;	
		  		  		}
		  		  
		  		  }
		  		 
		  		 
		  		  if(comment.length >500){
		  		  alert("Maximum length of comments is 500 characters.");
		  		  return false;
		  		  }else if(contact.length >500){
		  		  alert("Maximum length of contact details is 500 characters.");
		  		  return false;
		  		  
		  		  
		  		  }
		  		  
		  		  
		  		  }
	
	return true;
	}
	
	function saveFun(){
	
	 
	var fName=trim(document.getElementById('paraFrm_firstName').value);		
	var lName=trim(document.getElementById('paraFrm_lastName').value);
	var dateOfB=trim(document.getElementById('paraFrm_dob').value);		
	var gen=document.getElementById('paraFrm_gender').value;
	var mart=document.getElementById('paraFrm_maritalStatus').value;
	var exp=trim(document.getElementById('paraFrm_minExp').value);
	var addrs=trim(document.getElementById('paraFrm_address').value);
	var addrs1=trim(document.getElementById('paraFrm_address1').value);
	var citi=document.getElementById('paraFrm_city').value;
	var residence=trim(document.getElementById('paraFrm_resPhone').value);
	var em=trim(document.getElementById('paraFrm_email').value);
	var fileName=trim(document.getElementById('paraFrm_uploadFileName').value);
	var mon=document.getElementById("paraFrm_maxExp").value;
	var emp=trim(document.getElementById("paraFrm_refEmpName").value);
	var photo=document.getElementById("paraFrm_uploadPhoto").value;
	var othInf=document.getElementById("paraFrm_otherInfo").value;
	var mob=trim(document.getElementById("paraFrm_mobile").value);
	
	if(fName==""){
	
		alert("Please enter the first name");
		return false;
	}
	if(lName==""){
		alert("Please enter the last name");
		return false;
	}
	if(dateOfB==""){
		alert("Please enter/select date of birth");	
		return false;
	}	
	
	if(!(validDate('paraFrm_dob','Date of Birth'))){
		//alert("Date of birth is not a valid date.");
	    return false;
	
	}
	
	
	if(gen==""){
		alert("Please select the gender");
	    return false;
	
	}
	
	if(mart==""){
		alert("Please select the marital status");
	    return false;
	
	}
	if(exp==""){
		alert("Please enter the experience");
	    return false;
	
	}	
	 if(eval(mon) >11){
			alert("Please enter month less than 12");	   	     
   	     	return false;
   	     }
	
	if(addrs==""){
		alert("Please enter the current address");
	    return false;
	
	}	
	
	
	   if(addrs.length > 500){
	   	          alert("Maximum length of current address is 500 characters.");
	   	          return false;
	  }
   if(addrs1.length > 500){
   	       	  alert("Maximum length of permanent address is 500 characters.");
   	          return false;
     }
    if(photo.length >50){
    		 alert("Maximum length of upload photo is 500 characters.");
   	          return false;
    
    } 
     
	if(citi==""){
		alert("Please select the city");
	    return false;
	
	}	
	
	var currentCtc =document.getElementById('paraFrm_currentCtc').value;
	if(currentCtc==""){
		alert("Please enter Current CTC in Lacs");
		document.getElementById('paraFrm_currentCtc').focus();
	    return false;
	
	}	
	
	
	if(mob==""){
		alert("Please enter the mobile no.");
	    return false;
  	}
  	
  	
  	if(em==""){
  		alert("Please enter the email id");
  		return false;
  	
  	}
  if(!validateEmail('paraFrm_email')){
    	return false;
  	  }
  	  
  if(othInf.length > 300){
   	       	  alert("Maximum length of other information is 300 characters.");
   	          return false;
     }   
  	  
  	 
  	if(fileName==""){
  		alert("Please upload the resume.");
  		return false;
  	
  	}  
  	if(fileName.length>50){
  		alert("Maximum length of upload resume is 50 characters.");
  		return false;
  	
  	}  
  	
  	  if(!valCTC('paraFrm_currentCtc','Current CTC in Lacs'))
		return false;

		if(!valCTC('paraFrm_expectedCtc','Expected CTC in Lacs'))
		return false;
  	
  	
  	if(document.getElementById('paraFrm_radioEmp').value=='Y'){
  			if(emp==""){
  				alert("Please enter the employee details");
  				return false;
  			}
  	
  			if(eval(emp.length) >100){
			      alert("Maximum length of employee details is 100 characters");
			      return false;
			  }
  	
  	
     }
     
     
     if(!chkSize()){
     	   	 return false;
   	     }
  	
	  	document.getElementById("paraFrm").action="CandidateProfile_saveFirst.action";
		document.getElementById("paraFrm").submit();
  	
  	  
  	}
	
	
	function saveAndNext(){
	
	var fName=trim(document.getElementById('paraFrm_firstName').value);		
	var lName=trim(document.getElementById('paraFrm_lastName').value);
	var dateOfB=trim(document.getElementById('paraFrm_dob').value);		
	var gen=document.getElementById('paraFrm_gender').value;
	var mart=document.getElementById('paraFrm_maritalStatus').value;
	var exp=trim(document.getElementById('paraFrm_minExp').value);
	var addrs=trim(document.getElementById('paraFrm_address').value);
	var addrs1=trim(document.getElementById('paraFrm_address1').value);
	var citi=document.getElementById('paraFrm_city').value;
	var residence=trim(document.getElementById('paraFrm_resPhone').value);
	var em=trim(document.getElementById('paraFrm_email').value);
	var fileName=trim(document.getElementById('paraFrm_uploadFileName').value);
	var mon=document.getElementById("paraFrm_maxExp").value;
	var emp=trim(document.getElementById("paraFrm_refEmpName").value);
	var photo=document.getElementById("paraFrm_uploadPhoto").value;
	var othInf=document.getElementById("paraFrm_otherInfo").value;
	var mob=document.getElementById("paraFrm_mobile").value;
	
	if(fName==""){
	
		alert("Please enter the first name");
		return false;
	}
	if(lName==""){
		alert("Please enter the last name");
		return false;
	}
	if(dateOfB==""){
		alert("Please enter/select date of birth");	
		return false;
	}	
	
	if(!(validDate('paraFrm_dob','Date of Birth'))){
	//	alert("Date of birth is not a valid date.");
	    return false;
	
	}
	
	
	if(gen==""){
		alert("Please select the gender");
	    return false;
	
	}
	
	if(mart==""){
		alert("Please select the marital status");
	    return false;
	
	}
	if(exp==""){
		alert("Please enter the experience");
	    return false;
	
	}	
	 if(eval(mon) >11){
			alert("Please enter month less than 12");	   	     
   	     	return false;
   	     }
	
	if(addrs==""){
		alert("Please enter the current address");
	    return false;
	
	}	
	
	
	   if(addrs.length > 500){
	   	          alert("Maximum length of current address is 500 characters.");
	   	          return false;
	  }
   if(addrs1.length > 500){
   	       	  alert("Maximum length of permanent address is 500 characters.");
   	          return false;
     }
    if(photo.length >50){
    		 alert("Maximum length of upload photo is 500 characters.");
   	          return false;
    
    } 
     
	if(citi==""){
		alert("Please select the city");
	    return false;
	
	}	
	
	var currentCtc =document.getElementById('paraFrm_currentCtc').value;
	if(currentCtc==""){
		alert("Please enter Current CTC in Lacs");
		document.getElementById('paraFrm_currentCtc').focus();
	    return false;
	
	}	
	
	if(mob==""){
		alert("Please enter the mobile no.");
	    return false;
  	}
  	
  	
  	if(em==""){
  		alert("Please enter the email id");
  		return false;
  	
  	}
  if(!validateEmail('paraFrm_email')){
    	return false;
  	  }
  	  
  	 if(othInf.length > 300){
   	       	  alert("Maximum length of othrt information is 300 characters.");
   	          return false;
     }     
  	  
  	  
  	if(fileName==""){
  	
  		alert("Please upload the resume.");
  		return false;
  	
  	}  
  	if(fileName.length>50){
  		alert("Maximum length of upload resume is 50 characters.");
  		return false;
  	
  	}  
  	
  	  if(!valCTC('paraFrm_currentCtc','Current CTC in Lacs'))
		return false;

		if(!valCTC('paraFrm_expectedCtc','Expected CTC in Lacs'))
		return false;
  	
  	
  	if(document.getElementById('paraFrm_radioEmp').value=='Y'){
  			if(emp==""){
  				alert("Please enter the employee details");
  				return false;
  			}
  	
  			if(eval(emp.length) >100){
			      alert("Maximum length of employee details is 100 characters");
			      return false;
			  }
  	
  	
     }
     
     
     if(!chkSize()){
     	   	 return false;
   	     }
  	
	  	document.getElementById("paraFrm").action="CandidateProfile_nextPageEdit.action";
		document.getElementById("paraFrm").submit();
  	
  	  
  	}

   function setAddress(){
   	var addCheck =document.getElementById('addCheck').value;
   	var cityName1=document.getElementById('paraFrm_city').value;
   	var code1=document.getElementById('paraFrm_cityCode').value;
   	var stateName1=document.getElementById('paraFrm_state').value;
   	var stCode1=document.getElementById('paraFrm_stateCode').value;
   	var countryName1=document.getElementById('paraFrm_country').value;
    var counCodeName=document.getElementById('paraFrm_countryCode').value;
    var oldPin=document.getElementById('paraFrm_pincode').value;
    var oldAddr=document.getElementById('paraFrm_address').value;
   	
   	if(document.getElementById('addCheck').checked){
   			document.getElementById('paraFrm_city1').value=cityName1;
   			document.getElementById('paraFrm_cityCode1').value=code1;
   			document.getElementById('paraFrm_state1').value=stateName1;
   			document.getElementById('paraFrm_stateCode1').value=stCode1;
   			document.getElementById('paraFrm_country1').value=countryName1;
   			document.getElementById('paraFrm_countryCode1').value=counCodeName;
   			document.getElementById('paraFrm_pincode1').value=oldPin;
   			document.getElementById('paraFrm_address1').value=oldAddr;
   	   }else {
   	        document.getElementById('paraFrm_city1').value="";
   			document.getElementById('paraFrm_cityCode1').value="";
   			document.getElementById('paraFrm_state1').value="";
   			document.getElementById('paraFrm_stateCode1').value="";
   			document.getElementById('paraFrm_country1').value="";
   			document.getElementById('paraFrm_countryCode1').value="";
   			document.getElementById('paraFrm_pincode1').value="";
   			document.getElementById('paraFrm_address1').value="";
   	   
   	   }
   }
   
   
   
  // function showAddDiv(){
  // 		document.getElementById('address').style.display='';
 //  }
   
   
   function callVacAll()
  {
	 	
		var tbl = document.getElementById('tblRef');
		var rowLen = tbl.rows.length;
	if (document.getElementById('chkVacAll').checked == true){
	
	for (var idx = 1; idx < rowLen; idx++) {
	
				document.getElementById('confChkVac'+idx).checked = true;
				 document.getElementById('hdeleteVac'+idx).value="Y";
				
	    }

 }else{
 
	for (var idx = 1; idx < rowLen; idx++) {
	
	document.getElementById('confChkVac'+idx).checked = false;
	document.getElementById('hdeleteVac'+idx).value="";
     }
  }	 
		
	
  }
  
  
  function callForVac(id)
	   {
	 	  
	 
	   if(document.getElementById('confChkVac'+id).checked == true)
	   {	  
	    document.getElementById('hdeleteVac'+id).value="Y";
	   }
	   else{
	   document.getElementById('hdeleteVac'+id).value="";
	   	
   		}
  }
  
  function chkDeleteVacancy()
	{
	
		var tbl = document.getElementById('tblRef');
		var rowLen = tbl.rows.length;
	 if(chkVac()){
	 var con=confirm('Do you really want to delete this record ?');
	 if(con){
	   document.getElementById('paraFrm').action="CandidateProfile_deleteRowRef.action";
	    document.getElementById('paraFrm').submit();
	    } else{
	    		for (var idx = 1; idx < rowLen; idx++) {
	
				document.getElementById('confChkVac'+idx).checked = false;
				document.getElementById('hdeleteVac'+idx).value="";
				document.getElementById('chkVacAll').checked=false;
     			}
	    
	    
	    }
	 }else {
	 	alert('Please select a record to delete');
	 	 return false;
	 }
	 
	}
	
	
	
	function chkVac(){
	var tbl = document.getElementById('tblRef');
var rowLen = tbl.rows.length;

	
	  for(var a=1;a<rowLen;a++){	
	   if(document.getElementById('confChkVac'+a).checked == true)
	   {	
	 	    return true;
	   }	   
	  }
	  return false;
	}
	
/*
following functions are used in paging 

*/	
	
/*function callPage(id){
	   	document.getElementById('paraFrm_myPage').value=id;
		document.getElementById('paraFrm_show').value=id;
	    document.getElementById('paraFrm').action="CandDataBank_callPage.action";
	    document.getElementById('paraFrm').submit();
   }	*/
 
  
   
   

 function showEmpId(){
 
  
   if(document.getElementById('paraFrm_doYouY').checked){
     document.getElementById('paraFrm_radioEmp').value="Y";
  		document.getElementById('refEmp').style.display='';
     }
     
     if(document.getElementById('paraFrm_doYouN').checked){
     
     document.getElementById('paraFrm_radioEmp').value="N";
     
      		//document.getElementById('paraFrm_doYou').value="N";
      		document.getElementById('refEmp').style.display='none';
      		//document.getElementById('paraFrm_refEmpId').value="";
      		document.getElementById('paraFrm_refEmpName').value="";
      		//document.getElementById('paraFrm_refEmpTok').value="";
      		document.getElementById('paraFrm_doYouY').checked=false;
     
     }
     
  }
  
  
   
    
	
	
		
   
   
  		function valCTC(ctcfieldname,filedName)
	{
	
	var amount=document.getElementById(ctcfieldname).value;
	//var amountlabel=document.getElementById(ctclabelname).innerHTML.toLowerCase();
	
		
	if(trim(amount)!=""){
		if(isNaN(amount)) { 
		  alert("Only numbers are allowed in "+filedName+"");
		  document.getElementById(ctcfieldname).focus();
		  return false;
		 
		 }	
		 }
		  return true;
		 }
		 
		 
		 
  
  	
  	
  	
  	
  function validDate(fieldName,lableName){

	var date = document.getElementById(fieldName).value;
	if(date=='') return true;
	var dateFormat = /^[0-9]{2}[-]?[0-9]{2}[-]?[0-9]{4}$/;
	
	if(!(date.match(dateFormat)) || date.length<10){
		alert(""+lableName+" should be in DD-MM-YYYY format");
		document.getElementById(fieldName).focus();
		return false;
	}
	var dateArray = date.split("-");
	var day   = dateArray[0];
	var month = dateArray[1];
	var year  = dateArray[2];
	
	if(day<1 || day>31){
		alert("Day "+day+" is not a valid day");
		document.getElementById(fieldName).focus();
		return false;
	}
	
	if(month<1 || month>12){
		alert("Month "+month+" is not a valid month");
		document.getElementById(fieldName).focus();
		return false;
	}
	
	if(day>29 && month==2){
		window.alert("Day "+day+" of the month "+month+" is not a valid day");
		document.getElementById(fieldName).focus();
		return false;
	}
	
	if((month==2 && day==29) && ((year%4!=0) || (year%100==0 && year%400!=0))){
		window.alert("29th of February is not a valid date in "+year);
		document.getElementById(fieldName).focus();
		return false;
	}
	
	if (day>30 && (month == 2 || month==4 || month==6 || month==9 || month==11)) {
		window.alert("Day "+day+" of the month "+month+" is not a valid day");
		document.getElementById(fieldName).focus();
		return false;
	}
	return true;
} 
  	
  	
  	function callWindowToOpen(fieldName,windowName,readFlag) {
		 mytitle=windowName;
		window.open('../pages/common/popupTextArea.jsp?fieldName='+fieldName+'&windowName='+mytitle+'&readFlag='+readFlag+'','','width=500,height=430,scrollbars=no,resizable=no,top=250,left=350');
		document.getElementById('paraFrm').target ="main";	 
	}
  	
  </script>
