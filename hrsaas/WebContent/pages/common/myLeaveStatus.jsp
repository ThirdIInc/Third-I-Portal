<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
			
			<tr valign="top">
				<td width="55%" class="formth"><b>Leave Type</b></td>
				<td width="15%" class="formth"><b>Entitled</b></td>
				<td width="15%" class="formth"><b>Utilized</b></td>
				<td width="15%" class="formth"><b>Balance</b></td>
			</tr>
			<s:iterator value="list">
			<tr>
			<td class="sortabletd"><s:property value="leaveType"/>	</td>
			<td align="center" class="sortabletd"><s:property value="entitled"/>	</td>
			<td align="center" class="sortabletd"><s:property value="untilized"/>	</td>
			<td align="center" class="sortabletd">  <s:property value="balanced"/>	</td>
			</tr>
			
			</s:iterator>
		</table>

	