<%@include file="header.jsp"%>

<div class="container">
 <c:if test="${!empty skillEmpList}">

  <input type="hidden" id="pas" value='${skillEmpList}'/>

<div class="row">Skill vs employee count</div>
	<div id="skillChartdiv1" class="chartdivStyle" style="display:block !important;"></div>
	</c:if>
	 <c:if test="${empty skillEmpList}">
	 No Data Available
	 </c:if>
	
</div>
<%@include file="footer.jsp"%>

  <script type="text/javascript">
    var data = $('#jsonBom').val();
   </script>  

<script src="${pageContext.servletContext.contextPath}/resources/js/amcharts.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/js/serial.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/js/light.js"></script> 
<style>
#skillChartdiv1
{
width : 100%;
height : 450px;
font-size : 11px;
}
</style>
<script type="text/javascript">

  $(document).ready(function(){

  var data1 = $('#pas').val();
   var result = JSON.parse(data1);

var chart = AmCharts.makeChart( "skillChartdiv1", {

  "type": "serial",
  "theme": "light",

  "dataProvider":result,
  "gridAboveGraphs": true,
  "startDuration": 1,
  "graphs": [ {
    "balloonText": "[[category]]: <b>[[value]]</b>",
    "fillAlphas": 0.8,
    "lineAlpha": 0.2,
    "type": "column",
    "valueField": "numberOfEmp"
  } ],
  "chartCursor": {
    "categoryBalloonEnabled": false,
    "cursorAlpha": 0,
    "zoomable": false
  },
  "categoryField": "skillName",
  "categoryAxis": {
    "gridPosition": "start",
    "gridAlpha": 0,
    "tickPosition": "start",
    "tickLength": 10
  },
  "export": {
    "enabled": true
  }
} );
//add click listener
chart.addListener("clickGraphItem", handleClick);
function handleClick(event)
{
/* alert(event.item.category + ": " + event.item.values.value);
 */
 location.href="${pageContext.servletContext.contextPath}/employeesListBySkill/"+event.item.category;
}

  });
</script>

