<!-- Nilesh Dhandare -->

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@include file="/pages/common/commonValidations.jsp"%>


<script type="text/javascript">
var records = new Array();
</script>

<s:form action="BusinessRequirementDocument" validate="true"
	id="paraFrm" validate="true" theme="simple">

	<!--Hidden Fields -->
	<s:hidden name="brdSuperUserFlag" />
	<s:hidden name="viewCode" />
	<s:hidden name="attachFile" />
	<s:hidden name="dataPath" />
	<s:hidden name="status" />
	<s:hidden name="applnStatus" />
	<s:hidden name="checkAppRemove" />
	<s:hidden name="brdCode" />

	<s:hidden name="forMyActionPage" id="forMyActionPage" />
	<s:hidden name="myongoingProjectPage" id="myongoingProjectPage" />
	<s:hidden name="myCloseProjectPage" id="myCloseProjectPage" />
	<s:hidden name="myCancelProjectPage" id="myCancelProjectPage" />
	<s:hidden name="listType" />
	<!-- "myPage" variable is used for BRD SUPER USER -->
	<s:hidden name="myPage" id="myPage" />
	<!-- Hidden Fields End -->

	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong>
					</td>
					<td width="93%" class="txt"><strong class="text_head">
						Business Requirement Document</strong>
					</td>

					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>


		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<s:if test="stageFlag">
						<td width="5%" id="forwardButton" align="left">
							<input type="button" value="Forward" onclick="forwardApplication();" class="token"></td>
						<td id="updateActivityButton"  align="left" width="5%">
							<input type="button" value="Save Current Activity" onclick="checkForMandetoryFields();" class="token"></td>
						</s:if>
					<td width="70%" colspan="1"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>						
					<td width="20%" colspan="1">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		
		<!-- Activity Owner Section BEGINS -->
		<tr>
			<td colspan="4">
				<table width="100%" border="0" cellpadding="1" cellspacing="1" class="formbg">
					<tr>
						<td width="20%">
							 <label id="currentActivityOwner" name="currentActivityOwner" ondblclick="callShowDiv(this);"><%=label.get("currentActivityOwner")%></label> : 
						</td>
						
						<td width="80%">
							 <s:property value="activityOwnerName"/> 
						</td>
						
					</tr>
				</table>
			</td>
		</tr>
		<!-- Activity Owner Section BEGINS -->
		<s:if test="forwardStatus">
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">

				<tr>
					<td colspan="4">
					<table width="100%" border="0" cellpadding="1" cellspacing="1">
						<tr>
							<td width="25%"><label class="set"
								name="cancelProjectComments" id="cancelProjectComments"
								ondblclick="callShowDiv(this);"><%=label.get("cancelProjectComments")%></label>
							: <font color="red">*</font></td>

							<td colspan="3" width="75%"><s:textarea
								name="cancelProjectComments" cols="70" rows="6"
								onkeypress="return imposeMaxLength(event, this, 500);" /> <img
								src="../pages/images/zoomin.gif" height="12" align="bottom"
								width="12" theme="simple"
								onclick="javascript:callWindow('paraFrm_cancelProjectComments','cancelProjectComments','', '', '500','500');">(Max
							500 Chars)</td>
						</tr>
					</table>
					</td>
				</tr>

			</table>
			</td>
		</tr>
		</s:if>
		
		<!-- Checkbox Section -- BEGINS  -->
		<tr>
			<td colspan="4">
				<table width="100%" border="0" cellpadding="1" cellspacing="1" class="formbg">
					<tr>
						<td width="20%">
							<s:if test="stageFlag">
							<table>
								<tr>																								
									<!-- if Update Current Activity Status is checked then only show this button -->
									
								</tr>
							</table>
							</s:if>
						</td>
						
						<td width="15%">
							<label id="forward.section" name="forward.section" ondblclick="callShowDiv(this);"><%=label.get("forward.section")%></label>
						</td>
						
						<td width="20%">
							<s:checkbox name="forwardSectionCheckbox" onclick="hideShowRespectiveSection('forward');"/>
						</td>
						
						<td width="20%">
							<label id="current.activity.section" name="current.activity.section" ondblclick="callShowDiv(this);"><%=label.get("current.activity.section")%></label> 
						</td>
						
						<td width="15%">
							<s:checkbox name="currentActivityCheckbox" onclick="hideShowRespectiveSection('activity');"/>
						</td>
						
						<td width="10%">&nbsp;</td>
						
					</tr>
				</table>
			</td>
		</tr>
		<!-- Checkbox Section -- ENDS  -->
		
		<!-- Forward section Start -->
		<tr id="forwardSectionFlag">
			<td colspan="4">
				<table width="100%" border="0" cellpadding="1" cellspacing="1" class="formbg">
				<tr>
					<td width="100%" colspan="4"><b>Forward to </b></td>
				</tr>
				<s:if test="roleFlag">
					<tr>
						<td width="25%"><label id="role.name"
							name="role.name" ondblclick="callShowDiv(this);"><%=label.get("role.name")%></label>
						 : <font color="red">*</font></td>
						<td width="75%" colspan="3" id="ctrlShow"><s:select
							headerKey="-1" headerValue="------------Select--------------"
							name="empRole" cssStyle="width:160" list="mapRole" /></td>
					</tr>
				</s:if>
				<s:else>
					<tr>
						<td width="25%"><label id="role.name"
							name="role.name" ondblclick="callShowDiv(this);"><%=label.get("role.name")%></label>
						:</td>
						<td width="75%" colspan="3"><s:property value="empRole" /></td>
					</tr>
				</s:else>
				<tr>
					<td width="25%"><label id="employee.name"
						name="employee.name" ondblclick="callShowDiv(this);"><%=label.get("employee.name")%></label>
					 : <font color="red">*</font></td>
					<td width="75%" colspan="3"><s:textfield
						name="forwardEmpToken" cssStyle="background-color: #F2F2F2;"
						size="20" theme="simple" readonly="true" /> <s:textfield
						name="forwardEmpName" size="70" theme="simple" readonly="true"
						cssStyle="background-color: #F2F2F2;" /> <s:hidden
						name="fwdempCode" /> <img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="16" id='ctrlHide'
						onclick="javascript:forwardedEmp();">
					</td>
				</tr>
				
			<!-- 
				<tr>
					<td width="100%" colspan="4" align="center">
						<input type="button" value="Forward" onclick="forwardApplication();" class="token">
					</td>
				</tr>
			 -->	
				
			</table>
			</td>
		</tr>
		<!-- Forward section ENDS -->
		
		<!-- Activity Status Functionality -- BEGINS -->
		<tr id="currentActivitySectionFlag">
			<td colspan="4">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">
				<tr>
					<td width="100%" colspan="4"><b>Activity Status</b></td>
				</tr>
				 
				<tr>
					<td width="25%">
						<label id="currentActivityLabel" name="currentActivityLabel" ondblclick="callShowDiv(this);"><%=label.get("currentActivityLabel")%></label>
					 	: <font color="red">*</font>
					 </td>
					<td width="25%" id="ctrlShow">
						<s:select headerKey="-1" headerValue="------------Select--------------"
						name="currentActivityStatus" cssStyle="width:160" list="#{'S':'Started', 'N':'Not Started', 'H':'OnHold'}" />
					</td>
					
					<td width="20%" ><label id="forecastCompletionDateLabel"
						name="forecastCompletionDateLabel" ondblclick="callShowDiv(this);"><%=label.get("forecastCompletionDateLabel")%></label>
						: <font color="red">*</font>
					</td>
					<td width="30%" nowrap="nowrap">
						<s:textfield name="forecastedCompletionDate" onkeypress="return numbersWithHiphen();"/>&nbsp;
						<s:a href="javascript:NewCal('paraFrm_forecastedCompletionDate','DDMMYYYY');">
							<img src="../pages/images/Date.gif" id="ctrlHide"
								class="iconImage" height="16" align="absmiddle" width="16">
						</s:a>
					</td>
				</tr>
				
			<!-- 
				<tr>
					<td width="100%" colspan="4" align="center">
						<input type="button" value="Save Current Activity" onclick="checkForMandetoryFields();" class="token">
					</td>
				</tr>
			 -->	
				 
			</table>
			</td>
		</tr>
		<!-- Activity Status Functionality -- ENDS -->		
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="1" cellspacing="1">
					<tr>
						<td width="100%" colspan="4"><b>Information </b></td>
					</tr>

				<s:if test="commentFlag">
					<tr>
						<td colspan="1" width="25%"><label class="set"
							name="comments" id="comments" ondblclick="callShowDiv(this);"><%=label.get("comments")%></label>
						 : <font color="red">*</font></td>

						<td colspan="3" width="75%"><s:textarea name="comments"
							cols="78" rows="6"
							onkeypress="return imposeMaxLength(event, this, 500);" /><img
							id='ctrlHide' src="../pages/images/zoomin.gif" height="12"
							align="bottom" width="12" theme="simple"
							onclick="javascript:callWindow('paraFrm_comments','comments','', '', '500','500');"></td>
					</tr>
				</s:if>
				<s:else>
					<tr>
						<td colspan="1" width="25%"><label class="set"
							name="comments" id="comments" ondblclick="callShowDiv(this);"><%=label.get("comments")%></label>
						 : <font color="red">*</font></td>

						<td colspan="3" width="75%" id="ctrlShow"><s:textarea
							name="comments" cols="78" rows="6"
							onkeypress="return imposeMaxLength(event, this, 500);" /><img
							id='ctrlShow' src="../pages/images/zoomin.gif" height="12"
							align="bottom" width="12" theme="simple"
							onclick="javascript:callWindow('paraFrm_comments','comments','', '', '500','500');"></td>
					</tr>
				</s:else>


				<s:if test="stageFlag">
					<tr>
						<td width="25%" colspan="1"><label id="current.state"
							name="current.state" ondblclick="callShowDiv(this);"><%=label.get("current.state")%></label>
						 : <font color="red">*</font></td>
						<td width="75%" colspan="3" id="ctrlShow"><s:select
							headerKey="-1" headerValue="------------Select--------------"
							name="currentState" cssStyle="width:160" list="mapState" />
						</td>
					</tr>
				</s:if>
				<s:else>
					<tr>
						<td width="25%" colspan="1"><label id="current.state"
							name="current.state" ondblclick="callShowDiv(this);"><%=label.get("current.state")%></label>
						:</td>
						<td width="75%" colspan="3"><s:property value="currentState" />
						</td>
					</tr>
				</s:else>

			</table>
			</td>
		</tr>
		
		
		<!-- Stake Holders Added -->
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">


				<tr>
					<td colspan="1" width="27%" nowrap="nowrap">Stake Holders :</td>

					<td colspan="1" width="25%"><s:hidden name="empCode" /><s:hidden
						name="empToken" /><s:textfield name="empName" size="45"
						readonly="true" /></td>

					<td colspan="2" width="48%">&nbsp;<img
						src="../pages/common/css/default/images/search2.gif"
						class="iconImage" width="16" height="15" id="ctrlHide"
						onclick="javascript:getStakeHoldersEmp();" />&nbsp;&nbsp;<s:submit
						name="" id="ctrlHide" value=" Add" cssClass=" add"
						action="BusinessRequirementDocument_addStakeHolderEmployees"
						onclick="return callAddApprover();" /></td>


				</tr>


				<tr>
					<td colspan="4">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						align="right">

						<%
								int counter10 = 0;
								int counter2 = 0;
						%>

						<s:iterator value="approverStakeHoldersList" status="stat">

							<tr>
								<td colspan="1" width="27%">&nbsp;</td>
								<td colspan="1" width="25%" nowrap="nowrap"><%=++counter10%>)
								<s:hidden name="serialNum" /> <s:hidden
									name="stakeholderAppEmpNameItt" /> <s:property
									value="stakeholderAppEmpNameItt" /> <s:hidden name="empIttId" /></td>


								<td width="48%" colspan="2" id='ctrlHide'><a href="#"
									onclick="callForRemove(<%=counter10%>);"> Remove</a></td>

							</tr>
							<%
							counter2 = counter10;
							%>
						</s:iterator>
						<%
						counter2 = 0;
						%>
					</table>
					</td>
				</tr>


			</table>
			</td>
		</tr>
		<!-- Stake Holders Ends here-->
		
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">

				<tr>
					<td width="25%" colspan="1"><b>File Upload</b></td>
					<td width="75%" colspan="3" align="right">
						 <input type="button" class="token" value="Add More Attachment" onclick="addRowForwarApproverUploadDoc('forwardApproverUploadDocTableID');">
					</td>
				</tr>
				<s:if test="docFlag">
					<tr>
						<td width="25%" colspan="1"><label id="document.type"
							name="document.type" ondblclick="callShowDiv(this);"><%=label.get("document.type")%></label>
						:</td>
						<td width="75%" colspan="3" id="ctrlShow"><s:select
							headerKey="-1"
							headerValue="----------------------------Select----------------------------"
							name="documentAttached" list="mapDoc" /></td>
					</tr>
				</s:if>
				<s:else>
					<tr>
						<td width="25%" colspan="1"><label id="document.type"
							name="document.type" ondblclick="callShowDiv(this);"><%=label.get("document.type")%></label>
						:</td>
						<td width="75%" colspan="3"><s:property
							value="documentAttached" /></td>
					</tr>
				</s:else>

<!-- Mutiple File Upload -- BEGINS -->			
				<tr id="ctrlHide">
					<td width="25%" colspan="1">
						<label id="upload.document" name="upload.document" ondblclick="callShowDiv(this);"><%=label.get("upload.document")%></label> :
					</td>
					<td width="75%" colspan="3">
						<table id="forwardApproverUploadDocTableID" width="100%">
						<%
						int forwardApproverUploadDocCount = 0;
						%>
						<s:iterator value="forwardApproverUploadDocIterator">
							<tr>
								<td class="sortableTD" width="30%">
									<s:textfield size="50" name='forwardApproverUploadFileNameItr' readonly="true"  
										id="paraFrm_forwardApproverUploadFileNameItr<%=forwardApproverUploadDocCount%>"
										value='<s:property value="forwardApproverUploadFileNameItr"/>' /> 
								</td>
								 
								<td class="sortableTD" width="10%">
									 <input type="button" name="uploadDocumentButton" value="Select File" class="token"
										onclick="uploadFile('paraFrm_forwardApproverUploadFileNameItr<%=forwardApproverUploadDocCount%>');" />
								</td>
								
								<td class="sortableTD" width="10%" id="ctrlShow">
									<input type="button" name="show" value="Show" class="token"
										onclick="showRecord('paraFrm_forwardApproverUploadFileNameItr<%=forwardApproverUploadDocCount%>');" />
								</td>
								
								<td class="sortableTD" width="50%" id="ctrlHide">
									<img src="../pages/common/css/icons/delete.gif" 
										onclick="deleteCurrentRow(this);" />
								</td>
							</tr>
							<%
							++forwardApproverUploadDocCount;
							%>
						</s:iterator>
						
					 </table>
					</td>
				</tr>
<!-- Mutiple File Upload -- ENDS -->

			</table>
			</td>
		</tr>
		<!-- Initiator List -->
		
		<tr>
			<td colspan="4">
			<table width="100%" cellspacing="1" class="formbg" border="0">
				<tr>
					<td><strong>Initiator Logs :</strong></td>
				</tr>
				<tr align="center">

					<td colspan="4">
					<table width="100%" cellspacing="1" class="formbg" border="0">
						<tr>
							<td width="5%" class="formth" align="center"><label
								class="set" id="sr.no" name="sr.no"
								ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
							<td width="40%" class="formth" align="center"><label
								class="set" id="concern.person" name="concern.person"
								ondblclick="callShowDiv(this);"><%=label.get("concern.person")%></label></td>
							<td width="15%" class="formth" align="center"><label
								class="set" id="date" name="date"
								ondblclick="callShowDiv(this);"><%=label.get("date")%></label></td>
							<td class="formth" width="40%" align="center">
								<label class="set" id="document" name="document"
								ondblclick="callShowDiv(this);"><%=label.get("document")%></label>
							</td>

						</tr>
						<s:iterator value="initiatorList" status="stat">
							<tr>
								<td width="5%" class="sortableTD" align="right">1
									<s:hidden name="serialNo" />
								</td>

								<td width="40%" class="sortableTD">&nbsp; 
									<s:property value="initiatorName" />
									<s:hidden name="viewCode" />
									<s:hidden name="appCode" />
								</td>

								<td width="15%" align="center" class="sortableTD">&nbsp; 
									<s:property value="completedDate" />
									<s:hidden name="fileName" />
								</td>

							<!-- 
								<td class="sortableTD" width="30%">
									<a href="#"><s:property value="fileName"/></a>
								
									<s:if test="noInitiatorFileAttachedFlag=='true'">
										<a href="#" onclick="viewFile('<s:property value="viewCode"/>','<s:property value="appCode"/>')">View</a>
									</s:if>
								</td>
							 -->
							 	
							 <td width="40%" colspan="3" nowrap="nowrap">
						 		<table width="100%">
									<s:iterator value="initiatorUploadDocIterator" >
									<tr>
										<td class="sortableTD" nowrap="nowrap" align="left">
											<a href="#" title="Click here to view uploaded document" onclick="viewUploadedDocument('<s:property value='initiatorUploadDocNameItr'/>');"><font color="blue"><s:property value='initiatorUploadDocNameItr'/></font></a>  
										</td>
									</tr>
							  		</s:iterator>
					 		  	</table>
						     </td>
						  </tr>
						</s:iterator>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- end of Initiator List -->


		<!--  List Starts -->
		<tr>
			<td colspan="4">
			<table width="100%" cellspacing="1" class="formbg" border="0">
				<tr>
					<td><strong>Activity Logs :</strong></td>
				</tr>
				<tr align="center">

					<td colspan="4">
					<table width="100%" cellspacing="1" class="formbg" border="0">
						<tr>
							<td width="5%" class="formth" align="center"><label
								class="set" id="sr.no" name="sr.no"
								ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
							<td width="15%" class="formth" align="center"><label
								class="set" id="concern.person" name="concern.person"
								ondblclick="callShowDiv(this);"><%=label.get("concern.person")%></label></td>
							<td width="10%" class="formth" align="center"><label
								class="set" id="date" name="date"
								ondblclick="callShowDiv(this);"><%=label.get("date")%></label></td>
							<td width="10%" class="formth" align="center"><label
								class="set" id="state.name" name="state.name"
								ondblclick="callShowDiv(this);"><%=label.get("state.name")%></label></td>
							<td width="30%" class="formth" align="center"><label
								class="set" id="comments" name="comments"
								ondblclick="callShowDiv(this);"><%=label.get("comments")%></label></td>
							<td class="formth" width="10%" align="center">
								<label class="set" id="document" name="document"
								ondblclick="callShowDiv(this);"><%=label.get("document")%></label>
							</td>
							
							<td class="formth" width="10%" align="center">
								<label class="set" id="currentActivityLabel" name="currentActivityLabel"
								ondblclick="callShowDiv(this);"><%=label.get("currentActivityLabel")%></label>
							</td>
							
							<td class="formth" width="10%" align="center">
								<label class="set" id="forecastCompletionDateLabel" name="forecastCompletionDateLabel"
								ondblclick="callShowDiv(this);"><%=label.get("forecastCompletionDateLabel")%></label>
							</td>
						</tr>

						<%
						int d = 0;
						%>
						<s:if test="logList">

							<s:iterator value="logList" status="stat">
								<tr>
									<td class="sortableTD" align="center"><s:hidden
										name="serialNo" /><%=++d%></td>

									<td class="sortableTD">&nbsp; 
										<s:property value="concernedPerson" />
										<s:hidden name="viewCode" />
										<s:hidden name="appCode" />
									</td>

									<td align="center" class="sortableTD">&nbsp; 
										<s:property value="applDate" />
									</td>

									<td class="sortableTD"><s:property
										value="logState" /></td>

									<td class="sortableTD" align="left">  
									  <s:property value="empComments" />
									<!-- 
										<table width="100%">
										  <tr>
										  	<td>
										  		<s:textarea name="empComments" id="<%="empComments"+d%>" disabled="true" rows="2" cols="25"/>
												<s:hidden name="logFileName" />
										  	</td>
										  	
										  	<td>
										  		<img id='ctrlShow' src="../pages/images/zoomin.gif" height="12"
												align="bottom" width="12" theme="simple"
												onclick="javascript:callWindow('<%="empComments"+d%>', 'comments', 'readonly', '', '500');">
										  	</td>
										  </tr>
										</table>
									 -->
									</td>

									<td class="sortableTD">
										<!-- 
											<s:hidden name="logFileName" />
											<s:if test="noFileAttachedFlag=='true'">
												<a href="#" onclick="viewAttachedFile('<s:property value="logFileName"/>','<s:property value="viewCode"/>','<s:property value="appCode"/>')">View</a>
											</s:if>
											<s:else>&nbsp;&nbsp;</s:else>
										 -->
										
										<table width="100%">
											<s:iterator value="forwardedApproverUploadDocActivityLogIterator">
											<tr>
												<td class="sortableTD" nowrap="nowrap">
													<a href="#" title="Click here to view uploaded document" onclick="viewUploadedDocument('<s:property value='forwardedApprActivityLogUploadDocItr'/>');"><font color="blue"><s:property value='forwardedApprActivityLogUploadDocItr'/></font></a>  
												</td>
											</tr>
							  				</s:iterator>
					 		  			</table>
									</td>
									
									<td class="sortableTD">
										<s:property value="currentActivityStatusItr"/>
									</td>
									<td class="sortableTD" align="center">
										<s:property value="foreCastedDateItr"/>
									</td>
								</tr>
							</s:iterator>

						</s:if>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<!--List Ends-->


		<!-- New Block end -->
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">
				<tr>
					<td colspan="4" width="100%"><b>Project Information</b></td>
				</tr>
				<tr>
					<td colspan="1" width="25%"><label class="set"
						name="brd.ticket.no" id="brd.ticket.no"
						ondblclick="callShowDiv(this);"><%=label.get("brd.ticket.no")%></label>
					:</td>
					<td colspan="1" width="25%"><s:property value="brdNumber" /></td>

					<td colspan="1" width="20%"><label class="set"
						name="proj.name" id="proj.name" ondblclick="callShowDiv(this);"><%=label.get("proj.name")%></label>
					:</td>
					<s:if test="brdSuperUserFlag=='BRDSuperUser'&& forwardStatus">
						<td colspan="1" width="30%"><s:textfield size="90"
						theme="simple" maxlength="500" name="projectName"
						onkeypress="return isCharactersKey(event)" /></td>
					</s:if>
					<s:else>
						<td colspan="1" width="30%"><s:property value="projectName" /></td>
					</s:else>
				</tr>
	
				<tr>
					<td width="25%"><label class="set"
						name="project.type" id="project.type" ondblclick="callShowDiv(this);"><%=label.get("project.type")%></label>
					: <font color="red">*</font></td>
					<s:if test="brdSuperUserFlag=='BRDSuperUser'&& forwardStatus">					
					<td width="25%">
						<s:select name="projectType" cssStyle="width:150" theme="simple" headerKey="-1" headerValue="------- Select -------" list="mapProjectType"></s:select>
					</td>
					</s:if>
					<s:else><td width="25%">
						<s:select name="projectType" cssStyle="width:150" disabled="true" theme="simple" headerKey="-1" headerValue="------- Select -------" list="mapProjectType"></s:select>
						</td>
					</s:else>
					<td width="20%">
						<label class="set" name="project.classification" id="project.classification"
						ondblclick="callShowDiv(this);"><%=label.get("project.classification")%></label> : 
					<font color="red">*</font></td>
					<s:if test="brdSuperUserFlag=='BRDSuperUser'&& forwardStatus">
					<td width="30%">
						<s:select name="projectClassification" cssStyle="width:150" theme="simple" headerKey="-1" headerValue="----- Select -----" list="mapProjectClassification"/>
					</td>
					</s:if>
					<s:else><td width="30%">
						<s:select name="projectClassification" disabled="true" cssStyle="width:150" theme="simple" headerKey="-1" headerValue="----- Select -----" list="mapProjectClassification"/>
					</td>
					</s:else>	
				</tr>
				
				<tr>
					<td colspan="1" width="25%"><label class="set"
						name="start.date" id="start.date" ondblclick="callShowDiv(this);"><%=label.get("start.date")%></label>
					:</td>
					<td colspan="1" width="25%">
						<s:property value="startDate" />
						<s:hidden name="startDate" />
					</td>
					<td colspan="1" width="20%"><label class="set"
						name="expected.date" id="expected.date"
						ondblclick="callShowDiv(this);"><%=label.get("expected.date")%></label>
					:</td>
					<td colspan="1" width="30%"><s:property
						value="expectedEndDate" /></td>
				</tr>

				<tr>
					<td colspan="1" width="25%">
						<label class="set"
						name="allocated.budget" id="allocated.budget"
						ondblclick="callShowDiv(this);"><%=label.get("allocated.budget")%></label> :
					</td>
					<td colspan="1" width="25%">
						<s:property value="allocatedBudget" />
					</td>
					<td colspan="1" width="20%">
						<label class="set" name="priorityLable" id="priorityLable"
						ondblclick="callShowDiv(this);"><%=label.get("priorityLable")%></label> : 
					<font color="red">*</font></td>
					<s:if test="brdSuperUserFlag=='BRDSuperUser'&& forwardStatus">
						<td colspan="1" width="30%">
						<s:select name="priority" headerKey="" headerValue="----- Select -----" list="#{'Critical':'Critical', 'Routine':'Routine'}"/>
					</td>	
					</s:if>
					<s:else>
						<td colspan="1" width="30%">
						<s:select disabled="true" name="priority" headerKey="" headerValue="----- Select -----" list="#{'Critical':'Critical', 'Routine':'Routine'}"/>
					</td>	
					</s:else>
				</tr>

				<tr>
					<td width="25%">
						<label class="set" name="est.annual.financial.benefit" id="est.annual.financial.benefit" ondblclick="callShowDiv(this);"><%=label.get("est.annual.financial.benefit")%></label> : 
					</td>
					<td colspan="1" width="25%">
						<s:property value="projectAnnualFinancialBenefit" />
					</td>
					<td width="20%">
						<label class="set" name="businessUnitLbl" id="businessUnitLbl" ondblclick="callShowDiv(this);"><%=label.get("businessUnitLbl")%></label> : 
					<font color="red">*</font></td>
					
					<s:if test="brdSuperUserFlag=='BRDSuperUser'&& forwardStatus">
						<td colspan="1" width="30%">						
						<s:select name="businessUnitID" cssStyle="width:150" theme="simple" headerKey="-1" headerValue="--------Select---------" list="mapBusinessUnitID"></s:select>																													
					</s:if>
					<s:else>
					<td colspan="1" width="30%">
						<s:select name="businessUnitID" cssStyle="width:150" disabled="true" theme="simple" headerKey="-1" headerValue="--------Select---------" list="mapBusinessUnitID"></s:select>						
						</td>												
					</s:else>
					
				</tr>
				<tr>
					<td width="25%">
						<label class="set" name="rankLbl" id="rankLbl" ondblclick="callShowDiv(this);"><%=label.get("rankLbl")%></label> : 
					</td>
					<s:if test="brdSuperUserFlag=='BRDSuperUser' && forwardStatus">
						<td colspan="3" width="75%">
						<s:textfield size="19" theme="simple" maxlength="2" name="rank"/>
					</td>
					</s:if>
					<s:else>
						<td colspan="3" width="75%">
						<s:textfield size="19" theme="simple" maxlength="2" name="rank" disabled="true" />
					
					</td>
					</s:else>
				</tr>
				
				<tr>
					<td colspan="4" width="100%">&nbsp;</td>
				</tr>

				<tr>
					<td colspan="1" width="25%"><label class="set"
						name="business.objective" id="business.objective"
						ondblclick="callShowDiv(this);"><%=label.get("business.objective")%></label>
					:</td>

					<td colspan="3" width="75%"><s:property
						value="businessObjective" /></td>
				</tr>

				<tr>
					<td colspan="4" width="100%">&nbsp;</td>
				</tr>


				<tr>
					<td colspan="1" width="25%"><label class="set"
						name="project.closure.criteria" id="project.closure.criteria"
						ondblclick="callShowDiv(this);"><%=label.get("project.closure.criteria")%></label>
					:</td>

					<td colspan="3" width="75%"><s:property
						value="projectClosureCriteria" /></td>

				</tr>

				<tr>
					<td colspan="4" width="100%">&nbsp;</td>
				</tr>

				<tr>
					<td colspan="1" width="25%"><label class="set"
						name="constraints" id="constraints"
						ondblclick="callShowDiv(this);"><%=label.get("constraints")%></label>
					:</td>
					<td colspan="3" width="75%"><s:property value="constraints" /></td>
				</tr>

				<tr>
					<td colspan="4" width="100%">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="1" width="25%"><label class="set"
						name="assumptions" id="assumptions"
						ondblclick="callShowDiv(this);"><%=label.get("assumptions")%></label>
					:</td>
					<td colspan="3" width="75%"><s:property value="assuptions" /></td>
				</tr>
				
				<tr>
					<td colspan="4" width="100%">&nbsp;</td>
				</tr>
				
				<tr>
					<td colspan="1" width="25%"><label id="applicantCommentsLabel" name="applicantCommentsLabel" ondblclick="callShowDiv(this);"><%=label.get("applicantCommentsLabel")%></label>
					:</td>
					<td colspan="3" width="75%"><s:property value="applicantsComments" /></td>
				</tr>
				
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%" colspan="4"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script>
 onLoadShowThisData();
function onLoadShowThisData() {
 	try {
 		var table = document.getElementById('forwardApproverUploadDocTableID'); 
		var rowCount = table.rows.length; 
		var iteration = rowCount;
		if (iteration==0) {
		 	addRowForwarApproverUploadDoc('forwardApproverUploadDocTableID');
		 }
	} catch(e) {
		alert("Exception while loading page >>"+e);
	}
}	
// Add row Witness Details Begins
function addRowForwarApproverUploadDoc(tableID) {
	try {
		  var table = document.getElementById(tableID); 
		  var rowCount = table.rows.length; 
		  var iteration = rowCount;
		  var row = table.insertRow(rowCount);
						
		  var cell1= row.insertCell(0);
		  var column1 = document.createElement('input');
		  cell1.className='sortableTD';
		  column1.type = 'text';
  		  column1.name = 'forwardApproverUploadFileNameItr';
		  column1.id = 'paraFrm_forwardApproverUploadFileNameItr'+iteration;
		  column1.readonly = 'readonly';
		  column1.size ='50';
		  cell1.width = '30%';
		  cell1.appendChild(column1);
		  
		  var cell2 = row.insertCell(1);
		  var column2 = document.createElement('input');
		  cell2.className='sortableTD';
		  column2.type='button';
		  column2.align='center';
		  column2.name='uploadDocumentButton';
		  column2.value='Select File';
		  column2.className='token';
		  column2.onclick = function(){
		  						var uploadField= 'paraFrm_forwardApproverUploadFileNameItr'+iteration;
		  						uploadFile(uploadField); 
		  					};
		  cell2.width = '10%';				
		  cell2.appendChild(column2);
		  		  
		  var cell3= row.insertCell(2);
		  var column3 = document.createElement('input');
		  cell3.className='sortableTD';
		  column3.type='button';
		  column3.align='center';
		  column3.name='showBtn';
		  column3.value='Show';
		  column3.className='token';
		  column3.onclick=function(){
		  					return showRecord('paraFrm_forwardApproverUploadFileNameItr'+iteration);
		  				 };
		  cell3.width = '10%';			 
		  cell3.appendChild(column3);

		  var cell4= row.insertCell(3);
		  var column4 = document.createElement('img');
		  cell4.className='sortableTD';
		  column4.type='image';
		  column4.src="../pages/common/css/icons/delete.gif";
		  column4.align='absmiddle';
	  	  column4.id='img'+ iteration;
		  column4.theme='simple';
		  column4.onclick=function(){
		   					deleteCurrentRow(this);
		  				};
		  cell4.width = '50%';				
		  cell4.appendChild(column4);
	}catch(e) {
		alert("Error while adding row >>"+e);		
	}	  
} 

//Delete Row - BEGINS
function deleteCurrentRow(obj){ 
	var delRow = obj.parentNode.parentNode;
	var tbl = delRow.parentNode.parentNode;
	var rIndex = delRow.sectionRowIndex;
	var rowArray = new Array(delRow);
	var con = confirm("Do you really want to delete this uploaded document?");
	if(con) {	
		deleteRows(rowArray);
	} else {
		return false;
	}
}

function deleteRows(rowObjArray){	
	for (var i=0; i<rowObjArray.length; i++) {			
		var rIndex = rowObjArray[i].sectionRowIndex;
		rowObjArray[i].parentNode.deleteRow(rIndex);
	}
}

//View uploaded documents - BEGINS
function showRecord(fileName) {
	var fileName = document.getElementById(fileName).value;
	if(fileName == "") {
		alert("Please upload file.");
		return false ; 
	}
	document.getElementById('paraFrm').target = "_blank";
	document.getElementById('paraFrm').action = 'BusinessRequirementDocument_openAttachedFile.action?fileName='+fileName;
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target = "main";
	return true ; 
}

function viewUploadedDocument(fileName) {
	document.getElementById('paraFrm').target = "_blank";
	document.getElementById('paraFrm').action = 'BusinessRequirementDocument_openAttachedFile.action?fileName='+fileName;
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target = "main";
}	
//View uploaded documents - BEGINS
function forwardApplication() {
	var fwdempCode =  document.getElementById('paraFrm_fwdempCode').value;
	var empRole =  document.getElementById('paraFrm_empRole').value;
	var status =  document.getElementById('paraFrm_status').value;
	var comments =  document.getElementById('paraFrm_comments').value;
	var currentState =  document.getElementById('paraFrm_currentState').value;
	//var uploadDocName =  document.getElementById('paraFrm_uploadDocName').value;
	var empName = document.getElementById('paraFrm_empName').value;
	var priorityVar = document.getElementById('paraFrm_priority').value;
	var classificationVar = document.getElementById('paraFrm_projectClassification').value
	var projectTypeVar= document.getElementById('paraFrm_projectType').value;
	var buss_unit= document.getElementById('paraFrm_businessUnitID').value;
	//var project_name= document.getElementById('paraFrm_projectName').value;
	
	if(empRole=="-1") {
		alert("Please select Employee Role");
		document.getElementById('paraFrm_empRole').focus();
		return false;
	}
	if(fwdempCode=="") {
		alert("Please Select Employee to forward application");
		return false;
	}
	/*if(project_name==""){
		alert("Please Enter Project Name");
		return false;
	}*/
	if(projectTypeVar=="-1"){
	 	alert("Please Select Project Type");
	    return false;
 	}
 	if(classificationVar=="-1"){
	 	alert("Please Select Classification");
	  	return false;
 	}   
	if(priorityVar==""){
	 	alert("Please Select Priority");
	 	 return false;
 	}  	
 	if(buss_unit==""){
	 	alert("Please Select Business Unit");
	 	 return false;
 	} 
	if(empName!="") 	{
		alert("Please add Stake Holder");
		return false;
	}
	if(comments=="") {
 		alert("Please enter comments");
 		document.getElementById('paraFrm_comments').focus();
 		return false;
 	}
	if (comments!="") {
 		if (eval(comments.length) > 500) {	
 			alert("Only 500 characters are allowed.");
 			document.getElementById('paraFrm_comments').focus();
 			return false;
 		}
 	}	
 	if(currentState=="-1" || currentState=="") {
 		alert("Please select Current State");
 		document.getElementById("paraFrm_currentState").focus();
 		return false;
 	}
/* 
 	if(uploadDocName!="") {
 		alert("Please attach the file")
 		return false;
 	}
*/
	if(status=="B") {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'BusinessRequirementDocument_updateApplication.action';
		document.getElementById('paraFrm').submit(); 
	} else {	
   		var con = confirm("Do you really want to forward this application?");
   		if (con) {
   			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action = 'BusinessRequirementDocument_forwardApplication.action';
			document.getElementById('paraFrm').submit(); 
   		} else {
   			return false;
   		}
	} 
}


/* function sendbackFun() {
	var currentState =  document.getElementById('paraFrm_currentState').value;
	var fwdempCode =  document.getElementById('paraFrm_fwdempCode').value;
	var empRole =  document.getElementById('paraFrm_empRole').value;
	 if(currentState=="-1"){
		 alert("Please select Current State");
 		return false;
 	}
	if(empRole=="-1"){
		alert("Please select Employee Role");
		return false;
	}
	if(fwdempCode==""){
		alert("Please Select Employee to forward application");
		return false;
	}
	var con=confirm('Do you want to send back the application ?');
	if(con){
	    document.getElementById('paraFrm_applnStatus').value = 'B';	
		document.getElementById('paraFrm').action = 'BusinessRequirementDocument_sendBackApplication.action';
		document.getElementById('paraFrm').submit();
		
	}
}*/

function closeprojectFun() {
   var comments = document.getElementById('paraFrm_comments').value;
   var currentState =  document.getElementById('paraFrm_currentState').value;
   var brdFlag=document.getElementById('paraFrm_brdSuperUserFlag').value;
   if(comments=="") {
	 alert("Please enter Comments");
	 return false;
   }  
   if (comments!="") {
 	 if (eval(comments.length) > 500) {	
 		 alert("Only 500 characters are allowed.");
 		 document.getElementById('paraFrm_comments').focus();
 		 return false;
 	 }
  }			
  if(currentState=="-1") {
 	 alert("Please select Current State");
 	 return false;
  }		
  var con=confirm('Do you want to Close the Project ?');
	 if(con){
     	 document.getElementById('paraFrm_applnStatus').value = 'C';	
		 document.getElementById('paraFrm').action = 'BusinessRequirementDocument_closeApplication.action';
		 document.getElementById('paraFrm').submit();
	}
}

function backtolistFun() {
	document.getElementById('paraFrm').target = "_self";
	if(document.getElementById('paraFrm_brdSuperUserFlag').value == 'BRDSuperUser') {
		document.getElementById('paraFrm').action = 'BRDSuperUser_viewBrdApplicationList.action';
	} else {
		document.getElementById('paraFrm').action = 'BusinessRequirementDocument_back.action';
	}
	document.getElementById('paraFrm').submit();
}

function uploadFile(fieldName) {
		var dataPath = document.getElementById('paraFrm_dataPath').value;
		window.open('<%=request.getContextPath()%>/pages/recruitment/uploadResume.jsp?path=' + dataPath + '&field=' + 
		fieldName, '', 'width=400, height=200, scrollbars=no, resizable=no, top=100, left=150');
}
	
function openAttachedFile() {
		document.getElementById('paraFrm').action = 'BusinessRequirementDocument_openAttachedFile.action';  
		document.getElementById('paraFrm').submit();
}

function viewFile(viewCode,appCode) {
	var fileName = document.getElementById('paraFrm_fileName').value;
	if(fileName==""){
		alert("No File Attached !")
		return false;
	}
		document.getElementById('paraFrm').action = "BusinessRequirementDocument_viewInitiatorAttachedFile.action?viewCode11="+viewCode+"&appCode11="+appCode; 
		document.getElementById('paraFrm').submit();
}

function resetFun() {
		document.getElementById('paraFrm').target = "_self";     	
  		document.getElementById('paraFrm').action = 'BusinessRequirementDocumentApproval_reset.action';
     	document.getElementById('paraFrm').submit();
}
   
function forwardedEmp() {
   callsF9(500,325,'BusinessRequirementDocument_f9employee.action');
} 
   
function isNumWithCharKey(evt) {
	/* alert("Only Numbers and Special Characters are allowed here!");*/
        var charCode = (evt.which) ? evt.which : event.keyCode
        if((charCode!=37 ) &&(charCode!=46) ) {
             if (charCode > 31 && (charCode < 48 || charCode > 57) || (charCode==44) && (charCode==46) && (charCode==37))
                return false;
             }
        return true;
}		

function viewAttachedFile(name,viewCode,appCode) {		
	try{
		var logFileName = document.getElementById('paraFrm_logFileName').value;
  		if(name=="") {
  			alert("No File Attached !")
  			return false;
  		}
		document.getElementById('paraFrm').target = '_blank';
    	document.getElementById('paraFrm').action = "BusinessRequirementDocument_viewAttachedFile.action?viewCode11="+viewCode+"&appCode11="+appCode+"&logFileName="+name;
		document.getElementById('paraFrm').submit();
	} catch(e) {
		alert(e);
	}
}

function callAddApprover(){
  var empId = document.getElementById('paraFrm_empCode').value;
     if(empId=="") {
     	alert("Please Select Stake Holders");
     	return false;
  	 }
     return true;
}
   
   
function callForRemove(id) {
    var conf=confirm("Are you sure !\n You want to Remove this record ?");
  	if(conf){
		document.getElementById('paraFrm_checkAppRemove').value=id;
		document.getElementById('paraFrm').target="_self";
		document.getElementById("paraFrm").action="BusinessRequirementDocument_removeStakeHoldersApprover.action";
		document.getElementById("paraFrm").submit();
	}	
    return true;
}
   
function showEmployee(){
   	if(navigator.appName == 'Netscape') {
		var win = window.open('', 'win', 'top = 150, left = 200, width = 500, height = 325, scrollbars = yes, status = no, resizable = yes');
	} else {
		var win = window.open('', 'win', 'top = 230, left = 200, width = 500, height =352, scrollbars = yes, status = no, resizable = yes');
	}		
		document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action ="BusinessRequirementDocument_f9employee.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
    // callsF9(500,325,'BusinessRequirementDocument_f9employee.action')
}
   
function getStakeHoldersEmp() {
	try {
		callsF9(500,325,'BusinessRequirementDocument_f9stakeHolder.action');
	} catch(e) {
		alert(e);
	} 
}
   
function sendbackFun() {
    var comments =  document.getElementById('paraFrm_comments').value;
    if(comments=="") {
    	alert("Please enter comments");
    	return false;
    }

   	var con=confirm('Do you want to send back the application ?');
	 if(con){
	    document.getElementById('paraFrm_applnStatus').value = 'B';	
	 	document.getElementById('paraFrm').action = 'BusinessRequirementDocument_sendBackApplication.action';
		document.getElementById('paraFrm').submit();
	}
 	window.close();	
}

function printFun() {	
	window.print();
}
   
/*function getStakeHoldersEmp(){
	try{
	if(navigator.appName == 'Netscape') {
			var win = window.open('', 'win', 'top = 150, left = 200, width = 500, height = 325, scrollbars = yes, status = no, resizable = yes');
		} else {
			var win = window.open('', 'win', 'top = 230, left = 200, width = 500, height =352, scrollbars = yes, status = no, resizable = yes');
		}		
		document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action ="BusinessRequirementDocument_f9stakeHolder.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';	
	 //callsF9(500,325,'BusinessRequirementDocument_f9stakeHolder.action');		 
	}
	catch(e){
		alert(e);
	} 	
}*/

function imposeMaxLength(Event, Object, MaxLen) {
  return (Object.value.length <= MaxLen)||(Event.keyCode == 8 ||Event.keyCode==46||(Event.keyCode>=35&&Event.keyCode<=40))
}	

function checkForMandetoryFields() {
	var currentActivity = trim(document.getElementById('paraFrm_currentActivityStatus').value);
	var forecastCompletionDate = trim(document.getElementById('paraFrm_forecastedCompletionDate').value);
	var startDate = document.getElementById('paraFrm_startDate').value; 
	var comments =  trim(document.getElementById('paraFrm_comments').value);	
	var currentState =  document.getElementById('paraFrm_currentState').value;
	if (currentActivity == "-1") {
		alert("Please select "+ document.getElementById('currentActivityLabel').innerHTML.toLowerCase());
		document.getElementById('paraFrm_currentActivityStatus').focus();
		return false;
	}	 
	if (forecastCompletionDate == "") {
		alert("Please select or enter "+ document.getElementById('forecastCompletionDateLabel').innerHTML.toLowerCase());
		document.getElementById('paraFrm_forecastedCompletionDate').focus();
		return false;
	} else {
		if(!validateDate('paraFrm_forecastedCompletionDate', 'forecastCompletionDateLabel')) {
			return false;
		}		
		if(!dateDifferenceEqual(startDate, forecastCompletionDate, 'paraFrm_forecastedCompletionDate', 'start.date', 'forecastCompletionDateLabel')) {
			return false;
		}
	}		
	if(comments=="") {
 		alert("Please enter " + document.getElementById('comments').innerHTML.toLowerCase());
 		document.getElementById('paraFrm_comments').focus();
 		return false;
	}
	if (comments!="") {
 		if (eval(comments.length) > 500) {	
 			alert("Only 500 characters are allowed.");
 			document.getElementById('paraFrm_comments').focus();
 			return false;
 		}
 	}	
 	if(currentState=="-1") {
 		alert("Please select " + document.getElementById('current.state').innerHTML.toLowerCase());
 		document.getElementById('paraFrm_currentState').focus();
 		return false;
 	}
	var con = confirm("Do you really want to save this activity?");
	if (con) {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'BusinessRequirementDocument_saveCurrentActivityStatus.action';
		document.getElementById('paraFrm').submit();
	} else {
		return false;
	}
}

sectionOnLoad();
function sectionOnLoad() {
	document.getElementById('currentActivitySectionFlag').style.display = "none";
	document.getElementById('forwardSectionFlag').style.display = "none";	
	document.getElementById('forwardButton').style.display = "none";
	document.getElementById('updateActivityButton').style.display = "none";
}

function hideShowRespectiveSection(section) {
	var forwardSection = document.getElementById('paraFrm_forwardSectionCheckbox').checked; 
	var currentActivitySection = document.getElementById('paraFrm_currentActivityCheckbox').checked; 	
	if (section == "forward") {
			document.getElementById('paraFrm_currentState').value = "-1";
			//document.getElementById('paraFrm_comments').value = "";
			document.getElementById('paraFrm_currentActivityStatus').value = "-1";
			document.getElementById('paraFrm_forecastedCompletionDate').value = "";
			
		if (forwardSection) {
			document.getElementById('forwardSectionFlag').style.display = "";
			document.getElementById('currentActivitySectionFlag').style.display = "none";
			document.getElementById('paraFrm_currentActivityCheckbox').checked = false;
			
			document.getElementById('forwardButton').style.display = "";
			document.getElementById('updateActivityButton').style.display = "none";
		} else {
			document.getElementById('currentActivitySectionFlag').style.display = "";
			document.getElementById('forwardSectionFlag').style.display = "none";
			document.getElementById('paraFrm_forwardSectionCheckbox').checked = false;
			document.getElementById('paraFrm_currentActivityCheckbox').checked = true;
			
			document.getElementById('forwardButton').style.display = "none";
			document.getElementById('updateActivityButton').style.display = "";
		}	
	}
	
	
	if (section == "activity") {
			document.getElementById('paraFrm_empRole').value = "-1";
			document.getElementById('paraFrm_forwardEmpToken').value = "";
			document.getElementById('paraFrm_forwardEmpName').value = "";
			document.getElementById('paraFrm_fwdempCode').value = "";
			document.getElementById('paraFrm_currentState').value = "-1";
			//document.getElementById('paraFrm_comments').value = "";
			
		if (currentActivitySection) {
			document.getElementById('currentActivitySectionFlag').style.display = "";
			document.getElementById('forwardSectionFlag').style.display = "none";
			document.getElementById('paraFrm_forwardSectionCheckbox').checked = false;
			
			document.getElementById('forwardButton').style.display = "none";
			document.getElementById('updateActivityButton').style.display = "";
		} else {
			document.getElementById('forwardSectionFlag').style.display = "";
			document.getElementById('currentActivitySectionFlag').style.display = "none";
			document.getElementById('paraFrm_currentActivityCheckbox').checked = false;
			document.getElementById('paraFrm_forwardSectionCheckbox').checked = true;
			
			document.getElementById('forwardButton').style.display = "";
			document.getElementById('updateActivityButton').style.display = "none";
		}
	}
}


function cancelFun() {
	var cancelProjectComments = trim(document.getElementById('paraFrm_cancelProjectComments').value);
	if (cancelProjectComments == '') {
		alert("Please enter your comments.");
		document.getElementById('paraFrm_cancelProjectComments').focus();
		return false;
	}
	var con = confirm("Do you really want to cancel this project?");
	if (con) {
		document.getElementById("paraFrm").target = "_self";
		document.getElementById("paraFrm").action = "BusinessRequirementDocument_cancelProject.action";
		document.getElementById("paraFrm").submit();
	} else {
		return false;
	}
}


</script>
