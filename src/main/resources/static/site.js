function populate_legislators(legislators) {

    // Ugh, how can this be the suggested way to do things. Fuck this language.
    var radio_form = $('<div id="radio-form" class="form-group">\
    <label class="col-md-4 control-label" for="legislator">Who You Gonna Call?</label>\
    <div id="leg-inputs" class="col-md-5">\
    </div>\
</div>');
    var inputs = radio_form.find("#leg-inputs");

    var legislators_dict = {};

    var count = 0;
    legislators.forEach(function (legislator) {
        var legislator_id = "leg" + count;
        
        var legislator_form = '<div class="form-check">\
    <label class="form-check-label">\
    <input class="form-check-input" type="radio" name="legislator-radios" id="' + legislator_id + '" value="' + legislator_id + '" >\
    ' + legislator.name + '\
    </label>\
</div>'

        legislators_dict[legislator_id] = legislator;
        inputs.append(legislator_form);
        count ++;
    });

    radio_form.data("legislators-dict", legislators_dict);

    var lookup_form = $("#lookup-legislators-form");
    lookup_form.remove();

    var register_form = $("#register-form");

    register_form.prepend(radio_form);

    $("#go").removeClass("disabled");

}


$(function() {

    $("#register-form").submit(function(e){
        // TODO This stupidly fires even if the button is disabled, probably have to disable the form?
        e.preventDefault();
        
        console.log("Make A Call");

        radio_form = $("#radio-form");

        var legislators = radio_form.data("legislators-dict");
        // get values from form
        var values = {};
        $("#register-form").serializeArray().forEach(function (val) {
            values[val.name] = val.value;
        });

        legislator = legislators[values["legislator-radios"]];

        post_data = {};

        post_data["from_phone_number"] = values["phone-number"];
        post_data["to_phone_number"] = legislator["phone"];

        console.log("POST", post_data);

        $.ajax({
            url: '/api/v1/call',
            type: 'post',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            headers: {
                'x-requested-by': 'ttyl'
            },
            data: JSON.stringify(post_data)
        });
    });

    $("#lookup-legislators-form").submit(function(e) {
        e.preventDefault();

        $.ajax({
            url: "/api/vi/legislators",
            type: 'get',
            error: function(e) {

                response = [
                    { name: "Nancy Pelosi", phone: "202-225-4965", district: "12" },
                    { name: "Barbra Boxer", phone: "202-224-3553", district: "Junior Seat" },
                    { name: "Dianne Feinstein", phone: "202-224-3841", district: "Senior Seat" }
                ];

                populate_legislators(response);
                            

            },
            success: function(s) {
                console.log("WOOOO");
                console.log(s);
            }
        });
    });



});
