<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="java.util.HashMap"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="AppraisalReport" validate="true" id="paraFrm" theme="simple">
	
	
 <table width="100%" border="0" align="right" cellpadding="2" cellspacing="2" class="formbg" >
    
    <s:hidden name="frmDate"/>
	<s:hidden name="toDate"/>   
    <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Appraisal Form Report</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
    </tr>
    <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2">
         
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
							onclick="javascript:callsF9(500,325,'AppraisalFormReport_f9AppCal.action'); 
							">
					 </td> 
					 <td width="55%" colspan="1" height="20">
					 
					 <!-- 
					  <label class = "set" name="appraisal.from"  id="appraisal.from" ondblclick="callShowDiv(this);"><%=label.get("appraisal.from")%></label>:<s:property value="apprFrom"/>
					  <label class = "set" name="appraisal.to"  id="appraisal.to" ondblclick="callShowDiv(this);"><%=label.get("appraisal.to")%></label>:<s:property value="apprTo"/>
					  -->
					  
					  <s:hidden name="apprFrom" /> 
					  <s:hidden name="apprTo" />
					 
					 
					 </td>
		            </tr>
		            <!--<tr>
		             <td class="formtext"  colspan="1" height="20" class="formtext">
		             	<label  class = "set" name="appraisal.temp"  id="appraisal.temp" ondblclick="callShowDiv(this);"><%=label.get("appraisal.temp")%></label> :
		             </td>
		             <td  colspan="1" height="20" class="formtext">
		              <s:textfield name="template"  readonly="true"/>
		              <s:hidden name="templateId" />					  
		             </td>
		            </tr>
		            --><tr>
		               	<td width="25%" colspan="1" height="20" class="formtext">
		               	 <label  class = "set" name="employee"  id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> <font color="red">*</font>:
		               	</td>
					   	<td width="25%" colspan="2" height="20" >
					   	 <s:textfield name="empToken" size="10" readonly="true" /> 
					   	 <s:textfield name="empName" readonly="true" size="25" />
					   	 <img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="return callEmployee();">
					   	</td> 
					   	<td width="25%" height="20">
					   	<s:hidden name="empId"/>
					   	<s:hidden name="branch"/>
					   	<s:hidden name="desg"/>  </td>
	           		</tr>
	           		
	           		<!--<tr>
	           		 <td width="25%" colspan="1" height="20" class="formtext">
	         			<label  class = "set" name="appraisal.div"  id="appraisal.div" ondblclick="callShowDiv(this);"><%=label.get("appraisal.div")%></label> :
	           		 </td>
	           		 <td>
	           		 	<s:textfield name="division" size="20" readonly="true" />
	           		 	<s:hidden name="divId" />
						<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="return callEmployee();">
	           		 </td>
	           		</tr>
	           		
	           		<tr>
	           		 <td width="25%" colspan="1" height="20" class="formtext">
	         			<label  class = "set" name="appraisal.branch"  id="appraisal.branch" ondblclick="callShowDiv(this);"><%=label.get("appraisal.branch")%></label> :
	           		 </td>
	           		 <td>
	           		 	<s:textfield name="branch" size="20" readonly="true" />
	           		 	<s:hidden name="branchId" />
						<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="return callEmployee();">
	           		 </td>
	           		</tr>
	           		
	           		<tr>
	           		 <td width="25%" colspan="1" height="20" class="formtext">
	         			<label  class = "set" name="appraisal.dept"  id="appraisal.dept" ondblclick="callShowDiv(this);"><%=label.get("appraisal.dept")%></label> :
	           		 </td>
	           		 <td>
	           		 	<s:textfield name="dept" size="20" readonly="true" />
	           		 	<s:hidden name="deptId" />
						<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="return callEmployee();">
	           		 </td>
	           		</tr>	
	           		
	           		<tr>
	           		 <td width="25%" colspan="1" height="20" class="formtext">
	         			<label  class = "set" name="appraisal.desg"  id="appraisal.desg" ondblclick="callShowDiv(this);"><%=label.get("appraisal.desg")%></label> :
	           		 </td>
	           		 <td>
	           		 	<s:textfield name="desg" size="20" readonly="true" />
	           		 	<s:hidden name="desgId" />
						<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple" onclick="return callEmployee();">
	           		 </td>
	           		</tr>
	           		
            	--></table>
            </td>
          </tr>
      </table>
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
	</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js">

</script>


<script type="text/javascript">

	 function resetFun(){
	  
	  document.getElementById('paraFrm').action="AppraisalFormReport_reset.action";
	  document.getElementById('paraFrm').submit();
	  
	 }
	function callEmployee(){
			if(document.getElementById("paraFrm_apprCode").value==""){
				alert("Please select "+document.getElementById("appraisal.code").innerHTML);
				document.getElementById("paraFrm_apprCode").focus();
				return false;
			}
			
			javascript:callsF9(500,325,'AppraisalFormReport_f9Employee.action'); 
	}
	function callCalendar(){
			
			document.getElementById("paraFrm_empCode").value="";
			document.getElementById("paraFrm_empName").value="";
			document.getElementById("paraFrm_empToken").value="";
			
			javascript:callsF9(500,325,'AppraisalReport_f9AppCal.action'); 
	}
	function reportFun(){
			
			var fields = ["paraFrm_apprCode","paraFrm_empName"];
			var labels = [document.getElementById("appraisal.code").innerHTML,document.getElementById("employee").innerHTML]
			var types = ["select","select"];
			if(!(checkMandatory(fields,labels,types))){
				return false;
		}
		
			callReport('AppraisalFormReport_report.action');
			
			document.getElementById('paraFrm_apprCode').value="";
			document.getElementById('paraFrm_apprId').value="";
			document.getElementById('paraFrm_empToken').value="";
			document.getElementById('paraFrm_empName').value="";
			document.getElementById('paraFrm_empId').value="";
		}
</script>
