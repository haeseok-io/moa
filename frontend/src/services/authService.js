import api from "@/api/axios";

// 로그인 API
export const login = async loginData => {
    // 현재 테스트 코드, 추 후 spring boot api 호출 
    return {
        user: {
            id: 1,
            nickname: '테스트유저1',
            email: 'test@gmail.com'
        },
        accessToken: 'temp-token'
    }
}