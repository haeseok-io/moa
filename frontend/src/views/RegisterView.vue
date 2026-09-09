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
                    mode="register"
                    @submit="register"
                />
            </div>
        </div>
    </main>
</template>

<script setup>
    import { reactive } from 'vue'
    import { useRouter } from 'vue-router'
    import { useAuthStore } from '@/stores/auth'
    import { isValidEmail } from '@/utils/validation';
    import AuthForm from '@/components/AuthForm.vue';

    const router = useRouter()
    const auth = useAuthStore();

    const form = reactive({
        email: { value: '', error: '' }
    })

    const register = () => {

        // 유효성 체크
        if( !form.email.value ) {
            form.email.error = '이메일을 입력해주세요.'
            return
        }
        if( !isValidEmail(form.email.value) ) {
            form.email.error = '이메일 형식을 확인해주세요.'
            return
        }

        // 로그인 성공 처리
        alert('로그인 완료!야호!')
        router.push('/login')
    }
</script>

<style scope>

</style>