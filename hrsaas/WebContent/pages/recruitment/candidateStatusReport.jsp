<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<link rel="stylesheet" type="text/css"
	href="../pages/common/tabcontent.css" />
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>
<s:form action="CandidateStatusReport" method="post" id="paraFrm"
	theme="simple">
	<s:hidden name="report" />
	<s:hidden name="reportAction" value='CandidateStatusReport_getReport.action' />
	<s:hidden name="experience" />
	<s:hidden name="itrEmailFlag" />
	<s:hidden name="itrSrnFlag" />
	<s:hidden name="itrTestFlag" />
	<s:hidden name="itrConFlag" />
	<s:hidden name="postingDate" />
	<s:hidden name="itrInrvRndFlag" />
	<s:hidden name="itrOffStatFlag" />
	<s:hidden name="itrAppntStatFlag" />
	<s:hidden name="postingDate" />
	<s:hidden name="itrHdrEmail" />
	<s:hidden name="itrHdrSrn" />
	<s:hidden name="itrHdrTest" />
	<s:hidden name="itrHdrInrvRnd" />
	<s:hidden name="convertemp" />
	<s:hidden name="itrHdrOffStat" />
	<s:hidden name="itrHdrAppntStat" />
	<s:hidden name="itrHdrCon" />
	<s:hidden name="searchstatus" />
	<s:hidden name="position" />
	<s:hidden name="testresult" />
	<s:hidden name="candscreeningstatus" />
	<s:hidden name="intvstatus" />
	<s:hidden name="intvround" />
	<s:hidden name="makeoffer" />
	<s:hidden name="offerstatus" />
	<s:hidden name="appointstatus" />
	<s:hidden name="requisitionCode" />
	<s:hidden name="divID" />
	<s:hidden name="editVal" />
	<s:hidden name="exportAllData" />
	<s:hidden name="asc1" />
	<s:hidden name="desc1" />
	<s:hidden name="asce1" />
	<s:hidden name="desce1" />
	<s:hidden name="ascc1" />
	<s:hidden name="descc1" />
	<s:hidden name="aID" />
	<s:hidden name="myReqPage" />
	<s:hidden name="selectedReq" />
	<s:hidden name="noOfCol" />
	<s:hidden name="appRepCode" />
	<s:hidden name="reportFlag" />
	<s:hidden name="radioFlag" />
	<s:hidden name="radio1" />
	<s:hidden name="radioFlag1" />
	<s:hidden name="radio2" />
	<s:hidden name="radioFlag2" />
	<s:hidden name="radio3" />
	<s:hidden name="hdPage" />
	<s:hidden name="radioFlag3" />
	<s:hidden name="pageFlag" />
	<s:hidden name="editReqFlag" />

	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="2">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Candidate
					Status Report </strong></td>
				</tr>
			</table>
			</td>
		</tr>

     	<tr>
			<td>
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td nowrap="nowrap"><a href="#" onclick="callReset();">
							<img src="../pages/images/buttonIcons/Refresh.png"
								class="iconImage" align="absmiddle" title="PDF"> Reset </a>&nbsp;&nbsp;
							</td>
							<td width="100%" colspan="6"><%@ include
								file="/pages/common/reportButtonPanel.jsp"%></td>
						</tr>
					</table>
			</td>
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
<!--  
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2">

				<tr>
					<td width="100%"><s:if test="%{viewFlag}">

						<input type="button" value="Generate Report" class="token"
							onclick="return callReportCandidateStatus();" />

					</s:if> <s:submit cssClass="reset" action="CandidateStatusReport_reset"
						theme="simple" value="Reset"  /> <input
						type="button" class="add" theme="simple"
						value="Save Report Criteria" onclick="callSave();" /></td>
				</tr>


			</table>
			</td>
		</tr>-->
		
		<!--
		<tr valign="top">
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<div id="tabnav">


				<ul>
					<li><a id="filtOpt" title="Filter Option"
						href="javascript:callDivLoad('filtOpt', 'filterOption');">
					<div align="center"><span>&nbsp;&nbsp;Filter
					Option&nbsp;&nbsp;&nbsp;</span></div>
					</a></li>

				</ul>

				<ul>
					<li><a id="sortOpt" title="Sorting Option"
						href="javascript:callDivLoad('sortOpt', 'sortingOption');">
					<div align="center"><span>&nbsp;&nbsp;Sorting
					Option&nbsp;&nbsp;&nbsp;</span></div>
					</a></li>

				</ul>

				<ul>
					<li><a id="colDef" title="Column Definition"
						href="javascript:callDivLoad('colDef', 'columnDefinition');">
					<div align="center"><span>&nbsp;&nbsp;Column
					Definition&nbsp;&nbsp;&nbsp;</span></div>
					</a></li>

				</ul>


				<ul>
					<li><a id="advFilt" title="Advanced Filters"
						href="javascript:callDivLoad('advFilt', 'advancedFilters');">
					<div align="center"><span>&nbsp;&nbsp;Advanced
					Filters&nbsp;&nbsp;&nbsp;</span></div>
					</a></li>

				</ul>

				</div></table>
				</td>
				</tr>  -->
				<!-- Filter option begins -->
			
			<tr valign="top">
					<td width="100%">
					<div id="filterOption" >

					<table width="100%" border="0" id="reportBodyTable" class="formbg">
				<!--  	
				<tr>
					<td width="20%"><label class="set" id="save.setting"
						name="save.setting" ondblclick="callShowDiv(this);"><%=label.get("save.setting")%></label>
					</td>
					<td width="2%" >:</td>
					<td colspan="1"><s:select headerKey="-1"
						headerValue="--Select--" name="searchSetting" list="%{hashMap}"
						onchange="callVal();" /></td>
				</tr>-->	
						
						
						<tr>
							<td width="20%" colspan="1" class="formtext"><label
								class="set" id="reqname1" name="reqname1"
								ondblclick="callShowDiv(this);"><%=label.get("reqname")%></label>
							</td>
							<td width="2%">:<s:hidden name="reqCode" /></td>
							<td width="78%" id="mpreqsnId" colspan="2" nowrap="nowrap">
							  <s:textfield name="reqname" size="30" theme="simple" readonly="true" />
							<img
								src="../pages/images/recruitment/search2.gif" height="15"
								align="absmiddle" width="16" onclick="return callReqsn();">
							</td>

						</tr>
                   
						<tr>
							<td width="20%" colspan="1"></td>
							<td width="2%" colspan="1"></td>
							<td width="9%" colspan="1" align="center"><strong
								class="forminnerhead">(OR)</strong></td>
						</tr>
					

						<tr>
							<td width="20%" colspan="1" class="formtext"><label
								class="set" id="candidate" name="candidate"
								ondblclick="callShowDiv(this);"><%=label.get("candidate")%></label>
							</td>
							<td width="2%">:<s:hidden name="candidateCode" /></td>
							<td width="78%" id="mpCandId"  colspan="2" nowrap="nowrap"><s:textfield
								name="candidateName" size="30" theme="simple" readonly="true" />
							<img
								src="../pages/images/recruitment/search2.gif" height="15"
								align="absmiddle" width="16" onclick="return callCand();"></td>

						</tr>
 
						<tr>
							<td width="20%" colspan="1"></td>
							<td width="2%" colspan="1"></td>
							<td width="9" colspan="1" align="center"><strong
								class="forminnerhead">(OR)</strong></td>
						</tr>
						
						<tr>
							<td width="20%" colspan="1" class="formtext"><label
								class="set" id="position" name="position"
								ondblclick="callShowDiv(this);"><%=label.get("position")%></label>
							</td>
							<td width="2%">:<s:hidden name="rankId" /></td>
							<td width="78%" colspan="2" nowrap="nowrap" id="mpPositionId"><s:textfield
								name="rankName" size="30" theme="simple" readonly="true" />
								<img
								src="../pages/images/recruitment/search2.gif" height="15"
								align="absmiddle" width="16" onclick="return callPosition();"></td>

						</tr>

						<tr>
							<td width="20%" colspan="1" class="formtext"><label
								class="set" id="reqs.date" name="reqs.date"
								ondblclick="callShowDiv(this);"><%=label.get("reqs.date")%>
							</td>
							<td width="2%">:</td>
							<td width="20%" colspan="1" nowrap="nowrap"><s:select
								name="reqsDateCombo"
								list="#{'':'--Select--', 'O ':'On', 'B ':'Before', 'A ':'After', 'OB':'On Or Before',
									            			'OA':'On Or After','F ':'From'}"
								cssStyle="width:75" onchange="enableToDate();" /><s:textfield
								name="frmDate" size="10"
								onkeypress="return numbersWithHiphen();" theme="simple"
								maxlength="10"
								onfocus="clearText('paraFrm_frmDate','dd-mm-yyyy');"
								onblur="setText('paraFrm_frmDate','dd-mm-yyyy');" /> <s:a
								href="javascript:NewCal('paraFrm_frmDate','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif"
									class="iconImage" height="15" align="absmiddle" width="16">
							</s:a></td>
							<td width="30%" colspan="1" nowrap="nowrap" align="right">
							<div id="toDateText"  style="display: none;">
							<label
								class="set" id="todate" name="todate"
								ondblclick="callShowDiv(this);"><%=label.get("todate")%></label>
							: <s:textfield name="toDate" size="10"
								onkeypress="return numbersWithHiphen();" theme="simple"
								maxlength="10"
								onfocus="clearText('paraFrm_toDate','dd-mm-yyyy');"
								onblur="setText('paraFrm_toDate','dd-mm-yyyy');" /> <s:a
								href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif"
									class="iconImage" height="15" align="absmiddle" width="16">
							</s:a></div></td>
						</tr>


						<tr>
							<td width="20%" colspan="1" class="formtext" nowrap="nowrap"><label
								class="set" id="reqs.stat" name="reqs.stat"
								ondblclick="callShowDiv(this);"><%=label.get("reqs.stat")%>
							</td>
							<td width="2%">:</td>
							<td width="78%" colspan="2" nowrap="nowrap"><s:select
								name="reqsStatus" cssStyle="width:75"
								list="#{'':'--Select--','O':'Open', 'C':'Close','R':'Canceled'}" /></td>
							<td width="25%" colspan="1"></td>
						</tr>

					</table>
					</div>
					</td>
				</tr>
	   <!-- Filter option ends -->
				
		<!-- Advance filter begins -->
		       <tr>
				<td>
					<div id="advancedFilters"> 

					<table width="100%" border="0">

						<tr>
							<td width="19%"><label class="set" id="candscreeningstatus"
								name="candscreeningstatus" ondblclick="callShowDiv(this);"><%=label.get("candscreeningstatus")%>
							</td>
							<td width="2%">:</td>
							<td colspan="1" nowrap="nowrap"><s:select name="advScrn"
								cssStyle="width:135"
								list="#{'':'--Select--', 'T ':'Shortlist For Test','I ':'Shortlist For Interview','B ':'Both'}" /></td>
						</tr>
						<s:hidden name="intRndVal" />
						<s:hidden name="advIntRnd" />
						
                        <tr>
						<td width="19%"><label class="set" id="offer.status"
							name="offer.status" ondblclick="callShowDiv(this);"><%=label.get("offer.status")%>
						</td>
						<td width="2%">:</td>
						<td colspan="1" nowrap="nowrap"><s:select name="advOffStat"
							cssStyle="width:135"
							list="#{'':'--Select--', 'D ':'Due','I ':'Issued','OA':'Accepted', 
										            			'OR':'Rejected','C ':'Canceled'}" />
						</td>
						</tr>

						<tr>

							<td width="19%"><label class="set" id="appoint.status"
								name="appoint.status" ondblclick="callShowDiv(this);"><%=label.get("appoint.status")%>
							</td>
							<td width="2%">:</td>
							<td colspan="1" nowrap="nowrap"><s:select
								name="advAppntStat" cssStyle="width:135"
								list="#{'':'--Select--', 'D ':'Due','I ':'Issued','OA':'Accepted', 
										            			'OR':'Rejected','C ':'Canceled'}" />
							</td>
						</tr>
						<!--
				   <tr id="setting">
					<td width="18%">
					<label class="set" id="setting.name" name="setting.name"
						ondblclick="callShowDiv(this);"><%=label.get("setting.name")%></label>
					</td>
					<td width="2%">:</td>
					<td width="74%"><s:textfield name="saveSetting" maxlength="25" />
					<input
						type="button" class="add" theme="simple"
						value="Save Report Criteria" onclick="callSave();" />
					</td>
					
				</tr>  -->
				
				</table>
			</div> 
		</td>
	</tr>
				
		<!-- Advance filter ends -->

				<tr valign="top">
					<td width="100%">
					<div id="sortingOption" style="display: none;">

					<table width="100%" border="0" class="formbg">

						<tr>
							<td width="23%">Sort By :</td>
							<td colspan="1" nowrap="nowrap"><s:select name="sortBy"
								headerKey="1" headerValue="--Select--" cssStyle="width:135"
								list="#{'RN':'Requisition Name','P ':'Position','CN':'Candidate Name' 
										            			}" /></td>
						</tr>
						<tr>
							<td width="23%"></td>

							<td><s:if test="radio1">

								<input type="radio" name="asc" id="asc" onclick="callAsc();"
									checked="checked" />Ascending </s:if> <s:else>
								<input type="radio" name="asc" id="asc" onclick="callAsc();" />Ascending
												 
												 </s:else> <s:if test="radioFlag1">
								<input type="radio" name="desc" id="desc" onclick="callDesc();"
									checked="checked" /> Descending
												</s:if><s:else>
								<input type="radio" name="desc" id="desc" onclick="callDesc();" />Descending
												
												</s:else></td>

						</tr>
						<tr>

							<td width="24%">Then By :</td>
							<td colspan="1" nowrap="nowrap"><s:select name="firstBy"
								headerKey="1" headerValue="--Select--" cssStyle="width:135"
								list="#{'RN':'Requisition Name','P ':'Position','CN':'Candidate Name' 
										            			}" /></td>
						<tr>
							<td width="24%"></td>
							<td><s:if test="radio2">
								<input type="radio" name="asce" id="asce" onclick="callAsce();"
									checked="checked" /> Ascending</s:if> <s:else>
								<input type="radio" name="asce" id="asce" onclick="callAsce();" /> Ascending</s:else>

							<s:if test="radioFlag2">
								<input type="radio" name="desce" id="desce"
									onclick="callDesce();" checked="checked" />Descending</s:if> <s:else>
								<input type="radio" name="desce" id="desce"
									onclick="callDesce();" />Descending</s:else></td>


						</tr>
						<tr>

							<td width="24%">Then By :</td>
							<td colspan="1" nowrap="nowrap"><s:select name="secondBy"
								headerKey="1" headerValue="--Select--" cssStyle="width:135"
								list="#{'RN':'Requisition Name','P ':'Position','CN':'Candidate Name' 
										            			}" /></td>
						<tr>
							<td width="24%"></td>

							<td><s:if test="radio3">
								<input type="radio" name="ascc" id="ascc" onclick="callAscc();"
									checked="checked" /> Ascending </s:if> <s:else>
								<input type="radio" name="ascc" id="ascc" onclick="callAscc();" /> Ascending </s:else>
							<s:if test="radioFlag3">
								<input type="radio" name="descc" id="descc"
									onclick="callDescc();" checked="checked" />Descending</s:if> <s:else>
								<input type="radio" name="descc" id="descc"
									onclick="callDescc();" />Descending
												</s:else></td>

							</td>
						</tr>
					</table>
					</div>
					</td>
				</tr>

				<tr valign="top">
					<td width="98%">
					<div id="columnDefinition" style="display: none;"><s:if
						test="pageFlag">
						<%
							Object[][] obj=(Object[][])request.getAttribute("checkBox");
						for(int i=0;i<obj.length;i++){
							for(int j=0;j<obj[0].length;j++){
							out.println("obj["+i+"]["+j+"] >>"+obj[i][j]);
							}
						}
			%>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="formbg">

							<tr>

								<td width="18%"></td>
								<td width="2%"><input type="checkbox" name="chkCand1"
									id="chkCand1" checked="true" disabled="true"
									onclick="callCheck();"
									<%=obj[0][0].equals("true")?"checked":"" %> /></td>
								<s:hidden name="chkCand" />
								<td width="25%"><label class="set" id="candidatename"
									name="candidatename" ondblclick="callShowDiv(this);"><%=label.get("candidatename")%></td>

								<td width="2%"></td>
								<td width="2%"><input type="checkbox" name="chkOffStat1"
									id="chkOffStat1" onclick="callCheck();"
									<%=obj[0][8].equals("true")?"checked":"" %> /></td>
								<s:hidden name="chkOffStat" />
								<td width="25%"><label class="set" id="offer.status"
									name="offer.status" ondblclick="callShowDiv(this);"><%=label.get("offer.status")%></td>
							</tr>

							<tr>
								<td width="18%"></td>
								<td width="2%"><input type="checkbox" name="chkEmail1"
									id="chkEmail1" onclick="callCheck();"
									<%=obj[0][1].equals("true")?"checked":"" %> /></td>
								<s:hidden name="chkEmail" />
								<td width="25%"><label class="set" id="email.id"
									name="email.id" ondblclick="callShowDiv(this);"><%=label.get("email.id")%></td>

								<td width="2%"></td>
								<td width="2%"><input type="checkbox" name="chkAppntStat1"
									id="chkAppntStat1" onclick="callCheck();"
									<%=obj[0][9].equals("true")?"checked":"" %> /></td>
								<s:hidden name="chkAppntStat" />
								<td width="25%"><label class="set" id="appoint.status"
									name="appoint.status" ondblclick="callShowDiv(this);"><%=label.get("appoint.status")%></td>
							</tr>

							<tr>
								<td width="18%"></td>
								<td width="2%"><input type="checkbox"
									name="chkCandScrnStat1" id="chkCandScrnStat1"
									onclick="callCheck();"
									<%=obj[0][3].equals("true")?"checked":"" %> /></td>
								<s:hidden name="chkCandScrnStat" />
								<td width="25%"><label class="set" id="candscreeningstatus"
									name="candscreeningstatus" ondblclick="callShowDiv(this);"><%=label.get("candscreeningstatus")%></td>

								<td width="2%"></td>
								<td width="2%"><input type="checkbox" name="chkConEmp1"
									id="chkConEmp1" onclick="callCheck();"
									<%=obj[0][10].equals("true")?"checked":"" %> /></td>
								<s:hidden name="chkConEmp" />
								<td width="25%"><label class="set" id="convertemp"
									name="convertemp" ondblclick="callShowDiv(this);"><%=label.get("convertemp")%></td>
							</tr>
							<tr>
								<td width="18%"></td>
								<td width="2%"><input type="checkbox" name="chkMobileNo1"
									id="chkMobileNo1" onclick="callCheck();"
									<%=obj[0][4].equals("true")?"checked":"" %> /></td>
								<s:hidden name="chkMobileNo" />
								<td width="25%"><label class="set" id="contact.no"
									name="contact.no" ondblclick="callShowDiv(this);"><%=label.get("contact.no")%></td>

								<td width="2%"></td>
								<td width="2%"><input type="checkbox" name="chkLastComp1"
									id="chkLastComp1" onclick="callCheck();"
									<%=obj[0][11].equals("true")?"checked":"" %> /></td>
								<s:hidden name="chkLastComp" />
								<td width="25%"><label class="set" id="reqs.lastComp"
									name="reqs.lastComp" ondblclick="callShowDiv(this);"><%=label.get("reqs.lastComp")%></td>


							</tr>
							<tr>
								<td width="18%"></td>
								<td width="2%"><input type="checkbox" name="chkcurrCtc1"
									id="chkcurrCtc1" onclick="callCheck();"
									<%=obj[0][5].equals("true")?"checked":"" %> /></td>
								<s:hidden name="chkcurrCtc" />
								<td width="25%"><label class="set" id="reqs.currentCtc"
									name="reqs.currentCtc" ondblclick="callShowDiv(this);"><%=label.get("reqs.currentCtc")%></td>

								<td width="2%"></td>
								<td width="2%"><input type="checkbox" name="chkCurrLoc1"
									id="chkCurrLoc1" onclick="callCheck();"
									<%=obj[0][12].equals("true")?"checked":"" %> /></td>
								<s:hidden name="chkCurrLoc" />
								<td width="25%"><label class="set" id="reqs.currLoc"
									name="reqs.currLoc" ondblclick="callShowDiv(this);"><%=label.get("reqs.currLoc")%></td>
							</tr>

							<tr>
								<td width="18%"></td>
								<td width="2%"><input type="checkbox" name="chkQualDtl1"
									id="chkQualDtl1" onclick="callCheck();"
									<%=obj[0][6].equals("true")?"checked":"" %> /></td>
								<s:hidden name="chkQualDtl" />
								<td width="25%"><label class="set" id="reqs.quali"
									name="reqs.quali" ondblclick="callShowDiv(this);"><%=label.get("reqs.quali")%></td>

								<td width="2%"></td>
								<td width="2%"><input type="checkbox" name="chkExp1"
									id="chkExp1" onclick="callCheck();"
									<%=obj[0][13].equals("true")?"checked":"" %> /></td>
								<s:hidden name="chkExp" />
								<td width="25%"><label class="set" id="reqs.experience"
									name="reqs.experience" ondblclick="callShowDiv(this);"><%=label.get("reqs.experience")%></td>
							</tr>
							<tr>
								<td width="18%"></td>
								<td width="2%"></td>

								<td width="25%"></td>
							</tr>
						</table>
					</s:if>
					<s:else>
						<table width="98%" border="0" cellpadding="0" cellspacing="0"
							class="formbg">
							<tr>
								<td width="18%"></td>
								<td width="2%">
									<input type="checkbox" name="chkCand1"
									id="chkCand1" disabled="true" checked="true"
									onclick="callCheck();" />
								</td>
								<s:hidden name="chkCand" />
								<td width="25%">
									<label class="set" id="candidatename"
									name="candidatename" ondblclick="callShowDiv(this);"><%=label.get("candidatename")%>
								</td>
								<td width="2%"></td>
								<td width="2%">
									<input type="checkbox" name="chkOffStat1" id="chkOffStat1" onclick="callCheck();" />
								</td>
								<s:hidden name="chkOffStat" />
								<td width="25%"><label class="set" id="offer.status"
									name="offer.status" ondblclick="callShowDiv(this);"><%=label.get("offer.status")%>
								</td>
							</tr>
							<tr>
								<td width="18%"></td>
								<td width="2%"><input type="checkbox" name="chkEmail1"
									id="chkEmail1" onclick="callCheck();" /></td>
								<s:hidden name="chkEmail" />
								<td width="25%"><label class="set" id="email.id"
									name="email.id" ondblclick="callShowDiv(this);"><%=label.get("email.id")%></td>


								<td width="2%"></td>
								<td><input type="checkbox" name="chkAppntStat1"
									id="chkAppntStat1" onclick="callCheck();" /></td>
								<s:hidden name="chkAppntStat" />
								<td width="25%"><label class="set" id="appoint.status"
									name="appoint.status" ondblclick="callShowDiv(this);"><%=label.get("appoint.status")%></td>
							</tr>

							<tr>
								<td width="18%"></td>
								<td width="2%"><input type="checkbox"
									name="chkCandScrnStat1" id="chkCandScrnStat1"
									onclick="callCheck();" /></td>
								<s:hidden name="chkCandScrnStat" />
								<td width="25%"><label class="set" id="candscreeningstatus"
									name="candscreeningstatus" ondblclick="callShowDiv(this);"><%=label.get("candscreeningstatus")%></td>

								<td width="2%"></td>
								<td width="2%"><input type="checkbox" name="chkConEmp1"
									id="chkConEmp1" onclick="callCheck();" /></td>
								<s:hidden name="chkConEmp" />
								<td width="25%"><label class="set" id="convertemp"
									name="convertemp" ondblclick="callShowDiv(this);"><%=label.get("convertemp")%></td>


							</tr>

							<tr>
								<td width="18%"></td>
								<td width="2%"><input type="checkbox" name="chkMobileNo1"
									id="chkMobileNo1" onclick="callCheck();" /></td>
								<s:hidden name="chkMobileNo" />
								<td width="25%"><label class="set" id="contact.no"
									name="contact.no" ondblclick="callShowDiv(this);"><%=label.get("contact.no")%></td>

								<td width="2%"></td>
								<td width="2%"><input type="checkbox" name="chkLastComp1"
									id="chkLastComp1" onclick="callCheck();" /></td>
								<s:hidden name="chkLastComp" />
								<td width="25%"><label class="set" id="reqs.lastComp"
									name="reqs.lastComp" ondblclick="callShowDiv(this);"><%=label.get("reqs.lastComp")%></td>


							</tr>
							<tr>
								<td width="18%"></td>
								<td width="2%"><input type="checkbox" name="chkcurrCtc1"
									id="chkcurrCtc1" onclick="callCheck();" /></td>
								<s:hidden name="chkcurrCtc" />
								<td width="25%"><label class="set" id="reqs.currentCtc"
									name="reqs.currentCtc" ondblclick="callShowDiv(this);"><%=label.get("reqs.currentCtc")%></td>

								<td width="2%"></td>
								<td width="2%"><input type="checkbox" name="chkCurrLoc1"
									id="chkCurrLoc1" onclick="callCheck();" /></td>
								<s:hidden name="chkCurrLoc" />
								<td width="25%"><label class="set" id="reqs.currLoc"
									name="reqs.currLoc" ondblclick="callShowDiv(this);"><%=label.get("reqs.currLoc")%></td>


							</tr>
                
							<tr>
								<td width="18%"></td>
								<td width="2%"><input type="checkbox" name="chkQualDtl1"
									id="chkQualDtl1" onclick="callCheck();" /></td>
								<s:hidden name="chkQualDtl" />
								<td width="25%"><label class="set" id="reqs.quali"
									name="reqs.quali" ondblclick="callShowDiv(this);"><%=label.get("reqs.quali")%></td>

								<td width="2%"></td>
								<td width="2%"><input type="checkbox" name="chkExp1"
									id="chkExp1" onclick="callCheck();" /></td>
								<s:hidden name="chkExp" />
								<td width="25%"><label class="set" id="reqs.experience"
									name="reqs.experience" ondblclick="callShowDiv(this);"><%=label.get("reqs.experience")%></td>


							</tr>

							<tr>

								<td width="18%"></td>
								<td width="2%"></td>
								<td width="25%"></td>

							</tr>
						</table>
					</s:else></div>

					</td>
				</tr>
        <tr>
					<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td nowrap="nowrap"><a href="#" onclick="callReset();">
							<img src="../pages/images/buttonIcons/Refresh.png"
								class="iconImage" align="absmiddle" title="PDF"> Reset </a>&nbsp;&nbsp;
							</td>
							<td width="100%" colspan="6"><%@ include
								file="/pages/common/reportButtonPanel.jsp"%></td>
						</tr>
					</table>
					</td>
				</tr>
				
		<tr>
			<td>
			<div id='bottomButtonTable'></div>
			</td>
		</tr>

	</table>
	<s:hidden name="backFlag" />
</s:form>

<SCRIPT LANGUAGE="JavaScript">
var obj = document.getElementById("topButtonTable").cloneNode(true);
document.getElementById("bottomButtonTable").appendChild(obj);
</SCRIPT>


<script type="text/javascript">
displayTab();
function displayTab(){
 var fieldName =["paraFrm_frmDate","paraFrm_toDate"];
 setTextArray(fieldName,"dd-mm-yyyy");

var flag=document.getElementById('paraFrm_backFlag').value;

 var divID = document.getElementById('paraFrm_divID').value;
 var aID = document.getElementById('paraFrm_aID').value;
if(flag=="true"){
	if(aID=="" || divID==""){
	  callDivLoad('filtOpt', 'filterOption') ;
	
	}else{
	 callDivLoad(aID,divID);
  }
}else{
  callOnLoad();
}

}
//callOnLoad();

genReport();

	function callOnLoad() {
	
	var settingVal=  document.getElementById('paraFrm_searchSetting').value;
	if(settingVal=="-1")
	 { 
	
       //document.getElementById('textAreaId1').style.display='none';   
	 }else{
	
        //document.getElementById('textAreaId1').style.display='';   
	 }
	
		var divID = document.getElementById('paraFrm_divID').value;
		var aID = document.getElementById('paraFrm_aID').value;
		
		if(divID == '') {
			divID = 'filterOption';
		}
		
		if(aID == '') {
			aID = 'filtOpt';
		}
		
		document.getElementById('filterOption').style.display = 'none';
		document.getElementById('sortingOption').style.display = 'none';
		document.getElementById('columnDefinition').style.display = 'none';		
		document.getElementById(divID).style.display = '';
		document.getElementById('filtOpt').className = '';
		document.getElementById('sortOpt').className = '';
		document.getElementById('colDef').className = '';
		document.getElementById(aID).className = 'on';
	}
	
	function callDivLoad(aID, divID) {
		var val=document.getElementById('paraFrm_radioFlag').value;
		var pf=document.getElementById('paraFrm_pageFlag').value;
		document.getElementById('mpPositionId').style.display ='';
		document.getElementById('mpCandId').style.display ='';
		document.getElementById('mpreqsnId').style.display ='';
		document.getElementById('filterOption').style.display = 'none';
		document.getElementById('sortingOption').style.display = 'none';
		document.getElementById('columnDefinition').style.display = 'none';			
		document.getElementById('filtOpt').className = '';
		document.getElementById('sortOpt').className = '';
		document.getElementById('colDef').className = '';
		document.getElementById('advancedFilters').style.display = 'none';			
		document.getElementById('advFilt').className = '';
		document.getElementById(aID).className = 'on';
		document.getElementById(divID).style.display = '';
		document.getElementById('paraFrm_divID').value =  divID;
		document.getElementById('paraFrm_aID').value =  aID;
		
		
		if(pf=="false"){
		  if(document.getElementById('paraFrm_chkEmail').value=="Y"){
	       	       document.getElementById('chkEmail1').checked=true;
	       	     }
	       	     if(document.getElementById('paraFrm_chkCandScrnStat').value=="Y"){
	       	       document.getElementById('chkCandScrnStat1').checked=true;
	       	     }
	       	   
	         	if(document.getElementById('paraFrm_chkOffStat').value=="Y"){
	       	       document.getElementById('chkOffStat1').checked=true;
	       	     }
	       	   if(document.getElementById('paraFrm_chkAppntStat').value=="Y"){
	       	       document.getElementById('chkAppntStat1').checked=true;
	       	     }  
	       	     
		       if(document.getElementById('paraFrm_chkConEmp').value=="Y"){
	       	       document.getElementById('chkConEmp1').checked=true;
	       	     }  
		
		     if(document.getElementById('paraFrm_chkMobileNo').value=="Y"){
	       	       document.getElementById('chkMobileNo1').checked=true;
	       	     }  
	       	     
	       	 if(document.getElementById('paraFrm_chkLastComp').value=="Y"){
	       	       document.getElementById('chkLastComp1').checked=true;
	       	    }  
	       	 if(document.getElementById('paraFrm_chkcurrCtc').value=="Y"){
	       	       document.getElementById('chkcurrCtc1').checked=true;
	       	    }  
	       	 if(document.getElementById('paraFrm_chkLastComp').value=="Y"){
	       	       document.getElementById('chkLastComp1').checked=true;
	       	    }  
	       	 if(document.getElementById('paraFrm_chkCurrLoc').value=="Y"){
	       	       document.getElementById('chkCurrLoc1').checked=true;
	       	    }  
	       	 if(document.getElementById('paraFrm_chkQualDtl').value=="Y"){
	       	       document.getElementById('chkQualDtl1').checked=true;
	       	    }  
	       	 if(document.getElementById('paraFrm_chkExp').value=="Y"){
	       	       document.getElementById('chkExp1').checked=true;
	       	    }  

		}
		
		
		
		
		
		if(val=="false"){
			document.getElementById('asc').checked=true;
			document.getElementById('ascc').checked=true;
			document.getElementById('paraFrm_ascc1').value="A";
			document.getElementById('asce').checked=true;
			document.getElementById('paraFrm_asce1').value="A";
			document.getElementById('paraFrm_asc1').value="A";
		}else if(val=="true"){
				if( document.getElementById('paraFrm_asc1').value=="A"){
				    document.getElementById('asc').checked=true;
				}else{
					 document.getElementById('asc').checked=false;
				} 
				
				if(document.getElementById('paraFrm_desc1').value=="D"){
					   document.getElementById('desc').checked=true;
				
				}else{
						 document.getElementById('desc').checked=false;
				}
				
				//Second radio button
				if( document.getElementById('paraFrm_asce1').value=="A"){
				    document.getElementById('asce').checked=true;
				}else{
					 document.getElementById('asce').checked=false;
				} 
				
				if(document.getElementById('paraFrm_desce1').value=="D"){
					   document.getElementById('desce').checked=true;
				
				}else{
						 document.getElementById('desce').checked=false;
				}
				
				
				
				if( document.getElementById('paraFrm_ascc1').value=="A"){
				    document.getElementById('ascc').checked=true;
				}else{
					 document.getElementById('ascc').checked=false;
				} 
				
				if(document.getElementById('paraFrm_descc1').value=="D"){
					   document.getElementById('descc').checked=true;
				
				}else{
						 document.getElementById('descc').checked=false;
				}
		
		
		
		}
		
		
	}
	function callAscc(){
	document.getElementById('paraFrm_radioFlag').value="true";
	      document.getElementById('descc').checked=false;
		  document.getElementById('paraFrm_descc1').value="";
		  document.getElementById('paraFrm_ascc1').value="A";
		  document.getElementById('paraFrm_radioFlag3').value="false";
			   
	}
	function callDescc(){
	document.getElementById('paraFrm_radioFlag').value="true";
	      document.getElementById('ascc').checked=false;
		  document.getElementById('paraFrm_ascc1').value="";
		  document.getElementById('paraFrm_descc1').value="D";
		  document.getElementById('paraFrm_radio3').value="false";
			  
	}

function callAsc(){
  document.getElementById('paraFrm_radioFlag').value="true";
	      document.getElementById('desc').checked=false;
		  document.getElementById('paraFrm_desc1').value="";
		  document.getElementById('paraFrm_asc1').value="A";
		  document.getElementById('paraFrm_radioFlag1').value="false";
			   
	}
	function callDesc(){
	 document.getElementById('paraFrm_radioFlag').value="true";
	      document.getElementById('asc').checked=false;
		  document.getElementById('paraFrm_asc1').value="";
		  document.getElementById('paraFrm_desc1').value="D";
		   document.getElementById('paraFrm_radio1').value="false";
			  
	}
function callAsce(){
document.getElementById('paraFrm_radioFlag').value="true";
	      document.getElementById('desce').checked=false;
		  document.getElementById('paraFrm_desce1').value="";
		  document.getElementById('paraFrm_asce1').value="A";
		  document.getElementById('paraFrm_radioFlag2').value="false";
			   
	}
	function callDesce(){
		  document.getElementById('paraFrm_radioFlag').value="true";	
	      document.getElementById('asce').checked=false;
		  document.getElementById('paraFrm_asce1').value="";
		  document.getElementById('paraFrm_desce1').value="D";
		  document.getElementById('paraFrm_radio2').value="false";
			  
	}


function callReportCandidateStatus(){ 	
	

var frmDate=document.getElementById("paraFrm_frmDate").value;
var toDate=document.getElementById("paraFrm_toDate").value;
var sort1=document.getElementById("paraFrm_sortBy").value;
var sort2=document.getElementById("paraFrm_firstBy").value;
var sort3=document.getElementById("paraFrm_secondBy").value;
 	
 	
 	
      		if(trim(document.getElementById("paraFrm_frmDate").value)!="dd-mm-yyyy"){
      		   callDivLoad('filtOpt', 'filterOption');
      			if(document.getElementById("paraFrm_reqsDateCombo").value==""){
	      			alert("Please select requisition date option");
	      			return false;
      			}
      		}
      	
    
 	 
         if((frmDate!="dd-mm-yyyy")) {
         	callDivLoad('filtOpt', 'filterOption');
    		if(!validateDate('paraFrm_frmDate','reqs.date')){
		             return false;
		     }
		       document.getElementById('paraFrm_frmDate').focus();
			     
		   }
	 if(document.getElementById("paraFrm_reqsDateCombo").value=="F "){
		   callDivLoad('filtOpt', 'filterOption') ;	
		 var toDate=trim(document.getElementById("paraFrm_toDate").value); 	
		  if(toDate!="dd-mm-yyyy"){	
			  if(!validateDate('paraFrm_toDate','todate')){
		             return false;	 
		               document.getElementById('paraFrm_toDate').focus();
		             	}	
	        }     	
	             	
	  	 if(!dateDifferenceEqual(frmDate,toDate,"paraFrm_toDate",'reqs.date','todate'))return false;
  	 } 
  	 
  	  if(document.getElementById("paraFrm_advIntRnd").value!=""){
  	 callDivLoad('advFilt', 'advancedFilters');
  	    if(trim(document.getElementById("paraFrm_intRndVal").value)==""){
  	 	alert("Please enter the"+document.getElementById('intvround').innerHTML.toLowerCase());
  	 	 document.getElementById('paraFrm_intRndVal').IntRndValfocus();
  	 	return false;
  	 	}	
   	  }
 if(sort1!="1" && sort2!="1"){
  	 	if(sort1==sort2){
  	 			callDivLoad('sortOpt', 'sortingOption');
  	 		 	alert("Duplicate Sorting Option.");
  	 		 	return false;
  	 	}	
  	 
  	 }
  	 
  	 if(sort1!="1" && sort3!="1"){
  	 	if(sort1==sort3){
  	 			callDivLoad('sortOpt', 'sortingOption');
  	 		 	alert("Duplicate Sorting Option.");
  	 		 	return false;
  	 	}	
  	 
  	 }
  	 
  	 
  	 if(sort2!="1" && sort3!="1"){
  	 	if(sort2==sort3){
  	 		callDivLoad('sortOpt', 'sortingOption');
  	 		 	alert("Duplicate Sorting Option.");
  	 		 	return false;
  	 	}	
  	 
  	 }
  	
  	
  	
  /*	if((!(document.getElementById("onScrn").checked)) && (!(document.getElementById("genRep").checked))){
  			alert("Please select view on screen or report type");
  			return false;
  	
  	}*/
  	
  	if(!document.getElementById("genRep").checked)
  	{
  	alert("Please select report type");
  			return false;
  	
  	}
  	 
  	 if(document.getElementById("genRep").checked){
  	 if(document.getElementById("paraFrm_flag").value==""){
	
			alert("Please select report type");
			return false;
	}
}	
	
	/*if(document.getElementById("onScrn").checked){
			document.getElementById('paraFrm').action='CandidateStatusReport_showReqsnList.action';
			document.getElementById('paraFrm').submit();
     }else{
   			
     		document.getElementById('paraFrm').action='CandidateStatusReport_generateReport.action';
			document.getElementById('paraFrm').submit();
     }*/
	
	document.getElementById('paraFrm').action='CandidateStatusReport_generateReport.action';
			document.getElementById('paraFrm').submit();
	
	
		
}

enableToDate();	

function enableToDate(){
		try{
		var positioningCriteria = document.getElementById('paraFrm_reqsDateCombo').value;
		
		if(positioningCriteria == 'F '){
			document.getElementById('toDateText').style.display = '';
		}
		else{
			document.getElementById('toDateText').style.display ='none';
		}
		}catch(e){
		alert(e);
		}
	}
	
function callReqsn(){

//document.getElementById('mpCandId').style.display ='none';
//document.getElementById('mpPositionId').style.display ='none';
document.getElementById('paraFrm_rankName').value="";
document.getElementById('paraFrm_rankId').value="";
document.getElementById('paraFrm_candidateName').value="";
document.getElementById('paraFrm_candidateCode').value="";
//textAreaId
//document.getElementById('textAreaId').style.display ='none';
callsF9(500,325,'CandidateStatusReport_f9actionReqName.action');
}	


function callCand(){

//document.getElementById('mpreqsnId').style.display ='none';
//document.getElementById('mpPositionId').style.display ='none';
//document.getElementById('reqsButton').style.display ='none';
document.getElementById('paraFrm_rankName').value="";
document.getElementById('paraFrm_rankId').value="";
document.getElementById('paraFrm_reqname').value="";
document.getElementById('paraFrm_reqCode').value="";
callsF9(500,325,'CandidateStatusReport_f9actionCandidate.action');
}		

function callPosition(){
//document.getElementById('mpCandId').style.display ='none';
//document.getElementById('mpreqsnId').style.display ='none';
document.getElementById('paraFrm_candidateName').value="";
document.getElementById('paraFrm_candidateCode').value="";
document.getElementById('paraFrm_reqname').value="";
document.getElementById('paraFrm_reqCode').value="";

callsF9(500,325,'CandidateStatusReport_f9actionPosition.action');
}	

function viewOnScrn(){

document.getElementById('genRep').checked=false;
document.getElementById('paraFrm_flag').value="";
document.getElementById('showDropDown').style.display ='none';




}


function genReport(){
var flg=document.getElementById('paraFrm_reportFlag').value;
if(flg=="true"){
document.getElementById('genRep').checked=true;
document.getElementById('showDropDown').style.display ='';

}else{
//document.getElementById('onScrn').checked=true;
document.getElementById('showDropDown').style.display ='none';
}



}


function generateDropDown(){
//document.getElementById('onScrn').checked=false;
document.getElementById('showDropDown').style.display ='';

}

function callCheck(){
var counter=0;
if(!document.getElementById('chkEmail1').checked){
		   
		     document.getElementById('paraFrm_chkEmail').value="N";
		}else{
		     document.getElementById('paraFrm_chkEmail').value="Y";
		      counter++;
		}
		
		
		if(!document.getElementById('chkMobileNo1').checked){
		   
		     document.getElementById('paraFrm_chkMobileNo').value="N";
		}else{
		     document.getElementById('paraFrm_chkMobileNo').value="Y";
		      counter++;
		}
	
		if(!document.getElementById('chkCandScrnStat1').checked){
		   
		     document.getElementById('paraFrm_chkCandScrnStat').value="N";
		}else{
		     document.getElementById('paraFrm_chkCandScrnStat').value="Y";
		      counter++;
		}
		
		 
		
		 
		
	
		if(!document.getElementById('chkOffStat1').checked){
		     document.getElementById('paraFrm_chkOffStat').value="N";
		    
		}else{
		     document.getElementById('paraFrm_chkOffStat').value="Y";
		      counter++;
		   
		}
		
		if(!document.getElementById('chkAppntStat1').checked){
		   
		     document.getElementById('paraFrm_chkAppntStat').value="N";
		   
		}else{
		     document.getElementById('paraFrm_chkAppntStat').value="Y";
		 	 counter++;
		}
		
		if(!document.getElementById('chkConEmp1').checked){
		   
		     document.getElementById('paraFrm_chkConEmp').value="N";
		    
		}else{
		     document.getElementById('paraFrm_chkConEmp').value="Y";
		      counter++;
		    
		}
		
		if(!document.getElementById('chkLastComp1').checked){
		   
		     document.getElementById('paraFrm_chkLastComp').value="N";
		}else{
		     document.getElementById('paraFrm_chkLastComp').value="Y";
		      counter++;
		}
		
		if(!document.getElementById('chkcurrCtc1').checked){
		   
		     document.getElementById('paraFrm_chkcurrCtc').value="N";
		    
		}else{
		     document.getElementById('paraFrm_chkcurrCtc').value="Y";
		      counter++;
		    
		}
		
		if(!document.getElementById('chkCurrLoc1').checked){
		   
		     document.getElementById('paraFrm_chkCurrLoc').value="N";
		    
		}else{
		     document.getElementById('paraFrm_chkCurrLoc').value="Y";
		      counter++;
		    
		}
		
		if(!document.getElementById('chkQualDtl1').checked){
		   
		     document.getElementById('paraFrm_chkQualDtl').value="N";
		    
		}else{
		     document.getElementById('paraFrm_chkQualDtl').value="Y";
		      counter++;
		    
		}
		
		if(!document.getElementById('chkExp1').checked){
		   
		     document.getElementById('paraFrm_chkExp').value="N";
		    
		}else{
		     document.getElementById('paraFrm_chkExp').value="Y";
		      counter++;
		    
		}
		
		

}	

/*
following function is called when Select Requisition button is clicked to check the validation 
*/
 function callSelectReq(status)
 {
 	try{
   var fDate=trim(document.getElementById("paraFrm_frmDate").value);
   var tDate=trim(document.getElementById("paraFrm_toDate").value);
   var option=trim(document.getElementById("paraFrm_reqsDateCombo").value);
 
 	if(fDate!="dd-mm-yyyy"){
 		if(option==""){
 		   callDivLoad('filtOpt', 'filterOption');
 		   alert("Please select requisition date option");
	        return false;
 		}
 	}
 	
 	if(fDate!="dd-mm-yyyy"){
 		if(!validateDate('paraFrm_frmDate','reqs.date')){
		             return false;
		     }
 	}
 	
 	if(tDate!=""){
 		if(!validateDate('paraFrm_frmDate','reqs.date')){
 		return false;
 		}
 	
 	
 	}
 	
 
 
 if(trim(document.getElementById("paraFrm_frmDate").value)!="dd-mm-yyyy"){
      		  
      			if(document.getElementById("paraFrm_reqsDateCombo").value==""){
	      			alert("Please select requisition date option");
	      			return false;
      			}
      		}
 
 
 
  if(status=='edit'){
      document.getElementById('paraFrm_editReqFlag').value="true"; 
  }else{
	   document.getElementById('paraFrm_editReqFlag').value="false"; 
	   document.getElementById('paraFrm_selectedReqName').value="";
	   document.getElementById('paraFrm_selectedReq').value=""; 
  } 
	  document.getElementById('mpCandId').style.display ='none';
	  document.getElementById('mpreqsnId').style.display ='none';
	  document.getElementById('paraFrm').target="wind";
	  var wind = window.open('','wind','width=600,height=400,scrollbars=yes,resizable=yes,menubar=no,top=200,left=150');	 
	  document.getElementById('paraFrm').action="CandidateStatusReport_displayReq.action";
	  document.getElementById('paraFrm').submit();
	  document.getElementById('paraFrm').target="";
	  
	 }catch(e){alert("Error-"+e);} 
 }
 
 
 function callVal(){
 
  
	var repCode=document.getElementById('paraFrm_searchSetting').value;
	if(repCode!="-1"){

	document.getElementById('paraFrm_appRepCode').value=repCode;
	document.getElementById('paraFrm').action='CandidateStatusReport_getFilterDetails.action';
	document.getElementById('paraFrm').submit();
	}
		
	}
	
function callSave(){

try{

   
var sett=trim(document.getElementById('paraFrm_saveSetting').value);
var frmDate=trim(document.getElementById("paraFrm_frmDate").value);
var sort1=document.getElementById("paraFrm_sortBy").value;
var sort2=document.getElementById("paraFrm_firstBy").value;
var sort3=document.getElementById("paraFrm_secondBy").value;
 	
 	
 	
      		if(trim(document.getElementById("paraFrm_frmDate").value)!="dd-mm-yyyy"){
      			if(document.getElementById("paraFrm_reqsDateCombo").value==""){
	      			alert("Please select requisition date option");
	      			return false;
      			}
      		}
      	
    
 	 
         if((frmDate!="dd-mm-yyyy")) {
    		if(!validateDate('paraFrm_frmDate','reqs.date')){
		             return false;
		     }
		       document.getElementById('paraFrm_frmDate').focus();
			     
		   }
	 if(document.getElementById("paraFrm_reqsDateCombo").value=="F "){
		 var toDate=trim(document.getElementById("paraFrm_toDate").value); 	
		  if(toDate!="dd-mm-yyyy"){	
			  if(!validateDate('paraFrm_toDate','todate')){
		             return false;	 
		               document.getElementById('paraFrm_toDate').focus();
		             	}	
	        }     	
	             	
	  	 if(!dateDifferenceEqual(frmDate,toDate,"paraFrm_toDate",'reqs.date','todate'))return false;
  	 } 
  	 
  	  if(document.getElementById("paraFrm_advIntRnd").value!=""){
  	    if(trim(document.getElementById("paraFrm_intRndVal").value)==""){
  	 	alert("Please enter the"+document.getElementById('intvround').innerHTML.toLowerCase());
  	 	 document.getElementById('paraFrm_intRndVal').IntRndValfocus();
  	 	return false;
  	 	}	
   	  }
   	 
  	  if(trim(document.getElementById("paraFrm_saveSetting").value)==""){
  	 	alert("Please enter the  "+document.getElementById('setting.name').innerHTML.toLowerCase());
  	 	document.getElementById('paraFrm_saveSetting').focus();
  	 	return false;
  	 
  	 }
  	
  	 
  		 document.getElementById('paraFrm').action='CandidateStatusReport_saveSettings.action';
		document.getElementById('paraFrm').submit();
}catch(e){
 alert(e);
}

}	

	function callReport(type){	
        try{
	        if( validateReqDate()){
		    document.getElementById('paraFrm_report').value=type;
			callReportCommon(type);
			}
		  }
		 catch (e){
	 	   alert(e);
	 	 }	
	 }
	 
	 function mailReportFun(type){
	    if( validateReqDate()){
		document.getElementById('paraFrm_report').value=type;
		document.getElementById('paraFrm').action='CandidateStatusReport_mailReport.action';
		document.getElementById('paraFrm').submit();
		   }
		}
		
		function callReset(){
	  	document.getElementById('paraFrm').target='_self';
		document.getElementById('paraFrm').action='CandidateStatusReport_reset.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target="_self";
	}
	
   function validateReqDate(){
       var frmDate=document.getElementById("paraFrm_frmDate").value;
       var toDate=document.getElementById("paraFrm_toDate").value;
	
	   if(trim(document.getElementById("paraFrm_frmDate").value)!="dd-mm-yyyy"){
      			if(document.getElementById("paraFrm_reqsDateCombo").value==""){
	      			alert("Please select Requisition Date option");
	      			return false;
      			}
      		}
      	
      	if(document.getElementById("paraFrm_reqsDateCombo").value!=""){
      	   if(trim(document.getElementById("paraFrm_frmDate").value)=="dd-mm-yyyy"){
      	   alert("Please enter/select Requisition Date ");
      	   document.getElementById("paraFrm_frmDate").focus();
      	   return false;
      	   }
      	}
      	
         if((frmDate!="dd-mm-yyyy")) {
    		if(!validateDate('paraFrm_frmDate','reqs.date')){
		             return false;
		     }
		       document.getElementById('paraFrm_frmDate').focus();
		   }
		   
	     if(document.getElementById("paraFrm_reqsDateCombo").value=="F "){
			 var toDate=trim(document.getElementById("paraFrm_toDate").value); 	
			  if(toDate!="dd-mm-yyyy"){	
			 	if(!validateDate('paraFrm_toDate','todate')){
		             return false;	 
		               document.getElementById('paraFrm_toDate').focus();
		             }	
	          }else if(document.getElementById('paraFrm_toDate').value=="dd-mm-yyyy"){
      			  alert("Please enter/select Requisition To Date");
      			  document.getElementById('paraFrm_toDate').focus();
	      			return false;
      			}    	
	  	 if(!dateDifferenceEqual(frmDate,toDate,"paraFrm_toDate",'reqs.date','todate'))return false;
  	     } 
     
     return true;
     }
	
</script>
