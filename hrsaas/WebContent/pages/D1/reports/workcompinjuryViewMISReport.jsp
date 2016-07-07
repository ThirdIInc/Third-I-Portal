<!--@author Nilesh Dhandare on 18th May 11-->
<%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="WorkCompInjuryMISReport" validate="true" id="paraFrm"
	theme="simple">

	<!-- hidden variables -->

	<s:hidden name="reportId" />
	<s:hidden name="divName" />
	<s:hidden name="divId" />
	<s:hidden name="deptName" />
	<s:hidden name="deptId" />
	<s:hidden name="branchName" />
	<s:hidden name="branchId" />
	<s:hidden name="empTypeName" />
	<s:hidden name="empTypeId" />
	<s:hidden name="desigName" />
	<s:hidden name="desigId" />
	<s:hidden name="empToken" />
	<s:hidden name="empName" />
	<s:hidden name="empId" />

	<s:hidden name="managerName" />
	<s:hidden name="managerId" />
	<s:hidden name="trackingNumber" />
	<s:hidden name="trackingId" />
	<s:hidden name="empstatus" />
	<s:hidden name="applStatus" />




	<s:hidden name="empNameFlag" />
	<s:hidden name="divFlag" />
	<s:hidden name="deptFlag" />
	<s:hidden name="branchFlag" />
	<s:hidden name="desigFlag" />
	<s:hidden name="empTypeFlag" />

	<!-- New Flags -->

	<s:hidden name="managerNameFlag" />
	<s:hidden name="trackingNumberFlag" />
	<s:hidden name="aaaaaFlag" />
	<s:hidden name="timeofInjuryFlag" />
	<s:hidden name="cityFlag" />

	<s:hidden name="numberofDependantFlag" />
	<s:hidden name="workinghoursToFlag" />
	<s:hidden name="injuryDateFlag" />
	<s:hidden name="hoursWorkedFlag" />

	<s:hidden name="normalworkingHoursFlag" />
	<s:hidden name="injueredreturnFlag" />
	<s:hidden name="disebilityFlag" />
	<s:hidden name="lossworkDaysFlag" />
	<s:hidden name="lostFlag" />
	<s:hidden name="applDateFlag" />
	<s:hidden name="applStatusFlag" />
	<s:hidden name="statusFlag" />


	<!--Sort by-->
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
	<s:hidden name="thenByOrder2Dsc" />
	<s:hidden name="thenByOrder2" />

	<s:hidden name="hiddenColumns" />

	<s:hidden name="hidReportView" />
	<s:hidden name="hidReportRadio" />
	<s:hidden name="reportType" />
	<s:hidden name="reportView" />

	<s:hidden name="reportTitle" />
	<s:hidden name="settingName" />

	<s:hidden name="myPage" />
	<s:hidden name="show" />

	<s:hidden name="backFlag" />

	<!-- Added new  -->

	<s:hidden name="appliDateSelect" />
	<s:hidden name="appliFromDate" />
	<s:hidden name="appliToDate" />


	<s:hidden name="injuryDateSelect" />
	<s:hidden name="injuryFromDate" />
	<s:hidden name="injuryToDate" />

	<s:hidden name="approvalDateSelect" />
	<s:hidden name="approvalFromDate]" />
	<s:hidden name="approvalToDate" />


	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<!-- TABLE 1 -->
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<!-- TABLE 2 -->
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">
					Work Comp Injury Illness Mis Report</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			<!-- TABLE 2 --></td>
		</tr>

		<!-- BUTTON PANEL AND CHECK BOX BEGINS -->
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<!-- TABLE3 -->
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
			<!-- TABLE3 --></td>
		</tr>
		<!-- BUTTON PANEL AND CHECK BOX ENDS -->

		<!-- REPORT PART BEGINS -->
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<!-- TABLE4 -->
				<!-- PAGING BEGINS -->
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<!-- TABLE5 -->
						<tr>
							<td height="27" class="text_head"><strong>Termination
							MIS Report </strong></td>
							<%
								int totalPage = (Integer) request.getAttribute("totalPage");
								int pageNo = (Integer) request.getAttribute("PageNo");
								System.out.println("pageNo :"+pageNo);
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
					</table>
					<!-- TABLE5 --></td>
				</tr>
				<!-- PAGING ENDSS -->
				<!-- REPORT TABLE BEGINS -->
				<tr>
					<td width="100%">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="sortable">
						<tr>
							<td width="5%" valign="top" class="formth" nowrap="nowrap"><label
								class="set" name="serial.no" id="serial.no"
								ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
							<%
							String labelValues = (String)request.getAttribute("labelValues");
							//System.out.println("labels  : "+labelValues);
							if(!labelValues.equals("") && labelValues.length()>0){
								labelValues = labelValues.substring(0,
										labelValues.length() - 1);
								//System.out.println("labels  : "+labelValues);
								String[] splitLabels = labelValues.split(",");
								for(int i=0; i < splitLabels.length; i++){
									//System.out.println("labels  : "+splitLabels[i]);
								
							%>
							<td width="<%=(100/splitLabels.length)%>%" valign="top"
								class="formth" nowrap="nowrap"><%= splitLabels[i]%></td>

							<%}
							} %>

							<%
							int cnt = 0;
							int pgNo=pageNo * 20 - 20;
							%>
							<%
								Object[][] finalObj = (Object[][])request.getAttribute("finalObj");
								if(finalObj!=null && finalObj.length>0){
									//System.out.println("finalObj.length  :"+finalObj.length);
									for(int i=0; i < finalObj.length; i++){
							%>
						
						<tr>
							<td width="5%" class="sortableTD" nowrap="nowrap">
							<%
									++cnt;
									%> <%=++pgNo%></td>
							<%for(int j=1; j <= finalObj[0].length-1; j++){
										//System.out.println("query values["+i+"]["+(j-1)+"]  : "+String.valueOf(finalObj[i][j-1])); %>
							<td width="25%" class="sortableTD">&nbsp;<%= String.valueOf(finalObj[i][j-1])%></td>
							<% } %>
						</tr>
						<%	
									}
								} %>


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
						</tr>
					</table>
					</td>
				</tr>
				<!-- REPORT TABLE ENDS -->
			</table>
			<!-- TABLE4 --></td>
		</tr>

		<!-- REPORT PART ENDS -->

		<!-- BUTTON PANEL AND CHECK BOX BEGINS -->
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<!-- TABLE6 -->
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
			<!-- TABLE6 --></td>
		</tr>
		<!-- BUTTON PANEL AND CHECK BOX ENDS -->
	</table>
	<!-- TABLE 1 -->
</s:form>

<script type="text/javascript">

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
	   		alert(e);
	   	}
	}
	function callReportForDisp(reportType)
	{
		 document.getElementById('paraFrm_reportType').value=reportType;
		 callReport('WorkCompInjuryMISReport_report.action');    
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

	function callPage(id,pageImg){ 
	try{ 
 	 pageNo =document.getElementById('pageNoField').value;	
 	 totalPage =document.getElementById('totalPage').value;	 
 	 var actPage = document.getElementById('paraFrm_myPage').value; 
	 if(pageNo!="0"  & pageNo < totalPage){	
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
		  if(Number(totalPage)< Number(pageNo))
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
		document.getElementById('paraFrm').action='WorkCompInjuryMISReport_viewOnScreen.action';
		document.getElementById('paraFrm').submit(); 
		}catch(e){
			alert("error - "+e);
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
		   
			document.getElementById('paraFrm').action='WorkCompInjuryMISReport_viewOnScreen.action';
			document.getElementById('paraFrm').submit();
		}
		
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
	  
	  	document.getElementById('paraFrm').action='WorkCompInjuryMISReport_callBack.action';
	  	document.getElementById('paraFrm').submit(); 
	}	
</script>