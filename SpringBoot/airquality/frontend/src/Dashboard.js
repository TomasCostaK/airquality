import React, { Component } from "react";
// reactstrap components
import { Container, Row} from "reactstrap";

class Stats extends React.Component {
    render() {
        return (
            <div>
                <h3 style={{color:'rgba(0,0,0,0.6)', fontWeight:'bolder', fontSize:20}}>{this.props.stat_name + ': ' + this.props.number}</h3>
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

    const handleNameChange = event => setName(event.target.value);

    async function fetchCity(toInput){
        let response = await fetch("http://localhost:8080/api/search?name=" + name , {
            method : "GET",
            mode: "cors",
            cache: "no-cache"
        })
        try {
            let body = await response.json();
            setAqi(body.aqi)
            setPm10(body.pm10)
            setPm25(body.pm25)
            setDomi(body.dominentpol)
        } catch (e) {
            alert("The city selected doesnt have available data in the API. Try another one!")
        }
    }

    const handleSubmit = variables => {
        fetchCity(name);
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
                    <img src="label.png" alt="AQI Index (The closer to 0, the best)" />
                    <Row style={{alignContent:'center',justifyContent:'center',border:10,borderColor:'white'}}>
                        <p style={{color:'rgba(0,0,0,0.6)', fontWeight:'bold', fontSize:30}}>{'Analytics for city: ' + name}</p>
                    </Row>
                    <p style={{color:'rgba(0,0,0,0.6)', fontSize:13, marginTop:5, fontWeight:'bolder'}}><span>City name: </span> <input onChange={handleNameChange} name="text" type="text" placeholder="Search" /><button style={{color:'white',backgroundColor:'blue'}} onClick={handleSubmit}>Search</button></p>

                    <div style={{display:'flex',flex:1, flexDirection:'column' , justifyContent:'space-between',alignContent:'space-between'}}>
                        <Stats style={{flex:1}} stat_name="Average Quality Index" number={aqi}/>
                        <Stats style={{flex:1}} stat_name="Dominant Pollutant" number={domi}/>
                        <Stats style={{flex:1}} stat_name="PM2.5" number={pm25}/>
                        <Stats style={{flex:1}} stat_name="PM1.0" number={pm10}/>

                    </div>
                </Container>
            </div>
        )

}