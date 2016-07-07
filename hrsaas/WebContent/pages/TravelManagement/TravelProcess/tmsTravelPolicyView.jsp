 <%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="TravelPolicy" validate="true" id="paraFrm" 	theme="simple"> 
<s:hidden name="myPage"/>
<s:hidden name="show" />	 
	<table width="100%" border="0" align="right" cellpadding="0"  	class="formbg"	cellspacing="2" theme="simple">
		
	 <tr>
		 <td colspan="3" width="100%">
			<table width="100%" border="0" align="center" class="formbg" >
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
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
				<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						<%
							int totalPage = (Integer) request.getAttribute("totalPage");
							int PageNo = (Integer) request.getAttribute("pageNo");
							int row1 = (Integer) request.getAttribute("row");
						%>
						<td align="right" colspan="5"><b>Page:</b> <%
 		if (PageNo != 1) {
 		if (PageNo > totalPage) {
 		} else {
 %> <a href="#" onclick="callPage('1');"> <img
							src="../pages/common/img/first.gif" width="10" height="10"
							class="iconImage" /> </a>&nbsp; <a href="#" onclick="callPage('P');">
						<img src="../pages/common/img/previous.gif" width="10" height="10"
							class="iconImage" /> </a> <%
 	}
 	}
 	if (totalPage <= 5) {
 		if (totalPage == 1) {
 %> <b><u><%=totalPage%></u></b> <%
 			} else {
 			for (int z = 1; z <= totalPage; z++) {
 		if (PageNo == z) {
 %> <b><u><%=z%></u></b> <%
 } else {
 %> <a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%
 			}
 			}
 		}
 	} else {
 		if (PageNo == totalPage - 1 || PageNo == totalPage) {
 			for (int z = PageNo - 2; z <= totalPage; z++) {
 		if (PageNo == z) {
 %> <b><u><%=z%></u></b> <%
 } else {
 %> &nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%
 			}
 			}
 		} else if (PageNo <= 3) {
 			for (int z = 1; z <= 5; z++) {
 		if (PageNo == z) {
 %> <b><u><%=z%></u></b> <%
 } else {
 %> &nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%
 			}
 			}
 		} else if (PageNo > 3) {
 			for (int z = PageNo - 2; z <= PageNo + 2; z++) {
 		if (PageNo == z) {
 %> <b><u><%=z%></u></b> <%
 } else {
 %> &nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%
 			}
 			}
 		}
 	}
 	if (!(PageNo == totalPage)) {
 		if (totalPage == 0 || PageNo > totalPage) {
 		} else {
 %> ....<%=totalPage%> <a href="#" onclick="callPage('N')">
						<img src="../pages/common/img/next.gif" class="iconImage" /> </a>&nbsp;
						<a href="#" onclick="callPage('<%=totalPage%>');"> <img
							src="../pages/common/img/last.gif" width="10" height="10"
							class="iconImage" /> </a> <%
 	}
 	}
 %><select name="selectname" onchange="selectPage()"
							id="selectname">
							<%
							for (int j = 1; j <= totalPage; j++) {
							%>

							<option value="<%=j%>" <%=PageNo==j?"selected":"" %>><%=j%></option>
							<%
							}
							%>
						</select></td>
					</tr>
			</table>
			 <s:hidden name="policyName" />	<s:hidden  name="policyId" />  
			 <s:hidden name="policyAbbr" />	<s:hidden  name="effectDate" />  
			 <s:hidden name="desc" />	<s:hidden  name="status" />   
			 <s:hidden  name="activeflag" /> 
			
			</td>
		</tr>    

		  <tr>
			 <td colspan="2" width="100%">
			 <%
			try {
			%>
			 	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
					    <tr>
								<td width="5%" colspan="1" class="formth"><strong><label  class = "set"  id="srNo" name="srNo"  ondblclick="callShowDiv(this);"><%=label.get("srNo")%></label></strong></td>
								<td width="35%" colspan="1" class="formth"><strong><label  class = "set"  id="policy.name" name="policy.name" ondblclick="callShowDiv(this);"><%=label.get("policy.name")%></label></strong> </td>
						        <!-- 		<td width="15%" colspan="1" class="formth"><strong><label  class = "set"  id="abbr" name="abbr"   ondblclick="callShowDiv(this);"><%=label.get("abbr")%></label></strong></td> -->
								<td width="15%" colspan="1" class="formth"><strong><label  class = "set"  id="effective.date" name="effective.date"   ondblclick="callShowDiv(this);"><%=label.get("effective.date")%></label></strong></td>
								<td width="10%" colspan="1" class="formth"><strong><label  class = "set"  id="searchStatus" name="searchStatus" ondblclick="callShowDiv(this);"><%=label.get("searchStatus")%></label></strong></td>
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
								 
								<%=++row1%></td>
								<td width="35%" colspan="1" class="sortableTD"><s:property  value="itPolicyName" /> </td>
							    <!-- <td width="15%" colspan="1" class="sortableTD"><s:property  value="itPolicyAbbr" /></td> -->	
								<td width="15%" colspan="1" class="sortableTD" align="center"><s:property  value="itEffectDate" /></td>
								<td width="10%" colspan="1" class="sortableTD" ><s:property  value="itStatus" /></td>
				    	</tr>  
									<%b++; %>
								</s:iterator>
					</table>
					<%
				} catch (Exception e) {
				}
			%>				
				</td>
			</tr>
		 
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

					
					
				</tr>
			</table>
			<label></label></td>
		</tr>
		
	</table>


</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script> 
<script>

   function addnewFun()
     {    
        
         document.getElementById('paraFrm').action = "TravelPolicy_addNew.action";
		 document.getElementById('paraFrm').submit(); 
		 document.getElementById('paraFrm').target ="main"; 
    }
    
      function searchFun()
     {  
         callsF9(500,325,'TravelPolicy_f9action.action');
    }
    
   function newRowColor(cell)
   		 {
		   cell.className='Cell_bg_first';

	    }
	
	function oldRowColor(cell,val) {
	
	
	
	cell.className='Cell_bg_second';
	
	
		
	}
	
	function callForEdit(id)
     {    
     //alert('y');
     
        document.getElementById('paraFrm_policyId').value=id;
         //document.getElementById('paraFrm_searchFlag').value="true"; 
         //document.getElementById('paraFrm_editFlag').value="true"; 
         document.getElementById('paraFrm').action = "TravelPolicy_callEdit.action";
		 document.getElementById('paraFrm').submit(); 
		 document.getElementById('paraFrm').target ="main"; 
    }
	
    
   
</script>
 
 
  
    <script>
    
   function callPage(id)
	{
	 var pageNo=document.getElementById('paraFrm_myPage').value	
	if(id=="P")
	document.getElementById('paraFrm_myPage').value=eval(pageNo)-1;	
	else if(id=="N")	
	document.getElementById('paraFrm_myPage').value=eval(pageNo)+1;	
	else
	document.getElementById('paraFrm_myPage').value=id;		
		document.getElementById('paraFrm').action='TravelPolicy_callPage.action';
	document.getElementById('paraFrm').submit();
	}
	
	 
	
	 function previous()
   {
  // alert(''+document.getElementById('paraFrm_myPage').value);
   var pageno=	document.getElementById('paraFrm_myPage').value;
  // 	alert('pageno--'+pageno);
	 document.getElementById('paraFrm_myPage').value=eval(pageno)-1;
	
	 document.getElementById('paraFrm_show').value=eval(pageno)-1;
	   document.getElementById('paraFrm').action="TravelPolicy_callPage.action";
	   document.getElementById('paraFrm').submit();
   }
	   function selectPage(appStatus)
   {     	
   	 	var pageNo;
   		 	pageNo = document.getElementById('selectname').value;
   		 	document.getElementById('paraFrm_myPage').value=pageNo;
   		 	document.getElementById('paraFrm').action='TravelPolicy_callPage.action';
   		 	document.getElementById('paraFrm').submit();
   		}
</script>