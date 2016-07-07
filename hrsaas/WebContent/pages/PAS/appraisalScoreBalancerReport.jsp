
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri='/WEB-INF/cewolf-1.1.tld' prefix='cewolf' %>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="ScoreBalancerReportForm" validate="true" id="paraFrm" theme="simple">
 <s:hidden name="reportAction" value='AppraisalScoreBalancerReportAction_genReport.action'/>
<s:hidden name="report" />
 
 <table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg" >
	<tr>
		<td colspan="3">
		<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
			<tr>
				<td>
				<table width="98%" border="0" align="center" cellpadding="2" cellspacing="1">

					<tr>
						<td valign="bottom" class="txt"><strong class="formhead"><img
							src="../pages/images/review_shared.gif" width="25" height="25" /></strong></td>
						<td width="93%" class="txt">
							<strong class="text_head">
							<label  class = "set" name="scorebalancerreport.heading"  id="scorebalancerreport.heading" ondblclick="callShowDiv(this);"><%=label.get("scorebalancerreport.heading")%></label> 
							</strong>
						</td>
						<td width="3%" valign="top" class="txt">
						<div align="right"><img src="../pages/images/help.gif"
							width="16" height="16" /></div>
						</td>
					</tr>

				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	  
	<tr>
      <td colspan="3">
      	<table width="100%" border="0" cellpadding="2" cellspacing="1">
         <s:if test="isApprove"></s:if><s:else>
           <tr>
           <td width="78%">
           		<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
		   </td>
           <td width="22%"><div align="right"><font color="red">*</font> Indicates Required </div></td>
          </tr>
         </s:else>
        </table>
      </td>
    </tr>
    
 	
 	<tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
          <tr>
            <td><table width="98%" border="0" align="center" cellpadding="2" cellspacing="2">
              
            <tr>
              <s:hidden name="sAppId"></s:hidden>
              <s:hidden name="sAppStartDate"></s:hidden>
              <s:hidden name="sAppEndDate"></s:hidden>
              <td width="10%" colspan="1" height="20" class="formtext" nowrap="nowrap">
              	<label  class = "set" name="scorebalancerreport.appraisalcode"  id="scorebalancerreport.appraisalcode" ondblclick="callShowDiv(this);"><%=label.get("scorebalancerreport.appraisalcode")%></label> 
              	<font color="red"> * </font> :
              </td>
			  <td width="40%" colspan="1" height="20" align="left"><s:textfield name="sAppCode" size="25" readonly="true" /><img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="17" theme="simple"
						onclick="javascript:callsF9(500,325,'AppraisalScoreBalancerReport_f9SelectAppraisal.action'); "></td>
              <td width="25%" colspan="1" height="20" class="formtext"></td>
			  <td width="25%" colspan="1" height="20"></td> 
            </tr>
        
            </table></td>
          </tr>
          
      </table></td>
    </tr>
      
    <tr>
      <td colspan="3">
      	<table width="100%" border="0" cellpadding="2" cellspacing="1">
         <s:if test="isApprove"></s:if><s:else>
           <tr>
           <td width="78%">
           		<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
		   </td>
            <td width="22%"><div align="right">&nbsp;</td>
          </tr>
         </s:else>
        </table>
      </td>
    </tr>
    
</table>
</s:form>

<script type="text/javascript">

	function reportFun()
	{
		var fields = ["paraFrm_sAppCode"];
		var labels = [document.getElementById("scorebalancerreport.appraisalcode").innerHTML]
		var types = ["select"];
		if(!(checkMandatory(fields,labels,types)))
		{
			return false;
		}
		
		callReport('AppraisalScoreBalancerReport_genReport.action');
		document.getElementById("paraFrm_sAppCode").value = '';
	}

	function resetFun()
	{
		document.getElementById('paraFrm_sAppCode').value="";
	}
</script>