<template>
    <div class="w-[500px]">
        <form @submit.prevent="submit">
            <BaseInput
                v-model="props.modelValue.email.value"
                :error="props.modelValue.email.error"
                :success="props.modelValue.email.success"
                label="이메일로 로그인"
                placeholder="이메일을 입력하세요."
                :disabled="props.isEmailSend"
            >
                <template #append>
                    <BaseButton
                        type="submit"
                        width="w-[100px]"
                        height="h-[40px]"
                        :rounded="false"
                        :disabled="props.isEmailSend"
                    >
                        {{ props.isEmailSend ? '발송완료' : '로그인' }}
                    </BaseButton>
                </template>
            </BaseInput>
        </form>
        <div class="login__oauth mt-5">
            <p class="text-gray-500">소셜 계정으로 로그인</p>
            <ul class="flex items-center gap-5 text-white mt-4">
                <li
                    v-for="(data, index) in iconList"
                    :key="index"
                    class="w-[40px] h-[40px] bg-[#1A3553] rounded-full"
                    :class="{ 'pointer-events-none opacity-50' : props.isEmailSend }"
                >
                    <a :href="data.path" class="flex w-full h-full justify-center items-center">
                        <SimpleIcon :size="20" :icon="data.icon" />
                    </a>
                </li>
            </ul>
        </div>

        <div class="flex justify-between items-center mt-10 text-sm">
            <p class="text-gray-400">처음 이용하시는 경우 메일 인증 후 자동 회원가입됩니다.</p>
        </div>
    </div>
</template>

<script setup>
    import { siGithub, siGoogle, siDiscord, siGit } from 'simple-icons';
    import SimpleIcon from '@/components/SimpleIcon.vue';
    import BaseButton from '@/components/BaseButton.vue';   
    import BaseInput from '@/components/BaseInput.vue';
    

    const props = defineProps({
        modelValue: {
            type: Object,
            required: true
        },
        isEmailSend: {
            type: Boolean,
            default: false
        }
    })
    
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