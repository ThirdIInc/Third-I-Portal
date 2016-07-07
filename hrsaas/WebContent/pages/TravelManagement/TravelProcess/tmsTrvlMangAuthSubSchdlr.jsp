<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<s:form action="TrvlMangAuthorities_input" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />
	<s:hidden name="myHiddenEmpCode" />

	<!-- flags for paging and navigation panel -->
	<s:hidden name="panelFlag" />
	<s:hidden name="retrnFlag" />
	<s:hidden name="myHidden" />
	<s:hidden name="branchCode"/>
	<s:hidden name="subTabLength"/>
	<s:hidden name="hidTabEmpId"/>
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="2" class="formbg">

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Sub-Scheduler List</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:hidden name="mainSchdlrCode" />
		<s:hidden name="altMainSchdlrCode" />
		<s:hidden name="schdlrApprCode" />
		<s:hidden name="accntCode" />

		<!-- The Following code Denotes  Include JSP Page For Button Panel -->

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="22%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:hidden name="employeeCode" />
		<s:hidden name="employeeToken" />
		<!-- for iterator 
		<s:hidden name="itEmployee" />
		<s:hidden name="itEmployeeCode" />
		
		<s:hidden name="itTravel" />
		<s:hidden name="itLocalConv" />
		<s:hidden name="itLodging" />-->


		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>

					<td colspan="4">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2">


						<tr>
							<td valign="top" height="22" class="formtext" colspan="1"
								width="19%"><label
								class="set" id="emp1" name="emp"
								ondblclick="callShowDiv(this);"><%=label.get("emp")%></label><font color="red">*</font>:</td>
							<td width="30%" height="22" colspan="2"><s:textfield
								name="employee" size="35" theme="simple" readonly="true" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'TrvlMangAuthorities_f9employee.action');"></td>
							<td width="30%" height="22" colspan="1"><input type="button"
								class="add" value="  Add " onclick="return add();" /></td>

						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>





		<tr>
			<td colspan="3">
			<%
			try {
			%>
			<table width="100%" border="0" cellpadding="0" cellspacing="1"
				class="formbg" class="sortable">

				<tr>

					<td class="formtext">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="sortable">
						<tr class="td_bottom_border">
							<td height="15" class="formhead" nowrap="nowrap"><strong
								class="forminnerhead">Sub-Scheduler List</strong></td>
						</tr>
						<tr>
							<s:hidden name="myPage" id="myPage" />
							<td class="formth" align="center" width="5%"><strong><label
								class="set" id="sr.no3" name="sr.no"
								ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></strong></td>
							<td class="formth" align="center" width="15%"><strong><label
								class="set" id="emp.id3" name="emp.id"
								ondblclick="callShowDiv(this);"><%=label.get("emp.id")%></label></strong></td>

							<td class="formth" align="center" width="15%"><strong><label
								class="set" id="emp3" name="emp"
								ondblclick="callShowDiv(this);"><%=label.get("emp")%></label></strong></td>
						
						<!-- 
								<td class="formth" align="center" width="15%"><strong><label
								class="set" id="travel3" name="travel"
								ondblclick="callShowDiv(this);"><%=label.get("travel")%></label></strong></td>

							<td class="formth" align="center" width="15%"><strong><label
								class="set" id="local.conv3" name="local.conv"
								ondblclick="callShowDiv(this);"><%=label.get("local.conv")%></label></strong></td>

							<td class="formth" align="center" width="15%"><strong><label
								class="set" id="lodge" name="lodge"
								ondblclick="callShowDiv(this);"><%=label.get("lodge")%></label></strong></td>
						 -->
						
							<td align="right" class="formth" nowrap="nowrap" width="15%">
							<input type="button" class="delete" theme="simple"
								value=" Remove " onclick=" return chkDeleteSub();" /></td>


							<%
							int count = 0;
							%>
							<%!int d = 0;%>
							<%							
							int i = 0,
							  j=0;
							%>




							<s:iterator value="subSchdlrList">
								<tr id="tr1" <%if(count%2==0){
									%> class="tableCell1"
									<%}else{%> class="tableCell2" <%	}count++; %>
									onmouseover="javascript:newRowColor(this);"
									title="Double click for edit"
									onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
									ondblclick="javascript:callForEdit('<s:property  value="itEmployeeCode"/>');">


									<td align="left" class="sortableTD" nowrap="nowrap"><s:hidden
										name="srNo" /><s:property value="srNo" /> <%
									++i;
									%>
									</td>




									<td align="left" class="sortableTD"><input type="hidden"
										name="itEmployeeToken" value="<s:property
										value="itEmployeeToken" />"  id="itEmployeeToken<%=i %>"/> <s:property
										value="itEmployeeToken" /><input type="hidden" name="itEmployeeCode" value="<s:property value="itEmployeeCode" />" id="itEmployeeCode<%=i%>"/></td>

									<td align="left" class="sortableTD"><input type="hidden"
										name="itEmployee"  id="itEmployee<%=i %>" value="<s:property value="itEmployee" />"/> <s:property value="itEmployee" />
									
									<input
										type="hidden" class="checkbox" id="itTravel<%=i%>" <s:property value="itTravel"/>
										name="itTravel"  onclick="chkTrvl();"/>
										<input type="hidden" name="hidItTravel" id="hidItTravel<%=i%>" value="<s:property value="hidItTravel" />"/>
									
									<input
										type="hidden" class="checkbox" id="itLocalConv<%=i%>" <s:property value="itLocalConv"/>
										name="itLocalConv" onclick="chkConv();"/>
										<input type="hidden" name="hidItLocalConv" id="hidItLocalConv<%=i%>" value="<s:property value="hidItLocalConv" />"/>
									
									<input
										type="hidden" class="checkbox" id="itLodging<%=i%>" <s:property value="itLodging"/>
										name="itLodging" onclick="chkLodge();"/>									
										<input type="hidden" name="hidItLodging" id="hidItLodging<%=i%>" value="<s:property value="hidItLodging" />"/>
																		
										</td>

							<!-- 
										<td align="center" nowrap="nowrap" class="sortableTD"><input
										type="checkbox" class="checkbox" id="itTravel<%=i%>" <s:property value="itTravel"/>
										name="itTravel"  onclick="chkTrvl();"/>
										<input type="hidden" name="hidItTravel" id="hidItTravel<%=i%>" value="<s:property value="hidItTravel" />"/></td>
 
									<td align="center" nowrap="nowrap" class="sortableTD"><input
										type="checkbox" class="checkbox" id="itLocalConv<%=i%>" <s:property value="itLocalConv"/>
										name="itLocalConv" onclick="chkConv();"/>
										<input type="hidden" name="hidItLocalConv" id="hidItLocalConv<%=i%>" value="<s:property value="hidItLocalConv" />"/></td>

									<td align="center" nowrap="nowrap" class="sortableTD"><input
										type="checkbox" class="checkbox" id="itLodging<%=i%>" <s:property value="itLodging"/>
										name="itLodging" onclick="chkLodge();"/>									
										<input type="hidden" name="hidItLodging" id="hidItLodging<%=i%>" value="<s:property value="hidItLodging" />"/>
									</td>
								
							 -->		
																
									<input type="hidden" name="hdeleteSub" id="hdeleteSub<%=i%>" /><input type="hidden" name="hidDeleteEmpId" id="hidDeleteEmpId<%=i%>" />

									<td align="center" nowrap="nowrap" class="sortableTD"><input
										type="checkbox" class="checkbox" id="confChk<%=i%>"
										name="confChk" onclick="callForDeleteSub(<%=i%>,'<s:property value="itEmployeeCode" />')" /></td>
								</tr>

							</s:iterator>
							<%
							d = i;
							j++;
							%>
						</tr>



					</table>
					<table></table>

					<%
						} catch (Exception e) {
						}
					%>
					</td>
				</tr>
			</table>
			</td>
		</tr>

  
		<tr>
			<td colspan="10">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">

				<tr>
					<td align="center" colspan="1"><input type="button"
						value="Save" class="token" onclick="callSave();">
						<input type="button"
						value="Close" class="token" onclick="callClose();"></td>
				</tr>

			</table>
			</td>
		</tr>
		

	</table>

</s:form>
<script>
function add(){
	var employee=document.getElementById('paraFrm_employee').value;  
	if(employee==""){
	alert("Please select an "+document.getElementById('emp3').innerHTML.toLowerCase());
	return false;
	}
	document.getElementById("paraFrm").target ="";
	document.getElementById("paraFrm").action="TrvlMangAuthorities_addSubSchdlr.action" ;
	document.getElementById("paraFrm").submit();
}


function chkDeleteSub()
		{
		 if(chk2()){
		 var con=confirm('Do you really want to  delete the record ?');
	 	if(con){
	   document.getElementById('paraFrm').action="TrvlMangAuthorities_deleteSub.action";
	    document.getElementById('paraFrm').submit();
	    }
	    else{
	    var flag='<%=d %>';
	  	for(var a=1;a<=flag;a++){	
	   document.getElementById('confChk'+a).checked=false;
	   document.getElementById('confChk'+a).checked="";
	    document.getElementById('hdeleteSub'+a).value="";
	    }
	     return false;
		 }
	 	}
	 	else {
	 	alert('Please select atleast one record to delete.');
	 	 return false;
	 	}
	}
	
	function chk2(){
	 	var flag='<%=d %>';
	  	for(var a=1;a<=flag;a++){	
	   if(document.getElementById('confChk'+a).checked == true)
	   {	
	 	    return true;
	   }	   
	  }
	  	return false;
	}
	
	function callForDeleteSub(id,EmployeeCode)
	   {
	   //alert("code"+eval(EmployeeCode)); 
	   var i=eval(id)-1;
	   if(document.getElementById('confChk'+id).checked == true)
	   {	  
	   document.getElementById('hdeleteSub'+id).value=eval(id)-1;
	   document.getElementById('hidDeleteEmpId'+id).value=eval(EmployeeCode);
	   }
	   else
	   document.getElementById('hdeleteSub'+id).value="";	   
   	}
   		
   	function chkTrvl(){
   		var count=<%=d%>;
   		for(var a=1;a<=count;a++){
   		var hidItTravel=document.getElementById('hidItTravel'+a).value;
   		var hidItLodging=document.getElementById('hidItLodging'+a).value;	
   		
   		if(document.getElementById('itTravel'+a).checked == true){
			document.getElementById('hidItTravel'+a).value='Y';			
   			}else{
   			document.getElementById('hidItTravel'+a).value='N'; 
   			}   		
   		}   			
   	 }
   	 
   	 function chkConv(){
   		var count=<%=d%>;
   		for(var a=1;a<=count;a++){
   		var hidItLocalConv=document.getElementById('hidItLocalConv'+a).value; 
   		
   		if(document.getElementById('itLocalConv'+a).checked == true){
			document.getElementById('hidItLocalConv'+a).value='Y';			
   			}else{
   			document.getElementById('hidItLocalConv'+a).value='N'; 
   			}   		
   		}   			
   	 }
   	 
   	  function chkLodge(){
   		var count=<%=d%>;
   		for(var a=1;a<=count;a++){
   		var hidItLodging=document.getElementById('hidItLodging'+a).value;	
   		
   		if(document.getElementById('itLodging'+a).checked == true){
			document.getElementById('hidItLodging'+a).value='Y';			
   			}else{
   			document.getElementById('hidItLodging'+a).value='N'; 
   			}   		
   		}   			
   	 }
   		
   	function callSave(){
   		var tabLen=document.getElementById('paraFrm_subTabLength').value; 
   		if(tabLen=="0"){
   		alert("Please select atleast one sub-scheduler.");
   		return false;
   		}else{
   		
   		var myoption=confirm('Are you sure you want to add the sub-scheduler ?');
   		if(myoption)
   		{
   		var count=<%=d%>;
   		var totalStr=''; 
   		var empStr=''; 
   		
   		for(var a=1;a<=count;a++){
   		var itEmployeeCode=document.getElementById('itEmployeeCode'+a).value;  
   		var hidItTravel=document.getElementById('hidItTravel'+a).value;
   		var hidItLocalConv=document.getElementById('hidItLocalConv'+a).value; 
   		var hidItLodging=document.getElementById('hidItLodging'+a).value;			
   		if(document.getElementById('itTravel'+a).checked == true){
		hidItTravel='Y'; 
   		}else{
   		hidItTravel='N'; 
   		}
   		
   		if(document.getElementById('itLocalConv'+a).checked == true){
		hidItLocalConv='Y'; 
   		}else{
   		hidItLocalConv='N'; 
   		}
   		
   		if(document.getElementById('itLodging'+a).checked == true){
		hidItLodging='Y'; 
   		}else{
   		hidItLodging='N'; 
   		}
   		
   		var empTot=itEmployeeCode;   		
   		var tot=itEmployeeCode+'#'+hidItTravel+'#'+hidItLocalConv+'#'+hidItLodging;
   		if(a==1){
   		totalStr+=tot;
   		empStr+=empTot;
   		}else{
   		totalStr+=','+tot;
   		empStr+=','+empTot;
   		}
   		
   		} 		
   		opener.document.getElementById('paraFrm_myHidden').value=totalStr;
   		opener.document.getElementById('paraFrm_myHiddenEmpCode').value=empStr;
   		if(totalStr==""){
   		opener.document.getElementById('paraFrm_subTabLength').value="0";
   		}
   		
   		  
	    } 
	    else{
	    return false;
	    }	  	
   		
   		}
   	
   		
    	document.getElementById('paraFrm').target="main";
	  	 window.close(); 
   	}
   	
   	function callClose(){
   	var tabLen=document.getElementById('paraFrm_subTabLength').value; 
   		if(tabLen=="0"){
   		opener.document.getElementById('paraFrm_subTabLength').value="0";
   		}
   	
   	document.getElementById('paraFrm').target="main";
	window.close(); 
   	}
   	
   	
   	function newRowColor(cell){   		
		   cell.className='Cell_bg_first';
	    }
	    
 	function oldRowColor(cell,val) {
	 cell.className='Cell_bg_second';
	}
</script>




