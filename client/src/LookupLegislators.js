import React, { Component } from 'react';
//import './TTYL.css';

class LookupLegislators extends Component {
    constructor(props) {
        super(props);

        this.state = {zip: ''}

        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.buttonClass = this.buttonClass.bind(this);
    }

    handleChange(e) {
        this.setState({zip: e.target.value});
    }

    handleSubmit(event) {
        event.preventDefault();
        if (this.props.submissionEnabled) {

            this.props.lookupFunc(this.state.zip);
        }
    }

    buttonClass() {
        return "btn btn-primary" + (this.props.submissionEnabled ? '' : ' disabled');
    }

    render() {
        const zipCode = this.state.zip;
        return (
            <form id="lookup-legislators-form" className="form-horizontal" onSubmit={this.handleSubmit} >
                <div id="zip-container" className="form-group">
                    <label className="col-md-4 control-label" htmlFor="zip">Your Zipcode</label>
                    <div className="col-md-5">
                        <input id="zip" name="zip" type="number" inputMode="numeric" pattern="[0-9*]" placeholder="90210" className="form-control" onChange={this.handleChange} value={zipCode} />
                    </div>
                </div>
                <div className="form-group">
                    <label className="col-md-4 control-label" htmlFor="search"></label>
                    <div className="col-md-4">
                        <button id="find-leg" name="search" type="submit" className={this.buttonClass()}>Find Your Legislators</button>
                    </div>
                </div>
            </form>
        );
    }
}

export default LookupLegislators;

