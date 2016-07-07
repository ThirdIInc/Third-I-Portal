<%@ taglib prefix="s" uri="/struts-tags"%>

<%@include file="/pages/common/labelManagement.jsp"%>



<s:form action="CandDataBank" validate="true" id="paraFrm"
	 theme="simple">
	<s:hidden name="listFlag" />
	<s:hidden name="expFlag" />
	<s:hidden name="searchFlag" />
	<s:hidden name="qualiFlag" />
	<s:hidden name="skillFlag" />
	<s:hidden name="pageFlag" />
	<s:hidden name="refFlag" />
	<s:hidden name="domFlag" />
	<s:hidden name="radioEmp" />
	<s:hidden name="viewEditFlag" />
	<table width="100%" align="right" class="formbg" height="400">
		<!--
        <tr>
          <td colspan="3" valign="bottom" class="txt">&nbsp;</td>
        </tr>
        <tr>
          <td colspan="3" valign="bottom" background="../pages/common/css/default/images/lines.gif" class="txt"><img src="../pages/common/css/default/images/lines.gif" width="16" height="1" /></td>
        </tr>
        <tr>
          <td colspan="3" valign="bottom" class="txt"><img src="../pages/common/css/default/images/space.gif" width="5" height="5" /></td>
        </tr>
          -->
		<tr>
			<td colspan="3">
			<table width="100%" cellpadding="0" cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/common/css/default/images/review_shared.gif"
						width="25" height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Candidate
					Databank </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>



		<!--
        <tr>
          <td width="4%" valign="bottom" class="txt"><strong class="formhead"><img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" /></strong></td>
          <td width="93%" class="txt"><strong class="formhead">Candidate DataBank </strong></td>
          <td width="3%" valign="top" class="txt"><div align="right"><img src="../pages/common/css/default/images/help.gif" width="16" height="16" /></div></td>
        </tr>
        -->
		<!--<tr>
          <td height="5" colspan="3"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
        </tr>
         -->
		<s:hidden name="candCode" />
		<s:hidden name="candName" />
		<s:hidden name="experience" />
		<s:hidden name="postingDate" />
		<s:hidden name="shortStatus" />
		<s:hidden name="cityCode1" />
		<s:hidden name="stateCode1" />
		<s:hidden name="countryCode1" />
		<s:hidden name="updateFirst" />
		<s:hidden name="updateSecond" />
		<s:hidden name="cancelFirst" />
		<s:hidden name="dataPath" />
		<s:hidden name="pathPhoto" />
		<s:hidden name="show" />
		<s:hidden name="myPage" />
		<s:hidden name="hiddenCode" />

		<!--
         <s:textfield name="dob"/>
         -->
		<!--
         <tr>
          <td colspan="3"><img src="../pages/common/css/default/images/space.gif" width="5" height="4" /></td>
        </tr>
        
        -->
		<s:if test="listFlag">
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td colspan="2">

						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td colspan="2" nowrap="nowrap"><s:if test="searchFlag">
									<strong class="text_head">Applied Filters</strong>
								</s:if><s:else>
									<label class="set" name="searchApply.filter"
										id="searchApply.filter" ondblclick="callShowDiv(this);"><%=label.get("searchApply.filter")%>
								</s:else></td>

								<td id="showFilter" align="right" colspan="2"><input
									type="button" value="Show Filter" class="token"
									onclick="return callShowFilter();"></td>

								<td id="hideFilter" align="right"><input type="button"
									value="Hide Filter" class="token"
									onclick="return callHideFilter();"></td>
							</tr>
						</table>
						</td>
					</tr>

					<tr>
						<td colspan="2">

						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="showFilterToApp">
							<tr>
								<td width="20%" height="22" class="formtext"><label
									class="set" name="cand.name" id="cand.name"
									ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label>
								:</td>
								<td width="25%" height="22" nowrap="nowrap"><s:textfield
									size="25" name="searchCandName" readonly="true" maxlength="30" />
								<img src="../pages/images/recruitment/search2.gif" width="16"
									height="15" class="iconImage"
									onclick="javascript:callsF9(500,325,'CandDataBank_f9searchCand.action');">
								<s:hidden name="searchCandId" /></td>
								<td width="5%"></td>

								<td width="20%" height="22" class="formtext"><label
									name="experience" id="exper5" ondblclick="callShowDiv(this);"><%=label.get("experience")%></label>
								:</td>
								<td width="30%" height="22"><s:textfield name="expYear"
									size="2" maxlength="2" onkeypress="return numbersOnly();" />Year
								<s:textfield name="expMonth" size="2" maxlength="2"
									onkeypress="return numbersOnly();" />Months</td>

							</tr>


							<tr>
								<td width="20%" height="22" class="formtext"><label
									class="set" name="postingFrmDt" id="postingFrmDt"
									ondblclick="callShowDiv(this);"><%=label.get("postingFrmDt")%></label>
								:</td>
								<td width="25%" height="22" nowrap="nowrap"><s:textfield
									size="25" name="fromDate" readonly="false" maxlength="10"
									onkeypress="return numbersWithHiphen();"
									onfocus="clearText('paraFrm_fromDate','dd-mm-yyyy')"
									onblur="setText('paraFrm_fromDate','dd-mm-yyyy');" /> <s:a
									href="javascript:NewCal('paraFrm_fromDate','DDMMYYYY');">
									<img src="../pages/common/css/default/images/Date.gif"
										width="16" height="16" border="0" />
								</s:a></td>
								<td width="5%"></td>

								<td width="20%" height="22" class="formtext"><label
									name="postingToDt" id="postingToDt"
									ondblclick="callShowDiv(this);"><%=label.get("postingToDt")%></label>
								:</td>
								<td width="30%" height="22" nowrap="nowrap"><s:textfield
									name="toDate" size="25" maxlength="10"
									onkeypress="return numbersWithHiphen();"
									onfocus="clearText('paraFrm_toDate','dd-mm-yyyy')"
									onblur="setText('paraFrm_toDate','dd-mm-yyyy');" /> <s:a
									href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
									<img src="../pages/common/css/default/images/Date.gif"
										width="16" height="16" border="0" />
								</s:a></td>

							</tr>

							<tr>
								<td width="20%" height="22" class="formtext"><label
									class="set" name="stat" id="stat"
									ondblclick="callShowDiv(this);"><%=label.get("stat")%></label>
								:</td>
								<td width="25%" height="22"><s:select name="candStatus"
									cssStyle="width:152"
									list="#{'':'--Select--','S':'ShortListed','N':'New','R':'Rejected','E':'Employee'}" />

								</td>

								<td width="5%"></td>

								<td width="20%" height="22" class="formtext"><label
									class="set" name="resSrc" id="resSrc1"
									ondblclick="callShowDiv(this);"><%=label.get("resSrc")%></label>
								:</td>
								<td width="30%" height="22"><s:select name="resumeSrc"
									cssStyle="width:152"
									list="#{'':'--Select--','C':'Consultant','O':'Online','D':'Direct','E':'Employee Refferal'}" />

								</td>



							</tr>

							<tr>

								<td align="center" colspan="5"><input type="button"
									class="token" theme="simple" value="Apply Filters"
									onclick="return applyFilters();" />&nbsp; <input type="button"
									class="reset" theme="simple" onclick="return callReset();"
									value="Reset" /></td>

							</tr>
						</table>
						</td>
					</tr>

					<tr>
						<td colspan="2">
						<% 
		     						    String [] dispArr = (String [])request.getAttribute("dispArr"); 
		     						  if(dispArr!=null && dispArr.length >0)
		     						  {
		     							 
		     						      int k =0;
		     						      int count =0;
		     						      if(dispArr.length % 2==0)
		     						      {
		     						    	   k =dispArr.length/2;
		     						      }else
		     						      {
		     						    	 k =(dispArr.length/2)+1; 
		     						      } 
		     						  %>

						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="appliedFilterValue">

							<% for(int m=0;m<k;m++) 
									    	  {%>
							<tr>
								<% if(count<dispArr.length){ %>
								<td width="20%" height="22" class="formtext"><%=dispArr[count]%>
								</td>
								<% count++;%>
								<%} %>


								<% if(count<dispArr.length){ %>
								<td width="20%" height="22" class="formtext"><%=dispArr[count]%>
								</td>
								<% count++;%>
								<%} %>
							</tr>
							<% }
		     						  } // end of iff
									      %>



							<tr>

								<td align="center" colspan="5">&nbsp; <input type="button"
									class="reset" theme="simple" onclick="return callClear();"
									value="Clear Filter" /> &nbsp; <s:submit cssClass="token"
									action="CandDataBank_searchCandDet" theme="simple"
									value="Edit Filter " onclick="return callEditFilter();" /></td>
							</tr>
						</table>
						</td>
					</tr>


				</table>
				</td>
			</tr>

			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td width="78%"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						<td width="22%"><s:if test="listFlag"></s:if><s:else>
							<div align="right"><span class="style3"><font
								color="red">*</font></span>Indicates Required</div>
						</s:else></td>
					</tr>
				</table>
				<label></label></td>
			</tr>

			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td>
						<table width="99%" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td height="27" class="text_head"><strong>Candidate
								List :</strong></td>


								<%
					int totalPage = (Integer) request.getAttribute("totalPage");
					int pageNo = (Integer) request.getAttribute("PageNo");
					%>
								<s:if test="noData"></s:if>
								<s:else>
									<td align="right"><b>Page:</b> <input type="hidden"
										name="totalPage" id="totalPage" value="<%=totalPage%>">
									<a href="#" onclick="callPage('1','F');"> <img
										title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPage('P','P');"> <img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text" name="pageNoField"
										id="pageNoField" theme="simple" size="3" value="<%= pageNo%>"
										onkeypress="callPageText(event);return numbersOnly()"
										maxlength="4" /> of <%=totalPage%> <a href="#"
										onclick="callPage('N','N')"> <img title="Next Page"
										src="../pages/common/img/next.gif" class="iconImage" /> </a>&nbsp;
									<a href="#" onclick="callPage('<%=totalPage%>','L');"> <img
										title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
								</s:else>
							</tr>



						</table>
						</td>
					</tr>
					<tr>
						<td colspan="1" class="formtext">
						<table width="100%" border="0" cellpadding="1" cellspacing="1"
							class="sortable">
							<tr>
								<td width="4%" valign="top" class="formth" nowrap="nowrap"><b><label
									class="set" name="sr" id="sr1" ondblclick="callShowDiv(this);"><%=label.get("sr")%></label></b></td>
								<td width="20%" valign="top" class="formth"><b><label
									class="set" name="cand.name" id="cand.name"
									ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label></b></td>
								<td width="20%" valign="top" class="formth"><b><label
									class="set" name="revexperience" id="revexperience"
									ondblclick="callShowDiv(this);"><%=label.get("revexperience")%></label></b></td>
								<td width="15%" valign="top" class="formth" nowrap="nowrap"><b><label
									class="set" name="postdt" id="postdt"
									ondblclick="callShowDiv(this);"><%=label.get("postdt")%></label></b></td>
								<td width="11%" valign="top" class="formth"><b><label
									class="set" name="stat" id="stat"
									ondblclick="callShowDiv(this);"><%=label.get("stat")%></label></b></td>
								<td width="15%" valign="top" class="formth"><b><label
									class="set" name="resSrc" id="resSrc"
									ondblclick="callShowDiv(this);"><%=label.get("resSrc")%></label></b></td>
								<td width="15%" valign="top" class="formth"><b><label
									class="set" name="referedByEmp" id="referedByEmp"
									ondblclick="callShowDiv(this);"><%=label.get("referedByEmp")%></label></b></td>	
							</tr>

							<s:if test="noData">
								<tr>
									<td width="100%" colspan="12" align="center"><font
										color="red">There is no data to display</font></td>
								</tr>
							</s:if>
							<%
							int count = 0;
							%>
							<%!int d = 0;%>
							<%
							int cnt= pageNo*20-20;	
							int k = 0;
							%>

							<s:iterator value="loadList">
								<tr <%if(count%2==0){
									%> class="tableCell1" <%}else{%>
									class="tableCell2" <%	}count++; %>
									onmouseover="javascript:newRowColor(this);"
									onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
									ondblclick="javascript:callForEdit('<s:property value="hiddenCandCode" />');">
									<td width="4%" class="sortableTD" align="center"><%=++cnt%>
									<%++k;%>
									</td>

									<!--<s:property value ="srNo"/>
									-->
									<td width="20%" class="sortableTD"><s:hidden
										name="hiddenCandCode" /> <s:property value="firstName" />&nbsp;</td>
									<td width="10%" class="sortableTD"><s:property
										value="experience" />&nbsp;</td>
									<td width="15%" class="sortableTD" align="center"><s:property
										value="postingDate" />&nbsp;</td>
									<td width="11%" class="sortableTD"><s:property
										value="shortStatus" />&nbsp;</td>
									<td width="15%" class="sortableTD">
									<s:property	value="resSource" />&nbsp;</td>
									<td width="25%" class="sortableTD">
									<s:property	value="referedByEmp" />&nbsp;</td>
								</tr>

							</s:iterator>

						</table>
						</td>
					</tr>

				</table>

				</td>
			</tr>
			<tr>
				<td align="left"><jsp:include
					page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				<td align="right"><s:if test="mode">
					<b>Total no. of records :&nbsp;<s:property value="totRecord" /></b>
				</s:if></td>
			</tr>
		</s:if>
		<s:else>
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td width="78%"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						<td width="22%"><s:if test="listFlag"></s:if><s:else>
							<div align="right"><span class="style3"><font
								color="red">*</font></span>Indicates Required</div>
						</s:else></td>
					</tr>
				</table>
				<label></label></td>
			</tr>


			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td>
						<table width="98%" border="0" align="center" cellpadding="0"
							cellspacing="2">
							<!--
		                 	 <tr>
		                 	   <td colspan="5" class="formhead"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
		                  	</tr>
		                 	 -->
							<tr>
								<strong class="text_head">Candidate Details:</strong>
								<td colspan="5" class="formhead"><img
									src="../pages/common/css/default/images/space.gif" width="5"
									height="7" /></td>
							</tr>


							<tr>
								<td class="formtext" height="22"><label class="set"
									name="title" id="title" ondblclick="callShowDiv(this);"><%=label.get("title")%></label>
								:</td>
								<td width="27%" height="22"><s:textfield name="titleName"
									size="10" maxlength="30" readonly="readonly" /><s:hidden
									name="titleCode" /> <img
									src="../pages/images/recruitment/search2.gif" class="iconImage"
									onclick="javascript:callsF9(500,325,'CandDataBank_f9TitleAction.action')"
									width="16" height="15"></td>
								<td height="22" class="formtext" valign="top"><label
									name="photograph" id="photograph"
									ondblclick="callShowDiv(this);"><%=label.get("photograph")%></label>
								:</td>
								<td height="22" rowspan="6">
								<table width="5" height="100" border="0" cellpadding="0"
									cellspacing="0" class="border">
									<tr>
										<td bgcolor="#FFFFFF" align="center" style="padding: 3px;">
										<%
									String str = (String) request.getAttribute("photo");
										
									%> <%if(str.equals("") || str.equals("null") || str.equals(" "))   { %>
										<img src="../pages/images/employee/NoImage.jpg" height="150"
											width="150" align="middle" /> <% } else  { %> <img
											src="../pages/images/<%=session.getAttribute("session_pool") %>/databankphoto/<%=str %>"
											height="150" width="150" /> <%} %>
										</td>
									</tr>
								</table>

								</td>

							</tr>
							<tr>

								<td height="22" class="formtext"><label class="set"
									name="first.name" id="firstname"
									ondblclick="callShowDiv(this);"><%=label.get("first.name")%></label><span
									class="style3"></span> :<font color="red">*</font></td>
								<td height="22"><s:textfield size="30" name="firstName"
									onkeypress="return charactersOnly();" maxlength="30" /></td>
							</tr>
							<tr>

								<td height="22" class="formtext"><label name="middle.name"
									id="middlename" ondblclick="callShowDiv(this);"><%=label.get("middle.name")%></label>
								:</td>
								<td height="22"><s:textfield size="30" name="middleName"
									onkeypress="return charactersOnly();" maxlength="30" /></td>
							</tr>
							<tr>

								<td height="22" class="formtext"><label name="last.name"
									id="lastname" ondblclick="callShowDiv(this);"><%=label.get("last.name")%></label>
								:<font color="red">*</font></td>
								<td height="22"><s:textfield size="30" name="lastName"
									onkeypress="return charactersOnly();" maxlength="30" /></td>
							</tr>
							<tr>
								<td width="20%" height="22" class="formtext"><label
									name="dob" id="dob" ondblclick="callShowDiv(this);"><%=label.get("dob")%></label>
								:<font color="red">*</font></td>
								<td height="22"><label> <s:textfield name="dob"
									theme="simple" size="30" maxlength="10"
									onkeypress="return numbersWithHiphen();"
									onfocus="clearText('paraFrm_dob','dd-mm-yyyy')"
									onblur="setText('paraFrm_dob','dd-mm-yyyy');" /> <s:a
									href="javascript:NewCal('paraFrm_dob','DDMMYYYY');">
									<img src="../pages/common/css/default/images/Date.gif"
										width="16" height="16" border="0" />
								</s:a></label></td>
							</tr>
							<tr>

								<td height="22" class="formtext"><label name="gend"
									id="gender" ondblclick="callShowDiv(this);"><%=label.get("gend")%></label>
								:<font color="red">*</font></td>
								<td height="22"><s:select name="gender" cssStyle="width:72"
									list="#{'':'--Select--','M':'Male','F':'Female','O':'Other'}" /></td>
							</tr>
							<tr>
								<td height="22" class="formtext"><label
									name="marital.status" id="marstatus"
									ondblclick="callShowDiv(this);"><%=label.get("marital.status")%></label>
								:<font color="red">*</font></td>
								<td height="22"><s:select name="maritalStatus"
									cssStyle="width:72"
									list="#{'':'--Select--','S':'Single','M':'Married','D':'Divorced'}" /></td>

							</tr>
							<tr>
								<td height="22" class="formtext"><label name="experience"
									id="exper" ondblclick="callShowDiv(this);"><%=label.get("experience")%></label>
								:<font color="red">*</font></td>
								<td height="22"><s:textfield name="minExp" size="2"
									maxlength="2" onkeypress="return numbersOnly();" />&nbsp; Year
								<s:textfield name="maxExp" size="4" maxlength="2"
									onkeypress="return numbersOnly();" /> &nbsp; Months</td>
								<td height="22" class="formtext"><label name="photo"
									id="photo" ondblclick="callShowDiv(this);"><%=label.get("photo")%></label>
								:</td>
								<td height="22"><s:textfield name="uploadPhoto" size="18"
									maxlength="50" readonly="true" /><span id="upload"> <input
									name="Browse" type="button" class="token" value="Upload"
									onclick="uploadPhotograph('paraFrm_uploadPhoto');" /></span></td>
							</tr>

							<tr>
								<td height="22" class="formtext" colspan="2"><input
									type="checkbox" name="addCheck" id="addCheck"
									onclick="setAddress();" /> <label name="addresscheck"
									id="addresscheck" ondblclick="callShowDiv(this);"><%=label.get("addresscheck")%></label>
								:</td>
								<td height="22"></td>


							</tr>

							<tr>
								<td height="22" class="formtext" valign="top"><label
									name="address" id="address" ondblclick="callShowDiv(this);"><%=label.get("address")%></label>
								:<font color="red">*</font></td>
								<td height="22" nowrap="nowrap">
									<s:textarea rows="4" cols="31" name="address" 
									onkeyup="callColourLength('address','500');" /> 
									<img src="../pages/images/zoomin.gif" height="12" align="bottom"
										width="12" theme="simple" 
										onclick="javascript:callWindow('paraFrm_address','address','','500','500');">
								</td>

								<td height="22" class="formtext" valign="top" nowrap="nowrap"><label
									name="address1" id="address1" ondblclick="callShowDiv(this);"><%=label.get("address1")%></label>
								:</td>
								<td height="22" nowrap="nowrap"><s:textarea rows="4"
									cols="31" name="address1"  onkeyup="callColourLength('address1','500');" /> 
									<img src="../pages/images/zoomin.gif" height="12" align="bottom"
									width="12" theme="simple"
									onclick="javascript:callWindow('paraFrm_address1','address1','','500','500');">
								</td>

							</tr>

							<tr>
								<td height="22" class="formtext"><label name="city"
									id="city" ondblclick="callShowDiv(this);"><%=label.get("city")%></label>
								:<font color="red">*</font></td>
								<td height="22" nowrap="nowrap"><s:textfield size="30"
									name="city" readonly="true" maxlength="30" /> <img
									src="../pages/images/recruitment/search2.gif" width="16"
									height="15" class="iconImage"
									onclick="javascript:callsF9(500,325,'CandDataBank_f9cityaction1.action');">
								<s:hidden name="cityCode" /> <s:hidden name="stateCode" /> <s:hidden
									name="countryCode" /></td>

								<td height="22" class="formtext"><label name="city"
									id="city1" ondblclick="callShowDiv(this);"><%=label.get("city")%></label>
								:</td>
								<td height="22" nowrap="nowrap"><s:textfield size="30"
									name="city1" readonly="true" maxlength="30" /> <img
									src="../pages/images/recruitment/search2.gif" width="16"
									height="15" class="iconImage"
									onclick="javascript:callsF9(500,325,'CandDataBank_f9cityaction2.action');">
								</td>

							</tr>
							<tr>
								<td height="22" class="formtext"><label name="state"
									id="state" ondblclick="callShowDiv(this);"><%=label.get("state")%></label>
								:</td>
								<td height="22"><s:textfield size="30" name="state"
									readonly="true" maxlength="30" /></td>

								<td height="22" class="formtext"><label name="state"
									id="state1" ondblclick="callShowDiv(this);"><%=label.get("state")%></label>
								:</td>
								<td height="22"><s:textfield size="30" name="state1"
									readonly="true" maxlength="30" /></td>
							</tr>
							<tr>
								<td height="22" class="formtext"><label name="country"
									id="country" ondblclick="callShowDiv(this);"><%=label.get("country")%></label>
								:</td>
								<td height="22"><s:textfield size="30" name="country"
									readonly="true" maxlength="30" /></td>
								<td height="22" class="formtext"><label name="country"
									id="country1" ondblclick="callShowDiv(this);"><%=label.get("country")%></label>
								:</td>
								<td height="22"><s:textfield size="30" name="country1"
									readonly="true" maxlength="30" /></td>
							<tr>

								<td height="22" class="formtext"><label name="pincode"
									id="pincode" ondblclick="callShowDiv(this);"><%=label.get("pincode")%></label>
								:</td>
								<td height="22"><s:textfield size="30" name="pincode"
									readonly="false" maxlength="15"
									onkeypress="return numbersOnly();" /></td>


								<td height="22" class="formtext"><label name="pincode"
									id="pincode1" ondblclick="callShowDiv(this);"><%=label.get("pincode")%></label>
								:</td>
								<td height="22"><s:textfield size="30" name="pincode1"
									readonly="false" maxlength="15"
									onkeypress="return numbersOnly();" /></td>
							</tr>

							<tr>
								<td height="22" class="formtext"><label name="resphone"
									id="resphone" ondblclick="callShowDiv(this);"><%=label.get("resphone")%></label>
								:</td>
								<td height="22"><s:textfield size="30" name="resPhone"
									maxlength="25" onkeypress="return validatePhoneNo();" /></td>
								<td height="22" class="formtext"><label name="mobile"
									id="mobile" ondblclick="callShowDiv(this);"><%=label.get("mobile")%></label>
								:<font color="red">*</font></td>
								<td height="22"><s:textfield size="30" name="mobile"
									maxlength="25" onkeypress="return validateMobile();" /></td>
							</tr>
							<tr>
								<td height="22" class="formtext"><label name="offphone"
									id="offphone" ondblclick="callShowDiv(this);"><%=label.get("offphone")%></label>
								:</td>
								<td height="22"><s:textfield size="30" name="offPhone"
									maxlength="25" onkeypress="return validatePhoneNo();" /></td>
								<td height="22" class="formtext"><label name="email.id"
									id="email.id" ondblclick="callShowDiv(this);"><%=label.get("email.id")%></label>
								:<font color="red">*</font></td>
								<td height="22"><s:textfield size="30" name="email"
									maxlength="50" onblur="return callValidateEmail('paraFrm_email');" />

								</td>
							</tr>

							<tr>
								<td height="22" class="formtext"><label name="current.ctc"
									id="current.ctc" ondblclick="callShowDiv(this);"><%=label.get("current.ctc")%></label>
								: <font color="red">*</font></td>
								<td height="22"><s:textfield size="30" name="currentCtc"
									maxlength="6"
									onblur="return valCTC('paraFrm_currentCtc','current.ctc');"
									onkeypress="return numbersWithDot();" /></td>
								<td height="22" class="formtext" nowrap="nowrap"><label
									name="exp.ctc" id="exp.ctc" ondblclick="callShowDiv(this);"><%=label.get("exp.ctc")%></label>
								: <font color="red">*</font></td>
								<td height="22"><s:textfield size="30" name="expectedCtc"
									maxlength="6"
									onblur="return valCTC('paraFrm_expectedCtc','exp.ctc');"
									onkeypress="return numbersWithDot();" />
								</td>
							</tr>

							<tr>
								<td height="22" class="formtext"><label name="passport.no"
									id="passportno" ondblclick="callShowDiv(this);"><%=label.get("passport.no")%></label>
								:</td>
								<td height="22"><s:textfield size="30" name="passport"
									maxlength="25" onkeypress="return alphaNumeric();" /></td>
								<td height="22" class="formtext"><label name="pan.no"
									id="pan.no" ondblclick="callShowDiv(this);"><%=label.get("pan.no")%></label>
								:</td>
								<td height="22"><s:textfield size="30" name="panNo"
									maxlength="15" onkeypress="return alphaNumeric();" /></td>
							</tr>

							<!-- ADDED BY DHANASHREE BEGINS -->
							<tr>
								<td height="22" class="formtext"><label
									name="candidate.company" id="candidateCompany"
									ondblclick="callShowDiv(this);"><%=label.get("candidate.company")%></label>
								:</td>
								<td height="22"><s:textfield size="30"
									name="candidateCompany" 
									onkeypress="return alphaNumeric();" /></td>
								<td height="22" class="formtext"><label
									name="candidate.position" id="candidatePosition"
									ondblclick="callShowDiv(this);"><%=label.get("candidate.position")%></label>
								:</td>
								<td height="22"><s:textfield size="30"
									name="candidatePosition" 
									onkeypress="return alphaNumeric();" /></td>
							</tr>
							<!-- ADDED BY DHANASHREE ENDS -->
							
							
							<!-- ADDED BY Vishwambhar BEGINS -->
							<tr>
								<td height="22" class="formtext">
									<label name="candidate.location" id="candidate.location"
										ondblclick="callShowDiv(this);"><%=label.get("candidate.location")%></label> :
								</td>
								<td height="22">
									<s:textfield size="30" name="currentLocation" onkeypress="return alphaNumeric();" />
								</td>
								<td height="22" class="formtext">
									<label name="noticePeriodLabel" id="noticePeriodLabel"
										ondblclick="callShowDiv(this);"><%=label.get("noticePeriodLabel")%></label> : 
								</td>
								<td height="22">
									<s:textfield size="30" name="noticePeriod" onkeypress="return numbersOnly();" />
								</td>
							</tr>
							<!-- ADDED BY Vishwambhar ENDS -->


							<tr>
								<td height="22" class="formtext" valign="top"><label
									name="info" id="info" ondblclick="callShowDiv(this);"><%=label.get("info")%></label>
								:</td>
								<td height="22" nowrap="nowrap"><s:textarea rows="4"
									cols="31" name="otherInfo" /> <img
									src="../pages/images/zoomin.gif" height="12" align="bottom"
									width="12" theme="simple"
									onclick="javascript:callWindow('paraFrm_otherInfo','info','','300','300');">
								</td>

								<td height="22" class="formtext" valign="top"></td>
								<td height="22"></td>

							</tr>

							<tr>
								<td width="18%" height="22"><label class="set"
									name="resume" id="resume" ondblclick="callShowDiv(this);"><%=label.get("resume")%></label>
								:<font color="red" size="2">*</font></td>
								<td width="60%" nowrap="nowrap" height="22" colspan="3"><s:textfield
									name="uploadFileName" size="30" readonly="true" /> <span
									id="upload"><input type="button" class="token"
									theme="simple" value="Upload Resume"
									onclick="uploadFile('paraFrm_uploadFileName');" align="bottom"
									height="10" /></span></td>
							</tr>

							<tr>

								<td height="22" class="formtext" width="26%"><label
									name="doyou" id="doyou" ondblclick="callShowDiv(this);"><%=label.get("doyou")%></label>
								:</td>
								<td><s:hidden name="radioFlag" /> <s:if test="radioFlag">
									<s:radio name="doYou" value="%{'Y'}"
										list="#{'Y':'Yes','N':'No'}" onclick="showEmpId();"></s:radio>
								</s:if> <s:else>
									<s:radio name="doYou" value="%{'N'}"
										list="#{'Y':'Yes','N':'No'}" onclick="showEmpId();"></s:radio>
								</s:else></td>
							</tr>

							<tr id="refEmp">

								<td height="22" class="formtext" width="26%"><label
									name="ref" id="ref" ondblclick="callShowDiv(this);"><%=label.get("ref")%></label>
								:<font color="red">*</font></td>
								<td nowrap="nowrap"><s:textarea name="refEmpName" cols="31"
									rows="4" /> <img src="../pages/images/zoomin.gif" height="12"
									align="bottom" width="12" theme="simple"
									onclick="javascript:callWindow('paraFrm_refEmpName','ref','','100','100');"></td>

							</tr>






							<tr>

								<td height="22" class="formtext" width="26%"><label
									name="wheyouemp" id="wheyouemp" ondblclick="callShowDiv(this);"><%=label.get("wheyouemp")%></label>
								:</td>

								<td><s:hidden name="radioFlag1" /> <s:if test="radioFlag1">
									<s:radio name="wheYouEmp" value="%{'Y'}"
										list="#{'Y':'Yes','N':'No'}"></s:radio>
								</s:if> <s:else>
									<s:radio name="wheYouEmp" value="%{'N'}"
										list="#{'Y':'Yes','N':'No'}"></s:radio>
								</s:else></td>
							</tr>
							<tr>

								<td height="22" class="formtext" width="26%"><label
									name="relocate" id="relocate" ondblclick="callShowDiv(this);"><%=label.get("relocate")%></label>
								:</td>
								<td><s:hidden name="radioFlag2" /> <s:if test="radioFlag2">
									<s:radio name="relocate" value="%{'Y'}"
										list="#{'Y':'Yes','N':'No'}"></s:radio>
								</s:if> <s:else>
									<s:radio name="relocate" value="%{'N'}"
										list="#{'Y':'Yes','N':'No'}"></s:radio>
								</s:else></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				<!--
         <tr>
          <td height="5" colspan="3"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
        </tr>
       -->
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">

					<tr>
						<td class="formtext">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td><strong class="text_head">Reference Details
										: </strong></td>
										<td align="right"><input type="button" class="add"
											value=" Add Row" onclick="addRowToRef();" /> <input
											type="button" class="delete" value=" Remove"
											onclick="chkDeleteVacancy();" /></td>
									</tr>
									<tr>
										<td colspan="5">
										<table width="100%" id="tblRef" class="sortable">
											<tr>
												<td width="5%" valign="top" class="formth" nowrap="nowrap"><b><label
													class="set" name="sr" id="sr"
													ondblclick="callShowDiv(this);"><%=label.get("sr")%></label></b></td>
												<td width="15%" valign="top" class="formth" align="center"><b><label
													name="refName" id="refName" ondblclick="callShowDiv(this);"><%=label.get("refName")%></label></b></td>
												<td width="20%" valign="top" class="formth" align="center"><b><label
													name="prof" id="prof" ondblclick="callShowDiv(this);"><%=label.get("prof")%></label></b></td>
												<td width="15%" valign="top" class="formth" align="center"><b><label
													name="cont" id="cont" ondblclick="callShowDiv(this);"><%=label.get("cont")%></label></b></td>
												<td width="5%" valign="top" class="formth" align="center">&nbsp;</td>
												<td width="25%" valign="top" class="formth" align="center"><b><label
													name="rec.comments" id="rec.comments"
													ondblclick="callShowDiv(this);"><%=label.get("rec.comments")%></label></b></td>
												<td width="5%" valign="top" class="formth" align="center">&nbsp;</td>
												<td width="10%" valign="top" class="formth" abbr="right"
													align="left"><input type="checkbox" name="chkVacAll"
													id="chkVacAll" onclick="return callVacAll();" /></td>

												<td></td>
											</tr>
											<% int i = 0 ; %>
											<s:iterator value="refList">
												<tr>
													<td width="5%" class="sortableTD" align="center"><%=++i%><!--
						                                   
						                                   <s:property value="srNo" />
						                                   
						                                   --></td>
													<td width="15%" class="sortableTD" align="left"><s:textfield
														name="refName" size="25" maxlength="50" /></td>
													<td width="20%" class="sortableTD" align="left"><s:textfield
														name="profession" id="profession" size="20" maxlength="50" />
													</td>
													<td width="15%" class="sortableTD" align="left"><s:textarea
														rows="1" cols="20" name="contactDet"
														id='<%="contactDet"+i%>' /></td>

													<td width="5%" class="sortableTD"><img
														src="../pages/images/zoomin.gif" height="12"
														align="bottom" width="12" theme="simple"
														onclick="javascript:callWindow('<%="contactDet"+i%>','cont','','500','500');"></td>


													<td width="25%" class="sortableTD" align=center><s:textarea
														rows="1" cols="20" name="refComment"
														id='<%="refComment" +i%>' /></td>
													<td width="5%" class="sortableTD"><img
														src="../pages/images/zoomin.gif" height="12"
														align="bottom" width="12" theme="simple"
														onclick="javascript:callWindow('<%="refComment"+i%>','rec.comments','','500','500');"></td>

													<td width="10%" align="center" class="sortableTD"><input
														type="checkbox" class="checkbox" value="N"
														name="confChkVac" id="confChkVac<%=i%>"
														onclick="callForVac('<%=i%>')" /></td>
													<td width="0%"><input type="hidden"
														name="hdeleteVac<%=i%>" id="hdeleteVac<%=i%>" /></td>
													<td width="0%"><input type="hidden" name="refDtlCode"
														id="refDtlCode" /></td>
												</tr>
											</s:iterator>
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
				</td>
			</tr>
		</s:else>

		<tr>
			<s:if test="listFlag"></s:if>
			<s:else>
				<td width="60%"><jsp:include
					page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				<td width="40%" align="right"><strong>Page 1 of 1</strong></td>
			</s:else>

		</tr>

	</table>

</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script>
	function callJobNameDetailsWindow(action,e) {
 		//alert(action);
		callPageDisplay(action,'500','300','false',e);	 
	}
  
  function callColourLength(type,maxLenght){
		var cmt = document.getElementById('paraFrm_'+type).value;
		var remain = eval(maxLenght) - eval(cmt.length);
		if(eval(remain)< 0){
			document.getElementById('paraFrm_'+type).style.background = '#FFFF99';
		} else {
			document.getElementById('paraFrm_'+type).style.background = '#FFFFFF';
		} 
	}
  
  
  if(document.getElementById("paraFrm_listFlag").value=="true"){
  	callHideFilter();
  }
  
  function callShowFilter(){
    		callReset();
  			document.getElementById("showFilter").style.display='none';
  			document.getElementById("hideFilter").style.display='';//showFilterToApp
  			document.getElementById("showFilterToApp").style.display='';
  			document.getElementById("appliedFilterValue").style.display='none';
 }
 
   function callEditFilter(){ 
    		document.getElementById('paraFrm_viewEditFlag').value="true";
  			document.getElementById("showFilter").style.display='none';
  			document.getElementById("hideFilter").style.display='';//showFilterToApp
  			document.getElementById("showFilterToApp").style.display='';
  			document.getElementById("appliedFilterValue").style.display='none';
 }
 
 	window.onload=callMeOnLoad;
	
	function callMeOnLoad() {
  		var fieldName =["paraFrm_fromDate","paraFrm_toDate"];
	    setTextArray(fieldName,"dd-mm-yyyy");
		if(document.getElementById('paraFrm_viewEditFlag').value=="true") {
		   document.getElementById('showFilterToApp').style.display='';  
		   document.getElementById('hideFilter').style.display='';  
		   document.getElementById('showFilter').style.display='none';    
		}
	}
 
  function callHideFilter(){
	document.getElementById("showFilter").style.display=''; 			
	document.getElementById("hideFilter").style.display='none';
	document.getElementById("showFilterToApp").style.display='none';
	document.getElementById("appliedFilterValue").style.display='none';
 } 
 
 
showEnable();
function showEnable(){
	if(document.getElementById("paraFrm_searchFlag").value=="true"){
		document.getElementById("showFilter").style.display='none';
		document.getElementById("appliedFilterValue").style.display='';
	//document.getElementById("paraFrm_searchFlag").value="false";
	}
}  
  
  
 function callReset(){
	 document.getElementById("paraFrm_searchCandName").value="";
	 document.getElementById("paraFrm_searchCandId").value="";
	 document.getElementById("paraFrm_expMonth").value="";
	 document.getElementById("paraFrm_expYear").value="";
	 document.getElementById("paraFrm_fromDate").value="";
	 document.getElementById("paraFrm_toDate").value="";
	 document.getElementById("paraFrm_candStatus").value="";
	 document.getElementById("paraFrm_resumeSrc").value="";
	 var fieldName =["paraFrm_fromDate","paraFrm_toDate"];
     setTextArray(fieldName,"dd-mm-yyyy");
 } 
 
function applyFilters(){
	document.getElementById('paraFrm_viewEditFlag').value="false";
	var cand= document.getElementById("paraFrm_searchCandName").value;
	var mon=trim(document.getElementById("paraFrm_expMonth").value);
	var year=trim(document.getElementById("paraFrm_expYear").value);
	var fDate=document.getElementById('paraFrm_fromDate').value;
	var tDate=document.getElementById('paraFrm_toDate').value;
	var status=(document.getElementById("paraFrm_candStatus").value);
	var resume=document.getElementById('paraFrm_resumeSrc').value;
	document.getElementById('paraFrm_myPage').value="";
	if(cand=="" && mon=="" && year=="" && fDate=="dd-mm-yyyy" && tDate=="dd-mm-yyyy" && status=="" && resume==""){
			alert("Please enter/select atleast one field to apply filter");
			return false;
	} 
 			
	if(eval(mon) >11){
   		alert("Please enter month less than 12");	   	     
        return false;
    }
 			
 	if(fDate!="dd-mm-yyyy") {
  		 if(!validateDate('paraFrm_fromDate','postingFrmDt'))
	     return false; 
	}
	
	if(tDate!="dd-mm-yyyy") {
		if(!validateDate('paraFrm_toDate','postingToDt'))
		return false;
	}
	 if(fDate!="dd-mm-yyyy" && tDate!="dd-mm-yyyy") {
  		if(!dateDifferenceEqual(fDate,tDate,'paraFrm_fromDate', 'postingFrmDt','postingToDt'))
		return false;
	}
			
		document.getElementById("paraFrm_searchFlag").value="true";
		document.getElementById("showFilter").style.display='none';
		document.getElementById("hideFilter").style.display='none';
		document.getElementById("showFilterToApp").style.display='none';
		document.getElementById('paraFrm').action='CandDataBank_searchCandDet.action';
 		document.getElementById('paraFrm').submit();
  } 

function callClear(){
	document.getElementById("showFilter").style.display='';
	document.getElementById("hideFilter").style.display='none';
	document.getElementById("showFilterToApp").style.display='none';
	document.getElementById("appliedFilterValue").style.display='none';
	document.getElementById("paraFrm_searchFlag").value='false';
	document.getElementById("paraFrm_myPage").value="";
	document.getElementById('paraFrm').action='CandDataBank_input.action';
   	document.getElementById('paraFrm').submit();
}
 
  
  
//if(!(document.getElementById("paraFrm_listFlag").value=="true")){

 //}
 
 
  function uploadFile(fieldName) {
		var path = document.getElementById("paraFrm_dataPath").value;
		window.open('<%=request.getContextPath()%>/pages/recruitment/uploadResume.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
	}

 function uploadPhotograph(fieldName) {
	//	var path = document.getElementById("paraFrm_pathPhoto").value;
		var path="images/<%=session.getAttribute("session_pool")%>/databankphoto";
		window.open('../pages/common/multipleUploadFile.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
	}

function addRowToRef() {
		  var tbl = document.getElementById('tblRef');
		  var lastRow = tbl.rows.length;
		  
		  // if there's no header row in the table, then iteration = lastRow + 1
		  var iteration = lastRow;
		  var row = tbl.insertRow(lastRow);
		
		 
		  // left cell
		  var cellLeft = row.insertCell(0);
		  var textNode = document.createTextNode(iteration);
		  cellLeft.className='sortableTD';
		  cellLeft.align='center';
		  cellLeft.appendChild(textNode);
	  
   		  var cellRefName = row.insertCell(1);
      	  var refName = document.createElement('input');
      	  cellRefName.className='sortableTD';
		  refName.type = 'text';
		  refName.name = 'refName';
		  refName.id = 'refName'+iteration;
		  refName.size='25';
		  refName.maxLength='50';
		  cellRefName.align='center';
		  cellRefName.appendChild(refName);
  
		  var cellProfession = row.insertCell(2);
		  var profession = document.createElement('input');
		  cellProfession.className='sortableTD';
  		  profession.type = 'text';
		  profession.name = 'profession';
		  profession.id = 'profession'+iteration;
		  profession.size='20';
		  profession.maxLength='50';
		  cellProfession.align='left';
		  cellProfession.appendChild(profession);
		  
		  var cellContact = row.insertCell(3);
		  var contact = document.createElement('textarea');
		  cellContact.className='sortableTD';
  		   contact.name = 'contactDet';
		  contact.id = 'contactDet'+iteration;
		  contact.rows ='1';
		  contact.cols ='20';
		  cellContact.align='center';
		  cellContact.appendChild(contact);
		  
		    var cellContactImg = row.insertCell(4);
		    cellContactImg.className='sortableTD';
			var img=  document.createElement('img');
			img.type='image';
			img.src="../pages/images/zoomin.gif";
	 		img.height='12';
	 		img.align='bottom';
 			img.width='12';
 			img.id='img'+ iteration;
			img.theme='simple';
 			img.onclick=function(){
	 			readFlag='';
	 			fieldName='contactDet'+iteration;
	 			windowName='cont';
	 			charCount='500';
	 			maxLength='500';
	 			 mytitle=document.getElementById(windowName).innerHTML.toLowerCase();
	 			 window.open('../pages/common/popupTextArea.jsp?fieldName='+fieldName+'&windowName='+mytitle+'&charCntName='+charCount+'&maxLength='+maxLength+'&readFlag='+readFlag+'','','width=580,height=430,scrollbars=no,resizable=no,top=250,left=350');
				//window.open('../pages/common/popupTextArea.jsp?fieldName='+fieldName+'&windowName='+mytitle+'&readFlag='+readFlag+'','','width=500,height=430,scrollbars=no,resizable=no,top=250,left=350');
				document.getElementById('paraFrm').target ="main";
			};
 		  cellContactImg.appendChild(img);
		  var cellComm = row.insertCell(5);
		  cellComm.className='sortableTD';
		  var comm = document.createElement('textarea');
  		  comm.name = 'refComment';
		  comm.id = 'refComment'+iteration;
		  comm.rows = '1';
		  comm.cols ='20';
		  cellComm.align='center';
		  cellComm.appendChild(comm);
		  
		  
		    var cellCommentImg = row.insertCell(6);
		    cellCommentImg.className='sortableTD';
			var commImg=  document.createElement('img');
			commImg.type='image';
			commImg.src='../pages/images/zoomin.gif';
	 		commImg.height='12';
	 		commImg.align='bottom';
 			commImg.width='12';
 			commImg.id='img'+ iteration;
			commImg.theme='simple';
	 			commImg.onclick=function(){
	 			readFlag='';
	 			fieldName='refComment'+iteration;
	 			windowName='rec.comments';
	 			charCount='500';
	 			maxLength='500';
	 			mytitle=document.getElementById(windowName).innerHTML.toLowerCase();
	 			window.open('../pages/common/popupTextArea.jsp?fieldName='+fieldName+'&windowName='+mytitle+'&charCntName='+charCount+'&maxLength='+maxLength+'&readFlag='+readFlag+'','','width=580,height=430,scrollbars=no,resizable=no,top=250,left=350');
				//window.open('../pages/common/popupTextArea.jsp?fieldName='+fieldName+'&windowName='+mytitle+'&readFlag='+readFlag+'','','width=500,height=430,scrollbars=no,resizable=no,top=250,left=350');
				document.getElementById('paraFrm').target ="main";
            };
 			cellCommentImg.appendChild(commImg);
		  
		  var cellrefChk = row.insertCell(7);
		  var refChk = document.createElement('input');
		  cellrefChk.className='sortableTD';
		  refChk.type = 'checkbox';
		  refChk.name = 'confChkVac';
		  refChk.id = 'confChkVac'+iteration;
		  refChk.onclick=function(){
		 	  if(document.getElementById('confChkVac'+iteration).checked == true) {	
			    	document.getElementById('hdeleteVac'+iteration).value="Y";
			   } else {
			  	    document.getElementById('hdeleteVac'+iteration).value="";
		   		}
			};
 
		  cellrefChk.align='center';
		  cellrefChk.appendChild(refChk);
		  
		  
		  var hiddenDel=row.insertCell(8);
		  var hid = document.createElement('input');
		  hid.type = 'hidden';
		  hid.name = 'hdeleteVac'+iteration;
		  hid.id = 'hdeleteVac' +iteration;
		  hiddenDel.appendChild(hid);//refDtlCode
		  
		  var refDetCode=row.insertCell(9);
		  var detCode = document.createElement('input');
		  detCode.type = 'hidden';
		  detCode.name = 'refDtlCode';
		  detCode.id = 'refDtlCode' ;
		  refDetCode.appendChild(detCode);
	}  

	function deleteFun(){
		var conf=confirm("Do you really want to delete this record ?");
	  	if(conf){
			document.getElementById("paraFrm").action="CandDataBank_deleteRec.action";
	    	document.getElementById("paraFrm").submit();
	  	}
	}

	function addnewFun(){
		document.getElementById("paraFrm").action="CandDataBank_addNew.action";
	    document.getElementById("paraFrm").submit();
	    return false;
	}
	
	function chkSize(){
	  	var tbl = document.getElementById('tblRef');
 		var lastRow = tbl.rows.length;
 		for(var idx=1;idx<lastRow;idx++){
  		  var comment=document.getElementById('refComment'+idx).value;
  		  var contact=document.getElementById('contactDet'+idx).value;
  		  if (comment.length >500) {
	  		  alert("Maximum length of "+document.getElementById('rec.comments').innerHTML.toLowerCase()+" is 500 characters.");
	  		  return false;
  		  } else if(contact.length >500){
	  		  alert("Maximum length of "+document.getElementById('cont').innerHTML.toLowerCase()+" is 500 characters.");
	  		  return false;
  		  }
 		}
	return true;
	}
	
	
	function saveFun(){
		var add=document.getElementById("paraFrm_address").value;
		var add1=document.getElementById("paraFrm_address1").value;
		var emp=trim(document.getElementById("paraFrm_refEmpName").value);
		var inf=document.getElementById("paraFrm_otherInfo").value;
		var resume=document.getElementById("paraFrm_uploadFileName").value;
		var file=document.getElementById("paraFrm_uploadPhoto").value;
		var mon=document.getElementById("paraFrm_maxExp").value;
		var curCtc=document.getElementById("paraFrm_currentCtc").value;
		var expCtc=document.getElementById("paraFrm_expectedCtc").value;
		var cctc=curCtc.split(".");
		var exCtc=expCtc.split(".");
		
		var current=curCtc.indexOf('.');
		var currentCtc=curCtc.substring(eval(current)+1,curCtc.length);
		var expected=expCtc.indexOf('.');
		var expectedCTC=expCtc.substring(eval(expected)+1,expCtc.length);
		
		var currentCTCNext = trim(document.getElementById('paraFrm_currentCtc').value);
		var expectedCTCNext = trim(document.getElementById('paraFrm_expectedCtc').value);
		
		var resumeVar = trim(document.getElementById('paraFrm_uploadFileName').value);
		 
		var fieldName = ["paraFrm_gender",
							"paraFrm_maritalStatus","paraFrm_minExp","paraFrm_address","paraFrm_city","paraFrm_mobile",
							"paraFrm_email"];
		//var lableName = ["first name","last name","date of birth","gender","marital status","experience",
							//"current address","city","residence phone","email id","resume"];
		var lableName = ["gender","marstatus","exper","address","city","mobile","email.id"];
		var flag = ["select","select","enter","enter","select","enter","enter"];
		
    
	if(document.getElementById("paraFrm_updateFirst").value=="true"){
		if(trim(document.getElementById("paraFrm_firstName").value)==""){
			alert("Please enter "+document.getElementById('firstname').innerHTML.toLowerCase());
			return false;
		}
		
		if(trim(document.getElementById("paraFrm_lastName").value)==""){
			alert("Please enter "+document.getElementById('lastname').innerHTML.toLowerCase());
			return false;
		}
		
		if(trim(document.getElementById("paraFrm_dob").value)=="dd-mm-yyyy"){
			alert("Please enter/select "+document.getElementById('dob').innerHTML.toLowerCase());
			return false;
		}
		
		 if(!validateDate('paraFrm_dob','dob')){
     	 	return false;
     	 }
	
		if(!validateBlank(fieldName,lableName,flag)){
             	return false;
     	 } 
   	     
     	
     	 
		 if(eval(mon) >11){
			alert("Please enter month less than 12");	   	     
   	     	return false;
   	     }
     	 if(!callValidateEmail('paraFrm_email')){
   	     	return false;
   	     
   	     }
   	      	   
     	 if(!chkSize()){
     	 	return false;
   	     }
   	   
   	     
   	     if(add.length >500){
   	          alert("Maximum length of "+document.getElementById('address').innerHTML.toLowerCase()+" is 500 characters.");
   	          return false;
   	     }
   	     if(add1.length>500){
   	       	  alert("Maximum length of "+document.getElementById('address1').innerHTML.toLowerCase()+" is 500 characters.");
   	          return false;
   	     }
   	     if(inf.length >300){
   	     	 alert("Maximum length of "+document.getElementById('info').innerHTML.toLowerCase()+" is 300 characters.");
   	         return false;
   	     
   	     }
   	     if(file.length >50){
			 alert("Maximum length of "+document.getElementById('photograph').innerHTML.toLowerCase()+" is 50 characters.");
   	         return false; 	     
   	     
   	     }
   	   
   	     if (currentCTCNext == "") {
   	     	alert("please enter " + document.getElementById('current.ctc').innerHTML.toLowerCase() + " if any otherwise enter  0.");
   	     	document.getElementById('paraFrm_currentCtc').focus();
   	     	return false;
   	     }
   	     
   	     if (expectedCTCNext == "") {
   	     	alert("please enter " + document.getElementById('exp.ctc').innerHTML.toLowerCase() + " if any otherwise enter  0.");
   	     	document.getElementById('paraFrm_expectedCtc').focus();
   	     	return false;
   	     }
   	     
   	      if (resumeVar == "") {
   	     	alert("please " + document.getElementById('resume').innerHTML.toLowerCase() + ".");
   	     	document.getElementById('paraFrm_uploadFileName').focus();
   	     	return false;
   	     }
   	     
   	    if(!valCTC('paraFrm_currentCtc','current.ctc'))
		return false;

		if(!valCTC('paraFrm_expectedCtc','exp.ctc'))
		return false;
   	   
   	   //  if(resume.length >50){
		//	 alert("Maximum length of "+document.getElementById('resume').innerHTML.toLowerCase()+" is 50 characters.");
   	     //   return false; 	     
   	     
   	   // }
     	
     	 if(document.getElementById('paraFrm_radioEmp').value=='Y'){
	     	   if(emp=="") {
	     	   		document.getElementById("paraFrm_refEmpName").value="";
	     	     	alert("Please enter "+document.getElementById('ref').innerHTML.toLowerCase());
	     	     	return false;
	     	    }else if(eval(emp.length) >100){
	     	      	alert("Maximum length of "+document.getElementById('ref').innerHTML.toLowerCase()+" is 100 characters");
	     	       	return false;
	     	    } else {
	     	        document.getElementById("paraFrm").action="CandDataBank_updateFirst.action";
		          	document.getElementById("paraFrm").submit();
	     	    }
     	   } else {
 	 			document.getElementById("paraFrm").action="CandDataBank_updateFirst.action";
          		document.getElementById("paraFrm").submit();
         	}	
	}else{
		if(trim(document.getElementById("paraFrm_firstName").value)==""){
			alert("Please enter "+document.getElementById('firstname').innerHTML.toLowerCase());
			return false;
		}
		
		if(trim(document.getElementById("paraFrm_lastName").value)==""){
			alert("Please enter "+document.getElementById('lastname').innerHTML.toLowerCase());
			return false;
		}
		
		if(trim(document.getElementById("paraFrm_dob").value)=="dd-mm-yyyy"){
			alert("Please enter/select "+document.getElementById('dob').innerHTML.toLowerCase());
			return false;
		}
			
		 if(!validateDate('paraFrm_dob','dob')){
     	 	return false;
     	 }
	
		if(!validateBlank(fieldName,lableName,flag)){
              	return false;
     	 }else if(eval(mon) >11){
			alert("Please enter month less than 12");	   	     
   	     	return false;
   	     }else if(!callValidateEmail('paraFrm_email')){
   	     	return false;
   	     }else if(!chkSize()){
     	 		return false;
     	 }else if(add.length >500){
   	          alert("Maximum length of "+document.getElementById('address').innerHTML.toLowerCase()+" is 500 characters.");
   	          return false;
   	     }else if(add1.length>500){
   	       	  alert("Maximum length of "+document.getElementById('address1').innerHTML.toLowerCase()+" is 500 characters.");
   	          return false;
   	     }else if(inf.length >300){
   	     	 alert("Maximum length of "+document.getElementById('info').innerHTML.toLowerCase()+" is 300 characters.");
   	         return false;
   	     
   	     }else if(file.length >50){
			 alert("Maximum length of "+document.getElementById('photograph').innerHTML.toLowerCase()+" is 50 characters.");
   	         return false; 	     
   	     }
   	    
   	     if (currentCTCNext == "") {
   	     	alert("please enter " + document.getElementById('current.ctc').innerHTML.toLowerCase() + " if any otherwise enter  0.");
   	     	document.getElementById('paraFrm_currentCtc').focus();
   	     	return false;
   	     }
   	     
   	     if (expectedCTCNext == "") {
   	     	alert("please enter " + document.getElementById('exp.ctc').innerHTML.toLowerCase() + " if any otherwise enter  0.");
   	     	document.getElementById('paraFrm_expectedCtc').focus();
   	     	return false;
   	     }
   	     
   	      if (resumeVar == "") {
   	     	alert("please " + document.getElementById('resume').innerHTML.toLowerCase() + ".");
   	     	document.getElementById('paraFrm_uploadFileName').focus();
   	     	return false;
   	     }
   	      
   	    if(!valCTC('paraFrm_currentCtc','current.ctc'))
		return false;

		if(!valCTC('paraFrm_expectedCtc','exp.ctc'))
		return false;
		
   	  
   	     
   	     
   	   //else if(resume.length >50){
		// alert("Maximum length of "+document.getElementById('resume').innerHTML.toLowerCase()+" is 50 characters.");
   	   //      return false; 	     
   	     
   	    // }
     	 else if(document.getElementById('paraFrm_radioEmp').value=='Y'){
			if(emp==""){
				alert("Please enter "+document.getElementById('ref').innerHTML.toLowerCase());					
				return false;					
			} else if(eval(emp.length) >100){
	     	    alert("Maximum length of "+document.getElementById('ref').innerHTML.toLowerCase()+" is 100 characters.");
	     	    return false;
	     	} else {
	     		document.getElementById("paraFrm").action="CandDataBank_saveFirst.action";
   				document.getElementById("paraFrm").submit();
	     	}
     	  } else{
    	 	document.getElementById("paraFrm").action="CandDataBank_saveFirst.action";
    		document.getElementById("paraFrm").submit();
	     }
	   }  
	  return false;
	}
	
	
	function saveandnextFun(){
		var mon=document.getElementById("paraFrm_maxExp").value;
		var add=document.getElementById("paraFrm_address").value;
		var add1=document.getElementById("paraFrm_address1").value;
		var emp=document.getElementById("paraFrm_refEmpName").value;
		var inf=document.getElementById("paraFrm_otherInfo").value;
		var resume=document.getElementById("paraFrm_uploadFileName").value;
		var file=document.getElementById("paraFrm_uploadPhoto").value;
		var curCtc=document.getElementById("paraFrm_currentCtc").value;
		var expCtc=document.getElementById("paraFrm_expectedCtc").value;
		var cctc=curCtc.split(".");
		var exCtc=expCtc.split(".");
		var current=curCtc.indexOf('.');
		var currentCtc=curCtc.substring(eval(current)+1,curCtc.length);
		var expected=expCtc.indexOf('.');
		var expectedCTC=expCtc.substring(eval(expected)+1,expCtc.length);
	
		var currentCTCNext = trim(document.getElementById('paraFrm_currentCtc').value);
		var expectedCTCNext = trim(document.getElementById('paraFrm_expectedCtc').value);
		
		var resumeVar = trim(document.getElementById('paraFrm_uploadFileName').value);
		 
		var fieldName = ["paraFrm_gender",
							"paraFrm_maritalStatus","paraFrm_minExp","paraFrm_address","paraFrm_city","paraFrm_mobile",
							"paraFrm_email"];
		var lableName = ["gender","marstatus","exper", "address","city","mobile","email.id"];
		var flag = ["select","select","enter","enter","select","enter","enter"];
	
	
		if(trim(document.getElementById("paraFrm_firstName").value)==""){
			alert("Please enter "+document.getElementById('firstname').innerHTML.toLowerCase());
			return false;
		}
		
		if(trim(document.getElementById("paraFrm_lastName").value)==""){
			alert("Please enter "+document.getElementById('lastname').innerHTML.toLowerCase());
			return false;
		}
		
		if(trim(document.getElementById("paraFrm_dob").value)=="dd-mm-yyyy"){
			alert("Please enter/select "+document.getElementById('dob').innerHTML.toLowerCase());
			return false;
		}
	
	
	  	if(!validateDate('paraFrm_dob','dob')){
     	 
     	 	return false;
     	 }	
	
		if(!validateBlank(fieldName,lableName,flag)){
              	return false;
     	 }
     	 
     	if(eval(mon) >11){
			alert("Please enter month less than 12");	   	     
   	     	return false;
   	     }	  
     	if(!callValidateEmail('paraFrm_email')){
   	     	return false;
   	     
   	     }	 
	     if(!chkSize()){
	     	return false;
	     
	     } 	
     
     	if(add.length >500){
   	          alert("Maximum length of "+document.getElementById('address').innerHTML.toLowerCase()+" is 500 characters.");
   	          return false;
   	     }
   	     
   	     if(add1.length>500){
   	       	  alert("Maximum length of "+document.getElementById('address1').innerHTML.toLowerCase()+" is 500 characters.");
   	          return false;
   	     }
   	     
   	     if(inf.length >300){
   	     	 alert("Maximum length of "+document.getElementById('info').innerHTML.toLowerCase()+" is 300 characters.");
   	         return false;
   	     
   	     }
   	     
   	     if(file.length >50){
			 alert("Maximum length of "+document.getElementById('photograph').innerHTML.toLowerCase()+" is 50 characters.");
   	         return false; 	     
   	     }
   	     
   	     if (currentCTCNext == "") {
   	     	alert("please enter " + document.getElementById('current.ctc').innerHTML.toLowerCase() + " if any otherwise enter  0.");
   	     	document.getElementById('paraFrm_currentCtc').focus();
   	     	return false;
   	     }
   	     
   	     if (expectedCTCNext == "") {
   	     	alert("please enter " + document.getElementById('exp.ctc').innerHTML.toLowerCase() + " if any otherwise enter  0.");
   	     	document.getElementById('paraFrm_expectedCtc').focus();
   	     	return false;
   	     }
   	     
   	      if (resumeVar == "") {
   	     	alert("please " + document.getElementById('resume').innerHTML.toLowerCase() + ".");
   	     	document.getElementById('paraFrm_uploadFileName').focus();
   	     	return false;
   	     }
   	    
   	    if(!valCTC('paraFrm_currentCtc','current.ctc'))
		return false;

		if(!valCTC('paraFrm_expectedCtc','exp.ctc'))
		return false;
     	 
	    if(document.getElementById('paraFrm_radioEmp').value=='Y'){
	   	   if(emp=="") {
	   	     alert("Please enter "+document.getElementById('ref').innerHTML.toLowerCase());
	   	     return false;
	   	    } else if(eval(emp.length) >100){
	   	      alert("Maximum length of "+document.getElementById('ref').innerHTML.toLowerCase()+" is 100 characters.");
	   	       return false;
	   	    } else {
	   	        document.getElementById("paraFrm").action="CandDataBank_nextPage.action";
	         	document.getElementById("paraFrm").submit();
	   	    }
	     } else {
		 	document.getElementById("paraFrm").action="CandDataBank_nextPage.action";
	      	document.getElementById("paraFrm").submit();
	     }	
      return false;
	}
	
	function nextFun(){
		document.getElementById("paraFrm").action="CandDataBank_searchRecSecond.action";
	    document.getElementById("paraFrm").submit();
	}
	
	function cancelFun(){

		if(document.getElementById("paraFrm_cancelFirst").value=="true"){
	
			document.getElementById("paraFrm").action="CandDataBank_cancelFirst.action";
	    	document.getElementById("paraFrm").submit();
		
		}else{	
		
		document.getElementById("paraFrm").action="CandDataBank_input.action";
	    document.getElementById("paraFrm").submit();
	    }
	    return false;
	}
	
	function searchFun(){
	
		callsF9(500,325,'CandDataBank_f9search.action');
		return false;
	}

   
   function setAddress(){
   	var addCheck =document.getElementById('addCheck').value;
   	var cityName1=document.getElementById('paraFrm_city').value;
   	var code1=document.getElementById('paraFrm_cityCode').value;
   	var stateName1=document.getElementById('paraFrm_state').value;
   	var stCode1=document.getElementById('paraFrm_stateCode').value;
   	var countryName1=document.getElementById('paraFrm_country').value;
    var counCodeName=document.getElementById('paraFrm_countryCode').value;
    var oldPin=document.getElementById('paraFrm_pincode').value;
    var oldAddr=document.getElementById('paraFrm_address').value;
   	
   	if(document.getElementById('addCheck').checked){
   			document.getElementById('paraFrm_city1').value=cityName1;
   			document.getElementById('paraFrm_cityCode1').value=code1;
   			document.getElementById('paraFrm_state1').value=stateName1;
   			document.getElementById('paraFrm_stateCode1').value=stCode1;
   			document.getElementById('paraFrm_country1').value=countryName1;
   			document.getElementById('paraFrm_countryCode1').value=counCodeName;
   			document.getElementById('paraFrm_pincode1').value=oldPin;
   			document.getElementById('paraFrm_address1').value=oldAddr;
   	   }else {
   	        document.getElementById('paraFrm_city1').value="";
   			document.getElementById('paraFrm_cityCode1').value="";
   			document.getElementById('paraFrm_state1').value="";
   			document.getElementById('paraFrm_stateCode1').value="";
   			document.getElementById('paraFrm_country1').value="";
   			document.getElementById('paraFrm_countryCode1').value="";
   			document.getElementById('paraFrm_pincode1').value="";
   			document.getElementById('paraFrm_address1').value="";
   	   
   	   }
   }
   
   
   
   function showAddDiv(){
   		document.getElementById('address').style.display='';
   }
   
   
   function callVacAll()
  {
	 	
		var tbl = document.getElementById('tblRef');
		var rowLen = tbl.rows.length;
	if (document.getElementById('chkVacAll').checked == true){
	
	for (var idx = 1; idx < rowLen; idx++) {
	
				document.getElementById('confChkVac'+idx).checked = true;
				 document.getElementById('hdeleteVac'+idx).value="Y";
				
	    }

 }else{
 
	for (var idx = 1; idx < rowLen; idx++) {
	
	document.getElementById('confChkVac'+idx).checked = false;
	document.getElementById('hdeleteVac'+idx).value="";
     }
  }	 
		
	
  }
  
  
  function callForVac(id)
	   {
	 	  
	 
	   if(document.getElementById('confChkVac'+id).checked == true)
	   {	  
	    document.getElementById('hdeleteVac'+id).value="Y";
	   }
	   else{
	   document.getElementById('hdeleteVac'+id).value="";
	   	
   		}
  }
  
  function chkDeleteVacancy()
	{
	
		var tbl = document.getElementById('tblRef');
		var rowLen = tbl.rows.length;
	 if(chkVac()){
	 var con=confirm('Do you really want to remove this record ?');
	 if(con){
	   document.getElementById('paraFrm').action="CandDataBank_deleteRowRef.action";
	    document.getElementById('paraFrm').submit();
	    } else{
	    		for (var idx = 1; idx < rowLen; idx++) {
	
				document.getElementById('confChkVac'+idx).checked = false;
				document.getElementById('hdeleteVac'+idx).value="";
				document.getElementById('chkVacAll').checked=false;
     			}
	    
	    
	    }
	 }else {
	 	alert('Please select a record to remove');
	 	 return false;
	 }
	 
	}
	
	
	
	function chkVac(){
	var tbl = document.getElementById('tblRef');
var rowLen = tbl.rows.length;

	
	  for(var a=1;a<rowLen;a++){	
	   if(document.getElementById('confChkVac'+a).checked == true)
	   {	
	 	    return true;
	   }	   
	  }
	  return false;
	}
	
/*
following functions are used in paging 

*/	
	
/*function callPage(id){
	   	document.getElementById('paraFrm_myPage').value=id;
		document.getElementById('paraFrm_show').value=id;
	    document.getElementById('paraFrm').action="CandDataBank_callPage.action";
	    document.getElementById('paraFrm').submit();
   }	*/
 function previousPage()
   {
   var pageno=	document.getElementById('paraFrm_myPage').value;
   	
	 document.getElementById('paraFrm_myPage').value=eval(pageno)-1;
	 document.getElementById('paraFrm_show').value=eval(pageno)-1;
	   document.getElementById('paraFrm').action="CandDataBank_callPage.action";
	   document.getElementById('paraFrm').submit();
   }   	
   
  
   	function on()
   {
  	var val= document.getElementById('selectname').value;
	  document.getElementById('paraFrm_show').value=val;
	  document.getElementById('paraFrm_myPage').value=eval(val);
	  document.getElementById('selectname').value=val;
	  document.getElementById('paraFrm').action="CandDataBank_callPage.action";
	   document.getElementById('paraFrm').submit();
   }
   
    function nextPage()
   {
     var pageno=	document.getElementById('paraFrm_myPage').value;
   	  if(pageno=="1")
	   	{	document.getElementById('paraFrm_myPage').value=2;
		    document.getElementById('paraFrm_show').value=2;
	    }else{
				 document.getElementById('paraFrm_myPage').value=eval(pageno)+1;
				 document.getElementById('paraFrm_show').value=eval(pageno)+1;
	    }
		   document.getElementById('paraFrm').action='CandDataBank_callPage.action';
		   document.getElementById('paraFrm').submit();
   }	
function newRowColor(cell)
   		 {
		   cell.className='Cell_bg_first';

	    }
	
	function oldRowColor(cell,val) {
	
	 cell.className='Cell_bg_second';
	
		
	}
 function showEmpId(){
 
    var fieldName =["paraFrm_dob"];
    setTextArray(fieldName,"dd-mm-yyyy");
   if(document.getElementById('paraFrm_doYouY').checked){
     document.getElementById('paraFrm_radioEmp').value="Y";
  		document.getElementById('refEmp').style.display='';
     }
     
     if(document.getElementById('paraFrm_doYouN').checked){
     
     document.getElementById('paraFrm_radioEmp').value="N";
     
      		//document.getElementById('paraFrm_doYou').value="N";
      		document.getElementById('refEmp').style.display='none';
      		//document.getElementById('paraFrm_refEmpId').value="";
      		document.getElementById('paraFrm_refEmpName').value="";
      		//document.getElementById('paraFrm_refEmpTok').value="";
      		document.getElementById('paraFrm_doYouY').checked=false;
     
     }
     
  }
  
  function callForEdit(id){
	  	document.getElementById('paraFrm_hiddenCode').value=id;
		document.getElementById("paraFrm").action="CandDataBank_callForEdit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
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
		   
			document.getElementById('paraFrm').action='CandDataBank_callPage.action';
			document.getElementById('paraFrm').submit();
		}
		
	}	
	
	
	
	
 function callPage(id,pageImg){  
 	 pageNo =document.getElementById('pageNoField').value;	
 	 totalPage =document.getElementById('totalPage').value;	 
 	 
 	   if(pageNo==""){
	        alert("Please Enter Page Number.");
	        document.getElementById('pageNoField').focus();
			 return false;
	        }
	 if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero.");
		     document.getElementById('pageNoField').focus();
			 return false;
		    }
	  if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField').focus();
			 return false;
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
		
		if(document.getElementById("paraFrm_searchFlag").value=="true"){
			document.getElementById("showFilter").style.display='none';
			document.getElementById("appliedFilterValue").style.display='';
		 }
		document.getElementById('paraFrm_myPage').value=id;
		document.getElementById('paraFrm').action='CandDataBank_callPage.action';
		document.getElementById('paraFrm').submit(); 
	}		 
   
   
   /* function callPage(id){
	
		
		if(id=='P'){
		var p=document.getElementById('paraFrm_myPage').value;
		id=eval(p)-1;
		}
		if(id=='N'){
		var p=document.getElementById('paraFrm_myPage').value;
		id=eval(p)+1;
		}
		
		
   		if(document.getElementById("paraFrm_searchFlag").value=="true"){
			document.getElementById("showFilter").style.display='none';
			document.getElementById("appliedFilterValue").style.display='';
			//document.getElementById("paraFrm_searchFlag").value="false";
			}
   	
		document.getElementById('paraFrm_myPage').value=id;
		document.getElementById('paraFrm').action='CandDataBank_callPage.action';
		document.getElementById('paraFrm').submit();
		
	}	
	
*/	
function onPage()  {
 	if(document.getElementById("paraFrm_searchFlag").value=="true"){
		document.getElementById("showFilter").style.display='none';
		document.getElementById("appliedFilterValue").style.display='';
	}
 	
	var val= document.getElementById('selectname').value;
	document.getElementById('paraFrm_show').value=val;
	document.getElementById('paraFrm_myPage').value=eval(val);
	document.getElementById('selectname').value=val;
 	document.getElementById('paraFrm').action='CandDataBank_callPage.action';
    document.getElementById('paraFrm').submit();
 }
   
   
   
function valCTC(ctcfieldname,ctclabelname) {
	var amount=document.getElementById(ctcfieldname).value;
	var amountlabel=document.getElementById(ctclabelname).innerHTML.toLowerCase();
	if(trim(amount)!=""){
		if(isNaN(amount)) { 
		  alert("Only one dot is allowed in "+amountlabel+" field.");
		  document.getElementById(ctcfieldname).focus();
		  return false;
		}	
	}
	return true;
}
		 
  pgshow(); 
  
function pgshow() {
 	var pgno=document.getElementById('paraFrm_show').value;
 	if(!(pgno==""))
  	 document.getElementById('selectname').value=pgno;
}
 showEmpId(); 
  
//Myemail validation -- BEGINS  
function callValidateEmail (name) {
	var emailStr  = document.getElementById(name).value;
	if(emailStr=="")return true;
	
	var checkTLD        = 1;
	
	var knownDomsPat    = /^(com|net|org|edu|int|mil|gov|arpa|biz|aero|name|coop|info|pro|museum)$/;
	
	var emailPat		= /^(.+)@(.+)$/;
	
	var specialChars	= "\\(\\)><@,;:\\\\\\\"\\.\\[\\]";
	
	var validChars		= "\[^\\s" + specialChars + "\]";
	
	var quotedUser		= "(\"[^\"]*\")";
	
	var ipDomainPat		= /^\[(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})\]$/;
	
	var atom			= validChars + '+';
	
	var word			= "(" + atom + "|" + quotedUser + ")";
	
	var userPat			= new RegExp("^" + word + "(\\." + word + ")*$");
	
	var domainPat		= new RegExp("^" + atom + "(\\." + atom +")*$");
	
	var matchArray		= emailStr.match(emailPat);

	if (matchArray==null) {
		alert("Email address seems incorrect (check @ and .'s)");
		document.getElementById(name).focus();
		return false;
	}
	var user			= matchArray[1];
	var domain			= matchArray[2];
	
	 	if(user.indexOf("_")!=-1 && user.split(".").length>1){
	 		alert("The username doesn't seem to be valid for email.");
			document.getElementById(name).focus();
			return false;
	 	}
	for (i=0; i<user.length; i++) {
		if (user.charCodeAt(i)>127 || user.indexOf("-")!=-1) {
				alert("The username contains invalid characters.");
				document.getElementById(name).focus();
				return false;
		 }
	}

	if(domain.indexOf("-")!=-1 && domain.split("-").length>2){
	 		alert("The domain doesn't seem to be valid for email.");
			document.getElementById(name).focus();
			return false;
	 	}

	for (i=0; i<domain.length; i++) {
		if (domain.charCodeAt(i)>127 || domain.indexOf("_")!=-1) {
			alert("The domain name contains invalid characters.");
			document.getElementById(name).focus();
			return false;
   		}
	}


	if (user.match(userPat)==null) {
		alert("The username doesn't seem to be valid for email.");
		document.getElementById(name).focus();
		return false;
	}

	var IPArray=domain.match(ipDomainPat);
	if (IPArray!=null) {
		for (var i=1;i<=4;i++) {
			if (IPArray[i]>255) {
				alert("Destination IP address is invalid!");
				document.getElementById(name).focus();
				return false;
		   }
		}
			return true;
	}

	var atomPat		= new RegExp("^" + atom + "$");
	var domArr		= domain.split(".");
	var len    		= domArr.length;
	
	var dot         = domain.indexOf(".");
	var hiphen		= domain.indexOf("-");
	
	if(dot>hiphen && (dot-hiphen)<2){
		alert("The domain name does not seem to be valid");
		document.getElementById(name).focus();
		return false;
	}if(dot<hiphen && (hiphen-dot)<2){
		alert("The domain name does not seem to be valid");
		document.getElementById(name).focus();
		return false;
	}if(len>3){
		alert("Maximum two dots are allowed in domain name");
		document.getElementById(name).focus();
		return false;
	}
	
	for (i=0;i<len;i++) {
		if (domArr[i].search(atomPat)==-1) {
			alert("The domain name does not seem to be valid");
			document.getElementById(name).focus();
			return false;
	   }
	}if(domain.indexOf("-")!=-1){
		if(domain.indexOf("-")<2){
			alert("The domain name does not seem to be valid");
			document.getElementById(name).focus();
			return false;
		}
	}

	if (len<2) {
		alert("This email address is missing a hostname!");
		document.getElementById(name).focus();
		return false;
	}
	
	return true;
}
//Myemail validation -- ENDS  
 </script>
