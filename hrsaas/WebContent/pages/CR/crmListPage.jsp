<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp"%>

<STYLE type=text/css>

a:hover{
COLOR:#FF0000;
text-decoration:underline;
}


</STYLE>
<script type="text/javascript" src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript">
	var records = new Array();
</script>
<%
	String userType=(String)session.getAttribute("userType");
	
%>
<s:form action="PerformanceMetrics" id="paraFrm" validate="true" theme="simple" target="main">
<s:hidden name="hiddenSearchMessage" />
<s:hidden name="myPage" id="myPage" />
<s:hidden name="show" />
<s:hidden id ="dashBoardID" name="dashBoardID"/>
<s:hidden id="screenWidth" name="screenWidth"/>
<s:hidden id="dashBoardName" name="dashBoardName"/>



	<table width="100%" align="right" class="formbg" >
		<tr>
			<td valign="bottom" class="txt">
				<table width="100%" align="right" class="formbg">
					<tr>
						<td>
							<strong class="text_head">
								<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" />
							</strong>
						</td>
						<td width="93%" class="txt"><strong class="text_head"><s:property value="dashBoardName"/> Account List</strong></td>
						<td width="3%" valign="top" class="txt">
							<div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	
	
	<tr height="28">
			<td bgcolor="#6979AC">

			<table width="97%" align="center" border="0" cellpadding="0"
				cellspacing="0">
				
				<tr>
					<td width="3%" nowrap="nowrap" align="left"><input type="button" class="reset"
				value=" Back " onclick="return backFun();" /></td>
					<td width="12%" nowrap="nowrap" class="mainheader">&nbsp;</td>
					<td colspan="3" width="85%" class="mainheader" nowrap="nowrap"
						align="right" id="ctrlShow"><!--<s:select
						name="reportType" id="paraFrm_reportType"
						list="#{'':'--Select--','I':'Account ID','N':'Account Name'}" /> </select>
					 --><s:textfield onkeypress="setSearchFocus(event);" value="%{hiddenSearchMessage}"
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
				<table class="formbg" width="100%" cellspacing="1" cellspadding="0"	border="0">
				<tr>
					<td>
						<strong class="text_head"><s:property value="dashBoardName"/> Account List  </strong>
					</td>
					<td width="70%" align="right">
						<%
							int totalPage = 0;
							int pageNo = 0;
						%>
							<s:if test="listLength">
								<table>
								<tr>
								
									<td id="ctrlShow" width="100%" align="right">
									Show Only Parent 
									<input type="checkbox" name="parentFlagHiddenChk" value="parentFlagHiddenChk"  id="ShowParentckbx" onclick="callForParent();">
									<s:hidden name="parentFlagHiddenChk" />
									<b>Page:</b>
										 
									<%
									totalPage = (Integer) request.getAttribute("totalPage");
									pageNo = (Integer) request.getAttribute("pageNo");
									%> <a href="#"
										onclick="callPage('1', 'F', '<%=totalPage%>', 'PerformanceMetrics_input.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPage('P', 'P', '<%=totalPage%>', 'PerformanceMetrics_input.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text" name="pageNo"
										id="pageNo" size="3" value="<%=pageNo%>" maxlength="10"
										onkeypress="callPageText(event, '<%=totalPage%>', 'PerformanceMetrics_input.action');return numbersOnly();" />
									of <%=totalPage%> <a href="#"
										onclick="callPage('N', 'N', '<%=totalPage%>', 'PerformanceMetrics_input.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'PerformanceMetrics_input.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
								</tr>
								</table>
							</s:if>
					</td>
				</tr>
				
							
				<tr>
					<td colspan="2">
						<table width="100%" border="0"  class="formbg">
							<tr>
								<td class="formtext">
								<table width="100%" border="0" id="parentTableId">
									<tr>
									<td  colspan="1" class="formth"><label
										class="set" id="srno" name="srno"
										ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></td>
									<td  colspan="1" class="formth"><label
										class="set" id="acc.id" name="acc.id"
										ondblclick="callShowDiv(this);"><%=label.get("acc.id")%></label></td>
									<td  colspan="1" class="formth"><label
										class="set" id="business.name" name="business.name" 
										ondblclick="callShowDiv(this);"><%=label.get("business.name")%></label></td>
									
									<%if(!userType.equals("E")){ %>
									<td  colspan="1" class="formth"><label
										class="set" id="performance.metrics" name="performance.metrics"
										ondblclick="callShowDiv(this);"><%=label.get("performance.metrics")%></label></td>
									<td  colspan="1" class="formth"><label
										class="set" id="escalations" name="escalations"
										ondblclick="callShowDiv(this);"><%=label.get("escalations")%></label></td>
									<td  colspan="1" class="formth"><label
										class="set" id="planned" name="planned"
										ondblclick="callShowDiv(this);"><%=label.get("planned")%></label></td>
									<td  colspan="1" class="formth"><label
										class="set" id="data.reconcilation" name="data.reconcilation"
										ondblclick="callShowDiv(this);"><%=label.get("data.reconcilation")%></label></td>
									<%} %>
									<td  colspan="1" class="formth"><label
										class="set" id="View.Account" name="View.Account"
										ondblclick="callShowDiv(this);"><%=label.get("View.Account")%></label></td>
									<!--<td width="10%" colspan="1" class="formth"><label
										class="set" id="Last.Modified" name="Last.Modified"
										ondblclick="callShowDiv(this);"><%=label.get("Last.Modified")%></label></td>

								--></tr>

									<s:if test="listLength">
									<%
										int count2 = 0;
									%>
									<%!int d2 = 0;%>
									<%
										int i2 = pageNo * 20 - 20;		
									%>

									<s:iterator value="iteratorList"> 
									
										<s:hidden name="parentFlag" /> 
									
									<s:if test="parentFlag=='YY'">
										<tr  bgcolor="#C5BE97" id="parentRow"> 
											
											<td align="center" class="sortableTD"><%=++i2%></td>
										
											<td class="sortableTD" align="left"> 
												<s:hidden name="accountCode" />
												
												<s:property value="accountId"/>
											</td> 
											
											<td class="sortableTD" align="left"><s:property value="accountName"/></td>
											<%if(!userType.equals("E")){ %>
											<td align="center">
												<a href="#?" class="link"  onclick="javascript:callForPerformanceMetrics('<s:property value="accountCode"/>');">Manage </a>
											</td>
											<td align="center">
												<a href="#?" class="link"  onclick="javascript:callForEscalations('<s:property value="accountCode"/>');">Manage </a>
											</td>
											<td align="center">
												<a href="#?" class="link"  onclick="javascript:callForPlan('<s:property value="accountCode"/>');">Manage </a>
											</td>
											<td align="center">
												<a href="#?" class="link"  onclick="javascript:callForDataReconcilation('<s:property value="accountCode"/>');">Manage </a>
											</td>
											<%} %>
											<td align="center">
												<a href="#?" class="link"  onclick="javascript:callAction('<s:property value="accountCode"/>','<s:property value="accountId"/>','<s:property value="accountName"/>');">Manage </a>
											</td>
											<!--<td align="center">
											
											</td>
										
										--></tr>
									</s:if>
									<s:else>
									<tr >
											
											<td align="center" class="sortableTD"><%=++i2%></td>
										
											<td class="sortableTD" align="left"> 
												<s:hidden name="accountCode" />
												
												<s:property value="accountId"/>
											</td> 

											<td class="sortableTD" align="left"><s:property value="accountName"/></td>
											<%if(!userType.equals("E")){ %>	
											<td align="center">
												<a href="#?" class="link"  onclick="javascript:callForPerformanceMetrics('<s:property value="accountCode"/>');">Manage </a>
											</td>
											<td align="center">
												<a href="#?" class="link"  onclick="javascript:callForEscalations('<s:property value="accountCode"/>');">Manage </a>
											</td>
											<td align="center">
												<a href="#?" class="link"  onclick="javascript:callForPlan('<s:property value="accountCode"/>');">Manage </a>
											</td>
											<td align="center">
												<a href="#?" class="link"  onclick="javascript:callForDataReconcilation('<s:property value="accountCode"/>');">Manage </a>
											</td>
											<%} %>
											<td align="center">
												<a href="#?" class="link" onclick="javascript:callAction('<s:property value="accountCode"/>','<s:property value="accountId"/>','<s:property value="accountName"/>');">Manage </a>
											</td>
											<!--<td align="center">
											
											</td>
										
										--></tr>
									</s:else>
										
									</s:iterator>

									<%
									
									count2++;
										d2 = i2;
									%>
								</s:if>
							
								<s:else>
									<td align="center" colspan="6" nowrap="nowrap">
										<font color="red" >There is no data to display</font>
									</td>
								</s:else>
							</table>
						 </td>
						</tr>					
				     </table>
				   </td>
			    </tr>
			</table>
		   </td>
		</tr>
		<tr style="height: 600px">
		<td >
		
		</td>
		</tr>			
		
	</table>
	
</s:form>


<script>
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'D1AnalyticHome_input.action';
		document.getElementById('paraFrm').submit();
	}
	
  	function callForEdit(accountId){
 /// alert(accountId);
	 
	   	document.getElementById("paraFrm").action="PerformanceMetrics_calforedit.action?accountId="+accountId;
	   //	document.getElementById("paraFrm").target="main";
	   	document.getElementById('paraFrm').target = "_self";
	    document.getElementById("paraFrm").submit();
   }
   
    function callForPerformanceMetrics(accountId){
    	//// alert(accountId);
	   	document.getElementById("paraFrm").action="PerformanceMetrics_callForPerformanceMetrics.action?accountId="+accountId;
	   //	document.getElementById("paraFrm").target="main";
	   	document.getElementById('paraFrm').target = "_self";
	    document.getElementById("paraFrm").submit();
    }
    
    function callForEscalations(accountId){
    	/// alert(accountId);
	   	document.getElementById("paraFrm").action="PerformanceMetrics_callForEscalations.action?accountId="+accountId;
	   //	document.getElementById("paraFrm").target="main";
	   	document.getElementById('paraFrm').target = "_self";
	    document.getElementById("paraFrm").submit();
    }
     
     function callForPlan(accountId){
    	/// alert(accountId);
	   	document.getElementById("paraFrm").action="PerformanceMetrics_callForPlan.action?accountId="+accountId;
	   //	document.getElementById("paraFrm").target="main";
	   	document.getElementById('paraFrm').target = "_self";
	    document.getElementById("paraFrm").submit();
    }
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
	
	//changes By Vijay
	  function callForViewAccount(accountId,DashBoradAccountName){
     	
 	   	//document.getElementById("paraFrm").action='<%=request.getContextPath()%>/cr/CRMAccountInfo_getReportList.action?requestAccountCode='+accountId;
 	   	var dashBoardID=document.getElementById('dashBoardID').value;
 	   	document.getElementById("paraFrm").action='<%=request.getContextPath()%>/cr/DashBoard_input.action?requestAccountCode='+accountId+'&dashBoardId='+dashBoardID+'&DashBoradAccountName='+DashBoradAccountName;
 	   	document.getElementById("paraFrm").target="main";
 	   //	document.getElementById('paraFrm').target = "_self";
 	    document.getElementById("paraFrm").submit();
     }
     
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
	function setSearchFocus(e)
	{
		
		 try{
		if(e.keyCode == 13)
		{
		
	callSearch();
		}
		}
		catch(e){
		alert(e);
		}
	
	}
	function callSearch(){
	try{
		var searchMessagestr =document.getElementById('paraFrm_searchMessageText').value;
		 if(searchMessagestr=="Search Message")
		 {
		 searchMessagestr="";
		 }
		document.getElementById('paraFrm').action= 'PerformanceMetrics_applyFilter.action?searchMessage='+searchMessagestr;
		// alert("Action:---"+document.getElementById('paraFrm').action);
			document.getElementById("paraFrm").target="_self";
			document.getElementById('paraFrm').submit();
		}catch(e){
			//alert("Error ----- "+e);
		}	
	}
	
     
     function callForDataReconcilation(accountId){
    	
	   	document.getElementById("paraFrm").action="PerformanceMetrics_callForDataReconcilation.action?accountId="+accountId;
	   //	document.getElementById("paraFrm").target="main";
	   	document.getElementById('paraFrm').target = "_self";
	    document.getElementById("paraFrm").submit();
    }
    //call to DashBorad ---By Vijay
    function callAction(AccountID,accountName,dashBoardAccountName){  
   
	var dashBoardID=document.getElementById('dashBoardID').value;
	
	try{
		document.getElementById('paraFrm').target = "_self";
	 	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>'+"/cr/DashBoard_input.action?dashBoardID="+dashBoardID+"&accountID="+AccountID+"&accountName="+accountName+"&dashBoardAccountName="+dashBoardAccountName;
		document.getElementById('paraFrm').submit();
		return true;
		
	}
	catch(e){
	alert(e);		 
	}	 	
		return true;
}
setChekbx();
function setChekbx(){
var parentFlagHiddenChk=document.getElementById('paraFrm_parentFlagHiddenChk').value;

if(parentFlagHiddenChk=="true"){

document.getElementById('ShowParentckbx').checked=true;
}
else{

document.getElementById('ShowParentckbx').checked=false;
}
}

function callForParent(){
try{
var parentflag=document.getElementById('ShowParentckbx').checked;

var parentFlagHiddenChk=document.getElementById('paraFrm_parentFlagHiddenChk').value;
	document.getElementById("paraFrm").action="PerformanceMetrics_callForParentAccouts?parentFlag="+parentflag;
	   //	document.getElementById("paraFrm").target="main";
	   	document.getElementById('paraFrm').target = "_self";
	    document.getElementById("paraFrm").submit();
	    }
	    catch(e){
	    alert(e);
	    }

  }  
</script>



