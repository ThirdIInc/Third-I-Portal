<%@ taglib prefix="s" uri="/struts-tags"%>
<script language="JavaScript" type="text/javascript" src="..pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript" src="../pages/common/include/javascript/jquery-1.2.2.pack.js"></script>
<script type="text/javascript" src="../pages/common/include/javascript/ddaccordion.js"></script>

<style type="text/css">
.openpet{ /*class added to contents of 1st demo when they are open*/
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 11px;
	color: #333333;
	height:22px;
	text-decoration: none;
	font-weight: bold;
}
.openlanguage{ /*class added to contents of 2nd demo when they are open*/
	font-weight: bold;
}
.closedlanguage{ /*class added to contents of 2nd demo when they are closed*/
font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 11px;
	color: #333333;
	height:22px;
	text-decoration: none;
}
.style2 {font-weight: bold}
.style3 {color: #CC0000}
</style>

<script type="text/javascript">

//Initialize first demo:
ddaccordion.init({
	headerclass: "mypets", //Shared CSS class name of headers group
	contentclass: "thepet", //Shared CSS class name of contents group
	collapseprev: true, //Collapse previous content (so only one open at any time)? true/false 
	defaultexpanded: [0], //index of content(s) open by default [index1, index2, etc]. [] denotes no content.
	animatedefault: false, //Should contents open by default be animated into view?
	persiststate: true, //persist state of opened contents within browser session?
	toggleclass: ["", "openpet"], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
	togglehtml: ["none", "", ""], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
	animatespeed: "fast" //speed of animation: "fast", "normal", or "slow"
})

//Initialize 2nd demo:
ddaccordion.init({
	headerclass: "technology", //Shared CSS class name of headers group
	contentclass: "thelanguage", //Shared CSS class name of contents group
	collapseprev: false, //Collapse previous content (so only one open at any time)? true/false 
	defaultexpanded: [], //index of content(s) open by default [index1, index2, etc]. [] denotes no content.
	animatedefault: false, //Should contents open by default be animated into view?
	persiststate: false, //persist state of opened contents within browser session?
	toggleclass: ["closedlanguage", "openlanguage"], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
	togglehtml: ["prefix", "<img src='../pages/common/css/default/images/plus.gif' align='left' vspace='3'  hspace='3' /> ", "<img src='../pages/common/css/default/images/minus.gif'align='left' vspace='3' hspace='3'  /> "], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
	animatespeed: "slow" //speed of animation: "fast", "normal", or "slow"
})

</script>

<s:form action="ApplicationForm" validate="true" id="paraFrm"
	theme="simple">
		
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" >
        <tr>
          <td colspan="3" valign="bottom" class="txt">&nbsp;</td>
        </tr>
        <tr>
          <td colspan="3" valign="bottom" background="../pages/common/css/default/images/lines.gif" class="txt"><img src="../pages/common/css/default/images/lines.gif" width="16" height="1" /></td>
        </tr>
        <tr>
          <td colspan="3" valign="bottom" class="txt"><img src="../pages/common/css/default/images/space.gif" width="5" height="5" /></td>
        </tr>
        <tr>
          <td width="4%" valign="bottom" class="txt"><strong class="formhead"><img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" /></strong></td>
          <td width="93%" class="txt"><strong class="formhead">Application Form </strong></td>
          <td width="3%" valign="top" class="txt"><div align="right"><img src="../pages/common/css/default/images/help.gif" width="16" height="16" /></div></td>
        </tr>
        <tr>
          <td height="5" colspan="3"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
        </tr>
        <tr>
          <td colspan="3"><table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td width="78%">&nbsp;
             </td>
              <td width="22%"><div align="right"><span class="style3">*</span>Indicates Required </div></td>
            </tr>
          </table>            <label></label></td>
        </tr>
        <tr>
          <td colspan="3"><img src="../pages/common/css/default/images/space.gif" width="5" height="4" /></td>
        </tr>
        <tr>
          <td colspan="3"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
              <tr>
                <td><table width="98%" border="0" align="center" cellpadding="0" cellspacing="2">
                  <tr>
                    <td colspan="5" class="formhead"><s:hidden name="applicationForm.appCode"/><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
                  </tr>
                  <tr>
                    <td width="24%" height="22" class="formtext">Post Applied :<span class="style3">*</span> </td>
                    <td width="34%" height="22"><s:hidden name="applicationForm.postCode"></s:hidden><label>
                      <s:textfield size="25" name="applicationForm.postName" readonly="true" size="30" maxlength="30"/>
					  <img src="../pages/common/css/default/images/search2.gif" width="16" height="15" onclick="javascript:callsF9(500,325,'ApplicationForm_f9Postaction.action');"/></label></td>
                    <td width="2%" height="22">&nbsp;</td>
                    <td width="12%" height="22" class="formtext">Job Code : </td>
                    <td width="28%" height="22">
                    <s:hidden name="applicationForm.jobCode"></s:hidden>
                    <s:textfield size="25" name="applicationForm.jobName" readonly="true" size="30" maxlength="30"/>
                     <img src="../pages/common/css/default/images/search2.gif" width="16" height="15" onclick="javascript:callsF9(500,325,'ApplicationForm_f9Jobaction.action');" /></td>
                  </tr>
                  
                  <tr>
                    <td colspan="5"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
                  </tr>
                  
                </table>                </td>
              </tr>
            </table>
            <img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
        </tr>
        <tr>
          <td colspan="3"><div class="technology">&nbsp;General Information </div>
<div class="thelanguage">
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
    <tr>
      <td><table width="98%" border="0" align="center" cellpadding="0" cellspacing="2" >
          
          <tr>
            <td class="formtext" ><table width="100%" border="0" cellpadding="0" cellspacing="0"  >
                <tr class="sortable">
                  <td ><table width="100%" border="0" cellpadding="0" cellspacing="0">
                      <tr>
                        <td colspan="2"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
                      </tr>
                      
                      <tr>
                        <td height="22" class="formtext">&nbsp;First Name :<span class="style3">*</span></td>
                        <td height="22">
                        <s:textfield size="30" name="applicationForm.firstName" onkeypress="return charactersOnly(this);" maxlength="30"/>
                       </td>
                        <td height="22">&nbsp;</td>
                        <td height="22" class="formtext">Last Name :<span class="style3">*</span> </td>
                        <td height="22">
                        <s:textfield size="30" name="applicationForm.lastName" onkeypress="return charactersOnly();" maxlength="30"/>
                        </td>
                      </tr>
                      <tr>
                       
                    <td width="24%" height="22" class="formtext">&nbsp;Gender :<span class="style3">*</span> </td>
                    <td width="26%" height="22">
                      <s:select name="applicationForm.gender" headerKey="" headerValue="-----Select Gender-----"
						list="#{'M':'Male','F':'Female','O':'Other'}" cssStyle="width:154"/>
                   </td>
                    <td width="3%" height="22">&nbsp;</td>
                    <td width="15%" height="22" class="formtext">Date of Birth :<span class="style3">*</span> </td>
                    <td width="32%" height="22">
                    <s:textfield size="25" name="applicationForm.birthDate" onkeypress="return numbersWithHiphen();" maxlength="10" onblur="validateDate('paraFrm_applicationForm_birthDate','Date of Birth')"/> 
                    <s:a href="javascript:NewCal('applicationForm.birthDate','DDMMYYYY');">
						<img src="../pages/common/css/default/images/Date.gif" width="16" height="16" />
					</s:a>
                    </td>
                      </tr>
                  </table></td>
                </tr>
            </table></td>
          </tr>
          <tr>
            <td colspan="2" class="formtext"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
          </tr>
      </table></td>
    </tr>
  </table>
<img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></div>

<div class="technology">&nbsp;Contact Details</div>
<div class="thelanguage">
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
    <tr>
      <td><table width="98%" border="0" align="center" cellpadding="0" cellspacing="2" >
          
          <tr>
            <td class="formtext" ><table width="100%" border="0" cellpadding="0" cellspacing="0"  >
                <tr class="sortable">
                  <td ><table width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td colspan="2"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
                    </tr>
                    <tr>
                      <td height="22" class="formtext">&nbsp;Address1 :</td>
                      <td height="22">
                      <s:textfield size="25"name="applicationForm.address1" 
					onblur="return checkBlankSpace(this);" maxlength="50" size="30" />
                    </td>
                      <td height="22">&nbsp;</td>
                      <td height="22" class="formtext">Email :<span class="style3">*</span> </td>
                      <td height="22">
                      <s:textfield size="30" name="applicationForm.emailName" maxlength="50" 
						onkeypress="return emailCheck();" onblur="return validateEmail('paraFrm_applicationForm_emailName');"/>
                     </td>
                    </tr>
                    <tr>
                      <td height="22" class="formtext">&nbsp;Address2 : </td>
                      <td height="22">
                      <s:textfield size="30"name="applicationForm.address2" maxlength="30" onblur="return checkBlankSpace(this);"/>
                      </td>
                      <td height="22">&nbsp;</td>
                      <td height="22" class="formtext">Mobile : </td>
                      <td height="22">
                      <s:textfield size="30" name="applicationForm.mobile" onkeypress="return validateMobile();" maxlength="30"/>
                     </td>
                    </tr>
                    <tr>
                      <td height="22" class="formtext">&nbsp;Address3 : </td>
                      <td height="22">
                      <s:textfield size="30" name="applicationForm.address3" maxlength="30" onblur="return checkBlankSpace(this);"/></td>
                      <td height="22">&nbsp;</td>
                      <td height="22" class="formtext">Phone No  :</td>
                      <td height="22"><s:textfield size="30" name="applicationForm.phoneNo" onkeypress="return validatePhoneNo();" 
						maxlength="30" onblur="return checkBlankSpace(this);"/></td>
                    </tr>
                    <tr>
                      <td height="22" class="formtext">&nbsp;City :</td>
                      <td height="22">
                      <s:textfield size="30" name="applicationForm.cityName" readonly="true"/>
					<s:hidden name="applicationForm.cityCode"/>
					<img src="../pages/common/css/default/images/search2.gif" height="18" align="absmiddle" width="18" theme="simple"
						onclick="javascript:callsF9(500,325,'ApplicationForm_f9Cityaction.action');" >
                          </td>
                      <td height="22">&nbsp;</td>
                      <td height="22" class="formtext">Pin Code :</td>
                      <td height="22"><s:textfield size="30" name="applicationForm.pinCode" onkeypress="return numbersOnly();" 
						onblur="return checkPincode('paraFrm_applicationForm_pinCode', 'Pin Code');" maxlength="6"/></td>
                    </tr>
                    <tr>
                      <td height="22" class="formtext">&nbsp;State :</td>
                      <td height="22"><s:hidden name="applicationForm.stateCode"/>
					<s:textfield name="applicationForm.state" size="30" readonly="true"/></td>
                      <td height="22">&nbsp;</td>
                      <td height="22" class="formtext">Country : </td>
                      <td height="22"><s:hidden name="applicationForm.countryCode"/>
					<s:textfield name="applicationForm.country" size="30" readonly="true"/></td>
                    </tr>
                    <tr>
                      <td height="10" class="formtext"></td>
                      <td></td>
                      <td ></td>
                      <td  class="formtext"></td>
                      <td></td>
                    </tr>
                    <tr>
                      <td width="24%" height="22" class="formtext"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td width="34%">&nbsp;Fresher</td>
                            <td width="66%"><s:if test="fresherFlag">
							<input type ="checkbox" name="fresher" checked="checked" id="fresher" value="fresher" onclick="callEducation(this);"/>
					</s:if>
					<s:else>
						<input type ="checkbox" name="fresher" value="fresher" id="fresher" onclick="callEducation(this);"/>
					</s:else></td>
                          </tr>
                      </table></td>
                      <td width="27%" height="22"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td width="34%">Experienced</td>
                            <td width="66%"><s:if test="expFlag">
							<input type ="checkbox" name="experience" checked="checked" id="experience" value="experience" onclick="callEducation(this);"/>
						</s:if>
						<s:else>
							<input type ="checkbox" name="experience" value="experience" id="experience" onclick="callEducation(this);"/>
						</s:else></td>
                          </tr>
                        </table>
                          <label></label></td>
                      <td width="3%" height="22">&nbsp;</td>
                      <td width="15%" height="22" class="formtext">&nbsp;</td>
                      <td width="31%" height="22">&nbsp;</td>
                    </tr>
                  </table></td>
                </tr>
            </table></td>
          </tr>
          <tr>
            <td colspan="2" class="formtext"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
          </tr>
      </table></td>
    </tr>
  </table>
<img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></div>

<div class="technology">&nbsp;Educational Qualification </div>
<div class="thelanguage">
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
    <tr>
      <td><table width="98%" border="0" align="center" cellpadding="0" cellspacing="2" >
        <tr>
          <td class="formtext" ><table width="100%" border="0" cellpadding="0" cellspacing="0"  >
              <tr class="sortable">
                <td ><table width="100%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td colspan="2"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
                  </tr>
                  <tr>
                    <td width="24%" height="22" class="formtext">&nbsp;Qualification Name :<span class="style3">*</span></td>
                    <td width="27%" height="22"><s:textfield size="30" name="applicationForm.qualificationName" readonly="true"/>
					<img src="../pages/images/search.gif" height="18" 
						onclick="javascript:callsF9(500,325,'ApplicationForm_f9Courseaction.action');" >
					<s:hidden name="applicationForm.qualificationCode"/></td>
                    <td width="3%" height="22">&nbsp;</td>
                    <td width="18%" height="22" class="formtext">Qualification Type :</td>
                    <td width="28%" height="22"><s:textfield size="30" name="applicationForm.qualificationType" readonly="true"/></td>
                  </tr>
                  <tr>
                    <td height="22" class="formtext">&nbsp;Year of Passing :<span class="style3">*</span> </td>
                    <td height="22"><s:textfield size="30" name="applicationForm.yearofPassing" onkeypress="return numbersOnly();" 
						maxlength="4" onblur="return checkYear('paraFrm_applicationForm_yearofPassing', 'Year of Passing');"/></td>
                    <td height="22">&nbsp;</td>
                    <td height="22" class="formtext">Percentage :<span class="style3">*</span> </td>
                    <td height="22"><s:textfield size="30" name="applicationForm.percentage" onkeypress="return numbersWithDot();" 
						onblur="return checkPercentage('paraFrm_applicationForm_percentage', 'Percentage');" maxlength="5"/></td>
                  </tr>
                  <tr>
                    <td height="22" class="formtext">&nbsp;College/School : </td>
                    <td height="22"><s:textfield size="30" name="applicationForm.college" maxlength="50" onblur="return checkBlankSpace(this);"/></td>
                    <td height="22">&nbsp;</td>
                    <td height="22" class="formtext">University/Board :</td>
                    <td height="22"><s:textfield size="30" name="applicationForm.university" maxlength="30" onblur="return checkBlankSpace(this);"/></td>
                  </tr>
                  <tr>
                    <td height="22" class="formtext">&nbsp;Specialization  :</td>
                    <td height="22"> <s:textfield size="30" name="applicationForm.specialization" maxlength="50" onblur="return checkBlankSpace(this);"/></td>
                    <td height="22">&nbsp;</td>
                    <td height="22" class="formtext"><s:submit cssClass="token" name="Add" value="Add Qualification" 
						action="ApplicationForm_addQualification" onclick="return addQualification();"/></td>
                    <td height="22">&nbsp;</td>
                  </tr>
                </table></td>
              </tr>
          </table></td>
        </tr>
        <tr>
          <td colspan="2" class="formtext"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" />  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
    <tr>
      <td><table width="100%" border="0" cellpadding="1" cellspacing="1"   class="sortable">
        <tr >
          <td width="12%" align="center" valign="top" class="formth" >Sr.No.</td>
          <td width="12%" align="center" valign="top" class="formth" >Qualification</td>
          <td width="15%" align="center" valign="top" class="formth" >Qualification Type</td>
          <td width="14%" align="center" valign="top" class="formth" >Year of Passing</td>
          <td width="12%" align="center" valign="top" class="formth" >Percentage</td>
          <td width="12%" align="center" valign="top" class="formth" >College</td>
          <td width="12%" align="center" valign="top" class="formth" >University</td>
          <td width="12%" align="center" valign="top" class="formth" >Specialization</td>
        </tr>
        <%int i=1; %>
        
        
        <tr class="border2">
          <td class="border2">&nbsp;</td>
          <td class="border2">&nbsp;</td>
          <td class="border2">&nbsp;</td>
          <td class="border2">&nbsp;</td>
          <td class="border2">&nbsp;</td>
          <td class="border2">&nbsp;</td>
          <td class="border2">&nbsp;</td>
          <td class="border2">&nbsp;</td>
        </tr>
        
        <s:iterator value="qualificationList" status="stat">
							<tr>
								<td class="bodycell"><%=i %>
								<td class="bodycell"><s:property value="qualificationName"/></td>
								<td class="bodycell"><s:property value="qualificationType"/></td>
								<td class="bodycell"><s:property value="yearofPassing"/></td>
								<td class="bodycell"><s:property value="percentage"/></td>
								<td class="bodycell"><s:property value="college"/></td>
								<td class="bodycell"><s:property value="university"/></td>
								<td class="bodycell"><s:property value="specialization"/></td>
							</tr>
							<%i++; %>
						</s:iterator>
      </table></td>
    </tr>
  </table></td>
        </tr>
      </table></td>
    </tr>
  </table>
<img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></div>
<div id="experinced">
<div class="technology">&nbsp;Professional Experience</div>
<div class="thelanguage">
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
  <tr>
    <td><table width="98%" border="0" align="center" cellpadding="0" cellspacing="2" >
      <tr>
        <td class="formtext" ><table width="100%" border="0" cellpadding="0" cellspacing="0"  >
          <tr class="sortable">
            <td ><table width="100%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td colspan="2"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
              </tr>
              <tr>
                <td width="24%" height="22" class="formtext">&nbsp;Total Years of Experience :<span class="style3">*</span></td>
                <td width="27%" height="22"><s:select name="applicationForm.totExpYear" headerKey="" headerValue="----Year----" cssStyle="width:100"
										list="#{'0':'0','1':'1','2':'2 ','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','+10':'+10'}" />
                          <s:select name="applicationForm.totExpMonth" headerKey="" headerValue="----Month----" cssStyle="width:100"
										list="#{'0':'0','1':'1','2':'2 ','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10'}" /></td>
                <td width="3%" height="22">&nbsp;</td>
                <td width="15%" height="22" class="formtext">&nbsp;</td>
                <td width="31%" height="22">&nbsp;</td>
              </tr>
              <tr>
                <td height="22" class="formtext">&nbsp;Current Industry : </td>
                <td height="22"><s:textfield name="applicationForm.currentIndustry" size="30" maxlength="50" onblur="return checkBlankSpace(this);"/></td>
                <td height="22">&nbsp;</td>
                <td height="22" class="formtext">&nbsp;</td>
                <td height="22">&nbsp;</td>
              </tr>
              <tr>
                <td height="22" class="formtext">&nbsp;Current Functional Area : </td>
                <td height="22"><s:textfield size="30" name="applicationForm.currentFunArea" maxlength="30" onblur="return checkBlankSpace(this);"/></td>
                <td height="22">&nbsp;</td>
                <td height="22" class="formtext">&nbsp;</td>
                <td height="22">&nbsp;</td>
              </tr>
              <tr>
                <td height="22" class="formtext">&nbsp;Current Job Role :</td>
                <td height="22"><s:textfield size="30" name="applicationForm.currentJobRole" maxlength="40" onblur="return checkBlankSpace(this);"/>
                          </td>
                <td height="22">&nbsp;</td>
                <td height="22" class="formtext">&nbsp;</td>
                <td height="22">&nbsp;</td>
              </tr>
              <tr>
                <td height="22" class="formtext">&nbsp;Key Skills :</td>
                <td height="22"><s:textfield size="30" name="applicationForm.keySkill" maxlength="50" onblur="return checkBlankSpace(this);"/></td>
                <td height="22">&nbsp;</td>
                <td height="22" class="formtext">&nbsp;</td>
                <td height="22">&nbsp;</td>
              </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td colspan="2" class="formtext"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</div>
</div>
<div class="technology">&nbsp;Resume Details </div>
<div class="thelanguage">
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
  <tr>
    <td><table width="98%" border="0" align="center" cellpadding="0" cellspacing="2" >
      <tr>
        <td class="formtext" ><table width="100%" border="0" cellpadding="0" cellspacing="0"  >
          <tr class="sortable">
            <td ><table width="100%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td colspan="2"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
              </tr>
              <tr>
                <td width="25%" height="22" class="formtext">&nbsp;Upload Resume: : </td>
                <td width="36%" height="22"><s:textfield size="30" name="applicationForm.uploadFileName" readonly="true" />
                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  <input type="button" class="pagebutton" name="Browse" value="Browse" onclick="uploadFile('applicationForm.uploadFileName');"/></td>
                <td width="1%" height="22">&nbsp;</td>
                <td width="8%" height="22" class="formtext">&nbsp;</td>
                <td width="30%" height="22">&nbsp;</td>
              </tr>
              <tr>
                <td height="22" class="formtext">&nbsp;Copy and Paste Resume  : </td>
                <td height="22"><s:textarea cols="60" rows="5" name="applicationForm.resumePaste" onblur="return checkBlankSpace(this);"/></td>
                <td height="22">&nbsp;</td>
                <td height="22" class="formtext">&nbsp;</td>
                <td height="22">&nbsp;</td>
              </tr>
              <tr>
                <td height="22" class="formtext">&nbsp;</td>
                <td height="22"><s:submit  cssClass="pagebutton"  value="Submit" 
					onclick="return fromValidate();" /></td>
                <td height="22">&nbsp;</td>
                <td height="22" class="formtext">&nbsp;</td>
                <td height="22">&nbsp;</td>
              </tr>
              <tr>
                <td height="6" class="formtext"></td>
                <td ></td>
                <td></td>
                <td class="formtext"></td>
                <td ></td>
              </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td colspan="2" class="formtext"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
      </tr>
    </table></td>
  </tr>

</table>	
		
</div>

</td>

</tr>

</table>
</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js">

</script>
<script type="text/javascript">

function uploadFile(fieldName) {
	
	var path="Logo";
		window.open('../pages/common/multipleUploadFile.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
}

function saveFile(){
	alert("Your Resume Uploaded Successfully ! ");
}

function callEducation(obj){
	var selectedCheckBox = obj.value;
	if(selectedCheckBox=="fresher"){
		document.getElementById("experience").checked = '';
		document.getElementById("experinced").style.display = 'none';
	}if(selectedCheckBox=="experience"){
		document.getElementById("fresher").checked = '';
		document.getElementById("experinced").style.display = '';
	}
}

function fromValidate(){
	//var fieldNames = ["paraFrm_applicationForm_postName", "paraFrm_applicationForm_firstName", "paraFrm_applicationForm_lastName", 
	//				  "paraFrm_applicationForm_gender", "paraFrm_applicationForm_birthDate", "paraFrm_applicationForm_emailName"];
	//var labelNames = ["Post Applied", "First Name", "Last Name", "Gender", "Birth Date", "Email Id"];
	//if(!checkMandatory(fieldNames, labelNames)){
	//	return false;
	//}if(!validateDate("paraFrm_applicationForm_birthDate", "Birth Date")){
	//	return false;
	//}
	if(document.getElementById('paraFrm_applicationForm_postName').value=="")
	{alert('Please Select Post Applied');
	return false;
	}
	if(document.getElementById('paraFrm_applicationForm_firstName').value=="")
	{alert('Please Enter First Name');
	return false;
	}	
	if(document.getElementById('paraFrm_applicationForm_lastName').value=="")
	{alert('Please Enter Last Name');
	return false;
	}
	if(document.getElementById('paraFrm_applicationForm_gender').value=="")
	{alert('Please Select Gender');
	return false;
	}
	if(document.getElementById('paraFrm_applicationForm_birthDate').value=="")
	{alert('Please Enter Birth Date');
	return false;
	}
	if(document.getElementById('paraFrm_applicationForm_emailName').value=="")
	{alert('Please Enter Email Id');
	return false;
	}
	
	if(!howLong('paraFrm_applicationForm_birthDate'))
	{return false;}
	
	if(document.getElementById("paraFrm_applicationForm_mobile").value=="" && document.getElementById("paraFrm_applicationForm_phoneNo").value==""){
		alert("Please enter either mobile no or phone no");
		//document.getElementById("paraFrm_applicationForm_mobile").focus();
		return false;
	}if(document.getElementById("fresher").checked==false  && document.getElementById("experience").checked==false){
		alert("Please select a checkbox");
		//document.getElementById("fresher").focus();
		return false;
	}if(document.getElementById("paraFrm_applicationForm_appCode").value==""){
		alert("Please Add Educational Qualification Details By Pressing Add Qualification Button");
		//document.getElementById("applicationForm.qualificationName").focus();
		return false;
	}
	if(document.getElementById("experience").checked==true){
		var fieldsName = ["paraFrm_applicationForm_totExpYear", "paraFrm_applicationForm_totExpMonth"];
		var labelsName = ["Total Experience Year", "Total Experience Month"];
		if(!checkMandatory(fieldsName, labelsName))return false;
	}
	//document.getElementById('parafrm').target = '_blank';
	//document.getElementById('parafrm').target = 'main';
	document.getElementById('parafrm').action = "ApplicationForm_save.action";
	document.getElementById('parafrm').submit(); 
	return true;
}

function addQualification(){

	//if(!howLong("paraFrm_applicationForm_birthDate"))return false;
	if(document.getElementById("fresher").checked==false  && document.getElementById("experience").checked==false)
	{
		alert("Please select a checkbox");
		//document.getElementById("fresher").focus();
		return false;
	}
	if(document.getElementById('paraFrm_applicationForm_postName').value=="")
	{alert('Please Select Post Applied');
	return false;
	}
	if(document.getElementById('paraFrm_applicationForm_firstName').value=="")
	{alert('Please Enter First Name');
	return false;
	}
	
	
	if(document.getElementById('paraFrm_applicationForm_lastName').value=="")
	{alert('Please Enter Last Name');
	return false;
	}
	if(document.getElementById('paraFrm_applicationForm_gender').value=="")
	{alert('Please Select Gender');
	return false;
	}
	if(document.getElementById('paraFrm_applicationForm_birthDate').value=="")
	{alert('Please Enter Birth Date');
	return false;
	}
	if(document.getElementById('paraFrm_applicationForm_emailName').value=="")
	{alert('Please Enter Email Id');
	return false;
	}
	
	var fieldNames1 = [ "paraFrm_applicationForm_qualificationName", "paraFrm_applicationForm_yearofPassing", "paraFrm_applicationForm_percentage"];
	var labelNmaes1 = ["Qualification Name", "Year of Passing", "Percenage"];
	if(!checkMandatory(fieldNames1, labelNmaes1)){
		return false;
	}
	return true;
}
</script>