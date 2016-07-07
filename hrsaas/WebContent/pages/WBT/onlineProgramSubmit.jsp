 <%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<%@include file="/pages/charts/FusionCharts.jsp" %>

<script type="text/JavaScript">
<!--
function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}
//-->
</script>

<%
String totalProgramMarks="0";
String totalMarksObtained="0";
String passingMarks="0";
String moduleCode ="";
String sectionCode ="";
try{
totalProgramMarks =(String)request.getAttribute("totalProgramMarks");
totalMarksObtained =(String)request.getAttribute("totalMarksObtained");
passingMarks =(String)request.getAttribute("passingMarks");
System.out.println("totalMarksObtained-"+totalMarksObtained);

moduleCode =(String)request.getAttribute("moduleCode");
sectionCode =(String)request.getAttribute("sectionCode");
}catch(Exception e){
	
	e.printStackTrace();
}
%>

<s:form action="OnlineProgram" validate="true" id="paraFrm" theme="simple">
<s:hidden name="resultFlag"/>


<tr>
      <td width="301" height="70" valign="middle"><img src="../pages/WBT/images/logo.gif" width="324" height="57" /></td>
      <td width="608" valign="bottom"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="25"><div align="right" class="apphead">Web Based Training Portal</div></td>
        </tr>
      </table></td>
    </tr>
    <tr>
      <td colspan="2" bgcolor="#EC7521" height="4px"></td>
    </tr>
    <tr>
      <td colspan="2">&nbsp;</td>
    </tr>
 

	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0"><!-- Final Table -->
	<s:if test="true">
		<tr><!--Schedule interview-->
			<td colspan="5">
				<table width="100%" border="0"   cellpadding="0" cellspacing="0" class="formbg"><!--Table 2-->
					<tr>
						<td width="100%"></td>
					</tr>
					<tr>
						<td width="100%"></td>
					</tr>
					<s:if test="resultFlag">
 <div align="center"> <strong>Congratulations!!! You have successfully completed test! </strong>  </div>
</s:if>
<s:else><div align="center"> <strong> Sorry!! You have failed the test.Please try again.</strong></div>
</s:else>
					<tr>
						<td width="100%">&nbsp;</td>
					</tr>
					<%String dataXml="<chart palette='2' formatNumberScale='1' lowerLimit='0' upperLimit='"+totalProgramMarks+"' caption='Result' subcaption='' chartTopMargin='15' chartRightMargin='20' roundRadius='5' colorRangeFillMix='{light-60}' colorRangeFillRatio='100'> "
					  +"  <colorRange> "
					  +" <color minValue='0' maxValue='"+passingMarks+"' /> "
					   +"</colorRange>  "
					  +" <value>"+totalMarksObtained+"</value> "
					  +" <target>"+passingMarks+"</target> "
					  +" </chart>"; 
					  dataXml =createChartHTML("../pages/charts/fusionCharts/HBullet.swf",    "", dataXml, "productSales", "500", 70, false);
					  
					  System.out.println(dataXml);
					  %>
					<tr>
						<td width="100%" align="center"><%=dataXml %></td>
					</tr>
					
					 <tr>
			<td  valign="bottom" class="txt"><img
				src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
		</tr>
					
					<tr>
						<td align="center" width="100%">
							
							<table width="60%" border="0"  align="center" cellpadding="0" cellspacing="2"><!--Table header-->
								<tr>
									<td width="20%" bgcolor="#F2F2F2" align="left"><STRONG>Result </STRONG></td>
									<td width="25%">
									<s:hidden name="result" />
									<s:if test="passColor">									
									<FONT color="green"><s:property value="result"/></FONT>
									</s:if>
									<s:else>
									<FONT color="red"><s:property value="result"/></FONT>
									</s:else>
									</td>
									
								</tr>
								<tr>
									<td width="20%" bgcolor="#F2F2F2" align="left">Total Marks </td>
									<td width="25%"><s:hidden name="tempTotalMarks" /><s:property value="tempTotalMarks"/>
									</td>
									<td width="25%" bgcolor="#F2F2F2" align="left">Passing Marks </td>
									<td width="25%"><s:property value="passingMarks"/>
									<s:hidden name="passingMarks"/>
									</td>
								</tr>
								<!--<tr>
									<td width="20%" bgcolor="#F2F2F2">Marks For Correct Answer :</td>
									<td width="25%"><s:hidden name="correctMarks" /><s:property value="correctMarks"/>
									</td>
									<td width="25%" bgcolor="#F2F2F2">Marks For Wrong Answer :</td>
									<td width="25%"><s:property value="negativeMarks"/>
									<s:hidden name="negativeMarks"/>
									</td>
								</tr>
								--><!--<tr>
									<td width="20%" bgcolor="#F2F2F2">Marks For No Answer :</td>
									<td width="25%"><s:hidden name="blankAnswer" /><s:property value="blankAnswer"/>
									</td>
									
								</tr>
								--><tr>
									<td width="20%" bgcolor="#F2F2F2" align="left">Marks Obtained </td>
									<td width="25%"><s:hidden name="marksObtained" /><s:property value="marksObtained"/>
									</td>
									<td width="25%" bgcolor="#F2F2F2" align="left">Percentage </td>
									<td width="25%"><s:property value="percentage"/>
									<s:hidden name="percentage"/>
									</td>
								</tr>
								
							</table><!--Table header-->
						</td>
					</tr>		
					
				</table><!--Table 2-->
			</td>
		</tr><!--Schedule interview-->	
	</s:if>
	<!--<s:else>
		<tr>
			<td>
				<table width="100%" border="0"   cellpadding="0" cellspacing="0" class="formbg">Table 3
					<tr align="center">
						<td colspan="4">
							<strong>HR will get back to you soon.</strong>
						</td>
					</tr>
				</table>End of Table 3
			</td>
		</tr>
	</s:else>
	-->
			 <tr>
			<td  valign="bottom" class="txt"><img
				src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
		</tr>
	<tr>
		<td>
			<table width="100%" border="0"  align="center" cellpadding="0" cellspacing="2"><!--Table button-->
				<tr>
					<td colspan="4" align="center">
						<input type="button" value="Back To Program" onclick="backMenu()" class="token"/>
						&nbsp;
						<% if(sectionCode.equals(null) || sectionCode==null ||sectionCode.equals("")){ %>
						<%}else{ %>
						<input type="button" value="Back To Module" onclick="backToModule()" class="token"/>
						<%} %>
					</td>
				</tr>
			</table>
		</td>
	</tr>		
	
	
	 <tr>
      <td colspan="2"><hr /></td>
    </tr>
   <tr>
      <td height="25" colspan="2"><div align="center" class="text">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><div align="left">&reg;All Rights Reserved &copy;Copyright</div></td>
            <td><div align="right"><a href="javascript:void(0);" class="link" onclick="MM_openBrWindow('http://192.168.100.34/TechPortallive/terms.html','','width=700,height=400')">Terms &amp; Conditions</a> | <a href="javascript:void(0);" class="link" onclick="MM_openBrWindow('http://192.168.100.34/TechPortallive/privacypolicy.html','','width=700,height=400')">Privacy Policy</a></div></td>
          </tr></table>
        <div align="right"></div><table width="100%" border="0" cellspacing="0" cellpadding="0">
        </table>
        </div></td>
    </tr>
	
	</table><!-- Final Table -->
	
	<s:hidden name="programCode" /> 
	 <s:hidden name="userCode" />
		<s:hidden name="applicationCode" />
		<s:hidden name="userType" />
	 <s:hidden name="attempt"/> 
	  
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
	document.getElementById('paraFrm').action='OnlineProgram_backToProgram.action';
 	document.getElementById('paraFrm').submit();
} //end of method

function backToModule(){
	var attemptCount = document.getElementById("paraFrm_attempt").value;
	var programCode =document.getElementById("paraFrm_programCode").value;
	//alert('attemptCount '+attemptCount +' programCode '+programCode);
	document.getElementById('paraFrm').action='OnlineProgram_callModule.action?moduleCodeStr='+ <%=moduleCode%>+'&programCodeStr='+programCode+'&sectionCodeStr=null&from=programPage&attemptCountStr='+attemptCount;
	//alert(document.getElementById('paraFrm').action);
 	document.getElementById('paraFrm').submit();
	
} //end of method

</script> 