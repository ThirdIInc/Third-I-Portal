
<%@ taglib prefix="s" uri="/struts-tags"%>

<%@include file="/pages/common/labelManagement.jsp"%>


<s:form action="DepartmentClearance" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				0
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Department
					Clearance</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>

			<td width="100%" colspan="3"><s:if test="clearListFlag">
			<input type="button" class="token" value="    Back"
				onclick="return callBack();" theme="simple" />
			</s:if> <s:else>
				<input type="button" class="add" value="    Save"
					onclick="return saveValidate(this,'C');" theme="simple" />
					<input type="button" class="token" value="    Back"
				onclick="return callBack();" theme="simple" />

			</s:else>&nbsp;<!--<s:if test = "true"><input type="button"
				class="token" value="    Clear" onclick="return callClear();"
				theme="simple" /></s:if>--></td>




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
							<td width="20%" colspan="1" class="formtext"><label
								class="set" name="employee" id="employee"
								ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:
							<s:hidden name="employeeCode" value="%{employeeCode}" /></td>
							<td width="80%" colspan="3"><s:property value="empToken" /><s:property
								value="employeeName" /></td>

						</tr>

						<tr>
							<td width="20%" colspan="1"><label class="set"
								name="designation" id="designation"
								ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
							:</td>
							<td width="30%" colspan="1"><s:property
								value="%{designationName}" /></td>

							<td width="20%" colspan="1"><label class="set" name="branch"
								id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
							:</td>
							<td width="30%" colspan="1"><s:property
								value="%{branchName}" /></td>
						</tr>
						<tr>
							<td width="20%" colspan="1" class="formtext"><label
								class="set" name="department" id="department"
								ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:</td>
							<td width="30%" colspan="1"><s:property
								value="%{departmentName}" /><s:hidden name="deptCode" /></td>
							<td width="20%" colspan="1" class="formtext"><label
								class="set" name="appdate" id="appdate"
								ondblclick="callShowDiv(this);"><%=label.get("appdate")%></label>:
							</td>
							<td width="30%" colspan="1"><s:property value="resignDate" /></td>
						</tr>

						<tr>
							<td width="20%" colspan="1" class="formtext"><label
								class="set" name="acceptDate" id="acceptDate"
								ondblclick="callShowDiv(this);"><%=label.get("acceptDate")%></label>:
							</td>
							<td width="30%" colspan="1"><s:property
								value="%{acceptDate}" /></td>
							<td width="20%" colspan="1" class="formtext"><label
								class="set" name="sepDate" id="sepDate"
								ondblclick="callShowDiv(this);"><%=label.get("sepDate")%></label></td>
							<td width="30%" colspan="1"><s:property
								value="seperationDate" /></td>

						</tr>


						<tr>
							<td width="20%" colspan="1" class="formtext"><label
								class="set" name="applicationDate" id="applicationDate"
								ondblclick="callShowDiv(this);"><%=label.get("applicationDate")%></label>:
							</td>
							<td width="30%" colspan="1"><s:textfield theme="simple"
								readonly="true" name="applicationDate" size="20" /></td>

							<td width="20%" colspan="1" class="formtext"><label
								class="set" name="status" id="status"
								ondblclick="callShowDiv(this);"><%=label.get("status")%></label>:<font
								color="red">*</font></td>
							<td width="30%" colspan="1"><s:if test="%{clearListFlag}">
								<s:select theme="simple" name="status" cssStyle="width:130"
									disabled="true"
									list="#{'1':'--Select--','C':'Cleared','N':'Not Cleared' }" />
							</s:if> <s:else>
								<s:select theme="simple" name="status" cssStyle="width:130"
									list="#{'1':'--Select--','C':'Cleared','N':'Not Cleared' }" />
							</s:else></td>

						</tr>


						<tr>
							<td width="20%" colspan="1" class="formtext"><label
								class="set" name="comments" id="comments"
								ondblclick="callShowDiv(this);"><%=label.get("comments")%></label>:<font
								color="red">*</font></td>
							<td width="30%" colspan="3"><s:if test="%{clearListFlag}">
								<s:textarea cols="68" rows="5" readonly="true" name="comments" />
							</s:if> <s:else>
								<s:textarea cols="68" rows="5" name="comments" />

							</s:else></td>

						</tr>


					</table>
					</td>
				</tr>


			</table>
		<tr>
			<td colspan="3">
			<table class="formbg" width="100%">
				<tr>
					<td class="formhead"><strong class="forminnerhead">CheckList</strong></td>
				</tr>
				<tr>
					<td class="formth" align="center" width="10%"><label
						class="set" name="serial.no" id="serial.no1"
						ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
					<td align="center" class="formth" width="40%"><label
						class="set" name="checklist" id="checklist1"
						ondblclick="callShowDiv(this);"><%=label.get("checklist")%></label></td>
					<td align="center" class="formth" width="10%"><label
						class="set" name="isCleared" id="isCleared"
						ondblclick="callShowDiv(this);"><%=label.get("isCleared")%></label></td>
					<td align="center" class="formth" width="40%"><label
						class="set" name="comments" id="comments1"
						ondblclick="callShowDiv(this);"><%=label.get("comments")%></label></td>

				</tr>


				<%
						int i = 0;
						%>
				<%!int g = 1;%>
				<%
						int b = 1;
						%>
				<%
								java.util.HashMap hmChk = (java.util.HashMap) request
								.getAttribute("chkText");
						%>

				<s:iterator value="deptList">
					<tr>
						<td colspan="4" class="formth"><s:property value="department" />

						</td>

					</tr>

					<s:iterator value="list">

						<tr>


							<td width="10%" align="center" class="sortableTD"><s:hidden
								name="departmentCode"></s:hidden> <%=b%><input type="hidden"
								name="srNo" value="<%=b%>" /></td>

							<td class="sortableTD"><s:property value="checkListName" />

							<input type="hidden" name="checkListName"
								id='<%="checkListName"+b%>'
								value='<s:property value="checkListName" />'> <input
								type="hidden" name="checkListCode" id='<%="checkListCode"+b%>'
								value='<s:property value="checkListCode" />'></td>

							<td class="sortableTD" align="center"><s:if
								test="%{deptClearance.clearListFlag}">

								<s:if test="%{ chkSubmitTest}">
									<input type="checkbox" name="chkSubmit" id="chkSubmit<%=i%>"
										disabled="disabled" onclick="callChk(<%=i%>)"
										checked="checked" />
								</s:if>
								<s:else>
									<input type="checkbox" name="chkSubmit" id="chkSubmit<%=i%>"
										disabled="disabled" onclick="callChk(<%=i%>)" />
								</s:else>
							</s:if> <s:else>
								<s:if test="%{chkSubmitTest}">
									<input type="checkbox" name="chkSubmit" id="chkSubmit<%=i%>"
										onclick="callChk(<%=i%>)" checked="checked" />
									<input type="hidden" name="chkSubmitFlag"
										id="chkSubmitFlag<%=i%>"
										value='<s:property value="chkSubmitFlag"/>' />
								</s:if>
								<s:else>
									<input type="checkbox" name="chkSubmit" id="chkSubmit<%=i%>"
										onclick="callChk(<%=i%>)" />
									<input type="hidden" name="chkSubmitFlag"
										id="chkSubmitFlag<%=i%>" value="N" />
								</s:else>
							</s:else></td>

							<td class="sortableTD"><s:if
								test="%{deptClearance.clearListFlag}">

								<s:textarea rows="4" cols="60" value="%{chkListComments}"
									readonly="true" name="chkListComments"
									id='<%="chkListComments"+b%>' />
							</s:if> <s:else>
								<s:textarea rows="4" cols="60" value="%{chkListComments}"
									name="chkListComments" id='<%="chkListComments"+b%>' />
							</s:else></td>


						</tr>
						<%
									i++;
									b++;
							%>
					</s:iterator>

					<%
								g = b;
								b = 1;
						%>

				</s:iterator>



			</table>
			</td>
		</tr>


		<tr>

			<td width="100%" colspan="3"><s:if test="clearListFlag">
			<input type="button" class="token" value="    Back"
				onclick="return callBack();" theme="simple" />
			</s:if> <s:else>
				<input type="button" class="add" value="    Save"
					onclick="return saveValidate(this,'C');" theme="simple" />
					<input type="button" class="token" value="    Back"
				onclick="return callBack();" theme="simple" />

			</s:else>&nbsp;<!--<s:if test = "true"><input type="button"
				class="token" value="    Clear" onclick="return callClear();"
				theme="simple" /></s:if>--></td>




		</tr>
		<s:hidden name="resignCode" />
		<s:hidden name="checkApproveRejectStatus" />

	</table>

</s:form>

<script>

 function callBack()
	{ 
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'DepartmentClearance_backToList.action';
		document.getElementById('paraFrm').submit();
	}
	
 function callClear()
	{
	var comments = document.getElementById('paraFrm_comments').value;
	var status = document.getElementById('paraFrm_status').value ;
	if(comments=="")
	{
	alert("Please enter comments");
	return false;
	}
		
  	if(status == "1"){
  			alert("Please select "+document.getElementById('status').innerHTML.toLowerCase());
  			return false;
  		}		
		
		
		
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'DepartmentClearance_clear.action';
		document.getElementById('paraFrm').submit();
	}	
	
	
	function saveValidate(obj,id)
  {
   
  	try{
    
   	var conf;
  	document.getElementById("paraFrm_checkApproveRejectStatus").value=id; 
  	var comments = document.getElementById('paraFrm_comments').value;
  	var status = document.getElementById('paraFrm_status').value ;
  	
  	
  	if(status == "1"){
  			alert("Please select "+document.getElementById('status').innerHTML.toLowerCase());
  			return false;
  		}
  	
  	
  			if(comments == ""){
  			alert("Please enter "+document.getElementById('comments').innerHTML.toLowerCase());
  			return false;
  		}
  		
  			
       conf=confirm("Do you really want to save this application ?");
      
    	 if(conf)
				{
				 	obj.disabled=true;
			 		document.getElementById("paraFrm").target="main";
			 		document.getElementById("paraFrm").action="DepartmentClearance_saveApplication.action";
					document.getElementById("paraFrm").submit();
		  			window.close();
				 }
				 else
				 {
				 return false; 
  				}
  				
  			}catch(e){alert(e);}	
  		return true; 		
  }
	
	
	
	 function callChk(i)
 {
  	try
 	{
 	   if(document.getElementById('chkSubmit'+i).checked == true)
		   {	  
		    document.getElementById('chkSubmitFlag'+i).value="Y";
		   }
		   else
		   {
		   	document.getElementById('chkSubmitFlag'+i).value="N";
		   	
		   	}
	
	}catch(e){ alert(e);}
	
 	
 }

</script>
