<template>
    <v-container>
        <h1 class="text-h4 mb-4">Spaces</h1>
        <v-data-table
            :items="spaces"
            :headers="headers"
            @click:row="handleClick"
        />
    </v-container>
</template>

<script lang="ts">
    import { Vue, Component } from 'vue-property-decorator';
    import axios from 'axios';

    @Component
    export default class spaces extends Vue {
        
        spaces = [];
        headers = [
          { text: 'Nickname', value: 'nickname' },
          { text: 'Description', value: 'description' }
        ];

        handleClick(item: any) {
            this.$router.push(`${this.$route.params.propertyId}/${item.id}`)
        }

        async mounted() {
            try {
                const propertyId = this.$route.params.propertyId;
                const response = await axios.get(`/property/${propertyId}/spaces`);
                this.spaces = response.data;
            } catch (e) {
                console.log(e)
            }
        }
    }
</script>
