<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<link href="css/hrms.css" rel="stylesheet" type="text/css" />

<script language="JavaScript" type="text/javascript"
	src="..pages/common/include/javascript/sorttable.js"></script>


<%

 int totalWeightage = 0;
 String rating = "";
 Object isSelf[]=null;
 
 try{
	 isSelf=(Object[])request.getAttribute("isSelf");
	 totalWeightage  =( Integer.parseInt( request.getAttribute("gTotal").toString() ) );
	 rating = ""+(String)request.getAttribute("rating");
	 
 }catch(Exception e){ 
	 //e.printStackTrace(); 
 }
%>

<s:form name="paraFrm" theme="simple" id="paraFrm" method="post"
	action="AppraisalPhaseConfig">

	<s:hidden name="loadFlag" theme="simple" />
	<s:hidden name="addFlag" theme="simple" />
	<s:hidden name="modFlag" theme="simple" />
	<s:hidden name="dbFlag" theme="simple" />
	<s:hidden name="mode" theme="simple" />
	<input type="hidden" name="hSno" id="hSno">
	<s:hidden name="removePhases" theme="simple" />
	<input type="hidden" name="removeSno" id="removeSno" />
	<s:hidden name="phaseRating" />
	<input type="hidden" name="hWeigh" id="hWeigh" />
	<input type="hidden" name="rating" id="rating" value="<%=rating%>">
	<s:hidden name="show" value="%{show}" />
	<s:hidden name="myPage" id="myPage" />
	<s:hidden name="appStarted" />

	<table width="100%" border="0" align="left" cellpadding="2"
		cellspacing="1" class="formbg">

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt" align="left"><strong
						class="text_head">Appraisal Phase Configuration </strong></td>
				</tr>
			</table>
			</td>
		</tr>

		<!--   
         <s:textfield name="viewFlag"></s:textfield>
          -->
		<s:hidden name="editMode" />

		<!---------------------------------------- MODE = READONLY -------------------------------------------------->

		<s:if test="%{mode == 'readonly'}">


			<s:hidden name="apprId" />
			<s:hidden name="apprCode" />
			<s:hidden name="frmDate" />
			<s:hidden name="toDate" />

			<tr>
				<td width="78%"><jsp:include
					page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				<td align="right"><b>Page:</b> <%
							int total1 = (Integer) request.getAttribute("abc");//TOTAL PAGES
							int PageNo1 = (Integer) request.getAttribute("xyz");//CURRENT PAGE NUMBER
						%>
				<%
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
 		if (!(PageNo1 ==1) && !(total1==PageNo1)) {
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

					<option value="<%=i%>" <%=PageNo1==i?"selected":"" %>><%=i%></option>
					<%
}
	%>
				</select></td>
			</tr>
			<script>
		 
		   	function on()
			   {
			  	var val= document.getElementById('selectname').value;
				document.getElementById('paraFrm_show').value=val;
				 document.getElementById('myPage').value=eval(val);
				 document.getElementById('selectname').value=val;
				   document.getElementById('paraFrm').action="AppraisalPhaseConfig_callPage.action";
				   
				   document.getElementById('paraFrm').submit();
			   }
			   
			   	function callPage(id){
					document.getElementById('myPage').value=id;
					document.getElementById('paraFrm_show').value=id;
					document.getElementById('paraFrm').action="AppraisalPhaseConfig_callPage.action";
					document.getElementById('paraFrm').submit(); 
   				}
		 
		</script>

			<tr>
				<td colspan="3">
				<table width="100%" border="0" class="formbg">
					<!-- <tr height="22">
		  	 <td  class="formhead">
		  	 	<strong class="forminnerhead">
		  	 		Appraisal Process Configuration List
		  	 	</strong> 
		  	 </td>
		  	</tr> -->

					<script>
		  	
		  	 function rowCheck(){
		  	  
		  	  limit = document.getElementById('rowCount').value;
		  	  var count = 0;
			  for(i=1;i<=limit;i++){
			  	
			  	if(document.getElementById('chkDel'+i).checked){
			  	 	count++;
		  	  	}
		  	  	
		  	  }
		  	  if(count==0){
		  	  	alert('Please select a record to delete.');
		  	  	return false;
		  	  }	return true;	  	   
		  	  
		  	   
		  	
		  	  
		  	 }
		  	
		  	 function deleteProcessConfig(){
		  	 
		  	 
		  	  if(rowCheck()){
		  	  limit = document.getElementById('rowCount').value;
		  	  var confRequired = true;
		  	  
			  for(i=1;i<=limit;i++){
			  	if(document.getElementById('chkDel'+i).checked){
			  			
			  		if(confRequired){
			  			var con=confirm('Do you really want to delete the record?')			  			
			  			if(con){	 
					  	 	document.getElementById('paraFrm').action='AppraisalPhaseConfig_deleteProcessConfigurations.action';
					  	 	document.getElementById('paraFrm').submit();
					  	 	return true; 
				  	 	}else{ 
				  	 	    
				  	 	    document.getElementById('chkDel'+i).checked=false;
				  	 	    i=1;
				  	 	    confRequired=false;				  	 	    
				  	 	    
				  	 	}
				  	 }else{
				  	  
				  	   document.getElementById('chkDel'+i).checked=false;
				  	   
				  	 }
			  	 	
			  	}	   
		  	  }
		  	  
		  	  }
		  	 }
		  	</script>
					<tr>
						<td class="formtext">
						<table width="100%" border="0" cellpadding="1" cellspacing="2"
							class="sortable">
							<tr>
								<td width="5%" valign="top" class="formth"><label
									name="list.srno" z="set" id="list.srno"
									ondblclick="callShowDiv(this);"><%=label.get("list.srno")%></label>
								</td>
								<td width="55%" valign="top" class="formth"><label
									name="appraisal.code" class="set" id="appraisal.code"
									ondblclick="callShowDiv(this);"><%=label.get("appraisal.code")%></label>
								</td>
								<td width="15%" valign="top" class="formth" align="center">
								<label name="init.page.start.date" class="set"
									id="init.page.start.date" ondblclick="callShowDiv(this);"><%=label.get("init.page.start.date")%></label>
								</td>
								<td width="10%" valign="top" class="formth" align="center">
								<label name="init.page.end.date" class="set"
									id="init.page.end.date" ondblclick="callShowDiv(this);"><%=label.get("init.page.end.date")%></label>
								</td>
								<td width="10%" valign="top" class="formth" align="center"><input
									type="button" class="delete" value="    Delete"
									onclick="return deleteProcessConfig();"></td>
							</tr>

							<%int row=0,count2=0,cnt=PageNo1*20-20; %>

							<s:iterator value="calList">
								<tr <%if(count2%2==0){
									%> class="tableCell1"
									<%}else{ %> class="tableCell2" <%	}count2++; %>
									title="double click to edit"
									onmouseover="javascript:newRowColor(this);"
									onmouseout="javascript:oldRowColor(this,<%=count2%2 %>);"
									ondblclick="javascript:callForCalendarEdit(<s:property value="calId" />);">
									<td width="5%" class="sortableTD" align="center">
									<%++row;%><%=++cnt%></td>
									<td width="30%" class="sortableTD"><s:property
										value="calCode" /></td>
									<td width="11%" class="sortableTD" align="center"><s:property
										value="startdate" /></td>
									<td width="15%" class="sortableTD" align="center"><s:property
										value="endDate" /></td>
									<td align="center" class="sortableTD"><input
										type="checkbox" name="chkDel" id="chkDel<%=row%>"
										value="<s:property value="calId" />"></td>
								</tr>
							</s:iterator>
							<input type="hidden" id="rowCount" value="<%=row %>">
							<%if(row==0){ %>
							<tr>
								<td colspan="5" class="sortableTD" align="center"><font
									color="red">No Data To Display</font></td>
							</tr>
							<%} %>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>

		</s:if>
		<script>

 function callForCalendarEdit(calId){  
  
  document.getElementById('paraFrm_apprId').value=calId;
  document.getElementById('paraFrm_mode').value="";
  document.getElementById('paraFrm').action='AppraisalPhaseConfig_edit.action';
  document.getElementById('paraFrm').submit();
  
 }
 
</script>

		<!---------------------------------- MODE = READONLY ENDS------------------------------------>








		<!---------------------------------- MODE = ADD  BEGINS------------------------------------>

		<!-- editFlag- DISPLAYS ALL THE FIELDS ALONG WITH LABELS IN EDIT MODE -->
		<s:if test="%{editMode}">
			<tr>
				<td colspan="3">

				<table width="100%" border="0" cellpadding="2" cellspacing="2">
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


			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="2" cellspacing="2"
					class="formbg">
					<tr>
						<td><strong class="forminnerhead"> Appraisal
						Calendar Details </strong></td>
					</tr>
					<tr>
						<td>
						<table width="100%" border="0" align="left" cellpadding="2"
							cellspacing="2">
							<tr>
								<td width="10%" height="22" nowrap="nowrap" class="formtext">
								<label name="appraisal.code" class="set" id="appraisal.code"
									ondblclick="callShowDiv(this);"><%=label.get("appraisal.code")%></label>
								<font color="#FF0000">*</font>:</td>
								<td width="15%" height="22" class="formtext"><s:if
									test="%{mode == 'update'}">
									<s:hidden name="apprCode" />
									<s:property value="apprCode" />
								</s:if> <s:else>

									<s:textfield name="apprCode" theme="simple" size="29"
										maxlength="25" readonly="true" />
								</s:else> <s:hidden name="apprId" theme="simple" /></td>

								<td width="25%" height="22" align="left" class="formtext">
								<s:if test="%{mode == ''}">
									<img src="../pages/images/recruitment/search2.gif" height="16"
										align="absmiddle" width="17"
										onclick="javascript:callsF9(500,325,'AppraisalPhaseConfig_f9AppCal.action'); ">
								</s:if></td>
								<td width="50%" height="22" class="formtext" colspan="2"><label
									name="from" class="set" id="from"
									ondblclick="callShowDiv(this);"><%=label.get("from")%></label>:
								<s:if test="%{mode == 'update'}">
									<s:hidden name="frmDate" />
									<s:property value="frmDate" />
								</s:if> <s:else>
									<s:property value="frmDate" />
									<s:hidden name="frmDate" />
								</s:else> &nbsp;&nbsp; <label name="to" class="set" id="to"
									ondblclick="callShowDiv(this);"><%=label.get("to")%></label>: <s:if
									test="%{mode == 'update'}">
									<s:hidden name="toDate" />
									<s:property value="toDate" />
								</s:if> <s:else>
									<s:hidden name="toDate" />
									<s:property value="toDate" />
								</s:else></td>

							</tr>

						</table>

						</td>
					</tr>
				</table>
				</td>
			</tr>
			<!-- WHEN ADD NEW IS CLICKED and APPRAISAL CODE IS SELECTED THE BELOW SECTION IS DISPLAYED -->
			<s:if test="newFlag">
				<tr>
					<td colspan="3" align="left">
					<table width="100%" border="0" align="left" cellpadding="2"
						cellspacing="2" class="formbg">
						<tr>
							<td><strong class="forminnerhead">Appraisal Phase
							Details</strong></td>
						</tr>
						<tr>
							<td colspan="2">
							<table width="100%" border="0" align="left" cellpadding="2"
								cellspacing="2">


								<tr>
									<td height="27" class="formtext" width="20%"><label
										class="set" name="phase.rating" id="phase.rating"
										ondblclick="callShowDiv(this);"><%=label.get("phase.rating") %></label>:
									</td>
									<td height="25" width="26%"><!-- 
						 <input type="checkbox" name="C9" id="chkRating" value="ON"  onclick="return callCheckBox1();" >
						  --> <input type="checkbox" name="pRatingCheck"
										id="paraFrm_pRatingCheck" onclick="callCheckBox()"
										value="<%=rating %>"
										<%=rating.equals("checked")?"checked":"" %>
										<%=rating.equals("checked")?"disabled":"" %>> <s:hidden
										name="pRating" theme="simple" value="0" /></td>
								</tr>
								<tr>
									<td height="25" class="formtext" width="20%"><label
										name="phase.name" class="set" id="phase.name"
										ondblclick="callShowDiv(this);"><%=label.get("phase.name")%></label>
									<font color="#FF0000">*</font>:</td>
									<td height="25" width="26%"><s:textfield name="phase"
										theme="simple" size="30" maxlength="30"
										onkeypress="return alphaNumeric();" /> <script>
										
					/*alert(document.getElementById('paraFrm_mode').value=='');					
					if(document.getElementById('paraFrm_mode').value==''){
						document.getElementById('paraFrm_phase').disabled='disabled';
					}*/
					
					
					</script></td>
									<td height="25" width="21%"><label name="phase.sequence"
										class="set" id="phase.sequence"
										ondblclick="callShowDiv(this);"><%=label.get("phase.sequence")%></label>
									:</td>
									<td height="25" width="29%"><s:select name="pOrder"
										theme="simple"
										list="#{'1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10'}" />

									</td>

								</tr>
								<tr>




									<td height="25" width="21%"><label name="status"
										class="set" id="status" ondblclick="callShowDiv(this);"><%=label.get("status") %></label>
									:</td>


									<td height="25" width="29%"><!--           
                    <s:radio name="pStatus"  theme="simple"    list="#{'A':'Active','D':'De-Active '}" value=" Active "    /> 
               --> <input type="radio" name="pStatus"
										id="paraFrm_pStatus1" value="A" checked="checked">Active
									<input type="radio" name="pStatus" id="paraFrm_pStatus2"
										value="D">De-Active</td>
								</tr>

								<tr>
									<td width="100%" colspan="4">
									<div id="centDiv1"
										style='display: <%= rating . equals("checked") ? "" : "none" %>;'>
									<table width="100%" border="0">
										<tr>
											<td height="22" class="formtext" width="14%"><label
												name="weightage" class="set" id="weightage"
												ondblclick="callShowDiv(this)"><%=label.get("weightage") %></label>
											<font color="#FF0000">*</font>:</span></td>
											<td height="25" width="25%"><s:textfield
												name="pWeightage" theme="simple"
												onkeypress="return numbersOnly();" size="5" maxlength="3" />
											(in %)</td>
											<td height="25" width="29%" colspan="2">&nbsp;</td>
										</tr>
									</table>
									</div>
									</td>
								</tr>
								<tr>
									<td height="27" class="formtext" width="20%">Display
									Question Weightage in this phase</td>
									<td height="25" width="26%"><input type="checkbox"
										name="queWeightChk" id="queWeightChk" onclick="setQueWeight()" />
									<td><s:hidden name="pQueWeight" /> <script
										type="text/javascript">
                   			function setQueWeight()
                   			{
                   			
                   				if(document.getElementById('queWeightChk').checked)
                   					document.getElementById('paraFrm_pQueWeight').value="Y";
                   				else
                   					document.getElementById('paraFrm_pQueWeight').value="N";
                   			
                   			}
                   		</script>
								</tr>

								<tr>
									<td height="25" class="formtext" width="20%"><label
										class="set" name="desc" id="desc"
										ondblclick="callShowDiv(this)"><%=label.get("desc") %></label>:
									</td>
									<td height="25" width="79%" colspan="4"><s:textarea
										theme="simple" name="pDescr" rows="3" cols="87"
										onkeypress="return textCounter(this,100)"
										onchange="return textCounter(this,100)">
									</s:textarea></td>
								</tr>

								<tr>

									<td height="25" width="100%" colspan="5" align="center"><s:submit
										action="AppraisalPhaseConfig_addPhase" theme="simple"
										cssClass="add" value="    Add" onclick="return validate();" /></td>

									</td>
								</tr>
							</table>

							</td>

						</tr>
					</table>





					</td>
				</tr>

				<tr>
					<td colspan="3" class="formtext">
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="2" class="formbg">
						<tr>
							<td height="22" class="formhead"><strong
								class="forminnerhead"> Phase List </strong></td>
						</tr>
						<tr>
							<td class="formtext">

							<table id="phaseList" width="100%" border="0" cellpadding="1"
								cellspacing="2">
								<tr>
									<td width="3%" valign="top" class="formth"><label
										name="list.srno" class="set" id="list.srno"
										ondblclick="callShowDiv(this);"><%=label.get("list.srno")%></label>
									</td>
									<td width="30%" valign="top" class="formth" align=center>
									<label name="list.phase.name" class="set" id="list.phase.name"
										ondblclick="callShowDiv(this);"><%=label.get("list.phase.name")%></label>
									</td>
									<td width="5%" valign="top" class="formth" align=center><label
										name="list.isself" class="set" id="list.isself"
										ondblclick="callShowDiv(this);"><%=label.get("list.isself")%></label>
									</td>
									<td width="2%" valign="top" class="formth" align="center">
									<label name="phase.sequence" class="set" id="phase.sequence"
										ondblclick="callShowDiv(this);"><%=label.get("phase.sequence")%></label>
									</td>
									<td width="17%" valign="top" class="formth" align="center">
									<label name="list.weightage" class="set" id="list.weightage"
										ondblclick="callShowDiv(this);"><%=label.get("list.weightage")%></label>
									</td>
									<td width="15%" valign="top" class="formth" align="center">
									<label name="list.status" class="set" id="list.status"
										ondblclick="callShowDiv(this);"><%=label.get("list.status")%></label>
									</td>
									<td width="15%" valign="top" class="formth" align="center">
									<label name="list.descr" class="set" id="list.descr"
										ondblclick="callShowDiv(this);"><%=label.get("list.descr")%></label>
									</td>
									<td width="7%" valign="top" class="formth" align="center">
									<s:if
										test="%{mode == 'update' || mode == 'add' || removeFlag == 'true' }">

									</s:if></td>
								</tr>
								<% int j=0,count1=0,temp=0; %>
								<s:iterator value="phaseList">
									<% ++j; %>
									<tr title="Double click to update"
										<%if(count1%2==0){
									%> class="tableCell1" <%}else{ %>
										class="tableCell2" <%	}count1++; %>
										onmouseover="javascript:newRowColor(this);"
										onmouseout="javascript:oldRowColor(this,<%=count1%2 %>);"
										ondblclick="javascript:callForEdit(<%=j%>);">
										<td width="3%" class="sortableTD" align="center"><%=j%><input
											type="hidden" name="sNo" value="<%=j%>"><input
											type="hidden" name="phaseId" id="phaseId<%=j %>"
											value="<s:property value="phaseId" />"></td>
										<td width="15%" class="sortableTD"><s:property
											value="hPhase" /><input type="hidden" name="hPhase"
											id="hPhase<%=j %>" value="<s:property value="hPhase" />"></td>
										<td width="13%" align="center" class="sortableTD"><input
											type="hidden" name="hRself" id="hRself<%=j %>"
											value="<% if(isSelf!=null){ %><%= isSelf[temp]%><%}else %><%="N" %>">
										<input type="radio" name="rIsSelf" id="rIsSelf<%=j %>"
											value="<s:property value="phaseId" />"
											onclick="chkRadio('hRself<%=j %>')" <% if(isSelf!=null){ %>
											<%=isSelf[temp].equals("Y")?"checked":"" %> <%}else %>
											<%="N" %>></td>
										<td width="15%" align="center" class="sortableTD"><s:select
											name="hOrder" id="list%{sNo}" theme="simple"
											list="#{'1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10'}" />


										</td>
										<td width="12%" align="right" class="sortableTD"><s:property
											value="hWeightage" /><input type="hidden" name="hWeightage"
											id="hWeightage<%=j %>"
											value="<s:property value="hWeightage" />"></td>
										<td width="10%" class="sortableTD"><s:property
											value="hStatus" /><input type="hidden" name="hStatus"
											id="hStatus<%=j %>" value="<s:property value="hStatus" />">
										<input type="hidden" name="hRating" id="hRating<%=j %>"
											value="<s:property value="hRating" />"> <input
											type="hidden" name="hQueWeight" id="hQueWeight<%=j %>"
											value="<s:property value="hQueWeight" />"></td>
										<td class="sortableTD"><textarea name="hDescr"
											id="hDescr<%=j%>" rows="3" cols="30" readonly="readonly"><s:property
											value="hDescr" /></textarea></td>
										<td align="center" class="sortableTD">
										<input	type="button" value="Remove" class="delete"
											onclick="javascript:removePhase(document.getElementById('phaseId<%= j %>').value,'<%=j%>')">
										</td>
									</tr>
									<% temp++; %>
								</s:iterator>
								<%if(temp==0){ %>
								<tr>
									<td colspan="8" class="sortableTD" align="center"><font
										color="red">No Data To Display</font></td>
								</tr>
								<%}else{ %>
								<tr>
									<td class="sortableTD">&nbsp;</td>
									<td class="sortableTD" align="right" colspan="3"><b>Total
									Weightage</b></td>
									<td class="sortableTD" align="right"><b><%=totalWeightage%></b></td>
									<td class="sortableTD" colspan="3">&nbsp;</td>
								</tr>
								<%} %>
								<input type="hidden" id="rows" value="<%=j %>" />
								<script>
		 	         function setCode(id){
			 	          if(document.getElementById('chk'+id).checked){
			 	           document.getElementById('chkOpr'+id).value='Y';
			 	          }else{
			 	           document.getElementById('chkOpr'+id).value='N';
			 	          }
		 	         } 
		 	         
		 	         function chkRadio(id){
			 	          len = eval(document.getElementById('rows').value);
			 	          
			 	          for(i=1;i<=len;i++){
			 	           
			 	           	document.getElementById('hRself'+i).value='N';
			 	           
			 	          }
			 	          
			 	          document.getElementById(id).value='Y';
		 	         }
		 	        </script>

							</table>


							</td>
						</tr>


					</table>
					</td>
				</tr>
				<input type="hidden" name="totalWeightage" id="totalWeightage"
					value="<%=totalWeightage %>" />
			</s:if>
		</s:if>

		<!---------------------------------- MODE = ADD  ENDS------------------------------------>


		<!-- viewFlag - SHOW DETAILS IN LABELLED FORM ONLY -->

		<!-- viewFlag - SHOW DETAILS IN LABELLED FORM ONLY -->
		<s:if test="viewMode">
			<s:hidden name="apprId" theme="simple" />
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="2" cellspacing="2">
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


			<tr>
				<td colspan="3">
				<table width="100%" align="left" border="0" cellpadding="2"
					cellspacing="2">
					<tr>
						<td>
						<table width="100%" border="0" align="center" cellpadding="2"
							cellspacing="2" class="formbg">
							<tr height="22">
								<TD width="10%"><label name="appraisal.code" class="set"
									id="appraisal.code" ondblclick="callShowDiv(this);"><%=label.get("appraisal.code")%></label>
								:</TD>
								<td width="23%"><s:property value="apprCode" /></td>
								<td width="70%"><label name="from" class="set" id="from"
									ondblclick="callShowDiv(this);"><%=label.get("from")%></label>
								&nbsp;<s:property value="frmDate" />&nbsp;&nbsp; <label
									name="to" class="set" id="to" ondblclick="callShowDiv(this);"><%=label.get("to")%></label>
								&nbsp;<s:property value="toDate" /></td>
							</tr>
							<tr>
								<td colspan="3"></td>
							</tr>
						</table>
						</td>
					</tr>
					<tr>
						<td>
						<table width="100%" class="formbg">
							<tr>
								<td height="22" class="formhead"><strong
									class="forminnerhead">Phase List </strong></td>
							</tr>
							<tr>
								<td colspan="3"><!-- TABULAR DATA --> <% int i=0,temp1=0; %>
								<table width="100%">
									<tr>
										<td width="3%" valign="top" class="formth"><label
											name="list.srno" class="set" id="list.srno"
											ondblclick="callShowDiv(this);"><%=label.get("list.srno")%></label>
										</td>
										<td width="30%" valign="top" class="formth" align=center>
										<label name="list.phase.name" class="set" id="list.phase.name"
											ondblclick="callShowDiv(this);"><%=label.get("list.phase.name")%></label>
										</td>
										<td width="5%" valign="top" class="formth" align=center>
										<label name="list.isself" class="set" id="list.isself"
											ondblclick="callShowDiv(this);"><%=label.get("list.isself")%></label>
										</td>
										<td width="2%" valign="top" class="formth" align="center">
										<label name="list.priority" class="set" id="list.priority"
											ondblclick="callShowDiv(this);"><%=label.get("list.priority")%></label>
										</td>
										<td width="17%" valign="top" class="formth" align="center">
										<label name="list.weightage" class="set" id="list.weightage"
											ondblclick="callShowDiv(this);"><%=label.get("list.weightage")%></label>
										</td>
										<td width="15%" valign="top" class="formth" align="center">
										<label name="list.status" class="set" id="list.status"
											ondblclick="callShowDiv(this);"><%=label.get("list.status")%></label>
										</td>

									</tr>
									<s:iterator value="phaseList">
										<% ++i;%>
										<tr>
											<td class="sortableTD"><%=i%></td>
											<td class="sortableTD"><s:property value="hPhase" /></td>
											<td class="sortableTD" align="center"><input
												type="radio" name="rIsSelf" id="rIsSelf<%=i %>"
												value="<s:property value="phaseId" />"
												<% if(isSelf!=null){ %>
												<%=isSelf[temp1].equals("Y")?"checked":"" %> <%}else %>
												<%="N" %> disabled="disabled"></td>
											<td class="sortableTD" align="center"><s:select
												name="hOrder" id="list%{sNo}" theme="simple" disabled="true"
												list="#{'1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10'}" />
											</td>
											<td class="sortableTD" align="right"><s:property
												value="hWeightage" /></td>
											<td class="sortableTD" align="center"><s:property
												value="hStatus" /></td>
										</tr>
										<% temp1++;%>
									</s:iterator>
									<tr height="">
										<td class="sortableTD">&nbsp;</td>
										<td class="sortableTD" align="right" colspan="3"><b>Total
										Weightage</b></td>
										<td class="sortableTD" align="right"><b><%=totalWeightage>0?totalWeightage:"" %></b></td>
										<td class="sortableTD">&nbsp;</td>
									</tr>
								</table>
								</td>
							</tr>
						</table>
						</td>
					</tr>

				</table>
				</td>
			</tr>
		</s:if>




		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2">
				<tr>
					<td width="78%">
					<table width="100%" border="0" cellpadding="2" cellspacing="0">
						<tr>
							<td width="22%"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						</tr>
					</table>

					</td>

					<td width="22%"></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

</s:form>

<script>

	 function previous()
	   {
	   	 var pageno=	document.getElementById('myPage').value;
	   	
		 document.getElementById('myPage').value=eval(pageno)-1;
		 document.getElementById('paraFrm_show').value=eval(pageno)-1;
		   document.getElementById('paraFrm').action="AppraisalPhaseConfig_callPage.action";
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
		   document.getElementById('paraFrm').action="AppraisalPhaseConfig_callPage.action";
		   document.getElementById('paraFrm').submit();
  		}
	  
	  
	function textCounter(field,  maxlimit) {
		
		if (field.value.length > maxlimit){
			field.value = field.value.substring(0, maxlimit);
			field.focus;
			return false;
		}
		return true;
	}
					
   function validate(){
  // alert('');
        //alert(document.getElementById('paraFrm_pRatingCheck').checked);
        
        
        if(document.getElementById('paraFrm_apprCode').value==''){
       		
	        document.getElementById('paraFrm_apprCode').focus();
	        alert('Please select '+document.getElementById("appraisal.code").innerHTML.toLowerCase()+'.');
	        return false;
        	
       }if(trim(document.getElementById('paraFrm_phase').value)==''){
       		
	        document.getElementById('paraFrm_phase').focus();
	        alert('Please enter '+document.getElementById("phase.name").innerHTML.toLowerCase()+'.');
	        document.getElementById('paraFrm_phase').value="";
	        return false;
        
       }    
       
       //alert(document.getElementById('paraFrm_phase').value+' '+document.getElementById('hPhase'+1));
       if(eval(document.getElementById('rows').value)>0){
       for(i=1;i<=eval(document.getElementById('rows').value);i++){

       	       		if(document.getElementById('hSno').value==""){//NEW RECORD     	       		
       	       			 if(document.getElementById('paraFrm_phase').value==document.getElementById('hPhase'+i).value){
       	       				alert(document.getElementById("phase.name").innerHTML.toLowerCase()+' to be added, already exists.'); 
	       	        		document.getElementById('paraFrm_phase').select(); 
	       	        		document.getElementById('paraFrm_phase').focus();
	       	        		return false; 
	       	        		
       	       			 }if(document.getElementById('paraFrm_pOrder').value==document.getElementById('list'+i).value){       	       			 			 
       	       			 			
       	       			 			alert(document.getElementById("phase.sequence").innerHTML.toLowerCase()+' already exists.'); 
			       	        		document.getElementById('paraFrm_pOrder').focus();
			       	        		return false;    
			       	        		     	       			 			
       	       			 }if(document.getElementById('paraFrm_pRatingCheck').checked){
       	       			 
					       if( (eval(document.getElementById('totalWeightage').value) + eval(document.getElementById('paraFrm_pWeightage').value ) )>100 ){
					       		
					       		alert('Total weightage should not exceed 100,\nwhen phase wise rating is selected.');
					       		document.getElementById('paraFrm_pWeightage').focus();
					       		return false;
					       		/////phase.rating
					       	}
					       	 if(eval(document.getElementById('totalWeightage').value)=="100" && !(document.getElementById('paraFrm_pWeightage').value=="0")){
						        alert('Cannot add phase as total weightage is already 100.');
						        return false;
       					 	}
       					 }
       					
       	       			
       	       		}else{//UPDATABLE RECORD       	       		
		       	       		if( !(eval(document.getElementById('hSno').value)==i) ){		       	       			
		       	       			if(document.getElementById('paraFrm_phase').value==document.getElementById('hPhase'+i).value){
		       	       				
       	       						alert(document.getElementById("phase.name").innerHTML.toLowerCase()+' to be added, already exists.'); 
			       	        		document.getElementById('paraFrm_phase').select();
			       	        		document.getElementById('paraFrm_phase').focus();
			       	        		return false; 
			       	        		
       	       			 		}if(document.getElementById('paraFrm_pOrder').value==document.getElementById('list'+i).value){
       	       			 			alert(document.getElementById("phase.sequence").innerHTML.toLowerCase()+'already exists.'); 
			       	        		document.getElementById('paraFrm_pOrder').focus();
			       	        		return false;
			       	        		
       	       			 		}
       	       			 		//alert(document.getElementById('paraFrm_pRatingCheck').checked+'-'+document.getElementById('paraFrm_pStatus1').checked);
       	       			 		//alert(document.getElementById('paraFrm_pStatus1').checked);
       	       			 		//alert( (eval(document.getElementById('totalWeightage').value) - document.getElementById('hWeigh').value) + eval(document.getElementById('paraFrm_pWeightage').value ));
       	       			 		
       	       			 		if(document.getElementById('paraFrm_pRatingCheck').checked){
	       	       			 		if( ( (eval(document.getElementById('totalWeightage').value) - document.getElementById('hWeigh').value) + eval(document.getElementById('paraFrm_pWeightage').value ) )>100 ){
						       		
						       		if(eval(document.getElementById('totalWeightage').value)=="100"){
					        			alert('Cannot add phase as total weightage is already 100.');
					        			return false;
       					 			}
						       		
						       		
							       		alert('Total weightage should not exceed 100,\nwhen phase wise rating is selected.');
							       		document.getElementById('paraFrm_pWeightage').focus();
							       		return false;
						       		}
						       		 
					       		}
       	       			 		
		       	       			
		       	       		}
       	       		}
       	       
       	       } //for ends
       	    }//If ends   
	       	    else{
	       	      if(document.getElementById('paraFrm_pRatingCheck').checked){
						       if( (eval(document.getElementById('totalWeightage').value) + eval(document.getElementById('paraFrm_pWeightage').value ) )>100 ){
						       		
						       		alert('Total weightage should not exceed 100,\nwhen phase wise rating is selected.');
						       		document.getElementById('paraFrm_pWeightage').focus();
						       		return false;
						       		
						       	}
	       			}
       	    }  
       	       
       	if(document.getElementById('paraFrm_pRatingCheck').checked
        	
       		&& document.getElementById('paraFrm_pWeightage').value==''){
       		document.getElementById('paraFrm_pWeightage').focus();
       		alert('Please enter '+document.getElementById("weightage").innerHTML.toLowerCase()+'.');
       		return false;
       		
       		}
       
         
       	       
       	        
       	     return true;  
     }
             
      
  

	function callForEdit(id){
	
	  document.getElementById('hSno').value=id;
	   document.getElementById('hWeigh').value=document.getElementById('hWeightage'+id).value;
	 
	   document.getElementById('paraFrm_phase').value = document.getElementById('hPhase'+id).value
	   document.getElementById('paraFrm_pOrder').value = document.getElementById('list'+id).value
	   document.getElementById('paraFrm_pWeightage').value = document.getElementById('hWeightage'+id).value
	   //document.getElementById('paraFrm_status12').value = document.getElementById('hStatus'+id).value
		//Display wieghtage check box
		if(document.getElementById('hQueWeight'+id).value=="Y")
			document.getElementById('queWeightChk').checked=true;
		else
			document.getElementById('queWeightChk').checked=false;
	   document.getElementById('pQueWeight').value = document.getElementById('hQueWeight'+id).value;
	   if(document.getElementById('hRating'+id).value=="Y"){
	   	 document.getElementById('paraFrm_pRatingCheck').checked=true;
	   	 document.getElementById('centDiv1').style.display='';
	   }else{
	     document.getElementById('paraFrm_pRatingCheck').checked=false;
	   	 document.getElementById('centDiv1').style.display='none';
	   }
	   
	   document.getElementById('paraFrm_pDescr').value = document.getElementById('hDescr'+id).value
	  
	   if(document.getElementById('hStatus'+id).value=='De-Active'){
	    document.getElementById('paraFrm_pStatus2').checked=true; 
	   }else{
	    document.getElementById('paraFrm_pStatus1').checked=true; 
	   }
	   
	  
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

               
function removePhase(id,sno){
				
				alert("delete");
		
				 var conf = confirm('Do you really want to remove the phase?');
				 if(conf){
				 	document.getElementById('hSno').value=id;
				 	document.getElementById('removeSno').value=sno;				 	
				 	document.getElementById('paraFrm').action='AppraisalPhaseConfig_remove.action';
				 	document.getElementById('paraFrm').submit();
				 	return true ; 
				 }
				 return false;
				 
				 
				 
				 /*
				 var count = 0;
				 var length = document.getElementById('rows').value;  
				 				 
				 for(i=1;i<=length;i++){				  
				  if(document.getElementById('chk'+i).checked){
				   count++;
				  }
				 }	
				if(count==0){
				 alert('Please select a phase to delete.');
				 	return false;
				}else{				 
				 	return true;
				}
				*/
				
				
		}
                    </script>


<script>


	function addnewFun()
	{	
		
		document.getElementById('paraFrm').action="AppraisalPhaseConfig_addNew.action";
		document.getElementById('paraFrm').submit();
	}

// For Save Button

	function saveFun()
	{ 
		//if(!checkMandatory(fieldName, lableName, type))
	   // return false;
	   // if(!f9specialchars(f9Fields))
		//return false;
		/*
		if(document.getElementById('paraFrm_apprCode').value==''){
       
	        document.getElementById('paraFrm_apprCode').focus();
	        alert('Please select appraisal code.');        
	        return false;
        
       	}if(eval(document.getElementById('rows').value)<=0){
       		alert('Please add phases for the selected appraisal code.');
       		return false;
       	}*/
       	/*
        if(document.getElementById('paraFrm_apprCode').value==''){
       		
	        document.getElementById('paraFrm_apprCode').focus();
	        alert('Please select appraisal code.');        
	        return false;
        	
       }if(document.getElementById('paraFrm_phase').value==''){
       	
	        document.getElementById('paraFrm_phase').focus();
	        alert('Please enter phase name.');        
	        return false;
        
       }*/
       
       if(document.getElementById('paraFrm_appStarted').value=='Y'){
        	alert('Cannot save process configuration details,\n as the calendar is locked');
        	return false;
       }
       
       if(document.getElementById('paraFrm_apprCode').value==''){
       		
	        document.getElementById('paraFrm_apprCode').focus();
	        alert('Please select appraisal code.');        
	        return false;
        	
       }
       if(eval(document.getElementById('rows').value)<=0){
       
       		if(document.getElementById('paraFrm_phase').value==''){
       
		        document.getElementById('paraFrm_phase').focus();
		        alert('Please enter phase name.');        
		        return false;
        
       		}
       		alert('Please add phases .');
       		return false;
       		       		
        }
        
        
        
        
     	if(document.getElementById('rating').value=="checked" && 
     		eval(document.getElementById('totalWeightage').value)<100 
     	){
			 alert('Total weightage should be 100, when \n phase wise rating is selected.');
			 return false;
     	}
     	
     	
     	if(document.getElementById('rating').value=="checked" && 
     		eval(document.getElementById('totalWeightage').value)>100 
     	){
			 alert('Total weightage should not exceed 100, when \n phase wise rating is selected.');
			 return false;
     	}if(eval(document.getElementById('rows').value)>0){
     	 	
     	 	len = eval(document.getElementById('rows').value);
     	 	temp =0;
     	 	
     	 	for(i=1;i<=len;i++){
	     	 	 if(document.getElementById('rIsSelf'+i).checked){
	     	 	 
	     	 	    if(!(document.getElementById('list'+i).value=="1")){
	     	 	     
	     	 	     	alert('Self phase priority should be 1.');   	 	     	
	     	 	     	return false;
	     	 	    }
	     	 	    
	     	 	  	temp++;
	     	 	  	
	     	 	 }
     	 	}
	     	 if(temp==0){
		     	  alert('Please make a phase as self.');     	 
		     	  return false;
	     	 }
     	 
     	}
     	
     	for(i=1;i<=eval(document.getElementById('rows').value);i++){
       		  
       		  for(j=i+1;j<=eval(document.getElementById('rows').value);j++){
       		         	//alert(document.getElementById('list'+i).value+'---'+document.getElementById('list'+j).value);
       		         	//alert(i+'--'+j);
       		         	
       		 		if(document.getElementById('list'+i).value==document.getElementById('list'+j).value){
       		 			
       		 			alert('Phase sequence not allow dulicate.');
       		 			document.getElementById('list'+i).focus;
       		 			return false;
       		 		} 
       		 		
       		 		
       		 		
       		 	}
       		}
     	
     	
	    document.getElementById('paraFrm').action="AppraisalPhaseConfig_save.action";
		document.getElementById('paraFrm').submit();
		return true; 
       	
		//return false;
		
		
	}

//For Edit Button

	function editFun()
	{
		document.getElementById('paraFrm').action="AppraisalPhaseConfig_edit.action";
		document.getElementById('paraFrm').submit();
	}

//For Delete Button

	function deleteFun()
	{
		var con=confirm('Do you really want to delete the record?')
		if(con){				
			   document.getElementById('paraFrm').action="AppraisalPhaseConfig_delete.action";
			   document.getElementById('paraFrm').submit();
		} else{
		     return false;
		}
	}

//For F9Window

	function searchFun()
	{
		callsF9(500,300,"AppraisalPhaseConfig_f9Action.action");
	}

	function cancelFun()
	{
		document.getElementById('paraFrm').action="AppraisalCalendar_input.action";
		document.getElementById('paraFrm').submit();
	}




	function callCheckBox(){
	
	
				
		if(document.getElementById("paraFrm_pRatingCheck").checked==true){
			  
			
			
			if(eval(document.getElementById('rows').value)>0 
			&& (document.getElementById('paraFrm_phaseRating').value=="false" || document.getElementById('paraFrm_phaseRating').value=="")	 
			){
		  		alert('Phase wise rating not allowed,\n as  phases without phase rating exists. ');
		  		document.getElementById("paraFrm_pRatingCheck").checked=false;
		  		return false;
			}else{
			
			document.getElementById('paraFrm_phaseRating').value='true';
			document.getElementById('centDiv1').style.display='';
			document.getElementById('paraFrm_pRating').value='1';
			
			}
			
			  
		}else {
			
		 	document.getElementById('paraFrm_phaseRating').value='false';	
			document.getElementById('centDiv1').style.display='none';
			document.getElementById('paraFrm_pRating').value='0';
			document.getElementById('paraFrm_pWeightage').value='';			
			
		}
		
	
	}
	
	
	
	
	
</script>

