<!-- Added by manish sakpal on 11th Feb 2011-->

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"> var records = new Array(); </script>
<s:form action="AccidentDetails" validate="true" id="paraFrm" validate="true" theme="simple">	
	
	<table width="100%" border="0" align="right" cellpadding="2" cellspacing="1" class="formbg">
		<tr>
			<s:hidden name="accidentID" />
			<s:hidden name="casualityType" />
			<s:hidden name="typeOfAccident" />
			<s:hidden name="accidentPlace" />
			<td valign="bottom" class="txt">						
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td>
						<strong class="text_head"> 
							<img src="../pages/images/recruitment/review_shared.gif" width="25"	height="25" /> 
						</strong>
					</td>
					<td width="93%" class="txt">
						<strong class="text_head">Accident Details </strong>
					</td>
					<td width="3%" valign="top" class="txt">
						<div align="right">
							<img src="../pages/images/recruitment/help.gif" width="16" height="16" />
						</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>


		<tr>
			<td>
			<table width="100%">
				<td>
					<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
				</td>				
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" width="100%">
				<table class="formbg" width="100%" cellspacing="1" cellspadding="0"	border="0">
				 <tr>
					<td colspan="2" class="txt" width="85%">
						<strong class="text_head"> Accident Details List </strong>
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				
				<tr>
					<td colspan="8">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
						<td class="formtext">
							<table width="100%" border="0">
								<tr>								
									<td width="5%" class="formth" align="center">
										<label class="set" name="serial.no" id="serial.no" ondblclick="callShowDiv(this);"> <%=label.get("serial.no")%></label>
									</td>

									<td class="formth" align="center" width="20%">
										<label class="set" name="casualityType" id="casualityType" ondblclick="callShowDiv(this);"> <%=label.get("casualityType")%></label>
									</td>

									<td class="formth" align="center" width="20%">
										<label	class="set" name="typeOfAccident" id="typeOfAccident"	ondblclick="callShowDiv(this);"> <%=label.get("typeOfAccident")%></label>
									</td>						

									<td class="formth" align="center" width="20%">
										<label class="set" name="placeOfAccident" id="placeOfAccident" ondblclick="callShowDiv(this);"> <%=label.get("placeOfAccident")%></label>
									</td>	
									
									<td class="formth" align="center" width="15%">
										<label class="set" name="dateOfAccident" id="dateOfAccident" ondblclick="callShowDiv(this);"> <%=label.get("dateOfAccident")%></label>
									</td>
									
									<td class="formth" align="center" width="15%">
										<label class="set" name="timeOfAccident" id="timeOfAccident" ondblclick="callShowDiv(this);"> <%=label.get("timeOfAccident")%></label>
									</td>
									
									<td class="formth" align="center" width="10%">
										<label class="set" name="deleteRecord" id="deleteRecord" ondblclick="callShowDiv(this);"> <%=label.get("deleteRecord")%></label>
									</td>
								</tr>

								<s:if test="accidentListLength">
								<%
								int count = 0;
								%>
								<%!int d = 0;%>
								<%
									int i = 0;									
								%>

								<s:iterator value="accidentIteratorList">
									<tr <%if(count%2==0){
									%> class="tableCell1"
										<%}else{%> class="tableCell2" <%	}count++; %>
										onmouseover="javascript:newRowColor(this);"
										title="Double click for edit"
										onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
										ondblclick="javascript:callForEdit('<s:property value="hiddenAccidentListID"/>');">
										<s:hidden name="hiddenAccidentListID" />	
										<td align="center" class="sortableTD"><%=++i%></td>
										
										<td class="sortableTD">&nbsp;
											<s:hidden name="casualTypeRadio" />											
											<s:property	value="casualType" />																					
										</td>

										<td class="sortableTD">&nbsp;
											<s:property value="accidentType" />
										</td>										

										<td class="sortableTD">&nbsp;
											<s:property	value="accidentPlace" />
										</td>
										
										<td class="sortableTD" align="center">&nbsp;
											<s:property	value="accidentDate" />
										</td>
										
										<td align="center" class="sortableTD">&nbsp;
											<s:property	value="accidentTime" />
										</td>
										
										<td class="sortableTD" align="center">&nbsp;
										<img src="../pages/common/css/icons/delete.gif"
										onclick="callDeleteAccident('<s:property value="hiddenAccidentListID" />');" />
										</td>										
									</tr>
								</s:iterator>

								<%
								d = i;
								%>
							</s:if>
							
							<s:else>
								<td align="center" colspan="6" nowrap="nowrap">
									<font color="red" >There is no data to display</font>
								</td>
							</s:else>

						</table>
					 </td>
				  </table>
				</td>
			   </tr>
			  </table>
	        </td>
		 </tr>
		
		<tr>
			<td width="100%">
			<table border="0" width="100%">
				<td width="75%">
					<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
				</td>

				<td width="25%" align="Right">
					&nbsp;
				</td>

			</table>
			</td>
		</tr>
	</table>
</s:form>


<script>

function addnewFun()
{
  	    document.getElementById('paraFrm').target = "_self";
   	    document.getElementById('paraFrm').action="AccidentDetails_addNew.action";
	    document.getElementById('paraFrm').submit();	   
}
  	
function searchFun()
{
	if(navigator.appName == 'Netscape') 
	{
	  var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
	} 
	else 
	{
	  var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
	}
			
	document.getElementById("paraFrm").target = 'myWin';
	document.getElementById("paraFrm").action ='AccidentDetails_f9searchRecords.action';
	document.getElementById("paraFrm").submit();
}

function callForEdit(id)
{	
	callButton('NA', 'Y', 2);
  	
	document.getElementById("paraFrm").action="AccidentDetails_calforedit.action?id="+id;
	document.getElementById('paraFrm').target = "_self";
	document.getElementById("paraFrm").submit();
}

function callDeleteAccident(deleteAccidentID)
{
	 alert("deleteAccidentID : "+deleteAccidentID);
	var conf=confirm("Are you sure !\n You want to Remove this record ?");
  if(conf){
	  document.getElementById('paraFrm').target = "_self";
   	  document.getElementById('paraFrm').action="AccidentDetails_deleteAccidentRecordFromListPage.action?deleteAccidentID="+deleteAccidentID;
	  document.getElementById('paraFrm').submit();
	  }
}
</script>



