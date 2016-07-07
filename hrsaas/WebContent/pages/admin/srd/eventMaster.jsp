<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript">
	var records = new Array();
</script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="EventMaster" id="paraFrm" validate="true" target="main"
	theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Event
					Master </strong></td>
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
			<table width="100%" border="0" cellpadding="1" cellspacing="1">
				<tr>
					<td colspan="4"><s:submit cssClass="add"
						action="EventMaster_save" value="    Save    "
						onclick="return callsave();" /> <s:submit cssClass="reset"
						action="EventMaster_reset" value="    Reset    " /> <input
						type="button" class="search" value=" Search "
						onclick="javascript:callsF9(500,325,'EventMaster_f9action.action');" />
					</td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			<label></label></td>
		</tr>
		
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td>

					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="20%" colspan="1"><label class="set"
								name="eventName" id="eventName" ondblclick="callShowDiv(this);"><%=label.get("eventName")%></label><font
								color="red">*</font> :</td>
							<td width="30%" colspan="1"><s:hidden name="eventCategory" />
							<s:hidden name="eventModule"></s:hidden> <s:textfield
								theme="simple" name="eventName" size="20" maxlength="60" /></td>
							<td width="20%"><label class="set"
								name="eventTypeLabel" id="eventTypeLabel" ondblclick="callShowDiv(this);"><%=label.get("eventTypeLabel")%></label>:</td>
							<td width="30%" colspan="1"><s:hidden name="eventTypeCode" />
								<s:textfield theme="simple" name="eventType" size="20" maxlength="60" />
								<input type="button" name="Submit3"
								 class="button" value="..." onclick="javascript:callsF9(500,325,'EventMaster_f9eventType.action');" />
								</td>
						</tr>
						<tr>
							<td width="20%" colspan="1"><label class="set"
								name="eventYear" id="eventYear" ondblclick="callShowDiv(this);"><%=label.get("eventYear")%></label><font
								color="red">*</font> :</td>
							<td width="30%" colspan="1"><s:textfield theme="simple"
								name="eventFromYear" onblur="add();" size="20" maxlength="4"
								onkeypress="return numbersOnly();" /></td>

							<td width="20%" colspan="1"><label class="set"
								name="eventYear" id="eventYear" ondblclick="callShowDiv(this);"><%=label.get("eventYear")%></label><font
								color="red">*</font> :</td>
							<td width="30%" colspan="1"><s:textfield theme="simple"
								name="eventToYear" readonly="true" size="20" maxlength="4"
								onkeypress="return numbersOnly();" /></td>
						</tr>
						<tr>
							<td width="20%" colspan="1"><label class="set"
								name="event.date" id="event.date"
								ondblclick="callShowDiv(this);"><%=label.get("event.date")%></label><font
								color="red">*</font> :</td>
							<td width="30%" colspan="3"><s:textfield theme="simple"
								name="eventDate" size="20" /> <s:a
								href="javascript:NewCal('paraFrm_eventDate','DDMMYYYY');">
								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" />
							</s:a></td>
						</tr>
						<tr>
							<td width="20%" colspan="1"><label class="set"
								name="event.location" id="event.location"
								ondblclick="callShowDiv(this);"><%=label.get("event.location")%></label><font
								color="red">*</font> :</td>
							<td width="80%" colspan="3"><s:textfield theme="simple"
								name="eventLocation" size="20" maxlength="80" /></td>
						</tr>
						<tr>
							<td width="20%" colspan="1"><label class="set" name="desc"
								id="desc" ondblclick="callShowDiv(this);"><%=label.get("desc")%></label>
							:</td>
							<td width="80%" colspan="3"><s:textarea theme="simple"
								name="eventDesc" cols="17" rows="3" /></td>
						</tr>
						<tr>
						   <td width="20%" colspan="1"><label class="set" name="eventDiv"
								id="eventDiv" ondblclick="callShowDiv(this);"><%=label.get("eventDiv")%></label>
							:</td>
							<td width="80%" colspan="3"><s:hidden name="eventDivCode"/>
								<s:textarea name="eventDivision" cols="25" rows="2"
								readonly="true"/><img
								src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="16" theme="simple"
								onclick="javascript:callF9Function('paraFrm_eventDivision','paraFrm_eventDivCode'); "></td>
						</tr>
						<tr>
							<td width="20%" colspan="1"><label class="set"
								name="upload.cat" id="upload.cat"
								ondblclick="callShowDiv(this);"><%=label.get("upload.cat")%></label><font
								color="red">*</font> :</td>
							<td width="80%" colspan="3"><s:select theme="simple"
								name="uploadCategory" value="%{eventModule}"
								list="#{'-1':'       ----Select----         ','P':'Photo Gallery','C':'Content'}" /></td>
						</tr>
						<tr>
							<td width="20%" colspan="1"><label class="set" name="upload"
								id="upload" ondblclick="callShowDiv(this);"><%=label.get("upload")%></label>:</td>
							<td width="80%" colspan="3"><s:textfield
								name="userUploadFileName" size="12" maxlength="40"
								onfocus="clearText('paraFrm_userUploadFileName','Image Name')"
								onblur="setText('paraFrm_userUploadFileName','Image Name')" />
							<s:textfield size="40" name="uploadFileName" readonly="true" />
							<input type="button" class="token" name="Browse" value="Browse"
								onclick="uploadFile('uploadFileName');" /> <s:submit name=""
								value="Upload Images" cssClass="token"
								action="EventMaster_addMultipleImages"
								onclick="return callUpload();" /></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
				<td width="70%"></td>
					<td width="30%" align="center"><input type="button"
						id="ctrlShow" class="delete" value=" Delete" onclick="deleteFun()" /></td>
				</tr>
				<tr>
					<td colspan="5">
					<table width="100%">
						<tr>
							<td class="formth" width="10%"><label id="sno" name="sno"
								ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></td>
							<td class="formth" width="60%" align="center"><label
								id="image" name="image" ondblclick="callShowDiv(this);"><%=label.get("image")%></label></td>
							<td align="center" class="formth" id="ctrlShow" nowrap="nowrap"
								width="30%"><input type="checkbox" id="selAll"
								style="cursor: hand;" title="Select all records to remove"
								onclick="selectAllRecords(this);" /></td>
						</tr>
						<%!int y = 0;%>
						<% int count = 0;
						int i=0;%>
						<s:iterator value="list">
							<tr>
								<td class="sortableTD" width="10%"><%=++count%>&nbsp; <s:hidden
									name="uploadCategoryItt" /><%++i;%><s:hidden name="srNo"
									value="%{<%=count%>}" /></td>								
								<td class="sortableTD" width="50%" nowrap="nowrap" align="left">&nbsp;
								<s:hidden name="uploadImageItt" /> <s:hidden
									name="uploadImagePathItt" /><input type="hidden"
									name="hdeletePath" id="hdeletePath<%=i%>" /><s:property
									value="uploadImageItt" /><a href="#"
									onclick="showRecord('<s:property value="uploadImagePathItt" />');">
								<font color="blue"><s:property value="uploadImagePathItt" /></font></a></td>
								<s:hidden value="%{uploadImageItt}"   id='<%="uploadImageItt" + i%>' />
								<script type="text/javascript">
									 records[<%=i%>] = document.getElementById('uploadImageItt'+'<%=i%>').value;
								</script>
								<td id="ctrlShow" align="center" class="sortableTD" width="40%"><input
									type="checkbox" class="checkbox" id="confChk<%=count%>"
									name="confChk" onclick="callDelete('<s:property value="uploadImageItt"/>','<%=i%>');"/>&nbsp;</td>
							</tr>
						</s:iterator>
						<%
						y = count;
						%>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<s:hidden name="dataPath" />
	<s:hidden name="hiddenAutoCode" />

</s:form>

<script>
  function uploadFile(fieldName) {
	
		var path="images/<%=session.getAttribute("session_pool")%>/employeePortal";
		 
		 window.open('../pages/common/multipleUploadFile.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
		 document.getElementById('paraFrm').target="main";
		} 		
 </script>
<script type="text/javascript">


onload();
function onload(){
	setText('paraFrm_userUploadFileName','Image Name');
}

function showRecord(fileName){		 		
	var path='<%=session.getAttribute("session_pool")%>';
	window.open('../pages/images/'+path+'/employeePortal/'+fileName);	
}
		
function getYear(){
     var current = new Date();
	 var year =current.getFullYear();
	 var yr =document.getElementById("paraFrm_eventFromYear").value;
	 if(yr==''){
	  	document.getElementById("paraFrm_eventFromYear").value =year;
	    getToYear();
	  }
	  else{
	   getToYear();
	  }
}
getYear();	
	
 function getToYear(){ 		
 		 var x=eval(document.getElementById('paraFrm_eventFromYear').value) +1;
 	 	 document.getElementById('paraFrm_eventToYear').value=x;
 }
 
function add(){
      var from = document.getElementById('paraFrm_eventFromYear').value;
      if(from==""){
    	 document.getElementById('paraFrm_eventToYear').value="";
      }
      else{
   	 	var x=eval(from) +1;
	  	document.getElementById('paraFrm_eventToYear').value=x;
	  }
   }
   
function callsave(){
 	var total = '<%=y%>'; 
    var eventModule = document.getElementById('paraFrm_eventModule').value;
    var event_name = document.getElementById('paraFrm_eventName').value;   
    var event_year = document.getElementById('paraFrm_eventFromYear').value;    
    var event_date = document.getElementById('paraFrm_eventDate').value;
    var event_location = document.getElementById('paraFrm_eventLocation').value;  
    if(eventModule=="-1"){
   			alert("Please select event category");
   			return false;
     }
    if(event_name==""){
   			alert("Please enter event title");
   			return false;
    }   
    if(event_year==""){
   			alert("Please enter event year");
   			return false;
    }   
    if(event_date==""){
   			alert("Please select event date");
   			return false;
    }   
    if(!validateDate('paraFrm_eventDate','event.date'))
				  return false;     
    if(event_location==""){
   			alert("Please enter event location");
   			return false;
    }   
    if(total==0){
  	 alert("Please upload photo");
   			return false;
    }   
    return true;  
   }

	
function callUpload(){	 
	   var uploadFilePath = document.getElementById('paraFrm_uploadFileName').value;
	   var userUploadFileName =document.getElementById('paraFrm_userUploadFileName').value;
	   var event_upload_cat = document.getElementById('paraFrm_uploadCategory').value;
      
         if(event_upload_cat=="-1"){
   			alert("Please select event upload category");
   			return false;
   		}
		if(uploadFilePath=="" ){
		alert("Please Upload Photo");
		return false;
		}		
		if(userUploadFileName=="" || userUploadFileName=="Image Name"){
			alert("Please enter name of photo");
			return false;
		}
	return true;
}

function selectAllRecords(object) {
		if(object.checked) {
			for(var i = 1; i <= records.length; i++) {
				document.getElementById('confChk' + i).checked = true;
				document.getElementById('hdeletePath' + i).value = records[i];
			}
		} else {
			for(var i = 1; i <= records.length; i++) {
				document.getElementById('confChk' + i).checked = false;
				document.getElementById('hdeletePath' + i).value = "";
			}
		}
	}
	
	function deleteFun(){
		var eventID = '<%= y%>';
		if(validChk()){
		 	var con=confirm('Do you want to delete the record(s) ?');
				if(con){				
					document.getElementById('paraFrm').action="EventMaster_deleteRecords.action";
	        		document.getElementById('paraFrm').target = "_self";
	   				document.getElementById('paraFrm').submit();
				}	
				else {
				    document.getElementById('selAll').checked=false;
					for(var i=1;i<=eventID;i++){
					  document.getElementById('confChk'+a).checked = false;
			    	  document.getElementById('hdeletePath'+a).value="";						
					}
					return false;
				}			
			}
			else {
	 			alert('Please Select Atleast one Record');
	 			return false;
	 		}
	}
	
	function validChk(){
		var cnt = '<%= y%>';
		for(var i=1;i<=cnt;i++){
		 if(document.getElementById('confChk'+i).checked == true){	
	    	return true;
	   	 }	   
	  	}
	  return false;
	
	}
	
	function callDelete(path,i){
		if(document.getElementById('confChk'+i).checked == true){	  
	    	document.getElementById('hdeletePath'+i).value=path;
	   	}
	   	else
	   		document.getElementById('hdeletePath'+i).value="";
	}
	
function callF9Function(divName1,divCode1){    
   callsF9(500,325,'EventMaster_f9EventDiv.action?divName='+divName1+'&divCode='+divCode1);
 }
 
</script>