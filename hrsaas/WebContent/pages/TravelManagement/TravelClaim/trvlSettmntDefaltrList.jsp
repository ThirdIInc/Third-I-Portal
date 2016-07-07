<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript" src="../pages/common/include/javascript/sorttable.js"></script>
<s:form action="TravelClaimDefaulter" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="decPage"/>
	<s:hidden name="myPage"/>
	<s:hidden name="noData"/>
	<s:hidden name="deductFlag"/>
	<s:hidden name="appId"/><s:hidden name="appCode"/><s:hidden name="appDate"/><s:hidden name="startDate"/>
<s:hidden name="defaulterStatus"/><s:hidden name="gradeId"/><s:hidden name="endDate"/>
	
	
	<table class="formbg" width="100%">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">
					Travel Advance Settlement Defaulter List</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img	src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="100%">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td height="27" class="formtxt"><a
								href="TravelClaimDefaulter_input.action">Travel Advance Settlement Defaulter List
							</a> | <a
								href="TravelClaimDefaulter_closedDefaltr.action">Travel Advance Settlement Closed Defaulter List</a></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		 <tr>
          <td colspan="3">
          <table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
              <tr>
              <td width="100%">
              
              <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
                	<tr>
                    <td height="27" class="text_head">
                    	<strong>
                    	<%
                    			String str=(String)request.getAttribute("flag");
                    			out.println(str);
                   
                    	%>
                      	</strong></td>
	                    <%
					    int totalPage = (Integer) request.getAttribute("totalPage");
					    int pageNo = (Integer) request.getAttribute("PageNo");
					%>	
					
				<s:if test="noData"></s:if><s:else>	
	                <td align="right">
						
						
						<b>Page:</b> 
						 <input type="hidden" name="totalPage"  id="totalPage" value="<%=totalPage%>">
						 	<a href="#" onclick="callPage('1','F');"  >
										<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
									</a>&nbsp;
									<a href="#" onclick="callPage('P','P');" >
										<img  title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
									</a> 
							    <input type ="text" name="pageNoField" id="pageNoField" theme="simple" size="3"  value="<%= pageNo%>"  onkeypress="callPageText(event);return numbersOnly()"   maxlength="4" /> of <%=totalPage%>
						 		 		<a href="#" onclick="callPage('N','N')" >
											<img  title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
										</a>&nbsp;
										<a href="#" onclick="callPage('<%=totalPage%>','L');" >
											<img title="Last Page"  src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
										</a>
						</td>
						</s:else>
                  </tr>
                  </table>              
              </td>
		   </tr>
		      
		       <tr>
              <td width="100%">
              <table width="100%" border="0" cellpadding="0" cellspacing="0" id="tblSettlement">
	                        <tr >
	                          <td width="5%"  valign="top" class="formth" nowrap="nowrap"><b>
	                      	<label class="set" name="serial.no" id="serial.no"
								ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b></td>
	                          <td width="18%" valign="top" class="formth"><b>
	                          	<label  class = "set" name="empName" id="empName" 
						            ondblclick="callShowDiv(this);"><%=label.get("empName")%></label></b></td>
	                          <td width="18%" valign="top" class="formth" ><b>
	                          	<label  class = "set" name="trvlRqeName" id="trvlRqeName" 
						            ondblclick="callShowDiv(this);"><%=label.get("trvlRqeName")%></label></b></td>
	                          <td width="10%" valign="top" class="formth"><b>
	                          	<label  class = "set" name="advPayDate" id="advPayDate" 
						            ondblclick="callShowDiv(this);"><%=label.get("advPayDate")%></label></b></td>
	                          <td width="10%" valign="top" class="formth"><b>
	                          	<label  class = "set" name="advAmt" id="advAmt" 
						            ondblclick="callShowDiv(this);"><%=label.get("advAmt")%></label></b></td>
	                         
	                         
	                          <td width="10%" valign="top" class="formth">
	                                     <s:if test="deductFlag">
	                                    <b><label  class = "set" name="deductedAmount" id="deductedAmount" 
							            ondblclick="callShowDiv(this);"><%=label.get("deductedAmount")%></label></b>
	                                    </s:if><s:else>
		                                <b><label  class = "set" name="amtDed" id="amtDed" 
							            ondblclick="callShowDiv(this);"><%=label.get("amtDed")%></label></b>
							            </s:else>
							  </td>
							            <td width="15%" valign="top" class="formth"><b>
		                          <label  class = "set" name="expSettDate" id="expSettDate" 
							            ondblclick="callShowDiv(this);"><%=label.get("expSettDate")%></label></b></td>
	                            <s:if test="deductFlag"></s:if><s:else>
	                            <td width="10%" valign="top" class="formth"><b>
		                          <label  class = "set" name="pendingDays" id="pendingDays" 
							            ondblclick="callShowDiv(this);"><%=label.get("pendingDays")%></label></b></td>
							            </s:else>
							       <s:if test="deductFlag"></s:if><s:else>      
							   <td width="4%" valign="top" class="formth"><input type="checkbox" name="chkAll" id="chkAll"  onclick="return callAllCheckBox();"/>
		                       </td> </s:else>        
	                        </tr>
	                        <% int k=1; %>
	                        <%
											int cnt = pageNo * 20 - 20;
											int m = 0;
							%>
							<s:if test="noData">
							
							<tr>
									<td colspan="7" width="100%" align="center"><font color="red">There is no data to display.</font></td>						
							
							</tr>
							
							
							</s:if>
							
	                        <s:iterator value="openList" >
	                        <tr
	                          onmouseover="javascript:newRowColor(this);"
			                  onmouseout="javascript:oldRowColor(this);"
			                  title="Double click to view employee details"
		                     ondblclick="javascript:viewEmployee('<s:property value="trvlAppId"/>','<s:property value="trvlAppCode"/>','<s:property value="empId"/>','<s:property value="status"/>','<s:property value="advAmt"/>','<s:property value="defaulterId"/>','<s:property value="gradeCode"/>'
		                     ,'<s:property value="employeeCode"/>','<s:property value="initiatorCode"/>')"  >                      
	                       
	                         <td width="5%" class="sortableTD" align="center"><%=++cnt%><%++m;%><s:hidden name="trvlAppId"/><s:hidden name="trvlAppCode"/><s:hidden name="defaulterId"/><s:hidden name="disburseId"/></td>
	                        <td width="18%" class="sortableTD">&nbsp;<s:property value="empName"/><s:hidden name="empId"/><s:hidden name="status"/><s:hidden name="gradeCode"/></td>		
	                        <td width="18%" class="sortableTD">&nbsp;<s:property value="trvlReqName"/><s:hidden name="employeeCode"/><s:hidden name="initiatorCode"/></td>		
	                        <td width="10%" class="sortableTD">&nbsp;<s:property value="advPayDate"/></td>
	                        <td width="10%" class="sortableTD" align="right">&nbsp;<s:property value="advAmt"/><s:hidden name="advAmt" id="<%="advAmt"+k%>"/></td>
	                        <td width="10%" class="sortableTD" valign="middle">&nbsp;
	                        <s:if test="deductFlag"><s:property value="dedAmt"/></s:if><s:else>
	                        <s:textfield name="dedAmt" maxlength="15" size="10" id="<%="dedAmt"+k%>"  onkeypress="return numbersWithDot();" />
	                        </s:else>
	                        </td>
	                        <td width="10%" class="sortableTD" align="center">&nbsp;<s:property value="expSettDate" /></td>
	                       <s:if test="deductFlag"></s:if><s:else>
	                       			 <td width="10%" class="sortableTD" align="center">&nbsp;<s:property value="pendDays" /></td>
	                        </s:else>
	                        <s:if test="deductFlag"></s:if><s:else>
		                        <td width="4%" class="sortableTD"><input type="checkbox" class="checkbox" name="checkBox" id="checkBox<%=k%>" onclick="callCheckBox('<%=k%>')"/>
		                        <input type="hidden" name="hiddenChkBox"	id="hiddenChkBox<%=k%>" value="N"/>
	                        </s:else>
	                        </td>
	                        </tr>
	                        
	                        <%k++; %>
	                        </s:iterator>
	                        
	                     
                    </table>
                 </td>
              </tr>
	        </table>
		   </td>
		 </tr>
	
	
	
	<s:if test="noData">
	</s:if>
	
	<s:else>	
	<s:if test="deductFlag">
	</s:if>
	<s:else>
	
	
	<tr>
		      		<td width="100%"><b>
		      		<label class="set" name="deuctFrom" id="deuctFrom"
								ondblclick="callShowDiv(this);"><%=label.get("deuctFrom")%></label></b>
		      		 :<font color="red">*</font> <s:select name="month" cssStyle="width:75"
													list="#{'':'--Select--','1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September',
										'10':'October','11':'November','12':'December'}" />
										<s:textfield name="year" maxlength="4" size="8" onkeypress="return numbersOnly();"/><b>(yyyy)</b></td>
           </tr>
	       <tr><td width="100%" align="center">
	  		<input type="button" value="Process" class="token" onclick="callProcess();"/> </td>
	  
	   </tr>
	</s:else>
	
	
	</s:else>
	
	
	
	
	
	
		
	<!--<s:if test="%{ deductFlag || noData}">
	</s:if>
	
	<s:else>
	
	<tr>
		      		<td width="100%"><b>
		      		<label class="set" name="deuctFrom" id="deuctFrom"
								ondblclick="callShowDiv(this);"><%=label.get("deuctFrom")%></label></b>
		      		 :<font color="red">*</font> <s:select name="month" cssStyle="width:75"
													list="#{'':'--Select--','1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September',
										'10':'October','11':'November','12':'December'}" />
										<s:textfield name="year" maxlength="4" size="8" onkeypress="return numbersOnly();"/><b>(yyyy)</b></td>
           </tr>
	       <tr><td width="100%" align="center">
	  		<input type="button" value="Process" class="token" onclick="callProcess();"/> </td>
	  
	   </tr>
	
	</s:else>
	
	
	
	--></table>
	<s:hidden name="empCode"/><s:hidden name="initCode"/><s:hidden name="employeeCode"/><s:hidden name="initiatorCode"/>
</s:form>


<script>

function viewEmployee(trvAppId,trvAppCode,empId,status,advAmt,defaultId,gradeId,employeeCode,initiatorCode){
		document.getElementById("paraFrm_gradeId").value=gradeId;
		document.getElementById("paraFrm_empCode").value=employeeCode;
		document.getElementById("paraFrm_initCode").value=initiatorCode;
		document.getElementById("paraFrm_appCode").value=trvAppCode;
		document.getElementById("paraFrm_appId").value=trvAppId;
		document.getElementById("paraFrm").action="TravelClaimDefaulter_getEmployeeDet.action?trvAppId="+trvAppId+"&trvAppCode="+trvAppCode+"&empId="+empId+"&advAmt="+advAmt+"&status="+status+"&defaultId="+defaultId;
		document.getElementById("paraFrm").submit();

}

function valCTC(fieldName,labelName)
	{
	var amount=document.getElementById(fieldName).value;
	var amountLabel=document.getElementById(labelName).innerHTML.toLowerCase();
	if(trim(amount)!=""){
		 if(isNaN(amount)){ 
		    alert("Only one dot is allowed in "+amountLabel+" field.");
		    document.getElementById(fieldName).focus();
		    return false;
		    }	
		  }
		return true;
  }
		 
		 
		 
function callProcess(){

//check the current month and year.Entered month should be greater than the present month 
   //and year should be greater than or equal to the present year. 
		  
  
        var tbl = document.getElementById('tblSettlement');
		var rowLen =tbl.rows.length;	
	
		var counter=0;
		for(var i=1;i<rowLen;i++){
			var advance=trim(document.getElementById('advAmt'+i).value);
			var deduction=trim(document.getElementById('dedAmt'+i).value);
			if(!valCTC('dedAmt'+i,'amtDed')){
			   return false;
			}
	
		if(deduction!=""){
		if(deduction.indexOf(".")!=-1){
				var dot=deduction.split('.');
				if(dot[1].length >2){
					alert("Only two digits are allowed after the dot.");
					document.getElementById('dedAmt'+i).focus();
					return false;
     			}
     		}	
		  }		
			
		if(document.getElementById('checkBox'+i).checked == true){
			if(deduction==""){
				alert("Please enter the "+document.getElementById('amtDed').innerHTML.toLowerCase());
				 document.getElementById('dedAmt'+i).focus();
				return false;	
			}
		}	
			if(parseFloat(deduction) > parseFloat(advance)){
				alert("Deduction amount can't be greater than advance amount.");
				return false;
		    }
		    if(document.getElementById('checkBox'+i).checked == true){
		    	counter=1;
		    	break;
		     }
		    
		}
		
		if(counter==0){
			alert("Please select at least one application to process.");
			return false;
		 }
		
		if(document.getElementById('paraFrm_month').value==""){
			alert("Please select the month.");
			return false; 
		
		}
		
		if(document.getElementById('paraFrm_year').value==""){
			alert("Please enter the year.");
			return false; 
		
		}
	
	   var currDate=new Date();  
		   var curMon=currDate.getMonth()+1;
		   var curYear=currDate.getFullYear(); 
		   var selMon=document.getElementById('paraFrm_month').value;
		   var selYear=document.getElementById('paraFrm_year').value;
                 if(eval(selYear)<eval(curYear))
                 {
                 alert("Please Enter the year greater than or equal to  "+curYear);
                 return false;
                 }     		  
                 if(selMon<curMon)
                 {
                 alert("Selected Month should be greater than current month.");
                 return false;
                 } 
	
	
		document.getElementById("paraFrm").action="TravelClaimDefaulter_processData.action";
	    document.getElementById("paraFrm").submit();
		
		
  }


function callPageText(id){  
	 
		    
		    var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
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
	         if(document.getElementById('paraFrm_decPage').value=="open")		   
			document.getElementById('paraFrm').action='TravelClaimDefaulter_input.action';
			else
			document.getElementById('paraFrm').action='TravelClaimDefaulter_closedDefaltr.action';
			document.getElementById('paraFrm').submit();
		}
		
	}
function callPage(id,pageImg){  

 	 pageNo =document.getElementById('pageNoField').value;	
 	 totalPage =document.getElementById('totalPage').value;	  	
 	   if(pageNo==""){
	        alert("Please Enter Page Number.");
	        document.getElementById('pageNoField').focus();
			 return false;
	        }
	 if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero.");
		     document.getElementById('pageNoField').focus();
			 return false;
		    }
	  if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField').focus();
			 return false;
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
		// document.getElementById('paraFrm_myPage').value=pageNo;
		
	         if(document.getElementById('paraFrm_decPage').value=="open")		   
			document.getElementById('paraFrm').action='TravelClaimDefaulter_input.action';
			else
			document.getElementById('paraFrm').action='TravelClaimDefaulter_closedDefaltr.action';
		document.getElementById('paraFrm').submit(); 
	}



function callCheckBox(id)
	   {
	 	  
	 
	   if(document.getElementById('checkBox'+id).checked == true)
	   {	  
	    document.getElementById('hiddenChkBox'+id).value="Y";
	   }
	   else{
	   document.getElementById('hiddenChkBox'+id).value="N";
	   	
   		}
  }
function callAllCheckBox(){
		var tbl = document.getElementById('tblSettlement');
		var rowLen = tbl.rows.length;
	if (document.getElementById('chkAll').checked == true){
		for (var idx = 1; idx < rowLen; idx++) {
				 document.getElementById('checkBox'+idx).checked = true;
				 document.getElementById('hiddenChkBox'+idx).value="Y";
					
	  }

 }else{
    	 for (var idx = 1; idx < rowLen; idx++) {
		
	    	document.getElementById('checkBox'+idx).checked = false;
		    document.getElementById('hiddenChkBox'+idx).value="N";
	     }


}
}

function newRowColor(cell)
   		 {
		   cell.className='Cell_bg_first';

	    }
	
	function oldRowColor(cell) {
	
	 cell.className='Cell_bg_second';
	
		
	}

</script>
	