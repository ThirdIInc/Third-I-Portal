
<%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>

 


<s:form action="OnlineProgram" validate="true" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">

		<tr>
			<td width="100%" colspan="3">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<!-- Header -->
				<tr>
					<td width="301" height="70" valign="middle"><img
						src="../pages/WBT/images/logo.gif" width="324" height="57" /></td>
					<td width="608" valign="bottom">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td height="25">
							<div align="right" class="apphead">Web Based Training
							Portal</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			<!-- Header --></td>
		</tr>

		<tr>
			<td colspan="3" width="100%" bgcolor="#EC7521" height="4px"></td>
		</tr>
		<tr>
			<td colspan="3">&nbsp;</td>
		</tr>
	<tr>
      <td colspan="3"><table width="100%" height="320" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="895" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td><table width="100%" border="0" cellpadding="5" cellspacing="2">
                <tr>
                  <td width="12%" class="appheader">Program Name  </td>
                  <td width="88%" class="heading1">
               <s:property value="testProgramName" />
			<s:hidden name="testProgramCode" />
                  </td>
                  </tr>
                    <tr>
                  <td width="12%" class="appheader">Module Name  </td>
                  <td width="88%" class="heading1">
           <s:property value="testModuleName" />
			<s:hidden name="testModuleCode" />
                  </td>
                  </tr>
                  	<s:if test="testSectionDisplayFlag">
                  <tr>
                  <td width="12%" class="appheader">Section Name  </td>
                  <td width="88%" class="heading1">
            <s:property value="testSectionName" />
				<s:hidden name="testSectionCode" />
                  </td>
                  </tr>
                  </s:if>
                      <tr>
                  <td width="12%" class="appheader">Question   </td>
                  <td width="88%">
         <s:property value="questionName" />
			<s:hidden name="questionName" /> <s:hidden name="questionLevel" />
                  </td>
                  </tr>
                  <s:if test="%{documentNotAttachedFlag}">
                   <tr>
                  <td width="12%" class="appheader">Refer attached document   </td>
                  <td width="88%" class="heading1">
       <s:hidden
					name="questionUploadedDoc" />
					<a href="#"
					onclick="showRecord('questionFile');"
					title="Click here to view attached document"> <font
					color="blue"><u><s:property value="questionUploadedDoc" /></u></font></a>
                  </td>
                  </tr>
                  
                  </s:if>
                  	<s:if test="optionFlag">
                  <tr>
				<td colspan="1"></td>
				<td width="100%" >
				<table width="100%" border="0" cellpadding="1" cellspacing="1"
					class="sortable">
						<%!int i = 0;%>
					<%
								int z = 0;
								String[]check = (String[])request.getAttribute("check");
								int k = 1;
								%>
					<s:iterator value="optionList">
						<tr>
							<td class="border2" width="2%" nowrap="nowrap">
							<%if(check[z].equals("true")){
											
											%> <input type="checkbox" name="chk" checked="checked"
								id="<%="chk"+k %>" value="N" /> <% 	
										} 
										else{
										%> <input type="checkbox" name="chk" id="<%="chk"+k %>"
								value="N" /> <%} %> <input type="hidden" name="checkBox"
								id="<%=k %>" value="N" /></td>
							<td class="border2" width="95%">&nbsp;<s:property
								value="optionName" /></td>
							<s:hidden name="optionName" id="<%="optionName"+k%>" />
							<s:hidden name="optionCode" id="<%="optionCode"+k%>" />
							<s:hidden name="optionAnswer" id="<%="optionAnswer"+k%>" />
						</tr>
						<%
									k++;z++;
									%>
					</s:iterator>
					<%
										i = k;
										k = 1;
									%>
									
						</table></td></tr>		
						</s:if>
							<s:else>
						<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="1" cellspacing="1" bordercolor="red"
					class="sortable">
					   <tr>
                  <td width="12%" class="appheader">Answer   </td>
                  <td width="88%" class="heading1">
        <s:textarea theme="simple"
							name="subjectAns" rows="5" cols="110" /></td>
						<td width="15%" valign="bottom" nowrap="nowrap">MaxLimit:<s:textfield
							size="4" readonly="true" name="subjAnswerLimit" />
                  </td>
                  </tr>
                  
                  <tr>
						<td colspan="3">
						<table>
							<tr>
								<td width="20%" valign="top"><strong>Attach answer
								document :</strong></td>
								<td width="30%"><s:textfield name="answerUploadedDoc"
									size="45" readonly="true" cssStyle="background-color: #F2F2F2;" />
								</td>
								<td width="50%" nowrap="nowrap"><input type="button"
									value="Select File" class="token"
									onclick="uploadFile('answerUploadedDoc');" /> <input
									type="button" value="View File" class="token"
									onclick="showRecord('answerFile');" /></td>
							</tr>
						</table>
						</td>
					</tr>
					
					</table></td></tr>	
                
                </s:else>
                <tr>
			<td colspan="3" valign="bottom" class="txt"><img
				src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
		</tr>
		
		<tr  align="center">
		<td width="12%"></td>
			<td width="88%"><s:if test="previousButton">
				<input type="Button" class="token" theme="simple" name="Previous"  id="previousBtn"
					value="Previous" onclick="previous()" />
			</s:if> <s:if test="nextButtonFlag">
				<input type="Button" class="token" theme="simple" name="Next" id="nextBtn"
					value="Next" onclick="next()" />
			</s:if> <s:if test="submitButtonFlag">
				<input type="button" class="token" theme="simple" name="Submit"  id="submitBtn"
					value="Submit" onclick="submitTest()" />
			</s:if></td>
		</tr>
		
			<tr>
			<td colspan="5" style="height: 1px; width:100; background-color: #EBEBEB;  "> 
			 
			 </td>
		</tr>
		<!--
		
		  <tr width="100%">
      <td colspan="3" width="100%"><hr /></td>
    </tr>
    
    <tr>
      <td height="25" colspan="3" width="100%"><div  class="text">
        <table width="100%" border="2" cellspacing="0" cellpadding="0">
          <tr>
            <td><div align="left">&reg;All Rights Reserved &copy;Copyright</div></td>
            <td><div align="right"><a href="javascript:void(0);" class="link" onclick="MM_openBrWindow('http://192.168.100.34/TechPortallive/terms.html','','width=700,height=400')">Terms &amp; Conditions</a> | <a href="javascript:void(0);" class="link" onclick="MM_openBrWindow('http://192.168.100.34/TechPortallive/privacypolicy.html','','width=700,height=400')">Privacy Policy</a></div></td>
          </tr></table>
        <div align="right"></div><table width="100%" border="0" cellspacing="0" cellpadding="0">
        </table>
        </div></td>
    </tr>
                --></table></td></tr></table></td></tr></table></td></tr></table>  
                
                
                <s:hidden name="testType" />

	<s:hidden name="tempTotalMarks" />
	 <s:hidden name="sequenceCode" />
	 <s:hidden name="lastQuestionSequence" />
	<s:hidden name="timerFlag" id="timerFlag" />
	<s:hidden name="equalweightage" />
	<s:hidden name="marksWrongAns" />
	<s:hidden name="marksNoAns" />
	<s:hidden name="onlineScore" />
	<s:hidden name="passingMarks" />
	<s:hidden name="marksForCorrect" />
	<s:hidden name="marksHard" />
	<s:hidden name="marksMedium" />
	<s:hidden name="marksEasy" />
	<s:hidden name="source" />
	
	<s:hidden name="wrongmarksHard" />
	<s:hidden name="wrongmarksEasy" />
	<s:hidden name="wrongmarksMedium" />
	<s:hidden name="equalMarksForAll" />
	<s:hidden name="optionFlag" />
	<s:hidden name="templateCode" />
	<s:hidden name="onlineTestCode" />
	<s:hidden name="equalMarkForQueTypeCheck" />
	<s:hidden name="testSectionDisplayFlag" />

	<s:hidden name="preQuestionLevel" />
	<s:hidden name="reqCode" />
	<s:iterator value="randomQueList">
		<s:hidden name="randomQuesCodes" />
	</s:iterator>

	<s:hidden name="documentNotAttachedFlag"/> 


	<s:hidden name="userCode" />
	<s:hidden name="applicationCode" />
	<s:hidden name="userType" />
              <s:hidden name="dataPath" />  
              
<s:hidden name="attempt"/> 

</s:form>


<script>

   function next(){
   try{
   var count;
   var len = '<%=i%>';
   var optionCode='';
   var finalAnswer='';
   
     var optionFlag = document.getElementById('paraFrm_optionFlag').value;
     
      if(optionFlag == 'false'){
   		var subjectAns = document.getElementById('paraFrm_subjectAns').value;
  		var subjLimit = document.getElementById('paraFrm_subjAnswerLimit').value;
   		if(subjectAns != "" && subjectAns.length > subjLimit){
   			alert('Answer limit should not exceed the max limit.');
   			return false;
   		}  
   }  
   else{
   	 for(var i=1;i<len;i++){
	   	if(document.getElementById('chk'+i).checked ==true){
	   		optionCode+= document.getElementById('optionCode'+i).value+",";
	   		if(document.getElementById('optionAnswer'+i).value=='Y'){
	   			finalAnswer = 'Y';
	   		} //end of if
	   		else{
	   			finalAnswer = 'N';
	   			count = 1;
	   		} //end of else
	   	} //end of if
	   	else{
	   		if(document.getElementById('optionAnswer'+i).value=='Y'){
	   			count = 1;
	   		}  
	   	}  
  	 }  
  	 
  	 if(count==1){
   		finalAnswer = 'N';
 	  }  
   }
  	optionCode = optionCode.substring(0,optionCode.length-1);
  	
  	var onlineTime ="";
  	var testModuleCode =document.getElementById('paraFrm_testModuleCode').value;
  	var sectionProgramCode =document.getElementById('paraFrm_testProgramCode').value;
  		var testSectionCode ="";
  		
  		if(document.getElementById('paraFrm_testSectionDisplayFlag').value=='true')
  		{
  		testSectionCode=document.getElementById('paraFrm_testSectionCode').value;
  		}
  	
  	
  	//alert(optionCode);
  	var frmSrc = document.getElementById('paraFrm_source').value;
  		document.getElementById('paraFrm').target = "_self";
  		
  		document.getElementById('nextBtn').disabled=true;
  		
   	document.getElementById('paraFrm').action='OnlineProgram_takeTest.action?onlineTime='+onlineTime+'&optionCode='+optionCode+'&finalAnswer='+finalAnswer+'&moduleCodeValue='+testModuleCode+'&sectionProgramCode='+sectionProgramCode+'&sectionCodeStr='+testSectionCode+'&from='+frmSrc;
 	document.getElementById('paraFrm').submit();
 	
 	
   }
   catch(e)
   {
   	alert("e"+e);
   }
   
   }

 function previous(){
   //	secs++;
   //	var onlineTime = document.getElementById('text').value;
   	//alert('onlineTime  :'+onlineTime);
   	var onlineTime ="";
   	var testModuleCode =document.getElementById('paraFrm_testModuleCode').value;
  	var sectionProgramCode =document.getElementById('paraFrm_testProgramCode').value;
  		var testSectionCode ="";
  		
  		if(document.getElementById('paraFrm_testSectionDisplayFlag').value=='true')
  		{
  		testSectionCode=document.getElementById('paraFrm_testSectionCode').value;
  		}
  	 
  			var frmSrc = document.getElementById('paraFrm_source').value;
   	document.getElementById('paraFrm').target = "_self";
   	
   		document.getElementById('previousBtn').disabled=true;
   		
   	document.getElementById('paraFrm').action='OnlineProgram_previous.action?onlineTime='+onlineTime+'&moduleCodeValue='+testModuleCode+'&sectionProgramCode='+sectionProgramCode+'&sectionCodeStr='+testSectionCode+'&from='+frmSrc;
 
 			 
 
 	//document.getElementById('paraFrm').action='OnlineProgram_takeTest.action?onlineTime='+onlineTime+'&optionCode='+optionCode+'&finalAnswer='+finalAnswer+'&moduleCodeValue='+testModuleCode+'&sectionProgramCode='+sectionProgramCode+'&sectionCodeStr='+testSectionCode+'&from='+frmSrc;
 
 	document.getElementById('paraFrm').submit();
 	document.getElementById('paraFrm').submit();
   }
   
     function submitTest(){
   try{
   	//var testType = document.getElementById('paraFrm_testType').value
   var len = '<%=i%>';
   var optionCode='';
   var finalAnswer='';
   var count;
   //alert('len  :'+len);
    var optionFlag = document.getElementById('paraFrm_optionFlag').value;
    if(optionFlag == 'false'){
    	  var subjectAns = document.getElementById('paraFrm_subjectAns').value;
    	  var subjLimit = document.getElementById('paraFrm_subjAnswerLimit').value;
    	if(subjectAns != "" && subjectAns.length > subjLimit){
   			alert('Answer limit should not exceed the max limit.');
   			return false;
   		} //end of if
   	} //end of if
   	else{
	   		for(var i=1;i<len;i++){
	   
		   	if(document.getElementById('chk'+i).checked ==true){
		   		optionCode+= document.getElementById('optionCode'+i).value+",";
		   		if(document.getElementById('optionAnswer'+i).value=='Y'){
		   			finalAnswer = 'Y';
		   		} //end of if
		   		else{
		   			finalAnswer = 'N';
		   			count = 1;
		   		} //end of else
		   	} //end of if
		   	else{
		   		if(document.getElementById('optionAnswer'+i).value=='Y'){
		   			count = 1;
		   		} //end of if
		   	} //end of else
	  	 } //end of loop
    }
   
   if(count==1){
   		finalAnswer = 'N';
   } //end of if
  	optionCode = optionCode.substring(0,optionCode.length-1);
  	//alert('final option code'+optionCode);
  	//alert('final finalAnswer'+finalAnswer);
  	var v=confirm("Are you sure you want to submit the test?");
  	if (v){
  	
  		var onlineTime ="";
   	var testModuleCode =document.getElementById('paraFrm_testModuleCode').value;
  	var sectionProgramCode =document.getElementById('paraFrm_testProgramCode').value;
  	 	 	var testSectionCode ="";
  		 
  		if(document.getElementById('paraFrm_testSectionDisplayFlag').value=='true')
  		{
  		testSectionCode=document.getElementById('paraFrm_testSectionCode').value;
  		}
  	 var frmSrc = document.getElementById('paraFrm_source').value;
  	 
  		document.getElementById('paraFrm').target = "_self";
  		
  		document.getElementById('submitBtn').disabled=true;
  		
  		document.getElementById('paraFrm').action='OnlineProgram_submit.action?optionCode='+optionCode+'&finalAnswer='+finalAnswer+'&moduleCodeStr='+testModuleCode+'&programCodeStr='+sectionProgramCode+'&sectionCodeStr='+testSectionCode+'&from='+frmSrc;;
 		
  		
 		document.getElementById('paraFrm').submit();
	} //end of if
	else{
		return false;
	} //end of else
 	 }catch(e){
   		alert(e);
     } //end of catch
   } //end of xxx method
   
function uploadFile(fieldName) {
	var path = document.getElementById("paraFrm_dataPath").value;
	document.getElementById('paraFrm').target = "_blank";
	window.open('<%=request.getContextPath()%>/pages/recruitment/uploadResume.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
	document.getElementById('paraFrm').target = "main";
}

//View uploaded documents - BEGINS
function showRecord(fileType) {
	var fileName = "";
	document.getElementById('paraFrm').target = "_blank";
	if(fileType=='answerFile') {
		fileName =document.getElementById('paraFrm_answerUploadedDoc').value;
		if(fileName == "") {
			alert("Please upload file.");
			return false ; 
		}
	} else {
		fileName =document.getElementById('paraFrm_questionUploadedDoc').value;
		if(fileName == "") {
			alert("File is not uploaded.");
			return false ; 
		}
	}
	document.getElementById('paraFrm').action = 'OnlineProgram_openUploadedFile.action?fileName='+fileName;
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target = "main";
	return true ; 
}

</script>
 