import axios from 'axios';

class IbanService {

    postIbanValidation(iban) {
        return axios.post(`http://localhost:8080/api/check/validation?iban=` + iban);
    }

    postIbanBankName(iban) {
        return axios.post(`http://localhost:8080/api/check/bankcode?iban=` + iban);
    }

    getExportValidation(path) {
        return axios.get(`http://localhost:8080/api/check/exportValidation?path=` + path);
    }

    getExportBankName(path) {
        return axios.get(`http://localhost:8080/api/check/exportBankName?path=` + path);
    }
}

export default new IbanService();