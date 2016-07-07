<%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="OnlineTest" validate="true" id="paraFrm" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg"><!-- Final Table -->
		<tr>
			<td width="100%">
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg"><!-- Header -->
					<tr>
						<td valign="bottom" class="txt"><strong class="formhead"><img
							src="../pages/images/recruitment/review_shared.gif" width="25"
							height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="formhead">Online Test Details</strong></td>
						<td width="3%" valign="top" class="txt">
						<div align="right"><img
							src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
						</td>
					</tr>
				</table><!-- Header -->
			</td>
		</tr>
		<tr><!--welcome -->
			<td colspan="5">
				<table width="100%" border="0"   cellpadding="0" cellspacing="0"><!--Table 2-->
					<tr>
						<td colspan="5">
							<div align="right"><font color="red">*</font> Indicates
							Required</div>
							</td>
					</tr>
				</table>
			</td>
		</tr>		
	
	<!-- 
		<tr> 
			<td colspan="5">
				<table width="100%" border="0"   cellpadding="1" cellspacing="1" class="formbg"> 
					<tr>
						<td width="100%"><strong>Welcome To Glodyne Technoserve Online Test</strong></td>
					</tr>
					<tr>
						<td colspan="5" valign="bottom" class="txt"><img
							src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
					</tr>
					<tr>
						<td align="center" >Enter Test Template Name :<font color="red">*</font>&nbsp;<s:textfield name="tempName" size="20"/></td>
					</tr>
					<tr>
						<td colspan="5" valign="bottom" class="txt"><img
							src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
					</tr>
					<tr>
						<td align="center" >Enter Requisition Name :<font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;<s:textfield name="reqName" size="20"/></td>
					</tr>
					<tr>
						<td colspan="5" valign="bottom" class="txt"><img
							src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
					</tr>
					<tr>
						<td align="center" ><input type="button" class="token"  theme="simple"
								value="Continue" onclick="return loginContinue()"  />	</td>
					</tr>
				</table> 
			</td>
		</tr>		
	 -->	
		<tr> 
			<td colspan="5">
				<table width="100%" border="0"   cellpadding="1" cellspacing="1" class="formbg"><!--Table 2-->
					 <tr>
					 	<td class="formth" width="10%" align="center">
					 		<b><label class="set" name="sr.no" id="sr.no"
								ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></b>
						</td>

						<td class="formth" width="20%" align="center"><b><label
							class="set" name="jobCode" id="jobCode"
							ondblclick="callShowDiv(this);"><%=label.get("jobCode")%></label></b>
						</td>

						<td class="formth" width="25%" align="center"><b><label class="set"
							name="testTemplate" id="testTemplate" ondblclick="callShowDiv(this);"><%=label.get("testTemplate")%></label></b>	
						</td>

						<td class="formth" width="15%" align="center"><b><label
							class="set" name="testDate" id="testDate"
							ondblclick="callShowDiv(this);"><%=label.get("testDate")%></label></b>
						</td>
						
						<td class="formth" width="15%" align="center" nowrap="nowrap"><b><label
							class="set" name="testTime" id="testTime"
							ondblclick="callShowDiv(this);"><%=label.get("testTime")%></label></b>
						</td>	
							
						<td class="formth" width="15%" align="center">
							<b><label class="set" name="action" id="action"
							ondblclick="callShowDiv(this);"><%=label.get("action")%></label></b>
						</td>
					 </tr>
					 
				 <s:if test="scheduledDataFlag">
				 <%int count = 0; %>
				 <s:iterator value="scheduledTestList">
					 <tr>
					 	<td class="sortabletd" align="center" width="10%">
					 		<%= ++count %>
					 	</td>	
					 	
					 	<td class="sortabletd"  width="20%">
					 		<s:property value="requisitionTitleItr"/>
					 		<s:hidden name="requisitionCodeItr" />
					 	</td>	
					 	
					 	<td class="sortabletd" width="25%">
					 		<s:property value="testTemplateNameItr"/>
					 		<s:hidden name="testTemplateCodeItr" />
					 	</td>	
					 	
					 	<td class="sortabletd" width="15%" align="center">
					 		<s:property value="testDateItr"/>
					 	</td>
					 		
					 	<td class="sortabletd"  width="15%" align="center">
					 		<s:property value="testTimeItr"/>
					 		<s:hidden name="onlineTestCodeItr" />
					 	</td>	
					 	
					 	<td class="sortabletd"  width="15%" align="center">
					 		<input type="button" value="Start Test" onclick="return loginContinue('<s:property value="testDateItr"/>',
					 																			  '<s:property value="testTimeItr"/>',
					 																			  '<s:property value="testTemplateNameItr"/>',
					 																			  '<s:property value="requisitionCodeItr"/>', 
					 																			  '<s:property value="testTemplateCodeItr" />',
					 																			  '<s:property value="onlineTestCodeItr" />');" />
					 	</td>	
					 </tr>
				 </s:iterator>
				 </s:if>
				 <s:else>
				 	<tr>
				 		<td colspan="6" align="center">
				 			<font color="red">No data to display</font>
				 		</td>
				 	</tr>
				 </s:else>
				</table> 
			</td>
		</tr>		
		
	</table> 
</s:form>

<script>
function loginContinue(testDate, testTime, testTemplate, requisitionCode, templateCode, testCode){
	var con = confirm("Do your really want to start this test?");
	if(con) {
		document.getElementById('paraFrm').target='main';
		document.getElementById('paraFrm').action='OnlineTest_startTestValidation.action?testDate='+testDate+'&testTime='+testTime+'&testTemplate='+testTemplate+'&requisitionCode='+requisitionCode+'&templateCode='+templateCode+'&testCode='+testCode;
 		document.getElementById('paraFrm').submit();
	} else {
		return false;
	}	
}
</script>

<script language="JavaScript">
var message="Function Disabled!";

function clickIE4(){
if (event.button==2){
alert(message);
return false;
}
}
function clickNS4(e){
if (document.layers||document.getElementById&&!document.all){
if (e.which==2||e.which==3){
//alert(message);
return false;
}
}
}
if (document.layers){
document.captureEvents(Event.MOUSEDOWN);
document.onmousedown=clickNS4;
}
else if (document.all&&!document.getElementById){
document.onmousedown=clickIE4;
}
document.oncontextmenu=new Function("return false")
</script> 