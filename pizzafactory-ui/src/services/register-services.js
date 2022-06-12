import axios from 'axios'

const API_URL = 'http://localhost:8080/'

class RegisterServices {
  persistClient (
    firstName,
    lastName,
    email,
    num,
    address,
    username,
    password) {
    return axios.post(API_URL + 'client/save', {}, {
      params: {
        firstName: firstName,
        lastName: lastName,
        email: email,
        num: num,
        address: address,
        username: username,
        password: password
      }
    })
  }
}

export default new RegisterServices()
