
<%@ taglib prefix="s" uri="/struts-tags"%>

<%!int w=0 ; %>
<script language="JavaScript" type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"> </script>


<s:form action="OnlinePaperPriview" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">
		<tr>
			<s:hidden name="paperCode" value="%{paperCode}" />
			<s:hidden name="candidateLoginCode" value="%{candidateLoginCode}" />
			<s:hidden name="chkObject" value="%{chkObject}" />
			<s:hidden name="hiddenNext" id="hiddenNext" />
			<s:hidden name="hiddenPrivious" id="hiddenPrivious" />
			<td colspan="3" valign="bottom" class="txt">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="3" valign="bottom"
				background="../pages/images/recruitment/lines.gif" class="txt"><img
				src="../pages/images/recruitment/lines.gif" width="16" height="1" /></td>
		</tr>
		<tr>
			<td colspan="2" valign="bottom" class="txt"><img
				src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
		</tr>
		<tr>
			<td width="4%" valign="bottom" class="txt"><strong
				class="formhead"><img
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="formhead">
			Online Paper Set </strong></td>
		</tr>
		<tr>
			<td height="5" colspan="3"><img
				src="../pages/images/recruitment/space.gif" width="5" height="7" /></td>
		</tr>
		<tr>
			<td colspan="2">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="20%" align="left" colspan="1"><strong class="formhead">PAPER NAME  :</strong>
					<b><s:property  value="%{paperName}"/></b>
						 <s:hidden name="paperName" />
					</td>
					<td width="50%" align="right" colspan="1"><strong class="formhead">Time Duration :</strong>
					 <s:textfield name="paperTimeDuration" value="%{paperTimeDuration}"
						id="paperTimeDuration" size="5" readonly="true" /><s:hidden
						name="Time Duration" /></td>
					</tr>
				<tr>
					<td width="20%" align="left" colspan="1"><strong class="formhead">Total Number Of
					Question :</strong>
					<b><s:property   value="%{totalNoOfQues}"/></b>
					<s:hidden name="totalNoOfQues"/>
					</td>
					<td width="50%" align="right" colspan="1"><strong class="formhead">Time :</strong> <s:textfield
						name="text" value="%{text}" id="text" size="5" readonly="true" /></td>
				</tr>
			</table>
			<label></label></td>
		</tr>
		<tr>
			<td align="right">
			<table width="100%">
				<tr><td width="100%" align="right"><span id="theTime"
						class="timeClass"></span></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				</tr>
				<tr>
					<%! 
					int pp=0;
					%>
					<%! int kk = 0;%>
					<%	kk = (Integer) request.getAttribute("jjj");

						pp = (Integer) request.getAttribute("ppp");

						System.out.println("-------------------" + kk);

						String[] option = { "a", "b", "c", "d", "e", "f" };
					%>
					<td class="formth" width="100%">QuestionDesc</td>
				</tr>
				<tr>
					&nbsp;&nbsp;&nbsp;
					<%int i = 0;%>
					<%int j = 0; %>
					<%!int ii = 0; %>
					<%!int jj = 0; %>
					<s:iterator value="showPriview">
						<% ++i; %>
						<tr>
							<s:hidden name="testCode" />

							<td><s:hidden name="quesCode" /><s:hidden
								name="subjectQuestCode" /> <s:property value="quesDescItt" />
							<table>
								<s:hidden name="chkFlag" />
								<s:hidden name="chkobj" />
								<% int k = 0;%>
								<s:iterator value="showPriviewOption">
                            <tr>
										<s:if test="chkobj">
											<td><s:textarea name="SubjOpt" rows="03" cols="30"></s:textarea>
                                   </td>
										</s:if>
										<s:else>
											<td><input type="hidden" id="quesCodeChk<%=j%>"
												size="02" name="quesCodeChk"
												value='<s:property 	value="quesCodeChk" />' />
											&nbsp;&nbsp;&nbsp; <%=option[k]%>) <!--  <input  type="text"    id="optionCode"  name="optionCode" size="2"/>-->
											<s:if test="chkFlag">
												<input type="checkbox" name="Option" id="Option<%=j%>"
													checked="checked" onclick="setChkFlag('<%=j%>')">
												<s:property value="questionOption" />
											</s:if> <s:else>
												<input type="checkbox" name="Option" id="Option<%=j%>"
													onclick="setChkFlag('<%=j%>')">
												<s:property value="questionOption" />
											</s:else> <input type="hidden" id="optionCode<%=j%>" size="02"
												name="optionCode" value='<s:property 	value="optionCode" />' />
											</td>
										</s:else>
									</tr>
                                     <%	++k;%>
									<% ++j;	%>
								</s:iterator>
							</table>
					</s:iterator>
					<% ii=i;%>
						<%jj=j;	%>
					</td>
				</tr>
				</tr>
				<table>
					<tr>
						<td>
						<table width="40" border="0" cellpadding="0" cellspacing="0">
							<tr>
                                  <td width="40%" align="right" colspan="4"><s:hidden
									name="chkNextValue" /> <s:hidden name="chkNextPrevFlag" />
							<s:hidden  name="noOfQuestionPerPage" value="%{noOfQuestionPerPage}" />
								Page:</td>
								<td width="40%" colspan="4"><s:hidden
									name="onlinePaperPriview.Page" /></td>
								<td><s:textfield size="1" name="nextCode"
									value="%{nextCode}" /></td>
								<td colspan="3" valign="bottom" class="txt">&nbsp;</td>

								<td><s:if test="flag1"></s:if><s:else>
									<input type="Button" class="pagebutton" theme="simple"
										name="Next" value="Next" onclick="next()" />
								</s:else></td>
								<td><s:if test="flag">
									<input type="Button" class="pagebutton" theme="simple"
										name="Privious" value="Privious" onclick="privious()" />
								</s:if></td>
								<td><input type="Button" class="pagebutton" theme="simple"
									name="Result" value="Result" onclick="return result()" /></td>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<table width="100%">
		<tr>
			<td width="100%" align="right"><span id="theTime"
				class="timeClass"></span></td>
		</tr>
	</table>
	<s:hidden name="timerFlag" id="timerFlag" />
</s:form>
<script>

</script>
<script language="JavaScript">
var hrs,mins,secs,TimerRunning,TimerID;
 TimerRunning=false;
  setTime();
 function setTime(){
 var timerFlag = document.getElementById('timerFlag').value;
  if(timerFlag=="start"){
 	StartTime();
 }
  }
 			function setChkFlag(id)
 			{ 			
 			if(document.getElementById('Option'+id).checked==true)
 			{ 			
 			document.getElementById('quesCodeChk'+id).value='Y'; 
 			}
 			else
 			document.getElementById('quesCodeChk'+id).value=''; 
 			}
 function next(){
 secs++;
document.getElementById('paraFrm_chkNextPrevFlag').value='N';
var r='<%=kk%>';
document.getElementById('hiddenNext').value=r;
 var val=document.getElementById('nextCode').value;
 var value1=eval(val)+1;
 document.getElementById('nextCode').value=value1;
 document.getElementById('paraFrm').action="CandidateLogin_next.action";
 document.getElementById('paraFrm').submit();
}
function privious(){
var r='<%=pp%>';
document.getElementById('hiddenNext').value=r;
document.getElementById('hiddenPrivious').value=r;
document.getElementById('paraFrm_chkNextPrevFlag').value='P';
secs++;
 var val=document.getElementById('nextCode').value;
 var value1=eval(val)-1;
  document.getElementById('nextCode').value=value1;
 document.getElementById('paraFrm').action="CandidateLogin_privious.action";
 document.getElementById('paraFrm').submit();
  } 
 function StartTime() //call the Init function when u need to start the timer
 {
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
 StartExam();
  function StartExam(){
 if(<%=w%>==0){
 var v=confirm("Are u sure to start the Exam");
   if (v)
  {
  StartTime();
  }
  else
  { alert("you  are not  ready to start exam");
  StopTimer();
  window.close();
  }
  <%w++;%>
  }
  else{
  <%w++;%>
  } 
 }
 function StartTimer()
 {
	 TimerRunning=true;
 
  if(document.getElementById('text').value==""){
  document.getElementById('text').value=+hrs+":"+mins+":"+secs;
  }
  else{ 	
 	document.getElementById('text').value=+hrs+":"+mins+":"+secs;
  
  }
	
   TimerID=self.setTimeout("StartTimer()",1000);
   Check();
var endtime=document.getElementById('paperTimeDuration').value;
fieldList=endtime.split(":");
   if(hrs==fieldList[0] && mins==fieldList[1] && secs==fieldList[2])
   {
      StopTimer();
   }    
if(mins==60)
   {
      hrs++;
      mins=0;
      if(hrs<10)
      {
     hrs ="0"+""+hrs;
      }
   }    
   if(secs==60)
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
  function Check()
 {
var endtime=document.getElementById('paperTimeDuration').value;

	fieldList=endtime.split(":");
var hh=00;
var mm=00;
var ss=00;
 if(fieldList[0]==00 && fieldList[1]==00)
 {
    ss=(fieldList[2]-10);
   }
 else if(fieldList[0]==00 && fieldList[2]==00)
 {
    mm=(fieldList[1]-1);
 ss=50;
  }
 else if(fieldList[1]==00 && fieldList[2]==00)
 {
    hh=(fieldList[0]-1);
 ss=50;
   }
  else if((!fieldList[0]==00 )&& !(fieldList[1]==00) && !(fieldList[2]==00))
 {
  hh=fieldList[0];
  mm=fieldList[1];
  ss=(fieldList[2]-10);
 }
if(hrs==eval(hh) && mins==eval(mm) && secs==eval(ss))
   {
      alert("Your alloted time is 10 second remaining.");
   }
   else if(hrs==eval(hh) && mins==eval(mm) && secs==(eval(ss)+10))
   {
      alert("Your alloted time is over.");
      
       StopTimer();
       window.close();
   }
   }

 function Pad(number) //pads the mins/secs with a 0 if its less than 10
 {
 //alert(number);
	if(number<10){
	   number= number;
	  }
   return number;
 }
function result(){
 var msg;
msg= "ARE U SURE YOU WANT TO GET RESULT";
var agree=confirm(msg);
if (agree)
{
 document.getElementById('paraFrm').action="CandidateLogin_result.action";
 document.getElementById('paraFrm').submit();
 return true ;
}
else
return false ;
}
 </script>
<script>
document.oncontextmenu = new Function ("return false ");
</script>
