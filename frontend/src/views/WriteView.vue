<template>
    <main class="h-screen p-8">
        <form @submit.prevent="write" class="h-full">
            <input type="hidden" name="markdown">

            <div class="editor__title mb-8">
                <input type="text" v-model="title" class="h-20 text-4xl font-bold outline-none" placeholder="제목을 입력해주세요.">
            </div>
            <div ref="editorRef" class="overflow-hidden"></div>
            <div class="editor__button flex justify-end gap-3 mt-8">
                <BaseButton theme="lightgray" height="h-10" back>돌아가기</BaseButton>
                <BaseButton type="submit" theme="main" height="h-10">작성하기</BaseButton>
            </div>
        </form>
    </main>
</template>

<script setup>
    import { ref, onMounted } from 'vue';
    import { Editor } from '@toast-ui/editor';
    import '@toast-ui/editor/dist/i18n/ko-kr'
    import '@toast-ui/editor/dist/toastui-editor.css'
    import BaseButton from '@/components/BaseButton.vue';

    const editorRef = ref(null)
    const editor = ref(null)


    // 에디터 설정
    onMounted(() => {
        editor.value = new Editor({
            el: editorRef.value,
            language: 'ko-KR',
            height: 'calc(100% - 184px)',
            initialEditType: 'markdown',
            hideModeSwitch: true,
            previewStyle: 'vertical',
            placeholder: '내용을 입력하세요.',

            hooks: {
                addImageBlobHook(blob, callback) {
                    // 이미지 업로드 api 호출
                    uploadImage(blob)
                        .then(url => {
                            callback(url, 'image')
                        })
                }
            }
        })
    })

    const write = () => {
        console.log(editor.value.getMarkdown());
    }

    // 임시
    const uploadImage = blob => {
        return new Promise(resolve => {
            const url = URL.createObjectURL(blob)
            resolve(url)
        })
    }

    
</script>

<style scoped>
    /* 불필요한 UI 제거 */
    :deep(.toastui-editor-defaultUI) {
        border: none;
    }
    :deep(.toastui-editor-defaultUI-toolbar) {
        background: #F8F9FA;
    }
</style>