$(document).ready(function () {
    var table = $('#dataTables-example').DataTable({
        "ordering": false,
        "fnDrawCallback": function (oSettings) {
            resizeTextArea();
        }
    });

});

function resizeTextArea() {

    $("textarea").each(function () {
        this.style.height = '0px';
        this.style.height = (this.scrollHeight + 10) + 'px';
    });
}
