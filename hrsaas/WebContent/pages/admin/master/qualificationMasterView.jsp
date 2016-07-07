<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript">
var specArray = new Array();
</script>
<s:form action="QualificationMaster" id="paraFrm" validate="true"
	theme="simple">
	<s:hidden name="show" value="%{show}" />
	<s:hidden name="hiddencode" />
	<!-- Flagas used For Cancel Button -->
	<s:hidden name="editFlag" />
	<s:hidden name="quaID" /> 
<s:hidden name="quaName"/>
<s:hidden name="quaAbbr"/>
<s:hidden name="qualevel"/>
<s:hidden name="desciption"/>
<s:hidden name="status"/>
<s:hidden name="qualificationID"/>
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
					<td width="93%" class="txt"><strong class="text_head">Qualification</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" width="100%">

			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<!-- The Following code Denotes  Include JSP Page For Button Panel -->

						<tr>
							<td colspan="3">
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td width="75%"><jsp:include
										page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
							<td align="right"><b>Page:</b> <%
					int totalPage = (Integer) request.getAttribute("totalPage");
					int pageNo = (Integer) request.getAttribute("PageNo");
					%>
						<%	if(pageNo != 1)
								{
									if(pageNo > totalPage){}
									else{
							%>		<a href="#" onclick="callPage('1');" >
										<img src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
									</a>&nbsp;
									<a href="#" onclick="callPage('P');" >
										<img src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
									</a>
							<%	}
								}
								if(totalPage <= 5)
								{
									if(totalPage == 1)
									{
							%>			<b><u><%=totalPage%></u></b>
							<%		}
									else
									{
										for(int z = 1; z <= totalPage; z++)
										{
											if(pageNo == z)
											{
							%>					<b><u><%=z%></u></b>
							<%				}
											else
											{
							%>					<a href="#" onclick="callPage('<%=z%>');"><%=z%></a>
							<%				}
										}
									}
								}
								else
								{
									if(pageNo == totalPage - 1 ||pageNo == totalPage)
									{									
										for(int z = pageNo - 2; z <= totalPage; z++)
										{
											if(pageNo == z)
											{
							%>					<b><u><%=z%></u></b>
							<%				}
											else
											{
							%>					&nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a>
							<%				}
										}
									}
									else if(pageNo <= 3)
									{
										for(int z = 1; z <= 5; z++)
										{
											if(pageNo == z)
											{
							%>					<b><u><%=z%></u></b>
							<%				}
											else
											{
							%>					&nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a>
							<%				}
										}						
									}
									else if(pageNo > 3)
									{
										for(int z = pageNo - 2; z <= pageNo + 2; z++)
										{
											if(pageNo == z)
											{
							%>					<b><u><%=z%></u></b>
							<%				}
											else
											{
							%>					&nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a>
							<%				}
										}					
									}
								}
								if(!(pageNo == totalPage))
								{
									if(totalPage == 0 || pageNo > totalPage){}
									else
									{
							%>			....<%=totalPage%>
										<a href="#" onclick="callPage('N')" >
											<img src="../pages/common/img/next.gif" class="iconImage" />
										</a>&nbsp;
										<a href="#" onclick="callPage('<%=totalPage%>');" >
											<img src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
										</a>
							<%		}
								}
							%><select name="selectname" onchange="on()" id="selectname">
								<%
						for (int i = 1; i <= totalPage; i++) {
						%>

								<option value="<%=i%>"><%=i%></option>
								<%
						}
						%>
							</select></td>
						
						</tr>
					</table>
					</td>
						</tr>
							</table>
							</td>
						</tr>
					</table>
					<label></label></td>
				</tr>
				
				<s:if test="qualiView">
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>

							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="20%" height="22" class="formtext"><label name="qual.name"
										class="set" id="qual.name" ondblclick="callShowDiv(this);"><%=label.get("qual.name")%></label>
								 :</td>
									<td width="34%" height="22">
										
										<s:property value="quaName" />
									    </td>
									<td width="27%" height="22" class="formtext">&nbsp;</td>
									<td width="18%" height="22">&nbsp;</td>
								</tr>
								<tr>
									<td width="20%" height="22" class="formtext" nowrap="nowrap"><label name="qual.abbr"
										class="set" id="qual.abbr" ondblclick="callShowDiv(this);"><%=label.get("qual.abbr")%></label>
								:</td>
									<td height="22">
										
										<s:property value="quaAbbr" />
									</td>
									<td height="22" class="formtext">&nbsp;</td>
									<td height="22">&nbsp;</td>
								</tr>
								<tr>
									<td height="22" class="formtext" width="20%"><label name="qual.levl"
										class="set" id="qual.levl" ondblclick="callShowDiv(this);"><%=label.get("qual.levl")%></label>:
									</td>
									<td height="22">
									
									<s:property value="qualevel"/>
										
									</td>
									<td height="22" class="formtext">&nbsp;</td>
									<td height="22">&nbsp;</td>
								</tr>

								<tr>
									<td valign="top" height="22" class="formtext" width="20%" ><label name="qual.desc"
										class="set" id="qual.desc" ondblclick="callShowDiv(this);"><%=label.get("qual.desc")%></label>:
									</td>
									<td height="22" colspan="3" valign="top"> 
										
										<s:property value="desciption" />
									</td>
								</tr>

								<tr>
									<td height="22" class="formtext" width="20%"><label name="qual.stat"
										class="set" id="qual.stat" ondblclick="callShowDiv(this);"><%=label.get("qual.stat")%></label>:
									</td>
									<td height="22">
										
										<s:property value="status" />
									</td>
									<td height="22" class="formtext">&nbsp;</td>
									<td height="22">&nbsp;</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				</s:if>
				<tr>
					<td colspan="3">

					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<!-- The Following code Denotes  Include JSP Page For Button Panel -->

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
									<td class="formth" align="center"><b><label class="set" name="qual.srno"
										id="qual.srno" ondblclick="callShowDiv(this);"><%=label.get("qual.srno")%></label></b>
									</td>
									<td class="formth" align="center"><b><label class="set" name="qual.name"
										id="qual.name1" ondblclick="callShowDiv(this);"><%=label.get("qual.name")%></label></b>
									</td>
									<td class="formth" align="center"><b><label class="set" name="qual.abbr"
										id="qual.abbr1" ondblclick="callShowDiv(this);"><%=label.get("qual.abbr")%></label></b>
									</td>
									<td class="formth" align="center"><b><label class="set" name="qual.stat"
										id="qual.stat1" ondblclick="callShowDiv(this);"><%=label.get("qual.stat")%></label></b>
									</td>

									<td align="left" class="formth" nowrap="nowrap">
									<input type="checkbox" name="checkAll" id="checkAll"
										       onclick=" checkAllBox()"/>
									<input type="button"
										class="delete" theme="simple" value="     Delete  "
										onclick=" return chkDelete();" /></td>
									<%
									int count = 0;
									%>
									<%!int d = 0;%>
									<%
											int i = 0;
											int cn = pageNo * 20 - 20;
									%>
									<%int counter = 0;%>
									<s:iterator value="qualificationList">
										<tr <%if(count%2==0){
									%> class="tableCell1"
											<%}else{%> class="tableCell2" <%	}count++; %>
											onmouseover="javascript:newRowColor(this);"
										
											onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
											>

										<td width="10%" align="center" class="sortableTD"
											title="Double click for edit"
											ondblclick="javascript:callForEdit('<s:property value="quaID"/>');"
											><%=++cn%> <%
												 ++i;
												 %><s:hidden name="srNo" /></td>
											<s:hidden value="%{quaID}"></s:hidden>

											<td width="35%" align="left" class="sortableTD"
												title="Double click for edit"
												ondblclick="javascript:callForEdit('<s:property value="quaID"/>');"
											><s:property value="quaName" />
											<input type="hidden" name="hdeleteCode"
												id="hdeleteCode<%=i%>" /></td>
											<td width="25%" align="left" class="sortableTD"
												title="Double click for edit"
												ondblclick="javascript:callForEdit('<s:property value="quaID"/>');"
											
											><s:property value="quaAbbr" /></td>
											<script>
													specArray[<%=counter%>] = '<s:property  value="quaID"/>';
										</script>
											<td width="20%" align="center" class="sortableTD"
											title="Double click for edit"
											ondblclick="javascript:callForEdit('<s:property value="quaID"/>');"
											
											
											><s:property value="status" /></td>

											<td align="left" nowrap="nowrap" class="sortableTD"><input
												type="checkbox" class="checkbox" id="confChk<%=i%>"
												name="confChk"
												onclick="callForDelete1('<s:property value="quaID"/>','<%=i%>')" /></td>
										</tr>
									<%counter++; %>
									</s:iterator>
									<%
									d = i;
									%>
								</tr>
							</table>
							</td>
						</tr>
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
		<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="75%"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						<td colspan="3" width="100%" align="Right">
					<b>Total Records :&nbsp;<s:property value="totalRecords" /></b>
					</td>		
					</tr>
				</table>
				</td>
			</tr>
	</table>
</s:form>
<script>
var f9Fields= ["paraFrm_quaName","paraFrm_quaAbbr"];
var fieldName = ["paraFrm_quaName","paraFrm_quaAbbr"];
var lableName = ["<%=label.get("qual.name")%>","<%=label.get("qual.abbr")%>"];
var type = ['enter','enter'];



//For Edit Button

function editFun()
{
	document.getElementById('paraFrm').action="QualificationMaster_edit.action";
	document.getElementById('paraFrm').submit();
}

//For Delete Button

function deleteFun()
{
	var con=confirm('Do you really want to delete this record ?')
	if(con){
			var del="QualificationMaster_delete.action";
		   document.getElementById('paraFrm').action=del;
		   document.getElementById('paraFrm').submit();
	} else{
	     return false;
	}
}

//For F9Window

function searchFun()
{
	callsF9(500,300,"QualificationMaster_f9Action.action");
}

//For Cancel Button

function cancelFun()
{
		document.getElementById('paraFrm').action="QualificationMaster_cancelSec.action";	
		document.getElementById('paraFrm').submit();
	
}
	
	
//For Addnew Button 

function addnewFun()
{
	
	document.getElementById('paraFrm').action="QualificationMaster_addNew.action";
	document.getElementById('paraFrm').submit();
}	
	
//For Report Button

function reportFun()
{
	document.getElementById('paraFrm').action="QualificationMaster_report.action";
    document.getElementById("paraFrm").submit();
}	
	
	function resetData()
  	{
  	
  	 document.getElementById('paraFrm_show').value='1';
  	}
  	
  	
  	
function callsave(type)
 {

var fieldName = ["paraFrm_quaMaster_quaName","paraFrm_quaMaster_quaAbbr"];
var lableName = ["qual.name","qual.abbr"];
var flag = ["enter","enter"];
var desc=	document.getElementById('paraFrm_desciption').value;

	
     if(!validateBlank(fieldName,lableName,type))
          return false;
        
        
  if(desc.length>300){
  
  alert("Maximum length of 'Qualification Description' is 300 characters.");
		return false;
  
  }      
        
     
     }
   	    
     	    	
    function callPage(id){
	
		
		if(id=='P'){
		var p=document.getElementById('myPage').value;
		id=eval(p)-1;
		}
		if(id=='N'){
		var p=document.getElementById('myPage').value;
		id=eval(p)+1;
		}
		document.getElementById('myPage').value=id;
		document.getElementById('paraFrm_show').value=id;
		document.getElementById('paraFrm').action='QualificationMaster_callPage.action';
		document.getElementById('paraFrm').submit();
		
	}	
      
 	function on()
   {
  	var val= document.getElementById('selectname').value;
	document.getElementById('paraFrm_show').value=val;
	 document.getElementById('myPage').value=eval(val);
	 document.getElementById('selectname').value=val;
	   document.getElementById('paraFrm').action="QualificationMaster_callPage.action";
	   
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
	
	  	document.getElementById('paraFrm_hiddencode').value = id;
	   	document.getElementById("paraFrm").action="QualificationMaster_calforedit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
    }

   
    function deleteRecord(){
		if(!callDelete('paraFrm_quaMaster_quaID'))return false;
		
		else{
			document.getElementById('paraFrm').action="QualificationMaster_calfordelete.action";
		   document.getElementById('paraFrm').submit();
		}
	}
   
   
   function callForDelete(id){
  
    var conf=confirm("Do you really want to delete the records ?");
  			if(conf) 
  			{
  				
  				document.getElementById('paraFrm_hiddencode').value=id;
  		
  				document.getElementById("paraFrm").action="QualificationMaster_calfordelete.action";
  			
			   	document.getElementById("paraFrm").target="main";
			    document.getElementById("paraFrm").submit();
			    return true;
  			}
	  		else
	  		{
	  			
	  			 return false;
	  			 
	  		}
	  		
	   
   }
   
   function callForDelete1(id,i)
	   {
	   
	   var flag='<%=d %>';
	
	
	   if(document.getElementById('confChk'+i).checked == true)
	   {	  
	    document.getElementById('hdeleteCode'+i).value=id;
	   }
	   else
	   document.getElementById('hdeleteCode'+i).value="";
   }
   
   function chkDelete(){
	 var flag='<%=d %>';
	
	 if(chk()){
	 var con=confirm('Do you really want to delete the records?');
	 if(con){
	   document.getElementById('paraFrm').action="QualificationMaster_delete1.action";
	    document.getElementById('paraFrm').submit();
	    }
	    else{
	     var flag='<%=d %>';
	  for(var a=1;a<=flag;a++){	
	document.getElementById('confChk'+a).checked = false;
	document.getElementById('hdeleteCode'+a).value="";
	     }
	     return false;
	     }
	 } else {
	 	alert('Please select atleast one record to delete');
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
   
   	function newRowColor(cell) {
	//	cell.style.backgroundColor="#E2F2FE";
		//cell.style.cursor='hand';
		cell.className='Cell_bg_first';
	//	document.getElementById(cell).backgroundColor="#FFA31D";
	}
	
	function oldRowColor(cell,val) {
	
	//if(val=='1'){
	 cell.className='Cell_bg_second';
	//}
	//else cell.className='tableCell1';
		//cell.style.backgroundColor="#ffffff";
	}
	
	function maxlengthDesc()
	{
		var lengthDesc=document.getElementById('paraFrm_desciption').value;
		var len=lengthDesc.length;
		if(len=='300')
		{
			return false;
		}	
		else
		{
			return true;	
		}
	}
	
	function checkAllBox(){
	 var rowLen ='<%=d %>';
	
	 if (document.getElementById("checkAll").checked){
	   for(var idx=1;idx<=rowLen;idx++){
	    document.getElementById("confChk"+idx).checked ="true";
	    document.getElementById('hdeleteCode'+idx).value=specArray[idx-1];
	   
      }
	}else{
	  for (var idx = 1; idx <= rowLen; idx++) {
	   document.getElementById("confChk"+idx).checked = false;
	   document.getElementById('hdeleteCode'+idx).value="";
      }
    }	 
}
</script>

