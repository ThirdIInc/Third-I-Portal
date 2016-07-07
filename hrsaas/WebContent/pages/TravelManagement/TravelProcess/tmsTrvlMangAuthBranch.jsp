<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="TrvlMangAuthorities_input" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="2" class="formbg">
		<!-- <tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Branch
					list </strong></td>

				</tr>
			</table>
			</td>
		</tr>-->


		<s:if test="showBranchFlag">
			<tr>
				<td colspan="3">
				<%
				try {
				%>
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="formbg" class="sortable">

					<tr>

						<td class="formtext" width="100%">
						<table width="100%" border="0" cellpadding="1" cellspacing="1">
							<tr>
								<td height="15" class="formhead" nowrap="nowrap" colspan="7"><strong
									class="forminnerhead">Branch List</strong></td>
							</tr>
							<tr>

								<td class="formth" align="center" width="3%"><strong><label
									class="set" id="sr.no1" name="sr.no"
									ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></strong></td>
								<td class="formth" align="center" width="15%"><strong><label
									class="set" id="branch.name4" name="branch.name"
									ondblclick="callShowDiv(this);"><%=label.get("branch.name")%></label></strong></td>


							</tr>


							<%
							int count1 = 0;
							%>
							<%!int k = 0;%>
							<%							
							int m = 0;							 
							%>




							<s:iterator value="branchList">


								<tr id="tr1" <%if(count1%2==0){
									%> class="tableCell1"
									<%}else{%> class="tableCell2" <%	}count1++; %>
									onmouseover="javascript:newRowColor(this);"
									onmouseout="javascript:oldRowColor(this,<%=count1%2 %>);">


									<td align="left" class="sortableTD" nowrap="nowrap"><%=++m%>

									</td>


									<td align="left" class="sortableTD"><s:property
										value="divBranch" />&nbsp;</td>

								</tr>

							</s:iterator>
							<%
							k = m;							
							%>
						</table>


						</td>
					</tr>
				</table>
				<%
					} catch (Exception e) {
					}
				%>
				</td>
			</tr>
		</s:if>










	</table>
</s:form>

