import React, { Component } from 'react';
import '../App.css';
import IbanService from './IbanService';

class IbanForm extends Component {
    constructor(props) {
        super(props);
        this.state = {
            iban: "",
            action: ""
        };
    }

    inputChange = (event) => {
        this.setState({[event.target.name]: event.target.value});   
    }

    submitForm = (event) => {
        event.preventDefault();
        console.log("Iban: " + this.state.iban);
        console.log("Action: " + this.state.action)
        if (this.state.action === "1") {
            IbanService.postIbanValidation(this.state.iban)
            .then((res) => {
                const response = res.data;
                alert("Result: " + response);
            });
        }
        else if (this.state.action === "2") {
            IbanService.postIbanBankName(this.state.iban)
            .then((res) => {
                const response = res.data;
                console.log("Result: " + response); 
            }); 
        }
    }

render() {
    return (
        <div>
        <form onSubmit={this.submitForm}>
        <div className="form-group">
            <label htmlFor="iban" className="col-form-label">
                Write International Bank Account Number(IBAN):
            </label>
            <input className="form-control" type="text" value={this.state.iban} 
            id="iban" name="iban" onChange={this.inputChange} />    
        </div>
        <div className="form-group">
            <label htmlFor="action">What do you want to do?</label>
            <select className="form-control" id="action" name="action" 
            onChange={this.inputChange} required defaultValue={'DEFAULT'}>
            <option value="DEFAULT" disabled>Choose action</option>
            <option value="1">Check IBAN Validation</option>
            <option value="2">Check IBAN Bank</option>
            </select>
        </div>
        <div className="form-group">
            <button type="submit" className="btn btn-light">Do it!</button>
        </div>
        </form>         
        </div>
    );
    
}
}

export default IbanForm