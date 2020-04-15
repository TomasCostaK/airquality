import React, { Component } from "react";
// reactstrap components
import { Container, Row} from "reactstrap";

const API = 'http://localhost:8080/api/';
const DEFAULT_QUERY = 'search/';

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

export default function Dashboard() {
    const [name,setName] = React.useState("");
    const [aqi,setAqi] = React.useState("");
    const [pm10,setPm10] = React.useState("");
    const [pm25,setPm25] = React.useState("");
    const [domi,setDomi] = React.useState("");

    const handleNameChange = event => setName(event.target.value());

    async function fetchCity(toInput){
        alert("Going to fetch");
        let response = await fetch("http://localhost:8080/api/cities" , {
            method : "GET",
            mode: "cors",
            cache: "no-cache"
        })
        console.log(response)
        let body = await response.json();
    }

    const handleSubmit = variables => {
        const input = {name};
        fetchCity(input);
    }


        return (

            <div
                className=""
                data-parallax={true}
                style={{
                    backgroundColor:'rgba(0,0,0,0)',
                    fontWeight:'medium',
                    position: 'absolute', left: '50%', top: '50%',
                    transform: 'translate(-50%, -50%)',
                    padding: 10,
                }}
            >
                <Container style={{display:'flex',flex:1,flexDirection:'column',maringTop:30}}>
                    <Row style={{alignContent:'center',justifyContent:'center',border:10,borderColor:'white'}}>
                        <p style={{color:'rgba(0,0,0,0.6)', fontWeight:'bold', fontSize:30}}>Analytics for city:</p>
                    </Row>
                    <p style={{color:'rgba(0,0,0,0.6)', fontSize:13, marginTop:5, fontWeight:'bolder'}}><span>City name: </span> <input name="text" onClick={handleSubmit} type="text" placeholder="Search" /><button>Search</button></p>


                    <p style={{color:'rgba(0,0,0,0.6)', fontWeight:'bold', marginTop:80, textAlign:'center',fontSize:24}}>{'Cidade'}</p>
                    <div style={{display:'flex',flex:1, flexDirection:'row' , justifyContent:'space-between',alignContent:'space-between'}}>
                        <Stats style={{flex:1}} stat_name="Average Quality Index" number={0}/>
                        <Stats style={{flex:1}} stat_name="Dominent Pol" number={0}/>
                        <Stats style={{flex:1}} stat_name="PM2.5" number={0}/>
                        <Stats style={{flex:1}} stat_name="PM1.0" number={0}/>

                    </div>
                </Container>
            </div>
        )

}