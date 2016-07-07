<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript">
	var records = new Array();
</script>
<s:form action="TravelMode_input" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />

	<!-- Flagas used For Cancel Button -->

	<s:hidden name="loadFlag" />
	<s:hidden name="addFlag" />
	<s:hidden name="modFlag" />
	<s:hidden name="dbFlag" />
	<s:hidden name="cancelFlg" />
	<s:hidden name="pageFlag" />
	<!-- for priority  -->
	<s:hidden name="hidLevel" />
	<s:hidden name="previousLevel" />
	<s:hidden name="currentLevel" />
	<s:hidden name="firstRecordOfPage" />
	<s:hidden name="lastRecordOfPage" />

	<!-- flags for paging and navigation panel -->
	<s:hidden name="panelFlag" />
	<s:hidden name="retrnFlag" />

	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Travel
					Mode </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:hidden name="trvId" />
		<s:hidden name="typeId" />
		<s:hidden name="status" />
		<s:hidden name="trvMode" />
		<s:hidden name="trvClass" />
		<!-- The Following code Denotes  Include JSP Page For Button Panel -->
		<s:if test="onLoadFlag">
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="22%"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						<td width="22%"><!-- <div align="right"><span class="style2"><font
							color="red">*</font></span> Indicates Required</div>--></td>
					</tr>
				</table>
				</td>
			</tr>


			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td>
						<table width="98%" border="0" align="center" cellpadding="0"
							cellspacing="2">

							<tr>
								<td width="10%" height="22" class="formtext"><label
									class="set" name="travel.mode" id="travel.mode"
									ondblclick="callShowDiv(this);"><%=label.get("travel.mode")%></label>
								:</td>
								<td width="34%" height="22"><s:property value="trvMode" /></td>
							</tr>
							<tr>
								<td width="10%" height="22" class="formtext"><label
									class="set" name="travel.sts" id="status"
									ondblclick="callShowDiv(this);"><%=label.get("travel.sts")%></label>
								:</td>
								<td width="34%" height="22"><s:property value="status" /></td>
							</tr>

						</table>
						</td>
				</table>
				</td>
			</tr>
			<s:hidden name="hiddenEdit" />
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="22%"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						<td width="22%"><!-- <div align="right"><span class="style2"><font
							color="red">*</font></span> Indicates Required</div>--></td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
<s:if test="onLoadFlag"></s:if><s:else>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td align="right"><b>Page:</b> <%
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
							<td height="15" class="formhead" colspan="4"><strong
								class="forminnerhead"><label class="set"
								name="travel.list" id="travel.list"
								ondblclick="callShowDiv(this);"><%=label.get("travel.list")%></label></strong></td>
								<td align="center" class="formhead" nowrap="nowrap" width="15%"><s:submit
								cssClass="delete" theme="simple" value="   Delete"
								onclick=" return chkDelete();" /></td>
						</tr>
						<tr>
							<s:hidden name="myPage" id="myPage" />
							<td class="formth" align="center" nowrap="nowrap"><label
								class="set" name="travel.no" id="travel.no1"
								ondblclick="callShowDiv(this);"><%=label.get("travel.no")%></label></td>
							<td class="formth" align="center" width="20%"><label
								class="set" name="travel.mode" id="travel.mode1"
								ondblclick="callShowDiv(this);"><%=label.get("travel.mode")%></label></td>
							<td class="formth" align="center" width="20%"><label
								class="set" name="travel.sts" id="travel.sts1"
								ondblclick="callShowDiv(this);"><%=label.get("travel.sts")%></label></td>




							<td align="center" class="formth" width="20%"><label
								class="set" name="priority" id="priority"
								ondblclick="callShowDiv(this);"><%=label.get("priority")%></label></td>
							<td  align="center" class="formth" width="15%"  class="set" nowrap="nowrap" >
							<input type="checkbox" id="checkAll" style="cursor: hand;" title="Select all records to remove" 
								onclick="selectAllRecords(this);" />
						</td>

							<s:hidden name="statusUp" />
							<s:hidden name="statusDown" />
							<s:hidden name="upId" />


							<%
							int count = 0;
							%>
							<%!int d = 0;%>
							<%
							int cnt= pageNo*20-20;	
							int i = 0;
							%>
							<s:iterator value="trvList">
								<tr id="tr1" <%if(count%2==0){
									%> class="tableCell1"
									<%}else{%> class="tableCell2" <%	}count++; %>
									onmouseover="javascript:newRowColor(this);"
									title="Double click for edit"
									onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
									ondblclick="javascript:callForEdit('<s:property  value="trvId"/>');">
									<td align="center" nowrap="nowrap" width="10%"
										class="sortableTD"><%=++cnt%> <%
									++i;
									%>
									<s:hidden value="%{trvId}" id='<%="trvId" + i%>'></s:hidden>
									</td>
									
									<script type="text/javascript">
										records[<%=i%>] = document.getElementById('trvId' + '<%=i%>').value;
									</script>
									<td width="20%" align="left" class="sortableTD"><s:hidden
										value="%{trvId}"></s:hidden> <input type="hidden"
										name="hdeleteCode" id="hdeleteCode<%=i%>" /> <s:property
										value="trvMode" /></td>
									<td width="20%" align="center" class="sortableTD"><s:property
										value="status" />&nbsp;</td>
									<input type="hidden" name="hdeleteCode" id="hdeleteCode<%=i%>" />


									<td width="20%" align="center" class="sortableTD"><img
										src="../pages/common/css/default/images/up.GIF" width="16"
										height="15" onclick="callUP(<%=(cnt)%>);"> <img
										src="../pages/common/css/default/images/down.GIF" width="16"
										height="15" onclick="callDown(<%=(cnt)%>);"></td>
									<td align="center" nowrap="nowrap" width="10%"
										class="sortableTD"><input type="checkbox"
										class="checkbox" id="confChk<%=i%>" name="confChk"
										onclick="callForDelete1('<s:property  value="trvId"/>','<%=i%>')" />
									<s:hidden name="itLevel" id="<%="itLevel"+cnt%>" /></td>

								</tr>

							</s:iterator>
							<%
							d = i;
							%>
						</tr>
						<s:hidden name="subcode" />
						<s:hidden name="tableLength" />
						<s:hidden name="modeLength" />
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
	var f9Fields= ["paraFrm_trvMode","paraFrm_trvClass","paraFrm_tableLength"];
	var fieldName = ["paraFrm_trvMode","paraFrm_trvClass","paraFrm_tableLength"];
	var lableName = ["travel.mode","travel.class","class.list"];
	var type = ['enter','enter','add'];
	var f9Fields1= ["paraFrm_trvMode","paraFrm_trvClass"];
	var fieldName1 = ["paraFrm_trvMode","paraFrm_trvClass"];
	var lableName1 = ["travel.mode","travel.class"];

	//For Addnew Button 
	function addnewFun()
	{
	
		document.getElementById('paraFrm').action="TravelMode_addNew.action";
		document.getElementById('paraFrm').submit();
	}
	
	// For Save Button

function saveFun()
{	
	try{
	var trMode=document.getElementById('paraFrm_trvMode').value;
	var trLen=document.getElementById('paraFrm_tableLength').value;
		var trvModId=document.getElementById('paraFrm_trvId').value;
	var chktrMode = LTrim(trMode);
	if(trvModId=="" )
		{
			
			if(chktrMode=="")
			{
				alert("Please enter "+document.getElementById('travel.mode').innerHTML.toLowerCase() );
				return false;
			}
		}
	else{
	if(!validateBlank(fieldName1, lableName1, type))
    return false;
    if(!f9specialchars(f9Fields1))
	return false;
	} 
	
	document.getElementById('paraFrm').action="TravelMode_save.action";
	document.getElementById('paraFrm').submit();
	return true;
	}catch(e){
	alert(e);
	}
}

//For Report Button
function reportFun()
{
	
	//document.getElementById('paraFrm').action="TravelMode_report.action";
	//document.getElementById('paraFrm').submit();
}

//For Edit Button

function editFun()
{
	document.getElementById('paraFrm').action="TravelMode_edit.action";
	document.getElementById('paraFrm').submit();
}

//For Delete Button

function deleteFun()
{
	var con=confirm('Do you really want to delete this record ? ')
	if(con){
			var del="TravelMode_delete.action";
		   document.getElementById('paraFrm').action=del;
		   document.getElementById('paraFrm').submit();
	} else{
	     return false;
	}
}

//For F9Window

function searchFun()
{
	callsF9(500,300,"TravelMode_f9Action.action");
}

//For Cancel Button

/*function cancelFun()
{
		
	if(document.getElementById('paraFrm_loadFlag').value=="true")
	{
		//alert("in firast");
		document.getElementById('paraFrm').action="TravelMode_cancelFirst.action";
		document.getElementById('paraFrm').submit();
	}
	
	else if(document.getElementById('paraFrm_addFlag').value=="true")
		{	
		//alert("in sec");
		document.getElementById('paraFrm').action="TravelMode_cancelSec.action";	
		document.getElementById('paraFrm').submit();
	}
	else if(document.getElementById('paraFrm_modFlag').value=="true")
	{	
		//alert("inside mod");
		document.getElementById('paraFrm').action="TravelMode_cancelThrd.action";
		document.getElementById('paraFrm').submit();
	}
	else
	{	
		document.getElementById('paraFrm').action="TravelMode_cancelFrth.action";
		document.getElementById('paraFrm').submit();
	}
}*/


function callDelete(){
	if(document.getElementById('paraFrm_typeName').value==""){
			alert("Please select the record.");
			return false;
			
	}
	 
	var con=confirm('Do you really want to delete this record ? ');
	if(con){
		   document.getElementById('paraFrm').action="TravelMode_delete.action";
		   document.getElementById('paraFrm').submit();
	} else{
	     return false;
	   }
	
}
//For Cancel Button

function cancelFun(){
     	document.getElementById("paraFrm").action="TravelMode_cancelSec.action";
	    document.getElementById("paraFrm").submit();
    
    
    }
	
	
	/*function callPage(id){
	   	document.getElementById('myPage').value=id;
		document.getElementById('paraFrm_show').value=id;
	    document.getElementById('paraFrm').action="TravelMode_callPage2.action";
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
		document.getElementById('paraFrm').action='TravelMode_callPageView.action';
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
	   document.getElementById('paraFrm').action="TravelMode_callPage1.action";
	   document.getElementById('paraFrm').submit();
}	
   
function on(){
		var val= document.getElementById('selectname').value;
		document.getElementById('paraFrm_show').value=val;
		document.getElementById('myPage').value=eval(val);
		document.getElementById('selectname').value=val;
		document.getElementById('paraFrm').action="TravelMode_callPage1.action";
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
	   	document.getElementById("paraFrm").action="TravelMode_calforedit.action";
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
	   document.getElementById('paraFrm').action="TravelMode_delete1.action";
	    document.getElementById('paraFrm').submit();
	    }
	    else{
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
	   document.getElementById('paraFrm').action="TravelMode_callPage1.action";
	   document.getElementById('paraFrm').submit();
} 


//To remove record from list  
 function chkDeleteCls()
		{				
	 if(chkCls()){
		 var con=confirm('Do you really want to  delete the record ?');
	 	if(con){
	   	document.getElementById('paraFrm').action="TravelMode_deleteCls.action";
	    document.getElementById('paraFrm').submit();
	    }
	    else{
	  	for(var a=1;a<=flag;a++){	
	   	document.getElementById('confChkOp'+a).checked=false;
	   	document.getElementById('confChkOp'+a).checked="";
	    document.getElementById('hdeleteOp'+a).value="";
	    }
	     return false;
		 }
	 	}
	 	else {	 
	 	alert('Please select atleast one record to delete.');
	 	 return false;
	 	}
	}
	
	function chkCls(){
	
	  	for(var a=1;a<=flag;a++){	    	
	  	 if(document.getElementById('confChkOp'+a).checked == true)
	   	{  
	 	    return true;
	  	 }	   
	  }	   
	  	return false;
	}
	
	
	function callForDeleteOp(id)
	   {
	 	   var i=eval(id)-1;
	   if(document.getElementById('confChkOp'+id).checked==true)
	   {	
	    document.getElementById('hdeleteOp'+id).value=eval(id);
	   }
	   else
	   document.getElementById('hdeleteOp'+id).value="";	   
   	}
	
	function callForEditCls(editCls,editSts,sno){   
   		document.getElementById('paraFrm_hiddenEdit').value=sno;
	 	document.getElementById('paraFrm_trvClass').value=editCls;  
	 
	 	if(editSts=="Deactive"){
	 
	 	document.getElementById('paraFrm_status').value="D";  	
	 	}else{
	 	document.getElementById('paraFrm_status').value="A";  	
	 	}
	 	
		return false;  		
  		}
  		
  	function callAdd(){
  		var trvCls=document.getElementById('paraFrm_trvClass').value;
  		var chkCls=LTrim(trvCls);
  		if(chkCls==""){
  		alert("Please enter travel class");
  		return false;
  		}
  	}  	
  	
  	
  	
  	
  	function callUP(id)
 	{  
 	 
 	  var pageNo=document.getElementById('paraFrm_show').value;
 	
 	  if(id!=1)
 	  { 
 	     if(id%20==1)
       {
         var pgNumber =document.getElementById('selectname').value;
         var lastRecord = document.getElementById('paraFrm_lastRecordOfPage').value;
         var lastRecordArr = lastRecord.split(","); 
         document.getElementById('paraFrm_previousLevel').value = lastRecordArr[pgNumber-2];
         var currentLevel = document.getElementById('itLevel'+id).value;
		 document.getElementById('paraFrm_currentLevel').value =currentLevel ; 
       }
       else
       {
	 	   var preId= eval(id)-1;
	 	   var previous = document.getElementById('itLevel'+preId).value;
	 	   document.getElementById('paraFrm_previousLevel').value =previous ; 
	 	   var current = document.getElementById('itLevel'+id).value;
	 	   document.getElementById('paraFrm_currentLevel').value =current ; 
 	     }
 	   }
 	
	 if(pageNo==""){
	 pageNo=1;
 	}
 	id=(pageNo-1)*20+id;
 	
 	if(id=="1")
 	{
  	alert("This is first record,so you cannot up the record.");
  	return false;
 	}
 	 
  	 document.getElementById('paraFrm_statusUp').value="true";
  	 document.getElementById('paraFrm_statusDown').value="false";
  	 document.getElementById('paraFrm_upId').value=id; 
  	 document.getElementById('paraFrm').action="TravelMode_upDataView.action";
  	 document.getElementById('paraFrm').submit(); 
 	}
 
  function callDown(id)
 {  var len=document.getElementById('paraFrm_modeLength').value;   	
 	var pageNo=document.getElementById('paraFrm_show').value;
 	if(pageNo==""){
 	pageNo=1;
 	} 
 	if(id==eval(len))
  	{    
  	alert("This is last record,so you cannot down the record.");
 	 return false;
 	 }
       if(id%20==0)
       {
        var pgNumber =document.getElementById('selectname').value;
        var firstRecord = document.getElementById('paraFrm_firstRecordOfPage').value;
        var firstRecordArr = firstRecord.split(",");
        document.getElementById('paraFrm_previousLevel').value = firstRecordArr[pgNumber-1];
         var currentLevel = document.getElementById('itLevel'+id).value;
		 document.getElementById('paraFrm_currentLevel').value =currentLevel ; 
       }
       else{
		       var preId= eval(id)+1;
		 	   var previous = document.getElementById('itLevel'+preId).value;
		 	   document.getElementById('paraFrm_previousLevel').value =previous ; 
		 	   var current = document.getElementById('itLevel'+id).value;
		 	   document.getElementById('paraFrm_currentLevel').value =current ; 
 	     }
   	document.getElementById('paraFrm_statusDown').value="true";
   	document.getElementById('paraFrm_statusUp').value="false";
   	document.getElementById('paraFrm_upId').value=id; 
   	document.getElementById('paraFrm').action="TravelMode_upDataView.action";
   	document.getElementById('paraFrm').submit(); 
 }
 	function selectAllRecords(object) {
		
		try{if(object.checked) {
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
