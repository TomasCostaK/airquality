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
            this.setState({cityAirs: response.entity._embedded.cityAirs});
        });
    }

    render() {
        return (
            <CityAirList cityAirs={this.state.cityAirs}/>
    )
    }
}

class CityAirList extends React.Component{
    render() {
        const cityAirs = this.props.cityAirs.map(cityAir =>
            <CityAir key={cityAir._links.self.href} cityAir={cityAir}/>
        );
        return (
            <table>
                <tbody>
                <tr>
                    <th>Name</th>
                    <th>Air Quality Index</th>
                    <th>PM10</th>
                </tr>
                {cityAirs}
                </tbody>
            </table>
        )
    }
}

class CityAir extends React.Component{
    render() {
        //Shouldnt we get the variables
        return (
            <tr>
                <td>{this.props.cityAirs.name}</td>
                <td>{this.props.cityAirs.aqi}</td>
                <td>{this.props.cityAirs.pm10}</td>
            </tr>
        )
    }
}

ReactDOM.render(
    <App />,
    document.getElementById('react')
)