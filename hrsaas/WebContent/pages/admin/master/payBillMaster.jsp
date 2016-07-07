<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<script type="text/javascript">
var records = new Array();
</script>

<s:form action="PayBillMaster" validate="true" id="paraFrm"
	name="paraFrm" theme="simple">
<s:hidden name="reportAction" value='PayBillMaster_generateReport.action' />
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />
	<s:hidden name="report" />
	<s:hidden name="isNotGeneralUser" />
	<s:set name="updateFlag" value="updateFlag" />
	<s:set name="deleteFlag" value="deleteFlag" />
	<s:set name="insertFlag" value="insertFlag" />
	<s:set name="viewFlag" value="viewFlag" />

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
					<td width="93%" class="txt"><strong class="text_head">Pay
					Bill </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="4" width="100%">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">
				<tr>
					<td colspan="4">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td colspan="1" width="50%"><s:if test="insertFlag">
								<s:submit cssClass="add" action="PayBillMaster_save"
									theme="simple" value="   Add New"
									onclick="return callsave('addnew'); " />
							</s:if> <s:if test="updateFlag">
								<s:submit cssClass="edit" action="PayBillMaster_save"
									theme="simple" value="   Update"
									onclick="return callsave('update'); " />
							</s:if><s:if test="viewFlag">
								<input type="button" class="search" value="    Search "
									onclick="callSearch();" />
							</s:if> <s:submit cssClass="reset" action="PayBillMaster_reset"
								theme="simple" value="    Reset" /><!--<s:if
								test="deleteFlag">
								
								<s:submit cssClass="delete" action="PayBillMaster_delete"
									theme="simple" value="    Delete"
									onclick="return callDelete('paraFrm_payID');" />
							</s:if></td>-->
							
							<td colspan="2" width="40%" align="left"><%@ include
						file="/pages/common/reportButtonPanel.jsp"%></td>
						<td colspan="1" width="10%">
							<div align="right"><font color="red">*</font>Indicates Required</div>
							</td>	
						</tr>
					</table>
					<label></label></td>
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
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="98%" border="0" align="center" cellpadding="0"
								cellspacing="2">
								<tr>
									<td colspan="5" class="formhead"><strong
										class="forminnerhead">Pay Bill</strong></td>
									<s:hidden name="payID"></s:hidden>

								</tr>
								<tr>
									<td height="22" width="15%" class="formtext"><label
										class="set" name="paybill.group" id="paybill.group"
										ondblclick="callShowDiv(this);"><%=label.get("paybill.group")%></label>
									<font color="red">*</font> :</td>
									<td width="25%" height="22"><s:textfield name="payGrp"
										size="35" maxlength="25" onkeypress="return allCharacters();"></s:textfield></td>
									<td width="1%" height="22">&nbsp;</td>
									<td width="24%" class="formtext">&nbsp;</td>
									<td width="25%">&nbsp;</td>

								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="3">

					<table width="100%" border="0" cellpadding="0" cellspacing="0">

						<tr>
						<td colspan="1" width="80%">
							<td colspan="1" width="15%" align="right"><b>Page:</b>
							
							<%
								int total1 = (Integer) request.getAttribute("abc");
								int PageNo1 = (Integer) request.getAttribute("xyz");
							%>
							<%
							if (!(PageNo1 == 1)) {
							%><a href="#" onclick="callPage('1');"> <img
								src="../pages/common/img/first.gif" width="10" height="10"
								class="iconImage" /> </a>
							&nbsp;
							<a href="#" onclick="previous()"><img
								src="../pages/common/img/previous.gif" width="10" height="10"
								class="iconImage" /></a>
							<%
								}
								if (total1 < 5) {
									for (int i = 1; i <= total1; i++) {
							%>
							<a href="#" onclick="callPage('<%=i %>');"> <%
 if (PageNo1 == i) {
 %> <b><u><%=i%></u></b> <%
 } else
 %><%=i%> </a>
							<%
								}
								}

								if (total1 >= 5) {
									for (int i = 1; i <= 5; i++) {
							%>
							<a href="#" onclick="callPage('<%=i %>');"> <%
 if (PageNo1 == i) {
 %> <b><u><%=i%></u></b> <%
 } else
 %><%=i%> </a>
							<%
								}
								}
								if (!(PageNo1 == 1)) {
							%>...
							<a href="#" onclick="next()"> <img
								src="../pages/common/img/next.gif" class="iconImage" /> </a>
							&nbsp;
							<a href="#" onclick="callPage('<%=total1%>');"> <img
								src="../pages/common/img/last.gif" width="10" height="10"
								class="iconImage" /></a>
							<%
							}
							%>
							<select name="selectname" onchange="on()" id="selectname">
								<%
								for (int i = 1; i <= total1; i++) {
								%>

								<option value="<%=i%>"><%=i%></option>
								<%
								}
								%>
							</select>
							</td>
							<s:if test="deleteFlag">
							<td colspan="1" width="5%" align="right" class="formth" nowrap="nowrap"><s:submit
								cssClass="delete" theme="simple" value="     Delete  "
								onclick=" return chkDelete()" /></td></s:if>
						</tr>



					</table>
					</td>
				</tr>
				<tr>
					<td colspan="3">
					<%
					try {
					%>
					<table width="100%" border="0" cellpadding="0" cellspacing="1"
						class="formbg">

						<tr>

							<td class="formtext">
							<table width="100%" border="0" cellpadding="1" cellspacing="1">
								<tr>
									<s:hidden name="myPage" id="myPage" />
									<td class="formth" align="center"><label class="set"
										name="paybill.srno" id="paybill.srno"
										ondblclick="callShowDiv(this);"><%=label.get("paybill.srno")%></label>
									</td>
									<td class="formth" align="center"><label class="set"
										name="paybill.group" id="paybill.group1"
										ondblclick="callShowDiv(this);"><%=label.get("paybill.group")%></label>
									</td>

									<s:if test="modeLength">
										<td align="right" class="formth" id="ctrlShow" nowrap="nowrap"
											width="6%" colspan="1"><input type="checkbox"
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
										%>
										<s:iterator value="iteratorlist">

											<tr <%if(count%2==0){
									%> class="tableCell1"
												<%}else{%> class="tableCell2" <%	}count++; %>
												onmouseover="javascript:newRowColor(this);"
												title="Double click for edit"
												onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
												ondblclick="javascript:callForEdit('<s:property value="payID" />');">


												<td width="10%" align="left"><%=++i%></td>
												<s:hidden value="%{payID}" id='<%="payID" + i%>'></s:hidden>
                                                 <script type="text/javascript">records[<%=i%>] = document.getElementById('payID' + '<%=i%>').value;
												</script>
												<td width="90%" align="left"><s:property value="payGrp" />
												<input type="hidden" name="hdeleteCode"
													id="hdeleteCode<%=i%>" /></td>
													
													
													
												<td align="center" nowrap="nowrap"><input
													type="checkbox" class="checkbox" id="confChk<%=i%>"
													name="confChk"
													onclick="callForDelete1('<s:property value="payID" />','<%=i%>')" /></td>
											</tr>

										</s:iterator>
										<%
										d = i;
										%>
								</s:if>

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
			<td colspan="4">
			<table align="left" width="100%" cellpadding="0" cellspacing="0"
				id='topButtonTable'>
				<tr>
				<td colspan="1" width="50%"></td>
				<td colspan="2" width="40%"><%@ include	file="/pages/common/reportButtonPanel.jsp"%></td>
					<td colspan="1" width="10%" ></td>
				
				</tr>
				
			</table>
			</td>
		</tr>
		
	</table>
</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js">

</script>
<script>

function callReport(type){
		document.getElementById('paraFrm_report').value=type;
		callReportCommon(type);
		}
	
	
	function mailReportFun(type)
{
		
			document.getElementById('paraFrm_report').value=type;
			document.getElementById('paraFrm').action='PayBillMaster_mailReport.action';
			document.getElementById('paraFrm').submit();
			
		
	}


function selectAllRecords(object) {
///alert("ctrlShow-----"+object);
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




function callsave(type)
 {
 

var fieldName = ["paraFrm_payGrp"];
var lableName = ["paybill.group"];
var fieldName1 = ["paraFrm_payGrp"];
var badflag=["enter"];


if(type == 'update'){
		if(document.getElementById('paraFrm_payID').value==""){
			alert("Please select a record to update ");
  			return false;
			}
		}
	else 
	{
		if(!document.getElementById('paraFrm_payID').value==""){
			alert("Please Click Update ");
  			return false;
			}
		
	}


   if(!validateBlank(fieldName,lableName,badflag))
          return false;
        
      if(!f9specialchars(fieldName1))
      {
              return false;
       }
      document.getElementById('paraFrm').target = "_self";
     	    return true;
}


	
   function callPage(id){
	   	document.getElementById('myPage').value=id;
	document.getElementById('paraFrm_show').value=id;
	    document.getElementById('paraFrm').action="PayBillMaster_callPage.action";
	    document.getElementById('paraFrm').target = "_self";
	   document.getElementById('paraFrm').submit();
   }
   
   
   function next()
   {
   var pageno=	document.getElementById('myPage').value;
   	if(pageno=="1")
   	{	document.getElementById('myPage').value=2;
	 document.getElementById('paraFrm_show').value=2;
	 }
	 else{
	 document.getElementById('myPage').value=eval(pageno)+1;
	 document.getElementById('paraFrm_show').value=eval(pageno)+1;
	 }
	   document.getElementById('paraFrm').action="PayBillMaster_callPage.action";
	   document.getElementById('paraFrm').target = "_self";
	   document.getElementById('paraFrm').submit();
   }
  	
  	
  	
  	//-----function for previous
  	 function previous()
   {
   var pageno=	document.getElementById('myPage').value;
   	
	 document.getElementById('myPage').value=eval(pageno)-1;
	 document.getElementById('paraFrm_show').value=eval(pageno)-1;
	   document.getElementById('paraFrm').PayBillMaster="Action_callPage.action";
	   document.getElementById('paraFrm').target = "_self";
	   document.getElementById('paraFrm').submit();
   }
  	function on()
   {
  	var val= document.getElementById('selectname').value;
	document.getElementById('paraFrm_show').value=val;
	 document.getElementById('myPage').value=eval(val);
	 document.getElementById('selectname').value=val;
	   document.getElementById('paraFrm').action="PayBillMaster_callPage.action";
	   document.getElementById('paraFrm').target = "_self";
	   document.getElementById('paraFrm').submit();
   }
  	
  
  	
  	pgshow();
  	function pgshow()
  	{
  	var pgno=document.getElementById('paraFrm_show').value;
  
  	if(!(pgno==""))
  	 document.getElementById('selectname').value=pgno;
  	}



	function callForEdit(id){
	  	document.getElementById('paraFrm_hiddencode').value=id;
	   	document.getElementById("paraFrm").action="PayBillMaster_calforedit.action";
	   //	document.getElementById("paraFrm").target="main";
	       document.getElementById('paraFrm').target = "_self";
	    document.getElementById("paraFrm").submit();
   }
    function callForDelete(id){
	  	document.getElementById('paraFrm_hiddencode').value=id;
	   	document.getElementById("paraFrm").action="PayBillMaster_calfordelete.action";
	   	//document.getElementById("paraFrm").target="main";
	   	document.getElementById('paraFrm').target = "_self";
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
			cell.className='onOverCell';

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
	   document.getElementById('paraFrm').action="PayBillMaster_delete1.action";
	   document.getElementById('paraFrm').target = "_self";
	    document.getElementById('paraFrm').submit();
	    }
	     else
	    {     
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



function callSearch() {
	//alert('x');
		myWin = window.open('','myWin','top=260,left=250,width=700,height=400,scrollbars=no,toolbars=no,status=yes,resizable=yes');
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action = 'PayBillMaster_f9action.action';
		document.getElementById("paraFrm").submit();
	}
	function callDelete(id) {
		if(document.getElementById(id).value == "") {
			alert("Please select a record to delete");
			return false;
		}
      	var conf = confirm("Do you really want to delete this record ?");
		if(conf) {
			document.getElementById('paraFrm').target = "_self";
			return true;
  		}
	  	return false;
	}




</script>

