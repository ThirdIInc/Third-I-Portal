  <script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<link rel="stylesheet" type="text/css" href="../pages/common/tabcontent.css" />
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="AdvertiseReport" method="post" id="paraFrm" theme="simple"> 
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg">
 	    <tr>
			<td colspan="3" width="100%">   
				 <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt"><strong class="formhead"><img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="text_head">Advertisement Analysis </strong></td>
						<td width="3%" valign="top" class="otxt"><div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
					</tr>
				</table>  
			</td>
		 </tr>  
	      <tr>
	        <td colspan="3">
	           <table width="100%" border="0" cellpadding="0" cellspacing="0" >
					<tr>
					   <td width="100%" colspan="3">
					        <s:if test="%{viewFlag}">
					           <input type="button" class="token" onclick="return callReportVacancies();"	value=" Generate Report" />
					         </s:if>
					        <s:submit cssClass="reset" action="AdvertiseReport_reset" theme="simple" value="    Reset"  /> 
					        <input type="button" class="token" value=" Save report criteria" onclick="callSave();"/>
					  </td> 
					</tr>
				</table>
			</td>
		 </tr>
		 
		  <tr>
	        <td colspan="3">
	           <table width="100%" border="0" cellpadding="1" cellspacing="1" >
					<tr>
					    <td nowrap="nowrap"><label  class = "set"  id="save.setting"  name="save.setting" ondblclick="callShowDiv(this);"><%=label.get("save.setting")%></label> :</td> 
					    <td width="85%"> <s:select headerKey="B" headerValue="--Select--" name="searchSetting" list="map" onchange="callShowRecord();"  />
					      <s:hidden name="reqCode" />    <s:hidden name="settingNameDup" />   <s:hidden name="hidSelectedReqName"/>  
					        <s:hidden name="positionId"/>   <s:hidden name='aId'/> <s:hidden name='divId'/>  <s:hidden name='backFlag'/> 
					      <s:hidden name="common"/>  <s:hidden name="divCount"/>    <s:hidden name="selectedReq"/>    <s:hidden name="editReqFlag"/>  
					     <s:hidden name="checkedCount" value="9"/>  <s:hidden name="reqStatus"/> <s:hidden  name="exportAll" id="exportAll" value="on"/> 
					      <s:hidden name="radioStatus"/>  <s:hidden name="radioReq"/>  <s:hidden name="radioPosition"/>
					    </td> 
					</tr>
				</table>
			</td>
		 </tr>   
		  <tr>
			<td colspan="3"> 
			
		         <table   border="0" cellpadding="0" cellspacing="0" class="formbg" width="100%">
						<tr> 
							<td class="btn-middlebg">
							<div id="tabnav" style="vertical-align: bottom">
							 <ul>
								<li><a id="filtOpt" href="javascript:callTab('filtOpt','filter');">
									<div align="center"><span>Filter </span> </div></a>
								</li>
								<li><a id="sortOpt" href="javascript:callTab('sortOpt','sort');">
									<div align="center"><span>Sorting option</span></div></a>
								</li> 
								
								<li><a  id="colDef" href="javascript:callTab('colDef','column');">
									<div align="center"><span>Column Definition</span></div></a>
								</li> 
								
								<li><a  id="advFilter" href="javascript:callTab('advFilter','advance');">
									<div align="center"><span>Advanced Filter</span></div></a>
								</li>  
							 </ul>
							</div>
							</td>
						</tr>
					</table> 
					
			       <table id="filterDisp" width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg"  > 
			                  <tr>								
								<td width="100%" colspan="3" class="formtext"> <b> <label  class = "set"  id="selectFilter"  name="selectFilter" ondblclick="callShowDiv(this);"><%=label.get("selectFilter")%></label></b> </td>
							 </tr>  
							<tr>								
								<td width="22%" colspan="1" class="formtext">   <input type="radio" value="R"  <s:property value="radioReq"/> name="radioOption"  onclick="callRadioOptionFun('R');" >  <label  class = "set"  id="reqs.code1"  name="reqs.code" ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label> : </td>
								<td width="20%" colspan="1"   ><s:textfield name="reqname" size="25" theme="simple" readonly="true" />  </td>
								<td width="58%" colspan="1" id="f9ReqCode"  > <img src="../pages/images/recruitment/search2.gif" height="15" align="absmiddle" width="16" 
									onclick="callF9ReqCode('f9Val');"></td>							
						 	</tr>  
						 	 
						  	<tr>						
								<td width="22%" colspan="1" class="formtext">  <input type="radio" value="P"  <s:property value="radioPosition"/> name="radioOption"  onclick="callRadioOptionFun('P');" >  <label  class = "set"  id="position1"  name="position" ondblclick="callShowDiv(this);"><%=label.get("position")%></label> : </td>
								<td width="20%" colspan="1" ><s:textfield name="position" size="25" theme="simple" readonly="true" />  </td>
								<td width="58%" colspan="1" id="f9Position" > <img src="../pages/images/recruitment/search2.gif" height="15" align="absmiddle" width="16"  onclick="callF9Position('f9Val');">
								 </td>		 
						  	</tr> 
						  	 
						 	<tr>						
								<td width="22%" colspan="1" class="formtext"><label  class = "set"  id="modeLabel1111"  name="modeLabel" ondblclick="callShowDiv(this);"><%=label.get("modeLabel")%></label> : </td>
								<td width="20%" colspan="1" > <s:select headerKey="1" headerValue="--Select--" name="modeOfAdvertise" list="#{'N':'News Paper','T':'TV Media','W':'Website' ,'O':'Other'}"  />   </td>
								<td width="58%" colspan="1" > &nbsp; </td>		 
						  	</tr> 
						  	
						  	<tr>	
						  	<td width="22%" colspan="1" class="formtext"><label  class = "set"  id="advertiseDateChkLabel1"  name="advertiseDateChkLabel" ondblclick="callShowDiv(this);"><%=label.get("advertiseDateChkLabel")%></label> : </td>					
								 <td width="78%" colspan="2" >
								   <table width="100%">
								      <tr> 
								         <td nowrap="nowrap"> <s:select headerKey="1" headerValue="--Select--" name="dateFilter" list="#{'O':'On','B':'Before','A':'After' ,'OB':'On before' ,'OA':'On after','F':'From'}" onchange="callToDateDispOnClick();" />  </td>
								         <td width="10%" colspan="1" > <s:textfield name="frmDate"  size="9" maxlength="10"  onkeypress="return numbersWithHiphen();"  onfocus="clearText('paraFrm_frmDate','dd-mm-yyyy')" onblur="setText('paraFrm_frmDate','dd-mm-yyyy')" />   </td>
								         <td width="10%" colspan="1" align="left"> <img src="../pages/images/recruitment/Date.gif" id="fromDateIcon" class="iconImage" height="18" align="absmiddle" width="18" onclick="javascript:NewCal('paraFrm_frmDate','DDMMYYYY');">  </td>
								         <td width="14%" colspan="1" > <div id="toDateDivLabel"> <label  class = "set"  id="toDateLabel"  name="toDateLabel" ondblclick="callShowDiv(this);"><%=label.get("toDateLabel")%></label> : </div> </td>
								         <td width="51%" colspan="2" ><div id="toDateDiv">  <s:textfield name="toDate"  size="10" maxlength="10"  onkeypress="return numbersWithHiphen();" onfocus="clearText('paraFrm_toDate','dd-mm-yyyy')" onblur="setText('paraFrm_toDate','dd-mm-yyyy')" /> &nbsp; 
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
								        <td width="21%" colspan="1"> <label  class = "set"  id="labelSelectReq"  name="labelSelectReq" ondblclick="callShowDiv(this);"><%=label.get("labelSelectReq")%></label> :  </td>
								        <td width="39%" colspan="1" > <s:textarea name="selectedReqName" cols="40" rows="2" readonly="true" theme="simple"/>  </td>
								        <td width="40%">
								         <table width="100%" border="0">
								          <tr> 
									         <td  width="45%"  id="selectReqDiv" > <input type="button" class="token" value=" Select Requisition " onclick="callSelectReq('simple');">        </td>
									          <td  width="55%"  id="textAreaId1" > <input type="button" class="token" value=" Edit Selection " onclick="callSelectReq('edit');">     </td>
								          </tr>
								          </table>
								          </td>  
								      </tr>  
								   </table>
								</td>
						  	</tr>    
					</table> 
					
					   <table id="sort"  width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg"> 
					        <tr>								
								<td width="100%" colspan="2" class="formtext"> <b> <label  class = "set"  id="selectSort"  name="selectSort" ondblclick="callShowDiv(this);"><%=label.get("selectSort")%></label></b> </td>
							 </tr>  
							<tr>								
								<td width="17%" colspan="1" class="formtext"><label  class = "set"  id="sortByLabel"  name="sortByLabel" ondblclick="callShowDiv(this);"><%=label.get("sortByLabel")%></label> : </td>
								<td width="83%" colspan="1">  <s:select headerKey="1" headerValue="--Select--" name="sortBy" list="#{'R':'Requisition code' ,'A':'Advertise Date','C':'Cost','O':'Online Response','N':'No of Vacancies'}"  />  </td>							
						 	</tr>  
						 	 <tr>								
								<td width="17%" colspan="1" class="formtext">&nbsp; </td> <s:hidden name="sortByAsc"/> <s:hidden name="sortByDsc"/>
								<td width="83%" colspan="1"> <input type="radio" value="A" name="sortByOrder" <s:property value="sortByAsc"/>  > Ascending    <input type="radio" value="D" name="sortByOrder" <s:property value="sortByDsc"/> > Descending     </td>							
						 	</tr>  
						 	
						 	<tr>								
								<td width="17%" colspan="1" class="formtext"><label  class = "set"  id="thenByLabel"  name="thenByLabel" ondblclick="callShowDiv(this);"><%=label.get("thenByLabel")%></label> : </td>
								<td width="83%" colspan="1">  <s:select headerKey="1" headerValue="--Select--" name="thenBy1" list="#{'R':'Requisition code' ,'A':'Advertise Date','C':'Cost','O':'Online Response','N':'No of Vacancies'}"   />    </td>							
						 	</tr>  
						 	<tr>								
								<td width="17%" colspan="1" class="formtext">&nbsp; </td> <s:hidden name="thenByOrder1Asc"/> <s:hidden name="thenByOrder1Dsc"/>
								<td width="83%" colspan="1"> <input type="radio" value="A" name="thenByOrder1" <s:property value="thenByOrder1Asc"/> > Ascending    <input type="radio" value="D" name="thenByOrder1" <s:property value="thenByOrder1Dsc"/>> Descending     </td>							
						 	  </tr>  
						 	 <tr>								
								<td width="17%" colspan="1" class="formtext"><label  class = "set"  id="thenByLabel"  name="thenByLabel" ondblclick="callShowDiv(this);"><%=label.get("thenByLabel")%></label> : </td>
								<td width="83%" colspan="1">   <s:select headerKey="1" headerValue="--Select--" name="thenBy2"  list="#{'R':'Requisition code' ,'A':'Advertise Date','C':'Cost','O':'Online Response','N':'No of Vacancies'}"   />  </td>							
						 	  </tr>  
						  	 <tr>								
								<td width="17%" colspan="1" class="formtext">&nbsp; <s:hidden name="thenByOrder2Asc"/> <s:hidden name="thenByOrder2Dsc"/> </td>
								<td width="83%" colspan="1"> <input type="radio" value="A" name="thenByOrder2"  <s:property value="thenByOrder2Asc"/>  > Ascending    <input type="radio" value="D" name="thenByOrder2" <s:property value="thenByOrder2Dsc"/> > Descending     </td>							
						 	 </tr>  
					</table> 
					
					<table id="column" width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg"> 
					        <tr>								
								<td width="100%" colspan="6" class="formtext"> <b> <label  class = "set"  id="selectCoumn"  name="selectCoumn" ondblclick="callShowDiv(this);"><%=label.get("selectCoumn")%></label></b> </td>
							 </tr>  
							<tr>								
								<td width="5%" colspan="1" class="formtext"><s:hidden name="reqCodeChk" id="reqCodeChk" value="on" /> <input type="checkbox" name="reqCodeChk1" id="reqCodeChk1" <s:property value="reqCodeChk"/>  disabled="disabled" >  </td>
						        <td width="28%" colspan="1" class="formtext"><label  class = "set"  id="RequisitioncodeLabel1"  name="reqs.code" ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label> </td>
						        <td width="5%" colspan="1" class="formtext"><input type="checkbox" name="modeOfAdvChk" id="modeOfAdvChk"  <s:property value="modeOfAdvChk"/>  onclick="checkedCounter('modeOfAdvChk');"  >  </td>
						        <td width="28%" colspan="1" class="formtext"><label  class = "set"  id="modeLabel0001"  name="modeLabel" ondblclick="callShowDiv(this);"><%=label.get("modeLabel")%></label> </td>
						        <td width="5%" colspan="1" class="formtext"><input type="checkbox" name="advCostChk" id="advCostChk"  <s:property value="advCostChk"/>  onclick="checkedCounter('advCostChk');" >  </td>
						        <td width="28%" colspan="1" class="formtext"><label  class = "set"  id="onlineAdvCostLabel51"  name="onlineAdvCostLabel" ondblclick="callShowDiv(this);"><%=label.get("onlineAdvCostLabel")%></label> </td>
						       </tr>   
						  	
						  	 <tr>	
						  	 <td width="5%" colspan="1" class="formtext"><input type="checkbox" name="positionChk"  id="positionChk"  <s:property value="positionChk"/>  onclick="checkedCounter('positionChk');" >  </td>
						        <td width="28%" colspan="1" class="formtext"><label  class = "set"  id="position11"  name="position" ondblclick="callShowDiv(this);"><%=label.get("position")%></label> </td>
						  	    <td width="5%" colspan="1" class="formtext"><input type="checkbox" name="agencuNameChk" id="agencuNameChk"   <s:property value="agencuNameChk"/>  onclick="checkedCounter('agencuNameChk');"   >  </td>
						        <td width="28%" colspan="1" class="formtext"><label  class = "set"  id="agencuNameChkLabel"  name="agencuNameChkLabel" ondblclick="callShowDiv(this);"><%=label.get("agencuNameChkLabel")%></label> </td>
							    <td width="5%" colspan="1" class="formtext"><input type="checkbox" name="onlineResChk"  id="onlineResChk"   <s:property value="onlineResChk"/> onclick="checkedCounter('onlineResChk');"  >  </td>
						        <td width="28%" colspan="1" class="formtext"><label  class = "set"  id="onilineRespAdvLabel1"  name="onilineRespAdvLabel" ondblclick="callShowDiv(this);"><%=label.get("onlineResChkLabel")%></label> </td>
							   </tr>   
						  	
						  	<tr>  
						  	    <td width="5%" colspan="1" class="formtext"><input type="checkbox" name="numOfVacChk"  id="numOfVacChk"  <s:property value="numOfVacChk" /> onclick="checkedCounter('numOfVacChk');"   >  </td>
						        <td width="28%" colspan="1" class="formtext"><label  class = "set"  id="numOfVacAdvLabel1"  name="numOfVacAdvLabel" ondblclick="callShowDiv(this);"><%=label.get("numOfVacAdvLabel")%></label> </td>
						  	   <td width="5%" colspan="1" class="formtext"><input type="checkbox" name="advDateChk"  id="advDateChk"  <s:property value="advDateChk"/>  onclick="checkedCounter('advDateChk');" >  </td>
						       <td width="28%" colspan="1" class="formtext"><label  class = "set"  id="advertiseDateChkLabel2"  name="advertiseDateChkLabel" ondblclick="callShowDiv(this);"><%=label.get("advertiseDateChkLabel")%></label> </td>
						    </tr>    
						     
				</table> 
				
				<table id="advance" width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg"> 
			            	 <tr>								
								<td width="100%" colspan="3" class="formtext"> <b> <label  class = "set"  id="selectAdvanceFilter"  name="selectAdvanceFilter" ondblclick="callShowDiv(this);"><%=label.get("selectAdvanceFilter")%></label> </b> </td>
							 </tr> 
							<tr>								
								<td width="22%" colspan="1" class="formtext"> <label  class = "set"  id="numOfVacAdvLabel"  name="numOfVacAdvLabel" ondblclick="callShowDiv(this);"><%=label.get("numOfVacAdvLabel")%></label> :</td>
						        <td width="10%" colspan="1" class="formtext"> <s:select headerKey="1" headerValue="--Select--" name="numOfVacAdvCom" list="#{'IE':'=','LT':'<','GT':'>','LE':'<=','GE':'>='}" onchange="callAdvCombo('numOfVacAdvCom','numOfVacAdvTxt');"  /> </td>
							    <td width="68%" colspan="1" class="formtext"> <s:textfield name="numOfVacAdvTxt" size="15" onkeypress="return numbersOnly();" maxlength="10" />  </td>
						    </tr>   
						    <tr>								
								<td width="22%" colspan="1" class="formtext"> <label  class = "set"  id="onilineRespAdvLabel"  name="onilineRespAdvLabel" ondblclick="callShowDiv(this);"><%=label.get("onilineRespAdvLabel")%></label> : </td>
						        <td width="10%" colspan="1" class="formtext"> <s:select headerKey="1" headerValue="--Select--" name="onlineRespAdvCom"  list="#{'IE':'=','LT':'<','GT':'>','LE':'<=','GE':'>='}" onchange="callAdvCombo('onlineRespAdvCom','onlineRespAdvTxt');"  /> </td>
							    <td width="68%" colspan="1" class="formtext"> <s:textfield name="onlineRespAdvTxt" size="15" onkeypress="return numbersOnly();" maxlength="10" />  </td>
						    </tr> 
						    <tr>								
								<td width="22%" colspan="1" class="formtext"> <label  class = "set"  id="onlineAdvCostLabel111001"  name="onlineAdvCostLabel" ondblclick="callShowDiv(this);"><%=label.get("onlineAdvCostLabel")%></label> : </td>
						        <td width="10%" colspan="1" class="formtext"> <s:select headerKey="1" headerValue="--Select--" name="costAdvCom"  list="#{'IE':'=','LT':'<','GT':'>','LE':'<=','GE':'>='}"  onchange="callAdvCombo('costAdvCom','costAdvTxt');"   /> </td>
							    <td width="68%" colspan="1" class="formtext"> <s:textfield name="costAdvTxt" size="15" onkeypress="return numbersOnly();" maxlength="10"  />  </td>
						    </tr>  		      
				</table> 
				 </td>
				</tr>	  
			  <tr>
			     <td colspan="3"> 
			       <table width="100%" border="0" cellpadding="1" cellspacing="1" class="formbg"> 
			                   <tr>								
								 <td width="100%" colspan="2" class="formtext"> <b>Display option</b> </td>
							   </tr>  
							  <tr>								
								<td width="100%" colspan="2" class="formtext"> <s:hidden name="hidReportView" />  <s:hidden name="hidReportRadio" />   <input type="radio" value="V" name="reportView"  id="reportViewV" <s:property value="hidReportView"/>  onclick="callReportChk('N');" > <label  class = "set"  id="view.screen"  name="view.screen" ondblclick="callShowDiv(this);"><%=label.get("view.screen")%></label> </td>
							 </tr>  
							  <tr>								
								<td width="17%" colspan="1" class="formtext"> <input type="radio" value="R" name="reportView"  id="reportViewR"  <s:property value="hidReportRadio"/> onclick="callReportChk('Y');">  <label  class = "set"  id="report.type"  name="report.type" ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label>  </td>
								<td width="83%" colspan="1" class="formtext" > <div id="reportTypeDiv"> <s:select  headerKey="1" headerValue="--Select--" name="reportType" list="#{'P':'Pdf','X':'Xls' ,'T':'Doc'}"  /> </div>  </td>
							 </tr> 
							  <tr>								
								<td  nowrap="nowrap" colspan="1" class="formtext">  <label  class = "set"  id="setting.name"  name="setting.name" ondblclick="callShowDiv(this);"><%=label.get("setting.name")%></label> : </td>
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
							<s:if test="%{viewFlag}">
							  <input type="button" class="token" onclick="return callReportVacancies();"	value=" Generate Report" />
							 </s:if>
							<s:submit cssClass="reset" action="AdvertiseReport_reset" theme="simple" value="    Reset"  />  
							<input type="button" class="token"  	value=" Save report criteria" onclick="callSave();"/>
							</td> 
						  </tr>
					  </table>
					</td>					
			    </tr>					
			</table>
</s:form>

<script type="text/javascript"> 


function callRadioOptionFun(id)
{ 
  if(id=="R")
	{
	  callF9ReqCode('radio');
	} 
	if(id=="P")
	{
	  callF9Position('radio');
	}
	document.getElementById('paraFrm_radioStatus').value=id;
	
}

 callOnBackTab();
 function callOnBackTab()
 {
 var fieldName =["paraFrm_frmDate","paraFrm_toDate"];
   setTextArray(fieldName,"dd-mm-yyyy");
   
 var backFlag =   document.getElementById('paraFrm_backFlag').value;
 var aId =   document.getElementById('paraFrm_aId').value;
 var tabId =   document.getElementById('paraFrm_divId').value; 
 
  if(backFlag=='true'){
   callTab(aId,tabId);
   document.getElementById('paraFrm_backFlag').value="false";
   } else{
   callOnLoad(); 
   }
   callToDateDisp();
   var reqCode = document.getElementById('paraFrm_reqCode').value; 
   var positionId = document.getElementById('paraFrm_positionId').value;
   if(reqCode!=""){
     callF9ReqCode('radio');
   }  
   if(positionId!=""){
    callF9Position('radio');
   } 
   
   
 }
 
callMeOnload();
function callMeOnload()
{   
  radioStatus =document.getElementById('paraFrm_radioStatus').value;
  callRadioOptionFun(radioStatus);
    if(radioStatus=="")
   {
   document.getElementById('f9ReqCode').style.display='none';  
   document.getElementById('f9Position').style.display='none'; 
  } 
  
  if(radioStatus=="C")
  { 
   document.getElementById('paraFrm_radioReq').value="checked";
  }  
    if(radioStatus=="P")
  { 
   document.getElementById('paraFrm_radioPosition').value="checked";
  }
  
  var settingVal = document.getElementById('paraFrm_searchSetting').value;
  var dateFilter = document.getElementById('paraFrm_dateFilter').value; 
  var hidReportRadio = document.getElementById('paraFrm_hidReportRadio').value;   
  var divCount = document.getElementById('paraFrm_divCount').value;   
  var selectedReqName =  document.getElementById('paraFrm_selectedReqName').value ;  
   var backFlag =   document.getElementById('paraFrm_backFlag').value; 
    if((settingVal!="B" ||backFlag=='false') && selectedReqName!="" )
	 {   
	  document.getElementById('textAreaId1').style.display='';    
    }else{
       document.getElementById('textAreaId1').style.display='none';  
    } 
    
	if(dateFilter=="F")
	 { 
	   document.getElementById('toDateDiv').style.display=''; 
	   document.getElementById('toDateDivLabel').style.display='';     
	 }else{
	   document.getElementById('toDateDiv').style.display='none'; 
	   document.getElementById('toDateDivLabel').style.display='none';    
	 }
	 
	 
	 if(hidReportRadio=="checked")
	 { 
	  document.getElementById('reportTypeDiv').style.display=''; 
	 }else{
	  document.getElementById('reportTypeDiv').style.display='none'; 
	 }
}

  function callAdvCombo(filedName,resetFiled)
  {
  filedClick = document.getElementById('paraFrm_'+filedName).value;
  resetFiledName= document.getElementById('paraFrm_'+resetFiled).value;
   if(filedClick=="1") 
   {
    document.getElementById('paraFrm_'+resetFiled).value="";
   }
  }
 
 function callOnLoad()
 {
   document.getElementById('filterDisp').style.display='';
   document.getElementById('sort').style.display='none'; 
   document.getElementById('column').style.display='none';  
   document.getElementById('advance').style.display='none';    
   
   
	document.getElementById('filtOpt').className = '';
	document.getElementById('sortOpt').className = '';
	document.getElementById('colDef').className = '';
	document.getElementById('advFilter').className = '';
	document.getElementById('filtOpt').className = 'on';
	document.getElementById('paraFrm_aId').value =  'filtOpt';
	document.getElementById('paraFrm_divId').value = 'filter';
		
   
 }

function callShowRecord()
{ 
var settingVal = document.getElementById('paraFrm_searchSetting').value;
if(settingVal=="B")
{
 document.getElementById('paraFrm').action="AdvertiseReport_reset.action";
}else{
 document.getElementById('paraFrm').action="AdvertiseReport_viewSavedRecord.action";
 }
 document.getElementById('paraFrm').submit();
}

function callSave()
{  
    var settingVal = document.getElementById('paraFrm_searchSetting').value;
    if(settingVal=="B")
    { var settingName = document.getElementById('paraFrm_settingName').value;
      if(LTrim(settingName)=="")
      {
        alert("Please enter the "+document.getElementById('setting.name').innerHTML.toLowerCase());
	    document.getElementById('paraFrm_settingName').focus();
	    return false; 	 
      } 
    }
    
    // filter on tab 
	       var dateFilter = document.getElementById('paraFrm_dateFilter').value;
	       var frmDate = document.getElementById('paraFrm_frmDate').value;
	       var reqCode = document.getElementById('paraFrm_reqCode').value;
	      
	      if(reqCode=="")
	        {
		      if(dateFilter!="1")
		      {  	
		        if(frmDate=="dd-mm-yyyy")
		         {
		          callTab('filtOpt','filter');
		         alert("Please enter the "+document.getElementById('advertiseDateChkLabel1').innerHTML.toLowerCase());
			     document.getElementById('paraFrm_frmDate').focus();
			     return false; 	 
		        }  
		          if(!validateDate('paraFrm_frmDate', 'advertiseDateChkLabel1'))
		          {  return false;
		        } 
		        if(dateFilter=="F")
		         {
		           var toDate = document.getElementById('paraFrm_toDate').value;  		           
			        callTab('filtOpt','filter');  
			        clearText('paraFrm_toDate','dd-mm-yyyy');
			      if(!dateDifferenceEqual(frmDate,toDate,"paraFrm_toDate",'advertiseDateChkLabel1','toDateLabel'))
		           {
		            return false;
		           } 
		           if(!validateDate('paraFrm_toDate', 'toDateLabel'))
		          {  return false;
		           } 
		            setText('paraFrm_toDate','dd-mm-yyyy');
		         }
		     }
	    }
   	 
   	  // for sort the filter   
   	  var sortBy = document.getElementById('paraFrm_sortBy').value;
   	  var thenBy1 = document.getElementById('paraFrm_thenBy1').value;
   	  var thenBy2 = document.getElementById('paraFrm_thenBy2').value; 
   	  
   	  if(sortBy!="1" &&thenBy1!="1"){
   	    if(sortBy==thenBy1){ 
   	      callTab('sortOpt','sort');
   	     alert("Duplicate sorting option."); 
   	     document.getElementById('paraFrm_thenBy1').focus();
   	     return false;
   	    } 
   	  }
   	  
   	  if(sortBy!="1" &&thenBy2!="1"){
   	    if(sortBy==thenBy2){ 
   	      callTab('sortOpt','sort');
   	     alert("Duplicate sorting option."); 
   	     document.getElementById('paraFrm_thenBy2').focus();
   	     return false;
   	    } 
   	  }
   	  
   	    if(thenBy1!="1" &&thenBy2!="1"){
   	    if(thenBy1==thenBy2){ 
   	      callTab('sortOpt','sort');
   	     alert("Duplicate sorting option."); 
   	     document.getElementById('paraFrm_thenBy2').focus();
   	     return false;
   	    } 
   	  } 
   	  // == end of sort======
   	  
	 
	         var numOfVacAdvCom = document.getElementById('paraFrm_numOfVacAdvCom').value;
	         var numOfVacAdvTxt = document.getElementById('paraFrm_numOfVacAdvTxt').value; 
	          
	         var onlineRespAdvCom = document.getElementById('paraFrm_onlineRespAdvCom').value;
	         var onlineRespAdvTxt = document.getElementById('paraFrm_onlineRespAdvTxt').value;  
	         
	         var costAdvCom = document.getElementById('paraFrm_costAdvCom').value;
	         var costAdvTxt = document.getElementById('paraFrm_costAdvTxt').value;  
	         
	       
	         
	         if(numOfVacAdvCom!="1")
	         {
	           if(numOfVacAdvTxt==""){
	            callTab('advFilter','advance');
		         alert("Please enter the "+document.getElementById('numOfVacAdvLabel').innerHTML.toLowerCase());
			     document.getElementById('paraFrm_numOfVacAdvTxt').focus();
			     return false; 	
			     } 
	         }    
	        
	         if(onlineRespAdvCom!="1")
	         {
	           if(onlineRespAdvTxt==""){
	           callTab('advFilter','advance');
		         alert("Please enter the "+document.getElementById('onilineRespAdvLabel').innerHTML.toLowerCase());
			     document.getElementById('paraFrm_onlineRespAdvTxt').focus();
			     return false; 	
			     } 
	        }   
	         if(costAdvCom!="1")  {  
	           if(costAdvTxt==""){
	             callTab('advFilter','advance');
		         alert("Please enter the "+document.getElementById('onlineAdvCostLabel').innerHTML.toLowerCase());
			     document.getElementById('paraFrm_costAdvTxt').focus();
			     return false; 	
			     }  
		     }     
       var fieldName =["paraFrm_frmDate","paraFrm_toDate"];
       clearTextArray(fieldName,"dd-mm-yyyy"); 
   document.getElementById('paraFrm').action="AdvertiseReport_save.action";
   document.getElementById('paraFrm').submit();
}
function checkedCounter(fieldName)
{ 
	var checkedCount =  document.getElementById('paraFrm_checkedCount').value; 
	 if(checkedCount=="" || checkedCount=='null')
	 {
	  checkedCount="0";
	 }else{  
		 if(document.getElementById(fieldName).checked==true){ 
		 checkedCount= eval(eval(checkedCount)+1); 
		 document.getElementById('paraFrm_checkedCount').value =checkedCount;
		 }else{ 
		checkedCount= eval(eval(checkedCount)-1);
		 document.getElementById('paraFrm_checkedCount').value =checkedCount;
		 }
	 }
}
function callF9ReqCode(status) { 
   document.getElementById('f9Position').style.display='none';   
   document.getElementById('selectReqDiv').style.display='none';   
   document.getElementById('textAreaId1').style.display='none';  
   document.getElementById('f9ReqCode').style.display='';  
   
   document.getElementById('fromDateIcon').style.display='none'; 
   document.getElementById('paraFrm_frmDate').readOnly='true'; 
   document.getElementById('paraFrm_frmDate').value=''; 
    document.getElementById('paraFrm_toDate').value=''; 
   document.getElementById('paraFrm_dateFilter').value='1';  
   document.getElementById('toDateDiv').style.display='none'; 
   document.getElementById('toDateDivLabel').style.display='none';  
   document.getElementById('paraFrm_selectedReqName').value="";
   document.getElementById('paraFrm_selectedReq').value="";    
   document.getElementById('paraFrm_positionId').value="";
   document.getElementById('paraFrm_position').value="";  
   if(status=="f9Val"){
   callsF9(500,325,'AdvertiseReport_f9actionReqName.action'); 
   }
}

 
 
function callF9Position(status)
{ 
   document.getElementById('f9Position').style.display='';   
   document.getElementById('paraFrm_reqname').value="";
   document.getElementById('paraFrm_reqCode').value="";  
   document.getElementById('paraFrm_frmDate').readOnly='';   
   document.getElementById('fromDateIcon').style.display='';  
   document.getElementById('selectReqDiv').style.display='';   
   
    var settingVal = document.getElementById('paraFrm_searchSetting').value;
	var selectedReqName =  document.getElementById('paraFrm_selectedReqName').value ;  
	    var backFlag =   document.getElementById('paraFrm_backFlag').value;
     if((settingVal!="B" ||backFlag=='false') && selectedReqName!="" )
	 {   
	  document.getElementById('textAreaId1').style.display='';    
    }else{
       document.getElementById('textAreaId1').style.display='none';  
    }  

   document.getElementById('f9ReqCode').style.display='none';  
   setText('paraFrm_frmDate','dd-mm-yyyy');
   if(status=="f9Val"){
   callsF9(500,325,'AdvertiseReport_f9Position.action');
   }
} 

  function callToDateDisp()
 {  
   var dateFilter= document.getElementById('paraFrm_dateFilter').value;
   var  reqCode = document.getElementById('paraFrm_reqCode').value;
    
   if(dateFilter=="F"){
      if(reqCode==""){
     document.getElementById('toDateDiv').style.display=''; 
     document.getElementById('toDateDivLabel').style.display='';    
     }
   }else {
     document.getElementById('toDateDiv').style.display='none'; 
     document.getElementById('toDateDivLabel').style.display='none';       
   } 
 } 
 
   function callToDateDispOnClick()
 {
   var dateFilter= document.getElementById('paraFrm_dateFilter').value;
   var  reqCode = document.getElementById('paraFrm_reqCode').value;
   if(dateFilter=="F"){
      if(reqCode==""){
     document.getElementById('toDateDiv').style.display=''; 
     document.getElementById('toDateDivLabel').style.display='';   
     setText("paraFrm_toDate","dd-mm-yyyy"); 
     }
   }else {
     document.getElementById('toDateDiv').style.display='none'; 
     document.getElementById('toDateDivLabel').style.display='none';       
   } 
 } 
 
 
 function callReportChk(status)
 { 
	 if(status=="Y"){
	   document.getElementById('reportTypeDiv').style.display=''; 
	   document.getElementById('paraFrm_reqStatus').value ="R"; 
	  }else{
	   document.getElementById('reportTypeDiv').style.display='none';  
	   document.getElementById('paraFrm_reqStatus').value ="V"; 
	  }
 }
 function callTab(aId,tabId)
 {     
        document.getElementById('filtOpt').className = ''; 
		document.getElementById('sortOpt').className = ''; 
		document.getElementById('colDef').className = ''; 
		document.getElementById('advFilter').className = ''; 
		document.getElementById(aId).className = 'on'; 
		document.getElementById('paraFrm_aId').value =  aId; 
		document.getElementById('paraFrm_divId').value = tabId;  
     document.getElementById('paraFrm_divCount').value ="someThing"; 
	  if(tabId=="filter")
	  {   
	   document.getElementById('filterDisp').style.display='';
	   document.getElementById('sort').style.display='none'; 
	   document.getElementById('column').style.display='none';  
	   document.getElementById('advance').style.display='none';  
	  }
	 
	  if(tabId=="sort")
	  {  
	   
	   document.getElementById('sort').style.display='';
	   document.getElementById('filterDisp').style.display='none'; 
	   document.getElementById('column').style.display='none'; 
	   document.getElementById('advance').style.display='none';    
	  }
	  
	   if(tabId=="column")
	   { 
	   document.getElementById('column').style.display='';   
	   document.getElementById('sort').style.display='none';
	   document.getElementById('filterDisp').style.display='none'; 
	   document.getElementById('advance').style.display='none';  
	  } 
	  
	   if(tabId=="advance")
	   { 
	   document.getElementById('advance').style.display='';  
	   document.getElementById('column').style.display='none';   
	   document.getElementById('sort').style.display='none';
	   document.getElementById('filterDisp').style.display='none';  
	  }
 }

function callReportVacancies()
 { 	  
  // filter on tab 
	       var dateFilter = document.getElementById('paraFrm_dateFilter').value;
	       var frmDate = document.getElementById('paraFrm_frmDate').value;
	       var reqCode = document.getElementById('paraFrm_reqCode').value;
	      
	      if(reqCode=="")
	        {
		      if(dateFilter!="1")
		      {  	
		        if(frmDate=="dd-mm-yyyy")
		         {
		          callTab('filtOpt','filter');
		         alert("Please enter the "+document.getElementById('advertiseDateChkLabel1').innerHTML.toLowerCase());
			     document.getElementById('paraFrm_frmDate').focus();
			     return false; 	 
		        }  
		          if(!validateDate('paraFrm_frmDate', 'advertiseDateChkLabel1'))
		          {  return false;
		        } 
		        if(dateFilter=="F")
		         {
		           var toDate = document.getElementById('paraFrm_toDate').value;  		           
			        callTab('filtOpt','filter');  
			        clearText('paraFrm_toDate','dd-mm-yyyy');
			      if(!dateDifferenceEqual(frmDate,toDate,"paraFrm_toDate",'advertiseDateChkLabel1','toDateLabel'))
		           {
		            return false;
		           } 
		           if(!validateDate('paraFrm_toDate', 'toDateLabel'))
		          {  return false;
		           } 
		            setText('paraFrm_toDate','dd-mm-yyyy');
		         }
		     }
	    }
 
   	 // for sort the filter   
   	  var sortBy = document.getElementById('paraFrm_sortBy').value;
   	  var thenBy1 = document.getElementById('paraFrm_thenBy1').value;
   	  var thenBy2 = document.getElementById('paraFrm_thenBy2').value; 
   	  
   	  if(sortBy!="1" &&thenBy1!="1"){
   	    if(sortBy==thenBy1){ 
   	      callTab('sortOpt','sort');
   	     alert("Duplicate sorting option."); 
   	     document.getElementById('paraFrm_thenBy1').focus();
   	     return false;
   	    } 
   	  }
   	  
   	  if(sortBy!="1" &&thenBy2!="1"){
   	    if(sortBy==thenBy2){ 
   	      callTab('sortOpt','sort');
   	     alert("Duplicate sorting option."); 
   	     document.getElementById('paraFrm_thenBy2').focus();
   	     return false;
   	    } 
   	  }
   	  
   	    if(thenBy1!="1" &&thenBy2!="1"){
   	    if(thenBy1==thenBy2){ 
   	      callTab('sortOpt','sort');
   	     alert("Duplicate sorting option."); 
   	     document.getElementById('paraFrm_thenBy2').focus();
   	     return false;
   	    } 
   	  } 
   	  // == end of sort======
	 
	         var numOfVacAdvCom = document.getElementById('paraFrm_numOfVacAdvCom').value;
	         var numOfVacAdvTxt = document.getElementById('paraFrm_numOfVacAdvTxt').value;  
	         var onlineRespAdvCom = document.getElementById('paraFrm_onlineRespAdvCom').value;
	         var onlineRespAdvTxt = document.getElementById('paraFrm_onlineRespAdvTxt').value;   
	         var costAdvCom = document.getElementById('paraFrm_costAdvCom').value;
	         var costAdvTxt = document.getElementById('paraFrm_costAdvTxt').value;   
	         
	         if(numOfVacAdvCom!="1")
	         {
	           if(numOfVacAdvTxt==""){
	            callTab('advFilter','advance');
		         alert("Please enter the "+document.getElementById('numOfVacAdvLabel').innerHTML.toLowerCase());
			     document.getElementById('paraFrm_numOfVacAdvTxt').focus();
			     return false; 	
			     } 
	         }    
	        
	         if(onlineRespAdvCom!="1")
	         {
	           if(onlineRespAdvTxt==""){
	           callTab('advFilter','advance');
		         alert("Please enter the "+document.getElementById('onilineRespAdvLabel').innerHTML.toLowerCase());
			     document.getElementById('paraFrm_onlineRespAdvTxt').focus();
			     return false; 	
			     } 
	        }   
	         if(costAdvCom!="1")  {  
	           if(costAdvTxt==""){
	             callTab('advFilter','advance');
		         alert("Please enter the "+document.getElementById('onlineAdvCostLabel').innerHTML.toLowerCase());
			     document.getElementById('paraFrm_costAdvTxt').focus();
			     return false; 	
			     }  
		     }      
	  var fieldName =["paraFrm_frmDate","paraFrm_toDate"];
       clearTextArray(fieldName,"dd-mm-yyyy");
	if(document.getElementById("reportViewV").checked)
	{
	 document.getElementById('paraFrm').action="AdvertiseReport_viewOnScreen.action";
     document.getElementById('paraFrm').submit();
	}else{	
	   var reportType = document.getElementById("paraFrm_reportType").value; 
	   if(reportType=="1"){
	    alert("Please select "+document.getElementById('report.type').innerHTML.toLowerCase());
	    document.getElementById("paraFrm_reportType").focus();
	   return false;
	   }
  	  callReport('AdvertiseReport_report.action');   		
  	}
 }
 
 function callSelectReq(status)
 { 
	   var dateFilter = document.getElementById('paraFrm_dateFilter').value;
       var frmDate = document.getElementById('paraFrm_frmDate').value;
	      
	      if(dateFilter!="1")
	      {
	        if(frmDate=="dd-mm-yyyy")
	         {
	          callTab('filtOpt','filter');
	         alert("Please enter the "+document.getElementById('advertiseDateChkLabel1').innerHTML.toLowerCase());
		     document.getElementById('paraFrm_frmDate').focus();
		     return false; 	 
	        }   
	        if(dateFilter=="F")
	         {
	           var toDate = document.getElementById('paraFrm_toDate').value; 
		         callTab('filtOpt','filter');  
		         clearText('paraFrm_toDate','dd-mm-yyyy');
		      if(!dateDifferenceEqual(frmDate,toDate,"paraFrm_toDate",'advertiseDateChkLabel1','toDateLabel'))
	           {
	            return false;
	           } 
	           setText('paraFrm_toDate','dd-mm-yyyy');
	         }
	     }
	     
   document.getElementById('f9ReqCode').style.display='none'; 
  if(status=='edit'){
    document.getElementById('paraFrm_selectedReqName').value =document.getElementById('paraFrm_hidSelectedReqName').value;
    document.getElementById('paraFrm_editReqFlag').value="true";    
  }else{
   document.getElementById('paraFrm_editReqFlag').value="false"; 
   document.getElementById('paraFrm_selectedReqName').value="";
   document.getElementById('paraFrm_selectedReq').value=""; 
  } 
   
   document.getElementById('paraFrm').target="wind";
   var wind = window.open('','wind','width=700,height=450,scrollbars=no,resizable=yes,menubar=no,top=200,left=150');	 
   document.getElementById('paraFrm').action="AdvertiseReport_displayReq.action";
   document.getElementById('paraFrm').submit();
   document.getElementById('paraFrm').target="";
 }
 
</script>
