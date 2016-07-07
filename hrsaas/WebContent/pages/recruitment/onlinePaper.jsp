<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="OnlinePaper" validate="true" id="paraFrm"	theme="simple" >
	<table class="bodyTable" width="100%">
		<tr>
			<td width="4%" valign="bottom" class="txt"><strong
				class="formhead"><img
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="formhead">
			Online Paper Set </strong></td>

		</tr>
			<tr align="left" class="buttontr">
  		<td colspan="6">
  	
  		<s:submit cssClass="save" action="OnlinePaper_save"  theme="simple"  value="   Save   " onclick="return save();" />&nbsp;
  		<s:submit cssClass="reset"  action="OnlinePaper_reset" theme="simple"   value="    Reset  " />&nbsp;
  		<s:submit cssClass="delete"   action="OnlinePaper_delete" theme="simple"   value="   Delete  " onclick="return deletefun();"/>&nbsp;
  		
  		
  		 <input type="button"   theme="simple"  class="pagebutton" onclick="callReport('OnlinePaper_report.action')" value="  Report " />
  		    
  		   
  		
		</td>
		</tr>
			 <tr>
          <td colspan="4"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
            <tr>
              <td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="2">
		<tr>
			<td width="25%" colspan="1">Paper Code: 
			<td width="25%" colspan="2"><s:hidden name="onlinePaper.paperCode" /> 
			<img src="../pages/images/search.gif" height="18" align="middle"	width="18" theme = "simple" onclick="javascript:callsF9(500,325,'OnlinePaper_f9action.action');"> 
		
			<td  width="20%" colspan="2">Time Format should be hh:mm:ss(eg. 12:00:00(12 hours))</td>
			</td>
		</tr>
		<tr>
			<td width="25%" colspan="1" >Paper Name : </td>
			<td width="25%" colspan="1"><s:textfield size="25"  name="onlinePaper.paperName" maxlength="40"  onkeypress =" return charactersOnly();"/> </td>
			
		
			<td width="25%" colspan="1">Time Duration  : </td>
			<td width="25%" colspan="1"><s:textfield   size="25" name= "onlinePaper.paperTimeDuration"   maxlength="10" onkeypress="return numbersWithColonandDot();" />
			</td>
				
		
			</tr>
		<tr>
			<td width="25%" colspan="1">Passing Marks : </td>
			<td width="25%" colspan="1"><s:textfield size="25" name="onlinePaper.paperPassCrieteria" maxlength="10" onkeypress="return numbersOnly();" /> </td>
		
			<td width="25%" colspan="1">Total Number Of Question  : </td>
			<td width="25%" colspan="1"><s:textfield size="25" name="onlinePaper.totalNoOfQues" maxlength="10" onkeyup="Condition()"  onkeypress="return numbersOnly();" /> </td>
			</tr>
			<tr><td >No.Of Question Per Page  : </td>
			<td ><s:textfield size="25" name="onlinePaper.noOfQuestionPerPage" maxlength="10"  onkeyup="Condition()"onkeypress="return numbersOnly();" /> </td>
			
			</tr>
			</table></td>
						
		</tr>
			 </table></td>	
		<tr>
          <td colspan="4"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
            <tr>
              <td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="2">
              
			<tr><td width="25%" colspan="1">Subject : </td>	
							
			<td width="25%" colspan="1"><s:textfield size="25" name="onlinePaper.subject"  maxlength="10" onkeypress = " return charactersOnly();" readonly="true"/>
			<s:hidden name="onlinePaper.subjectCode" />
			<s:hidden name="onlinePaper.totalQue" />
			<s:hidden name="onlinePaper.subjectCodehdd" />
			<img src="../pages/images/search.gif" height="18" align="middle"	width="18" theme = "simple" onclick="javascript:callsF9(500,325,'OnlinePaper_f9actionSubject.action');">  </td>
			
			<td width="20%" colspan="1">No.Of Question : </td>
			<td width="25%" colspan="1"><s:textfield size="25" name="onlinePaper.noOfQuestion"  maxlength="10" onkeypress="return numbersOnly();" onkeyup="Condition();" /> </td>
			</tr>
			<tr>
			<td width="15%"> Hard Question  : </td>
			<td width="30%"><s:textfield size="25" name="onlinePaper.hardQuestion" maxlength="10" onkeypress="return numbersOnly();" onkeyup="Condition();"/> </td>
			
			<td > Medium Question  : </td>
			<td ><s:textfield size="25" name="onlinePaper.mediumQuestion" maxlength="10" onkeypress="return numbersOnly();" onkeyup="Condition();"/> </td>
			
			</tr>
			<tr>
			<td > Easy Question : </td>
			<td ><s:textfield size="25" name="onlinePaper.easyQuestion" maxlength="10" onkeypress="return numbersOnly();" onkeyup="Condition();"/> </td>
		     			
		     </tr>                                       
                
             <tr>
			<td >&nbsp;</td>
			<tr>
			<td ><s:submit  action="OnlinePaper_add" name="Add"   value="Add"   onclick="return add();" /> </td>
			
			<td ><s:if test="chk"><s:submit  name="Priview"  value="Preview"    onclick=" priview();" />
						
			</s:if> </td>
			</tr>      
            </table></td>  
            </tr></table></td>                             
           <tr>
          <td colspan="3"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
            <tr>
              <td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="2">         
           <tr>
          <td colspan="3"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
            <tr>
              <td class="formtext"><table width="100%" border="0" cellpadding="1" cellspacing="1" >
                <tr>
                  <td width="10%" class="formth">Sr.No</td>
                  <td class="formth">Subject</td>
                  <td class="formth">No.of Question</td>
                  <td class="formth">Hard Question</td>
                  <td class="formth">Medium Question</td>
                  <td class="formth">Easy Question</td>
               
                  <td class="formth"></td>
                </tr>                            
                 </tr>
                   <tr>
                  <% int i=0; %>
                     <s:iterator value="show" >
                     <tr>
                     <td width="10%">  <%=++i %><s:hidden name="srNo" /> </td>
           <td width="15%" ><s:property value="subItt" /><s:hidden name="subItt" /><s:hidden name="subjectCodeItt" /></td>
        
           <td width="15%" ><s:property value="numofItt" /><s:hidden name="numofItt" /></td>
           <td width="15%" ><s:property value="hardItt" /><s:hidden name="hardItt" /></td>
             <td width="14%" ><s:property value="mediumItt" /><s:hidden name="mediumItt" /></td>
          <td width="15%" ><s:property value="easyItt" /><s:hidden name="easyItt" /></td>
        
           <td  >
	      <input type="button" align="right" size ="10" class="edit" onclick="edit('<s:property value="subjectCodeItt" />','<s:property value="subItt" />','<s:property value="numofItt" />','<s:property value="hardItt" />','<s:property value="easyItt" />','<s:property value="mediumItt" />')"  value="     Edit"/>
	      <input type="button" align="right"  size ="10"  class="delete" onclick="deleteSubject('<%=i %>')"  value="   Delete"/>
	      </td>             
           </tr>                                              
                     
 </s:iterator>
 
 </table>             
              </td>
            </tr>
          </table></td>
        </tr>
  </table>
</br >
 
 </td></tr></table></td></tr></table>
 
   </s:form>
         
   <script type="text/javascript">   
   
 function save()
  {      
     var val=document.getElementById('paraFrm_onlinePaper_paperName').value;
  
  if(val=="")
  {
  alert ('Please Enter Paper Name:');
  
  return false;
	  }
	         
		
   var val2=document.getElementById('paraFrm_onlinePaper_paperPassCrieteria').value;	
 if(val2==""){
 
  alert('Please Enter Paper Passsing Marks:');
return false;
	}
      
   var val3=document.getElementById('paraFrm_onlinePaper_totalNoOfQues').value; 
 if(val3==""){
 
  alert('Please Enter Total No Of Question:');
return false;
	}
	   
   var val4=document.getElementById('paraFrm_onlinePaper_noOfQuestionPerPage').value; 
 if(val4==""){
 
  alert('Please Enter  No Of Question Per Page:');
return false;
}
    
     return true;
  
    }
function add()
	{
	
	            var noOfQuestionPerPage= document.getElementById('paraFrm_onlinePaper_noOfQuestionPerPage').value;
                var totalNoOfQues= document.getElementById('paraFrm_onlinePaper_totalNoOfQues').value;
                             
                if(eval(noOfQuestionPerPage)>eval(totalNoOfQues))
                  {
                  alert(" No Of Question PerPag Can not greater than Total No.of Question");
                  document.getElementById('paraFrm_onlinePaper_noOfQuestionPerPage').value;
                  return false;
                 }
                
	
	var val=document.getElementById('paraFrm_onlinePaper_paperName').value;
  
  if(val=="")
  {
  alert ('Please Enter Paper Name:');
  
  return false;
	  }
	var sub =document.getElementById('paraFrm_onlinePaper_subject').value;
	if(sub=="")
	{
	alert('Please Select Subject:');
	return false;
	}
	var sub1 =document.getElementById('paraFrm_onlinePaper_noOfQuestion').value;
	if(sub1=="")
	{
	alert('Please Enter No. of Question:');
	return false;
	}
	var sub2 =document.getElementById('paraFrm_onlinePaper_hardQuestion').value;
	if(sub2=="")
	{
	alert('Please Enter  No. of Hard Question:');
	return false;
		
	}
	var sub3 =document.getElementById('paraFrm_onlinePaper_mediumQuestion').value;
	if(sub3=="")
	{
	alert('Please Enter  No. of Medium Question:');
	return false;
	}
	var sub4 =document.getElementById('paraFrm_onlinePaper_easyQuestion').value;
	if(sub4=="")
	{
	alert('Please Enter  No. of Easy Question:');
	return false;
	} 
	             var question= document.getElementById('paraFrm_onlinePaper_noOfQuestion').value;
                 var hardQuestion= document.getElementById('paraFrm_onlinePaper_hardQuestion').value;
                 var mediumQuestion= document.getElementById('paraFrm_onlinePaper_mediumQuestion').value;
                 var easyQuestion= document.getElementById('paraFrm_onlinePaper_easyQuestion').value;
                 var totqs= document.getElementById('paraFrm_onlinePaper_totalQue').value;
                 var totalNoOfQues= document.getElementById('paraFrm_onlinePaper_totalNoOfQues').value;
	   
	   
	              var tot=eval(question)+eval(totqs); 
	               var total4=eval(hardQuestion)+eval(mediumQuestion)+eval(easyQuestion);
                
              if(eval(total4)!= eval(question))
                     {
                      alert(" Hard,Medium and easy Question is  not equal to  No.of Question");
                      document.getElementById('paraFrm_onlinePaper_hardQuestion').focus();
                      document.getElementById('paraFrm_onlinePaper_mediumQuestion').focus();
                      document.getElementById('paraFrm_onlinePaper_easyQuestion').focus();
                      return false;
                   }
              else if (tot>eval(totalNoOfQues))
                { 
                 alert(" You can not add more than Total No.of Question ");
                  document.getElementById('paraFrm_onlinePaper_noOfQuestion').focus();
                  return false;
                  }
              return true;
	}
	  	
    function deletefun()
            {
              var val= document.getElementById('paraFrm_onlinePaper_paperCode').value;
  
    if(val=="")
          {
           alert('Please Select Paper Code:');
           return false;
           }
    
       return true;
       }
     
    function Condition()
               {
     			var question= document.getElementById('paraFrm_onlinePaper_noOfQuestion').value;	
                var noOfQuestionPerPage= document.getElementById('paraFrm_onlinePaper_noOfQuestionPerPage').value;
                var totalNoOfQues= document.getElementById('paraFrm_onlinePaper_totalNoOfQues').value;
                var totqs= document.getElementById('paraFrm_onlinePaper_totalQue').value;	
                
                var mediumQuestion= document.getElementById('paraFrm_onlinePaper_mediumQuestion').value;
                var hardQuestion= document.getElementById('paraFrm_onlinePaper_hardQuestion').value;
                var easyQuestion= document.getElementById('paraFrm_onlinePaper_easyQuestion').value;
               
               var tot=eval(question)+eval(totqs);
                   
                if(eval(noOfQuestionPerPage)>eval(totalNoOfQues))
                  {
                  alert(" No Of Question PerPag Can not greater than Total No.of Question");
                 document.getElementById('paraFrm_onlinePaper_noOfQuestionPerPage').focus();
                  return false;
                 }            
                               
              else  if(eval( question)>eval(totalNoOfQues))
                  {              
                  
                   document.getElementById('paraFrm_onlinePaper_noOfQuestion').focus();
                   alert(" 'No Of Question  Can not greater than Total No.of Question'");
                   return false;
                  }
             
              else if (tot>eval(totalNoOfQues)){
                  
                  alert(" Total Subjects no. of Question can not  greater than Total No.of Question ");
                  document.getElementById('paraFrm_onlinePaper_noOfQuestion').focus();
                  return false;
                 }
           else  if(eval(hardQuestion)>eval(question))
                  {
                  alert(" Hard Question Can not greater than  No.of Question");
                  document.getElementById('paraFrm_onlinePaper_hardQuestion').focus();
                  return false;
                 }
             else  if(eval(mediumQuestion)>eval(question))
                  {
                   alert(" Medium Question Can not greater than  No.of Question");
                   document.getElementById('paraFrm_onlinePaper_mediumQuestion').focus();
                   return false;
                   }
              
              else  if(eval(easyQuestion)>eval(question))
                    {
                     alert(" Easy Question Can not greater than  No.of Question");
                     document.getElementById('paraFrm_onlinePaper_easyQuestion').focus();
                    return false;
                   }
                       
                   var total1=eval(hardQuestion)+ eval(mediumQuestion);
                 
              if(eval(total1)>eval(question))
                     {
                       alert(" Hard and Medium Question Can not greater than  No.of Question");
                       document.getElementById('paraFrm_onlinePaper_hardQuestion').focus();
                       document.getElementById('paraFrm_onlinePaper_mediumQuestion').focus();
                     
                      return false;
                                    
                     } 
                  
                
                    var total2=eval(mediumQuestion)+eval(easyQuestion);
                 
             if(eval(total2)>eval(question))
                      {
                      alert("  Medium and Easy Question Can not greater than  No.of Question");
                     document.getElementById('paraFrm_onlinePaper_mediumQuestion').focus();
                     document.getElementById('paraFrm_onlinePaper_easyQuestion').focus();
                  
                      return false;
                      }
                       
                   var total3=eval(easyQuestion)+eval(hardQuestion);
                 
              if(eval(total3)>eval(question))
                      {
                      alert(" Hard and easy Question Can not greater than  No.of Question");
                      document.getElementById('paraFrm_onlinePaper_easyQuestion').focus();
                      document.getElementById('paraFrm_onlinePaper_hardQuestion').focus();
                   
                      return false;
                      }
                                            
                 var total4=eval(hardQuestion)+eval(mediumQuestion)+eval(easyQuestion);
                 
                if(eval(total4)>eval(question)) 
                      {
                      alert(" Hard,Medium and easy Question Can not greater than  No.of Question");
                      document.getElementById('paraFrm_onlinePaper_hardQuestion').focus();
                      document.getElementById('paraFrm_onlinePaper_mediumQuestion').focus();
                      document.getElementById('paraFrm_onlinePaper_easyQuestion').focus();
                      return false;
                      } 
                     
                    return true;  
                            
                }                                               
           function edit(subCode,subName,NoOfQuestion,hardQuestion,MediumQuestion,EasyQuestion)
            {
      
    		 var aa=document.getElementById('paraFrm_onlinePaper_subjectCodehdd').value=subCode;
    		 document.getElementById('paraFrm_onlinePaper_subject').value=subName;
             document.getElementById('paraFrm_onlinePaper_noOfQuestion').value=NoOfQuestion;
             document.getElementById('paraFrm_onlinePaper_hardQuestion').value=hardQuestion;
             document.getElementById('paraFrm_onlinePaper_mediumQuestion').value=MediumQuestion;
             document.getElementById('paraFrm_onlinePaper_easyQuestion').value=EasyQuestion;
       
           }
       function deleteSubject(subCode)
                     {
                  var conf=confirm("Are you sure  you want to delete this Subject ?");
  	   if(conf)
                  {
                     
    				 document.getElementById('paraFrm_onlinePaper_subjectCodehdd').value=subCode;
	                 document.getElementById("paraFrm").action="OnlinePaper_deleteSubject.action";
	                 document.getElementById("paraFrm").submit();
                     
                 }
        else 
		      return false;
            }

    function priview()
          {      
            var val=document.getElementById('paraFrm_onlinePaper_paperName').value;
  
          if(val=="")
          {
           alert ('Please Enter Paper Name:');
  
           return false;
	  }
	 
   var id=document.getElementById('paraFrm_onlinePaper_paperCode').value;
   var page=document.getElementById('paraFrm_onlinePaper_noOfQuestionPerPage').value;
//window.open('/pims/master/OnlinePaperPriview_priview.action?sss='+id+'&bbb='+page,'','width=800,height=500,scrollbars=yes,resizable=yes,top=50,left=100');

 var wind = window.open('','wind','width=700,height=400,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
		document.getElementById('paraFrm').target = "wind";
		document.getElementById('paraFrm').action = 'OnlinePaperPriview_priview.action?sss='+id+'&bbb='+page;
		document.getElementById('paraFrm').submit();
		//document.getElementById('paraFrm').target = "main";
//window.open('/pims/master/OnlinePaper_input_newPage.action','width=700,height=300,scrollbars=no,resizable=no,top=50,left=100');

}
 </script>
 

  
    
    
    
    
    
    
    
    
   
  


