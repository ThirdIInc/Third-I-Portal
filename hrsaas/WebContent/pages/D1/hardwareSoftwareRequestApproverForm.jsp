<!-- Created by manish sakpal on 1st March 2011 -->

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="HardwareSoftwareRequestApprover" validate="true"
	id="paraFrm" validate="true" theme="simple">

	<table width="100%" align="right" cellpadding="2" cellspacing="1"
		class="formbg">
		<s:hidden name="ppoFlag" />
		<s:hidden name="listType" />

	<s:hidden name="myPage" id="myPage" />
	<s:hidden name="myPagePendingCancel" id="myPagePendingCancel" />
	<s:hidden name="myPageApproved" id="myPageApproved" />
	<s:hidden name="myPageRejected" id="myPageRejected" />

		<tr>
			<td>
			<table width="100%" align="right" class="formbg">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="93%" class="txt"><strong class="text_head">Hardware
					Software Request Approver </strong></td>
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


    <s:if test="attachedFlag">
			<tr>
				<td width="100%" height="22">
				<table width="100%" border="0" align="center" cellpadding="2"
					cellspacing="1" class="formbg">
					
						<tr>
							<td><label id="ppo.attachment" name="ppo.attachment"
								ondblclick="callShowDiv(this);"><%=label.get("ppo.attachment")%></label>
							:</td>
							<td width="75%"><s:textfield name="ppoNumber"
								maxlength="100" /> <s:textfield name="ppoAttachement"
								 readonly="true" size="20"
								cssStyle="background-color: #F2F2F2;" /> <input type="button"
								value="Upload File" class="token"
								onclick="uploadFile('ppoAttachement')" /></td>
						</tr>
						<tr>
							<td></td>
							<td><a href="#"
								onclick="viewUploadedFile('ppoAttachement');"><font
								color="blue"><u>click here to view attached file </u></font></a></td>

						</tr>
					</table></td></tr>
					
					</s:if>


		<s:if test="checkFlag">
			<tr>
				<td width="100%" height="22">
				<table width="100%" border="0" align="center" cellpadding="2"
					cellspacing="1" class="formbg">

					<tr>
						<td width="15%">
							<label id="ppo.attachment" name="ppo.attachment"
								ondblclick="callShowDiv(this);"><%=label.get("ppo.attachment")%></label>
						</td>
						<td width="85%"><s:property value="ppoNumber" /><s:hidden
							name="ppoNumber" /></td>
					</tr>

					<tr>
						<td width="15%">Attached File :</td>
						<td width="85%"><s:property value="ppoAttachement" /><s:hidden
							name="ppoAttachement" /></td>
					</tr>

					<tr>
						<td width="15%">&nbsp;</td>
						<td width="85%"><a href="#" id="ctrlShow"
							onclick="viewUploadedFile('ppoAttachement');"><font
							color="blue"><u>click here to view attached file </u></font></a></td>
					</tr>

				</table>
				</td>
			</tr>
		</s:if>
		<s:if test="fileFlag">
			<tr>
				<td width="100%" height="22">
				<table width="100%" border="0" align="center" cellpadding="2"
					cellspacing="1" class="formbg">
					<s:if test="%{status == '_F'}">
						<tr>
							<td><label id="ppo.attachment" name="ppo.attachment"
								ondblclick="callShowDiv(this);"><%=label.get("ppo.attachment")%></label>
							:</td>
							<td width="75%"><s:textfield name="ppoNumber"
								maxlength="100" /> <s:textfield name="ppoAttachement"
								onclick="uploadFile('ppoAttachement')" readonly="true" size="20"
								cssStyle="background-color: #F2F2F2;" /> <input type="button"
								value="Upload File" class="token"
								onclick="uploadFile('ppoAttachement')" /></td>
						</tr>
						<tr>
							<td></td>
							<td><a href="#"
								onclick="viewUploadedFile('ppoAttachement');"><font
								color="blue"><u>click here to view attached file </u></font></a></td>

						</tr>
					</s:if>
					<s:if test="%{status == '_A'}">
						<tr>
							<td><label id="ppo.attachment" name="ppo.attachment"
								ondblclick="callShowDiv(this);"><%=label.get("ppo.attachment")%></label>
							:</td>
							<td width="75%"><s:textfield name="ppoNumber" /> <s:textfield
								name="ppoAttachement" onclick="uploadFile('ppoAttachement')"
								readonly="true" size="20" cssStyle="background-color: #F2F2F2;" />

							</td>
						</tr>
						<tr>
							<td></td>
							<td><a href="#"
								onclick="viewUploadedFile('ppoAttachement');"><font
								color="blue"><u>click here to view attached file</u></font></a></td>

						</tr>
					</s:if>



				</table>
				</td>
			</tr>
		</s:if>
		<tr>


			<!-- Approver Comments Section Begins -->
		<tr>
			<td>
			<table width="100%" class="formbg" border="0" cellpadding="1"
				cellspacing="1">
				<tr>
					<td colspan="1" width="25%"><b>Approver Comments</b> :</td>
					<td colspan="4" width="75%"><s:if test="approverCommentsFlag">
						<s:textarea theme="simple" cols="70" rows="3"
							name="approverComments" id="approverComments" />
					</s:if></td>
				</tr>
				<s:if test="f9Flag">
					<tr>
						<td colspan="1" width="25%">Employee <font color="red"
							id="ctrlShow">* </font> :</td>
						<td width="75%" colspan="4"><s:textfield
							name="forwardEmpToken" cssStyle="background-color: #F2F2F2;"
							size="20" theme="simple" readonly="true" /> <s:textfield
							name="forwardEmpName" size="70" theme="simple" readonly="true"
							cssStyle="background-color: #F2F2F2;" /> <s:hidden
							name="fwdempCode" /><img
							src="../pages/images/recruitment/search2.gif" height="16"
							align="absmiddle" width="16" id='ctrlHide'
							onclick="javascript:forwardedEmp();"></td>
					</tr>
				</s:if>


				<tr>
					<td width="10%" class="formth">Sr. No.</td>
					<td width="25%" class="formth">Approver Name</td>
					<td width="35%" class="formth">Comments</td>
					<td width="20%" class="formth">Date</td>
					<td width="10%" class="formth">Status</td>
				</tr>
				<%
				int count = 0;
				%>
				<s:iterator value="approverCommentList">
					<tr>
						<td class="sortableTD" align="center"><%=++count%></td>
						<td class="sortableTD"><s:property value="apprName" /></td>
						<td class="sortableTD"><s:property value="apprComments" /></td>
						<td class="sortableTD" align="center"><s:property
							value="apprDate" /></td>
						<td class="sortableTD"><s:property value="apprStatus" /></td>
					</tr>
				</s:iterator>
				<%
				if (count == 0) {
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
		<!-- Tracking Number begins-->
		<tr>
			<td width="100%" height="22">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">

				<tr>
					<td colspan="1" width="18%">Tracking Number :</td>
					<td width="80%"><s:property value="requestTrackingNumber" /><s:hidden
						name="requestTrackingNumber" /></td>
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
					<td width="25%">&nbsp;</td>
					<td width="25%"><s:checkbox name="hardWareCheckbox"
						disabled="true" /> Hardware</td>
					<td width="25%"><s:checkbox name="softWareCheckbox"
						disabled="true" /> Software</td>
					<td width="25%"><s:checkbox name="airCardCheckbox"
						disabled="true" /> Aircard</td>
					
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
					<td class="formtext" width="15%"><label name="requesterName"
						id="requesterName" ondblclick="callShowDiv(this);"> <%=label.get("requesterName")%>
					</label><font color="red">*</font> :</td>
					<td height="22" width="25%"><s:hidden name="requesterID" /> <s:hidden
						name="requesterToken" /> <s:property value="requesterName" /></td>
					<td class="formtext" width="15%"><label name="requesterPhone"
						id="requesterPhone" ondblclick="callShowDiv(this);"> <%=label.get("requesterPhone")%>
					</label> :</td>
					<td height="22" width="20%"><s:property value="requesterPhone" />
					</td>
					<td colspan="2"></td>
				</tr>
				<tr>
					<td class="formtext" width="15%"><label name="requesterEmail"
						id="requesterEmail" ondblclick="callShowDiv(this);"> <%=label.get("requesterEmail")%>
					</label> :</td>
					<td height="22" width="25%"><s:property value="requesterEmail" /></td>
					<td class="formtext" width="15%">Status :</td>
					<td height="22" width="20%"><s:select name="applicationStatus"
						id="ctrlShow" disabled="true"
						list="#{'P':'Pending','B':'Sent Back','A':'Approved','C':'Pending','X':'Approved','Z':'Rejected','R':'Rejected','F':'Forwarded'}"
						cssStyle="width:130" /></td>
					<td colspan="2"></td>
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
					<td class="formtext" width="15%"><label name="employeeID"
						id="employeeID" ondblclick="callShowDiv(this);"> <%=label.get("employeeID")%>
					</label><font color="red">*</font> :</td>
					<td height="22" colspan="5"><s:hidden name="empID" /> <s:property
						value="empToken" /> <s:property value="empName" /></td>
				</tr>

				<tr>
					<td class="formtext" width="15%"><label name="empDept"
						id="empDept" ondblclick="callShowDiv(this);"> <%=label.get("empDept")%>
					</label> :</td>
					<td height="22" width="25%"><s:property value="empDept" /></td>
					<td class="formtext" width="15%"><label name="empBussUnit"
						id="empBussUnit" ondblclick="callShowDiv(this);"> <%=label.get("empBussUnit")%>
					</label> :</td>
					<td height="22" width="20%"><s:select headerKey=""
						cssStyle="width:129" headerValue="---- Select ----"
						name="empBussUnit" list="map" disabled="true" /></td>
					<td class="formtext" colspan="2">&nbsp;</td>
				</tr>

				<tr>
					<td class="formtext" width="15%"><label name="empAddress"
						id="empAddress" ondblclick="callShowDiv(this);"> <%=label.get("empAddress")%>
					</label> :</td>
					<td colspan="5">
						<s:textarea name="empAddress" cols="98" disabled="true" rows="4"/>
					</td>
				</tr>
				
				<tr>
					<td class="formtext" width="15%"><label name="empCity"
						id="empCity" ondblclick="callShowDiv(this);"> <%=label.get("empCity")%>
					</label> :</td>
					<td height="22" width="25%"><s:property value="empCity" /></td>
					<td class="formtext" width="15%"><label name="empState"
						id="empState" ondblclick="callShowDiv(this);"> <%=label.get("empState")%>
					</label> :</td>
					<td height="22" width="25%"><s:property value="empState" /></td>
					<td colspan="2">&nbsp;</td>
				</tr>

				<tr>
					<td class="formtext" width="15%">
						<label name="county" id="county" ondblclick="callShowDiv(this);"> <%=label.get("county")%> </label> :
					</td>
					<td height="22" width="25%">
						<s:property value="empCountry"/>
					</td>
					
					<td class="formtext" width="15%">
						<label name="empZip" id="empZip" ondblclick="callShowDiv(this);"> <%=label.get("empZip")%> </label> : <font color="red">*</font>
					</td>
					<td height="22" width="20%">
						<s:property value="empZip" />
					</td>
					<td class="formtext" colspan="2">&nbsp;</td>
				</tr>

				<tr>
					<td class="formtext" width="15%">
						<label name="empPhone" id="empPhone" ondblclick="callShowDiv(this);"> <%=label.get("empPhone")%> </label> :
						<font color="red">*</font>
					</td>
					<td width="25%">
						<s:property value="empPhone" />
					</td>
					
					<td class="formtext" width="15%">
						<label name="empEmail" id="empEmail" ondblclick="callShowDiv(this);"> <%=label.get("empEmail")%></label> :
					</td>
					
					<td height="22" width="20%">
						<s:property value="empEmail" />
					</td>
					
					<td class="formtext" colspan="2">&nbsp;</td>
				</tr>
			
			<!-- 
				<tr>
					<td class="formtext" width="15%">
						<label name="attn" id="attn" ondblclick="callShowDiv(this);"> <%=label.get("attn")%> </label> :
					</td>
					<td colspan="5">
						<s:property value="empAttn" />
					</td>
				</tr>			
			 -->	

				<tr>
					<td width="15%">&nbsp;</td>
					<td width="15%"><s:radio name="userProfile"
						value="%{userProfile}" list="#{'ou':'Office User'}"
						onclick="setUserProfileRadioValue(this);" disabled="true">
					</s:radio>&nbsp;</td>
					<td colspan="4">(90% usage - Standard hours support - High
					dependency on shared devices - <br>
					e.g. Administrative, Accounting, Marketing, Call Center Agent')</td>
				</tr>

				<tr>
					<td width="15%">&nbsp;</td>
					<td width="15%"><s:radio name="userProfile"
						value="%{userProfile}" list="#{'pu':'Production User'}"
						onclick="setUserProfileRadioValue(this);" disabled="true">
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
						onclick="setUserProfileRadioValue(this);" disabled="true">
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
						onclick="setUserProfileRadioValue(this);" disabled="true">
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
						onclick="setUserProfileRadioValue(this);" disabled="true">
					</s:radio></td>
				</tr>

				<tr>
					<td colspan="6">If Other selected for User Profile, Please
					Explain : <br>
					<s:property value="otherProfileExplain" /></td>
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
					<td class="formtext" colspan="6" align="left"><font
						color="red">*</font> Business Reason/Justification for
					Hardware/Software Request :</td>
				</tr>

				<tr>
					<td colspan="6"><s:property value="bussinessExplain" /></td>
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
					<td class="formtext" colspan="6" align="left"><label
						name="attachment" id="attachment" ondblclick="callShowDiv(this);">
					<%=label.get("attachment")%> </label> :</td>
				</tr>

				<!-- 
				<tr>
					<td width="15%">&nbsp;</td>
					<td colspan="5">
						<a href="#" onclick="showRecord('<s:property value="uploadFileName" />');"><font color="blue"><s:property value="uploadFileName" /></font></a>						
					</td>
				</tr>
			 -->
				<tr>
					<td width="15%">Attach file :</td>
					<td width="85%"><s:textfield readonly="true"
						cssStyle="background-color: #F2F2F2;" size="40"
						name="uploadFileName" /></td>
				</tr>

				<tr>
					<td></td>
					<td><a href="#" onclick="showRecord();"><font color="blue"><u>click
					here to view attached file</u></font></a></td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- H/W S/W request Attachment Section Ends -->


		<!-- Hardware Request Information Section Begins-->
		<tr>
			<td>
			<table width="100%" align="right" class="formbg">
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
						disabled="true"></s:select></td>
					<td class="formtext" width="15%"><label
						name="hardwareItemsRequested" id="hardwareItemsRequested"
						ondblclick="callShowDiv(this);"> <%=label.get("hardwareItemsRequested")%>
					</label> :</td>
					<td width="25%"><s:select name="hardwareItemsRequested"
						cssStyle="width:180" size="4" multiple="true"
						list="#{'1':'Desktop Standard User Build A','2':'Desktop Standard User Build B','3':'Laptop Standard User Build A','4':'Laptop Standard User Build B','5':'Other'}" />
					</td>
					<td class="formtext" width="10%"><label name="quantity"
						id="quantity" ondblclick="callShowDiv(this);"> <%=label.get("quantity")%>
					</label> :</td>
					<td width="25%"><s:property value="hwQuantity" /></td>
				</tr>
				<tr>
					<td class="formtext" colspan="6" align="left"><label
						name="specialHardwareRequested" id="specialHardwareRequested"
						ondblclick="callShowDiv(this);"> <%=label.get("specialHardwareRequested")%>
					</label> :</td>
				</tr>

				<tr>
					<td colspan="2"><s:property
						value="specialHardwareRequestedExplain" /></td>


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
					<td class="formtext" width="10%"><label name="quantity"
						id="quantity" ondblclick="callShowDiv(this);"> <%=label.get("quantity")%>
					</label> :</td>
					<td width="25%"><s:property value="swQuantity" /></td>
				</tr>
				<tr>
					<td class="formtext" colspan="6" align="left"><label
						name="descOfSoftwareRequest" id="descOfSoftwareRequest"
						ondblclick="callShowDiv(this);"> <%=label.get("descOfSoftwareRequest")%>
					</label> :</td>
				</tr>



				<tr>
					<td colspan="2"><s:property value="descOfSoftwareRequest" /></td>


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
					<td height="22" width="25%"><s:property value="manufacturer" />
					</td>
					<td class="formtext" width="15%"><label name="currentModel"
						id="currentModel" ondblclick="callShowDiv(this);"> <%=label.get("currentModel")%>
					</label> :</td>
					<td height="22" width="25%"><s:property value="currentModel" /></td>
					<td class="formtext" colspan="2">&nbsp;</td>
				</tr>

				<tr>
					<td class="formtext" width="15%"><label name="serial"
						id="serial" ondblclick="callShowDiv(this);"> <%=label.get("serial")%>
					</label> :</td>
					<td height="22" width="25%"><s:property value="serial" /></td>
					<td class="formtext" width="15%"><label name="compName"
						id="compName" ondblclick="callShowDiv(this);"> <%=label.get("compName")%>
					</label> :</td>
					<td height="22" width="25%"><s:property value="compName" /></td>
					<td class="formtext" colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td class="formtext" width="15%"><label name="os" id="os"
						ondblclick="callShowDiv(this);"> <%=label.get("os")%> </label> :</td>
					<td height="22" width="25%"><s:select name="operatingSystem"
						cssStyle="width:130"
						list="#{'wxp':'Windows XP','wv':'Vista','w7':'Windows 7'}"
						disabled="true" /></td>
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
						onclick="setDoYouHaveOpenTicketRadioValue(this);" disabled="true">
					</s:radio><br>
					<s:radio name="doYouHaveOpenTicketRadio"
						value="%{doYouHaveOpenTicketRadio}" list="#{'ys':'Yes'}"
						onclick="setDoYouHaveOpenTicketRadioValue(this);" disabled="true">
					</s:radio> &nbsp;&nbsp;</td>
					<td class="formtext" width="15%">If so, Enter Ticket # :</td>
					<td height="22" width="25%"><s:property value="openTicketYES" /></td>
					<td colspan="2">&nbsp;</td>
				</tr>


				<tr>
					<td class="formtext" width="15%"><label
						name="zenWorkInstalled" id="zenWorkInstalled"
						ondblclick="callShowDiv(this);"> <%=label.get("zenWorkInstalled")%>
					</label></td>
					<td height="22" width="25%"><s:radio name="zenworkRadio"
						value="%{zenworkRadio}" list="#{'no':'No'}"
						onclick="setZenWorkRadioValue(this);" disabled="true">
					</s:radio><br>
					<s:radio name="zenworkRadio" value="%{zenworkRadio}"
						list="#{'ys':'Yes'}" onclick="setZenWorkRadioValue(this);"
						disabled="true">
					</s:radio><br>
					<s:radio name="zenworkRadio" value="%{zenworkRadio}"
						list="#{'dnt':'I Dont Know'}"
						onclick="setZenWorkRadioValue(this);" disabled="true">
					</s:radio></td>
					<td class="formtext" width="15%"><label
						name="antiVirusInstalled" id="antiVirusInstalled"
						ondblclick="callShowDiv(this);"> <%=label.get("antiVirusInstalled")%>
					</label></td>
					<td height="22" width="25%"><s:radio name="antiVirusRadio"
						value="%{antiVirusRadio}" list="#{'no':'No'}"
						onclick="setAntiVirusRadioValue(this);" disabled="true">
					</s:radio><br>
					<s:radio name="antiVirusRadio" value="%{antiVirusRadio}"
						list="#{'ys':'Yes'}" onclick="setAntiVirusRadioValue(this);"
						disabled="true">
					</s:radio><br>
					<s:radio name="antiVirusRadio" value="%{antiVirusRadio}"
						list="#{'dnt':'I Dont Know'}"
						onclick="setAntiVirusRadioValue(this);" disabled="true">
					</s:radio></td>
					<td colspan="2">&nbsp;</td>
				</tr>

			</table>
			</td>
		</tr>
		<!-- Current Software/Hardware information Section Ends -->

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
					<s:property value="completedByToken" /> - <s:property
						value="completedByName" /></td>

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
		<s:hidden name="listTypeDetailPage" />
		<s:hidden name="selectedApproverCode" />
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


 function approveFun()
{
	var con = confirm("Do you really want to approve this application?");
	if(con)
	{
		document.getElementById('paraFrm').target = "_self";
    	document.getElementById('paraFrm').action = 'HardwareSoftwareRequestApprover_approveApplication.action';
		document.getElementById('paraFrm').submit();
	}
}

function rejectFun()
{
	var con = confirm("Do you really want to reject this application?");
	if(con)
	{
		document.getElementById('paraFrm').target = "_self";
    	document.getElementById('paraFrm').action = 'HardwareSoftwareRequestApprover_rejectApplication.action';
		document.getElementById('paraFrm').submit();
	}	
}

function sendbackFun()
{
	var con = confirm("Do you really want to send back this application?");
	if(con)
	{
		document.getElementById('paraFrm').target = "_self";
    	document.getElementById('paraFrm').action = 'HardwareSoftwareRequestApprover_sendBackApplication.action';
		document.getElementById('paraFrm').submit();
	}	
}
 
function backtolistFun() 
{
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'HardwareSoftwareRequestApprover_backToList.action';
		document.getElementById('paraFrm').submit();
}
function printFun() {	
	window.print();
	}
function showRecord()
	{
		var statusVar = document.getElementById('paraFrm_status').value;
		var uploadFileName = document.getElementById('paraFrm_uploadFileName').value;
		if(statusVar=='A' || statusVar=='R' || statusVar=='P'){
			if(uploadFileName == '') {
			alert('File is not attached.');
			return false;
		  }
		}else if(uploadFileName == '') {
			alert('Please first upload the file...');
			return false;
		}
		document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = "HardwareSoftwareRequestApprover_viewAttachedProof.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
	}
	function uploadFile(fieldName) {
		var path = document.getElementById("paraFrm_dataPath").value;
		window.open('<%=request.getContextPath()%>/pages/recruitment/uploadResume.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
	}
		
	function viewUploadedFile(uploadFileName) {
		var uploadFileName = document.getElementById('paraFrm_ppoAttachement').value;
			
	if(uploadFileName == '') {
			alert('File not available.');
			return false;
		}
	
		document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = 'HardwareSoftwareRequestApprover_viewUploadedFile.action?uploadFileName='+uploadFileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
	}
	
	
	
	
	function closeFun() {
					window.close();
			}
//Added By Nilesh D on 14th Nov 2011.
   function forwardedEmp()
   {
   callsF9(500,325,'HardwareSoftwareRequestApprover_f9forwardToEmp.action');
  
   }
   			
function forwardFun() {
	var fwdempCode =  document.getElementById('paraFrm_fwdempCode').value;
  	if(fwdempCode=="") {
 		alert("Please select employee");
 		return false;
 	}
 	
 	var con = confirm("Do you really want to forward this application?");
 	if(con) {
 		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'HardwareSoftwareRequestApprover_forward.action';
		document.getElementById('paraFrm').submit(); 
 	} else {
 		return false;
 	}
}		
			
</script>
