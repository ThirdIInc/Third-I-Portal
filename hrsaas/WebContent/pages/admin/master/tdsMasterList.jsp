<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="TDSMaster" validate="true" id="paraFrm" theme="simple">
<s:hidden name="myPage" id="myPage" />
	<s:hidden name="hiddenCode" />
	<s:hidden name="fromYear" /><s:hidden name="toYear" /><s:hidden name="tdsDate" />
	<s:hidden name="tdsDebit" /><s:hidden name="tdsDebitCode" /><s:hidden name="surcharge" />
	<s:hidden name="eduCess" />
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
					<td width="93%" class="txt"><strong class="text_head">TDS
					Parameter List
					<s:hidden name="tdsCode"/>
					 </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
<tr>
			<td width="70%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
		<tr>
			<td>
				<table class="formbg" width="100%" cellspacing="1" cellspadding="0"	border="0">
				<tr>
					<td width="30%" align="left">
						<strong class="text_head"> TDS Parameter List </strong>
					</td>
					<td width="70%" align="right">
						<%
							int totalDraftPage = 0;
							int draftPageNo = 0;
						%>
							<s:if test="aaa">
								<table>
								<tr>
									<td id="ctrlShow" width="100%" align="right"><b>Page:</b>
									<%
									totalDraftPage = (Integer) request.getAttribute("totalDraftPage");
									draftPageNo = (Integer) request.getAttribute("draftPageNo");
									%> <a href="#"
										onclick="callPage('1', 'F', '<%=totalDraftPage%>', 'TDSMaster_input.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPage('P', 'P', '<%=totalDraftPage%>', 'TDSMaster_input.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text" name="pageNo"
										id="pageNo" size="3" value="<%=draftPageNo%>" maxlength="10"
										onkeypress="callPageText(event, '<%=totalDraftPage%>', 'TDSMaster_input.action');return numbersOnly();" />
									of <%=totalDraftPage%> <a href="#"
										onclick="callPage('N', 'N', '<%=totalDraftPage%>', 'TDSMaster_input.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPage('<%=totalDraftPage%>', 'L', '<%=totalDraftPage%>', 'TDSMaster_input.action');">
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
								<table width="100%" border="0">
									<tr>
									<td width="10%" colspan="1" class="formth"><label
										class="set" id="taxation.srlNo" name="taxation.srlNo"
										ondblclick="callShowDiv(this);"><%=label.get("taxation.srlNo")%></label></td>
									<td width="25%" colspan="1" class="formth"><label
										class="set" id="fromyear1" name="fromyear"
										ondblclick="callShowDiv(this);"><%=label.get("fromyear")%></label></td>
									<td width="30%" colspan="1" class="formth"><label
										class="set" id="toyear" name="toyear"
										ondblclick="callShowDiv(this);"><%=label.get("toyear")%></label></td>
									<td width="20%" colspan="1" class="formth"><label
										class="set" id="taxation.TdsEffDate" name="taxation.TdsEffDate"
										ondblclick="callShowDiv(this);"><%=label.get("taxation.TdsEffDate")%></label></td>
									<!--<td width="20%" colspan="1" class="formth"><label
										class="set" id="course.namee" name="course.name"
										ondblclick="callShowDiv(this);"><%=label.get("course.name")%></label></td>

									--><td width="20%" colspan="1" class="formth"><label
										class="set" id="taxation.TDSDebtType" name="taxation.TDSDebtType"
										ondblclick="callShowDiv(this);"><%=label.get("taxation.TDSDebtType")%></label></td>

								</tr>

									<s:if test="draftVoucherListLength">
									<%
										int count = 0;
									%>
									<%!int d = 0;%>
									<%
										int i = 0;									
									%>

									<s:iterator value="tdsIteratorList">
											<tr <%if(count%2==0){
									%> class="tableCell1" <%}else{%>
								class="tableCell2" <%	}count++; %>
								onmouseover="javascript:newRowColor(this);"
								title="Double click for edit"
								onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
								ondblclick="javascript:callForEdit('<s:property value="itttdsCode" />');">
										
											<s:hidden
												name="itttdsCode" />
											<td align="center" class="sortableTD"><%=++i%></td>
											
											<td class="sortableTD" align="left"><s:property value="fromYear"/></td>

											<td class="sortableTD" align="left"><s:property value="toYear"/></td>


											<td class="sortableTD" align="center"><s:property value="tdsDate"/></td>
											
											<td class="sortableTD" align="center"><s:property value="tdsDebit"/>
											
											</td>
											
											
				
																				
										</tr>
									</s:iterator>

									<%
										d = i;
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


		<tr>
			<td width="70%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
	



</s:form>
<script>
	function addnewFun() {
		
		document.getElementById('paraFrm').target = "_self";
		document.getElementById("paraFrm").action="TDSMaster_addNew.action";
		document.getElementById("paraFrm").submit();
	}
	
	
	function searchFun() {
	
		if(navigator.appName == 'Netscape') {
			var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
		} else {
			var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		}
		
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action="TDSMaster_f9tdsSearch.action";
		document.getElementById("paraFrm").submit();
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
	function callForEdit(id){
	//alert(id);
	  	document.getElementById('paraFrm_hiddenCode').value=id;
	   	document.getElementById("paraFrm").action="TDSMaster_calforedit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
   }
</script>