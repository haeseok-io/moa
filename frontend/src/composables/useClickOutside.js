import { ref, onMounted, onBeforeUnmount } from 'vue'

export function useClickOutside() {
    const menuTarget = ref(null)
    const menuVisible = ref(false)

    const menuToggle = () => {
        menuVisible.value = !menuVisible.value
    }

    const menuOpen = () => {
        menuVisible.value = true
    }

    const menuClose = () => {
        menuVisible.value = false
    }

    const handleClickOutside = event => {
        if( menuVisible.value && menuTarget.value && !menuTarget.value.contains(event.target) ) {
            menuClose()
        }
    }

    onMounted(() => {
        document.addEventListener('click', handleClickOutside)
    })

    onBeforeUnmount(() => {
        document.removeEventListener('click', handleClickOutside)
    })

    return {
        menuTarget,
        menuVisible,
        menuToggle,
        menuClose
    }
}