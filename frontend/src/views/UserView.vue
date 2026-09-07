<template>
    <main>
        <section class="flex items-center w-[70%] py-10 mx-auto border-b border-[#e1e1e1]">
            <div class="w-[130px] h-[130px] rounded-2xl overflow-hidden">
                <img
                    src="https://placehold.co/300x200"
                    alt=""
                    class="w-full h-full object-cover"    
                >
            </div>
            <div>
                <RouterLink
                    :to="`/@${auth.user.nickname}/post`"
                    class="ml-5 font-semibold text-2xl"
                >
                    {{ auth.user.nickname }}
                </RouterLink>
            </div>
            <div class="flex gap-5 ml-auto">
                <p class="font-light"><span class="font-bold">0</span> 팔로워</p>
                <p class="font-light"><span class="font-bold">0</span> 팔로잉</p>
            </div>
        </section>
        <section class="w-[80%] py-20 m-auto">
            <div class="flex justify-center">
                <div class="relative">
                    <ul class="flex">
                        <li
                            v-for="(data, idx) in tabList"
                            :key="idx"
                        >
                            <RouterLink
                                :to="data.to"
                                class="block w-[120px] h-[60px] flex justify-center items-center text-xl"
                                :class="
                                    idx===activeTabIndex ? 'text-[#4F46E5] font-semibold' : 'text-[#9CA3AF]'
                                "
                            >
                                {{ data.name }}
                            </RouterLink>
                        </li>
                    </ul>
                    <span
                        class="absolute bottom-0 left-0 w-[120px] h-[2px] bg-[#4F46E5] transition-transform duration-200"
                        :style="{ transform: `translate(${activeTabIndex*120}px)` }"
                    >
                    </span>
                </div>
            </div>
             <component :is="currentView" />
        </section>
    </main>
</template>

<script setup>
    import { computed } from 'vue';
    import { useAuthStore } from '@/stores/auth';
    import { useRoute } from 'vue-router';
    import PostView from '@/views/PostView.vue';
    import AboutView from '@/views/AboutView.vue';

    const auth = useAuthStore()
    const route = useRoute()

    console.log(route.params.username)
    console.log(route.params.page)

    // 탭정보
    const tabList = computed(() => [
        { name: '내 글', to: `/@${auth.user.nickname}/post` },
        { name: '소개', to: `/@${auth.user.nickname}/about` }
    ])

    // 현재 탭 번호
    const activeTabIndex = computed(() => {
        return tabList.value.findIndex(
            tab => tab.to === route.fullPath
        )
    })

    // page 값에 따른 view 지정
    const currentView = computed(() => {
        switch(route.params.page) {
            case 'post' :
                return PostView
            case 'about' :
                return AboutView
            default :
                return PostView
        }
    })
</script>