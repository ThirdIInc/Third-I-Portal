<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/pages/common/labelManagement.jsp" %>
<s:form action="DashletSettings" method="post" name="DashletSettings"
	validate="true" id="paraFrm" theme="simple">
	
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />
	
	<!-- Flags used For Cancel Button -->
	
	<s:hidden name="loadFlag"/>
	<s:hidden name="addFlag"/>
	<s:hidden name="modFlag"/>  
	<s:hidden name="dbFlag"/>
	
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Dashlet  
					Settings </strong></td>
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
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4">
					<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
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
					<td>
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="2">
		<tr>
			
			<td width="15%" colspan="1" height="22"><label name="dashlet.name" id="dashlet.name" ondblclick="callShowDiv(this);"><%=label.get("dashlet.name") %></label><s:if test="%{flag}">
								<font color="red">*</font>
							</s:if><s:elseif test="%{editFlag}">
								<font color="red">*</font>
							</s:elseif><s:elseif test="%{dblFlag}">
								<font color="red">*</font>
							</s:elseif> :</td>
			<td colspan="2" width="50%"><s:hidden name="dashletId" />
							<s:if test="%{pageFlag}">
							<s:textfield size="25" name="name" theme="simple" 
									maxlength="21" readonly="true" />
							</s:if> <s:if test="%{onLoadFlag}">
								<s:textfield name="name" theme="simple" size="25"
									maxlength="21" readonly="true" />
							</s:if> <s:elseif test="%{flag}">
								<s:textfield name="name" theme="simple" size="25"
									maxlength="21" />
							</s:elseif><s:elseif test="%{saveFlag}">
								<s:label name="name" theme="simple">

								</s:label>

							</s:elseif><s:elseif test="%{editFlag}">
								<s:textfield name="name" theme="simple" size="25"
									maxlength="21" />
							</s:elseif> <s:elseif test="%{dblFlag}">
								<s:textfield name="name" theme="simple" size="25"
									maxlength="21" />
							</s:elseif>
			</td>
		</tr>

		<tr>
			
			<td width="15%" colspan="1" height="22"><label name="dashlet.link" id="dashlet.link" ondblclick="callShowDiv(this);"><%=label.get("dashlet.link") %></label><s:if test="%{flag}">
								<font color="red">*</font>
							</s:if><s:elseif test="%{editFlag}">
								<font color="red">*</font>
							</s:elseif><s:elseif test="%{dblFlag}">
								<font color="red">*</font>
							</s:elseif> :</td>
			<td colspan="2" width="50%"><s:if test="%{pageFlag}">
							<s:textfield size="25" name="link" theme="simple" readonly="true" />
							</s:if> <s:if test="%{onLoadFlag}">
								<s:textfield name="link" theme="simple" size="25"
									 readonly="true" />
							</s:if> <s:elseif test="%{flag}">
								<s:textfield name="link" theme="simple" size="25"
									 maxlength="95"/>
							</s:elseif><s:elseif test="%{saveFlag}">
								<s:label name="link" theme="simple">

								</s:label>

							</s:elseif><s:elseif test="%{editFlag}">
								<s:textfield name="link" theme="simple" size="25"
									 maxlength="95"/>
							</s:elseif> <s:elseif test="%{dblFlag}">
								<s:textfield name="link" theme="simple" size="25"
									 maxlength="95"/>
							</s:elseif>
				
			
			</td>
		</tr>
		
		<tr>
		
			<td width="15%" colspan="1" height="22"><label name="dashlet.position" id="dashlet.position" ondblclick="callShowDiv(this);"><%=label.get("dashlet.position") %></label><s:if test="%{flag}">
								<font color="red">*</font>
							</s:if><s:elseif test="%{editFlag}">
								<font color="red">*</font>
							</s:elseif><s:elseif test="%{dblFlag}">
								<font color="red">*</font>
							</s:elseif> :</td>
			<td colspan="2" width="50%" height="22"><s:if test="%{pageFlag}">
							<s:select name="position"
				list="#{'F':'Fixed','M':'Movable'}" disabled="true" />
							</s:if> <s:if test="%{onLoadFlag}">
								<s:select name="position"
				list="#{'F':'Fixed','M':'Movable'}" disabled="true" />
							</s:if> <s:elseif test="%{flag}">
								<s:select name="position"
				list="#{'F':'Fixed','M':'Movable'}" />
							</s:elseif><s:elseif test="%{saveFlag}">
								<s:label name="position" theme="simple">

								</s:label>

							</s:elseif><s:elseif test="%{editFlag}">
								<s:select name="position"
				list="#{'F':'Fixed','M':'Movable'}" />
							</s:elseif> <s:elseif test="%{dblFlag}">
								<s:select name="position"
				list="#{'F':'Fixed','M':'Movable'}" />
							</s:elseif>
			</td>
		</tr>
		
		<tr>
			
			<td width="15%" colspan="1" height="22"><label name="dashlet.type" id="dashlet.type" ondblclick="callShowDiv(this);"><%=label.get("dashlet.type") %></label><s:if test="%{flag}">
								<font color="red">*</font>
							</s:if><s:elseif test="%{editFlag}">
								<font color="red">*</font>
							</s:elseif><s:elseif test="%{dblFlag}">
								<font color="red">*</font>
							</s:elseif> :</td>
			<td colspan="2" width="50%"><s:if test="%{pageFlag}">
							<s:select name="type"
				list="#{'L':'List','G':'Graph','O':'Others'}"  disabled="true" />
							</s:if> <s:if test="%{onLoadFlag}">
								<s:select name="type"
				list="#{'L':'List','G':'Graph','O':'Others'}"  disabled="true" />
							</s:if> <s:elseif test="%{flag}">
								<s:select name="type"
				list="#{'L':'List','G':'Graph','O':'Others'}" />
							</s:elseif><s:elseif test="%{saveFlag}">
								<s:label name="type" theme="simple">

								</s:label>

							</s:elseif><s:elseif test="%{editFlag}">
								<s:select name="type"
				list="#{'L':'List','G':'Graph','O':'Others'}" />
							</s:elseif> <s:elseif test="%{dblFlag}">
								<s:select name="type"
				list="#{'L':'List','G':'Graph','O':'Others'}" />
							</s:elseif>
			
			</td>
		</tr>
		
		<tr>
		<td width="15%" colspan="1" height="22">
		<label name="dashlet.image" id="dashlet.image" ondblclick="callShowDiv(this);">
		<%=label.get("dashlet.image") %></label><font color="red">*</font>
		</td>
		<td><s:textfield name="dashletImageName"></s:textfield> </td>
		</tr>
		
		</table>
		</td>
		</tr>
		
		</table>
	</td>
	</tr>
		<tr>
			<td colspan="3">

			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="75%">
						<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp"/>
					</td>
				</tr>
								
				<tr><%
 	int total1 = (Integer) request.getAttribute("abc");
 	int PageNo1 = (Integer) request.getAttribute("xyz");
 %>
			<%if(total1 >0){ %>		
		<td align="right" colspan="2"><b>Page:</b>  <%
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
					</select></td>&nbsp;
					<%} %>
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
				class="formbg">

				<tr>
				<td class="formtext">
				<table width="100%" border="0" cellpadding="1" cellspacing="1"
					class="sortable">
						<tr>
							<td height="15" class="formhead" nowrap="nowrap"><strong
								class="forminnerhead">Dashlet List</strong></td>
						</tr>
						<tr class="td_bottom_border">
							<s:hidden name="myPage" id="myPage" />
							<td class="formth" align="center"><label name="sr.no" id="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no") %></label></td>
							<td class="formth" align="center"><label name="dashlet.name" id="dashlet.name1" ondblclick="callShowDiv(this);"><%=label.get("dashlet.name") %></label></td>							
							<td class="formth" align="center"><label name="dashlet.link" id="dashlet.link1" ondblclick="callShowDiv(this);"><%=label.get("dashlet.link") %></label></td>
							<td class="formth" align="center"><label name="dashlet.position" id="dashlet.position1" ondblclick="callShowDiv(this);"><%=label.get("dashlet.position") %></label></td>
							<td class="formth" align="center"><label name="dashlet.type" id="dashlet.type1" ondblclick="callShowDiv(this);"><%=label.get("dashlet.type") %></label></td>
							
							<td class="formth" align="center"><label name="dashlet.image" id="dashlet.image1" ondblclick="callShowDiv(this);"><%=label.get("dashlet.image") %></label></td>
							
							<td align="right" class="formth" nowrap="nowrap"><s:submit
								cssClass="delete" theme="simple" value=" Delete"
								onclick=" return chkDelete();" /></td>
							<%int count=0; %>
							<%!int d=0; %>
							<%
							int i = 0;
							int cn= PageNo1*20-20;
								%>
							<s:iterator value="typeList">

								<tr class="sortableTD"
								<%if(count%2==0){
									%>
									 class="tableCell1" <%}else{%>
									class="tableCell2" <%	}count++; %>
									onmouseover="javascript:newRowColor(this);"
									title="Double click for edit"
									onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
									ondblclick="javascript:callForEdit('<s:property  value="dashletId"/>');">


									<td width="10%" align="left" class="sortableTD"><%=++cn%><%++i;%>
									</td>


									<td width="25%" align="left" class="sortableTD"><s:hidden
										value="%{dashletId}"></s:hidden> <input type="hidden"
										name="hdeleteCode" id="hdeleteCode<%=i%>" /> <s:property
										value="name" /></td>									
									<td width="25%" align="left" class="sortableTD"><s:property
										value="link" /></td>
									<input type="hidden" name="hdeleteCode" id="hdeleteCode<%=i%>" />
									<td width="20%" align="left" class="sortableTD"><input type="hidden"
										name="hdeleteCode" id="hdeleteCode<%=i%>" /> <s:property
										value="position" /></td>
									<td width="20%" align="left" class="sortableTD"><input type="hidden"
										name="hdeleteCode" id="hdeleteCode<%=i%>" /> <s:property
										value="type" /></td>
										
											<td width="20%" align="left" class="sortableTD"><input type="hidden"
										name="dashletImageNameItt" id="dashletImageNameItt<%=i%>" /> <s:property
										value="dashletImageNameItt" /></td>
									
									<td align="center" nowrap="nowrap" width="10%" class="sortableTD"><input type="checkbox"
										class="checkbox" id="confChk<%=i%>" name="confChk"
										onclick="callForDelete1('<s:property  value="dashletId"/>','<%=i%>')" /></td>
								</tr>

							</s:iterator>
							<%
							d = i;
							%>
						</tr>


					</table>

					<%
						} catch (Exception e) {
						}
					%>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	
	
	
		
	</table>
</s:form>

<script>

	var f9Fields= ["paraFrm_name","paraFrm_link"];
	var fieldName = ["paraFrm_name","paraFrm_link","paraFrm_position","paraFrm_type"];
	var labelName = ["dashlet.name","dashlet.link","dashlet.position","dashlet.type"];
	var type = ['enter','enter','select','select'];

	//For Addnew Button 

	function addnewFun()
	{
		document.getElementById('paraFrm').action="DashletSettings_addNew.action";
		document.getElementById('paraFrm').submit();
	}

	// For Save Button

	function saveFun()
	{
		if(!validateBlank(fieldName, labelName, type))
	    return false;
	    if(!f9specialchars(f9Fields))
		return false;
		document.getElementById('paraFrm').action="DashletSettings_save.action";
		document.getElementById('paraFrm').submit();
		return true;
	}
	
	//For F9Window

	function searchFun()
	{
		callsF9(500,300,"DashletSettings_f9Action.action");
	}
	
	//For Cancel Button

	function cancelFun()
	{
		if(document.getElementById('paraFrm_loadFlag').value=="true")
		{
			//alert("in first");
			document.getElementById('paraFrm').action="DashletSettings_cancelFirst.action";
			document.getElementById('paraFrm').submit();
		}
		
		else if(document.getElementById('paraFrm_addFlag').value=="true")
		{	
			//alert("in sec");
			document.getElementById('paraFrm').action="DashletSettings_cancelSec.action";	
			document.getElementById('paraFrm').submit();
		}
		else if(document.getElementById('paraFrm_modFlag').value=="true")
		{	
			//alert("inside mod");
			document.getElementById('paraFrm').action="DashletSettings_cancelThrd.action";
			document.getElementById('paraFrm').submit();
		}
		else
		{	
			document.getElementById('paraFrm').action="DashletSettings_cancelFrth.action";
			document.getElementById('paraFrm').submit();
		}
	}
	
	//For Edit Button

	function editFun()
	{
		document.getElementById('paraFrm').action="DashletSettings_edit.action";
		document.getElementById('paraFrm').submit();
	}
	
	//For Delete Button

	function deleteFun()
	{
		var con=confirm('<%=label.get("confirm.delete")%>')
		if(con){
			var del="DashletSettings_delete.action";
			document.getElementById('paraFrm').action=del;
			document.getElementById('paraFrm').submit();
		} else{
		     return false;
		}
	}
	
	function callDelete(){
		if(document.getElementById('paraFrm_typeName').value==""){
			alert("Please select the record.");
			return false;
		}
	 
		var con=confirm('<%=label.get("confirm.delete")%>');
		if(con){
		   document.getElementById('paraFrm').action="DashletSettings_delete.action";
		   document.getElementById('paraFrm').submit();
		} else{
		   return false;
		}
	
	}
	

	function callPage(id){
	   	document.getElementById('myPage').value=id;
		document.getElementById('paraFrm_show').value=id;
	    document.getElementById('paraFrm').action="DashletSettings_callPage2.action";
	   	document.getElementById('paraFrm').submit();
	}

	function next(){
   		var pageno=	document.getElementById('myPage').value;
   		if(pageno=="1"){
	   		document.getElementById('myPage').value=2;
		    document.getElementById('paraFrm_show').value=2;
    	} else{
			 document.getElementById('myPage').value=eval(pageno)+1;
			 document.getElementById('paraFrm_show').value=eval(pageno)+1;
	    }
	   document.getElementById('paraFrm').action="DashletSettings_callPage1.action";
	   document.getElementById('paraFrm').submit();
	}	
   
	function on(){
		var val= document.getElementById('selectname').value;
		document.getElementById('paraFrm_show').value=val;
		document.getElementById('myPage').value=eval(val);
		document.getElementById('selectname').value=val;
		document.getElementById('paraFrm').action="DashletSettings_callPage1.action";
		document.getElementById('paraFrm').submit();
	}
   
   
	function newRowColor(cell){
		cell.className='onOverCell';
	}
	
	function oldRowColor(cell,val) {
		if(val=='1'){
	 		cell.className='tableCell2';
		}else {
			cell.className='tableCell1';
		}
	}
	
	function callForEdit(id){
	  	document.getElementById('paraFrm_hiddencode').value=id;
	  	//alert(id);
		document.getElementById("paraFrm").action="DashletSettings_calforedit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
	}
   
   
	pgshow();

	function pgshow(){
		var pgno=document.getElementById('paraFrm_show').value;
		if(!(pgno==""))
  			document.getElementById('selectname').value=pgno;
  	}
	  	
	function callForDelete1(id,i){
		if(document.getElementById('confChk'+i).checked == true){	  
		document.getElementById('hdeleteCode'+i).value=id;
	}else
	    document.getElementById('hdeleteCode'+i).value="";
	}
  	
	function chkDelete(){
		var flag='<%=d %>';
		if(chk()){
			 var con=confirm('<%= label.get("confirm.delete")%>');
			 if(con){
		  		document.getElementById('paraFrm').action="DashletSettings_delete1.action";
		  		document.getElementById('paraFrm').submit();
		 	 }else
		     	return false;
		} else {
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
	
	
	function previous(){
	   	var pageno=	document.getElementById('myPage').value;
	   	document.getElementById('myPage').value=eval(pageno)-1;
		document.getElementById('paraFrm_show').value=eval(pageno)-1;
		document.getElementById('paraFrm').action="DashletSettings_callPage1.action";
		document.getElementById('paraFrm').submit();
	}   
	
	/*
	//For Report Button
	function reportFun()
	{
		document.getElementById('paraFrm').action="DashletSettings_report.action";
		document.getElementById('paraFrm').submit();
	}

	function saveValidation(){	
		var val=document.getElementById('paraFrm_typeName').value;
		if(val==""){
			alert('Please enter RoomType Name');
			return false;
		}
		var value = LTrim(RTrim(val));
		if(val!=value){
			alert('Space not Allowed in RoomType Name!');
			return false;
		}
		return true;
	}  

	
	function updateValidation(){   
		var val=document.getElementById('paraFrm_typeName').value;
		if(val==""){
			  alert('Please enter RoomType Name!');
			  return false;
		}
	  
	   	var value = LTrim(RTrim(val));
		if(val!=value){
			alert('Space not Allowed in RoomType Name!');
			 return false;
		}
		
	   	return true;
	}*/

</script>