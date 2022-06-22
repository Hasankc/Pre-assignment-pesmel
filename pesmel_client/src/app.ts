const axios = require('axios').default;


export class App {
  public rawData;
  public aggregatedData;
  private minByDay;
  private avgByDay;
  private maxByDay;

  attached() {
    this.fetchRawData();
    this.fetchMinByDay();
    this.fetchAvgByDay();
    this.fetchMaxByDay();
    
    for (let i = 0; i < this.minByDay.length; i++) {
      let timestamp = this.minByDay[i].timestamp;
      let min = this.minByDay[i].temperature;
      let avg = this.avgByDay[i].temperature;
      let max = this.maxByDay[i].temperature;
      this.aggregatedData.push(
        {timestamp, min, avg, max}
      );
    }
  }

  private fetchRawData() {
    axios.get('http://localhost:8080/rawdata')
      .then((response) => {
        console.log(response);
        this.rawData = response.data;
      });
  }
  private fetchMinByDay() {
    axios.get('http://localhost:8080/min/day/all')
      .then((response) => {
        console.log(response);
        this.aggregatedData = response.data;
      });     
}
private fetchAvgByDay() {
  axios.get('http://localhost:8080/avg/day/all')
    .then((response) => {
      console.log(response);
      this.aggregatedData = response.data;
    });
  }
  private fetchMaxByDay() {
    axios.get('http://localhost:8080/max/day/all')
      .then((response) => {
        console.log(response);
        this.aggregatedData = response.data;
      });
}}
