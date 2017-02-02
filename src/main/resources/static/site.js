$(function() {
    console.log("Ready!");

    $("#register-form").submit(function(e){
        // Prevent Form Submission
        e.preventDefault();

        // Submit the form via a POST
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
