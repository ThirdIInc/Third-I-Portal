<%@ taglib prefix="s" uri="/struts-tags"%>

<%@include file="/pages/common/labelManagement.jsp"%>

<script language="JavaScript" type="text/javascript" src="../pages/common/include/javascript/sorttable.js"></script>

<div align="center" id="overlay" style="z-index: 3; visibility: hidden; position: absolute; width: 776px; height: 450px; margin: 0px; left: 0; top: 0; background-color: #A7BEE2; background-image: url('images/grad.gif'); filter: progid : DXImageTransform . Microsoft . alpha(opacity = 15); -moz-opacity: .1; opacity: .1;"></div>

<s:form action="CompOffApp" validate="true" id="paraFrm" validate="true" theme="simple">
	<table width="100%" class="formbg"><tr><td>
	<table width="100%" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
				<strong class="formhead"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong>
			</td>
			<td width="93%" class="txt"><strong class="text_head">Compensatory Off Approval</strong></td>
			<td width="3%" valign="top" class="txt"><div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
		</tr>
	</table>
	<table width="100%">
		<tr>
			<td colspan="3">
				<table width="100%">
					<tr>
						<td colspan="2">
							<table width="100%">
								<tr>
									<td height="27" class="formtxt">
										<a href="CompOffApp_callStatus.action?status=P">Pending List</a> | 
										<a href="CompOffApp_callStatus.action?status=A">Approved List</a> | 
										<a href="CompOffApp_callStatus.action?status=R">Rejected List</a>
									</td>
								</tr>
								<s:hidden name="apprflag" /><s:hidden name="listLength" />
							</table>
						</td>
					</tr>
				</table>
				<table width="100%">
					<tr>
						<td width="78%">
							<img src="../pages/images/recruitment/space.gif" width="5" height="4" /><br/>
							<s:if test="%{apprflag}"></s:if>
							<s:else>
								<input name="save" type="button" class="save" value="Save" onclick="saveValidate();" />
							</s:else>
							<input name="Submit3" type="submit" class="reset" value="Reset" />
						</td>
						<td width="22%">
							<div align="right"><span class="style2"><font color="red">*</font></span>Indicates Required</div>
						</td>
						<s:hidden name="status" />
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<table width="100%" class="formbg">
					<tr>
						<s:if test="%{pen}"><td height="27" class="formtxt"><strong>Pending List</strong></td></s:if>
						<s:elseif test="%{app}"><td height="27" class="formtxt"><strong>Approved List</strong></td></s:elseif>
						<s:elseif test="%{rej}"><td height="27" class="formtxt"><strong>Rejected List</strong></td></s:elseif>
						<s:else><td height="27" class="formtxt"><label class="set" id="selStat" name="selStat" ondblclick="callShowDiv(this);"><%=label.get("selStat")%></label></td></s:else>
					</tr>
					<tr>
						<td>
							<s:hidden name="compViewNo" />
							<table width="100%" border="1" cellpadding="1" cellspacing="1" class="sortable">
								<tr>
									<td width="5%" valign="top" class="formth"><label class="set" id="sno" name="sno"
										ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></td>
									<td width="10%" valign="top" class="formth"><label
										class="set" id="employee.id" name="employee.id"
										ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></td>
									<td width="25%" valign="top" class="formth"><label
										class="set" id="employee" name="employee"
										ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
									<td width="15%" valign="top" class="formth"><label
										class="set" id="appdate" name="appdate"
										ondblclick="callShowDiv(this);"><%=label.get("appdate")%></label></td>
									<td width="10%" valign="top" class="formth"><label
										class="set" id="stat" name="stat"
										ondblclick="callShowDiv(this);"><%=label.get("stat")%></label></td>
									<td width="15%" valign="top" class="formth"><label
										class="set" id="details" name="details"
										ondblclick="callShowDiv(this);"><%=label.get("details")%></label></td>
									<td width="25%" valign="top" class="formth"><label
										class="set" id="remark" name="remark"
										ondblclick="callShowDiv(this);"><%=label.get("remark")%></label></td>
									</tr>
									<s:if test="noData">
										<tr>
											<td width="100%" colspan="8" align="center"><font
												color="red">No Data To Display</font></td>
										</tr>
									</s:if>
									<%!int i = 0;%>
									<%int k = 1;%>		
									<s:iterator value="cmpList">		
										<tr>
											<td class="border2" width="5%"><%=k%><s:hidden
												name="compEmpId" /> <s:hidden name="empName" /> <s:hidden
												name="appDate" /> <s:hidden name="compEmpToken" /> <s:hidden
												name="compId" /> <s:hidden name="level" /></td>
											<td class="border2" width="10%"><s:property
												value="compEmpToken" /></td>
											<td class="border2" width="20%"><s:property
												value="empName" /></td>
											<td class="border2" width="15%"><s:property
												value="appDate" /></td>
											<td align="center" width="10%" class="border2"><s:if
												test="compApp.apprflag">
												<s:property value="statusNew" />
											</s:if> <s:else>
												<s:select name="checkStatus" id="<%="check"+k %>"
													cssStyle="width:100" theme="simple"
													list="#{'P':'Pending','A':'Approved','R':'Rejected'}" />
											</s:else></td>
											<td class="border2" width="15%" align="center"><input
												type="button" name="view_Details" class="pagebutton"
												value="Details"
												onclick="viewDetails('<s:property value="compId"/>')" /></td>
											<td class="border2" width="20%"><s:if
												test="compApp.apprflag">
												<s:property value="remark" />
											</s:if><s:else>
											<s:textarea name="remark" value="%{remark}" rows="2"
											cols="20"></s:textarea>
											</s:else></td>
										</tr>
										<%k++;%>
								</s:iterator>
								<%i = k;%>
							</table>
						</td>
					</tr>
					<s:hidden name="hiddenTrfCode" /><s:hidden name="count" value="<%=String.valueOf(k)%>" /></td>
				</table>
			</td>
		</tr>
	</table>
	</td></tr></table>
</s:form>

<script>
	 callOnload();
	
	function saveValidate(){
	var lenkkkk=document.getElementById("paraFrm_listLength").value;
	
	 
	if(document.getElementById("paraFrm_listLength").value=="0"){
	alert("No record to save");
	return false;
	}else{
	var chkFlag ="false"; 
	var length = document.getElementById("paraFrm_count").value; 
	for (i=1; i <length;i++){
	
			if(document.getElementById("check"+i).value=="A" || document.getElementById("check"+i).value=="R" ){
				 chkFlag ="true";
				 break;
			}
		}
		
		
		if(chkFlag =="false"){
		alert("Please change the "+document.getElementById('stat').innerHTML.toLowerCase());
		return false;
		}
	
	    document.getElementById("overlay").style.visibility = "visible";
		document.getElementById("overlay").style.display = "block";
		
	
	
	document.getElementById("paraFrm").action="CompOffApp_save.action";
 	document.getElementById("paraFrm").submit();
 		}
	}
		function callReset(){
			document.getElementById("paraFrm").action="CompOffApp_reset.action";
 	document.getElementById("paraFrm").submit();
	 }
	 function callOnload(){
	 	var check =<%=i%>;
	 document.getElementById('paraFrm_status').value ='P'; 

	 if(document.getElementById("paraFrm_status").value=='A' || document.getElementById("paraFrm_status").value=='R'){
			for(var k = 1;k < check ;k++ ){
			document.getElementById("check"+k).disabled=true;	
			}
		}
	}
	function viewDetails(compNo){
	 
		document.getElementById('paraFrm_compViewNo').value = compNo;
		document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = "CompOffApp_callView.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "";
		 
	}
</script>