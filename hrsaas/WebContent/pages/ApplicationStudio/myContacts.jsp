<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="org.paradyne.lib.Utility"%>
<%@ include file="/pages/common/labelManagement.jsp"%>

<%
	Object[][] contDataObj = null;
	try {
		contDataObj = (Object[][]) request.getAttribute("conData");
		System.out.println("Length of Contact Object---"+contDataObj.length);
		for(int i=0;i< contDataObj.length;i++){
		 System.out.println("contDataObj Length"+contDataObj[i][i]);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
<s:form action="MyContacts" id="paraFrm" theme="simple">

	<div id='div_Id'
		style='position: absolute; z-index: 3; width: 165px; height: 20px; display: none; border: 1px solid #CCC; top: 100px; left: 250px; padding: 0px; background-color: #D0CCCA;'>

	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>

			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td style="background-color: #F0F0F0; padding: 3px;">
					<div align="center"><img src="../pages/common/img/page.gif"
						width="25" height="25" /></div>
					</td>

					<td align="center" style="cursor: move; width: 90%"
						bgcolor="#F0F0F0" colspan="3"><span id="moduleName"
						style="cursor: move; color: black;"><b>Quick Contacts </b></span></td>
					<td border="1" bgcolor="#F0F0F0" colspan="2" width="5%"
						style="font-family: Arial; position: relative; cursor: pointer; color: black; text-align: center;"
						onclick="hide_Div();">&nbsp;<b>X</b>&nbsp;</td>
				</tr>
				<tr style="background-color: #F0F0F0; padding: 3px;">
					<td colspan="1" width="5%">&nbsp;</td>
					<td colspan="1" nowrap="nowrap">Contact Category</td>
					<td colspan="2" width="45%"><s:textfield name="deptName"
						id="deptName" size="40" maxlength="40">
					</s:textfield></td>
					<td colspan="1">&nbsp;</td>
				</tr>
				<tr style="background-color: #F0F0F0; padding: 3px;">
					<td colspan="1" width="5%">&nbsp;</td>
					<td colspan="1" nowrap="nowrap">Contact Name</td>
					<td colspan="2" width="45%"><s:textfield name="contactName"
						id="contactName" size="40" maxlength="40">
					</s:textfield></td>
					<td colspan="1">&nbsp;</td>
				</tr>
				<tr style="background-color: #F0F0F0; padding: 3px;">
					<td colspan="1" width="5%">&nbsp;</td>
					<td colspan="1">Contact No.</td>
					<td colspan="2" width="45%"><s:textfield name="contactNo"
						onkeypress="return numbersOnly();" id="contactNo" size="40"
						maxlength="40"></s:textfield></td>
					<td colspan="1" width="5%">&nbsp;</td>
				</tr>
				<tr style="background-color: #F0F0F0; padding: 3px;">
					<td colspan="1" width="5%"></td>
					<td colspan="1" nowrap="nowrap">Email ID/ IM</td>
					<td colspan="2" width="45%"><s:textfield name="emailID"
						id="emailID" size="40" maxlength="40"></s:textfield></td>
					<td colspan="1" width="5%">&nbsp;</td>
				</tr>
				<tr style="background-color: #F0F0F0; padding: 3px;">
					<td colspan="1" width="5%"></td>
					<td colspan="1" nowrap="nowrap">Division</td>
					<td colspan="2" width="45%"><s:hidden name="contactDivCode"/><s:textarea name="contactDivision"
						readonly="true" cols="35" rows="2"/><img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="16" theme="simple"
						onclick="javascript:callF9Function('paraFrm_contactDivision','paraFrm_contactDivCode'); "></td>
					<td colspan="1" width="5%">&nbsp;</td>
				</tr>
				<tr style="background-color: #F0F0F0; padding: 3px;">
					<td colspan="1" width="5%" align="center">&nbsp;</td>
					<td colspan="3" align="center" nowrap="nowrap"><input
						type="button" name="Add" value="Add" align="middle"
						onclick="callAddData();">&nbsp;&nbsp;&nbsp;<input
						type="button" name="Cancel" value="Cancel"
						onclick="callCancelData();"></td>

					<td colspan="1" width="5%">&nbsp;</td>
				</tr>
				<s:hidden name="hiddenConId" id="hiddenConId" />
			</table>
			</td>
		</tr>
	</table>
	</div>
	<s:hidden name="hiddenEditCode" />
	<table width="100%" border="0" align="left" cellpadding="0"
		cellspacing="0">
		<tr>
			<td valign="bottom" class="txt" width="100%">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td style="padding: 3px;"><strong class="text_head"><img
						src="../pages/common/img/page.gif" width="25" height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Quick Contacts </strong><s:hidden name="flagHrs" /></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4" nowrap="nowrap"><a href="javascript:void(0);"
						class="contlink" title="Add Contacts" theme="simple"
						onclick="return callDiv();"> <img
						src="../pages/common/img/page.gif" height="15" width="15"
						align="absmiddle" border="0" /> Add Contacts</a></td>
					<td width="22%">
					<div align="right"><span class="style2"> </span></div>
					</td>
				</tr>

			</table>
			</td>
		</tr>

		<%
		int srNo = 0;
		%>

		<tr height="20">
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td height="20" class="formth" width="10%" nowrap="nowrap"><strong>Sr.No</strong></td>
					<td height="20" class="formth" width="20%"><strong>Contact Category</strong></td>
					<td height="20" class="formth" width="22%"><strong>Contact
					Name</strong></td>
					<td height="20" class="formth" width="15%"><strong>Contact
					No.</strong></td>
					<td height="20" class="formth" width="25%"><strong>Email
					Id/ IM</strong></td>					
					<td height="20" class="formth" width="8%" nowrap="nowrap"></td>
				</tr>

				<%
						if (contDataObj != null && contDataObj.length > 0) {
						for (int i = 0; i < contDataObj.length; i++) {
				%>

				<tr style="cursor: pointer;" height="20"
					onmouseover="newRowColor(this);" onmouseout="oldRowColor(this);"
					height="20" class="sortableTD"
					>

					<td align="center"><%=++srNo%></td>

					<td height="20" class="sortableTD"><%=Utility.checkNull(String
									.valueOf(contDataObj[i][1]))%>
					</td>
					<td height="20" class="sortableTD"><%=Utility.checkNull(String
									.valueOf(contDataObj[i][2]))%>
					<s:hidden name="contactHiddenId"
						value="<%=Utility.checkNull(String.valueOf(contDataObj[i][0]))%>"></s:hidden>
					</td>

					<td height="20" class="sortableTD" width="15%"><%=Utility.checkNull(String
									.valueOf(contDataObj[i][3]))%>
					</td>

					<td height="20" class="sortableTD" width="15%"><%=Utility.checkNull(String
									.valueOf(contDataObj[i][4]))%><s:hidden name='<%=Utility.checkNull(String
									.valueOf(contDataObj[i][5]))%>'/>
					</td>
					<td height="20" class="sortableTD" nowrap="nowrap"><img
						style="cursor: pointer;"
						onclick="openDiv('<%=Utility.checkNull(String.valueOf(contDataObj[i][0]))%>',
										 '<%=Utility.checkNull(String.valueOf(contDataObj[i][1]))%>',
										 '<%=Utility.checkNull(String.valueOf(contDataObj[i][2]))%>',
										 '<%=Utility.checkNull(String.valueOf(contDataObj[i][3]))%>',
										 '<%=Utility.checkNull(String.valueOf(contDataObj[i][4]))%>',
										 '<%=Utility.checkNull(String.valueOf(contDataObj[i][6]))%>',
										 '<%=Utility.checkNull(String.valueOf(contDataObj[i][5]))%>')"										 
						src="../pages/mypage/images/icons/edit.png" title="Edit contacts" />
					| <img title="Remove contacts"
						src="../pages/mypage/images/icons/delete.png"
						style="cursor: pointer;"
						onclick="callDelete('<%=Utility.checkNull(String.valueOf(contDataObj[i][0])) %>')" />
					</td>
				</tr>

				<%
					}
					} else {
				%>
				<tr>
					<td height="20" align="center" colspan="8"><font color="red">No
					Data to display</font></td>
				</tr>
				<%
				}
				%>

			</table>
			</td>
		</tr>
	</table>
</s:form>

<script>

autoDate () ;

function autoDate() {
		var tDay = new Date();
		var tMonth = tDay.getMonth()+1;
		var tDate = tDay.getDate();
		var tHours =tDay.getHours();
		var tMinutes =tDay.getMinutes();		
			if ( tMonth < 10) tMonth = "0"+tMonth;
			if ( tDate < 10) tDate = "0"+tDate;			
			if ( tHours < 10) tHours = "0"+tHours;			
			if ( tMinutes < 10) tMinutes = "0"+tMinutes;			
			var then =tDate+"/"+tMonth+"/"+tDay.getFullYear();
			then += ' '+tHours+':'+tMinutes; 
			document.getElementById("favId").innerHTML=then;			
}

function callDiv(){	
	try{	
 		document.getElementById('div_Id').style.display = '';
 		document.getElementById('contactName').value='';
		document.getElementById('deptName').focus();
		document.getElementById('contactNo').value='';
		document.getElementById('deptName').value='';
		document.getElementById('emailID').value='';
		document.getElementById('paraFrm_contactDivision').value='';
		document.getElementById()('paraFrm_contactDivCode').value='';
		}catch(e){ }
	}
	
	function hide_Div() {		
		document.getElementById('div_Id').style.display = 'none';
		document.getElementById('contactName').value =trim(document.getElementById('contactName').value);
	}
	
	function openDiv(contactID, deptName, contactName, contactNo, emailID,contactDivision,ContactDivCode){
		try {
			document.getElementById('div_Id').style.display = '';
			document.getElementById('hiddenConId').value = contactID;
			document.getElementById('deptName').value = deptName;
			document.getElementById('contactName').value = contactName;
			document.getElementById('contactNo').value = contactNo;
			document.getElementById('emailID').value = emailID;			
			document.getElementById('paraFrm_contactDivCode').value=ContactDivCode;
			document.getElementById('paraFrm_contactDivision').value=contactDivision;			
		} catch(e) {
		}
	}
	
	function callAddData() {
	 try{	
		  var contactCode =document.getElementById('hiddenConId').value ;
		  var contactName =trim(document.getElementById('contactName').value) ;
		  var contactNo =trim(document.getElementById('contactNo').value) ;	
		  var emailid=validateEmail('emailID');
  		  if(!emailid){
  			return false;
  		  }	
		  if(trim(document.getElementById('contactName').value)!="" ){
				document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/AppStudio/MyContacts_addMyContacts.action?contactCode='+contactCode;
	 			document.getElementById('paraFrm').submit();
	 	  }
	 	  else{
	 			document.getElementById('div_Id').style.display = 'none';      
	 	  }	
	 }catch(e){
	 }
	return true
 }
	
	
function callDelete(id){
    var conf=confirm("Do you really want to delete contacts?");
    if(conf) {
    	   	document.getElementById('paraFrm_hiddenEditCode').value=id;
	   		document.getElementById("paraFrm").action="MyContacts_delete.action";
	  		document.getElementById("paraFrm").submit();
    } else {
    	 return false;
    }      
	 return true;
	   
   }
  
  
  
    function callCancelData()
  {
    document.getElementById('hiddenConId').value ="";
 	document.getElementById('div_Id').style.display = 'none';
 	document.getElementById('catId').style.display = 'none';
   }
   
 	function callNewFunc(data) {
 		 	var actualData=data.split(":")
 		 	if(actualData[0]=="http"){ 
 		 		window.open(data, 'new', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
 		 	}
 		 	else{
 		 		window.open("http://"+data, 'new', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
 		 	}
	}
 	
 	
window.onload()
{
callDisplayOtherText(document.getElementById('paraFrm_deptName').value);
}
function callF9Function(divName1,divCode1){    
   callsF9(500,325,'MyContacts_f9actionDiv.action?divName='+divName1+'&divCode='+divCode1);
 }
</script>

