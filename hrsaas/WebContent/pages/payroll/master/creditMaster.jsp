<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript">
	var records = new Array();
</script>
<s:form action="CreditMaster" id="paraFrm" validate="true"
	theme="simple">
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />
	<s:hidden name="creditMaster.CreditCode" />
	<s:hidden name="creditMaster.CreditName"/>
	<s:hidden name="creditMaster.CreditAbbr"/>
	
	<s:hidden name="report" />
	<s:hidden name="reportAction" value='CreditMaster_getReport.action' />
	
<table class="formbg" width="100%">
	<tr>
		<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong class="formhead">
					<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Credit Master</strong></td>
					<td width="3%" valign="top" class="txt"><div align="right">
					<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				</tr>
			</table>
		</td>
	</tr>

     <tr>
		<td colspan="3">
			<table width="100%" border="0" align="right" cellpadding="0"  cellspacing="0">
					<tr>
						<td width="100%" align="left" colspan="3">
							<table width="100%" align="left" cellspacing="0" cellpadding="0" id='topButtonTable'>
								<tr>
									<td width="20%" colspan="1"  align="left"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
									<td width="80%" colspan="2"><%@ include	file="/pages/common/reportButtonPanel.jsp"%></td>
								  	<% int totalPage = 0; int pageNo = 0; %>
								  				
								</tr>
							</table>
						</td>
					</tr>

				<tr>
					<td>
					<div name="htmlReport" id='reportDiv'
						style="background-color: #FFFFFF; height: 400; width: 100%; display: none; border-top: 1px #cccccc solid;">
					<iframe id="reportFrame" frameborder="0" onload=alertsize();
						style="vertical-align: top; float: left; border: 0px solid;"
						allowtransparency="yes" src="../pages/common/loading.jsp"
						scrolling="auto" marginwidth="0" marginheight="0" vspace="0"
						name="htmlReport" width="100%" height="200"></iframe></div>
					</td>

				</tr>
				<tr>
						<td colspan="3">
								<%
						try {
						%>
							<table width="100%" border="0" cellpadding="1" cellspacing="0"
								class="formbg" id="reportBodyTable">
								<tr>
									<s:if test="listLength">
									<td id="ctrlShow" width="100%" align="right" class="">
										<b>Page:</b>
										<%	 totalPage = (Integer) request.getAttribute("totalPage");
											 pageNo = (Integer) request.getAttribute("pageNo");
										%>
										<a href="#" onclick="callPage('1', 'F', '<%=totalPage%>', 'CreditMaster_callPage.action');">
											<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
										</a>&nbsp;
										<a href="#" onclick="callPage('P', 'P', '<%=totalPage%>', 'CreditMaster_callPage.action');" >
											<img title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
										</a>
										<input type="text" name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10"
										onkeypress="callPageText(event, '<%=totalPage%>', 'CreditMaster_callPage.action');return numbersOnly();" /> of <%=totalPage%>
										<a href="#" onclick="callPage('N', 'N', '<%=totalPage%>', 'CreditMaster_callPage.action')" >
											<img title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
										</a>&nbsp;
										<a href="#" onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'CreditMaster_callPage.action');" >
											<img title="Last Page" src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
										</a>						
									</td>						
									</s:if>			
									<td align="right">
										<s:submit cssClass="delete"  theme="simple"
													value="     Delete  "  onclick=" return chkDelete()"/>
									</td>
								</tr>										
								<tr>
							
									<td class="formtext" colspan="2">
									<table width="100%" border="0" cellpadding="1" cellspacing="1">
										<tr>
											<s:hidden name="myPage" id="myPage" />
									<td class="formth" align="center" ><!--Sr No.-->
										<label  class = "set" name="serial.no" id="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label>
									</td>
									<td class="formth" align="center"><!--Credit Name-->
										<label  class = "set" name="credit.name" id="credit.name1" ondblclick="callShowDiv(this);"><%=label.get("credit.name")%></label>
									</td>
									<td class="formth" align="center"><!--Credit Abbr-->
										<label  class = "set" name="credit.abbr" id="credit.abbr1" ondblclick="callShowDiv(this);"><%=label.get("credit.abbr")%></label>
									</td>
									<!--Credit Type-->
									<!-- 
									<td class="formth" align="center">
										<label  class = "set" name="credit.type" id="credit.type1" ondblclick="callShowDiv(this);"><%=label.get("credit.type")%></label>
									</td>
									-->
									<td class="formth" align="center"><!--Credit Pay--> 
										<label  class = "set" name="credit.payfl" id="credit.payfl1" ondblclick="callShowDiv(this);"><%=label.get("credit.payfl")%></label>
									</td>
									<td class="formth" align="center"><!--App IncomeTax-->
										<label  class = "set" name="credit.apait" id="credit.apait1" ondblclick="callShowDiv(this);"><%=label.get("credit.apait")%></label>
									</td>
									<td class="formth" align="center"><!--App Professional Tax-->
										<label  class = "set" name="credit.apapt" id="credit.apapt1" ondblclick="callShowDiv(this);"><%=label.get("credit.apapt")%></label>
									</td>		
									<td class="formth" align="center"><!--App ESIC-->
										<label  class = "set" name="credit.apaesic" id="credit.apaesic1" ondblclick="callShowDiv(this);"><%=label.get("credit.apaesic")%></label>
									</td>									
									<td class="formth" align="center"><!--App Arrears-->
										<label  class = "set" name="credit.apar" id="credit.apar1" ondblclick="callShowDiv(this);"><%=label.get("credit.apar")%></label>
									</td>
									<td class="formth" align="center"><!--Credit Period-->
										<label  class = "set" name="credit.period" id="credit.period1" ondblclick="callShowDiv(this);"><%=label.get("credit.period")%></label>
									</td>
									<td class="formth" align="center"><!--Credit Priority-->
										<label  class = "set" name="credit.priority" id="credit.priority1" ondblclick="callShowDiv(this);"><%=label.get("credit.priority")%></label>								
									</td>
									<!--Is Reimbursement
									<td class="formth" align="center">
										<label  class = "set" name="credit.reimb" id="credit.reimb1" ondblclick="callShowDiv(this);"><%=label.get("credit.reimb")%></label>
									</td>		
											-->													
									<td  align="right" class="formth" nowrap="nowrap" id="ctrlShow">
										<input type="checkbox" id="selAll" style="cursor: hand;" title="Select all records to remove" name="selAll"
											onclick="selectAllRecords(this);" />
									</td>
							 	</tr>
							
							
									<%int count=0; %>
									<%! int d=0; %>
									<%
									int cnt= pageNo*20-20;	
									int i = 0;
										%>
									<s:iterator value="iteratorlist">
							
										<tr 
										<%if(count%2==0){
											%>
											 class="tableCell1" <%}else{%>
											class="tableCell2" <%	}count++; %>
											onmouseover="javascript:newRowColor(this);"
											title="Double click for edit"
											onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
											ondblclick="javascript:callForEdit('<s:property value="CreditCode" />');">
											<td width="7%" align="left" class="sortableTD"><%=++cnt%><%++i;%></td>
											<s:hidden value="%{CreditCode}" id='<%="CreditCode"+i%>' />
												<script type="text/javascript">
													records[<%=i%>] = document.getElementById('CreditCode' + '<%=i%>').value;									
												</script>	
											<td width="35%" align="left" class="sortableTD">
							                                               <s:property value="CreditName" /></td>
											<td width="10%" class="sortableTD"><s:property value="CreditAbbr" /></td>
											
											
											<!-- 
											<td width="7%" class="sortableTD"><s:property value="CreditType" />&nbsp;</td>
											 -->
											<td width="7%" class="sortableTD"><s:property value="Creditpayflag" /></td>
											<td width="7%" class="sortableTD"><s:property value="taxable" />&nbsp;</td>
											<td width="7%" class="sortableTD"><s:property value="proTax" />&nbsp;</td>
											<td width="7%" class="sortableTD"><s:property value="esic" />&nbsp;</td>
											<td width="7%" class="sortableTD"><s:property value="appArrears" />&nbsp;</td>																																												
											<td width="7%" class="sortableTD"><s:property value="crePeriod" />&nbsp;</td>
											<td width="7%" class="sortableTD"><s:property value="CreditPrior" /> &nbsp;									
											<input type="hidden" name="hdeleteCode" id="hdeleteCode<%=i%>" />
											</td>
											<!--  <td width="7%" class="sortableTD"><s:property
											value="creditReimbItt" />&nbsp;</td>-->
																																
											<td  align="center" nowrap="nowrap" class="sortableTD" id="ctrlShow"><input type="checkbox"
												class="checkbox" id="confChk<%=i%>" name="confChk"
												onclick="callForDelete1('<s:property value="CreditCode" />','<%=i%>')" /></td>

										</tr>
							
									</s:iterator>
									<%d=i; %>
							
							
								
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
			
				</table>
		</td>
	</tr>
	

		<tr>
			<td>
			<div id='bottomButtonTable'></div>
			</td>
		</tr>
	</table>
</s:form>
<SCRIPT LANGUAGE="JavaScript">
</SCRIPT>

<script>

	function addnewFun()
	{	
		document.getElementById("paraFrm").target='_self';
		document.getElementById("paraFrm").action='CreditMaster_addNew.action';
		document.getElementById("paraFrm").submit();
	}

	function searchFun() {
			if(navigator.appName == 'Netscape') {
				var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
			} else {
				var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
			}
			
			document.getElementById("paraFrm").target = 'myWin';
			document.getElementById("paraFrm").action = 'CreditMaster_f9action.action';
			document.getElementById("paraFrm").submit();
	}

	function reportFun() {
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = 'CreditMaster_report.action';
		document.getElementById('paraFrm').submit();
	}
	
</script>



<script>

  	pgshow();
  	function pgshow()
  	{
  	var pgno=document.getElementById('paraFrm_show').value;
  
  	if(!(pgno==""))
  	 document.getElementById('selectname').value=pgno;
  	}



	function callForEdit(id){
	  	document.getElementById('paraFrm_hiddencode').value=id;
	  	
	   	document.getElementById("paraFrm").action="CreditMaster_calforedit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
   }
    function callForDelete(id){
	  	document.getElementById('paraFrm_hiddencode').value=id;
	   	document.getElementById("paraFrm").action="CreditMaster_calfordelete.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
   }
   
function callForDelete1(id,i)
	   {
	   
	
	   if(document.getElementById('confChk'+i).checked == true)
	   {	  
	    document.getElementById('hdeleteCode'+i).value=id;
	   }
	   else
	   document.getElementById('hdeleteCode'+i).value="";
	   
   }
   
   	function newRowColor(cell)
   	 {
			cell.className='Cell_bg_first';      //'onOverCellPayroll';

	}
	
	function oldRowColor(cell,val) {
	
	if(val=='1'){
	 cell.className='tableCell2';
	}
	else 
	cell.className='tableCell1';
		
	}
	
	function chkDelete()
	{
	
	 if(chk()){
	 var con=confirm('Do you really want to delete these records ?');
	 if(con){
	   document.getElementById('paraFrm').action="CreditMaster_delete1.action";
	    document.getElementById('paraFrm').submit();
	    }
	        else
	    {     
	    document.getElementById('selAll').checked=false;
	    var flag='<%=d %>';
	    for(var a=1;a<=flag;a++){	
	    document.getElementById('confChk'+a).checked =false;
	    document.getElementById('hdeleteCode'+a).value="";
	 	}
	     return false;
	     }
	     
	 }
	 else {
	 alert('Please Select Atleast one Record To Delete');
	 	 return false;
	 }
	 
	}
	
	function chk(){
	 var flag='<%=d %>';
	  for(var a=1;a<=flag;a++){	
	   if(document.getElementById('confChk'+a).checked == true)
	   {	
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
	
	//Added by Tinshuk Banafar
	
	function callReport(type){	
	try{
	
	    document.getElementById('paraFrm_report').value=type;
		callReportCommon(type);
	
		 }
		 catch (e)
	    {
	 	alert(e);
	 	}	
	 }
	 
	 function mailReportFun(type){
				
		document.getElementById('paraFrm_report').value=type;
		document.getElementById('paraFrm').action='CreditMaster_mailReport.action';
		document.getElementById('paraFrm').submit();
			//return true;
		}
  	</script>
