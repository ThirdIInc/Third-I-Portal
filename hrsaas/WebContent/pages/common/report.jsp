<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="org.paradyne.lib.report.HTMLGenerator"%>
<%@page import="com.lowagie.text.Table"%>
<%@page import="com.lowagie.text.html.HtmlWriter"%>
<%@page import="com.lowagie.text.html.HtmlTags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<s:form theme="simple" id="paraFrm" name="paraFrm">
	<s:hidden name="recordCount" value="%{recordCount}" />
	<s:hidden name="recordCount1" value="%{recordCount1}" />
	<s:hidden name="previousFlag" value="%{previousFlag}" />
	<s:hidden name="hdF9SearchIndex" value="%{hdF9SearchIndex}" />
	<s:hidden name="hdPage" value="%{hdPage}" />
	<s:hidden name="hdadvanced" value="%{hdadvanced}" />
	<s:hidden name="rowId" value="%{rowId}" />
	<s:hidden name="f9query" />
	<s:hidden name="reqIndex" />
	<s:hidden name="reqCode" />
	<s:hidden name="candidateCode" />
	<s:hidden name="rankId" />
	<s:hidden name="frmDate" />
	<s:hidden name="toDate" />
	<s:hidden name="type" />
	<s:hidden name="flag" />
	<s:hidden name="requisitionCode" />
	<%
		String[] width = (String[]) request.getAttribute("headerWidth");
		String[] heads = (String[]) request.getAttribute("headerNames");
		String[] heads1 = (String[]) request.getAttribute("headerNames1");
		int pages = (Integer) request.getAttribute("pages");
		int pages1 = (Integer) request.getAttribute("pages1");
		int PageNo = (Integer) request.getAttribute("PageNo");
		int PageNo1 = (Integer) request.getAttribute("PageNo1");
		int totalPage = (Integer) request.getAttribute("TOTAL_RECORDS");
		int totalPage1 = (Integer) request.getAttribute("TOTAL_RECORDS1");
		String reporttitle = (String) request.getAttribute("reporttitle");
	%>
	
	<table class="formbg" width="100%">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="1" class="formbg" >
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head"><%=reporttitle%></strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/common/css/default/images/help.gif" width="16"
						height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" width="100%" >
			<table width="100%" border="0" cellspacing="0" cellspacing="1" class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" cellspacing="1" cellspacing="0">
						<tr>
							<td width="100%"><s:submit cssClass="reset"
								action="CandidateStatusReport_report" onclick="return funPDF();"
								theme="simple" value="Export to Pdf"  /> <s:submit
								cssClass="reset" action="CandidateStatusReport_report"
								onclick="return funXls();" theme="simple" value="Export to Xls"
								 /> <s:submit cssClass="reset"
								action="CandidateStatusReport_report" onclick="return funDoc();"
								theme="simple" value="Export to Doc"  />
								<s:submit cssClass="back"
								action="CandidateStatusReport_input" 
								theme="simple" value="Back"  />
								</td>

						</tr>
						<tr><td>&nbsp;</td></tr>
					</table>
					</td>
				</tr>


				<tr>
					<td>
					<table cellspacing="0" cellspacing="1" width="100%" border="0" class="formbg">

						<tr>
							<td>

							<div class="scrollReport1" id="scrollDiv1">
							<%
										String[] displayData1 = (String[]) request
										.getAttribute("displayData1");
								int colNum1 = heads1.length;
								String[] fields1 = (String[]) request.getAttribute("fieldNames1");
								//System.out.println("colNum1 length....."+fields1.length);
								String[][] obj1 = (String[][]) request.getAttribute("f9Data1");
								int[] colIndex1 = (int[]) request
										.getAttribute("columnIndexForRequi");
								//System.out.println("displayData1.length........"+displayData1.length);
								String Req = (String) request.getAttribute("Req");
								String candidateCode = (String) request
										.getAttribute("candidateCode");
								//System.out.println("candidateCode......."+candidateCode);
								String rankId = (String) request.getAttribute("rankId");
								//System.out.println("rankId......."+rankId);
								String fromDate = (String) request.getAttribute("frmDate");
								String toDate = (String) request.getAttribute("toDate");
								String type = (String) request.getAttribute("type");
								String subtitle = (String) request.getAttribute("subtitle");
							%>
							
							<td align="left" class="iconImage"><b>Total Number Of
							Requisitions :<%=totalPage1%></b></td>

							<td align="right"><b>Pages:</b> <%
 if (!(PageNo1 == 1)) {
 %> <a href="#"
								onclick="callPage1('1','<%=Req%>','<%=candidateCode%>','<%=rankId%>','<%=fromDate%>','<%=toDate%>','<%=type%>');">
							<img src="../pages/common/img/first.gif" width="10" height="10"
								class="iconImage" /> </a>&nbsp; <a href="#" onclick="previous1()">
							<img src="../pages/common/img/previous.gif" width="10"
								height="10" class="iconImage" /> </a> <%
 	}
 	if (pages1 <= 5) {
 		for (int z1 = 1; z1 <= pages1; z1++) {
 %>&nbsp; <a href="#"
								onclick="callPage1('<%=z1 %>','<%=Req%>','<%=candidateCode%>','<%=rankId%>','<%=fromDate%>','<%=toDate%>','<%=type%>');">
							<%
							if (PageNo1 == z1) {
							%> <b><u><%=z1%></u></b> <%
 } else {
 %><%=z1%> </a> <%
 		}
 		}
 	} else {
 		if (PageNo1 == pages1 - 1 || PageNo1 == pages1) {
 			for (int z1 = PageNo1 - 2; z1 <= pages1; z1++) {
 %>&nbsp;<a href="#"
								onclick="callPage1('<%=z1 %>','<%=Req%>','<%=candidateCode%>','<%=rankId%>','<%=fromDate%>','<%=toDate%>','<%=type%>');">
							<%
							if (PageNo1 == z1) {
							%><b><u><%=z1%></u></b> <%
 } else {
 %><%=z1%></a> <%
 			}
 			}
 		} else if (PageNo1 <= 3) {
 			for (int z1 = 1; z1 <= 5; z1++) {
 %>&nbsp;<a href="#"
								onclick="callPage1('<%=z1 %>','<%=Req%>','<%=candidateCode%>','<%=rankId%>','<%=fromDate%>','<%=toDate%>','<%=type%>');">
							<%
							if (PageNo1 == z1) {
							%><b><u><%=z1%></u></b> <%
 } else {
 %><%=z1%></a> <%
 			}
 			}
 		} else if (PageNo1 > 3) {
 			for (int z1 = PageNo1 - 2; z1 <= PageNo1 + 2; z1++) {
 %>&nbsp;<a href="#"
								onclick="callPage1('<%=z1 %>','<%=Req%>','<%=candidateCode%>','<%=rankId%>','<%=fromDate%>','<%=toDate%>','<%=type%>');">
							<%
							if (PageNo1 == z1) {
							%><b><u><%=z1%></u></b> <%
 } else {
 %><%=z1%></a> <%
 			}
 			}
 		}
 	}
 	if (!(PageNo1 == pages1)) {
 		if (pages1 == 0) {

 		} else {
 %>...<%=pages1%><a href="#" onclick="next1()"> <img
								src="../pages/common/img/next.gif" class="iconImage" /> </a>&nbsp;<a
								href="#"
								onclick="callPage1('<%=pages1%>','<%=Req%>','<%=candidateCode%>','<%=rankId%>','<%=fromDate%>','<%=toDate%>','<%=type%>');">
							<img src="../pages/common/img/last.gif" width="10" height="10"
								class="iconImage" /></a> <%
 	}
 	}
 %></div>
							</td>
							<s:hidden name="pageNo1" />
					</table>
					</td>
				</tr>
				<tr><td></td></tr>
				<tr>
					<td>
					<table width="100%" border="0" cellspacing="1" cellspacing="0"
						class="formbg">

						<%
							String valueRequi = "";
							//System.out.println("object length"+obj1.length);
							if (obj1.length > 0) {
								for (int j = 0; j < obj1.length; j++) {
									//String[][] temp = obj1;
									//displayData1 = actValue1.split(",");
									//System.out.println("fields1......."+fields1.length);

									for (int k = 0; k < fields1.length; k++) {

								valueRequi += obj1[j][k] + ",";
								//System.out.println("valueRequi....."+valueRequi);

									}
									String actValue1 = valueRequi.substring(0, valueRequi
									.length());
									//System.out.println("actValue1......."+actValue1);
									String[] displayData2;
									displayData2 = actValue1.split(",");
									//System.out.println("displayData2......"+displayData2);
						%>

						<%
									//System.out.println("displayData2.length....."+displayData2.length);
									int i1 = 0;
									while (i1 < displayData2.length) {

								int j1 = 0;
								while (j1 < fields1.length) {
						%>

						<tr>
							<td width="20%"><%=heads1[j1]%>:</td>
							<td><%=displayData2[i1]%></td>

							<%
										//System.out.println("i1..."+i1);
										//System.out.println("displayData2[i1]........"+displayData2[i1]);
							%>
						</tr>

						<%
									j1++;
									i1 = i1 + 1;
								}
						%>

						<tr>
							<td></td>
						</tr>

						<%
								}
								}
							} else {
						%>
						<tr>
							<td></td>
							<td width="100%" align="center"><font color="red">There
							is no data to display</font></td>
						</tr>
						<%
						}
						%>
					</table>
					</td>
				</tr>


				<tr>
					<td>

					<table width="100%" border="0" cellspacing="1" cellspacing="0" class="formbg">
						<tr>
							<td align="left" class="iconImage"><b>Total Number Of <%=subtitle%>
							:<%=totalPage%></b></td>
							<td align="right"><b>Pages:</b> <%
 if (!(PageNo == 1)) {
 %> <a href="#" onclick="callPage('1');"> <img
								src="../pages/common/img/first.gif" width="10" height="10"
								class="iconImage" /> </a>&nbsp; <a href="#" onclick="previous()">
							<img src="../pages/common/img/previous.gif" width="10"
								height="10" class="iconImage" /> </a> <%
 	}
 	if (pages <= 5) {
 		for (int z = 1; z <= pages; z++) {
 %>&nbsp; <a href="#" onclick="callPage('<%=z %>');"> <%
 if (PageNo == z) {
 %> <b><u><%=z%></u></b> <%
 } else {
 %><%=z%> </a> <%
 		}
 		}
 	} else {
 		if (PageNo == pages - 1 || PageNo == pages) {
 			for (int z = PageNo - 2; z <= pages; z++) {
 %>&nbsp;<a href="#" onclick="callPage('<%=z %>');"> <%
 if (PageNo == z) {
 %><b><u><%=z%></u></b> <%
 } else {
 %><%=z%></a> <%
 			}
 			}
 		} else if (PageNo <= 3) {
 			for (int z = 1; z <= 5; z++) {
 %>&nbsp;<a href="#" onclick="callPage('<%=z %>');"> <%
 if (PageNo == z) {
 %><b><u><%=z%></u></b> <%
 } else {
 %><%=z%></a> <%
 			}
 			}
 		} else if (PageNo > 3) {
 			for (int z = PageNo - 2; z <= PageNo + 2; z++) {
 %>&nbsp;<a href="#" onclick="callPage('<%=z %>');"> <%
 if (PageNo == z) {
 %><b><u><%=z%></u></b> <%
 } else {
 %><%=z%></a> <%
 			}
 			}
 		}
 	}
 	if (!(PageNo == pages)) {
 		if (pages == 0) {

 		} else {
 %>...<%=pages%><a href="#" onclick="next()"> <img
								src="../pages/common/img/next.gif" class="iconImage" /> </a>&nbsp;<a
								href="#" onclick="callPage('<%=pages%>');"> <img
								src="../pages/common/img/last.gif" width="10" height="10"
								class="iconImage" /></a> <%
 	}
 	}
 %>
							</td>
						</tr>
					</table>
					<s:hidden name="pageNo" /></td>
				</tr>

				<tr>
					<td width="100%">

					<div class="scrollReport" id="scrollDiv" >


					<table cellspacing="0" cellspacing="1" border="0" width="100%"
						class="sortable">
						<%
									String[] displayData = (String[]) request
									.getAttribute("displayData");
							int colNum = heads.length;
							String fields = (String) request.getAttribute("fieldNames");
							String[][] obj = (String[][]) request.getAttribute("f9Data");
							int[] colIndex = (int[]) request.getAttribute("columnIndex");
						%>
						<tr>
							<%
								int i = 0;
								while (i < colNum) {
							%>
							<td class="formth" width="<%=width[i]%>%"><%=heads[i]%></td>
							<%
								i++;
								}
							%>
							<td class="formth" width="10%"><label class="set"
								name="view.eval" id="view.eval" ondblclick="callShowDiv(this);"><%=label.get("view.eval")%></label></td>
						</tr>

						<%
								if (obj.length > 0) {
								for (int k = 0; k < obj.length; k++) {
						%>
						<tr>
							<%
										int j = 0;
										while (j < colNum) {
							%>
							<td class="sortableTD"><%=obj[k][j]%>&nbsp;</td>
							<%
										j++;
										}
							%>
							<td class="sortableTD"><input type="button" value="View"
								class="token"
								onclick="viewInterviewDetails('<%=obj[k][colNum]%>','<%=obj[k][colNum+1] %>')" />&nbsp;</td>

						</tr>
						<%
							}
							} else {
						%>
						<tr>
							<td></td>
							<td width="100%" align="center"><font color="red">There
							is no data to display</font></td>
						</tr>
						<%
						}
						%>
					</table>
					</div>
					</td>
				</tr>
				<tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr>
				<tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr>
				<tr><td></td></tr><tr><td></td></tr><tr><td></td></tr>
				<tr>
					<td>
					<table width="100%" border="0" cellspacing="1" cellspacing="0">
						<tr>
							<td width="100%"><s:submit cssClass="reset"
								action="CandidateStatusReport_report" onclick="return funPDF();"
								theme="simple" value="Export to Pdf"  /> <s:submit
								cssClass="reset" action="CandidateStatusReport_report"
								onclick="return funXls();" theme="simple" value="Export to Xls"
								 /> <s:submit cssClass="reset"
								action="CandidateStatusReport_report" onclick="return funDoc();"
								theme="simple" value="Export to Doc"  />
								<s:submit cssClass="back"
								action="CandidateStatusReport_input" 
								theme="simple" value="Back"  />
								</td>

						</tr>
						<tr><td>&nbsp;</td></tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
			</tr>			
			</table>
</s:form>

<script>	
	
    function next() {
    	//document.getElementById('paraFrm_previousFlag').value=false;
    	var idPage=document.getElementById('paraFrm_pageNo').value;
    	if(idPage==""){idPage=1}
    	document.getElementById('paraFrm_recordCount').value=(idPage)*20+20;
	    document.getElementById('paraFrm_pageNo').value=eval(idPage)+1;
    	var idPage11=document.getElementById('paraFrm_pageNo').value;
    	document.getElementById('paraFrm').submit();   	    	
    }
        
    function previous() {
    	var idPage=document.getElementById('paraFrm_pageNo').value;
    	document.getElementById('paraFrm_recordCount').value=(idPage-2)*20+20;
	    document.getElementById('paraFrm_pageNo').value=eval(idPage)-1;
    	document.getElementById('paraFrm').submit();
	}
    
    
    function callPage(id) {    
    	document.getElementById('paraFrm_pageNo').value=id;
	   	document.getElementById('paraFrm_recordCount').value=(id-1)*20+20;
	   	document.getElementById('paraFrm').submit();
   	}
   	
     function previous1() {
    	var idPage1=document.getElementById('paraFrm_pageNo1').value;
    	document.getElementById('paraFrm_recordCount1').value=(idPage1-2)*1+1;
	    document.getElementById('paraFrm_pageNo1').value=eval(idPage1)-1;
	    document.getElementById('paraFrm_flag').value = ""; 
    	document.getElementById('paraFrm').submit();
	}
    
    
    function callPage1(id,Req,candidateCode,rankId,fromDate,toDate,type) {    	
    	document.getElementById('paraFrm_pageNo1').value=id;
	   	document.getElementById('paraFrm_recordCount1').value=(id-2)*1+1;
	   	document.getElementById('paraFrm_reqIndex').value=id;
	   	document.getElementById('paraFrm_reqCode').value=Req;
	   	document.getElementById('paraFrm_candidateCode').value=candidateCode;
	   	document.getElementById('paraFrm_rankId').value=rankId;
	   	document.getElementById('paraFrm_frmDate').value=fromDate;
	   	document.getElementById('paraFrm_toDate').value=toDate;
	   	document.getElementById('paraFrm_type').value=type;
	   	document.getElementById('paraFrm_flag').value = ""; 
	   	document.getElementById('paraFrm').submit();
   	}
   	
   	function next1() {
    	//document.getElementById('paraFrm_previousFlag').value=false;
    	var idPage1=document.getElementById('paraFrm_pageNo1').value;
    	if(idPage1==""){idPage1=1}
    	document.getElementById('paraFrm_recordCount1').value=(idPage1)*1+1;
	    document.getElementById('paraFrm_pageNo1').value=eval(idPage1)+1;
    	var idPage111=document.getElementById('paraFrm_pageNo1').value;
    	document.getElementById('paraFrm_flag').value = ""; 
    	document.getElementById('paraFrm').submit();   	    	
    }
    
    function funPDF()
    {
    	document.getElementById('paraFrm_flag').value = "Pdf"; 
    	
    }
    function funXls()
    {
    	document.getElementById('paraFrm_flag').value = "Xls"; 
    	
    }
    function funDoc()
    {
    	document.getElementById('paraFrm_flag').value = "Txt"; 
    	
    }
    
     function viewInterviewDetails(candCode,dupreqCode){
   		//var dupreqCode=document.getElementById('paraFrm_reqCode').value;
		window.open('InterviewDetails_showConductedIntDetails.action?reqCode='+dupreqCode+'&candCode='+candCode,'','top=100,left=200,resizable=yes,scrollbars=yes,width=700,height=400');
	}
    
    self.focus();    
	
</script>