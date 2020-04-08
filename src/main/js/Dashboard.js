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
                }}
            >
                <Container style={{display:'flex',flex:1,flexDirection:'column',maringTop:50}}>
                    <Row style={{alignContent:'center',justifyContent:'center',border:10,borderColor:'white'}}>
                        <Text style={{color:'rgba(0,0,0,0.6)', fontWeight:'bold', fontSize:30}}>Analytics for streets in:</Text>
                        <ReactSearchBox
                            placeholder="Search city"
                            value="Ilhavo"
                            data={this.state.streets}
                            color={'black'}
                            style={{fontWeight:'bold',width:10}}
                            inputBoxFontColor={'black'}
                            dropDownHoverColor={'rgba(0,255,255,0.1)'}
                            onSelect={record => this.changeStreet(record)}
                        />
                    </Row>
                    <Text style={{color:'rgba(0,0,0,0.6)', fontSize:13, marginTop:5, fontWeight:'bolder'}}>Street name: </Text>
                    <ReactSearchBox
                        placeholder="Search street"
                        value="Travessa das Leirinhas"
                        data={this.state.dataSource}
                        color={'black'}
                        style={{fontWeight:'bold',width:40}}
                        inputBoxFontColor={'black'}
                        dropDownHoverColor={'rgba(0,255,255,0.1)'}
                        onSelect={record => this.changeStreetDisplayed(record)}
                    />

                    <Row style={{flex:1, alignContent:'space-between',justifyContent:'space-between'}}>
                        <Container style={{flex:1, alignContent:'center',justifyContent:'center'}}>
                            <Text style={{color:'rgba(0,0,0,0.6)', fontSize:13, marginTop:5, fontWeight:'bolder'}}>Start Date: </Text>
                            <DatePicker
                                selected={this.state.begin_date_cal}
                                onChange={this.handleChangeStart}
                                maxDate={new Date()}
                            />
                        </Container>
                        <Container style={{flex:1, alignContent:'center',justifyContent:'center'}}>
                            <Text style={{color:'rgba(0,0,0,0.6)', fontSize:13, marginTop:5, fontWeight:'bolder'}}>End Date: </Text>
                            <DatePicker
                                selected={this.state.end_date_cal}
                                onChange={this.handleChangeEnd}
                                maxDate={new Date()}
                            />
                        </Container>
                        <Container style={{flex:1, alignContent:'center',justifyContent:'center'}}>
                            <Text style={{color:'rgba(0,0,0,0.6)', fontSize:13, marginTop:5, fontWeight:'bolder'}}>Week Day: </Text>
                            <Dropdown options={this.state.options} onChange={(day) => this.changeDay(day)} value={this.state.dayofweek} placeholder="Select a day" />

                        </Container>
                    </Row>
                    <Text style={{color:'rgba(0,0,0,0.6)', fontWeight:'bold', marginTop:80, textAlign:'center',fontSize:24}}>{this.state.street_name}</Text>
                    <div style={{display:'flex', flexDirection:'row' , justifyContent:'space-between',alignContent:'space-between'}}>
                        <Stats style={{flex:1}} stat_name="Nº of accidents" number={this.state.dataSourceStats[0].total_accident}/>
                        <Stats style={{flex:1}} stat_name="Roadblock total time" number={(this.state.dataSourceStats[0].road_block.total_time.toFixed()).toString() + "H"}/>
                        <Stats style={{flex:1}} stat_name="Nº of roadblocks" number={this.state.dataSourceStats[0].road_block.times}/>
                        <Stats style={{flex:1}} stat_name="Times Congested" number={this.state.dataSourceStats[0].transit_count}/>

                    </div>
                </Container>
            </div>
        )
    }

}