<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="java.util.HashMap"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="AppraisalReport" validate="true" id="paraFrm" theme="simple">
	
	<s:hidden name="reportAction" value='AppraisalSummaryReport_genReport.action'/>
<s:hidden name="report" />
 <table width="100%" border="0" align="right" cellpadding="2" cellspacing="2" class="formbg" >
    
    <s:hidden name="frmDate"/>
	<s:hidden name="toDate"/>   
    <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Appraisal Summary Report</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
    </tr>
    <tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" id='topButtonTable'>
				<tr valign="middle">
					<!--  <td>
						<input type="button" class="token"  onclick="resetFun()" value=" Reset "/>
					</td>-->
					<td nowrap="nowrap">
						<a href="#" onclick="callReset();">
							<img src="../pages/images/buttonIcons/Refresh.png" class="iconImage"  align="absmiddle" 
						 title="PDF"> Reset </a>&nbsp;&nbsp;
					</td>
					<td width="100%">
						<%@ include file="/pages/common/reportButtonPanel.jsp"%>

					</td>
				</tr>
			</table>
			</td>
		</tr>
    
    <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
          <tr>
            <td>	
            	<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2">
	                <tr>
		              <td width="15%" colspan="1" height="20" class="formtext">
		              	<label  class = "set" name="appraisal.code"  id="appraisal.code" ondblclick="callShowDiv(this);"><%=label.get("appraisal.code")%></label> <font color="red">*</font> :
		              </td>
					  <td width="30%" colspan="1" height="20">
					  	<s:textfield name="apprCode" size="20" maxlength="25" readonly="true" />
					  	<s:hidden name="apprId" />
							<img
							src="../pages/images/recruitment/search2.gif" height="16"
							align="absmiddle" width="17" theme="simple"
							onclick="javascript:callsF9(500,325,'AppraisalSummaryReport_f9AppCal.action'); 
							">
					 </td> 
					 <td width="55%" colspan="1" height="20"> 
					  <label class = "set" name="appraisal.from"  id="appraisal.from" ondblclick="callShowDiv(this);"><%=label.get("appraisal.from")%></label>:<s:property value="apprFrom"/><s:hidden name="apprFrom" />
					  <label class = "set" name="appraisal.to"  id="appraisal.to" ondblclick="callShowDiv(this);"><%=label.get("appraisal.to")%></label>:<s:property value="apprTo"/><s:hidden name="apprTo" />
					 </td>
		            </tr>
		            
		           
		              <s:hidden name="template"  />
		              <s:hidden name="templateId" />	
		            <!--
		            
		            <tr>
		             <td class="formtext"  colspan="1" height="20" class="formtext">
		             	<label  class = "set" name="appraisal.temp"  id="appraisal.temp" ondblclick="callShowDiv(this);"><%=label.get("appraisal.temp")%></label> :
		             </td>
		             <td  colspan="1" height="20" class="formtext">
		              <s:hidden name="template"  />
		              <s:hidden name="templateId" />					  
		             </td>
		            </tr>
		            
		            
		            --><tr>
		             <td class="formtext"  colspan="1" height="20" class="formtext">
		             	<label  class = "set" name="appraisal.div"  id="appraisal.div" ondblclick="callShowDiv(this);"><%=label.get("appraisal.div")%></label> :
		             </td>
		             <td  colspan="1" height="20" class="formtext">
		              <s:textfield name="division"  readonly="true"/>
		              <s:hidden name="divId" />
					  	<img
							src="../pages/images/recruitment/search2.gif" height="16"
							align="absmiddle" width="17" theme="simple"
							onclick="javascript:callsF9(500,325,'AppraisalSummaryReport_f9Division.action'); 
						">
		             </td>
		            </tr>
		            <tr>
		             <td class="formtext"  colspan="1" height="20" class="formtext">
		             	<label  class = "set" name="appraisal.branch"  id="appraisal.branch" ondblclick="callShowDiv(this);"><%=label.get("appraisal.branch")%></label> :
		             </td>
		             <td  colspan="1" height="20" class="formtext">
		              <s:textfield name="branch" readonly="true" />
		              <s:hidden name="branchId" />
					  	<img
							src="../pages/images/recruitment/search2.gif" height="16"
							align="absmiddle" width="17" theme="simple"
							onclick="javascript:callsF9(500,325,'AppraisalSummaryReport_f9Branch.action'); 
						">		              
		             </td>
		            </tr>
		            
		             <tr>
		             <td class="formtext" colspan="1" height="20" class="formtext">
		             	<label  class = "set" name="appraisal.dept"  id="appraisal.dept" ondblclick="callShowDiv(this);"><%=label.get("appraisal.dept")%></label> :
		             </td>
		             <td  colspan="1" height="20" class="formtext">
		              <s:textfield name="dept" readonly="true" />
		              <s:hidden name="deptId" />
						<img
							src="../pages/images/recruitment/search2.gif" height="16"
							align="absmiddle" width="17" theme="simple"
							onclick="javascript:callsF9(500,325,'AppraisalSummaryReport_f9Dept.action'); 
						">
		             </td>
		            </tr>
		            
		            <tr>
		             <td class="formtext"  colspan="1" height="20" class="formtext">
		             	<label  class = "set" name="appraisal.desg"  id="appraisal.desg" ondblclick="callShowDiv(this);"><%=label.get("appraisal.desg")%></label> :
		             </td>
		             <td  colspan="1" height="20" class="formtext">
		              <s:textfield name="desg" readonly="true" />
		              <s:hidden name="desgId" />
					  	<img
							src="../pages/images/recruitment/search2.gif" height="16"
							align="absmiddle" width="17" theme="simple"
							onclick="javascript:callsF9(500,325,'AppraisalSummaryReport_f9Desg.action'); 
						">		              
		             </td>
		            </tr>
		            <!-- 
		            <tr>
		             <td class="formtext"  colspan="1" height="20" class="formtext">
		              <label  class = "set" name="appraisal.rpt.type"  id="appraisal.rpt.type" ondblclick="callShowDiv(this);"><%=label.get("appraisal.rpt.type")%></label> :
		             </td> 
		             <td colspan="1" height="20" class="formtext">
		              <s:select  name="rpttype" list="#{'0':'Phase wise','1':'Section wise'}"  />
		             </td>
		            </tr>
		             -->
		            
		            <!--          
	            		<tr>
			               	<td width="25%" colspan="1" height="20" class="formtext"><label  class = "set" name="employee"  id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> :</td>
						   	<td width="25%" colspan="2" height="20" ><s:textfield name="empToken" size="10" readonly="true" /><s:textfield name="empName" readonly="true" size="25" /><img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="return callEmployee();"></td> 
						   	<td width="25%" height="20"><s:hidden name="empCode"/><s:hidden name="branch"/><s:hidden name="desg"/>  </td>
	           			</tr>
           			 -->
            	</table>
            </td>
          </tr>
      </table>
     </td>
   </tr>
    
 <tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" id='topButtonTable'>
				<tr valign="middle">
					<!--  <td>
						<input type="button" class="token"  onclick="resetFun()" value=" Reset "/>
					</td>-->
					<td nowrap="nowrap">
						<a href="#" onclick="callReset();">
							<img src="../pages/images/buttonIcons/Refresh.png" class="iconImage"  align="absmiddle" 
						 title="PDF"> Reset </a>&nbsp;&nbsp;
					</td>
					<td width="100%">
						<%@ include file="/pages/common/reportButtonPanel.jsp"%>
					</td>
				</tr>
			</table>
			</td>
		
		</tr>
		<tr>
			<td>
		<div name="htmlReport" id='reportDiv'
			style="background-color: #FFFFFF;  height: 400; width: 100%; display: none;border-top: 1px #cccccc solid;">
		<iframe id="reportFrame" frameborder="0" onload=alertsize();
			style="vertical-align: top; float: left; border: 0px solid;"
			allowtransparency="yes" src="../pages/common/loading.jsp" scrolling="auto"
			marginwidth="0" marginheight="0" vspace="0" name="htmlReport"
			width="100%" height="200"></iframe> </div>
		</td>
		</tr>
	</table>
	</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js">

</script>


<script type="text/javascript">

	function resetFun(){
	 
	 document.getElementById('paraFrm').action="AppraisalSummaryReport_reset.action";
	 document.getElementById('paraFrm').submit();
	 
	}
	
	function callEmployee(){
			if(document.getElementById("paraFrm_apprCode").value==""){
				alert("Please select "+document.getElementById("appraisal.code").innerHTML);
				document.getElementById("paraFrm_apprCode").focus();
				return false;
			}
			
			javascript:callsF9(500,325,'AppraisalReport_f9Employee.action'); 
	}
	function callCalendar(){
			
			document.getElementById("paraFrm_empCode").value="";
			document.getElementById("paraFrm_empName").value="";
			document.getElementById("paraFrm_empToken").value="";
			
			javascript:callsF9(500,325,'AppraisalReport_f9AppCal.action'); 
	}
	function reportFun(){
			var fields = ["paraFrm_apprCode"];
			var labels = [document.getElementById("appraisal.code").innerHTML]
			var types = ["select"];
			if(!(checkMandatory(fields,labels,types))){
				return false;
		}
		
			callReport('AppraisalSummaryReport_getReport.action');
		}
		
		
function callReport(type){

	var fields = ["paraFrm_apprCode"];
			var labels = [document.getElementById("appraisal.code").innerHTML]
			var types = ["select"];
			if(!(checkMandatory(fields,labels,types))){
				return false;
		}
		document.getElementById('paraFrm_report').value=type; 
		callReportCommon(type);		
}
function mailReportFun(type){
var fields = ["paraFrm_apprCode"];
			var labels = [document.getElementById("appraisal.code").innerHTML]
			var types = ["select"];
			if(!(checkMandatory(fields,labels,types))){
				return false;
		}
		document.getElementById('paraFrm_report').value=type;
		document.getElementById('paraFrm').action='AppraisalSummaryReport_mailReport.action';
		document.getElementById('paraFrm').submit();
			
		}
	
function callReset(){
	
	document.getElementById('paraFrm_apprCode').value="";
	document.getElementById('paraFrm_apprId').value="";
	document.getElementById('paraFrm_template').value="";
	document.getElementById('paraFrm_templateId').value="";
	document.getElementById('paraFrm_division').value="";
	document.getElementById('paraFrm_divId').value="";
	document.getElementById('paraFrm_branch').value="";
	document.getElementById('paraFrm_branchId').value="";
	document.getElementById('paraFrm_dept').value="";
	document.getElementById('paraFrm_deptId').value="";
	document.getElementById('paraFrm_desg').value="";
	document.getElementById('paraFrm_desgId').value="";
	
}
		
</script>
