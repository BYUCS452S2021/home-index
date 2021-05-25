<template>
    <v-container>
        <h1 class="text-h4 mb-4">Properties</h1>
        <v-data-table
            :items="properties"
            :headers="headers"
            @click:row="handleClick"
        />
    </v-container>
</template>

<script lang="ts">
    import { Vue, Component } from 'vue-property-decorator';
    import axios from 'axios';

    @Component
    export default class Properties extends Vue {
        
        properties = [];
        headers = [
          { text: 'Nickname', value: 'nickname' },
          { text: 'Address', value: 'address' }
        ];

        handleClick(item: any) {
            this.$router.push(`${item.id}`)
        }

        async mounted() {
            try {
                const response = await axios.get('/user-account/testuser/properties');
                this.properties = response.data;
            } catch (e) {
                console.log(e)
            }
        }
    }
</script>
