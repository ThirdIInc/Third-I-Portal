<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript">
	var records = new Array();
</script>
<style type="text/css">
  #uploadFlag 
  { 
  display:none; 
  }
</style>
<s:form action="WeekPlanner" method="post" name="TaskProject"
	validate="true" id="paraFrm" theme="simple">
<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<s:hidden name="dataPath" />
		<s:hidden name="searchStatus" /> 
		<s:hidden name="searchEmpCode" />
		<s:hidden name="toDate" />
		<s:hidden name="searchDate" />
		<s:hidden name="searchTaskTitle" />
		<s:hidden name="searchProject" />
		<s:hidden name="endSearchDateStart" />
		<s:hidden name="endSearchDateEnd" />
		<s:hidden name="closedTask" /> 
		<s:hidden name="myPage" />
		<tr>
			<td valign="bottom" class="txt">
			<table  width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg" >
			<tr>
			<td style="padding: 3px;"><strong class="text_head"><img
						src="../pages/mypage/images/icons/task24.png"  
						  /></strong></td>
			<td width="93%" class="txt"><strong class="text_head">My Task
			  </strong></td>
			<td width="3%" valign="top" class="txt">
			<div align="right"><img
				src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
				</td>
				</tr>
				</table>
			</td>
		</tr>
		
	 	<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>

					
					<td width="80%">
					<s:if test="closedFlag">
				
									</s:if>
									<s:else>
										<s:submit cssClass="token" value="Save"
									action="WeekPlanner_addTask" onclick="return addValidation();" />
									&nbsp;&nbsp;
									</s:else>
									<input type="button" name="back" class="back" value="Back" onclick="return backFun();"/>
									 </td>
					<td width="20%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		 <tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
				<tr>
					<td>
					<table width="100%" colspan="4" border="0" cellpadding="0"
						cellspacing="0">
						<tr>
							
							<!--<td nowrap="nowrap"><s:hidden name="hiddentaskId"
								id="hiddentaskId" /><s:select list=" #{'S':'Self','O':'Other'}"
								name="plannerBean.taskType" onchange="taskType();" /></td>

							-->
							
							
							<s:hidden name="hiddentaskId"
								id="hiddentaskId" />
								
								
								
								<td height="22" class="formtext" nowrap="nowrap">Employee
									:<font color="red">*</font></td>
									<td nowrap="nowrap">
									<s:if test="editFlag">
										<s:textfield size="23"
												name="plannerBean.empName" readonly="true" />
									</s:if>
									<s:else>
										<s:textfield size="23"
												name="plannerBean.empName" readonly="true" /><img
												align="absmiddle" style="cursor: pointer;"
												src="../pages/common/css/default/images/search2.gif"
												width="16" class="iconImage" height="15"
												onclick="javascript:callsF9(500,325,'WeekPlanner_f9actionAssignTo.action');" />
									</s:else>
									
									<s:hidden name="plannerBean.empToken" /> <s:hidden
										name="plannerBean.empCode" /></td>
								
								
								<td colspan="4">
							

							<table width="100%" colspan="4" border="0" cellpadding="0"
								cellspacing="0">
								<tr>
									
								</tr>
								<tr>
									<td colspan="2" class="formtext"><img
										src="../pages/common/css/default/images/space.gif" width="5"
										height="7" /></td>
								</tr>
							</table>
							
							</td>
						</tr>



						<tr>
							<td class="formtext">Task Project :<font color="red">*</font></td>
							<td nowrap="nowrap">
							
							<s:if test="editFlag">
								<s:select disabled="true" name="plannerBean.project"
								cssStyle="width:175" headerKey=""
								onclick="callOtherTaskProject(this.value)"
								headerValue="--- Select Task Project ---" size="1" list="tmap1" />
								</s:if>
								<s:else>
								<s:select name="plannerBean.project"
								cssStyle="width:175" headerKey=""
								onclick="callOtherTaskProject(this.value)"
								headerValue="--- Select Task Project ---" size="1" list="tmap1" />
								</s:else>
							
							
								
								</td>

							<td height="22" class="formtext">Task Type :<font
								color="red">*</font></td>
							<td>
							<s:if test="editFlag">
								<s:select name="plannerBean.type" disabled="true" cssStyle="width:175"
								headerKey="" headerValue="--- Select Task Type ---" size="1"
								onclick="callOtherTaskType(this.value)" list="tmap" />
								</s:if>
								<s:else>
								<s:select name="plannerBean.type" cssStyle="width:175"
								headerKey="" headerValue="--- Select Task Type ---" size="1"
								onclick="callOtherTaskType(this.value)" list="tmap" />
								</s:else>
							
							
								
								</td>
						</tr>

						<tr style="display: none" id="otherTaskProjectId">
							<td id="taskProjectId" style="display: none">Other Project
							description<font color="red">*</font></td>
							<td id="taskProjectId1" style="display: none">
							<s:if test="editFlag">
								<s:textfield
								size="25" maxlength="50" disabled="true" name="plannerBean.otherTaskProject" />
								</s:if>
								<s:else>
								<s:textfield
								size="25" maxlength="50" name="plannerBean.otherTaskProject" />
								</s:else>
							
							</td>
							<td id="blankId" style="display: none">&nbsp;</td>
							<td id="blankId1" style="display: none">&nbsp;</td>
							<td id="taskTypeId" style="display: none">Other Type
							description<font color="red">*</font></td>
							<td id="taskTypeId1" style="display: none">
							<s:if test="editFlag">
								<s:textfield
								size="25" maxlength="50" disabled="true" name="plannerBean.otherTaskType" />
								</s:if>
								<s:else>
								<s:textfield
								size="25" maxlength="50" name="plannerBean.otherTaskType" />
								</s:else>
							
							</td>

						</tr>


						<tr>
							<td class="formtext">Task Title :<font color="red">*</font></td>
							<td nowrap="nowrap">
							<s:if test="editFlag">
								<s:textfield size="50" disabled="true"
								name="plannerBean.taskTitleNew" maxlength="45" />
								</s:if>
								<s:else>
								<s:textfield size="50"
								name="plannerBean.taskTitleNew" maxlength="45"/>
								</s:else>
							
							</td>
							<td height="22">&nbsp;</td>
							<td height="22" class="formtext">hrs
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; mns</td>
						</tr>

						<tr>
							<td class="formtext">Start Date :<font color="red">&nbsp;</font></td>
							<td nowrap="nowrap">
							<s:if test="editFlag">
								<s:textfield
								name="plannerBean.newStartDate" size="25" disabled="true"/>
							
								</s:if>
								<s:else>
								<s:textfield
								name="plannerBean.newStartDate" size="25"
								onchange="return validateDate('paraFrm_plannerBean_newStartDate','Start Date');" />
									<s:a
										href="javascript:NewCal('paraFrm_plannerBean_newStartDate','DDMMYYYY');">
		
										<img src="../pages/images/recruitment/Date.gif"
											class="iconImage" height="16" align="absmiddle" width="16">
									</s:a>
								</s:else>
							
							
							
							</td>

							<td height="22" class="formtext">Start Time :<font
								color="red">&nbsp;</font></td>
							<td>
							
							<s:if test="editFlag">
								<s:select disabled="true"
								list=" #{'00':'00','01':'01','02':'02','03':'03','04':'04','05':'05','06':'06','07':'07','08':'08','09':'09','10':'10','11':'11','12':'12','13':'13','14':'14','15':'15','16':'16','17':'17','18':'18','19':'19','20':'20','21':'21','22':'22','23':'23'}"
								name="plannerBean.startTimeHr" /> : <s:select disabled="true"
								list=" #{'00':'00','01':'01','02':'02','03':'03','04':'04','05':'05','06':'06','07':'07','08':'08','09':'09','10':'10','11':'11','12':'12','13':'13','14':'14','15':'15','16':'16','17':'17','18':'18','19':'19','20':'20','21':'21','22':'22','23':'23','24':'24','25':'25','26':'26','27':'27','28':'28','29':'29','30':'30','31':'31','32':'32','33':'33','34':'34','35':'35','36':'36','37':'37','38':'38','39':'39','40':'40','41':'41','42':'42','43':'43','44':'44','45':'45','46':'46','47':'47','48':'48','49':'49','50':'50','51':'51','52':'52','53':'53','54':'54','55':'55','56':'56','57':'57','58':'58','59':'59'}"
								name="plannerBean.StartTimeMin" />
							
								</s:if>
								<s:else>
								<s:select
								list=" #{'00':'00','01':'01','02':'02','03':'03','04':'04','05':'05','06':'06','07':'07','08':'08','09':'09','10':'10','11':'11','12':'12','13':'13','14':'14','15':'15','16':'16','17':'17','18':'18','19':'19','20':'20','21':'21','22':'22','23':'23'}"
								name="plannerBean.startTimeHr" /> : <s:select
								list=" #{'00':'00','01':'01','02':'02','03':'03','04':'04','05':'05','06':'06','07':'07','08':'08','09':'09','10':'10','11':'11','12':'12','13':'13','14':'14','15':'15','16':'16','17':'17','18':'18','19':'19','20':'20','21':'21','22':'22','23':'23','24':'24','25':'25','26':'26','27':'27','28':'28','29':'29','30':'30','31':'31','32':'32','33':'33','34':'34','35':'35','36':'36','37':'37','38':'38','39':'39','40':'40','41':'41','42':'42','43':'43','44':'44','45':'45','46':'46','47':'47','48':'48','49':'49','50':'50','51':'51','52':'52','53':'53','54':'54','55':'55','56':'56','57':'57','58':'58','59':'59'}"
								name="plannerBean.StartTimeMin" />
								</s:else>
							
							
								
								</td>

						</tr>


						<tr>
							<td class="formtext" nowrap="nowrap">End Date :<font
								color="red">&nbsp;</font></td>
							<td nowrap="nowrap">
							<s:if test="editFlag">
								<s:textfield
								name="plannerBean.newEndDate" size="25" disabled="true"/>
							
								</s:if>
								<s:else>
								<s:textfield
								name="plannerBean.newEndDate" size="25"
								onchange="return validateDate('paraFrm_plannerBean_newEndDate','End Date');" />
									<s:a
										href="javascript:NewCal('paraFrm_plannerBean_newEndDate','DDMMYYYY');">
		
										<img src="../pages/images/recruitment/Date.gif"
											class="iconImage" height="16" align="absmiddle" width="16">
									</s:a>
								</s:else>
							
							
							</td>

							<td height="22" class="formtext" nowrap="nowrap">End Time :<font
								color="red">&nbsp;</font></td>
							<td>
								<s:if test="editFlag">
								<s:select disabled="true"
									list=" #{'00':'00','01':'01','02':'02','03':'03','04':'04','05':'05','06':'06','07':'07','08':'08','09':'09','10':'10','11':'11','12':'12','13':'13','14':'14','15':'15','16':'16','17':'17','18':'18','19':'19','20':'20','21':'21','22':'22','23':'23'}"
									name="plannerBean.endTimeHr" /> : <s:select disabled="true"
									list=" #{'00':'00','01':'01','02':'02','03':'03','04':'04','05':'05','06':'06','07':'07','08':'08','09':'09','10':'10','11':'11','12':'12','13':'13','14':'14','15':'15','16':'16','17':'17','18':'18','19':'19','20':'20','21':'21','22':'22','23':'23','24':'24','25':'25','26':'26','27':'27','28':'28','29':'29','30':'30','31':'31','32':'32','33':'33','34':'34','35':'35','36':'36','37':'37','38':'38','39':'39','40':'40','41':'41','42':'42','43':'43','44':'44','45':'45','46':'46','47':'47','48':'48','49':'49','50':'50','51':'51','52':'52','53':'53','54':'54','55':'55','56':'56','57':'57','58':'58','59':'59'}"
									name="plannerBean.endTimeMin" />
							
								</s:if>
								<s:else>
								<s:select
									list=" #{'00':'00','01':'01','02':'02','03':'03','04':'04','05':'05','06':'06','07':'07','08':'08','09':'09','10':'10','11':'11','12':'12','13':'13','14':'14','15':'15','16':'16','17':'17','18':'18','19':'19','20':'20','21':'21','22':'22','23':'23'}"
									name="plannerBean.endTimeHr" /> : <s:select
									list=" #{'00':'00','01':'01','02':'02','03':'03','04':'04','05':'05','06':'06','07':'07','08':'08','09':'09','10':'10','11':'11','12':'12','13':'13','14':'14','15':'15','16':'16','17':'17','18':'18','19':'19','20':'20','21':'21','22':'22','23':'23','24':'24','25':'25','26':'26','27':'27','28':'28','29':'29','30':'30','31':'31','32':'32','33':'33','34':'34','35':'35','36':'36','37':'37','38':'38','39':'39','40':'40','41':'41','42':'42','43':'43','44':'44','45':'45','46':'46','47':'47','48':'48','49':'49','50':'50','51':'51','52':'52','53':'53','54':'54','55':'55','56':'56','57':'57','58':'58','59':'59'}"
									name="plannerBean.endTimeMin" />
								</s:else>
								
							</td>

						</tr>
						
						<tr>
							

							<td height="22" class="formtext">Priority : &nbsp;</td>
							<td>
							<s:if test="editFlag">
								<s:select  disabled="true" list=" #{'H':'High','M':'Medium','L':'Low'}"
								name="priority" />
							
								</s:if>
								<s:else>
								<s:select  list=" #{'H':'High','M':'Medium','L':'Low'}"
								name="priority" />
								</s:else>
							
							
							</td>
							
							<td class="formtext">&nbsp;</td>
							<td nowrap="nowrap">
							&nbsp;
							
							</td>
						</tr>
						

						<tr>
							<td class="formtext">Task Description :</td>
							<td nowrap="nowrap">
							<s:if test="editFlag">
								<s:textarea disabled="true" rows="6" cols="60"
								name="plannerBean.taskDesc" />
							
								</s:if>
								<s:else>
								<s:textarea rows="6" cols="60"
								name="plannerBean.taskDesc" onkeyup="return callLength1('plannerBean.taskDesc','hobCnt',900)" />
								<label><font size="0.5">&nbsp;&nbsp;Remaining
												Chars:</font></label><s:textfield name="hobCnt" readonly="true" size="1"></s:textfield>
								</s:else>
							
							</td>

							<td height="22" class="formtext">Status : &nbsp;</td>
							<td>
							<s:if test="editFlag">
								<s:select  list=" #{'O':'Open','C':'Close'}"
								name="plannerBean.status" disabled="true"/>
							
								</s:if>
								<s:else>
								<s:select list=" #{'O':'Open','C':'Close'}"
								name="plannerBean.status" />
								</s:else>
							
							
							</td>
						</tr>
						
						<tr>
								<td colspan="4">
								<table width="100%" border="0" cellpadding="0" cellspacing="2"
									class="formbg">
									<%
										int g = 0;		int filenCount = 0;							
									%>
									<tr>
										
										
							<td colspan="4" width="100%"><input type="button" class="add"
										value="Attach Document" onclick="addRowUploadBlock('<%=g%>','<%=filenCount%>');" />
										</td>
										
						</tr>
						
						
									
									<tr>
										<!--<td><label class="set" name="upload.document"
											id="upload.document" ondblclick="callShowDiv(this);"><%=label.get("upload.document")%></label>:</td>
										<td><s:textfield name="uploadLocFileName" readonly="true"
											size="40" />
										<input name="Upload" type="button"
											class="token" value="Browse"
											onclick="uploadFile('uploadLocFileName');" />&nbsp; <input
											type="button" name="Add Proof" value="Upload" class="token"
											onclick="return callUpload();" /></td>
											
											--><td align="right">
									</td>
									
									
									</tr>
									<tr>
									<td width="100%" colspan="4">
									<div id="uploadFlag">
									
							
									<table class="formbg" width="100%"
										id="uploadTable<%=g %>" >
										<tr>
										
											<td width="30%" colspan="4" class="formth"><label
												class="set" id="upload.document" name="upload.document"
												ondblclick="callShowDiv(this);">Document Name</label></td>
<td  width="30%"></td><td  width="30%"></td>
										</tr>
										<%
							int cnt = 0;
								int cnt1 = 0;
						%>
										<s:iterator value="attachMultDocList">
										<s:hidden name="docFlag"></s:hidden>
									<tr>					
							<td class="sortableTD" align="center"><input
													type="text" name="uploadLocFileName"
													id="paraFrm_uploadLocFileName<%=g %>"
													value='<s:property value="uploadLocFileName"/>' size="50" /></td>

												<td class="sortableTD" align="center" nowrap="nowrap"><input
													type="button" name="uploadLoc" value="Upload" class="token"
													onclick="uploadTicketFile('paraFrm_uploadLocFileName<%=g %>');" />
												</td>


												<td class="sortableTD" align="center" nowrap="nowrap"><input
													type="button" name="show" value="Show" class="token"
													onclick="showRecord('paraFrm_uploadLocFileName<%=g %>');" /></td>
													
											<s:if test="docFlag">
												<td align="center" class="sortableTD"></td>
											</s:if>
											<s:else>
												<td align="center" class="sortableTD"><img
													src="../pages/common/css/icons/delete.gif"
													onclick="deleteCurrentRow(this);" /></td>
											
											</s:else>
													
							</tr>
							<%
								cnt1 = cnt;
							%>
							
							<%
							++g;
							%>
							</s:iterator>

									</table>
									
									</div>
									</td>
								</tr>
								</table>
								</td>
							</tr>

						<s:hidden name="isEdit" id="isEdit" />
						<s:hidden name="editFlag" />
						<tr>
							<td colspan="3">
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>


									<td width="80%">
									<s:if test="closedFlag">
					
									</s:if>
									<s:else>
										<s:submit cssClass="token" value="Save"
									action="WeekPlanner_addTask" onclick="return addValidation();" />
									&nbsp;&nbsp;
									</s:else><input
										type="button" name="back" class="back" value="Back"
										onclick="return backFun();" /></td>
									<td width="20%"></td>
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

		

		<s:if test="forwardFlag">
			<tr>
			<td>&nbsp;
			</td></tr>
		
		
			<tr>
			
			<td><strong>Forward Task:</strong>
			<s:if test="closedFlag">
			<s:checkbox name="forward" id="forward" onclick="displayTag('forward');" disabled="true"/>
			</s:if>
			<s:else>
			<s:checkbox name="forward" id="forward" onclick="displayTag('forward');" />
			</s:else>
			</td>
			
			<td><strong>Update Status:</strong>
			<s:if test="closedFlag">
			<s:checkbox name="statusUpdate" id="status" onclick="displayTag('status');" disabled="true"/>
			</s:if>
			<s:else>
			<s:checkbox name="statusUpdate" id="status" onclick="displayTag('status');" />
			</s:else>
			</td>
			
			</tr>
			
			<tr>
			<td>&nbsp;
			</td></tr>
				<tr id="forwardTask" style="display: none;">
					<td colspan="3">
						<table class="formbg" width="100%" cellspacing="0" cellspacing="1" border="0">		
					<tr>
							<td colspan="4"  class="txt"><strong class="text_head">Forward Task 
							  </strong></td>		
							
					</tr>
					<tr>
						<td width="20%">Forward To :<font color="red">*</font> </td>
						<s:hidden name="fwempToken" /> <s:hidden
										name="fwempCode" />
						<td width="30%"><s:textfield size="23"
												name="fwempName" readonly="true" /><img
												align="absmiddle" style="cursor: pointer;"
												src="../pages/common/css/default/images/search2.gif"
												width="16" class="iconImage" height="15"
												onclick="javascript:callsF9(500,325,'WeekPlanner_f9actionForwardTo.action');" />
						</td>
						<td width="20%">Forward Status :<font color="red">*</font></td>
						<td width="30%">
						<s:select name="plannerBean.fwStatus"
								cssStyle="width:175" headerKey=""								
								headerValue="--- Select Forward Status ---" size="1" list="tmap2" />
						</td>
						
						<!--<s:select  list=" #{'1':'Open','2':'Close'}"
								name="plannerBean.fwStatus" /></td>
					--></tr>
					<tr>
						<td width="20%">Hours worked :<font color="red">*</font> </td>
					
						<td width="30%"><s:textfield name="forwardHrs" onkeypress="return numbersOnly(event);" maxlength="3" size="3" 
												 />
						</td>
						<td width="20%">Task Completed :<font color="red">*</font></td>
						<td width="30%">
						<s:textfield onkeypress="return numbersOnly(event);" maxlength="3" size="3" name="forwardPercentage" onkeyup="this.value = minmax(this.value, 0, 100)"></s:textfield>
						<b>%</b>
						</td>
						
						<!--<s:select  list=" #{'1':'Open','2':'Close'}"
								name="plannerBean.fwStatus" /></td>
					--></tr>
					<tr>
						<td width="20%">Comment :<font color="red">*</font></td>

						<td width="30%"><s:textarea rows="5" cols="50"
							name="plannerBean.fwComment"
							onkeyup="return callLength2('plannerBean.fwComment','cmtCnt',400)" /><label><font
							size="0.5">&nbsp;&nbsp;Remaining Chars:</font></label><s:textfield
							name="cmtCnt" readonly="true" size="1"></s:textfield></td>
						<td width="20%"></td>
						<td width="30%"><input type="text" name="uploadFWFileName"
													id="paraFrm_uploadFWFileName"
													value='' size="20" />
													<input
													type="button" name="uploadLoc" value="Upload" class="token"
													onclick="uploadTicketFile('paraFrm_uploadFWFileName');" />
													</td>
					</tr>
					
					<tr>
					<td><s:submit cssClass="token" value=" Forward "
									action="WeekPlanner_forwardTask" onclick="return validateForward();" /></td>
					</tr>
					</table>
				</td>
			</tr>			
			
			<tr id="taskStatus" style="display: none;">
			<td colspan="3">
						<table class="formbg" width="100%" cellspacing="0" cellspacing="1" border="0">		
					<tr>
							<td colspan="4"  class="txt"><strong class="text_head">Update Status
							  </strong></td>		
							
					</tr>
					<tr>
						<td width="20%">Hours worked :<font color="red">*</font></td>
					
						<td width="30%"><s:textfield name="statusHrs" onkeypress="return numbersOnly(event);" maxlength="3" size="3" 
												 />
						</td>
						<td width="20%">Task Completed :<font color="red">*</font></td>
						<td width="30%">
						<s:textfield onkeypress="return numbersOnly(event);" maxlength="3" size="3" name="statusPercentage" onkeyup="this.value = minmax(this.value, 0, 100)"></s:textfield>
						<b>%</b>
						</td>
						
						<!--<s:select  list=" #{'1':'Open','2':'Close'}"
								name="plannerBean.fwStatus" /></td>
					--></tr>
					<tr>
						<td width="20%">Comment :<font color="red">*</font></td>
						
						<td width="30%"><s:textarea rows="5" cols="50"
								name="plannerBean.statusComment" onkeyup="return callLength3('plannerBean.statusComment','lastCnt',400)" />
						<label><font size="0.5">&nbsp;&nbsp;Remaining
												Chars:</font></label><s:textfield name="lastCnt" readonly="true" size="1"></s:textfield>
						</td>
						<td width="20%"></td>
						<td width="30%">
													</td>
					</tr>
					<tr>
					<td><s:submit cssClass="token" value=" Save "
								action="WeekPlanner_taskStatus"	 onclick="return validateStatus();" /></td>
					</tr>
					</table>
				</td>
			
			
			</tr>
			
			<tr>
					<td colspan="6">
						<table class="formbg" width="100%" cellspacing="0" cellspacing="1" border="0">		
					<tr>
							<td colspan="6"  class="txt"><strong class="text_head">Forwarded Task History
							  </strong></td>		
							
					</tr>
					<tr>
						<td >
							<table width="100%" border="0" cellpadding="1" cellspacing="1"   class="sortable">
							<tr>
								<td class="formth" width="5%" height="22" valign="top"><label name="reporting.srno" class = "set"  id="reporting.srno" ondblclick="callShowDiv(this);">Sr No</label></td>								
								<td class="formth" width="22%" height="22" valign="top"><label name="reporting.appr.name" class = "set"  id="reporting.appr.name" ondblclick="callShowDiv(this);">Employee Name</label></td>
								<td class="formth" width="21%" height="22" valign="top"><label name="reporting.appr.id" class = "set"  id="reporting.appr.id" ondblclick="callShowDiv(this);">Forwarded Date</label></td>
								<td class="formth" width="16%" height="22" valign="top"><label name="reporting.srno" class = "set"  id="reporting.srno" ondblclick="callShowDiv(this);">Comment</label></td>
								<td class="formth" width="16%" height="22" valign="top"><label name="reporting.srno" class = "set"  id="reporting.srno" ondblclick="callShowDiv(this);">Status</label></td>
								<td class="formth" width="16%" height="22" valign="top"><label name="reporting.srno" class = "set"  id="reporting.srno" ondblclick="callShowDiv(this);">Attach Document</label></td>
							</tr>
							<%							
							int count = 0;
							int k=1;
						%>
						<s:iterator value="fwHisList">
						<tr>
							<td width="5%" class="sortableTD"><%=++count %></td>							
							<td width="22%" class="sortableTD"><s:hidden name="fwHisName"/><s:property value="fwHisName"/></td>
							<td width="21%" class="sortableTD"><s:hidden name="fwHisdate"/><s:property value="fwHisdate"/></td>
							<td width="16%" class="sortableTD"><s:hidden name="fwHisComment"/><s:property value="fwHisComment"/></td>
							<td width="16%" class="sortableTD"><s:hidden name="fwHisStatus"/><s:property value="fwHisStatus"/></td>
							<td width="16%" class="sortableTD"><s:hidden name="fwHisAttchFile"/><s:property value="fwHisAttchFile" /></td>
						</tr>
						<%k++; %>
						</s:iterator>
							</table>
						</td>
					</tr>
					
				</table>
				</td>
			</tr>
			
			
			<tr>
			<td colspan="6">
			<table width="100%" border="0">
			
		
					<tr>
						<td colspan="6" class="txt"><strong class="text_head">Task
						Status </strong></td>
					</tr>
					
					
					<tr>
						<td  width="100%">
						<table width="100%" border="0" cellpadding="1" cellspacing="1"
							class="sortable">
							<tr>
							<td class="formth" width="5%" height="22" valign="top"><label
									 class="set" id="status.employee"
									ondblclick="callShowDiv(this);">Sr No</label></td>
							<td class="formth" width="22%" height="22" valign="top"><label
									 class="set" id="status.employee"
									ondblclick="callShowDiv(this);">Employee Name</label></td>
								<td class="formth" width="21%" height="22" valign="top"><label
									 class="set" id="status.dateTime"
									ondblclick="callShowDiv(this);">Forwarded Date</label></td>
								<td class="formth" width="16%" height="22" valign="top"><label
									 class="set" id="status.comment"
									ondblclick="callShowDiv(this);">Comments</label></td>
								<td class="formth" width="16%" height="22" valign="top"><label
									 class="set" id="status.hrs"
									ondblclick="callShowDiv(this);">Hrs</label></td>
								<td class="formth" width="16%" height="22" valign="top"><label
									 class="set" id="status.percent"
									ondblclick="callShowDiv(this);">Percentage</label></td>

							</tr>
							
							
							<%							
							int count1 = 0;
							int m=1;
						%>
						<s:iterator value="taskStatusList">
						<tr>
							<td width="5%" class="sortableTD"><%=++count1 %></td>							
							<td width="22%" class="sortableTD"><s:hidden name="statusEmpName"/><s:property value="statusEmpName"/></td>
							<td width="21%" class="sortableTD"><s:hidden name="statusDate"/><s:property value="statusDate"/></td>
							<td width="16%" class="sortableTD"><s:hidden name="statusTaskComment"/><s:property value="statusTaskComment"/></td>
							<td width="16%" class="sortableTD" align="center"><s:hidden name="statusTaskHrs"/><s:property value="statusTaskHrs"/></td>
							<td width="16%" class="sortableTD" align="center"><s:hidden name="statusTaskPercentage"/><s:property value="statusTaskPercentage"/></td>
						</tr>
						<%m++; %>
						</s:iterator>
						</table>
						</td>
					</tr>

				</table>
				</td>
				</tr>
		</s:if>
		
			
			
			
					</table>
		</s:form>
<script type="text/javascript">
onLoad();


function cantDelete(){

alert("You are not authorized to delete this.");
}

function displayTag(id)
{

	if (id=='forward')
	{
	document.getElementById('forwardTask').style.display='';
	document.getElementById('forward').value='Y';
	document.getElementById('status').value='N';
	document.getElementById('status').checked=false;
	document.getElementById('taskStatus').style.display='none';
	}
	
	
	if (id=='status')
	{
	document.getElementById('taskStatus').style.display='';
	document.getElementById('status').value='Y';
	document.getElementById('forward').value='N';
	document.getElementById('forward').checked=false;
	document.getElementById('forwardTask').style.display='none';
	
	}
	
	if(document.getElementById('status').checked==false && document.getElementById('forward').checked==false){
	document.getElementById('taskStatus').style.display='none';
	document.getElementById('forwardTask').style.display='none';
	}

}

function onLoad()
{
	
	document.getElementById('forward').value ='N';
	document.getElementById('status').value ='N';
	var table = document.getElementById('uploadTable'+idValue); 
	
		var rowCount = table.rows.length; 
		var iteration = rowCount-1;		
		if(!iteration>0)
		{
			addRowUploadBlock('uploadTable'+idValue);		
		}
}

function minmax(value, min, max) 
{
    if(parseInt(value) < 0 || isNaN(value)) 
        return 0; 
    else if(parseInt(value) > 100) 
        return 100; 
    else return value;
}

function validateForward()
{
	

	if(document.getElementById('paraFrm_fwempCode').value=="")
	{
	alert('Please select forward to employee');
	return false;
	}
	if(document.getElementById('paraFrm_plannerBean_fwStatus').value=="")
	{
	alert('Please select forward status');
	return false;
	}
	
	if(document.getElementById('paraFrm_forwardHrs').value=="")
	{
	alert('Please enter Hours worked ');
	return false;
	}
	if(document.getElementById('paraFrm_forwardPercentage').value=="")
	{
	alert('Please enter Percentage of task completed');
	return false;
	}
	if(document.getElementById('paraFrm_plannerBean_fwComment').value=="")
	{
	alert('Please enter comment');
	return false;
	}
	
	var fwdDesc = document.getElementById('paraFrm_plannerBean_fwComment').value;

	if(fwdDesc!=null || fwdDesc!=""){
		    if(fwdDesc.length>400){
		    alert("Maximum 400 characters are allowed for Comments");
			 document.getElementById('paraFrm_plannerBean_fwComment').focus();
			 return false;
		    }
		    }
		
}

function validateStatus()
{
	

	if(document.getElementById('paraFrm_statusHrs').value=="")
	{
	alert('Please enter Hours worked');
	return false;
	}
	if(document.getElementById('paraFrm_statusPercentage').value=="")
	{
	alert('Please enter Percentage of task completed');
	return false;
	}
	if(document.getElementById('paraFrm_plannerBean_statusComment').value=="")
	{
	alert('Please enter comment');
	return false;
	}
	
	var statusDesc = document.getElementById('paraFrm_plannerBean_statusComment').value;

	if(statusDesc!=null || statusDesc!=""){
		    if(statusDesc.length>400){
		    alert("Maximum 400 characters are allowed for Comments");
			 document.getElementById('paraFrm_plannerBean_statusComment').focus();
			 return false;
		    }
		    }
		
}



function numbersOnly(e){
	document.onkeypress = numbersOnly;
	var key //= (window.event) ? event.keyCode : e.which;
	if (window.event)
	    key = event.keyCode
	else
	   	key = e.which
	clear();
	// Was key that was pressed a numeric character (0-9) or backspace (8)?
	if ( key > 47 && key < 58 || key == 8 || key == 0)
	 	return true; // if so, do nothing
	else // otherwise, discard character
		return false;
	if (window.event) //IE
	    window.event.returnValue = null;     else //Firefox
	    e.preventDefault();
}

function backFun()
{
		
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action = 'WeekPlanner_backTask.action';
			document.getElementById('paraFrm').submit();
	
}
function addValidation()
{

if(document.getElementById('paraFrm_plannerBean_empCode').value=="")
{
alert('Please Select Employee');
return false;
}


	
	var taskProject= document.getElementById('paraFrm_plannerBean_project').value ;
	
	
		if(taskProject=="")
		{
			alert("Please select task project.");
			return false;
		}
		
		
	var taskType= document.getElementById('paraFrm_plannerBean_type').value ;
	
	
		if(taskType=="")
		{
			alert("Please select task type.");
			return false;
		}

if(trim(document.getElementById('paraFrm_plannerBean_taskTitleNew').value)=="")
{alert('Please Enter Task Title');
return false
}

var taskDesc = document.getElementById('paraFrm_plannerBean_taskDesc').value;

if(taskDesc!=null || taskDesc!=""){
		    if(taskDesc.length>900){
		    alert("Maximum 900 characters are allowed for Task Description");
			 document.getElementById('paraFrm_plannerBean_taskDesc').focus();
			 return false;
		    }
		    }



/*if(document.getElementById('paraFrm_plannerBean_newStartDate').value=="")
{alert('Please Enter Start Date');
return false
}




if(document.getElementById('paraFrm_plannerBean_startTimeHr').value=="00")
{
if(document.getElementById('paraFrm_plannerBean_StartTimeMin').value=="00"){
alert('Please Enter Start Time');
return false
}
}




if(document.getElementById('paraFrm_plannerBean_newEndDate').value=="")
{alert('Please Enter End Date');
return false
}*/
//if(document.getElementById('paraFrm_plannerBean_newEndDate').value !=document.getElementById('paraFrm_plannerBean_newStartDate').value)
//{alert('Please Enter End Date Equal To Start Date');
//return false
//}


/*if(document.getElementById('paraFrm_plannerBean_endTimeHr').value=="00" )
{
if(document.getElementById('paraFrm_plannerBean_endTimeMin').value=="00" )
{
alert('Please Enter End Time');
return false
}
}


if(eval(document.getElementById('paraFrm_plannerBean_endTimeHr').value)== eval(document.getElementById('paraFrm_plannerBean_startTimeHr').value) )
{
if(eval(document.getElementById('paraFrm_plannerBean_endTimeMin').value)< eval(document.getElementById('paraFrm_plannerBean_StartTimeMin').value) )
{
alert(' End Time must be greater than Start time ');
return false
}

}

 else if(eval(document.getElementById('paraFrm_plannerBean_endTimeHr').value)< eval(document.getElementById('paraFrm_plannerBean_startTimeHr').value) )
{

alert(' End Time must be greater than Start time ');
return false
}
*/

return true;

}

function callOtherTaskProject(value){
	
	if(value==34){
		document.getElementById('otherTaskProjectId').style.display = '';
		document.getElementById('taskProjectId').style.display = '';
		document.getElementById('taskProjectId1').style.display = '';
		document.getElementById('taskTypeId').style.display = 'none';
		document.getElementById('taskTypeId1').style.display = 'none';

		if(document.getElementById('paraFrm_plannerBean_type').value==12){
			document.getElementById('taskTypeId').style.display = '';
			document.getElementById('taskTypeId1').style.display = '';
			document.getElementById('blankId').style.display = 'none';
			document.getElementById('blankId1').style.display = 'none';
		}else{
			document.getElementById('blankId').style.display = '';
			document.getElementById('blankId1').style.display = '';
			document.getElementById('taskTypeId').style.display = 'none';
			document.getElementById('taskTypeId1').style.display = 'none';
		}
		
		
	}else{
		document.getElementById('otherTaskProjectId').style.display = 'none';
		document.getElementById('taskProjectId').style.display = 'none';
		document.getElementById('taskProjectId1').style.display = 'none';
		document.getElementById('taskTypeId').style.display = 'none';
		document.getElementById('taskTypeId1').style.display = 'none';
		document.getElementById('blankId').style.display = 'none';
		document.getElementById('blankId1').style.display = 'none';
	}
}

function callOtherTaskType(value){
	if(value==12){
		document.getElementById('otherTaskProjectId').style.display = '';
		document.getElementById('taskProjectId').style.display = 'none';
		document.getElementById('taskProjectId1').style.display = 'none';
		document.getElementById('taskTypeId').style.display = '';
		document.getElementById('taskTypeId1').style.display = '';
		
		if(document.getElementById('paraFrm_plannerBean_project').value==34){
			document.getElementById('taskProjectId').style.display = '';
			document.getElementById('taskProjectId1').style.display = '';
			document.getElementById('blankId').style.display = 'none';
			document.getElementById('blankId1').style.display = 'none';
		}else{
			document.getElementById('blankId').style.display = '';
			document.getElementById('blankId1').style.display = '';
			document.getElementById('taskProjectId').style.display = 'none';
			document.getElementById('taskProjectId1').style.display = 'none';
		}
	}else{
		document.getElementById('otherTaskProjectId').style.display = 'none';
		document.getElementById('taskProjectId').style.display = 'none';
		document.getElementById('taskProjectId1').style.display = 'none';
		document.getElementById('taskTypeId').style.display = 'none';
		document.getElementById('taskTypeId1').style.display = 'none';
		document.getElementById('blankId').style.display = 'none';
		document.getElementById('blankId1').style.display = 'none';
	}
	
	
	
	
	
	
	
	
}
function taskType()
{
	if(document.getElementById('isEdit').value=='Y') {
		document.getElementById('showTask').style.display = '';
	}
	var val=document.getElementById('paraFrm_plannerBean_taskType').value;
	
	if(val=="S") {
	
	document.getElementById('showTask').style.display ='';
 		
  		document.getElementById('paraFrm_plannerBean_empName').value ='';
   		document.getElementById('paraFrm_plannerBean_empCode').value ='';
    } 
}

function changeDivStyle(val){
	if(val==""){
		document.getElementById('showTask').style.display ='none';
	}
}

window.onload()
{
callOtherTaskProject(document.getElementById('paraFrm_plannerBean_project').value)
callOtherTaskType(document.getElementById('paraFrm_plannerBean_type').value)
//changeDivStyle(document.getElementById('paraFrm_plannerBean_taskType').value);
}

// Function For Adding More Bargaining Agreement
	function addRowUploadBlock(idValue,fileCount)	{
	try{
			document.getElementById('uploadFlag').style.display = 'block';
		  var tbl = document.getElementById('uploadTable'+idValue);
		  var lastRow = tbl.rows.length;
		  // if there's no header row in the table, then iteration = lastRow + 1
		   var iteration = lastRow-1;
		  var row = tbl.insertRow(lastRow);
		 
		  
		  var cell5= row.insertCell(0);
		  var column5 = document.createElement('input');
		  cell5.className='sortableTD';
		  column5.type = 'text';
  		  column5.name = 'uploadLocFileName';
		  column5.id = 'paraFrm_uploadLocFileName'+idValue+''+iteration;
		  column5.size ='50';
		  column5.maxLength ='20';
		  cell5.align='center';
		  cell5.appendChild(column5);
		  
		  var cell6 = row.insertCell(1);
		  var column6 = document.createElement('input');
		  cell6.className='sortableTD';
		  column6.type='button';
		  column6.align='center';
		  column6.name='uploadBtn';
		  column6.value='Upload';
		  column6.className='token';
		  column6.onclick=function(){
		  try {
		  var uploadField= 'paraFrm_uploadLocFileName'+idValue+''+iteration;
		   	uploadTicketFile(uploadField); 
		  }catch(e){alert(e);}
		  };
		  cell6.appendChild(column6);
		  
		  
		  var cell7= row.insertCell(2);
		  var column7 = document.createElement('input');
		  cell7.className='token';
		  column7.type='button';
		  column7.align='center';
		  column7.name='showBtn';
		  column7.value='Show';
		 column7.className='token';
		  column7.onclick=function(){
		  try {
		  return showRecord('paraFrm_uploadLocFileName'+idValue+''+iteration);
		  }catch(e){alert(e);}
		  };
		  cell7.appendChild(column7);

		  var cell8= row.insertCell(3);
		  var column8 = document.createElement('img');
		  cell8.className='sortableTD';
		  column8.type='image';
		  column8.src="../pages/common/css/icons/delete.gif";
		  column8.align='absmiddle';
	  	  column8.id='img'+ iteration;
		  column8.theme='simple';
		  cell8.align='center';

		  column8.onclick=function(){
		  try {
		   deleteCurrentRow(this);
		  }catch(e){alert(e);}
		  };
		  cell8.appendChild(column8);
		} catch(e){
		alert(e);
		}  
		  
	}
	
	function uploadTicketFile(fieldName) {

		var path = document.getElementById("paraFrm_dataPath").value;
		window.open('<%=request.getContextPath()%>/pages/recruitment/uploadResume.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
	}
	
	
	function updateFun(){
	
	document.getElementById('updateStatus').disabled=true;
	document.getElementById('paraFrm').action = 'WeekPlanner_showOnView.action';
	document.getElementById('paraFrm').submit(); 
	document.getElementById('paraFrm').target = "main";
	
	}
	
	function showRecord(fieldName)
	{
		var fileName =document.getElementById(fieldName).value;
		//alert(fileName);
		
		if(fileName=="")
		{
			alert("Please upload file.");
			return false ; 
		}
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = "WeekPlanner_viewAttachedProof.action?fileName="+fileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
		
		return true ; 
	}
	
	function deleteCurrentRow(obj){
		var delRow = obj.parentNode.parentNode;
		var tbl = delRow.parentNode.parentNode;
		var rIndex = delRow.sectionRowIndex;
		var rowArray = new Array(delRow);
		deleteRows(rowArray);
		
		
	}
	
	
	function validateDate(fieldName, labName){
	//alert("hi");
	var date = document.getElementById(fieldName).value;
	if(date=='') return true;
	var dateFormat = /^[0-9]{2}[-]?[0-9]{2}[-]?[0-9]{4}$/;
	
	if(!(date.match(dateFormat)) || date.length<10){
		alert(""+labName+" should be in DD-MM-YYYY format");
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


function deleteRows(rowObjArray){
		for (var i=0; i<rowObjArray.length; i++) {
			var rIndex = rowObjArray[i].sectionRowIndex;
			rowObjArray[i].parentNode.deleteRow(rIndex);
		}
		
		if(rIndex == 1)
		{
		var link = document.getElementById('uploadFlag');
		link.style.display = 'none'; 
		}
	}
	
function callLength1(type,lengthType,maxLenght){
			type = document.getElementById('paraFrm_plannerBean_taskDesc').value;
			//alert(type);
			//var cmt = document.getElementById(type).length;
			//alert(cmt);
			var remain = eval(maxLenght) - eval(type.length);
			document.getElementById('paraFrm_'+lengthType).value = remain;
			if(eval(remain)< 0){
				document.getElementById('paraFrm_plannerBean_taskDesc').style.background = '#FFFF99';
			}
			else {
				document.getElementById('paraFrm_plannerBean_taskDesc').style.background = '#FFFFFF';
				}
	}
	function callLength2(type,lengthType,maxLenght){
			type = document.getElementById('paraFrm_plannerBean_fwComment').value;
			//alert(type);
			//var cmt = document.getElementById(type).length;
			//alert(cmt);
			var remain = eval(maxLenght) - eval(type.length);
			document.getElementById('paraFrm_'+lengthType).value = remain;
			if(eval(remain)< 0){
				document.getElementById('paraFrm_plannerBean_fwComment').style.background = '#FFFF99';
			}
			else {
				document.getElementById('paraFrm_plannerBean_fwComment').style.background = '#FFFFFF';
				}
	}
	function callLength3(type,lengthType,maxLenght){
			type = document.getElementById('paraFrm_plannerBean_statusComment').value;
			//alert(type);
			//var cmt = document.getElementById(type).length;
			//alert(cmt);
			var remain = eval(maxLenght) - eval(type.length);
			document.getElementById('paraFrm_'+lengthType).value = remain;
			if(eval(remain)< 0){
				document.getElementById('paraFrm_plannerBean_statusComment').style.background = '#FFFF99';
			}
			else {
				document.getElementById('paraFrm_plannerBean_statusComment').style.background = '#FFFFFF';
				}
	}
	
	function imposeMaxLength(Event, Object, MaxLen)
	{
	    return (Object.value.length <= MaxLen)||(Event.keyCode == 8 ||Event.keyCode==46||(Event.keyCode>=35&&Event.keyCode<=40))
	}
	
	

</script>