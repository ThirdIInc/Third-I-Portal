<%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="OnlineTest" validate="true" id="paraFrm" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0"><!-- Final Table -->
		<tr>
			<td width="100%">
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg"><!-- Header -->
					<tr>
						<td valign="bottom" class="txt"><strong class="formhead"><img
							src="../pages/images/recruitment/review_shared.gif" width="25"
							height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="formhead">Online Test</strong></td>
						<td width="3%" valign="top" class="txt">
						<div align="right"><img
							src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
						</td>
					</tr>
				</table><!-- Header -->
			</td>
		</tr>
		<tr>
						<td colspan="5" valign="bottom" class="txt"><img
							src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
					</tr>
		<tr><!--Schedule interview-->
			<td colspan="5">
				<table width="100%" border="0"   cellpadding="0" cellspacing="0" class="formbg"><!--Table 2-->
					<tr>
						<td width="100%"></td>
					</tr>
					<tr>
						<td width="100%"><strong>Test Detail</strong></td>
					</tr>
					<tr>
						<td width="100%"></td>
					</tr>
					
					<tr>
						<td>
							
							<table width="100%" border="0"  align="center" cellpadding="0" cellspacing="2"><!--Table header-->
								<tr>
									<td width="20%" >Test Name :</td>
									<td width="25%"><s:hidden name="testTemplateCode" /><s:property value="tempName"/>
									<s:hidden name="tempName"/>
										</td>
									<td width="25%" >Test Duration :</td>
									<td width="25%"><s:property value="tempDuration"/><s:hidden name="tempDuration" />
									</td>
								</tr>
								<tr>
									<td width="20%" >Total Marks :</td>
									<td width="25%"><s:hidden name="tempTotalMarks" /><s:property value="tempTotalMarks"/>
									</td>
									<td width="20%" >Total Questions :</td>
									<td width="25%"><s:property value="tempTotalQues"/><s:hidden name="tempTotalQues"/></td>	
									
								</tr>
								
								<tr>
									<td width="19%" colspan="1" >Test Instructions :</td>
									<td width="81%" colspan="3"><s:property value="tempInstruction" />
										<s:hidden name="tempInstruction"/>
										
									</td>
									<td width="40%">
									
									</td>
								</tr>
						
							</table><!--Table header-->
						</td>
					</tr>		
					<tr>
						<td align="center" >
						<input type="button" class="token"  theme="simple"
								value="Start Test" onclick="return confirmMsg()"  />
							
							<!--
								<s:submit cssClass="token" action="OnlineTest_startTest" theme="simple"
								value="Start Test" onclick="return confirmMsg()"/>
							-->
							<s:submit cssClass="token" action="OnlineTest_cancel" theme="simple"
								value="Cancel"   />	</td>
					</tr>
				</table><!--Table 2-->
			</td>
		</tr><!--Schedule interview-->					
	</table><!-- Final Table -->
	<s:hidden name="testType"/>
	<s:hidden name="equalweightage"/>
	<s:hidden name="marksWrongAns"/>
	<s:hidden name="marksNoAns"/>
	<s:hidden name="onlineScore"/>
	<s:hidden name="passingMarks"/>
	<s:hidden name="marksForCorrect"/>
	<s:hidden name="marksHard"/>
	<s:hidden name="marksMedium"/>
	<s:hidden name="marksEasy"/>
	
	<s:hidden name="onlineTestCode" />
	<s:hidden name="wrongmarksHard"/>
	<s:hidden name="wrongmarksEasy"/>
	<s:hidden name="wrongmarksMedium"/>
	<s:hidden name="equalMarksForAll"/>
	<s:hidden name="templateCode"/>	
	<s:hidden name="reqCode"/>
	<s:hidden name="reqName"/>		 
	
	<s:iterator value="randomQueList">
		<s:hidden name="randomQuesCodes" />
	</s:iterator>
</s:form>


<script>
	function confirmMsg(){
	//alert('fdgdfg');
		var con = confirm("Are you sure to start the exam ?");
		if (con) {
			document.getElementById('paraFrm').target='_parent';
			document.getElementById('paraFrm').action='OnlineTest_startTest.action';
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