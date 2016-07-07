<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="PensionConfiguration" validate="true" id="paraFrm"	theme="simple">
	
	<s:hidden name="hiddencode" />	
  	<s:hidden name="readFlag"></s:hidden>
  	<s:hidden name="sMode"></s:hidden>	
  	<s:hidden name="sPensionCode"></s:hidden>	
  	<s:hidden name="sPensionTypeCode"></s:hidden>	
  	
  	<s:hidden name="sPensionEffFrm"></s:hidden>	
  	<s:hidden name="sPensionMinService"></s:hidden>	
  	<s:hidden name="sPensionMaxService"></s:hidden>	
  	<s:hidden name="sPensionMinAmt"></s:hidden>	
  	<s:hidden name="sPensionEmpStatus"></s:hidden>	
  	<s:hidden name="sPensionEmolFormula"></s:hidden>	
  	<s:hidden name="sPensionEmolMonths"></s:hidden>	
  	<s:hidden name="sPensionAvgEmol"></s:hidden>	
  	<s:hidden name="sPensionFormula"></s:hidden>	
  	<s:hidden name="sPensionVolWeiyears"></s:hidden>	
  	<s:hidden name="sPensionCompFormula"></s:hidden>
  	<!-- Added by nilesh  -->
  	<s:hidden name ="paraId" />
  	<s:hidden name ="editDataId" />
  	
  	
  	
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
							<td width="93%" class="txt"><strong class="text_head">Pension Configuration </strong></td>
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
					<td class="formth" width="5%" align="center"> <label name="sr.no" class = "set" id="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>				
					<td class="formth" width="15%" align="center"> <label name="pens.type.code" class = "set" id="pens.type.code" ondblclick="callShowDiv(this);"><%=label.get("pens.type.code")%></label></td>
					<td class="formth" width="20%" align="left"> <label name="pens.min.service" class = "set"  id="pens.min.service" ondblclick="callShowDiv(this);"><%=label.get("pens.min.service")%></label></td>
					<td class="formth" width="20%" align="center"> <label name="pens.max.service" class = "set"  id="pens.max.service" ondblclick="callShowDiv(this);"><%=label.get("pens.max.service")%></label></td>
					<td class="formth" width="15%" align="left"> <label name="pens.comp.formula" class = "set" id="pens.comp.formula" ondblclick="callShowDiv(this);"><%=label.get("pens.comp.formula")%></label></td>
					<td class="formth" width="15%" align="Center"> <label name="pens.min.amount" class = "set" id="pens.min.amount" ondblclick="callShowDiv(this);"><%=label.get("pens.min.amount")%></label></td>
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
				  
				  
				 <s:if test="%{lstPensionConfiguration == null || lstPensionConfiguration.size() == 0}">
					 <tr>
						<td width="100%" colspan="7" align="center">
							<font color="red">There is no data to display.</font>
						</td>
					 </tr>
				 </s:if> 
				  
				 <s:iterator value="lstPensionConfiguration">
					<tr
						<%if(count%2==0){%> class="tableCell1" <%}else{%>
					 	 class="tableCell2" <%	}count++; %>
						 onmouseover="javascript:newRowColor(this);"
						 title="Double click for edit"
						 onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
						 ondblclick="javascript:callForEdit('<s:property value="sPensionCode" />');" >
						 
							<td width="5%" align="center" class="sortableTD"><%=++cnt%><%++i;%></td>
							<td width="15%" align="center" class="sortableTD"><s:property value="sPensionTypeCode" /></td>
							<td width="20%" align="center" class="sortableTD"><s:property value="sPensionMinService" /></td>
							<td width="20%" align="center" class="sortableTD"><s:property value="sPensionMaxService" /></td>
							<s:if test="%{((sPensionFormula != null) && (sPensionFormula.length() > 0))}">
								<td width="15%" align="center" class="sortableTD"><s:property value="sPensionFormula" /></td>
							</s:if><s:else>	
								<td width="15%" align="center" class="sortableTD"><s:property value="sPensionCompFormula" /></td>
							</s:else>
							<td width="15%" align="right" class="sortableTD"><s:property value="sPensionMinAmt" /></td>
							<td width="10%" align="center" class="sortableTD">
								<input type="hidden" name="hdeleteCode" id="hdeleteCode<%=i%>" />
								<input type="checkbox" align="right" class="checkbox" id="confChk<%=i%>"
								name="confChk" onclick="callForDelete1('<s:property value="sPensionCode" />','<%=i%>')" />
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
    				
    				<s:if test="%{sPensionTypeCode == 1}">
	    				<tr>
							<td width="100%" colspan="4">
								<strong class="forminnerhead"><label
								class="set" id="heading1" name="heading1"
								ondblclick="callShowDiv(this);"><%=label.get("heading1")%></label></strong>
							</td>
						</tr>
					</s:if><s:elseif test="%{sPensionTypeCode == 2}">
						<tr>
							<td width="100%" colspan="4">
								<strong class="forminnerhead"><label
								class="set" id="heading2" name="heading2"
								ondblclick="callShowDiv(this);"><%=label.get("heading2")%></label></strong>
							</td>
						</tr>
					
					</s:elseif> <s:elseif test="%{sPensionTypeCode == 3}">
						<tr>
							<td width="100%" colspan="4">
								<strong class="forminnerhead"><label
								class="set" id="heading3" name="heading3"
								ondblclick="callShowDiv(this);"><%=label.get("heading3")%></label></strong>
							</td>
						</tr>
					</s:elseif> <s:elseif test="%{sPensionTypeCode == 4}">
						<tr>
							<td width="100%" colspan="4">
								<strong class="forminnerhead"><label
								class="set" id="heading4" name="heading4"
								ondblclick="callShowDiv(this);"><%=label.get("heading4")%></label></strong>
							</td>
						</tr>
					</s:elseif> 
					
					
					
			  		<tr>
			     		<td width="30%" class="formtext">
							<label name="pens.effective.from" class = "set" id="pens.effective.from" ondblclick="callShowDiv(this);"><%=label.get("pens.effective.from")%></label>
							<font color="#FF0000">*</font>
						</td>
			            <td width="70%" height="22">
							<s:property value="sPensionEffFrm" />
						</td>
			        </tr>
			        
			        <tr>
			     		<td width="30%" class="formtext">
							<label name="pens.min.service" class = "set" id="pens.min.service" ondblclick="callShowDiv(this);"><%=label.get("pens.min.service")%></label>
							<font color="#FF0000">*</font>
						</td>
			            <td width="70%" height="22">
							<s:property value="sPensionMinService" />
						</td>
			        </tr>
			        
			        <tr>
			     		<td width="30%" class="formtext">
							<label name="pens.max.service" class = "set" id="pens.max.service" ondblclick="callShowDiv(this);"><%=label.get("pens.max.service")%></label>
							<font color="#FF0000">*</font>
						</td>
			            <td width="70%" height="22">
							<s:property value="sPensionMaxService" />
						</td>
			        </tr>
			        
			        <tr>
			     		<td width="30%" class="formtext">
							<label name="pens.min.amount" class = "set" id="pens.min.amount" ondblclick="callShowDiv(this);"><%=label.get("pens.min.amount")%></label>
							<font color="#FF0000">*</font>
						</td>
			            <td width="70%" height="22">
							<s:property value="sPensionMinAmt" />
						</td>
			        </tr>
			        
			        <tr>
			     		<td width="30%" class="formtext">
							<label name="pens.emp.status" class = "set" id="pens.emp.status" ondblclick="callShowDiv(this);"><%=label.get("pens.emp.status")%></label>
							<font color="#FF0000">*</font>
						</td>
			            <td width="70%" height="22">
							<s:if test="%{sPensionEmpStatus == 1}">
								Superannuation
							</s:if><s:elseif test="%{sPensionEmpStatus == 2}">
								Voluntary
							</s:elseif><s:elseif test="%{sPensionEmpStatus == 3}">
								Compulsory Retirement
							</s:elseif><s:elseif test="%{sPensionEmpStatus == 4}">
								Death
							</s:elseif>
							
						</td>
			        </tr>
			        
			        <tr>
						<td width="100%" colspan="4">
							&nbsp;
						</td>
					</tr>
			        
			        <tr>
						<td width="100%" colspan="4">
							<strong class="forminnerhead"><label
							class="set" id="sub.heading" name="sub.heading"
							ondblclick="callShowDiv(this);"><%=label.get("sub.heading")%></label></strong>
						</td>
					</tr>
					
			        <tr>
			     		<td width="30%" class="formtext">
							<label name="pens.emol.formula" class = "set" id="pens.emol.formula" ondblclick="callShowDiv(this);"><%=label.get("pens.emol.formula")%></label>
							<font color="#FF0000">*</font>
						</td>
			            <td width="70%" height="22">
							<s:property value="sPensionEmolFormula" />
						</td>
			        </tr>
			        
			        <tr>
			     		<td width="30%" class="formtext">
							<label name="pens.emol.months" class = "set" id="pens.emol.months" ondblclick="callShowDiv(this);"><%=label.get("pens.emol.months")%></label>
							<font color="#FF0000">*</font>
						</td>
			            <td width="70%" height="22">
							<s:property value="sPensionEmolMonths" />
						</td>
			        </tr>
			        
			        <tr>
			     		<td width="30%" class="formtext">
							<label name="pens.avg.emol" class = "set" id="pens.avg.emol" ondblclick="callShowDiv(this);"><%=label.get("pens.avg.emol")%></label>
							<font color="#FF0000">*</font>
						</td>
			            <td width="70%" height="22">
							<s:property value="sPensionAvgEmol" />
						</td>
			        </tr>
			        
			        <tr>
			     		<td width="30%" class="formtext">
							<label name="pens.comp.formula" class = "set" id="pens.comp.formula" ondblclick="callShowDiv(this);"><%=label.get("pens.comp.formula")%></label>
							<font color="#FF0000">*</font>
						</td>
			            <td width="70%" height="22">
							<s:if test="%{(sPensionFormula.length() > 0)}">
								<s:property value="sPensionFormula" />
							</s:if><s:else>
								<s:property value="sPensionCompFormula" />
							</s:else>
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
		document.getElementById("paraFrm").action="PensionConfiguration_addNew.action";
		document.getElementById("paraFrm").submit();	
		return false;
	}
	
	function searchFun() {
		callsF9(500,325,'PensionConfiguration_search.action');
		return false;
	}
	
	function callForDelete(id) {
	  	document.getElementById('paraFrm_hiddencode').value=id;
	   	document.getElementById("paraFrm").action="PensionConfiguration_delete.action";
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
				document.getElementById('paraFrm').action="PensionConfiguration_deleteMultipleRecords.action";
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
			document.getElementById("paraFrm").action="PensionConfiguration_delete.action";
			document.getElementById("paraFrm").submit();
			return false;
		}
	}
	
	function callPage(id)
	{
	   	//alert(id);
	   	document.getElementById('myPage').value=id;
		document.getElementById('paraFrm_show').value=id;
	    document.getElementById('paraFrm').action="PensionConfiguration_callPage.action";
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
	   	
	   	document.getElementById('paraFrm').action="PensionConfiguration_callPage.action";
	   	document.getElementById('paraFrm').submit();
    }
  	
    function previous()
    {
   		var pageno = document.getElementById('myPage').value;
   	
		document.getElementById('myPage').value=eval(pageno)-1;
	 	document.getElementById('paraFrm_show').value=eval(pageno)-1;
	   	document.getElementById('paraFrm').action="PensionConfiguration_callPage.action";
	   	document.getElementById('paraFrm').submit();
    }
   
    function on()
    {
    	var val= document.getElementById('selectname').value;
		document.getElementById('paraFrm_show').value=val;
	 	document.getElementById('myPage').value=eval(val);
	 	document.getElementById('selectname').value=val;
	   	document.getElementById('paraFrm').action="PensionConfiguration_callPage.action";
	   
	   	document.getElementById('paraFrm').submit();
    }
   
   	pgshow();
  	
  	function pgshow()
  	{
  		var pgno=document.getElementById('paraFrm_show').value;
  		if(!(pgno==""))
  	 	document.getElementById('selectname').value = pgno;
  	}
  	
  	function callForEdit(id)
	{
	  	document.getElementById('paraFrm_hiddencode').value=id;
	  	document.getElementById('paraFrm_sMode').value='update';
	  
	   	document.getElementById("paraFrm").action="PensionConfiguration_edit.action"; 
	    document.getElementById("paraFrm").submit();
    }
    
    function cancelFun() {
		document.getElementById("paraFrm").action="PensionConfiguration_input.action";
		document.getElementById("paraFrm").submit();	
		return false;
	}
	
	function editFun() {
		document.getElementById("paraFrm").action="PensionConfiguration_editSearch.action";
		document.getElementById("paraFrm").submit();	
		return false;		
	}
	
</script>	