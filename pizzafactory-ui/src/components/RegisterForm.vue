<template>
  <div class="register">
    <p class="animated-header" data-text="Welcome...!">Welcome...!</p>
    <b-alert
      variant="success"
      v-if="isSuccess"
      :show="dismissCountDown"
    >
      Profile Created Successfully
    </b-alert>
    <form class="multi-step-form" data-multi-step>
      <div class="form active" data-step>
        <h3 class="step-title">Account Details</h3>
        <div class="form-group">
          <label for="email">Email</label>
          <input type="email" required name="email" id="email" v-model="email" placeholder="Enter email">
        </div>
        <div class="form-group">
          <label for="username">Username</label>
          <input type="text" required name="username" id="username" v-model="username" placeholder="Enter username">
        </div>
        <div class="form-group">
          <label for="password">Password</label>
          <input type="password" required name="password" id="password" v-model="password" placeholder="Enter password">
        </div>
        <div class="form-buttons" style="align-self: center">
          <b-button variant="success" type="button" data-next>Next</b-button>
        </div>
      </div>
      <div class="form" data-step>
        <h3 class="step-title">Contacts</h3>
        <div class="form-group">
          <label for="address">Address</label>
          <input type="text" required name="address" id="address" v-model="address" placeholder="Enter address">
        </div>
        <div class="form-group">
          <label for="number">Number</label>
          <input type="tel" required name="number" id="number" v-model="num" placeholder="Enter number">
        </div>
        <div class="form-buttons">
         <b-button variant="success" type="button" data-previous>Previous</b-button>
         <b-button variant="success" type="button" data-next>Next</b-button>
        </div>
      </div>
      <div class="form" data-step>
        <h3 class="step-title">Profile Names</h3>
        <div class="form-group">
          <label for="firstName">First Name</label>
          <input type="text" required name="firstName" id="firstName" v-model="firstName" placeholder="Enter First Name">
        </div>
        <div class="form-group">
          <label for="lastName">Last Name</label>
          <input type="text" required name="lastName" id="lastName" v-model="lastName" placeholder="Enter Last Name">
        </div>
        <div class="form-buttons">
         <b-button variant="success" type="button" data-previous>Previous</b-button>
         <b-button variant="success" type="submit" v-on:click="persistClient" data-next>Submit</b-button>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
import RegisterServices from '../services/register-services'

export default {
  name: 'RegisterForm',
  data () {
    return {
      firstName: '',
      lastName: '',
      email: '',
      num: '',
      address: '',
      username: '',
      password: '',
      isSuccess: false,
      dismissCountDown: 7
    }
  },
  mounted () {
    const multiStepForm = document.querySelector('[data-multi-step]')
    const formSteps = [...multiStepForm.querySelectorAll('[data-step]')]
    let currentStep = formSteps.findIndex(step => {
      return step.classList.contains('active')
    })

    if (currentStep < 0) {
      currentStep = 0
      showCurrentStep()
    }

    multiStepForm.addEventListener('click', e => {
      const inputs = [...formSteps[currentStep].querySelectorAll('input')]
      const allValid = inputs.every(input => input.reportValidity())

      if (e.target.matches('[data-next]')) {
        if (allValid) {
          currentStep += 1
          if (currentStep > 2) {
            currentStep = 0
          }
          showCurrentStep()
        }
      } else if (e.target.matches('[data-previous]')) {
        currentStep -= 1
        showCurrentStep()
      }
    })

    formSteps.forEach(step => {
      step.addEventListener('animationend', e => {
        formSteps[currentStep].classList.remove('hide')
        e.target.classList.toggle('hide', !e.target.classList.contains('active'))
      })
    })

    function showCurrentStep () {
      formSteps.forEach((step, index) => {
        step.classList.toggle('active', index === currentStep)
      })
    }
  },
  methods: {
    persistClient () {
      RegisterServices.persistClient(
        this.firstName,
        this.lastName,
        this.email,
        this.num,
        this.address,
        this.username,
        this.password
      )
        .then(
          this.isSuccess = true,
          response => console.log(response)
        )
        .catch((exception) => console.log(exception))
    }
  }
}
</script>

<style lang="css" scoped>
@import '../assets/styles/home.css';
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
