<!-- Added by manish sakpal -->

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="CapitalExpenditureApproval" validate="true" id="paraFrm"
	validate="true" theme="simple">

	<table width="100%" align="right" cellpadding="2" cellspacing="1"
		class="formbg">
		<tr>
			<td>
			<table width="100%" align="right" class="formbg">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="93%" class="txt"><strong class="text_head">Capital Expenditure
					Authorization Approval </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" />
					</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="20%">
					<div align="right"><span class="style2"></span> <font
						color="red">*</font>Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
			<tr>
			<td width="100%" height="22">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<s:if test="%{status=='_S'}">	
				<tr>
					<td id="ctrlShow"><label id="ppo.attachment" name="ppo.attachment"
						ondblclick="callShowDiv(this);"><%=label.get("ppo.attachment")%></label>
					:</td>
					<td width="75%" id="ctrlShow">
					<s:textfield name="ppoNumber" maxlength="100"/>
					<s:textfield name="ppoAttachement" 
								readonly="true" size="20" cssStyle="background-color: #F2F2F2;" />
								<input type="button" value="Upload File" class="token" onclick="uploadFile('ppoAttachement')" />
					</td>
				</tr>
				<tr>
						<td> </td>
						<td><a href="#" onclick="viewUploadedFile('ppoAttachement');"><font color="blue"><u>click here to view attached file </u></font></a>		 </td>
						
						</tr>
			</s:if>
				<s:if test="%{status == '_A' }">
				<tr>
					<td ><label id="ppo.attachment" name="ppo.attachment"
						ondblclick="callShowDiv(this);"><%=label.get("ppo.attachment")%></label>
					:</td>
					<td width="75%" >
					<s:textfield name="ppoNumber"/>
					<s:textfield name="ppoAttachement" onclick="uploadFile('ppoAttachement')"
								readonly="true" size="20" cssStyle="background-color: #F2F2F2;" />
								
					</td>
				</tr>
				<tr>
						<td> </td>
						<td><a href="#" onclick="viewUploadedFile('ppoAttachement');"><font color="blue"><u>click here to view attached file</u></font></a>		 </td>
						
						</tr>
				</s:if>
						
			</table>
			</td>
			</tr>
<!-- Approver Comments Section Begins -->
		<tr>
			<td>
				<table width="100%" class="formbg">
					<tr>
						<td colspan="2"><b>Approver Comments : </b></td>
						<s:if test="approverCommentsFlag">
							<td colspan="2" id="ctrlShow">
								<s:textarea name="approverComments" rows="4" cols="80"  onkeypress="return imposeMaxLength(event, this, 1000);"></s:textarea>
							</td>
						</s:if>
						<s:else>
							<td colspan="3">&nbsp;</td>
						</s:else>
					</tr>
					<tr>
						<td width="10%" class="formth">Sr. No.</td>
						<td width="25%" class="formth">Approver Name</td>
						<td width="40%" class="formth">Comments</td>
						<td width="15%" class="formth">Approved Date</td>
						<td width="10%" class="formth">Status</td>
					</tr>
					<%
						int approvercount = 0;
					%>
					<s:iterator value="approverCommentList">
						<tr>
							<td class="sortableTD"><%=++approvercount%></td>
							<td class="sortableTD"><s:property value="apprName" />&nbsp;</td>
							<td class="sortableTD"><s:property value="apprComments" />&nbsp;</td>
							<td class="sortableTD" align="center"><s:property value="apprDate" />&nbsp;</td>
							<td class="sortableTD"><s:property value="apprStatus" />&nbsp;</td>
						</tr>
					</s:iterator>
					<%
						if(approvercount == 0) {
					%>
					<tr>
						<td width="100%" colspan="5" align="center"><font color="red">No
						Data To Display</font></td>
					</tr>
					<%
						}
					%>
				</table>
			 </td>
		  </tr>
<!-- Approver Comments Section Ends -->
				
<!-- Next Approval Section Begins -->
		<tr>
			<td>
			  <table width="100%" align="right" class="formbg">
				<tr>
					<td colspan="4"><b>Note : </b>
							<br>Please Follow The Following Reporting Structure
						 	<br>Director >> Vice President >> Senior Vice President >> Controller >> Chief Financial Officer >> Chief Executive Officer
					</td>
				</tr>
				<tr>
					<td colspan="4" class="formtext">
						<b> Application Approvals </b>
					</td>
				</tr>
				
			
				<s:if test = "displayFlag">
				<tr>
					<td class="formtext" width="20%">
						Forward To Next Approver : 
					</td>					
					<td height="22" colspan="3" id="ctrlShow">
						<s:hidden name="forwardedApproverID"/>
						<s:textfield name="forwardedApproverToken" size="15" readonly="true" />						
						<s:textfield name="forwardedApproverName" size="60"  readonly="true" />
							<s:if test="%{hiddenForwardedType == 'CEO'}">
																	
							</s:if>
							<s:elseif test="%{status=='_P' || status=='_C' || status=='_F' }">
								<img id='ctrlShow' src="../pages/images/recruitment/search2.gif" height="16"
									align="absmiddle" width="17" theme="simple"
									onclick="javascript:callsF9(500,325,'CapitalExpenditureApproval_f9Approver.action');">
							</s:elseif>	
					</td>
					
				</tr>
				</s:if>
			
				<s:if test = "f9Flag">
				
				<tr>
				<td class="formtext" width="20%">
						Forward To Next Approver : 
					</td>	
				<td><s:property value="forwardedApproverToken"/>	<s:property value="forwardedApproverName"/>
				</td>
				
				</tr></s:if>
				
			
			<s:if test="%{status=='_P' || status=='_C' || status=='_F'}">	
				<tr>
					<td class="formtext" width="20%">
						Forwarded To : 
					</td>					
					<td colspan="3" id="ctrlShow">
						<s:if test="%{hiddenForwardedType == 'DR'}">
							<s:select cssStyle="width:150;background-color: #F2F2F2;" name="forwardedApproverType" 
							list="#{'VP':'Vice President','SVP':'Senior Vice President','CTRL':'Controller','CFO':'Chief Financial Officer','CEO':'Chief Executive Officer'}" />
						</s:if> 
						<s:elseif test="%{hiddenForwardedType == 'VP'}">
							<s:select 
								cssStyle="width:150;background-color: #F2F2F2;"
								name="forwardedApproverType" list="#{'SVP':'Senior Vice President','CTRL':'Controller','CFO':'Chief Financial Officer','CEO':'Chief Executive Officer'}" />
						</s:elseif> 
						<s:elseif test="%{hiddenForwardedType == 'SVP'}">
							<s:select 
								cssStyle="width:150;background-color: #F2F2F2;"
								name="forwardedApproverType" list="#{'CTRL':'Controller','CFO':'Chief Financial Officer','CEO':'Chief Executive Officer'}"  />
						</s:elseif>
						<s:elseif test="%{hiddenForwardedType == 'CTRL'}">
							<s:select 
								cssStyle="width:150;background-color: #F2F2F2;"
								name="forwardedApproverType" list="#{'CFO':'Chief Financial Officer','CEO':'Chief Executive Officer'}"  />
						</s:elseif>	
						<s:elseif test="%{hiddenForwardedType == 'CFO'}">
							<s:select 
								cssStyle="width:150;background-color: #F2F2F2;"
								name="forwardedApproverType" list="#{'CEO':'Chief Executive Officer'}"  />
						</s:elseif>	
						<s:elseif test="%{hiddenForwardedType == 'CEO'}">
							<s:select disabled="true"
								cssStyle="width:150;background-color: #F2F2F2;"
								name="forwardedApproverType" list="#{}"  />
						</s:elseif>	
						<s:else>
							<s:select headerKey="" headerValue="----- Select -----" cssStyle="width:150;background-color: #F2F2F2;" name="forwardedApproverType" 
							list="#{'DR':'Director','VP':'Vice President','SVP':'Senior Vice President','CTRL':'Controller','CFO':'Chief Financial Officer','CEO':'Chief Executive Officer'}" />
						</s:else>
					</td>
				</tr>
			</s:if>	
				
			<s:else>
					<tr>
					  <td class="formtext" width="20%">
						Forwarded To : 
					  </td>					
					<td colspan="3" id="ctrlShow">
						<s:if test="%{hiddenForwardedType == 'DR'}">
							<s:select disabled="true" cssStyle="width:150;background-color: #F2F2F2;" name="forwardedApproverType" 
							list="#{'VP':'Vice President','SVP':'Senior Vice President','CTRL':'Controller','CFO':'Chief Financial Officer','CEO':'Chief Executive Officer'}" />
						</s:if> 
						<s:elseif test="%{hiddenForwardedType == 'VP'}">
							<s:select disabled="true"
								cssStyle="width:150;background-color: #F2F2F2;"
								name="forwardedApproverType" list="#{'SVP':'Senior Vice President','CTRL':'Controller','CFO':'Chief Financial Officer','CEO':'Chief Executive Officer'}" />
						</s:elseif> 
						<s:elseif test="%{hiddenForwardedType == 'SVP'}">
							<s:select disabled="true"
								cssStyle="width:150;background-color: #F2F2F2;"
								name="forwardedApproverType" list="#{'CTRL':'Controller','CFO':'Chief Financial Officer','CEO':'Chief Executive Officer'}"  />
						</s:elseif>
						<s:elseif test="%{hiddenForwardedType == 'CTRL'}">
							<s:select disabled="true"
								cssStyle="width:150;background-color: #F2F2F2;"
								name="forwardedApproverType" list="#{'CFO':'Chief Financial Officer','CEO':'Chief Executive Officer'}"  />
						</s:elseif>	
						<s:elseif test="%{hiddenForwardedType == 'CFO'}">
							<s:select disabled="true"
								cssStyle="width:150;background-color: #F2F2F2;"
								name="forwardedApproverType" list="#{'CEO':'Chief Executive Officer'}"  />
						</s:elseif>	
						<s:elseif test="%{hiddenForwardedType == 'CEO'}">
							<s:select disabled="true"
								cssStyle="width:150;background-color: #F2F2F2;"
								name="forwardedApproverType" list="#{}"  />
						</s:elseif>	
						<s:else>
							<s:select disabled="true" headerKey="" headerValue="------ Select ------" cssStyle="width:150" name="forwardedApproverType"
								list="#{'DR':'Director','VP':'Vice President','SVP':'Senior Vice President','CTRL':'Controller','CFO':'Chief Financial Officer','CEO':'Chief Executive Officer'}" />
						</s:else>
					</td>
				 </tr>
			   </s:else>
		   	</table>
		  </td>
		</tr>  
<!-- Next Approval Section Ends --> 
	   		
		<tr>
			<td>
			<table width="100%" align="right" class="formbg">
				<tr>
					 <td colspan="4">
						<table width="100%" align="right" >
							<tr>
								<td colspan="4">
									<b>Instructions: </b>
										<br>1. Any User can fill form with all mandatory information.
										<br>2. When requestor submits form initially it goes to requestor's manager for approval. Manager then forward it to any person in Approval Reporting Structure. Next Reporting person either click 'Authorized Signoff' button to finally approve it then Request directly goes to Finance Department or again forward it to next available reporting person for higher approvals. 
										<br>3. For any CEAR request having Total Cost >$10000, CFO/CEO must need to approve the request.
								</td>
							</tr>
						</table>
					</td>
				  </tr>
			</table>
		 </td>
	  </tr>	 		
				
	  <tr>
		  <td>
			<table width="100%" align="right" class="formbg">
				<tr>
					 <td colspan="4">
						<table width="100%" align="right" >
							<tr>
								<td width="20%">
									<label name="trackingNumber" id="trackingNumber"
										ondblclick="callShowDiv(this);"> <%=label.get("trackingNumber")%> </label> :
								</td>								
								<td width="80%">
									<s:property value="trackingNumber"/>
								</td>
							</tr>					
						</table>
					</td>
				</tr>
			</table>
		 </td>
	 </tr>	 
	 		
	  <tr>
		  <td>
			<table width="100%" align="right" class="formbg">
				<tr>
					 <td colspan="4">
						<table width="100%" align="right" >
							<tr>
								<td width="20%">Original&nbsp;&nbsp;<s:checkbox name="originalCheckbox" /></td>								
								<td width="25%">Purchase Department&nbsp;&nbsp;<s:checkbox name="purchaseDeptCheckbox" /> </td>
								<td colspan="20%">Computer IT HW/SW&nbsp;&nbsp;<s:checkbox name="computerITCheckbox" /></td>
								<td width="25%">Local Purchase&nbsp;&nbsp;<s:checkbox name="localPurchaseCheckbox" onclick="CEARTypeCheckBox();"/></td>
							</tr>				
						</table>
					</td>
				</tr>
			</table>
		 </td>
	 </tr>	 		
	
		<tr>
			<td>
			<table width="100%" align="right" class="formbg">
				<tr>
					<td class="formtext" width="20%">
						<label name="submittedBy" id="submittedBy"
							ondblclick="callShowDiv(this);"> <%=label.get("submittedBy")%>
						</label> :
					</td>
					<td height="22" width="25%" >
						<s:property value="submittedByName" /> 
						<s:hidden name="submittedByName" />
						<s:hidden name="submittedByID" />
					</td>
					<td height="22" width="20%" >
						<label name="submittedDate" id="submittedDate"
							ondblclick="callShowDiv(this);"> <%=label.get("submittedDate")%>
						</label> :
					</td>
					<td height="22" width="25%" >
						<s:property value="submittedDate" />
						<s:hidden name="submittedDate" />
					</td>
				</tr>
				
				
				<tr>
					<td class="formtext" width="20%">
						<label name="businessJustification" id="businessJustification"
							ondblclick="callShowDiv(this);"> <%=label.get("businessJustification")%>
						</label><font color="red">*</font> :
					</td>
					<td height="22" width="25%" >
						<s:textarea name="businessJustification" cols="27" rows="3" onkeypress="return imposeMaxLength(event, this, 500);"/>
						<img id='ctrlHide' src="../pages/images/zoomin.gif" height="12" align="bottom"
							 width="12" theme="simple"
							onclick="javascript:callWindow('paraFrm_businessJustification','businessJustification','', '', '500','500');">
					</td>
					<td class="formtext" width="20%" id="showLocalPurcahseReasonLabel">
						<label name="reasonForLocalPurchase" id="reasonForLocalPurchase"
							ondblclick="callShowDiv(this);"> <%=label.get("reasonForLocalPurchase")%>
						</label> :
					</td>
					<td height="22" width="25%" id="showLocalPurcahseReasonField">
						<s:textarea name="reasonForLocalPurchase" cols="27" rows="3" onkeypress="return imposeMaxLength(event, this, 500);"/>
						<img id='ctrlHide' src="../pages/images/zoomin.gif" height="12" align="bottom"
							 width="12" theme="simple"
							onclick="javascript:callWindow('paraFrm_reasonForLocalPurchase','reasonForLocalPurchase','', '', '500','500');">
					</td>
				 </tr>
			  </table>
			</td>
		</tr>

<!-- General Information Section Begins -->
		<tr>
			<td>
			  <table width="100%" align="right" class="formbg">
				<tr>
					<td colspan="4" class="formtext">
						<b> General Information</b>
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="20%">
						<label name="departmentNumber"	id="departmentNumber" ondblclick="callShowDiv(this);"> <%=label.get("departmentNumber")%>
						</label> :
					</td>					
					
					<td height="22" width="25%">
						<s:textfield name="departmentNumber" size="25" readonly="true" />
					</td>
					
					<td class="formtext" width="20%">
						<label name="dateRequired" id="dateRequired" ondblclick="callShowDiv(this);"> <%=label.get("dateRequired")%>
						</label><font color="red">*</font> :
					</td>
					<td height="22" width="25%">
						<s:textfield name="dateRequired" size="25" onkeypress="return numbersWithHiphen();" /> 
						<a href="javascript:NewCal('paraFrm_dateRequired','DDMMYYYY');">
							<img id='ctrlHide' src="../pages/images/Date.gif" class="iconImage"	height="16" align="absmiddle" width="16"> 
						</a>
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="20%">
						<label name="location"	id="location" ondblclick="callShowDiv(this);"> <%=label.get("location")%>
						</label><font color="red">*</font> :
					</td>					
					
					<td height="22" width="25%">
						<s:hidden name="locationID" /> 
						<s:textfield name="location" size="25" readonly="true" />
						<img id='ctrlHide' src="../pages/images/recruitment/search2.gif" height="16"
							align="absmiddle" width="17" theme="simple"
							onclick="javascript:callsF9(500,325,'CapitalExpenditure_f9Location.action'); resetOtherLocation();">
					</td>
					
					<td class="formtext" width="20%">
						<label name="ifOtherLocation" id="ifOtherLocation" ondblclick="callShowDiv(this);"> <%=label.get("ifOtherLocation")%>
						</label> :
					</td>
					<td height="22" width="25%">
						<s:textfield name="ifOtherLocation" size="25" onkeypress="resetLocation();" />						
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="20%">
						<label name="shipToCompany" id="shipToCompany" ondblclick="callShowDiv(this);"> <%=label.get("shipToCompany")%>
						</label><font color="red">*</font> :
					</td>
					<td height="22" width="25%">
						<s:textfield name="shipToCompany" size="25" maxlength="30"/>						
					</td>
					<td class="formtext" width="20%">&nbsp;</td>					
					<td height="22" width="25%">&nbsp;</td>
				</tr>
				
				<tr>
					<td class="formtext" width="20%" >
						<label name="city"	id="city" ondblclick="callShowDiv(this);"> <%=label.get("city")%>
						</label><font color="red">*</font> :
					</td>					
					
				<!-- 
						<td height="22" width="25%" id="cityIfOtherSelected">
						<s:hidden name="cityID" />
						<s:textfield name="city" size="25" readonly="true" />
							<img id='ctrlHide' src="../pages/images/recruitment/search2.gif" height="16"
							align="absmiddle" width="17" theme="simple"
							onclick="javascript:callsF9(500,325,'CapitalExpenditure_f9City.action'); resetOtherLocation();">
					</td>
				 -->
				
					
					<td height="22" width="25%">
						<s:hidden name="cityID" />
						<s:textfield name="city" size="25"  maxlength="30"/>
					</td>
					
					<td class="formtext" width="20%">
						<label name="state" id="state" ondblclick="callShowDiv(this);"> <%=label.get("state")%>
						</label><font color="red">*</font> :
					</td>
					<td height="22" width="25%">
						<s:textfield name="state" size="25"  maxlength="30"/>						
					</td>
				</tr>
				
				
				<tr>
					<td class="formtext" width="20%">
						<label name="streetAddress"	id="streetAddress" ondblclick="callShowDiv(this);"> <%=label.get("streetAddress")%>
						</label><font color="red">*</font> :
					</td>					
					
					<td height="22" width="25%">						
						<s:textarea name="streetAddress" cols="27" rows="3" onkeypress="return imposeMaxLength(event, this, 500);"/>
						<img id='ctrlHide' src="../pages/images/zoomin.gif" height="12" align="bottom"
							 width="12" theme="simple"
							onclick="javascript:callWindow('paraFrm_streetAddress','streetAddress','', '', '500','500');">
					</td>
					
					<td class="formtext" width="20%">
						<label name="zipCode" id="zipCode" ondblclick="callShowDiv(this);"> <%=label.get("zipCode")%>
						</label><font color="red">*</font> :
					</td>
					<td height="22" width="25%">
						<s:textfield name="zipCode" size="25"  maxlength="30" />						
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="20%">
						<label name="attention"	id="attention" ondblclick="callShowDiv(this);"> <%=label.get("attention")%>
						</label><font color="red">*</font> :
					</td>					
					
					<td height="22" width="25%">						
						<s:textfield name="attention" size="25" />
					</td>
					
					<td class="formtext" width="20%">
						<label name="telePhoneNumber" id="telePhoneNumber" ondblclick="callShowDiv(this);"> <%=label.get("telePhoneNumber")%>
						</label><font color="red">*</font> :
					</td>
					<td height="22" width="25%">
						<s:textfield name="telePhoneNumber" size="25"  maxlength="30" />						
					</td>
				</tr>
			</table>
			</td>
		</tr>
<!-- General Information Section Ends -->
				
<!-- Quotes And Attachments Starts -->
		<tr>
			<td>
			  <table width="100%" align="right" class="formbg">
				<tr>
					<td colspan="4" class="formtext">
						<b> Quotes/Attachments</b>
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="20%">
						<label name="quotesAttached"	id="quotesAttached" ondblclick="callShowDiv(this);"> <%=label.get("quotesAttached")%>
						</label><font color="red">*</font> :
					</td>					
					
					<td height="22" width="25%">						
						<s:radio name="quotesAttached"	value="%{quotesAttached}" list="#{'no':'No','yes':'Yes'}"
						onclick="setQuotesAttached(this);" />
					</td>
					
					<td class="formtext" width="20%" id="quotesReasonTextLabel">
						<label name="quotesReason" id="quotesReason" ondblclick="callShowDiv(this);"> <%=label.get("quotesReason")%>
						</label> :
					</td>
					<td height="22" width="25%" id="quotesReasonTextField">
						<s:textarea name="quotesReason" cols="27" rows="3" onkeypress="return imposeMaxLength(event, this, 500);"/> 
							<img id='ctrlHide' src="../pages/images/zoomin.gif" height="12" align="bottom"
							 width="12" theme="simple"
							onclick="javascript:callWindow('paraFrm_quotesReason','quotesReason','', '', '500','500');">
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="20%">
						<label name="otherAttachments"	id="otherAttachments" ondblclick="callShowDiv(this);"> <%=label.get("otherAttachments")%>
						</label> :
					</td>					
					<td height="22" width="25%">						
						<s:textfield readonly="true" cssStyle="background-color: #F2F2F2;" size="40" name="uploadFileName" />
					</td>
					<td width="20%">&nbsp;</td>
					<td width="25%">&nbsp;</td>
				</tr>
				
				<tr>
					<td width="20%">&nbsp;</td>
					<td width="25%">
						<a href="#" onclick="showRecord();"><font color="blue"><u>click here to view attached file</u></font></a>
					</td>
					<td width="20%">&nbsp;</td>
					<td width="25%">&nbsp;</td>
				</tr>	
			</table>
		   </td>
		 </tr>  		
<!-- Quotes And Attachments Finish -->
		
<!-- Additional Comments Starts -->
		<tr>
			<td>
			<table width="100%" align="right" class="formbg">
				<tr>   
					<td colspan="4" class="formtext">
						<b> <label name="comments"	id="comments" ondblclick="callShowDiv(this);"> <%=label.get("comments")%>
						</label> : </b>
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="20%">&nbsp;</td>					
					
					<td colspan="2">						
						<s:textarea name="comments" cols="100" rows="3" onkeypress="return imposeMaxLength(event, this, 500);"/>
					</td>
					<td width="25%">
						<img id='ctrlHide' src="../pages/images/zoomin.gif" height="12" align="bottom"
							 width="12" theme="simple"
							onclick="javascript:callWindow('paraFrm_comments','comments','', '', '500','500');">						
					</td>
				</tr>
			</table>
		  </td>
	   </tr>  		
<!-- Additional Comments Finish -->

<!-- Other Approver Detail Table Entry BEGINS -->
	<s:if test="otherApproverFlag">
		<tr>
			<td>
			  <table width="100%" align="right" class="formbg" border="1">
				<tr>
					<td width="70%" >
						<b> Description</b><br>
						Attach Copy Of Justification Calculations, If Cost Savings Are Indicated
					</td>
					<td width="30%" ><b>Accounting Use Only</b></td>
				</tr>
				<tr>
					<td width="70%"> 
						<table width="100%" border="1" > <!-- Details Table Begins -->
							<tr>
								<td width="5%" align="center">
									<b>Item Number</b>
								</td>
								<td width="10%" align="center">
									<b><font color="red">*</font>Quantity</b>
								</td>
								<td width="25%" align="center">
									<b><font color="red">*</font>Description</b>
								</td>
								<td width="15%"  align="center" colspan="2">
									<b><font color="red">*</font>Vendor</b>
								</td>								
								<td width="10%" align="center">
									<b><font color="red">*</font>Unit Price</b>
								</td>
								<td width="15%" align="center" >
									<b>Total Cost</b>
								</td>
							</tr>
							<tr>
								<td align="center" id="ctrlShow">1</td>
								<td align="center" id="ctrlShow"><s:textfield size= "7" name="quantity1" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "25" name="description1" maxlength="100" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td colspan="2" align="center" id="ctrlShow"><s:hidden name="vendorID1"/>
									<s:textfield  size= "13" name="vendorName1" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="unitPrice1" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="totalCost1" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
							</tr>
							<tr>
								<td align="center" id="ctrlShow">2</td>
								<td align="center" id="ctrlShow"><s:textfield size= "7" name="quantity2" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "25" name="description2" maxlength="100" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td colspan="2" align="center" id="ctrlShow"><s:hidden name="vendorID2"/>
									<s:textfield  size= "13" name="vendorName2" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="unitPrice2" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="totalCost2" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
							</tr>
							<tr>
								<td align="center" id="ctrlShow">3</td>
								<td align="center" id="ctrlShow"><s:textfield size= "7" name="quantity3" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "25" name="description3" maxlength="100" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td colspan="2" align="center" id="ctrlShow"><s:hidden name="vendorID3"/>
									<s:textfield size= "13" name="vendorName3" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="unitPrice3" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="totalCost3" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
							</tr>
							<tr>
								<td align="center" id="ctrlShow">4</td>
								<td align="center" id="ctrlShow"><s:textfield size= "7" name="quantity4" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "25" name="description4" maxlength="100" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td colspan="2" align="center" id="ctrlShow"><s:hidden name="vendorID4"/>
									<s:textfield size= "13" name="vendorName4" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="unitPrice4" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="totalCost4" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
							</tr>
							<tr>
								<td align="center" id="ctrlShow">5</td>
								<td align="center" id="ctrlShow"><s:textfield size= "7" name="quantity5" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "25"  name="description5" maxlength="100" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td colspan="2" align="center" id="ctrlShow"><s:hidden name="vendorID5"/>
									<s:textfield size= "13" name="vendorName5" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="unitPrice5" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="totalCost5" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
							</tr>
							<tr>
								<td align="center" id="ctrlShow">6</td>
								<td align="center" id="ctrlShow"><s:textfield size= "7" name="quantity6" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "25" name="description6" maxlength="100" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td colspan="2" align="center" id="ctrlShow"><s:hidden name="vendorID6"/>
									<s:textfield size= "13" name="vendorName6" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="unitPrice6" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="totalCost6" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
							</tr>
							<tr>
								<td align="center" id="ctrlShow">7</td>
								<td align="center" id="ctrlShow"><s:textfield size= "7" name="quantity7" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "25" name="description7" maxlength="100" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td colspan="2" align="center" id="ctrlShow"><s:hidden name="vendorID7"/>
									<s:textfield size= "13" name="vendorName7" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="unitPrice7" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="totalCost7" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
							</tr>
							<tr>
								<td align="center" id="ctrlShow">8</td>
								<td align="center" id="ctrlShow"><s:textfield size= "7" name="quantity8" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "25" name="description8" maxlength="100" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td colspan="2" align="center" id="ctrlShow"><s:hidden name="vendorID8" />
									<s:textfield size= "13" name="vendorName8" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="unitPrice8" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="totalCost8" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
							</tr>
							<tr>
								<td align="center" id="ctrlShow">9</td>
								<td align="center" id="ctrlShow"><s:textfield size= "7" name="quantity9" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "25" name="description9" maxlength="100" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td colspan="2" align="center" id="ctrlShow"><s:hidden name="vendorID9"/>
									<s:textfield size= "13" name="vendorName9" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="unitPrice9" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="totalCost9" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
							</tr>
							<tr>
								<td align="center" id="ctrlShow">10</td>
								<td align="center" id="ctrlShow"><s:textfield size= "7" name="quantity10" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "25" name="description10" maxlength="100" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td colspan="2" align="center" id="ctrlShow"><s:hidden name="vendorID10"/>
									<s:textfield size= "13" name="vendorName10" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="unitPrice10" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="totalCost10" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
							</tr>
							<tr>
								<td align="right" colspan="5" id="ctrlShow"><b>Sub-Total : </b></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="subTotalUnitPrice" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="subTotalTotalCost" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
							</tr>
						</table> <!-- Details Table Ends -->
					</td> 
					
					<td width="30%"> 
						<table width="100%" border="1"> <!-- Accounting Use Only Table Begins -->
							<tr>	
								<td width="35%" align="center"><b>Tag Number</b></td>
								<td width="30%" align="center"><b>Serial Number</b></td>
								<td width="35%" align="center"><b>CEAR Price</b></td>
							</tr>
							<tr>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="tagNumber1" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="serialNumber1" readonly="true" cssStyle="background-color: #F2F2F2;" /></td>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="cearPrice1" readonly="true" cssStyle="background-color: #F2F2F2;" /></td>
							</tr>
							<tr>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="tagNumber2" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="serialNumber2" readonly="true" cssStyle="background-color: #F2F2F2;" /></td>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="cearPrice2" readonly="true" cssStyle="background-color: #F2F2F2;" /></td>
							</tr>
							<tr>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="tagNumber3" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="serialNumber3" readonly="true" cssStyle="background-color: #F2F2F2;" /></td>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="cearPrice3"  readonly="true" cssStyle="background-color: #F2F2F2;" /></td>
							</tr>
							<tr>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="tagNumber4" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="serialNumber4" readonly="true" cssStyle="background-color: #F2F2F2;" /></td>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="cearPrice4"  readonly="true" cssStyle="background-color: #F2F2F2;" /></td>
							</tr>
							<tr>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="tagNumber5" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="serialNumber5" readonly="true" cssStyle="background-color: #F2F2F2;" /></td>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="cearPrice5" readonly="true" cssStyle="background-color: #F2F2F2;" /></td>
							</tr>
							<tr>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="tagNumber6" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="serialNumber6"  readonly="true" cssStyle="background-color: #F2F2F2;" /></td>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="cearPrice6"  readonly="true" cssStyle="background-color: #F2F2F2;" /></td>
							</tr>
							<tr>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="tagNumber7" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="serialNumber7"  readonly="true" cssStyle="background-color: #F2F2F2;" /></td>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="cearPrice7"  readonly="true" cssStyle="background-color: #F2F2F2;" /></td>
							</tr>
							<tr>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="tagNumber8" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="serialNumber8" readonly="true" cssStyle="background-color: #F2F2F2;" /></td>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="cearPrice8" readonly="true" cssStyle="background-color: #F2F2F2;" /></td>
							</tr>
							<tr>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="tagNumber9" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="serialNumber9" readonly="true" cssStyle="background-color: #F2F2F2;" /></td>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="cearPrice9" readonly="true" cssStyle="background-color: #F2F2F2;" /></td>
							</tr>
							<tr>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="tagNumber10" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="serialNumber10" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="cearPrice10" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
							</tr>
							<tr>
								<td colspan="2" align="right">
									<b>Sub-Total : </b>
								</td>
								<td align="center" id="ctrlShow" ><s:textfield size="10" name="subTotalCEAR" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
							</tr>
						</table>	<!-- Accounting Use Only Table Ends -->
					</td> 
				</tr>
			 </table>
			</td>
		 </tr>	
	</s:if>
<!-- Other Approver Detail Table Entry ENDS -->

<!-- Finance Approver Detail Table Entry BEGINS -->
	<s:if test="financeApprovalFlag">
		<tr>
			<td>
			  <table width="100%" align="right" class="formbg" border="1">
				<tr>
					<td width="70%" >
						<b> Description</b><br>
						Attach Copy Of Justification Calculations, If Cost Savings Are Indicated
					</td>
					<td width="30%" ><b>Accounting Use Only</b></td>
				</tr>
				<tr>
					<td width="70%"> 
						<table width="100%" border="1" > <!-- Details Table Begins -->
							<tr>
								<td width="5%" align="center">
									<b>Item Number</b>
								</td>
								<td width="10%" align="center">
									<b><font color="red">*</font>Quantity</b>
								</td>
								<td width="25%" align="center">
									<b><font color="red">*</font>Description</b>
								</td>
								<td width="15%"  align="center" colspan="2">
									<b><font color="red">*</font>Vendor</b>
								</td>								
								<td width="10%" align="center">
									<b><font color="red">*</font>Unit Price</b>
								</td>
								<td width="15%" align="center" >
									<b>Total Cost</b>
								</td>
							</tr>
							<tr>
								<td align="center" id="ctrlShow">1</td>
								<td align="center" id="ctrlShow"><s:textfield size= "7" name="quantity1" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "25" name="description1" maxlength="100" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td colspan="2" align="center" id="ctrlShow"><s:hidden name="vendorID1"/>
									<s:textfield  size= "13" name="vendorName1" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="unitPrice1" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="totalCost1" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
							</tr>
							<tr>
								<td align="center" id="ctrlShow">2</td>
								<td align="center" id="ctrlShow"><s:textfield size= "7" name="quantity2" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "25" name="description2" maxlength="100" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td colspan="2" align="center" id="ctrlShow"><s:hidden name="vendorID2"/>
									<s:textfield  size= "13" name="vendorName2" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="unitPrice2" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="totalCost2" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
							</tr>
							<tr>
								<td align="center" id="ctrlShow">3</td>
								<td align="center" id="ctrlShow"><s:textfield size= "7" name="quantity3" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "25" name="description3" maxlength="100" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td colspan="2" align="center" id="ctrlShow"><s:hidden name="vendorID3"/>
									<s:textfield size= "13" name="vendorName3" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="unitPrice3" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="totalCost3" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
							</tr>
							<tr>
								<td align="center" id="ctrlShow">4</td>
								<td align="center" id="ctrlShow"><s:textfield size= "7" name="quantity4" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "25" name="description4" maxlength="100" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td colspan="2" align="center" id="ctrlShow"><s:hidden name="vendorID4"/>
									<s:textfield size= "13" name="vendorName4" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="unitPrice4" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="totalCost4" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
							</tr>
							<tr>
								<td align="center" id="ctrlShow">5</td>
								<td align="center" id="ctrlShow"><s:textfield size= "7" name="quantity5" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "25"  name="description5" maxlength="100" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td colspan="2" align="center" id="ctrlShow"><s:hidden name="vendorID5"/>
									<s:textfield size= "13" name="vendorName5" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="unitPrice5" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="totalCost5" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
							</tr>
							<tr>
								<td align="center" id="ctrlShow">6</td>
								<td align="center" id="ctrlShow"><s:textfield size= "7" name="quantity6" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "25" name="description6" maxlength="100" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td colspan="2" align="center" id="ctrlShow"><s:hidden name="vendorID6"/>
									<s:textfield size= "13" name="vendorName6" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="unitPrice6" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="totalCost6" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
							</tr>
							<tr>
								<td align="center" id="ctrlShow">7</td>
								<td align="center" id="ctrlShow"><s:textfield size= "7" name="quantity7" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "25" name="description7" maxlength="100" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td colspan="2" align="center" id="ctrlShow"><s:hidden name="vendorID7"/>
									<s:textfield size= "13" name="vendorName7" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="unitPrice7" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="totalCost7" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
							</tr>
							<tr>
								<td align="center" id="ctrlShow">8</td>
								<td align="center" id="ctrlShow"><s:textfield size= "7" name="quantity8" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "25" name="description8" maxlength="100" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td colspan="2" align="center" id="ctrlShow"><s:hidden name="vendorID8" />
									<s:textfield size= "13" name="vendorName8" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="unitPrice8" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="totalCost8" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
							</tr>
							<tr>
								<td align="center" id="ctrlShow">9</td>
								<td align="center" id="ctrlShow"><s:textfield size= "7" name="quantity9" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "25" name="description9" maxlength="100" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td colspan="2" align="center" id="ctrlShow"><s:hidden name="vendorID9"/>
									<s:textfield size= "13" name="vendorName9" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="unitPrice9" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="totalCost9" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
							</tr>
							<tr>
								<td align="center" id="ctrlShow">10</td>
								<td align="center" id="ctrlShow"><s:textfield size= "7" name="quantity10" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "25" name="description10" maxlength="100" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td colspan="2" align="center" id="ctrlShow"><s:hidden name="vendorID10"/>
									<s:textfield size= "13" name="vendorName10" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="unitPrice10" maxlength="20" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="totalCost10" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
							</tr>
							<tr>
								<td align="right" colspan="5" id="ctrlShow"><b>Sub-Total : </b></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="subTotalUnitPrice" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center" id="ctrlShow"><s:textfield size= "10" name="subTotalTotalCost" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
							</tr>
						</table> <!-- Details Table Ends -->
					</td> 
					
					<td width="30%"> 
						<table width="100%" border="1"> <!-- Accounting Use Only Table Begins -->
							<tr>	
								<td width="35%" align="center"><b>Tag Number</b></td>
								<td width="30%" align="center"><b>Serial Number</b></td>
								<td width="35%" align="center"><b>CEAR Price</b></td>
							</tr>
							<tr>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="tagNumber1" maxlength="30"/></td>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="serialNumber1" maxlength="30" onkeypress="return numbersWithHiphen();" /></td>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="cearPrice1" maxlength="20" onkeypress="return numbersWithDot();" onchange="calculateCEARSubTotal();" /></td>
							</tr>
							<tr>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="tagNumber2" maxlength="30"/></td>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="serialNumber2" maxlength="30" onkeypress="return numbersWithHiphen();" /></td>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="cearPrice2" maxlength="20" onkeypress="return numbersWithDot();" onchange="calculateCEARSubTotal();" /></td>
							</tr>
							<tr>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="tagNumber3" maxlength="30"/></td>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="serialNumber3" maxlength="30" onkeypress="return numbersWithHiphen();" /></td>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="cearPrice3"  maxlength="20" onkeypress="return numbersWithDot();" onchange="calculateCEARSubTotal();" /></td>
							</tr>
							<tr>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="tagNumber4" maxlength="30"/></td>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="serialNumber4" maxlength="30" onkeypress="return numbersWithHiphen();" /></td>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="cearPrice4" maxlength="20" onkeypress="return numbersWithDot();" onchange="calculateCEARSubTotal();" /></td>
							</tr>
							<tr>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="tagNumber5" maxlength="30"/></td>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="serialNumber5" maxlength="30" onkeypress="return numbersWithHiphen();" /></td>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="cearPrice5" maxlength="20" onkeypress="return numbersWithDot();" onchange="calculateCEARSubTotal();" /></td>
							</tr>
							<tr>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="tagNumber6"  maxlength="30"/></td>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="serialNumber6"  maxlength="30" onkeypress="return numbersWithHiphen();" /></td>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="cearPrice6" maxlength="20" onkeypress="return numbersWithDot();" onchange="calculateCEARSubTotal();"/></td>
							</tr>
							<tr>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="tagNumber7"  maxlength="30"/></td>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="serialNumber7" maxlength="30" onkeypress="return numbersWithHiphen();" /></td>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="cearPrice7" maxlength="20" onkeypress="return numbersWithDot();" onchange="calculateCEARSubTotal();" /></td>
							</tr>
							<tr>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="tagNumber8"  maxlength="30"/></td>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="serialNumber8" maxlength="30" onkeypress="return numbersWithHiphen();"/></td>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="cearPrice8" maxlength="20" onkeypress="return numbersWithDot();" onchange="calculateCEARSubTotal();"/></td>
							</tr>
							<tr>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="tagNumber9"  maxlength="30"/></td>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="serialNumber9" maxlength="30" onkeypress="return numbersWithHiphen();" /></td>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="cearPrice9" maxlength="20" onkeypress="return numbersWithDot();" onchange="calculateCEARSubTotal();"/></td>
							</tr>
							<tr>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="tagNumber10" maxlength="30"/></td>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="serialNumber10" maxlength="30" onkeypress="return numbersWithHiphen();"/></td>
								<td align="center" id="ctrlShow"><s:textfield size="10" name="cearPrice10" maxlength="20" onkeypress="return numbersWithDot();" onchange="calculateCEARSubTotal();"/></td>
							</tr>
							<tr>
								<td colspan="2" align="right">
									<b>Sub-Total : </b>
								</td>
								<td align="center" id="ctrlShow" ><s:textfield size="10" name="subTotalCEAR" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
							</tr>
						</table>	<!-- Accounting Use Only Table Ends -->
					</td> 
				</tr>
			 </table>
			</td>
		 </tr>	
	</s:if>
<!-- Finance Approver Detail Table Entry ENDS -->		

<!-- Calculation Part For APPLICANT Starts -->
	<s:if test="otherApproverFlag">
		<tr>
			<td>
			<table width="100%" align="right" class="formbg">
				<tr>
					<td colspan="2" class="formtext" align="center">
						<b>Cost Breakdown</b>
					</td>
					<td colspan="2" class="formtext" align="center">
						<b>Invoice Information (For A/P use only)</b>
					</td>
					<td colspan="2" class="formtext" align="center">
						<b>Actual Cost(For A/P use only)</b>
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="10%">
						<label name="costSubTotal"	id="costSubTotal" ondblclick="callShowDiv(this);"> <%=label.get("costSubTotal")%>
						</label> :
					</td>					
					<td height="22" width="20%">						
						<s:textfield name="costSubTotal" size="20" readonly="true" cssStyle="background-color: #F2F2F2;" />	
					</td>
					
					<td class="formtext" width="15%" >
						<label name="invoicePoNumber" id="invoicePoNumber" ondblclick="callShowDiv(this);"> <%=label.get("invoicePoNumber")%>
						</label> :
					</td>
					
					<td height="22" width="20%" >						
						<s:textfield name="invoicePoNumber" size="20" maxlength="15" readonly="true" cssStyle="background-color: #F2F2F2;"/>	
					</td>
					
					<td class="formtext" width="15%">
						<label name="assetSubTotal"	id="assetSubTotal" ondblclick="callShowDiv(this);"> <%=label.get("assetSubTotal")%>
						</label> :
					</td>					
					<td height="22" width="20%" >						
						<s:textfield name="assetSubTotal" maxlength="15" size="20" maxlength="15" readonly="true" cssStyle="background-color: #F2F2F2;"/>	
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="10%">
						<label name="costInstallation"	id="costInstallation" ondblclick="callShowDiv(this);"> <%=label.get("costInstallation")%>
						</label> :
					</td>					
					<td height="22" width="20%">						
						<s:textfield name="costInstallation" maxlength="15" size="20" onkeypress="return numbersWithDot();" />	
					</td>
					
					<td class="formtext" width="15%" >
						<label name="invoiceVendorNumber" id="invoiceVendorNumber" ondblclick="callShowDiv(this);"> <%=label.get("invoiceVendorNumber")%>
						</label> :
					</td>
					<td height="22" width="20%" >	
						<s:hidden name="invoiceVendorID" />					
						<s:textfield name="invoiceVendorNumber" size="20" readonly="true" cssStyle="background-color: #F2F2F2;"/>
					</td>
					
					<td class="formtext" width="15%" >
						<label name="actualInstallation"	id="actualInstallation" ondblclick="callShowDiv(this);"> <%=label.get("actualInstallation")%>
						</label> :
					</td>					
					<td height="22" width="20%" >						
						<s:textfield name="actualInstallation" size="20"  maxlength="15" readonly="true" cssStyle="background-color: #F2F2F2;"/>	
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="10%">
						<label name="costMaterial"	id="costMaterial" ondblclick="callShowDiv(this);"> <%=label.get("costMaterial")%>
						</label> :
					</td>					
					<td height="22" width="20%">						
						<s:textfield name="costMaterial" maxlength="15" size="20" onkeypress="return numbersWithDot();" />	
					</td>
					
					<td class="formtext" width="15%" >
						<label name="invoiceTotal" id="invoiceTotal" ondblclick="callShowDiv(this);"> <%=label.get("invoiceTotal")%>
						</label> :
					</td>
					<td height="22" width="20%">						
						<s:textfield name="invoiceTotal" size="20" maxlength="15" readonly="true" cssStyle="background-color: #F2F2F2;"/>	
					</td>
					
					<td class="formtext" width="15%">
						<label name="actualMaterial"	id="actualMaterial" ondblclick="callShowDiv(this);"> <%=label.get("actualMaterial")%>
						</label> :
					</td>					
					<td height="22" width="20%">						
						<s:textfield name="actualMaterial" size="20" maxlength="15" readonly="true" cssStyle="background-color: #F2F2F2;"/>	
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="10%">
						<label name="costFreight"	id="costFreight" ondblclick="callShowDiv(this);"> <%=label.get("costFreight")%>
						</label> :
					</td>					
					<td height="22" width="20%">						
						<s:textfield name="costFreight" size="20"  maxlength="15" onkeypress="return numbersWithDot();" />	
					</td>
					
					<td class="formtext" width="15%" >&nbsp;</td>
					<td height="22" width="20%">&nbsp;</td>
					
					<td class="formtext" width="15%">
						<label name="actualFreight"	id="actualFreight" ondblclick="callShowDiv(this);"> <%=label.get("actualFreight")%>
						</label> :
					</td>					
					<td height="22" width="20%">						
						<s:textfield name="actualFreight" size="20" maxlength="15" readonly="true" cssStyle="background-color: #F2F2F2;"/>	
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="10%">
						<label name="costTax"	id="costTax" ondblclick="callShowDiv(this);"> <%=label.get("costTax")%>
						</label> :
					</td>					
					<td height="22" width="20%">						
						<s:textfield name="costTax" size="20"  maxlength="15" onkeypress="return numbersWithDot();" />	
					</td>
					
					<td class="formtext" width="15%" >
						<label name="dateTagged" id="dateTagged" ondblclick="callShowDiv(this);"> <%=label.get("dateTagged")%>
						</label> :
					</td>
					<td height="22" width="20%">						
						<s:textfield name="dateTagged" size="20" readonly="true" cssStyle="background-color: #F2F2F2;"/>							
					</td>
					
					<td class="formtext" width="15%">
						<label name="actualTax"	id="actualTax" ondblclick="callShowDiv(this);"> <%=label.get("actualTax")%>
						</label> :
					</td>					
					<td height="22" width="20%">						
						<s:textfield name="actualTax" size="20" maxlength="15"  readonly="true" cssStyle="background-color: #F2F2F2;"/>	
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="10%">
						<label name="costTotal"	id="costTotal" ondblclick="callShowDiv(this);"> <%=label.get("costTotal")%>
						</label> :
					</td>					
					<td height="22" width="20%">						
						<s:textfield name="costTotal" size="20"  readonly="true" cssStyle="background-color: #F2F2F2;"/>	
					</td>
					
					<td class="formtext" width="15%" >&nbsp;</td>
					<td height="22" width="20%">&nbsp;</td>
					
					<td class="formtext" width="15%">
						<label name="actualTotal"	id="actualTotal" ondblclick="callShowDiv(this);"> <%=label.get("actualTotal")%>
						</label> :
					</td>					
					<td height="22" width="20%">						
						<s:textfield name="actualTotal" size="20" readonly="true" cssStyle="background-color: #F2F2F2;"/>	
					</td>
				</tr>
			</table>
		   </td>
		 </tr>  
	</s:if>		 
<!-- Calculation Part OtherApprover Finish -->

<!-- Calculation Part For Finance-Approver Begins -->
	<s:if test="financeApprovalFlag">
		<tr>
			<td>
			<table width="100%" align="right" class="formbg">
				<tr>
					<td colspan="2" class="formtext" align="center">
						<b>Cost Breakdown</b>
					</td>
					<td colspan="2" class="formtext" align="center">
						<b>Invoice Information (For A/P use only)</b>
					</td>
					<td colspan="2" class="formtext" align="center">
						<b>Actual Cost(For A/P use only)</b>
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="10%">
						<label name="costSubTotal"	id="costSubTotal" ondblclick="callShowDiv(this);"> <%=label.get("costSubTotal")%>
						</label> :
					</td>					
					<td height="22" width="20%">						
						<s:textfield name="costSubTotal" size="20" readonly="true" cssStyle="background-color: #F2F2F2;" />	
					</td>
					
					<td class="formtext" width="15%" >
						<label name="invoicePoNumber" id="invoicePoNumber" ondblclick="callShowDiv(this);"> <%=label.get("invoicePoNumber")%>
						</label> :
					</td>
					
					<td height="22" width="20%" id="ctrlShow">						
						<s:textfield name="invoicePoNumber" size="20" maxlength="15" onkeypress="return numbersWithDot();"/>	
					</td>
					
					<td class="formtext" width="15%">
						<label name="assetSubTotal"	id="assetSubTotal" ondblclick="callShowDiv(this);"> <%=label.get("assetSubTotal")%>
						</label> :
					</td>					
					<td height="22" width="20%"  id="ctrlShow">						
						<s:textfield name="assetSubTotal" maxlength="15" size="20" maxlength="15" onchange="javascript:calculateActualCostTotal()" onkeypress="return numbersWithDot();"/>	
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="10%">
						<label name="costInstallation"	id="costInstallation" ondblclick="callShowDiv(this);"> <%=label.get("costInstallation")%>
						</label> :
					</td>					
					<td height="22" width="20%">						
						<s:textfield name="costInstallation" maxlength="15" size="20" onkeypress="return numbersWithDot();" />	
					</td>
					
					<td class="formtext" width="15%" >
						<label name="invoiceVendorNumber" id="invoiceVendorNumber" ondblclick="callShowDiv(this);"> <%=label.get("invoiceVendorNumber")%>
						</label> :
					</td>
					<td height="22" width="20%"  id="ctrlShow">	
						<s:hidden name="invoiceVendorID" />					
						<s:textfield name="invoiceVendorNumber" size="20" maxlength="30"/>
					<!-- 
							<img id='ctrlHide' src="../pages/images/recruitment/search2.gif" height="16"
							align="absmiddle" width="17" theme="simple"
							onclick="javascript:callsF9(500,325,'CapitalExpenditure_f9InvoiceVendor.action');">				
					 -->	
						
					</td>
					
					<td class="formtext" width="15%">
						<label name="actualInstallation"	id="actualInstallation" ondblclick="callShowDiv(this);"> <%=label.get("actualInstallation")%>
						</label> :
					</td>					
					<td height="22" width="20%" id="ctrlShow">						
						<s:textfield name="actualInstallation" size="20"  maxlength="15" onchange="javascript:calculateActualCostTotal()" onkeypress="return numbersWithDot();"/>	
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="10%">
						<label name="costMaterial"	id="costMaterial" ondblclick="callShowDiv(this);"> <%=label.get("costMaterial")%>
						</label> :
					</td>					
					<td height="22" width="20%">						
						<s:textfield name="costMaterial" maxlength="15" size="20" onkeypress="return numbersWithDot();" />	
					</td>
					
					<td class="formtext" width="15%" >
						<label name="invoiceTotal" id="invoiceTotal" ondblclick="callShowDiv(this);"> <%=label.get("invoiceTotal")%>
						</label> :
					</td>
					<td height="22" width="20%" id="ctrlShow">						
						<s:textfield name="invoiceTotal" size="20" maxlength="15" onkeypress="return numbersWithDot();"/>	
					</td>
					
					<td class="formtext" width="15%">
						<label name="actualMaterial"	id="actualMaterial" ondblclick="callShowDiv(this);"> <%=label.get("actualMaterial")%>
						</label> :
					</td>					
					<td height="22" width="20%" id="ctrlShow">						
						<s:textfield name="actualMaterial" size="20" maxlength="15"  onchange="javascript:calculateActualCostTotal()" onkeypress="return numbersWithDot();"/>	
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="10%">
						<label name="costFreight"	id="costFreight" ondblclick="callShowDiv(this);"> <%=label.get("costFreight")%>
						</label> :
					</td>					
					<td height="22" width="20%">						
						<s:textfield name="costFreight" size="20"  maxlength="15" onkeypress="return numbersWithDot();" />	
					</td>
					
					<td class="formtext" width="15%" >&nbsp;</td>
					<td height="22" width="20%">&nbsp;</td>
					
					<td class="formtext" width="15%">
						<label name="actualFreight"	id="actualFreight" ondblclick="callShowDiv(this);"> <%=label.get("actualFreight")%>
						</label> :
					</td>					
					<td height="22" width="20%" id="ctrlShow">						
						<s:textfield name="actualFreight" size="20" maxlength="15"  onchange="javascript:calculateActualCostTotal()" onkeypress="return numbersWithDot();"/>	
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="10%">
						<label name="costTax"	id="costTax" ondblclick="callShowDiv(this);"> <%=label.get("costTax")%>
						</label> :
					</td>					
					<td height="22" width="20%">						
						<s:textfield name="costTax" size="20"  maxlength="15" onkeypress="return numbersWithDot();" />	
					</td>
					
					<td class="formtext" width="15%" >
						<label name="dateTagged" id="dateTagged" ondblclick="callShowDiv(this);"> <%=label.get("dateTagged")%>
						</label> :
					</td>
					<td height="22" width="20%" id="ctrlShow">						
						<s:textfield name="dateTagged" size="20"/>
						<a href="javascript:NewCal('paraFrm_dateTagged','DDMMYYYY');">
							<img id='ctrlShow' src="../pages/images/Date.gif" class="iconImage"	height="16" align="absmiddle" width="16"> 
						</a>	
					</td>
					
					<td class="formtext" width="15%">
						<label name="actualTax"	id="actualTax" ondblclick="callShowDiv(this);"> <%=label.get("actualTax")%>
						</label> :
					</td>					
					<td height="22" width="20%" id="ctrlShow">						
						<s:textfield name="actualTax" size="20" maxlength="15"  onchange="javascript:calculateActualCostTotal()" onkeypress="return numbersWithDot();"/>	
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="10%">
						<label name="costTotal"	id="costTotal" ondblclick="callShowDiv(this);"> <%=label.get("costTotal")%>
						</label> :
					</td>					
					<td height="22" width="20%">						
						<s:textfield name="costTotal" size="20"  readonly="true" cssStyle="background-color: #F2F2F2;"/>	
					</td>
					
					<td class="formtext" width="15%" >&nbsp;</td>
					<td height="22" width="20%">&nbsp;</td>
					
					<td class="formtext" width="15%">
						<label name="actualTotal" id="actualTotal" ondblclick="callShowDiv(this);"> <%=label.get("actualTotal")%>
						</label> :
					</td>					
					<td height="22" width="20%" id="ctrlShow">						
						<s:textfield name="actualTotal" size="20" readonly="true" cssStyle="background-color: #F2F2F2;"/>	
					</td>
				</tr>
			</table>
		   </td>
		 </tr>  
	  </s:if>
<!-- Calculation Part For ACCOUNTANTS Ends -->
								
		
		<tr>
		   <td>
				<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
		   </td>
		</tr>
		<s:hidden name="dataPath"   />
		<s:hidden name="capitalExpID" />		
		<s:hidden name="trackingNumber" />	
		<s:hidden name="hiddenForwardedType"/>	
		<s:hidden name="status" />
		<s:hidden name="level" />
		<input type="hidden" name="quotesAttachedRadioOptionValue"
		id="quotesAttachedRadioOptionValue"	value='<s:property value="quotesAttachedRadioOptionValue"/>' />
	</table>
</s:form>

<script>
	doCalculation();
	function doCalculation(){
		try{
			var quotesReasonVar= document.getElementById('quotesAttachedRadioOptionValue').value;
			if(quotesReasonVar=='no'){
				document.getElementById('quotesReasonTextLabel').style.display = '';
				document.getElementById('quotesReasonTextField').style.display = '';
			}else{
				document.getElementById('quotesReasonTextLabel').style.display = 'none';
				document.getElementById('quotesReasonTextField').style.display = 'none';
			}
			
			//document.getElementById('paraFrm_quotesReason').readOnly='true';	
			//document.getElementById('paraFrm_quotesReason').style.background='#F2F2F2'; 
			
			var localPurchaseCheckboxVar = document.getElementById('paraFrm_localPurchaseCheckbox').checked;
			if(localPurchaseCheckboxVar == false){
			document.getElementById('showLocalPurcahseReasonLabel').style.display = 'none';
			document.getElementById('showLocalPurcahseReasonField').style.display = 'none';
			//document.getElementById('paraFrm_reasonForLocalPurchase').readOnly='true';	
			//document.getElementById('paraFrm_reasonForLocalPurchase').style.background='#F2F2F2';
			}else {
				document.getElementById('showLocalPurcahseReasonLabel').style.display = '';
				document.getElementById('showLocalPurcahseReasonField').style.display = '';
			//	document.getElementById('paraFrm_reasonForLocalPurchase').readOnly='';	
			//	document.getElementById('paraFrm_reasonForLocalPurchase').style.background='white';
			}
		}catch(e){alert(e);}
	}

	function uploadFile(fieldName) {
		//alert(fieldName);
		var dataPath = document.getElementById('paraFrm_dataPath').value;
		window.open('<%=request.getContextPath()%>/pages/helpdesk/fileUpload.jsp?path=' + dataPath + '&field=' + 
		fieldName, '', 'width=400, height=200, scrollbars=no, resizable=no, top=100, left=150');
	}
	
//Calculate Description Table Total-Cost and Sub-Total 	BEGINS
	function calculateTotalCost(){
			var totalCost = '0.0';
			var quantity = '0.0';
			var unitPrice = '0.0';
			for(k=1; k<=10; k++) {
			 totalCost = '';
			 quantity = '0.0';
			 unitPrice = '0.0';
						 
			if(!document.getElementById('paraFrm_quantity'+k).value==''){
				quantity = document.getElementById('paraFrm_quantity'+k).value;
			}
			if(!document.getElementById('paraFrm_unitPrice'+k).value==''){
				unitPrice = document.getElementById('paraFrm_unitPrice'+k).value;
			}
			if(!document.getElementById('paraFrm_quantity'+k).value=='' && !document.getElementById('paraFrm_unitPrice'+k).value==''){
			totalCost = quantity * unitPrice;
			document.getElementById('paraFrm_totalCost'+k).value = totalCost;
			}
		} //End of 1st for loop
		 
		 totalCost = '0.0';
		 unitPrice = '0.0';
		for(k=1; k<=10; k++) {
			if(!document.getElementById('paraFrm_unitPrice'+k).value==''){
				unitPrice = eval(unitPrice)+eval(document.getElementById('paraFrm_unitPrice'+k).value);
			}
		
			if(!document.getElementById('paraFrm_totalCost'+k).value==''){
				totalCost = eval(totalCost)+eval(document.getElementById('paraFrm_totalCost'+k).value);
			}
		} // End of 2nd for loop
		document.getElementById('paraFrm_subTotalUnitPrice').value = unitPrice;
		document.getElementById('paraFrm_subTotalTotalCost').value = totalCost;
		
		//For setting Cost BreakDown Sub-total
		 document.getElementById('paraFrm_costSubTotal').value = document.getElementById('paraFrm_subTotalTotalCost').value;
		 //For setting Cost BreakDown Total
		 document.getElementById('paraFrm_costTotal').value = document.getElementById('paraFrm_subTotalTotalCost').value;
	 }
//Calculate Description Table Total-Cost and Sub-Total 	ENDS
	
//For sub-Total Calculation of CEAR Price BEGINS
	function calculateCEARSubTotal(){
		var subTotalCEAR = '0.0';
		for(var m=1; m<=10; m++) {
			if(!document.getElementById('paraFrm_cearPrice'+m).value==''){
				subTotalCEAR = eval(subTotalCEAR)+parseFloat(document.getElementById('paraFrm_cearPrice'+m).value);
			}
		}
		subTotalCEAR = Math.round(subTotalCEAR*Math.pow(10,2))/Math.pow(10,2);
		document.getElementById('paraFrm_subTotalCEAR').value = subTotalCEAR;
	}
//For sub-Total Calculation of CEAR Price ENDS
	
//For Cost Break Calculation BEGINS	
	function calculateCostBreakDownTotal(){
		try{
			var total = 0.0;
			total=eval(document.getElementById('paraFrm_costSubTotal').value);
			if(document.getElementById('paraFrm_costInstallation').value==""){
				document.getElementById('paraFrm_costInstallation').value="0.0";
			}
			if(document.getElementById('paraFrm_costMaterial').value==""){
				document.getElementById('paraFrm_costMaterial').value="0.0";
			}
			if(document.getElementById('paraFrm_costFreight').value==""){
				document.getElementById('paraFrm_costFreight').value="0.0";
			}
			if(document.getElementById('paraFrm_costTax').value==""){
				document.getElementById('paraFrm_costTax').value="0.0";
			}
			total=eval(document.getElementById('paraFrm_costSubTotal').value) +
		  	  eval(document.getElementById('paraFrm_costInstallation').value) +
		  	  eval(document.getElementById('paraFrm_costMaterial').value)+
		      eval(document.getElementById('paraFrm_costFreight').value) +
		  	  eval(document.getElementById('paraFrm_costTax').value) ;

			document.getElementById('paraFrm_costTotal').value=total;
			if(document.getElementById('paraFrm_costTotal').value=="NaN"){
			   document.getElementById('paraFrm_costTotal').value="";
				}
		}
		catch(e){
			alert("Exception occured in calculateCostBreakDownTotal() : "+e);
		}	
	}
//For Cost Break Calculation ENDS

//For Actual Cost Calculation BEGINS	
	function calculateActualCostTotal(){
		try{
			var total = 0.0;
			if(document.getElementById('paraFrm_assetSubTotal').value==""){
				document.getElementById('paraFrm_assetSubTotal').value="0.0";
			}
			if(document.getElementById('paraFrm_actualInstallation').value==""){
				document.getElementById('paraFrm_actualInstallation').value="0.0";
			}
			if(document.getElementById('paraFrm_actualMaterial').value==""){
				document.getElementById('paraFrm_actualMaterial').value="0.0";
			}
			if(document.getElementById('paraFrm_actualFreight').value==""){
				document.getElementById('paraFrm_actualFreight').value="0.0";
			}
			if(document.getElementById('paraFrm_actualTax').value==""){
				document.getElementById('paraFrm_actualTax').value="0.0";
			}
			
			total=parseFloat(document.getElementById('paraFrm_assetSubTotal').value) +
		  	  parseFloat(document.getElementById('paraFrm_actualInstallation').value) +
		  	  parseFloat(document.getElementById('paraFrm_actualMaterial').value)+
		      parseFloat(document.getElementById('paraFrm_actualFreight').value) +
		  	  parseFloat(document.getElementById('paraFrm_actualTax').value) ;
			
			total = Math.round(total*Math.pow(10,2))/Math.pow(10,2);
			document.getElementById('paraFrm_actualTotal').value=total;
			if(document.getElementById('paraFrm_actualTotal').value=="NaN"){
			   document.getElementById('paraFrm_actualTotal').value="";
				}
		}
		catch(e){
			alert("Exception occured in calculateCostBreakDownTotal() : "+e);
		}	
	}
//For Actual Cost Calculation BEGINS
	
	
	function showRecord()
	{
		var statusVar = document.getElementById('paraFrm_status').value;
		var uploadFileName = document.getElementById('paraFrm_uploadFileName').value;
		if(statusVar=='_A' || statusVar=='_R' || statusVar=='_P' || statusVar=='_F' || statusVar=='_S'){
			if(uploadFileName == '') {
			alert('File not attached.');
			return false;
		  }
		}else if(uploadFileName == '') {
			alert('Please first upload the file...');
			return false;
		}
		document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = "CapitalExpenditureApproval_viewAttachedProof.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
	}
	
	function approveFun()
	{
	
	  try
		{
		 for(k=1; k<=10; k++) {
			if(document.getElementById('paraFrm_quantity'+k).value==''){
				if(!document.getElementById('paraFrm_tagNumber'+k).value==''){
					alert("You are not allowed to enter Tag Number for item number "+k+".");
					return false;
				}else if(!document.getElementById('paraFrm_serialNumber'+k).value==''){
					alert("You are not allowed to enter Serial Number for item number "+k+".");
					return false;
				}else if(!document.getElementById('paraFrm_cearPrice'+k).value==''){
					alert("You are not allowed to enter CEAR Price for item number "+k+".");
					return false;
				}
			  }
			}
			
		for(p=1; p<=10; p++) {
			if(!document.getElementById('paraFrm_cearPrice'+p).value==''){
				if(isNaN(document.getElementById('paraFrm_cearPrice'+p).value)){
					alert("Please enter CEAR price for item number "+ p +" in numbers only");
					return false;					
				}
			}
		}	
		
		var invoicePoNumberVar = document.getElementById('paraFrm_invoicePoNumber').value;
		if(invoicePoNumberVar !=""){
			if(isNaN(document.getElementById('paraFrm_invoicePoNumber').value)){
				alert("Please enter PO number in numbers only");
				document.getElementById('paraFrm_invoicePoNumber').focus();
				return false;
			}
		}
		
		var invoiceTotalVar = document.getElementById('paraFrm_invoiceTotal').value;
		if(invoiceTotalVar!=""){
			if(isNaN(document.getElementById('paraFrm_invoiceTotal').value)){
					alert('Please enter invoice total in numbers only');
					document.getElementById('paraFrm_invoiceTotal').focus();
					return false;			
				}
		}
		
		var assetSubTotalVar = document.getElementById('paraFrm_assetSubTotal').value;
		if(assetSubTotalVar!=""){
			if(isNaN(document.getElementById('paraFrm_assetSubTotal').value)){
					alert('Please enter asset sub-total in numbers only');
					document.getElementById('paraFrm_assetSubTotal').focus();
					return false;			
				}
		}
		 
		var actualInstallationVar = document.getElementById('paraFrm_actualInstallation').value;
		if(actualInstallationVar!=""){
			if(isNaN(document.getElementById('paraFrm_actualInstallation').value)){
					alert('Please enter actual installation cost in numbers only');
					document.getElementById('paraFrm_actualInstallation').focus();
					return false;			
				}
		}
		
		var actualMaterialVar = document.getElementById('paraFrm_actualMaterial').value;
		if(actualMaterialVar!=""){
			if(isNaN(document.getElementById('paraFrm_actualMaterial').value)){
					alert('Please enter actual material in numbers only');
					document.getElementById('paraFrm_actualMaterial').focus();
					return false;			
				}
		}
		
		var actualFreightVar = document.getElementById('paraFrm_actualFreight').value;
		if(actualFreightVar!=""){
			if(isNaN(document.getElementById('paraFrm_actualFreight').value)){
					alert('Please enter actual freight in numbers only');
					document.getElementById('paraFrm_actualFreight').focus();
					return false;			
				}
		}
		
		var actualTaxVar = document.getElementById('paraFrm_actualTax').value;
		if(actualTaxVar!=""){
			if(isNaN(document.getElementById('paraFrm_actualTax').value)){
					alert('Please enter actual tax in numbers only');
					document.getElementById('paraFrm_actualTax').focus();
					return false;			
				}
		}
			 
		}catch(e)
		{
			alert("Exception occured in approve function : "+e);
			return false;
		}
		
		
		var dateTaggedVar = trim(document.getElementById('paraFrm_dateTagged').value);
			 if(dateTaggedVar!=""){
				if(!validateDate('paraFrm_dateTagged', 'dateTagged')) {
				document.getElementById('paraFrm_dateTagged').focus();
				return false;
				}
			}
		
		if(document.getElementById('paraFrm_status').value == '_P')
		{
			document.getElementById('paraFrm_status').value = 'A';
		}else if(document.getElementById('paraFrm_status').value == '_C')
		{
			document.getElementById('paraFrm_status').value = 'X';
		}else if(document.getElementById('paraFrm_status').value == '_S')
		{
			document.getElementById('paraFrm_status').value = 'A';
		}else if(document.getElementById('paraFrm_status').value == '_F')
		{
			document.getElementById('paraFrm_status').value = 'A';
		}
	var con = confirm("Do you really want to approve this application?");
		if(con){	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action='CapitalExpenditureApproval_approveApplication.action';
	  	document.getElementById('paraFrm').submit();
	  	}		
	}
	
	
function rejectFun()
	{
		var con = confirm("Do you really want to reject this application?");
		if(con){
			if(document.getElementById('paraFrm_status').value == '_C'){
				document.getElementById('paraFrm_status').value = 'Z';	
			}else if(document.getElementById('paraFrm_status').value == '_P'){
				document.getElementById('paraFrm_status').value = 'R';	
			} else if(document.getElementById('paraFrm_status').value == '_F'){
				document.getElementById('paraFrm_status').value = 'R';	
			} else if(document.getElementById('paraFrm_status').value == '_S'){
				document.getElementById('paraFrm_status').value = 'R';	
			} 
			document.getElementById('paraFrm').target = "_self";
    		document.getElementById('paraFrm').action = 'CapitalExpenditureApproval_rejectApplication.action';
			document.getElementById('paraFrm').submit();
		}	
	}
	
	function sendbackFun()
	{
		var con = confirm("Do you really want to send back this application?");
		if(con){
			document.getElementById('paraFrm_status').value = 'B';	
			document.getElementById('paraFrm').target = "_self";
    		document.getElementById('paraFrm').action = 'CapitalExpenditureApproval_sendBackApplication.action';
			document.getElementById('paraFrm').submit();
		}	
	}
	
	function forwardFun()
	{	
		var forwardedApproverIDVar = document.getElementById('paraFrm_forwardedApproverID').value;
		if(forwardedApproverIDVar==""){
			alert("Please select next approver.");
			return false;
		}	
			
		var forwardedApproverTypeVar = document.getElementById('paraFrm_forwardedApproverType').value;
		if(forwardedApproverTypeVar=='')
		{
			alert("Please Follow the Following Reporting Structure :");
		  	document.getElementById('paraFrm_forwardedApproverType').focus();
		 	return false;		
		}	
		
		var con = confirm("Do you really want to forward this application?");
		if(con){
			document.getElementById('paraFrm_status').value = 'F';	
			document.getElementById('paraFrm').target = "_self";
    		document.getElementById('paraFrm').action = 'CapitalExpenditureApproval_forwardApplication.action';
			document.getElementById('paraFrm').submit();
		}	
	}
	
	function backtolistFun() 
	{
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'CapitalExpenditureApproval_backToList.action';
		document.getElementById('paraFrm').submit();
	}
	
	function printFun() {	
	window.print();
	}
	
	function authorizedsignoffFun() {
	totalCostVar = document.getElementById('paraFrm_costTotal').value;
	approverTypeVar = document.getElementById('paraFrm_hiddenForwardedType').value;
	if(eval(totalCostVar)>10000){
		if(approverTypeVar=="DR" || approverTypeVar=="VP" || approverTypeVar=="SVP" || approverTypeVar=="CTRL" || approverTypeVar==""){
			alert("You are not allowed to Authorized sign off for this application.\n As Total amount is greater than $10000.\n Kindly forward this application either to C.F.O. OR to C.E.O.");
			return false;		
		}
	}
	var con=confirm("Do you really want to authorized sign off this application?");
	if(con){
			document.getElementById('paraFrm_status').value = 'S';	
			document.getElementById('paraFrm').target = "_self";
      		document.getElementById('paraFrm').action = 'CapitalExpenditureApproval_authorizedSignOffApplication.action';
			document.getElementById('paraFrm').submit();
		}
	}
	
	function imposeMaxLength(Event, Object, MaxLen)
	{
        return (Object.value.length <= MaxLen)||(Event.keyCode == 8 ||Event.keyCode==46||(Event.keyCode>=35&&Event.keyCode<=40))
	}	
	function viewUploadedFile(id) {
		var uploadFileName = document.getElementById('paraFrm_'+id).value;
		
		if(uploadFileName == '') {
			alert('File not available...');
			return false;
		}
		document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = 'CapitalExpenditureApproval_viewUploadedFile.action?uploadFileName='+uploadFileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
	}	
</script>				
