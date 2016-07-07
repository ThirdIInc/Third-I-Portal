<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="DefineSection" validate="true" id="paraFrm"
	theme="simple">
<%
String calupdateflag=(String)request.getAttribute("calupdateFlag");
%>
<s:hidden  name="calupdateFlag" value="<%=calupdateflag %>"/>

	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		
		<tr>
	        <td colspan="3" width="100%">
	        	<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
	        		 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Define Section</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				     </tr>
	        	</table>
	        </td>
        </tr>
       
    	<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
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

						<tr>
							<s:hidden name="apprId"></s:hidden>
							<s:hidden name="startDate"></s:hidden>
							<s:hidden name="endDate"></s:hidden>
							<s:hidden name="apprCode"></s:hidden>
							<s:hidden name="templateCode"></s:hidden>
							<s:hidden name="templateName"></s:hidden>
							<s:hidden name="addFLag"></s:hidden>
							<s:hidden name="srNo"></s:hidden>
							<s:hidden name="lockAppraisal"></s:hidden>
							

							<td width="25%" colspan="1" height="20" class="formtext"><label
								class="set" name="appraisal.code" id="appraisal.code" ondblclick="callShowDiv(this);"><%=label.get("appraisal.code")%></label>
							:</td>
							<td width="25%" colspan="1" height="20"><s:property
								value="apprCode" /></td>
							<td width="30%" colspan="1" height="20" nowrap="nowrap" class="formtext">
							<label class="set"  name="template.appr.start.date" id="template.appr.start.date" ondblclick="callShowDiv(this);"><%=label.get("template.appr.start.date")%></label>:
								<s:property	value="startDate"></s:property>&nbsp;&nbsp;
							<label class="set"  name="template.appr.end.date" id="template.appr.end.date" ondblclick="callShowDiv(this);"><%=label.get("template.appr.end.date")%></label>:
								<s:property value="endDate"></s:property>
							</td>
							<td width="20%" colspan="1" height="20"></td>
						</tr>
						<tr>
							<td width="25%" colspan="1" height="20" class="formtext"><label
								class="set" name="template.name" id="template.name" ondblclick="callShowDiv(this);"><%=label.get("template.name")%></label>
							:</td>
							<td width="25%" colspan="1" height="20"><s:property
								value="templateName" /></td>
							<td width="30%" colspan="1" height="20" class="formtext"></td>
							<td width="20%" colspan="1" height="20"></td>
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
					<td>
					<table width="98%" border="0" align="center" cellpadding="2"
						cellspacing="2">

						<tr>
							<td width="25%" colspan="1" height="20" class="formtext"><label name="section.name"
								class="set" id="section.name" ondblclick="callShowDiv(this);"><%=label.get("section.name")%></label>
							<font color="red">*</font> :</td>
							<td width="25%" colspan="1" height="20"><s:textfield
								name="sectionName" size="30" maxlength="145"/></td>
							<td width="25%" colspan="1" height="20" class="formtext"><s:submit cssClass="add"  onclick="return callAdd();"   action="DefineSection_addSection" theme="simple"  value="   Add   " /></td>
							<td width="25%" colspan="1" height="20"></td>
						</tr>
</table>

</td></tr></table></td></tr>
    <tr>
    <%try{
    %>
    <%!int l= 0,z=0; %>
      <%!int i= 0,x=0; %>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
							<td width ="100%"><b>V-Visibility, R-Rating, C-Comment</b> </td>
						</tr>
				<tr>
					<td class="formtext">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="sortable">
						
						<tr>
						<td class="formth" width="1%"><label name="section.srno" class="set" id="section.srno" ondblclick="callShowDiv(this);"><%=label.get("section.srno")%></label></td>
						<td class="formth" width="10%"><label name="section.name" class="set" id="section.name1" ondblclick="callShowDiv(this);"><%=label.get("section.name")%></label></td>
						<td class="formth" width="1%"><label name="section.visibility" class="set" id="section.visibility" ondblclick="callShowDiv(this);"><%=label.get("section.visibility")%></label></td>
						<% Object [][] phaseCode =(Object[][])request.getAttribute("phaseCode"); %>
						<%if(phaseCode != null && phaseCode.length != 0) {
							 %>
							<% for(;l<phaseCode.length;l++) {%>
								<td class="formth" width="2%" >
								<s:hidden name="phaseCode" value='<%=""+phaseCode[l][0] %>'/>
								<%=String.valueOf(phaseCode[l][1]) %>
							
								</td>
							<%}%>					
							<%}%>	
							<!--  
								<td class="formth" width="8%"><label name="section.shuffle" class="set" id="section.shuffle" ondblclick="callShowDiv(this);"><%=label.get("section.shuffle")%></label> </td>
								<td class="formth" width="3%"><label name="section.remove" class="set" id="section.remove" ondblclick="callShowDiv(this);"><%=label.get("section.remove")%></label> </td>			
							-->
						</tr>
						<%z=l;
						l=0;%>
						<%  String [] sectName =(String[])request.getAttribute("sectionname"); %>
						<% String [] sectCode =(String[])request.getAttribute("sectioncode"); %>
						<% Object [][] sectionData =(Object[][])request.getAttribute("sectionData"); %>
						<%if (sectName !=  null ){ %>
						<%int a=0; %>
						<%  for(;i<sectCode.length;i++) {%>
					<tr>
							<td width="1%" align="center" ><%=i+1 %></td>
							<td width="10%" ><s:hidden name="secCode"  value='<%=sectCode[i] %>'/>
							<s:hidden name="priority" value='<%=""+(i+1) %>' />
							<%=String.valueOf(sectName[i]) %><s:hidden name="secName" value='<%=sectName[i] %>'  id='<%="secName"+i%>' />
							
							</td>
							<td width="1%" align="center" >
								<table>
									<tr><td>V</td></tr>
									<tr><td>R</td></tr>
									<tr><td>C</td></tr>
								</table>
							</td>
							
							<%  for(int j=0;j<phaseCode.length;j++) {%>
								
								<td width="2%" align="center" >
								<table>
									<tr><td><input type="checkbox" name="<%=i %>_<%=j %>_V"  onclick="callChk('<%=i %>_<%=j %>_V');"   id="<%=i %>_<%=j %>_V"   />
									<input type="hidden" name="h_<%=i %>_<%=j %>_V"  id="h_<%=i %>_<%=j %>_V"  value='N'  />
									<%if(sectionData != null && sectionData.length != 0) { %>
										<% if(String.valueOf(sectionData[a][2]).equals("Y")){ %>
											<script>
											
													document.getElementById(<%=i%>+'_'+<%=j%>+'_V').checked=true;
												document.getElementById('h_'+<%=i%>+'_'+<%=j%>+'_V').value='Y';
											</script>
									
										<%}%>
									<%}%>
									</td></tr>
									
									<tr><td><input type="checkbox" name="<%=i %>_<%=j %>_R"  onclick="callChk('<%=i %>_<%=j %>_R');"  id="<%=i %>_<%=j %>_R"   />
									<input type="hidden" name="h_<%=i %>_<%=j %>_R"  id="h_<%=i %>_<%=j %>_R"  value='N'  />
									<%if(sectionData != null && sectionData.length != 0) { %>
										<% if(String.valueOf(sectionData[a][3]).equals("Y")){ %>
											<script>
												document.getElementById(<%=i%>+'_'+<%=j%>+'_R').checked=true;
												document.getElementById('h_'+<%=i%>+'_'+<%=j%>+'_R').value='Y';
											</script>
									
										<%}%>
									<%}%>
									</td></tr>
									
									<tr><td><input type="checkbox" name="<%=i %>_<%=j %>_C"  onclick="callChk('<%=i %>_<%=j %>_C');"  id="<%=i %>_<%=j%>_C" />
									<input type="hidden" name="h_<%=i %>_<%=j %>_C"  id="h_<%=i %>_<%=j %>_C" value='N'  />
									<%if(sectionData != null && sectionData.length != 0) { %>
										<% if(String.valueOf(sectionData[a][4]).equals("Y")){ %>
											<script>
												document.getElementById(<%=i%>+'_'+<%=j%>+'_C').checked=true;
												document.getElementById('h_'+<%=i%>+'_'+<%=j%>+'_C').value='Y';
											</script>
									
										<%}%>
									<%}%>
									</td></tr>
								</table>
								</td>
										
								<%a++; %>
							<%}%>					
				
						<!--  <td width="8%" align="center">
							<input type="button" name="Up" value="Up" class="token" onclick="shuffleRow(<%=i %>,'up')"/>
							<input type="button" name="Down" value="Down" class="token"  onclick="shuffleRow(<%=i %>,'down')" />
						</td>	
						<td width="3%" align="center">
							<input type="button" name="Remove" value="Remove" class="token" onclick="deleteRow(<%=i %>)"/>
						</td>	-->
						
						<%} %>
						<%} %>
						
						</tr><%x=i;
						i=0;%>
						<%}
    					catch(Exception e){
							e.printStackTrace();
						} %>
</table>
</td>
</tr>
</table>
</td>
</tr>
	
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%"></td>
				</tr>
			</table>
			</td>
		</tr>


	</table>
	


</s:form>




<script type="text/javascript">

function callChk(id){

if(document.getElementById(id).checked){
	document.getElementById('h_'+id).value='Y'
		
}else{

document.getElementById('h_'+id).value='N'
}
}

var fieldName  = ["paraFrm_sectionName"];
var lableName  = ["section.name"];
var types  = ["enter"];

function saveFun(){
		
			var countP = <%=z%>;
			var countS = <%=x%>;
			
			//if(document.getElementById("paraFrm_lockAppraisal").value == 'true'){
			//	alert("Question mapped with section so can't change.");
			//	return false;
			//}
	
			if(countP == 0){
				alert('Please add phases.');
				return false;
			}
			if(countS == 0){
				alert('Please add atleast one section.');
				return false;
			}
			document.getElementById("paraFrm").action="DefineSection_save.action";
			document.getElementById("paraFrm").submit();
		
		
}
function saveandnextFun(){
			var countP = <%=z%>;
			var countS = <%=x%>;
			
			//if(document.getElementById("paraFrm_lockAppraisal").value == 'true'){
			//	var conf=confirm("Question mapped with section so can't change Do you want to move next.");
  			//		if(conf){
  			//			document.getElementById("paraFrm").action="SectionMapping_input.action";
			//			document.getElementById("paraFrm").submit();
  			//			return true;
  			//			}
	  		//		else
	  		//		return false;
	  		//}
			if(countP == 0){
				alert('Please add phases.');
				return false;
			}
			if(countS == 0){
				alert('Please add atleast one section.');
				return false;
			}
			document.getElementById("paraFrm").action="DefineSection_saveAndNext.action";
			document.getElementById("paraFrm").submit();

}
function saveandpreviousFun(){

			var countP = <%=z%>;
			var countS = <%=x%>;
			
			//if(document.getElementById("paraFrm_lockAppraisal").value == 'true'){
			//	var conf=confirm("Question mapped with section so can't change Do you want to move previous");
  			//		if(conf){
  			//			document.getElementById("paraFrm").action="AppraisalInstruction_input.action";
			//			document.getElementById("paraFrm").submit();
  			//			return true;
  			//			}
	  		//		else
	  		//		return false;
	  		//}
	  		
			if(countP == 0){
				alert('Please add phases.');
				return false;
			}
			if(countS == 0){
				alert('Please add atleast one section.');
				return false;
			}
			document.getElementById("paraFrm").action="DefineSection_saveAndPrevious.action";
			document.getElementById("paraFrm").submit();

}

function shuffleRow(id,type){

	document.getElementById('paraFrm_srNo').value = id;
	
	var countS = <%=x%>;
	
	if(document.getElementById("paraFrm_lockAppraisal").value == 'true'){
			alert("appraisal process started so can't change.");
			return false;
	}
	if(type == 'up'&& id > 0 ){
	document.getElementById("paraFrm").action="DefineSection_shuffleRow.action?type=up";
	document.getElementById("paraFrm").submit();
	}
	if(type == 'down' && id+1 != countS ){
	document.getElementById("paraFrm").action="DefineSection_shuffleRow.action?type=down";
	document.getElementById("paraFrm").submit();
	}
	
}
function deleteRow(id){
	
	document.getElementById('paraFrm_srNo').value = id;
	
	if(document.getElementById("paraFrm_lockAppraisal").value == 'true'){
			alert("appraisal process started so can't change.");
			return false;
	}
	
	 var conf=confirm("Do you really want to Remove this record ?");
  		if(conf){
  			document.getElementById("paraFrm").action="DefineSection_removeSection.action";
			document.getElementById("paraFrm").submit();
  			return true;
  		}
	  	else{
	  		 return false;
	  	}
	    return true;
}


function cancelFun(){
		document.getElementById("paraFrm").action="AppraisalInstruction_input.action";
	    document.getElementById("paraFrm").submit();	
				
}

function resetFun(){
		
		document.getElementById("paraFrm").action="DefineSection_reset.action";
		document.getElementById("paraFrm").submit();
		
}
function nextFun(){
		
		document.getElementById("paraFrm").action="TrainingDetails_input.action";
		document.getElementById("paraFrm").submit();
		
}
function previousFun(){
		
		document.getElementById("paraFrm").action="AppraisalInstruction_input.action";
	    document.getElementById("paraFrm").submit();	
		
}
function callAdd(){
			
			var count = <%=z%>;
			var countS = <%=x%>;
			
			//if(document.getElementById("paraFrm_lockAppraisal").value == 'true'){
			//	alert("appraisal process started so can't change.");
			//	return false;
			//}
	
			if(count == 0){
				alert('Please add phases');
				return false;
			}
			if(!validateBlank(fieldName, lableName,types)){
				return false;			  
			}
			var newSection = document.getElementById("paraFrm_sectionName").value;
			for(var count=0;count<eval(countS);count++){
				//alert(".........."+newSection);
				//alert("count= "+count+" section-------- "+document.getElementById("secName"+count).value);
				if(newSection == document.getElementById("secName"+count).value){
					alert(document.getElementById("section.name").innerHTML.toLowerCase()+" already exists.Please add different "+document.getElementById("section.name").innerHTML.toLowerCase());
					return false;
				}
		
			}
			
		}
</script>
