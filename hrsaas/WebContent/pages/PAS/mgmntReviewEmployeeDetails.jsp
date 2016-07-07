<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@ include file="/pages/charts/FusionCharts.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<s:form name="" action="" validate="" id="paraFrm" theme="simple">
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Competency Analysis</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
				<td colspan="4" width="100%" align="left"><input type="button" class="token" onclick="javascript:window.close();" value="  OK  "></td>
				</tr>
		<%String strXML = "";
		%>
		<tr>
			<td colspan="4">
			<table width="100%" border="0" class="formbg">
				<tr>
					<td width="20%"><label id="empId" name="empId1"
						onDblClick="callShowDiv(this);"><%=label.get("empId")%></label> :</td>
					<td colspan="3"><s:hidden name="employeeId" />
					<s:property value="empToken" /> - <s:property value="employeeName" />
					<!-- 
					<s:textfield name="empToken" size="14" readonly="true" cssStyle="background-color: #F2F2F2;" />
					<s:textfield size="81" name="employeeName" readonly="true"
						cssStyle="background-color: #F2F2F2;" />
						 -->
						</td>
				</tr>
				<tr>
					<td width="20%"><label class="set" id="designation" name="designation"
						onDblClick="callShowDiv(this);"><%=label.get("designation")%></label>
					:</td>
					<td width="25%"><s:hidden name="desigId" /><s:property value="desigName" />
					</td>
					<td width="20%"><label class="set" id="department"
						name="department" onDblClick="callShowDiv(this);"><%=label.get("department")%></label>
					:</td>
					<td width="35%"><s:hidden name="deptId" /> <s:property value="deptName" /></td>
				</tr>
				<tr>
					<td width="20%"><label class="set" id="branch" name="branch"
						onDblClick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
					<td width="25%"><s:hidden name="branchId" /><s:property value="branchName" />
					</td>
					<td width="20%"><label class="set" id="division1" name="division"
						onDblClick="callShowDiv(this);"><%=label.get("division")%></label>
					:</td>
					<td width="35%"><s:hidden name="divisionId" /> <s:property value="divisionName" /></td>
				</tr>
				<tr>
					<td width="20%"><label class="set" id="krascore" name="krascore"
						onDblClick="callShowDiv(this);"><%=label.get("krascore")%></label>
					:</td>
					<td width="25%"><s:property value="kraScore" />
					</td>
					<td width="20%"><label class="set" id="compscore" name="compscore"
						onDblClick="callShowDiv(this);"><%=label.get("compscore")%></label>
					:</td>
					<td width="35%"><s:property value="competencyScore" />
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%" class="txt"><strong class="text_head"><label id="comp.analysis" name="comp.analysis"
						onDblClick="callShowDiv(this);"><%=label.get("comp.analysis")%></label></strong></td>
		</tr>
		<tr>
			<td class="formtext">
			<table width="100%" border="0" class="formbg" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="20%" class="formth"  valign="top"><label class="set"
						id="competency" name="competency" ondblclick="callShowDiv(this);"><%=label.get("competency")%></label></td>
					<td width="15%" class="formth"  valign="top" colspan="1"><label class="set"
						id="compRating" name="compRating" ondblclick="callShowDiv(this);"><%=label.get("compRating")%></label></td>
					<td width="40%" class="formth"  valign="top"><label class="set"
						id="selfjust" name="selfjust" ondblclick="callShowDiv(this);"><%=label.get("selfjust")%></label></td>
					<td width="25%" class="formth"  valign="top"><label class="set"
						id="managerjust" name="managerjust" ondblclick="callShowDiv(this);"><%=label.get("managerjust")%></label></td>
				</tr>
				<%int count=0; 
				Object [][]ratingDataForGraph=(Object[][])request.getAttribute("ratingDataForGraph");
				%>
				
				<s:iterator value="analysisList">
					<tr>
						<td width="20%" align="left" class="sortableTD"><s:hidden
							name="questionCodeItt" />&nbsp;<s:property
							value="competencyItt" />
							</td>
						
				<td width="15%" valign="top" class="sortableTD">
				<table>
				<tr><td>
				<%
							
				%> <%
 			//Now, we need to convert this data into XML. We convert using string concatenation.

 			//Initialize <chart> element
 			
 			try {
 		strXML = "<chart bgColor='FFFFFF' bgAlpha='0' showBorder='0' upperLimit='4' lowerLimit='0' gaugeRoundRadius='5' chartBottomMargin='10' ticksBelowGauge='0'" 
 			+" showGaugeLabels='0'  showTickMarks='0' valueAbovePointer='0' pointerOnTop='1' pointerRadius='9'>"
 			+" <colorRange> " 
 			+" <color minValue='0' maxValue='4' code='#FFFFFF' /> </colorRange>" 
 			+" <pointers>";
 			System.out.println("String.valueOf(ratingDataForGraph[count][0])="+String.valueOf(ratingDataForGraph[count][0]));
 			System.out.println("String.valueOf(ratingDataForGraph[count][1])="+String.valueOf(ratingDataForGraph[count][1]));
 			if(!(String.valueOf(ratingDataForGraph[count][1]).equals("")||String.valueOf(ratingDataForGraph[count][1]).equals(null)||String.valueOf(ratingDataForGraph[count][1]).equals("null"))){
 					strXML+=" <pointer value='"+String.valueOf(ratingDataForGraph[count][1])+"'  bgColor='FF2020' pointerOnTop='0' radius='9' sides='4' bgalpha='100'  />";
 			}
 			if(!(String.valueOf(ratingDataForGraph[count][0]).equals("")||String.valueOf(ratingDataForGraph[count][0]).equals(null)||String.valueOf(ratingDataForGraph[count][0]).equals("null"))){
 				strXML+=" <pointer value='"+String.valueOf(ratingDataForGraph[count][0])+"' bgColor='FFFF20'  pointerOnTop='1' radius='7'  bgalpha='100'  /> ";
 			}
 			strXML+=" </pointers> ";
 		//Close <chart> element
			count++;
 			} catch (Exception e) {

 			}
 			strXML = strXML + "</chart>";
 			//strXML = strXML.replace("&","%26");
 			System.out.println("strXML=poll==>" + strXML);
 			String chartCode = "";
 			System.out.println("in createChart strXML=======" + strXML);
 			chartCode = createChartHTML(
 			"../pages/charts/fusionCharts/HLinearGauge.swf", "",
 			strXML, "productSales", "150", 35, false);
 			System.out.println("in createChart result" + chartCode);
 %> <%=chartCode%>
 </td></tr>
 </table></td>
						
						
						<td width="40%" align="left" class="sortableTD">&nbsp;<s:property
							value="selfJustificationItt" /></td>
						<td width="25%" align="left" class="sortableTD">&nbsp;<s:property
							value="managerJustificationItt" /></td>
					</tr>
				</s:iterator>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="4">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="2" >
				<tr>
					<td colspan="1" width="25%"><input type="text" disabled="disabled" style="background-color: #FF2020; height: 15px; width: 15px">&nbsp Indicates Manager Rating</td>
					<td colspan="3" width="75%"><input type="text" disabled="disabled" style="background-color: #FFFF20; height: 15px; width: 15px">&nbsp Indicates Self Rating</td>
					
				</tr>
				<tr>
				<td colspan="4" width="100%" align="left"><input type="button" class="token" onclick="javascript:window.close();" value="  OK  "></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>