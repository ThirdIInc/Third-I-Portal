<%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="InductionSchedule" validate="true" id="paraFrm" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg"><!-- Final Table -->
		 <tr><!--InductionSchedule-->
			<td colspan="5">
				<table width="100%" border="0"   cellpadding="0" cellspacing="0" ><!--Table 1-->
					<tr>
				      <td colspan="3" width="100%">
				        <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
							<tr>
								<td width="4%" valign="bottom" class="txt"><strong class="formhead"><img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" /></strong></td>
								<td width="93%" class="txt"><strong class="text_head">Schedule Induction </strong></td>
								<td width="3%" valign="top" class="txt"><div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
							</tr>
						</table>
					  </td>
					</tr>
				</table><!--Table 1-->
			</td>
		</tr><!--InductionSchedule-->
		<tr>
			<td>
				<table width="100%" align="center" cellpadding="0" cellspacing="2"><!--button panel-->
					<tr>
         				 <td width="78%" colspan=2">
              				<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp"/>
             	 		</td>
             	 		<td width="22%">
							<div align="right"><font color="red">*</font>Indicates Required</div>
						</td>
					</tr>			
				</table><!--button panel-->
			</td>
		</tr>	
		<tr><!--Schedule interview-->
			<td colspan="5">
				<table width="100%" border="0"   cellpadding="0" cellspacing="0" class="formbg"><!--Table 2-->
					<tr>
						<td width="100%"></td>
					</tr>
					<tr>
						<td width="100%"><strong>Induction Form</strong></td>
					</tr>
					<tr>
						<td width="100%"></td>
					</tr>
					<tr>
						<td>
							
							<table width="100%" border="0"  align="center" cellpadding="0" cellspacing="2"><!--Table header-->
								<tr>
									<td width="20%">
										<label  class = "set" name="indname" id="indname" ondblclick="callShowDiv(this);"><%=label.get("indname")%></label> :</td>								<td width="25%"><s:property value="inductionName"/><s:hidden name="inductionCode"/>
									</td>
									<td width="20%" >
										<label  class = "set" name="cntperson" id="cntperson" ondblclick="callShowDiv(this);"><%=label.get("cntperson")%></label> :
									</td>
									<td width="25%"><s:hidden name="cntPersonId" /><s:hidden name="cntPersonToken"/><s:property value="contactPerson"/>
									</td>
								</tr>
								<tr>
									<td width="20%">
										<label  class = "set" name="indfrom" id="indfrom" ondblclick="callShowDiv(this);"><%=label.get("indfrom")%></label> :
									</td>
									<td width="25%"><s:property value="inductionFrom"/></td>
									<td width="20%" >
										<label  class = "set" name="indto" id="indto" ondblclick="callShowDiv(this);"><%=label.get("indto")%></label> :
									</td>
									<td width="25%"><s:property value="inductionTo"/></td>	
								</tr>
								<tr>
									<td width="19%" colspan="1" >
										<label  class = "set" name="inddesc" id="inddesc" ondblclick="callShowDiv(this);"><%=label.get("inddesc")%></label> :
									</td>
									<td width="81%" colspan="3"><s:property value="inducDesc" /></td>
									<td width="40%"></td>
								</tr>
								<tr>
									<td width="19%" colspan="1" >
										<label  class = "set" name="indvenue" id="indvenue"	ondblclick="callShowDiv(this);"><%=label.get("indvenue")%></label> :
									</td>
									<td width="81%" colspan="3"><s:property value="inducVenue" /></td>
									<td width="40%"></td>
								</tr>
							</table><!--Table header-->
						</td>
					</tr>		
				</table><!--Table 2-->
			</td>
		</tr>
		<tr>
			<td colspan="5" valign="bottom" class="txt"><img
				src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
		</tr>
		<tr>
									<td colspan="5">
										<table width="100%" border="0" cellpadding="1" cellspacing="1" class="formbg"><!--Table 7-->
											<tr>
												<td>
													<table width="100%" border="0"  align="center" cellpadding="0" cellspacing="2"><!--Candidate List header-->
														<tr>
															<td><strong class="formhead">Activity List : </strong></td>
														 </tr>
													</table><!--Candidate List header-->	
												</td>
										</tr>
											<tr>
												<td>
													<table width="100%" border="0" cellpadding="1" cellspacing="1" class="sortable"><!--Table 6-->
														<tr>
															<td width="10%" valign="top" class="formth" ><label  class = "set" name="serial.no" id="srno1" 
			            										ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
															<td width="15%" valign="top" class="formth"><label  class = "set" name="date" id="date" 
			            										ondblclick="callShowDiv(this);"><%=label.get("date")%></label></td>
															<td width="10%" valign="top" class="formth"><label  class = "set" name="stattime" id="stattime" 
			            										ondblclick="callShowDiv(this);"><%=label.get("stattime")%></label></td>
															<td width="10%" valign="top" class="formth"><label  class = "set" name="endtime" id="endtime" 
			            										ondblclick="callShowDiv(this);"><%=label.get("endtime")%></label></td>
															<td width="30%" valign="top" class="formth" nowrap="nowrap"><label  class = "set" name="actlistdtl" id="actlistdtl" 
			            										ondblclick="callShowDiv(this);"><%=label.get("actlistdtl")%></label></td>
															<td width="30%" valign="top" class="formth" nowrap="nowrap"><label  class = "set" name="actowner" id="actowner" 
			            										ondblclick="callShowDiv(this);"><%=label.get("actowner")%></label></td>
														</tr>
														<%! int a=0; %>
							
							  							<%int b=1; %>
																<s:iterator status="stat" value="activityList">
								                        	<tr>
								                        		<td width="10%" class="sortableTD"><%=b %></td>
								                        		
								                        		<td width="15%" class="sortableTD">&nbsp;<s:property value="actListDate"/>
								                        			<s:hidden name="actListDate" id="<%="actListDate"+b%>"/>&nbsp;</td>
								                        		<td width="15%" class="sortableTD">&nbsp;<s:property value="actListStartTime"/>
								                        			<s:hidden name="actListStartTime" id="<%="actListStartTime"+b%>"/>&nbsp;</td>
								                        		<td width="15%" class="sortableTD">&nbsp;<s:property value="actListEndTime"/>
								                        			<s:hidden name="actListEndTime" id="<%="actListEndTime"+b%>"/>&nbsp;</td>
								                        		<td width="15%" class="sortableTD">&nbsp;<s:property value="actListDetails"/>
								                        			<s:hidden name="actListDetails" id="<%="actListDetails"+b%>"/>&nbsp;</td>
								                        		<td width="15%" class="sortableTD">&nbsp;<s:property value="actListOwner"/>
								                        			<s:hidden name="actListOwner" id="<%="actListOwner"+b%>"/>&nbsp;
								                        			<s:hidden name="actListOwnerIDIerator" id="<%="actListOwnerIDIerator"+b%>"/>
								                        			</td>								
								                        		<s:hidden name="ownerType" id="<%="ownerType"+b%>"/>
								                        		<s:hidden name="designation" id="<%="designation"+b%>"/>
								                        	</tr>
								                        <%b++;%>
													</s:iterator>
														  <%a=b;%>
													</table><!--Table 6-->
												</td>
											</tr>	
										</table><!--Table 7-->
									</td>
								</tr>		
		<tr>
			<td colspan="5" valign="bottom" class="txt"><img
			src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
		</tr>
		<tr>
			<td>
				<table width="100%" border="0"  align="center" cellpadding="0" cellspacing="2"><!--Table Employee-->
								<tr>
									<td colspan="5">
										<table width="100%" border="0" cellpadding="1" cellspacing="1" class="formbg"><!--Table 7-->
											<tr>
												<td>
													<table width="100%" border="0"  align="center" cellpadding="0" cellspacing="2"><!--Candidate List header-->
														<tr>
															<td><strong class="formhead">Employee/Participants Details : </strong></td>
														 </tr>
													</table><!--Candidate List header-->	
												</td>
										</tr>
										<!-- change the label name as empname  and set proper alignment of Employee/Participants Details  records-->
											<tr>
												<td>
													<table width="100%" border="0" cellpadding="1" cellspacing="1" class="sortable" id="tblEmployeeList"><!--Table 6-->
														<tr>
															<td width="4%" valign="top" class="formth" ><label  class = "set" name="serial.no" id="srno1" 
			            										ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
															<td width="30%" valign="top" class="formth"><label  class = "set" name="empname" id="empname" 
			            										ondblclick="callShowDiv(this);"><%=label.get("empname")%></label></td>
															<td width="10%" valign="top" class="formth"><label  class = "set" name="division" id="division" 
			            										ondblclick="callShowDiv(this);"><%=label.get("division")%></label></td>
															<td width="10%" valign="top" class="formth"><label  class = "set" name="branch" id="branch" 
			            										ondblclick="callShowDiv(this);"><%=label.get("branch")%></label></td>
															<td width="20%" valign="top" class="formth" nowrap="nowrap"><label  class = "set" name="department" id="department" 
			            										ondblclick="callShowDiv(this);"><%=label.get("department")%></label></td>
															<td width="20%" valign="top" class="formth" nowrap="nowrap"><label  class = "set" name="doj" id="doj" 
			            										ondblclick="callShowDiv(this);"><%=label.get("doj")%></label></td> 
			            									<td width="20%" valign="top" class="formth" nowrap="nowrap"><label  class = "set" name="givenFeedbackStatus" id="givenFeedbackStatus" 
			            										ondblclick="callShowDiv(this);"><%=label.get("givenFeedbackStatus")%></label></td>	
			            									<td width="10%" valign="top" class="formth" ><label  class = "set" name="viewfeedback" id="viewfeedback" 
			            											ondblclick="callShowDiv(this);"><%=label.get("viewfeedback")%></label></td>	
														</tr>
														<%! int c=0; %>
							
							  							<%int j=1; %>
																<s:iterator status="stat" value="participantList">
								                        	<tr>
								                        		<td width="10%" class="sortableTD"><%=j %></td>				                        	
								                        		<s:hidden name="feedbackStatus" id="<%="feedbackStatus"+j%>"/>
								                        		
								                        		<td width="15%" class="sortableTD"><s:property value="empName"/>
								                        			<s:hidden name="empName" id="<%="empName"+j%>"/><s:hidden name="empCode" id="<%="empCode"+j%>"/>&nbsp;</td>
								                        		<td width="15%" class="sortableTD"><s:property value="divName"/>
								                        			<s:hidden name="divName" id="<%="divName"+j%>"/><s:hidden name="divCode" id="<%="divCode"+j%>"/>&nbsp;</td>
								                        		<td width="15%" class="sortableTD"><s:property value="brName"/>
								                        			<s:hidden name="brName" id="<%="brName"+j%>"/><s:hidden name="brCode" id="<%="brCode"+j%>"/>&nbsp;</td>
								                        		<td width="15%" class="sortableTD"><s:property value="deptListName"/>
								                        			<s:hidden name="deptListName" id="<%="deptListName"+j%>"/><s:hidden name="deptListCode" id="<%="deptListCode"+j%>"/>&nbsp;</td>
								                        		<td width="15%" class="sortableTD"><s:property value="dtOfJoinList"/>
								                        			<s:hidden name="dtOfJoinList" id="<%="dtOfJoinList"+j%>"/>&nbsp;</td>	
								                        		<td width="15%" class="sortableTD"><s:property value="givenFeedbackStatus"/>
								                        			<s:hidden name="givenFeedbackStatus" id="<%="givenFeedbackStatus"+j%>"/>&nbsp;</td>	
								                        		<td class="sortableTD" width="10%"><input
																		type="button" name="view" class="token"
																		value="view" onclick="viewFeedbackDetails('<s:property value="empCode"/>','<s:property value="feedbackStatus"/>')"/>&nbsp;</td>									
								                        	</tr>
								                        <%j++;%>
													</s:iterator>
														  <%c=j;%>
													</table><!--Table 6-->
												</td>
											</tr>	
										</table><!--Table 7-->
									</td>
								</tr>		
				</table><!--Table Employee-->
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" align="center" cellpadding="0" cellspacing="2"><!--button panel-->
					<tr>
         				 <td>
              				<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp"/>
             	 		</td>
					</tr>			
				</table><!--button panel-->
			</td>
		</tr>															
	</table><!-- Final Table -->	
	<s:hidden name="ownerIntOrExt"/>
</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js">
</script>


<script>
	function editFun()
	{
		var inducCode =  document.getElementById("paraFrm_inductionCode").value;
		//alert("Induction code : "+inducCode);
		document.getElementById("paraFrm").action='InductionSchedule_edit.action?inducCode='+inducCode;
	    document.getElementById("paraFrm").submit();
	}
	
	function cancelFun(){
		document.getElementById("paraFrm").action="InductionSchedule_getInducDueList.action";
	    document.getElementById("paraFrm").submit();
	}
	
	function viewFeedbackDetails(empCode,feedbackStatus)
	{
	var inducCode =  document.getElementById("paraFrm_inductionCode").value;
		//alert("inducCode :::::::: "+inducCode);
		if(feedbackStatus =="N" || feedbackStatus =="")
		{			
			alert('Feedback not given');			
			return false;
		}
	 	else
		{		 
			var wind = window.open('','wind','width=800,height=500,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
			document.getElementById('paraFrm').target = "wind";
			document.getElementById('paraFrm').action = 'InductionFeedback_viewFeedback.action?empCode='+empCode+'&inducCode='+inducCode ;
			document.getElementById('paraFrm').submit();
		 	document.getElementById('paraFrm').target = "_self";		
		}		
		
	}
</script>