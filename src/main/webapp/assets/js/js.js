$(document).ready(function() {
    $('#myTable').DataTable( {
        "order": [[ 3, "desc" ]],
	responsive: true,
    //paging: false,
	//info: false,
	//searching: false,
	"scrollX": true,
	
    } );
} );