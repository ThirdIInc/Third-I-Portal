<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@page import="java.util.HashMap"%>
<script type="text/javascript">var specArray = new Array();</script>
<script language="JavaScript" type="text/javascript"
	src="..pages/common/include/javascript/sorttable.js"></script>

<script type="text/javascript"
	src="../pages/common/include/javascript/jquery-1.2.2.pack.js"></script>

<script type="text/javascript"
	src="../pages/common/include/javascript/ddaccordion.js"></script>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>


<s:form action="JobDescriptionMaster" validate="true" id="paraFrm"
	 theme="simple">
	<s:hidden name="show" />
	<s:hidden name="hiddenCode" />
	<s:hidden name="editFlag" />
	<s:hidden name="jdMaster.jdCode" />
	<s:hidden name="jdMaster.jdEffDate" />
	<s:hidden name="jdMaster.jdDescName" />
	<s:hidden name="jdMaster.jdDesc" />
	<s:hidden name="jdMaster.jdRoleDesc" />
	<s:hidden name="jdMaster.status" />
	<s:hidden name="recruitmentDays" />
	<s:hidden name="gradeName" />
	<s:hidden name="gradeId" />
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
					<td width="93%" class="txt"><strong class="text_head">Job
					Description</strong></td>
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
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<%
						int totalPage = (Integer) request.getAttribute("abc");
						int pageNo = (Integer) request.getAttribute("xyz");
					%>
					<s:if test="flagView">


						<td align="right" width="22%" nowrap="nowrap" id="ctrlShow">
						<b>Page:</b> <input type="hidden" name="totalPage" id="totalPage"
							value="<%=totalPage%>"> <a href="#"
							onclick="callPage('1','F');"> <img title="First Page"
							src="../pages/common/img/first.gif" width="10" height="10"
							class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('P','P');"> <img title="Previous Page"
							src="../pages/common/img/previous.gif" width="10" height="10"
							class="iconImage" /> </a> <input type="text" name="pageNoField"
							id="pageNoField" theme="simple" size="3" value="<%= pageNo%>"
							onkeypress="callPageText(event);return numbersOnly()"
							maxlength="4" /> of <%=totalPage%> <a href="#"
							onclick="callPage('N','N')"> <img title="Next Page"
							src="../pages/common/img/next.gif" class="iconImage" /> </a>&nbsp; <a
							href="#" onclick="callPage('<%=totalPage%>','L');"> <img
							title="Last Page" src="../pages/common/img/last.gif" width="10"
							height="10" class="iconImage" /> </a></td>


					</s:if>

				</tr>
			</table>
			</td>
		</tr>
		<s:if test="jobView">
			<tr>
				<td colspan="3" width="100%">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td colspan="3" width="100%">
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="2">
							<tr>
								<td colspan="3" width="100%" class="formhead"><img
									src="../pages/common/css/default/images/space.gif" width="5"
									height="7" /></td>
							</tr>

							<tr>
								<td width="22%" height="22" class="formtext"><label
									class="set" name="jdesc.edate" id="jdesc.edate"
									ondblclick="callShowDiv(this);"><%=label.get("jdesc.edate")%></label>
								:</td>
								<td width="34%" height="22"><s:property
									value="jdMaster.jdEffDate" /></td>
								<td width="27%" height="22" class="formtext">&nbsp;</td>
								<td width="18%" height="22">&nbsp;</td>
							</tr>


							<tr>
								<td width="22%" height="22" class="formtext" nowrap="nowrap">
								<label class="set" name="jdesc.jname" id="jdesc.jname"
									ondblclick="callShowDiv(this);"><%=label.get("jdesc.jname")%></label>
								:</td>

								<td width="34%" height="22"><s:property
									value="jdMaster.jdDescName" /></td>
								<td width="27%" height="22" class="formtext">&nbsp;</td>
								<td width="18%" height="22">&nbsp;</td>
							</tr>

							<tr>
								<td width="22%" height="22" valign="top" class="formtext"
									nowrap="nowrap"><label class="set" name="jdesc.jdesc"
									id="jdesc.jdesc" ondblclick="callShowDiv(this);"><%=label.get("jdesc.jdesc")%></label>
								:</td>

								<td height="22" colspan="3"><s:property
									value="jdMaster.jdDesc" /></td>
							</tr>

							<tr>
								<td width="22%" height="22" valign="top" class="formtext"
									nowrap="nowrap"><label class="set" name="jdesc.randr"
									id="jdesc.randr" ondblclick="callShowDiv(this);"><%=label.get("jdesc.randr")%></label>
								:</td>

								<td height="22" colspan="3"><s:property
									value="jdMaster.jdRoleDesc" /></td>
							</tr>
							
							<!-- ADDED BY DHANASHREE BEGINS -->
						<tr>
							<td width="22%" height="22" class="formtext"><label
								class="set" name="rec.days" id="rec.days1"
								ondblclick="callShowDiv(this);"><%=label.get("rec.days")%></label>

							:</td>
							<td width="78%" height="22" colspan="3"><s:property
									value="recruitmentDays"  /></td>
							

						</tr>
						<tr>
							<td width="22%" colspan="1"><label class="set" name="grade"
								id="grade1" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>
							:</td>
							<td width="78%" colspan="2"><s:property
									value="gradeName"  />
							
							</td>

						</tr>
						<!-- ADDED BY DHANASHREE ENDS -->

							<tr>
								<td width="22%" height="22" class="formtext" nowrap="nowrap"><label
									class="set" name="jdesc.statu" id="jdesc.statu"
									ondblclick="callShowDiv(this);"><%=label.get("jdesc.statu")%></label>:
								</td>
								<td width="34%" height="22"><s:property value="pageStatus" />
								</td>
								<td width="27%" height="22" class="formtext">&nbsp;</td>
								<td width="18%" height="22">&nbsp;</td>
							</tr>
							<tr>

								<td colspan="3" align="center"><img
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

		<s:if test="flagView">
			<tr>
				<td colspan="5">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td colspan="5">
						<%
						try {
						%>
						<table width="100%" border="0" cellpadding="0" cellspacing="1"
							class="formbg">

							<tr>

								<td class="formtext" colspan="5">
								<table width="100%" cellpadding="1" cellspacing="1">
									<tr>
										<td width="100%" nowrap="nowrap" colspan="5">
										<table width="100%">
											<tr>
												<td class="formhead" width="85%" align="left"
													nowrap="nowrap" colspan="4"><strong
													class="forminnerhead">Job Description List:</strong></td>
												<td align="center" width="15%" colspan="1"><s:if
													test="jdMaster.deleteFlag">
													<input type="button" id="ctrlShow" class="delete"
														theme="simple" value=" Delete"
														onclick=" return chkDelete()" />
												</s:if></td>
											</tr>
										</table>
										</td>
									</tr>
									<tr>
										<s:hidden name="myPage" id="myPage" />
										<td class="formth" align="center" width="8%" nowrap="nowrap"><b><label
											class="set" name="jdesc.serno" id="jdesc.serno"
											ondblclick="callShowDiv(this);"><%=label.get("jdesc.serno")%></label></b>
										</td>
										<td class="formth" align="center" width="15%"><b><label
											class="set" name="jdesc.edate" id="jdesc.edate1"
											ondblclick="callShowDiv(this);"><%=label.get("jdesc.edate")%></label></b>
										</td>
										<td class="formth" align="center" width="52%"><b><label
											class="set" name="jdesc.jname" id="jdesc.jname1"
											ondblclick="callShowDiv(this);"><%=label.get("jdesc.jname")%></label></b>
										</td>
										<td class="formth" align="center" width="10%"><b><label
											class="set" name="jdesc.statu" id="jdesc.statu1"
											ondblclick="callShowDiv(this);"><%=label.get("jdesc.statu")%></label></b>
										</td>

										<td align="left" class="formth" width="15%" id="ctrlShow">

										<input type="checkbox" name="checkAll" id="checkAll"
											onclick=" checkAllBox()" /></td>
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
									<s:iterator value="jdList">

										<tr <%if(count%2==0){
									%> class="tableCell1"
											<%}else{%> class="tableCell2" <%	}count++; %>
											onmouseover="javascript:newRowColor(this);"
											onmouseout="javascript:oldRowColor(this,<%=count%2 %>);">


											<td width="8%" align="center" class="sortableTD"
												title="Double click for edit"
												ondblclick="javascript:callForEdit('<s:property  value="jdCode"/>');"><%=++cnt%>
											<%
											++i;
											%>
											</td>
											<s:hidden value="%{jdCode}"></s:hidden>

											<td width="15%" align="center" class="sortableTD"
												title="Double click for edit"
												ondblclick="javascript:callForEdit('<s:property  value="jdCode"/>');"><s:property
												value="jdEffDate" /></td>
											<td width="52%" align="left" class="sortableTD"
												title="Double click for edit"
												ondblclick="javascript:callForEdit('<s:property  value="jdCode"/>');"><s:property
												value="jdDescName" /></td>
											<script>
													specArray[<%=counter%>] = '<s:property  value="jdCode"/>';
											</script>
											<!--<td width="25%" align="left"><s:property value="jdDesc" /></td>
											<td width="30%" align="left"><s:property value="jdRoleDesc" /></td>
											-->
											<td width="10%" align="center" class="sortableTD"
												title="Double click for edit"
												ondblclick="javascript:callForEdit('<s:property  value="jdCode"/>');"><s:property
												value="status" /></td>


											<input type="hidden" name="hdeleteCode"
												id="hdeleteCode<%=i%>" />
											</td>
											<td align="center" class="sortableTD" width="15%"
												id="ctrlShow"><input type="checkbox" class="checkbox"
												id="confChk<%=i%>" name="confChk"
												onclick="callForDelete1('<s:property  value="jdCode"/>','<%=i%>')" /></td>
										</tr>
										<%
										counter++;
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

		<tr>
			<td width="78%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

			<s:if test="flagView">
				<td colspan="3" align="Right"><b>Total no. of records
				:&nbsp;<s:property value="totalRecords" /></b></td>
			</s:if>
		</tr>
	</table>
</s:form>
<script> 
//window.onload=   document.getElementById('pageNoField').focus();
function saveFun()
{
	var f9Fields= ["paraFrm_jdMaster_jdEffDate", "paraFrm_jdMaster_jdDescName",'paraFrm_jdMaster_jdRoleDesc'];
	var fieldName=["paraFrm_jdMaster_jdEffDate", "paraFrm_jdMaster_jdDescName", 
  					'paraFrm_jdMaster_jdRoleDesc'];
	var lableName=["jdesc.edate","jdesc.jname","jdesc.randr"];
	var type=["enter/select","enter","enter"];
	if(!validateDate('paraFrm_jdMaster_jdEffDate','jdesc.edate'))
    return false;  
	if(!validateBlank(fieldName,lableName,type))
          return false;
          
    if(!f9specialchars(f9Fields))
	return false;
	
	var desc =	document.getElementById('paraFrm_jdMaster_jdDescName').value;
	var rolesandres=document.getElementById('paraFrm_jdMaster_jdRoleDesc').value;
	if(desc != "" && desc.length >250){
		alert("Maximum length of <%=label.get("jdesc.jdesc")%> is 250 characters.");
		return false;
    }    
	if(rolesandres != "" && rolesandres.length > 250){
		alert("Maximum length of <%=label.get("jdesc.randr")%> is 250 characters.");
		return false;
	}
	document.getElementById('paraFrm').action="JobDescriptionMaster_save.action";
	document.getElementById('paraFrm').submit();
	return true;
}
function cancelFun(){
    	document.getElementById('paraFrm').action="JobDescriptionMaster_cancelSecond.action";
        document.getElementById("paraFrm").submit();
    
    }
//For Report Button

function reportFun()
{
	document.getElementById('paraFrm').action="JobDescriptionMaster_report.action";
    document.getElementById("paraFrm").submit();
}
    
function callingStage(stage){
		document.getElementById('paraFrm').action = "JobDescriptionMaster_cancel.action?callingStage="+stage;
		document.getElementById('paraFrm').submit();
	}
	
	
	 
	 function deleteRecord(){
		if(!callDelete('paraFrm_jdMaster_jdDescName'))return false;
		
		else{
			document.getElementById('paraFrm').action="JobDescriptionMaster_delete.action";
		   document.getElementById('paraFrm').submit();
		}
	}
	
  function savevalidation(){
  	var f9Fields= ["paraFrm_jdMaster_jdEffDate", "paraFrm_jdMaster_jdDescName", 'paraFrm_jdMaster_jdDesc', 
  					'paraFrm_jdMaster_jdRoleDesc'];
  
    var fieldName = ['paraFrm_jdMaster_jdEffDate'];
	var lableName = ["jdesc.edate"];
	var flag = ["enter/select"];
	  
	   if(!validateBlank(fieldName,lableName,flag))
          return false;
	
	if(!validateDate("paraFrm_jdMaster_jdEffDate","<%=label.get("jdesc.edate")%>")){
		return false;
	}
	
	var fieldName1 = ['paraFrm_jdMaster_jdDescName'];
	var lableName1 = ["jdesc.jname"];
	var flag1      = ["enter"];
	
	   if(!validateBlank(fieldName1,lableName1,flag1))
          return false;
	
	var desc    =	document.getElementById('paraFrm_jdMaster_jdDesc').value;
	
	if(desc != "" && desc.length >950){
		alert("Maximum length of <%=label.get("jdesc.jdesc")%> is 950 characters.");
		return false;
    }    
	
	var fieldName2 = ['paraFrm_jdMaster_jdRoleDesc'];
	var lableName2 = ["jdesc.randr"];
	var flag2      = ["enter"];
	
	  if(!validateBlank(fieldName2,lableName2,flag2))
          return false;
	  
	
	var jobRole =	document.getElementById('paraFrm_jdMaster_jdRoleDesc').value;
  	
    if(jobRole.length >950){
 		alert("Maximum length of <%=label.get("jdesc.randr")%>  is 950 characters.");
		return false;
    }    
    
    if(!f9specialchars(f9Fields))
	return false;   
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
		    document.getElementById('paraFrm').action='JobDescriptionMaster_callPage.action';
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
		var p=document.getElementById('myPage').value;
		id=eval(p)-1;
		}
		if(id=='N'){
		var p=document.getElementById('myPage').value;
		id=eval(p)+1;
		}
		document.getElementById('myPage').value=id;
		//document.getElementById('paraFrm_show').value=id;
	    document.getElementById('paraFrm').action="JobDescriptionMaster_callPage.action";
	   	document.getElementById('paraFrm').submit();
	}
   
   function onPage()
   {
  	var val= document.getElementById('selectname').value;
	document.getElementById('paraFrm_show').value=val;
	 document.getElementById('myPage').value=eval(val);
	 document.getElementById('selectname').value=val;
	   document.getElementById('paraFrm').action="JobDescriptionMaster_callPage.action";
	   
	   document.getElementById('paraFrm').submit();
   }
   
   function chkDelete(){
	 var flag='<%=d %>';
	
	 if(chk()){
	 var con=confirm('Do you really want to delete the records?');
	 if(con){
	   document.getElementById('paraFrm').action="JobDescriptionMaster_deleteRow.action";
	    document.getElementById('paraFrm').submit();
	    }
	    else{
	     var flag='<%=d %>';
	     document.getElementById("checkAll").checked = false;
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
	
	
	function newRowColor(cell)
   	 {
			cell.className='Cell_bg_first';

	}
	
	function oldRowColor(cell,val) {
	
	//if(val=='1'){
	 cell.className='Cell_bg_second';
	//}
	//else 
	//cell.className='tableCell1';
		
	}
	
	function callForEdit(id){
	  	document.getElementById('paraFrm_hiddenCode').value=id;
	
	   	document.getElementById("paraFrm").action="JobDescriptionMaster_calforedit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
   }
	
		function callForDelete1(id,i)
	   {
	   
	
	   if(document.getElementById('confChk'+i).checked == true)
	   {	  
	    document.getElementById('hdeleteCode'+i).value=id;
	   }
	   else
	   document.getElementById('hdeleteCode'+i).value="";
	   
   }
	

	pgshow();
  	function pgshow()
  	{
  	var pgno=document.getElementById('paraFrm_show').value;
  
  	if(!(pgno==""))
  	 document.getElementById('selectname').value=pgno;
  	}
	  function addnewFun(){
   
      	document.getElementById('paraFrm').action="JobDescriptionMaster_addNew.action";
        document.getElementById("paraFrm").submit();
    
    }
    
    function searchFun(){
    		callsF9(500,325,'JobDescriptionMaster_f9action.action');
    
    }
    
    function editFun(){
    	document.getElementById('paraFrm').action="JobDescriptionMaster_edit.action";
        document.getElementById("paraFrm").submit();
    
    }
    
    function cancelFun(){
    	document.getElementById('paraFrm').action="JobDescriptionMaster_cancelSecond.action";
        document.getElementById("paraFrm").submit();
    
    }
    
    function deleteFun(){
   		var conf=confirm("Do you really want to delete this record ?");
			if(conf){
				document.getElementById('paraFrm').action = "JobDescriptionMaster_delete.action";
		   		document.getElementById("paraFrm").submit();
					
			}else{
			
			     return false;
			}
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
