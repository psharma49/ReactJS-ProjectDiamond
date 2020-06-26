import React, { Component } from "react";
import UploadData from "./UploadData";

class HomeButton extends Component {
  constructor(props) {
    super(props)
  
    this.state = {
    }
  }
  
  render() {
    return (
      <div className="homebutton">
        <div className="Navbar">
          <div className="navbarlabel">{this.props.headingText}</div>
          <a href="http://localhost:3000/UploadData">
            <img
              className="HomeButtonIcon"
              src={process.env.PUBLIC_URL + "/home1.png"}
              alt="optum"
              width="20px"
              // onClick={() => this.imageClick()}
            />
          </a>
        </div>
      </div>
    );
  }
}

export default HomeButton;
