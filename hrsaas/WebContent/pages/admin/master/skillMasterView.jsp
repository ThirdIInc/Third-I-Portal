<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<!--<script language="JavaScript" type="text/javascript"
	src="..pages/common/include/javascript/sorttable.js"></script>-->
<script type="text/javascript">
var skillArray = new Array();
</script>
<s:form action="SkillMaster" id="paraFrm" validate="true" theme="simple">
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />
	<s:hidden name="cancelFlg"/>
	<s:hidden name="skillMaster.skills"/>
	<s:hidden name="skillMaster.skillsAbbr"/>
	<s:hidden name="desciption"/>
	<s:hidden name="status"/>
	<s:hidden  name="skillMaster.skillCode" />
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
					<td width="93%" class="txt"><strong class="text_head">Skill</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="78%">
							<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp"/></td> 
                               <%
							int totalPage = (Integer) request.getAttribute("totalPage");
							int pageNo = (Integer) request.getAttribute("PageNo");
							%>
                                 <s:if test="flagShow"> 
                                          <td align="right" nowrap="nowrap" id="ctrlShow"><b>Page:</b>
					 <input type="hidden" name="totalPage"  id="totalPage" value="<%=totalPage%>">
						 	<a href="#" onclick="callPage('1','F');" >
										<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
									</a>&nbsp;
									<a href="#" onclick="callPage('P','P');" >
										<img  title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
									</a> 
							    <input type ="text" name="pageNoField" id="pageNoField" theme="simple" size="3"  value="<%= pageNo%>"  onkeypress="callPageText(event);return numbersOnly()"   maxlength="4" title="press Enter" /> of <%=totalPage%>
						 		 		<a href="#" onclick="callPage('N','N');" >
											<img  title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
										</a>&nbsp;
										<a href="#" onclick="callPage('<%=totalPage%>','L');" >
											<img title="Last Page"  src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
										</a>
							 
							</td>
					 </s:if>	  
					</table>
					</td>
				</tr>
				
			
				<tr>
					<td colspan="3">
					<s:if test="flagView">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="98%" border="0" align="center" cellpadding="0"
								cellspacing="2">

								<tr>
									<td height="7" colspan="5" class="formtext"><img
										src="../pages/common/css/default/images/space.gif" width="5"
										height="7" /></td>
								</tr>

								<tr>
									<td width="20%" height="22" class="formtext"><b><label
										class="set" name="skill.name" id="skill.name" ondblclick="callShowDiv(this);"><%=label.get("skill.name")%></label></b> :</td>
									<td height="22"><s:hidden name="hskillCode" />
										
										<s:property value="skillMaster.skills"  />
								</td>
									
								</tr>
								<tr>
									<td height="22" class="formtext" width="15%"><label
										class="set" name="skill.abbr" id="skill.abbr" ondblclick="callShowDiv(this);"><%=label.get("skill.abbr")%></label>
									:</td>
									<td height="22">
									
										<s:property value="skillMaster.skillsAbbr" />
									</td>
								</tr>

								<tr>
									<td height="22" valign="top" class="formtext" width="15%"><label
										class="set" name="skill.desc" id="skill.desc" ondblclick="callShowDiv(this);"><%=label.get("skill.desc")%></label>:
									</td>
									<td height="22">
										
										<s:property value="desciption" />
									</td>
								</tr>

								<tr>
									<td height="22" class="formtext"><label
										class="set" name="skill.stat" id="skill.stat" ondblclick="callShowDiv(this);"><%=label.get("skill.stat")%></label>:
									</td>
									<td height="22">
								
									<s:property value="status"/></td>
								</tr>





								

							</table>
							</td>
						</tr>
					</table>
					</s:if>
					</td>
				</tr> 

<s:if test="flagShow">
				 

				<tr>
					<td colspan="3">
					<%
			try {
			%>
					<table width="100%" border="0" cellpadding="0" cellspacing="1"
						class="formbg">

						<tr>

							<td class="formtext">
							<table width="100%" border="0" cellpadding="1" cellspacing="1" id="tblSkill">
								<tr>
									<td colspan="4" ><strong class="forminnerhead">Skill List:</strong></td>
										<td colspan="1" align="center"> <s:if test="skillMaster.deleteFlag">
										<input type="button" id="ctrlShow" class="delete" theme="simple" 	value="Delete" onclick=" return chkDelete();" />
									</s:if> </td>
								</tr>
								<tr>
									<s:hidden name="myPage" id="myPage" />
									<td class="formth" align="center" width="8%"><b><label class="set" name="skill.srno"
										id="skill.srno" ondblclick="callShowDiv(this);"><%=label.get("skill.srno")%></label></b>
									</td>
									<td class="formth" align="center" width="43%"><b><label class="set" name="skill.name"
										id="skill.name1" ondblclick="callShowDiv(this);"><%=label.get("skill.name")%></label></b>
									</td>
									<td class="formth" align="center" width="27%"><b><label class="set" name="skill.abbr"
										id="skill.abbr1" ondblclick="callShowDiv(this);"><%=label.get("skill.abbr")%></label></b>
									</td>
									<td class="formth" align="center" width="10%"><b><label class="set" name="skill.stat"
										id="skill.stat1" ondblclick="callShowDiv(this);"><%=label.get("skill.stat")%></label></b>
									</td>
									<td align="center" class="formth" nowrap="nowrap" width="12%" id="ctrlShow">
									<input type="checkbox"  name="chkSkillAll" id="paraFrm_chkSkillAll"  onclick="return callSkillAll();"/>
									 
									</td>
							</tr>

									<%
							int count = 0;
							%>
									<%!int d = 0;%>
									<%
							int cnt= pageNo*20-20;	
							int i = 0;
							%>	<%int counter = 0;%>
									<s:iterator value="iteratorlist">

										<tr <%if(count%2==0){
									%> class="tableCell1"
											<%}else{%> class="tableCell2" <%	}count++; %>
											onmouseover="javascript:newRowColor(this);"
										
											onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
											>


													<td width="8%" align="center" class="sortableTD"
											title="Double click for edit"
											ondblclick="javascript:callForEdit('<s:property  value="skillsCode"/>');"
											
											><%=++cnt%> <%++i;%>
											</td>
											<s:hidden value="%{skillsCode}"></s:hidden>

											<td width="43%" align="left" class="sortableTD"
											title="Double click for edit"
											ondblclick="javascript:callForEdit('<s:property  value="skillsCode"/>');"
											><s:property
												value="skillItt" /></td>
											<td width="27%" align="left" class="sortableTD"
											title="Double click for edit"
											ondblclick="javascript:callForEdit('<s:property  value="skillsCode"/>');"
											
											><s:property
												value="skillAbbrItt" /> <input type="hidden"
												name="hdeleteCode" id="hdeleteCode<%=i%>" /></td>
												
											<script type="text/javascript">
							
													skillArray[<%=counter%>] = '<s:property  value="skillsCode"/>';
							
											</script>	
												
											<td width="10%" align="center" class="sortableTD"
											title="Double click for edit"
											ondblclick="javascript:callForEdit('<s:property  value="skillsCode"/>');"
											><s:property value="status" />
											</td>
											<td align="center" id="ctrlShow" nowrap="nowrap" class="sortableTD" width="12%"><input
												type="checkbox" class="checkbox" id="confChk<%=i%>"
												name="confChk"
												onclick="callForDelete1('<s:property  value="skillsCode"/>','<%=i%>')" /></td>
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
				</s:if>
			</table>
			</td>
		</tr>
		
		<tr>
							<td width="78%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp"/></td>

<td align="right" width="22%"><s:if test="flagShow"><strong class="forminnerhead">Total no. of Records :</strong><s:property value="totRecord"/></s:if></td>
						</tr>
	</table>
</s:form>
<script>

//window.onload=   document.getElementById('pageNoField').focus();
function callingStage(stage){
		document.getElementById('paraFrm').action = "SkillMaster_cancel.action?callingStage="+stage;
		document.getElementById('paraFrm').submit();
	}


	
	function savevalidation()
 {
 
var fieldName = ["paraFrm_skillMaster_skills","paraFrm_skillMaster_skillsAbbr"];
var lableName = ["skill.name","skill.abbr"];
var flag = ["enter","enter"];

var desc=document.getElementById('paraFrm_desciption').value;


	
     if(!validateBlank(fieldName,lableName,flag))
          return false;
   if(desc.length >250){
 		alert("Maximum length of <%=label.get("skill.desc")%> is 250 characters.");
		return false;
   
   }     
     
   
     	
    }
    
 function on(){
		var val= document.getElementById('selectname').value;
		document.getElementById('paraFrm_show').value=val;
		document.getElementById('myPage').value=eval(val);
		document.getElementById('selectname').value=val;
		document.getElementById('paraFrm').action="SkillMaster_callPage.action";
		document.getElementById('paraFrm').submit();
}   
    
    
     function deleteRecord(){
		if(!callDelete('paraFrm_skillMaster_skills'))return false;
		
		else{
			document.getElementById('paraFrm').action="SkillMaster_delete.action";
		   document.getElementById('paraFrm').submit();
		}
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
   
   	function newRowColor(cell)
   	 {
			cell.className='Cell_bg_first';

	}
	
	function oldRowColor(cell,val) {
	
	
	 cell.className='Cell_bg_second';
	
		
	}
	
	function chkDelete()
	{
	
	 if(chk()){
	 var con=confirm('Do you really want to delete the records ?');
	 if(con){
	   document.getElementById('paraFrm').action="SkillMaster_deleteRow.action";
	    document.getElementById('paraFrm').submit();
	    }
	    else{
	    var flag='<%=d %>';
	  for(var a=1;a<=flag;a++){	
	document.getElementById('paraFrm_chkSkillAll').checked = false;
	document.getElementById('confChk'+a).checked = false;
	document.getElementById('hdeleteCode'+a).value="";
	}
	     return false;
	 }
	 }
	 else {
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
	    document.getElementById('paraFrm').action="SkillMaster_callPage.action";
	   	document.getElementById('paraFrm').submit();
	}
  /*  function callPage(id){
	
		
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
		document.getElementById('paraFrm').action='SkillMaster_callPage.action';
		document.getElementById('paraFrm').submit();
		
	}*/	
   pgshow();

function pgshow()
  	{
  	var pgno=document.getElementById('paraFrm_show').value;
  
  	if(!(pgno==""))
  	 document.getElementById('selectname').value=pgno;
  	}    
  	
    	

function maxlength()
{
	var length=document.getElementById('paraFrm_desciption').value;
	var l=length.length;
	if(l=='300')
	{
		return false;
	}
	else
	{
		return true;
	}
}	


	function callForEdit(id){
	  	document.getElementById('paraFrm_hiddencode').value=id;
		document.getElementById("paraFrm").action="SkillMaster_calforedit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
   }
   
   function editRow(id){	
	
	  	document.getElementById('paraFrm_hiddencode').value = id;
	   	document.getElementById("paraFrm").action="SkillMaster_calforedit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
    }	
    
    function addnewFun(){
    	document.getElementById('myPage').value ="1";
    	document.getElementById("paraFrm").action="SkillMaster_addNew.action";
    	document.getElementById("paraFrm").submit();
    
    }
    
    function searchFun(){

		
    	callsF9(500,325,'SkillMaster_f9action.action');
    	
    }
    
   function editFun(){
    	document.getElementById("paraFrm").action="SkillMaster_edit.action";
    	document.getElementById("paraFrm").submit();
    
    }
    
    function cancelFun(){
    	document.getElementById("paraFrm").action="SkillMaster_cancelSec.action";
    	document.getElementById("paraFrm").submit();
    
    }
    
    
    function deleteFun(){
    	 var con=confirm('Do you really want to delete the record ?');
    	if(con){
    	document.getElementById("paraFrm").action="SkillMaster_delete.action";
    	document.getElementById("paraFrm").submit();
    	}
    }
//For Report Button

function reportFun()
{
	document.getElementById('paraFrm').action="SkillMaster_report.action";
    document.getElementById("paraFrm").submit();
}
  
  
  function callSkillAll()
  {
	 	  
		var tbl = document.getElementById('tblSkill');
		var rowLen ='<%=d %>';
		
	
		if (document.getElementById("paraFrm_chkSkillAll").checked){
			
		  for(var idx=1;idx<=rowLen;idx++){
		   document.getElementById("confChk"+idx).checked ="true";
		  document.getElementById('hdeleteCode'+idx).value=skillArray[idx-1];//alert("hhh"+skillArray.length);
		  
		  }
			
			
	}else{
	for (var idx = 1; idx <= rowLen; idx++) {
	
	document.getElementById("confChk"+idx).checked = false;
	document.getElementById('hdeleteCode'+idx).value="";
     }
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
		    document.getElementById('paraFrm').action='SkillMaster_callPage.action';
			document.getElementById('paraFrm').submit();
		}
		
	}    
    
</script>

