import axios from 'axios'

const API_URL = 'http://localhost:8080/'

class OrderServices {
  persistOrder (menuItem, email, quantity) {
    return axios.post(API_URL + 'orders/save', {}, {
      params: {
        menuItem: menuItem,
        email: email,
        quantity: quantity
      }
    })
  }
}

export default new OrderServices()
