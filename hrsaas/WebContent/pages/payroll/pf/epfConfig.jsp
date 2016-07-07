<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript">
	var records = new Array();
</script>
<s:form action="PFParameter" method="post" id="paraFrm" validate="true"
	target="main" theme="simple">
	
<s:hidden name="show"  />
<s:hidden name="hiddencode" />
<s:hidden name='EPFflag'/>
<s:hidden name='GPFflag'/>
<s:hidden name='VPFflag'/>
<s:hidden name='PFTflag'/>
<s:hidden name="pfParam.effDate"/>
<s:hidden name="pfParam.deduction"/>
<table class="formbg" width="100%">

		<tr>
			<td colspan="3" width="100%"><%@ include file="pfConfigHeader.jsp"%></td>
		</tr>
	        <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">EPF Parameter</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
	
	<tr>
	<td colspan="3" width="100%">

	 <table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" >
    <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
          <!--  
            <td width="78%">	
	    <s:if test="%{insertFlag}">
			<s:submit   cssClass="add" action="PFParameter_save" onclick="return callSave();"
						value="  Add New" />
	  </s:if>
	  <s:if test="%{updateFlag}"> 
	            <s:submit  cssClass="edit" action="PFParameter_save" onclick="return callUpdate();"
						value="   Update" />
	   </s:if>
	 <s:if test="%{viewFlag}">
		 <input type="button" class="search"	value="    Search"
						onclick="javascript:callsF9(500,325,'PFParameter_f9action.action');" />
						</s:if>
	<s:submit cssClass="reset" action="PFParameter_reset"
					theme="simple" value="    Reset" />
        <s:if test="%{deleteFlag}">	
	            <s:submit cssClass="delete" 
					theme="simple" value="    Delete" action="PFParameter_delete" onclick="return callDelete('paraFrm_pfParam_pfCode');"  />
	 </s:if>
	  -->
	<!--<s:if test="%{viewFlag}">
				<input  type="button" class="token"
					value=" Report" onclick="callReport('PFParameter_report.action')" />	
	</s:if>-->
		
	
	    <td>
	    	<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
	    </td>
		<% int totalPage = 0; int pageNo = 0; %>
		<s:if test="makeList">
		<td id="ctrlShow" width="30%" align="right" class="">
			<b>Page:</b>
			<%	 totalPage = (Integer) request.getAttribute("totalPage");
				pageNo = (Integer) request.getAttribute("pageNo");
			%>
			<a href="#" onclick="callPage('1', 'F', '<%=totalPage%>', 'PFParameter_callPage.action');">
				<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
			</a>&nbsp;
			<a href="#" onclick="callPage('P', 'P', '<%=totalPage%>', 'PFParameter_callPage.action');" >
				<img title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
			</a>
			<input type="text" name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10"
			onkeypress="callPageText(event, '<%=totalPage%>', 'PFParameter_callPage.action');return numbersOnly();" /> of <%=totalPage%>
			<a href="#" onclick="callPage('N', 'N', '<%=totalPage%>', 'PFParameter_callPage.action')" >
				<img title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
			</a>&nbsp;
			<a href="#" onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'PFParameter_callPage.action');" >
				<img title="Last Page" src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
			</a>														
		</td>						
		</s:if>						
          </tr>
		<tr>
			<td colspan="2">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					id='topButtonTable'>
					<tr valign="middle">
						<!--<td nowrap="nowrap"><a href="#" onclick="resetFun();">
							<img src="../pages/images/buttonIcons/Refresh.png"
								class="iconImage" align="absmiddle" title="Reset"> Reset
							</a>&nbsp;&nbsp;
						</td>
						--><td width="100%"><%@ include
							file="/pages/common/reportButtonPanel.jsp"%>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<div name="htmlReport" id='reportDiv'
						style="background-color: #FFFFFF;  height: 400; width: 100%; display: none;border-top: 1px #cccccc solid;">
				<iframe id="reportFrame" frameborder="0" onload=alertsize();
						style="vertical-align: top; float: left; border: 0px solid;"
						allowtransparency="yes" src="../pages/common/loading.jsp" scrolling="auto"
						marginwidth="0" marginheight="0" vspace="0" name="htmlReport"
						width="100%" height="200"></iframe> </div>
			</td>
		</tr>
          
        </table>
          </td>
    </tr>
<s:hidden name="pfParam.pfCode"></s:hidden>

<tr>
			<td colspan="3">
			<%
			try {
			%>
			<table width="100%" border="0" cellpadding="1" cellspacing="0"
				class="formbg">
				<tr>
					<td colspan="2" class="txt"><strong class="text_head"> EPF Parameter </strong></td>
					<td align="right" >
						<s:submit cssClass="delete"  theme="simple"  value="     Delete  "   onclick=" return chkDelete()"/>
					</td>
				</tr>
				<tr>
					<td class="formtext" colspan="3">
					<table width="100%" border="0" cellpadding="1" cellspacing="1">
						<tr>
							<s:hidden name="myPage" id="myPage" />
							<td class="formth" align="center"><!--Sr No.  -->
								<label  class = "set" name="pfparam.srno" id="pfparam.srno" ondblclick="callShowDiv(this);"><%=label.get("pfparam.srno")%></label>
							</td>
							<td class="formth" align="center"><!--Effective Date  -->
								<label  class = "set" name="pfparam.edate" id="pfparam.edate1" ondblclick="callShowDiv(this);"><%=label.get("pfparam.edate")%></label>
							</td>
							<!--PF Type  -->
							<!--  
					        <td class="formth" align="center">
					        	<label  class = "set" name="pfparam.type" id="pfparam.type1" ondblclick="callShowDiv(this);"><%=label.get("pfparam.type")%></label>
					        </td>
					        -->
							<td class="formth" align="center"><!--Percentage  -->
							 	<label  class = "set" name="pfparam.percent" id="pfparam.percent1" ondblclick="callShowDiv(this);"><%=label.get("pfparam.percent")%></label>
							</td>
							<s:if test="makeList">
							<td  align="right" class="formth" nowrap="nowrap"  id="ctrlShow">
								<input type="checkbox" id="selAll" style="cursor: hand;" title="Select all records to remove" 
														onclick="selectAllRecords(this);" />
							</td>
							</s:if>				
						</tr>
							<%int count=0; %>
							<%! int d=0; %>
							<%
							int cnt= pageNo*20-20;	
							int i = 0;
								%>
							<s:if test="makeList">	
							<s:iterator value="iteratorlist">

								<tr 
								<%if(count%2==0){
									%>
									 class="tableCell1" <%}else{%>
									class="tableCell2" <%	}count++; %>
									onmouseover="javascript:newRowColor(this);"
									title="Double click for edit"
									onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
									ondblclick="javascript:callForEdit('<s:property value="pfCode" />');">

									<td width="10%" align="left" class="sortableTD"> <%=++cnt%><%++i;%></td>
									<s:hidden value="%{pfCode}" id='<%="pfCode"+i%>' />
									<script type="text/javascript">
										records[<%=i%>] = document.getElementById('pfCode' + '<%=i%>').value;									
									</script>										
									<td width="40%" align="left" class="sortableTD">
                                                                    <s:property value="effDate" />
									<input type="hidden" name="hdeleteCode" id="hdeleteCode<%=i%>" />
									</td>
							<!-- 	<td width="20%"><s:property value="pfType" /></td>  -->
									<td width="40%" class="sortableTD"><s:property value="deduction" /></td>
									<td  align="center" class="sortableTD" id="ctrlShow"><input type="checkbox"
										class="checkbox" id="confChk<%=i%>" name="confChk"
										onclick="callForDelete1('<s:property value="pfCode" />','<%=i%>')" /></td>
								</tr>

							</s:iterator>
							</s:if>
							<%d=i; %>
						
					
					<s:else>
						<tr><td align="center"><font color="red">No Data To Display</font></td></tr>
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
  </table></td></tr>
	<tr><td width="100%">
		<table border="0" width="100%">
			<tr>
				<td colspan="2">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						id='topButtonTable'>
						<tr valign="middle">
							<!--<td nowrap="nowrap"><a href="#" onclick="resetFun();">
								<img src="../pages/images/buttonIcons/Refresh.png"
									class="iconImage" align="absmiddle" title="Reset"> Reset
								</a>&nbsp;&nbsp;
							</td>
							--><td width="100%"><%@ include
								file="/pages/common/reportButtonPanel.jsp"%>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td width="75%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				<td width="25%" align="Right"><s:if test="makeList"><b>Total No. Of Records:</b>&nbsp;<s:property value="totalRecords" /></s:if></td>
			</tr>
		</table></td>
	</tr>
  </table>
	<s:hidden name="reportType" />
	<s:hidden name="reportAction" value='PFParameter_report.action' />
</s:form>

<script type="text/javascript">
function callReport(type){
		document.getElementById('paraFrm_reportType').value=type;
		callReportCommon(type);
} 

function mailReportFun(type){
		document.getElementById('paraFrm_reportType').value=type;
		document.getElementById('paraFrm').action='PFParameter_mailReport.action';
		document.getElementById('paraFrm').submit();
}

	function addnewFun() {
		document.getElementById('paraFrm').target="_self";
		document.getElementById('paraFrm').action="PFParameter_addNew.action";
		document.getElementById('paraFrm').submit();
	}
	function searchFun() {
			if(navigator.appName == 'Netscape') {
				var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
			} else {
				var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
			}
			document.getElementById("paraFrm").target = 'myWin';
			document.getElementById("paraFrm").action = 'PFParameter_f9action.action';
			document.getElementById("paraFrm").submit();
	}
</script>	
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<script type="text/javascript">

function chkPercentage()
{

	var percen= document.getElementById('paraFrm_pfParam_deduction').value;
	var empPf= document.getElementById('paraFrm_pfParam_empPF').value;
	var pensionPf=document.getElementById('paraFrm_pfParam_pensionFund').value;
	
	if(percen==""){
	  alert("Please enter Percentage first");
	  document.getElementById('paraFrm_pfParam_empPF').value='';
	  document.getElementById('paraFrm_pfParam_deduction').focus()=true;
	  
	  return false;
	}
	
	if(empPf!="")
	{
	if(eval(document.getElementById('paraFrm_pfParam_empPF').value)>eval(document.getElementById('paraFrm_pfParam_deduction').value))
	{
	  alert("Employee PF should be lessthan the PF Percentage");
	  document.getElementById('paraFrm_pfParam_empPF').value='';
	  document.getElementById('paraFrm_pfParam_empFamilyPF').value='';
	  return false;
	
	}
	var empfamilyPf = (eval(percen * 100)/100 -eval(empPf *100)/100);
	
	document.getElementById('paraFrm_pfParam_empFamilyPF').value=empfamilyPf;

	}
	if(pensionPf!="")
	{
	if(eval(document.getElementById('paraFrm_pfParam_pensionFund').value)>eval(document.getElementById('paraFrm_pfParam_deduction').value))
	{
	  alert("Pension Fund Percentage should be lessthan PF Percentage");
	  document.getElementById('paraFrm_pfParam_pensionFund').value='';
	  document.getElementById('paraFrm_pfParam_compFund').value='';
	  return false;
	
	}
	var compFund = (eval(percen * 100)/100-eval(pensionPf * 100)/100);
	
	document.getElementById('paraFrm_pfParam_compFund').value=compFund;

	}
	
	return true;
	
}



var fieldName = ["paraFrm_pfParam_effDate","paraFrm_pfParam_deduction","paraFrm_pfParam_empPF","paraFrm_pfParam_pensionFund","paraFrm_pfParam_debitName","paraFrm_pfParam_pfFormula","paraFrm_pfParam_empFamilyPF","paraFrm_pfParam_compFund"];
var lableName = ["pfparam.edate","pfparam.percent","pfparam.empPF","pfparam.pensionFund","pfparam.PFdebiCode","pfparam.PFformulea","pfparam.empfamilyFund","pfparam.compFund"];
var badflag = ["enter","enter","enter","enter","select","enter","enter","enter"];


function callSave()
 {
		if(!document.getElementById('paraFrm_pfParam_pfCode').value==""){
 		alert("Please click on 'Update' button");
 			return false;
 }
       
	   if(!validateBlank(fieldName,lableName,badflag))
          return false; 
       	
       if(!validateDate('paraFrm_pfParam_effDate','pfparam.edate'))
             return false;	   
            if(!checkPercentage('paraFrm_pfParam_deduction',"PF Percentage"))
             {  return false;	}
             
            if(!checkPercentage('paraFrm_pfParam_empPF',"Employee PF"))
            {return false;}	
           if(!checkPercentage('paraFrm_pfParam_pensionFund',"Pension Fund"))
           { return false; }
   
		return true;
}
 function callUpdate(){
 		if(document.getElementById('paraFrm_pfParam_pfCode').value==""){
 			alert("Please select the record to update!");
 			return false;
 		}else {
 			    if(!checkMandatory(fieldName,lableName,badflag))
       				 return false;
        
      			 if(!validateDate('paraFrm_pfParam_effDate','PFEffective Date'))
             return false;	   
             
             if(!checkPercentage('paraFrm_pfParam_deduction',"PF Percentage"))
             {  return false;	}
             
            if(!checkPercentage('paraFrm_pfParam_empPF',"Employee PF"))
            {return false;}	
           if(!checkPercentage('paraFrm_pfParam_pensionFund',"Pension Fund"))
           { return false; }
              
		return true;   
 		}
 }

	function callForEdit(id){
	  	document.getElementById('paraFrm_hiddencode').value=id;
	   	document.getElementById("paraFrm").action="PFParameter_calforedit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
   }
    function callForDelete(id){
	  	document.getElementById('paraFrm_hiddencode').value=id;
	   	document.getElementById("paraFrm").action="PFParameter_calfordelete.action";
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
	   {
	   document.getElementById('hdeleteCode'+i).value="";
	   document.getElementById('selAll').checked = false;
	   }
	   
   }
   
   	function newRowColor(cell)
   	 {
			cell.className='Cell_bg_first';

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
	   document.getElementById('paraFrm').action="PFParameter_delete1.action";
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
	function setEligibleAll(){
		if(document.getElementById('eliAllCheck').checked)
			document.getElementById('noMaxLimitChk').value="Y";
		else
			document.getElementById('noMaxLimitChk').value="N";
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

</script>		
		
