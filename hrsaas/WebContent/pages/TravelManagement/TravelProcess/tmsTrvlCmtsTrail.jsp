<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<s:form action="TravelAppvr_input" id="paraFrm" theme="simple" >
	<s:hidden name="noData" />
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="2" class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt" colspan="1"><strong
						class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt" colspan="2"><strong
						class="text_head">Travel Comments Trail</strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">
				<tr>
					<td class="formth"><label class="set" name="trvl.srNo" id="trvl.srNo" ondblclick="callShowDiv(this);"><%=label.get("trvl.srNo")%></label></td>
					<td class="formth"><label class="set" name="trvl.DateTime" id="trvl.DateTime" ondblclick="callShowDiv(this);"><%=label.get("trvl.DateTime")%></label></td>
					<td class="formth"><label class="set" name="trvlUsrType" id="trvlUsrType" ondblclick="callShowDiv(this);"><%=label.get("trvlUsrType")%></label></td>
					<td class="formth"><label class="set" name="trvl.usrName" id="trvl.usrName" ondblclick="callShowDiv(this);"><%=label.get("trvl.usrName")%></label></td>
					<td class="formth"><label class="set" name="trvl.status" id="trvl.status" ondblclick="callShowDiv(this);"><%=label.get("trvl.status")%></label></td>
					<td class="formth"><label class="set" name="trvl.cmts" id="trvl.cmts" ondblclick="callShowDiv(this);"><%=label.get("trvl.cmts")%></label></td>

				</tr>
				<%!int d = 0;%>
				<%
				int z = 1;
				%>

				<s:iterator value="cmtsTrail">
					<tr>
						<td class="sortableTD"><%=z%></td>
						<td class="sortableTD"><s:property value="cmtsTrlDate" />&nbsp;</td>
						<td class="sortableTD"><s:property value="cmtsTrlUser" />&nbsp;</td>
						<td class="sortableTD"><s:property value="cmtsTrlName" />&nbsp;</td>
						<td class="sortableTD"><s:property value="cmtsTrlStatus" />&nbsp;</td>
						<td class="sortableTD"><s:property value="cmtsTrlCmts" />&nbsp;</td>

					</tr>
					<%
					z++;
					%>
				</s:iterator>
				<%
				d = z;
				%>
				<%if(d<=1)
					{%>
						<tr>
						<td width="100%" colspan="6" align="center"><font color="red">No
						Data To Display</font></td>
					</tr>
					<%
					}
					%>
			</table>
			</td>
			</tr>
			</table>
</s:form>



