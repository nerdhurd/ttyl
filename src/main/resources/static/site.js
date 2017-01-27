$(function() {
    $("#register-form").submit(function(e){
        e.preventDefault();
        var formData = new FormData(e);
        $.ajax({
            url: '/api/v1/call',
            type: 'post',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            headers: {
                'x-requested-by': 'ttyl'
            },
            data: JSON.stringify({
                to: $("#phone-number").val()
            })
        });
    });
});
