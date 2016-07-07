<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="QuestionCategory" validate="true" id="paraFrm"
	theme="simple">

  <s:hidden name="hiddencode" />	
  <s:hidden name="readFlag"></s:hidden>
  <s:hidden name="sMode"></s:hidden>		
  <s:hidden name="sQstCategoryCode"></s:hidden>
  <s:hidden name="sQstCategoryName"></s:hidden>
  <s:hidden name="sCategoryStatus"></s:hidden>
  <s:hidden name="sCategoryDescription"></s:hidden>
  <s:hidden name="flgStatus"></s:hidden>
  		
  <table width="100%" border="0" align="right" cellpadding="2" cellspacing="2" class="formbg" >
    <tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="2" cellspacing="1">

						<tr>
							<td valign="bottom" class="txt"><strong class="formhead"><img
								src="../pages/images/review_shared.gif" width="25" height="25" /></strong></td>
							<td width="93%" class="txt"><strong class="text_head">Question Category</strong></td>
							<td width="3%" valign="top" class="txt">
							<div align="right"><img src="../pages/images/help.gif"
								width="16" height="16" /></div>
							</td>
						</tr>

					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
    
    
    <s:if test="readFlag">
	    <tr>
		  <td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="1">
				<tr>
					<td width="78%">
		            	<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
					</td>
					<td align="right"><b>Page:</b> 
					
						<%
						int total1 = (Integer) request.getAttribute("abc");
						int PageNo1 = (Integer) request.getAttribute("xyz");
						%> 
						<%
						if (!(PageNo1 == 1)) 
						{
						%>
						<a href="#" onclick="callPage('1');"> 
						<img src="../pages/common/img/first.gif" width="10" height="10"
							class="iconImage" /> </a>&nbsp; <a href="#" onclick="previous()"><img
							src="../pages/common/img/previous.gif" width="10" height="10"
							class="iconImage" /></a> 
						<%
		 				}
		 				if (total1 < 5) 
		 				{
			 				for (int i = 1; i <= total1; i++) 
			 				{
				 				%> 
				 				<a href="#" onclick="callPage('<%=i %>');"> 
				 				<%
				 				if (PageNo1 == i) {
				 				%> <b><u><%=i%></u></b> <%
				 				} else
				 				%><%=i%> </a> <%
			 				}
		 				}
		
		 				if (total1 >= 5) 
		 				{
		 					for (int i = 1; i <= 5; i++) 
		 					{
			 					%> <a href="#" onclick="callPage('<%=i %>');"> <%
			 					if (PageNo1 == i) {
			 					%> <b><u><%=i%></u></b> <%
			 					} else
			 					%><%=i%> </a> <%
		 					}
		 				}
		 				
		 				if (!(PageNo1 ==1) && !(total1==PageNo1)) 
		 				{
		 					%>...<a href="#" onclick="next()"> <img
							src="../pages/common/img/next.gif" class="iconImage" /> </a> &nbsp;<a
							href="#" onclick="callPage('<%=total1%>');"> <img
							src="../pages/common/img/last.gif" width="10" height="10"
							class="iconImage" /></a> <%
		 				}
		 				%> 
		 				<select name="selectname" onchange="on()" id="selectname">
						<%
						for (int i = 1; i <= total1; i++) {
						%>
							<option value="<%=i%>" <%=PageNo1==i?"selected":"" %>><%=i%></option>
						<% } %>
						</select>
					
					</td>
				</tr>
			</table>
		  </td>
		</tr>
			
	    
	    <tr>
	      <td colspan="3">
		      <table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg" >
		          <tr>
		          	<s:hidden name="show" value="%{show}" />
		          	<s:hidden name="myPage" id="myPage" />
					<td class="formth" width="5%" align="center"> <label name="questioncategory.srno" class = "set" id="questioncategory.srno" ondblclick="callShowDiv(this);"><%=label.get("questioncategory.srno")%></label></td>
					<td class="formth" width="30%" align="left"> <label name="questioncategory.category" class = "set"  id="questioncategory.category" ondblclick="callShowDiv(this);"><%=label.get("questioncategory.category")%></label></td>
					<td class="formth" width="15%" align="center"> <label name="questioncategory.status" class = "set"  id="questioncategory.status" ondblclick="callShowDiv(this);"><%=label.get("questioncategory.status")%></label></td>
					<td class="formth" width="40%" align="left"> <label name="questioncategory.description" class = "set" id="questioncategory.description" ondblclick="callShowDiv(this);"><%=label.get("questioncategory.description")%></label></td>
					<td class="formth" width="10%" align="center">
						<input type="button" name="delete" value="     Delete " class="delete" onClick="deleteMultipleRecordFun()">
					</td>
				  </tr>
		
				 <% int count = 0; %>
				 <%! int d = 0; %>
				 <%
				 	int cnt = PageNo1*20-20;	
				 	int i = 0;
				 %>
				  
				 <s:iterator value="lstQuestionCategory">
						<tr
							<%if(count%2==0){%> class="tableCell1" <%}else{%>
						 	 class="tableCell2" <%	}count++; %>
							 onmouseover="javascript:newRowColor(this);"
							 title="Double click for edit"
							 onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
							 ondblclick="javascript:callForEdit('<s:property value="sQstCategoryCode" />');" >
							 
								<td width="5%" align="center" class="sortableTD"><%=++cnt%><%++i;%></td>
								<td width="30%" align="left" class="sortableTD"><s:property value="sQstCategoryName" /></td>
								<td width="15%" align="center" class="sortableTD"><s:property value="sCategoryStatusDesc" /></td>
								<td width="40%" align="left" class="sortableTD"><s:property value="sCategoryDescription" /></td>
								<td width="10%" align="center" class="sortableTD">
									<input type="hidden" name="hdeleteCode" id="hdeleteCode<%=i%>" />
									<input type="checkbox" align="right" class="checkbox" id="confChk<%=i%>"
									name="confChk" onclick="callForDelete1('<s:property value="sQstCategoryCode" />','<%=i%>')" />
								</td>
						</tr>
				 </s:iterator>
				 <% d = i; %>
		      </table>
	      </td>
	    </tr>
	    
 		
 		<tr>
      		<td colspan="3">
	      		<table width="100%" border="0" cellpadding="2" cellspacing="1">
	         		<tr>
	            		<td width="78%">
	            			<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
						</td>
	          		</tr>
	        	</table>
          	</td>
    	</tr>
    	
	</s:if><s:else>
  		
  		<%-- Start : Navigation Panel --%>
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
    	
	    <%-- End : Navigation Panel --%>	
  		
  		<tr>
	      	<td colspan="3">
		  		<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg" >
			  		
			  		<tr>
			     		<td width="10%" height="22" class="formtext">
							<label name="questioncategory.category" class = "set" id="questioncategory.category" ondblclick="callShowDiv(this);"><%=label.get("questioncategory.category")%></label>
							<font color="#FF0000">*</font>
						</td>
			            <td width="73%" height="22">
							<%--<s:textfield name="sQstCategoryName" maxlength="100" size="50" />--%>
							<s:property value="questionCategory.sQstCategoryName" />
						</td>
			            <td width="17%" height="22">&nbsp;</td>
			        </tr>
			                
			        <tr height="30">
			             <td width="10%" height="25" class="formtext" >
			               	<label name="questioncategory.status" class = "set" id="questioncategory.status" ondblclick="callShowDiv(this);"><%=label.get("questioncategory.status")%></label>
			             </td>
			             <td width="73%" height="25" align="left">
							<s:if test="flgStatus">
								<input type="radio" name="sCategoryStatus" value = "A" checked="checked" disabled="disabled"> Active 
								<input type="radio" name="sCategoryStatus" value = "D" disabled="disabled"> De-Active
							</s:if>
							<s:else>	
							 	<input type="radio" name="sCategoryStatus" value = "A" disabled="disabled"> Active 
								<input type="radio" name="sCategoryStatus" value = "D" checked="checked" disabled="disabled"> De-Active
							</s:else>
						</td>
						<td width="17%" height="25">
			          		&nbsp;
						</td>
			        </tr>
						   
					<tr>
			            <td width="10%" height="25" class="formtext">
			               	<label name="questioncategory.description" class = "set" id="questioncategory.description" ondblclick="callShowDiv(this);"><%=label.get("questioncategory.description")%></label>
							<font color="#FF0000">*</font>
						</td>
			            <td width="73%" height="25">
							<%-- <s:textarea rows="2" cols="85" name="sCategoryDescription" ></s:textarea> --%>
							<s:property value="questionCategory.sCategoryDescription" />
			            </td>
			          	<td width="17%" height="25">
			          		&nbsp;
						</td>
					</tr>
					
					
					
				</table>
		   </td>
	   </tr>
	   
	   <%-- Start : Navigation Panel --%>
 		
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
	   <%-- End : Navigation Panel --%>
	   
	</s:else> 
  </table>
	
</s:form>

<script type="text/javascript">
	function addnewFun() {
		document.getElementById("paraFrm").action="QuestionCategory_addNew.action";
		document.getElementById("paraFrm").submit();	
	}
	
	function searchFun() {
		callsF9(500,325,'QuestionCategory_search.action');
	}
	
	function saveFun() {
		document.getElementById("paraFrm").action="QuestionCategory_addNew.action";
		document.getElementById("paraFrm").submit();
			
	}
	
	function resetFun() {
		document.getElementById("paraFrm").action="QuestionCategory_reset.action";
		document.getElementById("paraFrm").submit();
			
	}
	
	function cancelFun() {
		document.getElementById("paraFrm").action="QuestionCategory_input.action";
		document.getElementById("paraFrm").submit();			
	}
	
	function editFun() {
		document.getElementById("paraFrm").action="QuestionCategory_editSearch.action";
		document.getElementById("paraFrm").submit();			
	}
	
	function callForDelete(id) {
	  	document.getElementById('paraFrm_hiddencode').value=id;
	   	document.getElementById("paraFrm").action="QuestionCategory_delete.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
    }
   
   function callForDelete1(id,i)
	{
	   var flag='<%=d %>';
	
	   if(document.getElementById('confChk'+i).checked == true)
	   {	  
	   		document.getElementById('hdeleteCode'+i).value=id;
	   }
	   else
	   {
	   		document.getElementById('hdeleteCode'+i).value="";
	   	}
   	}
   	
   	function deleteMultipleRecordFun()
	{
		 if(chk())
		 {
		 	var con=confirm('Do you want to  delete the record ?');
			if(con)
			{
				document.getElementById('paraFrm').action="QuestionCategory_deleteMultipleRecords.action";
			    document.getElementById('paraFrm').submit();
			}
			else
			    return true;
		 }
		 else 
		 {
		  		alert('Please select atleast one record to delete');
		 	 	return false;
		 }
	}
	
	function chk() 
	{
		var flag = '<%=d %>';
	  	for (var a=1;a<=flag;a++)
	  	{	
		   	if(document.getElementById('confChk'+a).checked == true)
		   	{	
		 	    return true;
		   	}	   
	  	}
	  	return false;
	}
	
	function deleteFun()
	{
		var conf=confirm("Do you really want to delete this record ?");
		if(conf) 
		{
		document.getElementById("paraFrm").action="QuestionCategory_delete.action";
		document.getElementById("paraFrm").submit();
		}
	}
	
	function callPage(id)
	{
	   	//alert(id);
	   	document.getElementById('myPage').value=id;
		document.getElementById('paraFrm_show').value=id;
	    document.getElementById('paraFrm').action="QuestionCategory_callPage.action";
	   	document.getElementById('paraFrm').submit();
    }
	
    function next()
    {
   		var pageno=	document.getElementById('myPage').value;
   		if(pageno=="1")
   		{	
   			document.getElementById('myPage').value=2;
	 		document.getElementById('paraFrm_show').value=2;
	 	}
	 	else
	 	{
	 		document.getElementById('myPage').value=eval(pageno)+1;
	 		document.getElementById('paraFrm_show').value=eval(pageno)+1;
	 	}
	   	
	   	document.getElementById('paraFrm').action="QuestionCategory_callPage.action";
	   	document.getElementById('paraFrm').submit();
    }
  	
    function previous()
    {
   		var pageno = document.getElementById('myPage').value;
   	
		document.getElementById('myPage').value=eval(pageno)-1;
	 	document.getElementById('paraFrm_show').value=eval(pageno)-1;
	   	document.getElementById('paraFrm').action="QuestionCategory_callPage.action";
	   	document.getElementById('paraFrm').submit();
    }
   
    function on()
    {
    	var val= document.getElementById('selectname').value;
		document.getElementById('paraFrm_show').value=val;
	 	document.getElementById('myPage').value=eval(val);
	 	document.getElementById('selectname').value=val;
	   	document.getElementById('paraFrm').action="QuestionCategory_callPage.action";
	   
	   	document.getElementById('paraFrm').submit();
    }
   
   	pgshow();
  	
  	function pgshow()
  	{
  		var pgno=document.getElementById('paraFrm_show').value;
  		if(!(pgno==""))
  	 	document.getElementById('selectname').value = pgno;
  	}
  	
  	function newRowColor(cell)
   	{
		cell.className='onOverCell';
	}
	
	function oldRowColor(cell,val) 
	{
		if(val=='1')
		{
	 		cell.className='tableCell2';
		}
		else
		{ 
			cell.className='tableCell1';
		}
		
	}
	
	function callForEdit(id)
	{
	  	document.getElementById('paraFrm_hiddencode').value=id;
	  
	   	document.getElementById("paraFrm").action="QuestionCategory_edit.action"; 
	    document.getElementById("paraFrm").submit();
    }
	
</script>
