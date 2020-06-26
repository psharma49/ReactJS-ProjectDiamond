import React, { Component } from "react";

class Imagess extends Component {
  render() {
    return (
      <div className="ImagesPortfolioView">
        <img
          className="commImagePortfolio"
          src={process.env.PUBLIC_URL + "/Commercial.png"}
          alt="optum"
          width="50px"
        />

        <img
          className="markImagePortfolio"
          src={process.env.PUBLIC_URL + "/Market.png"}
          alt="optum"
          width="50px"
        />

        <img
          className="effImagePortfolio"
          src={process.env.PUBLIC_URL + "/Efficiency.png"}
          alt="optum"
          width="50px"
        />

        <img
          className="custImagePortfolio"
          src={process.env.PUBLIC_URL + "/Customer.png"}
          alt="optum"
          width="50px"
        />

        <img
          className="futImagePortfolio"
          src={process.env.PUBLIC_URL + "/Future.png"}
          alt="optum"
          width="50px"
        />
      </div>
    );
  }
}

export default Imagess;
