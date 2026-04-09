<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { fetchBoardList } from '@/api/board'

const posts = ref([])
const router = useRouter()

onMounted(async () => {
  posts.value = await fetchBoardList()
})
</script>

<template>
  <section class="board">
    <div class="board-header">
      <h1>게시글 목록</h1>
      <button @click="router.push('/board/reg')">글쓰기</button>
    </div>
    <table>
      <thead>
        <tr>
          <th style="width: 80px">번호</th>
          <th>제목</th>
        </tr>
      </thead>
      <tbody>
        <tr v-if="posts.length === 0">
          <td colspan="2" class="empty">등록된 게시글이 없습니다.</td>
        </tr>
        <tr v-for="p in posts" :key="p.idx">
          <td>{{ p.idx }}</td>
          <td>
            <a href="#" @click.prevent="router.push(`/board/${p.idx}`)">{{ p.title }}</a>
          </td>
        </tr>
      </tbody>
    </table>
  </section>
</template>

<style scoped>
.board {
  max-width: 800px;
  margin: 2rem auto;
  padding: 0 1rem;
}
.board-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 1rem;
}
table {
  width: 100%;
  border-collapse: collapse;
}
th, td {
  border: 1px solid #ccc;
  padding: 0.5rem;
  text-align: left;
}
th {
  background: #f5f5f5;
}
.empty {
  text-align: center;
  color: #888;
}
a {
  cursor: pointer;
}
button {
  padding: 0.5rem 1rem;
  cursor: pointer;
}
</style>
