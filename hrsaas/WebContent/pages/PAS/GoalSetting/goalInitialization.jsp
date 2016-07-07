<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Goal Initialization</title>
</head>
<body>
<s:form action="GoalInitialization" id="paraFrm" theme="simple">

<s:hidden name="hiddencode" />
 
<s:hidden name="goalfromDate" />
<s:hidden name="goaltoDate" />
<s:hidden name="goalName"></s:hidden>
<s:hidden name="goalId" />
<s:hidden name="goalPublishStatus" />
<s:hidden name="paraId"/>
<s:hidden name="myPage" id="myPage"/>

	<table width="100%" cellspacing="1" cellpadding="2" border="0"
		align="right" class="formbg">
		<tbody>
			<tr>
				<td width="100%" colspan="3">
				<table width="100%" cellspacing="2" cellpadding="2" border="0"
					align="center" class="formbg">
					<tbody>
						<tr>
							<td width="4%" valign="bottom" class="txt"><strong
								class="formhead"> <img height="25" width="25"
								src="../pages/images/recruitment/review_shared.gif"></strong></td>
							<td width="93%" class="txt"><strong class="text_head">Goal  
							Initialization</strong></td>
							<td width="3%" valign="top" class="txt">
							<div align="right"><img height="16" width="16"
								src="../pages/images/recruitment/help.gif"></div>
							</td>
						</tr>
					</tbody>
				</table>
				</td>
			</tr>
			<tr>
				<td width="100%">
				<table width="100%">
					<tbody>
						<tr>
							<td width="70%"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

							<%
								int totalPage = 0;
								int PageNo = 0;
							%>
							<td width="30%" align="right" class="" id="ctrlShow"><b>Page:</b>
							 <%
 					totalPage = (Integer) request.getAttribute("totalPage");
 					PageNo = (Integer) request.getAttribute("pageNo");
 								%>
							<a
								onclick="callPage('1', 'F', '<%=totalPage%>', 'GoalInitialization_input.action');"
								href="#"> <img height="10" width="10" class="iconImage"
								src="../pages/common/img/first.gif" title="First Page"> </a>&nbsp;
							<a
								onclick="callPage('P', 'P', '<%=totalPage%>', 'GoalInitialization_input.action');"
								href="#"> <img height="10" width="10" class="iconImage"
								src="../pages/common/img/previous.gif" title="Previous Page">
							</a> <!--<input type="text"
								onkeypress="callPageText(event, '1', 'GoalInitialization_input.action');return numbersOnly();"
								maxlength="10" value='<%=PageNo%>' size="3" id="pageNoField"
								name="pageNoField"> of <%=totalPage%> 
								-->
								<input type="text" name="pageNoField" id="pageNoField" size="3" value="<%=PageNo%>" maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'GoalInitialization_input.action');return numbersOnly();" /> of <%=totalPage%>
							
								
								<a
								onclick="callPage('N', 'N', '<%=totalPage%>', 'GoalInitialization_input.action')"
								href="#"> <img class="iconImage"
								src="../pages/common/img/next.gif" title="Next Page"> </a>&nbsp;
							<a
								onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'GoalInitialization_input.action');"
								href="#"> <img height="10" width="10" class="iconImage"
								src="../pages/common/img/last.gif" title="Last Page"> </a></td>
						</tr>
						<tr>
							<td colspan="3">
							<table width="100%" cellspacing="2" cellpadding="2" border="0"
								class="formbg">
								<tbody>
									<tr>
										<td></td>
									</tr>
									<tr>
										<td width="5%" class="formth"><label class="set"
						id="srNo" name="srNo"
						onDblClick="callShowDiv(this);"><%=label.get("srNo")%></label></td>
										<td width="25%" align="left" class="formth"><label class="set"
						id="goalPeriod" name="goalPeriod"
						onDblClick="callShowDiv(this);"><%=label.get("goalPeriod")%></label></td>
										<td width="20%" class="formth"><label class="set"
						id="goalFromDate" name="goalFromDate"
						onDblClick="callShowDiv(this);"><%=label.get("goalFromDate")%></label></td>
										<td width="20%" class="formth"><label class="set"
						id="goalToDate" name="goalToDate" onDblClick="callShowDiv(this);"><%=label.get("goalToDate")%></label></td>
										<td width="20%" class="formth"><label class="set"
						id="goalStatus" name="goalStatus"
						onDblClick="callShowDiv(this);"><%=label.get("goalStatus")%></label></td>
										<td width="15%" no="" class="formth"><input type="button"
											onclick="return deleteMultiple();" class="delete"
											value=" Delete"> </td>
									</tr>
									<%
									int count = 0;
									%>
									<%
										int k = 1;
										int c = 0;
										int cn = PageNo * 20 - 20;
									%>
									<!------------- Iterator Starts --------->

									<s:iterator value="iteratorlist">

									<tr <%if(count%2==0){
									%> class="tableCell1"
										<%}else{%> class="tableCell2" <%	}count++; %>
										onmouseover="javascript:newRowColor(this);"
										title="Double click for edit"
										onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
										ondblclick="javascript:callForEdit('<s:property value="goalIdItt" />','<s:property value="goalStatusItt" />');">
 
									 
											<td class="sortableTD" width="5%"><%=++cn%><s:hidden
												name="srNo" /></td>
											<td class="sortableTD">
										 	 <s:hidden name='goalIdItt' id='<%="goalIdItt"+c%>' />
											<s:property value="goalNameItt" /></td>
											<td class="sortableTD" align="center"><s:property
												value="goalfromDateItt" /></td>
											<td class="sortableTD" align="center"><s:property
												value="goaltoDateItt" /></td>
											<td class="sortableTD" align="center"><s:property
												value="goalStatusItt" /></td>
											<td align="center" class="sortableTD" id="ctrlShow"><input
												type="checkbox" name="allChk" id="<%="chkDelete"+c%>"
												onclick="callCheck('<s:property	value="goalStatusItt" />',this)" /></td>
										 
										<%
											k++;
											c++;
										%>
										
										</tr>
									</s:iterator>
									<!------------- Iterator Ends --------->
									<%
									if (c == 0) {
									%>
									<tr align="center">
										<td colspan="6" class="sortableTD" width="100%"><font
											color="red">No Data to display</font></td>
									</tr>
									<%
									}
									%>
									
									<input type="hidden" id="count" name="count" value="<%=c%>"/>
								</tbody>
							</table>
							</td>
						</tr>
						<tr>
							<td width="70%"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						</tr>
					</tbody>
				</table>
				</td>
			</tr>
		</tbody>
	</table>
</s:form>
</body>
</html>

<script type="text/javascript">

	function addnewFun()
	{	
		document.getElementById("paraFrm").target='_self';
		document.getElementById("paraFrm").action='GoalInitialization_addNew.action';
		document.getElementById("paraFrm").submit();
	}

	function searchFun() {
			if(navigator.appName == 'Netscape') {
				var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
			} else {
				var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
			}
			document.getElementById("paraFrm").target = 'myWin';
			document.getElementById("paraFrm").action = 'GoalInitialization_f9action.action';
			document.getElementById("paraFrm").submit();
	}

	function reportFun() {
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = 'GoalInitialization_report.action';
		document.getElementById('paraFrm').submit();
	}
	
	function callCheck(status,obj){
   			if(status=="Published"){
   					alert("Published goals can not be deleted");
   					obj.checked=false;
   					return false;
   				}
		}
		
	function deleteMultiple(){
		var count =document.getElementById('count').value;
		
		var param="";
		var checked =false;
		for(var check=0;check <count; check++){
			if(document.getElementById('chkDelete'+check).checked){
				if(check == eval(count-1)){
				param += document.getElementById('goalIdItt'+check).value ;
			}else{
				param += document.getElementById('goalIdItt'+check).value+"," ;
			}
			checked = true;
		}
	}
			if(!checked){
				alert("Select minimum one goal to delete");
				return false;
			}else{
				document.getElementById('paraFrm_paraId').value=param;
				
				var conf = confirm("Do you really want to delete this record?");
  				if(conf) {
	      				document.getElementById('paraFrm').action = 'GoalInitialization_multipleDelete.action';
						document.getElementById('paraFrm').submit();
				}
			}
			
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
	
	
	function callForEdit(autoCode,status){
	 callButton('NA', 'Y', 2);
	 	document.getElementById("paraFrm").action="GoalInitialization_callforedit.action?autoCode="+autoCode+"&status="+status; 
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
		 
	}
	
	
	
	
</script>