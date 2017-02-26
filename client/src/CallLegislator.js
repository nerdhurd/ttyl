import React, { Component } from 'react';

class CallLegislator extends Component {
    constructor(props) {
        super(props);

        this.state = { selectedLeg: null, phoneNumber: "" };

        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleLegChange = this.handleLegChange.bind(this);
        this.handlePhoneChange = this.handlePhoneChange.bind(this);
        this.legislatorInputs = this.legislatorInputs.bind(this);
    }

    handleLegChange(e) {
        this.setState({ selectedLeg: e.target.value });
    }

    handlePhoneChange(e) {
        this.setState({ phoneNumber: e.target.value });
    }

    handleSubmit(event) {
        event.preventDefault();
        if (this.props.submissionEnabled) {
            const selectedLegislator = this.props.legislators.find( (leg) => { return leg.phone === this.state.selectedLeg });
            this.props.callFunc(selectedLegislator, this.state.phoneNumber);
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
                        {this.props.legislators.map((legislator, i) => {
                            const legislator_id = "leg_" + i;
                            return (
                                <div key={legislator_id} className="form-check">
                                    <label className="form-check-label">
                                        <input className="form-check-input" type="radio" name="legislator-radios" id={legislator.phone} value={legislator.phone} checked={this.state.selectedLeg === legislator.phone} onChange={this.handleLegChange} />
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
                        <input id="phone-number" name="phone-number" type="tel" placeholder="+1 800 867 5309" className="form-control" value={this.state.phoneNumber} onChange={this.handlePhoneChange} />
                    </div>
                </div>

                <div className="form-group">
                    <label className="col-md-4 control-label" htmlFor="go"></label>
                    <div className="col-md-4">
                        <button id="go" name="go" type="submit" className={this.buttonClass()}>Go</button>
                    </div>
                </div>
            </form>
        );
    }
}

export default CallLegislator;

