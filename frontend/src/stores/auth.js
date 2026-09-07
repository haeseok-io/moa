import { defineStore } from 'pinia';
import { ref } from 'vue';

export const useAuthStore = defineStore('auth', () => {
    const isLogin = ref(false); // 로그인상태
    const user = ref(null); // 유저정보
    const accessToken = ref(null); // 토큰값


    // 로그인 처리
    const login = (userInfo, token) => {
        isLogin.value = true;
        user.value = userInfo;
        accessToken.value = token;

        // 로컬스토리지에 임시 저장 (로그인 구현 전까지만 사용)
        localStorage.setItem('user', JSON.stringify(userInfo))
        localStorage.setItem('accessToken', token)
    }

    // 로그아웃 처리
    const logout = () => {
        isLogin.value = false;
        user.value = null;
        accessToken.value = null;

        console.log(111)

        // 로컬스토리지에 제거 (로그인 구현 전까지만 사용)
        localStorage.removeItem('user')
        localStorage.removeItem('accessToken')
    }

    // 새로고침 후 로그인 정보 병합 ( 로그인 구현 후 코드 수정 필요 )
    const restoreLogin = () => {
        const savedUser = localStorage.getItem('user')
        const savedToken = localStorage.getItem('accessToken')

        if( savedUser && savedToken ) {
            isLogin.value = true
            user.value = JSON.parse(savedUser)
            accessToken.value = savedToken
        }
    }

    return {isLogin, user, accessToken, login, logout, restoreLogin}
})