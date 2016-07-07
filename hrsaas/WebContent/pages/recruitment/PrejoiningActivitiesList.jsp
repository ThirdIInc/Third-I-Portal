<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../common/commonValidations.jsp"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="PrejoiningActivities" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="bgStatus" />
	<s:hidden name="hiddencode" />
	<s:hidden name="prejoinCode" />
	<s:hidden name="candidateCode" />
	<s:hidden name="candidateName" />
	<s:hidden name="reqName" />
	<s:hidden name="reqCode" />
	<s:hidden name="checkListType" />
	<s:hidden name="checkListType" />
	<s:hidden name="DupcheckListType" />
	<s:hidden name="DupcheckListresponce" />
	<s:hidden name="offerDate" />
	<s:hidden name="joinDate" />
	<s:hidden name="reportingName" />
	<s:hidden name="reportingTo" />
	<s:hidden name="Appstatus" />
	<s:hidden name="Buttonpanelcode" />
	<s:hidden name="show" />
	<s:hidden name="myPage" id="myPage" />
	<s:hidden name="modeLength" />
	<s:hidden name="totalRecords" />
	<s:hidden name="positionId" />
	<s:hidden name="candCode1" />
	<s:hidden name="ChkSerch" />
	<s:hidden name="searchFlag" />
<s:hidden name="applyFilterFlag"/>

	<table border="0"  class="formbg" width="100%" >
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Pre
					Joining Activities</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">
				<!-- The Following code Denotes  Include JSP Page For Button Panel -->

				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="75%"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
							<td width="22%">
							
							</td>
						</tr>
					</table>
					</td>
				</tr>

				
				

			    <tr>
			        <td ><img src="../pages/common/css/default/images/space.gif" width="5" height="2" /></td>
			    </tr>
				<tr>
					<td colspan="3"><s:hidden name="bgcheckCode" />
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td height="27" class="formtxt"><a href="#"
								onclick="callFun('P');">Pending List </a> | <a href="#"
								onclick="callFun('C');">Completed List </a></td>

						</tr>	</table></td></tr>
				<tr>
					<td colspan="3"><img
						src="../pages/images/recruitment/space.gif" width="5" height="7" /></td>
				</tr>
				
				<tr>
					<td colspan="3" >

					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="formbg">
						<tr>

							<td colspan="2" nowrap="nowrap"><strong class="text_head"><s:if
								test="searchFlag">Applied Filter</s:if><s:else><label  class = "set" name="searchApply.filter" id="searchApply.filter" 
			            		ondblclick="callShowDiv(this);"><%=label.get("searchApply.filter")%></label></s:else></strong></td>

							<td id="showFilter" align="right" colspan="2"><input
								type="button" value="Show Filter" cssClass="token"
								onclick="return callShowFilter();"></td>

							<td id="hideFilter" align="right"><input type="button"
								value="Hide Filter" cssClass="token"
								onclick="return callHideFilter();"></td>
						</tr>

						<tr>
							<td colspan="4" width="100%">
							<div id="showFilterValue">
							<table  width="100%" >

								<tr><td width="18%">
						            <label  class = "set" name="cand.name" id="cand.name" 
						            	ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label> : </td>
						         <td width="25%" nowrap="nowrap" >
						         	<s:textfield name="candidateName1" size="25" maxlength="50"  onkeypress="return charactersOnly();"/>
						         	<img class="iconImage" src="../pages/images/recruitment/search2.gif" width="15"
											height="16" onclick="javascript:callsF9(500,325,'PrejoiningActivities_f9CandidateAction.action');"/>
									</td>
								
										 <td width="14%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td> 						
			
									<td width="18%"><label  class = "set" name="position" id="position1" 
			            	       	ondblclick="callShowDiv(this);"><%=label.get("position")%></label> :</td>
									<td width="25%" nowrap="nowrap"><s:textfield name="positionName" size="25"
										readonly="true" /><img src="../pages/images/search2.gif"
										class="iconImage" height="16" align="absmiddle" width="15"
										onclick="javascript:callsF9(500,325,'PrejoiningActivities_f9Position.action');"></td>
									</tr>
									
									<tr>
								<td width="18%" ><label  class = "set" name="offfDate" id="offfDate" 
			            		ondblclick="callShowDiv(this);"><%=label.get("offfDate")%></label> :</td>
									<td width="25%" nowrap="nowrap"><s:textfield name="offerFDate" size="25" onkeypress="return numbersWithHiphen();" maxlength="10"
										readonly="false"  /><s:a  href="javascript:NewCal('paraFrm_offerFDate','DDMMYYYY');">
                      			<img class="iconImage" src="../pages/images/recruitment/Date.gif" width="16"
												height="16" border="0" align="absmiddle" />
										</s:a></td>
										 <td width="14%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td> 	
									<td width="18%" ><label  class = "set" name="offtDate" id="offtDate" 
			            		ondblclick="callShowDiv(this);"><%=label.get("offtDate")%></label> :<td width="25%" nowrap="nowrap"><s:textfield name="offerTDate" size="25"
										onkeypress="return numbersWithHiphen();" maxlength="10" readonly="false" /><s:a  href="javascript:NewCal('paraFrm_offerTDate','DDMMYYYY');">
                      			<img class="iconImage" src="../pages/images/recruitment/Date.gif" width="16"
												height="16" border="0" align="absmiddle" />
										</s:a></td>
									</tr>
				             
			                  	<tr>
								<td width="18%" ><label  class = "set" name="joiningFromDate" id="joiningFromDate" 
			            		ondblclick="callShowDiv(this);"><%=label.get("joiningFromDate")%></label> :</td>
									<td width="25%" nowrap="nowrap"><s:textfield name="joinFDate" size="25" onkeypress="return numbersWithHiphen();" maxlength="10"
										readonly="false"  /><s:a  href="javascript:NewCal('paraFrm_joinFDate','DDMMYYYY');">
                      			<img class="iconImage" src="../pages/images/recruitment/Date.gif" width="16"
												height="16" border="0" align="absmiddle" />
										</s:a></td>
										 <td width="14%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td> 	
									<td width="18%" ><label  class = "set" name="joiningToDate" id="joiningToDate" 
			            		ondblclick="callShowDiv(this);"><%=label.get("joiningToDate")%></label> :<td width="25%" nowrap="nowrap"><s:textfield name="joinTDate" size="25"
										onkeypress="return numbersWithHiphen();" maxlength="10" readonly="false" /><s:a  href="javascript:NewCal('paraFrm_joinTDate','DDMMYYYY');">
                      			<img class="iconImage" src="../pages/images/recruitment/Date.gif" width="16"
												height="16" border="0" align="absmiddle" />
										</s:a></td>
									</tr>
								
								
								
								<tr>
									<td width="100%" align="center" colspan="5"><s:submit
										cssClass="token" value=" Apply Filter  "
										onclick="return ApplyFilter();" />&nbsp; <input type="button"
										class="reset" theme="simple" onclick="return calReset();"
										value="Reset " /></td>
								</tr>
							</table>
							</div>
							</td>
						</tr>

						<tr>
							<td width="100%">
							<%
								String[] dispArr = (String[]) request.getAttribute("dispArr");
								if (dispArr != null && dispArr.length > 0) {

									int k = 0;
									int count = 0;
									if (dispArr.length % 2 == 0) {
										k = dispArr.length / 2;

									} else {
										k = (dispArr.length / 2) + 1;

									}
							%>

							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								id="enableFilterValue">

								<%
								for (int m = 0; m < k; m++) {
								%>

								<tr>
									<%
									if (count < dispArr.length) {
									%>

									<td width="50%" height="22" class="formtext"><%=dispArr[count]%>

									</td>
									<%
									count++;
									%>

									<%
									}
									%>


									<%
									if (count < dispArr.length) {
									%>
									<td width="50%" height="22" class="formtext"><%=dispArr[count]%>
									</td>
									<%
									count++;
									%>
									<%
									}
									%>
								</tr>
								<%
									}
									} // end of iff
								%>



								<tr id="clearId">

									<td align="center" colspan="2" width="100%">&nbsp;
									 <input type="button" class="reset" theme="simple" onclick="return callClear();" value="Clear Filter" /> &nbsp; 
									<input type="button" class="token" theme="simple" onclick="return callShowFilter();" value="Edit Filter" /> </td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				
				<tr>
					<td colspan="3"><img
						src="../pages/images/recruitment/space.gif" width="5" height="7" /></td>
				</tr>
				<tr>
					<td colspan="3">
					<table width="100%" border="0" 
						class="formbg">
						<tr>
							<td height="27" class="formtxt"><strong> <%
 	String status = (String) request.getAttribute("stat");
 	if (status == "C") {
 		out.println("Completed List");
 	} else {
 		status = "P";
 		out.println("Pending List");
 	}
 	
 %> </strong></td>  
 
                               <%
							int totalPage = (Integer) request.getAttribute("abc");
							int pageNo = (Integer) request.getAttribute("xyz");
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
					
						<tr>
							<td colspan="3">
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								class="sortable">
								<tr>
									<s:hidden name="bgpendinglistLength" />
									<td width="5%" align="center" class="formth" nowrap="nowrap">

									<b><label class="set" name="serial.no" id="serial.no"
										ondblclick="callShowDiv(this);"> <%=label.get("serial.no")%></label></b>
									</td>
									<td width="20%" align="center" class="formth" nowrap="nowrap">

									<b><label class="set" name="cand.name" id="LcanName"
										ondblclick="callShowDiv(this);"> <%=label.get("cand.name")%></label></b>
									</td>
									<td width="20%" align="center" class="formth"><b><label
										class="set" name="position" id="Lposition"
										ondblclick="callShowDiv(this);"> <%=label.get("position")%></label></b>
									</td>
									<td  align="center" nowrap="nowrap" class="formth"><b><label
										class="set" name="offer.date" id="Loffd"
										ondblclick="callShowDiv(this);"> <%=label.get("offer.date")%></label></b>
									</td>
									
									<td  align="center"  nowrap="nowrap" class="formth"><b><label
										class="set" name="joining.date" id="Ljoin"
										ondblclick="callShowDiv(this);"> <%=label.get("joining.date")%></label></b>

									</td>
									<td width="20%" align="center" class="formth"><b><label
										class="set" name="prej.repto" id="Lrepto"
										ondblclick="callShowDiv(this);"> <%=label.get("prej.repto")%></label></b>

									</td>
									<td width="10%" align="center" class="formth"><b><label
										class="set" name="Astatus" id="Astatus"
										ondblclick="callShowDiv(this);"> <%=label.get("Astatus")%></label></b>
									</td>

								</tr>
								<s:if test="noData">
									<tr>
										<td width="100%" colspan="7" align="center"><font
											color="red">No Data To Display</font></td>
									</tr>
								</s:if>
								<%!int i = 0;%>
								<%
									int k = 1, count = 0;
									int cn = pageNo * 20 - 20;
								%>

								<s:iterator value="LChkList">
									<tr <%if(count%2==0){
									%> class="tableCell1"
										<%}else{%> class="tableCell2" <%	}count++; %>
										ondblclick="return callForEdit('<s:property value="Loffercode"/>');"
										onmouseover="javascript:newRowColor(this);"
										title="Double click for edit"
										onmouseout="javascript:oldRowColor(this,<%=count%2 %>);">


										<td class="sortabletd" width="5%" nowrap="nowrap"><%=++cn%>

										</td>
										<td class="sortabletd" width="20%"><s:hidden
											name="Lreqcode" id='<%="Lreqcode"+k%>' /> <s:hidden
											name="Loffercode" id='<%="Loffercode"+k%>' /> <s:hidden
											name="Lcancode" id='<%="Lcancode"+k%>' /> <s:property
											value="Lcandidate" /></td>



										<td class="sortabletd" align="left" width="15%"><s:property
											value="Lposition" />&nbsp;</td>
										<td class="sortabletd" align="center" width="15%"><s:property
											value="LofferDate" />&nbsp;</td>
										<td class="sortabletd" align="center" width="15%"><s:property
											value="LjoinDate" />&nbsp;</td>
										<s:hidden name="LreportTo" id='<%="LreportTo"+k%>' />
										<td class="sortabletd" width="30%"><s:property
											value="LreporterName" /> &nbsp;</td>
										<td class="sortabletd" align="center" width="10%"><s:property
											value="Lofferstatus" /> &nbsp;</td>
									</tr>
									<%
										k++;
										count++;
									%>
								</s:iterator>
								<%
									i = k;
									k = 1;
									count = 0;
								%>

							</table>
							</td>
						</tr>

					</table>
					</td>
				</tr>
				
				

				<tr>
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="75%"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						<td colspan="3" width="100%" align="right"><s:if
						test="modeLength">
						<b>Total No. of Records:</b>&nbsp;<s:property value="totalRecords" />
					</s:if></td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td></td>
					<td align="center"><input type="hidden" name="noofrecords"
						id="noofrecords" value="<%=i-1%>" /> <s:hidden name="Chkreqcode"></s:hidden>
					<s:hidden name="Chkcandidatecode"></s:hidden> <s:hidden
						name="Chkoffercode"></s:hidden> <s:hidden name="conduct"></s:hidden>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

</s:form>

<script>
try{
window.onload = document.getElementById('pageNoField').focus();
}catch(e)
{  
}

function callFun(status){

	
		document.getElementById("paraFrm_bgStatus").value=status;
   	  document.getElementById("paraFrm").action='PrejoiningActivities_callstatus.action?bgStatus='+status;
      document.getElementById('paraFrm').target="main";
	 document.getElementById("paraFrm").submit();
	
	}
	


function addnewFun()
{
	document.getElementById('paraFrm').action="PrejoiningActivities_addNew.action";
	document.getElementById('paraFrm').submit();
}


function searchFun()
{
	callsF9(500,300,"PrejoiningActivities_f9search.action");
	
}

 function callForEdit(code){

	document.getElementById("paraFrm_hiddencode").value=code;	
	document.getElementById("paraFrm").action="PrejoiningActivities_callforEdit.action";
	document.getElementById("paraFrm").submit();
	document.getElementById("paraFrm_hiddencode").value="";	
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
 

 function callPage(id,pageImg){  
 	 pageNo =document.getElementById('pageNoField').value;	
 	 totalPage =document.getElementById('totalPage').value;	 
 	 var actPage = document.getElementById('myPage').value; 
	 if(pageNo!="0"  & pageNo<totalPage){	
		 document.getElementById('myPage').value=pageNo;
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
		document.getElementById('myPage').value=id;
		document.getElementById('paraFrm_show').value=id;
	    document.getElementById('paraFrm').action="PrejoiningActivities_callPage.action";
		document.getElementById('paraFrm').submit(); 
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
		 	var actPage = document.getElementById('myPage').value;   
	     
		 	 
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
	        
	         document.getElementById('myPage').value=pageNo; 
		   	document.getElementById('paraFrm').action="PrejoiningActivities_callPage.action";
			document.getElementById('paraFrm').submit();
		}
		
	}	
	 
	 
	 

function next(){
   var pageno=	document.getElementById('myPage').value;
   	if(pageno=="1"){
   		document.getElementById('myPage').value=2;
	    document.getElementById('paraFrm_show').value=2;
    } else{
				 document.getElementById('myPage').value=eval(pageno)+1;
				 document.getElementById('paraFrm_show').value=eval(pageno)+1;
	    }
	   document.getElementById('paraFrm').action="PrejoiningActivities_callPage.action";
	   document.getElementById('paraFrm').submit();
}	
   
function on(){
		var val= document.getElementById('selectname').value;
		document.getElementById('paraFrm_show').value=val;
		document.getElementById('myPage').value=eval(val);
		document.getElementById('selectname').value=val;
		document.getElementById('paraFrm').action="PrejoiningActivities_callPage.action";
		document.getElementById('paraFrm').submit();
}
   
  //pgshow(); 
 
  	
  	function previous(){
   var pageno=	document.getElementById('myPage').value;
   	
	 document.getElementById('myPage').value=eval(pageno)-1;
	 document.getElementById('paraFrm_show').value=eval(pageno)-1;
	   document.getElementById('paraFrm').action="PrejoiningActivities_callPage.action";
	   document.getElementById('paraFrm').submit();
}  
	

function ApplyFilter()
{

      var candidateName1=document.getElementById('paraFrm_candidateName1').value;
      var positionName=document.getElementById('paraFrm_positionName').value;
      var offerFDate=document.getElementById('paraFrm_offerFDate').value;
      var offerTDate=document.getElementById('paraFrm_offerTDate').value;
      var joinFDate=document.getElementById('paraFrm_joinFDate').value;
      var joinTDate=document.getElementById('paraFrm_joinTDate').value;
     
  		   
			 
    if((candidateName1=="")&&(positionName=="")&&(offerFDate=="")&&(offerTDate=="")&&(joinFDate=="")&&(joinTDate==""))
  		
  		{
  		
  		alert('Please enter/select atleast one field to apply filter');
  		return false;
  		}  
  		if(offerFDate!="")
  		     {
  	    if(!validateDate('paraFrm_offerFDate','offfDate'))
			return false;
			}
		if(offerTDate!="")
  		     {
  	    if(!validateDate('paraFrm_offerTDate','offtDate'))
			return false;
			}
		
		if(offerFDate!=""&& offerTDate!="")
  		     {
  	    if(!dateDifferenceEqual(offerFDate,offerTDate,'paraFrm_offerTDate', 'offfDate','offtDate'))
				return false;
			 }
			if(joinFDate!="")
  		     {
  	    if(!validateDate('paraFrm_joinFDate','joiningFromDate'))
			return false;
			} 
	 if(joinTDate!="")
  		     {
  	    if(!validateDate('paraFrm_joinTDate','joiningToDate'))
			return false;
			}
		
		if(joinFDate!=""&& joinTDate!="")
  		     {
  	    if(!dateDifferenceEqual(joinFDate,joinTDate,'paraFrm_joinTDate', 'joiningFromDate','joiningToDate'))
				return false;
			 }
			
			document.getElementById("paraFrm_searchFlag").value="true";
			var status=document.getElementById("paraFrm_bgStatus").value;
			if(document.getElementById("paraFrm_bgStatus").value!="")
			 status=document.getElementById("paraFrm_bgStatus").value;
			 else
			 status="P";
			document.getElementById("paraFrm").action='PrejoiningActivities_prejoining.action?bgStatus='+status;
			
			  document.getElementById("paraFrm").submit();
  
  		
}

myonload();
function myonload(){
showEnable();
callFilter();
}

function showEnable(){

			
			if(document.getElementById("paraFrm_searchFlag").value=="true"){
			document.getElementById("showFilter").style.display='none';
			document.getElementById("hideFilter").style.display='none';
	}
}  


	function callFilter(){
		        
	         var chkSearch=document.getElementById('paraFrm_searchFlag').value;
	if(document.getElementById('paraFrm_searchFlag').value=="true")
	        {  
	            document.getElementById('hideFilter').style.display='none';
				document.getElementById('showFilter').style.display='none';
				document.getElementById('showFilterValue').style.display='none';
				document.getElementById('enableFilterValue').style.display='';
	      }
	else
	      {
	      		document.getElementById('showFilter').style.display='';
	      		  document.getElementById('hideFilter').style.display='none';
	            document.getElementById('showFilterValue').style.display='none';
				document.getElementById('enableFilterValue').style.display='none';
	        }
	                
	        
	    }

function callShowFilter(){//callShowFilter()
     document.getElementById('hideFilter').style.display='';
	document.getElementById('showFilter').style.display='none';
	document.getElementById('showFilterValue').style.display='inline';
	document.getElementById('enableFilterValue').style.display='none';
	

}

function callHideFilter(){
calReset();
	document.getElementById('showFilterValue').style.display='none';
	document.getElementById('hideFilter').style.display='none';
	document.getElementById('showFilter').style.display='';
	
}
function calReset(){
 			 document.getElementById('enableFilterValue').style.display='none';
			 document.getElementById('paraFrm_searchFlag').value="false";
			 document.getElementById('paraFrm_positionId').value="";
		 	 document.getElementById('paraFrm_positionName').value="";
		 	  document.getElementById('paraFrm_candCode1').value="";
		   	 document.getElementById('paraFrm_candidateName1').value="";
		    // document.getElementById('paraFrm_targetFDate').value="";
    		//document.getElementById('paraFrm_targetTDate').value="";
    		document.getElementById('paraFrm_offerFDate').value="";
    		document.getElementById('paraFrm_offerTDate').value="";
    		document.getElementById('paraFrm_joinFDate').value="";
    		document.getElementById('paraFrm_joinTDate').value="";    	    
   }
   
   
function callClear(){
 document.getElementById('showFilterValue').style.display='none';
 document.getElementById("paraFrm_searchFlag").value='false';
 document.getElementById("paraFrm").action='PrejoiningActivities_clearFilter.action';
 document.getElementById("paraFrm").submit();
  
 	
 }
	
    	
    	</script>