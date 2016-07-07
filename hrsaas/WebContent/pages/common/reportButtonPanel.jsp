<%@ taglib prefix="s" uri="/struts-tags"%>

<STYLE>
.submenu {position:absolute;background-color:#fff;width:85;}
</STYLE>

<SCRIPT>
var cm=null;
document.onclick = new Function("show(null)");
function getPos(el,sProp) {
	var iPos = 0;
	while (el!=null) {
		iPos+=el["offset" + sProp];
		el = el.offsetParent;
	}
	return iPos;
}

function show(el,m) {
	if (m) {
		m.style.display='block';
		m.style.pixelLeft = getPos(el,"Left");
		m.style.pixelTop = getPos(el,"Top") + el.offsetHeight;
	}
	if ((m!=cm) && (cm)) cm.style.display='none';
	cm=m;
}
</SCRIPT>

<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr valign="middle">

		<td nowrap="nowrap" valign="middle" align="left"
			style='height: 20px; vertical-align: middle;padding: 0;margin: 0'>
			
			<a href="#" onmouseover="show(this,document.getElementById('ds1'));"><img
				src="../pages/images/buttonIcons/email.png" class="iconImage"
				align="absmiddle" " title="PDF" style="cursor: inherit;padding-right: 5px;"/>Mail Report </a>
				
				<div ID="ds1" CLASS="submenu" STYLE="display:none">
				<table border="1" bordercolor="#cccccc" cellspacing="0" cellpadding="2" width="100%" style="border-collapse: collapse;"><tr><td><a href="#" onclick="document.getElementById('paraFrm').target = 'main';mailReportFun('PDF');"><img
					src="../pages/images/buttonIcons/file-pdf.png" class="iconImage"
					align="absmiddle" " title="PDF" style="padding-right: 5px;">PDF</a></td></tr>
				<tr><td><a href="#"
					onclick="mailReportFun('Xls');"><img
					src="../pages/images/buttonIcons/file-xls.png" class="iconImage"
					align="absmiddle" " title="PDF" style="padding-right: 5px;">Excel</a></td></tr>
				</table>
				</div>
		</td>
		<td nowrap="nowrap">&nbsp;&nbsp; <a value='Preview' href="#"
			onclick="callReport('HTML');"> <img
			src="../pages/images/buttonIcons/Preview.png" class="iconImage"
			align="absmiddle" title="Preview"><span id='previewDiv'
			style="padding-left: 5px;">Preview</span></a></td>
		<td width='100%' align="right"><b>Export :</b>&nbsp;</td>


		<td nowrap="nowrap"><a href="#" onclick="callReport('Pdf');">
		<img src="../pages/images/buttonIcons/file-pdf.png" class="iconImage"
			align="absmiddle" " title="PDF"><span
			style="padding-left: 5px;">Pdf</span></a>&nbsp;&nbsp;</td>
		<td nowrap="nowrap"><a href="#" onclick="callReport('Xls');">
		<img src="../pages/images/buttonIcons/file-xls.png" class="iconImage"
			align="absmiddle" onclick="callReport('Xls');" title="Excel"><span
			style="padding-left: 5px;">Excel</span></a> &nbsp;&nbsp;</td>
	</tr>

</table>
<s:hidden name="inputAction" />
<script>
function callReportCommon(type){
				if(type=="HTML"){
				if(document.getElementById('previewDiv').innerHTML=='Preview'){
				//	document.getElementById('reportDiv').style.display='block';
				//	document.getElementById('reportBodyTable').style.display='none';
				//	document.getElementById('paraFrm').target = 'htmlReport';
				//	document.getElementById('previewDiv').innerHTML='Close Preview';
					document.getElementById('paraFrm').action=document.getElementById('paraFrm_reportAction').value;
					var height=window.screen.height-200;
					var width=window.screen.width-50;
					var previewWin=window.open('../pages/common/loading.jsp','previewWin','toolbar=no,titlebar=no,menubar=no,scrollbars=yes,location=no,directories=no,channelmode=no,width='+width+',height='+height+',top=100,left=25');
					document.getElementById('paraFrm').target='previewWin';
					document.getElementById('paraFrm').submit();
					}
					else{
						document.getElementById('reportDiv').style.display='none';
						document.getElementById('reportBodyTable').style.display='block';
						document.getElementById('paraFrm').target = 'main';
						document.getElementById('previewDiv').innerHTML='Preview';
					}
				}else{
					document.getElementById('paraFrm').target = 'main';
					document.getElementById('paraFrm').action=document.getElementById('paraFrm_reportAction').value;
					document.getElementById('paraFrm').submit();
				}
				document.getElementById('paraFrm').target = '';
	}
</script>