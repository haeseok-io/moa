<template>
    <main class="main">
        <div class="home__toolbar flex justify-between items-center mb-5">
            <nav class="home__tabs">
                <ul class="home__tab-list flex">
                    <li v-for="(tab, index) in tabs" :key="tab.key" class="flex items-center px-3 py-4" :class="tab.key===activeTab ? 'text-[#111827]' : 'text-[#9CA3AF]'">
                        <component :is="tab.icon" :size="20" />
                        <span class="ml-2 font-semibold text-lg">{{ tab.name }}</span>
                    </li>
                </ul>
            </nav>

            <div class="home__sort">
                <SortDropdown :options="sortOption" :selected="sortSelected" size="md" @change="changeSort"/>
            </div>
        </div>

        <div class="home__postCard">
            <PostCardList :posts="tempPosts" />
        </div>
    </main>

    <div ref="loadMoreTrigger" class="h-20 bg-black"></div>
    <div v-if="loading">데이터 불러오는중..</div>
</template>

<script setup>
    import { ref, onMounted, onUnmounted } from 'vue'
    import { useAuthStore } from '@/stores/auth'
    import { Clock, TrendingUp, Rss } from "@lucide/vue"

    import PostCardList from '@/components/PostCardList.vue'
    import SortDropdown from '@/components/SortDropdown.vue'

    // 탭 관련 설정
    const activeTab = ref('latest')
    const tabs = [
        { key: "latest", name: "최신", icon: Clock },
        { key: "pupular", name: "인기", icon: TrendingUp },
        { key: "feed", name: "피드", icon: Rss }
    ]

    // 정렬 관련 설정
    const sortOption = [
        { value: "today", label: "오늘" },
        { value: "weed", label: "이번주" },
        { value: "month", label: "이번달" },
        { value: "year", label: "올해" }
    ]

    const sortSelected = ref(sortOption[0])
    const changeSort = value => {
        sortSelected.value = value
    }

    // 페이지 스크롤 관련 설정
    const page = ref(1)
    const loading = ref(false)
    const hasNext = ref(true)
    const loadMoreTrigger = ref(null)

    const tempPosts = ref([])

    const loadPosts = () => {
        if( loading.value || !hasNext.value ) return

        // 로딩시작
        loading.value = true

        // 데이터 호출
        const tempData = postDatas;

        tempPosts.value.push(...tempData)
        page.value++

        // 마지막 페이지처리

        // 로딩 종료
        loading.value = false
    }

    let observer

    onMounted(() => {
        loadPosts()

        observer = new IntersectionObserver(([entry]) => {
            if( entry.isIntersecting ) {
                loadPosts()
            }
        })

        observer.observe(loadMoreTrigger.value)
    })

    onUnmounted(() => {
        observer.disconnect()
    })

    // 카드 리스트 임시 데이터
    const postDatas = [
        {
            id: 1,
            thumbnail: 'https://placehold.co/300x200',
            subject: '테스트 제목1111',
            details: '이건 테스트 입니다. 1번 글 입니다.',
            date: '2026-07-01 15:44:52',
            comment: 3,
            user: {
                thumbnail: 'https://placehold.co/100x100',
                nickname: '테스트 유저'
            },
            like: 10
        },
        {
            id: 2,
            thumbnail: 'https://placehold.co/300x200',
            subject: '테스트 222222',
            details: '이건 테스트 입니다. 2번 글 입니다!!!',
            date: '2026-07-10 16:33:52',
            comment: 2,
            user: {
                thumbnail: 'https://placehold.co/100x100',
                nickname: '테스트 유저2'
            },
            like: 2
        },
        {
            id: 3,
            thumbnail: 'https://placehold.co/300x200',
            subject: '테스트 222222',
            details: '이건 테스트 입니다. 2번 글 입니다!!!이건 테스트 입니다. 2번 글 입니다!!!이건 테스트 입니다. 2번 글 입니다!!!이건 테스트 입니다. 2번 글 입니다!!!이건 테스트 입니다. 2번 글 입니다!!!이건 테스트 입니다. 2번 글 입니다!!!이건 테스트 입니다. 2번 글 입니다!!!이건 테스트 입니다. 2번 글 입니다!!!이건 테스트 입니다. 2번 글 입니다!!!이건 테스트 입니다. 2번 글 입니다!!!이건 테스트 입니다. 2번 글 입니다!!!이건 테스트 입니다. 2번 글 입니다!!!이건 테스트 입니다. 2번 글 입니다!!!이건 테스트 입니다. 2번 글 입니다!!!이건 테스트 입니다. 2번 글 입니다!!!이건 테스트 입니다. 2번 글 입니다!!!',
            date: '2026-07-10 16:33:52',
            comment: 2,
            user: {
                thumbnail: 'https://placehold.co/100x100',
                nickname: '테스트 유저2'
            },
            like: 2
        },
        {
            id: 4,
            thumbnail: 'https://placehold.co/300x200',
            subject: '테스트 222222',
            details: '이건 테스트 입니다. 2번 글 입니다!!!',
            date: '2026-07-10 16:33:52',
            comment: 2,
            user: {
                thumbnail: 'https://placehold.co/100x100',
                nickname: '테스트 유저2'
            },
            like: 2
        },
        {
            id: 5,
            thumbnail: 'https://placehold.co/300x200',
            subject: '테스트 222222',
            details: '이건 테스트 입니다. 2번 글 입니다!!!',
            date: '2026-07-10 16:33:52',
            comment: 2,
            user: {
                thumbnail: 'https://placehold.co/100x100',
                nickname: '테스트 유저2'
            },
            like: 2
        },
        {
            id: 6,
            thumbnail: 'https://placehold.co/300x200',
            subject: '테스트 222222',
            details: '이건 테스트 입니다. 2번 글 입니다!!!',
            date: '2026-07-10 16:33:52',
            comment: 2,
            user: {
                thumbnail: 'https://placehold.co/100x100',
                nickname: '테스트 유저2'
            },
            like: 2
        },
        {
            id: 7,
            thumbnail: 'https://placehold.co/300x200',
            subject: '테스트 222222',
            details: '이건 테스트 입니다. 2번 글 입니다!!!',
            date: '2026-07-10 16:33:52',
            comment: 2,
            user: {
                thumbnail: 'https://placehold.co/100x100',
                nickname: '테스트 유저2'
            },
            like: 2
        },
        {
            id: 8,
            thumbnail: 'https://placehold.co/300x200',
            subject: '테스트 222222',
            details: '이건 테스트 입니다. 2번 글 입니다!!!',
            date: '2026-07-10 16:33:52',
            comment: 2,
            user: {
                thumbnail: 'https://placehold.co/100x100',
                nickname: '테스트 유저2'
            },
            like: 2
        }
    ]
</script>