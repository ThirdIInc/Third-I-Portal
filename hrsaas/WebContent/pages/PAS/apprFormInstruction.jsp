<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="ApprFormInstruction" validate="true" id="paraFrm"
	theme="simple">
		<s:hidden name="source" id="source"/>
	  <table width="100%" border="0" align="right" cellpadding="2" cellspacing="1" class="formbg" >
    
    <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head"><label name="appraisal.form.head" class = "set"  id="appraisal.form.head" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.head")%></label></strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
    <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="1">
         <tr>
            <td width="78%">
            <jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
          			
		</td>
            <td width="22%"><div align="right"><font color="red">*</font> Indicates Required </div></td>
          </tr>
        </table>
          </td>
    </tr>
    <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="0" class="formbg">
          <tr>
            <td><s:hidden name="detailFLag"/><s:hidden name="templateCode"/><s:hidden name="phaseForwardFlag"/><b><label name="appraisal.details" class = "set"  id="appraisal.details" ondblclick="callShowDiv(this);"><%=label.get("appraisal.details")%></label></b><table width="98%" border="0" align="center" cellpadding="2" cellspacing="2">
              <s:hidden name="apprValidTillDate"/>
            <tr>
              <td width="18%" colspan="1" height="20" class="formtext"><b><label name="appraisal.form.period"  class = "set"  id="appraisal.form.period" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.period")%></label> :</b></td>
			  <td width="15%" colspan="1" height="20"><s:hidden name="apprId"/><s:hidden name="apprCode"/><s:property value="apprCode"  /></td>
              <td width="10%" colspan="1" height="20"></td>   
              <td width="50%" colspan="1" height="20">
              <label name="appr.from.date" class = "set"  id="appr.from.date" ondblclick="callShowDiv(this);"><%=label.get("appr.from.date")%></label> :
              	<s:hidden name="apprStartDate"/><s:property value="apprStartDate"  />&nbsp;
              <label name="appr.to.date" class = "set"  id="appr.to.date" ondblclick="callShowDiv(this);"><%=label.get("appr.to.date")%></label> :
              	<s:hidden name="apprEndDate"/><s:property value="apprEndDate" /></td> 
            </tr>
             <tr>
              <td width="18%" colspan="1" height="20" class="formtext"><b><label name="appraisal.form.phase"  class = "set"  id="appraisal.form.phase" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.phase")%></label> :</b></td>
			  <td width="15%" colspan="1" height="20"><s:hidden name="phaseCode"/><s:hidden name="phaseName"/><s:property value="phaseName"  /></td>
              <td width="10%" colspan="1" height="20"></td>   
              <td width="50%" colspan="1" height="20">
             <label name="phase.from.date" class = "set"  id="phase.from.date" ondblclick="callShowDiv(this);"><%=label.get("phase.from.date")%></label> :
              <s:hidden name="phaseStartDate"/><s:property value="phaseStartDate" />&nbsp;
             <label name="phase.to.date" class = "set"  id="phase.to.date" ondblclick="callShowDiv(this);"><%=label.get("phase.to.date")%></label> : 
              <s:hidden name="phaseEndDate"/><s:property value="phaseEndDate" /><s:hidden name="phaseLockFlag"/><s:hidden name="quesWtDisplayFlag"/></td> 
            </tr>
            <tr>
              <td width="18%" colspan="1" height="20" class="formtext"><b><label name="employee"  class = "set"  id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> :</b></td>
			  <td width="15%" colspan="1" height="20"><s:hidden name="empId"/><s:hidden name="empName"/><s:property value="empName"  /></td>
              <td width="10%" colspan="1" height="20"></td>   
              <td width="50%" colspan="1" height="20"><b><label  name="designation" class = "set"  id="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label> : </b><s:hidden name="empDesgName"/><s:property value="empDesgName" /></td> 
            </tr>
                           
            </table></td>
          </tr>
          
      </table></td>
    </tr>
    <tr>
      <td colspan="5"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
          <tr>
            <td><b><label  name="appraisal.form.instruction" class = "set"  id="appraisal.form.instruction" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.instruction")%></label></b><table width="98%" border="0" align="center" cellpadding="2" cellspacing="2">
              <tr>
              <td><s:hidden name="apprInstr" id="apprInstr"/>
              <div id="apprInstrDiv"></div>
              <script>
					document.getElementById("apprInstrDiv").innerHTML=document.getElementById("apprInstr").value;
			</script>
              </td>
                </tr>
                
            </table></td>
          </tr>
          
      </table></td>
    </tr>
    
    
 <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2">
         <tr>
            <td width="78%">
            <jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
          			
		</td>
            
          </tr>
        </table>
          </td>
    </tr>
 
 
 
 </table>

  <label></label>
	
	
	</s:form>


<script type="text/javascript">

function nextFun(){
		
	
		document.getElementById("paraFrm").action="ApprFormSection_input.action";
		document.getElementById("paraFrm").submit();
		
}

function previousFun(){
		
			document.getElementById("paraFrm").action="ApprFormGeneralInfo_retrieveDetails.action";
			document.getElementById("paraFrm").submit();
		
		
				
}

</script>
