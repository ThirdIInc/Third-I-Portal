<%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="OnlineTest" validate="true" id="paraFrm" theme="simple">
<s:hidden name="resultFlag"/>
<s:hidden name="onlineTestCode" />	
<s:hidden name="reqCode"/>
<s:if test="resultFlag">
<strong> Congratulations!!!!You have successfully completed Your Test</strong>
</s:if>
<s:else> <strong> Sorry !! You have failed the test</strong></s:else>
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0"><!-- Final Table -->
	<s:if test="chkOnlineScoreFlag">
		<tr><!--Schedule interview-->
			<td colspan="5">
				<table width="100%" border="0"   cellpadding="0" cellspacing="0" class="formbg"><!--Table 2-->
					<tr>
						<td width="100%"></td>
					</tr>
					<tr>
						<td width="100%"><strong>Test Paper</strong></td>
					</tr>
					<tr>
						<td width="100%"></td>
					</tr>
					
					<tr>
						<td>
							
							<table width="100%" border="0"  align="center" cellpadding="0" cellspacing="2"><!--Table header-->
								
								<tr>
									<td width="20%" bgcolor="#F2F2F2">Total Marks :</td>
									<td width="25%"><s:hidden name="tempTotalMarks" /><s:property value="tempTotalMarks"/>
									</td>
									<td width="25%" bgcolor="#F2F2F2">Passing Marks :</td>
									<td width="25%"><s:property value="passingMarks"/>
									<s:hidden name="passingMarks"/>
									</td>
								</tr>
								<tr>
									<td width="20%" bgcolor="#F2F2F2">Marks For Correct Answer :</td>
									<td width="25%"><s:hidden name="correctMarks" /><s:property value="correctMarks"/>
									</td>
									<td width="25%" bgcolor="#F2F2F2">Marks For Wrong Answer :</td>
									<td width="25%"><s:property value="negativeMarks"/>
									<s:hidden name="negativeMarks"/>
									</td>
								</tr>
								<tr>
									<td width="20%" bgcolor="#F2F2F2">Marks For No Answer :</td>
									<td width="25%"><s:hidden name="blankAnswer" /><s:property value="blankAnswer"/>
									</td>
									
								</tr>
								<tr>
									<td width="20%" bgcolor="#F2F2F2">Marks Obtained :</td>
									<td width="25%"><s:hidden name="marksObtained" /><s:property value="marksObtained"/>
									</td>
									<td width="25%" bgcolor="#F2F2F2">Percentage :</td>
									<td width="25%"><s:property value="percentage"/>
									<s:hidden name="percentage"/>
									</td>
								</tr>
								<tr>
									<td width="20%" bgcolor="#F2F2F2"><STRONG>Result :</STRONG></td>
									<td width="25%"><s:hidden name="result" /><FONT color="red"><s:property value="result"/></FONT>
									</td>
									
								</tr>
							</table><!--Table header-->
						</td>
					</tr>		
					
				</table><!--Table 2-->
			</td>
		</tr><!--Schedule interview-->	
	</s:if>
	<s:else>
		<tr>
			<td>
				<table width="100%" border="0"   cellpadding="0" cellspacing="0" class="formbg"><!--Table 3-->
					<tr align="center">
						<td colspan="4">
							<strong>HR will get back to you soon.</strong>
						</td>
					</tr>
				</table><!--End of Table 3-->
			</td>
		</tr>
	</s:else>
	<tr>
		<td>
			<table width="100%" border="0"  align="center" cellpadding="0" cellspacing="2"><!--Table button-->
				<tr>
					<td colspan="4" align="center">
						<input type="button" value="Go Back To Main Screen" onclick="backMenu()" class="token"/>
					</td>
				</tr>
			</table>
		</td>
	</tr>		
	</table><!-- Final Table -->
	<s:hidden name="reqCode"></s:hidden>
</s:form>

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

function backMenu(){
	document.getElementById('paraFrm').action='OnlineTest_backMenu.action';
 	document.getElementById('paraFrm').submit();
} //end of method

</script> 