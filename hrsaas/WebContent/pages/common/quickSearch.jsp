<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri='/WEB-INF/cewolf-1.1.tld' prefix='cewolf'%>

<table width="100%" border="0" align="center" cellpadding="0"
	cellspacing="0" bgcolor="#FFFFFF">
	<tr><td colspan="2">&nbsp;</td></tr>
				<tr><td colspan="2">
			<s:radio name="pollOption" theme="simple" onclick="callPollQuick(this);"
				list="#{'e':'Employee Name','b':'Birth Date','j':'Joining Date'}" ></s:radio>
				</td></TR>
	<tr>
	<tr><td colspan="2">&nbsp;</td></tr>
	<tr>
	<td>
	<s:textfield name="quickSearch" id="quickSearch"
				theme="simple" value="" size="24" /></td><td>
				<input type="button"
				class="pagebutton" value=" Search " onclick="callForDetails()" />
				</td></tr>				
				<tr><td colspan="2">&nbsp;</td></tr>
				<tr><td colspan="2" align="right">
				<a href="#" onclick="callForAdvSearch()">Advance Search >></a>
				</td></tr>
</table>
