<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="TestTemplate" id="paraFrm" validate="true" theme="simple" target="main">
<s:hidden name="myPage"/>
<s:hidden name="totalNoQue"/>
<input type="text" name="checkText" style="visibility: hidden;"/>
	<s:hidden name="correctCheck"/>
	<s:hidden name="marksForHard"/>
	<s:hidden name="marksForMedium"/>
	<s:hidden name="marksForEasy"/>
	<s:hidden name="testTotalMarks"/>
	<s:hidden name="testType" />
	<s:hidden name="category"/>
	<s:hidden name="compLevel" />
	<s:hidden name="compLevel1" />
	<s:hidden name="subject" />
	<s:hidden name="hardCompLevel" />
	<s:hidden name="mediumCompLevel" />
	<s:hidden name="easyCompLevel" />
	<s:hidden name="listFlag"/>
	<s:hidden name="cntHard"/>
	<s:hidden name="cntMedium"/>
	<s:hidden name="cntEasy"/>
	<s:hidden name="totalnoOfQus"/>
	<s:hidden name="hiddenSubject" id="hiddenSubject" />
	<s:hidden name="hiddenCategory" />
	<s:hidden name="markHard"/>
	<s:hidden name="markMedium"/>
	<s:hidden name="markEasy"/>
	<s:hidden name="totalMarkss"/>
	
	<s:hidden name="equalDistriRadio"/>
	<s:hidden name="defineDistriRadio"/>
	
	<s:hidden value="selectQuesCode"/>	
	<s:hidden name="hiddenQuestionCode"/>
	<s:hidden name="hiddenCode"/>
	<s:hidden name="equalWeightageFlag" />
	<s:hidden name="hiddenQuCodeWithStar"/>
	<s:hidden name="checkedQuestions"/>
	<s:hidden name="addToListQuestions"/>
<table class="formbg" width="100%"><!--Start of initial table  -->
	<tr>
       <td colspan="3">
         <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg"><!--table 1  -->
         	<tr>
				<td valign="bottom" class="txt"><strong class="formhead">
					<img src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
				<td width="93%" class="txt"><strong class="formhead">Question Bank </strong></td>
				<td width="3%" valign="top" class="txt">
					<div align="right">
						<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
			</tr>
         </table><!--end of table 1  -->
       </td>
    </tr>
    <tr>
        <td colspan="3">
        	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
	           <tr>
	              <td width="15%">
	              	<input type="button" class="token" name="Add to list" value="  Add to list  " onclick="selectQues();"/>				
	              </td>
	               <td width="22%">
					<input type="button" class="token" name="Cancel" value=" Cancel " onclick="cancel();"/>				
	              </td>
	              <td colspan="3" align="right"><%
					int totalPage = (Integer) request.getAttribute("totalPage");
					int pageNo = (Integer) request.getAttribute("PageNo");
				  %>
					<s:if test="noData"> </s:if>
				    <s:else>
	                   <td align="right"><b>Page:</b>
							<input type="hidden" name="totalPage"  id="totalPage" value="<%=totalPage%>">
							<a href="#" onclick="callPage('1','F');"  >
							<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
							</a>&nbsp;
							<a href="#" onclick="callPage('P','P');" >
							<img  title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
							</a> 
							<input type ="text" name="pageNoField" id="pageNoField" theme="simple" size="3"  value="<%= pageNo%>"  onkeypress="callPageText(event);return numbersOnly()"   maxlength="4" title="press Enter" /> of <%=totalPage%>
							<a href="#" onclick="callPage('N','N')" >
							<img  title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
							</a>&nbsp;
							<a href="#" onclick="callPage('<%=totalPage%>','L');" >
							<img title="Last Page"  src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
							</a>
						</td>
					</s:else>	  </td>
	           </tr>
          </table>
        </td>
    </tr>
     <tr>
		<td width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"	class="formbg">
				<tr>
					<td height="29" width="40%" >Select complexity level:</td>
					<td height="29" colspan="3" width="60%">Hard<s:checkbox 
						name="hard" disabled="true" onclick="showlist1();" />&nbsp;Medium <s:checkbox name="medium" onclick="showlist1();" disabled="true" />&nbsp;
						Easy <s:checkbox name="easy" disabled="true" onclick="showlist1();"/>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg"  id="tblqulist"><!--table 3  -->
				<tr>
				   <td width="100%" colspan="5"><strong class="forminnerhead">Question List</strong></td>
				</tr>
				<tr>	
					<td width="6%" valign="top" class="formth"><b>Sr No</b></td>
					<td width="25%" valign="top" class="formth"><b>Subject-Category</b></td>
					<td width="30%" valign="top" class="formth"><b>Question Name</b></td>
					<td width="15%" valign="top" class="formth"><b>Complexity Level</b></td>
					<td width="10%" valign="top" class="formth"><input type="checkbox" name="chkAll" id="paraFrm_chkAll"  onclick="return callQuestionsAll();"/><b>Select</b></td>
				</tr>
				<%
					int cnt= (pageNo-1)*20;	
				%>
				<%!int l = 0, t=0;%>
				<s:iterator status="stat" value="quesDataList">
					<tr>		
						<input type="hidden" name="selectQuesCode" id="selectQuesCode<%=l%>"  value='<s:property value="selectQuesCode"/>'/>
						<td class="sortabletd" width="6%"><%=++cnt%>&nbsp;</td>
						<td class="sortabletd" width="25%">
							<input type="hidden" name="quesSubject" value='<s:property value="quesSubject"/>' id="quesSubject<%=l%>"/>
							<s:property value="quesSubject"/>&nbsp;</td>
						<td class="sortabletd" width="30%">
							<input type="hidden" name="quesName" value='<s:property value="quesName"/>' id="quesName<%=l%>"/>
							<s:property value="quesName"/>&nbsp;</td>
						<td class="sortabletd" width="15%" align="center">
							<input type="hidden" name="complexicity" value='<s:property value="complexicity"/>' 
							id="complexicity<%=l%>"/><s:property value="complexicity"/>&nbsp;</td>
						<td class="sortabletd" width="10%">
							<input type="checkbox"  name="check" id="<%="check"+l %>"   value='<s:property value="selectQuesCode"/>' 
							onclick="return checkedbox('<%=l%>');" /> 
							<input type="hidden" name="checkFlag" id="checkFlag<%=l%>" /></td>
					</tr>
							<%l++;%>							
				</s:iterator>
				<%t=l;l=0; %>
				<input type="hidden" name="rec" id="rec" value='<%=(t)%>' />		
			</table><!--end of table 3 -->
		</td>
	</tr>
	<tr>
        <td colspan="3">
        	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	           <tr>
	              <td width="78%">
	              	<input type="button" class="token" name="Add to list" value=" Add to list " onclick="selectQues();"/>				
					<input type="button" class="token" name="Cancel" value=" Cancel " onclick="cancel();"/>				
	              </td>
	              <td width="22%"><div align="right"></td>
	           </tr>
          </table>
        </td>
    </tr>		     
</table><!--End of initial table  -->
</s:form>
<script>
onLoad();

function onLoad(){
	document.getElementById('paraFrm_easy').checked = true;
	document.getElementById('paraFrm_medium').checked = true;
	document.getElementById('paraFrm_hard').checked = true;
	try{
		if(document.getElementById('paraFrm_addToListQuestions').value == "")return false;
	var checkedQues = document.getElementById('paraFrm_addToListQuestions').value;
	checkedQues = checkedQues.substring(0,checkedQues.length - 1);
	var checkArray = checkedQues.split(',');
	//alert("checkArray=="+checkArray.length);
	var listCount='<%=t%>';
	//alert("listCount=="+listCount);
	if(listCount > 0){
		for(var q=0;q<listCount;q++){
			for(var i=0;i<checkArray.length;i++){
			//alert(document.getElementById('selectQuesCode'+q).value);
			//alert(checkArray[i]);
				if(document.getElementById('selectQuesCode'+q).value == checkArray[i]){
					document.getElementById('check'+q).checked = true;
					break;
				} //end of if		
			} //end of loop
	 	} //end of loop
	} //end of if	
	flag = true;
	for(var q=0;q<listCount;q++){
		if(!document.getElementById('check'+q).checked)
		{
			flag = false;
			document.getElementById("paraFrm_chkAll").checked = flag;
			break;
		} //end of if
	} //end of loop
	document.getElementById("paraFrm_chkAll").checked = flag;
	 
	
}catch(e){
		alert(e);
} //end fo catch	 
	
} //end of onLoad method

//========this function is called when any of the checkbox is checked=======//
function checkedbox(id){
		var checkedQues = document.getElementById('paraFrm_checkedQuestions').value;
		var checkArray = checkedQues.split(',');
		var k = checkedQues;
		var flag = "false";
		if(document.getElementById('check'+id).checked){
			for(var i=0;i<checkArray.length;i++){
				if(checkArray[i] == document.getElementById('check'+id).value){
					flag = "true";
					break;
				} //end of if
			} //end of loop
			if(flag == "false"){
				k +=document.getElementById('check'+id).value+",";
			} //end of if
		} //end of if 
		else{
			var uncheck = "";
			for(var i=0;i<checkArray.length;i++){
				if(checkArray[i] != document.getElementById('check'+id).value){
					uncheck += checkArray[i]+",";
				} //end of if
			} //end of loop
			k = uncheck.substring(0,uncheck.length - 1);
		} //end of else
		document.getElementById('paraFrm_checkedQuestions').value= k;
} //end of checkedbox method

//============this function is called when select all checkbox is checked======//
function callQuestionsAll()
{
try{
	var checkedQues = document.getElementById('paraFrm_checkedQuestions').value;
	var checkArray = checkedQues.split(',');
	var k = checkedQues;
	var count = document.getElementById("rec").value;
	if (document.getElementById("paraFrm_chkAll").checked){
		for(var idx=0;idx<count;idx++){
			var flag = "false";
		   document.getElementById("check"+idx).checked=true;
		  	if(checkedQues != ""){
			   for(var i=0;i<checkArray.length;i++){
					if(checkArray[i] == document.getElementById('check'+idx).value){
						flag = "true";
						break;
					} //end of if
			   } //end of loop
			} //end of if
			if(flag == "false"){
				k +=document.getElementById('check'+idx).value+",";
			} //end of if
		} //end of loop
	} //end of if
	else{
		for (var idx = 0; idx < count; idx++) {
			document.getElementById("check"+idx).checked = false;
			flag = "true";
			var val="";
			var uncheck = k;
			for(var i=0;i<checkArray.length;i++){
				if(checkArray[i] == document.getElementById('check'+idx).value){
					k = k.replace(checkArray[i]+',','');
				} //end of if
			} //end of loop
     	} //end of loop
  	} //end of else	 
  	document.getElementById('paraFrm_checkedQuestions').value= k;
  	}catch(e){
	alert(e);
}
} //end of callQuestionsAll method


//===================================this functions is used for paging=================================//

	function callPageText(id){
	try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			//clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoField').value;	 
		 	totalPage =document.getElementById('totalPage').value;	
		 	var actPage = document.getElementById('paraFrm_myPage').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoField').focus();
		     document.getElementById('pageNoField').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField').focus();
		     document.getElementById('pageNoField').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoField').focus();
		      return false;
	        }
	        
	         document.getElementById('paraFrm_myPage').value=pageNo;
		   	document.getElementById('paraFrm').target = "new";
			document.getElementById('paraFrm').action='TestTemplate_selectQuestion.action';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){
		alert(e);
	}
		
	}
	
	 function callPage(id,pageImg){ 
	 	try{ 
 	 pageNo =document.getElementById('pageNoField').value;	
 	 totalPage =document.getElementById('totalPage').value;	 
 	 var actPage = document.getElementById('paraFrm_myPage').value; 
	 if(pageNo!="0"  & pageNo<totalPage){	
		 document.getElementById('paraFrm_myPage').value=pageNo;
	   }
	   
 	 	    	
       if( pageImg !="F" & pageImg !="L" )
         { 
	 	   if(pageNo==""){
		        alert("Please Enter Page Number.");
		        document.getElementById('pageNoField').focus();
				 return false;
		        }
		  if(Number(pageNo)<=0)
			    {
			     alert("Page number should be greater than zero.");
			     document.getElementById('pageNoField').value=actPage;
			     document.getElementById('pageNoField').focus();
				 return false;
			    }
		  if(Number(totalPage)<Number(pageNo))
			    {
			     alert("Page number should not be greater than "+totalPage+".");
			      document.getElementById('pageNoField').value=actPage;
			     document.getElementById('pageNoField').focus();
				 return false;
			    }
		 }
		    	
       if(pageImg=="F")
         {
	        if(pageNo=="1"){
	         alert("This is first page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       }  
       
       	if(pageImg=="L")
         {
	        if(eval(pageNo)==eval(totalPage)){
	         alert("This is last page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       } 
       
        if(pageImg=="P")
       {
	        if(pageNo=="1"){
	         alert("There is no previous page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        }  
       }  
       if(pageImg=="N")
       {
	        if(Number(pageNo)==Number(totalPage)){
	         alert("There is no next page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        }  
       }  
       
		if(id=='P'){
		var p=document.getElementById('pageNoField').value;
		id=eval(p)-1;
		}
		if(id=='N'){
		var p=document.getElementById('pageNoField').value;
		id=eval(p)+1;
		} 
		document.getElementById('paraFrm_myPage').value=id;
		//document.getElementById('paraFrm_show').value=id;
		document.getElementById('paraFrm').target = "new"; 
		document.getElementById('paraFrm').action='TestTemplate_selectQuestion.action';
		document.getElementById('paraFrm').submit(); 
		}catch(e){
		alert(e);
	}
	}		
//===================================end of paging functions======================================//

//============this function is called when clicked on close button=========================//
function cancel(){
	window.close();
	opener.document.getElementById('paraFrm').target = "main";
} //end of cancel method  

//==========this function is called when clicked on the Add to list button=================//
function selectQues(){
	try{
		var checkedQues = document.getElementById('paraFrm_checkedQuestions').value;
		var subjectCodes = document.getElementById('hiddenSubject').value;
		var categoryCodes = document.getElementById('paraFrm_hiddenCategory').value;
		var defineDistribution = document.getElementById('paraFrm_correctCheck').value;
		if(checkedQues == ""){
			alert('Please select atleast one question from the list');
			return false;
		} //end of if
		 checkedQues += document.getElementById('paraFrm_addToListQuestions').value;
		 opener.document.getElementById('paraFrm_checkedQuestions').value = "";
		 opener.document.getElementById('hiddenSubject').value = subjectCodes;
		 opener.document.getElementById('paraFrm_hiddenCategory').value = categoryCodes;
		 opener.document.getElementById('paraFrm_addToListQuestions').value= checkedQues;
		 opener.document.getElementById('paraFrm_correctCheck').value = defineDistribution;
		 opener.document.getElementById('paraFrm_listFlag').value = false;
		 opener.document.getElementById('paraFrm_myPage').value = "1";
		 opener.document.getElementById('paraFrm').target='main';
		 opener.document.getElementById('paraFrm').action='TestTemplate_addQuestions.action'; 
		 opener.document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm_addToListQuestions').value= checkedQues;
		document.getElementById('paraFrm_myPage').value = "1";
		document.getElementById('paraFrm_checkedQuestions').value = "";
		document.getElementById('paraFrm').target = "new"; 
		document.getElementById('paraFrm').action='TestTemplate_selectQuestion.action';
		document.getElementById('paraFrm').submit(); 
		
	}catch(e){
		alert(e);
	} //end of catch
} //end of selectQues method
</script>