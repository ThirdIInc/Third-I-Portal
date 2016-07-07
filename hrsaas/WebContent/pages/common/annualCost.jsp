<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri='/WEB-INF/cewolf-1.1.tld' prefix='cewolf'%>
<table width="100%" >

	<tr>
		<td>
		<div  >
		<table>
			<tr>
				<td><cewolf:chart id="verticalBar3D1" title=""
					type="verticalBar3D" xaxislabel="Cost Center" yaxislabel="Cost" background="bighead.gif">
					<cewolf:gradientpaint >
						<cewolf:point x="0" y="0"  />
					</cewolf:gradientpaint>
					<cewolf:data>
						<cewolf:producer id="categoryData" />
					</cewolf:data>
					<cewolf:chartpostprocessor id="meterRanges">
					</cewolf:chartpostprocessor>
				</cewolf:chart> <cewolf:img chartid="verticalBar3D1" renderer="/cewolf" width="450"
					height="225" />
					
					
					<%--<cewolf:chart id="areaChart" title="Area" type="area" xaxislabel="Fruit" yaxislabel="favorite" >
					
    <cewolf:data>
        <cewolf:producer id="categoryData" />
       
    </cewolf:data>
    
    
</cewolf:chart>
<cewolf:img chartid="areaChart" renderer="/cewolf" width="300" height="300"/>
--%>
					</td>
			</tr>
		</table>
		</div>
		</td>
	</tr>
</table>
