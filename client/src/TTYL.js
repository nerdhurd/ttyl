import React, { Component } from 'react';
import './TTYL.css';
import LookupLegislators from './LookupLegislators';

class TTYL extends Component {
    render() {
        return (
            <div className="TTYL">
                <h1 className="text-center">TTYL</h1>
                <LookupLegislators />
            </div>
        );
    }
}

export default TTYL;
