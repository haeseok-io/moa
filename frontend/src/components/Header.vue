<template>
    <header class="header mb-5">
        <div class="conatiner h-[80px] flex items-center">
            <h1 class="header__logo">
                <RouterLink to="/">
                    <img src="@/assets/image/moa-logo.svg" class="w-auto h-[50px]" alt="">
                </RouterLink>
            </h1>
            <div class="header__menu ml-auto">
                <nav>
                    <ul v-if="!auth.isLogin">
                        <li><BaseButton to="/login">로그인</BaseButton></li>
                    </ul>

                    <div v-else ref="profileWrap" class="flex gap-5 items-center">
                        <Bell :size=22 />
                        <BaseButton to="/write" theme="line">새 글 작성</BaseButton>
                        <div class="profile__wrap relative">
                            <div
                                class="profile__image flex gap-1 items-center"
                                @click="menuToggle"
                            >
                                <img src="https://placehold.co/300x200" class="w-10 h-10 object-cover rounded-full cursor-pointer" alt="">
                                <ChevronDown :size=20 />
                            </div>
                            <Transition
                                enter-active-class="transition duration-150"
                                enter-from-class="opacity-0 translate-y-1"  
                                enter-to-class="opacity-100 translate-y-0"
                                leave-active-class="transition duration-100"
                                leave-from-class="opacity-100 translate-y-0"
                                leave-to-class="opacity-0 translate-y-1"
                            >
                                <ul 
                                    v-if="menuVisible"
                                    class="absolute right-0 top-full z-20 w-[200px] mt-4 overflow-hidden border border-gray-200 bg-white shadow-sm"
                                >
                                    <li v-for="(data, key) in profileMenuList" :key="key">
                                        <RouterLink
                                            :to="data.to"
                                            @click="profileMenuClick(data)"
                                            class="flex w-full items-center px-3 py-3 transition hover:bg-gray-50 hover:text-[#4F46E5]"
                                        >
                                            {{ data.name }}
                                        </RouterLink>
                                    </li>
                                </ul>
                            </Transition>
                        </div>
                    </div>
                </nav>
            </div>
        </div>
    </header>
</template>

<script setup>
    import { computed } from 'vue'
    import { useAuthStore } from '@/stores/auth'
    import { Bell, ChevronDown } from '@lucide/vue'
    import BaseButton from '@/components/BaseButton.vue';
    import { useClickOutside } from '@/composables/useClickOutside'

    const auth = useAuthStore()

    // 메뉴 드롭다운 이벤트
    const {
        menuTarget: profileWrap,
        menuVisible,
        menuToggle,
        menuClose
    } = useClickOutside()

    // 프로필 메뉴 목록
    const profileMenuList = computed(() => [
        {
            type: 'mypost',
            name: '내 글',
            to: `/@${auth.user?.nickname}/post`,
        },
        {
            type: 'draft',
            name: '임시 글',
            to: ''
        },
        {
            type: 'reading',
            name: '읽기 목록',
            to: ''
        },
        { 
            type: 'setting',
            name: '설정', 
            to: ''
        },
        {
            type: 'logout',
            name: '로그아웃', 
            to: '/'
        }
    ])

    // 프로필 메뉴 클릭 이벤트 처리
    const profileMenuClick = data => {
        menuClose()

        if( data.type==='logout' ) {
            auth.logout()
        }
    }
</script>