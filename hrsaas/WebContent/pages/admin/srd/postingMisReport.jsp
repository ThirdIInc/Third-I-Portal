<%@ taglib prefix="s" uri="/struts-tags"%>
 <%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="PostingMisReport" method="post" id="paraFrm" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg" >
		
		<tr>
			<td valign="bottom" class="txt">
			<table  width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg" >
			<tr>
			<td>
			<strong class="text_head"><img
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="text_head">Posting MIS Report
			  </strong></td>
			<td width="3%" valign="top" class="txt">
			<div align="right"><img
				src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
				</td>
				</tr>
				</table>
			</td>
		</tr>
	
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">



				<tr>
					<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="78%" colspan="1">
								<input type="button" class="token"
									onclick="return callMisReport();"
									value="   View Report"  />
									<input type="button" onclick="callReset();" class="reset" value="  Reset"> 
							</td>
							<!-- <td width="22%">
							<div align="right"><font color="red">*</font> Indicates
							Required</div>
							</td>-->
						</tr>
					</table>
					<label></label></td>
				</tr>





			

				<tr>
					<td colspan="4">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="2">
								
								
								
								<tr>
									
									<td width="20%" colspan="1"><label  class = "set" name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> :</td>
									<td width="80%" colspan="3"><s:textfield
										theme="simple" theme="simple" name="token"  readonly="true" /><s:hidden theme="simple" name="empid" /><s:textfield
										theme="simple" name="empName" size="55"
										readonly="true" /> <img
										src="../pages/images/recruitment/search2.gif" height="18"
										align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'PostingMisReport_f9action.action');">
									</td>
									

								</tr>
								<tr>
								
								<td nowrap="nowrap" width="20%" colspan="1"><label  class = "set" name="division" id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label> :</td>
									<td width="30%" colspan="1"><s:hidden name="divCode" /> <s:textfield
										name="divsion" theme="simple"   size="25" readonly="true" /> <img
										src="../pages/images/recruitment/search2.gif" height="18"
										align="absmiddle" width="18" 
										onclick="javascript:callsF9(500,325,'PostingMisReport_f9div.action');">
									</td>
									<td colspan="1"><label  class = "set" name="department" id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> :</td>
									<td colspan="1"><s:hidden name="deptCode"
										 theme="simple" /> <s:textfield
										name="deptName" theme="simple" size="25"
										readonly="true" /> <img
										src="../pages/images/recruitment/search2.gif" height="18"
										align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'PostingMisReport_f9dept.action');">
									</td>
									
								</tr>
								<tr>
									<td width="20%" colspan="1"><label  class = "set" name="branch" id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
									<td width="30%" colspan="1"><s:hidden name="centerId"
										 theme="simple" /> <s:textfield
										name="centerName" theme="simple" size="25"
										readonly="true" /> <!-- <img src="../pages/images/search.gif"
										class="iconImage" height="18" align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'LeaveApplicationMis_f9center.action');">-->

									<img src="../pages/images/recruitment/search2.gif" height="18"
										align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'PostingMisReport_f9center.action');">

									</td>
									<td width="20%" colspan="1" nowrap="nowrap"><label  class = "set" name="designation" id="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label> :</td>
									<td width="30%" colspan="1"><s:hidden name="desgCode"
										 theme="simple" /> <s:textfield
										name="desgName" theme="simple" size="25"
										readonly="true" /> <img
										src="../pages/images/recruitment/search2.gif" height="18"
										align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'PostingMisReport_f9desg.action');">
									</td>
									
								</tr>
								
								
								
								
  
									<tr>
										<td ><label   class = "set"
											name="site" id="site" ondblclick="callShowDiv(this);"><%=label.get("site")%></label>:</td>								
												<td>
													<label> <s:hidden
											name="siteCode" value="%{siteCode}"/>
											 <s:hidden
											name="siteLocation" /><s:textfield size="25"
											name="siteName" readonly="true" /> <img
											src="../pages/images/recruitment/search2.gif" class="iconImage"
											height="16" align="absmiddle" width="15"
											onclick="javascript:callsF9(500,325,'PostingMisReport_f9site.action');">&nbsp;</label>
										</td>
													
										<td class="formtext"><label  name="status" id="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label> :</td>
									    <td><label> <s:select name="status"  headerKey="-1" headerValue="--Select--"
											list="#{'S':'Service','R':'Retired','N':'Resigned','E':'Terminated'}" />
										</label></td>							
								</tr>

								<tr>
									<td><label  class = "set" name="report.type" id="report.type" ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label> :</td>
									<td><s:select theme="simple" name="reportType"
										headerKey="Pdf" headerValue="Pdf"
										list="#{'Xls':'Xls'}" /></td>									
								</tr>
								
							</table>
							</td>
						</tr>


					</table>
					</td>
				</tr>


			</table>
			</td>
		</tr>
	</table>

</s:form>

<script type="text/javascript">

	function callMisReport(){
  	document.getElementById('paraFrm').target='_blank';
	document.getElementById('paraFrm').action='PostingMisReport_report.action';
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target="main";
	}
	function callReset(){
  	document.getElementById('paraFrm').target='_self';
	document.getElementById('paraFrm').action='PostingMisReport_clear.action';
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target="_self";
	}
	
</script>
