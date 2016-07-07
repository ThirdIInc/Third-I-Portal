<!-- Nilesh Dhandare -->

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@include file="/pages/common/commonValidations.jsp"%>


<s:form action="BusinessRequirementDocument" validate="true" id="paraFrm" validate="true" theme="simple">

	<!--Hidden Fields -->
	<s:hidden name="brdCode" />
	<s:hidden name="attachFile" />
	<s:hidden name="dataPath" />
	<s:hidden name="status" />
	<s:hidden name="checkRemove" />
	<s:hidden name="applicantEditableFlag" />
	
	<s:hidden name="forMyActionPage" id="forMyActionPage" />
	<s:hidden name="myongoingProjectPage" id="myongoingProjectPage" />
	<s:hidden name="myCloseProjectPage" id="myCloseProjectPage" />
	<s:hidden name="myCancelProjectPage" id="myCancelProjectPage" />
	<s:hidden name="listType" />
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
						height="25" /></strong></td>
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
					<td width="80%" colspan="1"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="20%" colspan="3">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		
		<!-- Activity Owner Section BEGINS -->
		<s:if test="activityOwnerPresentFlag">
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
		</s:if>
		<!-- Activity Owner Section BEGINS -->
		
		<!-- Cancel Project Comments -- BEGINS -->
		<s:if test="projectCommentsFlag">
		<tr id='ctrlHide'>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="formbg">
				 
				<tr>
					<td colspan="4">
					<table width="100%" border="0" cellpadding="1" cellspacing="1">
						<tr>
							<td width="25%"><label class="set"
								name="cancelProjectComments" id="cancelProjectComments"
								ondblclick="callShowDiv(this);"><%=label.get("cancelProjectComments")%></label>
							: <font color="red">*</font></td>
							 
							<td colspan="3" width="75%">
								<s:textarea name="cancelProjectComments" cols="70" rows="6"
								onkeypress="return imposeMaxLength(event, this, 500);" />
								<img src="../pages/images/zoomin.gif" height="12"
									align="bottom" width="12" theme="simple"
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
		<!-- Cancel Project Comments -- ENDS -->
		
		
		<s:if test="logList">
			<tr>
				<td colspan="4">
				<table width="100%" cellspacing="1" cellpadding="2" class="formbg"
					border="0">
					<tr>
						<td><strong>Activity Logs :</strong></td>
					</tr>
					<tr align="center">

						<td colspan="4">
						<table width="100%" cellspacing="1" class="formbg" border="0">
							<tr>
								<td width="5%" class="formth"><label class="set"
									id="sr.no" name="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
								<td width="15%" class="formth"><label class="set"
									id="concern.person" name="concern.person"
									ondblclick="callShowDiv(this);"><%=label.get("concern.person")%></label></td>
								<td width="10%" class="formth"><label class="set" id="date"
									name="date" ondblclick="callShowDiv(this);"><%=label.get("date")%></label></td>
								<td width="10%" class="formth"><label class="set"
									id="state.name" name="state.name"
									ondblclick="callShowDiv(this);"><%=label.get("state.name")%></label></td>
								<td width="30%" class="formth"><label class="set"
									id="comments" name="comments" ondblclick="callShowDiv(this);"><%=label.get("comments")%></label></td>
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
							<s:if test="activityList">

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
										<s:property value="applDate" /></td>

										<td class="sortableTD"><s:property
											value="logState" />&nbsp;&nbsp;</td>

										<td class="sortableTD">  
											<s:property value="empComments" />
									<!-- 
										<table width="100%">
										  		<tr>
										  			<td id="ctrlShow">
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

									<!-- 
										<td class="sortableTD" width="5%" align="center">
											<s:hidden name="logFileName" />
											<s:if test="noFileAttachedFlag=='true'">
												<a href="#" title="View attached file" onclick="viewAttachedFile('<s:property value="logFileName"/>','<s:property value="viewCode"/>','<s:property value="appCode"/>')">View</a>
											</s:if><s:else>&nbsp;&nbsp;</s:else>
										</td>
									-->
										<td class="sortableTD">
											<table width="100%">
												<s:iterator value="forwardedApproverUploadDocActivityLogIterator">
												<tr>
													<td class="sortableTD" width="95%" nowrap="nowrap" align="left">
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
							<s:else>
								<td align="center" colspan="6" nowrap="nowrap"><font
									color="red">There is no data to display</font></td>
							</s:else>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">
				<tr>
					<td colspan="4" width="100%"><b>Project Information</b></td>
				</tr>
				<tr>
					<td width="25%"><label class="set"
						name="brd.ticket.no" id="brd.ticket.no"
						ondblclick="callShowDiv(this);"><%=label.get("brd.ticket.no")%></label>
					:</td>
					<td colspan="3" width="75%"><s:property value="brdNumber" /><s:hidden
						name="brdNumber" /></td>
				</tr>
				<tr>
					<td width="25%"><label class="set"
						name="proj.name" id="proj.name" ondblclick="callShowDiv(this);"><%=label.get("proj.name")%></label>
					 : <font color="red" id="ctrlHide">*</font></td>
					<td colspan="3" width="75%"><s:textfield size="90"
						theme="simple" maxlength="500" name="projectName"
						onkeypress="return isCharactersKey(event)" /></td>
				</tr>

				<tr>
					<td width="25%"><label class="set"
						name="project.type" id="project.type" ondblclick="callShowDiv(this);"><%=label.get("project.type")%></label>
					:<font color="red">*</font></td>
					<td width="25%">
						<s:select name="projectType" cssStyle="width:150" theme="simple" headerKey="-1" headerValue="------- Select -------" list="mapProjectType"
						onchange="callProjectType()"></s:select>
					</td>
					<td width="20%">
						<label class="set" name="project.classification" id="project.classification"
						ondblclick="callShowDiv(this);"><%=label.get("project.classification")%></label> : 
						<font color="red">*</font></td>
					<td width="30%">
						<s:select name="projectClassification" cssStyle="width:150" theme="simple" headerKey="-1" headerValue="----- Select -----" list="mapProjectClassification"/>
					</td>	
				</tr>
				
				<tr>
					<td width="25%"><label class="set"
						name="start.date" id="start.date" ondblclick="callShowDiv(this);"><%=label.get("start.date")%></label>
					:</td>
					<td width="25%"><s:textfield name="startDate"
						maxlength="10" size="15" theme="simple" /><s:a
						href="javascript:NewCal('paraFrm_startDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage"
							height="18" align="absmiddle" width="18" id="ctrlHide">
					</s:a></td>
					<td width="20%"><label class="set"
						name="expected.date" id="expected.date"
						ondblclick="callShowDiv(this);"><%=label.get("expected.date")%></label>
					 : <font color="red" id="ctrlHide">*</font> </td>
					<td width="30%"><s:textfield
						name="expectedEndDate" maxlength="10" size="15" theme="simple" /><s:a
						href="javascript:NewCal('paraFrm_expectedEndDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage"
							height="18" align="absmiddle" width="18" id="ctrlHide">
					</s:a></td>
				</tr>

				<tr>
					<td width="25%"><label class="set"
						name="allocated.budget" id="allocated.budget"
						ondblclick="callShowDiv(this);"><%=label.get("allocated.budget")%></label>
					:</td>
					<td width="25%"><s:textfield size="19"
						theme="simple" maxlength="20" name="allocatedBudget"
						onkeypress="return numbersOnly(event)" />
					</td>
					<td width="20%">
						<label class="set" name="priorityLable" id="priorityLable"
						ondblclick="callShowDiv(this);"><%=label.get("priorityLable")%></label> : 
						<font color="red">*</font></td>
					<td width="30%">
						<s:select name="priority" cssStyle="width:150" theme="simple" headerKey="" headerValue="-------- Select -------" list="#{'Critical':'Critical', 'Routine':'Routine'}"/>
					</td>	
				</tr>

				<tr>
					<td width="25%">
						<label class="set" name="est.annual.financial.benefit" id="est.annual.financial.benefit" ondblclick="callShowDiv(this);"><%=label.get("est.annual.financial.benefit")%></label> : 
					</td>
					<td colspan="1" width="25%">
						<s:textfield size="19" theme="simple" maxlength="500" name="projectAnnualFinancialBenefit"
						onkeypress="return numbersOnly(event)" />
					</td>
					<td width="20%">
						<label class="set" name="businessUnitLbl" id="businessUnitLbl" ondblclick="callShowDiv(this);"><%=label.get("businessUnitLbl")%></label> : 
					 <font color="red">*</font></td>
					<td colspan="1" width="30%">					
						<s:select name="businessUnitID" cssStyle="width:150" theme="simple" headerKey="-1" headerValue="-------- Select --------" list="mapBusinessUnitID"
						onchange="callBusinessUnit()"></s:select>						
				</tr>
				<tr>
					<td width="25%">
						<label class="set" name="rankLbl" id="rankLbl" ondblclick="callShowDiv(this);"><%=label.get("rankLbl")%></label> : 
					</td>
					<td colspan="3" width="75%">
						<s:textfield size="19" theme="simple" maxlength="2" name="rank"
						onkeypress="return numbersOnly();" />
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- Text area fileds -->
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">

				<tr>
					<td colspan="4">
					<table width="100%" border="0" cellpadding="1" cellspacing="1">
						<tr>
							<td width="25%"><label class="set"
								name="business.objective" id="business.objective"
								ondblclick="callShowDiv(this);"><%=label.get("business.objective")%></label>
							:</td>
							<s:if test="businessFlag">
								<td colspan="3" width="75%"><s:textarea
									name="businessObjective"  cols="70" rows="6"
									onkeypress="return imposeMaxLength(event, this, 2000);" /><img
									id='ctrlHide' src="../pages/images/zoomin.gif" height="12"
									align="bottom" width="12" theme="simple"
									onclick="javascript:callWindow('paraFrm_businessObjective','business.objective','', '', '2000','2000');">(Max
								2000 Chars)</td>
							</s:if>
							<s:else>
								<td colspan="3" width="75%"><s:textarea
									name="businessObjective" cols="70" rows="6"
									onkeypress="return imposeMaxLength(event, this, 2000);" /><img
									id='ctrlHide' src="../pages/images/zoomin.gif" height="12"
									align="bottom" width="12" theme="simple"
									onclick="javascript:callWindow('paraFrm_businessObjective','business.objective','', '', '2000','2000');"></td>
							</s:else>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="4" width="100%">&nbsp;</td>
				</tr>
				<tr>
				<tr>
					<td colspan="4">
					<table width="100%" border="0" cellpadding="1" cellspacing="1">

						<tr>
							<td width="25%"><label class="set"
								name="project.closure.criteria" id="project.closure.criteria"
								ondblclick="callShowDiv(this);"><%=label.get("project.closure.criteria")%></label>
							:</td>
							<s:if test="closureFlag">
								<td colspan="3" width="75%"><s:textarea
									name="projectClosureCriteria" cols="70" rows="6"
									onkeypress="return imposeMaxLength(event, this, 2000);" /><img
									id='ctrlHide' src="../pages/images/zoomin.gif" height="12"
									align="bottom" width="12" theme="simple"
									onclick="javascript:callWindow('paraFrm_projectClosureCriteria','project.closure.criteria','', '', '2000','2000');">(Max
								2000 Chars)</td>
							</s:if>
							<s:else>
								<td colspan="3" width="75%"><s:textarea
									name="projectClosureCriteria" cols="70" rows="6"
									onkeypress="return imposeMaxLength(event, this, 2000);" /><img
									id='ctrlHide' src="../pages/images/zoomin.gif" height="12"
									align="bottom" width="12" theme="simple"
									onclick="javascript:callWindow('paraFrm_projectClosureCriteria','project.closure.criteria','', '', '2000','2000');"></td>
							</s:else>


						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="4" width="100%">&nbsp;</td>
				</tr>
				<tr>
				<tr>
					<td colspan="4">
					<table width="100%" border="0" cellpadding="1" cellspacing="1">
						<tr>
							<td width="25%"><label class="set"
								name="constraints" id="constraints"
								ondblclick="callShowDiv(this);"><%=label.get("constraints")%></label>
							:</td>
							<s:if test="constraintsFlag">
								<td colspan="3" width="75%"><s:textarea name="constraints"
									cols="70" rows="6"
									onkeypress="return imposeMaxLength(event, this, 2000);" /><img
									id='ctrlHide' src="../pages/images/zoomin.gif" height="12"
									align="bottom" width="12" theme="simple"
									onclick="javascript:callWindow('paraFrm_constraints','constraints','', '', '2000','2000');">(Max
								2000 Chars)</td>
							</s:if>
							<s:else>
								<td colspan="3" width="75%"><s:textarea name="constraints"
									cols="70" rows="6"
									onkeypress="return imposeMaxLength(event, this, 2000);" /><img
									id='ctrlHide' src="../pages/images/zoomin.gif" height="12"
									align="bottom" width="12" theme="simple"
									onclick="javascript:callWindow('paraFrm_constraints','constraints','', '', '2000','2000');"></td>
							</s:else>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td colspan="4" width="100%">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="4">
					<table width="100%" border="0" cellpadding="1" cellspacing="1">
						<tr>
							<td width="25%"><label class="set"
								name="assumptions" id="assumptions"
								ondblclick="callShowDiv(this);"><%=label.get("assumptions")%></label>
							:</td>
							<s:if test="assumptionFlag">
								<td colspan="3" width="75%"><s:textarea name="assuptions"
									cols="70" rows="6"
									onkeypress="return imposeMaxLength(event, this, 2000);" /><img
									id='ctrlHide' src="../pages/images/zoomin.gif" height="12"
									align="bottom" width="12" theme="simple"
									onclick="javascript:callWindow('paraFrm_assuptions','assumptions','', '', '2000','2000');">(Max
								2000 Chars)</td>
							</s:if>
							<s:else>
								<td colspan="3" width="75%"><s:textarea name="assuptions"
									cols="70" rows="6"
									onkeypress="return imposeMaxLength(event, this, 2000);" /><img
									id='ctrlHide' src="../pages/images/zoomin.gif" height="12"
									align="bottom" width="12" theme="simple"
									onclick="javascript:callWindow('paraFrm_assuptions','assumptions','', '', '2000','2000');"></td>
							</s:else>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">
				<tr>
					<td width="28%" nowrap="nowrap">Stake Holders :</td>

					<td width="25%"><s:hidden name="employeeId" /><s:hidden
						name="employeeToken" /><s:textfield name="employeeName" size="45"
						readonly="true" /></td>

					<td colspan="2" width="47%">&nbsp;<img
						src="../pages/common/css/default/images/search2.gif"
						class="iconImage" width="16" height="15" id="ctrlHide"
						onclick="javascript:getKeepInformedEmp();" />&nbsp;&nbsp;<s:submit
						name="" id="ctrlHide" value=" Add" cssClass=" add"
						action="BusinessRequirementDocument_addStakeHolderEmpList"
						onclick="return callAddApprover();" /></td>
				</tr>

				<s:if test="displayFlag">
					<tr>
						<td colspan="4">
						<table width="100%" border="0" cellpadding="1" cellspacing="1"
							align="right">
							<%
									int counter11 = 0;
									int counter2 = 0;
									System.out.println("$$$$$$$$$$ 1");
							%>
							<s:iterator value="stakeHoldersList" status="stat">
								<tr>
									<td width="27%">&nbsp;</td>
									<td width="25%" nowrap="nowrap"><%=++counter11%>
									<s:hidden name="serialNoStake" /> <s:hidden
										name="stakeholderEmpNameItt" /> <s:property
										value="stakeholderEmpNameItt" /> <s:hidden name="empid" /></td>
									<td width="48%" colspan="2"><a href="#"
										onclick="callForRemove(<%=counter11%>);"> Remove</a></td>
								</tr>
								<%
								counter2 = counter11;
								%>
							</s:iterator>
							<%
							counter2 = 0;
							%>
						</table>
						</td>
					</tr>
				</s:if>
				<s:else>
					<tr>
						<td colspan="4">
						<table width="100%" border="0" cellpadding="1" cellspacing="1"
							align="right">

							<%
									int counter12 = 0;
									int counter22 = 0;
									System.out.println("$$$$$$ 2");
							%>

							<s:iterator value="stakeHoldersList" status="stat">

								<tr>
									<td width="25%">&nbsp;</td>
									<td width="15%" nowrap="nowrap"><%=++counter12%>)
									<s:hidden name="serialNoStake" /> <s:hidden
										name="stakeholderEmpNameItt" /> <s:property
										value="stakeholderEmpNameItt" /> <s:hidden name="empid" /></td>
									<td width="60%" colspan="2">&nbsp;</td>
								</tr>
								<%
								counter22 = counter12;
								%>
							</s:iterator>
							<%
							counter22 = 0;
							%>
						</table>
						</td>
					</tr>
				</s:else>
			</table>
			</td>
		</tr>
		
<!-- BEGINS -- Multiple Upload Document -->
		<tr>
			<td colspan="4">
			  <table width="100%" border="0" cellpadding="1" cellspacing="1" class="formbg">
				<tr>
					<td width="25%" colspan="1"><b>Upload Document</b></td>
					<td width="75%" colspan="3" align="right">
						 <input type="button" class="token" value="Add More Attachment" onclick="addRowForuploadDocuments('uploadDocumentTableID');">
					</td>
				</tr>

				<tr>
					<td width="25%" colspan="1"><label id="document.type"
						name="document.type" ondblclick="callShowDiv(this);"><%=label.get("document.type")%></label> : 
					</td>
					<td width="75%" colspan="3">
						<s:select headerKey="-1" headerValue="-----------------------Select--------------------------"
							name="docType" list="mapDoc" />
					</td>
				</tr>

				<tr>
					<td width="25%" colspan="1">
						<label id="upload.document" name="upload.document" ondblclick="callShowDiv(this);"><%=label.get("upload.document")%></label> :
					</td>
					<td width="75%" colspan="3">
						 <table id="uploadDocumentTableID" width="100%">
						<%
							int uploadDocCount = 0;
						%>
						<s:iterator value="uploadDocumentIterator">
							<tr>
								<td class="sortableTD" width="30%">
									<input type="text" size="50" name='uploadFileNameItr' readonly="true"
										value="<s:property value='uploadFileNameItr'/>" id="paraFrm_uploadFileNameItr<%=uploadDocCount%>" cssStyle=""/> 
								</td>
								 
								<td class="sortableTD" width="10%">
									 <input type="button" name="uploadDocumentButton" value="Select File" class="token"
										onclick="uploadFile('paraFrm_uploadFileNameItr<%=uploadDocCount%>');" />
								</td>
								
								<td class="sortableTD" width="10%" id="ctrlShow">
									<input type="button" name="show" value="Show" class="token"
										onclick="showRecord('paraFrm_uploadFileNameItr<%=uploadDocCount%>');" />
								</td>
								
								<td class="sortableTD" width="50%" id="ctrlHide">
									<img src="../pages/common/css/icons/delete.gif" 
										onclick="deleteCurrentRow(this);" />
								</td>
							</tr>
							<%
							uploadDocCount++;
							%>
						</s:iterator>
						
					 </table>
					</td>
				 </tr>
			  </table>
			</td>
		</tr>
<!-- ENDS -- Multiple Upload Document -->
		
		

		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">
				<tr>
					<td width="25%"><label id="applicantCommentsLabel"
						name="applicantCommentsLabel" ondblclick="callShowDiv(this);"><%=label.get("applicantCommentsLabel")%></label>
					 : <font color="red" id="ctrlHide">*</font></td>

					<s:if test="applicantCommnetsFlag">	
					<td width="75%" colspan="2"><s:textarea name="applicantsComments"
						cols="70" rows="6"
						onkeypress="return imposeMaxLength(event, this, 2000);" /> <img
						id='ctrlHide' src="../pages/images/zoomin.gif" height="12"
						align="bottom" width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_applicantsComments','applicantCommentsLabel','', '', '2000','2000');">(Max
					2000 Chars)</td>
					</s:if>
					<s:else>
						<td width="75%" colspan="2"><s:textarea name="applicantsComments"
						cols="70" rows="6"
						onkeypress="return imposeMaxLength(event, this, 2000);" /> <img
						id='ctrlHide' src="../pages/images/zoomin.gif" height="12"
						align="bottom" width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_applicantsComments','applicantCommentsLabel','', '', '2000','2000');"></td>
					</s:else>	
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">
				<tr>
					<td width="100%" colspan="4"><b>Forward to </b></td>
				</tr>

				<tr>
					<td width="25%" colspan="1"><label id="role.name"
						name="role.name" ondblclick="callShowDiv(this);"><%=label.get("role.name")%></label>
					 : <font color="red" id="ctrlHide">*</font></td>
					<td width="75%" colspan="3"><s:select headerKey="-1"
						headerValue="------------Select--------------" name="selectRole"
						cssStyle="width:160" list="mapRole" /></td>
				</tr>

				<tr>
					<td width="25%"><label id="employee.name"
						name="employee.name" ondblclick="callShowDiv(this);"><%=label.get("employee.name")%></label>
					 : <font color="red" id="ctrlHide">* </font></td>
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
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">
				<tr>
					<td class="formtext" width="25%"><label name="completedBy"
						id="completedBy" ondblclick="callShowDiv(this);"> <%=label.get("completedBy")%>
					</label> :</td>
					<td height="22" width="25%"><s:hidden name="completedByID" />
					<s:hidden name="completedBy" /> <s:property value="completedBy" />
					</td>
					<td class="formtext" width="25%"><label name="completedDate"
						id="completedDate" ondblclick="callShowDiv(this);"> <%=label.get("completedDate")%>
					</label> :</td>
					<td height="22" width="25%"><s:hidden name="completedDate" />
					<s:property value="completedDate" /></td>
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
 		var table = document.getElementById('uploadDocumentTableID'); 
		var rowCount = table.rows.length; 
		var iteration = rowCount;
		if (iteration==0) {
		 	addRowForuploadDocuments('uploadDocumentTableID');
		 }
	} catch(e) {
		alert("Exception while loading page >>"+e);
	}
}	
 
// Add row Witness Details Begins
function addRowForuploadDocuments(tableID) {
	try {
		  var table = document.getElementById(tableID); 
		  var rowCount = table.rows.length; 
		  var iteration = rowCount;
		  var row = table.insertRow(rowCount);
		 		
		  var cell1= row.insertCell(0);
		  var column1 = document.createElement('input');
		  cell1.className='sortableTD';
		  column1.type = 'text';
  		  column1.name = 'uploadFileNameItr';
		  column1.id = 'paraFrm_uploadFileNameItr'+iteration;
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
		  						var uploadField= 'paraFrm_uploadFileNameItr'+iteration;
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
		  					return showRecord('paraFrm_uploadFileNameItr'+iteration);
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
// Add row Witness Details Ends

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
//Delete Row - ENDS


//View uploaded documents - BEGINS
function showRecord(fileName) {
		var fileName =document.getElementById(fileName).value;
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


function draftFun() {
	var projectName =  document.getElementById('paraFrm_projectName').value; 
	var startDate =  document.getElementById('paraFrm_startDate').value;
	var expectedEndDate =  document.getElementById('paraFrm_expectedEndDate').value;
	var fwdempCode =  document.getElementById('paraFrm_fwdempCode').value;
	var allocatedBudget =  document.getElementById('paraFrm_allocatedBudget').value;
	var selectRole = document.getElementById('paraFrm_selectRole').value;
	//var uploadFileName = document.getElementById('paraFrm_uploadFileName').value;
	var employeeName = document.getElementById('paraFrm_employeeName').value;
	//var docType =  document.getElementById('paraFrm_docType').value;
	//var uploadFileName =  document.getElementById('paraFrm_uploadFileName').value;
	var priorityVar = document.getElementById('paraFrm_priority').value;
	var classificationVar = document.getElementById('paraFrm_projectClassification').value
	var projectTypeVar= document.getElementById('paraFrm_projectType').value;
	var buss_unit= document.getElementById('paraFrm_businessUnitID').value;
	
 	if(projectName==""){
		 alert("Please Enter Project Name");
		 document.getElementById('paraFrm_projectName').focus();
		 return false;
	}      	
  	if(!(projectName=="")) {
  		var count =0;
		var iChars =" ";
		  for (var i = 0; i < projectName.length; i++) {		
			  	if (iChars.indexOf(projectName.charAt(i))!= -1) {
			  	 			count=count+1; 
  				}
  		  }
  		  if(count==projectName.length) {
  				alert ("Blank Spaces Not Allowed in Project Name");
  				document.getElementById('paraFrm_projectName').focus();
  				return false;
  		  }
  	}  	
  	if(projectName==""){
	  alert("Please Enter Project Name");
	  return false;
 	}	
 	if(projectTypeVar=="-1"){
	 	alert("Please Select Project Type");
	    return false;
 	}
 	if(classificationVar=="-1"){
	 	alert("Please Select Classification");
	  	return false;
 	}   
 	if(expectedEndDate=="") {
	   alert("Please enter/select Expected End Date");
	   document.getElementById('paraFrm_expectedEndDate').focus();
	   return false;
	} else {
		if(!validateDate('paraFrm_expectedEndDate',"expected.date")){
			document.getElementById('paraFrm_expectedEndDate').focus();
			return false;   	
	   	}	
	}	
 	if(priorityVar==""){
	 	alert("Please Select Priority");
	 	 return false;
 	}  	
 	if(buss_unit==""){
	 	alert("Please Select Business Unit");
	 	 return false;
 	}  		  
  	/*for date validation*/	
	if(!validateDate('paraFrm_startDate',"start.date")){
		document.getElementById('paraFrm_startDate').focus();
		return false;   	
	}				
    			
	if(!startDate == "" && !expectedEndDate == "") {
		if(!dateDifferenceEqual(startDate,expectedEndDate,'paraFrm_startDate','start.date','expected.date')){
			document.getElementById('paraFrm_expectedEndDate').focus();
			return false;   	
	   	}			
	}
    /*end of date validation*/
	if(!allocatedBudget==""){
		if(isNaN(document.getElementById('paraFrm_allocatedBudget').value)){
			alert('Please enter project allocated budget as a number');
			document.getElementById('paraFrm_allocatedBudget').focus();
			return false;
     	}
  	}
 	if(employeeName!="") {
 		alert("Please add Stake Holder");
 		return false;
 	}
   /*if(uploadFileName!=""){
 			alert("Please attach the file")
 			return false;
 	  }*/

	var authorComments = trim(document.getElementById('paraFrm_applicantsComments').value);
	if (authorComments == "") {
		alert("Please enter "+document.getElementById('applicantCommentsLabel').innerHTML.toLowerCase());
		document.getElementById('paraFrm_applicantsComments').focus();
		return false;
	}
 	if(selectRole=="-1") {
 		alert("Please select Role of employee");
		return false; 
 	}
 	if(fwdempCode=="") {
 		alert("Please Select Employee to forward application");
 		return false;
 	} 
    //document.getElementById('paraFrm_status').value='D';
    document.getElementById('paraFrm').target = "_self";
    document.getElementById('paraFrm').action = 'BusinessRequirementDocument_draft.action';
    document.getElementById('paraFrm').submit();
 }

function forwardFun() {
	var projectName =  document.getElementById('paraFrm_projectName').value; 
	var startDate = document.getElementById('paraFrm_startDate').value;
	var expectedEndDate =  document.getElementById('paraFrm_expectedEndDate').value;
	var allocatedBudget =  document.getElementById('paraFrm_allocatedBudget').value;
	var selectRole = document.getElementById('paraFrm_selectRole').value;
	var fwdempCode =  document.getElementById('paraFrm_fwdempCode').value;
	//var uploadFileName = document.getElementById('paraFrm_uploadFileName').value;
	var docType =  document.getElementById('paraFrm_docType').value;
	var employeeName = document.getElementById('paraFrm_employeeName').value;
	var priorityVar = document.getElementById('paraFrm_priority').value;
	var classificationVar = document.getElementById('paraFrm_projectClassification').value
	var projectTypeVar= document.getElementById('paraFrm_projectType').value;
	var buss_unit= document.getElementById('paraFrm_businessUnitID').value;
	
 	if(projectName==""){
	  alert("Please Enter Project Name");
	  return false;
 	}	
 	if(projectTypeVar=="-1"){
	 	alert("Please Select Project Type");
	    return false;
 	}
 	if(classificationVar=="-1"){
	 	alert("Please Select Classification");
	  	return false;
 	}   
 	if(expectedEndDate=="") {
	   alert("Please enter/select Expected End Date");
	   document.getElementById('paraFrm_expectedEndDate').focus();
	   return false;
	} else {
		if(!validateDate('paraFrm_expectedEndDate',"expected.date")){
			document.getElementById('paraFrm_expectedEndDate').focus();
			return false;   	
	   	}	
	}	
 	if(priorityVar==""){
	 	alert("Please Select Priority");
	 	 return false;
 	}  	
 	if(buss_unit==""){
	 	alert("Please Select Business Unit");
	 	 return false;
 	} 
 	
 	if(!(projectName=="")) {
 		var count =0;
		var iChars =" ";
		for (var i = 0; i < projectName.length; i++) {		
			if (iChars.indexOf(projectName.charAt(i))!= -1) {
	 			count=count+1; 
  			}
  		}  	
  		if(count==projectName.length){
  			alert ("Blank Spaces Not Allowed in Project Name");
  			return false;
  		}
 	}	  	
   /*for date validation*/	
	if(!validateDate('paraFrm_startDate',"start.date")){
		document.getElementById('paraFrm_startDate').focus();
		return false;   	
	 }				
    			
	if(!startDate == "" && !expectedEndDate == ""){
		if(!dateDifferenceEqual(startDate,expectedEndDate,'paraFrm_startDate','start.date','expected.date')){
			document.getElementById('paraFrm_expectedEndDate').focus();
			return false;   	
	 	}			
	}
  	if(!allocatedBudget==""){
		if(isNaN(document.getElementById('paraFrm_allocatedBudget').value)){
			alert('Please enter project allocated budget as a number');
			document.getElementById('paraFrm_allocatedBudget').focus();
			return false;
		}
  	}
 	if(employeeName!="") {
 		alert("Please add Stake Holder");
 		return false;
 	}
/*
 	if(uploadFileName!="") {
 		alert("Please attach the file")
 		return false;
 	}
*/
	var applicantComments = trim(document.getElementById('paraFrm_applicantsComments').value);
	if (applicantComments == "") {
		alert("Please enter "+document.getElementById('applicantCommentsLabel').innerHTML.toLowerCase());
		document.getElementById('paraFrm_applicantsComments').focus();
		return false;
	}	
	if(selectRole=="-1") {
		alert("Please select Role of employee");
		return false;
	}
	if(fwdempCode=="") {
 		alert("Please Select Employee to forward application");
 		return false;
	 }
	var con = confirm("Do you really want to forward this application?");
	if (con) {
		document.getElementById('paraFrm_status').value='F';
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'BusinessRequirementDocument_forward.action';
		document.getElementById('paraFrm').submit(); 
	} else {	
	 	return false;
	}
 }

/*	
	setAddedFile();	
	function setAddedFile() {
		var attachFile = document.getElementById('paraFrm_attachFile').value;	
		document.getElementById('attachedFile').innerHTML =attachFile;
	}
*/	
	
///Added new function for uplaod file.
function uploadFile_new(fieldName) {
	var path = document.getElementById("paraFrm_dataPath").value;
	window.open('<%=request.getContextPath()%>/pages/common/uploadFile.jsp?path=' + path + '&field=' + 
	fieldName, '', 'width=400, height=200, scrollbars=no, resizable=no, top=50, left=100');
 }	
	
	
function uploadFile(fieldName) {
	var path = document.getElementById("paraFrm_dataPath").value;
	window.open('<%=request.getContextPath()%>/pages/recruitment/uploadResume.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
 }

function openAttachedFile() {
	document.getElementById('paraFrm').action = 'BusinessRequirementDocument_openAttachedFile.action';  
	document.getElementById('paraFrm').submit();
 }

function resetFun()  {
	document.getElementById('paraFrm').target = "_self";     	
  	document.getElementById('paraFrm').action = 'BusinessRequirementDocument_reset.action';
    document.getElementById('paraFrm').submit();
 }

function backtolistFun()  {
	document.getElementById('paraFrm').target = "_self";
    document.getElementById('paraFrm').action = 'BusinessRequirementDocument_back.action';
	document.getElementById('paraFrm').submit();
 }

function deleteFun() {
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'BusinessRequirementDocument_deleteApplication.action';
		document.getElementById('paraFrm').submit();
	}
 }


function callAddApprover(){
   var empId = document.getElementById('paraFrm_employeeId').value;
   if(empId=="") {
     	alert("Please Select Stake Holders");
     	return false;
  	}
    return true;
 }
   
function viewAttachedFile(logFileName,viewCode,appCode) {
    try {
       if(logFileName==""){
         alert("No File Attached !");
         return false;
       }
       document.getElementById('paraFrm').target = '_blank';
	   document.getElementById('paraFrm').action = "BusinessRequirementDocument_viewAttachedFile.action?viewCode11="+viewCode+"&appCode11="+appCode+"&logFileName="+logFileName;
	   document.getElementById('paraFrm').submit();
	} catch(e) {
		alert(e);
	}
 }

function forwardedEmp() {
   	callsF9(500,325,'BusinessRequirementDocument_f9forwardToEmp.action');
 }
   
function getKeepInformedEmp() {
	try {
		callsF9(500,325,'BusinessRequirementDocument_f9stakeHolderEmployee.action');
	} catch(e) {
		alert(e);
	} 
 }
	
function callForRemove(id) {
    var conf=confirm("Are you sure !\n You want to Remove this record ?");
  	if(conf){
		document.getElementById('paraFrm_checkRemove').value=id;
		document.getElementById('paraFrm').target="_self";
	 	document.getElementById("paraFrm").action="BusinessRequirementDocument_removeStakeHolders.action";
		document.getElementById("paraFrm").submit();
	}	
    return true;
 }
	
function imposeMaxLength(Event, Object, MaxLen) {
  return (Object.value.length <= MaxLen)||(Event.keyCode == 8 ||Event.keyCode==46||(Event.keyCode>=35&&Event.keyCode<=40))
 }	
	
function isNumWithCharKey(evt) {
	/* alert("Only Numbers and Special Characters are allowed here!");*/
     var charCode = (evt.which) ? evt.which : event.keyCode           
     if((charCode!=36 ) &&(charCode!=46) ){
           if (charCode > 31 && (charCode < 48 || charCode > 57) || (charCode==44) && (charCode==46))
                return false;
           }
           return true;
 }

function saveFun() {
	document.getElementById('paraFrm').target="_self";
	document.getElementById("paraFrm").action="BusinessRequirementDocument_saveApplicantRelatedData.action";
	document.getElementById("paraFrm").submit();
 }

function printFun() {	
	window.print();
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

function callProjectType(){
	document.getElementById("paraFrm").target = "_self";
	document.getElementById("paraFrm").action = "BusinessRequirementDocument_projectTypeOwner.action?";
	document.getElementById("paraFrm").submit();
}
function callBusinessUnit(){
	document.getElementById("paraFrm").target = "_self";
	document.getElementById("paraFrm").action = "BusinessRequirementDocument_setBusinessUnitEmployee.action?";
	document.getElementById("paraFrm").submit();
}
</script>
