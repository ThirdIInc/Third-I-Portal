
<%@taglib uri='/WEB-INF/cewolf-1.1.tld' prefix='cewolf'%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<table width="100%" align="center" cellpadding="0" cellspacing="0">

	<tr>
		<td><cewolf:chart id="pie3d" title="" type="pie3D"
			legendanchor="west" showlegend="true">
			<cewolf:gradientpaint>
				<cewolf:point x="0" y="0" color="#FFFFFF" />
			</cewolf:gradientpaint>

			<cewolf:data>
				<cewolf:producer id="pieData" />
			</cewolf:data>

			<cewolf:chartpostprocessor id="meterRanges">
			</cewolf:chartpostprocessor>

		</cewolf:chart> <s:if test="%{adHm.vasFlag}">
			<cewolf:img chartid="pie3d" renderer="/cewolf" width="240"
				height="170"></cewolf:img>
		</s:if> <s:else>
			<cewolf:img chartid="pie3d" renderer="/cewolf" width="300"
				height="170"></cewolf:img>
		</s:else></td>

		<td>
		<table>
			<tr>
				<td><s:select name="assetCategory" id="asstCt"
					cssStyle="width:100" headerKey="" size="1" list="assetmap"
					onchange="getAssetCategory();" /></td>


				<td><s:select name="assetType" id="asstTypeId"
					cssStyle="width:100" size="1" list="assetmap1"
					onchange="getAssetSubtype();" /></td>
			</tr>
		</table>
		</td>


	</tr>


</table>







