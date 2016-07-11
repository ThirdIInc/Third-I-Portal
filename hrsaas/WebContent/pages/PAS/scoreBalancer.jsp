<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<html>
<head>
<script type="text/javascript" src="../pages/PAS/PASAjax.js"></script>
<jsp:include page="/pages/CommonCssJS.jsp" ></jsp:include>
</head>
<body>
<s:form action="ScoreBalancer" validate="true" id="paraFrm"
	theme="simple">


	<table width="100%" cellpadding="2" cellspacing="1" class="formbg">

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <i class="fa fa-balance-scale" aria-hidden="true"></i></strong></td>
						
					<td width="93%" class="txt"><strong class="text_head">Score Balancer</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
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
						<!--<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>-->
						<table>
							<tbody>
								<tr id="trButton">
									<td id="tdButton">
				
										<input type="button" name="navigationButtons" id="save" value="Save" class="save" onclick="javascript:saveFun();"/>
								
										<input type="button" name="navigationButtons" id="reset" value="Reset" class="reset" onclick="javascript:resetFun();"/>
								
										<input type="button" name="navigationButtons" id="finalize" value="Finalize" class="token" onclick="javascript:finalizeFun();"/>
								
										<input type="button" name="navigationButtons" id="recalculate" value="Recalculate" class="token" onclick="javascript:recalculateFun();"/>
								
									</td>
								</tr>
							</tbody>
						</table>
						
					<td width="22%">
					<div align="right"><font color="red">*</font>Indicates
					Required</div>
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
					<td>
					<table width="98%" border="0" align="center" cellpadding="2"
						cellspacing="2">

						<s:hidden name="startDate"></s:hidden>
						<s:hidden name="endDate"></s:hidden>
						<s:hidden name="apprId"></s:hidden>
						<s:hidden name="ratingType"></s:hidden>
						<s:hidden name="maxRating"></s:hidden>
						<tr>
							<td width="15%" colspan="1" height="20" class="formtext"><label
								name="appraisal.code" class="set" id="appraisal.code"
								ondblclick="callShowDiv(this);"><%=label.get("appraisal.code")%></label>
							<font color="red">*</font> :</td>
							<td width="30%" colspan="1" height="20"><s:textfield
								name="apprCode" size="30" readonly="true" /><img
								src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="15" theme="simple"
								onclick="javascript:callsF9(500,325,'ScoreBalancer_f9SelectAppraisal.action'); "></td>
							<td width="55%" colspan="2" height="20" class="formtext"><label
								name="scorebalancer.from.date" class="set"
								id="scorebalancer.from.date" ondblclick="callShowDiv(this);"><%=label.get("scorebalancer.from.date")%></label>
							: <s:property value="startDate"></s:property>&nbsp; <label
								name="scorebalancer.to.date" class="set"
								id="scorebalancer.to.date" ondblclick="callShowDiv(this);"><%=label.get("scorebalancer.to.date")%></label>
							: <s:property value="endDate"></s:property></td>

						</tr>
						<tr>
							<td colspan="1" width="15%"><label class="set" id="division"
								name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
							:</td>
							<td colspan="1" width="30%"><s:hidden name="divCode" /> <s:textfield
								name="divName" theme="simple" readonly="true" maxlength="50"
								size="30" /> <img src="../pages/images/recruitment/search2.gif"
								class="iconImage" height="16" align="absmiddle" width="15"
								onclick="javascript:callsF9(500,325,'ScoreBalancer_f9Div.action');"></td>

							<td colspan="1" width="15%"><label class="set" id="branch"
								name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
							:</td>
							<td width="40%" colspan="1"><s:hidden name="branchCode" />
							<s:textfield name="branchName" theme="simple" readonly="true"
								maxlength="50" size="30" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="16" align="absmiddle" width="15"
								onclick="javascript:callsF9(500,325,'ScoreBalancer_f9Branch.action');"></td>
						</tr>
						<tr>
							<td colspan="1" width="15%"><label class="set"
								id="department" name="department"
								ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
							:</td>
							<td colspan="1" width="30%"><s:hidden name="deptCode" /> <s:textfield
								name="deptNameUp" theme="simple" readonly="true" maxlength="50"
								size="30" /> <img src="../pages/images/recruitment/search2.gif"
								class="iconImage" height="16" align="absmiddle" width="15"
								onclick="javascript:callsF9(500,325,'ScoreBalancer_f9Dept.action');"></td>
							<td colspan="1" width="15%"><label class="set"
								id="appraiser" name="appraiser" ondblclick="callShowDiv(this);"><%=label.get("appraiser")%></label>
							:</td>
							<td colspan="1" width="40%"><s:hidden name="apprEmpCode" />
							<s:textfield name="apprEmpName" theme="simple" readonly="true"
								maxlength="50" size="30" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="16" align="absmiddle" width="15"
								onclick="javascript:callsF9(500,325,'ScoreBalancer_f9Employee.action');"></td>
						</tr>

					</table>
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
					<td width="25%" colspan="4"><b>Employee List</b></td>

					<td align="right" colspan="4" width="75%"><b>Page:</b> <%
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
 	if (!(PageNo1 == total1)) {
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

				<tr>
					<td class="formth" width="5%"><label name="scorebalancer.srno"
						class="set" id="scorebalancer.srno"
						ondblclick="callShowDiv(this);"><%=label.get("scorebalancer.srno")%></label></td>
					<td class="formth" width="25%" align="left"><label
						name="employee" class="set" id="employee"
						ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
					<td class="formth" width="25%" align="left"><label
						name="department" class="set" id="department"
						ondblclick="callShowDiv(this);"><%=label.get("department")%></label></td>
					<td class="formth" width="25%" align="left"><label
						name="scorebalancer.appraiser" class="set"
						id="scorebalancer.appraiser" ondblclick="callShowDiv(this);"><%=label.get("scorebalancer.appraiser")%></label></td>
					<td class="formth" width="5%" align="left"><label
						name="scorebalancer.actualscore" class="set"
						id="scorebalancer.actualscore" ondblclick="callShowDiv(this);"><%=label.get("scorebalancer.actualscore")%></label></td>
					<td class="formth" width="5%" align="center"><label
						name="scorebalancer.openrand" class="set"
						id="scorebalancer.openrand" ondblclick="callShowDiv(this);"><%=label.get("scorebalancer.openrand")%></label></td>
					<td class="formth" width="5%" align="left"><label
						name="scorebalancer.adjust" class="set" id="scorebalancer.adjust"
						ondblclick="callShowDiv(this);"><%=label.get("scorebalancer.adjust")%></label></td>
					<td class="formth" width="5%" align="left"><label
						name="scorebalancer.finalscore" class="set"
						id="scorebalancer.finalscore" ondblclick="callShowDiv(this);"><%=label.get("scorebalancer.finalscore")%></label>
					<td class="formth" width="5%" align="left"><input
						type="checkbox" name="checkAll" id="checkAll"
						onclick="chkAllEmp();" /></td>
				</tr>
				<%
					int j = 1, c = 0;
					int cnt = PageNo1 * 20 - 20;
				%>
				<s:if test="noData">
					<tr>
						<td width="100%" colspan="8" align="center" class="sortableTD"><font
							color="red">No Data To Display</font></td>
					</tr>
				</s:if>
				<s:else>

					<s:iterator value="empList">

						<tr>
							<td width="5%" align="center" class="td_bottom_border"><%=++cnt%></td>
							<td width="25%" align="left" class="td_bottom_border"><s:property
								value="empName" /></td>
							<td width="25%" align="left" class="td_bottom_border"><s:property
								value="deptName" /></td>
							<td width="25%" align="left" class="td_bottom_border"><s:property
								value="appraiserName" />&nbsp;</td>
							<input type="hidden" value='<s:property value="oprand"/>'
								name="hoprand" id='<%="hoprand"+j %>' />
							<s:if test="finalizeStatus">
								<td width="5%" align="left" class="td_bottom_border"><s:hidden
									name="empId" id='<%="empId"+j%>' /> <input type="text"
									name="apprScore" id="apprScore<%=j %>" size="3" readonly="true"
									value="<s:property value="apprScore" />" /></td>
								<td width="5%" align="left" class="td_bottom_border"><s:select
									disabled="true" name='oprand' id='<%="oprand"+j %>'
									theme="simple" onclick="disableChange(this);"
									list="#{'+':'+','-':'-'}" /></td>
								<td width="5%" align="left" class="td_bottom_border"><input
									type="text" name="adjustFactor" size="3"
									id="adjustFactor<%=j %>" maxLength="3" readonly="true"
									onkeyup="callFinal('<%=j %>')"
									value="<s:property value="adjustFactor" />" /></td>
								<td width="5%" align="left" class="td_bottom_border"><input
									type="text" name="apprFinalScore" id="apprFinalScore<%=j %>"
									size="3" readonly="true"
									value="<s:property value="apprFinalScore" />" />
								<td width="3%"><input type="checkbox" name="chkEmp"
									checked="checked" disabled="true" id='<%="chkEmp"+j %>'
									onclick="chkEmployee('<%=j%>');" /> <s:hidden
									name="templateCode" id='<%="templateCode"+j%>'></s:hidden></td>
							</s:if>
							<s:else>
								<td width="5%" align="left" class="td_bottom_border"><input
									type="text" name="apprScore" id="apprScore<%=j %>"
									readonly="readonly" size="3"
									value="<s:property value="apprScore" />" /><s:hidden
									name="empId" id='<%="empId"+j%>' /></td>
								<td width="5%" align="left" class="td_bottom_border"><s:select
									name='oprand' id='<%="oprand"+j %>' theme="simple"
									onchange="setHoprand(this);" list="#{'+':'+','-':'-'}" /></td>
								<td width="5%" align="left" class="td_bottom_border"><input
									type="text" name="adjustFactor" size="3"
									id="adjustFactor<%=j %>" maxlength="6"
									onkeypress=" return numbersWithDot()"
									onkeyup="callFinal('<%=j %>')"
									value="<s:property value="adjustFactor" />" /></td>
								<td width="5%" align="left" class="td_bottom_border"><input
									type="text" name="apprFinalScore" id="apprFinalScore<%=j %>"
									size="3" readonly="true"
									value="<s:property value="apprFinalScore" />" />
								<td width="3%"><input type="checkbox" name="chkEmp"
									id='<%="chkEmp"+j %>' onclick="chkEmployee('<%=j%>');" /> <s:hidden
									name="templateCode" id='<%="templateCode"+j%>'></s:hidden></td>
							</s:else>
							<%
								j++;
								c++;
							%>
						</tr>
					</s:iterator>

				</s:else>
				<tr>
					<td><input type="hidden" name="count" id="count"
						value="<%=c%>" /></td>
				</tr>


			</table>
			</td>
		</tr>


		<tr>
			<td colspan="3">
			<table width="100%" cellpadding="0" cellspacing="1">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%"></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">&nbsp;</td>
			<s:hidden name="show" value="%{show}" />
			<s:hidden name="myPage" id="myPage" />
		</tr>
	</table>


</s:form>
</body>
</html>


<script>
				
		var fieldName  = ["paraFrm_apprCode"];
		var lableName  = ["Appraisal Code"];
		var flag	   = ["select"];
		pgshow();
		
		function saveFun(){
			
			if(document.getElementById("paraFrm_apprCode").value==""){
				
				alert("Please select "+document.getElementById("appraisal.code").innerHTML)	;
				return false;	
			}
			var tableLength = document.getElementById("count").value;
			if(tableLength == 0 ){
				
				alert("No employees for this appraisal.");
				return false;
			}
			if(!chkAllFinalize()){
				
					alert("Appraisal scores for all the employees have been already finalised");
					return false;
			}
			document.getElementById("paraFrm").action="ScoreBalancer_save.action";
			document.getElementById("paraFrm").submit();
		}
		function resetFun(){
			
			document.getElementById('paraFrm_startDate').value='';
			document.getElementById('paraFrm_endDate').value='';
			document.getElementById('paraFrm_apprId').value='';
			document.getElementById("paraFrm_apprCode").value='';
			document.getElementById('paraFrm_apprCode').value='';
			document.getElementById('paraFrm_divCode').value='';
			document.getElementById('paraFrm_divName').value='';
			document.getElementById('paraFrm_branchCode').value='';
			document.getElementById('paraFrm_branchName').value='';
			document.getElementById('paraFrm_deptCode').value='';
			document.getElementById('paraFrm_deptNameUp').value='';
			document.getElementById('paraFrm_apprEmpCode').value='';
			document.getElementById('paraFrm_apprEmpName').value='';
			document.getElementById("paraFrm").action="ScoreBalancer_input.action";
			document.getElementById("paraFrm").submit();
			document.getElementById("paraFrm").target = 'main';
		}
		function finalizeFun(){
			
			if(document.getElementById("paraFrm_apprCode").value==""){
				alert("Please select "+document.getElementById("appraisal.code").innerHTML)	;
				return false;	
			}
			var tableLength = document.getElementById("count").value;
			if(tableLength == 0 )
			{
				alert("No employees for this appraisal.");
				return false;
			}
			
			var flag=false;
			if(!chkAllFinalize())
			{
				alert("Appraisal scores for all the employees have been already finalised");
				return false;
			}
			for(i=1;i<=document.getElementById('count').value;i++)
				if(document.getElementById('chkEmp'+i).checked && !document.getElementById('chkEmp'+i).disabled)
				{
					flag=true;
					break;
				}
				if(!flag)
				{
					alert("Please select employees to finalize");
					return false;
				}
				if(!confirm("Are you sure want to finalize these record(s)?"))
					return false;
					var apprId = document.getElementById('paraFrm_apprId').value;
					/*finalizeEmployees('ScoreBalancer_finalizeEmployees.action?apprId='+apprId+'&abc='+Math.random(),'paraFrm');*/
					document.getElementById("paraFrm").action='ScoreBalancer_finalizeEmployees.action?apprId='+apprId+'&abc='+Math.random();
					document.getElementById("paraFrm").submit();
					document.getElementById("paraFrm").target = 'main';
					alert("Appraisal score for selected employees has been finalized");
				
					var flag=0;
					for(i=1;i<=document.getElementById('count').value;i++)
						if(document.getElementById('chkEmp'+i).checked)
						{
							document.getElementById('chkEmp'+i).disabled=true;
							document.getElementById('apprScore'+i).readOnly=true;
							document.getElementById('adjustFactor'+i).readOnly=true;
							document.getElementById('oprand'+i).disabled=true;
							document.getElementById('apprFinalScore'+i).readOnly=true;
						}
						else if(flag==0)
							flag=2;
						if(flag==0)
						document.getElementById('checkAll').disabled=true;
		}
		  
		function recalculateFun(){
		
			if(document.getElementById("paraFrm_apprCode").value==""){
			
				alert("Please select "+document.getElementById("appraisal.code").innerHTML)	;
				return false;	
			}
			var tableLength = document.getElementById("count").value;
		
			if(tableLength == 0 ){
				alert("No employees for this appraisal.");
				return false;
			}
				
			var flag=false;
			if(!chkAllFinalize())
			{
				alert("Appraisal scores for all the employees have been already finalised");
				
			}
			for(i=1;i<=document.getElementById('count').value;i++)
				if(document.getElementById('chkEmp'+i).checked && !document.getElementById('chkEmp'+i).disabled)
				{
					flag=true;
					break;
				}
				if(!flag)
				{
					alert("Please select employees to recalculate");
					return false;
				}
				if(!confirm("Are you sure want to recalculate these record(s)?"))
					return false;
				var apprId = document.getElementById('paraFrm_apprId').value;
				recalculateEmployees('ScoreBalancer_recalEmployees.action?apprId='+apprId,'paraFrm');
				
		}
		
		function searchFun(){

			document.getElementById("paraFrm_saveFlag").value='true';
			callsF9(500,325,'TemplateDefination_search.action');
		}
		
		function validateF9(action){
			alert("validateF9");
			
			if(document.getElementById("paraFrm_apprCode").value=="") {
				alert("Please select "+document.getElementById("appraisal.code").innerHTML.toLowerCase());
				document.getElementById("paraFrm_apprCode").focus();
				return false;
			}
					
			callsF9(500,325,'ScoreBalancer_'+action+'.action'); 
		}
		
		
		function callFinal(id){
			
			var apprScore = document.getElementById("apprScore"+id).value
			var finalScore = document.getElementById("apprFinalScore"+id).value
			var adjust = document.getElementById("adjustFactor"+id).value
			if(adjust == '')
				adjust=0;
			var aperand = document.getElementById("oprand"+id).value
			if(aperand == '+'){
				document.getElementById("apprFinalScore"+id).value = Math.round((eval(apprScore)+ eval(adjust)) * Math.pow(10, 2))/ Math.pow(10, 2);
			}
			if(aperand == '-'){
				document.getElementById("apprFinalScore"+id).value = Math.round((eval(apprScore)- eval(adjust)) * Math.pow(10, 2))/ Math.pow(10, 2);
			}
			if(document.getElementById("apprFinalScore"+id).value > eval(document.getElementById("paraFrm_maxRating").value))
			{
					alert(document.getElementById("scorebalancer.finalscore").innerHTML+' should not exceed Max Overall Rating '+document.getElementById("paraFrm_maxRating").value);
					document.getElementById("apprFinalScore"+id).value =apprScore;
					document.getElementById("adjustFactor"+id).value=0;
			}
		}
		function next(){
			
		   var pageno=	document.getElementById('myPage').value;
		   	if(pageno=="1")
		   	{	document.getElementById('myPage').value=2;
			 document.getElementById('paraFrm_show').value=2;
			 }
			 else{
			 document.getElementById('myPage').value=eval(pageno)+1;
			 document.getElementById('paraFrm_show').value=eval(pageno)+1;
			 }
			   document.getElementById('paraFrm').action="ScoreBalancer_paging.action";
			   document.getElementById('paraFrm').submit();
		}
			//-----function for previous
		function previous(){
				
		   var pageno=	document.getElementById('myPage').value;
		   	
			 document.getElementById('myPage').value=eval(pageno)-1;
			 document.getElementById('paraFrm_show').value=eval(pageno)-1;
			 document.getElementById('paraFrm').action="ScoreBalancer_paging.action";
			 document.getElementById('paraFrm').submit();
			   
		}
		function on(){
		  	
			var val= document.getElementById('selectname').value;
			document.getElementById('paraFrm_show').value=val;
			document.getElementById('myPage').value=eval(val);
			document.getElementById('selectname').value=val;
			document.getElementById('paraFrm').action="ScoreBalancer_paging.action";
			document.getElementById('paraFrm').submit();
		}
		  
		function pgshow(){
		  	
			var pgno=document.getElementById('paraFrm_show').value;
		  
		  	if(!(pgno==""))
		  	 document.getElementById('selectname').value=pgno;
		  	 onLoad();
		 }
		  	
		 function callPage(id){
			document.getElementById('myPage').value=id;
			document.getElementById('paraFrm_show').value=id;
			document.getElementById('paraFrm').action="ScoreBalancer_paging.action";
			document.getElementById('paraFrm').submit();
		 }
		   
		 function onLoad(){
			 
		   	if(document.getElementById('count').value=="" ||document.getElementById('count').value==0)
		   		return false;
			for(i=1;i<=document.getElementById('count').value;i++)
				if(document.getElementById('hoprand'+i).value == "")
					document.getElementById('hoprand'+i).value = '+';
						
		   	for(i=1;i<=document.getElementById('count').value;i++)
		   		if(!document.getElementById('chkEmp'+i).checked){
		   			
		   			document.getElementById('checkAll').checked=false;
		   			return false;
		   		}
		   		document.getElementById('checkAll').checked=true;
		   		document.getElementById('checkAll').disabled=true;
		   }
		   function chkEmployee(id)
		   {
		   		if(document.getElementById('count').value=="" ||document.getElementById('count').value==0)
		   			return false;
		   		
		   		document.getElementById('chkEmp'+id).value=document.getElementById('apprScore'+id).value
		   		+'&'+document.getElementById('adjustFactor'+id).value
		   		+'&'+document.getElementById('oprand'+id).value
		   		+'&'+document.getElementById('apprFinalScore'+id).value
		   		+'&'+document.getElementById('empId'+id).value
		   		+'&'+document.getElementById('templateCode'+id).value
		   		+'&'+id;
		   		document.getElementById('chkEmp'+id).value=document.getElementById('chkEmp'+id).value.replace('+','P');
		   		document.getElementById('chkEmp'+id).value=document.getElementById('chkEmp'+id).value.replace('-','M');
		   		
		   		if (document.getElementById('chkEmp'+id).checked == true)
		   		{
		   			document.getElementById('oprand'+id).disabled=true;	
		   			document.getElementById('adjustFactor'+id).readOnly=true;
		   		}
		   		else 
		   		{
		   			document.getElementById('oprand'+id).disabled=false;	
		   			document.getElementById('adjustFactor'+id).readOnly=false;
		   		}
		   		
		   		for(i=1;i<=document.getElementById('count').value;i++)
		   			if(!document.getElementById('chkEmp'+i).checked)
		   			{
		   				document.getElementById('checkAll').checked=false;
		   				return false;
		   			}
		   		document.getElementById('checkAll').checked=true;
		   }
		   function chkAllEmp()
		   {
		   		for(i=1;i<=document.getElementById('count').value;i++)
		   		{
		   			if(!document.getElementById('chkEmp'+i).disabled)
		   				document.getElementById('chkEmp'+i).checked=document.getElementById('checkAll').checked;
		
			   		document.getElementById('chkEmp'+i).value=document.getElementById('apprScore'+i).value
			   		+'&'+document.getElementById('adjustFactor'+i).value
			   		+'&'+document.getElementById('oprand'+i).value
			   		+'&'+document.getElementById('apprFinalScore'+i).value
			   		+'&'+document.getElementById('empId'+i).value
			   		+'&'+document.getElementById('templateCode'+i).value
			   		+'&'+i;
					
			   		document.getElementById('chkEmp'+i).value=document.getElementById('chkEmp'+i).value.replace('+','P');
			   		document.getElementById('chkEmp'+i).value=document.getElementById('chkEmp'+i).value.replace('-','M');
			   		
			   		if (document.getElementById('chkEmp'+i).checked == true)
			   		{
			   			document.getElementById('oprand'+i).disabled=true;	
			   			document.getElementById('adjustFactor'+i).readOnly=true;
			   		}
			   		else 
			   		{
			   			document.getElementById('oprand'+i).disabled=false;	
			   			document.getElementById('adjustFactor'+i).readOnly=false;
			   		}
		   		}
		   }
		   
		   function setHoprand(obj)
		   {
		   		var id=obj.id.replace("oprand","");
		   		document.getElementById('hoprand'+id).value=document.getElementById('oprand'+id).value;
		   		//alert(id);
		   		callFinal(id);
		   }
		   
		   function chkAllFinalize()
		   {
		   		for(i=1;i<=document.getElementById('count').value;i++)
		   			if(!document.getElementById('chkEmp'+i).disabled){
		   				return true;
		   			}
		   		return false;
		   }
		
	
</script>
