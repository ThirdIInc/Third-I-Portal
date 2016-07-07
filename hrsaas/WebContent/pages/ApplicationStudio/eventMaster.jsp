 <%@ taglib prefix="s" uri="/struts-tags" %>

<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript">
	var records = new Array();
</script>
<s:form action="Event" validate="true" id="paraFrm" validate="true" theme="simple">
	<s:hidden name="show"  />
	<s:hidden name="hiddencode" />
	
	<s:hidden name="event" />
	<s:hidden name="mailId" />
	<s:hidden name="eventName" />
	<s:hidden name="eventCode" />
	


	<table width="100%" border="0" align="right"
		 class="formbg">
		
		<tr>
			<td valign="bottom" class="txt">
			<table  width="100%" border="0" align="right"  class="formbg" >
			<tr>
			<td>
			<strong class="text_head"><img
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="text_head">Event
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
			<td width="100%">
				<table width="100%">
					<tr>			
						<td width="70%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					</tr>
				</table>
			</td>						
		</tr>
		<tr>
			<td colspan="3" width="100%">
				<table class="formbg" width="100%" cellspacing="0" cellspacing="1" border="0">		
					<tr>
						<td colspan="2" class="txt"><strong class="text_head">Event </strong></td>		
						<td align="right">
							<input type="button" id="ctrlShow" class="delete" theme="simple"	value=" Delete"    onclick="return chkDelete()"/>
						</td>
					</tr>			
					<tr>
						<td colspan="3">
						<%
						try {
						%>
						<table width="100%" border="0" cellping="0" cellspacing="0"
							class="formbg">
							<tr>
								
								<td class="formtext" colspan="3">
								<table width="100%" border="0" cellping="1" cellspacing="1">
									<tr>
										<s:hidden name="myPage" id="myPage" />
										<td class="formth" align="center"><label  class = "set" name="srNo" id="srNo" ondblclick="callShowDiv(this);"><%=label.get("srNo")%></label></td>
										<td class="formth" align="center"><label  class = "set" name="event" id="event" ondblclick="callShowDiv(this);"><%=label.get("event")%></label></td>
										<td class="formth" align="center"><label  class = "set" name="mailId" id="mailId" ondblclick="callShowDiv(this);"><%=label.get("mailId")%></label></td>
										
										<s:if test="modeLength"><td id="ctrlShow" align="right" class="formth" nowrap="nowrap">
										<input type="checkbox" id="selAll" style="cursor: hand;" title="Select all records to remove" 
											onclick="selectAllRecords(this);" />
									</td>
									</s:if>
									<s:if test="modeLength">
									<%int count=0; %>
										<%!int d=0; %>
										<% int i = 0; %>		
										<s:iterator value="eventList">
											
											<tr title="Double click for edit"
															<% if(count % 2 == 0) { %> 
																class="tableCell1"
															<% } else { %>
																class="tableCell2"
															<%	} count++; %>
															onmouseover="javascript:newRowColor(this);"												
															onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
															ondblclick="javascript:callForEdit('<s:property value="hiddenAutoCode"/>');">	
											<td align="center" nowrap="nowrap" id="ctrlShow" class="sortableTD" width="10%"><%=count%></td>
											<td align="center" nowrap="nowrap" id="ctrlShow" class="sortableTD" width="10%">
												<s:property value="event"/>
											</td>
											<td align="center" nowrap="nowrap" id="ctrlShow" class="sortableTD" width="10%">
												<s:property value="mailId"/>
													 
													
										 <input type="hidden" name="hiddenAutoCode" id='hiddenAutoCode<%=count%>' value="<s:property value="hiddenAutoCode"/>"  />
 														
											</td>
											<% ++i; %> 
											 <script type="text/javascript">
													records[<%=count%>] = document.getElementById('hiddenAutoCode'+'<%=count%>').value;
											 </script>
											<td align="center" nowrap="nowrap" id="ctrlShow" class="sortableTD" width="10%">
												<input type="hidden" name="hdeleteCode" id="hdeleteCode<%=i%>" />
												<s:hidden name="hiddenAutoCode"  />
												<input type="checkbox" class="checkbox" id="confChk<%=i%>" name="confChk"
													  onclick="callForDelete1('<s:property value="hiddenAutoCode"/>','<%=i%>')" />
											</td>
											 
											</tr>
			
										</s:iterator>
										<% d = i; %>
										</s:if>
								</tr>
									
								</table>
								<s:if test="modeLength"></s:if>
								<s:else>
									<table width="100%">
										<tr>
											<td align="center"><font color="red">No Data To Display</font></td>
										</tr>
									</table>
								</s:else>
								</td>
							</tr>
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
			<td width="100%" colspan="3">
				<table width="100%">
					<td width="80%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				</table>
			</td>
		</tr>
  </table>		 
  </s:form>	
  	<script>
	  	function addnewFun() {
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action = 'Event_addNewEvent.action';
			document.getElementById('paraFrm').submit();
		}
		function callForEdit(id){
  		  	    callButton('NA', 'Y', 2);
			  	document.getElementById('paraFrm_hiddencode').value=id;
			   	document.getElementById("paraFrm").action='Event_callForEdit.action?hiddenCode='+id;
			   	document.getElementById('paraFrm').target = "_self";
			    document.getElementById("paraFrm").submit();
		}
   
		 function callForDelete1(id,i)
		 {
		   var flag='<%=d %>';
		   if(document.getElementById('confChk'+i).checked == true)
		   {	
		    document.getElementById('hdeleteCode'+i).value=id;
		   }
		   else
		   document.getElementById('hdeleteCode'+i).value="";
	   }
  	    function chkDelete(){
  	     if(chk()){
			 var con=confirm('Do you want to delete the record(s) ?');
			 if(con){
			   document.getElementById('paraFrm').action="Event_deleteEventData.action";
			   document.getElementById('paraFrm').target = "_self";
			    document.getElementById('paraFrm').submit();
			  }
			  else
			  { 
			    var flag='<%=d %>';
			    document.getElementById('selAll').checked=false;
			    for(var a=1;a<=flag;a++){	
					document.getElementById('confChk'+a).checked = false;
					document.getElementById('hdeleteCode'+a).value="";
				}
			     return false;
			  }
		  }
		 else {
			  alert('please select atleast one record');
			  return false;
		 }
      }
	 function chk(){
		 var flag='<%=d %>';
		  for(var a=1;a<=flag;a++){	
			   if(document.getElementById('confChk'+a).checked == true){	  
			    return true;
			   }	   
		  }
		  return false;
	 }   
	 
	 function selectAllRecords(object) {
	    
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
	}
	function searchFun() {
		if(navigator.appName == 'Netscape') {
			var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
		} else {
			var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		}
		
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action ='Event_f9action.action';
		document.getElementById("paraFrm").submit();
	}
  	</script>