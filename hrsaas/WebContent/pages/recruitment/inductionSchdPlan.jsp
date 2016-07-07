<%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="InductionSchedule" validate="true" id="paraFrm" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg"><!-- Final Table -->
		 <tr><!--InductionSchedule-->
			<td colspan="5">
				<table width="100%" border="0"   cellpadding="0" cellspacing="0" ><!--Table 1-->
					<tr>
						<td colspan="5" valign="bottom" class="txt">&nbsp;</td>
					</tr>
					
		<tr>
	      <td colspan="3" width="100%">
	        <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong class="formhead"><img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Schedule Induction</strong></td>
					<td width="3%" valign="top" class="txt">  <div align="right"><img	src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>  </td>
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
         				 <td width="78%" colspan="2">
              				<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp"/>
              				<!-- 
              				<input type="button" class="token" onclick="javascript:callsF9(500,325,'InductionSchedule_f9addNewEmployee.action');"
              				 theme="simple"	value="Add New Employee" />
              				 -->
             	 		</td>
             	 		<td width="22%"><div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required </div></td>
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
										<label  class = "set" name="indname" id="indname" ondblclick="callShowDiv(this);"><%=label.get("indname")%></label> :<font color="red">*</font>
									</td>
									<td width="25%">
										<s:textfield name="inductionName" size="25" maxlength="30" onkeypress="return allCharacters(this);" />
									</td>
									<td width="20%">
										<label  class = "set" name="cntperson" id="cntperson" ondblclick="callShowDiv(this);"><%=label.get("cntperson")%></label> :<font color="red">*</font>
									</td>
									<td width="25%">
										<s:hidden name="cntPersonId" />
										<s:hidden name="cntPersonToken"/>
										<s:textfield name="contactPerson" size="25"	readonly="true" />
											<img src="../pages/images/search2.gif" height="16" align="absmiddle" width="16" theme="simple"	onclick="javascript:callsF9(500,325,'InductionSchedule_f9CntPerson.action');" >
									</td>
								</tr>
								
								<tr>
									<td width="20%">
										<label  class = "set" name="indfrom" id="indfrom" ondblclick="callShowDiv(this);"><%=label.get("indfrom")%></label><br>
										<font style="size: 6" > (dd-mm-yyyy)</font> :<font color="red">*</font>
									</td>
									<td width="25%">
										<s:textfield name="inductionFrom" size="25" onkeypress="return numbersWithHiphen();" maxlength="10"  />
										<a	href="javascript:NewCal('paraFrm_inductionFrom','DDMMYYYY');"> 
										<img src="../pages/images/recruitment/Date.gif" class="iconImage" height="16" align="absmiddle" width="16"> </a>
									</td>
									<td width="20%">
										<label  class = "set" name="indto" id="indto" ondblclick="callShowDiv(this);"><%=label.get("indto")%></label><br>
											<font style="size: 6" > (dd-mm-yyyy)</font> :<font color="red">*</font> 
									</td>
									<td width="25%">
										<s:textfield name="inductionTo" size="25" onkeypress="return numbersWithHiphen();" maxlength="10"/>
										<a href="javascript:NewCal('paraFrm_inductionTo','DDMMYYYY');"> 
											<img src="../pages/images/recruitment/Date.gif" class="iconImage" height="16" align="absmiddle" width="16"> </a>
									</td>									
								</tr>
								
								<tr>
									<td width="20%" colspan="1">
										<label class = "set" name="inddesc" id="inddesc" ondblclick="callShowDiv(this);"><%=label.get("inddesc")%></label> :
									</td>
									<td width="25%" colspan="3">
										<s:textarea label="%{getText('Select Interview Requirements')}"	theme="simple" cols="60" rows="3" name="inducDesc" onkeyup="callLength('inducDesc','remaincharindDesc','500');"  onkeypress="return allCharacters(this);" />&nbsp;&nbsp;				 					
										<img src="../pages/images/zoomin.gif" height="12" align="absmiddle" width="12" theme="simple" onclick="javascript:callWindow('paraFrm_inducDesc','inddesc','','paraFrm_remaincharindDesc','500');" >
										&nbsp;&nbsp;Remaining chars <s:textfield name="remaincharindDesc" readonly="true" size="5" />
									</td>																                                                                 
								</tr>
								
								<tr>
									<td width="19%" colspan="1">
										<label  class = "set" name="indvenue" id="indvenue"	ondblclick="callShowDiv(this);"><%=label.get("indvenue")%></label> :<font color="red">*</font>
									</td>
									<td width="25%" colspan="3">
										<s:textarea label="%{getText('Select Interview Requirements')}"	theme="simple" cols="60" rows="3" name="inducVenue" onkeyup="callLength('inducVenue','remaincharindVenue','500');" onkeypress="return allCharacters(this);"/>&nbsp;&nbsp;
											<img src="../pages/images/zoomin.gif" height="12" align="absmiddle" width="12" theme="simple" onclick="javascript:callWindow('paraFrm_inducVenue','indvenue','','paraFrm_remaincharindVenue','500');" >
											&nbsp;&nbsp;Remaining chars <s:textfield name="remaincharindVenue" readonly="true" size="5" />
									</td>									
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
		<tr><!--Activity Details-->
			<td colspan="5">
				<table width="100%" border="0"   cellpadding="0" cellspacing="0" class="formbg"><!--Table 2-->
					<tr>
						<td width="100%"></td>
					</tr>
					
					<tr>
						<td width="100%"><strong>Activity Details</strong></td>
					</tr>
					
					<tr>
						<td width="100%"></td>
					</tr>
					
					<tr>
						<td>							
							<table width="100%" border="0"  align="center" cellpadding="0" cellspacing="2"><!--Table header-->							
								<tr>
									<td width="20%" nowrap="nowrap">
										<label  class = "set" name="actdtl" id="actdtl"	ondblclick="callShowDiv(this);"><%=label.get("actdtl")%></label>
										<font style="size: 6" > (dd-mm-yyyy)</font> :<font color="red">*</font>
									</td>
									<td width="25%" nowrap="nowrap" colspan="3">
										<s:textfield name="actDate" size="25" onkeypress="return numbersWithHiphen();" maxlength="10"/>
										<a href="javascript:NewCal('paraFrm_actDate','DDMMYYYY');"> 
											<img src="../pages/images/recruitment/Date.gif" class="iconImage" height="16" align="absmiddle" width="16"> 
										</a>
									</td>											
								</tr>
							
								<tr>
									<td width="20%" colspan="1">
										<label  class = "set" name="actdetail" id="actdetail" ondblclick="callShowDiv(this);"><%=label.get("actdetail")%></label> :
									</td>
									<td colspan="3">
										<s:textarea label="%{getText('Select text')}" theme="simple" cols="48" rows="3" name="actDetails" onkeyup="callLength('actDetails','remaincharactivityDetail','500');" onkeypress="return allCharacters(this);"/>&nbsp;&nbsp;
										<img src="../pages/images/zoomin.gif" height="12" align="absmiddle" width="12" theme="simple" onclick="javascript:callWindow('paraFrm_actDetails','actdetail','','paraFrm_remaincharactivityDetail','500');" >
											&nbsp;&nbsp;Remaining chars <s:textfield name="remaincharactivityDetail" readonly="true" size="5" /> 
										<s:hidden name="rowId"/>
									</td>
								</tr>
								
								<tr>
									<td width="20%">
										<label  class = "set" name="actstattime" id="actstattime" ondblclick="callShowDiv(this);"><%=label.get("actstattime")%></label><br> 
										<font style="size: 6" >(hh:mm)</font>:<font color="red">*</font>
									</td>
									<td width="25%">
										<s:textfield name="actStartTime" size="25" maxlength="5" onkeypress="return numbersWithColon();" />
									</td>
									<td width="20%">
										<label  class = "set" name="actendtime" id="actendtime"	ondblclick="callShowDiv(this);"><%=label.get("actendtime")%></label><br> 
										<font style="size: 6" >(hh:mm)</font> :<font color="red">*</font>
									</td>
									<td width="25%">
										<s:textfield name="actEndTime" size="20" maxlength="5" onkeypress="return numbersWithColon();"/>
									</td>
								</tr>
								
								<tr>
									<td width="25%">
										<label  class = "set" name="actowner" id="actowner"	ondblclick="callShowDiv(this);"><%=label.get("actowner")%></label> :
									</td>
									<td width="25%" colspan="3">
										<input type="radio" name="ownerButton" id="ownerInternal" value="internal" onclick="callRadioButton(this);"/>Internal
										<input type="radio" name="ownerButton" value="external" id="ownerExternal" onclick="callRadioButton(this);"/>External
									</td>
								</tr>
								
								<tr id="ownerFlagInternal">
									<td width="20%">
										<label  class = "set" name="ownernameint" id="ownernameint" ondblclick="callShowDiv(this);"><%=label.get("ownernameint")%></label> :<font color="red">*</font>
									</td>
									<td width="28%">
										<s:textfield name="ownerNameInt" size="25" readonly="true"/>
										<s:hidden name="ownerTokenInt"/>									
										<img src="../pages/images/search2.gif" height="16" align="absmiddle" width="16" theme="simple" onclick="javascript:callsF9(500,325,'InductionSchedule_f9OwnerName.action');" >
										<s:hidden name="ownerIdInt"/>
									</td>	
									<td width="20%">
										<label  class = "set" name="designation" id="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label> :
									</td>
									<td width="25%">
										<s:textfield name="designationInt" readonly="true" size="25"/>
									</td>			
								</tr>
								
								<tr id="ownerFlagExternal">
									<td width="20%">
										<label  class = "set" name="ownernameext" id="ownernameext"	ondblclick="callShowDiv(this);"><%=label.get("ownernameext")%></label> :<font color="red">*</font>
									</td>
									<td width="25%">
										<s:textfield name="ownerNameExt"  size="25" onkeypress="return allCharacters(this);"/>
										<s:hidden name="ownerTokenExt"/>
										<s:hidden name="ownerIdExt"/>
									</td>	
									<td width="20%">
										<label  class = "set" name="designation" id="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label> :
									</td>
									<td width="25%">
										<s:textfield name="designationExt" size="20"/>
									</td>			
								</tr>
								
								<tr>
									<td width="20%">
										<label  class = "set" name="venue" id="venue" ondblclick="callShowDiv(this);"><%=label.get("venue")%></label> :
									</td>
									<td width="25%">
										<input type="checkbox" name="chkVenue" id="chkVenue" value="N" onclick="callSameAsAbove()"/>
			                        	<input type="hidden" name="checkBoxVenue"  value="N"/>
			                        		<label  class = "set" name="above" id="above" ondblclick="callShowDiv(this);"><%=label.get("above")%></label>
			                        </td>
			                        <td width="20%"></td>
									<td width="25%"></td>				
								</tr>
								
								<tr>
									<td width="19%" colspan="1">
										<label  class = "set" name="venuedtl" id="venuedtl"	ondblclick="callShowDiv(this);"><%=label.get("venuedtl")%></label> :
									</td>
									<td width="81%" colspan="3">
										<s:textarea label="%{getText('Select Interview Requirements')}"	theme="simple" cols="48" rows="3" name="actVenue" onkeyup="callLength('actVenue','remaincharvenueDetails','500');" onkeypress="return allCharacters(this);"/>&nbsp;&nbsp;
											<img src="../pages/images/zoomin.gif" height="12" align="absmiddle" width="12" theme="simple" onclick="javascript:callWindow('paraFrm_actVenue','venuedtl','','paraFrm_remaincharvenueDetails','500');" >
												&nbsp;&nbsp;Remaining chars <s:textfield name="remaincharvenueDetails" readonly="true" size="5" />
									</td>
									<td width="40%"></td>
								</tr>
								
								<tr>
									<td colspan="5"></td>
								</tr>
								
								<tr>
									<td colspan="2"></td>
									<td>
										<s:submit cssClass="token" action="InductionSchedule_addActivity" theme="simple" value="Add Activity Details" onclick="return addvalidation();" />	
									</td>
								</tr>
								
								<tr>
									<td colspan="5"></td>
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
															<td width="10%" valign="top" class="formth" ><b> 
																<label  class = "set" name="serial.no" id="srno1" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label> </b>
															</td>
															<td valign="top" class="formth"> <b> 
																<label  class = "set" name="date" id="date"	ondblclick="callShowDiv(this);"><%=label.get("date")%></label> </b> 
															</td>
															<td valign="top" class="formth"> <b> 
																<label  class = "set" name="stattime" id="stattime"	ondblclick="callShowDiv(this);"><%=label.get("stattime")%></label> </b> 
															</td>
															<td valign="top" class="formth"> <b> 
																<label  class = "set" name="endtime" id="endtime" ondblclick="callShowDiv(this);"><%=label.get("endtime")%></label> </b> 
															</td>
															<td width="30%" valign="top" class="formth" nowrap="nowrap"><b> 
																<label  class = "set" name="actdetail" id="actlistdtl" ondblclick="callShowDiv(this);"><%=label.get("actdetail")%></label> </b>
															</td>
															<td width="30%" valign="top" class="formth" nowrap="nowrap"><b> 
																<label  class = "set" name="actowner" id="actowner"	ondblclick="callShowDiv(this);"><%=label.get("ownernameint")%></label> </b>
															</td>
															<td width="5%" valign="top" class="formth" nowrap="nowrap">&nbsp;</td>
															<td width="5%" valign="top" class="formth">&nbsp;</td>
														</tr>
														<%! int a=0; %>
							
							  							<%int b=1; %>
																<s:iterator status="stat" value="activityList">
								                        	<tr>
								                        		<td width="10%" align="center" class="sortableTD"><%=b %></td>
								                        		
								                        		<td  nowrap="nowrap" class="sortableTD">&nbsp;<s:property value="actListDate"/>
								                        			<s:hidden name="actListDate" id="<%="actListDate"+b%>"/></td>
								                        		<td  nowrap="nowrap" class="sortableTD">&nbsp;<s:property value="actListStartTime"/>
								                        			<s:hidden name="actListStartTime" id="<%="actListStartTime"+b%>"/></td>
								                        		<td  nowrap="nowrap"  class="sortableTD">&nbsp;<s:property value="actListEndTime"/>
								                        			<s:hidden name="actListEndTime" id="<%="actListEndTime"+b%>"/></td>
								                        		<td width="15%" class="sortableTD">&nbsp;<s:property value="actListDetails"/>
								                        			<s:hidden name="actListDetails" id="<%="actListDetails"+b%>"/>
								                        			<s:hidden name="actVenueDetails" id="<%="actVenueDetails"+b%>"/>
								                        			</td>
								                        		<td width="15%" class="sortableTD">&nbsp;<s:property value="actListOwner"/>
								                        			<s:hidden name="actListOwner" id="<%="actListOwner"+b%>"/>
								                        			<s:hidden name="actListOwnerIDIerator" id="<%="actListOwnerIDIerator"+b%>"/>
								                        		</td>								
								                        		<td width="5%" class="sortableTD">
								                        			<input type="button" class="edit" value=" Edit"  onclick="editList('<%=b %>');"/>
								                        		</td>
								                        		<td width="5%" class="sortableTD">
								                        			<input type="button" class="delete" value=" Remove"  onclick="deleteList('<%=b %>');"/>
								                        			<s:hidden name="ownerType" id="<%="ownerType"+b%>"/>
								                        			<s:hidden name="designation" id="<%="designation"+b%>"/>
								                        		</td>
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
															 <td align="right"> <input type="button" class="delete" value="   Remove"  onclick="return deleteEmployee();"/></td>
														 </tr>
													</table><!--Candidate List header-->	
												</td>
										</tr>
											<tr>
												<td>
													<table width="100%" border="0" cellpadding="1" cellspacing="1" class="sortable" id="tblEmployeeList"><!--Table 6-->
														<tr>
															<td width="10%" valign="top" class="formth" ><b><label  class = "set" name="serial.no" id="srno2" 
			            										ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label> </b></td>
															<td width="15%" valign="top" class="formth"><b> 
																<label  class = "set" name="empname" id="empname" ondblclick="callShowDiv(this);"><%=label.get("empname")%></label> </b>
															</td>
															<td width="10%" valign="top" class="formth"><b>
																<label  class = "set" name="division" id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label> </b>
															</td>
															<td width="10%" valign="top" class="formth"><b>
																<label  class = "set" name="branch" id="branch"	ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> </b>
															</td>
															<td width="30%" valign="top" class="formth" nowrap="nowrap"><b> 
																<label  class = "set" name="department" id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> </b>
															</td>
															<td  valign="top" class="formth" > <b> 
																<label  class = "set" name="doj" id="doj" ondblclick="callShowDiv(this);"><%=label.get("doj")%></label> </b> 
															</td>
															
															<td width="20%" valign="top" class="formth" nowrap="nowrap"><label  class = "set" name="givenFeedbackStatus" id="givenFeedbackStatus" 
			            										ondblclick="callShowDiv(this);"><%=label.get("givenFeedbackStatus")%></label></td>	
			            										
															<td width="5%" valign="top" class="formth" abbr="right">
																<input class="classcheck" 
																	style="text-align: center; border: none; margin: 1px"
																	type="checkbox" size="2" name="chkAll" id="chkAll" onclick="return callChkAll();"">							
															</td>
														</tr>
														<%! int c=0; %>
							
							  							<%int j=1; %>
																<s:iterator status="stat" value="participantList">
								                        	<tr>
								                        		<td width="10%" class="sortableTD"><%=j %></td>
								                        		
								                        		<td width="15%" class="sortableTD"><s:property value="empName"/>
								                        			<s:hidden name="empName" id="<%="empName"+j%>"/><s:hidden name="empCode" id="<%="empCode"+j%>"/>&nbsp;</td>
								                        		<td width="15%" class="sortableTD"><s:property value="divName"/>
								                        			<s:hidden name="divName" id="<%="divName"+j%>"/><s:hidden name="divCode" id="<%="divCode"+j%>"/> &nbsp; </td>
								                        		<td width="15%" class="sortableTD"><s:property value="brName"/>
								                        			<s:hidden name="brName" id="<%="brName"+j%>"/><s:hidden name="brCode" id="<%="brCode"+j%>"/> &nbsp; </td>
								                        		<td width="15%" class="sortableTD"><s:property value="deptListName"/>
								                        			<s:hidden name="deptListName" id="<%="deptListName"+j%>"/><s:hidden name="deptListCode" id="<%="deptListCode"+j%>"/> &nbsp; </td>
								                        		<td  nowrap="nowrap" class="sortableTD"><s:property value="dtOfJoinList"/>
								                        			<s:hidden name="dtOfJoinList" id="<%="dtOfJoinList"+j%>"/>&nbsp;</td>
								                        		
								                        		<td width="15%" class="sortableTD"><s:property value="givenFeedbackStatus"/>
								                        			<s:hidden name="givenFeedbackStatus" id="<%="givenFeedbackStatus"+j%>"/>&nbsp;</td>
								                        											
								                        		<td width="5%" class="sortableTD">
								                        			<input type="checkbox" name="chkEmpList" id="<%="chkEmpList"+j %>" value="N" onclick="callChk(<%=j %>)"/>
								                        			<input type="hidden" name="checkBoxEmpList" id="<%=j %>" value="N"/>						                        		
								                        			<s:hidden name="feedbackStatus" id="<%="feedbackStatus"%>"/>                        			
								                        		</td>
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
              			
              			<!-- 
								<input type="button" class="token" onclick="javascript:callsF9(500,325,'InductionSchedule_f9addNewEmployee.action');"
              				theme="simple"	value="Add New Employee"/>	              			
              			 -->
              			
             	 		</td>
					</tr>			
				</table><!--button panel-->
			</td>
		</tr>															
	</table><!-- Final Table -->	
	<s:hidden name="ownerIntOrExt"/>
	<s:hidden name="inductionCode"/>
	<s:hidden name="empNewToken"/>
	<s:hidden name="empNewName"/>
	<s:hidden name="empNewId"/>
	
	<s:hidden name="newlyAddedEmpToken" />
	<s:hidden name="newlyAddedEmpName" />
	<s:hidden name="newlyAddedEmpCode" />
	
</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js">
</script>


<script>
	
	
	onLoad();
function onLoad(){
		document.getElementById('ownerFlagInternal').style.display = "";
		document.getElementById('ownerFlagExternal').style.display = "none";
		document.getElementById('ownerInternal').checked = true;
		document.getElementById('paraFrm_ownerIntOrExt').value="internal";
	}
	
function addnewemployeeFun() {
	try {
		var wind = window.open('','wind','width=700,height=400,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
		document.getElementById('paraFrm').target = "wind";
	   	document.getElementById('paraFrm').action = 'InductionSchedule_f9addNewEmployee.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "_self";
	} catch(e) {
		alert("Error occured in add new employee : "+ e);
	}
}

function callRadioButton(obj){
		try{
		if(obj.value == "external"){
			document.getElementById('ownerFlagInternal').style.display = "none";
			document.getElementById('ownerFlagExternal').style.display = "";
			document.getElementById('paraFrm_ownerIntOrExt').value = obj.value;
		}
		else{
			document.getElementById('ownerFlagInternal').style.display = "";
			document.getElementById('ownerFlagExternal').style.display = "none";
			document.getElementById('paraFrm_ownerIntOrExt').value = obj.value;
		}
		}catch(e){
		alert(e);
		}
	}
	
	
function addvalidation()
	{
		if(document.getElementById('paraFrm_actDate').value=="")
		{
			alert("Please enter/select  "+document.getElementById('actdtl').innerHTML.toLowerCase());
			return false;
		}
		
		if(!validateDate("paraFrm_actDate",'actdtl')) return false;
		
		var frmDt = document.getElementById('paraFrm_inductionFrom').value;
  		var toDt = document.getElementById('paraFrm_inductionTo').value;
  		var actDate = document.getElementById('paraFrm_actDate').value;
  		
  		//if(!dateDifferenceEqual(frmDt, actDate, "paraFrm_actDate", "Induction From", "Activity Date"))return false;
  		var fieldName = new Array();
  		fieldName[0]="paraFrm_inductionFrom";
  		fieldName[1]="paraFrm_inductionTo";
  		fieldName[2]="paraFrm_actDate";
  		
  		var labelName= new Array();
  		labelName[0]='indfrom';
  		labelName[1]='indto';
  		labelName[2]='actdtl';
  		//if(!dateDifferenceEqual(actDate, toDt, "paraFrm_actDate",'indfrom','actdtl'))return false;
  		if(!dateBetweenTwoDates(fieldName,labelName)) return false;
  		
  		//if(!dateDifferenceEqual(actDate, toDt, "paraFrm_inductionTo", "Activity Date", "Induction From"))return false;
		
		if(!dateDifferenceEqual(actDate, toDt, "paraFrm_inductionTo",'actdtl','indfrom'))return false;
		
		
		var actDetails = document.getElementById('paraFrm_actDetails').value;
		if(actDetails.length >500)
		{
 			alert("Maximum length of '"+document.getElementById('actdetail').innerHTML.toLowerCase()+"' is 500 characters.");
			return false;
  		 }  
		
		if(document.getElementById('paraFrm_actStartTime').value=="")
		{
			//alert('Please enter start time');
				alert("Please enter  "+document.getElementById('actstattime').innerHTML.toLowerCase());
			
			return false;
		}
		
		//if(!validateTime("paraFrm_actStartTime", "Activity Start Time")) return false;
		
		if(!validateTime("paraFrm_actStartTime",'actstattime')) return false;
		
		if(document.getElementById('paraFrm_actEndTime').value=="")
		{
			//alert('Please enter end time');
				alert("Please enter  "+document.getElementById('actendtime').innerHTML.toLowerCase());
			
			return false;
		}
	//	if(!validateTime("paraFrm_actEndTime", "Activity End Time")) return false;
	
	  if(!validateTime("paraFrm_actEndTime",'actendtime')) return false;
	 	var  startTime=document.getElementById('paraFrm_actStartTime').value;
	  	var endTime=document.getElementById('paraFrm_actEndTime').value;
	    if(!timeDifference(startTime, endTime, 'paraFrm_actEndTime', 'actstattime', 'actendtime'))
			 {
			 	return false;
			 }
			   			 
		//alert(document.getElementById('paraFrm_ownerIntOrExt').value);
		if(document.getElementById('paraFrm_ownerIntOrExt').value=="external")
		{
			var extname=document.getElementById('paraFrm_ownerNameExt').value;		
			if(trim(extname)=="")
			{
				//alert('Please enter external owner name');
				alert("Please enter external "+document.getElementById('ownernameext').innerHTML.toLowerCase());
				document.getElementById('paraFrm_ownerNameExt').value="";
			
				return false;
			}
			
		}
		
		if(document.getElementById('paraFrm_ownerIntOrExt').value=="internal")
		{
			if(document.getElementById('paraFrm_ownerNameInt').value=="")
			{
				//alert('Please enter internal owner name');
				alert("Please select internal "+document.getElementById('ownernameint').innerHTML.toLowerCase());
			
				return false;
			}
		}
		
		var venueDetails = document.getElementById('paraFrm_actVenue').value;
		if(venueDetails.length >500)
		{
 			//alert("Maximum length of 'Venue Details' is 500 characters.");
 			alert("Maximum length of '"+document.getElementById('venuedtl').innerHTML.toLowerCase()+"' is 500 characters.");
			
			return false;
  		 } 

}


function editList(id){
		//alert(document.getElementById('actListDate'+id).value);
		//alert(id);
		document.getElementById('paraFrm_rowId').value = id;
		//var date = document.getElementById('actListDate'+id).value;
		//alert(date);
		document.getElementById('paraFrm_actDate').value = document.getElementById('actListDate'+id).value;
		document.getElementById('paraFrm_actDetails').value = document.getElementById('actListDetails'+id).value;
		document.getElementById('paraFrm_actStartTime').value = document.getElementById('actListStartTime'+id).value;
		document.getElementById('paraFrm_actEndTime').value = document.getElementById('actListEndTime'+id).value;
		document.getElementById('paraFrm_ownerIntOrExt').value = document.getElementById('ownerType'+id).value;
		document.getElementById('paraFrm_actVenue').value = document.getElementById('actVenueDetails'+id).value;
		
		
		
		
		if(document.getElementById('ownerType'+id).value =="internal"){
			document.getElementById('paraFrm_ownerNameInt').value = document.getElementById('actListOwner'+id).value;
			document.getElementById('paraFrm_designationInt').value = document.getElementById('designation'+id).value;
			document.getElementById('paraFrm_ownerIdInt').value = document.getElementById('actListOwnerIDIerator'+id).value;
			document.getElementById('ownerFlagInternal').style.display = "";
			document.getElementById('ownerFlagExternal').style.display = "none";
			document.getElementById('ownerInternal').checked = true;
		}
		else{
			document.getElementById('paraFrm_ownerNameExt').value = document.getElementById('actListOwner'+id).value;
			document.getElementById('paraFrm_designationExt').value = document.getElementById('designation'+id).value;
			document.getElementById('paraFrm_ownerIdExt').value = "";
			document.getElementById('ownerFlagInternal').style.display = "none";
			document.getElementById('ownerFlagExternal').style.display = "";
			document.getElementById('ownerExternal').checked = true;
		}
	}
	
function deleteList(id){
		document.getElementById('paraFrm_rowId').value = id;
		
		 var conf=confirm("Do you really want to Remove this record ?");
  			if(conf) 
  			{
  				document.getElementById("paraFrm").action = 'InductionSchedule_deleteActivity.action'; 
	  			document.getElementById("paraFrm").submit();
	  			document.getElementById("paraFrm").target = "main";
  			}
	  		else
	  		{
	  			 return false;
	  		}
	}
	
/*
* Modified by manish 
* Add alert  showing "Record Removed Successfully."
*/
	
function deleteEmployee()
	{
		 if(chkSkill()){
		 var con=confirm('Do you really want to Remove this record ?');
		 if(con){
		    document.getElementById('paraFrm').action="InductionSchedule_deleteEmployee.action";
	  		document.getElementById('paraFrm').submit();
		    } else{
		     return true;
		    }
		 }else {
		 	alert('Please Select Atleast one Record To Remove');
		 	 return false;
		 }
	 
	}
	
function chkSkill(){
	var tbl = document.getElementById('tblEmployeeList');
		var rowLen = tbl.rows.length;
		  for(var a=1;a<rowLen;a++){	
		   if(document.getElementById('chkEmpList'+a).checked == true)
			   {	
		 	    return true;
		        }	   
		  }
		  return false;
	}
	
function callChkAll()  {
	 
		var tbl = document.getElementById('tblEmployeeList');
		var rowLen = tbl.rows.length;
		if(document.getElementById('chkAll').checked){
		
			 for(i = 1; i < rowLen; i++){
			 
			 	  document.getElementById('chkEmpList'+i).checked = true;
				  document.getElementById(i).value="Y";
			  }
	    }else{
	    
	     for(i = 1; i < rowLen; i++){
			   document.getElementById('chkEmpList'+i).checked =false;
			   document.getElementById(i).value="";
		 }
	  }	
	
  }
  
function saveFun(){   
   
   var indname=document.getElementById('paraFrm_inductionName').value;
  		if(trim(indname)==""){
  			//alert('Please enter induction name');
  			alert("Please enter "+document.getElementById('indname').innerHTML.toLowerCase());
  			document.getElementById('paraFrm_inductionName').value="";
  			return false;
  		}
  		if(document.getElementById('paraFrm_cntPersonId').value==""){
  			//alert('Please select contact person');
  			alert("Please select "+document.getElementById('cntperson').innerHTML.toLowerCase());
  			
  			return false;
  		}
  		if(document.getElementById('paraFrm_inductionFrom').value==""){
  			//alert('Please enter Induction From date');
  			alert("Please enter/select "+document.getElementById('indfrom').innerHTML.toLowerCase());
  			
  			return false;
  		}
  		
  		//if(!validateDate("paraFrm_inductionFrom", "Induction From")) return false;
  		
  		//indfrom  indto indfrom actdtl actstattime actendtime
  		
  			if(!validateDate("paraFrm_inductionFrom",'indfrom')) return false;
  		
  		if(document.getElementById('paraFrm_inductionTo').value==""){
  			//alert('Please enter Induction to date');
  			alert("Please enter/select  "+document.getElementById('indto').innerHTML.toLowerCase());
  			
  			return false;
  		}
  		
  		//if(!validateDate("paraFrm_inductionTo", "Induction To")) return false;
  		
  			if(!validateDate("paraFrm_inductionTo",'indto')) return false;
  		
  		
  		var frmDt = document.getElementById('paraFrm_inductionFrom').value;
  		var toDt = document.getElementById('paraFrm_inductionTo').value;
  		
  	//	if(!dateDifferenceEqual(frmDt, toDt, "paraFrm_inductionTo", "Induction From", "Induction To"))return false;
  		
  		if(!dateDifferenceEqual(frmDt, toDt, "paraFrm_inductionTo",'indfrom','indto'))return false;
  		
  		
 /*
 * Added by manish sakpal 
 * characters in the induction description and induction venue should not greater than 500. 
 */ 		
  	
   		
  		var indvenue=document.getElementById('paraFrm_inducVenue').value;
  		if(trim(indvenue)=="")
  		{
  			//alert('Please enter Induction venue');
  			alert("Please enter "+document.getElementById('indvenue').innerHTML.toLowerCase());
  			document.getElementById('paraFrm_inducVenue').value="";  			
  			return false;
  		}		
  		  
  		var venue = document.getElementById('paraFrm_inducVenue').value;
  		var description = document.getElementById('paraFrm_inducDesc').value;
  		
  		if(description.length >500){
 			//alert("Maximum length of 'Description' is 500 characters.");
 			alert("Maximum length of 'Description' is 500 characters.");
			return false;
  		 } 
  		if(venue.length >500)
  		{
 		//	alert("Maximum length of 'Venue' is 500 characters.");
 			alert("Maximum length of "+document.getElementById('indvenue').innerHTML.toLowerCase()+" is 500 characters.");
			return false;
  		 }    
  		
  		var actvityListLength= '<%=a%>';  		
  		if(actvityListLength==1)
  		{
  			alert("Please add Activity Details");
  			return false;
  		}
  		  
  		
  		var tbl = document.getElementById('tblEmployeeList');
		var rowLen = tbl.rows.length;
		
		if(rowLen==1){
			alert('Please select employees for induction');
			return false;
		}
  
		document.getElementById("paraFrm").action="InductionSchedule_save.action";
	    document.getElementById("paraFrm").submit();
	}
	
function callSameAsAbove(){
		if(document.getElementById('chkVenue').checked==true){
			document.getElementById('paraFrm_actVenue').value = document.getElementById('paraFrm_inducVenue').value;
		}
		else{
			document.getElementById('paraFrm_actVenue').value = "";
		}
	}
	
function cancelFun(){
		if(document.getElementById('paraFrm_inductionCode').value==""){
			document.getElementById("paraFrm").action="InductionSchedule_getInducDueList.action";
	    	document.getElementById("paraFrm").submit();
		}
		else{
			document.getElementById("paraFrm").action="InductionSchedule_viewFromSearch.action";
	    	document.getElementById("paraFrm").submit();
		}
		
	}
	

function allCharacters(e)
{
		
		document.onkeypress = allCharacters;
	var key //= (window.event) ? event.keyCode : e.which;
	if (window.event)
	    key = event.keyCode
	else
	   	key = e.which
	   //	alert(key);
	clear();
	// Was key that was pressed a numeric character (0-9) or backspace (8)?
	if (key == 39 || key == 34 || key == 92)
	 	return false; // if so, do nothing
	else // otherwise, discard character
		return true;
	if (window.event) //IE
	    window.event.returnValue = null;     else //Firefox
	    e.preventDefault();
}

function clear()
{
	document.onkeypress = "";
}	

</script>