<template>
  <div>
    <b-button variant="success" v-on:click="filterMenus">Load Menu</b-button>

    <b-pagination
      v-model="currentPage"
      :total-rows="totalElements"
      :per-page="perPage"
      aria-controls="my-table"
      @input="filterMenus"
    ></b-pagination>

    <b-spinbutton id="demo-sb" v-on:change="filterMenus" v-model="perPage" min="1" max="100"></b-spinbutton>

    <p class="mt-3">
      Current Page: {{ currentPage }} | Total Rows: {{totalElements}} | Total Pages: {{totalPages}}
    </p>

    <b-table
      id="my-table"
      striped hover
      :items="menus"
      :fields="fields"
      :per-page="0"
      :current-page="currentPage"
    >
      <template slot="top-row" slot-scope="{ fields }">
        <td v-for="field in fields" :key="field.key">
          <input v-model="filters[field.key]" :placeholder="field.label">
        </td>
      </template>

      <template v-slot:cell(drink)="list">
        <td v-for="element in list.item.drink" :key="element.id">
        {{element.name}},
        </td>
      </template>
    </b-table>
  </div>
</template>

<script>
import MenuServices from '../services/menu-services'

export default {
  name: 'MenuView',
  data () {
    return {
      perPage: 5,
      currentPage: 1,
      totalElements: 0,
      totalPages: 0,
      filters: {
        id: '',
        item: '',
        description: '',
        drink: []
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
      MenuServices.filterMenus(this.perPage, this.currentPage, this.filters.item).then(
        (response) => {
          console.log(response)
          this.menus = response.data.menus
          this.totalElements = response.data.totalElements
          this.totalPages = response.data.totalPages
        }
      )
    }
  }
}
</script>

<style scoped>

</style>
