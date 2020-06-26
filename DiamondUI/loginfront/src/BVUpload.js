import React, { Component } from "react";
import HeaderComponent from "./HeaderComponent";
import HomeButton from "./HomeButton";
import AuthenticationService from './AuthenticationService'


class BVUpload extends Component {
  constructor(props) {
    super(props)
  
    this.state = {
      headingText: "Upload Data"
    }
  }
  

   OntoBulkUpload(){
    this.props.history.push("/BulkUpload");
   } 

  render() {
    const isUserLoggedin = AuthenticationService.isUserLoggedin();
    return (
      <div>
        {isUserLoggedin &&
        <div>
        <HeaderComponent/>
        <HomeButton
        headingText={this.state.headingText}/>
        <div className="app">
          <div className="container">
            <div className="BVUpload">
              <img
                className="optummmBV"
                src={process.env.PUBLIC_URL + "/optummm.png"}
                alt="optum"
                width="200px"
              />
              <div className="screentext">Upload/Modify Business Value</div>
              <div className="upload">
                <button className="btnBV" onClick={() => this.OntoBulkUpload()}>Bulk Upload</button>
              </div>
              <div className="dashboardbutton">
                <button className="btnBV">
                  Manual Upload
                </button>
              </div>
              <div className="dashboardbutton">
                <button className="btnBV">
                  Modify Business Values
                </button>
              </div>
            </div>
          </div>
        </div>
        </div>
        }
      </div>
    );
  }
}
export default BVUpload;
