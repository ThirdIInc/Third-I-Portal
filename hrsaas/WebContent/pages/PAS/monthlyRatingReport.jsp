<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="MonthlyRatingReport" validate="true" id="paraFrm" theme="simple">
	
	
 <table width="100%" border="0" align="right" cellpadding="2" cellspacing="2" class="formbg" >
    
      
    <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Monthly Rating Report</strong></td>
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
      <td colspan="3">
       <table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
          <tr>		           
		      <td class="formtext"  colspan="1" height="20" class="formtext" >
		       	<label  class = "set" name="appraisal.div"  id="appraisal.div" ondblclick="callShowDiv(this);"><%=label.get("appraisal.div")%></label> 
		       	<font color="red">*</font> : 
		      </td>
		      <td  colspan="1" height="20" class="formtext" width="15">
		         <s:textfield name="division"  readonly="true"/>
		           <s:hidden name="divId" />
		       </td>
		       <td>    
				  	<img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="17" theme="simple"
						onclick="javascript:callsF9(500,325,'MonthlyRatingReport_f9Division.action');">
		      </td>
		      
		      <td class="formtext"  colspan="1" height="20" class="formtext" >
		      		<label  class = "set" name="appraisal.branch"  id="appraisal.branch" ondblclick="callShowDiv(this);"><%=label.get("appraisal.branch")%></label> :
		      </td>
		      <td  colspan="1" height="20" class="formtext" width="15">
		        	<s:textfield name="branch" readonly="true" />
		        	<s:hidden name="branchId" />
		       </td>
		       <td> 
			 		<img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="17" theme="simple"
						onclick="javascript:callsF9(500,325,'MonthlyRatingReport_f9Branch.action');">		              
		      </td>
		   </tr>		   
		      
		   
		            
		  <tr>
		    <td class="formtext" colspan="1" height="20" class="formtext" >
		      	<label  class = "set" name="appraisal.dept"  id="appraisal.dept" ondblclick="callShowDiv(this);"><%=label.get("appraisal.dept")%></label> :
		    </td>
		    <td  colspan="1" height="20" class="formtext" width="15">
		        <s:textfield name="dept" readonly="true" />
		        <s:hidden name="deptId" />
		    </td>    
		     <td>   
				<img
					src="../pages/images/recruitment/search2.gif" height="16"
					align="absmiddle" width="17" theme="simple"
					onclick="javascript:callsF9(500,325,'MonthlyRatingReport_f9Dept.action');">
		    </td>
		    
		    
		     <td class="formtext"  colspan="1" height="20" class="formtext" >
		       	<label  class = "set" name="appraisal.desg"  id="appraisal.desg" ondblclick="callShowDiv(this);"><%=label.get("appraisal.desg")%></label> :
		     </td>
		     <td  colspan="1" height="20" class="formtext" width="15">
		        <s:textfield name="desg" readonly="true" />
		         <s:hidden name="desgId" />
		      </td>   
		      <td>   
			  	<img
					src="../pages/images/recruitment/search2.gif" height="16"
					align="absmiddle" width="17" theme="simple"
					onclick="javascript:callsF9(500,325,'MonthlyRatingReport_f9Desg.action');">		              
		     </td>
		  </tr>
		            
		 
		 <tr>
		    <td class="formtext" colspan="1" height="20" class="formtext" >
		      	<label  class = "set" name="appraisal.month"  id="appraisal.month" ondblclick="callShowDiv(this);"><%=label.get("appraisal.month")%></label> :
		    </td>
		    <td  colspan="1" height="20" class="formtext" width="15">
		        <s:select name="month" headerKey="0"
						headerValue="--Select--" cssStyle="width:125"
						list="#{'1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" />	        
		    </td>
		    <td></td>
		    
		     <td class="formtext"  colspan="1" height="20" class="formtext" >
		       	<label  class = "set" name="appraisal.year"  id="appraisal.year" ondblclick="callShowDiv(this);"><%=label.get("appraisal.year")%></label> :
		     </td>
		     <td  colspan="2" height="20" class="formtext" >
		        <s:textfield name="year" />		        		              
		     </td>
		  </tr>
		  
		  
		  <tr>
		    <td class="formtext" colspan="1" height="20" class="formtext" >
		      	<label  class = "set" name="appraisal.manager"  id="appraisal.manager" ondblclick="callShowDiv(this);"><%=label.get("appraisal.manager")%></label> :
		    </td>
		    <td  colspan="1" height="20" class="formtext" width="15">
		    	<s:hidden name="managerToken" />
		        <s:textfield name="manager" readonly="true" />
		        <s:hidden name="managerID" />
		    </td>
		    
		    <td>    
		        <img
					src="../pages/images/recruitment/search2.gif" height="16"
					align="absmiddle" width="17" theme="simple"
					onclick="javascript:callsF9(500,325,'MonthlyRatingReport_f9Manager.action');">
		    </td>		    
		    
		     <td class="formtext"  colspan="1" height="20" class="formtext" >
		       	<label  class = "set" name="appraisal.reportType"  id="appraisal.reportType" ondblclick="callShowDiv(this);"><%=label.get("appraisal.reportType")%></label> :
		     </td>
		     <td  colspan="2" height="20" class="formtext" >
		        <s:select name="reportType" headerKey="Xls" headerValue="XLS" list="#{'Pdf':'PDF'}" />			  			              
		     </td>
		  </tr>
		 
		       
      </table>
     </td>
   </tr>
    
    
     <tr>
      <td colspan="3">
        <table width="100%" border="0" cellpadding="2" cellspacing="2">
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
 
<script type="text/javascript">
 	function resetFun()
 		{	 
	 		document.getElementById('paraFrm').action="MonthlyRatingReport_reset.action";
	 		document.getElementById('paraFrm').submit();
		}
		
	function reportFun()
	{
		try
		{
			var division = document.getElementById('paraFrm_division').value;
			if(division=="")
			{
				alert("Please select Division.");
				return false;
			}
		
			document.getElementById('paraFrm').action="MonthlyRatingReport_getReport.action";
	 		document.getElementById('paraFrm').submit();
	 	}
	 	catch(e)
	 	{
	 		alert("Exception occurred in reportFun() ======> "+e);
	 		return false;
	 	}	
		
	}	
</script>