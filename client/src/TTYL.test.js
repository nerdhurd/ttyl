import React from 'react';
import ReactDOM from 'react-dom';
import TTYL from './TTYL';

it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(<TTYL />, div);
});
