<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="RoomType_input" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<script type="text/javascript">
	var records = new Array();
</script>
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />
	<s:hidden name="upDownFlag" />
	
	<!-- Flagas used For Cancel Button -->
	<!--  
	<s:hidden name="loadFlag"/>
	<s:hidden name="addFlag"/>
	<s:hidden name="modFlag"/>  
	<s:hidden name="dbFlag"/>
	<s:hidden name="editFlag"/>
	-->
	<!--  for priority -->
	
	<s:hidden name="hidLevel" />
	<s:hidden name="previousLevel" />
	<s:hidden name="currentLevel" />
	<s:hidden name="firstRecordOfPage" />
	<s:hidden name="lastRecordOfPage" />
	<s:hidden name="callpageflag"/>
	<s:hidden name="hotelName"/>
	<s:hidden name="hotelId"/>
	
	
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		
		<!-- <tr>
			<td width="4%" valign="bottom" class="txt"><strong
				class="formhead"><img
				src="../pages/common/css/default/images/review_shared.gif"
				width="25" height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="formhead">Room Type</strong></td>
			<td width="3%" valign="top" class="txt">
			<div align="right"><img
				src="../pages/common/css/default/images/help.gif" width="16"
				height="16" /></div>
			</td> 
		</tr>-->
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Room
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
					<td colspan="3">

					<table width="100%" border="0" cellpadding="0" cellspacing="0">


						<tr>
							<td><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp"/></td>


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

		

		
							
						<s:hidden name="typeId" />
							
								
								<s:hidden name="typeName" />
							

						

							

						

						
							
							
							
								<s:hidden name="desciption" 
									 ></s:hidden >
							
						
						
								<s:hidden name="status" 
									/>
							
								
								
					

				

		


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
							<td height="15" class="formhead" nowrap="nowrap" colspan="5"><strong
								class="forminnerhead"><label  class = "set" name="room.list" id="room.list" ondblclick="callShowDiv(this);"><%=label.get("room.list")%></label></strong></td>
									<td align="right" class="formhead" nowrap="nowrap"><strong><s:submit
								cssClass="delete" theme="simple" value="    Delete"
								onclick=" return chkDelete();" /></strong></td>
						</tr>
						<tr>
							<s:hidden name="myPage" id="myPage" />
							<td class="formth" align="center"><strong><label  class = "set" name="room.no" id="room.no" ondblclick="callShowDiv(this);"><%=label.get("room.no")%></label></strong></td>
							<td class="formth" align="center"><strong><label  class = "set" name="hotel.name" id="hotel.name1" ondblclick="callShowDiv(this);"><%=label.get("hotel.name")%></label></strong></td>
							<td class="formth" align="center"><strong><label  class = "set" name="room.type" id="room.type1" ondblclick="callShowDiv(this);"><%=label.get("room.type")%></label></strong></td>							
							
							<td class="formth" align="center"><strong><label  class = "set" name="room.sts" id="room.sts1" ondblclick="callShowDiv(this);"><%=label.get("room.sts")%></label></strong></td>
							<td align="center" class="formth" width="10%"><strong><label class="set"
								name="priority" id="priority"
								ondblclick="callShowDiv(this);"><%=label.get("priority")%></label></strong></td>
							<td align="right" class="formth" nowrap="nowrap"><input type="checkbox" id="checkAll" style="cursor: hand;" title="Select all records to remove" 
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
							<s:iterator value="typeList">

								<tr id="tr1" <%if(count%2==0){
									%> class="tableCell1"
									<%}else{%> class="tableCell2" <%	}count++; %>
									onmouseover="javascript:newRowColor(this);"
									title="Double click for edit"
									onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
									ondblclick="javascript:callForEdit('<s:property  value="typeId"/>');">


									<td width="10%" align="left" class="sortableTD"><%=++cnt%>
									<%
									++i;
									%>
									<s:hidden value="%{typeId}" id='<%="typeId" + i%>'></s:hidden>
									</td>
									
									<script type="text/javascript">
										records[<%=i%>] = document.getElementById('typeId' + '<%=i%>').value;
									</script>

									
									<td width="40%" align="left" class="sortableTD"><s:hidden name="itHotelId"/>
									<s:hidden name="itHotelName"/><s:property value="itHotelName"/>&nbsp;</td>


									<td width="40%" align="left" class="sortableTD"><s:hidden
										value="%{typeId}"></s:hidden> <input type="hidden"
										name="hdeleteCode" id="hdeleteCode<%=i%>" /> <s:property
										value="typeName" /></td>
										
																		
																		
									<td width="40%" align="left" class="sortableTD"><s:property
										value="status" /></td>
										<td width="20%" align="center" class="sortableTD"><img
										src="../pages/common/css/default/images/up.GIF" width="16"
										height="15" onclick="callUP(<%=(cnt)%>,'UP');"> <img
										src="../pages/common/css/default/images/down.GIF" width="16"
										height="15" onclick="callDown(<%=(cnt)%>,'DOWN');"></td>
									<input type="hidden" name="hdeleteCode" id="hdeleteCode<%=i%>" />
									
									<td align="center"  class="sortableTD"><input type="checkbox"
										class="checkbox" id="confChk<%=i%>" name="confChk"
										onclick="callForDelete1('<s:property  value="typeId"/>','<%=i%>')" /><s:hidden
										name="itLevel" id="<%="itLevel"+cnt%>" /></td>
									
								</tr>

							</s:iterator>
							<%
							d = i;
							%>
						</tr>
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
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="22%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					
				</tr>
			</table>
			</td>
		</tr>

	</table>
	

</s:form>

<script>

function textCounter(field,  maxlimit) {
		
	          // if too long...trim it!
		if (field.value.length > maxlimit){
			
			//alert('Field value should not exceed '+maxlimit+' chars.');	 
			field.value = field.value.substring(0, maxlimit);
			field.focus;
			return false;
			
		}
		
		return true;
		
	}

function callForFirstEdit(){
var flag=document.getElementById('paraFrm_loadFlag').value; 
//alert(flag);
if(flag=='true'){
document.getElementById('paraFrm_typeName').focus();

}

}
var f9Fields= ["paraFrm_typeName"];
var fieldName = ["paraFrm_typeName"];
var lableName = ["room.type"];
var type = ['enter'];

//For Addnew Button 

function addnewFun()
{
	
	document.getElementById('paraFrm').action="RoomType_addNew.action";
	document.getElementById('paraFrm').submit();
}

// For Save Button

function saveFun()
{
	if(!validateBlank(fieldName, lableName, type))
    return false;
    if(!f9specialchars(f9Fields))
	return false;
	document.getElementById('paraFrm').action="RoomType_save.action";
	document.getElementById('paraFrm').submit();
	return true;
}
// For Update Button

/*function updateFun()
{
	if(!checkMandatory(fieldName, lableName, type))
    return false;
    if(!f9specialchars(f9Fields))
	return false;
	document.getElementById('paraFrm').action="RoomType_update.action";
	document.getElementById('paraFrm').submit();
	return true;
}*/
//For Report Button
function reportFun()
{
	document.getElementById('paraFrm').action="RoomType_report.action";
	document.getElementById('paraFrm').submit();
}

//For Edit Button

function editFun()
{
	document.getElementById('paraFrm').action="RoomType_edit.action";
	document.getElementById('paraFrm').submit();
}

//For Delete Button

function deleteFun()
{
	var con=confirm('Do you really want to delete this record ? ')
	if(con){
			var del="RoomType_delete.action";
		   document.getElementById('paraFrm').action=del;
		   document.getElementById('paraFrm').submit();
	} else{
	     return false;
	}
}

//For F9Window

function searchFun()
{
	callsF9(500,300,"RoomType_f9Action.action");
}

//For Cancel Button

function cancelFun(){
     	document.getElementById("paraFrm").action="RoomType_cancelFirst.action";
	    document.getElementById("paraFrm").submit();   
    
    }


function saveValidation(){	
  var val=document.getElementById('paraFrm_typeName').value;
	  if(val==""){
		  alert('Please enter Room Type Name');
		  return false;
	  }
  
	  var value = LTrim(RTrim(val));
	  if(val!=value){
		alert('Space not Allowed in Room Type Name!');
		 return false;
	  }
	

	/* var abbr=document.getElementById('paraFrm_industryAbbr').value;
		  
	if(abbr==""){
		alert('Please enter Industry Abbr!');
		 return false;
	}
	var value = LTrim(RTrim(abbr));
	if(abbr!=value)	{
			alert('Space not Allowed in Industry Abbr.');
			return false;
	}*/
	  return true;
}  
	
	
	
	
function callDelete(){
	if(document.getElementById('paraFrm_typeName').value==""){
			alert("Please select the record.");
			return false;
			
	}
	 
	var con=confirm('Do you really want to delete this record ? ');
	if(con){
		   document.getElementById('paraFrm').action="RoomType_delete.action";
		   document.getElementById('paraFrm').submit();
	} else{
	     return false;
	   }
	
}
	
function updateValidation(){   
var val=document.getElementById('paraFrm_typeName').value;
	if(val==""){
		  alert('Please enter Room Type Name!');
		  return false;
	}
  
   	var value = LTrim(RTrim(val));
	if(val!=value){
		alert('Space not Allowed in Room Type Name!');
		 return false;
	}
	/*var abbr=document.getElementById('paraFrm_industryAbbr').value;
  
    if(abbr==""){
		  alert('Please Enter Industry Abbr.');
		  return false;
  	}
  	var value = LTrim(RTrim(abbr));
	if(abbr!=value){
		alert('Space not Allowed in Industry Abbr.');
		 return false;
	}*/
   	return true;
}

/*function callPage(id){
	   	document.getElementById('myPage').value=id;
		document.getElementById('paraFrm_show').value=id;
	    document.getElementById('paraFrm').action="RoomType_callPage2.action";
	   	document.getElementById('paraFrm').submit();
}*/
function callPage(id){
//alert("callPage");
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
		document.getElementById('paraFrm').action='RoomType_callPage2.action';
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
	   document.getElementById('paraFrm').action="RoomType_callPage1.action";
	   document.getElementById('paraFrm').submit();
}	
   
function on(){
		var val= document.getElementById('selectname').value;
		document.getElementById('paraFrm_show').value=val;
		document.getElementById('myPage').value=eval(val);
		document.getElementById('selectname').value=val;
		document.getElementById('paraFrm').action="RoomType_callPage1.action";
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
	
	   	document.getElementById("paraFrm").action="RoomType_calforedit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
}
   
   
pgshow();

function pgshow()
  	{
  	var pgno=document.getElementById('paraFrm_show').value;
  
  	if(!(pgno==""))
  	{
  	 document.getElementById('selectname').value=pgno;
  	}
  	callForFirstEdit();
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
	   document.getElementById('paraFrm').action="RoomType_delete1.action";
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
	   document.getElementById('paraFrm').action="RoomType_callPage1.action";
	   document.getElementById('paraFrm').submit();
}   
/*function callUP(id)
 	{  
 	var pageNo=document.getElementById('paraFrm_show').value;
 	//alert('----'+pageNo); 
	 if(pageNo==""){
	 pageNo=1;
 	}
 	id=(pageNo-1)*20+id;
 	//alert(id);
 	if(id=="1")
 	{
  	alert("This is first record, so you cannot up the record.");
  	return false;
 	}
 	 
  	 document.getElementById('paraFrm_statusUp').value="true";
  	 document.getElementById('paraFrm_statusDown').value="false";
  	 document.getElementById('paraFrm_upId').value=id; 
  	 document.getElementById('paraFrm').action="RoomType_upData.action";
  	 document.getElementById('paraFrm').submit(); 
 	}
 
  function callDown(id)
 {  
 	var len=document.getElementById('paraFrm_modeLength').value;  
 	//alert("length"+len);
 	var pageNo=document.getElementById('paraFrm_show').value;
 	//alert('----'+pageNo); 
 	if(pageNo==""){
 	pageNo=1;
 	} 
 	id=(pageNo-1)*20+id;
 	 //alert("id"+id);	
  	if(id==eval(len))
  	{    
  	alert("This is last record, so you cannot down the record.");
 	 return false;
 	 }
  	
   	document.getElementById('paraFrm_statusDown').value="true";
   	document.getElementById('paraFrm_statusUp').value="false";
   	document.getElementById('paraFrm_upId').value=id; 
   	document.getElementById('paraFrm').action="RoomType_upData.action";
   	document.getElementById('paraFrm').submit(); 
 }*/
 function callUP(id,UP)
 	{
 	//alert('enter')  
 	  document.getElementById('paraFrm_upDownFlag').value=UP;
 	  var pageNo=document.getElementById('paraFrm_show').value;
 	 // alert(id);
 	  if(id!=1)
 	  { 
 	     if(id%20==1)
       {
       //alert('in if');
         var pgNumber =document.getElementById('selectname').value;
         var lastRecord = document.getElementById('paraFrm_lastRecordOfPage').value;
         var lastRecordArr = lastRecord.split(","); 
          
         document.getElementById('paraFrm_previousLevel').value = lastRecordArr[pgNumber-2];
       //  alert('in if11'+document.getElementById('paraFrm_previousLevel').value)
         var currentLevel = document.getElementById('itLevel'+id).value;
		 document.getElementById('paraFrm_currentLevel').value =currentLevel ; 
       }
       else
       {
     //  alert('else');
	 	   var preId= eval(id)-1;
	 	  // alert('preId'+preId);
	 	   var previous = document.getElementById('itLevel'+preId).value;
	 	   //alert('previous'+previous);
	 	   document.getElementById('paraFrm_previousLevel').value =previous ;
	 	 //  alert('in else111'+ document.getElementById('paraFrm_previousLevel').value) 
	 	   var current = document.getElementById('itLevel'+id).value;
	 	   document.getElementById('paraFrm_currentLevel').value =current ; 
 	     }
 	   }
 	//alert('----'+pageNo); 
	 if(pageNo==""){
	 pageNo=1;
 	}
 	id=(pageNo-1)*20+id;
 	//alert(id);
 	if(id=="1")
 	{
  	alert("This is first record,so you cannot up the record.");
  	return false;
 	}
 	 
  	 document.getElementById('paraFrm_statusUp').value="true";
  	 document.getElementById('paraFrm_statusDown').value="false";
  	 document.getElementById('paraFrm_upId').value=id; 
  	 document.getElementById('paraFrm').action="RoomType_upDataView.action";
  	 document.getElementById('paraFrm').submit(); 
 	}
 
  function callDown(id,DOWN)
 {  
  document.getElementById('paraFrm_upDownFlag').value=DOWN;
       //alert(id);
       var len=document.getElementById('paraFrm_modeLength').value;  
 	//alert("length"+len);
 	var pageNo=document.getElementById('paraFrm_show').value;
 	//alert('----'+pageNo); 
 	if(pageNo==""){
 	pageNo=1;
 	} 
 	//id=(pageNo-1)*20+id;
 	// alert("id"+id);	
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
        // alert("pgNumber======== "+pgNumber+" ===== firstRecordArr[pgNumber-1]"+firstRecordArr[pgNumber-1]);
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
   	document.getElementById('paraFrm').action="RoomType_upDataView.action";
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

