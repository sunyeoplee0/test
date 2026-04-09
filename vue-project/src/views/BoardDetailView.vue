<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { fetchBoardDetail } from '@/api/board'

const props = defineProps({
  idx: { type: [String, Number], required: true },
})

const post = ref(null)
const router = useRouter()

onMounted(async () => {
  try {
    post.value = await fetchBoardDetail(props.idx)
  } catch {
    alert('게시글을 찾을 수 없습니다.')
    router.replace('/board/list')
  }
})
</script>

<template>
  <section class="board" v-if="post">
    <h1>{{ post.title }}</h1>
    <div class="meta">번호: {{ post.idx }}</div>
    <pre class="content">{{ post.content }}</pre>
    <button @click="router.push('/board/list')">목록보기</button>
  </section>
</template>

<style scoped>
.board {
  max-width: 800px;
  margin: 2rem auto;
  padding: 0 1rem;
}
.meta {
  color: #888;
  margin-bottom: 1rem;
}
.content {
  border: 1px solid #ccc;
  padding: 1rem;
  min-height: 200px;
  white-space: pre-wrap;
  word-break: break-word;
  font-family: inherit;
  margin-bottom: 1rem;
}
button {
  padding: 0.5rem 1rem;
  cursor: pointer;
}
</style>
