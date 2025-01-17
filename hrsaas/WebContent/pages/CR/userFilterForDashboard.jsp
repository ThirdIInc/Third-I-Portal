<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp"%>

<STYLE type=text/css>
a:hover {
	COLOR: #FF0000;
	text-decoration: underline;
}
</STYLE>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script type="text/javascript">
	var records = new Array();
</script>
 <s:form action="PerformanceMetrics" id="paraFrm" validate="true"
	theme="simple" target="main">
	
	
		<s:hidden name="dashBoardID"></s:hidden>
		<s:hidden name="userType" id="userType"></s:hidden>
		<s:hidden name="informCode" />
		
		<s:hidden name="dashBoardName"/>
		<s:hidden name="accountName"/>
		<s:hidden id="accountID" name="accountID"/>
		<s:hidden id="backToAccountLsit" name="backToAccountLsit"/>
	<table width="100%" align="right" class="formbg">
		<tr>
			<td valign="bottom" class="txt" colspan="2">
			<table width="100%" align="right" class="formbg" >
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="93%" class="txt"><strong class="text_head">User Filter</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td align="left"><input type="button" class="save"
				value="Save" onclick="return save();" />
			<input type="button" class="back"
				value="  Back " onclick="return backFun();" /></td>
		</tr>
		
		<tr>
		<td>
		<table width="100%" border="0">
							<tr>
								<td width="15%" colspan="1" height="22">
								<label class="set" name="dashBoardName" id="dashBoardName" ondblclick="callShowDiv(this);">
								DashBoard Name</label>:
								<font color="red"></font>
								</td>
								<td  colspan="2" height="22">
								<s:textfield  theme="simple" size="25" readonly="true" value="%{dashBoardName}"cssStyle="background-color: #F2F2F2;">
								</s:textfield> </td>
								<s:if test="accountID!=null">
								<s:if test="accountID!=''">
								<td  colspan="1" height="22">
								<label class="set" name="accountName" id="accountName" ondblclick="callShowDiv(this);">
								Account Name</label>:
								<font color="red"></font>
								</td>
								<td  colspan="1" height="22">
								<s:textfield  theme="simple" size="45" readonly="true" value="%{accountName}"cssStyle="background-color: #F2F2F2;">
								</s:textfield> </td>
								</s:if>
								</s:if>
								
							</tr>
							<tr>
							<td>
								<label  name="userName" id="informName" ondblclick="callShowDiv(this);">
								User Name</label>:
								</td>
								<td  colspan="1" height="22">
								<s:textfield  theme="simple" size="25" readonly="true" value="%{informName}"cssStyle="background-color: #F2F2F2;">
								</s:textfield> </td>
								
							</tr>
							
	
						</table>
		</td>
		</tr>
	
		
		
		<s:if test="showParameterFlag">
		<tr>
			<td>
			<fieldset><legend class="legend1"> Parameters
			 </legend>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<s:iterator value="filterList">
					<tr>
						<td width="50%"><s:property value="parameterName"  /><s:hidden name="parameterName" /></td>
						<td width="50%"><s:textfield name="defaultValue" maxlength="450"></s:textfield></td>
					</tr>
				</s:iterator>

			</table>
			</fieldset>
			</td>
		</tr>
		</s:if>
		<s:if test="showReportFlag">
		<tr>
			<td>
			<fieldset><legend class="legend1"> Reports
			 </legend>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<% int j=1; %>
				<s:iterator value="reportList">
					<tr>
						<td width="50%"><s:property value="reportName" /><s:hidden name="reportName"/></td>
						<td align="left" width="50%"><input type="checkbox" name="report" class="checkbox" id="<%="report"+j%>" onclick="callCheck('report<%=j%>','report<%=j%>')">
						<s:hidden name="reportHidden" id="<%="reportHidden"+j%>"/>
						<s:hidden name="reportId" id="<%="reportId"+j%>"/></td>
					</tr>
					<% j++; %>
				</s:iterator>
			<input type="hidden" name="otherLengthVar" id="otherLengthVar" value="<%=j%>"/> 
			</table>
			</fieldset>
			</td>
		</tr>
		</s:if>
		<s:if test="showGraphFlag">
		<tr>
			<td>
			<fieldset><legend class="legend1"> Graphs
			 </legend>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<%int k =1; %>
				<s:iterator value="graphList">
					<tr>
						<td width="50%"> <s:property value="graphName" /><s:hidden name="graphName"/></td>
						<td align="left" width="50%"><input type="checkbox" name="graph" id="<%="graph"+k%>" class="checkbox" onclick="callCheckGraph('graph<%=k%>','graph<%=k%>')">
						<s:hidden name="graphHidden" id="<%="graphHidden"+k%>"/>
						<s:hidden name="componentId" /><s:hidden name= "autoIdForGraph"/></td>
					</tr>
					<%k++; %>
				</s:iterator>
			<input type="hidden" name="otherLengthVars" id="otherLengthVars" value="<%=k%>"/> 
			</table>
			</fieldset>
			</td>
		</tr>
		</s:if>
		<% int l=1 ;%>
		<s:if test="documentList!=null ">
		<tr>
		<td>
		<fieldset>
		<legend class="legend1"> User Documents</legend>
		<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
		
		
		<s:iterator value="documentList">
		<tr>
		<td width="50%">
		<s:property  value="documentFileName" />
		<s:hidden name="documentFileName"></s:hidden>
		</td>
		<td>
		<input type="checkbox" name="document" id="<%="document"+l %>" class="checkbox" onclick="callCheckDocuments()">
		<s:hidden name="docHidden" id="<%="docHidden"+l%>"/>
		<s:hidden name="autoidDoc"></s:hidden>
		</td>
		</tr>
		<%l++; %>
		</s:iterator>
		</table>
		</fieldset>
		</td>
		</tr>
		
		</s:if>
		
		<input type="hidden" name="otherLengthVars1" id="otherLengthVars1" value="<%=l%>"/> 
		
		
		
		<tr>
		<td>
		<fieldset>
		<legend class="legend1"> Apply Configuration </legend>
		<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td width="50%"><label class="set" name="applDiv" id="applDiv"
						ondblclick="callShowDiv(this);">Apply Same Configuration to Other Users </label>
					:</td>
					<td ><s:hidden name="applUserCode"></s:hidden>
					<s:textarea name="applUserNameTxtAr" cols="25" rows="2"
						readonly="true"></s:textarea> <img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="16" theme="simple"
						onclick="javascript:callF9Function('paraFrm_applUserNameTxtAr','paraFrm_applUserCode'); ">
					</td>
				</tr>
				</table>
		</fieldset>	
		</td>
				</tr>	
		
		<tr>
			<td align="left"><input type="button" class="save"
				value="Save" onclick="return save();" />
			<input type="button" class="back"
				value="  Back " onclick="return backFun();" /></td>
		</tr>
		</table>
		
	</s:form>
	<script type="text/javascript">
	
	onLoad();
	 function onLoad(){
	 	try{
	 	graphOnLoad();
	 	var listCount = document.getElementById('otherLengthVar').value;
		var doclist=document.getElementById('otherLengthVars1').value;
		
		///alert("listCount"+listCount)
		for(var i = 1; i < listCount; i++) {
			//alert("Report -"+i+" "+document.getElementById('reportHidden'+i).value);
			if(document.getElementById('reportHidden'+i).value=='Y'){
				document.getElementById('report'+i).checked=true;
				document.getElementById('reportHidden'+i).value="Y";
				}
			else{
				document.getElementById('reportHidden'+i).value="N";
				}
			}
		
		for(var i=1;i<doclist;i++){
		///alert("Documents - "+i+" "+document.getElementById('docHidden'+i).value)
		if(document.getElementById('docHidden'+i).value=='Y'){
				document.getElementById('document'+i).checked=true;
				document.getElementById('docHidden'+i).value="Y";
				}
			else{
				document.getElementById('docHidden'+i).value="N";
				}
		}
		}
		catch(e){
			///alert(e);
		}
	 }
	
	function backFun(){
	
	if(document.getElementById('userType').value=='I')
	{
	document.getElementById("paraFrm").action="ManageClientDashBoard_callForInternalUsers.action";
	document.getElementById('paraFrm').target = "_self";
	document.getElementById("paraFrm").submit();
	}
	else{
 	document.getElementById("paraFrm").action="ManageClientDashBoard_callForExternalUsers.action";
	document.getElementById('paraFrm').target = "_self";
	document.getElementById("paraFrm").submit();
	
	}
	}
	
	function callCheck(checkbox,field){
	var listCount = document.getElementById('otherLengthVar').value;
	
		for(var i = 1; i < listCount; i++) {
					if(document.getElementById('report'+i).checked == true){
						
						document.getElementById('reportHidden'+i).value="Y";
					}
					else{
						document.getElementById('reportHidden'+i).value="N";
					}
					
				}	
	}
	
	function callCheckGraph(checkbox,field){
	var list = document.getElementById('otherLengthVars').value;
	
		for(var i = 1; i < list; i++) {
					if(document.getElementById('graph'+i).checked == true){
						
						document.getElementById('graphHidden'+i).value="Y";
					}
					else{
						document.getElementById('graphHidden'+i).value="N";
					}
					
				}	
	}
	
	function callCheckDocuments(){
	
	var list = document.getElementById('otherLengthVars1').value;
		for(var i=1;i<list;i++){
			if(document.getElementById('document'+i).checked == true){
						
						document.getElementById('docHidden'+i).value="Y";
					}
					else{
						document.getElementById('docHidden'+i).value="N";
					}		
					}
	}
	
	function save(){
	
	document.getElementById("paraFrm").action="ManageClientDashBoard_saveFilter.action"
	document.getElementById('paraFrm').target = "_self";
	document.getElementById("paraFrm").submit();
	
	}
	
	function callF9Function(applyUserName,applayUserCode)
 {   
 	
   callsF9(500,325,'ManageClientDashBoard_f9users.action?applyUserName='+applyUserName+'&applayUserCode='+applayUserCode);
 }
 
 function graphOnLoad(){
 try{
 var list = document.getElementById('otherLengthVars').value;
 ///	alert("list - "+list)
		for(var i = 1; i < list; i++) {
			//alert("Graph -"+i+" "+document.getElementById('graphHidden'+i).value);
			if(document.getElementById('graphHidden'+i).value=='Y'){
				document.getElementById('graph'+i).checked=true;
				document.getElementById('graphHidden'+i).value="Y";
				}
			else{
				document.getElementById('graphHidden'+i).value="N";
				}
		}
		}catch(e){
		//alert(e);
		}
 }
	
	</script>