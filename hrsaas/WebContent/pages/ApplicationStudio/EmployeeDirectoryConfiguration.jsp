<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="EmployeeDirectoryConf" validate="true" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td colspan="4">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Employee Directory Configuration </strong></td>
				</tr>
			</table>
			</td>
			</tr>
			<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><s:submit cssClass="save"
						 theme="simple" value="   Save   "
						action="EmployeeDirectoryConf_save" /> <s:submit cssClass="reset"
						action="EmployeeDirectoryConf_reset" theme="simple" value="    Reset  " />
					</td>
					<td width="22%">
					<div align="right"><font color="red">&nbsp;</font> </div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
				<tr>
					<td colspan="4">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2" class="formbg">
						<tr>
							<td colspan="4" class="text_head"><strong
								class="forminnerhead">Personal Information </strong></td>
						</tr>
						<tr>
													<td width="40%">
														<label id="birthday" name="birthday" ondblclick="callShowDiv(this);"><%=label.get("birthday")%></label> :
													</td>
													<td><s:checkbox name="birthdayFlag" /></td>
												</tr>
						<tr>
													<td width="40%">
														<label id="bloodgroup" name="bloodgroup" ondblclick="callShowDiv(this);"><%=label.get("bloodgroup")%></label> :
													</td>
													<td><s:checkbox name="bloodgroupFlag" /></td>
												</tr>	
						<tr>
													<td width="40%">
														<label id="employeephoto" name="employeephoto" ondblclick="callShowDiv(this);"><%=label.get("employeephoto")%></label> :
													</td>
													<td><s:checkbox name="photoFlag" /></td>
												</tr>	
						</table>
					</td>
				
			</tr>
			</tr>
				<tr>
					<td colspan="4">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2" class="formbg">
						<tr>
							<td colspan="4" class="text_head"><strong
								class="forminnerhead">Company Profile </strong></td>
						</tr>
						<tr>
													<td width="40%">
														<label id="dateofjoining" name="dateofjoining" ondblclick="callShowDiv(this);"><%=label.get("dateofjoining")%></label> :
													</td>
													<td><s:checkbox name="dateofjoiningFlag" /></td>
												</tr>
						<tr>
													<td width="40%">
														<label id="department" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> :
													</td>
													<td><s:checkbox name="departmentFlag" /></td>
												</tr>		<tr>
													<td width="40%">
														<label id="roleanddesignation" name="roleanddesignation" ondblclick="callShowDiv(this);"><%=label.get("roleanddesignation")%></label> :
													</td>
													<td><s:checkbox name="roleanddesignationFlag" /></td>
												</tr>
						<tr>
													<td width="40%">
														<label id="managerInfo" name="managerInfo" ondblclick="callShowDiv(this);"><%=label.get("managerInfo")%></label> :
													</td>
													<td><s:checkbox name="managerInfoFlag" /></td>
												</tr>		
						</table>
					</td>
				
			</tr>
			</tr>
				<tr>
					<td colspan="4">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2" class="formbg">
						<tr>
							<td colspan="4" class="text_head"><strong
								class="forminnerhead">Contact Information </strong></td>
						</tr>
						<tr>
													<td width="40%">
														<label id="locatoin" name="locatoin" ondblclick="callShowDiv(this);"><%=label.get("locatoin")%></label> :
													</td>
													<td><s:checkbox name="locatoinFlag" /></td>
												</tr>
						<tr>
													<td width="40%">
														<label id="email" name="email" ondblclick="callShowDiv(this);"><%=label.get("email")%></label> :
													</td>
													<td><s:checkbox name="emailFlag" /></td>
												</tr>		
						<tr>
													<td width="40%">
														<label id="extension" name="extension" ondblclick="callShowDiv(this);"><%=label.get("extension")%></label> :
													</td>
													<td><s:checkbox name="extensionFlag" /></td>
												</tr>
						<tr>
													<td width="40%">
														<label id="mobileno" name="mobileno" ondblclick="callShowDiv(this);"><%=label.get("mobileno")%></label> :
													</td>
													<td><s:checkbox name="mobilenoFlag" /></td>
												</tr>
						<tr>
													<td width="40%">
														<label id="phoneno" name="phoneno" ondblclick="callShowDiv(this);"><%=label.get("phoneno")%></label> :
													</td>
													<td><s:checkbox name="phonenoFlag" /></td>
												</tr>																									
						</table>
					</td>
				
			</tr>
			<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><s:submit cssClass="save"
						 theme="simple" value="   Save   "
						action="EmployeeDirectoryConf_save" /> <s:submit cssClass="reset"
						action="EmployeeDirectoryConf_reset" theme="simple" value="    Reset  " />
					</td>
					<td width="22%">
					<div align="right"><font color="red">&nbsp;</font> </div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		</table>
</s:form>