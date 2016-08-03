<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>

 <s:form  action="AppraisalSchedule" validate="true" id="paraFrm"
	theme="simple">
	
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />
	<s:hidden name="readFlag"></s:hidden>
	 <s:hidden name="apprId"></s:hidden>
     <s:hidden name="apprCode"></s:hidden>
     <s:hidden name="startDate"></s:hidden>
     <s:hidden name="endDate"></s:hidden>
     <s:hidden name="addFlag"></s:hidden>
        <s:hidden name="appStarted"></s:hidden>
    
              
	  <table width="100%" border="0" align="right" cellpadding="2" cellspacing="1" class="formbg" >
   		 
   		<tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Appraisal Schedule</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
	    
	    <s:if test="readFlag">
	    <tr>
			<td colspan="3">

			<table width="100%" border="0" cellpadding="2" cellspacing="2">

				<tr>
				<td width="78%">
	            <jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
	          			
			</td>
					<td align="right"><b>Page:</b> <%
	int total1 = (Integer) request.getAttribute("abc");
	int PageNo1 = (Integer) request.getAttribute("xyz");
%> <%
	if (!(PageNo1 == 1)) {
%><a href="#" onclick="callPage('1');"> <img
						src="../pages/common/img/first.gif" width="10" height="10"
						class="iconImage" /> </a>&nbsp; <a href="#" onclick="previous()"><img
						src="../pages/common/img/previous.gif" width="10" height="10"
						class="iconImage" /></a> <%
 }
 if (total1 < 5) {
 	for (int i = 1; i <= total1; i++) {
 %> <a href="#" onclick="callPage('<%=i %>');"> <%
 if (PageNo1 == i) {
 %> <b><u><%=i%></u></b> <%
 } else
 %><%=i%> </a> <%
 		}
 		}

 		if (total1 >= 5) {
 			for (int i = 1; i <= 5; i++) {
 %> <a href="#" onclick="callPage('<%=i %>');"> <%
 if (PageNo1 == i) {
 %> <b><u><%=i%></u></b> <%
 } else
 %><%=i%> </a> <%
 		}
 		}
 		if (!(PageNo1 ==1)) {
 %>...<a href="#" onclick="next()"> <img
						src="../pages/common/img/next.gif" class="iconImage" /> </a> &nbsp;<a
						href="#" onclick="callPage('<%=total1%>');"> <img
						src="../pages/common/img/last.gif" width="10" height="10"
						class="iconImage" /></a> <%
 }
 %> <select name="selectname" onchange="on()" id="selectname">
						<%
for (int i = 1; i <= total1; i++) {
	%>

						<option value="<%=i%>"><%=i%></option>
						<%
}
	%>
					</select></td>
				</tr>



			</table>
			</td>
		</tr>
		
		 <tr>
			<td colspan="3">
			<%
			try {
			%>
			<table width="100%" border="0" cellpadding="2" cellspacing="1"
				class="formbg">

				<tr>

					<td colspan="4" class="formtext">
					<table width="100%" border="0" cellpadding="1" cellspacing="1">
						<tr>
							<s:hidden name="myPage" id="myPage" />
							<td class="formth" align="center" colspan="1" width="10%"><label name="appr.srno" class = "set"  id="appr.srno" ondblclick="callShowDiv(this);"><%=label.get("appr.srno")%></label></td>
												
							<td class="formth" align="center" colspan="1" width="55%" nowrap="nowrap" ><label name="appraisal.code" class = "set"  id="appraisal.code" ondblclick="callShowDiv(this);"><%=label.get("appraisal.code")%></label></td>
							<td class="formth" align="center" colspan="1" width="15%" nowrap="nowrap" ><label name="appr.start.date" class = "set"  id="appr.start.date" ondblclick="callShowDiv(this);"><%=label.get("appr.start.date")%></label></td>
							<td class="formth" align="center" colspan="1" width="15%" nowrap="nowrap" ><label name="appr.end.date" class = "set"  id="appr.end.date" ondblclick="callShowDiv(this);"><%=label.get("appr.end.date")%></label></td>
							<td align="right" class="formth" nowrap="nowrap" colspan="1"
								width="15%"><s:submit cssClass="delete" theme="simple" onclick=" return chkDelete()"
								value="     Delete  " /></td>

							<%int count=0; %>
							<%! int d=0; %>
							<%
							int cnt= PageNo1*20-20;	
							int i = 0;
								%>

							<s:iterator value="dataList">

								<tr <%if(count%2==0){
									%> class="tableCell1" <%}else{%>
									class="tableCell2" <%	}count++; %>
									onmouseover="javascript:newRowColor(this);"
									title="Double click for edit"
									onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
									ondblclick="javascript:callForEdit('<s:property value="apprId" />');">


									<td width="10%" align="center" class="sortableTD"><%=++cnt%><%++i;%></td>
									<s:hidden value="%{apprId}"/>


								
									<td width="55%" align="left" class="sortableTD"><s:property value="apprCode" /></td>
									<td width="15%" align="center" class="sortableTD"><s:property value="startDate" /></td>
									<td width="15%" align="center" class="sortableTD"><s:property value="endDate" /><s:hidden name="lockAppr"/>
									<input type="hidden" name="hdeleteCode" id="hdeleteCode<%=i%>" />
									</td>
									<td align="center" nowrap="nowrap" class="sortableTD"><input
										type="checkbox" align="right" class="checkbox" id="confChk<%=i%>"
										name="confChk"
										onclick="callForDelete1('<s:property value="apprId" />','<%=i%>')" /></td>
								</tr>

							</s:iterator>
							<%d=i; %>
						</tr>
				</table>

					<%
				} catch (Exception e) {
				}
			%>
					</td>
				</tr>
				
				</table></td></tr>
 <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2">
         <tr>
            <td width="78%">
            <jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
          			
	</td>
           
          </tr>
        </table>
          <label></label></td>
    </tr>
	    </s:if ><s:else>
	    
	    <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2">
         <tr>
            <td width="78%">
            <jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
          			
	</td>
           
          </tr>
        </table>
          <label></label></td>
    </tr>
	    
	    
	    <tr>
		      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
		          <tr>
		            <td>
		            <table width="98%" border="0" align="center" cellpadding="2" cellspacing="2">
		            <tr>
		              <td width="10%" colspan="1" height="20" class="formtext"><label name="appraisal.code"  class = "set"  id="appraisal.code" ondblclick="callShowDiv(this);"><%=label.get("appraisal.code")%></label> <font color="red">*</font> :</td>
					  <td width="40%" colspan="1" height="20"><s:property value="apprCode"/>
		              <td width="50%" colspan="2" height="20" class="formtext" align="left">
		              	<label name="appr.from.date" class = "set"  id="appr.from.date" ondblclick="callShowDiv(this);"><%=label.get("appr.from.date")%></label> :  &nbsp;&nbsp; <s:property value="startDate"></s:property>&nbsp;&nbsp;<label name="appr.to.date" class = "set"  id="appr.to.date" ondblclick="callShowDiv(this);"><%=label.get("appr.to.date")%></label> : &nbsp;&nbsp;<s:property value="endDate"></s:property>
		              </td>
		            </tr>
		       
		            </table></td>
		          </tr>
	  
	    
	  <tr>
      <td colspan="3">
     
      <table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg" >
          <tr>
										<td class="formth" width="5%"><label  class = "set"  id="phase.srno" ondblclick="callShowDiv(this);"><%=label.get("phase.srno")%></label></td>
										<td class="formth" width="30%" align="left"><label  class = "set"  id="phase.name" ondblclick="callShowDiv(this);"><%=label.get("phase.name")%></label></td>
										<td class="formth" width="20%" align="center"><label  class = "set"  id="phase.start.date" ondblclick="callShowDiv(this);"><%=label.get("phase.start.date")%></label></td>
										<td class="formth" width="20%" align="center"><label  class = "set"  id="phase.end.date" ondblclick="callShowDiv(this);"><%=label.get("phase.end.date")%></label></td>
										<td class="formth" width="15%"><label  class = "set"  id="phase.lock" ondblclick="callShowDiv(this);"><%=label.get("phase.lock")%></label></td>
									</tr>

							<%
						int j = 1 , c=0;
							
							%>
							<s:iterator value="phaseList">
											
								<tr>
									<td width="5%" align="center" class="sortableTD"><%=j%></td>
									<td width="30%" align="left" class="sortableTD"><s:hidden name="phaseCode" /> <s:hidden name="phaseName"></s:hidden><s:property
										value="phaseName" /></td>
										<td width="20%" align="center" class="sortableTD"><s:hidden name="phaseStartDate"></s:hidden><s:property value="phaseStartDate"/>
										</td>
									<td width="20%" class="sortableTD" align="center"><s:hidden name="phaseEndDate"></s:hidden><s:property value="phaseEndDate"/>
									</td>
									<td width="15%" class="sortableTD" align="center">
									<s:if test='%{phaseLockFlag=="Y"}'>
									 Yes
									</s:if >
									<s:else>
									 No
									</s:else>
										<s:hidden name="phaseLockFlag"></s:hidden>
									</td>
									
								
								 <%j++; c++;%>
								 </tr>
							</s:iterator>
							<tr><td><input type="hidden" name="count" id="count" value="<%=c%>"/></td></tr>
      </table>
      </td>
    </tr>
    
            
	    </table></td></tr>
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
	    </s:else>
	    </table>
	    </s:form>
	<script type="text/javascript">

function addnewFun(){
			document.getElementById("paraFrm").action="AppraisalSchedule_addNew.action";
			document.getElementById("paraFrm").submit();
}
function searchFun(){
		
		callsF9(500,325,'AppraisalSchedule_search.action');
		
}
function editFun(){
	document.getElementById("paraFrm").action="AppraisalSchedule_edit.action";
			document.getElementById("paraFrm").submit();
}
function cancelFun(){
	document.getElementById("paraFrm").action="AppraisalCalendar_input.action";
			document.getElementById("paraFrm").submit();
}
function deleteFun(){
		
		if(document.getElementById("paraFrm_phaseLockFlag").value == 'N')
			{
			
 				var conf=confirm("Do you really want to delete this record ?");
  					if(conf) {
							document.getElementById("paraFrm").action="AppraisalSchedule_delete.action";
							document.getElementById("paraFrm").submit();
						}
			}else{
			
				alert("Appraisal schedule cannot be deleted as it is referenced to some other resources.");
			}
}
function callPage(id){
	   	document.getElementById('myPage').value=id;
	document.getElementById('paraFrm_show').value=id;
	    document.getElementById('paraFrm').action="AppraisalSchedule_callPage.action";
	   document.getElementById('paraFrm').submit();
   }
   
   
   function next()
   {
   var pageno=	document.getElementById('myPage').value;
   	if(pageno=="1")
   	{	document.getElementById('myPage').value=2;
	 document.getElementById('paraFrm_show').value=2;
	 }
	 else{
	 document.getElementById('myPage').value=eval(pageno)+1;
	 document.getElementById('paraFrm_show').value=eval(pageno)+1;
	 }
	   document.getElementById('paraFrm').action="AppraisalSchedule_callPage.action";
	   document.getElementById('paraFrm').submit();
   }
  	
  	
  	
  	//-----function for previous
  	 function previous()
   {
   var pageno=	document.getElementById('myPage').value;
   	
	 document.getElementById('myPage').value=eval(pageno)-1;
	 document.getElementById('paraFrm_show').value=eval(pageno)-1;
	   document.getElementById('paraFrm').action="AppraisalSchedule_callPage.action";
	   document.getElementById('paraFrm').submit();
   }
  	function on()
   {
  	var val= document.getElementById('selectname').value;
	document.getElementById('paraFrm_show').value=val;
	 document.getElementById('myPage').value=eval(val);
	 document.getElementById('selectname').value=val;
	   document.getElementById('paraFrm').action="AppraisalSchedule_callPage.action";
	   
	   document.getElementById('paraFrm').submit();
   }
  	
  
  	
  	pgshow();
  	function pgshow()
  	{
  	var pgno=document.getElementById('paraFrm_show').value;
  
  	if(!(pgno==""))
  	 document.getElementById('selectname').value=pgno;
  	}



	function callForEdit(id){
		
	  	document.getElementById('paraFrm_hiddencode').value=id;
	  
	   	document.getElementById("paraFrm").action="AppraisalSchedule_calforedit.action"; 
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
	   document.getElementById('hdeleteCode'+i).value="";
	   
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
	
	function chkDelete()
	{
	
	 if(chk()){	
				 var con=confirm('Do you want to  delete the record ?');
			 	if(con){
			 	  document.getElementById('paraFrm').action="AppraisalSchedule_delete1.action";
			   	 document.getElementById('paraFrm').submit();
			   		 }
			    else
			     return false;
	     }
	 else {
	  alert('Please select atleast one record to delete');
	 	 return false;
	 }
	 
	}
	
	function chk(){
	 var flag='<%=d %>';
	  for(var a=1;a<=flag;a++){	
	   if(document.getElementById('confChk'+a).checked == true)
	   {	
	 	    return true;
	   }	   
	  }
	  return false;
	}
   
	


</script>    
	    