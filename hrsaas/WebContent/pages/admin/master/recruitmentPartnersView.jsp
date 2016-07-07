<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script>
var recrArray=new Array();
</script>
<s:form action="RecruitmentPartners" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />
	<s:hidden name="editViewFlag" />
	<s:hidden name="cancelFlag" />
	<s:hidden name="noData"/>
	
	<s:hidden name="recPartnerName" /> <s:hidden name="recPartnerType" /> <s:hidden name="recContactPerson" />
	<s:hidden name="status" /> <s:hidden name="cityCode" /> <s:hidden name="recpartnerCode" />  <s:hidden name="recPartnerCity" />
 
	
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
					<td width="93%" class="txt"><strong class="text_head">Recruitment
					Partners</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<%
							int totalPage = (Integer) request.getAttribute("abc");
							int pageNo = (Integer) request.getAttribute("xyz");
						%>
		<tr>
			<td colspan="3" width="100%">

			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">

				<!-- The Following code Denotes  Include JSP Page For Button Panel --> 

				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"> 
						<tr> <td colspan="1"> <jsp:include 	page="/pages/ApplicationStudio/navigationPanel.jsp" /> </td>
						<td align="right" id="ctrlShow"><b>Page:</b> <input type="hidden"
										name="totalPage" id="totalPage" value="<%=totalPage%>">
									<a href="#" onclick="callPage('1','F');"> <img
										title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPage('P','P');"> <img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text" name="pageNoField"
										id="pageNoField" theme="simple" size="3" value="<%= pageNo%>"
										onkeypress="callPageText(event);return numbersOnly()"
										maxlength="4" /> of <%=totalPage%> <a href="#"
										onclick="callPage('N','N')"> <img title="Next Page"
										src="../pages/common/img/next.gif" class="iconImage" /> </a>&nbsp;
									<a href="#" onclick="callPage('<%=totalPage%>','L');"> <img
										title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
							
						</tr>

					</table>
					</td>
				</tr>




			</table>
			</td>
		</tr>
    	<tr>
			<td colspan="3">
			<%
			try {
			%>
			<table width="100%" border="0" cellpadding="0" cellspacing="1"
				class="formbg">

				<tr>

					<td class="formtext">
					<table width="100%" border="0" cellpadding="1" cellspacing="1">
						<tr>
							<td height="15" class="formhead" nowrap="nowrap" colspan="6" width="90%"><strong
								class="forminnerhead">Recruitment Partners List</strong></td>
								
								<td width="10%" colspan="1" align="right" id="ctrlShow">
								<input type="button" class="delete" theme="simple" value="  Delete  " onclick="return chkDelete();" />
								
								</td>
								
								
						</tr>
						<tr>
							<s:hidden name="myPage" id="myPage" />
							<td nowrap="nowrap" class="formth" align="center" width="8%"><label
								name="recr.serno" class="set" id="recr.serno"
								ondblclick="callShowDiv(this);"><%=label.get("recr.serno")%></label>
							</td>
							<td width="25%" class="formth" align="center"><label
								name="recr.pname" class="set" id="recr.pname1"
								ondblclick="callShowDiv(this);"><%=label.get("recr.pname")%></label>
							</td>
							
							<td width="17%" class="formth" align="center"><label
								name="recr.ptype" class="set" id="recr.ptype1"
								ondblclick="callShowDiv(this);"><%=label.get("recr.ptype")%></label>
							</td>
							<td width="20%" class="formth" align="center"><label
								name="recr.coper" class="set" id="recr.coper1"
								ondblclick="callShowDiv(this);"><%=label.get("recr.coper")%></label>
							</td>
							
							<td width="10%" class="formth" align="center"><label
								name="city" class="set" id="recr.pcity1"
								ondblclick="callShowDiv(this);"><%=label.get("city")%></label>
							</td>
							<td width="10%" class="formth" align="center"><label
								name="recr.stats" class="set" id="recr.stats1"
								ondblclick="callShowDiv(this);"><%=label.get("recr.stats")%></label>
							</td>
							<td width="10%" align="center" class="formth" nowrap="nowrap" id="ctrlShow">
							<input type="checkbox" name="chkAll" id="paraFrm_chkAll"  onclick="return callChkAll();"/>
							<!-- <input
								type="button" class="delete" theme="simple"
								value="     Delete  " onclick="return chkDelete();" /> -->
								
								
								</td>

						</tr>
						
						<s:if test="noData">
						
						<tr><td colspan="7" width="100%" align="center"><font color="red">There is no data to display</font></td></tr>
						
						</s:if>
						
						
						<%
						int count = 0;
						%>
						<%!int d = 0;%>
						<%
								int cnt = pageNo * 20 - 20;
								int i = 0,counter=0;
						%>
						<s:iterator value="recpartnerList">

							<tr id="tr1" <%if(count%2==0){
									%> class="tableCell1"
								<%}else{%> class="tableCell2" <%	}count++; %>
								onmouseover="javascript:newRowColor(this);"
								onmouseout="javascript:oldRowColor(this,<%=count%2 %>);">


								<td width="8%" align="center" class="sortableTD"
									title="Double click for edit"
									ondblclick="javascript:callForEdit('<s:property  value="recpartnerCode"/>');"><%=++cnt%>
								<%
								++i;
								%>
								</td>
											<script type="text/javascript">
							
													recrArray[<%=counter%>] = '<s:property  value="recpartnerCode"/>';
							
											</script>	

								<td width="25%" align="left" class="sortableTD"
									title="Double click for edit"
									ondblclick="javascript:callForEdit('<s:property  value="recpartnerCode"/>');"><s:hidden
									value="%{recpartnerCode}"></s:hidden> <input type="hidden"
									name="hdeleteCode" id="hdeleteCode<%=i%>" /> <s:property
									value="recPartnerName" />&nbsp;
									</td>
								<td width="20%" align="left" class="sortableTD"
										title="Double click for edit"
										ondblclick="javascript:callForEdit('<s:property  value="recpartnerCode"/>');"><s:property
										value="recPartnerType" />&nbsp;</td>	
									
									
								<td width="20%" align="left" class="sortableTD"
									title="Double click for edit"
									ondblclick="javascript:callForEdit('<s:property  value="recpartnerCode"/>');"><s:property
									value="recContactPerson" />&nbsp;
									</td>
										
								<td width="10%" align="left" class="sortableTD"
									title="Double click for edit"
									ondblclick="javascript:callForEdit('<s:property  value="recpartnerCode"/>');"><s:property
									value="recPartnerCity" />&nbsp;
									</td>
								<td width="10%" align="left" class="sortableTD"
									title="Double click for edit"
									ondblclick="javascript:callForEdit('<s:property  value="recpartnerCode"/>');"><s:property
									value="status" />&nbsp;
									</td>
								<input type="hidden" name="hdeleteCode" id="hdeleteCode<%=i%>" />
								</td>
								<td width="10%" align="center" nowrap="nowrap"
									class="sortableTD" id="ctrlShow"><input type="checkbox" class="checkbox"
									id="confChk<%=i%>" name="confChk"
									onclick="callForDelete1('<s:property  value="recpartnerCode"/>','<%=i%>')" /></td>
							</tr>
						<%++counter;%>
						</s:iterator>
						<%
						d = i;
						%>



					</table>

					<%
						} catch (Exception e) {
						}
					%>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
			<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="75%"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
							<td width="25%" align="right"><s:if test="noData"></s:if><s:else><strong class="forminnerhead">Total no. of records :<s:property value="dataLength"/></strong></s:else></td>
						</tr>
					</table>
					</td>
				</tr>
	</table>

</s:form>
<script>
 
function addnewFun()
{
	document.getElementById('paraFrm').action="RecruitmentPartners_addNew.action";
	document.getElementById('paraFrm').submit();
}

// For Save Button

 


//For Edit Button

function editFun()
{ 
	document.getElementById('paraFrm').action="RecruitmentPartners_edit.action";
	document.getElementById('paraFrm').submit();
}

//For Delete Button

function deleteFun()
{
	var con=confirm('Do you really want to delete this record?')
	if(con){
		   document.getElementById('paraFrm').action="RecruitmentPartners_delete.action";
		   document.getElementById('paraFrm').submit();
	} else{
	     return false;
	}
}

//For F9Window

function searchFun()
{
	callsF9(500,300,"RecruitmentPartners_f9Action.action");
	//document.getElementById('paraFrm').submit();
}

//For Cancel Button
function cancelFun(){
    	document.getElementById('paraFrm').action="RecruitmentPartners_cancelSecond.action";
        document.getElementById("paraFrm").submit();
}
//For Report Button

function reportFun()
{
	document.getElementById('paraFrm').action="RecruitmentPartners_report.action";
    document.getElementById("paraFrm").submit();
}

function callDelete(){
	if(document.getElementById('paraFrm_recPartnerName').value==""){
			alert("Please select the record.");
			return false;
			
	}
	 
	var con=confirm('Do you really want to delete this record?');
	if(con){
		   document.getElementById('paraFrm').action="RecruitmentPartners_delete.action";
		   document.getElementById('paraFrm').submit();
	} else{
	     return false;
	   }
	
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
		 	var actPage = document.getElementById('myPage').value;   
	     
		 	 
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
	        
	         document.getElementById('myPage').value=pageNo;
		   
			document.getElementById('paraFrm').action='RecruitmentPartners_callPage.action';
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
		
		
		document.getElementById('myPage').value=id;
		document.getElementById('paraFrm').action='RecruitmentPartners_callPage.action';
		document.getElementById('paraFrm').submit(); 
	}
 

/*function callPage(id){
	
		
		if(id=='P'){
		var p=document.getElementById('myPage').value;
		id=eval(p)-1;
		}
		if(id=='N'){
		var p=document.getElementById('myPage').value;
		id=eval(p)+1;
		}
		document.getElementById('myPage').value=id;
		document.getElementById('paraFrm_show').value=id;
	    document.getElementById('paraFrm').action="RecruitmentPartners_callPage.action";
	   	document.getElementById('paraFrm').submit();
}*/	
function on(){
		var val= document.getElementById('selectname').value;
		document.getElementById('paraFrm_show').value=val;
		document.getElementById('myPage').value=eval(val);
		document.getElementById('selectname').value=val;
		document.getElementById('paraFrm').action="RecruitmentPartners_callPage.action";
		document.getElementById('paraFrm').submit();
}
   
   
function newRowColor(cell){
			cell.className='onOverCell';

}
	
function oldRowColor(cell,val) {
	
	if(val=='1'){
	 cell.className='tableCell2';
	}else {
	cell.className='tableCell1';
		}
}
	
	
	
	
function callForEdit(id){ 
  	document.getElementById('paraFrm_hiddencode').value=id;
   	document.getElementById("paraFrm").action="RecruitmentPartners_calforedit.action";
   	document.getElementById("paraFrm").target="main";
    document.getElementById("paraFrm").submit();
}
   
   
pgshow();

function pgshow()
  	{
  	var pgno=document.getElementById('paraFrm_show').value; 
  	if(!(pgno==""))
  	 document.getElementById('selectname').value=pgno;
  	}
  	
function callForDelete1(id,i){
	  if(document.getElementById('confChk'+i).checked == true){	  
	    document.getElementById('hdeleteCode'+i).value=id;
	  }
	  else
	    document.getElementById('hdeleteCode'+i).value="";
}
  	
function chkDelete(){
	 var flag='<%=d %>';
	
	 if(chk()){
	 var con=confirm('Do you really want to delete the records?');
	 if(con){
	   document.getElementById('paraFrm').action="RecruitmentPartners_delete1.action";
	    document.getElementById('paraFrm').submit();
	    }
	    else{
	     var flag='<%=d %>';
	  for(var a=1;a<=flag;a++){	
	document.getElementById('confChk'+a).checked = false;
	document.getElementById('hdeleteCode'+a).value="";
	     }
	     return false;
	     }
	 } else {
	 	alert("Please select atleast one record to delete");
	 	 return false;
	 }
} 

function chk(){
	 var flag='<%=d %>';
	  for(var a=1;a<=flag;a++){	
	   if(document.getElementById('confChk'+a).checked == true)
	   {	
	 	    return true;
	   }	   
	  }
	  return false;
}
	
function maxlengthAddr()
{
	var addr=document.getElementById('paraFrm_recPartnerAddress').value;
	var addrLength=addr.length;
	if(addrLength=='250')
	{
		return false;
	}
	else
	{
		return true;
	}
}	
function maxlengthDesc()
{
	var desc=document.getElementById('paraFrm_partnerDesc').value;
	var descLength=desc.length;
	if(descLength=='100')
	{
		return false;
	}
	else
	{
		return true;
	}
}


 function callChkAll()
  {
	 	  
		
		var rowLen ='<%=d %>';
		
	
		if (document.getElementById("paraFrm_chkAll").checked){
			
		  for(var idx=1;idx<=rowLen;idx++){
		   document.getElementById("confChk"+idx).checked ="true";
		  document.getElementById('hdeleteCode'+idx).value=recrArray[idx-1];//alert("hhh"+skillArray.length);
		  
		  }
			
			
	}else{
	for (var idx = 1; idx <= rowLen; idx++) {
	
	  document.getElementById("confChk"+idx).checked = false;
	  document.getElementById('hdeleteCode'+idx).value="";
     }
  }	 
	
  }    

</script>

