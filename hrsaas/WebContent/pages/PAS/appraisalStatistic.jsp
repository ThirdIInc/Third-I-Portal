<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="java.util.HashMap"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="AppraisalReport" validate="true" id="paraFrm" theme="simple">
<s:hidden name="report" />
 <s:hidden name="reportAction" value='AppraisalStatistic_getReport.action' />	
	
 <table width="100%" border="0" align="right" cellpadding="2" cellspacing="2" class="formbg" >
    
    <s:hidden name="frmDate"/>
	<s:hidden name="toDate"/>   
    <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Appraisal Statistics Report</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
    </tr>
    
    	<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td nowrap="nowrap"><a href="#" onclick="resetFun();">
					<img src="../pages/images/buttonIcons/Refresh.png"
						class="iconImage" align="absmiddle" title="PDF"> Reset </a>&nbsp;&nbsp;
					</td>
					<td width="100%" colspan="6"><%@ include
						file="/pages/common/reportButtonPanel.jsp"%></td>
				</tr>
			</table>
			</td>
	</tr>
	<tr>
	 <td>		
        <div name="htmlReport" id='reportDiv'
			style="background-color: #FFFFFF; height: 400; width: 100%; display: none; border-top: 1px #cccccc solid;">
		<iframe id="reportFrame" frameborder="0" onload=alertsize();
			style="vertical-align: top; float: left; border: 0px solid;"
			allowtransparency="yes" src="../pages/common/loading.jsp"
			scrolling="auto" marginwidth="0" marginheight="0" vspace="0"
			name="htmlReport" width="100%" height="200"></iframe></div>
	 </td>
	</tr>
    <tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%"></td>
					<td width="22%" align="right">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr> 
    
    <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
          <tr>
            <td>	
            	<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" id="reportBodyTable">
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
							onclick="javascript:callsF9(500,325,'AppraisalStatistic_f9AppCal.action'); 
							">
					 </td>
					 
					 <td width="55%" colspan="1" height="20" >
					  <label class = "set" name="appraisal.from"  id="appraisal.from" ondblclick="callShowDiv(this);"><%=label.get("appraisal.from")%></label>:<s:property value="apprFrom"/><s:hidden name="apprFrom" />
					  <label class = "set" name="appraisal.to"  id="appraisal.to" ondblclick="callShowDiv(this);"><%=label.get("appraisal.to")%></label>:<s:property value="apprTo"/><s:hidden name="apprTo" />
					 </td>
		            </tr>
		            <!-- 
		            <tr>
		             <td class="formtext"  colspan="1" height="20" class="formtext">
		             	<label  class = "set" name="appraisal.temp"  id="appraisal.temp" ondblclick="callShowDiv(this);"><%=label.get("appraisal.temp")%></label> :
		             </td>
		             <td  colspan="1" height="20" class="formtext">
		              <s:textfield name="template"  readonly="true"/>
		              <s:hidden name="templateId" />					  
		             </td>
		            </tr>
		             -->
		            <tr>	
		            <td colspan="2">
		            	<input type="radio" name="appstat" value="org" checked="checked">
		            	<label class = "set" name="appraisal.org.wise"  id="appraisal.org.wise" ondblclick="callShowDiv(this);"><%=label.get("appraisal.org.wise")%></label>									 
					</td>
					</tr>
					<tr>
					<td colspan="2"><input type="radio" name="appstat" value="branch" >
						 <label class = "set" name="appraisal.branch.wise"  id="appraisal.branch.wise" ondblclick="callShowDiv(this);"><%=label.get("appraisal.branch.wise")%></label>
					</td>
					</tr>
					<tr>
					<td colspan="2"><input type="radio" name="appstat" value="dept" >
						 <label class = "set" name="appraisal.dept.wise"  id="appraisal.dept.wise" ondblclick="callShowDiv(this);"><%=label.get("appraisal.dept.wise")%></label>						 
					</td>
					</tr>
					<tr>
					<td colspan="2"><input type="radio" name="appstat" value="desi" >
						 <label class = "set" name="appraisal.desg.wise"  id="appraisal.desg.wise" ondblclick="callShowDiv(this);"><%=label.get("appraisal.desg.wise")%></label>					 
						 
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
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2">
          <tr>
            <td width="78%">
            	<!-- <jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /> -->
  			
			</td>
          </tr>
        	</table>
          </td>
    </tr>
  	<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td nowrap="nowrap"><a href="#" onclick="resetFun();">
					<img src="../pages/images/buttonIcons/Refresh.png"
						class="iconImage" align="absmiddle" title="PDF"> Reset </a>&nbsp;&nbsp;
					</td>
					<td width="100%" colspan="6"><%@ include
						file="/pages/common/reportButtonPanel.jsp"%></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<div id='bottomButtonTable'></div>
			</td>
		</tr>
  	
	</table>
</s:form>
<SCRIPT LANGUAGE="JavaScript">
var obj = document.getElementById("topButtonTable").cloneNode(true);
document.getElementById("bottomButtonTable").appendChild(obj);
</SCRIPT>
<script type="text/javascript" src="../pages/common/datetimepicker.js">

</script>


<script type="text/javascript">
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
		
			callReport('AppraisalStatistic_report.action');
		}
	function resetFun(){

	document.getElementById('paraFrm_apprCode').value="";
	document.getElementById('paraFrm_apprId').value="";
	document.getElementById('paraFrm').action="AppraisalStatistic_reset.action"; 
	document.getElementById('paraFrm').submit();
	
	}	
	
	
	//Added by Tinshuk Banafar ...Begin....
	
	function callReport(type){	
	try{
		var fields = ["paraFrm_apprCode"];
			var labels = [document.getElementById("appraisal.code").innerHTML]
			var types = ["select"];
			if(!(checkMandatory(fields,labels,types))){
				return false;
		}
	    document.getElementById('paraFrm_report').value=type;
		callReportCommon(type);
		 }
		 catch (e){
	 	alert(e);
	 	}	
	 }
	 
	 function mailReportFun(type){
	   
	   var fields = ["paraFrm_apprCode"];
			var labels = [document.getElementById("appraisal.code").innerHTML]
			var types = ["select"];
			if(!(checkMandatory(fields,labels,types))){
				return false;
		}
		document.getElementById('paraFrm_report').value=type;
		document.getElementById('paraFrm').action='AppraisalStatistic_mailReport.action';
		document.getElementById('paraFrm').submit();
			
		}
	
	//Added by Tinshuk Banafar ...End....
		
</script>
