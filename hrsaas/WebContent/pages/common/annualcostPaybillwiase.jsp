<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri='/WEB-INF/cewolf-1.1.tld' prefix='cewolf'%>
<table width="100%">

	<tr>
		<td>
		<div >
		<table bgcolor="#FFFFFF">
			<tr>
				<td><cewolf:chart id="verticalBar3D1" title=""
					type="verticalBar3D" xaxislabel="Cost Center" yaxislabel="Cost">
					<cewolf:gradientpaint>
						<cewolf:point x="0" y="0" color="#FFFFFF" />
					</cewolf:gradientpaint>
					<cewolf:data>
						<cewolf:producer id="categoryData1" />
					</cewolf:data>
				</cewolf:chart> <cewolf:img chartid="verticalBar3D1" renderer="/cewolf" width="650"
					height="225" /></td>
			</tr>
		</table>
		</div>
		</td>
	</tr>
</table>
