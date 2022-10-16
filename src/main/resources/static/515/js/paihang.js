function loadContent(commid) {
    $.ajax({
        url: "/paihang_fragment",
        type: "GET",
        contentType: "application/html",
        dataType: "html",
        data: {"commid":commid},
        success: function (result) {
            $("#content").html(result)
        }
    });
}

function xijia() {
    loadContent(23)
}

function yijia() {
    loadContent(21)
}

function dejia() {
    loadContent(22)
}

function fajia() {
    loadContent(24)
}