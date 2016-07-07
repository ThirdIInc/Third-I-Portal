<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="java.util.HashMap"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="CompetencyMonitoring" validate="true" id="paraFrm" theme="simple">
<table width="100%" border="0" align="right" cellpadding="2" cellspacing="2" class="formbg" >
	    <tr>
	        <td colspan="3" width="100%">
	        	<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
	        		<tr>
				         <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Competency Monitoring</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				     </tr>
	        	</table>
	        </td>
        </tr>
        <tr>
     		 <td colspan="3">
     		 	<table width="100%" border="0" cellpadding="2" cellspacing="2">
                  <tr>
            		<td width="78%"></td>
            		<td width="22%"><div align="right"><font color="red">*</font> Indicates Required </div></td>
          		  </tr>
        		</table>
          	</td>
   		 </tr>
   		 <tr>
	      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
	            <tr>
	              <td width="20%" colspan="1" height="20" class="formtext"><label  class = "set" name="compMonit.competencyName"  id="compMonit.competencyName" ondblclick="callShowDiv(this);"><%=label.get("compMonit.competencyName")%></label> <font color="red">*</font> :</td>
				  <td width="30%" colspan="1" height="20"><s:textfield name="compName" size="20" maxlength="25" readonly="true" /><s:hidden name="compId" /><s:hidden name="compFrmDate"/><s:hidden name="compToDate"/><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="javascript:callCompetency();"></td>
	               <td width="20%" colspan="1" height="20" class="formtext"><label  class = "set" name="compMonit.reviewDate"  id="compMonit.reviewDate" ondblclick="callShowDiv(this);"><%=label.get("compMonit.reviewDate")%></label> <font color="red">*</font> :</td>
				  <td width="30%" colspan="1" height="20"><s:textfield name="assessmentDate" size="20" maxlength="25" readonly="true" /><s:hidden name="assessmentId" /><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="javascript:callReviewDate();"></td>

	            </tr>
	         </table></td>
	       </tr>
	        <tr>
	      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
	            <tr>
	              <td width="20%" colspan="1" height="20" class="formtext"><label  class = "set" name="compMonit.empName"  id="compMonit.empName" ondblclick="callShowDiv(this);"><%=label.get("compMonit.empName")%></label> <font color="red">*</font> :</td>
				  <td width="30%" colspan="1" height="20"><s:textfield name="compEmpName" size="20" maxlength="25" readonly="true" /><s:hidden name="compEmpToken"/><s:hidden name="compEmpId"/><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="javascript:callSelectEmployee();"></td>
	               <td width="50%" colspan="1" height="20" class="formtext">&nbsp;</td>
				  

	            </tr>
	         </table></td>
	       </tr>
	   		 <tr>
	      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
	            <tr>
	              <td width="20%" colspan="1" height="20" class="formtext"><label  class = "set" name="compMonit.sendbackto"  id="compMonit.sendbackto" ondblclick="callShowDiv(this);"><%=label.get("compMonit.sendbackto")%></label> <font color="red">*</font> :</td>
				  <td width="30%" colspan="1" height="20"><s:textfield name="sendbackEmpName" size="20" maxlength="25" readonly="true" /><s:hidden name="sendbackEmpName"/><s:hidden name="sendbackEmpId"/><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="javascript:callsF9(500,325,'CompetencyMonitoring_f9SendbackEmpAction.action');"></td>
	               <td width="50%" colspan="1" height="20" class="formtext"><input type="button" value="Process" onclick="callSendBackEmp();"></td>
				  

	            </tr>
	         </table></td>
	       </tr>
   		 </table>
   		 
</s:form>
<script>
	function callCompetency(){
			document.getElementById("paraFrm_assessmentDate").value="";
			document.getElementById("paraFrm_assessmentId").value="";
			document.getElementById("paraFrm_compEmpName").value="";
			document.getElementById("paraFrm_compEmpId").value="";
			document.getElementById("paraFrm_compEmpToken").value="";
			
			javascript:callsF9(500,325,'CompetencyMonitoring_f9CompAction.action'); 
	}
	function callReviewDate(){
			if(document.getElementById("paraFrm_compId").value=="")
			{
				alert("Please select competency");
				return false;
			}			
			document.getElementById("paraFrm_compEmpName").value="";
			document.getElementById("paraFrm_compEmpId").value="";
			document.getElementById("paraFrm_compEmpToken").value="";
			
			javascript:callsF9(500,325,'CompetencyMonitoring_f9AssessmentDate.action'); 
	}
	function callSelectEmployee(){
			if(document.getElementById("paraFrm_compId").value=="")
			{
				alert("Please select competency");
				return false;
			}			
			
			
			javascript:callsF9(500,325,'CompetencyMonitoring_f9CompEmpAction.action'); 
	}
	function callSendBackEmp()
	{
		if(document.getElementById("paraFrm_compId").value=="")
			{
				alert("Please select competency");
				return false;
			}
			if(document.getElementById("paraFrm_assessmentId").value=="")
			{
				alert("Please select review date");
				return false;
			}
			if(document.getElementById("paraFrm_compEmpId").value=="")
			{
				alert("Please select employee");
				return false;
			}
			if(document.getElementById("paraFrm_sendbackEmpId").value=="")
			{
				alert("Please select send back competency to");
				return false;
			}
	
		var conf = confirm('Do you really want to add selected employee to send back process ?');
			if(conf){
				document.getElementById('paraFrm').target="main";
				document.getElementById("paraFrm").action="CompetencyMonitoring_sendbackCompetency.action";
		  		document.getElementById("paraFrm").submit();
		  	}
	}
</script>