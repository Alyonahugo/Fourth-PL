// if (window.location.pathname == '/Fourth-PL/faces/templateFirst.xhtml')
    // $( document ).ready( function() {
    //     $('#content').style('text-align', 'center');
    //     $('table').style('margin-left', '40%');
    // } );
console.log('checking1');
if (window.location.pathname == '/Fourth-PL/faces/templateFirst.xhtml') {
	console.log('checking2');
	$( document ).ready( function() {
		console.log('checking4');
        $('#content').style('text-align', 'center');
        $('table').style('margin-left', '40%');
    } );
} else {
	console.log('checking3');
}

