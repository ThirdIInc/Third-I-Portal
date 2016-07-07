<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<link rel="stylesheet" type="text/css" title="default-theme"
	href="../pages/common/css/default/default.css" />
</head>
<body>
<s:form  id="paraFrm">
<s:hidden name="cntHard"/>
	<s:hidden name="cntMedium"/>
	<s:hidden name="cntEasy"/>
	<s:hidden name="totalnoOfQus"/>
	<s:hidden name="correctCheck"/>
	<s:hidden name="markHard"/>
	<s:hidden name="markMedium"/>
	<s:hidden name="markEasy"/>
	<s:hidden name="totalMarkss"/>
	<s:hidden name="marksForHard"/>
	<s:hidden name="marksForMedium"/>
	<s:hidden name="marksForEasy"/>
	<s:hidden name="testTotalMarks"/>
	<s:hidden name="myPage" id="myPage" />
	<s:hidden name="testType" />
	<s:hidden name="category"/>
	<s:hidden name="compLevel" />
	<s:hidden name="compLevel1" />
	<s:hidden name="subject" />
	<s:hidden name="hardCompLevel" />
	<s:hidden name="mediumCompLevel" />
	<s:hidden name="easyCompLevel" />
	<s:hidden value="selectQuesCode"/>	
	<s:hidden name="hiddenQuestionCode"/>
	<s:hidden name="hiddenCode"/>
	<s:hidden name="totalNoQue"/>
	 
	
<table class="formbg" width="100%">
	<tr>
          	<td colspan="3">
          		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">  
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
				</table>
			</td>
	</tr>
	
	<tr>
          <td colspan="3"><table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td width="78%">
              	<input type="button" class="token" name="Ok" value=" Ok " onclick="selectQues();"/>	
              	<input type="button" class="token" name="Back" value=" Back " onclick="history.go(-1);"/>						
              </td>
              <td width="22%"><div align="right"></td>
            </tr>
          </table><label></label></td>
   </tr>
        
        <tr>
			<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
					 <tr>
				      <td width="100%" colspan="5"><strong class="forminnerhead">View Cart Question List</strong></td>
				   </tr>
					<tr>	
						
						
						<td width="6%" valign="top" class="formth"><b>Sr No</b></td>
						<td width="25%" valign="top" class="formth"><b>Subject-Category</b></td>
						<td width="30%" valign="top" class="formth"><b>Question Name</b></td>
						<td width="15%" valign="top" class="formth"><b>Complexity Level</b></td>
						<td width="10%" valign="top" class="formth"><input type="checkbox" name="chkAll" id="paraFrm_chkAll"  onclick="return callQuestionsAll();"/><b>Select</b></td>
					</tr>
					
							
					<%int l = 0;
					  int k = 0;
					%>
				
						<s:iterator  value="quesDataList">
							<tr>		
								<input type="hidden" name="selectQuesCode" id="selectQuesCode<%=l%>"  value='<s:property value="selectQuesCode"/>'/>
								<td class="border2" width="6%"><%=++k%>&nbsp;</td>
								<td class="border2" width="25%">
									<input type="hidden" name="quesSubject" value='<s:property value="quesSubject"/>' id="quesSubject<%=l%>"/>
										<s:property value="quesSubject"/>&nbsp;</td>
								<td class="border2" width="30%">
									<input type="hidden" name="quesName" value='<s:property value="quesName"/>' id="quesName<%=l%>"/>
										<s:property value="quesName"/>&nbsp;</td>
								<td class="border2" width="15%">
									<input type="hidden" name="complexicity" value='<s:property value="complexicity"/>' 
										id="complexicity<%=l%>"/><s:property value="complexicity"/>&nbsp;</td>
								<td class="border2" width="10%">
								<input type="checkbox"  name="check" id="check<%=l%>"
								onclick="return checkedbox('<%=l%>');" />
								<input type="hidden" name="checkFlag" id="checkFlag<%=l%>" />
								</td>	
								
							</tr>
								<%l++;%>					
						</s:iterator>	
				
						<input type="hidden" name="rec" id="rec" value='<%=(l)%>' />
						
					</table>
				</td>
			</tr>
		   <tr>
          	<td colspan="3">
          		<table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td width="78%">
              	<input type="button" class="token" name="Add Questions" value=" Ok " onclick="selectQues();"/>              								
              	<input type="button" class="token" name="Back" value=" Back " onclick="history.go(-1);"/>
              </td>
              <td width="22%"><div align="right"></td>
            </tr>
          </table><label></label></td>
   </tr>
   	</table>
   	
   </s:form>
</body>
</html>
<script>
fun();
function fun(){
opener.document.getElementById("paraFrm_hiddenQuCodeWithStar").value=document.getElementById("paraFrm_hiddenQuCodeWithStar").value;
}
var chk=0;


function selectQues(){


	var count = document.getElementById('rec').value;
	//alert("count"+count);
	if(count == 0){
			alert('Please select atleast one question from the list');
			return false;
		}					
		var chkFlag    = false;
	//alert("count"+count);
	for(var i=0; i<count; i++){
		//alert("in for loop"+i);
		//alert("In if loop check...."+i+"---->"+document.getElementById('check'+i).checked);
		
		if(document.getElementById('check'+i).checked == true){
		   chkFlag = true;
		
		}			
	}
	
	
	//alert("chkFlag........"+chkFlag);
	if(!chkFlag){
			alert('Please select atleast one question from the list');
			return false;
	}
	
	var isChecked = "";
	var selectedQuesCode = "";
	var selectedQuesName = "";
	var complex = "";
	var subcat="";
	var noofQues = 0;
	var totalMarks = 0;
	var cntHard=0;
	var cntMedium=0;
	var cntEasy=0;
	var totalnoOfQus=0;
	
	var MarkEasy=0;
	var MarkMedium=0;
	var MarkHard=0;
	var totalMarkss=0
	
	var correctCheck   = document.getElementById("paraFrm_correctCheck").value;
	//alert("correctCheck......"+correctCheck);
	var marksForHard   = document.getElementById("paraFrm_marksForHard").value;
	//alert("marksForHard......"+marksForHard);
	var marksForMedium = document.getElementById("paraFrm_marksForMedium").value;
	//alert("marksForMedium......"+marksForMedium);
	var marksForEasy   = document.getElementById("paraFrm_marksForEasy").value;
	//alert("marksForEasy......"+marksForEasy);
	var testTotalMarks = document.getElementById("paraFrm_testTotalMarks").value; 
	//alert("testTotalMarks......"+testTotalMarks);
	
	//alert("correctCheck........."+correctCheck);
	
	
	
	try{
   		for(var i=0; i<count; i++){
   			isChecked = document.getElementById('check'+i).checked;
   			
   			
   			
   			if(isChecked == true){
   				var complexicity = document.getElementById("complexicity"+i).value;
   				
	   			selectedQuesCode += document.getElementById('selectQuesCode'+i).value+",";
	   			selectedQuesName += i+". "+document.getElementById('quesName'+i).value+",\n";
	   			complex += document.getElementById('complexicity'+i).value+",";
	   			subcat += document.getElementById('quesSubject'+i).value+",";	   			
	   			noofQues = noofQues+1;
	   			
	   			//alert("complexicity....."+complexicity);
	   			if(correctCheck){
	   				if(complexicity == "Easy"){
	   					cntEasy++;
	   					totalMarks += eval(marksForEasy);
	   				}
	   				else if(complexicity == "Hard"){
	   					cntHard++;
	   					totalMarks += eval(marksForHard);
	   				}
	   				else if(complexicity == "Medium"){
	   					cntMedium++;
	   					totalMarks += eval(marksForMedium);
	   				}
	   			}
   			}
   		}
   		MarkEasy=cntEasy*marksForEasy;
   		MarkMedium=cntMedium*marksForMedium;
   		MarkHard=cntHard*marksForHard;
   		
   		//alert("MarkEasy...."+MarkEasy);
   		//alert("MarkMedium...."+MarkMedium);
   		//alert("MarkHard...."+MarkHard);
   		
   		totalMarkss=MarkEasy+MarkMedium+MarkHard;   		
   		totalnoOfQus=cntEasy+cntHard+cntMedium;
   		
	   	selectedQuesCode = selectedQuesCode.substring(0, selectedQuesCode.length-1);
	   	selectedQuesName = selectedQuesName.substring(0, selectedQuesName.length-1);
	   	complex = complex.substring(0, complex.length-1);
	   	subcat = subcat.substring(0, subcat.length-1);
	   	
	   /*	if(eval(noofQues) != eval(document.getElementById("paraFrm_totalNoQue").value)){
	   		alert("Selected questions should be equal to "+opener.document.getElementById("total.ques").innerHTML.toLowerCase());
	   		return false;
	   	}*/
	   	
	   	/*if(eval(testTotalMarks) != eval(totalMarks)){
	   		alert(opener.document.getElementById("total.marks").innerHTML.toLowerCase()+" should be equal to sum of marks for selected questions");
     		return false;
	   	}*/
	   
	   //	alert("selectedQuesCode......."+selectedQuesCode+"selectedQuesName...."+selectedQuesName+"complex....."+complex+"subcat......"+subcat);
	   
		opener.document.getElementById('paraFrm_selectQuCode').value = selectedQuesCode;
		opener.document.getElementById('paraFrm_qusName').value = selectedQuesName;
		opener.document.getElementById('paraFrm_complexicity').value=complex;		
		opener.document.getElementById('paraFrm_qusSubject').value=subcat;
		opener.document.getElementById('paraFrm_cnt').value=noofQues;
		opener.document.getElementById('hiddenSubject').value=document.getElementById('paraFrm_subject').value;
		opener.document.getElementById('hiddenCategory').value=document.getElementById('paraFrm_category').value;
		
		opener.document.getElementById('paraFrm_cntHard').value =cntHard;
		opener.document.getElementById('paraFrm_cntMedium').value=cntMedium;
		opener.document.getElementById('paraFrm_cntEasy').value=cntEasy;
		opener.document.getElementById('paraFrm_totalnoOfQus').value=totalnoOfQus;
		
		opener.document.getElementById('paraFrm_markEasy').value=MarkEasy;
		opener.document.getElementById('paraFrm_markMedium').value=MarkMedium;
		opener.document.getElementById('paraFrm_markHard').value=MarkHard;
		opener.document.getElementById('paraFrm_totalMarkss').value=totalMarkss;
		
		
		opener.document.getElementById('paraFrm').target='main';
		opener.document.getElementById('paraFrm').action='TestTemplate_addQuestions.action'; 
		opener.document.getElementById('paraFrm').submit();
		window.close();
	}
	catch(e){
   		alert(e);
   	}
	window.close();
}

function callQuestionsAll()
  {
	 	  
		
		var count = document.getElementById("rec").value;
	
		if (document.getElementById("paraFrm_chkAll").checked){
			
		  for(var idx=0;idx<count;idx++){
		   document.getElementById("check"+idx).checked ="true";
		  // document.getElementById('hdeleteCode'+idx).value=subjectArray[idx-1];//alert("hhh"+skillArray.length);
		  
		  }
			
			
	     }
	    else{
			for (var idx = 0; idx < count; idx++) {
	
			document.getElementById("check"+idx).checked = false;	
     		}
  		}	 
  		
		
  }    


function checkedbox(i)
{
		var isChecked = document.getElementById('check'+i).checked;
			if(isChecked == true){
				document.getElementById('checkFlag'+i).value = "Y";
   			}else{
   				document.getElementById('checkFlag'+i).value = "N";
   				}
   				
   				var NoOfRec= document.getElementById('rec').value;
   				var count=0;
   				if(NoOfRec!=null && NoOfRec>0){
   				 for(var j=0;j<NoOfRec;j++){
   				 		if(document.getElementById('checkFlag'+j).value=="Y"){
   				 			count++;
   				 		}else{
   				 		document.getElementById('chkAll').checked=false;
   				 		return;
   				 		
   				 		}
   					}
   					
   					
   					if(count == NoOfRec){   					
   					 document.getElementById('chkAll').checked=true;
   					}
   				}
}
</script>