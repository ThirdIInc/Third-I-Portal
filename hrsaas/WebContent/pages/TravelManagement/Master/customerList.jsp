<!-- Added by manish sakpal -->

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"> var records = new Array(); </script>
<s:form action="CustomerMaster" validate="true" id="paraFrm" validate="true" theme="simple">	
	
	<s:hidden name="myPage" id="myPage" />
	<s:hidden name="customerName" />
	<s:hidden name="contactPerson" />	
	<table width="100%" border="0" align="right" cellpadding="2" cellspacing="1" class="formbg">
		<tr>
			<td valign="bottom" class="txt">			
			<s:hidden name="customercode" /> 			
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td>
						<strong class="text_head"> 
							<img src="../pages/images/recruitment/review_shared.gif" width="25"	height="25" /> 
						</strong>
					</td>
					<td width="93%" class="txt">
						<strong class="text_head">Customer List </strong>
					</td>
					<td width="3%" valign="top" class="txt">
						<div align="right">
							<img src="../pages/images/recruitment/help.gif" width="16" height="16" />
						</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>


		<tr>
			<td>
			<table width="100%">
				<td>
					<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
				</td>
				<%
					int totalPage = 0;
					int pageNo = 0;
				%>
				<s:if test="modeLength">
				
				<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
					<%
						totalPage = (Integer) request.getAttribute("totalPage");
						pageNo = (Integer) request.getAttribute("pageNo");
					%> 
					<a href="#"	onclick="callPage('1', 'F', '<%=totalPage%>', 'CustomerMaster_callPage.action');">
						<img title="First Page" src="../pages/common/img/first.gif"	width="10" height="10" class="iconImage" /> 
					</a>&nbsp; 
					<a href="#"	onclick="callPage('P', 'P', '<%=totalPage%>', 'CustomerMaster_callPage.action');">
						<img title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage" />
					</a> 
					<input type="text" name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"	maxlength="10" onkeypress="callPageText(event, '<%=totalPage%>', 'CustomerMaster_callPage.action');return numbersOnly();" />
						of <%=totalPage%> 
					<a href="#"	onclick="callPage('N', 'N', '<%=totalPage%>', 'CustomerMaster_callPage.action')">
						<img title="Next Page" src="../pages/common/img/next.gif" class="iconImage" /> 
					</a>&nbsp; 
					<a href="#" onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'CustomerMaster_callPage.action');">
						<img title="Last Page" src="../pages/common/img/last.gif" width="10" height="10" class="iconImage" /> 
					</a>
				</td>
				</s:if>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" width="100%">
				<table class="formbg" width="100%" cellspacing="1" cellspadding="0"	border="0">
				 <tr>
					<td colspan="2" class="txt" width="85%">
						<strong class="text_head"> Customer List </strong>
					</td>
					<td>
						<s:if test="modeLength">
							<input type="button" id="ctrlShow" class="delete" value=" Delete" onclick="return chkDelete();" />
						</s:if> 
						<s:else>						
						</s:else>
					</td>
				</tr>
				
				<tr>
					<td colspan="8">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
						<td class="formtext">
							<table width="100%" border="0">
								<tr>								
									<td width="5%" class="formth" align="center">
										<label class="set" name="serial.no" id="serial.no" ondblclick="callShowDiv(this);"> <%=label.get("serial.no")%></label>
									</td>

									<td class="formth" align="center" width="15%">
										<label class="set" name="customer.name" id="customer.name" ondblclick="callShowDiv(this);"> <%=label.get("customer.name")%></label>
									</td>

									<td class="formth" align="center" width="15%">
										<label	class="set" name="contact.person" id="contact.person"	ondblclick="callShowDiv(this);"> <%=label.get("contact.person")%></label>
									</td>						

									<td class="formth" align="center" width="15%">
										<label class="set" name="customer.city" id="customer.city" ondblclick="callShowDiv(this);"> <%=label.get("customer.city")%></label>
									</td>	

									<s:if test="modeLength">
										<td align="right" class="formth" id="ctrlShow" nowrap="nowrap"	width="15%">
											<input type="checkbox" id="selAll" style="cursor: hand;" title="Select all records to remove" onclick="selectAllRecords(this);" />
										</td>
									</s:if>
								</tr>

								<s:if test="modeLength">
								<%
								int count = 0;
								%>
								<%!int d = 0;%>
								<%
									int i = 0;
									int cn = pageNo * 20 - 20;
								%>

								<s:iterator value="customerList">


									<tr <%if(count%2==0){
									%> class="tableCell1"
										<%}else{%> class="tableCell2" <%	}count++; %>
										onmouseover="javascript:newRowColor(this);"
										title="Double click for edit"
										onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
										ondblclick="javascript:callForEdit('<s:property value="customercode"/>');">
										<td align="center" class="sortableTD"><%=++i%> 
											
										</td>
										
										<s:hidden value="%{customercode}" id='<%="customercode" + i%>' />
										<script type="text/javascript">records[<%=i%>] = document.getElementById('customercode' + '<%=i%>').value;</script>


										<td align="left" class="sortableTD">&nbsp;
											<s:property	value="customerName" />	
											<input type="hidden" name="customerdeleteCode" id="customerdeleteCode<%=i%>" />										
										</td>

										<td align="left" class="sortableTD">&nbsp;
											<s:property value="contactPerson" />
										</td>										

										<td align="left" class="sortableTD">&nbsp;
											<s:property	value="city" />
										</td>
								
										<td align="center" nowrap="nowrap"	class="sortableTD">																				
											<input type="checkbox" class="checkbox" id="confChk<%=i%>" name="confChk" onclick="callForDelete('<s:property value="customercode"/>','<%=i%>')" />
										</td>
									</tr>
								</s:iterator>

								<%
								d = i;
								%>
							</s:if>
							
							<s:else>
								<td align="center" colspan="4" nowrap="nowrap">
									<font color="red" ><b>There is no data to display</b></font>
								</td>
							</s:else>

						</table>
					 </td>
				  </table>
				</td>
			   </tr>
			  </table>
	        </td>
		 </tr>

		
		<tr>
			<td width="100%">
			<table border="0" width="100%">
				<td width="75%">
					<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
				</td>

				<td width="25%" align="Right">
					<s:if test="modeLength"><b>Total No. Of Records:</b>&nbsp;<s:property value="totalRecords" /></s:if>
					<s:else>	</s:else>
				</td>

			</table>
			</td>
		</tr>
	</table>
</s:form>


<script>

function addnewFun()
{
  	    document.getElementById('paraFrm').target = "_self";
   	    document.getElementById('paraFrm').action="CustomerMaster_addNew.action";
	    document.getElementById('paraFrm').submit();	   
}
  	
function searchFun()
{
	if(navigator.appName == 'Netscape') 
	{
	  var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
	} 
	else 
	{
	  var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
	}
			
	document.getElementById("paraFrm").target = 'myWin';
	document.getElementById("paraFrm").action ='CustomerMaster_f9searchCustomer.action';
	document.getElementById("paraFrm").submit();
}



function callForDelete(id,i)
{	
	 if(document.getElementById('confChk'+i).checked == true)
	   {	  
	    	document.getElementById('customerdeleteCode'+i).value=id;
	    	
	   }
	   else
	   {
	   		document.getElementById('customerdeleteCode'+i).value="";
   		}
}

	
function chkDelete()
{	 
	 if(chk())
	 {	
	 	var con=confirm('Do you want to delete the record(s) ?');
	 	if(con)
	 	{
	   		document.getElementById('paraFrm').action="CustomerMaster_deleteChkRecords.action";
	   		document.getElementById('paraFrm').target = "_self";
	    	document.getElementById('paraFrm').submit();
	    }
	    else
	    { 
			document.getElementById('selAll').checked = false;
	    	var flag='<%=d %>';
	  		for(var a=1;a<=flag;a++)
	  		{	
				document.getElementById('confChk'+a).checked = false;
				document.getElementById('customerdeleteCode'+a).value="";	
			}
	     return false;
	 	}
	 }
	 else 
	 {
	 	alert('please select atleast one record');
	 	return true ;
	 }	
}



function chk()
{   
	 var flag='<%=d %>';
	  for(var a=1;a<=flag;a++)
	  {	
	   	if(document.getElementById('confChk'+a).checked == true)
	   	{	
	  		// alert('11');  
	    	return true;
	   	}	   
	  }
	  return false;
}

	
function callForEdit(id)
{	
	callButton('NA', 'Y', 2);
  	
	document.getElementById("paraFrm").action="CustomerMaster_calforedit.action?id="+id;
	document.getElementById('paraFrm').target = "_self";
	document.getElementById("paraFrm").submit();
}


function selectAllRecords(object) 
{
	if(object.checked) 
	{
		for(var i = 1; i <= records.length; i++) 
		{
				document.getElementById('confChk' + i).checked = true;
				document.getElementById('customerdeleteCode' + i).value = records[i];
		}
	}
	else 
	{
		for(var i = 1; i <= records.length; i++) 
		{
			document.getElementById('confChk' + i).checked = false;
			document.getElementById('customerdeleteCode' + i).value = "";
		}
	}
}

</script>



