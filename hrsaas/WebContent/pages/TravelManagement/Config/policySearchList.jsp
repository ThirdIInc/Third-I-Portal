 <%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="TravelPolicy" validate="true" id="paraFrm" 	theme="simple"> 	 
	<table width="100%" border="0" align="right" cellpadding="0"  	class="formbg"	cellspacing="2">
		<tr>
			<td colspan="3" valign="bottom" class="txt">&nbsp;</td>
		</tr> 
	 <tr>
		 <td colspan="3">
			<table width="100%" border="0" align="center" class="formbg">
				<tr>
					<td valign="bottom" class="txt" width="3%" colspan="1"><strong
						class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="97%" class="txt" colspan="2"><strong
						class="text_head">Travel Policy </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		 
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			 <s:hidden name="policyName" />	<s:hidden  name="policyId" />  
			 <s:hidden name="policyAbbr" />	<s:hidden  name="effectDate" />  
			 <s:hidden name="desc" />	<s:hidden  name="status" />   <s:hidden  name="searchFlag" />  
			 <s:hidden  name="editFlag" />  
			</td>
		</tr>    

		  <tr>
			 <td colspan="2" width="100%">
			 	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
					    <tr>
								<td width="5%" colspan="1" class="formth"><label  class = "set"  id="srNo" name="srNo"  ondblclick="callShowDiv(this);"><%=label.get("srNo")%></label></td>
								<td width="55%" colspan="1" class="formth"><label  class = "set"  id="policy.name" name="policy.name" ondblclick="callShowDiv(this);"><%=label.get("policy.name")%></label> </td>
								<td width="15%" colspan="1" class="formth"><label  class = "set"  id="abbr" name="abbr"   ondblclick="callShowDiv(this);"><%=label.get("abbr")%></label></td>
								<td width="15%" colspan="1" class="formth"><label  class = "set"  id="effective.date" name="effective.date"   ondblclick="callShowDiv(this);"><%=label.get("effective.date")%></label></td>
								<td width="10%" colspan="1" class="formth"><label  class = "set"  id="searchStatus" name="searchStatus" ondblclick="callShowDiv(this);"><%=label.get("searchStatus")%></label></td>
				    	</tr>
				    	     <%int b=1 ,count = 0; %>
								<s:iterator value="policyList">  
						<tr <%if(count%2==0){
									%> class="tableCell1" <%}else{%>
									class="tableCell2" <%	}count++; %>
									onmouseover="javascript:newRowColor(this);" 
									onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
									ondblclick="javascript:callForEdit('<s:property value="itPolicyId" />');" >    
								
								<td width="5%" colspan="1" class="sortableTD">								
								<s:hidden name="itPolicyName" />  <s:hidden name="itPolicyAbbr" />  
						       <s:hidden name="itEffectDate" />  <s:hidden name="itStatus" />   <s:hidden name="itPolicyId" />
								 
								<%=b%></td>
								<td width="55%" colspan="1" class="sortableTD"><s:property  value="itPolicyName" /> </td>
								<td width="15%" colspan="1" class="sortableTD"><s:property  value="itPolicyAbbr" /></td>
								<td width="15%" colspan="1" class="sortableTD"><s:property  value="itEffectDate" /></td>
								<td width="10%" colspan="1" class="sortableTD"><s:property  value="itStatus" /></td>
				    	</tr>  
									<%b++; %>
								</s:iterator>
					</table>				
				</td>
			</tr>
		 
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

					<td width="22%"> &nbsp;
					</td>
				</tr>
			</table>
			<label></label></td>
		</tr>
	</table>
</td>
</tr>  
</table>
</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script> 
<script>

   function addnewFun()
     {    
         document.getElementById('paraFrm_editFlag').value="false";
         document.getElementById('paraFrm').action = "TravelPolicy_addNew.action";
		 document.getElementById('paraFrm').submit(); 
		 document.getElementById('paraFrm').target ="main"; 
    }
    
      function searchFun()
     {  
         document.getElementById('paraFrm_searchFlag').value="true"; 
          document.getElementById('paraFrm_editFlag').value="true"; 
         callsF9(500,325,'TravelPolicy_f9action.action');
    }
    
    function newRowColor(cell)
   		 {
		   cell.className='onOverCell';

	    }
	
	function oldRowColor(cell,val) {
	
	if(val=='1'){
	 cell.className='tableCell2';
	}
	else 
	cell.className='tableCell1';
		
	}
	
	function callForEdit(id)
     {    
     
         document.getElementById('paraFrm_policyId').value=id;
         document.getElementById('paraFrm_searchFlag').value="true"; 
          document.getElementById('paraFrm_editFlag').value="true"; 
         document.getElementById('paraFrm').action = "TravelPolicy_edit.action";
		 document.getElementById('paraFrm').submit(); 
		 document.getElementById('paraFrm').target ="main"; 
    }
	
    
</script>
 