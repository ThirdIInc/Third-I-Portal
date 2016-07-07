<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="LocationType_input" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<script type="text/javascript">
	var records = new Array();
</script>
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />

	<!-- Flagas used For Cancel Button -->
	<s:hidden name="onLoadFlag" />
	<s:hidden name="loadFlag" />
	<s:hidden name="addFlag" />
	<s:hidden name="modFlag" />
	<s:hidden name="dbFlag" />
	<s:hidden name="cancelFlg" />
	<s:hidden name="pageFlag" />
	<s:hidden name="locationCode" />
	<s:hidden name="locationName" />
	<s:hidden name="Status" />


	<s:hidden name="panelFlag" />
	<s:hidden name="retrnFlag" />

	<table width="100%" border="0" align="right" cellpadding="1"
		cellspacing="2" class="formbg" >
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Travel
					Type </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- The Following code Denotes  Include JSP Page For Button Panel -->

		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<s:if test="onLoadFlag">
						<td width="22%"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					</s:if>
					<td width="22%"><!-- <div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>--></td>
				</tr>
			</table>
			</td>
		</tr>

		 <tr>
			<td colspan="4"><s:if test="onLoadFlag">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td colspan="4">
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="2">


							<tr>
								<td width="21%" height="22" class="formtext" colspan="1"><label
									class="set" id="location.name" name="location.name"
									ondblclick="callShowDiv(this);"><%=label.get("location.name")%></label>
								:</td>
								<td width="20%" height="22" colspan="3"><s:property
									value="locationName" /></td>
							</tr>
							
							
							<tr>
								<td valign="top" height="22" width="21%" class="formtext"
									colspan="1"><label class="set" id="desc" name="desc"
									ondblclick="callShowDiv(this);"><%=label.get("desc")%></label>
								:</td>
								<td colspan="3" width="70%" style="overflow: hidden;"><s:textarea
									readonly="true" cssStyle="border:none;overflow: hidden;"
									name="Description" cols="75" rows="5" /> <s:hidden
									name="Description" /></td>

							</tr>
							

							<tr>
								<td height="22" width="21%" class="formtext" colspan="1"><label
									class="set" id="status" name="status"
									ondblclick="callShowDiv(this);"><%=label.get("status")%></label>
								:</td>
								<td height="22" width="20%" colspan="3"><s:property
									value="Status" /></td>
								<!--  <td><input type="text" name="hiddenText"
									style="visibility: hidden;"></td>-->
							</tr>

						</table>
						</td>
					</tr>
				</table>
			</s:if></td>
		</tr>
		
		
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<s:if test="onLoadFlag">
						<td width="22%"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					</s:if>
					<td width="22%"><!-- <div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>--></td>
				</tr>
			</table>
			</td>
		</tr>
		
		
		<s:if test="onLoadFlag"></s:if><s:else>
		  <tr>
			<td colspan="4">

			<table width="100%" border="0" cellpadding="0" cellspacing="0">


				<tr>
					<td><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>


					<td align="right"><b>Page:</b>
					<%
					int totalPage = (Integer) request.getAttribute("abc");
					int pageNo = (Integer) request.getAttribute("xyz");
					%> <%	if(pageNo != 1)
								{
									if(pageNo > totalPage){}
									else{
							%> <a href="#" onclick="callPage('1');"> <img
						src="../pages/common/img/first.gif" width="10" height="10"
						class="iconImage" /> </a>&nbsp; <a href="#" onclick="callPage('P');">
					<img src="../pages/common/img/previous.gif" width="10" height="10"
						class="iconImage" /> </a> <%	}
								}
								if(totalPage <= 5)
								{
									if(totalPage == 1)
									{
							%> <b><u><%=totalPage%></u></b> <%		}
									else
									{
										for(int z = 1; z <= totalPage; z++)
										{
											if(pageNo == z)
											{
							%> <b><u><%=z%></u></b> <%				}
											else
											{
							%> <a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%				}
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
							%> <b><u><%=z%></u></b> <%				}
											else
											{
							%> &nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%				}
										}
									}
									else if(pageNo <= 3)
									{
										for(int z = 1; z <= 5; z++)
										{
											if(pageNo == z)
											{
							%> <b><u><%=z%></u></b> <%				}
											else
											{
							%> &nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%				}
										}						
									}
									else if(pageNo > 3)
									{
										for(int z = pageNo - 2; z <= pageNo + 2; z++)
										{
											if(pageNo == z)
											{
							%> <b><u><%=z%></u></b> <%				}
											else
											{
							%> &nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%				}
										}					
									}
								}
								if(!(pageNo == totalPage))
								{
									if(totalPage == 0 || pageNo > totalPage){}
									else
									{
							%> ....<%=totalPage%> <a href="#" onclick="callPage('N')"> <img
						src="../pages/common/img/next.gif" class="iconImage" /> </a>&nbsp; <a
						href="#" onclick="callPage('<%=totalPage%>');"> <img
						src="../pages/common/img/last.gif" width="10" height="10"
						class="iconImage" /> </a> <%		}
								}
							%><select name="selectname" onchange="on()" id="selectname">
						<%
						for (int i = 1; i <= totalPage; i++) {
						%>

						<option value="<%=i%>"><%=i%></option>
						<%
						}
						%>
					</select></td>
				</tr>



			</table>
			</td>
		</tr>


		<tr>
			<td colspan="4">
			<%
			try {
			%>
			<table width="100%" border="0" cellpadding="0" cellspacing="1"
				class="formbg">

				<tr>

					<td class="formtext">
					<table width="100%" border="0" cellpadding="1" cellspacing="1">
						<tr>
							<td height="15" class="formhead" nowrap="nowrap" colspan="3"><strong
								class="forminnerhead"><label class="set"
								id="location.list" name="location.list"
								ondblclick="callShowDiv(this);"><%=label.get("location.list")%></label></strong></td>
								<td align="right" class="formhead" nowrap="nowrap" width="15%"><input
								type="button" class="delete" theme="simple" value="Delete "
								onclick=" return chkDelete(this);" /></td>
						</tr>
						<tr>
							<s:hidden name="myPage" id="myPage" />
							<td class="formth" align="center"><strong ><label class="set"
								id="sr.no" name="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></strong></td>
							<td class="formth" align="center"><strong ><label class="set"
								id="location.names" name="location.name"
								ondblclick="callShowDiv(this);"><%=label.get("location.name")%></label></strong></td>

							<td class="formth" align="center"><strong ><label class="set"
								id="status3" name="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label></strong></td>
							<td  align="center" class="formth" width="15%"  class="set" nowrap="nowrap" >
							<input type="checkbox" id="checkAll" style="cursor: hand;" title="Select all records to remove" 
								onclick="selectAllRecords(this);" />
						     </td>


							<%
							int count = 0;
							%>
							<%!int d = 0;%>
							<%
							int cnt= pageNo*20-20;	
							int i = 0;
							%>
							<s:iterator value="locationList">

								<tr id="tr1" <%if(count%2==0){
									%> class="tableCell1"
									<%}else{%> class="tableCell2" <%	}count++; %>
									onmouseover="javascript:newRowColor(this);"
									title="Double click for edit"
									onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
									ondblclick="javascript:callForEdit('<s:property  value="locationCode"/>');">
									<td width="10%" align="left" class="sortableTD"><%=++cnt%>
									<%
									++i;
									%>
									<s:hidden value="%{locationCode}" id='<%="locationCode" + i%>'></s:hidden>
									</td>
									
									<script type="text/javascript">
										records[<%=i%>] = document.getElementById('locationCode' + '<%=i%>').value;
									</script>


									<td width="40%" align="left" class="sortableTD"><s:hidden
										value="%{locationCode}"></s:hidden> <input type="hidden"
										name="hdeleteCode" id="hdeleteCode<%=i%>" /> <s:property
										value="locationName" /></td>

									<td width="40%" align="left" class="sortableTD"><s:property
										value="Status" /></td>
									<input type="hidden" name="hdeleteCode" id="hdeleteCode<%=i%>" />
									</td>
									<td align="center" nowrap="nowrap" class="sortableTD"><input
										type="checkbox" class="checkbox" id="confChk<%=i%>"
										name="confChk"
										onclick="callForDelete1('<s:property  value="locationCode"/>','<%=i%>')" /></td>
								</tr>

							</s:iterator>
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
		</tr></s:else>
		<s:if test="onLoadFlag"></s:if><s:else>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="22%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					
				</tr>
			</table>
			</td>
		</tr></s:else>

	</table>

</s:form>



<script>

callForFirstEdit();
function callForFirstEdit(){
var flag=document.getElementById('paraFrm_loadFlag').value; 
//alert(flag);
if(flag=='true'){
document.getElementById('paraFrm_locationName').focus();
}

}

var f9Fields= ["paraFrm_locationName"];
var fieldName = ["paraFrm_locationName"];
var lableName = ["location.name"];
var type = ['enter'];

//For Addnew Button 

function addnewFun()
{
	
	document.getElementById('paraFrm').action="LocationType_addNew.action";
	document.getElementById('paraFrm').submit();
}

// For Save Button

function saveFun()
{
	if(!validateBlank(fieldName, lableName, type))
    return false;
    if(!f9specialchars(f9Fields))
	return false;
	document.getElementById('paraFrm').action="LocationType_save.action";
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm_gradeName').focus();
	return true;
}

//For Edit Button

function updateFun()
{
	document.getElementById('paraFrm').action="LocationType_update.action";
	document.getElementById('paraFrm').submit();
}

function reportFun()
{  
     document.getElementById('paraFrm').target="_balnk";
	document.getElementById('paraFrm').action="LocationType_report.action";
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target="main";
}
function editFun()
{
	document.getElementById('paraFrm').action="LocationType_edit.action";
	document.getElementById('paraFrm').submit();
}

//For Delete Button

function deleteFun()
{
	var con=confirm('Do you really want to delete this record ? ')
	if(con){
			var del="LocationType_delete.action";
		   document.getElementById('paraFrm').action=del;
		   document.getElementById('paraFrm').submit();
	} else{
	     return false;
	}
}

//For F9Window

function searchFun()
{
	callsF9(500,300,"LocationType_f9Action.action");
}

//For Cancel Button

/*function cancelFun()
{
	//alert("1pppk"+document.getElementById('paraFrm_loadFlag').value);
	//alert("2pppppp"+document.getElementById('paraFrm_addFlag').value);
	//alert("3pppppp"+document.getElementById('paraFrm_modFlag').value);
	
	if(document.getElementById('paraFrm_loadFlag').value=="true")
	{
		//alert("in firast");
		document.getElementById('paraFrm').action="LocationType_cancelFirst.action";
		document.getElementById('paraFrm').submit();
	}
	
	else if(document.getElementById('paraFrm_addFlag').value=="true")
		{	
		//alert("in sec");
		document.getElementById('paraFrm').action="LocationType_cancelSec.action";	
		document.getElementById('paraFrm').submit();
	}
	else if(document.getElementById('paraFrm_modFlag').value=="true")
	{	
		//alert("inside mod");
		document.getElementById('paraFrm').action="LocationType_cancelThrd.action";
		document.getElementById('paraFrm').submit();
	}
	else
	{	
		document.getElementById('paraFrm').action="LocationType_cancelFrth.action";
		document.getElementById('paraFrm').submit();
	}
}*/

function cancelFun(){
     	document.getElementById("paraFrm").action="LocationType_cancelSec.action";
	    document.getElementById("paraFrm").submit();
    
    
    }

function saveValidation(){	
  var val=document.getElementById('paraFrm_loactionName').value;
	  if(val==""){
		  alert("Please enter "+document.getElementById('location.name').innerHTML.toLowerCase());
		  return false;
	  }
  
	  var value = LTrim(RTrim(val));
	  if(val!=value){
		alert('Space not allowed in location  type name!');
		 return false;
	  }
	

	
	  return true;
}  
	
	
	
	
function callDelete(){
	if(document.getElementById('paraFrm_locationName').value==""){
			alert("Please select the record.");
			return false;
			
	}
	 
	var con=confirm('Do you really want to delete this record ? ');
	if(con){
		   document.getElementById('paraFrm').action="LocationType_delete.action";
		   document.getElementById('paraFrm').submit();
	} else{
	     return false;
	   }
	
}
	
function updateValidation(){   
var val=document.getElementById('paraFrm_locationName').value;
	if(val==""){
		  alert("Please enter "+document.getElementById('location.name').innerHTML.toLowerCase());
		  return false;
	}
  
   	var value = LTrim(RTrim(val));
	if(val!=value){
		alert('Space not allowed in location  type name!');
		 return false;
	}
	
   	return true;
}

/*function callPage(id){
	   	document.getElementById('myPage').value=id;
		document.getElementById('paraFrm_show').value=id;
	    document.getElementById('paraFrm').action="LocationType_callPage2.action";
	   	document.getElementById('paraFrm').submit();
}*/
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
		document.getElementById('paraFrm').action='LocationType_callPageView.action';
		document.getElementById('paraFrm').submit();
		
	}	

function next(){
   var pageno=	document.getElementById('myPage').value;
   	if(pageno=="1"){
   		document.getElementById('myPage').value=2;
	    document.getElementById('paraFrm_show').value=2;
    } else{
				 document.getElementById('myPage').value=eval(pageno)+1;
				 document.getElementById('paraFrm_show').value=eval(pageno)+1;
	    }
	   document.getElementById('paraFrm').action="LocationType_callPage1.action";
	   document.getElementById('paraFrm').submit();
}	
   
function on(){
		var val= document.getElementById('selectname').value;
		document.getElementById('paraFrm_show').value=val;
		document.getElementById('myPage').value=eval(val);
		document.getElementById('selectname').value=val;
		document.getElementById('paraFrm').action="LocationType_callPage1View.action";
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
	
	   	document.getElementById("paraFrm").action="LocationType_calforedit.action";
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
	 var con=confirm('Do you really want to delete this record ? ');
	 if(con){
	   document.getElementById('paraFrm').action="LocationType_delete1.action";
	    document.getElementById('paraFrm').submit();
	    }
	    else
	    {
		     var flag='<%=d %>';
		  
		      document.getElementById('checkAll').checked=false;
		     for(var a=1;a<=flag;a++)
		     	document.getElementById('confChk'+a).checked = false;
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
	
function previous(){
   var pageno=	document.getElementById('myPage').value;
   	
	 document.getElementById('myPage').value=eval(pageno)-1;
	 document.getElementById('paraFrm_show').value=eval(pageno)-1;
	 document.getElementById('paraFrm').action="LocationType_callPage1.action";
	 document.getElementById('paraFrm').submit();
}   

  	function selectAllRecords(object) {
		
		try{
		if(object.checked) {
		
			for(var i = 1; i <= records.length; i++) {
			
				document.getElementById('confChk' + i).checked = true;
				document.getElementById('hdeleteCode' + i).value = records[i];
			}
		} else {
			for(var i = 1; i <= records.length; i++) {
				document.getElementById('confChk' + i).checked = false;
				document.getElementById('hdeleteCode' + i).value = "";
			}
		}
		}catch(e)
		{
		//alert(e);
		}
	}	
</script>

