<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<%@page import="org.paradyne.bean.PAS.*"%>

<% ApprFormGeneralInfo aprInfo = new ApprFormGeneralInfo(); %>
<html>
<head>
<link type="text/css" href="../pages/PAS/PAS-Css/apprFormGeneralInfo.css" rel="stylesheet"  />
</head>
<body>

<s:form action="ApprFormGeneralInfo" validate="true" id="paraFrm" theme="simple">
	<s:hidden name="source" id="source"/>
		
	  <table width="100%" border="0" align="right" cellpadding="2" cellspacing="1" class="formbg" >
	        			
				          <div class = "form-header">
				          	<img src="../pages/images/recruitment/review_shared.gif" /> 
				          					          	
				          	<label class = "form-head-label" name="appraisal.form.head" id="appraisal.form.head" ondblclick="callShowDiv(this);">  <%=label.get("appraisal.form.head")%></label>
				         		
				          </div>
    <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="0">
         <tr>
            <td width="78%">
            <jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
          			
		</td>
            
          </tr>
        </table>
          </td>
    </tr>
    <tr>
      <td colspan="3" width="100%">
         
            <div class="Appraisal-details">
            	<s:hidden name="phaseForwardFlag"/><s:hidden name="detailFLag"/><s:hidden name="instrVisibilty"/><s:hidden name="templateCode"/>
            	<div>
            	<b> 
            		<span><i class="fa fa-info-circle" aria-hidden="true"></i></span>
            		<label name="appraisal.details" class = "Appraisal-details"  id="appraisal.details"><%=label.get("appraisal.details")%></label>
            	</b>
            	</div>
            
              	<s:hidden name="apprValidTillDate"/><s:hidden name="ratingDefined"/>
           	</div>	
           	<div class = "formWrapper">
              	<div class="col-md-4">
              	<b>
              		<label name="appraisal.form.period"  class = "set"  id="appraisal.form.period" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.period")%>
          			 </label> :</b>
             
			  		<s:hidden name="apprId"/><s:hidden name="apprCode"/><s:property value="apprCode"  />
			   </div>
                
              <div class= "col-md-4">
              		<label name="appr.from.date" class = "set"  id="appr.from.date" ondblclick="callShowDiv(this);"><%=label.get("appr.from.date")%></label> : 
              		<s:hidden name="apprStartDate"/><s:property value="apprStartDate"  />&nbsp;
              		<label name="appr.to.date" class = "set"  id="appr.to.date" ondblclick="callShowDiv(this);"><%=label.get("appr.to.date")%></label> : 
               		<s:hidden name="apprEndDate"/><s:property value="apprEndDate" /> 
              </div>
            
             
             <div class="col-md-4"> 
             	<b>
             	<label name="appraisal.form.phase" class = "set"  id="appraisal.form.phase" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.phase")%>
             	</label> :</b>
			  	<s:hidden name="phaseCode"/><s:hidden name="phaseName"/><s:property value="phaseName"  />
             </div>
             
             <div class= "col-md-4">
	             <label name="phase.from.date" class = "set"  id="phase.from.date" ondblclick="callShowDiv(this);"><%=label.get("phase.from.date")%></label> : 
    	       	 <s:hidden name="phaseStartDate"/><s:property value="phaseStartDate" />&nbsp;
              	<label name="phase.to.date" class = "set"  id="phase.to.date" ondblclick="callShowDiv(this);"><%=label.get("phase.to.date")%></label> :
              	<s:hidden name="phaseEndDate"/><s:property value="phaseEndDate" /><s:hidden name="phaseLockFlag"/><s:hidden name="quesWtDisplayFlag"/>
              </div>
           </div>
          
      </td>
    </tr>
    
      <td colspan="5" width="100%">
          <div  class="Appraisal-details">
          
           
            	<div>
            	<b>
            		<span><i class="fa fa-info-circle" aria-hidden="true"></i></span>
            		<label name="appraisal.details" class = "Appraisal-details"  id="appraisal.details">Employee Details</label>
            	</b>
            	</div>
            
           </div>
            	<div class = "formWrapper">
              		<div class = "col-md-3">
              			<label  name="employee.id" class = "set"  id="employee.id"><%=label.get("employee.id")%></label> :
			  			<s:hidden name="empId"></s:hidden><s:property value="empCode"  />
			  		</div>
					<div class = "col-md-3">
             			<label  name="employee" class = "set"  id="employee" ><%=label.get("employee")%></label> :   
              			<s:hidden name="empName"/><s:property value="empName" />
              		</div> 
            
            
           		 	<div class = "col-md-3">
              			<label  name="branch" class = "set"  id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :
			  			<s:property value="empBrnName"  />
			  		</div>
			  		<div class = "col-md-3">
              			<label name="department"  class = "set"  id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> :   
              			<s:property value="empDeptName" />
              		</div>
            
            
             		<div class = "col-md-3">
            			<label  name="designation" class = "set"  id="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label> :
			  			<s:hidden name="empDesgName"/><s:property value="empDesgName"  />
			  		</div>
			  		<div class = "col-md-3">
              			<label name="reporting.to"  class = "set"  id="reporting.to" ondblclick="callShowDiv(this);"><%=label.get("reporting.to")%></label> :   
              			<s:property value="empReportingTo" />
             		 </div>
            
            
           			<!-- <div class = "col-md-3">
            		 	 <label name="doj" class = "set"  id="doj" ondblclick="callShowDiv(this);"><%=label.get("doj")%></label> :
			 		 	<s:property value="empDoj"  /> 
              		</div>-->
              		<div class = "col-md-3">
              			<label  class = "set" name="appraisal.form.appr.date"  id="appraisal.form.appr.date" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.appr.date")%></label> :   
              			<s:hidden name="apprDate" /><s:property value="phaseStartDate" />
            		</div>
            			<label  class = "set" name="appraisal.valid.date"  id="appraisal.valid.date" style="visibility: hidden"><%=label.get("appraisal.valid.date")%></label>
                  </div>
           
          
          
     </td>
   
    
    <tr>
      <td colspan="3" width="100%">
          
           
            <div class = "Appraisal-details">
            	<b>
            	<span><i class="fa fa-info-circle" aria-hidden="true"></i></span>
            	<label name="appraisal.form.appraiser" class = "set"  id="appraisal.form.appraiser" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.appraiser")%></label>
            	</b>
            </div>
            <div class = "tabledetails">
            <table width="98%" border="0" align="center" cellpadding="2" cellspacing="2">
              <tr>
					<td class="formth" width="5%"><label  class = "set" name="appraisal.form.appraiser.srno"  id="appraisal.form.appraiser.srno" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.appraiser.srno")%></label></td>
					<td class="formth" width="25%" align="left"><label  name="appraisal.form.appraiser.name" class = "set"  id="appraisal.form.appraiser.name" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.appraiser.name")%></label></td>
					<td class="formth" width="20%" align="left"><label  name="appraisal.form.appraiser.desgname" class = "set"  id="appraisal.form.appraiser.desgname" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.appraiser.desgname")%></label></td>
					<td class="formth" width="20%" align="left"><label  name="appraisal.form.appraiser.phase" class = "set"  id="appraisal.form.appraiser.phase" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.appraiser.phase")%></label></td>
					<td class="formth" width="15%" align="left"><label  name="appraisal.form.appraiser.level" class = "set"  id="appraisal.form.appraiser.level" ondblclick="callShowDiv(this);"><%=label.get("appraisal.form.appraiser.level")%></label></td>
				</tr>
							<%int j = 1 , c=0;%>
							<s:iterator value="appraiserList">
								<% if(aprInfo.getAppraiserName() != null || aprInfo.getAppraiserName() != "" ){%>
								<tr>
									<td width="5%" align="center" class="td_bottom_border"><%=j%></td>
									<td width="25%" align="left" class="td_bottom_border"><s:property value="appraiserName" />&nbsp;</td>
									<td width="20%" align="left" class="td_bottom_border"><s:property value="appraiserDesgName"/>&nbsp;</td>									
									<td width="20%" align="left" class="td_bottom_border"><s:property value="appraiserPhaseName" /></td>
									<td width="15%" align="center" class="td_bottom_border"><s:property value="appraiserLevel" /></td>					
							<%j++; c++;%><%System.out.println("----------" +j); %><%System.out.println("----------its c" +c); %>
								 </tr>
								 <%} %>
							</s:iterator>
							<tr><td><input type="hidden" name="count" id="count" value="<%=c%>"/></td></tr>         
      </table></div>
 
   
  
 </td></tr>
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
	</body>
</html>

<script type="text/javascript" src="../pages/common/datetimepicker.js">

</script>


<script type="text/javascript">
var fieldName  = ["paraFrm_apprId"];
var lableName  = [document.getElementById("appraisal.form.period").innerHTML.toLowerCase()];
var flag	   = ["select"];

//var fieldNameAppr = ["paraFrm_apprStartDate","paraFrm_apprEndDate","paraFrm_apprDate"];
//var lableNameAppr = ["appr.from.date","appr.to.date","appraisal.form.appr.date"];

//var fieldNameApprValid = ["paraFrm_apprStartDate","paraFrm_apprValidTillDate","paraFrm_apprDate"];
//var lableNameApprValid = ["appr.from.date","appraisal.valid.date","appraisal.form.appr.date"];

var fieldNamePhase = ["paraFrm_phaseStartDate","paraFrm_phaseEndDate","paraFrm_apprDate"];
var lableNamePhase = ["phase.from.date","phase.to.date","appraisal.form.appr.date"];

var fieldNameValid = ["paraFrm_phaseStartDate","paraFrm_apprValidTillDate","paraFrm_apprDate"];
var lableNameValid = ["phase.from.date","appraisal.valid.date","appraisal.form.appr.date"];



function searchFun(){
		
		callsF9(500,325,'ApprFormGeneralInfo_searchAppraisal.action');
						
}
function nextFun(){
						
			//alert("lock flag---"+document.getElementById("paraFrm_phaseLockFlag").value);
			//alert("phase forward flag---"+document.getElementById("paraFrm_phaseForwardFlag").value);
			//alert("phase code---"+document.getElementById("paraFrm_phaseCode").value);
			//alert("emp code---"+document.getElementById("paraFrm_empName").value);	
			//alert("template code---"+document.getElementById("paraFrm_templateCode").value);
			//alert("appraiser length---"+document.getElementById("count").value);
			//alert("valid till date---"+document.getElementById("paraFrm_apprValidTillDate").value);					
			
			if(document.getElementById("paraFrm_phaseForwardFlag").value == "false"){
			
										
					if(!checkMandatory(fieldName, lableName, flag))
						return false;
								
					if(document.getElementById("paraFrm_phaseCode").value == ""){
						alert("Phases not defined for this Appraisal.");
						return false;
					}
					if(document.getElementById("paraFrm_empName").value == ""){
						alert("You are not eligible for this Appraisal.");
						return false;
					}	
					if(document.getElementById('paraFrm_templateCode').value == ""){
						alert("Template not defined for this Employee.");
						return false;
					}	
					if(document.getElementById("count").value == 0){
						alert("Appraisers not defined for this Employee.");
						return false;
					}	
					
					//alert("phase lock after end date"+document.getElementById("paraFrm_phaseLockFlag").value);
					if(document.getElementById("paraFrm_phaseLockFlag").value == 'Y')	
					{
					//alert("phase lock after end date");
					//	if(!dateBetweenTwoDates(fieldNameAppr, lableNameAppr)){
					//			return false;
					//	}
					
						if(!dateBetweenTwoDates(fieldNamePhase, lableNamePhase)){
								return false;
							}
					}else{
					//alert("phase not locking  after end date");
					//if(!dateBetweenTwoDates(fieldNameApprValid, lableNameApprValid)){
					//		return false;
					//}
						
						if(!dateBetweenTwoDates(fieldNameValid, lableNameValid)){
								return false;
							}
					}
					if(document.getElementById("paraFrm_ratingDefined").value == 'false'){
						alert("Rating scale not defined for this appraisal.");
						return false;
					}
				
				}	
				//alert("instruction visibility------"+document.getElementById('paraFrm_instrVisibilty').value);	
				var instructionVisibility = document.getElementById('paraFrm_instrVisibilty').value;
					
				if(instructionVisibility == "Y"){
			
					document.getElementById("paraFrm").action="ApprFormInstruction_input.action";
					document.getElementById("paraFrm").submit();
				}else{
					
					document.getElementById("paraFrm").action="ApprFormSection_input.action";
					document.getElementById("paraFrm").submit();
				}
				
}
function backFun(){
					if(document.getElementById('source').value=='mymessages')
					{
					document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
					}
					else if(document.getElementById("paraFrm_detailFLag").value == 'true'){
						document.getElementById("paraFrm").action="EvaluatorPanel_input.action";
					}else{
						document.getElementById("paraFrm").action="ApprFormStart_input.action";
					}
					document.getElementById("paraFrm").submit();
						
}
function sendbacktoemployeeFun(){
		
					document.getElementById("paraFrm").action="ApprFormGeneralInfo_sendBackToApplicant.action";
					document.getElementById("paraFrm").submit();
						
}

</script>
