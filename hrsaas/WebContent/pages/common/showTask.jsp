<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="WeekPlanner" id="paraFrm" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">
		<tr>
			<td colspan="3">
			<table>
				<tr>
					<td valign="bottom" class="txt"><strong class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="formhead">Assigned
					Task </strong></td>
					<td width="5%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>

			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
				<tr>
					<td>
					<table width="100%" colspan="4" border="0" cellpadding="0"
						cellspacing="0">
						<tr>
							<td class="formtext">Define Task :</td>
							<td nowrap="nowrap"><s:hidden name="hiddentaskId"
								id="hiddentaskId" /><s:select list=" #{'S':'Self','O':'Other'}"
								name="plannerBean.taskType" onchange="taskType();" disabled="true"/></td>

							<td colspan="4">
							<div id="divadv">
							<table width="100%" colspan="4" border="0" cellpadding="0"
								cellspacing="0">
								<tr>
									<td height="22" class="formtext">Assigned To :</td>
									<td><s:hidden name="hiddentaskId" id="hiddentaskId" /><s:textfield
										size="30" maxlength="30" name="plannerBean.empName"
										disabled="true"/></td>

								</tr>
								<tr>
									<td colspan="2" class="formtext"><img
										src="../pages/common/css/default/images/space.gif" width="5"
										height="7" /></td>
								</tr>
							</table>
							</div>
							</td>
						</tr>



						<tr>
							<td class="formtext">Task Project :</td>
							<td nowrap="nowrap"><s:select name="plannerBean.project"
								cssStyle="width:175" headerKey=""
								headerValue="--- Select Task Project ---" size="1" list="tmap1" disabled="true"/></td>

							<td height="22" class="formtext">Task Type :</td>
							<td><s:select name="plannerBean.type" cssStyle="width:175"
								headerKey="" headerValue="--- Select Task Type ---" size="1"
								list="tmap" disabled="true"/></td>
						</tr>


						<tr>
							<td class="formtext">Task Title :</td>
							<td nowrap="nowrap"><s:textfield size="50"
								name="plannerBean.taskTitleNew" disabled="true"/></td>
							<td height="22">&nbsp;</td>
							<td height="22" class="formtext">hrs
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; mns</td>
						</tr>

						<tr>
							<td class="formtext">Start Date :</td>
							<td nowrap="nowrap"><s:textfield
								name="plannerBean.newStartDate" size="25" disabled="true" /></td>
							<td height="22" class="formtext">Start Time :</td>
							<td><s:textfield size="2" name="plannerBean.startTimeHr" disabled="true"/>
							:<s:textfield size="2" name="plannerBean.StartTimeMin" disabled="true"/></td>

						</tr>
						<tr>
							<td class="formtext">End Date :</td>
							<td nowrap="nowrap"><s:textfield
								name="plannerBean.newEndDate" size="25" readonly="true" disabled="true"/></td>
							<td height="22" class="formtext">End Time :</td>
							<td><s:textfield size="2" name="plannerBean.endTimeHr" disabled="true"/>:
							<s:textfield size="2" name="plannerBean.endTimeMin" disabled="true"/></td>

						</tr>

						<tr>
							<td class="formtext">Task Description :</td>
							<td nowrap="nowrap"><s:textarea rows="6" cols="60"
								name="plannerBean.taskDesc" disabled="true"/></td>

							<td height="22" class="formtext">Status &nbsp;</td>
							<td><s:select list=" #{'O':'Open','C':'Close'}"
								name="plannerBean.status" disabled="true"/></td>
						</tr>





					</table>
					</td>
				</tr>
			</table>
			</div>
			</td>
		</tr>
	</table>

</s:form>