<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="ReimbursementBalanceReport" validate="true" id="paraFrm" theme="simple">

<table width="100%" border="0" align="right" cellpadding="2" cellspacing="2" class="formbg" >
	<tr>
	   <td>
	   		<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
	        	 <tr>
				    <td width="4%" valign="bottom" class="txt">
				    	<strong class="formhead">
				    		<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" />
				    	</strong>
				    </td>
				    <td width="93%" class="txt"><strong class="text_head">Reimbursement Balance Sheet Report</strong></td>
				    <td width="3%" valign="top" class="txt">
				    	<div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
				    </td>
				  </tr>
	        </table>
	   </td>
    </tr>	<!-- End of 1st tr -->
    
   <tr>
	<td colspan="3" width="100%">
	
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">
		
		<tr>
			<td colspan="3" align="right" width="100%">
				<div align="right"><font color="red">*</font> Indicates Required</div>
			</td>
		</tr>
		
		
		<tr>
			<td >
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
    
    
    <tr>
      <td>
       <table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg" id="reportBodyTable">
     <!--    <tr>
							<td class="text_head" colspan="6"><strong
								class="forminnerhead">Select Period</strong></td>
						</tr>
       	 <tr>
		    <td class="formtext" colspan="1" height="20" class="formtext" width="22%">
		      	<label  class = "set" name="reimbursement.fromDate"  id="reimbursement.fromDate" ondblclick="callShowDiv(this);"><%=label.get("reimbursement.fromDate")%></label><font color="red">*</font> :
		    </td>
		    <td height="20" class="formtext" width="22%">
		        <s:textfield name="fromDate" size="30" readonly="false"/>
		    </td>
		    <td width="5%">
		    	<a	href="javascript:NewCal('paraFrm_fromDate','DDMMYYYY');"> <img
						src="../pages/images/Date.gif" class="iconImage" height="16"
						align="absmiddle" width="16"> </a>
		    </td>
		    
		     <td class="formtext" colspan="1" height="20" class="formtext" width="22%">
		      	<label  class = "set" name="reimbursement.toDate"  id="reimbursement.toDate" ondblclick="callShowDiv(this);"><%=label.get("reimbursement.toDate")%></label><font color="red">*</font> :
		    </td>
		    <td height="20" class="formtext" width="22%">
		        <s:textfield name="toDate" size="30" readonly="false" />
		    </td>
		    <td width="5%">
		    	<a	href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');"> <img
						src="../pages/images/Date.gif" class="iconImage" height="16"
						align="absmiddle" width="16"> </a>
		    </td>
		  </tr>
		  <tr>
							<td colspan="6">
							<hr />
							</td>
						</tr>
		-->
		<tr>
			<td colspan="5">
			<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg" >
			<tr>
							<td class="text_head" colspan="6"><strong
								class="forminnerhead">Select Filter Option</strong></td>
						</tr>
       
          <tr>		           
		      <td class="formtext" colspan="1" height="20" class="formtext" width="20%">
		       	<label  class = "set" name="reimbursement.div"  id="reimbursement.div" ondblclick="callShowDiv(this);"><%=label.get("reimbursement.div")%></label><font color="red">*</font> : 
		      </td>
		      <td  colspan="1" height="20" class="formtext" width="78%">
		       
		         <s:textarea name="division" cols="120" rows="1" readonly="true" />
		           <s:hidden name="divId" />
		       </td>
		       <td width="2%">    
				  	<img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="17" theme="simple"
						onclick="javascript:callDropdown('paraFrm_division',350,250,'ReimbursementBalanceReport_f9Division.action',event,'false','no','right')">
		      </td>
		      </tr>
		       <tr>
		    <td class="formtext" colspan="1" height="20" class="formtext" width="20%">
		      	<label  class = "set" name="reimbursement.head"  id="reimbursement.head" ondblclick="callShowDiv(this);"><%=label.get("reimbursement.head")%></label><font color="red">*</font> :
		    </td>
		    <td  colspan="1" height="20" class="formtext" width="78%">
		    	<s:hidden name="creditCode" />
		    	<s:textarea name="creditName" cols="120" rows="1" readonly="true" />
		       
		        <s:hidden name="creditType" />
		    </td>
		    
		    <td width="2%">    
		        <img
					src="../pages/images/recruitment/search2.gif" height="16"
					align="absmiddle" width="17" theme="simple"
					onclick="javascript:callDropdown('paraFrm_creditName',350,250,'ReimbursementBalanceReport_f9CreditHead.action',event,'false','no','right')">
		    </td>		    
		    
		    
		     <s:hidden name="reportType"></s:hidden>
		     <s:hidden name="reportAction" value='ReimbursementBalanceReport_getReport.action'/>
		       
		  </tr>
		      <tr>
		      <td class="formtext"  colspan="1" height="20" class="formtext" width="20%">
		      		<label  class = "set" name="reimbursement.branch"  id="reimbursement.branch" ondblclick="callShowDiv(this);"><%=label.get("reimbursement.branch")%></label> :
		      </td>
		      <td  colspan="1" height="20" class="formtext" width="78%">
		       <s:textarea name="branch" cols="120" rows="1" readonly="true" />
		        	
		        	<s:hidden name="branchId" />
		       </td>
		       <td width="2%"> 
			 		<img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="17" theme="simple"
						onclick="javascript:callDropdown('paraFrm_branch',350,250,'ReimbursementBalanceReport_f9Branch.action',event,'false','no','right')">		              
		      </td>
		   </tr>		   
		      
		   
		            
		  <tr>
		    <td class="formtext" colspan="1" height="20" class="formtext" width="20%">
		      	<label  class = "set" name="reimbursement.dept"  id="reimbursement.dept" ondblclick="callShowDiv(this);"><%=label.get("reimbursement.dept")%></label> :
		    </td>
		    <td  colspan="1" height="20" class="formtext" width="78%">
		     <s:textarea name="dept" cols="120" rows="1" readonly="true" />
		        
		        <s:hidden name="deptId" />
		    </td>    
		     <td width="2%">   
				<img
					src="../pages/images/recruitment/search2.gif" height="16"
					align="absmiddle" width="17" theme="simple"
					onclick="javascript:callDropdown('paraFrm_dept',350,250,'ReimbursementBalanceReport_f9Dept.action',event,'false','no','right')">
		    </td>
		    </tr>
		    <tr>
		    
		     <td class="formtext"  colspan="1" height="20" class="formtext" width="20%">
		       	<label  class = "set" name="reimbursement.paybill"  id="reimbursement.paybill" ondblclick="callShowDiv(this);"><%=label.get("reimbursement.paybill")%></label> :
		     </td>
		     <td  colspan="1" height="20" class="formtext" width="78%">
		     <s:textarea name="paybillname" cols="120" rows="1" readonly="true" />
		        
		         <s:hidden name="paybillid" />
		      </td>   
		      <td width="2%">   
			  	<img
					src="../pages/images/recruitment/search2.gif" height="16"
					align="absmiddle" width="17" theme="simple"
					onclick="javascript:callDropdown('paraFrm_paybillname',350,250,'ReimbursementBalanceReport_f9Paybill.action',event,'false','no','right')">		              
		     </td>
		  </tr>
		  
		   <tr>
		    <td class="formtext" colspan="1" height="20" class="formtext" width="20%">
		      	<label  class = "set" name="reimbursement.grade"  id="reimbursement.grade" ondblclick="callShowDiv(this);"><%=label.get("reimbursement.grade")%></label> :
		    </td>
		    <td  colspan="1" height="20" class="formtext" width="78%">
		     <s:textarea name="gradeName" cols="120" rows="1" readonly="true" />
		        
		        <s:hidden name="gradeId" />
		    </td>    
		     <td width="2%">   
				<img
					src="../pages/images/recruitment/search2.gif" height="16"
					align="absmiddle" width="17" theme="simple"
					onclick="javascript:callDropdown('paraFrm_gradeName',350,250,'ReimbursementBalanceReport_f9Grade.action',event,'false','no','right')">
		    </td>
	 
		  </tr>
		   <tr>
		    <td class="formtext" colspan="1" height="20" class="formtext" width="20%">
		      	<label  class = "set" name="reimbursement.costcenter"  id="reimbursement.costcenter" ondblclick="callShowDiv(this);"><%=label.get("reimbursement.costcenter")%></label> :
		    </td>
		    <td  colspan="1" height="20" class="formtext" width="78%">
		     <s:textarea name="costcentername" cols="120" rows="1" readonly="true" />
		        
		        <s:hidden name="costcenterid" />
		    </td>    
		     <td width="2%">   
				<img
					src="../pages/images/recruitment/search2.gif" height="16"
					align="absmiddle" width="17" theme="simple"
					onclick="javascript:callDropdown('paraFrm_gradeName',350,250,'ReimbursementBalanceReport_f9Costcenter.action',event,'false','no','right')">
		    </td>
	 
		  </tr>
		            
		 <!--  <tr>
							<td colspan="6">
							<hr />
							</td>
						</tr>
		
		  -->
		  
		 
			</table>
			</td>
		</tr>
						
		  <tr>
			<td colspan="5">
			<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg" >
			<tr>
					<td class="text_head" colspan="6" ><strong
						class="forminnerhead">Select any/all of the following options to add in the report:</strong></td>
		</tr>
		<tr>
			<td width="15%" align="right">Division</td><td width="2%"><input type="checkbox" name="reportdivision"/></td>
			<td width="15%" align="right">Brach</td><td width="2%"><input type="checkbox" name="reportbranch"/></td>
			<td width="15%" align="right">Department</td><td width="2%"><input type="checkbox" name="reportdept"/></td>
			
		</tr>
		
		<tr>
			<td width="15%" align="right">Grade</td><td width="2%"><input type="checkbox" name="reportgrade"/></td>
			<td width="15%" align="right">Account No</td><td width="2%"><input type="checkbox" name="reportaccno"/></td>
			<td width="15%" align="right">Bank</td><td width="2%"><input type="checkbox" name="reportbank"/></td>
			
		</tr>
		
			</table>
			</td>
		</tr>
		  
		 
		       
      </table>
     </td>
   </tr> <!-- End of 3rd tr -->
    
    
    <tr>
			<td >
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
						<%@ include file="/pages/common/reportButtonPanelBottom.jsp"%>
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
</td>
</tr>
</table>
</td>
</tr>
</table>

</s:form>



<script type="text/javascript">

function mailReportFun(type){
		if(!validateFields()){
			return false;
		} else {
		//alert(1);
		document.getElementById('paraFrm_reportType').value=type;
			//callDropdown(obj.name,200,250,'ReimbursementBalanceReport_f9reportType.action','false');
			document.getElementById('paraFrm').action='ReimbursementBalanceReport_mailReport.action';
			document.getElementById('paraFrm').submit();
			//return true;
		}	
	}


	
	function validateFields()
	{
		
			/*var reportFromDate =document.getElementById('paraFrm_fromDate').value;
  			var reportToDate =document.getElementById('paraFrm_toDate').value;
  			
  			if(reportFromDate=="")
  			{
  				alert("Please enter/select from date");
  				return false;
  			}
  			if(reportToDate=="")
  			{
  				alert("Please enter/select to date");
  				return false;
  			}
  			
			
			if(reportFromDate!="")
			{				
				if(!validateDate('paraFrm_fromDate',"reimbursement.fromDate")){
					document.getElementById('paraFrm_fromDate').focus();
					return false;   	
   				}				
			}
			
			if(reportToDate!="")
			{				
				if(!validateDate('paraFrm_toDate',"reimbursement.toDate")){
					document.getElementById('paraFrm_toDate').focus();
					return false;   	
   				}				
			}
			
			if(reportToDate!="" &&  reportFromDate!="")
			{	
				var datdiffresignDate =dateDifferenceEqual(reportFromDate,reportToDate,'paraFrm_toDate','reimbursement.fromDate','reimbursement.toDate');
  				if(!datdiffresignDate)
  				{
  					return false;
  				}
			}
			*/
			var division = document.getElementById('paraFrm_division').value;
			if(division=="")
			{
				alert("Please select Division.");
				return false;
			}
			
			var credithead = document.getElementById('paraFrm_creditName').value;
			if(credithead=="")
			{
				alert("Please select Reimburse Head .");
				return false;
			}
			
			
			return true;
		
	}

 	function callReset()
 		{	 
	 		document.getElementById('paraFrm').action="ReimbursementBalanceReport_reset.action";
	 		document.getElementById('paraFrm').submit();
		}
		
	function callReport(type)
	{
		document.getElementById('paraFrm_reportType').value=type;
		try
		{	
		if(!validateFields()){
			return false;
		} else {
			//document.getElementById('paraFrm').action="ReimbursementBalanceReport_getReport.action";
	 		//document.getElementById('paraFrm').submit();
	 		callReportCommon(type);
			return true;
		}	
		//alert("document.getElementById('departmentWise').checked : "+document.getElementById('departmentWise').checked);
		//alert("document.getElementById('costcenterWise').checked : "+document.getElementById('costcenterWise').checked);		
			/*
			var division = document.getElementById('paraFrm_division').value;
			if(division=="")
			{
				alert("Please select Division.");
				return false;
			}			
			
			if(document.getElementById('departmentWise').checked)
			{
			}
			if(document.getElementById('departmentWise').checked==false && document.getElementById('costcenterWise').checked ==false )
			{
			 	alert("Please select show static ");
			 	return false;
			}
			var reportFromDate =document.getElementById('paraFrm_fromDate').value;
  			var reportToDate =document.getElementById('paraFrm_toDate').value;
			
			if(reportFromDate!="")
			{				
				if(!validateDate('paraFrm_fromDate',"reimbursement.fromDate")){
					document.getElementById('paraFrm_fromDate').focus();
					return false;   	
   				}				
			}
			
			if(reportToDate!="")
			{				
				if(!validateDate('paraFrm_toDate',"reimbursement.toDate")){
					document.getElementById('paraFrm_toDate').focus();
					return false;   	
   				}				
			}
			
			if(reportToDate!="" &&  reportFromDate!="")
			{	
				var datdiffresignDate =dateDifferenceEqual(reportFromDate,reportToDate,'paraFrm_toDate','reimbursement.fromDate','reimbursement.toDate');
  				if(!datdiffresignDate)
  				{
  					return false;
  				}
			}*/
		
			
	 		
	 	}
	 	catch(e)
	 	{
	 		alert("Exception occurred in reportFun() ======> "+e);
	 		return false;
	 	}	
		
	}	
</script>
