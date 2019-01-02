<div class="col-lg-12 proficiency_footer next">
<div class="container text-center">
<span class=" proficiency_color">© Copyright 2016 Attra Pty.</span>
</div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
       <script src = "https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
       
       <script src = "${pageContext.servletContext.contextPath}/resources/js/jquery.editable-select.js"></script>
       
      <!-- Include all compiled plugins (below), or include individual files as needed -->
      <script src = "${pageContext.servletContext.contextPath}/resources/js/bootstrap.min.js"></script>
 <!-- DataTables JavaScript -->
	 <script src="${pageContext.servletContext.contextPath}/resources/js/bootstrap-datepicker.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/resources/js/jquery.dataTables.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/resources/js/dataTables.bootstrap.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/resources/js/myscript.js"></script>

    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
    
     $(document).ready(function() {
        $('#allEmp,#dataTables-example').DataTable({
                responsive: true
        });
		  $('#skillEmp').DataTable({
                responsive: true
        });
		$('#dataTables-example_filter,#dataTables-example1_filter,#dataTables-example_paginate,#dataTables-example1_paginate,.dataTables_filter').addClass('pull-right');


    }); 
    </script>
  
    <input type="text" hidden value="${pageContext.servletContext.contextPath}" id="contextPaht"/>
   </body>
</html>