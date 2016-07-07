<link rel="stylesheet" type="text/css" href="../pages/common/tabcontent.css" />
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="LTATaxCalculation" method="post" id="paraFrm" theme="simple"> 

	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg">
 	    <tr>
			<td colspan="3" width="100%">   
				 <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt"><strong class="formhead"><img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="text_head">LTA Calculation</strong></td>
						<td width="3%" valign="top" class="otxt"><div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
					</tr>
				</table>  
			</td>
		 </tr>  
	      <tr>
	        <td colspan="3">
	           <table width="100%" border="0" cellpadding="0" cellspacing="0" >
					<tr>
					   <td width="100%" colspan="3">
					         <jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
					  </td> 
					</tr>
				</table>
			</td>
		 </tr>
		 
	<s:if test="isOnload">			
	<tr>
		<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
			<tr>

						<td width="40%" colspan="7"><strong><label  class = "set" name="reportTitle" id="reportTitle" ondblclick="callShowDiv(this);"><%=label.get("reportTitle")%></label>.</strong></td>						
								<%
								int totalPage = 0;
								int pageNo =0;
								int count=0;
								try{
					 totalPage = (Integer) request.getAttribute("totalPage");
					pageNo = (Integer) request.getAttribute("PageNo");
					
								}catch(Exception e){
									
								}
					%> <s:if test="noData"></s:if><s:else>
						<td align="right" width="60%" colspan="6"><b>Page:</b> 
					 <input type="hidden" name="totalPage"  id="totalPage" value="<%=totalPage%>">
						 	<a href="#" onclick="callPage('1','F');"  >
										<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
									</a>&nbsp;
									<a href="#" onclick="callPage('P','P');" >
										<img  title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
									</a> 
							    <input type ="text" name="pageNoField" id="pageNoField" theme="simple" size="3"  value="<%= pageNo%>"  onkeypress="callPageText(event);return numbersOnly()"   maxlength="10" /> of <%=totalPage%>
						 		 		<a href="#" onclick="callPage('N','N')" >
											<img  title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
										</a>&nbsp;
										<a href="#" onclick="callPage('<%=totalPage%>','L');" >
											<img title="Last Page"  src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
										</a>
						</td>
						</s:else>
						</tr>
				<!--  <tr>
					
					<td width="100%" colspan="13" align="right"><input type="button" value="View Calculated List" onclick="return callViewList();" class="token"/> <input type="button" value="Process" onclick="return callProcessing();" class="token"/></td>
				</tr>-->
				</table>
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				
				
				<tr>
					<s:hidden name='noData'/>
					<td width="5%" class="formth"><label  class = "set" name="srno" id="srno" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></td>
					<td width="10%" class="formth"><label  class = "set" name="employee.id" id="employee.id" ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></td>
					<td width="25%" class="formth"><label  class = "set" name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
					<td width="10%" class="formth"><label  class = "set" name="blockYearFromLabel" id="blockYearFromLabel" ondblclick="callShowDiv(this);"><%=label.get("blockYearFromLabel")%></label></td>
					<td width="10%" class="formth"><label  class = "set" name="blockYearToLabel" id="blockYearToLabel" ondblclick="callShowDiv(this);"><%=label.get("blockYearToLabel")%></label></td>
					<td width="10%" class="formth"><label  class = "set" name="yearOfVisitLabel" id="yearOfVisitLabel" ondblclick="callShowDiv(this);"><%=label.get("yearOfVisitLabel")%></label></td>
					<td width="10%" class="formth"><label  class = "set" name="claimAmtLabel" id="claimAmtLabel" ondblclick="callShowDiv(this);"><%=label.get("claimAmtLabel")%></label></td>
					<td width="10%" class="formth"><label  class = "set" name="isTaxExemptedLabel" id="isTaxExemptedLabel" ondblclick="callShowDiv(this);"><%=label.get("isTaxExemptedLabel")%></label></td>
					<!--  
					<td width="10%" class="formth"><input type="checkbox" name='checkAll' id='checkAll' onclick="return callCheckAll();" /></td>
					-->
				</tr>
				 <s:if test="noData"> 
				 <tr>
					<td width="100%" colspan="8" align="center"><font color="red">No data to display</font></td>
				</tr>
				 </s:if>
				 <s:else>
				<% int i = 0;%>
				<s:iterator value="applEmpList">
					<tr class="sortableTD"
											<%if(count%2==0){
												%>
												 class="tableCell1" <%}else{%>
												class="tableCell2" <%	}count++; %>
												onmouseover="javascript:newRowColor(this);"
												title="Double click for edit"
												onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
												ondblclick="javascript:callForEdit('<s:property value="ltaIdList"/>');">
						<td class="sortableTD"  width="5%"><%=((pageNo-1)*20)+(++i) %></td>
						<td class="sortableTD" width="10%" nowrap="nowrap"><s:property
							value="empTokenList" /><s:hidden name="empTokenList"/><s:hidden	name="empCodeList"/><s:hidden name="ltaIdList"/></td>
						<td class="sortableTD" width="20%" align="left" nowrap="nowrap"><s:property
							value="empNameList" /><s:hidden name="empNameList"/></td>
										
					<td class="sortableTD" width="10%" align="center"
							nowrap="nowrap"><s:property value="blockYearFromList"/><s:hidden name="blockYearFromList"/></td>
					<td class="sortableTD" width="10%" align="center"
							nowrap="nowrap"><s:property value="blockYearToList"/><s:hidden name="blockYearToList"/></td>
					<td class="sortableTD" width="10%" align="center"
							nowrap="nowrap"><s:property value="yearOfVisitList"/><s:hidden name="yearOfVisitList"/></td>
					<td class="sortableTD" width="10%" align="center"
							nowrap="nowrap"><s:property value="claimAmtList"/><s:hidden name="claimAmtList"/></td>
					<td class="sortableTD" width="10%" align="center" nowrap="nowrap"><s:hidden name="isTaxExemptedList"/><s:property value="isTaxExemptedList"/></td>
					<!--  <td class="sortableTD" width="10%" align="center"
							nowrap="nowrap"><input type="checkbox" name="processingChk" id="<%="processingChk"+i%>" onclick="return chkEmpForProcess(<%=i%>)" /><s:hidden name='processingChkHd' id="<%="processingChkHd"+i%>"/></td>
					-->
					</tr>
				</s:iterator>
				<input type="hidden" name='count' id='count' value='<%=i%>'/>
				</s:else>
				<s:hidden name='empCode'/>
				<s:hidden name='empName'/>
				<s:hidden name='empToken'/>
				<s:hidden name='blockYearFrom'/>
				<s:hidden name='blockYearTo'/>
				<s:hidden name='yearOfVisit'/>
				<s:hidden name='ltaId'/>
				<s:hidden name='claimDate'/>
				<s:hidden name='claimType'/>
				<s:hidden name='yearOfExemption'/>
				
		
		
		</table></td></tr>
		
		</s:if>
		
		<s:else>
		 <tr>
      		<td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
          	<tr>
            	<td><table width="98%" border="0" align="center" cellpadding="2" cellspacing="2">
             
           <tr>
               <td width="25%" colspan="1" height="20" class="formtext"><label  class = "set" name="employee"  id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> <font color="red">*</font> :</td>
			   <td width="25%" colspan="3" height="20" ><s:hidden name="empCode"/> <s:textfield name="empToken" size="10" readonly="true" /><s:textfield name="empName" readonly="true" size="50" />
			   <img id='ctrlHide' onclick="javascript:callsF9(500,325,'LTATaxCalculation_f9empAction.action');" src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" theme="simple"></td> 
           </tr>
           
           <tr>
              
              <td width="25%" colspan="1" height="20" class="formtext"><label  class = "set" name="yearOfVisitLabel"  id="yearOfVisitLabel" ondblclick="callShowDiv(this);"><%=label.get("yearOfVisitLabel")%></label> <font color="red">*</font> :</td>
			  <td width="25%" colspan="1" height="20"><s:textfield name="yearOfVisit"  size="20" maxlength="4" onkeypress="return numbersOnly();" /></td>
               <td width="25%" colspan="1" height="20" class="formtext"><label  class = "set" name="claimDateLabel"  id="claimDateLabel" ondblclick="callShowDiv(this);"><%=label.get("claimDateLabel")%></label> <font color="red">*</font> :</td>
			  <td width="25%" colspan="1" height="20"><s:textfield name="claimDate" size="20" maxlength="10" onkeypress="return numbersWithHiphen();" /><s:a href="javascript:NewCal('paraFrm_claimDate','DDMMYYYY');"><img id='ctrlHide'  src="../pages/images/recruitment/Date.gif" class="iconImage" height="18" align="absmiddle" width="18"></s:a></td>
            </tr>
           
            <tr>
              
              <td width="25%" colspan="1" height="20" class="formtext"><label  class = "set" name="blockYearFromLabel"  id="blockYearFromLabel" ondblclick="callShowDiv(this);"><%=label.get("blockYearFromLabel")%></label> <font color="red">*</font> :</td>
			  <td width="25%" colspan="1" height="20"><s:textfield name="blockYearFrom" size="20" maxlength="4" onkeypress="return numbersOnly();" /></td>
               <td width="25%" colspan="1" height="20" class="formtext"><label  class = "set" name="blockYearToLabel"  id="blockYearToLabel" ondblclick="callShowDiv(this);"><%=label.get("blockYearToLabel")%></label> <font color="red">*</font> :</td>
			  <td width="25%" colspan="1" height="20"><s:textfield name="blockYearTo" size="20" maxlength="4" onkeypress="return numbersOnly();" /></td>
            </tr>
                       
            <tr>
              
              <td width="25%" colspan="1" height="20" class="formtext"><label  class = "set" name="claimAmtLabel"  id="claimAmtLabel" ondblclick="callShowDiv(this);"><%=label.get("claimAmtLabel")%></label> <font color="red">*</font> :</td>
			  <td width="25%" colspan="1" height="20"><s:textfield name="claimAmt" size="20" maxlength="10" onkeypress="return numbersWithDot();" /></td>
               <td width="25%" colspan="1" height="20" class="formtext"><label  class = "set" name="claimTypeLabel"  id="claimTypeLabel" ondblclick="callShowDiv(this);"><%=label.get("claimTypeLabel")%></label> <font color="red">*</font> :</td>
			  <td width="25%" colspan="1" height="20"><s:select	name="claimType" headerKey="" theme="simple" cssStyle="width:110" list="#{'S':'Self','F':'Family','B':'Both'}" /></td>
            </tr>
            
            <tr>
              
              <td width="25%" colspan="1" height="20" class="formtext"><s:hidden name="ltaId"/><label  class = "set" name="isTaxExemptedLabel"  id="isTaxExemptedLabel" ondblclick="callShowDiv(this);"><%=label.get("isTaxExemptedLabel")%></label> :</td>
			  <td width="25%" colspan="1" height="20"><s:select	name="isTaxExempted" headerKey="" theme="simple" cssStyle="width:110" list="#{'N':'No','Y':'Yes'}" /></td>
               <td width="25%" colspan="1" height="20" class="formtext"><label  class = "set" name="yearOfExemptionLabel"  id="yearOfExemptionLabel" ondblclick="callShowDiv(this);"><%=label.get("yearOfExemptionLabel")%></label>  :</td>
			  <td width="25%" colspan="1" height="20"><s:textfield name="yearOfExemption" size="5" maxlength="4" onkeypress="return numbersOnly();" onkeyup="return callYearOfExemption(this)" onblur="return callYearOfExemption(this)" /><s:textfield name="yearOfExemptionTo" cssStyle="border: none" size="5" maxlength="4" readonly="true" /></td>
            </tr>
            <tr>
              
              <td width="25%" colspan="1" height="20" class="formtext"><label  class = "set" name="ltaExemptedAmt"  id="ltaExemptedAmt" ondblclick="callShowDiv(this);"><%=label.get("ltaExemptedAmt")%></label> :</td>
			  <td width="25%" colspan="1" height="20"><s:textfield name="exemptedAmt" size="20" maxlength="10" onkeypress="return numbersWithDot();" /></td>
               <td width="25%" colspan="1" height="20" class="formtext"></td>
			  <td width="25%" colspan="1" height="20"></td>
            </tr>
           
           
          
            </table></td>
          </tr>
      </table></td>
    </tr>
		</s:else>
			  		
		       <tr> 
		          <td colspan="3">
			           <table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
							<td width="100%" colspan="3">
					         <jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
					  		</td> 
						  </tr>
					  </table>
					</td>					
			    </tr>					
			</table>  
</s:form> 
 

<script type="text/javascript">    
 
function addnewFun(){
			document.getElementById('paraFrm').action='LTATaxCalculation_addNew.action';
			document.getElementById('paraFrm').submit();
}

function saveFun(){
			document.getElementById('paraFrm').action='LTATaxCalculation_save.action';
			document.getElementById('paraFrm').submit();
}


function editFun(){
			document.getElementById('paraFrm').action='LTATaxCalculation_edit.action';
			document.getElementById('paraFrm').submit();
}
function saveFun(){
			var fields=["paraFrm_empName","paraFrm_blockYearFrom","paraFrm_blockYearTo","paraFrm_yearOfVisit","paraFrm_claimDate","paraFrm_claimAmt"];
			var labels=["employee","blockYearFromLabel","blockYearToLabel","yearOfVisitLabel","claimDateLabel","claimAmtLabel"];
			var flags=["select","enter","enter","enter","enter","enter"];
			
			var blkFrmYear=document.getElementById('paraFrm_blockYearFrom').value;
			var blkToYear=document.getElementById('paraFrm_blockYearTo').value;
			var yearOfVisit=document.getElementById('paraFrm_yearOfVisit').value;
			var claimAmt=document.getElementById('paraFrm_claimAmt').value;
			var exemptedAmt=document.getElementById('paraFrm_exemptedAmt').value;
			if(!validateBlank(fields,labels,flags)){
				return false;
			}
			/*if(yearOfVisit < blkFrmYear || yearOfVisit > blkToYear){
				alert(document.getElementById('yearOfVisitLabel').innerHTML+' should be between '+document.getElementById('blockYearFromLabel').innerHTML
				+' and '+document.getElementById('blockYearToLabel').innerHTML);
				return false;
			}*/
		
			if(!validateDate("paraFrm_claimDate", "claimDateLabel")){
				return false;
			}
			if(eval(exemptedAmt)>eval(claimAmt)){
				alert(document.getElementById('ltaExemptedAmt').innerHTML+" should be less than or equal to "+document.getElementById('claimAmtLabel').innerHTML);
				return false;
			}
			document.getElementById('paraFrm').action='LTATaxCalculation_save.action';
			document.getElementById('paraFrm').submit();
}

function searchFun(){
		callsF9(500,325,'LTATaxCalculation_f9LTAAction.action');
	}
	function resetFun(){
			document.getElementById('paraFrm_empCode').value='';
			document.getElementById('paraFrm_empName').value='';
			document.getElementById('paraFrm_empToken').value='';
			document.getElementById('paraFrm_blockYearFrom').value='';
			document.getElementById('paraFrm_blockYearTo').value='';
			document.getElementById('paraFrm_claimAmt').value='';
			document.getElementById('paraFrm_claimDate').value='';
			document.getElementById('paraFrm_yearOfExemption').value='';
			document.getElementById('paraFrm_yearOfExemptionTo').value='';
			document.getElementById('paraFrm_yearOfVisit').value='';
			document.getElementById('paraFrm_claimType').value='S';
			document.getElementById('paraFrm_isTaxExempted').value='Y';
	}
 function callReportChk(status)
 { 
	 if(status=="Y"){
	   document.getElementById('reportTypeDiv').style.display=''; 
	   document.getElementById('paraFrm_repStatus').value ="R"; 
	  }else{
	   document.getElementById('reportTypeDiv').style.display='none';  
	   document.getElementById('paraFrm_repStatus').value ="V"; 
	  }
 }
function chkEmpForProcess(id){
	if(document.getElementById("processingChk"+id).checked){
		document.getElementById("processingChkHd"+id).value='Y';
	}else{
		document.getElementById("processingChkHd"+id).value='N';
	}
	var count=document.getElementById('count').value;
	var flag=true;
	for(var i=1;i<=eval(count);i++){
	if(!document.getElementById("processingChk"+i).checked){
		flag=false;
	}
	}
	document.getElementById('checkAll').checked=flag;
}

 
 function callCheckAll(){
	var flag=document.getElementById('checkAll').checked;
	var count=document.getElementById('count').value;
	for(var i=1;i<=eval(count);i++){
	if(flag){
	document.getElementById("processingChkHd"+i).value="Y";
	}else{
		document.getElementById("processingChkHd"+i).value="N";
	}
	document.getElementById("processingChk"+i).checked=flag;
}
}
 
 function callPageText(id,status1){
 			 
	   if(status1=="null" || status1=="" ){		
				status1="P";
			}
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
		     document.getElementById('pageNoField').value=document.getElementById('paraFrm_myPageEmpConf').value;
			 return false;
		    }
		    document.getElementById('paraFrm_myPageEmpConf').value=pageNo;
		    
		   	document.getElementById('paraFrm').action='LTATaxCalculation_input.action';
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
       
         var p=document.getElementById('pageNoField').value;
		if(id=='P'){
		id=eval(p)-1;
		}
		if(id=='N'){
		id=eval(p)+1;
		} 
		
		document.getElementById('paraFrm_myPageEmpConf').value=id;
		document.getElementById('paraFrm').action='LTATaxCalculation_input.action';
		document.getElementById('paraFrm').submit();
		 
	}
	

	
		function newRowColor(cell) {
	//	cell.style.backgroundColor="#E2F2FE";
		//cell.style.cursor='hand';
		cell.className='onOverCell';
	//	document.getElementById(cell).backgroundColor="#FFA31D";
	}
	
	function newRowColor(cell) {
	//	cell.style.backgroundColor="#E2F2FE";
		//cell.style.cursor='hand';
		 cell.className='Cell_bg_first'; 
		//cell.className='onOverCell';
	//	document.getElementById(cell).backgroundColor="#FFA31D";
	}
	
	function oldRowColor(cell,val) {
	
	if(val=='1'){
	 cell.className='tableCell2';
	}
	else cell.className='tableCell1';
		//cell.style.backgroundColor="#ffffff";
	}
	
	function callForEdit(ltaId){
		//alert(ltaId);
			document.getElementById('paraFrm').action='LTATaxCalculation_editOnDoubleClick.action?ltaId='+ltaId;
			document.getElementById('paraFrm').submit();
	}
	
	function deleteFun(){
		//alert(ltaId);
		var isExempted = document.getElementById('paraFrm_isTaxExempted').value;
		if(isExempted=='Y'){
			alert("LTA claim amount is exempted, You can't delete the record");
			return false;
		}
		var conf=confirm("Do you really want to delete this record ?");
  			if(conf) 
  			{
  			document.getElementById('paraFrm').action='LTATaxCalculation_delete.action';
			document.getElementById('paraFrm').submit();
  			}
	  		else
	  		{
	  			 return false;
	  		}
			
	}
	
	function callYearOfExemption(obj){
		var frmYear=obj.value;
		if(frmYear!='' && frmYear!='0')
		document.getElementById('paraFrm_yearOfExemptionTo').value="-"+(eval(frmYear)+1);
		else{
			document.getElementById('paraFrm_yearOfExemptionTo').value="";
		}
		
	}
	
	function backFun(){
	
			document.getElementById('paraFrm').action='LTATaxCalculation_back.action';
			document.getElementById('paraFrm').submit();
	}

function callBlockYear(){
  		var year = document.getElementById("paraFrm_yearOfVisit").value;
  		var str1;
  		var str2;
		var start = 2006;
		var end   = 2009;
		
		if(year != ""){
			if(!(year<start)){
				while (start <= year) {
					if(start <= year && end >= year){
						str1 = start;
						str2 = end;
						break;
					}
					else{
						start = end+1;
						end   = start+3;
						str1  = start;
						str2  = end;
					}
				}
			}
			else{
				start = 2002;
				end   = 2005;
				
				while (end >= year) {
					if(start <= year && end >= year){
						str1 = start;
						str2 = end;
						break;
					}
					else{
						end   = start-1;
						start = end-3;
						str1  = start;
						str2  = end;
					}
				}
			}
			document.getElementById("paraFrm_blockYearFrom").value = str1;
			document.getElementById("paraFrm_blockYearTo").value  = str2;
		}
  	}

 
</script>
 