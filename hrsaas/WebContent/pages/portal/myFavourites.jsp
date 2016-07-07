
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="org.paradyne.lib.Utility"%>
<%@ include file="/pages/common/labelManagement.jsp"%>

<%
	Object[][] favDataObj = null;
	try {
		favDataObj = (Object[][]) request.getAttribute("favData");

	} catch (Exception e) {
		e.printStackTrace();
	}
%>
<s:form action="MyFavourites" id="paraFrm" theme="simple">

	<div id='div_Id'
		style='position: absolute; z-index: 3; width: 165px; height: 20px; display: none; border: 1px solid #CCC; top: 100px; left: 250px; padding: 0px; background-color: #D0CCCA;'>

	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>

			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>

					<td style="background-color: #F0F0F0; padding: 3px;">
					<div align="center"><img
						src="../pages/mypage/images/icons/myfavourite.gif" width="25"
						height="25" /></div>
					</td>

					<td align="center" style="cursor: move; width: 90%"
						bgcolor="#F0F0F0" colspan="3"
						>
					<span id="moduleName" style="cursor: move; color: black;"><b>My Favorites</b></span></td>

					<td border="1" bgcolor="#F0F0F0" colspan="2" width="5%"
						style="font-family: Arial; position: relative; cursor: pointer; color: black; text-align: center;"
						onclick="hide_Div();">&nbsp;<b>X</b>&nbsp;</td>
				</tr>


				<tr style="background-color: #F0F0F0; padding: 3px; ">
					<td colspan="1" width="5%">&nbsp;</td>
					<td colspan="1"  nowrap="nowrap">Link
					Name</td>
					<td colspan="2" width="45%" ><s:textfield name="linkName" id="linkName"
						size="40" maxlength="40" > </s:textfield></td>
					<td colspan="1">&nbsp;</td>

				</tr>
				<tr style="background-color: #F0F0F0; padding: 3px;">
					<td colspan="1" width="5%">&nbsp;</td>
					<td colspan="1">Link
					URL</td>
					<td colspan="2" width="45%"><s:textfield name="linkUrl" id="linkUrl"
						size="40" maxlength="40" ></s:textfield></td>
					<td colspan="1" width="5%">&nbsp;</td>
				</tr>


				<tr style="background-color: #F0F0F0; padding: 3px;">
					<td colspan="1" width="5%"></td>
					<td colspan="1"  nowrap="nowrap">Category</td>
					
					
						
					
					<td colspan="2" width="45%"><s:select name="category" cssStyle="width:175"
								headerKey="" headerValue="--- Select category ---" id="category"
								list="tmap" onclick="callDisplayOtherText();"/></td>
					
					
					
					<td colspan="1" width="5%" >&nbsp;</td>
					
				</tr>

				<tr style="background-color: #F0F0F0; padding: 3px;" id="catId" style="display: none">
				<td colspan="1" width="5%" >&nbsp;</td>	
				<td colspan="1">Other Description</td>
				<td  colspan="1" width="80%" id="" >
					<s:textfield name="categoryOther" id="categoryOther"
						size="25" maxlength="40"></s:textfield></td>
				<td colspan="2" width="5%" >&nbsp;</td>		
						
				</tr>
				
				<tr  style="background-color: #F0F0F0; padding: 3px;  " >
					<td colspan="1" width="5%" align="center" >&nbsp;</td>
					<td colspan="3" align="center"
						nowrap="nowrap"><input type="button" name="Add" value="Add" align="middle"
						onclick="callAddData();" >&nbsp;&nbsp;&nbsp;<input
						type="button" name="Cancel" value="Cancel"
						onclick="callCancelData();"></td>
					
					<td colspan="1" width="5%">&nbsp;</td>
				</tr>

			</table>
			</td>
		</tr>
	</table>

	</div>


	<s:hidden name="hiddenFavId" id="hiddenFavId"></s:hidden>
	<s:hidden name="hiddenEditCode" />

	<table width="100%" border="0" align="left" cellpadding="0"
		cellspacing="0">

		 
		
		<tr>
			<td valign="bottom" class="txt" width="100%">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="pageHeader">
				<tr>
					<td style="padding: 3px;"><strong class="text_head"><img
						src="../pages/mypage/images/icons/myfavourite.png"  
						  /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">My
					 Favorites
 </strong></td>
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
					<td colspan="4" nowrap="nowrap"><a href="javascript:void(0);" class="contlink"
						title="Add Favorites" theme="simple" onclick="return callDiv();"> <img
						src="../pages/mypage/images/icons/draft.png"  
						  align="absmiddle" border="0" /> Add Favorites</a></td>
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
			<table width="100%" border="0">
				<tr>
					<td height="20" width="10%" class="formth" nowrap="nowrap"><strong>Sr.No</strong></td>
					<td height="20" class="formth" width="27%"><strong>Link
					Name</strong></td>
					<td height="20" class="formth" width="25%"><strong>Category</strong></td>
					<td height="20" class="formth" width="15%" nowrap="nowrap"><strong>Created
					On </strong></td>
					<td height="20" class="formth" width="15%" nowrap="nowrap"><strong>Updated
					On </strong></td>
					<td height="20" class="formth" width="8%" nowrap="nowrap"></td>
				</tr>

				<%
						if (favDataObj != null && favDataObj.length > 0) {

						for (int i = 0; i < favDataObj.length; i++) {
				%>



				<tr style="cursor: pointer;" height="20"
					onmouseover="newRowColor(this);" onmouseout="oldRowColor(this);"
					
					>

					<td height="20" align="center" class="sortableTD"
						title="Double click to view favourites" onclick="callNewFunc('<%=String.valueOf(favDataObj[i][3])%>');"><%=++srNo%> </td>
					<td height="20" class="sortableTD" ><a href="javascript:void(0);"
						onclick="callNewFunc('<%=String.valueOf(favDataObj[i][3])%>');"
						class="servicelink"> <%=Utility.checkNull(String
									.valueOf(favDataObj[i][2]))%></a></td>
				

					<td height="20" class="sortableTD" align="center"
						title="Double click to view favourites"
						onclick="callNewFunc('<%=String.valueOf(favDataObj[i][3])%>');"><%=Utility.checkNull(String
									.valueOf(favDataObj[i][4]))%></td>
					<td height="20" class="sortableTD" width="15%" align="center"
						title="Double click to view favourites"
						onclick="callNewFunc('<%=String.valueOf(favDataObj[i][3])%>');"><%=Utility.checkNull(String
									.valueOf(favDataObj[i][5]))%></td>

					<td height="20" class="sortableTD" width="15%" align="center"
						title="Double click to view favourites"
						onclick="callNewFunc('<%=String.valueOf(favDataObj[i][3])%>');"><%=Utility.checkNull(String
									.valueOf(favDataObj[i][6]))%></td>
					<td height="20" align="center" class="sortableTD" nowrap="nowrap"
						title="Double click to view favourites"><img
						style="cursor: pointer;"
						onclick="openDiv('<%=Utility.checkNull(String.valueOf(favDataObj[i][2]))%>','<%=Utility.checkNull(String.valueOf(favDataObj[i][3]))%>','<%=Utility.checkNull(String.valueOf(favDataObj[i][4]))%>','<%=Utility.checkNull(String.valueOf(favDataObj[i][1]))%>')"
						src="../pages/mypage/images/icons/edit.png"
						title="Edit favourites" /> | <img title="Remove favourites"
						src="../pages/mypage/images/icons/delete.png"
						style="cursor: pointer;"
						onclick="callDelete('<%=Utility.checkNull(String.valueOf(favDataObj[i][1])) %>')" />

					</td>
				</tr>

				<%
					}
					} else {
				%>
				<tr>
					<td height="20" align="center" colspan="6" width="100%"
						class="border">No Data to display</td>
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

function autoDate() 
 	{
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
function callDiv()
	{	
	try{
	
 		document.getElementById('div_Id').style.display = '';
 		document.getElementById('linkName').value='';
		document.getElementById('linkName').focus();
		document.getElementById('linkUrl').value='';
		document.getElementById('category').value='';
		
		}catch(e){ alert(e);}
	}
	
	function hide_Div() {
		
		document.getElementById('div_Id').style.display = 'none';
		document.getElementById('linkName').value =trim(document.getElementById('linkName').value);
	}
	
	function openDiv(linkName,LinkUrl,category,favId)
	{
		document.getElementById('div_Id').style.display = '';
		document.getElementById('linkName').value=linkName;
		document.getElementById('catId').style.display = 'none';
		document.getElementById('linkUrl').value=LinkUrl;
		document.getElementById('category').value=category;
		document.getElementById('hiddenFavId').value=favId;
	}
	
	function callAddData()
	{
	
		try{
		
	var favCode =document.getElementById('hiddenFavId').value ;
	
	var linkName =trim(document.getElementById('linkName').value) ;
	var linkUrl =trim(document.getElementById('linkUrl').value) ;
	
	  		var website=validateWebSite('linkUrl');
			if(!website){
				return false;
			}
		 

			
	
	if(trim(document.getElementById('linkName').value)!="" ){
		document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/portal/MyFavourites_addMyFavourites.action?favCode='+favCode;
	 	document.getElementById('paraFrm').submit();
	 }
	 else{
	 	document.getElementById('div_Id').style.display = 'none';
	 }
	 
	
	}catch(e)
	{
		alert("Error  "+e);
	}
 
	return true
	}
	
	
	function callDelete(id){
    
    
    	//  alert("id  "+id);
    	 var conf=confirm("Do you really want to delete favorite?");
    	 
    	 if(conf)
    	 {
    	   document.getElementById('paraFrm_hiddenEditCode').value=id;
	   	document.getElementById("paraFrm").action="MyFavourites_delete.action";
	  	document.getElementById("paraFrm").submit();
    	 }
    	 else{
    	 	return false;
    	 }
      
	 return true;
	   
   }
   
   function callDisplayOtherText(){
 	//alert("1--"+value)
 		if(document.getElementById('category').value=="Other"){
 			document.getElementById('catId').style.display = '';
 		}else{
 			document.getElementById('catId').style.display = 'none';
 		}
		if(value ==""){
 			document.getElementById('catId').style.display = 'none';
 		}
 		
 		
 	}
  function callButton(value)
  {

  	if(value!="" ){
  		document.getElementById('Add').disabled=false;
  		document.getElementById('div_Id').style.display ='';
  	}
  }
  
  
    function callCancelData()
  {
 	document.getElementById('div_Id').style.display = 'none';
 	document.getElementById('catId').style.display = 'none';
   }
   

 	
 	
 	
 	function callNewFunc(data) {
 		 	var actualData=data.split(":")
 		 	if(actualData[0]=="http"){ 
 		 		window.open(data, 'new', 'top = 150, left = 200, width = 750, height = 475, scrollbars = yes, status = yes, resizable = yes');
 		 	}
 		 	else{
 		 		window.open("http://"+data, 'new', 'top = 150, left = 200, width = 750, height = 475, scrollbars = yes, status = yes, resizable = yes');
 		 	}
	}
 	
 	
window.onload()
{
callDisplayOtherText(document.getElementById('paraFrm_category').value);
}
</script>

