<%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="OnlineTest" validate="true" id="paraFrm" theme="simple">
	<s:hidden name="dataPath"/>
	<s:hidden name="wrongmarksHard"/>
	<s:hidden name="wrongmarksEasy"/>
	<s:hidden name="wrongmarksMedium"/>
	<s:hidden name="equalMarksForAll"/>
	<s:hidden name="optionFlag"/>
	<s:hidden name="templateCode"/>
	<s:hidden name="onlineTestCode" />	
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
						<td width="100%"><strong>Test Paper</strong></td>
					</tr>
					<tr>
						<td width="100%"></td>
					</tr>
					
					<tr>
						<td>
							
							<table width="100%" border="0"  align="center" cellpadding="0" cellspacing="2"><!--Table header-->
								<tr>
									<td width="20%">Test Name :</td>
									<td width="25%"><s:hidden name="testTemplateCode" /><s:property value="tempName"/>
									<s:hidden name="tempName"/></td>
									<td width="20%">&nbsp;</td>
									<td width="25%">&nbsp;</td>
								</tr>
								<tr>
									<td width="20%">Total Number Of Question  :</td>
									<td width="25%"><s:property value="tempTotalQues"/><s:hidden name="tempTotalQues"/></td>
									<td width="25%">Time Duration :</td>
									<td width="25%"><s:property value="tempDuration"/><s:hidden name="tempDuration" />
									</td>
								</tr>
								<tr>
									<td width="20%">Total Marks :</td>
									<td width="25%"><s:hidden name="tempTotalMarks" /><s:property value="tempTotalMarks"/>
									</td>
									<td width="25%">Time :</td>
									<td width="25%">
										<s:textfield name="text" value="%{text}" id="text" size="10" readonly="true" cssStyle="background-color: #F2F2F2; border: none;"/>
									</td>
								</tr>
							</table><!--Table header-->
						</td>
					</tr>		
					
				</table><!--Table 2-->
			</td>
		</tr><!--Schedule interview-->	
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
						<td width="100%"><strong>Test Questions</strong></td>
					</tr>
					<tr>
						<td width="100%"></td>
					</tr>
					
					<tr>
						<td>
							<table width="100%" border="0"  align="center" cellpadding="0" cellspacing="2"><!--Table header-->
								<tr>
									<td colspan="3">
										<strong>Question No : </strong><s:property value="sequenceCode"/> of <s:property value="tempTotalQues"/>
									</td>
								</tr>
								
								<tr>
									<td colspan="3">
										<table cellpadding="0" cellspacing="0">
											<tr>
												<td>
													<strong>Subject : </strong><s:property value="subject"/> 
												</td>
												<td width="20px">&nbsp;</td>
												<td>
													<strong>Category : </strong><s:property value="category"/>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								
									<tr>
										<td width="6%" valign="top"><strong>Question :</strong></td>
										<td width="50%" colspan="2">
											<s:property value="questionName"/>
											<s:hidden name="questionName" />
											<s:hidden name="questionLevel" />
										</td>
									</tr>
								<s:if test="documentNotAttachedFlag">
									<tr>
										<td colspan="3">
											<strong>Refer attached document :  </strong>
											<s:hidden name="questionUploadedDoc"/>
											<a href="#" onclick="showRecord('questionFile');" title="Click here to view attached document">  <font color="blue"><s:property value="questionUploadedDoc"/></font></a> 
										</td>
									</tr>
								</s:if>
								<s:else>
									<tr>
										<td colspan="3">&nbsp;</td>
									</tr>
								</s:else>
								
								<tr>
									<td colspan="3" valign="bottom" class="txt"><img
									src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
								</tr>
								</tr>
							</table><!--Table header-->
						</td>
					</tr>
					<s:if test="optionFlag">
						<tr>
							<td>
								<table width="100%" border="0" cellpadding="1" cellspacing="1" class="sortable"><!--Table 6-->
									<tr>
										<td width="2%" valign="top" class="formth">&nbsp;</td>
										<td width="3%" valign="top" class="formth">Sr No.</td>
										<td width="40%" valign="top" class="formth">Option</td>
									</tr>
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
											
											%>
											<input type="checkbox"  name="chk" checked="checked" id="<%="chk"+k %>" value="N"/>
										<% 	
										} 
										else{
										%>
										<input type="checkbox"  name="chk"  id="<%="chk"+k %>" value="N"/>
										<%} %>
										
			                        			<input type="hidden" name="checkBox" id="<%=k %>" value="N"/></td>
										<td class="border2" width="3%" nowrap="nowrap"><%=k%></td>
										<td class="border2" width="15%">&nbsp;<s:property
											value="optionName"/></td>
										<s:hidden name="optionName" id="<%="optionName"+k%>"/>
										<s:hidden name="optionCode" id="<%="optionCode"+k%>"/>
										<s:hidden name="optionAnswer" id="<%="optionAnswer"+k%>"/>	
									</tr>
									<%
									k++;z++;
									%>	
								</s:iterator>
									<%
										i = k;
										k = 1;
									%>
								</table><!--Table 6-->
							</td>	
						</tr>
					</s:if>
					<s:else>
						<tr>
							<td>
								<table width="100%" border="0" cellpadding="1" cellspacing="1" class="sortable"><!--Table for subjective text area-->
									<tr>
										<td colspan="1" width="5%" valign="top">
											<strong>Answer:</strong>
										</td>
										<td colspan="2" width="30%">
											<s:textarea theme="simple"
													name="subjectAns" rows="5" cols="110" />
										</td>
										<td width="15%" valign="bottom" nowrap="nowrap">
											MaxLimit:<s:textfield size="4" readonly="true" name="subjAnswerLimit"/>
										</td>
									</tr>
									
									<tr>
										<td colspan="4">
											<table>
												<tr>
													<td width="20%" valign="top">
														<strong>Attach answer document :</strong>
													</td>
													<td width="30%">
														 <s:textfield name="answerUploadedDoc" size="45" readonly="true" cssStyle="background-color: #F2F2F2;" />
													</td>
													<td width="50%" nowrap="nowrap">
														<input type="button" value="Select File" class="token" onclick="uploadFile('answerUploadedDoc');" />
														<input type="button" value="View File" class="token" onclick="showRecord('answerFile');" />	
													</td>
												</tr>
											</table>
										</td>
									</tr>
									
								</table><!--Table for subjective text area-->
							</td>
						</tr>		
					</s:else>	
					<tr>
						<td colspan="5" valign="bottom" class="txt"><img
						src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
					</tr>			
					<tr>
						<td align="center" >
						<s:if test="previousButton">
							<input type="Button" class="token" theme="simple"
										name="Previous" value="Previous" onclick="previous()" />
						</s:if>
						<s:if test="nextButtonFlag">	
						<input type="Button" class="token" theme="simple"
										name="Next" value="Next" onclick="next()" />
						</s:if>
						<s:if test="submitButtonFlag">	
						<input type="button" class="token" theme="simple"
										name="Submit" value="Submit" onclick="xxx()" />
						</s:if>		
						</td>
					</tr>
				</table><!--Table 2-->
			</td>
		</tr><!--Schedule interview-->				
	</table><!-- Final Table -->
	<s:hidden name="testType"/>	 
	<s:hidden name="sequenceCode"/>
	<s:hidden name="lastQuestionSequence"/>
	<s:hidden name="timerFlag" id="timerFlag" />
	<s:hidden name="equalweightage"/>
	<s:hidden name="marksWrongAns"/>
	<s:hidden name="marksNoAns"/>
	<s:hidden name="onlineScore"/>
	<s:hidden name="passingMarks"/>
	<s:hidden name="marksForCorrect"/>
	<s:hidden name="marksHard"/>
	<s:hidden name="marksMedium"/>
	<s:hidden name="marksEasy"/>
	
	
	<s:hidden name="preQuestionLevel"/>
	<s:hidden name="reqCode"/>
	<s:iterator value="randomQueList">
		<s:hidden name="randomQuesCodes" />
	</s:iterator>
</s:form>

<script>
var hrs,mins,secs,TimerRunning,TimerID;
 TimerRunning=false;
  setTime();
  function setTime(){
 	var timerFlag = document.getElementById('timerFlag').value;
  	if(timerFlag=="start"){
  	//alert('in setTime');
 		StartTime();
	 }
  }
  
  function StartTime() //call the Init function when u need to start the timer
 {
 //alert("start time........."+document.getElementById('text').value);
		if(document.getElementById('text').value==""){
		   hrs="00";
		   mins="00";
		   secs="00";
	   }
	   else{
		   hrs="";
		   mins="";
		   secs="";
		   var timeVAl = document.getElementById('text').value;
		   var timeSplit = timeVAl.split(":");
		   hrs = timeSplit[0];
		   mins = timeSplit[1];
		   secs= timeSplit[2];
	  }
	   	   StopTimer();
	   	   StartTimer();
 }
 
 function StopTimer()
 {
   if(TimerRunning)
   clearTimeout(TimerID);
   TimerRunning=false;
 }
 
 function StartTimer()
 {
	 TimerRunning=true;
 //alert('StartTimer');
  if(document.getElementById('text').value==""){
 	 document.getElementById('text').value=+hrs+":"+mins+":"+secs;
  }
  else{ 	
 	document.getElementById('text').value=+hrs+":"+mins+":"+secs;
  
  }
	
   TimerID=self.setTimeout("StartTimer()",1000);
   Check();
	var endtime=document.getElementById('paraFrm_tempDuration').value;
	
	fieldList=endtime.split(":");
   if(hrs==fieldList[0] && mins==fieldList[1] && secs==fieldList[2])
   {
      StopTimer();
   }    
if(mins==59)
   {
      hrs++;
      mins=0;
      if(hrs<10)
      {
     hrs ="0"+""+hrs;
      }
   }    
   if(secs==59)
   {
      mins++;
      secs=0;
      if(mins<10)
      {
      mins ="0"+""+mins;
      }
   }
   secs++;
   if(secs<10)
      	{ 		
   		 secs ="0"+""+secs;
		}
 }
 
function Check() {

try {
	var endtime=document.getElementById('paraFrm_tempDuration').value;
	//alert("endtime........"+endtime);
	fieldList=endtime.split(":");
	//alert("fieldList[0]...."+fieldList[0]+"fieldList[1]....."+fieldList[1]+"fieldList[2]......"+fieldList[2]);
	var hh=00;
	var mm=00;
	var ss=00;
	//alert('fieldList[0]  :'+fieldList[0]);//hrs
	//alert('fieldList[1]  :'+fieldList[1]);
	//alert('fieldList[2]  :'+fieldList[2]);
 	if(fieldList[0]==00 && fieldList[1]==00) {
    	ss=(fieldList[2]-10);
    	//alert(1);
   } else if(fieldList[0]==00 && fieldList[2]==00) {
 	//alert(2);
    mm=(fieldList[1]-1);
 	ss=50;
  } else if(fieldList[1]==00 && fieldList[2]==00) {
 	//alert(3);
    hh=(fieldList[0]-1);
     mm=59;
 	ss=50;
  } else if((!fieldList[0]==00 )&& !(fieldList[1]==00) && !(fieldList[2]==00)) {
  	hh=fieldList[0];
  	mm=fieldList[1];
  	ss=(fieldList[2]-10);
  }
 //alert("hrs:"+hrs+"mins:"+mins+"secs:"+secs)
 //alert("hh:"+hh+"mm:"+mm+"ss:"+ss)
 
   if(hrs==eval(hh) && mins==eval(mm) && secs==eval(ss)) {
      alert("Your alloted time is 10 second remaining.");
   } else if(hrs==eval(hh) && mins==eval(mm) && secs== eval(ss+10)) {
      alert("Your alloted time is over.");
      StopTimer();
      
   	  var optionCode='';
   	  var finalAnswer='';
      var count;
  	  var optionFlag = document.getElementById('paraFrm_optionFlag').value;
   	  if(optionFlag == 'true'){
   	  	var len = '<%=i%>';
  	  	for(var i=1;i<len;i++){
		   	if(document.getElementById('chk'+i).checked ==true){
		   		optionCode+= document.getElementById('optionCode'+i).value+",";
		   		
		   		if(document.getElementById('optionAnswer'+i).value=='Y'){
		   			finalAnswer = 'Y';
		   		} else {
		   			finalAnswer = 'N';
		   			count = 1;
		   		}
		   	} else {
		   		if(document.getElementById('optionAnswer'+i).value=='Y'){
		   			count = 1;
		   		}
		   	}
   	   }
  	  } 
   	  
	   if(count==1){
	   		finalAnswer = 'N';
	   }
  	   optionCode = optionCode.substring(0,optionCode.length-1);
  	   document.getElementById("paraFrm").target = "_self";
       document.getElementById("paraFrm").action='OnlineTest_submit.action?optionCode='+optionCode+'&finalAnswer='+finalAnswer;
	   document.getElementById("paraFrm").submit();
   }
} catch(e) {
	//alert("Error >>>>"+e);
}

}
   
   function next(){
   try{
   var testType = document.getElementById('paraFrm_testType').value
   var len = '<%=i%>';
   var optionCode='';
   var finalAnswer='';
   var count;
  // alert('len  :'+len);
   var optionFlag = document.getElementById('paraFrm_optionFlag').value;
   //alert('optionFlag  :'+optionFlag);
   if(optionFlag == 'false'){
   		var subjectAns = document.getElementById('paraFrm_subjectAns').value;
  		var subjLimit = document.getElementById('paraFrm_subjAnswerLimit').value;
   		if(subjectAns != "" && subjectAns.length > subjLimit){
   			alert('Answer limit should not exceed the max limit.');
   			return false;
   		} //end of if
   } //end of else
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
   } //end of else
  
   if(count==1){
   		finalAnswer = 'N';
   } //end of if
  	optionCode = optionCode.substring(0,optionCode.length-1);
  	
   	secs++;
   	var onlineTime = document.getElementById('text').value;
   	//alert('onlineTime  :'+onlineTime);
   	document.getElementById('paraFrm').target = "_self";
   	document.getElementById('paraFrm').action='OnlineTest_startTest.action?onlineTime='+onlineTime+'&optionCode='+optionCode+'&finalAnswer='+finalAnswer;
 	document.getElementById('paraFrm').submit();
 	//document.getElementById('paraFrm_subjectAns').value = "";
    }catch(e){
   		alert(e);
    } //end of catch
   } //end of next method
   
   function previous(){
   	secs++;
   	var onlineTime = document.getElementById('text').value;
   	//alert('onlineTime  :'+onlineTime);
   	document.getElementById('paraFrm').target = "_self";
   	document.getElementById('paraFrm').action='OnlineTest_previous.action?onlineTime='+onlineTime;
 	document.getElementById('paraFrm').submit();
   }
   
   function xxx(){
   try{
   	var testType = document.getElementById('paraFrm_testType').value
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
   	} //end of else
   
   if(count==1){
   		finalAnswer = 'N';
   } //end of if
  	optionCode = optionCode.substring(0,optionCode.length-1);
  	//alert('final option code'+optionCode);
  	//alert('final finalAnswer'+finalAnswer);
  	var v=confirm("Are you sure you want to submit the test?");
  	if (v){
  		document.getElementById('paraFrm').target = "_self";
  		document.getElementById('paraFrm').action='OnlineTest_submit.action?optionCode='+optionCode+'&finalAnswer='+finalAnswer;
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
	document.getElementById('paraFrm').action = 'OnlineTest_openUploadedFile.action?fileName='+fileName;
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target = "main";
	return true ; 
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