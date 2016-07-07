<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="RequisitionAnalysis" validate="true" id="paraFrm" validate="true" theme="simple">
<s:hidden name="show" />
<s:hidden name="myPage" id="myPage" />
<s:hidden name="hiddencode" /><s:hidden name="requisitionHiddenCode" />
<s:hidden name="divison"/><s:hidden name="divisonCode" />
<s:hidden name="branch"/><s:hidden name="branchCode" />
<s:hidden name="department"/><s:hidden name="deptCode" />
<s:hidden name="designation"/><s:hidden name="designationCode" />
<s:hidden name="reqDate"/>
<s:hidden name="noOfPersons"/>
<s:hidden name="totalCTC"/>
<s:hidden name="reqLength"/>
<table class="formbg" width="100%">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Requisition Analysis Report</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16"
						height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td width="78%"><s:if test="noData"></s:if>
			 <s:else>
				<input type="button" class="report" value="Export Into Pdf"
					onclick="callReport('Pdf');">
				<input type="button" class="report" value="Export Into Doc"
					onclick="callReport('Txt');">
				<input type="button" class="report" value="Export Into Xls"
					onclick="callReport('Xls');">		
			</s:else> <input type="button" class="back" value="    Back" onclick="callBack();">
			</td>
		</tr>
		
		<tr>
			<td colspan="4">
			<table width="100%" class="formbg">
				<s:if test="noData">
				</s:if>
				<s:else>
					<tr>
						<td width="25%" colspan="1" nowrap="nowrap"><label
							class="set" name="reqs.code" id="reqs.code"
							ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label>
						:</td>
						<td width="25%" colspan="1"><s:property
							value="requisitionCode" /></td>
						<td width="25%" colspan="1"><label class="set"
							name="division" id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
						:</td>
						<td width="25%" colspan="1"><s:property value="divison" /></td>
					</tr>

					<tr>
						<td width="25%" colspan="1" nowrap="nowrap"><label
							class="set" name="branch" id="branch"
							ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
						:</td>
						<td width="25%" colspan="1"><s:property
							value="branch" /></td>
						<td width="25%" colspan="1"><label class="set"
							name="department" id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
						:</td>
						<td width="25%" colspan="1"><s:property value="department" /></td>
					</tr>
					
					<tr>
						<td width="25%" colspan="1" nowrap="nowrap"><label
							class="set" name="designation" id="designation"
							ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
						:</td>
						<td width="25%" colspan="1"><s:property
							value="designation" /></td>
						<td width="25%" colspan="1"><label class="set"
							name="reqs.date" id="reqs.date" ondblclick="callShowDiv(this);"><%=label.get("reqs.date")%></label>
						:</td>
						<td width="25%" colspan="1"><s:property value="reqDate" /></td>
					</tr>
					
					<tr>
						<td width="25%" colspan="1" nowrap="nowrap"><label
							class="set" name="noOfPersons" id="noOfPersons"
							ondblclick="callShowDiv(this);"><%=label.get("noOfPersons")%></label>
						:</td>
						<td width="25%" colspan="1"><s:property
							value="noOfPersons" /></td>
						<td width="25%" colspan="1"><label class="set"
							name="totalCTC" id="totalCTC" ondblclick="callShowDiv(this);"><%=label.get("totalCTC")%></label>
						:</td>
						<td width="25%" colspan="1"><s:property value="totalCTC" /></td>
					</tr>
					
			</s:else>
			</table>
			</td>
		</tr>
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<%
					int totalPage = (Integer) request.getAttribute("totalPage");
					int pageNo = (Integer) request.getAttribute("PageNo");
					
					%>
					<s:if test="noData">
					</s:if>	
					<s:else>
					<td align="right"><b>Page:</b>
					 <input type="hidden" name="totalPage"  id="totalPage" value="<%=totalPage%>">
						 	<a href="#" onclick="callPage('1','F','<%=totalPage %>','RequisitionAnalysis_callJspView.action');"  >
										<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
									</a>&nbsp;
									<a href="#" onclick="callPage('P','P','<%=totalPage %>','RequisitionAnalysis_callJspView.action');" >
										<img  title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
									</a> 
							    <input type ="text" name="pageNoField" id="pageNoField" theme="simple" size="3"  value="<%= pageNo%>"  onkeypress="callPageText(event,'<%=totalPage %>','RequisitionAnalysis_callJspView.action');return numbersOnly()"   maxlength="4" /> of <%=totalPage%>
						 		 		<a href="#" onclick="callPage('N','N','<%=totalPage %>','RequisitionAnalysis_callJspView.action')" >
											<img  title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
										</a>&nbsp;
										<a href="#" onclick="callPage('<%=totalPage%>','L','<%=totalPage %>','RequisitionAnalysis_callJspView.action');" >
											<img title="Last Page"  src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
										</a>
							 
					</td>  
					</s:else>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
			<td colspan="3">
			<%
			try {
			%>
			<table width="100%" border="0" cellpadding="0" cellspacing="1"
				class="formbg">

				<tr>

					<td class="formtext">
					<table width="100%" border="0" cellpadding="1" cellspacing="1">
						<tr>
							<td height="15" class="formhead" nowrap="nowrap" colspan="10"><strong
								class="forminnerhead">Requisition Analysis List</strong></td>
						</tr>
						<tr>
							<td width="3%" class="formth" align="center"><label  class = "set" name="serial.no" id="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label> 
							</td>
							<td width="15%" class="formth" align="center">
							<label
							class="set" name="reqs.code" id="reqs.code1" ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label>
							</td>
							<td width="10%" class="formth" align="center">
							<label class="set" name="division" id="division1" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
							</td>
							<td width="11%" class="formth" align="center">
							<label class="set" name="branch" id="branch1" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
 							</td>
 							<td width="3%" class="formth" align="center">
 							<label class="set" name="department" id="department1" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
 							</td>
 							<td width="11%" class="formth" align="center">
 							<label class="set" name="designation" id="designation1" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
 							</td>
 							<td width="8%" class="formth" align="center">
 							<label class="set" name="reqs.date" id="reqs.date1" ondblclick="callShowDiv(this);"><%=label.get("reqs.date")%></label>
 							</td>
 							<td width="3%" class="formth" align="center">
 							<label class="set" name="noOfPersons" id="noOfPersons1" ondblclick="callShowDiv(this);"><%=label.get("noOfPersons")%></label>
 							</td>
 							<td width="19%" class="formth" align="center">
 							<label class="set" name="avgCTC" id="avgCTC" ondblclick="callShowDiv(this);"><%=label.get("avgCTC")%></label>
 							</td>
 							<td width="15%" class="formth" align="center">
 								<label class="set" name="totalCTC" id="totalCTC1" ondblclick="callShowDiv(this);"><%=label.get("totalCTC")%></label>
 							</td>
					</tr>
							<%!int k=0; %>
						<%int i=0; %>
						<%
											int cnt = pageNo * 20 - 20;
											
						%>
						<s:if test="noData">
									<tr>
										<td width="100%" colspan="10" align="center"><font
											color="red">There is no data to display</font></td>
									</tr>
						</s:if>
							<s:iterator value="requisitionList">
							<tr>
							<td width="3%"  align="center" class="sortableTD"><%=++cnt%></td>
							<td width="15%" align="left" class="sortableTD"><s:property value="requisitionCode"/></td>
							<td width="10%"  align="left" class="sortableTD">&nbsp;<s:property value="divison"/></td>
							<td width="11%"  align="left" class="sortableTD">&nbsp;<s:property value="branch"/></td>
							<td width="3%"   align="left" class="sortableTD">&nbsp;<s:property value="department"/> </td>
	                        <td width="11%" align="left" class="sortableTD">&nbsp; <s:property value="designation"/> </td>
	                        <td width="8%" align="left" class="sortableTD">&nbsp; <s:property value="reqDate"/> </td>
	                        <td width="3%" align="left" class="sortableTD">&nbsp;<s:property value="noOfPersons"/> </td>
	                        <td width="3%" align="left" class="sortableTD">&nbsp;<s:property value="avgCTC"/> </td>
	                        <td width="15%" align="left" class="sortableTD" nowrap="nowrap">&nbsp;	<s:property value="totalCTC"/></td>
							</tr>	
							</s:iterator>
							<%
							k=i;
							%>

					</table>
					
					<%
						} catch (Exception e) {
						}
					%>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="78%"><s:if test="noData"> </s:if>
				<s:else>
				<input type="button" class="report" value="Export Into Pdf"
					onclick="callReport('Pdf');">
				<input type="button" class="report" value="Export Into Doc"
					onclick="callReport('Txt');">
				<input type="button" class="report" value="Export Into Xls"
					onclick="callReport('Xls');">
				</s:else> <input type="button" class="back" value="    Back"
				onclick="callBack();"></td>
			<td align="right" width="20%" nowrap="nowrap"><strong
				class="text_head">Total Records :<s:property
				value="reqLength" /></strong></td>
		</tr>
	</table>		
</s:form>

<script>
	function callBack()
	{
		document.getElementById('paraFrm').target =""; 
	 	document.getElementById('paraFrm').action = "RequisitionAnalysis_input.action";
     	document.getElementById('paraFrm').submit(); 
	}
	function callReport(reportType){
		document.getElementById('paraFrm').action = "RequisitionAnalysis_report.action?selectedType="+reportType;
    	document.getElementById('paraFrm').submit(); 
	}
	
</script>