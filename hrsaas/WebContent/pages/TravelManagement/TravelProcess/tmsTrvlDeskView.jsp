
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri='/WEB-INF/cewolf-1.1.tld' prefix='cewolf'%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<script language="JavaScript" type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>

<s:form action="TravelDesk" validate="true" id="paraFrm" theme="simple">
	<table width="100%" border="0" cellpadding="2" cellspacing="1"
		class="formbg">

		<tr>
			<td colspan="3">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt" colspan="1"><strong
						class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt" colspan="2"><strong
						class="text_head">Travel Desk</strong></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class=formbg>
				<tr>
					<td height="27" class="formtxt"><strong>Assigned List</strong></td>
				</tr>
				<tr>
					<td width="100%" colspan="8">
					<table width="100%" cellpadding="1" cellspacing="1" class="formbg"
						border="0">
						<tr><s:hidden name="colorId"/>
							<td width="10%" valign="top" class="formth" nowrap="nowrap"
								colspan="1"><label
								class="set" id="sr.No2" name="sr.No"
								ondblclick="callShowDiv(this);"><%=label.get("sr.No")%></label></td>
							<td width="15%" valign="top" class="formth" colspan="1"><label
								class="set" id="emp.id" name="emp.id"
								ondblclick="callShowDiv(this);"><%=label.get("emp.id")%></label></td>
							<td width="35%" valign="top" class="formth" colspan="1"><label
								class="set" id="schdlr.name1" name="schdlr.name"
								ondblclick="callShowDiv(this);"><%=label.get("schdlr.name")%></label></td>
							<td width="10%" valign="top" class="formth" colspan="1"><label
								class="set" id="travel1" name="travel"
								ondblclick="callShowDiv(this);"><%=label.get("travel")%></label>
							</td>
							
							<td width="10%" valign="top" class="formth" colspan="1"><label
								class="set" id="lodging" name="lodging"
								ondblclick="callShowDiv(this);"><%=label.get("lodging")%></label>
							</td>
							
							<td width="12%" valign="top" class="formth" colspan="1"><label
								class="set" id="local.conv1" name="local.conv"
								ondblclick="callShowDiv(this);"><%=label.get("local.conv")%></label></td>
						</tr>

						<%!int j = 0;%>
						<%
						int m = 1;
						%>

						<s:iterator value="assignedCntList">
							<tr id="asSchTr<%=m%>">
								<td class="sortableTD" nowrap="nowrap" colspan="1" width="10%"><%=m%></td>
								<td class="sortableTD" width="15%" colspan="1" ><s:property
									value="schEmpTokenCnt" />
									<s:hidden name="schEmpIdCnt" id='<%="schEmpIdCnt" + m%>'/> <s:hidden
									name="schEmpTokenCnt" />&nbsp;</td>

								<td class="sortableTD" width="35%" colspan="1"><s:property
									value="schEmpNameCnt" /><s:hidden name="schEmpNameCnt" />&nbsp;</td>

								<td class="sortableTD" width="10%" colspan="1" align="center">
								<s:property value="trvlCnt" /><s:hidden name="trvlCnt" />&nbsp;</td>								

								<td class="sortableTD" width="10%" colspan="1" align="center">
								<s:property value="lodgeCnt" /><s:hidden name="lodgeCnt" />&nbsp;</td>
								
								<td class="sortableTD" width="12%" colspan="1" align="center">
								<s:property value="localConvCnt" /><s:hidden
									name="localConvCnt" />&nbsp;</td>

							</tr>
							<script>							
							
							if(document.getElementById('schEmpIdCnt<%=m%>').value==
								   document.getElementById('paraFrm_colorId').value)
								   		document.getElementById('asSchTr<%=m%>').style.background = '#FFFFCC';
								   		
							</script>
							<%
							m++;
							%>
						</s:iterator>
						<%
						j = m;
						%>

					</table>
					</td>
				</tr>
				<!-- <tr>
						<td colspan="6" align="center"><input type="button"
							class="token" value="Close" onclick="return callClose();"></td>
					</tr>-->

			</table>
			</td>
		</tr>

		<tr>
			<td>
			<table width="100%" border="0" align="center" class="formbg">
				<tr>
					<td><strong>Graphical View</strong></td>
				</tr>
				<tr>
					<td><cewolf:chart id="verticalBar3D1" title=""
						type="verticalBar3d" xaxislabel="" yaxislabel=""
						showlegend="false">
						<cewolf:gradientpaint>
							<cewolf:point x="0" y="0" color="#FFFFFF" />
						</cewolf:gradientpaint>
						<cewolf:chartpostprocessor id="meterRanges">
						</cewolf:chartpostprocessor>
						<cewolf:data>
							<cewolf:producer id="categoryData" />
						</cewolf:data>
					</cewolf:chart> <cewolf:img chartid="verticalBar3D1" renderer="/cewolf"
						width="590" height="450" /></td>
				</tr>
			</table>
			</td>
		</tr>

	</table>
</s:form>

<!--<script>
function callClose(){
window.close();
}

</script>-->

