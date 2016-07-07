<!-- Added by manish sakpal -->

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="HardwareSoftwareRequest" validate="true" id="paraFrm"
	validate="true" theme="simple">
	
	<table width="100%" align="right" cellpadding="2" cellspacing="1" class="formbg">
		<s:hidden name="listType"/>
		<s:hidden name="myPage" id="myPage" />
		<s:hidden name="myPageInProcess" id="myPageInProcess" />
		<s:hidden name="myPageSentBack" id="myPageSentBack" />
	
		<s:hidden name="myPageApproved" id="myPageApproved" />
		<s:hidden name="myPageApprovedCancel" id="myPageApprovedCancel" />
	
		<s:hidden name="myPageCancel" id="myPageCancel" />
	
		<s:hidden name="myPageRejected" id="myPageRejected" />
		<s:hidden name="myPageCancelRejected" id="myPageCancelRejected" />
		<tr>
			<td>
			<table width="100%" align="right" class="formbg">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="93%" class="txt"><strong class="text_head">Hardware
					Software Request</strong></td>
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
						<td><a href="#" onclick="viewUploadedFilePPO('ppoAttachement');"><font color="blue"><u>click here to view attached file </u></font></a>		 </td>
						
						</tr>
									
						
			</table>
			</td>
			</tr>
<tr>
			
			
			</s:if>

		<!-- Approver Comments Section Begins -->
		<s:if test="approverCommentFlag">
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
						<td width="15%" class="formth">Date</td>
						<td width="10%" class="formth">Status</td>
					</tr>
					<%
						int count = 0;
					%>
					<s:iterator value="approverCommentList">
						<tr>
							<td class="sortableTD"><%=++count%></td>
							<td class="sortableTD"><s:property value="apprName" /></td>
							<td class="sortableTD"><s:property value="apprComments" /></td>
							<td class="sortableTD" align="center"><s:property
								value="apprDate" /></td>
							<td class="sortableTD"><s:property value="apprStatus" /></td>
						</tr>
					</s:iterator>
					<%
						if(count == 0) {
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
	<!-- Tracking Number begins-->
	<tr>
					<td width="100%" height="22">
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="1" class="formbg">
						
						<tr>
							<td colspan="1" width="18%">Tracking Number : 
							</td>
							<td width="80%" >
							<s:property value="requestTrackingNumber"/><s:hidden name="requestTrackingNumber"/>
							</td>
						</tr>
					</table>
					</td>
					</tr>
			<!-- Tracking Number end-->		
		<!-- Type Of Request Begins -->
		<tr>
			<td>
			<table width="100%" align="right" class="formbg">
				<tr>
					<td colspan="6"><b>Type Of Request</b></td>
				</tr>

				<tr>
					<td width="5%">&nbsp;</td>
					<td width="15%"><s:checkbox name="hardWareCheckbox" />
					Hardware</td>
					<td width="15%"><s:checkbox name="softWareCheckbox" />
					Software</td>
					<td width="15%"><s:checkbox name="airCardCheckbox" /> Aircard</td>
					<td colspan="2">&nbsp;</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- Type Of Request Ends -->

		<!-- Requester Information Section Begins -->
		<tr>
			<td>
			<table width="100%" align="right" class="formbg">
				<tr>
					<td colspan="6" class="formtext"><b> Requester Information</b>
					</td>
				</tr>
				
				
				<tr>
					<td class="formtext" width="16%"><label name="requesterName"
						id="requesterName" ondblclick="callShowDiv(this);"> <%=label.get("requesterName")%>
					</label> :<font color="red">*</font></td>

					<s:if test="hrdsoftBean.generalFlag">
						<td height="22" width="26%"><s:hidden name="requesterID" />
						<s:hidden name="requesterToken" /> <s:textfield
							name="requesterName" size="30" readonly="true" /></td>
						<td>&nbsp;</td>	
					</s:if>

					<s:else>
						<td height="22" width="23%"><s:hidden name="requesterID" />
						<s:hidden name="requesterToken" /> <s:textfield
							name="requesterName" size="30" readonly="true" /></td>
						<s:if test="%{status=='_D' || status=='_B'}">
							<td>&nbsp;</td>
						</s:if>
						<s:else>
							<td><img id='ctrlHide'
								src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="17" theme="simple"
								onclick="javascript:callsF9(500,325,'HardwareSoftwareRequest_f9Requester.action');">
							</td>
						</s:else>

					</s:else>

					<td class="formtext" width="15%"><label name="requesterPhone"
						id="requesterPhone" ondblclick="callShowDiv(this);"> <%=label.get("requesterPhone")%>
					</label> :</td>
					<td height="22" width="20%"><s:textfield name="requesterPhone"
						size="30" maxlength="15" readonly="true" />
					</td>

					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="formtext" width="16%"><label name="requesterEmail"
						id="requesterEmail" ondblclick="callShowDiv(this);"> <%=label.get("requesterEmail")%>
					</label> :</td>
					<td height="22" width="23%"><s:textfield name="requesterEmail"
						size="30" readonly="true" /></td>
					<td>&nbsp;</td>
					<td class="formtext" width="15%">Status :</td>
					<td height="22" width="20%"><s:select name="applicationStatus"
						disabled="true" id="ctrlShow"
						list="#{'D':'Draft','P':'Pending','F':'Forwarded','B':'Sent Back','A':'Approved','C':'Pending','X':'Approved','Z':'Rejected','R':'Rejected'}"
						cssStyle="width:155" /></td>
					<td>&nbsp;</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- Requester Information Section Ends -->

		<!-- Hardware Software Recipient Information Section Begins -->
		<tr>
			<td>
			<table width="100%" align="right" class="formbg">
				<tr>
					<td colspan="6" class="formtext"><b> Hardware/Software
					Recipient Information</b></td>
				</tr>
				<tr>
					<td class="formtext" width="15%"><label name="empID"
						id="empID" ondblclick="callShowDiv(this);"> <%=label.get("empID")%>
					</label> :<font color="red">*</font></td>
					<td height="22" colspan="5"><s:hidden name="empID" /> 
						<s:textfield name="empToken" size="30" readonly="true" cssStyle="background-color: #F2F2F2;"/> 
						<s:textfield name="empName" size="70" readonly="true" cssStyle="background-color: #F2F2F2;"/> 
						<img id='ctrlHide'	src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="17" theme="simple"
						onclick="javascript:callsF9(500,325,'HardwareSoftwareRequest_f9Employee.action');">
					</td>
				</tr>

				<tr>
					<td class="formtext" width="15%"><label name="empDept"
						id="empDept" ondblclick="callShowDiv(this);"> <%=label.get("empDept")%>
					</label> :</td>
					<td height="22" width="25%">
						<table>
							<tr>
								<td>
									<s:hidden name="empDeptID"/>
									<s:textfield name="empDept" size="30" readonly="true" />
								</td>
								
								<td>
									<img id='ctrlHide' src="../pages/images/recruitment/search2.gif" height="16"
										align="absmiddle" width="17" theme="simple"
										onclick="javascript:callsF9(500,325,'HardwareSoftwareRequest_f9DepartmentNum.action');">
								</td>
							</tr>
						</table>
					</td>
					<td class="formtext" width="15%"><label name="empBussUnit"
						id="empBussUnit" ondblclick="callShowDiv(this);"> <%=label.get("empBussUnit")%>
					</label> :</td>
					<td height="22" width="20%"><s:select headerKey=""
						cssStyle="width:155" headerValue="---- Select ----"
						name="empBussUnit" list="map" /></td>
					<td class="formtext" colspan="2">&nbsp;</td>
				</tr>

				
				<tr>
					<td class="formtext" width="15%"><label name="empAddress"
						id="empAddress" ondblclick="callShowDiv(this);"> <%=label.get("empAddress")%>
					</label> :</td>
					<td colspan="5">
						<s:textarea name="empAddress" cols="98"
						rows="4" onkeypress="return imposeMaxLength(event, this, 1000);" />
					</td>
				</tr>

				<tr>
					<td class="formtext" width="15%"><label name="empCity"
						id="empCity" ondblclick="callShowDiv(this);"> <%=label.get("empCity")%>
					</label> :</td>
					<td height="22" width="25%"><s:textfield name="empCity"
						size="30" maxlength="50"/></td>
					<td class="formtext" width="15%"><label name="empState"
						id="empState" ondblclick="callShowDiv(this);"> <%=label.get("empState")%>
					</label> :</td>
					<td height="22" width="25%"><s:textfield name="empState"
						size="30" maxlength="50"/></td>
					<td colspan="2">&nbsp;</td>
				</tr>

				<tr>
					<td class="formtext" width="15%">
						<label name="county" id="county" ondblclick="callShowDiv(this);"> <%=label.get("county")%> </label> :	
					</td>
					
					<td height="22" width="25%">
						<s:textfield name="empCountry" size="30" onkeypress="30"/>
					</td>
					
					<td class="formtext" width="15%">
						<label name="empZip" id="empZip" ondblclick="callShowDiv(this);"> <%=label.get("empZip")%> </label> :<font color="red">*</font>
					</td>
					
					<td height="22" width="20%">
						<s:textfield name="empZip" size="30" onkeypress="30"/>
					</td>
					
					<td class="formtext" colspan="2">&nbsp;</td>
				</tr>

				<tr>
					<td class="formtext" width="15%">
						<label name="empPhone" id="empPhone" ondblclick="callShowDiv(this);"> <%=label.get("empPhone")%> </label> : 
						<font color="red">*</font>
					</td>
					<td width="25%">
						<s:textfield name="empPhone" size="30" maxlength="30" />
					</td>
					<td class="formtext" width="15%">
						<label name="empEmail" id="empEmail" ondblclick="callShowDiv(this);"> <%=label.get("empEmail")%> </label> :
					</td>
					<td height="22" width="20%">
						<s:textfield name="empEmail" size="30" maxlength="100" />
					</td>
					
					<td class="formtext" colspan="2">&nbsp;</td>
				</tr>
				
			<!-- 
				<tr>
					<td class="formtext" width="15%">
						<label name="attn" id="attn" ondblclick="callShowDiv(this);"> <%=label.get("attn")%> </label> :
					</td>
					<td colspan="5">
						<s:textfield name="empAttn" size="30" maxlength="30" />
					</td>
				</tr>
			 -->	
				

				<tr>
					<td width="15%">&nbsp;</td>
					<td width="15%"><s:radio name="userProfile"
						value="%{userProfile}" list="#{'ou':'Office User'}"
						onclick="setUserProfileRadioValue(this);">
					</s:radio>&nbsp;</td>
					<td colspan="4">(90% usage - Standard hours support - High
					dependency on shared devices - <br>
					e.g. Administrative, Accounting, Marketing, Call Center Agent')</td>
				</tr>

				<tr>
					<td width="15%">&nbsp;</td>
					<td width="15%"><s:radio name="userProfile"
						value="%{userProfile}" list="#{'pu':'Production User'}"
						onclick="setUserProfileRadioValue(this);">
					</s:radio></td>
					<td colspan="4">(Specific functionality permits several users
					to utilize without customization - <br>
					PC is viewed as a function, not as an individual tool - e.g. IT
					Developers/Power Users')</td>
				</tr>


				<tr>
					<td class="formtext" width="15%"><label name="userProfile"
						id="userProfile" ondblclick="callShowDiv(this);"> <%=label.get("userProfile")%>
					</label> :</td>
					<td width="15%"><s:radio name="userProfile"
						value="%{userProfile}" list="#{'rw':'RoadWorrier'}"
						onclick="setUserProfileRadioValue(this);">
					</s:radio></td>
					<td colspan="4">(Dependent upon remote access - Travel (80% or
					more) - Extended business hours - <br>
					High dependency on critical systems reliability - e.g. Salesperson,
					Field Engineer')</td>
				</tr>

				<tr>
					<td width="15%">&nbsp;</td>
					<td width="15%"><s:radio name="userProfile"
						value="%{userProfile}" list="#{'tc':'TeleCommuter'}"
						onclick="setUserProfileRadioValue(this);">
					</s:radio></td>
					<td colspan="4">(Dependent upon remote access - One primary
					location - <br>
					Travel (<50%) - Remote user (20%) - e.g. Telecommuter,
					Executive/VP, Remote Home Office')</td>
				</tr>

				<tr>
					<td width="15%">&nbsp;</td>
					<td colspan="5"><s:radio name="userProfile"
						value="%{userProfile}" list="#{'ot':'Other'}"
						onclick="setUserProfileRadioValue(this);">
					</s:radio></td>
				</tr>

				<tr>
					<td colspan="6"><label name="otherProfileExplain"
						id="otherProfileExplain" ondblclick="callShowDiv(this);">
					<%=label.get("otherProfileExplain")%> </label> : <br>
					<s:textarea name="otherProfileExplain" cols="61" rows="4"
						onkeypress="return imposeMaxLength(event, this, 2000);" /> <img
						id='ctrlHide' src="../pages/images/zoomin.gif" height="12"
						align="bottom" width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_otherProfileExplain','otherProfileExplain','', '', '2000','2000');">
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- Hardware/Software Recipient Information Section Ends -->

		<!-- Business Justification Section Begins -->
		<tr>
			<td>
			<table width="100%" align="right" class="formbg">
				<tr>
					<td class="formtext" colspan="6" align="left"><b><label
						name="bussinessExplain" id="bussinessExplain"
						ondblclick="callShowDiv(this);"> <%=label.get("bussinessExplain")%>
					</label></b> :<font color="red">*</font>
				</tr>

				<tr>
					<td colspan="6"><s:textarea name="bussinessExplain" cols="61"
						rows="4" onkeypress="return imposeMaxLength(event, this, 2000);" />
					<img id='ctrlHide' src="../pages/images/zoomin.gif" height="12"
						align="bottom" width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_bussinessExplain','bussinessExplain','', '', '2000','2000');">
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- Bussiness Justification Section Ends -->
	
		<!-- H/W S/W request Attachment Section Begins -->
		<tr>
			<td>
			<table width="100%" align="right" class="formbg">
				<tr>
					<td class="formtext" colspan="6" align="left"><b><label
						name="attachment" id="attachment"
						ondblclick="callShowDiv(this);"> <%=label.get("attachment")%>
					</label></b> :
					</td>
				</tr>

	<!-- 
			<tr>
					<td width="15%">&nbsp;</td>
					
					<s:if test="%{status=='' || status=='_D' || status=='_B'}">
						<td width="30%">						
							<s:textfield name="uploadFileName" size="40" readonly="true" cssStyle="background-color: #F2F2F2;" />
							<br><a href="#" onclick="showRecord('<s:property value="uploadFileName" />');"><font color="blue"><s:property value="uploadFileName" /></font></a>
						</td>
						<td colspan="4">
							<input type="button" class="token" theme="simple" value="Select File" onclick="uploadFile('uploadFileName');" />
						</td>		
					</s:if> 
					<s:else>
						<td colspan="5">
							<a href="#" onclick="showRecord('<s:property value="uploadFileName" />');"><font color="blue"><s:property value="uploadFileName" /></font></a>						
						</td>
					</s:else> 
			</tr>
	 -->
	
				<tr>
					<td width="15%">Attach file : </td>
					<td width="85%">
						<s:textfield name="uploadFileName" size="40" readonly="true" cssStyle="background-color: #F2F2F2;" />
					 <s:if test="%{status=='' || status=='_D' || status=='_B'}">	
						<input type="button" value="Upload File" class="token" onclick="uploadFile('uploadFileName');" />
				 	 </s:if>	
					</td>
				</tr>
						
				<tr>
					<td> </td>
					<td>
						<a href="#" onclick="showRecord();"><font color="blue"><u>click here to view attached file</u></font></a>
					</td>
				</tr>	
			</table>
			</td>
		</tr>
		<!-- H/W S/W request Attachment Section Ends -->
	
		<!-- Hardware Request Information Section Begins-->
		<tr>
			<td>
			<table width="100%" align="right" class="formbg" border="0">
				<tr>
					<td class="formtext" colspan="6" align="left"><b>Hardware
					Request Information :</b></td>
				</tr>
				<tr>
					<td class="formtext" width="15%"><label
						name="typeOfHardwareRequest" id="typeOfHardwareRequest"
						ondblclick="callShowDiv(this);"> <%=label.get("typeOfHardwareRequest")%>
					</label> :</td>
					<td width="25%"><s:select name="typeOfHardwareRequest"
						list="#{'N':'New','R':'Replacement','A':'Addition to Hardware'}"
						cssStyle="width:155"></s:select></td>
					<td class="formtext" width="15%"><label
						name="hardwareItemsRequested" id="hardwareItemsRequested"
						ondblclick="callShowDiv(this);"> <%=label.get("hardwareItemsRequested")%>
					</label> :</td>
					<td width="25%"><s:select name="hardwareItemsRequested"
						cssStyle="width:180" size="4" multiple="true"
						list="#{'1':'Desktop Standard User Build A','2':'Desktop Standard User Build B','3':'Laptop Standard User Build A','4':'Laptop Standard User Build B','5':'Other'}" />
					</td>
					
					<td class="formtext" width="10%"><label
						name="quantity" id="quantity"
						ondblclick="callShowDiv(this);"> <%=label.get("quantity")%>
					</label> :</td>
					<td width="25%"><s:textfield name="hwQuantity"
						size="10" maxlength="10"/>
					</td>
				</tr>
				<tr>
					<td class="formtext" colspan="6" align="left"><label
						name="specialHardwareRequestedExplain"
						id="specialHardwareRequestedExplain"
						ondblclick="callShowDiv(this);"> <%=label.get("specialHardwareRequestedExplain")%>
					</label> :</td>
				</tr>
				<tr>
					<td colspan="2"><s:textarea
						name="specialHardwareRequestedExplain" cols="51" rows="4"
						onkeypress="return imposeMaxLength(event, this, 2000);" /> <img
						id='ctrlHide' src="../pages/images/zoomin.gif" height="12"
						align="bottom" width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_specialHardwareRequestedExplain','specialHardwareRequestedExplain','', '', '2000','2000');">
					</td>
					
					
					
				</tr>
			</table>
			</td>
		</tr>
		<!-- Hardware Request Information Section Ends -->


		<!-- Software Request Information Section Begins -->
		<tr>
			<td>
			<table width="100%" align="right" class="formbg">
				<tr>
					<td class="formtext" colspan="6" align="left"><b>Software
					Request Information :</b></td>
				</tr>
				<tr>
					<td class="formtext" width="15%"><label
						name="softwareItemsRequested" id="softwareItemsRequested"
						ondblclick="callShowDiv(this);"> <%=label.get("softwareItemsRequested")%>
					</label> :</td>
					<td width="25%"><s:select name="softwareItemsRequested"
						cssStyle="width:136" size="4" multiple="true"
						list="#{'1':'MS Office','2':'MS Project','3':'MS Visio','4':'Open Office'}" />
					</td>
					<td class="formtext" width="15%"><label
						name="specialSoftwareItemsRequested"
						id="specialSoftwareItemsRequested" ondblclick="callShowDiv(this);">
					<%=label.get("specialSoftwareItemsRequested")%> </label> :</td>
					<td width="25%"><s:select name="specialSoftwareItemsRequested"
						cssStyle="width:180" multiple="true" list="specialSoftwareMap" />
					</td>
					<td class="formtext" width="10%"><label
						name="quantity" id="quantity"
						ondblclick="callShowDiv(this);"> <%=label.get("quantity")%>
					</label> :</td>
					<td width="25%"><s:textfield name="swQuantity"
						size="10" maxlength="10"/>
					</td>
				</tr>
				<tr>
					<td class="formtext" colspan="6" align="left"><label
						name="descOfSoftwareRequest" id="descOfSoftwareRequest"
						ondblclick="callShowDiv(this);"> <%=label.get("descOfSoftwareRequest")%>
					</label> :</td>
				</tr>
				<tr>
					<td colspan="2"><s:textarea name="descOfSoftwareRequest"
						cols="51" rows="4"
						onkeypress="return imposeMaxLength(event, this, 2000);" /> <img
						id='ctrlHide' src="../pages/images/zoomin.gif" height="12"
						align="bottom" width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_descOfSoftwareRequest','descOfSoftwareRequest','', '', '2000','2000');">
					</td>
					
					
				</tr>
			</table>
			</td>
		</tr>
		<!-- Software Request Information Section Ends -->

		<!-- Current Software/Hardware information Section Begins -->
		<tr>
			<td>
			<table width="100%" align="right" class="formbg">
				<tr>
					<td class="formtext" colspan="6" align="left"><b>Current
					Hardware/Software Information : </b></td>
				</tr>
				<tr>
					<td class="formtext" width="15%"><label name="manufacturer"
						id="manufacturer" ondblclick="callShowDiv(this);"> <%=label.get("manufacturer")%>
					</label> :</td>
					<td height="22" width="25%"><s:textfield name="manufacturer"
						size="30" /></td>
					<td class="formtext" width="15%"><label name="currentModel"
						id="currentModel" ondblclick="callShowDiv(this);"> <%=label.get("currentModel")%>
					</label> :</td>
					<td height="22" width="25%"><s:textfield name="currentModel"
						size="30" /></td>
					<td class="formtext" colspan="2">&nbsp;</td>
				</tr>

				<tr>
					<td class="formtext" width="15%"><label name="serial"
						id="serial" ondblclick="callShowDiv(this);"> <%=label.get("serial")%>
					</label> :</td>
					<td height="22" width="25%"><s:textfield name="serial"
						size="30" /></td>
					<td class="formtext" width="15%"><label name="compName"
						id="compName" ondblclick="callShowDiv(this);"> <%=label.get("compName")%>
					</label> :</td>
					<td height="22" width="25%"><s:textfield name="compName"
						size="30" /></td>
					<td class="formtext" colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td class="formtext" width="15%"><label name="os" id="os"
						ondblclick="callShowDiv(this);"> <%=label.get("os")%> </label> :</td>
					<td height="22" width="25%"><s:select name="operatingSystem"
						cssStyle="width:130"
						list="#{'wxp':'Windows XP','wv':'Vista','w7':'Windows 7'}" /></td>
					<td colspan="4">&nbsp;</td>
				</tr>

				<tr>
					<td class="formtext" width="15%"><label
						name="doYouHaveOpenTicket" id="doYouHaveOpenTicket"
						ondblclick="callShowDiv(this);"> <%=label.get("doYouHaveOpenTicket")%>
					</label></td>
					<td height="22" width="25%"><s:radio
						name="doYouHaveOpenTicketRadio"
						value="%{doYouHaveOpenTicketRadio}" list="#{'no':'No'}"
						onclick="setDoYouHaveOpenTicketRadioValue(this);">
					</s:radio><br>
					<s:radio name="doYouHaveOpenTicketRadio"
						value="%{doYouHaveOpenTicketRadio}" list="#{'ys':'Yes'}"
						onclick="setDoYouHaveOpenTicketRadioValue(this);">
					</s:radio> &nbsp;&nbsp;</td>
					<td class="formtext" width="15%">If so, Enter Ticket # :</td>
					<td height="22" width="25%"><s:textfield name="openTicketYES"
						size="30" /></td>
					<td colspan="2">&nbsp;</td>
				</tr>

				<tr>
					<td class="formtext" width="15%"><label
						name="zenWorkInstalled" id="zenWorkInstalled"
						ondblclick="callShowDiv(this);"> <%=label.get("zenWorkInstalled")%>
					</label></td>
					<td height="22" width="25%"><s:radio name="zenworkRadio"
						value="%{zenworkRadio}" list="#{'no':'No'}"
						onclick="setZenWorkRadioValue(this);">
					</s:radio><br>
					<s:radio name="zenworkRadio" value="%{zenworkRadio}"
						list="#{'ys':'Yes'}" onclick="setZenWorkRadioValue(this);">
					</s:radio><br>
					<s:radio name="zenworkRadio" value="%{zenworkRadio}"
						list="#{'dnt':'I Dont Know'}"
						onclick="setZenWorkRadioValue(this);">
					</s:radio></td>
					<td class="formtext" width="15%"><label
						name="antiVirusInstalled" id="antiVirusInstalled"
						ondblclick="callShowDiv(this);"> <%=label.get("antiVirusInstalled")%>
					</label></td>
					<td height="22" width="25%"><s:radio name="antiVirusRadio"
						value="%{antiVirusRadio}" list="#{'no':'No'}"
						onclick="setAntiVirusRadioValue(this);">
					</s:radio><br>
					<s:radio name="antiVirusRadio" value="%{antiVirusRadio}"
						list="#{'ys':'Yes'}" onclick="setAntiVirusRadioValue(this);">
					</s:radio><br>
					<s:radio name="antiVirusRadio" value="%{antiVirusRadio}"
						list="#{'dnt':'I Dont Know'}"
						onclick="setAntiVirusRadioValue(this);">
					</s:radio></td>
					<td colspan="2">&nbsp;</td>
				</tr>

			</table>
			</td>
		</tr>
		<!-- Current Software/Hardware information Section Ends -->

		<!-- Approver Details Begins -->
		<tr>
			<td>
			<table width="100%" align="right" class="formbg">
				<tr>
					<td class="formtext" colspan="6" align="left"><b>Form
					Approver</b></td>
				</tr>

				<tr>
					<td class="formtext" colspan="2"><label name="defaultManager"
						id="defaultManager" ondblclick="callShowDiv(this);"> <%=label.get("defaultManager")%>
					</label> :</td>
					<td height="22" colspan="5">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td><s:hidden name="firstApproverCode" /></td>
							<s:iterator value="approverList">
								<tr>
									<td><s:hidden name="approverName" /> <s:property
										value="approverToken" /> <s:property value="approverName" />
									</td>
								</tr>
							</s:iterator>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td class="formtext" colspan="2"><label name="changeMyManager"
						id="changeMyManager" ondblclick="callShowDiv(this);"> <%=label.get("changeMyManager")%>
					</label> :</td>
					<td height="22" colspan="4"><s:textfield
						name="selectApproverToken" size="30"
						value="%{selectApproverToken}" theme="simple" readonly="true" />
					<s:textfield name="selectapproverName" size="56"
						value="%{selectapproverName}" theme="simple" readonly="true" /> <s:hidden
						name="selectApproverCode" value="%{selectApproverCode}" /> <img
						id='ctrlHide' src="../pages/images/recruitment/search2.gif"
						height="16" align="absmiddle" width="17" theme="simple"
						onclick="javascript:callsF9(500,325,'HardwareSoftwareRequest_f9Approver.action');">
					</td>
				</tr>

			</table>
			</td>
		</tr>
		<!-- Approver Details Ends -->

		<!-- COMPLETED BY/ON Begins -->
		<tr>
			<td>
			<table width="100%" align="right" class="formbg">
				<tr>
					<td class="formtext" width="15%"><b><label
						name="completedBy" id="completedBy"
						ondblclick="callShowDiv(this);"> <%=label.get("completedBy")%>
					</label></b> :</td>
					<td height="22" width="25%"><s:hidden name="completedByID" />
					<s:hidden name="completedByToken" /> <s:hidden
						name="completedByName" /> <s:property value="completedByToken" />
					- <s:property value="completedByName" /></td>

					<td class="formtext" width="15%"><b><label
						name="completedOn" id="completedOn"
						ondblclick="callShowDiv(this);"> <%=label.get("completedOn")%>
					</label></b> :</td>
					<td height="22" width="25%"><s:hidden name="completedOn" /> <s:property
						value="completedOn" /></td>
					<td class="formtext" colspan="2">&nbsp;</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- COMPLETED BY/ON Begins -->

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				</tr>
			</table>
			</td>
		</tr>

		<s:hidden name="hwSwID" />
		<input type="hidden" name="userProfileRadioOptionValue"
			id="userProfileRadioOptionValue"
			value='<s:property value="userProfileRadioOptionValue"/>' />
		<input type="hidden" name="doYouHaveOpenTicketOptionValue"
			id="doYouHaveOpenTicketOptionValue"
			value='<s:property value="doYouHaveOpenTicketOptionValue"/>' />
		<input type="hidden" name="zenWorkOptionValue" id="zenWorkOptionValue"
			value='<s:property value="zenWorkOptionValue"/>' />
		<input type="hidden" name="antiVirusOptionValue"
			id="antiVirusOptionValue"
			value='<s:property value="antiVirusOptionValue"/>' />
		<s:hidden name="status" />
		<s:hidden name="level" />
		<s:hidden name="hardwareHiddenValues" />
		<s:hidden name="softwareHiddenValues" />
		<s:hidden name="specialSoftwareHiddenValues" />
		
		<s:hidden name="dataPath" />
	</table>
</s:form>

<script>
onload();
function onload()
{	

	// Hardware items Listbox
	if(!(document.getElementById('paraFrm_hardwareHiddenValues').value)=="") {
		var hardwareItems = '';
		var hardwareItemsListBoxVal = document.getElementById('paraFrm_hardwareItemsRequested');
		
		for(var i = 0; i < hardwareItemsListBoxVal.options.length; i++) {
			var hwOption = hardwareItemsListBoxVal.options[i].value;
			var hardwareHiddenRecords = document.getElementById('paraFrm_hardwareHiddenValues').value.split(',');

			for(var j = 0; j < hardwareHiddenRecords.length; j++) {
				if(hardwareHiddenRecords[j] == hwOption) {
					hardwareItemsListBoxVal.options[i].selected = 'selected';
					if(j==(hardwareHiddenRecords.length-1)) {
						hardwareItems += hardwareItemsListBoxVal.options[i].innerHTML;
					} else {
						hardwareItems += hardwareItemsListBoxVal.options[i].innerHTML+ ',';
					}
					break;
				}
			}
		}
			
			var hardwareEnableAll = document.getElementById('paraFrm_enableAll').value;
			
		if(hardwareEnableAll == 'N') {
			var hardwareApplnCell = hardwareItemsListBoxVal.parentNode;
			var hardwareApplnLbl = document.createElement('label');
			
			hardwareApplnCell.appendChild(hardwareApplnLbl);
		}
		
	}
	
	
	// Software items ListBox
	if(!(document.getElementById('paraFrm_softwareHiddenValues').value)=="") {
		var softwareItems = '';
		var softwareItemsListBoxVal = document.getElementById('paraFrm_softwareItemsRequested');
		
		for(var i = 0; i < softwareItemsListBoxVal.options.length; i++) {
			var swOption = softwareItemsListBoxVal.options[i].value;
			var softwareHiddenRecords = document.getElementById('paraFrm_softwareHiddenValues').value.split(',');

			for(var j = 0; j < softwareHiddenRecords.length; j++) {
				if(softwareHiddenRecords[j] == swOption) {
					softwareItemsListBoxVal.options[i].selected = 'selected';
					
					if(j==(softwareHiddenRecords.length-1)) {
						softwareItems += softwareItemsListBoxVal.options[i].innerHTML;
					} else {
						softwareItems += softwareItemsListBoxVal.options[i].innerHTML + ',';
					}
					break;
				}
			}
		}
		
		var enableAll = document.getElementById('paraFrm_enableAll').value;
		if(enableAll == 'N') {
			var softwareApplnCell = softwareItemsListBoxVal.parentNode;
			var softwareApplnLbl = document.createElement('label');
			
			softwareApplnCell.appendChild(softwareApplnLbl);
		}
	}
	
	// Special Software items ListBox
	if(!(document.getElementById('paraFrm_specialSoftwareHiddenValues').value)=="") {
		var specialSoftwareItems = '';
		var specialSoftwareItemsListBoxVal = document.getElementById('paraFrm_specialSoftwareItemsRequested');
		
		for(var i = 0; i < specialSoftwareItemsListBoxVal.options.length; i++) {
			var specialSWOption = specialSoftwareItemsListBoxVal.options[i].value;
			var specialSoftwareHiddenRecords = document.getElementById('paraFrm_specialSoftwareHiddenValues').value.split(',');

			for(var j = 0; j < specialSoftwareHiddenRecords.length; j++) {
				if(specialSoftwareHiddenRecords[j] == specialSWOption) {
					specialSoftwareItemsListBoxVal.options[i].selected = 'selected';
					
					if(j==(specialSoftwareHiddenRecords.length-1)) {
						specialSoftwareItems += specialSoftwareItemsListBoxVal.options[i].innerHTML;
					} else {
						specialSoftwareItems += specialSoftwareItemsListBoxVal.options[i].innerHTML + ',';
					}
					break;
				}
			}
		}
			
			var specialSoftwareEnableAll = document.getElementById('paraFrm_enableAll').value;
		if(specialSoftwareEnableAll == 'N') {
			var specialSoftwareApplnCell = specialSoftwareItemsListBoxVal.parentNode;
			var specialSoftwareApplnLbl = document.createElement('label');
			
			specialSoftwareApplnCell.appendChild(specialSoftwareApplnLbl);
		}
	}
}


function setUserProfileRadioValue(id){
	var opt=document.getElementById('userProfileRadioOptionValue').value =id.value;	
}

function setZenWorkRadioValue(id)
{
	var opt=document.getElementById('zenWorkOptionValue').value =id.value;	
}

function setAntiVirusRadioValue(id)
{
	var opt=document.getElementById('antiVirusOptionValue').value =id.value;	
}

function setDoYouHaveOpenTicketRadioValue(id){
	var openTicketOptionValue=document.getElementById('doYouHaveOpenTicketOptionValue').value =id.value;
	 if(openTicketOptionValue=='no')
		 {		 		 	
		 	 document.getElementById('paraFrm_openTicketYES').value='';
			 document.getElementById('paraFrm_openTicketYES').readOnly='true'; 
			 document.getElementById('paraFrm_openTicketYES').style.background='#F2F2F2'; 
		 }
		 else
		  if(openTicketOptionValue=='ys')
		 {
		 	 document.getElementById('paraFrm_openTicketYES').readOnly=''; 
			 document.getElementById('paraFrm_openTicketYES').style.background='white';
		 }	
 }


 function sendforapprovalFun()
{		
	try
	{
		var hardWareCheckboxVar = document.getElementById('paraFrm_hardWareCheckbox').checked;
		var softWareCheckboxVar = document.getElementById('paraFrm_softWareCheckbox').checked;
		var airCardCheckboxVar = document.getElementById('paraFrm_airCardCheckbox').checked;
		if((hardWareCheckboxVar == false) && (softWareCheckboxVar == false) && (airCardCheckboxVar == false))
		{
			alert("please select Type Of Request ");
			return false;
		}
		
		var requesterIDVar = document.getElementById('paraFrm_requesterID').value;
		if(requesterIDVar=="")
		{
			alert("Please Select Requester.");
		  	document.getElementById('paraFrm_requesterName').focus();
		 	return false;		
		}
		
		var empIDVar = document.getElementById('paraFrm_empID').value;
		if(empIDVar=="")
		{
			alert("Please Select Employee.");
		  	document.getElementById('paraFrm_empName').focus();
		 	return false;		
		}
		
		var bussinessExplainVar = document.getElementById('paraFrm_bussinessExplain').value;
		if(bussinessExplainVar=="")
		{
			alert("Please enter "+document.getElementById('bussinessExplain').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_bussinessExplain').focus();
		 	return false;		
		}
		
		var empZipVar = document.getElementById('paraFrm_empZip').value;
		if(empZipVar=="")
		{
			alert("Please enter "+document.getElementById('empZip').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_empZip').focus();
		 	return false;		
		}
		
		var phoneVar = document.getElementById('paraFrm_empPhone').value;
		if(phoneVar=="") {
			alert("Please enter "+document.getElementById('empPhone').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_empPhone').focus();
		 	return false;		
		}
		var firstApproverCodeVar = document.getElementById('paraFrm_firstApproverCode').value;
		
		if(firstApproverCodeVar=="")
		{
			var selectapproverNameVar = document.getElementById('paraFrm_selectapproverName').value;
			if(selectapproverNameVar == "")
			{
				alert("Please Select Approver.");
		  		document.getElementById('paraFrm_selectapproverName').focus();
		 		return false;
		 	}	
		}
	}catch(e)
	  {
			alert("Exception occurred in send for approver function."+e);
	  }
		
	 var con=confirm('Do you really want to send this application for approval?');
	 if(con)
	 {
		document.getElementById('paraFrm_status').value = 'P';	
		document.getElementById('paraFrm').target = "_self";
    	document.getElementById('paraFrm').action='HardwareSoftwareRequest_sendForApprovalFunction.action';
		document.getElementById('paraFrm').submit();
	}		
}


function draftFun()
{	
	try
	{
		var hardWareCheckboxVar = document.getElementById('paraFrm_hardWareCheckbox').checked;
		var softWareCheckboxVar = document.getElementById('paraFrm_softWareCheckbox').checked;
		var airCardCheckboxVar = document.getElementById('paraFrm_airCardCheckbox').checked;
		if((hardWareCheckboxVar == false) && (softWareCheckboxVar == false) && (airCardCheckboxVar == false))
		{
			alert("please select Type Of Request ");
			return false;
		}
		
		var requesterIDVar = document.getElementById('paraFrm_requesterID').value;
		if(requesterIDVar=="")
		{
			alert("Please Select Requester.");
		  	document.getElementById('paraFrm_requesterName').focus();
		 	return false;		
		}
		
		var empIDVar = document.getElementById('paraFrm_empID').value;
		if(empIDVar=="")
		{
			alert("Please Select Employee.");
		  	document.getElementById('paraFrm_empName').focus();
		 	return false;		
		}
		
		var bussinessExplainVar = document.getElementById('paraFrm_bussinessExplain').value;
		if(bussinessExplainVar=="")
		{
			alert("Please enter "+document.getElementById('bussinessExplain').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_bussinessExplain').focus();
		 	return false;		
		}
		var empZipVar = document.getElementById('paraFrm_empZip').value;
		if(empZipVar=="")
		{
			alert("Please enter "+document.getElementById('empZip').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_empZip').focus();
		 	return false;		
		}
		
		
		var phoneVar = document.getElementById('paraFrm_empPhone').value;
		if(phoneVar=="") {
			alert("Please enter "+document.getElementById('empPhone').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_empPhone').focus();
		 	return false;		
		}
		
		var firstApproverCodeVar = document.getElementById('paraFrm_firstApproverCode').value;
		if(firstApproverCodeVar=="")
		{
			var selectapproverNameVar = document.getElementById('paraFrm_selectapproverName').value;
			if(selectapproverNameVar == "")
			{
				alert("Please Select Approver.");
		  		document.getElementById('paraFrm_selectapproverName').focus();
		 		return false;
		 	}	
		}
		
	}catch(e)
	{
		alert("Exception occured in draft function : "+e);
	}
		
		document.getElementById('paraFrm_status').value = 'D';	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action='HardwareSoftwareRequest_draftFunction.action';
	  	document.getElementById('paraFrm').submit();		
}


function resetFun() {
		document.getElementById('paraFrm').target = "_self";     	
  		document.getElementById('paraFrm').action = 'HardwareSoftwareRequest_reset.action';
     	document.getElementById('paraFrm').submit();
}

function backtolistFun() {	
	document.getElementById('paraFrm').target = "_self";
    document.getElementById('paraFrm').action = 'HardwareSoftwareRequest_back.action';
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
      	document.getElementById('paraFrm').action = 'HardwareSoftwareRequest_delete.action';
		document.getElementById('paraFrm').submit();
	}
}

function cancelapplicationFun() 
{
	 var con=confirm('Do you want to cancel this application ?');
	 if(con)
	 {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'HardwareSoftwareRequest_cancel.action';
		document.getElementById('paraFrm').submit();
	}
}	


function imposeMaxLength(Event, Object, MaxLen)
{
  return (Object.value.length <= MaxLen)||(Event.keyCode == 8 ||Event.keyCode==46||(Event.keyCode>=35&&Event.keyCode<=40))
}

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
		document.getElementById('paraFrm').action = "HardwareSoftwareRequest_viewAttachedProof.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
	}
	
function uploadFile(fieldName) {
		//alert(fieldName);
		var dataPath = document.getElementById('paraFrm_dataPath').value;
		window.open('<%=request.getContextPath()%>/pages/recruitment/uploadResume.jsp?path=' + dataPath + '&field=' + 
		fieldName, '', 'width=400, height=200, scrollbars=no, resizable=no, top=100, left=150');
	}
	function viewUploadedFilePPO(id) {
		var uploadFileName = document.getElementById('paraFrm_'+id).value;
		
		if(uploadFileName == '') {
			alert('File not available...');
			return false;
		}
		document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = 'HardwareSoftwareRequestApprover_viewUploadedFile.action?uploadFileName='+uploadFileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
	}	
</script>
