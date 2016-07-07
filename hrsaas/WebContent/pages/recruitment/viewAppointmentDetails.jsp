<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="OfferDetails" id="paraFrm" theme="simple">
	<s:hidden name="appointmentCode"/>
	<s:hidden name="positionCode"/> 
	<s:hidden name="divisionCode" />
	<s:hidden name="branchCode" />
	<s:hidden name="deptCode" />
	<s:hidden name="addcandFlag"/>
	<s:hidden name="appointmentLetterRegCode"/>
	<s:hidden name="hiddenAppointmentCode" />
	<s:hidden name="hiddenRequisitionCode"/>
	<table class="formbg" width="100%"><!-- main table -->
	      <tr>
        	<td width="100%" colspan="3">
        		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
				     <tr>
					     <td width="4%" valign="bottom" class="txt"><strong class="formhead"><img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" /></strong></td>
					     <td width="93%" class="txt"><strong class="text_head">Appointment Details</strong></td>
					     <td width="3%" valign="top" class="txt"><div align="right"><img src="../pages/common/css/default/images/help.gif" width="16" height="16" /></div></td>
				     </tr>
				 </table>
			</td>
		</tr>
	     
	     <tr>
	     	<td width="100%" colspan="3">
	     		<!--<s:if test="addcandFlag"><s:submit value="   Cancel" cssClass="del" action="CreateOffer_input"/></s:if>
	     		<s:else>
	     			<s:submit value="   Edit" cssClass="edit" action="OfferDetails_backFromView"/>
	     			<s:submit value="   Cancel" cssClass="del" action="AppointmentDetails_reset"/>
	     		</s:else>
	     		-->
	     		<s:if test="doubleClickViewModeFlag">
	     			<s:submit value=" Cancel" cssClass="cancel" onclick="callCancelAppoint();"/>
	     		</s:if>
	     		<s:else>
	     			<s:submit value=" Edit" cssClass="edit" action="AppointmentDetails_backFromView"/>
	     			<!-- 
	     				<s:submit value="Preview Appointment" cssClass="token" onclick="return validatePreviewForTemplate()" action="AppointmentDetails_previewappointment"/>
	     			-->
			     	<s:submit  value="Preview Appointment in Doc" cssClass="token" onclick="return validatePreviewForTemplate()" action="AppointmentDetails_previewappointment"/>
			     	<input type="button" value="Preview Appointment in PDF" class="token"	onclick="return validatePreviewForTemplatePDF()" /> 
			     	<s:submit id="mydisplay1" value="Email Appointment" cssClass="token" onclick="return validatePreviewForEmailTemplate()" action="AppointmentDetails_emailappointment"/>
		     		<s:submit value=" Cancel" cssClass="cancel" action="CreateAppointment_dupinput"/>
	     		</s:else>
	     		</td>
	     </tr>
	     
	     <tr>
	     	<td width="100%" colspan="3">
	     		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg"><!-- table 1 -->
	     			<tr>
						<td width="49%" colspan="2"><strong class="formhead">Requisition Details</strong></td>
						<td width="1%">&nbsp;</td>
						<td width="49%" colspan="2"><strong class="formhead">Candidate Details</strong></td>
				    </tr>
				    
				    <tr>
				    	<td width="48%" class="txt" colspan="2">
				    		<table width="100%" border="0" cellpadding="0" cellspacing="0"><!-- table 2 -->
				    			<tr>
				    				<td width="20%" height="25" valign="top"><label  class = "set" name="reqs.code" id="requisition.code" 
							            	ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label> : </td>
							        <td width="20%" height="25" valign="top"><s:property value="requisitionName"/>
							        	<s:hidden name="requisitionName"/><s:hidden name="requisitionCode"/></td>
				    			</tr>
				    			
				    			<tr>
				    				<td width="20%" height="25" valign="top"><label  class = "set" name="position" id="position" 
							            	ondblclick="callShowDiv(this);"><%=label.get("position")%></label> :</td>
							        <td width="20%" height="25" valign="top"><s:property value="position"/>
							        	<s:hidden name="position"/></td>
				    			</tr>
				    			
				    			<tr>
				    				<td width="20%" height="25" valign="top"><label  class = "set" name="division" id="division" 
							            	ondblclick="callShowDiv(this);"><%=label.get("division")%></label> : </td>
							        <td width="20%" height="25" valign="top"><s:property value="division"/>
							        	<s:hidden name="division"/></td>
				    			</tr>
				    			
				    			<tr>
				    				<td width="20%" height="25" valign="top"><label  class = "set" name="branch" id="branch" 
							            	ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> : </td>
							        <td width="20%" height="25" valign="top"><s:property value="branch"/>
							        	<s:hidden name="branch"/></td>
				    			</tr>
				    			
				    			<tr>
				    				<td width="20%" height="25" valign="top"><label  class = "set" name="department" id="department" 
							            	ondblclick="callShowDiv(this);"><%=label.get("department")%></label> : </td>
							        <td width="20%" height="25" valign="top"><s:property value="department"/>
							        	<s:hidden name="department"/></td>
				    			</tr>
				    		</table><!-- table 2 -->
				    	</td>
				    	<td width="1%" height="25">&nbsp;</td>
						<td width="48%" class="txt" colspan="2" valign="top">
				    		<table width="100%" border="0" cellpadding="0" cellspacing="0"><!-- table 3 -->
				    			<tr>
				    				<td width="20%" height="25" valign="top"><label  class = "set" name="cand.name" id="candidate.name" 
							            	ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label> : </td>
							        <td width="25%" height="25" valign="top"><s:property value="candidateName"/>
							        	<s:hidden name="candidateCode"/><s:hidden name="candidateName"/></td>
				    			</tr>
				    			
				    			<tr>
				    				<td width="20%" height="25" valign="top"><label  class = "set" name="expected.date" id="expected.date" 
								ondblclick="callShowDiv(this);"><%=label.get("expected.date")%></label>  :</td>
							        <td width="25%" height="25" valign="top"><s:property value="joiningDate"/>
							        	<s:hidden name="joiningDate"/></td>
				    			</tr>
				    			
				    			<tr>
				    				<td width="20%" height="25" valign="top"><label  class = "set" name="appointment.date" id="appointment.date" 
							            	ondblclick="callShowDiv(this);"><%=label.get("appointment.date")%></label> :</td>
							        <td width="25%" height="25" valign="top"><s:property value="appointmentDate"/>
							        	<s:hidden name="appointmentDate"/></td>
				    			</tr>
				    			
				    			<tr>
				    				<td width="20%" height="25" valign="top"><label  class = "set" name="current.ctc" id="current.ctc" 
							            	ondblclick="callShowDiv(this);"><%=label.get("current.ctc")%></label> : </td>
							        <td width="25%" height="25" valign="top"><s:property value="currentCtc"/>
							        	<s:hidden name="currentCtc"/></td>
				    			</tr>
				    			
				    			<tr>
				    				<td width="20%" height="25" valign="top"><label  class = "set" name="negotiated.ctc" id="negotiated.ctc" 
							            	ondblclick="callShowDiv(this);"><%=label.get("negotiated.ctc")%></label> : </td>
							        <td width="25%" height="25" valign="top"><s:property value="negotiatedCtc"/>
							        	<s:hidden name="negotiatedCtc"/></td>
				    			</tr>
				    			
				    			
				    			<tr>
							    	<td width="20%" height="25" valign="top">
							    		<label  class = "set" name="candidateEmailIDLabel" id="candidateEmailIDLabel" 
										    ondblclick="callShowDiv(this);"><%=label.get("candidateEmailIDLabel")%></label> : 
									</td>
									<td width="25%" height="25" valign="top">
										<s:property value="candidateEmailID"/>
									   	<s:hidden name="candidateEmailID"/>
									</td>
							    </tr>
				    		</table><!-- table 3 -->
				    	</td>				    	
				    </tr>
	     		</table><!-- table 1 -->
	     	</td>
	     </tr>
	     
	      <tr>
						<td colspan="3" width="100%">
							<table width="100%" border="0" cellpadding="1" cellspacing="1"
								 id="evalDtls" class="formbg"><!-- table 6 -->
								<tr>
									<td colspan="3"><strong class="formhead">Recruiter Selection</strong></td>
								</tr>
								<s:hidden name="hBranch" /><s:hidden name="hDesg" />
								<s:if test="addcandFlag">
								<tr>
									<td width="25%" nowrap="nowrap">
										<label  class = "set" name="rec.name" id="rec.name" 
			            				ondblclick="callShowDiv(this);"><%=label.get("rec.name")%></label> :
									</td>
									<s:hidden name="recruiterId"/>
									<s:hidden name="recruiterToken"/>
									<td width="22%">
										<s:property value="recruiterName"/>
										<s:hidden name="recruiterName"/>
									</td>
									<td colspan="3" align="left"></td>
								</tr>
								</s:if>
								<s:else>
									<tr>
										<td width="23%">
											<label  class = "set" name="assignRecruiter" id="assignRecruiter" 
			            					ondblclick="callShowDiv(this);"><%=label.get("assignRecruiter")%></label> :
										</td>
										<s:hidden name="recruiterId"/>
										<s:hidden name="recruiterToken"/>
										<td width="25%">
											<s:property value="recruiterName"/>
											<s:hidden name="recruiterName"/>
										</td>
										<td colspan="3" align="left">
											
										</td>
									</tr>
								</s:else>
								<!-- KEEP INFORMED TO BLOCK STARTS -->

				<tr>
					<td><strong>Keep Informed To : </strong></td>
					<td><s:hidden name="employeeId" /> <s:hidden
						name="employeeToken" /> </td>
					
					<td colspan="2" align="left">&nbsp;</td>
				</tr>

				<tr valign="top">
					<td colspan="3" rowspan="5">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">

						<%
							int counter11 = 0;
							int counter2 = 0;
						%>

						<s:iterator value="keepInformedList" status="stat">
							<tr>
								<td width="80%"><%=++counter11%><s:hidden name="serialNo" /><s:hidden
									name="keepInformedEmpName" /><s:property
									value="keepInformedEmpName" /><s:hidden
									name="keepInformedEmpId" /></td>
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
					<td></td>
				</tr>
				<!-- KEEP INFORMED TO BLOCK ENDS -->
							</table>
						</td>
					</tr>			
	     
	     <tr>
	     	<td width="100%" colspan="4">
	     		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg"><!-- table 4 -->
	     			<tr>
	     				<td width="25%" height="25" valign="top">
	     					<label  class = "set" name="job.desc" id="job.descc" 
								ondblclick="callShowDiv(this);"><%=label.get("job.desc")%></label> : </td>
						<td colspan="3"><s:property value="jobDescription"/>
							<s:hidden name="jobDescription"/></td>
	     			</tr>
	     			
	     			<tr>
	     				<td width="25%" height="25" valign="top">
	     					<label  class = "set" name="roles.res" id="roles.res" 
								ondblclick="callShowDiv(this);"><%=label.get("roles.res")%></label> :</td>
						<td colspan="3"><s:property value="rolesResponsibility"/>
							<s:hidden name="rolesResponsibility"/></td>
	     			</tr>
	     			
	     			<tr>
	     			
	     				<td width="21%" height="25" valign="top"> 
	     					<label  class = "set" name="offered.ctc" id="offered.ctc" 
								ondblclick="callShowDiv(this);"><%=label.get("offered.ctc")%></label> :</td>
						<td width="25%" height="25" valign="top"><s:property value="offeredCtc"/>
							<s:hidden name="offeredCtc"/></td>
							
						
							
	     			</tr>
	     			
	     			<tr>
	     				<td width="25%" height="25" valign="top">
	     					<label  class = "set" name="hiring.mgr" id="hiring.mgr" 
								ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%></label> :</td>
						<td width="27%" height="25" valign="top"><s:property value="hiringMgr"/>
							<s:hidden name="hiringMgrCode"/><s:hidden name="hiringMgr"/></td>
						<td width="21%" height="25" valign="top"> 
						<label  class = "set" name="joining.date" id="joining.date" 
							            	ondblclick="callShowDiv(this);"><%=label.get("joining.date")%></label> :</td>
						<td width="25%" height="25" valign="top"><s:property value="expJoiningDate"/>
							<s:hidden name="expJoiningDate"/></td>
	     			</tr>
	     			
	     			<tr>
	     				<td width="25%" height="25" valign="top">
	     					<label  class = "set" name="reporting.to" id="reporting.to" 
								ondblclick="callShowDiv(this);"><%=label.get("reporting.to")%></label> :</td>
						<td width="27%" height="25" valign="top"><s:property value="reportingTo"/>
							<s:hidden name="reportingToCode"/><s:hidden name="reportingTo"/></td>
						<td width="21%" nowrap="nowrap" valign="top" ><label  class = "set" name="reporting.to.admin" id="reporting.to.admin" 
											ondblclick="callShowDiv(this);"><%=label.get("reporting.to.admin")%></label> :</td>
						<td width="18%" valign="top"><s:property value="reportingToAdmin"/></td>
										<s:hidden name="reportingToAdmin"/><s:hidden name="reportingToAdminCode"/>
	     			</tr>

	     			<tr>
	     				<td width="25%" height="25" valign="top">
	     					<label  class = "set" name="grade" id="grade" 
								ondblclick="callShowDiv(this);"><%=label.get("grade")%></label> :</td>
						<td width="27%" height="25" valign="top"><s:property value="grade"/>
							<s:hidden name="gradeCode"/><s:hidden name="grade"/></td>
						<td width="21%" height="25" valign="top"> 
	     					<label  class = "set" name="joining.formality" id="joining.formality" 
								ondblclick="callShowDiv(this);"><%=label.get("joining.formality")%></label> :</td>
						<td width="25%" height="25" valign="top"><s:property value="testRequirements"/>
							<s:hidden name="testRequirements"/></td>
	     			</tr>
	     			
	     			<tr>
	     				<td width="25%" height="25" valign="top"><s:hidden name="probation" value="%{probation}"/>
	     					<label  class = "set" name="probation.period" id="probation.period" 
								ondblclick="callShowDiv(this);"><%=label.get("probation.period")%></label> :</td>
						<td width="27%" height="25" valign="top"><s:property value="probation"/>
							<span id="months"><s:property value="months"/>&nbsp;Months</span>
							<s:hidden name="Months"/></td>
						<td width="21%" height="25" valign="top"> 
	     					<label  class = "set" name="signing.authority" id="signing.authority" 
								ondblclick="callShowDiv(this);"><%=label.get("signing.authority")%></label> :</td>
						<td width="25%" height="25" valign="top"><s:property value="signingAuthority"/>
							<s:hidden name="authorityCode"/><s:hidden name="signingAuthority"/></td>
	     			</tr>
	     			
	     			<tr>
	     				<td width="25%" height="25" valign="top">
	     					<label  class = "set" name="bg.check" id="bg.check" 
								ondblclick="callShowDiv(this);"><%=label.get("bg.check")%></label> :</td>
						<td width="27%" height="25" valign="top"><s:property value="backgroundCheck"/>
							<s:hidden name="backgroundCheck"/></td>
						<td width="21%" height="25" valign="top"> 
	     					<label  class = "set" name="designation" id="designation" 
								ondblclick="callShowDiv(this);"><%=label.get("designation")%></label> :</td>
						<td width="25%" height="25" valign="top"><s:property value="designation"/>
							<s:hidden name="designation"/></td>
	     			</tr>
	     			
	     			<tr>
	     				<td width="25%" height="25" valign="top">
	     					<label  class = "set" name="appointment.status" id="appointment.status" 
								ondblclick="callShowDiv(this);"><%=label.get("appointment.status")%></label> : </td>
						<td width="27%" height="25" valign="top"><s:property value="appointmentStatus"/>
							<s:hidden name="appointmentStatus"/></td>
						<td width="21%" height="25" valign="top"> 
	     					<label  class = "set" name="employee.type" id="employee.type" 
								ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label> : </td>
						<td width="25%" height="25" valign="top"><s:property value="empType"/>
							<s:hidden name="empTypeCode"/><s:hidden name="empType"/></td>
						<td width="25%"></td>
						<td width="27%"></td>
	     			</tr>
	     			
	     			<tr>
	     				<td width="25%" height="25" valign="top">
	     					<label  class = "set" name="remarks" id="remarks" 
								ondblclick="callShowDiv(this);"><%=label.get("remarks")%></label> :</td>
						<td width="27%" height="25" valign="top"><s:property value="remarks"/>
							<s:hidden name="remarks"/></td>
						<td width="21%" height="25" valign="top"> 
	     					<label  class = "set" name="cand.constraints" id="cand.constraints" 
								ondblclick="callShowDiv(this);"><%=label.get("cand.constraints")%></label> :</td>
						<td width="25%" height="25" valign="top"><s:property value="candConstraints"/>
							<s:hidden name="candConstraints"/></td>
	     			</tr>
	     		
	     		<!-- 
	     			<tr>
	     				<td width="21%" height="25" valign="top"> 
	     					<label  class = "set" name="appointment.emailtemplate" id="appointment.emailtemplate" 
								ondblclick="callShowDiv(this);"><%=label.get("appointment.emailtemplate")%></label> : </td>
	     				<td width="25%" height="25" valign="top"><s:property value="emailtemplate"/>
							<s:hidden name="emailtemplateCode"/><s:hidden name="emailtemplate"/></td>	
						<td width="21%" height="25" valign="top"> 
	     					<label  class = "set" name="appointment.template" id="appointment.template" 
								ondblclick="callShowDiv(this);"><%=label.get("appointment.template")%></label> : </td>
						<td width="25%" height="25" valign="top"><s:property value="template"/>
							<s:hidden name="templateCode"/><s:hidden name="template"/></td>	
	     			</tr>
	     		 -->	
	     			
	     			<tr>
	     				<td width="21%" height="25" valign="top"> 
	     					<label  class = "set" name="appointment.template" id="appointment.template" 
								ondblclick="callShowDiv(this);"><%=label.get("appointment.template")%></label> : </td>
	     				<td width="25%" height="25" valign="top">
	     					<s:property value="template"/>
							<s:hidden name="templateCode"/>
							<s:hidden name="template"/>
						</td>	
						<td width="21%" height="25" valign="top"> 
	     					<label  class = "set" name="attachmentOfAnnexure" id="attachmentOfAnnexure" 
								ondblclick="callShowDiv(this);"><%=label.get("attachmentOfAnnexure")%></label> : </td>
						<td width="25%" height="25" valign="top">
							<s:hidden name="dataPath" />
							<s:hidden name="annextureFileName"/>
							<s:property value="annextureFileName"/>
						</td>	
	     			</tr>
	     			
	     			<tr>
						<td width="21%" height="25" valign="top"> </td>
	     				<td width="25%" height="25" valign="top"> </td>	
						<td colspan="2"> 
	     					<a href="#" onclick="viewAttachedFile();"><font color="blue"><u>click here to view attached file</u></font></a>
						</td>	
					</tr>
	     		</table><!-- table 4 -->
	     	</td>
	     </tr>
	     
	      <s:if test="showCandidateCommentsFlag">
	     	<tr>
				<td colspan="3" width="100%">
					<table width="100%" border="0" cellpadding="1" cellspacing="1" id="evalDtls" class="formbg"> 
						<tr>
					  		<td width="25%"><strong class="formhead">Candidate Comments : </strong></td>
					  		<td colspan="3"><s:property value="candidateComments"/></td>
						</tr>
					</table>
				</td>
			</tr>			
	   </s:if>  
	     
	      <tr>
	     	<td width="100%" colspan="3">
	     		<!--<s:if test="addcandFlag">
	     			<s:submit value="   Cancel" cssClass="del" action="CreateOffer_input"/></s:if>
	     		<s:else>
		     		<s:submit value="   Edit" cssClass="edit" action="OfferDetails_backFromView"/>
		     		<s:submit value="   Cancel" cssClass="del" action="AppointmentDetails_reset"/>
	     		</s:else>
	     	-->
		     	<s:if test="doubleClickViewModeFlag">
		     		<s:submit value=" Cancel" cssClass="cancel" action="CreateAppointment_dupinput"/>
	     		</s:if>
	     		<s:else>
	     			<s:submit value=" Edit" cssClass="edit" action="AppointmentDetails_backFromView"/>
	     			<!--
	     				<s:submit value="Preview Appointment" cssClass="token" onclick="return validatePreviewForTemplate()" action="AppointmentDetails_previewappointment"/>
	     			 -->
			     	<s:submit  value="Preview Appointment in Doc" cssClass="token" onclick="return validatePreviewForTemplate()" action="AppointmentDetails_previewappointment"/>
			     	<input type="button" value="Preview Appointment in PDF" class="token"	onclick="return validatePreviewForTemplatePDF()" />
			     	<s:submit id="mydisplay" value="Email Appointment" cssClass="token" onclick="return validatePreviewForEmailTemplate()" action="AppointmentDetails_emailappointment"/>
		     		<s:submit value=" Cancel" cssClass="cancel" action="CreateAppointment_dupinput"/>
	     		</s:else>
	     	</td>
	     </tr>
	</table><!-- main table -->
</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<script>
showMonths();
	function showMonths(){
		if(document.getElementById("paraFrm_probation").value == "Yes"){
			document.getElementById('months').style.display = '';
		}
		else{
			document.getElementById('months').style.display = 'none';
		}
		
		var appointmentStatus = document.getElementById('paraFrm_appointmentStatus').value;
		var myvalue = document.getElementById('paraFrm_appointmentCode').value;
		if((myvalue!=null || myvalue!=" ")&& appointmentStatus!="") {
				document.getElementById('paraFrm_appointmentStatus').value=appointmentStatus;
				
				if(document.getElementById('paraFrm_appointmentStatus').value=='Issued') {		
				document.getElementById('mydisplay').style.display = '';   
				document.getElementById('mydisplay1').style.display = '';   
				} else {		
				document.getElementById('mydisplay').style.display = 'none'; 
				document.getElementById('mydisplay1').style.display = 'none';
				}
		} else {
		document.getElementById('mydisplay').style.display = 'none';  
		document.getElementById('mydisplay1').style.display = 'none';  
		}
	}
	
	function callCancelAppoint()
	{
	 document.getElementById('paraFrm').action="CreateAppointment_showAppointmentList.action?status=OA";
	 document.getElementById('paraFrm').submit();
	}
	
	function viewAttachedFile()
	{
		var annextureFileName = document.getElementById('paraFrm_annextureFileName').value;
		if(annextureFileName == '') {
			alert('File is not attached.');
			return false;
		 }
		document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = "AppointmentDetails_viewAttachedAnnexure.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
	}
	
	function validatePreviewForTemplate(){
		var fieldName = ["paraFrm_requisitionName", "paraFrm_candidateName","paraFrm_reportingTo","paraFrm_signingAuthority","paraFrm_template"];
		var lableName = ["requisition.code", "candidate.name" 
							,"reporting.to","signing.authority","appointment.template"];
		var flag      = ["select", "select", "select","select","select"];
		
		if(!validateBlank(fieldName, lableName, flag))return false;		
	}
	
	
	function validatePreviewForEmailTemplate(){
		var fieldName = ["paraFrm_requisitionName", "paraFrm_candidateName","paraFrm_reportingTo","paraFrm_signingAuthority"];
		var lableName = ["requisition.code", "candidate.name", 
							"reporting.to","signing.authority"];
		var flag      = ["select", "select", "select","select"];
		
		if(!validateBlank(fieldName, lableName, flag))return false;		
		
		var con = confirm("Do you really want to email this appointment?");
   			if(con)
   			{
   				return true;
   			}else {
   				return false;
   			}	
	}
	
function validatePreviewForTemplatePDF(){
		var fieldName = ["paraFrm_requisitionName", "paraFrm_candidateName","paraFrm_reportingTo","paraFrm_signingAuthority"];
		var lableName = ["requisition.code", "candidate.name" ,"reporting.to","signing.authority"];
		var flag      = ["select", "select", "select", "select"];
		
		if(!validateBlank(fieldName, lableName, flag))return false;		
		
		if(document.getElementById("paraFrm_template").value=="") {
			alert("Please "+document.getElementById("appointment.template").innerHTML.toLowerCase());
			document.getElementById("paraFrm_template").focus();
			return false;
		}
		
		callReport("AppointmentDetails_previewAppointmentPDF.action");
	}	
</script>