<%@ taglib prefix="s" uri="/struts-tags"%>
<script language="JavaScript" type="text/javascript"
	src="..pages/common/include/javascript/sorttable.js"></script>

<script type="text/javascript"
	src="../pages/common/include/javascript/jquery-1.2.2.pack.js"></script>

<script type="text/javascript"
	src="../pages/common/include/javascript/ddaccordion.js"></script>
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
	togglehtml: ["prefix", "<img src='../pages/common/css/default/images/plus.gif'align='left' vspace='3'  hspace='3' /> ", "<img src='../pages/common/css/default/images/minus.gif'align='left' vspace='3' hspace='3'  /> "], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
	animatespeed: "slow" //speed of animation: "fast", "normal", or "slow"
})

</script>
<s:form action="WeekPlanner" id="paraFrm" theme="simple">
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
          <td width="3%" valign="bottom" class="txt"><strong class="formhead"><img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" /></strong></td>
          <td width="94%" class="txt"><strong class="formhead">Vacancy List </strong></td>
          <td width="3%" valign="top" class="txt"><div align="right"><img src="../pages/common/css/default/images/help.gif" width="16" height="16" /></div></td>
        </tr>
        <tr>
          <td height="5" colspan="3"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
        </tr>
        <tr>
          <td colspan="3"><label><img src="../pages/common/css/default/images/space.gif" width="5" height="9"   /></label></td>
        </tr>
        <tr>
          <td colspan="3"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg"  >
            <tr class="sortable">
              <td ><table width="100%" height="22" border="0" align="center" cellpadding="1" cellspacing="1"   >
                  <tr >
                    <td width="29%" valign="top" class="formth" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sr.No</td>
                    <td width="41%" valign="top" class="formth" >&nbsp;&nbsp;Designation</td>
                    <td width="30%" valign="top" class="formth" >&nbsp;&nbsp;Status</td>
                  </tr>
              </table></td>
            </tr>
          </table></td>
        </tr>
        <tr>
          <td colspan="3"><img src="../pages/common/css/default/images/space.gif" width="5" height="4" /></td>
        </tr>
        
        <tr>
          <td colspan="3" valign="top"><div class="technology">
            <table width="95%" border="0" align="center" cellpadding="1" cellspacing="1"   >
              <tr >
                <td width="28%" valign="top"  >1</td>
                <td width="43%" valign="top"  >Marketing Executive </td>
                <td width="21%" valign="top" >Open</td>
                <td width="8%" valign="top" ><div align="right"><img src="../pages/common/css/default/images/Printer.gif" width="16" height="16" />&nbsp;</div></td>
              </tr>
            </table>
          </div>
          <div class="thelanguage">
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
    <tr>
      <td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="2" >
          
          <tr>
            <td class="formtext" ><table width="100%" border="0" cellpadding="0" cellspacing="0">

                <tr>
                  <td width="21%" height="22" bgcolor="#F2F7FD">Requisition No. :</td>
                  <td width="2%" bgcolor="#FFFFFF">&nbsp;</td>
                  <td width="77%" height="22" bgcolor="#FFFFFF">1005</td>
                </tr>
                <tr>
                  <td height="22" bgcolor="#F2F7FD">Division :</td>
                  <td bgcolor="#FFFFFF">&nbsp;</td>
                  <td height="22" bgcolor="#FFFFFF">Glodyne Technoserve Limited </td>
                </tr>
                <tr>
                  <td height="22" bgcolor="#F2F7FD">Branch :</td>
                  <td bgcolor="#FFFFFF">&nbsp;</td>
                  <td height="22" bgcolor="#FFFFFF">Bandra</td>
                </tr>
                <tr>
                  <td height="22" bgcolor="#F2F7FD">Department :</td>
                  <td bgcolor="#FFFFFF">&nbsp;</td>
                  <td height="22" bgcolor="#FFFFFF">Finance</td>
                </tr>
                <tr>
                  <td height="22" bgcolor="#F2F7FD">Designation :</td>
                  <td bgcolor="#FFFFFF">&nbsp;</td>
                  <td height="22" bgcolor="#FFFFFF">Sr. Marketing Executive </td>
                </tr>
                <tr>
                  <td height="22" valign="top" bgcolor="#F2F7FD">Job Description :</td>
                  <td bgcolor="#FFFFFF">&nbsp;</td>
                  <td height="22" bgcolor="#FFFFFF">Candidate should have sales or marketing experience in Insurance, Home Loan, real estates etc. <br />
                    <p>To make house calls, to meet prospective students &amp; their parents.</p>
                    <p>To work as per targets. <br />
                    </p></td>
                </tr>
                <tr>
                  <td height="22" bgcolor="#F2F7FD">Skills :</td>
                  <td bgcolor="#FFFFFF">&nbsp;</td>
                  <td height="22" bgcolor="#FFFFFF">Communication skills and pleasing personality. </td>
                </tr>
                <tr>
                  <td height="22" bgcolor="#F2F7FD">Qualification :</td>
                  <td bgcolor="#FFFFFF">&nbsp;</td>
                  <td height="22" bgcolor="#FFFFFF">Graduate/ Post graduate preferably with Diploma in Marketing </td>
                </tr>
                <tr>
                  <td height="22" bgcolor="#F2F7FD">Desired Qualities :</td>
                  <td bgcolor="#FFFFFF">&nbsp;</td>
                  <td height="22" bgcolor="#FFFFFF">Marketing with good communication skills and pleasing personality. </td>
                </tr>
                <tr>
                  <td height="22" bgcolor="#F2F7FD">Experience :</td>
                  <td bgcolor="#FFFFFF">&nbsp;</td>
                  <td height="22" bgcolor="#FFFFFF">2 To 5 Yrs </td>
                </tr>
                <tr>
                  <td height="22" bgcolor="#F2F7FD">Status :</td>
                  <td bgcolor="#FFFFFF">&nbsp;</td>
                  <td height="22" bgcolor="#FFFFFF">Open</td>
                </tr>
              </table>              </td>
          </tr>
          
      </table></td>
    </tr>
  </table>
<img src="../pages/common/css/default/images/space.gif" width="5" height="4" /></div>

<div class="technology">
  <table width="95%" border="0" align="center" cellpadding="1" cellspacing="1"   >
    <tr >
      <td width="28%" valign="top"  >2</td>
      <td width="43%" valign="top"  >BDM </td>
      <td width="21%" valign="top" >Open</td>
      <td width="8%" valign="top" ><div align="right"><img src="../pages/common/css/default/images/Printer.gif" width="16" height="16" />&nbsp;</div></td>
    </tr>
  </table>
</div>
<div class="thelanguage">
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
    <tr>
      <td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="2" >
          
          <tr>
            <td class="formtext" ><table width="100%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="21%" height="22" bgcolor="#F2F7FD">Requisition No. :</td>
                <td width="2%" bgcolor="#FFFFFF">&nbsp;</td>
                <td width="77%" height="22" bgcolor="#FFFFFF">1005</td>
              </tr>
              <tr>
                <td height="22" bgcolor="#F2F7FD">Division :</td>
                <td bgcolor="#FFFFFF">&nbsp;</td>
                <td height="22" bgcolor="#FFFFFF">Glodyne Technoserve Limited </td>
              </tr>
              <tr>
                <td height="22" bgcolor="#F2F7FD">Branch :</td>
                <td bgcolor="#FFFFFF">&nbsp;</td>
                <td height="22" bgcolor="#FFFFFF">Bandra</td>
              </tr>
              <tr>
                <td height="22" bgcolor="#F2F7FD">Department :</td>
                <td bgcolor="#FFFFFF">&nbsp;</td>
                <td height="22" bgcolor="#FFFFFF">Finance</td>
              </tr>
              <tr>
                <td height="22" bgcolor="#F2F7FD">Designation :</td>
                <td bgcolor="#FFFFFF">&nbsp;</td>
                <td height="22" bgcolor="#FFFFFF">Sr. Marketing Executive </td>
              </tr>
              <tr>
                <td height="22" valign="top" bgcolor="#F2F7FD">Job Description :</td>
                <td bgcolor="#FFFFFF">&nbsp;</td>
                <td height="22" bgcolor="#FFFFFF">Candidate should have sales or marketing experience in Insurance, Home Loan, real estates etc. <br />
                    <p>To make house calls, to meet prospective students &amp; their parents.</p>
                  <p>To work as per targets. <br />
                  </p></td>
              </tr>
              <tr>
                <td height="22" bgcolor="#F2F7FD">Skills :</td>
                <td bgcolor="#FFFFFF">&nbsp;</td>
                <td height="22" bgcolor="#FFFFFF">Communication skills and pleasing personality. </td>
              </tr>
              <tr>
                <td height="22" bgcolor="#F2F7FD">Qualification :</td>
                <td bgcolor="#FFFFFF">&nbsp;</td>
                <td height="22" bgcolor="#FFFFFF">Graduate/ Post graduate preferably with Diploma in Marketing </td>
              </tr>
              <tr>
                <td height="22" bgcolor="#F2F7FD">Desired Qualities :</td>
                <td bgcolor="#FFFFFF">&nbsp;</td>
                <td height="22" bgcolor="#FFFFFF">Marketing with good communication skills and pleasing personality. </td>
              </tr>
              <tr>
                <td height="22" bgcolor="#F2F7FD">Experience :</td>
                <td bgcolor="#FFFFFF">&nbsp;</td>
                <td height="22" bgcolor="#FFFFFF">2 To 5 Yrs </td>
              </tr>
              <tr>
                <td height="22" bgcolor="#F2F7FD">Status :</td>
                <td bgcolor="#FFFFFF">&nbsp;</td>
                <td height="22" bgcolor="#FFFFFF">Open</td>
              </tr>
            </table></td>
          </tr>
          
      </table></td>
    </tr>
  </table>
<img src="../pages/common/css/default/images/space.gif" width="5" height="4" /></div>

<div class="technology">
  <table width="95%" border="0" align="center" cellpadding="1" cellspacing="1"   >
    <tr >
      <td width="28%" valign="top"  >3</td>
      <td width="43%" valign="top"  >VP </td>
      <td width="21%" valign="top" >Closed</td>
      <td width="8%" valign="top" ><div align="right"><img src="../pages/common/css/default/images/Printer.gif" width="16" height="16" />&nbsp;</div></td>
    </tr>
  </table>
</div>
<div class="thelanguage">
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
    <tr>
      <td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="2" >
        <tr>
          <td class="formtext" ><table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td width="21%" height="22" bgcolor="#F2F7FD">Requisition No. :</td>
              <td width="2%" bgcolor="#FFFFFF">&nbsp;</td>
              <td width="77%" height="22" bgcolor="#FFFFFF">1005</td>
            </tr>
            <tr>
              <td height="22" bgcolor="#F2F7FD">Division :</td>
              <td bgcolor="#FFFFFF">&nbsp;</td>
              <td height="22" bgcolor="#FFFFFF">Glodyne Technoserve Limited </td>
            </tr>
            <tr>
              <td height="22" bgcolor="#F2F7FD">Branch :</td>
              <td bgcolor="#FFFFFF">&nbsp;</td>
              <td height="22" bgcolor="#FFFFFF">Bandra</td>
            </tr>
            <tr>
              <td height="22" bgcolor="#F2F7FD">Department :</td>
              <td bgcolor="#FFFFFF">&nbsp;</td>
              <td height="22" bgcolor="#FFFFFF">Finance</td>
            </tr>
            <tr>
              <td height="22" bgcolor="#F2F7FD">Designation :</td>
              <td bgcolor="#FFFFFF">&nbsp;</td>
              <td height="22" bgcolor="#FFFFFF">Sr. Marketing Executive </td>
            </tr>
            <tr>
              <td height="22" valign="top" bgcolor="#F2F7FD">Job Description :</td>
              <td bgcolor="#FFFFFF">&nbsp;</td>
              <td height="22" bgcolor="#FFFFFF">Candidate should have sales or marketing experience in Insurance, Home Loan, real estates etc. <br />
                  <p>To make house calls, to meet prospective students &amp; their parents.</p>
                <p>To work as per targets. <br />
                </p></td>
            </tr>
            <tr>
              <td height="22" bgcolor="#F2F7FD">Skills :</td>
              <td bgcolor="#FFFFFF">&nbsp;</td>
              <td height="22" bgcolor="#FFFFFF">Communication skills and pleasing personality. </td>
            </tr>
            <tr>
              <td height="22" bgcolor="#F2F7FD">Qualification :</td>
              <td bgcolor="#FFFFFF">&nbsp;</td>
              <td height="22" bgcolor="#FFFFFF">Graduate/ Post graduate preferably with Diploma in Marketing </td>
            </tr>
            <tr>
              <td height="22" bgcolor="#F2F7FD">Desired Qualities :</td>
              <td bgcolor="#FFFFFF">&nbsp;</td>
              <td height="22" bgcolor="#FFFFFF">Marketing with good communication skills and pleasing personality. </td>
            </tr>
            <tr>
              <td height="22" bgcolor="#F2F7FD">Experience :</td>
              <td bgcolor="#FFFFFF">&nbsp;</td>
              <td height="22" bgcolor="#FFFFFF">2 To 5 Yrs </td>
            </tr>
            <tr>
              <td height="22" bgcolor="#F2F7FD">Status :</td>
              <td bgcolor="#FFFFFF">&nbsp;</td>
              <td height="22" bgcolor="#FFFFFF">Open</td>
            </tr>
          </table></td>
        </tr>
        
      </table></td>
    </tr>
  </table>
</div>

<div class="thelanguage"> </div></td>
        </tr>
        
        <tr>
          <td colspan="3"><img src="../pages/common/css/default/images/space.gif" width="5" height="4"  /></td>
        </tr>
  </table>

</s:form>