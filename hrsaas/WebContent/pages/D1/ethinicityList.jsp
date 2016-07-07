<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript">
var records = new Array();
</script>
<s:form action="Ethinicity" validate="true" id="paraFrm" validate="true"
	theme="simple">
	
	<!-- Hidden form fields start-->
	<s:hidden name="ethicCode" />
	<s:hidden name="ethinicity" />
  <!-- Hidden form fields Ends-->


	<table width="100%" border="0" align="right" class="formbg">

		<tr>
			<td colspan="3">
			<table width="100%" border="0" align="right" class="formbg">
				<tr>
					<td colspan="1" width="5%"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td colspan="1" width="80%" class="txt"><strong
						class="text_head"><b>Ethnicity</b></strong></td>
					<td colspan="1" width="5%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>


		<tr>
			<td colspan="3">
			<table width="100%" cellspacing="2" cellspacing="2" border="0">
				<td width="40%" colspan="1"><jsp:include
					page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

				<%
					int totalPage = 0;
					int pageNo = 0;
				%>
				<s:if test="ethicListLength">
					<s:if test="modeLength">
						<td id="ctrlShow" width="60%" align="right" class="" colspan="2"><b>Page:</b>
						<%
						totalPage = (Integer) request.getAttribute("totalPage");
						pageNo = (Integer) request.getAttribute("pageNo");
					%> <a href="#"
							onclick="callPage('1', 'F', '<%=totalPage%>', 'Ethinicity_callPage.action');">
						<img title="First Page" src="../pages/common/img/first.gif"
							width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('P', 'P', '<%=totalPage%>', 'Ethinicity_callPage.action');">
						<img title="Previous Page" src="../pages/common/img/previous.gif"
							width="10" height="10" class="iconImage" /> </a> <input type="text"
							name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
							maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'Ethinicity_callPage.action');return numbersOnly();" />
						of <%=totalPage%> <a href="#"
							onclick="callPage('N', 'N', '<%=totalPage%>', 'Ethinicity_callPage.action')">
						<img title="Next Page" src="../pages/common/img/next.gif"
							class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'Ethinicity_callPage.action');">
						<img title="Last Page" src="../pages/common/img/last.gif"
							width="10" height="10" class="iconImage" /> </a></td>
					</s:if>
				</s:if>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" width="100%">
			<table class="formbg" width="100%" cellspacing="2" cellspacing="2"
				border="0">
				<tr>
					<td colspan="1" class="txt" width="5%"><strong
						class="text_head"><b> Ethnicity</b> </strong></td>
					<td colspan="1" align="right" width="60%"><input type="button"
						id="ctrlShow" class="delete" theme="simple" value=" Delete"
						onclick="return chkDelete();" /></td>
				<td colspan="1" width="35%">&nbsp;</td>
				
				
				</tr>


				<!-- start iteraor -->

				<tr>
					<td colspan="3">
					<%
						try {
						%>
					<table width="100%" border="0" class="formbg" cellspacing="2"
						cellspacing="2">
						<tr>

							<td class="formtext">
							<table width="100%" border="0" cellpadding="2" cellspacing="2">
								<tr>

									<s:hidden name="myPage" id="myPage" />
									<td class="formth" align="center" width="5%" colspan="1"><label
										class="set" name="sno" id="sno"
										ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></td>
									<td class="formth" align="center" width="88%" colspan="1"><label
										class="set" name="ethic_name" id="ethic_name"
										ondblclick="callShowDiv(this);"><%=label.get("ethic_name")%></label></td>

									<s:if test="modeLength">
										<td align="right" class="formth" id="ctrlShow" nowrap="nowrap"
											width="7%" colspan="1"><input type="checkbox"
											id="selAll" style="cursor: hand;"
											title="Select all records to remove"
											onclick="selectAllRecords(this);" /></td>
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

									<s:if test="ethicListLength">

										<s:iterator value="ethicList">
											<tr <%if(count%2==0){
												%> class="tableCell1"
												<%}else{%> class="tableCell2" <%	}count++; %>
												onmouseover="javascript:newRowColor(this);"
												title="Double click for edit"
												onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
												ondblclick="javascript:callForEdit('<s:property value="ethicCode"/>');">


												<td colspan="1" width="5%" align="center" class="sortableTD"><%=++cn%>
												<%
												++i;
												%><s:hidden name="srNo" /></td>
												<s:hidden value="%{ethicCode}" id='<%="ethicCode" + i%>'></s:hidden>
												<script type="text/javascript">
													records[<%=i%>] = document.getElementById('ethicCode' + '<%=i%>').value;
												</script>
												<td colspan="1" width="88%" align="left" class="sortableTD"><s:property
													value="ethinicity" /> <input type="hidden"
													name="hdeleteCode" id="hdeleteCode<%=i%>" /></td>

												<td colspan="1" width="7%" id="ctrlShow" align="center" nowrap="nowrap" 
													class="sortableTD"><input type="checkbox"
													class="checkbox" id="confChk<%=i%>" name="confChk"
													onclick="callForDelete1('<s:property value="ethicCode"/>','<%=i%>')" /></td>
											</tr>

										</s:iterator>
										<%
										d = i;
										%>
									</s:if>
								</s:if>

								<s:else>
									<td align="center" colspan="6" nowrap="nowrap"><font
										color="red">There is no data to display</font></td>
								</s:else>

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
					<td width="100%">
					<table width="100%" border="0" cellpadding="2" cellspacing="2">
						<tr>
							<td width="75%"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

							<td width="20%" align="right"><s:if test="modeLength">
								<b>Total No. Of Records:</b>&nbsp;<s:property
									value="totalNoOfRecords" />
							</s:if></td>
						</tr>
					</table>
					</td>
				</tr>

			</table>
			</td>
		</tr>
	</table>
</s:form>
<script>
function addnewFun() {
	
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'Ethinicity_addNew.action';
		document.getElementById('paraFrm').submit();
	}


function searchFun() {
		
		if(navigator.appName == 'Netscape') {
			var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
		} else {
			var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		}
		
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action ='Ethinicity_f9action.action';
		document.getElementById("paraFrm").submit();
	}



function callForDelete1(id,i)
	   {
	 /*alert("i am in dete fun in regionMasterInput jsp");*/
	   /** var flag='<%=d %>';*/
	/*alert('id----1-----'+id);*/
	/*alert('i----1-----'+i);*/
	
	   if(document.getElementById('confChk'+i).checked == true)
	   {	  
	    document.getElementById('hdeleteCode'+i).value=id;
	   }
	   else
	   {
	   document.getElementById('hdeleteCode'+i).value="";
   }
}

function callForEdit(id){
	callButton('NA', 'Y', 2);
	  	document.getElementById('paraFrm_ethicCode').value=id;
	   	document.getElementById("paraFrm").action="Ethinicity_editBusinessData.action";
	   	document.getElementById('paraFrm').target = "_self";
	    document.getElementById("paraFrm").submit();
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


function chk(){
   
	 var flag='<%=d %>';
	  for(var a=1;a<=flag;a++){	
	   if(document.getElementById('confChk'+a).checked == true)
	   {	
	  // alert('11');  
	    return true;
	   }	   
	  }
	  return false;
	}

	function chkDelete()
	{
	 
	 if(chk()){
	
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
	   document.getElementById('paraFrm').action="Ethinicity_deleteChkRecords.action";
	   document.getElementById('paraFrm').target = "_self";
	    document.getElementById('paraFrm').submit();
	    }
	    else
	    { 
	document.getElementById('selAll').checked = false;
	    var flag='<%=d %>';
	  for(var a=1;a<=flag;a++){	
	document.getElementById('confChk'+a).checked = false;
	document.getElementById('hdeleteCode'+a).value="";
	
	}
	     return false;
	 }
	 }
	 else {
	 alert('please select atleast one record');
	 return true ;
	 }
	
}
</script>
