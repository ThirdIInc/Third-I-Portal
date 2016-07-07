<%@ taglib prefix="s" uri="/struts-tags"%>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr valign="middle">
		<td nowrap="nowrap" valign="middle" align="left"
			style='height: 20px; vertical-align: middle;padding: 0;margin: 0'>
			
			<a href="#" onmouseover="show(this,ds1);"><img
				src="../pages/images/buttonIcons/email.png" class="iconImage"
				align="absmiddle" " title="PDF" style="cursor: inherit;padding-right: 5px;"/>Mail Report </a>
		</td>
		<td nowrap="nowrap">&nbsp;&nbsp; <a value='Preview' href="#"
			onclick="callReport('HTML');"> <img
			src="../pages/images/buttonIcons/Preview.png" class="iconImage"
			align="absmiddle" title="Preview"><span id='previewDivBottom'
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