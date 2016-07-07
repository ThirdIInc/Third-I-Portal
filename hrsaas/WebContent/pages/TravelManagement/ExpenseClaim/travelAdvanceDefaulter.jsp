
<script type="text/javascript"> 
  var DeftArr = new Array(); 
</script>
 <%@ taglib prefix="s" uri="/struts-tags"%>  
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form name="paraFrm" theme="simple" id="paraFrm" method="post" action="AdvanceDefaulter">

 
	<table width="100%" border="0" align="right" cellpadding="0" class="formbg"
		cellspacing="0"> 
		<tr>
			<td colspan="3" valign="bottom" class="txt"><img
				src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
		</tr>
		<tr>
			<td valign="bottom" class="txt"><strong class="formhead"><img
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="formhead">Travel Advance Settlement Defaulter</strong></td>
			<td width="3%" valign="top" class="txt">
			<div align="right"><img
				src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
			</td>
		</tr>
		<tr>
			<td height="5" colspan="3"><img
				src="../pages/images/recruitment/space.gif" width="5" height="7" /></td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
				<tr>
					<td width="78%">    <input type="button" value="Search"  class="token" onclick="callForF9();" >  </td>
                
					<td width="22%">
					
					<div align="right"><font color="red">*</font> Indicates Required</div>
					</td>
				</tr>
			</table>
			 </td>
		</tr> 
		<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td colspan="1" width="80%">
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="2" >
							<tr>
								<td width="20%" colspan="5"><STRONG> <label  class = "set" name="tourAdvanceDur"  id="tourAdvanceDur" ondblclick="callShowDiv(this);"><%=label.get("tourAdvanceDur")%></label>    <s:hidden name="processDate"  />  </STRONG></td>

							</tr>
							 
							 <tr> 
								   <td height="27" class="formtxt" colspan="1" width="10%" ><label  class = "set" name="laFromDate"  id="laFromDate" ondblclick="callShowDiv(this);"><%=label.get("laFromDate")%></label><font color="red">*</font> :</td>
								   <td width="25%" colspan="1"><s:textfield name="fromDate" size="10" onkeypress="return numbersWithHiphen();" theme="simple"
										onblur="return validateDate('paraFrm_fromDate','Date');" maxlength="10" /> 
										<s:a href="javascript:NewCal('paraFrm_fromDate','DDMMYYYY');">
										<img src="../pages/images/recruitment/Date.gif" class="iconImage" height="16" align="absmiddle" width="16"> </s:a>
								  <s:hidden name ="advHeaderId"/>
								   </td>
								   <td height="27" class="formtxt"  width="10%" colspan="1"><label  class = "set" name="laTodate"  id="laTodate" ondblclick="callShowDiv(this);"><%=label.get("laTodate")%></label><font color="red">*</font> :</td> 
								   <td width="25%" colspan="1"><s:textfield name="toDate" size="10" onkeypress="return numbersWithHiphen();" theme="simple"
										onblur="return validateDate('paraFrm_toDate','Date');" maxlength="10" /> 
										<s:a href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
										<img src="../pages/images/recruitment/Date.gif" class="iconImage" height="16" align="absmiddle" width="16"> </s:a>
								   </td> 
								    <td height="27" class="formtxt" colspan="1">	<input type="button" value="Go"   class="token" onclick="callForGo();"  > 
						             <s:hidden name="dispDataFlag"/> 
								    </td>
								</tr>
								
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>  
		<s:if test="dispDataFlag">
			<tr>
			  <td width="100%" colspan="4">
								<table class="formbg" width="100%">
								   <tr>
										<td width="5%" colspan="1" class="formth"><label  class = "set" name="SrNo" id="SrNo" ondblclick="callShowDiv(this);"><%=label.get("SrNo")%></label></td>
										<td width="25%" colspan="1" class="formth"><label  class = "set"  name="empName" id="empName" ondblclick="callShowDiv(this);"><%=label.get("empName")%></label></td>
										<td width="25%" colspan="1" class="formth"><label  class = "set"  name="trRequest" id="trRequest" ondblclick="callShowDiv(this);"><%=label.get("trRequest")%></label></td>
										<td width="10%" colspan="1" class="formth"><label  class = "set" name="advanceDate"  id="advanceDate" ondblclick="callShowDiv(this);"><%=label.get("advanceDate")%></label></td>
										<td width="10%" colspan="1" class="formth"><label  class = "set"  name="advanceAmt" id="advanceAmt" ondblclick="callShowDiv(this);"><%=label.get("advanceAmt")%></label></td>
										<td width="10%" colspan="1" class="formth"><label  class = "set"  name="expSettleDate" id="expSettleDate" ondblclick="callShowDiv(this);"><%=label.get("expSettleDate")%></label></td>
										<td width="10%" colspan="1" class="formth"><label  class = "set" name="pendingDays"  id="pendingDays" ondblclick="callShowDiv(this);"><%=label.get("pendingDays")%></label></td>
										<td width="5%" colspan="1" class="formth"> <input type="checkbox" name="selectAllDefaulter"  <s:property value="selectAllDefaulter"/>  id="selectAllDefaulter" 	onclick="callSelectForDefaulter();"> </td>
									</tr>
									
									
								<!-- 	<tr>
										<td width="5%" colspan="1" class="formth">Sr.No</td>
										<td width="25%" colspan="1" class="formth">Employee Name</td>
										<td width="25%" colspan="1" class="formth">Travel request Name</td>
										<td width="10%" colspan="1" class="formth">Advance Payment Date </td>
										<td width="10%" colspan="1" class="formth">Advance Amount</td>
										<td width="10%" colspan="1" class="formth"> Expected Settlement Date</td>
										<td width="10%" colspan="1" class="formth">Pending Since (Days)</td>
										<td width="5%" colspan="1" class="formth"><input type="checkbox" name="selectAllDefaulter"  <s:property value="selectAllDefaulter"/>  id="selectAllDefaulter" 	onclick="callSelectForDefaulter();"></td>
									</tr>
									-->
									<% int i=1;%>
									<% int j=0;%>
								 
								  <s:iterator value="advDftList">
								  <tr>
								  
								  <s:hidden name="empName"/>  <input type="hidden" name="trRequest" id="trRequest<%=i%>" value='<s:property value="trRequest"/>' >  <s:hidden name="advanceDate" /> 
								  <s:hidden name="advanceAmt" />  <s:hidden name="expSettleDate" />  <s:hidden name="pendingDays" /> 
								  <s:hidden name="trAppId" />  <s:hidden name="trEmpId" />   
										<td width="5%" colspan="1" class="border2"><%=i%></td>
										<td width="25%" colspan="1" class="border2"><s:property value="empName" />  </td>
										<td width="25%" colspan="1" class="border2"><s:property value="trRequest" /> </td>
										<td width="10%" colspan="1" class="border2"><s:property value="advanceDate" /></td>
										<td width="10%" colspan="1" class="border2"><s:property value="advanceAmt" /> </td>
										<td width="10%" colspan="1" class="border2"><s:property value="expSettleDate" /> </td>
										<td width="10%" colspan="1" class="border2"> <s:property value="pendingDays" /></td>
										<td width="5%" colspan="1" class="border2"> 
										<input 	type="checkbox" name="itDefaultChk" id="itDefaultChk<%=i%>"   <s:property value="itDefaultChk"/> 
										onclick="callForSingleDefault('<%=i%>');"> 
												<input 	type="hidden" name="hidDefaultChk" value='<s:property value="hidDefaultChk"/>' id="hidDefaultChk<%=i%>"> 
										 </td>
									</tr>
									<script>	
		                                  DeftArr[<%=j%>] = document.getElementById('trRequest'+<%=i%>).value;   
		                            </script>
		                            <%j++; %>		                            
									<%i++;%>
								  </s:iterator>
								</table>
								</td>
							</tr>
				</s:if>
						</table>
						</td>
					</tr>
					
		<tr>
			<td height="5" colspan="3"><img
				src="../pages/images/recruitment/space.gif" width="5" height="7" /></td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg" >
				<tr>
					<td width="20%"> Salary Month :  </td> 
					<td width="20%">
					<s:select name="salMonth" headerKey="0" headerValue="Select"  list="#{'1':'Jan','2':'Feb','3':'Mar','4':'Apr','5':'May','6':'June','7':'July','8':'Aug','9':'Sep' ,'10':'Oct','11':'Nov','12':'Dec'}"  />
				         </td>
					<td width="20%"> Salary Year :  </td> 
					<td width="20%"><s:textfield name="salYear"  size="10" maxlength="4" theme="simple" /></td>
					<td width="20%">  <s:hidden name ="processFlag"/> 
					<s:if test="processFlag">
					<input type="button" value="Reprocess"  class="token" onclick="callForReprocess();"> 
					</s:if>
					<s:else>
					<input type="button" value="Process"  class="token" onclick="callForProcess();"> 
					</s:else> 
					</td>
				</tr>
			</table>
			<label></label></td>
		</tr>
	</table> 
</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>  
<script>   
  
    function callForGo()
     {  
      fromDate = document.getElementById('paraFrm_fromDate').value; 
      toDate = document.getElementById('paraFrm_toDate').value; 
      if(fromDate=="")  {
       alert("Please enter From Date.");
       document.getElementById('paraFrm_fromDate').focus();
       return false;
      }
      
      if(toDate=="")   {
       alert("Please enter To Date.");
       document.getElementById('paraFrm_toDate').focus();
       return false;
      } 
       if(!dateDifference(fromDate, toDate,"paraFrm_fromDate", "From Date", "To Date"))
	       {
	        return false;
	       } 
         document.getElementById('paraFrm').action = "AdvanceDefaulter_goForAdvance.action";
		 document.getElementById('paraFrm').submit();
		 document.getElementById('paraFrm').target ="main"; 
    }   
    
    
    function callForProcess()
     {   
      fromDate = document.getElementById('paraFrm_fromDate').value; 
      toDate = document.getElementById('paraFrm_toDate').value; 
      salMonth = document.getElementById('paraFrm_salMonth').value; 
      salYear = document.getElementById('paraFrm_salYear').value; 
      
       if(fromDate=="")  {
       alert("Please enter From Date.");
       document.getElementById('paraFrm_fromDate').focus();
       return false;
      }
      
      if(toDate=="")   {
       alert("Please enter To Date.");
       document.getElementById('paraFrm_toDate').focus();
       return false;
      } 
      if(!dateDifference(fromDate, toDate,"paraFrm_fromDate", "From Date", "To Date"))
       {
        return false;
       } 
       
       if(salMonth=="0")
       {
        alert("Please select salary month.");
        document.getElementById('paraFrm_salMonth').focus();
        return fasle;
       }
       
        if(salYear=="")
       {
        alert("Please enter salary year.");
        document.getElementById('paraFrm_salYear').focus();
        return fasle;
       } 
        if(DeftArr.length>0)
         {
	           chkFlag="false";
	           var j =1;
			    for(i=0;i<DeftArr.length;i++)
			    {   
				      if(document.getElementById('hidDefaultChk'+j).value=='Y')
				      {
				       chkFlag ="true";
				       break;
				      } 
			     j++;
			    }
			    
			    if(chkFlag=="false"){
			    alert("Please select at least on checkbox.");
			    return false;
			    }
			     
		  }else
		  {
		   alert("There is record for process,please click on Go Button.");
		   return false;
		  }
	       
       var conf=confirm('Do you really want to Process.');
      if(conf==true){ 
         document.getElementById('paraFrm').action = "AdvanceDefaulter_process.action";
		 document.getElementById('paraFrm').submit();
		 document.getElementById('paraFrm').target ="main"; 
      
     }else
      {
        return false;
      } 
    }   
    
      function callForReprocess()
     {   
     
		    fromDate = document.getElementById('paraFrm_fromDate').value; 
		    toDate = document.getElementById('paraFrm_toDate').value; 
		    salMonth = document.getElementById('paraFrm_salMonth').value; 
		    salYear = document.getElementById('paraFrm_salYear').value; 
		    
          if(fromDate=="")  {
		       alert("Please enter From Date.");
		       document.getElementById('paraFrm_fromDate').focus();
		       return false;
          }
      
           if(toDate=="")   {
		       alert("Please enter To Date.");
		       document.getElementById('paraFrm_toDate').focus();
		       return false;
             } 
	      if(!dateDifference(fromDate, toDate,"paraFrm_fromDate", "From Date", "To Date"))
	       {
	          return false;
	       } 
       
         if(salMonth=="0")
          {
	        alert("Please select salary month.");
	        document.getElementById('paraFrm_salMonth').focus();
	        return fasle;
          }
       
         if(salYear=="")
           {
	        alert("Please enter salary year.");
	        document.getElementById('paraFrm_salYear').focus();
	        return fasle;
        } 
        if(DeftArr.length>0)
         {
	           chkFlag="false";
	           var j =1;
			    for(i=0;i<DeftArr.length;i++)
			    {   
				      if(document.getElementById('hidDefaultChk'+j).value=='Y')
				      {
				       chkFlag ="true";
				       break;
				      } 
			     j++;
			    }
			    
			    if(chkFlag=="false"){
			    alert("Please select at least on checkbox.");
			    return false;
			    } 
		  }else
		  {
		   alert("There is record for process,please click on Go Button.");
		   return false;
		  } 
		       
	       var conf=confirm('Do you really want to Reprocess.');
	       if(conf==true){ 
	         document.getElementById('paraFrm').action = "AdvanceDefaulter_reProcess.action";
			 document.getElementById('paraFrm').submit();
			 document.getElementById('paraFrm').target ="main"; 
	      
	       }else
	       {
	         return false;
	       }  
     }  
 
    
     function callForF9()
     {  
          callsF9(500,325,'AdvanceDefaulter_f9Action.action');
    }   
    
    function callSelectForDefaulter()
{

	 if(document.getElementById('selectAllDefaulter').checked)
	  {   
	 
	    var j =1;
		    for(i=0;i<DeftArr.length;i++)
		    {  
		      document.getElementById('itDefaultChk'+j).checked='true' 
		     document.getElementById('hidDefaultChk'+j).value='Y';
		      
		     j++;
		    }
	  }else
	  {
	       var j =1;
		    for(i=0;i<DeftArr.length;i++)
		    { 
		      document.getElementById('itDefaultChk'+j).checked=false
		      document.getElementById('hidDefaultChk'+j).value='N' ;
		     j++;
		    } 
	  }
 }	
    
 function callForSingleDefault(id)
    {  
      
     if(document.getElementById('itDefaultChk'+id).checked)
      {  
        document.getElementById('hidDefaultChk'+id).value='Y';
      }else
       { 
         document.getElementById('hidDefaultChk'+id).value='N';
       }
     
       //========== for main chk box===========
        var flag ="checked";
          var j =1;
		    for(i=0;i<DeftArr.length;i++)
		    {   
			    if(document.getElementById('hidDefaultChk'+j).value=='N')
			    {
			     flag ="unChecked";
			     break;
			    }  
		     j++;
		    }
		    
		    if(flag =="unChecked")
		    {
		      document.getElementById('selectAllDefaulter').checked=false; 
		    }else
		    {
		     document.getElementById('selectAllDefaulter').checked=true;
		      
		    }
		    
    }
   
    	 
</script>

