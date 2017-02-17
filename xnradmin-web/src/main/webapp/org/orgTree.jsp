<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

request.setAttribute("basePath",basePath);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>组织结构图</title>
    <link rel="stylesheet" href="${basePath}/css/jorgchart/bootstrap.min.css"/>
    <link rel="stylesheet" href="${basePath}/css/jorgchart/jquery.jOrgChart.css"/>
    <link rel="stylesheet" href="${basePath}/css/jorgchart/custom.css"/>
    <link href="${basePath}/css/jorgchart/prettify.css" type="text/css" rel="stylesheet" />

    <script type="text/javascript" src="${basePath}/js/plugin/jorgchart/prettify.js"></script>
    
    <!-- jQuery includes -->
  
   	<script type="text/javascript" src="${basePath}/js/jquery-1.7.2.js"></script>
   	<script type="text/javascript" src="${basePath}/js/jquery-ui-1.8.16.min.js"></script>
   	 
    <script src="${basePath}/js/plugin/jorgchart/jquery.jOrgChart.js"></script>

    <script>
    jQuery(document).ready(function() {
        $("#org").jOrgChart({
            chartElement : '#chart',
            dragAndDrop  : true
        });
    });
    </script>
  </head>

  <body onload="prettyPrint();">
    
    
    <ul id="org" style="display:none">
    <li>
       	${orgTree}
     </li>
     
   </ul>            
    
    <div id="chart" class="orgChart"></div>
    
    <script>
        jQuery(document).ready(function() {
            
            /* Custom jQuery for the example */
            $("#show-list").click(function(e){
                e.preventDefault();
                
                $('#list-html').toggle('fast', function(){
                    if($(this).is(':visible')){
                        $('#show-list').text('Hide underlying list.');
                        $(".topbar").fadeTo('fast',0.9);
                    }else{
                        $('#show-list').text('Show underlying list.');
                        $(".topbar").fadeTo('fast',1);                  
                    }
                });
            });
            
            $('#list-html').text($('#org').html());
            
            $("#org").bind("DOMSubtreeModified", function() {
                $('#list-html').text('');
                
                $('#list-html').text($('#org').html());
                
                prettyPrint();                
            });
        });
    </script>

</body>
</html>