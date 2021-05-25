<template>
    <v-container>
        <h1 class="text-h4 mb-4">Containers</h1>
        <v-data-table
            :items="containers"
            :headers="headers"
        />
    </v-container>
</template>

<script lang="ts">
    import { Vue, Component } from 'vue-property-decorator';
    import axios from 'axios';

    @Component
    export default class containers extends Vue {
        
        containers = [];
        headers = [
          { text: 'Nickname', value: 'nickname' },
          { text: 'Description', value: 'description' }
        ];

        async mounted() {
            try {
                const spaceId = this.$route.params.spaceId;
                const response = await axios.get(`/space/${spaceId}/containers`);
                this.containers = response.data;
            } catch (e) {
                console.log(e)
            }
        }
    }
</script>
