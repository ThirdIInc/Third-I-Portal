
<!-- Vishwambhar Deshpande Date:-19 Aug 2010 -->
<%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="NewLoansIssuedReport" validate="true" id="paraFrm"
	theme="simple">

	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<tr>
			<td colspan="3" width="100%"><s:hidden name="searchFlag" />
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/common/css/default/images/review_shared.gif"
						width="25" height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">
					New Loans Issued Report </strong></td>
					<td width="3%" valign="top" class="otxt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<s:hidden name="misreport.reportId" />

		<s:hidden name="divCode" />
		<s:hidden name="divName" />
		<s:hidden name="deptCode" />
		<s:hidden name="deptName" />
		<s:hidden name="branchCode" />
		<s:hidden name="branchName" />
		<s:hidden name="designationCode" />
		<s:hidden name="designationName" />
		<s:hidden name="empCode" />
		<s:hidden name="empToken" />
		<s:hidden name="empName" />
		<s:hidden name="loanType" />

		<s:hidden name="misreport.empNameFlag" />
		<s:hidden name="misreport.divFlag" />
		<s:hidden name="misreport.loanAmountFlag" />
		<s:hidden name="misreport.loanTypeFlag" />
		<s:hidden name="misreport.loanPaymentDateFlag" />
		<s:hidden name="misreport.loanInstallmentFlag" />
		<s:hidden name="misreport.noOfInstallFlag" />


		<s:hidden name="sortBy" />
		<s:hidden name="hiddenSortBy" />
		<s:hidden name="sortByAsc" />
		<s:hidden name="sortByDsc" />
		<s:hidden name="sortByOrder" />
		<s:hidden name="thenBy1" />
		<s:hidden name="hiddenThenBy1" />
		<s:hidden name="thenByOrder1Asc" />
		<s:hidden name="thenByOrder1Dsc" />
		<s:hidden name="thenByOrder1" />
		<s:hidden name="thenBy2" />
		<s:hidden name="hiddenThenBy2" />
		<s:hidden name="thenByOrder2Asc" />
		<s:hidden name="thenByOrder2" />
		<s:hidden name="thenByOrder2Dsc" />

		<s:hidden name="hiddenColumns" />



		<s:hidden name="hidReportView" />
		<s:hidden name="hidReportRadio" />
		<s:hidden name="reportType" />
		<s:hidden name="reportView" />

		<s:hidden name="misreport.reportTitle" />
		<s:hidden name="settingName" />

		<s:hidden name="myPage" />
		<s:hidden name="show" />

		<s:hidden name='backFlag' />

		<s:hidden name='loanDateSelect' />
		<s:hidden name='misreport.loanFromDate' />
		<s:hidden name='misreport.loanToDate' />


		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="70%"><input type="button" value="  Back  "
						class="token" onclick="callBack();"> <s:if test="noData">
					</s:if> <s:else>
						<input type="button" value=" Export In Pdf " class="token"
							onclick="callReportForDisp('P');">
						<input type="button" value=" Export In Xls " class="token"
							onclick="callReportForDisp('X');">
						<input type="button" value=" Export In Doc " class="token"
							onclick="callReportForDisp('T');">
					</s:else></td>

					<td width="30%"><s:if test="noData">
					</s:if> <s:else>
						<label class="set" name="export.all" id="export.all"
							ondblclick="callShowDiv(this);"><%=label.get("export.all")%></label> : <input
							type="checkbox" name="exportAll" id="exportAll" checked="checked"
							onclick="callChkBox('exportAll');">
					</s:else></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td height="27" class="text_head"><strong> New
							Loans Issued Report </strong></td>
							<%
								int totalPage = 0;
								int pageNo = 0;
								try {
									totalPage = (Integer) request.getAttribute("totalPage");
									pageNo = (Integer) request.getAttribute("PageNo");
								} catch (Exception e) {

								}
								//System.out.println("pageNo :-----------" + pageNo);
							%>
							<s:if test="noData">
							</s:if>
							<s:else>
								<td align="right"><b>Page:</b> <input type="hidden"
									name="totalPage" id="totalPage" value="<%=totalPage%>">
								<a href="#" onclick="callPage('1','F');"> <img
									title="First Page" src="../pages/common/img/first.gif"
									width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPage('P','P');"> <img title="Previous Page"
									src="../pages/common/img/previous.gif" width="10" height="10"
									class="iconImage" /> </a> <input type="text" name="pageNoField"
									id="pageNoField" theme="simple" size="3" value="<%= pageNo%>"
									onkeypress="callPageText(event);return numbersOnly()"
									maxlength="4" title="press Enter" /> of <%=totalPage%> <a
									href="#" onclick="callPage('N','N')"> <img
									title="Next Page" src="../pages/common/img/next.gif"
									class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPage('<%=totalPage%>','L');"> <img
									title="Last Page" src="../pages/common/img/last.gif" width="10"
									height="10" class="iconImage" /> </a></td>
							</s:else>
						</tr>

						</td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td width="100%">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="sortable">
						<!--Table 5-->
						<tr>
							<td colspan="3" width="100%">
							<table width="100%" border="0" cellpadding="1" cellspacing="1"
								class="sortable">
								<!--Table 6-->

								<tr>

									<td width="5%" valign="top" class="formth" nowrap="nowrap"><label
										class="set" name="serial.no" id="serial.no"
										ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
									<%
										String labelValues = "";
										try {
											labelValues = (String) request.getAttribute("labelValues");
										} catch (Exception e) {

										}
										System.out.println("labels ================ : " + labelValues);
										if (!labelValues.equals("") && labelValues.length() > 0) {
											labelValues = labelValues
											.substring(0, labelValues.length() - 1);
											// System.out.println("labels  : "+labelValues);
											String[] splitLabels = labelValues.split(",");
											for (int i = 0; i < splitLabels.length; i++) {
												// System.out.println("labels =================== : "+splitLabels[i]);
									%>
									<td width="<%=(100/splitLabels.length)%>%" valign="top"
										class="formth"><%=splitLabels[i]%></td>

									<%
										}
										}
									%>
								</tr>


								<%
									int cnt = 0;
									int pgNo = pageNo * 20 - 20;
								%>
								<%
									Object[][] finalObj = (Object[][]) request.getAttribute("finalObj");
									// System.out.println("finalObj.length================  :"+finalObj.length);
									if (finalObj != null && finalObj.length > 0) {
										// System.out.println("finalObj.length  :"+finalObj.length);
										for (int i = 0; i < finalObj.length; i++) {
								%>
								<tr>
									<td width="5%" class="sortableTD" nowrap="nowrap">
									<%
									++cnt;
									%> <%=++pgNo%></td>
									<%
											for (int j = 1; j <= finalObj[0].length; j++) {
											//System.out.println("query values["+i+"]["+(j-1)+"]  : "+String.valueOf(finalObj[i][j-1]));
									%>
									<td width="25%" nowrap="nowrap" class="sortableTD">&nbsp;<%=String.valueOf(finalObj[i][j - 1])%></td>
									<%
									}
									%>
								</tr>
								<%
									}
									}
								%>


								<s:if test="noData">
									<tr>
										<td width="100%" colspan="7" align="center"><font
											color="red">There is no data to display.</font></td>
									</tr>
								</s:if>
								<s:else>
									<tr>
										<td width="100%" colspan="11" align="right"><b>Total
										records : <s:property value="dataLength" /></b></td>
									</tr>
								</s:else>
							</table>
							</td>
						</tr>
					</table>
					<!--Table 5--></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="70%"><input type="button" value="  Back  "
						class="token" onclick="callBack();"> <s:if test="noData">
					</s:if> <s:else>
						<input type="button" value=" Export In Pdf " class="token"
							onclick="callReportForDisp('P');">
						<input type="button" value=" Export In Xls " class="token"
							onclick="callReportForDisp('X');">
						<input type="button" value=" Export In Doc " class="token"
							onclick="callReportForDisp('T');">
					</s:else></td>

					<td width="30%"><s:if test="noData">
					</s:if> <s:else>
						<label class="set" name="export.all" id="export.all"
							ondblclick="callShowDiv(this);"><%=label.get("export.all")%></label> : <input
							type="checkbox" name="exportAll_bot" id="exportAll_bot"
							checked="checked" onclick="callChkBox('exportAll_bot');">
					</s:else></td>
				</tr>
			</table>
			</td>
		</tr>

	</table>
</s:form>

<script> 
 callOnLoad();

window.onload=   document.getElementById('pageNoField').focus();

function callOnLoad(){
 	try{
 	var sortBy = document.getElementById('paraFrm_sortBy').value;
   	document.getElementById('paraFrm_hiddenSortBy').value=sortBy;
   	
   	var thenBy1 = document.getElementById('paraFrm_thenBy1').value;
   	document.getElementById('paraFrm_hiddenThenBy1').value=thenBy1;
   	
   	var thenBy2 = document.getElementById('paraFrm_thenBy2').value;
   	document.getElementById('paraFrm_hiddenThenBy2').value=thenBy2;
   	}catch(e){
   		//alert(e);
   	}
 }
 
 function callChkBox(fieldName)
 {  
	 if(fieldName=="exportAll_bot")
	 { 
		  if(document.getElementById('exportAll_bot').checked){
		  
		     document.getElementById('exportAll').checked=true;
		   }else{
		      document.getElementById('exportAll').checked=false;
		   }
	 }else{
 
		    if(document.getElementById('exportAll').checked){
		     document.getElementById('exportAll_bot').checked=true;
		   }else{
		      document.getElementById('exportAll_bot').checked=false;
		   } 
	   } 
 }
	function callPageText(id){   
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoField').value;	 
		 	totalPage =document.getElementById('totalPage').value;	
		 	var actPage = document.getElementById('paraFrm_myPage').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoField').focus();
		     document.getElementById('pageNoField').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField').focus();
		     document.getElementById('pageNoField').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoField').focus();
		      return false;
	        }
	        
	         document.getElementById('paraFrm_myPage').value=pageNo;
		   
			document.getElementById('paraFrm').action='NewLoanIssuedRpt_viewOnScreen.action';
			document.getElementById('paraFrm').submit();
		}
		
	}
	
	function callPage(id,pageImg){ 
	try{ 
 	 pageNo =document.getElementById('pageNoField').value;	
 	 totalPage =document.getElementById('totalPage').value;	 
 	 var actPage = document.getElementById('paraFrm_myPage').value; 
	 if(pageNo!="0"  & pageNo<totalPage){	
		 document.getElementById('paraFrm_myPage').value=pageNo;
	   }
	   
 	 	    	
       if( pageImg !="F" & pageImg !="L" )
         { 
	 	   if(pageNo==""){
		        alert("Please Enter Page Number.");
		        document.getElementById('pageNoField').focus();
				 return false;
		        }
		  if(Number(pageNo)<=0)
			    {
			     alert("Page number should be greater than zero.");
			     document.getElementById('pageNoField').value=actPage;
			     document.getElementById('pageNoField').focus();
				 return false;
			    }
		  if(Number(totalPage)<Number(pageNo))
			    {
			     alert("Page number should not be greater than "+totalPage+".");
			      document.getElementById('pageNoField').value=actPage;
			     document.getElementById('pageNoField').focus();
				 return false;
			    }
		 }
		    	
       if(pageImg=="F")
         {
	        if(pageNo=="1"){
	         alert("This is first page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       }  
       
       	if(pageImg=="L")
         {
	        if(eval(pageNo)==eval(totalPage)){
	         alert("This is last page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       } 
       
        if(pageImg=="P")
       {
	        if(pageNo=="1"){
	         alert("There is no previous page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        }  
       }  
       if(pageImg=="N")
       {
	        if(Number(pageNo)==Number(totalPage)){
	         alert("There is no next page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        }  
       }  
       
		if(id=='P'){
		var p=document.getElementById('pageNoField').value;
		id=eval(p)-1;
		}
		if(id=='N'){
		var p=document.getElementById('pageNoField').value;
		id=eval(p)+1;
		} 
		document.getElementById('paraFrm_myPage').value=id;
		document.getElementById('paraFrm_show').value=id;
		document.getElementById('paraFrm').action='NewLoanIssuedRpt_viewOnScreen.action';
		document.getElementById('paraFrm').submit(); 
		}catch(e){
			alert(e);
		}
	}	
	 
	 
 function onPage()
   {
  	var val= document.getElementById('selectname').value;
	document.getElementById('paraFrm_show').value=val;
	document.getElementById('paraFrm_myPage').value=eval(val);
	document.getElementById('selectname').value=val;
	document.getElementById('paraFrm').action="NewLoanIssuedRpt_viewOnScreen.action";
	document.getElementById('paraFrm').submit();
   }
   
   	pgshow();
  	function pgshow()
  	{
	  	var pgno=document.getElementById('paraFrm_show').value;  
	  	if(!(pgno=="")){
	  	 document.getElementById('selectname').value=pgno;
	  	 }
	  	 
	  	
  	}

	
	
	
function callReportForDisp(reportType)
{
 document.getElementById('paraFrm_reportType').value=reportType;
   callReport('NewLoanIssuedRpt_report.action');    
}

function callBack(){
	document.getElementById('paraFrm_backFlag').value="true";
  	document.getElementById('paraFrm_reportType').value="1";
  
  	var sortBy = document.getElementById('paraFrm_sortBy').value;
	document.getElementById('paraFrm_hiddenSortBy').value=sortBy;
	
	var thenBy1 = document.getElementById('paraFrm_thenBy1').value;
   	document.getElementById('paraFrm_hiddenThenBy1').value=thenBy1;
   	
   	var thenBy2 = document.getElementById('paraFrm_thenBy2').value;
   	document.getElementById('paraFrm_hiddenThenBy2').value=thenBy2;
  
  	document.getElementById('paraFrm').action='NewLoanIssuedRpt_callBack.action';
  	document.getElementById('paraFrm').submit(); 
}
 
 
 
 

</script>























































































































































































