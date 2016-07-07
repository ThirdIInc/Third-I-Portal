<!-- Added by manish sakpal -->

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="CapitalExpenditure" validate="true" id="paraFrm"
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
					Authorization Form</strong></td>
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
		<s:if test="ppoFlag">
			<tr>
			<td width="100%" height="22">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				
				<tr>
					<td ><label id="ppo.attachment" name="ppo.attachment"
						ondblclick="callShowDiv(this);"><%=label.get("ppo.attachment")%></label>
					:</td>
					<td width="75%" >
					<s:textfield name="ppoNumber" readonly="true" cssStyle="background-color: #F2F2F2;"/>
					<s:textfield name="ppoAttachement" 
								readonly="true" size="20" cssStyle="background-color: #F2F2F2;" />
								
					</td>
				</tr>
				<tr>
						<td> </td>
						<td><a href="#" onclick="viewUploadedFile('ppoAttachement');"><font color="blue"><u>click here to view attached file </u></font></a>		 </td>
						
						</tr>
									
						
			</table>
			</td>
			</tr>
<tr>
			
			
			</s:if>
		<!-- Approver Comments Section Begins -->
		<s:if test="approverCommentsFlag">
		<tr>
			<td>
				<table width="100%" class="formbg">
					<tr>
						<td colspan="5"><b>Approver Comments</b></td>
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
							<td class="sortableTD" align="center"><%=++approvercount%></td>
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
		  </s:if>
		<!-- Approver Comments Section Ends -->
		
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
												<br>3. For any CEAR request having Total Cost > $10000, CFO/CEO must need to approve the request.
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
						<s:hidden name="departmentID"/>
						<s:textfield name="departmentNumber" size="25" maxlength="40" readonly="true"/>
						<img id='ctrlHide' src="../pages/images/recruitment/search2.gif" height="16"
							align="absmiddle" width="17" theme="simple"
							onclick="javascript:callsF9(500,325,'CapitalExpenditure_f9DepartmentNum.action');">
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
						<s:hidden name="cityName" /> <s:hidden name="locationID" /> 
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
					<td width="25%" id="quotesReasonTextField">
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
					<td width="25%">						
						<s:textfield name="uploadFileName" size="40" readonly="true" cssStyle="background-color: #F2F2F2;" />
					</td>
					<td width="20%">
						<s:if test="%{status=='' || status=='_D' || status=='_B'}">	
							 <input type="button" value="Upload File" class="token" onclick="uploadFile('uploadFileName');" />
				 	   </s:if>
				 	</td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td width="20%">&nbsp;</td>
					<td width="25%">
						<a href="#" onclick="showRecord();"><font color="blue"><u>click here to view attached file</u></font></a>
					</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
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
		
		
<!-- Manish Sakpal Begins -->
		<!-- 
		<tr>
			<td>
			  <table width="100%" align="right" class="formbg" border="1">
				<tr>
					<td width="68%" >
						<b> Description</b><br>
						Attach Copy Of Justification Calculations, If Cost Savings Are Indicated
					</td>
					<td width="2%"></td>
					<td width="30%" ><b>Accounting Use Only</b></td>
				</tr>
				<tr>
					<td width="68%"> 
						<table width="68%" border="1" > 
							<tr>
								<td width="5%" align="center">
									<b>Item Number</b>
								</td>
								<td width="8%" align="center">
									<b><font color="red">*</font>Quantity</b>
								</td>
								<td width="20%" align="center">
									<b><font color="red">*</font>Description</b>
								</td>
								<td width="10%"  align="center" colspan="2">
									<b><font color="red">*</font>Vendor</b>
								</td>								
								<td width="10%" align="center">
									<b><font color="red">*</font>Unit Price</b>
								</td>
								<td width="10%" align="center" >
									<b>Total Cost</b>
								</td>
							</tr>
							
						<s:iterator value="detailIteratorList">						 
							<tr>
								<td align="center"></td>
								<td><input type="text" size="10" name="quantity" onkeypress="return numbersWithDot();" onchange="calculateTotalCost();"/></td>
								<td><input type="text" size="18" name="description" /></td>
								<td>
									<input type="hidden" name="vendorID" />
									<input type="text" size="10" name="vendorName"  readonly="true"/>
								</td>
								<td width="5%">
									<img id='ctrlHide' src="../pages/images/recruitment/search2.gif" height="16"
										align="absmiddle" width="17" theme="simple"
										onclick="getVendors(1,1);"></td>
								<td><input type="text" size="10" name="unitPrice" onchange="calculateTotalCost();"/></td>
								<td><input type="text" size="12" name="totalCost" readonly="true"/></td>
							</tr>
						</s:iterator>
						
						</table>
					 </td>
					 
					 <td width="2%"></td>	
					 
					 <td width="30%"> 
						<table width="30%" border="1"> 
							
							
							<tr>	
								<td width="10%" align="center"><b>Tag Number</b></td>
								<td width="10%" align="center"><b>Serial Number</b></td>
								<td width="10%" align="center"><b>CEAR Price</b></td>
							</tr>
							<s:iterator value="accountOnlyIteratorList">	
							<tr>
								<td><s:textfield size="8" name="tagNumber" /></td>
								<td><s:textfield size="8" name="serialNumber" onkeypress="return numbersWithHiphen();"/></td>
								<td><s:textfield size="8" name="cearPrice"  onkeypress="return numbersWithDot();" onchange="calculateCEARSubTotal();"/></td>
							</tr>
							</s:iterator>
						</table>
					</td>
				  </tr>
			   </table>
		    </td>
	    </tr>							
		 -->
	<!-- Manish Table End -->	
		
				
<!-- Table Entry For APPLICANT Begins -->
	<s:if test="applicantFlag">		
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
								<td align="center">1</td>
								<td align="center"><s:textfield size= "7" name="quantity1" maxlength="20" onkeypress="return numbersOnly();" onchange="calculateTotalCost();"/></td>
								<td align="center"><s:textfield size= "25" name="description1" maxlength="100"/></td>
								<td colspan="2" align="center"><s:hidden name="vendorID1"/>
									<s:textfield  size= "13" name="vendorName1" maxlength="20"/></td>
								<td align="center"><s:textfield size= "10" name="unitPrice1" maxlength="20" onkeypress="return numbersWithDot();" onchange="calculateTotalCost();"/></td>
								<td align="center"><s:textfield size= "10" name="totalCost1"  readonly="true"/></td>
							</tr>
							<tr>
								<td align="center">2</td>
								<td align="center"><s:textfield size= "7" name="quantity2" maxlength="20" onkeypress="return numbersOnly();" onchange="calculateTotalCost();"/></td>
								<td align="center"><s:textfield size= "25" name="description2" maxlength="100"/></td>
								<td colspan="2" align="center"><s:hidden name="vendorID2"/>
									<s:textfield  size= "13" name="vendorName2" maxlength="20"/></td>
								<td align="center"><s:textfield size= "10" name="unitPrice2" maxlength="20" onkeypress="return numbersWithDot();" onchange="calculateTotalCost();"/></td>
								<td align="center"><s:textfield size= "10" name="totalCost2"  readonly="true"/></td>
							</tr>
							<tr>
								<td align="center">3</td>
								<td align="center"><s:textfield size= "7" name="quantity3" maxlength="20" onkeypress="return numbersOnly();" onchange="calculateTotalCost();"/></td>
								<td align="center"><s:textfield size= "25" name="description3" maxlength="100"/></td>
								<td colspan="2" align="center"><s:hidden name="vendorID3"/>
									<s:textfield size= "13" name="vendorName3" maxlength="20"/></td>
								<td align="center"><s:textfield size= "10" name="unitPrice3" maxlength="20" onkeypress="return numbersWithDot();" onchange="calculateTotalCost();"/></td>
								<td align="center"><s:textfield size= "10" name="totalCost3"  readonly="true"/></td>
							</tr>
							<tr>
								<td align="center">4</td>
								<td align="center"><s:textfield size= "7" name="quantity4" maxlength="20" onkeypress="return numbersOnly();" onchange="calculateTotalCost();"/></td>
								<td align="center"><s:textfield size= "25" name="description4" maxlength="100"/></td>
								<td colspan="2" align="center"><s:hidden name="vendorID4"/>
									<s:textfield size= "13" name="vendorName4" maxlength="20"/></td>
								<td align="center"><s:textfield size= "10" name="unitPrice4" maxlength="20" onkeypress="return numbersWithDot();" onchange="calculateTotalCost();"/></td>
								<td align="center"><s:textfield size= "10" name="totalCost4"  readonly="true"/></td>
							</tr>
							<tr>
								<td align="center">5</td>
								<td align="center"><s:textfield size= "7" name="quantity5" maxlength="20" onkeypress="return numbersOnly();" onchange="calculateTotalCost();"/></td>
								<td align="center"><s:textfield size= "25"  name="description5" maxlength="100"/></td>
								<td colspan="2" align="center"><s:hidden name="vendorID5"/>
									<s:textfield size= "13" name="vendorName5" maxlength="20"/></td>
								<td align="center"><s:textfield size= "10" name="unitPrice5" maxlength="20" onkeypress="return numbersWithDot();" onchange="calculateTotalCost();"/></td>
								<td align="center"><s:textfield size= "10" name="totalCost5"  readonly="true"/></td>
							</tr>
							<tr>
								<td align="center">6</td>
								<td align="center"><s:textfield size= "7" name="quantity6" maxlength="20" onkeypress="return numbersOnly();" onchange="calculateTotalCost();"/></td>
								<td align="center"><s:textfield size= "25" name="description6" maxlength="100"/></td>
								<td colspan="2" align="center"><s:hidden name="vendorID6"/>
									<s:textfield size= "13" name="vendorName6" maxlength="20"/></td>
								<td align="center"><s:textfield size= "10" name="unitPrice6" maxlength="20" onkeypress="return numbersWithDot();" onchange="calculateTotalCost();"/></td>
								<td align="center"><s:textfield size= "10" name="totalCost6"  readonly="true"/></td>
							</tr>
							<tr>
								<td align="center">7</td>
								<td align="center"><s:textfield size= "7" name="quantity7" maxlength="20" onkeypress="return numbersOnly();" onchange="calculateTotalCost();"/></td>
								<td align="center"><s:textfield size= "25" name="description7" maxlength="100"/></td>
								<td colspan="2" align="center"><s:hidden name="vendorID7"/>
									<s:textfield size= "13" name="vendorName7" maxlength="20"/></td>
								<td align="center"><s:textfield size= "10" name="unitPrice7" maxlength="20" onkeypress="return numbersWithDot();" onchange="calculateTotalCost();"/></td>
								<td align="center"><s:textfield size= "10" name="totalCost7"  readonly="true"/></td>
							</tr>
							<tr>
								<td align="center">8</td>
								<td align="center"><s:textfield size= "7" name="quantity8" maxlength="20" onkeypress="return numbersOnly();" onchange="calculateTotalCost();"/></td>
								<td align="center"><s:textfield size= "25" name="description8" maxlength="100"/></td>
								<td colspan="2" align="center"><s:hidden name="vendorID8"/>
									<s:textfield size= "13" name="vendorName8" maxlength="20"/></td>
								<td align="center"><s:textfield size= "10" name="unitPrice8" maxlength="20" onkeypress="return numbersWithDot();" onchange="calculateTotalCost();"/></td>
								<td align="center"><s:textfield size= "10" name="totalCost8"  readonly="true"/></td>
							</tr>
							<tr>
								<td align="center">9</td>
								<td align="center"><s:textfield size= "7" name="quantity9" maxlength="20" onkeypress="return numbersOnly();" onchange="calculateTotalCost();"/></td>
								<td align="center"><s:textfield size= "25" name="description9" maxlength="100"/></td>
								<td colspan="2" align="center"><s:hidden name="vendorID9"/>
									<s:textfield size= "13" name="vendorName9" maxlength="20"/></td>
								<td align="center"><s:textfield size= "10" name="unitPrice9" maxlength="20" onkeypress="return numbersWithDot();" onchange="calculateTotalCost();"/></td>
								<td align="center"><s:textfield size= "10" name="totalCost9"  readonly="true"/></td>
							</tr>
							<tr>
								<td align="center">10</td>
								<td align="center"><s:textfield size= "7" name="quantity10" maxlength="20" onkeypress="return numbersOnly();" onchange="calculateTotalCost();"/></td>
								<td align="center"><s:textfield size= "25" name="description10" maxlength="100"/></td>
								<td colspan="2" align="center"><s:hidden name="vendorID10"/>
									<s:textfield size= "13" name="vendorName10" maxlength="20"/></td>
								<td align="center"><s:textfield size= "10" name="unitPrice10" maxlength="20" onkeypress="return numbersWithDot();" onchange="calculateTotalCost();"/></td>
								<td align="center"><s:textfield size= "10" name="totalCost10"  readonly="true"/></td>
							</tr>
							<tr>
								<td align="right" colspan="5"><b>Sub-Total : </b></td>
								<td align="center"><s:textfield size= "10" name="subTotalUnitPrice" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
								<td align="center"><s:textfield size= "10" name="subTotalTotalCost" readonly="true" cssStyle="background-color: #F2F2F2;"/></td>
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
<!-- Table Entry For APPLICANT Ends -->	
		
<!-- Table Entry For ACCOUNTANT Begins -->
	<s:if test="accountantFlag">		
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
<!-- Table Entry For ACCOUNTANT Ends -->	

<!-- Calculation Part For APPLICANT Starts -->
	<s:if test="applicantFlag">	
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
					<td class="formtext" width="15%">
						<label name="costSubTotal"	id="costSubTotal" ondblclick="callShowDiv(this);"> <%=label.get("costSubTotal")%>
						</label> :
					</td>					
					<td height="22" width="25%">						
						<s:textfield name="costSubTotal" size="20" readonly="true" cssStyle="background-color: #F2F2F2;" onchange="javascript:calculateCostBreakDownTotal()"/>	
					</td>
					
					<td class="formtext" width="15%" >
						<label name="invoicePoNumber" id="invoicePoNumber" ondblclick="callShowDiv(this);"> <%=label.get("invoicePoNumber")%>
						</label> :
					</td>
					
					<td height="22" width="25%">						
						<s:textfield name="invoicePoNumber" size="20" maxlength="15" readonly="true" cssStyle="background-color: #F2F2F2;"/>	
					</td>
					
					<td class="formtext" width="15%">
						<label name="assetSubTotal"	id="assetSubTotal" ondblclick="callShowDiv(this);"> <%=label.get("assetSubTotal")%>
						</label> :
					</td>					
					<td height="22" width="25%">						
						<s:textfield name="assetSubTotal" maxlength="15" size="20" maxlength="15" readonly="true" cssStyle="background-color: #F2F2F2;"/>	
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="15%">
						<label name="costInstallation"	id="costInstallation" ondblclick="callShowDiv(this);"> <%=label.get("costInstallation")%>
						</label> :
					</td>					
					<td height="22" width="25%">						
						<s:textfield name="costInstallation" maxlength="15" size="20" onkeypress="return numbersWithDot();" onchange="javascript:calculateCostBreakDownTotal()"/>	
					</td>
					
					<td class="formtext" width="15%" >
						<label name="invoiceVendorNumber" id="invoiceVendorNumber" ondblclick="callShowDiv(this);"> <%=label.get("invoiceVendorNumber")%>
						</label> :
					</td>
					<td height="22" width="25%">	
						<s:hidden name="invoiceVendorID" />					
						<s:textfield name="invoiceVendorNumber" size="20" readonly="true" cssStyle="background-color: #F2F2F2;"/>
					</td>
					
					<td class="formtext" width="15%">
						<label name="actualInstallation"	id="actualInstallation" ondblclick="callShowDiv(this);"> <%=label.get("actualInstallation")%>
						</label> :
					</td>					
					<td height="22" width="25%">						
						<s:textfield name="actualInstallation" size="20"  maxlength="15" readonly="true" cssStyle="background-color: #F2F2F2;"/>	
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="15%">
						<label name="costMaterial"	id="costMaterial" ondblclick="callShowDiv(this);"> <%=label.get("costMaterial")%>
						</label> :
					</td>					
					<td height="22" width="25%">						
						<s:textfield name="costMaterial" maxlength="15" size="20" onkeypress="return numbersWithDot();" onchange="javascript:calculateCostBreakDownTotal()"/>	
					</td>
					
					<td class="formtext" width="15%" >
						<label name="invoiceTotal" id="invoiceTotal" ondblclick="callShowDiv(this);"> <%=label.get("invoiceTotal")%>
						</label> :
					</td>
					<td height="22" width="25%">						
						<s:textfield name="invoiceTotal" size="20" maxlength="15" readonly="true" cssStyle="background-color: #F2F2F2;"/>	
					</td>
					
					<td class="formtext" width="15%">
						<label name="actualMaterial"	id="actualMaterial" ondblclick="callShowDiv(this);"> <%=label.get("actualMaterial")%>
						</label> :
					</td>					
					<td height="22" width="25%">						
						<s:textfield name="actualMaterial" size="20" maxlength="15" readonly="true" cssStyle="background-color: #F2F2F2;"/>	
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="15%">
						<label name="costFreight"	id="costFreight" ondblclick="callShowDiv(this);"> <%=label.get("costFreight")%>
						</label> :
					</td>					
					<td height="22" width="25%">						
						<s:textfield name="costFreight" size="20"  maxlength="15" onkeypress="return numbersWithDot();" onchange="javascript:calculateCostBreakDownTotal()"/>	
					</td>
					
					<td class="formtext" width="15%" >&nbsp;</td>
					<td height="22" width="25%">&nbsp;</td>
					
					<td class="formtext" width="15%">
						<label name="actualFreight"	id="actualFreight" ondblclick="callShowDiv(this);"> <%=label.get("actualFreight")%>
						</label> :
					</td>					
					<td height="22" width="25%">						
						<s:textfield name="actualFreight" size="20" maxlength="15" readonly="true" cssStyle="background-color: #F2F2F2;"/>	
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="15%">
						<label name="costTax"	id="costTax" ondblclick="callShowDiv(this);"> <%=label.get("costTax")%>
						</label> :
					</td>					
					<td height="22" width="25%">						
						<s:textfield name="costTax" size="20"  maxlength="15" onkeypress="return numbersWithDot();" onchange="javascript:calculateCostBreakDownTotal()"/>	
					</td>
					
					<td class="formtext" width="15%" >
						<label name="dateTagged" id="dateTagged" ondblclick="callShowDiv(this);"> <%=label.get("dateTagged")%>
						</label> :
					</td>
					<td height="22" width="25%">						
						<s:textfield name="dateTagged" size="20" readonly="true" cssStyle="background-color: #F2F2F2;"/>							
					</td>
					
					<td class="formtext" width="15%">
						<label name="actualTax"	id="actualTax" ondblclick="callShowDiv(this);"> <%=label.get("actualTax")%>
						</label> :
					</td>					
					<td height="22" width="25%">						
						<s:textfield name="actualTax" size="20" maxlength="15"  readonly="true" cssStyle="background-color: #F2F2F2;"/>	
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="15%">
						<label name="costTotal"	id="costTotal" ondblclick="callShowDiv(this);"> <%=label.get("costTotal")%>
						</label> :
					</td>					
					<td height="22" width="25%">						
						<s:textfield name="costTotal" size="20"  readonly="true" cssStyle="background-color: #F2F2F2;"/>	
					</td>
					
					<td class="formtext" width="15%" >&nbsp;</td>
					<td height="22" width="25%">&nbsp;</td>
					
					<td class="formtext" width="15%">
						<label name="actualTotal"	id="actualTotal" ondblclick="callShowDiv(this);"> <%=label.get("actualTotal")%>
						</label> :
					</td>					
					<td height="22" width="25%">						
						<s:textfield name="actualTotal" size="20" readonly="true" cssStyle="background-color: #F2F2F2;"/>	
					</td>
				</tr>
			</table>
		   </td>
		 </tr>  
		</s:if> 
<!-- Calculation Part APPLICANT Finish -->

<!-- Calculation Part For ACCOUNTANTS Begins -->
		<s:if test="accountantFlag">
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
					<td class="formtext" width="15%">
						<label name="costSubTotal"	id="costSubTotal" ondblclick="callShowDiv(this);"> <%=label.get("costSubTotal")%>
						</label> :
					</td>					
					<td height="22" width="25%">						
						<s:textfield name="costSubTotal" size="20" readonly="true" cssStyle="background-color: #F2F2F2;" />	
					</td>
					
					<td class="formtext" width="15%" >
						<label name="invoicePoNumber" id="invoicePoNumber" ondblclick="callShowDiv(this);"> <%=label.get("invoicePoNumber")%>
						</label> :
					</td>
					
					<td height="22" width="25%">						
						<s:textfield name="invoicePoNumber" size="20" maxlength="15" readonly="true" cssStyle="background-color: #F2F2F2;"/>	
					</td>
					
					<td class="formtext" width="15%">
						<label name="assetSubTotal"	id="assetSubTotal" ondblclick="callShowDiv(this);"> <%=label.get("assetSubTotal")%>
						</label> :
					</td>					
					<td height="22" width="25%">						
						<s:textfield name="assetSubTotal" maxlength="15" size="20" maxlength="15" readonly="true" cssStyle="background-color: #F2F2F2;"/>	
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="15%">
						<label name="costInstallation"	id="costInstallation" ondblclick="callShowDiv(this);"> <%=label.get("costInstallation")%>
						</label> :
					</td>					
					<td height="22" width="25%">						
						<s:textfield name="costInstallation" maxlength="15" size="20" readonly="true" cssStyle="background-color: #F2F2F2;"/>	
					</td>
					
					<td class="formtext" width="15%" >
						<label name="invoiceVendorNumber" id="invoiceVendorNumber" ondblclick="callShowDiv(this);"> <%=label.get("invoiceVendorNumber")%>
						</label> :
					</td>
					<td height="22" width="25%">	
						<s:hidden name="invoiceVendorID" />					
						<s:textfield name="invoiceVendorNumber" size="20" readonly="true" cssStyle="background-color: #F2F2F2;"/>
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
					<td height="22" width="25%">						
						<s:textfield name="actualInstallation" size="20"  maxlength="15" readonly="true" cssStyle="background-color: #F2F2F2;"/>	
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="15%">
						<label name="costMaterial"	id="costMaterial" ondblclick="callShowDiv(this);"> <%=label.get("costMaterial")%>
						</label> :
					</td>					
					<td height="22" width="25%">						
						<s:textfield name="costMaterial" maxlength="15" size="20" readonly="true" cssStyle="background-color: #F2F2F2;"/>	
					</td>
					
					<td class="formtext" width="15%" >
						<label name="invoiceTotal" id="invoiceTotal" ondblclick="callShowDiv(this);"> <%=label.get("invoiceTotal")%>
						</label> :
					</td>
					<td height="22" width="25%">						
						<s:textfield name="invoiceTotal" size="20" maxlength="15" readonly="true" cssStyle="background-color: #F2F2F2;"/>	
					</td>
					
					<td class="formtext" width="15%">
						<label name="actualMaterial"	id="actualMaterial" ondblclick="callShowDiv(this);"> <%=label.get("actualMaterial")%>
						</label> :
					</td>					
					<td height="22" width="25%">						
						<s:textfield name="actualMaterial" size="20" maxlength="15"  readonly="true" cssStyle="background-color: #F2F2F2;"/>	
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="15%">
						<label name="costFreight"	id="costFreight" ondblclick="callShowDiv(this);"> <%=label.get("costFreight")%>
						</label> :
					</td>					
					<td height="22" width="25%">						
						<s:textfield name="costFreight" size="20"  maxlength="15" readonly="true" cssStyle="background-color: #F2F2F2;"/>	
					</td>
					
					<td class="formtext" width="15%" >&nbsp;</td>
					<td height="22" width="25%">&nbsp;</td>
					
					<td class="formtext" width="15%">
						<label name="actualFreight"	id="actualFreight" ondblclick="callShowDiv(this);"> <%=label.get("actualFreight")%>
						</label> :
					</td>					
					<td height="22" width="25%">						
						<s:textfield name="actualFreight" size="20" maxlength="15" readonly="true" cssStyle="background-color: #F2F2F2;"/>	
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="15%">
						<label name="costTax"	id="costTax" ondblclick="callShowDiv(this);"> <%=label.get("costTax")%>
						</label> :
					</td>					
					<td height="22" width="25%">						
						<s:textfield name="costTax" size="20"  maxlength="15" readonly="true" cssStyle="background-color: #F2F2F2;"/>	
					</td>
					
					<td class="formtext" width="15%" >
						<label name="dateTagged" id="dateTagged" ondblclick="callShowDiv(this);"> <%=label.get("dateTagged")%>
						</label> :
					</td>
					<td height="22" width="25%">						
						<s:textfield name="dateTagged" size="20"/>
						<a href="javascript:NewCal('paraFrm_dateTagged','DDMMYYYY');">
							<img id='ctrlHide' src="../pages/images/Date.gif" class="iconImage"	height="16" align="absmiddle" width="16"> 
						</a>	
					</td>
					
					<td class="formtext" width="15%">
						<label name="actualTax"	id="actualTax" ondblclick="callShowDiv(this);"> <%=label.get("actualTax")%>
						</label> :
					</td>					
					<td height="22" width="25%">						
						<s:textfield name="actualTax" size="20" maxlength="15" readonly="true" cssStyle="background-color: #F2F2F2;"/>	
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="15%">
						<label name="costTotal"	id="costTotal" ondblclick="callShowDiv(this);"> <%=label.get("costTotal")%>
						</label> :
					</td>					
					<td height="22" width="25%">						
						<s:textfield name="costTotal" size="20"  readonly="true" cssStyle="background-color: #F2F2F2;"/>	
					</td>
					
					<td class="formtext" width="15%" >&nbsp;</td>
					<td height="22" width="25%">&nbsp;</td>
					
					<td class="formtext" width="15%">
						<label name="actualTotal"	id="actualTotal" ondblclick="callShowDiv(this);"> <%=label.get("actualTotal")%>
						</label> :
					</td>					
					<td height="22" width="25%">						
						<s:textfield name="actualTotal" size="20" readonly="true" cssStyle="background-color: #F2F2F2;"/>	
					</td>
				</tr>
			</table>
		   </td>
		 </tr>  
	   </s:if>
<!-- Calculation Part For ACCOUNTANTS Ends -->

<!-- Approval Section Begins -->
		<tr>
			<td>
			  <table width="100%" align="right" class="formbg">
				<tr>
					<td colspan="4" class="formtext">
						<b> Application Approvals </b>
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="25%">
						Forward To Manager : 
					</td>					
					<td height="22" colspan="3">
						<s:hidden name="forwardedManagerID" />
						<s:textfield name="forwardedManagerToken" size="20" readonly="true" cssStyle="background-color: #F2F2F2;"/>						
						<s:textfield name="forwardedManagerName" size="60"  readonly="true" cssStyle="background-color: #F2F2F2;"/>
							<img id='ctrlHide' src="../pages/images/recruitment/search2.gif" height="16"
							align="absmiddle" width="17" theme="simple"
							onclick="javascript:callsF9(500,325,'CapitalExpenditure_f9Approver.action');">
					</td>
				</tr>
			 </table>
		  </td>
		</tr>  
<!-- Approval Section Ends --> 
								
	 <tr>
		 <td>
				<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
		 </td>
	 </tr>
		
		<s:hidden name="dataPath"   />
		<s:hidden name="capitalExpID" />		
		<s:hidden name="trackingNumber" />
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
			var quotesOptionVar = document.getElementById('quotesAttachedRadioOptionValue').value;
			if(quotesOptionVar == 'no'){
					document.getElementById('quotesReasonTextLabel').style.display = '';
					document.getElementById('quotesReasonTextField').style.display = '';			
			}else {
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

	function CEARTypeCheckBox(){
		var localPurchaseCheckboxVar = document.getElementById('paraFrm_localPurchaseCheckbox').checked;
		if(localPurchaseCheckboxVar == false)
		{
			document.getElementById('showLocalPurcahseReasonLabel').style.display = 'none';
			document.getElementById('showLocalPurcahseReasonField').style.display = 'none';
			document.getElementById('paraFrm_reasonForLocalPurchase').value='';
		//	document.getElementById('paraFrm_reasonForLocalPurchase').readOnly='true';	
		//	document.getElementById('paraFrm_reasonForLocalPurchase').style.background='#F2F2F2'; 
		}
		else {
			document.getElementById('showLocalPurcahseReasonLabel').style.display = '';
			document.getElementById('showLocalPurcahseReasonField').style.display = '';
		//	document.getElementById('paraFrm_reasonForLocalPurchase').readOnly='';	
		//	document.getElementById('paraFrm_reasonForLocalPurchase').style.background='white'; 
		}
		
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
			totalCost = Math.round(totalCost*Math.pow(10,2))/Math.pow(10,2);
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
		unitPrice = Math.round(unitPrice*Math.pow(10,2))/Math.pow(10,2);
		document.getElementById('paraFrm_subTotalUnitPrice').value = unitPrice;
		
		totalCost = Math.round(totalCost*Math.pow(10,2))/Math.pow(10,2);
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
				subTotalCEAR = eval(subTotalCEAR)+eval(document.getElementById('paraFrm_cearPrice'+m).value);
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
			total=parseFloat(document.getElementById('paraFrm_costSubTotal').value) +
		  	  parseFloat(document.getElementById('paraFrm_costInstallation').value) +
		  	  parseFloat(document.getElementById('paraFrm_costMaterial').value)+
		      parseFloat(document.getElementById('paraFrm_costFreight').value) +
		  	  parseFloat(document.getElementById('paraFrm_costTax').value) ;

			total = Math.round(total*Math.pow(10,2))/Math.pow(10,2);
		
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
		if(statusVar=='_A' || statusVar=='_R' || statusVar=='_P'){
			if(uploadFileName == '') {
			alert('File is not attached.');
			return false;
		  }
		}else if(uploadFileName == '') {
			alert('Please first upload the file...');
			return false;
		}
		document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = "CapitalExpenditure_viewAttachedProof.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
	}
	
	
	
	function setQuotesAttached(id){
		var opt = document.getElementById('quotesAttachedRadioOptionValue').value =id.value;
		var quotesValue = document.getElementById('quotesAttachedRadioOptionValue').value;
		if(quotesValue=='yes')
		{
			document.getElementById('quotesReasonTextLabel').style.display = 'none';
			document.getElementById('quotesReasonTextField').style.display = 'none';
			
			//document.getElementById('paraFrm_quotesReason').readOnly='true';	
			//document.getElementById('paraFrm_quotesReason').style.background='#F2F2F2'; 
			document.getElementById('paraFrm_quotesReason').value = '';
		}else {
			document.getElementById('quotesReasonTextLabel').style.display = '';
			document.getElementById('quotesReasonTextField').style.display = '';
			
			//document.getElementById('paraFrm_quotesReason').readOnly ='';
			//document.getElementById('paraFrm_quotesReason').style.background='white'; 
		}
	}
	

	function draftFun()
	{	
		try
		{
			var originalCheckboxVar = document.getElementById('paraFrm_originalCheckbox').checked;
			var purchaseDeptCheckboxVar = document.getElementById('paraFrm_purchaseDeptCheckbox').checked;
			var computerITCheckboxVar = document.getElementById('paraFrm_computerITCheckbox').checked;
			var localPurchaseCheckboxVar = document.getElementById('paraFrm_localPurchaseCheckbox').checked;
			if((originalCheckboxVar == false) && (purchaseDeptCheckboxVar == false) && (computerITCheckboxVar == false) && (localPurchaseCheckboxVar == false))
			{
				alert("Please Select Request Type.");
				return false;
			}
			
			var businessJustification = trim(document.getElementById('paraFrm_businessJustification').value);
			if(businessJustification==""){
				alert("Please enter "+document.getElementById('businessJustification').innerHTML.toLowerCase()+".");
				document.getElementById('paraFrm_businessJustification').focus();
				return false;
			}
			
			var dateRequired = trim(document.getElementById('paraFrm_dateRequired').value);
			if(dateRequired==""){
				alert("Please enter "+document.getElementById('dateRequired').innerHTML.toLowerCase()+".");
				document.getElementById('paraFrm_dateRequired').focus();
				return false;
			}
			
			 if(dateRequired!=""){
				if(!validateDate('paraFrm_dateRequired', 'dateRequired')) {
				document.getElementById('paraFrm_dateRequired').focus();
				return false;
				}
			}
			
		   	var locationVar = trim(document.getElementById('paraFrm_location').value);
		   	var ifOtherLocationVar = trim(document.getElementById('paraFrm_ifOtherLocation').value);
		   	
		   	if(locationVar=="" && ifOtherLocationVar==""){
		   		alert("Please select "+document.getElementById('location').innerHTML.toLowerCase()+".");
		   		document.getElementById('paraFrm_location').focus();
		   		return false;
		   	}
			
			var shipToCompanyVar = document.getElementById('paraFrm_shipToCompany').value;
			if(shipToCompanyVar == ""){
				alert("Please enter "+document.getElementById('shipToCompany').innerHTML.toLowerCase()+".");
		   		document.getElementById('paraFrm_shipToCompany').focus();
		   		return false;
			}
			
			var cityVar = document.getElementById('paraFrm_city').value;
			if(cityVar == ""){
				alert("Please enter "+document.getElementById('city').innerHTML.toLowerCase()+".");
		   		document.getElementById('paraFrm_city').focus();
		   		return false;
			}
			
			var stateVar = document.getElementById('paraFrm_state').value;
			if(stateVar == ""){
				alert("Please enter "+document.getElementById('state').innerHTML.toLowerCase()+".");
		   		document.getElementById('paraFrm_state').focus();
		   		return false;
			}
			
			var streetAddressVar = document.getElementById('paraFrm_streetAddress').value;
			if(streetAddressVar == ""){
				alert("Please enter "+document.getElementById('streetAddress').innerHTML.toLowerCase()+".");
		   		document.getElementById('paraFrm_streetAddress').focus();
		   		return false;
			}
			
			var zipCodeVar = document.getElementById('paraFrm_zipCode').value;
			if(zipCodeVar == ""){
				alert("Please enter "+document.getElementById('zipCode').innerHTML.toLowerCase()+".");
		   		document.getElementById('paraFrm_zipCode').focus();
		   		return false;
			}
			
			var attentionVar = document.getElementById('paraFrm_attention').value;
			if(attentionVar == ""){
				alert("Please enter "+document.getElementById('attention').innerHTML.toLowerCase()+".");
		   		document.getElementById('paraFrm_attention').focus();
		   		return false;
			}
			
			var telePhoneNumberVar = document.getElementById('paraFrm_telePhoneNumber').value;
			if(telePhoneNumberVar == ""){
				alert("Please enter "+document.getElementById('telePhoneNumber').innerHTML.toLowerCase()+".");
		   		document.getElementById('paraFrm_telePhoneNumber').focus();
		   		return false;
			}

			
			var quotesAttachedRadioOptionValueVar = trim(document.getElementById('quotesAttachedRadioOptionValue').value);
			if(quotesAttachedRadioOptionValueVar==""){
				alert("Please select "+document.getElementById('quotesAttached').innerHTML.toLowerCase()+".");
				return false;	
			}else if(quotesAttachedRadioOptionValueVar =="yes"){
				var uploadFileNameVar = trim(document.getElementById('paraFrm_uploadFileName').value);
				if(uploadFileNameVar == ""){
					alert("Please upload attachment.");
					return false;
				}
			}	
			
//Validation for Table Entries Begins 
		if(document.getElementById('paraFrm_quantity1').value==''){
				alert("Please enter quantity for item number1.");
				return false;
			}
			
			if(!document.getElementById('paraFrm_quantity1').value==''){
				
				if(isNaN(document.getElementById('paraFrm_quantity1').value)){
					alert('Please enter quantity in numbers only');
					document.getElementById('paraFrm_quantity1').focus();
					return false;			
				}else{
					if(document.getElementById('paraFrm_description1').value==''){
						alert("Please enter description for item number1.");
						return false;
					}else if(document.getElementById('paraFrm_vendorName1').value==''){
						alert("Please select vendor for item number1.");
						return false;
					}else if(document.getElementById('paraFrm_unitPrice1').value==''){
						alert("Please enter unit price for item number1.");
						return false;
					}
				}
			 }
			 
			 if(!document.getElementById('paraFrm_unitPrice1').value==''){
				if(isNaN(document.getElementById('paraFrm_unitPrice1').value)){
					alert('Please enter unit price for item number 1 in numbers only');
					document.getElementById('paraFrm_unitPrice1').focus();
					return false;			
				}
			}
			 
			
			for(k=1; k<=10; k++) {
			if(!document.getElementById('paraFrm_quantity'+k).value==''){
				if(isNaN(document.getElementById('paraFrm_quantity'+k).value)){
					alert('Please enter quantity for item number'+k+'in numbers only');
					document.getElementById('paraFrm_quantity'+k).focus();
					return false;			
				}else{
					if(document.getElementById('paraFrm_description'+k).value==''){
						alert("Please enter description for item number "+ k +".");
						return false;
					}else if(document.getElementById('paraFrm_vendorName'+k).value==''){
						alert("Please select vendor for item number "+ k +".");
						return false;
					}else if(document.getElementById('paraFrm_unitPrice'+k).value==''){
						alert("Please enter unit price for item number "+ k +".");
						return false;
					}
				}
			 }
		  } 
		
		for(s=1; s<=10; s++) {
			if(!document.getElementById('paraFrm_unitPrice'+s).value==''){
				if(isNaN(document.getElementById('paraFrm_unitPrice'+s).value)){
					alert('Please enter unit price for item number '+ s +' in numbers only');
					document.getElementById('paraFrm_unitPrice'+s).focus();
					return false;			
				}
			}	
		}	
//Validation for Table Entries Ends	
		
		var costInstallationVar = trim(document.getElementById('paraFrm_costInstallation').value);
		if(costInstallationVar!=""){
			if(isNaN(document.getElementById('paraFrm_costInstallation').value)){
					alert('Please enter cost breakdown installation cost in numbers only');
					document.getElementById('paraFrm_costInstallation').focus();
					return false;			
				}
		}
		
		var costMaterialVar = document.getElementById('paraFrm_costMaterial').value;
		if(costMaterialVar!=""){
			if(isNaN(document.getElementById('paraFrm_costMaterial').value)){
					alert('Please enter cost breakdown materials in numbers only');
					document.getElementById('paraFrm_costMaterial').focus();
					return false;			
				}
		}
		
     	var costFreightVar = document.getElementById('paraFrm_costFreight').value;
     	if(costFreightVar!=""){
			if(isNaN(document.getElementById('paraFrm_costFreight').value)){
					alert('Please enter cost breakdown freight in numbers only');
					document.getElementById('paraFrm_costFreight').focus();
					return false;			
				}
		}
		
		var costTaxVar = document.getElementById('paraFrm_costTax').value;
		if(costTaxVar!=""){
			if(isNaN(document.getElementById('paraFrm_costTax').value)){
					alert('Please enter cost breakdown tax in numbers only');
					document.getElementById('paraFrm_costTax').focus();
					return false;			
				}
		}
			
		var forwardedManagerIDVar = document.getElementById('paraFrm_forwardedManagerID').value;
			if(forwardedManagerIDVar==""){
			alert("Please select approver.");
			return false;
			}	
				
		}catch(e)
		{
			alert("Exception occured in draft function : "+e);
		}
		document.getElementById('paraFrm_status').value = 'D';	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action='CapitalExpenditure_draftFunction.action';
	  	document.getElementById('paraFrm').submit();		
	}
	
	
	function sendforapprovalFun()
	{	
		try
		{
			var originalCheckboxVar = document.getElementById('paraFrm_originalCheckbox').checked;
			var purchaseDeptCheckboxVar = document.getElementById('paraFrm_purchaseDeptCheckbox').checked;
			var computerITCheckboxVar = document.getElementById('paraFrm_computerITCheckbox').checked;
			var localPurchaseCheckboxVar = document.getElementById('paraFrm_localPurchaseCheckbox').checked;
			if((originalCheckboxVar == false) && (purchaseDeptCheckboxVar == false) && (computerITCheckboxVar == false) && (localPurchaseCheckboxVar == false))
			{
				alert("Please Select Request Type.");
				return false;
			}
			
			var businessJustification = trim(document.getElementById('paraFrm_businessJustification').value);
			if(businessJustification==""){
				alert("Please enter "+document.getElementById('businessJustification').innerHTML.toLowerCase()+".");
				document.getElementById('paraFrm_businessJustification').focus();
				return false;
			}
			
			var dateRequired = trim(document.getElementById('paraFrm_dateRequired').value);
			if(dateRequired==""){
				alert("Please enter "+document.getElementById('dateRequired').innerHTML.toLowerCase()+".");
				document.getElementById('paraFrm_dateRequired').focus();
				return false;
			}
			
			 if(dateRequired!=""){
				if(!validateDate('paraFrm_dateRequired', 'dateRequired')) {
				document.getElementById('paraFrm_dateRequired').focus();
				return false;
				}
			}
			
		   		var locationVar = trim(document.getElementById('paraFrm_location').value);
		   	var ifOtherLocationVar = trim(document.getElementById('paraFrm_ifOtherLocation').value);
		   	
		   	if(locationVar=="" && ifOtherLocationVar==""){
		   		alert("Please select "+document.getElementById('location').innerHTML.toLowerCase()+".");
		   		document.getElementById('paraFrm_location').focus();
		   		return false;
		   	}
			
			var shipToCompanyVar = document.getElementById('paraFrm_shipToCompany').value;
			if(shipToCompanyVar == ""){
				alert("Please enter "+document.getElementById('shipToCompany').innerHTML.toLowerCase()+".");
		   		document.getElementById('paraFrm_shipToCompany').focus();
		   		return false;
			}
			
			var cityVar = document.getElementById('paraFrm_city').value;
			if(cityVar == ""){
				alert("Please enter "+document.getElementById('city').innerHTML.toLowerCase()+".");
		   		document.getElementById('paraFrm_city').focus();
		   		return false;
			}
			
			var stateVar = document.getElementById('paraFrm_state').value;
			if(stateVar == ""){
				alert("Please enter "+document.getElementById('state').innerHTML.toLowerCase()+".");
		   		document.getElementById('paraFrm_state').focus();
		   		return false;
			}
			
			var streetAddressVar = document.getElementById('paraFrm_streetAddress').value;
			if(streetAddressVar == ""){
				alert("Please enter "+document.getElementById('streetAddress').innerHTML.toLowerCase()+".");
		   		document.getElementById('paraFrm_streetAddress').focus();
		   		return false;
			}
			
			var zipCodeVar = document.getElementById('paraFrm_zipCode').value;
			if(zipCodeVar == ""){
				alert("Please enter "+document.getElementById('zipCode').innerHTML.toLowerCase()+".");
		   		document.getElementById('paraFrm_zipCode').focus();
		   		return false;
			}
			
			var attentionVar = document.getElementById('paraFrm_attention').value;
			if(attentionVar == ""){
				alert("Please enter "+document.getElementById('attention').innerHTML.toLowerCase()+".");
		   		document.getElementById('paraFrm_attention').focus();
		   		return false;
			}
			
			var telePhoneNumberVar = document.getElementById('paraFrm_telePhoneNumber').value;
			if(telePhoneNumberVar == ""){
				alert("Please enter "+document.getElementById('telePhoneNumber').innerHTML.toLowerCase()+".");
		   		document.getElementById('paraFrm_telePhoneNumber').focus();
		   		return false;
			}
			
			var quotesAttachedRadioOptionValueVar = trim(document.getElementById('quotesAttachedRadioOptionValue').value);
			if(quotesAttachedRadioOptionValueVar==""){
				alert("Please select "+document.getElementById('quotesAttached').innerHTML.toLowerCase()+".");
				return false;	
			}	
			
//Validation for Table Entries Begins 
		if(document.getElementById('paraFrm_quantity1').value==''){
				alert("Please enter quantity for item number1.");
				return false;
			}
			
			if(!document.getElementById('paraFrm_quantity1').value==''){
				
				if(isNaN(document.getElementById('paraFrm_quantity1').value)){
					alert('Please enter quantity in numbers only');
					document.getElementById('paraFrm_quantity1').focus();
					return false;			
				}else{
					if(document.getElementById('paraFrm_description1').value==''){
						alert("Please enter description for item number1.");
						return false;
					}else if(document.getElementById('paraFrm_vendorName1').value==''){
						alert("Please select vendor for item number1.");
						return false;
					}else if(document.getElementById('paraFrm_unitPrice1').value==''){
						alert("Please enter unit price for item number1.");
						return false;
					}
				}
			 }
			 
			 if(!document.getElementById('paraFrm_unitPrice1').value==''){
				if(isNaN(document.getElementById('paraFrm_unitPrice1').value)){
					alert('Please enter unit price in numbers only');
					document.getElementById('paraFrm_unitPrice1').focus();
					return false;			
				}
			}
			 
			
			for(k=1; k<=10; k++) {
			if(!document.getElementById('paraFrm_quantity'+k).value==''){
				if(isNaN(document.getElementById('paraFrm_quantity'+k).value)){
					alert('Please enter quantity for item number '+ k +' in numbers only');
					document.getElementById('paraFrm_quantity'+k).focus();
					return false;			
				}else{
					if(document.getElementById('paraFrm_description'+k).value==''){
						alert("Please enter description for item number "+k+".");
						return false;
					}else if(document.getElementById('paraFrm_vendorName'+k).value==''){
						alert("Please select vendor for item number "+k+".");
						return false;
					}else if(document.getElementById('paraFrm_unitPrice'+k).value==''){
						alert("Please enter unit price for item number "+k+".");
						return false;
					}
				}
			 }
		  } 
		
		for(s=1; s<=10; s++) {
			if(!document.getElementById('paraFrm_unitPrice'+s).value==''){
				if(isNaN(document.getElementById('paraFrm_unitPrice'+s).value)){
					alert('Please enter unit price for item number '+ s +' in numbers only');
					document.getElementById('paraFrm_unitPrice'+s).focus();
					return false;			
				}
			}	
		}	
//Validation for Table Entries Ends	

		var forwardedManagerIDVar = document.getElementById('paraFrm_forwardedManagerID').value;
			if(forwardedManagerIDVar==""){
			alert("Please select approver.");
			return false;
			}	
			
		}catch(e)
		{
			alert("Exception occured in send for approval function : "+e);
		}
		
		var con = confirm("Do you really want to send this application for approval?");
		 if(con)
		 {
		document.getElementById('paraFrm_status').value = 'P';	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action='CapitalExpenditure_sendForApprovalFunction.action';
	  	document.getElementById('paraFrm').submit();
	  	}		
	}
	
	
	function resetFun() 
	{
		document.getElementById('paraFrm').target = "_self";     	
  		document.getElementById('paraFrm').action = 'CapitalExpenditure_reset.action';
     	document.getElementById('paraFrm').submit();
	}

	function backtolistFun() 
	{
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'CapitalExpenditure_backToList.action';
		document.getElementById('paraFrm').submit();
	}

	function printFun() {	
	window.print();
	}

	function deleteFun() 
	{
	 var con=confirm('Do you want to delete this application ?');
	 	if(con)
	 	{
			document.getElementById('paraFrm').target = "_self";
      		document.getElementById('paraFrm').action = 'CapitalExpenditure_delete.action';
			document.getElementById('paraFrm').submit();
		}
	}

	function cancelapplicationFun() 
	{
		 var con=confirm('Do you want to cancel this application ?');
	 	if(con)
	 	{
			document.getElementById('paraFrm_status').value = 'C';	
			document.getElementById('paraFrm').target = "_self";
      		document.getElementById('paraFrm').action = 'CapitalExpenditure_cancel.action';
			document.getElementById('paraFrm').submit();
		}
	}
	
	function resetOtherLocation(){
		document.getElementById('paraFrm_ifOtherLocation').value='';
	}
	
	function resetLocation(){
		
		document.getElementById('paraFrm_locationID').value='';
		document.getElementById('paraFrm_location').value='';
	/*	
		document.getElementById('cityIfOtherSelected').style.display = '';
		document.getElementById('citySelected').style.display = 'none';
		document.getElementById('paraFrm_cityID').value='';
		document.getElementById('paraFrm_city').value='';
		document.getElementById('paraFrm_state').value='';
		document.getElementById('paraFrm_streetAddress').value='';
		document.getElementById('paraFrm_zipCode').value='';
		document.getElementById('paraFrm_telePhoneNumber').value='';
	*/	
	}	
	
	function getVendors(vendorNumber,vendor){
		javascript:callsF9(500,325,'CapitalExpenditure_f9DetailVendor.action?vendorNumber='+vendorNumber+'&vendor='+vendor);
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
		document.getElementById('paraFrm').action = 'CapitalExpenditure_viewUploadedFile.action?uploadFileName='+uploadFileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
	}
</script>		
