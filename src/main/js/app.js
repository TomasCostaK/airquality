const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {cityAirs: []};
    }

    componentDidMount() {
        client({method: 'GET', path: '/api/cityAirs'}).done(response => {
            this.setState({employees: response.entity._embedded.cityAirs});
        });
    }

    render() {
        return (
            <EmployeeList employees={this.state.cityAirs}/>
    )
    }
}