import React, { Component } from "react";
// reactstrap components
import { Container, Row} from "reactstrap";

const API = 'http://localhost:8080/api/';
const DEFAULT_QUERY = 'all_streets_city/';
const STATS_QUERY = 'statistics/';
const GRAPH_STATS = 'charts/';

class Stats extends React.Component {
    render() {
        return (
            <div>
                <h3 style={{color:'rgba(0,0,0,0.6)', fontWeight:'bolder', fontSize:20}}>{this.props.stat_name}</h3>
                <h4 style={{color:'rgba(0,0,0,0.6)', fontWeight:'bolder', textAlign:'center' ,fontSize:22}}> {this.props.number} </h4>
            </div>
        );
    }
}

export default class Dashboard extends React.Component {
    constructor(props){
        super(props);
        this.state = {

        };
    }

    componentDidMount() {

    }

    fetchCity(name){

    }

    render() {
        return (

            <div
                className=""
                data-parallax={true}
                style={{
                    marginTop:100,
                    backgroundColor:'rgba(0,0,0,0)',
                    fontWeight:'medium',
                    position: 'absolute', left: '50%', top: '50%',
                    transform: 'translate(-50%, -50%)',
                    padding: 10,
                }}
            >
                <Container style={{display:'flex',flex:1,flexDirection:'column',maringTop:50}}>
                    <Row style={{alignContent:'center',justifyContent:'center',border:10,borderColor:'white'}}>
                        <p style={{color:'rgba(0,0,0,0.6)', fontWeight:'bold', fontSize:30}}>Analytics for city:</p>
                    </Row>
                    <p style={{color:'rgba(0,0,0,0.6)', fontSize:13, marginTop:5, fontWeight:'bolder'}}><span>City name: </span> <input name="text" type="text" placeholder="Search" /><button>Search</button></p>


                    <p style={{color:'rgba(0,0,0,0.6)', fontWeight:'bold', marginTop:80, textAlign:'center',fontSize:24}}>{'Cidade X'}</p>
                    <div style={{display:'flex', flexDirection:'row' , justifyContent:'space-between',alignContent:'space-between'}}>
                        <Stats style={{flex:1}} stat_name="Nº of accidents" number={3}/>
                        <Stats style={{flex:1}} stat_name="Roadblock total time" number={3}/>
                        <Stats style={{flex:1}} stat_name="Nº of roadblocks" number={4}/>
                        <Stats style={{flex:1}} stat_name="Times Congested" number={5}/>

                    </div>
                </Container>
            </div>
        )
    }

}