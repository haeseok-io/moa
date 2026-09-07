<template>
    <button
        :type="props.type"
        :class="[
            'transition font-medium cursor-pointer',
            themeClass,
            sizeClass,
            roundedClass,
            {
                'opacity-50 cursor-not-allowed': disabled || loading
            }
        ]"
        @click="clickHandler"
    >
        <slot />
    </button>
</template>

<script setup>
    import { computed } from 'vue'
    import { useRouter } from 'vue-router'

    // 버튼 설정
    const props = defineProps({
        type: { type: String, default: 'button' },
        theme: { type: String, default: 'primary'},
        size: { type: String, default: 'md' },
        rounded: { type: Boolean, default: true },
        disabled: { type: Boolean, default: false },
        loading: { type: Boolean, default: false },
        to: { type: String, default: null },
        back: { type: Boolean, default: false },
        width: { type: String, default: '' },
        height: { type: String, default: '' }
    })

    // 조건별 클래스 설정
    const themeClass = computed(() => {
        switch(props.theme) {
            case 'main' :
                return 'bg-[#4F46E5] text-[#F8F9FA] hover:bg-[#4338CA] hover:text-white'
            case 'sub' :
                return 'bg-[#8B5CF6] text-[#F8F9FA] hover:bg-[#7C3AED] hover:text-white'
            case 'gray' :
                return 'bg-[#9CA3AF] text-[#F8F9FA] hover:bg-[#6B7280] hover:text-white'
            case 'lightgray' :
                return 'bg-[#E5E7EB] text-[#374151] hover:bg-[#D1D5DB] hover:text-[#111827]'
            case 'line' :
                return 'bg-[#F8F9FA] text-[#111827] border border-[#111827] hover:bg-[#111827] hover:text-[#F8F9FA]'
            default :
                return 'bg-[#111827] text-[#F8F9FA] hover:bg-[#0B1220] hover:text-white'
        }
    })

    const sizeClass = computed(() => {
        let baseSizeClass = '';

        switch(props.size) {
            case 'xs' :
                baseSizeClass = 'px-2 py-1 text-xs'
                break
            case 'sm' : 
                baseSizeClass = 'px-2 py-1.5 text-sm'
                break
            case 'lg' :
                baseSizeClass = 'px-4 py-1.5 text-lg'
                break
            case 'xl' :
                baseSizeClass = 'px-4 py-2 text-xl'
                break
            case 'full' :
                baseSizeClass = props.height
                    ? (props.width ? '' : 'w-full')
                    : (props.width ? 'h-full py-3' : 'w-full h-full')
                break
            default : 
                baseSizeClass = 'px-3 py-1.5 text-base'
        }

        return `${baseSizeClass} ${props.width} ${props.height}`.trim()
    })

    const roundedClass = computed(() => {
        switch(props.rounded) {
            case true :
                return 'rounded-lg'
            default: 
                return ''
        }
    })

    // 버튼 클릭 핸들러
    const router = useRouter()
    const emit = defineEmits(['click'])
    const clickHandler = event => {
        // 로딩중이거나 비활성화 상태라면 예외처리
        if( props.disabled || props.loading ) {
            return
        }

        // to 파라미터가 있을경우 라우터 이동처리
        if( props.to ) {
            router.push(props.to)
            return
        }

        // 뒤로가기
        if( props.back ) {
            router.back()
            return
        }

        // 기본 이벤트 처리
        emit('click', event)
    }
</script>