<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="ChangePassword" id="paraFrm" validate="true"
	target="_top">
	<table width="100%">
		<tr>
			<td width="100%" colspan="3" class="pageHeader" align="center">Password
			Change</td>
		</tr>
		<tr>
			<td>
			<table colspan="2" align="center">
				<s:hidden name="emp_id" />
				<tr>
					<td class="COMMONLABEL">Emp Name</td> <td><s:textfield theme="simple" name="empnm" /></td>
				</tr>
				<tr>
					<td class="COMMONLABEL">ENTER Old Password</td>
					<td><s:textfield theme="simple" name="oldpsswd" /></td>
				</tr>
				<tr>
					<td class="COMMONLABEL">ENTER NEW Password</td>
					<td><s:textfield theme="simple" name="newpsswd1" /></td>
				</tr>
				<tr>
					<td class="COMMONLABEL">RE-ENTER NEW Password</td>
					<td><s:textfield theme="simple" name="newpsswd2" /></td>
				</tr>
				<tr>
					<td colspan="1"></td>
					<td align="center"><s:submit cssClass="pagebutton" cssClass="button" theme="simple"
						action="ChangePassword_savePsswd" value="Save" /></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>
