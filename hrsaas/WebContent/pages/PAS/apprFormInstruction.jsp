<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<html>
<head>
<link type="text/css" href="../pages/PAS/PAS-Css/apprFormGeneralInfo.css" rel="stylesheet"  />

</head>
<s:form action="ApprFormInstruction" validate="true" id="paraFrm"
	theme="simple">
		<s:hidden name="source" id="source"/>
	  <table width="100%" border="0" align="right" cellpadding="2" cellspacing="1" class="formbg" >
    
      <div class = "form-header">
				          	<img src="../pages/images/recruitment/review_shared.gif" /> 
				          					          	
				          	<label class = "form-head-label" name="appraisal.form.head" id="appraisal.form.head" ondblclick="callShowDiv(this);">  <%=label.get("appraisal.form.head")%></label>
				         		
	 </div>
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
      <td colspan="3">
          
            <div class="Appraisal-details">
           	 	<s:hidden name="detailFLag"/><s:hidden name="templateCode"/><s:hidden name="phaseForwardFlag"/>
           		<div>
            		<b><label name="appraisal.details" class = "set"  id="appraisal.details" ondblclick="callShowDiv(this);"><%=label.get("appraisal.details")%></label>
            		</b>
            	</div>
            </div>
           <s:hidden name="apprValidTillDate"/>
           <div class = "formWrapper">
            <div class = "col-md-3">
              <b>
              	<label name="appraisal.form.period"  class = "set"  id="appraisal.form.period" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.period")%>
              	</label> :</b>
			  	<s:hidden name="apprId"/><s:hidden name="apprCode"/><s:property value="apprCode"  />
            </div>
            <div class = "col-md-3">
            	<label name="appr.from.date" class = "set"  id="appr.from.date" ondblclick="callShowDiv(this);"><%=label.get("appr.from.date")%></label> :
             	<s:hidden name="apprStartDate"/><s:property value="apprStartDate"  />&nbsp;
            </div>
            <div class = "col-md-3">		
             	 <label name="appr.to.date" class = "set"  id="appr.to.date" ondblclick="callShowDiv(this);"><%=label.get("appr.to.date")%></label> :
                 <s:hidden name="apprEndDate"/><s:property value="apprEndDate" />
            </div> 
            
            <div class = "col-md-3">
              <b><label name="appraisal.form.phase"  class = "set"  id="appraisal.form.phase" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.phase")%></label> :</b>
			  <s:hidden name="phaseCode"/><s:hidden name="phaseName"/><s:property value="phaseName"  />
            </div>    
            <div class = "col-md-3"> 
	             <label name="phase.from.date" class = "set"  id="phase.from.date" ondblclick="callShowDiv(this);"><%=label.get("phase.from.date")%></label> :
    	          <s:hidden name="phaseStartDate"/><s:property value="phaseStartDate" />&nbsp;
            </div> 
            <div class = "col-md-3">
       		      <label name="phase.to.date" class = "set"  id="phase.to.date" ondblclick="callShowDiv(this);"><%=label.get("phase.to.date")%></label> : 
            	  <s:hidden name="phaseEndDate"/><s:property value="phaseEndDate" /><s:hidden name="phaseLockFlag"/><s:hidden name="quesWtDisplayFlag"/>
            </div>  
            
            <div class = "col-md-3">
            	  <b><label name="employee"  class = "set"  id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> :</b>
			  	  <s:hidden name="empId"/><s:hidden name="empName"/><s:property value="empName"  />
             </div>
             <div class = "col-md-3">   
            	  <b><label  name="designation" class = "set"  id="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label> : </b><s:hidden name="empDesgName"/><s:property value="empDesgName" /> 
             </div>
           </div>
      </td>
    </tr>
    <tr>
      <td colspan="5">
          <tr>
            <td>
            <div  class="Appraisal-details">
            	<div>
            	<b>
            		<label  name="appraisal.form.instruction" class = "set"  id="appraisal.form.instruction" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.instruction")%>
            		</label></b>
            	</div>
            </div>	
            
              
              
              <s:hidden name="apprInstr" id="apprInstr"/>
              <div id="apprInstrDiv"></div>
              <script>
					document.getElementById("apprInstrDiv").innerHTML=document.getElementById("apprInstr").value;
			  </script>
              
                
                
            </td>
          </tr>
          
      </td>
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
</html>

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
