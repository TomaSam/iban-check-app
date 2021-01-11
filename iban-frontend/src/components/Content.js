import React from 'react';
import '../App.css';
import IbanForm from './IbanForm';
import IbanExport from './IbanExport';

function Content() {
    
    return (
        <div className="content">
            <div className="container">
                <div className="row">
                    <div className="col-12 col-sm-6">
                        <IbanForm />
                    </div>
                    <div className="col-12 col-sm-6">
                        <IbanExport />
                    </div>      
                </div>
            </div>       
        </div>
        );
    }

export default Content;