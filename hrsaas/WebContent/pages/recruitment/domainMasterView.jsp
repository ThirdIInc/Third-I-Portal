<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript">
var domArray=new Array();
</script>


<s:form action="DomainMaster" validate="true" id="paraFrm"
	 theme="simple">
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />
	<s:hidden name="cancelFlg" />
	<s:hidden name="domainName" />
	<s:hidden name="domainCode" />
	<s:hidden name="domAbbr" />
	<s:hidden name="domDesc" />
	<s:hidden name="domainStatus" />
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
					<td width="93%" class="txt"><strong class="text_head">Functional/
					Domain</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<%
					int totalPage = (Integer) request.getAttribute("totalPage");
					int pageNo = (Integer) request.getAttribute("PageNo");
				%>

				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<s:if test="flagShow">	
						<td align="right" width="22%" nowrap="nowrap" id="ctrlShow"><b>Page:</b> <input type="hidden"
										name="totalPage" id="totalPage" value="<%=totalPage%>">
									<a href="#" onclick="callPage('1','F');"> <img
										title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#" onclick="callPage('P','P');"> <img
										title="Previous Page" src="../pages/common/img/previous.gif"
										width="10" height="10" class="iconImage" /> </a> <input
										type="text" name="pageNoField" id="pageNoField" theme="simple"
										size="3" value="<%= pageNo%>"
										onkeypress="callPageText(event);return numbersOnly()"
										maxlength="4" /> of <%=totalPage%> <a href="#"
										onclick="callPage('N','N')"> <img title="Next Page"
										src="../pages/common/img/next.gif" class="iconImage" /> </a>&nbsp;
									<a href="#" onclick="callPage('<%=totalPage%>','L');"> <img
										title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td></s:if>

				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" width="100%">

			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">

				<s:if test="flagView">
					<tr>
						<td colspan="3">


						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="formbg">
							<tr>
								<td>
								<table width="98%" border="0" align="center" cellpadding="0"
									cellspacing="2">



									<tr>
										<td width="22%" height="22" class="formtext"><label
											class="set" name="dom.name" id="dom.name"
											ondblclick="callShowDiv(this);"><%=label.get("dom.name")%></label>
										:</td>

										<td width="34%" height="22"><s:property
											value="domainName" /></td>
										<td width="27%" height="22" class="formtext">&nbsp;</td>
										<td width="18%" height="22">&nbsp;</td>
									</tr>

									<tr>
										<td width="22%" height="22" class="formtext" nowrap="nowrap"><label
											class="set" name="dom.abbr" id="dom.abbr"
											ondblclick="callShowDiv(this);"><%=label.get("dom.abbr")%></label>
										:</td>


										<td height="22" width="22%"><s:property value="domAbbr" /></td>
										<td height="22" class="formtext">&nbsp;</td>
										<td height="22">&nbsp;</td>
									</tr>


									<tr>
										<td width="22%" height="22" valign="top" class="formtext"
											nowrap="nowrap"><label class="set" name="dom.desc"
											id="dom.desc" ondblclick="callShowDiv(this);"><%=label.get("dom.desc")%></label>:
										</td>

										<td height="22" colspan="3"><s:property value="domDesc" /></td>
									</tr>


									<tr>
										<td width="20%" height="22" class="formtext"><label
											class="set" name="dom.stat" id="dom.stat"
											ondblclick="callShowDiv(this);"><%=label.get("dom.stat")%></label>:
										</td>

										<td height="22"><s:property value="viewStatus" /></td>
										<td height="22" class="formtext">&nbsp;</td>
										<td height="22">&nbsp;</td>
									</tr>
									<tr>
										<td colspan="5"><img
											src="../pages/common/css/default/images/space.gif" width="5"
											height="7" /></td>
									</tr>

								</table>
								</td>
							</tr>
						</table>

						</td>
					</tr>

				</s:if>

				

<s:if test="flagShow">
				<tr>
					<td colspan="3">

					<table width="100%" border="0" cellpadding="0" cellspacing="0">

						<tr>
							<td colspan="3">
							<%
							try {
							%>
							<table width="100%" border="0" cellpadding="0" cellspacing="1"
								class="formbg">

								<tr>

									<td class="formtext" colspan="5">
									<table width="100%" border="0" cellpadding="1" cellspacing="1">
									<tr><td width="100%" nowrap="nowrap" colspan="5"><table width="100%"><tr>
									<td class="formhead" width="90%" align="left" colspan="4"><strong
										class="forminnerhead">Domain List</strong></td>
										<td align="center" width="10%" id="ctrlShow" colspan="1">
										<s:if test="deleteFlag">
										<input type="button" class="delete" theme="simple"	value="Delete" onclick=" return chkDelete();" />
											</s:if>
											</td></tr></table>
								      </td></tr>
								
										<tr>
											<s:hidden name="myPage" id="myPage" />
											<td class="formth" align="center" width="8%" nowrap="nowrap"><b><label
												class="set" name="dom.srno" id="dom.srno"
												ondblclick="callShowDiv(this);"><%=label.get("dom.srno")%></label></b>
											</td>
											<td class="formth" align="center" width="43%"><b><label
												class="set" name="dom.name" id="dom.name1"
												ondblclick="callShowDiv(this);"><%=label.get("dom.name")%></label></b>
											</td>
											<td class="formth" align="center" width="27%"><b><label
												class="set" name="dom.abbr" id="dom.abbr1"
												ondblclick="callShowDiv(this);"><%=label.get("dom.abbr")%></label></b>
											</td>
											<td class="formth" align="center" width="10%"><b><label
												class="set" name="dom.stat" id="dom.stat1"
												ondblclick="callShowDiv(this);"><%=label.get("dom.stat")%></label></b>
											</td>
											<td align="left" class="formth" nowrap="nowrap" width="12%" id="ctrlShow">
											<input type="checkbox" name="chkDomAll"
												id="paraFrm_chkDomAll" onclick="return callDomAll();" /> </td>
										</tr>


										<%
												int count = 0;
												int counter = 0;
										%>
										<%!int d = 0;%>
										<%
												int cnt = pageNo * 20 - 20;
												int i = 0;
										%>
										<s:iterator value="domainList">

											<tr id="tr1" <%if(count%2==0){
									%>
												class="tableCell1" <%}else{%> class="tableCell2"
												<%	}count++; %> onmouseover="javascript:newRowColor(this);"
												onmouseout="javascript:oldRowColor(this,<%=count%2 %>);">


												<td width="8%" align="center" class="sortableTD"
													title="Double click for edit"
													ondblclick="javascript:editRow('<s:property  value="domainCode"/>');"><%=++cnt%>
												<%
												++i;
												%>
												</td>


												<td width="43%" align="left" class="sortableTD"
													title="Double click for edit"
													ondblclick="javascript:editRow('<s:property  value="domainCode"/>');"><s:hidden
													value="%{domainCode}"></s:hidden> <input type="hidden"
													name="hdeleteCode" id="hdeleteCode<%=i%>" /> <s:property
													value="domainName" /></td>


												<script type="text/javascript">
							
													domArray[<%=counter%>] = '<s:property  value="domainCode"/>';
							
									</script>


												<td width="27%" align="left" class="sortableTD"
													title="Double click for edit"
													ondblclick="javascript:editRow('<s:property  value="domainCode"/>');"><s:property
													value="domAbbr" /></td>
												<td width="10%" align="left" class="sortableTD"
													title="Double click for edit"
													ondblclick="javascript:editRow('<s:property  value="domainCode"/>');"><s:property
													value="domainStatus" /> <input type="hidden"
													name="hdeleteCode" id="hdeleteCode<%=i%>" /></td>


												<td align="center" class="sortableTD" width="12%" id="ctrlShow"><input
													type="checkbox" class="checkbox" id="confChk<%=i%>"
													name="confChk"
													onclick="deleteRow('<s:property  value="domainCode"/>','<%=i%>')" /></td>
											</tr>
											<%
											++counter;
											%>
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

					</table>
					</td>
				</tr>
		</s:if>		
				
			</table></td></tr>
			<tr>
					<td align="left"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<s:if test="flagShow">
					<td align="right"><strong class="forminnerhead">Total
					no. of records :</strong><s:property value="totRecord" /></td></s:if>
				</tr>
			
			</table>
</s:form>
<script>
	
	function maxlength()
	{
	var length=document.getElementById('paraFrm_domDesc').value;
	var l=length.length;
	if(l=='500')
	{
		return false;
	}
	else
	{
		return true;
	}
	}	
	
	
	
	function callingStage(stage){
		document.getElementById('paraFrm').action = "DomainMaster_cancel.action?callingStage="+stage;
		document.getElementById('paraFrm').submit();
	}
	
	 function saveValidation(){
		 var fieldName = ['paraFrm_domainName', 'paraFrm_domAbbr'];
		 var lableName = ["dom.name","dom.abbr"];
		 var flag = ["enter", "enter"];
	  
	  	 if(!validateBlank(fieldName,lableName,flag))
          return false;
	  	
	  	var desc = document.getElementById('paraFrm_domDesc').value;
	  	
		if(desc != "" && desc.length > 300){
			alert("Maximum length of <%=label.get("dom.desc")%> is 300 characters.");
			return false;
		}
  	}
	
	function deleteRecord(){	//callDelete
		if(!callDelete('paraFrm_domainName'))return false;
		
		else{
			document.getElementById('paraFrm').action = "DomainMaster_delete.action";
		    document.getElementById('paraFrm').submit();
		}
	}
	
	//Modified by prasad
	
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
		   
			document.getElementById('paraFrm').action='DomainMaster_callPage.action';
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
		document.getElementById('paraFrm').action='DomainMaster_callPage.action';
		document.getElementById('paraFrm').submit(); 
	}
	
	/* function callPage(id){
	
		
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
		document.getElementById('paraFrm').action='DomainMaster_callPage.action';
		document.getElementById('paraFrm').submit();
		
	}*/	
  
   
    function newRowColor(cell){
		 cell.className='Cell_bg_first';
	}
	
	function oldRowColor(cell, val) {
	 cell.className='Cell_bg_second';
	}
	
	function editRow(id){	
	
	  	document.getElementById('paraFrm_hiddencode').value = id;
	
	   	document.getElementById("paraFrm").action="DomainMaster_calforedit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
    }
   
 
  	function deleteRow(id,i){	//callForDelete1
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
	   document.getElementById('paraFrm').action="DomainMaster_delete1.action";
	    document.getElementById('paraFrm').submit();
	    }
	    else{
	     var flag='<%=d %>';
	  for(var a=1;a<=flag;a++){	
	  document.getElementById("paraFrm_chkDomAll").checked=false;
	document.getElementById('confChk'+a).checked = false;
	document.getElementById('hdeleteCode'+a).value="";
	     }
	     return false;
	     }
	 } else {
	 	alert('Please select atleast one record to delete');
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
   
	
    
    function addnewFun(){
   
      	document.getElementById('paraFrm').action="DomainMaster_addNew.action";
        document.getElementById("paraFrm").submit();
    
    }
    
    function searchFun(){
    		callsF9(500,325,'DomainMaster_f9Domain.action');
    
    }
    
    function editFun(){
    	document.getElementById('paraFrm').action="DomainMaster_edit.action";
        document.getElementById("paraFrm").submit();
    
    }
    
    function cancelFun(){
    	document.getElementById('paraFrm').action="DomainMaster_cancelSecond.action";
        document.getElementById("paraFrm").submit();
    
    }
     
    function reportFun(){
    	document.getElementById('paraFrm').action="DomainMaster_report.action";
        document.getElementById("paraFrm").submit();
    }
    function deleteFun(){
   		var conf=confirm("Do you really want to delete this record ?");
			if(conf){
				document.getElementById('paraFrm').action = "DomainMaster_delete.action";
		   		document.getElementById("paraFrm").submit();
					
			}else{
			
			     return false;
			}
    }
    
    
    function on(){
		var val= document.getElementById('selectname').value;
		document.getElementById('paraFrm_show').value=val;
		document.getElementById('myPage').value=eval(val);
		document.getElementById('selectname').value=val;
		document.getElementById('paraFrm').action="DomainMaster_callPage.action";
		document.getElementById('paraFrm').submit();
}    
 
pgshow();

function pgshow()
  	{
  	var pgno=document.getElementById('paraFrm_show').value;
  
  	if(!(pgno==""))
  	 document.getElementById('selectname').value=pgno;
}



 function callDomAll()
  {
	 	  
	var rowLen ='<%=d %>';
	if (document.getElementById("paraFrm_chkDomAll").checked){
			
		  for(var idx=1;idx<=rowLen;idx++){
		   document.getElementById("confChk"+idx).checked ="true";
		  document.getElementById('hdeleteCode'+idx).value=domArray[idx-1];
		  
		  }
			
			
	}else{
			for (var idx = 1; idx <= rowLen; idx++) {
			document.getElementById("paraFrm_chkDomAll").checked=false;
			document.getElementById("confChk"+idx).checked = false;
			document.getElementById('hdeleteCode'+idx).value="";
	  }
    }	 
	
  }    
	
	</script>

