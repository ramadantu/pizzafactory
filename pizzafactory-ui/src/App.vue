<template>
  <div id="app">
    <header>
      <img src="./assets/pizza-header.jpg" alt="header" width="100%">
    </header>
    <b-navbar toggleable="lg" type="dark" variant="danger" sticky>
      <b-navbar-brand to="/" style="padding-left: 10px">PIZZA FANTASTICHE</b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item to="/">Home</b-nav-item>
          <b-nav-item to="/menu">Menu</b-nav-item>
          <b-nav-item to="/order">Order</b-nav-item>
        </b-navbar-nav>

        <b-navbar-nav class="ml-auto">
          <b-nav-item-dropdown>
            <template #button-content>
              <em>User</em>
            </template>
            <b-dropdown-item href="#">Profile</b-dropdown-item>
            <b-dropdown-item href="#">Sign Out</b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
    <router-view/>
    <footer>
      <p style="margin: 0">© <i>All copyrights are reserved</i> | 2022 Pizza Fantastiche</p>
    </footer>
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  background-image: url("assets/pizza-background.jpg");
}

header {
}

footer {
  text-align: left;
  padding: 10px;
  background-color: #b04a1f;
  position: sticky;
  bottom: 0;
  color: white;
}
</style>

<script>
export default {
  components: {}
}
</script>
