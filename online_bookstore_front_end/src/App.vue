<template >
  <mynav />
</template>

<script>
import mynav from "./components/mynav.vue";
import "@element-plus/icons-vue";
export default {
  name: "App",
  components: {
    mynav,
  },
};
</script>

<style >
html,
body,
#app {
  height: 100%;
}
body {
  height: 100%;
  background-color: #f1f2f6;
}
</style>
