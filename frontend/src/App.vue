<template>
    <div :class="!isHideLayout() ? 'reponsive w-[1440px] m-auto size-fit' : ''">
        <Header v-if="!isHideLayout()"/>
        <RouterView />
    </div>
</template>

<script setup>
    import { onMounted } from "vue";
    import { useRoute } from "vue-router";
    import { useAuthStore } from "@/stores/auth";
    import Header from "@/components/Header.vue";

    // 로그인 유지
    const auth = useAuthStore()
    auth.restoreLogin()

    // 헤더 제거 컴포넌트
    const route = useRoute()
    const hideLayoutPages = ['login', 'register', 'write']
    const isHideLayout = () => {
        return hideLayoutPages.includes(route.name)
    }
</script>