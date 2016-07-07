<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp"%>

<div align="center" id="overlay" style="z-index: 3; visibility: hidden; position: absolute; width: 776px; height: 450px; margin: 0px; left: 0; top: 0; background-color: #A7BEE2; background-image: url('images/grad.gif'); filter: progid :       DXImageTransform .       Microsoft .       alpha(opacity =       15); -moz-opacity: .1; opacity: .1;"></div>

<s:form action="OutdoorApproval" validate="true" id="paraFrm" theme="simple">
	<table width="100%" class="formbg">
		<tr>
			<td>
				<table width="100%" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt">
							<strong class="formhead"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong>
						</td>
						<td width="93%" class="txt"><strong class="text_head">Outdoor Approval</strong></td>
						<td width="3%" valign="top" class="txt">
							<div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
						</td>
					</tr>
				</table>
				<table>
					<tr>
						<td colspan="3"><img src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
					</tr>
				</table>
				<table width="100%" height="27">
					<tr>
						<td class="formtxt">
							<a href="OutdoorApproval_ckeckdata.action?status=P">Pending List</a> |
							<a href="OutdoorApproval_ckeckdata.action?status=A">Approved List</a> | 
							<a href="OutdoorApproval_ckeckdata.action?status=R">Rejected List</a>
							<s:hidden name="outAppStatus" />
						</td>
					</tr>
				</table>
				<table>
					<tr>
						<td colspan="3"><img src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
					</tr>
				</table>
				
			<!-- 
					<table width="100%">
					<tr>
						<td>
							<s:if test="%{apprflag}"></s:if>
							<s:elseif test="%{insertFlag}">
								<s:submit cssClass="save" value="Save" action="OutdoorApproval_save" onclick="return saveValid()" />
							</s:elseif>
							<input name="reset" type="submit" class="reset" value=" Reset" />
						</td>
						<td width="22%">
							<div align="right"><span class="style2"><font color="red">*</font></span>Indicates Required</div>
						</td>
					</tr>
				</table>
			
			 -->	
			
				<table width="100%" class="formbg">
					<tr>
						<td height="27" class="formtxt">
							<strong>
							<%	String status = (String)request.getAttribute("stat");
	 							if(status != null) {
	 								out.println(status);
	 							} else {
	 								out.println("Select Status");
	 							}
							%>
							</strong>
						</td>
					</tr>
					<tr>
						<td>
							<table width="100%" border="0" cellpadding="0" cellspacing="1"  class="formbg">
								<tr>
									<td width="5%" class="formth" align="center">
										Sr. No.
									</td>
									
									<td width="15%" class="formth" align="center">
										<label id="employee.id" name="employee.id" ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label>
									</td>
									<td width="25%" class="formth" align="center">
										<label id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
									</td>
									<td width="20%" class="formth" align="center">
										<label id="date" name="date" ondblclick="callShowDiv(this);"><%=label.get("date")%></label>
									</td>
									<td width="15%" class="formth" align="center">
										<label id="location" name="location" ondblclick="callShowDiv(this);"><%=label.get("location")%></label>
									</td>
								<!-- 	
									<td width="15%" class="formth" align="center">
										<label id="status" name="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label>
									</td>
								 -->	
								
									<td width="20%" class="formth" align="center">
										<label id="view" name="view" ondblclick="callShowDiv(this);"><%=label.get("view")%></label>
									</td>
								<!-- 
									<td width="15%" class="formth" align="center">
										<label id="remark" name="remark" ondblclick="callShowDiv(this);"><%=label.get("remark")%></label>
									</td>
								 -->
								
								</tr>
								<s:if test="noData">
									<tr>
										<td width="100%" colspan="7" align="center">
											<font color="red">There is no data to display</font></td>
									</tr>
								</s:if>	
								<%!int i = 0;%>
								<% int k = 1; int c = 0; %>	
								<s:iterator value="list" status="stat">
									<tr>
										<td class="sortableTD" width="5%" align="center"><%=k%></td>
										
										<td class="sortableTD" width="15%">
											<s:property value="etoken" /><s:hidden name="outVisitcode"></s:hidden><s:hidden name="level" />
										</td>
										<td class="sortableTD" width="25%">
											<s:property value="ename" /><s:hidden name="eApprCode" />
										</td>
										<td class="sortableTD" width="10%" align="center">
											<s:property value="outDate" /><s:hidden name="outDate" />
										</td>
										<td class="sortableTD" width="12%">
											<s:property value="outLoc" /><s:hidden name="outLoc" />
										</td>
										
									<!-- 
										<td class="sortableTD" width="13%">
											<s:select name="checkStatus" id="<%="check"+k %>" cssStyle="width:100" theme="simple" 
											list="#{'P':'Pending','A':'Approved','R':'Rejected'}" />
										</td>
									 -->	
									
										<td class="sortableTD" width="10%" align="center">
											<input type="button" name="view_Details" class="token" value=" View Details "
											onclick="viewDetails('<s:property value="outVisitcode"/>')" />
											<s:hidden name="etoken"></s:hidden><s:hidden name="outdrCode"></s:hidden>
										</td>
									<!-- 
										<td class="sortableTD" width="15%">
											<s:if test="approval.apprflag"></s:if>
											<s:else>
												<s:textarea name="purpose" rows="1" cols="20" />
											</s:else>
											<s:property value="purpose" />
										</td>
									 -->
									
									</tr>
									<% k++; c++; %>
								</s:iterator>
								<% i = k; %>
							</table>
						</td>
					</tr>
					<tr><td>&nbsp;<s:hidden name="count" value="<%=String.valueOf(c)%>" /></td></tr>
				</table>
			</td>
		</tr>
	</table>
</s:form>

<script language="JavaScript" type="text/javascript" src="../pages/common/include/javascript/sorttable.js"></script>

<script>
	callOnload();
	
	function callOnload() {
		var check = <%=i%>;
		var value = document.getElementById('paraFrm_outAppStatus').value;
		if(value == "A" || value == "R") {
			for(var k = 1; k < check; k++) {
				document.getElementById("check" + k).disabled = true;	
			}
		}
	}		

	function saveValid() {
		var value1 = document.getElementById('paraFrm_outAppStatus').value;
		var invCount = "0";
		var length = document.getElementById("paraFrm_count").value;

		if(document.getElementById("paraFrm_count").value == 0) {
			alert("There is no record to save");
			return false;
		}

		for (var ii = 1; ii <= length; ii++) {
			if(document.getElementById("check" + ii).value == "P") {
				invCount = eval(invCount) + eval(1);
			}
		}
		
		if(invCount == length) {
			alert("Please change the  " + document.getElementById('status').innerHTML.toLowerCase());
			return false;
		}
	
		var value = document.getElementById('paraFrm_outAppStatus').value;
		if(value == "A" || value == "R") {
			for(var k = 1; k < check; k++) {
				document.getElementById("check" + k).disabled = true;	
			}
		}
		document.getElementById("overlay").style.visibility = "visible";
		document.getElementById("overlay").style.display = "block";
		return true;
	}
	
	function viewDetails(outdoorcode) {
		document.getElementById('paraFrm_outdrCode').value = outdoorcode;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action = "OutdoorVisit_showDetailsForApproval.action";
		document.getElementById('paraFrm').submit();
		//document.getElementById('paraFrm').target = "main";
	}
</script>