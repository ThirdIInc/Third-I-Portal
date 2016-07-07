
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>
<script type="text/javascript"
	src="../pages/TravelManagement/TravelProcess/HotelTree.js"></script>
<script type="text/javascript"
	src="../pages/TravelManagement/TravelProcess/lodgeTree.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript" src="../tmsAjax.js"></script>

<script type="text/javascript">
	var Tree = new Array;
	var Tree1 = new Array;
	var mainMenu=0;
	</script>


<%
	String[][] temp = null;
	Object[][] items = null;

	try {
		items = (Object[][]) request.getAttribute("menuItems");

	} catch (Exception e) {
		System.out.println("Exception ---0");
	}

	try {
		int i;
		if (items != null && items.length > 0) {
			for (i = 0; i < items.length; i++) {
%>
<script type="text/javascript">
					// nodeId | parentNodeId | nodeName | nodeUrl
		var value= '<%=items[i][0] %>'+'|'+'<%=items[i][1] %>'+'|'+'<%=items[i][2] %>'+'|#';
		Tree[<%=i%>]  = value;
	</script>
<%
		}
		}
	} catch (Exception e) {
		System.out.println("Exception ---1");
	}
%>

<!-- for lodge entitle -->

<%
	String[][] temp1 = null;
	Object[][] lodgeitems = null;
	try {
		lodgeitems = (Object[][]) request
		.getAttribute("menuLodgeItems");
		//System.out.println("lodgeitems ---0"+lodgeitems.length);
	} catch (Exception e) {
		System.out.println("Exception ---0");
	}

	try {
		int i;
		if (lodgeitems != null && lodgeitems.length > 0) {
			for (i = 0; i < lodgeitems.length; i++) {
%>
<script type="text/javascript">
					// nodeId | parentNodeId | nodeName | nodeUrl
		var value1= '<%=lodgeitems[i][0] %>'+'|'+'<%=lodgeitems[i][1] %>'+'|'+'<%=lodgeitems[i][2] %>'+'|#';
		Tree1[<%=i%>]  = value1;
	</script>
<%
		}
		}
	} catch (Exception e) {
		System.out.println("Exception ---1");
	}
%>


<!--  -->


<script>
 var travelArr = new Array();
 var hotelArr = new Array();
 var expArr = new Array();
 var roomArr = new Array();
 
 </script>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="TravelPolicy" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="tp.policyId" />
	<s:hidden name="cancelFlag" />
	<s:hidden name="activeflag" />
	<s:hidden name="nextflag" />
	<s:hidden name="childflag" id="childflag" value="N" />
	<s:hidden name="lodgechildflag" id="lodgechildflag" value="N" />
	<table width="100%" border="0" align="right" cellpadding="0"
		class="formbg" cellspacing="0">
		<tr>
			<td colspan="3" valign="bottom" class="txt">&nbsp;</td>
		</tr>


		<!--<tr>
			<td colspan="3" valign="bottom"
				background="../pages/images/recruitment/lines.gif" class="txt"><img
				src="../pages/images/recruitment/lines.gif" width="16" height="1" /></td>
		</tr>

		--><tr>
			<td colspan="3">
			<table width="100%" border="0" align="center" class="formbg">
				<tr>
					<td valign="bottom" class="txt" width="3%" colspan="1"><strong
						class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="97%" class="txt" colspan="2"><strong
						class="text_head">Travel Policy details </strong></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="60%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="20%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
				<tr>
					<td width="60%"></td>
					<td width="20%">
					<div align="right"><font color="blue"> Travel Currency :<s:property
						value="travelCurrency" /></font></div>
					</td>

				</tr>
			</table>
			<label></label></td>
		</tr>

		<tr>

			<td colspan="6" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="100%" colspan="6">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">

						<tr>
							<td width="25%" colspan="6"><strong><label
								class="set" id="exp.entitle" ondblclick="callShowDiv(this);"><%=label.get("exp.entitle")%></label></strong>
							</td>

						</tr>
						<tr>
							<td width="100%" colspan="6">
							<div style="height: 300; overflow: scroll;">
							<table width="100%">
								<tr>
									<td width="5%" colspan="1" class="formth"><strong><label
										class="set" id="srNo" name="srNo"
										ondblclick="callShowDiv(this);"><%=label.get("srNo")%></label></strong>
									</td>
									<td width="35%" colspan="1" class="formth"><strong><label
										class="set" id="exp.cate" name="exp.cate"
										ondblclick="callShowDiv(this);"><%=label.get("exp.cate")%></label></strong>
									</td>
									<td width="10%" colspan="1" class="formth"><strong><label
										class="set" id="unit" name="unit"
										ondblclick="callShowDiv(this);"><%=label.get("unit")%></label></strong>
									</td>


									<!-- (start)Added by manish sakpal -->
									<td width="10%" colspan="1" class="formth"><strong><label
										class="set" id="amtwithbill" name="amtwithbill"
										ondblclick="callShowDiv(this);"><%=label.get("amtwithbill")%></label></strong>
									</td>

									<td width="10%" colspan="1" class="formth"><strong><label
										class="set" id="amtwithoutbill" name="amtwithoutbill"
										ondblclick="callShowDiv(this);"><%=label.get("amtwithoutbill")%></label></strong>
									</td>
									<!-- (End)Added by manish sakpal -->


									<td width="8%" colspan="1" class="formth"><strong><label
										class="set" id="atActual" name="atActual"
										ondblclick="callShowDiv(this);"><%=label.get("atActual")%></label></strong>
									</td>
									<td width="8%" colspan="1" class="formth"><strong><label
										class="set" id="proof" name="proof"
										ondblclick="callShowDiv(this);"><%=label.get("proof")%></label></strong>
									</td>

									<!-- (start) Added by manish sakpal -->
									<td width="8%" colspan="1" class="formth"><strong><label
										class="set" id="citygrade" name="citygrade"
										ondblclick="callShowDiv(this);"><%=label.get("citygrade")%></label></strong>
									</td>
									<!-- (End) Added by manish sakpal -->

								</tr>
								<!-- End of 1st row -->

								<!-- ################## ITERATOR BEGINS #################  -->

								<%
								int p = 1, p1 = 0;
								%>

								<s:iterator value="expList">
									<tr>
										<!-- 2nd row -->
										<s:hidden name="exCategory" />
										<s:hidden name="exCategoryId" />
										<s:hidden name="expCatUnit" />
										<s:hidden name="expCatUnitCode" />
										<s:hidden name="readOnlyFlag" />
										<td width="5%" class="sortableTD"><%=p%></td>
										<td width="35%" class="sortableTD"><s:property
											value="exCategory" /></td>
										<td width="10%" class="sortableTD"><s:property
											value="expCatUnit" /></td>

										<s:if test="tp.nextflag">
											<!-- (start) Added by manish sakpal -->
											<td width="10%" class="sortableTD"><input type="text"
												style="text-align: right" name="expValuewithBill"
												id="expValuewithBill<%=p%>"
												value="<s:property value="expValuewithBill" />" size="6"												
												 onkeypress="return numbersWithDot();" maxlength="8"></td>

											<td width="10%" class="sortableTD"><input type="text"
												style="text-align: right" name="expValuewithoutBill"
												id="expValuewithoutBill<%=p%>"
												value="<s:property value="expValuewithoutBill" />" size="6"												
												 onkeypress="return numbersWithDot();" maxlength="8"></td>
											<!-- (End) Added by manish sakpal -->

											<td width="8%" class="sortableTD" align="center"><s:hidden
												name="hidActualAtt" id='<%="hidActualAtt"+p %>' /> <input
												type="checkbox" name="actualAtt" id="actualAtt<%=p%>"
												onclick="callExpChk('<%=p%>')"> <script>
														if(document.getElementById("hidActualAtt<%=p%>").value=='Y')
														document.getElementById("actualAtt<%=p%>").checked = true;
												</script></td>
											<td width="8%" class="sortableTD"><s:select
												name="proofId" list="#{'N':'No','Y':'YES'}" /></td>

											<!-- (Start) Added by manish sakpal -->
											<td width="8%" class="sortableTD"><s:select
												headerKey="-1" headerValue="-- Select --" name="cityId"
												cssStyle="width:100" list="tmap" /></td>
											<!-- (End) Added by manish sakpal -->
										</s:if>



										<s:else>

											<!-- (start) Added by manish sakpal -->
											<td width="8%" class="sortableTD"><input type="text"
												style="text-align: right" name="expValuewithBill"
												disabled="disabled" id="expValuewithBill<%=p%>"
												value="<s:property value="expValuewithBill" />" size="6"
												 onkeypress="return numbersOnly();"
												maxlength="8"></td>

											<td width="8%" class="sortableTD"><input type="text"
												style="text-align: right" name="expValuewithoutBill"
												disabled="disabled" id="expValuewithoutBill<%=p%>"
												value="<s:property value="expValuewithoutBill" />" size="6"
												 onkeypress="return numbersOnly();"
												maxlength="8"></td>
											<!-- (End) Added by manish sakpal -->

											<td width="8%" class="sortableTD" align="center"><s:hidden
												name="hidActualAtt" id='<%="hidActualAtt"+p %>' /> <input
												type="checkbox" disabled="disabled" name="actualAtt"
												id="actualAtt<%=p%>" onclick="callExpChk('<%=p%>')" /> <script>
												 	if(document.getElementById("hidActualAtt<%=p%>").value=='Y')
													document.getElementById("actualAtt<%=p%>").checked = true;
												</script></td>
											<td width="8%" class="sortableTD"><s:select
												disabled="disabled" name="proofId"
												list="#{'N':'No','Y':'Yes'}" /></td>

											<!-- (start) Added by manish sakpal -->
											<td width="8%" class="sortableTD"><s:select
												headerKey="-1" headerValue="-- Select --" name="cityId"
												cssStyle="width:100" list="tmap" /></td>
											<!-- (End) Added by manish sakpal -->

										</s:else>

									</tr>
									<!-- End of 2nd row -->
									<script>	
		                                  expArr[<%=p1%>] = document.getElementById('hidActualAtt'+<%=p%>).value;   
		                            </script>
									<%
									p++;
									%>
									<%
									p1++;
									%>

								</s:iterator>
								<!-- ################## ITERATOR ENDS #################  -->
								<!--  					
						<tr>									
							<td colspan="2" class="sortableTD">&nbsp;</td>
							<td width="8%" colspan="1" class="sortableTD">
								<strong><label class="set" id="totAmt" name="totAmt" ondblclick="callShowDiv(this);"><%=label.get("totAmt")%></label></strong>
							</td>

							<s:if test="tp.nextflag">
								<td width="10%" colspan="1" class="sortableTD">
									<s:textfield name="totExpAmtWithBill" readonly="true" cssStyle="text-align:right" maxlength="8" size="6" />
								</td>
								<td width="10%" colspan="1" class="sortableTD">
									<s:textfield	name="totExpAmtWithoutBill" readonly="true" cssStyle="text-align:right" maxlength="8" size="6" />
								</td>
							</s:if>

							<s:else>
								<td width="10%" colspan="1" class="sortableTD">
									<s:textfield name="totExpAmtWithBill" readonly="true" disabled="true"	cssStyle="text-align:right" maxlength="8" size="7" />
								</td>
								<td width="10%" colspan="1" class="sortableTD">
									<s:textfield name="totExpAmtWithoutBill" readonly="true" disabled="true" cssStyle="text-align:right" maxlength="8" size="6" />
								</td>
							</s:else>

								<td width="8%" colspan="3" class="sortableTD">&nbsp;</td>
						</tr>			
					-->


							</table>
							</div>
							<!-- End of division --></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>



		<!-- For Travel Mode Entitle -->
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<tr>
							<s:if test="nextflag">
								<td width="60%" colspan="2"><s:checkbox name="modeEntitle"
									id="modeEntitle" onclick=" modelist()" /> <strong> <label
									class="set" id="trModeEnt" name="trModeEnt"
									ondblclick="callShowDiv(this);"><%=label.get("trModeEnt")%></label>
								<font color="red">*</font> </strong></td>
								<!-- 
								<td width="40%"  colspan="1"><strong><label
										class="set" id="proof" name="proof"
										ondblclick="callShowDiv(this);"><%=label.get("proof")%></label></strong><s:select name="proofIdtrvl"
											list="#{'N':'No','Y':'YES'}" /></td> -->
								<s:hidden name="proofIdtrvl" value="" />
							</s:if>
							<s:else>
								<td width="60%" colspan="2"><s:checkbox name="modeEntitle"
									disabled="true" id="modeEntitle" onclick=" modelist()" /> <strong><label
									class="set" id="trModeEnt" name="trModeEnt"
									ondblclick="callShowDiv(this);"><%=label.get("trModeEnt")%></label></strong>
								</td>
								<!--  <td width="40%"  colspan="1"><strong><label
										class="set" id="proof" name="proof"
										ondblclick="callShowDiv(this);"><%=label.get("proof")%></label></strong><s:select name="proofIdtrvl"
											list="#{'N':'No','Y':'YES'}" /></td>-->
								<s:hidden name="proofIdtrvl" value="" />
							</s:else>
							<td colspan="1">&nbsp;</td>
						</tr>


						<s:if test="nextflag">
							<tr>
								<td colspan="3"><label class="set" id="mapTrMode"
									name="mapTrMode" ondblclick="callShowDiv(this);"><%=label.get("mapTrMode")%></label>
								<font color="red">*</font> : <s:hidden name="expCateTravelId" />
								<s:hidden name="expCateTravelUnit" /> <s:textfield
									name="expCateTravel" readonly="true" /> <img
									src="../pages/images/recruitment/search2.gif" class="iconImage"
									height="18" align="absmiddle" width="18"
									onclick="javascript:callsF9(500,325,'TravelPolicy_f9ExpenseCatTravel.action');">
								</td>
							</tr>
						</s:if>

						<s:else>
							<tr>
								<td colspan="2"><label class="set" id="mapTrMode"
									name="mapTrMode" ondblclick="callShowDiv(this);"><%=label.get("mapTrMode")%></label>
								<font color="red">*</font> : <s:hidden name="expCateTravelId" />
								<s:hidden name="expCateTravelUnit" /> <s:textfield
									name="expCateTravel" disabled="true" /></td>
							</tr>
						</s:else>


						<tr>
							<td id="mode">
							<table width="100%">
								<tr>
									<td><a href="javascript:openAll();">open all </a> | <a
										href="javascript:closeAll();">close all</a></td>
								</tr>
								<tr>
									<td><script type="text/javascript">
													//alert('x');							
													createTree(Tree);
											</script></td>
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



		<!-- For Lodging  Entitle -->
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<tr>
							<s:if test="nextflag">
								<td width="60%" colspan="2"><s:checkbox name="lodgemode"
									id="lodgemode" onclick="lodgemodelist()" /> <strong>
								<label class="set" id="lodgEnt" name="lodgEnt"
									ondblclick="callShowDiv(this);"><%=label.get("lodgEnt")%></label><font
									color="red">*</font> </strong></td>
								<!--  
								<td width="40%"  colspan="1"><strong><label
										class="set" id="proof" name="proof"
										ondblclick="callShowDiv(this);"><%=label.get("proof")%></label></strong><s:select name="proofIdlodge" 
											list="#{'N':'No','Y':'YES'}" /></td> -->
								<s:hidden name="proofIdlodge" value="" />
							</s:if>
							<s:else>
								<td width="60%" colspan="1"><s:checkbox name="lodgemode"
									disabled="true" id="lodgemode" onclick="lodgemodelist()" /> <strong><label
									class="set" id="lodgEnt" name="lodgEnt"
									ondblclick="callShowDiv(this);"><%=label.get("lodgEnt")%></label></strong>
								</td>
								<!--  
								<td width="40%"  colspan="1"><strong><label
										class="set" id="proof" name="proof"
										ondblclick="callShowDiv(this);"><%=label.get("proof")%></label></strong><s:select name="proofIdlodge"
											list="#{'N':'No','Y':'YES'}" /></td> -->
								<s:hidden name="proofIdlodge" value="" />
							</s:else>
							<td colspan="1">&nbsp;</td>
						</tr>

						<s:if test="nextflag">
							<tr>
								<td colspan="3"><label class="set" id="mapLodg"
									name="mapLodg" ondblclick="callShowDiv(this);"><%=label.get("mapLodg")%></label>
								<font color="red">*</font> : <s:hidden name="expCateHotelId" />
								<s:hidden name="expCateHotelUnit" /> <s:textfield
									name="expCateHotel" readonly="true" /> <img
									src="../pages/images/recruitment/search2.gif" class="iconImage"
									height="18" align="absmiddle" width="18"
									onclick="javascript:callsF9(500,325,'TravelPolicy_f9ExpHotel.action');">
								</td>
							</tr>
						</s:if>

						<s:else>
							<tr>
								<td colspan="2"><label class="set" id="mapLodg"
									name="mapLodg" ondblclick="callShowDiv(this);"><%=label.get("mapLodg")%></label><font
									color="red">*</font> : <s:hidden name="expCateHotelId" /> <s:hidden
									name="expCateHotelUnit" /> <s:textfield name="expCateHotel"
									disabled="true" /></td>
							</tr>
						</s:else>
						<tr>
							<td id="mode1">
							<table width="100%">
								<tr>
									<td><a href="javascript:openAll1();">open all</a> | <a
										href="javascript:closeAll1();">close all</a></td>
								</tr>
								<tr>
									<td><script type="text/javascript">
													createTree1(Tree1);
													//alert('y');
												</script></td>
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


		<!-- policy guideline -->
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2"> <s:hidden name="settleDays"/>  
						<!--<s:if test="nextflag">
							<tr>
								<td width="100%" colspan="3"><label class="set"
									id="settleDay" name="settleDay" ondblclick="callShowDiv(this);"><%=label.get("settleDay")%></label><font
									color="red">*</font> :&nbsp; <s:textfield size="10"
									name="settleDays" onkeypress="return numbersOnly();"
									maxlength="3" /></td>
							</tr>
						</s:if>

						<s:else>
							<tr>
								<td colspan="3"><label class="set" id="settleDay"
									name="settleDay" ondblclick="callShowDiv(this);"><%=label.get("settleDay")%></label><font
									color="red">*</font> :&nbsp; <s:textfield size="10"
									name="settleDays" onkeypress="return numbersOnly();"
									disabled="true" maxlength="6" /></td>
							</tr>
						</s:else>

						--><tr>
							<td colspan="3"><Strong><label class="set"
								id="polGuid" name="polGuid" ondblclick="callShowDiv(this);"><%=label.get("polGuid")%></label></Strong>
							</td>
						</tr>

						<tr>
							<td width="15%" class="formtext"><label name="upload.file"
								id="upload.file" ondblclick="callShowDiv(this);"><%=label.get("upload.file")%></label>
							:</td>
							<td width="40%">
							<table>
								<tr>
									<td><s:textfield name="uploadFileName" readonly="true"
										size="20" /></td>
									<td><input name="Upload" type="button" class="token"
										value="Browse" onclick="uploadFile('uploadFileName');" /></td>
								</tr>
							</table>
							</td>
							<td><a href="#" onclick="showRecord();">View Policy
							Guidelines</a> <!-- <input type="button" name="show"  class="token" value="Show File" onclick="showRecord();" /> -->
							</td>
						</tr>

						<!-- 
					
						<s:if test="nextflag">
							<tr>
								<td width="97%" colspan="1"><s:hidden name="textAreaLength"
									id="textAreaLength" value="0" /> <s:textarea name="guidLines"
									cols="118" rows="5"
									onkeyup="callLength('guidLines','descCnt','2000');" /> <img
									src="../pages/images/zoomin.gif" class="iconImage" height="11"
									width="11"
									onclick="callWindow('paraFrm_guidLines','polGuid','','paraFrm_descCnt','2000');">
								Remaining chars <input type="text" name="descCnt"
									id="paraFrm_descCnt" size="5" readonly="readonly"></td>
							</tr>
						</s:if>

						<s:else>
							<tr>
								<td width="97%" colspan="1"><s:hidden name="textAreaLength"
									id="textAreaLength" value="0" /> <s:textarea name="guidLines"
									cols="118" rows="5" onkeyup="return textCounter(this,2000)"
									onkeydown="return textCounter(this,2000)" disabled="true" /></td>
							</tr>
						</s:else>			
					
					
					 -->



					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>



		<!--setting flags for hotel  -->

		<%
				try {
				String[][] menuFlags = (String[][]) request
				.getAttribute("menuFlags");
				int j = 0;
				if (items != null && items.length > 0) {
					for (j = 0; j < items.length; j++) {
				int k = 0;
				if (menuFlags != null && menuFlags.length > 0) {
					for (k = 0; k < menuFlags.length; k++) {
						if (items[j][0].equals(menuFlags[k][0])) {
		%>
		<s:if test="nextflag">
			<%
								if (String.valueOf(menuFlags[k][1]).equals(
								"Y")) {
			%>

			<script>
			//alert('x');
			document.getElementById('childflag').value='Y'
			document.getElementById('insert<%=String.valueOf(items[j][0])%>').checked =true				
		</script>

			<%
			}
			%>
		</s:if>

		<s:else>
			<%
								if (String.valueOf(menuFlags[k][1]).equals(
								"Y")) {
			%>
			<script>
				document.getElementById('insert<%=String.valueOf(items[j][0])%>').checked =true;
				document.getElementById('insert<%=String.valueOf(items[j][0])%>').disabled=true;
		</script>
			<%
			} else {
			%>
			<script>
				document.getElementById('insert<%=String.valueOf(items[j][0])%>').disabled=true;	
		</script>
			<%
			}
			%>
		</s:else>

		<%
					}
					}
				}
					}
				}
			} catch (Exception e) {
				System.out.println("Exception ---3");
				e.printStackTrace();
			}
		%>

		<!-- setting flags for lodge -->

		<%
				try {
				String[][] lodgeMenuFlags = (String[][]) request
				.getAttribute("lodgeMenuFlags");

				int j = 0;
				for (j = 0; j < lodgeitems.length; j++) {
					int k = 0;
					if (lodgeMenuFlags != null && lodgeMenuFlags.length > 0) {
				for (k = 0; k < lodgeMenuFlags.length; k++) {

					if (lodgeitems[j][0].equals(lodgeMenuFlags[k][0])) {
		%>
		<s:if test="nextflag">
			<%
							if (String.valueOf(lodgeMenuFlags[k][1])
							.equals("Y")) {
			%>
			<script>
										
										document.getElementById('lodgechildflag').value='Y'
				document.getElementById('insertlodge<%=String.valueOf(lodgeitems[j][0])%>').checked =true;
		</script>
			<%
			}
			%>
		</s:if>
		<s:else>
			<%
							if (String.valueOf(lodgeMenuFlags[k][1])
							.equals("Y")) {
			%>
			<script>
				document.getElementById('insertlodge<%=String.valueOf(lodgeitems[j][0])%>').checked =true;
				document.getElementById('insertlodge<%=String.valueOf(lodgeitems[j][0])%>').disabled=true;
		</script>
			<%
			} else {
			%>
			<script>
												document.getElementById('insertlodge<%=String.valueOf(lodgeitems[j][0])%>').disabled=true;	
											</script>
			<%
			}
			%>

		</s:else>


		<%
				}
				}
					}
				}
			} catch (Exception e) {
				System.out.println("Exception ---5");
				e.printStackTrace();
			}
		%>









		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="20%" align="right"><font><b>Page 3 of 3</font></td>
				</tr>
			</table>
			<label></label></td>
		</tr>
	</table>


	<s:hidden name="dataPath" />



</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<script type="text/javascript">


function showRecord()
	{
		var fileName =document.getElementById('paraFrm_uploadFileName').value;
		if(fileName=="")
		{
			alert("Please Upload File.");
			return false;
		}
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = "TravelPolicy_viewAttachedProof.action?fileName="+fileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
	}


 modelist();
 lodgemodelist()
 function modelist()
 {

if(document.getElementById('modeEntitle').checked)
{
 
   document.getElementById('mode').style.display='';
   openAll();
    
}
   else
   {
     
    document.getElementById('mode').style.display='none';
    
   }
   
 }
 
 function  lodgemodelist()
 {
	
	// document.getElementById('mode1').style.display='none';
	if(document.getElementById('lodgemode').checked)
	{
	
	   document.getElementById('mode1').style.display='';
	   openAll1();
	    
}
   else{
    document.getElementById('mode1').style.display='none';
    closeAll1();
    }
 }
	
    function previousFun()
     {   
         document.getElementById('paraFrm').action = "TravelPolicy_previousSecond.action";
		 document.getElementById('paraFrm').submit();
		 document.getElementById('paraFrm').target ="main"; 
    }
    
    function returntolistFun()
     {  
         document.getElementById('paraFrm').action = "TravelPolicy_cancel.action?saveType=details";
		 document.getElementById('paraFrm').submit();
		 document.getElementById('paraFrm').target ="main"; 
    }
    
   function editFun()
     {  
    // alert('x');
      	 //document.getElementById('paraFrm_editFlag').value="false";
         document.getElementById('paraFrm').action = "TravelPolicy_edit.action?saveType=details";
		 document.getElementById('paraFrm').submit();
		 document.getElementById('paraFrm').target ="main"; 
    }
    
    
    function saveFun()
     {   
     
     try{
     var travelId=document.getElementById('paraFrm_expCateTravel').value;
  
  
  
      var logeId=document.getElementById('paraFrm_expCateHotel').value;

     
      //var days=document.getElementById('paraFrm_settleDays').value;
    //  alert('days'+days);
    
         if(travelId=='')
         {
         alert(" please Select  "+document.getElementById('mapTrMode').innerHTML.toLowerCase());
          document.getElementById('paraFrm_expCateTravel').focus(); 
         return false;
         }
         if(logeId=='')
         {
         alert(" please Select  "+document.getElementById('mapLodg').innerHTML.toLowerCase());
         document.getElementById('paraFrm_expCateHotel').focus(); 
           return false;
         }
         /*if(days=='')
         {
         alert(" please  enter "+document.getElementById('settleDay').innerHTML.toLowerCase());
           document.getElementById('paraFrm_settleDays').focus();
           return false;
         }
         if(days>365)
         {
         alert(document.getElementById('settleDay').innerHTML.toLowerCase()+"  should not be grater than 365 ");
           return false;
         }*/
         
     
		   		if(document.getElementById('modeEntitle').checked==false)
		   		{
				    alert("Please select "+document.getElementById('trModeEnt').innerHTML.toLowerCase()+" checkbox");
				    return false;
		   		}
		   		if(document.getElementById('childflag').value=='N')
		   		{
		   		   alert(' Please select the option in '+document.getElementById('trModeEnt').innerHTML.toLowerCase());
				    return false;
		   		}
		    	if(document.getElementById('lodgemode').checked==false)
		   		{
				    alert(" Please select the "+document.getElementById('lodgEnt').innerHTML.toLowerCase()+" checkbox");
				    return false;
				}
				if(document.getElementById('lodgechildflag').value=='N')
		   		{
		   		   alert(' Please select the option  in '+document.getElementById('lodgEnt').innerHTML.toLowerCase());
				    return false;
		   		} 
         document.getElementById('paraFrm').action = "TravelPolicy_saveDetails.action";
		 document.getElementById('paraFrm').submit();
		 document.getElementById('paraFrm').target ="main"; 
     }
     catch(e){ }
    //alert('x');
     
    }
    
    //for saveandprevious 
    function  saveandpreviousFun()
    {
    //alert('x');
    var travelId=document.getElementById('paraFrm_expCateTravel').value;
  
         var logeId=document.getElementById('paraFrm_expCateHotel').value;
    // alert(logeId);
      //var days=document.getElementById('paraFrm_settleDays').value;
         if(travelId=='')
         {
         alert(" please Select  "+document.getElementById('mapTrMode').innerHTML.toLowerCase());
         document.getElementById('paraFrm_expCateTravel').focus(); 
         return false;
         }
         if(logeId=='')
         {
         alert(" please Select  "+document.getElementById('mapLodg').innerHTML.toLowerCase());
         document.getElementById('paraFrm_expCateHotel').focus(); 
           return false;
         }
         /*if(days=='')
         {
          alert(" please  enter "+document.getElementById('settleDay').innerHTML.toLowerCase());
         document.getElementById('paraFrm_settleDays').focus();
           return false;
         }
          if(days>365)
         {
         alert(document.getElementById('settleDay').innerHTML.toLowerCase()+"  should not be grater than 365 ");
           return false;
         }*/
         
     
		   		if(document.getElementById('modeEntitle').checked==false)
		   		{
				  alert("Please select "+document.getElementById('trModeEnt').innerHTML.toLowerCase()+" checkbox");
				    return false;
		   		}
		   		if(document.getElementById('childflag').value=='N')
		   		{
		   		  alert(' Please select the option in '+document.getElementById('trModeEnt').innerHTML.toLowerCase());
				    return false;
		   		}
		    	if(document.getElementById('lodgemode').checked==false)
		   		{
				    alert(" Please select the "+document.getElementById('lodgEnt').innerHTML.toLowerCase()+" checkbox");
				    return false;
				}
				if(document.getElementById('lodgechildflag').value=='N')
		   		{
		   		   alert(' Please select the option  in '+document.getElementById('lodgEnt').innerHTML.toLowerCase());
				    return false;
		   		} 
		   		
         document.getElementById('paraFrm').action = "TravelPolicy_saveAndPrevioudDetails.action";
		 document.getElementById('paraFrm').submit();
		 document.getElementById('paraFrm').target ="main"; 
    }
  
function textCounter(field,  maxlimit) { 
		
		if (field.value.length > maxlimit){			
				alert('Field value should not exceed '+maxlimit+' chars.');
				field.value = field.value.substring(0, maxlimit);	
				field.focus;			
				return false;	 
				//field.value = field.value.substring(0, maxlimit);			
		}
		
		return true;
		
	}
	
	 function callTextArea()
	 {
	  var length = document.getElementById('textAreaLength').value;
	  document.getElementById('paraFrm_guidLines').style.height=eval(length)+eval(200);
	  document.getElementById('textAreaLength').value=eval(length)+eval(200); 
	 }  
	 
	 function callTextAreaMinus()
	 {
	 
	 var size =document.getElementById('paraFrm_guidLines').style.height;
	 var abc = size.split("px"); 
	  var length = document.getElementById('textAreaLength').value; 
	  if(abc[0]>201)
	  {
		  document.getElementById('paraFrm_guidLines').style.height=eval(length)-eval(200);
		  document.getElementById('textAreaLength').value=eval(length)-eval(200); 
	  }
	  
	    if(abc[0]>101 && abc[0]<=200)
	  {
		  document.getElementById('paraFrm_guidLines').style.height=eval(length)-eval(100);
		  document.getElementById('textAreaLength').value=eval(length)-eval(100); 
	  }
	  
	 }   
   
   
      function callExpChk(id)
       {   
	     try
	     {
	      	if(document.getElementById('actualAtt'+id).checked)
	      	{	     
	      		
	      		document.getElementById('expValuewithBill'+id).readOnly='true';
	      		document.getElementById('expValuewithBill'+id).value='0';
	      
		      	document.getElementById('expValuewithoutBill'+id).readOnly='true';  
		      	document.getElementById('expValuewithoutBill'+id).value='0';
	      
	    	  	document.getElementById('hidActualAtt'+id).value="Y";  
	    	 // 	alert("Value of hidActualAtt ========================> "+hidActualAtt); 
	      	//	expAddition();
	      	}
	      	else
	      	{   
	        	
	        	document.getElementById('expValuewithBill'+id).readOnly=false; 
	        	document.getElementById('expValuewithoutBill'+id).readOnly=false;  
	        	document.getElementById('hidActualAtt'+id).value="N"; 
	        //	expAddition();
	      	}
	     }
	     catch(e)
	     {
	  		   alert("Exception Occured ========> "+e);      					
	     } 	
     }
    
    
     
     function callTravelMode(id)
    {  
     var from =eval(id)+eval(1);
     var length =eval(travelArr.length)+eval(1); 
      if(document.getElementById('trModeStatus'+id).checked)
      {    
        document.getElementById('hidtrModeStatus'+id).value="Y";  
        for(i=from;i<length;i++)
	        {
		         document.getElementById('hidtrModeStatus'+i).value="Y";
		         document.getElementById('trModeStatus'+i).checked=true; 
	        }  
      }else
      {   
        document.getElementById('hidtrModeStatus'+id).value="N";
          for(i=1;i<from;i++)
	        {
		         document.getElementById('hidtrModeStatus'+i).value="N";
		         document.getElementById('trModeStatus'+i).checked=false; 
	        } 
      }
    }
    
    
    function callHotelTypeStatus(id)
    { 
	     var from =eval(id)+eval(1);
	     var length =eval(hotelArr.length)+eval(1); 
	     
	     if(document.getElementById('hotelStatus'+id).checked)
	      {      document.getElementById('hidHotelStatus'+id).value="Y";
		          for(i=from;i<length;i++)
			        {
				         document.getElementById('hidHotelStatus'+i).value="Y";
				         document.getElementById('hotelStatus'+i).checked=true; 
			        }   
	      }else
	      {   document.getElementById('hidHotelStatus'+id).value="N";
	          for(i=1;i<from;i++)
		        {
			         document.getElementById('hidHotelStatus'+i).value="N";
			         document.getElementById('hotelStatus'+i).checked=false; 
		        }  
	       }
    }
    
    
       function callRoomStatus(id)
    { 
	     var from =eval(id)+eval(1);
	     var length =eval(roomArr.length)+eval(1); 
	     
	     if(document.getElementById('roomStatus'+id).checked)
	      {      document.getElementById('hidRoomStatus'+id).value="Y";
		          for(i=from;i<length;i++)
			        {
				         document.getElementById('hidRoomStatus'+i).value="Y";
				         document.getElementById('roomStatus'+i).checked=true; 
			        }   
	      }else
	      {   document.getElementById('hidRoomStatus'+id).value="N";
	          for(i=1;i<from;i++)
		        {
			         document.getElementById('hidRoomStatus'+i).value="N";
			         document.getElementById('roomStatus'+i).checked=false; 
		        }  
	       }
    }
   
    
   function expAddition(){ 
   //alert('x');
   //alert(expArr.length);
	      var tot=0;
	     for(var i=1; i<=expArr.length;i++)
	       {
	       //alert('for');
	        var expValuewithBill =document.getElementById('expValuewithBill'+i).value; 
	        
	        var expValuewithoutBill =document.getElementById('expValuewithoutBill'+i).value; 
	         
	      // alert('expValue'+expValue);
	          if(expValuewithBill=="" && expValuewithoutBill=="") 
	          { 
	             expValuewithBill=0;
	             expValuewithoutBill=0;
	          }
	          tot=Math.abs(tot)+Math.abs(expValuewithBill)+Math.abs(expValuewithoutBill);
	       }  
	       //alert('tot'+tot);
	      document.getElementById('paraFrm_totExpAmt').value =tot;
    }
    
 function deleteFun()
     {  
     var conf=confirm("Do you really want to delete this record ?");
  	  if(conf) 
  	 	 {
  	 	  document.getElementById('paraFrm_editFlag').value="false";
  		 document.getElementById('paraFrm').action = "TravelPolicy_delete.action";
		 document.getElementById('paraFrm').submit();
		 document.getElementById('paraFrm').target ="main";
  		 }else
  		 {
  		   return false;
  		 } 
    }
    //for travel mode checkbox
     /*function modelist()
     {
    // alert('x');
     alert(document.getElementById('modeEntitle').checked);
     if(document.getElementById('modeEntitle').checked)
     {
     //alert('if');
     document.getElementById('paraFrm').action = "TravelPolicy_getItems.action";
      document.getElementById('paraFrm').submit();
	 document.getElementById('paraFrm').target ="main";
     
     }
     }*/
     //for lodge entitle
   /*function  lodgemodelist()
   {
   alert('ff');
    if(document.getElementById('lodgemode').checked)
     {
     alert('lodgemode');
     document.getElementById('paraFrm').action = "TravelPolicy_getLodgeItems.action";
      document.getElementById('paraFrm').submit();
	 document.getElementById('paraFrm').target ="main";
     
     }
     else
     {
     
     document.getElementById('paraFrm').action = "TravelPolicy_getLodgeItemsReset.action";
      document.getElementById('paraFrm').submit();
	 document.getElementById('paraFrm').target ="main";
   }
   }*/
     
     function hasParentNode(chilNode,flagType) {

	
	for (i=0; i<nodes.length; i++) {
		var nodChild = nodes[i].split("|");
		 if (nodChild[0] == chilNode) {
			//alert('chilNode '+chilNode);
			if( nodChild[1] > 0){
				if(document.getElementById(flagType+chilNode).checked){
					document.getElementById(flagType+nodChild[1]).checked = true;
				}else{
					//document.getElementById('insert'+nodChild[1]).checked = false;
				}
				hasParentNode(nodChild[1],flagType) ;
			}  //END OF 2ND IF
		
		} 
	}
	
//	return false;
}


function checkNode(parent,flagType) {

//alert("checkNode"+parent);

var checkFlag='false';
	for (var i = 0; i < nodes.length; i++) {
	//alert('gg'+nodes.length);
		var nodeVal= nodes[i].split("|");
		//alert('nodeVal'+nodeVal);
		//alert('nodeVal[0]'+nodeVal[0]);
		if (nodeVal[0] == parent){
			checkFlag='true';
			//alert('checkFlag'+nodeVal[0]);
		}
		//alert('out if');
		if(checkFlag=='true'){
		//alert('in if');
		//alert('afer in if--'+document.getElementById(flagType+parent).value);
		
				
				document.getElementById(flagType+nodeVal[0]).checked=true;
			
				
				
		
		/*if (nodeVal[1] == parent) {
			if(document.getElementById(flagType+parent).checked){
				document.getElementById(flagType+nodeVal[0]).checked=true;
				var hcn	= hasChild(nodeVal[0],flagType);
				if(hcn){
					checkNode(nodeVal[0],flagType);
				}
			}else{
				document.getElementById(flagType+nodeVal[0]).checked=false;
				var hcn	= hasChildFalse(nodeVal[0],flagType);
				if(hcn){
					checkNode(nodeVal[0],flagType);
				}
			}
		}*/
		
	}
	else{
	document.getElementById(flagType+nodeVal[0]).checked=false;
	}
	
	}
	var count=0;
	for (var i = 0; i < nodes.length; i++) {
	var nodeVal= nodes[i].split("|");
	
	if(document.getElementById(flagType+nodeVal[0]).checked){
	
		count++;
	}
	
	}
	if(count>0){
		document.getElementById('childflag').value='Y'
	}
	else{
		document.getElementById('childflag').value='N'
	}
	
}

function hasChild(newNode,flagType) {

//alert('inside has child  '+flagType);
//alert('newNode'+newNode);
	for (i=0; i< nodes.length; i++) {
		var nodeVal = nodes[i].split("|");
		//alert('newNode '+newNode);
		for (j=0; j< nodeVal.length; j++) {
		
		}
		if (nodeVal[1] == newNode){ 
		
			document.getElementById(flagType+newNode).checked = true;
			return true;
		}
	}
	return false;
}

function hasChildFalse(newNode,flagType) {

	for (i=0; i< nodes.length; i++) {
		var nodeVal = nodes[i].split("|");
		if (nodeVal[1] == newNode){ 
			document.getElementById(flagType+newNode).checked = false;
			return true;
		}
	}
	return false;
}
    
     function hasParentNode1(chilNode,flagType) {

	//alert('parent');
	for (i=0; i<nodes1.length; i++) {
		var nodChild = nodes1[i].split("|");
		 if (nodChild[0] == chilNode) {
		//	alert(chilNode);
			if( nodChild[1] > 0){
		//	alert(nodChild[1]);
				if(document.getElementById(flagType+chilNode).checked){
					document.getElementById(flagType+nodChild[1]).checked = true;
				}else{
					//document.getElementById('insert'+nodChild[1]).checked = false;
				}
				hasParentNode1(nodChild[1],flagType) ;
			}  //END OF 2ND IF
		
		} 
	}
	
//	return false;
}

//for test 
function checkNode1(parent,flagType) {


//alert('parent11    '+parent);
var checkFlag='false';
	for (var i = 0; i < nodes1.length; i++) {
		
		var nodeVal= nodes1[i].split("|");
		
		if (nodeVal[0] == parent){
			checkFlag='true';
			//alert('checkFlag  nodeVal[0]   '+nodeVal[0]);
		}
		if(checkFlag=='true'){
	//alert('in if');
		
		
		//alert('nodeVal[0]---'+flagType+nodeVal[0]);
		     document.getElementById(flagType+nodeVal[0]).checked=true;
		}else{
		//alert('else')
			document.getElementById(flagType+nodeVal[0]).checked=false;
		}
		
	}
	
	var count=0;
	for (var i = 0; i < nodes1.length; i++) {
	var nodeVal= nodes1[i].split("|");
	
	if(document.getElementById(flagType+nodeVal[0]).checked){
	
		count++;
	}
	
	}
	if(count>0){
		document.getElementById('lodgechildflag').value='Y'
	}
	else{
		document.getElementById('lodgechildflag').value='N'
	}
	
	
	
	
}

/*function checkNode1(parent,flagType) {

	//alert('parent  '+parent);
	var checkFlag='false';
	for (var i = 0; i < nodes1.length; i++) {
		
		var nodeVal= nodes1[i].split("|");
		//alert("parent=="+parent);
		//alert("nodeVal=="+nodeVal);
		//alert("nodeVal[1]=="+nodeVal[1]);
		if (nodeVal[0] == parent) {
			if(document.getElementById(flagType+parent).checked){
				document.getElementById(flagType+nodeVal[0]).checked=true;
				var hcn	= hasChild(nodeVal[0],flagType);
				if(hcn){
					checkNode(nodeVal[0],flagType);
				}
			}else{
				document.getElementById(flagType+nodeVal[0]).checked=false;
				var hcn	= hasChildFalse(nodeVal[0],flagType);
				if(hcn){
					checkNode(nodeVal[0],flagType);
				}
			}
		}
		
	}
}*/

function hasChild1(newNode,flagType) {
//alert('newNode1 :  '+newNode);
	for (i=0; i< nodes1.length; i++) {
		var nodeVal = nodes1[i].split("|");
		
		for (j=0; j< nodeVal.length; j++) {
		
		}
		if (nodeVal[1] == newNode){ 
			document.getElementById(flagType+newNode).checked = true;
			return true;
		}
	}
	return false;
}

function hasChildFalse1(newNode,flagType) {
//alert('false1');

	for (i=0; i< nodes1.length; i++) {
		var nodeVal = nodes1[i].split("|");
		if (nodeVal[1] == newNode){ 
			document.getElementById(flagType+newNode).checked = false;
			return true;
		}
	}
	return false;
}

function previewTemplate(){
	window.open('','new','top=100,left=300,width=400,height=400,scrollbars=yes,status=no,resizable=no');
	document.getElementById("paraFrm").target="new";
	document.getElementById("paraFrm").action='TravelPolicy_View.action';
	document.getElementById("paraFrm").submit();
	document.getElementById("paraFrm").target="main";
}



function uploadFile(fieldName) 
{
	try
	{
		var path = document.getElementById("paraFrm_dataPath").value;
		window.open('<%=request.getContextPath()%>/pages/recruitment/uploadResume.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
	}
	catch(e)
	{
		alert("Error ===> "+e);
	}
}		

function numbersWithDot()
{
	
	document.onkeypress = numbersWithDot;
	var key //= (window.event) ? event.keyCode : e.which;
	if (window.event)
	    key = event.keyCode
	else
	   	key = e.which
	clear();
	// Was key that was pressed a numeric character (0-9) or backspace (8)?
	if ( key > 47 && key < 58 || key == 8 || key == 0 || key == 46)
	 	return true; // if so, do nothing
	else // otherwise, discard character
		return false;
	if (window.event) //IE
	    window.event.returnValue = null;     else //Firefox
	    e.preventDefault();
}




function numbersOnly(e){
	document.onkeypress = numbersOnly;
	var key //= (window.event) ? event.keyCode : e.which;
	if (window.event)
	    key = event.keyCode
	else
	   	key = e.which
	clear();
	// Was key that was pressed a numeric character (0-9) or backspace (8)?
	if ( key > 47 && key < 58 || key == 8 || key == 0)
	 	return true; // if so, do nothing
	else // otherwise, discard character
		return false;
	if (window.event) //IE
	    window.event.returnValue = null;     else //Firefox
	    e.preventDefault();
}


function clear(){
	document.onkeypress = "";
}

     
</script>

