function redrawTag() {
    var temp;

    temp = $("select");
    if (temp.length) {
        temp.select2();
    }

    temp = $("input[type='checkbox']");
    if (temp.length) {
        temp.radiocheck();
    }

    temp = $("input[type='radio']");
    if (temp.length) {
        temp.radiocheck();
    }

    // Focus state for append/prepend inputs
    $(".input-group").on("focus", ".form-control", function () {
        $(this).closest(".input-group, .form-group").addClass("focus");
    }).on("blur", ".form-control", function () {
        $(this).closest(".input-group, .form-group").removeClass("focus");
    });

    // Disable link clicks to prevent page scrolling
    $(document).on('click', 'a[href="#fakelink"]', function (e) {
        e.preventDefault();
    });
}

$(function () {
    redrawTag();
});