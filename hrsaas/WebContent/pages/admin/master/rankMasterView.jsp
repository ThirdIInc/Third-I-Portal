<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript">
var rankArray = new Array();
</script>
<s:form action="RankMaster" validate="true" id="paraFrm" validate="true"
	theme="simple">
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />
	<s:hidden name="editFlag"/>
	<s:hidden name="cancelFlag"/>
	
	<s:hidden name="designationCode" /> 
<s:hidden name="designationName"/>
<s:hidden name="designationAbbr"/>
<s:hidden name="designationDesc"/>
<s:hidden name="designationStatus"/>
	<table class="formbg" width="100%">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <imgf
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Designation</strong></td>
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
				<!-- The Following code Denotes  Include JSP Page For Button Panel -->

				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							
							<td width="75%"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						
							<td align="right"><b>Page:</b><%
					int totalPage = (Integer) request.getAttribute("abc");
					int pageNo = (Integer) request.getAttribute("xyz");
					%>
						<%	if(pageNo != 1)
								{
									if(pageNo > totalPage){}
									else{
							%>		<a href="#" onclick="callPage('1');" >
										<img src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
									</a>&nbsp;
									<a href="#" onclick="callPage('P');" >
										<img src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
									</a>
							<%	}
								}
								if(totalPage <= 5)
								{
									if(totalPage == 1)
									{
							%>			<b><u><%=totalPage%></u></b>
							<%		}
									else
									{
										for(int z = 1; z <= totalPage; z++)
										{
											if(pageNo == z)
											{
							%>					<b><u><%=z%></u></b>
							<%				}
											else
											{
							%>					<a href="#" onclick="callPage('<%=z%>');"><%=z%></a>
							<%				}
										}
									}
								}
								else
								{
									if(pageNo == totalPage - 1 ||pageNo == totalPage)
									{									
										for(int z = pageNo - 2; z <= totalPage; z++)
										{
											if(pageNo == z)
											{
							%>					<b><u><%=z%></u></b>
							<%				}
											else
											{
							%>					&nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a>
							<%				}
										}
									}
									else if(pageNo <= 3)
									{
										for(int z = 1; z <= 5; z++)
										{
											if(pageNo == z)
											{
							%>					<b><u><%=z%></u></b>
							<%				}
											else
											{
							%>					&nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a>
							<%				}
										}						
									}
									else if(pageNo > 3)
									{
										for(int z = pageNo - 2; z <= pageNo + 2; z++)
										{
											if(pageNo == z)
											{
							%>					<b><u><%=z%></u></b>
							<%				}
											else
											{
							%>					&nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a>
							<%				}
										}					
									}
								}
								if(!(pageNo == totalPage))
								{
									if(totalPage == 0 || pageNo > totalPage){}
									else
									{
							%>			....<%=totalPage%>
										<a href="#" onclick="callPage('N')" >
											<img src="../pages/common/img/next.gif" class="iconImage" />
										</a>&nbsp;
										<a href="#" onclick="callPage('<%=totalPage%>');" >
											<img src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
										</a>
							<%		}
								}
							%><select name="selectname" onchange="on()" id="selectname">
								<%
						for (int i = 1; i <= totalPage; i++) {
						%>

								<option value="<%=i%>"><%=i%></option>
								<%
						}
						%>
							</select>
							
							</td>
						</tr>

					</table>
					</td>
				</tr>
						</tr>
					</table>
					</td>
				</tr>


				

<s:if test="designationView">
				<tr>
					<td colspan="3">
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
									<td width="22%" height="22" class="formtext"><label name="desg.name"
										class="set" id="desg.name" ondblclick="callShowDiv(this);"><%=label.get("desg.name")%></label>
									 :</td>
									<td width="34%" height="22">
											
											<s:property value="designationName"/>
									</td>
									<td width="27%" height="22" class="formtext">&nbsp;</td>
									<td width="18%" height="22">&nbsp;</td>
								</tr>

								<tr>
									<td width="22%" height="22" class="formtext" nowrap="nowrap"><label name="desg.abbr"
										class="set" id="desg.abbr" ondblclick="callShowDiv(this);"><%=label.get("desg.abbr")%></label>
									 :</td>
									<td height="22" width="22%">
									
										<s:property value="designationAbbr"/>
									</td>
									<td height="22" class="formtext">&nbsp;</td>
									<td height="22">&nbsp;</td>
								</tr>

								<tr>
									<td width="22%" height="22" valign="top" class="formtext" nowrap="nowrap"><label name="desg.desc"
										class="set" id="desg.desc" ondblclick="callShowDiv(this);"><%=label.get("desg.desc")%></label>:
									</td>
									<td height="22" colspan="3">
									
										<s:property value="designationDesc"/>
									</td>
								</tr>

								<tr>
									<td width="20%" height="22" class="formtext"><label name="is.active"
										class="set" id="is.active" ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label>:
									</td>
									<td>
										<s:property value="pageStatus"/>
									</td>
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
				<tr>
					<td colspan="3"><img
						src="../pages/common/css/default/images/space.gif" width="5"
						height="4" /></td>
				</tr>

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

							<td class="formtext">
							<table width="100%" border="0" cellpadding="1" cellspacing="1">
								<tr>
									<td height="15" class="formhead" nowrap="nowrap"><strong
										class="forminnerhead">Designation List</strong></td>
								</tr>
								<tr>
									<s:hidden name="myPage" id="myPage" />
									<td class="formth" width="5%" align="center"><b><label class="set" name="desg.srno"
										id="desg.srno" ondblclick="callShowDiv(this);"><%=label.get("desg.srno")%></label>.</b>
									</td>
									<td class="formth" align="center"><b><label class="set" name="desg.name"
										id="desg.name1" ondblclick="callShowDiv(this);"><%=label.get("desg.name")%></label></b>
									</td>
									<td class="formth" align="center"><b><label class="set" name="desg.abbr"
										id="desg.abbr1" ondblclick="callShowDiv(this);"><%=label.get("desg.abbr")%></label></b>
									</td>
									<td class="formth" align="center"><b><label class="set" name="is.active"
										id="is.active" ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label></b>
									</td>
									<td align="left" class="formth" nowrap="nowrap">
									<input type="checkbox" name="checkAll" id="checkAll"
										       onclick=" checkAllBox()"/>
										<input type="button" class="delete" theme="simple"
											value="     Delete  " onclick=" return chkDelete()" />
											
									</td>


									<%
									int count = 0;
									%>
									<%!int d = 0;%>
									<%
											int cnt = pageNo * 20 - 20;
											int i = 0;
									%>
									<%int counter = 0;%>
									<s:iterator value="designationList">

										<tr id="tr1" <%if(count%2==0){
									%> class="tableCell1"
											<%}else{%> class="tableCell2" <%	}count++; %>
											onmouseover="javascript:newRowColor(this);"
											onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
											>


											<td width="5%" align="center" class="sortableTD"
											title="Double click for edit"
											ondblclick="javascript:callForEdit('<s:property  value="designationCode"/>');">
											<%=++cnt%> <%
 ++i;
 %>
											</td>


											<td width="40%" align="left" class="sortableTD"
											title="Double click for edit"
											ondblclick="javascript:callForEdit('<s:property  value="designationCode"/>');">
											<s:hidden value="%{designationCode}"></s:hidden> <input type="hidden"
												name="hdeleteCode" id="hdeleteCode<%=i%>" /> <s:property
												value="designationName" /></td>
											<td width="40%" align="left" class="sortableTD" title="Double click for edit"
											ondblclick="javascript:callForEdit('<s:property  value="designationCode"/>');">
											<s:property value="designationAbbr" /></td>
											<script>
													rankArray[<%=counter%>] = '<s:property  value="designationCode"/>';
									</script>	
											<td width="40%" align="left" class="sortableTD" title="Double click for edit"
											ondblclick="javascript:callForEdit('<s:property  value="designationCode"/>');">
											<s:property value="designationStatus" /></td>
											<input type="hidden" name="hdeleteCode"
												id="hdeleteCode<%=i%>" value="<%=i%>"/>
												
												
											</td>
											<td align="left" nowrap="nowrap" class="sortableTD"><input
												type="checkbox" class="checkbox" id="confChk<%=i%>"
												name="confChk"
												onclick="callForDelete1('<s:property  value="designationCode"/>','<%=i%>')" /></td>
										</tr>
										<%counter++; %>
									</s:iterator>
									<input type="hidden" name="count" id="count" value="<%=count%>"/>
									<%
									d = i;
									%>
								</tr>


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
					<td colspan="3"><img
						src="../pages/common/css/default/images/space.gif" width="5"
						height="4" /></td>
				</tr>
				
				<tr>
					<td width="75%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td colspan="3" width="100%" align="Right">
					<b>Total No. of Records :&nbsp;<s:property value="totalRecords" /></b>
					</td>	
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script>

var f9Fields= ["paraFrm_designationName","paraFrm_designationAbbr"];
var fieldName = ["paraFrm_designationName","paraFrm_designationAbbr"];
var lableName = ["desg.name","desg.abbr"];
var type = ['enter','enter'];

//For Addnew Button 

function addnewFun()
{
	document.getElementById('paraFrm').action="RankMaster_addNew.action";
	document.getElementById('paraFrm').submit();
}

// For Save Button

function saveFun()
{
	  if(!validateBlank(fieldName,lableName,type))
          return false;
    
    if(!f9specialchars(f9Fields))
	return false;
	
	var desc = document.getElementById("paraFrm_designationDesc").value;
	
	if(desc != "" && desc.length >200){
		alert("Maximum length of "+document.getElementById('desg.desc').innerHTML.toLowerCase()+ "is 200 characters.");
		return false;
	}
	
	document.getElementById('paraFrm').action="RankMaster_save.action";
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').submit();
	return true;
}

//For Edit Button

function editFun()
{
	document.getElementById('paraFrm').action="RankMaster_edit.action";
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').submit();
}

//For Delete Button

function deleteFun()
{
	var con=confirm('Do you really want to delete this record?')
	if(con){
		   document.getElementById('paraFrm').action="RankMaster_delete.action";
		   document.getElementById('paraFrm').target = "_self";
		   document.getElementById('paraFrm').submit();
	} else{
	     return false;
	}
	//document.getElementById('paraFrm').action="SpecializationMaster_delete.action";
	//document.getElementById('paraFrm').submit();
}

//For F9Window

function searchFun()
{
        myWin = window.open('','myWin','top=260,left=250,width=700,height=400,scrollbars=no,toolbars=no,status=yes,resizable=yes');
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action = 'RankMaster_f9Action.action';
		document.getElementById("paraFrm").submit();
	//callsF9(500,300,"RankMaster_f9Action.action");
	//document.getElementById('paraFrm').submit();
}

//For Cancel Button

function cancelFun(){
    	document.getElementById('paraFrm').action="RankMaster_cancelSecond.action";
    	
        document.getElementById("paraFrm").submit();
    
}

//For Report Button

function reportFun()
{
	document.getElementById('paraFrm').action="RankMaster_report.action";
    document.getElementById("paraFrm").submit();
}
function saveValidation(){	
  var val=document.getElementById('paraFrm_designationName').value;
	  if(val==""){
		  alert('Please enter designation name');
		  return false;
	  }
  
	  var value = LTrim(RTrim(val));
	  if(val!=value){
		alert('Space not Allowed in Designation Name!');
		 return false;
	  }
	

	 var abbr=document.getElementById('paraFrm_designationAbbr').value;
		  
	if(abbr==""){
		alert('Please enter Designation Abbr!');
		 return false;
	}
	var value = LTrim(RTrim(abbr));
	if(abbr!=value)	{
			alert('Space not Allowed in Designation Abbr.');
			return false;
	}
	document.getElementById('paraFrm').target = "_self";
	  return true;
}  
	

function maxlength()
{
	var length=document.getElementById('paraFrm_designationDesc').value;
	var l=length.length;
	if(l=='250')
	{
		return false;
	}
	else
	{
		return true;
	}
}	
	
function callDelete(){
	if(document.getElementById('paraFrm_designationName').value==""){
			alert("Please select the record.");
			return false;
			
	}
	 
	var con=confirm('Are you sure to delete this record?');
	if(con){
		   document.getElementById('paraFrm').action="RankMaster_delete.action";
		   document.getElementById('paraFrm').submit();
	} else{
	     return false;
	   }
	
}
	
function updateValidation(){   
var val=document.getElementById('paraFrm_designationName').value;
	if(val==""){
		  alert('Please enter designation name!');
		  return false;
	}
  
   	var value = LTrim(RTrim(val));
	if(val!=value){
		alert('Space not allowed in designation name!');
		 return false;
	}
	var abbr=document.getElementById('paraFrm_designationAbbr').value;
  
    if(abbr==""){
		  alert('Please enter designation abbr.');
		  return false;
  	}
  	var value = LTrim(RTrim(abbr));
	if(abbr!=value){
		alert('Space not allowed in designation abbr.');
		 return false;
	}
   	return true;
}


function callPage(id){
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
	    document.getElementById('paraFrm').action="RankMaster_callPage.action";
	    document.getElementById('paraFrm').target = "_self";
	   	document.getElementById('paraFrm').submit();
}	

function on(){
		var val= document.getElementById('selectname').value;
		document.getElementById('paraFrm_show').value=val;
		document.getElementById('myPage').value=eval(val);
		document.getElementById('selectname').value=val;
		document.getElementById('paraFrm').action="RankMaster_callPage.action";
		document.getElementById('paraFrm').target = "_self";
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
	
	   	document.getElementById("paraFrm").action="RankMaster_calforedit.action";
	   	document.getElementById("paraFrm").target="main";
	  document.getElementById('paraFrm').target = "_self";
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
	   document.getElementById('paraFrm').action="RankMaster_delete1.action";
	   document.getElementById('paraFrm').target = "_self";
	    document.getElementById('paraFrm').submit();
	    }
	    else{
	      alert("Count ------------------> "+document.getElementById('count').value);
	     var flag=document.getElementById('count').value;
	     document.getElementById('checkAll').checked=false;
	    var flag='<%=d %>';
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

function checkAllBox(){
	 var rowLen ='<%=d %>';
	
	 if (document.getElementById("checkAll").checked){
	   for(var idx=1;idx<=rowLen;idx++){
	    document.getElementById("confChk"+idx).checked ="true";
	    document.getElementById('hdeleteCode'+idx).value=rankArray[idx-1];
	   
      }
	}else{
	  for (var idx = 1; idx <= rowLen; idx++) {
	   document.getElementById("confChk"+idx).checked = false;
	   document.getElementById("checkAll").checked=false;
	   document.getElementById('hdeleteCode'+idx).value="";
      }
    }	 
}
	
</script>

