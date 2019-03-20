const React = require('react');
const ReactDOM = require('react-dom');
const axios = require('axios');

class App extends React.Component {

    render() {
        return <p> Hello World! </p>;
    }
}

ReactDOM.render(
    <App />,
    document.getElementById('react')
);
