<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="QuestionCategory" validate="true" id="paraFrm" theme="simple">

  <s:hidden name="hiddencode"></s:hidden>	
  <s:hidden name="readFlag"></s:hidden>
  <s:hidden name="sMode"></s:hidden>		
  <s:hidden name="sQuestionCode"></s:hidden>
  <s:hidden name="sQuestion"></s:hidden>
  <s:hidden name="sQuestionType"></s:hidden>
  <s:hidden name="sQuestionStatus"></s:hidden>
  <s:hidden name="sAnwserLimit"></s:hidden>
  <s:hidden name="sQuestionMandatory"></s:hidden>
  <s:hidden name="sQuestionCategoryCode"></s:hidden>
  <s:hidden name="sQuestionCategoryName"></s:hidden>
  <s:hidden name="flgStatus"></s:hidden>
  <s:hidden name="flgQuestionType"></s:hidden>
  		
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
							<td width="93%" class="txt"><strong class="text_head">Questions Definition</strong></td>
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
				<td class="formth" width="5%" align="center"> <label  name="questionnairedefinition.srno" class = "set" id="questionnairedefinition.srno" ondblclick="callShowDiv(this);"><%=label.get("questionnairedefinition.srno")%></label></td>
				<td class="formth" width="30%" align="left"> <label name="questionnairedefinition.question" class = "set"  id="questionnairedefinition.question" ondblclick="callShowDiv(this);"><%=label.get("questionnairedefinition.question")%></label></td>
				<td class="formth" width="10%" align="center"> <label name="questionnairedefinition.answertype" class = "set"  id="questionnairedefinition.answertype" ondblclick="callShowDiv(this);"><%=label.get("questionnairedefinition.answertype")%></label></td>
				<td class="formth" width="10%" align="center"> <label name="questionnairedefinition.status" class = "set" id="questionnairedefinition.status" ondblclick="callShowDiv(this);"><%=label.get("questionnairedefinition.status")%></label></td>
				<td class="formth" width="10%" align="center"> <label name="questionnairedefinition.mandedory" class = "set" id="questionnairedefinition.mandedory" ondblclick="callShowDiv(this);"><%=label.get("questionnairedefinition.mandedory")%></label></td>
				<td class="formth" width="25%" align="center"> <label name="questionnairedefinition.questioncategory" class = "set" id="questionnairedefinition.questioncategory" ondblclick="callShowDiv(this);"><%=label.get("questionnairedefinition.questioncategory")%></label></td>
				
				<td class="formth" width="10%" align="center" nowrap="nowrap">
					<input type="button" name="delete" value="     Delete " class="delete" onClick="deleteMultipleRecordFun()">
				</td>
			  </tr>
	
			 <%int count = 0; %>
			 <%! int d = 0; %>
			 <%
			 	int cnt = PageNo1*20-20;	
				int i = 0;
			%>
			  
			  <s:iterator value="lstQuestion">
					<tr
						<%if(count%2==0){%> class="tableCell1" <%}else{%>
					 	 class="tableCell2" <%	}count++; %>
						 onmouseover="javascript:newRowColor(this);"
						 title="Double click for edit"
						 onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
						 ondblclick="javascript:callForEdit('<s:property value="sQuestionCode" />');" >
						 
							<td width="5%" align="center" class="sortableTD"><%=++cnt%><%++i;%></td>
							<td width="30%" align="left" class="sortableTD"><s:property value="sQuestion" /></td>
							<td width="10%" align="center" class="sortableTD"><s:property value="sQuestionType" /></td>
							<td width="10%" align="center" class="sortableTD"><s:property value="sQuestionStatus" /></td>
							<td width="10%" align="center" class="sortableTD"><s:property value="sQuestionMandatory" /></td>
							<td width="25%" align="center" class="sortableTD"><s:property value="sQuestionCategoryName" /></td>
							
							<td width="10%" align="center" class="sortableTD">
								<input type="hidden" name="hdeleteCode" id="hdeleteCode<%=i%>" />
								<input type="checkbox" align="right" class="checkbox" id="confChk<%=i%>"
								name="confChk" onclick="callForDelete1('<s:property value="sQuestionCode" />','<%=i%>')" />
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
   	
	</s:if> 
	<s:else>
	 
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
    	
	
  	<tr>
    	<td colspan="3">
    		<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
              <tr>
                <td>
					<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2">
                  			
                  			<tr>
                    			<td width="15%" height="30" class="formtext">
                    				<label name="questionnairedefinition.question" class = "set"  id="questionnairedefinition.question" ondblclick="callShowDiv(this);"><%=label.get("questionnairedefinition.question")%></label>&nbsp; 
                    				<font color="#FF0000">*</font>: 
                    			</td>
                    			<td width="75%" height="30" colspan="3">
									<!--  <textarea rows="2" name="sQuestion" cols="85"></textarea> -->
									<s:property value="sQuestion" />
                      			</td>
                    			<td width="10%" height="30">
									&nbsp;
								</td>
        					</tr>
                 
        					<tr>
                    			<td width="15%" height="30" class="formtext">
                    				<label name="questionnairedefinition.answertype" class = "set"  id="questionnairedefinition.answertype" ondblclick="callShowDiv(this);"><%=label.get("questionnairedefinition.answertype")%></label> :
                    			</td>

                    			<td width="30%" height="30" nowrap="nowrap">
                    				<!--  
									<input type="radio" name="sQuestionType" value="O" checked onclick="return callCharDiv(this);" disabled="true">Objective 
									<input type="radio" name="sQuestionType" value="D" onclick="return callCharDiv(this);" disabled="true">Descriptive
									--> 
									<s:if test="flgQuestionType">
										<input type="radio" name="sQuestionType" value="O" checked="checked" disabled="disabled"> 
										Objective 
										
										<input type="radio" name="sQuestionType" value="D" disabled="disabled" > 
										Descriptive
									</s:if>
									<s:else>	
										<input type="radio" name="sQuestionType" value="O" disabled="disabled"> 
										Objective
										 
										<input type="radio" name="sQuestionType" value="D" checked="checked"  disabled="disabled"> 
										Descriptive
									</s:else> 
								</td>
								
                    			<td width="15%" height="30" class="formtext" >
                    				<label name="questionnairedefinition.status" class = "set"  id="questionnairedefinition.status" ondblclick="callShowDiv(this);"><%=label.get("questionnairedefinition.status")%></label> :
                    			</td>
                    			
                    			<td width="30%" height="30">
									<!--  
									<input type="radio" name="sQuestionStatus" value="A" checked="checked" disabled="disabled">Active
									<input type="radio" name="sQuestionStatus" value="D" disabled="disabled">De-Active
									-->
									<s:if test="flgStatus">
										<input type="radio" name="sQuestionStatus" value="A" checked="checked" disabled="disabled"> 
										Active 
										
										<input type="radio" name="sQuestionStatus" value="D" disabled="disabled"> 
										De-Active
									</s:if> 
									<s:else>
										<input type="radio" name="sQuestionStatus" value="A" disabled="disabled"> 
										Active
										
										<input type="radio" name="sQuestionStatus" value="D" checked="checked" disabled="disabled"> 
										De-Active
									</s:else>
								</td>
								                    
								<td width="10%" height="30"> 
									&nbsp;
								</td>
							</tr>
							
							<s:if test="%{flgQuestionType == false}">
							<tr>
                    			<td width="15%"> 
									&nbsp;
								</td>
								
								<td width="85%" colspan="4">
									<div id="charDiv">
										<table width="100%" border=0>
											<tr>
									  			<td width="15%">
													<label name="charLimit" class = "set"  id="charLimit" ondblclick="callShowDiv(this);"><%=label.get("charLimit")%></label> :
												</td>
												<td width="7%" align="left">
													<s:property value="sAnwserLimit" />
												</td> 
												<td width="78%">
													&nbsp;&nbsp;&nbsp;
													Note : Default character length will be 500 chars. Maximum character length will be 2000 chars.
												</td>
											</tr>
										</table>
									</div>
								</td>
				   			</tr>
				   			</s:if>
							
                  			<tr>
								<td width="15%" height="30">
									<label name="questionnairedefinition.mandedory" class = "set"  id="questionnairedefinition.mandedory" ondblclick="callShowDiv(this);"><%=label.get("questionnairedefinition.mandedory")%></label> :  
								</td>
								
								<td width="30%" height="30" class="formtext">
									<s:property value = "sQuestionMandatory" />
									</select>
								</td>
					
				   				<td width="15%" height="30">
				   					<label name="questionnairedefinition.questioncategory" class = "set"  id="questionnairedefinition.questioncategory" ondblclick="callShowDiv(this);"><%=label.get("questionnairedefinition.questioncategory")%></label> : 
				   				</td>
								
								<td width="30%" height="30">
									<s:property value = "sQuestionCategoryName" />
									</select>
								</td>
								
								<td width="10%" height="30"> 
									&nbsp;
								</td>
                  			</tr>
                  	</table>                
				 </td>
              </tr>
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
	   
	</s:else> 
</table>
	
</s:form>

<script type="text/javascript">


	function addnewFun() {
		document.getElementById("paraFrm").action="QuestionnaireDefinition_addNew.action";
		document.getElementById("paraFrm").submit();	
	}
	
	function searchFun() {
		callsF9(500,325,'QuestionnaireDefinition_search.action');
	}
	
	function saveFun() {
		document.getElementById("paraFrm").action="QuestionnaireDefinition_addNew.action";
		document.getElementById("paraFrm").submit();
			
	}
	
	function resetFun() {
		document.getElementById("paraFrm").action="QuestionnaireDefinition_reset.action";
		document.getElementById("paraFrm").submit();
			
	}
	
	function cancelFun() {
		document.getElementById("paraFrm").action="QuestionnaireDefinition_input.action";
		document.getElementById("paraFrm").submit();			
	}
	
	function editFun() {
		document.getElementById("paraFrm").action="QuestionnaireDefinition_editSearch.action";
		document.getElementById("paraFrm").submit();			
	}
	
	function callForDelete(id) {
	  	document.getElementById('paraFrm_hiddencode').value=id;
	   	document.getElementById("paraFrm").action="QuestionnaireDefinition_delete.action";
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
				document.getElementById("paraFrm").action="QuestionnaireDefinition_delete.action";
				document.getElementById("paraFrm").submit();
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
			document.getElementById("paraFrm").action="QuestionnaireDefinition_deleteSelectedRecord.action";
			document.getElementById("paraFrm").submit();
		}
	}
	
	
	function callPage(id)
	{
	   	//alert(id);
	   	document.getElementById('myPage').value=id;
		document.getElementById('paraFrm_show').value=id;
	    document.getElementById('paraFrm').action="QuestionnaireDefinition_callPage.action";
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
	   	
	   	document.getElementById('paraFrm').action="QuestionnaireDefinition_callPage.action";
	   	document.getElementById('paraFrm').submit();
    }
  	
    function previous()
    {
   		var pageno = document.getElementById('myPage').value;
   	
		document.getElementById('myPage').value=eval(pageno)-1;
	 	document.getElementById('paraFrm_show').value=eval(pageno)-1;
	   	document.getElementById('paraFrm').action="QuestionnaireDefinition_callPage.action";
	   	document.getElementById('paraFrm').submit();
    }
   
    function on()
    {
    	var val= document.getElementById('selectname').value;
		document.getElementById('paraFrm_show').value=val;
	 	document.getElementById('myPage').value=eval(val);
	 	document.getElementById('selectname').value=val;
	   	document.getElementById('paraFrm').action="QuestionnaireDefinition_callPage.action";
	   
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
	
	//function editFun() {
	//	document.getElementById("paraFrm").action="QuestionnaireDefinition_edit.action";
	//	document.getElementById("paraFrm").submit();			
	//}
	
	function callForEdit(id)
	{
	  	document.getElementById('paraFrm_hiddencode').value=id;
	  
	   	document.getElementById("paraFrm").action="QuestionnaireDefinition_edit.action"; 
	    document.getElementById("paraFrm").submit();
    }
	
</script>
