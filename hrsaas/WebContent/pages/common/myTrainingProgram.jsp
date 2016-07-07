<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0" class="sortable" bgcolor="#FFFFFF">
			<tr>
				<td width="50%" class="whitetable">Subject</td>
				<td width="15%" class="whitetable">From Date</td>
				<td width="15%" class="whitetable">To Date</td>
				<td width="10%" class="whitetable">Type</td>
				<td width="10%" class="whitetable">Status</td>
			</tr>
			<s:iterator value="mylist">
			<tr>
			<td class="whitetable1"><s:property value="subject"/>	</td>
			<td  class="whitetable1"><s:property value="fromDate"/>	</td>
			<td  class="whitetable1"><s:property value="toDate"/>	</td>
			<td  class="whitetable1">  <s:property value="type"/>	</td>
			<td  class="whitetable1">  <s:property value="status"/>	</td>
			</tr>
			
			</s:iterator>
			
		</table>