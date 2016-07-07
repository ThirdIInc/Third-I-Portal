<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="DonationMaster" validate="true" id="paraFrm" validate="true"
	theme="simple">
	<s:hidden name="myPage" id="myPage" />
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />
	<s:hidden name="editFlag"/>
	<s:hidden name="cancelFlag"/>
	<s:hidden name="hiddenDonationName" id="hiddenDonationName"/>
	<s:hidden name="hiddenpercentage" id="hiddenpercentage"/>
	<s:hidden name="donationHiddenCode"/>
	<table class="formbg" align="right" width="100%">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Donation </strong></td>
					
					


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
						<tr>
							<td width="75%"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
							<td width="22%">
							<div align="right"><span class="style2"><font
								color="red">*</font></span> Indicates Required</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				
				
				<!-- Dynamic Rows Start -->
				
				<tr>
				<td colspan="7" >
				<table width="100%" border="0" align="center" cellpadding="2"
					cellspacing="2" class="formbg">
					
					<tr>
						<td  height="22" class="formtext"><label  class = "set"  id="donation.name" name="donation.name" ondblclick="callShowDiv(this);"><%=label.get("donation.name")%></label> :<font
							color="red">*</font>
						<s:textfield size="25" name="donationName"
									maxlength="50"/></td>
						</td>
						
						<td  height="22" class="formtext"><label  class = "set"  id="donation.percentage" name="donation.percentage" ondblclick="callShowDiv(this);"><%=label.get("donation.percentage")%></label> :<font
							color="red">*</font>
						<s:textfield size="25" name="percentage"
									maxlength="5" onkeypress="return checkNumbersWithDot(this);" /></td>
						</td>
						
						<td  height="22" class="formtext"><label name="is.active"
										class="set" id="is.active" ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label> :
									<s:checkbox theme="simple" id="isActive" name="isActive" /><s:hidden
						name="active" /></td>
						
						<td  align="center">
						<input type="button" value="Add Donation" onclick="return callAdd();" class="add">
						</td>
					</tr>
					
			
					
					
					<tr>
					<td colspan="7">
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="1" class="formbg" id="tableID">
							
    	<tr>
					<td colspan="7">
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="1" >
													
							<tr><td>
    <% int totalPage = 0; int pageNo = 0; %>
	<s:if test="modeLength">
			<td colspan="7"width="20%" class="text_head"><strong
								class="forminnerhead">Donation Details : </strong></td>
			
			<td id="ctrlShow" width="80%" align="right" class="">
				<b>Page:</b>
				<%	 totalPage = (Integer) request.getAttribute("totalPage");
					 pageNo = (Integer) request.getAttribute("pageNo");
				%>
				<a href="#" onclick="callPage('1', 'F', '<%=totalPage%>', 'DonationMaster_input.action');">
					<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
				</a>&nbsp;
				<a href="#" onclick="callPage('P', 'P', '<%=totalPage%>', 'DonationMaster_input.action');" >
					<img title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
				</a> 
				<input type="text" name="pageNo" id="pageNo" size="3" value="<%=pageNo%>" maxlength="10"
				onkeypress="callPageText(event, '<%=totalPage%>', 'DonationMaster_input.action');return numbersOnly();" /> of <%=totalPage%>
				<a href="#" onclick="callPage('N', 'N', '<%=totalPage%>', 'DonationMaster_input.action')" >
					<img title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
				</a>&nbsp;
				<a href="#" onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'DonationMaster_input.action');" >
					<img title="Last Page" src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
				</a>
			</td></s:if>
    			</td></tr></table></td>
    			
    			
    			</tr>
						
						
						<tr>
						<td colspan="1" width="20%" class="formth"><label class="set" name="donation.name"
							id="donation.name1" ondblclick="callShowDiv(this);"><%=label.get("donation.name")%></label>
						</td>						
						
						<td colspan="1" width="19%" align="center" class="formth">
						<label class="set" name="donation.percentage" id="donation.percentage1"
							ondblclick="callShowDiv(this);"><%=label.get("donation.percentage")%></label></td>	
						
						<td colspan="1" width="19%" align="center" class="formth">
						<label class="set" name="is.active" id="is.active1"
							ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label></td>		
						
					<td colspan="1" width="10%" class="formth" >Edit/Delete</td>
						
						</tr>
						
						<%
										int i = pageNo * 10 - 10;									
									%>
						
						<%int count1=1; %>					
						<s:iterator value="donationList">
						<tr><s:hidden name="ittdonationCode" />
						
						<!--
							 <td  colspan="1" class="sortableTD">&nbsp;<%=++i%></td>
							 --><td  colspan="1"  class="sortableTD">&nbsp;
								<s:property value="ittdonationName"/><s:hidden name="ittdonationName"/>
							 </td>
						
						
							 <td  colspan="1"  class="sortableTD" align="right">&nbsp;
								<s:property value="ittpercentage" id="ittpercentage<%=count1%>"/><s:hidden name="ittpercentage"/>
							 </td>
							 <td  colspan="1"  class="sortableTD">&nbsp;
								<s:property value="ittactive"/><s:hidden name="ittactive"/>
								<s:hidden name="ittdonationCode"/>
							 </td>
							
								
								
						
							 <td class="sortableTD" width="19%" id="ctrlShow" align="center">
									<input type="button" class="rowEdit"
										onclick="callForEdit('<s:property value="ittdonationCode"/>')"
										 />
								|
									<input type="button" class="rowDelete"
										onclick="callDelete('<s:property value="ittdonationCode"/>')"
										/>
								<!--
							 
							 <%count1++; %>
							 
						
						-->
								</td>
						</tr>
						
						
						</s:iterator>
					</table>
					<s:hidden name="paracode" />
					</td>
				</tr>
			<tr><td width="100%" colspan="5">
					<table border="0" width="100%">
			<td align="Right"><s:if test="modeLength"><b>Total No. Of Records:</b>&nbsp;<s:property value="totalRecords" /></s:if></td>
			
			</table></td>
			</tr>	
				</table>
				</td>
			</tr>
			
			<!-- Dynamic rows end -->
				
			
				</table></s:form>
				
<script>



// For Save Button

function saveFun(){	


	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'DonationMaster_save.action';
		document.getElementById('paraFrm').submit();
	  return true;
}  


	function resetFun() {
		document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'DonationMaster_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'DonationMaster_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'DonationMaster_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	function callSearch(action) {
		var myWinDiv = window.open('', 'myWinDiv', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv';
		document.getElementById("paraFrm").action = 'DonationMaster_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}
	
	function editFun() {
		return true;
	}
	
	
	function callValidationHS(){
		try{
		
		
		var hCode=document.getElementById('paraFrm_donationName').value;
	document.getElementById('hiddenDonationName').value = hCode; 
	
	var hpercentage=document.getElementById('paraFrm_percentage').value;
	document.getElementById('hiddenpercentage').value = hpercentage; 
		
		var qtt=trimData(document.getElementById('paraFrm_donationName').value);
		
		if(document.getElementById('paraFrm_donationName').value==''){
			 alert ("Please enter " +document.getElementById('donation.name').innerHTML.toLowerCase());
			 document.getElementById('paraFrm_donationName').focus();
			return false;
		}
		
		if(qtt==''){
			 alert ("Please enter " +document.getElementById('donation.name').innerHTML.toLowerCase());
			 document.getElementById('paraFrm_donationName').focus();
			return false;
		}
		
		if(document.getElementById('paraFrm_percentage').value==''){
			 alert ("Please enter " +document.getElementById('donation.percentage').innerHTML.toLowerCase());
			 document.getElementById('paraFrm_percentage').focus();
			return false;
		}
		
		 	if(document.getElementById('isActive').checked)
		 	{	
		 		document.getElementById('paraFrm_active').value="Y";
		 	}else{
		 		
		 		document.getElementById('paraFrm_active').value="N";
		 	}	
		
		} catch(e) {
		alert(e);
		}
		
		addRowToTable11('tableID','donationName,percentage,active,delete,donationCode','S,S,S,delete,H');
		}
		
		
			
		 
		 
		
				function imposeMaxLength(Event, Object, MaxLen)
{
        return (Object.value.length <= MaxLen)||(Event.keyCode == 8 ||Event.keyCode==46||(Event.keyCode>=35&&Event.keyCode<=40))
}

function addRowToTable11(tableId,fields,type)
		{
		  fieldList = fields.split(",");
		   fieldType = type.split(",");
		  var tbl = document.getElementById(tableId);
		  var lastRow = tbl.rows.length;
		  var iteration = lastRow;
		  var row = tbl.insertRow(lastRow);
		  var cellRight ="";	
		  var el="";
		  for(i = 0; i < fieldList.length; i++) {
		  
		  
		 
		  if(fieldType[i]=='delete'){
		   		cellRight = row.insertCell(i);
			   var column3 = document.createElement('img');
			  column3.type='image';
			  column3.src="../pages/common/css/icons/delete.gif";
			  column3.align='absmiddle';
			  column3.id='img'+ i;
			  column3.theme='simple';
			  cellRight.align='center';
			  cellRight.className='sortableTD';
			
			  column3.onclick=function(){
			  try {
			   	deleteSelectedRow(this);
			  	
			  }catch(e){alert(e);}
			  };
			  cellRight.appendChild(column3);
			  			 
			  
		  }
		  else{
			  	
			  if(fieldType[i]=='N'){
			  	 el = document.createElement('input');
			  	 cellRight = row.insertCell(i);
			     el.style.border = 'none';
			     el.type = 'text';
			     el.readOnly =true;			     
			     el.value=iteration;
			  	 el.name = 'itt' + fieldList[i];
			 	 el.size = 10;
			   	 cellRight.className='sortableTD';
			  	 cellRight.appendChild(el);
			  }
			  if(fieldType[i]=='S'){
			  	 el = document.createElement('input');
			  	 cellRight = row.insertCell(i);
			     el.style.border = 'none';
			     el.type = 'text';
			     el.readOnly =true;			     
			     el.value=document.getElementById('paraFrm_'+fieldList[i]).value;
			  	 el.name = 'itt' + fieldList[i];
			  	  el.id = 'itt' + fieldList[i]+iteration;
			  	//  alert(el.id);
			 	 el.size = 20;
			   	 cellRight.className='sortableTD';
			  	 cellRight.appendChild(el);
			  }
			  
			  if(fieldType[i]=='R'){
			  	 el = document.createElement('input');
			  	 cellRight = row.insertCell(i);
			     el.style.border = 'none';
			     el.type = 'text';
			     el.readOnly =true;			     
			  
			  	
			  	//  alert(el.id);
			 	 el.size = 5;
			   	 cellRight.className='sortableTD';
			  	 cellRight.appendChild(el);
			  }
			   if(fieldType[i]=='H'){
			   			   		   
			  	el = document.createElement('input');
				//j = document.createTextNode(iteration);
				el.setAttribute('type', 'hidden');
				el.setAttribute('value', 'Y');
				el.setAttribute('name', 'itt' + fieldList[i]);
				cellRight.appendChild(el);
				
			  	 					  	 			  
			   }
			 
			  				   
			  		 
		  }
		  
		  
		  
		  }
		  
		  for(i = 0; i < fieldList.length; i++) {
		   if(fieldType[i]=='H'||fieldType[i]=='S'){
		    document.getElementById('paraFrm_'+fieldList[i]).value='';	
		    }
		   }  
		  
		   
		 }
	function  deleteSelectedRow(obj){
		var con=confirm('Do you want to delete the record(s) ?');
	 	if(con){
			var delRow = obj.parentNode.parentNode;
			var tbl = delRow.parentNode.parentNode;
			var rIndex = delRow.sectionRowIndex;
			var rowArray = new Array(delRow);
			deleteRows(rowArray);
		}
	}
	
	function deleteRows(rowObjArray){
		for (var i=0; i<rowObjArray.length; i++) {
			var rIndex = rowObjArray[i].sectionRowIndex;
			rowObjArray[i].parentNode.deleteRow(rIndex);
		}
	}
			function trimData(str) {     
	if(!str || typeof str != 'string')         
		return null;     
	return str.replace(/^[\s]+/,'').replace(/[\s]+$/,'').replace(/[\s]{2,}/,' '); 
	}	
		function reportFun() {
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = 'DonationMaster_report.action';
		document.getElementById('paraFrm').submit();
	}
	
	// Application In-Process List Begins
	function callPage(id, pageImg, totalPageHid, action) {		
		try
		{
		pageNo = document.getElementById('pageNo').value;	
		actPage = document.getElementById('myPage').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNo').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNo').value = actPage;
			    document.getElementById('pageNo').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNo').value=actPage;
			    document.getElementById('pageNo').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNo').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNo').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNo').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNo').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNo').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNo').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPage').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}

function callPageText(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNo').value;
		 	var actPage = document.getElementById('myPage').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNo').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNo').focus();
		     document.getElementById('pageNo').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNo').focus();
		     document.getElementById('pageNo').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNo').focus();
		      return false;
	        }
	        
	         document.getElementById('myPage').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}
	// Application In-Process List Ends
	
	function callAdd(){
	try{
	
	var hCode=document.getElementById('paraFrm_donationName').value;
	document.getElementById('hiddenDonationName').value = hCode; 
	
	var hpercentage=document.getElementById('paraFrm_percentage').value;
	document.getElementById('hiddenpercentage').value = hpercentage; 
		
		var qtt=trimData(document.getElementById('paraFrm_donationName').value);
		
		if(document.getElementById('paraFrm_donationName').value==''){
			 alert ("Please enter " +document.getElementById('donation.name').innerHTML.toLowerCase());
			 document.getElementById('paraFrm_donationName').focus();
			return false;
		}
		
		if(qtt==''){
			 alert ("Please enter " +document.getElementById('donation.name').innerHTML.toLowerCase());
			 document.getElementById('paraFrm_donationName').focus();
			return false;
		}
		
		if(document.getElementById('paraFrm_percentage').value==''){
			 alert ("Please enter " +document.getElementById('donation.percentage').innerHTML.toLowerCase());
			 document.getElementById('paraFrm_percentage').focus();
			return false;
		}
		
		
		if ((document.getElementById('paraFrm_percentage').value) > 100) {
    			 alert("Percentage must be less than 100%\n");
    			document.getElementById('paraFrm_percentage').focus();
		 		return false;
			}
	
	}catch (e)
	{
	alert(e);
	}
		
		
		document.getElementById('paraFrm').target="main";		  
	   	document.getElementById("paraFrm").action="DonationMaster_addDonationDtl.action";
	    document.getElementById("paraFrm").submit();
	    document.getElementById('paraFrm').target="main";
	}
	
	 function callForEdit(id){
    try{
	   	document.getElementById("paraFrm").action="DonationMaster_edit.action";
	  	document.getElementById('paraFrm_paracode').value=id;
	  	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
   	}catch (e)
	{
	alert(e);
	}
	}
	function callDelete(id){
		var conf=confirm("Are you sure to delete this record?");   
   		if(conf) {
   		  		  
	   	document.getElementById("paraFrm").action="DonationMaster_delete.action";
	  	document.getElementById('paraFrm_paracode').value=id;
	    document.getElementById("paraFrm").submit();
	    document.getElementById('paraFrm').target="main";
   }else {
   		return false;
   
   }
   }
   
   function checkNumbersWithDot(obj) {
	var count = 0;
	var txtNo = obj.value;
	
	for(var i = 0; i < txtNo.length; i++) {
		if(txtNo.charAt(i) == '.') {
			count = count + 1;
		}
	}
	
	if(count > 0) {
		if(!numbersOnly()) {
			return false;
		}
	} else if(!numbersWithDot()) {
		return false;
	}
	return true;
}
	
</script>
