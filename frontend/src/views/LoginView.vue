<template>
    <main>
        <div class="login__responsive h-screen flex items-center">
            <div class="login__visual w-[calc(100%-800px)] flex justify-center">
                <div class="visual__content text-center">
                    <img src="@/assets/image/moa-logo.svg" width="200" alt="">
                    <p class="mt-5 font-bold text-2xl">환영합니다!</p>
                </div>
            </div>
            <div class="login__content w-[800px] h-full bg-white flex items-center px-10">
                <AuthForm
                    v-model="form"
                    @submit="login"
                    :is-email-send="isEmailSend"
                />
            </div>
        </div>
    </main>
</template>

<script setup>
    import { reactive, ref } from 'vue'
    import { useRouter } from 'vue-router'
    import { useAuthStore } from '@/stores/auth'
    import { isValidEmail } from '@/utils/validation';
    import AuthForm from '@/components/AuthForm.vue';
    import axios from 'axios';

    const router = useRouter()
    const auth = useAuthStore();
    const isEmailSend = ref(false)

    const form = reactive({
        email: { value: '', error: '' }
    })

    const login = async () => {
        // Check
        if( !form.email.value ) {
            form.email.error = '이메일을 입력해주세요.'
            return
        }
        if( !isValidEmail(form.email.value) ) {
            form.email.error = '이메일 형식을 확인해주세요.'
            return
        }
        
        // Data
        form.email.error = '';

        // Process
        try {
            // 이메일 발송
            await axios.post('http://localhost:8080/api/auth/email/request', {email: form.email.value});
            
            // 이메일 발송 성공
            isEmailSend.value = true;
            form.email.success = '이메일이 발송되었습니다.';
        } catch(error) {
            form.email.error = error;
        }
    }
</script>

<style scope>

</style>