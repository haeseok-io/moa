<template>
    <ul class="grid grid-cols-4 gap-x-8 gap-y-8">
        <PostCard 
            v-for="data in posts"
            :key="data.id"
            :data="data"
        />
    </ul>
</template>

<script setup>
    import PostCard from './PostCard.vue'

    defineProps({
        posts: Array
    })
</script>