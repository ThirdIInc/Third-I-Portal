<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="SendMail" validate="true" id="paraFrm" validate="true"
	theme="simple">
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="right" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					
					<%!String code = "";%>
					<%
					code = (String) request.getAttribute("code");
					%>


					<td width="93%" class="txt"><strong class="formhead">
					<%
					if (code.equals("departmentCode")) {
					%> <label class="set" name="department" id="department"><%=label.get("department")%></label>

					<%
					} else if (code.equals("divCode")) {
					%> <label class="set" name="division" id="division"><%=label.get("division")%></label>

					<%
					} else if (code.equals("branchCode")) {
					%> <label class="set" name="branch" id="branch"><%=label.get("branch")%></label>

					<%
					}
					%> List </strong></td>
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
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="80%" class="formth">
					<%
					if (code.equals("departmentCode")) {
					%> <label class="set" name="department" id="department"><%=label.get("department")%></label>

					<%
					} else if (code.equals("divCode")) {
					%> <label class="set" name="division" id="division"><%=label.get("division")%></label>


					<%
					} else if (code.equals("branchCode")) {
					%> <label class="set" name="branch" id="branch"><%=label.get("branch")%></label>


					<%
					}
					%> Name</td>
					<td width="20%" class="formth">Select</td>
					<s:hidden name="frmId" id="frmId" />
					<s:hidden name="hiddenfrmId" id="hiddenfrmId" />


				</tr>

				<%
				int y = 1;
				%>
				<%!int z = 0;%>
				<s:iterator value="list">
					<tr>
						<td class="td_bottom_border" width="80%"><input type="hidden"
							name="departmentCode" id="departmentCode<%=y%>"
							value='<s:property value="departmentCode"/>' /> <input
							type="hidden" value='<s:property value="departmentName"/>'
							id="departmentName<%=y%>" /> <s:property value="departmentName" />
						</td>
						<td class="td_bottom_border" width="10%"><input type="checkbox"
							name="check" id="check<%=y%>" value='<s:property value="check"/>' /></td>
					</tr>
					<%
					y++;
					%>
				</s:iterator>
				<%
				z = y;
				%>
			</table>
			</td>
		</tr>

		<tr>
			<td align="center" width="25%" colspan="1"><input type="button"
				class="add" name="Ok" value=" OK " onclick="calculate();" />
		</tr>
	</table>

</s:form>
<script>

var code = '<%=code%>'

function calculate(){
	 var value='<%=z%>'; 
	 var id = '';
	 var id1='';
	 var chkBox;
	 
	 try{
   		for(var i=1;i<value;i++){
	   		chkBox = document.getElementById('check'+i).checked;
	   		if(chkBox == true){
   				id += document.getElementById('departmentCode'+i).value+",";
   			}
  		 }
  		 id = id.substring(0,id.length-1);
  		 
  		 opener.document.getElementById('paraFrm_'+code).value=id;
	}
	catch(e){
   		alert(e);
   	}
	window.close();
}
function setInitialValue(){
	 var count  = '<%=z%>'; 
     var oldvalue = opener.document.getElementById('paraFrm_'+code).value;
     fieldList  = oldvalue.split(",");
     
     for(k = 1; k <= count; k++){
     	for(i=0; i < fieldList.length;i++) {
      		if(document.getElementById('departmentCode'+k).value == fieldList[i]){
      			document.getElementById('check'+k).checked = true;
     		 }
  		}
     }
}
setInitialValue();

</script>