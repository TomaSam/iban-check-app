import React, { Component } from 'react';
import '../App.css';
import IbanService from './IbanService';

class IbanExport extends Component {
    constructor(props) {
        super(props);
        this.state = {
            path: "",
            action: ""
        };
    }

    inputChange = (event) => {
        this.setState({[event.target.name]: event.target.value});   
    }

    submitForm = (event) => {
        event.preventDefault();
        console.log(this.state.path);
        console.log(this.state.action)
        if (this.state.action === "1") {
            IbanService.getExportValidation(this.state.path);
            alert("Check your directory: " + this.state.path);
        }
        else if (this.state.action === "2") {
            IbanService.getExportBankName(this.state.path);
            alert("Check your directory: " + this.state.path);
        }
    }

render() {
    return (
        <form onSubmit={this.submitForm}>
        <div className="form-group">
            <label htmlFor="path" className="col-form-label">
                Write path of CSV file:
            </label>
            <input className="form-control" type="text" value={this.state.path} 
            id="path" name="path" onChange={this.inputChange} />
        </div>
        <div className="form-group">
            <label htmlFor="action">What do you want to do?</label>
            <select className="form-control" id="action" name="action" 
            onChange={this.inputChange} required defaultValue={'DEFAULT'}>
            <option value="DEFAULT" disabled>Choose action</option>
            <option value="1">Export IBAN Validation</option>
            <option value="2">Export IBAN Bank</option>
            </select>
        </div>
        <div className="form-group">
            <button type="submit" className="btn btn-light">Do it!</button>
        </div>
        </form>       
        
    );
    
}
}

export default IbanExport