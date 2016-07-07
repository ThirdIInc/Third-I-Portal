<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@page import="org.paradyne.lib.Utility"%>

<%
	String moduleContent = "";
	String moduleInstruction = "";

	String sectionContent = "";
	try {
		moduleContent = (String) request.getAttribute("moduleContent");
		moduleInstruction = (String) request
		.getAttribute("moduleInstruction");
		sectionContent = (String) request
		.getAttribute("sectionContent");

	} catch (Exception e) {
		e.printStackTrace();
	}
%>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>DECISIONONE A GLODYNE COMPANY</title>
<META http-equiv="Page-Enter" CONTENT="RevealTrans(Duration=4,Transition=undefined)">
<link href="images/skin.css" rel="stylesheet" type="text/css" />




<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-image: url();
	background-repeat: repeat-x;
	
}
-->
</style>
<script type="text/JavaScript">
<!--
function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}
//-->
</script>
</head>

<body>
<div id="windowDiv" onblur="self.focus()"></div>
 <div id="optionDiv"
	style='position: absolute; z-index: 3; display: none; border: 2px solid #EC7521; background-color: #C0C0C0; '>
	<table width="100%"  height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="99%"><iframe id="zoomFrame" src="../pages/common/loading.jsp"  scrolling="no" frameborder="0" width="100%" height="650px">
	 	</iframe></td>
    <td width="1%" valign="top"> <span style="text-align:right"><a href="#" onClick="closeDiv();">Close</a></span></td>
  </tr>
</table>
</div>


<s:form action="OnlineProgram" validate="true" id="paraFrm"
	theme="simple">
  <table width="909" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
    <tr>
      <td valign="middle"><img src="../pages/WBT/images/logo.gif" /></td>
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
      <td colspan="2"><table width="100%" height="320" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="895" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td colspan="5">
              	<table width="100%" border="0" cellpadding="0" cellspacing="0">
                	<tr valign="top">
						<td width="12%" class="appheader">Program Name   </td>
						<td width="80%" class="heading1">
						
							<s:property value="sectionProgramName"/> 
							<s:hidden name="sectionProgramCode" />
                  		</td>
                  		<td width="8%" align="right" style="text-align: right;">
                  		<s:if test="sectionDisplayFlag">
				               <img src="../pages/WBT/images/back.png" 
						          onclick="callAction('M');"
						          style="cursor: pointer; float: right;" title="Back"/>
             				</s:if>
				            <s:else>
								<img src="../pages/WBT/images/back.png" 
						          onclick="callAction('P');"
						          style="cursor: pointer; float: right;" title="Back"/>
				            </s:else>
				            Back
				            </td>
					</tr>
                  
					<tr>
						<td width="12%" class="appheader">Module Name  </td>
						<td width="80%" class="heading1">
							<s:property value="sectionModuleName" />
							<s:hidden name="sectionModuleCode" />
						</td>
						<td width="8%" align="right" style="text-align: right;">
						</td>
					</tr>
					<s:if test="sectionDisplayFlag">
					<tr>
						<td width="12%" class="appheader">Section Name  </td>
						<td width="80%" class="heading1"><s:property value="sectionName" />
							<s:hidden name="sectionCode" />
						</td>
							<td width="8%" align="right" style="text-align: right;">
						</td>
					</tr>
					</s:if>
				</table>
			</td>
           </tr>
           <tr>
               <td colspan="5"><hr /></td>
            </tr>
            <s:if test="%{showModuleDtlFlag}">
	            <tr>
	              <td class="apphead" colspan="5"><span class="text">
	              <%=Utility.checkNull(moduleInstruction)%>
	              </span></td>
	            </tr>
             <tr id="navigationTr"  >
				<td colspan="2">
					<img src="../pages/WBT/images/first.png" height="18px" width="20px"
						          onclick="first();" 
						          style="cursor: pointer; float: left;" title="First"/>
						&nbsp;
					<img src="../pages/WBT/images/prev.png" height="18px" width="20px"
						          onclick="previous();"
						          style="cursor: pointer; float: left;" title="Previous"/>
						          
				</td>
				<td class="apphead" align="center"><span class="text">
	              <%--  <%=Utility.checkNull(moduleContent)%> --%>
	              
	              <s:if test="%{contentList}">
	              		Content index :
						<s:select theme="simple" 
							list="%{contentList}" name="contentId" onchange="changeContent();" />
					</s:if>
	              </span>
	              <span id="indexInfo" ></span>
				</td>
				<td colspan="2" align="right">
					
					<img src="../pages/WBT/images/last.png" height="18px" width="20px"
						          onclick="last();"
						          style="cursor: pointer; float: right;" title="Last"/>
						          &nbsp;
					<img src="../pages/WBT/images/next.png" height="18px" width="20px"
						          onclick="next();"
						          style="cursor: pointer; float: right; " title="Next"/>	     
				</td>
            </tr>
             
            <tr id="navigationHr">
               <td colspan="5"><hr /></td>
            </tr>
            <!--<tr>
              <td class="heading" colspan="5" align="right">
              		<img src="../pages/WBT/images/zoom.png"
						          onclick="openZoom();"
						          style="cursor: pointer; float: right; " title="Zoom" id="zoomId"/>	
              </td>
            </tr>
            -->
            <s:if test="%{contentList}">
           	<tr id="contentTr" style="height: 500px;">
				<td colspan="5"><iframe id="contentIframe" src="" scrolling="no" frameborder="0" 
					height="630px;" width="1000px;"> </iframe></td>
			</tr>
            </s:if>
            <tr>
				<td colspan="5">
					<table width="100%" border="0" cellspacing="2" cellpadding="2">
						<s:if test="%{sectionList}">
							<tr>
								<td width="9%" height="27" bgcolor="#F2f2f2"><span class="text">Sr.No.</span></td>
								<td width="35%" bgcolor="#F2f2f2"><span class="text">Section Name </span></td>
								<td width="16%" bgcolor="#F2f2f2"><span class="text">Completion Status</span></td>
								<!--<td width="22%" bgcolor="#F2f2f2" class="text" align="center">Marks Obtained(%)  </td>-->
								<td width="22%" bgcolor="#F2f2f2" class="text" align="center">Attempts Remaining  </td>
								<td width="18%" bgcolor="#F2f2f2" class="text" align="center">Result</td>
							</tr>
						</s:if>
							<%
                                int count = 1;
							%>
						<s:iterator value="sectionList">
						<tr>
							<td class="text" align="center"><%=count++%></td>
							<td class="text">
								<table width="128" height="18" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td width="174" nowrap="nowrap">
											<s:property value="sectionNameItt"/>
										</td>
										<td width="26">
											<img src="../pages/WBT/images/play.png" width="16" height="16" 
												style="cursor: pointer;"  title="<s:property value="sectionNameItt"/>"
												onclick="return callSection('<s:property value="sectionCodeItt"/>','<s:property value="sectionCompletionStatusItt"/>','<s:property value="sectionAttemptRemaining"/>');"  />
										</td>
		                      		</tr>
		                    	</table>
							</td>
							<td class="text" align="center">  <s:property value="sectionCompletionStatusItt"/></td>
							<!--<td class="text" align="center"> <s:property value="sectionMarksItt"/> </td>-->
							<td class="text" align="center"> <s:property value="sectionAttemptRemaining"/> </td>
							<td class="text" align="center"> <s:property value="sectionResultItt"/></td>
						</tr>
						</s:iterator>
					</table>
				</td>
			</tr>
			</s:if>
			<s:else>
			
			<tr id="navigationTr">
				<td colspan="2"> 
						<img src="../pages/WBT/images/first.png" height="18px" width="20px"
						          onclick="first();"
						          style="cursor: pointer; float: left;" title="First"/>
						&nbsp;
						<img src="../pages/WBT/images/prev.png" height="18px" width="20px"
						          onclick="previous();"
						          style="cursor: pointer; float: left;" title="Previous"/>
				</td>
				<td class="apphead" align="center">
					<span class="text"> <%-- <%=Utility.checkNull(sectionContent)%> --%>
						<s:if test="%{contentList}">
						Content Index : <s:select theme="simple"  
											list="%{contentList}" name="contentId" onchange="changeContent();" />
						</s:if>
					</span>
					<span id="indexInfo"></span>
				</td>
				<td colspan="2" align="right">
					<img src="../pages/WBT/images/last.png" height="18px" width="20px"
						          onclick="last();"
						          style="cursor: pointer; float: right;" title="Last"/>
						          &nbsp;
					<img src="../pages/WBT/images/next.png" height="18px" width="20px"
						          onclick="next();"
						          style="cursor: pointer; float: right;" title="Next"/>
				</td>
			</tr>
			<tr id="navigationHr">
               <td colspan="5"><hr /></td>
            </tr>
            
			<!--<tr>
              <td class="heading" colspan="5" align="right">
              		<img src="../pages/WBT/images/zoom.png"
						          onclick="openZoom();"
						          style="cursor: pointer; float: right; " title="Zoom" id="zoomId"/>	
              </td>
            </tr>
            -->
            <s:if test="%{contentList}">
			<tr id="contentTr" style="height: 500px;">
				<td colspan="5"><iframe id="contentIframe" src=""  scrolling="no" frameborder="0" style="overflow: yes"
							height="630px" width="1000px"> </iframe>
				</td>
			</tr>
			</s:if>
			</s:else>
				
			<tr>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
			</tr>
		</table>
		</td>
		</tr>
		</table>
		</td>
        </tr>
		<s:if test="takeTestBtnFlag">
		<tr>
			<td colspan="2" align="center"><input type="button" value="TAKE THE TEST"
					id="testBtn" onclick="callTest();" />
			</td>
		</tr>
		</s:if> 
		<s:if test="%{finishBtnFlag}">
		<tr>
			<td colspan="2" align="center">
				<input type="button" value="Acknowledge & Finish" id="finishBtn" onclick="callFinish();" />
			</td>
		</tr>
		</s:if>
		</table>
    <tr>
      <td colspan="2">&nbsp;</td>
    </tr>
    <tr>
      <td colspan="2" height="4px"></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    
    <tr>
      <td colspan="2"><hr /></td>
    </tr>
    <tr>
      <td height="25" colspan="2"><div align="center" class="text">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><div align="left">&reg;All Rights Reserved &copy;Copyright</div></td>
            <td><div align="right"><a href="javascript:void(0);" class="link" onclick="MM_openBrWindow('http://tpstaging.decisionone.com/techportalgolive/terms.html','Terms','width=700,height=400,scrollbars=yes')">Terms &amp; Conditions</a> | <a href="javascript:void(0);" class="link" onclick="MM_openBrWindow('http://tpstaging.decisionone.com/techportalgolive/privacypolicy.html','Privacy','width=700,height=400,scrollbars=yes')">Privacy Policy</a></div></td>
          </tr></table>
        <div align="right"></div><table width="100%" border="0" cellspacing="0" cellpadding="0">
        </table>
        </div></td>
    </tr>
  </table>
  
  	<s:iterator value="randomQueList">
			<s:hidden name="randomQuesCodes" />
		</s:iterator>
<s:hidden name="isRandomQues" />
		<s:hidden name="source" />
		<s:hidden name="showModuleDtlFlag" /> 
		
		<s:hidden name="testStatusCheck" /> 
		
		<s:hidden name="noQuesAvailable"/>
		
		<s:hidden name="sectionDisplayFlag"/>
		
			 <s:hidden name="isModuleCompleted"/>
			 
			 		 <s:hidden name="userCode" />
		<s:hidden name="applicationCode" />
		<s:hidden name="userType" />
		
	 	   <s:hidden name="takeTestBtnFlag" />
 		   <s:hidden name="finishBtnFlag" /> 
 		  
 		   <s:hidden name="attempt"/> 
 		   
 		   <s:hidden name="remainingAttemptCount" />
</s:form>




</body>
</html>


<script>

function callAction(id)
{

var programCode =document.getElementById('paraFrm_sectionProgramCode').value;
	var moduleCode =document.getElementById('paraFrm_sectionModuleCode').value;
	var testStatus =document.getElementById('paraFrm_testStatusCheck').value;
	
	
	var userCode =document.getElementById('paraFrm_userCode').value;
	var applicationCode = document.getElementById('paraFrm_applicationCode').value;
	var userType = document.getElementById('paraFrm_userType').value;
	//alert(testStatus);
	if(id=='P')
	{
	document.getElementById('paraFrm').target='_parent';
			document.getElementById('paraFrm').action='OnlineProgram_callProgram.action?ProgramCode='+programCode+'&from=programPage&EmpID='+userCode+'&applicationCode='+applicationCode+'&userType='+userType;
 			document.getElementById('paraFrm').submit();
	
	}
	 
	if(id=='M')
	{
	document.getElementById('paraFrm').target='_parent';
			document.getElementById('paraFrm').action='OnlineProgram_callModule.action?moduleCodeStr='+moduleCode+'&programCodeStr='+programCode+'&sectionCodeStr=null&from=programPage&testStatus='+testStatus;
 			document.getElementById('paraFrm').submit();

	}

}


function callSection(sectionCode,testStatus,attemptCount)
{
	try{
	
	var programCode =document.getElementById('paraFrm_sectionProgramCode').value;
	var  moduleCode =document.getElementById('paraFrm_sectionModuleCode').value;
	 
	
	document.getElementById('paraFrm_source').value='sectionPage'
	var frmSrc = document.getElementById('paraFrm_source').value;
	//alert("moduleCode-------"+moduleCode);
	//alert("programCode-------"+programCode);
	//alert("sectionCode-------"+sectionCode);
 	
 	//alert(testStatus);
 	
 	 
	
	document.getElementById('paraFrm').target='_parent';
			document.getElementById('paraFrm').action='OnlineProgram_callSection.action?moduleCodeStr='+moduleCode+'&programCodeStr='+programCode+'&sectionCodeStr='+sectionCode+'&from='+frmSrc+'&testStatus='+testStatus+'&attemptCountStr='+attemptCount;
 			document.getElementById('paraFrm').submit();
 	
 	}catch(e){alert("Error-----"+e);}		

}

 


function callTest()
{
	try{
			var  moduleCode=document.getElementById('paraFrm_sectionModuleCode').value;
		 var  sectionProgramCode=document.getElementById('paraFrm_sectionProgramCode').value;
		 
		 var isSection =document.getElementById('paraFrm_sectionDisplayFlag').value;
		  var  sectionCode="";
	if(isSection=='true')
	{
	sectionCode = document.getElementById('paraFrm_sectionCode').value;
	}
		
			var frmSrc = document.getElementById('paraFrm_source').value;
			 
			document.getElementById('paraFrm').target='_parent';
			
			document.getElementById('testBtn').disabled=true;
			
			document.getElementById('paraFrm').action='OnlineProgram_takeTest.action?moduleCodeValue='+moduleCode+'&sectionProgramCode='+sectionProgramCode+'&sectionCodeStr='+sectionCode+'&from='+frmSrc;
 		
  
 		
 			document.getElementById('paraFrm').submit();
	}catch(e){alert("Error----------"+e);}
}


function callFinish()
{
	try{
			var  moduleCode=document.getElementById('paraFrm_sectionModuleCode').value;
		 var  sectionProgramCode=document.getElementById('paraFrm_sectionProgramCode').value;
		 
		 var isSection =document.getElementById('paraFrm_sectionDisplayFlag').value;
		  var  sectionCode="";
	if(isSection=='true')
	{
	sectionCode = document.getElementById('paraFrm_sectionCode').value;
	}
		
			var frmSrc = document.getElementById('paraFrm_source').value;
			
			var conf=confirm("Please ensure you have browsed through the entire content to proceed further. Please click \"OK\" to continue or \"Cancel\" to browse through the content once again…");
			 		if(conf)
			 		{
			 
			document.getElementById('paraFrm').target='_parent';
			
			document.getElementById('finishBtn').disabled=true;
			
			document.getElementById('paraFrm').action='OnlineProgram_callFinish.action?moduleCodeValue='+moduleCode+'&sectionProgramCode='+sectionProgramCode+'&sectionCodeStr='+sectionCode+'&from='+frmSrc;
  	
 			document.getElementById('paraFrm').submit();
 			
 			}
 			else{
 				return false;
 			}
	}catch(e){alert("Error----------"+e);}
}


function changeContent(){
	var ele = document.getElementById("paraFrm_contentId");
	if(ele != null){
		var index=document.getElementById("paraFrm_contentId").selectedIndex + 1;
		var length = document.getElementById("paraFrm_contentId").length;
		var info = "<b>" + index + " of " + length + "</b>";
		document.getElementById('indexInfo').innerHTML = info;
		
		var contentId = document.getElementById("paraFrm_contentId").value;
		contentId = contentId.split("|")[0]; 
		if(contentId != null && contentId != "" ){
			document.getElementById('contentIframe').src='OnlineProgram_getContent.action?contentId='+contentId;
		}
		//document.getElementById('contentIframe').contentWindow.location.reload(true);
		if(length <= 1 ){
			document.getElementById("navigationTr").style.display="none";
			document.getElementById("navigationHr").style.display="none";
			
		}
	}else {
		document.getElementById("navigationTr").style.display="none";
		document.getElementById("navigationHr").style.display="none";
		document.getElementById("zoomId").style.display="none";
		
	}
}

function first(){
	document.getElementById("paraFrm_contentId").selectedIndex = 0; 
	changeContent();
}

function last(){
	var length = document.getElementById("paraFrm_contentId").length;
	document.getElementById("paraFrm_contentId").selectedIndex = length - 1 ; 
	changeContent();
}

function previous(){
	var next=document.getElementById("paraFrm_contentId").selectedIndex;
	var options=document.getElementById("paraFrm_contentId").options;
	
	if(next == 0 ){
		alert("First content");
	} else {
		document.getElementById("paraFrm_contentId").selectedIndex = next - 1; 
		changeContent()
	}
}

function next(){
	var next=document.getElementById("paraFrm_contentId").selectedIndex;
	var options=document.getElementById("paraFrm_contentId").options;
	var length = document.getElementById("paraFrm_contentId").length;
	if(length == next +1 ){
		alert("Last content");
	} else {
		document.getElementById("paraFrm_contentId").selectedIndex = next + 1; 
		changeContent()
	}
}

changeContent();


function closeDiv(){
	parent.document.getElementById('optionDiv').style.display='none';
	parent.document.getElementById('windowDiv').style.display='none';
}
function openZoom(){
	var contentId = document.getElementById("paraFrm_contentId").value;
	var url = contentId.split("|")[1]; 
	if(url.replace(/^\s+|\s+$/g,"")==""){
		
	}else{
	/*
	parent.document.getElementById ('windowDiv').style.display='';
    parent.document.getElementById ('windowDiv').style.visibility='visible';
    parent.document.getElementById ('windowDiv').style.position='absolute';

    parent.document.getElementById ('windowDiv').style.top=parent.document.body.scrollTop;
    parent.document.getElementById ('windowDiv').style.left=parent.document.body.scrollTop;
    alert(document.body.offsetHeight);
    alert(parent.document.body.scrollHeight);
    var height=parent.document.body.scrollHeight;
    parent.document.getElementById ('windowDiv').style.height= document.body.offsetHeight+'px';
	//parent.document.getElementById ('windowDiv').style.width='100%';
    //parent.document.getElementById ('windowDiv').style.height='100%';

    parent.document.getElementById('windowDiv').style.backgroundColor = "Gray";
    parent.document.getElementById('windowDiv').style.filter = "alpha(opacity=40)";
    parent.document.getElementById('windowDiv').style.opacity = "0.6";
	
	parent.document.getElementById('optionDiv').style.display='block';
	parent.document.getElementById ('optionDiv').style.width='98%';
	//parent.document.getElementById ('zoomFrame').style.width=window.innerWidth;
	parent.document.getElementById ('optionDiv').style.height='98%';
	//parent.document.getElementById ('zoomFrame').style.height=window.innerHeight;
*/

 	parent.document.getElementById ('windowDiv').style.display='';
    parent.document.getElementById ('windowDiv').style.visibility='visible';
    parent.document.getElementById ('windowDiv').style.position='absolute';

    parent.document.getElementById ('windowDiv').style.top='0px';
    parent.document.getElementById ('windowDiv').style.left='0px';
    parent.document.getElementById ('windowDiv').style.width= '100%';
    
	
 	var height=parent.document.body.scrollHeight;
    parent.document.getElementById ('windowDiv').style.height= height+'px';

    parent.document.getElementById('windowDiv').style.backgroundColor = "Gray";
    parent.document.getElementById('windowDiv').style.filter = "alpha(opacity=40)";
    parent.document.getElementById('windowDiv').style.opacity = "0.6";
   	parent.document.getElementById('optionDiv').style.display='block';
	parent.document.getElementById('optionDiv').style.display='block';
	parent.document.getElementById ('optionDiv').style.width='98%';
	//parent.document.getElementById ('zoomFrame').style.width=window.innerWidth;
	parent.document.getElementById ('optionDiv').style.height='98%';
	//parent.document.getElementById ('zoomFrame').style.height=window.innerHeight;
	parent.window.focus();
	JSFX_FloatTopDiv();
	parent.document.getElementById('zoomFrame').src='Loading....';
	parent.document.getElementById('zoomFrame').src=url;
	}
}


var verticalpos="fromtop"

if (!document.layers)
document.write('</div>');
function JSFX_FloatTopDiv()
{
	var startX = 250,
	startY = 200;
	var ns = (navigator.appName.indexOf("Netscape") != -1);
	var d = parent.document;
	function ml(id)
	{
		var el=d.getElementById?d.getElementById(id):d.all?d.all[id]:d.layers[id];
		if(d.layers)el.style=el;
		el.sP=function(x,y){this.style.left=x;this.style.top=y;};
		el.x = startX;
		if (verticalpos=="fromtop")
		el.y = startY;
		else{
		el.y = ns ? pageYOffset + innerHeight : parent.document.body.scrollTop + parent.document.body.clientHeight;
		el.y -= startY;
		}
		return el;
	}
	window.stayTopLeft=function()
	{
		if (verticalpos=="fromtop"){
		var pY = ns ? pageYOffset : parent.document.body.scrollTop;
		ftlObj.y += (pY + startY - ftlObj.y)/8;
		}
		else{
		var pY = ns ? pageYOffset + innerHeight : parent.document.body.scrollTop + parent.document.body.clientHeight;
		ftlObj.y += (pY - startY - ftlObj.y)/8;
		}
		ftlObj.sP(ftlObj.x, ftlObj.y);
		setTimeout("stayTopLeft()", 10);
	}
	ftlObj = ml("optionDiv");
	stayTopLeft();
}






</script>


