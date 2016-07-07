<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="org.paradyne.lib.Utility,java.util.*"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/pages/mypage/myprocessManagerAlert.js"></script>
<link href="../pages/portal/images/skin1.css" rel="stylesheet"
	type="text/css" />

<%
	Object serviceMenuObj[][] = null;
	try {
		serviceMenuObj = (Object[][]) request
		.getAttribute("serviceMenulst");
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
<%
	int InboxCount = 0;
	int DraftCount = 0;
	int noOfItem = 0;
	try {
		InboxCount = (Integer) request.getAttribute("InboxCount");
		DraftCount = (Integer) request.getAttribute("DraftCount");
		DraftCount = (Integer) request.getAttribute("DraftCount");
		noOfItem = (Integer) request.getAttribute("noOfItem");
		// out.println("InboxCount" + InboxCount);
		// out.println("DraftCount" + DraftCount);
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
<s:form action="MypageProcessManagerAlerts" id="paraFrm"
	name="MypageForm" theme="simple">
	<s:hidden name="myPage" id="myPage" />
	<s:hidden id="oldAlertID" />
	<s:hidden id="newAlertID" />
	<s:hidden id="alertClose" value="Y" />
	<s:hidden name="dynamicStatus" id="dynamicStatus" />
	<s:hidden name="hiddenModuleName" />
	<s:hidden name="hiddenYear" />
	<s:hidden name="hiddenSearchMessgae" />
	<s:hidden name="hiddenisClickOn" />
	<s:hidden name="hiddensortOption" id="hiddensortOption" />
	<s:hidden name="checkItemCount" id="checkItemCount" />
	<s:hidden name="mypageStatus" />
	<%				
			HashMap alertsMap = (HashMap) request.getAttribute("alertsMap");		
	%>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="bottom" class="txt" width="100%">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="pageHeader">
				<tr>
					<td style="padding: 3px;"><strong class="text_head"><img
						src="../pages/mypage/images/icons/mymessages.png" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">My
					Messages </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" height="21" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="2%"><img src="../pages/mypage/images/inbox.gif" /></td>
					<td width="8%" class="emptext" nowrap="nowrap"><a href="#"
						title="Inbox" class="contlink"
						onclick="return callInboxOrDraft('Inbox');">&nbsp;<b>Inbox&nbsp;(<%=InboxCount%>)</b></a>&nbsp;</td>
					<td width="3%"><img src="../pages/mypage/images/draft.gif" /></td>
					<td width="87%" class="emptext" nowrap="nowrap"><a href="#"
						title="Draft" class="contlink"
						onclick="return callInboxOrDraft('Draft');">&nbsp;<b>Draft&nbsp;(<%=DraftCount%>)</b></a></td>
					<td colspan="1" height="20" nowrap="nowrap"><img
						align="absmiddle" src="../pages/mypage/images/info.png">
					Information Message</td>
					<td colspan="1" height="20" nowrap="nowrap">&nbsp;&nbsp;<img
						align="absmiddle" src="../pages/mypage/images/icons/action.png">
					Action Message</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr height="28">
			<td bgcolor="#6979AC">
			<%
			Object[][]moduleNameObj= (Object[][])request.getAttribute("moduleNameObj");
			%>
			<table width="97%" align="center" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="3%" nowrap="nowrap" align="left"><img
						src="../pages/mypage/images/mymessage.png" /></td>
					<td width="12%" nowrap="nowrap" class="mainheader">&nbsp;My
					Message</td>
					<td colspan="3" width="85%" class="mainheader" nowrap="nowrap"
						align="right"><s:select list="moduleMap" name="moduleName"
						onchange="viewStatus();callSearch();" cssStyle="width:70"></s:select>
					<s:select name="status" onchange="return callSearch();"
						list="statusMap" cssStyle="width:70;height:2;" /> <s:textfield
						onkeypress="setSearchFocus();"
						onfocus="clearText('paraFrm_searchMessageText','Search Message')"
						onblur="setText('paraFrm_searchMessageText','Search Message')"
						name="searchMessageText" size="20" /> <img
						onclick="return callSearch();" align="absmiddle"
						style="cursor: pointer;" src="../pages/mypage/images/search.gif" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="20%" class="formtxt"></td>
					<%
							int totalPage =  (Integer) request.getAttribute("totalPage");
							int pageNo =  (Integer) request.getAttribute("pageNo");
						%>
					<!-- Paging implementation -->
					<td id="showCtrl" width="80%" align="right"><s:if
						test="showPageFlag">
						<b>Page:</b>
						<a href="#"
							onclick="callPage('1', 'F', '<%=totalPage%>', 'MypageProcessManagerAlerts_input.action');">
						<img title="First Page" src="../pages/common/img/first.gif"
							width="10" height="10" class="iconImage" /> </a>&nbsp;
									<a href="#"
							onclick="callPage('P', 'P', '<%=totalPage%>', 'MypageProcessManagerAlerts_input.action');">
						<img title="Previous Page" src="../pages/common/img/previous.gif"
							width="10" height="10" class="iconImage" /> </a>
						<input type="text" name="pageNoField" id="pageNoField" size="3"
							value="<%=pageNo%>" maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'MypageProcessManagerAlerts_input.action');return numbersOnly();" />
									 of <%=totalPage%>
						<a href="#"
							onclick="callPage('N', 'N', '<%=totalPage%>', 'MypageProcessManagerAlerts_input.action')">
						<img title="Next Page" src="../pages/common/img/next.gif"
							class="iconImage" /> </a>&nbsp;
									<a href="#"
							onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'MypageProcessManagerAlerts_input.action');">
						<img title="Last Page" src="../pages/common/img/last.gif"
							width="10" height="10" class="iconImage" /> </a>
					</s:if></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="5">
			<table width="100%" align="center" border="0" cellpadding="0"
				bordercolor="red" cellspacing="0">
				<tr height="20">
					<td class="tableHeader" width="3%">&nbsp;</td>
					<td class="tableHeader" width="12%" align="left"
						style="cursor: pointer;" onclick="call('from');" title="From"><b>From&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img
						src="../pages/portal/images/arrow.png" width="9" height="5" alt="" /></b></td>
					<td class="tableHeader" width="55%" align="left"><b>Subject
					</b></td>
					<td class="tableHeader" width="15%" align="left"
						style="cursor: pointer;" onclick="call('received');"
						title="Received"><b>Received&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img
						src="../pages/portal/images/arrow.png" width="9" height="5" alt="" /></b></td>
					<td class="tableHeader" width="15%" align="left" title="Categories"
						onclick="call('module');" style="cursor: pointer;"><b>Categories&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img
						src="../pages/portal/images/arrow.png" width="9" height="5" alt="" /></b></td>
				</tr>
				<%						
				if (alertsMap != null && alertsMap.size() > 0) {
				Set keySet = null;
				Iterator itKeyList = null;
				keySet = alertsMap.keySet();
				itKeyList = keySet.iterator();
				while (itKeyList.hasNext()) {
					String key = (String) itKeyList.next();
					Vector vectAlert = (Vector) alertsMap.get(key);
				%>
				<%
						int approvalCnt = 1;
						if (vectAlert!=null  && vectAlert.size()> 0) {
							String day="Older";
							if(key.equals("1")){
								 day="Today";
					    	}else if(key.equals("2")){
								 day="Yesterday";
					    	}else if(key.equals("3")){
								 day="Last Week";
					    	}else if(key.equals("4")){
								 day="Last Month";
					    	}else if(key.equals("5")){
								 day="Older";
					    	}						
				%>
				<tr height="15">
					<td class="tableSortOrderCell" colspan="5"><b><%=day%></b></td>
				</tr>
				<% for (int i = 0; i < vectAlert.size(); i++){
			    	 Object[] alertMsg=(Object[])vectAlert.get(i);
			    	 String action = request.getContextPath()
						+ String.valueOf(alertMsg[9]);			    				    	 
			    	 %>
				<tr id="alert<%=0%>" height="25" onmouseover="newRowColor(this);"
					onmouseout="oldRowColor(this);">
					<td align="left" class="tableCell">
					<%
						if (String.valueOf(alertMsg[6]).equalsIgnoreCase("Alert")) {
					%><img src="../pages/mypage/images/icons/action.png"> <%
 					} else {
 					%> <img src="../pages/mypage/images/info.png"> <%
					}
					%>
					</td>
					<td class="tableCell" align="left"><%=String.valueOf(alertMsg[10])%></td>
					<td align="left"><a href="javascript:void(0)"
						title="Click to show Message"
						onclick="viewApplication('<%=action%>')" class="contlink"> <%=String.valueOf(alertMsg[1])%></a></td>
					<td class="tableCell" align="left"><%=String.valueOf(alertMsg[4])%></td>
					<td class="tableCell"><%=String.valueOf(alertMsg[5])%></td>
				</tr>
				<%}//end for %>
				<%}//end if %>

				<%
				}//end while
				}//end if
				else { %>
				<tr align="center" height="20">
					<td class="border" colspan="5" width="100%">No match found for
					your search.</td>
				</tr>
				<%} %>
				<tr bgcolor="#6979AC" height="25">
					<td class="mainheader" colspan="5" height="28">&nbsp;<strong>Item
					No :<%=noOfItem%> </strong></td>
				</tr> 
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script>

 setText();
function setText(){	
	if(document.getElementById('paraFrm_searchMessageText').value == '' ){
		document.getElementById('paraFrm_searchMessageText').value ='Search Message'; 
		document.getElementsByName('paraFrm_searchMessageText').value ='Search Message';
		document.getElementById('paraFrm_searchMessageText').style.color = '#ACACAC';
	}else if(document.getElementById('paraFrm_searchMessageText').value=='Search Message'){
			document.getElementById('paraFrm_searchMessageText').style.color = '#ACACAC';
		}
} 

function setSearchFocus(){		 
		if(window.event && window.event.keyCode == 13){
			callSearch();
		}	
}

function viewStatus(){
	try{
	// alert('In callStatus()');
   	//document.getElementById('showStatus').style.display = ''
	var moduleName  =document.getElementById('paraFrm_moduleName').value;	
	//alert(moduleName);
	document.getElementById('paraFrm_hiddenModuleName').value=moduleName;
 	//document.getElementById('paraFrm_hiddenModuleName').value='Leave';
 	//alert('In callStatus()'+document.getElementById('paraFrm_moduleName').value);
 	//alert("vish"+document.getElementById('paraFrm_status').value);
 	// alert("vish1"+document.getElementById('paraFrm_status').value);
  	displayModuleStatus('<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_displayModuleStatus.action?','MypageForm',moduleName);
   }
   catch(e){
   //alert("Error--------"+e);
   }	
}

function callInboxOrDraft(id){
	try{
		//document.getElementById('paraFrm_moduleName').value='';
		document.getElementById('paraFrm_status').value='';
		document.getElementById('paraFrm_hiddenModuleName').value='';
		document.getElementById('dynamicStatus').value='';
		document.getElementById('myPage').value='';	  
		if(id=="Inbox"){
			document.getElementById('checkItemCount').value='I';
		    document.getElementById('hiddensortOption').value='received';
		    document.getElementById('paraFrm').target = "_self";
	 	    document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action?isClickOn=Input&hiddenMypageStatus=Inbox';
		    document.getElementById('paraFrm').submit();
		    return false;
		}		
		if(id=="Draft"){
			document.getElementById('checkItemCount').value='D';
			document.getElementById('hiddensortOption').value='received';
			document.getElementById('paraFrm').target = "_self";
	 		document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action?isClickOn=Input&hiddenMypageStatus=NotInbox';
			document.getElementById('paraFrm').submit();
			return false;
		}
	}catch(e){ alert("Error---e--  "+e);}
}

function callSearch(){
     //alert("In callSearch----------------");
	 // alert("id---------"+id);
	 //
	 document.getElementById('dynamicStatus').value=document.getElementById('paraFrm_status').value;
	 //document.getElementById('sortOption').value=id;
	 try{
		var moduleNamestr =document.getElementById('paraFrm_moduleName').value;  
		var statusstr ='';	
		var filterStatus =document.getElementById('dynamicStatus').value;	
		//alert("statusstr   "+statusstr);
		var yearstr ='';
		//document.getElementById('paraFrm_mypageyear').value;
		var searchMessagestr =document.getElementById('paraFrm_searchMessageText').value;	 
	 	if(searchMessagestr=="Search Message"){
	 			searchMessagestr="";
	 	}	 
		document.getElementById('paraFrm_hiddenisClickOn').value='Input';
		var isClickOn =document.getElementById('paraFrm_hiddenisClickOn').value;
		//alert("In moduleName----------------"+moduleNamestr);
		//alert("In status----------------"+statusstr);
		// alert("In year----------------"+yearstr);
		//alert("In searchMessage----------------"+searchMessagestr);
		//alert("In isClickOn----------------"+isClickOn);
		var  ItemCountNo =document.getElementById('checkItemCount').value;
		if(document.getElementById('checkItemCount').value=='I'){
				statusstr="Inbox";
	    }else{
			statusstr="NotInbox";
		}
		document.getElementById('paraFrm').action= 'MypageProcessManagerAlerts_applyFilter.action?moduleName='+moduleNamestr+'&hiddenMypageStatus='+statusstr+'&year='+yearstr+'&searchMessage='+searchMessagestr+'&isClickOn='+isClickOn+'&sortOption=received&ItemCount='+ItemCountNo+'&filterStatus='+filterStatus;
		// alert("Action:---"+document.getElementById('paraFrm').action);
		document.getElementById("paraFrm").target="_self";
		document.getElementById('paraFrm').submit();
	  }
	  catch(e){
		//alert("Error ----- "+e);
	}		
}

function callSearch_NEW(){
	// alert("In callSearch----------------");	
	try{
		var moduleNamestr =''; 
		var statusstr ='';	
		var filterStatus ='';	
		//alert("filterStatus -------------  "+filterStatus);	
		//alert("statusstr   "+statusstr);
		var yearstr ='';
		//document.getElementById('paraFrm_mypageyear').value;
		var searchMessagestr =document.getElementById('paraFrm_searchMessageText').value;
		document.getElementById('paraFrm_hiddenisClickOn').value='Input';
		var isClickOn =document.getElementById('paraFrm_hiddenisClickOn').value;
		//alert("In moduleName----------------"+moduleNamestr);
		//alert("In status----------------"+statusstr);
		// alert("In year----------------"+yearstr);
		//alert("In searchMessage----------------"+searchMessagestr);
		//alert("In isClickOn----------------"+isClickOn);
		var  ItemCountNo =document.getElementById('checkItemCount').value;	
		if(document.getElementById('checkItemCount').value=='I'){
			statusstr="Inbox";
		}
		else{
			statusstr="NotInbox";
		}
		document.getElementById('paraFrm').action= 'MypageProcessManagerAlerts_applyFilter.action?moduleName='+moduleNamestr+'&hiddenMypageStatus='+statusstr+'&year='+yearstr+'&searchMessage='+searchMessagestr+'&isClickOn='+isClickOn+'&sortOption=received&ItemCount='+ItemCountNo+'&filterStatus='+filterStatus;
		//alert("Action:---"+document.getElementById('paraFrm').action);
		document.getElementById("paraFrm").target="_self";
		document.getElementById('paraFrm').submit();
	}catch(e){
		alert("Error ----- "+e);
	}		
}

function call(id){
	// alert("In call----------------");
	//  alert("id---------"+id);
	document.getElementById('hiddensortOption').value=id;
	try{
		var moduleNamestr ='';
		//document.getElementById('paraFrm_moduleName').value;
		//var statusstr =document.getElementById('paraFrm_status').value;	
		//alert("statusstr   "+statusstr);
		var yearstr ='';
		//document.getElementById('paraFrm_mypageyear').value;
		var searchMessagestr =document.getElementById('paraFrm_searchMessageText').value;
		if(searchMessagestr=="Search Message"){
			searchMessagestr="";
		}	
		document.getElementById('paraFrm_hiddenisClickOn').value='Input';
		var isClickOn ='Input';
		//document.getElementById('paraFrm_hiddenisClickOn').value;
		//alert("In moduleName----------------"+moduleNamestr);
		//alert("In status----------------"+statusstr);
		//alert("In year----------------"+yearstr);
		//alert("In searchMessage----------------"+searchMessagestr);	
		//alert("In isClickOn----------------"+isClickOn);
		var  ItemCountNo =document.getElementById('checkItemCount').value;
		var statusstr;
		if(ItemCountNo=='D'){
			statusstr ='NotInbox';
		}
		if(ItemCountNo=='I'){
			statusstr ='Inbox';
		}
		document.getElementById('paraFrm').action= 'MypageProcessManagerAlerts_applyFilter.action?moduleName='+moduleNamestr+'&hiddenMypageStatus='+statusstr+'&year='+yearstr+'&searchMessage='+searchMessagestr+'&isClickOn='+isClickOn+'&sortOption='+id+'&ItemCount='+ItemCountNo;	
		//alert("Action:---"+document.getElementById('paraFrm').action);
		document.getElementById("paraFrm").target="_self";
		document.getElementById('paraFrm').submit();
	 }catch(e){
		//alert("Error ------- "+e);		 
	}	
}

   function callOnload(){
	try{
		var selectedmodule = document.getElementById('paraFrm_hiddenModuleName').value;	 
		if(selectedmodule==""){	
	   		document.getElementById('paraFrm_moduleName').options[0].text='--Select--';
	   		document.getElementById('paraFrm_moduleName').options[0].value='--Select--';
		}else{
	 		document.getElementById('paraFrm_moduleName').options[0].text=selectedmodule;
	 		document.getElementById('paraFrm_moduleName').options[0].value=selectedmodule;	 
	    }
 	}catch(e){alert('onload error'+e);}
  }
 
 
  function viewApplication(action){
	   //  alert("In my code:"+action);
	   try{
			document.getElementById('paraFrm').action= action;
			document.getElementById("paraFrm").target="_self";
			document.getElementById('paraFrm').submit();
	   }catch(e){
		//alert("Exception:"+e);
	   }	
    }
</script>