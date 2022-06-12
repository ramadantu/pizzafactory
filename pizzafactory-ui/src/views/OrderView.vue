<template>
  <div id="body">
    <b-card id="offer" no-body class="overflow-hidden" style="max-width: 540px;">
      <b-row no-gutters>
        <b-col md="6">
          <b-card-img id="offer-image" :src="pic" alt="Image" class="rounded-0"></b-card-img>
        </b-col>
        <b-col md="6">
          <b-card-body title="SPECIAL OFFER!">
            <b-card-text id="offer-text">
              BUY NOW...<br>
              ...PIZZA CHICKEN<br>
              AND GET<br>
              <b id="special-text">2 FOR 1</b>
            </b-card-text>
          </b-card-body>
        </b-col>
      </b-row>
    </b-card>

    <b-alert
      variant="success"
      v-if="isSuccess"
      :show="dismissCountDown"
    >
      Order Created Successfully
    </b-alert>

    <b-form class="order-form" @submit="persistOrder" @reset="onReset">
      <h3 class="form-title">Order</h3>
        <b-form-group
          id="input-group-email"
          label="Email address:"
          label-for="input-email"
        >
          <b-form-input
            id="input-email"
            v-model="email"
            type="email"
            placeholder="Enter email"
            required
          ></b-form-input>
        </b-form-group>

        <div id="menu">
          <b-form-group
            id="input-group-menu"
            label="Menu:"
            label-for="input-menu">
            <b-form-select
              id="input-menu"
              v-model=menuItem
              :options=menu
              required
            ></b-form-select>
          </b-form-group>

          <b-form-group
            id="input-group-quantity"
            label="Quantity:"
            label-for="input-quantity">
            <b-form-spinbutton
              id="input-quantity"
              v-model=quantity
              min="1"
              max="20"
              required
            ></b-form-spinbutton>
          </b-form-group>
        </div>

      <div id="buttons">
      <b-button type="submit" variant="primary">Submit</b-button>
      <b-button type="reset" variant="danger">Reset</b-button>
      </div>
    </b-form>
  </div>
</template>

<script>
import OrderServices from '../services/order-services'
import MenuServices from '../services/menu-services'
import pic from '../assets/special-offer.png'

export default {
  name: 'OrderView',
  created () {
    this.fetchMenusItem()
  },
  data () {
    return {
      pic: pic,
      menuItem: '',
      email: '',
      quantity: 1,
      menu: [],
      isSuccess: false,
      dismissCountDown: 7
    }
  },
  methods: {
    persistOrder () {
      OrderServices.persistOrder(
        this.menuItem,
        this.email,
        this.quantity
      )
        .then(
          this.isSuccess = true,
          response => { console.log(response) })
        .catch((exception) => { console.log(exception) })
    },
    fetchMenusItem () {
      MenuServices.fetchMenusItem()
        .then(response => {
          this.menu = response.data
          console.log(response)
        })
    },
    onReset () {
      this.email = ''
      this.quantity = 1
    }
  }
}
</script>

<style lang="css" scoped>
@import '../assets/styles/order.css';
</style>
