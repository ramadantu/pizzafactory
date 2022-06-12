<template>
  <div id="body">
    <div id="filter-table">
      <div id="spin-button">
        <label id="text">Per page:</label>
        <b-spinbutton
          v-on:change="filterMenus"
          v-model="perPage"
          min="1"
          max="10">
        </b-spinbutton>
      </div>
      <div id="filter-input">
        <b-form-input
          v-model="filters.item"
          type="search"
          placeholder="Type to Search"
          @input="filterMenus">
        </b-form-input>
      </div>
    </div>

    <b-table
      id="my-table"
      striped hover fixed
      :items="menus"
      :fields="fields"
      :filter="filters.item"
      :per-page="0"
      :current-page="currentPage"
    >
      <template v-slot:cell(drink)="list">
        <p v-for="element in list.item.drink" :key="element.id" style="display: inline">
        {{element.name}},
        </p>
      </template>
    </b-table>

    <b-pagination
      id="pagination"
      v-model="currentPage"
      :total-rows="totalElements"
      :per-page="perPage"
      aria-controls="my-table"
      @input="filterMenus"
    ></b-pagination>
  </div>
</template>

<script>
import MenuServices from '../services/menu-services'

export default {
  name: 'MenuView',
  created () {
    this.filterMenus()
  },
  data () {
    return {
      perPage: 5,
      currentPage: 1,
      totalElements: 0,
      totalPages: 0,
      filters: {
        item: ''
      },
      fields: [
        { key: 'id', label: 'Идентификатор' },
        { key: 'item', label: 'Име' },
        { key: 'description', label: 'Съставки' },
        { key: 'drink', label: 'Напитки' }
      ],
      menus: [
        {
          id: '',
          item: '',
          description: '',
          drink: [
            {
              id: '',
              name: ''
            }
          ]
        }
      ]
    }
  },
  methods: {
    filterMenus () {
      MenuServices.filterMenus(
        this.perPage,
        this.currentPage,
        this.filters.item
      )
        .then(
          (response) => {
            this.menus = response.data.menus
            this.totalElements = response.data.totalElements
            this.totalPages = response.data.totalPages
            console.log(response)
          }
        )
    }
  }
}
</script>

<style lang="css">
@import '../assets/styles/menu.css';
</style>
