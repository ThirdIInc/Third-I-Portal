<%@ taglib prefix="s" uri="/struts-tags"%>

<%@include file="/pages/common/labelManagement.jsp"%>
<script language="JavaScript" type="text/javascript"
	src="include/javascript/sorttable.js"></script>


<s:form action="CeoMessageList" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">CEO
					Message </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>

			<td width="100%" colspan="3">
			
			<s:if test="showFlag">
			
			<input type="button" class="token"
				value="    Accept" onclick="return checkAppStatus(this,'A');"
				theme="simple" /> <input type="button" class="token"
				value="    Reject" onclick="return checkAppStatus(this,'R');"
				theme="simple" />
				</s:if>
				 <s:submit name="cancelBtn" value=" Cancel"
				cssClass="token" action="CeoMessageList_callstatus"></s:submit></td>

		</tr>


		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<tr>
							<td colspan="5" class="text_head"><strong
								class="forminnerhead">Employee Details </strong></td>
						</tr>

						<tr>
							<td width="20%" colspan="1"><label name="employee"
								id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:</td>
							<td width="80%" colspan="3"><s:property
								value="employeeToken" /> <s:property value="employeeName" />
								 <s:hidden name="employeeCode"   />
								</td>
						</tr>
						<tr>
							<td width="20%" colspan="1"><label name="division"
								id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:</td>
							<td width="30%" colspan="1"><s:property value="divisionName" />
							</td>
							<td width="20%" colspan="1"><label name="department"
								id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:</td>
							<td width="30%" colspan="1"><s:property value="deptName" />
							</td>
						</tr>
						<tr>
							<td width="20%" colspan="1"><label name="subject"
								id="subject9" ondblclick="callShowDiv(this);"><%=label.get("subject")%></label>:</td>
							<td width="80%" colspan="3"><s:property value="subjectName" />
							<s:hidden name="subjectName"></s:hidden>
							</td>
						</tr>

						<tr>
							<td width="20%" colspan="1"><label name="desc" id="desc"
								ondblclick="callShowDiv(this);"><%=label.get("desc")%></label>:</td>
							<td width="80%" colspan="3"><s:property value="descName" />
							<s:hidden name="descName"></s:hidden>
							</td>
						</tr>

						<tr>
							<td width="20%" colspan="1"><label name="identity"
								id="identity" ondblclick="callShowDiv(this);"><%=label.get("identity")%></label>:</td>
							<td width="80%" colspan="3"><s:property value="showIdentity" />
							<s:hidden name="showIdentity"></s:hidden>
							</td>
						</tr>

					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>

	<tr>

			<td width="100%" colspan="3">
			
			<s:if test="showFlag">
			
			<input type="button" class="token"
				value="    Accept" onclick="return checkAppStatus(this,'A');"
				theme="simple" /> <input type="button" class="token"
				value="    Reject" onclick="return checkAppStatus(this,'R');"
				theme="simple" />
				</s:if>
				 <s:submit name="cancelBtn" value=" Cancel"
				cssClass="token" action="CeoMessageList_callstatus"></s:submit></td>

		</tr>

	</table>

	<s:hidden name="checkAcceptRejectStatus" /> 

	<s:hidden name="hiddenMessageCode" /> 

</s:form>

<script>

  
  function checkAppStatus(obj,id)
  {
   
  	try{
  	 
   	var conf;
  	document.getElementById("paraFrm_checkAcceptRejectStatus").value=id; 
  	if(document.getElementById("paraFrm_checkAcceptRejectStatus").value=="A")
       conf=confirm("Do you really want to accept this message ?");
      if(document.getElementById("paraFrm_checkAcceptRejectStatus").value=="R")
       conf=confirm("Do you really want to reject this message ?");
        
 		 if(conf)
				{
				 	obj.disabled=true;
			 		document.getElementById("paraFrm").target="main";
					document.getElementById("paraFrm").action="CeoMessageList_acceptRejectMessage.action";
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


</script>