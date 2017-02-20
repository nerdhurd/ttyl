import React, { Component } from 'react';

class CallLegislator extends Component {
    constructor(props) {
        super(props);

        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(event) {
        event.preventDefault();
        if (this.props.submissionEnabled) {

            console.log("SUBMITTED 2");

            this.props.callFunc(this.state.zip);
        }
    }

    buttonClass() {
        return "btn btn-primary" + (this.props.submissionEnabled ? '' : ' disabled');
    }

    render() {
        return (
            <form id="register-form" className="form-horizontal" onSubmit={this.handleSubmit}>
                <div id="radio-form" className="form-group">
                    <label className="col-md-4 control-label" htmlFor="legislator">Who You Gonna Call?</label>
                    <div id="leg-inputs" className="col-md-5">

                        {this.props.legislators.map(function(legislator, i) {
                            console.log(legislator);
                            const legislator_id = "leg" + i;
                            return (
                                <div key={legislator_id} className="form-check">
                                    <label className="form-check-label">
                                    <input className="form-check-input" type="radio" name="legislator-radios" id={legislator_id} value={legislator_id} />
                                    {legislator.name}
                                    </label>
                                </div>
                                );
                        })}

                    </div>
                </div>
                <div className="form-group">
                    <label className="col-md-4 control-label" htmlFor="phone-number">Your Phone Number</label>
                    <div className="col-md-5">
                        <input id="phone-number" name="phone-number" type="tel" placeholder="+1 800 867 5309" className="form-control" />
                    </div>
                </div>

                <div className="form-group">
                    <label className="col-md-4 control-label" htmlFor="go"></label>
                    <div className="col-md-4">
                        <button id="go" name="go" type="submit" className="disabled btn btn-primary">Go</button>
                    </div>
                </div>
            </form>
        );
    }
}

export default CallLegislator;

