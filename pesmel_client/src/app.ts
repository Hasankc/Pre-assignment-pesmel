const axios = require('axios').default;


export class App {
  public rawData;
  public aggregatedData;
  private minByDay;
  private avgByDay;
  private maxByDay;

  constructor() {
    this.aggregatedData = [];
  }

  async attached() {
    this.rawData = await this.fetchRawData();
    this.minByDay = await this.fetchMinByDay();
    this.avgByDay = await this.fetchAvgByDay();
    this.maxByDay = await this.fetchMaxByDay();
    this.createAggregatedData();
  }

  private createAggregatedData() {
    for (let i = 0; i < this.minByDay.length; i++) {
      let timestamp = this.minByDay[i][1];
      let min = this.minByDay[i][0];
      let avg = Math.round(this.avgByDay[i][0] * 10) / 10;
      let max = this.maxByDay[i][0];
      this.aggregatedData.push(
        { timestamp, min, avg, max }
      );
    }
  }

  private async fetchRawData() {
    const result = await axios.get('http://localhost:8080/rawdata');
    return result.data;
  }

  private async fetchMinByDay() {
    const result = await axios.get('http://localhost:8080/min/day/all');
    return result.data;
  }

  private async fetchAvgByDay() {
    const result = await axios.get('http://localhost:8080/avg/day/all');
    return result.data;
  }

  private async fetchMaxByDay() {
    const result = await axios.get('http://localhost:8080/max/day/all')
    return result.data;
  }
}
