import React, { Component } from 'react';
import './TTYL.css';
import LookupLegislators from './LookupLegislators';
import CallLegislator from './CallLegislator';

class TTYL extends Component {

    // states
    // init -> looking_for_legislators -> legislators_found -> calling?

    constructor(props) {
        super(props);

        this.state = { status: "init" }
        this.lookupLegislators = this.lookupLegislators.bind(this);
    }

    lookupLegislators(zipCode) {
        // this function is called by LookupLegislators.
        // if it successeds, we remote that block. 
        // if not, we set an error and re-enable the button.
        console.log("loking Them up: " + zipCode);
        console.log(this);
        this.setState({ status: "looking_for_legislators" });
        console.log(this.state.status);
        console.log(this.state.status === "init");

        //this will be a fetch. but fake it
        const response = [
            { name: "Nancy Pelosi", phone: "202-225-4965", district: "12" },
            { name: "Barbra Boxer", phone: "202-224-3553", district: "Junior Seat" },
            { name: "Dianne Feinstein", phone: "202-224-3841", district: "Senior Seat" }
        ];

        this.setState({status: "legislators_found", legislators: response});

    }

    render() {
        const status = this.state.status;

        let form = null;
        if (status === "init" || status === "looking_for_legislators") {
            form = <LookupLegislators lookupFunc={this.lookupLegislators} submissionEnabled={status === "init"} />;
        } else {
            form = <CallLegislator legislators={this.state.legislators} submissionEnabled={status === "legislators_found"} />
        }

        return (
            <div className="TTYL">
                <h1 className="text-center">TTYL</h1>
                {form}
            </div>
        );
    }
}

export default TTYL;
