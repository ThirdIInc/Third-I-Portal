<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript">
var checkListArray = new Array();
</script>
<s:form action="CheckListMaster" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="show" value="%{show}" />
	<s:hidden name="hiddencode" />
	<s:hidden name="editFlag"/>
	<s:hidden name="cancelFlag"/>
	<s:hidden name="checkType"/>
<s:hidden name="checkCode" />
<s:hidden name="desciption"></s:hidden>
<s:hidden name="status"/>
<s:hidden name="checkName" />
	<table class="formbg" width="100%">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Check
					List</strong></td>
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
							<td width="25%"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" />
							
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
<s:if test="checkListView">
				

				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>

							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="0">

								<tr>

									<td width="15%" colspan="1" height="22"><label class="set" name="clist.type"
										id="clist.type" ondblclick="callShowDiv(this);"><%=label.get("clist.type")%></label>
									:</td>
									<td width="85%" colspan="2" height="22">
										<s:property value="checkType"/>
									</td>
								</tr>

								<tr>
									<td width="15%" colspan="1" height="22"><label class="set" name="clist.name"
										id="clist.name" ondblclick="callShowDiv(this);"><%=label.get("clist.name")%></label>
									:</td>
									<td width="85%" colspan="2" height="22">
										<s:property value="checkName"/>
									</td>
								</tr>

								<tr>
									<td width="15%" valign="top" colspan="1" height="22"
										nowrap="nowrap"><label class="set" name="clist.desc" id="clist.desc"
										ondblclick="callShowDiv(this);"><%=label.get("clist.desc")%></label>:</td>
									<td width="85%" colspan="2" height="22">
										<s:property value="desciption"/>
									</td>
								</tr>
								<tr>
									<td width="15%" colspan="1" height="22"><label class="set" name="clist.stat"
										id="clist.stat" ondblclick="callShowDiv(this);"><%=label.get("clist.stat")%></label>:</td>
									<td width="85%" colspan="2" height="22">
										<s:property value="status"/>
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
				</td></tr></s:if>
				
				

				<tr>
					<td colspan="3">
					<%
					try {
					%>
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>

							<td class="formtext">
							<table width="100%" border="0" cellpadding="1" cellspacing="1">
								<tr>
									<td height="15" class="text_head" nowrap="nowrap"><strong
										class="forminnerhead">Check List</strong></td>
								</tr>
								<tr>
									<s:hidden name="myPage" id="myPage" />
									<td class="formth" align="center"><b><label class="set" name="clist.srno"
										id="clist.srno" ondblclick="callShowDiv(this);"><%=label.get("clist.srno")%></label></b>
									</td>
									<td class="formth" align="center"><b><label class="set" name="clist.name"
										id="clist.name1" ondblclick="callShowDiv(this);"><%=label.get("clist.name")%></label></b>
									</td>
									<td class="formth" align="center"><b><label class="set" name="clist.type"
										id="clist.type1" ondblclick="callShowDiv(this);"><%=label.get("clist.type")%></label></b>
									</td>
									<td class="formth" align="center"><b><label class="set" name="clist.stat"
										id="clist.stat1" ondblclick="callShowDiv(this);"><%=label.get("clist.stat")%></label></b>
									</td>

									<td align="right" class="formth" nowrap="nowrap"><s:if
										test="checkListMaster.deleteFlag">
										
									<input type="checkbox" name="checkAll" id="checkAll"
										       onclick=" checkAllBox()"/>
										<input type="button" class="delete" theme="simple"
											value="     Delete  " onclick="return chkDelete();" />
									</s:if></td>
									<%
									int count = 0;
									%>
									<%!int d = 0;%>
									<%
											int i = 0;
											int cn = pageNo * 20 - 20;
									%><%int counter = 0;%>
									<s:iterator value="att">
										<tr <%if(count%2==0){
									%> class="tableCell1"
											<%}else{%> class="tableCell2" <%	}count++; %>
											onmouseover="javascript:newRowColor(this);"
											onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
											nowrap="nowrap">


											<td width="10%" align="left" class="sortableTD"
											title="Double click for edit"
											ondblclick="javascript:callForEdit('<s:property  value="checkCode"/>');"
											><%=++cn%> <%
 ++i;
 %><s:hidden
												name="srNo" /></td>
											<s:hidden value="%{checkCode}"></s:hidden>

											<td width="35%" align="left" nowrap="nowrap" class="sortableTD"
											title="Double click for edit"
											ondblclick="javascript:callForEdit('<s:property  value="checkCode"/>');"
											>
											<s:property
												value="checkName" />
												 <input type="hidden"
												name="hdeleteCode" id="hdeleteCode<%=i%>" /></td>
											<td width="30%" align="left" nowrap="nowrap" class="sortableTD"
											title="Double click for edit"
											ondblclick="javascript:callForEdit('<s:property  value="checkCode"/>');"
											><s:property
												value="checkType" /></td>
												<script>
													checkListArray[<%=counter%>] = '<s:property  value="checkCode"/>';
									</script>	
											<td width="15%" align="left" nowrap="nowrap" class="sortableTD"
											title="Double click for edit"
											ondblclick="javascript:callForEdit('<s:property  value="checkCode"/>');"
											><s:property
												value="status" />
												<input type="hidden" name="hdeleteCode"
												id="hdeleteCode<%=i%>" value="<%=i%>"/>
												
												</td>

											<td align="left" nowrap="nowrap" class="sortableTD"><input
												type="checkbox" class="checkbox" id="confChk<%=i%>"
												name="confChk"
												onclick="callForDelete1('<s:property value="checkCode"/>','<%=i%>')" /></td>
										</tr>

									<%counter++; %>
									</s:iterator>
									<input type="hidden" name="count" id="count" value="<%=count%>"/>
									<%
									d = i;
									%>
								</tr>

								<tr>
									<td colspan="6">&nbsp;</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					<%
						} catch (Exception e) {
						}
					%>
					</td>
				</tr>
						<tr>
					<td width="75%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td colspan="3" width="100%" align="Right">
					<b>Total No. of Records :&nbsp;<s:property value="totalRecords" /></b>
					</td>	
				</tr>
			
	</table>
</s:form>
<script>
var f9Fields= ["paraFrm_checkType", "paraFrm_checkName"];
var fieldName = ["paraFrm_checkType", "paraFrm_checkName"];
var lableName = ["clist.type", "clist.name"];
var type = ['select','enter'];

//For Addnew Button 

function addnewFun()
{
	
	document.getElementById('paraFrm').action="CheckListMaster_addNew.action";
	document.getElementById('paraFrm').submit();
}

// For Save Button

function saveFun()
{
	 if(!validateBlank(fieldName,lableName,type))
          return false;
    
    if(!f9specialchars(f9Fields))
	return false;
	
	var desc = document.getElementById("paraFrm_desciption").value;
	
	if(desc != "" && desc.length >250){
		alert("Maximum length of "+document.getElementById("clist.desc").innerHTML.toLowerCase() +" is 250 characters.");
		return false;
	}
	
	document.getElementById('paraFrm').action="CheckListMaster_save.action";
	document.getElementById('paraFrm').submit();
	return true;
}

//For Edit Button

function editFun()
{

	document.getElementById('paraFrm').action="CheckListMaster_edit.action";
	document.getElementById('paraFrm').submit();
}

//For Delete Button

function deleteFun()
{
	var con=confirm('Do you really want to delete this record?')
	if(con){
			var del="CheckListMaster_delete.action";
		   document.getElementById('paraFrm').action=del;
		   document.getElementById('paraFrm').submit();
		   return true;
	} else{
	     return false;
	}
}
//For F9Window

function searchFun()
{
	callsF9(500,300,"CheckListMaster_f9action.action");
}

//For Cancel Button
function cancelFun(){
    	document.getElementById('paraFrm').action="CheckListMaster_cancelSecond.action";
        document.getElementById("paraFrm").submit();
    
}

//For Report Button

function reportFun()
{
	document.getElementById('paraFrm').action="CheckListMaster_report.action";
    document.getElementById("paraFrm").submit();
}

function resetData()
  	{
  	 document.getElementById('paraFrm_show').value='1';
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
	    document.getElementById('paraFrm').action="CheckListMaster_callPage.action";
	   	document.getElementById('paraFrm').submit();
	}	   
   
function on()
{
var val= document.getElementById('selectname').value;
document.getElementById('paraFrm_show').value=val;
document.getElementById('myPage').value=eval(val);
document.getElementById('selectname').value=val;
document.getElementById('paraFrm').action="CheckListMaster_callPage.action";
document.getElementById('paraFrm').submit();
}
  	pgshow();
  	function pgshow()
  	{
  	var pgno=document.getElementById('paraFrm_show').value;
  
  	if(!(pgno==""))
  	 document.getElementById('selectname').value=pgno;
  	}
  	
  	function callForEdit(id){
	  	document.getElementById('paraFrm_hiddencode').value=id;
	   	document.getElementById("paraFrm").action="CheckListMaster_calforedit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
   }
   /* function callForDelete(id){
	  	document.getElementById('paraFrm_hiddencode').value=id;
	   	document.getElementById("paraFrm").action="CheckListMaster_calfordelete.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
   }*/
function callForDelete1(id,i)
	   {
	   
	   var flag='<%=d %>';
	 // alert('id----1-----'+id);
	  // alert('i----1-----'+i);
	
	   if(document.getElementById('confChk'+i).checked == true)
	   {	  
	    document.getElementById('hdeleteCode'+i).value=id;
	   }
	   else
	   document.getElementById('hdeleteCode'+i).value="";
   }
   
   function chkDelete()
	{
	
	 if(chk()){
	 var con=confirm('Do you really want to delete records?');
	 if(con){
	   document.getElementById('paraFrm').action="CheckListMaster_delete1.action";
	    document.getElementById('paraFrm').submit();
	    }
	    else
	    { 
	    
	    var flag='<%=d %>';
	  for(var a=1;a<=flag;a++){	
	document.getElementById('confChk'+a).checked = false;
	document.getElementById('hdeleteCode'+a).value="";
	
	}
	     return false;
	 }
	 }
	 else {
	 alert('Please select at least one record to delete');
	 return false;
	 }
	}
	
	function chk(){
	 var flag='<%=d %>';
	  for(var a=1;a<=flag;a++){	
	   if(document.getElementById('confChk'+a).checked == true)
	   {	
	  // alert('11');  
	    return true;
	   }	   
	  }
	  return false;
	}
   
   	function newRowColor(cell) {
	
		cell.className='onOverCell';
	
	}
	
	function oldRowColor(cell,val) {
	
	if(val=='1'){
	 cell.className='tableCell2';
	}
	else cell.className='tableCell1';
		//cell.style.backgroundColor="#ffffff";
	}
	
function checkAllBox(){
	 var rowLen ='<%=d %>';
	
	 if (document.getElementById("checkAll").checked){
	   for(var idx=1;idx<=rowLen;idx++){
	    document.getElementById("confChk"+idx).checked ="true";
	    document.getElementById('hdeleteCode'+idx).value=checkListArray[idx-1];
	   
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
