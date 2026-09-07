<template>
    <span class="simple-icon">
        <svg
            :width="props.size"
            :height="props.size"
            :viewBox="viewBox"
            fill="currentColor"
        >
            <path :d="props.icon.path" />
        </svg>
    </span>
</template>

<script setup>
    import { computed } from 'vue';

    const props = defineProps({
        icon: {
            type: Object,
            required: true
        },
        size: {
            type: Number,
            default: 20
        },
    })

    const viewBox = computed(() => props.icon.svg.match(/viewBox="([^"]+)"/)?.[1] ?? '0 0 24 24')
</script>