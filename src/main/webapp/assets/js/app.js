function showNotify(from, align, message) {
    color = Math.floor((Math.random() * 4) + 1);

    $.notify({
        icon: "nc-icon nc-app",
        message: message

    }, {
        type: type[color],
        timer: 8000,
        placement: {
            from: from,
            align: align
        }
    });
}