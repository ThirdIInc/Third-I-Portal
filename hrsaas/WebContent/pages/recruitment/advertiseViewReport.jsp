 <%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="AdvertiseReport" validate="true" id="paraFrm" theme="simple"> 
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg">
	  <tr>
			<td colspan="3" width="100%">  <s:hidden name="searchFlag"/>
				 <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt"><strong class="formhead"><img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="text_head">Advertisement Analysis</strong></td>
						<td width="3%" valign="top" class="otxt"><div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
					</tr>
				</table>   
			</td>
		   </tr>  
		   
           <tr>
	         <td colspan="3">
	           <table width="100%" border="0" cellpadding="0" cellspacing="0" >
					<tr>
					    <td  width="70%" > <input type="button" value="  Back  " class="token" onclick="callBack();">  
					     <s:if test="noData"> </s:if> <s:else>
					     <input type="button" value=" Export In Pdf " class="token" onclick="callReportForDisp('P');"> 
					     <input type="button" value=" Export In Xls " class="token" onclick="callReportForDisp('X');"> 
					    <input type="button" value=" Export In Doc " class="token" onclick="callReportForDisp('T');"> 
					    </s:else>
					    </td>
					    
					    <td  width="30%" > <s:if test="noData"> </s:if> <s:else> <label class="set" name="export.all" id="export.all" ondblclick="callShowDiv(this);"><%=label.get("export.all")%></label> : <input type="checkbox" name="exportAll" id="exportAll" checked="checked"  onclick="callChkBox('exportAll');" > </s:else>   </td>
					</tr>
				</table>
			</td>
		  </tr>
	
		    <tr>
			<td width="100%">  <s:hidden name="searchSetting"/>
			 <s:hidden name="myPage"/> <s:hidden name="show"  />   <s:hidden name="checkedCount" value="9"/>
			 <s:hidden name="frmDate"/> <s:hidden name="toDate"  /> <s:hidden name="reqname"/> <s:hidden name="reqCode"  />   <s:hidden name="dataLength"  /> 
			  <s:hidden name="reportType"  /> <s:hidden name="hidSelectedReqName"/>
			 <s:hidden name="reqname1"/>   <s:hidden name="selectedReq"/> <s:hidden name="position"  />
			 <s:hidden name="positionId"/> <s:hidden name="dateFilter"  />  <s:hidden name="thenByOrder2"/> <s:hidden name="thenBy2"  />  
			 <s:hidden name="sortBy"/> <s:hidden name="sortByOrder"  /> <s:hidden name="thenBy1"/> <s:hidden name="thenByOrder1"  />
			 
			 
			 <s:hidden name="numOfVacAdvCom"/> <s:hidden name="numOfVacAdvTxt"  />  
             <s:hidden name="onlineRespAdvCom"/> <s:hidden name="onlineRespAdvTxt"  /> <s:hidden name="costAdvCom"/> <s:hidden name="costAdvTxt"  />
	         
              <s:hidden name="reqCodeChk"/> <s:hidden name="numOfVacChk"  /> <s:hidden name="modeOfAdvChk"/> <s:hidden name="agencuNameChk"  /> 
             <s:hidden name="advDateChk"/> <s:hidden name="advCostChk"  /> <s:hidden name="onlineResChk"/> <s:hidden name="positionChk"  />
             <s:hidden name='aId'/> <s:hidden name='divId'/>  <s:hidden name='backFlag'/> <s:hidden name='modeOfAdvertise'/>  
               <s:hidden name='settingName'/>  <s:hidden name="settingNameDup" />  <s:hidden name='selectedReqName'/>
			  <s:hidden name="radioStatus"/>  <s:hidden name="radioReq"/>  <s:hidden name="radioPosition"/>
				<table width="100%" border="0"  cellpadding="0" cellspacing="0" class="formbg"><!--Table 3-->
					    <tr>
						     <td>
									<table width="100%" border="0" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td height="27" class="text_head"><strong>Advertisement List </strong></td>
     					    <%
							int totalPage = (Integer) request.getAttribute("totalPage");
							int pageNo = (Integer) request.getAttribute("PageNo");
							%>
     					 <s:if test="noData"> </s:if> <s:else>
 							<td align="right"><b>Page:</b> 
					     <input type="hidden" name="totalPage"  id="totalPage" value="<%=totalPage%>">
						 	<a href="#" onclick="callPage('1','F');"  >
										<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
									</a>&nbsp;
									<a href="#" onclick="callPage('P','P');" >
										<img  title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
									</a> 
							    <input type ="text" name="pageNoField" id="pageNoField" theme="simple" size="3"  value="<%= pageNo%>"  onkeypress="callPageText(event);return numbersOnly()"   maxlength="4" title="press Enter" /> of <%=totalPage%>
						 		 		<a href="#" onclick="callPage('N','N')" >
											<img  title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
										</a>&nbsp;
										<a href="#" onclick="callPage('<%=totalPage%>','L');" >
											<img title="Last Page"  src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
										</a> 
							</td>
					 </s:else>
  
										</tr>
										 
										</td>
									</tr> 
								 </table>
							 </td> 
					</tr>  
   
					<tr>
						<td width="100%"> 
						 <table width="100%" border="0" cellpadding="1" cellspacing="1" class="sortable"><!--Table 5-->
					      <tr> 
						    <td colspan="3" width="100%">
							<table width="100%" border="0" cellpadding="1" cellspacing="1" class="sortable"><!--Table 6-->
								<tr>   
									<td width="5%" valign="top" class="formth" nowrap="nowrap"><label class="set" name="serial.no" id="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
									<s:if test="reqCodeChkFlag">
									<td width="20%" valign="top" class="formth"><label  class = "set" name="reqs.code" id="reqs.code11" ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label></td>
									</s:if>
									<s:if test="positionChkFlag">
									<td width="20%" valign="top" class="formth"><label  class = "set" name="position" id="position11"  ondblclick="callShowDiv(this);"><%=label.get("position")%></label></td> 
			            			</s:if>
			            			<s:if test="numOfVacChkFlag">
			            			<td  valign="top" class="formth"><label  class = "set" name="numOfVacAdvLabel" id="numOfVacAdvLabel11"  ondblclick="callShowDiv(this);"><%=label.get("numOfVacAdvLabel")%></label></td> 
									</s:if>
									 <s:if test="modeOfAdvChkFlag">
									<td width="15%" valign="top" class="formth" ><label  class = "set" name="modeLabel" id="modeLabel21" ondblclick="callShowDiv(this);"><%=label.get("modeLabel")%></label></td>
									</s:if>
									 <s:if test="agencuNameChkFlag">
									<td width="15%" valign="top" class="formth" ><label  class = "set" name="agencuNameChkLabel" id="agencuNameChkLabel" 	ondblclick="callShowDiv(this);"><%=label.get("agencuNameChkLabel")%></label></td> 
			            	 	    </s:if>
			            	 	    <s:if test="advDateChkFlag">
			            	 	    <td  valign="top" class="formth" ><label  class = "set" name="advertiseDateChkLabel" id="advertiseDateChkLabel" ondblclick="callShowDiv(this);"><%=label.get("advertiseDateChkLabel")%></label></td> 
			            		    </s:if>
			            		     <s:if test="advCostChkFlag">
			            		    <td  valign="top" class="formth"  ><label  class = "set" name="onlineAdvCostLabel" id="onlineAdvCostLabel8811"  ondblclick="callShowDiv(this);"><%=label.get("onlineAdvCostLabel")%></label></td> 
				            	 	</s:if>
				            	 	 <s:if test="onlineResChkFlag">
				            	 	 <td width="10%" valign="top" class="formth" ><label  class = "set" name="onilineRespAdvLabel" id="onilineRespAdvLabel"  ondblclick="callShowDiv(this);"><%=label.get("onilineRespAdvLabel")%></label></td> 
				            		</s:if> 
				              	</tr> 
	
							 	<% int cnt=0; 
								   int pgNo = pageNo * 20 - 20;
								   %> 
								     
								<s:iterator value="advertiseViewList">  
									<tr> 
									<td width="5%"  class="sortableTD" nowrap="nowrap">  <%++cnt;%> <%=++pgNo%>
										 <s:hidden name="ivReqCode"/>  <s:hidden name="ivPostion"/>  <s:hidden name="ivNoVac"/>
										 <s:hidden name="ivModeOfAdv"/>  <s:hidden name="ivAgencyName"/>  <s:hidden name="ivAdvDate"/>
										 <s:hidden name="ivAdvCost"/>  <s:hidden name="ivOnlineResp"/>   
									</td>  
	                                 <s:if test="reqCodeChkFlag">
									<td width="25%" class="sortableTD"  > <s:property value="ivReqCode"/> </td>
									</s:if>
									<s:if test="positionChkFlag">
									<td width="15%"  class="sortableTD"> <s:property value="ivPostion"/></td> 
									 </s:if>
									 <s:if test="numOfVacChkFlag">
									<td  class="sortableTD" nowrap="nowrap" align="right"   > <s:property value="ivNoVac"/> &nbsp; </td>
									</s:if>
									 <s:if test="modeOfAdvChkFlag">
									<td width="15%"  class="sortableTD"> <s:property value="ivModeOfAdv"/> &nbsp;</td> 
									</s:if>
									 <s:if test="agencuNameChkFlag"> 
									<td width="15%" class="sortableTD"  > <s:property value="ivAgencyName"/> &nbsp; </td>
									</s:if>
									 <s:if test="advDateChkFlag">
									<td  nowrap="nowrap" class="sortableTD" align="center" > <s:property value="ivAdvDate"/> &nbsp;</td> 
									</s:if>
									 <s:if test="advCostChkFlag">
									<td class="sortableTD" nowrap="nowrap"  align="right"  > <s:property value="ivAdvCost"/> </td>
									</s:if>
									 <s:if test="onlineResChkFlag">
									<td width="10%"  class="sortableTD" align="right"> <s:property value="ivOnlineResp"/></td> 
									</s:if>  
								</tr>  
								</s:iterator>
								<s:if test="noData">
									<tr>
										<td width="100%" colspan="7" align="center"><font
											color="red">There is no data to display.</font></td>
									</tr>
								</s:if> 
								<s:else>
								  <tr>
										<td width="100%" colspan="11" align="right">  <b>Total records : <s:property value="dataLength"/></b>  </td>
								 </tr> 
								</s:else>
							</table>
							</td>
							</tr>		
							</table><!--Table 5-->
						</td>
					</tr>					
				</table><!--Table 3-->
			</td>
		</tr><!--end of vacancy listing-->  
		  <tr>
	         <td colspan="3">
	           <table width="100%" border="0" cellpadding="0" cellspacing="0" >
					<tr>
					    <td  width="70%" > <input type="button" value="  Back  " class="token" onclick="callBack();">   
					     <s:if test="noData"> </s:if> <s:else>
			            <input type="button" value=" Export In Pdf " class="token" onclick="callReportForDisp('P');">  
					     <input type="button" value=" Export In Xls " class="token" onclick="callReportForDisp('X');">  
					      <input type="button" value=" Export In Doc " class="token" onclick="callReportForDisp('T');">
					      </s:else>
					       </td>
					    <td  width="30%" >  <s:if test="noData"> </s:if> <s:else> <label class="set" name="export.all" id="export.all" ondblclick="callShowDiv(this);"><%=label.get("export.all")%></label> : <input type="checkbox" name="exportAll_bot" id="exportAll_bot" checked="checked" onclick="callChkBox('exportAll_bot');" > </s:else>  </td>
					</tr>
				</table>
			</td>
		  </tr>  
  </table>
</s:form>
 <script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script> 
window.onload=   document.getElementById('pageNoField').focus();
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
 
function callReportForDisp(reportType)
{
  document.getElementById('paraFrm_reportType').value=reportType;
  callReport('AdvertiseReport_report.action');   
}
function callBack()
 {
 
   document.getElementById('paraFrm_radioReq').value=""; 
   document.getElementById('paraFrm_radioPosition').value="";
 
 radioStatus =document.getElementById('paraFrm_radioStatus').value;
  if(radioStatus=="R")
  { 
   document.getElementById('paraFrm_radioReq').value="checked";
  }  
  if(radioStatus=="P")
  { 
   document.getElementById('paraFrm_radioPosition').value="checked";
  }
  
  
  document.getElementById('paraFrm_backFlag').value="true"; 
  document.getElementById('paraFrm_reportType').value="1";
  document.getElementById('paraFrm').action='AdvertiseReport_callBack.action';
  document.getElementById('paraFrm').submit(); 
 } 
 
 function callPageText(id){ 
		var code;
			if (id.keyCode) 
				code = id.keyCode;
			else
				if (id.which) 
					code = id.which;
					
			if(code==13) 
			{
				pageNo =document.getElementById('pageNoField').value;		
			 	totalPage =document.getElementById('totalPage').value;	
			 	var actPage = document.getElementById('paraFrm_myPage').value; 
			 	if(pageNo!="0"  & pageNo<totalPage & pageNo!="" ){	
				 document.getElementById('paraFrm_myPage').value=pageNo;
		        }
		        if(pageNo==actPage){ 
			      document.getElementById('pageNoField').focus();
			      return false;
		        }
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
		    
			document.getElementById('paraFrm').action='AdvertiseReport_viewOnScreen.action';
			document.getElementById('paraFrm').submit();
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
			document.getElementById('paraFrm').action='AdvertiseReport_viewOnScreen.action';
			document.getElementById('paraFrm').submit();
		}
		
	}	
	
	
  function callPage(id,pageImg){  
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
		document.getElementById('paraFrm').action='AdvertiseReport_viewOnScreen.action';
		document.getElementById('paraFrm').submit(); 
	}	
	 
	 
	 
 function onPage()
   {
  	var val= document.getElementById('selectname').value;
	document.getElementById('paraFrm_show').value=val;
	document.getElementById('paraFrm_myPage').value=eval(val);
	document.getElementById('selectname').value=val;
	document.getElementById('paraFrm').action="AdvertiseReport_viewOnScreen.action";
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

</script>