<!-- Added by manish sakpal -->

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"> var records = new Array(); </script>
<s:form action="DisputeManagementSystem" validate="true" id="paraFrm" validate="true" theme="simple">	
	<s:hidden name="disputeRaisedBy"/>
	<s:hidden name="underAct"/>
	<table width="100%" border="0" align="right" cellpadding="2" cellspacing="1" class="formbg">
		<tr>
			<s:hidden name="disputeID" /> 
			<td valign="bottom" class="txt">						
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td>
						<strong class="text_head"> 
							<img src="../pages/images/recruitment/review_shared.gif" width="25"	height="25" /> 
						</strong>
					</td>
					<td width="93%" class="txt">
						<strong class="text_head">Dispute Management System</strong>
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
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" width="100%">
				<table class="formbg" width="100%" cellspacing="1" cellspadding="0"	border="0">
				 <tr>
					<td colspan="2" class="txt" width="85%">
						<strong class="text_head"> Dispute Management System List </strong>
					</td>
					<td>
						&nbsp;
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

									<td class="formth" align="center" width="20%">
										<label class="set" name="disputeRaisedBy" id="disputeRaisedBy" ondblclick="callShowDiv(this);"> <%=label.get("disputeRaisedBy")%></label>
									</td>

									<td class="formth" align="center" width="20%">
										<label	class="set" name="typeOfDispute" id="typeOfDispute"	ondblclick="callShowDiv(this);"> <%=label.get("typeOfDispute")%></label>
									</td>						

									<td class="formth" align="center" width="20%">
										<label class="set" name="disputeStatement" id="disputeStatement" ondblclick="callShowDiv(this);"> <%=label.get("disputeStatement")%></label>
									</td>	
									
									<td class="formth" align="center" width="15%">
										<label class="set" name="underAct" id="underAct" ondblclick="callShowDiv(this);"> <%=label.get("underAct")%></label>
									</td>
									
									<td class="formth" align="center" width="10%">
										<label class="set" name="loggedOnDateLabel" id="loggedOnDateLabel" ondblclick="callShowDiv(this);"> <%=label.get("loggedOnDateLabel")%></label>
									</td>
								</tr>

								<s:if test="modeLength">
								<%
								int count = 0;
								%>
								<%!int d = 0;%>
								<%
									int i = 0;									
								%>

								<s:iterator value="disputeManagementList">
									<tr <%if(count%2==0){
									%> class="tableCell1"
										<%}else{%> class="tableCell2" <%	}count++; %>
										onmouseover="javascript:newRowColor(this);"
										title="Double click for edit"
										onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
										ondblclick="javascript:callForEdit('<s:property value="hiddendisputeListID"/>');">
										<s:hidden name="hiddendisputeListID" />	
										<td align="center" class="sortableTD"><%=++i%></td>
										
										<td class="sortableTD">&nbsp;
											<s:hidden name="disputeRaisedID" />
											<s:property	value="disputeRaisedBy" />																					
										</td>

										<td class="sortableTD">&nbsp;
											<s:property value="typeOfDispute" />
										</td>										

										<td class="sortableTD">&nbsp;
											<s:property	value="disputeStatement" />
										</td>
										
										<td class="sortableTD">&nbsp;
											<s:property	value="underAct" />
										</td>
										
										<td align="center" class="sortableTD">&nbsp;
											<s:property	value="loggedOnDate" />
										</td>										
									</tr>
								</s:iterator>

								<%
								d = i;
								%>
							</s:if>
							
							<s:else>
								<td align="center" colspan="6" nowrap="nowrap">
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
					&nbsp;
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
   	    document.getElementById('paraFrm').action="DisputeManagementSystem_addNew.action";
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
	document.getElementById("paraFrm").action ='DisputeManagementSystem_f9searchRecords.action';
	document.getElementById("paraFrm").submit();
}

function callForEdit(id)
{	
	callButton('NA', 'Y', 2);
  	
	document.getElementById("paraFrm").action="DisputeManagementSystem_calforedit.action?id="+id;
	document.getElementById('paraFrm').target = "_self";
	document.getElementById("paraFrm").submit();
}
</script>



