<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript">
	var records = new Array();
</script>
<s:form action="DebitMaster" validate="true" id="paraFrm" theme="simple">
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />
	<s:hidden name="debitMaster.DebitCode"/>
	<s:hidden name="debitMaster.DebitName"/>
	<s:hidden name="debitMaster.DebitAbbr"/>
	<s:hidden name="reportAction" value='DebitMaster_report2.action'/>
	<s:hidden name="report" />
	
	<table class="formbg" width="100%">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Debit
					Master</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0"><tr valign="middle">

				<tr>
					<td colspan="3" width="100%">
						<table align="left" width="100%" cellpadding="0" cellspacing="0" id='topButtonTable'>
							<tr>
								<td colspan="1" width="40%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
								<td colspan="2" width="60%" align="left"><%@ include	file="/pages/common/reportButtonPanel.jsp"%></td>
								
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
							<% int totalPage = 0; int pageNo = 0; %>
								<s:if test="listLength">
								<td width="100%" align="right" class="" >
									<b>Page:</b>
									<%	 totalPage = (Integer) request.getAttribute("totalPage");
										 pageNo = (Integer) request.getAttribute("pageNo");
									%>
									<a href="#" onclick="callPage('1', 'F', '<%=totalPage%>', 'DebitMaster_callPage.action');">
										<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
									</a>&nbsp;
									<a href="#" onclick="callPage('P', 'P', '<%=totalPage%>', 'DebitMaster_callPage.action');" >
										<img title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
									</a>
									<input type="text" name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10"
									onkeypress="callPageText(event, '<%=totalPage%>', 'DebitMaster_callPage.action');return numbersOnly();" /> of <%=totalPage%>
									<a href="#" onclick="callPage('N', 'N', '<%=totalPage%>', 'DebitMaster_callPage.action')" >
										<img title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
									</a>&nbsp;
									<a href="#" onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'DebitMaster_callPage.action');" >
										<img title="Last Page" src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
									</a>														
								</td>
								</s:if>
							<td align="right">
								<s:submit cssClass="delete" theme="simple" value="     Delete  "
										onclick=" return chkDelete()" />
							</td>
						</tr>										
						<tr>

							<td class="formtext" colspan="2">
							<table width="100%" border="0" cellpadding="1" cellspacing="1">
								<tr>
									<s:hidden name="myPage" id="myPage" />
									<td class="formth" align="center"><!--Sr No-->
										<label  class = "set" name="serial.no" id="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label>
									</td>
									<td class="formth" align="center"><!--Debit Name-->
										<label  class = "set" name="debit.name" id="debit.name1" ondblclick="callShowDiv(this);"><%=label.get("debit.name")%></label>
									</td>
									<td class="formth" align="center"><!--Debit Abbr-->
										<label  class = "set" name="debit.abbr" id="debit.abbr1" ondblclick="callShowDiv(this);"><%=label.get("debit.abbr")%></label>
									</td>
									<td class="formth" align="center"><!--Debit Exempted in Tax-->
										<label  class = "set" name="debit.exempttax" id="debit.exempttax1" ondblclick="callShowDiv(this);"><%=label.get("debit.exempttax")%></label>
									</td>
									<!-- 	<td class="formth" align="center">Debit Balance Carry Forward Flag</td>
							<td class="formth" align="center">Is Loan Flag</td>
							 -->
									<td class="formth" align="center"><!--Debit Priority-->
										<label  class = "set" name="debit.prior" id="debit.prior1" ondblclick="callShowDiv(this);"><%=label.get("debit.prior")%></label>
									</td>
									<td class="formth" align="center"><!--Debit Exempted-->
										<label  class = "set" name="debit.exemptundersec" id="debit.exemptundersec1" ondblclick="callShowDiv(this);"><%=label.get("debit.exemptundersec")%></label>
									</td>
									<td class="formth" align="center"><!--Debit Period-->
										<label  class = "set" name="debit.period" id="debit.period1" ondblclick="callShowDiv(this);"><%=label.get("debit.period")%></label>
									</td>
									<td class="formth" align="center"><!--Debit Pay Flag-->
										<label  class = "set" name="debit.payFlag" id="debit.payFlag1" ondblclick="callShowDiv(this);"><%=label.get("debit.payFlag")%></label>
									</td>


									<td align="right" class="formth" id="ctrlShow">
										<input type="checkbox" id="selAll" style="cursor: hand;" title="Select all records to remove" name="selAll"
											onclick="selectAllRecords(this);" />										
									</td>
								</tr>


								<%
								int count = 0;
								%>
								<%!int d = 0;%>
								<%
										int cnt = pageNo * 20 - 20;
										int i = 0;
								%>
								<s:iterator value="iteratorlist">

									<tr <%if(count%2==0){
									%> class="tableCell1"
										<%}else{%> class="tableCell2" <%	}count++; %>
										onmouseover="javascript:newRowColor(this);"
										title="Double click for edit"
										onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
										ondblclick="javascript:callForEdit('<s:property value="DebitCode" />');">


										<td width="10%" align="left" class="sortableTD"><%=++cnt%><%++i;%>
										</td>
										<s:hidden value="%{DebitCode}" id='<%="DebitCode"+i%>' />
										<script type="text/javascript">
											records[<%=i%>] = document.getElementById('DebitCode'+'<%=i%>').value;
										</script>
										<td width="30%" align="left" class="sortableTD"><s:property
											value="DebitName" /></td>
										<td width="10%" class="sortableTD"><s:property value="DebitAbbr" /></td>

										<td width="12%" class="sortableTD"><s:property value="Debitexempt" /></td>
										<!--  <td width="7%"><s:property value="DebitBalFlag" /></td>
									<td width="7%"><s:property value="DebitforLoan" /></td>
								-->
										<td width="7%" class="sortableTD"><s:property value="debitPriority" />&nbsp;</td>
										<td width="15%" class="sortableTD"><s:property value="exemptSectionNo" />&nbsp;</td>
										<td width="7%" class="sortableTD"><s:property value="tableRecover" />&nbsp;</td>
										<td width="7%" class="sortableTD"><s:property value="debitPayFlag" />&nbsp; <input
											type="hidden" name="hdeleteCode" id="hdeleteCode<%=i%>" /></td>
										<td align="center" class="sortableTD" id="ctrlShow"><input type="checkbox"
											class="checkbox" id="confChk<%=i%>" name="confChk"
											onclick="callForDelete1('<s:property value="DebitCode" />','<%=i%>')" /></td>




									</tr>

								</s:iterator>
								<%
								d = i;
								%>
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
		<td colspan="3">
			<table width="100%" cellspacing="0" cellpadding="0" id='topButtonTable'>
				<tr>
					<td colspan="1" width="40%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td colspan="2" width="60%" align="left"><%@ include	file="/pages/common/reportButtonPanel.jsp"%></td>
					
				</tr>
				<tr><s:if test="listLength">
						<td colspan="3" width="100%" align="right"><b>Total No. of Records: <s:property value="totalRecords" /></b></b></td>
					</s:if></tr>
				
			</table>
		</td>			
	</tr>						

	</table>
</s:form>

<script type="text/javascript">

	function addnewFun()
	{	
		document.getElementById("paraFrm").target='_self';
		document.getElementById("paraFrm").action='DebitMaster_addNew.action';
		document.getElementById("paraFrm").submit();
	}

	function searchFun() {
			if(navigator.appName == 'Netscape') {
				var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
			} else {
				var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
			}
			document.getElementById("paraFrm").target = 'myWin';
			document.getElementById("paraFrm").action = 'DebitMaster_f9action.action';
			document.getElementById("paraFrm").submit();
	}

	function reportFun() {
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = 'DebitMaster_report.action';
		document.getElementById('paraFrm').submit();
	}
	
</script>

<script type="text/javascript">



  	
  	pgshow();
  	function pgshow()
  	{
  	var pgno=document.getElementById('paraFrm_show').value;
  
  	if(!(pgno==""))
  	 document.getElementById('selectname').value=pgno;
  	}



	function callForEdit(id){
	  	document.getElementById('paraFrm_hiddencode').value=id;
	   	document.getElementById("paraFrm").action="DebitMaster_calforedit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
   }
    function callForDelete(id){
	  	document.getElementById('paraFrm_hiddencode').value=id;
	   	document.getElementById("paraFrm").action="DebitMaster_calfordelete.action";
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
			cell.className='Cell_bg_first';           //'onOverCellPayroll';

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
	   document.getElementById('paraFrm').action="DebitMaster_delete1.action";
	    document.getElementById('paraFrm').submit();
	    }
	    else
	    {     
	    document.getElementById('selAll').checked=false;
	    var flag='<%=d %>';
	    for(var a=1;a<=flag;a++){	
	    document.getElementById('confChk'+a).checked=false;
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
   function selectAllRecords(object)
   {
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
   	function callReport(type){
		document.getElementById('paraFrm_report').value=type;
		callReportCommon(type);
		}
	
	
	function mailReportFun(type)
{
		
			document.getElementById('paraFrm_report').value=type;
			//callDropdown(obj.name,200,250,'MonthlyEDSummaryReport_mailReport.action','false');
			document.getElementById('paraFrm').action='DebitMaster_mailReport.action';
			document.getElementById('paraFrm').submit();
			//return true;
		
	}
   
</script>




