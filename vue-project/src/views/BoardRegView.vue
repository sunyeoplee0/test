<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { registerBoard } from '@/api/board'

const title = ref('')
const content = ref('')
const router = useRouter()

const submit = async () => {
  const res = await registerBoard({ title: title.value, content: content.value })
  alert(res.message ?? '게시글이 등록되었습니다.')
  router.push('/board/list')
}
</script>

<template>
  <section class="board">
    <h1>게시글 작성</h1>
    <form @submit.prevent="submit">
      <input v-model="title" placeholder="제목" required />
      <textarea v-model="content" placeholder="내용" rows="10" required></textarea>
      <div class="actions">
        <button type="button" @click="router.push('/board/list')">취소</button>
        <button type="submit">저장</button>
      </div>
    </form>
  </section>
</template>

<style scoped>
.board {
  max-width: 800px;
  margin: 2rem auto;
  padding: 0 1rem;
}
form {
  display: flex;
  flex-direction: column;
  gap: 0.75rem;
}
input, textarea {
  padding: 0.5rem;
  font-family: inherit;
  font-size: 1rem;
  border: 1px solid #ccc;
}
.actions {
  display: flex;
  gap: 0.5rem;
  justify-content: flex-end;
}
button {
  padding: 0.5rem 1rem;
  cursor: pointer;
}
</style>
