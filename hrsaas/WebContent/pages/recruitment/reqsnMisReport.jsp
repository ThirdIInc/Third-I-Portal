<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="ManpowerReqsn" id="paraFrm" theme="simple">
<s:hidden name="selectedReqFlag"/><s:hidden name="hidFirstAsc"/><s:hidden name="hidFirstDesc"/>
<s:hidden name="firstRadio"/><s:hidden name="hidThirdAsc"/><s:hidden name="hidThirdDesc"/><s:hidden name="secondRadio"/>
<s:hidden name="thirdRadio"/><s:hidden name="hidSecAsc"/><s:hidden name="hidSecDesc"/><s:hidden name="editVal"/><s:hidden name="headerChk"/>
<s:hidden name="divId"/><s:hidden name="aId"/><s:hidden name="backFlag"/>
<s:hidden name="mraRepCode"/>	<s:hidden name="radio1"/><s:hidden name="radioFlag1"/><s:hidden name="radioFlag"/>
<s:hidden name="radio2"/><s:hidden name="radioFlag2"/><s:hidden name="radio3"/><s:hidden name="radioFlag3"/>
<s:hidden name="scrnFlg"/><s:hidden name="viewOnScrn"/><s:hidden name="reportFlag"/><s:hidden name="dateFlag"/>
<s:hidden name="pageNoField" id="pageNoField"/><s:hidden name="totalPage" id="totalPage"/><s:hidden name="hdPage" id="hdPage"/>
 <s:hidden name="hidSelectedReqName"/><s:hidden name="editFlag"/> 
	<table width="100%" colspan="4" cellpadding="0" cellspacing="0"
		align="right" class="formbg"><s:hidden name="exportAll"/><s:hidden name="exportAllData"/><s:hidden name="exportAll1"/>

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Manpower Requisition Report</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		
		<tr align="left">
			<td colspan="4">
				<input type="button" class="token"  theme="simple" value='Show Report' onclick="call();" />
			    <input type="button" class="reset"  theme="simple" value=" Reset" onclick="callReset();"/>
				<input type="button" class="token"  value=" Save Report Criteria" onclick="callSave();"/>
				
				</td>
				
				
				
						
		</tr>
		
	<tr>
	        <td colspan="3">
	           <table width="100%" border="0" cellpadding="1" cellspacing="1" >
					<tr>
					    <td   nowrap="nowrap"><label  class = "set"  id="save.setting"  name="save.setting" ondblclick="callShowDiv(this);"><%=label.get("save.setting")%></label> :</td> 
					    <td width="85%">  <s:select headerKey="-1" headerValue="--Select--" name="searchSetting"   list="%{hashMap}"  onchange="callVal();"/>
					      <s:hidden name="reqCode" /> <s:hidden name="hiringManagerId" />   
					      <s:hidden name="recruiterCode"/>   <s:hidden name="positionId"/> 
					      <s:hidden name="common"/>  <s:hidden name="divCount"/>  
					       <s:hidden name="selectedReq"/>    <s:hidden name="editReqFlag"/>  
					     <s:hidden name="checkedCount" value="13"/>  <s:hidden name="reqStatus"/>  <s:hidden  name="exportAll" id="exportAll" value="on"/> 
					    <s:hidden name="radioStatus"/>  <s:hidden name="radioReq"/>  <s:hidden name="radioRecr"/> <s:hidden name="radioHirMng"/> <s:hidden name="radioPosition"/> 
					    </td> 
					</tr>
				</table>
			</td>
		 </tr>   	
		
		
	<tr>
		<td colspan="3">
				 <table   border="0" cellpadding="0" cellspacing="0"  width="100%">
						<tr> 
							<td class="btn-middlebg">
							<div id="tabnav" style="vertical-align: bottom">
							 <ul>
								<li><a id="filtOpt" href="javascript:callTab('filtOpt','filter');">
									<div align="center"><span>Filter Option</span> </div></a>
								</li>
								<li><a id="sortOpt" href="javascript:callTab('sortOpt','sort');">
									<div align="center"><span>Sorting Option</span></div></a>
								</li> 
													
								
								<li><a  id="advFilter" href="javascript:callTab('advFilter','advance');">
									<div align="center"><span>Advanced Filter</span></div></a>
								</li>  
							 </ul>
							</div>
							</td>
						</tr>
					
		
	<tr><td colspan="3">
		<div id="filterOption">	
			<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
				<tr>
					<td>
								<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" >
											
									<tr>
										<td colspan="1" width="20%"><label  class = "set" name="division" id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:</td>
										<td colspan="3" width="80%"><s:hidden name="divCode" />
										<s:textfield name="divName" theme="simple" readonly="true" size="25" /> <img
											src="../pages/images/recruitment/search2.gif" class="iconImage"
											height="16" align="absmiddle" width="15"
											onclick="javascript:callsF9(500,325,'ManpowerReqsn_f9Div.action');">
										</td>
									</tr>	
									
									<tr>
										<td colspan="1" width="20%"><label  class = "set" name="branch" id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
										<td colspan="3" width="80%"><s:hidden name="brnCode" />
										<s:textfield name="brnName" theme="simple" readonly="true" size="25" /> <img
											src="../pages/images/recruitment/search2.gif" class="iconImage"
											height="16" align="absmiddle" width="15"
											onclick="javascript:callsF9(500,325,'ManpowerReqsn_f9Brn.action');">
										</td>
									</tr>		
											
									<tr>
										<td colspan="1" width="20%"><label  class = "set" name="department" id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:</td>
										<td colspan="3" width="80%"><s:hidden name="deptCode" />
										<s:textfield name="deptName" theme="simple" readonly="true" size="25" /> <img
											src="../pages/images/recruitment/search2.gif" class="iconImage"
											height="16" align="absmiddle" width="15"
											onclick="javascript:callsF9(500,325,'ManpowerReqsn_f9Dept.action');">
										</td>
									</tr>		
									<tr>
										<td colspan="1" width="20%"><label  class = "set" name="position" id="position" ondblclick="callShowDiv(this);"><%=label.get("position")%></label>:</td>
										<td colspan="3" width="80%"><s:hidden name="posCode" />
										<s:textfield name="posName" theme="simple" readonly="true" size="25" /> <img
											src="../pages/images/recruitment/search2.gif" class="iconImage"
											height="16" align="absmiddle" width="15"
											onclick="javascript:callsF9(500,325,'ManpowerReqsn_f9Pos.action');">
										</td>
									</tr>						
								
							<tr>	
						  	
						  	<td width="20%" colspan="1" class="formtext"><label  class = "set"  id="reqs.date"  name="reqs.date" ondblclick="callShowDiv(this);"><%=label.get("reqs.date")%></label> : </td>					
								 <td width="80%" colspan="2" >
								   <table width="100%">
								      <tr> 
								         <td nowrap="nowrap"> <s:select headerKey="1" headerValue="--Select--" name="dateFilter" list="#{'O ':'On','B ':'Before','A ':'After' ,'OB':'On Or before' ,'OA':'On Or after','F ':'From'}" onchange="callToDateDispOnClick();" />  </td>
								         <td width="10%" colspan="1" > <s:textfield name="frmDate"  size="9" maxlength="10"  onkeypress="return numbersWithHiphen();" onfocus="clearText('paraFrm_frmDate','dd-mm-yyyy')" onblur="setText('paraFrm_frmDate','dd-mm-yyyy')"  />   </td>
								         <td width="10%" colspan="1" align="left"> <img src="../pages/images/recruitment/Date.gif" id="fromDateIcon" class="iconImage" height="18" align="absmiddle" width="18" onclick="javascript:NewCal('paraFrm_frmDate','DDMMYYYY');">  </td>
								         <td width="15%" colspan="1" > <div id="toDateDivLabel"> <label  class = "set"  id="toDateLabel"  name="tDate" ondblclick="callShowDiv(this);"><%=label.get("tDate")%></label> : </div> </td>
								         <td width="50%" colspan="2" ><div id="toDateDiv">  <s:textfield name="toDate"  size="10" maxlength="10"  onkeypress="return numbersWithHiphen();" onfocus="clearText('paraFrm_toDate','dd-mm-yyyy')" onblur="setText('paraFrm_toDate','dd-mm-yyyy')"  /> &nbsp; 
								                       <img src="../pages/images/recruitment/Date.gif"  class="iconImage" height="18" align="absmiddle" width="18" onclick="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
								                       </div>
								            </td>
								      </tr>  
								   </table>
								</td>
						  	</tr>   
						  	
						  	<tr>	 
						  	 
								 <td width="100%" colspan="3" >
								   <table width="100%" border="0">
								      <tr> 
								        <td width="19%" colspan="1"> <label  class = "set"  id="selectReqLabel"  name="selectReqLabel" ondblclick="callShowDiv(this);"><%=label.get("selectReqLabel")%></label> :    </td>
								        <td width="29%" colspan="1" align="left"> <s:textarea name="selectedReqName" cols="35" rows="2" readonly="true" theme="simple"/>  </td>
								         <td width="52%">
								         <table width="100%">
								          <tr>
								            <td width="40%"> <div  id="selectReqDiv"> <input type="button" class="token" value=" Select Requisition " onclick="callSelectReq('simple');">   </div> 
								          </td>
								           <td width="60%" colspan="1" > <div id="textAreaId1" > <input type="button" class="token" value=" Edit Requisition " onclick="callSelectReq('edit');">     </td>
							
								          </tr> 
								         </table> 
								         </td>
								     </tr>  
								   </table>
								</td>
						  	</tr>     
									
								
								</table>
					      </td>
				       </tr>
	    	    </table>
		  </div>
		 </td>
   	</tr>
   	
   	
   	<tr>
 		<td colspan="3">
 					<div id="sortingOption">
 						<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
 							<tr><td>
 								<table width="100%" border="0" cellpadding="2" cellspacing="2">
 										
 									<tr>
							             <td width="20%" colspan="1">Sort By :</td>
							             <td colspan="3" nowrap="nowrap" width="80%"><s:select name="firstSort" 
								          cssStyle="width:125" headerKey="1" headerValue="--Select--"
								          list="#{'RN':'Requisition Code','PO':'Position','RD':'Requisition Date','BR':'Branch','DE':'Department','DI':'Division'}"/>
							             </td>
						             </tr>
 							<tr>
								 <td width="20%"></td>
								  <td width="80%">
								<s:if test="radio1">
								<input type="radio" value="A" name="firstAsc" id="firstAsc" checked="checked" onclick="callFirstAsc();">
								</s:if><s:else>
									<input type="radio" value="A" name="firstAsc" id="firstAsc" onclick="callFirstAsc();">
								</s:else>
								
								 Ascending    
								
								<s:if test="radioFlag1">
								<input type="radio" value="D" name="firstDesc" id="firstDesc" checked="checked" onclick="callFirstDesc();"> 
								</s:if><s:else>
								<input type="radio" value="D" name="firstDesc" id="firstDesc" onclick="callFirstDesc();">
								</s:else>
								Descending</td>
						    </tr>
 										
 										
 									<tr>
							             <td width="20%" colspan="1">Then By :</td>
							             <td colspan="3" nowrap="nowrap" width="80%"><s:select name="secondSort" 
								          cssStyle="width:125" headerKey="1" headerValue="--Select--"
								          list="#{'RN':'Requisition Code','PO':'Position','RD':'Requisition Date','BR':'Branch','DE':'Department','DI':'Division'}"/>
							             </td>
						             </tr>
						             
						             
						            <tr>
							<td width="20%"></td>
							<td width="80%">
							<s:if test="radio2">
							
							<input type="radio" value="A" name="secAsc" id="secAsc" checked="checked" onclick="callSecAsc();">
							</s:if><s:else>
								<input type="radio" value="A" name="secAsc" id="secAsc" onclick="callSecAsc();">
							
							</s:else>
							 Ascending   
							 
							 <s:if test="radioFlag2">
							  <input type="radio" value="D" name="secDesc"  id="secDesc" checked="checked" onclick="callSecDesc();">
							  </s:if><s:else>
							   <input type="radio" value="D" name="secDesc"  id="secDesc" onclick="callSecDesc();">
							  </s:else>
							   Descending</td>
						</tr> 
						             
						             <tr>
							             <td width="20%" colspan="1">Then By :</td>
							             <td colspan="3" nowrap="nowrap" width="80%"><s:select name="thirdSort" 
								          cssStyle="width:125" headerKey="1" headerValue="--Select--"
								          list="#{'RN':'Requisition Code','PO':'Position','RD':'Requisition Date','BR':'Branch','DE':'Department','DI':'Division'}"/>
							             </td>
						             </tr>	
 										
 								<tr>
							<td width="20%" colspan="1"></td>
							<td width="80%" colspan="3">
							
							<s:if test="radio3">
							<input type="radio" value="A" name="thirdAsc" checked="checked" id="thirdAsc" onclick="callThirdAsc();"> 
							</s:if><s:else>
							<input type="radio" value="A" name="thirdAsc" id="thirdAsc" onclick="callThirdAsc();"> 
							</s:else>
							Ascending    
							<s:if test="radioFlag3">
							<input type="radio" value="D" name="thirdDesc" id="thirdDesc" checked="checked" onclick="callThirdDesc();"> 
							</s:if><s:else>
							<input type="radio" value="D" name="thirdDesc" id="thirdDesc" onclick="callThirdDesc();"> 
							</s:else>
							
							Descending</td>
						</tr>
 				   	</table>
 					</td>
 		    	</tr>
 	    	</table>
     	</div>
     </td>  	
   </tr>
   
   <tr>
		<td><div id="advancedFilters">
			<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
				<tr><td>
						<table width="100%" border="0" cellpadding="2" cellspacing="2">
						
						<tr>
							<td width="20%" colspan="1"><label class="set" id="hiring.mgr"
								name="hiring.mgr" ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%>
							:</td>
							<td colspan="3" width="80%"><s:hidden name="hiringMgrId"/><s:hidden name="empToken"/><s:textfield name="hiringMgr" theme="simple" readonly="true" size="25" /> <img
											src="../pages/images/recruitment/search2.gif" class="iconImage"
											height="16" align="absmiddle" width="15"
											onclick="javascript:callsF9(500,325,'ManpowerReqsn_f9Hiring.action');">
							</td>
						</tr>
						<tr>
							<td width="20%"><label class="set" id="reqs.status" name="reqs.status"
								ondblclick="callShowDiv(this);"><%=label.get("reqs.status")%> :</td>
							<td colspan="3" width="80%"><s:select name="reqsStatus" headerKey="1" headerValue="--Select--"
								cssStyle="width:90"
								list="#{'O':'Open','C':'Close'}" />
							</td>
						</tr>
						<tr>
							<td width="20%"><label class="set" id="vacn1" name="vacn"
								ondblclick="callShowDiv(this);"><%=label.get("vacn")%> :</td>
							<td colspan="3" width="80%"><s:select name="advVacOpt" headerKey="1" headerValue="--Select--"
								cssStyle="width:90"
								list="#{'IE':'=','LT':'<','GT':'>','LE':'<=','GE':'>='}" />
							<s:textfield name="advVacVal" size="4" maxlength="3"
								onkeypress="return numbersOnly();" /></td>
						</tr>
				
					</table>
				</td>
			</tr>
		</table>
	  </div>
	</td>   
   </tr>
   
   	</table>
  </td></tr>
	  <tr>
			     <td colspan="3"> 
			       <table width="100%" border="0" cellpadding="1" cellspacing="1" class="formbg"> 
			                   <tr>								
								 <td width="100%" colspan="2" class="formtext"> <b>Display option</b> </td>
							   </tr>  
							  <tr>								
								<td width="100%" colspan="2" class="formtext"> <s:hidden name="hidReportView" />  <s:hidden name="hidReportRadio" />   <input type="radio" value="V" name="reportView"  id="reportViewV" <s:property value="hidReportView"/>  onclick="callReportChk('N');" > <label  class = "set"  id="view.screen"  name="view.screen" ondblclick="callShowDiv(this);"><%=label.get("view.screen")%></label>  </td>
							 </tr>  
							  <tr>								
								<td width="17%" colspan="1" class="formtext"> <input type="radio" value="R" name="reportView"  id="reportViewR"  <s:property value="hidReportRadio"/> onclick="callReportChk('Y');"> <label  class = "set"  id="report.type"  name="report.type" ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label>  </td>
								<td width="83%" colspan="1" class="formtext" > <div id="reportTypeDiv"> <s:select  headerKey="1" headerValue="--Select--" name="reportType" list="#{'P':'Pdf','X':'Xls' ,'T':'Doc'}"  /> </div>  </td>
							 </tr>  
							  <tr>								
								<td nowrap="nowrap" colspan="1" class="formtext"> <label  class = "set"  id="setting.name"  name="setting.name" ondblclick="callShowDiv(this);"><%=label.get("setting.name")%></label> : </td>
							    <td width="83%" colspan="1" ><s:textfield name="settingName" size="25" theme="simple" maxlength="40" />  </td>
				            </tr>  
					 </table>
			      </td>
		       </tr>  		
		       <tr> 
		          <td colspan="3">
			           <table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
							<td width="100%" colspan="3"> 
							<input type="button" class="token" theme="simple" value='Show Report' onclick="call();" />
						    <input type="button" class="reset"  theme="simple" value=" Reset" onclick="callReset();"/>
							<input type="button" class="token"  	value=" Save Report Criteria" onclick="callSave();"/>
							</td> 
						  </tr>
					  </table>
					</td>					
			    </tr>					
	
	

	</table>
	
</s:form>


<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script>
callBack();
function callBack(){

if(document.getElementById('paraFrm_editFlag').value=="true"){
		
 document.getElementById('textAreaId1').style.display=''; 
}else{
 document.getElementById('textAreaId1').style.display='none'; 
}



var flag=document.getElementById('paraFrm_backFlag').value;


if(document.getElementById('paraFrm_dateFilter').value=="F "){
document.getElementById('toDateDivLabel').style.display='';
document.getElementById('toDateDiv').style.display='';
}else{

	if(document.getElementById('paraFrm_dateFlag').value=="true"){
	document.getElementById('toDateDivLabel').style.display='true';
	document.getElementById('toDateDiv').style.display='true';
	}else{
	document.getElementById('toDateDivLabel').style.display='none';
	document.getElementById('toDateDiv').style.display='none';
	}
}
var aid=document.getElementById('paraFrm_aId').value;
var div=document.getElementById('paraFrm_divId').value;
var sf=document.getElementById('paraFrm_scrnFlg').value;

if(sf=="true"){

		if(document.getElementById('paraFrm_reportFlag').value=="true"){
			document.getElementById('reportTypeDiv').style.display='';
			document.getElementById('reportViewR').checked=true;
			document.getElementById('reportViewV').checked=false;
		
		}else{
		    document.getElementById('reportTypeDiv').style.display='none';
		    document.getElementById('reportViewV').checked=true;
			document.getElementById('reportViewR').checked=false;
		}

}else{
if(document.getElementById('paraFrm_hidReportView').value){
document.getElementById('reportTypeDiv').style.display='none';
}else{
document.getElementById('reportTypeDiv').style.display='';
}

}
if(flag=="true"){

	if(aid=="" || div==""){
	  callTab('filtOpt', 'filterOption') ;
	
	}else{
	 callTab(aid,div);
  }
}else{
  callOnLoad();
}
 
}
if(document.getElementById('paraFrm_backFlag').value=="false"){
	callOnLoad();
}
function callOnLoad()
 {
 
   document.getElementById('firstAsc').checked=true;
   document.getElementById('secAsc').checked=true;
   document.getElementById('thirdAsc').checked=true;
   if(document.getElementById('paraFrm_reportFlag').value=="true"){
     document.getElementById('reportViewR').checked=true;
      document.getElementById('reportViewV').checked=false;
      
   document.getElementById('reportTypeDiv').style.display='';
   }else{
     document.getElementById('reportViewR').checked=false;
      document.getElementById('reportViewV').checked=true;
     
   document.getElementById('reportTypeDiv').style.display='none';
   }
   
   if(document.getElementById('paraFrm_dateFlag').value=="true"){
    document.getElementById('toDateDiv').style.display=''; 
   document.getElementById('toDateDivLabel').style.display='';
   
   }else{
   
    document.getElementById('toDateDiv').style.display='none'; 
   document.getElementById('toDateDivLabel').style.display='none';
   }
  /*  if(document.getElementById('paraFrm_viewOnScrn').value=="true"){
    	 document.getElementById('reportViewV').checked=true;
    	   document.getElementById('reportViewR').checked=false;
    
    }else{
     document.getElementById('reportViewV').checked=false;
       document.getElementById('reportViewR').checked=true;
    }
  */
  
  
   document.getElementById('selectReqDiv').style.display=''; 
      
   var fieldName =["paraFrm_frmDate"];
   setTextArray(fieldName,"dd-mm-yyyy");
  
   document.getElementById('filterOption').style.display='';
   document.getElementById('sortingOption').style.display='none';
   document.getElementById('advancedFilters').style.display='none';    
   document.getElementById('sortOpt').className = '';
	document.getElementById('advFilter').className = '';
	document.getElementById('filtOpt').className = 'on';
	document.getElementById('paraFrm_aId').value =  'filtOpt';
	document.getElementById('paraFrm_divId').value = 'filter'; 
   
 }
function callTab(aId,tabId)
 { 
 
   
     //   document.getElementById('filtOpt').className = ''; 
	//	document.getElementById('sortOpt').className = ''; 
	//	document.getElementById('advFilter').className = ''; 
	//	document.getElementById(aId).className = 'on';
		
		
		if(tabId=="filter")
	  {   
	  document.getElementById('filterOption').style.display='';
	  document.getElementById('sortingOption').style.display='none'; 
	  document.getElementById('advancedFilters').style.display='none'; 
	  document.getElementById('paraFrm_aId').value =  'filtOpt';
	  document.getElementById('paraFrm_divId').value = 'filter'; 
	  document.getElementById('sortOpt').className = '';
	  document.getElementById('advFilter').className = '';
	  document.getElementById('filtOpt').className = 'on';
	  }
	   
	  if(tabId=="sort")
	  {   
if(document.getElementById('paraFrm_radioFlag').value=="true"){	

   if(document.getElementById('paraFrm_radio1').value=="true"){
	
	  	     document.getElementById('firstAsc').checked=true;
	  	     document.getElementById('firstDesc').checked=false;
	     }else{
	     
	         document.getElementById('firstDesc').checked=true;
	         document.getElementById('firstAsc').checked=false;
	     }
	     
	     if(document.getElementById('paraFrm_radio2').value=="true"){
	  	     document.getElementById('secAsc').checked=true;
	  	     document.getElementById('secDesc').checked=false;
	     }else{
	         document.getElementById('secDesc').checked=true;
	         document.getElementById('secAsc').checked=false;
	     }
	     
	     if(document.getElementById('paraFrm_radio3').value=="true"){
	  	     document.getElementById('thirdAsc').checked=true;
	  	     document.getElementById('thirdDesc').checked=false;
	     }else{
	         document.getElementById('thirdDesc').checked=true;
	         document.getElementById('thirdAsc').checked=false;
	     }
}else{

  if(document.getElementById('paraFrm_hidFirstAsc').value=='A'){
	  	     document.getElementById('firstAsc').checked=true;
	  	     document.getElementById('firstDesc').checked=false;
	     }else{
	         document.getElementById('firstDesc').checked=true;
	         document.getElementById('firstAsc').checked=false;
	     }
	     
	     if(document.getElementById('paraFrm_hidSecAsc').value=='A'){
	  	     document.getElementById('secAsc').checked=true;
	  	     document.getElementById('secDesc').checked=false;
	     }else{
	         document.getElementById('secDesc').checked=true;
	         document.getElementById('secAsc').checked=false;
	     }
	     
	     if(document.getElementById('paraFrm_hidThirdAsc').value=='A'){
	  	     document.getElementById('thirdAsc').checked=true;
	  	     document.getElementById('thirdDesc').checked=false;
	     }else{
	         document.getElementById('thirdDesc').checked=true;
	         document.getElementById('thirdAsc').checked=false;
	     }



 }	     
	   document.getElementById('sortingOption').style.display=''; 
	   document.getElementById('filterOption').style.display='none'; 
	   document.getElementById('advancedFilters').style.display='none'; 
	   document.getElementById('paraFrm_aId').value =  'sortOpt';
	   document.getElementById('paraFrm_divId').value = 'sort';   
	   document.getElementById('sortOpt').className = 'on';
	   document.getElementById('advFilter').className = '';
	   document.getElementById('filtOpt').className = '';
	  } 
	  
	   if(tabId=="advance")
	  {   
	   
	     
	   document.getElementById('sortingOption').style.display='none'; 
	   document.getElementById('filterOption').style.display='none'; 
	   document.getElementById('advancedFilters').style.display='';    
	   document.getElementById('paraFrm_aId').value =  'advFilter';
	   document.getElementById('paraFrm_divId').value = 'advance'; 
	   document.getElementById('sortOpt').className='';
	   document.getElementById('advFilter').className='on';
	   document.getElementById('filtOpt').className='';
	  }
}		

 function call(){ 
  	var dateFilter = document.getElementById('paraFrm_dateFilter').value;
  	var frmDate = document.getElementById('paraFrm_frmDate').value; 
    var sort1=document.getElementById('paraFrm_firstSort').value;
	var sort2=document.getElementById('paraFrm_secondSort').value;
	var sort3=document.getElementById('paraFrm_thirdSort').value;
     
	      if(dateFilter!="1")
	      { 
	        if(frmDate=="dd-mm-yyyy")
	         { 
	          callTab('filtOpt','filter');
	         alert("Please enter/select  the "+document.getElementById('reqs.date').innerHTML.toLowerCase()); 
	          clearText("paraFrm_frmDate","dd-mm-yyyy");
		     document.getElementById('paraFrm_frmDate').focus();
		     return false; 	 
	        }   
	       if(!validateDate('paraFrm_frmDate', 'reqs.date'))
	        {  return false;
	        }
	        if(dateFilter=="F ")
	         {
	           
	           var toDate = document.getElementById('paraFrm_toDate').value; 
		        clearText("paraFrm_toDate","dd-mm-yyyy");
		         if(!validateDate('paraFrm_toDate', 'toDateLabel'))
	             {  return false;
	               }
		         callTab('filtOpt','filter');
	             if(!dateDifferenceEqual(frmDate,toDate,"paraFrm_toDate",'reqs.date','toDateLabel'))
	           {
	            return false;
	           }
	             setText("paraFrm_toDate","dd-mm-yyyy");
	         }
	     }
	     
	        if(sort1!="1" && sort2!="1"){
		  	 	if(sort1==sort2){
		  	 			callTab('sortOpt','sort')
		  	 		 	alert("Duplicate Sorting Option.");
		  	 		 	return false;
		  	 	}	
		  	 
		  	 }
		  	 
		  	 if(sort1!="1" && sort3!="1"){
		  	 	if(sort1==sort3){
		  	 			callTab('sortOpt','sort')
		  	 		 	alert("Duplicate Sorting Option.");
		  	 		 	return false;
		  	 	}	
		  	 
		  	 }
		  	 
		  	 
		  	 if(sort2!="1" && sort3!="1"){
		  	 	if(sort2==sort3){
		  	 			callTab('sortOpt','sort')
		  	 		 	alert("Duplicate Sorting Option.");
		  	 		 	return false;
		  	 	}	
		  	 
		  	 }
		  	 
		   if(trim(document.getElementById('paraFrm_advVacOpt').value)!="1") {
      	if(trim(document.getElementById('paraFrm_advVacVal').value)==""){
      		alert("Please enter the no. of vacancies");
      		callTab('advFilter','advance');
      		return false;
      	}
      }	 
	     
	     if(document.getElementById('reportViewR').checked){
	     	if(document.getElementById('paraFrm_reportType').value=="1"){
	     		alert("Please select the "+document.getElementById('report.type').innerHTML.toLowerCase());
	     		  callTab('filtOpt','filter');
	     		  return false;
	     	}
	     
	     } 
		
var type=document.getElementById('paraFrm_reportType').value;	
if(type=="P")
 type="Pdf";
 else if(type=="X")
 type="Xls";
 else
 type="Txt";  	
  	//else {
	      if(document.getElementById('reportViewV').checked){
			document.getElementById('paraFrm').action='ManpowerReqsn_callJspView.action';	
			document.getElementById('paraFrm').submit();
			}else{
			document.getElementById('paraFrm').action='ManpowerReqsn_report.action?rep='+type;	
			document.getElementById('paraFrm').submit();
			
			}
		
	
	//}
	}
	
function callToDateDispOnClick()
 {
   var dateFilter= document.getElementById('paraFrm_dateFilter').value;
  
   document.getElementById('paraFrm_toDate').value="";  
   if(dateFilter=="F "){
   
      setText("paraFrm_toDate","dd-mm-yyyy");
      document.getElementById('toDateDiv').style.display=''; 
     document.getElementById('toDateDivLabel').style.display='';
   }else {
      setText("paraFrm_toDate","dd-mm-yyyy");
     document.getElementById('toDateDiv').style.display='none'; 
     document.getElementById('toDateDivLabel').style.display='none';       
   } 
 }	
	
function callReportChk(status){
	if(status=="Y"){
	 document.getElementById('reportViewV').checked=false;
     document.getElementById('reportViewR').checked=true;
     document.getElementById('reportTypeDiv').style.display='';   
     document.getElementById('paraFrm_hidReportRadio').value='checked';
      document.getElementById('paraFrm_hidReportView').value='';
     //reportTypeDiv
	
	}else{
	 document.getElementById('reportViewV').checked=true;
     document.getElementById('reportViewR').checked=false;
      document.getElementById('reportTypeDiv').style.display='none';  
	 document.getElementById('paraFrm_hidReportView').value='checked';
	  document.getElementById('paraFrm_hidReportRadio').value='';
	}
}

function callSelectReq(status)
 {
      // filter on tab 
       var dateFilter = document.getElementById('paraFrm_dateFilter').value;
       var frmDate = document.getElementById('paraFrm_frmDate').value; 
	      if(dateFilter!="1")
	      { 
	        if(frmDate=="dd-mm-yyyy")
	         { 
	          callTab('filtOpt','filter');
	         alert("Please enter/select  the "+document.getElementById('reqs.date').innerHTML.toLowerCase()); 
	          clearText("paraFrm_frmDate","dd-mm-yyyy");
		     document.getElementById('paraFrm_frmDate').focus();
		     return false; 	 
	        }   
	       if(!validateDate('paraFrm_frmDate', 'reqs.date'))
	        {  return false;
	        }
	        if(dateFilter=="F ")
	         {
	           
	           var toDate = document.getElementById('paraFrm_toDate').value; 
		        clearText("paraFrm_toDate","dd-mm-yyyy");
		         if(!validateDate('paraFrm_toDate', 'toDateLabel'))
	             {  return false;
	               }
		         callTab('filtOpt','filter');
	             if(!dateDifferenceEqual(frmDate,toDate,"paraFrm_toDate",'reqs.date','toDateLabel'))
	           {
	            return false;
	           }
	             setText("paraFrm_toDate","dd-mm-yyyy");
	         }
	     }
	     
   if(trim(document.getElementById('paraFrm_advVacOpt').value)!="1") {
      	if(trim(document.getElementById('paraFrm_advVacVal').value)==""){
      		alert("Please enter the no. of vacancies");
      		callTab('advFilter','advance');
      		return false;
      	}
      }
  if(status=='edit'){
  
     document.getElementById('paraFrm_selectedReqName').value =document.getElementById('paraFrm_hidSelectedReqName').value;
   document.getElementById('paraFrm_editReqFlag').value="true";     
  }else{
   document.getElementById('paraFrm_editReqFlag').value="false"; 
   document.getElementById('paraFrm_selectedReqName').value="";
   document.getElementById('paraFrm_selectedReq').value=""; 
  }  
   document.getElementById('paraFrm').target="wind";
   var wind = window.open('','wind','width=700,height=450,scrollbars=yes,resizable=no,status=yes,menubar=no,top=200,left=150');	 
   document.getElementById('paraFrm').action="ManpowerReqsn_displayReq.action";
   document.getElementById('paraFrm').submit();
   document.getElementById('paraFrm').target="";
 }
 
 function callFirstAsc(){
	  document.getElementById('paraFrm_firstRadio').value='false';
	  document.getElementById('paraFrm_hidFirstAsc').value='A';
	  document.getElementById('paraFrm_hidFirstDesc').value='';
	  document.getElementById('firstDesc').checked=false;
					
					
} 
 function callFirstDesc(){
 	  document.getElementById('paraFrm_firstRadio').value='true';
	  document.getElementById('paraFrm_hidFirstAsc').value='';
	  document.getElementById('paraFrm_hidFirstDesc').value='D';
	  document.getElementById('firstAsc').checked=false;
					
					
						
} 


function callSecAsc(){
document.getElementById('paraFrm_secondRadio').value='false';
document.getElementById('paraFrm_hidSecAsc').value='A';
document.getElementById('paraFrm_hidSecDesc').value='';
document.getElementById('secDesc').checked=false;

}

function callSecDesc(){
	  document.getElementById('paraFrm_secondRadio').value='true';
	  document.getElementById('paraFrm_hidSecAsc').value='';
	  document.getElementById('paraFrm_hidSecDesc').value='D';
	  document.getElementById('secAsc').checked=false;

}

function callThirdAsc(){
		document.getElementById('paraFrm_thirdRadio').value='false';
		document.getElementById('paraFrm_hidThirdAsc').value='A';
	  document.getElementById('paraFrm_hidThirdDesc').value='';
	  document.getElementById('thirdDesc').checked=false;

}

function callThirdDesc(){
		document.getElementById('paraFrm_thirdRadio').value='true';
	  document.getElementById('paraFrm_hidThirdAsc').value='';
	  document.getElementById('paraFrm_hidThirdDesc').value='D';
	  document.getElementById('thirdAsc').checked=false;

}
 
function callVal(){
 
  
	var repCode=document.getElementById('paraFrm_searchSetting').value;
	if(repCode!="-1"){
	//	document.getElementById('setting').style.display ='none';
		document.getElementById('paraFrm_mraRepCode').value=repCode;
		document.getElementById('paraFrm').action='ManpowerReqsn_getFilterDetails.action';
		document.getElementById('paraFrm').submit();
	}else{
		document.getElementById('paraFrm_mraRepCode').value="";
	}
} 

function callSave(){

var sort1=document.getElementById('paraFrm_firstSort').value;
var sort2=document.getElementById('paraFrm_secondSort').value;
var sort3=document.getElementById('paraFrm_thirdSort').value;

  var dateFilter = document.getElementById('paraFrm_dateFilter').value;
       var frmDate = document.getElementById('paraFrm_frmDate').value; 
       
     
	      if(dateFilter!="1")
	      { 
	        if(frmDate=="dd-mm-yyyy")
	         { 
	          callTab('filtOpt','filter');
	         alert("Please enter/select  the "+document.getElementById('reqs.date').innerHTML.toLowerCase()); 
	          clearText("paraFrm_frmDate","dd-mm-yyyy");
		     document.getElementById('paraFrm_frmDate').focus();
		     return false; 	 
	        }   
	       if(!validateDate('paraFrm_frmDate', 'reqs.date'))
	        {  return false;
	        }
	        if(dateFilter=="F ")
	         {
	           
	           var toDate = document.getElementById('paraFrm_toDate').value; 
		        clearText("paraFrm_toDate","dd-mm-yyyy");
		         if(!validateDate('paraFrm_toDate', 'toDateLabel'))
	             {  return false;
	               }
		         callTab('filtOpt','filter');
	             if(!dateDifferenceEqual(frmDate,toDate,"paraFrm_toDate",'reqs.date','toDateLabel'))
	           {
	            return false;
	           }
	             setText("paraFrm_toDate","dd-mm-yyyy");
	         }
	     }
	     
	     
			        if(sort1!="1" && sort2!="1"){
		  	 	if(sort1==sort2){
		  	 			callTab('sortOpt','sort')
		  	 		 	alert("Duplicate Sorting Option.");
		  	 		 	return false;
		  	 	}	
		  	 
		  	 }
		  	 
		  	 if(sort1!="1" && sort3!="1"){
		  	 	if(sort1==sort3){
		  	 			callTab('sortOpt','sort')
		  	 		 	alert("Duplicate Sorting Option.");
		  	 		 	return false;
		  	 	}	
		  	 
		  	 }
		  	 
		  	 
		  	 if(sort2!="1" && sort3!="1"){
		  	 	if(sort2==sort3){
		  	 			callTab('sortOpt','sort')
		  	 		 	alert("Duplicate Sorting Option.");
		  	 		 	return false;
		  	 	}	
		  	 
		  	 }
			     
	     
	     if(trim(document.getElementById('paraFrm_advVacOpt').value)!="1") {
      	     if(trim(document.getElementById('paraFrm_advVacVal').value)==""){
      		   alert("Please enter the no. of vacancies");
      		   callTab('advFilter','advance');
      		   return false;
      	   }
      }
	     if(document.getElementById('reportViewR').checked){
	     	if(document.getElementById('paraFrm_reportType').value=="1"){
	     		alert("Please select the "+document.getElementById('report.type').innerHTML.toLowerCase());
	     		  callTab('filtOpt','filter');
	     		  return false;
	     	}
	     
	     } 
	     
	       if(trim(document.getElementById('paraFrm_settingName').value)==""){
       		 alert("Please enter the "+document.getElementById('setting.name').innerHTML.toLowerCase());
       		document.getElementById('paraFrm_settingName').focus();
       		return false;
       }
      
       
 	document.getElementById('paraFrm').action="ManpowerReqsn_saveSettings.action";
	document.getElementById('paraFrm').submit();
}
 	
function callReset(){

document.getElementById('paraFrm').action="ManpowerReqsn_reset.action";
document.getElementById('paraFrm').submit();

}  	
</script>