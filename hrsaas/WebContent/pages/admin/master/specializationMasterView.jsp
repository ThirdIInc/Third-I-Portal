<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript">
var specArray = new Array();
</script>
<s:form action="SpecializationMaster" validate="true" id="paraFrm"
	 theme="simple">
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />
	<s:hidden name="specializationCode" />
	<s:hidden name="specializationName" />
	<s:hidden name="specializationAbbr" />
	<s:hidden name="specializationDesc" />
	<s:hidden name="specializationStatus" />

	<!-- Flagas used For Cancel Button -->


	<s:hidden name="editFlag" />

	<table class="formbg" width="100%">
		<tr>
			<td colspan="4" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Specialization</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>


		<tr>
			<td colspan="4" width="100%">

			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">



				<!-- The Following code Denotes  Include JSP Page For Button Panel -->

				<tr>
					<td colspan="4" width="100%">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<%
							int totalPage = (Integer) request.getAttribute("totalPage");
							int pageNo = (Integer) request.getAttribute("PageNo");
						%>

						<tr>
							<td width="78%" align="left"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						<s:if test="flagShow">		
								<td align="right" width="22%" nowrap="nowrap" id="ctrlShow"><b>Page:</b> <input type="hidden"
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
								title="Last Page" src="../pages/common/img/last.gif" width="10"
								height="10" class="iconImage" /> </a></td></s:if>
								

						</tr>
					</table>
					</td>
				</tr>

				

				<s:if test="specializationView">
					<tr>
						<td colspan="4" width="100%">
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="formbg">
							<tr>
								<td>
								<table width="100%" border="0" align="center" cellpadding="0"
									cellspacing="2">

									<tr>
										<td height="7" colspan="5" class="formtext"><img
											src="../pages/common/css/default/images/space.gif" width="5"
											height="7" /></td>
									</tr>

									<tr>
										<td width="22%" height="22" class="formtext"><label
											class="set" name="spec.name" id="spec.name"
											ondblclick="callShowDiv(this);"><%=label.get("spec.name")%></label>
										:</td>
										<td width="34%" height="22"><s:property
											value="specializationName" /></td>
										<td width="27%" height="22" class="formtext">&nbsp;</td>
										<td width="18%" height="22">&nbsp;</td>
									</tr>

									<tr>
										<td height="22" width="22%" class="formtext" nowrap="nowrap"><label
											class="set" name="spec.abbr" id="spec.abbr"
											ondblclick="callShowDiv(this);"><%=label.get("spec.abbr")%></label>
										:</td>
										<td height="22" width="22%"><s:property
											value="specializationAbbr" /></td>
										<td height="22" class="formtext">&nbsp;</td>
										<td height="22">&nbsp;</td>
									</tr>

									<tr>
										<td width="22%" height="22" valign="top" class="formtext"
											nowrap="nowrap"><label class="set" name="spec.desc"
											id="spec.desc" ondblclick="callShowDiv(this);"><%=label.get("spec.desc")%></label>
										:</td>
										<td height="22" colspan="3"><s:property
											value="specializationDesc" /></td>
									</tr>

									<tr>
										<td width="22%" height="22" class="formtext"><label
											class="set" name="spec.stat" id="spec.stat"
											ondblclick="callShowDiv(this);"><%=label.get("spec.stat")%></label>
										:</td>
										<td height="22"><s:property value="specStatsView" /></td>
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
					<td colspan="5" width="100%">
					<%
					try {
					%>
					<table width="100%" border="0" cellpadding="0" cellspacing="1" class="formbg">

						<tr>

							<td class="formtext" width="100%" colspan="4">
							<table width="100%" border="0" cellpadding="1" cellspacing="1">
								<tr><td width="100%" nowrap="nowrap" colspan="5"><table width="100%"><tr>
									<td class="formhead" width="80%" align="left" colspan="4"><strong
										class="forminnerhead">Specialization List</strong></td>
										<td align="center" width="20%" id="ctrlShow" colspan="1">
									
										<input type="button" class="delete" theme="simple"
											value="Delete" onclick=" return chkDelete()" />
											
											</td></tr></table>
								</td></tr>
								
								<!-- <tr>
									<td height="15" class="formhead" nowrap="nowrap" width="80"><strong
										class="forminnerhead" >Specialization List</strong></td>
									<td align="right"  width="20%"><input type="button"
										class="delete" theme="simple" value="Delete"
										onclick=" chkDelete()" /></td>
								</tr>-->
								<tr>
									<s:hidden name="myPage" id="myPage" />
									<td class="formth" align="center"><b><label
										class="set" name="spec.srno" id="spec.srno"
										ondblclick="callShowDiv(this);"><%=label.get("spec.srno")%></label></b>
									</td>
									<td class="formth" align="center"><b><label
										class="set" name="spec.name" id="spec.name1"
										ondblclick="callShowDiv(this);"><%=label.get("spec.name")%></label></b>
									</td>
									<td class="formth" align="center"><b><label
										class="set" name="spec.abbr" id="spec.abbr1"
										ondblclick="callShowDiv(this);"><%=label.get("spec.abbr")%></label></b>
									</td>
									<td class="formth" align="left"><b><label class="set"
										name="spec.stat" id="spec.stat1"
										ondblclick="callShowDiv(this);"><%=label.get("spec.stat")%></label></b>
									</td>
									<td colspan="2" bordercolor="red" width="20%" align="left"
										class="formth" nowrap="nowrap" id="ctrlShow"><input type="checkbox"
										name="checkAll" id="checkAll" onclick=" checkAllBox()" /></td>

								</tr>
								<%
								int count = 0;
								%>
								<%!int d = 0;%>
								<%
										int cnt = pageNo * 20 - 20;
										int i = 0;
								%>
								<%
								int counter = 0;
								%>
								<s:iterator value="specializationList">

									<tr id="tr1" <%if(count%2==0){
									%> class="tableCell1"
										<%}else{%> class="tableCell2" <%	}count++; %>
										onmouseover="javascript:newRowColor(this);"
										onmouseout="javascript:oldRowColor(this,<%=count%2 %>);">


										<td width="8%" align="center" class="sortableTD"
											title="Double click for edit"
											ondblclick="javascript:callForEdit('<s:property  value="specializationCode"/>');"><%=++cnt%>
										<%
										++i;
										%>
										</td>

										<td width="30%" align="left" class="sortableTD"
											title="Double click for edit"
											ondblclick="javascript:callForEdit('<s:property  value="specializationCode"/>');"><s:hidden
											value="%{specializationCode}" /> <s:property
											value="specializationName" /></td>
										<td width="25%" align="left" class="sortableTD"
											title="Double click for edit"
											ondblclick="javascript:callForEdit('<s:property  value="specializationCode"/>');"><s:property
											value="specializationAbbr" /></td>
										<script>
													specArray[<%=counter%>] = '<s:property  value="specializationCode"/>';
										</script>
										<td width="15%" align="center" class="sortableTD"
											title="Double click for edit"
											ondblclick="javascript:callForEdit('<s:property  value="specializationCode"/>');"><s:property
											value="specializationStatus" /></td>
										<input type="hidden" name="hdeleteCode" id="hdeleteCode<%=i%>"
											value="<%=i%>" />

										<td width="20%" colspan="2" align="center" nowrap="nowrap"
											class="sortableTD" id="ctrlShow"><input type="checkbox"
											class="checkbox" id="confChk<%=i%>" name="confChk"
											onclick="callForDelete1('<s:property  value="specializationCode"/>','<%=i%>')" /></td>
									</tr>
									<%
									counter++;
									%>
								</s:iterator>
								<input type="hidden" name="count" id="count" value="<%=count%>" />
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
</s:if>				
				
				<tr>
					<td width="75%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<s:if test="flagShow">	
					<td  width="25%" align="Right"><b>Total no. of records :<s:property value="totalRecords" /></b></td></s:if>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script>

window.onload = document.getElementById('pageNoField').focus();

//For Addnew Button 

function addnewFun()
{
	document.getElementById('paraFrm').action="SpecializationMaster_addNew.action";
	document.getElementById('paraFrm').submit();
}

// For Save Button


//For Edit Button

function editFun()
{
	document.getElementById('paraFrm').action="SpecializationMaster_edit.action";
	document.getElementById('paraFrm').submit();
}

//For Delete Button

function deleteFun()
{
	var con=confirm('Do you really want to delete this record?')
	if(con){
		   document.getElementById('paraFrm').action="SpecializationMaster_delete.action";
		   document.getElementById('paraFrm').submit();
	} else{
	     return false;
	}
}

//For F9Window

function searchFun()
{
	callsF9(500,300,"SpecializationMaster_f9Action.action");
}

//For Cancel Button

function cancelFun()
{
	
		document.getElementById('paraFrm').action="SpecializationMaster_cancelSec.action";
		document.getElementById('paraFrm').submit();
	
}
//For Report Button

function reportFun()
{
	document.getElementById('paraFrm').target="_blank";
	document.getElementById('paraFrm').action="SpecializationMaster_report.action";
    document.getElementById("paraFrm").submit();
}

function callDelete(){
	if(document.getElementById('paraFrm_specializationName').value==""){
			alert("Please select the record.");
			return false;
			
	}
	 
	var con=confirm('Do you really want to delete this record?');
	if(con){
		   document.getElementById('paraFrm').action="SpecializationMaster_delete.action";
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
		   
			document.getElementById('paraFrm').action='SpecializationMaster_callPage.action';
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
		document.getElementById('paraFrm').action='SpecializationMaster_callPage.action';
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
		document.getElementById('paraFrm').action='SpecializationMaster_callPage.action';
		document.getElementById('paraFrm').submit();
		
	}	*/

/*function callPage(id){
	   	document.getElementById('myPage').value=id;
		document.getElementById('paraFrm_show').value=id;
	    document.getElementById('paraFrm').action="SpecializationMaster_callPage2.action";
	   	document.getElementById('paraFrm').submit();
}*/


   

   
function newRowColor(cell){
			cell.className='Cell_bg_first';

}
	
function oldRowColor(cell,val) {
	
	//if(val=='1'){
	 cell.className='Cell_bg_second';
	//}else {
	//cell.className='tableCell1';
	//	}
}
	
function callForEdit(id){
	  	document.getElementById('paraFrm_hiddencode').value=id;
		
	   	document.getElementById("paraFrm").action="SpecializationMaster_calforedit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
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
	   document.getElementById('paraFrm').action="SpecializationMaster_delete1.action";
	    document.getElementById('paraFrm').submit();
	    }
	    else{
	    //alert("Count ------------------> "+document.getElementById('count').value);
	     var flag=document.getElementById('count').value;
	     document.getElementById('checkAll').checked=false;
	  for(var a=1;a<=flag;a++){	
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



function editRow(id){	
	
	  	document.getElementById('paraFrm_hiddencode').value = id;
	
	   	document.getElementById("paraFrm").action="SpecializationMaster_calforedit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
    }
    
    function on(){
		var val= document.getElementById('selectname').value;
		document.getElementById('paraFrm_show').value=val;
		document.getElementById('myPage').value=eval(val);
		document.getElementById('selectname').value=val;
		document.getElementById('paraFrm').action="SpecializationMaster_callPage.action";
		document.getElementById('paraFrm').submit();
}   
pgshow();

function pgshow()
  	{
  	var pgno=document.getElementById('paraFrm_show').value;
  
  	if(!(pgno==""))
  	 document.getElementById('selectname').value=pgno;
  	}

function checkAllBox(){
	 var rowLen ='<%=d %>';
	
	 if (document.getElementById("checkAll").checked){
	   for(var idx=1;idx<=rowLen;idx++){
	    document.getElementById("confChk"+idx).checked ="true";
	    document.getElementById('hdeleteCode'+idx).value=specArray[idx-1];
	   
      }
	}else{
	  for (var idx = 1; idx <= rowLen; idx++) {
	   document.getElementById("confChk"+idx).checked = false;
	   document.getElementById('hdeleteCode'+idx).value="";
      }
    }	 
}
</script>

