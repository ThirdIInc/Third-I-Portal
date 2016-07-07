<%@ taglib prefix="s" uri="/struts-tags"%>




<s:form action="TemplateAction" theme="simple" name="paraFrm"
	id="paraFrm">

	<table width="100%" border="0">
		<tr>
			<td>

			<table width="100%" border="0">
				<tr>
					<td width="20%" valign="bottom" class="txt" align="left"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" /></strong> <strong
						class="formhead">Template Report </strong></td>

					<td colspan="2" width="20%" align="right">
					<div align="right"><font color="red">*</font></span> Indicates
					Required</div>
					</td>

				</tr>

			</table>
			</td>
		</tr>


		<tr>
			<td colspan="4" width="100%" valign="bottom" class="txt"><img
				src="../pages/common/css/default/images/space.gif" height="5" /></td>
		</tr>



		<tr>
			<td colspan="4" width="100%" valign="bottom" class="txt"><img
				src="../pages/common/css/default/images/space.gif" height="5" /></td>
		</tr>



		<tr>
			<td colspan="4" width="100%" valign="bottom" class="txt"><img
				src="../pages/common/css/default/images/space.gif" height="5" /></td>
		</tr>



		<tr>


			<td colspan="3">
			<table width="80%">
				<tr>

					<td width="10%%" colspan="1">Select Template Name <font
						color="red">*</font>:</td>
					<td width="30%" colspan="2"><s:textfield theme="simple"
						name="tempName" /> <img src="../pages/images/search.gif"
						class="iconImage" height="18" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'TemplateAction_f9Action.action');">
				</tr>
				<s:hidden theme="simple" name="tempId" />
				</td>
				<s:hidden name="queryId" />

				<%!int d = 0;%>
				<%
						int i = 0;
						%>

				<s:iterator value="paraList">

					<tr>

						<td><s:label name="paraName" id="paraName<%=i%>"
							value="%{paraName}" /><font color="red">*</font>:</td>
						<td><input type="text" name="paraValue" id="paraValue<%=i%>" />
						<%
 i++;
 %>
						</td>

					</tr>


				</s:iterator>
				<%
						d = i;
						%>

				<tr>
					<td width="15%" colspan="1"></td>

					<td width="30%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:submit
						cssClass="pagebutton" value="Report"
						action="TemplateAction_ReportGenerate"
						onclick="return callReport();" /></td>

				</tr>




				<!--    <input type="hidden" id="hdMax" value="<%=d%>">-->





			</table>

			</td>
		</tr>

	</table>
</s:form>

<script>
function callReport()
{
var tempNm=document.getElementById('tempName').value;
var paraVal;
var flag='<%=d %>';	 

if(tempNm=="")
{
alert("Please Select the Template Name");
return false;
}
else
{

for(var a=0;a<=flag;a++){	
	paraVal=document.getElementById('paraValue'+a).value;
	   if(paraVal=="")
	   {
	   alert("Please Enter the required fields");
	   return false;
	   }	
	  }	
	return true;
 }


}
  		
  	</script>
