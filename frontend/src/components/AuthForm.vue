<template>
    <div class="w-[500px]">
        <h3 class="text-2xl font-bold mb-10">repoHub</h3>
        <form @submit.prevent="submit">
            <BaseInput
                v-model="props.modelValue.email.value"
                :error="props.modelValue.email.error"
                :label="'이메일로 '+modeText"
                placeholder="이메일을 입력하세요."
            >
                <template #append>
                    <BaseButton
                        type="submit"
                        width="w-[100px]"
                        height="h-[40px]"
                        :rounded="false"
                    >
                        {{ modeText }}
                    </BaseButton>
                </template>
            </BaseInput>
        </form>
        <div class="login__oauth mt-5">
            <p class="text-gray-400">소셜 계정으로 {{ modeText }}</p>
            <ul class="flex items-center gap-10 text-white mt-4">
                <li
                    v-for="(data, index) in iconList"
                    :key="index"
                    class="w-[50px] h-[50px] bg-[#1A3553] rounded-full"
                >
                    <a :href="data.path" class="flex w-full h-full justify-center items-center">
                        <SimpleIcon :size="24" :icon="data.icon" />
                    </a>
                </li>
            </ul>
        </div>

        <div class="flex justify-between items-center mt-20 text-sm">
            <p>
                <span class="mr-2">{{ footText }}</span>
                <RouterLink :to="modePath" class="font-semibold hover:border-b">{{ modeTextReverse }}</RouterLink>
            </p>
            <RouterLink to="/" class="inline-flex items-center text-gray-300 border-b border-dashed border-gray-300 hover:text-gray-500">
                <MoveLeft :size=18 />
                <span class="inline-block ml-2">홈으로</span>
            </RouterLink>
        </div>
    </div>
</template>

<script setup>
    import { computed } from 'vue';
    import { MoveLeft } from '@lucide/vue'
    import { siGithub, siGoogle, siDiscord, siGit } from 'simple-icons';
    import SimpleIcon from '@/components/SimpleIcon.vue';
    import BaseButton from '@/components/BaseButton.vue';   
    import BaseInput from '@/components/BaseInput.vue';
    

    const props = defineProps({
        modelValue: {
            type: Object,
            required: true
        },
        mode: {
            type: String,
            default: 'login'
        }
    })
    
    const isLogin = computed(() => props.mode==='login')
    const modeText = computed(() => isLogin.value ? '로그인' : '회원가입')
    const modeTextReverse = computed(() => isLogin.value ? '회원가입' : '로그인')
    const footText = computed(() => isLogin.value ? '아직 회원이 아니신가요?' : '계정이 이미 있으신가요?')
    const modePath = computed(() => isLogin.value ? '/register' : '/login')
    const iconList = [
        { icon: siGithub, path: '' },
        { icon: siGoogle, path: '' },
        { icon: siDiscord, path: '' }
    ]

    const emit = defineEmits([
        'update:modelValue',
        'submit'
    ])
    const submit = () => {
        emit('submit')
    }
</script>