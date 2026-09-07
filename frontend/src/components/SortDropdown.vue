<template>
    <div ref="sortWrap" class="relative w-30 h-[40px]">
        <button
            type="button"
            class="flex items-center justify-between transition w-full h-full h-8 px-2 bg-white cursor-pointer shadow-sm"
            :class="sizeClass.button"
            @click="menuToggle"
        >
            <span>{{ selected.label }}</span>
            <ChevronDown :size=sizeClass.icon />
        </button>

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
                class="absolute right-0 top-full z-20 mt-2 w-[150px] overflow-hidden bg-white shadow-sm"
                :class="sizeClass.menu"
            >
                <li v-for="option in options" :key="option.value">
                    <button
                        type="button"
                        class="flex w-full items-center px-3 py-3 transition cursor-pointer hover:bg-gray-50" 
                        :class="option.value === selected?.value ? 'text-[#4F46E5]' : ''"
                        @click="selectOption(option)">
                        <span>{{ option.label }}</span>
                    </button>
                </li>
            </ul>
        </Transition>
    </div>
</template>

<script setup>
    import { computed } from 'vue'
    import { ChevronDown } from '@lucide/vue'
    import { useClickOutside } from '@/composables/useClickOutside'

    // 메뉴 드롭다운 이벤트
    const {
        menuTarget: sortWrap,
        menuVisible,
        menuToggle,
        menuClose
    } = useClickOutside()

    const props = defineProps({
        options: { type: Array, default: () => []},
        selected: { type: Object, default: null },
        size: { type: String, default: 'md'}
    })

    const sizeClass = computed(() => {
        const size = {
            sm: { button: 'text-xs', menu: 'text-xs', icon: 16 },
            md: { button: 'text-sm', menu: 'text-sm', icon: 20 },
            lg: { button: 'text-base', menu: 'text-base', icon: 24 },
            custom: {}
        }

        return size[props.size]
    })

    const emit = defineEmits(['change'])
    const selectOption = option => {
        emit('change', option)
        menuClose()
    }
</script>