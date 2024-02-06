<script setup lang="ts">
import { useRoute } from 'vue-router'

const routes = useRoute()
const studendId = routes.params.id


</script>

<template>
  <nav class="nav waikawa-bg-800">
    <input type="checkbox" name="nav-check" id="nav-check" />
    <header class="nav-header">
      <RouterLink :to="{ name: 'home' }" class="nav-title text-size-6 waikawa-200"
        ><i class="trademark icon"></i
      ></RouterLink>
    </header>
    <div class="nav-links">
      <RouterLink class="text-size-3 waikawa-200" :to="{ name: 'profile' }">Perfil</RouterLink>
      <RouterLink
        class="text-size-3 waikawa-200"
        :to="{
          name: 'course-detail'
          
        }"
        >Asignatura</RouterLink
      >
      <a
        class="text-size-3 waikawa-200"
        href="http://stackoverflow.com/users/4084003/"
        target="_blank"
        >Servicio</a
      >
    </div>
  </nav>
</template>

<style scoped>
* {
  box-sizing: border-box;
}
.nav {
  height: 50px;
  width: 100%;
  position: relative;
  display: flex;
  justify-content: space-between;
  margin: 0px;
  font-family: 'segoe ui';
}

.nav > nav-header {
  display: inline;
}
.nav-title {
  margin: 9px;
  text-align: right;
}

.nav > .nav-btn {
  display: none;
}
.nav > .nav-links {
  display: inline;
  justify-content: end;
}

.nav > .nav-links > a {
  display: inline-block;
  padding: 13px;
  margin: 0 5px;
  text-decoration: none;
}
.nav > .nav-links > a:hover {
  background-color: rgba(0, 0, 0, 0.3);
  outline: 4px solid rgba(0, 0, 0, 0.3);
}
.nav > #nav-check {
  display: none;
}
</style>
