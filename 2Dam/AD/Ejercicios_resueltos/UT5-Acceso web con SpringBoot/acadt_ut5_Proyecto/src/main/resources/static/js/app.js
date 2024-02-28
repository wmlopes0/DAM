$(document).ready(function() {
	$('#delete-modal').on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget);
		var data = button.data('id');

		var modal = $(this);

		var a = modal.find('.modal-body a')[0];
		a.href = a.href + data;

	});
});
